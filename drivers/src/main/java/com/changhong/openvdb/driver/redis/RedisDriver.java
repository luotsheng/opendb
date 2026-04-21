package com.changhong.openvdb.driver.redis;

import com.changhong.openvdb.driver.api.*;
import com.changhong.openvdb.driver.api.sql.SQL;
import com.changhong.utils.collection.Lists;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.commands.ProtocolCommand;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static com.changhong.utils.TypeConverter.atos;
import static com.changhong.utils.collection.Lists.beg;
import static com.changhong.utils.string.StaticLibrary.strip;

/**
 * Redis 驱动层实现
 *
 * @author Luo Tiansheng
 * @since 2026/4/20
 */
public class RedisDriver extends Driver {

        private final Jedis jedis;

        /**
         * 构造一个新的驱动实例。
         *
         * @param dataSource 数据源，用于获取数据库连接（不能为 {@code null}）
         * @throws NullPointerException 如果 {@code dataSource} 为 {@code null}
         */
        public RedisDriver(DataSource dataSource) {
                super(dataSource);
                this.jedis = ((RedisDataSource) dataSource).getJedis();
        }

        @Override
        public List<String> getCatalogs() {
                List<String> catalogs = Lists.newArrayList();
                int count = Integer.parseInt(jedis.configGet("databases").get("databases"));

                for (int i = 0; i < count; i++) {
                        jedis.select(i);
                        if (jedis.dbSize() > 0)
                                catalogs.add(String.valueOf(i));
                }

                return catalogs;
        }

        @Override
        public DataGrid execute(long jobId, Session session, SQL sql)
        {
                jedis.select(Integer.parseInt(session.catalog()));

                String[] parts = strip(sql.getRaw()).split("\\s+");
                ProtocolCommand cmd = () -> parts[0].getBytes(StandardCharsets.UTF_8);
                byte[][] args = new byte[parts.length - 1][];

                for (int i = 1; i < parts.length; i++)
                        args[i - 1] = parts[i].getBytes(StandardCharsets.UTF_8);

                // GET serviceCalendar|2026-04
                Object result = jedis.sendCommand(cmd, args);

                return switch (result) {
                        case null -> DataGrid.ofValue(session, null);

                        case byte[] b -> DataGrid.ofValue(session, atos(b));

                        case Long l -> DataGrid.ofValue(session, atos(l));

                        case List<?> list -> {
                                if (list.isEmpty())
                                        yield DataGrid.ofList(session, List.of());

                                Object second = list.get(1);

                                if (second instanceof List<?> byteList) {
                                        List<String> values = new ArrayList<>(list.size());
                                        for (Object v : byteList)
                                                values.add(atos((byte[]) v));
                                        yield DataGrid.ofList(session, values);
                                }

                                yield DataGrid.ofList(session, List.of());
                        }

                        default -> DataGrid.ofValue(session, result.toString());
                };

        }

        @Override
        public DbType getType()
        {
                return DbType.redis;
        }

        @Override
        protected Dialect createDialect()
        {
                return null;
        }

        @Override
        public String showCreateTable(Session session, String table)
        {
                throw new UnsupportedOperationException();
        }

        @Override
        public List<Table> getTables(Session session)
        {
                return Lists.newArrayList();
        }

        @Override
        public List<Index> getIndexes(Session session, String table)
        {
                throw new UnsupportedOperationException();
        }

        @Override
        public Set<String> getIndexTypes()
        {
                throw new UnsupportedOperationException();
        }

        @Override
        public void dropTable(Session session, String table)
        {
                throw new UnsupportedOperationException();
        }

        @Override
        public void dropColumns(Session session, String table, Collection<Column> columns)
        {
                throw new UnsupportedOperationException();
        }

        @Override
        public void dropIndexKeys(Session session, String table, Collection<Index> selectionItems)
        {
                throw new UnsupportedOperationException();
        }

        @Override
        public void dropPrimaryKey(Session session, String table)
        {
                throw new UnsupportedOperationException();
        }

        @Override
        public void addPrimaryKey(Session session, String table, Collection<Column> primaryKeys)
        {
                throw new UnsupportedOperationException();
        }

        @Override
        public void alterIndexKeys(Session session, String table, Collection<Index> indexes)
        {
                throw new UnsupportedOperationException();
        }

        @Override
        public void alterChange(Session session, String table, Collection<Column> columns)
        {
                throw new UnsupportedOperationException();
        }

        @Override
        public void alterVisible(Session session, String table, Collection<Index> indexes)
        {
                throw new UnsupportedOperationException();
        }

}
