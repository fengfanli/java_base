package com.feng.io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * java.io.File 类
 * 1、凡是与输入输出相关的类、接口等都定义在java.io 包下
 * 2、File是一个类，可以有构造器创建其对象。此对象对应（.txt .avi .doc .ppt .mp3 .jpg）或其文件目录
 * 3、File类对象时与平台无关的。
 * 4、File中的方法，仅涉及到如何创建、删除、重命名等等。只要涉及文件内容的，File是无能为力的，必须由io流来完成。
 * 5、File类的对象常作为IO流的具体类的构造器的形参。
 */
/**
 * @ClassName Test_01_File
 * @Description TODO
 * @Author fengfanli
 * @Date 2021/1/29 16:15
 * @Version 1.0
 */
public class Test_01_File {

    /**
     * 路径：
     * 绝对路径：包括盘符在内的完整的文件路径
     * 相对路径：在当前文件目录下的文件的路径
     * <p>
     *     访问文件名
     * getName()	： 获取文件名
     * getPath()	： 获取文件路径
     * getAbsoluteFile() ：获取绝对文件名
     * getAbsolutePath() ：获取绝对路径
     * getParent()		 ：获取父级目录
     * renameTo(File newName) ：重命名（有坑）
     */
    @Test
    public void test01() {
        File file1 = new File("D:/io/helloworld.txt"); // 绝对目录
        File file2 = new File("study_io/src/test/hello.txt"); // 相对目录

        File file3 = new File("d:\\io\\io1");// 目录

        System.out.println("文件：");
        System.out.println(file1.getName());
        System.out.println(file1.getPath());
        System.out.println(file1.getAbsoluteFile());
        System.out.println(file1.getAbsolutePath());
        System.out.println(file1.getParent());
        System.out.println("目录：");
        System.out.println(file3.getName());
        System.out.println(file3.getPath());
        System.out.println(file3.getAbsoluteFile());
        System.out.println(file3.getAbsolutePath());
        System.out.println(file3.getParent());

        //renameTo(File newName)
        // file1.renameTo(file2):file1重命名为file2，
        // 要求：file1 一定存在，file2一定不存在, file 可以是文件，也可是目录
        boolean b = file1.renameTo(file2);
        System.out.println(b);
    }

    /**
     * 文件检测
     * exists()	：是否存在
     * canWrite()：能否写
     * canRead()：能否读
     * isFile()：是否是文件
     * isDirectory()：是否是目录
     * lastModified()：最后一次修改时间
     * length()：文件长度
     */
    @Test
    public void test02() {
        File file1 = new File("D:/io/helloworld.txt");
        File file2 = new File("D:\\io\\io1");

        System.out.println("文件：");
        System.out.println(file1.exists());
        System.out.println(file1.canRead());
        System.out.println(file1.canWrite());
        System.out.println(file1.isFile());
        System.out.println(file1.isDirectory());
        System.out.println(new Date(file1.lastModified()));
        System.out.println(file1.length());
        System.out.println("目录：");
        System.out.println(file2.exists());
        System.out.println(file2.canRead());
        System.out.println(file2.canWrite());
        System.out.println(file2.isFile());
        System.out.println(file2.isDirectory());
        System.out.println(new Date(file2.lastModified()));
        System.out.println(file2.length());

    }

    /**
     * 文件、目录 操作
     * createNewFile() ： 创建一个新文件
     * delete() : 删除一个文件
     * mkDir()  ： 创建一个文件目录。只有在上层文件目录存在的情况下，才能返回true
     * mkDirs() ： 穿件一个文件目录。若上层文件目录不存在，一并创建。
     * list()   ：获取所有的文件名称遍历
     * listFiles()：获取所有的 文件对象 File
     */
    @Test
    public void test03() throws IOException {
        File file1 = new File("D:/io/helloworld.txt");
        System.out.println("删除文件："+file1.delete());
        if (!file1.exists()) {
            boolean newFile = file1.createNewFile();
            System.out.println("创建文件："+newFile);
        }

        File file2 = new File("D:\\io\\io2");
        if (!file2.exists()){
            boolean mkdir = file2.mkdir();
            System.out.println("创建目录"+mkdir);
            // file2.mkdirs();
        }

        System.out.println();
        System.out.println("list()方法:");
        File file3 = new File("D:\\apache-maven-3.6.1");
        String[] strs = file3.list();
        for (String s : strs){
            System.out.println(s);
        }

        System.out.println();
        System.out.println("listFiles()方法:");
        File[] files = file3.listFiles();
        for (int i = 0; i< files.length; i++){
            System.out.println(files[i].getName()+","+files[i].getPath());
        }
    }




}

