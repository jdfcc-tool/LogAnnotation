package com.jdfcc.logannotation.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Jdfcc
 * @Description FileUtil
 * @DateTime 2023/6/26 14:39
 */
public class FileUtil {

    /**
     * 将文件以固定格式写入
     * @param path 文件写入路径,需以/结尾
     * @param filename 文件名
     * @param value 写入的内容
     * @param suffix 文件扩展名
     */
    public static void writeFile(String path, String filename, String value,String suffix) {

        String logFile = path + filename+"."+suffix;
        try {
            File file = new File(logFile);
            File parentDir = file.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(value + "\n");
            fileWriter.close();
            System.out.println("Content appended to file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

}
