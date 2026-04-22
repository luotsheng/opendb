package valkyrie.driver.api;

import lombok.Getter;

import static valkyrie.utils.string.StaticLibrary.lowercase;

/**
 * @author Luo Tiansheng
 * @since 2026/4/11
 */
@Getter
public enum DbType
{
        mysql("MySQL", true),
        dm("达梦数据库", true),
        redis("Redis", false),
        ;

        private final String alias;
        private final boolean supportedProductMetaData;

        DbType(String alias, boolean supportedProductMetaData)
        {
                this.alias = alias;
                this.supportedProductMetaData = supportedProductMetaData;
        }

        public static DbType of(String type)
        {
                return valueOf(lowercase(type));
        }
}
