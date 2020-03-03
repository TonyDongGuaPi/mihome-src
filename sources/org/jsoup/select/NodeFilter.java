package org.jsoup.select;

import org.jsoup.nodes.Node;

public interface NodeFilter {

    public enum FilterResult {
        CONTINUE,
        SKIP_CHILDREN,
        SKIP_ENTIRELY,
        REMOVE,
        STOP
    }

    FilterResult a(Node node, int i);

    FilterResult b(Node node, int i);
}
