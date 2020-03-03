package com.jstun.core.attribute;

import com.jstun.core.attribute.MessageAttributeInterface;
import com.jstun.core.util.Utility;
import com.jstun.core.util.UtilityException;

public class ChangeRequest extends MessageAttribute {

    /* renamed from: a  reason: collision with root package name */
    boolean f6198a = false;
    boolean b = false;

    public ChangeRequest() {
        super(MessageAttributeInterface.MessageAttributeType.ChangeRequest);
    }

    public boolean a() {
        return this.f6198a;
    }

    public boolean b() {
        return this.b;
    }

    public void c() {
        this.f6198a = true;
    }

    public void d() {
        this.b = true;
    }

    public byte[] e() throws UtilityException {
        byte[] bArr = new byte[8];
        System.arraycopy(Utility.b(b(this.c)), 0, bArr, 0, 2);
        System.arraycopy(Utility.b(4), 0, bArr, 2, 2);
        if (this.f6198a) {
            bArr[7] = Utility.a(4);
        }
        if (this.b) {
            bArr[7] = Utility.a(2);
        }
        if (this.f6198a && this.b) {
            bArr[7] = Utility.a(6);
        }
        return bArr;
    }

    public static ChangeRequest a(byte[] bArr) throws MessageAttributeParsingException {
        try {
            if (bArr.length >= 4) {
                ChangeRequest changeRequest = new ChangeRequest();
                int a2 = Utility.a(bArr[3]);
                if (a2 != 0) {
                    if (a2 == 2) {
                        changeRequest.d();
                    } else if (a2 == 4) {
                        changeRequest.c();
                    } else if (a2 == 6) {
                        changeRequest.c();
                        changeRequest.d();
                    } else {
                        throw new MessageAttributeParsingException("Status parsing error");
                    }
                }
                return changeRequest;
            }
            throw new MessageAttributeParsingException("Data array too short");
        } catch (UtilityException unused) {
            throw new MessageAttributeParsingException("Parsing error");
        }
    }
}
