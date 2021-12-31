package xyz.rk0cc.jogu;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
final class GitRepositoryURLTest {
    @DisplayName("Test Git repository URL parser")
    @Order(1)
    @Test
    void testParse() {
        assertDoesNotThrow(() -> GitRepositoryURL.parse("git://example.com/foo.git"));
        assertDoesNotThrow(() -> GitRepositoryURL.parse("https://example.com/foo.git"));
        assertDoesNotThrow(() -> GitRepositoryURL.parse("ssh://alice@example.com/foo.git"));
        assertDoesNotThrow(() -> GitRepositoryURL.parse("alice@example.com:foo.git"));
        assertThrows(
                UnknownGitRepositoryURLTypeException.class,
                () -> GitRepositoryURL.parse("ssh://example.com/foo.git")
        );
        assertThrows(
                UnknownGitRepositoryURLTypeException.class,
                () -> GitRepositoryURL.parse("files:///path/to/foo.git")
        );
        assertThrows(
                UnknownGitRepositoryURLTypeException.class,
                () -> GitRepositoryURL.parse("ftp://example.com/foo.git")
        );
        assertThrows(
                UnknownGitRepositoryURLTypeException.class,
                () -> GitRepositoryURL.parse("https://bob@example.com/foo.git")
        );
        assertThrows(
                UnknownGitRepositoryURLTypeException.class,
                () -> GitRepositoryURL.parse("git://eve@example.com/foo.git")
        );
    }

    @DisplayName("Check return type of Git repository URL")
    @Order(2)
    @Test
    void testReturnType() throws UnknownGitRepositoryURLTypeException {
        assertInstanceOf(GitGitRepositoryURL.class, GitRepositoryURL.parse("git://example.com/foo.git"));
        assertInstanceOf(GitHttpsRepositoryURL.class, GitRepositoryURL.parse("https://example.com/foo.git"));
        assertInstanceOf(GitSSHRepositoryURL.class, GitRepositoryURL.parse("ssh://alice@example.com/foo.git"));
        assertInstanceOf(GitAltSSHRepositoryURL.class, GitRepositoryURL.parse("alice@example.com:foo.git"));
    }

    @DisplayName("Check Git repository URL parser with declared type")
    @Order(3)
    @Test
    void testTypeDeclaredParse() {
        assertDoesNotThrow(
                () -> GitRepositoryURL.parse("git://example.com/foo.git", GitGitRepositoryURL.class)
        );
        assertDoesNotThrow(
                () -> GitRepositoryURL.parse("https://example.com/foo.git", GitHttpsRepositoryURL.class)
        );
        assertDoesNotThrow(
                () -> GitRepositoryURL.parse("ssh://alice@example.com/foo.git", GitSSHRepositoryURL.class)
        );
        assertDoesNotThrow(
                () -> GitRepositoryURL.parse("alice@example.com:foo.git", GitAltSSHRepositoryURL.class)
        );
        assertThrows(
                ClassCastException.class,
                () -> GitRepositoryURL.parse("git://foo.com/pew/l.git", GitRepositoryURL.class)
        );
        assertThrows(
                ClassCastException.class,
                () -> GitRepositoryURL.parse("git://foo.com/pew/l.git", GitRepositoryURL.class)
        );
        assertThrows(
                ClassCastException.class,
                () -> GitRepositoryURL.parse("https://gitsample.com/idk.git", GitGitRepositoryURL.class)
        );
        assertThrows(
                ClassCastException.class,
                () -> GitRepositoryURL.parse("dawn@example.com:snake/pip.git", GitSSHRepositoryURL.class)
        );
        assertThrows(
                ClassCastException.class,
                () -> GitRepositoryURL.parse("ssh://gloria@example.com/ram/sea", GitAltSSHRepositoryURL.class)
        );
    }

    @DisplayName("Check .git is included in the path")
    @Order(4)
    @Test
    void testDotGitCondition() throws UnknownGitRepositoryURLTypeException {
        assertTrue(GitRepositoryURL.parse("git://example.com/foo.git").isDotGitEndedPath());
        assertTrue(GitRepositoryURL.parse("git://example.com/foo.oi.git").isDotGitEndedPath());
        assertThrows(
                UnknownGitRepositoryURLTypeException.class,
                () -> GitRepositoryURL.parse("git://example.com/foo/.git").isDotGitEndedPath()
        );
        assertFalse(GitRepositoryURL.parse("git://example.com/foo.git.sike").isDotGitEndedPath());
        assertFalse(GitRepositoryURL.parse("git://example.com/foo.html").isDotGitEndedPath());
        assertFalse(GitRepositoryURL.parse("git://example.com/foo.jar").isDotGitEndedPath());
    }
}
