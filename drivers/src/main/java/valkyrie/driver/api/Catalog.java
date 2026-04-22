package valkyrie.driver.api;

import lombok.Getter;

/**
 * @author Luo Tiansheng
 * @since 2026/4/21
 */
@Getter
public class Catalog
{
        /**
         * GUI 展示标签
         */
        private final String label;

        /**
         * 真实名称
         */
        private final String name;

        private Catalog(String label, String name)
        {
                this.label = label;
                this.name = name;
        }

        public static Catalog of(String cat)
        {
                return of(cat, cat);
        }

        public static Catalog of(String lab, String cat)
        {
                return new Catalog(lab, cat);
        }
}
