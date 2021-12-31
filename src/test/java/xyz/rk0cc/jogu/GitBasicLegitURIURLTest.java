package xyz.rk0cc.jogu;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

final class GitBasicLegitURIURLTest implements IdvGitRepositoryURLTestTask {
    private static LegitURIFormat https, git;

    @BeforeAll
    static void init() throws UnknownGitRepositoryURLTypeException {
        https = (LegitURIFormat) GitRepositoryURL.parse("https://example.com/a/b.git");
        git = (LegitURIFormat) GitRepositoryURL.parse("git://example.com/x/y.git");
    }

    @DisplayName("Return protocol uses for Git repository")
    @Test
    @Override
    public void testProtocol() {
        assertEquals(https.protocol(), "https");
        assertEquals(git.protocol(), "git");
    }

    @DisplayName("Test host, both https and git are the same")
    @Test
    @Override
    public void testHost() {
        assertEquals(https.host(), git.host());
    }

    @DisplayName("Return path to repository")
    @Test
    @Override
    public void testPath() {
        assertEquals(https.path(), "/a/b.git");
        assertEquals(git.path(), "/x/y.git");
    }

    @Disabled("Does not included on this test")
    @DisplayName("Return userinfo in Git repository URL")
    @Test
    @Override
    public void testUserInfo() {

    }
}
