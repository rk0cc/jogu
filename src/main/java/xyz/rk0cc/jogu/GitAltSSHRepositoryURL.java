package xyz.rk0cc.jogu;

import javax.annotation.Nonnull;

@SuppressWarnings("ClassCanBeRecord")
public final class GitAltSSHRepositoryURL implements GitUserinfoRepositoryURL {
    private final String userinfo, host, path;

    GitAltSSHRepositoryURL(@Nonnull String userinfo, @Nonnull String host, @Nonnull String path) {
        this.userinfo = userinfo;
        this.host = host.toLowerCase();
        this.path = path;
    }

    @Nonnull
    @Override
    public String host() {
        return host;
    }

    @Nonnull
    @Override
    public String path() {
        return ":" + path;
    }

    @Nonnull
    @Override
    public String userinfo() {
        return userinfo;
    }

    @Nonnull
    @Override
    public String assembleURL() {
        return userinfo + "@" + host + path;
    }

    @Nonnull
    public GitSSHRepositoryURL convertToTraditional() {
        return new GitSSHRepositoryURL(userinfo, host, path);
    }

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
