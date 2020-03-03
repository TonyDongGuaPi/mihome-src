package com.unionpay.tsmservice.mi;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Base64;
import com.unionpay.tsmservice.mi.ITsmActivityCallback;
import com.unionpay.tsmservice.mi.ITsmCallback;
import com.unionpay.tsmservice.mi.ITsmService;
import com.unionpay.tsmservice.mi.data.Constant;
import com.unionpay.tsmservice.mi.request.RequestParams;
import com.unionpay.tsmservice.mi.request.SafetyKeyboardRequestParams;
import com.unionpay.tsmservice.mi.utils.IUPJniInterface;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class UPTsmAddon {

    /* renamed from: a  reason: collision with root package name */
    private static UPTsmAddon f9836a = null;
    private static CopyOnWriteArrayList b = null;
    private Context c = null;
    private ServiceConnection d = null;
    /* access modifiers changed from: private */
    public ITsmService e = null;
    /* access modifiers changed from: private */
    public boolean f = false;
    private HashMap g = new HashMap();
    private HashMap h = new HashMap();
    private HashMap i = new HashMap();
    private HashMap j = new HashMap();
    private HashMap k = new HashMap();
    private HashMap l = new HashMap();
    private HashMap m = new HashMap();
    private HashMap n = new HashMap();
    private HashMap o = new HashMap();
    private HashMap p = new HashMap();
    private HashMap q = new HashMap();
    private HashMap r = new HashMap();
    private HashMap s = new HashMap();
    private HashMap t = new HashMap();
    private HashMap u = new HashMap();
    private HashMap v = new HashMap();
    /* access modifiers changed from: private */
    public int[] w;
    private final Handler.Callback x = new Handler.Callback() {
        public final synchronized boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    UPTsmAddon.a();
                    return true;
                case 1:
                    UPTsmAddon.b();
                    return true;
                default:
                    return false;
            }
        }
    };
    /* access modifiers changed from: private */
    public final Handler y = new Handler(Looper.getMainLooper(), this.x);

    public interface UPTsmConnectionListener {
        void onTsmConnected();

        void onTsmDisconnected();
    }

    public final class a extends ITsmActivityCallback.Stub {
        private int b = 1000;

        public a() {
        }

        public final void startActivity(String str, String str2, int i, Bundle bundle) {
            UPTsmAddon.a((ITsmActivityCallback) UPTsmAddon.b(UPTsmAddon.this, this.b).get(UPTsmAddon.this.c.getPackageName()), str, str2, i, bundle);
            UPTsmAddon.b(UPTsmAddon.this, this.b).remove(UPTsmAddon.this.c.getPackageName());
        }
    }

    final class b extends ITsmCallback.Stub {
        private int b;
        private int c;

        private b(int i, int i2) {
            this.b = i;
            this.c = i2;
        }

        /* synthetic */ b(UPTsmAddon uPTsmAddon, int i, int i2, byte b2) {
            this(i, i2);
        }

        public final void onError(String str, String str2) {
            Bundle bundle = new Bundle();
            bundle.putString("errorCode", str);
            bundle.putString("errorDesc", str2);
            UPTsmAddon.a((ITsmCallback) UPTsmAddon.a(UPTsmAddon.this, this.b).get(String.valueOf(this.c)), bundle);
            UPTsmAddon.a(UPTsmAddon.this, this.b).remove(String.valueOf(this.c));
            if (UPTsmAddon.a(UPTsmAddon.this, this.b).isEmpty()) {
                UPTsmAddon.this.w[this.b] = 0;
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:23:0x00ee, code lost:
            r1.putParcelable("result", r7);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void onResult(android.os.Bundle r7) {
            /*
                r6 = this;
                android.os.Bundle r0 = new android.os.Bundle
                r0.<init>()
                int r0 = r6.b
                android.os.Bundle r1 = new android.os.Bundle
                r1.<init>()
                android.os.Parcel r2 = android.os.Parcel.obtain()
                java.lang.String r3 = "errorCode"
                java.lang.String r3 = r7.getString(r3)
                java.lang.String r4 = "result"
                java.lang.String r4 = r7.getString(r4)
                java.lang.String r5 = "errorCode"
                r1.putString(r5, r3)
                boolean r3 = android.text.TextUtils.isEmpty(r4)
                r5 = 0
                if (r3 != 0) goto L_0x004b
                java.lang.String r3 = com.unionpay.tsmservice.mi.UPTsmAddon.d(r4)
                byte[] r3 = android.util.Base64.decode(r3, r5)
                if (r3 == 0) goto L_0x003c
                int r4 = r3.length
                if (r4 == 0) goto L_0x003c
                int r4 = r3.length
                r2.unmarshall(r3, r5, r4)
                r2.setDataPosition(r5)
            L_0x003c:
                int r3 = r2.dataSize()
                if (r3 != 0) goto L_0x004b
                java.lang.String r7 = "errorCode"
                java.lang.String r0 = "010035"
                r1.putString(r7, r0)
                goto L_0x00f3
            L_0x004b:
                switch(r0) {
                    case 0: goto L_0x00e2;
                    case 1: goto L_0x00d5;
                    case 2: goto L_0x00c8;
                    case 3: goto L_0x004e;
                    case 4: goto L_0x004e;
                    case 5: goto L_0x004e;
                    case 6: goto L_0x00bb;
                    case 7: goto L_0x004e;
                    case 8: goto L_0x00ae;
                    case 9: goto L_0x00ae;
                    case 10: goto L_0x00a1;
                    case 11: goto L_0x0094;
                    case 12: goto L_0x0087;
                    case 13: goto L_0x004e;
                    case 14: goto L_0x00ae;
                    case 15: goto L_0x007a;
                    case 16: goto L_0x006c;
                    case 17: goto L_0x005e;
                    case 18: goto L_0x0050;
                    default: goto L_0x004e;
                }
            L_0x004e:
                goto L_0x00f4
            L_0x0050:
                java.lang.Class<com.unionpay.tsmservice.mi.result.MessageDetailsResult> r7 = com.unionpay.tsmservice.mi.result.MessageDetailsResult.class
                java.lang.ClassLoader r7 = r7.getClassLoader()
                android.os.Parcelable r7 = r2.readParcelable(r7)
                com.unionpay.tsmservice.mi.result.MessageDetailsResult r7 = (com.unionpay.tsmservice.mi.result.MessageDetailsResult) r7
                goto L_0x00ee
            L_0x005e:
                java.lang.Class<com.unionpay.tsmservice.mi.result.TransactionDetailsResult> r7 = com.unionpay.tsmservice.mi.result.TransactionDetailsResult.class
                java.lang.ClassLoader r7 = r7.getClassLoader()
                android.os.Parcelable r7 = r2.readParcelable(r7)
                com.unionpay.tsmservice.mi.result.TransactionDetailsResult r7 = (com.unionpay.tsmservice.mi.result.TransactionDetailsResult) r7
                goto L_0x00ee
            L_0x006c:
                java.lang.Class<com.unionpay.tsmservice.mi.result.AddCardResult> r7 = com.unionpay.tsmservice.mi.result.AddCardResult.class
                java.lang.ClassLoader r7 = r7.getClassLoader()
                android.os.Parcelable r7 = r2.readParcelable(r7)
                com.unionpay.tsmservice.mi.result.AddCardResult r7 = (com.unionpay.tsmservice.mi.result.AddCardResult) r7
                goto L_0x00ee
            L_0x007a:
                java.lang.Class<com.unionpay.tsmservice.mi.result.GetSeIdResult> r7 = com.unionpay.tsmservice.mi.result.GetSeIdResult.class
                java.lang.ClassLoader r7 = r7.getClassLoader()
                android.os.Parcelable r7 = r2.readParcelable(r7)
                com.unionpay.tsmservice.mi.result.GetSeIdResult r7 = (com.unionpay.tsmservice.mi.result.GetSeIdResult) r7
                goto L_0x00ee
            L_0x0087:
                java.lang.Class<com.unionpay.tsmservice.mi.result.PinRequestResult> r7 = com.unionpay.tsmservice.mi.result.PinRequestResult.class
                java.lang.ClassLoader r7 = r7.getClassLoader()
                android.os.Parcelable r7 = r2.readParcelable(r7)
                com.unionpay.tsmservice.mi.result.PayResultNotifyResult r7 = (com.unionpay.tsmservice.mi.result.PayResultNotifyResult) r7
                goto L_0x00ee
            L_0x0094:
                java.lang.Class<com.unionpay.tsmservice.mi.result.PinRequestResult> r7 = com.unionpay.tsmservice.mi.result.PinRequestResult.class
                java.lang.ClassLoader r7 = r7.getClassLoader()
                android.os.Parcelable r7 = r2.readParcelable(r7)
                com.unionpay.tsmservice.mi.result.PinRequestResult r7 = (com.unionpay.tsmservice.mi.result.PinRequestResult) r7
                goto L_0x00ee
            L_0x00a1:
                java.lang.Class<com.unionpay.tsmservice.mi.result.OnlinePaymentVerifyResult> r7 = com.unionpay.tsmservice.mi.result.OnlinePaymentVerifyResult.class
                java.lang.ClassLoader r7 = r7.getClassLoader()
                android.os.Parcelable r7 = r2.readParcelable(r7)
                com.unionpay.tsmservice.mi.result.OnlinePaymentVerifyResult r7 = (com.unionpay.tsmservice.mi.result.OnlinePaymentVerifyResult) r7
                goto L_0x00ee
            L_0x00ae:
                java.lang.Class<com.unionpay.tsmservice.mi.result.VendorPayStatusResult> r7 = com.unionpay.tsmservice.mi.result.VendorPayStatusResult.class
                java.lang.ClassLoader r7 = r7.getClassLoader()
                android.os.Parcelable r7 = r2.readParcelable(r7)
                com.unionpay.tsmservice.mi.result.VendorPayStatusResult r7 = (com.unionpay.tsmservice.mi.result.VendorPayStatusResult) r7
                goto L_0x00ee
            L_0x00bb:
                java.lang.Class<com.unionpay.tsmservice.mi.result.AcquireSeAppListResult> r7 = com.unionpay.tsmservice.mi.result.AcquireSeAppListResult.class
                java.lang.ClassLoader r7 = r7.getClassLoader()
                android.os.Parcelable r7 = r2.readParcelable(r7)
                com.unionpay.tsmservice.mi.result.AcquireSeAppListResult r7 = (com.unionpay.tsmservice.mi.result.AcquireSeAppListResult) r7
                goto L_0x00ee
            L_0x00c8:
                java.lang.Class<com.unionpay.tsmservice.mi.result.GetEncryptDataResult> r7 = com.unionpay.tsmservice.mi.result.GetEncryptDataResult.class
                java.lang.ClassLoader r7 = r7.getClassLoader()
                android.os.Parcelable r7 = r2.readParcelable(r7)
                com.unionpay.tsmservice.mi.result.GetEncryptDataResult r7 = (com.unionpay.tsmservice.mi.result.GetEncryptDataResult) r7
                goto L_0x00ee
            L_0x00d5:
                java.lang.Class<com.unionpay.tsmservice.mi.result.EncryptDataResult> r7 = com.unionpay.tsmservice.mi.result.EncryptDataResult.class
                java.lang.ClassLoader r7 = r7.getClassLoader()
                android.os.Parcelable r7 = r2.readParcelable(r7)
                com.unionpay.tsmservice.mi.result.EncryptDataResult r7 = (com.unionpay.tsmservice.mi.result.EncryptDataResult) r7
                goto L_0x00ee
            L_0x00e2:
                java.lang.Class<com.unionpay.tsmservice.mi.result.InitResult> r7 = com.unionpay.tsmservice.mi.result.InitResult.class
                java.lang.ClassLoader r7 = r7.getClassLoader()
                android.os.Parcelable r7 = r2.readParcelable(r7)
                com.unionpay.tsmservice.mi.result.InitResult r7 = (com.unionpay.tsmservice.mi.result.InitResult) r7
            L_0x00ee:
                java.lang.String r0 = "result"
                r1.putParcelable(r0, r7)
            L_0x00f3:
                r7 = r1
            L_0x00f4:
                r2.recycle()
                com.unionpay.tsmservice.mi.UPTsmAddon r0 = com.unionpay.tsmservice.mi.UPTsmAddon.this
                int r1 = r6.b
                java.util.HashMap r0 = com.unionpay.tsmservice.mi.UPTsmAddon.a((com.unionpay.tsmservice.mi.UPTsmAddon) r0, (int) r1)
                int r1 = r6.c
                java.lang.String r1 = java.lang.String.valueOf(r1)
                java.lang.Object r0 = r0.get(r1)
                com.unionpay.tsmservice.mi.ITsmCallback r0 = (com.unionpay.tsmservice.mi.ITsmCallback) r0
                com.unionpay.tsmservice.mi.UPTsmAddon.a((com.unionpay.tsmservice.mi.ITsmCallback) r0, (android.os.Bundle) r7)
                com.unionpay.tsmservice.mi.UPTsmAddon r7 = com.unionpay.tsmservice.mi.UPTsmAddon.this
                int r0 = r6.b
                java.util.HashMap r7 = com.unionpay.tsmservice.mi.UPTsmAddon.a((com.unionpay.tsmservice.mi.UPTsmAddon) r7, (int) r0)
                int r0 = r6.c
                java.lang.String r0 = java.lang.String.valueOf(r0)
                r7.remove(r0)
                com.unionpay.tsmservice.mi.UPTsmAddon r7 = com.unionpay.tsmservice.mi.UPTsmAddon.this
                int r0 = r6.b
                java.util.HashMap r7 = com.unionpay.tsmservice.mi.UPTsmAddon.a((com.unionpay.tsmservice.mi.UPTsmAddon) r7, (int) r0)
                boolean r7 = r7.isEmpty()
                if (r7 == 0) goto L_0x0137
                com.unionpay.tsmservice.mi.UPTsmAddon r7 = com.unionpay.tsmservice.mi.UPTsmAddon.this
                int[] r7 = r7.w
                int r0 = r6.b
                r7[r0] = r5
            L_0x0137:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.mi.UPTsmAddon.b.onResult(android.os.Bundle):void");
        }
    }

    static {
        try {
            System.loadLibrary("uptsmaddonmi");
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
        }
    }

    private UPTsmAddon(Context context) {
        this.c = context;
        this.w = new int[19];
        if (!a(context)) {
            throw new RuntimeException();
        }
    }

    private static int a(int i2, RequestParams requestParams, ITsmCallback iTsmCallback) {
        return new SessionKeyReExchange(f9836a, i2, requestParams, iTsmCallback).reExchangeKey();
    }

    private static int a(int i2, SafetyKeyboardRequestParams safetyKeyboardRequestParams, int i3, OnSafetyKeyboardCallback onSafetyKeyboardCallback, Context context) {
        return new SessionKeyReExchange(f9836a, i2, safetyKeyboardRequestParams, i3, onSafetyKeyboardCallback, context).reExchangeKey();
    }

    private static int a(RequestParams requestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) {
        return new SessionKeyReExchange(f9836a, 16, requestParams, iTsmCallback, iTsmProgressCallback).reExchangeKey();
    }

    private static String a(Bundle bundle) {
        String str = "";
        Parcel obtain = Parcel.obtain();
        obtain.writeBundle(bundle);
        byte[] marshall = obtain.marshall();
        if (!(marshall == null || marshall.length == 0)) {
            str = c(Base64.encodeToString(marshall, 0));
        }
        obtain.recycle();
        return str;
    }

    static /* synthetic */ HashMap a(UPTsmAddon uPTsmAddon, int i2) {
        switch (i2) {
            case 0:
                return uPTsmAddon.g;
            case 1:
                return uPTsmAddon.h;
            case 2:
                return uPTsmAddon.i;
            case 6:
                return uPTsmAddon.k;
            case 7:
                return uPTsmAddon.l;
            case 8:
                return uPTsmAddon.m;
            case 9:
                return uPTsmAddon.n;
            case 10:
                return uPTsmAddon.o;
            case 11:
                return uPTsmAddon.p;
            case 12:
                return uPTsmAddon.q;
            case 14:
                return uPTsmAddon.r;
            case 15:
                return uPTsmAddon.s;
            case 16:
                return uPTsmAddon.t;
            case 17:
                return uPTsmAddon.u;
            case 18:
                return uPTsmAddon.v;
            default:
                return null;
        }
    }

    static /* synthetic */ void a() {
        if (b != null && b.size() > 0) {
            Iterator it = b.iterator();
            while (it.hasNext()) {
                UPTsmConnectionListener uPTsmConnectionListener = (UPTsmConnectionListener) it.next();
                if (uPTsmConnectionListener != null) {
                    uPTsmConnectionListener.onTsmConnected();
                }
            }
        }
    }

    static /* synthetic */ void a(ITsmActivityCallback iTsmActivityCallback, String str, String str2, int i2, Bundle bundle) {
        if (iTsmActivityCallback != null) {
            try {
                iTsmActivityCallback.startActivity(str, str2, i2, bundle);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    static /* synthetic */ void a(ITsmCallback iTsmCallback, Bundle bundle) {
        if (iTsmCallback != null) {
            try {
                String string = bundle.getString("errorCode");
                if ("10000".equals(string)) {
                    iTsmCallback.onResult(bundle);
                } else {
                    iTsmCallback.onError(string, bundle.getString("errorDesc"));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private static boolean a(Context context) {
        try {
            return IUPJniInterface.iJE(context);
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            return false;
        }
    }

    static /* synthetic */ HashMap b(UPTsmAddon uPTsmAddon, int i2) {
        if (i2 != 1000) {
            return null;
        }
        return uPTsmAddon.j;
    }

    static /* synthetic */ void b() {
        if (b != null && b.size() > 0) {
            Iterator it = b.iterator();
            while (it.hasNext()) {
                UPTsmConnectionListener uPTsmConnectionListener = (UPTsmConnectionListener) it.next();
                if (uPTsmConnectionListener != null) {
                    uPTsmConnectionListener.onTsmDisconnected();
                }
            }
        }
    }

    private static boolean b(String str) {
        try {
            return IUPJniInterface.cSKV(str);
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private static String c(String str) {
        try {
            return IUPJniInterface.eMG(str);
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private boolean c() {
        String f2 = f("com.unionpay.tsmservice.mi");
        return f2 != null && f2.compareTo(Constant.APK_VERSION_010002) >= 0;
    }

    /* access modifiers changed from: private */
    public static String d(String str) {
        try {
            return IUPJniInterface.dMG(str);
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private boolean e(String str) {
        String f2 = f("com.unionpay.tsmservice.mi");
        return f2 != null && f2.compareTo(str) >= 0;
    }

    private String f(String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = this.c.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            packageInfo = null;
        }
        if (packageInfo != null) {
            return packageInfo.versionName;
        }
        return null;
    }

    private static String g(String str) {
        try {
            JSONObject jSONObject = TextUtils.isEmpty(str) ? new JSONObject() : new JSONObject(str);
            jSONObject.put("jarVersionCode", 19);
            return jSONObject.toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return str;
        }
    }

    public static synchronized UPTsmAddon getInstance(Context context) {
        synchronized (UPTsmAddon.class) {
            if (context == null) {
                return null;
            }
            if (f9836a == null) {
                f9836a = new UPTsmAddon(context.getApplicationContext());
            }
            if (b == null) {
                b = new CopyOnWriteArrayList();
            }
            UPTsmAddon uPTsmAddon = f9836a;
            return uPTsmAddon;
        }
    }

    private String h(String str) {
        try {
            JSONObject jSONObject = TextUtils.isEmpty(str) ? new JSONObject() : new JSONObject(str);
            jSONObject.put("packageName", this.c.getPackageName());
            return jSONObject.toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return str;
        }
    }

    public static boolean isAppRunInBackground(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return true;
        }
        for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
            if (TextUtils.equals(next.processName, context.getPackageName()) && next.importance == 100) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0005, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int acquireSEAppList(com.unionpay.tsmservice.mi.request.AcquireSEAppListRequestParams r8, com.unionpay.tsmservice.mi.ITsmCallback r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r9 != 0) goto L_0x0006
            r8 = -3
        L_0x0004:
            monitor-exit(r7)
            return r8
        L_0x0006:
            java.lang.String r0 = "01.00.08"
            boolean r0 = r7.e(r0)     // Catch:{ all -> 0x00a6 }
            if (r0 != 0) goto L_0x0010
            r8 = -8
            goto L_0x0004
        L_0x0010:
            com.unionpay.tsmservice.mi.ITsmService r0 = r7.e     // Catch:{ all -> 0x00a6 }
            if (r0 == 0) goto L_0x00a3
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x00a6 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00a6 }
            boolean r0 = b((java.lang.String) r0)     // Catch:{ all -> 0x00a6 }
            r1 = 6
            if (r0 == 0) goto L_0x009d
            com.unionpay.tsmservice.mi.request.AcquireSEAppListRequestParams r0 = new com.unionpay.tsmservice.mi.request.AcquireSEAppListRequestParams     // Catch:{ all -> 0x00a6 }
            r0.<init>()     // Catch:{ all -> 0x00a6 }
            java.lang.String r2 = ""
            if (r8 == 0) goto L_0x0045
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x00a6 }
            android.os.Bundle r3 = r8.getParams()     // Catch:{ all -> 0x00a6 }
            if (r3 == 0) goto L_0x0045
            android.os.Bundle r4 = new android.os.Bundle     // Catch:{ all -> 0x00a6 }
            r4.<init>()     // Catch:{ all -> 0x00a6 }
            java.lang.String r5 = "encryptData"
            java.lang.String r3 = a((android.os.Bundle) r3)     // Catch:{ all -> 0x00a6 }
            r4.putString(r5, r3)     // Catch:{ all -> 0x00a6 }
            r0.setParams(r4)     // Catch:{ all -> 0x00a6 }
        L_0x0045:
            java.lang.String r2 = g(r2)     // Catch:{ all -> 0x00a6 }
            java.lang.String r2 = r7.h(r2)     // Catch:{ all -> 0x00a6 }
            java.lang.String r2 = c((java.lang.String) r2)     // Catch:{ all -> 0x00a6 }
            r0.setReserve(r2)     // Catch:{ all -> 0x00a6 }
            java.util.HashMap r2 = r7.k     // Catch:{ all -> 0x00a6 }
            int[] r3 = r7.w     // Catch:{ all -> 0x00a6 }
            r3 = r3[r1]     // Catch:{ all -> 0x00a6 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00a6 }
            r2.put(r3, r9)     // Catch:{ all -> 0x00a6 }
            com.unionpay.tsmservice.mi.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x0093 }
            com.unionpay.tsmservice.mi.UPTsmAddon$b r3 = new com.unionpay.tsmservice.mi.UPTsmAddon$b     // Catch:{ Exception -> 0x0093 }
            int[] r4 = r7.w     // Catch:{ Exception -> 0x0093 }
            r5 = r4[r1]     // Catch:{ Exception -> 0x0093 }
            int r6 = r5 + 1
            r4[r1] = r6     // Catch:{ Exception -> 0x0093 }
            r4 = 0
            r3.<init>(r7, r1, r5, r4)     // Catch:{ Exception -> 0x0093 }
            int r0 = r2.acquireSEAppList(r0, r3)     // Catch:{ Exception -> 0x0093 }
            if (r0 == 0) goto L_0x0088
            java.util.HashMap r2 = r7.k     // Catch:{ all -> 0x00a6 }
            int[] r3 = r7.w     // Catch:{ all -> 0x00a6 }
            r4 = r3[r1]     // Catch:{ all -> 0x00a6 }
            int r4 = r4 + -1
            r3[r1] = r4     // Catch:{ all -> 0x00a6 }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x00a6 }
            r2.remove(r3)     // Catch:{ all -> 0x00a6 }
        L_0x0088:
            r2 = -2
            if (r2 != r0) goto L_0x0091
            int r8 = a((int) r1, (com.unionpay.tsmservice.mi.request.RequestParams) r8, (com.unionpay.tsmservice.mi.ITsmCallback) r9)     // Catch:{ all -> 0x00a6 }
            monitor-exit(r7)
            return r8
        L_0x0091:
            monitor-exit(r7)
            return r0
        L_0x0093:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x00a6 }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x00a6 }
            r8.<init>()     // Catch:{ all -> 0x00a6 }
            throw r8     // Catch:{ all -> 0x00a6 }
        L_0x009d:
            int r8 = a((int) r1, (com.unionpay.tsmservice.mi.request.RequestParams) r8, (com.unionpay.tsmservice.mi.ITsmCallback) r9)     // Catch:{ all -> 0x00a6 }
            monitor-exit(r7)
            return r8
        L_0x00a3:
            r8 = -1
            goto L_0x0004
        L_0x00a6:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.mi.UPTsmAddon.acquireSEAppList(com.unionpay.tsmservice.mi.request.AcquireSEAppListRequestParams, com.unionpay.tsmservice.mi.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0011, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int addCardToVendorPay(com.unionpay.tsmservice.mi.request.AddCardToVendorPayRequestParams r8, com.unionpay.tsmservice.mi.ITsmCallback r9, com.unionpay.tsmservice.mi.ITsmProgressCallback r10) {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r8 == 0) goto L_0x00a8
            if (r9 != 0) goto L_0x0007
            goto L_0x00a8
        L_0x0007:
            java.lang.String r0 = "01.00.11"
            boolean r0 = r7.e(r0)     // Catch:{ all -> 0x00a5 }
            if (r0 != 0) goto L_0x0012
            r8 = -8
        L_0x0010:
            monitor-exit(r7)
            return r8
        L_0x0012:
            com.unionpay.tsmservice.mi.ITsmService r0 = r7.e     // Catch:{ all -> 0x00a5 }
            if (r0 == 0) goto L_0x00a2
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x00a5 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00a5 }
            boolean r0 = b((java.lang.String) r0)     // Catch:{ all -> 0x00a5 }
            if (r0 == 0) goto L_0x009c
            com.unionpay.tsmservice.mi.request.AddCardToVendorPayRequestParams r0 = new com.unionpay.tsmservice.mi.request.AddCardToVendorPayRequestParams     // Catch:{ all -> 0x00a5 }
            r0.<init>()     // Catch:{ all -> 0x00a5 }
            android.os.Bundle r1 = r8.getParams()     // Catch:{ all -> 0x00a5 }
            if (r1 == 0) goto L_0x003e
            android.os.Bundle r2 = new android.os.Bundle     // Catch:{ all -> 0x00a5 }
            r2.<init>()     // Catch:{ all -> 0x00a5 }
            java.lang.String r3 = "encryptData"
            java.lang.String r1 = a((android.os.Bundle) r1)     // Catch:{ all -> 0x00a5 }
            r2.putString(r3, r1)     // Catch:{ all -> 0x00a5 }
            r0.setParams(r2)     // Catch:{ all -> 0x00a5 }
        L_0x003e:
            java.lang.String r1 = r8.getReserve()     // Catch:{ all -> 0x00a5 }
            java.lang.String r1 = g(r1)     // Catch:{ all -> 0x00a5 }
            java.lang.String r1 = r7.h(r1)     // Catch:{ all -> 0x00a5 }
            java.lang.String r1 = c((java.lang.String) r1)     // Catch:{ all -> 0x00a5 }
            r0.setReserve(r1)     // Catch:{ all -> 0x00a5 }
            java.util.HashMap r1 = r7.t     // Catch:{ all -> 0x00a5 }
            int[] r2 = r7.w     // Catch:{ all -> 0x00a5 }
            r3 = 16
            r2 = r2[r3]     // Catch:{ all -> 0x00a5 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x00a5 }
            r1.put(r2, r9)     // Catch:{ all -> 0x00a5 }
            com.unionpay.tsmservice.mi.ITsmService r1 = r7.e     // Catch:{ Exception -> 0x0092 }
            com.unionpay.tsmservice.mi.UPTsmAddon$b r2 = new com.unionpay.tsmservice.mi.UPTsmAddon$b     // Catch:{ Exception -> 0x0092 }
            int[] r4 = r7.w     // Catch:{ Exception -> 0x0092 }
            r5 = r4[r3]     // Catch:{ Exception -> 0x0092 }
            int r6 = r5 + 1
            r4[r3] = r6     // Catch:{ Exception -> 0x0092 }
            r4 = 0
            r2.<init>(r7, r3, r5, r4)     // Catch:{ Exception -> 0x0092 }
            int r0 = r1.addCardToVendorPay(r0, r2, r10)     // Catch:{ Exception -> 0x0092 }
            if (r0 == 0) goto L_0x0087
            java.util.HashMap r1 = r7.t     // Catch:{ all -> 0x00a5 }
            int[] r2 = r7.w     // Catch:{ all -> 0x00a5 }
            r4 = r2[r3]     // Catch:{ all -> 0x00a5 }
            int r4 = r4 + -1
            r2[r3] = r4     // Catch:{ all -> 0x00a5 }
            java.lang.String r2 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x00a5 }
            r1.remove(r2)     // Catch:{ all -> 0x00a5 }
        L_0x0087:
            r1 = -2
            if (r1 != r0) goto L_0x0090
            int r8 = a((com.unionpay.tsmservice.mi.request.RequestParams) r8, (com.unionpay.tsmservice.mi.ITsmCallback) r9, (com.unionpay.tsmservice.mi.ITsmProgressCallback) r10)     // Catch:{ all -> 0x00a5 }
            monitor-exit(r7)
            return r8
        L_0x0090:
            monitor-exit(r7)
            return r0
        L_0x0092:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x00a5 }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x00a5 }
            r8.<init>()     // Catch:{ all -> 0x00a5 }
            throw r8     // Catch:{ all -> 0x00a5 }
        L_0x009c:
            int r8 = a((com.unionpay.tsmservice.mi.request.RequestParams) r8, (com.unionpay.tsmservice.mi.ITsmCallback) r9, (com.unionpay.tsmservice.mi.ITsmProgressCallback) r10)     // Catch:{ all -> 0x00a5 }
            monitor-exit(r7)
            return r8
        L_0x00a2:
            r8 = -1
            goto L_0x0010
        L_0x00a5:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        L_0x00a8:
            r8 = -3
            goto L_0x0010
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.mi.UPTsmAddon.addCardToVendorPay(com.unionpay.tsmservice.mi.request.AddCardToVendorPayRequestParams, com.unionpay.tsmservice.mi.ITsmCallback, com.unionpay.tsmservice.mi.ITsmProgressCallback):int");
    }

    public synchronized void addConnectionListener(UPTsmConnectionListener uPTsmConnectionListener) {
        if (uPTsmConnectionListener != null) {
            b.add(uPTsmConnectionListener);
        }
    }

    public boolean bind() {
        if (this.d == null) {
            this.d = new ServiceConnection() {
                public final synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    boolean unused = UPTsmAddon.this.f = true;
                    ITsmService unused2 = UPTsmAddon.this.e = ITsmService.Stub.asInterface(iBinder);
                    UPTsmAddon.this.y.sendEmptyMessage(0);
                }

                public final synchronized void onServiceDisconnected(ComponentName componentName) {
                    boolean unused = UPTsmAddon.this.f = false;
                    ITsmService unused2 = UPTsmAddon.this.e = null;
                    UPTsmAddon.this.y.sendEmptyMessage(1);
                }
            };
        }
        if (this.f) {
            return true;
        }
        Intent intent = new Intent("com.unionpay.tsmservice.mi.UPTsmService");
        intent.setPackage("com.unionpay.tsmservice.mi");
        return this.c.bindService(intent, this.d, 1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000b, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int cancelPay() {
        /*
            r5 = this;
            monitor-enter(r5)
            java.lang.String r0 = "01.00.08"
            boolean r0 = r5.e(r0)     // Catch:{ all -> 0x0059 }
            if (r0 != 0) goto L_0x000c
            r0 = -8
        L_0x000a:
            monitor-exit(r5)
            return r0
        L_0x000c:
            com.unionpay.tsmservice.mi.ITsmService r0 = r5.e     // Catch:{ all -> 0x0059 }
            if (r0 == 0) goto L_0x0057
            android.content.Context r0 = r5.c     // Catch:{ all -> 0x0059 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x0059 }
            boolean r0 = b((java.lang.String) r0)     // Catch:{ all -> 0x0059 }
            r1 = 0
            r2 = 13
            r3 = 0
            if (r0 == 0) goto L_0x0051
            com.unionpay.tsmservice.mi.request.CancelPayRequestParams r0 = new com.unionpay.tsmservice.mi.request.CancelPayRequestParams     // Catch:{ all -> 0x0059 }
            r0.<init>()     // Catch:{ all -> 0x0059 }
            java.lang.String r4 = ""
            java.lang.String r4 = g(r4)     // Catch:{ all -> 0x0059 }
            java.lang.String r4 = r5.h(r4)     // Catch:{ all -> 0x0059 }
            java.lang.String r4 = c((java.lang.String) r4)     // Catch:{ all -> 0x0059 }
            r0.setReserve(r4)     // Catch:{ all -> 0x0059 }
            com.unionpay.tsmservice.mi.ITsmService r4 = r5.e     // Catch:{ Exception -> 0x0047 }
            int r0 = r4.cancelPay(r0)     // Catch:{ Exception -> 0x0047 }
            r4 = -2
            if (r4 != r0) goto L_0x0045
            int r0 = a((int) r2, (com.unionpay.tsmservice.mi.request.SafetyKeyboardRequestParams) r3, (int) r1, (com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback) r3, (android.content.Context) r3)     // Catch:{ all -> 0x0059 }
            monitor-exit(r5)
            return r0
        L_0x0045:
            monitor-exit(r5)
            return r0
        L_0x0047:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0059 }
            android.os.RemoteException r0 = new android.os.RemoteException     // Catch:{ all -> 0x0059 }
            r0.<init>()     // Catch:{ all -> 0x0059 }
            throw r0     // Catch:{ all -> 0x0059 }
        L_0x0051:
            int r0 = a((int) r2, (com.unionpay.tsmservice.mi.request.SafetyKeyboardRequestParams) r3, (int) r1, (com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback) r3, (android.content.Context) r3)     // Catch:{ all -> 0x0059 }
            monitor-exit(r5)
            return r0
        L_0x0057:
            r0 = -1
            goto L_0x000a
        L_0x0059:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.mi.UPTsmAddon.cancelPay():int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0005, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int cardListStatusChanged(com.unionpay.tsmservice.mi.request.CardListStatusChangedRequestParams r8, com.unionpay.tsmservice.mi.ITsmCallback r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r9 != 0) goto L_0x0006
            r8 = -3
        L_0x0004:
            monitor-exit(r7)
            return r8
        L_0x0006:
            java.lang.String r0 = "01.00.08"
            boolean r0 = r7.e(r0)     // Catch:{ all -> 0x008f }
            if (r0 != 0) goto L_0x0010
            r8 = -8
            goto L_0x0004
        L_0x0010:
            com.unionpay.tsmservice.mi.ITsmService r0 = r7.e     // Catch:{ all -> 0x008f }
            if (r0 == 0) goto L_0x008c
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x008f }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x008f }
            boolean r0 = b((java.lang.String) r0)     // Catch:{ all -> 0x008f }
            r1 = 7
            if (r0 == 0) goto L_0x0086
            com.unionpay.tsmservice.mi.request.CardListStatusChangedRequestParams r0 = new com.unionpay.tsmservice.mi.request.CardListStatusChangedRequestParams     // Catch:{ all -> 0x008f }
            r0.<init>()     // Catch:{ all -> 0x008f }
            java.lang.String r2 = ""
            if (r8 == 0) goto L_0x002e
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x008f }
        L_0x002e:
            java.lang.String r2 = g(r2)     // Catch:{ all -> 0x008f }
            java.lang.String r2 = r7.h(r2)     // Catch:{ all -> 0x008f }
            java.lang.String r2 = c((java.lang.String) r2)     // Catch:{ all -> 0x008f }
            r0.setReserve(r2)     // Catch:{ all -> 0x008f }
            java.util.HashMap r2 = r7.l     // Catch:{ all -> 0x008f }
            int[] r3 = r7.w     // Catch:{ all -> 0x008f }
            r3 = r3[r1]     // Catch:{ all -> 0x008f }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x008f }
            r2.put(r3, r9)     // Catch:{ all -> 0x008f }
            com.unionpay.tsmservice.mi.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x007c }
            com.unionpay.tsmservice.mi.UPTsmAddon$b r3 = new com.unionpay.tsmservice.mi.UPTsmAddon$b     // Catch:{ Exception -> 0x007c }
            int[] r4 = r7.w     // Catch:{ Exception -> 0x007c }
            r5 = r4[r1]     // Catch:{ Exception -> 0x007c }
            int r6 = r5 + 1
            r4[r1] = r6     // Catch:{ Exception -> 0x007c }
            r4 = 0
            r3.<init>(r7, r1, r5, r4)     // Catch:{ Exception -> 0x007c }
            int r0 = r2.cardListStatusChanged(r0, r3)     // Catch:{ Exception -> 0x007c }
            if (r0 == 0) goto L_0x0071
            java.util.HashMap r2 = r7.l     // Catch:{ all -> 0x008f }
            int[] r3 = r7.w     // Catch:{ all -> 0x008f }
            r4 = r3[r1]     // Catch:{ all -> 0x008f }
            int r4 = r4 + -1
            r3[r1] = r4     // Catch:{ all -> 0x008f }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x008f }
            r2.remove(r3)     // Catch:{ all -> 0x008f }
        L_0x0071:
            r2 = -2
            if (r2 != r0) goto L_0x007a
            int r8 = a((int) r1, (com.unionpay.tsmservice.mi.request.RequestParams) r8, (com.unionpay.tsmservice.mi.ITsmCallback) r9)     // Catch:{ all -> 0x008f }
            monitor-exit(r7)
            return r8
        L_0x007a:
            monitor-exit(r7)
            return r0
        L_0x007c:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x008f }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x008f }
            r8.<init>()     // Catch:{ all -> 0x008f }
            throw r8     // Catch:{ all -> 0x008f }
        L_0x0086:
            int r8 = a((int) r1, (com.unionpay.tsmservice.mi.request.RequestParams) r8, (com.unionpay.tsmservice.mi.ITsmCallback) r9)     // Catch:{ all -> 0x008f }
            monitor-exit(r7)
            return r8
        L_0x008c:
            r8 = -1
            goto L_0x0004
        L_0x008f:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.mi.UPTsmAddon.cardListStatusChanged(com.unionpay.tsmservice.mi.request.CardListStatusChangedRequestParams, com.unionpay.tsmservice.mi.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003f, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r5.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0048, code lost:
        throw new android.os.RemoteException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005a, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r5.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0063, code lost:
        throw new android.os.RemoteException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x006c, code lost:
        return r5;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:13:0x0038, B:19:0x0049] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int clearEncryptData(int r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 2000(0x7d0, float:2.803E-42)
            if (r5 < r0) goto L_0x0070
            r0 = 2002(0x7d2, float:2.805E-42)
            if (r5 <= r0) goto L_0x000a
            goto L_0x0070
        L_0x000a:
            com.unionpay.tsmservice.mi.ITsmService r0 = r4.e     // Catch:{ all -> 0x006d }
            if (r0 == 0) goto L_0x006a
            android.content.Context r0 = r4.c     // Catch:{ all -> 0x006d }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x006d }
            boolean r0 = b((java.lang.String) r0)     // Catch:{ all -> 0x006d }
            r1 = 4
            r2 = 0
            if (r0 == 0) goto L_0x0064
            boolean r0 = r4.c()     // Catch:{ all -> 0x006d }
            if (r0 == 0) goto L_0x0049
            com.unionpay.tsmservice.mi.request.ClearEncryptDataRequestParams r0 = new com.unionpay.tsmservice.mi.request.ClearEncryptDataRequestParams     // Catch:{ all -> 0x006d }
            r0.<init>()     // Catch:{ all -> 0x006d }
            java.lang.String r3 = ""
            java.lang.String r3 = g(r3)     // Catch:{ all -> 0x006d }
            java.lang.String r3 = r4.h(r3)     // Catch:{ all -> 0x006d }
            java.lang.String r3 = c((java.lang.String) r3)     // Catch:{ all -> 0x006d }
            r0.setReserve(r3)     // Catch:{ all -> 0x006d }
            com.unionpay.tsmservice.mi.ITsmService r3 = r4.e     // Catch:{ Exception -> 0x003f }
            int r0 = r3.clearKeyboardEncryptData(r0, r5)     // Catch:{ Exception -> 0x003f }
            goto L_0x004f
        L_0x003f:
            r5 = move-exception
            r5.printStackTrace()     // Catch:{ all -> 0x006d }
            android.os.RemoteException r5 = new android.os.RemoteException     // Catch:{ all -> 0x006d }
            r5.<init>()     // Catch:{ all -> 0x006d }
            throw r5     // Catch:{ all -> 0x006d }
        L_0x0049:
            com.unionpay.tsmservice.mi.ITsmService r0 = r4.e     // Catch:{ Exception -> 0x005a }
            int r0 = r0.clearEncryptData(r5)     // Catch:{ Exception -> 0x005a }
        L_0x004f:
            r3 = -2
            if (r3 != r0) goto L_0x0058
            int r5 = a((int) r1, (com.unionpay.tsmservice.mi.request.SafetyKeyboardRequestParams) r2, (int) r5, (com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback) r2, (android.content.Context) r2)     // Catch:{ all -> 0x006d }
            monitor-exit(r4)
            return r5
        L_0x0058:
            monitor-exit(r4)
            return r0
        L_0x005a:
            r5 = move-exception
            r5.printStackTrace()     // Catch:{ all -> 0x006d }
            android.os.RemoteException r5 = new android.os.RemoteException     // Catch:{ all -> 0x006d }
            r5.<init>()     // Catch:{ all -> 0x006d }
            throw r5     // Catch:{ all -> 0x006d }
        L_0x0064:
            int r5 = a((int) r1, (com.unionpay.tsmservice.mi.request.SafetyKeyboardRequestParams) r2, (int) r5, (com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback) r2, (android.content.Context) r2)     // Catch:{ all -> 0x006d }
            monitor-exit(r4)
            return r5
        L_0x006a:
            r5 = -1
        L_0x006b:
            monitor-exit(r4)
            return r5
        L_0x006d:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        L_0x0070:
            r5 = -3
            goto L_0x006b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.mi.UPTsmAddon.clearEncryptData(int):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00c3, code lost:
        return -3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int encryptData(com.unionpay.tsmservice.mi.request.EncryptDataRequestParams r10, com.unionpay.tsmservice.mi.ITsmCallback r11) {
        /*
            r9 = this;
            monitor-enter(r9)
            r0 = -3
            if (r10 == 0) goto L_0x00c2
            if (r11 != 0) goto L_0x0008
            goto L_0x00c2
        L_0x0008:
            com.unionpay.tsmservice.mi.ITsmService r1 = r9.e     // Catch:{ all -> 0x00bf }
            if (r1 == 0) goto L_0x00bc
            android.content.Context r1 = r9.c     // Catch:{ all -> 0x00bf }
            java.lang.String r1 = r1.getPackageName()     // Catch:{ all -> 0x00bf }
            boolean r1 = b((java.lang.String) r1)     // Catch:{ all -> 0x00bf }
            r2 = 1
            if (r1 == 0) goto L_0x00b6
            com.unionpay.tsmservice.mi.request.EncryptDataRequestParams r1 = new com.unionpay.tsmservice.mi.request.EncryptDataRequestParams     // Catch:{ all -> 0x00bf }
            r1.<init>()     // Catch:{ all -> 0x00bf }
            java.lang.String r3 = r10.getReserve()     // Catch:{ all -> 0x00bf }
            boolean r4 = r9.c()     // Catch:{ all -> 0x00bf }
            if (r4 == 0) goto L_0x0030
            java.lang.String r3 = g(r3)     // Catch:{ all -> 0x00bf }
            java.lang.String r3 = r9.h(r3)     // Catch:{ all -> 0x00bf }
        L_0x0030:
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x00bf }
            if (r4 != 0) goto L_0x003d
            java.lang.String r3 = c((java.lang.String) r3)     // Catch:{ all -> 0x00bf }
            r1.setReserve(r3)     // Catch:{ all -> 0x00bf }
        L_0x003d:
            java.util.List r3 = r10.getData()     // Catch:{ all -> 0x00bf }
            java.util.ArrayList r3 = (java.util.ArrayList) r3     // Catch:{ all -> 0x00bf }
            r4 = 0
            if (r3 == 0) goto L_0x006f
            int r5 = r3.size()     // Catch:{ all -> 0x00bf }
            if (r5 != 0) goto L_0x004e
            monitor-exit(r9)
            return r0
        L_0x004e:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x00bf }
            r0.<init>()     // Catch:{ all -> 0x00bf }
            r6 = 0
        L_0x0054:
            if (r6 >= r5) goto L_0x006c
            java.lang.Object r7 = r3.get(r6)     // Catch:{ all -> 0x00bf }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x00bf }
            boolean r8 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x00bf }
            if (r8 != 0) goto L_0x0069
            java.lang.String r7 = c((java.lang.String) r7)     // Catch:{ all -> 0x00bf }
            r0.add(r7)     // Catch:{ all -> 0x00bf }
        L_0x0069:
            int r6 = r6 + 1
            goto L_0x0054
        L_0x006c:
            r1.setData(r0)     // Catch:{ all -> 0x00bf }
        L_0x006f:
            java.util.HashMap r0 = r9.h     // Catch:{ all -> 0x00bf }
            int[] r3 = r9.w     // Catch:{ all -> 0x00bf }
            r3 = r3[r2]     // Catch:{ all -> 0x00bf }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00bf }
            r0.put(r3, r11)     // Catch:{ all -> 0x00bf }
            com.unionpay.tsmservice.mi.ITsmService r0 = r9.e     // Catch:{ Exception -> 0x00ac }
            com.unionpay.tsmservice.mi.UPTsmAddon$b r3 = new com.unionpay.tsmservice.mi.UPTsmAddon$b     // Catch:{ Exception -> 0x00ac }
            int[] r5 = r9.w     // Catch:{ Exception -> 0x00ac }
            r6 = r5[r2]     // Catch:{ Exception -> 0x00ac }
            int r7 = r6 + 1
            r5[r2] = r7     // Catch:{ Exception -> 0x00ac }
            r3.<init>(r9, r2, r6, r4)     // Catch:{ Exception -> 0x00ac }
            int r0 = r0.encryptData(r1, r3)     // Catch:{ Exception -> 0x00ac }
            if (r0 == 0) goto L_0x00a1
            java.util.HashMap r1 = r9.h     // Catch:{ all -> 0x00bf }
            int[] r3 = r9.w     // Catch:{ all -> 0x00bf }
            r4 = r3[r2]     // Catch:{ all -> 0x00bf }
            int r4 = r4 - r2
            r3[r2] = r4     // Catch:{ all -> 0x00bf }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x00bf }
            r1.remove(r3)     // Catch:{ all -> 0x00bf }
        L_0x00a1:
            r1 = -2
            if (r1 != r0) goto L_0x00aa
            int r10 = a((int) r2, (com.unionpay.tsmservice.mi.request.RequestParams) r10, (com.unionpay.tsmservice.mi.ITsmCallback) r11)     // Catch:{ all -> 0x00bf }
            monitor-exit(r9)
            return r10
        L_0x00aa:
            monitor-exit(r9)
            return r0
        L_0x00ac:
            r10 = move-exception
            r10.printStackTrace()     // Catch:{ all -> 0x00bf }
            android.os.RemoteException r10 = new android.os.RemoteException     // Catch:{ all -> 0x00bf }
            r10.<init>()     // Catch:{ all -> 0x00bf }
            throw r10     // Catch:{ all -> 0x00bf }
        L_0x00b6:
            int r10 = a((int) r2, (com.unionpay.tsmservice.mi.request.RequestParams) r10, (com.unionpay.tsmservice.mi.ITsmCallback) r11)     // Catch:{ all -> 0x00bf }
            monitor-exit(r9)
            return r10
        L_0x00bc:
            r10 = -1
            monitor-exit(r9)
            return r10
        L_0x00bf:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        L_0x00c2:
            monitor-exit(r9)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.mi.UPTsmAddon.encryptData(com.unionpay.tsmservice.mi.request.EncryptDataRequestParams, com.unionpay.tsmservice.mi.ITsmCallback):int");
    }

    public int exchangeKey(String str, String[] strArr) {
        if (TextUtils.isEmpty(str) || strArr == null || strArr.length == 0) {
            return -3;
        }
        if (this.e == null) {
            return -1;
        }
        try {
            return this.e.exchangeKey(str, strArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            throw new RemoteException();
        }
    }

    public Context getContext() {
        return this.c;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00b8, code lost:
        return -3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00bd, code lost:
        return -3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int getEncryptData(com.unionpay.tsmservice.mi.request.GetEncryptDataRequestParams r8, com.unionpay.tsmservice.mi.ITsmCallback r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            r0 = -3
            if (r9 == 0) goto L_0x00bc
            if (r8 != 0) goto L_0x0008
            goto L_0x00bc
        L_0x0008:
            int r1 = r8.getType()     // Catch:{ all -> 0x00b9 }
            java.lang.String r2 = r8.getPan()     // Catch:{ all -> 0x00b9 }
            r3 = 2000(0x7d0, float:2.803E-42)
            if (r1 < r3) goto L_0x00b7
            r4 = 2001(0x7d1, float:2.804E-42)
            if (r1 <= r4) goto L_0x001a
            goto L_0x00b7
        L_0x001a:
            if (r1 != r3) goto L_0x0024
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00b9 }
            if (r4 == 0) goto L_0x0024
            monitor-exit(r7)
            return r0
        L_0x0024:
            com.unionpay.tsmservice.mi.ITsmService r0 = r7.e     // Catch:{ all -> 0x00b9 }
            if (r0 == 0) goto L_0x00b4
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x00b9 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00b9 }
            boolean r0 = b((java.lang.String) r0)     // Catch:{ all -> 0x00b9 }
            r4 = 2
            if (r0 == 0) goto L_0x00ae
            com.unionpay.tsmservice.mi.request.GetEncryptDataRequestParams r0 = new com.unionpay.tsmservice.mi.request.GetEncryptDataRequestParams     // Catch:{ all -> 0x00b9 }
            r0.<init>()     // Catch:{ all -> 0x00b9 }
            if (r1 != r3) goto L_0x0043
            java.lang.String r2 = c((java.lang.String) r2)     // Catch:{ all -> 0x00b9 }
            r0.setPan(r2)     // Catch:{ all -> 0x00b9 }
        L_0x0043:
            r0.setType(r1)     // Catch:{ all -> 0x00b9 }
            java.lang.String r1 = r8.getReserve()     // Catch:{ all -> 0x00b9 }
            boolean r2 = r7.c()     // Catch:{ all -> 0x00b9 }
            if (r2 == 0) goto L_0x0058
            java.lang.String r1 = g(r1)     // Catch:{ all -> 0x00b9 }
            java.lang.String r1 = r7.h(r1)     // Catch:{ all -> 0x00b9 }
        L_0x0058:
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x00b9 }
            if (r2 != 0) goto L_0x0065
            java.lang.String r1 = c((java.lang.String) r1)     // Catch:{ all -> 0x00b9 }
            r0.setReserve(r1)     // Catch:{ all -> 0x00b9 }
        L_0x0065:
            java.util.HashMap r1 = r7.i     // Catch:{ all -> 0x00b9 }
            int[] r2 = r7.w     // Catch:{ all -> 0x00b9 }
            r2 = r2[r4]     // Catch:{ all -> 0x00b9 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x00b9 }
            r1.put(r2, r9)     // Catch:{ all -> 0x00b9 }
            com.unionpay.tsmservice.mi.ITsmService r1 = r7.e     // Catch:{ Exception -> 0x00a4 }
            com.unionpay.tsmservice.mi.UPTsmAddon$b r2 = new com.unionpay.tsmservice.mi.UPTsmAddon$b     // Catch:{ Exception -> 0x00a4 }
            int[] r3 = r7.w     // Catch:{ Exception -> 0x00a4 }
            r5 = r3[r4]     // Catch:{ Exception -> 0x00a4 }
            int r6 = r5 + 1
            r3[r4] = r6     // Catch:{ Exception -> 0x00a4 }
            r3 = 0
            r2.<init>(r7, r4, r5, r3)     // Catch:{ Exception -> 0x00a4 }
            int r0 = r1.getEncryptData(r0, r2)     // Catch:{ Exception -> 0x00a4 }
            if (r0 == 0) goto L_0x0099
            java.util.HashMap r1 = r7.i     // Catch:{ all -> 0x00b9 }
            int[] r2 = r7.w     // Catch:{ all -> 0x00b9 }
            r3 = r2[r4]     // Catch:{ all -> 0x00b9 }
            int r3 = r3 + -1
            r2[r4] = r3     // Catch:{ all -> 0x00b9 }
            java.lang.String r2 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00b9 }
            r1.remove(r2)     // Catch:{ all -> 0x00b9 }
        L_0x0099:
            r1 = -2
            if (r1 != r0) goto L_0x00a2
            int r8 = a((int) r4, (com.unionpay.tsmservice.mi.request.RequestParams) r8, (com.unionpay.tsmservice.mi.ITsmCallback) r9)     // Catch:{ all -> 0x00b9 }
            monitor-exit(r7)
            return r8
        L_0x00a2:
            monitor-exit(r7)
            return r0
        L_0x00a4:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x00b9 }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x00b9 }
            r8.<init>()     // Catch:{ all -> 0x00b9 }
            throw r8     // Catch:{ all -> 0x00b9 }
        L_0x00ae:
            int r8 = a((int) r4, (com.unionpay.tsmservice.mi.request.RequestParams) r8, (com.unionpay.tsmservice.mi.ITsmCallback) r9)     // Catch:{ all -> 0x00b9 }
            monitor-exit(r7)
            return r8
        L_0x00b4:
            r8 = -1
            monitor-exit(r7)
            return r8
        L_0x00b7:
            monitor-exit(r7)
            return r0
        L_0x00b9:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        L_0x00bc:
            monitor-exit(r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.mi.UPTsmAddon.getEncryptData(com.unionpay.tsmservice.mi.request.GetEncryptDataRequestParams, com.unionpay.tsmservice.mi.ITsmCallback):int");
    }

    public synchronized int getListenerCount() {
        if (b == null) {
            return 0;
        }
        return b.size();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0005, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int getMessageDetails(com.unionpay.tsmservice.mi.request.GetMessageDetailsRequestParams r8, com.unionpay.tsmservice.mi.ITsmCallback r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r9 != 0) goto L_0x0006
            r8 = -3
        L_0x0004:
            monitor-exit(r7)
            return r8
        L_0x0006:
            java.lang.String r0 = "01.00.17"
            boolean r0 = r7.e(r0)     // Catch:{ all -> 0x00a7 }
            if (r0 != 0) goto L_0x0010
            r8 = -8
            goto L_0x0004
        L_0x0010:
            com.unionpay.tsmservice.mi.ITsmService r0 = r7.e     // Catch:{ all -> 0x00a7 }
            if (r0 == 0) goto L_0x00a4
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x00a7 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00a7 }
            boolean r0 = b((java.lang.String) r0)     // Catch:{ all -> 0x00a7 }
            r1 = 18
            if (r0 == 0) goto L_0x009e
            com.unionpay.tsmservice.mi.request.GetMessageDetailsRequestParams r0 = new com.unionpay.tsmservice.mi.request.GetMessageDetailsRequestParams     // Catch:{ all -> 0x00a7 }
            r0.<init>()     // Catch:{ all -> 0x00a7 }
            java.lang.String r2 = ""
            if (r8 == 0) goto L_0x0046
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x00a7 }
            android.os.Bundle r3 = r8.getParams()     // Catch:{ all -> 0x00a7 }
            if (r3 == 0) goto L_0x0046
            android.os.Bundle r4 = new android.os.Bundle     // Catch:{ all -> 0x00a7 }
            r4.<init>()     // Catch:{ all -> 0x00a7 }
            java.lang.String r5 = "encryptData"
            java.lang.String r3 = a((android.os.Bundle) r3)     // Catch:{ all -> 0x00a7 }
            r4.putString(r5, r3)     // Catch:{ all -> 0x00a7 }
            r0.setParams(r4)     // Catch:{ all -> 0x00a7 }
        L_0x0046:
            java.lang.String r2 = g(r2)     // Catch:{ all -> 0x00a7 }
            java.lang.String r2 = r7.h(r2)     // Catch:{ all -> 0x00a7 }
            java.lang.String r2 = c((java.lang.String) r2)     // Catch:{ all -> 0x00a7 }
            r0.setReserve(r2)     // Catch:{ all -> 0x00a7 }
            java.util.HashMap r2 = r7.v     // Catch:{ all -> 0x00a7 }
            int[] r3 = r7.w     // Catch:{ all -> 0x00a7 }
            r3 = r3[r1]     // Catch:{ all -> 0x00a7 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00a7 }
            r2.put(r3, r9)     // Catch:{ all -> 0x00a7 }
            com.unionpay.tsmservice.mi.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x0094 }
            com.unionpay.tsmservice.mi.UPTsmAddon$b r3 = new com.unionpay.tsmservice.mi.UPTsmAddon$b     // Catch:{ Exception -> 0x0094 }
            int[] r4 = r7.w     // Catch:{ Exception -> 0x0094 }
            r5 = r4[r1]     // Catch:{ Exception -> 0x0094 }
            int r6 = r5 + 1
            r4[r1] = r6     // Catch:{ Exception -> 0x0094 }
            r4 = 0
            r3.<init>(r7, r1, r5, r4)     // Catch:{ Exception -> 0x0094 }
            int r0 = r2.getMessageDetails(r0, r3)     // Catch:{ Exception -> 0x0094 }
            if (r0 == 0) goto L_0x0089
            java.util.HashMap r2 = r7.v     // Catch:{ all -> 0x00a7 }
            int[] r3 = r7.w     // Catch:{ all -> 0x00a7 }
            r4 = r3[r1]     // Catch:{ all -> 0x00a7 }
            int r4 = r4 + -1
            r3[r1] = r4     // Catch:{ all -> 0x00a7 }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x00a7 }
            r2.remove(r3)     // Catch:{ all -> 0x00a7 }
        L_0x0089:
            r2 = -2
            if (r2 != r0) goto L_0x0092
            int r8 = a((int) r1, (com.unionpay.tsmservice.mi.request.RequestParams) r8, (com.unionpay.tsmservice.mi.ITsmCallback) r9)     // Catch:{ all -> 0x00a7 }
            monitor-exit(r7)
            return r8
        L_0x0092:
            monitor-exit(r7)
            return r0
        L_0x0094:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x00a7 }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x00a7 }
            r8.<init>()     // Catch:{ all -> 0x00a7 }
            throw r8     // Catch:{ all -> 0x00a7 }
        L_0x009e:
            int r8 = a((int) r1, (com.unionpay.tsmservice.mi.request.RequestParams) r8, (com.unionpay.tsmservice.mi.ITsmCallback) r9)     // Catch:{ all -> 0x00a7 }
            monitor-exit(r7)
            return r8
        L_0x00a4:
            r8 = -1
            goto L_0x0004
        L_0x00a7:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.mi.UPTsmAddon.getMessageDetails(com.unionpay.tsmservice.mi.request.GetMessageDetailsRequestParams, com.unionpay.tsmservice.mi.ITsmCallback):int");
    }

    public int getPubKey(int i2, String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return -3;
        }
        if (this.e == null) {
            return -1;
        }
        try {
            return this.e.getPubKey(i2, strArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            throw new RemoteException();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0005, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int getSeId(com.unionpay.tsmservice.mi.request.GetSeIdRequestParams r8, com.unionpay.tsmservice.mi.ITsmCallback r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r9 != 0) goto L_0x0006
            r8 = -3
        L_0x0004:
            monitor-exit(r7)
            return r8
        L_0x0006:
            java.lang.String r0 = "01.00.10"
            boolean r0 = r7.e(r0)     // Catch:{ all -> 0x0090 }
            if (r0 != 0) goto L_0x0010
            r8 = -8
            goto L_0x0004
        L_0x0010:
            com.unionpay.tsmservice.mi.ITsmService r0 = r7.e     // Catch:{ all -> 0x0090 }
            if (r0 == 0) goto L_0x008d
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x0090 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x0090 }
            boolean r0 = b((java.lang.String) r0)     // Catch:{ all -> 0x0090 }
            r1 = 15
            if (r0 == 0) goto L_0x0087
            com.unionpay.tsmservice.mi.request.GetSeIdRequestParams r0 = new com.unionpay.tsmservice.mi.request.GetSeIdRequestParams     // Catch:{ all -> 0x0090 }
            r0.<init>()     // Catch:{ all -> 0x0090 }
            java.lang.String r2 = ""
            if (r8 == 0) goto L_0x002f
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x0090 }
        L_0x002f:
            java.lang.String r2 = g(r2)     // Catch:{ all -> 0x0090 }
            java.lang.String r2 = r7.h(r2)     // Catch:{ all -> 0x0090 }
            java.lang.String r2 = c((java.lang.String) r2)     // Catch:{ all -> 0x0090 }
            r0.setReserve(r2)     // Catch:{ all -> 0x0090 }
            java.util.HashMap r2 = r7.s     // Catch:{ all -> 0x0090 }
            int[] r3 = r7.w     // Catch:{ all -> 0x0090 }
            r3 = r3[r1]     // Catch:{ all -> 0x0090 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x0090 }
            r2.put(r3, r9)     // Catch:{ all -> 0x0090 }
            com.unionpay.tsmservice.mi.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x007d }
            com.unionpay.tsmservice.mi.UPTsmAddon$b r3 = new com.unionpay.tsmservice.mi.UPTsmAddon$b     // Catch:{ Exception -> 0x007d }
            int[] r4 = r7.w     // Catch:{ Exception -> 0x007d }
            r5 = r4[r1]     // Catch:{ Exception -> 0x007d }
            int r6 = r5 + 1
            r4[r1] = r6     // Catch:{ Exception -> 0x007d }
            r4 = 0
            r3.<init>(r7, r1, r5, r4)     // Catch:{ Exception -> 0x007d }
            int r0 = r2.getSEId(r0, r3)     // Catch:{ Exception -> 0x007d }
            if (r0 == 0) goto L_0x0072
            java.util.HashMap r2 = r7.s     // Catch:{ all -> 0x0090 }
            int[] r3 = r7.w     // Catch:{ all -> 0x0090 }
            r4 = r3[r1]     // Catch:{ all -> 0x0090 }
            int r4 = r4 + -1
            r3[r1] = r4     // Catch:{ all -> 0x0090 }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x0090 }
            r2.remove(r3)     // Catch:{ all -> 0x0090 }
        L_0x0072:
            r2 = -2
            if (r2 != r0) goto L_0x007b
            int r8 = a((int) r1, (com.unionpay.tsmservice.mi.request.RequestParams) r8, (com.unionpay.tsmservice.mi.ITsmCallback) r9)     // Catch:{ all -> 0x0090 }
            monitor-exit(r7)
            return r8
        L_0x007b:
            monitor-exit(r7)
            return r0
        L_0x007d:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x0090 }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x0090 }
            r8.<init>()     // Catch:{ all -> 0x0090 }
            throw r8     // Catch:{ all -> 0x0090 }
        L_0x0087:
            int r8 = a((int) r1, (com.unionpay.tsmservice.mi.request.RequestParams) r8, (com.unionpay.tsmservice.mi.ITsmCallback) r9)     // Catch:{ all -> 0x0090 }
            monitor-exit(r7)
            return r8
        L_0x008d:
            r8 = -1
            goto L_0x0004
        L_0x0090:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.mi.UPTsmAddon.getSeId(com.unionpay.tsmservice.mi.request.GetSeIdRequestParams, com.unionpay.tsmservice.mi.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0005, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int getTransactionDetails(com.unionpay.tsmservice.mi.request.GetTransactionDetailsRequestParams r8, com.unionpay.tsmservice.mi.ITsmCallback r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r9 != 0) goto L_0x0006
            r8 = -3
        L_0x0004:
            monitor-exit(r7)
            return r8
        L_0x0006:
            java.lang.String r0 = "01.00.17"
            boolean r0 = r7.e(r0)     // Catch:{ all -> 0x00a7 }
            if (r0 != 0) goto L_0x0010
            r8 = -8
            goto L_0x0004
        L_0x0010:
            com.unionpay.tsmservice.mi.ITsmService r0 = r7.e     // Catch:{ all -> 0x00a7 }
            if (r0 == 0) goto L_0x00a4
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x00a7 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00a7 }
            boolean r0 = b((java.lang.String) r0)     // Catch:{ all -> 0x00a7 }
            r1 = 17
            if (r0 == 0) goto L_0x009e
            com.unionpay.tsmservice.mi.request.GetTransactionDetailsRequestParams r0 = new com.unionpay.tsmservice.mi.request.GetTransactionDetailsRequestParams     // Catch:{ all -> 0x00a7 }
            r0.<init>()     // Catch:{ all -> 0x00a7 }
            java.lang.String r2 = ""
            if (r8 == 0) goto L_0x0046
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x00a7 }
            android.os.Bundle r3 = r8.getParams()     // Catch:{ all -> 0x00a7 }
            if (r3 == 0) goto L_0x0046
            android.os.Bundle r4 = new android.os.Bundle     // Catch:{ all -> 0x00a7 }
            r4.<init>()     // Catch:{ all -> 0x00a7 }
            java.lang.String r5 = "encryptData"
            java.lang.String r3 = a((android.os.Bundle) r3)     // Catch:{ all -> 0x00a7 }
            r4.putString(r5, r3)     // Catch:{ all -> 0x00a7 }
            r0.setParams(r4)     // Catch:{ all -> 0x00a7 }
        L_0x0046:
            java.lang.String r2 = g(r2)     // Catch:{ all -> 0x00a7 }
            java.lang.String r2 = r7.h(r2)     // Catch:{ all -> 0x00a7 }
            java.lang.String r2 = c((java.lang.String) r2)     // Catch:{ all -> 0x00a7 }
            r0.setReserve(r2)     // Catch:{ all -> 0x00a7 }
            java.util.HashMap r2 = r7.u     // Catch:{ all -> 0x00a7 }
            int[] r3 = r7.w     // Catch:{ all -> 0x00a7 }
            r3 = r3[r1]     // Catch:{ all -> 0x00a7 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00a7 }
            r2.put(r3, r9)     // Catch:{ all -> 0x00a7 }
            com.unionpay.tsmservice.mi.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x0094 }
            com.unionpay.tsmservice.mi.UPTsmAddon$b r3 = new com.unionpay.tsmservice.mi.UPTsmAddon$b     // Catch:{ Exception -> 0x0094 }
            int[] r4 = r7.w     // Catch:{ Exception -> 0x0094 }
            r5 = r4[r1]     // Catch:{ Exception -> 0x0094 }
            int r6 = r5 + 1
            r4[r1] = r6     // Catch:{ Exception -> 0x0094 }
            r4 = 0
            r3.<init>(r7, r1, r5, r4)     // Catch:{ Exception -> 0x0094 }
            int r0 = r2.getTransactionDetails(r0, r3)     // Catch:{ Exception -> 0x0094 }
            if (r0 == 0) goto L_0x0089
            java.util.HashMap r2 = r7.u     // Catch:{ all -> 0x00a7 }
            int[] r3 = r7.w     // Catch:{ all -> 0x00a7 }
            r4 = r3[r1]     // Catch:{ all -> 0x00a7 }
            int r4 = r4 + -1
            r3[r1] = r4     // Catch:{ all -> 0x00a7 }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x00a7 }
            r2.remove(r3)     // Catch:{ all -> 0x00a7 }
        L_0x0089:
            r2 = -2
            if (r2 != r0) goto L_0x0092
            int r8 = a((int) r1, (com.unionpay.tsmservice.mi.request.RequestParams) r8, (com.unionpay.tsmservice.mi.ITsmCallback) r9)     // Catch:{ all -> 0x00a7 }
            monitor-exit(r7)
            return r8
        L_0x0092:
            monitor-exit(r7)
            return r0
        L_0x0094:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x00a7 }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x00a7 }
            r8.<init>()     // Catch:{ all -> 0x00a7 }
            throw r8     // Catch:{ all -> 0x00a7 }
        L_0x009e:
            int r8 = a((int) r1, (com.unionpay.tsmservice.mi.request.RequestParams) r8, (com.unionpay.tsmservice.mi.ITsmCallback) r9)     // Catch:{ all -> 0x00a7 }
            monitor-exit(r7)
            return r8
        L_0x00a4:
            r8 = -1
            goto L_0x0004
        L_0x00a7:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.mi.UPTsmAddon.getTransactionDetails(com.unionpay.tsmservice.mi.request.GetTransactionDetailsRequestParams, com.unionpay.tsmservice.mi.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0005, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int getVendorPayStatus(com.unionpay.tsmservice.mi.request.GetVendorPayStatusRequestParams r8, com.unionpay.tsmservice.mi.ITsmCallback r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r9 != 0) goto L_0x0006
            r8 = -3
        L_0x0004:
            monitor-exit(r7)
            return r8
        L_0x0006:
            java.lang.String r0 = "01.00.08"
            boolean r0 = r7.e(r0)     // Catch:{ all -> 0x0090 }
            if (r0 != 0) goto L_0x0010
            r8 = -8
            goto L_0x0004
        L_0x0010:
            com.unionpay.tsmservice.mi.ITsmService r0 = r7.e     // Catch:{ all -> 0x0090 }
            if (r0 == 0) goto L_0x008d
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x0090 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x0090 }
            boolean r0 = b((java.lang.String) r0)     // Catch:{ all -> 0x0090 }
            r1 = 9
            if (r0 == 0) goto L_0x0087
            com.unionpay.tsmservice.mi.request.GetVendorPayStatusRequestParams r0 = new com.unionpay.tsmservice.mi.request.GetVendorPayStatusRequestParams     // Catch:{ all -> 0x0090 }
            r0.<init>()     // Catch:{ all -> 0x0090 }
            java.lang.String r2 = ""
            if (r8 == 0) goto L_0x002f
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x0090 }
        L_0x002f:
            java.lang.String r2 = g(r2)     // Catch:{ all -> 0x0090 }
            java.lang.String r2 = r7.h(r2)     // Catch:{ all -> 0x0090 }
            java.lang.String r2 = c((java.lang.String) r2)     // Catch:{ all -> 0x0090 }
            r0.setReserve(r2)     // Catch:{ all -> 0x0090 }
            java.util.HashMap r2 = r7.n     // Catch:{ all -> 0x0090 }
            int[] r3 = r7.w     // Catch:{ all -> 0x0090 }
            r3 = r3[r1]     // Catch:{ all -> 0x0090 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x0090 }
            r2.put(r3, r9)     // Catch:{ all -> 0x0090 }
            com.unionpay.tsmservice.mi.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x007d }
            com.unionpay.tsmservice.mi.UPTsmAddon$b r3 = new com.unionpay.tsmservice.mi.UPTsmAddon$b     // Catch:{ Exception -> 0x007d }
            int[] r4 = r7.w     // Catch:{ Exception -> 0x007d }
            r5 = r4[r1]     // Catch:{ Exception -> 0x007d }
            int r6 = r5 + 1
            r4[r1] = r6     // Catch:{ Exception -> 0x007d }
            r4 = 0
            r3.<init>(r7, r1, r5, r4)     // Catch:{ Exception -> 0x007d }
            int r0 = r2.getVendorPayStatus(r0, r3)     // Catch:{ Exception -> 0x007d }
            if (r0 == 0) goto L_0x0072
            java.util.HashMap r2 = r7.n     // Catch:{ all -> 0x0090 }
            int[] r3 = r7.w     // Catch:{ all -> 0x0090 }
            r4 = r3[r1]     // Catch:{ all -> 0x0090 }
            int r4 = r4 + -1
            r3[r1] = r4     // Catch:{ all -> 0x0090 }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x0090 }
            r2.remove(r3)     // Catch:{ all -> 0x0090 }
        L_0x0072:
            r2 = -2
            if (r2 != r0) goto L_0x007b
            int r8 = a((int) r1, (com.unionpay.tsmservice.mi.request.RequestParams) r8, (com.unionpay.tsmservice.mi.ITsmCallback) r9)     // Catch:{ all -> 0x0090 }
            monitor-exit(r7)
            return r8
        L_0x007b:
            monitor-exit(r7)
            return r0
        L_0x007d:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x0090 }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x0090 }
            r8.<init>()     // Catch:{ all -> 0x0090 }
            throw r8     // Catch:{ all -> 0x0090 }
        L_0x0087:
            int r8 = a((int) r1, (com.unionpay.tsmservice.mi.request.RequestParams) r8, (com.unionpay.tsmservice.mi.ITsmCallback) r9)     // Catch:{ all -> 0x0090 }
            monitor-exit(r7)
            return r8
        L_0x008d:
            r8 = -1
            goto L_0x0004
        L_0x0090:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.mi.UPTsmAddon.getVendorPayStatus(com.unionpay.tsmservice.mi.request.GetVendorPayStatusRequestParams, com.unionpay.tsmservice.mi.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0005, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int getVendorPayStatusForBankApp(com.unionpay.tsmservice.mi.request.GetVendorPayStatusRequestParams r8, com.unionpay.tsmservice.mi.ITsmCallback r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r9 != 0) goto L_0x0006
            r8 = -3
        L_0x0004:
            monitor-exit(r7)
            return r8
        L_0x0006:
            java.lang.String r0 = "01.00.09"
            boolean r0 = r7.e(r0)     // Catch:{ all -> 0x0090 }
            if (r0 != 0) goto L_0x0010
            r8 = -8
            goto L_0x0004
        L_0x0010:
            com.unionpay.tsmservice.mi.ITsmService r0 = r7.e     // Catch:{ all -> 0x0090 }
            if (r0 == 0) goto L_0x008d
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x0090 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x0090 }
            boolean r0 = b((java.lang.String) r0)     // Catch:{ all -> 0x0090 }
            r1 = 14
            if (r0 == 0) goto L_0x0087
            com.unionpay.tsmservice.mi.request.GetVendorPayStatusRequestParams r0 = new com.unionpay.tsmservice.mi.request.GetVendorPayStatusRequestParams     // Catch:{ all -> 0x0090 }
            r0.<init>()     // Catch:{ all -> 0x0090 }
            java.lang.String r2 = ""
            if (r8 == 0) goto L_0x002f
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x0090 }
        L_0x002f:
            java.lang.String r2 = g(r2)     // Catch:{ all -> 0x0090 }
            java.lang.String r2 = r7.h(r2)     // Catch:{ all -> 0x0090 }
            java.lang.String r2 = c((java.lang.String) r2)     // Catch:{ all -> 0x0090 }
            r0.setReserve(r2)     // Catch:{ all -> 0x0090 }
            java.util.HashMap r2 = r7.r     // Catch:{ all -> 0x0090 }
            int[] r3 = r7.w     // Catch:{ all -> 0x0090 }
            r3 = r3[r1]     // Catch:{ all -> 0x0090 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x0090 }
            r2.put(r3, r9)     // Catch:{ all -> 0x0090 }
            com.unionpay.tsmservice.mi.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x007d }
            com.unionpay.tsmservice.mi.UPTsmAddon$b r3 = new com.unionpay.tsmservice.mi.UPTsmAddon$b     // Catch:{ Exception -> 0x007d }
            int[] r4 = r7.w     // Catch:{ Exception -> 0x007d }
            r5 = r4[r1]     // Catch:{ Exception -> 0x007d }
            int r6 = r5 + 1
            r4[r1] = r6     // Catch:{ Exception -> 0x007d }
            r4 = 0
            r3.<init>(r7, r1, r5, r4)     // Catch:{ Exception -> 0x007d }
            int r0 = r2.getVendorPayStatusForBankApp(r0, r3)     // Catch:{ Exception -> 0x007d }
            if (r0 == 0) goto L_0x0072
            java.util.HashMap r2 = r7.r     // Catch:{ all -> 0x0090 }
            int[] r3 = r7.w     // Catch:{ all -> 0x0090 }
            r4 = r3[r1]     // Catch:{ all -> 0x0090 }
            int r4 = r4 + -1
            r3[r1] = r4     // Catch:{ all -> 0x0090 }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x0090 }
            r2.remove(r3)     // Catch:{ all -> 0x0090 }
        L_0x0072:
            r2 = -2
            if (r2 != r0) goto L_0x007b
            int r8 = a((int) r1, (com.unionpay.tsmservice.mi.request.RequestParams) r8, (com.unionpay.tsmservice.mi.ITsmCallback) r9)     // Catch:{ all -> 0x0090 }
            monitor-exit(r7)
            return r8
        L_0x007b:
            monitor-exit(r7)
            return r0
        L_0x007d:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x0090 }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x0090 }
            r8.<init>()     // Catch:{ all -> 0x0090 }
            throw r8     // Catch:{ all -> 0x0090 }
        L_0x0087:
            int r8 = a((int) r1, (com.unionpay.tsmservice.mi.request.RequestParams) r8, (com.unionpay.tsmservice.mi.ITsmCallback) r9)     // Catch:{ all -> 0x0090 }
            monitor-exit(r7)
            return r8
        L_0x008d:
            r8 = -1
            goto L_0x0004
        L_0x0090:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.mi.UPTsmAddon.getVendorPayStatusForBankApp(com.unionpay.tsmservice.mi.request.GetVendorPayStatusRequestParams, com.unionpay.tsmservice.mi.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0037, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0040, code lost:
        throw new android.os.RemoteException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0052, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005b, code lost:
        throw new android.os.RemoteException();
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:9:0x0030, B:15:0x0041] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int hideKeyboard() {
        /*
            r5 = this;
            monitor-enter(r5)
            com.unionpay.tsmservice.mi.ITsmService r0 = r5.e     // Catch:{ all -> 0x0065 }
            if (r0 == 0) goto L_0x0062
            android.content.Context r0 = r5.c     // Catch:{ all -> 0x0065 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x0065 }
            boolean r0 = b((java.lang.String) r0)     // Catch:{ all -> 0x0065 }
            r1 = 0
            r2 = 5
            r3 = 0
            if (r0 == 0) goto L_0x005c
            boolean r0 = r5.c()     // Catch:{ all -> 0x0065 }
            if (r0 == 0) goto L_0x0041
            com.unionpay.tsmservice.mi.request.HideSafetyKeyboardRequestParams r0 = new com.unionpay.tsmservice.mi.request.HideSafetyKeyboardRequestParams     // Catch:{ all -> 0x0065 }
            r0.<init>()     // Catch:{ all -> 0x0065 }
            java.lang.String r4 = ""
            java.lang.String r4 = g(r4)     // Catch:{ all -> 0x0065 }
            java.lang.String r4 = r5.h(r4)     // Catch:{ all -> 0x0065 }
            java.lang.String r4 = c((java.lang.String) r4)     // Catch:{ all -> 0x0065 }
            r0.setReserve(r4)     // Catch:{ all -> 0x0065 }
            com.unionpay.tsmservice.mi.ITsmService r4 = r5.e     // Catch:{ Exception -> 0x0037 }
            int r0 = r4.hideSafetyKeyboard(r0)     // Catch:{ Exception -> 0x0037 }
            goto L_0x0047
        L_0x0037:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0065 }
            android.os.RemoteException r0 = new android.os.RemoteException     // Catch:{ all -> 0x0065 }
            r0.<init>()     // Catch:{ all -> 0x0065 }
            throw r0     // Catch:{ all -> 0x0065 }
        L_0x0041:
            com.unionpay.tsmservice.mi.ITsmService r0 = r5.e     // Catch:{ Exception -> 0x0052 }
            int r0 = r0.hideKeyboard()     // Catch:{ Exception -> 0x0052 }
        L_0x0047:
            r4 = -2
            if (r4 != r0) goto L_0x0050
            int r0 = a((int) r2, (com.unionpay.tsmservice.mi.request.SafetyKeyboardRequestParams) r3, (int) r1, (com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback) r3, (android.content.Context) r3)     // Catch:{ all -> 0x0065 }
            monitor-exit(r5)
            return r0
        L_0x0050:
            monitor-exit(r5)
            return r0
        L_0x0052:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0065 }
            android.os.RemoteException r0 = new android.os.RemoteException     // Catch:{ all -> 0x0065 }
            r0.<init>()     // Catch:{ all -> 0x0065 }
            throw r0     // Catch:{ all -> 0x0065 }
        L_0x005c:
            int r0 = a((int) r2, (com.unionpay.tsmservice.mi.request.SafetyKeyboardRequestParams) r3, (int) r1, (com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback) r3, (android.content.Context) r3)     // Catch:{ all -> 0x0065 }
            monitor-exit(r5)
            return r0
        L_0x0062:
            r0 = -1
            monitor-exit(r5)
            return r0
        L_0x0065:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.mi.UPTsmAddon.hideKeyboard():int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0005, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int init(com.unionpay.tsmservice.mi.request.InitRequestParams r8, com.unionpay.tsmservice.mi.ITsmCallback r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r9 != 0) goto L_0x0006
            r8 = -3
        L_0x0004:
            monitor-exit(r7)
            return r8
        L_0x0006:
            com.unionpay.tsmservice.mi.ITsmService r0 = r7.e     // Catch:{ all -> 0x00a1 }
            if (r0 == 0) goto L_0x009e
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x00a1 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00a1 }
            boolean r0 = b((java.lang.String) r0)     // Catch:{ all -> 0x00a1 }
            r1 = 0
            if (r0 == 0) goto L_0x0098
            com.unionpay.tsmservice.mi.request.InitRequestParams r0 = new com.unionpay.tsmservice.mi.request.InitRequestParams     // Catch:{ all -> 0x00a1 }
            r0.<init>()     // Catch:{ all -> 0x00a1 }
            java.lang.String r2 = ""
            if (r8 == 0) goto L_0x0035
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x00a1 }
            java.lang.String r3 = r8.getSignature()     // Catch:{ all -> 0x00a1 }
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x00a1 }
            if (r4 != 0) goto L_0x0035
            java.lang.String r3 = c((java.lang.String) r3)     // Catch:{ all -> 0x00a1 }
            r0.setSignature(r3)     // Catch:{ all -> 0x00a1 }
        L_0x0035:
            boolean r3 = r7.c()     // Catch:{ all -> 0x00a1 }
            if (r3 == 0) goto L_0x0043
            java.lang.String r2 = g(r2)     // Catch:{ all -> 0x00a1 }
            java.lang.String r2 = r7.h(r2)     // Catch:{ all -> 0x00a1 }
        L_0x0043:
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00a1 }
            if (r3 != 0) goto L_0x0050
            java.lang.String r2 = c((java.lang.String) r2)     // Catch:{ all -> 0x00a1 }
            r0.setReserve(r2)     // Catch:{ all -> 0x00a1 }
        L_0x0050:
            java.util.HashMap r2 = r7.g     // Catch:{ all -> 0x00a1 }
            int[] r3 = r7.w     // Catch:{ all -> 0x00a1 }
            r3 = r3[r1]     // Catch:{ all -> 0x00a1 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00a1 }
            r2.put(r3, r9)     // Catch:{ all -> 0x00a1 }
            com.unionpay.tsmservice.mi.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x008e }
            com.unionpay.tsmservice.mi.UPTsmAddon$b r3 = new com.unionpay.tsmservice.mi.UPTsmAddon$b     // Catch:{ Exception -> 0x008e }
            int[] r4 = r7.w     // Catch:{ Exception -> 0x008e }
            r5 = r4[r1]     // Catch:{ Exception -> 0x008e }
            int r6 = r5 + 1
            r4[r1] = r6     // Catch:{ Exception -> 0x008e }
            r3.<init>(r7, r1, r5, r1)     // Catch:{ Exception -> 0x008e }
            int r0 = r2.init(r0, r3)     // Catch:{ Exception -> 0x008e }
            if (r0 == 0) goto L_0x0083
            java.util.HashMap r2 = r7.g     // Catch:{ all -> 0x00a1 }
            int[] r3 = r7.w     // Catch:{ all -> 0x00a1 }
            r4 = r3[r1]     // Catch:{ all -> 0x00a1 }
            int r4 = r4 + -1
            r3[r1] = r4     // Catch:{ all -> 0x00a1 }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x00a1 }
            r2.remove(r3)     // Catch:{ all -> 0x00a1 }
        L_0x0083:
            r2 = -2
            if (r2 != r0) goto L_0x008c
            int r8 = a((int) r1, (com.unionpay.tsmservice.mi.request.RequestParams) r8, (com.unionpay.tsmservice.mi.ITsmCallback) r9)     // Catch:{ all -> 0x00a1 }
            monitor-exit(r7)
            return r8
        L_0x008c:
            monitor-exit(r7)
            return r0
        L_0x008e:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x00a1 }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x00a1 }
            r8.<init>()     // Catch:{ all -> 0x00a1 }
            throw r8     // Catch:{ all -> 0x00a1 }
        L_0x0098:
            int r8 = a((int) r1, (com.unionpay.tsmservice.mi.request.RequestParams) r8, (com.unionpay.tsmservice.mi.ITsmCallback) r9)     // Catch:{ all -> 0x00a1 }
            monitor-exit(r7)
            return r8
        L_0x009e:
            r8 = -1
            goto L_0x0004
        L_0x00a1:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.mi.UPTsmAddon.init(com.unionpay.tsmservice.mi.request.InitRequestParams, com.unionpay.tsmservice.mi.ITsmCallback):int");
    }

    public boolean isConnected() {
        return this.f;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0011, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int onlinePaymentVerify(com.unionpay.tsmservice.mi.request.OnlinePaymentVerifyRequestParams r8, com.unionpay.tsmservice.mi.ITsmCallback r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r8 == 0) goto L_0x00a8
            if (r9 != 0) goto L_0x0007
            goto L_0x00a8
        L_0x0007:
            java.lang.String r0 = "01.00.08"
            boolean r0 = r7.e(r0)     // Catch:{ all -> 0x00a5 }
            if (r0 != 0) goto L_0x0012
            r8 = -8
        L_0x0010:
            monitor-exit(r7)
            return r8
        L_0x0012:
            com.unionpay.tsmservice.mi.ITsmService r0 = r7.e     // Catch:{ all -> 0x00a5 }
            if (r0 == 0) goto L_0x00a2
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x00a5 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00a5 }
            boolean r0 = b((java.lang.String) r0)     // Catch:{ all -> 0x00a5 }
            r1 = 10
            if (r0 == 0) goto L_0x009c
            com.unionpay.tsmservice.mi.request.OnlinePaymentVerifyRequestParams r0 = new com.unionpay.tsmservice.mi.request.OnlinePaymentVerifyRequestParams     // Catch:{ all -> 0x00a5 }
            r0.<init>()     // Catch:{ all -> 0x00a5 }
            android.os.Bundle r2 = r8.getParams()     // Catch:{ all -> 0x00a5 }
            if (r2 == 0) goto L_0x0040
            android.os.Bundle r3 = new android.os.Bundle     // Catch:{ all -> 0x00a5 }
            r3.<init>()     // Catch:{ all -> 0x00a5 }
            java.lang.String r4 = "encryptData"
            java.lang.String r2 = a((android.os.Bundle) r2)     // Catch:{ all -> 0x00a5 }
            r3.putString(r4, r2)     // Catch:{ all -> 0x00a5 }
            r0.setParams(r3)     // Catch:{ all -> 0x00a5 }
        L_0x0040:
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x00a5 }
            java.lang.String r2 = g(r2)     // Catch:{ all -> 0x00a5 }
            java.lang.String r2 = r7.h(r2)     // Catch:{ all -> 0x00a5 }
            java.lang.String r2 = c((java.lang.String) r2)     // Catch:{ all -> 0x00a5 }
            r0.setReserve(r2)     // Catch:{ all -> 0x00a5 }
            java.util.HashMap r2 = r7.o     // Catch:{ all -> 0x00a5 }
            int[] r3 = r7.w     // Catch:{ all -> 0x00a5 }
            r3 = r3[r1]     // Catch:{ all -> 0x00a5 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00a5 }
            r2.put(r3, r9)     // Catch:{ all -> 0x00a5 }
            com.unionpay.tsmservice.mi.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x0092 }
            com.unionpay.tsmservice.mi.UPTsmAddon$b r3 = new com.unionpay.tsmservice.mi.UPTsmAddon$b     // Catch:{ Exception -> 0x0092 }
            int[] r4 = r7.w     // Catch:{ Exception -> 0x0092 }
            r5 = r4[r1]     // Catch:{ Exception -> 0x0092 }
            int r6 = r5 + 1
            r4[r1] = r6     // Catch:{ Exception -> 0x0092 }
            r4 = 0
            r3.<init>(r7, r1, r5, r4)     // Catch:{ Exception -> 0x0092 }
            int r0 = r2.onlinePaymentVerify(r0, r3)     // Catch:{ Exception -> 0x0092 }
            if (r0 == 0) goto L_0x0087
            java.util.HashMap r2 = r7.o     // Catch:{ all -> 0x00a5 }
            int[] r3 = r7.w     // Catch:{ all -> 0x00a5 }
            r4 = r3[r1]     // Catch:{ all -> 0x00a5 }
            int r4 = r4 + -1
            r3[r1] = r4     // Catch:{ all -> 0x00a5 }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x00a5 }
            r2.remove(r3)     // Catch:{ all -> 0x00a5 }
        L_0x0087:
            r2 = -2
            if (r2 != r0) goto L_0x0090
            int r8 = a((int) r1, (com.unionpay.tsmservice.mi.request.RequestParams) r8, (com.unionpay.tsmservice.mi.ITsmCallback) r9)     // Catch:{ all -> 0x00a5 }
            monitor-exit(r7)
            return r8
        L_0x0090:
            monitor-exit(r7)
            return r0
        L_0x0092:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x00a5 }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x00a5 }
            r8.<init>()     // Catch:{ all -> 0x00a5 }
            throw r8     // Catch:{ all -> 0x00a5 }
        L_0x009c:
            int r8 = a((int) r1, (com.unionpay.tsmservice.mi.request.RequestParams) r8, (com.unionpay.tsmservice.mi.ITsmCallback) r9)     // Catch:{ all -> 0x00a5 }
            monitor-exit(r7)
            return r8
        L_0x00a2:
            r8 = -1
            goto L_0x0010
        L_0x00a5:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        L_0x00a8:
            r8 = -3
            goto L_0x0010
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.mi.UPTsmAddon.onlinePaymentVerify(com.unionpay.tsmservice.mi.request.OnlinePaymentVerifyRequestParams, com.unionpay.tsmservice.mi.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0011, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int payResultNotify(com.unionpay.tsmservice.mi.request.PayResultNotifyRequestParams r8, com.unionpay.tsmservice.mi.ITsmCallback r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r8 == 0) goto L_0x00a8
            if (r9 != 0) goto L_0x0007
            goto L_0x00a8
        L_0x0007:
            java.lang.String r0 = "01.00.08"
            boolean r0 = r7.e(r0)     // Catch:{ all -> 0x00a5 }
            if (r0 != 0) goto L_0x0012
            r8 = -8
        L_0x0010:
            monitor-exit(r7)
            return r8
        L_0x0012:
            com.unionpay.tsmservice.mi.ITsmService r0 = r7.e     // Catch:{ all -> 0x00a5 }
            if (r0 == 0) goto L_0x00a2
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x00a5 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00a5 }
            boolean r0 = b((java.lang.String) r0)     // Catch:{ all -> 0x00a5 }
            r1 = 12
            if (r0 == 0) goto L_0x009c
            com.unionpay.tsmservice.mi.request.PayResultNotifyRequestParams r0 = new com.unionpay.tsmservice.mi.request.PayResultNotifyRequestParams     // Catch:{ all -> 0x00a5 }
            r0.<init>()     // Catch:{ all -> 0x00a5 }
            android.os.Bundle r2 = r8.getParams()     // Catch:{ all -> 0x00a5 }
            if (r2 == 0) goto L_0x0040
            android.os.Bundle r3 = new android.os.Bundle     // Catch:{ all -> 0x00a5 }
            r3.<init>()     // Catch:{ all -> 0x00a5 }
            java.lang.String r4 = "encryptData"
            java.lang.String r2 = a((android.os.Bundle) r2)     // Catch:{ all -> 0x00a5 }
            r3.putString(r4, r2)     // Catch:{ all -> 0x00a5 }
            r0.setParams(r3)     // Catch:{ all -> 0x00a5 }
        L_0x0040:
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x00a5 }
            java.lang.String r2 = g(r2)     // Catch:{ all -> 0x00a5 }
            java.lang.String r2 = r7.h(r2)     // Catch:{ all -> 0x00a5 }
            java.lang.String r2 = c((java.lang.String) r2)     // Catch:{ all -> 0x00a5 }
            r0.setReserve(r2)     // Catch:{ all -> 0x00a5 }
            java.util.HashMap r2 = r7.q     // Catch:{ all -> 0x00a5 }
            int[] r3 = r7.w     // Catch:{ all -> 0x00a5 }
            r3 = r3[r1]     // Catch:{ all -> 0x00a5 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00a5 }
            r2.put(r3, r9)     // Catch:{ all -> 0x00a5 }
            com.unionpay.tsmservice.mi.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x0092 }
            com.unionpay.tsmservice.mi.UPTsmAddon$b r3 = new com.unionpay.tsmservice.mi.UPTsmAddon$b     // Catch:{ Exception -> 0x0092 }
            int[] r4 = r7.w     // Catch:{ Exception -> 0x0092 }
            r5 = r4[r1]     // Catch:{ Exception -> 0x0092 }
            int r6 = r5 + 1
            r4[r1] = r6     // Catch:{ Exception -> 0x0092 }
            r4 = 0
            r3.<init>(r7, r1, r5, r4)     // Catch:{ Exception -> 0x0092 }
            int r0 = r2.payResultNotify(r0, r3)     // Catch:{ Exception -> 0x0092 }
            if (r0 == 0) goto L_0x0087
            java.util.HashMap r2 = r7.q     // Catch:{ all -> 0x00a5 }
            int[] r3 = r7.w     // Catch:{ all -> 0x00a5 }
            r4 = r3[r1]     // Catch:{ all -> 0x00a5 }
            int r4 = r4 + -1
            r3[r1] = r4     // Catch:{ all -> 0x00a5 }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x00a5 }
            r2.remove(r3)     // Catch:{ all -> 0x00a5 }
        L_0x0087:
            r2 = -2
            if (r2 != r0) goto L_0x0090
            int r8 = a((int) r1, (com.unionpay.tsmservice.mi.request.RequestParams) r8, (com.unionpay.tsmservice.mi.ITsmCallback) r9)     // Catch:{ all -> 0x00a5 }
            monitor-exit(r7)
            return r8
        L_0x0090:
            monitor-exit(r7)
            return r0
        L_0x0092:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x00a5 }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x00a5 }
            r8.<init>()     // Catch:{ all -> 0x00a5 }
            throw r8     // Catch:{ all -> 0x00a5 }
        L_0x009c:
            int r8 = a((int) r1, (com.unionpay.tsmservice.mi.request.RequestParams) r8, (com.unionpay.tsmservice.mi.ITsmCallback) r9)     // Catch:{ all -> 0x00a5 }
            monitor-exit(r7)
            return r8
        L_0x00a2:
            r8 = -1
            goto L_0x0010
        L_0x00a5:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        L_0x00a8:
            r8 = -3
            goto L_0x0010
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.mi.UPTsmAddon.payResultNotify(com.unionpay.tsmservice.mi.request.PayResultNotifyRequestParams, com.unionpay.tsmservice.mi.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0005, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int pinRequest(com.unionpay.tsmservice.mi.request.PinRequestRequestParams r8, com.unionpay.tsmservice.mi.ITsmCallback r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r9 != 0) goto L_0x0006
            r8 = -3
        L_0x0004:
            monitor-exit(r7)
            return r8
        L_0x0006:
            java.lang.String r0 = "01.00.08"
            boolean r0 = r7.e(r0)     // Catch:{ all -> 0x00a7 }
            if (r0 != 0) goto L_0x0010
            r8 = -8
            goto L_0x0004
        L_0x0010:
            com.unionpay.tsmservice.mi.ITsmService r0 = r7.e     // Catch:{ all -> 0x00a7 }
            if (r0 == 0) goto L_0x00a4
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x00a7 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00a7 }
            boolean r0 = b((java.lang.String) r0)     // Catch:{ all -> 0x00a7 }
            r1 = 11
            if (r0 == 0) goto L_0x009e
            com.unionpay.tsmservice.mi.request.PinRequestRequestParams r0 = new com.unionpay.tsmservice.mi.request.PinRequestRequestParams     // Catch:{ all -> 0x00a7 }
            r0.<init>()     // Catch:{ all -> 0x00a7 }
            java.lang.String r2 = ""
            if (r8 == 0) goto L_0x0046
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x00a7 }
            android.os.Bundle r3 = r8.getParams()     // Catch:{ all -> 0x00a7 }
            if (r3 == 0) goto L_0x0046
            android.os.Bundle r4 = new android.os.Bundle     // Catch:{ all -> 0x00a7 }
            r4.<init>()     // Catch:{ all -> 0x00a7 }
            java.lang.String r5 = "encryptData"
            java.lang.String r3 = a((android.os.Bundle) r3)     // Catch:{ all -> 0x00a7 }
            r4.putString(r5, r3)     // Catch:{ all -> 0x00a7 }
            r0.setParams(r4)     // Catch:{ all -> 0x00a7 }
        L_0x0046:
            java.lang.String r2 = g(r2)     // Catch:{ all -> 0x00a7 }
            java.lang.String r2 = r7.h(r2)     // Catch:{ all -> 0x00a7 }
            java.lang.String r2 = c((java.lang.String) r2)     // Catch:{ all -> 0x00a7 }
            r0.setReserve(r2)     // Catch:{ all -> 0x00a7 }
            java.util.HashMap r2 = r7.p     // Catch:{ all -> 0x00a7 }
            int[] r3 = r7.w     // Catch:{ all -> 0x00a7 }
            r3 = r3[r1]     // Catch:{ all -> 0x00a7 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00a7 }
            r2.put(r3, r9)     // Catch:{ all -> 0x00a7 }
            com.unionpay.tsmservice.mi.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x0094 }
            com.unionpay.tsmservice.mi.UPTsmAddon$b r3 = new com.unionpay.tsmservice.mi.UPTsmAddon$b     // Catch:{ Exception -> 0x0094 }
            int[] r4 = r7.w     // Catch:{ Exception -> 0x0094 }
            r5 = r4[r1]     // Catch:{ Exception -> 0x0094 }
            int r6 = r5 + 1
            r4[r1] = r6     // Catch:{ Exception -> 0x0094 }
            r4 = 0
            r3.<init>(r7, r1, r5, r4)     // Catch:{ Exception -> 0x0094 }
            int r0 = r2.pinRequest(r0, r3)     // Catch:{ Exception -> 0x0094 }
            if (r0 == 0) goto L_0x0089
            java.util.HashMap r2 = r7.p     // Catch:{ all -> 0x00a7 }
            int[] r3 = r7.w     // Catch:{ all -> 0x00a7 }
            r4 = r3[r1]     // Catch:{ all -> 0x00a7 }
            int r4 = r4 + -1
            r3[r1] = r4     // Catch:{ all -> 0x00a7 }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x00a7 }
            r2.remove(r3)     // Catch:{ all -> 0x00a7 }
        L_0x0089:
            r2 = -2
            if (r2 != r0) goto L_0x0092
            int r8 = a((int) r1, (com.unionpay.tsmservice.mi.request.RequestParams) r8, (com.unionpay.tsmservice.mi.ITsmCallback) r9)     // Catch:{ all -> 0x00a7 }
            monitor-exit(r7)
            return r8
        L_0x0092:
            monitor-exit(r7)
            return r0
        L_0x0094:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x00a7 }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x00a7 }
            r8.<init>()     // Catch:{ all -> 0x00a7 }
            throw r8     // Catch:{ all -> 0x00a7 }
        L_0x009e:
            int r8 = a((int) r1, (com.unionpay.tsmservice.mi.request.RequestParams) r8, (com.unionpay.tsmservice.mi.ITsmCallback) r9)     // Catch:{ all -> 0x00a7 }
            monitor-exit(r7)
            return r8
        L_0x00a4:
            r8 = -1
            goto L_0x0004
        L_0x00a7:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.mi.UPTsmAddon.pinRequest(com.unionpay.tsmservice.mi.request.PinRequestRequestParams, com.unionpay.tsmservice.mi.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0005, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int queryVendorPayStatus(com.unionpay.tsmservice.mi.request.QueryVendorPayStatusRequestParams r8, com.unionpay.tsmservice.mi.ITsmCallback r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r9 != 0) goto L_0x0006
            r8 = -3
        L_0x0004:
            monitor-exit(r7)
            return r8
        L_0x0006:
            java.lang.String r0 = "01.00.07"
            boolean r0 = r7.e(r0)     // Catch:{ all -> 0x0090 }
            if (r0 != 0) goto L_0x0010
            r8 = -8
            goto L_0x0004
        L_0x0010:
            com.unionpay.tsmservice.mi.ITsmService r0 = r7.e     // Catch:{ all -> 0x0090 }
            if (r0 == 0) goto L_0x008d
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x0090 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x0090 }
            boolean r0 = b((java.lang.String) r0)     // Catch:{ all -> 0x0090 }
            r1 = 8
            if (r0 == 0) goto L_0x0087
            com.unionpay.tsmservice.mi.request.QueryVendorPayStatusRequestParams r0 = new com.unionpay.tsmservice.mi.request.QueryVendorPayStatusRequestParams     // Catch:{ all -> 0x0090 }
            r0.<init>()     // Catch:{ all -> 0x0090 }
            java.lang.String r2 = ""
            if (r8 == 0) goto L_0x002f
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x0090 }
        L_0x002f:
            java.lang.String r2 = g(r2)     // Catch:{ all -> 0x0090 }
            java.lang.String r2 = r7.h(r2)     // Catch:{ all -> 0x0090 }
            java.lang.String r2 = c((java.lang.String) r2)     // Catch:{ all -> 0x0090 }
            r0.setReserve(r2)     // Catch:{ all -> 0x0090 }
            java.util.HashMap r2 = r7.m     // Catch:{ all -> 0x0090 }
            int[] r3 = r7.w     // Catch:{ all -> 0x0090 }
            r3 = r3[r1]     // Catch:{ all -> 0x0090 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x0090 }
            r2.put(r3, r9)     // Catch:{ all -> 0x0090 }
            com.unionpay.tsmservice.mi.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x007d }
            com.unionpay.tsmservice.mi.UPTsmAddon$b r3 = new com.unionpay.tsmservice.mi.UPTsmAddon$b     // Catch:{ Exception -> 0x007d }
            int[] r4 = r7.w     // Catch:{ Exception -> 0x007d }
            r5 = r4[r1]     // Catch:{ Exception -> 0x007d }
            int r6 = r5 + 1
            r4[r1] = r6     // Catch:{ Exception -> 0x007d }
            r4 = 0
            r3.<init>(r7, r1, r5, r4)     // Catch:{ Exception -> 0x007d }
            int r0 = r2.queryVendorPayStatus(r0, r3)     // Catch:{ Exception -> 0x007d }
            if (r0 == 0) goto L_0x0072
            java.util.HashMap r2 = r7.m     // Catch:{ all -> 0x0090 }
            int[] r3 = r7.w     // Catch:{ all -> 0x0090 }
            r4 = r3[r1]     // Catch:{ all -> 0x0090 }
            int r4 = r4 + -1
            r3[r1] = r4     // Catch:{ all -> 0x0090 }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x0090 }
            r2.remove(r3)     // Catch:{ all -> 0x0090 }
        L_0x0072:
            r2 = -2
            if (r2 != r0) goto L_0x007b
            int r8 = a((int) r1, (com.unionpay.tsmservice.mi.request.RequestParams) r8, (com.unionpay.tsmservice.mi.ITsmCallback) r9)     // Catch:{ all -> 0x0090 }
            monitor-exit(r7)
            return r8
        L_0x007b:
            monitor-exit(r7)
            return r0
        L_0x007d:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x0090 }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x0090 }
            r8.<init>()     // Catch:{ all -> 0x0090 }
            throw r8     // Catch:{ all -> 0x0090 }
        L_0x0087:
            int r8 = a((int) r1, (com.unionpay.tsmservice.mi.request.RequestParams) r8, (com.unionpay.tsmservice.mi.ITsmCallback) r9)     // Catch:{ all -> 0x0090 }
            monitor-exit(r7)
            return r8
        L_0x008d:
            r8 = -1
            goto L_0x0004
        L_0x0090:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.mi.UPTsmAddon.queryVendorPayStatus(com.unionpay.tsmservice.mi.request.QueryVendorPayStatusRequestParams, com.unionpay.tsmservice.mi.ITsmCallback):int");
    }

    public synchronized void removeConnectionListener(UPTsmConnectionListener uPTsmConnectionListener) {
        if (uPTsmConnectionListener != null) {
            b.remove(uPTsmConnectionListener);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0005, code lost:
        return r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int setSafetyKeyboardBitmap(com.unionpay.tsmservice.mi.request.SafetyKeyboardRequestParams r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            if (r5 != 0) goto L_0x0006
            r5 = -3
        L_0x0004:
            monitor-exit(r4)
            return r5
        L_0x0006:
            com.unionpay.tsmservice.mi.ITsmService r0 = r4.e     // Catch:{ all -> 0x005a }
            if (r0 == 0) goto L_0x0058
            android.content.Context r0 = r4.c     // Catch:{ all -> 0x005a }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x005a }
            boolean r0 = b((java.lang.String) r0)     // Catch:{ all -> 0x005a }
            r1 = 0
            r2 = 3
            if (r0 == 0) goto L_0x0052
            java.lang.String r0 = r5.getReserve()     // Catch:{ all -> 0x005a }
            boolean r3 = r4.c()     // Catch:{ all -> 0x005a }
            if (r3 == 0) goto L_0x002a
            java.lang.String r0 = g(r0)     // Catch:{ all -> 0x005a }
            java.lang.String r0 = r4.h(r0)     // Catch:{ all -> 0x005a }
        L_0x002a:
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x005a }
            if (r3 != 0) goto L_0x0037
            java.lang.String r0 = c((java.lang.String) r0)     // Catch:{ all -> 0x005a }
            r5.setReserve(r0)     // Catch:{ all -> 0x005a }
        L_0x0037:
            com.unionpay.tsmservice.mi.ITsmService r0 = r4.e     // Catch:{ Exception -> 0x0048 }
            int r0 = r0.setSafetyKeyboardBitmap(r5)     // Catch:{ Exception -> 0x0048 }
            r3 = -2
            if (r3 != r0) goto L_0x0046
            int r5 = a((int) r2, (com.unionpay.tsmservice.mi.request.RequestParams) r5, (com.unionpay.tsmservice.mi.ITsmCallback) r1)     // Catch:{ all -> 0x005a }
            monitor-exit(r4)
            return r5
        L_0x0046:
            monitor-exit(r4)
            return r0
        L_0x0048:
            r5 = move-exception
            r5.printStackTrace()     // Catch:{ all -> 0x005a }
            android.os.RemoteException r5 = new android.os.RemoteException     // Catch:{ all -> 0x005a }
            r5.<init>()     // Catch:{ all -> 0x005a }
            throw r5     // Catch:{ all -> 0x005a }
        L_0x0052:
            int r5 = a((int) r2, (com.unionpay.tsmservice.mi.request.RequestParams) r5, (com.unionpay.tsmservice.mi.ITsmCallback) r1)     // Catch:{ all -> 0x005a }
            monitor-exit(r4)
            return r5
        L_0x0058:
            r5 = -1
            goto L_0x0004
        L_0x005a:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.mi.UPTsmAddon.setSafetyKeyboardBitmap(com.unionpay.tsmservice.mi.request.SafetyKeyboardRequestParams):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0083, code lost:
        return r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int showSafetyKeyboard(com.unionpay.tsmservice.mi.request.SafetyKeyboardRequestParams r5, int r6, com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback r7, android.content.Context r8) {
        /*
            r4 = this;
            monitor-enter(r4)
            if (r5 == 0) goto L_0x0087
            r0 = 2000(0x7d0, float:2.803E-42)
            if (r6 < r0) goto L_0x0087
            r0 = 2002(0x7d2, float:2.805E-42)
            if (r6 <= r0) goto L_0x000d
            goto L_0x0087
        L_0x000d:
            com.unionpay.tsmservice.mi.ITsmService r0 = r4.e     // Catch:{ all -> 0x0084 }
            if (r0 == 0) goto L_0x0081
            android.content.Context r0 = r4.c     // Catch:{ all -> 0x0084 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x0084 }
            boolean r0 = b((java.lang.String) r0)     // Catch:{ all -> 0x0084 }
            r1 = 1000(0x3e8, float:1.401E-42)
            if (r0 == 0) goto L_0x007b
            java.util.HashMap r0 = r4.j     // Catch:{ all -> 0x0084 }
            android.content.Context r2 = r4.c     // Catch:{ all -> 0x0084 }
            java.lang.String r2 = r2.getPackageName()     // Catch:{ all -> 0x0084 }
            com.unionpay.tsmservice.mi.a r3 = new com.unionpay.tsmservice.mi.a     // Catch:{ all -> 0x0084 }
            r3.<init>(r8)     // Catch:{ all -> 0x0084 }
            r0.put(r2, r3)     // Catch:{ all -> 0x0084 }
            java.lang.String r0 = r5.getReserve()     // Catch:{ all -> 0x0084 }
            boolean r2 = r4.c()     // Catch:{ all -> 0x0084 }
            if (r2 == 0) goto L_0x0041
            java.lang.String r0 = g(r0)     // Catch:{ all -> 0x0084 }
            java.lang.String r0 = r4.h(r0)     // Catch:{ all -> 0x0084 }
        L_0x0041:
            boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x0084 }
            if (r2 != 0) goto L_0x004e
            java.lang.String r0 = c((java.lang.String) r0)     // Catch:{ all -> 0x0084 }
            r5.setReserve(r0)     // Catch:{ all -> 0x0084 }
        L_0x004e:
            com.unionpay.tsmservice.mi.ITsmService r0 = r4.e     // Catch:{ Exception -> 0x0071 }
            com.unionpay.tsmservice.mi.UPTsmAddon$a r2 = new com.unionpay.tsmservice.mi.UPTsmAddon$a     // Catch:{ Exception -> 0x0071 }
            r2.<init>()     // Catch:{ Exception -> 0x0071 }
            int r0 = r0.showSafetyKeyboard(r5, r6, r7, r2)     // Catch:{ Exception -> 0x0071 }
            if (r0 == 0) goto L_0x0066
            java.util.HashMap r2 = r4.j     // Catch:{ all -> 0x0084 }
            android.content.Context r3 = r4.c     // Catch:{ all -> 0x0084 }
            java.lang.String r3 = r3.getPackageName()     // Catch:{ all -> 0x0084 }
            r2.remove(r3)     // Catch:{ all -> 0x0084 }
        L_0x0066:
            r2 = -2
            if (r2 != r0) goto L_0x006f
            int r5 = a((int) r1, (com.unionpay.tsmservice.mi.request.SafetyKeyboardRequestParams) r5, (int) r6, (com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback) r7, (android.content.Context) r8)     // Catch:{ all -> 0x0084 }
            monitor-exit(r4)
            return r5
        L_0x006f:
            monitor-exit(r4)
            return r0
        L_0x0071:
            r5 = move-exception
            r5.printStackTrace()     // Catch:{ all -> 0x0084 }
            android.os.RemoteException r5 = new android.os.RemoteException     // Catch:{ all -> 0x0084 }
            r5.<init>()     // Catch:{ all -> 0x0084 }
            throw r5     // Catch:{ all -> 0x0084 }
        L_0x007b:
            int r5 = a((int) r1, (com.unionpay.tsmservice.mi.request.SafetyKeyboardRequestParams) r5, (int) r6, (com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback) r7, (android.content.Context) r8)     // Catch:{ all -> 0x0084 }
            monitor-exit(r4)
            return r5
        L_0x0081:
            r5 = -1
        L_0x0082:
            monitor-exit(r4)
            return r5
        L_0x0084:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        L_0x0087:
            r5 = -3
            goto L_0x0082
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.mi.UPTsmAddon.showSafetyKeyboard(com.unionpay.tsmservice.mi.request.SafetyKeyboardRequestParams, int, com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback, android.content.Context):int");
    }

    public void unbind() {
        if (this.d != null && this.f) {
            this.c.unbindService(this.d);
            this.f = false;
        }
    }
}
