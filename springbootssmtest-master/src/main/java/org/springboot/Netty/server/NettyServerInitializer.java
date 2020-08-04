package org.springboot.Netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;


/**
 * @ClassName NettyServerInitializer
 * @Description TODO
 * @Author mcx
 * @Date 2020/8/3 10:16
 * @Version 1.0
 */
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {


    /**
     * 初始化channel
     */
    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        System.out.println("nettyServerChannel开始初始化了!!");
        long t1=System.currentTimeMillis();
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
        pipeline.addLast(new NettyServerHandler());
        long t2=System.currentTimeMillis();
        System.out.println("初始化netty channnel耗时:"+(t2-t1));
    }
}