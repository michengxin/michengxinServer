package org.springboot.Netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @ClassName NettyClientHandler
 * @Description TODO
 * @Author mcx
 * @Date 2020/8/3 10:46
 * @Version 1.0
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+"发送的信息是" + msg);
        System.out.println("收到服务端消息: " + msg);
    }


}