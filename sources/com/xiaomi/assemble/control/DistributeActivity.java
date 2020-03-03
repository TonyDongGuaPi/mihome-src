package com.xiaomi.assemble.control;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import com.xiaomi.mipush.sdk.HWPushHelper;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageHelper;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

public class DistributeActivity extends Activity {
    private static Class<?> b;

    /* renamed from: a  reason: collision with root package name */
    private String f9997a = "isExecClickCallback";

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT != 26) {
            setRequestedOrientation(1);
        }
        new AsyncTask<Integer, Integer, Integer>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Integer doInBackground(Integer... numArr) {
                DistributeActivity.this.doDistribute();
                return 0;
            }
        }.execute(new Integer[0]);
    }

    public void doDistribute() {
        Bundle extras;
        Intent intent = getIntent();
        HWPushHelper.a(intent);
        if (intent == null || (extras = intent.getExtras()) == null || Boolean.valueOf(extras.getString(this.f9997a, "true")).booleanValue()) {
            MiPushMessage miPushMessage = (MiPushMessage) getIntent().getSerializableExtra(PushMessageHelper.j);
            PushMessageReceiver a2 = a();
            if (a2 != null) {
                a2.onNotificationMessageClicked(this, miPushMessage);
            }
            finish();
            return;
        }
        finish();
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0051 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0020 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.xiaomi.mipush.sdk.PushMessageReceiver a() {
        /*
            r4 = this;
            java.lang.Class<?> r0 = b
            if (r0 != 0) goto L_0x005e
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "com.xiaomi.mipush.RECEIVE_MESSAGE"
            r0.<init>(r1)
            java.lang.String r1 = r4.getPackageName()
            r0.setPackage(r1)
            android.content.pm.PackageManager r1 = r4.getPackageManager()
            r2 = 16384(0x4000, float:2.2959E-41)
            java.util.List r0 = r1.queryBroadcastReceivers(r0, r2)
            java.util.Iterator r0 = r0.iterator()
        L_0x0020:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x005e
            java.lang.Object r1 = r0.next()
            android.content.pm.ResolveInfo r1 = (android.content.pm.ResolveInfo) r1
            android.content.pm.ActivityInfo r1 = r1.activityInfo
            if (r1 == 0) goto L_0x004e
            java.lang.String r2 = r1.name     // Catch:{ ClassNotFoundException -> 0x004c }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ ClassNotFoundException -> 0x004c }
            if (r2 != 0) goto L_0x004e
            java.lang.Class<com.xiaomi.mipush.sdk.PushMessageReceiver> r2 = com.xiaomi.mipush.sdk.PushMessageReceiver.class
            java.lang.String r3 = r1.name     // Catch:{ ClassNotFoundException -> 0x004c }
            java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch:{ ClassNotFoundException -> 0x004c }
            boolean r2 = r2.isAssignableFrom(r3)     // Catch:{ ClassNotFoundException -> 0x004c }
            if (r2 == 0) goto L_0x004e
            boolean r2 = r1.enabled     // Catch:{ ClassNotFoundException -> 0x004c }
            if (r2 == 0) goto L_0x004e
            r2 = 1
            goto L_0x004f
        L_0x004c:
            r1 = move-exception
            goto L_0x005a
        L_0x004e:
            r2 = 0
        L_0x004f:
            if (r2 == 0) goto L_0x0020
            java.lang.String r1 = r1.name     // Catch:{ ClassNotFoundException -> 0x004c }
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ ClassNotFoundException -> 0x004c }
            b = r1     // Catch:{ ClassNotFoundException -> 0x004c }
            goto L_0x005e
        L_0x005a:
            r1.printStackTrace()
            goto L_0x0020
        L_0x005e:
            java.lang.Class<?> r0 = b
            if (r0 == 0) goto L_0x006f
            java.lang.Class<?> r0 = b     // Catch:{ Exception -> 0x006b }
            java.lang.Object r0 = r0.newInstance()     // Catch:{ Exception -> 0x006b }
            com.xiaomi.mipush.sdk.PushMessageReceiver r0 = (com.xiaomi.mipush.sdk.PushMessageReceiver) r0     // Catch:{ Exception -> 0x006b }
            goto L_0x0070
        L_0x006b:
            r0 = move-exception
            r0.printStackTrace()
        L_0x006f:
            r0 = 0
        L_0x0070:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.assemble.control.DistributeActivity.a():com.xiaomi.mipush.sdk.PushMessageReceiver");
    }
}
