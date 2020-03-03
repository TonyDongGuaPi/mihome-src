package com.xiaomi.smarthome.smartconfig.step;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.xiaomi.miio.MiioLocalAPI;
import com.xiaomi.miio.MiioLocalErrorCode;
import com.xiaomi.miio.MiioLocalRpcResponse;
import com.xiaomi.miio.MiioLocalRpcResult;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.plugin.host.PluginHostApi;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.step.ApSecureQRStep;
import java.util.Random;
import org.json.JSONObject;

public class ApSecureQRStep {

    /* renamed from: a  reason: collision with root package name */
    private static final String f22451a = "ApSecureQRStep";
    /* access modifiers changed from: private */
    public static final String b = new MiioLocalRpcResult(MiioLocalErrorCode.EXCEPTION).a();
    /* access modifiers changed from: private */
    public byte[] c;
    private long d;
    private String e;
    /* access modifiers changed from: private */
    public String f;
    /* access modifiers changed from: private */
    public ApSecureQRCallback g;
    /* access modifiers changed from: private */
    public boolean h = false;

    public interface ApSecureQRCallback {
        void a();

        void a(String str);
    }

    public ApSecureQRStep(long j, String str, byte[] bArr, ApSecureQRCallback apSecureQRCallback) {
        this.d = j;
        this.e = str;
        this.c = bArr;
        this.g = apSecureQRCallback;
    }

    public void a(String str) {
        a(ByteUtils.a(str));
    }

    public void a() {
        this.h = true;
        this.g = null;
    }

    /* access modifiers changed from: private */
    public void a(final byte[] bArr) {
        if (!this.h) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("id", ((PluginHostApi) PluginHostApi.instance()).generateNonce());
                jSONObject.put("method", APSecurePinStep.f22386a);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("type", 3);
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("step", 1);
                jSONObject3.put("mode", 3);
                jSONObject2.put("oob", jSONObject3);
                jSONObject.put("params", jSONObject2);
                MiioLocalAPI.a(c(), jSONObject.toString(), this.d, this.e, (MiioLocalRpcResponse) new MiioLocalRpcResponse.MiioLocalRpcResponseSimple() {
                    public void a(JSONObject jSONObject) {
                        LogUtilGrey.a(ApSecureQRStep.f22451a, "oobGetDevSign, response= " + jSONObject);
                        String unused = ApSecureQRStep.this.f = jSONObject.optJSONObject("oob").optString("sign");
                        ApSecureQRStep.this.b(bArr);
                    }

                    public void a(int i, String str, Throwable th) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("oobGetDevSign onfail:");
                        sb.append(str == null ? Log.getStackTraceString(th) : str.toString());
                        LogUtilGrey.a(ApSecureQRStep.f22451a, sb.toString());
                        if (!ApSecureQRStep.this.h) {
                            SHApplication.getGlobalHandler().postDelayed(new Runnable(bArr) {
                                private final /* synthetic */ byte[] f$1;

                                {
                                    this.f$1 = r2;
                                }

                                public final void run() {
                                    ApSecureQRStep.AnonymousClass1.this.a(this.f$1);
                                }
                            }, 1000);
                        }
                    }

                    /* access modifiers changed from: private */
                    public /* synthetic */ void a(byte[] bArr) {
                        ApSecureQRStep.this.a(bArr);
                    }
                }, 5000);
            } catch (Exception e2) {
                LogUtilGrey.a(f22451a, Log.getStackTraceString(e2));
                if (this.g != null) {
                    this.g.a();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void b(final byte[] bArr) {
        final byte[] bArr2 = new byte[16];
        new Random().nextBytes(bArr2);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", ((PluginHostApi) PluginHostApi.instance()).generateNonce());
            jSONObject.put("method", APSecurePinStep.f22386a);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("type", 3);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("step", 2);
            byte[] a2 = APSecurePinStep.a(this.c, bArr, bArr2);
            jSONObject3.put("sign", Base64.encodeToString(a2, 2));
            jSONObject2.put("oob", jSONObject3);
            jSONObject.put("params", jSONObject2);
            String jSONObject4 = jSONObject.toString();
            String c2 = c();
            LogUtilGrey.a(f22451a, "oobGetRandomDev, gatewayAddr:" + c2 + " request= " + jSONObject4 + "  sign:" + ByteUtils.d(a2) + "  randomByte:" + ByteUtils.d(bArr2));
            MiioLocalAPI.a(c2, jSONObject4, this.d, this.e, (MiioLocalRpcResponse) new MiioLocalRpcResponse.MiioLocalRpcResponseSimple() {
                public void a(JSONObject jSONObject) {
                    String encodeToString = Base64.encodeToString(APSecurePinStep.a(ApSecureQRStep.this.c, bArr, Base64.decode(jSONObject.optJSONObject("oob").optString("random"), 2)), 2);
                    LogUtilGrey.a(ApSecureQRStep.f22451a, "oobGetRandomDev, response= " + jSONObject + "  deviceSign:" + ApSecureQRStep.this.f + "  appSign:" + encodeToString);
                    if (TextUtils.equals(ApSecureQRStep.this.f, encodeToString)) {
                        ApSecureQRStep.this.b(Base64.encodeToString(bArr2, 2));
                    } else if (ApSecureQRStep.this.g != null) {
                        ApSecureQRStep.this.g.a();
                    }
                }

                public void a(int i, String str, Throwable th) {
                    if (!ApSecureQRStep.this.h) {
                        if (ApSecureQRStep.b.equals(str)) {
                            LogUtilGrey.a(ApSecureQRStep.f22451a, str + Log.getStackTraceString(th));
                            SHApplication.getGlobalHandler().postDelayed(new Runnable(bArr) {
                                private final /* synthetic */ byte[] f$1;

                                {
                                    this.f$1 = r2;
                                }

                                public final void run() {
                                    ApSecureQRStep.AnonymousClass2.this.a(this.f$1);
                                }
                            }, 1000);
                            return;
                        }
                        LogUtilGrey.a(ApSecureQRStep.f22451a, "oobGetRandomDev, onFail= " + str.toString());
                        if (ApSecureQRStep.this.g != null) {
                            ApSecureQRStep.this.g.a();
                        }
                    }
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void a(byte[] bArr) {
                    ApSecureQRStep.this.b(bArr);
                }
            }, 5000);
        } catch (Exception e2) {
            LogUtilGrey.a(f22451a, Log.getStackTraceString(e2));
            if (this.g != null) {
                this.g.a();
            }
        }
    }

    /* access modifiers changed from: private */
    public void b(final String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", ((PluginHostApi) PluginHostApi.instance()).generateNonce());
            jSONObject.put("method", APSecurePinStep.f22386a);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("type", 3);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("step", 3);
            jSONObject3.put("random", str);
            jSONObject2.put("oob", jSONObject3);
            jSONObject.put("params", jSONObject2);
            String c2 = c();
            String jSONObject4 = jSONObject.toString();
            LogUtilGrey.a(f22451a, "oobGetIV, gatewayAddr:" + c2 + " request= " + jSONObject4);
            MiioLocalAPI.a(c2, jSONObject4, this.d, this.e, (MiioLocalRpcResponse) new MiioLocalRpcResponse.MiioLocalRpcResponseSimple() {
                public void a(JSONObject jSONObject) {
                    LogUtilGrey.a(ApSecureQRStep.f22451a, "oobGetIV, response= " + jSONObject);
                    SmartConfigDataProvider.a().b(SmartConfigDataProvider.l, jSONObject.optJSONObject("oob").optString("iv"));
                    if (ApSecureQRStep.this.g != null) {
                        ApSecureQRStep.this.g.a(jSONObject.optJSONObject("oob").optString("iv"));
                    }
                }

                public void a(int i, String str, Throwable th) {
                    if (!ApSecureQRStep.this.h) {
                        if (ApSecureQRStep.b.equals(str)) {
                            LogUtilGrey.a(ApSecureQRStep.f22451a, Log.getStackTraceString(th));
                            SHApplication.getGlobalHandler().postDelayed(new Runnable(str) {
                                private final /* synthetic */ String f$1;

                                {
                                    this.f$1 = r2;
                                }

                                public final void run() {
                                    ApSecureQRStep.AnonymousClass3.this.a(this.f$1);
                                }
                            }, 1000);
                            return;
                        }
                        if (ApSecureQRStep.this.g != null) {
                            ApSecureQRStep.this.g.a();
                        }
                        LogUtilGrey.a(ApSecureQRStep.f22451a, "oobGetIV, onFail= " + str.toString());
                    }
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void a(String str) {
                    ApSecureQRStep.this.b(str);
                }
            }, 5000);
        } catch (Exception e2) {
            LogUtilGrey.a(f22451a, Log.getStackTraceString(e2));
            if (this.g != null) {
                this.g.a();
            }
        }
    }

    private String c() {
        Context appContext = SHApplication.getAppContext();
        if (appContext == null) {
            return "";
        }
        return a((long) ((WifiManager) appContext.getSystemService("wifi")).getDhcpInfo().gateway);
    }

    private String a(long j) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.valueOf((int) (j & 255)));
        stringBuffer.append('.');
        stringBuffer.append(String.valueOf((int) ((j >> 8) & 255)));
        stringBuffer.append('.');
        stringBuffer.append(String.valueOf((int) ((j >> 16) & 255)));
        stringBuffer.append('.');
        stringBuffer.append(String.valueOf((int) ((j >> 24) & 255)));
        return stringBuffer.toString();
    }
}
