package Netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 聊天服务器
 */
public class NettyChatServer {
    private int port;

    public NettyChatServer(int port) {
        this.port = port;
    }

    public void run() throws InterruptedException {
        // 1.创建bossGroup线程组：处理网络事件——连接事件
        EventLoopGroup boosGroup = null;
        // 2.创建workGroup：处理网络时间——读写事件 2*CPUNum的线程数
        EventLoopGroup workGroup = null;

        try {
            boosGroup = new NioEventLoopGroup(1);
            workGroup = new NioEventLoopGroup();
            // 创建服务端启动助手
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 设置BG和WG
            serverBootstrap.group(boosGroup,workGroup)
                    .channel(NioServerSocketChannel.class) // 设置服务端通道
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,Boolean.TRUE)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        //7. 创建一个通道初始化对象
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                        //8. 向pipeline中添加自定义业务处理handler
                        //添加编解码器
                            ch.pipeline().addLast(new StringDecoder());
                            ch.pipeline().addLast(new StringEncoder());
                            ch.pipeline().addLast(new NettyChatServerHandler());
                        }
                    });
            // 启动服务端并绑定端口，同时将异步改为同步
            ChannelFuture future = serverBootstrap.bind(port);
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if(future.isSuccess()){
                        System.out.println("端口绑定成功");
                    }else {
                        System.out.println("端口绑定失败");
                    }
                }
            });
            System.out.println("聊天室服务器启动成功");
            //关闭通道(并不是真正意义上关闭，而是监听通道关闭的状态和关闭连接池)
            future.channel().closeFuture().sync();
        }finally {
            boosGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new NettyChatServer(9998).run();
    }
}
