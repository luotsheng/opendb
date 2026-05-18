package valkyrie.driver.api;

/**
 * @author Luo Tiansheng
 * @since 2026/5/18
 */
public interface SQLExecuteCallback
{
        default void execute(String sql) { /* IGNORE */ }

        default void executeQuery(String sql, boolean skip) { /* IGNORE */ }

        default void executeUpdate(String sql) { /* IGNORE */ }

        default void row(int value) { /* IGNORE */ }

        default void cost(long time) { /* IGNORE */ }
}
