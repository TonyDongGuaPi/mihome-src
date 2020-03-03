package com.jstun.core.attribute;

import com.jstun.core.attribute.MessageAttributeInterface;
import com.jstun.core.util.Address;
import com.jstun.core.util.Utility;
import com.jstun.core.util.UtilityException;

public class MappedResponseChangedSourceAddressReflectedFrom extends MessageAttribute {

    /* renamed from: a  reason: collision with root package name */
    int f6201a;
    Address b;

    public MappedResponseChangedSourceAddressReflectedFrom() {
        try {
            this.f6201a = 0;
            this.b = new Address("0.0.0.0");
        } catch (UtilityException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public MappedResponseChangedSourceAddressReflectedFrom(MessageAttributeInterface.MessageAttributeType messageAttributeType) {
        super(messageAttributeType);
    }

    public int a() {
        return this.f6201a;
    }

    public Address b() {
        return this.b;
    }

    public void a(int i) throws MessageAttributeException {
        if (i > 65536 || i < 0) {
            throw new MessageAttributeException("Port value " + i + " out of range.");
        }
        this.f6201a = i;
    }

    public void a(Address address) {
        this.b = address;
    }

    public byte[] e() throws UtilityException {
        byte[] bArr = new byte[12];
        System.arraycopy(Utility.b(b(this.c)), 0, bArr, 0, 2);
        System.arraycopy(Utility.b(8), 0, bArr, 2, 2);
        bArr[5] = Utility.a(1);
        System.arraycopy(Utility.b(this.f6201a), 0, bArr, 6, 2);
        System.arraycopy(this.b.a(), 0, bArr, 8, 4);
        return bArr;
    }

    protected static MappedResponseChangedSourceAddressReflectedFrom a(MappedResponseChangedSourceAddressReflectedFrom mappedResponseChangedSourceAddressReflectedFrom, byte[] bArr) throws MessageAttributeParsingException {
        try {
            if (bArr.length >= 8) {
                int a2 = Utility.a(bArr[1]);
                if (a2 == 1) {
                    byte[] bArr2 = new byte[2];
                    System.arraycopy(bArr, 2, bArr2, 0, 2);
                    mappedResponseChangedSourceAddressReflectedFrom.a(Utility.a(bArr2));
                    mappedResponseChangedSourceAddressReflectedFrom.a(new Address(Utility.a(bArr[4]), Utility.a(bArr[5]), Utility.a(bArr[6]), Utility.a(bArr[7])));
                    return mappedResponseChangedSourceAddressReflectedFrom;
                }
                throw new MessageAttributeParsingException("Family " + a2 + " is not supported");
            }
            throw new MessageAttributeParsingException("Data array too short");
        } catch (UtilityException unused) {
            throw new MessageAttributeParsingException("Parsing error");
        } catch (MessageAttributeException unused2) {
            throw new MessageAttributeParsingException("Port parsing error");
        }
    }

    public String toString() {
        return "Address " + this.b.toString() + ", Port " + this.f6201a;
    }
}
