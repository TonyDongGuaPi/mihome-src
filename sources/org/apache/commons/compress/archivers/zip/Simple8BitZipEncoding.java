package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.CharCompanionObject;

class Simple8BitZipEncoding implements ZipEncoding {

    /* renamed from: a  reason: collision with root package name */
    private final char[] f3263a;
    private final List<Simple8BitChar> b;

    private static final class Simple8BitChar implements Comparable<Simple8BitChar> {

        /* renamed from: a  reason: collision with root package name */
        public final char f3264a;
        public final byte b;

        Simple8BitChar(byte b2, char c) {
            this.b = b2;
            this.f3264a = c;
        }

        /* renamed from: a */
        public int compareTo(Simple8BitChar simple8BitChar) {
            return this.f3264a - simple8BitChar.f3264a;
        }

        public String toString() {
            return "0x" + Integer.toHexString(this.f3264a & CharCompanionObject.b) + "->0x" + Integer.toHexString(this.b & 255);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Simple8BitChar)) {
                return false;
            }
            Simple8BitChar simple8BitChar = (Simple8BitChar) obj;
            if (this.f3264a == simple8BitChar.f3264a && this.b == simple8BitChar.b) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return this.f3264a;
        }
    }

    public Simple8BitZipEncoding(char[] cArr) {
        this.f3263a = (char[]) cArr.clone();
        ArrayList arrayList = new ArrayList(this.f3263a.length);
        byte b2 = Byte.MAX_VALUE;
        for (char simple8BitChar : this.f3263a) {
            b2 = (byte) (b2 + 1);
            arrayList.add(new Simple8BitChar(b2, simple8BitChar));
        }
        Collections.sort(arrayList);
        this.b = Collections.unmodifiableList(arrayList);
    }

    public char a(byte b2) {
        return b2 >= 0 ? (char) b2 : this.f3263a[b2 + 128];
    }

    public boolean a(char c) {
        return (c >= 0 && c < 128) || b(c) != null;
    }

    public boolean a(ByteBuffer byteBuffer, char c) {
        if (c < 0 || c >= 128) {
            Simple8BitChar b2 = b(c);
            if (b2 == null) {
                return false;
            }
            byteBuffer.put(b2.b);
            return true;
        }
        byteBuffer.put((byte) c);
        return true;
    }

    private Simple8BitChar b(char c) {
        int size = this.b.size();
        int i = 0;
        while (size > i) {
            int i2 = ((size - i) / 2) + i;
            Simple8BitChar simple8BitChar = this.b.get(i2);
            if (simple8BitChar.f3264a == c) {
                return simple8BitChar;
            }
            if (simple8BitChar.f3264a < c) {
                i = i2 + 1;
            } else {
                size = i2;
            }
        }
        if (i >= this.b.size()) {
            return null;
        }
        Simple8BitChar simple8BitChar2 = this.b.get(i);
        if (simple8BitChar2.f3264a != c) {
            return null;
        }
        return simple8BitChar2;
    }

    public boolean a(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!a(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public ByteBuffer b(String str) {
        ByteBuffer allocate = ByteBuffer.allocate(str.length() + 6 + ((str.length() + 1) / 2));
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (allocate.remaining() < 6) {
                allocate = ZipEncodingHelper.a(allocate, allocate.position() + 6);
            }
            if (!a(allocate, charAt)) {
                ZipEncodingHelper.a(allocate, charAt);
            }
        }
        allocate.limit(allocate.position());
        allocate.rewind();
        return allocate;
    }

    public String a(byte[] bArr) throws IOException {
        char[] cArr = new char[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            cArr[i] = a(bArr[i]);
        }
        return new String(cArr);
    }
}
