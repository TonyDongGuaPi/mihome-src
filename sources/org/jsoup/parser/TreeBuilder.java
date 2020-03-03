package org.jsoup.parser;

import java.io.Reader;
import java.util.ArrayList;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Token;

abstract class TreeBuilder {

    /* renamed from: a  reason: collision with root package name */
    private Token.StartTag f3686a = new Token.StartTag();
    private Token.EndTag b = new Token.EndTag();
    CharacterReader j;
    Tokeniser k;
    protected Document l;
    protected ArrayList<Element> m;
    protected String n;
    protected Token o;
    protected ParseErrorList p;
    protected ParseSettings q;

    /* access modifiers changed from: package-private */
    public abstract ParseSettings a();

    /* access modifiers changed from: protected */
    public abstract boolean a(Token token);

    TreeBuilder() {
    }

    /* access modifiers changed from: protected */
    public void a(Reader reader, String str, ParseErrorList parseErrorList, ParseSettings parseSettings) {
        Validate.a((Object) reader, "String input must not be null");
        Validate.a((Object) str, "BaseURI must not be null");
        this.l = new Document(str);
        this.q = parseSettings;
        this.j = new CharacterReader(reader);
        this.p = parseErrorList;
        this.o = null;
        this.k = new Tokeniser(this.j, parseErrorList);
        this.m = new ArrayList<>(32);
        this.n = str;
    }

    /* access modifiers changed from: package-private */
    public Document b(Reader reader, String str, ParseErrorList parseErrorList, ParseSettings parseSettings) {
        a(reader, str, parseErrorList, parseSettings);
        z();
        return this.l;
    }

    /* access modifiers changed from: protected */
    public void z() {
        Token a2;
        do {
            a2 = this.k.a();
            a(a2);
            a2.b();
        } while (a2.f3683a != Token.TokenType.EOF);
    }

    /* access modifiers changed from: protected */
    public boolean l(String str) {
        if (this.o == this.f3686a) {
            return a(new Token.StartTag().a(str));
        }
        return a(this.f3686a.b().a(str));
    }

    public boolean a(String str, Attributes attributes) {
        if (this.o == this.f3686a) {
            return a(new Token.StartTag().a(str, attributes));
        }
        this.f3686a.b();
        this.f3686a.a(str, attributes);
        return a(this.f3686a);
    }

    /* access modifiers changed from: protected */
    public boolean m(String str) {
        if (this.o == this.b) {
            return a(new Token.EndTag().a(str));
        }
        return a(this.b.b().a(str));
    }

    /* access modifiers changed from: protected */
    public Element A() {
        int size = this.m.size();
        if (size > 0) {
            return this.m.get(size - 1);
        }
        return null;
    }
}
