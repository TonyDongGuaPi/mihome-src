package com.jstun.core.attribute;

import com.jstun.core.attribute.MessageAttributeInterface;
import com.jstun.core.util.Utility;
import com.jstun.core.util.UtilityException;
import java.util.Iterator;
import java.util.Vector;

public class UnknownAttribute extends MessageAttribute {

    /* renamed from: a  reason: collision with root package name */
    Vector<MessageAttributeInterface.MessageAttributeType> f6204a = new Vector<>();

    public UnknownAttribute() {
        super(MessageAttributeInterface.MessageAttributeType.UnknownAttribute);
    }

    public void c(MessageAttributeInterface.MessageAttributeType messageAttributeType) {
        this.f6204a.add(messageAttributeType);
    }

    public byte[] e() throws UtilityException {
        int i;
        if (this.f6204a.size() % 2 == 1) {
            i = ((this.f6204a.size() + 1) * 2) + 4;
        } else {
            i = (this.f6204a.size() * 2) + 4;
        }
        byte[] bArr = new byte[i];
        System.arraycopy(Utility.b(b(this.c)), 0, bArr, 0, 2);
        System.arraycopy(Utility.b(i - 4), 0, bArr, 2, 2);
        Iterator<MessageAttributeInterface.MessageAttributeType> it = this.f6204a.iterator();
        while (it.hasNext()) {
            System.arraycopy(Utility.b(b(it.next())), 0, bArr, 4, 2);
        }
        if (this.f6204a.size() % 2 == 1) {
            System.arraycopy(Utility.b(b(this.f6204a.elementAt(1))), 0, bArr, 4, 2);
        }
        return bArr;
    }

    public static UnknownAttribute a(byte[] bArr) throws MessageAttributeParsingException {
        try {
            UnknownAttribute unknownAttribute = new UnknownAttribute();
            if (bArr.length % 4 == 0) {
                for (int i = 0; i < bArr.length; i += 4) {
                    byte[] bArr2 = new byte[4];
                    System.arraycopy(bArr, i, bArr2, 0, 4);
                    unknownAttribute.c(MessageAttribute.a(Utility.b(bArr2)));
                }
                return unknownAttribute;
            }
            throw new MessageAttributeParsingException("Data array too short");
        } catch (UtilityException unused) {
            throw new MessageAttributeParsingException("Parsing error");
        }
    }
}
