package org.springboot.Netty.server;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.Map;

/**
 * @ClassName NettyServerHandler
 * @Description TODO
 * @Author mcx
 * @Date 2020/8/3 10:18
 * @Version 1.0
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<String> {



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 当出现异常就关闭连接
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress()+"溜了");
        ctx.close();
    }
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);//单例模式

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println("开始读取...");
        Channel channel = channelHandlerContext.channel();
        System.out.println(channel.remoteAddress()+":"+s);
        System.out.println("结束读取...");
        channelGroup.forEach(ch -> {
            if(channel != ch){
                ch.writeAndFlush(ch.remoteAddress() + "发送的消息是" + s+"\n");
            }else{
                ch.writeAndFlush("【自己】：" + s +"\n");
            }
        });
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("【服务器】 - " + channel.remoteAddress() + "加入\n");
        channelGroup.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //channelGroup.remove(channel);  //netty自动删除
        channelGroup.writeAndFlush("【服务器】 - " + channel.remoteAddress() + "断开连接\n");
        channelGroup.add(channel);
        System.out.println(channelGroup.size());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "上线\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "下线\n");
    }

//    @Override
////    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
////        cause.printStackTrace();
////        ctx.close();
////    }
}