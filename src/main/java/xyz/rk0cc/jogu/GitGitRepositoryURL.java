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
    private final String host, path;

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
