package com.jstun.core.attribute;

import com.jstun.core.attribute.MessageAttributeInterface;
import java.util.logging.Logger;

public class MappedAddress extends MappedResponseChangedSourceAddressReflectedFrom {
    private static Logger p = Logger.getLogger("com.jstun.core.attribute.MappedAddress");

    public MappedAddress() {
        super(MessageAttributeInterface.MessageAttributeType.MappedAddress);
    }

    public static MessageAttribute a(byte[] bArr) throws MessageAttributeParsingException {
        MappedAddress mappedAddress = new MappedAddress();
        MappedResponseChangedSourceAddressReflectedFrom.a(mappedAddress, bArr);
        Logger logger = p;
        logger.finer("Message Attribute: Mapped Address parsed: " + mappedAddress.toString() + ".");
        return mappedAddress;
    }
}
