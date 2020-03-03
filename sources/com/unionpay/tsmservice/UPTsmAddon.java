package com.unionpay.tsmservice;

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
import com.unionpay.tsmservice.ITsmActivityCallback;
import com.unionpay.tsmservice.ITsmCallback;
import com.unionpay.tsmservice.ITsmService;
import com.unionpay.tsmservice.data.Constant;
import com.unionpay.tsmservice.request.RequestParams;
import com.unionpay.tsmservice.request.SafetyKeyboardRequestParams;
import com.unionpay.tsmservice.utils.IUPJniInterface;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class UPTsmAddon {

    /* renamed from: a  reason: collision with root package name */
    private static UPTsmAddon f9823a = null;
    private static CopyOnWriteArrayList<UPTsmConnectionListener> b = null;
    private HashMap<String, ITsmCallback> A = new HashMap<>();
    private HashMap<String, ITsmCallback> B = new HashMap<>();
    private HashMap<String, ITsmCallback> C = new HashMap<>();
    private HashMap<String, ITsmCallback> D = new HashMap<>();
    private HashMap<String, ITsmCallback> E = new HashMap<>();
    private HashMap<String, ITsmCallback> F = new HashMap<>();
    private HashMap<String, ITsmCallback> G = new HashMap<>();
    private HashMap<String, ITsmCallback> H = new HashMap<>();
    private HashMap<String, ITsmCallback> I = new HashMap<>();
    private HashMap<String, ITsmCallback> J = new HashMap<>();
    private HashMap<String, ITsmCallback> K = new HashMap<>();
    private HashMap<String, ITsmCallback> L = new HashMap<>();
    private HashMap<String, ITsmCallback> M = new HashMap<>();
    private HashMap<String, ITsmCallback> N = new HashMap<>();
    private HashMap<String, ITsmCallback> O = new HashMap<>();
    private HashMap<String, ITsmCallback> P = new HashMap<>();
    private HashMap<String, ITsmCallback> Q = new HashMap<>();
    private HashMap<String, ITsmCallback> R = new HashMap<>();
    private HashMap<String, ITsmCallback> S = new HashMap<>();
    private HashMap<String, ITsmCallback> T = new HashMap<>();
    private HashMap<String, ITsmCallback> U = new HashMap<>();
    private HashMap<String, ITsmCallback> V = new HashMap<>();
    private HashMap<String, ITsmCallback> W = new HashMap<>();
    private HashMap<String, ITsmCallback> X = new HashMap<>();
    private HashMap<String, ITsmActivityCallback> Y = new HashMap<>();
    /* access modifiers changed from: private */
    public int[] Z;
    private final Handler.Callback aa = new Handler.Callback() {
        public final synchronized boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    UPTsmAddon.this.a();
                    return true;
                case 1:
                    UPTsmAddon.this.b();
                    return true;
                default:
                    return false;
            }
        }
    };
    /* access modifiers changed from: private */
    public final Handler ab = new Handler(Looper.getMainLooper(), this.aa);
    private Context c = null;
    private ServiceConnection d = null;
    /* access modifiers changed from: private */
    public ITsmService e = null;
    /* access modifiers changed from: private */
    public boolean f = false;
    private int g = 1;
    private boolean h = false;
    private HashMap<String, ITsmCallback> i = new HashMap<>();
    private HashMap<String, ITsmCallback> j = new HashMap<>();
    private HashMap<String, ITsmCallback> k = new HashMap<>();
    private HashMap<String, ITsmCallback> l = new HashMap<>();
    private HashMap<String, ITsmCallback> m = new HashMap<>();
    private HashMap<String, ITsmCallback> n = new HashMap<>();
    private HashMap<String, ITsmCallback> o = new HashMap<>();
    private HashMap<String, ITsmCallback> p = new HashMap<>();
    private HashMap<String, ITsmCallback> q = new HashMap<>();
    private HashMap<String, ITsmCallback> r = new HashMap<>();
    private HashMap<String, ITsmCallback> s = new HashMap<>();
    private HashMap<String, ITsmCallback> t = new HashMap<>();
    private HashMap<String, ITsmCallback> u = new HashMap<>();
    private HashMap<String, ITsmCallback> v = new HashMap<>();
    private HashMap<String, ITsmCallback> w = new HashMap<>();
    private HashMap<String, ITsmCallback> x = new HashMap<>();
    private HashMap<String, ITsmCallback> y = new HashMap<>();
    private HashMap<String, ITsmCallback> z = new HashMap<>();

    public interface UPTsmConnectionListener {
        void onTsmConnected();

        void onTsmDisconnected();
    }

    public class a extends ITsmActivityCallback.Stub {
        private int b = 1000;

        public a() {
        }

        public final void startActivity(String str, String str2, int i, Bundle bundle) throws RemoteException {
            UPTsmAddon.a((ITsmActivityCallback) UPTsmAddon.b(UPTsmAddon.this, this.b).get(UPTsmAddon.this.c.getPackageName()), str, str2, i, bundle);
            UPTsmAddon.b(UPTsmAddon.this, this.b).remove(UPTsmAddon.this.c.getPackageName());
        }
    }

    private class b extends ITsmCallback.Stub {
        private int b;
        private int c;

        private b(int i, int i2) {
            this.b = i;
            this.c = i2;
        }

        /* synthetic */ b(UPTsmAddon uPTsmAddon, int i, int i2, byte b2) {
            this(i, i2);
        }

        public final void onError(String str, String str2) throws RemoteException {
            Bundle bundle = new Bundle();
            bundle.putString("errorCode", str);
            bundle.putString("errorDesc", str2);
            UPTsmAddon.a((ITsmCallback) UPTsmAddon.a(UPTsmAddon.this, this.b).get(String.valueOf(this.c)), bundle);
            UPTsmAddon.a(UPTsmAddon.this, this.b).remove(String.valueOf(this.c));
            if (UPTsmAddon.a(UPTsmAddon.this, this.b).isEmpty()) {
                UPTsmAddon.this.Z[this.b] = 0;
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:46:0x0193  */
        /* JADX WARNING: Removed duplicated region for block: B:48:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void onResult(android.os.Bundle r7) throws android.os.RemoteException {
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
                if (r3 != 0) goto L_0x004d
                com.unionpay.tsmservice.UPTsmAddon r3 = com.unionpay.tsmservice.UPTsmAddon.this
                java.lang.String r3 = r3.c((java.lang.String) r4)
                byte[] r3 = android.util.Base64.decode(r3, r5)
                if (r3 == 0) goto L_0x003e
                int r4 = r3.length
                if (r4 == 0) goto L_0x003e
                int r4 = r3.length
                r2.unmarshall(r3, r5, r4)
                r2.setDataPosition(r5)
            L_0x003e:
                int r3 = r2.dataSize()
                if (r3 != 0) goto L_0x004d
                java.lang.String r7 = "errorCode"
                java.lang.String r0 = "010035"
                r1.putString(r7, r0)
                goto L_0x0159
            L_0x004d:
                if (r0 == 0) goto L_0x0148
                r3 = 3
                if (r0 == r3) goto L_0x013b
                r3 = 12
                if (r0 == r3) goto L_0x012e
                r3 = 20
                if (r0 == r3) goto L_0x0121
                r3 = 31
                if (r0 == r3) goto L_0x0114
                r3 = 36
                if (r0 == r3) goto L_0x0107
                switch(r0) {
                    case 22: goto L_0x00fa;
                    case 23: goto L_0x00ed;
                    default: goto L_0x0065;
                }
            L_0x0065:
                switch(r0) {
                    case 28: goto L_0x00e0;
                    case 29: goto L_0x00d2;
                    default: goto L_0x0068;
                }
            L_0x0068:
                switch(r0) {
                    case 38: goto L_0x00c4;
                    case 39: goto L_0x00b6;
                    default: goto L_0x006b;
                }
            L_0x006b:
                switch(r0) {
                    case 41: goto L_0x0107;
                    case 42: goto L_0x00a8;
                    case 43: goto L_0x009a;
                    case 44: goto L_0x008c;
                    case 45: goto L_0x007e;
                    case 46: goto L_0x0070;
                    default: goto L_0x006e;
                }
            L_0x006e:
                goto L_0x015a
            L_0x0070:
                java.lang.Class<com.unionpay.tsmservice.result.UniteResult> r7 = com.unionpay.tsmservice.result.UniteResult.class
                java.lang.ClassLoader r7 = r7.getClassLoader()
                android.os.Parcelable r7 = r2.readParcelable(r7)
                com.unionpay.tsmservice.result.UniteResult r7 = (com.unionpay.tsmservice.result.UniteResult) r7
                goto L_0x0154
            L_0x007e:
                java.lang.Class<com.unionpay.tsmservice.result.SendCustomDataResult> r7 = com.unionpay.tsmservice.result.SendCustomDataResult.class
                java.lang.ClassLoader r7 = r7.getClassLoader()
                android.os.Parcelable r7 = r2.readParcelable(r7)
                com.unionpay.tsmservice.result.SendCustomDataResult r7 = (com.unionpay.tsmservice.result.SendCustomDataResult) r7
                goto L_0x0154
            L_0x008c:
                java.lang.Class<com.unionpay.tsmservice.result.MessageDetailsResult> r7 = com.unionpay.tsmservice.result.MessageDetailsResult.class
                java.lang.ClassLoader r7 = r7.getClassLoader()
                android.os.Parcelable r7 = r2.readParcelable(r7)
                com.unionpay.tsmservice.result.MessageDetailsResult r7 = (com.unionpay.tsmservice.result.MessageDetailsResult) r7
                goto L_0x0154
            L_0x009a:
                java.lang.Class<com.unionpay.tsmservice.result.TransactionDetailsResult> r7 = com.unionpay.tsmservice.result.TransactionDetailsResult.class
                java.lang.ClassLoader r7 = r7.getClassLoader()
                android.os.Parcelable r7 = r2.readParcelable(r7)
                com.unionpay.tsmservice.result.TransactionDetailsResult r7 = (com.unionpay.tsmservice.result.TransactionDetailsResult) r7
                goto L_0x0154
            L_0x00a8:
                java.lang.Class<com.unionpay.tsmservice.result.AcquireSeAppListResult> r7 = com.unionpay.tsmservice.result.AcquireSeAppListResult.class
                java.lang.ClassLoader r7 = r7.getClassLoader()
                android.os.Parcelable r7 = r2.readParcelable(r7)
                com.unionpay.tsmservice.result.AcquireSeAppListResult r7 = (com.unionpay.tsmservice.result.AcquireSeAppListResult) r7
                goto L_0x0154
            L_0x00b6:
                java.lang.Class<com.unionpay.tsmservice.result.OnlinePaymentVerifyResult> r7 = com.unionpay.tsmservice.result.OnlinePaymentVerifyResult.class
                java.lang.ClassLoader r7 = r7.getClassLoader()
                android.os.Parcelable r7 = r2.readParcelable(r7)
                com.unionpay.tsmservice.result.OnlinePaymentVerifyResult r7 = (com.unionpay.tsmservice.result.OnlinePaymentVerifyResult) r7
                goto L_0x0154
            L_0x00c4:
                java.lang.Class<com.unionpay.tsmservice.result.AddCardResult> r7 = com.unionpay.tsmservice.result.AddCardResult.class
                java.lang.ClassLoader r7 = r7.getClassLoader()
                android.os.Parcelable r7 = r2.readParcelable(r7)
                com.unionpay.tsmservice.result.AddCardResult r7 = (com.unionpay.tsmservice.result.AddCardResult) r7
                goto L_0x0154
            L_0x00d2:
                java.lang.Class<com.unionpay.tsmservice.result.CheckSSamsungPayResult> r7 = com.unionpay.tsmservice.result.CheckSSamsungPayResult.class
                java.lang.ClassLoader r7 = r7.getClassLoader()
                android.os.Parcelable r7 = r2.readParcelable(r7)
                com.unionpay.tsmservice.result.CheckSSamsungPayResult r7 = (com.unionpay.tsmservice.result.CheckSSamsungPayResult) r7
                goto L_0x0154
            L_0x00e0:
                java.lang.Class<com.unionpay.tsmservice.result.GetCardInfoBySpayResult> r7 = com.unionpay.tsmservice.result.GetCardInfoBySpayResult.class
                java.lang.ClassLoader r7 = r7.getClassLoader()
                android.os.Parcelable r7 = r2.readParcelable(r7)
                com.unionpay.tsmservice.result.GetCardInfoBySpayResult r7 = (com.unionpay.tsmservice.result.GetCardInfoBySpayResult) r7
                goto L_0x0154
            L_0x00ed:
                java.lang.Class<com.unionpay.tsmservice.result.EncryptDataResult> r7 = com.unionpay.tsmservice.result.EncryptDataResult.class
                java.lang.ClassLoader r7 = r7.getClassLoader()
                android.os.Parcelable r7 = r2.readParcelable(r7)
                com.unionpay.tsmservice.result.EncryptDataResult r7 = (com.unionpay.tsmservice.result.EncryptDataResult) r7
                goto L_0x0154
            L_0x00fa:
                java.lang.Class<com.unionpay.tsmservice.result.SendApduResult> r7 = com.unionpay.tsmservice.result.SendApduResult.class
                java.lang.ClassLoader r7 = r7.getClassLoader()
                android.os.Parcelable r7 = r2.readParcelable(r7)
                com.unionpay.tsmservice.result.SendApduResult r7 = (com.unionpay.tsmservice.result.SendApduResult) r7
                goto L_0x0154
            L_0x0107:
                java.lang.Class<com.unionpay.tsmservice.result.VendorPayStatusResult> r7 = com.unionpay.tsmservice.result.VendorPayStatusResult.class
                java.lang.ClassLoader r7 = r7.getClassLoader()
                android.os.Parcelable r7 = r2.readParcelable(r7)
                com.unionpay.tsmservice.result.VendorPayStatusResult r7 = (com.unionpay.tsmservice.result.VendorPayStatusResult) r7
                goto L_0x0154
            L_0x0114:
                java.lang.Class<com.unionpay.tsmservice.result.GetEncryptDataResult> r7 = com.unionpay.tsmservice.result.GetEncryptDataResult.class
                java.lang.ClassLoader r7 = r7.getClassLoader()
                android.os.Parcelable r7 = r2.readParcelable(r7)
                com.unionpay.tsmservice.result.GetEncryptDataResult r7 = (com.unionpay.tsmservice.result.GetEncryptDataResult) r7
                goto L_0x0154
            L_0x0121:
                java.lang.Class<com.unionpay.tsmservice.result.OpenChannelResult> r7 = com.unionpay.tsmservice.result.OpenChannelResult.class
                java.lang.ClassLoader r7 = r7.getClassLoader()
                android.os.Parcelable r7 = r2.readParcelable(r7)
                com.unionpay.tsmservice.result.OpenChannelResult r7 = (com.unionpay.tsmservice.result.OpenChannelResult) r7
                goto L_0x0154
            L_0x012e:
                java.lang.Class<com.unionpay.tsmservice.result.GetSeIdResult> r7 = com.unionpay.tsmservice.result.GetSeIdResult.class
                java.lang.ClassLoader r7 = r7.getClassLoader()
                android.os.Parcelable r7 = r2.readParcelable(r7)
                com.unionpay.tsmservice.result.GetSeIdResult r7 = (com.unionpay.tsmservice.result.GetSeIdResult) r7
                goto L_0x0154
            L_0x013b:
                java.lang.Class<com.unionpay.tsmservice.result.GetSeAppListResult> r7 = com.unionpay.tsmservice.result.GetSeAppListResult.class
                java.lang.ClassLoader r7 = r7.getClassLoader()
                android.os.Parcelable r7 = r2.readParcelable(r7)
                com.unionpay.tsmservice.result.GetSeAppListResult r7 = (com.unionpay.tsmservice.result.GetSeAppListResult) r7
                goto L_0x0154
            L_0x0148:
                java.lang.Class<com.unionpay.tsmservice.result.InitResult> r7 = com.unionpay.tsmservice.result.InitResult.class
                java.lang.ClassLoader r7 = r7.getClassLoader()
                android.os.Parcelable r7 = r2.readParcelable(r7)
                com.unionpay.tsmservice.result.InitResult r7 = (com.unionpay.tsmservice.result.InitResult) r7
            L_0x0154:
                java.lang.String r0 = "result"
                r1.putParcelable(r0, r7)
            L_0x0159:
                r7 = r1
            L_0x015a:
                r2.recycle()
                com.unionpay.tsmservice.UPTsmAddon r0 = com.unionpay.tsmservice.UPTsmAddon.this
                int r1 = r6.b
                java.util.HashMap r0 = com.unionpay.tsmservice.UPTsmAddon.a((com.unionpay.tsmservice.UPTsmAddon) r0, (int) r1)
                int r1 = r6.c
                java.lang.String r1 = java.lang.String.valueOf(r1)
                java.lang.Object r0 = r0.get(r1)
                com.unionpay.tsmservice.ITsmCallback r0 = (com.unionpay.tsmservice.ITsmCallback) r0
                com.unionpay.tsmservice.UPTsmAddon.a((com.unionpay.tsmservice.ITsmCallback) r0, (android.os.Bundle) r7)
                com.unionpay.tsmservice.UPTsmAddon r7 = com.unionpay.tsmservice.UPTsmAddon.this
                int r0 = r6.b
                java.util.HashMap r7 = com.unionpay.tsmservice.UPTsmAddon.a((com.unionpay.tsmservice.UPTsmAddon) r7, (int) r0)
                int r0 = r6.c
                java.lang.String r0 = java.lang.String.valueOf(r0)
                r7.remove(r0)
                com.unionpay.tsmservice.UPTsmAddon r7 = com.unionpay.tsmservice.UPTsmAddon.this
                int r0 = r6.b
                java.util.HashMap r7 = com.unionpay.tsmservice.UPTsmAddon.a((com.unionpay.tsmservice.UPTsmAddon) r7, (int) r0)
                boolean r7 = r7.isEmpty()
                if (r7 == 0) goto L_0x019d
                com.unionpay.tsmservice.UPTsmAddon r7 = com.unionpay.tsmservice.UPTsmAddon.this
                int[] r7 = r7.Z
                int r0 = r6.b
                r7[r0] = r5
            L_0x019d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.b.onResult(android.os.Bundle):void");
        }
    }

    static {
        try {
            System.loadLibrary("uptsmaddon");
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
        }
    }

    private UPTsmAddon(Context context) {
        this.c = context;
        this.Z = new int[47];
        if (!a(context)) {
            throw new RuntimeException();
        }
    }

    private static int a(int i2, RequestParams requestParams, ITsmCallback iTsmCallback) throws RemoteException {
        return new SessionKeyReExchange(f9823a, i2, requestParams, iTsmCallback).reExchangeKey();
    }

    private static int a(int i2, RequestParams requestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) throws RemoteException {
        return new SessionKeyReExchange(f9823a, i2, requestParams, iTsmCallback, iTsmProgressCallback).reExchangeKey();
    }

    private static int a(int i2, SafetyKeyboardRequestParams safetyKeyboardRequestParams, int i3, OnSafetyKeyboardCallback onSafetyKeyboardCallback, Context context) throws RemoteException {
        return new SessionKeyReExchange(f9823a, i2, safetyKeyboardRequestParams, i3, onSafetyKeyboardCallback, context).reExchangeKey();
    }

    private String a(Bundle bundle) {
        String str = "";
        Parcel obtain = Parcel.obtain();
        obtain.writeBundle(bundle);
        byte[] marshall = obtain.marshall();
        if (!(marshall == null || marshall.length == 0)) {
            str = b(Base64.encodeToString(marshall, 0));
        }
        obtain.recycle();
        return str;
    }

    static /* synthetic */ HashMap a(UPTsmAddon uPTsmAddon, int i2) {
        switch (i2) {
            case 0:
                return uPTsmAddon.i;
            case 1:
                return uPTsmAddon.j;
            case 2:
                return uPTsmAddon.l;
            case 3:
                return uPTsmAddon.k;
            case 4:
                return uPTsmAddon.n;
            case 5:
                return uPTsmAddon.m;
            case 6:
                return uPTsmAddon.A;
            case 7:
                return uPTsmAddon.u;
            case 8:
                return uPTsmAddon.v;
            case 9:
                return uPTsmAddon.o;
            case 10:
                return uPTsmAddon.t;
            case 11:
                return uPTsmAddon.r;
            case 12:
                return uPTsmAddon.z;
            case 13:
                return uPTsmAddon.y;
            case 14:
                return uPTsmAddon.E;
            case 15:
                return uPTsmAddon.p;
            case 16:
                return uPTsmAddon.C;
            case 17:
                return uPTsmAddon.D;
            case 18:
                return uPTsmAddon.q;
            case 19:
                return uPTsmAddon.s;
            case 20:
                return uPTsmAddon.w;
            case 21:
                return uPTsmAddon.F;
            case 22:
                return uPTsmAddon.x;
            case 23:
                return uPTsmAddon.B;
            case 24:
                return uPTsmAddon.G;
            case 25:
                return uPTsmAddon.H;
            case 28:
                return uPTsmAddon.I;
            case 29:
                return uPTsmAddon.J;
            case 30:
                return uPTsmAddon.K;
            case 31:
                return uPTsmAddon.L;
            case 35:
                return uPTsmAddon.M;
            case 36:
                return uPTsmAddon.N;
            case 37:
                return uPTsmAddon.O;
            case 38:
                return uPTsmAddon.P;
            case 39:
                return uPTsmAddon.Q;
            case 40:
                return uPTsmAddon.R;
            case 41:
                return uPTsmAddon.S;
            case 42:
                return uPTsmAddon.T;
            case 43:
                return uPTsmAddon.U;
            case 44:
                return uPTsmAddon.V;
            case 45:
                return uPTsmAddon.W;
            case 46:
                return uPTsmAddon.X;
            default:
                return null;
        }
    }

    private static HashMap<String, String> a(HashMap<String, String> hashMap) {
        String str;
        if (hashMap == null) {
            return new HashMap<>();
        }
        HashMap<String, String> hashMap2 = new HashMap<>();
        for (String next : hashMap.keySet()) {
            if (!(next == null || (str = hashMap.get(next)) == null)) {
                hashMap2.put(new String(next), new String(str));
            }
        }
        return hashMap2;
    }

    /* access modifiers changed from: private */
    public synchronized void a() {
        if (b != null && b.size() > 0) {
            Iterator<UPTsmConnectionListener> it = b.iterator();
            while (it.hasNext()) {
                UPTsmConnectionListener next = it.next();
                if (next != null) {
                    next.onTsmConnected();
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

    private static boolean a(String str) {
        try {
            return IUPJniInterface.cSKV(str);
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private String b(String str) {
        try {
            return IUPJniInterface.eMG(str, this.g);
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            return "";
        }
    }

    static /* synthetic */ HashMap b(UPTsmAddon uPTsmAddon, int i2) {
        if (i2 != 1000) {
            return null;
        }
        return uPTsmAddon.Y;
    }

    /* access modifiers changed from: private */
    public synchronized void b() {
        if (b != null && b.size() > 0) {
            Iterator<UPTsmConnectionListener> it = b.iterator();
            while (it.hasNext()) {
                UPTsmConnectionListener next = it.next();
                if (next != null) {
                    next.onTsmDisconnected();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public String c(String str) {
        try {
            return IUPJniInterface.dMG(str, this.g);
        } catch (UnsatisfiedLinkError e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private boolean c() {
        String e2 = e("com.unionpay.tsmservice");
        if (e2 == null || e2.compareTo(Constant.SUPPORTED_MIN_APK_VERSION) < 0) {
            return false;
        }
        if (e2.compareTo(Constant.APK_VERSION_010018) >= 0) {
            this.g = 1;
            this.h = true;
            return true;
        } else if (e2.compareTo(Constant.APK_VERSION_010012) >= 0 && e2.compareTo(Constant.APK_VERSION_010016) <= 0) {
            this.g = 2;
            this.h = false;
            return true;
        } else if (e2.compareTo(Constant.APK_VERSION_010017) != 0 && e2.compareTo(Constant.SUPPORTED_MIN_APK_VERSION) != 0) {
            return false;
        } else {
            this.g = 1;
            this.h = false;
            return true;
        }
    }

    private boolean d(String str) {
        String e2 = e("com.unionpay.tsmservice");
        return e2 != null && e2.compareTo(str) >= 0;
    }

    private String e(String str) {
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

    private static String f(String str) {
        try {
            JSONObject jSONObject = TextUtils.isEmpty(str) ? new JSONObject() : new JSONObject(str);
            jSONObject.put("jarVersionCode", 52);
            return jSONObject.toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return str;
        }
    }

    private String g(String str) {
        try {
            JSONObject jSONObject = TextUtils.isEmpty(str) ? new JSONObject() : new JSONObject(str);
            jSONObject.put("packageName", this.c.getPackageName());
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
            if (f9823a == null) {
                f9823a = new UPTsmAddon(context.getApplicationContext());
            }
            if (b == null) {
                b = new CopyOnWriteArrayList<>();
            }
            UPTsmAddon uPTsmAddon = f9823a;
            return uPTsmAddon;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0005, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int acquireSEAppList(com.unionpay.tsmservice.request.AcquireSEAppListRequestParams r8, com.unionpay.tsmservice.ITsmCallback r9) throws android.os.RemoteException {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r9 != 0) goto L_0x0006
            r8 = -3
        L_0x0004:
            monitor-exit(r7)
            return r8
        L_0x0006:
            java.lang.String r0 = "01.00.28"
            boolean r0 = r7.d((java.lang.String) r0)     // Catch:{ all -> 0x00ba }
            r1 = -8
            if (r0 != 0) goto L_0x0011
            monitor-exit(r7)
            return r1
        L_0x0011:
            boolean r0 = r7.c()     // Catch:{ all -> 0x00ba }
            if (r0 != 0) goto L_0x0019
            monitor-exit(r7)
            return r1
        L_0x0019:
            com.unionpay.tsmservice.ITsmService r0 = r7.e     // Catch:{ all -> 0x00ba }
            if (r0 == 0) goto L_0x00b7
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x00ba }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00ba }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x00ba }
            r1 = 42
            if (r0 == 0) goto L_0x00b1
            com.unionpay.tsmservice.request.AcquireSEAppListRequestParams r0 = new com.unionpay.tsmservice.request.AcquireSEAppListRequestParams     // Catch:{ all -> 0x00ba }
            r0.<init>()     // Catch:{ all -> 0x00ba }
            java.lang.String r2 = ""
            if (r8 == 0) goto L_0x004f
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x00ba }
            android.os.Bundle r3 = r8.getParams()     // Catch:{ all -> 0x00ba }
            if (r3 == 0) goto L_0x004f
            android.os.Bundle r4 = new android.os.Bundle     // Catch:{ all -> 0x00ba }
            r4.<init>()     // Catch:{ all -> 0x00ba }
            java.lang.String r5 = "encryptData"
            java.lang.String r3 = r7.a((android.os.Bundle) r3)     // Catch:{ all -> 0x00ba }
            r4.putString(r5, r3)     // Catch:{ all -> 0x00ba }
            r0.setParams(r4)     // Catch:{ all -> 0x00ba }
        L_0x004f:
            boolean r3 = r7.h     // Catch:{ all -> 0x00ba }
            if (r3 == 0) goto L_0x005b
            java.lang.String r2 = f(r2)     // Catch:{ all -> 0x00ba }
            java.lang.String r2 = r7.g(r2)     // Catch:{ all -> 0x00ba }
        L_0x005b:
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00ba }
            if (r3 != 0) goto L_0x0068
            java.lang.String r2 = r7.b((java.lang.String) r2)     // Catch:{ all -> 0x00ba }
            r0.setReserve(r2)     // Catch:{ all -> 0x00ba }
        L_0x0068:
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.T     // Catch:{ all -> 0x00ba }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00ba }
            r3 = r3[r1]     // Catch:{ all -> 0x00ba }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00ba }
            r2.put(r3, r9)     // Catch:{ all -> 0x00ba }
            com.unionpay.tsmservice.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x00a7 }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x00a7 }
            int[] r4 = r7.Z     // Catch:{ Exception -> 0x00a7 }
            r5 = r4[r1]     // Catch:{ Exception -> 0x00a7 }
            int r6 = r5 + 1
            r4[r1] = r6     // Catch:{ Exception -> 0x00a7 }
            r4 = 0
            r3.<init>(r7, r1, r5, r4)     // Catch:{ Exception -> 0x00a7 }
            int r0 = r2.acquireSEAppList(r0, r3)     // Catch:{ Exception -> 0x00a7 }
            if (r0 == 0) goto L_0x009c
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.T     // Catch:{ all -> 0x00ba }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00ba }
            r4 = r3[r1]     // Catch:{ all -> 0x00ba }
            int r4 = r4 + -1
            r3[r1] = r4     // Catch:{ all -> 0x00ba }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x00ba }
            r2.remove(r3)     // Catch:{ all -> 0x00ba }
        L_0x009c:
            r2 = -2
            if (r2 != r0) goto L_0x00a5
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x00ba }
            monitor-exit(r7)
            return r8
        L_0x00a5:
            monitor-exit(r7)
            return r0
        L_0x00a7:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x00ba }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x00ba }
            r8.<init>()     // Catch:{ all -> 0x00ba }
            throw r8     // Catch:{ all -> 0x00ba }
        L_0x00b1:
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x00ba }
            monitor-exit(r7)
            return r8
        L_0x00b7:
            r8 = -1
            goto L_0x0004
        L_0x00ba:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.acquireSEAppList(com.unionpay.tsmservice.request.AcquireSEAppListRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0005, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int activateVendorPay(com.unionpay.tsmservice.request.ActivateVendorPayRequestParams r8, com.unionpay.tsmservice.ITsmCallback r9) throws android.os.RemoteException {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r9 != 0) goto L_0x0006
            r8 = -3
        L_0x0004:
            monitor-exit(r7)
            return r8
        L_0x0006:
            java.lang.String r0 = "01.00.20"
            boolean r0 = r7.d((java.lang.String) r0)     // Catch:{ all -> 0x00a3 }
            r1 = -8
            if (r0 != 0) goto L_0x0011
            monitor-exit(r7)
            return r1
        L_0x0011:
            boolean r0 = r7.c()     // Catch:{ all -> 0x00a3 }
            if (r0 != 0) goto L_0x0019
            monitor-exit(r7)
            return r1
        L_0x0019:
            com.unionpay.tsmservice.ITsmService r0 = r7.e     // Catch:{ all -> 0x00a3 }
            if (r0 == 0) goto L_0x00a0
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x00a3 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00a3 }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x00a3 }
            r1 = 37
            if (r0 == 0) goto L_0x009a
            com.unionpay.tsmservice.request.ActivateVendorPayRequestParams r0 = new com.unionpay.tsmservice.request.ActivateVendorPayRequestParams     // Catch:{ all -> 0x00a3 }
            r0.<init>()     // Catch:{ all -> 0x00a3 }
            java.lang.String r2 = ""
            if (r8 == 0) goto L_0x0038
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x00a3 }
        L_0x0038:
            boolean r3 = r7.h     // Catch:{ all -> 0x00a3 }
            if (r3 == 0) goto L_0x0044
            java.lang.String r2 = f(r2)     // Catch:{ all -> 0x00a3 }
            java.lang.String r2 = r7.g(r2)     // Catch:{ all -> 0x00a3 }
        L_0x0044:
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00a3 }
            if (r3 != 0) goto L_0x0051
            java.lang.String r2 = r7.b((java.lang.String) r2)     // Catch:{ all -> 0x00a3 }
            r0.setReserve(r2)     // Catch:{ all -> 0x00a3 }
        L_0x0051:
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.O     // Catch:{ all -> 0x00a3 }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00a3 }
            r3 = r3[r1]     // Catch:{ all -> 0x00a3 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00a3 }
            r2.put(r3, r9)     // Catch:{ all -> 0x00a3 }
            com.unionpay.tsmservice.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x0090 }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x0090 }
            int[] r4 = r7.Z     // Catch:{ Exception -> 0x0090 }
            r5 = r4[r1]     // Catch:{ Exception -> 0x0090 }
            int r6 = r5 + 1
            r4[r1] = r6     // Catch:{ Exception -> 0x0090 }
            r4 = 0
            r3.<init>(r7, r1, r5, r4)     // Catch:{ Exception -> 0x0090 }
            int r0 = r2.activateVendorPay(r0, r3)     // Catch:{ Exception -> 0x0090 }
            if (r0 == 0) goto L_0x0085
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.O     // Catch:{ all -> 0x00a3 }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00a3 }
            r4 = r3[r1]     // Catch:{ all -> 0x00a3 }
            int r4 = r4 + -1
            r3[r1] = r4     // Catch:{ all -> 0x00a3 }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x00a3 }
            r2.remove(r3)     // Catch:{ all -> 0x00a3 }
        L_0x0085:
            r2 = -2
            if (r2 != r0) goto L_0x008e
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x00a3 }
            monitor-exit(r7)
            return r8
        L_0x008e:
            monitor-exit(r7)
            return r0
        L_0x0090:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x00a3 }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x00a3 }
            r8.<init>()     // Catch:{ all -> 0x00a3 }
            throw r8     // Catch:{ all -> 0x00a3 }
        L_0x009a:
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x00a3 }
            monitor-exit(r7)
            return r8
        L_0x00a0:
            r8 = -1
            goto L_0x0004
        L_0x00a3:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.activateVendorPay(com.unionpay.tsmservice.request.ActivateVendorPayRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00b6, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int addCardToVendorPay(com.unionpay.tsmservice.request.AddCardToVendorPayRequestParams r8, com.unionpay.tsmservice.ITsmCallback r9, com.unionpay.tsmservice.ITsmProgressCallback r10) throws android.os.RemoteException {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r8 == 0) goto L_0x00ba
            if (r9 != 0) goto L_0x0007
            goto L_0x00ba
        L_0x0007:
            java.lang.String r0 = "01.00.20"
            boolean r0 = r7.d((java.lang.String) r0)     // Catch:{ all -> 0x00b7 }
            r1 = -8
            if (r0 != 0) goto L_0x0012
            monitor-exit(r7)
            return r1
        L_0x0012:
            boolean r0 = r7.c()     // Catch:{ all -> 0x00b7 }
            if (r0 != 0) goto L_0x001a
            monitor-exit(r7)
            return r1
        L_0x001a:
            com.unionpay.tsmservice.ITsmService r0 = r7.e     // Catch:{ all -> 0x00b7 }
            if (r0 == 0) goto L_0x00b4
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x00b7 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00b7 }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x00b7 }
            r1 = 38
            if (r0 == 0) goto L_0x00ae
            com.unionpay.tsmservice.request.AddCardToVendorPayRequestParams r0 = new com.unionpay.tsmservice.request.AddCardToVendorPayRequestParams     // Catch:{ all -> 0x00b7 }
            r0.<init>()     // Catch:{ all -> 0x00b7 }
            android.os.Bundle r2 = r8.getParams()     // Catch:{ all -> 0x00b7 }
            if (r2 == 0) goto L_0x0048
            android.os.Bundle r3 = new android.os.Bundle     // Catch:{ all -> 0x00b7 }
            r3.<init>()     // Catch:{ all -> 0x00b7 }
            java.lang.String r4 = "encryptData"
            java.lang.String r2 = r7.a((android.os.Bundle) r2)     // Catch:{ all -> 0x00b7 }
            r3.putString(r4, r2)     // Catch:{ all -> 0x00b7 }
            r0.setParams(r3)     // Catch:{ all -> 0x00b7 }
        L_0x0048:
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x00b7 }
            boolean r3 = r7.h     // Catch:{ all -> 0x00b7 }
            if (r3 == 0) goto L_0x0058
            java.lang.String r2 = f(r2)     // Catch:{ all -> 0x00b7 }
            java.lang.String r2 = r7.g(r2)     // Catch:{ all -> 0x00b7 }
        L_0x0058:
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00b7 }
            if (r3 != 0) goto L_0x0065
            java.lang.String r2 = r7.b((java.lang.String) r2)     // Catch:{ all -> 0x00b7 }
            r0.setReserve(r2)     // Catch:{ all -> 0x00b7 }
        L_0x0065:
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.P     // Catch:{ all -> 0x00b7 }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00b7 }
            r3 = r3[r1]     // Catch:{ all -> 0x00b7 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00b7 }
            r2.put(r3, r9)     // Catch:{ all -> 0x00b7 }
            com.unionpay.tsmservice.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x00a4 }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x00a4 }
            int[] r4 = r7.Z     // Catch:{ Exception -> 0x00a4 }
            r5 = r4[r1]     // Catch:{ Exception -> 0x00a4 }
            int r6 = r5 + 1
            r4[r1] = r6     // Catch:{ Exception -> 0x00a4 }
            r4 = 0
            r3.<init>(r7, r1, r5, r4)     // Catch:{ Exception -> 0x00a4 }
            int r0 = r2.addCardToVendorPay(r0, r3, r10)     // Catch:{ Exception -> 0x00a4 }
            if (r0 == 0) goto L_0x0099
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.P     // Catch:{ all -> 0x00b7 }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00b7 }
            r4 = r3[r1]     // Catch:{ all -> 0x00b7 }
            int r4 = r4 + -1
            r3[r1] = r4     // Catch:{ all -> 0x00b7 }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x00b7 }
            r2.remove(r3)     // Catch:{ all -> 0x00b7 }
        L_0x0099:
            r2 = -2
            if (r2 != r0) goto L_0x00a2
            int r8 = a(r1, r8, r9, r10)     // Catch:{ all -> 0x00b7 }
            monitor-exit(r7)
            return r8
        L_0x00a2:
            monitor-exit(r7)
            return r0
        L_0x00a4:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x00b7 }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x00b7 }
            r8.<init>()     // Catch:{ all -> 0x00b7 }
            throw r8     // Catch:{ all -> 0x00b7 }
        L_0x00ae:
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x00b7 }
            monitor-exit(r7)
            return r8
        L_0x00b4:
            r8 = -1
        L_0x00b5:
            monitor-exit(r7)
            return r8
        L_0x00b7:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        L_0x00ba:
            r8 = -3
            goto L_0x00b5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.addCardToVendorPay(com.unionpay.tsmservice.request.AddCardToVendorPayRequestParams, com.unionpay.tsmservice.ITsmCallback, com.unionpay.tsmservice.ITsmProgressCallback):int");
    }

    public synchronized void addConnectionListener(UPTsmConnectionListener uPTsmConnectionListener) {
        if (uPTsmConnectionListener != null) {
            b.add(uPTsmConnectionListener);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x008f, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000f, code lost:
        return r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int appDataUpdate(com.unionpay.tsmservice.request.AppDataUpdateRequestParams r7, com.unionpay.tsmservice.ITsmCallback r8, com.unionpay.tsmservice.ITsmProgressCallback r9) throws android.os.RemoteException {
        /*
            r6 = this;
            monitor-enter(r6)
            if (r7 == 0) goto L_0x00a6
            if (r8 != 0) goto L_0x0007
            goto L_0x00a6
        L_0x0007:
            boolean r0 = r6.c()     // Catch:{ all -> 0x00a3 }
            if (r0 != 0) goto L_0x0010
            r7 = -8
        L_0x000e:
            monitor-exit(r6)
            return r7
        L_0x0010:
            com.unionpay.tsmservice.ITsmService r0 = r6.e     // Catch:{ all -> 0x00a3 }
            if (r0 == 0) goto L_0x00a0
            android.content.Context r0 = r6.c     // Catch:{ all -> 0x00a3 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00a3 }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x00a3 }
            r1 = 18
            if (r0 == 0) goto L_0x009a
            com.unionpay.tsmservice.request.AppDataUpdateRequestParams r0 = new com.unionpay.tsmservice.request.AppDataUpdateRequestParams     // Catch:{ all -> 0x00a3 }
            r0.<init>()     // Catch:{ all -> 0x00a3 }
            java.lang.String r2 = r7.getReserve()     // Catch:{ all -> 0x00a3 }
            com.unionpay.tsmservice.AppID r3 = r7.getAppID()     // Catch:{ all -> 0x00a3 }
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00a3 }
            if (r4 != 0) goto L_0x003c
            java.lang.String r2 = r6.b((java.lang.String) r2)     // Catch:{ all -> 0x00a3 }
            r0.setReserve(r2)     // Catch:{ all -> 0x00a3 }
        L_0x003c:
            if (r3 == 0) goto L_0x0062
            java.lang.String r2 = r3.getAppAid()     // Catch:{ all -> 0x00a3 }
            java.lang.String r3 = r3.getAppVersion()     // Catch:{ all -> 0x00a3 }
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00a3 }
            if (r4 != 0) goto L_0x0062
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x00a3 }
            if (r4 != 0) goto L_0x0062
            com.unionpay.tsmservice.AppID r4 = new com.unionpay.tsmservice.AppID     // Catch:{ all -> 0x00a3 }
            java.lang.String r2 = r6.b((java.lang.String) r2)     // Catch:{ all -> 0x00a3 }
            java.lang.String r3 = r6.b((java.lang.String) r3)     // Catch:{ all -> 0x00a3 }
            r4.<init>(r2, r3)     // Catch:{ all -> 0x00a3 }
            r0.setAppID(r4)     // Catch:{ all -> 0x00a3 }
        L_0x0062:
            com.unionpay.tsmservice.ITsmService r2 = r6.e     // Catch:{ Exception -> 0x0090 }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x0090 }
            int[] r4 = r6.Z     // Catch:{ Exception -> 0x0090 }
            r4 = r4[r1]     // Catch:{ Exception -> 0x0090 }
            r5 = 0
            r3.<init>(r6, r1, r4, r5)     // Catch:{ Exception -> 0x0090 }
            int r0 = r2.appDataUpdate(r0, r3, r9)     // Catch:{ Exception -> 0x0090 }
            r2 = -2
            if (r2 != r0) goto L_0x007b
            int r7 = a(r1, r7, r8, r9)     // Catch:{ all -> 0x00a3 }
            monitor-exit(r6)
            return r7
        L_0x007b:
            if (r0 != 0) goto L_0x008e
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r7 = r6.q     // Catch:{ all -> 0x00a3 }
            int[] r9 = r6.Z     // Catch:{ all -> 0x00a3 }
            r2 = r9[r1]     // Catch:{ all -> 0x00a3 }
            int r3 = r2 + 1
            r9[r1] = r3     // Catch:{ all -> 0x00a3 }
            java.lang.String r9 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x00a3 }
            r7.put(r9, r8)     // Catch:{ all -> 0x00a3 }
        L_0x008e:
            monitor-exit(r6)
            return r0
        L_0x0090:
            r7 = move-exception
            r7.printStackTrace()     // Catch:{ all -> 0x00a3 }
            android.os.RemoteException r7 = new android.os.RemoteException     // Catch:{ all -> 0x00a3 }
            r7.<init>()     // Catch:{ all -> 0x00a3 }
            throw r7     // Catch:{ all -> 0x00a3 }
        L_0x009a:
            int r7 = a(r1, r7, r8, r9)     // Catch:{ all -> 0x00a3 }
            monitor-exit(r6)
            return r7
        L_0x00a0:
            r7 = -1
            goto L_0x000e
        L_0x00a3:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        L_0x00a6:
            r7 = -3
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.appDataUpdate(com.unionpay.tsmservice.request.AppDataUpdateRequestParams, com.unionpay.tsmservice.ITsmCallback, com.unionpay.tsmservice.ITsmProgressCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x009f, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00a0, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00a9, code lost:
        throw new android.os.RemoteException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x01df, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x01e0, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:?, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01e9, code lost:
        throw new android.os.RemoteException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
        return r0;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:27:0x0074, B:77:0x01aa] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int appDelete(com.unionpay.tsmservice.request.AppDeleteRequestParams r18, com.unionpay.tsmservice.ITsmCallback r19, com.unionpay.tsmservice.ITsmProgressCallback r20) throws android.os.RemoteException {
        /*
            r17 = this;
            r1 = r17
            r0 = r18
            r2 = r19
            r3 = r20
            monitor-enter(r17)
            if (r0 == 0) goto L_0x01fa
            if (r2 != 0) goto L_0x000f
            goto L_0x01fa
        L_0x000f:
            boolean r4 = r17.c()     // Catch:{ all -> 0x01f7 }
            if (r4 != 0) goto L_0x0018
            r0 = -8
        L_0x0016:
            monitor-exit(r17)
            return r0
        L_0x0018:
            com.unionpay.tsmservice.ITsmService r4 = r1.e     // Catch:{ all -> 0x01f7 }
            if (r4 == 0) goto L_0x01f4
            android.content.Context r4 = r1.c     // Catch:{ all -> 0x01f7 }
            java.lang.String r4 = r4.getPackageName()     // Catch:{ all -> 0x01f7 }
            boolean r4 = a((java.lang.String) r4)     // Catch:{ all -> 0x01f7 }
            r5 = 17
            if (r4 == 0) goto L_0x01ea
            com.unionpay.tsmservice.request.AppDeleteRequestParams r4 = new com.unionpay.tsmservice.request.AppDeleteRequestParams     // Catch:{ all -> 0x01f7 }
            r4.<init>()     // Catch:{ all -> 0x01f7 }
            java.lang.String r6 = r18.getReserve()     // Catch:{ all -> 0x01f7 }
            com.unionpay.tsmservice.AppID r7 = r18.getAppID()     // Catch:{ all -> 0x01f7 }
            boolean r8 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x01f7 }
            if (r8 != 0) goto L_0x0044
            java.lang.String r6 = r1.b((java.lang.String) r6)     // Catch:{ all -> 0x01f7 }
            r4.setReserve(r6)     // Catch:{ all -> 0x01f7 }
        L_0x0044:
            if (r7 == 0) goto L_0x006a
            java.lang.String r6 = r7.getAppAid()     // Catch:{ all -> 0x01f7 }
            java.lang.String r7 = r7.getAppVersion()     // Catch:{ all -> 0x01f7 }
            boolean r8 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x01f7 }
            if (r8 != 0) goto L_0x006a
            boolean r8 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x01f7 }
            if (r8 != 0) goto L_0x006a
            com.unionpay.tsmservice.AppID r8 = new com.unionpay.tsmservice.AppID     // Catch:{ all -> 0x01f7 }
            java.lang.String r6 = r1.b((java.lang.String) r6)     // Catch:{ all -> 0x01f7 }
            java.lang.String r7 = r1.b((java.lang.String) r7)     // Catch:{ all -> 0x01f7 }
            r8.<init>(r6, r7)     // Catch:{ all -> 0x01f7 }
            r4.setAppID(r8)     // Catch:{ all -> 0x01f7 }
        L_0x006a:
            java.util.Map r6 = r18.getParams()     // Catch:{ all -> 0x01f7 }
            java.util.HashMap r6 = (java.util.HashMap) r6     // Catch:{ all -> 0x01f7 }
            r7 = -2
            r8 = 0
            if (r6 != 0) goto L_0x00aa
            com.unionpay.tsmservice.ITsmService r6 = r1.e     // Catch:{ Exception -> 0x00a0 }
            com.unionpay.tsmservice.UPTsmAddon$b r9 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x00a0 }
            int[] r10 = r1.Z     // Catch:{ Exception -> 0x00a0 }
            r10 = r10[r5]     // Catch:{ Exception -> 0x00a0 }
            r9.<init>(r1, r5, r10, r8)     // Catch:{ Exception -> 0x00a0 }
            int r4 = r6.appDelete(r4, r9, r3)     // Catch:{ Exception -> 0x00a0 }
            if (r7 != r4) goto L_0x008b
            int r0 = a(r5, r0, r2, r3)     // Catch:{ all -> 0x01f7 }
            monitor-exit(r17)
            return r0
        L_0x008b:
            if (r4 != 0) goto L_0x009e
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r0 = r1.D     // Catch:{ all -> 0x01f7 }
            int[] r3 = r1.Z     // Catch:{ all -> 0x01f7 }
            r6 = r3[r5]     // Catch:{ all -> 0x01f7 }
            int r7 = r6 + 1
            r3[r5] = r7     // Catch:{ all -> 0x01f7 }
            java.lang.String r3 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x01f7 }
            r0.put(r3, r2)     // Catch:{ all -> 0x01f7 }
        L_0x009e:
            monitor-exit(r17)
            return r4
        L_0x00a0:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x01f7 }
            android.os.RemoteException r0 = new android.os.RemoteException     // Catch:{ all -> 0x01f7 }
            r0.<init>()     // Catch:{ all -> 0x01f7 }
            throw r0     // Catch:{ all -> 0x01f7 }
        L_0x00aa:
            java.util.HashMap r6 = a((java.util.HashMap<java.lang.String, java.lang.String>) r6)     // Catch:{ all -> 0x01f7 }
            java.lang.String r9 = "cardHolderName"
            java.lang.Object r9 = r6.get(r9)     // Catch:{ all -> 0x01f7 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ all -> 0x01f7 }
            java.lang.String r10 = "idType"
            java.lang.Object r10 = r6.get(r10)     // Catch:{ all -> 0x01f7 }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ all -> 0x01f7 }
            java.lang.String r11 = "idNo"
            java.lang.Object r11 = r6.get(r11)     // Catch:{ all -> 0x01f7 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x01f7 }
            java.lang.String r12 = "pan"
            java.lang.Object r12 = r6.get(r12)     // Catch:{ all -> 0x01f7 }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ all -> 0x01f7 }
            java.lang.String r13 = "pin"
            java.lang.Object r13 = r6.get(r13)     // Catch:{ all -> 0x01f7 }
            java.lang.String r13 = (java.lang.String) r13     // Catch:{ all -> 0x01f7 }
            java.lang.String r14 = "expiryDate"
            java.lang.Object r14 = r6.get(r14)     // Catch:{ all -> 0x01f7 }
            java.lang.String r14 = (java.lang.String) r14     // Catch:{ all -> 0x01f7 }
            java.lang.String r15 = "cvn2"
            java.lang.Object r15 = r6.get(r15)     // Catch:{ all -> 0x01f7 }
            java.lang.String r15 = (java.lang.String) r15     // Catch:{ all -> 0x01f7 }
            java.lang.String r7 = "msisdn"
            java.lang.Object r7 = r6.get(r7)     // Catch:{ all -> 0x01f7 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x01f7 }
            java.lang.String r8 = "smsAuthCode"
            java.lang.Object r8 = r6.get(r8)     // Catch:{ all -> 0x01f7 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ all -> 0x01f7 }
            java.lang.String r5 = "ecashBalance"
            java.lang.Object r5 = r6.get(r5)     // Catch:{ all -> 0x01f7 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x01f7 }
            java.lang.String r0 = "cardType"
            java.lang.Object r0 = r6.get(r0)     // Catch:{ all -> 0x01f7 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x01f7 }
            boolean r16 = android.text.TextUtils.isEmpty(r9)     // Catch:{ all -> 0x01f7 }
            if (r16 != 0) goto L_0x0115
            java.lang.String r2 = "cardHolderName"
            java.lang.String r9 = r1.b((java.lang.String) r9)     // Catch:{ all -> 0x01f7 }
            r6.put(r2, r9)     // Catch:{ all -> 0x01f7 }
        L_0x0115:
            boolean r2 = android.text.TextUtils.isEmpty(r10)     // Catch:{ all -> 0x01f7 }
            if (r2 != 0) goto L_0x0124
            java.lang.String r2 = "idType"
            java.lang.String r9 = r1.b((java.lang.String) r10)     // Catch:{ all -> 0x01f7 }
            r6.put(r2, r9)     // Catch:{ all -> 0x01f7 }
        L_0x0124:
            boolean r2 = android.text.TextUtils.isEmpty(r11)     // Catch:{ all -> 0x01f7 }
            if (r2 != 0) goto L_0x0133
            java.lang.String r2 = "idNo"
            java.lang.String r9 = r1.b((java.lang.String) r11)     // Catch:{ all -> 0x01f7 }
            r6.put(r2, r9)     // Catch:{ all -> 0x01f7 }
        L_0x0133:
            boolean r2 = android.text.TextUtils.isEmpty(r12)     // Catch:{ all -> 0x01f7 }
            if (r2 != 0) goto L_0x0142
            java.lang.String r2 = "pan"
            java.lang.String r9 = r1.b((java.lang.String) r12)     // Catch:{ all -> 0x01f7 }
            r6.put(r2, r9)     // Catch:{ all -> 0x01f7 }
        L_0x0142:
            boolean r2 = android.text.TextUtils.isEmpty(r13)     // Catch:{ all -> 0x01f7 }
            if (r2 != 0) goto L_0x014d
            java.lang.String r2 = "pin"
            r6.put(r2, r13)     // Catch:{ all -> 0x01f7 }
        L_0x014d:
            boolean r2 = android.text.TextUtils.isEmpty(r14)     // Catch:{ all -> 0x01f7 }
            if (r2 != 0) goto L_0x015c
            java.lang.String r2 = "expiryDate"
            java.lang.String r9 = r1.b((java.lang.String) r14)     // Catch:{ all -> 0x01f7 }
            r6.put(r2, r9)     // Catch:{ all -> 0x01f7 }
        L_0x015c:
            boolean r2 = android.text.TextUtils.isEmpty(r15)     // Catch:{ all -> 0x01f7 }
            if (r2 != 0) goto L_0x016b
            java.lang.String r2 = "cvn2"
            java.lang.String r9 = r1.b((java.lang.String) r15)     // Catch:{ all -> 0x01f7 }
            r6.put(r2, r9)     // Catch:{ all -> 0x01f7 }
        L_0x016b:
            boolean r2 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x01f7 }
            if (r2 != 0) goto L_0x017a
            java.lang.String r2 = "msisdn"
            java.lang.String r7 = r1.b((java.lang.String) r7)     // Catch:{ all -> 0x01f7 }
            r6.put(r2, r7)     // Catch:{ all -> 0x01f7 }
        L_0x017a:
            boolean r2 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x01f7 }
            if (r2 != 0) goto L_0x0189
            java.lang.String r2 = "smsAuthCode"
            java.lang.String r7 = r1.b((java.lang.String) r8)     // Catch:{ all -> 0x01f7 }
            r6.put(r2, r7)     // Catch:{ all -> 0x01f7 }
        L_0x0189:
            boolean r2 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x01f7 }
            if (r2 != 0) goto L_0x0198
            java.lang.String r2 = "ecashBalance"
            java.lang.String r5 = r1.b((java.lang.String) r5)     // Catch:{ all -> 0x01f7 }
            r6.put(r2, r5)     // Catch:{ all -> 0x01f7 }
        L_0x0198:
            boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x01f7 }
            if (r2 != 0) goto L_0x01a7
            java.lang.String r2 = "cardType"
            java.lang.String r0 = r1.b((java.lang.String) r0)     // Catch:{ all -> 0x01f7 }
            r6.put(r2, r0)     // Catch:{ all -> 0x01f7 }
        L_0x01a7:
            r4.setParams(r6)     // Catch:{ all -> 0x01f7 }
            com.unionpay.tsmservice.ITsmService r0 = r1.e     // Catch:{ Exception -> 0x01e0 }
            com.unionpay.tsmservice.UPTsmAddon$b r2 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x01e0 }
            int[] r5 = r1.Z     // Catch:{ Exception -> 0x01e0 }
            r6 = 17
            r5 = r5[r6]     // Catch:{ Exception -> 0x01e0 }
            r7 = 0
            r2.<init>(r1, r6, r5, r7)     // Catch:{ Exception -> 0x01e0 }
            int r0 = r0.appDelete(r4, r2, r3)     // Catch:{ Exception -> 0x01e0 }
            r2 = -2
            if (r2 != r0) goto L_0x01c9
            r2 = r18
            r4 = r19
            int r0 = a(r6, r2, r4, r3)     // Catch:{ all -> 0x01f7 }
            monitor-exit(r17)
            return r0
        L_0x01c9:
            r4 = r19
            if (r0 != 0) goto L_0x01de
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r1.D     // Catch:{ all -> 0x01f7 }
            int[] r3 = r1.Z     // Catch:{ all -> 0x01f7 }
            r5 = r3[r6]     // Catch:{ all -> 0x01f7 }
            int r7 = r5 + 1
            r3[r6] = r7     // Catch:{ all -> 0x01f7 }
            java.lang.String r3 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x01f7 }
            r2.put(r3, r4)     // Catch:{ all -> 0x01f7 }
        L_0x01de:
            monitor-exit(r17)
            return r0
        L_0x01e0:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x01f7 }
            android.os.RemoteException r0 = new android.os.RemoteException     // Catch:{ all -> 0x01f7 }
            r0.<init>()     // Catch:{ all -> 0x01f7 }
            throw r0     // Catch:{ all -> 0x01f7 }
        L_0x01ea:
            r4 = r2
            r2 = r0
            r0 = 17
            int r0 = a(r0, r2, r4, r3)     // Catch:{ all -> 0x01f7 }
            monitor-exit(r17)
            return r0
        L_0x01f4:
            r0 = -1
            goto L_0x0016
        L_0x01f7:
            r0 = move-exception
            monitor-exit(r17)
            throw r0
        L_0x01fa:
            r0 = -3
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.appDelete(com.unionpay.tsmservice.request.AppDeleteRequestParams, com.unionpay.tsmservice.ITsmCallback, com.unionpay.tsmservice.ITsmProgressCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00a0, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000f, code lost:
        return r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int appDownload(com.unionpay.tsmservice.request.AppDownloadRequestParams r7, com.unionpay.tsmservice.ITsmCallback r8, com.unionpay.tsmservice.ITsmProgressCallback r9) throws android.os.RemoteException {
        /*
            r6 = this;
            monitor-enter(r6)
            if (r7 == 0) goto L_0x00b7
            if (r8 != 0) goto L_0x0007
            goto L_0x00b7
        L_0x0007:
            boolean r0 = r6.c()     // Catch:{ all -> 0x00b4 }
            if (r0 != 0) goto L_0x0010
            r7 = -8
        L_0x000e:
            monitor-exit(r6)
            return r7
        L_0x0010:
            com.unionpay.tsmservice.ITsmService r0 = r6.e     // Catch:{ all -> 0x00b4 }
            if (r0 == 0) goto L_0x00b1
            android.content.Context r0 = r6.c     // Catch:{ all -> 0x00b4 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00b4 }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x00b4 }
            r1 = 16
            if (r0 == 0) goto L_0x00ab
            com.unionpay.tsmservice.request.AppDownloadRequestParams r0 = new com.unionpay.tsmservice.request.AppDownloadRequestParams     // Catch:{ all -> 0x00b4 }
            r0.<init>()     // Catch:{ all -> 0x00b4 }
            java.lang.String r2 = r7.getReserve()     // Catch:{ all -> 0x00b4 }
            com.unionpay.tsmservice.AppID r3 = r7.getAppID()     // Catch:{ all -> 0x00b4 }
            java.lang.String r4 = r7.getAppName()     // Catch:{ all -> 0x00b4 }
            boolean r5 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00b4 }
            if (r5 != 0) goto L_0x0040
            java.lang.String r2 = r6.b((java.lang.String) r2)     // Catch:{ all -> 0x00b4 }
            r0.setReserve(r2)     // Catch:{ all -> 0x00b4 }
        L_0x0040:
            if (r3 == 0) goto L_0x0066
            java.lang.String r2 = r3.getAppAid()     // Catch:{ all -> 0x00b4 }
            java.lang.String r3 = r3.getAppVersion()     // Catch:{ all -> 0x00b4 }
            boolean r5 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00b4 }
            if (r5 != 0) goto L_0x0066
            boolean r5 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x00b4 }
            if (r5 != 0) goto L_0x0066
            com.unionpay.tsmservice.AppID r5 = new com.unionpay.tsmservice.AppID     // Catch:{ all -> 0x00b4 }
            java.lang.String r2 = r6.b((java.lang.String) r2)     // Catch:{ all -> 0x00b4 }
            java.lang.String r3 = r6.b((java.lang.String) r3)     // Catch:{ all -> 0x00b4 }
            r5.<init>(r2, r3)     // Catch:{ all -> 0x00b4 }
            r0.setAppID(r5)     // Catch:{ all -> 0x00b4 }
        L_0x0066:
            boolean r2 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x00b4 }
            if (r2 != 0) goto L_0x0073
            java.lang.String r2 = r6.b((java.lang.String) r4)     // Catch:{ all -> 0x00b4 }
            r0.setAppName(r2)     // Catch:{ all -> 0x00b4 }
        L_0x0073:
            com.unionpay.tsmservice.ITsmService r2 = r6.e     // Catch:{ Exception -> 0x00a1 }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x00a1 }
            int[] r4 = r6.Z     // Catch:{ Exception -> 0x00a1 }
            r4 = r4[r1]     // Catch:{ Exception -> 0x00a1 }
            r5 = 0
            r3.<init>(r6, r1, r4, r5)     // Catch:{ Exception -> 0x00a1 }
            int r0 = r2.appDownload(r0, r3, r9)     // Catch:{ Exception -> 0x00a1 }
            r2 = -2
            if (r2 != r0) goto L_0x008c
            int r7 = a(r1, r7, r8, r9)     // Catch:{ all -> 0x00b4 }
            monitor-exit(r6)
            return r7
        L_0x008c:
            if (r0 != 0) goto L_0x009f
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r7 = r6.C     // Catch:{ all -> 0x00b4 }
            int[] r9 = r6.Z     // Catch:{ all -> 0x00b4 }
            r2 = r9[r1]     // Catch:{ all -> 0x00b4 }
            int r3 = r2 + 1
            r9[r1] = r3     // Catch:{ all -> 0x00b4 }
            java.lang.String r9 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x00b4 }
            r7.put(r9, r8)     // Catch:{ all -> 0x00b4 }
        L_0x009f:
            monitor-exit(r6)
            return r0
        L_0x00a1:
            r7 = move-exception
            r7.printStackTrace()     // Catch:{ all -> 0x00b4 }
            android.os.RemoteException r7 = new android.os.RemoteException     // Catch:{ all -> 0x00b4 }
            r7.<init>()     // Catch:{ all -> 0x00b4 }
            throw r7     // Catch:{ all -> 0x00b4 }
        L_0x00ab:
            int r7 = a(r1, r7, r8, r9)     // Catch:{ all -> 0x00b4 }
            monitor-exit(r6)
            return r7
        L_0x00b1:
            r7 = -1
            goto L_0x000e
        L_0x00b4:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        L_0x00b7:
            r7 = -3
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.appDownload(com.unionpay.tsmservice.request.AppDownloadRequestParams, com.unionpay.tsmservice.ITsmCallback, com.unionpay.tsmservice.ITsmProgressCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x009d, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x009e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00a7, code lost:
        throw new android.os.RemoteException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01f4, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01f5, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:?, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x01fe, code lost:
        throw new android.os.RemoteException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        return r0;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:27:0x0072, B:80:0x01bf] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int appDownloadApply(com.unionpay.tsmservice.request.AppDownloadApplyRequestParams r18, com.unionpay.tsmservice.ITsmCallback r19) throws android.os.RemoteException {
        /*
            r17 = this;
            r1 = r17
            r0 = r18
            r2 = r19
            monitor-enter(r17)
            if (r0 == 0) goto L_0x020f
            if (r2 != 0) goto L_0x000d
            goto L_0x020f
        L_0x000d:
            boolean r3 = r17.c()     // Catch:{ all -> 0x020c }
            if (r3 != 0) goto L_0x0016
            r0 = -8
        L_0x0014:
            monitor-exit(r17)
            return r0
        L_0x0016:
            com.unionpay.tsmservice.ITsmService r3 = r1.e     // Catch:{ all -> 0x020c }
            if (r3 == 0) goto L_0x0209
            android.content.Context r3 = r1.c     // Catch:{ all -> 0x020c }
            java.lang.String r3 = r3.getPackageName()     // Catch:{ all -> 0x020c }
            boolean r3 = a((java.lang.String) r3)     // Catch:{ all -> 0x020c }
            r4 = 15
            if (r3 == 0) goto L_0x01ff
            com.unionpay.tsmservice.request.AppDownloadApplyRequestParams r3 = new com.unionpay.tsmservice.request.AppDownloadApplyRequestParams     // Catch:{ all -> 0x020c }
            r3.<init>()     // Catch:{ all -> 0x020c }
            java.lang.String r5 = r18.getReserve()     // Catch:{ all -> 0x020c }
            com.unionpay.tsmservice.AppID r6 = r18.getAppID()     // Catch:{ all -> 0x020c }
            boolean r7 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x020c }
            if (r7 != 0) goto L_0x0042
            java.lang.String r5 = r1.b((java.lang.String) r5)     // Catch:{ all -> 0x020c }
            r3.setReserve(r5)     // Catch:{ all -> 0x020c }
        L_0x0042:
            if (r6 == 0) goto L_0x0068
            java.lang.String r5 = r6.getAppAid()     // Catch:{ all -> 0x020c }
            java.lang.String r6 = r6.getAppVersion()     // Catch:{ all -> 0x020c }
            boolean r7 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x020c }
            if (r7 != 0) goto L_0x0068
            boolean r7 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x020c }
            if (r7 != 0) goto L_0x0068
            com.unionpay.tsmservice.AppID r7 = new com.unionpay.tsmservice.AppID     // Catch:{ all -> 0x020c }
            java.lang.String r5 = r1.b((java.lang.String) r5)     // Catch:{ all -> 0x020c }
            java.lang.String r6 = r1.b((java.lang.String) r6)     // Catch:{ all -> 0x020c }
            r7.<init>(r5, r6)     // Catch:{ all -> 0x020c }
            r3.setAppID(r7)     // Catch:{ all -> 0x020c }
        L_0x0068:
            java.util.Map r5 = r18.getParams()     // Catch:{ all -> 0x020c }
            java.util.HashMap r5 = (java.util.HashMap) r5     // Catch:{ all -> 0x020c }
            r6 = -2
            r7 = 0
            if (r5 != 0) goto L_0x00a8
            com.unionpay.tsmservice.ITsmService r5 = r1.e     // Catch:{ Exception -> 0x009e }
            com.unionpay.tsmservice.UPTsmAddon$b r8 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x009e }
            int[] r9 = r1.Z     // Catch:{ Exception -> 0x009e }
            r9 = r9[r4]     // Catch:{ Exception -> 0x009e }
            r8.<init>(r1, r4, r9, r7)     // Catch:{ Exception -> 0x009e }
            int r3 = r5.appDownloadApply(r3, r8)     // Catch:{ Exception -> 0x009e }
            if (r6 != r3) goto L_0x0089
            int r0 = a(r4, r0, r2)     // Catch:{ all -> 0x020c }
            monitor-exit(r17)
            return r0
        L_0x0089:
            if (r3 != 0) goto L_0x009c
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r0 = r1.p     // Catch:{ all -> 0x020c }
            int[] r5 = r1.Z     // Catch:{ all -> 0x020c }
            r6 = r5[r4]     // Catch:{ all -> 0x020c }
            int r7 = r6 + 1
            r5[r4] = r7     // Catch:{ all -> 0x020c }
            java.lang.String r4 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x020c }
            r0.put(r4, r2)     // Catch:{ all -> 0x020c }
        L_0x009c:
            monitor-exit(r17)
            return r3
        L_0x009e:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x020c }
            android.os.RemoteException r0 = new android.os.RemoteException     // Catch:{ all -> 0x020c }
            r0.<init>()     // Catch:{ all -> 0x020c }
            throw r0     // Catch:{ all -> 0x020c }
        L_0x00a8:
            java.util.HashMap r5 = a((java.util.HashMap<java.lang.String, java.lang.String>) r5)     // Catch:{ all -> 0x020c }
            java.lang.String r8 = "accountLimit"
            java.lang.Object r8 = r5.get(r8)     // Catch:{ all -> 0x020c }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ all -> 0x020c }
            java.lang.String r9 = "accountType"
            java.lang.Object r9 = r5.get(r9)     // Catch:{ all -> 0x020c }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ all -> 0x020c }
            java.lang.String r10 = "cardHolderName"
            java.lang.Object r10 = r5.get(r10)     // Catch:{ all -> 0x020c }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ all -> 0x020c }
            java.lang.String r11 = "idType"
            java.lang.Object r11 = r5.get(r11)     // Catch:{ all -> 0x020c }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x020c }
            java.lang.String r12 = "idNo"
            java.lang.Object r12 = r5.get(r12)     // Catch:{ all -> 0x020c }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ all -> 0x020c }
            java.lang.String r13 = "pan"
            java.lang.Object r13 = r5.get(r13)     // Catch:{ all -> 0x020c }
            java.lang.String r13 = (java.lang.String) r13     // Catch:{ all -> 0x020c }
            java.lang.String r14 = "pin"
            java.lang.Object r14 = r5.get(r14)     // Catch:{ all -> 0x020c }
            java.lang.String r14 = (java.lang.String) r14     // Catch:{ all -> 0x020c }
            java.lang.String r15 = "expiryDate"
            java.lang.Object r15 = r5.get(r15)     // Catch:{ all -> 0x020c }
            java.lang.String r15 = (java.lang.String) r15     // Catch:{ all -> 0x020c }
            java.lang.String r6 = "cvn2"
            java.lang.Object r6 = r5.get(r6)     // Catch:{ all -> 0x020c }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x020c }
            java.lang.String r7 = "msisdn"
            java.lang.Object r7 = r5.get(r7)     // Catch:{ all -> 0x020c }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x020c }
            java.lang.String r4 = "smsAuthCode"
            java.lang.Object r4 = r5.get(r4)     // Catch:{ all -> 0x020c }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ all -> 0x020c }
            java.lang.String r0 = "cardType"
            java.lang.Object r0 = r5.get(r0)     // Catch:{ all -> 0x020c }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x020c }
            boolean r16 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x020c }
            if (r16 != 0) goto L_0x011b
            java.lang.String r2 = "accountLimit"
            java.lang.String r8 = r1.b((java.lang.String) r8)     // Catch:{ all -> 0x020c }
            r5.put(r2, r8)     // Catch:{ all -> 0x020c }
        L_0x011b:
            boolean r2 = android.text.TextUtils.isEmpty(r9)     // Catch:{ all -> 0x020c }
            if (r2 != 0) goto L_0x012a
            java.lang.String r2 = "accountType"
            java.lang.String r8 = r1.b((java.lang.String) r9)     // Catch:{ all -> 0x020c }
            r5.put(r2, r8)     // Catch:{ all -> 0x020c }
        L_0x012a:
            boolean r2 = android.text.TextUtils.isEmpty(r10)     // Catch:{ all -> 0x020c }
            if (r2 != 0) goto L_0x0139
            java.lang.String r2 = "cardHolderName"
            java.lang.String r8 = r1.b((java.lang.String) r10)     // Catch:{ all -> 0x020c }
            r5.put(r2, r8)     // Catch:{ all -> 0x020c }
        L_0x0139:
            boolean r2 = android.text.TextUtils.isEmpty(r11)     // Catch:{ all -> 0x020c }
            if (r2 != 0) goto L_0x0148
            java.lang.String r2 = "idType"
            java.lang.String r8 = r1.b((java.lang.String) r11)     // Catch:{ all -> 0x020c }
            r5.put(r2, r8)     // Catch:{ all -> 0x020c }
        L_0x0148:
            boolean r2 = android.text.TextUtils.isEmpty(r12)     // Catch:{ all -> 0x020c }
            if (r2 != 0) goto L_0x0157
            java.lang.String r2 = "idNo"
            java.lang.String r8 = r1.b((java.lang.String) r12)     // Catch:{ all -> 0x020c }
            r5.put(r2, r8)     // Catch:{ all -> 0x020c }
        L_0x0157:
            boolean r2 = android.text.TextUtils.isEmpty(r13)     // Catch:{ all -> 0x020c }
            if (r2 != 0) goto L_0x0166
            java.lang.String r2 = "pan"
            java.lang.String r8 = r1.b((java.lang.String) r13)     // Catch:{ all -> 0x020c }
            r5.put(r2, r8)     // Catch:{ all -> 0x020c }
        L_0x0166:
            boolean r2 = android.text.TextUtils.isEmpty(r14)     // Catch:{ all -> 0x020c }
            if (r2 != 0) goto L_0x0171
            java.lang.String r2 = "pin"
            r5.put(r2, r14)     // Catch:{ all -> 0x020c }
        L_0x0171:
            boolean r2 = android.text.TextUtils.isEmpty(r15)     // Catch:{ all -> 0x020c }
            if (r2 != 0) goto L_0x0180
            java.lang.String r2 = "expiryDate"
            java.lang.String r8 = r1.b((java.lang.String) r15)     // Catch:{ all -> 0x020c }
            r5.put(r2, r8)     // Catch:{ all -> 0x020c }
        L_0x0180:
            boolean r2 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x020c }
            if (r2 != 0) goto L_0x018f
            java.lang.String r2 = "cvn2"
            java.lang.String r6 = r1.b((java.lang.String) r6)     // Catch:{ all -> 0x020c }
            r5.put(r2, r6)     // Catch:{ all -> 0x020c }
        L_0x018f:
            boolean r2 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x020c }
            if (r2 != 0) goto L_0x019e
            java.lang.String r2 = "msisdn"
            java.lang.String r6 = r1.b((java.lang.String) r7)     // Catch:{ all -> 0x020c }
            r5.put(r2, r6)     // Catch:{ all -> 0x020c }
        L_0x019e:
            boolean r2 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x020c }
            if (r2 != 0) goto L_0x01ad
            java.lang.String r2 = "smsAuthCode"
            java.lang.String r4 = r1.b((java.lang.String) r4)     // Catch:{ all -> 0x020c }
            r5.put(r2, r4)     // Catch:{ all -> 0x020c }
        L_0x01ad:
            boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x020c }
            if (r2 != 0) goto L_0x01bc
            java.lang.String r2 = "cardType"
            java.lang.String r0 = r1.b((java.lang.String) r0)     // Catch:{ all -> 0x020c }
            r5.put(r2, r0)     // Catch:{ all -> 0x020c }
        L_0x01bc:
            r3.setParams(r5)     // Catch:{ all -> 0x020c }
            com.unionpay.tsmservice.ITsmService r0 = r1.e     // Catch:{ Exception -> 0x01f5 }
            com.unionpay.tsmservice.UPTsmAddon$b r2 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x01f5 }
            int[] r4 = r1.Z     // Catch:{ Exception -> 0x01f5 }
            r5 = 15
            r4 = r4[r5]     // Catch:{ Exception -> 0x01f5 }
            r6 = 0
            r2.<init>(r1, r5, r4, r6)     // Catch:{ Exception -> 0x01f5 }
            int r0 = r0.appDownloadApply(r3, r2)     // Catch:{ Exception -> 0x01f5 }
            r2 = -2
            if (r2 != r0) goto L_0x01de
            r2 = r18
            r3 = r19
            int r0 = a(r5, r2, r3)     // Catch:{ all -> 0x020c }
            monitor-exit(r17)
            return r0
        L_0x01de:
            r3 = r19
            if (r0 != 0) goto L_0x01f3
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r1.p     // Catch:{ all -> 0x020c }
            int[] r4 = r1.Z     // Catch:{ all -> 0x020c }
            r6 = r4[r5]     // Catch:{ all -> 0x020c }
            int r7 = r6 + 1
            r4[r5] = r7     // Catch:{ all -> 0x020c }
            java.lang.String r4 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x020c }
            r2.put(r4, r3)     // Catch:{ all -> 0x020c }
        L_0x01f3:
            monitor-exit(r17)
            return r0
        L_0x01f5:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x020c }
            android.os.RemoteException r0 = new android.os.RemoteException     // Catch:{ all -> 0x020c }
            r0.<init>()     // Catch:{ all -> 0x020c }
            throw r0     // Catch:{ all -> 0x020c }
        L_0x01ff:
            r3 = r2
            r2 = r0
            r0 = 15
            int r0 = a(r0, r2, r3)     // Catch:{ all -> 0x020c }
            monitor-exit(r17)
            return r0
        L_0x0209:
            r0 = -1
            goto L_0x0014
        L_0x020c:
            r0 = move-exception
            monitor-exit(r17)
            throw r0
        L_0x020f:
            r0 = -3
            goto L_0x0014
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.appDownloadApply(com.unionpay.tsmservice.request.AppDownloadApplyRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000f, code lost:
        return r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int appLock(com.unionpay.tsmservice.request.AppLockRequestParams r6, com.unionpay.tsmservice.ITsmCallback r7) throws android.os.RemoteException {
        /*
            r5 = this;
            monitor-enter(r5)
            if (r6 == 0) goto L_0x0088
            if (r7 != 0) goto L_0x0007
            goto L_0x0088
        L_0x0007:
            boolean r0 = r5.c()     // Catch:{ all -> 0x0085 }
            if (r0 != 0) goto L_0x0010
            r6 = -8
        L_0x000e:
            monitor-exit(r5)
            return r6
        L_0x0010:
            com.unionpay.tsmservice.ITsmService r0 = r5.e     // Catch:{ all -> 0x0085 }
            if (r0 == 0) goto L_0x0083
            android.content.Context r0 = r5.c     // Catch:{ all -> 0x0085 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x0085 }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x0085 }
            r1 = 26
            if (r0 == 0) goto L_0x007d
            com.unionpay.tsmservice.request.AppLockRequestParams r0 = new com.unionpay.tsmservice.request.AppLockRequestParams     // Catch:{ all -> 0x0085 }
            r0.<init>()     // Catch:{ all -> 0x0085 }
            java.lang.String r2 = r6.getReserve()     // Catch:{ all -> 0x0085 }
            com.unionpay.tsmservice.AppID r3 = r6.getAppID()     // Catch:{ all -> 0x0085 }
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x0085 }
            if (r4 != 0) goto L_0x003c
            java.lang.String r2 = r5.b((java.lang.String) r2)     // Catch:{ all -> 0x0085 }
            r0.setReserve(r2)     // Catch:{ all -> 0x0085 }
        L_0x003c:
            if (r3 == 0) goto L_0x0062
            java.lang.String r2 = r3.getAppAid()     // Catch:{ all -> 0x0085 }
            java.lang.String r3 = r3.getAppVersion()     // Catch:{ all -> 0x0085 }
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x0085 }
            if (r4 != 0) goto L_0x0062
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x0085 }
            if (r4 != 0) goto L_0x0062
            com.unionpay.tsmservice.AppID r4 = new com.unionpay.tsmservice.AppID     // Catch:{ all -> 0x0085 }
            java.lang.String r2 = r5.b((java.lang.String) r2)     // Catch:{ all -> 0x0085 }
            java.lang.String r3 = r5.b((java.lang.String) r3)     // Catch:{ all -> 0x0085 }
            r4.<init>(r2, r3)     // Catch:{ all -> 0x0085 }
            r0.setAppID(r4)     // Catch:{ all -> 0x0085 }
        L_0x0062:
            com.unionpay.tsmservice.ITsmService r2 = r5.e     // Catch:{ Exception -> 0x0073 }
            int r0 = r2.appLock(r0, r7)     // Catch:{ Exception -> 0x0073 }
            r2 = -2
            if (r2 != r0) goto L_0x0071
            int r6 = a(r1, r6, r7)     // Catch:{ all -> 0x0085 }
            monitor-exit(r5)
            return r6
        L_0x0071:
            monitor-exit(r5)
            return r0
        L_0x0073:
            r6 = move-exception
            r6.printStackTrace()     // Catch:{ all -> 0x0085 }
            android.os.RemoteException r6 = new android.os.RemoteException     // Catch:{ all -> 0x0085 }
            r6.<init>()     // Catch:{ all -> 0x0085 }
            throw r6     // Catch:{ all -> 0x0085 }
        L_0x007d:
            int r6 = a(r1, r6, r7)     // Catch:{ all -> 0x0085 }
            monitor-exit(r5)
            return r6
        L_0x0083:
            r6 = -1
            goto L_0x000e
        L_0x0085:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        L_0x0088:
            r6 = -3
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.appLock(com.unionpay.tsmservice.request.AppLockRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000f, code lost:
        return r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int appUnlock(com.unionpay.tsmservice.request.AppUnlockRequestParams r6, com.unionpay.tsmservice.ITsmCallback r7) throws android.os.RemoteException {
        /*
            r5 = this;
            monitor-enter(r5)
            if (r6 == 0) goto L_0x0088
            if (r7 != 0) goto L_0x0007
            goto L_0x0088
        L_0x0007:
            boolean r0 = r5.c()     // Catch:{ all -> 0x0085 }
            if (r0 != 0) goto L_0x0010
            r6 = -8
        L_0x000e:
            monitor-exit(r5)
            return r6
        L_0x0010:
            com.unionpay.tsmservice.ITsmService r0 = r5.e     // Catch:{ all -> 0x0085 }
            if (r0 == 0) goto L_0x0083
            android.content.Context r0 = r5.c     // Catch:{ all -> 0x0085 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x0085 }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x0085 }
            r1 = 27
            if (r0 == 0) goto L_0x007d
            com.unionpay.tsmservice.request.AppUnlockRequestParams r0 = new com.unionpay.tsmservice.request.AppUnlockRequestParams     // Catch:{ all -> 0x0085 }
            r0.<init>()     // Catch:{ all -> 0x0085 }
            java.lang.String r2 = r6.getReserve()     // Catch:{ all -> 0x0085 }
            com.unionpay.tsmservice.AppID r3 = r6.getAppID()     // Catch:{ all -> 0x0085 }
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x0085 }
            if (r4 != 0) goto L_0x003c
            java.lang.String r2 = r5.b((java.lang.String) r2)     // Catch:{ all -> 0x0085 }
            r0.setReserve(r2)     // Catch:{ all -> 0x0085 }
        L_0x003c:
            if (r3 == 0) goto L_0x0062
            java.lang.String r2 = r3.getAppAid()     // Catch:{ all -> 0x0085 }
            java.lang.String r3 = r3.getAppVersion()     // Catch:{ all -> 0x0085 }
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x0085 }
            if (r4 != 0) goto L_0x0062
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x0085 }
            if (r4 != 0) goto L_0x0062
            com.unionpay.tsmservice.AppID r4 = new com.unionpay.tsmservice.AppID     // Catch:{ all -> 0x0085 }
            java.lang.String r2 = r5.b((java.lang.String) r2)     // Catch:{ all -> 0x0085 }
            java.lang.String r3 = r5.b((java.lang.String) r3)     // Catch:{ all -> 0x0085 }
            r4.<init>(r2, r3)     // Catch:{ all -> 0x0085 }
            r0.setAppID(r4)     // Catch:{ all -> 0x0085 }
        L_0x0062:
            com.unionpay.tsmservice.ITsmService r2 = r5.e     // Catch:{ Exception -> 0x0073 }
            int r0 = r2.appUnlock(r0, r7)     // Catch:{ Exception -> 0x0073 }
            r2 = -2
            if (r2 != r0) goto L_0x0071
            int r6 = a(r1, r6, r7)     // Catch:{ all -> 0x0085 }
            monitor-exit(r5)
            return r6
        L_0x0071:
            monitor-exit(r5)
            return r0
        L_0x0073:
            r6 = move-exception
            r6.printStackTrace()     // Catch:{ all -> 0x0085 }
            android.os.RemoteException r6 = new android.os.RemoteException     // Catch:{ all -> 0x0085 }
            r6.<init>()     // Catch:{ all -> 0x0085 }
            throw r6     // Catch:{ all -> 0x0085 }
        L_0x007d:
            int r6 = a(r1, r6, r7)     // Catch:{ all -> 0x0085 }
            monitor-exit(r5)
            return r6
        L_0x0083:
            r6 = -1
            goto L_0x000e
        L_0x0085:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        L_0x0088:
            r6 = -3
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.appUnlock(com.unionpay.tsmservice.request.AppUnlockRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    public boolean bind() {
        try {
            if (this.d == null) {
                this.d = new ServiceConnection() {
                    public final synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        boolean unused = UPTsmAddon.this.f = true;
                        ITsmService unused2 = UPTsmAddon.this.e = ITsmService.Stub.asInterface(iBinder);
                        UPTsmAddon.this.ab.sendEmptyMessage(0);
                    }

                    public final synchronized void onServiceDisconnected(ComponentName componentName) {
                        boolean unused = UPTsmAddon.this.f = false;
                        ITsmService unused2 = UPTsmAddon.this.e = null;
                        UPTsmAddon.this.ab.sendEmptyMessage(1);
                    }
                };
            }
            if (this.f) {
                return true;
            }
            Intent intent = new Intent("com.unionpay.tsmservice.UPTsmService");
            intent.setPackage("com.unionpay.tsmservice");
            return this.c.bindService(intent, this.d, 1);
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0005, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int cardListStatusChanged(com.unionpay.tsmservice.request.CardListStatusChangedRequestParams r8, com.unionpay.tsmservice.ITsmCallback r9) throws android.os.RemoteException {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r9 != 0) goto L_0x0006
            r8 = -3
        L_0x0004:
            monitor-exit(r7)
            return r8
        L_0x0006:
            java.lang.String r0 = "01.00.14"
            boolean r0 = r7.d((java.lang.String) r0)     // Catch:{ all -> 0x00a3 }
            r1 = -8
            if (r0 != 0) goto L_0x0011
            monitor-exit(r7)
            return r1
        L_0x0011:
            boolean r0 = r7.c()     // Catch:{ all -> 0x00a3 }
            if (r0 != 0) goto L_0x0019
            monitor-exit(r7)
            return r1
        L_0x0019:
            com.unionpay.tsmservice.ITsmService r0 = r7.e     // Catch:{ all -> 0x00a3 }
            if (r0 == 0) goto L_0x00a0
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x00a3 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00a3 }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x00a3 }
            r1 = 35
            if (r0 == 0) goto L_0x009a
            com.unionpay.tsmservice.request.CardListStatusChangedRequestParams r0 = new com.unionpay.tsmservice.request.CardListStatusChangedRequestParams     // Catch:{ all -> 0x00a3 }
            r0.<init>()     // Catch:{ all -> 0x00a3 }
            java.lang.String r2 = ""
            if (r8 == 0) goto L_0x0038
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x00a3 }
        L_0x0038:
            boolean r3 = r7.h     // Catch:{ all -> 0x00a3 }
            if (r3 == 0) goto L_0x0044
            java.lang.String r2 = f(r2)     // Catch:{ all -> 0x00a3 }
            java.lang.String r2 = r7.g(r2)     // Catch:{ all -> 0x00a3 }
        L_0x0044:
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00a3 }
            if (r3 != 0) goto L_0x0051
            java.lang.String r2 = r7.b((java.lang.String) r2)     // Catch:{ all -> 0x00a3 }
            r0.setReserve(r2)     // Catch:{ all -> 0x00a3 }
        L_0x0051:
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.M     // Catch:{ all -> 0x00a3 }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00a3 }
            r3 = r3[r1]     // Catch:{ all -> 0x00a3 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00a3 }
            r2.put(r3, r9)     // Catch:{ all -> 0x00a3 }
            com.unionpay.tsmservice.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x0090 }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x0090 }
            int[] r4 = r7.Z     // Catch:{ Exception -> 0x0090 }
            r5 = r4[r1]     // Catch:{ Exception -> 0x0090 }
            int r6 = r5 + 1
            r4[r1] = r6     // Catch:{ Exception -> 0x0090 }
            r4 = 0
            r3.<init>(r7, r1, r5, r4)     // Catch:{ Exception -> 0x0090 }
            int r0 = r2.cardListStatusChanged(r0, r3)     // Catch:{ Exception -> 0x0090 }
            if (r0 == 0) goto L_0x0085
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.M     // Catch:{ all -> 0x00a3 }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00a3 }
            r4 = r3[r1]     // Catch:{ all -> 0x00a3 }
            int r4 = r4 + -1
            r3[r1] = r4     // Catch:{ all -> 0x00a3 }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x00a3 }
            r2.remove(r3)     // Catch:{ all -> 0x00a3 }
        L_0x0085:
            r2 = -2
            if (r2 != r0) goto L_0x008e
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x00a3 }
            monitor-exit(r7)
            return r8
        L_0x008e:
            monitor-exit(r7)
            return r0
        L_0x0090:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x00a3 }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x00a3 }
            r8.<init>()     // Catch:{ all -> 0x00a3 }
            throw r8     // Catch:{ all -> 0x00a3 }
        L_0x009a:
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x00a3 }
            monitor-exit(r7)
            return r8
        L_0x00a0:
            r8 = -1
            goto L_0x0004
        L_0x00a3:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.cardListStatusChanged(com.unionpay.tsmservice.request.CardListStatusChangedRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000f, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int checkSSamsungPay(com.unionpay.tsmservice.request.CheckSSamsungPayRequestParams r8, com.unionpay.tsmservice.ITsmCallback r9) throws android.os.RemoteException {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r8 == 0) goto L_0x0099
            if (r9 != 0) goto L_0x0007
            goto L_0x0099
        L_0x0007:
            boolean r0 = r7.c()     // Catch:{ all -> 0x0096 }
            if (r0 != 0) goto L_0x0010
            r8 = -8
        L_0x000e:
            monitor-exit(r7)
            return r8
        L_0x0010:
            com.unionpay.tsmservice.ITsmService r0 = r7.e     // Catch:{ all -> 0x0096 }
            if (r0 == 0) goto L_0x0093
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x0096 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x0096 }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x0096 }
            r1 = 29
            if (r0 == 0) goto L_0x008d
            com.unionpay.tsmservice.request.CheckSSamsungPayRequestParams r0 = new com.unionpay.tsmservice.request.CheckSSamsungPayRequestParams     // Catch:{ all -> 0x0096 }
            r0.<init>()     // Catch:{ all -> 0x0096 }
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x0096 }
            boolean r3 = r7.h     // Catch:{ all -> 0x0096 }
            if (r3 == 0) goto L_0x0037
            java.lang.String r2 = f(r2)     // Catch:{ all -> 0x0096 }
            java.lang.String r2 = r7.g(r2)     // Catch:{ all -> 0x0096 }
        L_0x0037:
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x0096 }
            if (r3 != 0) goto L_0x0044
            java.lang.String r2 = r7.b((java.lang.String) r2)     // Catch:{ all -> 0x0096 }
            r0.setReserve(r2)     // Catch:{ all -> 0x0096 }
        L_0x0044:
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.J     // Catch:{ all -> 0x0096 }
            int[] r3 = r7.Z     // Catch:{ all -> 0x0096 }
            r3 = r3[r1]     // Catch:{ all -> 0x0096 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x0096 }
            r2.put(r3, r9)     // Catch:{ all -> 0x0096 }
            com.unionpay.tsmservice.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x0083 }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x0083 }
            int[] r4 = r7.Z     // Catch:{ Exception -> 0x0083 }
            r5 = r4[r1]     // Catch:{ Exception -> 0x0083 }
            int r6 = r5 + 1
            r4[r1] = r6     // Catch:{ Exception -> 0x0083 }
            r4 = 0
            r3.<init>(r7, r1, r5, r4)     // Catch:{ Exception -> 0x0083 }
            int r0 = r2.checkSSamsungPay(r0, r3)     // Catch:{ Exception -> 0x0083 }
            if (r0 == 0) goto L_0x0078
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.J     // Catch:{ all -> 0x0096 }
            int[] r3 = r7.Z     // Catch:{ all -> 0x0096 }
            r4 = r3[r1]     // Catch:{ all -> 0x0096 }
            int r4 = r4 + -1
            r3[r1] = r4     // Catch:{ all -> 0x0096 }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x0096 }
            r2.remove(r3)     // Catch:{ all -> 0x0096 }
        L_0x0078:
            r2 = -2
            if (r2 != r0) goto L_0x0081
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x0096 }
            monitor-exit(r7)
            return r8
        L_0x0081:
            monitor-exit(r7)
            return r0
        L_0x0083:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x0096 }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x0096 }
            r8.<init>()     // Catch:{ all -> 0x0096 }
            throw r8     // Catch:{ all -> 0x0096 }
        L_0x008d:
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x0096 }
            monitor-exit(r7)
            return r8
        L_0x0093:
            r8 = -1
            goto L_0x000e
        L_0x0096:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        L_0x0099:
            r8 = -3
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.checkSSamsungPay(com.unionpay.tsmservice.request.CheckSSamsungPayRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0013, code lost:
        return r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0056, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r6.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005f, code lost:
        throw new android.os.RemoteException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0071, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        r6.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x007a, code lost:
        throw new android.os.RemoteException();
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:24:0x004f, B:30:0x0060] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int clearEncryptData(int r6) throws android.os.RemoteException {
        /*
            r5 = this;
            monitor-enter(r5)
            r0 = 2000(0x7d0, float:2.803E-42)
            if (r6 < r0) goto L_0x0086
            r0 = 2001(0x7d1, float:2.804E-42)
            if (r6 <= r0) goto L_0x000b
            goto L_0x0086
        L_0x000b:
            boolean r0 = r5.c()     // Catch:{ all -> 0x0083 }
            if (r0 != 0) goto L_0x0014
            r6 = -8
        L_0x0012:
            monitor-exit(r5)
            return r6
        L_0x0014:
            com.unionpay.tsmservice.ITsmService r0 = r5.e     // Catch:{ all -> 0x0083 }
            if (r0 == 0) goto L_0x0081
            android.content.Context r0 = r5.c     // Catch:{ all -> 0x0083 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x0083 }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x0083 }
            r1 = 33
            r2 = 0
            if (r0 == 0) goto L_0x007b
            java.lang.String r0 = "01.00.24"
            boolean r0 = r5.d((java.lang.String) r0)     // Catch:{ all -> 0x0083 }
            if (r0 == 0) goto L_0x0060
            com.unionpay.tsmservice.request.ClearEncryptDataRequestParams r0 = new com.unionpay.tsmservice.request.ClearEncryptDataRequestParams     // Catch:{ all -> 0x0083 }
            r0.<init>()     // Catch:{ all -> 0x0083 }
            java.lang.String r3 = ""
            boolean r4 = r5.h     // Catch:{ all -> 0x0083 }
            if (r4 == 0) goto L_0x0042
            java.lang.String r3 = f(r3)     // Catch:{ all -> 0x0083 }
            java.lang.String r3 = r5.g(r3)     // Catch:{ all -> 0x0083 }
        L_0x0042:
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x0083 }
            if (r4 != 0) goto L_0x004f
            java.lang.String r3 = r5.b((java.lang.String) r3)     // Catch:{ all -> 0x0083 }
            r0.setReserve(r3)     // Catch:{ all -> 0x0083 }
        L_0x004f:
            com.unionpay.tsmservice.ITsmService r3 = r5.e     // Catch:{ Exception -> 0x0056 }
            int r0 = r3.clearKeyboardEncryptData(r0, r6)     // Catch:{ Exception -> 0x0056 }
            goto L_0x0066
        L_0x0056:
            r6 = move-exception
            r6.printStackTrace()     // Catch:{ all -> 0x0083 }
            android.os.RemoteException r6 = new android.os.RemoteException     // Catch:{ all -> 0x0083 }
            r6.<init>()     // Catch:{ all -> 0x0083 }
            throw r6     // Catch:{ all -> 0x0083 }
        L_0x0060:
            com.unionpay.tsmservice.ITsmService r0 = r5.e     // Catch:{ Exception -> 0x0071 }
            int r0 = r0.clearEncryptData(r6)     // Catch:{ Exception -> 0x0071 }
        L_0x0066:
            r3 = -2
            if (r3 != r0) goto L_0x006f
            int r6 = a((int) r1, (com.unionpay.tsmservice.request.SafetyKeyboardRequestParams) r2, (int) r6, (com.unionpay.tsmservice.OnSafetyKeyboardCallback) r2, (android.content.Context) r2)     // Catch:{ all -> 0x0083 }
            monitor-exit(r5)
            return r6
        L_0x006f:
            monitor-exit(r5)
            return r0
        L_0x0071:
            r6 = move-exception
            r6.printStackTrace()     // Catch:{ all -> 0x0083 }
            android.os.RemoteException r6 = new android.os.RemoteException     // Catch:{ all -> 0x0083 }
            r6.<init>()     // Catch:{ all -> 0x0083 }
            throw r6     // Catch:{ all -> 0x0083 }
        L_0x007b:
            int r6 = a((int) r1, (com.unionpay.tsmservice.request.SafetyKeyboardRequestParams) r2, (int) r6, (com.unionpay.tsmservice.OnSafetyKeyboardCallback) r2, (android.content.Context) r2)     // Catch:{ all -> 0x0083 }
            monitor-exit(r5)
            return r6
        L_0x0081:
            r6 = -1
            goto L_0x0012
        L_0x0083:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        L_0x0086:
            r6 = -3
            goto L_0x0012
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.clearEncryptData(int):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001c, code lost:
        return r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00ae, code lost:
        return -3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int closeChannel(com.unionpay.tsmservice.request.CloseChannelRequestParams r8, com.unionpay.tsmservice.ITsmCallback r9) throws android.os.RemoteException {
        /*
            r7 = this;
            monitor-enter(r7)
            r0 = -3
            if (r8 == 0) goto L_0x00ad
            if (r9 != 0) goto L_0x0008
            goto L_0x00ad
        L_0x0008:
            java.lang.String r1 = r8.getChannel()     // Catch:{ all -> 0x00aa }
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x00aa }
            if (r2 == 0) goto L_0x0014
            monitor-exit(r7)
            return r0
        L_0x0014:
            boolean r0 = r7.c()     // Catch:{ all -> 0x00aa }
            if (r0 != 0) goto L_0x001d
            r8 = -8
        L_0x001b:
            monitor-exit(r7)
            return r8
        L_0x001d:
            com.unionpay.tsmservice.ITsmService r0 = r7.e     // Catch:{ all -> 0x00aa }
            if (r0 == 0) goto L_0x00a7
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x00aa }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00aa }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x00aa }
            r2 = 21
            if (r0 == 0) goto L_0x00a1
            java.lang.String r0 = r7.b((java.lang.String) r1)     // Catch:{ all -> 0x00aa }
            com.unionpay.tsmservice.request.CloseChannelRequestParams r1 = new com.unionpay.tsmservice.request.CloseChannelRequestParams     // Catch:{ all -> 0x00aa }
            r1.<init>()     // Catch:{ all -> 0x00aa }
            r1.setChannel(r0)     // Catch:{ all -> 0x00aa }
            java.lang.String r0 = r8.getReserve()     // Catch:{ all -> 0x00aa }
            boolean r3 = r7.h     // Catch:{ all -> 0x00aa }
            if (r3 == 0) goto L_0x004b
            java.lang.String r0 = f(r0)     // Catch:{ all -> 0x00aa }
            java.lang.String r0 = r7.g(r0)     // Catch:{ all -> 0x00aa }
        L_0x004b:
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x00aa }
            if (r3 != 0) goto L_0x0058
            java.lang.String r0 = r7.b((java.lang.String) r0)     // Catch:{ all -> 0x00aa }
            r1.setReserve(r0)     // Catch:{ all -> 0x00aa }
        L_0x0058:
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r0 = r7.F     // Catch:{ all -> 0x00aa }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00aa }
            r3 = r3[r2]     // Catch:{ all -> 0x00aa }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00aa }
            r0.put(r3, r9)     // Catch:{ all -> 0x00aa }
            com.unionpay.tsmservice.ITsmService r0 = r7.e     // Catch:{ Exception -> 0x0097 }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x0097 }
            int[] r4 = r7.Z     // Catch:{ Exception -> 0x0097 }
            r5 = r4[r2]     // Catch:{ Exception -> 0x0097 }
            int r6 = r5 + 1
            r4[r2] = r6     // Catch:{ Exception -> 0x0097 }
            r4 = 0
            r3.<init>(r7, r2, r5, r4)     // Catch:{ Exception -> 0x0097 }
            int r0 = r0.closeChannel(r1, r3)     // Catch:{ Exception -> 0x0097 }
            if (r0 == 0) goto L_0x008c
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r1 = r7.F     // Catch:{ all -> 0x00aa }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00aa }
            r4 = r3[r2]     // Catch:{ all -> 0x00aa }
            int r4 = r4 + -1
            r3[r2] = r4     // Catch:{ all -> 0x00aa }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x00aa }
            r1.remove(r3)     // Catch:{ all -> 0x00aa }
        L_0x008c:
            r1 = -2
            if (r1 != r0) goto L_0x0095
            int r8 = a(r2, r8, r9)     // Catch:{ all -> 0x00aa }
            monitor-exit(r7)
            return r8
        L_0x0095:
            monitor-exit(r7)
            return r0
        L_0x0097:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x00aa }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x00aa }
            r8.<init>()     // Catch:{ all -> 0x00aa }
            throw r8     // Catch:{ all -> 0x00aa }
        L_0x00a1:
            int r8 = a(r2, r8, r9)     // Catch:{ all -> 0x00aa }
            monitor-exit(r7)
            return r8
        L_0x00a7:
            r8 = -1
            goto L_0x001b
        L_0x00aa:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        L_0x00ad:
            monitor-exit(r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.closeChannel(com.unionpay.tsmservice.request.CloseChannelRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0005, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int createSSD(com.unionpay.tsmservice.request.UniteRequestParams r8, com.unionpay.tsmservice.ITsmCallback r9) throws android.os.RemoteException {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r9 != 0) goto L_0x0006
            r8 = -3
        L_0x0004:
            monitor-exit(r7)
            return r8
        L_0x0006:
            java.lang.String r0 = "01.00.38"
            boolean r0 = r7.d((java.lang.String) r0)     // Catch:{ all -> 0x00bd }
            r1 = -8
            if (r0 != 0) goto L_0x0011
            monitor-exit(r7)
            return r1
        L_0x0011:
            boolean r0 = r7.c()     // Catch:{ all -> 0x00bd }
            if (r0 != 0) goto L_0x0019
            monitor-exit(r7)
            return r1
        L_0x0019:
            com.unionpay.tsmservice.ITsmService r0 = r7.e     // Catch:{ all -> 0x00bd }
            if (r0 == 0) goto L_0x00ba
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x00bd }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00bd }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x00bd }
            r1 = 46
            if (r0 == 0) goto L_0x00b4
            com.unionpay.tsmservice.request.UniteRequestParams r0 = new com.unionpay.tsmservice.request.UniteRequestParams     // Catch:{ all -> 0x00bd }
            r0.<init>()     // Catch:{ all -> 0x00bd }
            if (r8 != 0) goto L_0x0037
            com.unionpay.tsmservice.request.UniteRequestParams r8 = new com.unionpay.tsmservice.request.UniteRequestParams     // Catch:{ all -> 0x00bd }
            r8.<init>()     // Catch:{ all -> 0x00bd }
        L_0x0037:
            android.os.Bundle r2 = r8.getParams()     // Catch:{ all -> 0x00bd }
            if (r2 == 0) goto L_0x004e
            android.os.Bundle r3 = new android.os.Bundle     // Catch:{ all -> 0x00bd }
            r3.<init>()     // Catch:{ all -> 0x00bd }
            java.lang.String r4 = "encryptData"
            java.lang.String r2 = r7.a((android.os.Bundle) r2)     // Catch:{ all -> 0x00bd }
            r3.putString(r4, r2)     // Catch:{ all -> 0x00bd }
            r0.setParams(r3)     // Catch:{ all -> 0x00bd }
        L_0x004e:
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x00bd }
            boolean r3 = r7.h     // Catch:{ all -> 0x00bd }
            if (r3 == 0) goto L_0x005e
            java.lang.String r2 = f(r2)     // Catch:{ all -> 0x00bd }
            java.lang.String r2 = r7.g(r2)     // Catch:{ all -> 0x00bd }
        L_0x005e:
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00bd }
            if (r3 != 0) goto L_0x006b
            java.lang.String r2 = r7.b((java.lang.String) r2)     // Catch:{ all -> 0x00bd }
            r0.setReserve(r2)     // Catch:{ all -> 0x00bd }
        L_0x006b:
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.X     // Catch:{ all -> 0x00bd }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00bd }
            r3 = r3[r1]     // Catch:{ all -> 0x00bd }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00bd }
            r2.put(r3, r9)     // Catch:{ all -> 0x00bd }
            com.unionpay.tsmservice.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x00aa }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x00aa }
            int[] r4 = r7.Z     // Catch:{ Exception -> 0x00aa }
            r5 = r4[r1]     // Catch:{ Exception -> 0x00aa }
            int r6 = r5 + 1
            r4[r1] = r6     // Catch:{ Exception -> 0x00aa }
            r4 = 0
            r3.<init>(r7, r1, r5, r4)     // Catch:{ Exception -> 0x00aa }
            int r0 = r2.createSSD(r0, r3)     // Catch:{ Exception -> 0x00aa }
            if (r0 == 0) goto L_0x009f
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.X     // Catch:{ all -> 0x00bd }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00bd }
            r4 = r3[r1]     // Catch:{ all -> 0x00bd }
            int r4 = r4 + -1
            r3[r1] = r4     // Catch:{ all -> 0x00bd }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x00bd }
            r2.remove(r3)     // Catch:{ all -> 0x00bd }
        L_0x009f:
            r2 = -2
            if (r2 != r0) goto L_0x00a8
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x00bd }
            monitor-exit(r7)
            return r8
        L_0x00a8:
            monitor-exit(r7)
            return r0
        L_0x00aa:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x00bd }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x00bd }
            r8.<init>()     // Catch:{ all -> 0x00bd }
            throw r8     // Catch:{ all -> 0x00bd }
        L_0x00b4:
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x00bd }
            monitor-exit(r7)
            return r8
        L_0x00ba:
            r8 = -1
            goto L_0x0004
        L_0x00bd:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.createSSD(com.unionpay.tsmservice.request.UniteRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00be, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000f, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int eCashTopUp(com.unionpay.tsmservice.request.ECashTopUpRequestParams r8, com.unionpay.tsmservice.ITsmCallback r9) throws android.os.RemoteException {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r8 == 0) goto L_0x00d5
            if (r9 != 0) goto L_0x0007
            goto L_0x00d5
        L_0x0007:
            boolean r0 = r7.c()     // Catch:{ all -> 0x00d2 }
            if (r0 != 0) goto L_0x0010
            r8 = -8
        L_0x000e:
            monitor-exit(r7)
            return r8
        L_0x0010:
            com.unionpay.tsmservice.ITsmService r0 = r7.e     // Catch:{ all -> 0x00d2 }
            if (r0 == 0) goto L_0x00cf
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x00d2 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00d2 }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x00d2 }
            r1 = 19
            if (r0 == 0) goto L_0x00c9
            com.unionpay.tsmservice.request.ECashTopUpRequestParams r0 = new com.unionpay.tsmservice.request.ECashTopUpRequestParams     // Catch:{ all -> 0x00d2 }
            r0.<init>()     // Catch:{ all -> 0x00d2 }
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x00d2 }
            com.unionpay.tsmservice.AppID r3 = r8.getAppID()     // Catch:{ all -> 0x00d2 }
            java.lang.String r4 = r8.getType()     // Catch:{ all -> 0x00d2 }
            java.lang.String r5 = r8.getAmount()     // Catch:{ all -> 0x00d2 }
            boolean r6 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00d2 }
            if (r6 != 0) goto L_0x0044
            java.lang.String r2 = r7.b((java.lang.String) r2)     // Catch:{ all -> 0x00d2 }
            r0.setReserve(r2)     // Catch:{ all -> 0x00d2 }
        L_0x0044:
            if (r3 == 0) goto L_0x006a
            java.lang.String r2 = r3.getAppAid()     // Catch:{ all -> 0x00d2 }
            java.lang.String r3 = r3.getAppVersion()     // Catch:{ all -> 0x00d2 }
            boolean r6 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00d2 }
            if (r6 != 0) goto L_0x006a
            boolean r6 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x00d2 }
            if (r6 != 0) goto L_0x006a
            com.unionpay.tsmservice.AppID r6 = new com.unionpay.tsmservice.AppID     // Catch:{ all -> 0x00d2 }
            java.lang.String r2 = r7.b((java.lang.String) r2)     // Catch:{ all -> 0x00d2 }
            java.lang.String r3 = r7.b((java.lang.String) r3)     // Catch:{ all -> 0x00d2 }
            r6.<init>(r2, r3)     // Catch:{ all -> 0x00d2 }
            r0.setAppID(r6)     // Catch:{ all -> 0x00d2 }
        L_0x006a:
            java.lang.String r2 = r8.getEncrpytPin()     // Catch:{ all -> 0x00d2 }
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00d2 }
            if (r3 != 0) goto L_0x0077
            r0.setEncrpytPin(r2)     // Catch:{ all -> 0x00d2 }
        L_0x0077:
            boolean r2 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x00d2 }
            if (r2 != 0) goto L_0x0084
            java.lang.String r2 = r7.b((java.lang.String) r4)     // Catch:{ all -> 0x00d2 }
            r0.setType(r2)     // Catch:{ all -> 0x00d2 }
        L_0x0084:
            boolean r2 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x00d2 }
            if (r2 != 0) goto L_0x0091
            java.lang.String r2 = r7.b((java.lang.String) r5)     // Catch:{ all -> 0x00d2 }
            r0.setAmount(r2)     // Catch:{ all -> 0x00d2 }
        L_0x0091:
            com.unionpay.tsmservice.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x00bf }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x00bf }
            int[] r4 = r7.Z     // Catch:{ Exception -> 0x00bf }
            r4 = r4[r1]     // Catch:{ Exception -> 0x00bf }
            r5 = 0
            r3.<init>(r7, r1, r4, r5)     // Catch:{ Exception -> 0x00bf }
            int r0 = r2.eCashTopUp(r0, r3)     // Catch:{ Exception -> 0x00bf }
            r2 = -2
            if (r2 != r0) goto L_0x00aa
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x00d2 }
            monitor-exit(r7)
            return r8
        L_0x00aa:
            if (r0 != 0) goto L_0x00bd
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r8 = r7.s     // Catch:{ all -> 0x00d2 }
            int[] r2 = r7.Z     // Catch:{ all -> 0x00d2 }
            r3 = r2[r1]     // Catch:{ all -> 0x00d2 }
            int r4 = r3 + 1
            r2[r1] = r4     // Catch:{ all -> 0x00d2 }
            java.lang.String r1 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00d2 }
            r8.put(r1, r9)     // Catch:{ all -> 0x00d2 }
        L_0x00bd:
            monitor-exit(r7)
            return r0
        L_0x00bf:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x00d2 }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x00d2 }
            r8.<init>()     // Catch:{ all -> 0x00d2 }
            throw r8     // Catch:{ all -> 0x00d2 }
        L_0x00c9:
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x00d2 }
            monitor-exit(r7)
            return r8
        L_0x00cf:
            r8 = -1
            goto L_0x000e
        L_0x00d2:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        L_0x00d5:
            r8 = -3
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.eCashTopUp(com.unionpay.tsmservice.request.ECashTopUpRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00cc, code lost:
        return -3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0010, code lost:
        return r10;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int encryptData(com.unionpay.tsmservice.request.EncryptDataRequestParams r10, com.unionpay.tsmservice.ITsmCallback r11) throws android.os.RemoteException {
        /*
            r9 = this;
            monitor-enter(r9)
            r0 = -3
            if (r10 == 0) goto L_0x00cb
            if (r11 != 0) goto L_0x0008
            goto L_0x00cb
        L_0x0008:
            boolean r1 = r9.c()     // Catch:{ all -> 0x00c8 }
            if (r1 != 0) goto L_0x0011
            r10 = -8
        L_0x000f:
            monitor-exit(r9)
            return r10
        L_0x0011:
            com.unionpay.tsmservice.ITsmService r1 = r9.e     // Catch:{ all -> 0x00c8 }
            if (r1 == 0) goto L_0x00c5
            android.content.Context r1 = r9.c     // Catch:{ all -> 0x00c8 }
            java.lang.String r1 = r1.getPackageName()     // Catch:{ all -> 0x00c8 }
            boolean r1 = a((java.lang.String) r1)     // Catch:{ all -> 0x00c8 }
            r2 = 23
            if (r1 == 0) goto L_0x00bf
            com.unionpay.tsmservice.request.EncryptDataRequestParams r1 = new com.unionpay.tsmservice.request.EncryptDataRequestParams     // Catch:{ all -> 0x00c8 }
            r1.<init>()     // Catch:{ all -> 0x00c8 }
            java.lang.String r3 = r10.getReserve()     // Catch:{ all -> 0x00c8 }
            boolean r4 = r9.h     // Catch:{ all -> 0x00c8 }
            if (r4 == 0) goto L_0x0038
            java.lang.String r3 = f(r3)     // Catch:{ all -> 0x00c8 }
            java.lang.String r3 = r9.g(r3)     // Catch:{ all -> 0x00c8 }
        L_0x0038:
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x00c8 }
            if (r4 != 0) goto L_0x0045
            java.lang.String r3 = r9.b((java.lang.String) r3)     // Catch:{ all -> 0x00c8 }
            r1.setReserve(r3)     // Catch:{ all -> 0x00c8 }
        L_0x0045:
            java.util.List r3 = r10.getData()     // Catch:{ all -> 0x00c8 }
            java.util.ArrayList r3 = (java.util.ArrayList) r3     // Catch:{ all -> 0x00c8 }
            r4 = 0
            if (r3 == 0) goto L_0x0077
            int r5 = r3.size()     // Catch:{ all -> 0x00c8 }
            if (r5 != 0) goto L_0x0056
            monitor-exit(r9)
            return r0
        L_0x0056:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x00c8 }
            r0.<init>()     // Catch:{ all -> 0x00c8 }
            r6 = 0
        L_0x005c:
            if (r6 >= r5) goto L_0x0074
            java.lang.Object r7 = r3.get(r6)     // Catch:{ all -> 0x00c8 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x00c8 }
            boolean r8 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x00c8 }
            if (r8 != 0) goto L_0x0071
            java.lang.String r7 = r9.b((java.lang.String) r7)     // Catch:{ all -> 0x00c8 }
            r0.add(r7)     // Catch:{ all -> 0x00c8 }
        L_0x0071:
            int r6 = r6 + 1
            goto L_0x005c
        L_0x0074:
            r1.setData(r0)     // Catch:{ all -> 0x00c8 }
        L_0x0077:
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r0 = r9.B     // Catch:{ all -> 0x00c8 }
            int[] r3 = r9.Z     // Catch:{ all -> 0x00c8 }
            r3 = r3[r2]     // Catch:{ all -> 0x00c8 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00c8 }
            r0.put(r3, r11)     // Catch:{ all -> 0x00c8 }
            com.unionpay.tsmservice.ITsmService r0 = r9.e     // Catch:{ Exception -> 0x00b5 }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x00b5 }
            int[] r5 = r9.Z     // Catch:{ Exception -> 0x00b5 }
            r6 = r5[r2]     // Catch:{ Exception -> 0x00b5 }
            int r7 = r6 + 1
            r5[r2] = r7     // Catch:{ Exception -> 0x00b5 }
            r3.<init>(r9, r2, r6, r4)     // Catch:{ Exception -> 0x00b5 }
            int r0 = r0.encryptData(r1, r3)     // Catch:{ Exception -> 0x00b5 }
            if (r0 == 0) goto L_0x00aa
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r1 = r9.B     // Catch:{ all -> 0x00c8 }
            int[] r3 = r9.Z     // Catch:{ all -> 0x00c8 }
            r4 = r3[r2]     // Catch:{ all -> 0x00c8 }
            int r4 = r4 + -1
            r3[r2] = r4     // Catch:{ all -> 0x00c8 }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x00c8 }
            r1.remove(r3)     // Catch:{ all -> 0x00c8 }
        L_0x00aa:
            r1 = -2
            if (r1 != r0) goto L_0x00b3
            int r10 = a(r2, r10, r11)     // Catch:{ all -> 0x00c8 }
            monitor-exit(r9)
            return r10
        L_0x00b3:
            monitor-exit(r9)
            return r0
        L_0x00b5:
            r10 = move-exception
            r10.printStackTrace()     // Catch:{ all -> 0x00c8 }
            android.os.RemoteException r10 = new android.os.RemoteException     // Catch:{ all -> 0x00c8 }
            r10.<init>()     // Catch:{ all -> 0x00c8 }
            throw r10     // Catch:{ all -> 0x00c8 }
        L_0x00bf:
            int r10 = a(r2, r10, r11)     // Catch:{ all -> 0x00c8 }
            monitor-exit(r9)
            return r10
        L_0x00c5:
            r10 = -1
            goto L_0x000f
        L_0x00c8:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        L_0x00cb:
            monitor-exit(r9)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.encryptData(com.unionpay.tsmservice.request.EncryptDataRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    public int exchangeKey(String str, String[] strArr) throws RemoteException {
        if (TextUtils.isEmpty(str) || strArr == null || strArr.length == 0) {
            return -3;
        }
        if (!c()) {
            return -8;
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

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000f, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int executeCmd(com.unionpay.tsmservice.request.ExecuteCmdRequestParams r8, com.unionpay.tsmservice.ITsmCallback r9, com.unionpay.tsmservice.ITsmProgressCallback r10) throws android.os.RemoteException {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r8 == 0) goto L_0x00bb
            if (r9 != 0) goto L_0x0007
            goto L_0x00bb
        L_0x0007:
            boolean r0 = r7.c()     // Catch:{ all -> 0x00b8 }
            if (r0 != 0) goto L_0x0010
            r8 = -8
        L_0x000e:
            monitor-exit(r7)
            return r8
        L_0x0010:
            com.unionpay.tsmservice.ITsmService r0 = r7.e     // Catch:{ all -> 0x00b8 }
            if (r0 == 0) goto L_0x00b5
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x00b8 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00b8 }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x00b8 }
            r1 = 25
            if (r0 == 0) goto L_0x00af
            com.unionpay.tsmservice.request.ExecuteCmdRequestParams r0 = new com.unionpay.tsmservice.request.ExecuteCmdRequestParams     // Catch:{ all -> 0x00b8 }
            r0.<init>()     // Catch:{ all -> 0x00b8 }
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x00b8 }
            java.lang.String r3 = r8.getSsid()     // Catch:{ all -> 0x00b8 }
            java.lang.String r4 = r8.getSign()     // Catch:{ all -> 0x00b8 }
            boolean r5 = r7.h     // Catch:{ all -> 0x00b8 }
            if (r5 == 0) goto L_0x003f
            java.lang.String r2 = f(r2)     // Catch:{ all -> 0x00b8 }
            java.lang.String r2 = r7.g(r2)     // Catch:{ all -> 0x00b8 }
        L_0x003f:
            boolean r5 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00b8 }
            if (r5 != 0) goto L_0x004c
            java.lang.String r2 = r7.b((java.lang.String) r2)     // Catch:{ all -> 0x00b8 }
            r0.setReserve(r2)     // Catch:{ all -> 0x00b8 }
        L_0x004c:
            boolean r2 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x00b8 }
            if (r2 != 0) goto L_0x0059
            java.lang.String r2 = r7.b((java.lang.String) r3)     // Catch:{ all -> 0x00b8 }
            r0.setSsid(r2)     // Catch:{ all -> 0x00b8 }
        L_0x0059:
            boolean r2 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x00b8 }
            if (r2 != 0) goto L_0x0066
            java.lang.String r2 = r7.b((java.lang.String) r4)     // Catch:{ all -> 0x00b8 }
            r0.setSign(r2)     // Catch:{ all -> 0x00b8 }
        L_0x0066:
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.H     // Catch:{ all -> 0x00b8 }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00b8 }
            r3 = r3[r1]     // Catch:{ all -> 0x00b8 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00b8 }
            r2.put(r3, r9)     // Catch:{ all -> 0x00b8 }
            com.unionpay.tsmservice.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x00a5 }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x00a5 }
            int[] r4 = r7.Z     // Catch:{ Exception -> 0x00a5 }
            r5 = r4[r1]     // Catch:{ Exception -> 0x00a5 }
            int r6 = r5 + 1
            r4[r1] = r6     // Catch:{ Exception -> 0x00a5 }
            r4 = 0
            r3.<init>(r7, r1, r5, r4)     // Catch:{ Exception -> 0x00a5 }
            int r0 = r2.executeCmd(r0, r3, r10)     // Catch:{ Exception -> 0x00a5 }
            if (r0 == 0) goto L_0x009a
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.H     // Catch:{ all -> 0x00b8 }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00b8 }
            r4 = r3[r1]     // Catch:{ all -> 0x00b8 }
            int r4 = r4 + -1
            r3[r1] = r4     // Catch:{ all -> 0x00b8 }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x00b8 }
            r2.remove(r3)     // Catch:{ all -> 0x00b8 }
        L_0x009a:
            r2 = -2
            if (r2 != r0) goto L_0x00a3
            int r8 = a(r1, r8, r9, r10)     // Catch:{ all -> 0x00b8 }
            monitor-exit(r7)
            return r8
        L_0x00a3:
            monitor-exit(r7)
            return r0
        L_0x00a5:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x00b8 }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x00b8 }
            r8.<init>()     // Catch:{ all -> 0x00b8 }
            throw r8     // Catch:{ all -> 0x00b8 }
        L_0x00af:
            int r8 = a(r1, r8, r9, r10)     // Catch:{ all -> 0x00b8 }
            monitor-exit(r7)
            return r8
        L_0x00b5:
            r8 = -1
            goto L_0x000e
        L_0x00b8:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        L_0x00bb:
            r8 = -3
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.executeCmd(com.unionpay.tsmservice.request.ExecuteCmdRequestParams, com.unionpay.tsmservice.ITsmCallback, com.unionpay.tsmservice.ITsmProgressCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x009c, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000f, code lost:
        return r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int getAccountBalance(com.unionpay.tsmservice.request.GetAccountBalanceRequestParams r7, com.unionpay.tsmservice.ITsmCallback r8) throws android.os.RemoteException {
        /*
            r6 = this;
            monitor-enter(r6)
            if (r7 == 0) goto L_0x00b3
            if (r8 != 0) goto L_0x0007
            goto L_0x00b3
        L_0x0007:
            boolean r0 = r6.c()     // Catch:{ all -> 0x00b0 }
            if (r0 != 0) goto L_0x0010
            r7 = -8
        L_0x000e:
            monitor-exit(r6)
            return r7
        L_0x0010:
            com.unionpay.tsmservice.ITsmService r0 = r6.e     // Catch:{ all -> 0x00b0 }
            if (r0 == 0) goto L_0x00ad
            android.content.Context r0 = r6.c     // Catch:{ all -> 0x00b0 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00b0 }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x00b0 }
            r1 = 8
            if (r0 == 0) goto L_0x00a7
            com.unionpay.tsmservice.request.GetAccountBalanceRequestParams r0 = new com.unionpay.tsmservice.request.GetAccountBalanceRequestParams     // Catch:{ all -> 0x00b0 }
            r0.<init>()     // Catch:{ all -> 0x00b0 }
            java.lang.String r2 = r7.getReserve()     // Catch:{ all -> 0x00b0 }
            com.unionpay.tsmservice.AppID r3 = r7.getAppID()     // Catch:{ all -> 0x00b0 }
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00b0 }
            if (r4 != 0) goto L_0x003c
            java.lang.String r2 = r6.b((java.lang.String) r2)     // Catch:{ all -> 0x00b0 }
            r0.setReserve(r2)     // Catch:{ all -> 0x00b0 }
        L_0x003c:
            if (r3 == 0) goto L_0x0062
            java.lang.String r2 = r3.getAppAid()     // Catch:{ all -> 0x00b0 }
            java.lang.String r3 = r3.getAppVersion()     // Catch:{ all -> 0x00b0 }
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00b0 }
            if (r4 != 0) goto L_0x0062
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x00b0 }
            if (r4 != 0) goto L_0x0062
            com.unionpay.tsmservice.AppID r4 = new com.unionpay.tsmservice.AppID     // Catch:{ all -> 0x00b0 }
            java.lang.String r2 = r6.b((java.lang.String) r2)     // Catch:{ all -> 0x00b0 }
            java.lang.String r3 = r6.b((java.lang.String) r3)     // Catch:{ all -> 0x00b0 }
            r4.<init>(r2, r3)     // Catch:{ all -> 0x00b0 }
            r0.setAppID(r4)     // Catch:{ all -> 0x00b0 }
        L_0x0062:
            java.lang.String r2 = r7.getEncryptPin()     // Catch:{ all -> 0x00b0 }
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00b0 }
            if (r3 != 0) goto L_0x006f
            r0.setEncryptPin(r2)     // Catch:{ all -> 0x00b0 }
        L_0x006f:
            com.unionpay.tsmservice.ITsmService r2 = r6.e     // Catch:{ Exception -> 0x009d }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x009d }
            int[] r4 = r6.Z     // Catch:{ Exception -> 0x009d }
            r4 = r4[r1]     // Catch:{ Exception -> 0x009d }
            r5 = 0
            r3.<init>(r6, r1, r4, r5)     // Catch:{ Exception -> 0x009d }
            int r0 = r2.getAccountBalance(r0, r3)     // Catch:{ Exception -> 0x009d }
            r2 = -2
            if (r2 != r0) goto L_0x0088
            int r7 = a(r1, r7, r8)     // Catch:{ all -> 0x00b0 }
            monitor-exit(r6)
            return r7
        L_0x0088:
            if (r0 != 0) goto L_0x009b
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r7 = r6.v     // Catch:{ all -> 0x00b0 }
            int[] r2 = r6.Z     // Catch:{ all -> 0x00b0 }
            r3 = r2[r1]     // Catch:{ all -> 0x00b0 }
            int r4 = r3 + 1
            r2[r1] = r4     // Catch:{ all -> 0x00b0 }
            java.lang.String r1 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00b0 }
            r7.put(r1, r8)     // Catch:{ all -> 0x00b0 }
        L_0x009b:
            monitor-exit(r6)
            return r0
        L_0x009d:
            r7 = move-exception
            r7.printStackTrace()     // Catch:{ all -> 0x00b0 }
            android.os.RemoteException r7 = new android.os.RemoteException     // Catch:{ all -> 0x00b0 }
            r7.<init>()     // Catch:{ all -> 0x00b0 }
            throw r7     // Catch:{ all -> 0x00b0 }
        L_0x00a7:
            int r7 = a(r1, r7, r8)     // Catch:{ all -> 0x00b0 }
            monitor-exit(r6)
            return r7
        L_0x00ad:
            r7 = -1
            goto L_0x000e
        L_0x00b0:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        L_0x00b3:
            r7 = -3
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.getAccountBalance(com.unionpay.tsmservice.request.GetAccountBalanceRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x008e, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000f, code lost:
        return r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int getAccountInfo(com.unionpay.tsmservice.request.GetAccountInfoRequestParams r7, com.unionpay.tsmservice.ITsmCallback r8) throws android.os.RemoteException {
        /*
            r6 = this;
            monitor-enter(r6)
            if (r7 == 0) goto L_0x00a5
            if (r8 != 0) goto L_0x0007
            goto L_0x00a5
        L_0x0007:
            boolean r0 = r6.c()     // Catch:{ all -> 0x00a2 }
            if (r0 != 0) goto L_0x0010
            r7 = -8
        L_0x000e:
            monitor-exit(r6)
            return r7
        L_0x0010:
            com.unionpay.tsmservice.ITsmService r0 = r6.e     // Catch:{ all -> 0x00a2 }
            if (r0 == 0) goto L_0x009f
            android.content.Context r0 = r6.c     // Catch:{ all -> 0x00a2 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00a2 }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x00a2 }
            r1 = 7
            if (r0 == 0) goto L_0x0099
            com.unionpay.tsmservice.request.GetAccountInfoRequestParams r0 = new com.unionpay.tsmservice.request.GetAccountInfoRequestParams     // Catch:{ all -> 0x00a2 }
            r0.<init>()     // Catch:{ all -> 0x00a2 }
            java.lang.String r2 = r7.getReserve()     // Catch:{ all -> 0x00a2 }
            com.unionpay.tsmservice.AppID r3 = r7.getAppID()     // Catch:{ all -> 0x00a2 }
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00a2 }
            if (r4 != 0) goto L_0x003b
            java.lang.String r2 = r6.b((java.lang.String) r2)     // Catch:{ all -> 0x00a2 }
            r0.setReserve(r2)     // Catch:{ all -> 0x00a2 }
        L_0x003b:
            if (r3 == 0) goto L_0x0061
            java.lang.String r2 = r3.getAppAid()     // Catch:{ all -> 0x00a2 }
            java.lang.String r3 = r3.getAppVersion()     // Catch:{ all -> 0x00a2 }
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00a2 }
            if (r4 != 0) goto L_0x0061
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x00a2 }
            if (r4 != 0) goto L_0x0061
            com.unionpay.tsmservice.AppID r4 = new com.unionpay.tsmservice.AppID     // Catch:{ all -> 0x00a2 }
            java.lang.String r2 = r6.b((java.lang.String) r2)     // Catch:{ all -> 0x00a2 }
            java.lang.String r3 = r6.b((java.lang.String) r3)     // Catch:{ all -> 0x00a2 }
            r4.<init>(r2, r3)     // Catch:{ all -> 0x00a2 }
            r0.setAppID(r4)     // Catch:{ all -> 0x00a2 }
        L_0x0061:
            com.unionpay.tsmservice.ITsmService r2 = r6.e     // Catch:{ Exception -> 0x008f }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x008f }
            int[] r4 = r6.Z     // Catch:{ Exception -> 0x008f }
            r4 = r4[r1]     // Catch:{ Exception -> 0x008f }
            r5 = 0
            r3.<init>(r6, r1, r4, r5)     // Catch:{ Exception -> 0x008f }
            int r0 = r2.getAccountInfo(r0, r3)     // Catch:{ Exception -> 0x008f }
            r2 = -2
            if (r2 != r0) goto L_0x007a
            int r7 = a(r1, r7, r8)     // Catch:{ all -> 0x00a2 }
            monitor-exit(r6)
            return r7
        L_0x007a:
            if (r0 != 0) goto L_0x008d
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r7 = r6.u     // Catch:{ all -> 0x00a2 }
            int[] r2 = r6.Z     // Catch:{ all -> 0x00a2 }
            r3 = r2[r1]     // Catch:{ all -> 0x00a2 }
            int r4 = r3 + 1
            r2[r1] = r4     // Catch:{ all -> 0x00a2 }
            java.lang.String r1 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00a2 }
            r7.put(r1, r8)     // Catch:{ all -> 0x00a2 }
        L_0x008d:
            monitor-exit(r6)
            return r0
        L_0x008f:
            r7 = move-exception
            r7.printStackTrace()     // Catch:{ all -> 0x00a2 }
            android.os.RemoteException r7 = new android.os.RemoteException     // Catch:{ all -> 0x00a2 }
            r7.<init>()     // Catch:{ all -> 0x00a2 }
            throw r7     // Catch:{ all -> 0x00a2 }
        L_0x0099:
            int r7 = a(r1, r7, r8)     // Catch:{ all -> 0x00a2 }
            monitor-exit(r6)
            return r7
        L_0x009f:
            r7 = -1
            goto L_0x000e
        L_0x00a2:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        L_0x00a5:
            r7 = -3
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.getAccountInfo(com.unionpay.tsmservice.request.GetAccountInfoRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x009f, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000f, code lost:
        return r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int getAppDetail(com.unionpay.tsmservice.request.GetAppDetailRequestParams r7, com.unionpay.tsmservice.ITsmCallback r8) throws android.os.RemoteException {
        /*
            r6 = this;
            monitor-enter(r6)
            if (r7 == 0) goto L_0x00b6
            if (r8 != 0) goto L_0x0007
            goto L_0x00b6
        L_0x0007:
            boolean r0 = r6.c()     // Catch:{ all -> 0x00b3 }
            if (r0 != 0) goto L_0x0010
            r7 = -8
        L_0x000e:
            monitor-exit(r6)
            return r7
        L_0x0010:
            com.unionpay.tsmservice.ITsmService r0 = r6.e     // Catch:{ all -> 0x00b3 }
            if (r0 == 0) goto L_0x00b0
            android.content.Context r0 = r6.c     // Catch:{ all -> 0x00b3 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00b3 }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x00b3 }
            r1 = 4
            if (r0 == 0) goto L_0x00aa
            com.unionpay.tsmservice.request.GetAppDetailRequestParams r0 = new com.unionpay.tsmservice.request.GetAppDetailRequestParams     // Catch:{ all -> 0x00b3 }
            r0.<init>()     // Catch:{ all -> 0x00b3 }
            java.lang.String r2 = r7.getReserve()     // Catch:{ all -> 0x00b3 }
            com.unionpay.tsmservice.AppID r3 = r7.getAppID()     // Catch:{ all -> 0x00b3 }
            java.lang.String r4 = r7.getTransType()     // Catch:{ all -> 0x00b3 }
            boolean r5 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00b3 }
            if (r5 != 0) goto L_0x003f
            java.lang.String r2 = r6.b((java.lang.String) r2)     // Catch:{ all -> 0x00b3 }
            r0.setReserve(r2)     // Catch:{ all -> 0x00b3 }
        L_0x003f:
            if (r3 == 0) goto L_0x0065
            java.lang.String r2 = r3.getAppAid()     // Catch:{ all -> 0x00b3 }
            java.lang.String r3 = r3.getAppVersion()     // Catch:{ all -> 0x00b3 }
            boolean r5 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00b3 }
            if (r5 != 0) goto L_0x0065
            boolean r5 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x00b3 }
            if (r5 != 0) goto L_0x0065
            com.unionpay.tsmservice.AppID r5 = new com.unionpay.tsmservice.AppID     // Catch:{ all -> 0x00b3 }
            java.lang.String r2 = r6.b((java.lang.String) r2)     // Catch:{ all -> 0x00b3 }
            java.lang.String r3 = r6.b((java.lang.String) r3)     // Catch:{ all -> 0x00b3 }
            r5.<init>(r2, r3)     // Catch:{ all -> 0x00b3 }
            r0.setAppID(r5)     // Catch:{ all -> 0x00b3 }
        L_0x0065:
            boolean r2 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x00b3 }
            if (r2 != 0) goto L_0x0072
            java.lang.String r2 = r6.b((java.lang.String) r4)     // Catch:{ all -> 0x00b3 }
            r0.setTransType(r2)     // Catch:{ all -> 0x00b3 }
        L_0x0072:
            com.unionpay.tsmservice.ITsmService r2 = r6.e     // Catch:{ Exception -> 0x00a0 }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x00a0 }
            int[] r4 = r6.Z     // Catch:{ Exception -> 0x00a0 }
            r4 = r4[r1]     // Catch:{ Exception -> 0x00a0 }
            r5 = 0
            r3.<init>(r6, r1, r4, r5)     // Catch:{ Exception -> 0x00a0 }
            int r0 = r2.getAppDetail(r0, r3)     // Catch:{ Exception -> 0x00a0 }
            r2 = -2
            if (r2 != r0) goto L_0x008b
            int r7 = a(r1, r7, r8)     // Catch:{ all -> 0x00b3 }
            monitor-exit(r6)
            return r7
        L_0x008b:
            if (r0 != 0) goto L_0x009e
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r7 = r6.n     // Catch:{ all -> 0x00b3 }
            int[] r2 = r6.Z     // Catch:{ all -> 0x00b3 }
            r3 = r2[r1]     // Catch:{ all -> 0x00b3 }
            int r4 = r3 + 1
            r2[r1] = r4     // Catch:{ all -> 0x00b3 }
            java.lang.String r1 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00b3 }
            r7.put(r1, r8)     // Catch:{ all -> 0x00b3 }
        L_0x009e:
            monitor-exit(r6)
            return r0
        L_0x00a0:
            r7 = move-exception
            r7.printStackTrace()     // Catch:{ all -> 0x00b3 }
            android.os.RemoteException r7 = new android.os.RemoteException     // Catch:{ all -> 0x00b3 }
            r7.<init>()     // Catch:{ all -> 0x00b3 }
            throw r7     // Catch:{ all -> 0x00b3 }
        L_0x00aa:
            int r7 = a(r1, r7, r8)     // Catch:{ all -> 0x00b3 }
            monitor-exit(r6)
            return r7
        L_0x00b0:
            r7 = -1
            goto L_0x000e
        L_0x00b3:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        L_0x00b6:
            r7 = -3
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.getAppDetail(com.unionpay.tsmservice.request.GetAppDetailRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0097, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000f, code lost:
        return r9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int getAppList(com.unionpay.tsmservice.request.GetAppListRequestParams r9, com.unionpay.tsmservice.ITsmCallback r10) throws android.os.RemoteException {
        /*
            r8 = this;
            monitor-enter(r8)
            if (r9 == 0) goto L_0x00ae
            if (r10 != 0) goto L_0x0007
            goto L_0x00ae
        L_0x0007:
            boolean r0 = r8.c()     // Catch:{ all -> 0x00ab }
            if (r0 != 0) goto L_0x0010
            r9 = -8
        L_0x000e:
            monitor-exit(r8)
            return r9
        L_0x0010:
            com.unionpay.tsmservice.ITsmService r0 = r8.e     // Catch:{ all -> 0x00ab }
            if (r0 == 0) goto L_0x00a8
            android.content.Context r0 = r8.c     // Catch:{ all -> 0x00ab }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00ab }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x00ab }
            r1 = 2
            if (r0 == 0) goto L_0x00a2
            com.unionpay.tsmservice.request.GetAppListRequestParams r0 = new com.unionpay.tsmservice.request.GetAppListRequestParams     // Catch:{ all -> 0x00ab }
            r0.<init>()     // Catch:{ all -> 0x00ab }
            java.lang.String r2 = r9.getReserve()     // Catch:{ all -> 0x00ab }
            java.lang.String r3 = r9.getKeyword()     // Catch:{ all -> 0x00ab }
            java.lang.String[] r4 = r9.getStatus()     // Catch:{ all -> 0x00ab }
            boolean r5 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00ab }
            if (r5 != 0) goto L_0x003f
            java.lang.String r2 = r8.b((java.lang.String) r2)     // Catch:{ all -> 0x00ab }
            r0.setReserve(r2)     // Catch:{ all -> 0x00ab }
        L_0x003f:
            boolean r2 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x00ab }
            if (r2 != 0) goto L_0x004c
            java.lang.String r2 = r8.b((java.lang.String) r3)     // Catch:{ all -> 0x00ab }
            r0.setKeyword(r2)     // Catch:{ all -> 0x00ab }
        L_0x004c:
            r2 = 0
            if (r4 == 0) goto L_0x006b
            int r3 = r4.length     // Catch:{ all -> 0x00ab }
            java.lang.String[] r5 = new java.lang.String[r3]     // Catch:{ all -> 0x00ab }
            r6 = 0
        L_0x0053:
            if (r6 >= r3) goto L_0x0068
            r7 = r4[r6]     // Catch:{ all -> 0x00ab }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x00ab }
            if (r7 != 0) goto L_0x0065
            r7 = r4[r6]     // Catch:{ all -> 0x00ab }
            java.lang.String r7 = r8.b((java.lang.String) r7)     // Catch:{ all -> 0x00ab }
            r5[r6] = r7     // Catch:{ all -> 0x00ab }
        L_0x0065:
            int r6 = r6 + 1
            goto L_0x0053
        L_0x0068:
            r0.setStatus(r5)     // Catch:{ all -> 0x00ab }
        L_0x006b:
            com.unionpay.tsmservice.ITsmService r3 = r8.e     // Catch:{ Exception -> 0x0098 }
            com.unionpay.tsmservice.UPTsmAddon$b r4 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x0098 }
            int[] r5 = r8.Z     // Catch:{ Exception -> 0x0098 }
            r5 = r5[r1]     // Catch:{ Exception -> 0x0098 }
            r4.<init>(r8, r1, r5, r2)     // Catch:{ Exception -> 0x0098 }
            int r0 = r3.getAppList(r0, r4)     // Catch:{ Exception -> 0x0098 }
            r2 = -2
            if (r2 != r0) goto L_0x0083
            int r9 = a(r1, r9, r10)     // Catch:{ all -> 0x00ab }
            monitor-exit(r8)
            return r9
        L_0x0083:
            if (r0 != 0) goto L_0x0096
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r9 = r8.l     // Catch:{ all -> 0x00ab }
            int[] r2 = r8.Z     // Catch:{ all -> 0x00ab }
            r3 = r2[r1]     // Catch:{ all -> 0x00ab }
            int r4 = r3 + 1
            r2[r1] = r4     // Catch:{ all -> 0x00ab }
            java.lang.String r1 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00ab }
            r9.put(r1, r10)     // Catch:{ all -> 0x00ab }
        L_0x0096:
            monitor-exit(r8)
            return r0
        L_0x0098:
            r9 = move-exception
            r9.printStackTrace()     // Catch:{ all -> 0x00ab }
            android.os.RemoteException r9 = new android.os.RemoteException     // Catch:{ all -> 0x00ab }
            r9.<init>()     // Catch:{ all -> 0x00ab }
            throw r9     // Catch:{ all -> 0x00ab }
        L_0x00a2:
            int r9 = a(r1, r9, r10)     // Catch:{ all -> 0x00ab }
            monitor-exit(r8)
            return r9
        L_0x00a8:
            r9 = -1
            goto L_0x000e
        L_0x00ab:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        L_0x00ae:
            r9 = -3
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.getAppList(com.unionpay.tsmservice.request.GetAppListRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x008e, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000f, code lost:
        return r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int getAppStatus(com.unionpay.tsmservice.request.GetAppStatusRequestParams r7, com.unionpay.tsmservice.ITsmCallback r8) throws android.os.RemoteException {
        /*
            r6 = this;
            monitor-enter(r6)
            if (r7 == 0) goto L_0x00a5
            if (r8 != 0) goto L_0x0007
            goto L_0x00a5
        L_0x0007:
            boolean r0 = r6.c()     // Catch:{ all -> 0x00a2 }
            if (r0 != 0) goto L_0x0010
            r7 = -8
        L_0x000e:
            monitor-exit(r6)
            return r7
        L_0x0010:
            com.unionpay.tsmservice.ITsmService r0 = r6.e     // Catch:{ all -> 0x00a2 }
            if (r0 == 0) goto L_0x009f
            android.content.Context r0 = r6.c     // Catch:{ all -> 0x00a2 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00a2 }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x00a2 }
            r1 = 5
            if (r0 == 0) goto L_0x0099
            com.unionpay.tsmservice.request.GetAppStatusRequestParams r0 = new com.unionpay.tsmservice.request.GetAppStatusRequestParams     // Catch:{ all -> 0x00a2 }
            r0.<init>()     // Catch:{ all -> 0x00a2 }
            java.lang.String r2 = r7.getReserve()     // Catch:{ all -> 0x00a2 }
            com.unionpay.tsmservice.AppID r3 = r7.getAppID()     // Catch:{ all -> 0x00a2 }
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00a2 }
            if (r4 != 0) goto L_0x003b
            java.lang.String r2 = r6.b((java.lang.String) r2)     // Catch:{ all -> 0x00a2 }
            r0.setReserve(r2)     // Catch:{ all -> 0x00a2 }
        L_0x003b:
            if (r3 == 0) goto L_0x0061
            java.lang.String r2 = r3.getAppAid()     // Catch:{ all -> 0x00a2 }
            java.lang.String r3 = r3.getAppVersion()     // Catch:{ all -> 0x00a2 }
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00a2 }
            if (r4 != 0) goto L_0x0061
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x00a2 }
            if (r4 != 0) goto L_0x0061
            com.unionpay.tsmservice.AppID r4 = new com.unionpay.tsmservice.AppID     // Catch:{ all -> 0x00a2 }
            java.lang.String r2 = r6.b((java.lang.String) r2)     // Catch:{ all -> 0x00a2 }
            java.lang.String r3 = r6.b((java.lang.String) r3)     // Catch:{ all -> 0x00a2 }
            r4.<init>(r2, r3)     // Catch:{ all -> 0x00a2 }
            r0.setAppID(r4)     // Catch:{ all -> 0x00a2 }
        L_0x0061:
            com.unionpay.tsmservice.ITsmService r2 = r6.e     // Catch:{ Exception -> 0x008f }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x008f }
            int[] r4 = r6.Z     // Catch:{ Exception -> 0x008f }
            r4 = r4[r1]     // Catch:{ Exception -> 0x008f }
            r5 = 0
            r3.<init>(r6, r1, r4, r5)     // Catch:{ Exception -> 0x008f }
            int r0 = r2.getAppStatus(r0, r3)     // Catch:{ Exception -> 0x008f }
            r2 = -2
            if (r2 != r0) goto L_0x007a
            int r7 = a(r1, r7, r8)     // Catch:{ all -> 0x00a2 }
            monitor-exit(r6)
            return r7
        L_0x007a:
            if (r0 != 0) goto L_0x008d
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r7 = r6.m     // Catch:{ all -> 0x00a2 }
            int[] r2 = r6.Z     // Catch:{ all -> 0x00a2 }
            r3 = r2[r1]     // Catch:{ all -> 0x00a2 }
            int r4 = r3 + 1
            r2[r1] = r4     // Catch:{ all -> 0x00a2 }
            java.lang.String r1 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00a2 }
            r7.put(r1, r8)     // Catch:{ all -> 0x00a2 }
        L_0x008d:
            monitor-exit(r6)
            return r0
        L_0x008f:
            r7 = move-exception
            r7.printStackTrace()     // Catch:{ all -> 0x00a2 }
            android.os.RemoteException r7 = new android.os.RemoteException     // Catch:{ all -> 0x00a2 }
            r7.<init>()     // Catch:{ all -> 0x00a2 }
            throw r7     // Catch:{ all -> 0x00a2 }
        L_0x0099:
            int r7 = a(r1, r7, r8)     // Catch:{ all -> 0x00a2 }
            monitor-exit(r6)
            return r7
        L_0x009f:
            r7 = -1
            goto L_0x000e
        L_0x00a2:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        L_0x00a5:
            r7 = -3
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.getAppStatus(com.unionpay.tsmservice.request.GetAppStatusRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001c, code lost:
        return r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0084, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x009b, code lost:
        return -3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int getAssociatedApp(com.unionpay.tsmservice.request.GetAssociatedAppRequestParams r7, com.unionpay.tsmservice.ITsmCallback r8) throws android.os.RemoteException {
        /*
            r6 = this;
            monitor-enter(r6)
            r0 = -3
            if (r7 == 0) goto L_0x009a
            if (r8 != 0) goto L_0x0008
            goto L_0x009a
        L_0x0008:
            java.lang.String r1 = r7.getEncryptPan()     // Catch:{ all -> 0x0097 }
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x0097 }
            if (r2 == 0) goto L_0x0014
            monitor-exit(r6)
            return r0
        L_0x0014:
            boolean r0 = r6.c()     // Catch:{ all -> 0x0097 }
            if (r0 != 0) goto L_0x001d
            r7 = -8
        L_0x001b:
            monitor-exit(r6)
            return r7
        L_0x001d:
            com.unionpay.tsmservice.ITsmService r0 = r6.e     // Catch:{ all -> 0x0097 }
            if (r0 == 0) goto L_0x0095
            android.content.Context r0 = r6.c     // Catch:{ all -> 0x0097 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x0097 }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x0097 }
            r2 = 1
            if (r0 == 0) goto L_0x008f
            com.unionpay.tsmservice.request.GetAssociatedAppRequestParams r0 = new com.unionpay.tsmservice.request.GetAssociatedAppRequestParams     // Catch:{ all -> 0x0097 }
            r0.<init>()     // Catch:{ all -> 0x0097 }
            java.lang.String r3 = r7.getReserve()     // Catch:{ all -> 0x0097 }
            boolean r4 = r6.h     // Catch:{ all -> 0x0097 }
            if (r4 == 0) goto L_0x0043
            java.lang.String r3 = f(r3)     // Catch:{ all -> 0x0097 }
            java.lang.String r3 = r6.g(r3)     // Catch:{ all -> 0x0097 }
        L_0x0043:
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x0097 }
            if (r4 != 0) goto L_0x0050
            java.lang.String r3 = r6.b((java.lang.String) r3)     // Catch:{ all -> 0x0097 }
            r0.setReserve(r3)     // Catch:{ all -> 0x0097 }
        L_0x0050:
            java.lang.String r1 = r6.b((java.lang.String) r1)     // Catch:{ all -> 0x0097 }
            r0.setEncryptPan(r1)     // Catch:{ all -> 0x0097 }
            com.unionpay.tsmservice.ITsmService r1 = r6.e     // Catch:{ Exception -> 0x0085 }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x0085 }
            int[] r4 = r6.Z     // Catch:{ Exception -> 0x0085 }
            r4 = r4[r2]     // Catch:{ Exception -> 0x0085 }
            r5 = 0
            r3.<init>(r6, r2, r4, r5)     // Catch:{ Exception -> 0x0085 }
            int r0 = r1.getAssociatedApp(r0, r3)     // Catch:{ Exception -> 0x0085 }
            r1 = -2
            if (r1 != r0) goto L_0x0070
            int r7 = a(r2, r7, r8)     // Catch:{ all -> 0x0097 }
            monitor-exit(r6)
            return r7
        L_0x0070:
            if (r0 != 0) goto L_0x0083
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r7 = r6.j     // Catch:{ all -> 0x0097 }
            int[] r1 = r6.Z     // Catch:{ all -> 0x0097 }
            r3 = r1[r2]     // Catch:{ all -> 0x0097 }
            int r4 = r3 + 1
            r1[r2] = r4     // Catch:{ all -> 0x0097 }
            java.lang.String r1 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x0097 }
            r7.put(r1, r8)     // Catch:{ all -> 0x0097 }
        L_0x0083:
            monitor-exit(r6)
            return r0
        L_0x0085:
            r7 = move-exception
            r7.printStackTrace()     // Catch:{ all -> 0x0097 }
            android.os.RemoteException r7 = new android.os.RemoteException     // Catch:{ all -> 0x0097 }
            r7.<init>()     // Catch:{ all -> 0x0097 }
            throw r7     // Catch:{ all -> 0x0097 }
        L_0x008f:
            int r7 = a(r2, r7, r8)     // Catch:{ all -> 0x0097 }
            monitor-exit(r6)
            return r7
        L_0x0095:
            r7 = -1
            goto L_0x001b
        L_0x0097:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        L_0x009a:
            monitor-exit(r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.getAssociatedApp(com.unionpay.tsmservice.request.GetAssociatedAppRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x002a, code lost:
        return r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00a6, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00bb, code lost:
        return -3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00c0, code lost:
        return -3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int getCardInfo(com.unionpay.tsmservice.request.GetCardInfoRequestParams r8, com.unionpay.tsmservice.ITsmCallback r9) throws android.os.RemoteException {
        /*
            r7 = this;
            monitor-enter(r7)
            r0 = -3
            if (r8 == 0) goto L_0x00bf
            if (r9 != 0) goto L_0x0008
            goto L_0x00bf
        L_0x0008:
            java.lang.String[] r1 = r8.getAppAID()     // Catch:{ all -> 0x00bc }
            int r2 = r1.length     // Catch:{ all -> 0x00bc }
            if (r1 == 0) goto L_0x00ba
            if (r2 != 0) goto L_0x0013
            goto L_0x00ba
        L_0x0013:
            r3 = 0
            r4 = 0
        L_0x0015:
            if (r4 >= r2) goto L_0x001e
            r5 = r1[r4]     // Catch:{ all -> 0x00bc }
            if (r5 != 0) goto L_0x001e
            int r4 = r4 + 1
            goto L_0x0015
        L_0x001e:
            if (r4 != r2) goto L_0x0022
            monitor-exit(r7)
            return r0
        L_0x0022:
            boolean r0 = r7.c()     // Catch:{ all -> 0x00bc }
            if (r0 != 0) goto L_0x002b
            r8 = -8
        L_0x0029:
            monitor-exit(r7)
            return r8
        L_0x002b:
            com.unionpay.tsmservice.ITsmService r0 = r7.e     // Catch:{ all -> 0x00bc }
            if (r0 == 0) goto L_0x00b7
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x00bc }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00bc }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x00bc }
            r4 = 6
            if (r0 == 0) goto L_0x00b1
            java.lang.String[] r0 = new java.lang.String[r2]     // Catch:{ all -> 0x00bc }
            r5 = 0
        L_0x003f:
            if (r5 >= r2) goto L_0x0055
            r6 = r1[r5]     // Catch:{ all -> 0x00bc }
            if (r6 != 0) goto L_0x004a
            r6 = r1[r5]     // Catch:{ all -> 0x00bc }
            r0[r5] = r6     // Catch:{ all -> 0x00bc }
            goto L_0x0052
        L_0x004a:
            r6 = r1[r5]     // Catch:{ all -> 0x00bc }
            java.lang.String r6 = r7.b((java.lang.String) r6)     // Catch:{ all -> 0x00bc }
            r0[r5] = r6     // Catch:{ all -> 0x00bc }
        L_0x0052:
            int r5 = r5 + 1
            goto L_0x003f
        L_0x0055:
            com.unionpay.tsmservice.request.GetCardInfoRequestParams r1 = new com.unionpay.tsmservice.request.GetCardInfoRequestParams     // Catch:{ all -> 0x00bc }
            r1.<init>()     // Catch:{ all -> 0x00bc }
            r1.setAppAID(r0)     // Catch:{ all -> 0x00bc }
            java.lang.String r0 = r8.getReserve()     // Catch:{ all -> 0x00bc }
            boolean r2 = r7.h     // Catch:{ all -> 0x00bc }
            if (r2 == 0) goto L_0x006d
            java.lang.String r0 = f(r0)     // Catch:{ all -> 0x00bc }
            java.lang.String r0 = r7.g(r0)     // Catch:{ all -> 0x00bc }
        L_0x006d:
            boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x00bc }
            if (r2 != 0) goto L_0x007a
            java.lang.String r0 = r7.b((java.lang.String) r0)     // Catch:{ all -> 0x00bc }
            r1.setReserve(r0)     // Catch:{ all -> 0x00bc }
        L_0x007a:
            com.unionpay.tsmservice.ITsmService r0 = r7.e     // Catch:{ Exception -> 0x00a7 }
            com.unionpay.tsmservice.UPTsmAddon$b r2 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x00a7 }
            int[] r5 = r7.Z     // Catch:{ Exception -> 0x00a7 }
            r5 = r5[r4]     // Catch:{ Exception -> 0x00a7 }
            r2.<init>(r7, r4, r5, r3)     // Catch:{ Exception -> 0x00a7 }
            int r0 = r0.getCardInfo(r1, r2)     // Catch:{ Exception -> 0x00a7 }
            r1 = -2
            if (r1 != r0) goto L_0x0092
            int r8 = a(r4, r8, r9)     // Catch:{ all -> 0x00bc }
            monitor-exit(r7)
            return r8
        L_0x0092:
            if (r0 != 0) goto L_0x00a5
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r8 = r7.A     // Catch:{ all -> 0x00bc }
            int[] r1 = r7.Z     // Catch:{ all -> 0x00bc }
            r2 = r1[r4]     // Catch:{ all -> 0x00bc }
            int r3 = r2 + 1
            r1[r4] = r3     // Catch:{ all -> 0x00bc }
            java.lang.String r1 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x00bc }
            r8.put(r1, r9)     // Catch:{ all -> 0x00bc }
        L_0x00a5:
            monitor-exit(r7)
            return r0
        L_0x00a7:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x00bc }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x00bc }
            r8.<init>()     // Catch:{ all -> 0x00bc }
            throw r8     // Catch:{ all -> 0x00bc }
        L_0x00b1:
            int r8 = a(r4, r8, r9)     // Catch:{ all -> 0x00bc }
            monitor-exit(r7)
            return r8
        L_0x00b7:
            r8 = -1
            goto L_0x0029
        L_0x00ba:
            monitor-exit(r7)
            return r0
        L_0x00bc:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        L_0x00bf:
            monitor-exit(r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.getCardInfo(com.unionpay.tsmservice.request.GetCardInfoRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000f, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int getCardInfoBySamsungPay(com.unionpay.tsmservice.request.GetCardInfoBySpayRequestParams r8, com.unionpay.tsmservice.ITsmCallback r9) throws android.os.RemoteException {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r8 == 0) goto L_0x00c9
            if (r9 != 0) goto L_0x0007
            goto L_0x00c9
        L_0x0007:
            boolean r0 = r7.c()     // Catch:{ all -> 0x00c6 }
            if (r0 != 0) goto L_0x0010
            r8 = -8
        L_0x000e:
            monitor-exit(r7)
            return r8
        L_0x0010:
            com.unionpay.tsmservice.ITsmService r0 = r7.e     // Catch:{ all -> 0x00c6 }
            if (r0 == 0) goto L_0x00c3
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x00c6 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00c6 }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x00c6 }
            r1 = 28
            if (r0 == 0) goto L_0x00bd
            com.unionpay.tsmservice.request.GetCardInfoBySpayRequestParams r0 = new com.unionpay.tsmservice.request.GetCardInfoBySpayRequestParams     // Catch:{ all -> 0x00c6 }
            r0.<init>()     // Catch:{ all -> 0x00c6 }
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x00c6 }
            boolean r3 = r7.h     // Catch:{ all -> 0x00c6 }
            if (r3 == 0) goto L_0x0037
            java.lang.String r2 = f(r2)     // Catch:{ all -> 0x00c6 }
            java.lang.String r2 = r7.g(r2)     // Catch:{ all -> 0x00c6 }
        L_0x0037:
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00c6 }
            if (r3 != 0) goto L_0x0044
            java.lang.String r2 = r7.b((java.lang.String) r2)     // Catch:{ all -> 0x00c6 }
            r0.setReserve(r2)     // Catch:{ all -> 0x00c6 }
        L_0x0044:
            com.unionpay.tsmservice.data.Amount r2 = r8.getAmount()     // Catch:{ all -> 0x00c6 }
            if (r2 == 0) goto L_0x0074
            java.lang.String r3 = r2.getCurrencyType()     // Catch:{ all -> 0x00c6 }
            java.lang.String r2 = r2.getProductPrice()     // Catch:{ all -> 0x00c6 }
            com.unionpay.tsmservice.data.Amount r4 = new com.unionpay.tsmservice.data.Amount     // Catch:{ all -> 0x00c6 }
            r4.<init>()     // Catch:{ all -> 0x00c6 }
            boolean r5 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x00c6 }
            if (r5 != 0) goto L_0x0064
            java.lang.String r3 = r7.b((java.lang.String) r3)     // Catch:{ all -> 0x00c6 }
            r4.setCurrencyType(r3)     // Catch:{ all -> 0x00c6 }
        L_0x0064:
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00c6 }
            if (r3 != 0) goto L_0x0071
            java.lang.String r2 = r7.b((java.lang.String) r2)     // Catch:{ all -> 0x00c6 }
            r4.setProductPrice(r2)     // Catch:{ all -> 0x00c6 }
        L_0x0071:
            r0.setAmount(r4)     // Catch:{ all -> 0x00c6 }
        L_0x0074:
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.I     // Catch:{ all -> 0x00c6 }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00c6 }
            r3 = r3[r1]     // Catch:{ all -> 0x00c6 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00c6 }
            r2.put(r3, r9)     // Catch:{ all -> 0x00c6 }
            com.unionpay.tsmservice.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x00b3 }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x00b3 }
            int[] r4 = r7.Z     // Catch:{ Exception -> 0x00b3 }
            r5 = r4[r1]     // Catch:{ Exception -> 0x00b3 }
            int r6 = r5 + 1
            r4[r1] = r6     // Catch:{ Exception -> 0x00b3 }
            r4 = 0
            r3.<init>(r7, r1, r5, r4)     // Catch:{ Exception -> 0x00b3 }
            int r0 = r2.getCardInfoBySamsungPay(r0, r3)     // Catch:{ Exception -> 0x00b3 }
            if (r0 == 0) goto L_0x00a8
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.I     // Catch:{ all -> 0x00c6 }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00c6 }
            r4 = r3[r1]     // Catch:{ all -> 0x00c6 }
            int r4 = r4 + -1
            r3[r1] = r4     // Catch:{ all -> 0x00c6 }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x00c6 }
            r2.remove(r3)     // Catch:{ all -> 0x00c6 }
        L_0x00a8:
            r2 = -2
            if (r2 != r0) goto L_0x00b1
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x00c6 }
            monitor-exit(r7)
            return r8
        L_0x00b1:
            monitor-exit(r7)
            return r0
        L_0x00b3:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x00c6 }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x00c6 }
            r8.<init>()     // Catch:{ all -> 0x00c6 }
            throw r8     // Catch:{ all -> 0x00c6 }
        L_0x00bd:
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x00c6 }
            monitor-exit(r7)
            return r8
        L_0x00c3:
            r8 = -1
            goto L_0x000e
        L_0x00c6:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        L_0x00c9:
            r8 = -3
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.getCardInfoBySamsungPay(com.unionpay.tsmservice.request.GetCardInfoBySpayRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    public Context getContext() {
        return this.c;
    }

    public int getCryptType() {
        return this.g;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0065, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0005, code lost:
        return r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int getDefaultCard(com.unionpay.tsmservice.request.GetDefaultCardRequestParams r7, com.unionpay.tsmservice.ITsmCallback r8) throws android.os.RemoteException {
        /*
            r6 = this;
            monitor-enter(r6)
            if (r8 != 0) goto L_0x0006
            r7 = -3
        L_0x0004:
            monitor-exit(r6)
            return r7
        L_0x0006:
            boolean r0 = r6.c()     // Catch:{ all -> 0x0078 }
            if (r0 != 0) goto L_0x000e
            r7 = -8
            goto L_0x0004
        L_0x000e:
            com.unionpay.tsmservice.ITsmService r0 = r6.e     // Catch:{ all -> 0x0078 }
            if (r0 == 0) goto L_0x0076
            android.content.Context r0 = r6.c     // Catch:{ all -> 0x0078 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x0078 }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x0078 }
            r1 = 13
            if (r0 == 0) goto L_0x0070
            com.unionpay.tsmservice.request.GetDefaultCardRequestParams r0 = new com.unionpay.tsmservice.request.GetDefaultCardRequestParams     // Catch:{ all -> 0x0078 }
            r0.<init>()     // Catch:{ all -> 0x0078 }
            if (r7 == 0) goto L_0x0038
            java.lang.String r2 = r7.getReserve()     // Catch:{ all -> 0x0078 }
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x0078 }
            if (r3 != 0) goto L_0x0038
            java.lang.String r2 = r6.b((java.lang.String) r2)     // Catch:{ all -> 0x0078 }
            r0.setReserve(r2)     // Catch:{ all -> 0x0078 }
        L_0x0038:
            com.unionpay.tsmservice.ITsmService r2 = r6.e     // Catch:{ Exception -> 0x0066 }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x0066 }
            int[] r4 = r6.Z     // Catch:{ Exception -> 0x0066 }
            r4 = r4[r1]     // Catch:{ Exception -> 0x0066 }
            r5 = 0
            r3.<init>(r6, r1, r4, r5)     // Catch:{ Exception -> 0x0066 }
            int r0 = r2.getDefaultCard(r0, r3)     // Catch:{ Exception -> 0x0066 }
            r2 = -2
            if (r2 != r0) goto L_0x0051
            int r7 = a(r1, r7, r8)     // Catch:{ all -> 0x0078 }
            monitor-exit(r6)
            return r7
        L_0x0051:
            if (r0 != 0) goto L_0x0064
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r7 = r6.y     // Catch:{ all -> 0x0078 }
            int[] r2 = r6.Z     // Catch:{ all -> 0x0078 }
            r3 = r2[r1]     // Catch:{ all -> 0x0078 }
            int r4 = r3 + 1
            r2[r1] = r4     // Catch:{ all -> 0x0078 }
            java.lang.String r1 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x0078 }
            r7.put(r1, r8)     // Catch:{ all -> 0x0078 }
        L_0x0064:
            monitor-exit(r6)
            return r0
        L_0x0066:
            r7 = move-exception
            r7.printStackTrace()     // Catch:{ all -> 0x0078 }
            android.os.RemoteException r7 = new android.os.RemoteException     // Catch:{ all -> 0x0078 }
            r7.<init>()     // Catch:{ all -> 0x0078 }
            throw r7     // Catch:{ all -> 0x0078 }
        L_0x0070:
            int r7 = a(r1, r7, r8)     // Catch:{ all -> 0x0078 }
            monitor-exit(r6)
            return r7
        L_0x0076:
            r7 = -1
            goto L_0x0004
        L_0x0078:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.getDefaultCard(com.unionpay.tsmservice.request.GetDefaultCardRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x002c, code lost:
        return r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00c0, code lost:
        return -3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00c5, code lost:
        return -3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int getEncryptData(com.unionpay.tsmservice.request.GetEncryptDataRequestParams r8, com.unionpay.tsmservice.ITsmCallback r9) throws android.os.RemoteException {
        /*
            r7 = this;
            monitor-enter(r7)
            r0 = -3
            if (r9 == 0) goto L_0x00c4
            if (r8 != 0) goto L_0x0008
            goto L_0x00c4
        L_0x0008:
            int r1 = r8.getType()     // Catch:{ all -> 0x00c1 }
            java.lang.String r2 = r8.getPan()     // Catch:{ all -> 0x00c1 }
            r3 = 2000(0x7d0, float:2.803E-42)
            if (r1 < r3) goto L_0x00bf
            r4 = 2001(0x7d1, float:2.804E-42)
            if (r1 <= r4) goto L_0x001a
            goto L_0x00bf
        L_0x001a:
            if (r1 != r3) goto L_0x0024
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00c1 }
            if (r4 == 0) goto L_0x0024
            monitor-exit(r7)
            return r0
        L_0x0024:
            boolean r0 = r7.c()     // Catch:{ all -> 0x00c1 }
            if (r0 != 0) goto L_0x002d
            r8 = -8
        L_0x002b:
            monitor-exit(r7)
            return r8
        L_0x002d:
            com.unionpay.tsmservice.ITsmService r0 = r7.e     // Catch:{ all -> 0x00c1 }
            if (r0 == 0) goto L_0x00bc
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x00c1 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00c1 }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x00c1 }
            r4 = 31
            if (r0 == 0) goto L_0x00b6
            com.unionpay.tsmservice.request.GetEncryptDataRequestParams r0 = new com.unionpay.tsmservice.request.GetEncryptDataRequestParams     // Catch:{ all -> 0x00c1 }
            r0.<init>()     // Catch:{ all -> 0x00c1 }
            if (r1 != r3) goto L_0x004d
            java.lang.String r2 = r7.b((java.lang.String) r2)     // Catch:{ all -> 0x00c1 }
            r0.setPan(r2)     // Catch:{ all -> 0x00c1 }
        L_0x004d:
            r0.setType(r1)     // Catch:{ all -> 0x00c1 }
            java.lang.String r1 = r8.getReserve()     // Catch:{ all -> 0x00c1 }
            boolean r2 = r7.h     // Catch:{ all -> 0x00c1 }
            if (r2 == 0) goto L_0x0060
            java.lang.String r1 = f(r1)     // Catch:{ all -> 0x00c1 }
            java.lang.String r1 = r7.g(r1)     // Catch:{ all -> 0x00c1 }
        L_0x0060:
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x00c1 }
            if (r2 != 0) goto L_0x006d
            java.lang.String r1 = r7.b((java.lang.String) r1)     // Catch:{ all -> 0x00c1 }
            r0.setReserve(r1)     // Catch:{ all -> 0x00c1 }
        L_0x006d:
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r1 = r7.L     // Catch:{ all -> 0x00c1 }
            int[] r2 = r7.Z     // Catch:{ all -> 0x00c1 }
            r2 = r2[r4]     // Catch:{ all -> 0x00c1 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x00c1 }
            r1.put(r2, r9)     // Catch:{ all -> 0x00c1 }
            com.unionpay.tsmservice.ITsmService r1 = r7.e     // Catch:{ Exception -> 0x00ac }
            com.unionpay.tsmservice.UPTsmAddon$b r2 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x00ac }
            int[] r3 = r7.Z     // Catch:{ Exception -> 0x00ac }
            r5 = r3[r4]     // Catch:{ Exception -> 0x00ac }
            int r6 = r5 + 1
            r3[r4] = r6     // Catch:{ Exception -> 0x00ac }
            r3 = 0
            r2.<init>(r7, r4, r5, r3)     // Catch:{ Exception -> 0x00ac }
            int r0 = r1.getEncryptData(r0, r2)     // Catch:{ Exception -> 0x00ac }
            if (r0 == 0) goto L_0x00a1
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r1 = r7.L     // Catch:{ all -> 0x00c1 }
            int[] r2 = r7.Z     // Catch:{ all -> 0x00c1 }
            r3 = r2[r4]     // Catch:{ all -> 0x00c1 }
            int r3 = r3 + -1
            r2[r4] = r3     // Catch:{ all -> 0x00c1 }
            java.lang.String r2 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00c1 }
            r1.remove(r2)     // Catch:{ all -> 0x00c1 }
        L_0x00a1:
            r1 = -2
            if (r1 != r0) goto L_0x00aa
            int r8 = a(r4, r8, r9)     // Catch:{ all -> 0x00c1 }
            monitor-exit(r7)
            return r8
        L_0x00aa:
            monitor-exit(r7)
            return r0
        L_0x00ac:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x00c1 }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x00c1 }
            r8.<init>()     // Catch:{ all -> 0x00c1 }
            throw r8     // Catch:{ all -> 0x00c1 }
        L_0x00b6:
            int r8 = a(r4, r8, r9)     // Catch:{ all -> 0x00c1 }
            monitor-exit(r7)
            return r8
        L_0x00bc:
            r8 = -1
            goto L_0x002b
        L_0x00bf:
            monitor-exit(r7)
            return r0
        L_0x00c1:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        L_0x00c4:
            monitor-exit(r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.getEncryptData(com.unionpay.tsmservice.request.GetEncryptDataRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
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
    public synchronized int getMessageDetails(com.unionpay.tsmservice.request.GetMessageDetailsRequestParams r8, com.unionpay.tsmservice.ITsmCallback r9) throws android.os.RemoteException {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r9 != 0) goto L_0x0006
            r8 = -3
        L_0x0004:
            monitor-exit(r7)
            return r8
        L_0x0006:
            java.lang.String r0 = "01.00.35"
            boolean r0 = r7.d((java.lang.String) r0)     // Catch:{ all -> 0x00ba }
            r1 = -8
            if (r0 != 0) goto L_0x0011
            monitor-exit(r7)
            return r1
        L_0x0011:
            boolean r0 = r7.c()     // Catch:{ all -> 0x00ba }
            if (r0 != 0) goto L_0x0019
            monitor-exit(r7)
            return r1
        L_0x0019:
            com.unionpay.tsmservice.ITsmService r0 = r7.e     // Catch:{ all -> 0x00ba }
            if (r0 == 0) goto L_0x00b7
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x00ba }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00ba }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x00ba }
            r1 = 44
            if (r0 == 0) goto L_0x00b1
            com.unionpay.tsmservice.request.GetMessageDetailsRequestParams r0 = new com.unionpay.tsmservice.request.GetMessageDetailsRequestParams     // Catch:{ all -> 0x00ba }
            r0.<init>()     // Catch:{ all -> 0x00ba }
            java.lang.String r2 = ""
            if (r8 == 0) goto L_0x004f
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x00ba }
            android.os.Bundle r3 = r8.getParams()     // Catch:{ all -> 0x00ba }
            if (r3 == 0) goto L_0x004f
            android.os.Bundle r4 = new android.os.Bundle     // Catch:{ all -> 0x00ba }
            r4.<init>()     // Catch:{ all -> 0x00ba }
            java.lang.String r5 = "encryptData"
            java.lang.String r3 = r7.a((android.os.Bundle) r3)     // Catch:{ all -> 0x00ba }
            r4.putString(r5, r3)     // Catch:{ all -> 0x00ba }
            r0.setParams(r4)     // Catch:{ all -> 0x00ba }
        L_0x004f:
            boolean r3 = r7.h     // Catch:{ all -> 0x00ba }
            if (r3 == 0) goto L_0x005b
            java.lang.String r2 = f(r2)     // Catch:{ all -> 0x00ba }
            java.lang.String r2 = r7.g(r2)     // Catch:{ all -> 0x00ba }
        L_0x005b:
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00ba }
            if (r3 != 0) goto L_0x0068
            java.lang.String r2 = r7.b((java.lang.String) r2)     // Catch:{ all -> 0x00ba }
            r0.setReserve(r2)     // Catch:{ all -> 0x00ba }
        L_0x0068:
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.V     // Catch:{ all -> 0x00ba }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00ba }
            r3 = r3[r1]     // Catch:{ all -> 0x00ba }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00ba }
            r2.put(r3, r9)     // Catch:{ all -> 0x00ba }
            com.unionpay.tsmservice.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x00a7 }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x00a7 }
            int[] r4 = r7.Z     // Catch:{ Exception -> 0x00a7 }
            r5 = r4[r1]     // Catch:{ Exception -> 0x00a7 }
            int r6 = r5 + 1
            r4[r1] = r6     // Catch:{ Exception -> 0x00a7 }
            r4 = 0
            r3.<init>(r7, r1, r5, r4)     // Catch:{ Exception -> 0x00a7 }
            int r0 = r2.getMessageDetails(r0, r3)     // Catch:{ Exception -> 0x00a7 }
            if (r0 == 0) goto L_0x009c
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.V     // Catch:{ all -> 0x00ba }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00ba }
            r4 = r3[r1]     // Catch:{ all -> 0x00ba }
            int r4 = r4 + -1
            r3[r1] = r4     // Catch:{ all -> 0x00ba }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x00ba }
            r2.remove(r3)     // Catch:{ all -> 0x00ba }
        L_0x009c:
            r2 = -2
            if (r2 != r0) goto L_0x00a5
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x00ba }
            monitor-exit(r7)
            return r8
        L_0x00a5:
            monitor-exit(r7)
            return r0
        L_0x00a7:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x00ba }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x00ba }
            r8.<init>()     // Catch:{ all -> 0x00ba }
            throw r8     // Catch:{ all -> 0x00ba }
        L_0x00b1:
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x00ba }
            monitor-exit(r7)
            return r8
        L_0x00b7:
            r8 = -1
            goto L_0x0004
        L_0x00ba:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.getMessageDetails(com.unionpay.tsmservice.request.GetMessageDetailsRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    public int getPubKey(int i2, String[] strArr) throws RemoteException {
        if (strArr == null || strArr.length == 0) {
            return -3;
        }
        if (!c()) {
            return -8;
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
    public synchronized int getSEAppList(com.unionpay.tsmservice.request.GetSeAppListRequestParams r8, com.unionpay.tsmservice.ITsmCallback r9) throws android.os.RemoteException {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r9 != 0) goto L_0x0006
            r8 = -3
        L_0x0004:
            monitor-exit(r7)
            return r8
        L_0x0006:
            boolean r0 = r7.c()     // Catch:{ all -> 0x0097 }
            if (r0 != 0) goto L_0x000e
            r8 = -8
            goto L_0x0004
        L_0x000e:
            com.unionpay.tsmservice.ITsmService r0 = r7.e     // Catch:{ all -> 0x0097 }
            if (r0 == 0) goto L_0x0094
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x0097 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x0097 }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x0097 }
            r1 = 3
            if (r0 == 0) goto L_0x008e
            com.unionpay.tsmservice.request.GetSeAppListRequestParams r0 = new com.unionpay.tsmservice.request.GetSeAppListRequestParams     // Catch:{ all -> 0x0097 }
            r0.<init>()     // Catch:{ all -> 0x0097 }
            java.lang.String r2 = ""
            if (r8 == 0) goto L_0x002c
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x0097 }
        L_0x002c:
            boolean r3 = r7.h     // Catch:{ all -> 0x0097 }
            if (r3 == 0) goto L_0x0038
            java.lang.String r2 = f(r2)     // Catch:{ all -> 0x0097 }
            java.lang.String r2 = r7.g(r2)     // Catch:{ all -> 0x0097 }
        L_0x0038:
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x0097 }
            if (r3 != 0) goto L_0x0045
            java.lang.String r2 = r7.b((java.lang.String) r2)     // Catch:{ all -> 0x0097 }
            r0.setReserve(r2)     // Catch:{ all -> 0x0097 }
        L_0x0045:
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.k     // Catch:{ all -> 0x0097 }
            int[] r3 = r7.Z     // Catch:{ all -> 0x0097 }
            r3 = r3[r1]     // Catch:{ all -> 0x0097 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x0097 }
            r2.put(r3, r9)     // Catch:{ all -> 0x0097 }
            com.unionpay.tsmservice.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x0084 }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x0084 }
            int[] r4 = r7.Z     // Catch:{ Exception -> 0x0084 }
            r5 = r4[r1]     // Catch:{ Exception -> 0x0084 }
            int r6 = r5 + 1
            r4[r1] = r6     // Catch:{ Exception -> 0x0084 }
            r4 = 0
            r3.<init>(r7, r1, r5, r4)     // Catch:{ Exception -> 0x0084 }
            int r0 = r2.getSEAppList(r0, r3)     // Catch:{ Exception -> 0x0084 }
            if (r0 == 0) goto L_0x0079
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.k     // Catch:{ all -> 0x0097 }
            int[] r3 = r7.Z     // Catch:{ all -> 0x0097 }
            r4 = r3[r1]     // Catch:{ all -> 0x0097 }
            int r4 = r4 + -1
            r3[r1] = r4     // Catch:{ all -> 0x0097 }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x0097 }
            r2.remove(r3)     // Catch:{ all -> 0x0097 }
        L_0x0079:
            r2 = -2
            if (r2 != r0) goto L_0x0082
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x0097 }
            monitor-exit(r7)
            return r8
        L_0x0082:
            monitor-exit(r7)
            return r0
        L_0x0084:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x0097 }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x0097 }
            r8.<init>()     // Catch:{ all -> 0x0097 }
            throw r8     // Catch:{ all -> 0x0097 }
        L_0x008e:
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x0097 }
            monitor-exit(r7)
            return r8
        L_0x0094:
            r8 = -1
            goto L_0x0004
        L_0x0097:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.getSEAppList(com.unionpay.tsmservice.request.GetSeAppListRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00b1, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000f, code lost:
        return r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int getSMSAuthCode(com.unionpay.tsmservice.request.GetSMSAuthCodeRequestParams r7, com.unionpay.tsmservice.ITsmCallback r8) throws android.os.RemoteException {
        /*
            r6 = this;
            monitor-enter(r6)
            if (r7 == 0) goto L_0x00c8
            if (r8 != 0) goto L_0x0007
            goto L_0x00c8
        L_0x0007:
            boolean r0 = r6.c()     // Catch:{ all -> 0x00c5 }
            if (r0 != 0) goto L_0x0010
            r7 = -8
        L_0x000e:
            monitor-exit(r6)
            return r7
        L_0x0010:
            com.unionpay.tsmservice.ITsmService r0 = r6.e     // Catch:{ all -> 0x00c5 }
            if (r0 == 0) goto L_0x00c2
            android.content.Context r0 = r6.c     // Catch:{ all -> 0x00c5 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00c5 }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x00c5 }
            r1 = 11
            if (r0 == 0) goto L_0x00bc
            com.unionpay.tsmservice.request.GetSMSAuthCodeRequestParams r0 = new com.unionpay.tsmservice.request.GetSMSAuthCodeRequestParams     // Catch:{ all -> 0x00c5 }
            r0.<init>()     // Catch:{ all -> 0x00c5 }
            java.lang.String r2 = r7.getReserve()     // Catch:{ all -> 0x00c5 }
            com.unionpay.tsmservice.AppID r3 = r7.getAppID()     // Catch:{ all -> 0x00c5 }
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00c5 }
            if (r4 != 0) goto L_0x003c
            java.lang.String r2 = r6.b((java.lang.String) r2)     // Catch:{ all -> 0x00c5 }
            r0.setReserve(r2)     // Catch:{ all -> 0x00c5 }
        L_0x003c:
            if (r3 == 0) goto L_0x0062
            java.lang.String r2 = r3.getAppAid()     // Catch:{ all -> 0x00c5 }
            java.lang.String r3 = r3.getAppVersion()     // Catch:{ all -> 0x00c5 }
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00c5 }
            if (r4 != 0) goto L_0x0062
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x00c5 }
            if (r4 != 0) goto L_0x0062
            com.unionpay.tsmservice.AppID r4 = new com.unionpay.tsmservice.AppID     // Catch:{ all -> 0x00c5 }
            java.lang.String r2 = r6.b((java.lang.String) r2)     // Catch:{ all -> 0x00c5 }
            java.lang.String r3 = r6.b((java.lang.String) r3)     // Catch:{ all -> 0x00c5 }
            r4.<init>(r2, r3)     // Catch:{ all -> 0x00c5 }
            r0.setAppID(r4)     // Catch:{ all -> 0x00c5 }
        L_0x0062:
            java.lang.String r2 = r7.getPan()     // Catch:{ all -> 0x00c5 }
            java.lang.String r3 = r7.getMsisdn()     // Catch:{ all -> 0x00c5 }
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00c5 }
            if (r4 != 0) goto L_0x0077
            java.lang.String r2 = r6.b((java.lang.String) r2)     // Catch:{ all -> 0x00c5 }
            r0.setPan(r2)     // Catch:{ all -> 0x00c5 }
        L_0x0077:
            boolean r2 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x00c5 }
            if (r2 != 0) goto L_0x0084
            java.lang.String r2 = r6.b((java.lang.String) r3)     // Catch:{ all -> 0x00c5 }
            r0.setMsisdn(r2)     // Catch:{ all -> 0x00c5 }
        L_0x0084:
            com.unionpay.tsmservice.ITsmService r2 = r6.e     // Catch:{ Exception -> 0x00b2 }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x00b2 }
            int[] r4 = r6.Z     // Catch:{ Exception -> 0x00b2 }
            r4 = r4[r1]     // Catch:{ Exception -> 0x00b2 }
            r5 = 0
            r3.<init>(r6, r1, r4, r5)     // Catch:{ Exception -> 0x00b2 }
            int r0 = r2.getSMSAuthCode(r0, r3)     // Catch:{ Exception -> 0x00b2 }
            r2 = -2
            if (r2 != r0) goto L_0x009d
            int r7 = a(r1, r7, r8)     // Catch:{ all -> 0x00c5 }
            monitor-exit(r6)
            return r7
        L_0x009d:
            if (r0 != 0) goto L_0x00b0
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r7 = r6.r     // Catch:{ all -> 0x00c5 }
            int[] r2 = r6.Z     // Catch:{ all -> 0x00c5 }
            r3 = r2[r1]     // Catch:{ all -> 0x00c5 }
            int r4 = r3 + 1
            r2[r1] = r4     // Catch:{ all -> 0x00c5 }
            java.lang.String r1 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00c5 }
            r7.put(r1, r8)     // Catch:{ all -> 0x00c5 }
        L_0x00b0:
            monitor-exit(r6)
            return r0
        L_0x00b2:
            r7 = move-exception
            r7.printStackTrace()     // Catch:{ all -> 0x00c5 }
            android.os.RemoteException r7 = new android.os.RemoteException     // Catch:{ all -> 0x00c5 }
            r7.<init>()     // Catch:{ all -> 0x00c5 }
            throw r7     // Catch:{ all -> 0x00c5 }
        L_0x00bc:
            int r7 = a(r1, r7, r8)     // Catch:{ all -> 0x00c5 }
            monitor-exit(r6)
            return r7
        L_0x00c2:
            r7 = -1
            goto L_0x000e
        L_0x00c5:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        L_0x00c8:
            r7 = -3
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.getSMSAuthCode(com.unionpay.tsmservice.request.GetSMSAuthCodeRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0005, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int getSeId(com.unionpay.tsmservice.request.GetSeIdRequestParams r8, com.unionpay.tsmservice.ITsmCallback r9) throws android.os.RemoteException {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r9 != 0) goto L_0x0006
            r8 = -3
        L_0x0004:
            monitor-exit(r7)
            return r8
        L_0x0006:
            boolean r0 = r7.c()     // Catch:{ all -> 0x0098 }
            if (r0 != 0) goto L_0x000e
            r8 = -8
            goto L_0x0004
        L_0x000e:
            com.unionpay.tsmservice.ITsmService r0 = r7.e     // Catch:{ all -> 0x0098 }
            if (r0 == 0) goto L_0x0095
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x0098 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x0098 }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x0098 }
            r1 = 12
            if (r0 == 0) goto L_0x008f
            com.unionpay.tsmservice.request.GetSeIdRequestParams r0 = new com.unionpay.tsmservice.request.GetSeIdRequestParams     // Catch:{ all -> 0x0098 }
            r0.<init>()     // Catch:{ all -> 0x0098 }
            java.lang.String r2 = ""
            if (r8 == 0) goto L_0x002d
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x0098 }
        L_0x002d:
            boolean r3 = r7.h     // Catch:{ all -> 0x0098 }
            if (r3 == 0) goto L_0x0039
            java.lang.String r2 = f(r2)     // Catch:{ all -> 0x0098 }
            java.lang.String r2 = r7.g(r2)     // Catch:{ all -> 0x0098 }
        L_0x0039:
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x0098 }
            if (r3 != 0) goto L_0x0046
            java.lang.String r2 = r7.b((java.lang.String) r2)     // Catch:{ all -> 0x0098 }
            r0.setReserve(r2)     // Catch:{ all -> 0x0098 }
        L_0x0046:
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.z     // Catch:{ all -> 0x0098 }
            int[] r3 = r7.Z     // Catch:{ all -> 0x0098 }
            r3 = r3[r1]     // Catch:{ all -> 0x0098 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x0098 }
            r2.put(r3, r9)     // Catch:{ all -> 0x0098 }
            com.unionpay.tsmservice.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x0085 }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x0085 }
            int[] r4 = r7.Z     // Catch:{ Exception -> 0x0085 }
            r5 = r4[r1]     // Catch:{ Exception -> 0x0085 }
            int r6 = r5 + 1
            r4[r1] = r6     // Catch:{ Exception -> 0x0085 }
            r4 = 0
            r3.<init>(r7, r1, r5, r4)     // Catch:{ Exception -> 0x0085 }
            int r0 = r2.getSEId(r0, r3)     // Catch:{ Exception -> 0x0085 }
            if (r0 == 0) goto L_0x007a
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.z     // Catch:{ all -> 0x0098 }
            int[] r3 = r7.Z     // Catch:{ all -> 0x0098 }
            r4 = r3[r1]     // Catch:{ all -> 0x0098 }
            int r4 = r4 + -1
            r3[r1] = r4     // Catch:{ all -> 0x0098 }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x0098 }
            r2.remove(r3)     // Catch:{ all -> 0x0098 }
        L_0x007a:
            r2 = -2
            if (r2 != r0) goto L_0x0083
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x0098 }
            monitor-exit(r7)
            return r8
        L_0x0083:
            monitor-exit(r7)
            return r0
        L_0x0085:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x0098 }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x0098 }
            r8.<init>()     // Catch:{ all -> 0x0098 }
            throw r8     // Catch:{ all -> 0x0098 }
        L_0x008f:
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x0098 }
            monitor-exit(r7)
            return r8
        L_0x0095:
            r8 = -1
            goto L_0x0004
        L_0x0098:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.getSeId(com.unionpay.tsmservice.request.GetSeIdRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00a0, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000f, code lost:
        return r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int getTransElements(com.unionpay.tsmservice.request.GetTransElementsRequestParams r7, com.unionpay.tsmservice.ITsmCallback r8) throws android.os.RemoteException {
        /*
            r6 = this;
            monitor-enter(r6)
            if (r7 == 0) goto L_0x00b7
            if (r8 != 0) goto L_0x0007
            goto L_0x00b7
        L_0x0007:
            boolean r0 = r6.c()     // Catch:{ all -> 0x00b4 }
            if (r0 != 0) goto L_0x0010
            r7 = -8
        L_0x000e:
            monitor-exit(r6)
            return r7
        L_0x0010:
            com.unionpay.tsmservice.ITsmService r0 = r6.e     // Catch:{ all -> 0x00b4 }
            if (r0 == 0) goto L_0x00b1
            android.content.Context r0 = r6.c     // Catch:{ all -> 0x00b4 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00b4 }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x00b4 }
            r1 = 9
            if (r0 == 0) goto L_0x00ab
            com.unionpay.tsmservice.request.GetTransElementsRequestParams r0 = new com.unionpay.tsmservice.request.GetTransElementsRequestParams     // Catch:{ all -> 0x00b4 }
            r0.<init>()     // Catch:{ all -> 0x00b4 }
            java.lang.String r2 = r7.getReserve()     // Catch:{ all -> 0x00b4 }
            com.unionpay.tsmservice.AppID r3 = r7.getAppID()     // Catch:{ all -> 0x00b4 }
            java.lang.String r4 = r7.getTransType()     // Catch:{ all -> 0x00b4 }
            boolean r5 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00b4 }
            if (r5 != 0) goto L_0x0040
            java.lang.String r2 = r6.b((java.lang.String) r2)     // Catch:{ all -> 0x00b4 }
            r0.setReserve(r2)     // Catch:{ all -> 0x00b4 }
        L_0x0040:
            if (r3 == 0) goto L_0x0066
            java.lang.String r2 = r3.getAppAid()     // Catch:{ all -> 0x00b4 }
            java.lang.String r3 = r3.getAppVersion()     // Catch:{ all -> 0x00b4 }
            boolean r5 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00b4 }
            if (r5 != 0) goto L_0x0066
            boolean r5 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x00b4 }
            if (r5 != 0) goto L_0x0066
            com.unionpay.tsmservice.AppID r5 = new com.unionpay.tsmservice.AppID     // Catch:{ all -> 0x00b4 }
            java.lang.String r2 = r6.b((java.lang.String) r2)     // Catch:{ all -> 0x00b4 }
            java.lang.String r3 = r6.b((java.lang.String) r3)     // Catch:{ all -> 0x00b4 }
            r5.<init>(r2, r3)     // Catch:{ all -> 0x00b4 }
            r0.setAppID(r5)     // Catch:{ all -> 0x00b4 }
        L_0x0066:
            boolean r2 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x00b4 }
            if (r2 != 0) goto L_0x0073
            java.lang.String r2 = r6.b((java.lang.String) r4)     // Catch:{ all -> 0x00b4 }
            r0.setTransType(r2)     // Catch:{ all -> 0x00b4 }
        L_0x0073:
            com.unionpay.tsmservice.ITsmService r2 = r6.e     // Catch:{ Exception -> 0x00a1 }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x00a1 }
            int[] r4 = r6.Z     // Catch:{ Exception -> 0x00a1 }
            r4 = r4[r1]     // Catch:{ Exception -> 0x00a1 }
            r5 = 0
            r3.<init>(r6, r1, r4, r5)     // Catch:{ Exception -> 0x00a1 }
            int r0 = r2.getTransElements(r0, r3)     // Catch:{ Exception -> 0x00a1 }
            r2 = -2
            if (r2 != r0) goto L_0x008c
            int r7 = a(r1, r7, r8)     // Catch:{ all -> 0x00b4 }
            monitor-exit(r6)
            return r7
        L_0x008c:
            if (r0 != 0) goto L_0x009f
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r7 = r6.o     // Catch:{ all -> 0x00b4 }
            int[] r2 = r6.Z     // Catch:{ all -> 0x00b4 }
            r3 = r2[r1]     // Catch:{ all -> 0x00b4 }
            int r4 = r3 + 1
            r2[r1] = r4     // Catch:{ all -> 0x00b4 }
            java.lang.String r1 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00b4 }
            r7.put(r1, r8)     // Catch:{ all -> 0x00b4 }
        L_0x009f:
            monitor-exit(r6)
            return r0
        L_0x00a1:
            r7 = move-exception
            r7.printStackTrace()     // Catch:{ all -> 0x00b4 }
            android.os.RemoteException r7 = new android.os.RemoteException     // Catch:{ all -> 0x00b4 }
            r7.<init>()     // Catch:{ all -> 0x00b4 }
            throw r7     // Catch:{ all -> 0x00b4 }
        L_0x00ab:
            int r7 = a(r1, r7, r8)     // Catch:{ all -> 0x00b4 }
            monitor-exit(r6)
            return r7
        L_0x00b1:
            r7 = -1
            goto L_0x000e
        L_0x00b4:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        L_0x00b7:
            r7 = -3
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.getTransElements(com.unionpay.tsmservice.request.GetTransElementsRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x008f, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000f, code lost:
        return r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int getTransRecord(com.unionpay.tsmservice.request.GetTransRecordRequestParams r7, com.unionpay.tsmservice.ITsmCallback r8) throws android.os.RemoteException {
        /*
            r6 = this;
            monitor-enter(r6)
            if (r7 == 0) goto L_0x00a6
            if (r8 != 0) goto L_0x0007
            goto L_0x00a6
        L_0x0007:
            boolean r0 = r6.c()     // Catch:{ all -> 0x00a3 }
            if (r0 != 0) goto L_0x0010
            r7 = -8
        L_0x000e:
            monitor-exit(r6)
            return r7
        L_0x0010:
            com.unionpay.tsmservice.ITsmService r0 = r6.e     // Catch:{ all -> 0x00a3 }
            if (r0 == 0) goto L_0x00a0
            android.content.Context r0 = r6.c     // Catch:{ all -> 0x00a3 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00a3 }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x00a3 }
            r1 = 10
            if (r0 == 0) goto L_0x009a
            com.unionpay.tsmservice.request.GetTransRecordRequestParams r0 = new com.unionpay.tsmservice.request.GetTransRecordRequestParams     // Catch:{ all -> 0x00a3 }
            r0.<init>()     // Catch:{ all -> 0x00a3 }
            java.lang.String r2 = r7.getReserve()     // Catch:{ all -> 0x00a3 }
            com.unionpay.tsmservice.AppID r3 = r7.getAppID()     // Catch:{ all -> 0x00a3 }
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00a3 }
            if (r4 != 0) goto L_0x003c
            java.lang.String r2 = r6.b((java.lang.String) r2)     // Catch:{ all -> 0x00a3 }
            r0.setReserve(r2)     // Catch:{ all -> 0x00a3 }
        L_0x003c:
            if (r3 == 0) goto L_0x0062
            java.lang.String r2 = r3.getAppAid()     // Catch:{ all -> 0x00a3 }
            java.lang.String r3 = r3.getAppVersion()     // Catch:{ all -> 0x00a3 }
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00a3 }
            if (r4 != 0) goto L_0x0062
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x00a3 }
            if (r4 != 0) goto L_0x0062
            com.unionpay.tsmservice.AppID r4 = new com.unionpay.tsmservice.AppID     // Catch:{ all -> 0x00a3 }
            java.lang.String r2 = r6.b((java.lang.String) r2)     // Catch:{ all -> 0x00a3 }
            java.lang.String r3 = r6.b((java.lang.String) r3)     // Catch:{ all -> 0x00a3 }
            r4.<init>(r2, r3)     // Catch:{ all -> 0x00a3 }
            r0.setAppID(r4)     // Catch:{ all -> 0x00a3 }
        L_0x0062:
            com.unionpay.tsmservice.ITsmService r2 = r6.e     // Catch:{ Exception -> 0x0090 }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x0090 }
            int[] r4 = r6.Z     // Catch:{ Exception -> 0x0090 }
            r4 = r4[r1]     // Catch:{ Exception -> 0x0090 }
            r5 = 0
            r3.<init>(r6, r1, r4, r5)     // Catch:{ Exception -> 0x0090 }
            int r0 = r2.getTransRecord(r0, r3)     // Catch:{ Exception -> 0x0090 }
            r2 = -2
            if (r2 != r0) goto L_0x007b
            int r7 = a(r1, r7, r8)     // Catch:{ all -> 0x00a3 }
            monitor-exit(r6)
            return r7
        L_0x007b:
            if (r0 != 0) goto L_0x008e
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r7 = r6.t     // Catch:{ all -> 0x00a3 }
            int[] r2 = r6.Z     // Catch:{ all -> 0x00a3 }
            r3 = r2[r1]     // Catch:{ all -> 0x00a3 }
            int r4 = r3 + 1
            r2[r1] = r4     // Catch:{ all -> 0x00a3 }
            java.lang.String r1 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00a3 }
            r7.put(r1, r8)     // Catch:{ all -> 0x00a3 }
        L_0x008e:
            monitor-exit(r6)
            return r0
        L_0x0090:
            r7 = move-exception
            r7.printStackTrace()     // Catch:{ all -> 0x00a3 }
            android.os.RemoteException r7 = new android.os.RemoteException     // Catch:{ all -> 0x00a3 }
            r7.<init>()     // Catch:{ all -> 0x00a3 }
            throw r7     // Catch:{ all -> 0x00a3 }
        L_0x009a:
            int r7 = a(r1, r7, r8)     // Catch:{ all -> 0x00a3 }
            monitor-exit(r6)
            return r7
        L_0x00a0:
            r7 = -1
            goto L_0x000e
        L_0x00a3:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        L_0x00a6:
            r7 = -3
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.getTransRecord(com.unionpay.tsmservice.request.GetTransRecordRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0005, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int getTransactionDetails(com.unionpay.tsmservice.request.GetTransactionDetailsRequestParams r8, com.unionpay.tsmservice.ITsmCallback r9) throws android.os.RemoteException {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r9 != 0) goto L_0x0006
            r8 = -3
        L_0x0004:
            monitor-exit(r7)
            return r8
        L_0x0006:
            java.lang.String r0 = "01.00.35"
            boolean r0 = r7.d((java.lang.String) r0)     // Catch:{ all -> 0x00ba }
            r1 = -8
            if (r0 != 0) goto L_0x0011
            monitor-exit(r7)
            return r1
        L_0x0011:
            boolean r0 = r7.c()     // Catch:{ all -> 0x00ba }
            if (r0 != 0) goto L_0x0019
            monitor-exit(r7)
            return r1
        L_0x0019:
            com.unionpay.tsmservice.ITsmService r0 = r7.e     // Catch:{ all -> 0x00ba }
            if (r0 == 0) goto L_0x00b7
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x00ba }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00ba }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x00ba }
            r1 = 43
            if (r0 == 0) goto L_0x00b1
            com.unionpay.tsmservice.request.GetTransactionDetailsRequestParams r0 = new com.unionpay.tsmservice.request.GetTransactionDetailsRequestParams     // Catch:{ all -> 0x00ba }
            r0.<init>()     // Catch:{ all -> 0x00ba }
            java.lang.String r2 = ""
            if (r8 == 0) goto L_0x004f
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x00ba }
            android.os.Bundle r3 = r8.getParams()     // Catch:{ all -> 0x00ba }
            if (r3 == 0) goto L_0x004f
            android.os.Bundle r4 = new android.os.Bundle     // Catch:{ all -> 0x00ba }
            r4.<init>()     // Catch:{ all -> 0x00ba }
            java.lang.String r5 = "encryptData"
            java.lang.String r3 = r7.a((android.os.Bundle) r3)     // Catch:{ all -> 0x00ba }
            r4.putString(r5, r3)     // Catch:{ all -> 0x00ba }
            r0.setParams(r4)     // Catch:{ all -> 0x00ba }
        L_0x004f:
            boolean r3 = r7.h     // Catch:{ all -> 0x00ba }
            if (r3 == 0) goto L_0x005b
            java.lang.String r2 = f(r2)     // Catch:{ all -> 0x00ba }
            java.lang.String r2 = r7.g(r2)     // Catch:{ all -> 0x00ba }
        L_0x005b:
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00ba }
            if (r3 != 0) goto L_0x0068
            java.lang.String r2 = r7.b((java.lang.String) r2)     // Catch:{ all -> 0x00ba }
            r0.setReserve(r2)     // Catch:{ all -> 0x00ba }
        L_0x0068:
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.U     // Catch:{ all -> 0x00ba }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00ba }
            r3 = r3[r1]     // Catch:{ all -> 0x00ba }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00ba }
            r2.put(r3, r9)     // Catch:{ all -> 0x00ba }
            com.unionpay.tsmservice.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x00a7 }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x00a7 }
            int[] r4 = r7.Z     // Catch:{ Exception -> 0x00a7 }
            r5 = r4[r1]     // Catch:{ Exception -> 0x00a7 }
            int r6 = r5 + 1
            r4[r1] = r6     // Catch:{ Exception -> 0x00a7 }
            r4 = 0
            r3.<init>(r7, r1, r5, r4)     // Catch:{ Exception -> 0x00a7 }
            int r0 = r2.getTransactionDetails(r0, r3)     // Catch:{ Exception -> 0x00a7 }
            if (r0 == 0) goto L_0x009c
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.U     // Catch:{ all -> 0x00ba }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00ba }
            r4 = r3[r1]     // Catch:{ all -> 0x00ba }
            int r4 = r4 + -1
            r3[r1] = r4     // Catch:{ all -> 0x00ba }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x00ba }
            r2.remove(r3)     // Catch:{ all -> 0x00ba }
        L_0x009c:
            r2 = -2
            if (r2 != r0) goto L_0x00a5
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x00ba }
            monitor-exit(r7)
            return r8
        L_0x00a5:
            monitor-exit(r7)
            return r0
        L_0x00a7:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x00ba }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x00ba }
            r8.<init>()     // Catch:{ all -> 0x00ba }
            throw r8     // Catch:{ all -> 0x00ba }
        L_0x00b1:
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x00ba }
            monitor-exit(r7)
            return r8
        L_0x00b7:
            r8 = -1
            goto L_0x0004
        L_0x00ba:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.getTransactionDetails(com.unionpay.tsmservice.request.GetTransactionDetailsRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0005, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int getVendorPayStatus(com.unionpay.tsmservice.request.GetVendorPayStatusRequestParams r8, com.unionpay.tsmservice.ITsmCallback r9) throws android.os.RemoteException {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r9 != 0) goto L_0x0006
            r8 = -3
        L_0x0004:
            monitor-exit(r7)
            return r8
        L_0x0006:
            java.lang.String r0 = "01.00.20"
            boolean r0 = r7.d((java.lang.String) r0)     // Catch:{ all -> 0x00a3 }
            r1 = -8
            if (r0 != 0) goto L_0x0011
            monitor-exit(r7)
            return r1
        L_0x0011:
            boolean r0 = r7.c()     // Catch:{ all -> 0x00a3 }
            if (r0 != 0) goto L_0x0019
            monitor-exit(r7)
            return r1
        L_0x0019:
            com.unionpay.tsmservice.ITsmService r0 = r7.e     // Catch:{ all -> 0x00a3 }
            if (r0 == 0) goto L_0x00a0
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x00a3 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00a3 }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x00a3 }
            r1 = 36
            if (r0 == 0) goto L_0x009a
            com.unionpay.tsmservice.request.GetVendorPayStatusRequestParams r0 = new com.unionpay.tsmservice.request.GetVendorPayStatusRequestParams     // Catch:{ all -> 0x00a3 }
            r0.<init>()     // Catch:{ all -> 0x00a3 }
            java.lang.String r2 = ""
            if (r8 == 0) goto L_0x0038
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x00a3 }
        L_0x0038:
            boolean r3 = r7.h     // Catch:{ all -> 0x00a3 }
            if (r3 == 0) goto L_0x0044
            java.lang.String r2 = f(r2)     // Catch:{ all -> 0x00a3 }
            java.lang.String r2 = r7.g(r2)     // Catch:{ all -> 0x00a3 }
        L_0x0044:
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00a3 }
            if (r3 != 0) goto L_0x0051
            java.lang.String r2 = r7.b((java.lang.String) r2)     // Catch:{ all -> 0x00a3 }
            r0.setReserve(r2)     // Catch:{ all -> 0x00a3 }
        L_0x0051:
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.N     // Catch:{ all -> 0x00a3 }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00a3 }
            r3 = r3[r1]     // Catch:{ all -> 0x00a3 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00a3 }
            r2.put(r3, r9)     // Catch:{ all -> 0x00a3 }
            com.unionpay.tsmservice.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x0090 }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x0090 }
            int[] r4 = r7.Z     // Catch:{ Exception -> 0x0090 }
            r5 = r4[r1]     // Catch:{ Exception -> 0x0090 }
            int r6 = r5 + 1
            r4[r1] = r6     // Catch:{ Exception -> 0x0090 }
            r4 = 0
            r3.<init>(r7, r1, r5, r4)     // Catch:{ Exception -> 0x0090 }
            int r0 = r2.getVendorPayStatus(r0, r3)     // Catch:{ Exception -> 0x0090 }
            if (r0 == 0) goto L_0x0085
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.N     // Catch:{ all -> 0x00a3 }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00a3 }
            r4 = r3[r1]     // Catch:{ all -> 0x00a3 }
            int r4 = r4 + -1
            r3[r1] = r4     // Catch:{ all -> 0x00a3 }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x00a3 }
            r2.remove(r3)     // Catch:{ all -> 0x00a3 }
        L_0x0085:
            r2 = -2
            if (r2 != r0) goto L_0x008e
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x00a3 }
            monitor-exit(r7)
            return r8
        L_0x008e:
            monitor-exit(r7)
            return r0
        L_0x0090:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x00a3 }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x00a3 }
            r8.<init>()     // Catch:{ all -> 0x00a3 }
            throw r8     // Catch:{ all -> 0x00a3 }
        L_0x009a:
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x00a3 }
            monitor-exit(r7)
            return r8
        L_0x00a0:
            r8 = -1
            goto L_0x0004
        L_0x00a3:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.getVendorPayStatus(com.unionpay.tsmservice.request.GetVendorPayStatusRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001c, code lost:
        return r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0079, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0090, code lost:
        return -3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int hideAppApply(com.unionpay.tsmservice.request.HideAppApplyRequestParams r7, com.unionpay.tsmservice.ITsmCallback r8) throws android.os.RemoteException {
        /*
            r6 = this;
            monitor-enter(r6)
            r0 = -3
            if (r7 == 0) goto L_0x008f
            if (r8 != 0) goto L_0x0008
            goto L_0x008f
        L_0x0008:
            java.lang.String r1 = r7.getApplyId()     // Catch:{ all -> 0x008c }
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x008c }
            if (r2 == 0) goto L_0x0014
            monitor-exit(r6)
            return r0
        L_0x0014:
            boolean r0 = r6.c()     // Catch:{ all -> 0x008c }
            if (r0 != 0) goto L_0x001d
            r7 = -8
        L_0x001b:
            monitor-exit(r6)
            return r7
        L_0x001d:
            com.unionpay.tsmservice.ITsmService r0 = r6.e     // Catch:{ all -> 0x008c }
            if (r0 == 0) goto L_0x008a
            android.content.Context r0 = r6.c     // Catch:{ all -> 0x008c }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x008c }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x008c }
            r2 = 24
            if (r0 == 0) goto L_0x0084
            java.lang.String r0 = r6.b((java.lang.String) r1)     // Catch:{ all -> 0x008c }
            com.unionpay.tsmservice.request.HideAppApplyRequestParams r1 = new com.unionpay.tsmservice.request.HideAppApplyRequestParams     // Catch:{ all -> 0x008c }
            r1.<init>()     // Catch:{ all -> 0x008c }
            r1.setApplyId(r0)     // Catch:{ all -> 0x008c }
            java.lang.String r0 = r7.getReserve()     // Catch:{ all -> 0x008c }
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x008c }
            if (r3 != 0) goto L_0x004c
            java.lang.String r0 = r6.b((java.lang.String) r0)     // Catch:{ all -> 0x008c }
            r1.setReserve(r0)     // Catch:{ all -> 0x008c }
        L_0x004c:
            com.unionpay.tsmservice.ITsmService r0 = r6.e     // Catch:{ Exception -> 0x007a }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x007a }
            int[] r4 = r6.Z     // Catch:{ Exception -> 0x007a }
            r4 = r4[r2]     // Catch:{ Exception -> 0x007a }
            r5 = 0
            r3.<init>(r6, r2, r4, r5)     // Catch:{ Exception -> 0x007a }
            int r0 = r0.hideAppApply(r1, r3)     // Catch:{ Exception -> 0x007a }
            r1 = -2
            if (r1 != r0) goto L_0x0065
            int r7 = a(r2, r7, r8)     // Catch:{ all -> 0x008c }
            monitor-exit(r6)
            return r7
        L_0x0065:
            if (r0 != 0) goto L_0x0078
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r7 = r6.G     // Catch:{ all -> 0x008c }
            int[] r1 = r6.Z     // Catch:{ all -> 0x008c }
            r3 = r1[r2]     // Catch:{ all -> 0x008c }
            int r4 = r3 + 1
            r1[r2] = r4     // Catch:{ all -> 0x008c }
            java.lang.String r1 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x008c }
            r7.put(r1, r8)     // Catch:{ all -> 0x008c }
        L_0x0078:
            monitor-exit(r6)
            return r0
        L_0x007a:
            r7 = move-exception
            r7.printStackTrace()     // Catch:{ all -> 0x008c }
            android.os.RemoteException r7 = new android.os.RemoteException     // Catch:{ all -> 0x008c }
            r7.<init>()     // Catch:{ all -> 0x008c }
            throw r7     // Catch:{ all -> 0x008c }
        L_0x0084:
            int r7 = a(r2, r7, r8)     // Catch:{ all -> 0x008c }
            monitor-exit(r6)
            return r7
        L_0x008a:
            r7 = -1
            goto L_0x001b
        L_0x008c:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        L_0x008f:
            monitor-exit(r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.hideAppApply(com.unionpay.tsmservice.request.HideAppApplyRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0056, code lost:
        throw new android.os.RemoteException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0068, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0071, code lost:
        throw new android.os.RemoteException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0009, code lost:
        return r0;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:20:0x0046, B:26:0x0057] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int hideKeyboard() throws android.os.RemoteException {
        /*
            r6 = this;
            monitor-enter(r6)
            boolean r0 = r6.c()     // Catch:{ all -> 0x007a }
            if (r0 != 0) goto L_0x000a
            r0 = -8
        L_0x0008:
            monitor-exit(r6)
            return r0
        L_0x000a:
            com.unionpay.tsmservice.ITsmService r0 = r6.e     // Catch:{ all -> 0x007a }
            if (r0 == 0) goto L_0x0078
            android.content.Context r0 = r6.c     // Catch:{ all -> 0x007a }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x007a }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x007a }
            r1 = 0
            r2 = 34
            r3 = 0
            if (r0 == 0) goto L_0x0072
            java.lang.String r0 = "01.00.24"
            boolean r0 = r6.d((java.lang.String) r0)     // Catch:{ all -> 0x007a }
            if (r0 == 0) goto L_0x0057
            com.unionpay.tsmservice.request.HideSafetyKeyboardRequestParams r0 = new com.unionpay.tsmservice.request.HideSafetyKeyboardRequestParams     // Catch:{ all -> 0x007a }
            r0.<init>()     // Catch:{ all -> 0x007a }
            java.lang.String r4 = ""
            boolean r5 = r6.h     // Catch:{ all -> 0x007a }
            if (r5 == 0) goto L_0x0039
            java.lang.String r4 = f(r4)     // Catch:{ all -> 0x007a }
            java.lang.String r4 = r6.g(r4)     // Catch:{ all -> 0x007a }
        L_0x0039:
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x007a }
            if (r5 != 0) goto L_0x0046
            java.lang.String r4 = r6.b((java.lang.String) r4)     // Catch:{ all -> 0x007a }
            r0.setReserve(r4)     // Catch:{ all -> 0x007a }
        L_0x0046:
            com.unionpay.tsmservice.ITsmService r4 = r6.e     // Catch:{ Exception -> 0x004d }
            int r0 = r4.hideSafetyKeyboard(r0)     // Catch:{ Exception -> 0x004d }
            goto L_0x005d
        L_0x004d:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x007a }
            android.os.RemoteException r0 = new android.os.RemoteException     // Catch:{ all -> 0x007a }
            r0.<init>()     // Catch:{ all -> 0x007a }
            throw r0     // Catch:{ all -> 0x007a }
        L_0x0057:
            com.unionpay.tsmservice.ITsmService r0 = r6.e     // Catch:{ Exception -> 0x0068 }
            int r0 = r0.hideKeyboard()     // Catch:{ Exception -> 0x0068 }
        L_0x005d:
            r4 = -2
            if (r4 != r0) goto L_0x0066
            int r0 = a((int) r2, (com.unionpay.tsmservice.request.SafetyKeyboardRequestParams) r3, (int) r1, (com.unionpay.tsmservice.OnSafetyKeyboardCallback) r3, (android.content.Context) r3)     // Catch:{ all -> 0x007a }
            monitor-exit(r6)
            return r0
        L_0x0066:
            monitor-exit(r6)
            return r0
        L_0x0068:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x007a }
            android.os.RemoteException r0 = new android.os.RemoteException     // Catch:{ all -> 0x007a }
            r0.<init>()     // Catch:{ all -> 0x007a }
            throw r0     // Catch:{ all -> 0x007a }
        L_0x0072:
            int r0 = a((int) r2, (com.unionpay.tsmservice.request.SafetyKeyboardRequestParams) r3, (int) r1, (com.unionpay.tsmservice.OnSafetyKeyboardCallback) r3, (android.content.Context) r3)     // Catch:{ all -> 0x007a }
            monitor-exit(r6)
            return r0
        L_0x0078:
            r0 = -1
            goto L_0x0008
        L_0x007a:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.hideKeyboard():int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0005, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int init(com.unionpay.tsmservice.request.InitRequestParams r8, com.unionpay.tsmservice.ITsmCallback r9) throws android.os.RemoteException {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r9 != 0) goto L_0x0006
            r8 = -3
        L_0x0004:
            monitor-exit(r7)
            return r8
        L_0x0006:
            boolean r0 = r7.c()     // Catch:{ all -> 0x00a7 }
            if (r0 != 0) goto L_0x000e
            r8 = -8
            goto L_0x0004
        L_0x000e:
            com.unionpay.tsmservice.ITsmService r0 = r7.e     // Catch:{ all -> 0x00a7 }
            if (r0 == 0) goto L_0x00a4
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x00a7 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00a7 }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x00a7 }
            r1 = 0
            if (r0 == 0) goto L_0x009e
            com.unionpay.tsmservice.request.InitRequestParams r0 = new com.unionpay.tsmservice.request.InitRequestParams     // Catch:{ all -> 0x00a7 }
            r0.<init>()     // Catch:{ all -> 0x00a7 }
            java.lang.String r2 = ""
            if (r8 == 0) goto L_0x003d
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x00a7 }
            java.lang.String r3 = r8.getSignature()     // Catch:{ all -> 0x00a7 }
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x00a7 }
            if (r4 != 0) goto L_0x003d
            java.lang.String r3 = r7.b((java.lang.String) r3)     // Catch:{ all -> 0x00a7 }
            r0.setSignature(r3)     // Catch:{ all -> 0x00a7 }
        L_0x003d:
            boolean r3 = r7.h     // Catch:{ all -> 0x00a7 }
            if (r3 == 0) goto L_0x0049
            java.lang.String r2 = f(r2)     // Catch:{ all -> 0x00a7 }
            java.lang.String r2 = r7.g(r2)     // Catch:{ all -> 0x00a7 }
        L_0x0049:
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00a7 }
            if (r3 != 0) goto L_0x0056
            java.lang.String r2 = r7.b((java.lang.String) r2)     // Catch:{ all -> 0x00a7 }
            r0.setReserve(r2)     // Catch:{ all -> 0x00a7 }
        L_0x0056:
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.i     // Catch:{ all -> 0x00a7 }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00a7 }
            r3 = r3[r1]     // Catch:{ all -> 0x00a7 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00a7 }
            r2.put(r3, r9)     // Catch:{ all -> 0x00a7 }
            com.unionpay.tsmservice.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x0094 }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x0094 }
            int[] r4 = r7.Z     // Catch:{ Exception -> 0x0094 }
            r5 = r4[r1]     // Catch:{ Exception -> 0x0094 }
            int r6 = r5 + 1
            r4[r1] = r6     // Catch:{ Exception -> 0x0094 }
            r3.<init>(r7, r1, r5, r1)     // Catch:{ Exception -> 0x0094 }
            int r0 = r2.init(r0, r3)     // Catch:{ Exception -> 0x0094 }
            if (r0 == 0) goto L_0x0089
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.i     // Catch:{ all -> 0x00a7 }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00a7 }
            r4 = r3[r1]     // Catch:{ all -> 0x00a7 }
            int r4 = r4 + -1
            r3[r1] = r4     // Catch:{ all -> 0x00a7 }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x00a7 }
            r2.remove(r3)     // Catch:{ all -> 0x00a7 }
        L_0x0089:
            r2 = -2
            if (r2 != r0) goto L_0x0092
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x00a7 }
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
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x00a7 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.init(com.unionpay.tsmservice.request.InitRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    public boolean isConnected() {
        return this.f;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00d8, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int onlinePaymentVerify(com.unionpay.tsmservice.request.OnlinePaymentVerifyRequestParams r8, com.unionpay.tsmservice.ITsmCallback r9) throws android.os.RemoteException {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r8 == 0) goto L_0x00dc
            if (r9 != 0) goto L_0x0007
            goto L_0x00dc
        L_0x0007:
            java.lang.String r0 = "01.00.21"
            boolean r0 = r7.d((java.lang.String) r0)     // Catch:{ all -> 0x00d9 }
            r1 = -8
            if (r0 != 0) goto L_0x0012
            monitor-exit(r7)
            return r1
        L_0x0012:
            boolean r0 = r7.c()     // Catch:{ all -> 0x00d9 }
            if (r0 != 0) goto L_0x001a
            monitor-exit(r7)
            return r1
        L_0x001a:
            com.unionpay.tsmservice.ITsmService r0 = r7.e     // Catch:{ all -> 0x00d9 }
            if (r0 == 0) goto L_0x00d6
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x00d9 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00d9 }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x00d9 }
            r1 = 39
            if (r0 == 0) goto L_0x00d0
            com.unionpay.tsmservice.request.OnlinePaymentVerifyRequestParams r0 = new com.unionpay.tsmservice.request.OnlinePaymentVerifyRequestParams     // Catch:{ all -> 0x00d9 }
            r0.<init>()     // Catch:{ all -> 0x00d9 }
            android.os.Bundle r2 = r8.getResource()     // Catch:{ all -> 0x00d9 }
            if (r2 == 0) goto L_0x0048
            android.os.Bundle r3 = new android.os.Bundle     // Catch:{ all -> 0x00d9 }
            r3.<init>()     // Catch:{ all -> 0x00d9 }
            java.lang.String r4 = "encryptData"
            java.lang.String r2 = r7.a((android.os.Bundle) r2)     // Catch:{ all -> 0x00d9 }
            r3.putString(r4, r2)     // Catch:{ all -> 0x00d9 }
            r0.setResource(r3)     // Catch:{ all -> 0x00d9 }
        L_0x0048:
            java.lang.String r2 = r8.getOrderNumber()     // Catch:{ all -> 0x00d9 }
            java.lang.String r3 = r8.getAId()     // Catch:{ all -> 0x00d9 }
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00d9 }
            if (r4 != 0) goto L_0x005d
            java.lang.String r2 = r7.b((java.lang.String) r2)     // Catch:{ all -> 0x00d9 }
            r0.setOrderNumber(r2)     // Catch:{ all -> 0x00d9 }
        L_0x005d:
            boolean r2 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x00d9 }
            if (r2 != 0) goto L_0x006a
            java.lang.String r2 = r7.b((java.lang.String) r3)     // Catch:{ all -> 0x00d9 }
            r0.setAId(r2)     // Catch:{ all -> 0x00d9 }
        L_0x006a:
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x00d9 }
            boolean r3 = r7.h     // Catch:{ all -> 0x00d9 }
            if (r3 == 0) goto L_0x007a
            java.lang.String r2 = f(r2)     // Catch:{ all -> 0x00d9 }
            java.lang.String r2 = r7.g(r2)     // Catch:{ all -> 0x00d9 }
        L_0x007a:
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00d9 }
            if (r3 != 0) goto L_0x0087
            java.lang.String r2 = r7.b((java.lang.String) r2)     // Catch:{ all -> 0x00d9 }
            r0.setReserve(r2)     // Catch:{ all -> 0x00d9 }
        L_0x0087:
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.Q     // Catch:{ all -> 0x00d9 }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00d9 }
            r3 = r3[r1]     // Catch:{ all -> 0x00d9 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00d9 }
            r2.put(r3, r9)     // Catch:{ all -> 0x00d9 }
            com.unionpay.tsmservice.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x00c6 }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x00c6 }
            int[] r4 = r7.Z     // Catch:{ Exception -> 0x00c6 }
            r5 = r4[r1]     // Catch:{ Exception -> 0x00c6 }
            int r6 = r5 + 1
            r4[r1] = r6     // Catch:{ Exception -> 0x00c6 }
            r4 = 0
            r3.<init>(r7, r1, r5, r4)     // Catch:{ Exception -> 0x00c6 }
            int r0 = r2.onlinePaymentVerify(r0, r3)     // Catch:{ Exception -> 0x00c6 }
            if (r0 == 0) goto L_0x00bb
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.Q     // Catch:{ all -> 0x00d9 }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00d9 }
            r4 = r3[r1]     // Catch:{ all -> 0x00d9 }
            int r4 = r4 + -1
            r3[r1] = r4     // Catch:{ all -> 0x00d9 }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x00d9 }
            r2.remove(r3)     // Catch:{ all -> 0x00d9 }
        L_0x00bb:
            r2 = -2
            if (r2 != r0) goto L_0x00c4
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x00d9 }
            monitor-exit(r7)
            return r8
        L_0x00c4:
            monitor-exit(r7)
            return r0
        L_0x00c6:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x00d9 }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x00d9 }
            r8.<init>()     // Catch:{ all -> 0x00d9 }
            throw r8     // Catch:{ all -> 0x00d9 }
        L_0x00d0:
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x00d9 }
            monitor-exit(r7)
            return r8
        L_0x00d6:
            r8 = -1
        L_0x00d7:
            monitor-exit(r7)
            return r8
        L_0x00d9:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        L_0x00dc:
            r8 = -3
            goto L_0x00d7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.onlinePaymentVerify(com.unionpay.tsmservice.request.OnlinePaymentVerifyRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001c, code lost:
        return r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00ae, code lost:
        return -3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int openChannel(com.unionpay.tsmservice.request.OpenChannelRequestParams r8, com.unionpay.tsmservice.ITsmCallback r9) throws android.os.RemoteException {
        /*
            r7 = this;
            monitor-enter(r7)
            r0 = -3
            if (r8 == 0) goto L_0x00ad
            if (r9 != 0) goto L_0x0008
            goto L_0x00ad
        L_0x0008:
            java.lang.String r1 = r8.getAppAID()     // Catch:{ all -> 0x00aa }
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x00aa }
            if (r2 == 0) goto L_0x0014
            monitor-exit(r7)
            return r0
        L_0x0014:
            boolean r0 = r7.c()     // Catch:{ all -> 0x00aa }
            if (r0 != 0) goto L_0x001d
            r8 = -8
        L_0x001b:
            monitor-exit(r7)
            return r8
        L_0x001d:
            com.unionpay.tsmservice.ITsmService r0 = r7.e     // Catch:{ all -> 0x00aa }
            if (r0 == 0) goto L_0x00a7
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x00aa }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00aa }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x00aa }
            r2 = 20
            if (r0 == 0) goto L_0x00a1
            java.lang.String r0 = r7.b((java.lang.String) r1)     // Catch:{ all -> 0x00aa }
            com.unionpay.tsmservice.request.OpenChannelRequestParams r1 = new com.unionpay.tsmservice.request.OpenChannelRequestParams     // Catch:{ all -> 0x00aa }
            r1.<init>()     // Catch:{ all -> 0x00aa }
            r1.setAppAID(r0)     // Catch:{ all -> 0x00aa }
            java.lang.String r0 = r8.getReserve()     // Catch:{ all -> 0x00aa }
            boolean r3 = r7.h     // Catch:{ all -> 0x00aa }
            if (r3 == 0) goto L_0x004b
            java.lang.String r0 = f(r0)     // Catch:{ all -> 0x00aa }
            java.lang.String r0 = r7.g(r0)     // Catch:{ all -> 0x00aa }
        L_0x004b:
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x00aa }
            if (r3 != 0) goto L_0x0058
            java.lang.String r0 = r7.b((java.lang.String) r0)     // Catch:{ all -> 0x00aa }
            r1.setReserve(r0)     // Catch:{ all -> 0x00aa }
        L_0x0058:
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r0 = r7.w     // Catch:{ all -> 0x00aa }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00aa }
            r3 = r3[r2]     // Catch:{ all -> 0x00aa }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00aa }
            r0.put(r3, r9)     // Catch:{ all -> 0x00aa }
            com.unionpay.tsmservice.ITsmService r0 = r7.e     // Catch:{ Exception -> 0x0097 }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x0097 }
            int[] r4 = r7.Z     // Catch:{ Exception -> 0x0097 }
            r5 = r4[r2]     // Catch:{ Exception -> 0x0097 }
            int r6 = r5 + 1
            r4[r2] = r6     // Catch:{ Exception -> 0x0097 }
            r4 = 0
            r3.<init>(r7, r2, r5, r4)     // Catch:{ Exception -> 0x0097 }
            int r0 = r0.openChannel(r1, r3)     // Catch:{ Exception -> 0x0097 }
            if (r0 == 0) goto L_0x008c
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r1 = r7.w     // Catch:{ all -> 0x00aa }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00aa }
            r4 = r3[r2]     // Catch:{ all -> 0x00aa }
            int r4 = r4 + -1
            r3[r2] = r4     // Catch:{ all -> 0x00aa }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x00aa }
            r1.remove(r3)     // Catch:{ all -> 0x00aa }
        L_0x008c:
            r1 = -2
            if (r1 != r0) goto L_0x0095
            int r8 = a(r2, r8, r9)     // Catch:{ all -> 0x00aa }
            monitor-exit(r7)
            return r8
        L_0x0095:
            monitor-exit(r7)
            return r0
        L_0x0097:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x00aa }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x00aa }
            r8.<init>()     // Catch:{ all -> 0x00aa }
            throw r8     // Catch:{ all -> 0x00aa }
        L_0x00a1:
            int r8 = a(r2, r8, r9)     // Catch:{ all -> 0x00aa }
            monitor-exit(r7)
            return r8
        L_0x00a7:
            r8 = -1
            goto L_0x001b
        L_0x00aa:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        L_0x00ad:
            monitor-exit(r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.openChannel(com.unionpay.tsmservice.request.OpenChannelRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0005, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int preDownload(com.unionpay.tsmservice.request.PreDownloadRequestParams r8, com.unionpay.tsmservice.ITsmCallback r9, com.unionpay.tsmservice.ITsmProgressCallback r10) throws android.os.RemoteException {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r9 != 0) goto L_0x0006
            r8 = -3
        L_0x0004:
            monitor-exit(r7)
            return r8
        L_0x0006:
            java.lang.String r0 = "01.00.26"
            boolean r0 = r7.d((java.lang.String) r0)     // Catch:{ all -> 0x00ba }
            r1 = -8
            if (r0 != 0) goto L_0x0011
            monitor-exit(r7)
            return r1
        L_0x0011:
            boolean r0 = r7.c()     // Catch:{ all -> 0x00ba }
            if (r0 != 0) goto L_0x0019
            monitor-exit(r7)
            return r1
        L_0x0019:
            com.unionpay.tsmservice.ITsmService r0 = r7.e     // Catch:{ all -> 0x00ba }
            if (r0 == 0) goto L_0x00b7
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x00ba }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00ba }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x00ba }
            r1 = 40
            if (r0 == 0) goto L_0x00b1
            com.unionpay.tsmservice.request.PreDownloadRequestParams r0 = new com.unionpay.tsmservice.request.PreDownloadRequestParams     // Catch:{ all -> 0x00ba }
            r0.<init>()     // Catch:{ all -> 0x00ba }
            java.lang.String r2 = ""
            if (r8 == 0) goto L_0x004f
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x00ba }
            android.os.Bundle r3 = r8.getParams()     // Catch:{ all -> 0x00ba }
            if (r3 == 0) goto L_0x004f
            android.os.Bundle r4 = new android.os.Bundle     // Catch:{ all -> 0x00ba }
            r4.<init>()     // Catch:{ all -> 0x00ba }
            java.lang.String r5 = "encryptData"
            java.lang.String r3 = r7.a((android.os.Bundle) r3)     // Catch:{ all -> 0x00ba }
            r4.putString(r5, r3)     // Catch:{ all -> 0x00ba }
            r0.setParams(r4)     // Catch:{ all -> 0x00ba }
        L_0x004f:
            boolean r3 = r7.h     // Catch:{ all -> 0x00ba }
            if (r3 == 0) goto L_0x005b
            java.lang.String r2 = f(r2)     // Catch:{ all -> 0x00ba }
            java.lang.String r2 = r7.g(r2)     // Catch:{ all -> 0x00ba }
        L_0x005b:
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00ba }
            if (r3 != 0) goto L_0x0068
            java.lang.String r2 = r7.b((java.lang.String) r2)     // Catch:{ all -> 0x00ba }
            r0.setReserve(r2)     // Catch:{ all -> 0x00ba }
        L_0x0068:
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.R     // Catch:{ all -> 0x00ba }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00ba }
            r3 = r3[r1]     // Catch:{ all -> 0x00ba }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00ba }
            r2.put(r3, r9)     // Catch:{ all -> 0x00ba }
            com.unionpay.tsmservice.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x00a7 }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x00a7 }
            int[] r4 = r7.Z     // Catch:{ Exception -> 0x00a7 }
            r5 = r4[r1]     // Catch:{ Exception -> 0x00a7 }
            int r6 = r5 + 1
            r4[r1] = r6     // Catch:{ Exception -> 0x00a7 }
            r4 = 0
            r3.<init>(r7, r1, r5, r4)     // Catch:{ Exception -> 0x00a7 }
            int r0 = r2.preDownload(r0, r3, r10)     // Catch:{ Exception -> 0x00a7 }
            if (r0 == 0) goto L_0x009c
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.R     // Catch:{ all -> 0x00ba }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00ba }
            r4 = r3[r1]     // Catch:{ all -> 0x00ba }
            int r4 = r4 + -1
            r3[r1] = r4     // Catch:{ all -> 0x00ba }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x00ba }
            r2.remove(r3)     // Catch:{ all -> 0x00ba }
        L_0x009c:
            r2 = -2
            if (r2 != r0) goto L_0x00a5
            int r8 = a(r1, r8, r9, r10)     // Catch:{ all -> 0x00ba }
            monitor-exit(r7)
            return r8
        L_0x00a5:
            monitor-exit(r7)
            return r0
        L_0x00a7:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x00ba }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x00ba }
            r8.<init>()     // Catch:{ all -> 0x00ba }
            throw r8     // Catch:{ all -> 0x00ba }
        L_0x00b1:
            int r8 = a(r1, r8, r9, r10)     // Catch:{ all -> 0x00ba }
            monitor-exit(r7)
            return r8
        L_0x00b7:
            r8 = -1
            goto L_0x0004
        L_0x00ba:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.preDownload(com.unionpay.tsmservice.request.PreDownloadRequestParams, com.unionpay.tsmservice.ITsmCallback, com.unionpay.tsmservice.ITsmProgressCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0005, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int queryVendorPayStatus(com.unionpay.tsmservice.request.QueryVendorPayStatusRequestParams r8, com.unionpay.tsmservice.ITsmCallback r9) throws android.os.RemoteException {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r9 != 0) goto L_0x0006
            r8 = -3
        L_0x0004:
            monitor-exit(r7)
            return r8
        L_0x0006:
            java.lang.String r0 = "01.00.27"
            boolean r0 = r7.d((java.lang.String) r0)     // Catch:{ all -> 0x00a3 }
            r1 = -8
            if (r0 != 0) goto L_0x0011
            monitor-exit(r7)
            return r1
        L_0x0011:
            boolean r0 = r7.c()     // Catch:{ all -> 0x00a3 }
            if (r0 != 0) goto L_0x0019
            monitor-exit(r7)
            return r1
        L_0x0019:
            com.unionpay.tsmservice.ITsmService r0 = r7.e     // Catch:{ all -> 0x00a3 }
            if (r0 == 0) goto L_0x00a0
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x00a3 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00a3 }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x00a3 }
            r1 = 41
            if (r0 == 0) goto L_0x009a
            com.unionpay.tsmservice.request.QueryVendorPayStatusRequestParams r0 = new com.unionpay.tsmservice.request.QueryVendorPayStatusRequestParams     // Catch:{ all -> 0x00a3 }
            r0.<init>()     // Catch:{ all -> 0x00a3 }
            java.lang.String r2 = ""
            if (r8 == 0) goto L_0x0038
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x00a3 }
        L_0x0038:
            boolean r3 = r7.h     // Catch:{ all -> 0x00a3 }
            if (r3 == 0) goto L_0x0044
            java.lang.String r2 = f(r2)     // Catch:{ all -> 0x00a3 }
            java.lang.String r2 = r7.g(r2)     // Catch:{ all -> 0x00a3 }
        L_0x0044:
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00a3 }
            if (r3 != 0) goto L_0x0051
            java.lang.String r2 = r7.b((java.lang.String) r2)     // Catch:{ all -> 0x00a3 }
            r0.setReserve(r2)     // Catch:{ all -> 0x00a3 }
        L_0x0051:
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.S     // Catch:{ all -> 0x00a3 }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00a3 }
            r3 = r3[r1]     // Catch:{ all -> 0x00a3 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00a3 }
            r2.put(r3, r9)     // Catch:{ all -> 0x00a3 }
            com.unionpay.tsmservice.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x0090 }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x0090 }
            int[] r4 = r7.Z     // Catch:{ Exception -> 0x0090 }
            r5 = r4[r1]     // Catch:{ Exception -> 0x0090 }
            int r6 = r5 + 1
            r4[r1] = r6     // Catch:{ Exception -> 0x0090 }
            r4 = 0
            r3.<init>(r7, r1, r5, r4)     // Catch:{ Exception -> 0x0090 }
            int r0 = r2.queryVendorPayStatus(r0, r3)     // Catch:{ Exception -> 0x0090 }
            if (r0 == 0) goto L_0x0085
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.S     // Catch:{ all -> 0x00a3 }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00a3 }
            r4 = r3[r1]     // Catch:{ all -> 0x00a3 }
            int r4 = r4 + -1
            r3[r1] = r4     // Catch:{ all -> 0x00a3 }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x00a3 }
            r2.remove(r3)     // Catch:{ all -> 0x00a3 }
        L_0x0085:
            r2 = -2
            if (r2 != r0) goto L_0x008e
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x00a3 }
            monitor-exit(r7)
            return r8
        L_0x008e:
            monitor-exit(r7)
            return r0
        L_0x0090:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x00a3 }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x00a3 }
            r8.<init>()     // Catch:{ all -> 0x00a3 }
            throw r8     // Catch:{ all -> 0x00a3 }
        L_0x009a:
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x00a3 }
            monitor-exit(r7)
            return r8
        L_0x00a0:
            r8 = -1
            goto L_0x0004
        L_0x00a3:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.queryVendorPayStatus(com.unionpay.tsmservice.request.QueryVendorPayStatusRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    public synchronized void removeConnectionListener(UPTsmConnectionListener uPTsmConnectionListener) {
        if (uPTsmConnectionListener != null) {
            b.remove(uPTsmConnectionListener);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000f, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int sendApdu(com.unionpay.tsmservice.request.SendApduRequestParams r8, com.unionpay.tsmservice.ITsmCallback r9) throws android.os.RemoteException {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r8 == 0) goto L_0x00bb
            if (r9 != 0) goto L_0x0007
            goto L_0x00bb
        L_0x0007:
            boolean r0 = r7.c()     // Catch:{ all -> 0x00b8 }
            if (r0 != 0) goto L_0x0010
            r8 = -8
        L_0x000e:
            monitor-exit(r7)
            return r8
        L_0x0010:
            com.unionpay.tsmservice.ITsmService r0 = r7.e     // Catch:{ all -> 0x00b8 }
            if (r0 == 0) goto L_0x00b5
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x00b8 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00b8 }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x00b8 }
            r1 = 22
            if (r0 == 0) goto L_0x00af
            com.unionpay.tsmservice.request.SendApduRequestParams r0 = new com.unionpay.tsmservice.request.SendApduRequestParams     // Catch:{ all -> 0x00b8 }
            r0.<init>()     // Catch:{ all -> 0x00b8 }
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x00b8 }
            java.lang.String r3 = r8.getChannel()     // Catch:{ all -> 0x00b8 }
            java.lang.String r4 = r8.getHexApdu()     // Catch:{ all -> 0x00b8 }
            boolean r5 = r7.h     // Catch:{ all -> 0x00b8 }
            if (r5 == 0) goto L_0x003f
            java.lang.String r2 = f(r2)     // Catch:{ all -> 0x00b8 }
            java.lang.String r2 = r7.g(r2)     // Catch:{ all -> 0x00b8 }
        L_0x003f:
            boolean r5 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00b8 }
            if (r5 != 0) goto L_0x004c
            java.lang.String r2 = r7.b((java.lang.String) r2)     // Catch:{ all -> 0x00b8 }
            r0.setReserve(r2)     // Catch:{ all -> 0x00b8 }
        L_0x004c:
            boolean r2 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x00b8 }
            if (r2 != 0) goto L_0x0059
            java.lang.String r2 = r7.b((java.lang.String) r3)     // Catch:{ all -> 0x00b8 }
            r0.setChannel(r2)     // Catch:{ all -> 0x00b8 }
        L_0x0059:
            boolean r2 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x00b8 }
            if (r2 != 0) goto L_0x0066
            java.lang.String r2 = r7.b((java.lang.String) r4)     // Catch:{ all -> 0x00b8 }
            r0.setHexApdu(r2)     // Catch:{ all -> 0x00b8 }
        L_0x0066:
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.x     // Catch:{ all -> 0x00b8 }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00b8 }
            r3 = r3[r1]     // Catch:{ all -> 0x00b8 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00b8 }
            r2.put(r3, r9)     // Catch:{ all -> 0x00b8 }
            com.unionpay.tsmservice.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x00a5 }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x00a5 }
            int[] r4 = r7.Z     // Catch:{ Exception -> 0x00a5 }
            r5 = r4[r1]     // Catch:{ Exception -> 0x00a5 }
            int r6 = r5 + 1
            r4[r1] = r6     // Catch:{ Exception -> 0x00a5 }
            r4 = 0
            r3.<init>(r7, r1, r5, r4)     // Catch:{ Exception -> 0x00a5 }
            int r0 = r2.sendApdu(r0, r3)     // Catch:{ Exception -> 0x00a5 }
            if (r0 == 0) goto L_0x009a
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.x     // Catch:{ all -> 0x00b8 }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00b8 }
            r4 = r3[r1]     // Catch:{ all -> 0x00b8 }
            int r4 = r4 + -1
            r3[r1] = r4     // Catch:{ all -> 0x00b8 }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x00b8 }
            r2.remove(r3)     // Catch:{ all -> 0x00b8 }
        L_0x009a:
            r2 = -2
            if (r2 != r0) goto L_0x00a3
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x00b8 }
            monitor-exit(r7)
            return r8
        L_0x00a3:
            monitor-exit(r7)
            return r0
        L_0x00a5:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x00b8 }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x00b8 }
            r8.<init>()     // Catch:{ all -> 0x00b8 }
            throw r8     // Catch:{ all -> 0x00b8 }
        L_0x00af:
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x00b8 }
            monitor-exit(r7)
            return r8
        L_0x00b5:
            r8 = -1
            goto L_0x000e
        L_0x00b8:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        L_0x00bb:
            r8 = -3
            goto L_0x000e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.sendApdu(com.unionpay.tsmservice.request.SendApduRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00b6, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int sendCustomData(com.unionpay.tsmservice.request.SendCustomDataRequestParams r8, com.unionpay.tsmservice.ITsmCallback r9) throws android.os.RemoteException {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r8 == 0) goto L_0x00ba
            if (r9 != 0) goto L_0x0007
            goto L_0x00ba
        L_0x0007:
            java.lang.String r0 = "01.00.37"
            boolean r0 = r7.d((java.lang.String) r0)     // Catch:{ all -> 0x00b7 }
            r1 = -8
            if (r0 != 0) goto L_0x0012
            monitor-exit(r7)
            return r1
        L_0x0012:
            boolean r0 = r7.c()     // Catch:{ all -> 0x00b7 }
            if (r0 != 0) goto L_0x001a
            monitor-exit(r7)
            return r1
        L_0x001a:
            com.unionpay.tsmservice.ITsmService r0 = r7.e     // Catch:{ all -> 0x00b7 }
            if (r0 == 0) goto L_0x00b4
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x00b7 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x00b7 }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x00b7 }
            r1 = 45
            if (r0 == 0) goto L_0x00ae
            com.unionpay.tsmservice.request.SendCustomDataRequestParams r0 = new com.unionpay.tsmservice.request.SendCustomDataRequestParams     // Catch:{ all -> 0x00b7 }
            r0.<init>()     // Catch:{ all -> 0x00b7 }
            android.os.Bundle r2 = r8.getParams()     // Catch:{ all -> 0x00b7 }
            if (r2 == 0) goto L_0x0048
            android.os.Bundle r3 = new android.os.Bundle     // Catch:{ all -> 0x00b7 }
            r3.<init>()     // Catch:{ all -> 0x00b7 }
            java.lang.String r4 = "encryptData"
            java.lang.String r2 = r7.a((android.os.Bundle) r2)     // Catch:{ all -> 0x00b7 }
            r3.putString(r4, r2)     // Catch:{ all -> 0x00b7 }
            r0.setParams(r3)     // Catch:{ all -> 0x00b7 }
        L_0x0048:
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x00b7 }
            boolean r3 = r7.h     // Catch:{ all -> 0x00b7 }
            if (r3 == 0) goto L_0x0058
            java.lang.String r2 = f(r2)     // Catch:{ all -> 0x00b7 }
            java.lang.String r2 = r7.g(r2)     // Catch:{ all -> 0x00b7 }
        L_0x0058:
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x00b7 }
            if (r3 != 0) goto L_0x0065
            java.lang.String r2 = r7.b((java.lang.String) r2)     // Catch:{ all -> 0x00b7 }
            r0.setReserve(r2)     // Catch:{ all -> 0x00b7 }
        L_0x0065:
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.W     // Catch:{ all -> 0x00b7 }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00b7 }
            r3 = r3[r1]     // Catch:{ all -> 0x00b7 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00b7 }
            r2.put(r3, r9)     // Catch:{ all -> 0x00b7 }
            com.unionpay.tsmservice.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x00a4 }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x00a4 }
            int[] r4 = r7.Z     // Catch:{ Exception -> 0x00a4 }
            r5 = r4[r1]     // Catch:{ Exception -> 0x00a4 }
            int r6 = r5 + 1
            r4[r1] = r6     // Catch:{ Exception -> 0x00a4 }
            r4 = 0
            r3.<init>(r7, r1, r5, r4)     // Catch:{ Exception -> 0x00a4 }
            int r0 = r2.sendCustomData(r0, r3)     // Catch:{ Exception -> 0x00a4 }
            if (r0 == 0) goto L_0x0099
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.W     // Catch:{ all -> 0x00b7 }
            int[] r3 = r7.Z     // Catch:{ all -> 0x00b7 }
            r4 = r3[r1]     // Catch:{ all -> 0x00b7 }
            int r4 = r4 + -1
            r3[r1] = r4     // Catch:{ all -> 0x00b7 }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x00b7 }
            r2.remove(r3)     // Catch:{ all -> 0x00b7 }
        L_0x0099:
            r2 = -2
            if (r2 != r0) goto L_0x00a2
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x00b7 }
            monitor-exit(r7)
            return r8
        L_0x00a2:
            monitor-exit(r7)
            return r0
        L_0x00a4:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x00b7 }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x00b7 }
            r8.<init>()     // Catch:{ all -> 0x00b7 }
            throw r8     // Catch:{ all -> 0x00b7 }
        L_0x00ae:
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x00b7 }
            monitor-exit(r7)
            return r8
        L_0x00b4:
            r8 = -1
        L_0x00b5:
            monitor-exit(r7)
            return r8
        L_0x00b7:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        L_0x00ba:
            r8 = -3
            goto L_0x00b5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.sendCustomData(com.unionpay.tsmservice.request.SendCustomDataRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001c, code lost:
        return r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0079, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0090, code lost:
        return -3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int setDefaultCard(com.unionpay.tsmservice.request.SetDefaultCardRequestParams r7, com.unionpay.tsmservice.ITsmCallback r8) throws android.os.RemoteException {
        /*
            r6 = this;
            monitor-enter(r6)
            r0 = -3
            if (r7 == 0) goto L_0x008f
            if (r8 != 0) goto L_0x0008
            goto L_0x008f
        L_0x0008:
            java.lang.String r1 = r7.getAppAID()     // Catch:{ all -> 0x008c }
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x008c }
            if (r2 == 0) goto L_0x0014
            monitor-exit(r6)
            return r0
        L_0x0014:
            boolean r0 = r6.c()     // Catch:{ all -> 0x008c }
            if (r0 != 0) goto L_0x001d
            r7 = -8
        L_0x001b:
            monitor-exit(r6)
            return r7
        L_0x001d:
            com.unionpay.tsmservice.ITsmService r0 = r6.e     // Catch:{ all -> 0x008c }
            if (r0 == 0) goto L_0x008a
            android.content.Context r0 = r6.c     // Catch:{ all -> 0x008c }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x008c }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x008c }
            r2 = 14
            if (r0 == 0) goto L_0x0084
            java.lang.String r0 = r6.b((java.lang.String) r1)     // Catch:{ all -> 0x008c }
            com.unionpay.tsmservice.request.SetDefaultCardRequestParams r1 = new com.unionpay.tsmservice.request.SetDefaultCardRequestParams     // Catch:{ all -> 0x008c }
            r1.<init>()     // Catch:{ all -> 0x008c }
            r1.setAppAID(r0)     // Catch:{ all -> 0x008c }
            java.lang.String r0 = r7.getReserve()     // Catch:{ all -> 0x008c }
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x008c }
            if (r3 != 0) goto L_0x004c
            java.lang.String r0 = r6.b((java.lang.String) r0)     // Catch:{ all -> 0x008c }
            r1.setReserve(r0)     // Catch:{ all -> 0x008c }
        L_0x004c:
            com.unionpay.tsmservice.ITsmService r0 = r6.e     // Catch:{ Exception -> 0x007a }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x007a }
            int[] r4 = r6.Z     // Catch:{ Exception -> 0x007a }
            r4 = r4[r2]     // Catch:{ Exception -> 0x007a }
            r5 = 0
            r3.<init>(r6, r2, r4, r5)     // Catch:{ Exception -> 0x007a }
            int r0 = r0.setDefaultCard(r1, r3)     // Catch:{ Exception -> 0x007a }
            r1 = -2
            if (r1 != r0) goto L_0x0065
            int r7 = a(r2, r7, r8)     // Catch:{ all -> 0x008c }
            monitor-exit(r6)
            return r7
        L_0x0065:
            if (r0 != 0) goto L_0x0078
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r7 = r6.E     // Catch:{ all -> 0x008c }
            int[] r1 = r6.Z     // Catch:{ all -> 0x008c }
            r3 = r1[r2]     // Catch:{ all -> 0x008c }
            int r4 = r3 + 1
            r1[r2] = r4     // Catch:{ all -> 0x008c }
            java.lang.String r1 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x008c }
            r7.put(r1, r8)     // Catch:{ all -> 0x008c }
        L_0x0078:
            monitor-exit(r6)
            return r0
        L_0x007a:
            r7 = move-exception
            r7.printStackTrace()     // Catch:{ all -> 0x008c }
            android.os.RemoteException r7 = new android.os.RemoteException     // Catch:{ all -> 0x008c }
            r7.<init>()     // Catch:{ all -> 0x008c }
            throw r7     // Catch:{ all -> 0x008c }
        L_0x0084:
            int r7 = a(r2, r7, r8)     // Catch:{ all -> 0x008c }
            monitor-exit(r6)
            return r7
        L_0x008a:
            r7 = -1
            goto L_0x001b
        L_0x008c:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        L_0x008f:
            monitor-exit(r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.setDefaultCard(com.unionpay.tsmservice.request.SetDefaultCardRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0005, code lost:
        return r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int setSafetyKeyboardBitmap(com.unionpay.tsmservice.request.SafetyKeyboardRequestParams r5) throws android.os.RemoteException {
        /*
            r4 = this;
            monitor-enter(r4)
            if (r5 != 0) goto L_0x0006
            r5 = -3
        L_0x0004:
            monitor-exit(r4)
            return r5
        L_0x0006:
            boolean r0 = r4.c()     // Catch:{ all -> 0x0061 }
            if (r0 != 0) goto L_0x000e
            r5 = -8
            goto L_0x0004
        L_0x000e:
            com.unionpay.tsmservice.ITsmService r0 = r4.e     // Catch:{ all -> 0x0061 }
            if (r0 == 0) goto L_0x005f
            android.content.Context r0 = r4.c     // Catch:{ all -> 0x0061 }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x0061 }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x0061 }
            r1 = 0
            r2 = 32
            if (r0 == 0) goto L_0x0059
            java.lang.String r0 = r5.getReserve()     // Catch:{ all -> 0x0061 }
            boolean r3 = r4.h     // Catch:{ all -> 0x0061 }
            if (r3 == 0) goto L_0x0031
            java.lang.String r0 = f(r0)     // Catch:{ all -> 0x0061 }
            java.lang.String r0 = r4.g(r0)     // Catch:{ all -> 0x0061 }
        L_0x0031:
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x0061 }
            if (r3 != 0) goto L_0x003e
            java.lang.String r0 = r4.b((java.lang.String) r0)     // Catch:{ all -> 0x0061 }
            r5.setReserve(r0)     // Catch:{ all -> 0x0061 }
        L_0x003e:
            com.unionpay.tsmservice.ITsmService r0 = r4.e     // Catch:{ Exception -> 0x004f }
            int r0 = r0.setSafetyKeyboardBitmap(r5)     // Catch:{ Exception -> 0x004f }
            r3 = -2
            if (r3 != r0) goto L_0x004d
            int r5 = a(r2, r5, r1)     // Catch:{ all -> 0x0061 }
            monitor-exit(r4)
            return r5
        L_0x004d:
            monitor-exit(r4)
            return r0
        L_0x004f:
            r5 = move-exception
            r5.printStackTrace()     // Catch:{ all -> 0x0061 }
            android.os.RemoteException r5 = new android.os.RemoteException     // Catch:{ all -> 0x0061 }
            r5.<init>()     // Catch:{ all -> 0x0061 }
            throw r5     // Catch:{ all -> 0x0061 }
        L_0x0059:
            int r5 = a(r2, r5, r1)     // Catch:{ all -> 0x0061 }
            monitor-exit(r4)
            return r5
        L_0x005f:
            r5 = -1
            goto L_0x0004
        L_0x0061:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.setSafetyKeyboardBitmap(com.unionpay.tsmservice.request.SafetyKeyboardRequestParams):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0005, code lost:
        return r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int setSamsungDefaultWallet(com.unionpay.tsmservice.request.SetSamsungDefWalletRequestParams r8, com.unionpay.tsmservice.ITsmCallback r9) throws android.os.RemoteException {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r9 != 0) goto L_0x0006
            r8 = -3
        L_0x0004:
            monitor-exit(r7)
            return r8
        L_0x0006:
            boolean r0 = r7.c()     // Catch:{ all -> 0x008a }
            if (r0 != 0) goto L_0x000e
            r8 = -8
            goto L_0x0004
        L_0x000e:
            com.unionpay.tsmservice.ITsmService r0 = r7.e     // Catch:{ all -> 0x008a }
            if (r0 == 0) goto L_0x0087
            android.content.Context r0 = r7.c     // Catch:{ all -> 0x008a }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x008a }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x008a }
            r1 = 30
            if (r0 == 0) goto L_0x0081
            com.unionpay.tsmservice.request.SetSamsungDefWalletRequestParams r0 = new com.unionpay.tsmservice.request.SetSamsungDefWalletRequestParams     // Catch:{ all -> 0x008a }
            r0.<init>()     // Catch:{ all -> 0x008a }
            if (r8 == 0) goto L_0x0038
            java.lang.String r2 = r8.getReserve()     // Catch:{ all -> 0x008a }
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x008a }
            if (r3 != 0) goto L_0x0038
            java.lang.String r2 = r7.b((java.lang.String) r2)     // Catch:{ all -> 0x008a }
            r0.setReserve(r2)     // Catch:{ all -> 0x008a }
        L_0x0038:
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.K     // Catch:{ all -> 0x008a }
            int[] r3 = r7.Z     // Catch:{ all -> 0x008a }
            r3 = r3[r1]     // Catch:{ all -> 0x008a }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x008a }
            r2.put(r3, r9)     // Catch:{ all -> 0x008a }
            com.unionpay.tsmservice.ITsmService r2 = r7.e     // Catch:{ Exception -> 0x0077 }
            com.unionpay.tsmservice.UPTsmAddon$b r3 = new com.unionpay.tsmservice.UPTsmAddon$b     // Catch:{ Exception -> 0x0077 }
            int[] r4 = r7.Z     // Catch:{ Exception -> 0x0077 }
            r5 = r4[r1]     // Catch:{ Exception -> 0x0077 }
            int r6 = r5 + 1
            r4[r1] = r6     // Catch:{ Exception -> 0x0077 }
            r4 = 0
            r3.<init>(r7, r1, r5, r4)     // Catch:{ Exception -> 0x0077 }
            int r0 = r2.setSamsungDefaultWallet(r0, r3)     // Catch:{ Exception -> 0x0077 }
            if (r0 == 0) goto L_0x006c
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmCallback> r2 = r7.K     // Catch:{ all -> 0x008a }
            int[] r3 = r7.Z     // Catch:{ all -> 0x008a }
            r4 = r3[r1]     // Catch:{ all -> 0x008a }
            int r4 = r4 + -1
            r3[r1] = r4     // Catch:{ all -> 0x008a }
            java.lang.String r3 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x008a }
            r2.remove(r3)     // Catch:{ all -> 0x008a }
        L_0x006c:
            r2 = -2
            if (r2 != r0) goto L_0x0075
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x008a }
            monitor-exit(r7)
            return r8
        L_0x0075:
            monitor-exit(r7)
            return r0
        L_0x0077:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x008a }
            android.os.RemoteException r8 = new android.os.RemoteException     // Catch:{ all -> 0x008a }
            r8.<init>()     // Catch:{ all -> 0x008a }
            throw r8     // Catch:{ all -> 0x008a }
        L_0x0081:
            int r8 = a(r1, r8, r9)     // Catch:{ all -> 0x008a }
            monitor-exit(r7)
            return r8
        L_0x0087:
            r8 = -1
            goto L_0x0004
        L_0x008a:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.setSamsungDefaultWallet(com.unionpay.tsmservice.request.SetSamsungDefWalletRequestParams, com.unionpay.tsmservice.ITsmCallback):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0015, code lost:
        return r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int showSafetyKeyboard(com.unionpay.tsmservice.request.SafetyKeyboardRequestParams r5, int r6, com.unionpay.tsmservice.OnSafetyKeyboardCallback r7, android.content.Context r8) throws android.os.RemoteException {
        /*
            r4 = this;
            monitor-enter(r4)
            if (r5 == 0) goto L_0x008d
            r0 = 2000(0x7d0, float:2.803E-42)
            if (r6 < r0) goto L_0x008d
            r0 = 2001(0x7d1, float:2.804E-42)
            if (r6 <= r0) goto L_0x000d
            goto L_0x008d
        L_0x000d:
            boolean r0 = r4.c()     // Catch:{ all -> 0x008a }
            if (r0 != 0) goto L_0x0016
            r5 = -8
        L_0x0014:
            monitor-exit(r4)
            return r5
        L_0x0016:
            com.unionpay.tsmservice.ITsmService r0 = r4.e     // Catch:{ all -> 0x008a }
            if (r0 == 0) goto L_0x0088
            android.content.Context r0 = r4.c     // Catch:{ all -> 0x008a }
            java.lang.String r0 = r0.getPackageName()     // Catch:{ all -> 0x008a }
            boolean r0 = a((java.lang.String) r0)     // Catch:{ all -> 0x008a }
            r1 = 1000(0x3e8, float:1.401E-42)
            if (r0 == 0) goto L_0x0082
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmActivityCallback> r0 = r4.Y     // Catch:{ all -> 0x008a }
            android.content.Context r2 = r4.c     // Catch:{ all -> 0x008a }
            java.lang.String r2 = r2.getPackageName()     // Catch:{ all -> 0x008a }
            com.unionpay.tsmservice.a r3 = new com.unionpay.tsmservice.a     // Catch:{ all -> 0x008a }
            r3.<init>(r8)     // Catch:{ all -> 0x008a }
            r0.put(r2, r3)     // Catch:{ all -> 0x008a }
            java.lang.String r0 = r5.getReserve()     // Catch:{ all -> 0x008a }
            boolean r2 = r4.h     // Catch:{ all -> 0x008a }
            if (r2 == 0) goto L_0x0048
            java.lang.String r0 = f(r0)     // Catch:{ all -> 0x008a }
            java.lang.String r0 = r4.g(r0)     // Catch:{ all -> 0x008a }
        L_0x0048:
            boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x008a }
            if (r2 != 0) goto L_0x0055
            java.lang.String r0 = r4.b((java.lang.String) r0)     // Catch:{ all -> 0x008a }
            r5.setReserve(r0)     // Catch:{ all -> 0x008a }
        L_0x0055:
            com.unionpay.tsmservice.ITsmService r0 = r4.e     // Catch:{ Exception -> 0x0078 }
            com.unionpay.tsmservice.UPTsmAddon$a r2 = new com.unionpay.tsmservice.UPTsmAddon$a     // Catch:{ Exception -> 0x0078 }
            r2.<init>()     // Catch:{ Exception -> 0x0078 }
            int r0 = r0.showSafetyKeyboard(r5, r6, r7, r2)     // Catch:{ Exception -> 0x0078 }
            if (r0 == 0) goto L_0x006d
            java.util.HashMap<java.lang.String, com.unionpay.tsmservice.ITsmActivityCallback> r2 = r4.Y     // Catch:{ all -> 0x008a }
            android.content.Context r3 = r4.c     // Catch:{ all -> 0x008a }
            java.lang.String r3 = r3.getPackageName()     // Catch:{ all -> 0x008a }
            r2.remove(r3)     // Catch:{ all -> 0x008a }
        L_0x006d:
            r2 = -2
            if (r2 != r0) goto L_0x0076
            int r5 = a((int) r1, (com.unionpay.tsmservice.request.SafetyKeyboardRequestParams) r5, (int) r6, (com.unionpay.tsmservice.OnSafetyKeyboardCallback) r7, (android.content.Context) r8)     // Catch:{ all -> 0x008a }
            monitor-exit(r4)
            return r5
        L_0x0076:
            monitor-exit(r4)
            return r0
        L_0x0078:
            r5 = move-exception
            r5.printStackTrace()     // Catch:{ all -> 0x008a }
            android.os.RemoteException r5 = new android.os.RemoteException     // Catch:{ all -> 0x008a }
            r5.<init>()     // Catch:{ all -> 0x008a }
            throw r5     // Catch:{ all -> 0x008a }
        L_0x0082:
            int r5 = a((int) r1, (com.unionpay.tsmservice.request.SafetyKeyboardRequestParams) r5, (int) r6, (com.unionpay.tsmservice.OnSafetyKeyboardCallback) r7, (android.content.Context) r8)     // Catch:{ all -> 0x008a }
            monitor-exit(r4)
            return r5
        L_0x0088:
            r5 = -1
            goto L_0x0014
        L_0x008a:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        L_0x008d:
            r5 = -3
            goto L_0x0014
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.UPTsmAddon.showSafetyKeyboard(com.unionpay.tsmservice.request.SafetyKeyboardRequestParams, int, com.unionpay.tsmservice.OnSafetyKeyboardCallback, android.content.Context):int");
    }

    public void unbind() {
        if (this.d != null && this.f) {
            this.c.unbindService(this.d);
            this.f = false;
        }
    }
}
