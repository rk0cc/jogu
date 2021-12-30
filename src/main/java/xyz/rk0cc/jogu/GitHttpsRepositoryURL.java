package xyz.rk0cc.jogu;

import javax.annotation.Nonnull;

@SuppressWarnings("ClassCanBeRecord")
public final class GitHttpsRepositoryURL implements LegitURIFormat {
    private final String host, path;

    GitHttpsRepositoryURL(@Nonnull String host, @Nonnull String path) {
        this.host = host;
        this.path = path;
    }

    @Nonnull
    @Override
    public String protocol() {
        return "https";
    }

    @Nonnull
    @Override
    public String host() {
        return host;
    }

    @Nonnull
    @Override
    public String path() {
        return path;
    }

    @Nonnull
    @Override
    public String toString() {
        return "GitHttpsRepositoryURL{" +
                "host='" + host + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
