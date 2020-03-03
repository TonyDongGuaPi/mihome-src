package org.jsoup.select;

import java.util.Iterator;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;

public class NodeTraversor {

    /* renamed from: a  reason: collision with root package name */
    private NodeVisitor f3711a;

    public NodeTraversor(NodeVisitor nodeVisitor) {
        this.f3711a = nodeVisitor;
    }

    public void a(Node node) {
        a(this.f3711a, node);
    }

    public static void a(NodeVisitor nodeVisitor, Node node) {
        Node node2 = node;
        int i = 0;
        while (node2 != null) {
            nodeVisitor.a(node2, i);
            if (node2.c() > 0) {
                node2 = node2.e(0);
                i++;
            } else {
                while (node2.ak() == null && i > 0) {
                    nodeVisitor.b(node2, i);
                    node2 = node2.ae();
                    i--;
                }
                nodeVisitor.b(node2, i);
                if (node2 != node) {
                    node2 = node2.ak();
                } else {
                    return;
                }
            }
        }
    }

    public static void a(NodeVisitor nodeVisitor, Elements elements) {
        Validate.a((Object) nodeVisitor);
        Validate.a((Object) elements);
        Iterator it = elements.iterator();
        while (it.hasNext()) {
            a(nodeVisitor, (Node) (Element) it.next());
        }
    }

    public static NodeFilter.FilterResult a(NodeFilter nodeFilter, Node node) {
        Node node2 = node;
        int i = 0;
        while (node2 != null) {
            NodeFilter.FilterResult a2 = nodeFilter.a(node2, i);
            if (a2 == NodeFilter.FilterResult.STOP) {
                return a2;
            }
            if (a2 != NodeFilter.FilterResult.CONTINUE || node2.c() <= 0) {
                while (node2.ak() == null && i > 0) {
                    if ((a2 == NodeFilter.FilterResult.CONTINUE || a2 == NodeFilter.FilterResult.SKIP_CHILDREN) && (a2 = nodeFilter.b(node2, i)) == NodeFilter.FilterResult.STOP) {
                        return a2;
                    }
                    Node ae = node2.ae();
                    i--;
                    if (a2 == NodeFilter.FilterResult.REMOVE) {
                        node2.ah();
                    }
                    a2 = NodeFilter.FilterResult.CONTINUE;
                    node2 = ae;
                }
                if ((a2 == NodeFilter.FilterResult.CONTINUE || a2 == NodeFilter.FilterResult.SKIP_CHILDREN) && (a2 = nodeFilter.b(node2, i)) == NodeFilter.FilterResult.STOP) {
                    return a2;
                }
                if (node2 == node) {
                    return a2;
                }
                Node ak = node2.ak();
                if (a2 == NodeFilter.FilterResult.REMOVE) {
                    node2.ah();
                }
                node2 = ak;
            } else {
                node2 = node2.e(0);
                i++;
            }
        }
        return NodeFilter.FilterResult.CONTINUE;
    }

    /* JADX WARNING: Removed duplicated region for block: B:1:0x000a A[LOOP:0: B:1:0x000a->B:4:0x001c, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(org.jsoup.select.NodeFilter r2, org.jsoup.select.Elements r3) {
        /*
            org.jsoup.helper.Validate.a((java.lang.Object) r2)
            org.jsoup.helper.Validate.a((java.lang.Object) r3)
            java.util.Iterator r3 = r3.iterator()
        L_0x000a:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x001e
            java.lang.Object r0 = r3.next()
            org.jsoup.nodes.Element r0 = (org.jsoup.nodes.Element) r0
            org.jsoup.select.NodeFilter$FilterResult r0 = a((org.jsoup.select.NodeFilter) r2, (org.jsoup.nodes.Node) r0)
            org.jsoup.select.NodeFilter$FilterResult r1 = org.jsoup.select.NodeFilter.FilterResult.STOP
            if (r0 != r1) goto L_0x000a
        L_0x001e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.select.NodeTraversor.a(org.jsoup.select.NodeFilter, org.jsoup.select.Elements):void");
    }
}
