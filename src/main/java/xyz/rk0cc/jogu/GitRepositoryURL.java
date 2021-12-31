package xyz.rk0cc.jogu;

import org.jetbrains.annotations.UnmodifiableView;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * An interface that indicating this object is referencing the location of remote Git repository.
 *
 * @since 1.0.0
 */
public sealed interface GitRepositoryURL extends Serializable permits GitUserinfoRepositoryURL, LegitURIFormat {
    /**
     * An unmodifiable {@link Set} that indicating which {@link Class} can be used for
     * {@link #parse(String, Class) parser}.
     */
    @UnmodifiableView Set<Class<? extends GitRepositoryURL>> PARSER_APPLY_TYPE = Set.of(
            GitRepositoryURL.class,
            GitAltSSHRepositoryURL.class,
            GitHttpsRepositoryURL.class,
            GitGitRepositoryURL.class,
            GitSSHRepositoryURL.class
    );

    /**
     * Indicate where the repository hosted.
     *
     * @return Repository's host with port if applied.
     */
    @Nonnull
    String host();

    /**
     * Indicate where the repository located in the hosting server.
     *
     * @return Path to the repository in hosting server.
     */
    @Nonnull
    String path();

    /**
     * Assemble {@link #host()} and {@link #path()} with other required section to combine a completed {@link String} of
     * Git repository URL address
     *
     * @return A {@link String} of repository address.
     */
    @Nonnull
    String assembleURL();

    /**
     * Determine is {@link #path()} with <code>.git</code> ended.
     *
     * @apiNote <code>path/to/.git</code> is not accepted since it refer to Git repository's control directory.
     *
     * @return <code>true</code> if contains.
     */
    default boolean isDotGitEndedPath() {
        String[] dotSpilt = path().split("\\.");
        if (Pattern.matches(".*/$", dotSpilt[dotSpilt.length - 2])) return false;
        return dotSpilt[dotSpilt.length - 1].equals("git");
    }

    /**
     * Parsing URL address to subclass of {@link GitRepositoryURL}.
     *
     * @param gitURL Git repository URL address.
     * @param returnType Which {@link Class subclass} of {@link GitRepositoryURL} will be return.
     * @param <T> A subclass {@link java.lang.reflect.Type type} which inherited from {@link GitRepositoryURL}.
     *
     * @return An object of {@link GitRepositoryURL} subclass which defined return type from <code>returnType</code>.
     *
     * @throws UnknownGitRepositoryURLTypeException If parser can not find which subclass of {@link GitRepositoryURL}
     *         can be applied.
     * @throws ClassCastException If <code>returnType</code> is set to {@link GitRepositoryURL} or the actual return
     *         type is not mentioned on <code>returnType</code>.
     *
     * @see #parse(String)
     */
    @Nonnull
    static <T extends GitRepositoryURL> T parse(@Nonnull String gitURL, @Nonnull Class<T> returnType)
            throws UnknownGitRepositoryURLTypeException {

        if (!PARSER_APPLY_TYPE.contains(returnType) || returnType.equals(GitRepositoryURL.class))
            throw new ClassCastException("Declared return type parser must be GitRepositoryURL final subclasses.");

        final T guo = new GitRepositoryURLParseProcessor<T>().generateNewGitRepoURL(gitURL);

        if (!guo.getClass().equals(returnType))
            throw new ClassCastException(
                    "Returned Git repository type is '" + guo.getClass().getName() + "', "
                    + "not '" + returnType.getName() + "'."
            );

        return guo;
    }

    /**
     * Parse a {@link String} of Git repository URL addresses to {@link GitRepositoryURL}.
     *
     * @param gitURL Git repository URL address.
     *
     * @return An object of {@link GitRepositoryURL} with proper information.
     *
     * @throws UnknownGitRepositoryURLTypeException If
     */
    @Nonnull
    static GitRepositoryURL parse(@Nonnull String gitURL) throws UnknownGitRepositoryURLTypeException {
        return new GitRepositoryURLParseProcessor<GitRepositoryURL>().generateNewGitRepoURL(gitURL);
    }
}

/**
 * Parser processor for handling {@link GitRepositoryURL#parse(String, Class) parser}.
 *
 * @param <T> Return type of {@link GitRepositoryURL}.
 */
final class GitRepositoryURLParseProcessor<T extends GitRepositoryURL> {
    /**
     * An unmodifiable {@link Set} of {@link URI#getScheme() scheme} that is expecting has
     * {@link URI#getUserInfo() user information} or not.
     */
    private static final @UnmodifiableView Set<String> noUserInfoScheme = Set.of("https", "git"),
            userInfoScheme = Set.of("ssh");

    /**
     * Construct {@link GitRepositoryURL} with actual class.
     *
     * @param gitURL Git repository URL.
     *
     * @return Actual type of the {@link GitRepositoryURL} subclass.
     *
     * @throws UnknownGitRepositoryURLTypeException This exception thrown when any {@link Throwable} throw when parsing
     *         {@link String} to {@link GitRepositoryURL}.
     */
    @SuppressWarnings("unchecked")
    @Nonnull
    T generateNewGitRepoURL(@Nonnull String gitURL) throws UnknownGitRepositoryURLTypeException {
        try {
            try {
                // Determine is a legit URL or not.
                URI guri = new URI(gitURL);

                // This should be git control file
                assert !Pattern.matches("^.*/\\.git$", Objects.requireNonNull(guri.getPath()));

                final String hostWPort = guri.getHost() + (guri.getPort() == -1 ? "" : ":" + guri.getPort());
                final String pathWOSlashFirst = guri.getPath().substring(1);

                if (guri.getUserInfo() == null) {
                    assert noUserInfoScheme.contains(guri.getScheme());
                    return (T) switch (guri.getScheme()) {
                        case "https" -> new GitHttpsRepositoryURL(hostWPort, pathWOSlashFirst);
                        case "git" -> new GitGitRepositoryURL(hostWPort, pathWOSlashFirst);
                        // Default block is required, omit it will cause compile error.
                        default -> throw new AssertionError("Found unknown non-userinfo scheme: " + guri.getScheme());
                    };
                } else {
                    assert userInfoScheme.contains(guri.getScheme());
                    // noinspection SwitchStatementWithTooFewBranches
                    return (T) switch (guri.getScheme()) {
                        case "ssh" -> new GitSSHRepositoryURL(guri.getUserInfo(), hostWPort, pathWOSlashFirst);
                        // Default block is required, omit it will cause compile error.
                        default -> throw new AssertionError("Found unknown userinfo scheme: " + guri.getScheme());
                    };
                }
            } catch (URISyntaxException urie) {
                // Check is alternative SSH URL
                String[] aSSHUsrNHP = gitURL.split("@");

                assert aSSHUsrNHP.length == 2;

                final String user = aSSHUsrNHP[0];
                String[] hostNPath = aSSHUsrNHP[1].split(":");

                assert hostNPath.length == 2;
                return (T) new GitAltSSHRepositoryURL(user, hostNPath[0], hostNPath[1]);
            }
        } catch (Throwable t) {
            throw new UnknownGitRepositoryURLTypeException(gitURL, GitRepositoryURL.class, t);
        }
    }
}