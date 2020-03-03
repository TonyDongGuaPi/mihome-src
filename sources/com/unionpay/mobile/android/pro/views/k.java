package com.unionpay.mobile.android.pro.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.drew.metadata.photoshop.PhotoshopDirectory;
import com.google.android.gms.actions.SearchIntents;
import com.unionpay.mobile.android.languages.c;
import com.unionpay.mobile.android.model.e;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import com.unionpay.mobile.android.nocard.utils.f;
import com.unionpay.mobile.android.nocard.views.b;
import com.unionpay.mobile.android.upviews.a;
import com.unionpay.mobile.android.upwidget.UPRadiationView;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.utils.i;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.utils.o;
import com.unionpay.mobile.android.widgets.ay;
import com.unionpay.tsmservice.data.Constant;
import com.unionpay.tsmservice.data.ResultCode;
import com.unionpay.uppay.PayActivity;
import com.xiaomi.youpin.login.ui.web.LoginBindBaseWebActivity;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import java.security.MessageDigest;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import org.cybergarage.upnp.UPnP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class k extends b implements Handler.Callback, a.b {
    private static Date ac = new Date(System.currentTimeMillis());
    private static String ad = new SimpleDateFormat("yyyyMMddhhmmss").format(ac);
    /* access modifiers changed from: private */
    public LinearLayout A = null;
    private LinearLayout B = null;
    private RelativeLayout C = null;
    private LinearLayout D = null;
    private LinearLayout E = null;
    /* access modifiers changed from: private */
    public ay F;
    private UPRadiationView G;
    private ImageView H;
    private String I;
    private String J;
    /* access modifiers changed from: private */
    public com.unionpay.mobile.android.upwidget.a K = null;
    /* access modifiers changed from: private */
    public com.unionpay.mobile.android.upwidget.a L = null;
    /* access modifiers changed from: private */
    public boolean M = true;
    private boolean N = false;
    private a O = null;
    /* access modifiers changed from: private */
    public String P;
    /* access modifiers changed from: private */
    public int Q = 5;
    /* access modifiers changed from: private */
    public NfcAdapter R;
    /* access modifiers changed from: private */
    public FrameLayout S;
    private View.OnClickListener T = new l(this);
    private View.OnClickListener U = new n(this);
    private View.OnClickListener V = new o(this);
    private View.OnClickListener W = new p(this);
    private View.OnClickListener aa = new q(this);
    private View.OnClickListener ab = new r(this);
    private String ae = null;
    private HashMap<String, String> af = new HashMap<>();
    public String r = UPnP.VERSION;
    UPPayEngine s;
    com.unionpay.mobile.android.pboctransaction.nfc.a t;
    private int u = 20;
    /* access modifiers changed from: private */
    public int v = 100;
    private TextView w = null;
    private boolean x = false;
    /* access modifiers changed from: private */
    public a y = null;
    /* access modifiers changed from: private */
    public Handler z = null;

    public k(Context context, e eVar, UPPayEngine uPPayEngine) {
        super(context, eVar);
        this.f = 6;
        this.q = "nfcpay";
        this.s = uPPayEngine;
        this.z = new Handler(this);
        this.x = this.f9608a.K;
        setBackgroundColor(-11495169);
        e();
    }

    static /* synthetic */ void a(k kVar, String str, String str2) {
        kVar.v = 7;
        kVar.b.a(c.bD.U);
        kVar.e.c(str, str2);
    }

    private void a(String str, StringBuffer stringBuffer) {
        String str2 = this.af.get(str);
        String a2 = com.unionpay.mobile.android.pboctransaction.e.a(new byte[]{(byte) (str2.length() / 2)}, 1);
        stringBuffer.append(str);
        stringBuffer.append(a2);
        stringBuffer.append(str2);
    }

    /* access modifiers changed from: private */
    public void b(String str, HashMap<String, String> hashMap) {
        Object a2 = ((PayActivity) this.d).a(com.unionpay.mobile.android.pro.pboc.engine.b.class.toString());
        if ((a2 != null ? (com.unionpay.mobile.android.pro.pboc.engine.b) a2 : null) == null) {
            super.b(5);
        } else {
            new Thread(new s(this, str, hashMap)).start();
        }
    }

    private static final String d(String str) {
        try {
            byte[] bytes = str.getBytes();
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bytes);
            return com.unionpay.mobile.android.pboctransaction.e.a(instance.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void d(String str, String str2) {
        this.v = 8;
        if (TextUtils.isEmpty(str2)) {
            this.e.c(str, "");
        } else {
            this.e.a(str, "\"uuid\":\"" + str2 + "\"", 10);
        }
        this.Q--;
    }

    private static String e(String str, String str2) {
        byte[] a2 = com.unionpay.mobile.android.pboctransaction.e.a(str);
        byte[] a3 = com.unionpay.mobile.android.pboctransaction.e.a(str2);
        for (int i = 0; i < a2.length; i++) {
            a2[i] = (byte) (a2[i] ^ a3[i]);
        }
        return com.unionpay.mobile.android.pboctransaction.e.a(a2);
    }

    private void s() {
        this.v = 103;
        this.e.a(SearchIntents.EXTRA_QUERY, this.f9608a.aj, 3);
        this.u--;
    }

    /* access modifiers changed from: private */
    public HashMap<String, String> t() {
        HashMap<String, String> hashMap = new HashMap<>();
        if (this.y != null) {
            hashMap = this.y.c();
        }
        if (this.O != null) {
            HashMap<String, String> c = this.O.c();
            if (c == null || hashMap == null) {
                return (c == null || hashMap != null) ? hashMap : c;
            }
            hashMap.putAll(c);
        }
    }

    private static Bundle u() {
        Bundle bundle = new Bundle();
        bundle.putString("action_resp_code", "0000");
        return bundle;
    }

    private int v() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) this.d).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public final synchronized Bundle a(String str, HashMap<String, String> hashMap) {
        Bundle u2;
        u2 = u();
        this.af.put("PIN", str);
        StringBuffer stringBuffer = new StringBuffer();
        a("9F26", stringBuffer);
        a("9F27", stringBuffer);
        a("9F10", stringBuffer);
        a("9F37", stringBuffer);
        a("9F36", stringBuffer);
        a("95", stringBuffer);
        a("9A", stringBuffer);
        a("9C", stringBuffer);
        a("9F02", stringBuffer);
        a("5F2A", stringBuffer);
        a("82", stringBuffer);
        a("9F1A", stringBuffer);
        a("9F03", stringBuffer);
        a("9F33", stringBuffer);
        a("9F34", stringBuffer);
        a("9F35", stringBuffer);
        a("9F1E", stringBuffer);
        if (this.af.get("9F63") != null && !TextUtils.isEmpty(this.af.get("9F63"))) {
            a("9F63", stringBuffer);
        }
        this.af.put("DCD", stringBuffer.toString());
        this.I = e(this.I, this.s.b());
        String str2 = (((("" + "pan=" + this.af.get("AN1")) + "&pin=" + this.af.get("PIN")) + "&icc_data=" + this.af.get("DCD")) + "&card_seq_id=" + this.af.get("5F34")) + "&auth_id=" + this.J;
        com.unionpay.mobile.android.utils.k.c("mac", str2);
        String d = d(str2);
        com.unionpay.mobile.android.utils.k.c("md5", d);
        String g = this.e.g(d);
        com.unionpay.mobile.android.utils.k.c(DTransferConstants.n, g);
        String a2 = this.e.a(str2 + com.alipay.sdk.sys.a.b + g, this.I);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("v", this.r);
            jSONObject.put("cmd", "pay");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject.put("params", jSONObject2);
            jSONObject2.put("encrypt_key_field", a2);
            jSONObject2.put("pay_type", "2");
            jSONObject2.put("pay_mode", "1");
            jSONObject2.put(LoginBindBaseWebActivity.SOURCE_BIND, "no");
            jSONObject2.put("carrier_tp", "7");
            jSONObject2.put("carrier_app_tp", "0");
            jSONObject2.put("sign", g);
            jSONObject2.put("pan", this.af.get("AN1"));
            if (this.af.get("ED") != null) {
                jSONObject2.put("expire", this.af.get("ED"));
            }
            if (this.af.get("TD2") != null) {
                jSONObject2.put("track2_data", this.af.get("TD2"));
            }
            if (!(hashMap == null || hashMap.keySet() == null || hashMap.keySet().size() <= 0)) {
                hashMap.remove("pan");
                hashMap.remove(Constant.KEY_PIN);
                for (String next : hashMap.keySet()) {
                    jSONObject2.put(next, hashMap.get(next));
                }
            }
            u2.putString("action_resp_message", this.s.a(jSONObject.toString()));
        } catch (JSONException unused) {
            u2.putString("action_resp_code", ResultCode.ERROR_INTERFACE_GET_SMS_AUTH_CODE);
            return u2;
        }
        return u2;
    }

    public final void a(NfcAdapter nfcAdapter) {
        this.R = nfcAdapter;
        if (this.R.isEnabled()) {
            this.B.setVisibility(8);
            if (this.M) {
                this.D.setVisibility(0);
            }
            this.E.setVisibility(8);
            if (this.G != null) {
                this.G.setVisibility(0);
                return;
            }
            return;
        }
        this.B.setVisibility(0);
        this.D.setVisibility(8);
        this.E.setVisibility(0);
        if (this.G != null) {
            this.G.setVisibility(4);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x01e6  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x01f8  */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.unionpay.mobile.android.pboctransaction.nfc.a r9) {
        /*
            r8 = this;
            r8.t = r9
            com.unionpay.mobile.android.model.b r9 = r8.f9608a
            java.util.HashMap<java.lang.String, java.lang.String> r9 = r9.p
            java.lang.String r0 = ad
            r1 = 8
            r2 = 2
            java.lang.String r0 = r0.substring(r2, r1)
            java.sql.Date r2 = new java.sql.Date
            long r3 = java.lang.System.currentTimeMillis()
            r2.<init>(r3)
            long r2 = r2.getTime()
            java.lang.String r4 = java.lang.String.valueOf(r2)
            int r5 = r4.length()
            r6 = 1
            r7 = 0
            if (r5 >= r1) goto L_0x0037
            java.lang.String r4 = "%08d"
            java.lang.Object[] r5 = new java.lang.Object[r6]
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r5[r7] = r2
            java.lang.String r2 = java.lang.String.format(r4, r5)
            goto L_0x0044
        L_0x0037:
            int r2 = r4.length()
            int r2 = r2 - r1
            int r3 = r4.length()
            java.lang.String r2 = r4.substring(r2, r3)
        L_0x0044:
            java.util.HashMap<java.lang.String, java.lang.String> r3 = r8.af
            java.lang.String r4 = "9F26"
            java.lang.String r5 = ""
            r3.put(r4, r5)
            java.util.HashMap<java.lang.String, java.lang.String> r3 = r8.af
            java.lang.String r4 = "9F27"
            java.lang.String r5 = "80"
            r3.put(r4, r5)
            java.util.HashMap<java.lang.String, java.lang.String> r3 = r8.af
            java.lang.String r4 = "9F10"
            java.lang.String r5 = ""
            r3.put(r4, r5)
            java.util.HashMap<java.lang.String, java.lang.String> r3 = r8.af
            java.lang.String r4 = "9F37"
            r3.put(r4, r2)
            java.util.HashMap<java.lang.String, java.lang.String> r2 = r8.af
            java.lang.String r3 = "9F36"
            java.lang.String r4 = ""
            r2.put(r3, r4)
            java.util.HashMap<java.lang.String, java.lang.String> r2 = r8.af
            java.lang.String r3 = "95"
            java.lang.String r4 = "0000000800"
            r2.put(r3, r4)
            java.util.HashMap<java.lang.String, java.lang.String> r2 = r8.af
            java.lang.String r3 = "9A"
            r2.put(r3, r0)
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r8.af
            java.lang.String r2 = "9C"
            java.lang.String r3 = "45"
            r0.put(r2, r3)
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r8.af
            java.lang.String r2 = "9F02"
            java.lang.String r3 = "000000000000"
            r0.put(r2, r3)
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r8.af
            java.lang.String r2 = "5F2A"
            java.lang.String r3 = "0156"
            r0.put(r2, r3)
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r8.af
            java.lang.String r2 = "82"
            java.lang.String r3 = ""
            r0.put(r2, r3)
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r8.af
            java.lang.String r2 = "9F1A"
            java.lang.String r3 = "0156"
            r0.put(r2, r3)
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r8.af
            java.lang.String r2 = "9F03"
            java.lang.String r3 = "000000000000"
            r0.put(r2, r3)
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r8.af
            java.lang.String r2 = "9F33"
            java.lang.String r3 = "A04000"
            r0.put(r2, r3)
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r8.af
            java.lang.String r2 = "9F34"
            java.lang.String r3 = "420300"
            r0.put(r2, r3)
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r8.af
            java.lang.String r2 = "9F35"
            java.lang.String r3 = "34"
            r0.put(r2, r3)
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r8.af
            java.lang.String r2 = "9F1E"
            java.lang.String r3 = "3030303030303030"
            r0.put(r2, r3)
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r8.af
            java.lang.String r2 = "84"
            java.lang.String r3 = ""
            r0.put(r2, r3)
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r8.af
            java.lang.String r2 = "9F09"
            java.lang.String r3 = "0001"
            r0.put(r2, r3)
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r8.af
            java.lang.String r2 = "9F74"
            java.lang.String r3 = ""
            r0.put(r2, r3)
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r8.af
            java.lang.String r2 = "9F63"
            java.lang.String r3 = ""
            r0.put(r2, r3)
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r8.af
            java.lang.String r2 = "9F7A"
            java.lang.String r3 = "00"
            r0.put(r2, r3)
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r8.af
            java.lang.String r2 = "9F21"
            java.lang.String r3 = ad
            java.lang.String r1 = r3.substring(r1)
            r0.put(r2, r1)
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r8.af
            java.lang.String r1 = "9F4E"
            java.lang.String r2 = "0000000000000000000000000000000000000000"
            r0.put(r1, r2)
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r8.af
            java.lang.String r1 = "DF31"
            java.lang.String r2 = "0100000000"
            r0.put(r1, r2)
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r8.af
            java.lang.String r1 = "9F66"
            java.lang.String r2 = "36800000"
            r0.put(r1, r2)
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r8.af
            java.lang.String r1 = "DF60"
            java.lang.String r2 = "00"
            r0.put(r1, r2)
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r8.af
            java.lang.String r1 = "9F02"
            java.lang.String r2 = "trans_amt"
            java.lang.Object r2 = r9.get(r2)
            r0.put(r1, r2)
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r8.af
            java.lang.String r1 = "9F1A"
            java.lang.String r2 = "0156"
            r0.put(r1, r2)
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r8.af
            java.lang.String r1 = "5F2A"
            java.lang.String r2 = "trans currcy code"
            java.lang.Object r2 = r9.get(r2)
            r0.put(r1, r2)
            java.util.HashMap<java.lang.String, java.lang.String> r0 = r8.af
            java.lang.String r1 = "9C"
            java.lang.String r2 = "trans_type"
            java.lang.Object r9 = r9.get(r2)
            r0.put(r1, r9)
            java.util.HashMap<java.lang.String, java.lang.String> r9 = r8.af
            java.lang.String r0 = "CUR"
            java.util.HashMap<java.lang.String, java.lang.String> r1 = r8.af
            java.lang.String r2 = "5F2A"
            java.lang.Object r1 = r1.get(r2)
            r9.put(r0, r1)
            android.os.Bundle r9 = u()
            java.sql.Date r0 = new java.sql.Date
            long r1 = java.lang.System.currentTimeMillis()
            r0.<init>(r1)
            ac = r0
            java.text.SimpleDateFormat r0 = new java.text.SimpleDateFormat
            java.lang.String r1 = "yyyyMMddHHmmss"
            r0.<init>(r1)
            java.sql.Date r1 = ac
            java.lang.String r0 = r0.format(r1)
            ad = r0
            java.lang.String r0 = new java.lang.String
            java.lang.String r1 = ad
            r0.<init>(r1)
            r8.ae = r0
            com.unionpay.mobile.android.pboctransaction.nfc.a r0 = r8.t
            java.lang.String r0 = r0.a()
            if (r0 == 0) goto L_0x01d5
            int r1 = r0.length()
            if (r1 != 0) goto L_0x01ac
            goto L_0x01d5
        L_0x01ac:
            java.lang.String r1 = "noSupUnionpay"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x01b9
            java.lang.String r0 = "action_resp_code"
            java.lang.String r1 = "10024"
            goto L_0x01d9
        L_0x01b9:
            com.unionpay.mobile.android.pboctransaction.nfc.a r1 = r8.t
            java.util.HashMap<java.lang.String, java.lang.String> r2 = r8.af
            java.lang.String r0 = r1.a((java.lang.String) r0, (java.util.HashMap<java.lang.String, java.lang.String>) r2)
            if (r0 == 0) goto L_0x01d0
            int r1 = r0.length()
            if (r1 != 0) goto L_0x01ca
            goto L_0x01d0
        L_0x01ca:
            java.util.HashMap<java.lang.String, java.lang.String> r1 = r8.af
            com.unionpay.mobile.android.pboctransaction.nfc.a.b(r0, r1)
            goto L_0x01dc
        L_0x01d0:
            java.lang.String r0 = "action_resp_code"
            java.lang.String r1 = "10020"
            goto L_0x01d9
        L_0x01d5:
            java.lang.String r0 = "action_resp_code"
            java.lang.String r1 = "10019"
        L_0x01d9:
            r9.putString(r0, r1)
        L_0x01dc:
            java.lang.String r0 = "action_resp_code"
            java.lang.String r0 = r9.getString(r0)
            java.lang.String r1 = "0000"
            if (r0 == r1) goto L_0x01f2
            android.os.Handler r0 = r8.z
            android.os.Handler r1 = r8.z
            android.os.Message r9 = r1.obtainMessage(r7, r9)
            r0.sendMessage(r9)
            r6 = 0
        L_0x01f2:
            if (r6 == 0) goto L_0x022b
            boolean r9 = r8.M
            if (r9 == 0) goto L_0x022b
            r9 = 102(0x66, float:1.43E-43)
            r8.v = r9
            r8.j = r7
            com.unionpay.mobile.android.widgets.m r9 = r8.b
            com.unionpay.mobile.android.languages.c r0 = com.unionpay.mobile.android.languages.c.bD
            java.lang.String r0 = r0.U
            r9.a((java.lang.String) r0)
            java.util.HashMap<java.lang.String, java.lang.String> r9 = r8.af
            java.lang.String r0 = "AN1"
            java.lang.Object r9 = r9.get(r0)
            java.lang.String r9 = (java.lang.String) r9
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "\"pan\":\""
            r0.<init>(r1)
            r0.append(r9)
            java.lang.String r9 = "\",\"carrier_tp\":\"7\""
            r0.append(r9)
            java.lang.String r9 = r0.toString()
            com.unionpay.mobile.android.nocard.utils.UPPayEngine r0 = r8.e
            java.lang.String r1 = "cardrules"
            r0.c(r1, r9)
        L_0x022b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.pro.views.k.a(com.unionpay.mobile.android.pboctransaction.nfc.a):void");
    }

    public final void a(a.C0077a aVar) {
    }

    /* access modifiers changed from: protected */
    public final void a(String str, boolean z2) {
        this.b.a(new m(this, z2), (View.OnClickListener) null);
        this.b.a(c.bD.Y, str, c.bD.W);
    }

    public final void a(JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject;
        int i = this.v;
        switch (i) {
            case 7:
                i();
                JSONArray d = j.d(jSONObject2, "options");
                if (this.O != null) {
                    this.O.a(d);
                    return;
                }
                return;
            case 8:
                String a2 = j.a(jSONObject2, "status");
                if (a2 == null || !"01".equals(a2)) {
                    JSONArray d2 = j.d(jSONObject2, "options");
                    String a3 = j.a(jSONObject2, "empty_info");
                    if (this.O != null) {
                        this.O.a(d2, a3);
                        return;
                    }
                    return;
                }
                String a4 = j.a(jSONObject2, "uuid");
                if (this.Q >= 0) {
                    d(this.P, a4);
                    return;
                }
                String str = c.bD.D;
                if (this.O != null) {
                    this.O.a((JSONArray) null, str);
                    return;
                }
                return;
            default:
                boolean z2 = true;
                switch (i) {
                    case 101:
                        this.f9608a.aj = i.a(jSONObject.toString());
                        String a5 = j.a(jSONObject2, "qn");
                        if (!TextUtils.isEmpty(a5)) {
                            this.f9608a.n = this.e.h(com.unionpay.mobile.android.utils.c.b(a5));
                        }
                        if (this.f9608a.aj == null || this.f9608a.aj.length() <= 0) {
                            super.b(2);
                            return;
                        }
                        this.u = 20;
                        s();
                        return;
                    case 102:
                        this.b.c();
                        try {
                            this.I = (String) jSONObject2.get("encrypt_key");
                            this.J = (String) jSONObject2.get("auth_id");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        int a6 = f.a(this.f9608a, jSONObject2, false);
                        this.p = f.a(jSONObject);
                        if (a6 != 0) {
                            super.b(a6);
                            return;
                        }
                        this.M = false;
                        this.S.setBackgroundColor(-1052684);
                        setBackgroundColor(-1052684);
                        this.F.setBackgroundColor(com.unionpay.mobile.android.global.a.M);
                        this.F.a(8);
                        this.A.removeAllViews();
                        this.B.setVisibility(8);
                        this.m.setBackgroundColor(-1052684);
                        this.l.setVisibility(0);
                        this.H.setVisibility(8);
                        this.D.setVisibility(8);
                        new LinearLayout.LayoutParams(-1, -2);
                        JSONArray jSONArray = new JSONArray();
                        if (this.p != null) {
                            com.unionpay.mobile.android.model.f fVar = (com.unionpay.mobile.android.model.f) this.p;
                            jSONArray.put(fVar.a("promotion"));
                            jSONArray.put(fVar.a("instalment"));
                            this.f9608a.aU = fVar.a("promotion_instalment_msgbox");
                        }
                        if (jSONArray.length() > 0) {
                            this.O = new a(this.d, jSONArray, this, this.q);
                            this.O.a(this.b, this.f9608a.aU);
                            this.O.a(this.U);
                            this.O.b(this.V);
                            this.O.c(this.W);
                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                            layoutParams.topMargin = com.unionpay.mobile.android.global.a.f;
                            this.A.addView(this.O, layoutParams);
                        }
                        if (this.O != null) {
                            this.O.c("instalment");
                        }
                        this.y = new a(this.d, this.f9608a.z, this.e.c(), this, this.af.get("AN1"), true, this.q);
                        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
                        layoutParams2.topMargin = com.unionpay.mobile.android.global.a.f;
                        this.A.addView(this.y, layoutParams2);
                        LinearLayout linearLayout = new LinearLayout(this.d);
                        linearLayout.setOrientation(1);
                        linearLayout.setId(linearLayout.hashCode());
                        new LinearLayout.LayoutParams(-1, -2);
                        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
                        layoutParams3.topMargin = com.unionpay.mobile.android.global.a.f;
                        layoutParams3.leftMargin = g.a(this.d, 10.0f);
                        this.A.addView(linearLayout, layoutParams3);
                        if (!(!h() && this.f9608a.al == null && this.f9608a.am == null)) {
                            if (this.f9608a.al != null) {
                                Context context = this.d;
                                JSONObject jSONObject3 = this.f9608a.al;
                                View.OnClickListener onClickListener = this.ab;
                                this.L = new com.unionpay.mobile.android.upwidget.a(context, jSONObject3, onClickListener, this.q + "_agree_user_protocol");
                                linearLayout.addView(this.L);
                            }
                            if (this.f9608a.am != null) {
                                Context context2 = this.d;
                                JSONObject jSONObject4 = this.f9608a.am;
                                this.K = new com.unionpay.mobile.android.upwidget.a(context2, jSONObject4, (View.OnClickListener) null, this.q + "_remember_bankNO");
                                LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-2, -2);
                                layoutParams4.topMargin = com.unionpay.mobile.android.global.a.f;
                                linearLayout.addView(this.K, layoutParams4);
                            }
                        }
                        new LinearLayout.LayoutParams(-1, -2);
                        this.w = new TextView(this.d);
                        this.w.setText(j.a(this.f9608a.C, "label"));
                        this.w.setTextSize(com.unionpay.mobile.android.global.b.i);
                        this.w.setTextColor(o());
                        this.w.setGravity(17);
                        TextView textView = this.w;
                        if (this.y != null && !this.y.e()) {
                            z2 = false;
                        }
                        textView.setEnabled(z2);
                        int i2 = com.unionpay.mobile.android.global.a.n;
                        this.w.setBackgroundDrawable(this.c.a(2008, -1, -1));
                        this.w.setOnClickListener(this.T);
                        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-1, i2);
                        layoutParams5.topMargin = com.unionpay.mobile.android.global.a.f;
                        int a7 = g.a(this.d, 10.0f);
                        layoutParams5.rightMargin = a7;
                        layoutParams5.leftMargin = a7;
                        this.A.addView(this.w, layoutParams5);
                        return;
                    case 103:
                        String a8 = j.a(jSONObject2, "status");
                        String a9 = j.a(jSONObject2, "fail_msg");
                        if (this.u <= 0 || !a8.equalsIgnoreCase("01")) {
                            this.v = 100;
                            if (a8.equalsIgnoreCase("00")) {
                                i();
                                this.v = 100;
                                this.f9608a.H = j.d(jSONObject2, "result");
                                this.f9608a.P = j.a(jSONObject2, "openupgrade_flag");
                                this.f9608a.Q = j.a(jSONObject2, "temporary_pay_flag");
                                this.f9608a.R = j.a(jSONObject2, "temporary_pay_info");
                                this.f9608a.V = j.a(jSONObject2, "front_url");
                                this.f9608a.W = j.a(jSONObject2, "front_request");
                                this.f9608a.A = j.a(jSONObject2, "title");
                                this.f9608a.B = j.a(jSONObject2, "succ_info");
                                com.unionpay.mobile.android.nocard.utils.b.b(jSONObject2, this.f9608a);
                                com.unionpay.mobile.android.nocard.utils.b.a(jSONObject2, this.f9608a);
                                if (this.y != null) {
                                    this.y.f();
                                }
                                StringBuilder sb = new StringBuilder();
                                sb.append(this.q);
                                sb.append("_succeed");
                                if (this.f9608a.f) {
                                    this.f9608a.I.f = "success";
                                    j();
                                    return;
                                }
                                d(8);
                                return;
                            }
                            i();
                            if (a8.equalsIgnoreCase(Constant.RECHARGE_MODE_BUSINESS_OFFICE)) {
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append(this.q);
                                sb2.append("_fail");
                                String[] strArr = o.j;
                                String[] strArr2 = {a8, a9};
                                a(a9);
                                return;
                            } else if (this.u <= 0) {
                                a(this.f9608a.ak);
                                return;
                            } else {
                                return;
                            }
                        } else {
                            s();
                            return;
                        }
                    case 104:
                        try {
                            this.I = (String) jSONObject2.get("encrypt_key");
                            this.J = (String) jSONObject2.get("auth_id");
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                        if (this.f9608a.p != null) {
                            a.C0077a a10 = this.y.a();
                            if (!a10.a()) {
                                a(a10.b);
                                return;
                            }
                            this.v = 101;
                            b(this.y.a().b, t());
                            return;
                        }
                        return;
                    default:
                        return;
                }
        }
    }

    public final void a(boolean z2) {
        if (this.w != null) {
            this.w.setEnabled(!z2);
        }
    }

    /* access modifiers changed from: protected */
    public final void b() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        this.F = this.f9608a.aD ? new ay(this.d, c.bD.bo, this.c.a(1030, -1, -1), g.a(this.d, 20.0f), this) : new ay(getContext(), c.bD.bo, this);
        this.F.setBackgroundColor(-16686660);
        this.F.a(0);
        layoutParams.addRule(13, -1);
        this.k.addView(this.F, layoutParams);
    }

    public final void b(int i) {
        super.b(i);
    }

    /* access modifiers changed from: protected */
    public final void c() {
        setBackgroundColor(-11495169);
        this.l.setVisibility(8);
        this.S = new FrameLayout(this.d);
        this.m.addView(this.S, new RelativeLayout.LayoutParams(-1, -1));
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, (v() - com.unionpay.mobile.android.global.b.n) - (com.unionpay.mobile.android.global.b.n / 2));
        this.A = new LinearLayout(this.d);
        this.A.setId(this.A.hashCode());
        this.A.setOrientation(1);
        this.S.addView(this.A, layoutParams);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(com.unionpay.mobile.android.global.b.n * 2, com.unionpay.mobile.android.global.b.n * 2);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) this.d).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        layoutParams2.leftMargin = (displayMetrics.widthPixels / 2) - com.unionpay.mobile.android.global.b.n;
        layoutParams2.topMargin = (v() / 2) - (com.unionpay.mobile.android.global.b.n * 2);
        this.H = new ImageView(this.d);
        this.H.setBackgroundDrawable(this.c.a(1032, -1, -1));
        this.S.addView(this.H, layoutParams2);
        this.E = new LinearLayout(this.d);
        this.E.setBackgroundColor(-1342177280);
        this.S.addView(this.E, new FrameLayout.LayoutParams(-1, -1));
        this.C = new RelativeLayout(this.d);
        this.S.addView(this.C, new FrameLayout.LayoutParams(-1, -1));
        this.m.setBackgroundColor(-11495169);
        LinearLayout linearLayout = this.A;
        this.A.removeAllViews();
        this.G = new UPRadiationView(this.d);
        linearLayout.addView(this.G, new LinearLayout.LayoutParams(-1, -1));
        RelativeLayout relativeLayout = this.C;
        relativeLayout.setGravity(1);
        int a2 = g.a(this.d, 10.0f);
        this.B = new LinearLayout(this.d);
        this.B.setId(this.B.hashCode());
        this.B.setOrientation(0);
        int i = a2 * 2;
        this.B.setPadding(i, a2, i, a2);
        Drawable a3 = this.c.a(PhotoshopDirectory.F, -1, -1);
        this.B.setBackgroundDrawable(a3);
        String str = c.bD.bl;
        TextView textView = new TextView(this.d);
        textView.setTextColor(-1);
        textView.setText(str);
        textView.setTextSize(com.unionpay.mobile.android.global.b.k);
        this.B.addView(textView);
        String str2 = c.bD.bm;
        TextView textView2 = new TextView(this.d);
        textView2.setTextColor(-16729682);
        textView2.setText(str2);
        textView2.setTextSize(com.unionpay.mobile.android.global.b.k);
        textView2.setOnClickListener(this.aa);
        this.B.addView(textView2);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.addRule(10, -1);
        layoutParams3.topMargin = com.unionpay.mobile.android.global.b.n;
        relativeLayout.addView(this.B, layoutParams3);
        this.D = new LinearLayout(this.d);
        this.D.setId(this.D.hashCode());
        this.D.setOrientation(0);
        this.D.setGravity(17);
        this.D.setPadding(i, a2, i, a2);
        this.D.setBackgroundDrawable(a3);
        String str3 = c.bD.bn;
        TextView textView3 = new TextView(this.d);
        textView3.setTextColor(-1);
        textView3.setText(str3);
        textView3.setTextSize(com.unionpay.mobile.android.global.b.k);
        this.D.addView(textView3);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams4.addRule(12, -1);
        layoutParams4.bottomMargin = com.unionpay.mobile.android.global.b.n;
        relativeLayout.addView(this.D, layoutParams4);
    }

    public final void c(String str) {
    }

    public final void c(String str, String str2) {
    }

    public final boolean handleMessage(Message message) {
        if (message.obj == null) {
            return true;
        }
        Bundle bundle = (Bundle) message.obj;
        String string = bundle.getString("action_resp_code");
        String string2 = bundle.getString("action_resp_message");
        if (!"0000".equalsIgnoreCase(string)) {
            ResultCode.ERROR_INTERFACE_GET_CARD_INFO.equalsIgnoreCase(string);
            a(this.f9608a.ap, false);
            return true;
        } else if (string2 == null) {
            return true;
        } else {
            a(0, string2);
            return true;
        }
    }

    public final void k() {
        if (this.f9608a.aD) {
            this.b.a(new t(this), new u(this));
            this.b.a(c.bD.Y, c.bD.av, c.bD.W, c.bD.X);
        } else if (this.y != null && this.y.d()) {
        } else {
            if (!this.f9608a.K || !this.x) {
                this.f9608a.K = false;
                this.M = false;
                a(2);
                return;
            }
            this.f9608a.K = false;
            m();
        }
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.y != null) {
            this.y.d();
        }
        if (this.G != null) {
            this.G.a();
        }
        this.G = null;
        this.b = null;
    }

    public final void r() {
    }
}
