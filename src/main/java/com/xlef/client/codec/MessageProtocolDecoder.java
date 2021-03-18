package com.xlef.client.codec;

import com.xlef.common.CommonMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class MessageProtocolDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        CommonMessage commonMessage = new CommonMessage();
        commonMessage.decode(byteBuf);
        list.add(commonMessage);
    }
}
