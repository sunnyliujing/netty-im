package com.xlef.client.codec;

import com.xlef.common.CommonMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

public class MessageProtocolEncoder extends MessageToMessageEncoder<CommonMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, CommonMessage commonMessage, List<Object> list) throws Exception {
        ByteBuf buffer = ctx.alloc().buffer();
        commonMessage.encode(buffer);
        list.add(buffer);
    }
}
