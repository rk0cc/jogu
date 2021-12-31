package xyz.rk0cc.jogu;

import javax.annotation.Nonnull;

@SuppressWarnings("ClassCanBeRecord")
public final class GitGitRepositoryURL implements LegitURIFormat {
    private final String host, path;

    GitGitRepositoryURL(@Nonnull String host, @Nonnull String path) {
        this.host = host.toLowerCase();
        this.path = path;
    }

    @Nonnull
    @Override
    public String protocol() {
        return "git";
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
    public String toString() {
        return "GitGitRepositoryURL{" +
                "host='" + host + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
