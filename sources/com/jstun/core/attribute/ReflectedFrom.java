package com.jstun.core.attribute;

import com.jstun.core.attribute.MessageAttributeInterface;
import java.util.logging.Logger;

public class ReflectedFrom extends MappedResponseChangedSourceAddressReflectedFrom {
    private static Logger p = Logger.getLogger("com.jstun.core.attribute.ReflectedFrom");

    public ReflectedFrom() {
        super(MessageAttributeInterface.MessageAttributeType.ReflectedFrom);
    }

    public static ReflectedFrom a(byte[] bArr) throws MessageAttributeParsingException {
        ReflectedFrom reflectedFrom = new ReflectedFrom();
        MappedResponseChangedSourceAddressReflectedFrom.a(reflectedFrom, bArr);
        Logger logger = p;
        logger.finer("Message Attribute: ReflectedFrom parsed: " + reflectedFrom.toString() + ".");
        return reflectedFrom;
    }
}
