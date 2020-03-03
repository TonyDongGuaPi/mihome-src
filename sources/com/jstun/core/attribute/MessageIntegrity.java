package com.jstun.core.attribute;

import com.jstun.core.attribute.MessageAttributeInterface;

public class MessageIntegrity extends MessageAttribute {
    public MessageIntegrity() {
        super(MessageAttributeInterface.MessageAttributeType.MessageIntegrity);
    }

    public byte[] e() {
        return new byte[0];
    }

    public static MessageIntegrity a(byte[] bArr) {
        return new MessageIntegrity();
    }
}
