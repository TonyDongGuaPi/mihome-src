package com.xiaomi.smarthome.device;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.entity.upnp.UPnPConstants;
import com.xiaomi.smarthome.core.entity.upnp.UPnPDeviceEvent;
import com.xiaomi.smarthome.core.entity.upnp.UPnPRequest;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.KeyValuePair;
import com.xiaomi.smarthome.download.Downloads;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.ClientApiStub;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.upnp.UPnPApi;
import java.util.ArrayList;
import java.util.List;

public class UPnPDeviceHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f15001a = "UPnPHelper";
    private static final Byte[] b = new Byte[0];
    private static volatile UPnPDeviceHelper c = null;
    private BroadcastReceiver d = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            UPnPDeviceEvent uPnPDeviceEvent;
            Bundle bundleExtra = intent.getBundleExtra("params");
            if (bundleExtra != null) {
                if (ClientApiStub.ACTION_UPNP_DEVICE_CHANGED.equals(intent.getAction())) {
                    String string = bundleExtra.getString(UPnPConstants.f13998a, (String) null);
                    if (string != null) {
                        UPnPDeviceHelper.this.b(string);
                        return;
                    }
                    String string2 = bundleExtra.getString(UPnPConstants.b, (String) null);
                    if (string2 != null) {
                        UPnPDeviceHelper.this.a(string2);
                    }
                } else if (ClientApiStub.ACTION_UPNP_DEVICE_EVENT.equals(intent.getAction()) && (uPnPDeviceEvent = (UPnPDeviceEvent) bundleExtra.getParcelable(UPnPConstants.c)) != null) {
                    UPnPDeviceHelper.this.a(uPnPDeviceEvent);
                }
            }
        }
    };

    public static UPnPDeviceHelper a() {
        if (c == null) {
            synchronized (b) {
                if (c == null) {
                    c = new UPnPDeviceHelper();
                    c.b();
                }
            }
        }
        return c;
    }

    private UPnPDeviceHelper() {
    }

    private void b() {
        IntentFilter intentFilter = new IntentFilter(ClientApiStub.ACTION_UPNP_DEVICE_CHANGED);
        intentFilter.addAction(ClientApiStub.ACTION_UPNP_DEVICE_EVENT);
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(this.d, intentFilter);
    }

    public String a(String str, String str2) {
        return (str == null || str.isEmpty() || str2 == null || str2.isEmpty()) ? "" : CoreApi.a().b(str, str2);
    }

    public void a(String str, String str2, String str3, List<KeyValuePair> list, final Callback<String> callback) {
        UPnPRequest uPnPRequest = new UPnPRequest();
        uPnPRequest.f14000a = str;
        uPnPRequest.b = str2;
        uPnPRequest.c = str3;
        ArrayList<com.xiaomi.smarthome.core.entity.net.KeyValuePair> arrayList = new ArrayList<>();
        for (KeyValuePair next : list) {
            arrayList.add(new com.xiaomi.smarthome.core.entity.net.KeyValuePair(next.getKey(), next.getValue()));
        }
        uPnPRequest.d = arrayList;
        CoreApi.a().a(uPnPRequest, (AsyncCallback<String, Error>) new AsyncCallback<String, Error>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                if (callback != null) {
                    callback.onSuccess(str);
                }
            }

            public void onFailure(Error error) {
                if (callback != null) {
                    callback.onFailure(error.a(), error.b());
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(UPnPDeviceEvent uPnPDeviceEvent) {
        if (uPnPDeviceEvent != null) {
            UPnPApi.EventData eventData = new UPnPApi.EventData();
            eventData.udn = uPnPDeviceEvent.f13999a;
            eventData.seq = uPnPDeviceEvent.b;
            eventData.name = uPnPDeviceEvent.c;
            eventData.value = uPnPDeviceEvent.d;
            Intent intent = new Intent();
            intent.putExtra(UPnPApi.EVENT_KEY, eventData);
            PluginRecord d2 = CoreApi.a().d("chuangmi.wifi.v1");
            if (d2 != null) {
                PluginApi.getInstance().sendMessage(SHApplication.getAppContext(), d2, 16, intent, (DeviceStat) null, (RunningProcess) null, false, (PluginApi.SendMessageCallback) null);
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        PluginRecord d2 = CoreApi.a().d("chuangmi.wifi.v1");
        Intent intent = new Intent();
        if (str != null) {
            intent.putExtra(Downloads.COLUMN_UDN, str);
        }
        if (d2 == null) {
            Miio.b(f15001a, "plugin record is null");
            return;
        }
        Miio.b(f15001a, "upnp disconnect");
        PluginApi.getInstance().sendMessage(SHApplication.getAppContext(), d2, 13, intent, (DeviceStat) null, (RunningProcess) null, false, (PluginApi.SendMessageCallback) null);
    }

    /* access modifiers changed from: private */
    public void b(String str) {
        PluginRecord d2 = CoreApi.a().d("chuangmi.wifi.v1");
        Intent intent = new Intent();
        if (str != null) {
            intent.putExtra(Downloads.COLUMN_UDN, str);
        }
        if (d2 == null) {
            Miio.b(f15001a, "plugin record is null");
            return;
        }
        Miio.b(f15001a, "upnp connect");
        PluginApi.getInstance().sendMessage(SHApplication.getAppContext(), d2, 12, intent, (DeviceStat) null, (RunningProcess) null, false, (PluginApi.SendMessageCallback) null);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this.d);
        c = null;
        super.finalize();
    }
}
