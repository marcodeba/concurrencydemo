package com.demo.javademo.io.copyFile;

import java.io.*;

public class CopyFileTest {
    public static void main(String[] args) throws IOException {
        CopyFileTest test = new CopyFileTest();
    }

    public void test12() throws IOException {
        // 输入和输出都使用缓冲流
        //FileInputStream in = new FileInputStream("E:\\视频资料\\大数据原理与应用\\1.1大数据时代.mp4");
        BufferedInputStream inBuffer = new BufferedInputStream(new FileInputStream("E:\\视频资料\\大数据原理与应用\\1.1大数据时代.mp4"));
        FileOutputStream out = new FileOutputStream("1.1大数据时代.mp4");
        BufferedOutputStream outBuffer = new BufferedOutputStream(out);
        int len = 0;
        byte[] bs = new byte[1024];
        long begin = System.currentTimeMillis();
        while ((len = inBuffer.read(bs)) != -1) {
            outBuffer.write(bs, 0, len);
        }
        System.out.println("复制文件所需的时间：" + (System.currentTimeMillis() - begin)); // 平均时间约 200 多毫秒
        inBuffer.close();
        //in.close();
        outBuffer.close();
        out.close();
    }

    public void test13() throws IOException {
        // 只有输入使用缓冲流
        //FileInputStream in = new FileInputStream("E:\\视频资料\\大数据原理与应用\\1.1大数据时代.mp4");
        BufferedInputStream inBuffer = new BufferedInputStream(new FileInputStream("E:\\视频资料\\大数据原理与应用\\1.1大数据时代.mp4"));
        FileOutputStream out = new FileOutputStream("1.1大数据时代.mp4");
        int len = 0;
        byte[] bs = new byte[1024];
        long begin = System.currentTimeMillis();
        while ((len = inBuffer.read(bs)) != -1) {
            out.write(bs, 0, len);
        }
        System.out.println("复制文件所需时间：" + (System.currentTimeMillis() - begin)); // 平均时间约 500 多毫秒
        inBuffer.close();
        //in.close();
        out.close();
    }

    public void test14() throws IOException {
        // 输入和输出都不使用缓冲流
        FileInputStream in = new FileInputStream("E:\\视频资料\\大数据原理与应用\\1.1大数据时代.mp4");
        FileOutputStream out = new FileOutputStream("1.1大数据时代.mp4");
        int len = 0;
        byte[] bs = new byte[1024];
        long begin = System.currentTimeMillis();
        while ((len = in.read(bs)) != -1) {
            out.write(bs, 0, len);
        }
        System.out.println("复制文件所需时间：" + (System.currentTimeMillis() - begin)); // 平均时间 700 多毫秒
        in.close();
        out.close();
    }

    public void test15() throws IOException {
        // 不使用缓冲
        FileInputStream in = new FileInputStream("E:\\视频资料\\大数据原理与应用\\1.1大数据时代.mp4");
        FileOutputStream out = new FileOutputStream("1.1大数据时代.mp4");
        int len = 0;
        long begin = System.currentTimeMillis();
        while ((len = in.read()) != -1) {
            out.write(len);
        }
        System.out.println("复制文件所需时间：" + (System.currentTimeMillis() - begin)); // 平均时间约 160000 毫秒，约 2 分多钟
        in.close();
        out.close();
    }
}
