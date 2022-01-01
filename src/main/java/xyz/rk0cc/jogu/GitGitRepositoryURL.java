package xyz.rk0cc.jogu;

import javax.annotation.Nonnull;

/**
 * An object of Git repository URL address which using <code>git://</code> to crawl files.
 *
 * @since 1.0.0
 *
 * @see GitHttpsRepositoryURL
 */
@SuppressWarnings("ClassCanBeRecord")
public final class GitGitRepositoryURL implements LegitURIFormat {
    /**
     * Basic information of Git protocol URL.
     */
    private final String host, path;

    /**
     * Construct new Git repository URL which using Git protocol (<code>git://</code>).
     *
     * @param host Repository host.
     * @param path Path to repository.
     */
    GitGitRepositoryURL(@Nonnull String host, @Nonnull String path) {
        this.host = host.toLowerCase();
        this.path = path;
    }

    /**
     * {@inheritDoc}
     */
    @Nonnull
    @Override
    public String protocol() {
        return "git";
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
    public String toString() {
        return "GitGitRepositoryURL{" +
                "host='" + host + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
