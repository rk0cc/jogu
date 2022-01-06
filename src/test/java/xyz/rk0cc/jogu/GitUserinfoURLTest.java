package xyz.rk0cc.jogu;

import org.junit.jupiter.api.*;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

final class GitUserinfoURLTest implements IdvGitRepositoryURLTestTask {
    private static GitUserinfoRepositoryURL tradSSH, altSSH;

    @BeforeAll
    static void init() throws UnknownGitRepositoryURLTypeException {
        tradSSH = (GitUserinfoRepositoryURL) GitRepositoryURL.parse("ssh://alice@example.com/foo.git");
        altSSH = (GitUserinfoRepositoryURL) GitRepositoryURL.parse("bob@example.com:foo.git");
    }

    @Disabled("It possibility does not came with protocol for some objects")
    @DisplayName("Test which protocol is used")
    @Test
    @Override
    public void testProtocol() {

    }

    @DisplayName("Test which host is used")
    @Test
    @Override
    public void testHost() {
        assertEquals(tradSSH.host(), altSSH.host());
    }

    @DisplayName("Test path to Git repository")
    @Test
    @Override
    public void testPath() {
        assertEquals(tradSSH.path(), "/foo.git");
        assertEquals(altSSH.path(), ":foo.git");
    }

    @DisplayName("Test which userinfo to access Git repository hosting server")
    @Test
    @Override
    public void testUserInfo() {
        assertEquals(tradSSH.userinfo(), "alice");
        assertEquals(altSSH.userinfo(), "bob");
    }

    @DisplayName("Forbid parsing traditional SSH with specific port number provided")
    @Test
    public void testForbidParseCustomPortNo() {
        assertThrows(
                URISyntaxException.class,
                () -> GitRepositoryURL.parse("ssh://alice@example.com:222/foo.git", GitSSHRepositoryURL.class)
                        .convertToAlternative()
        );
        assertDoesNotThrow(
                () -> GitRepositoryURL.parse("ssh://alice@example.com/foo.git", GitSSHRepositoryURL.class)
                        .convertToAlternative()
        );
    }
}
