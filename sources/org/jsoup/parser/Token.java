package org.jsoup.parser;

import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.nodes.Attributes;

abstract class Token {

    /* renamed from: a  reason: collision with root package name */
    TokenType f3683a;

    enum TokenType {
        Doctype,
        StartTag,
        EndTag,
        Comment,
        Character,
        EOF
    }

    /* access modifiers changed from: package-private */
    public abstract Token b();

    private Token() {
    }

    /* access modifiers changed from: package-private */
    public String a() {
        return getClass().getSimpleName();
    }

    static void a(StringBuilder sb) {
        if (sb != null) {
            sb.delete(0, sb.length());
        }
    }

    static final class Doctype extends Token {
        final StringBuilder b = new StringBuilder();
        String c = null;
        final StringBuilder d = new StringBuilder();
        final StringBuilder e = new StringBuilder();
        boolean f = false;

        Doctype() {
            super();
            this.f3683a = TokenType.Doctype;
        }

        /* access modifiers changed from: package-private */
        public Token b() {
            a(this.b);
            this.c = null;
            a(this.d);
            a(this.e);
            this.f = false;
            return this;
        }

        /* access modifiers changed from: package-private */
        public String n() {
            return this.b.toString();
        }

        /* access modifiers changed from: package-private */
        public String o() {
            return this.c;
        }

        /* access modifiers changed from: package-private */
        public String p() {
            return this.d.toString();
        }

        public String q() {
            return this.e.toString();
        }

        public boolean r() {
            return this.f;
        }
    }

    static abstract class Tag extends Token {
        protected String b;
        protected String c;
        boolean d = false;
        Attributes e;
        private String f;
        private StringBuilder g = new StringBuilder();
        private String h;
        private boolean i = false;
        private boolean j = false;

        Tag() {
            super();
        }

        /* access modifiers changed from: package-private */
        /* renamed from: n */
        public Tag b() {
            this.b = null;
            this.c = null;
            this.f = null;
            a(this.g);
            this.h = null;
            this.i = false;
            this.j = false;
            this.d = false;
            this.e = null;
            return this;
        }

        /* access modifiers changed from: package-private */
        public final void o() {
            String str;
            if (this.e == null) {
                this.e = new Attributes();
            }
            if (this.f != null) {
                this.f = this.f.trim();
                if (this.f.length() > 0) {
                    if (this.j) {
                        str = this.g.length() > 0 ? this.g.toString() : this.h;
                    } else {
                        str = this.i ? "" : null;
                    }
                    this.e.a(this.f, str);
                }
            }
            this.f = null;
            this.i = false;
            this.j = false;
            a(this.g);
            this.h = null;
        }

        /* access modifiers changed from: package-private */
        public final void p() {
            if (this.f != null) {
                o();
            }
        }

        /* access modifiers changed from: package-private */
        public final String q() {
            Validate.b(this.b == null || this.b.length() == 0);
            return this.b;
        }

        /* access modifiers changed from: package-private */
        public final String r() {
            return this.c;
        }

        /* access modifiers changed from: package-private */
        public final Tag a(String str) {
            this.b = str;
            this.c = Normalizer.a(str);
            return this;
        }

        /* access modifiers changed from: package-private */
        public final boolean s() {
            return this.d;
        }

        /* access modifiers changed from: package-private */
        public final Attributes t() {
            return this.e;
        }

        /* access modifiers changed from: package-private */
        public final void b(String str) {
            if (this.b != null) {
                str = this.b.concat(str);
            }
            this.b = str;
            this.c = Normalizer.a(this.b);
        }

        /* access modifiers changed from: package-private */
        public final void a(char c2) {
            b(String.valueOf(c2));
        }

        /* access modifiers changed from: package-private */
        public final void c(String str) {
            if (this.f != null) {
                str = this.f.concat(str);
            }
            this.f = str;
        }

        /* access modifiers changed from: package-private */
        public final void b(char c2) {
            c(String.valueOf(c2));
        }

        /* access modifiers changed from: package-private */
        public final void d(String str) {
            v();
            if (this.g.length() == 0) {
                this.h = str;
            } else {
                this.g.append(str);
            }
        }

        /* access modifiers changed from: package-private */
        public final void c(char c2) {
            v();
            this.g.append(c2);
        }

        /* access modifiers changed from: package-private */
        public final void a(char[] cArr) {
            v();
            this.g.append(cArr);
        }

        /* access modifiers changed from: package-private */
        public final void a(int[] iArr) {
            v();
            for (int appendCodePoint : iArr) {
                this.g.appendCodePoint(appendCodePoint);
            }
        }

        /* access modifiers changed from: package-private */
        public final void u() {
            this.i = true;
        }

        private void v() {
            this.j = true;
            if (this.h != null) {
                this.g.append(this.h);
                this.h = null;
            }
        }
    }

    static final class StartTag extends Tag {
        StartTag() {
            this.e = new Attributes();
            this.f3683a = TokenType.StartTag;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: n */
        public Tag b() {
            super.b();
            this.e = new Attributes();
            return this;
        }

        /* access modifiers changed from: package-private */
        public StartTag a(String str, Attributes attributes) {
            this.b = str;
            this.e = attributes;
            this.c = Normalizer.a(this.b);
            return this;
        }

        public String toString() {
            if (this.e == null || this.e.a() <= 0) {
                return "<" + q() + ">";
            }
            return "<" + q() + " " + this.e.toString() + ">";
        }
    }

    static final class EndTag extends Tag {
        EndTag() {
            this.f3683a = TokenType.EndTag;
        }

        public String toString() {
            return "</" + q() + ">";
        }
    }

    static final class Comment extends Token {
        final StringBuilder b = new StringBuilder();
        boolean c = false;

        /* access modifiers changed from: package-private */
        public Token b() {
            a(this.b);
            this.c = false;
            return this;
        }

        Comment() {
            super();
            this.f3683a = TokenType.Comment;
        }

        /* access modifiers changed from: package-private */
        public String n() {
            return this.b.toString();
        }

        public String toString() {
            return "<!--" + n() + "-->";
        }
    }

    static final class Character extends Token {
        private String b;

        Character() {
            super();
            this.f3683a = TokenType.Character;
        }

        /* access modifiers changed from: package-private */
        public Token b() {
            this.b = null;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Character a(String str) {
            this.b = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public String n() {
            return this.b;
        }

        public String toString() {
            return n();
        }
    }

    static final class EOF extends Token {
        /* access modifiers changed from: package-private */
        public Token b() {
            return this;
        }

        EOF() {
            super();
            this.f3683a = TokenType.EOF;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean c() {
        return this.f3683a == TokenType.Doctype;
    }

    /* access modifiers changed from: package-private */
    public final Doctype d() {
        return (Doctype) this;
    }

    /* access modifiers changed from: package-private */
    public final boolean e() {
        return this.f3683a == TokenType.StartTag;
    }

    /* access modifiers changed from: package-private */
    public final StartTag f() {
        return (StartTag) this;
    }

    /* access modifiers changed from: package-private */
    public final boolean g() {
        return this.f3683a == TokenType.EndTag;
    }

    /* access modifiers changed from: package-private */
    public final EndTag h() {
        return (EndTag) this;
    }

    /* access modifiers changed from: package-private */
    public final boolean i() {
        return this.f3683a == TokenType.Comment;
    }

    /* access modifiers changed from: package-private */
    public final Comment j() {
        return (Comment) this;
    }

    /* access modifiers changed from: package-private */
    public final boolean k() {
        return this.f3683a == TokenType.Character;
    }

    /* access modifiers changed from: package-private */
    public final Character l() {
        return (Character) this;
    }

    /* access modifiers changed from: package-private */
    public final boolean m() {
        return this.f3683a == TokenType.EOF;
    }
}
