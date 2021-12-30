package xyz.rk0cc.jogu;

import javax.annotation.Nonnull;
import java.net.URI;
import java.net.URISyntaxException;

public sealed interface LegitURIFormat extends GitRepositoryURL
        permits GitGitRepositoryURL, GitHttpsRepositoryURL, GitSSHRepositoryURL {
    @Nonnull
    String protocol();

    @Nonnull
    @Override
    default String assembleURL() {
        assert !protocol().contains("://");
        assert host().charAt(host().length() - 1) != '/';
        assert path().charAt(0) == '/';
        return protocol() + "://" + host() + path();
    }

    @Nonnull
    default URI toURI() {
        try {
            return new URI(assembleURL());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
