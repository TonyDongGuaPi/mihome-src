package com.jstun.core.attribute;

import com.jstun.core.attribute.MessageAttributeInterface;
import java.util.logging.Logger;

public class ChangedAddress extends MappedResponseChangedSourceAddressReflectedFrom {
    private static Logger p = Logger.getLogger("com.jstun.core.attribute.ChangedAddress");

    public ChangedAddress() {
        super(MessageAttributeInterface.MessageAttributeType.ChangedAddress);
    }

    public static MessageAttribute a(byte[] bArr) throws MessageAttributeParsingException {
        ChangedAddress changedAddress = new ChangedAddress();
        MappedResponseChangedSourceAddressReflectedFrom.a(changedAddress, bArr);
        Logger logger = p;
        logger.finer("Message Attribute: Changed Address parsed: " + changedAddress.toString() + ".");
        return changedAddress;
    }
}
