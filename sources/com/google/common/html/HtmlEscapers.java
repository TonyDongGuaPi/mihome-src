package com.google.common.html;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.escape.Escaper;
import com.google.common.escape.Escapers;
import com.taobao.weex.el.parse.Operators;
import kotlin.text.Typography;

@GwtCompatible
@Beta
public final class HtmlEscapers {
    private static final Escaper HTML_ESCAPER = Escapers.builder().addEscape('\"', "&quot;").addEscape(Operators.SINGLE_QUOTE, "&#39;").addEscape(Typography.c, "&amp;").addEscape(Typography.d, "&lt;").addEscape(Typography.e, "&gt;").build();

    public static Escaper htmlEscaper() {
        return HTML_ESCAPER;
    }

    private HtmlEscapers() {
    }
}
