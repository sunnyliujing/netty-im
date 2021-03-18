package com.xlef.handler;

import com.xlef.common.CommonMessage;
import com.xlef.common.MessageBody;
import com.xlef.common.MessageHeader;
import com.xlef.common.MessageType;
import com.xlef.message.ReplyMessageBody;
import com.xlef.utils.IdUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageServerProcessHandler extends SimpleChannelInboundHandler<CommonMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CommonMessage commonMessage) throws Exception {
        MessageBody messageBody = commonMessage.getMessageBody();
        MessageHeader header = commonMessage.getMessageHeader();

        if(MessageType.MESSAGE.getMessageClazz()==messageBody.getClass()){
            ReplyMessageBody replyMessageBody = new ReplyMessageBody();
            replyMessageBody.setStatus(0);
            replyMessageBody.setStatusText("success");
            replyMessageBody.setStreamId(header.getStreamId());
            CommonMessage message = new CommonMessage(IdUtil.nextId(),replyMessageBody);
            ctx.writeAndFlush(message);
        }

    }
}
