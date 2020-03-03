package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;

public class Collector {
    private Collector() {
    }

    public static Elements a(Evaluator evaluator, Element element) {
        Elements elements = new Elements();
        NodeTraversor.a((NodeVisitor) new Accumulator(element, elements, evaluator), (Node) element);
        return elements;
    }

    private static class Accumulator implements NodeVisitor {

        /* renamed from: a  reason: collision with root package name */
        private final Element f3693a;
        private final Elements b;
        private final Evaluator c;

        public void b(Node node, int i) {
        }

        Accumulator(Element element, Elements elements, Evaluator evaluator) {
            this.f3693a = element;
            this.b = elements;
            this.c = evaluator;
        }

        public void a(Node node, int i) {
            if (node instanceof Element) {
                Element element = (Element) node;
                if (this.c.a(this.f3693a, element)) {
                    this.b.add(element);
                }
            }
        }
    }

    public static Element b(Evaluator evaluator, Element element) {
        FirstFinder firstFinder = new FirstFinder(element, evaluator);
        NodeTraversor.a((NodeFilter) firstFinder, (Node) element);
        return firstFinder.b;
    }

    private static class FirstFinder implements NodeFilter {

        /* renamed from: a  reason: collision with root package name */
        private final Element f3694a;
        /* access modifiers changed from: private */
        public Element b = null;
        private final Evaluator c;

        FirstFinder(Element element, Evaluator evaluator) {
            this.f3694a = element;
            this.c = evaluator;
        }

        public NodeFilter.FilterResult a(Node node, int i) {
            if (node instanceof Element) {
                Element element = (Element) node;
                if (this.c.a(this.f3694a, element)) {
                    this.b = element;
                    return NodeFilter.FilterResult.STOP;
                }
            }
            return NodeFilter.FilterResult.CONTINUE;
        }

        public NodeFilter.FilterResult b(Node node, int i) {
            return NodeFilter.FilterResult.CONTINUE;
        }
    }
}
