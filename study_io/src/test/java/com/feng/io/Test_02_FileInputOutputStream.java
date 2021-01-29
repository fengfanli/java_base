package com.feng.io;


import org.junit.Test;

import java.io.*;

/**
 * 1、流的分类
 * 按照数据流向的不同：输入流 输出流
 * 按照处理数据的单位的不同：字节流 字符流（处理的文本文件）
 * 按照角色的不同：节点流（直接作用于文件的） 处理流
 * <p>
 * 2、 IO 的体系
 * 抽象基类         节点流（文件流）            缓冲流（处理流的一种：加速节点流的操作，速率快，可以说是对节点流的加工）
 * InputStream      FileInputStream(字节)       BufferedInputStream
 * OutputStream     FileOutputStream(字节)      BufferedOurputStream
 * Reader           FileReader(字符)            BufferedReader
 * Writer           FileWriter(字符)            BufferedWriter
 */

/**
 * @ClassName Test_02_FileInputOutputStream
 * @Description TODO
 * @Author fengfanli
 * @Date 2021/1/29 16:19
 * @Version 1.0
 */
public class Test_02_FileInputOutputStream {

    /**
     * 从硬盘存的一个文件中，读取其内容到程序中，使用FileInputStream
     * 要读取的文件一定要存在，否则抛异常：FileNotFoundException
     *
     * @throws FileNotFoundException
     */
    @Test
    public void testFileInputStream1() throws IOException {
        // 1、创建一个 File类的对象。
        File file = new File("study_io/src/test/hello.txt");
        // 2、创建一个FileInputStream 类的对象
        FileInputStream fis = new FileInputStream(file);
        // 3、调用FileInputoutStream 的方法，实现file文件的读取。
        /**
         * read(): 读取文件的一个字节（ 1 byte ）。当执行到文件结尾时，返回-1
         */
//        int b = fis.read();
//        while (b != -1){
//            System.out.print(b); //  这是把char型转成int型了， 下面是还原出来
//            System.out.print((char) b);
//            b = fis.read();
//        }
        int b;
        while ((b = fis.read()) != -1) {
            System.out.println(b);  // 对应的 字母 在ASCII 中的位置
            System.out.println((char) b);
        }
        // 4、关闭相应的流   流的资源，不是java虚拟机内存的资源（内存的资源，会自动关闭），需要显示的关，
        fis.close();

        /**
         * hello.txt 算是一个文本， 文本对于英文字符来讲，可用byte来读取。
         *  因为英文字符一个 256 就可放的下，  1byte = 8bit((位)  即256  英文大小：a-z:97-122 A-Z：65-90
         *  如果加中文：中国，一个中文就是两个char, 用byte来读 就会出错。中文是字符来读。
         */
    }


    /**
     * 使用 try-catch 的方式处理如下的异常更合理：保证流的关闭操作一定可以执行
     */
    @Test
    public void testFileInputStream2() {
        FileInputStream fis = null;
        try {
            // 1、创建一个 File类的对象。
            File file = new File("study_io/src/test/hello.txt");
            // 2、创建一个FileInputStream 类的对象
            fis = new FileInputStream(file);
            // 3、调用FileInputoutStream 的方法，实现file文件的读取。
            int b;
            while ((b = fis.read()) != -1) {
                System.out.println(b);  //
                System.out.println((char) b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                // 4、关闭相应的流
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 使用String 字符串 对字节数组 优化。
     **/
    @Test
    public void testFileInputStream3() {
        FileInputStream fls = null;
        try {
            File file = new File("study_io/src/test/hello.txt");
            fls = new FileInputStream(file);
            byte[] b = new byte[5];    // 读取到的数据要写入的数组
            int len;                    // 每次读入到 byte 中的字节的长度
            while ((len = fls.read(b)) != -1) {
//                for (int i = 0; i< len; i++){  //b.length 则输出 abcdefgcde
//                    System.out.print((char) b[i]);
//                }
                String str = new String(b, 0, len);
                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fls != null) {
                try {
                    fls.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * FileOutputStream
     */
    @Test
    public void testFileOutputStream4() {
        // 1、创建一个File对象，表明要写入的文件位置
        // 输出的物理文件可以不存在，在执行的过程中，若不存在，会自动的创建。若存在，会将原有的文件覆盖
        File file = new File("hello1.txt");
        // 2、创建一个FileOutputStream 的对象，将file 的对象作为形参传递给FileOutputStream 的构造器中
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            // 3、写入的操作
            fos.write(new String(" I Love China I Love The World").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4、关闭输出流
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    /**
     * 从硬盘读取一个文件，并写入到另一个位置。（相当于复制）
     */
    @Test
    public void testFileInputOutputStream5() {
        // 1、提供读入、写出的文件
        File file1 = new File("C:\\Users\\fw8842\\Desktop\\fengfanli\\PersonalPhoto.jpg");
        File file2 = new File("C:\\Users\\fw8842\\Desktop\\fengfanli\\PersonalPhoto01.jpg");
        // 2、提供相应的流
        FileInputStream fls = null;
        FileOutputStream fos = null;
        try {
            fls = new FileInputStream(file1);
            fos = new FileOutputStream(file2);
            // 3、 实现文件的复制
            byte[] b = new byte[20];
            int len;
            while ((len = fls.read(b)) != -1) {
                // 错误的两种写法：
                // fos.write(b);
                //fos.write(b, 0 ,b.length);
                fos.write(b, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4、 关闭输出、输入流
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fls != null) {
                try {
                    fls.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Test
    public void testCopyFile6() {
        long start = System.currentTimeMillis();
//        String src = "C:\\Users\\fw8842\\Desktop\\fengfanli\\rlw.mp4";
//        String dest = "C:\\Users\\fw8842\\Desktop\\fengfanli\\rlw01.mp4";
        String src = "C:\\Users\\fw8842\\Desktop\\fengfanli\\111.zip";
        String dest = "C:\\Users\\fw8842\\Desktop\\fengfanli\\333.zip";
        copyFile(src, dest);
        long end = System.currentTimeMillis();
        System.out.println("花费的时间：" + (end - start)); // 2838
    }

    /**
     * 实现文件复制的方法
     *
     * @param src
     * @param dest
     */
    public void copyFile(String src, String dest) {
        // 1、提供读入、写出的文件
        File file1 = new File(src);
        File file2 = new File(dest);
        // 2、提供相应的流
        FileInputStream fls = null;
        FileOutputStream fos = null;
        try {
            fls = new FileInputStream(file1);
            fos = new FileOutputStream(file2);
            // 3、 实现文件的复制
            byte[] b = new byte[1024];
            int len;
            while ((len = fls.read(b)) != -1) {
                // 错误的两种写法：
                // fos.write(b);
                //fos.write(b, 0 ,b.length);
                fos.write(b, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4、 关闭输出、输入流
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fls != null) {
                try {
                    fls.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

