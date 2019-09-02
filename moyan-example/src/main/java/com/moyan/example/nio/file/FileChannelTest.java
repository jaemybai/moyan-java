package com.moyan.example.nio.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTest {

    private static Logger logger = LoggerFactory.getLogger(FileChannelTest.class);

    public static void main(String[] args) throws Exception{
        main_testChannel(args);
    }

    public static void main_testChannel(String[] args) throws Exception {
        File file = new File("sss");
        logger.info("base Path:{}" , file.getAbsolutePath());
        String srcPath = "pom.xml";
        String destPath = "destFile.xml";
        FileInputStream fileInputStream = new FileInputStream(srcPath);
        FileOutputStream fileOutputStream = new FileOutputStream(destPath);

        FileChannel inputFileChannel = fileInputStream.getChannel();
        FileChannel outputFileChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(40);
        int index = 0;
        while (true) {
            int eof = inputFileChannel.read(byteBuffer);
            logger.info("read index:{},size:{}, length: {}", ++index, eof,byteBuffer.array().length);
            if (eof == -1) {
                break;
            }
            byteBuffer.flip();
//            char[] chars = new char[eof];
//            int idx = 0;
//            char tem ;
//            while (byteBuffer.hasRemaining()) {
//                chars[idx] = (char)byteBuffer.get();
//                tem = chars[idx];
//                System.out.print(chars[idx]);
//                idx++;
//            }
//            if(index ==1) {
//                break;
//            }
            while (byteBuffer.hasRemaining()) {
                outputFileChannel.write(byteBuffer);
            }
            byteBuffer.clear();
        }
        inputFileChannel.close();
        outputFileChannel.close();
        fileInputStream.close();
        fileOutputStream.close();
    }

}
