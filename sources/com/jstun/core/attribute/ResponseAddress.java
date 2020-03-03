package com.jstun.core.attribute;

import com.jstun.core.attribute.MessageAttributeInterface;
import java.util.logging.Logger;

public class ResponseAddress extends MappedResponseChangedSourceAddressReflectedFrom {
    private static Logger p = Logger.getLogger("com.jstun.core.attribute.ResponseAddress");

    public ResponseAddress() {
        super(MessageAttributeInterface.MessageAttributeType.ResponseAddress);
    }

    public static MessageAttribute a(byte[] bArr) throws MessageAttributeParsingException {
        ResponseAddress responseAddress = new ResponseAddress();
        MappedResponseChangedSourceAddressReflectedFrom.a(responseAddress, bArr);
        Logger logger = p;
        logger.finer("Message Attribute: Response Address parsed: " + responseAddress.toString() + ".");
        return responseAddress;
    }
}
