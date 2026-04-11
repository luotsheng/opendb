package com.changhong.driver.api;

import java.util.List;

/**
 * SQL 执行单元
 *
 * 表示一次用户提交的 SQL 内容，支持包含多条语句。
 * 每条语句可能属于不同类型（SELECT / DDL / DML / DCL / TCL / 扩展语句）。
 *
 * 执行特性：
 * - SQL 内容可能包含多条语句（按分隔符拆分后执行）
 * - 执行顺序严格按照语句顺序
 * - 每条语句独立产生执行结果
 *
 * 使用场景：
 * - 编辑器执行选中 SQL
 * - 脚本批量执行
 * - 控制台命令执行
 *
 * 该接口由各自驱动独立实现，其中 SQL 方言解析等内容，由子类自由实现。
 *
 * @author Luo Tiansheng
 * @since 2026/4/11
 */
public interface SQL
{
        /**
         * SQL 分割后的语句列表
         *
         * 用于批量执行场景，每条语句独立执行
         *
         * @return SQL 语句列表
         */
        List<String> getStatements();
}
