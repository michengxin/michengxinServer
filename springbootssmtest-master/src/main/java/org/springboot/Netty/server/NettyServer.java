package org.springboot.Netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName NettyServer
 * @Description TODO
 * @Author mcx
 * @Date 2020/8/3 9:52
 * @Version 1.0
 */
@Component
public class NettyServer implements Runnable {
    /**
     * 日志
     */
    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 端口号
     */

    private static final int port = 8011;

    /**
     * 启动服务器方法
     *
     * @param
     */
    public void run() {
        System.out.println("--------------------NettyServer开启------------------------------------");
        long t1 = System.currentTimeMillis();
        EventLoopGroup bossGroup = new NioEventLoopGroup();//后续(创建一个线程池)
        EventLoopGroup workerGroup = new NioEventLoopGroup();//前缀(创建一个线程池)
        try {
            //初始化ServerBootstrap实例,此实例是netty服务端应用开发的入口,也是本篇介绍的重点,下面我们会深入分析
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //通过ServerBootstrap的group方法，设置（1）中初始化的主从"线程池"；
            serverBootstrap.group(bossGroup, workerGroup);
            //指定通道channel的类型，由于是服务端，故而是NioServerSocketChannel；
            serverBootstrap.channel(NioServerSocketChannel.class);
            //配置ServerSocketChannel的选项
            serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);//请求连接的队列大小 1024个请求(该服务器中最多同时处理1024个请求)
            //设置ServerSocketChannel的处理器（此处不详述，后面的系列会进行深入分析） 日志处理器
            serverBootstrap.handler(new LoggingHandler(LogLevel.INFO));
//            SO_RCVBUF
//          Socket参数，TCP数据接收缓冲区大小。该缓冲区即TCP接收滑动窗口，linux操作系统可使用命令：cat /proc/sys/net/ipv4/tcp_rmem查询其大小。一般情况下，该值可由用户在任意时刻设置，但当设置值超过64KB时，需要在连接到远端之前设置。
//            SO_SNDBUF
//         Socket参数，TCP数据发送缓冲区大小。该缓冲区即TCP发送滑动窗口，linux操作系统可使用命令：cat /proc/sys/net/ipv4/tcp_smem查询其大小。
//            TCP_NODELAY
//  TCP参数，立即发送数据，默认值为Ture（Netty默认为True而操作系统默认为False）。该值设置Nagle算法的启用，改算法将小的碎片数据连接成更大的报文来最小化所发送的报文的数量，如果需要发送一些较小的报文，则需要禁用该算法。Netty默认禁用该算法，从而最小化报文传输延时。
//            SO_KEEPALIVE
//  Socket参数，连接保活，默认值为False。启用该功能时，TCP会主动探测空闲连接的有效性。可以将此功能视为TCP的心跳机制，需要注意的是：默认的心跳间隔是7200s即2小时。Netty默认关闭该功能。
//            SO_REUSEADDR
//  Socket参数，地址复用，默认值False。有四种情况可以使用：(1).当有一个有相同本地地址和端口的socket1处于TIME_WAIT状态时，而你希望启动的程序的socket2要占用该地址和端口，比如重启服务且保持先前端口。(2).有多块网卡或用IP Alias技术的机器在同一端口启动多个进程，但每个进程绑定的本地IP地址不能相同。(3).单个进程绑定相同的端口到多个socket上，但每个socket绑定的ip地址不同。(4).完全相同的地址和端口的重复绑定。但这只用于UDP的多播，不用于TCP。
//            SO_LINGER
//  Socket参数，关闭Socket的延迟时间，默认值为-1，表示禁用该功能。-1表示socket.close()方法立即返回，但OS底层会将发送缓冲区全部发送到对端。0表示socket.close()方法立即返回，OS放弃发送缓冲区的数据直接向对端发送RST包，对端收到复位错误。非0整数值表示调用socket.close()方法的线程被阻塞直到延迟时间到或发送缓冲区中的数据发送完毕，若超时，则对端会收到复位错误。
//            IP_TOS
//  IP参数，设置IP头部的Type-of-Service字段，用于描述IP包的优先级和QoS选项。
//            ALLOW_HALF_CLOSURE
//  Netty参数，一个连接的远端关闭时本地端是否关闭，默认值为False。值为False时，连接自动关闭；为True时，触发ChannelInboundHandler的userEventTriggered()方法，事件为ChannelInputShutdownEvent。
            serverBootstrap.childOption(ChannelOption.TCP_NODELAY, true);//立即发送数据，默认值为Ture
            serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);//连接保活，默认值为False。启用该功能时，TCP会主动探测空闲连接的有效性。可以将此功能视为TCP的心跳机制
            //客户端连接之后触发方法 initChannel()
            serverBootstrap.childHandler(new NettyServerInitializer());

//            serverBootstrap.setPipelineFactory(new HttpServerPipelineFactory());
            // 绑定端口,开始接收进来的连接
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();//绑定并侦听某个端口
            log.info("netty服务启动: [port:" + port + "]");
            // 等待服务器socket关闭
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("netty服务启动异常-" + e.getMessage() + e.getLocalizedMessage());
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            long t2 = System.currentTimeMillis();
            System.out.println("启动netty:" + (t2 - t1));
        }
    }

}