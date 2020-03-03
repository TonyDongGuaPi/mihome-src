package org.jsoup.parser;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.el.parse.Operators;
import org.jsoup.helper.StringUtil;
import org.jsoup.helper.Validate;

public class TokenQueue {
    private static final char c = '\\';

    /* renamed from: a  reason: collision with root package name */
    private String f3684a;
    private int b = 0;

    public TokenQueue(String str) {
        Validate.a((Object) str);
        this.f3684a = str;
    }

    public boolean a() {
        return o() == 0;
    }

    private int o() {
        return this.f3684a.length() - this.b;
    }

    public char b() {
        if (a()) {
            return 0;
        }
        return this.f3684a.charAt(this.b);
    }

    public void a(Character ch) {
        a(ch.toString());
    }

    public void a(String str) {
        this.f3684a = str + this.f3684a.substring(this.b);
        this.b = 0;
    }

    public boolean b(String str) {
        return this.f3684a.regionMatches(true, this.b, str, 0, str.length());
    }

    public boolean c(String str) {
        return this.f3684a.startsWith(str, this.b);
    }

    public boolean a(String... strArr) {
        for (String b2 : strArr) {
            if (b(b2)) {
                return true;
            }
        }
        return false;
    }

    public boolean a(char... cArr) {
        if (a()) {
            return false;
        }
        for (char c2 : cArr) {
            if (this.f3684a.charAt(this.b) == c2) {
                return true;
            }
        }
        return false;
    }

    public boolean c() {
        return o() >= 2 && this.f3684a.charAt(this.b) == '<' && Character.isLetter(this.f3684a.charAt(this.b + 1));
    }

    public boolean d(String str) {
        if (!b(str)) {
            return false;
        }
        this.b += str.length();
        return true;
    }

    public boolean d() {
        return !a() && StringUtil.b((int) this.f3684a.charAt(this.b));
    }

    public boolean e() {
        return !a() && Character.isLetterOrDigit(this.f3684a.charAt(this.b));
    }

    public void f() {
        if (!a()) {
            this.b++;
        }
    }

    public char g() {
        String str = this.f3684a;
        int i = this.b;
        this.b = i + 1;
        return str.charAt(i);
    }

    public void e(String str) {
        if (b(str)) {
            int length = str.length();
            if (length <= o()) {
                this.b += length;
                return;
            }
            throw new IllegalStateException("Queue not long enough to consume sequence");
        }
        throw new IllegalStateException("Queue did not match expected sequence");
    }

    public String f(String str) {
        int indexOf = this.f3684a.indexOf(str, this.b);
        if (indexOf == -1) {
            return n();
        }
        String substring = this.f3684a.substring(this.b, indexOf);
        this.b += substring.length();
        return substring;
    }

    public String g(String str) {
        int i = this.b;
        String substring = str.substring(0, 1);
        boolean equals = substring.toLowerCase().equals(substring.toUpperCase());
        while (!a() && !b(str)) {
            if (equals) {
                int indexOf = this.f3684a.indexOf(substring, this.b) - this.b;
                if (indexOf == 0) {
                    this.b++;
                } else if (indexOf < 0) {
                    this.b = this.f3684a.length();
                } else {
                    this.b += indexOf;
                }
            } else {
                this.b++;
            }
        }
        return this.f3684a.substring(i, this.b);
    }

    public String b(String... strArr) {
        int i = this.b;
        while (!a() && !a(strArr)) {
            this.b++;
        }
        return this.f3684a.substring(i, this.b);
    }

    public String h(String str) {
        String f = f(str);
        d(str);
        return f;
    }

    public String i(String str) {
        String g = g(str);
        d(str);
        return g;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0067 A[EDGE_INSN: B:35:0x0067->B:28:0x0067 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a(char r9, char r10) {
        /*
            r8 = this;
            r0 = 0
            r1 = -1
            r2 = 0
            r3 = -1
            r4 = -1
            r5 = 0
        L_0x0006:
            boolean r6 = r8.a()
            if (r6 == 0) goto L_0x000d
            goto L_0x0067
        L_0x000d:
            char r6 = r8.g()
            java.lang.Character r6 = java.lang.Character.valueOf(r6)
            if (r0 == 0) goto L_0x001b
            r7 = 92
            if (r0 == r7) goto L_0x005b
        L_0x001b:
            r7 = 39
            java.lang.Character r7 = java.lang.Character.valueOf(r7)
            boolean r7 = r6.equals(r7)
            if (r7 != 0) goto L_0x0033
            r7 = 34
            java.lang.Character r7 = java.lang.Character.valueOf(r7)
            boolean r7 = r6.equals(r7)
            if (r7 == 0) goto L_0x003b
        L_0x0033:
            char r7 = r6.charValue()
            if (r7 == r9) goto L_0x003b
            r2 = r2 ^ 1
        L_0x003b:
            if (r2 == 0) goto L_0x003e
            goto L_0x0065
        L_0x003e:
            java.lang.Character r7 = java.lang.Character.valueOf(r9)
            boolean r7 = r6.equals(r7)
            if (r7 == 0) goto L_0x004f
            int r5 = r5 + 1
            if (r3 != r1) goto L_0x005b
            int r3 = r8.b
            goto L_0x005b
        L_0x004f:
            java.lang.Character r7 = java.lang.Character.valueOf(r10)
            boolean r7 = r6.equals(r7)
            if (r7 == 0) goto L_0x005b
            int r5 = r5 + -1
        L_0x005b:
            if (r5 <= 0) goto L_0x0061
            if (r0 == 0) goto L_0x0061
            int r4 = r8.b
        L_0x0061:
            char r0 = r6.charValue()
        L_0x0065:
            if (r5 > 0) goto L_0x0006
        L_0x0067:
            if (r4 < 0) goto L_0x0070
            java.lang.String r9 = r8.f3684a
            java.lang.String r9 = r9.substring(r3, r4)
            goto L_0x0072
        L_0x0070:
            java.lang.String r9 = ""
        L_0x0072:
            if (r5 <= 0) goto L_0x008d
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r0 = "Did not find balanced marker at '"
            r10.append(r0)
            r10.append(r9)
            java.lang.String r0 = "'"
            r10.append(r0)
            java.lang.String r10 = r10.toString()
            org.jsoup.helper.Validate.b((java.lang.String) r10)
        L_0x008d:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.TokenQueue.a(char, char):java.lang.String");
    }

    public static String j(String str) {
        StringBuilder a2 = StringUtil.a();
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        int i = 0;
        char c2 = 0;
        while (i < length) {
            char c3 = charArray[i];
            if (c3 != '\\') {
                a2.append(c3);
            } else if (c2 != 0 && c2 == '\\') {
                a2.append(c3);
            }
            i++;
            c2 = c3;
        }
        return a2.toString();
    }

    public boolean h() {
        boolean z = false;
        while (d()) {
            this.b++;
            z = true;
        }
        return z;
    }

    public String i() {
        int i = this.b;
        while (e()) {
            this.b++;
        }
        return this.f3684a.substring(i, this.b);
    }

    public String j() {
        int i = this.b;
        while (!a() && (e() || a(Operators.CONDITION_IF_MIDDLE, '_', '-'))) {
            this.b++;
        }
        return this.f3684a.substring(i, this.b);
    }

    public String k() {
        int i = this.b;
        while (!a()) {
            if (!e()) {
                if (!a("*|", "|", JSMethod.NOT_SET, "-")) {
                    break;
                }
            }
            this.b++;
        }
        return this.f3684a.substring(i, this.b);
    }

    public String l() {
        int i = this.b;
        while (!a() && (e() || a('-', '_'))) {
            this.b++;
        }
        return this.f3684a.substring(i, this.b);
    }

    public String m() {
        int i = this.b;
        while (!a() && (e() || a('-', '_', Operators.CONDITION_IF_MIDDLE))) {
            this.b++;
        }
        return this.f3684a.substring(i, this.b);
    }

    public String n() {
        String substring = this.f3684a.substring(this.b, this.f3684a.length());
        this.b = this.f3684a.length();
        return substring;
    }

    public String toString() {
        return this.f3684a.substring(this.b);
    }
}
