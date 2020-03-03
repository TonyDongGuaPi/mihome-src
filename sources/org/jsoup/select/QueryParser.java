package org.jsoup.select;

import com.amap.location.common.model.Adjacent;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.helper.StringUtil;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.parser.TokenQueue;
import org.jsoup.select.CombiningEvaluator;
import org.jsoup.select.Evaluator;
import org.jsoup.select.Selector;
import org.jsoup.select.StructuralEvaluator;

public class QueryParser {

    /* renamed from: a  reason: collision with root package name */
    private static final String[] f3712a = {",", ">", "+", Constants.J, " "};
    private static final String[] b = {"=", Operators.NOT_EQUAL2, "^=", "$=", "*=", "~="};
    private static final Pattern f = Pattern.compile("(([+-])?(\\d+)?)n(\\s*([+-])?\\s*\\d+)?", 2);
    private static final Pattern g = Pattern.compile("([+-])?(\\d+)");
    private TokenQueue c;
    private String d;
    private List<Evaluator> e = new ArrayList();

    private QueryParser(String str) {
        this.d = str;
        this.c = new TokenQueue(str);
    }

    public static Evaluator a(String str) {
        try {
            return new QueryParser(str).a();
        } catch (IllegalArgumentException e2) {
            throw new Selector.SelectorParseException(e2.getMessage(), new Object[0]);
        }
    }

    /* access modifiers changed from: package-private */
    public Evaluator a() {
        this.c.h();
        if (this.c.a(f3712a)) {
            this.e.add(new StructuralEvaluator.Root());
            a(this.c.g());
        } else {
            c();
        }
        while (!this.c.a()) {
            boolean h = this.c.h();
            if (this.c.a(f3712a)) {
                a(this.c.g());
            } else if (h) {
                a(' ');
            } else {
                c();
            }
        }
        if (this.e.size() == 1) {
            return this.e.get(0);
        }
        return new CombiningEvaluator.And((Collection<Evaluator>) this.e);
    }

    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00b2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(char r11) {
        /*
            r10 = this;
            org.jsoup.parser.TokenQueue r0 = r10.c
            r0.h()
            java.lang.String r0 = r10.b()
            org.jsoup.select.Evaluator r0 = a((java.lang.String) r0)
            java.util.List<org.jsoup.select.Evaluator> r1 = r10.e
            int r1 = r1.size()
            r2 = 44
            r3 = 1
            r4 = 0
            if (r1 != r3) goto L_0x0036
            java.util.List<org.jsoup.select.Evaluator> r1 = r10.e
            java.lang.Object r1 = r1.get(r4)
            org.jsoup.select.Evaluator r1 = (org.jsoup.select.Evaluator) r1
            boolean r5 = r1 instanceof org.jsoup.select.CombiningEvaluator.Or
            if (r5 == 0) goto L_0x0033
            if (r11 == r2) goto L_0x0033
            r5 = r1
            org.jsoup.select.CombiningEvaluator$Or r5 = (org.jsoup.select.CombiningEvaluator.Or) r5
            org.jsoup.select.Evaluator r5 = r5.a()
            r6 = 1
            r9 = r5
            r5 = r1
            r1 = r9
            goto L_0x003e
        L_0x0033:
            r5 = r1
            r6 = 0
            goto L_0x003e
        L_0x0036:
            org.jsoup.select.CombiningEvaluator$And r1 = new org.jsoup.select.CombiningEvaluator$And
            java.util.List<org.jsoup.select.Evaluator> r5 = r10.e
            r1.<init>((java.util.Collection<org.jsoup.select.Evaluator>) r5)
            goto L_0x0033
        L_0x003e:
            java.util.List<org.jsoup.select.Evaluator> r7 = r10.e
            r7.clear()
            r7 = 62
            r8 = 2
            if (r11 != r7) goto L_0x0059
            org.jsoup.select.CombiningEvaluator$And r11 = new org.jsoup.select.CombiningEvaluator$And
            org.jsoup.select.Evaluator[] r2 = new org.jsoup.select.Evaluator[r8]
            r2[r4] = r0
            org.jsoup.select.StructuralEvaluator$ImmediateParent r0 = new org.jsoup.select.StructuralEvaluator$ImmediateParent
            r0.<init>(r1)
            r2[r3] = r0
            r11.<init>((org.jsoup.select.Evaluator[]) r2)
            goto L_0x00b0
        L_0x0059:
            r7 = 32
            if (r11 != r7) goto L_0x006e
            org.jsoup.select.CombiningEvaluator$And r11 = new org.jsoup.select.CombiningEvaluator$And
            org.jsoup.select.Evaluator[] r2 = new org.jsoup.select.Evaluator[r8]
            r2[r4] = r0
            org.jsoup.select.StructuralEvaluator$Parent r0 = new org.jsoup.select.StructuralEvaluator$Parent
            r0.<init>(r1)
            r2[r3] = r0
            r11.<init>((org.jsoup.select.Evaluator[]) r2)
            goto L_0x00b0
        L_0x006e:
            r7 = 43
            if (r11 != r7) goto L_0x0083
            org.jsoup.select.CombiningEvaluator$And r11 = new org.jsoup.select.CombiningEvaluator$And
            org.jsoup.select.Evaluator[] r2 = new org.jsoup.select.Evaluator[r8]
            r2[r4] = r0
            org.jsoup.select.StructuralEvaluator$ImmediatePreviousSibling r0 = new org.jsoup.select.StructuralEvaluator$ImmediatePreviousSibling
            r0.<init>(r1)
            r2[r3] = r0
            r11.<init>((org.jsoup.select.Evaluator[]) r2)
            goto L_0x00b0
        L_0x0083:
            r7 = 126(0x7e, float:1.77E-43)
            if (r11 != r7) goto L_0x0098
            org.jsoup.select.CombiningEvaluator$And r11 = new org.jsoup.select.CombiningEvaluator$And
            org.jsoup.select.Evaluator[] r2 = new org.jsoup.select.Evaluator[r8]
            r2[r4] = r0
            org.jsoup.select.StructuralEvaluator$PreviousSibling r0 = new org.jsoup.select.StructuralEvaluator$PreviousSibling
            r0.<init>(r1)
            r2[r3] = r0
            r11.<init>((org.jsoup.select.Evaluator[]) r2)
            goto L_0x00b0
        L_0x0098:
            if (r11 != r2) goto L_0x00bf
            boolean r11 = r1 instanceof org.jsoup.select.CombiningEvaluator.Or
            if (r11 == 0) goto L_0x00a5
            org.jsoup.select.CombiningEvaluator$Or r1 = (org.jsoup.select.CombiningEvaluator.Or) r1
            r1.b(r0)
            r11 = r1
            goto L_0x00b0
        L_0x00a5:
            org.jsoup.select.CombiningEvaluator$Or r11 = new org.jsoup.select.CombiningEvaluator$Or
            r11.<init>()
            r11.b(r1)
            r11.b(r0)
        L_0x00b0:
            if (r6 == 0) goto L_0x00b9
            r0 = r5
            org.jsoup.select.CombiningEvaluator$Or r0 = (org.jsoup.select.CombiningEvaluator.Or) r0
            r0.a(r11)
            r11 = r5
        L_0x00b9:
            java.util.List<org.jsoup.select.Evaluator> r0 = r10.e
            r0.add(r11)
            return
        L_0x00bf:
            org.jsoup.select.Selector$SelectorParseException r0 = new org.jsoup.select.Selector$SelectorParseException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unknown combinator: "
            r1.append(r2)
            r1.append(r11)
            java.lang.String r11 = r1.toString()
            java.lang.Object[] r1 = new java.lang.Object[r4]
            r0.<init>(r11, r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.select.QueryParser.a(char):void");
    }

    private String b() {
        StringBuilder sb = new StringBuilder();
        while (!this.c.a()) {
            if (this.c.b(Operators.BRACKET_START_STR)) {
                sb.append(Operators.BRACKET_START_STR);
                sb.append(this.c.a(Operators.BRACKET_START, Operators.BRACKET_END));
                sb.append(Operators.BRACKET_END_STR);
            } else if (this.c.b(Operators.ARRAY_START_STR)) {
                sb.append(Operators.ARRAY_START_STR);
                sb.append(this.c.a(Operators.ARRAY_START, Operators.ARRAY_END));
                sb.append(Operators.ARRAY_END_STR);
            } else if (this.c.a(f3712a)) {
                break;
            } else {
                sb.append(this.c.g());
            }
        }
        return sb.toString();
    }

    private void c() {
        if (this.c.d("#")) {
            d();
        } else if (this.c.d(".")) {
            e();
        } else if (this.c.e() || this.c.b("*|")) {
            f();
        } else if (this.c.b(Operators.ARRAY_START_STR)) {
            g();
        } else if (this.c.d("*")) {
            h();
        } else if (this.c.d(":lt(")) {
            i();
        } else if (this.c.d(":gt(")) {
            j();
        } else if (this.c.d(":eq(")) {
            k();
        } else if (this.c.b(":has(")) {
            m();
        } else if (this.c.b(":contains(")) {
            a(false);
        } else if (this.c.b(":containsOwn(")) {
            a(true);
        } else if (this.c.b(":containsData(")) {
            n();
        } else if (this.c.b(":matches(")) {
            b(false);
        } else if (this.c.b(":matchesOwn(")) {
            b(true);
        } else if (this.c.b(":not(")) {
            o();
        } else if (this.c.d(":nth-child(")) {
            a(false, false);
        } else if (this.c.d(":nth-last-child(")) {
            a(true, false);
        } else if (this.c.d(":nth-of-type(")) {
            a(false, true);
        } else if (this.c.d(":nth-last-of-type(")) {
            a(true, true);
        } else if (this.c.d(":first-child")) {
            this.e.add(new Evaluator.IsFirstChild());
        } else if (this.c.d(":last-child")) {
            this.e.add(new Evaluator.IsLastChild());
        } else if (this.c.d(":first-of-type")) {
            this.e.add(new Evaluator.IsFirstOfType());
        } else if (this.c.d(":last-of-type")) {
            this.e.add(new Evaluator.IsLastOfType());
        } else if (this.c.d(":only-child")) {
            this.e.add(new Evaluator.IsOnlyChild());
        } else if (this.c.d(":only-of-type")) {
            this.e.add(new Evaluator.IsOnlyOfType());
        } else if (this.c.d(":empty")) {
            this.e.add(new Evaluator.IsEmpty());
        } else if (this.c.d(":root")) {
            this.e.add(new Evaluator.IsRoot());
        } else if (this.c.d(":matchText")) {
            this.e.add(new Evaluator.MatchText());
        } else {
            throw new Selector.SelectorParseException("Could not parse query '%s': unexpected token at '%s'", this.d, this.c.n());
        }
    }

    private void d() {
        String l = this.c.l();
        Validate.a(l);
        this.e.add(new Evaluator.Id(l));
    }

    private void e() {
        String l = this.c.l();
        Validate.a(l);
        this.e.add(new Evaluator.Class(l.trim()));
    }

    private void f() {
        String k = this.c.k();
        Validate.a(k);
        if (k.startsWith("*|")) {
            this.e.add(new CombiningEvaluator.Or(new Evaluator.Tag(Normalizer.b(k)), new Evaluator.TagEndsWith(Normalizer.b(k.replace("*|", ":")))));
            return;
        }
        if (k.contains("|")) {
            k = k.replace("|", ":");
        }
        this.e.add(new Evaluator.Tag(k.trim()));
    }

    private void g() {
        TokenQueue tokenQueue = new TokenQueue(this.c.a(Operators.ARRAY_START, Operators.ARRAY_END));
        String b2 = tokenQueue.b(b);
        Validate.a(b2);
        tokenQueue.h();
        if (tokenQueue.a()) {
            if (b2.startsWith("^")) {
                this.e.add(new Evaluator.AttributeStarting(b2.substring(1)));
            } else {
                this.e.add(new Evaluator.Attribute(b2));
            }
        } else if (tokenQueue.d("=")) {
            this.e.add(new Evaluator.AttributeWithValue(b2, tokenQueue.n()));
        } else if (tokenQueue.d(Operators.NOT_EQUAL2)) {
            this.e.add(new Evaluator.AttributeWithValueNot(b2, tokenQueue.n()));
        } else if (tokenQueue.d("^=")) {
            this.e.add(new Evaluator.AttributeWithValueStarting(b2, tokenQueue.n()));
        } else if (tokenQueue.d("$=")) {
            this.e.add(new Evaluator.AttributeWithValueEnding(b2, tokenQueue.n()));
        } else if (tokenQueue.d("*=")) {
            this.e.add(new Evaluator.AttributeWithValueContaining(b2, tokenQueue.n()));
        } else if (tokenQueue.d("~=")) {
            this.e.add(new Evaluator.AttributeWithValueMatching(b2, Pattern.compile(tokenQueue.n())));
        } else {
            throw new Selector.SelectorParseException("Could not parse attribute query '%s': unexpected token at '%s'", this.d, tokenQueue.n());
        }
    }

    private void h() {
        this.e.add(new Evaluator.AllElements());
    }

    private void i() {
        this.e.add(new Evaluator.IndexLessThan(l()));
    }

    private void j() {
        this.e.add(new Evaluator.IndexGreaterThan(l()));
    }

    private void k() {
        this.e.add(new Evaluator.IndexEquals(l()));
    }

    private void a(boolean z, boolean z2) {
        String b2 = Normalizer.b(this.c.h(Operators.BRACKET_END_STR));
        Matcher matcher = f.matcher(b2);
        Matcher matcher2 = g.matcher(b2);
        int i = 2;
        int i2 = 0;
        if (Adjacent.f.equals(b2)) {
            i2 = 1;
        } else if (!Adjacent.e.equals(b2)) {
            if (matcher.matches()) {
                i = matcher.group(3) != null ? Integer.parseInt(matcher.group(1).replaceFirst("^\\+", "")) : 1;
                if (matcher.group(4) != null) {
                    i2 = Integer.parseInt(matcher.group(4).replaceFirst("^\\+", ""));
                }
            } else if (matcher2.matches()) {
                i2 = Integer.parseInt(matcher2.group().replaceFirst("^\\+", ""));
                i = 0;
            } else {
                throw new Selector.SelectorParseException("Could not parse nth-index '%s': unexpected format", b2);
            }
        }
        if (z2) {
            if (z) {
                this.e.add(new Evaluator.IsNthLastOfType(i, i2));
            } else {
                this.e.add(new Evaluator.IsNthOfType(i, i2));
            }
        } else if (z) {
            this.e.add(new Evaluator.IsNthLastChild(i, i2));
        } else {
            this.e.add(new Evaluator.IsNthChild(i, i2));
        }
    }

    private int l() {
        String trim = this.c.h(Operators.BRACKET_END_STR).trim();
        Validate.a(StringUtil.b(trim), "Index must be numeric");
        return Integer.parseInt(trim);
    }

    private void m() {
        this.c.e(":has");
        String a2 = this.c.a(Operators.BRACKET_START, Operators.BRACKET_END);
        Validate.a(a2, ":has(el) subselect must not be empty");
        this.e.add(new StructuralEvaluator.Has(a(a2)));
    }

    private void a(boolean z) {
        this.c.e(z ? ":containsOwn" : ":contains");
        String j = TokenQueue.j(this.c.a(Operators.BRACKET_START, Operators.BRACKET_END));
        Validate.a(j, ":contains(text) query must not be empty");
        if (z) {
            this.e.add(new Evaluator.ContainsOwnText(j));
        } else {
            this.e.add(new Evaluator.ContainsText(j));
        }
    }

    private void n() {
        this.c.e(":containsData");
        String j = TokenQueue.j(this.c.a(Operators.BRACKET_START, Operators.BRACKET_END));
        Validate.a(j, ":containsData(text) query must not be empty");
        this.e.add(new Evaluator.ContainsData(j));
    }

    private void b(boolean z) {
        this.c.e(z ? ":matchesOwn" : ":matches");
        String a2 = this.c.a(Operators.BRACKET_START, Operators.BRACKET_END);
        Validate.a(a2, ":matches(regex) query must not be empty");
        if (z) {
            this.e.add(new Evaluator.MatchesOwn(Pattern.compile(a2)));
        } else {
            this.e.add(new Evaluator.Matches(Pattern.compile(a2)));
        }
    }

    private void o() {
        this.c.e(":not");
        String a2 = this.c.a(Operators.BRACKET_START, Operators.BRACKET_END);
        Validate.a(a2, ":not(selector) subselect must not be empty");
        this.e.add(new StructuralEvaluator.Not(a(a2)));
    }
}
