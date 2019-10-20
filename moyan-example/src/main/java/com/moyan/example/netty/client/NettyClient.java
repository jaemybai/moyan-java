package com.moyan.example.netty.client;

import com.moyan.example.AbstractOperateLogTest;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

public class NettyClient extends AbstractOperateLogTest {

    public static void main(String[] args) throws Exception{
        new NettyClient().runClient();
    }

    private void runClient() throws Exception{
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group).channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                });
        Channel channel = bootstrap.connect("127.0.0.1",9091).channel();
        int cnt = 0;
        while (true) {
            channel.writeAndFlush(this.getClass().getName() + "hello server index:" + cnt);
            Thread.sleep(2000);
        }
    }
}
