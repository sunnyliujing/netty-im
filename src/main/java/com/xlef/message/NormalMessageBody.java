package com.xlef.message;

import com.xlef.common.Message;
import com.xlef.common.MessageBody;
import com.xlef.common.MessageType;
import lombok.Data;

@Data
public class NormalMessageBody extends MessageBody {
    private String from;
    private String to;
    private String content;
}
