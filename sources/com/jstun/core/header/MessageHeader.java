package com.jstun.core.header;

import com.jstun.core.attribute.MessageAttribute;
import com.jstun.core.attribute.MessageAttributeInterface;
import com.jstun.core.attribute.MessageAttributeParsingException;
import com.jstun.core.header.MessageHeaderInterface;
import com.jstun.core.util.Utility;
import com.jstun.core.util.UtilityException;
import java.util.TreeMap;
import java.util.logging.Logger;

public class MessageHeader implements MessageHeaderInterface {
    private static Logger j = Logger.getLogger("com.jstun.core.header.MessageHeader");

    /* renamed from: a  reason: collision with root package name */
    MessageHeaderInterface.MessageHeaderType f6206a;
    byte[] b = new byte[16];
    TreeMap<MessageAttributeInterface.MessageAttributeType, MessageAttribute> c = new TreeMap<>();

    public MessageHeader() {
    }

    public MessageHeader(MessageHeaderInterface.MessageHeaderType messageHeaderType) {
        a(messageHeaderType);
    }

    public void a(MessageHeaderInterface.MessageHeaderType messageHeaderType) {
        this.f6206a = messageHeaderType;
    }

    public MessageHeaderInterface.MessageHeaderType a() {
        return this.f6206a;
    }

    public static int b(MessageHeaderInterface.MessageHeaderType messageHeaderType) {
        if (messageHeaderType == MessageHeaderInterface.MessageHeaderType.BindingRequest) {
            return 1;
        }
        if (messageHeaderType == MessageHeaderInterface.MessageHeaderType.BindingResponse) {
            return 257;
        }
        if (messageHeaderType == MessageHeaderInterface.MessageHeaderType.BindingErrorResponse) {
            return 273;
        }
        if (messageHeaderType == MessageHeaderInterface.MessageHeaderType.SharedSecretRequest) {
            return 2;
        }
        if (messageHeaderType == MessageHeaderInterface.MessageHeaderType.SharedSecretResponse) {
            return 258;
        }
        return messageHeaderType == MessageHeaderInterface.MessageHeaderType.SharedSecretErrorResponse ? 274 : -1;
    }

    public void a(byte[] bArr) {
        System.arraycopy(bArr, 0, this.b, 0, 16);
    }

    public void b() throws UtilityException {
        System.arraycopy(Utility.b((int) (Math.random() * 65536.0d)), 0, this.b, 0, 2);
        System.arraycopy(Utility.b((int) (Math.random() * 65536.0d)), 0, this.b, 2, 2);
        System.arraycopy(Utility.b((int) (Math.random() * 65536.0d)), 0, this.b, 4, 2);
        System.arraycopy(Utility.b((int) (Math.random() * 65536.0d)), 0, this.b, 6, 2);
        System.arraycopy(Utility.b((int) (Math.random() * 65536.0d)), 0, this.b, 8, 2);
        System.arraycopy(Utility.b((int) (Math.random() * 65536.0d)), 0, this.b, 10, 2);
        System.arraycopy(Utility.b((int) (Math.random() * 65536.0d)), 0, this.b, 12, 2);
        System.arraycopy(Utility.b((int) (Math.random() * 65536.0d)), 0, this.b, 14, 2);
    }

    public byte[] c() {
        byte[] bArr = new byte[this.b.length];
        System.arraycopy(this.b, 0, bArr, 0, this.b.length);
        return bArr;
    }

    public boolean a(MessageHeader messageHeader) {
        byte[] c2 = messageHeader.c();
        if (c2.length == 16 && c2[0] == this.b[0] && c2[1] == this.b[1] && c2[2] == this.b[2] && c2[3] == this.b[3] && c2[4] == this.b[4] && c2[5] == this.b[5] && c2[6] == this.b[6] && c2[7] == this.b[7] && c2[8] == this.b[8] && c2[9] == this.b[9] && c2[10] == this.b[10] && c2[11] == this.b[11] && c2[12] == this.b[12] && c2[13] == this.b[13] && c2[14] == this.b[14] && c2[15] == this.b[15]) {
            return true;
        }
        return false;
    }

    public void a(MessageAttribute messageAttribute) {
        this.c.put(messageAttribute.f(), messageAttribute);
    }

    public MessageAttribute a(MessageAttributeInterface.MessageAttributeType messageAttributeType) {
        return this.c.get(messageAttributeType);
    }

    public byte[] d() throws UtilityException {
        int i = 20;
        int i2 = 20;
        for (MessageAttributeInterface.MessageAttributeType messageAttributeType : this.c.keySet()) {
            i2 += this.c.get(messageAttributeType).g();
        }
        byte[] bArr = new byte[i2];
        System.arraycopy(Utility.b(b(this.f6206a)), 0, bArr, 0, 2);
        System.arraycopy(Utility.b(i2 - 20), 0, bArr, 2, 2);
        System.arraycopy(this.b, 0, bArr, 4, 16);
        for (MessageAttributeInterface.MessageAttributeType messageAttributeType2 : this.c.keySet()) {
            MessageAttribute messageAttribute = this.c.get(messageAttributeType2);
            System.arraycopy(messageAttribute.e(), 0, bArr, i, messageAttribute.g());
            i += messageAttribute.g();
        }
        return bArr;
    }

    public int e() throws UtilityException {
        return d().length;
    }

    public void b(byte[] bArr) throws MessageAttributeParsingException {
        try {
            byte[] bArr2 = new byte[2];
            System.arraycopy(bArr, 2, bArr2, 0, 2);
            int a2 = Utility.a(bArr2);
            System.arraycopy(bArr, 4, this.b, 0, 16);
            int i = 20;
            while (a2 > 0) {
                byte[] bArr3 = new byte[a2];
                System.arraycopy(bArr, i, bArr3, 0, a2);
                MessageAttribute b2 = MessageAttribute.b(bArr3);
                a(b2);
                a2 -= b2.g();
                i += b2.g();
            }
        } catch (UtilityException unused) {
            throw new MessageAttributeParsingException("Parsing error");
        }
    }

    public static MessageHeader c(byte[] bArr) throws MessageHeaderParsingException {
        try {
            MessageHeader messageHeader = new MessageHeader();
            byte[] bArr2 = new byte[2];
            System.arraycopy(bArr, 0, bArr2, 0, 2);
            int a2 = Utility.a(bArr2);
            switch (a2) {
                case 1:
                    messageHeader.a(MessageHeaderInterface.MessageHeaderType.BindingRequest);
                    j.finer("Binding Request received.");
                    break;
                case 2:
                    messageHeader.a(MessageHeaderInterface.MessageHeaderType.SharedSecretRequest);
                    j.finer("Shared Secret Request received.");
                    break;
                case 257:
                    messageHeader.a(MessageHeaderInterface.MessageHeaderType.BindingResponse);
                    j.finer("Binding Response received.");
                    break;
                case 258:
                    messageHeader.a(MessageHeaderInterface.MessageHeaderType.SharedSecretResponse);
                    j.finer("Shared Secret Response received.");
                    break;
                case 273:
                    messageHeader.a(MessageHeaderInterface.MessageHeaderType.BindingErrorResponse);
                    j.finer("Binding Error Response received.");
                    break;
                case 274:
                    messageHeader.a(MessageHeaderInterface.MessageHeaderType.SharedSecretErrorResponse);
                    j.finer("Shared Secret Error Response received.");
                    break;
                default:
                    throw new MessageHeaderParsingException("Message type " + a2 + "is not supported");
            }
            return messageHeader;
        } catch (UtilityException unused) {
            throw new MessageHeaderParsingException("Parsing error");
        }
    }
}
