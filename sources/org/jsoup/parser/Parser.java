package org.jsoup.parser;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public class Parser {

    /* renamed from: a  reason: collision with root package name */
    private static final int f3681a = 0;
    private TreeBuilder b;
    private int c = 0;
    private ParseErrorList d;
    private ParseSettings e;

    public Parser(TreeBuilder treeBuilder) {
        this.b = treeBuilder;
        this.e = treeBuilder.a();
    }

    public Document a(String str, String str2) {
        this.d = b() ? ParseErrorList.tracking(this.c) : ParseErrorList.noTracking();
        return this.b.b(new StringReader(str), str2, this.d, this.e);
    }

    public Document a(Reader reader, String str) {
        this.d = b() ? ParseErrorList.tracking(this.c) : ParseErrorList.noTracking();
        return this.b.b(reader, str, this.d, this.e);
    }

    public TreeBuilder a() {
        return this.b;
    }

    public Parser a(TreeBuilder treeBuilder) {
        this.b = treeBuilder;
        return this;
    }

    public boolean b() {
        return this.c > 0;
    }

    public Parser a(int i) {
        this.c = i;
        return this;
    }

    public List<ParseError> c() {
        return this.d;
    }

    public Parser a(ParseSettings parseSettings) {
        this.e = parseSettings;
        return this;
    }

    public ParseSettings d() {
        return this.e;
    }

    public static Document b(String str, String str2) {
        HtmlTreeBuilder htmlTreeBuilder = new HtmlTreeBuilder();
        return htmlTreeBuilder.b(new StringReader(str), str2, ParseErrorList.noTracking(), htmlTreeBuilder.a());
    }

    public static List<Node> a(String str, Element element, String str2) {
        HtmlTreeBuilder htmlTreeBuilder = new HtmlTreeBuilder();
        return htmlTreeBuilder.a(str, element, str2, ParseErrorList.noTracking(), htmlTreeBuilder.a());
    }

    public static List<Node> a(String str, Element element, String str2, ParseErrorList parseErrorList) {
        HtmlTreeBuilder htmlTreeBuilder = new HtmlTreeBuilder();
        return htmlTreeBuilder.a(str, element, str2, parseErrorList, htmlTreeBuilder.a());
    }

    public static List<Node> c(String str, String str2) {
        XmlTreeBuilder xmlTreeBuilder = new XmlTreeBuilder();
        return xmlTreeBuilder.a(str, str2, ParseErrorList.noTracking(), xmlTreeBuilder.a());
    }

    public static Document d(String str, String str2) {
        Document e2 = Document.e(str2);
        Element f = e2.f();
        List<Node> a2 = a(str, f, str2);
        Node[] nodeArr = (Node[]) a2.toArray(new Node[a2.size()]);
        for (int length = nodeArr.length - 1; length > 0; length--) {
            nodeArr[length].ah();
        }
        for (Node a3 : nodeArr) {
            f.a(a3);
        }
        return e2;
    }

    public static String a(String str, boolean z) {
        return new Tokeniser(new CharacterReader(str), ParseErrorList.noTracking()).b(z);
    }

    public static Document e(String str, String str2) {
        return b(str, str2);
    }

    public static Parser e() {
        return new Parser(new HtmlTreeBuilder());
    }

    public static Parser f() {
        return new Parser(new XmlTreeBuilder());
    }
}
