package xyz.rk0cc.jogu;

import javax.annotation.Nonnull;

/**
 * This interface allows {@link GitRepositoryURL} parsing userinfo in the URL.
 *
 * @since 1.0.0
 */
public sealed interface GitUserinfoRepositoryURL extends GitRepositoryURL
        permits GitAltSSHRepositoryURL, GitSSHRepositoryURL {
    /**
     * Get an userinfo for accessing Git repository URL.
     *
     * @return Userinfo of the URL.
     */
    @Nonnull
    String userinfo();
}
