package com.changhong.openvdb.core.repository;

import com.changhong.openvdb.core.Users;
import com.changhong.openvdb.core.exception.CoreException;
import com.changhong.openvdb.core.model.ScriptFile;

import java.io.File;
import java.io.FileWriter;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import static com.changhong.utils.string.StaticLibrary.strnempty;

/**
 * 脚本数据
 *
 * @author Luo Tiansheng
 * @since 2026/3/25
 */
public class ScriptFileRepository
{
        public static ScriptFile save(String conn,
                                String catalog,
                                String schema,
                                String name,
                                String content)
        {
                return save(new File(getPath(conn, catalog, schema, name) + ".sql"), content);
        }

        @SuppressWarnings("ResultOfMethodCallIgnored")
        public static ScriptFile save(File scriptFile, String content)
        {
                try {
                        if (!scriptFile.exists()) {
                                scriptFile.getParentFile().mkdirs();
                                scriptFile.createNewFile();
                        }

                        try (FileWriter fw = new FileWriter(scriptFile)) {
                                fw.write(content);
                        }
                } catch (Exception e) {
                        throw new CoreException(e);
                }

                return new ScriptFile(scriptFile);
        }

        @SuppressWarnings("ResultOfMethodCallIgnored")
        public static ScriptFile rename(ScriptFile src, String name)
        {
                var newFile = new File(src.getParentFile(), name);
                src.renameTo(newFile);
                return new ScriptFile(newFile);
        }

        public static List<ScriptFile> loadScriptFiles(String conn,
                                                       String catalog,
                                                       String schema)
        {
                List<ScriptFile> models = new ArrayList<>();

                File sqlDir = new File(getPath(conn, catalog, schema, null));

                File[] files = sqlDir.listFiles();
                if (files == null)
                        return models;

                for (File file : files)
                        models.add(new ScriptFile(file));

                Collator collator = Collator.getInstance(Locale.CHINA);
                models.sort(Comparator.comparing(ScriptFile::getName, collator));

                return models;
        }

        @SuppressWarnings("SameParameterValue")
        private static String getPath(String conn,
                                      String catalog,
                                      String schema,
                                      String name)
        {
                var baseDir = Users.connectionDir + "/" + conn + "/" + catalog;

                if (strnempty(schema))
                        baseDir += "/" + schema;

                if (strnempty(name))
                        baseDir += "/" + name;

                return baseDir;
        }
}
