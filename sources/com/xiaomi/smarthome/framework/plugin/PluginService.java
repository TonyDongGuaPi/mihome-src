package com.xiaomi.smarthome.framework.plugin;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Location;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.react.bridge.WritableArray;
import com.google.android.exoplayer2.C;
import com.miui.tsmclient.net.TSMAuthContants;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.router.miio.miioplugin.DeviceStatus;
import com.xiaomi.router.miio.miioplugin.DeviceTagInfo;
import com.xiaomi.router.miio.miioplugin.ILocationCallback;
import com.xiaomi.router.miio.miioplugin.IPluginCallback;
import com.xiaomi.router.miio.miioplugin.IPluginCallback2;
import com.xiaomi.router.miio.miioplugin.IPluginCallback3;
import com.xiaomi.router.miio.miioplugin.IPluginCallbackDeviceList;
import com.xiaomi.router.miio.miioplugin.IPluginCallbackRoomStatus;
import com.xiaomi.router.miio.miioplugin.IPluginCallbackUserInfo;
import com.xiaomi.router.miio.miioplugin.IPluginRequest;
import com.xiaomi.router.miio.miioplugin.RoomStatus;
import com.xiaomi.smarthome.ad.PluginAdManager;
import com.xiaomi.smarthome.ad.api.Advertisement;
import com.xiaomi.smarthome.ad.api.IAdCallback;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.bluetooth.Response;
import com.xiaomi.smarthome.config.AndroidCommonConfigManager;
import com.xiaomi.smarthome.config.CommonExtraConfigManager;
import com.xiaomi.smarthome.config.model.SupportBleGatewayFirmwareVersion;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.device.api.IBleCallback;
import com.xiaomi.smarthome.device.api.IRecommendSceneItemCallback;
import com.xiaomi.smarthome.device.api.ISceneCallback;
import com.xiaomi.smarthome.device.api.ISceneInfoCallback;
import com.xiaomi.smarthome.device.api.IXmPluginHostActivity;
import com.xiaomi.smarthome.device.api.RecommendSceneItem;
import com.xiaomi.smarthome.device.api.SceneInfo;
import com.xiaomi.smarthome.device.api.UserInfo;
import com.xiaomi.smarthome.device.authorization.DeviceAuthManager;
import com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.BleDispatcher;
import com.xiaomi.smarthome.device.bluetooth.BleGatewayManager;
import com.xiaomi.smarthome.device.bluetooth.MediaButtonReceiver;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothHelper;
import com.xiaomi.smarthome.device.utils.DeviceShortcutUtils;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.device.utils.DeviceTagManager;
import com.xiaomi.smarthome.devicesubscribe.DevicePropSubscriber;
import com.xiaomi.smarthome.devicesubscribe.DeviceSubscribeManager;
import com.xiaomi.smarthome.devicesubscribe.SubscribeCallback;
import com.xiaomi.smarthome.devicesubscribe.UnSubscribeCallback;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AppUsrExpPlanUtil;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.PluginRuntimeManager;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.api.RemoteAsyncApiHelper;
import com.xiaomi.smarthome.framework.api.RouterLocalApi;
import com.xiaomi.smarthome.framework.location.SHLocationManager;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.framework.navigate.PageUrl;
import com.xiaomi.smarthome.framework.navigate.UrlResolver;
import com.xiaomi.smarthome.framework.page.verify.VerifyManager;
import com.xiaomi.smarthome.framework.plugin.mpk.MpkPluginApi;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;
import com.xiaomi.smarthome.framework.push.listener.DevicePushListener;
import com.xiaomi.smarthome.framework.webview.CommonWebViewActivity;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.initdevice.InitDeviceRoomActivity;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.util.ImageDownloadManager;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.library.http.HttpApi;
import com.xiaomi.smarthome.library.http.Request;
import com.xiaomi.smarthome.library.http.async.AsyncHandler;
import com.xiaomi.smarthome.light.group.LightGroupManager;
import com.xiaomi.smarthome.listcamera.FloatingCameraManager;
import com.xiaomi.smarthome.miio.MiioManager;
import com.xiaomi.smarthome.miio.db.record.ShareUserRecord;
import com.xiaomi.smarthome.miio.user.UserMamanger;
import com.xiaomi.smarthome.mitsmsdk.NfcChannelManager;
import com.xiaomi.smarthome.newui.DeviceMainPage;
import com.xiaomi.smarthome.newui.card.ControlCardInfoManager;
import com.xiaomi.smarthome.newui.card.MiotSpecCardManager;
import com.xiaomi.smarthome.scene.CommonSceneOnline;
import com.xiaomi.smarthome.scene.api.RemoteSceneApi;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.setting.ServerRouteUtil;
import com.xiaomi.smarthome.shop.utils.AsycnTaskExecutor;
import com.xiaomi.smarthome.smartconfig.DeviceFinder;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import okhttp3.Call;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PluginService extends Service {

    /* renamed from: a  reason: collision with root package name */
    private static final String f17137a = "PluginService";
    private final IPluginRequest.Stub b = new IPluginRequest.Stub() {
        private String mMediaButtonModel;
        private ComponentName mMediaComponent;

        public int getApiLevel() throws RemoteException {
            return 100;
        }

        public boolean isNotificationBarOpBtnEnabled(String str, String str2) throws RemoteException {
            return false;
        }

        public void updateDeviceStatus(final DeviceStatus deviceStatus, final IPluginCallback iPluginCallback) throws RemoteException {
            for (final Device next : SmartHomeDeviceManager.a().d()) {
                if (next.did != null && next.did.equalsIgnoreCase(deviceStatus.d)) {
                    SHApplication.getGlobalHandler().post(new Runnable() {
                        public void run() {
                            MiioManager.a().a(next, deviceStatus.e, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                                /* renamed from: a */
                                public void onSuccess(Void voidR) {
                                    PluginService.this.handleSuccess(iPluginCallback, (String) null);
                                }

                                public void onFailure(Error error) {
                                    PluginService.this.handleFail(iPluginCallback, error.a(), error.b());
                                }
                            });
                        }
                    });
                }
            }
        }

        public void getDeviceStatus(String str, DeviceStatus deviceStatus) throws RemoteException {
            Device n = SmartHomeDeviceManager.a().n(str);
            if (n != null) {
                copyDevicsStatus(deviceStatus, n);
            }
        }

        public void copyDevicsStatus(DeviceStatus deviceStatus, Device device) {
            deviceStatus.d = device.did;
            deviceStatus.e = device.name;
            deviceStatus.f = device.mac;
            deviceStatus.g = device.model;
            deviceStatus.i = device.ip;
            deviceStatus.j = device.parentId != null ? device.parentId : "";
            deviceStatus.k = device.parentModel != null ? device.parentModel : "";
            deviceStatus.m = device.isShared() ? 1 : 0;
            deviceStatus.l = device.isOwner() ? 1 : 0;
            deviceStatus.h = device.extra;
            deviceStatus.n = device.token;
            deviceStatus.o = device.userId;
            deviceStatus.p = device.location != null ? device.location.ordinal() : 0;
            deviceStatus.q = device.latitude;
            deviceStatus.r = device.longitude;
            deviceStatus.s = device.bssid;
            deviceStatus.t = device.lastModified;
            deviceStatus.u = device.pid;
            deviceStatus.v = device.rssi;
            deviceStatus.w = device.isOnline;
            deviceStatus.x = device.resetFlag;
            deviceStatus.y = device.ssid;
            deviceStatus.z = device.ownerName;
            deviceStatus.A = device.ownerId;
            deviceStatus.B = device.propInfo;
            deviceStatus.C = device.version;
            deviceStatus.D.clear();
            deviceStatus.D.putAll(device.property);
            deviceStatus.h = device.extra;
            deviceStatus.E = device.showMode;
            deviceStatus.F = device.event;
            deviceStatus.G = device.permitLevel;
            deviceStatus.H = device.isSetPinCode;
        }

        public List<DeviceStatus> getDeviceList() throws RemoteException {
            ArrayList arrayList = new ArrayList();
            for (Device copyDevicsStatus : SmartHomeDeviceManager.a().f()) {
                DeviceStatus deviceStatus = new DeviceStatus();
                copyDevicsStatus(deviceStatus, copyDevicsStatus);
                arrayList.add(deviceStatus);
            }
            return arrayList;
        }

        public List<DeviceStatus> getDeviceListV2(List<String> list) throws RemoteException {
            ArrayList arrayList = new ArrayList();
            if (list == null || list.size() <= 0) {
                return arrayList;
            }
            for (Device next : SmartHomeDeviceManager.a().f()) {
                if (!TextUtils.isEmpty(next.model)) {
                    Iterator<String> it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        String next2 = it.next();
                        if (!TextUtils.isEmpty(next2) && next.model.equalsIgnoreCase(next2)) {
                            DeviceStatus deviceStatus = new DeviceStatus();
                            copyDevicsStatus(deviceStatus, next);
                            arrayList.add(deviceStatus);
                            break;
                        }
                    }
                }
            }
            return arrayList;
        }

        public List<RoomStatus> getRoomAll() throws RemoteException {
            Home m = HomeManager.a().m();
            if (m == null) {
                return Collections.emptyList();
            }
            List<Room> d = m.d();
            if (d == null) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            for (Room next : d) {
                if (next != null) {
                    RoomStatus roomStatus = new RoomStatus();
                    roomStatus.f13055a = next.b();
                    roomStatus.b = next.c();
                    roomStatus.g = next.h();
                    roomStatus.c = next.d();
                    roomStatus.d = next.e();
                    roomStatus.h = next.a();
                    roomStatus.e = next.f();
                    roomStatus.f = next.g();
                    arrayList.add(roomStatus);
                }
            }
            return arrayList;
        }

        public void deleteRooms(List<String> list, final IPluginCallback2 iPluginCallback2) throws RemoteException {
            HomeManager.a().a(list, (HomeManager.IHomeOperationCallback) new HomeManager.IHomeOperationCallback() {
                public void a() {
                    if (iPluginCallback2 != null) {
                        try {
                            iPluginCallback2.onRequestSuccess(new Intent());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }

                public void a(int i, Error error) {
                    if (iPluginCallback2 != null) {
                        try {
                            iPluginCallback2.onRequestFailed(i, error != null ? error.b() : "");
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

        public void roomRename(String str, String str2, final IPluginCallback2 iPluginCallback2) throws RemoteException {
            if (!HomeManager.A(str2)) {
                if (iPluginCallback2 != null) {
                    try {
                        iPluginCallback2.onRequestFailed(-1, "name too long");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            } else if (!StringUtil.t(str2)) {
                Room i = HomeManager.a().i(str);
                if (i != null) {
                    if (!HomeManager.a().a(i, str2)) {
                        i.e(str2);
                        HomeManager.a().a(i, (List<String>) Collections.EMPTY_LIST, (List<String>) Collections.EMPTY_LIST, (HomeManager.IHomeOperationCallback) new HomeManager.IHomeOperationCallback() {
                            public void a() {
                                if (iPluginCallback2 != null) {
                                    try {
                                        iPluginCallback2.onRequestSuccess(new Intent());
                                    } catch (RemoteException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            public void a(int i, Error error) {
                                if (iPluginCallback2 != null) {
                                    try {
                                        iPluginCallback2.onRequestFailed(i, error != null ? error.b() : "");
                                    } catch (RemoteException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                    } else if (iPluginCallback2 != null) {
                        try {
                            iPluginCallback2.onRequestFailed(-1, "duplicate names");
                        } catch (RemoteException e2) {
                            e2.printStackTrace();
                        }
                    }
                } else if (iPluginCallback2 != null) {
                    try {
                        iPluginCallback2.onRequestFailed(-1, "no such room");
                    } catch (RemoteException e3) {
                        e3.printStackTrace();
                    }
                }
            } else if (iPluginCallback2 != null) {
                try {
                    iPluginCallback2.onRequestFailed(-1, "not support emoji");
                } catch (RemoteException e4) {
                    e4.printStackTrace();
                }
            }
        }

        public void addRoom(RoomStatus roomStatus, final IPluginCallbackRoomStatus iPluginCallbackRoomStatus) throws RemoteException {
            if (roomStatus == null) {
                iPluginCallbackRoomStatus.onRequestFailed(-1, "room is null");
                return;
            }
            final String str = roomStatus.d;
            if (TextUtils.isEmpty(str)) {
                iPluginCallbackRoomStatus.onRequestFailed(-1, "room name is empty");
            } else if (!HomeManager.A(str)) {
                iPluginCallbackRoomStatus.onRequestFailed(-1, "room name too long");
            } else {
                List<Room> e = HomeManager.a().e();
                if (e != null && e.size() >= 50) {
                    iPluginCallbackRoomStatus.onRequestFailed(-1, "exceed room max count");
                } else if (StringUtil.t(str)) {
                    iPluginCallbackRoomStatus.onRequestFailed(-1, "room name contains emoji...");
                } else if (HomeManager.a().a((Room) null, str)) {
                    iPluginCallbackRoomStatus.onRequestFailed(-1, "exist same room name");
                } else {
                    HomeManager.a().a(str, (List<String>) new ArrayList(), "", (HomeManager.IHomeOperationCallback) new HomeManager.IHomeOperationCallback() {
                        public void a() {
                            try {
                                List<RoomStatus> roomAll = AnonymousClass1.this.getRoomAll();
                                RoomStatus roomStatus = null;
                                int size = roomAll.size();
                                int i = 0;
                                while (true) {
                                    if (i >= size) {
                                        break;
                                    } else if (str.equals(roomAll.get(i).d)) {
                                        roomStatus = roomAll.get(i);
                                        break;
                                    } else {
                                        i++;
                                    }
                                }
                                iPluginCallbackRoomStatus.onRequestSuccess(roomStatus);
                            } catch (RemoteException e) {
                                LogUtil.b(PluginService.f17137a, "addRoom:  " + e.toString());
                            }
                        }

                        public void a(int i, Error error) {
                            try {
                                iPluginCallbackRoomStatus.onRequestFailed(i, error.toString());
                            } catch (RemoteException e) {
                                LogUtil.b(PluginService.f17137a, "addRoom:  " + e.toString());
                            }
                        }
                    });
                }
            }
        }

        public List<DeviceStatus> getSubDeviceByParentDid(String str) throws RemoteException {
            ArrayList arrayList = new ArrayList();
            for (Device copyDevicsStatus : SmartHomeDeviceManager.a().j(str)) {
                DeviceStatus deviceStatus = new DeviceStatus();
                copyDevicsStatus(deviceStatus, copyDevicsStatus);
                arrayList.add(deviceStatus);
            }
            return arrayList;
        }

        public List<SceneInfo> getSceneByDid(String str) throws RemoteException {
            return SceneManager.x().h(str);
        }

        public void refreshScene(String str, ISceneCallback iSceneCallback) throws RemoteException {
            if (iSceneCallback != null) {
                iSceneCallback.onSceneLoadFinished(new ArrayList());
            }
        }

        public void subscribeDevice(final String str, int i, List<String> list, int i2, final IPluginCallback iPluginCallback) throws RemoteException {
            final Device n = SmartHomeDeviceManager.a().n(str);
            if (n == null) {
                PluginService.this.handleFail(iPluginCallback, -1, "device is null");
            } else if (i2 <= 0 || i2 > 3) {
                PluginService.this.handleFail(iPluginCallback, -1, "expire time mast not more than 3");
            } else {
                final AnonymousClass5 r0 = new DevicePushListener.DevicePushCallback() {
                    public void a(JSONArray jSONArray) {
                        PluginRecord d = CoreApi.a().d(n.model);
                        if (d != null) {
                            MpkPluginApi.onReceiveDevicePush(d, str, jSONArray);
                        }
                    }
                };
                DevicePushListener.a().a(n, (DevicePushListener.DevicePushCallback) r0);
                PluginService.this.mMainHandler.postDelayed(new Runnable() {
                    public void run() {
                        DevicePushListener.a().b(n, r0);
                    }
                }, ((long) i2) * 60000);
                n.subscribeDevice(list, i2, new AsyncCallback<Boolean, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Boolean bool) {
                        PluginService.this.handleSuccess(iPluginCallback, (String) null);
                    }

                    public void onFailure(Error error) {
                        PluginService.this.handleFail(iPluginCallback, error.a(), error.b());
                    }
                });
            }
        }

        public void unsubscribeDevice(String str, int i, List<String> list, final IPluginCallback iPluginCallback) throws RemoteException {
            final Device n = SmartHomeDeviceManager.a().n(str);
            if (n == null) {
                PluginService.this.handleFail(iPluginCallback, -1, "device is null");
            } else {
                n.unsubscribeDevice(list, new AsyncCallback<Boolean, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Boolean bool) {
                        PluginService.this.handleSuccess(iPluginCallback, (String) null);
                        DevicePushListener.a().a(n);
                    }

                    public void onFailure(Error error) {
                        PluginService.this.handleFail(iPluginCallback, error.a(), error.b());
                    }
                });
            }
        }

        public void subscribeDeviceV2(String str, int i, List<String> list, int i2, final IPluginCallback2 iPluginCallback2) throws RemoteException {
            DeviceSubscribeManager.a().a(str, i, list, i2, (SubscribeCallback) new SubscribeCallback() {
                public void a(String str) {
                    if (iPluginCallback2 != null) {
                        Intent intent = new Intent();
                        intent.putExtra("subscribeDeviceV2_flag", "onSuccess");
                        intent.putExtra("subscribeDeviceV2_subid", str);
                        try {
                            iPluginCallback2.onRequestSuccess(intent);
                        } catch (RemoteException unused) {
                        }
                    }
                }

                public void a(Error error) {
                    if (iPluginCallback2 != null) {
                        try {
                            iPluginCallback2.onRequestFailed(error.a(), error.b());
                        } catch (RemoteException unused) {
                        }
                    }
                }

                public void a(String str, String str2, JSONArray jSONArray) {
                    if (iPluginCallback2 != null) {
                        Intent intent = new Intent();
                        intent.putExtra("subscribeDeviceV2_flag", "onReceive");
                        intent.putExtra("subscribeDeviceV2_did", str);
                        intent.putExtra("subscribeDeviceV2_model", str2);
                        intent.putExtra("subscribeDeviceV2_entry", jSONArray.toString());
                        try {
                            iPluginCallback2.onRequestSuccess(intent);
                        } catch (RemoteException unused) {
                        }
                    }
                }
            });
        }

        public void unsubscribeDeviceV2(String str, int i, List<String> list, String str2, final IPluginCallback2 iPluginCallback2) throws RemoteException {
            DeviceSubscribeManager.a().a(str, i, list, str2, (UnSubscribeCallback) new UnSubscribeCallback() {
                public void a() {
                    if (iPluginCallback2 != null) {
                        try {
                            iPluginCallback2.onRequestSuccess((Intent) null);
                        } catch (RemoteException unused) {
                        }
                    }
                }

                public void a(Error error) {
                    if (iPluginCallback2 != null) {
                        try {
                            iPluginCallback2.onRequestFailed(error.a(), error.b());
                        } catch (RemoteException unused) {
                        }
                    }
                }
            });
        }

        public String getAccountId() throws RemoteException {
            return CoreApi.a().s();
        }

        public Location getLastLocation() throws RemoteException {
            return SHLocationManager.a().b();
        }

        public boolean isGPSLocationEnable() throws RemoteException {
            return SHLocationManager.a().d();
        }

        public boolean isNetworkLocationEnabled() throws RemoteException {
            return SHLocationManager.a().c();
        }

        public void requestLocation(final ILocationCallback iLocationCallback) throws RemoteException {
            SHLocationManager.a().a((SHLocationManager.LocationCallback) new SHLocationManager.LocationCallback() {
                public void onSucceed(String str, Location location) {
                    super.onSucceed(str, location);
                    if (location != null) {
                        location.setProvider(str);
                    }
                    if (iLocationCallback != null) {
                        try {
                            iLocationCallback.onSuccess();
                        } catch (RemoteException unused) {
                        }
                    }
                }

                public void onFailure(String str) {
                    super.onFailure(str);
                    if (iLocationCallback != null) {
                        try {
                            iLocationCallback.onFailure();
                        } catch (RemoteException unused) {
                        }
                    }
                }
            });
        }

        public void recordCountEvent(String str, String str2) throws RemoteException {
            MobclickAgent.a((Context) PluginService.this, str, str2);
        }

        public void recordCalculateEvent(String str, String str2, long j) throws RemoteException {
            HashMap hashMap = new HashMap();
            hashMap.put(str2, String.valueOf(j));
            MobclickAgent.a((Context) PluginService.this, str, (Map<String, String>) hashMap);
        }

        public void recordStringPropertyEvent(String str, String str2, String str3) throws RemoteException {
            HashMap hashMap = new HashMap();
            hashMap.put(str2, str3);
            MobclickAgent.a((Context) PluginService.this, str, (Map<String, String>) hashMap);
        }

        public void recordNumericPropertyEvent(String str, String str2, long j) throws RemoteException {
            HashMap hashMap = new HashMap();
            hashMap.put(str2, String.valueOf(j));
            MobclickAgent.a((Context) PluginService.this, str, (Map<String, String>) hashMap);
        }

        public void addRecord(String str, String str2) throws RemoteException {
            CoreApi.a().a(StatType.EVENT, (String) null, str, str2, (String) null, false);
        }

        public void modDeviceName(String str, final String str2, final IPluginCallback iPluginCallback) throws RemoteException {
            final Device n = SmartHomeDeviceManager.a().n(str);
            if (n != null) {
                MiioManager.a().a(n, str2, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        n.name = str2;
                        PluginService.this.handleSuccess(iPluginCallback, (String) null);
                    }

                    public void onFailure(Error error) {
                        PluginService.this.handleFail(iPluginCallback, -9, "");
                    }
                });
            } else if (iPluginCallback != null) {
                iPluginCallback.onRequestFailed(-9, "device is null");
            }
        }

        public void unBindDevice(String str, int i, final IPluginCallback iPluginCallback) throws RemoteException {
            MiioManager.a().a(str, i, (AsyncResponseCallback<Void>) new AsyncResponseCallback<Void>() {
                public void a(Void voidR) {
                    PluginService.this.handleSuccess(iPluginCallback, (String) null);
                }

                public void a(int i) {
                    PluginService.this.handleFail(iPluginCallback, i, "");
                }

                public void a(int i, Object obj) {
                    PluginService.this.handleFail(iPluginCallback, i, obj != null ? obj.toString() : "");
                }
            });
        }

        public void updateDeviceList(IPluginCallback iPluginCallback) throws RemoteException {
            if (iPluginCallback != null) {
                final WeakReference weakReference = new WeakReference(iPluginCallback);
                SmartHomeDeviceManager.a().a((SmartHomeDeviceManager.IClientDeviceListener) new SmartHomeDeviceManager.IClientDeviceListener() {
                    public void a(int i, Device device) {
                    }

                    public void a(int i) {
                        if (i == 3 && weakReference.get() != null) {
                            PluginService.this.handleSuccess((IPluginCallback) weakReference.get(), (String) null);
                        }
                    }

                    public void b(int i) {
                        if (i == 3 && weakReference.get() != null) {
                            PluginService.this.handleFail((IPluginCallback) weakReference.get(), -1, "");
                        }
                    }
                });
            }
            SmartHomeDeviceManager.a().p();
        }

        public void updateDeviceProperties(String str, String str2) throws RemoteException {
            Device n = SmartHomeDeviceManager.a().n(str);
            if (n != null) {
                try {
                    JSONObject jSONObject = new JSONObject(str2);
                    if (n.propInfo == null) {
                        n.propInfo = jSONObject;
                        return;
                    }
                    try {
                        Iterator<String> keys = jSONObject.keys();
                        while (keys.hasNext()) {
                            String next = keys.next();
                            if (n.propInfo.has(next)) {
                                n.propInfo.put(next, jSONObject.get(next));
                            } else {
                                n.propInfo.put(next, jSONObject.get(next));
                            }
                        }
                    } catch (Exception unused) {
                    }
                } catch (JSONException unused2) {
                }
            }
        }

        public void log(String str, String str2) throws RemoteException {
            MyLog.a(str + " " + str2);
        }

        public void logByModel(String str, String str2) throws RemoteException {
            MyLog.b(str, str2);
        }

        public void addToLauncher(String str, Intent intent) throws RemoteException {
            Device n = SmartHomeDeviceManager.a().n(str);
            if (n != null) {
                DeviceShortcutUtils.a(false, n, intent, "plugin", (AsyncResponseCallback<Void>) new AsyncResponseCallback<Void>() {
                    public void a(int i, Object obj) {
                    }

                    public void a(Void voidR) {
                    }

                    public void a(int i) {
                        try {
                            AnonymousClass1.this.requestPermission(new String[]{"com.android.launcher.permission.INSTALL_SHORTCUT"}, (IPluginCallback) null);
                        } catch (RemoteException e) {
                            Log.e(PluginService.f17137a, "INSTALL_SHORTCUT", e);
                        }
                    }
                });
            }
        }

        public String getRouterFileDownloadUrl(String str) throws RemoteException {
            return RouterLocalApi.a().b(str);
        }

        public void checkLocalRouterInfo(final String str, final IPluginCallback iPluginCallback) throws RemoteException {
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    RouterLocalApi.a().a(str, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                        /* renamed from: a */
                        public void onSuccess(Void voidR) {
                            PluginService.this.handleSuccess(iPluginCallback, (String) null);
                        }

                        public void onFailure(Error error) {
                            PluginService.this.handleFail(iPluginCallback, error.a(), error.b());
                        }
                    });
                }
            });
        }

        public boolean isLocalMiRouter() throws RemoteException {
            return RouterLocalApi.a().b();
        }

        public void gotoPage(Uri uri) throws RemoteException {
            UrlResolver.a(SHApplication.getAppContext(), uri, true);
        }

        public void sendMessage(String str, int i, Intent intent, final IPluginCallback2 iPluginCallback2) throws RemoteException {
            Device n = SmartHomeDeviceManager.a().n(str);
            if (n == null) {
                if (iPluginCallback2 != null) {
                    iPluginCallback2.onRequestFailed(-1, "not found device");
                }
            } else if (CoreApi.a().c(n.model)) {
                PluginRecord d = CoreApi.a().d(n.model);
                Intent intent2 = new Intent();
                if (intent != null) {
                    intent2.putExtras(intent);
                }
                if (iPluginCallback2 == null) {
                    PluginApi.getInstance().sendMessage(SHApplication.getAppContext(), d, i, intent2, n.newDeviceStat(), (RunningProcess) null, false, (PluginApi.SendMessageCallback) null);
                    return;
                }
                PluginApi.getInstance().sendMessage(SHApplication.getAppContext(), d, i, intent2, n.newDeviceStat(), (RunningProcess) null, true, new PluginApi.SendMessageCallback() {
                    public void onMessageFailure(int i, String str) {
                        super.onMessageFailure(i, str);
                        if (iPluginCallback2 != null) {
                            try {
                                iPluginCallback2.onRequestFailed(i, str);
                            } catch (RemoteException unused) {
                            }
                        }
                    }

                    public void onMessageSuccess(Intent intent) {
                        super.onMessageSuccess(intent);
                        if (iPluginCallback2 != null) {
                            try {
                                iPluginCallback2.onRequestSuccess(intent);
                            } catch (RemoteException unused) {
                            }
                        }
                    }
                });
            } else if (iPluginCallback2 != null) {
                iPluginCallback2.onRequestFailed(-1, "plugin found device");
            }
        }

        public void getUserInfo(String str, final IPluginCallbackUserInfo iPluginCallbackUserInfo) throws RemoteException {
            AnonymousClass18 r0 = new AsyncResponseCallback<ShareUserRecord>() {
                public void a(ShareUserRecord shareUserRecord) {
                    if (iPluginCallbackUserInfo != null) {
                        UserInfo userInfo = new UserInfo();
                        userInfo.userId = shareUserRecord.userId;
                        userInfo.nickName = shareUserRecord.nickName;
                        userInfo.url = shareUserRecord.url;
                        userInfo.localPath = shareUserRecord.localPath;
                        userInfo.shareTime = shareUserRecord.shareTime;
                        userInfo.phone = shareUserRecord.phone;
                        userInfo.email = shareUserRecord.email;
                        userInfo.sex = shareUserRecord.sex;
                        userInfo.birth = shareUserRecord.birth;
                        try {
                            iPluginCallbackUserInfo.onRequestSuccess(userInfo);
                        } catch (RemoteException unused) {
                        }
                    }
                }

                public void a(int i, Object obj) {
                    if (iPluginCallbackUserInfo != null) {
                        try {
                            iPluginCallbackUserInfo.onRequestFailed(i, "");
                        } catch (RemoteException unused) {
                        }
                    }
                }

                public void a(int i) {
                    if (iPluginCallbackUserInfo != null) {
                        try {
                            iPluginCallbackUserInfo.onRequestFailed(i, "");
                        } catch (RemoteException unused) {
                        }
                    }
                }
            };
            if (getAccountId().equals(str)) {
                UserMamanger.a().a((AsyncResponseCallback<ShareUserRecord>) r0);
            } else {
                getUserInfoFromRemoteFamilyApi(str, r0);
            }
        }

        private void getUserInfoFromRemoteFamilyApi(String str, final AsyncResponseCallback<ShareUserRecord> asyncResponseCallback) {
            RemoteFamilyApi.a().a(SHApplication.getAppContext(), str, (AsyncCallback<com.xiaomi.smarthome.framework.api.model.UserInfo, Error>) new AsyncCallback<com.xiaomi.smarthome.framework.api.model.UserInfo, Error>() {
                /* renamed from: a */
                public void onSuccess(com.xiaomi.smarthome.framework.api.model.UserInfo userInfo) {
                    final ShareUserRecord shareUserRecord = new ShareUserRecord();
                    shareUserRecord.userId = userInfo.f16462a;
                    shareUserRecord.url = userInfo.c;
                    shareUserRecord.nickName = userInfo.e;
                    if (asyncResponseCallback != null) {
                        SHApplication.getGlobalHandler().post(new Runnable() {
                            public void run() {
                                asyncResponseCallback.a(shareUserRecord);
                            }
                        });
                    }
                }

                public void onFailure(Error error) {
                    if (asyncResponseCallback != null) {
                        SHApplication.getGlobalHandler().post(new Runnable() {
                            public void run() {
                                asyncResponseCallback.a(ErrorCode.ERROR_INVALID_REQUEST.getCode());
                            }
                        });
                    }
                }
            });
        }

        public void updateSubDevice(String[] strArr, final IPluginCallbackDeviceList iPluginCallbackDeviceList) throws RemoteException {
            DeviceApi.getInstance().getSubDevice(SHApplication.getAppContext(), strArr, new AsyncCallback<List<Device>, Error>() {
                /* renamed from: a */
                public void onSuccess(List<Device> list) {
                    SmartHomeDeviceManager.a().a(list);
                    if (iPluginCallbackDeviceList != null) {
                        ArrayList arrayList = new ArrayList();
                        for (Device copyDevicsStatus : list) {
                            DeviceStatus deviceStatus = new DeviceStatus();
                            AnonymousClass1.this.copyDevicsStatus(deviceStatus, copyDevicsStatus);
                            arrayList.add(deviceStatus);
                        }
                        try {
                            iPluginCallbackDeviceList.onRequestSuccess(arrayList);
                        } catch (RemoteException unused) {
                        }
                    }
                }

                public void onFailure(Error error) {
                    if (iPluginCallbackDeviceList != null) {
                        try {
                            iPluginCallbackDeviceList.onRequestFailed(error.a(), error.b());
                        } catch (RemoteException unused) {
                        }
                    }
                }
            });
        }

        public void callRemoteAsync(String[] strArr, int i, String str, final IPluginCallback iPluginCallback, final IPluginCallback iPluginCallback2) throws RemoteException {
            try {
                String[] strArr2 = strArr;
                int i2 = i;
                RemoteAsyncApiHelper.a().a(SHApplication.getAppContext(), strArr2, i2, (Object) new JSONObject(str), (Callback<JSONObject>) new Callback<JSONObject>() {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        PluginService.this.handleSuccess(iPluginCallback, jSONObject == null ? null : jSONObject.toString());
                    }

                    public void onFailure(int i, String str) {
                        PluginService.this.handleFail(iPluginCallback, i, str);
                    }
                }, (Callback<JSONObject>) new Callback<JSONObject>() {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        PluginService.this.handleSuccess(iPluginCallback2, jSONObject == null ? null : jSONObject.toString());
                    }

                    public void onFailure(int i, String str) {
                        PluginService.this.handleFail(iPluginCallback2, i, str);
                    }
                });
            } catch (Exception e) {
                PluginService.this.handleFail(iPluginCallback, -1, e.getMessage());
            }
        }

        public void startSearchNewDevice(String str, final IPluginCallback2 iPluginCallback2) throws RemoteException {
            final List<Device> j = SmartHomeDeviceManager.a().j(str);
            DeviceApi.getInstance().getSubDevice(SHApplication.getAppContext(), new String[]{str}, new AsyncCallback<List<Device>, Error>() {
                /* renamed from: a */
                public void onSuccess(List<Device> list) {
                    ArrayList<Device> arrayList = new ArrayList<>();
                    for (Device next : list) {
                        boolean z = false;
                        for (Device device : j) {
                            if (device.did.equalsIgnoreCase(next.did)) {
                                z = true;
                            }
                        }
                        if (!z) {
                            arrayList.add(next);
                        }
                    }
                    ArrayList arrayList2 = new ArrayList();
                    for (Device device2 : arrayList) {
                        SmartHomeDeviceManager.a().b(device2);
                        arrayList2.add(device2.newDeviceStat());
                    }
                    if (iPluginCallback2 != null) {
                        Intent intent = new Intent();
                        intent.putParcelableArrayListExtra("data", arrayList2);
                        try {
                            iPluginCallback2.onRequestSuccess(intent);
                        } catch (RemoteException unused) {
                        }
                    }
                }

                public void onFailure(Error error) {
                    if (iPluginCallback2 != null) {
                        try {
                            iPluginCallback2.onRequestFailed(-1, "");
                        } catch (RemoteException unused) {
                        }
                    }
                }
            });
        }

        public void notifyBluetoothBinded(String str, String str2) throws RemoteException {
            BluetoothHelper.e(str, str2);
        }

        public void loadWebView(String str, String str2) throws RemoteException {
            String host = Uri.parse(str).getHost();
            if (!TextUtils.isEmpty(host)) {
                if (!TextUtils.isEmpty(str2) || host.contains("recharge.pay.xiaomi.com") || (!host.contains("mi.com") && !host.contains("xiaomi.com") && !host.contains("xiaomiyoupin.com"))) {
                    Intent intent = new Intent(SHApplication.getAppContext(), CommonWebViewActivity.class);
                    intent.putExtra("url", str);
                    intent.putExtra("title", str2);
                    intent.setFlags(C.ENCODING_PCM_MU_LAW);
                    PluginService.this.startActivity(intent);
                    return;
                }
                UrlDispatchManger.a().c(str);
            }
        }

        public void loadUrl(String str, String str2) throws RemoteException {
            String host = Uri.parse(str).getHost();
            if (TextUtils.isEmpty(host)) {
                LogUtil.e(PluginService.f17137a, "loadUrl error： host is empty");
                return;
            }
            boolean z = false;
            if (TextUtils.isEmpty(str2) && !host.contains("recharge.pay.xiaomi.com") && (host.contains("mi.com") || host.contains("xiaomi.com") || host.contains("xiaomiyoupin.com"))) {
                z = UrlDispatchManger.a().a((Activity) null, str, -1);
            }
            if (!z) {
                Intent intent = new Intent(SHApplication.getAppContext(), CommonWebViewActivity.class);
                intent.putExtra("url", str);
                intent.putExtra("title", str2);
                intent.setFlags(C.ENCODING_PCM_MU_LAW);
                PluginService.this.startActivity(intent);
            }
        }

        public void loadRecommendScenes(String str, final IRecommendSceneItemCallback iRecommendSceneItemCallback) throws RemoteException {
            SceneManager.x().a(str, (IXmPluginHostActivity.AsyncCallback<List<RecommendSceneItem>>) new IXmPluginHostActivity.AsyncCallback<List<RecommendSceneItem>>() {
                /* renamed from: a */
                public void onSuccess(List<RecommendSceneItem> list) {
                    if (iRecommendSceneItemCallback != null) {
                        try {
                            iRecommendSceneItemCallback.onRequestSuccess(list);
                        } catch (RemoteException unused) {
                        }
                    }
                }

                public void onFailure(int i, Object obj) {
                    if (iRecommendSceneItemCallback != null) {
                        try {
                            iRecommendSceneItemCallback.onRequestFailed(i, obj != null ? obj.toString() : "");
                        } catch (RemoteException unused) {
                        }
                    }
                }
            });
        }

        public void updateSceneItem(final SceneInfo sceneInfo, final ISceneInfoCallback iSceneInfoCallback) throws RemoteException {
            SceneApi.SmartHomeScene e = SceneManager.x().e(sceneInfo.mSceneIdV2);
            e.g = sceneInfo.mName;
            e.n = sceneInfo.mEnable;
            RemoteSceneApi.a().c(SHApplication.getAppContext(), e, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                /* renamed from: a */
                public void onSuccess(Void voidR) {
                    if (iSceneInfoCallback != null) {
                        try {
                            iSceneInfoCallback.onSuccess(sceneInfo);
                        } catch (RemoteException unused) {
                        }
                    }
                }

                public void onFailure(Error error) {
                    try {
                        if (iSceneInfoCallback != null) {
                            iSceneInfoCallback.onFailure(-1, (String) null);
                        }
                    } catch (RemoteException unused) {
                    }
                }
            });
        }

        public void updateScene(String str, final IPluginCallback iPluginCallback) throws RemoteException {
            SceneManager.x().a(str, (SceneManager.IScenceListener) new SceneManager.IScenceListener() {
                public void onRefreshScenceSuccess(int i) {
                    Log.e("1", "2 " + i);
                    if (iPluginCallback != null && i == 5) {
                        try {
                            iPluginCallback.onRequestSuccess((String) null);
                        } catch (RemoteException unused) {
                        }
                    }
                }

                public void onRefreshScenceFailed(int i) {
                    if (iPluginCallback != null && i == 5) {
                        try {
                            iPluginCallback.onRequestFailed(-1, "");
                        } catch (RemoteException unused) {
                        }
                    }
                }
            });
        }

        public boolean hasSceneOnline(String str, String str2) throws RemoteException {
            CommonSceneOnline a2 = SceneManager.x().a(str, str2);
            return (a2 == null || a2.e == null || a2.e.size() <= 0) ? false : true;
        }

        public void setSubDeviceShownMode(String str, boolean z, final IPluginCallback iPluginCallback) throws RemoteException {
            SmartHomeDeviceManager.a().a(z, str, SHApplication.getAppContext(), (AsyncResponseCallback<Void>) new AsyncResponseCallback<Void>() {
                public void a(Void voidR) {
                    if (iPluginCallback != null) {
                        try {
                            iPluginCallback.onRequestSuccess((String) null);
                        } catch (RemoteException unused) {
                        }
                    }
                }

                public void a(int i, Object obj) {
                    if (iPluginCallback != null) {
                        try {
                            iPluginCallback.onRequestFailed(i, obj == null ? "" : obj.toString());
                        } catch (RemoteException unused) {
                        }
                    }
                }

                public void a(int i) {
                    if (iPluginCallback != null) {
                        try {
                            iPluginCallback.onRequestFailed(i, "");
                        } catch (RemoteException unused) {
                        }
                    }
                }
            });
        }

        public DeviceStatus getDevice(String str) throws RemoteException {
            Device b;
            Device n = SmartHomeDeviceManager.a().n(str);
            JSONObject jSONObject = null;
            if (n == null) {
                return null;
            }
            if (TextUtils.isEmpty(n.parentModel) && !TextUtils.isEmpty(n.parentId) && (b = SmartHomeDeviceManager.a().b(n.parentId)) != null) {
                n.parentModel = b.model;
            }
            if ((n.propInfo == null || n.propInfo.length() == 0) && TextUtils.isEmpty(n.event)) {
                Map<String, Object> b2 = MiotSpecCardManager.f().b(n.did);
                Map<String, Object> b3 = ControlCardInfoManager.f().b(n.did);
                if (b2 != null) {
                    try {
                        if (n.propInfo == null) {
                            n.propInfo = new JSONObject();
                        }
                        jSONObject = new JSONObject();
                        for (Map.Entry next : b2.entrySet()) {
                            String str2 = (String) next.getKey();
                            if (str2 != null) {
                                if (str2.startsWith(Device.EVENT_PREFIX)) {
                                    jSONObject.put(str2, next.getValue());
                                } else if (str2.startsWith("prop.")) {
                                    jSONObject.put(str2, next.getValue());
                                    n.propInfo.put(str2.substring("prop.".length()), next.getValue());
                                } else {
                                    n.propInfo.put(str2, next.getValue());
                                }
                            }
                        }
                    } catch (JSONException e) {
                        Log.e(PluginService.f17137a, Log.getStackTraceString(e));
                    }
                }
                if (b3 != null) {
                    if (jSONObject == null) {
                        jSONObject = new JSONObject();
                    }
                    if (n.propInfo == null) {
                        n.propInfo = new JSONObject();
                    }
                    for (Map.Entry next2 : b3.entrySet()) {
                        String str3 = (String) next2.getKey();
                        if (str3 != null) {
                            if (str3.startsWith(Device.EVENT_PREFIX)) {
                                jSONObject.put(str3, next2.getValue());
                            } else if (str3.startsWith("prop.")) {
                                jSONObject.put(str3, next2.getValue());
                                n.propInfo.put(str3.substring("prop.".length()), next2.getValue());
                            } else {
                                n.propInfo.put(str3, next2.getValue());
                            }
                        }
                    }
                }
                if (jSONObject != null) {
                    n.event = jSONObject.toString();
                }
            }
            DeviceStatus deviceStatus = new DeviceStatus();
            copyDevicsStatus(deviceStatus, n);
            return deviceStatus;
        }

        public DeviceTagInfo getDeviceTagInfo(String str) throws RemoteException {
            DeviceTagInterface<Device> b = SmartHomeDeviceHelper.a().b();
            JSONObject jSONObject = new JSONObject();
            for (Integer intValue : b.p()) {
                int intValue2 = intValue.intValue();
                List<String> c = b.c(intValue2, str);
                if (c != null && c.size() > 0) {
                    JSONArray jSONArray = new JSONArray();
                    for (String put : c) {
                        jSONArray.put(put);
                    }
                    try {
                        jSONObject.put(String.valueOf(intValue2), jSONArray);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (jSONObject.length() <= 0) {
                return null;
            }
            DeviceTagInfo deviceTagInfo = new DeviceTagInfo();
            deviceTagInfo.d = jSONObject.toString();
            return deviceTagInfo;
        }

        public String getLightDeviceGroupModel(String str) throws RemoteException {
            if (!TextUtils.isEmpty(str)) {
                return LightGroupManager.a().b(str);
            }
            LogUtil.e(PluginService.f17137a, "getLightDeviceGroupModel error: model is empty");
            return "";
        }

        public void addTag(String str, String str2) throws RemoteException {
            if (!TextUtils.isEmpty(str)) {
                HashSet hashSet = new HashSet();
                hashSet.add(str);
                ((DeviceTagManager) SmartHomeDeviceHelper.a().b()).a((Set<String>) hashSet, str2, false, (HomeManager.IHomeOperationCallback) null);
            }
        }

        public void removeTag(String str) throws RemoteException {
            SmartHomeDeviceHelper.a().b().a(str);
        }

        public List<String> getRecommendTags(String str) throws RemoteException {
            SmartHomeDeviceManager a2 = SmartHomeDeviceManager.a();
            Device b = a2.b(str);
            if (b == null) {
                b = a2.l(str);
            }
            return SmartHomeDeviceHelper.a().b().a(b);
        }

        public int getMainProcessId() throws RemoteException {
            return Process.myPid();
        }

        public void renameBluetoothDevice(String str, String str2) throws RemoteException {
            BluetoothHelper.c(str, str2);
        }

        public void setBleDeviceSubtitle(String str, String str2) throws RemoteException {
            BluetoothHelper.d(str, str2);
        }

        public void registerMediaButtonReceiver(String str) throws RemoteException {
            BluetoothLog.c("registerMediaButtonReceiver model = " + str);
            if (this.mMediaComponent == null) {
                this.mMediaButtonModel = str;
                Context appContext = CommonApplication.getAppContext();
                this.mMediaComponent = new ComponentName(appContext.getPackageName(), MediaButtonReceiver.class.getName());
                ((AudioManager) appContext.getSystemService("audio")).registerMediaButtonEventReceiver(this.mMediaComponent);
                return;
            }
            BluetoothLog.c(">>> MediaComponent not null");
        }

        public void unRegisterMediaButtonReceiver(String str) throws RemoteException {
            BluetoothLog.c("unRegisterMediaButtonReceiver model = " + str);
            if (!TextUtils.isEmpty(str) && str.equals(this.mMediaButtonModel) && this.mMediaComponent != null) {
                this.mMediaButtonModel = null;
                ((AudioManager) CommonApplication.getAppContext().getSystemService("audio")).unregisterMediaButtonEventReceiver(this.mMediaComponent);
                this.mMediaComponent = null;
            }
        }

        public String getMediaButtonModel() throws RemoteException {
            return this.mMediaButtonModel;
        }

        public void visualSecureBind(String str) throws RemoteException {
            Device b = SmartHomeDeviceManager.a().b(str);
            if (b == null) {
                b = BLEDeviceManager.c(str);
            }
            if (b != null) {
                BleDispatcher.a(CommonApplication.getAppContext(), b);
            }
        }

        public void secureConnect(String str, final IBleCallback iBleCallback) throws RemoteException {
            Device f = SmartHomeDeviceManager.a().f(str);
            if (f == null || !f.isBinded()) {
                BluetoothMyLogger.d(String.format("secureConnect: %s has unbind", new Object[]{BluetoothMyLogger.a(str)}));
                if (iBleCallback != null) {
                    try {
                        iBleCallback.onResponse(-1, (Bundle) null);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                BluetoothHelper.a(str, (Response.BleConnectResponse) new Response.BleConnectResponse() {
                    /* renamed from: a */
                    public void onResponse(int i, Bundle bundle) {
                        if (iBleCallback != null) {
                            try {
                                iBleCallback.onResponse(i, bundle);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        }

        public void securityChipConnect(String str, final IBleCallback iBleCallback) throws RemoteException {
            Device f = SmartHomeDeviceManager.a().f(str);
            if (f == null || !f.isBinded()) {
                BluetoothMyLogger.d(String.format("securityChipConnect: %s has unbind", new Object[]{BluetoothMyLogger.a(str)}));
                if (iBleCallback != null) {
                    try {
                        iBleCallback.onResponse(-1, (Bundle) null);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                BluetoothHelper.c(str, (Response.BleConnectResponse) new Response.BleConnectResponse() {
                    /* renamed from: a */
                    public void onResponse(int i, Bundle bundle) {
                        if (iBleCallback != null) {
                            try {
                                iBleCallback.onResponse(i, bundle);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        }

        public void securityChipSharedDeviceConnect(String str, final IBleCallback iBleCallback) throws RemoteException {
            Device f = SmartHomeDeviceManager.a().f(str);
            if (f == null || !f.isBinded()) {
                BluetoothMyLogger.d(String.format("securityChipSharedDeviceConnect: %s has unbind", new Object[]{BluetoothMyLogger.a(str)}));
                if (iBleCallback != null) {
                    try {
                        iBleCallback.onResponse(-1, (Bundle) null);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                BluetoothHelper.b(str, (Response.BleConnectResponse) new Response.BleConnectResponse() {
                    /* renamed from: a */
                    public void onResponse(int i, Bundle bundle) {
                        if (iBleCallback != null) {
                            try {
                                iBleCallback.onResponse(i, bundle);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        }

        public void reverseGeo(double d, double d2, final IPluginCallback2 iPluginCallback2) throws RemoteException {
            SHLocationManager.a().a(d, d2, (SHLocationManager.LocationCallback) new SHLocationManager.LocationCallback() {
                public void onSucceed(String str, Location location) {
                    Bundle extras = location.getExtras();
                    if (extras == null) {
                        try {
                            iPluginCallback2.onRequestFailed(-1, "location not contains bundle extras");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Address address = (Address) extras.getParcelable("address");
                        if (address == null) {
                            try {
                                iPluginCallback2.onRequestFailed(-1, "location bundle not contains address");
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        } else {
                            Intent intent = new Intent();
                            intent.putExtra("address", address);
                            try {
                                iPluginCallback2.onRequestSuccess(intent);
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            }
                        }
                    }
                }

                public void onFailure(String str) {
                    try {
                        iPluginCallback2.onRequestFailed(-1, "reverseGeo failed");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        public void requestPermission(String[] strArr, final IPluginCallback iPluginCallback) throws RemoteException {
            AndPermission.b(SHApplication.getAppContext()).a(strArr).a((Rationale) null).a((Action) new Action() {
                public void onAction(List<String> list) {
                    if (iPluginCallback != null) {
                        try {
                            iPluginCallback.onRequestSuccess("onGranted");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).b(new Action() {
                public void onAction(List<String> list) {
                    if (iPluginCallback != null) {
                        try {
                            iPluginCallback.onRequestSuccess("onDenied");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).a();
        }

        public void addDeviceToMain(final String str, String str2, final IPluginCallback iPluginCallback) throws RemoteException {
            BluetoothLog.c(String.format("getDeviceInfo did = %s", new Object[]{str}));
            DeviceApi.getInstance().getDeviceDetail(SHApplication.getAppContext(), new String[]{str}, new AsyncCallback<List<Device>, Error>() {
                /* renamed from: a */
                public void onSuccess(List<Device> list) {
                    BluetoothLog.c(String.format("onSuccess size = %d", new Object[]{Integer.valueOf(list.size())}));
                    if (list.size() > 0) {
                        Device b2 = SmartHomeDeviceManager.a().b(str);
                        if (b2 != null) {
                            SmartHomeDeviceManager.a().c(b2);
                        }
                        final Device device = list.get(0);
                        SmartHomeDeviceManager.a().a((SmartHomeDeviceManager.IClientDeviceListener) new AddDeviceListener(device, iPluginCallback));
                        SmartHomeDeviceManager.a().b(device);
                        DeviceFinder.a().c(str);
                        Home m = HomeManager.a().m();
                        if (device != null && m != null) {
                            HomeManager.a().a(m, (Room) null, device, (HomeManager.IHomeOperationCallback) new HomeManager.IHomeOperationCallback() {
                                public void a(int i, Error error) {
                                }

                                public void a() {
                                    HomeManager.a().b(device);
                                }
                            });
                            DeviceApi.getInstance().reportNewBind(SHApplication.getAppContext(), device.did, PageUrl.e, new AsyncCallback<JSONObject, Error>() {
                                /* renamed from: a */
                                public void onSuccess(JSONObject jSONObject) {
                                    if (jSONObject != null && jSONObject.optInt("code", -1) == 0) {
                                        Log.d("wangyh", "onSuccess: " + jSONObject.toString());
                                    }
                                }

                                public void onFailure(Error error) {
                                    Log.d("wangyh", "onFailure: " + error.toString());
                                }
                            });
                        } else if (iPluginCallback != null) {
                            String str = "";
                            if (device == null) {
                                try {
                                    str = str + "[device:null]";
                                } catch (RemoteException e) {
                                    e.printStackTrace();
                                    return;
                                }
                            }
                            if (m == null) {
                                str = str + "[home:null]";
                            }
                            iPluginCallback.onRequestFailed(0, str);
                        }
                    } else if (iPluginCallback != null) {
                        try {
                            iPluginCallback.onRequestFailed(0, "");
                        } catch (RemoteException e2) {
                            e2.printStackTrace();
                        }
                    }
                }

                public void onFailure(Error error) {
                    BluetoothLog.c(String.format("onFailure %s", new Object[]{error}));
                    if (iPluginCallback != null) {
                        try {
                            iPluginCallback.onRequestFailed(0, "getDeviceDetail fail");
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

        public void bleMeshConnect(String str, String str2, final IBleCallback iBleCallback) throws RemoteException {
            Device f = SmartHomeDeviceManager.a().f(str);
            if (f == null) {
                f = SmartHomeDeviceManager.a().c(str2);
            }
            if (f == null || !f.isBinded()) {
                BluetoothMyLogger.d(String.format("bleMeshConnect: %s has unbind", new Object[]{BluetoothMyLogger.a(str)}));
                if (iBleCallback != null) {
                    try {
                        iBleCallback.onResponse(-1, (Bundle) null);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                BluetoothHelper.a(str, str2, f.token, (Response.BleConnectResponse) new Response.BleConnectResponse() {
                    /* renamed from: a */
                    public void onResponse(int i, Bundle bundle) {
                        if (iBleCallback != null) {
                            try {
                                iBleCallback.onResponse(i, bundle);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        }

        public void bleStandardAuthConnect(String str, final IBleCallback iBleCallback) throws RemoteException {
            Device f = SmartHomeDeviceManager.a().f(str);
            if (f == null || !f.isBinded()) {
                BluetoothMyLogger.d(String.format("bleStandardAuthConnect: %s has unbind", new Object[]{BluetoothMyLogger.a(str)}));
                if (iBleCallback != null) {
                    try {
                        iBleCallback.onResponse(-1, (Bundle) null);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                BluetoothHelper.d(str, (Response.BleConnectResponse) new Response.BleConnectResponse() {
                    /* renamed from: a */
                    public void onResponse(int i, Bundle bundle) {
                        if (iBleCallback != null) {
                            try {
                                iBleCallback.onResponse(i, bundle);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        }

        public void refreshDeviceListUi() throws RemoteException {
            RnPluginLog.a("send broadcast refreshDeviceListUi...");
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(DeviceMainPage.f));
        }

        public String getVirtualGroupStatus(String str) throws RemoteException {
            return SmartHomeDeviceManager.a().e(str);
        }

        public void callBleApi(String str, int i, Bundle bundle, final IBleCallback iBleCallback) throws RemoteException {
            BluetoothHelper.a(str, i, bundle, (Response.BleCallResponse) new Response.BleCallResponse() {
                /* renamed from: a */
                public void onResponse(int i, Bundle bundle) {
                    try {
                        iBleCallback.onResponse(i, bundle);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        public void updateDevice(List<String> list, final IPluginCallbackDeviceList iPluginCallbackDeviceList) throws RemoteException {
            SmartHomeDeviceManager.a().a(list, (AsyncCallback<List<Device>, Error>) new AsyncCallback<List<Device>, Error>() {
                /* renamed from: a */
                public void onSuccess(List<Device> list) {
                    if (iPluginCallbackDeviceList != null) {
                        try {
                            ArrayList arrayList = new ArrayList();
                            if (list != null) {
                                for (Device newDeviceStatus : list) {
                                    arrayList.add(newDeviceStatus.newDeviceStatus());
                                }
                            }
                            iPluginCallbackDeviceList.onRequestSuccess(arrayList);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(Error error) {
                    if (iPluginCallbackDeviceList != null) {
                        try {
                            iPluginCallbackDeviceList.onRequestFailed(error.a(), error.b());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

        public String getPluginProcessName(int i, String str) throws RemoteException {
            return PluginRuntimeManager.getInstance().getPluginProcess(i, str).getValue();
        }

        public String getDevicePincode(String str) throws RemoteException {
            return VerifyManager.a(SHApplication.getAppContext()).a(str, 0);
        }

        public void loadBitmap(String str, final IPluginCallback3 iPluginCallback3) throws RemoteException {
            boolean z;
            try {
                if (Fresco.getImagePipelineFactory() != null) {
                    if (Fresco.getImagePipeline() != null) {
                        z = false;
                        if (z) {
                            ImageDownloadManager.a().a(str, (ImageDownloadManager.ImageCallback) new ImageDownloadManager.ImageCallback() {
                                public void a(Bitmap bitmap) {
                                    try {
                                        iPluginCallback3.onSuccess(bitmap);
                                    } catch (RemoteException e) {
                                        e.printStackTrace();
                                    }
                                }

                                public void a() {
                                    try {
                                        iPluginCallback3.onFailed();
                                    } catch (RemoteException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            return;
                        }
                        Fresco.getImagePipeline().fetchDecodedImage(ImageRequestBuilder.newBuilderWithSource(Uri.parse(str)).build(), (Object) null).subscribe(new BaseBitmapDataSubscriber() {
                            public void onNewResultImpl(@Nullable Bitmap bitmap) {
                                try {
                                    iPluginCallback3.onSuccess(bitmap);
                                } catch (RemoteException e) {
                                    e.printStackTrace();
                                }
                            }

                            public void onFailureImpl(DataSource dataSource) {
                                try {
                                    iPluginCallback3.onFailed();
                                } catch (RemoteException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, AsycnTaskExecutor.f22185a);
                        return;
                    }
                }
                iPluginCallback3.onFailed();
            } catch (Exception e) {
                e.printStackTrace();
                z = true;
            }
        }

        public void sendPluginAdRequest(String str, String str2) throws RemoteException {
            PluginAdManager.a(SHApplication.getAppContext()).a(str, str2);
        }

        public void stopPluginAd(String str) throws RemoteException {
            PluginAdManager.a(SHApplication.getAppContext()).a(str);
        }

        public void queryAd(String str, String str2, IAdCallback iAdCallback) throws RemoteException {
            PluginAdManager.a(SHApplication.getAppContext()).a(str, str2, iAdCallback);
        }

        public void reportAdShow(Advertisement advertisement) throws RemoteException {
            PluginAdManager.a(SHApplication.getAppContext()).a(advertisement);
        }

        public void reportAdClick() throws RemoteException {
            PluginAdManager.a(SHApplication.getAppContext()).a();
        }

        public void reportAdClose(Advertisement advertisement) throws RemoteException {
            PluginAdManager.a(SHApplication.getAppContext()).b(advertisement);
        }

        public void reportHotSpotShow() throws RemoteException {
            PluginAdManager.a(SHApplication.getAppContext()).b();
        }

        public void getDeviceRealIconByModel(String str, IPluginCallback3 iPluginCallback3) throws RemoteException {
            PluginRecord d;
            if (str != null && (d = CoreApi.a().d(str)) != null && d.t() != null) {
                loadBitmap(d.t(), iPluginCallback3);
            }
        }

        public void delDeviceBatch(List<String> list, final IPluginCallback iPluginCallback) throws RemoteException {
            SmartHomeDeviceManager.a().a(list, SHApplication.getAppContext(), (SmartHomeDeviceManager.IDelDeviceBatchCallback) new SmartHomeDeviceManager.IDelDeviceBatchCallback() {
                public void a() {
                    if (iPluginCallback != null) {
                        try {
                            iPluginCallback.onRequestSuccess("");
                        } catch (RemoteException unused) {
                        }
                    }
                }

                public void a(Error error) {
                    if (iPluginCallback != null) {
                        try {
                            iPluginCallback.onRequestFailed(error.a(), error.b());
                        } catch (RemoteException unused) {
                        }
                    }
                }
            });
        }

        public void openCameraFloatingWindow(final String str) throws RemoteException {
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    Device n = SmartHomeDeviceManager.a().n(str);
                    FloatingCameraManager.a();
                    if (n != null) {
                        FloatingCameraManager.a(n);
                    }
                }
            });
        }

        public void closeCameraFloatingWindow(String str) throws RemoteException {
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    FloatingCameraManager.a();
                }
            });
        }

        public String getDevicePropByDid(String str) throws RemoteException {
            JSONArray a2 = DevicePropSubscriber.a(str);
            if (a2 == null) {
                return null;
            }
            return a2.toString();
        }

        public boolean checkVoiceCtrlAuthorized(String str) throws RemoteException {
            return DeviceAuthManager.a().a(str);
        }

        public boolean checkIfVoiceCtrlAuthorizedExpired(String str) throws RemoteException {
            return DeviceAuthManager.a().a(str);
        }

        public boolean isUsrExpPlanEnabled(String str) {
            return AppUsrExpPlanUtil.a(PluginService.this.getApplicationContext(), str);
        }

        public void setUsrExpPlanEnabled(String str, boolean z) {
            AppUsrExpPlanUtil.a(PluginService.this.getApplicationContext(), str, z);
        }

        public void getWatchControllableDevices(String str, final IPluginCallback iPluginCallback) throws RemoteException {
            Request request;
            BluetoothLog.c(String.format("getWatchControllableDevices for %s", new Object[]{str}));
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(TSMAuthContants.PARAM_LANGUAGE, SHApplication.getAppContext().getResources().getConfiguration().locale.getLanguage().equals("zh") ? "zh_CN" : "en");
                jSONObject.put("name", "watchconfig");
                jSONObject.put("version", "1");
            } catch (JSONException unused) {
                if (iPluginCallback != null) {
                    iPluginCallback.onRequestFailed(-1, "params invalid");
                }
            }
            try {
                request = new Request.Builder().a("GET").b(PluginService.this.a(jSONObject)).a();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                request = null;
            }
            if (request != null) {
                HttpApi.a(request, (AsyncHandler) new AsyncHandler() {
                    public void onFailure(com.xiaomi.smarthome.library.http.Error error, Exception exc, okhttp3.Response response) {
                    }

                    public void onSuccess(Object obj, okhttp3.Response response) {
                    }

                    public void processFailure(Call call, IOException iOException) {
                    }

                    public void processResponse(okhttp3.Response response) {
                        try {
                            JSONObject jSONObject = new JSONObject(response.body().string());
                            if (!jSONObject.isNull("result")) {
                                JSONObject optJSONObject = jSONObject.optJSONObject("result");
                                if (optJSONObject != null) {
                                    String optString = optJSONObject.optString("content");
                                    if (!TextUtils.isEmpty(optString)) {
                                        iPluginCallback.onRequestSuccess(optString);
                                        return;
                                    }
                                }
                                iPluginCallback.onRequestFailed(-1, (String) null);
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
            }
        }

        public boolean checkIfSupportVoiceCtrl(String str) throws RemoteException {
            return DeviceAuthManager.a().b(str);
        }

        public void isBleGatewayExistInDeviceList(IBleCallback iBleCallback) throws RemoteException {
            boolean z;
            int i;
            boolean z2;
            Iterator<Device> it = SmartHomeDeviceManager.a().d().iterator();
            while (true) {
                z = true;
                i = 0;
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                Device next = it.next();
                PluginRecord d = CoreApi.a().d(next.model);
                if (next.isOwner() && d != null && d.c() != null && d.c().J() == 1) {
                    if (next instanceof MiioDeviceV2) {
                        String str = ((MiioDeviceV2) next).D;
                        List<SupportBleGatewayFirmwareVersion> f = AndroidCommonConfigManager.a().f();
                        if (f != null && f.size() > 0 && !TextUtils.isEmpty(str)) {
                            Iterator<SupportBleGatewayFirmwareVersion> it2 = f.iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    break;
                                }
                                SupportBleGatewayFirmwareVersion next2 = it2.next();
                                if (TextUtils.equals(next2.f13958a, next.model)) {
                                    if (BluetoothHelper.a(str, next2.b) < 0) {
                                        z2 = false;
                                    }
                                }
                            }
                        }
                    }
                    z2 = true;
                    if (z2) {
                        break;
                    }
                }
            }
            if (iBleCallback != null) {
                if (!z) {
                    i = -1;
                }
                iBleCallback.onResponse(i, (Bundle) null);
            }
        }

        public List<DeviceStatus> getFilterBluetoothDeviceList(String str) throws RemoteException {
            List<DeviceStatus> arrayList = new ArrayList<>();
            if (!TextUtils.isEmpty(str)) {
                Iterator<Map.Entry<String, List<String>>> it = CommonExtraConfigManager.a().b().entrySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Map.Entry next = it.next();
                    List list = (List) next.getValue();
                    if (TextUtils.equals(str, (String) next.getKey())) {
                        arrayList = getDeviceListV2(list);
                        break;
                    }
                }
            }
            for (DeviceStatus next2 : arrayList) {
                next2.n = "";
                next2.i = "";
                next2.q = 0.0d;
                next2.r = 0.0d;
            }
            return arrayList;
        }

        public List<DeviceStatus> getBleGatewayDeviceList() {
            ArrayList arrayList = new ArrayList();
            for (Device next : SmartHomeDeviceManager.a().d()) {
                PluginRecord d = CoreApi.a().d(next.model);
                if (!(!next.isOwner() || d == null || d.c() == null)) {
                    boolean z = true;
                    if (d.c().J() == 1) {
                        if (next instanceof MiioDeviceV2) {
                            String str = ((MiioDeviceV2) next).D;
                            List<SupportBleGatewayFirmwareVersion> f = AndroidCommonConfigManager.a().f();
                            if (f != null && f.size() > 0 && !TextUtils.isEmpty(str)) {
                                Iterator<SupportBleGatewayFirmwareVersion> it = f.iterator();
                                while (true) {
                                    if (!it.hasNext()) {
                                        break;
                                    }
                                    SupportBleGatewayFirmwareVersion next2 = it.next();
                                    if (TextUtils.equals(next2.f13958a, next.model)) {
                                        if (BluetoothHelper.a(str, next2.b) < 0) {
                                            z = false;
                                        }
                                    }
                                }
                            }
                        }
                        if (z) {
                            DeviceStatus deviceStatus = new DeviceStatus();
                            copyDevicsStatus(deviceStatus, next);
                            arrayList.add(deviceStatus);
                        }
                    }
                }
            }
            return arrayList;
        }

        public String getSpecInstanceStr(String str) {
            return MiotSpecCardManager.f().h(str);
        }

        public String getSpecProptyValueFromSpecCard(String str) {
            return MiotSpecCardManager.f().i(str);
        }

        public void getBleGatewaySubDevices(List<String> list, final IPluginCallbackDeviceList iPluginCallbackDeviceList) throws RemoteException {
            BleGatewayManager.a(list, new BleGatewayManager.BleGatewayBatchCallback() {
                public void a(List<BleGatewayManager.BleGateway> list) {
                    ArrayList<BleGatewayManager.BleGatewayItem> b2;
                    if (list == null || list.size() == 0) {
                        try {
                            iPluginCallbackDeviceList.onRequestSuccess(new ArrayList());
                        } catch (RemoteException e) {
                            LogUtil.b(PluginService.f17137a, "getBleGatewaySubDevices..." + e.toString());
                        }
                    } else {
                        int size = list.size();
                        ArrayList arrayList = new ArrayList();
                        for (int i = 0; i < size; i++) {
                            if (!(list.get(i) == null || (b2 = list.get(i).b()) == null || b2.size() <= 0)) {
                                int size2 = b2.size();
                                for (int i2 = 0; i2 < size2; i2++) {
                                    Device b3 = SmartHomeDeviceManager.a().b(b2.get(i2).a());
                                    if (b3 != null) {
                                        b3.rssi = b2.get(i2).b();
                                        if (!BleGatewayManager.a(list.get(i).a()) || !(b3.pid == Device.PID_BLE_MESH || b3.pid == Device.PID_VIRTUAL_GROUP)) {
                                            if (b3.property == null) {
                                                b3.property = new Bundle();
                                            }
                                            b3.property.putString("deviceGatewayType", "normal");
                                        } else {
                                            if (b3.property == null) {
                                                b3.property = new Bundle();
                                            }
                                            b3.property.putString("deviceGatewayType", "mesh");
                                        }
                                        arrayList.add(b3.newDeviceStatus());
                                    }
                                }
                            }
                        }
                        try {
                            iPluginCallbackDeviceList.onRequestSuccess(arrayList);
                        } catch (RemoteException e2) {
                            LogUtil.b(PluginService.f17137a, "getBleGatewaySubDevices..." + e2.toString());
                        }
                    }
                }
            });
        }

        public void initBandManager(String str, String str2, final IPluginCallback iPluginCallback) throws RemoteException {
            NfcChannelManager.a().a(PluginService.this.getApplicationContext(), str, str2, (Callback<Boolean>) new Callback<Boolean>() {
                /* renamed from: a */
                public void onSuccess(Boolean bool) {
                    if (iPluginCallback != null) {
                        try {
                            iPluginCallback.onRequestSuccess("");
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(int i, String str) {
                    try {
                        iPluginCallback.onRequestFailed(i, str);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        public void deInitBandManager() throws RemoteException {
            NfcChannelManager.a().g();
        }

        public void connectBand(String str, final IPluginCallback iPluginCallback) throws RemoteException {
            NfcChannelManager.a().a(str, (Callback<Integer>) new Callback<Integer>() {
                /* renamed from: a */
                public void onSuccess(Integer num) {
                    try {
                        iPluginCallback.onRequestSuccess(String.valueOf(num));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }

                public void onFailure(int i, String str) {
                    try {
                        iPluginCallback.onRequestFailed(i, str);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        public void getAllCards(final IPluginCallback iPluginCallback) throws RemoteException {
            NfcChannelManager.a().b((Callback<String>) new Callback<String>() {
                /* renamed from: a */
                public void onSuccess(String str) {
                    try {
                        iPluginCallback.onRequestSuccess(str);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }

                public void onFailure(int i, String str) {
                    try {
                        iPluginCallback.onRequestFailed(i, str);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        public void issueDoorCard(final IPluginCallback iPluginCallback) throws RemoteException {
            NfcChannelManager.a().c((Callback<Boolean>) new Callback<Boolean>() {
                /* renamed from: a */
                public void onSuccess(Boolean bool) {
                    if (iPluginCallback != null) {
                        try {
                            iPluginCallback.onRequestSuccess("");
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(int i, String str) {
                    try {
                        iPluginCallback.onRequestFailed(i, str);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        public void deleteCard(String str, final IPluginCallback iPluginCallback) throws RemoteException {
            NfcChannelManager.a().b(str, (Callback<Boolean>) new Callback<Boolean>() {
                /* renamed from: a */
                public void onSuccess(Boolean bool) {
                    if (iPluginCallback != null) {
                        try {
                            iPluginCallback.onRequestSuccess("");
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(int i, String str) {
                    try {
                        iPluginCallback.onRequestFailed(i, str);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        public void setDefaultCard(String str, final IPluginCallback iPluginCallback) throws RemoteException {
            NfcChannelManager.a().c(str, (Callback<Boolean>) new Callback<Boolean>() {
                /* renamed from: a */
                public void onSuccess(Boolean bool) {
                    if (iPluginCallback != null) {
                        try {
                            iPluginCallback.onRequestSuccess("");
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(int i, String str) {
                    try {
                        iPluginCallback.onRequestFailed(i, str);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        public void updateCard(String str, final IPluginCallback iPluginCallback) throws RemoteException {
            NfcChannelManager.a().d(str, (Callback<Boolean>) new Callback<Boolean>() {
                /* renamed from: a */
                public void onSuccess(Boolean bool) {
                    if (iPluginCallback != null) {
                        try {
                            iPluginCallback.onRequestSuccess("");
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(int i, String str) {
                    try {
                        iPluginCallback.onRequestFailed(i, str);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        public void getDefaultCardAndActivateInfo(final IPluginCallback iPluginCallback) throws RemoteException {
            NfcChannelManager.a().d((Callback<WritableArray>) new Callback<WritableArray>() {
                /* renamed from: a */
                public void onSuccess(WritableArray writableArray) {
                    if (iPluginCallback != null) {
                        try {
                            iPluginCallback.onRequestSuccess(writableArray.toString());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(int i, String str) {
                    try {
                        iPluginCallback.onRequestFailed(i, str);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        public int getUsePreviewConfig() throws RemoteException {
            return GlobalSetting.E ? 1 : 0;
        }
    };
    Handler mMainHandler = new Handler();

    /* access modifiers changed from: private */
    @NonNull
    public String a(JSONObject jSONObject) throws UnsupportedEncodingException {
        return ServerRouteUtil.a(SHApplication.getAppContext()) + "/app/service/getappconfig?data=" + URLEncoder.encode(jSONObject.toString(), "UTF-8");
    }

    /* access modifiers changed from: package-private */
    public void handleSuccess(IPluginCallback iPluginCallback, String str) {
        if (iPluginCallback != null) {
            try {
                iPluginCallback.onRequestSuccess(str);
            } catch (RemoteException unused) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void handleFail(IPluginCallback iPluginCallback, int i, String str) {
        if (iPluginCallback != null) {
            try {
                iPluginCallback.onRequestFailed(i, str);
            } catch (RemoteException unused) {
            }
        }
    }

    public IBinder onBind(Intent intent) {
        return this.b;
    }

    public void onCreate() {
        super.onCreate();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    static class AddDeviceListener implements SmartHomeDeviceManager.IClientDeviceListener {

        /* renamed from: a  reason: collision with root package name */
        Device f17197a;
        IPluginCallback b;

        public void a(int i, Device device) {
        }

        public void b(int i) {
        }

        AddDeviceListener(Device device, IPluginCallback iPluginCallback) {
            this.f17197a = device;
            this.b = iPluginCallback;
        }

        public void a(int i) {
            if (3 == i) {
                SmartHomeDeviceManager.a().c((SmartHomeDeviceManager.IClientDeviceListener) this);
                if (this.f17197a != null && SmartHomeDeviceManager.a().b(this.f17197a.did) != null) {
                    Context appContext = SHApplication.getAppContext();
                    Intent intent = new Intent(appContext, InitDeviceRoomActivity.class);
                    intent.putExtra("device_id", this.f17197a.did);
                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                    appContext.startActivity(intent);
                    if (this.b != null) {
                        try {
                            this.b.onRequestSuccess("");
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
