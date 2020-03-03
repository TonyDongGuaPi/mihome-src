package org.jsoup.select;

import java.util.Iterator;
import java.util.regex.Pattern;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.PseudoTextElement;
import org.jsoup.nodes.TextNode;
import org.jsoup.nodes.XmlDeclaration;

public abstract class Evaluator {

    public static final class AllElements extends Evaluator {
        public boolean a(Element element, Element element2) {
            return true;
        }

        public String toString() {
            return "*";
        }
    }

    public abstract boolean a(Element element, Element element2);

    protected Evaluator() {
    }

    public static final class Tag extends Evaluator {

        /* renamed from: a  reason: collision with root package name */
        private String f3709a;

        public Tag(String str) {
            this.f3709a = str;
        }

        public boolean a(Element element, Element element2) {
            return element2.t().equalsIgnoreCase(this.f3709a);
        }

        public String toString() {
            return String.format("%s", new Object[]{this.f3709a});
        }
    }

    public static final class TagEndsWith extends Evaluator {

        /* renamed from: a  reason: collision with root package name */
        private String f3710a;

        public TagEndsWith(String str) {
            this.f3710a = str;
        }

        public boolean a(Element element, Element element2) {
            return element2.t().endsWith(this.f3710a);
        }

        public String toString() {
            return String.format("%s", new Object[]{this.f3710a});
        }
    }

    public static final class Id extends Evaluator {

        /* renamed from: a  reason: collision with root package name */
        private String f3705a;

        public Id(String str) {
            this.f3705a = str;
        }

        public boolean a(Element element, Element element2) {
            return this.f3705a.equals(element2.w());
        }

        public String toString() {
            return String.format("#%s", new Object[]{this.f3705a});
        }
    }

    public static final class Class extends Evaluator {

        /* renamed from: a  reason: collision with root package name */
        private String f3700a;

        public Class(String str) {
            this.f3700a = str;
        }

        public boolean a(Element element, Element element2) {
            return element2.F(this.f3700a);
        }

        public String toString() {
            return String.format(".%s", new Object[]{this.f3700a});
        }
    }

    public static final class Attribute extends Evaluator {

        /* renamed from: a  reason: collision with root package name */
        private String f3696a;

        public Attribute(String str) {
            this.f3696a = str;
        }

        public boolean a(Element element, Element element2) {
            return element2.c(this.f3696a);
        }

        public String toString() {
            return String.format("[%s]", new Object[]{this.f3696a});
        }
    }

    public static final class AttributeStarting extends Evaluator {

        /* renamed from: a  reason: collision with root package name */
        private String f3698a;

        public AttributeStarting(String str) {
            Validate.a(str);
            this.f3698a = Normalizer.a(str);
        }

        public boolean a(Element element, Element element2) {
            for (org.jsoup.nodes.Attribute a2 : element2.s().b()) {
                if (Normalizer.a(a2.getKey()).startsWith(this.f3698a)) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return String.format("[^%s]", new Object[]{this.f3698a});
        }
    }

    public static final class AttributeWithValue extends AttributeKeyPair {
        public AttributeWithValue(String str, String str2) {
            super(str, str2);
        }

        public boolean a(Element element, Element element2) {
            return element2.c(this.f3697a) && this.b.equalsIgnoreCase(element2.d(this.f3697a).trim());
        }

        public String toString() {
            return String.format("[%s=%s]", new Object[]{this.f3697a, this.b});
        }
    }

    public static final class AttributeWithValueNot extends AttributeKeyPair {
        public AttributeWithValueNot(String str, String str2) {
            super(str, str2);
        }

        public boolean a(Element element, Element element2) {
            return !this.b.equalsIgnoreCase(element2.d(this.f3697a));
        }

        public String toString() {
            return String.format("[%s!=%s]", new Object[]{this.f3697a, this.b});
        }
    }

    public static final class AttributeWithValueStarting extends AttributeKeyPair {
        public AttributeWithValueStarting(String str, String str2) {
            super(str, str2);
        }

        public boolean a(Element element, Element element2) {
            return element2.c(this.f3697a) && Normalizer.a(element2.d(this.f3697a)).startsWith(this.b);
        }

        public String toString() {
            return String.format("[%s^=%s]", new Object[]{this.f3697a, this.b});
        }
    }

    public static final class AttributeWithValueEnding extends AttributeKeyPair {
        public AttributeWithValueEnding(String str, String str2) {
            super(str, str2);
        }

        public boolean a(Element element, Element element2) {
            return element2.c(this.f3697a) && Normalizer.a(element2.d(this.f3697a)).endsWith(this.b);
        }

        public String toString() {
            return String.format("[%s$=%s]", new Object[]{this.f3697a, this.b});
        }
    }

    public static final class AttributeWithValueContaining extends AttributeKeyPair {
        public AttributeWithValueContaining(String str, String str2) {
            super(str, str2);
        }

        public boolean a(Element element, Element element2) {
            return element2.c(this.f3697a) && Normalizer.a(element2.d(this.f3697a)).contains(this.b);
        }

        public String toString() {
            return String.format("[%s*=%s]", new Object[]{this.f3697a, this.b});
        }
    }

    public static final class AttributeWithValueMatching extends Evaluator {

        /* renamed from: a  reason: collision with root package name */
        String f3699a;
        Pattern b;

        public AttributeWithValueMatching(String str, Pattern pattern) {
            this.f3699a = Normalizer.b(str);
            this.b = pattern;
        }

        public boolean a(Element element, Element element2) {
            return element2.c(this.f3699a) && this.b.matcher(element2.d(this.f3699a)).find();
        }

        public String toString() {
            return String.format("[%s~=%s]", new Object[]{this.f3699a, this.b.toString()});
        }
    }

    public static abstract class AttributeKeyPair extends Evaluator {

        /* renamed from: a  reason: collision with root package name */
        String f3697a;
        String b;

        public AttributeKeyPair(String str, String str2) {
            Validate.a(str);
            Validate.a(str2);
            this.f3697a = Normalizer.b(str);
            if ((str2.startsWith("\"") && str2.endsWith("\"")) || (str2.startsWith("'") && str2.endsWith("'"))) {
                str2 = str2.substring(1, str2.length() - 1);
            }
            this.b = Normalizer.b(str2);
        }
    }

    public static final class IndexLessThan extends IndexEvaluator {
        public IndexLessThan(int i) {
            super(i);
        }

        public boolean a(Element element, Element element2) {
            return element != element2 && element2.K() < this.f3706a;
        }

        public String toString() {
            return String.format(":lt(%d)", new Object[]{Integer.valueOf(this.f3706a)});
        }
    }

    public static final class IndexGreaterThan extends IndexEvaluator {
        public IndexGreaterThan(int i) {
            super(i);
        }

        public boolean a(Element element, Element element2) {
            return element2.K() > this.f3706a;
        }

        public String toString() {
            return String.format(":gt(%d)", new Object[]{Integer.valueOf(this.f3706a)});
        }
    }

    public static final class IndexEquals extends IndexEvaluator {
        public IndexEquals(int i) {
            super(i);
        }

        public boolean a(Element element, Element element2) {
            return element2.K() == this.f3706a;
        }

        public String toString() {
            return String.format(":eq(%d)", new Object[]{Integer.valueOf(this.f3706a)});
        }
    }

    public static final class IsLastChild extends Evaluator {
        public String toString() {
            return ":last-child";
        }

        public boolean a(Element element, Element element2) {
            Element y = element2.Y();
            if (y == null || (y instanceof Document) || element2.K() != y.A().size() - 1) {
                return false;
            }
            return true;
        }
    }

    public static final class IsFirstOfType extends IsNthOfType {
        public String toString() {
            return ":first-of-type";
        }

        public IsFirstOfType() {
            super(0, 1);
        }
    }

    public static final class IsLastOfType extends IsNthLastOfType {
        public String toString() {
            return ":last-of-type";
        }

        public IsLastOfType() {
            super(0, 1);
        }
    }

    public static abstract class CssNthEvaluator extends Evaluator {

        /* renamed from: a  reason: collision with root package name */
        protected final int f3704a;
        protected final int b;

        /* access modifiers changed from: protected */
        public abstract String a();

        /* access modifiers changed from: protected */
        public abstract int b(Element element, Element element2);

        public CssNthEvaluator(int i, int i2) {
            this.f3704a = i;
            this.b = i2;
        }

        public CssNthEvaluator(int i) {
            this(0, i);
        }

        public boolean a(Element element, Element element2) {
            Element y = element2.Y();
            if (y == null || (y instanceof Document)) {
                return false;
            }
            int b2 = b(element, element2);
            if (this.f3704a == 0) {
                return b2 == this.b;
            }
            if ((b2 - this.b) * this.f3704a < 0 || (b2 - this.b) % this.f3704a != 0) {
                return false;
            }
            return true;
        }

        public String toString() {
            if (this.f3704a == 0) {
                return String.format(":%s(%d)", new Object[]{a(), Integer.valueOf(this.b)});
            } else if (this.b == 0) {
                return String.format(":%s(%dn)", new Object[]{a(), Integer.valueOf(this.f3704a)});
            } else {
                return String.format(":%s(%dn%+d)", new Object[]{a(), Integer.valueOf(this.f3704a), Integer.valueOf(this.b)});
            }
        }
    }

    public static final class IsNthChild extends CssNthEvaluator {
        /* access modifiers changed from: protected */
        public String a() {
            return "nth-child";
        }

        public IsNthChild(int i, int i2) {
            super(i, i2);
        }

        /* access modifiers changed from: protected */
        public int b(Element element, Element element2) {
            return element2.K() + 1;
        }
    }

    public static final class IsNthLastChild extends CssNthEvaluator {
        /* access modifiers changed from: protected */
        public String a() {
            return "nth-last-child";
        }

        public IsNthLastChild(int i, int i2) {
            super(i, i2);
        }

        /* access modifiers changed from: protected */
        public int b(Element element, Element element2) {
            return element2.Y().A().size() - element2.K();
        }
    }

    public static class IsNthOfType extends CssNthEvaluator {
        /* access modifiers changed from: protected */
        public String a() {
            return "nth-of-type";
        }

        public IsNthOfType(int i, int i2) {
            super(i, i2);
        }

        /* access modifiers changed from: protected */
        public int b(Element element, Element element2) {
            Iterator it = element2.Y().A().iterator();
            int i = 0;
            while (it.hasNext()) {
                Element element3 = (Element) it.next();
                if (element3.u().equals(element2.u())) {
                    i++;
                    continue;
                }
                if (element3 == element2) {
                    break;
                }
            }
            return i;
        }
    }

    public static class IsNthLastOfType extends CssNthEvaluator {
        /* access modifiers changed from: protected */
        public String a() {
            return "nth-last-of-type";
        }

        public IsNthLastOfType(int i, int i2) {
            super(i, i2);
        }

        /* access modifiers changed from: protected */
        public int b(Element element, Element element2) {
            Elements A = element2.Y().A();
            int i = 0;
            for (int K = element2.K(); K < A.size(); K++) {
                if (((Element) A.get(K)).u().equals(element2.u())) {
                    i++;
                }
            }
            return i;
        }
    }

    public static final class IsFirstChild extends Evaluator {
        public String toString() {
            return ":first-child";
        }

        public boolean a(Element element, Element element2) {
            Element y = element2.Y();
            return y != null && !(y instanceof Document) && element2.K() == 0;
        }
    }

    public static final class IsRoot extends Evaluator {
        public String toString() {
            return ":root";
        }

        public boolean a(Element element, Element element2) {
            if (element instanceof Document) {
                element = element.a(0);
            }
            return element2 == element;
        }
    }

    public static final class IsOnlyChild extends Evaluator {
        public String toString() {
            return ":only-child";
        }

        public boolean a(Element element, Element element2) {
            Element y = element2.Y();
            return y != null && !(y instanceof Document) && element2.G().size() == 0;
        }
    }

    public static final class IsOnlyOfType extends Evaluator {
        public String toString() {
            return ":only-of-type";
        }

        public boolean a(Element element, Element element2) {
            Element y = element2.Y();
            if (y == null || (y instanceof Document)) {
                return false;
            }
            Iterator it = y.A().iterator();
            int i = 0;
            while (it.hasNext()) {
                if (((Element) it.next()).u().equals(element2.u())) {
                    i++;
                }
            }
            return i == 1;
        }
    }

    public static final class IsEmpty extends Evaluator {
        public String toString() {
            return ":empty";
        }

        public boolean a(Element element, Element element2) {
            for (Node next : element2.ab()) {
                if (!(next instanceof Comment) && !(next instanceof XmlDeclaration) && !(next instanceof DocumentType)) {
                    return false;
                }
            }
            return true;
        }
    }

    public static abstract class IndexEvaluator extends Evaluator {

        /* renamed from: a  reason: collision with root package name */
        int f3706a;

        public IndexEvaluator(int i) {
            this.f3706a = i;
        }
    }

    public static final class ContainsText extends Evaluator {

        /* renamed from: a  reason: collision with root package name */
        private String f3703a;

        public ContainsText(String str) {
            this.f3703a = Normalizer.a(str);
        }

        public boolean a(Element element, Element element2) {
            return Normalizer.a(element2.N()).contains(this.f3703a);
        }

        public String toString() {
            return String.format(":contains(%s)", new Object[]{this.f3703a});
        }
    }

    public static final class ContainsData extends Evaluator {

        /* renamed from: a  reason: collision with root package name */
        private String f3701a;

        public ContainsData(String str) {
            this.f3701a = Normalizer.a(str);
        }

        public boolean a(Element element, Element element2) {
            return Normalizer.a(element2.R()).contains(this.f3701a);
        }

        public String toString() {
            return String.format(":containsData(%s)", new Object[]{this.f3701a});
        }
    }

    public static final class ContainsOwnText extends Evaluator {

        /* renamed from: a  reason: collision with root package name */
        private String f3702a;

        public ContainsOwnText(String str) {
            this.f3702a = Normalizer.a(str);
        }

        public boolean a(Element element, Element element2) {
            return Normalizer.a(element2.P()).contains(this.f3702a);
        }

        public String toString() {
            return String.format(":containsOwn(%s)", new Object[]{this.f3702a});
        }
    }

    public static final class Matches extends Evaluator {

        /* renamed from: a  reason: collision with root package name */
        private Pattern f3707a;

        public Matches(Pattern pattern) {
            this.f3707a = pattern;
        }

        public boolean a(Element element, Element element2) {
            return this.f3707a.matcher(element2.N()).find();
        }

        public String toString() {
            return String.format(":matches(%s)", new Object[]{this.f3707a});
        }
    }

    public static final class MatchesOwn extends Evaluator {

        /* renamed from: a  reason: collision with root package name */
        private Pattern f3708a;

        public MatchesOwn(Pattern pattern) {
            this.f3708a = pattern;
        }

        public boolean a(Element element, Element element2) {
            return this.f3708a.matcher(element2.P()).find();
        }

        public String toString() {
            return String.format(":matchesOwn(%s)", new Object[]{this.f3708a});
        }
    }

    public static final class MatchText extends Evaluator {
        public String toString() {
            return ":matchText";
        }

        public boolean a(Element element, Element element2) {
            if (element2 instanceof PseudoTextElement) {
                return true;
            }
            for (TextNode next : element2.C()) {
                PseudoTextElement pseudoTextElement = new PseudoTextElement(org.jsoup.parser.Tag.valueOf(element2.t()), element2.d(), element2.s());
                next.k(pseudoTextElement);
                pseudoTextElement.a((Node) next);
            }
            return false;
        }
    }
}
