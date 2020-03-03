package com.jstun.core.attribute;

import com.jstun.core.attribute.MessageAttributeInterface;
import com.jstun.core.util.Utility;
import com.jstun.core.util.UtilityException;
import java.util.logging.Logger;

public abstract class MessageAttribute implements MessageAttributeInterface {

    /* renamed from: a  reason: collision with root package name */
    private static Logger f6202a = Logger.getLogger("com.jstun.core.util.MessageAttribute");
    MessageAttributeInterface.MessageAttributeType c;

    public abstract byte[] e() throws UtilityException;

    public MessageAttribute() {
    }

    public MessageAttribute(MessageAttributeInterface.MessageAttributeType messageAttributeType) {
        a(messageAttributeType);
    }

    public void a(MessageAttributeInterface.MessageAttributeType messageAttributeType) {
        this.c = messageAttributeType;
    }

    public MessageAttributeInterface.MessageAttributeType f() {
        return this.c;
    }

    public static int b(MessageAttributeInterface.MessageAttributeType messageAttributeType) {
        if (messageAttributeType == MessageAttributeInterface.MessageAttributeType.MappedAddress) {
            return 1;
        }
        if (messageAttributeType == MessageAttributeInterface.MessageAttributeType.ResponseAddress) {
            return 2;
        }
        if (messageAttributeType == MessageAttributeInterface.MessageAttributeType.ChangeRequest) {
            return 3;
        }
        if (messageAttributeType == MessageAttributeInterface.MessageAttributeType.SourceAddress) {
            return 4;
        }
        if (messageAttributeType == MessageAttributeInterface.MessageAttributeType.ChangedAddress) {
            return 5;
        }
        if (messageAttributeType == MessageAttributeInterface.MessageAttributeType.Username) {
            return 6;
        }
        if (messageAttributeType == MessageAttributeInterface.MessageAttributeType.Password) {
            return 7;
        }
        if (messageAttributeType == MessageAttributeInterface.MessageAttributeType.MessageIntegrity) {
            return 8;
        }
        if (messageAttributeType == MessageAttributeInterface.MessageAttributeType.ErrorCode) {
            return 9;
        }
        if (messageAttributeType == MessageAttributeInterface.MessageAttributeType.UnknownAttribute) {
            return 10;
        }
        if (messageAttributeType == MessageAttributeInterface.MessageAttributeType.ReflectedFrom) {
            return 11;
        }
        return messageAttributeType == MessageAttributeInterface.MessageAttributeType.Dummy ? 0 : -1;
    }

    public static MessageAttributeInterface.MessageAttributeType a(long j) {
        if (j == 1) {
            return MessageAttributeInterface.MessageAttributeType.MappedAddress;
        }
        if (j == 2) {
            return MessageAttributeInterface.MessageAttributeType.ResponseAddress;
        }
        if (j == 3) {
            return MessageAttributeInterface.MessageAttributeType.ChangeRequest;
        }
        if (j == 4) {
            return MessageAttributeInterface.MessageAttributeType.SourceAddress;
        }
        if (j == 5) {
            return MessageAttributeInterface.MessageAttributeType.ChangedAddress;
        }
        if (j == 6) {
            return MessageAttributeInterface.MessageAttributeType.Username;
        }
        if (j == 7) {
            return MessageAttributeInterface.MessageAttributeType.Password;
        }
        if (j == 8) {
            return MessageAttributeInterface.MessageAttributeType.MessageIntegrity;
        }
        if (j == 9) {
            return MessageAttributeInterface.MessageAttributeType.ErrorCode;
        }
        if (j == 10) {
            return MessageAttributeInterface.MessageAttributeType.UnknownAttribute;
        }
        if (j == 11) {
            return MessageAttributeInterface.MessageAttributeType.ReflectedFrom;
        }
        if (j == 0) {
            return MessageAttributeInterface.MessageAttributeType.Dummy;
        }
        return null;
    }

    public int g() throws UtilityException {
        return e().length;
    }

    public static MessageAttribute b(byte[] bArr) throws MessageAttributeParsingException {
        try {
            byte[] bArr2 = new byte[2];
            System.arraycopy(bArr, 0, bArr2, 0, 2);
            int a2 = Utility.a(bArr2);
            byte[] bArr3 = new byte[2];
            System.arraycopy(bArr, 2, bArr3, 0, 2);
            int a3 = Utility.a(bArr3);
            byte[] bArr4 = new byte[a3];
            System.arraycopy(bArr, 4, bArr4, 0, a3);
            switch (a2) {
                case 1:
                    return MappedAddress.a(bArr4);
                case 2:
                    return ResponseAddress.a(bArr4);
                case 3:
                    return ChangeRequest.a(bArr4);
                case 4:
                    return SourceAddress.a(bArr4);
                case 5:
                    return ChangedAddress.a(bArr4);
                case 6:
                    return Username.a(bArr4);
                case 7:
                    return Password.a(bArr4);
                case 8:
                    return MessageIntegrity.a(bArr4);
                case 9:
                    return ErrorCode.a(bArr4);
                case 10:
                    return UnknownAttribute.a(bArr4);
                case 11:
                    return ReflectedFrom.a(bArr4);
                default:
                    if (a2 > 32767) {
                        Logger logger = f6202a;
                        logger.finer("MessageAttribute with type " + a2 + " unkown.");
                        return Dummy.a(bArr4);
                    }
                    throw new UnknownMessageAttributeException("Unkown mandatory message attribute", a((long) a2));
            }
        } catch (UtilityException unused) {
            throw new MessageAttributeParsingException("Parsing error");
        }
    }
}
