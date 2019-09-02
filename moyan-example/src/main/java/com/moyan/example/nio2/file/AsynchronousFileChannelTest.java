package com.moyan.example.nio2.file;

import com.moyan.example.base.AbstractOperateLogTest;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

public class AsynchronousFileChannelTest extends AbstractOperateLogTest {

    public static void main(String[] args) throws Exception{

//        new AsynchronousFileChannelTest().test_readAndWriteDirectly();
        new AsynchronousFileChannelTest().test_readAndWritewithHandler();

    }


    private void test_readAndWriteDirectly() throws Exception{
        Path path = Paths.get("pom.xml");
        AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);

        Path writePath = Paths.get("write.xml");
        if(!writePath.toFile().exists()) {
            writePath.toFile().createNewFile();
        }
        AsynchronousFileChannel writeAsynchronousFileChannel = AsynchronousFileChannel.open(writePath, StandardOpenOption.WRITE);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int position = 0;
        Future<Integer> future = asynchronousFileChannel.read(byteBuffer,position);
        while (!future.isDone()) {

        }
        byteBuffer.flip();
        int size = byteBuffer.limit() - byteBuffer.position();
        byte[] dataBytes = new byte[size];
        System.arraycopy(byteBuffer.array(),byteBuffer.position(),dataBytes,0,size);
        logger.info("futureSize:{}, realSise:{},str:{}", future.get(),size,new String(dataBytes));
        writeAsynchronousFileChannel.write(byteBuffer,0);
        byteBuffer.clear();
        asynchronousFileChannel.close();
        writeAsynchronousFileChannel.close();
    }

    private void test_readAndWritewithHandler() throws Exception{
        Path path = Paths.get("pom.xml");
        AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);

        Path writePath = Paths.get("write2.xml");
        if(writePath.toFile().delete()) {
            writePath.toFile().createNewFile();
        }
        AsynchronousFileChannel writeAsynchronousFileChannel = AsynchronousFileChannel.open(writePath, StandardOpenOption.WRITE);


        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        asynchronousFileChannel.read(byteBuffer, 0, null,
                new CompletionHandler<Integer, Object>() {
                    @Override
                    public void completed(Integer result, Object attachment) {
                        byteBuffer.flip();
                        int size = byteBuffer.limit() - byteBuffer.position();
                        byte[] dataBytes = new byte[size];
                        System.arraycopy(byteBuffer.array(),byteBuffer.position(),dataBytes,0,size);
                        logger.info("futureSize:{}, realSise:{},str:{}", result,size,"");

                        writeAsynchronousFileChannel.write(byteBuffer, 0, null,
                                new CompletionHandler<Integer, Object>() {
                                    @Override
                                    public void completed(Integer result, Object attachment) {
                                        byteBuffer.clear();
                                        logger.info("futureSize:{}, realSise:{},str:{}", result,size,new String(dataBytes));
                                    }

                                    @Override
                                    public void failed(Throwable exc, Object attachment) {
                                        logger.error(exc.getMessage(),exc);
                                    }
                                });
                    }

                    @Override
                    public void failed(Throwable exc, Object attachment) {
                        logger.error(exc.getMessage(),exc);
                    }
                });


        asynchronousFileChannel.close();
        writeAsynchronousFileChannel.close();
    }
}
