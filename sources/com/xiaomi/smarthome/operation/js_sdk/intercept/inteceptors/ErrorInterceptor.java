package com.xiaomi.smarthome.operation.js_sdk.intercept.inteceptors;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.operation.js_sdk.intercept.IUrlInterceptorAdapter;

public class ErrorInterceptor extends IUrlInterceptorAdapter {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21072a = "ErrorInterceptor";
    private String b;
    private boolean c = false;

    public boolean a(WebView webView, String str) {
        if (this.c) {
            return false;
        }
        return super.a(webView, str);
    }

    public void b(WebView webView, String str) {
        super.b(webView, str);
        if (!this.c) {
            c(webView);
        }
    }

    public void a(WebView webView, int i, String str, String str2) {
        Log.d(f21072a, "onReceivedError: " + str);
        this.c = true;
        b(webView);
        super.a(webView, i, str, str2);
    }

    private void b(WebView webView) {
        String url = webView.getUrl();
        if (this.b == null) {
            this.b = url;
        }
        ViewGroup viewGroup = (ViewGroup) webView.getParent();
        if (viewGroup.findViewById(R.id.webview_error_page) == null) {
            viewGroup.addView(a(webView, viewGroup));
        }
    }

    private void c(WebView webView) {
        this.b = null;
        ViewGroup viewGroup = (ViewGroup) webView.getParent();
        viewGroup.removeView(viewGroup.findViewById(R.id.webview_error_page));
    }

    private View a(WebView webView, ViewGroup viewGroup) {
        int width = webView.getWidth();
        int height = webView.getHeight();
        Context context = webView.getContext();
        ViewGroup viewGroup2 = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.webview_error_page, viewGroup, false);
        viewGroup2.findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener(webView) {
            private final /* synthetic */ WebView f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                ErrorInterceptor.this.a(this.f$1, view);
            }
        });
        a((TextView) viewGroup2.findViewById(R.id.desc), context);
        ViewGroup.LayoutParams layoutParams = viewGroup2.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        viewGroup2.setLayoutParams(layoutParams);
        if (!(viewGroup instanceof FrameLayout)) {
            viewGroup2.setX((float) webView.getLeft());
            viewGroup2.setY(0.0f);
        }
        return viewGroup2;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(WebView webView, View view) {
        a(webView);
    }

    public void a(WebView webView) {
        this.c = false;
        webView.stopLoading();
        webView.loadUrl(this.b);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0055  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.widget.TextView r9, final android.content.Context r10) {
        /*
            r8 = this;
            r0 = 2131500726(0x7f0c1eb6, float:1.8625138E38)
            java.lang.String r0 = r10.getString(r0)
            r1 = 0
            r2 = 0
            java.lang.String r3 = "%"
            int r3 = r0.indexOf(r3)     // Catch:{ Exception -> 0x004d }
            java.lang.String r4 = "%"
            int r5 = r3 + 1
            int r4 = r0.indexOf(r4, r5)     // Catch:{ Exception -> 0x004d }
            java.lang.String r5 = r0.substring(r5, r4)     // Catch:{ Exception -> 0x004d }
            java.lang.String r3 = r0.substring(r1, r3)     // Catch:{ Exception -> 0x004d }
            int r2 = r3.length()     // Catch:{ Exception -> 0x0049 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0044 }
            r6.<init>()     // Catch:{ Exception -> 0x0044 }
            r6.append(r3)     // Catch:{ Exception -> 0x0044 }
            r6.append(r5)     // Catch:{ Exception -> 0x0044 }
            int r4 = r4 + 1
            java.lang.String r4 = r0.substring(r4)     // Catch:{ Exception -> 0x0044 }
            r6.append(r4)     // Catch:{ Exception -> 0x0044 }
            java.lang.String r4 = r6.toString()     // Catch:{ Exception -> 0x0044 }
            int r3 = r5.length()     // Catch:{ Exception -> 0x0042 }
            int r1 = r2 + r3
            goto L_0x0053
        L_0x0042:
            r3 = move-exception
            goto L_0x0050
        L_0x0044:
            r4 = move-exception
            r7 = r4
            r4 = r3
            r3 = r7
            goto L_0x0050
        L_0x0049:
            r2 = move-exception
            r4 = r3
            r3 = r2
            goto L_0x004f
        L_0x004d:
            r3 = move-exception
            r4 = r2
        L_0x004f:
            r2 = 0
        L_0x0050:
            r3.printStackTrace()
        L_0x0053:
            if (r1 != 0) goto L_0x0061
            java.lang.String r1 = "%"
            java.lang.String r3 = ""
            java.lang.String r4 = r0.replaceAll(r1, r3)
            int r1 = r4.length()
        L_0x0061:
            android.text.SpannableStringBuilder r0 = android.text.SpannableStringBuilder.valueOf(r4)
            com.xiaomi.smarthome.operation.js_sdk.intercept.inteceptors.ErrorInterceptor$1 r3 = new com.xiaomi.smarthome.operation.js_sdk.intercept.inteceptors.ErrorInterceptor$1
            r3.<init>(r10)
            r10 = 33
            r0.setSpan(r3, r2, r1, r10)
            r9.setText(r0)
            android.text.method.MovementMethod r10 = android.text.method.LinkMovementMethod.getInstance()
            r9.setMovementMethod(r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.operation.js_sdk.intercept.inteceptors.ErrorInterceptor.a(android.widget.TextView, android.content.Context):void");
    }
}
