package com.xiaomi.smarthome.framework.page.verify;

import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.xiaomi.smarthome.core.server.internal.util.LtmkEncryptUtil;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.verify.callback.GetCipherCallback;
import com.xiaomi.smarthome.framework.page.verify.callback.PinOptCallback;
import java.util.HashMap;
import javax.crypto.Cipher;

public class VerifyManager {

    /* renamed from: a  reason: collision with root package name */
    public static final int f17075a = 6;
    private static final String b = DeviceVerifyConfigCache.class.getSimpleName();
    private static final int c = 4;
    private static volatile VerifyManager d;
    /* access modifiers changed from: private */
    public Context e;
    /* access modifiers changed from: private */
    public DeviceVerifyConfigCache f;
    /* access modifiers changed from: private */
    public HashMap<String, PincodeData> g = new HashMap<>();

    private VerifyManager(Context context) {
        this.e = context.getApplicationContext();
        if (c(this.e)) {
            this.f = DeviceVerifyConfigCache.a(this.e);
        }
    }

    public static VerifyManager a(Context context) {
        if (d == null) {
            synchronized (VerifyManager.class) {
                if (d == null) {
                    d = new VerifyManager(context);
                }
            }
        }
        return d;
    }

    private int a(String str, String str2) {
        return LtmkEncryptUtil.a(str, str2) ? 6 : 4;
    }

    private static boolean b(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /* access modifiers changed from: package-private */
    public void a(String str, GetCipherCallback getCipherCallback) {
        if (this.f == null) {
            getCipherCallback.onGetCipherError(DeviceVerifyConfigCache.c, "DeviceVerifyCache is null!");
        }
        this.f.a(str, getCipherCallback);
    }

    /* access modifiers changed from: package-private */
    public void b(String str, GetCipherCallback getCipherCallback) {
        if (this.f == null) {
            getCipherCallback.onGetCipherError(DeviceVerifyConfigCache.c, "DeviceVerifyCache is null!");
        }
        this.f.b(str, getCipherCallback);
    }

    /* access modifiers changed from: package-private */
    public void a(String str, String str2, final String str3, final String str4, @NonNull final PinOptCallback pinOptCallback) {
        if (!b(this.e)) {
            pinOptCallback.c();
        } else if (!TextUtils.isDigitsOnly(str4) || str4.length() != a(str, str2)) {
            pinOptCallback.a("pin format invalid");
        } else {
            DevicePinApi.a().a(this.e, str3, str4, new AsyncCallback<Integer, Error>() {
                /* renamed from: a */
                public void onSuccess(Integer num) {
                    VerifyManager.this.g.put(str3, new PincodeData(str4));
                    pinOptCallback.a();
                }

                public void onFailure(Error error) {
                    pinOptCallback.a(error.b());
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void a(final String str, String str2, final PinOptCallback pinOptCallback) {
        if (!b(this.e)) {
            pinOptCallback.c();
        } else {
            DevicePinApi.a().b(this.e, str, str2, new AsyncCallback<Integer, Error>() {
                /* renamed from: a */
                public void onSuccess(Integer num) {
                    if (num.intValue() != 0) {
                        pinOptCallback.b();
                        return;
                    }
                    if (VerifyManager.this.c(VerifyManager.this.e) && VerifyManager.this.a()) {
                        VerifyManager.this.f.b(str);
                    }
                    VerifyManager.this.g.remove(str);
                    pinOptCallback.a();
                }

                public void onFailure(Error error) {
                    pinOptCallback.a(error.b());
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str, String str2, final String str3, final String str4, String str5, final PinOptCallback pinOptCallback) {
        if (!b(this.e)) {
            pinOptCallback.c();
        } else if (!TextUtils.isDigitsOnly(str4) || str4.length() != a(str, str2)) {
            pinOptCallback.a("pin format invalid");
        } else {
            DevicePinApi.a().a(this.e, str3, str4, str5, new AsyncCallback<Integer, Error>() {
                /* renamed from: a */
                public void onSuccess(Integer num) {
                    if (num.intValue() != 0) {
                        pinOptCallback.b();
                        return;
                    }
                    if (VerifyManager.this.b(str3)) {
                        VerifyManager.this.a(str3);
                    }
                    VerifyManager.this.g.put(str3, new PincodeData(str4));
                    pinOptCallback.a();
                }

                public void onFailure(Error error) {
                    pinOptCallback.a(error.b());
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public String a(String str, Cipher cipher) {
        return this.f.a(str, cipher);
    }

    /* access modifiers changed from: package-private */
    public void b(String str, String str2, final String str3, final String str4, final PinOptCallback pinOptCallback) {
        if (!b(this.e)) {
            pinOptCallback.c();
        } else if (!TextUtils.isDigitsOnly(str4) || str4.length() != a(str, str2)) {
            pinOptCallback.a("pin format invalid");
        } else {
            DevicePinApi.a().c(this.e, str3, str4, new AsyncCallback<Integer, Error>() {
                /* renamed from: a */
                public void onSuccess(Integer num) {
                    if (num.intValue() == 0) {
                        VerifyManager.this.g.put(str3, new PincodeData(str4));
                        pinOptCallback.a();
                        return;
                    }
                    pinOptCallback.b();
                }

                public void onFailure(Error error) {
                    pinOptCallback.a(error.b());
                }
            });
        }
    }

    public String a(String str, long j) {
        PincodeData pincodeData = this.g.get(str);
        if (pincodeData != null) {
            return (j == 0 || System.currentTimeMillis() - pincodeData.b < j) ? pincodeData.f17061a : "";
        }
        return "";
    }

    /* access modifiers changed from: package-private */
    @TargetApi(23)
    public void a(String str, String str2, Cipher cipher) {
        if (this.f != null) {
            try {
                this.f.a(str, str2, cipher);
                this.f.a(str, true);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: package-private */
    @TargetApi(23)
    public void a(String str) {
        if (this.f != null) {
            this.f.a(str, false);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean b(String str) {
        return a() && this.f.a(str);
    }

    /* access modifiers changed from: package-private */
    public boolean a() {
        return c(this.e) && this.f != null;
    }

    /* access modifiers changed from: private */
    public boolean c(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        }
        try {
            FingerprintManager fingerprintManager = (FingerprintManager) context.getSystemService(FingerprintManager.class);
            if (fingerprintManager != null && fingerprintManager.isHardwareDetected()) {
                if (fingerprintManager.hasEnrolledFingerprints()) {
                    KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(KeyguardManager.class);
                    if (keyguardManager == null || !keyguardManager.isKeyguardSecure()) {
                        return false;
                    }
                    return true;
                }
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
