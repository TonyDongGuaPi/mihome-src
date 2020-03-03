package org.jsoup.nodes;

import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.ui.component.WXBasicComponentType;
import com.xiaomi.smarthome.constants.AppConstants;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import kotlin.text.Typography;
import org.jsoup.helper.ChangeNotifyingArrayList;
import org.jsoup.helper.StringUtil;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.nodes.Document;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.jsoup.parser.Tag;
import org.jsoup.select.Collector;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;
import org.jsoup.select.QueryParser;
import org.jsoup.select.Selector;

public class Element extends Node {
    private static final List<Node> b = Collections.emptyList();
    private static final Pattern c = Pattern.compile("\\s+");

    /* renamed from: a  reason: collision with root package name */
    List<Node> f3665a;
    /* access modifiers changed from: private */
    public Tag g;
    private WeakReference<List<Element>> h;
    private Attributes i;
    private String j;

    public Element(String str) {
        this(Tag.valueOf(str), "", new Attributes());
    }

    public Element(Tag tag, String str, Attributes attributes) {
        Validate.a((Object) tag);
        Validate.a((Object) str);
        this.f3665a = b;
        this.j = str;
        this.i = attributes;
        this.g = tag;
    }

    public Element(Tag tag, String str) {
        this(tag, str, (Attributes) null);
    }

    /* access modifiers changed from: protected */
    public List<Node> q() {
        if (this.f3665a == b) {
            this.f3665a = new NodeList(this, 4);
        }
        return this.f3665a;
    }

    /* access modifiers changed from: protected */
    public boolean r() {
        return this.i != null;
    }

    public Attributes s() {
        if (!r()) {
            this.i = new Attributes();
        }
        return this.i;
    }

    public String d() {
        return this.j;
    }

    /* access modifiers changed from: protected */
    public void i(String str) {
        this.j = str;
    }

    public int c() {
        return this.f3665a.size();
    }

    public String a() {
        return this.g.a();
    }

    public String t() {
        return this.g.a();
    }

    public Element j(String str) {
        Validate.a(str, "Tag name must not be empty.");
        this.g = Tag.a(str, ParseSettings.b);
        return this;
    }

    public Tag u() {
        return this.g;
    }

    public boolean v() {
        return this.g.b();
    }

    public String w() {
        return s().d("id");
    }

    /* renamed from: b */
    public Element a(String str, String str2) {
        super.a(str, str2);
        return this;
    }

    public Element a(String str, boolean z) {
        s().a(str, z);
        return this;
    }

    public Map<String, String> x() {
        return s().c();
    }

    /* renamed from: y */
    public final Element Y() {
        return (Element) this.e;
    }

    public Elements z() {
        Elements elements = new Elements();
        a(this, elements);
        return elements;
    }

    private static void a(Element element, Elements elements) {
        Element y = element.Y();
        if (y != null && !y.t().equals("#root")) {
            elements.add(y);
            a(y, elements);
        }
    }

    public Element a(int i2) {
        return b().get(i2);
    }

    public Elements A() {
        return new Elements(b());
    }

    private List<Element> b() {
        List<Element> list;
        if (this.h != null && (list = (List) this.h.get()) != null) {
            return list;
        }
        int size = this.f3665a.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i2 = 0; i2 < size; i2++) {
            Node node = this.f3665a.get(i2);
            if (node instanceof Element) {
                arrayList.add((Element) node);
            }
        }
        this.h = new WeakReference<>(arrayList);
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public void B() {
        super.B();
        this.h = null;
    }

    public List<TextNode> C() {
        ArrayList arrayList = new ArrayList();
        for (Node next : this.f3665a) {
            if (next instanceof TextNode) {
                arrayList.add((TextNode) next);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public List<DataNode> D() {
        ArrayList arrayList = new ArrayList();
        for (Node next : this.f3665a) {
            if (next instanceof DataNode) {
                arrayList.add((DataNode) next);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public Elements k(String str) {
        return Selector.a(str, this);
    }

    public Element l(String str) {
        return Selector.b(str, this);
    }

    public boolean m(String str) {
        return a(QueryParser.a(str));
    }

    public boolean a(Evaluator evaluator) {
        return evaluator.a((Element) af(), this);
    }

    public Element a(Node node) {
        Validate.a((Object) node);
        m(node);
        q();
        this.f3665a.add(node);
        node.f(this.f3665a.size() - 1);
        return this;
    }

    public Element a(Element element) {
        Validate.a((Object) element);
        element.a((Node) this);
        return this;
    }

    public Element b(Node node) {
        Validate.a((Object) node);
        b(0, node);
        return this;
    }

    public Element a(int i2, Collection<? extends Node> collection) {
        Validate.a((Object) collection, "Children collection to be inserted must not be null.");
        int c2 = c();
        if (i2 < 0) {
            i2 += c2 + 1;
        }
        Validate.a(i2 >= 0 && i2 <= c2, "Insert position out of bounds.");
        ArrayList arrayList = new ArrayList(collection);
        b(i2, (Node[]) arrayList.toArray(new Node[arrayList.size()]));
        return this;
    }

    public Element a(int i2, Node... nodeArr) {
        Validate.a((Object) nodeArr, "Children collection to be inserted must not be null.");
        int c2 = c();
        if (i2 < 0) {
            i2 += c2 + 1;
        }
        Validate.a(i2 >= 0 && i2 <= c2, "Insert position out of bounds.");
        b(i2, nodeArr);
        return this;
    }

    public Element n(String str) {
        Element element = new Element(Tag.valueOf(str), d());
        a((Node) element);
        return element;
    }

    public Element o(String str) {
        Element element = new Element(Tag.valueOf(str), d());
        b((Node) element);
        return element;
    }

    public Element p(String str) {
        Validate.a((Object) str);
        a((Node) new TextNode(str));
        return this;
    }

    public Element q(String str) {
        Validate.a((Object) str);
        b((Node) new TextNode(str));
        return this;
    }

    public Element r(String str) {
        Validate.a((Object) str);
        List<Node> a2 = Parser.a(str, this, d());
        a((Node[]) a2.toArray(new Node[a2.size()]));
        return this;
    }

    public Element s(String str) {
        Validate.a((Object) str);
        List<Node> a2 = Parser.a(str, this, d());
        b(0, (Node[]) a2.toArray(new Node[a2.size()]));
        return this;
    }

    /* renamed from: t */
    public Element N(String str) {
        return (Element) super.N(str);
    }

    /* renamed from: c */
    public Element i(Node node) {
        return (Element) super.i(node);
    }

    /* renamed from: u */
    public Element M(String str) {
        return (Element) super.M(str);
    }

    /* renamed from: d */
    public Element h(Node node) {
        return (Element) super.h(node);
    }

    public Element E() {
        this.f3665a.clear();
        return this;
    }

    /* renamed from: v */
    public Element L(String str) {
        return (Element) super.L(str);
    }

    public String F() {
        if (w().length() > 0) {
            return "#" + w();
        }
        StringBuilder sb = new StringBuilder(t().replace(Operators.CONDITION_IF_MIDDLE, '|'));
        String a2 = StringUtil.a((Collection) T(), ".");
        if (a2.length() > 0) {
            sb.append('.');
            sb.append(a2);
        }
        if (Y() == null || (Y() instanceof Document)) {
            return sb.toString();
        }
        sb.insert(0, " > ");
        if (Y().k(sb.toString()).size() > 1) {
            sb.append(String.format(":nth-child(%d)", new Object[]{Integer.valueOf(K() + 1)}));
        }
        return Y().F() + sb.toString();
    }

    public Elements G() {
        if (this.e == null) {
            return new Elements(0);
        }
        List<Element> b2 = Y().b();
        Elements elements = new Elements(b2.size() - 1);
        for (Element next : b2) {
            if (next != this) {
                elements.add(next);
            }
        }
        return elements;
    }

    public Element H() {
        if (this.e == null) {
            return null;
        }
        List<Element> b2 = Y().b();
        Integer valueOf = Integer.valueOf(a(this, b2));
        Validate.a((Object) valueOf);
        if (b2.size() > valueOf.intValue() + 1) {
            return b2.get(valueOf.intValue() + 1);
        }
        return null;
    }

    public Element I() {
        if (this.e == null) {
            return null;
        }
        List<Element> b2 = Y().b();
        Integer valueOf = Integer.valueOf(a(this, b2));
        Validate.a((Object) valueOf);
        if (valueOf.intValue() > 0) {
            return b2.get(valueOf.intValue() - 1);
        }
        return null;
    }

    public Element J() {
        List<Element> b2 = Y().b();
        if (b2.size() > 1) {
            return b2.get(0);
        }
        return null;
    }

    public int K() {
        if (Y() == null) {
            return 0;
        }
        return a(this, Y().b());
    }

    public Element L() {
        List<Element> b2 = Y().b();
        if (b2.size() > 1) {
            return b2.get(b2.size() - 1);
        }
        return null;
    }

    private static <E extends Element> int a(Element element, List<E> list) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (list.get(i2) == element) {
                return i2;
            }
        }
        return 0;
    }

    public Elements w(String str) {
        Validate.a(str);
        return Collector.a(new Evaluator.Tag(Normalizer.b(str)), this);
    }

    public Element x(String str) {
        Validate.a(str);
        Elements a2 = Collector.a(new Evaluator.Id(str), this);
        if (a2.size() > 0) {
            return (Element) a2.get(0);
        }
        return null;
    }

    public Elements y(String str) {
        Validate.a(str);
        return Collector.a(new Evaluator.Class(str), this);
    }

    public Elements z(String str) {
        Validate.a(str);
        return Collector.a(new Evaluator.Attribute(str.trim()), this);
    }

    public Elements A(String str) {
        Validate.a(str);
        return Collector.a(new Evaluator.AttributeStarting(str.trim()), this);
    }

    public Elements c(String str, String str2) {
        return Collector.a(new Evaluator.AttributeWithValue(str, str2), this);
    }

    public Elements d(String str, String str2) {
        return Collector.a(new Evaluator.AttributeWithValueNot(str, str2), this);
    }

    public Elements e(String str, String str2) {
        return Collector.a(new Evaluator.AttributeWithValueStarting(str, str2), this);
    }

    public Elements f(String str, String str2) {
        return Collector.a(new Evaluator.AttributeWithValueEnding(str, str2), this);
    }

    public Elements g(String str, String str2) {
        return Collector.a(new Evaluator.AttributeWithValueContaining(str, str2), this);
    }

    public Elements a(String str, Pattern pattern) {
        return Collector.a(new Evaluator.AttributeWithValueMatching(str, pattern), this);
    }

    public Elements h(String str, String str2) {
        try {
            return a(str, Pattern.compile(str2));
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Pattern syntax error: " + str2, e);
        }
    }

    public Elements b(int i2) {
        return Collector.a(new Evaluator.IndexLessThan(i2), this);
    }

    public Elements c(int i2) {
        return Collector.a(new Evaluator.IndexGreaterThan(i2), this);
    }

    public Elements d(int i2) {
        return Collector.a(new Evaluator.IndexEquals(i2), this);
    }

    public Elements B(String str) {
        return Collector.a(new Evaluator.ContainsText(str), this);
    }

    public Elements C(String str) {
        return Collector.a(new Evaluator.ContainsOwnText(str), this);
    }

    public Elements a(Pattern pattern) {
        return Collector.a(new Evaluator.Matches(pattern), this);
    }

    public Elements D(String str) {
        try {
            return a(Pattern.compile(str));
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Pattern syntax error: " + str, e);
        }
    }

    public Elements b(Pattern pattern) {
        return Collector.a(new Evaluator.MatchesOwn(pattern), this);
    }

    public Elements E(String str) {
        try {
            return b(Pattern.compile(str));
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Pattern syntax error: " + str, e);
        }
    }

    public Elements M() {
        return Collector.a(new Evaluator.AllElements(), this);
    }

    public String N() {
        final StringBuilder sb = new StringBuilder();
        NodeTraversor.a((NodeVisitor) new NodeVisitor() {
            public void b(Node node, int i) {
            }

            public void a(Node node, int i) {
                if (node instanceof TextNode) {
                    Element.b(sb, (TextNode) node);
                } else if (node instanceof Element) {
                    Element element = (Element) node;
                    if (sb.length() <= 0) {
                        return;
                    }
                    if ((element.v() || element.g.a().equals(TtmlNode.TAG_BR)) && !TextNode.a(sb)) {
                        sb.append(' ');
                    }
                }
            }
        }, (Node) this);
        return sb.toString().trim();
    }

    public String O() {
        final StringBuilder sb = new StringBuilder();
        NodeTraversor.a((NodeVisitor) new NodeVisitor() {
            public void b(Node node, int i) {
            }

            public void a(Node node, int i) {
                if (node instanceof TextNode) {
                    sb.append(((TextNode) node).f());
                }
            }
        }, (Node) this);
        return sb.toString();
    }

    public String P() {
        StringBuilder sb = new StringBuilder();
        a(sb);
        return sb.toString().trim();
    }

    private void a(StringBuilder sb) {
        for (Node next : this.f3665a) {
            if (next instanceof TextNode) {
                b(sb, (TextNode) next);
            } else if (next instanceof Element) {
                a((Element) next, sb);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void b(StringBuilder sb, TextNode textNode) {
        String f = textNode.f();
        if (e(textNode.e)) {
            sb.append(f);
        } else {
            StringUtil.a(sb, f, TextNode.a(sb));
        }
    }

    private static void a(Element element, StringBuilder sb) {
        if (element.g.a().equals(TtmlNode.TAG_BR) && !TextNode.a(sb)) {
            sb.append(" ");
        }
    }

    static boolean e(Node node) {
        if (node != null && (node instanceof Element)) {
            Element element = (Element) node;
            int i2 = 0;
            while (!element.g.j()) {
                element = element.Y();
                i2++;
                if (i2 < 6) {
                    if (element == null) {
                    }
                }
            }
            return true;
        }
        return false;
    }

    public Element h(String str) {
        Validate.a((Object) str);
        E();
        a((Node) new TextNode(str));
        return this;
    }

    public boolean Q() {
        for (Node next : this.f3665a) {
            if (next instanceof TextNode) {
                if (!((TextNode) next).g()) {
                    return true;
                }
            } else if ((next instanceof Element) && ((Element) next).Q()) {
                return true;
            }
        }
        return false;
    }

    public String R() {
        StringBuilder sb = new StringBuilder();
        for (Node next : this.f3665a) {
            if (next instanceof DataNode) {
                sb.append(((DataNode) next).b());
            } else if (next instanceof Comment) {
                sb.append(((Comment) next).b());
            } else if (next instanceof Element) {
                sb.append(((Element) next).R());
            }
        }
        return sb.toString();
    }

    public String S() {
        return d(AppConstants.x).trim();
    }

    public Set<String> T() {
        LinkedHashSet linkedHashSet = new LinkedHashSet(Arrays.asList(c.split(S())));
        linkedHashSet.remove("");
        return linkedHashSet;
    }

    public Element a(Set<String> set) {
        Validate.a((Object) set);
        if (set.isEmpty()) {
            s().e(AppConstants.x);
        } else {
            s().a(AppConstants.x, StringUtil.a((Collection) set, " "));
        }
        return this;
    }

    public boolean F(String str) {
        String d = s().d(AppConstants.x);
        int length = d.length();
        int length2 = str.length();
        if (length == 0 || length < length2) {
            return false;
        }
        if (length == length2) {
            return str.equalsIgnoreCase(d);
        }
        boolean z = false;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            if (Character.isWhitespace(d.charAt(i3))) {
                if (!z) {
                    continue;
                } else if (i3 - i2 == length2 && d.regionMatches(true, i2, str, 0, length2)) {
                    return true;
                } else {
                    z = false;
                }
            } else if (!z) {
                i2 = i3;
                z = true;
            }
        }
        if (!z || length - i2 != length2) {
            return false;
        }
        return d.regionMatches(true, i2, str, 0, length2);
    }

    public Element G(String str) {
        Validate.a((Object) str);
        Set<String> T = T();
        T.add(str);
        a(T);
        return this;
    }

    public Element H(String str) {
        Validate.a((Object) str);
        Set<String> T = T();
        T.remove(str);
        a(T);
        return this;
    }

    public Element I(String str) {
        Validate.a((Object) str);
        Set<String> T = T();
        if (T.contains(str)) {
            T.remove(str);
        } else {
            T.add(str);
        }
        a(T);
        return this;
    }

    public String U() {
        if (t().equals(WXBasicComponentType.TEXTAREA)) {
            return N();
        }
        return d("value");
    }

    public Element J(String str) {
        if (t().equals(WXBasicComponentType.TEXTAREA)) {
            h(str);
        } else {
            a("value", str);
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public void a(Appendable appendable, int i2, Document.OutputSettings outputSettings) throws IOException {
        if (outputSettings.f() && (this.g.c() || ((Y() != null && Y().u().c()) || outputSettings.g()))) {
            if (!(appendable instanceof StringBuilder)) {
                c(appendable, i2, outputSettings);
            } else if (((StringBuilder) appendable).length() > 0) {
                c(appendable, i2, outputSettings);
            }
        }
        appendable.append(Typography.d).append(t());
        if (this.i != null) {
            this.i.a(appendable, outputSettings);
        }
        if (!this.f3665a.isEmpty() || !this.g.h()) {
            appendable.append(Typography.e);
        } else if (outputSettings.e() != Document.OutputSettings.Syntax.html || !this.g.g()) {
            appendable.append(" />");
        } else {
            appendable.append(Typography.e);
        }
    }

    /* access modifiers changed from: package-private */
    public void b(Appendable appendable, int i2, Document.OutputSettings outputSettings) throws IOException {
        if (!this.f3665a.isEmpty() || !this.g.h()) {
            if (outputSettings.f() && !this.f3665a.isEmpty() && (this.g.c() || (outputSettings.g() && (this.f3665a.size() > 1 || (this.f3665a.size() == 1 && !(this.f3665a.get(0) instanceof TextNode)))))) {
                c(appendable, i2, outputSettings);
            }
            appendable.append("</").append(t()).append(Typography.e);
        }
    }

    public String V() {
        StringBuilder a2 = StringUtil.a();
        b(a2);
        return an().f() ? a2.toString().trim() : a2.toString();
    }

    private void b(StringBuilder sb) {
        for (Node b2 : this.f3665a) {
            b2.b((Appendable) sb);
        }
    }

    public <T extends Appendable> T a(T t) {
        for (Node b2 : this.f3665a) {
            b2.b((Appendable) t);
        }
        return t;
    }

    public Element K(String str) {
        E();
        r(str);
        return this;
    }

    public String toString() {
        return i();
    }

    /* renamed from: o */
    public Element p() {
        return (Element) super.clone();
    }

    /* renamed from: W */
    public Element X() {
        return new Element(this.g, this.j, this.i);
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public Element g(Node node) {
        Element element = (Element) super.g(node);
        element.i = this.i != null ? this.i.clone() : null;
        element.j = this.j;
        element.f3665a = new NodeList(element, this.f3665a.size());
        element.f3665a.addAll(this.f3665a);
        return element;
    }

    private static final class NodeList extends ChangeNotifyingArrayList<Node> {
        private final Element owner;

        NodeList(Element element, int i) {
            super(i);
            this.owner = element;
        }

        public void onContentsChanged() {
            this.owner.B();
        }
    }
}
