package com.megvii.livenessdetection;

import android.content.Context;
import com.megvii.licensemanager.ILicenseManager;
import com.megvii.livenessdetection.obf.b;
import com.megvii.livenessdetection.obf.c;
import com.megvii.livenessdetection.obf.e;
import java.security.InvalidParameterException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LivenessLicenseManager implements ILicenseManager {

    /* renamed from: a  reason: collision with root package name */
    private Context f6681a;
    private e b;

    private native String nativeCheckLicense(Context context, String str);

    private native String nativeGenAuthMsg(Context context, String str, String str2, String str3, String str4, String str5, String str6);

    public LivenessLicenseManager(Context context) {
        if (context != null) {
            this.f6681a = context.getApplicationContext();
            c.a(this.f6681a).a("livenessdetection", "v2.4.5");
            this.b = new e(this.f6681a);
            return;
        }
        throw new InvalidParameterException("mContext cannot be null");
    }

    public String getContext(String str) {
        int i;
        JSONObject a2 = b.a();
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis());
        String sb2 = sb.toString();
        this.b.a("809bd36cf78612fd1f11b739c382bfac", b.a(this.f6681a.getPackageName().getBytes()));
        this.b.a("37dbd151eb3ca24477bc27cf0febcbe3", sb2);
        if (str != null && !str.isEmpty()) {
            this.b.a("cb072839e1e240a23ccc123ca6cf165", str);
        }
        String a3 = this.b.a("cb072839e1e240a23baae123ca6cf165");
        Context context = this.f6681a;
        String packageName = this.f6681a.getPackageName();
        String jSONObject = a2.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(System.currentTimeMillis() / 1000);
        try {
            JSONObject jSONObject2 = new JSONObject(nativeGenAuthMsg(context, packageName, str, jSONObject, sb3.toString(), a(this.f6681a), a3));
            String string = jSONObject2.getString("auth");
            String string2 = jSONObject2.getString("seed");
            String string3 = jSONObject2.getString("key");
            try {
                i = Integer.parseInt(this.b.a("5f389fef5fd41c84a33a91c6574cbf51"));
            } catch (Exception unused) {
                i = 0;
            }
            e eVar = this.b;
            StringBuilder sb4 = new StringBuilder();
            sb4.append(i + 1);
            eVar.a("5f389fef5fd41c84a33a91c6574cbf51", sb4.toString());
            this.b.a("b62f7aea9613b98976498a9ecabe537b", string3);
            if (!string2.equals(a3)) {
                this.b.a("cb072839e1e240a23baae123ca6cf165", string2);
            }
            return string;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public long setLicense(String str) {
        long j;
        if (this.f6681a == null || str == null || str.length() == 0) {
            return 0;
        }
        this.f6681a = this.f6681a.getApplicationContext();
        if (this.b.a("b62f7aea9613b98976498a9ecabe537b") == null || this.b.a("cb072839e1e240a23baae123ca6cf165") == null) {
            return 0;
        }
        try {
            JSONObject jSONObject = new JSONObject(nativeCheckLicense(this.f6681a, this.b.a("cb072839e1e240a23baae123ca6cf165") + ":" + this.b.a("b62f7aea9613b98976498a9ecabe537b") + ":" + str));
            j = jSONObject.getLong("expire_time");
            try {
                int i = new JSONObject(jSONObject.getString("extra")).getInt("max_saved_log");
                e eVar = this.b;
                StringBuilder sb = new StringBuilder();
                sb.append(i);
                eVar.a("889109d126886bd98bc8f6a70d138545", sb.toString());
            } catch (Exception unused) {
            }
        } catch (Exception unused2) {
            j = 0;
        }
        if (j != 0) {
            e eVar2 = this.b;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(j);
            eVar2.a("a01625815f3428cb69100cc5d613fa7d", sb2.toString());
            this.b.a("e2380b201325a8f252636350338aeae8", this.b.a("b62f7aea9613b98976498a9ecabe537b") + ":" + str);
            this.b.a("bc8f6a70d138545889109d126886bd98", Detector.getVersion());
        }
        return j;
    }

    public long checkCachedLicense() {
        String a2 = this.b.a("a01625815f3428cb69100cc5d613fa7d");
        if (a2 == null || !Detector.getVersion().equals(this.b.a("bc8f6a70d138545889109d126886bd98"))) {
            return 0;
        }
        try {
            long parseLong = Long.parseLong(a2);
            if (System.currentTimeMillis() / 1000 > parseLong) {
                return 0;
            }
            return parseLong;
        } catch (Exception unused) {
            return 0;
        }
    }

    public String getVersion() {
        return Detector.getVersion();
    }

    private String a(Context context) {
        String b2;
        JSONArray jSONArray = new JSONArray();
        if (context == null) {
            return jSONArray.toString();
        }
        if (!"false".equals(this.b.a("49668163590f816aaf863df014568115")) && (b2 = this.b.b("8cd0604ba33e2ba7f38a56f0aec08a54")) != null) {
            try {
                jSONArray = new JSONArray(b2);
            } catch (Exception unused) {
            }
        }
        return jSONArray.toString();
    }

    static {
        try {
            System.loadLibrary("livenessdetection_v2.4.5");
        } catch (Exception unused) {
        }
    }
}
