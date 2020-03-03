package com.xiaomi.smarthome.library.bluetooth.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.TypedValue;
import android.widget.TextView;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.library.R;
import com.xiaomi.smarthome.library.bluetooth.BluetoothContextManager;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleResponse;
import com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearchResult;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import com.xiaomi.smarthome.library.common.util.ListUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BluetoothUtils extends BluetoothContextManager {

    /* renamed from: a  reason: collision with root package name */
    private static BluetoothManager f18548a;
    private static BluetoothAdapter c;

    public static String a(int i) {
        switch (i) {
            case 1:
                return "classic";
            case 2:
                return "ble";
            default:
                return "unknown";
        }
    }

    public static boolean a() {
        return Build.VERSION.SDK_INT >= 18 && n() != null && n().getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
    }

    public static boolean b() {
        BluetoothAdapter e = e();
        return e != null && e.isEnabled();
    }

    public static int c() {
        return e().getState();
    }

    public static void a(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        b(broadcastReceiver, intentFilter);
    }

    public static void a(BroadcastReceiver broadcastReceiver) {
        try {
            b(broadcastReceiver);
        } catch (Throwable th) {
            BluetoothLog.a(th);
        }
    }

    public static void a(Intent intent) {
        b(intent);
    }

    public static void a(String str) {
        b(new Intent(str));
    }

    public static void a(final String str, long j) {
        a((Runnable) new Runnable() {
            public void run() {
                BluetoothUtils.a(str);
            }
        }, j);
    }

    private static void b(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        n().registerReceiver(broadcastReceiver, intentFilter);
    }

    private static void b(BroadcastReceiver broadcastReceiver) {
        n().unregisterReceiver(broadcastReceiver);
    }

    private static void b(Intent intent) {
        n().sendBroadcast(intent);
    }

    public static BluetoothManager d() {
        if (!a()) {
            return null;
        }
        if (f18548a == null) {
            f18548a = (BluetoothManager) n().getSystemService(BleDevice.f14751a);
        }
        return f18548a;
    }

    public static BluetoothAdapter e() {
        if (c == null) {
            c = BluetoothAdapter.getDefaultAdapter();
        }
        return c;
    }

    public static boolean f() {
        BluetoothAdapter e;
        if (!b() || (e = e()) == null) {
            return false;
        }
        return e.disable();
    }

    public static BluetoothDevice b(String str) {
        BluetoothAdapter e;
        if (TextUtils.isEmpty(str) || (e = e()) == null) {
            return null;
        }
        return e.getRemoteDevice(str);
    }

    public static List<BluetoothDevice> g() {
        if (Build.VERSION.SDK_INT < 18) {
            return ListUtils.a();
        }
        BluetoothManager d = d();
        if (d != null) {
            return d.getConnectedDevices(7);
        }
        return ListUtils.a();
    }

    public static List<BluetoothSearchResult> h() {
        ArrayList arrayList = new ArrayList();
        List<BluetoothDevice> g = g();
        if (!ListUtils.a(g)) {
            for (BluetoothDevice next : g) {
                BluetoothSearchResult bluetoothSearchResult = new BluetoothSearchResult(next);
                bluetoothSearchResult.a();
                bluetoothSearchResult.a(next.getName());
                arrayList.add(bluetoothSearchResult);
            }
        }
        return arrayList;
    }

    public static List<BluetoothDevice> i() {
        Set<BluetoothDevice> bondedDevices;
        BluetoothAdapter e = e();
        ArrayList arrayList = new ArrayList();
        if (!(e == null || (bondedDevices = e.getBondedDevices()) == null)) {
            arrayList.addAll(bondedDevices);
        }
        return arrayList;
    }

    public static List<BluetoothSearchResult> j() {
        ArrayList arrayList = new ArrayList();
        for (BluetoothDevice next : i()) {
            BluetoothSearchResult bluetoothSearchResult = new BluetoothSearchResult(next);
            bluetoothSearchResult.c();
            bluetoothSearchResult.a(next.getName());
            arrayList.add(bluetoothSearchResult);
        }
        return arrayList;
    }

    @TargetApi(18)
    public static boolean c(String str) {
        if (TextUtils.isEmpty(str) || !a()) {
            return false;
        }
        if (d().getConnectionState(b(str), 7) == 2) {
            return true;
        }
        return false;
    }

    @TargetApi(18)
    public static int d(String str) {
        if (TextUtils.isEmpty(str) || !a()) {
            return -1;
        }
        return d().getConnectionState(b(str), 7);
    }

    @TargetApi(18)
    public static boolean e(String str) {
        if (TextUtils.isEmpty(str) || !a()) {
            return false;
        }
        if (d().getConnectionState(b(str), 7) == 0) {
            return true;
        }
        return false;
    }

    public static boolean a(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null || !a() || Build.VERSION.SDK_INT < 18 || d().getConnectionState(bluetoothDevice, 7) != 2) {
            return false;
        }
        return true;
    }

    private static boolean b(BluetoothDevice bluetoothDevice) {
        return bluetoothDevice != null && bluetoothDevice.getBondState() == 12;
    }

    public static boolean a(BluetoothSearchResult bluetoothSearchResult) {
        if (bluetoothSearchResult.b()) {
            return a(bluetoothSearchResult.f());
        }
        if (bluetoothSearchResult.d()) {
            return b(bluetoothSearchResult.f());
        }
        return false;
    }

    public static boolean f(String str) {
        BluetoothDevice b = b(str);
        if (Build.VERSION.SDK_INT < 18 || b.getType() == 1) {
            return b(b);
        }
        return a(b);
    }

    public static boolean g(String str) {
        BluetoothDevice b;
        Method method;
        try {
            if (!(TextUtils.isEmpty(str) || (b = b(str)) == null || (method = b.getClass().getMethod("removeBond", new Class[0])) == null)) {
                return ((Boolean) method.invoke(b, new Object[0])).booleanValue();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return false;
    }

    public static void a(Context context) {
        a(context, 16);
    }

    public static void a(Context context, int i) {
        if (context != null && (context instanceof Activity) && !b()) {
            ((Activity) context).startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), i);
        }
    }

    public static MLAlertDialog b(Context context, int i) {
        if (context == null || !(context instanceof Activity)) {
            return null;
        }
        TextView textView = new TextView(context);
        textView.setTextSize(2, 14.0f);
        textView.setGravity(19);
        textView.setText(i);
        int applyDimension = (int) TypedValue.applyDimension(1, 16.0f, context.getResources().getDisplayMetrics());
        return new MLAlertDialog.Builder(context).a(textView, applyDimension, applyDimension, applyDimension, applyDimension).b();
    }

    public static void a(final Context context, int i, final BleResponse bleResponse) {
        if (context != null && (context instanceof Activity) && !b()) {
            TextView textView = new TextView(context);
            textView.setTextSize(2, 14.0f);
            textView.setGravity(19);
            textView.setText(R.string.opening_bluetooth);
            int applyDimension = (int) TypedValue.applyDimension(1, 16.0f, context.getResources().getDisplayMetrics());
            final MLAlertDialog b = new MLAlertDialog.Builder(context).a(textView, applyDimension, applyDimension, applyDimension, applyDimension).b();
            new Handler(Looper.getMainLooper());
            final AnonymousClass2 r1 = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (intent.getAction().equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                        int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0);
                        if (intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", 0) != 12 && intExtra == 12) {
                            b.dismiss();
                            try {
                                context.unregisterReceiver(this);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (bleResponse != null) {
                                bleResponse.a(0, null);
                            }
                        }
                    }
                }
            };
            new MLAlertDialog.Builder(context).b(i).d(true).b(R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (bleResponse != null) {
                        bleResponse.a(-2, null);
                    }
                }
            }).a(R.string.open_tips, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    b.show();
                    AsyncTaskUtils.a(new AsyncTask<Object, Object, Boolean>() {
                        /* access modifiers changed from: protected */
                        /* renamed from: a */
                        public Boolean doInBackground(Object... objArr) {
                            return Boolean.valueOf(BluetoothUtils.k());
                        }

                        /* access modifiers changed from: protected */
                        /* renamed from: a */
                        public void onPostExecute(Boolean bool) {
                            if (bool.booleanValue()) {
                                context.registerReceiver(r1, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
                                return;
                            }
                            b.dismiss();
                        }
                    }, new Object[0]);
                }
            }).a(Color.parseColor("#FF0099FF"), -1).b().show();
        }
    }

    public static boolean k() {
        BluetoothAdapter e;
        if (b() || (e = e()) == null) {
            return false;
        }
        return e.enable();
    }

    public static boolean a(BluetoothGatt bluetoothGatt) {
        boolean z;
        if (bluetoothGatt != null) {
            try {
                Method method = BluetoothGatt.class.getMethod("refresh", new Class[0]);
                if (method != null) {
                    method.setAccessible(true);
                    z = ((Boolean) method.invoke(bluetoothGatt, new Object[0])).booleanValue();
                    BluetoothLog.c(String.format("refreshDeviceCache return %b", new Object[]{Boolean.valueOf(z)}));
                    return z;
                }
            } catch (Exception e) {
                BluetoothLog.a((Throwable) e);
            }
        }
        z = false;
        BluetoothLog.c(String.format("refreshDeviceCache return %b", new Object[]{Boolean.valueOf(z)}));
        return z;
    }
}
