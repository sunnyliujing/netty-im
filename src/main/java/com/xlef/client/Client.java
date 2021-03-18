package com.xlef.client;

import com.xlef.common.CommonMessage;
import com.xlef.message.NormalMessageBody;
import com.xlef.client.codec.MessageFrameDecoder;
import com.xlef.client.codec.MessageFrameEncoder;
import com.xlef.client.codec.MessageProtocolDecoder;
import com.xlef.client.codec.MessageProtocolEncoder;
import com.xlef.utils.IdUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class Client {

    public static void main(String[] args) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline()
                        .addLast(new MessageFrameDecoder())
                        .addLast(new MessageProtocolDecoder())
                        .addLast(new MessageFrameEncoder())
                        .addLast(new MessageProtocolEncoder())
                        .addLast(new LoggingHandler(LogLevel.INFO));
            }
        });

        try {
            ChannelFuture future = bootstrap.connect("127.0.0.1", 7080).sync();
            NormalMessageBody body = new NormalMessageBody();
            body.setContent("hello");
            body.setFrom("1001");
            body.setTo("1002");
            CommonMessage commonMessage = new CommonMessage(IdUtil.nextId(),body);
            future.channel().writeAndFlush(commonMessage);
            future.channel().closeFuture().sync();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
