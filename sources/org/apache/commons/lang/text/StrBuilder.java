package org.apache.commons.lang.text;

import com.google.code.microlog4android.format.PatternFormatter;
import java.io.Reader;
import java.io.Writer;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.SystemUtils;

public class StrBuilder implements Cloneable {

    /* renamed from: a  reason: collision with root package name */
    static final int f3399a = 32;
    private static final long d = 7628716375283629643L;
    protected char[] b;
    protected int c;
    private String e;
    private String f;

    public StrBuilder() {
        this(32);
    }

    public StrBuilder(int i) {
        this.b = new char[(i <= 0 ? 32 : i)];
    }

    public StrBuilder(String str) {
        if (str == null) {
            this.b = new char[32];
            return;
        }
        this.b = new char[(str.length() + 32)];
        c(str);
    }

    public String a() {
        return this.e;
    }

    public StrBuilder a(String str) {
        this.e = str;
        return this;
    }

    public String b() {
        return this.f;
    }

    public StrBuilder b(String str) {
        if (str != null && str.length() == 0) {
            str = null;
        }
        this.f = str;
        return this;
    }

    public int c() {
        return this.c;
    }

    public StrBuilder a(int i) {
        if (i >= 0) {
            if (i < this.c) {
                this.c = i;
            } else if (i > this.c) {
                b(i);
                this.c = i;
                for (int i2 = this.c; i2 < i; i2++) {
                    this.b[i2] = 0;
                }
            }
            return this;
        }
        throw new StringIndexOutOfBoundsException(i);
    }

    public int d() {
        return this.b.length;
    }

    public StrBuilder b(int i) {
        if (i > this.b.length) {
            char[] cArr = this.b;
            this.b = new char[(i * 2)];
            System.arraycopy(cArr, 0, this.b, 0, this.c);
        }
        return this;
    }

    public StrBuilder e() {
        if (this.b.length > c()) {
            char[] cArr = this.b;
            this.b = new char[c()];
            System.arraycopy(cArr, 0, this.b, 0, this.c);
        }
        return this;
    }

    public int f() {
        return this.c;
    }

    public boolean g() {
        return this.c == 0;
    }

    public StrBuilder h() {
        this.c = 0;
        return this;
    }

    public char c(int i) {
        if (i >= 0 && i < c()) {
            return this.b[i];
        }
        throw new StringIndexOutOfBoundsException(i);
    }

    public StrBuilder a(int i, char c2) {
        if (i < 0 || i >= c()) {
            throw new StringIndexOutOfBoundsException(i);
        }
        this.b[i] = c2;
        return this;
    }

    public StrBuilder d(int i) {
        if (i < 0 || i >= this.c) {
            throw new StringIndexOutOfBoundsException(i);
        }
        a(i, i + 1, 1);
        return this;
    }

    public char[] i() {
        if (this.c == 0) {
            return ArrayUtils.r;
        }
        char[] cArr = new char[this.c];
        System.arraycopy(this.b, 0, cArr, 0, this.c);
        return cArr;
    }

    public char[] a(int i, int i2) {
        int f2 = f(i, i2) - i;
        if (f2 == 0) {
            return ArrayUtils.r;
        }
        char[] cArr = new char[f2];
        System.arraycopy(this.b, i, cArr, 0, f2);
        return cArr;
    }

    public char[] a(char[] cArr) {
        int c2 = c();
        if (cArr == null || cArr.length < c2) {
            cArr = new char[c2];
        }
        System.arraycopy(this.b, 0, cArr, 0, c2);
        return cArr;
    }

    public void a(int i, int i2, char[] cArr, int i3) {
        if (i < 0) {
            throw new StringIndexOutOfBoundsException(i);
        } else if (i2 < 0 || i2 > c()) {
            throw new StringIndexOutOfBoundsException(i2);
        } else if (i <= i2) {
            System.arraycopy(this.b, i, cArr, i3, i2 - i);
        } else {
            throw new StringIndexOutOfBoundsException("end < start");
        }
    }

    public StrBuilder j() {
        if (this.e != null) {
            return c(this.e);
        }
        c(SystemUtils.F);
        return this;
    }

    public StrBuilder k() {
        if (this.f == null) {
            return this;
        }
        return c(this.f);
    }

    public StrBuilder a(Object obj) {
        if (obj == null) {
            return k();
        }
        return c(obj.toString());
    }

    public StrBuilder c(String str) {
        if (str == null) {
            return k();
        }
        int length = str.length();
        if (length > 0) {
            int c2 = c();
            b(c2 + length);
            str.getChars(0, length, this.b, c2);
            this.c += length;
        }
        return this;
    }

    public StrBuilder a(String str, int i, int i2) {
        int i3;
        if (str == null) {
            return k();
        }
        if (i < 0 || i > str.length()) {
            throw new StringIndexOutOfBoundsException("startIndex must be valid");
        } else if (i2 < 0 || (i3 = i + i2) > str.length()) {
            throw new StringIndexOutOfBoundsException("length must be valid");
        } else {
            if (i2 > 0) {
                int c2 = c();
                b(c2 + i2);
                str.getChars(i, i3, this.b, c2);
                this.c += i2;
            }
            return this;
        }
    }

    public StrBuilder a(StringBuffer stringBuffer) {
        if (stringBuffer == null) {
            return k();
        }
        int length = stringBuffer.length();
        if (length > 0) {
            int c2 = c();
            b(c2 + length);
            stringBuffer.getChars(0, length, this.b, c2);
            this.c += length;
        }
        return this;
    }

    public StrBuilder a(StringBuffer stringBuffer, int i, int i2) {
        int i3;
        if (stringBuffer == null) {
            return k();
        }
        if (i < 0 || i > stringBuffer.length()) {
            throw new StringIndexOutOfBoundsException("startIndex must be valid");
        } else if (i2 < 0 || (i3 = i + i2) > stringBuffer.length()) {
            throw new StringIndexOutOfBoundsException("length must be valid");
        } else {
            if (i2 > 0) {
                int c2 = c();
                b(c2 + i2);
                stringBuffer.getChars(i, i3, this.b, c2);
                this.c += i2;
            }
            return this;
        }
    }

    public StrBuilder a(StrBuilder strBuilder) {
        if (strBuilder == null) {
            return k();
        }
        int c2 = strBuilder.c();
        if (c2 > 0) {
            int c3 = c();
            b(c3 + c2);
            System.arraycopy(strBuilder.b, 0, this.b, c3, c2);
            this.c += c2;
        }
        return this;
    }

    public StrBuilder a(StrBuilder strBuilder, int i, int i2) {
        int i3;
        if (strBuilder == null) {
            return k();
        }
        if (i < 0 || i > strBuilder.c()) {
            throw new StringIndexOutOfBoundsException("startIndex must be valid");
        } else if (i2 < 0 || (i3 = i + i2) > strBuilder.c()) {
            throw new StringIndexOutOfBoundsException("length must be valid");
        } else {
            if (i2 > 0) {
                int c2 = c();
                b(c2 + i2);
                strBuilder.a(i, i3, this.b, c2);
                this.c += i2;
            }
            return this;
        }
    }

    public StrBuilder b(char[] cArr) {
        if (cArr == null) {
            return k();
        }
        int length = cArr.length;
        if (length > 0) {
            int c2 = c();
            b(c2 + length);
            System.arraycopy(cArr, 0, this.b, c2, length);
            this.c += length;
        }
        return this;
    }

    public StrBuilder a(char[] cArr, int i, int i2) {
        if (cArr == null) {
            return k();
        }
        if (i < 0 || i > cArr.length) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Invalid startIndex: ");
            stringBuffer.append(i2);
            throw new StringIndexOutOfBoundsException(stringBuffer.toString());
        } else if (i2 < 0 || i + i2 > cArr.length) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Invalid length: ");
            stringBuffer2.append(i2);
            throw new StringIndexOutOfBoundsException(stringBuffer2.toString());
        } else {
            if (i2 > 0) {
                int c2 = c();
                b(c2 + i2);
                System.arraycopy(cArr, i, this.b, c2, i2);
                this.c += i2;
            }
            return this;
        }
    }

    public StrBuilder a(boolean z) {
        if (z) {
            b(this.c + 4);
            char[] cArr = this.b;
            int i = this.c;
            this.c = i + 1;
            cArr[i] = PatternFormatter.THREAD_CONVERSION_CHAR;
            char[] cArr2 = this.b;
            int i2 = this.c;
            this.c = i2 + 1;
            cArr2[i2] = PatternFormatter.RELATIVE_TIME_CONVERSION_CHAR;
            char[] cArr3 = this.b;
            int i3 = this.c;
            this.c = i3 + 1;
            cArr3[i3] = 'u';
            char[] cArr4 = this.b;
            int i4 = this.c;
            this.c = i4 + 1;
            cArr4[i4] = 'e';
        } else {
            b(this.c + 5);
            char[] cArr5 = this.b;
            int i5 = this.c;
            this.c = i5 + 1;
            cArr5[i5] = 'f';
            char[] cArr6 = this.b;
            int i6 = this.c;
            this.c = i6 + 1;
            cArr6[i6] = 'a';
            char[] cArr7 = this.b;
            int i7 = this.c;
            this.c = i7 + 1;
            cArr7[i7] = 'l';
            char[] cArr8 = this.b;
            int i8 = this.c;
            this.c = i8 + 1;
            cArr8[i8] = 's';
            char[] cArr9 = this.b;
            int i9 = this.c;
            this.c = i9 + 1;
            cArr9[i9] = 'e';
        }
        return this;
    }

    public StrBuilder a(char c2) {
        b(c() + 1);
        char[] cArr = this.b;
        int i = this.c;
        this.c = i + 1;
        cArr[i] = c2;
        return this;
    }

    public StrBuilder e(int i) {
        return c(String.valueOf(i));
    }

    public StrBuilder a(long j) {
        return c(String.valueOf(j));
    }

    public StrBuilder a(float f2) {
        return c(String.valueOf(f2));
    }

    public StrBuilder a(double d2) {
        return c(String.valueOf(d2));
    }

    public StrBuilder b(Object obj) {
        return a(obj).j();
    }

    public StrBuilder d(String str) {
        return c(str).j();
    }

    public StrBuilder b(String str, int i, int i2) {
        return a(str, i, i2).j();
    }

    public StrBuilder b(StringBuffer stringBuffer) {
        return a(stringBuffer).j();
    }

    public StrBuilder b(StringBuffer stringBuffer, int i, int i2) {
        return a(stringBuffer, i, i2).j();
    }

    public StrBuilder b(StrBuilder strBuilder) {
        return a(strBuilder).j();
    }

    public StrBuilder b(StrBuilder strBuilder, int i, int i2) {
        return a(strBuilder, i, i2).j();
    }

    public StrBuilder c(char[] cArr) {
        return b(cArr).j();
    }

    public StrBuilder b(char[] cArr, int i, int i2) {
        return a(cArr, i, i2).j();
    }

    public StrBuilder b(boolean z) {
        return a(z).j();
    }

    public StrBuilder b(char c2) {
        return a(c2).j();
    }

    public StrBuilder f(int i) {
        return e(i).j();
    }

    public StrBuilder b(long j) {
        return a(j).j();
    }

    public StrBuilder b(float f2) {
        return a(f2).j();
    }

    public StrBuilder b(double d2) {
        return a(d2).j();
    }

    public StrBuilder a(Object[] objArr) {
        if (objArr != null && objArr.length > 0) {
            for (Object a2 : objArr) {
                a(a2);
            }
        }
        return this;
    }

    public StrBuilder a(Collection collection) {
        if (collection != null && collection.size() > 0) {
            for (Object a2 : collection) {
                a(a2);
            }
        }
        return this;
    }

    public StrBuilder a(Iterator it) {
        if (it != null) {
            while (it.hasNext()) {
                a(it.next());
            }
        }
        return this;
    }

    public StrBuilder a(Object[] objArr, String str) {
        if (objArr != null && objArr.length > 0) {
            if (str == null) {
                str = "";
            }
            a(objArr[0]);
            for (int i = 1; i < objArr.length; i++) {
                c(str);
                a(objArr[i]);
            }
        }
        return this;
    }

    public StrBuilder a(Collection collection, String str) {
        if (collection != null && collection.size() > 0) {
            if (str == null) {
                str = "";
            }
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                a(it.next());
                if (it.hasNext()) {
                    c(str);
                }
            }
        }
        return this;
    }

    public StrBuilder a(Iterator it, String str) {
        if (it != null) {
            if (str == null) {
                str = "";
            }
            while (it.hasNext()) {
                a(it.next());
                if (it.hasNext()) {
                    c(str);
                }
            }
        }
        return this;
    }

    public StrBuilder e(String str) {
        return a(str, (String) null);
    }

    public StrBuilder a(String str, String str2) {
        if (g()) {
            str = str2;
        }
        if (str != null) {
            c(str);
        }
        return this;
    }

    public StrBuilder c(char c2) {
        if (f() > 0) {
            a(c2);
        }
        return this;
    }

    public StrBuilder a(char c2, char c3) {
        if (f() > 0) {
            a(c2);
        } else {
            a(c3);
        }
        return this;
    }

    public StrBuilder a(String str, int i) {
        if (str != null && i > 0) {
            c(str);
        }
        return this;
    }

    public StrBuilder a(char c2, int i) {
        if (i > 0) {
            a(c2);
        }
        return this;
    }

    public StrBuilder b(int i, char c2) {
        if (i >= 0) {
            b(this.c + i);
            for (int i2 = 0; i2 < i; i2++) {
                char[] cArr = this.b;
                int i3 = this.c;
                this.c = i3 + 1;
                cArr[i3] = c2;
            }
        }
        return this;
    }

    public StrBuilder a(Object obj, int i, char c2) {
        if (i > 0) {
            b(this.c + i);
            String b2 = obj == null ? b() : obj.toString();
            if (b2 == null) {
                b2 = "";
            }
            int length = b2.length();
            if (length >= i) {
                b2.getChars(length - i, length, this.b, this.c);
            } else {
                int i2 = i - length;
                for (int i3 = 0; i3 < i2; i3++) {
                    this.b[this.c + i3] = c2;
                }
                b2.getChars(0, length, this.b, this.c + i2);
            }
            this.c += i;
        }
        return this;
    }

    public StrBuilder a(int i, int i2, char c2) {
        return a((Object) String.valueOf(i), i2, c2);
    }

    public StrBuilder b(Object obj, int i, char c2) {
        if (i > 0) {
            b(this.c + i);
            String b2 = obj == null ? b() : obj.toString();
            if (b2 == null) {
                b2 = "";
            }
            int length = b2.length();
            if (length >= i) {
                b2.getChars(0, i, this.b, this.c);
            } else {
                int i2 = i - length;
                b2.getChars(0, length, this.b, this.c);
                for (int i3 = 0; i3 < i2; i3++) {
                    this.b[this.c + length + i3] = c2;
                }
            }
            this.c += i;
        }
        return this;
    }

    public StrBuilder b(int i, int i2, char c2) {
        return b((Object) String.valueOf(i), i2, c2);
    }

    public StrBuilder a(int i, Object obj) {
        if (obj == null) {
            return a(i, this.f);
        }
        return a(i, obj.toString());
    }

    public StrBuilder a(int i, String str) {
        int i2;
        j(i);
        if (str == null) {
            str = this.f;
        }
        if (str == null) {
            i2 = 0;
        } else {
            i2 = str.length();
        }
        if (i2 > 0) {
            int i3 = this.c + i2;
            b(i3);
            System.arraycopy(this.b, i, this.b, i + i2, this.c - i);
            this.c = i3;
            str.getChars(0, i2, this.b, i);
        }
        return this;
    }

    public StrBuilder a(int i, char[] cArr) {
        j(i);
        if (cArr == null) {
            return a(i, this.f);
        }
        int length = cArr.length;
        if (length > 0) {
            b(this.c + length);
            System.arraycopy(this.b, i, this.b, i + length, this.c - i);
            System.arraycopy(cArr, 0, this.b, i, length);
            this.c += length;
        }
        return this;
    }

    public StrBuilder a(int i, char[] cArr, int i2, int i3) {
        j(i);
        if (cArr == null) {
            return a(i, this.f);
        }
        if (i2 < 0 || i2 > cArr.length) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Invalid offset: ");
            stringBuffer.append(i2);
            throw new StringIndexOutOfBoundsException(stringBuffer.toString());
        } else if (i3 < 0 || i2 + i3 > cArr.length) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Invalid length: ");
            stringBuffer2.append(i3);
            throw new StringIndexOutOfBoundsException(stringBuffer2.toString());
        } else {
            if (i3 > 0) {
                b(this.c + i3);
                System.arraycopy(this.b, i, this.b, i + i3, this.c - i);
                System.arraycopy(cArr, i2, this.b, i, i3);
                this.c += i3;
            }
            return this;
        }
    }

    public StrBuilder a(int i, boolean z) {
        j(i);
        if (z) {
            b(this.c + 4);
            System.arraycopy(this.b, i, this.b, i + 4, this.c - i);
            int i2 = i + 1;
            this.b[i] = PatternFormatter.THREAD_CONVERSION_CHAR;
            int i3 = i2 + 1;
            this.b[i2] = PatternFormatter.RELATIVE_TIME_CONVERSION_CHAR;
            this.b[i3] = 'u';
            this.b[i3 + 1] = 'e';
            this.c += 4;
        } else {
            b(this.c + 5);
            System.arraycopy(this.b, i, this.b, i + 5, this.c - i);
            int i4 = i + 1;
            this.b[i] = 'f';
            int i5 = i4 + 1;
            this.b[i4] = 'a';
            int i6 = i5 + 1;
            this.b[i5] = 'l';
            this.b[i6] = 's';
            this.b[i6 + 1] = 'e';
            this.c += 5;
        }
        return this;
    }

    public StrBuilder c(int i, char c2) {
        j(i);
        b(this.c + 1);
        System.arraycopy(this.b, i, this.b, i + 1, this.c - i);
        this.b[i] = c2;
        this.c++;
        return this;
    }

    public StrBuilder b(int i, int i2) {
        return a(i, String.valueOf(i2));
    }

    public StrBuilder a(int i, long j) {
        return a(i, String.valueOf(j));
    }

    public StrBuilder a(int i, float f2) {
        return a(i, String.valueOf(f2));
    }

    public StrBuilder a(int i, double d2) {
        return a(i, String.valueOf(d2));
    }

    private void a(int i, int i2, int i3) {
        System.arraycopy(this.b, i2, this.b, i, this.c - i2);
        this.c -= i3;
    }

    public StrBuilder c(int i, int i2) {
        int f2 = f(i, i2);
        int i3 = f2 - i;
        if (i3 > 0) {
            a(i, f2, i3);
        }
        return this;
    }

    public StrBuilder d(char c2) {
        int i = 0;
        while (i < this.c) {
            if (this.b[i] == c2) {
                int i2 = i;
                do {
                    i2++;
                    if (i2 >= this.c || this.b[i2] != c2) {
                        int i3 = i2 - i;
                        a(i, i2, i3);
                        i = i2 - i3;
                    }
                    i2++;
                    break;
                } while (this.b[i2] != c2);
                int i32 = i2 - i;
                a(i, i2, i32);
                i = i2 - i32;
            }
            i++;
        }
        return this;
    }

    public StrBuilder e(char c2) {
        int i = 0;
        while (true) {
            if (i >= this.c) {
                break;
            } else if (this.b[i] == c2) {
                a(i, i + 1, 1);
                break;
            } else {
                i++;
            }
        }
        return this;
    }

    public StrBuilder f(String str) {
        int length = str == null ? 0 : str.length();
        if (length > 0) {
            int b2 = b(str, 0);
            while (b2 >= 0) {
                a(b2, b2 + length, length);
                b2 = b(str, b2);
            }
        }
        return this;
    }

    public StrBuilder g(String str) {
        int b2;
        int length = str == null ? 0 : str.length();
        if (length > 0 && (b2 = b(str, 0)) >= 0) {
            a(b2, b2 + length, length);
        }
        return this;
    }

    public StrBuilder a(StrMatcher strMatcher) {
        return a(strMatcher, (String) null, 0, this.c, -1);
    }

    public StrBuilder b(StrMatcher strMatcher) {
        return a(strMatcher, (String) null, 0, this.c, 1);
    }

    private void a(int i, int i2, int i3, String str, int i4) {
        int i5 = (this.c - i3) + i4;
        if (i4 != i3) {
            b(i5);
            System.arraycopy(this.b, i2, this.b, i + i4, this.c - i2);
            this.c = i5;
        }
        if (i4 > 0) {
            str.getChars(0, i4, this.b, i);
        }
    }

    public StrBuilder a(int i, int i2, String str) {
        int i3;
        int f2 = f(i, i2);
        if (str == null) {
            i3 = 0;
        } else {
            i3 = str.length();
        }
        a(i, f2, f2 - i, str, i3);
        return this;
    }

    public StrBuilder b(char c2, char c3) {
        if (c2 != c3) {
            for (int i = 0; i < this.c; i++) {
                if (this.b[i] == c2) {
                    this.b[i] = c3;
                }
            }
        }
        return this;
    }

    public StrBuilder c(char c2, char c3) {
        if (c2 != c3) {
            int i = 0;
            while (true) {
                if (i >= this.c) {
                    break;
                } else if (this.b[i] == c2) {
                    this.b[i] = c3;
                    break;
                } else {
                    i++;
                }
            }
        }
        return this;
    }

    public StrBuilder b(String str, String str2) {
        int i;
        int length = str == null ? 0 : str.length();
        if (length > 0) {
            if (str2 == null) {
                i = 0;
            } else {
                i = str2.length();
            }
            int b2 = b(str, 0);
            while (b2 >= 0) {
                a(b2, b2 + length, length, str2, i);
                b2 = b(str, b2 + i);
            }
        }
        return this;
    }

    public StrBuilder c(String str, String str2) {
        int b2;
        int i;
        int length = str == null ? 0 : str.length();
        if (length > 0 && (b2 = b(str, 0)) >= 0) {
            if (str2 == null) {
                i = 0;
            } else {
                i = str2.length();
            }
            a(b2, b2 + length, length, str2, i);
        }
        return this;
    }

    public StrBuilder a(StrMatcher strMatcher, String str) {
        return a(strMatcher, str, 0, this.c, -1);
    }

    public StrBuilder b(StrMatcher strMatcher, String str) {
        return a(strMatcher, str, 0, this.c, 1);
    }

    public StrBuilder a(StrMatcher strMatcher, String str, int i, int i2, int i3) {
        return b(strMatcher, str, i, f(i, i2), i3);
    }

    private StrBuilder b(StrMatcher strMatcher, String str, int i, int i2, int i3) {
        int i4;
        if (strMatcher == null || this.c == 0) {
            return this;
        }
        if (str == null) {
            i4 = 0;
        } else {
            i4 = str.length();
        }
        char[] cArr = this.b;
        int i5 = i3;
        int i6 = i2;
        int i7 = i;
        while (i7 < i6 && i5 != 0) {
            int a2 = strMatcher.a(cArr, i7, i, i6);
            if (a2 > 0) {
                a(i7, i7 + a2, a2, str, i4);
                i6 = (i6 - a2) + i4;
                i7 = (i7 + i4) - 1;
                if (i5 > 0) {
                    i5--;
                }
            }
            i7++;
        }
        return this;
    }

    public StrBuilder l() {
        if (this.c == 0) {
            return this;
        }
        int i = this.c / 2;
        char[] cArr = this.b;
        int i2 = 0;
        int i3 = this.c - 1;
        while (i2 < i) {
            char c2 = cArr[i2];
            cArr[i2] = cArr[i3];
            cArr[i3] = c2;
            i2++;
            i3--;
        }
        return this;
    }

    public StrBuilder m() {
        if (this.c == 0) {
            return this;
        }
        int i = this.c;
        char[] cArr = this.b;
        int i2 = 0;
        while (i2 < i && cArr[i2] <= ' ') {
            i2++;
        }
        while (i2 < i && cArr[i - 1] <= ' ') {
            i--;
        }
        if (i < this.c) {
            c(i, this.c);
        }
        if (i2 > 0) {
            c(0, i2);
        }
        return this;
    }

    public boolean h(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return true;
        }
        if (length > this.c) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (this.b[i] != str.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public boolean i(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return true;
        }
        if (length > this.c) {
            return false;
        }
        int i = this.c - length;
        int i2 = 0;
        while (i2 < length) {
            if (this.b[i] != str.charAt(i2)) {
                return false;
            }
            i2++;
            i++;
        }
        return true;
    }

    public String g(int i) {
        return d(i, this.c);
    }

    public String d(int i, int i2) {
        return new String(this.b, i, f(i, i2) - i);
    }

    public String h(int i) {
        if (i <= 0) {
            return "";
        }
        if (i >= this.c) {
            return new String(this.b, 0, this.c);
        }
        return new String(this.b, 0, i);
    }

    public String i(int i) {
        if (i <= 0) {
            return "";
        }
        if (i >= this.c) {
            return new String(this.b, 0, this.c);
        }
        return new String(this.b, this.c - i, i);
    }

    public String e(int i, int i2) {
        if (i < 0) {
            i = 0;
        }
        if (i2 <= 0 || i >= this.c) {
            return "";
        }
        if (this.c <= i + i2) {
            return new String(this.b, i, this.c - i);
        }
        return new String(this.b, i, i2);
    }

    public boolean f(char c2) {
        char[] cArr = this.b;
        for (int i = 0; i < this.c; i++) {
            if (cArr[i] == c2) {
                return true;
            }
        }
        return false;
    }

    public boolean j(String str) {
        return b(str, 0) >= 0;
    }

    public boolean c(StrMatcher strMatcher) {
        return a(strMatcher, 0) >= 0;
    }

    public int g(char c2) {
        return b(c2, 0);
    }

    public int b(char c2, int i) {
        if (i < 0) {
            i = 0;
        }
        if (i >= this.c) {
            return -1;
        }
        char[] cArr = this.b;
        while (i < this.c) {
            if (cArr[i] == c2) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public int k(String str) {
        return b(str, 0);
    }

    public int b(String str, int i) {
        if (i < 0) {
            i = 0;
        }
        if (str == null || i >= this.c) {
            return -1;
        }
        int length = str.length();
        if (length == 1) {
            return b(str.charAt(0), i);
        }
        if (length == 0) {
            return i;
        }
        if (length > this.c) {
            return -1;
        }
        char[] cArr = this.b;
        int i2 = (this.c - length) + 1;
        while (i < i2) {
            int i3 = 0;
            while (i3 < length) {
                if (str.charAt(i3) != cArr[i + i3]) {
                    i++;
                } else {
                    i3++;
                }
            }
            return i;
        }
        return -1;
    }

    public int d(StrMatcher strMatcher) {
        return a(strMatcher, 0);
    }

    public int a(StrMatcher strMatcher, int i) {
        if (i < 0) {
            i = 0;
        }
        if (strMatcher == null || i >= this.c) {
            return -1;
        }
        int i2 = this.c;
        char[] cArr = this.b;
        for (int i3 = i; i3 < i2; i3++) {
            if (strMatcher.a(cArr, i3, i, i2) > 0) {
                return i3;
            }
        }
        return -1;
    }

    public int h(char c2) {
        return c(c2, this.c - 1);
    }

    public int c(char c2, int i) {
        if (i >= this.c) {
            i = this.c - 1;
        }
        if (i < 0) {
            return -1;
        }
        while (i >= 0) {
            if (this.b[i] == c2) {
                return i;
            }
            i--;
        }
        return -1;
    }

    public int l(String str) {
        return c(str, this.c - 1);
    }

    public int c(String str, int i) {
        if (i >= this.c) {
            i = this.c - 1;
        }
        if (str == null || i < 0) {
            return -1;
        }
        int length = str.length();
        if (length <= 0 || length > this.c) {
            if (length == 0) {
                return i;
            }
        } else if (length == 1) {
            return c(str.charAt(0), i);
        } else {
            int i2 = (i - length) + 1;
            while (i2 >= 0) {
                int i3 = 0;
                while (i3 < length) {
                    if (str.charAt(i3) != this.b[i2 + i3]) {
                        i2--;
                    } else {
                        i3++;
                    }
                }
                return i2;
            }
        }
        return -1;
    }

    public int e(StrMatcher strMatcher) {
        return b(strMatcher, this.c);
    }

    public int b(StrMatcher strMatcher, int i) {
        if (i >= this.c) {
            i = this.c - 1;
        }
        if (strMatcher == null || i < 0) {
            return -1;
        }
        char[] cArr = this.b;
        int i2 = i + 1;
        while (i >= 0) {
            if (strMatcher.a(cArr, i, 0, i2) > 0) {
                return i;
            }
            i--;
        }
        return -1;
    }

    public StrTokenizer n() {
        return new StrBuilderTokenizer(this);
    }

    public Reader o() {
        return new StrBuilderReader(this);
    }

    public Writer p() {
        return new StrBuilderWriter(this);
    }

    public boolean c(StrBuilder strBuilder) {
        if (this == strBuilder) {
            return true;
        }
        if (this.c != strBuilder.c) {
            return false;
        }
        char[] cArr = this.b;
        char[] cArr2 = strBuilder.b;
        for (int i = this.c - 1; i >= 0; i--) {
            char c2 = cArr[i];
            char c3 = cArr2[i];
            if (c2 != c3 && Character.toUpperCase(c2) != Character.toUpperCase(c3)) {
                return false;
            }
        }
        return true;
    }

    public boolean d(StrBuilder strBuilder) {
        if (this == strBuilder) {
            return true;
        }
        if (this.c != strBuilder.c) {
            return false;
        }
        char[] cArr = this.b;
        char[] cArr2 = strBuilder.b;
        for (int i = this.c - 1; i >= 0; i--) {
            if (cArr[i] != cArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj instanceof StrBuilder) {
            return d((StrBuilder) obj);
        }
        return false;
    }

    public int hashCode() {
        char[] cArr = this.b;
        int i = 0;
        for (int i2 = this.c - 1; i2 >= 0; i2--) {
            i = (i * 31) + cArr[i2];
        }
        return i;
    }

    public String toString() {
        return new String(this.b, 0, this.c);
    }

    public StringBuffer q() {
        StringBuffer stringBuffer = new StringBuffer(this.c);
        stringBuffer.append(this.b, 0, this.c);
        return stringBuffer;
    }

    public Object clone() throws CloneNotSupportedException {
        StrBuilder strBuilder = (StrBuilder) super.clone();
        strBuilder.b = new char[this.b.length];
        System.arraycopy(this.b, 0, strBuilder.b, 0, this.b.length);
        return strBuilder;
    }

    /* access modifiers changed from: protected */
    public int f(int i, int i2) {
        if (i >= 0) {
            if (i2 > this.c) {
                i2 = this.c;
            }
            if (i <= i2) {
                return i2;
            }
            throw new StringIndexOutOfBoundsException("end < start");
        }
        throw new StringIndexOutOfBoundsException(i);
    }

    /* access modifiers changed from: protected */
    public void j(int i) {
        if (i < 0 || i > this.c) {
            throw new StringIndexOutOfBoundsException(i);
        }
    }

    class StrBuilderTokenizer extends StrTokenizer {

        /* renamed from: a  reason: collision with root package name */
        private final StrBuilder f3401a;

        StrBuilderTokenizer(StrBuilder strBuilder) {
            this.f3401a = strBuilder;
        }

        /* access modifiers changed from: protected */
        public List a(char[] cArr, int i, int i2) {
            if (cArr == null) {
                return super.a(this.f3401a.b, 0, this.f3401a.f());
            }
            return super.a(cArr, i, i2);
        }

        public String a() {
            String a2 = super.a();
            return a2 == null ? this.f3401a.toString() : a2;
        }
    }

    class StrBuilderReader extends Reader {

        /* renamed from: a  reason: collision with root package name */
        private int f3400a;
        private int b;
        private final StrBuilder c;

        public void close() {
        }

        public boolean markSupported() {
            return true;
        }

        StrBuilderReader(StrBuilder strBuilder) {
            this.c = strBuilder;
        }

        public int read() {
            if (!ready()) {
                return -1;
            }
            StrBuilder strBuilder = this.c;
            int i = this.f3400a;
            this.f3400a = i + 1;
            return strBuilder.c(i);
        }

        public int read(char[] cArr, int i, int i2) {
            int i3;
            if (i < 0 || i2 < 0 || i > cArr.length || (i3 = i + i2) > cArr.length || i3 < 0) {
                throw new IndexOutOfBoundsException();
            } else if (i2 == 0) {
                return 0;
            } else {
                if (this.f3400a >= this.c.f()) {
                    return -1;
                }
                if (this.f3400a + i2 > this.c.f()) {
                    i2 = this.c.f() - this.f3400a;
                }
                this.c.a(this.f3400a, this.f3400a + i2, cArr, i);
                this.f3400a += i2;
                return i2;
            }
        }

        public long skip(long j) {
            if (((long) this.f3400a) + j > ((long) this.c.f())) {
                j = (long) (this.c.f() - this.f3400a);
            }
            if (j < 0) {
                return 0;
            }
            this.f3400a = (int) (((long) this.f3400a) + j);
            return j;
        }

        public boolean ready() {
            return this.f3400a < this.c.f();
        }

        public void mark(int i) {
            this.b = this.f3400a;
        }

        public void reset() {
            this.f3400a = this.b;
        }
    }

    class StrBuilderWriter extends Writer {

        /* renamed from: a  reason: collision with root package name */
        private final StrBuilder f3402a;

        public void close() {
        }

        public void flush() {
        }

        StrBuilderWriter(StrBuilder strBuilder) {
            this.f3402a = strBuilder;
        }

        public void write(int i) {
            this.f3402a.a((char) i);
        }

        public void write(char[] cArr) {
            this.f3402a.b(cArr);
        }

        public void write(char[] cArr, int i, int i2) {
            this.f3402a.a(cArr, i, i2);
        }

        public void write(String str) {
            this.f3402a.c(str);
        }

        public void write(String str, int i, int i2) {
            this.f3402a.a(str, i, i2);
        }
    }
}
