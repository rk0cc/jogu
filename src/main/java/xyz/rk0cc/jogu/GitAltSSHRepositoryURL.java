package xyz.rk0cc.jogu;

import javax.annotation.Nonnull;

/**
 * An alternative form of {@link GitSSHRepositoryURL} which omit protocol and completely incompatible address with
 * {@link java.net.URI}.
 *
 * @since 1.0.0
 */
@SuppressWarnings("ClassCanBeRecord")
public final class GitAltSSHRepositoryURL implements GitUserinfoRepositoryURL {
    /**
     * Basic information of alternative SSH URL.
     */
    private final String userinfo, host, path;

    /**
     * Construct new Git repository URL which using alternative SSH URL format.
     *
     * @param userinfo User information.
     * @param host Repository host.
     * @param path Path to repository.
     */
    GitAltSSHRepositoryURL(@Nonnull String userinfo, @Nonnull String host, @Nonnull String path) {
        assert !host.contains(":");
        this.userinfo = userinfo;
        this.host = host.toLowerCase();
        this.path = path;
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
        return ":" + path;
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
    public String assembleURL() {
        return userinfo + "@" + host + path;
    }

    /**
     * Convert alternative SSH form to {@link GitSSHRepositoryURL} which compatible with {@link java.net.URI}.
     *
     * @return New {@link GitSSHRepositoryURL} with same context.
     */
    @Nonnull
    public GitSSHRepositoryURL convertToTraditional() {
        return new GitSSHRepositoryURL(userinfo, host, path);
    }

    /**
     * {@inheritDoc}
     */
    @Nonnull
    @Override
    public String toString() {
        return "GitAltSSHRepositoryURL{" +
                "userinfo='" + userinfo + '\'' +
                ", host='" + host + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
