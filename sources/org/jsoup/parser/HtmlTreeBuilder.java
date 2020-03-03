package org.jsoup.parser;

import com.alipay.mobile.security.bio.workspace.Env;
import com.alipay.sdk.cons.c;
import com.coloros.mcssdk.mode.CommandMessage;
import com.facebook.share.internal.ShareConstants;
import com.mi.global.shop.model.Tags;
import com.mi.mistatistic.sdk.data.EventData;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.ui.component.WXBasicComponentType;
import com.tencent.tinker.loader.shareutil.SharePatchInfo;
import com.unionpay.tsmservice.mi.data.Constant;
import com.xiaomi.jr.hybrid.WebEvent;
import com.xiaomi.miot.support.monitor.core.BaseInfo;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.smarthome.core.server.internal.plugin.PluginManager;
import com.xiaomi.stat.d;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jsoup.helper.StringUtil;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.Token;
import org.jsoup.select.Elements;

public class HtmlTreeBuilder extends TreeBuilder {

    /* renamed from: a  reason: collision with root package name */
    static final String[] f3675a = {"applet", ShareConstants.FEED_CAPTION_PARAM, "html", "marquee", EventData.e, "table", "td", "th"};
    static final String[] b = {"ol", "ul"};
    static final String[] c = {"button"};
    static final String[] d = {"html", "table"};
    static final String[] e = {"optgroup", "option"};
    static final String[] f = {d.s, "dt", "li", "optgroup", "option", "p", "rp", "rt"};
    static final String[] g = {"address", "applet", "area", "article", "aside", "base", "basefont", "bgsound", "blockquote", "body", TtmlNode.TAG_BR, "button", ShareConstants.FEED_CAPTION_PARAM, "center", Constant.KEY_COL, "colgroup", CommandMessage.COMMAND, d.s, Tags.MiPhoneDetails.DETAILS, SharePatchInfo.g, "div", "dl", "dt", WXBasicComponentType.EMBED, "fieldset", "figcaption", "figure", WXBasicComponentType.FOOTER, c.c, "frame", "frameset", "h1", "h2", "h3", "h4", PluginManager.h, "h6", TtmlNode.TAG_HEAD, "header", "hgroup", "hr", "html", "iframe", "img", "input", "isindex", "li", "link", "listing", "marquee", "menu", "meta", "nav", "noembed", "noframes", "noscript", EventData.e, "ol", "p", "param", "plaintext", Env.NAME_PRE, WebEvent.A, "section", "select", "style", MibiConstants.ee, "table", "tbody", "td", WXBasicComponentType.TEXTAREA, "tfoot", "th", "thead", "title", BaseInfo.KEY_TIME_RECORD, "ul", "wbr", "xmp"};
    public static final int h = 100;
    static final /* synthetic */ boolean i = (!HtmlTreeBuilder.class.desiredAssertionStatus());
    private boolean A;
    private boolean B;
    private boolean C;
    private String[] D = {null};
    private HtmlTreeBuilderState r;
    private HtmlTreeBuilderState s;
    private boolean t;
    private Element u;
    private FormElement v;
    private Element w;
    private ArrayList<Element> x;
    private List<String> y;
    private Token.EndTag z;

    public /* bridge */ /* synthetic */ boolean a(String str, Attributes attributes) {
        return super.a(str, attributes);
    }

    HtmlTreeBuilder() {
    }

    /* access modifiers changed from: package-private */
    public ParseSettings a() {
        return ParseSettings.f3680a;
    }

    /* access modifiers changed from: protected */
    public void a(Reader reader, String str, ParseErrorList parseErrorList, ParseSettings parseSettings) {
        super.a(reader, str, parseErrorList, parseSettings);
        this.r = HtmlTreeBuilderState.Initial;
        this.s = null;
        this.t = false;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = new ArrayList<>();
        this.y = new ArrayList();
        this.z = new Token.EndTag();
        this.A = true;
        this.B = false;
        this.C = false;
    }

    /* access modifiers changed from: package-private */
    public List<Node> a(String str, Element element, String str2, ParseErrorList parseErrorList, ParseSettings parseSettings) {
        Element element2;
        this.r = HtmlTreeBuilderState.Initial;
        a(new StringReader(str), str2, parseErrorList, parseSettings);
        this.w = element;
        this.C = true;
        if (element != null) {
            if (element.ag() != null) {
                this.l.a(element.ag().n());
            }
            String t2 = element.t();
            if (StringUtil.a(t2, "title", WXBasicComponentType.TEXTAREA)) {
                this.k.a(TokeniserState.Rcdata);
            } else {
                if (StringUtil.a(t2, "iframe", "noembed", "noframes", "style", "xmp")) {
                    this.k.a(TokeniserState.Rawtext);
                } else if (t2.equals(WebEvent.A)) {
                    this.k.a(TokeniserState.ScriptData);
                } else if (t2.equals("noscript")) {
                    this.k.a(TokeniserState.Data);
                } else if (t2.equals("plaintext")) {
                    this.k.a(TokeniserState.Data);
                } else {
                    this.k.a(TokeniserState.Data);
                }
            }
            element2 = new Element(Tag.a("html", parseSettings), str2);
            this.l.a((Node) element2);
            this.m.add(element2);
            n();
            Elements z2 = element.z();
            z2.add(0, element);
            Iterator it = z2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Element element3 = (Element) it.next();
                if (element3 instanceof FormElement) {
                    this.v = (FormElement) element3;
                    break;
                }
            }
        } else {
            element2 = null;
        }
        z();
        if (element != null) {
            return element2.ab();
        }
        return this.l.ab();
    }

    /* access modifiers changed from: protected */
    public boolean a(Token token) {
        this.o = token;
        return this.r.process(token, this);
    }

    /* access modifiers changed from: package-private */
    public boolean a(Token token, HtmlTreeBuilderState htmlTreeBuilderState) {
        this.o = token;
        return htmlTreeBuilderState.process(token, this);
    }

    /* access modifiers changed from: package-private */
    public void a(HtmlTreeBuilderState htmlTreeBuilderState) {
        this.r = htmlTreeBuilderState;
    }

    /* access modifiers changed from: package-private */
    public HtmlTreeBuilderState b() {
        return this.r;
    }

    /* access modifiers changed from: package-private */
    public void c() {
        this.s = this.r;
    }

    /* access modifiers changed from: package-private */
    public HtmlTreeBuilderState d() {
        return this.s;
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z2) {
        this.A = z2;
    }

    /* access modifiers changed from: package-private */
    public boolean e() {
        return this.A;
    }

    /* access modifiers changed from: package-private */
    public Document f() {
        return this.l;
    }

    /* access modifiers changed from: package-private */
    public String g() {
        return this.n;
    }

    /* access modifiers changed from: package-private */
    public void a(Element element) {
        if (!this.t) {
            String a2 = element.a("href");
            if (a2.length() != 0) {
                this.n = a2;
                this.t = true;
                this.l.O(a2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean h() {
        return this.C;
    }

    /* access modifiers changed from: package-private */
    public void b(HtmlTreeBuilderState htmlTreeBuilderState) {
        if (this.p.canAddError()) {
            this.p.add(new ParseError(this.j.a(), "Unexpected token [%s] when in state [%s]", this.o.a(), htmlTreeBuilderState));
        }
    }

    /* access modifiers changed from: package-private */
    public Element a(Token.StartTag startTag) {
        if (startTag.s()) {
            Element b2 = b(startTag);
            this.m.add(b2);
            this.k.a(TokeniserState.Data);
            this.k.a((Token) this.z.b().a(b2.t()));
            return b2;
        }
        Element element = new Element(Tag.a(startTag.q(), this.q), this.n, this.q.a(startTag.e));
        b(element);
        return element;
    }

    /* access modifiers changed from: package-private */
    public Element a(String str) {
        Element element = new Element(Tag.a(str, this.q), this.n);
        b(element);
        return element;
    }

    /* access modifiers changed from: package-private */
    public void b(Element element) {
        b((Node) element);
        this.m.add(element);
    }

    /* access modifiers changed from: package-private */
    public Element b(Token.StartTag startTag) {
        Tag a2 = Tag.a(startTag.q(), this.q);
        Element element = new Element(a2, this.n, startTag.e);
        b((Node) element);
        if (startTag.s()) {
            if (!a2.i()) {
                a2.m();
            } else if (!a2.g()) {
                this.k.b("Tag cannot be self closing; not a void tag");
            }
        }
        return element;
    }

    /* access modifiers changed from: package-private */
    public FormElement a(Token.StartTag startTag, boolean z2) {
        FormElement formElement = new FormElement(Tag.a(startTag.q(), this.q), this.n, startTag.e);
        a(formElement);
        b((Node) formElement);
        if (z2) {
            this.m.add(formElement);
        }
        return formElement;
    }

    /* access modifiers changed from: package-private */
    public void a(Token.Comment comment) {
        b((Node) new Comment(comment.n()));
    }

    /* access modifiers changed from: package-private */
    public void a(Token.Character character) {
        Node node;
        String t2 = A().t();
        if (t2.equals(WebEvent.A) || t2.equals("style")) {
            node = new DataNode(character.n());
        } else {
            node = new TextNode(character.n());
        }
        A().a(node);
    }

    private void b(Node node) {
        if (this.m.size() == 0) {
            this.l.a(node);
        } else if (p()) {
            a(node);
        } else {
            A().a(node);
        }
        if (node instanceof Element) {
            Element element = (Element) node;
            if (element.u().k() && this.v != null) {
                this.v.c(element);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Element i() {
        return (Element) this.m.remove(this.m.size() - 1);
    }

    /* access modifiers changed from: package-private */
    public void c(Element element) {
        this.m.add(element);
    }

    /* access modifiers changed from: package-private */
    public ArrayList<Element> j() {
        return this.m;
    }

    /* access modifiers changed from: package-private */
    public boolean d(Element element) {
        return a((ArrayList<Element>) this.m, element);
    }

    private boolean a(ArrayList<Element> arrayList, Element element) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size) == element) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public Element b(String str) {
        for (int size = this.m.size() - 1; size >= 0; size--) {
            Element element = (Element) this.m.get(size);
            if (element.a().equals(str)) {
                return element;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean e(Element element) {
        for (int size = this.m.size() - 1; size >= 0; size--) {
            if (((Element) this.m.get(size)) == element) {
                this.m.remove(size);
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void c(String str) {
        int size = this.m.size() - 1;
        while (size >= 0) {
            this.m.remove(size);
            if (!((Element) this.m.get(size)).a().equals(str)) {
                size--;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String... strArr) {
        int size = this.m.size() - 1;
        while (size >= 0) {
            this.m.remove(size);
            if (!StringUtil.b(((Element) this.m.get(size)).a(), strArr)) {
                size--;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void d(String str) {
        int size = this.m.size() - 1;
        while (size >= 0 && !((Element) this.m.get(size)).a().equals(str)) {
            this.m.remove(size);
            size--;
        }
    }

    /* access modifiers changed from: package-private */
    public void k() {
        c("table");
    }

    /* access modifiers changed from: package-private */
    public void l() {
        c("tbody", "tfoot", "thead", "template");
    }

    /* access modifiers changed from: package-private */
    public void m() {
        c(BaseInfo.KEY_TIME_RECORD, "template");
    }

    private void c(String... strArr) {
        int size = this.m.size() - 1;
        while (size >= 0) {
            Element element = (Element) this.m.get(size);
            if (!StringUtil.a(element.a(), strArr) && !element.a().equals("html")) {
                this.m.remove(size);
                size--;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Element f(Element element) {
        if (i || d(element)) {
            for (int size = this.m.size() - 1; size >= 0; size--) {
                if (((Element) this.m.get(size)) == element) {
                    return (Element) this.m.get(size - 1);
                }
            }
            return null;
        }
        throw new AssertionError();
    }

    /* access modifiers changed from: package-private */
    public void a(Element element, Element element2) {
        int lastIndexOf = this.m.lastIndexOf(element);
        Validate.a(lastIndexOf != -1);
        this.m.add(lastIndexOf + 1, element2);
    }

    /* access modifiers changed from: package-private */
    public void b(Element element, Element element2) {
        a((ArrayList<Element>) this.m, element, element2);
    }

    private void a(ArrayList<Element> arrayList, Element element, Element element2) {
        int lastIndexOf = arrayList.lastIndexOf(element);
        Validate.a(lastIndexOf != -1);
        arrayList.set(lastIndexOf, element2);
    }

    /* access modifiers changed from: package-private */
    public void n() {
        int size = this.m.size() - 1;
        boolean z2 = false;
        while (size >= 0) {
            Element element = (Element) this.m.get(size);
            if (size == 0) {
                element = this.w;
                z2 = true;
            }
            String a2 = element.a();
            if ("select".equals(a2)) {
                a(HtmlTreeBuilderState.InSelect);
                return;
            } else if ("td".equals(a2) || ("th".equals(a2) && !z2)) {
                a(HtmlTreeBuilderState.InCell);
                return;
            } else if (BaseInfo.KEY_TIME_RECORD.equals(a2)) {
                a(HtmlTreeBuilderState.InRow);
                return;
            } else if ("tbody".equals(a2) || "thead".equals(a2) || "tfoot".equals(a2)) {
                a(HtmlTreeBuilderState.InTableBody);
                return;
            } else if (ShareConstants.FEED_CAPTION_PARAM.equals(a2)) {
                a(HtmlTreeBuilderState.InCaption);
                return;
            } else if ("colgroup".equals(a2)) {
                a(HtmlTreeBuilderState.InColumnGroup);
                return;
            } else if ("table".equals(a2)) {
                a(HtmlTreeBuilderState.InTable);
                return;
            } else if (TtmlNode.TAG_HEAD.equals(a2)) {
                a(HtmlTreeBuilderState.InBody);
                return;
            } else if ("body".equals(a2)) {
                a(HtmlTreeBuilderState.InBody);
                return;
            } else if ("frameset".equals(a2)) {
                a(HtmlTreeBuilderState.InFrameset);
                return;
            } else if ("html".equals(a2)) {
                a(HtmlTreeBuilderState.BeforeHead);
                return;
            } else if (z2) {
                a(HtmlTreeBuilderState.InBody);
                return;
            } else {
                size--;
            }
        }
    }

    private boolean a(String str, String[] strArr, String[] strArr2) {
        this.D[0] = str;
        return a(this.D, strArr, strArr2);
    }

    private boolean a(String[] strArr, String[] strArr2, String[] strArr3) {
        int size = this.m.size() - 1;
        int i2 = size > 100 ? size - 100 : 0;
        while (size >= i2) {
            String a2 = ((Element) this.m.get(size)).a();
            if (StringUtil.b(a2, strArr)) {
                return true;
            }
            if (StringUtil.b(a2, strArr2)) {
                return false;
            }
            if (strArr3 != null && StringUtil.b(a2, strArr3)) {
                return false;
            }
            size--;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean b(String[] strArr) {
        return a(strArr, f3675a, (String[]) null);
    }

    /* access modifiers changed from: package-private */
    public boolean e(String str) {
        return a(str, (String[]) null);
    }

    /* access modifiers changed from: package-private */
    public boolean a(String str, String[] strArr) {
        return a(str, f3675a, strArr);
    }

    /* access modifiers changed from: package-private */
    public boolean f(String str) {
        return a(str, b);
    }

    /* access modifiers changed from: package-private */
    public boolean g(String str) {
        return a(str, c);
    }

    /* access modifiers changed from: package-private */
    public boolean h(String str) {
        return a(str, d, (String[]) null);
    }

    /* access modifiers changed from: package-private */
    public boolean i(String str) {
        for (int size = this.m.size() - 1; size >= 0; size--) {
            String a2 = ((Element) this.m.get(size)).a();
            if (a2.equals(str)) {
                return true;
            }
            if (!StringUtil.b(a2, e)) {
                return false;
            }
        }
        Validate.b("Should not be reachable");
        return false;
    }

    /* access modifiers changed from: package-private */
    public void g(Element element) {
        this.u = element;
    }

    /* access modifiers changed from: package-private */
    public Element o() {
        return this.u;
    }

    /* access modifiers changed from: package-private */
    public boolean p() {
        return this.B;
    }

    /* access modifiers changed from: package-private */
    public void b(boolean z2) {
        this.B = z2;
    }

    /* access modifiers changed from: package-private */
    public FormElement q() {
        return this.v;
    }

    /* access modifiers changed from: package-private */
    public void a(FormElement formElement) {
        this.v = formElement;
    }

    /* access modifiers changed from: package-private */
    public void r() {
        this.y = new ArrayList();
    }

    /* access modifiers changed from: package-private */
    public List<String> s() {
        return this.y;
    }

    /* access modifiers changed from: package-private */
    public void a(List<String> list) {
        this.y = list;
    }

    /* access modifiers changed from: package-private */
    public void j(String str) {
        while (str != null && !A().a().equals(str) && StringUtil.b(A().a(), f)) {
            i();
        }
    }

    /* access modifiers changed from: package-private */
    public void t() {
        j((String) null);
    }

    /* access modifiers changed from: package-private */
    public boolean h(Element element) {
        return StringUtil.b(element.a(), g);
    }

    /* access modifiers changed from: package-private */
    public Element u() {
        if (this.x.size() > 0) {
            return this.x.get(this.x.size() - 1);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public Element v() {
        int size = this.x.size();
        if (size > 0) {
            return this.x.remove(size - 1);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void i(Element element) {
        int size = this.x.size() - 1;
        int i2 = 0;
        while (true) {
            if (size >= 0) {
                Element element2 = this.x.get(size);
                if (element2 == null) {
                    break;
                }
                if (d(element, element2)) {
                    i2++;
                }
                if (i2 == 3) {
                    this.x.remove(size);
                    break;
                }
                size--;
            } else {
                break;
            }
        }
        this.x.add(element);
    }

    private boolean d(Element element, Element element2) {
        return element.a().equals(element2.a()) && element.s().equals(element2.s());
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0030  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0058 A[RETURN] */
    void w() {
        /*
            r7 = this;
            org.jsoup.nodes.Element r0 = r7.u()
            if (r0 == 0) goto L_0x0059
            boolean r1 = r7.d((org.jsoup.nodes.Element) r0)
            if (r1 == 0) goto L_0x000d
            goto L_0x0059
        L_0x000d:
            java.util.ArrayList<org.jsoup.nodes.Element> r1 = r7.x
            int r1 = r1.size()
            r2 = 1
            int r1 = r1 - r2
            r3 = r0
            r0 = r1
        L_0x0017:
            r4 = 0
            if (r0 != 0) goto L_0x001b
            goto L_0x002e
        L_0x001b:
            java.util.ArrayList<org.jsoup.nodes.Element> r3 = r7.x
            int r0 = r0 + -1
            java.lang.Object r3 = r3.get(r0)
            org.jsoup.nodes.Element r3 = (org.jsoup.nodes.Element) r3
            if (r3 == 0) goto L_0x002d
            boolean r5 = r7.d((org.jsoup.nodes.Element) r3)
            if (r5 == 0) goto L_0x0017
        L_0x002d:
            r2 = 0
        L_0x002e:
            if (r2 != 0) goto L_0x003b
            java.util.ArrayList<org.jsoup.nodes.Element> r2 = r7.x
            int r0 = r0 + 1
            java.lang.Object r2 = r2.get(r0)
            org.jsoup.nodes.Element r2 = (org.jsoup.nodes.Element) r2
            r3 = r2
        L_0x003b:
            org.jsoup.helper.Validate.a((java.lang.Object) r3)
            java.lang.String r2 = r3.a()
            org.jsoup.nodes.Element r2 = r7.a((java.lang.String) r2)
            org.jsoup.nodes.Attributes r5 = r2.s()
            org.jsoup.nodes.Attributes r6 = r3.s()
            r5.a((org.jsoup.nodes.Attributes) r6)
            java.util.ArrayList<org.jsoup.nodes.Element> r5 = r7.x
            r5.set(r0, r2)
            if (r0 != r1) goto L_0x002d
            return
        L_0x0059:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.HtmlTreeBuilder.w():void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP:0: B:0:0x0000->B:3:0x000c, LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void x() {
        /*
            r1 = this;
        L_0x0000:
            java.util.ArrayList<org.jsoup.nodes.Element> r0 = r1.x
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x000e
            org.jsoup.nodes.Element r0 = r1.v()
            if (r0 != 0) goto L_0x0000
        L_0x000e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.HtmlTreeBuilder.x():void");
    }

    /* access modifiers changed from: package-private */
    public void j(Element element) {
        for (int size = this.x.size() - 1; size >= 0; size--) {
            if (this.x.get(size) == element) {
                this.x.remove(size);
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean k(Element element) {
        return a(this.x, element);
    }

    /* access modifiers changed from: package-private */
    public Element k(String str) {
        for (int size = this.x.size() - 1; size >= 0; size--) {
            Element element = this.x.get(size);
            if (element == null) {
                return null;
            }
            if (element.a().equals(str)) {
                return element;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void c(Element element, Element element2) {
        a(this.x, element, element2);
    }

    /* access modifiers changed from: package-private */
    public void y() {
        this.x.add((Object) null);
    }

    /* access modifiers changed from: package-private */
    public void a(Node node) {
        Element element;
        Element b2 = b("table");
        boolean z2 = false;
        if (b2 == null) {
            element = (Element) this.m.get(0);
        } else if (b2.Y() != null) {
            element = b2.Y();
            z2 = true;
        } else {
            element = f(b2);
        }
        if (z2) {
            Validate.a((Object) b2);
            b2.i(node);
            return;
        }
        element.a(node);
    }

    public String toString() {
        return "TreeBuilder{currentToken=" + this.o + ", state=" + this.r + ", currentElement=" + A() + Operators.BLOCK_END;
    }
}
