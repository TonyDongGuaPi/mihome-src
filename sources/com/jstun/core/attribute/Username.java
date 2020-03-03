package com.jstun.core.attribute;

import com.jstun.core.attribute.MessageAttributeInterface;
import com.jstun.core.util.Utility;
import com.jstun.core.util.UtilityException;

public class Username extends MessageAttribute {

    /* renamed from: a  reason: collision with root package name */
    String f6205a;

    public Username() {
        super(MessageAttributeInterface.MessageAttributeType.Username);
    }

    public Username(String str) {
        super(MessageAttributeInterface.MessageAttributeType.Username);
        a(str);
    }

    public String a() {
        return this.f6205a;
    }

    public void a(String str) {
        this.f6205a = str;
    }

    public byte[] e() throws UtilityException {
        int length = this.f6205a.length();
        int i = length % 4;
        if (i != 0) {
            length += 4 - i;
        }
        int i2 = length + 4;
        byte[] bArr = new byte[i2];
        System.arraycopy(Utility.b(b(this.c)), 0, bArr, 0, 2);
        System.arraycopy(Utility.b(i2 - 4), 0, bArr, 2, 2);
        byte[] bytes = this.f6205a.getBytes();
        System.arraycopy(bytes, 0, bArr, 4, bytes.length);
        return bArr;
    }

    public static Username a(byte[] bArr) {
        Username username = new Username();
        username.a(new String(bArr));
        return username;
    }
}
