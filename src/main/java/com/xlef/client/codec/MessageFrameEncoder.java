package com.xlef.client.codec;

import io.netty.handler.codec.LengthFieldPrepender;

public class MessageFrameEncoder extends LengthFieldPrepender {
    public MessageFrameEncoder() {
        super(2);
    }
}
