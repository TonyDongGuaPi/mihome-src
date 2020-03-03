package com.jstun.core.attribute;

import com.jstun.core.attribute.MessageAttributeInterface;
import java.util.logging.Logger;

public class SourceAddress extends MappedResponseChangedSourceAddressReflectedFrom {
    private static Logger p = Logger.getLogger("com.jstun.core.attribute.SourceAddress");

    public SourceAddress() {
        super(MessageAttributeInterface.MessageAttributeType.SourceAddress);
    }

    public static MessageAttribute a(byte[] bArr) throws MessageAttributeParsingException {
        SourceAddress sourceAddress = new SourceAddress();
        MappedResponseChangedSourceAddressReflectedFrom.a(sourceAddress, bArr);
        Logger logger = p;
        logger.finer("Message Attribute: Source Address parsed: " + sourceAddress.toString() + ".");
        return sourceAddress;
    }
}
