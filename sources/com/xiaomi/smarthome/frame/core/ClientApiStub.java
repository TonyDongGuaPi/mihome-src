package com.xiaomi.smarthome.frame.core;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.content.LocalBroadcastManager;
import com.xiaomi.smarthome.core.client.IClientApi;
import com.xiaomi.smarthome.core.entity.device.Device;
import com.xiaomi.smarthome.core.entity.message.CoreMessageType;
import com.xiaomi.smarthome.core.server.IServerCallback;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import java.util.Locale;

public class ClientApiStub extends IClientApi.Stub {
    public static final String ACTION_UPDATE_DEVICE_LIST = "update_device_list_action";
    public static final String ACTION_UPNP_DEVICE_CHANGED = "upnp_device_changed_action";
    public static final String ACTION_UPNP_DEVICE_EVENT = "upnp_device_event";
    public static volatile long sOnCoreReadyTime;
    private Context mAppContext;
    private CoreHostApi mCoreHostApi;

    public ClientApiStub(Context context, CoreHostApi coreHostApi) {
        this.mAppContext = context;
        this.mCoreHostApi = coreHostApi;
    }

    public void onAccountReady(boolean z, String str) throws RemoteException {
        if (GlobalSetting.u) {
            LogUtilGrey.a("login", "ClientApiStub onAccountReady isMiLoggedIn=" + z);
        }
        CoreApi.a().a(z, str);
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(this.mAppContext);
        Intent intent = new Intent("CoreApi.onAccountReadyInternal");
        intent.putExtra("isMiLoggedIn", z);
        intent.putExtra("miId", str);
        instance.sendBroadcast(intent);
        if (this.mCoreHostApi != null) {
            this.mCoreHostApi.a();
        }
    }

    public void onGlobalDynamicSettingReady() throws RemoteException {
        CoreApi.a().b();
        LocalBroadcastManager.getInstance(this.mAppContext).sendBroadcast(new Intent("CoreApi.onGlobalDynamicSettingReadyInternal"));
        if (this.mCoreHostApi != null) {
            this.mCoreHostApi.b();
        }
    }

    public void onStatisticReady() throws RemoteException {
        CoreApi.a().c();
        LocalBroadcastManager.getInstance(this.mAppContext).sendBroadcast(new Intent("CoreApi.onStatisticReadyInternal"));
        if (this.mCoreHostApi != null) {
            this.mCoreHostApi.c();
        }
    }

    public void onPluginReady() throws RemoteException {
        CoreApi.a().d();
        LocalBroadcastManager.getInstance(this.mAppContext).sendBroadcast(new Intent("CoreApi.onPluginReadyInternal"));
        if (this.mCoreHostApi != null) {
            this.mCoreHostApi.d();
        }
    }

    public void onCoreReady() throws RemoteException {
        LogUtil.a("ClientApiStub", "onCoreReady");
        sOnCoreReadyTime = System.currentTimeMillis();
        CoreApi.a().e();
        LogUtil.a("ClientApiStub", "onPluginChanged will be called");
        CoreApi.a().a(true, true, (String) null);
        LocalBroadcastManager.getInstance(this.mAppContext).sendBroadcast(new Intent("CoreApi.onCoreReadyInternal"));
        CoreApi.a().f();
        LocalBroadcastManager.getInstance(this.mAppContext).sendBroadcast(new Intent("CoreApi.onPluginCacheReadyInternal"));
        if (this.mCoreHostApi != null) {
            this.mCoreHostApi.e();
        }
    }

    public void refreshServiceToken(String str, String str2, boolean z, String str3, IServerCallback iServerCallback) throws RemoteException {
        if (this.mCoreHostApi != null) {
            this.mCoreHostApi.a(str, str2, z, str3, iServerCallback);
        }
    }

    public void onUnAuthorized() throws RemoteException {
        CoreApi.a().r();
        CoreApi.a().t();
        if (this.mCoreHostApi != null) {
            this.mCoreHostApi.f();
        }
    }

    public void onCoreMessage(CoreMessageType coreMessageType, Bundle bundle) throws RemoteException {
        if (coreMessageType.ordinal() == CoreMessageType.UPDATE_DEVICE_LIST.ordinal()) {
            Intent intent = new Intent(ACTION_UPDATE_DEVICE_LIST);
            bundle.setClassLoader(Device.class.getClassLoader());
            intent.putExtra("params", bundle);
            LocalBroadcastManager.getInstance(this.mAppContext).sendBroadcast(intent);
        } else if (coreMessageType.ordinal() == CoreMessageType.UPNP_DEVICE_CHANGED.ordinal()) {
            Intent intent2 = new Intent(ACTION_UPNP_DEVICE_CHANGED);
            bundle.setClassLoader(Device.class.getClassLoader());
            intent2.putExtra("params", bundle);
            LocalBroadcastManager.getInstance(this.mAppContext).sendBroadcast(intent2);
        } else if (coreMessageType.ordinal() == CoreMessageType.UPNP_DEVICE_EVENT.ordinal()) {
            Intent intent3 = new Intent(ACTION_UPNP_DEVICE_EVENT);
            bundle.setClassLoader(Device.class.getClassLoader());
            intent3.putExtra("params", bundle);
        }
    }

    public void onServerChanged(ServerBean serverBean, ServerBean serverBean2) throws RemoteException {
        if (this.mCoreHostApi != null) {
            this.mCoreHostApi.a(serverBean, serverBean2);
        }
    }

    public void onLocaleChanged(Bundle bundle, Bundle bundle2) throws RemoteException {
        if (this.mCoreHostApi != null) {
            this.mCoreHostApi.a((Locale) bundle.getSerializable("result"), (Locale) bundle2.getSerializable("result"));
        }
    }

    public void onPluginChanged(boolean z, boolean z2, String str) throws RemoteException {
        CoreApi.a().a(z, z2, str);
    }

    public void onActivityResume(int i, int i2, String str) throws RemoteException {
        if (this.mCoreHostApi != null) {
            this.mCoreHostApi.a(i, i2, str);
        }
    }

    public void onBleCharacterChanged(Bundle bundle) {
        if (this.mCoreHostApi != null) {
            this.mCoreHostApi.a(bundle);
        }
    }
}
