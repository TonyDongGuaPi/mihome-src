package com.unionpay.mobile.android.pro.views;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.actions.SearchIntents;
import com.unionpay.mobile.android.hce.c;
import com.unionpay.mobile.android.model.d;
import com.unionpay.mobile.android.model.e;
import com.unionpay.mobile.android.model.f;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import com.unionpay.mobile.android.nocard.views.b;
import com.unionpay.mobile.android.upviews.a;
import com.unionpay.mobile.android.upwidget.UPScrollView;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.utils.i;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.utils.o;
import com.unionpay.mobile.android.widgets.ay;
import com.unionpay.mobile.android.widgets.z;
import com.unionpay.tsmservice.data.Constant;
import com.unionpay.tsmservice.data.ResultCode;
import com.unionpay.uppay.PayActivity;
import com.xiaomi.youpin.login.ui.web.LoginBindBaseWebActivity;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import java.security.MessageDigest;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import miuipub.reflect.Field;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class a extends b implements Handler.Callback, a.b {
    private static Date M = new Date(System.currentTimeMillis());
    private static String N = new SimpleDateFormat("yyyyMMddhhmmss").format(M);
    private static HashMap<String, String> P = new HashMap<>();
    private static HashMap<String, String> Q = new HashMap<>();
    private ay A;
    private String B;
    /* access modifiers changed from: private */
    public c C;
    /* access modifiers changed from: private */
    public int D = 5;
    /* access modifiers changed from: private */
    public String E;
    /* access modifiers changed from: private */
    public boolean F = false;
    private Handler.Callback G = new b(this);
    /* access modifiers changed from: private */
    public Handler H = new Handler(this.G);
    private View.OnClickListener I = new c(this);
    private View.OnClickListener J = new d(this);
    private View.OnClickListener K = new e(this);
    private View.OnClickListener L = new f(this);
    private String O = null;
    public String r = "1.9";
    UPPayEngine s;
    private int t = 20;
    /* access modifiers changed from: private */
    public int u = 100;
    private TextView v = null;
    private boolean w = false;
    /* access modifiers changed from: private */
    public com.unionpay.mobile.android.upviews.a x = null;
    private com.unionpay.mobile.android.upviews.a y = null;
    /* access modifiers changed from: private */
    public Handler z = null;

    public a(Context context, e eVar, UPPayEngine uPPayEngine) {
        super(context, eVar);
        this.f = 6;
        this.q = "hcepay";
        this.s = uPPayEngine;
        this.z = new Handler(this);
        this.C = (c) com.unionpay.mobile.android.model.b.bb.get(this.f9608a.bc);
        this.w = this.f9608a.K;
        setBackgroundColor(-1052684);
        e();
    }

    private void a(LinearLayout linearLayout) {
        z c = this.y != null ? this.y.c("instalment") : null;
        int length = this.f9608a.z.length();
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < length; i++) {
            Object b = j.b(this.f9608a.z, i);
            if (b != null) {
                try {
                    JSONObject jSONObject = (JSONObject) b;
                    if ("pan".equals(j.a(jSONObject, "type"))) {
                        jSONObject.put("label", this.C.b() + this.C.c());
                    }
                    jSONArray.put(jSONObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        this.x = new com.unionpay.mobile.android.upviews.a(this.d, jSONArray, this.e.c(), this, this.C.a(), true, false, c, this.f9608a.ad, this.q);
        linearLayout.addView(this.x, new LinearLayout.LayoutParams(-1, -2));
    }

    static /* synthetic */ void a(a aVar, String str, String str2) {
        aVar.u = 8;
        aVar.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        aVar.e.c(str, str2);
    }

    static /* synthetic */ void a(a aVar, String str, HashMap hashMap) {
        Object a2 = ((PayActivity) aVar.d).a(com.unionpay.mobile.android.pro.pboc.engine.b.class.toString());
        if ((a2 != null ? (com.unionpay.mobile.android.pro.pboc.engine.b) a2 : null) == null) {
            aVar.b(5);
        } else {
            new Thread(new g(aVar, str, hashMap)).start();
        }
    }

    private static void a(StringBuffer stringBuffer) {
        for (String next : P.keySet()) {
            String str = P.get(next);
            if (!TextUtils.isEmpty(str)) {
                String a2 = com.unionpay.mobile.android.pboctransaction.e.a(new byte[]{(byte) (str.length() / 2)}, 1);
                stringBuffer.append(next);
                stringBuffer.append(a2);
                stringBuffer.append(str);
            }
        }
    }

    private boolean b(HashMap<String, String> hashMap) {
        String str;
        String substring = N.substring(2, 8);
        long time = new Date(System.currentTimeMillis()).getTime();
        String valueOf = String.valueOf(time);
        if (valueOf.length() < 8) {
            str = String.format("%08d", new Object[]{Long.valueOf(time)});
        } else {
            str = valueOf.substring(valueOf.length() - 8, valueOf.length());
        }
        P.put("9F26", "");
        P.put("9F27", "80");
        P.put("9F10", "");
        P.put("9F37", str);
        P.put("9F36", "");
        P.put("95", "0000000800");
        P.put("9A", substring);
        P.put("9C", "45");
        P.put("9F02", Constant.DEFAULT_BALANCE);
        P.put("5F2A", "0156");
        P.put("82", "");
        P.put("9F1A", "0156");
        P.put("9F03", Constant.DEFAULT_BALANCE);
        P.put("9F33", "A04000");
        P.put("9F34", "420300");
        P.put("9F35", "34");
        P.put("9F1E", "3030303030303030");
        P.put("84", "");
        P.put("9F09", "0001");
        P.put("9F41", "");
        P.put("91", "");
        P.put("71", "");
        P.put("72", "");
        P.put("DF31", "");
        P.put("9F74", "");
        P.put("9F63", "");
        P.put("8A", "");
        Q.put("9F66", "26C00000");
        t();
        P.put("9F02", hashMap.get("trans_amt"));
        P.put("9F1A", "0156");
        P.put("5F2A", hashMap.get("trans currcy code"));
        P.put("9C", hashMap.get("trans_type"));
        Q.put("CUR", P.get("5F2A"));
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("9F66", Q.get("9F66"));
            jSONObject.put("9F02", P.get("9F02"));
            jSONObject.put("9F03", P.get("9F03"));
            jSONObject.put("9F1A", P.get("9F1A"));
            jSONObject.put("95", P.get("95"));
            jSONObject.put("5F2A", P.get("5F2A"));
            jSONObject.put("9A", P.get("9A"));
            jSONObject.put("9C", P.get("9C"));
            jSONObject.put("9F37", P.get("9F37"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String f = this.C.f();
        try {
            this.C.g().a(com.unionpay.mobile.android.hce.a.b(this.C.a(), f), com.unionpay.mobile.android.hce.a.b(jSONObject.toString(), f), "", new com.unionpay.mobile.android.hce.b(2004, "", this.H));
            this.H.sendMessageDelayed(this.H.obtainMessage(2006), (long) this.f9608a.be);
            return true;
        } catch (RemoteException e2) {
            e2.printStackTrace();
            a(this.f9608a.ap, false);
            return false;
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

    private static String d(String str, String str2) {
        byte[] a2 = com.unionpay.mobile.android.pboctransaction.e.a(str);
        byte[] a3 = com.unionpay.mobile.android.pboctransaction.e.a(str2);
        for (int i = 0; i < a2.length; i++) {
            a2[i] = (byte) (a2[i] ^ a3[i]);
        }
        return com.unionpay.mobile.android.pboctransaction.e.a(a2);
    }

    static /* synthetic */ boolean d(JSONObject jSONObject) {
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            String a2 = j.a(jSONObject, next);
            if (!TextUtils.isEmpty(a2)) {
                (("5F34".equals(next) || "57".equals(next) || "9F6C".equals(next) || "9F5D".equals(next) || "5F20".equals(next)) ? Q : P).put(next, a2);
            }
        }
        String str = P.get("9F10");
        return TextUtils.isEmpty(str) || ((byte) (com.unionpay.mobile.android.pboctransaction.e.a(str)[4] & 48)) == 32;
    }

    /* access modifiers changed from: private */
    public void e(String str, String str2) {
        this.u = 9;
        if (TextUtils.isEmpty(str2)) {
            this.e.c(str, "");
        } else {
            this.e.a(str, "\"uuid\":\"" + str2 + "\"", 10);
        }
        this.D--;
    }

    static /* synthetic */ HashMap f(a aVar) {
        HashMap<String, String> hashMap = new HashMap<>();
        if (aVar.x != null) {
            hashMap = aVar.x.c();
        }
        if (aVar.y != null) {
            HashMap<String, String> c = aVar.y.c();
            if (c == null || hashMap == null) {
                return (c == null || hashMap != null) ? hashMap : c;
            }
            hashMap.putAll(c);
        }
    }

    static /* synthetic */ void p(a aVar) {
        aVar.u = 104;
        aVar.j = false;
        aVar.b.a(com.unionpay.mobile.android.languages.c.bD.U);
        k.c("uppay", "");
        aVar.e.c("cardsecret", "");
    }

    private void s() {
        this.u = 103;
        this.e.a(SearchIntents.EXTRA_QUERY, this.f9608a.aj, 3);
        this.t--;
    }

    private static Bundle t() {
        Bundle bundle = new Bundle();
        bundle.putString("action_resp_code", "0000");
        return bundle;
    }

    public final synchronized Bundle a(String str, HashMap<String, String> hashMap) {
        Bundle t2;
        t2 = t();
        Q.put("PIN", str);
        Q.put("AN1", this.C.a());
        StringBuffer stringBuffer = new StringBuffer();
        a(stringBuffer);
        Q.put("DCD", stringBuffer.toString());
        this.B = d(this.f9608a.bf, this.s.b());
        if (Q.get("5F34") != null) {
            StringBuffer stringBuffer2 = new StringBuffer(Q.get("5F34"));
            stringBuffer2.insert(0, "0");
            Q.put("5F34", stringBuffer2.toString());
        } else {
            Q.put("5F34", "");
        }
        if (Q.get("57") != null) {
            String upperCase = Q.get("57").toUpperCase();
            while (true) {
                if (!upperCase.substring(upperCase.length() - 1, upperCase.length()).equalsIgnoreCase("f")) {
                    if (!upperCase.substring(upperCase.length() - 1, upperCase.length()).equalsIgnoreCase(Field.g)) {
                        break;
                    }
                }
                upperCase = upperCase.substring(0, upperCase.length() - 1);
            }
            Q.put("TD2", upperCase.toString());
            int indexOf = upperCase.indexOf(Field.h);
            String substring = upperCase.substring(0, indexOf);
            if (substring.endsWith(Field.g) || substring.endsWith("f")) {
                substring = substring.substring(0, substring.length() - 1);
            }
            Q.put("AN1", substring);
            Q.put("ED", upperCase.substring(indexOf + 1, indexOf + 5));
        }
        Q.put("AMT", P.get("9F02"));
        String str2 = (((("" + "pan=" + Q.get("AN1")) + "&pin=" + Q.get("PIN")) + "&icc_data=" + Q.get("DCD")) + "&card_seq_id=" + Q.get("5F34")) + "&auth_id=" + this.f9608a.bg;
        k.c("mac", str2);
        String d = d(str2);
        k.c("md5", d);
        String g = this.e.g(d);
        k.c(DTransferConstants.n, g);
        String a2 = this.e.a(str2 + com.alipay.sdk.sys.a.b + g, this.B);
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
            jSONObject2.put("carrier_tp", "9");
            jSONObject2.put("carrier_app_tp", "0");
            jSONObject2.put("sign", g);
            jSONObject2.put("pan", Q.get("AN1"));
            if (Q.get("ED") != null) {
                jSONObject2.put("expire", Q.get("ED"));
            }
            if (Q.get("TD2") != null) {
                jSONObject2.put("track2_data", Q.get("TD2"));
            }
            if (!(hashMap == null || hashMap.keySet() == null || hashMap.keySet().size() <= 0)) {
                hashMap.remove("pan");
                hashMap.remove(Constant.KEY_PIN);
                for (String next : hashMap.keySet()) {
                    jSONObject2.put(next, hashMap.get(next));
                }
            }
            t2.putString("action_resp_message", this.s.a(jSONObject.toString()));
        } catch (JSONException unused) {
            t2.putString("action_resp_code", ResultCode.ERROR_INTERFACE_GET_SMS_AUTH_CODE);
            return t2;
        }
        return t2;
    }

    public final void a(a.C0077a aVar) {
    }

    public final void a(JSONObject jSONObject) {
        switch (this.u) {
            case 8:
                i();
                JSONArray d = j.d(jSONObject, "options");
                if (this.y != null) {
                    this.y.a(d);
                    return;
                }
                return;
            case 9:
                String a2 = j.a(jSONObject, "status");
                if (a2 == null || !"01".equals(a2)) {
                    JSONArray d2 = j.d(jSONObject, "options");
                    String a3 = j.a(jSONObject, "empty_info");
                    if (this.y != null) {
                        this.y.a(d2, a3);
                        return;
                    }
                    return;
                }
                String a4 = j.a(jSONObject, "uuid");
                if (this.D >= 0) {
                    e(this.E, a4);
                    return;
                }
                String str = com.unionpay.mobile.android.languages.c.bD.D;
                if (this.y != null) {
                    this.y.a((JSONArray) null, str);
                    return;
                }
                return;
            case 101:
                this.f9608a.aj = i.a(jSONObject.toString());
                String a5 = j.a(jSONObject, "qn");
                if (!TextUtils.isEmpty(a5)) {
                    this.f9608a.n = this.e.h(com.unionpay.mobile.android.utils.c.b(a5));
                }
                if (this.f9608a.aj == null || this.f9608a.aj.length() <= 0) {
                    b(2);
                    return;
                }
                this.t = 20;
                s();
                return;
            case 103:
                String a6 = j.a(jSONObject, "status");
                String a7 = j.a(jSONObject, "fail_msg");
                if (this.t <= 0 || !a6.equalsIgnoreCase("01")) {
                    this.u = 100;
                    if (a6.equalsIgnoreCase("00")) {
                        i();
                        this.u = 100;
                        this.f9608a.H = j.d(jSONObject, "result");
                        this.f9608a.P = j.a(jSONObject, "openupgrade_flag");
                        this.f9608a.Q = j.a(jSONObject, "temporary_pay_flag");
                        this.f9608a.R = j.a(jSONObject, "temporary_pay_info");
                        this.f9608a.V = j.a(jSONObject, "front_url");
                        this.f9608a.W = j.a(jSONObject, "front_request");
                        this.f9608a.A = j.a(jSONObject, "title");
                        this.f9608a.B = j.a(jSONObject, "succ_info");
                        com.unionpay.mobile.android.nocard.utils.b.b(jSONObject, this.f9608a);
                        com.unionpay.mobile.android.nocard.utils.b.a(jSONObject, this.f9608a);
                        if (this.x != null) {
                            this.x.f();
                        }
                        StringBuilder sb = new StringBuilder();
                        sb.append(this.q);
                        sb.append("_succeed");
                        Iterator<d> it = com.unionpay.mobile.android.model.b.bb.iterator();
                        while (it.hasNext()) {
                            try {
                                this.d.unbindService(((c) it.next()).h());
                            } catch (IllegalArgumentException unused) {
                            }
                        }
                        if (this.f9608a.f) {
                            this.f9608a.I.f = "success";
                            j();
                            return;
                        }
                        d(8);
                        return;
                    }
                    i();
                    this.F = true;
                    if (a6.equalsIgnoreCase(Constant.RECHARGE_MODE_BUSINESS_OFFICE)) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(this.q);
                        sb2.append("_fail");
                        String[] strArr = o.j;
                        String[] strArr2 = {a6, a7};
                        a(a7);
                        return;
                    } else if (this.t <= 0) {
                        b(19);
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
                    this.f9608a.bf = (String) jSONObject.get("encrypt_key");
                    this.f9608a.bg = (String) jSONObject.get("auth_id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (this.f9608a.p != null) {
                    a.C0077a a8 = this.x.a();
                    if (!a8.a()) {
                        this.F = true;
                        a(a8.b);
                        return;
                    }
                    this.u = 101;
                    a(this.f9608a.p);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public final void a(boolean z2) {
        if (this.v != null) {
            this.v.setEnabled(!z2);
        }
    }

    public final boolean a(HashMap<String, String> hashMap) {
        M = new Date(System.currentTimeMillis());
        N = new SimpleDateFormat("yyyyMMddHHmmss").format(M);
        this.O = new String(N);
        return b(hashMap);
    }

    /* access modifiers changed from: protected */
    public final void b() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        this.A = new ay(getContext(), com.unionpay.mobile.android.languages.c.bD.bq, this);
        layoutParams.addRule(13, -1);
        this.k.addView(this.A, layoutParams);
    }

    public final void b(int i) {
        switch (this.u) {
            case 101:
            case 103:
            case 104:
                this.F = true;
                break;
        }
        super.b(i);
    }

    /* access modifiers changed from: protected */
    public final void c() {
        this.m.removeAllViews();
        this.o.a((UPScrollView.a) this);
        LinearLayout linearLayout = new LinearLayout(this.d);
        boolean z2 = true;
        linearLayout.setOrientation(1);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = com.unionpay.mobile.android.global.a.f;
        layoutParams.addRule(10, -1);
        this.m.addView(linearLayout, layoutParams);
        JSONArray jSONArray = new JSONArray();
        if (this.p != null) {
            f fVar = (f) this.p;
            jSONArray.put(fVar.a("promotion"));
            jSONArray.put(fVar.a("instalment"));
            this.f9608a.aU = fVar.a("promotion_instalment_msgbox");
        }
        if (jSONArray.length() > 0) {
            this.y = new com.unionpay.mobile.android.upviews.a(this.d, jSONArray, this, this.q);
            this.y.a(this.I);
            this.y.b(this.J);
            this.y.c(this.K);
            this.y.a(this.b, this.f9608a.aU);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
            layoutParams2.bottomMargin = com.unionpay.mobile.android.global.a.f;
            linearLayout.addView(this.y, layoutParams2);
        }
        a(linearLayout);
        new LinearLayout.LayoutParams(-1, -2);
        this.v = new TextView(this.d);
        this.v.setText(j.a(this.f9608a.C, "label"));
        this.v.setTextSize(com.unionpay.mobile.android.global.b.i);
        this.v.setTextColor(o());
        this.v.setGravity(17);
        TextView textView = this.v;
        if (this.x != null && !this.x.e()) {
            z2 = false;
        }
        textView.setEnabled(z2);
        int i = com.unionpay.mobile.android.global.a.n;
        this.v.setBackgroundDrawable(this.c.a(2008, -1, -1));
        this.v.setOnClickListener(this.L);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, i);
        layoutParams3.topMargin = com.unionpay.mobile.android.global.a.f;
        int a2 = g.a(this.d, 10.0f);
        layoutParams3.rightMargin = a2;
        layoutParams3.leftMargin = a2;
        linearLayout.addView(this.v, layoutParams3);
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
        if (this.x != null && this.x.d()) {
            return;
        }
        if (!this.f9608a.K || !this.w) {
            this.f9608a.K = false;
            a(2);
            return;
        }
        this.f9608a.K = false;
        m();
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.x != null) {
            this.x.d();
        }
        this.b = null;
    }

    public final void r() {
    }
}
