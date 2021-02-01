package com.feng.io;

import org.junit.Test;

import java.io.*;

/**
 * * 抽象基类         节点流（文件流）            缓冲流（处理流的一种：加速节点流的操作，速率快，可以说是对节点流的加工）
 * * InputStream      FileInputStream(字节)       BufferedInputStream
 * * OutputStream     FileOutputStream(字节)      BufferedOurputStream (flush());
 * * Reader           FileReader(字符)            BufferedReader  (readLine())  读取一行
 * * Writer           FileWriter(字符)            BufferedWriter (flush());
 */
/**
 * @ClassName Test_04_Buffered
 * @Description TODO
 * @Author fengfanli
 * @Date 2021/1/29 16:55
 * @Version 1.0
 */
public class Test_04_Buffered {

    /**
     * 缓冲字节流 ：处理文本文件   BufferedReader、BufferedWriter
     */
    @Test
    public void testBufferedReader() {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            File file = new File("java.txt");
            File file1 = new File("java2.txt");
            FileReader fr = new FileReader(file);
            FileWriter fw = new FileWriter(file1);
            br = new BufferedReader(fr);
            bw = new BufferedWriter(fw);

//            char[] c = new char[1024];
//            int len;
//            while ((len = br.read(c)) != -1){
//                String str = new String(c, 0, len);
//                System.out.print(str);
//            }
            String str;
            while ((str = br.readLine()) != null) {          // 这个会更块：阅读一行，返回也是字符串
                System.out.println(str);
                bw.write(str);    // 不会自动换行，都在一行
                bw.newLine();    //  或者 直接一句话 bw.write(str + "\n");  这样就可以换行啦
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    @Test
    public void testCopyFile() {
        long start = System.currentTimeMillis();
//        String src = "C:\\Users\\fw8842\\Desktop\\fengfanli\\rlw.mp4";
//        String dest = "C:\\Users\\fw8842\\Desktop\\fengfanli\\rlw02.mp4";
        String src = "C:\\Users\\fw8842\\Desktop\\fengfanli\\111.zip";
        String dest = "E:\\111.zip";
        copyFile(src, dest);
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为：" + (end - start)); // 4 比使用节点流 快太多了，节点流为  2838
    }

    /**
     * 实现文件复制的方法
     *
     * @param src
     * @param dest
     */
    public void copyFile(String src, String dest) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            // 1. 提供读入、写出的文件
            File file1 = new File("PersonalPhoto.jpg");
            File file2 = new File("PersonalPhoto01.jpg");
            // 2、想创建相应的节点流 ：FileInputStream FileOutputStream
            FileInputStream fis = new FileInputStream(file1);
            FileOutputStream fos = new FileOutputStream(file2);
            // 3、将创建的节点流对象作为形参传递给缓冲流的构造器中
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);
            //4、具体的实现文件复制的操作
            byte[] b = new byte[1024];
            int len;
            while ((len = bis.read(b)) != -1) {
                bos.write(b, 0, len);
                bos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 5、关闭相应的流
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 缓冲字节流： 使用BufferedInputStream 和 BufferedOutputStream 实现非 文本文件 的复制
     */
    @Test
    public void testBufferedInputStream() {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            // 1. 提供读入、写出的文件
            File file1 = new File("PersonalPhoto.jpg");
            File file2 = new File("PersonalPhoto01.jpg");
            // 2、想创建相应的节点流 ：FileInputStream FileOutputStream
            FileInputStream fis = new FileInputStream(file1);
            FileOutputStream fos = new FileOutputStream(file2);
            // 3、将创建的节点流对象作为形参传递给缓冲流的构造器中
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);
            //4、具体的实现文件复制的操作
            byte[] b = new byte[256];
            int len;
            while ((len = bis.read(b)) != -1) {
                bos.write(b, 0, len);
                bos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 5、关闭相应的流
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
