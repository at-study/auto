package redmine.managers;

/**
 * Контекст, в котором хранятся сущности, необходимые в процессе выполнения теста
 */
public class Context {

    private static Stash stash;

    public static Stash getStash() {
        if (stash == null) {
            stash = new Stash();
        }
        return stash;
    }

    public static void clearStash() {
        if (stash != null) {
            stash = null;
        }
    }
}
