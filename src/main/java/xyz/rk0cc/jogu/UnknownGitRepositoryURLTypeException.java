package xyz.rk0cc.jogu;

import javax.annotation.Nonnull;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * An exception throws from {@link GitRepositoryURL} parsers that has unexpected exception thrown due to invalid type
 * of Git repository URL found.
 *
 * @since 1.0.0
 */
public final class UnknownGitRepositoryURLTypeException extends Exception {
    /**
     * A Git repository URL that can not to be defined in {@link GitRepositoryURL}.
     */
    public final String gitURL;
    /**
     * Parser of {@link GitRepositoryURL} and it must be inherited already.
     */
    public final Class<? extends GitRepositoryURL> parserClass;

    /**
     * Create exception when found invalid Git repository URL type.
     *
     * @param gitURL A {@link String} of Git repository URL.
     * @param parserClass A {@link Class} under {@link GitRepositoryURL} with static method <code>parse(String)</code>
     *                    implemented publicly.
     * @param message Custom message when throwing exception.
     */
    public UnknownGitRepositoryURLTypeException(
            @Nonnull String gitURL,
            @Nonnull Class<? extends GitRepositoryURL> parserClass,
            @Nonnull String message
    ) {
        super(message);
        this.gitURL = gitURL;
        this.parserClass = parserClass;
    }

    /**
     * Create exception when found invalid Git repository URL type.
     *
     * @param gitURL A {@link String} of Git repository URL.
     * @param parserClass A {@link Class} under {@link GitRepositoryURL} with static method <code>parse(String)</code>
     *                    implemented publicly.
     */
    public UnknownGitRepositoryURLTypeException(
            @Nonnull String gitURL,
            @Nonnull Class<? extends GitRepositoryURL> parserClass
    ) {
        this(gitURL, parserClass, "Can not find suitable type for " + gitURL);
    }

    /**
     * Create exception when found invalid Git repository URL type.
     *
     * @param gitURL A {@link String} of Git repository URL.
     * @param parserClass A {@link Class} under {@link GitRepositoryURL} with static method <code>parse(String)</code>
     *                    implemented publicly.
     * @param message Custom message when throwing exception.
     * @param throwable A {@link Throwable} causing this exception thrown.
     */
    public UnknownGitRepositoryURLTypeException(
            @Nonnull String gitURL,
            @Nonnull Class<? extends GitRepositoryURL> parserClass,
            @Nonnull String message,
            @Nonnull Throwable throwable
    ) {
        super(message, throwable);
        assert containsParser(parserClass);
        this.gitURL = gitURL;
        this.parserClass = parserClass;
    }

    /**
     * Create exception when found invalid Git repository URL type.
     *
     * @param gitURL A {@link String} of Git repository URL.
     * @param parserClass A {@link Class} under {@link GitRepositoryURL} with static method <code>parse(String)</code>
     *                    implemented publicly.
     * @param throwable A {@link Throwable} causing this exception thrown.
     */
    public UnknownGitRepositoryURLTypeException(
            @Nonnull String gitURL,
            @Nonnull Class<? extends GitRepositoryURL> parserClass,
            @Nonnull Throwable throwable
    ) {
        this(gitURL, parserClass, "Can not find suitable type for " + gitURL, throwable);
    }

    /**
     * {@inheritDoc}
     */
    @Nonnull
    @Override
    public String toString() {
        return super.toString()
                + "\n\nParser located class: " + parserClass.getName()
                + "\nUnknown URL: " + gitURL;
    }

    private static boolean containsParser(@Nonnull Class<? extends GitRepositoryURL> parserClass) {
        try {
            final Method parserMethod = parserClass.getMethod("parse", String.class);
            final int parserModifier = parserMethod.getModifiers();
            return parserMethod.getReturnType().isAssignableFrom(GitRepositoryURL.class)
                    && Modifier.isPublic(parserModifier)
                    && Modifier.isStatic(parserModifier)
                    && Arrays.asList(parserMethod.getExceptionTypes())
                        .contains(UnknownGitRepositoryURLTypeException.class);
        } catch (NoSuchMethodException nsme) {
            return false;
        }
    }
}
