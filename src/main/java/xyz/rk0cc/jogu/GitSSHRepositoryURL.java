package xyz.rk0cc.jogu;

import javax.annotation.Nonnull;

/**
 * An object of Git repository URL address which using <code>ssh://</code> with userinfo to crawl files.
 *
 * @since 1.0.0
 */
@SuppressWarnings("ClassCanBeRecord")
public final class GitSSHRepositoryURL implements GitUserinfoRepositoryURL, LegitURIFormat {
    private final String userinfo, host, path;

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
     */
    @Nonnull
    public GitAltSSHRepositoryURL convertToAlternative() {
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
