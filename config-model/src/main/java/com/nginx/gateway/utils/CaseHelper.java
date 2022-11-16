package com.nginx.gateway.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * <p>CaseHelper</p>
 *
 * @author Jiuping Yi
 */
@Slf4j
public class CaseHelper {


    public static List<File> getCaseDataFiles(String packageName) {

        List<File> cases = new ArrayList<>();
        try {
            String packageDirName = packageName.replace('.', '/');
            Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                String protocol = url.getProtocol();
                if ("file".equals(protocol)) {
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    cases.addAll(findCaseDataFilesByDir(packageName, filePath));
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return cases;
    }

    public static List<File> findCaseDataFilesByDir(String packageName, String packagePath) {
        File dir = new File(packagePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return new ArrayList<>(0);
        }

        List<File> cases = new ArrayList<>();

        File[] dirs = dir.listFiles();
        if (dirs == null) {
            return cases;
        }

        for (File file : dirs) {
            if (file.isDirectory()) {
                cases.addAll(findCaseDataFilesByDir(packageName + "." + file.getName(), file.getAbsolutePath()));
            } else if (file.getName().endsWith(".json")) {
                cases.add(file);
            }
        }

        return cases;
    }
}
