package com.xiaomi.shopviews.widget.homestarautofill;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.http.SslError;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.base.utils.ScreenInfo;
import com.xiaomi.shopviews.model.HomeSection;
import com.xiaomi.shopviews.model.HomeSectionClickListener;
import com.xiaomi.shopviews.model.HomeSectionItem;
import com.xiaomi.shopviews.model.IHomeItemView;
import java.util.Iterator;

public class HomeStarAutoFillItem extends RelativeLayout implements IHomeItemView {
    public static final String ITEMTYPE_IMAGE = "image";
    public static final String ITEMTYPE_TEXT = "textview";
    public static final String ITEMTYPE_WEB = "webview";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public HomeSectionClickListener f13331a;

    private void a(Context context, AttributeSet attributeSet, int i) {
    }

    /* access modifiers changed from: private */
    public void a(HomeSectionItem homeSectionItem) {
    }

    public HomeStarAutoFillItem(Context context) {
        super(context);
        a(context, (AttributeSet) null, 0);
    }

    public HomeStarAutoFillItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet, 0);
    }

    public HomeStarAutoFillItem(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet, i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0115 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void bind(com.xiaomi.shopviews.model.HomeSection r8, int r9, int r10) {
        /*
            r7 = this;
            com.xiaomi.shopviews.model.HomeSectionBody r9 = r8.mBody
            if (r9 == 0) goto L_0x0119
            int r9 = r7.getChildCount()
            if (r9 <= 0) goto L_0x0119
            com.xiaomi.shopviews.model.HomeSectionBody r8 = r8.mBody
            java.util.ArrayList<com.xiaomi.shopviews.model.HomeSectionItem> r8 = r8.mItems
            if (r8 == 0) goto L_0x0119
            int r9 = r8.size()
            if (r9 <= 0) goto L_0x0119
            r9 = 0
            r10 = 0
        L_0x0018:
            int r0 = r8.size()
            if (r10 >= r0) goto L_0x0119
            android.view.View r0 = r7.getChildAt(r10)
            java.lang.Object r1 = r8.get(r10)
            com.xiaomi.shopviews.model.HomeSectionItem r1 = (com.xiaomi.shopviews.model.HomeSectionItem) r1
            java.lang.String r2 = r1.mType
            r3 = -1
            int r4 = r2.hashCode()
            r5 = -1002626734(0xffffffffc43d2152, float:-756.5206)
            r6 = 1
            if (r4 == r5) goto L_0x0054
            r5 = 100313435(0x5faa95b, float:2.3572098E-35)
            if (r4 == r5) goto L_0x004a
            r5 = 1224424441(0x48fb3bf9, float:514527.78)
            if (r4 == r5) goto L_0x0040
            goto L_0x005e
        L_0x0040:
            java.lang.String r4 = "webview"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x005e
            r2 = 0
            goto L_0x005f
        L_0x004a:
            java.lang.String r4 = "image"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x005e
            r2 = 2
            goto L_0x005f
        L_0x0054:
            java.lang.String r4 = "textview"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x005e
            r2 = 1
            goto L_0x005f
        L_0x005e:
            r2 = -1
        L_0x005f:
            switch(r2) {
                case 0: goto L_0x0100;
                case 1: goto L_0x0082;
                case 2: goto L_0x0064;
                default: goto L_0x0062;
            }
        L_0x0062:
            goto L_0x0115
        L_0x0064:
            boolean r2 = r0 instanceof android.widget.ImageView
            if (r2 == 0) goto L_0x0115
            android.widget.ImageView r0 = (android.widget.ImageView) r0
            r0.setClickable(r6)
            if (r1 == 0) goto L_0x0115
            com.xiaomi.base.imageloader.ImageLoaderInterface r2 = com.xiaomi.base.imageloader.ImageLoader.a()
            java.lang.String r3 = r1.mImageUrl
            r2.a((java.lang.String) r3, (android.widget.ImageView) r0)
            com.xiaomi.shopviews.widget.homestarautofill.HomeStarAutoFillItem$2 r2 = new com.xiaomi.shopviews.widget.homestarautofill.HomeStarAutoFillItem$2
            r2.<init>(r1)
            r0.setOnClickListener(r2)
            goto L_0x0115
        L_0x0082:
            boolean r2 = r0 instanceof android.widget.TextView
            if (r2 == 0) goto L_0x0115
            android.widget.TextView r0 = (android.widget.TextView) r0
            float r2 = r1.mTextSize
            r3 = 0
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 <= 0) goto L_0x0094
            float r2 = r1.mTextSize
            r0.setTextSize(r2)
        L_0x0094:
            java.lang.String r2 = r1.mTextColor
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L_0x00a5
            java.lang.String r2 = r1.mTextColor
            int r2 = android.graphics.Color.parseColor(r2)
            r0.setTextColor(r2)
        L_0x00a5:
            int r2 = r1.mTextLines
            if (r2 <= 0) goto L_0x00ae
            int r2 = r1.mTextLines
            r0.setLines(r2)
        L_0x00ae:
            boolean r2 = r1.mTextBold
            if (r2 == 0) goto L_0x00ba
            android.text.TextPaint r2 = r0.getPaint()
            r2.setFakeBoldText(r6)
            goto L_0x00c1
        L_0x00ba:
            android.text.TextPaint r2 = r0.getPaint()
            r2.setFakeBoldText(r9)
        L_0x00c1:
            java.lang.String r2 = r1.mTextAlign
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            r3 = 3
            if (r2 != 0) goto L_0x00f7
            java.lang.String r2 = r1.mTextAlign
            java.lang.String r4 = "center"
            boolean r2 = r2.equalsIgnoreCase(r4)
            if (r2 == 0) goto L_0x00da
            r2 = 17
            r0.setGravity(r2)
            goto L_0x00fa
        L_0x00da:
            java.lang.String r2 = r1.mTextAlign
            java.lang.String r4 = "left"
            boolean r2 = r2.equalsIgnoreCase(r4)
            if (r2 == 0) goto L_0x00e8
            r0.setGravity(r3)
            goto L_0x00fa
        L_0x00e8:
            java.lang.String r2 = r1.mTextAlign
            java.lang.String r3 = "right"
            boolean r2 = r2.equalsIgnoreCase(r3)
            if (r2 == 0) goto L_0x00fa
            r2 = 5
            r0.setGravity(r2)
            goto L_0x00fa
        L_0x00f7:
            r0.setGravity(r3)
        L_0x00fa:
            java.lang.String r1 = r1.mText
            r0.setText(r1)
            goto L_0x0115
        L_0x0100:
            boolean r2 = r0 instanceof android.webkit.WebView
            if (r2 == 0) goto L_0x0115
            android.webkit.WebView r0 = (android.webkit.WebView) r0
            java.lang.String r2 = r1.mUrl
            r0.loadUrl(r2)
            if (r1 == 0) goto L_0x0115
            com.xiaomi.shopviews.widget.homestarautofill.HomeStarAutoFillItem$1 r2 = new com.xiaomi.shopviews.widget.homestarautofill.HomeStarAutoFillItem$1
            r2.<init>(r1)
            r0.setOnTouchListener(r2)
        L_0x0115:
            int r10 = r10 + 1
            goto L_0x0018
        L_0x0119:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.shopviews.widget.homestarautofill.HomeStarAutoFillItem.bind(com.xiaomi.shopviews.model.HomeSection, int, int):void");
    }

    public void draw(HomeSection homeSection) {
        int measuredWidth = getMeasuredWidth();
        if (measuredWidth == 0) {
            measuredWidth = ScreenInfo.a().e();
        }
        double d = (double) measuredWidth;
        Double.isNaN(d);
        double d2 = d * 1.0d;
        double d3 = (double) (homeSection.mBody.mWidth == 0 ? measuredWidth : homeSection.mBody.mWidth);
        Double.isNaN(d3);
        double d4 = d2 / d3;
        double d5 = (double) homeSection.mBody.mHeight;
        Double.isNaN(d5);
        setLayoutParams(new AbsListView.LayoutParams(measuredWidth, (int) (d5 * d4)));
        if (!TextUtils.isEmpty(homeSection.mBody.mBgColor)) {
            int parseColor = Color.parseColor(homeSection.mBody.mBgColor);
            if (!(getBackground() == null || ((ColorDrawable) getBackground()).getColor() == parseColor)) {
                setBackgroundColor(parseColor);
            }
        }
        Iterator<HomeSectionItem> it = homeSection.mBody.mItems.iterator();
        while (it.hasNext()) {
            HomeSectionItem next = it.next();
            double d6 = (double) next.mWidth;
            Double.isNaN(d6);
            double d7 = (double) next.mHeight;
            Double.isNaN(d7);
            int i = (int) (d7 * d4);
            double d8 = (double) next.mX;
            Double.isNaN(d8);
            double d9 = (double) next.mY;
            Double.isNaN(d9);
            String lowerCase = next.mType.toLowerCase();
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (d6 * d4), i);
            layoutParams.leftMargin = (int) (d8 * d4);
            layoutParams.topMargin = (int) (d9 * d4);
            char c = 65535;
            int hashCode = lowerCase.hashCode();
            if (hashCode != -1002626734) {
                if (hashCode != 100313435) {
                    if (hashCode == 1224424441 && lowerCase.equals("webview")) {
                        c = 0;
                    }
                } else if (lowerCase.equals("image")) {
                    c = 2;
                }
            } else if (lowerCase.equals(ITEMTYPE_TEXT)) {
                c = 1;
            }
            switch (c) {
                case 0:
                    WebView webView = new WebView(getContext());
                    webView.setVerticalScrollBarEnabled(true);
                    webView.setWebViewClient(new MyWebViewClient());
                    webView.setLayoutParams(layoutParams);
                    WebSettings settings = webView.getSettings();
                    settings.setJavaScriptEnabled(true);
                    settings.setUseWideViewPort(true);
                    settings.setLoadWithOverviewMode(true);
                    addView(webView);
                    break;
                case 1:
                    TextView textView = new TextView(getContext());
                    textView.setLayoutParams(layoutParams);
                    addView(textView);
                    break;
                case 2:
                    ImageView imageView = new ImageView(getContext());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    imageView.setLayoutParams(layoutParams);
                    addView(imageView);
                    break;
            }
        }
    }

    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt != null && (childAt instanceof ImageView)) {
                ((ImageView) childAt).setImageBitmap((Bitmap) null);
            }
        }
    }

    public void setHomeSectionClickListener(HomeSectionClickListener homeSectionClickListener) {
        this.f13331a = homeSectionClickListener;
    }

    class MyWebViewClient extends WebViewClient {
        MyWebViewClient() {
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            super.onReceivedError(webView, i, str, str2);
            webView.setVisibility(8);
        }

        @SuppressLint({"NewApi"})
        public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
            super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
            webView.setVisibility(8);
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
            webView.setVisibility(8);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            webView.loadUrl(str);
            return true;
        }
    }
}
