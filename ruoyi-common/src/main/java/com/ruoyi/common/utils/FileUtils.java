package com.ruoyi.common.utils;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Author:hanguanglei
 * @Description:
 * @Date: Created in 2020-03-26 15:15
 * @Modified By:
 */

public class FileUtils {

    /**
     * File 转 MultipartFile
     *
     * @param file
     * @throws Exception
     */
//    public static void fileToMultipartFile(File file) throws Exception {
//        FileInputStream fileInput = new FileInputStream(file);
//        MultipartFile toMultipartFile = new MockMultipartFile("file", file.getName(), "text/plain",
//                IOUtils.toByteArray(fileInput));
//        toMultipartFile.getInputStream();
//    }


    public static File multipartFileToFile(@RequestParam MultipartFile file) throws Exception {
        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();

            return toFile;
        }
        return toFile;
    }



    /**
     * InputStream 转 File
     *
     * @param ins
     * @param file
     */
    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
