package com.xiaomi.smarthome.core.server.internal.bluetooth;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.xiaomi.smarthome.core.server.CoreService;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import java.util.UUID;
import miui.bluetooth.ble.MiBleDeviceManager;

public class MiuiSDKHelper implements MiBleDeviceManager.MiBleDeviceManagerListener {

    /* renamed from: a  reason: collision with root package name */
    private static final boolean f14146a = true;
    private MiBleDeviceManager b;
    private int c;

    private MiuiSDKHelper() {
        e();
    }

    public static MiuiSDKHelper a() {
        return MikeyHelperHolder.f14148a;
    }

    private static class MikeyHelperHolder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static MiuiSDKHelper f14148a = new MiuiSDKHelper();

        private MikeyHelperHolder() {
        }
    }

    private void e() {
        AsyncTaskUtils.a(new AsyncTask<Void, Void, Void>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Void doInBackground(Void... voidArr) {
                UUID.randomUUID();
                return null;
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(Void voidR) {
                try {
                    MiuiSDKHelper.this.f();
                } catch (Throwable th) {
                    BluetoothLog.a(th);
                }
            }
        }, new Void[0]);
    }

    /* access modifiers changed from: private */
    public void f() {
        MiBleDeviceManager.createManager(CoreService.getAppContext(), this);
    }

    public void onInit(MiBleDeviceManager miBleDeviceManager) {
        if (miBleDeviceManager != null) {
            this.b = miBleDeviceManager;
            try {
                this.c = this.b.getServiceVersion();
            } catch (Throwable th) {
                BluetoothLog.a(th);
            }
        }
    }

    public boolean a(String str, byte[] bArr) {
        boolean z;
        if (d()) {
            try {
                z = this.b.bindDevice(str, bArr);
            } catch (Throwable th) {
                BluetoothLog.a(th);
            }
            BluetoothLog.e(String.format("bindDevice %s return %b", new Object[]{str, Boolean.valueOf(z)}));
            return z;
        }
        z = false;
        BluetoothLog.e(String.format("bindDevice %s return %b", new Object[]{str, Boolean.valueOf(z)}));
        return z;
    }

    public boolean a(String str) {
        boolean z;
        if (this.b != null) {
            try {
                z = this.b.unbindDevice(str);
            } catch (Throwable th) {
                BluetoothLog.a(th);
            }
            BluetoothLog.e(String.format("unbindDevice %s return %b", new Object[]{str, Boolean.valueOf(z)}));
            return z;
        }
        z = false;
        BluetoothLog.e(String.format("unbindDevice %s return %b", new Object[]{str, Boolean.valueOf(z)}));
        return z;
    }

    public void onDestroy() {
        this.b = null;
    }

    public boolean b() {
        return this.b != null && this.c >= 15;
    }

    public boolean a(String str, int i, boolean z) {
        String str2;
        if (!b()) {
            return false;
        }
        switch (i) {
            case 1:
                str2 = MiBleDeviceManager.SETTING_IMMEDIATE_ALERT_INCALL_IN_CONTACTS_ENABLED;
                break;
            case 2:
                str2 = MiBleDeviceManager.SETTING_IMMEDIATE_ALERT_INCALL_NO_CONTACTS_ENABLED;
                break;
            case 3:
                str2 = MiBleDeviceManager.SETTING_IMMEDIATE_ALERT_ALARM_ENABLED;
                break;
            case 4:
                str2 = MiBleDeviceManager.SETTING_IMMEDIATE_ALERT_SMS_IN_CONTACTS_ENABLED;
                break;
            case 5:
                str2 = MiBleDeviceManager.SETTING_IMMEDIATE_ALERT_SMS_NO_CONTACTS_ENABLED;
                break;
            default:
                return false;
        }
        return this.b.setSettings(str, str2, z);
    }

    public boolean c() {
        return this.b != null && this.c >= 3;
    }

    public boolean d() {
        return this.b != null && this.c >= 13;
    }

    public boolean b(String str) {
        return c() && !TextUtils.isEmpty(str) && this.b != null && this.b.getDeviceType(str) == 69;
    }
}
