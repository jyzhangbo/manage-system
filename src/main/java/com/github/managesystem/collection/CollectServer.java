package com.github.managesystem.collection;

import com.github.managesystem.collection.handle.encoder.ResponseEncoder;
import com.github.managesystem.collection.handle.decoder.ProtocolReceiveDecoder;
import com.github.managesystem.collection.handle.CollectServerHandler;
import com.github.managesystem.collection.handle.decoder.ByteToValueDecoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CollectServer {

    private final int port;

    public static Map<String,ChannelHandlerContext> channelHandlerMap = new HashMap<>();

    public CollectServer(int port){
        this.port = port;
    }

    public void start() throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup();

        EventLoopGroup group = new NioEventLoopGroup();

        try{

            ServerBootstrap sb = new ServerBootstrap();
            sb.group(bossGroup,group)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            log.info("IP:{} 连接成功",channel.remoteAddress().getHostName());
                            channel.pipeline().addLast(new ProtocolReceiveDecoder());
                            channel.pipeline().addLast(new ByteToValueDecoder());
                            channel.pipeline().addLast(new ResponseEncoder());
                            channel.pipeline().addLast(new StringEncoder());
                            channel.pipeline().addLast(new CollectServerHandler());

                        }
                    })
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture cf = sb.bind(port).sync(); // 服务器异步创建绑定
            System.out.println(CollectServer.class + " 启动正在监听： " + cf.channel().localAddress());
            cf.channel().closeFuture().sync(); // 关闭服务器通道

        }catch (Exception e){
            group.shutdownGracefully().sync(); // 释放线程池资源
            bossGroup.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception{
        new CollectServer(8888).start();
    }

}
