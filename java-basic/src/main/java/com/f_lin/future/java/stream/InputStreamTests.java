package com.f_lin.future.java.stream;

import java.io.*;

/**
 * @author F_lin
 * @since 2019/4/7
 **/
public class InputStreamTests {

    public static void main(String[] args) throws IOException {
        //fileInputStream();
        //fileReader();
        fileOutPutSteam();
    }


    private static InputStream fileInputStream() throws IOException {
        String path = "E:\\eg\\inputStreamTest.txt";
        //创建字节输入流
        InputStream stream = new FileInputStream(path);
        //创建一个长度为1024的竹筒
        byte[] temp = new byte[2014];
        //用于保存的实际字节数
        int hasRead;
        //使用循环来重复取水的过程
        while ((hasRead = stream.read(temp)) > 0) {
            //取出竹筒中的水滴（字节），将字节数组转换成字符串进行输出
            System.out.print(new String(temp, 0, hasRead));
        }
        return stream;
    }

    private static void fileReader() throws IOException {
        String path = "E:\\eg\\inputStreamTest.txt";
        //创建字节输入流
        Reader reader = new FileReader(path);
        //创建一个长度为1024的竹筒
        char[] temp = new char[2014];
        //用于保存的实际字节数
        int hasRead;
        //使用循环来重复取水的过程
        while ((hasRead = reader.read(temp)) > 0) {
            //取出竹筒中的水滴（字节），将字节数组转换成字符串进行输出
            System.out.print(new String(temp, 0, hasRead));
        }
    }

    private static void fileOutPutSteam() throws IOException {
        String path = "E:\\eg\\outPutStreamTest.txt";
       /* String path2 = "E:\\eg\\inputStreamTest.txt";
        //创建字节输入流
        InputStream stream = new FileInputStream(path2);*/

        FileOutputStream outputStream = new FileOutputStream(path);

        byte[] temp = new byte[2014];
        int hasRead;
        FileInputStream stream = (FileInputStream)fileInputStream();
        stream.reset();

        while ((hasRead = stream.read(temp)) > 0) {
            outputStream.write(temp, 0, hasRead);
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(stream);

    }
}
