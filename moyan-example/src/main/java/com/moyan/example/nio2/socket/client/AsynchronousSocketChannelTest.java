package com.moyan.example.nio2.socket.client;

import com.moyan.example.base.AbstractOperateLogTest;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.Future;

public class AsynchronousSocketChannelTest extends AbstractOperateLogTest {

    public static void main(String[] args) throws Exception{

    }

    private void test() throws Exception{
        SocketAddress socketAddress = new InetSocketAddress("127.0.0.1",9090);
        AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();
        Future future = socketChannel.connect(socketAddress);
        future.get();
        socketChannel.connect(socketAddress, null, new CompletionHandler<Void, Object>() {
            @Override
            public void completed(Void result, Object attachment) {
            }

            @Override
            public void failed(Throwable exc, Object attachment) {

            }
        });
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        socketChannel.read(byteBuffer);
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
    }

}
