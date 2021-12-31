package xyz.rk0cc.jogu;

import javax.annotation.Nonnull;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * This interface of {@link GitRepositoryURL} is compatible with {@link URI}.
 *
 * @apiNote Despite this library refer to URL since it actually is, not all protocols can be supported in
 *          {@link java.net.URL} like <code>{@link GitGitRepositoryURL git://}</code>.
 *
 * @since 1.0.0
 */
public sealed interface LegitURIFormat extends GitRepositoryURL
        permits GitGitRepositoryURL, GitHttpsRepositoryURL, GitSSHRepositoryURL {
    /**
     * Indicate which protocol is going to use on this repository.
     *
     * @return Protocol for getting Git repository
     *
     * @see <a href="https://git-scm.com/book/en/v2/Git-on-the-Server-The-Protocols">Official documentation of the Git protocol</a>
     */
    @Nonnull
    String protocol();

    /**
     * Combine {@link #protocol()}, {@link #host()}, and {@link #path()} to a {@link String} of legit URL which also
     * uses as {@link URI#URI(String)} in {@link #toURI()}.
     *
     * @return A completed and legit {@link String} of Git repository URL.
     */
    @Nonnull
    @Override
    default String assembleURL() {
        assert !protocol().contains("://");
        assert host().charAt(host().length() - 1) != '/';
        assert path().charAt(0) == '/';
        return protocol() + "://" + host() + path();
    }

    /**
     * Convert to {@link URI} from {@link #assembleURL()}.
     *
     * @return A {@link URI} object of {@link #assembleURL()}.
     */
    @Nonnull
    default URI toURI() {
        try {
            return new URI(assembleURL());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
