package com.jstun.core.attribute;

import com.jstun.core.attribute.MessageAttributeInterface;

public class UnknownMessageAttributeException extends MessageAttributeParsingException {
    private static final long serialVersionUID = 5375193544145543299L;
    private MessageAttributeInterface.MessageAttributeType type;

    public UnknownMessageAttributeException(String str, MessageAttributeInterface.MessageAttributeType messageAttributeType) {
        super(str);
        this.type = messageAttributeType;
    }

    public MessageAttributeInterface.MessageAttributeType getType() {
        return this.type;
    }
}
