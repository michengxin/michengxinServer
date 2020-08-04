package org.springboot.Netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import org.springboot.entity.Permission;
import org.springboot.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName NettyClientInitializer
 * @Description TODO
 * @Author mcx
 * @Date 2020/8/3 10:45
 * @Version 1.0
 */
public class NettyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Autowired
    UserService userService;
    /**
     * 初始化channel
     */
    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
//        List<Permission> permissionList=  userService.selectView();
        pipeline.addLast(new NettyClientHandler());
    }


}