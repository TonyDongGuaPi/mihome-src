package org.jsoup.select;

import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Element;

public class Selector {
    private Selector() {
    }

    public static Elements a(String str, Element element) {
        Validate.a(str);
        return a(QueryParser.a(str), element);
    }

    public static Elements a(Evaluator evaluator, Element element) {
        Validate.a((Object) evaluator);
        Validate.a((Object) element);
        return Collector.a(evaluator, element);
    }

    public static Elements a(String str, Iterable<Element> iterable) {
        Validate.a(str);
        Validate.a((Object) iterable);
        Evaluator a2 = QueryParser.a(str);
        ArrayList arrayList = new ArrayList();
        IdentityHashMap identityHashMap = new IdentityHashMap();
        for (Element a3 : iterable) {
            Iterator it = a(a2, a3).iterator();
            while (it.hasNext()) {
                Element element = (Element) it.next();
                if (!identityHashMap.containsKey(element)) {
                    arrayList.add(element);
                    identityHashMap.put(element, Boolean.TRUE);
                }
            }
        }
        return new Elements((List<Element>) arrayList);
    }

    static Elements a(Collection<Element> collection, Collection<Element> collection2) {
        Elements elements = new Elements();
        for (Element next : collection) {
            boolean z = false;
            Iterator<Element> it = collection2.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (next.equals(it.next())) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (!z) {
                elements.add(next);
            }
        }
        return elements;
    }

    public static Element b(String str, Element element) {
        Validate.a(str);
        return Collector.b(QueryParser.a(str), element);
    }

    public static class SelectorParseException extends IllegalStateException {
        public SelectorParseException(String str, Object... objArr) {
            super(String.format(str, objArr));
        }
    }
}
