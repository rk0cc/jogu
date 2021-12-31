package xyz.rk0cc.jogu;

import javax.annotation.Nonnull;

/**
 * An object of Git repository URL address which using <code>https://</code> to crawl files.
 *
 * @since 1.0.0
 *
 * @see GitGitRepositoryURL
 */
@SuppressWarnings("ClassCanBeRecord")
public final class GitHttpsRepositoryURL implements LegitURIFormat {
    private final String host, path;

    GitHttpsRepositoryURL(@Nonnull String host, @Nonnull String path) {
        this.host = host.toLowerCase();
        this.path = path;
    }

    /**
     * {@inheritDoc}
     */
    @Nonnull
    @Override
    public String protocol() {
        return "https";
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
        return "GitHttpsRepositoryURL{" +
                "host='" + host + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
