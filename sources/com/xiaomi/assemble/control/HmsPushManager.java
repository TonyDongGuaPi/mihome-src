package com.xiaomi.assemble.control;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.huawei.hms.api.ConnectionResult;
import com.huawei.hms.api.HuaweiApiClient;
import com.huawei.hms.support.api.push.HuaweiPush;
import com.huawei.hms.support.api.push.TokenResult;
import com.xiaomi.mipush.sdk.AbstractPushManager;
import com.xiaomi.mipush.sdk.HWPushHelper;

public class HmsPushManager implements AbstractPushManager {
    public static final String TAG = "HmsPushManager";

    /* renamed from: a  reason: collision with root package name */
    private static final String f10000a = "HmsConnectedSuspend";
    private static final String b = "HmsConnectedFailed";
    private static final String c = "HmsGetTokenError";
    private static final int d = 3;
    private static final int e = 2000;
    private static final int[] f = {2000, 4000, 8000};
    private Handler g = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public HuaweiApiClient h;
    /* access modifiers changed from: private */
    public Context i;
    private HuaweiApiClient.ConnectionCallbacks j = new HuaweiApiClient.ConnectionCallbacks() {
        public void onConnected() {
            if (!HWPushHelper.e(HmsPushManager.this.i) || HWPushHelper.d(HmsPushManager.this.i)) {
                HmsPushManager.this.c();
            }
            HWPushHelper.a(false);
            Log.i(HmsPushManager.TAG, "Hms connected success !");
        }

        public void onConnectionSuspended(int i) {
            if (HmsPushManager.this.h != null) {
                if (HWPushHelper.h(HmsPushManager.this.i)) {
                    HmsPushManager.this.a(0);
                } else {
                    HWPushHelper.a(true);
                }
            }
            Log.i(HmsPushManager.TAG, "Hms connected suspended,retry connected! reason code =" + i);
            HWPushHelper.a(HmsPushManager.f10000a, i);
        }
    };
    private HuaweiApiClient.OnConnectionFailedListener k = new HuaweiApiClient.OnConnectionFailedListener() {
        public void onConnectionFailed(ConnectionResult connectionResult) {
            if (connectionResult != null) {
                int errorCode = connectionResult.getErrorCode();
                if (7 != errorCode && 14 != errorCode) {
                    HWPushHelper.a(HmsPushManager.b, errorCode);
                    Log.i(HmsPushManager.TAG, "Hms create connected failed ! Error Code = " + errorCode);
                } else if (!HWPushHelper.h(HmsPushManager.this.i)) {
                    HWPushHelper.a(true);
                    Log.i(HmsPushManager.TAG, "Hms create connected failed, because of no network!");
                } else if (HmsPushManager.this.h != null) {
                    HmsPushManager.this.a(0);
                }
            }
        }
    };

    private HmsPushManager(Context context) {
        this.i = context;
    }

    public static HmsPushManager newInstance(Context context) {
        return new HmsPushManager(context);
    }

    public void a() {
        this.h = new HuaweiApiClient.Builder(this.i).addApi(HuaweiPush.PUSH_API).addConnectionCallbacks(this.j).addOnConnectionFailedListener(this.k).build();
        if (this.h != null) {
            this.h.connect();
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        if (!this.h.isConnected()) {
            Log.i(TAG, "Hms get token failed , client not connected ");
            if (HWPushHelper.h(this.i)) {
                a(0);
            } else {
                HWPushHelper.a(true);
            }
        } else {
            new Thread() {
                public void run() {
                    TokenResult await = HuaweiPush.HuaweiPushApi.getToken(HmsPushManager.this.h).await();
                    if (await != null && await.getTokenRes() != null) {
                        int retCode = await.getTokenRes().getRetCode();
                        if (retCode != 0) {
                            HWPushHelper.a(HmsPushManager.c, retCode);
                            Log.i(HmsPushManager.TAG, "Hms get token failed ErrorCode = " + retCode);
                        } else if (retCode == 0) {
                            Log.i(HmsPushManager.TAG, "Hms get token call success!");
                            HWPushHelper.c(HmsPushManager.this.i);
                        }
                    }
                }
            }.start();
        }
    }

    public void b() {
        if (this.g != null) {
            this.g.removeCallbacksAndMessages((Object) null);
        }
        if (this.h != null) {
            this.h.disconnect();
        }
        HWPushHelper.a(false);
    }

    /* access modifiers changed from: private */
    public void a(final int i2) {
        long b2 = b(i2);
        if (this.g == null) {
            this.g = new Handler(Looper.getMainLooper());
        }
        this.g.postDelayed(new Runnable() {
            public void run() {
                if (!HmsPushManager.this.h.isConnected()) {
                    HmsPushManager.this.h.connect();
                    int i = i2 + 1;
                    if (i > 3) {
                        Log.i(HmsPushManager.TAG, "Hms connect fail, but retry too many times, stop retry");
                        return;
                    }
                    Log.i(HmsPushManager.TAG, "Hms connect fail, connect again, retryIndex: " + i);
                    HmsPushManager.this.a(i);
                    return;
                }
                Log.i(HmsPushManager.TAG, "Hms connect success, stop retry");
            }
        }, b2);
    }

    private long b(int i2) {
        int length = f.length;
        if (i2 < length) {
            return (long) f[i2];
        }
        return (long) f[length - 1];
    }
}
