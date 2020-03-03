package com.xiaomi.verificationsdk.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.accountsdk.hasheddeviceidlib.HashedDeviceIdUtil;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.SimpleRequest;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.accountsdk.utils.EasyMap;
import com.xiaomi.passport.PassportUserEnvironment;
import com.xiaomi.passport.SecurityDeviceSignManager;
import com.xiaomi.stat.d;
import com.xiaomi.verificationsdk.BuildConfig;
import com.xiaomi.verificationsdk.VerificationManager;
import com.xiaomi.verificationsdk.internal.EnvEncryptUtils;
import com.xiaomi.verificationsdk.internal.ErrorInfo;
import com.xiaomi.verificationsdk.internal.VerifyError;
import com.xiaomi.verificationsdk.internal.VerifyResult;
import java.io.IOException;
import java.net.ConnectException;
import java.net.NetworkInterface;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SensorHelper implements SensorEventListener {
    private static HandlerThread e = new HandlerThread("sensor");

    /* renamed from: a  reason: collision with root package name */
    private final String f23124a = "SensorHelper";
    private SensorManager b;
    private int c;
    private Handler d;
    private TelephonyManager f;
    /* access modifiers changed from: private */
    public Context g;
    /* access modifiers changed from: private */
    public int h;
    /* access modifiers changed from: private */
    public boolean i;
    /* access modifiers changed from: private */
    public long j;
    private BatteryReceiver k;
    private JSONArray l;
    private JSONArray m;
    private JSONArray n;
    private JSONArray o;
    private JSONArray p;
    private String q;
    private volatile boolean r = false;
    private volatile boolean s = false;

    private String e() {
        return null;
    }

    private String g() {
        return null;
    }

    public void onAccuracyChanged(Sensor sensor, int i2) {
    }

    public String a() {
        return this.q;
    }

    public void a(String str) {
        this.q = str;
    }

    public void b() {
        this.q = "";
        this.l = new JSONArray();
        this.m = new JSONArray();
        this.n = new JSONArray();
        this.o = new JSONArray();
        this.p = new JSONArray();
    }

    public void c() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        d();
        final long currentTimeMillis = System.currentTimeMillis();
        this.d.post(new Runnable() {
            public void run() {
                SensorHelper.this.a(SensorHelper.this.a(SensorHelper.this.j, currentTimeMillis));
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }

    static {
        e.start();
    }

    public SensorHelper(Context context) {
        if (context != null) {
            this.g = context;
            this.b = (SensorManager) context.getSystemService("sensor");
            this.d = new Handler(e.getLooper());
            this.f = (TelephonyManager) context.getSystemService("phone");
            return;
        }
        throw new IllegalArgumentException("SensorHelper init : context  should not be null");
    }

    public void a(String str, RegisterInfo registerInfo, Boolean bool, VerificationManager.Verify2Callback verify2Callback) {
        ScoreManager.b();
        final RegisterInfo registerInfo2 = registerInfo;
        final VerificationManager.Verify2Callback verify2Callback2 = verify2Callback;
        final String str2 = str;
        final Boolean bool2 = bool;
        this.d.post(new Runnable() {
            public void run() {
                try {
                    if (registerInfo2 == null) {
                        throw new IllegalArgumentException("uploadData :registerInfo parameter not be null");
                    } else if (verify2Callback2 != null) {
                        EnvEncryptUtils.EncryptResult a2 = EnvEncryptUtils.a(str2);
                        String str = "";
                        try {
                            Bundle bundle = SecurityDeviceSignManager.sign(SensorHelper.this.g, registerInfo2.b() + registerInfo2.a() + a2.b + a2.f23121a, (Bundle) null).get(3000, TimeUnit.MILLISECONDS);
                            if (bundle != null) {
                                if (bundle.getBoolean("booleanResult")) {
                                    str = bundle.getString("userData");
                                } else {
                                    int i = bundle.getInt("errorCode");
                                    String string = bundle.getString("errorMessage");
                                    AccountLog.i("SensorHelper", "error code: " + i);
                                    AccountLog.i("SensorHelper", "error msg: " + string);
                                }
                            }
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        } catch (ExecutionException e3) {
                            e3.printStackTrace();
                        } catch (TimeoutException e4) {
                            e4.printStackTrace();
                        }
                        EasyMap easyMap = new EasyMap();
                        if (bool2.booleanValue()) {
                            easyMap.easyPut("s", "");
                            easyMap.easyPut("d", "");
                        } else {
                            easyMap.easyPut("s", a2.b);
                            easyMap.easyPut("d", a2.f23121a);
                        }
                        if (!TextUtils.isEmpty(str)) {
                            easyMap.easyPut(d.o, str);
                        }
                        String property = System.getProperty("http.agent");
                        EasyMap easyMap2 = new EasyMap();
                        EasyMap easyPut = easyMap2.easyPut("User-Agent", property + " AndroidVerifySDK/" + BuildConfig.f);
                        SimpleRequest.StringContent postAsString = SimpleRequest.postAsString("https://verify.sec.xiaomi.com/captcha/data?clientId=" + registerInfo2.b() + "&eventId=" + registerInfo2.a(), (Map<String, String>) easyMap, (Map<String, String>) null, (Map<String, String>) easyPut, true, (Integer) 3000);
                        if (postAsString == null) {
                            verify2Callback2.a(SensorHelper.a(ErrorInfo.ErrorCode.ERROR_NETWORK_EXCEPTION.getCode(), "uploadData:network exception", ErrorInfo.getMsgIdGivenErrorCode(ErrorInfo.ErrorCode.ERROR_NETWORK_EXCEPTION)));
                            return;
                        }
                        JSONObject jSONObject = new JSONObject(postAsString.getBody());
                        int optInt = jSONObject.optInt("code");
                        String optString = jSONObject.optString("msg");
                        if (optInt == 0) {
                            JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                            int optInt2 = jSONObject2.optInt("result");
                            String optString2 = jSONObject2.optString("flag");
                            String optString3 = jSONObject2.optString("url");
                            JSONObject optJSONObject = jSONObject2.optJSONObject("extra");
                            if (optJSONObject != null) {
                                ScoreManager.a(optJSONObject.toString());
                            }
                            if (!TextUtils.isEmpty(optString3)) {
                                verify2Callback2.a(optString3);
                            } else if (optInt2 == 0) {
                                verify2Callback2.a(new VerifyResult.Builder().a(registerInfo2.b()).b(registerInfo2.a()).c(optString2).d(ScoreManager.a()).a());
                            } else {
                                verify2Callback2.a(SensorHelper.a(ErrorInfo.ErrorCode.ERROR_HUMANCOMPUTER_VERIFICATION_FAILED.getCode(), "uploadData:human computer verification failed", ErrorInfo.getMsgIdGivenErrorCode(ErrorInfo.ErrorCode.ERROR_HUMANCOMPUTER_VERIFICATION_FAILED)));
                            }
                        } else if (optInt == 500) {
                            verify2Callback2.a(new VerifyResult.Builder().a(registerInfo2.b()).b(registerInfo2.a()).c(EnvEncryptUtils.a()).a());
                        } else {
                            verify2Callback2.a(SensorHelper.a(optInt, "uploadData:" + optString, ErrorInfo.getMsgIdGivenErrorCode(ErrorInfo.ErrorCode.ERROR_SERVER)));
                        }
                    } else {
                        throw new IllegalArgumentException("uploadData :verifyCallback not be null");
                    }
                } catch (JSONException e5) {
                    AccountLog.e("SensorHelper", "fail to parse JSONObject", e5);
                    int code = ErrorInfo.ErrorCode.ERROR_JSON_EXCEPTION.getCode();
                    verify2Callback2.a(SensorHelper.a(code, "uploadData:" + e5.toString(), ErrorInfo.getMsgIdGivenErrorCode(ErrorInfo.ErrorCode.ERROR_JSON_EXCEPTION)));
                } catch (EnvEncryptUtils.EncryptException e6) {
                    AccountLog.e("SensorHelper", "", e6);
                    int code2 = ErrorInfo.ErrorCode.ERROR_ENCRYPT_EXCEPTION.getCode();
                    verify2Callback2.a(SensorHelper.a(code2, "uploadData:" + e6.toString(), ErrorInfo.getMsgIdGivenErrorCode(ErrorInfo.ErrorCode.ERROR_ENCRYPT_EXCEPTION)));
                } catch (IOException e7) {
                    SensorHelper.this.a(verify2Callback2, e7);
                } catch (AccessDeniedException e8) {
                    AccountLog.e("SensorHelper", "", e8);
                    int code3 = ErrorInfo.ErrorCode.ERROR_ACCESSDENIED_EXCEPTION.getCode();
                    verify2Callback2.a(SensorHelper.a(code3, "uploadData:" + e8.toString(), ErrorInfo.getMsgIdGivenErrorCode(ErrorInfo.ErrorCode.ERROR_ACCESSDENIED_EXCEPTION)));
                } catch (AuthenticationFailureException e9) {
                    AccountLog.e("SensorHelper", "", e9);
                    int code4 = ErrorInfo.ErrorCode.ERROR_AUTHENTICATIONFAILURE_EXCEPTION.getCode();
                    verify2Callback2.a(SensorHelper.a(code4, "uploadData:" + e9.toString(), ErrorInfo.getMsgIdGivenErrorCode(ErrorInfo.ErrorCode.ERROR_AUTHENTICATIONFAILURE_EXCEPTION)));
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(VerificationManager.Verify2Callback verify2Callback, IOException iOException) {
        AccountLog.e("SensorHelper", "", iOException);
        if (iOException instanceof ConnectException) {
            int code = ErrorInfo.ErrorCode.ERROR_CONNECT_UNREACHABLE_EXCEPTION.getCode();
            verify2Callback.a(a(code, "uploadData:" + iOException.toString(), ErrorInfo.getMsgIdGivenErrorCode(ErrorInfo.ErrorCode.ERROR_CONNECT_UNREACHABLE_EXCEPTION)));
        } else if (iOException instanceof SocketTimeoutException) {
            int code2 = ErrorInfo.ErrorCode.ERROR_SOCKET_TIMEOUT_EXCEPTION.getCode();
            verify2Callback.a(a(code2, "uploadData:" + iOException.toString(), ErrorInfo.getMsgIdGivenErrorCode(ErrorInfo.ErrorCode.ERROR_SOCKET_TIMEOUT_EXCEPTION)));
        } else if (iOException instanceof ConnectTimeoutException) {
            int code3 = ErrorInfo.ErrorCode.ERROR_CONNECT_TIMEOUT_EXCEPTION.getCode();
            verify2Callback.a(a(code3, "uploadData:" + iOException.toString(), ErrorInfo.getMsgIdGivenErrorCode(ErrorInfo.ErrorCode.ERROR_CONNECT_TIMEOUT_EXCEPTION)));
        } else {
            int code4 = ErrorInfo.ErrorCode.ERROR_IO_EXCEPTION.getCode();
            verify2Callback.a(a(code4, "uploadData:" + iOException.toString(), ErrorInfo.getMsgIdGivenErrorCode(ErrorInfo.ErrorCode.ERROR_IO_EXCEPTION)));
        }
    }

    public void a(int i2, int i3) {
        this.c = i2;
        final long currentTimeMillis = System.currentTimeMillis();
        v();
        this.d.postDelayed(new Runnable() {
            public void run() {
                long currentTimeMillis = System.currentTimeMillis();
                SensorHelper.this.d();
                SensorHelper.this.a(SensorHelper.this.a(currentTimeMillis, currentTimeMillis));
            }
        }, (long) i3);
    }

    public String a(long j2, long j3) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject.put("type", 2);
            jSONObject.put("timestamp", j2);
            jSONObject.put(Constants.b, j3 - j2);
            jSONObject2.put("userId", "");
            jSONObject2.put(Constants.h, "");
            jSONObject2.put(Constants.i, "");
            jSONObject2.put(Constants.j, h());
            jSONObject2.put("iccid", "");
            jSONObject2.put("gps", "");
            jSONObject2.put(Constants.m, this.h);
            jSONObject2.put("wifi_ssid", "");
            jSONObject2.put(Constants.o, "");
            jSONObject2.put("device_id", l());
            jSONObject2.put("app", m());
            jSONObject2.put(Constants.r, BuildConfig.f);
            jSONObject2.put(Constants.s, n());
            jSONObject2.put(Constants.t, o());
            jSONObject2.put("system_version", p());
            jSONObject2.put(Constants.v, q());
            jSONObject2.put(Constants.w, r());
            jSONObject2.put("debug", s());
            jSONObject2.put(Constants.A, u());
            jSONObject2.put(Constants.y, this.i);
            jSONObject2.put(Constants.z, t());
            jSONObject.put(Constants.d, jSONObject2);
            jSONObject3.put(Constants.C, this.l);
            jSONObject3.put(Constants.D, this.m);
            jSONObject3.put(Constants.E, this.n);
            jSONObject3.put("light", this.o);
            jSONObject3.put(Constants.G, this.p);
            jSONObject3.put(Constants.H, "");
            jSONObject.put("action", jSONObject3);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    private String f() {
        return this.f.getLine1Number();
    }

    private String h() {
        return this.f.getNetworkOperator();
    }

    private String i() {
        return this.f.getSimSerialNumber();
    }

    private String j() {
        return PassportUserEnvironment.Holder.getInstance().getSSID();
    }

    private int k() {
        GsmCellLocation gsmCellLocation = (GsmCellLocation) this.f.getCellLocation();
        if (gsmCellLocation != null) {
            return gsmCellLocation.getCid();
        }
        return 0;
    }

    private String l() {
        return new HashedDeviceIdUtil(this.g).getHashedDeviceIdNoThrow();
    }

    private String m() {
        String str = "";
        try {
            String str2 = this.g.getPackageManager().getPackageInfo(this.g.getPackageName(), 0).versionName;
            if (str2 == null) {
                return "";
            }
            try {
                return str2.length() <= 0 ? "" : str2;
            } catch (Exception e2) {
                String str3 = str2;
                e = e2;
                str = str3;
                Log.e("VersionInfo", "Exception", e);
                return str;
            }
        } catch (Exception e3) {
            e = e3;
            Log.e("VersionInfo", "Exception", e);
            return str;
        }
    }

    private String n() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces == null) {
                return "0";
            }
            Iterator<T> it = Collections.list(networkInterfaces).iterator();
            while (it.hasNext()) {
                NetworkInterface networkInterface = (NetworkInterface) it.next();
                if (networkInterface.isUp()) {
                    if (networkInterface.getInterfaceAddresses().size() != 0) {
                        if ("tun0".equals(networkInterface.getName()) || "ppp0".equals(networkInterface.getName())) {
                            return "1";
                        }
                    }
                }
            }
            return "0";
        } catch (Throwable th) {
            th.printStackTrace();
            return "0";
        }
    }

    private String o() {
        return Build.MODEL;
    }

    private String p() {
        return "Android " + Build.VERSION.RELEASE;
    }

    private String q() {
        return Build.FINGERPRINT;
    }

    private double r() {
        double d2 = 0.0d;
        try {
            double d3 = (double) Settings.System.getInt(this.g.getContentResolver(), Constants.w);
            Double.isNaN(d3);
            d2 = d3 / 255.0d;
            return Double.parseDouble(new DecimalFormat("##0.00").format(d2));
        } catch (Exception e2) {
            e2.printStackTrace();
            return d2;
        }
    }

    private boolean s() {
        return Settings.Secure.getInt(this.g.getContentResolver(), "adb_enabled", 0) > 0;
    }

    private boolean t() {
        int i2;
        try {
            i2 = Settings.System.getInt(this.g.getContentResolver(), "screen_off_timeout");
        } catch (Exception unused) {
            i2 = 0;
        }
        return i2 == Integer.MAX_VALUE;
    }

    private boolean u() {
        return ((TelephonyManager) this.g.getSystemService("phone")).getNetworkOperatorName().toLowerCase().equals("android");
    }

    private void v() {
        this.r = true;
        a(1);
        a(4);
        a(2);
        a(5);
        a(6);
        IntentFilter intentFilter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
        if (!this.s) {
            this.k = new BatteryReceiver();
            this.g.registerReceiver(this.k, intentFilter);
            this.s = true;
            this.j = System.currentTimeMillis();
        }
    }

    public void d() {
        if (this.r) {
            this.r = false;
            w();
        }
    }

    private void a(int i2) {
        Sensor defaultSensor = this.b.getDefaultSensor(i2);
        if (defaultSensor != null) {
            this.b.registerListener(this, defaultSensor, this.c * 1000);
        }
    }

    private synchronized void w() {
        try {
            this.b.unregisterListener(this);
            if (this.s) {
                this.g.unregisterReceiver(this.k);
                this.s = false;
            }
        } catch (Exception e2) {
            Log.e("SensorHelper", e2.toString());
        }
        return;
    }

    /* access modifiers changed from: private */
    public void a(SensorData sensorData) {
        if (this.r) {
            if (this.l == null) {
                this.l = new JSONArray();
            }
            if (this.m == null) {
                this.m = new JSONArray();
            }
            if (this.n == null) {
                this.n = new JSONArray();
            }
            if (this.o == null) {
                this.o = new JSONArray();
            }
            if (this.p == null) {
                this.p = new JSONArray();
            }
            try {
                switch (sensorData.c) {
                    case 1:
                        this.m.put(sensorData.c());
                        return;
                    case 2:
                        this.n.put(sensorData.c());
                        return;
                    case 4:
                        this.l.put(sensorData.c());
                        return;
                    case 5:
                        this.o.put(sensorData.c());
                        return;
                    case 6:
                        this.p.put(sensorData.c());
                        return;
                    default:
                        return;
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void onSensorChanged(final SensorEvent sensorEvent) {
        this.d.post(new Runnable() {
            public void run() {
                SensorHelper.this.a(new SensorData(sensorEvent));
            }
        });
    }

    private class SensorData {
        private final float[] b;
        /* access modifiers changed from: private */
        public final int c;

        SensorData(SensorEvent sensorEvent) {
            this.b = sensorEvent.values;
            this.c = sensorEvent.sensor.getType();
        }

        /* access modifiers changed from: package-private */
        public byte[] a() {
            ByteBuffer allocate = ByteBuffer.allocate((this.b.length * 4) + 4 + 8);
            for (float putFloat : this.b) {
                allocate.putFloat(putFloat);
            }
            allocate.putInt(d());
            allocate.putLong(System.currentTimeMillis());
            return allocate.array();
        }

        /* access modifiers changed from: package-private */
        public String b() {
            StringBuilder sb = new StringBuilder();
            for (float append : this.b) {
                sb.append(append);
                sb.append(",");
            }
            sb.append(d());
            sb.append(",");
            sb.append(System.currentTimeMillis());
            return sb.toString();
        }

        /* access modifiers changed from: package-private */
        public JSONArray c() throws JSONException {
            double d;
            double d2;
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(System.currentTimeMillis() - SensorHelper.this.j);
            DecimalFormat decimalFormat = new DecimalFormat("##0.0");
            if (this.c == 5) {
                try {
                    d2 = Double.parseDouble(decimalFormat.format((double) this.b[0]));
                } catch (Exception unused) {
                    d2 = 0.0d;
                }
                jSONArray.put(d2);
            } else {
                for (float f : this.b) {
                    try {
                        d = Double.parseDouble(new DecimalFormat("##0.0000").format((double) f));
                    } catch (Exception e) {
                        Log.e("SensorHelper", e.toString());
                        d = 0.0d;
                    }
                    jSONArray.put(d);
                }
            }
            return jSONArray;
        }

        private int d() {
            switch (this.c) {
                case 1:
                    return 2;
                case 2:
                    return 3;
                case 4:
                    return 1;
                case 5:
                    return 4;
                case 6:
                    return 5;
                default:
                    return 0;
            }
        }

        public String toString() {
            return b();
        }
    }

    public class BatteryReceiver extends BroadcastReceiver {
        public BatteryReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            int unused = SensorHelper.this.h = intent.getExtras().getInt("level");
            if (!"android.intent.action.BATTERY_CHANGED".equals(intent.getAction())) {
                boolean unused2 = SensorHelper.this.i = false;
            } else if (intent.getIntExtra("status", 1) == 2) {
                boolean unused3 = SensorHelper.this.i = true;
            }
        }
    }

    public static VerifyError a(int i2, String str, int i3) {
        return new VerifyError.Build().a(i2).a(str).b(i3).a();
    }
}
