package org.jsoup.parser;

import com.alipay.mobile.security.bio.workspace.Env;
import com.alipay.sdk.cons.c;
import com.coloros.mcssdk.mode.CommandMessage;
import com.facebook.share.internal.ShareConstants;
import com.mi.global.shop.model.Tags;
import com.mi.mistatistic.sdk.data.EventData;
import com.taobao.weex.adapter.URIAdapter;
import com.taobao.weex.ui.component.WXBasicComponentType;
import com.tencent.tinker.loader.shareutil.SharePatchInfo;
import com.unionpay.tsmservice.mi.data.Constant;
import com.xiaomi.jr.hybrid.WebEvent;
import com.xiaomi.miot.support.monitor.core.BaseInfo;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.smarthome.core.server.internal.plugin.PluginManager;
import com.xiaomi.stat.d;
import java.util.ArrayList;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.Token;

enum HtmlTreeBuilderState {
    Initial {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                return true;
            }
            if (token.i()) {
                htmlTreeBuilder.a(token.j());
            } else if (token.c()) {
                Token.Doctype d = token.d();
                DocumentType documentType = new DocumentType(htmlTreeBuilder.q.a(d.n()), d.p(), d.q());
                documentType.e(d.o());
                htmlTreeBuilder.f().a((Node) documentType);
                if (d.r()) {
                    htmlTreeBuilder.f().a(Document.QuirksMode.quirks);
                }
                htmlTreeBuilder.a(BeforeHtml);
            } else {
                htmlTreeBuilder.a(BeforeHtml);
                return htmlTreeBuilder.a(token);
            }
            return true;
        }
    },
    BeforeHtml {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.c()) {
                htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                return false;
            }
            if (token.i()) {
                htmlTreeBuilder.a(token.j());
            } else if (HtmlTreeBuilderState.isWhitespace(token)) {
                return true;
            } else {
                if (!token.e() || !token.f().r().equals("html")) {
                    if (token.g()) {
                        if (StringUtil.a(token.h().r(), TtmlNode.TAG_HEAD, "body", "html", TtmlNode.TAG_BR)) {
                            return anythingElse(token, htmlTreeBuilder);
                        }
                    }
                    if (!token.g()) {
                        return anythingElse(token, htmlTreeBuilder);
                    }
                    htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                    return false;
                }
                htmlTreeBuilder.a(token.f());
                htmlTreeBuilder.a(BeforeHead);
            }
            return true;
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.a("html");
            htmlTreeBuilder.a(BeforeHead);
            return htmlTreeBuilder.a(token);
        }
    },
    BeforeHead {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                return true;
            }
            if (token.i()) {
                htmlTreeBuilder.a(token.j());
            } else if (token.c()) {
                htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                return false;
            } else if (token.e() && token.f().r().equals("html")) {
                return InBody.process(token, htmlTreeBuilder);
            } else {
                if (!token.e() || !token.f().r().equals(TtmlNode.TAG_HEAD)) {
                    if (token.g()) {
                        if (StringUtil.a(token.h().r(), TtmlNode.TAG_HEAD, "body", "html", TtmlNode.TAG_BR)) {
                            htmlTreeBuilder.l(TtmlNode.TAG_HEAD);
                            return htmlTreeBuilder.a(token);
                        }
                    }
                    if (token.g()) {
                        htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                        return false;
                    }
                    htmlTreeBuilder.l(TtmlNode.TAG_HEAD);
                    return htmlTreeBuilder.a(token);
                }
                htmlTreeBuilder.g(htmlTreeBuilder.a(token.f()));
                htmlTreeBuilder.a(InHead);
            }
            return true;
        }
    },
    InHead {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.a(token.l());
                return true;
            }
            switch (token.f3683a) {
                case Comment:
                    htmlTreeBuilder.a(token.j());
                    break;
                case Doctype:
                    htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                    return false;
                case StartTag:
                    Token.StartTag f = token.f();
                    String r = f.r();
                    if (r.equals("html")) {
                        return InBody.process(token, htmlTreeBuilder);
                    }
                    if (StringUtil.a(r, "base", "basefont", "bgsound", CommandMessage.COMMAND, "link")) {
                        Element b = htmlTreeBuilder.b(f);
                        if (r.equals("base") && b.c("href")) {
                            htmlTreeBuilder.a(b);
                            break;
                        }
                    } else if (r.equals("meta")) {
                        htmlTreeBuilder.b(f);
                        break;
                    } else if (r.equals("title")) {
                        HtmlTreeBuilderState.handleRcData(f, htmlTreeBuilder);
                        break;
                    } else {
                        if (StringUtil.a(r, "noframes", "style")) {
                            HtmlTreeBuilderState.handleRawtext(f, htmlTreeBuilder);
                            break;
                        } else if (r.equals("noscript")) {
                            htmlTreeBuilder.a(f);
                            htmlTreeBuilder.a(InHeadNoscript);
                            break;
                        } else if (r.equals(WebEvent.A)) {
                            htmlTreeBuilder.k.a(TokeniserState.ScriptData);
                            htmlTreeBuilder.c();
                            htmlTreeBuilder.a(Text);
                            htmlTreeBuilder.a(f);
                            break;
                        } else if (!r.equals(TtmlNode.TAG_HEAD)) {
                            return anythingElse(token, htmlTreeBuilder);
                        } else {
                            htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                            return false;
                        }
                    }
                case EndTag:
                    String r2 = token.h().r();
                    if (r2.equals(TtmlNode.TAG_HEAD)) {
                        htmlTreeBuilder.i();
                        htmlTreeBuilder.a(AfterHead);
                        break;
                    } else {
                        if (StringUtil.a(r2, "body", "html", TtmlNode.TAG_BR)) {
                            return anythingElse(token, htmlTreeBuilder);
                        }
                        htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                        return false;
                    }
                default:
                    return anythingElse(token, htmlTreeBuilder);
            }
            return true;
        }

        private boolean anythingElse(Token token, TreeBuilder treeBuilder) {
            treeBuilder.m(TtmlNode.TAG_HEAD);
            return treeBuilder.a(token);
        }
    },
    InHeadNoscript {
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0085, code lost:
            if (org.jsoup.helper.StringUtil.a(r8.f().r(), "basefont", "bgsound", "link", "meta", "noframes", "style") != false) goto L_0x00d0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x00bf, code lost:
            if (org.jsoup.helper.StringUtil.a(r8.f().r(), com.google.android.exoplayer2.text.ttml.TtmlNode.TAG_HEAD, "noscript") == false) goto L_0x00c1;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean process(org.jsoup.parser.Token r8, org.jsoup.parser.HtmlTreeBuilder r9) {
            /*
                r7 = this;
                boolean r0 = r8.c()
                r1 = 1
                if (r0 == 0) goto L_0x000b
                r9.b((org.jsoup.parser.HtmlTreeBuilderState) r7)
                goto L_0x0046
            L_0x000b:
                boolean r0 = r8.e()
                if (r0 == 0) goto L_0x0028
                org.jsoup.parser.Token$StartTag r0 = r8.f()
                java.lang.String r0 = r0.r()
                java.lang.String r2 = "html"
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x0028
                org.jsoup.parser.HtmlTreeBuilderState r0 = InBody
                boolean r8 = r9.a((org.jsoup.parser.Token) r8, (org.jsoup.parser.HtmlTreeBuilderState) r0)
                return r8
            L_0x0028:
                boolean r0 = r8.g()
                if (r0 == 0) goto L_0x0047
                org.jsoup.parser.Token$EndTag r0 = r8.h()
                java.lang.String r0 = r0.r()
                java.lang.String r2 = "noscript"
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x0047
                r9.i()
                org.jsoup.parser.HtmlTreeBuilderState r8 = InHead
                r9.a((org.jsoup.parser.HtmlTreeBuilderState) r8)
            L_0x0046:
                return r1
            L_0x0047:
                boolean r0 = org.jsoup.parser.HtmlTreeBuilderState.isWhitespace((org.jsoup.parser.Token) r8)
                if (r0 != 0) goto L_0x00d0
                boolean r0 = r8.i()
                if (r0 != 0) goto L_0x00d0
                boolean r0 = r8.e()
                r2 = 2
                r3 = 0
                if (r0 == 0) goto L_0x0088
                org.jsoup.parser.Token$StartTag r0 = r8.f()
                java.lang.String r0 = r0.r()
                r4 = 6
                java.lang.String[] r4 = new java.lang.String[r4]
                java.lang.String r5 = "basefont"
                r4[r3] = r5
                java.lang.String r5 = "bgsound"
                r4[r1] = r5
                java.lang.String r5 = "link"
                r4[r2] = r5
                r5 = 3
                java.lang.String r6 = "meta"
                r4[r5] = r6
                r5 = 4
                java.lang.String r6 = "noframes"
                r4[r5] = r6
                r5 = 5
                java.lang.String r6 = "style"
                r4[r5] = r6
                boolean r0 = org.jsoup.helper.StringUtil.a((java.lang.String) r0, (java.lang.String[]) r4)
                if (r0 == 0) goto L_0x0088
                goto L_0x00d0
            L_0x0088:
                boolean r0 = r8.g()
                if (r0 == 0) goto L_0x00a3
                org.jsoup.parser.Token$EndTag r0 = r8.h()
                java.lang.String r0 = r0.r()
                java.lang.String r4 = "br"
                boolean r0 = r0.equals(r4)
                if (r0 == 0) goto L_0x00a3
                boolean r8 = r7.anythingElse(r8, r9)
                return r8
            L_0x00a3:
                boolean r0 = r8.e()
                if (r0 == 0) goto L_0x00c1
                org.jsoup.parser.Token$StartTag r0 = r8.f()
                java.lang.String r0 = r0.r()
                java.lang.String[] r2 = new java.lang.String[r2]
                java.lang.String r4 = "head"
                r2[r3] = r4
                java.lang.String r4 = "noscript"
                r2[r1] = r4
                boolean r0 = org.jsoup.helper.StringUtil.a((java.lang.String) r0, (java.lang.String[]) r2)
                if (r0 != 0) goto L_0x00c7
            L_0x00c1:
                boolean r0 = r8.g()
                if (r0 == 0) goto L_0x00cb
            L_0x00c7:
                r9.b((org.jsoup.parser.HtmlTreeBuilderState) r7)
                return r3
            L_0x00cb:
                boolean r8 = r7.anythingElse(r8, r9)
                return r8
            L_0x00d0:
                org.jsoup.parser.HtmlTreeBuilderState r0 = InHead
                boolean r8 = r9.a((org.jsoup.parser.Token) r8, (org.jsoup.parser.HtmlTreeBuilderState) r0)
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.HtmlTreeBuilderState.AnonymousClass5.process(org.jsoup.parser.Token, org.jsoup.parser.HtmlTreeBuilder):boolean");
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.b((HtmlTreeBuilderState) this);
            htmlTreeBuilder.a(new Token.Character().a(token.toString()));
            return true;
        }
    },
    AfterHead {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.a(token.l());
            } else if (token.i()) {
                htmlTreeBuilder.a(token.j());
            } else if (token.c()) {
                htmlTreeBuilder.b((HtmlTreeBuilderState) this);
            } else if (token.e()) {
                Token.StartTag f = token.f();
                String r = f.r();
                if (r.equals("html")) {
                    return htmlTreeBuilder.a(token, InBody);
                }
                if (r.equals("body")) {
                    htmlTreeBuilder.a(f);
                    htmlTreeBuilder.a(false);
                    htmlTreeBuilder.a(InBody);
                } else if (r.equals("frameset")) {
                    htmlTreeBuilder.a(f);
                    htmlTreeBuilder.a(InFrameset);
                } else {
                    if (StringUtil.a(r, "base", "basefont", "bgsound", "link", "meta", "noframes", WebEvent.A, "style", "title")) {
                        htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                        Element o = htmlTreeBuilder.o();
                        htmlTreeBuilder.c(o);
                        htmlTreeBuilder.a(token, InHead);
                        htmlTreeBuilder.e(o);
                    } else if (r.equals(TtmlNode.TAG_HEAD)) {
                        htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                        return false;
                    } else {
                        anythingElse(token, htmlTreeBuilder);
                    }
                }
            } else if (token.g()) {
                if (StringUtil.a(token.h().r(), "body", "html")) {
                    anythingElse(token, htmlTreeBuilder);
                } else {
                    htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                    return false;
                }
            } else {
                anythingElse(token, htmlTreeBuilder);
            }
            return true;
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.l("body");
            htmlTreeBuilder.a(true);
            return htmlTreeBuilder.a(token);
        }
    },
    InBody {
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:65:0x0122  */
        /* JADX WARNING: Removed duplicated region for block: B:69:0x012f  */
        /* JADX WARNING: Removed duplicated region for block: B:75:0x0167 A[LOOP:3: B:74:0x0165->B:75:0x0167, LOOP_END] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean process(org.jsoup.parser.Token r17, org.jsoup.parser.HtmlTreeBuilder r18) {
            /*
                r16 = this;
                r0 = r16
                r1 = r17
                r2 = r18
                int[] r3 = org.jsoup.parser.HtmlTreeBuilderState.AnonymousClass24.f3676a
                org.jsoup.parser.Token$TokenType r4 = r1.f3683a
                int r4 = r4.ordinal()
                r3 = r3[r4]
                r4 = 1
                r5 = 0
                switch(r3) {
                    case 1: goto L_0x08db;
                    case 2: goto L_0x08d7;
                    case 3: goto L_0x032d;
                    case 4: goto L_0x004b;
                    case 5: goto L_0x0018;
                    default: goto L_0x0015;
                }
            L_0x0015:
                r1 = 1
                goto L_0x08e4
            L_0x0018:
                org.jsoup.parser.Token$Character r1 = r17.l()
                java.lang.String r3 = r1.n()
                java.lang.String r6 = org.jsoup.parser.HtmlTreeBuilderState.nullString
                boolean r3 = r3.equals(r6)
                if (r3 == 0) goto L_0x002e
                r2.b((org.jsoup.parser.HtmlTreeBuilderState) r0)
                return r5
            L_0x002e:
                boolean r3 = r18.e()
                if (r3 == 0) goto L_0x0041
                boolean r3 = org.jsoup.parser.HtmlTreeBuilderState.isWhitespace((org.jsoup.parser.Token) r1)
                if (r3 == 0) goto L_0x0041
                r18.w()
                r2.a((org.jsoup.parser.Token.Character) r1)
                goto L_0x0015
            L_0x0041:
                r18.w()
                r2.a((org.jsoup.parser.Token.Character) r1)
                r2.a((boolean) r5)
                goto L_0x0015
            L_0x004b:
                org.jsoup.parser.Token$EndTag r3 = r17.h()
                java.lang.String r6 = r3.r()
                java.lang.String[] r7 = org.jsoup.parser.HtmlTreeBuilderState.Constants.p
                boolean r7 = org.jsoup.helper.StringUtil.b(r6, r7)
                r8 = 0
                if (r7 == 0) goto L_0x0180
                r3 = 0
            L_0x005d:
                r7 = 8
                if (r3 >= r7) goto L_0x0015
                org.jsoup.nodes.Element r7 = r2.k((java.lang.String) r6)
                if (r7 != 0) goto L_0x006c
                boolean r1 = r16.anyOtherEndTag(r17, r18)
                return r1
            L_0x006c:
                boolean r9 = r2.d((org.jsoup.nodes.Element) r7)
                if (r9 != 0) goto L_0x0079
                r2.b((org.jsoup.parser.HtmlTreeBuilderState) r0)
                r2.j((org.jsoup.nodes.Element) r7)
                return r4
            L_0x0079:
                java.lang.String r9 = r7.a()
                boolean r9 = r2.e((java.lang.String) r9)
                if (r9 != 0) goto L_0x0087
                r2.b((org.jsoup.parser.HtmlTreeBuilderState) r0)
                return r5
            L_0x0087:
                org.jsoup.nodes.Element r9 = r18.A()
                if (r9 == r7) goto L_0x0090
                r2.b((org.jsoup.parser.HtmlTreeBuilderState) r0)
            L_0x0090:
                java.util.ArrayList r9 = r18.j()
                int r10 = r9.size()
                r13 = r8
                r11 = 0
                r12 = 0
            L_0x009b:
                if (r11 >= r10) goto L_0x00c0
                r14 = 64
                if (r11 >= r14) goto L_0x00c0
                java.lang.Object r14 = r9.get(r11)
                org.jsoup.nodes.Element r14 = (org.jsoup.nodes.Element) r14
                if (r14 != r7) goto L_0x00b4
                int r12 = r11 + -1
                java.lang.Object r12 = r9.get(r12)
                org.jsoup.nodes.Element r12 = (org.jsoup.nodes.Element) r12
                r13 = r12
                r12 = 1
                goto L_0x00bd
            L_0x00b4:
                if (r12 == 0) goto L_0x00bd
                boolean r15 = r2.h((org.jsoup.nodes.Element) r14)
                if (r15 == 0) goto L_0x00bd
                goto L_0x00c1
            L_0x00bd:
                int r11 = r11 + 1
                goto L_0x009b
            L_0x00c0:
                r14 = r8
            L_0x00c1:
                if (r14 != 0) goto L_0x00ce
                java.lang.String r1 = r7.a()
                r2.c((java.lang.String) r1)
                r2.j((org.jsoup.nodes.Element) r7)
                return r4
            L_0x00ce:
                r10 = r14
                r11 = r10
                r9 = 0
            L_0x00d1:
                r12 = 3
                if (r9 >= r12) goto L_0x0116
                boolean r12 = r2.d((org.jsoup.nodes.Element) r10)
                if (r12 == 0) goto L_0x00de
                org.jsoup.nodes.Element r10 = r2.f((org.jsoup.nodes.Element) r10)
            L_0x00de:
                boolean r12 = r2.k((org.jsoup.nodes.Element) r10)
                if (r12 != 0) goto L_0x00e8
                r2.e((org.jsoup.nodes.Element) r10)
                goto L_0x0112
            L_0x00e8:
                if (r10 != r7) goto L_0x00eb
                goto L_0x0116
            L_0x00eb:
                org.jsoup.nodes.Element r12 = new org.jsoup.nodes.Element
                java.lang.String r15 = r10.a()
                org.jsoup.parser.ParseSettings r4 = org.jsoup.parser.ParseSettings.b
                org.jsoup.parser.Tag r4 = org.jsoup.parser.Tag.a(r15, r4)
                java.lang.String r15 = r18.g()
                r12.<init>(r4, r15)
                r2.c(r10, r12)
                r2.b(r10, r12)
                org.jsoup.nodes.Element r4 = r11.Y()
                if (r4 == 0) goto L_0x010d
                r11.ah()
            L_0x010d:
                r12.a((org.jsoup.nodes.Node) r11)
                r10 = r12
                r11 = r10
            L_0x0112:
                int r9 = r9 + 1
                r4 = 1
                goto L_0x00d1
            L_0x0116:
                java.lang.String r4 = r13.a()
                java.lang.String[] r9 = org.jsoup.parser.HtmlTreeBuilderState.Constants.q
                boolean r4 = org.jsoup.helper.StringUtil.b(r4, r9)
                if (r4 == 0) goto L_0x012f
                org.jsoup.nodes.Element r4 = r11.Y()
                if (r4 == 0) goto L_0x012b
                r11.ah()
            L_0x012b:
                r2.a((org.jsoup.nodes.Node) r11)
                goto L_0x013b
            L_0x012f:
                org.jsoup.nodes.Element r4 = r11.Y()
                if (r4 == 0) goto L_0x0138
                r11.ah()
            L_0x0138:
                r13.a((org.jsoup.nodes.Node) r11)
            L_0x013b:
                org.jsoup.nodes.Element r4 = new org.jsoup.nodes.Element
                org.jsoup.parser.Tag r9 = r7.u()
                java.lang.String r10 = r18.g()
                r4.<init>(r9, r10)
                org.jsoup.nodes.Attributes r9 = r4.s()
                org.jsoup.nodes.Attributes r10 = r7.s()
                r9.a((org.jsoup.nodes.Attributes) r10)
                java.util.List r9 = r14.ab()
                int r10 = r14.c()
                org.jsoup.nodes.Node[] r10 = new org.jsoup.nodes.Node[r10]
                java.lang.Object[] r9 = r9.toArray(r10)
                org.jsoup.nodes.Node[] r9 = (org.jsoup.nodes.Node[]) r9
                int r10 = r9.length
                r11 = 0
            L_0x0165:
                if (r11 >= r10) goto L_0x016f
                r12 = r9[r11]
                r4.a((org.jsoup.nodes.Node) r12)
                int r11 = r11 + 1
                goto L_0x0165
            L_0x016f:
                r14.a((org.jsoup.nodes.Node) r4)
                r2.j((org.jsoup.nodes.Element) r7)
                r2.e((org.jsoup.nodes.Element) r7)
                r2.a((org.jsoup.nodes.Element) r14, (org.jsoup.nodes.Element) r4)
                int r3 = r3 + 1
                r4 = 1
                goto L_0x005d
            L_0x0180:
                java.lang.String[] r4 = org.jsoup.parser.HtmlTreeBuilderState.Constants.o
                boolean r4 = org.jsoup.helper.StringUtil.b(r6, r4)
                if (r4 == 0) goto L_0x01ab
                boolean r1 = r2.e((java.lang.String) r6)
                if (r1 != 0) goto L_0x0192
                r2.b((org.jsoup.parser.HtmlTreeBuilderState) r0)
                return r5
            L_0x0192:
                r18.t()
                org.jsoup.nodes.Element r1 = r18.A()
                java.lang.String r1 = r1.a()
                boolean r1 = r1.equals(r6)
                if (r1 != 0) goto L_0x01a6
                r2.b((org.jsoup.parser.HtmlTreeBuilderState) r0)
            L_0x01a6:
                r2.c((java.lang.String) r6)
                goto L_0x0015
            L_0x01ab:
                java.lang.String r4 = "span"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x01b8
                boolean r1 = r16.anyOtherEndTag(r17, r18)
                return r1
            L_0x01b8:
                java.lang.String r4 = "li"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x01e3
                boolean r1 = r2.f((java.lang.String) r6)
                if (r1 != 0) goto L_0x01ca
                r2.b((org.jsoup.parser.HtmlTreeBuilderState) r0)
                return r5
            L_0x01ca:
                r2.j((java.lang.String) r6)
                org.jsoup.nodes.Element r1 = r18.A()
                java.lang.String r1 = r1.a()
                boolean r1 = r1.equals(r6)
                if (r1 != 0) goto L_0x01de
                r2.b((org.jsoup.parser.HtmlTreeBuilderState) r0)
            L_0x01de:
                r2.c((java.lang.String) r6)
                goto L_0x0015
            L_0x01e3:
                java.lang.String r4 = "body"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x01fe
                java.lang.String r1 = "body"
                boolean r1 = r2.e((java.lang.String) r1)
                if (r1 != 0) goto L_0x01f7
                r2.b((org.jsoup.parser.HtmlTreeBuilderState) r0)
                return r5
            L_0x01f7:
                org.jsoup.parser.HtmlTreeBuilderState r1 = AfterBody
                r2.a((org.jsoup.parser.HtmlTreeBuilderState) r1)
                goto L_0x0015
            L_0x01fe:
                java.lang.String r4 = "html"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0213
                java.lang.String r1 = "body"
                boolean r1 = r2.m(r1)
                if (r1 == 0) goto L_0x0015
                boolean r1 = r2.a((org.jsoup.parser.Token) r3)
                return r1
            L_0x0213:
                java.lang.String r4 = "form"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x0248
                org.jsoup.nodes.FormElement r1 = r18.q()
                r2.a((org.jsoup.nodes.FormElement) r8)
                if (r1 == 0) goto L_0x0244
                boolean r3 = r2.e((java.lang.String) r6)
                if (r3 != 0) goto L_0x022b
                goto L_0x0244
            L_0x022b:
                r18.t()
                org.jsoup.nodes.Element r3 = r18.A()
                java.lang.String r3 = r3.a()
                boolean r3 = r3.equals(r6)
                if (r3 != 0) goto L_0x023f
                r2.b((org.jsoup.parser.HtmlTreeBuilderState) r0)
            L_0x023f:
                r2.e((org.jsoup.nodes.Element) r1)
                goto L_0x0015
            L_0x0244:
                r2.b((org.jsoup.parser.HtmlTreeBuilderState) r0)
                return r5
            L_0x0248:
                java.lang.String r4 = "p"
                boolean r4 = r6.equals(r4)
                if (r4 == 0) goto L_0x027a
                boolean r1 = r2.g((java.lang.String) r6)
                if (r1 != 0) goto L_0x0261
                r2.b((org.jsoup.parser.HtmlTreeBuilderState) r0)
                r2.l(r6)
                boolean r1 = r2.a((org.jsoup.parser.Token) r3)
                return r1
            L_0x0261:
                r2.j((java.lang.String) r6)
                org.jsoup.nodes.Element r1 = r18.A()
                java.lang.String r1 = r1.a()
                boolean r1 = r1.equals(r6)
                if (r1 != 0) goto L_0x0275
                r2.b((org.jsoup.parser.HtmlTreeBuilderState) r0)
            L_0x0275:
                r2.c((java.lang.String) r6)
                goto L_0x0015
            L_0x027a:
                java.lang.String[] r3 = org.jsoup.parser.HtmlTreeBuilderState.Constants.f
                boolean r3 = org.jsoup.helper.StringUtil.b(r6, r3)
                if (r3 == 0) goto L_0x02a5
                boolean r1 = r2.e((java.lang.String) r6)
                if (r1 != 0) goto L_0x028c
                r2.b((org.jsoup.parser.HtmlTreeBuilderState) r0)
                return r5
            L_0x028c:
                r2.j((java.lang.String) r6)
                org.jsoup.nodes.Element r1 = r18.A()
                java.lang.String r1 = r1.a()
                boolean r1 = r1.equals(r6)
                if (r1 != 0) goto L_0x02a0
                r2.b((org.jsoup.parser.HtmlTreeBuilderState) r0)
            L_0x02a0:
                r2.c((java.lang.String) r6)
                goto L_0x0015
            L_0x02a5:
                java.lang.String[] r3 = org.jsoup.parser.HtmlTreeBuilderState.Constants.c
                boolean r3 = org.jsoup.helper.StringUtil.b(r6, r3)
                if (r3 == 0) goto L_0x02d4
                java.lang.String[] r1 = org.jsoup.parser.HtmlTreeBuilderState.Constants.c
                boolean r1 = r2.b((java.lang.String[]) r1)
                if (r1 != 0) goto L_0x02b9
                r2.b((org.jsoup.parser.HtmlTreeBuilderState) r0)
                return r5
            L_0x02b9:
                r2.j((java.lang.String) r6)
                org.jsoup.nodes.Element r1 = r18.A()
                java.lang.String r1 = r1.a()
                boolean r1 = r1.equals(r6)
                if (r1 != 0) goto L_0x02cd
                r2.b((org.jsoup.parser.HtmlTreeBuilderState) r0)
            L_0x02cd:
                java.lang.String[] r1 = org.jsoup.parser.HtmlTreeBuilderState.Constants.c
                r2.a((java.lang.String[]) r1)
                goto L_0x0015
            L_0x02d4:
                java.lang.String r3 = "sarcasm"
                boolean r3 = r6.equals(r3)
                if (r3 == 0) goto L_0x02e1
                boolean r1 = r16.anyOtherEndTag(r17, r18)
                return r1
            L_0x02e1:
                java.lang.String[] r3 = org.jsoup.parser.HtmlTreeBuilderState.Constants.h
                boolean r3 = org.jsoup.helper.StringUtil.b(r6, r3)
                if (r3 == 0) goto L_0x0317
                java.lang.String r1 = "name"
                boolean r1 = r2.e((java.lang.String) r1)
                if (r1 != 0) goto L_0x0015
                boolean r1 = r2.e((java.lang.String) r6)
                if (r1 != 0) goto L_0x02fb
                r2.b((org.jsoup.parser.HtmlTreeBuilderState) r0)
                return r5
            L_0x02fb:
                r18.t()
                org.jsoup.nodes.Element r1 = r18.A()
                java.lang.String r1 = r1.a()
                boolean r1 = r1.equals(r6)
                if (r1 != 0) goto L_0x030f
                r2.b((org.jsoup.parser.HtmlTreeBuilderState) r0)
            L_0x030f:
                r2.c((java.lang.String) r6)
                r18.x()
                goto L_0x0015
            L_0x0317:
                java.lang.String r3 = "br"
                boolean r3 = r6.equals(r3)
                if (r3 == 0) goto L_0x0328
                r2.b((org.jsoup.parser.HtmlTreeBuilderState) r0)
                java.lang.String r1 = "br"
                r2.l(r1)
                return r5
            L_0x0328:
                boolean r1 = r16.anyOtherEndTag(r17, r18)
                return r1
            L_0x032d:
                org.jsoup.parser.Token$StartTag r3 = r17.f()
                java.lang.String r4 = r3.r()
                java.lang.String r6 = "a"
                boolean r6 = r4.equals(r6)
                if (r6 == 0) goto L_0x0367
                java.lang.String r1 = "a"
                org.jsoup.nodes.Element r1 = r2.k((java.lang.String) r1)
                if (r1 == 0) goto L_0x035b
                r2.b((org.jsoup.parser.HtmlTreeBuilderState) r0)
                java.lang.String r1 = "a"
                r2.m(r1)
                java.lang.String r1 = "a"
                org.jsoup.nodes.Element r1 = r2.b((java.lang.String) r1)
                if (r1 == 0) goto L_0x035b
                r2.j((org.jsoup.nodes.Element) r1)
                r2.e((org.jsoup.nodes.Element) r1)
            L_0x035b:
                r18.w()
                org.jsoup.nodes.Element r1 = r2.a((org.jsoup.parser.Token.StartTag) r3)
                r2.i((org.jsoup.nodes.Element) r1)
                goto L_0x0015
            L_0x0367:
                java.lang.String[] r6 = org.jsoup.parser.HtmlTreeBuilderState.Constants.i
                boolean r6 = org.jsoup.helper.StringUtil.b(r4, r6)
                if (r6 == 0) goto L_0x037a
                r18.w()
                r2.b((org.jsoup.parser.Token.StartTag) r3)
                r2.a((boolean) r5)
                goto L_0x0015
            L_0x037a:
                java.lang.String[] r6 = org.jsoup.parser.HtmlTreeBuilderState.Constants.b
                boolean r6 = org.jsoup.helper.StringUtil.b(r4, r6)
                if (r6 == 0) goto L_0x0394
                java.lang.String r1 = "p"
                boolean r1 = r2.g((java.lang.String) r1)
                if (r1 == 0) goto L_0x038f
                java.lang.String r1 = "p"
                r2.m(r1)
            L_0x038f:
                r2.a((org.jsoup.parser.Token.StartTag) r3)
                goto L_0x0015
            L_0x0394:
                java.lang.String r6 = "span"
                boolean r6 = r4.equals(r6)
                if (r6 == 0) goto L_0x03a4
                r18.w()
                r2.a((org.jsoup.parser.Token.StartTag) r3)
                goto L_0x0015
            L_0x03a4:
                java.lang.String r6 = "li"
                boolean r6 = r4.equals(r6)
                if (r6 == 0) goto L_0x03fb
                r2.a((boolean) r5)
                java.util.ArrayList r1 = r18.j()
                int r4 = r1.size()
                r5 = 1
                int r4 = r4 - r5
            L_0x03b9:
                if (r4 <= 0) goto L_0x03e9
                java.lang.Object r5 = r1.get(r4)
                org.jsoup.nodes.Element r5 = (org.jsoup.nodes.Element) r5
                java.lang.String r6 = r5.a()
                java.lang.String r7 = "li"
                boolean r6 = r6.equals(r7)
                if (r6 == 0) goto L_0x03d3
                java.lang.String r1 = "li"
                r2.m(r1)
                goto L_0x03e9
            L_0x03d3:
                boolean r6 = r2.h((org.jsoup.nodes.Element) r5)
                if (r6 == 0) goto L_0x03e6
                java.lang.String r5 = r5.a()
                java.lang.String[] r6 = org.jsoup.parser.HtmlTreeBuilderState.Constants.e
                boolean r5 = org.jsoup.helper.StringUtil.b(r5, r6)
                if (r5 != 0) goto L_0x03e6
                goto L_0x03e9
            L_0x03e6:
                int r4 = r4 + -1
                goto L_0x03b9
            L_0x03e9:
                java.lang.String r1 = "p"
                boolean r1 = r2.g((java.lang.String) r1)
                if (r1 == 0) goto L_0x03f6
                java.lang.String r1 = "p"
                r2.m(r1)
            L_0x03f6:
                r2.a((org.jsoup.parser.Token.StartTag) r3)
                goto L_0x0015
            L_0x03fb:
                java.lang.String r6 = "html"
                boolean r6 = r4.equals(r6)
                if (r6 == 0) goto L_0x0436
                r2.b((org.jsoup.parser.HtmlTreeBuilderState) r0)
                java.util.ArrayList r1 = r18.j()
                java.lang.Object r1 = r1.get(r5)
                org.jsoup.nodes.Element r1 = (org.jsoup.nodes.Element) r1
                org.jsoup.nodes.Attributes r2 = r3.t()
                java.util.Iterator r2 = r2.iterator()
            L_0x0418:
                boolean r3 = r2.hasNext()
                if (r3 == 0) goto L_0x0015
                java.lang.Object r3 = r2.next()
                org.jsoup.nodes.Attribute r3 = (org.jsoup.nodes.Attribute) r3
                java.lang.String r4 = r3.getKey()
                boolean r4 = r1.c(r4)
                if (r4 != 0) goto L_0x0418
                org.jsoup.nodes.Attributes r4 = r1.s()
                r4.a((org.jsoup.nodes.Attribute) r3)
                goto L_0x0418
            L_0x0436:
                java.lang.String[] r6 = org.jsoup.parser.HtmlTreeBuilderState.Constants.f3677a
                boolean r6 = org.jsoup.helper.StringUtil.b(r4, r6)
                if (r6 == 0) goto L_0x0445
                org.jsoup.parser.HtmlTreeBuilderState r3 = InHead
                boolean r1 = r2.a((org.jsoup.parser.Token) r1, (org.jsoup.parser.HtmlTreeBuilderState) r3)
                return r1
            L_0x0445:
                java.lang.String r1 = "body"
                boolean r1 = r4.equals(r1)
                r6 = 2
                if (r1 == 0) goto L_0x04a5
                r2.b((org.jsoup.parser.HtmlTreeBuilderState) r0)
                java.util.ArrayList r1 = r18.j()
                int r4 = r1.size()
                r7 = 1
                if (r4 == r7) goto L_0x04a4
                int r4 = r1.size()
                if (r4 <= r6) goto L_0x0475
                java.lang.Object r4 = r1.get(r7)
                org.jsoup.nodes.Element r4 = (org.jsoup.nodes.Element) r4
                java.lang.String r4 = r4.a()
                java.lang.String r6 = "body"
                boolean r4 = r4.equals(r6)
                if (r4 != 0) goto L_0x0475
                goto L_0x04a4
            L_0x0475:
                r2.a((boolean) r5)
                java.lang.Object r1 = r1.get(r7)
                org.jsoup.nodes.Element r1 = (org.jsoup.nodes.Element) r1
                org.jsoup.nodes.Attributes r2 = r3.t()
                java.util.Iterator r2 = r2.iterator()
            L_0x0486:
                boolean r3 = r2.hasNext()
                if (r3 == 0) goto L_0x0015
                java.lang.Object r3 = r2.next()
                org.jsoup.nodes.Attribute r3 = (org.jsoup.nodes.Attribute) r3
                java.lang.String r4 = r3.getKey()
                boolean r4 = r1.c(r4)
                if (r4 != 0) goto L_0x0486
                org.jsoup.nodes.Attributes r4 = r1.s()
                r4.a((org.jsoup.nodes.Attribute) r3)
                goto L_0x0486
            L_0x04a4:
                return r5
            L_0x04a5:
                java.lang.String r1 = "frameset"
                boolean r1 = r4.equals(r1)
                if (r1 == 0) goto L_0x0506
                r2.b((org.jsoup.parser.HtmlTreeBuilderState) r0)
                java.util.ArrayList r1 = r18.j()
                int r4 = r1.size()
                r7 = 1
                if (r4 == r7) goto L_0x0505
                int r4 = r1.size()
                if (r4 <= r6) goto L_0x04d4
                java.lang.Object r4 = r1.get(r7)
                org.jsoup.nodes.Element r4 = (org.jsoup.nodes.Element) r4
                java.lang.String r4 = r4.a()
                java.lang.String r6 = "body"
                boolean r4 = r4.equals(r6)
                if (r4 != 0) goto L_0x04d4
                goto L_0x0505
            L_0x04d4:
                boolean r4 = r18.e()
                if (r4 != 0) goto L_0x04db
                return r5
            L_0x04db:
                r4 = 1
                java.lang.Object r5 = r1.get(r4)
                org.jsoup.nodes.Element r5 = (org.jsoup.nodes.Element) r5
                org.jsoup.nodes.Element r6 = r5.Y()
                if (r6 == 0) goto L_0x04eb
                r5.ah()
            L_0x04eb:
                int r5 = r1.size()
                if (r5 <= r4) goto L_0x04fb
                int r5 = r1.size()
                int r5 = r5 - r4
                r1.remove(r5)
                r4 = 1
                goto L_0x04eb
            L_0x04fb:
                r2.a((org.jsoup.parser.Token.StartTag) r3)
                org.jsoup.parser.HtmlTreeBuilderState r1 = InFrameset
                r2.a((org.jsoup.parser.HtmlTreeBuilderState) r1)
                goto L_0x0015
            L_0x0505:
                return r5
            L_0x0506:
                java.lang.String[] r1 = org.jsoup.parser.HtmlTreeBuilderState.Constants.c
                boolean r1 = org.jsoup.helper.StringUtil.b(r4, r1)
                if (r1 == 0) goto L_0x0536
                java.lang.String r1 = "p"
                boolean r1 = r2.g((java.lang.String) r1)
                if (r1 == 0) goto L_0x051b
                java.lang.String r1 = "p"
                r2.m(r1)
            L_0x051b:
                org.jsoup.nodes.Element r1 = r18.A()
                java.lang.String r1 = r1.a()
                java.lang.String[] r4 = org.jsoup.parser.HtmlTreeBuilderState.Constants.c
                boolean r1 = org.jsoup.helper.StringUtil.b(r1, r4)
                if (r1 == 0) goto L_0x0531
                r2.b((org.jsoup.parser.HtmlTreeBuilderState) r0)
                r18.i()
            L_0x0531:
                r2.a((org.jsoup.parser.Token.StartTag) r3)
                goto L_0x0015
            L_0x0536:
                java.lang.String[] r1 = org.jsoup.parser.HtmlTreeBuilderState.Constants.d
                boolean r1 = org.jsoup.helper.StringUtil.b(r4, r1)
                if (r1 == 0) goto L_0x0553
                java.lang.String r1 = "p"
                boolean r1 = r2.g((java.lang.String) r1)
                if (r1 == 0) goto L_0x054b
                java.lang.String r1 = "p"
                r2.m(r1)
            L_0x054b:
                r2.a((org.jsoup.parser.Token.StartTag) r3)
                r2.a((boolean) r5)
                goto L_0x0015
            L_0x0553:
                java.lang.String r1 = "form"
                boolean r1 = r4.equals(r1)
                if (r1 == 0) goto L_0x0578
                org.jsoup.nodes.FormElement r1 = r18.q()
                if (r1 == 0) goto L_0x0565
                r2.b((org.jsoup.parser.HtmlTreeBuilderState) r0)
                return r5
            L_0x0565:
                java.lang.String r1 = "p"
                boolean r1 = r2.g((java.lang.String) r1)
                if (r1 == 0) goto L_0x0572
                java.lang.String r1 = "p"
                r2.m(r1)
            L_0x0572:
                r1 = 1
                r2.a((org.jsoup.parser.Token.StartTag) r3, (boolean) r1)
                goto L_0x08e4
            L_0x0578:
                r1 = 1
                java.lang.String[] r6 = org.jsoup.parser.HtmlTreeBuilderState.Constants.f
                boolean r6 = org.jsoup.helper.StringUtil.b(r4, r6)
                if (r6 == 0) goto L_0x05d1
                r2.a((boolean) r5)
                java.util.ArrayList r4 = r18.j()
                int r5 = r4.size()
                int r5 = r5 - r1
            L_0x058d:
                if (r5 <= 0) goto L_0x05bf
                java.lang.Object r1 = r4.get(r5)
                org.jsoup.nodes.Element r1 = (org.jsoup.nodes.Element) r1
                java.lang.String r6 = r1.a()
                java.lang.String[] r7 = org.jsoup.parser.HtmlTreeBuilderState.Constants.f
                boolean r6 = org.jsoup.helper.StringUtil.b(r6, r7)
                if (r6 == 0) goto L_0x05a9
                java.lang.String r1 = r1.a()
                r2.m(r1)
                goto L_0x05bf
            L_0x05a9:
                boolean r6 = r2.h((org.jsoup.nodes.Element) r1)
                if (r6 == 0) goto L_0x05bc
                java.lang.String r1 = r1.a()
                java.lang.String[] r6 = org.jsoup.parser.HtmlTreeBuilderState.Constants.e
                boolean r1 = org.jsoup.helper.StringUtil.b(r1, r6)
                if (r1 != 0) goto L_0x05bc
                goto L_0x05bf
            L_0x05bc:
                int r5 = r5 + -1
                goto L_0x058d
            L_0x05bf:
                java.lang.String r1 = "p"
                boolean r1 = r2.g((java.lang.String) r1)
                if (r1 == 0) goto L_0x05cc
                java.lang.String r1 = "p"
                r2.m(r1)
            L_0x05cc:
                r2.a((org.jsoup.parser.Token.StartTag) r3)
                goto L_0x0015
            L_0x05d1:
                java.lang.String r1 = "plaintext"
                boolean r1 = r4.equals(r1)
                if (r1 == 0) goto L_0x05f2
                java.lang.String r1 = "p"
                boolean r1 = r2.g((java.lang.String) r1)
                if (r1 == 0) goto L_0x05e6
                java.lang.String r1 = "p"
                r2.m(r1)
            L_0x05e6:
                r2.a((org.jsoup.parser.Token.StartTag) r3)
                org.jsoup.parser.Tokeniser r1 = r2.k
                org.jsoup.parser.TokeniserState r2 = org.jsoup.parser.TokeniserState.PLAINTEXT
                r1.a((org.jsoup.parser.TokeniserState) r2)
                goto L_0x0015
            L_0x05f2:
                java.lang.String r1 = "button"
                boolean r1 = r4.equals(r1)
                if (r1 == 0) goto L_0x061a
                java.lang.String r1 = "button"
                boolean r1 = r2.g((java.lang.String) r1)
                if (r1 == 0) goto L_0x060f
                r2.b((org.jsoup.parser.HtmlTreeBuilderState) r0)
                java.lang.String r1 = "button"
                r2.m(r1)
                r2.a((org.jsoup.parser.Token) r3)
                goto L_0x0015
            L_0x060f:
                r18.w()
                r2.a((org.jsoup.parser.Token.StartTag) r3)
                r2.a((boolean) r5)
                goto L_0x0015
            L_0x061a:
                java.lang.String[] r1 = org.jsoup.parser.HtmlTreeBuilderState.Constants.g
                boolean r1 = org.jsoup.helper.StringUtil.b(r4, r1)
                if (r1 == 0) goto L_0x062e
                r18.w()
                org.jsoup.nodes.Element r1 = r2.a((org.jsoup.parser.Token.StartTag) r3)
                r2.i((org.jsoup.nodes.Element) r1)
                goto L_0x0015
            L_0x062e:
                java.lang.String r1 = "nobr"
                boolean r1 = r4.equals(r1)
                if (r1 == 0) goto L_0x0655
                r18.w()
                java.lang.String r1 = "nobr"
                boolean r1 = r2.e((java.lang.String) r1)
                if (r1 == 0) goto L_0x064c
                r2.b((org.jsoup.parser.HtmlTreeBuilderState) r0)
                java.lang.String r1 = "nobr"
                r2.m(r1)
                r18.w()
            L_0x064c:
                org.jsoup.nodes.Element r1 = r2.a((org.jsoup.parser.Token.StartTag) r3)
                r2.i((org.jsoup.nodes.Element) r1)
                goto L_0x0015
            L_0x0655:
                java.lang.String[] r1 = org.jsoup.parser.HtmlTreeBuilderState.Constants.h
                boolean r1 = org.jsoup.helper.StringUtil.b(r4, r1)
                if (r1 == 0) goto L_0x066b
                r18.w()
                r2.a((org.jsoup.parser.Token.StartTag) r3)
                r18.y()
                r2.a((boolean) r5)
                goto L_0x0015
            L_0x066b:
                java.lang.String r1 = "table"
                boolean r1 = r4.equals(r1)
                if (r1 == 0) goto L_0x0699
                org.jsoup.nodes.Document r1 = r18.f()
                org.jsoup.nodes.Document$QuirksMode r1 = r1.n()
                org.jsoup.nodes.Document$QuirksMode r4 = org.jsoup.nodes.Document.QuirksMode.quirks
                if (r1 == r4) goto L_0x068c
                java.lang.String r1 = "p"
                boolean r1 = r2.g((java.lang.String) r1)
                if (r1 == 0) goto L_0x068c
                java.lang.String r1 = "p"
                r2.m(r1)
            L_0x068c:
                r2.a((org.jsoup.parser.Token.StartTag) r3)
                r2.a((boolean) r5)
                org.jsoup.parser.HtmlTreeBuilderState r1 = InTable
                r2.a((org.jsoup.parser.HtmlTreeBuilderState) r1)
                goto L_0x0015
            L_0x0699:
                java.lang.String r1 = "input"
                boolean r1 = r4.equals(r1)
                if (r1 == 0) goto L_0x06bb
                r18.w()
                org.jsoup.nodes.Element r1 = r2.b((org.jsoup.parser.Token.StartTag) r3)
                java.lang.String r3 = "type"
                java.lang.String r1 = r1.d(r3)
                java.lang.String r3 = "hidden"
                boolean r1 = r1.equalsIgnoreCase(r3)
                if (r1 != 0) goto L_0x0015
                r2.a((boolean) r5)
                goto L_0x0015
            L_0x06bb:
                java.lang.String[] r1 = org.jsoup.parser.HtmlTreeBuilderState.Constants.j
                boolean r1 = org.jsoup.helper.StringUtil.b(r4, r1)
                if (r1 == 0) goto L_0x06c8
                r2.b((org.jsoup.parser.Token.StartTag) r3)
                goto L_0x0015
            L_0x06c8:
                java.lang.String r1 = "hr"
                boolean r1 = r4.equals(r1)
                if (r1 == 0) goto L_0x06e5
                java.lang.String r1 = "p"
                boolean r1 = r2.g((java.lang.String) r1)
                if (r1 == 0) goto L_0x06dd
                java.lang.String r1 = "p"
                r2.m(r1)
            L_0x06dd:
                r2.b((org.jsoup.parser.Token.StartTag) r3)
                r2.a((boolean) r5)
                goto L_0x0015
            L_0x06e5:
                java.lang.String r1 = "image"
                boolean r1 = r4.equals(r1)
                if (r1 == 0) goto L_0x0705
                java.lang.String r1 = "svg"
                org.jsoup.nodes.Element r1 = r2.b((java.lang.String) r1)
                if (r1 != 0) goto L_0x0700
                java.lang.String r1 = "img"
                org.jsoup.parser.Token$Tag r1 = r3.a((java.lang.String) r1)
                boolean r1 = r2.a((org.jsoup.parser.Token) r1)
                return r1
            L_0x0700:
                r2.a((org.jsoup.parser.Token.StartTag) r3)
                goto L_0x0015
            L_0x0705:
                java.lang.String r1 = "isindex"
                boolean r1 = r4.equals(r1)
                if (r1 == 0) goto L_0x07a6
                r2.b((org.jsoup.parser.HtmlTreeBuilderState) r0)
                org.jsoup.nodes.FormElement r1 = r18.q()
                if (r1 == 0) goto L_0x0717
                return r5
            L_0x0717:
                java.lang.String r1 = "form"
                r2.l(r1)
                org.jsoup.nodes.Attributes r1 = r3.e
                java.lang.String r4 = "action"
                boolean r1 = r1.g(r4)
                if (r1 == 0) goto L_0x0737
                org.jsoup.nodes.FormElement r1 = r18.q()
                java.lang.String r4 = "action"
                org.jsoup.nodes.Attributes r5 = r3.e
                java.lang.String r6 = "action"
                java.lang.String r5 = r5.c(r6)
                r1.a((java.lang.String) r4, (java.lang.String) r5)
            L_0x0737:
                java.lang.String r1 = "hr"
                r2.l(r1)
                java.lang.String r1 = "label"
                r2.l(r1)
                org.jsoup.nodes.Attributes r1 = r3.e
                java.lang.String r4 = "prompt"
                boolean r1 = r1.g(r4)
                if (r1 == 0) goto L_0x0754
                org.jsoup.nodes.Attributes r1 = r3.e
                java.lang.String r4 = "prompt"
                java.lang.String r1 = r1.c(r4)
                goto L_0x0756
            L_0x0754:
                java.lang.String r1 = "This is a searchable index. Enter search keywords: "
            L_0x0756:
                org.jsoup.parser.Token$Character r4 = new org.jsoup.parser.Token$Character
                r4.<init>()
                org.jsoup.parser.Token$Character r1 = r4.a(r1)
                r2.a((org.jsoup.parser.Token) r1)
                org.jsoup.nodes.Attributes r1 = new org.jsoup.nodes.Attributes
                r1.<init>()
                org.jsoup.nodes.Attributes r3 = r3.e
                java.util.Iterator r3 = r3.iterator()
            L_0x076d:
                boolean r4 = r3.hasNext()
                if (r4 == 0) goto L_0x0789
                java.lang.Object r4 = r3.next()
                org.jsoup.nodes.Attribute r4 = (org.jsoup.nodes.Attribute) r4
                java.lang.String r5 = r4.getKey()
                java.lang.String[] r6 = org.jsoup.parser.HtmlTreeBuilderState.Constants.k
                boolean r5 = org.jsoup.helper.StringUtil.b(r5, r6)
                if (r5 != 0) goto L_0x076d
                r1.a((org.jsoup.nodes.Attribute) r4)
                goto L_0x076d
            L_0x0789:
                java.lang.String r3 = "name"
                java.lang.String r4 = "isindex"
                r1.a((java.lang.String) r3, (java.lang.String) r4)
                java.lang.String r3 = "input"
                r2.a((java.lang.String) r3, (org.jsoup.nodes.Attributes) r1)
                java.lang.String r1 = "label"
                r2.m(r1)
                java.lang.String r1 = "hr"
                r2.l(r1)
                java.lang.String r1 = "form"
                r2.m(r1)
                goto L_0x0015
            L_0x07a6:
                java.lang.String r1 = "textarea"
                boolean r1 = r4.equals(r1)
                if (r1 == 0) goto L_0x07c5
                r2.a((org.jsoup.parser.Token.StartTag) r3)
                org.jsoup.parser.Tokeniser r1 = r2.k
                org.jsoup.parser.TokeniserState r3 = org.jsoup.parser.TokeniserState.Rcdata
                r1.a((org.jsoup.parser.TokeniserState) r3)
                r18.c()
                r2.a((boolean) r5)
                org.jsoup.parser.HtmlTreeBuilderState r1 = Text
                r2.a((org.jsoup.parser.HtmlTreeBuilderState) r1)
                goto L_0x0015
            L_0x07c5:
                java.lang.String r1 = "xmp"
                boolean r1 = r4.equals(r1)
                if (r1 == 0) goto L_0x07e5
                java.lang.String r1 = "p"
                boolean r1 = r2.g((java.lang.String) r1)
                if (r1 == 0) goto L_0x07da
                java.lang.String r1 = "p"
                r2.m(r1)
            L_0x07da:
                r18.w()
                r2.a((boolean) r5)
                org.jsoup.parser.HtmlTreeBuilderState.handleRawtext(r3, r2)
                goto L_0x0015
            L_0x07e5:
                java.lang.String r1 = "iframe"
                boolean r1 = r4.equals(r1)
                if (r1 == 0) goto L_0x07f5
                r2.a((boolean) r5)
                org.jsoup.parser.HtmlTreeBuilderState.handleRawtext(r3, r2)
                goto L_0x0015
            L_0x07f5:
                java.lang.String r1 = "noembed"
                boolean r1 = r4.equals(r1)
                if (r1 == 0) goto L_0x0802
                org.jsoup.parser.HtmlTreeBuilderState.handleRawtext(r3, r2)
                goto L_0x0015
            L_0x0802:
                java.lang.String r1 = "select"
                boolean r1 = r4.equals(r1)
                if (r1 == 0) goto L_0x084e
                r18.w()
                r2.a((org.jsoup.parser.Token.StartTag) r3)
                r2.a((boolean) r5)
                org.jsoup.parser.HtmlTreeBuilderState r1 = r18.b()
                org.jsoup.parser.HtmlTreeBuilderState r3 = InTable
                boolean r3 = r1.equals(r3)
                if (r3 != 0) goto L_0x0847
                org.jsoup.parser.HtmlTreeBuilderState r3 = InCaption
                boolean r3 = r1.equals(r3)
                if (r3 != 0) goto L_0x0847
                org.jsoup.parser.HtmlTreeBuilderState r3 = InTableBody
                boolean r3 = r1.equals(r3)
                if (r3 != 0) goto L_0x0847
                org.jsoup.parser.HtmlTreeBuilderState r3 = InRow
                boolean r3 = r1.equals(r3)
                if (r3 != 0) goto L_0x0847
                org.jsoup.parser.HtmlTreeBuilderState r3 = InCell
                boolean r1 = r1.equals(r3)
                if (r1 == 0) goto L_0x0840
                goto L_0x0847
            L_0x0840:
                org.jsoup.parser.HtmlTreeBuilderState r1 = InSelect
                r2.a((org.jsoup.parser.HtmlTreeBuilderState) r1)
                goto L_0x0015
            L_0x0847:
                org.jsoup.parser.HtmlTreeBuilderState r1 = InSelectInTable
                r2.a((org.jsoup.parser.HtmlTreeBuilderState) r1)
                goto L_0x0015
            L_0x084e:
                java.lang.String[] r1 = org.jsoup.parser.HtmlTreeBuilderState.Constants.l
                boolean r1 = org.jsoup.helper.StringUtil.b(r4, r1)
                if (r1 == 0) goto L_0x0873
                org.jsoup.nodes.Element r1 = r18.A()
                java.lang.String r1 = r1.a()
                java.lang.String r4 = "option"
                boolean r1 = r1.equals(r4)
                if (r1 == 0) goto L_0x086b
                java.lang.String r1 = "option"
                r2.m(r1)
            L_0x086b:
                r18.w()
                r2.a((org.jsoup.parser.Token.StartTag) r3)
                goto L_0x0015
            L_0x0873:
                java.lang.String[] r1 = org.jsoup.parser.HtmlTreeBuilderState.Constants.m
                boolean r1 = org.jsoup.helper.StringUtil.b(r4, r1)
                if (r1 == 0) goto L_0x08a3
                java.lang.String r1 = "ruby"
                boolean r1 = r2.e((java.lang.String) r1)
                if (r1 == 0) goto L_0x0015
                r18.t()
                org.jsoup.nodes.Element r1 = r18.A()
                java.lang.String r1 = r1.a()
                java.lang.String r4 = "ruby"
                boolean r1 = r1.equals(r4)
                if (r1 != 0) goto L_0x089e
                r2.b((org.jsoup.parser.HtmlTreeBuilderState) r0)
                java.lang.String r1 = "ruby"
                r2.d((java.lang.String) r1)
            L_0x089e:
                r2.a((org.jsoup.parser.Token.StartTag) r3)
                goto L_0x0015
            L_0x08a3:
                java.lang.String r1 = "math"
                boolean r1 = r4.equals(r1)
                if (r1 == 0) goto L_0x08b3
                r18.w()
                r2.a((org.jsoup.parser.Token.StartTag) r3)
                goto L_0x0015
            L_0x08b3:
                java.lang.String r1 = "svg"
                boolean r1 = r4.equals(r1)
                if (r1 == 0) goto L_0x08c3
                r18.w()
                r2.a((org.jsoup.parser.Token.StartTag) r3)
                goto L_0x0015
            L_0x08c3:
                java.lang.String[] r1 = org.jsoup.parser.HtmlTreeBuilderState.Constants.n
                boolean r1 = org.jsoup.helper.StringUtil.b(r4, r1)
                if (r1 == 0) goto L_0x08cf
                r2.b((org.jsoup.parser.HtmlTreeBuilderState) r0)
                return r5
            L_0x08cf:
                r18.w()
                r2.a((org.jsoup.parser.Token.StartTag) r3)
                goto L_0x0015
            L_0x08d7:
                r2.b((org.jsoup.parser.HtmlTreeBuilderState) r0)
                return r5
            L_0x08db:
                org.jsoup.parser.Token$Comment r1 = r17.j()
                r2.a((org.jsoup.parser.Token.Comment) r1)
                goto L_0x0015
            L_0x08e4:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.HtmlTreeBuilderState.AnonymousClass7.process(org.jsoup.parser.Token, org.jsoup.parser.HtmlTreeBuilder):boolean");
        }

        /* access modifiers changed from: package-private */
        public boolean anyOtherEndTag(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            String a2 = htmlTreeBuilder.q.a(token.h().q());
            ArrayList<Element> j = htmlTreeBuilder.j();
            int size = j.size() - 1;
            while (true) {
                if (size < 0) {
                    break;
                }
                Element element = j.get(size);
                if (element.a().equals(a2)) {
                    htmlTreeBuilder.j(a2);
                    if (!a2.equals(htmlTreeBuilder.A().a())) {
                        htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                    }
                    htmlTreeBuilder.c(a2);
                } else if (htmlTreeBuilder.h(element)) {
                    htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                    return false;
                } else {
                    size--;
                }
            }
            return true;
        }
    },
    Text {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.k()) {
                htmlTreeBuilder.a(token.l());
                return true;
            } else if (token.m()) {
                htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                htmlTreeBuilder.i();
                htmlTreeBuilder.a(htmlTreeBuilder.d());
                return htmlTreeBuilder.a(token);
            } else if (!token.g()) {
                return true;
            } else {
                htmlTreeBuilder.i();
                htmlTreeBuilder.a(htmlTreeBuilder.d());
                return true;
            }
        }
    },
    InTable {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.k()) {
                htmlTreeBuilder.r();
                htmlTreeBuilder.c();
                htmlTreeBuilder.a(InTableText);
                return htmlTreeBuilder.a(token);
            } else if (token.i()) {
                htmlTreeBuilder.a(token.j());
                return true;
            } else if (token.c()) {
                htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                return false;
            } else if (token.e()) {
                Token.StartTag f = token.f();
                String r = f.r();
                if (r.equals(ShareConstants.FEED_CAPTION_PARAM)) {
                    htmlTreeBuilder.k();
                    htmlTreeBuilder.y();
                    htmlTreeBuilder.a(f);
                    htmlTreeBuilder.a(InCaption);
                } else if (r.equals("colgroup")) {
                    htmlTreeBuilder.k();
                    htmlTreeBuilder.a(f);
                    htmlTreeBuilder.a(InColumnGroup);
                } else if (r.equals(Constant.KEY_COL)) {
                    htmlTreeBuilder.l("colgroup");
                    return htmlTreeBuilder.a(token);
                } else {
                    if (StringUtil.a(r, "tbody", "tfoot", "thead")) {
                        htmlTreeBuilder.k();
                        htmlTreeBuilder.a(f);
                        htmlTreeBuilder.a(InTableBody);
                    } else {
                        if (StringUtil.a(r, "td", "th", BaseInfo.KEY_TIME_RECORD)) {
                            htmlTreeBuilder.l("tbody");
                            return htmlTreeBuilder.a(token);
                        } else if (r.equals("table")) {
                            htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                            if (htmlTreeBuilder.m("table")) {
                                return htmlTreeBuilder.a(token);
                            }
                        } else {
                            if (StringUtil.a(r, "style", WebEvent.A)) {
                                return htmlTreeBuilder.a(token, InHead);
                            }
                            if (r.equals("input")) {
                                if (!f.e.c("type").equalsIgnoreCase("hidden")) {
                                    return anythingElse(token, htmlTreeBuilder);
                                }
                                htmlTreeBuilder.b(f);
                            } else if (!r.equals(c.c)) {
                                return anythingElse(token, htmlTreeBuilder);
                            } else {
                                htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                                if (htmlTreeBuilder.q() != null) {
                                    return false;
                                }
                                htmlTreeBuilder.a(f, false);
                            }
                        }
                    }
                }
                return true;
            } else if (token.g()) {
                String r2 = token.h().r();
                if (!r2.equals("table")) {
                    if (!StringUtil.a(r2, "body", ShareConstants.FEED_CAPTION_PARAM, Constant.KEY_COL, "colgroup", "html", "tbody", "td", "tfoot", "th", "thead", BaseInfo.KEY_TIME_RECORD)) {
                        return anythingElse(token, htmlTreeBuilder);
                    }
                    htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                    return false;
                } else if (!htmlTreeBuilder.h(r2)) {
                    htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                    return false;
                } else {
                    htmlTreeBuilder.c("table");
                    htmlTreeBuilder.n();
                    return true;
                }
            } else if (!token.m()) {
                return anythingElse(token, htmlTreeBuilder);
            } else {
                if (htmlTreeBuilder.A().a().equals("html")) {
                    htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                }
                return true;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.b((HtmlTreeBuilderState) this);
            if (!StringUtil.a(htmlTreeBuilder.A().a(), "table", "tbody", "tfoot", "thead", BaseInfo.KEY_TIME_RECORD)) {
                return htmlTreeBuilder.a(token, InBody);
            }
            htmlTreeBuilder.b(true);
            boolean a2 = htmlTreeBuilder.a(token, InBody);
            htmlTreeBuilder.b(false);
            return a2;
        }
    },
    InTableText {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (AnonymousClass24.f3676a[token.f3683a.ordinal()] != 5) {
                if (htmlTreeBuilder.s().size() > 0) {
                    for (String next : htmlTreeBuilder.s()) {
                        if (!HtmlTreeBuilderState.isWhitespace(next)) {
                            htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                            if (StringUtil.a(htmlTreeBuilder.A().a(), "table", "tbody", "tfoot", "thead", BaseInfo.KEY_TIME_RECORD)) {
                                htmlTreeBuilder.b(true);
                                htmlTreeBuilder.a((Token) new Token.Character().a(next), InBody);
                                htmlTreeBuilder.b(false);
                            } else {
                                htmlTreeBuilder.a((Token) new Token.Character().a(next), InBody);
                            }
                        } else {
                            htmlTreeBuilder.a(new Token.Character().a(next));
                        }
                    }
                    htmlTreeBuilder.r();
                }
                htmlTreeBuilder.a(htmlTreeBuilder.d());
                return htmlTreeBuilder.a(token);
            }
            Token.Character l = token.l();
            if (l.n().equals(HtmlTreeBuilderState.nullString)) {
                htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                return false;
            }
            htmlTreeBuilder.s().add(l.n());
            return true;
        }
    },
    InCaption {
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0090, code lost:
            if (org.jsoup.helper.StringUtil.a(r14.f().r(), com.facebook.share.internal.ShareConstants.FEED_CAPTION_PARAM, com.unionpay.tsmservice.mi.data.Constant.KEY_COL, "colgroup", "tbody", "td", "tfoot", "th", "thead", com.xiaomi.miot.support.monitor.core.BaseInfo.KEY_TIME_RECORD) == false) goto L_0x0092;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean process(org.jsoup.parser.Token r14, org.jsoup.parser.HtmlTreeBuilder r15) {
            /*
                r13 = this;
                boolean r0 = r14.g()
                r1 = 1
                r2 = 0
                if (r0 == 0) goto L_0x004e
                org.jsoup.parser.Token$EndTag r0 = r14.h()
                java.lang.String r0 = r0.r()
                java.lang.String r3 = "caption"
                boolean r0 = r0.equals(r3)
                if (r0 == 0) goto L_0x004e
                org.jsoup.parser.Token$EndTag r14 = r14.h()
                java.lang.String r14 = r14.r()
                boolean r14 = r15.h((java.lang.String) r14)
                if (r14 != 0) goto L_0x002a
                r15.b((org.jsoup.parser.HtmlTreeBuilderState) r13)
                return r2
            L_0x002a:
                r15.t()
                org.jsoup.nodes.Element r14 = r15.A()
                java.lang.String r14 = r14.a()
                java.lang.String r0 = "caption"
                boolean r14 = r14.equals(r0)
                if (r14 != 0) goto L_0x0040
                r15.b((org.jsoup.parser.HtmlTreeBuilderState) r13)
            L_0x0040:
                java.lang.String r14 = "caption"
                r15.c((java.lang.String) r14)
                r15.x()
                org.jsoup.parser.HtmlTreeBuilderState r14 = InTable
                r15.a((org.jsoup.parser.HtmlTreeBuilderState) r14)
                goto L_0x00b8
            L_0x004e:
                boolean r0 = r14.e()
                r3 = 8
                r4 = 7
                r5 = 6
                r6 = 5
                r7 = 4
                r8 = 3
                r9 = 2
                r10 = 9
                if (r0 == 0) goto L_0x0092
                org.jsoup.parser.Token$StartTag r0 = r14.f()
                java.lang.String r0 = r0.r()
                java.lang.String[] r11 = new java.lang.String[r10]
                java.lang.String r12 = "caption"
                r11[r2] = r12
                java.lang.String r12 = "col"
                r11[r1] = r12
                java.lang.String r12 = "colgroup"
                r11[r9] = r12
                java.lang.String r12 = "tbody"
                r11[r8] = r12
                java.lang.String r12 = "td"
                r11[r7] = r12
                java.lang.String r12 = "tfoot"
                r11[r6] = r12
                java.lang.String r12 = "th"
                r11[r5] = r12
                java.lang.String r12 = "thead"
                r11[r4] = r12
                java.lang.String r12 = "tr"
                r11[r3] = r12
                boolean r0 = org.jsoup.helper.StringUtil.a((java.lang.String) r0, (java.lang.String[]) r11)
                if (r0 != 0) goto L_0x00a8
            L_0x0092:
                boolean r0 = r14.g()
                if (r0 == 0) goto L_0x00b9
                org.jsoup.parser.Token$EndTag r0 = r14.h()
                java.lang.String r0 = r0.r()
                java.lang.String r11 = "table"
                boolean r0 = r0.equals(r11)
                if (r0 == 0) goto L_0x00b9
            L_0x00a8:
                r15.b((org.jsoup.parser.HtmlTreeBuilderState) r13)
                java.lang.String r0 = "caption"
                boolean r0 = r15.m(r0)
                if (r0 == 0) goto L_0x00b8
                boolean r14 = r15.a((org.jsoup.parser.Token) r14)
                return r14
            L_0x00b8:
                return r1
            L_0x00b9:
                boolean r0 = r14.g()
                if (r0 == 0) goto L_0x00fd
                org.jsoup.parser.Token$EndTag r0 = r14.h()
                java.lang.String r0 = r0.r()
                r11 = 10
                java.lang.String[] r11 = new java.lang.String[r11]
                java.lang.String r12 = "body"
                r11[r2] = r12
                java.lang.String r12 = "col"
                r11[r1] = r12
                java.lang.String r1 = "colgroup"
                r11[r9] = r1
                java.lang.String r1 = "html"
                r11[r8] = r1
                java.lang.String r1 = "tbody"
                r11[r7] = r1
                java.lang.String r1 = "td"
                r11[r6] = r1
                java.lang.String r1 = "tfoot"
                r11[r5] = r1
                java.lang.String r1 = "th"
                r11[r4] = r1
                java.lang.String r1 = "thead"
                r11[r3] = r1
                java.lang.String r1 = "tr"
                r11[r10] = r1
                boolean r0 = org.jsoup.helper.StringUtil.a((java.lang.String) r0, (java.lang.String[]) r11)
                if (r0 == 0) goto L_0x00fd
                r15.b((org.jsoup.parser.HtmlTreeBuilderState) r13)
                return r2
            L_0x00fd:
                org.jsoup.parser.HtmlTreeBuilderState r0 = InBody
                boolean r14 = r15.a((org.jsoup.parser.Token) r14, (org.jsoup.parser.HtmlTreeBuilderState) r0)
                return r14
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.HtmlTreeBuilderState.AnonymousClass11.process(org.jsoup.parser.Token, org.jsoup.parser.HtmlTreeBuilder):boolean");
        }
    },
    InColumnGroup {
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0073, code lost:
            if (r3.equals("html") == false) goto L_0x0080;
         */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x0084  */
        /* JADX WARNING: Removed duplicated region for block: B:32:0x0089  */
        /* JADX WARNING: Removed duplicated region for block: B:33:0x008d  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean process(org.jsoup.parser.Token r8, org.jsoup.parser.HtmlTreeBuilder r9) {
            /*
                r7 = this;
                boolean r0 = org.jsoup.parser.HtmlTreeBuilderState.isWhitespace((org.jsoup.parser.Token) r8)
                r1 = 1
                if (r0 == 0) goto L_0x000f
                org.jsoup.parser.Token$Character r8 = r8.l()
                r9.a((org.jsoup.parser.Token.Character) r8)
                return r1
            L_0x000f:
                int[] r0 = org.jsoup.parser.HtmlTreeBuilderState.AnonymousClass24.f3676a
                org.jsoup.parser.Token$TokenType r2 = r8.f3683a
                int r2 = r2.ordinal()
                r0 = r0[r2]
                r2 = 6
                if (r0 == r2) goto L_0x00a0
                r2 = 0
                switch(r0) {
                    case 1: goto L_0x0098;
                    case 2: goto L_0x0094;
                    case 3: goto L_0x0055;
                    case 4: goto L_0x0025;
                    default: goto L_0x0020;
                }
            L_0x0020:
                boolean r8 = r7.anythingElse(r8, r9)
                return r8
            L_0x0025:
                org.jsoup.parser.Token$EndTag r0 = r8.h()
                java.lang.String r0 = r0.c
                java.lang.String r3 = "colgroup"
                boolean r0 = r0.equals(r3)
                if (r0 == 0) goto L_0x0050
                org.jsoup.nodes.Element r8 = r9.A()
                java.lang.String r8 = r8.a()
                java.lang.String r0 = "html"
                boolean r8 = r8.equals(r0)
                if (r8 == 0) goto L_0x0047
                r9.b((org.jsoup.parser.HtmlTreeBuilderState) r7)
                return r2
            L_0x0047:
                r9.i()
                org.jsoup.parser.HtmlTreeBuilderState r8 = InTable
                r9.a((org.jsoup.parser.HtmlTreeBuilderState) r8)
                goto L_0x009f
            L_0x0050:
                boolean r8 = r7.anythingElse(r8, r9)
                return r8
            L_0x0055:
                org.jsoup.parser.Token$StartTag r0 = r8.f()
                java.lang.String r3 = r0.r()
                r4 = -1
                int r5 = r3.hashCode()
                r6 = 98688(0x18180, float:1.38291E-40)
                if (r5 == r6) goto L_0x0076
                r6 = 3213227(0x3107ab, float:4.50269E-39)
                if (r5 == r6) goto L_0x006d
                goto L_0x0080
            L_0x006d:
                java.lang.String r5 = "html"
                boolean r3 = r3.equals(r5)
                if (r3 == 0) goto L_0x0080
                goto L_0x0081
            L_0x0076:
                java.lang.String r2 = "col"
                boolean r2 = r3.equals(r2)
                if (r2 == 0) goto L_0x0080
                r2 = 1
                goto L_0x0081
            L_0x0080:
                r2 = -1
            L_0x0081:
                switch(r2) {
                    case 0: goto L_0x008d;
                    case 1: goto L_0x0089;
                    default: goto L_0x0084;
                }
            L_0x0084:
                boolean r8 = r7.anythingElse(r8, r9)
                return r8
            L_0x0089:
                r9.b((org.jsoup.parser.Token.StartTag) r0)
                goto L_0x009f
            L_0x008d:
                org.jsoup.parser.HtmlTreeBuilderState r0 = InBody
                boolean r8 = r9.a((org.jsoup.parser.Token) r8, (org.jsoup.parser.HtmlTreeBuilderState) r0)
                return r8
            L_0x0094:
                r9.b((org.jsoup.parser.HtmlTreeBuilderState) r7)
                goto L_0x009f
            L_0x0098:
                org.jsoup.parser.Token$Comment r8 = r8.j()
                r9.a((org.jsoup.parser.Token.Comment) r8)
            L_0x009f:
                return r1
            L_0x00a0:
                org.jsoup.nodes.Element r0 = r9.A()
                java.lang.String r0 = r0.a()
                java.lang.String r2 = "html"
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x00b1
                return r1
            L_0x00b1:
                boolean r8 = r7.anythingElse(r8, r9)
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.HtmlTreeBuilderState.AnonymousClass12.process(org.jsoup.parser.Token, org.jsoup.parser.HtmlTreeBuilder):boolean");
        }

        private boolean anythingElse(Token token, TreeBuilder treeBuilder) {
            if (treeBuilder.m("colgroup")) {
                return treeBuilder.a(token);
            }
            return true;
        }
    },
    InTableBody {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            switch (AnonymousClass24.f3676a[token.f3683a.ordinal()]) {
                case 3:
                    Token.StartTag f = token.f();
                    String r = f.r();
                    if (r.equals("template")) {
                        htmlTreeBuilder.a(f);
                        break;
                    } else if (r.equals(BaseInfo.KEY_TIME_RECORD)) {
                        htmlTreeBuilder.l();
                        htmlTreeBuilder.a(f);
                        htmlTreeBuilder.a(InRow);
                        break;
                    } else {
                        if (StringUtil.a(r, "th", "td")) {
                            htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                            htmlTreeBuilder.l(BaseInfo.KEY_TIME_RECORD);
                            return htmlTreeBuilder.a((Token) f);
                        }
                        if (StringUtil.a(r, ShareConstants.FEED_CAPTION_PARAM, Constant.KEY_COL, "colgroup", "tbody", "tfoot", "thead")) {
                            return exitTableBody(token, htmlTreeBuilder);
                        }
                        return anythingElse(token, htmlTreeBuilder);
                    }
                case 4:
                    String r2 = token.h().r();
                    if (StringUtil.a(r2, "tbody", "tfoot", "thead")) {
                        if (htmlTreeBuilder.h(r2)) {
                            htmlTreeBuilder.l();
                            htmlTreeBuilder.i();
                            htmlTreeBuilder.a(InTable);
                            break;
                        } else {
                            htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                            return false;
                        }
                    } else if (r2.equals("table")) {
                        return exitTableBody(token, htmlTreeBuilder);
                    } else {
                        if (!StringUtil.a(r2, "body", ShareConstants.FEED_CAPTION_PARAM, Constant.KEY_COL, "colgroup", "html", "td", "th", BaseInfo.KEY_TIME_RECORD)) {
                            return anythingElse(token, htmlTreeBuilder);
                        }
                        htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                        return false;
                    }
                default:
                    return anythingElse(token, htmlTreeBuilder);
            }
            return true;
        }

        private boolean exitTableBody(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (htmlTreeBuilder.h("tbody") || htmlTreeBuilder.h("thead") || htmlTreeBuilder.e("tfoot")) {
                htmlTreeBuilder.l();
                htmlTreeBuilder.m(htmlTreeBuilder.A().a());
                return htmlTreeBuilder.a(token);
            }
            htmlTreeBuilder.b((HtmlTreeBuilderState) this);
            return false;
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            return htmlTreeBuilder.a(token, InTable);
        }
    },
    InRow {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.e()) {
                Token.StartTag f = token.f();
                String r = f.r();
                if (r.equals("template")) {
                    htmlTreeBuilder.a(f);
                } else {
                    if (StringUtil.a(r, "th", "td")) {
                        htmlTreeBuilder.m();
                        htmlTreeBuilder.a(f);
                        htmlTreeBuilder.a(InCell);
                        htmlTreeBuilder.y();
                    } else {
                        if (StringUtil.a(r, ShareConstants.FEED_CAPTION_PARAM, Constant.KEY_COL, "colgroup", "tbody", "tfoot", "thead", BaseInfo.KEY_TIME_RECORD)) {
                            return handleMissingTr(token, htmlTreeBuilder);
                        }
                        return anythingElse(token, htmlTreeBuilder);
                    }
                }
            } else if (!token.g()) {
                return anythingElse(token, htmlTreeBuilder);
            } else {
                String r2 = token.h().r();
                if (r2.equals(BaseInfo.KEY_TIME_RECORD)) {
                    if (!htmlTreeBuilder.h(r2)) {
                        htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                        return false;
                    }
                    htmlTreeBuilder.m();
                    htmlTreeBuilder.i();
                    htmlTreeBuilder.a(InTableBody);
                } else if (r2.equals("table")) {
                    return handleMissingTr(token, htmlTreeBuilder);
                } else {
                    if (!StringUtil.a(r2, "tbody", "tfoot", "thead")) {
                        if (!StringUtil.a(r2, "body", ShareConstants.FEED_CAPTION_PARAM, Constant.KEY_COL, "colgroup", "html", "td", "th")) {
                            return anythingElse(token, htmlTreeBuilder);
                        }
                        htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                        return false;
                    } else if (!htmlTreeBuilder.h(r2)) {
                        htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                        return false;
                    } else {
                        htmlTreeBuilder.m(BaseInfo.KEY_TIME_RECORD);
                        return htmlTreeBuilder.a(token);
                    }
                }
            }
            return true;
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            return htmlTreeBuilder.a(token, InTable);
        }

        private boolean handleMissingTr(Token token, TreeBuilder treeBuilder) {
            if (treeBuilder.m(BaseInfo.KEY_TIME_RECORD)) {
                return treeBuilder.a(token);
            }
            return false;
        }
    },
    InCell {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.g()) {
                String r = token.h().r();
                if (!StringUtil.a(r, "td", "th")) {
                    if (StringUtil.a(r, "body", ShareConstants.FEED_CAPTION_PARAM, Constant.KEY_COL, "colgroup", "html")) {
                        htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                        return false;
                    }
                    if (!StringUtil.a(r, "table", "tbody", "tfoot", "thead", BaseInfo.KEY_TIME_RECORD)) {
                        return anythingElse(token, htmlTreeBuilder);
                    }
                    if (!htmlTreeBuilder.h(r)) {
                        htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                        return false;
                    }
                    closeCell(htmlTreeBuilder);
                    return htmlTreeBuilder.a(token);
                } else if (!htmlTreeBuilder.h(r)) {
                    htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                    htmlTreeBuilder.a(InRow);
                    return false;
                } else {
                    htmlTreeBuilder.t();
                    if (!htmlTreeBuilder.A().a().equals(r)) {
                        htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                    }
                    htmlTreeBuilder.c(r);
                    htmlTreeBuilder.x();
                    htmlTreeBuilder.a(InRow);
                    return true;
                }
            } else {
                if (token.e()) {
                    if (StringUtil.a(token.f().r(), ShareConstants.FEED_CAPTION_PARAM, Constant.KEY_COL, "colgroup", "tbody", "td", "tfoot", "th", "thead", BaseInfo.KEY_TIME_RECORD)) {
                        if (htmlTreeBuilder.h("td") || htmlTreeBuilder.h("th")) {
                            closeCell(htmlTreeBuilder);
                            return htmlTreeBuilder.a(token);
                        }
                        htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                        return false;
                    }
                }
                return anythingElse(token, htmlTreeBuilder);
            }
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            return htmlTreeBuilder.a(token, InBody);
        }

        private void closeCell(HtmlTreeBuilder htmlTreeBuilder) {
            if (htmlTreeBuilder.h("td")) {
                htmlTreeBuilder.m("td");
            } else {
                htmlTreeBuilder.m("th");
            }
        }
    },
    InSelect {
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0072, code lost:
            if (r0.equals("select") != false) goto L_0x0080;
         */
        /* JADX WARNING: Removed duplicated region for block: B:28:0x0083  */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x0088  */
        /* JADX WARNING: Removed duplicated region for block: B:35:0x009a  */
        /* JADX WARNING: Removed duplicated region for block: B:39:0x00b4  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean process(org.jsoup.parser.Token r8, org.jsoup.parser.HtmlTreeBuilder r9) {
            /*
                r7 = this;
                int[] r0 = org.jsoup.parser.HtmlTreeBuilderState.AnonymousClass24.f3676a
                org.jsoup.parser.Token$TokenType r1 = r8.f3683a
                int r1 = r1.ordinal()
                r0 = r0[r1]
                r1 = 2
                r2 = 1
                r3 = 0
                switch(r0) {
                    case 1: goto L_0x01c6;
                    case 2: goto L_0x01c2;
                    case 3: goto L_0x0101;
                    case 4: goto L_0x0045;
                    case 5: goto L_0x002a;
                    case 6: goto L_0x0015;
                    default: goto L_0x0010;
                }
            L_0x0010:
                boolean r8 = r7.anythingElse(r8, r9)
                return r8
            L_0x0015:
                org.jsoup.nodes.Element r8 = r9.A()
                java.lang.String r8 = r8.a()
                java.lang.String r0 = "html"
                boolean r8 = r8.equals(r0)
                if (r8 != 0) goto L_0x01cd
                r9.b((org.jsoup.parser.HtmlTreeBuilderState) r7)
                goto L_0x01cd
            L_0x002a:
                org.jsoup.parser.Token$Character r8 = r8.l()
                java.lang.String r0 = r8.n()
                java.lang.String r1 = org.jsoup.parser.HtmlTreeBuilderState.nullString
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x0040
                r9.b((org.jsoup.parser.HtmlTreeBuilderState) r7)
                return r3
            L_0x0040:
                r9.a((org.jsoup.parser.Token.Character) r8)
                goto L_0x01cd
            L_0x0045:
                org.jsoup.parser.Token$EndTag r0 = r8.h()
                java.lang.String r0 = r0.r()
                r4 = -1
                int r5 = r0.hashCode()
                r6 = -1010136971(0xffffffffc3ca8875, float:-405.06607)
                if (r5 == r6) goto L_0x0075
                r6 = -906021636(0xffffffffc9ff34fc, float:-2090655.5)
                if (r5 == r6) goto L_0x006c
                r1 = -80773204(0xfffffffffb2f7fac, float:-9.1124144E35)
                if (r5 == r1) goto L_0x0062
                goto L_0x007f
            L_0x0062:
                java.lang.String r1 = "optgroup"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x007f
                r1 = 0
                goto L_0x0080
            L_0x006c:
                java.lang.String r5 = "select"
                boolean r5 = r0.equals(r5)
                if (r5 == 0) goto L_0x007f
                goto L_0x0080
            L_0x0075:
                java.lang.String r1 = "option"
                boolean r1 = r0.equals(r1)
                if (r1 == 0) goto L_0x007f
                r1 = 1
                goto L_0x0080
            L_0x007f:
                r1 = -1
            L_0x0080:
                switch(r1) {
                    case 0: goto L_0x00b4;
                    case 1: goto L_0x009a;
                    case 2: goto L_0x0088;
                    default: goto L_0x0083;
                }
            L_0x0083:
                boolean r8 = r7.anythingElse(r8, r9)
                return r8
            L_0x0088:
                boolean r8 = r9.i((java.lang.String) r0)
                if (r8 != 0) goto L_0x0092
                r9.b((org.jsoup.parser.HtmlTreeBuilderState) r7)
                return r3
            L_0x0092:
                r9.c((java.lang.String) r0)
                r9.n()
                goto L_0x01cd
            L_0x009a:
                org.jsoup.nodes.Element r8 = r9.A()
                java.lang.String r8 = r8.a()
                java.lang.String r0 = "option"
                boolean r8 = r8.equals(r0)
                if (r8 == 0) goto L_0x00af
                r9.i()
                goto L_0x01cd
            L_0x00af:
                r9.b((org.jsoup.parser.HtmlTreeBuilderState) r7)
                goto L_0x01cd
            L_0x00b4:
                org.jsoup.nodes.Element r8 = r9.A()
                java.lang.String r8 = r8.a()
                java.lang.String r0 = "option"
                boolean r8 = r8.equals(r0)
                if (r8 == 0) goto L_0x00e7
                org.jsoup.nodes.Element r8 = r9.A()
                org.jsoup.nodes.Element r8 = r9.f((org.jsoup.nodes.Element) r8)
                if (r8 == 0) goto L_0x00e7
                org.jsoup.nodes.Element r8 = r9.A()
                org.jsoup.nodes.Element r8 = r9.f((org.jsoup.nodes.Element) r8)
                java.lang.String r8 = r8.a()
                java.lang.String r0 = "optgroup"
                boolean r8 = r8.equals(r0)
                if (r8 == 0) goto L_0x00e7
                java.lang.String r8 = "option"
                r9.m(r8)
            L_0x00e7:
                org.jsoup.nodes.Element r8 = r9.A()
                java.lang.String r8 = r8.a()
                java.lang.String r0 = "optgroup"
                boolean r8 = r8.equals(r0)
                if (r8 == 0) goto L_0x00fc
                r9.i()
                goto L_0x01cd
            L_0x00fc:
                r9.b((org.jsoup.parser.HtmlTreeBuilderState) r7)
                goto L_0x01cd
            L_0x0101:
                org.jsoup.parser.Token$StartTag r0 = r8.f()
                java.lang.String r4 = r0.r()
                java.lang.String r5 = "html"
                boolean r5 = r4.equals(r5)
                if (r5 == 0) goto L_0x0118
                org.jsoup.parser.HtmlTreeBuilderState r8 = InBody
                boolean r8 = r9.a((org.jsoup.parser.Token) r0, (org.jsoup.parser.HtmlTreeBuilderState) r8)
                return r8
            L_0x0118:
                java.lang.String r5 = "option"
                boolean r5 = r4.equals(r5)
                if (r5 == 0) goto L_0x013a
                org.jsoup.nodes.Element r8 = r9.A()
                java.lang.String r8 = r8.a()
                java.lang.String r1 = "option"
                boolean r8 = r8.equals(r1)
                if (r8 == 0) goto L_0x0135
                java.lang.String r8 = "option"
                r9.m(r8)
            L_0x0135:
                r9.a((org.jsoup.parser.Token.StartTag) r0)
                goto L_0x01cd
            L_0x013a:
                java.lang.String r5 = "optgroup"
                boolean r5 = r4.equals(r5)
                if (r5 == 0) goto L_0x0171
                org.jsoup.nodes.Element r8 = r9.A()
                java.lang.String r8 = r8.a()
                java.lang.String r1 = "option"
                boolean r8 = r8.equals(r1)
                if (r8 == 0) goto L_0x0158
                java.lang.String r8 = "option"
                r9.m(r8)
                goto L_0x016d
            L_0x0158:
                org.jsoup.nodes.Element r8 = r9.A()
                java.lang.String r8 = r8.a()
                java.lang.String r1 = "optgroup"
                boolean r8 = r8.equals(r1)
                if (r8 == 0) goto L_0x016d
                java.lang.String r8 = "optgroup"
                r9.m(r8)
            L_0x016d:
                r9.a((org.jsoup.parser.Token.StartTag) r0)
                goto L_0x01cd
            L_0x0171:
                java.lang.String r5 = "select"
                boolean r5 = r4.equals(r5)
                if (r5 == 0) goto L_0x0183
                r9.b((org.jsoup.parser.HtmlTreeBuilderState) r7)
                java.lang.String r8 = "select"
                boolean r8 = r9.m(r8)
                return r8
            L_0x0183:
                r5 = 3
                java.lang.String[] r5 = new java.lang.String[r5]
                java.lang.String r6 = "input"
                r5[r3] = r6
                java.lang.String r6 = "keygen"
                r5[r2] = r6
                java.lang.String r2 = "textarea"
                r5[r1] = r2
                boolean r1 = org.jsoup.helper.StringUtil.a((java.lang.String) r4, (java.lang.String[]) r5)
                if (r1 == 0) goto L_0x01ae
                r9.b((org.jsoup.parser.HtmlTreeBuilderState) r7)
                java.lang.String r8 = "select"
                boolean r8 = r9.i((java.lang.String) r8)
                if (r8 != 0) goto L_0x01a4
                return r3
            L_0x01a4:
                java.lang.String r8 = "select"
                r9.m(r8)
                boolean r8 = r9.a((org.jsoup.parser.Token) r0)
                return r8
            L_0x01ae:
                java.lang.String r0 = "script"
                boolean r0 = r4.equals(r0)
                if (r0 == 0) goto L_0x01bd
                org.jsoup.parser.HtmlTreeBuilderState r0 = InHead
                boolean r8 = r9.a((org.jsoup.parser.Token) r8, (org.jsoup.parser.HtmlTreeBuilderState) r0)
                return r8
            L_0x01bd:
                boolean r8 = r7.anythingElse(r8, r9)
                return r8
            L_0x01c2:
                r9.b((org.jsoup.parser.HtmlTreeBuilderState) r7)
                return r3
            L_0x01c6:
                org.jsoup.parser.Token$Comment r8 = r8.j()
                r9.a((org.jsoup.parser.Token.Comment) r8)
            L_0x01cd:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.HtmlTreeBuilderState.AnonymousClass16.process(org.jsoup.parser.Token, org.jsoup.parser.HtmlTreeBuilder):boolean");
        }

        private boolean anythingElse(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            htmlTreeBuilder.b((HtmlTreeBuilderState) this);
            return false;
        }
    },
    InSelectInTable {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.e()) {
                if (StringUtil.a(token.f().r(), ShareConstants.FEED_CAPTION_PARAM, "table", "tbody", "tfoot", "thead", BaseInfo.KEY_TIME_RECORD, "td", "th")) {
                    htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                    htmlTreeBuilder.m("select");
                    return htmlTreeBuilder.a(token);
                }
            }
            if (token.g()) {
                if (StringUtil.a(token.h().r(), ShareConstants.FEED_CAPTION_PARAM, "table", "tbody", "tfoot", "thead", BaseInfo.KEY_TIME_RECORD, "td", "th")) {
                    htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                    if (!htmlTreeBuilder.h(token.h().r())) {
                        return false;
                    }
                    htmlTreeBuilder.m("select");
                    return htmlTreeBuilder.a(token);
                }
            }
            return htmlTreeBuilder.a(token, InSelect);
        }
    },
    AfterBody {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                return htmlTreeBuilder.a(token, InBody);
            }
            if (token.i()) {
                htmlTreeBuilder.a(token.j());
                return true;
            } else if (token.c()) {
                htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                return false;
            } else if (token.e() && token.f().r().equals("html")) {
                return htmlTreeBuilder.a(token, InBody);
            } else {
                if (!token.g() || !token.h().r().equals("html")) {
                    if (token.m()) {
                        return true;
                    }
                    htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                    htmlTreeBuilder.a(InBody);
                    return htmlTreeBuilder.a(token);
                } else if (htmlTreeBuilder.h()) {
                    htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                    return false;
                } else {
                    htmlTreeBuilder.a(AfterAfterBody);
                    return true;
                }
            }
        }
    },
    InFrameset {
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:34:0x007e  */
        /* JADX WARNING: Removed duplicated region for block: B:36:0x0082  */
        /* JADX WARNING: Removed duplicated region for block: B:38:0x0089  */
        /* JADX WARNING: Removed duplicated region for block: B:39:0x008d  */
        /* JADX WARNING: Removed duplicated region for block: B:40:0x0091  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean process(org.jsoup.parser.Token r7, org.jsoup.parser.HtmlTreeBuilder r8) {
            /*
                r6 = this;
                boolean r0 = org.jsoup.parser.HtmlTreeBuilderState.isWhitespace((org.jsoup.parser.Token) r7)
                r1 = 1
                if (r0 == 0) goto L_0x0010
                org.jsoup.parser.Token$Character r7 = r7.l()
                r8.a((org.jsoup.parser.Token.Character) r7)
                goto L_0x00fb
            L_0x0010:
                boolean r0 = r7.i()
                if (r0 == 0) goto L_0x001f
                org.jsoup.parser.Token$Comment r7 = r7.j()
                r8.a((org.jsoup.parser.Token.Comment) r7)
                goto L_0x00fb
            L_0x001f:
                boolean r0 = r7.c()
                r2 = 0
                if (r0 == 0) goto L_0x002a
                r8.b((org.jsoup.parser.HtmlTreeBuilderState) r6)
                return r2
            L_0x002a:
                boolean r0 = r7.e()
                if (r0 == 0) goto L_0x0098
                org.jsoup.parser.Token$StartTag r7 = r7.f()
                java.lang.String r0 = r7.r()
                r3 = -1
                int r4 = r0.hashCode()
                r5 = -1644953643(0xffffffff9df3ffd5, float:-6.458609E-21)
                if (r4 == r5) goto L_0x0070
                r5 = 3213227(0x3107ab, float:4.50269E-39)
                if (r4 == r5) goto L_0x0066
                r5 = 97692013(0x5d2a96d, float:1.9810542E-35)
                if (r4 == r5) goto L_0x005c
                r5 = 1192721831(0x47177da7, float:38781.652)
                if (r4 == r5) goto L_0x0052
                goto L_0x007a
            L_0x0052:
                java.lang.String r4 = "noframes"
                boolean r0 = r0.equals(r4)
                if (r0 == 0) goto L_0x007a
                r0 = 3
                goto L_0x007b
            L_0x005c:
                java.lang.String r4 = "frame"
                boolean r0 = r0.equals(r4)
                if (r0 == 0) goto L_0x007a
                r0 = 2
                goto L_0x007b
            L_0x0066:
                java.lang.String r4 = "html"
                boolean r0 = r0.equals(r4)
                if (r0 == 0) goto L_0x007a
                r0 = 0
                goto L_0x007b
            L_0x0070:
                java.lang.String r4 = "frameset"
                boolean r0 = r0.equals(r4)
                if (r0 == 0) goto L_0x007a
                r0 = 1
                goto L_0x007b
            L_0x007a:
                r0 = -1
            L_0x007b:
                switch(r0) {
                    case 0: goto L_0x0091;
                    case 1: goto L_0x008d;
                    case 2: goto L_0x0089;
                    case 3: goto L_0x0082;
                    default: goto L_0x007e;
                }
            L_0x007e:
                r8.b((org.jsoup.parser.HtmlTreeBuilderState) r6)
                return r2
            L_0x0082:
                org.jsoup.parser.HtmlTreeBuilderState r0 = InHead
                boolean r7 = r8.a((org.jsoup.parser.Token) r7, (org.jsoup.parser.HtmlTreeBuilderState) r0)
                return r7
            L_0x0089:
                r8.b((org.jsoup.parser.Token.StartTag) r7)
                goto L_0x00fb
            L_0x008d:
                r8.a((org.jsoup.parser.Token.StartTag) r7)
                goto L_0x00fb
            L_0x0091:
                org.jsoup.parser.HtmlTreeBuilderState r0 = InBody
                boolean r7 = r8.a((org.jsoup.parser.Token) r7, (org.jsoup.parser.HtmlTreeBuilderState) r0)
                return r7
            L_0x0098:
                boolean r0 = r7.g()
                if (r0 == 0) goto L_0x00e1
                org.jsoup.parser.Token$EndTag r0 = r7.h()
                java.lang.String r0 = r0.r()
                java.lang.String r3 = "frameset"
                boolean r0 = r0.equals(r3)
                if (r0 == 0) goto L_0x00e1
                org.jsoup.nodes.Element r7 = r8.A()
                java.lang.String r7 = r7.a()
                java.lang.String r0 = "html"
                boolean r7 = r7.equals(r0)
                if (r7 == 0) goto L_0x00c2
                r8.b((org.jsoup.parser.HtmlTreeBuilderState) r6)
                return r2
            L_0x00c2:
                r8.i()
                boolean r7 = r8.h()
                if (r7 != 0) goto L_0x00fb
                org.jsoup.nodes.Element r7 = r8.A()
                java.lang.String r7 = r7.a()
                java.lang.String r0 = "frameset"
                boolean r7 = r7.equals(r0)
                if (r7 != 0) goto L_0x00fb
                org.jsoup.parser.HtmlTreeBuilderState r7 = AfterFrameset
                r8.a((org.jsoup.parser.HtmlTreeBuilderState) r7)
                goto L_0x00fb
            L_0x00e1:
                boolean r7 = r7.m()
                if (r7 == 0) goto L_0x00fc
                org.jsoup.nodes.Element r7 = r8.A()
                java.lang.String r7 = r7.a()
                java.lang.String r0 = "html"
                boolean r7 = r7.equals(r0)
                if (r7 != 0) goto L_0x00fb
                r8.b((org.jsoup.parser.HtmlTreeBuilderState) r6)
                return r1
            L_0x00fb:
                return r1
            L_0x00fc:
                r8.b((org.jsoup.parser.HtmlTreeBuilderState) r6)
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.HtmlTreeBuilderState.AnonymousClass19.process(org.jsoup.parser.Token, org.jsoup.parser.HtmlTreeBuilder):boolean");
        }
    },
    AfterFrameset {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (HtmlTreeBuilderState.isWhitespace(token)) {
                htmlTreeBuilder.a(token.l());
                return true;
            } else if (token.i()) {
                htmlTreeBuilder.a(token.j());
                return true;
            } else if (token.c()) {
                htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                return false;
            } else if (token.e() && token.f().r().equals("html")) {
                return htmlTreeBuilder.a(token, InBody);
            } else {
                if (token.g() && token.h().r().equals("html")) {
                    htmlTreeBuilder.a(AfterAfterFrameset);
                    return true;
                } else if (token.e() && token.f().r().equals("noframes")) {
                    return htmlTreeBuilder.a(token, InHead);
                } else {
                    if (token.m()) {
                        return true;
                    }
                    htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                    return false;
                }
            }
        }
    },
    AfterAfterBody {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.i()) {
                htmlTreeBuilder.a(token.j());
                return true;
            } else if (token.c() || HtmlTreeBuilderState.isWhitespace(token) || (token.e() && token.f().r().equals("html"))) {
                return htmlTreeBuilder.a(token, InBody);
            } else {
                if (token.m()) {
                    return true;
                }
                htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                htmlTreeBuilder.a(InBody);
                return htmlTreeBuilder.a(token);
            }
        }
    },
    AfterAfterFrameset {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            if (token.i()) {
                htmlTreeBuilder.a(token.j());
                return true;
            } else if (token.c() || HtmlTreeBuilderState.isWhitespace(token) || (token.e() && token.f().r().equals("html"))) {
                return htmlTreeBuilder.a(token, InBody);
            } else {
                if (token.m()) {
                    return true;
                }
                if (token.e() && token.f().r().equals("noframes")) {
                    return htmlTreeBuilder.a(token, InHead);
                }
                htmlTreeBuilder.b((HtmlTreeBuilderState) this);
                return false;
            }
        }
    },
    ForeignContent {
        /* access modifiers changed from: package-private */
        public boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder) {
            return true;
        }
    };
    
    /* access modifiers changed from: private */
    public static String nullString;

    /* access modifiers changed from: package-private */
    public abstract boolean process(Token token, HtmlTreeBuilder htmlTreeBuilder);

    static {
        nullString = String.valueOf(0);
    }

    /* access modifiers changed from: private */
    public static boolean isWhitespace(Token token) {
        if (token.k()) {
            return isWhitespace(token.l().n());
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static boolean isWhitespace(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!StringUtil.b((int) str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static void handleRcData(Token.StartTag startTag, HtmlTreeBuilder htmlTreeBuilder) {
        htmlTreeBuilder.k.a(TokeniserState.Rcdata);
        htmlTreeBuilder.c();
        htmlTreeBuilder.a(Text);
        htmlTreeBuilder.a(startTag);
    }

    /* access modifiers changed from: private */
    public static void handleRawtext(Token.StartTag startTag, HtmlTreeBuilder htmlTreeBuilder) {
        htmlTreeBuilder.k.a(TokeniserState.Rawtext);
        htmlTreeBuilder.c();
        htmlTreeBuilder.a(Text);
        htmlTreeBuilder.a(startTag);
    }

    static final class Constants {

        /* renamed from: a  reason: collision with root package name */
        static final String[] f3677a = null;
        static final String[] b = null;
        static final String[] c = null;
        static final String[] d = null;
        static final String[] e = null;
        static final String[] f = null;
        static final String[] g = null;
        static final String[] h = null;
        static final String[] i = null;
        static final String[] j = null;
        static final String[] k = null;
        static final String[] l = null;
        static final String[] m = null;
        static final String[] n = null;
        static final String[] o = null;
        static final String[] p = null;
        static final String[] q = null;

        Constants() {
        }

        static {
            f3677a = new String[]{"base", "basefont", "bgsound", CommandMessage.COMMAND, "link", "meta", "noframes", WebEvent.A, "style", "title"};
            b = new String[]{"address", "article", "aside", "blockquote", "center", Tags.MiPhoneDetails.DETAILS, SharePatchInfo.g, "div", "dl", "fieldset", "figcaption", "figure", WXBasicComponentType.FOOTER, "header", "hgroup", "menu", "nav", "ol", "p", "section", MibiConstants.ee, "ul"};
            c = new String[]{"h1", "h2", "h3", "h4", PluginManager.h, "h6"};
            d = new String[]{"listing", Env.NAME_PRE};
            e = new String[]{"address", "div", "p"};
            f = new String[]{d.s, "dt"};
            g = new String[]{"b", "big", "code", "em", URIAdapter.FONT, "i", "s", "small", "strike", "strong", TtmlNode.TAG_TT, "u"};
            h = new String[]{"applet", "marquee", EventData.e};
            i = new String[]{"area", TtmlNode.TAG_BR, WXBasicComponentType.EMBED, "img", "keygen", "wbr"};
            j = new String[]{"param", "source", "track"};
            k = new String[]{"action", "name", "prompt"};
            l = new String[]{"optgroup", "option"};
            m = new String[]{"rp", "rt"};
            n = new String[]{ShareConstants.FEED_CAPTION_PARAM, Constant.KEY_COL, "colgroup", "frame", TtmlNode.TAG_HEAD, "tbody", "td", "tfoot", "th", "thead", BaseInfo.KEY_TIME_RECORD};
            o = new String[]{"address", "article", "aside", "blockquote", "button", "center", Tags.MiPhoneDetails.DETAILS, SharePatchInfo.g, "div", "dl", "fieldset", "figcaption", "figure", WXBasicComponentType.FOOTER, "header", "hgroup", "listing", "menu", "nav", "ol", Env.NAME_PRE, "section", MibiConstants.ee, "ul"};
            p = new String[]{"a", "b", "big", "code", "em", URIAdapter.FONT, "i", "nobr", "s", "small", "strike", "strong", TtmlNode.TAG_TT, "u"};
            q = new String[]{"table", "tbody", "tfoot", "thead", BaseInfo.KEY_TIME_RECORD};
        }
    }
}
