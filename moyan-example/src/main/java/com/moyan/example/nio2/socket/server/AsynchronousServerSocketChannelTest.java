package com.moyan.example.nio2.socket.server;

import com.moyan.example.base.AbstractOperateLogTest;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.Selector;
import java.util.concurrent.Future;

public class AsynchronousServerSocketChannelTest extends AbstractOperateLogTest {

    public static void main(String[] args) throws Exception{
        new AsynchronousServerSocketChannelTest().test_read_andWrite();
    }

    private void test_read_andWrite() throws Exception {
        AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9090));
        final Selector selector = Selector.open();
        serverSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(AsynchronousSocketChannel result, Object attachment) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                Future<Integer> future =  result.read(byteBuffer);
                result.read(byteBuffer, null, new CompletionHandler<Integer, Object>() {
                    @Override
                    public void completed(Integer result, Object attachment) {

                    }

                    @Override
                    public void failed(Throwable exc, Object attachment) {

                    }
                });

                result.write(byteBuffer);
                result.write(byteBuffer, null, new CompletionHandler<Integer, Object>() {

                    @Override
                    public void completed(Integer result, Object attachment) {

                    }

                    @Override
                    public void failed(Throwable exc, Object attachment) {

                    }
                });
            }

            @Override
            public void failed(Throwable exc, Object attachment) {

            }
        });
    }
}
