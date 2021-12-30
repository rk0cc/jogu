package xyz.rk0cc.jogu;

import javax.annotation.Nonnull;

public sealed interface GitUserinfoRepositoryURL extends GitRepositoryURL
        permits GitAltSSHRepositoryURL, GitSSHRepositoryURL {
    @Nonnull
    String userinfo();
}
