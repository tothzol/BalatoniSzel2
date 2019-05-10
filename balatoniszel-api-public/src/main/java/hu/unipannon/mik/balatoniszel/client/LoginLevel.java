package hu.unipannon.mik.balatoniszel.client;

public enum LoginLevel {
    PUBLIC(0),
    GUEST(1),
    ADMIN(2);

    private final int level;

    LoginLevel(int level) {
        this.level = level;
    }

    public boolean atLeast(LoginLevel level) {
        return this.level >= level.level;
    }
}
