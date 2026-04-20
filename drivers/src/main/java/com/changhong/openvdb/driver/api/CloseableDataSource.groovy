package com.changhong.openvdb.driver.api

import javax.sql.DataSource

/**
 * @author Luo Tiansheng
 * @since 2026/4/20
 */
interface CloseableDataSource extends DataSource, AutoCloseable {

}