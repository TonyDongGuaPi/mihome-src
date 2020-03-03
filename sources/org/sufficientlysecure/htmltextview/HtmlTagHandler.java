package org.sufficientlysecure.htmltextview;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.Html;
import android.text.Layout;
import android.text.TextPaint;
import android.text.style.AlignmentSpan;
import android.text.style.BulletSpan;
import android.text.style.LeadingMarginSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.TypefaceSpan;
import com.xiaomi.miot.support.monitor.core.BaseInfo;
import java.util.Stack;
import org.xml.sax.XMLReader;

public class HtmlTagHandler implements Html.TagHandler {

    /* renamed from: a  reason: collision with root package name */
    public static final String f4189a = "HTML_TEXTVIEW_ESCAPED_UL_TAG";
    public static final String b = "HTML_TEXTVIEW_ESCAPED_OL_TAG";
    public static final String c = "HTML_TEXTVIEW_ESCAPED_LI_TAG";
    private static final int i = 10;
    private static final int j = 20;
    private static final BulletSpan k = new BulletSpan(10);
    Stack<String> d = new Stack<>();
    Stack<Integer> e = new Stack<>();
    StringBuilder f = new StringBuilder();
    int g = 0;
    private final TextPaint h;
    private ClickableTableSpan l;
    private DrawTableLinkSpan m;

    public HtmlTagHandler(TextPaint textPaint) {
        this.h = textPaint;
    }

    /* access modifiers changed from: package-private */
    public String a(@Nullable String str) {
        if (str == null) {
            return null;
        }
        return str.replace("<ul", "<HTML_TEXTVIEW_ESCAPED_UL_TAG").replace("</ul>", "</HTML_TEXTVIEW_ESCAPED_UL_TAG>").replace("<ol", "<HTML_TEXTVIEW_ESCAPED_OL_TAG").replace("</ol>", "</HTML_TEXTVIEW_ESCAPED_OL_TAG>").replace("<li", "<HTML_TEXTVIEW_ESCAPED_LI_TAG").replace("</li>", "</HTML_TEXTVIEW_ESCAPED_LI_TAG>");
    }

    private static class Ul {
        private Ul() {
        }
    }

    private static class Ol {
        private Ol() {
        }
    }

    private static class Code {
        private Code() {
        }
    }

    private static class Center {
        private Center() {
        }
    }

    private static class Strike {
        private Strike() {
        }
    }

    private static class Table {
        private Table() {
        }
    }

    private static class Tr {
        private Tr() {
        }
    }

    private static class Th {
        private Th() {
        }
    }

    private static class Td {
        private Td() {
        }
    }

    public void handleTag(boolean z, String str, Editable editable, XMLReader xMLReader) {
        ClickableTableSpan clickableTableSpan;
        int i2 = 10;
        DrawTableLinkSpan drawTableLinkSpan = null;
        if (z) {
            if (str.equalsIgnoreCase(f4189a)) {
                this.d.push(str);
            } else if (str.equalsIgnoreCase(b)) {
                this.d.push(str);
                this.e.push(1);
            } else if (str.equalsIgnoreCase(c)) {
                if (editable.length() > 0 && editable.charAt(editable.length() - 1) != 10) {
                    editable.append("\n");
                }
                if (!this.d.isEmpty()) {
                    String peek = this.d.peek();
                    if (peek.equalsIgnoreCase(b)) {
                        a(editable, (Object) new Ol());
                        this.e.push(Integer.valueOf(this.e.pop().intValue() + 1));
                    } else if (peek.equalsIgnoreCase(f4189a)) {
                        a(editable, (Object) new Ul());
                    }
                }
            } else if (str.equalsIgnoreCase("code")) {
                a(editable, (Object) new Code());
            } else if (str.equalsIgnoreCase("center")) {
                a(editable, (Object) new Center());
            } else if (str.equalsIgnoreCase("s") || str.equalsIgnoreCase("strike")) {
                a(editable, (Object) new Strike());
            } else if (str.equalsIgnoreCase("table")) {
                a(editable, (Object) new Table());
                if (this.g == 0) {
                    this.f = new StringBuilder();
                    editable.append("table placeholder");
                }
                this.g++;
            } else if (str.equalsIgnoreCase(BaseInfo.KEY_TIME_RECORD)) {
                a(editable, (Object) new Tr());
            } else if (str.equalsIgnoreCase("th")) {
                a(editable, (Object) new Th());
            } else if (str.equalsIgnoreCase("td")) {
                a(editable, (Object) new Td());
            }
        } else if (str.equalsIgnoreCase(f4189a)) {
            this.d.pop();
        } else if (str.equalsIgnoreCase(b)) {
            this.d.pop();
            this.e.pop();
        } else if (str.equalsIgnoreCase(c)) {
            if (!this.d.isEmpty()) {
                if (this.d.peek().equalsIgnoreCase(f4189a)) {
                    if (editable.length() > 0 && editable.charAt(editable.length() - 1) != 10) {
                        editable.append("\n");
                    }
                    if (this.d.size() > 1) {
                        i2 = 10 - k.getLeadingMargin(true);
                        if (this.d.size() > 2) {
                            i2 -= (this.d.size() - 2) * 20;
                        }
                    }
                    a(editable, Ul.class, false, new LeadingMarginSpan.Standard((this.d.size() - 1) * 20), new BulletSpan(i2));
                } else if (this.d.peek().equalsIgnoreCase(b)) {
                    if (editable.length() > 0 && editable.charAt(editable.length() - 1) != 10) {
                        editable.append("\n");
                    }
                    int size = (this.d.size() - 1) * 20;
                    if (this.d.size() > 2) {
                        size -= (this.d.size() - 2) * 20;
                    }
                    a(editable, Ol.class, false, new LeadingMarginSpan.Standard(size), new NumberSpan(this.h, ((Integer) this.e.lastElement()).intValue() - 1));
                }
            }
        } else if (str.equalsIgnoreCase("code")) {
            a(editable, Code.class, false, new TypefaceSpan("monospace"));
        } else if (str.equalsIgnoreCase("center")) {
            a(editable, Center.class, true, new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER));
        } else if (str.equalsIgnoreCase("s") || str.equalsIgnoreCase("strike")) {
            a(editable, Strike.class, false, new StrikethroughSpan());
        } else if (str.equalsIgnoreCase("table")) {
            this.g--;
            if (this.g == 0) {
                String sb = this.f.toString();
                if (this.l != null) {
                    clickableTableSpan = this.l.a();
                    clickableTableSpan.a(sb);
                } else {
                    clickableTableSpan = null;
                }
                if (this.m != null) {
                    drawTableLinkSpan = this.m.a();
                }
                a(editable, Table.class, false, drawTableLinkSpan, clickableTableSpan);
            } else {
                a(editable, Table.class, false, new Object[0]);
            }
        } else if (str.equalsIgnoreCase(BaseInfo.KEY_TIME_RECORD)) {
            a(editable, Tr.class, false, new Object[0]);
        } else if (str.equalsIgnoreCase("th")) {
            a(editable, Th.class, false, new Object[0]);
        } else if (str.equalsIgnoreCase("td")) {
            a(editable, Td.class, false, new Object[0]);
        }
        a(z, str);
    }

    private void a(boolean z, String str) {
        if (this.g > 0 || str.equalsIgnoreCase("table")) {
            this.f.append("<");
            if (!z) {
                this.f.append("/");
            }
            StringBuilder sb = this.f;
            sb.append(str.toLowerCase());
            sb.append(">");
        }
    }

    private void a(Editable editable, Object obj) {
        int length = editable.length();
        editable.setSpan(obj, length, length, 17);
    }

    private void a(Editable editable, Class cls, boolean z, Object... objArr) {
        Object b2 = b(editable, cls);
        int spanStart = editable.getSpanStart(b2);
        int length = editable.length();
        if (this.g > 0) {
            this.f.append(a(editable, cls));
        }
        editable.removeSpan(b2);
        if (spanStart != length) {
            if (z) {
                editable.append("\n");
                length++;
            }
            for (Object span : objArr) {
                editable.setSpan(span, spanStart, length, 33);
            }
        }
    }

    private CharSequence a(Editable editable, Class cls) {
        int spanStart = editable.getSpanStart(b(editable, cls));
        int length = editable.length();
        CharSequence subSequence = editable.subSequence(spanStart, length);
        editable.delete(spanStart, length);
        return subSequence;
    }

    private static Object b(Editable editable, Class cls) {
        Object[] spans = editable.getSpans(0, editable.length(), cls);
        if (spans.length == 0) {
            return null;
        }
        for (int length = spans.length; length > 0; length--) {
            int i2 = length - 1;
            if (editable.getSpanFlags(spans[i2]) == 17) {
                return spans[i2];
            }
        }
        return null;
    }

    public void a(ClickableTableSpan clickableTableSpan) {
        this.l = clickableTableSpan;
    }

    public void a(DrawTableLinkSpan drawTableLinkSpan) {
        this.m = drawTableLinkSpan;
    }
}
