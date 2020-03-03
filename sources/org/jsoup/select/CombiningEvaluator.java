package org.jsoup.select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Element;

abstract class CombiningEvaluator extends Evaluator {

    /* renamed from: a  reason: collision with root package name */
    final ArrayList<Evaluator> f3695a;
    int b;

    CombiningEvaluator() {
        this.b = 0;
        this.f3695a = new ArrayList<>();
    }

    CombiningEvaluator(Collection<Evaluator> collection) {
        this();
        this.f3695a.addAll(collection);
        b();
    }

    /* access modifiers changed from: package-private */
    public Evaluator a() {
        if (this.b > 0) {
            return this.f3695a.get(this.b - 1);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void a(Evaluator evaluator) {
        this.f3695a.set(this.b - 1, evaluator);
    }

    /* access modifiers changed from: package-private */
    public void b() {
        this.b = this.f3695a.size();
    }

    static final class And extends CombiningEvaluator {
        And(Collection<Evaluator> collection) {
            super(collection);
        }

        And(Evaluator... evaluatorArr) {
            this((Collection<Evaluator>) Arrays.asList(evaluatorArr));
        }

        public boolean a(Element element, Element element2) {
            for (int i = 0; i < this.b; i++) {
                if (!((Evaluator) this.f3695a.get(i)).a(element, element2)) {
                    return false;
                }
            }
            return true;
        }

        public String toString() {
            return StringUtil.a((Collection) this.f3695a, " ");
        }
    }

    static final class Or extends CombiningEvaluator {
        Or(Collection<Evaluator> collection) {
            if (this.b > 1) {
                this.f3695a.add(new And(collection));
            } else {
                this.f3695a.addAll(collection);
            }
            b();
        }

        Or(Evaluator... evaluatorArr) {
            this((Collection<Evaluator>) Arrays.asList(evaluatorArr));
        }

        Or() {
        }

        public void b(Evaluator evaluator) {
            this.f3695a.add(evaluator);
            b();
        }

        public boolean a(Element element, Element element2) {
            for (int i = 0; i < this.b; i++) {
                if (((Evaluator) this.f3695a.get(i)).a(element, element2)) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return StringUtil.a((Collection) this.f3695a, ", ");
        }
    }
}
