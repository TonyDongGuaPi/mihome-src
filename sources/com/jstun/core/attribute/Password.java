package com.jstun.core.attribute;

import com.jstun.core.attribute.MessageAttributeInterface;
import com.jstun.core.util.Utility;
import com.jstun.core.util.UtilityException;

public class Password extends MessageAttribute {

    /* renamed from: a  reason: collision with root package name */
    String f6203a;

    public Password() {
        super(MessageAttributeInterface.MessageAttributeType.Password);
    }

    public Password(String str) {
        super(MessageAttributeInterface.MessageAttributeType.Password);
        a(str);
    }

    public String a() {
        return this.f6203a;
    }

    public void a(String str) {
        this.f6203a = str;
    }

    public byte[] e() throws UtilityException {
        int length = this.f6203a.length();
        int i = length % 4;
        if (i != 0) {
            length += 4 - i;
        }
        int i2 = length + 4;
        byte[] bArr = new byte[i2];
        System.arraycopy(Utility.b(b(this.c)), 0, bArr, 0, 2);
        System.arraycopy(Utility.b(i2 - 4), 0, bArr, 2, 2);
        byte[] bytes = this.f6203a.getBytes();
        System.arraycopy(bytes, 0, bArr, 4, bytes.length);
        return bArr;
    }

    public static Password a(byte[] bArr) {
        Password password = new Password();
        password.a(new String(bArr));
        return password;
    }
}
