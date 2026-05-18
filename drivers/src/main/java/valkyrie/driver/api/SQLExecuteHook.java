package valkyrie.driver.api;

/**
 * SQL 执行生命周期钩子接口。
 * <p>
 * 用于在 SQL 语句执行的前后及异常发生时插入自定义逻辑，如日志记录、性能监控、
 * 慢查询统计或执行链路追踪。
 * <p>
 * <b>执行顺序：</b>
 * <ol>
 *   <li>调用 {@link #beforeExecute(String)}</li>
 *   <li>执行 SQL 语句</li>
 *   <li>若执行成功，调用 {@link #afterExecute(String, long)}</li>
 *   <li>若执行失败，调用 {@link #onError(String, Throwable)}</li>
 * </ol>
 * <p>
 * <b>实现注意事项：</b>
 * <ul>
 *   <li>钩子方法应避免抛出异常，以免干扰主业务流程（建议内部捕获并记录）</li>
 *   <li>钩子实现应为无状态或线程安全，可能被多个线程并发调用</li>
 * </ul>
 *
 * @author Luo Tiansheng
 * @since 2026/5/18
 */
public interface SQLExecuteHook {

        /**
         * SQL 语句执行前回调。
         *
         * @param sql 待执行的 SQL 语句（可能为 {@code null} 或空白）
         */
        default void beforeExecute(String sql) { /* DO NOTHING... */ };

        /**
         * SQL 语句执行成功后的回调。
         *
         * @param sql    已执行的 SQL 语句
         * @param isSkip 是否跳过 SQL
         * @param cost   执行耗时（单位：毫秒），始终为非负数
         */
        default void afterExecute(String sql, boolean isSkip, long cost) { /* DO NOTHING... */ };

        /**
         * SQL 语句执行异常时的回调。
         *
         * @param sql 发生异常的 SQL 语句
         * @param e   捕获的异常对象（不能为 {@code null}）
         */
        default void onError(String sql, Throwable e) { /* DO NOTHING... */ }
}
