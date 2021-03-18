package com.xlef.common;

public class CommonMessage extends Message<MessageBody>{
    @Override
    public Class getMessageBodyDecodeClass(int opCode) {
        return MessageType.fromOpCode(opCode);
    }

    public CommonMessage(){

    }

    public CommonMessage(Long streamId,MessageBody messageBody){
        MessageHeader header = new MessageHeader();
        header.setStreamId(streamId);
        header.setOpCode(MessageType.fromMessageBody(messageBody));
        this.setMessageHeader(header);
        this.setMessageBody(messageBody);
    }
}
