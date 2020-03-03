package com.xiaomi.jr.richtext;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;
import com.xiaomi.jr.common.utils.Constants;
import com.xiaomi.jr.common.utils.DensityUtils;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.MiuiImageUtils;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class RichTextRender {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11022a = "RichTextRender";
    private static final int b = R.id.rich_text_render;
    private Context c;
    private TextView d;
    /* access modifiers changed from: private */
    public String e;
    private ImageLoadTarget f;

    private interface ImageLoadTarget extends Target {
        Bitmap a();

        Picasso.LoadedFrom b();

        void c();
    }

    public static RichTextRender a(TextView textView) {
        if (textView != null) {
            return b(textView);
        }
        throw new NullPointerException();
    }

    private static RichTextRender b(TextView textView) {
        RichTextRender richTextRender = (RichTextRender) textView.getTag(b);
        if (richTextRender != null) {
            return richTextRender;
        }
        RichTextRender richTextRender2 = new RichTextRender();
        richTextRender2.d = textView;
        richTextRender2.c = textView.getContext().getApplicationContext();
        textView.setTag(b, richTextRender2);
        return richTextRender2;
    }

    public static void a(TextView textView, String str) {
        if (c(str)) {
            a(textView).a(textView.getContext()).a(str);
        } else {
            textView.setText(str);
        }
    }

    public RichTextRender a(String str) {
        this.e = str;
        if (this.d != null) {
            if (this.f != null) {
                this.f.c();
                this.f = null;
            }
            b(this.e);
        }
        return this;
    }

    public RichTextRender a(Context context) {
        this.c = context;
        return this;
    }

    private static boolean c(String str) {
        return str != null && (str.contains("<txt") || str.contains("<img"));
    }

    public void b(String str) {
        this.d.setText(d("<root>" + str + "</root>"));
    }

    private SpannableStringBuilder d(String str) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        try {
            NodeList childNodes = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(str))).getDocumentElement().getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node item = childNodes.item(i);
                if (item.getNodeType() == 1) {
                    NamedNodeMap attributes = item.getAttributes();
                    String nodeName = item.getNodeName();
                    String str2 = null;
                    if ("txt".equals(nodeName)) {
                        Node namedItem = attributes.getNamedItem("size");
                        String nodeValue = namedItem != null ? namedItem.getNodeValue() : null;
                        Node namedItem2 = attributes.getNamedItem("color");
                        String nodeValue2 = namedItem2 != null ? namedItem2.getNodeValue() : null;
                        Node namedItem3 = attributes.getNamedItem("style");
                        String nodeValue3 = namedItem3 != null ? namedItem3.getNodeValue() : null;
                        Node namedItem4 = attributes.getNamedItem("bg");
                        String nodeValue4 = namedItem4 != null ? namedItem4.getNodeValue() : null;
                        Node namedItem5 = attributes.getNamedItem("alignSize");
                        String nodeValue5 = namedItem5 != null ? namedItem5.getNodeValue() : null;
                        Node namedItem6 = attributes.getNamedItem("href");
                        if (namedItem6 != null) {
                            str2 = namedItem6.getNodeValue();
                        }
                        spannableStringBuilder.append(a(item.getTextContent(), nodeValue, nodeValue2, nodeValue3, nodeValue4, nodeValue5, str2));
                    } else if ("img".equals(nodeName)) {
                        Node namedItem7 = attributes.getNamedItem("src");
                        String nodeValue6 = namedItem7 != null ? namedItem7.getNodeValue() : null;
                        Node namedItem8 = attributes.getNamedItem("h");
                        String nodeValue7 = namedItem8 != null ? namedItem8.getNodeValue() : null;
                        Node namedItem9 = attributes.getNamedItem("alignSize");
                        if (namedItem9 != null) {
                            str2 = namedItem9.getNodeValue();
                        }
                        Spannable a2 = a(nodeValue6, nodeValue7, str2);
                        if (a2 != null) {
                            spannableStringBuilder.append(a2);
                        }
                    }
                } else if (item.getNodeType() == 3) {
                    item.getNodeValue();
                    spannableStringBuilder.append(a(item.getTextContent(), (String) null, (String) null, (String) null, (String) null, (String) null, (String) null));
                }
            }
        } catch (Exception e2) {
            MifiLog.d(f11022a, "exception on getSpanned: " + e2.toString());
        }
        return spannableStringBuilder;
    }

    private Spannable a(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        String str8 = str2;
        String str9 = str3;
        String str10 = str4;
        String str11 = str5;
        String str12 = str7;
        float a2 = a(str2, this.d.getTextSize());
        int a3 = a(str3, this.d.getCurrentTextColor());
        int b2 = b(str10, this.d.getTypeface() != null ? this.d.getTypeface().getStyle() : 0);
        if (str11 != null) {
            Bitmap a4 = a(str, a2, a3, b2, a(str11, 0));
            SpannableString spannableString = new SpannableString("stub");
            BitmapDrawable bitmapDrawable = new BitmapDrawable(this.c.getResources(), a4);
            bitmapDrawable.setBounds(0, 0, bitmapDrawable.getIntrinsicWidth(), bitmapDrawable.getIntrinsicHeight());
            spannableString.setSpan(new CenteredImageSpan(bitmapDrawable, a(str6, a2)), 0, spannableString.length(), 33);
            spannableString.setSpan(new AbsoluteSizeSpan(bitmapDrawable.getBounds().height()), 0, spannableString.length(), 33);
            return spannableString;
        }
        String str13 = str;
        SpannableString spannableString2 = new SpannableString(str);
        if (str12 != null) {
            spannableString2.setSpan(new NoUnderlineURLSpan(str12), 0, spannableString2.length(), 33);
            this.d.setMovementMethod(LinkMovementMethod.getInstance());
            if (str9 == null) {
                str9 = "#0000ff";
                a3 = Color.parseColor(str9);
            }
        }
        if (str8 != null) {
            spannableString2.setSpan(new AbsoluteSizeSpan((int) a2), 0, spannableString2.length(), 33);
        }
        if (str9 != null) {
            spannableString2.setSpan(new ForegroundColorSpan(a3), 0, spannableString2.length(), 33);
        }
        if (str10 != null) {
            spannableString2.setSpan(new StyleSpan(b2), 0, spannableString2.length(), 33);
        }
        return spannableString2;
    }

    private Bitmap a(String str, float f2, int i, int i2, int i3) {
        Paint paint = new Paint();
        paint.setTextSize(f2);
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        float f3 = f2 / 3.0f;
        float f4 = 2.0f * f3;
        int i4 = (int) f4;
        Bitmap createBitmap = Bitmap.createBitmap(rect.width() + i4, rect.height() + i4, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        paint.setColor(i3);
        paint.setStyle(Paint.Style.FILL);
        float f5 = f2 / 4.0f;
        canvas.drawRoundRect(new RectF(0.0f, 0.0f, ((float) rect.width()) + f4, ((float) rect.height()) + f4), f5, f5, paint);
        paint.setColor(i);
        paint.setTypeface(Typeface.create(this.d.getTypeface(), i2));
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(str, ((float) (rect.width() / 2)) + f3, ((float) (rect.height() - rect.bottom)) + f3, paint);
        return createBitmap;
    }

    private Spannable a(final String str, String str2, String str3) {
        Drawable drawable;
        Uri parse = Uri.parse(str);
        int a2 = (int) a(str2, this.d.getTextSize());
        float f2 = (float) a2;
        float a3 = a(str3, f2);
        if (Constants.h.equals(parse.getScheme())) {
            drawable = this.c.getResources().getDrawable(this.c.getResources().getIdentifier(parse.getPath().substring(1), "drawable", this.c.getPackageName()));
        } else {
            if (Constants.i.equals(parse.getScheme())) {
                parse = Uri.parse(MiuiImageUtils.b(str.substring("miuifile://".length()), a2));
            }
            RequestCreator load = Picasso.get().load(parse);
            AnonymousClass1 r2 = new ImageLoadTarget() {
                private Bitmap c;
                private Picasso.LoadedFrom d;
                private boolean e;

                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
                    if (!this.e) {
                        this.c = bitmap;
                        this.d = loadedFrom;
                        if (loadedFrom != Picasso.LoadedFrom.MEMORY) {
                            RichTextRender.this.b(RichTextRender.this.e);
                        }
                    }
                }

                public void onBitmapFailed(Exception exc, Drawable drawable) {
                    if (!this.e) {
                        MifiLog.d(RichTextRender.f11022a, "load image " + str + " fail");
                    }
                }

                public void onPrepareLoad(Drawable drawable) {
                    if (!this.e) {
                        MifiLog.d(RichTextRender.f11022a, "load image " + str + " fail");
                    }
                }

                public Bitmap a() {
                    return this.c;
                }

                public Picasso.LoadedFrom b() {
                    return this.d;
                }

                public void c() {
                    this.e = true;
                }
            };
            this.f = r2;
            load.into((Target) r2);
            if (this.f.b() != Picasso.LoadedFrom.MEMORY) {
                return null;
            }
            drawable = new BitmapDrawable(this.c.getResources(), this.f.a());
        }
        SpannableString spannableString = new SpannableString("stub");
        drawable.setBounds(0, 0, (int) (((((float) drawable.getIntrinsicWidth()) * 1.0f) / ((float) drawable.getIntrinsicHeight())) * f2), a2);
        spannableString.setSpan(new CenteredImageSpan(drawable, a3), 0, spannableString.length(), 33);
        spannableString.setSpan(new AbsoluteSizeSpan(drawable.getBounds().height()), 0, spannableString.length(), 33);
        return spannableString;
    }

    private float a(String str, float f2) {
        if (str == null) {
            return f2;
        }
        try {
            return (float) DensityUtils.a(this.c, (float) Integer.parseInt(str));
        } catch (NumberFormatException unused) {
            MifiLog.d(f11022a, "attr format error: " + str);
            return f2;
        }
    }

    private int a(String str, int i) {
        if (str == null) {
            return i;
        }
        try {
            return Color.parseColor(str);
        } catch (IllegalArgumentException unused) {
            MifiLog.d(f11022a, "attr format error: " + str);
            return i;
        }
    }

    private int b(String str, int i) {
        if (str == null) {
            return i;
        }
        if (TextUtils.equals(str, "bold")) {
            return 1;
        }
        if (TextUtils.equals(str, "italic")) {
            return 2;
        }
        if (TextUtils.equals(str, "bold_italic")) {
            return 3;
        }
        return i;
    }
}
