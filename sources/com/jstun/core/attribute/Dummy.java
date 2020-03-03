package com.jstun.core.attribute;

import com.jstun.core.attribute.MessageAttributeInterface;
import com.jstun.core.util.Utility;
import com.jstun.core.util.UtilityException;

public class Dummy extends MessageAttribute {

    /* renamed from: a  reason: collision with root package name */
    int f6199a;

    public Dummy() {
        super(MessageAttributeInterface.MessageAttributeType.Dummy);
    }

    public void a(int i) {
        this.f6199a = i;
    }

    public byte[] e() throws UtilityException {
        byte[] bArr = new byte[(this.f6199a + 4)];
        System.arraycopy(Utility.b(b(this.c)), 0, bArr, 0, 2);
        System.arraycopy(Utility.b(this.f6199a), 0, bArr, 2, 2);
        return bArr;
    }

    public static Dummy a(byte[] bArr) {
        Dummy dummy = new Dummy();
        dummy.a(bArr.length);
        return dummy;
    }
}
