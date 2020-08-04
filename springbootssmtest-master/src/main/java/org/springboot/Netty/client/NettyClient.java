package org.springboot.Netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springboot.config.ResponseData.clas.RestResponseData;
import org.springboot.config.ResponseData.clas.ServiceException;

/**
 * @ClassName NettyClient
 * @Description TODO
 * @Author mcx
 * @Date 2020/8/3 10:43
 * @Version 1.0
 */
public class NettyClient {
    /**
     * 主机
     */
    private String host;

    /**
     * 端口号
     */
    private int port;

    /**
     * 构造函数
     *
     * @param host
     * @param port
     */
    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * 连接方法
     */
    public void connect() {
        EventLoopGroup group = new NioEventLoopGroup();// 初始化用于连接及I/O工作的"线程池"；
        try {
            //初始化Bootstrap实例， 此实例是netty客户端应用开发的入口，也是本篇介绍的重点， 下面我们会深入分析；
            Bootstrap bootstrap = new Bootstrap();
            //通过Bootstrap的group方法，设置（1）中初始化的"线程池"；
            //指定通道channel的类型，由于是客户端，故而是NioSocketChannel；
            bootstrap.group(group).channel(NioSocketChannel.class);
            //设置SocketChannel的选项（此处不详述，后面的系列会进行深入分析）；
            bootstrap.option(ChannelOption.TCP_NODELAY, true);//要求低延迟
            // bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.handler(new NettyClientInitializer());//程序一启动就执行,设置SocketChannel的处理器， 其内部是实际业务开发的"主战场"（此处不详述，后面的系列会进行深入分析）；
            Channel channel = bootstrap.connect(host, port).sync().channel();//连接指定的服务地址；
            // 发送json字符串
            String msg = "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmccccccccccxxxxxxx\n";
            channel.writeAndFlush(msg);
            channel.closeFuture().sync();
        } catch (Exception e) {
            throw new ServiceException(500,"客户端连接失败");
        } finally {
            group.shutdownGracefully();
        }
    }

    /**
     * 测试入口
     *
     * @param args
     */
    public static void main(String[] args) {
        String host = "172.18.19.68";
        int port = 8011;
        NettyClient nettyClient = new NettyClient(host, port);
        nettyClient.connect();

    }
}