package com.xiaomi.smarthome.smartconfig.step;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.xiaomi.miio.MiioLocalAPI;
import com.xiaomi.miio.MiioLocalErrorCode;
import com.xiaomi.miio.MiioLocalRpcResponse;
import com.xiaomi.miio.MiioLocalRpcResult;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.utils.SecurityChipUtil;
import com.xiaomi.smarthome.frame.plugin.host.PluginHostApi;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.page.verify.view.PinInputView;
import com.xiaomi.smarthome.framework.page.verify.view.PinSoftKeyboard;
import com.xiaomi.smarthome.library.common.util.ByteUtils;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.step.APSecurePinStep;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;

public class APSecurePinStep extends ApSecureConfigStep implements PinSoftKeyboard.OnPinSoftKeyboardClickListener {

    /* renamed from: a  reason: collision with root package name */
    public static final String f22386a = "miIO.handshake";
    private static final String ap = "APSecurePinStep";
    /* access modifiers changed from: private */
    public static final String aq = new MiioLocalRpcResult(MiioLocalErrorCode.EXCEPTION).a();
    private String ar = "";
    /* access modifiers changed from: private */
    public String as;
    /* access modifiers changed from: private */
    public byte[] at;
    /* access modifiers changed from: private */
    public View au;
    private int av = 0;
    private View aw;
    /* access modifiers changed from: private */
    public ImageView ax;
    PinSoftKeyboard b;
    View c;
    PinInputView d;
    View e;

    public void a(int i) {
    }

    public int b() {
        return -1;
    }

    public void c() {
    }

    public void d() {
    }

    public void e() {
    }

    public boolean a() {
        t();
        SHApplication.getGlobalHandler().post(new Runnable() {
            public void run() {
                APSecurePinStep.this.D();
            }
        });
        return true;
    }

    public void a(Message message) {
        if (U_() != null) {
            U_().removeMessages(message.what);
        }
        int i = message.what;
        if (i == 101) {
            LogUtilGrey.a(ap, "handleMessage:  NETWORK_STATE_CHANGED " + l());
            b(true);
        } else if (i == 123) {
            LogUtilGrey.a(ap, "handleMessage:  MSG_RECONNECT_DEVICE_AP " + l());
            super.a(message);
        }
    }

    public void a(Context context) {
        this.ae = LayoutInflater.from(context).inflate(R.layout.ble_secure_pin_code_layout, (ViewGroup) null);
        this.b = (PinSoftKeyboard) this.ae.findViewById(R.id.xiaomi_sm_pin_softkeyboard);
        this.c = this.ae.findViewById(R.id.cancel);
        this.d = (PinInputView) this.ae.findViewById(R.id.xiaomi_sm_pin_inputs);
        this.au = this.ae.findViewById(R.id.error_hint);
        this.e = this.ae.findViewById(R.id.ratio_container);
        this.aw = this.ae.findViewById(R.id.fl_hint);
        this.ax = (ImageView) this.ae.findViewById(R.id.loading);
        this.b.setClickListener(this);
        this.e.setVisibility(8);
        int intValue = ((Integer) SmartConfigDataProvider.a().a("length")).intValue();
        this.k = ((Long) SmartConfigDataProvider.a().a(SmartConfigDataProvider.J)).longValue();
        this.l = (String) SmartConfigDataProvider.a().a(SmartConfigDataProvider.G);
        this.at = (byte[]) SmartConfigDataProvider.a().a("sign");
        this.w = (WifiManager) this.af.getApplicationContext().getSystemService("wifi");
        this.x = (ConnectivityManager) this.af.getSystemService("connectivity");
        if (intValue < 6 && (this.d.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.d.getLayoutParams();
            marginLayoutParams.leftMargin = (marginLayoutParams.leftMargin * 3) / 2;
            marginLayoutParams.rightMargin = (marginLayoutParams.rightMargin * 3) / 2;
        }
        this.d.setSpacing(DisplayUtils.a(18.0f));
        this.d.setPincodeNumber(intValue);
        this.aw.setVisibility(0);
        this.au.setVisibility(4);
        this.ax.setVisibility(4);
        q();
        this.c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                APSecurePinStep.this.a();
            }
        });
    }

    public void onNumberClick(int i) {
        if (this.ax.getVisibility() != 0) {
            String add = this.d.add(i);
            this.au.setVisibility(4);
            if (add.length() >= this.d.getPincodeNumber()) {
                a(add);
            }
        }
    }

    public void onBackClick() {
        a();
    }

    public void onDeleteClick() {
        if (this.ax.getVisibility() != 0) {
            this.d.delete();
        }
    }

    /* access modifiers changed from: private */
    public void q() {
        if (!this.ag) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("id", ((PluginHostApi) PluginHostApi.instance()).generateNonce());
                jSONObject.put("method", f22386a);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("type", 3);
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("step", 1);
                jSONObject3.put("mode", 2);
                jSONObject2.put("oob", jSONObject3);
                jSONObject.put("params", jSONObject2);
                MiioLocalAPI.a(n(), jSONObject.toString(), this.k, this.l, (MiioLocalRpcResponse) new MiioLocalRpcResponse.MiioLocalRpcResponseSimple() {
                    public void a(JSONObject jSONObject) {
                        LogUtilGrey.a(APSecurePinStep.ap, "oobGetDevSign, response= " + jSONObject);
                        String unused = APSecurePinStep.this.as = jSONObject.optJSONObject("oob").optString("sign");
                        APSecurePinStep.this.s();
                    }

                    public void a(int i, String str, Throwable th) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("oobGetDevSign onfail:");
                        sb.append(str == null ? Log.getStackTraceString(th) : str.toString());
                        LogUtilGrey.a(APSecurePinStep.ap, sb.toString());
                        if (!APSecurePinStep.this.ag && !APSecurePinStep.this.ah) {
                            APSecurePinStep.this.U_().postDelayed(new Runnable() {
                                public final void run() {
                                    APSecurePinStep.AnonymousClass3.this.a();
                                }
                            }, 1000);
                        }
                    }

                    /* access modifiers changed from: private */
                    public /* synthetic */ void a() {
                        APSecurePinStep.this.q();
                    }
                }, 5000);
            } catch (Exception e2) {
                LogUtilGrey.a(ap, Log.getStackTraceString(e2));
                a();
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(final String str) {
        this.ax.post(new Runnable() {
            public void run() {
                APSecurePinStep.this.ax.setVisibility(0);
                Drawable drawable = APSecurePinStep.this.ax.getDrawable();
                if (drawable instanceof AnimationDrawable) {
                    ((AnimationDrawable) drawable).start();
                }
            }
        });
        final byte[] bytes = str.getBytes();
        final byte[] bArr = new byte[16];
        new Random().nextBytes(bArr);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", ((PluginHostApi) PluginHostApi.instance()).generateNonce());
            jSONObject.put("method", f22386a);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("type", 3);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("step", 2);
            byte[] a2 = a(this.at, bytes, bArr);
            jSONObject3.put("sign", Base64.encodeToString(a2, 2));
            jSONObject2.put("oob", jSONObject3);
            jSONObject.put("params", jSONObject2);
            String jSONObject4 = jSONObject.toString();
            String n = n();
            LogUtilGrey.a(ap, "oobGetRandomDev, gatewayAddr:" + n + " request= " + jSONObject4 + "  pincode:" + str + "  sign:" + ByteUtils.d(a2) + "  randomByte:" + ByteUtils.d(bArr));
            MiioLocalAPI.a(n, jSONObject4, this.k, this.l, (MiioLocalRpcResponse) new MiioLocalRpcResponse.MiioLocalRpcResponseSimple() {
                public void a(JSONObject jSONObject) {
                    String encodeToString = Base64.encodeToString(APSecurePinStep.a(APSecurePinStep.this.at, bytes, Base64.decode(jSONObject.optJSONObject("oob").optString("random"), 2)), 2);
                    LogUtilGrey.a(APSecurePinStep.ap, "oobGetRandomDev, response= " + jSONObject + "  deviceSign:" + APSecurePinStep.this.as + "  appSign:" + encodeToString);
                    if (TextUtils.equals(APSecurePinStep.this.as, encodeToString)) {
                        APSecurePinStep.this.b(Base64.encodeToString(bArr, 2));
                    } else {
                        APSecurePinStep.this.r();
                    }
                }

                public void a(int i, String str, Throwable th) {
                    if (!APSecurePinStep.this.ag && !APSecurePinStep.this.ah) {
                        if (APSecurePinStep.aq.equals(str)) {
                            LogUtilGrey.a(APSecurePinStep.ap, str + Log.getStackTraceString(th));
                            APSecurePinStep.this.U_().postDelayed(new Runnable(str) {
                                private final /* synthetic */ String f$1;

                                {
                                    this.f$1 = r2;
                                }

                                public final void run() {
                                    APSecurePinStep.AnonymousClass5.this.a(this.f$1);
                                }
                            }, 1000);
                            return;
                        }
                        LogUtilGrey.a(APSecurePinStep.ap, "oobGetRandomDev, onFail= " + str.toString());
                        APSecurePinStep.this.q();
                        APSecurePinStep.this.r();
                    }
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void a(String str) {
                    APSecurePinStep.this.a(str);
                }
            }, 5000);
        } catch (Exception e2) {
            LogUtilGrey.a(ap, Log.getStackTraceString(e2));
            a();
        }
    }

    /* access modifiers changed from: private */
    public void b(final String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", ((PluginHostApi) PluginHostApi.instance()).generateNonce());
            jSONObject.put("method", f22386a);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("type", 3);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("step", 3);
            jSONObject3.put("random", str);
            jSONObject2.put("oob", jSONObject3);
            jSONObject.put("params", jSONObject2);
            String n = n();
            String jSONObject4 = jSONObject.toString();
            LogUtilGrey.a(ap, "oobGetIV, gatewayAddr:" + n + " request= " + jSONObject4);
            MiioLocalAPI.a(n, jSONObject4, this.k, this.l, (MiioLocalRpcResponse) new MiioLocalRpcResponse.MiioLocalRpcResponseSimple() {
                public void a(JSONObject jSONObject) {
                    LogUtilGrey.a(APSecurePinStep.ap, "oobGetIV, response= " + jSONObject);
                    SmartConfigDataProvider.a().b(SmartConfigDataProvider.l, jSONObject.optJSONObject("oob").optString("iv"));
                    APSecurePinStep.this.a();
                }

                public void a(int i, String str, Throwable th) {
                    if (!APSecurePinStep.this.ag && !APSecurePinStep.this.ah) {
                        if (APSecurePinStep.aq.equals(str)) {
                            LogUtilGrey.a(APSecurePinStep.ap, Log.getStackTraceString(th));
                            APSecurePinStep.this.U_().postDelayed(new Runnable(str) {
                                private final /* synthetic */ String f$1;

                                {
                                    this.f$1 = r2;
                                }

                                public final void run() {
                                    APSecurePinStep.AnonymousClass6.this.a(this.f$1);
                                }
                            }, 1000);
                            return;
                        }
                        APSecurePinStep.this.r();
                        LogUtilGrey.a(APSecurePinStep.ap, "oobGetIV, onFail= " + str.toString());
                    }
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void a(String str) {
                    APSecurePinStep.this.b(str);
                }
            }, 5000);
        } catch (Exception e2) {
            LogUtilGrey.a(ap, Log.getStackTraceString(e2));
            a();
        }
    }

    /* access modifiers changed from: private */
    public void r() {
        int i = this.av;
        this.av = i + 1;
        if (i >= 2) {
            a();
            return;
        }
        this.au.post(new Runnable() {
            public void run() {
                APSecurePinStep.this.d.reset();
                APSecurePinStep.this.ax.setVisibility(4);
                APSecurePinStep.this.au.setVisibility(0);
            }
        });
        q();
    }

    public SmartConfigStep.Step f() {
        return SmartConfigStep.Step.STEP_APSECURE_PIN_STEP;
    }

    public static byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = new byte[(bArr3.length + bArr2.length)];
        System.arraycopy(bArr3, 0, bArr4, 0, bArr3.length);
        System.arraycopy(bArr2, 0, bArr4, bArr3.length, bArr2.length);
        return SecurityChipUtil.a(bArr, bArr4);
    }

    /* access modifiers changed from: private */
    public void s() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", ((PluginHostApi) PluginHostApi.instance()).generateNonce());
            jSONObject.put("method", "show_pin_code");
            jSONObject.put("params", "");
            MiioLocalAPI.a(n(), jSONObject.toString(), this.k, this.l, (MiioLocalRpcResponse) new MiioLocalRpcResponse.MiioLocalRpcResponseSimple() {
                public void a(JSONObject jSONObject) {
                    LogUtilGrey.a(APSecurePinStep.ap, "showPinCode, response= " + jSONObject);
                }

                public void a(int i, String str, Throwable th) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("showPinCode onfail:");
                    sb.append(str == null ? Log.getStackTraceString(th) : str.toString());
                    LogUtilGrey.a(APSecurePinStep.ap, sb.toString());
                    if (!APSecurePinStep.this.ag && !APSecurePinStep.this.ah) {
                        APSecurePinStep.this.U_().postDelayed(new Runnable() {
                            public final void run() {
                                APSecurePinStep.AnonymousClass8.this.a();
                            }
                        }, 1000);
                    }
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void a() {
                    APSecurePinStep.this.s();
                }
            });
        } catch (JSONException e2) {
            LogUtilGrey.a(ap, Log.getStackTraceString(e2));
        }
    }

    private void t() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", ((PluginHostApi) PluginHostApi.instance()).generateNonce());
            jSONObject.put("method", "stop_show_pin_code");
            jSONObject.put("params", "");
            MiioLocalAPI.a(n(), jSONObject.toString(), this.k, this.l, (MiioLocalRpcResponse) new MiioLocalRpcResponse.MiioLocalRpcResponseSimple() {
                public void a(JSONObject jSONObject) {
                    LogUtilGrey.a(APSecurePinStep.ap, "stopPinCode, response= " + jSONObject);
                }

                public void a(int i, String str, Throwable th) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("stopPinCode onfail:");
                    sb.append(str == null ? Log.getStackTraceString(th) : str.toString());
                    LogUtilGrey.a(APSecurePinStep.ap, sb.toString());
                }
            });
        } catch (JSONException e2) {
            LogUtilGrey.a(ap, Log.getStackTraceString(e2));
        }
    }
}
