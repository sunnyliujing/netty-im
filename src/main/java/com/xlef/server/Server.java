package com.xlef.server;

import com.xlef.handler.EchoServerHandler;
import com.xlef.handler.MessageServerProcessHandler;
import com.xlef.handler.TestServerHandler;
import com.xlef.server.codec.MessageFrameDecoder;
import com.xlef.server.codec.MessageFrameEncoder;
import com.xlef.server.codec.MessageProtocolDecoder;
import com.xlef.server.codec.MessageProtocolEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class Server {

    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void run() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        try {
            serverBootstrap
                    .group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel
                                    .pipeline()
                                    .addLast(new MessageFrameDecoder())
                                    .addLast(new MessageProtocolDecoder())
                                    .addLast(new MessageFrameEncoder())
                                    .addLast(new MessageProtocolEncoder())
                                    .addLast(new LoggingHandler(LogLevel.INFO))
                                    .addLast(new MessageServerProcessHandler());

                        }
                    }).option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true);
            serverBootstrap.bind(port).sync().channel().closeFuture().sync();
        }finally{
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Server(7080).run();
    }
}
