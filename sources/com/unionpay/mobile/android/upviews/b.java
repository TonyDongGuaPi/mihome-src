package com.unionpay.mobile.android.upviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.xiaomi.smarthome.httpserver.NanoHTTPD;
import java.util.ArrayList;
import java.util.Timer;

public final class b extends WebView implements Handler.Callback {

    /* renamed from: a  reason: collision with root package name */
    private WebSettings f9707a;
    /* access modifiers changed from: private */
    public Handler b;
    private a c;
    /* access modifiers changed from: private */
    public Timer d;
    /* access modifiers changed from: private */
    public boolean e;
    /* access modifiers changed from: private */
    public ArrayList<String> f;

    public interface a {
        void r();

        void s();
    }

    /* renamed from: com.unionpay.mobile.android.upviews.b$b  reason: collision with other inner class name */
    public interface C0078b extends a {
        void c(String str);
    }

    private class c extends WebChromeClient {
        private c() {
        }

        /* synthetic */ c(b bVar, byte b) {
            this();
        }

        public final void onProgressChanged(WebView webView, int i) {
            if (i == 100) {
                b.this.b.sendEmptyMessage(1);
            }
        }
    }

    private class d extends WebViewClient {
        private d() {
        }

        /* synthetic */ d(b bVar, byte b) {
            this();
        }

        public final void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            b.this.d.cancel();
            b.this.d.purge();
        }

        public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            Timer unused = b.this.d = new Timer();
            b.this.d.schedule(new c(this), 30000);
        }

        public final void onReceivedError(WebView webView, int i, String str, String str2) {
            b.this.a();
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: java.lang.String} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean shouldOverrideUrlLoading(android.webkit.WebView r4, java.lang.String r5) {
            /*
                r3 = this;
                com.unionpay.mobile.android.upviews.b r0 = com.unionpay.mobile.android.upviews.b.this
                java.util.ArrayList r0 = r0.f
                r1 = 0
                if (r0 == 0) goto L_0x0056
                com.unionpay.mobile.android.upviews.b r0 = com.unionpay.mobile.android.upviews.b.this
                java.util.ArrayList r0 = r0.f
                int r0 = r0.size()
                if (r0 != 0) goto L_0x0016
                goto L_0x0056
            L_0x0016:
                if (r5 == 0) goto L_0x0056
                int r0 = r5.length()
                if (r0 <= 0) goto L_0x0056
                r0 = 0
            L_0x001f:
                com.unionpay.mobile.android.upviews.b r2 = com.unionpay.mobile.android.upviews.b.this
                java.util.ArrayList r2 = r2.f
                if (r2 == 0) goto L_0x0056
                com.unionpay.mobile.android.upviews.b r2 = com.unionpay.mobile.android.upviews.b.this
                java.util.ArrayList r2 = r2.f
                int r2 = r2.size()
                if (r0 >= r2) goto L_0x0056
                com.unionpay.mobile.android.upviews.b r2 = com.unionpay.mobile.android.upviews.b.this
                java.util.ArrayList r2 = r2.f
                java.lang.Object r2 = r2.get(r0)
                java.lang.String r2 = (java.lang.String) r2
                boolean r2 = r5.startsWith(r2)
                if (r2 == 0) goto L_0x0053
                com.unionpay.mobile.android.upviews.b r1 = com.unionpay.mobile.android.upviews.b.this
                java.util.ArrayList r1 = r1.f
                java.lang.Object r0 = r1.get(r0)
                r1 = r0
                java.lang.String r1 = (java.lang.String) r1
                goto L_0x0056
            L_0x0053:
                int r0 = r0 + 1
                goto L_0x001f
            L_0x0056:
                r0 = 1
                if (r1 == 0) goto L_0x0070
                com.unionpay.mobile.android.upviews.b r4 = com.unionpay.mobile.android.upviews.b.this
                android.os.Handler r4 = r4.b
                r1 = 4
                android.os.Message r4 = r4.obtainMessage(r1)
                r4.obj = r5
                com.unionpay.mobile.android.upviews.b r5 = com.unionpay.mobile.android.upviews.b.this
                android.os.Handler r5 = r5.b
                r5.sendMessage(r4)
                return r0
            L_0x0070:
                r4.loadUrl(r5)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.upviews.b.d.shouldOverrideUrlLoading(android.webkit.WebView, java.lang.String):boolean");
        }
    }

    public b(Context context, a aVar) {
        super(context);
        this.f9707a = null;
        this.b = null;
        this.c = null;
        this.d = new Timer();
        this.e = false;
        this.f = null;
        this.b = new Handler(this);
        this.c = aVar;
        setScrollBarStyle(33554432);
        this.f9707a = getSettings();
        this.f9707a.setJavaScriptEnabled(true);
        setWebChromeClient(new c(this, (byte) 0));
        setWebViewClient(new d(this, (byte) 0));
    }

    /* access modifiers changed from: private */
    public final void a() {
        loadData(String.format("<div align=\"center\">%s</div>", new Object[]{"&#x7F51;&#x9875;&#x52A0;&#x8F7D;&#x5931;&#x8D25;&#xFF0C;&#x8BF7;&#x91CD;&#x8BD5;"}), NanoHTTPD.c, "utf-8");
    }

    public final void a(String str) {
        if (this.f == null) {
            this.f = new ArrayList<>();
        }
        if (str != null && str.length() > 0) {
            this.f.add(str);
        }
    }

    public final void b(String str) {
        Message obtainMessage = this.b.obtainMessage(0);
        obtainMessage.obj = str;
        this.b.sendMessage(obtainMessage);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean handleMessage(android.os.Message r5) {
        /*
            r4 = this;
            int r0 = r5.what
            r1 = 1
            switch(r0) {
                case 0: goto L_0x0030;
                case 1: goto L_0x0020;
                case 2: goto L_0x0020;
                case 3: goto L_0x001d;
                case 4: goto L_0x0007;
                default: goto L_0x0006;
            }
        L_0x0006:
            goto L_0x005a
        L_0x0007:
            com.unionpay.mobile.android.upviews.b$a r0 = r4.c
            if (r0 == 0) goto L_0x005a
            com.unionpay.mobile.android.upviews.b$a r0 = r4.c
            boolean r0 = r0 instanceof com.unionpay.mobile.android.upviews.b.C0078b
            if (r0 == 0) goto L_0x005a
            com.unionpay.mobile.android.upviews.b$a r0 = r4.c
            com.unionpay.mobile.android.upviews.b$b r0 = (com.unionpay.mobile.android.upviews.b.C0078b) r0
            java.lang.Object r5 = r5.obj
            java.lang.String r5 = (java.lang.String) r5
            r0.c(r5)
            goto L_0x005a
        L_0x001d:
            r4.a()
        L_0x0020:
            int r5 = r5.what
            if (r5 != r1) goto L_0x0026
            r4.e = r1
        L_0x0026:
            com.unionpay.mobile.android.upviews.b$a r5 = r4.c
            if (r5 == 0) goto L_0x005a
            com.unionpay.mobile.android.upviews.b$a r5 = r4.c
            r5.s()
            goto L_0x005a
        L_0x0030:
            com.unionpay.mobile.android.upviews.b$a r0 = r4.c
            if (r0 == 0) goto L_0x0039
            com.unionpay.mobile.android.upviews.b$a r0 = r4.c
            r0.r()
        L_0x0039:
            java.lang.String r0 = ""
            java.lang.Object r2 = r5.obj
            if (r2 == 0) goto L_0x0044
            java.lang.Object r5 = r5.obj
            r0 = r5
            java.lang.String r0 = (java.lang.String) r0
        L_0x0044:
            java.lang.String r5 = "uppay"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "url = "
            r2.<init>(r3)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            android.util.Log.e(r5, r2)
            r4.loadUrl(r0)
        L_0x005a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.upviews.b.handleMessage(android.os.Message):boolean");
    }
}
