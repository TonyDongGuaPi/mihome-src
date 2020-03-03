package org.jsoup.select;

import java.util.Iterator;
import org.jsoup.nodes.Element;

abstract class StructuralEvaluator extends Evaluator {

    /* renamed from: a  reason: collision with root package name */
    Evaluator f3713a;

    StructuralEvaluator() {
    }

    static class Root extends Evaluator {
        public boolean a(Element element, Element element2) {
            return element == element2;
        }

        Root() {
        }
    }

    static class Has extends StructuralEvaluator {
        public Has(Evaluator evaluator) {
            this.f3713a = evaluator;
        }

        public boolean a(Element element, Element element2) {
            Iterator it = element2.M().iterator();
            while (it.hasNext()) {
                Element element3 = (Element) it.next();
                if (element3 != element2 && this.f3713a.a(element, element3)) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return String.format(":has(%s)", new Object[]{this.f3713a});
        }
    }

    static class Not extends StructuralEvaluator {
        public Not(Evaluator evaluator) {
            this.f3713a = evaluator;
        }

        public boolean a(Element element, Element element2) {
            return !this.f3713a.a(element, element2);
        }

        public String toString() {
            return String.format(":not%s", new Object[]{this.f3713a});
        }
    }

    static class Parent extends StructuralEvaluator {
        public Parent(Evaluator evaluator) {
            this.f3713a = evaluator;
        }

        public boolean a(Element element, Element element2) {
            if (element == element2) {
                return false;
            }
            for (Element y = element2.Y(); !this.f3713a.a(element, y); y = y.Y()) {
                if (y == element) {
                    return false;
                }
            }
            return true;
        }

        public String toString() {
            return String.format(":parent%s", new Object[]{this.f3713a});
        }
    }

    static class ImmediateParent extends StructuralEvaluator {
        public ImmediateParent(Evaluator evaluator) {
            this.f3713a = evaluator;
        }

        public boolean a(Element element, Element element2) {
            Element y;
            if (element == element2 || (y = element2.Y()) == null || !this.f3713a.a(element, y)) {
                return false;
            }
            return true;
        }

        public String toString() {
            return String.format(":ImmediateParent%s", new Object[]{this.f3713a});
        }
    }

    static class PreviousSibling extends StructuralEvaluator {
        public PreviousSibling(Evaluator evaluator) {
            this.f3713a = evaluator;
        }

        public boolean a(Element element, Element element2) {
            if (element == element2) {
                return false;
            }
            for (Element I = element2.I(); I != null; I = I.I()) {
                if (this.f3713a.a(element, I)) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return String.format(":prev*%s", new Object[]{this.f3713a});
        }
    }

    static class ImmediatePreviousSibling extends StructuralEvaluator {
        public ImmediatePreviousSibling(Evaluator evaluator) {
            this.f3713a = evaluator;
        }

        public boolean a(Element element, Element element2) {
            Element I;
            if (element == element2 || (I = element2.I()) == null || !this.f3713a.a(element, I)) {
                return false;
            }
            return true;
        }

        public String toString() {
            return String.format(":prev%s", new Object[]{this.f3713a});
        }
    }
}
