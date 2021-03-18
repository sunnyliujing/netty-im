package com.xlef.common;

import com.xlef.message.NormalMessageBody;
import com.xlef.message.ReplyMessageBody;

public enum MessageType {
    MESSAGE(1, NormalMessageBody.class),
    REPLY(2,ReplyMessageBody.class);

    private int opCode;
    private Class<? extends MessageBody> messageClazz;

    MessageType(int opCode,Class<? extends MessageBody> messageClazz){
        this.opCode = opCode;
        this.messageClazz = messageClazz;
    }

    public int getOpCode(){
        return opCode;
    }

    public Class<? extends MessageBody> getMessageClazz(){
        return messageClazz;
    }

    public  static Class<? extends MessageBody> fromOpCode(int opCode){
        for(MessageType type:MessageType.values()){
            if(type.opCode==opCode){
                return type.messageClazz;
            }
        }
        return null;
    }

    public static int fromMessageBody(MessageBody body){
        for(MessageType type:MessageType.values()){
            if(type.messageClazz==body.getClass()){
                return type.opCode;
            }
        }
        return -1;
    }
}
