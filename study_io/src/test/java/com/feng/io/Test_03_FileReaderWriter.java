package com.feng.io;



import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 使用 FileReader FileWriter 也可以 实现 文本文件 的复制。
 * 对于非文本文件（视频文件、音频文件、图片），只能使用字节流！！！
 *
 * 可对汉字、字母进行操作
 */
/**
 * @ClassName Test_03_FileReaderWriter
 * @Description TODO
 * @Author fengfanli
 * @Date 2021/1/29 16:49
 * @Version 1.0
 */
public class Test_03_FileReaderWriter {

    /**
     *  读取文件到控制台
     */
    @Test
    public void testFileReader01(){
        File file = new File("study_io/src/test/java.txt");
        FileReader fr = null;
        try {
            fr = new FileReader(file);
            char[] c = new char[3];
            int len;
            while ((len = fr.read(c)) != -1){
                String str = new String(c, 0, len);
                System.out.print(str);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (fr != null){
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 复制文件
     */
    @Test
    public void testFileReaderWriter02(){
        // 1、输入流对应的文件src一定要存在，否则抛异常。输出流对应的文件dest可以不存在，执行过程中会自动创建
        FileReader fr = null;
        FileWriter fw = null;

        try {
            File src = new File("study_io/src/test/java.txt");
            File dest = new File("study_io/src/test/java1.txt");
            fr = new FileReader(src);
            fw = new FileWriter(dest);
            char[] c = new char[24];
            int len;
            while ((len= fr.read(c)) != -1){
                String str = new String(c, 0, len);
//                System.out.print(str);
                fw.write(str);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (fw != null){
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fr != null){
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
