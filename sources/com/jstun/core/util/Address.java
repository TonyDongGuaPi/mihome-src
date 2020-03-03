package com.jstun.core.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

public class Address {

    /* renamed from: a  reason: collision with root package name */
    int f6207a;
    int b;
    int c;
    int d;

    public Address(int i, int i2, int i3, int i4) throws UtilityException {
        if (i < 0 || i > 255 || i2 < 0 || i2 > 255 || i3 < 0 || i3 > 255 || i4 < 0 || i4 > 255) {
            throw new UtilityException("Address is malformed.");
        }
        this.f6207a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    public Address(String str) throws UtilityException {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ".");
        if (stringTokenizer.countTokens() == 4) {
            int i = 0;
            while (stringTokenizer.hasMoreTokens()) {
                int parseInt = Integer.parseInt(stringTokenizer.nextToken());
                if (parseInt >= 0 && parseInt <= 255) {
                    switch (i) {
                        case 0:
                            this.f6207a = parseInt;
                            i++;
                            break;
                        case 1:
                            this.b = parseInt;
                            i++;
                            break;
                        case 2:
                            this.c = parseInt;
                            i++;
                            break;
                        case 3:
                            this.d = parseInt;
                            i++;
                            break;
                    }
                } else {
                    throw new UtilityException("Address is in incorrect format.");
                }
            }
            return;
        }
        throw new UtilityException("4 octets in address string are required.");
    }

    public Address(byte[] bArr) throws UtilityException {
        if (bArr.length >= 4) {
            this.f6207a = Utility.a(bArr[0]);
            this.b = Utility.a(bArr[1]);
            this.c = Utility.a(bArr[2]);
            this.d = Utility.a(bArr[3]);
            return;
        }
        throw new UtilityException("4 bytes are required.");
    }

    public String toString() {
        return this.f6207a + "." + this.b + "." + this.c + "." + this.d;
    }

    public byte[] a() throws UtilityException {
        return new byte[]{Utility.a(this.f6207a), Utility.a(this.b), Utility.a(this.c), Utility.a(this.d)};
    }

    public InetAddress b() throws UtilityException, UnknownHostException {
        return InetAddress.getByAddress(new byte[]{Utility.a(this.f6207a), Utility.a(this.b), Utility.a(this.c), Utility.a(this.d)});
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            byte[] a2 = a();
            byte[] a3 = ((Address) obj).a();
            if (a2[0] == a3[0] && a2[1] == a3[1] && a2[2] == a3[2] && a2[3] == a3[3]) {
                return true;
            }
            return false;
        } catch (UtilityException unused) {
            return false;
        }
    }

    public int hashCode() {
        return (this.f6207a << 24) + (this.b << 16) + (this.c << 8) + this.d;
    }
}
