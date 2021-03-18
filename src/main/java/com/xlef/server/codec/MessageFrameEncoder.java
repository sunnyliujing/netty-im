package com.xlef.server.codec;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

public class MessageFrameEncoder extends LengthFieldPrepender {
    public MessageFrameEncoder() {
        super(2);
    }
}
