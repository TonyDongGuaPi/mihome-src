package com.xiaomi.channel.gamesdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.exoplayer2.C;
import com.xiaomi.channel.gamesdk.aidl.IGameService;
import com.xiaomi.channel.sdk.VersionManager;

public class GameServiceClient {

    /* renamed from: a  reason: collision with root package name */
    public static final String f10050a = "code";
    public static final String b = "data";
    public static final String c = "flag";
    public static final int d = 0;
    public static final int e = -1;
    public static final int f = 20001;
    public static final int g = 20002;
    public static final int h = 20003;
    public static final int i = 20004;
    public static final int j = 20005;
    public static final int k = 20006;
    public static final int l = 20007;
    public static final int m = 20008;
    public static final int n = 20009;
    public static final int o = 20010;
    public static final int p = 20011;
    public static final int q = 20012;
    public static final int r = -10003;
    public static final int s = -10004;
    public static final int t = 1146;
    public static final Intent u = new Intent();
    private static final String v = "http://www.miliao.com/from=android&version=%d";
    private static final String w = "GameServiceClient";
    private static GameServiceClient x;
    private Context A;
    private ServiceConnection B = new ServiceConnection() {
        public void onServiceDisconnected(ComponentName componentName) {
            synchronized (GameServiceClient.this.z) {
                GameServiceClient.this.y = null;
                GameServiceClient.this.z.notifyAll();
            }
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            synchronized (GameServiceClient.this.z) {
                GameServiceClient.this.y = IGameService.Stub.asInterface(iBinder);
                Log.d(GameServiceClient.w, "GameService::onServiceConnected");
                GameServiceClient.this.z.notifyAll();
            }
        }
    };
    /* access modifiers changed from: private */
    public IGameService y;
    /* access modifiers changed from: private */
    public final Object z = new Object();

    static {
        u.setComponent(new ComponentName("com.xiaomi.channel", "com.xiaomi.channel.gameService.GameService"));
    }

    public static boolean a(Context context) {
        return VersionManager.a(context, t);
    }

    private GameServiceClient(Context context) {
        if (context != null) {
            try {
                this.A = context.getApplicationContext();
                g();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void g() {
        if (a(this.A)) {
            synchronized (this.z) {
                try {
                    this.A.bindService(u, this.B, 1);
                    Log.d(w, "GameService::bindService");
                    try {
                        this.z.wait(30000);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    public static synchronized GameServiceClient b(Context context) {
        GameServiceClient gameServiceClient;
        synchronized (GameServiceClient.class) {
            if (x == null) {
                x = new GameServiceClient(context);
            }
            gameServiceClient = x;
        }
        return gameServiceClient;
    }

    public Retobj a(String str) throws RemoteException {
        Retobj retobj = new Retobj();
        if (!VersionManager.d(this.A)) {
            retobj.a(-10001);
            return retobj;
        } else if (!VersionManager.a(this.A, t)) {
            retobj.a(-10002);
            return retobj;
        } else if (a()) {
            return this.y.isMiIdMyFriend(str);
        } else {
            retobj.a(-10005);
            return retobj;
        }
    }

    public boolean a() {
        if (this.y != null) {
            return true;
        }
        g();
        if (this.y != null) {
            return true;
        }
        return false;
    }

    public synchronized void b() {
        if (this.y != null) {
            this.A.unbindService(this.B);
            this.y = null;
        }
        x = null;
        this.A = null;
    }

    public Retobj a(String str, String str2) throws RemoteException {
        Retobj retobj = new Retobj();
        if (!VersionManager.d(this.A)) {
            retobj.a(-10001);
            return retobj;
        } else if (!VersionManager.a(this.A, t)) {
            retobj.a(-10002);
            return retobj;
        } else if (a()) {
            return this.y.sendTextMsgToFriend(str2, str);
        } else {
            retobj.a(-10005);
            return retobj;
        }
    }

    public Bundle c() throws RemoteException {
        if (a() && this.y != null) {
            return this.y.getAccount();
        }
        return null;
    }

    public Bundle b(String str, String str2) throws RemoteException {
        if (a() && this.y != null) {
            return this.y.getAuthToken(str, str2);
        }
        return null;
    }

    public static int c(final Context context) {
        if (!VersionManager.d(context)) {
            return -10001;
        }
        if (!VersionManager.a(context, t)) {
            return -10002;
        }
        new Thread(new Runnable() {
            public void run() {
                GameServiceClient.b(context);
            }
        }).start();
        return 0;
    }

    public Retobj a(String str, String str2, String str3, String str4) throws RemoteException {
        Retobj retobj = new Retobj();
        if (!VersionManager.d(this.A)) {
            retobj.a(-10001);
            return retobj;
        } else if (!VersionManager.a(this.A, t)) {
            retobj.a(-10002);
            return retobj;
        } else if (a()) {
            return this.y.addFriend(str, str2, str3, str4);
        } else {
            retobj.a(-10005);
            return retobj;
        }
    }

    public Retobj d() throws RemoteException {
        Retobj retobj = new Retobj();
        if (!VersionManager.d(this.A)) {
            retobj.a(-10001);
            return retobj;
        } else if (!VersionManager.a(this.A, t)) {
            retobj.a(-10002);
            return retobj;
        } else if (a()) {
            return this.y.checkMiLiaoStatus();
        } else {
            retobj.a(-10005);
            return retobj;
        }
    }

    public Retobj b(String str) throws RemoteException {
        Retobj retobj = new Retobj();
        if (!VersionManager.d(this.A)) {
            retobj.a(-10001);
            return retobj;
        } else if (!VersionManager.a(this.A, t)) {
            retobj.a(-10002);
            return retobj;
        } else if (a()) {
            return this.y.subscribeVip(str);
        } else {
            retobj.a(-10005);
            return retobj;
        }
    }

    public Retobj c(String str) throws RemoteException {
        Retobj retobj = new Retobj();
        if (!VersionManager.d(this.A)) {
            retobj.a(-10001);
            return retobj;
        } else if (!VersionManager.a(this.A, t)) {
            retobj.a(-10002);
            return retobj;
        } else if (a()) {
            return this.y.checkVipIsScubscribed(str);
        } else {
            retobj.a(-10005);
            return retobj;
        }
    }

    public Retobj c(String str, String str2) throws RemoteException {
        Retobj retobj = new Retobj();
        if (!VersionManager.d(this.A)) {
            retobj.a(-10001);
            return retobj;
        } else if (!VersionManager.a(this.A, t)) {
            retobj.a(-10002);
            return retobj;
        } else if (a()) {
            return this.y.joinUnion(str, str2);
        } else {
            retobj.a(-10005);
            return retobj;
        }
    }

    public Retobj d(String str) throws RemoteException {
        Retobj retobj = new Retobj();
        if (!VersionManager.d(this.A)) {
            retobj.a(-10001);
            return retobj;
        } else if (!VersionManager.a(this.A, t)) {
            retobj.a(-10002);
            return retobj;
        } else if (a()) {
            return this.y.checkHasJoinedUnion(str);
        } else {
            retobj.a(-10005);
            return retobj;
        }
    }

    public String a(Bundle bundle) throws RemoteException {
        if (!VersionManager.d(this.A)) {
            return String.valueOf(-10001);
        }
        if (!VersionManager.a(this.A, t)) {
            return String.valueOf(-10002);
        }
        if (!a()) {
            return String.valueOf(-10005);
        }
        return bundle != null ? this.y.doShare(bundle) : "";
    }

    public Retobj d(String str, String str2) throws RemoteException {
        Retobj retobj = new Retobj();
        if (!VersionManager.d(this.A)) {
            retobj.a(-10001);
            return retobj;
        } else if (!VersionManager.a(this.A, t)) {
            retobj.a(-10002);
            return retobj;
        } else if (a()) {
            return this.y.openComposeOrSixinActivity(str, str2);
        } else {
            retobj.a(-10005);
            return retobj;
        }
    }

    public void e() {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(String.format(v, new Object[]{Integer.valueOf(VersionManager.c(this.A))})));
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        this.A.startActivity(intent);
    }

    public String f() throws RemoteException {
        if (!VersionManager.d(this.A)) {
            return String.valueOf(-10001);
        }
        if (!VersionManager.a(this.A, t)) {
            return String.valueOf(-10002);
        }
        if (!a()) {
            return String.valueOf(-10005);
        }
        return this.y.getCurrentMiId();
    }
}
