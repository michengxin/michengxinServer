package org.springboot.leetcode;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.StandardSocketOptions;

/**
 * @ClassName ConnectHandler
 * @Description TODO
 * @Author mcx
 * @Date 2020/7/29 14:08
 * @Version 1.0
 */
public class ConnectHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {   //1

        System.out.println(
                "Client " + ctx.channel().remoteAddress() + " connected");
        System.out.println("连接成功..");
    }
}