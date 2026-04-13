package com.changhong.openvdb.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.net.URI;

/**
 * 查询脚本信息
 *
 * @author Luo Tiansheng
 * @since 2026/3/31
 */
public class ScriptFile extends File
{
        public ScriptFile(File file)
        {
                this(file.getAbsolutePath());
        }

        public ScriptFile(String pathname)
        {
                super(pathname);
        }

        public ScriptFile(String parent, String child)
        {
                super(parent, child);
        }

        public ScriptFile(File parent, String child)
        {
                super(parent, child);
        }

        public ScriptFile(URI uri)
        {
                super(uri);
        }
}
