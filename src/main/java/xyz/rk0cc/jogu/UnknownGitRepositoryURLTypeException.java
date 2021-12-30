package xyz.rk0cc.jogu;

import javax.annotation.Nonnull;

public final class UnknownGitRepositoryURLTypeException extends Exception {
    public final String gitURL;
    public final Class<?> parserClass;

    public UnknownGitRepositoryURLTypeException(
            @Nonnull String gitURL,
            @Nonnull Class<? extends GitRepositoryURL> parserClass,
            @Nonnull String message
    ) {
        super(message);
        this.gitURL = gitURL;
        this.parserClass = parserClass;
    }

    public UnknownGitRepositoryURLTypeException(
            @Nonnull String gitURL,
            @Nonnull Class<? extends GitRepositoryURL> parserClass
    ) {
        this(gitURL, parserClass, "Can not find suitable type for " + gitURL);
    }

    public UnknownGitRepositoryURLTypeException(
            @Nonnull String gitURL,
            @Nonnull Class<? extends GitRepositoryURL> parserClass,
            @Nonnull String message,
            @Nonnull Throwable throwable
    ) {
        super(message, throwable);
        this.gitURL = gitURL;
        this.parserClass = parserClass;
    }

    public UnknownGitRepositoryURLTypeException(
            @Nonnull String gitURL,
            @Nonnull Class<? extends GitRepositoryURL> parserClass,
            @Nonnull Throwable throwable
    ) {
        this(gitURL, parserClass, "Can not find suitable type for " + gitURL, throwable);
    }

    @Nonnull
    @Override
    public String toString() {
        return super.toString()
                + "\n\nParser located class: " + parserClass.getName()
                + "\nUnknown URL: " + gitURL;
    }
}
