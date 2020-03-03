package com.xiaomi.smarthome.framework.navigate;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.AppStateNotifier;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.MultiHomeDeviceManager;
import com.xiaomi.smarthome.stat.STAT;

public abstract class SmartHomeJumper {

    /* renamed from: a  reason: collision with root package name */
    protected Activity f16637a;
    /* access modifiers changed from: private */
    public JumperRefreshListener b;

    public abstract void a(Intent intent);

    private class JumperRefreshListener implements SmartHomeDeviceManager.IClientDeviceListener {
        private String b;
        private String c;
        private String d;
        private Intent e;
        private boolean f;

        public void a(int i, Device device) {
        }

        JumperRefreshListener(String str, String str2, String str3, Intent intent, boolean z) {
            this.b = str;
            this.c = str2;
            this.d = str3;
            this.e = intent;
            this.f = z;
        }

        public void a(int i) {
            if (i == 3) {
                SmartHomeJumper.this.a(false, this.f, this.d, this.b, this.c, this.e);
                SmartHomeDeviceManager.a().c((SmartHomeDeviceManager.IClientDeviceListener) this);
                HomeManager.a().b();
            }
        }

        public void b(int i) {
            SmartHomeDeviceManager.a().c((SmartHomeDeviceManager.IClientDeviceListener) this);
        }
    }

    public static SmartHomeJumper a(Activity activity, Intent intent) {
        Uri data = intent.getData();
        if (data == null) {
            return new SmartHomeIntentJumper(activity);
        }
        STAT.f22748a.b(data.toString());
        return new SmartHomeUriJumper(activity);
    }

    SmartHomeJumper(Activity activity) {
        this.f16637a = activity;
    }

    /* access modifiers changed from: package-private */
    public void b(Intent intent) {
        Intent intent2 = new Intent(this.f16637a, SmartHomeMainActivity.class);
        if (intent != null) {
            intent2.putExtras(intent);
        }
        intent2.setFlags(268468224);
        this.f16637a.startActivity(intent2);
    }

    /* access modifiers changed from: private */
    public boolean a() {
        if ((Build.VERSION.SDK_INT < 17 || !this.f16637a.isDestroyed()) && !this.f16637a.isFinishing()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z, boolean z2, String str, String str2, String str3, Intent intent) {
        if (TextUtils.isEmpty(str2)) {
            this.f16637a.finish();
            return;
        }
        final String str4 = str;
        final String str5 = str2;
        final String str6 = str3;
        final Intent intent2 = intent;
        final boolean z3 = z2;
        final boolean z4 = z;
        SHApplication.getStateNotifier().a((AppStateNotifier.LoginCallback) new AppStateNotifier.LoginCallback() {
            /* JADX WARNING: Code restructure failed: missing block: B:33:0x00d5, code lost:
                r0 = r4;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void a() {
                /*
                    r12 = this;
                    java.lang.String r0 = r3
                    boolean r0 = android.text.TextUtils.isEmpty(r0)
                    r1 = 0
                    r2 = 2131497396(0x7f0c11b4, float:1.8618384E38)
                    if (r0 != 0) goto L_0x005c
                    com.xiaomi.smarthome.AppStateNotifier r0 = com.xiaomi.smarthome.application.SHApplication.getStateNotifier()
                    int r0 = r0.a()
                    r3 = 4
                    if (r0 != r3) goto L_0x005c
                    com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
                    java.lang.String r0 = r0.s()
                    java.lang.String r3 = r3
                    boolean r0 = r0.equals(r3)
                    if (r0 != 0) goto L_0x005c
                    com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder r0 = new com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder
                    com.xiaomi.smarthome.framework.navigate.SmartHomeJumper r3 = com.xiaomi.smarthome.framework.navigate.SmartHomeJumper.this
                    android.app.Activity r3 = r3.f16637a
                    r0.<init>(r3)
                    com.xiaomi.smarthome.framework.navigate.SmartHomeJumper r3 = com.xiaomi.smarthome.framework.navigate.SmartHomeJumper.this
                    android.app.Activity r3 = r3.f16637a
                    r4 = 2131497438(0x7f0c11de, float:1.861847E38)
                    java.lang.String r3 = r3.getString(r4)
                    com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder r0 = r0.b((java.lang.CharSequence) r3)
                    com.xiaomi.smarthome.framework.navigate.SmartHomeJumper$1$2 r3 = new com.xiaomi.smarthome.framework.navigate.SmartHomeJumper$1$2
                    r3.<init>()
                    com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder r0 = r0.a((int) r2, (android.content.DialogInterface.OnClickListener) r3)
                    r2 = 2131493823(0x7f0c03bf, float:1.8611137E38)
                    com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder r0 = r0.b((int) r2, (android.content.DialogInterface.OnClickListener) r1)
                    com.xiaomi.smarthome.framework.navigate.SmartHomeJumper$1$1 r1 = new com.xiaomi.smarthome.framework.navigate.SmartHomeJumper$1$1
                    r1.<init>()
                    com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder r0 = r0.a((com.xiaomi.smarthome.library.common.dialog.MLAlertDialog.DismissCallBack) r1)
                    r0.d()
                    return
                L_0x005c:
                    com.xiaomi.smarthome.device.SmartHomeDeviceManager r0 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
                    java.lang.String r3 = r4
                    com.xiaomi.smarthome.device.Device r0 = r0.b((java.lang.String) r3)
                    if (r0 != 0) goto L_0x00d6
                    com.xiaomi.smarthome.device.SmartHomeDeviceManager r3 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
                    java.util.List r3 = r3.f()
                    if (r3 == 0) goto L_0x00d6
                    java.util.Iterator r3 = r3.iterator()
                L_0x0076:
                    boolean r4 = r3.hasNext()
                    if (r4 == 0) goto L_0x00d6
                    java.lang.Object r4 = r3.next()
                    com.xiaomi.smarthome.device.Device r4 = (com.xiaomi.smarthome.device.Device) r4
                    java.lang.String r5 = r5
                    boolean r5 = android.text.TextUtils.isEmpty(r5)
                    if (r5 != 0) goto L_0x009c
                    boolean r5 = r4 instanceof com.xiaomi.smarthome.device.MiTVDevice
                    if (r5 == 0) goto L_0x009c
                    java.lang.String r5 = r5
                    r6 = r4
                    com.xiaomi.smarthome.device.MiTVDevice r6 = (com.xiaomi.smarthome.device.MiTVDevice) r6
                    java.lang.String r6 = r6.u
                    boolean r5 = r5.equals(r6)
                    if (r5 == 0) goto L_0x009c
                    goto L_0x00d5
                L_0x009c:
                    java.lang.String r5 = r5
                    boolean r5 = android.text.TextUtils.isEmpty(r5)
                    if (r5 != 0) goto L_0x00bb
                    java.lang.String r5 = r4.mac
                    boolean r5 = android.text.TextUtils.isEmpty(r5)
                    if (r5 != 0) goto L_0x00bb
                    java.lang.String r5 = r5
                    java.lang.String r6 = r4.mac
                    java.lang.String r6 = r6.toLowerCase()
                    boolean r5 = r5.equals(r6)
                    if (r5 == 0) goto L_0x00bb
                    goto L_0x00d5
                L_0x00bb:
                    java.lang.String r5 = r4
                    boolean r5 = android.text.TextUtils.isEmpty(r5)
                    if (r5 != 0) goto L_0x0076
                    java.lang.String r5 = r4.did
                    boolean r5 = android.text.TextUtils.isEmpty(r5)
                    if (r5 != 0) goto L_0x0076
                    java.lang.String r5 = r4
                    java.lang.String r6 = r4.did
                    boolean r5 = r5.equals(r6)
                    if (r5 == 0) goto L_0x0076
                L_0x00d5:
                    r0 = r4
                L_0x00d6:
                    if (r0 != 0) goto L_0x010d
                    com.xiaomi.smarthome.device.SmartHomeDeviceManager r3 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
                    java.util.List r3 = r3.k()
                    if (r3 == 0) goto L_0x010d
                    java.util.Iterator r3 = r3.iterator()
                L_0x00e6:
                    boolean r4 = r3.hasNext()
                    if (r4 == 0) goto L_0x010d
                    java.lang.Object r4 = r3.next()
                    com.xiaomi.smarthome.device.Device r4 = (com.xiaomi.smarthome.device.Device) r4
                    java.lang.String r5 = r4
                    boolean r5 = android.text.TextUtils.isEmpty(r5)
                    if (r5 != 0) goto L_0x00e6
                    java.lang.String r5 = r4.did
                    boolean r5 = android.text.TextUtils.isEmpty(r5)
                    if (r5 != 0) goto L_0x00e6
                    java.lang.String r5 = r4
                    java.lang.String r6 = r4.did
                    boolean r5 = r5.equals(r6)
                    if (r5 == 0) goto L_0x00e6
                    r0 = r4
                L_0x010d:
                    if (r0 == 0) goto L_0x01ed
                    boolean r3 = r0.isBinded()
                    if (r3 != 0) goto L_0x0148
                    boolean r3 = r0.canUseNotBind
                    if (r3 != 0) goto L_0x0148
                    com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder r0 = new com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder
                    com.xiaomi.smarthome.framework.navigate.SmartHomeJumper r1 = com.xiaomi.smarthome.framework.navigate.SmartHomeJumper.this
                    android.app.Activity r1 = r1.f16637a
                    r0.<init>(r1)
                    com.xiaomi.smarthome.framework.navigate.SmartHomeJumper r1 = com.xiaomi.smarthome.framework.navigate.SmartHomeJumper.this
                    android.app.Activity r1 = r1.f16637a
                    r3 = 2131497440(0x7f0c11e0, float:1.8618473E38)
                    java.lang.String r1 = r1.getString(r3)
                    com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder r0 = r0.b((java.lang.CharSequence) r1)
                    com.xiaomi.smarthome.framework.navigate.SmartHomeJumper$1$4 r1 = new com.xiaomi.smarthome.framework.navigate.SmartHomeJumper$1$4
                    r1.<init>()
                    com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder r0 = r0.a((int) r2, (android.content.DialogInterface.OnClickListener) r1)
                    com.xiaomi.smarthome.framework.navigate.SmartHomeJumper$1$3 r1 = new com.xiaomi.smarthome.framework.navigate.SmartHomeJumper$1$3
                    r1.<init>()
                    com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder r0 = r0.a((com.xiaomi.smarthome.library.common.dialog.MLAlertDialog.DismissCallBack) r1)
                    r0.d()
                    goto L_0x0233
                L_0x0148:
                    com.xiaomi.smarthome.frame.core.CoreApi r2 = com.xiaomi.smarthome.frame.core.CoreApi.a()
                    java.lang.String r3 = r0.model
                    boolean r2 = r2.c((java.lang.String) r3)
                    if (r2 == 0) goto L_0x01b9
                    com.xiaomi.smarthome.frame.core.CoreApi r1 = com.xiaomi.smarthome.frame.core.CoreApi.a()
                    java.lang.String r2 = r0.model
                    com.xiaomi.smarthome.core.entity.plugin.PluginRecord r5 = r1.d((java.lang.String) r2)
                    android.content.Intent r7 = new android.content.Intent
                    r7.<init>()
                    android.content.Intent r1 = r6
                    if (r1 == 0) goto L_0x016c
                    android.content.Intent r1 = r6
                    r7.putExtras(r1)
                L_0x016c:
                    com.xiaomi.smarthome.framework.navigate.SmartHomeJumper r1 = com.xiaomi.smarthome.framework.navigate.SmartHomeJumper.this
                    android.app.Activity r1 = r1.f16637a
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder
                    r2.<init>()
                    com.xiaomi.smarthome.framework.navigate.SmartHomeJumper r3 = com.xiaomi.smarthome.framework.navigate.SmartHomeJumper.this
                    android.app.Activity r3 = r3.f16637a
                    r4 = 2131497833(0x7f0c1369, float:1.861927E38)
                    java.lang.String r3 = r3.getString(r4)
                    r2.append(r3)
                    java.lang.String r3 = r5.p()
                    r2.append(r3)
                    com.xiaomi.smarthome.framework.navigate.SmartHomeJumper r3 = com.xiaomi.smarthome.framework.navigate.SmartHomeJumper.this
                    android.app.Activity r3 = r3.f16637a
                    r4 = 2131497825(0x7f0c1361, float:1.8619254E38)
                    java.lang.String r3 = r3.getString(r4)
                    r2.append(r3)
                    java.lang.String r2 = r2.toString()
                    com.xiaomi.smarthome.library.common.dialog.XQProgressHorizontalDialog r1 = com.xiaomi.smarthome.library.common.dialog.XQProgressHorizontalDialog.b(r1, r2)
                    com.xiaomi.smarthome.frame.plugin.PluginApi r3 = com.xiaomi.smarthome.frame.plugin.PluginApi.getInstance()
                    com.xiaomi.smarthome.framework.navigate.SmartHomeJumper r2 = com.xiaomi.smarthome.framework.navigate.SmartHomeJumper.this
                    android.app.Activity r4 = r2.f16637a
                    r6 = 1
                    com.xiaomi.smarthome.device.api.DeviceStat r8 = r0.newDeviceStat()
                    r9 = 0
                    r10 = 0
                    com.xiaomi.smarthome.framework.navigate.SmartHomeJumper$1$5 r11 = new com.xiaomi.smarthome.framework.navigate.SmartHomeJumper$1$5
                    r11.<init>(r1)
                    r3.sendMessage(r4, r5, r6, r7, r8, r9, r10, r11)
                    goto L_0x0233
                L_0x01b9:
                    android.content.Intent r2 = r6
                    if (r2 == 0) goto L_0x01c3
                    android.content.Intent r1 = r6
                    android.os.Bundle r1 = r1.getExtras()
                L_0x01c3:
                    r9 = r1
                    com.xiaomi.smarthome.device.renderer.DeviceRenderer r6 = com.xiaomi.smarthome.device.renderer.DeviceRenderer.a((com.xiaomi.smarthome.device.Device) r0)
                    com.xiaomi.smarthome.framework.navigate.SmartHomeJumper r1 = com.xiaomi.smarthome.framework.navigate.SmartHomeJumper.this
                    android.app.Activity r1 = r1.f16637a
                    android.content.Context r8 = r1.getApplicationContext()
                    r10 = 0
                    r11 = 0
                    r7 = r0
                    android.content.Intent r1 = r6.a((com.xiaomi.smarthome.device.Device) r7, (android.content.Context) r8, (android.os.Bundle) r9, (boolean) r10, (com.xiaomi.smarthome.device.renderer.DeviceRenderer.LoadingCallback) r11)
                    if (r1 == 0) goto L_0x01e5
                    com.xiaomi.smarthome.framework.navigate.SmartHomeJumper r2 = com.xiaomi.smarthome.framework.navigate.SmartHomeJumper.this     // Catch:{ Exception -> 0x01e1 }
                    android.app.Activity r2 = r2.f16637a     // Catch:{ Exception -> 0x01e1 }
                    r2.startActivity(r1)     // Catch:{ Exception -> 0x01e1 }
                    goto L_0x01e5
                L_0x01e1:
                    r1 = 2
                    com.xiaomi.smarthome.framework.statistic.StatHelper.a((int) r1, (com.xiaomi.smarthome.device.Device) r0)
                L_0x01e5:
                    com.xiaomi.smarthome.framework.navigate.SmartHomeJumper r0 = com.xiaomi.smarthome.framework.navigate.SmartHomeJumper.this
                    android.app.Activity r0 = r0.f16637a
                    r0.finish()
                    goto L_0x0233
                L_0x01ed:
                    boolean r0 = r8
                    if (r0 == 0) goto L_0x021d
                    com.xiaomi.smarthome.framework.navigate.SmartHomeJumper r0 = com.xiaomi.smarthome.framework.navigate.SmartHomeJumper.this
                    com.xiaomi.smarthome.framework.navigate.SmartHomeJumper$JumperRefreshListener r8 = new com.xiaomi.smarthome.framework.navigate.SmartHomeJumper$JumperRefreshListener
                    com.xiaomi.smarthome.framework.navigate.SmartHomeJumper r2 = com.xiaomi.smarthome.framework.navigate.SmartHomeJumper.this
                    java.lang.String r3 = r4
                    java.lang.String r4 = r5
                    java.lang.String r5 = r3
                    android.content.Intent r6 = r6
                    boolean r7 = r7
                    r1 = r8
                    r1.<init>(r3, r4, r5, r6, r7)
                    com.xiaomi.smarthome.framework.navigate.SmartHomeJumper.JumperRefreshListener unused = r0.b = r8
                    com.xiaomi.smarthome.device.SmartHomeDeviceManager r0 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
                    com.xiaomi.smarthome.framework.navigate.SmartHomeJumper r1 = com.xiaomi.smarthome.framework.navigate.SmartHomeJumper.this
                    com.xiaomi.smarthome.framework.navigate.SmartHomeJumper$JumperRefreshListener r1 = r1.b
                    r0.a((com.xiaomi.smarthome.device.SmartHomeDeviceManager.IClientDeviceListener) r1)
                    com.xiaomi.smarthome.device.SmartHomeDeviceManager r0 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
                    r0.p()
                    goto L_0x0233
                L_0x021d:
                    com.xiaomi.smarthome.framework.navigate.SmartHomeJumper r0 = com.xiaomi.smarthome.framework.navigate.SmartHomeJumper.this
                    android.app.Activity r0 = r0.f16637a
                    r1 = 2131497444(0x7f0c11e4, float:1.8618481E38)
                    r2 = 0
                    android.widget.Toast r0 = android.widget.Toast.makeText(r0, r1, r2)
                    r0.show()
                    com.xiaomi.smarthome.framework.navigate.SmartHomeJumper r0 = com.xiaomi.smarthome.framework.navigate.SmartHomeJumper.this
                    android.app.Activity r0 = r0.f16637a
                    r0.finish()
                L_0x0233:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.navigate.SmartHomeJumper.AnonymousClass1.a():void");
            }

            public void b() {
                MultiHomeDeviceManager.a().b();
                SmartHomeDeviceManager.a().v();
                SceneManager.x().y();
                SmartHomeDeviceManager.a().p();
                Intent intent = new Intent(SmartHomeJumper.this.f16637a, SmartHomeMainActivity.class);
                intent.putExtra("user_id", str4);
                SmartHomeJumper.this.f16637a.startActivity(intent);
                SmartHomeJumper.this.f16637a.finish();
            }
        });
    }
}
