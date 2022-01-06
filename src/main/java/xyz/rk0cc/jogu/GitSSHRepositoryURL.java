package xyz.rk0cc.jogu;

import javax.annotation.Nonnull;
import java.net.URISyntaxException;

/**
 * An object of Git repository URL address which using <code>ssh://</code> with userinfo to crawl files.
 *
 * @since 1.0.0
 */
@SuppressWarnings("ClassCanBeRecord")
public final class GitSSHRepositoryURL implements GitUserinfoRepositoryURL, LegitURIFormat {
    /**
     * Basic information of legit SSH URL.
     */
    private final String userinfo, host, path;

    /**
     * Construct new Git repository URL which using legit SSH URL format.
     *
     * @param userinfo User information.
     * @param host Repository host.
     * @param path Path to repository.
     */
    GitSSHRepositoryURL(@Nonnull String userinfo, @Nonnull String host, @Nonnull String path) {
        this.userinfo = userinfo;
        this.host = host.toLowerCase();
        this.path = path;
    }

    /**
     * {@inheritDoc}
     */
    @Nonnull
    @Override
    public String protocol() {
        return "ssh";
    }

    /**
     * {@inheritDoc}
     */
    @Nonnull
    @Override
    public String userinfo() {
        return userinfo;
    }

    /**
     * {@inheritDoc}
     */
    @Nonnull
    @Override
    public String host() {
        return host;
    }

    /**
     * {@inheritDoc}
     */
    @Nonnull
    @Override
    public String path() {
        return "/" + path;
    }

    /**
     * {@inheritDoc}
     */
    @Nonnull
    @Override
    public String assembleURL() {
        return protocol() + "://" + userinfo() + "@" + host() + path();
    }

    /**
     * Convert legit SSH URL to {@link GitAltSSHRepositoryURL} which incompatible with {@link java.net.URI}.
     *
     * @return New object of {@link GitAltSSHRepositoryURL} with exact same data.
     *
     * @throws URISyntaxException {@link GitAltSSHRepositoryURL} do not accept custom port provided in the URL syntax.
     *                            As a result, this exception throw if indicated with port is located.
     */
    @Nonnull
    public GitAltSSHRepositoryURL convertToAlternative() throws URISyntaxException {
        if (host.split(":").length != 1)
            throw new URISyntaxException(assembleURL(), "Alternative SSH URL is not accepted specific port number");
        return new GitAltSSHRepositoryURL(userinfo, host, path);
    }

    /**
     * {@inheritDoc}
     */
    @Nonnull
    @Override
    public String toString() {
        return "GitSSHRepositoryURL{" +
                "userinfo='" + userinfo + '\'' +
                ", host='" + host + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
