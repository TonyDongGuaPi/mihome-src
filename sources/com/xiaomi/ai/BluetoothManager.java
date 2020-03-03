package com.xiaomi.ai;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.media.AudioManager;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.ai.utils.Log;

public class BluetoothManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9895a = "MiSpeechSDK:BluetoothManager";
    private static volatile BluetoothManager j;
    private AudioManager b;
    private BluetoothAdapter c;
    /* access modifiers changed from: private */
    public BluetoothDevice d;
    /* access modifiers changed from: private */
    public BluetoothHeadset e;
    private Context f;
    /* access modifiers changed from: private */
    public boolean g = false;
    /* access modifiers changed from: private */
    public volatile boolean h;
    private BroadcastReceiver i = new a(this);
    private BluetoothProfile.ServiceListener k = new b(this);

    private BluetoothManager(Context context) {
        this.f = context;
        this.b = (AudioManager) context.getSystemService("audio");
        try {
            this.c = BluetoothAdapter.getDefaultAdapter();
        } catch (RuntimeException e2) {
            Log.b(f9895a, "Cant get default bluetooth adapter ", e2);
        }
    }

    public static BluetoothManager a(Context context) {
        if (j == null) {
            synchronized (BluetoothManager.class) {
                if (j == null) {
                    j = new BluetoothManager(context);
                }
            }
        }
        return j;
    }

    public void a(boolean z) {
        String str;
        String str2;
        String str3;
        String str4;
        long currentTimeMillis = System.currentTimeMillis();
        Log.e(f9895a, "BluetoothManager(" + z + Operators.BRACKET_END_STR);
        if (!a()) {
            str = f9895a;
            str2 = "can't bluetooth";
        } else {
            if (!this.g) {
                Log.e(f9895a, "getProfileProxy ok ");
                this.c.getProfileProxy(this.f, this.k, 1);
                this.g = true;
            }
            if (this.e != null) {
                boolean isAudioConnected = this.e.isAudioConnected(this.d);
                if (z && !isAudioConnected) {
                    if (this.e.startVoiceRecognition(this.d)) {
                        str3 = f9895a;
                        str4 = "start success";
                    } else {
                        str3 = f9895a;
                        str4 = "start fail already";
                    }
                    Log.f(str3, str4);
                } else if (z || !isAudioConnected) {
                    Log.f(f9895a, "setBluetoothOn  failed  dst=" + z + " state=" + isAudioConnected);
                } else {
                    Log.f(f9895a, "stop success");
                    this.e.stopVoiceRecognition(this.d);
                }
            } else if (z) {
                Log.f(f9895a, "set mHasPendingRequest true");
                this.h = true;
            } else {
                this.h = false;
            }
            str = f9895a;
            str2 = "setBluetoothOn take time =" + (System.currentTimeMillis() - currentTimeMillis);
        }
        Log.f(str, str2);
    }

    public boolean a() {
        boolean z;
        if (this.c == null) {
            return false;
        }
        if (!this.c.isEnabled() || 2 != this.c.getProfileConnectionState(1)) {
            z = false;
        } else {
            Log.f(f9895a, "hasConnectedDevice true");
            z = true;
        }
        return z && this.b.isBluetoothScoAvailableOffCall();
    }

    public boolean b() {
        return this.e != null && this.e.isAudioConnected(this.d);
    }

    public void c() {
        Log.f(f9895a, "Register BT media receiver");
        if (a()) {
            Log.f(f9895a, "Register BT media receiver success");
            this.c.getProfileProxy(this.f, this.k, 1);
            this.g = true;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.media.ACTION_SCO_AUDIO_STATE_UPDATED");
        intentFilter.addAction("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED");
        this.f.registerReceiver(this.i, intentFilter);
    }

    public void d() {
        try {
            Log.f(f9895a, "Unregister BT media receiver");
            a(false);
            this.f.unregisterReceiver(this.i);
        } catch (Exception e2) {
            Log.b(f9895a, "Failed to unregister media state receiver", e2);
        }
        if (this.g) {
            this.c.closeProfileProxy(1, this.e);
            this.g = false;
        }
    }

    public String e() {
        if (this.d == null) {
            return null;
        }
        return this.d.getAddress();
    }

    public String f() {
        if (this.d == null) {
            return null;
        }
        return this.d.getName();
    }
}
