package com.xlef.message;

import com.xlef.common.MessageBody;
import lombok.Data;

@Data
public class ReplyMessageBody extends MessageBody {
    long streamId;
    long status;
    String statusText;
}
