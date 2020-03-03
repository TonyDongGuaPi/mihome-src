package com.unionpay;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.LinearLayout;
import com.miui.tsmclient.analytics.AnalyticManager;
import org.json.JSONException;
import org.json.JSONObject;

public class UPPayWapActivity extends Activity {

    /* renamed from: a  reason: collision with root package name */
    LinearLayout f9534a;
    private WebView b;
    private WebViewJavascriptBridge c;
    /* access modifiers changed from: private */
    public AlertDialog d;

    static /* synthetic */ void a(UPPayWapActivity uPPayWapActivity, String str, String str2) {
        Intent intent = new Intent();
        intent.putExtra(AnalyticManager.KEY_PAY_RESULT, str);
        intent.putExtra("result_data", str2);
        uPPayWapActivity.setResult(-1, intent);
        uPPayWapActivity.finish();
    }

    static /* synthetic */ void a(UPPayWapActivity uPPayWapActivity, boolean z) {
        uPPayWapActivity.f9534a.setVisibility(z ? 0 : 8);
    }

    /* access modifiers changed from: private */
    public static String b(String str, String str2, String str3) {
        try {
            JSONObject jSONObject = new JSONObject("{\"code\":\"0\",\"msg\":\"success\"}");
            if (str != null) {
                jSONObject.put("code", str);
            }
            if (str2 != null) {
                jSONObject.put("msg", str2);
            }
            if (str3 != null) {
                jSONObject.put("value", str3);
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:22|23|25) */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        finish();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x01e7 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r13) {
        /*
            r12 = this;
            super.onCreate(r13)
            android.content.Intent r13 = r12.getIntent()     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r0 = "magic_data"
            java.lang.String r13 = r13.getStringExtra(r0)     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r0 = "949A1CC"
            boolean r13 = r0.equalsIgnoreCase(r13)     // Catch:{ Exception -> 0x01e7 }
            if (r13 != 0) goto L_0x0018
            r12.finish()     // Catch:{ Exception -> 0x01e7 }
        L_0x0018:
            android.content.Intent r13 = r12.getIntent()     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r0 = "waptype"
            java.lang.String r13 = r13.getStringExtra(r0)     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r0 = ""
            java.lang.String r1 = ""
            if (r13 == 0) goto L_0x0051
            java.lang.String r2 = "new_page"
            boolean r13 = r13.equals(r2)     // Catch:{ Exception -> 0x01e7 }
            if (r13 == 0) goto L_0x0051
            android.content.Intent r13 = r12.getIntent()     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r2 = "wapurl"
            java.lang.String r13 = r13.getStringExtra(r2)     // Catch:{ Exception -> 0x01e7 }
            android.content.Intent r2 = r12.getIntent()     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r3 = "waptitle"
            java.lang.String r2 = r2.getStringExtra(r3)     // Catch:{ Exception -> 0x01e7 }
            if (r13 == 0) goto L_0x0047
            goto L_0x0048
        L_0x0047:
            r13 = r0
        L_0x0048:
            if (r2 == 0) goto L_0x004b
            r1 = r2
        L_0x004b:
            com.unionpay.j r0 = new com.unionpay.j     // Catch:{ Exception -> 0x01e7 }
            r0.<init>(r12)     // Catch:{ Exception -> 0x01e7 }
            goto L_0x0087
        L_0x0051:
            android.content.Intent r13 = r12.getIntent()     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r1 = "wapurl"
            java.lang.String r13 = r13.getStringExtra(r1)     // Catch:{ Exception -> 0x01e7 }
            android.content.Intent r1 = r12.getIntent()     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r2 = "paydata"
            java.lang.String r1 = r1.getStringExtra(r2)     // Catch:{ Exception -> 0x01e7 }
            if (r1 == 0) goto L_0x007b
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01e7 }
            r0.<init>()     // Catch:{ Exception -> 0x01e7 }
            r0.append(r13)     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r13 = "?s="
            r0.append(r13)     // Catch:{ Exception -> 0x01e7 }
            r0.append(r1)     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x01e7 }
        L_0x007b:
            r13 = r0
            com.unionpay.utils.k r0 = com.unionpay.utils.k.a()     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r1 = r0.e     // Catch:{ Exception -> 0x01e7 }
            com.unionpay.n r0 = new com.unionpay.n     // Catch:{ Exception -> 0x01e7 }
            r0.<init>(r12)     // Catch:{ Exception -> 0x01e7 }
        L_0x0087:
            android.view.Window r2 = r12.getWindow()     // Catch:{ Exception -> 0x01e7 }
            r3 = 1
            r2.requestFeature(r3)     // Catch:{ Exception -> 0x01e7 }
            android.widget.LinearLayout r2 = new android.widget.LinearLayout     // Catch:{ Exception -> 0x01e7 }
            r2.<init>(r12)     // Catch:{ Exception -> 0x01e7 }
            r2.setOrientation(r3)     // Catch:{ Exception -> 0x01e7 }
            android.widget.RelativeLayout$LayoutParams r4 = new android.widget.RelativeLayout$LayoutParams     // Catch:{ Exception -> 0x01e7 }
            r5 = -2
            r6 = -1
            r4.<init>(r6, r5)     // Catch:{ Exception -> 0x01e7 }
            android.widget.RelativeLayout r7 = new android.widget.RelativeLayout     // Catch:{ Exception -> 0x01e7 }
            r7.<init>(r12)     // Catch:{ Exception -> 0x01e7 }
            r7.setLayoutParams(r4)     // Catch:{ Exception -> 0x01e7 }
            r4 = 1092616192(0x41200000, float:10.0)
            int r4 = com.unionpay.utils.f.a(r12, r4)     // Catch:{ Exception -> 0x01e7 }
            r8 = 1112539136(0x42500000, float:52.0)
            int r8 = com.unionpay.utils.f.a(r12, r8)     // Catch:{ Exception -> 0x01e7 }
            android.widget.RelativeLayout$LayoutParams r9 = new android.widget.RelativeLayout$LayoutParams     // Catch:{ Exception -> 0x01e7 }
            r9.<init>(r6, r8)     // Catch:{ Exception -> 0x01e7 }
            r7.setLayoutParams(r9)     // Catch:{ Exception -> 0x01e7 }
            r9 = -10705958(0xffffffffff5ca3da, float:-2.9328093E38)
            r7.setBackgroundColor(r9)     // Catch:{ Exception -> 0x01e7 }
            android.widget.LinearLayout r9 = new android.widget.LinearLayout     // Catch:{ Exception -> 0x01e7 }
            r9.<init>(r12)     // Catch:{ Exception -> 0x01e7 }
            r12.f9534a = r9     // Catch:{ Exception -> 0x01e7 }
            android.widget.LinearLayout r9 = r12.f9534a     // Catch:{ Exception -> 0x01e7 }
            r9.setPadding(r4, r4, r4, r4)     // Catch:{ Exception -> 0x01e7 }
            android.widget.LinearLayout r9 = r12.f9534a     // Catch:{ Exception -> 0x01e7 }
            r10 = 16
            r9.setGravity(r10)     // Catch:{ Exception -> 0x01e7 }
            android.widget.RelativeLayout$LayoutParams r9 = new android.widget.RelativeLayout$LayoutParams     // Catch:{ Exception -> 0x01e7 }
            r9.<init>(r5, r5)     // Catch:{ Exception -> 0x01e7 }
            r5 = 9
            r9.addRule(r5, r6)     // Catch:{ Exception -> 0x01e7 }
            r5 = 15
            r9.addRule(r5, r6)     // Catch:{ Exception -> 0x01e7 }
            r9.leftMargin = r4     // Catch:{ Exception -> 0x01e7 }
            android.widget.LinearLayout r4 = r12.f9534a     // Catch:{ Exception -> 0x01e7 }
            r4.setOnClickListener(r0)     // Catch:{ Exception -> 0x01e7 }
            android.widget.LinearLayout r0 = r12.f9534a     // Catch:{ Exception -> 0x01e7 }
            r7.addView(r0, r9)     // Catch:{ Exception -> 0x01e7 }
            r0 = 1101004800(0x41a00000, float:20.0)
            int r4 = com.unionpay.utils.f.a(r12, r0)     // Catch:{ Exception -> 0x01e7 }
            r9 = 1093664768(0x41300000, float:11.0)
            int r9 = com.unionpay.utils.f.a(r12, r9)     // Catch:{ Exception -> 0x01e7 }
            android.widget.ImageView r10 = new android.widget.ImageView     // Catch:{ Exception -> 0x01e7 }
            r10.<init>(r12)     // Catch:{ Exception -> 0x01e7 }
            int r11 = com.unionpay.utils.h.f9845a     // Catch:{ Exception -> 0x01e7 }
            android.graphics.drawable.Drawable r11 = com.unionpay.utils.g.a((int) r11)     // Catch:{ Exception -> 0x01e7 }
            r10.setBackgroundDrawable(r11)     // Catch:{ Exception -> 0x01e7 }
            android.widget.RelativeLayout$LayoutParams r11 = new android.widget.RelativeLayout$LayoutParams     // Catch:{ Exception -> 0x01e7 }
            r11.<init>(r9, r4)     // Catch:{ Exception -> 0x01e7 }
            r11.addRule(r5, r6)     // Catch:{ Exception -> 0x01e7 }
            android.widget.LinearLayout r4 = r12.f9534a     // Catch:{ Exception -> 0x01e7 }
            r4.addView(r10, r11)     // Catch:{ Exception -> 0x01e7 }
            android.widget.RelativeLayout$LayoutParams r4 = new android.widget.RelativeLayout$LayoutParams     // Catch:{ Exception -> 0x01e7 }
            r5 = 1134559232(0x43a00000, float:320.0)
            int r5 = com.unionpay.utils.f.a(r12, r5)     // Catch:{ Exception -> 0x01e7 }
            r4.<init>(r5, r8)     // Catch:{ Exception -> 0x01e7 }
            r5 = 13
            r4.addRule(r5, r6)     // Catch:{ Exception -> 0x01e7 }
            android.widget.TextView r5 = new android.widget.TextView     // Catch:{ Exception -> 0x01e7 }
            r5.<init>(r12)     // Catch:{ Exception -> 0x01e7 }
            r5.setTextSize(r0)     // Catch:{ Exception -> 0x01e7 }
            r5.setTextColor(r6)     // Catch:{ Exception -> 0x01e7 }
            r5.setText(r1)     // Catch:{ Exception -> 0x01e7 }
            r0 = 17
            r5.setGravity(r0)     // Catch:{ Exception -> 0x01e7 }
            r5.setSingleLine(r3)     // Catch:{ Exception -> 0x01e7 }
            android.text.TextUtils$TruncateAt r0 = android.text.TextUtils.TruncateAt.END     // Catch:{ Exception -> 0x01e7 }
            r5.setEllipsize(r0)     // Catch:{ Exception -> 0x01e7 }
            r7.addView(r5, r4)     // Catch:{ Exception -> 0x01e7 }
            r2.addView(r7)     // Catch:{ Exception -> 0x01e7 }
            android.webkit.WebView r0 = new android.webkit.WebView     // Catch:{ Exception -> 0x01e7 }
            r0.<init>(r12)     // Catch:{ Exception -> 0x01e7 }
            r12.b = r0     // Catch:{ Exception -> 0x01e7 }
            android.webkit.WebView r0 = r12.b     // Catch:{ Exception -> 0x01e7 }
            android.widget.RelativeLayout$LayoutParams r1 = new android.widget.RelativeLayout$LayoutParams     // Catch:{ Exception -> 0x01e7 }
            r1.<init>(r6, r6)     // Catch:{ Exception -> 0x01e7 }
            r0.setLayoutParams(r1)     // Catch:{ Exception -> 0x01e7 }
            android.webkit.WebView r0 = r12.b     // Catch:{ Exception -> 0x01e7 }
            r2.addView(r0)     // Catch:{ Exception -> 0x01e7 }
            r12.setContentView(r2)     // Catch:{ Exception -> 0x01e7 }
            com.unionpay.WebViewJavascriptBridge r0 = new com.unionpay.WebViewJavascriptBridge     // Catch:{ Exception -> 0x01e7 }
            android.webkit.WebView r1 = r12.b     // Catch:{ Exception -> 0x01e7 }
            r2 = 0
            r0.<init>(r12, r1, r2)     // Catch:{ Exception -> 0x01e7 }
            r12.c = r0     // Catch:{ Exception -> 0x01e7 }
            android.webkit.WebView r0 = r12.b     // Catch:{ Exception -> 0x01e7 }
            r0.loadUrl(r13)     // Catch:{ Exception -> 0x01e7 }
            com.unionpay.WebViewJavascriptBridge r13 = r12.c     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r0 = "getDeviceInfo"
            com.unionpay.q r1 = new com.unionpay.q     // Catch:{ Exception -> 0x01e7 }
            r1.<init>(r12)     // Catch:{ Exception -> 0x01e7 }
            r13.registerHandler(r0, r1)     // Catch:{ Exception -> 0x01e7 }
            com.unionpay.WebViewJavascriptBridge r13 = r12.c     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r0 = "saveData"
            com.unionpay.r r1 = new com.unionpay.r     // Catch:{ Exception -> 0x01e7 }
            r1.<init>(r12)     // Catch:{ Exception -> 0x01e7 }
            r13.registerHandler(r0, r1)     // Catch:{ Exception -> 0x01e7 }
            com.unionpay.WebViewJavascriptBridge r13 = r12.c     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r0 = "getData"
            com.unionpay.s r1 = new com.unionpay.s     // Catch:{ Exception -> 0x01e7 }
            r1.<init>(r12)     // Catch:{ Exception -> 0x01e7 }
            r13.registerHandler(r0, r1)     // Catch:{ Exception -> 0x01e7 }
            com.unionpay.WebViewJavascriptBridge r13 = r12.c     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r0 = "removeData"
            com.unionpay.t r1 = new com.unionpay.t     // Catch:{ Exception -> 0x01e7 }
            r1.<init>(r12)     // Catch:{ Exception -> 0x01e7 }
            r13.registerHandler(r0, r1)     // Catch:{ Exception -> 0x01e7 }
            com.unionpay.WebViewJavascriptBridge r13 = r12.c     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r0 = "setPageBackEnable"
            com.unionpay.u r1 = new com.unionpay.u     // Catch:{ Exception -> 0x01e7 }
            r1.<init>(r12)     // Catch:{ Exception -> 0x01e7 }
            r13.registerHandler(r0, r1)     // Catch:{ Exception -> 0x01e7 }
            com.unionpay.WebViewJavascriptBridge r13 = r12.c     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r0 = "payBySDK"
            com.unionpay.v r1 = new com.unionpay.v     // Catch:{ Exception -> 0x01e7 }
            r1.<init>(r12)     // Catch:{ Exception -> 0x01e7 }
            r13.registerHandler(r0, r1)     // Catch:{ Exception -> 0x01e7 }
            com.unionpay.WebViewJavascriptBridge r13 = r12.c     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r0 = "downloadApp"
            com.unionpay.w r1 = new com.unionpay.w     // Catch:{ Exception -> 0x01e7 }
            r1.<init>(r12)     // Catch:{ Exception -> 0x01e7 }
            r13.registerHandler(r0, r1)     // Catch:{ Exception -> 0x01e7 }
            com.unionpay.WebViewJavascriptBridge r13 = r12.c     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r0 = "payResult"
            com.unionpay.k r1 = new com.unionpay.k     // Catch:{ Exception -> 0x01e7 }
            r1.<init>(r12)     // Catch:{ Exception -> 0x01e7 }
            r13.registerHandler(r0, r1)     // Catch:{ Exception -> 0x01e7 }
            com.unionpay.WebViewJavascriptBridge r13 = r12.c     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r0 = "closePage"
            com.unionpay.l r1 = new com.unionpay.l     // Catch:{ Exception -> 0x01e7 }
            r1.<init>(r12)     // Catch:{ Exception -> 0x01e7 }
            r13.registerHandler(r0, r1)     // Catch:{ Exception -> 0x01e7 }
            com.unionpay.WebViewJavascriptBridge r13 = r12.c     // Catch:{ Exception -> 0x01e7 }
            java.lang.String r0 = "openNewPage"
            com.unionpay.m r1 = new com.unionpay.m     // Catch:{ Exception -> 0x01e7 }
            r1.<init>(r12)     // Catch:{ Exception -> 0x01e7 }
            r13.registerHandler(r0, r1)     // Catch:{ Exception -> 0x01e7 }
            return
        L_0x01e7:
            r12.finish()     // Catch:{ Exception -> 0x01ea }
        L_0x01ea:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.UPPayWapActivity.onCreate(android.os.Bundle):void");
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        onPause();
        return true;
    }
}
