package xyz.rk0cc.jogu;

import javax.annotation.Nonnull;

@SuppressWarnings("ClassCanBeRecord")
public final class GitSSHRepositoryURL implements GitUserinfoRepositoryURL, LegitURIFormat {
    private final String userinfo, host, path;

    GitSSHRepositoryURL(@Nonnull String userinfo, @Nonnull String host, @Nonnull String path) {
        this.userinfo = userinfo;
        this.host = host.toLowerCase();
        this.path = path;
    }

    @Nonnull
    @Override
    public String protocol() {
        return "ssh";
    }

    @Nonnull
    @Override
    public String userinfo() {
        return userinfo;
    }

    @Nonnull
    @Override
    public String host() {
        return host;
    }

    @Nonnull
    @Override
    public String path() {
        return "/" + path;
    }

    @Nonnull
    @Override
    public String assembleURL() {
        return protocol() + "://" + userinfo() + "@" + host() + path();
    }

    @Nonnull
    public GitAltSSHRepositoryURL convertToAlternative() {
        return new GitAltSSHRepositoryURL(userinfo, host, path);
    }

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
