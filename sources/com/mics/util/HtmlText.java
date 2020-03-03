package com.mics.util;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.Html;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.util.Log;
import android.util.Pair;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.TextView;
import com.mics.core.MiCS;
import com.mics.widget.util.SmileyUtils;
import com.taobao.weex.adapter.URIAdapter;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xml.sax.XMLReader;

public class HtmlText {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7779a = "ypQuickAsk";
    public static final String b = "ypLeaveMsg";
    public static final String c = "ypShowHumanService";
    private static final String d = "HtmlText";
    private static final String e = "&lt;%s&gt;(.*)&lt;/%s&gt;";
    private static final String f = String.format(e, new Object[]{f7779a, f7779a});
    private static final String g = String.format(e, new Object[]{b, b});
    private static final String h = String.format(e, new Object[]{c, c});
    private static final String i = (Operators.BRACKET_START_STR + f + ")|(" + g + ")|(" + h + Operators.BRACKET_END_STR);
    private static final String j = "&lt;ypLink href=['|\"].+['|\"]&gt;([^&lt;]*?)&lt;/ypLink&gt;";
    private static final String k = "(&lt;br&gt;)|(&lt;font(.*)&gt;(.*?)[&lt;/font&gt;]*)";
    private static final String l = "(<br>)|(<font(.*)>(.*?)[</font>]*)";
    private static final String m = "<ypLink href=['|\"].+['|\"]>([<]*?)</ypLink>";
    private static final String n = "<%s>(.*)</%s>";
    private static final String o = ("(<ypLink href=['|\"].+['|\"]>([<]*?)</ypLink>)|(" + String.format(n, new Object[]{f7779a, f7779a}) + ")|(" + String.format(n, new Object[]{b, b}) + ")|(" + String.format(n, new Object[]{c, c}) + Operators.BRACKET_END_STR);

    public interface OnClickListener {
        void onClick(String str);

        void showHumanService(String str);

        void toLeaveMessage(String str);
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        Matcher matcher = Pattern.compile(o).matcher(str);
        while (matcher.find()) {
            String group = matcher.group();
            str = str.replace(group, group.replaceAll("yp", "&#121;&#112;"));
        }
        Matcher matcher2 = Pattern.compile(l).matcher(str);
        while (matcher2.find()) {
            String group2 = matcher2.group();
            str = str.replace(group2, group2.replaceAll("<br>", "<&#98;&#114;>").replaceAll(URIAdapter.FONT, "&#102;&#111;&#110;&#116;"));
        }
        return str;
    }

    public static Spanned a(Context context, String str) {
        return a(context, str, (OnClickListener) null);
    }

    public static Spanned a(Context context, String str, OnClickListener onClickListener) {
        Matcher matcher = Pattern.compile(i).matcher(str.replaceAll("\n", "<br>").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;"));
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, matcher.group().replace("&lt;", "<").replace("&gt;", ">"));
        }
        matcher.appendTail(stringBuffer);
        Matcher matcher2 = Pattern.compile(j).matcher(stringBuffer.toString());
        StringBuffer stringBuffer2 = new StringBuffer();
        while (matcher2.find()) {
            matcher2.appendReplacement(stringBuffer2, matcher2.group().replaceAll("ypLink", "a").replace("&lt;", "<").replace("&gt;", ">"));
        }
        matcher2.appendTail(stringBuffer2);
        Matcher matcher3 = Pattern.compile(k).matcher(stringBuffer2.toString());
        StringBuffer stringBuffer3 = new StringBuffer();
        while (matcher3.find()) {
            matcher3.appendReplacement(stringBuffer3, matcher3.group().replace("&lt;", "<").replace("&gt;", ">"));
        }
        matcher3.appendTail(stringBuffer3);
        Spanned fromHtml = Html.fromHtml(stringBuffer3.toString(), (Html.ImageGetter) null, new HtmlTagHandler(onClickListener));
        URLSpan[] uRLSpanArr = (URLSpan[]) fromHtml.getSpans(0, fromHtml.length(), URLSpan.class);
        ArrayList arrayList = new ArrayList();
        for (URLSpan uRLSpan : uRLSpanArr) {
            arrayList.add(new Pair(Integer.valueOf(fromHtml.getSpanStart(uRLSpan)), Integer.valueOf(fromHtml.getSpanEnd(uRLSpan))));
        }
        if (fromHtml instanceof Spannable) {
            try {
                Linkify.addLinks((Spannable) fromHtml, 1);
            } catch (NullPointerException e2) {
                Log.d(d, "addLink failed, the system error.", e2);
            }
            for (int i2 = 0; i2 < uRLSpanArr.length; i2++) {
                ((Spannable) fromHtml).setSpan(uRLSpanArr[i2], ((Integer) ((Pair) arrayList.get(i2)).first).intValue(), ((Integer) ((Pair) arrayList.get(i2)).second).intValue(), 33);
            }
        }
        URLSpan[] uRLSpanArr2 = (URLSpan[]) fromHtml.getSpans(0, fromHtml.length(), URLSpan.class);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(fromHtml);
        for (URLSpan uRLSpan2 : uRLSpanArr2) {
            int spanStart = fromHtml.getSpanStart(uRLSpan2);
            int spanEnd = fromHtml.getSpanEnd(uRLSpan2);
            String url = uRLSpan2.getURL();
            if (URLUtil.isNetworkUrl(url)) {
                spannableStringBuilder.removeSpan(uRLSpan2);
                spannableStringBuilder.setSpan(new CustomURLSpan(url), spanStart, spanEnd, 33);
            }
        }
        SmileyUtils.a(context, (Spannable) spannableStringBuilder);
        return spannableStringBuilder;
    }

    private static class CustomURLSpan extends URLSpan {

        /* renamed from: a  reason: collision with root package name */
        private String f7780a;

        CustomURLSpan(String str) {
            super(str);
            this.f7780a = str;
        }

        public void onClick(View view) {
            MiCS.a().a(this.f7780a);
        }

        public void updateDrawState(TextPaint textPaint) {
            textPaint.setUnderlineText(false);
            textPaint.setColor(textPaint.linkColor);
        }
    }

    public static class MovementCheck extends LinkMovementMethod {

        /* renamed from: a  reason: collision with root package name */
        private GestureDetector f7786a = new GestureDetector((Context) null, this.b);
        private LongPress b = new LongPress();

        public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent motionEvent) {
            try {
                this.b.a(textView, spannable);
                if (!this.f7786a.onTouchEvent(motionEvent) || super.onTouchEvent(textView, spannable, motionEvent)) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                return true;
            }
        }
    }

    private static class LongPress extends GestureDetector.SimpleOnGestureListener {

        /* renamed from: a  reason: collision with root package name */
        private BackgroundColorSpan f7785a = new BackgroundColorSpan(0);
        private BackgroundColorSpan b = new BackgroundColorSpan(Color.parseColor("#20000000"));
        private ClickableSpan c;
        private TextView d;
        private Spannable e;

        LongPress() {
        }

        public void a(TextView textView, Spannable spannable) {
            this.d = textView;
            this.e = spannable;
        }

        public boolean onDown(MotionEvent motionEvent) {
            a(motionEvent);
            return super.onDown(motionEvent);
        }

        public void onLongPress(MotionEvent motionEvent) {
            b(motionEvent);
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            b(motionEvent);
            return true;
        }

        private void a(MotionEvent motionEvent) {
            this.c = a(this.d, this.e, motionEvent);
            if (this.c != null) {
                this.e.removeSpan(this.b);
                this.e.setSpan(this.b, this.e.getSpanStart(this.c), this.e.getSpanEnd(this.c), 33);
                Selection.setSelection(this.e, this.e.getSpanStart(this.c), this.e.getSpanEnd(this.c));
            }
        }

        private void b(MotionEvent motionEvent) {
            this.c = a(this.d, this.e, motionEvent);
            if (this.c != null) {
                this.e.setSpan(this.f7785a, this.e.getSpanStart(this.c), this.e.getSpanEnd(this.c), 33);
                Selection.setSelection(this.e, this.e.getSpanStart(this.c), this.e.getSpanEnd(this.c));
            }
            this.e.removeSpan(this.b);
            this.e.removeSpan(this.f7785a);
            Selection.removeSelection(this.e);
        }

        private ClickableSpan a(TextView textView, Spannable spannable, MotionEvent motionEvent) {
            int x = ((int) motionEvent.getX()) - textView.getTotalPaddingLeft();
            int y = ((int) motionEvent.getY()) - textView.getTotalPaddingTop();
            int scrollX = x + textView.getScrollX();
            Layout layout = textView.getLayout();
            int offsetForHorizontal = layout.getOffsetForHorizontal(layout.getLineForVertical(y + textView.getScrollY()), (float) scrollX);
            ClickableSpan[] clickableSpanArr = (ClickableSpan[]) spannable.getSpans(offsetForHorizontal, offsetForHorizontal, ClickableSpan.class);
            if (clickableSpanArr.length > 0) {
                return clickableSpanArr[0];
            }
            return null;
        }
    }

    public static class HtmlTagHandler implements Html.TagHandler {

        /* renamed from: a  reason: collision with root package name */
        private int f7781a;
        private int b;
        private OnClickListener c;

        HtmlTagHandler(OnClickListener onClickListener) {
            this.c = onClickListener;
        }

        public void handleTag(boolean z, String str, Editable editable, XMLReader xMLReader) {
            if (str.equalsIgnoreCase(HtmlText.f7779a)) {
                if (z) {
                    this.f7781a = editable.length();
                    return;
                }
                this.b = editable.length();
                editable.setSpan(new MiTagClick(this.c, editable.subSequence(this.f7781a, this.b).toString()), this.f7781a, this.b, 33);
            } else if (str.equalsIgnoreCase(HtmlText.b)) {
                if (z) {
                    this.f7781a = editable.length();
                    return;
                }
                this.b = editable.length();
                editable.setSpan(new ActionTagClick(this.c, editable.subSequence(this.f7781a, this.b).toString()), this.f7781a, this.b, 33);
            } else if (!str.equalsIgnoreCase(HtmlText.c)) {
            } else {
                if (z) {
                    this.f7781a = editable.length();
                    return;
                }
                this.b = editable.length();
                editable.setSpan(new ServiceTagClick(this.c, editable.subSequence(this.f7781a, this.b).toString()), this.f7781a, this.b, 33);
            }
        }

        private class MiTagClick extends URLSpan {
            private OnClickListener b;
            private String c;

            MiTagClick(OnClickListener onClickListener, String str) {
                super(str);
                this.b = onClickListener;
                this.c = str;
            }

            public void onClick(View view) {
                if (this.b != null) {
                    this.b.onClick(this.c);
                }
            }

            public void updateDrawState(TextPaint textPaint) {
                textPaint.setUnderlineText(false);
                textPaint.setColor(textPaint.linkColor);
            }
        }

        private class ActionTagClick extends URLSpan {
            private OnClickListener b;
            private String c;

            ActionTagClick(OnClickListener onClickListener, String str) {
                super(str);
                this.b = onClickListener;
                this.c = str;
            }

            public void onClick(View view) {
                if (this.b != null) {
                    this.b.toLeaveMessage(this.c);
                }
            }

            public void updateDrawState(TextPaint textPaint) {
                textPaint.setUnderlineText(false);
                textPaint.setColor(textPaint.linkColor);
            }
        }

        private class ServiceTagClick extends URLSpan {
            private OnClickListener b;
            private String c;

            ServiceTagClick(OnClickListener onClickListener, String str) {
                super(str);
                this.b = onClickListener;
                this.c = str;
            }

            public void onClick(View view) {
                if (this.b != null) {
                    this.b.showHumanService(this.c);
                }
            }

            public void updateDrawState(TextPaint textPaint) {
                textPaint.setUnderlineText(false);
                textPaint.setColor(textPaint.linkColor);
            }
        }
    }
}
