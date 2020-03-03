package com.xiaomi.smarthome.core.server.internal.bluetooth.channel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import cn.com.fmsh.script.constants.ScriptToolsConst;
import com.xiaomi.smarthome.core.server.bluetooth.IBleChannelReader;
import com.xiaomi.smarthome.core.server.bluetooth.IBleChannelWriter;
import com.xiaomi.smarthome.core.server.bluetooth.IBleResponse;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.channel.Channel;
import com.xiaomi.smarthome.library.bluetooth.channel.ChannelCallback;
import com.xiaomi.smarthome.library.bluetooth.connect.BleConnectManager;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleRequestMtuResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleWriteResponse;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public abstract class ChannelManager {

    /* renamed from: a  reason: collision with root package name */
    public static final int f14151a = 6;
    protected static final String b = "action.online.status.changed";
    public static final String c = "extra_mac";
    public static final String d = "extra_online_status";
    private static final int e = 247;
    private static final int f = 5;
    private static final int g = 23;
    /* access modifiers changed from: private */
    public static ConcurrentMap<String, Integer> i = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public static ConcurrentMap<String, Integer> j = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public static ConcurrentMap<String, Boolean> k = new ConcurrentHashMap();
    private ConcurrentMap<String, BleChannel> h = new ConcurrentHashMap();
    private BroadcastReceiver l = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            BluetoothLog.d("channel manager receive action=" + action);
            if (TextUtils.equals(action, "com.xiaomi.smarthome.bluetooth.connect_status_changed")) {
                String stringExtra = intent.getStringExtra("key_device_address");
                if (intent.getIntExtra("key_connect_status", 5) == 32) {
                    ChannelManager.k.put(stringExtra, false);
                    ChannelManager.a(stringExtra);
                    BluetoothLog.a("channel manager receive device mac=%s, disconnected", stringExtra);
                    ChannelManager.this.b(stringExtra);
                }
            }
        }
    };

    public static abstract class BleChannelReader extends IBleChannelReader.Stub {
    }

    private int a(int i2) {
        return i2 - 5;
    }

    public abstract void a(String str, List<byte[]> list, ChannelCallback channelCallback);

    public abstract void a(String str, byte[] bArr, ChannelCallback channelCallback, boolean z);

    public abstract boolean a();

    /* access modifiers changed from: protected */
    public void b(String str) {
    }

    ChannelManager() {
        IntentFilter intentFilter = new IntentFilter("com.xiaomi.smarthome.bluetooth.connect_status_changed");
        intentFilter.addAction("action.online.status.changed");
        BluetoothUtils.a(this.l, intentFilter);
    }

    public static void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            j.remove(str);
            i.remove(str);
        }
    }

    public void c(String str) {
        Integer num = (Integer) i.get(str);
        Boolean bool = (Boolean) k.get(str);
        if (num != null) {
            return;
        }
        if (bool == null || !bool.booleanValue()) {
            k.put(str, true);
            BluetoothLog.a("channel manager receive device mac=%s, connected,send request mtu ", str);
            a(str, (int) e, (BleRequestMtuResponse) null);
        }
    }

    public synchronized void a(String str, IBleChannelReader iBleChannelReader) {
        BleChannel bleChannel = (BleChannel) this.h.get(str);
        if (bleChannel != null) {
            bleChannel.b(iBleChannelReader);
        }
    }

    public synchronized IBleChannelWriter b(String str, IBleChannelReader iBleChannelReader) {
        BleChannel bleChannel;
        BluetoothLog.c(String.format("registerChannelReader mac =%s ", new Object[]{str}));
        bleChannel = (BleChannel) this.h.get(str);
        if (bleChannel == null) {
            bleChannel = new BleChannel(str, this, a());
            this.h.put(str, bleChannel);
        }
        bleChannel.a(iBleChannelReader);
        return bleChannel.e;
    }

    public synchronized IBleChannelWriter a(String str, boolean z, IBleChannelReader iBleChannelReader, BleRequestMtuResponse bleRequestMtuResponse) {
        BleChannel bleChannel;
        BluetoothLog.c(String.format("registerChannelReader mac =%s ", new Object[]{str}));
        bleChannel = (BleChannel) this.h.get(str);
        if (bleChannel == null) {
            bleChannel = new BleChannel(str, this, a());
            this.h.put(str, bleChannel);
        }
        bleChannel.a(iBleChannelReader);
        return bleChannel.e;
    }

    private void a(final String str, final int i2, final BleRequestMtuResponse bleRequestMtuResponse) {
        BleConnectManager.a().a(str, i2, (BleRequestMtuResponse) new BleRequestMtuResponse() {
            public void a(int i, Integer num) {
                BluetoothLog.d("request mtu ,mac =" + str + ",mtu=" + i2 + ",resp code =" + i + ",data=" + num);
                if (i != 0 || num == null) {
                    ChannelManager.i.put(str, 23);
                    if (bleRequestMtuResponse != null) {
                        bleRequestMtuResponse.a(0, null);
                        return;
                    }
                    return;
                }
                ChannelManager.i.put(str, num);
                ChannelManager.this.d(str);
            }
        });
    }

    public void d(final String str) {
        byte[] bArr = {ScriptToolsConst.TagName.CommandMultiple};
        BluetoothLog.a("channel manager receive device mac=%s, connected,send A4 again", str);
        BleConnectManager.a().a(str, BluetoothConstants.i, BluetoothConstants.M, bArr, (BleWriteResponse) new BleWriteResponse() {
            public void a(int i, Void voidR) {
                BluetoothLog.a("ChannelManager write 0xA4 ,mac = %s,response code =%d", str, Integer.valueOf(i));
            }
        });
    }

    public synchronized IBleChannelWriter e(String str) {
        BleChannel bleChannel = (BleChannel) this.h.get(str);
        if (bleChannel == null) {
            return null;
        }
        return bleChannel.e;
    }

    public synchronized BleChannel f(String str) {
        return (BleChannel) this.h.get(str);
    }

    public static class BleChannel extends Channel {
        private String d;
        /* access modifiers changed from: private */
        public IBleChannelWriter e = new IBleChannelWriter.Stub() {
            public void write(byte[] bArr, int i, final IBleResponse iBleResponse) throws RemoteException {
                BleChannel.this.a(bArr, i, (ChannelCallback) new ChannelCallback() {
                    public void a(int i) {
                        try {
                            if (iBleResponse != null) {
                                iBleResponse.onResponse(i, (Bundle) null);
                            }
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            public void writeWithOpCode(int i, byte[] bArr, int i2, final IBleResponse iBleResponse) throws RemoteException {
                BleChannel.this.a(i, bArr, i2, new ChannelCallback() {
                    public void a(int i) {
                        try {
                            if (iBleResponse != null) {
                                iBleResponse.onResponse(i, (Bundle) null);
                            }
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        };
        private List<IBleChannelReader> f = new ArrayList();
        private ChannelManager g;
        private boolean h;

        BleChannel(String str, ChannelManager channelManager, boolean z) {
            this.d = str;
            this.g = channelManager;
            this.h = z;
        }

        /* access modifiers changed from: package-private */
        public void a(IBleChannelReader iBleChannelReader) {
            if (iBleChannelReader != null) {
                Iterator<IBleChannelReader> it = this.f.iterator();
                while (it.hasNext()) {
                    IBleChannelReader next = it.next();
                    if (next != null && next.asBinder() != null && next.asBinder().equals(iBleChannelReader.asBinder())) {
                        return;
                    }
                    if (next == null || next.asBinder() == null) {
                        it.remove();
                    }
                }
                this.f.add(iBleChannelReader);
            }
        }

        public int a() {
            return this.f.size();
        }

        /* access modifiers changed from: package-private */
        public void b(IBleChannelReader iBleChannelReader) {
            if (iBleChannelReader != null) {
                this.f.remove(iBleChannelReader);
            }
        }

        public void a(byte[] bArr, ChannelCallback channelCallback, boolean z) {
            this.g.a(this.d, bArr, channelCallback, z);
        }

        public void a(List<byte[]> list, ChannelCallback channelCallback) {
            this.g.a(this.d, list, channelCallback);
        }

        public void a(byte[] bArr, int i) {
            for (IBleChannelReader next : this.f) {
                if (next != null) {
                    try {
                        next.onRead(this.d, bArr, i);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }

        public boolean b() {
            return this.h;
        }

        public int c() {
            ChannelManager channelManager = this.g;
            Integer num = (Integer) ChannelManager.i.get(this.d);
            if (num == null || num.intValue() < 5) {
                return 18;
            }
            return num.intValue() - 5;
        }

        public int d() {
            ChannelManager channelManager = this.g;
            Integer num = (Integer) ChannelManager.j.get(this.d);
            if (num == null || num.intValue() < 5) {
                return 18;
            }
            return num.intValue();
        }

        public void a(int i) {
            ChannelManager channelManager = this.g;
            ChannelManager.j.put(this.d, Integer.valueOf(i));
        }

        public int e() {
            return d() == 18 ? 1 : 6;
        }
    }
}
