package com.xiaomi.channel.sdk;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.xiaomi.channel.gamesdk.GameServiceClient;
import java.util.List;

public class MLShareApiFactory {

    /* renamed from: a  reason: collision with root package name */
    public static final String f10068a = "extra_share_bundle";
    public String b;
    /* access modifiers changed from: private */
    public Context c;
    private String d;
    private String e;
    private String f;
    private Drawable g;
    private String h;

    public MLShareApiFactory(Context context, String str, String str2) {
        if (context == null) {
            throw new IllegalArgumentException("context could not be null!");
        } else if (!TextUtils.isEmpty(str)) {
            this.d = str;
            this.b = str2;
            a(context);
        } else {
            throw new IllegalArgumentException("appId could not be empty!");
        }
    }

    public MLShareApiFactory(Context context) {
        if (context != null) {
            a(context);
            return;
        }
        throw new IllegalArgumentException("context could not be null!");
    }

    public void a(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            this.f = str;
            if (!TextUtils.isEmpty(str2)) {
                this.e = str2;
            }
        }
    }

    private void a(Context context) {
        this.c = context.getApplicationContext();
        try {
            PackageInfo packageInfo = this.c.getPackageManager().getPackageInfo(this.f, 0);
            this.e = packageInfo.applicationInfo.loadLabel(this.c.getPackageManager()).toString();
            this.g = packageInfo.applicationInfo.loadIcon(this.c.getPackageManager());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public String a(IShareReq iShareReq, boolean z) throws RemoteException {
        String str;
        if (iShareReq == null) {
            return "";
        }
        final Intent intent = new Intent();
        Bundle a2 = iShareReq.a();
        if (a2 != null) {
            if (!TextUtils.isEmpty(this.d)) {
                a2.putString("app_id", this.d);
            }
            a2.putString("app_name", this.e);
            if (z) {
                a2.putString(ShareConstants.w, this.f);
                MLExtraInfo b2 = iShareReq.b();
                if (b2 != null) {
                    a2.putString(ShareConstants.s, b2.a());
                }
            }
            if (this.g != null) {
                a2.putParcelable(ShareConstants.x, ShareUtils.a(this.g));
            }
            if (TextUtils.isEmpty(this.b)) {
                str = ShareConstants.P;
            } else {
                str = this.b;
            }
            a2.putString(ShareConstants.y, str);
            if (!TextUtils.isEmpty(this.h)) {
                a2.putString(ShareConstants.t, this.h);
            }
        }
        if (!VersionManager.d(this.c)) {
            return "";
        }
        if (VersionManager.a(this.c, GameServiceClient.t)) {
            return GameServiceClient.b(this.c).a(a2);
        }
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) this.c.getSystemService("activity")).getRunningAppProcesses();
        boolean z2 = false;
        int i = 0;
        while (true) {
            if (i >= runningAppProcesses.size()) {
                break;
            } else if (runningAppProcesses.get(i).processName.equals("com.xiaomi.channel")) {
                z2 = true;
                break;
            } else {
                i++;
            }
        }
        String valueOf = String.valueOf(System.currentTimeMillis());
        a2.putString(ShareConstants.z, valueOf);
        intent.putExtra(ShareConstants.m, a2);
        intent.setAction(ShareConstants.d);
        if (!z2) {
            this.c.startActivity(this.c.getPackageManager().getLaunchIntentForPackage("com.xiaomi.channel"));
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    MLShareApiFactory.this.c.sendBroadcast(intent);
                }
            }, 2000);
        } else {
            this.c.sendBroadcast(intent);
        }
        return valueOf;
    }

    public void a(String str) {
        this.h = str;
    }
}
