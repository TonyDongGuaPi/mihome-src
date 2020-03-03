package org.jsoup.parser;

import com.alipay.sdk.util.i;
import java.util.Arrays;
import kotlin.text.Typography;
import org.apache.commons.lang.CharUtils;
import org.jsoup.helper.StringUtil;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Entities;
import org.jsoup.parser.Token;

final class Tokeniser {

    /* renamed from: a  reason: collision with root package name */
    static final char f3685a = '�';
    private static final char[] i = {9, 10, CharUtils.b, 12, ' ', Typography.d, Typography.c};
    StringBuilder b = new StringBuilder(1024);
    Token.Tag c;
    Token.StartTag d = new Token.StartTag();
    Token.EndTag e = new Token.EndTag();
    Token.Character f = new Token.Character();
    Token.Doctype g = new Token.Doctype();
    Token.Comment h = new Token.Comment();
    private final CharacterReader j;
    private final ParseErrorList k;
    private TokeniserState l = TokeniserState.Data;
    private Token m;
    private boolean n = false;
    private String o = null;
    private StringBuilder p = new StringBuilder(1024);
    private String q;
    private final int[] r = new int[1];
    private final int[] s = new int[2];

    /* access modifiers changed from: package-private */
    public boolean k() {
        return true;
    }

    static {
        Arrays.sort(i);
    }

    Tokeniser(CharacterReader characterReader, ParseErrorList parseErrorList) {
        this.j = characterReader;
        this.k = parseErrorList;
    }

    /* access modifiers changed from: package-private */
    public Token a() {
        while (!this.n) {
            this.l.read(this, this.j);
        }
        if (this.p.length() > 0) {
            String sb = this.p.toString();
            this.p.delete(0, this.p.length());
            this.o = null;
            return this.f.a(sb);
        } else if (this.o != null) {
            Token.Character a2 = this.f.a(this.o);
            this.o = null;
            return a2;
        } else {
            this.n = false;
            return this.m;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Token token) {
        Validate.b(this.n, "There is an unread token pending!");
        this.m = token;
        this.n = true;
        if (token.f3683a == Token.TokenType.StartTag) {
            this.q = ((Token.StartTag) token).b;
        } else if (token.f3683a == Token.TokenType.EndTag && ((Token.EndTag) token).e != null) {
            b("Attributes incorrectly present on end tag");
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        if (this.o == null) {
            this.o = str;
            return;
        }
        if (this.p.length() == 0) {
            this.p.append(this.o);
        }
        this.p.append(str);
    }

    /* access modifiers changed from: package-private */
    public void a(char[] cArr) {
        a(String.valueOf(cArr));
    }

    /* access modifiers changed from: package-private */
    public void a(int[] iArr) {
        a(new String(iArr, 0, iArr.length));
    }

    /* access modifiers changed from: package-private */
    public void a(char c2) {
        a(String.valueOf(c2));
    }

    /* access modifiers changed from: package-private */
    public TokeniserState b() {
        return this.l;
    }

    /* access modifiers changed from: package-private */
    public void a(TokeniserState tokeniserState) {
        this.l = tokeniserState;
    }

    /* access modifiers changed from: package-private */
    public void b(TokeniserState tokeniserState) {
        this.j.f();
        this.l = tokeniserState;
    }

    /* access modifiers changed from: package-private */
    public int[] a(Character ch, boolean z) {
        int i2;
        if (this.j.b()) {
            return null;
        }
        if ((ch != null && ch.charValue() == this.j.c()) || this.j.d(i)) {
            return null;
        }
        int[] iArr = this.r;
        this.j.g();
        if (this.j.d("#")) {
            boolean e2 = this.j.e("X");
            String n2 = e2 ? this.j.n() : this.j.o();
            if (n2.length() == 0) {
                c("numeric reference with no numerals");
                this.j.h();
                return null;
            }
            if (!this.j.d(i.b)) {
                c("missing semicolon");
            }
            try {
                i2 = Integer.valueOf(n2, e2 ? 16 : 10).intValue();
            } catch (NumberFormatException unused) {
                i2 = -1;
            }
            if (i2 == -1 || ((i2 >= 55296 && i2 <= 57343) || i2 > 1114111)) {
                c("character outside of valid range");
                iArr[0] = 65533;
                return iArr;
            }
            iArr[0] = i2;
            return iArr;
        }
        String m2 = this.j.m();
        boolean c2 = this.j.c(';');
        if (!(Entities.b(m2) || (Entities.a(m2) && c2))) {
            this.j.h();
            if (c2) {
                c(String.format("invalid named referenece '%s'", new Object[]{m2}));
            }
            return null;
        } else if (!z || (!this.j.p() && !this.j.q() && !this.j.c('=', '-', '_'))) {
            if (!this.j.d(i.b)) {
                c("missing semicolon");
            }
            int a2 = Entities.a(m2, this.s);
            if (a2 == 1) {
                iArr[0] = this.s[0];
                return iArr;
            } else if (a2 == 2) {
                return this.s;
            } else {
                Validate.b("Unexpected characters returned for " + m2);
                return this.s;
            }
        } else {
            this.j.h();
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public Token.Tag a(boolean z) {
        this.c = z ? this.d.b() : this.e.b();
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public void c() {
        this.c.p();
        a((Token) this.c);
    }

    /* access modifiers changed from: package-private */
    public void d() {
        this.h.b();
    }

    /* access modifiers changed from: package-private */
    public void e() {
        a((Token) this.h);
    }

    /* access modifiers changed from: package-private */
    public void f() {
        this.g.b();
    }

    /* access modifiers changed from: package-private */
    public void g() {
        a((Token) this.g);
    }

    /* access modifiers changed from: package-private */
    public void h() {
        Token.a(this.b);
    }

    /* access modifiers changed from: package-private */
    public boolean i() {
        return this.q != null && this.c.q().equalsIgnoreCase(this.q);
    }

    /* access modifiers changed from: package-private */
    public String j() {
        return this.q;
    }

    /* access modifiers changed from: package-private */
    public void c(TokeniserState tokeniserState) {
        if (this.k.canAddError()) {
            this.k.add(new ParseError(this.j.a(), "Unexpected character '%s' in input state [%s]", Character.valueOf(this.j.c()), tokeniserState));
        }
    }

    /* access modifiers changed from: package-private */
    public void d(TokeniserState tokeniserState) {
        if (this.k.canAddError()) {
            this.k.add(new ParseError(this.j.a(), "Unexpectedly reached end of file (EOF) in input state [%s]", tokeniserState));
        }
    }

    private void c(String str) {
        if (this.k.canAddError()) {
            this.k.add(new ParseError(this.j.a(), "Invalid character reference: %s", str));
        }
    }

    /* access modifiers changed from: package-private */
    public void b(String str) {
        if (this.k.canAddError()) {
            this.k.add(new ParseError(this.j.a(), str));
        }
    }

    /* access modifiers changed from: package-private */
    public String b(boolean z) {
        StringBuilder a2 = StringUtil.a();
        while (!this.j.b()) {
            a2.append(this.j.b((char) Typography.c));
            if (this.j.c((char) Typography.c)) {
                this.j.d();
                int[] a3 = a((Character) null, z);
                if (a3 == null || a3.length == 0) {
                    a2.append(Typography.c);
                } else {
                    a2.appendCodePoint(a3[0]);
                    if (a3.length == 2) {
                        a2.appendCodePoint(a3[1]);
                    }
                }
            }
        }
        return a2.toString();
    }
}
