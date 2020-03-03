package org.jsoup.parser;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Locale;
import org.jsoup.UncheckedIOException;
import org.jsoup.helper.Validate;

public final class CharacterReader {

    /* renamed from: a  reason: collision with root package name */
    static final char f3674a = '￿';
    static final int b = 32768;
    private static final int c = 12;
    private static final int d = 24576;
    private final char[] e;
    private final Reader f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private final String[] l;

    public CharacterReader(Reader reader, int i2) {
        this.l = new String[512];
        Validate.a((Object) reader);
        Validate.a(reader.markSupported());
        this.f = reader;
        this.e = new char[(i2 <= 32768 ? i2 : 32768)];
        r();
    }

    public CharacterReader(Reader reader) {
        this(reader, 32768);
    }

    public CharacterReader(String str) {
        this(new StringReader(str), str.length());
    }

    private void r() {
        if (this.i >= this.h) {
            try {
                this.j += this.i;
                this.f.skip((long) this.i);
                this.f.mark(32768);
                this.g = this.f.read(this.e);
                this.f.reset();
                this.i = 0;
                this.k = 0;
                int i2 = 24576;
                if (this.g <= 24576) {
                    i2 = this.g;
                }
                this.h = i2;
            } catch (IOException e2) {
                throw new UncheckedIOException(e2);
            }
        }
    }

    public int a() {
        return this.j + this.i;
    }

    public boolean b() {
        return this.i >= this.g;
    }

    public char c() {
        r();
        if (b()) {
            return 65535;
        }
        return this.e[this.i];
    }

    /* access modifiers changed from: package-private */
    public char d() {
        r();
        char c2 = b() ? 65535 : this.e[this.i];
        this.i++;
        return c2;
    }

    /* access modifiers changed from: package-private */
    public void e() {
        this.i--;
    }

    public void f() {
        this.i++;
    }

    /* access modifiers changed from: package-private */
    public void g() {
        this.k = this.i;
    }

    /* access modifiers changed from: package-private */
    public void h() {
        this.i = this.k;
    }

    /* access modifiers changed from: package-private */
    public int a(char c2) {
        r();
        for (int i2 = this.i; i2 < this.g; i2++) {
            if (c2 == this.e[i2]) {
                return i2 - this.i;
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public int a(CharSequence charSequence) {
        r();
        char charAt = charSequence.charAt(0);
        int i2 = this.i;
        while (i2 < this.g) {
            if (charAt != this.e[i2]) {
                do {
                    i2++;
                    if (i2 >= this.g) {
                        break;
                    }
                } while (charAt == this.e[i2]);
            }
            int i3 = i2 + 1;
            int length = (charSequence.length() + i3) - 1;
            if (i2 < this.g && length <= this.g) {
                int i4 = i3;
                int i5 = 1;
                while (i4 < length && charSequence.charAt(i5) == this.e[i4]) {
                    i4++;
                    i5++;
                }
                if (i4 == length) {
                    return i2 - this.i;
                }
            }
            i2 = i3;
        }
        return -1;
    }

    public String b(char c2) {
        int a2 = a(c2);
        if (a2 == -1) {
            return k();
        }
        String a3 = a(this.e, this.l, this.i, a2);
        this.i += a2;
        return a3;
    }

    /* access modifiers changed from: package-private */
    public String a(String str) {
        int a2 = a((CharSequence) str);
        if (a2 == -1) {
            return k();
        }
        String a3 = a(this.e, this.l, this.i, a2);
        this.i += a2;
        return a3;
    }

    public String a(char... cArr) {
        r();
        int i2 = this.i;
        int i3 = this.g;
        char[] cArr2 = this.e;
        loop0:
        while (this.i < i3) {
            for (char c2 : cArr) {
                if (cArr2[this.i] == c2) {
                    break loop0;
                }
            }
            this.i++;
        }
        return this.i > i2 ? a(this.e, this.l, i2, this.i - i2) : "";
    }

    /* access modifiers changed from: package-private */
    public String b(char... cArr) {
        r();
        int i2 = this.i;
        int i3 = this.g;
        char[] cArr2 = this.e;
        while (this.i < i3 && Arrays.binarySearch(cArr, cArr2[this.i]) < 0) {
            this.i++;
        }
        return this.i > i2 ? a(this.e, this.l, i2, this.i - i2) : "";
    }

    /* access modifiers changed from: package-private */
    public String i() {
        r();
        int i2 = this.i;
        int i3 = this.g;
        char[] cArr = this.e;
        while (this.i < i3 && (r3 = cArr[this.i]) != '&' && r3 != '<' && r3 != 0) {
            this.i++;
        }
        return this.i > i2 ? a(this.e, this.l, i2, this.i - i2) : "";
    }

    /* access modifiers changed from: package-private */
    public String j() {
        r();
        int i2 = this.i;
        int i3 = this.g;
        char[] cArr = this.e;
        while (this.i < i3 && (r3 = cArr[this.i]) != 9 && r3 != 10 && r3 != 13 && r3 != 12 && r3 != ' ' && r3 != '/' && r3 != '>' && r3 != 0) {
            this.i++;
        }
        return this.i > i2 ? a(this.e, this.l, i2, this.i - i2) : "";
    }

    /* access modifiers changed from: package-private */
    public String k() {
        r();
        String a2 = a(this.e, this.l, this.i, this.g - this.i);
        this.i = this.g;
        return a2;
    }

    /* access modifiers changed from: package-private */
    public String l() {
        r();
        int i2 = this.i;
        while (this.i < this.g && (((r1 = this.e[this.i]) >= 'A' && r1 <= 'Z') || ((r1 >= 'a' && r1 <= 'z') || Character.isLetter(r1)))) {
            this.i++;
        }
        return a(this.e, this.l, i2, this.i - i2);
    }

    /* access modifiers changed from: package-private */
    public String m() {
        r();
        int i2 = this.i;
        while (this.i < this.g && (((r1 = this.e[this.i]) >= 'A' && r1 <= 'Z') || ((r1 >= 'a' && r1 <= 'z') || Character.isLetter(r1)))) {
            this.i++;
        }
        while (!b() && (r1 = this.e[this.i]) >= '0' && r1 <= '9') {
            this.i++;
        }
        return a(this.e, this.l, i2, this.i - i2);
    }

    /* access modifiers changed from: package-private */
    public String n() {
        r();
        int i2 = this.i;
        while (this.i < this.g && (((r1 = this.e[this.i]) >= '0' && r1 <= '9') || ((r1 >= 'A' && r1 <= 'F') || (r1 >= 'a' && r1 <= 'f')))) {
            this.i++;
        }
        return a(this.e, this.l, i2, this.i - i2);
    }

    /* access modifiers changed from: package-private */
    public String o() {
        r();
        int i2 = this.i;
        while (this.i < this.g && (r1 = this.e[this.i]) >= '0' && r1 <= '9') {
            this.i++;
        }
        return a(this.e, this.l, i2, this.i - i2);
    }

    /* access modifiers changed from: package-private */
    public boolean c(char c2) {
        return !b() && this.e[this.i] == c2;
    }

    /* access modifiers changed from: package-private */
    public boolean b(String str) {
        r();
        int length = str.length();
        if (length > this.g - this.i) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (str.charAt(i2) != this.e[this.i + i2]) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean c(String str) {
        r();
        int length = str.length();
        if (length > this.g - this.i) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (Character.toUpperCase(str.charAt(i2)) != Character.toUpperCase(this.e[this.i + i2])) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean c(char... cArr) {
        if (b()) {
            return false;
        }
        r();
        char c2 = this.e[this.i];
        for (char c3 : cArr) {
            if (c3 == c2) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean d(char[] cArr) {
        r();
        return !b() && Arrays.binarySearch(cArr, this.e[this.i]) >= 0;
    }

    /* access modifiers changed from: package-private */
    public boolean p() {
        if (b()) {
            return false;
        }
        char c2 = this.e[this.i];
        if ((c2 < 'A' || c2 > 'Z') && ((c2 < 'a' || c2 > 'z') && !Character.isLetter(c2))) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean q() {
        char c2;
        if (!b() && (c2 = this.e[this.i]) >= '0' && c2 <= '9') {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean d(String str) {
        r();
        if (!b(str)) {
            return false;
        }
        this.i += str.length();
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean e(String str) {
        if (!c(str)) {
            return false;
        }
        this.i += str.length();
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean f(String str) {
        return a((CharSequence) str.toLowerCase(Locale.ENGLISH)) > -1 || a((CharSequence) str.toUpperCase(Locale.ENGLISH)) > -1;
    }

    public String toString() {
        return new String(this.e, this.i, this.g - this.i);
    }

    private static String a(char[] cArr, String[] strArr, int i2, int i3) {
        if (i3 > 12) {
            return new String(cArr, i2, i3);
        }
        if (i3 < 1) {
            return "";
        }
        int i4 = 0;
        int i5 = i2;
        int i6 = 0;
        while (i4 < i3) {
            i6 = (i6 * 31) + cArr[i5];
            i4++;
            i5++;
        }
        int length = i6 & (strArr.length - 1);
        String str = strArr[length];
        if (str == null) {
            String str2 = new String(cArr, i2, i3);
            strArr[length] = str2;
            return str2;
        } else if (a(cArr, i2, i3, str)) {
            return str;
        } else {
            String str3 = new String(cArr, i2, i3);
            strArr[length] = str3;
            return str3;
        }
    }

    static boolean a(char[] cArr, int i2, int i3, String str) {
        if (i3 != str.length()) {
            return false;
        }
        int i4 = 0;
        while (true) {
            int i5 = i3 - 1;
            if (i3 == 0) {
                return true;
            }
            int i6 = i2 + 1;
            int i7 = i4 + 1;
            if (cArr[i2] != str.charAt(i4)) {
                return false;
            }
            i2 = i6;
            i3 = i5;
            i4 = i7;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(int i2, int i3, String str) {
        return a(this.e, i2, i3, str);
    }
}
