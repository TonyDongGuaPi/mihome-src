package com.xiaomi.smarthome.framework.plugin.mpk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.Utils.FileUtil;
import com.adobe.xmp.XMPConst;
import com.alipay.sdk.sys.a;
import com.google.android.exoplayer2.C;
import com.miui.tsmclient.net.TSMAuthContants;
import com.tencent.bugly.crashreport.BuglyLog;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.smtt.sdk.TbsReaderView;
import com.tutk.DecryptUtil;
import com.tutk.TuTkClient;
import com.xiaomi.aaccodec.AAcCodecImp;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.jr.account.IAccountProvider;
import com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor;
import com.xiaomi.mistream.XmStreamClient;
import com.xiaomi.plugin.core.XmPluginPackage;
import com.xiaomi.printer.PrinterControlImpl;
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
import com.xiaomi.router.miio.miioplugin.PluginServiceManager;
import com.xiaomi.router.miio.miioplugin.RoomStatus;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.bluetooth.Response;
import com.xiaomi.smarthome.bluetooth.XmBluetoothRecord;
import com.xiaomi.smarthome.camera.HLSDownloader;
import com.xiaomi.smarthome.camera.IXmStreamClient;
import com.xiaomi.smarthome.camera.XmAAcCodec;
import com.xiaomi.smarthome.camera.XmCameraP2p;
import com.xiaomi.smarthome.camera.XmMp4Record;
import com.xiaomi.smarthome.camera.XmP2PInfo;
import com.xiaomi.smarthome.camera.XmVideoViewGl;
import com.xiaomi.smarthome.camera.exopackage.MJExoPlayer;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.ModifyGroupActivity;
import com.xiaomi.smarthome.device.api.BleMeshFirmwareUpdateInfo;
import com.xiaomi.smarthome.device.api.BtFirmwareUpdateInfo;
import com.xiaomi.smarthome.device.api.BtFirmwareUpdateInfoV2;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.DeviceTag;
import com.xiaomi.smarthome.device.api.FirmwareUpdateInfo;
import com.xiaomi.smarthome.device.api.ICloudDataCallback;
import com.xiaomi.smarthome.device.api.KeyValuePair;
import com.xiaomi.smarthome.device.api.MessageCallback;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.device.api.ProductInfo;
import com.xiaomi.smarthome.device.api.RoomStat;
import com.xiaomi.smarthome.device.api.UserInfo;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.device.api.printer.PrinterControl;
import com.xiaomi.smarthome.device.api.spec.operation.ActionParam;
import com.xiaomi.smarthome.device.api.spec.operation.PropertyParam;
import com.xiaomi.smarthome.device.api.spec.operation.controller.DeviceController;
import com.xiaomi.smarthome.device.authorization.DeviceAuthManager;
import com.xiaomi.smarthome.device.authorization.page.DeviceAuthSlaveListActivity;
import com.xiaomi.smarthome.device.bluetooth.BleFirmwareUpgrader;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.device.utils.DeviceTagManager;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.download.DownloadManager;
import com.xiaomi.smarthome.download.MiWFDownloadManager;
import com.xiaomi.smarthome.fastvideo.VideoGlSurfaceView;
import com.xiaomi.smarthome.fastvideo.VideoPlayerBase;
import com.xiaomi.smarthome.fastvideo.VideoPlayerFFmpeg;
import com.xiaomi.smarthome.fastvideo.VideoPlayerRender;
import com.xiaomi.smarthome.fastvideo.VideoViewGlImpl;
import com.xiaomi.smarthome.fastvideo.decoder.Mp4Record;
import com.xiaomi.smarthome.feedback.FeedbackActivity;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.file.FileUtils;
import com.xiaomi.smarthome.frame.login.LoginApi;
import com.xiaomi.smarthome.frame.login.util.LoginGetAccountUtil;
import com.xiaomi.smarthome.frame.plugin.host.PluginHostApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.api.UserApi;
import com.xiaomi.smarthome.framework.api.UserConfig;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.framework.permission.PermissionHelper;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.international.ServerApi;
import com.xiaomi.smarthome.library.apache.http.message.BasicNameValuePair;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.dialog.AlwaysDeniedPermissionDialog;
import com.xiaomi.smarthome.library.common.network.LocalNetworkUtils;
import com.xiaomi.smarthome.library.common.network.Network;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.util.FontManager;
import com.xiaomi.smarthome.library.common.util.ListUtils;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.http.HttpApi;
import com.xiaomi.smarthome.library.http.Request;
import com.xiaomi.smarthome.library.http.async.AsyncHandler;
import com.xiaomi.smarthome.miio.CameraCryptoUtils;
import com.xiaomi.smarthome.miio.camera.cloudstorage.FFmpegJni;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoCryptoUtils;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoM3U8DownloadManager;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.FileDownloadTask;
import com.xiaomi.smarthome.newui.card.DeviceCardApi;
import com.xiaomi.smarthome.newui.card.spec.instance.SpecDeviceCodec;
import com.xiaomi.smarthome.plugin.Error;
import com.xiaomi.smarthome.plugin.devicesubscribe.PluginSubscribeCallback;
import com.xiaomi.smarthome.plugin.devicesubscribe.PluginUnSubscribeCallback;
import com.xiaomi.smarthome.scene.SmartHomeSceneUtility;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scenenew.lumiscene.LocalSceneBuilder;
import com.xiaomi.smarthome.scenenew.manager.PluginRecommendSceneManager;
import com.xiaomi.smarthome.setting.RnPluginDebugDeviceMock;
import com.xiaomi.smarthome.setting.ServerRouteUtil;
import com.xiaomi.smarthome.wxapi.WxPushHelper;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import com.xiaomi.zxing.BarcodeFormat;
import com.xiaomi.zxing.BinaryBitmap;
import com.xiaomi.zxing.EncodeHintType;
import com.xiaomi.zxing.RGBLuminanceSource;
import com.xiaomi.zxing.Result;
import com.xiaomi.zxing.WriterException;
import com.xiaomi.zxing.common.BitMatrix;
import com.xiaomi.zxing.common.HybridBinarizer;
import com.xiaomi.zxing.qrcode.QRCodeReader;
import com.xiaomi.zxing.qrcode.QRCodeWriter;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.SettingService;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import okhttp3.Call;
import org.apache.http.NameValuePair;
import org.aspectj.runtime.internal.AroundClosure;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PluginHostApiImpl extends PluginHostApi {
    static final String TAG = "XmPluginHostApiImpl";
    private static final String[] WHITELIST_SID = {"i.ai.mi.com"};
    private static MiWFDownloadManager mDownloadManager = null;
    IWXAPI wxapi;

    public class AjcClosure1 extends AroundClosure {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            return PluginHostApiImpl.openConnection_aroundBody0((PluginHostApiImpl) objArr2[0], (URL) objArr2[1]);
        }
    }

    public class AjcClosure3 extends AroundClosure {
        public AjcClosure3(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            return PluginHostApiImpl.openConnection_aroundBody2((PluginHostApiImpl) objArr2[0], (URL) objArr2[1]);
        }
    }

    public class AjcClosure5 extends AroundClosure {
        public AjcClosure5(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            return PluginHostApiImpl.openConnection_aroundBody4((PluginHostApiImpl) objArr2[0], (URL) objArr2[1]);
        }
    }

    static /* synthetic */ void lambda$getServerName$2() throws Exception {
    }

    public int getDrawableResIdByName(XmPluginPackage xmPluginPackage, String str) {
        return R.drawable.ic_launcher;
    }

    public int getMiHomeNotificationIcon() {
        return R.drawable.ic_launcher;
    }

    public boolean stopService(Intent intent, XmPluginPackage xmPluginPackage, Class cls) {
        return false;
    }

    public PluginHostApiImpl(Context context) {
        super(context);
        initRequest();
    }

    public String getPluginProcess(int i, String str) {
        if (PluginServiceManager.a().b() == null) {
            return null;
        }
        try {
            return PluginServiceManager.a().b().getPluginProcessName(i, str);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void ensureService(Callback callback) {
        PluginServiceManager.a().c(new PluginServiceManager.BindServiceListener() {
            public final void onBindService(IPluginRequest iPluginRequest) {
                Callback.this.onSuccess(null);
            }
        });
    }

    private void initRequest() {
        try {
            PluginServiceManager.a().b();
        } catch (Exception e) {
            e.printStackTrace();
            MyLog.d(e.getMessage());
            if (!ServerCompact.e(context())) {
                BuglyLog.e("pluginserver", "PluginHostApiImpl.initRequest:" + e.getMessage());
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public Application application() {
        return SHApplication.getApplication();
    }

    public Context context() {
        return SHApplication.getAppContext();
    }

    /* access modifiers changed from: package-private */
    public Looper getLooper() {
        Looper myLooper = Looper.myLooper();
        return myLooper == null ? Looper.getMainLooper() : myLooper;
    }

    /* access modifiers changed from: package-private */
    public <T> void handlerSuccess(final Callback<T> callback, final T t, Looper looper) {
        Log.d(TAG, "handlerSuccess");
        if (callback != null) {
            if (looper == null) {
                looper = Looper.getMainLooper();
            }
            new Handler(looper).post(new Runnable() {
                public void run() {
                    if (callback != null) {
                        callback.onSuccess(t);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public <T> void handlerFailed(final Callback<T> callback, final int i, final String str, Looper looper) {
        Log.d(TAG, "handlerFailed");
        if (callback != null) {
            if (looper == null) {
                looper = Looper.getMainLooper();
            }
            new Handler(looper).post(new Runnable() {
                public void run() {
                    if (callback != null) {
                        callback.onFailure(i, str);
                    }
                }
            });
        }
    }

    public <T> void callHttpApi(String str, String str2, String str3, List<NameValuePair> list, Callback<T> callback, Parser<T> parser) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (NameValuePair next : list) {
                arrayList.add(new BasicNameValuePair(next.getName(), next.getValue()));
            }
        }
        NetworkUtils.a(str2, str3, (List<com.xiaomi.smarthome.library.apache.http.NameValuePair>) arrayList, callback, parser);
    }

    public <T> void callHttpApiV13(String str, String str2, String str3, List<KeyValuePair> list, Callback<T> callback, Parser<T> parser) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (KeyValuePair next : list) {
                arrayList.add(new BasicNameValuePair(next.getKey(), next.getValue()));
            }
            NetworkUtils.a(str2, str3, (List<com.xiaomi.smarthome.library.apache.http.NameValuePair>) arrayList, callback, parser);
        }
    }

    public List<DeviceStat> getDeviceList() {
        if (PluginServiceManager.a().b() == null) {
            return null;
        }
        try {
            List<DeviceStatus> deviceList = PluginServiceManager.a().b().getDeviceList();
            if (deviceList == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (DeviceStatus a2 : deviceList) {
                arrayList.add(a2.a());
            }
            return arrayList;
        } catch (RemoteException unused) {
            return null;
        }
    }

    public PrinterControl getPrinterControl() {
        return new PrinterControlImpl();
    }

    public List<DeviceStat> getDeviceListV2(List<String> list) {
        if (PluginServiceManager.a().b() == null) {
            return null;
        }
        try {
            List<DeviceStatus> deviceListV2 = PluginServiceManager.a().b().getDeviceListV2(list);
            if (deviceListV2 == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (DeviceStatus a2 : deviceListV2) {
                arrayList.add(a2.a());
            }
            return arrayList;
        } catch (RemoteException unused) {
            return null;
        }
    }

    public List<RoomStat> getRoomAll() {
        IPluginRequest b = PluginServiceManager.a().b();
        if (b != null) {
            try {
                List<RoomStatus> roomAll = b.getRoomAll();
                if (roomAll != null) {
                    ArrayList arrayList = new ArrayList();
                    for (RoomStatus a2 : roomAll) {
                        arrayList.add(a2.a());
                    }
                    return arrayList;
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return Collections.emptyList();
    }

    public void deleteRoom(List<String> list, final Callback<Void> callback) {
        IPluginRequest b = PluginServiceManager.a().b();
        if (b != null) {
            try {
                b.deleteRooms(list, new IPluginCallback2.Stub() {
                    public void onRequestSuccess(Intent intent) throws RemoteException {
                        if (callback != null) {
                            callback.onSuccess(null);
                        }
                    }

                    public void onRequestFailed(int i, String str) throws RemoteException {
                        if (callback != null) {
                            callback.onFailure(i, str);
                        }
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void renameRoom(String str, String str2, final Callback<Void> callback) {
        IPluginRequest b = PluginServiceManager.a().b();
        if (b != null) {
            try {
                b.roomRename(str, str2, new IPluginCallback2.Stub() {
                    public void onRequestSuccess(Intent intent) throws RemoteException {
                        if (callback != null) {
                            callback.onSuccess(null);
                        }
                    }

                    public void onRequestFailed(int i, String str) throws RemoteException {
                        if (callback != null) {
                            callback.onFailure(i, str);
                        }
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void addRoom(final RoomStat roomStat, final Callback<RoomStat> callback) {
        IPluginRequest b = PluginServiceManager.a().b();
        if (b != null) {
            RoomStatus roomStatus = new RoomStatus();
            if (roomStat != null) {
                roomStatus.d = roomStat.name;
                roomStatus.h = roomStat.icon;
                roomStatus.f13055a = roomStat.bssid;
                roomStatus.e = roomStat.parentid;
                roomStatus.g = roomStat.dids;
            }
            try {
                b.addRoom(roomStatus, new IPluginCallbackRoomStatus.Stub() {
                    public void onRequestSuccess(RoomStatus roomStatus) throws RemoteException {
                        if (roomStatus != null) {
                            callback.onSuccess(roomStatus.a());
                        } else {
                            callback.onSuccess(roomStat);
                        }
                    }

                    public void onRequestFailed(int i, String str) throws RemoteException {
                        callback.onFailure(i, str);
                    }
                });
            } catch (RemoteException e) {
                LogUtil.b("PluginHostApi", "addRoom  " + e.toString());
            }
        }
    }

    public DeviceStat getDeviceByDid(String str) {
        if ("00-00-00-00-00-00".equals(str)) {
            return RnPluginDebugDeviceMock.b();
        }
        if (PluginServiceManager.a().b() == null) {
            return null;
        }
        try {
            DeviceStatus device = PluginServiceManager.a().b().getDevice(str);
            if (device != null) {
                return device.a();
            }
            return null;
        } catch (RemoteException e) {
            LogUtil.b(TAG, "RemoteException:" + e.getLocalizedMessage());
            return null;
        }
    }

    public DeviceTag getDeviceTagByDid(String str) {
        if (PluginServiceManager.a().b() == null) {
            return null;
        }
        try {
            DeviceTagInfo deviceTagInfo = PluginServiceManager.a().b().getDeviceTagInfo(str);
            if (deviceTagInfo != null) {
                return deviceTagInfo.a();
            }
            return null;
        } catch (RemoteException unused) {
            return null;
        }
    }

    public String getLightDeviceGroupModel(String str) {
        if (PluginServiceManager.a().b() != null) {
            try {
                return PluginServiceManager.a().b().getLightDeviceGroupModel(str);
            } catch (RemoteException e) {
                LogUtil.b(TAG, "getLightDeviceGroupModel error:" + Log.getStackTraceString(e));
            }
        }
        return "";
    }

    public void addTag(String str, String str2) {
        if (PluginServiceManager.a().b() != null) {
            try {
                PluginServiceManager.a().b().addTag(str, str2);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeTag(String str) {
        if (PluginServiceManager.a().b() != null) {
            try {
                PluginServiceManager.a().b().removeTag(str);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public List<DeviceStat> getSubDeviceByParentDid(String str) {
        if (!TextUtils.isEmpty(str) && PluginServiceManager.a().b() != null) {
            try {
                List<DeviceStatus> subDeviceByParentDid = PluginServiceManager.a().b().getSubDeviceByParentDid(str);
                if (subDeviceByParentDid != null) {
                    ArrayList arrayList = new ArrayList();
                    for (DeviceStatus a2 : subDeviceByParentDid) {
                        arrayList.add(a2.a());
                    }
                    return arrayList;
                }
            } catch (RemoteException unused) {
            }
        }
        return null;
    }

    public void recordCountEvent(String str, String str2) {
        addRecord(str, str2, (Object) 1, (JSONObject) null);
    }

    public void recordCountEvent(String str, String str2, Map<String, String> map) {
        addRecord(str, str2, (Object) map, (JSONObject) null);
    }

    public void recordCalculateEvent(String str, String str2, long j) {
        addRecord(str, str2, (Object) Long.valueOf(j), (JSONObject) null);
    }

    public void recordCalculateEvent(String str, String str2, long j, Map<String, String> map) {
        addRecord(str, str2, (Object) Long.valueOf(j), (JSONObject) null);
    }

    public void recordStringPropertyEvent(String str, String str2, String str3) {
        addRecord(str, str2, (Object) str3, (JSONObject) null);
    }

    public void recordNumericPropertyEvent(String str, String str2, long j) {
        addRecord(str, str2, (Object) Long.valueOf(j), (JSONObject) null);
    }

    public void subscribeDevice(String str, int i, List<String> list, int i2, final Callback<Void> callback) {
        final Looper looper = getLooper();
        AnonymousClass6 r6 = new IPluginCallback.Stub() {
            public void onRequestFailed(int i, String str) throws RemoteException {
                PluginHostApiImpl.this.handlerFailed(callback, i, str, looper);
            }

            public void onRequestSuccess(String str) throws RemoteException {
                PluginHostApiImpl.this.handlerSuccess(callback, null, looper);
            }
        };
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().subscribeDevice(str, i, list, i2, r6);
            } else if (callback != null) {
                callback.onFailure(ErrorCode.INVALID.getCode(), "PluginRequest is null");
            }
        } catch (RemoteException e) {
            if (callback != null) {
                callback.onFailure(ErrorCode.INVALID.getCode(), e.toString());
            }
        }
    }

    public void unsubscribeDevice(String str, int i, List<String> list, final Callback<Void> callback) {
        final Looper looper = getLooper();
        AnonymousClass7 r1 = new IPluginCallback.Stub() {
            public void onRequestFailed(int i, String str) throws RemoteException {
                PluginHostApiImpl.this.handlerFailed(callback, i, str, looper);
            }

            public void onRequestSuccess(String str) throws RemoteException {
                PluginHostApiImpl.this.handlerSuccess(callback, null, looper);
            }
        };
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().unsubscribeDevice(str, i, list, r1);
            } else if (callback != null) {
                callback.onFailure(ErrorCode.INVALID.getCode(), "PluginRequest is null");
            }
        } catch (RemoteException e) {
            if (callback != null) {
                callback.onFailure(ErrorCode.INVALID.getCode(), e.toString());
            }
        }
    }

    /* access modifiers changed from: private */
    public Looper getCallbackLooper(Looper looper) {
        return looper == null ? Looper.getMainLooper() : looper;
    }

    public void subscribeDeviceV2(String str, int i, List<String> list, int i2, final PluginSubscribeCallback pluginSubscribeCallback) {
        final Looper looper = getLooper();
        AnonymousClass8 r6 = new IPluginCallback2.Stub() {
            public void onRequestSuccess(Intent intent) throws RemoteException {
                String stringExtra = intent.getStringExtra("subscribeDeviceV2_flag");
                if (!TextUtils.isEmpty(stringExtra)) {
                    if (stringExtra.equalsIgnoreCase("onSuccess")) {
                        final String stringExtra2 = intent.getStringExtra("subscribeDeviceV2_subid");
                        if (pluginSubscribeCallback != null) {
                            new Handler(PluginHostApiImpl.this.getCallbackLooper(looper)).post(new Runnable() {
                                public void run() {
                                    pluginSubscribeCallback.onSuccess(stringExtra2);
                                }
                            });
                        }
                    } else if (stringExtra.equalsIgnoreCase("onReceive")) {
                        final String stringExtra3 = intent.getStringExtra("subscribeDeviceV2_did");
                        final String stringExtra4 = intent.getStringExtra("subscribeDeviceV2_model");
                        final JSONArray jSONArray = null;
                        try {
                            jSONArray = new JSONArray(intent.getStringExtra("subscribeDeviceV2_entry"));
                        } catch (JSONException unused) {
                        }
                        if (pluginSubscribeCallback != null) {
                            new Handler(PluginHostApiImpl.this.getCallbackLooper(looper)).post(new Runnable() {
                                public void run() {
                                    pluginSubscribeCallback.onReceive(stringExtra3, stringExtra4, jSONArray);
                                }
                            });
                        }
                    }
                }
            }

            public void onRequestFailed(final int i, final String str) throws RemoteException {
                if (pluginSubscribeCallback != null) {
                    new Handler(PluginHostApiImpl.this.getCallbackLooper(looper)).post(new Runnable() {
                        public void run() {
                            pluginSubscribeCallback.onFailure(new Error(i, str));
                        }
                    });
                }
            }
        };
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().subscribeDeviceV2(str, i, list, i2, r6);
            } else if (pluginSubscribeCallback != null) {
                pluginSubscribeCallback.onFailure(new Error(ErrorCode.INVALID.getCode(), "PluginRequest is null"));
            }
        } catch (RemoteException e) {
            if (pluginSubscribeCallback != null) {
                pluginSubscribeCallback.onFailure(new Error(ErrorCode.INVALID.getCode(), e.toString()));
            }
        }
    }

    public void unsubscribeDeviceV2(String str, int i, List<String> list, String str2, final PluginUnSubscribeCallback pluginUnSubscribeCallback) {
        final Looper looper = getLooper();
        AnonymousClass9 r6 = new IPluginCallback2.Stub() {
            public void onRequestSuccess(Intent intent) throws RemoteException {
                if (pluginUnSubscribeCallback != null) {
                    new Handler(PluginHostApiImpl.this.getCallbackLooper(looper)).post(new Runnable() {
                        public void run() {
                            pluginUnSubscribeCallback.onSuccess();
                        }
                    });
                }
            }

            public void onRequestFailed(final int i, final String str) throws RemoteException {
                if (pluginUnSubscribeCallback != null) {
                    new Handler(PluginHostApiImpl.this.getCallbackLooper(looper)).post(new Runnable() {
                        public void run() {
                            pluginUnSubscribeCallback.onFailure(new Error(i, str));
                        }
                    });
                }
            }
        };
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().unsubscribeDeviceV2(str, i, list, str2, r6);
            } else if (pluginUnSubscribeCallback != null) {
                pluginUnSubscribeCallback.onFailure(new Error(ErrorCode.INVALID.getCode(), "PluginRequest is null"));
            }
        } catch (RemoteException e) {
            if (pluginUnSubscribeCallback != null) {
                pluginUnSubscribeCallback.onFailure(new Error(ErrorCode.INVALID.getCode(), e.toString()));
            }
        }
    }

    public <T> void callMethod(String str, String str2, Callback<T> callback, Parser<T> parser) {
        request(str, str2, false, callback, parser);
    }

    public <T> void callMethodFromLocal(String str, String str2, Callback<T> callback, Parser<T> parser) {
        requestFromLocal(str, str2, callback, parser);
    }

    public <T> void callMethodFromCloud(String str, String str2, Callback<T> callback, Parser<T> parser) {
        request(str, str2, true, callback, parser);
    }

    public <T> void callLocalHttpApi(String str, String str2, String str3, List<NameValuePair> list, Callback<T> callback, Parser<T> parser) {
        final ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (NameValuePair next : list) {
                arrayList.add(new BasicNameValuePair(next.getName(), next.getValue()));
            }
        }
        final String str4 = str2;
        final String str5 = str3;
        final Callback<T> callback2 = callback;
        final Parser<T> parser2 = parser;
        SHApplication.getGlobalHandler().post(new Runnable() {
            public void run() {
                try {
                    LocalNetworkUtils.a(str4, str5, (List<com.xiaomi.smarthome.library.apache.http.NameValuePair>) arrayList, callback2, parser2);
                } catch (Exception unused) {
                }
            }
        });
    }

    public <T> void callLocalHttpApiV13(String str, String str2, String str3, List<KeyValuePair> list, Callback<T> callback, Parser<T> parser) {
        final ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (KeyValuePair next : list) {
                arrayList.add(new BasicNameValuePair(next.getKey(), next.getValue()));
            }
        }
        final String str4 = str2;
        final String str5 = str3;
        final Callback<T> callback2 = callback;
        final Parser<T> parser2 = parser;
        SHApplication.getGlobalHandler().post(new Runnable() {
            public void run() {
                try {
                    LocalNetworkUtils.a(str4, str5, (List<com.xiaomi.smarthome.library.apache.http.NameValuePair>) arrayList, callback2, parser2);
                } catch (Exception unused) {
                }
            }
        });
    }

    public void modDeviceName(String str, String str2, final Callback<Void> callback) {
        final Looper looper = getLooper();
        AnonymousClass12 r1 = new IPluginCallback.Stub() {
            public void onRequestFailed(int i, String str) throws RemoteException {
                PluginHostApiImpl.this.handlerFailed(callback, i, str, looper);
            }

            public void onRequestSuccess(String str) throws RemoteException {
                PluginHostApiImpl.this.handlerSuccess(callback, null, looper);
            }
        };
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().modDeviceName(str, str2, r1);
            } else if (callback != null) {
                callback.onFailure(ErrorCode.INVALID.getCode(), "PluginRequest is null");
            }
        } catch (RemoteException e) {
            if (callback != null) {
                callback.onFailure(ErrorCode.INVALID.getCode(), e.toString());
            }
        }
    }

    public void unBindDevice(String str, int i, final Callback<Void> callback) {
        final Looper looper = getLooper();
        AnonymousClass13 r1 = new IPluginCallback.Stub() {
            public void onRequestFailed(int i, String str) throws RemoteException {
                PluginHostApiImpl.this.handlerFailed(callback, i, str, looper);
            }

            public void onRequestSuccess(String str) throws RemoteException {
                PluginHostApiImpl.this.handlerSuccess(callback, null, looper);
            }
        };
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().unBindDevice(str, i, r1);
            } else if (callback != null) {
                callback.onFailure(ErrorCode.INVALID.getCode(), "PluginRequest is null");
            }
        } catch (RemoteException e) {
            if (callback != null) {
                callback.onFailure(ErrorCode.INVALID.getCode(), e.toString());
            }
        }
    }

    public Location getLastLocation() {
        try {
            if (PluginServiceManager.a().b() != null) {
                return PluginServiceManager.a().b().getLastLocation();
            }
            return null;
        } catch (RemoteException unused) {
            return null;
        }
    }

    public boolean isNetworkLocationEnabled() {
        try {
            if (PluginServiceManager.a().b() != null) {
                return PluginServiceManager.a().b().isNetworkLocationEnabled();
            }
            return false;
        } catch (RemoteException unused) {
            return false;
        }
    }

    public boolean isGPSLocationEnable() {
        try {
            if (PluginServiceManager.a().b() != null) {
                return PluginServiceManager.a().b().isGPSLocationEnable();
            }
            return false;
        } catch (RemoteException unused) {
            return false;
        }
    }

    public void requestLocation(final Callback<Location> callback) {
        final Looper looper = getLooper();
        AnonymousClass14 r1 = new ILocationCallback.Stub() {
            public void onSuccess() throws RemoteException {
                Location lastLocation = PluginHostApiImpl.this.getLastLocation();
                if (lastLocation == null) {
                    PluginHostApiImpl.this.handlerFailed(callback, -1, "", looper);
                } else {
                    PluginHostApiImpl.this.handlerSuccess(callback, lastLocation, looper);
                }
            }

            public void onFailure() throws RemoteException {
                PluginHostApiImpl.this.handlerFailed(callback, -1, "", looper);
            }
        };
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().requestLocation(r1);
            } else if (callback != null) {
                callback.onFailure(ErrorCode.INVALID.getCode(), "PluginRequest is null");
            }
        } catch (RemoteException e) {
            if (callback != null) {
                callback.onFailure(ErrorCode.INVALID.getCode(), e.toString());
            }
        }
    }

    public void getServiceToken(String str, final Callback<JSONObject> callback) {
        if (Arrays.binarySearch(WHITELIST_SID, str) < 0) {
            callback.onFailure(-1, "Not in the scope of permission");
        } else {
            LoginGetAccountUtil.a(SHApplication.getAppContext(), "i.ai.mi.com", new AsyncCallback<AccountInfo, com.xiaomi.smarthome.frame.Error>() {
                public void onSuccess(AccountInfo accountInfo) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("cUserId", accountInfo.getEncryptedUserId());
                        jSONObject.put("serviceToken", accountInfo.getServiceToken());
                        jSONObject.put("ssecurity", accountInfo.getSecurity());
                        jSONObject.put("ph", accountInfo.getPh());
                        jSONObject.put(IAccountProvider.d, accountInfo.getSlh());
                        callback.onSuccess(jSONObject);
                    } catch (Throwable th) {
                        Log.e("getServiceToken", "i.ai.mi.com", th);
                        callback.onFailure(-3, "format json failed");
                    }
                }

                public void onFailure(com.xiaomi.smarthome.frame.Error error) {
                    callback.onFailure(-4, error.b());
                }
            });
        }
    }

    public List<DeviceStat> getIrDevList() {
        List<PluginRecord> O = CoreApi.a().O();
        ArrayList arrayList = new ArrayList();
        for (PluginRecord next : O) {
            if (IRDeviceUtil.a(next)) {
                DeviceStat deviceStat = new DeviceStat();
                deviceStat.model = next.o();
                deviceStat.name = next.p();
                deviceStat.deviceIconReal = next.t();
                if (next.c() != null) {
                    deviceStat.pid = next.c().t();
                }
                arrayList.add(deviceStat);
            }
        }
        return arrayList;
    }

    public String getProperty(String str, String str2) {
        if ("account.xiaoqiang.userId".equals(str2)) {
            return CoreApi.a().s();
        }
        if ("account.xiaoqiang.serviceToken".equals(str2)) {
            MiServiceTokenInfo a2 = CoreApi.a().a("xiaoqiang");
            if (a2 != null) {
                return a2.c;
            }
            return "";
        } else if ("account.xiaoqiang.timediff".equals(str2)) {
            MiServiceTokenInfo a3 = CoreApi.a().a("xiaoqiang");
            if (a3 != null) {
                return Long.toString(a3.e);
            }
            return "";
        } else if ("account.xiaoqiang.ssecurity".equals(str2)) {
            MiServiceTokenInfo a4 = CoreApi.a().a("xiaoqiang");
            if (a4 != null) {
                return a4.d;
            }
            return "";
        } else if ("account.xiaoqiang.passtoken".equals(str2)) {
            return CoreApi.a().w();
        } else {
            return null;
        }
    }

    public void updateDeviceList(final Callback<Void> callback) {
        final Looper looper = getLooper();
        AnonymousClass16 r1 = new IPluginCallback.Stub() {
            public void onRequestFailed(int i, String str) throws RemoteException {
                PluginHostApiImpl.this.handlerFailed(callback, i, str, looper);
            }

            public void onRequestSuccess(String str) throws RemoteException {
                PluginHostApiImpl.this.handlerSuccess(callback, null, looper);
            }
        };
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().updateDeviceList(r1);
            } else if (callback != null) {
                callback.onFailure(ErrorCode.INVALID.getCode(), "PluginRequest is null");
            }
        } catch (RemoteException e) {
            if (callback != null) {
                callback.onFailure(ErrorCode.INVALID.getCode(), e.toString());
            }
        }
    }

    public void updateDeviceProperties(String str, JSONObject jSONObject) {
        try {
            if (PluginServiceManager.a().b() != null && jSONObject != null) {
                PluginServiceManager.a().b().updateDeviceProperties(str, jSONObject.toString());
            }
        } catch (RemoteException unused) {
        }
    }

    public void log(String str, String str2) {
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().log(str, str2);
            }
        } catch (RemoteException unused) {
        }
    }

    public void logByModel(String str, String str2) {
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().logByModel(str, str2);
            }
        } catch (RemoteException unused) {
        }
    }

    public void startService(Intent intent, XmPluginPackage xmPluginPackage, Class cls) {
        Intent intent2 = new Intent(SHApplication.getAppContext(), XmPluginService.class);
        if (intent != null) {
            intent2.putExtras(intent);
        }
        intent2.putExtra("extra_package", xmPluginPackage.packageName);
        intent2.putExtra("extra_class", cls.getName());
        SHApplication.getAppContext().startActivity(intent2);
    }

    public boolean bindService(Intent intent, XmPluginPackage xmPluginPackage, Class cls, ServiceConnection serviceConnection, int i) {
        Intent intent2 = new Intent(SHApplication.getAppContext(), XmPluginService.class);
        if (intent != null) {
            intent2.putExtras(intent);
        }
        intent2.putExtra("extra_package", xmPluginPackage.packageName);
        intent2.putExtra("extra_class", cls.getName());
        return SHApplication.getAppContext().bindService(intent2, serviceConnection, i);
    }

    public void addToLauncher(XmPluginPackage xmPluginPackage, String str, Intent intent) {
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().addToLauncher(str, intent);
            }
        } catch (RemoteException unused) {
        }
    }

    public void setSubDeviceShownMode(XmPluginPackage xmPluginPackage, boolean z, String str, Context context, final Callback<Void> callback) {
        final Looper looper = getLooper();
        AnonymousClass17 r4 = new IPluginCallback.Stub() {
            public void onRequestFailed(int i, String str) throws RemoteException {
                PluginHostApiImpl.this.handlerFailed(callback, i, str, looper);
            }

            public void onRequestSuccess(String str) throws RemoteException {
                PluginHostApiImpl.this.handlerSuccess(callback, null, looper);
            }
        };
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().setSubDeviceShownMode(str, z, r4);
            } else if (callback != null) {
                callback.onFailure(ErrorCode.INVALID.getCode(), "PluginRequest is null");
            }
        } catch (RemoteException e) {
            if (callback != null) {
                callback.onFailure(ErrorCode.INVALID.getCode(), e.toString());
            }
        }
    }

    public String getRouterFileDownloadUrl(String str) {
        try {
            return PluginServiceManager.a().b() != null ? PluginServiceManager.a().b().getRouterFileDownloadUrl(str) : "";
        } catch (RemoteException unused) {
            return "";
        }
    }

    public void checkLocalRouterInfo(String str, final Callback<Void> callback) {
        final Looper looper = getLooper();
        AnonymousClass18 r1 = new IPluginCallback.Stub() {
            public void onRequestFailed(int i, String str) throws RemoteException {
                PluginHostApiImpl.this.handlerFailed(callback, i, str, looper);
            }

            public void onRequestSuccess(String str) throws RemoteException {
                PluginHostApiImpl.this.handlerSuccess(callback, null, looper);
            }
        };
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().checkLocalRouterInfo(str, r1);
            } else if (callback != null) {
                callback.onFailure(ErrorCode.INVALID.getCode(), "PluginRequest is null");
            }
        } catch (RemoteException e) {
            if (callback != null) {
                callback.onFailure(ErrorCode.INVALID.getCode(), e.toString());
            }
        }
    }

    public boolean isLocalMiRouter() {
        try {
            if (PluginServiceManager.a().b() != null) {
                return PluginServiceManager.a().b().isLocalMiRouter();
            }
            return false;
        } catch (RemoteException unused) {
            return false;
        }
    }

    public void gotoPage(Context context, XmPluginPackage xmPluginPackage, Uri uri, Callback<Void> callback) {
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().gotoPage(uri);
            }
        } catch (RemoteException unused) {
        }
    }

    public void sendMessage(String str, int i, Intent intent, DeviceStat deviceStat, final MessageCallback messageCallback) {
        final Looper looper = getLooper();
        AnonymousClass19 r0 = messageCallback != null ? new IPluginCallback2.Stub() {
            public void onRequestFailed(final int i, final String str) throws RemoteException {
                new Handler(looper).post(new Runnable() {
                    public void run() {
                        if (messageCallback != null) {
                            messageCallback.onFailure(i, str);
                        }
                    }
                });
            }

            public void onRequestSuccess(final Intent intent) throws RemoteException {
                new Handler(looper).post(new Runnable() {
                    public void run() {
                        if (messageCallback != null) {
                            messageCallback.onSuccess(intent);
                        }
                    }
                });
            }
        } : null;
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().sendMessage(str, i, intent, r0);
            } else if (messageCallback != null) {
                messageCallback.onFailure(ErrorCode.INVALID.getCode(), "PluginRequest is null");
            }
        } catch (RemoteException e) {
            if (messageCallback != null) {
                messageCallback.onFailure(ErrorCode.INVALID.getCode(), e.toString());
            }
        }
    }

    public void notifyBluetoothBinded(String str, String str2) {
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().notifyBluetoothBinded(str.toUpperCase(), str2);
            }
        } catch (RemoteException unused) {
        }
    }

    public void getUserInfo(String str, final Callback<UserInfo> callback) {
        final Looper looper = getLooper();
        AnonymousClass20 r1 = new IPluginCallbackUserInfo.Stub() {
            public void onRequestFailed(int i, String str) throws RemoteException {
                PluginHostApiImpl.this.handlerFailed(callback, i, str, looper);
            }

            public void onRequestSuccess(UserInfo userInfo) throws RemoteException {
                PluginHostApiImpl.this.handlerSuccess(callback, userInfo, looper);
            }
        };
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().getUserInfo(str, r1);
            } else if (callback != null) {
                callback.onFailure(ErrorCode.INVALID.getCode(), "PluginRequest is null");
            }
        } catch (RemoteException e) {
            if (callback != null) {
                callback.onFailure(ErrorCode.INVALID.getCode(), e.toString());
            }
        }
    }

    @Deprecated
    public long startDownload(Uri uri, String str, String str2, String str3) {
        DownloadManager.Request request = new DownloadManager.Request(uri, str);
        request.a(SHApplication.getAppContext(), str2, str3);
        MiWFDownloadManager downloadManager = getDownloadManager();
        if (downloadManager != null) {
            return downloadManager.a(request);
        }
        return 0;
    }

    @Deprecated
    private MiWFDownloadManager getDownloadManager() {
        if (mDownloadManager == null) {
            synchronized (DownloadManager.class) {
                if (mDownloadManager == null) {
                    mDownloadManager = new MiWFDownloadManager(SHApplication.getAppContext(), SHApplication.getAppContext().getContentResolver(), SHApplication.getAppContext().getPackageName());
                }
            }
        }
        return mDownloadManager;
    }

    @Deprecated
    public void pauseDownload(long... jArr) {
        MiWFDownloadManager downloadManager = getDownloadManager();
        if (downloadManager != null) {
            downloadManager.c(jArr);
        }
    }

    @Deprecated
    public void resumeDownload(long... jArr) {
        MiWFDownloadManager downloadManager = getDownloadManager();
        if (downloadManager != null) {
            downloadManager.d(jArr);
        }
    }

    @Deprecated
    public void restartDownload(long... jArr) {
        MiWFDownloadManager downloadManager = getDownloadManager();
        if (downloadManager != null) {
            downloadManager.e(jArr);
        }
    }

    @Deprecated
    public void removeDownload(long... jArr) {
        MiWFDownloadManager downloadManager = getDownloadManager();
        if (downloadManager != null) {
            downloadManager.b(jArr);
        }
    }

    @Deprecated
    public Cursor queryDownload(boolean z, long... jArr) {
        MiWFDownloadManager downloadManager = getDownloadManager();
        if (downloadManager == null) {
            return null;
        }
        DownloadManager.Query query = new DownloadManager.Query();
        query.a(jArr).a(z);
        return downloadManager.a(query);
    }

    @Deprecated
    public void notifyLocalWifiConnect(boolean z) {
        MiWFDownloadManager downloadManager = getDownloadManager();
        if (downloadManager != null) {
            downloadManager.b(z);
        }
    }

    public void updateSubDevice(XmPluginPackage xmPluginPackage, String[] strArr, final Callback<List<DeviceStat>> callback) {
        final Looper looper = getLooper();
        AnonymousClass21 r0 = new IPluginCallbackDeviceList.Stub() {
            public void onRequestFailed(int i, String str) throws RemoteException {
                PluginHostApiImpl.this.handlerFailed(callback, i, str, looper);
            }

            public void onRequestSuccess(List<DeviceStatus> list) throws RemoteException {
                if (callback != null) {
                    ArrayList arrayList = new ArrayList();
                    for (DeviceStatus a2 : list) {
                        arrayList.add(a2.a());
                    }
                    PluginHostApiImpl.this.handlerSuccess(callback, arrayList, looper);
                }
            }
        };
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().updateSubDevice(strArr, r0);
            } else if (callback != null) {
                callback.onFailure(ErrorCode.INVALID.getCode(), "PluginRequest is null");
            }
        } catch (RemoteException e) {
            if (callback != null) {
                callback.onFailure(ErrorCode.INVALID.getCode(), e.toString());
            }
        }
    }

    public void callRemoteAsync(String[] strArr, int i, Object obj, final Callback<JSONObject> callback) {
        final Looper looper = getLooper();
        if (obj == null || !(obj instanceof JSONObject)) {
            handlerFailed(callback, -1, "params is not jsonobject", looper);
            return;
        }
        AnonymousClass22 r6 = new IPluginCallback.Stub() {
            public void onRequestFailed(int i, String str) throws RemoteException {
                PluginHostApiImpl.this.handlerFailed(callback, i, str, looper);
            }

            public void onRequestSuccess(String str) throws RemoteException {
                if (callback != null) {
                    JSONObject jSONObject = null;
                    if (!TextUtils.isEmpty(str)) {
                        try {
                            jSONObject = new JSONObject(str);
                        } catch (JSONException unused) {
                        }
                    }
                    PluginHostApiImpl.this.handlerSuccess(callback, jSONObject, looper);
                }
            }
        };
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().callRemoteAsync(strArr, i, obj.toString(), r6, (IPluginCallback) null);
            } else if (callback != null) {
                callback.onFailure(ErrorCode.INVALID.getCode(), "PluginRequest is null");
            }
        } catch (RemoteException e) {
            if (callback != null) {
                callback.onFailure(ErrorCode.INVALID.getCode(), e.toString());
            }
        }
    }

    public void getBluetoothFirmwareUpdateInfo(String str, Callback<BtFirmwareUpdateInfo> callback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("model", str);
            callSmartHomeApi(str, "/home/latest_version", jSONObject, callback, new Parser<BtFirmwareUpdateInfo>() {
                public BtFirmwareUpdateInfo parse(String str) throws JSONException {
                    if (TextUtils.isEmpty(str)) {
                        return null;
                    }
                    JSONObject jSONObject = new JSONObject(str);
                    BtFirmwareUpdateInfo btFirmwareUpdateInfo = new BtFirmwareUpdateInfo();
                    btFirmwareUpdateInfo.version = jSONObject.optString("version");
                    btFirmwareUpdateInfo.url = jSONObject.optString("url");
                    btFirmwareUpdateInfo.changeLog = jSONObject.optString("changeLog");
                    btFirmwareUpdateInfo.md5 = jSONObject.optString("md5");
                    return btFirmwareUpdateInfo;
                }
            });
        } catch (JSONException e) {
            if (callback != null) {
                callback.onFailure(-1, e.toString());
            }
        }
    }

    public IWXAPI createWXAPI(Context context, boolean z) {
        if (this.wxapi == null) {
            this.wxapi = WXAPIFactory.createWXAPI(SHApplication.getAppContext(), GlobalSetting.e, true);
            this.wxapi.registerApp(GlobalSetting.e);
        }
        return this.wxapi;
    }

    public void reportBluetoothRecords(String str, String str2, List<XmBluetoothRecord> list, Callback<List<Boolean>> callback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            JSONArray jSONArray = new JSONArray();
            if (!ListUtils.a(list)) {
                for (XmBluetoothRecord json : list) {
                    jSONArray.put(json.toJson());
                }
            }
            jSONObject.put("datas", jSONArray);
            callSmartHomeApi(str2, "/device/event", jSONObject, callback, new Parser<List<Boolean>>() {
                public List<Boolean> parse(String str) throws JSONException {
                    BluetoothLog.e("reportBluetoothRecords: " + str);
                    ArrayList arrayList = new ArrayList();
                    if (!TextUtils.isEmpty(str)) {
                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            if ("0".equals(jSONObject.optString("code"))) {
                                JSONArray optJSONArray = jSONObject.optJSONArray("result");
                                int length = optJSONArray != null ? optJSONArray.length() : 0;
                                for (int i = 0; i < length; i++) {
                                    arrayList.add(Boolean.valueOf(optJSONArray.optBoolean(i)));
                                }
                            }
                        } catch (Exception unused) {
                        }
                    }
                    return arrayList;
                }
            });
        } catch (Exception e) {
            if (callback != null) {
                callback.onFailure(-1, e.toString());
            }
        }
    }

    public void downloadBleFirmware(String str, Response.BleUpgradeResponse bleUpgradeResponse) {
        if (!TextUtils.isEmpty(str)) {
            BleFirmwareUpgrader.a(str, bleUpgradeResponse);
        }
    }

    public void downloadFirmware(String str, Response.FirmwareUpgradeResponse firmwareUpgradeResponse) {
        if (!TextUtils.isEmpty(str)) {
            BleFirmwareUpgrader.a(str, firmwareUpgradeResponse);
        }
    }

    public void cancelDownloadBleFirmware(String str) {
        if (!TextUtils.isEmpty(str)) {
            BleFirmwareUpgrader.a(str);
        }
    }

    public void setBleDeviceSubtitle(String str, String str2) {
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().setBleDeviceSubtitle(str, str2);
            }
        } catch (RemoteException unused) {
        }
    }

    public void deviceRename(String str, String str2) {
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().renameBluetoothDevice(str, str2);
            }
        } catch (RemoteException unused) {
        }
    }

    public void callRemoteAsync(String[] strArr, int i, Object obj, final Callback<JSONObject> callback, final Callback<JSONObject> callback2) {
        final Looper looper = getLooper();
        if (obj == null || !(obj instanceof JSONObject)) {
            handlerFailed(callback, -1, "params is not jsonobject", looper);
            return;
        }
        AnonymousClass25 r6 = new IPluginCallback.Stub() {
            public void onRequestFailed(int i, String str) throws RemoteException {
                PluginHostApiImpl.this.handlerFailed(callback, i, str, looper);
            }

            public void onRequestSuccess(String str) throws RemoteException {
                if (callback != null) {
                    JSONObject jSONObject = null;
                    if (!TextUtils.isEmpty(str)) {
                        try {
                            jSONObject = new JSONObject(str);
                        } catch (JSONException unused) {
                        }
                    }
                    PluginHostApiImpl.this.handlerSuccess(callback, jSONObject, looper);
                }
            }
        };
        AnonymousClass26 r7 = new IPluginCallback.Stub() {
            public void onRequestFailed(int i, String str) throws RemoteException {
                PluginHostApiImpl.this.handlerFailed(callback2, i, str, looper);
            }

            public void onRequestSuccess(String str) throws RemoteException {
                if (callback2 != null) {
                    JSONObject jSONObject = null;
                    if (!TextUtils.isEmpty(str)) {
                        try {
                            jSONObject = new JSONObject(str);
                        } catch (JSONException unused) {
                        }
                    }
                    PluginHostApiImpl.this.handlerSuccess(callback2, jSONObject, looper);
                }
            }
        };
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().callRemoteAsync(strArr, i, obj.toString(), r6, r7);
            } else if (callback != null) {
                callback.onFailure(ErrorCode.INVALID.getCode(), "PluginRequest is null");
            }
        } catch (RemoteException e) {
            if (callback != null) {
                callback.onFailure(ErrorCode.INVALID.getCode(), e.toString());
            }
        }
    }

    @Deprecated
    public String getGlobalSettingServer() {
        ServerBean F = CoreApi.a().F();
        if (F == null) {
            return "";
        }
        return F.f1530a;
    }

    @Deprecated
    public String getGlobalSettingServer(boolean z) {
        ServerBean F = CoreApi.a().F();
        if (F == null) {
            return "";
        }
        if (z) {
            return F.f1530a;
        }
        String a2 = ServerCompact.a(F);
        return TextUtils.isEmpty(a2) ? ServerCompact.e().f1530a : a2;
    }

    public String getCurrentServer() {
        ServerBean F = CoreApi.a().F();
        return F != null ? F.a() : "";
    }

    @SuppressLint({"CheckResult"})
    public void getServerName(Callback<String> callback) {
        if (callback != null) {
            Observable<String> observeOn = ServerApi.a().a(CoreApi.a().F()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            callback.getClass();
            observeOn.subscribe(new Consumer() {
                public final void accept(Object obj) {
                    Callback.this.onSuccess((String) obj);
                }
            }, new Consumer() {
                public final void accept(Object obj) {
                    Callback.this.onFailure(-1, ((Throwable) obj).getMessage());
                }
            }, $$Lambda$PluginHostApiImpl$zRSwWJcbBCJnMmIrm3rrvZfs2R0.INSTANCE);
        }
    }

    public boolean isInternationalServer(Context context) {
        return ServerCompact.e(context);
    }

    public boolean isChinaMainLand(Context context) {
        return ServerCompact.f(context);
    }

    public boolean isEurope(Context context) {
        return ServerCompact.g(context);
    }

    public boolean isKorea(Context context) {
        return ServerCompact.h(context);
    }

    public boolean isTW(Context context) {
        return ServerCompact.i(context);
    }

    public boolean isIndia(Context context) {
        return ServerCompact.j(context);
    }

    public boolean isAmerica(Context context) {
        return ServerCompact.l(context);
    }

    public boolean isRussia(Context context) {
        return ServerCompact.m(context);
    }

    public boolean isSingapore(Context context) {
        return ServerCompact.n(context);
    }

    public void getRechargeBalances(int i, double d, double d2, final Callback<JSONObject> callback) {
        String str = "";
        try {
            List<Address> fromLocation = new Geocoder(XmPluginHostApi.instance().context(), Locale.CHINA).getFromLocation(d, d2, 1);
            if (fromLocation != null && fromLocation.size() > 0) {
                Address address = fromLocation.get(0);
                if (TextUtils.isEmpty(str)) {
                    str = address.getLocality();
                }
                if (TextUtils.isEmpty(str)) {
                    str = address.getAdminArea();
                }
                if (TextUtils.isEmpty(str)) {
                    str = address.getSubAdminArea();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String format = String.format("/api/utility/v1/utilities/%d/balances?refs=10", new Object[]{Integer.valueOf(i)});
        NetRequest.Builder builder = new NetRequest.Builder();
        builder.a("GET");
        builder.b(format);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new com.xiaomi.smarthome.core.entity.net.KeyValuePair("cityName", str));
        builder.b((List<com.xiaomi.smarthome.core.entity.net.KeyValuePair>) arrayList);
        CoreApi.a().a(context(), builder.a(), new JsonParser<JSONObject>() {
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject.optJSONObject("data");
            }
        }, new AsyncCallback<JSONObject, com.xiaomi.smarthome.frame.Error>() {
            public void onSuccess(JSONObject jSONObject) {
                PluginHostApiImpl.this.handlerSuccess(callback, jSONObject, (Looper) null);
            }

            public void onFailure(com.xiaomi.smarthome.frame.Error error) {
                PluginHostApiImpl.this.handlerFailed(callback, error.a(), error.b(), (Looper) null);
            }
        });
    }

    public Bitmap encodeBarcode(String str, int i, int i2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Hashtable hashtable = new Hashtable();
        hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
        try {
            BitMatrix a2 = new QRCodeWriter().a(str, BarcodeFormat.QR_CODE, i, i2, hashtable);
            int[] iArr = new int[(i * i2)];
            for (int i3 = 0; i3 < i2; i3++) {
                for (int i4 = 0; i4 < i; i4++) {
                    if (a2.a(i4, i3)) {
                        iArr[(i3 * i) + i4] = -16777216;
                    } else {
                        iArr[(i3 * i) + i4] = -1;
                    }
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
            createBitmap.setPixels(iArr, 0, i, 0, 0, i, i2);
            return createBitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decodeBarcode(Bitmap bitmap) {
        if (bitmap == null || bitmap.getConfig() != Bitmap.Config.ARGB_8888) {
            return null;
        }
        QRCodeReader qRCodeReader = new QRCodeReader();
        int[] iArr = new int[(bitmap.getWidth() * bitmap.getHeight())];
        bitmap.getPixels(iArr, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        try {
            Result a2 = qRCodeReader.a(new BinaryBitmap(new HybridBinarizer(new RGBLuminanceSource(bitmap.getWidth(), bitmap.getHeight(), iArr))));
            if (a2 != null) {
                return a2.a();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateDevice(List<String> list, final Callback<List<DeviceStat>> callback) {
        final Looper looper = getLooper();
        AnonymousClass29 r1 = new IPluginCallbackDeviceList.Stub() {
            public void onRequestFailed(int i, String str) throws RemoteException {
                PluginHostApiImpl.this.handlerFailed(callback, i, str, looper);
            }

            public void onRequestSuccess(List<DeviceStatus> list) throws RemoteException {
                if (callback != null) {
                    ArrayList arrayList = new ArrayList();
                    for (DeviceStatus a2 : list) {
                        arrayList.add(a2.a());
                    }
                    PluginHostApiImpl.this.handlerSuccess(callback, arrayList, looper);
                }
            }
        };
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().updateDevice(list, r1);
            } else if (callback != null) {
                callback.onFailure(ErrorCode.INVALID.getCode(), "PluginRequest is null");
            }
        } catch (RemoteException e) {
            if (callback != null) {
                callback.onFailure(ErrorCode.INVALID.getCode(), e.toString());
            }
        }
    }

    public String getDevicePincode(String str) {
        if (PluginServiceManager.a().b() == null) {
            return "";
        }
        try {
            return PluginServiceManager.a().b().getDevicePincode(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            return "";
        }
    }

    public boolean checkAndShowVoiceCtrlAuthorizePageIfNeed(Activity activity, String str, int i) {
        try {
            if (!CoreApi.a().D() && PluginServiceManager.a().b() != null) {
                if (!PluginServiceManager.a().b().checkVoiceCtrlAuthorized(str)) {
                    showVoiceCtrlAuthorizePage(activity, str, i);
                    return true;
                } else if (PluginServiceManager.a().b().checkIfVoiceCtrlAuthorizedExpired(str)) {
                    showVoiceCtrlAuthorizePage(activity, str, i);
                    return true;
                }
            }
        } catch (RemoteException unused) {
        }
        return false;
    }

    public void showVoiceCtrlAuthorizePage(Activity activity, String str, int i) {
        Intent intent = new Intent(SHApplication.getAppContext(), DeviceAuthSlaveListActivity.class);
        intent.putExtra("device_id", str);
        if (!DeviceAuthManager.a().a(str)) {
            intent.putExtra(DeviceAuthSlaveListActivity.INTENT_KEY_SHOW_BOTTOM_BAR, false);
        }
        activity.startActivityForResult(intent, i);
    }

    public void visualSecureBind(String str) {
        if (PluginServiceManager.a().b() != null) {
            try {
                PluginServiceManager.a().b().visualSecureBind(str);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void getFirmwareUpdateInfoCommon(String str, Callback<FirmwareUpdateInfo> callback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("model", str);
            callSmartHomeApi(str, "/home/latest_version", jSONObject, callback, new Parser<FirmwareUpdateInfo>() {
                public FirmwareUpdateInfo parse(String str) throws JSONException {
                    if (TextUtils.isEmpty(str)) {
                        return null;
                    }
                    JSONObject jSONObject = new JSONObject(str);
                    FirmwareUpdateInfo firmwareUpdateInfo = new FirmwareUpdateInfo();
                    firmwareUpdateInfo.version = jSONObject.optString("version");
                    firmwareUpdateInfo.url = jSONObject.optString("url");
                    firmwareUpdateInfo.changeLog = jSONObject.optString("changeLog");
                    if (jSONObject.has("md5")) {
                        firmwareUpdateInfo.md5 = jSONObject.optString("md5");
                    }
                    return firmwareUpdateInfo;
                }
            });
        } catch (JSONException e) {
            if (callback != null) {
                callback.onFailure(-1, e.toString());
            }
        }
    }

    public void loadBitmap(String str, final Callback<Bitmap> callback) {
        if (PluginServiceManager.a().b() != null) {
            try {
                PluginServiceManager.a().b().loadBitmap(str, new IPluginCallback3.Stub() {
                    public void onSuccess(Bitmap bitmap) throws RemoteException {
                        callback.onSuccess(bitmap);
                    }

                    public void onFailed() throws RemoteException {
                        callback.onFailure(0, "");
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void getDeviceRealIconByModel(String str, final Callback<Bitmap> callback) {
        if (PluginServiceManager.a().b() != null) {
            try {
                PluginServiceManager.a().b().getDeviceRealIconByModel(str, new IPluginCallback3.Stub() {
                    public void onSuccess(Bitmap bitmap) throws RemoteException {
                        callback.onSuccess(bitmap);
                    }

                    public void onFailed() throws RemoteException {
                        callback.onFailure(0, "");
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> getRecommendTags(String str) {
        IPluginRequest b = PluginServiceManager.a().b();
        if (b == null) {
            return null;
        }
        try {
            return b.getRecommendTags(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void initCameraFrameSender(String str) {
        CameraFrameSender.instance().initCameraFrame(str);
    }

    public void sendCameraFrame(String str, byte[] bArr, long j, int i, long j2, boolean z, int i2, int i3) {
        CameraFrameSender.instance().sendCameraFrame(str, bArr, j, i, j2, z, i2, i3);
    }

    public void sendCameraFrame(String str, byte[] bArr, long j, int i, long j2, int i2, boolean z, int i3, int i4) {
        CameraFrameSender.instance().sendCameraFrame(str, bArr, j, i, j2, i2, z, i3, i4);
    }

    public void closeCameraFrameSender(String str) {
        CameraFrameSender.instance().closeCameraFrame(str);
    }

    public void setUserConfigV5(XmPluginPackage xmPluginPackage, String str, int i, Map<String, Object> map, final Callback<int[]> callback) {
        JSONObject jSONObject = new JSONObject();
        try {
            for (Map.Entry next : map.entrySet()) {
                jSONObject.put((String) next.getKey(), next.getValue());
            }
            UserApi a2 = UserApi.a();
            JSONArray jSONArray = new JSONArray();
            final int[] a3 = a2.a(jSONObject, str, i, 1000, 2048, jSONArray);
            a2.a(SHApplication.getAppContext(), jSONArray, (AsyncCallback<JSONObject, com.xiaomi.smarthome.frame.Error>) new AsyncCallback<JSONObject, com.xiaomi.smarthome.frame.Error>() {
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject.optInt("result") != 0) {
                        callback.onSuccess(a3);
                    }
                }

                public void onFailure(com.xiaomi.smarthome.frame.Error error) {
                    callback.onFailure(error.a(), error.b());
                }
            });
        } catch (JSONException e) {
            if (callback != null) {
                callback.onFailure(-1, e.toString());
            }
        }
    }

    public void getUserConfigV5(XmPluginPackage xmPluginPackage, final String str, int[] iArr, final Callback<Map<String, Object>> callback) {
        JSONArray jSONArray = new JSONArray();
        for (int put : iArr) {
            jSONArray.put(put);
        }
        UserApi.a().a(SHApplication.getAppContext(), str, jSONArray, (AsyncCallback<Map<Integer, UserConfig.UserConfigData>, com.xiaomi.smarthome.frame.Error>) new AsyncCallback<Map<Integer, UserConfig.UserConfigData>, com.xiaomi.smarthome.frame.Error>() {
            public void onSuccess(Map<Integer, UserConfig.UserConfigData> map) {
                PluginHostApiImpl.this.processConfigInfo(map, callback, str);
            }

            public void onFailure(com.xiaomi.smarthome.frame.Error error) {
                if (callback != null) {
                    callback.onFailure(error.a(), error.b());
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void processConfigInfo(Map<Integer, UserConfig.UserConfigData> map, Callback<Map<String, Object>> callback, String str) {
        ArrayList arrayList = new ArrayList();
        for (Integer next : map.keySet()) {
            try {
                JSONObject jSONObject = new JSONObject(map.get(next).c);
                if (jSONObject.has("ts") && jSONObject.has("tc")) {
                    PackConfigInfo packConfigInfo = new PackConfigInfo();
                    packConfigInfo.sourceKey = next.intValue();
                    packConfigInfo.packStart = jSONObject.getInt("ts");
                    packConfigInfo.packLength = jSONObject.getInt("tc");
                    arrayList.add(packConfigInfo);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (arrayList.size() > 0) {
            repackConfigInfo(map, arrayList, callback, str);
        } else {
            responseResult(map, callback);
        }
    }

    /* access modifiers changed from: private */
    public void responseResult(Map<Integer, UserConfig.UserConfigData> map, Callback<Map<String, Object>> callback) {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : map.entrySet()) {
            try {
                hashMap.put(String.valueOf((Integer) next.getKey()), UserConfig.UserConfigData.a((UserConfig.UserConfigData) next.getValue()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        callback.onSuccess(hashMap);
    }

    private void repackConfigInfo(final Map<Integer, UserConfig.UserConfigData> map, final List<PackConfigInfo> list, final Callback<Map<String, Object>> callback, String str) {
        JSONArray jSONArray = new JSONArray();
        for (PackConfigInfo next : list) {
            for (int i = next.packStart; i < next.packStart + next.packLength; i++) {
                jSONArray.put(i);
            }
        }
        UserApi.a().a(SHApplication.getAppContext(), str, jSONArray, (AsyncCallback<Map<Integer, UserConfig.UserConfigData>, com.xiaomi.smarthome.frame.Error>) new AsyncCallback<Map<Integer, UserConfig.UserConfigData>, com.xiaomi.smarthome.frame.Error>() {
            public void onSuccess(Map<Integer, UserConfig.UserConfigData> map) {
                for (PackConfigInfo packConfigInfo : list) {
                    int i = packConfigInfo.sourceKey;
                    StringBuilder sb = new StringBuilder();
                    for (int i2 = packConfigInfo.packStart; i2 < packConfigInfo.packStart + packConfigInfo.packLength; i2++) {
                        sb.append(map.get(Integer.valueOf(i2)).c);
                    }
                    ((UserConfig.UserConfigData) map.get(Integer.valueOf(i))).c = sb.toString();
                }
                PluginHostApiImpl.this.responseResult(map, callback);
            }

            public void onFailure(com.xiaomi.smarthome.frame.Error error) {
                if (callback != null) {
                    callback.onFailure(error.a(), error.b());
                }
            }
        });
    }

    private static class PackConfigInfo {
        int packLength;
        int packStart;
        int sourceKey;

        private PackConfigInfo() {
        }
    }

    public void openCameraFloatingWindow(String str) {
        if (PluginServiceManager.a().b() != null) {
            try {
                PluginServiceManager.a().b().openCameraFloatingWindow(str);
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.setFlags(C.ENCODING_PCM_MU_LAW);
                intent.addCategory("android.intent.category.HOME");
                SHApplication.getAppContext().startActivity(intent);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeCameraFloatingWindow(String str) {
        if (PluginServiceManager.a().b() != null) {
            try {
                PluginServiceManager.a().b().closeCameraFloatingWindow(str);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public Typeface getFont(String str) {
        return FontManager.a(str);
    }

    public JSONArray getDeviceProp(String str) {
        if (PluginServiceManager.a().b() == null) {
            return null;
        }
        try {
            String devicePropByDid = PluginServiceManager.a().b().getDevicePropByDid(str);
            if (TextUtils.isEmpty(devicePropByDid)) {
                devicePropByDid = XMPConst.ai;
            }
            return new JSONArray(devicePropByDid);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void getControllableDevices(String str, final Callback<JSONObject> callback) {
        if (str.contains("watch") && PluginServiceManager.a().b() != null) {
            try {
                PluginServiceManager.a().b().getWatchControllableDevices(str, new IPluginCallback.Stub() {
                    public void onRequestSuccess(String str) throws RemoteException {
                        if (callback != null) {
                            try {
                                callback.onSuccess(PluginHostApiImpl.this.getWatchControllableDevices(new JSONObject(str).optJSONArray("result")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    public void onRequestFailed(int i, String str) throws RemoteException {
                        if (callback != null) {
                            callback.onFailure(i, str);
                        }
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void gotoFeedback(Activity activity, String str, String str2, XmPluginPackage xmPluginPackage) {
        Intent intent = new Intent();
        intent.setClass(activity, FeedbackActivity.class);
        intent.putExtra("extra_device_model", str);
        activity.startActivity(intent);
    }

    public JSONObject getWatchControllableDevices(JSONArray jSONArray) {
        JSONArray jSONArray2 = new JSONArray();
        List<DeviceStat> deviceList = getDeviceList();
        HashMap hashMap = new HashMap();
        for (DeviceStat next : deviceList) {
            List list = (List) hashMap.get(next.model);
            if (list == null) {
                list = new LinkedList();
                hashMap.put(next.model, list);
            }
            list.add(next);
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                JSONArray optJSONArray = jSONObject.optJSONArray("adapt_models");
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    List<DeviceStat> list2 = (List) hashMap.get(optJSONArray.optString(i2));
                    if (!ListUtils.a(list2)) {
                        for (DeviceStat deviceStat : list2) {
                            JSONObject jSONObject2 = new JSONObject(jSONObject.toString());
                            jSONObject2.put("did", deviceStat.did);
                            jSONObject2.put("name", deviceStat.name);
                            jSONObject2.put("model", deviceStat.model);
                            PluginRecord d = CoreApi.a().d(deviceStat.model);
                            jSONObject2.put("icon", d != null ? d.t() : "");
                            jSONArray2.put(jSONObject2);
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject3.put("result", jSONArray2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        BluetoothLog.e(String.format("getWatchControllableDevices:\n%s", new Object[]{jSONObject3}));
        return jSONObject3;
    }

    public void gotoAuthManagerPage(Activity activity, String str) {
        if (!CoreApi.a().D()) {
            try {
                if (PluginServiceManager.a().b() != null && PluginServiceManager.a().b().checkIfSupportVoiceCtrl(str)) {
                    Intent intent = new Intent(activity, DeviceAuthSlaveListActivity.class);
                    intent.putExtra("device_id", str);
                    intent.putExtra(DeviceAuthSlaveListActivity.INTENT_KEY_SHOW_BOTTOM_BAR, false);
                    activity.startActivity(intent);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void getAppConfig(String str, String str2, String str3, final Callback<String> callback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(TSMAuthContants.PARAM_LANGUAGE, str2);
            jSONObject.put("name", str);
            jSONObject.put("version", str3);
        } catch (Exception unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        Request request = null;
        try {
            request = new Request.Builder().a("GET").b(buildUrl(jSONObject)).a();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (request != null) {
            HttpApi.a(request, (AsyncHandler) new AsyncHandler() {
                public void onSuccess(Object obj, okhttp3.Response response) {
                }

                public void processResponse(okhttp3.Response response) {
                    try {
                        JSONObject jSONObject = new JSONObject(response.body().string());
                        if (!jSONObject.isNull("result")) {
                            JSONObject optJSONObject = jSONObject.optJSONObject("result");
                            if (!optJSONObject.isNull("content")) {
                                callback.onSuccess(optJSONObject.get("content").toString());
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }

                public void processFailure(Call call, IOException iOException) {
                    callback.onFailure(-1, (String) null);
                }

                public void onFailure(com.xiaomi.smarthome.library.http.Error error, Exception exc, okhttp3.Response response) {
                    callback.onFailure(-1, (String) null);
                }
            });
        }
    }

    @NonNull
    private String buildUrl(JSONObject jSONObject) throws UnsupportedEncodingException {
        return ServerRouteUtil.a(SHApplication.getAppContext()) + "/app/service/getappconfig?data=" + URLEncoder.encode(jSONObject.toString(), "UTF-8");
    }

    public Locale getSettingLocale() {
        return CoreApi.a().I();
    }

    public boolean isModelSupport(String str) {
        return CoreApi.a().d(str) != null;
    }

    public void reverseGeo(double d, double d2, final Callback<Address> callback) {
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().reverseGeo(d, d2, new IPluginCallback2.Stub() {
                    public void onRequestSuccess(Intent intent) throws RemoteException {
                        Address address = (Address) intent.getParcelableExtra("address");
                        if (address != null) {
                            callback.onSuccess(address);
                        } else {
                            callback.onFailure(-1, "get intent with null address");
                        }
                    }

                    public void onRequestFailed(int i, String str) throws RemoteException {
                        callback.onFailure(i, str);
                    }
                });
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void logForModel(String str, String str2) {
        CoreApi.a().a(1, str, str2);
    }

    public boolean checkAndRequestPermisson(Activity activity, boolean z, Callback<List<String>> callback, String... strArr) {
        if (strArr == null || activity == null || activity.isFinishing()) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= 17 && activity.isDestroyed()) {
            return true;
        }
        boolean a2 = PermissionHelper.a(strArr);
        if (activity.getApplicationInfo().targetSdkVersion < 23 || !z || a2 || PluginServiceManager.a().b() == null) {
            if (callback != null) {
                if (a2) {
                    callback.onSuccess(Arrays.asList(strArr));
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (String append : strArr) {
                        sb.append(append);
                        sb.append(",");
                    }
                    sb.deleteCharAt(sb.lastIndexOf(","));
                    callback.onFailure(strArr.length, sb.toString());
                }
            }
            return a2;
        }
        try {
            final Looper looper = getLooper();
            final Activity activity2 = activity;
            final String[] strArr2 = strArr;
            final Callback<List<String>> callback2 = callback;
            PluginServiceManager.a().b().requestPermission(strArr, new IPluginCallback.Stub() {
                public void onRequestSuccess(String str) throws RemoteException {
                    if (activity2.isFinishing()) {
                        return;
                    }
                    if (Build.VERSION.SDK_INT >= 17 && activity2.isDestroyed()) {
                        return;
                    }
                    if (TextUtils.equals(str, "onDenied") && AndPermission.a(activity2, strArr2)) {
                        new Handler(PluginHostApiImpl.this.getCallbackLooper(looper)).post(new Runnable() {
                            public void run() {
                                List<String> a2 = Permission.a((Context) PluginHostApiImpl.this.application(), strArr2);
                                String format = String.format(PluginHostApiImpl.this.application().getText(R.string.permission_tips_denied_msg).toString(), new Object[]{TextUtils.join("\n", a2)});
                                final SettingService a3 = AndPermission.a(activity2);
                                new AlwaysDeniedPermissionDialog.Builder(activity2).b(format).a((View.OnClickListener) new View.OnClickListener() {
                                    public void onClick(View view) {
                                        a3.a();
                                    }
                                }).b((View.OnClickListener) new View.OnClickListener() {
                                    public void onClick(View view) {
                                        a3.c();
                                        PluginHostApiImpl.this.handlerFailed(callback2, -1, "Permisson Denied", looper);
                                    }
                                }).a().a();
                            }
                        });
                    } else if (TextUtils.equals(str, "onGranted") && callback2 != null) {
                        PluginHostApiImpl.this.handlerSuccess(callback2, Arrays.asList(strArr2), looper);
                    } else if (TextUtils.equals(str, "onDenied")) {
                        PluginHostApiImpl.this.handlerFailed(callback2, -1, "Permisson Denied", looper);
                    }
                }

                public void onRequestFailed(int i, String str) throws RemoteException {
                    PluginHostApiImpl.this.handlerFailed(callback2, i, str, looper);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public XmVideoViewGl createVideoView(Context context, FrameLayout frameLayout, boolean z, int i) {
        return new VideoViewGlImpl(context, frameLayout, z, i);
    }

    public XmVideoViewGl createVideoViewOnFront(Context context, FrameLayout frameLayout, boolean z, int i) {
        return new VideoViewGlImpl(context, frameLayout, z, i, true);
    }

    public XmVideoViewGl createMp4View(Context context, FrameLayout frameLayout, boolean z) {
        VideoPlayerBase videoPlayerBase;
        if (z) {
            videoPlayerBase = new VideoPlayerRender((VideoGlSurfaceView) null);
        } else {
            videoPlayerBase = new VideoPlayerFFmpeg((VideoGlSurfaceView) null);
        }
        VideoViewGlImpl videoViewGlImpl = new VideoViewGlImpl(context, frameLayout, videoPlayerBase);
        videoViewGlImpl.addMp4Player(videoPlayerBase);
        return videoViewGlImpl;
    }

    public XmMp4Record createMp4Record() {
        return new Mp4Record();
    }

    public XmCameraP2p createCameraP2p(XmP2PInfo xmP2PInfo, int i) {
        if (i == 1) {
            return TuTkClient.getCameraClient(xmP2PInfo);
        }
        return null;
    }

    public void updateP2pPwd(DeviceStat deviceStat, int i, Callback<XmP2PInfo> callback) {
        if (deviceStat == null) {
            if (callback != null) {
                callback.onFailure(-1, "deviceStat null");
            }
        } else if (i == 1) {
            DecryptUtil.updateTutkPwd(deviceStat, callback);
        } else if (callback != null) {
            callback.onFailure(-1, "not support type");
        }
    }

    public XmAAcCodec createAAcCodec(boolean z, int i, int i2, int i3) {
        if (z) {
            return new AAcCodecImp(i, i2, i3);
        }
        return new AAcCodecImp();
    }

    public void getBindKey(String str, final Callback<String> callback) {
        JSONObject jSONObject = new JSONObject();
        final Looper looper = getLooper();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new com.xiaomi.smarthome.core.entity.net.KeyValuePair("data", jSONObject.toString()));
        CoreApi.a().a((Context) null, new NetRequest.Builder().a("POST").b("/user/get_bindkey").b((List<com.xiaomi.smarthome.core.entity.net.KeyValuePair>) arrayList).a(), new JsonParser<String>() {
            public String parse(JSONObject jSONObject) throws JSONException {
                return jSONObject.optString("bindkey");
            }
        }, Crypto.RC4, new AsyncCallback<String, com.xiaomi.smarthome.frame.Error>() {
            public void onSuccess(String str) {
                PluginHostApiImpl.this.handlerSuccess(callback, str, looper);
            }

            public void onFailure(com.xiaomi.smarthome.frame.Error error) {
                if (error != null) {
                    PluginHostApiImpl.this.handlerFailed(callback, error.a(), error.b(), looper);
                } else {
                    PluginHostApiImpl.this.handlerFailed(callback, -1, "", looper);
                }
            }
        });
    }

    public void getCloudVideoFile(Context context, String str, ICloudDataCallback iCloudDataCallback) {
        if (context != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String optString = jSONObject.optString("did");
                String optString2 = jSONObject.optString("fileId");
                String optString3 = jSONObject.optString("stoId");
                String optString4 = jSONObject.optString(TbsReaderView.KEY_FILE_PATH);
                if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2) && !TextUtils.isEmpty(optString3)) {
                    CloudVideoNetUtils.getInstance().getCloudFile(context, optString, optString2, optString3, optString4, iCloudDataCallback);
                } else if (iCloudDataCallback != null) {
                    iCloudDataCallback.onCloudDataFailed(-97, "params error");
                }
            } catch (JSONException unused) {
                if (iCloudDataCallback != null) {
                    iCloudDataCallback.onCloudDataFailed(-98, "JSONException");
                }
            }
        } else if (iCloudDataCallback != null) {
            iCloudDataCallback.onCloudDataFailed(-99, "context null");
        }
    }

    public void getCloudVideoFile(String str, String str2, String str3, ICloudDataCallback iCloudDataCallback) {
        new CloudVideoM3U8DownloadManager().DownloadM3U8(str, str2, str3, iCloudDataCallback);
    }

    public String getCloudImageUrl(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return null;
        }
        return CloudVideoNetUtils.getInstance().getSnapshotUrl(str, str2, str3);
    }

    public byte[] sendImageDownloadRequest(Context context, String str) {
        HttpURLConnection httpURLConnection;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            if (!str.startsWith("https")) {
                return null;
            }
            URL url = new URL(str);
            int i = 0;
            if (Network.c(context)) {
                HttpURLConnection.setFollowRedirects(false);
                String a2 = Network.a(url);
                String host = url.getHost();
                httpURLConnection = (HttpURLConnection) TraceNetTrafficMonitor.b().b(new URL(a2));
                httpURLConnection.setRequestProperty("X-Online-Host", host);
                int responseCode = httpURLConnection.getResponseCode();
                while (true) {
                    if (responseCode < 300 || responseCode >= 400) {
                        break;
                    }
                    String headerField = httpURLConnection.getHeaderField("location");
                    if (TextUtils.isEmpty(headerField)) {
                        break;
                    }
                    URL url2 = new URL(headerField);
                    String a3 = Network.a(url2);
                    String host2 = url2.getHost();
                    HttpURLConnection httpURLConnection2 = (HttpURLConnection) TraceNetTrafficMonitor.b().b(new URL(a3));
                    httpURLConnection2.setRequestProperty("X-Online-Host", host2);
                    HttpURLConnection httpURLConnection3 = httpURLConnection2;
                    responseCode = httpURLConnection2.getResponseCode();
                    httpURLConnection = httpURLConnection3;
                }
            } else {
                httpURLConnection = (HttpURLConnection) TraceNetTrafficMonitor.b().b(url);
                HttpURLConnection.setFollowRedirects(true);
            }
            httpURLConnection.setRequestProperty("Cookie", "yetAnotherServiceToken=" + CloudVideoNetUtils.getInstance().getTokenInfo().c);
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            int contentLength = httpURLConnection.getContentLength();
            byte[] bArr = new byte[contentLength];
            while (i < contentLength) {
                try {
                    int read = inputStream.read(bArr, i, contentLength - i);
                    if (read != -1) {
                        i += read;
                    }
                } catch (Exception unused) {
                    return null;
                }
            }
            return CloudVideoCryptoUtils.getInstance().decrypt(bArr);
        } catch (IOException unused2) {
            return null;
        }
    }

    static final URLConnection openConnection_aroundBody0(PluginHostApiImpl pluginHostApiImpl, URL url) {
        return url.openConnection();
    }

    static final URLConnection openConnection_aroundBody2(PluginHostApiImpl pluginHostApiImpl, URL url) {
        return url.openConnection();
    }

    static final URLConnection openConnection_aroundBody4(PluginHostApiImpl pluginHostApiImpl, URL url) {
        return url.openConnection();
    }

    public void createDeviceGroup(Context context, String str) {
        Intent intent = new Intent(context, ModifyGroupActivity.class);
        intent.putExtra("group_model", str);
        intent.putExtra("from", ModifyGroupActivity.PLUGIN_GROUP_DEVICE);
        context.startActivity(intent);
    }

    public boolean isUsrExpPlanEnabled(String str) {
        try {
            return PluginServiceManager.a().b().isUsrExpPlanEnabled(str);
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public void setUsrExpPlanEnabled(String str, boolean z) {
        try {
            PluginServiceManager.a().b().setUsrExpPlanEnabled(str, z);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<DeviceStat> getFilterBluetoothDeviceList(String str) {
        if (PluginServiceManager.a().b() == null) {
            return null;
        }
        try {
            List<DeviceStatus> filterBluetoothDeviceList = PluginServiceManager.a().b().getFilterBluetoothDeviceList(str);
            if (filterBluetoothDeviceList == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (DeviceStatus a2 : filterBluetoothDeviceList) {
                arrayList.add(a2.a());
            }
            return arrayList;
        } catch (RemoteException unused) {
            return null;
        }
    }

    public ProductInfo getProductInfo(String str) {
        PluginRecord d = CoreApi.a().d(str);
        if (d == null) {
            return null;
        }
        ProductInfo productInfo = new ProductInfo();
        productInfo.model = d.o();
        productInfo.name = d.p();
        productInfo.iconReal = d.t();
        if (d.c() == null) {
            return productInfo;
        }
        productInfo.productId = d.c().d();
        productInfo.desc = d.c().s();
        return productInfo;
    }

    public MJExoPlayer createExoPlayer(Context context, ViewGroup viewGroup, AttributeSet attributeSet, int i) {
        return new MJExoPlayerImpl(context, viewGroup, attributeSet, i);
    }

    public String generateRequestUrl(String str, JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject == null || jSONObject2 == null) {
            return null;
        }
        return CloudVideoNetUtils.getInstance().generateRequestUrl(str, jSONObject, jSONObject2);
    }

    public HLSDownloader getHLSDownloader(String str) {
        return new HLSDownloaderImpl();
    }

    public List<DeviceStat> getBleGatewayDeviceList() {
        if (PluginServiceManager.a().b() == null) {
            return null;
        }
        try {
            List<DeviceStatus> bleGatewayDeviceList = PluginServiceManager.a().b().getBleGatewayDeviceList();
            if (bleGatewayDeviceList == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (DeviceStatus a2 : bleGatewayDeviceList) {
                arrayList.add(a2.a());
            }
            return arrayList;
        } catch (RemoteException unused) {
            return null;
        }
    }

    public DeviceController getSpecDeviceController(String str) {
        try {
            String specInstanceStr = PluginServiceManager.a().b().getSpecInstanceStr(str);
            if (TextUtils.isEmpty(specInstanceStr)) {
                return null;
            }
            return SpecDeviceCodec.a(new JSONObject(specInstanceStr)).createController(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void getBleMeshFirmwareUpdateInfo(String str, String str2, Callback<BleMeshFirmwareUpdateInfo> callback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str2);
            callSmartHomeApi(str, "/v2/device/latest_ver", jSONObject, callback, new Parser<BleMeshFirmwareUpdateInfo>() {
                public BleMeshFirmwareUpdateInfo parse(String str) throws JSONException {
                    if (TextUtils.isEmpty(str)) {
                        return null;
                    }
                    JSONObject jSONObject = new JSONObject(str);
                    BleMeshFirmwareUpdateInfo bleMeshFirmwareUpdateInfo = new BleMeshFirmwareUpdateInfo();
                    bleMeshFirmwareUpdateInfo.version = jSONObject.optString("version");
                    bleMeshFirmwareUpdateInfo.safeUrl = jSONObject.optString("safe_url");
                    bleMeshFirmwareUpdateInfo.url = jSONObject.optString("url");
                    bleMeshFirmwareUpdateInfo.changeLog = jSONObject.optString("changeLog");
                    bleMeshFirmwareUpdateInfo.md5 = jSONObject.optString("md5");
                    return bleMeshFirmwareUpdateInfo;
                }
            });
        } catch (JSONException e) {
            if (callback != null) {
                callback.onFailure(-1, e.toString());
            }
        }
    }

    public String getVirtualGroupStatus(String str) {
        if (PluginServiceManager.a().b() == null) {
            return "3";
        }
        try {
            return PluginServiceManager.a().b().getVirtualGroupStatus(str);
        } catch (Exception e) {
            e.printStackTrace();
            return "3";
        }
    }

    public void getPropertyValues(Context context, List<PropertyParam> list, final Callback<List<PropertyParam>> callback) {
        if (callback != null) {
            if (list == null) {
                callback.onFailure(-9999, (String) null);
            } else {
                DeviceCardApi.b(context, list, new AsyncCallback<List<PropertyParam>, com.xiaomi.smarthome.frame.Error>() {
                    public void onSuccess(List<PropertyParam> list) {
                        if (list == null || list.size() == 0) {
                            callback.onFailure(-9, (String) null);
                        } else {
                            callback.onSuccess(list);
                        }
                    }

                    public void onFailure(com.xiaomi.smarthome.frame.Error error) {
                        callback.onFailure(error.a(), (String) null);
                    }
                });
            }
        }
    }

    public void setPropertyValue(Context context, PropertyParam propertyParam, final Callback<PropertyParam> callback) {
        if (callback != null) {
            if (propertyParam == null) {
                callback.onFailure(-9999, (String) null);
            } else {
                DeviceCardApi.SpecPropertyApi.instance.setDeviceSpecProp(context, propertyParam, new AsyncCallback<PropertyParam, com.xiaomi.smarthome.frame.Error>() {
                    public void onSuccess(PropertyParam propertyParam) {
                        if (propertyParam == null) {
                            callback.onFailure(-9, (String) null);
                        } else {
                            callback.onSuccess(propertyParam);
                        }
                    }

                    public void onFailure(com.xiaomi.smarthome.frame.Error error) {
                        callback.onFailure(error.a(), (String) null);
                    }
                });
            }
        }
    }

    public void doAction(Context context, ActionParam actionParam, final Callback<ActionParam> callback) {
        if (callback != null) {
            if (actionParam == null) {
                callback.onFailure(-9999, (String) null);
            } else {
                DeviceCardApi.SpecActionApi.instance.setDeviceSpecAction(context, actionParam, new AsyncCallback<ActionParam, com.xiaomi.smarthome.frame.Error>() {
                    public void onSuccess(ActionParam actionParam) {
                        if (actionParam == null) {
                            callback.onFailure(-9, (String) null);
                        } else {
                            callback.onSuccess(actionParam);
                        }
                    }

                    public void onFailure(com.xiaomi.smarthome.frame.Error error) {
                        callback.onFailure(error.a(), (String) null);
                    }
                });
            }
        }
    }

    public int videoConverter(String str, String str2) {
        LogUtil.a("videoConverter", "model:" + str + " cmdLine:" + str2);
        if (!TextUtils.isEmpty(str2)) {
            return new FFmpegJni().runCmd(str2);
        }
        return -1;
    }

    public void setWxPush(Activity activity, String str, String str2, boolean z, int i, Callback<Boolean> callback) {
        WxPushHelper.a().a(activity, str, str2, z, i, callback);
    }

    public void getWxPushSwitchState(String str, String str2, Callback<Boolean> callback) {
        WxPushHelper.a().b(str, str2, callback);
    }

    public void getAreaPropInfo(String str, String str2, String str3, String str4, @Nullable String str5, Callback<String> callback) {
        if (!TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str3) || !TextUtils.isEmpty(str4)) {
            JSONObject jSONObject = new JSONObject();
            try {
                if (!TextUtils.isEmpty(str2)) {
                    jSONObject.put("area_id", str2);
                }
                if (!TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4)) {
                    jSONObject.put("latitude", str4);
                    jSONObject.put("longitude", str3);
                }
                if (!TextUtils.isEmpty(str5)) {
                    jSONObject.put("cityId", str5);
                }
            } catch (JSONException unused) {
            }
            callSmartHomeApi(str, "/location/area_prop_info", jSONObject, callback, new Parser<String>() {
                public String parse(String str) throws JSONException {
                    return str;
                }
            });
            return;
        }
        throw new IllegalArgumentException("areaId or latitude-longitude must be passed in !");
    }

    public void getFile(String str, String str2, String str3, ICloudDataCallback iCloudDataCallback) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            LogUtil.a(TAG, "getFile:" + str + " requestUrl:" + str2 + " localFilePath:" + str3);
            new FileDownloadTask(iCloudDataCallback).execute(new String[]{str2, str3});
        } else if (iCloudDataCallback != null) {
            iCloudDataCallback.onCloudDataFailed(-9, "param(s) invalid");
        }
    }

    public void getBluetoothFirmwareUpdateInfoV2(String str, String str2, int i, Callback<BtFirmwareUpdateInfoV2> callback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            jSONObject.put("model", str2);
            jSONObject.put("plugin_level", "" + i);
            jSONObject.put("platform", "android");
            Context context = context();
            if (context != null) {
                try {
                    PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                    jSONObject.put("app_level", "" + packageInfo.versionCode);
                } catch (PackageManager.NameNotFoundException unused) {
                }
            }
            callSmartHomeApi(str2, "/v2/device/latest_ver", jSONObject, callback, new Parser<BtFirmwareUpdateInfoV2>() {
                public BtFirmwareUpdateInfoV2 parse(String str) throws JSONException {
                    if (TextUtils.isEmpty(str)) {
                        return null;
                    }
                    JSONObject jSONObject = new JSONObject(str);
                    BtFirmwareUpdateInfoV2 btFirmwareUpdateInfoV2 = new BtFirmwareUpdateInfoV2();
                    btFirmwareUpdateInfoV2.version = jSONObject.optString("version");
                    btFirmwareUpdateInfoV2.url = jSONObject.optString("url");
                    btFirmwareUpdateInfoV2.safeUrl = jSONObject.optString("safe_url");
                    btFirmwareUpdateInfoV2.changeLog = jSONObject.optString("changeLog");
                    btFirmwareUpdateInfoV2.md5 = jSONObject.optString("md5");
                    btFirmwareUpdateInfoV2.uploadTime = jSONObject.optLong("upload_time");
                    return btFirmwareUpdateInfoV2;
                }
            });
        } catch (JSONException e) {
            if (callback != null) {
                callback.onFailure(-1, e.toString());
            }
        }
    }

    public void getBleGatewaySubDevices(List<String> list, final Callback<List<DeviceStat>> callback) {
        IPluginRequest b = PluginServiceManager.a().b();
        if (b != null) {
            try {
                b.getBleGatewaySubDevices(list, new IPluginCallbackDeviceList.Stub() {
                    public void onRequestSuccess(List<DeviceStatus> list) throws RemoteException {
                        ArrayList arrayList = new ArrayList();
                        if (list != null && list.size() > 0) {
                            int size = list.size();
                            for (int i = 0; i < size; i++) {
                                arrayList.add(list.get(i).a());
                            }
                        }
                        callback.onSuccess(arrayList);
                    }

                    public void onRequestFailed(int i, String str) throws RemoteException {
                        callback.onFailure(i, str);
                    }
                });
            } catch (RemoteException e) {
                callback.onFailure(-1, e.toString());
            }
        }
    }

    public IXmStreamClient createStreamClient(String str, String str2, DeviceStat deviceStat) {
        return XmStreamClient.getInstance(deviceStat);
    }

    public final void initBandManager(String str, String str2, final Callback<Boolean> callback) {
        if (PluginServiceManager.a().b() != null) {
            try {
                PluginServiceManager.a().b().initBandManager(str, str2, new IPluginCallback.Stub() {
                    public void onRequestSuccess(String str) throws RemoteException {
                        callback.onSuccess(true);
                    }

                    public void onRequestFailed(int i, String str) throws RemoteException {
                        callback.onFailure(i, str);
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void deInitBandManager() {
        if (PluginServiceManager.a().b() != null) {
            try {
                PluginServiceManager.a().b().deInitBandManager();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void connectBand(String str, final Callback<Integer> callback) {
        if (PluginServiceManager.a().b() != null) {
            try {
                PluginServiceManager.a().b().connectBand(str, new IPluginCallback.Stub() {
                    public void onRequestSuccess(String str) throws RemoteException {
                        callback.onSuccess(Integer.valueOf(Integer.parseInt(str)));
                    }

                    public void onRequestFailed(int i, String str) throws RemoteException {
                        callback.onFailure(i, str);
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void getAllCards(final Callback<String> callback) {
        if (PluginServiceManager.a().b() != null) {
            try {
                PluginServiceManager.a().b().getAllCards(new IPluginCallback.Stub() {
                    public void onRequestSuccess(String str) throws RemoteException {
                        callback.onSuccess(str);
                    }

                    public void onRequestFailed(int i, String str) throws RemoteException {
                        callback.onFailure(i, str);
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void issueDoorCard(final Callback<Boolean> callback) {
        if (PluginServiceManager.a().b() != null) {
            try {
                PluginServiceManager.a().b().issueDoorCard(new IPluginCallback.Stub() {
                    public void onRequestSuccess(String str) throws RemoteException {
                        callback.onSuccess(true);
                    }

                    public void onRequestFailed(int i, String str) throws RemoteException {
                        callback.onFailure(i, str);
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteCard(String str, final Callback<Boolean> callback) {
        if (PluginServiceManager.a().b() != null) {
            try {
                PluginServiceManager.a().b().deleteCard(str, new IPluginCallback.Stub() {
                    public void onRequestSuccess(String str) throws RemoteException {
                        callback.onSuccess(true);
                    }

                    public void onRequestFailed(int i, String str) throws RemoteException {
                        callback.onFailure(i, str);
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void setDefaultCard(String str, final Callback<Boolean> callback) {
        if (PluginServiceManager.a().b() != null) {
            try {
                PluginServiceManager.a().b().setDefaultCard(str, new IPluginCallback.Stub() {
                    public void onRequestSuccess(String str) throws RemoteException {
                        callback.onSuccess(true);
                    }

                    public void onRequestFailed(int i, String str) throws RemoteException {
                        callback.onFailure(i, str);
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateCard(String str, final Callback<Boolean> callback) {
        if (PluginServiceManager.a().b() != null) {
            try {
                PluginServiceManager.a().b().updateCard(str, new IPluginCallback.Stub() {
                    public void onRequestSuccess(String str) throws RemoteException {
                        callback.onSuccess(true);
                    }

                    public void onRequestFailed(int i, String str) throws RemoteException {
                        callback.onFailure(i, str);
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void getDefaultCardAndActivateInfo(final Callback<String> callback) {
        if (PluginServiceManager.a().b() != null) {
            try {
                PluginServiceManager.a().b().getDefaultCardAndActivateInfo(new IPluginCallback.Stub() {
                    public void onRequestSuccess(String str) throws RemoteException {
                        callback.onSuccess(str);
                    }

                    public void onRequestFailed(int i, String str) throws RemoteException {
                        callback.onFailure(i, str);
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void getRecommendScenes(String str, String str2, Callback<JSONObject> callback) {
        if (!TextUtils.isEmpty(this.did)) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("did", str2);
                if (str != null) {
                    jSONObject.put("model", str);
                }
                jSONObject.put("api_version", 4);
            } catch (JSONException e) {
                if (callback != null) {
                    callback.onFailure(-1, e.toString());
                    return;
                }
            }
            callSmartHomeApi(str, "/v2/scene/get_rec_in_plugin", jSONObject, callback, new Parser(str2) {
                private final /* synthetic */ String f$0;

                {
                    this.f$0 = r1;
                }

                public final Object parse(String str) {
                    return PluginHostApiImpl.lambda$getRecommendScenes$3(this.f$0, str);
                }
            });
        } else if (callback != null) {
            callback.onFailure(-1, "deviceId is illegal");
        }
    }

    static /* synthetic */ JSONObject lambda$getRecommendScenes$3(String str, String str2) throws JSONException {
        Context appContext = SHApplication.getAppContext();
        SharePrefsManager.a(appContext, "scene_list_cache", PluginRecommendSceneManager.c + str, str2);
        if (!TextUtils.isEmpty(str2)) {
            return new JSONObject(str2);
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0063  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void editSceneV2(java.lang.String r15, int r16, java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, org.json.JSONObject r21, org.json.JSONArray r22, com.xiaomi.smarthome.device.api.Callback<org.json.JSONObject> r23) {
        /*
            r14 = this;
            r5 = r16
            r10 = r23
            boolean r0 = android.text.TextUtils.isEmpty(r17)
            r1 = -1
            if (r0 == 0) goto L_0x0014
            if (r10 == 0) goto L_0x0013
            java.lang.String r0 = "us_id is illegal"
            r10.onFailure(r1, r0)
        L_0x0013:
            return
        L_0x0014:
            org.json.JSONObject r2 = new org.json.JSONObject
            r2.<init>()
            java.lang.String r0 = "us_id"
            r6 = r17
            r2.put(r0, r6)     // Catch:{ JSONException -> 0x003a }
            java.lang.String r0 = "st_id"
            r2.put(r0, r5)     // Catch:{ JSONException -> 0x003a }
            java.lang.String r0 = "name"
            r8 = r20
            r2.put(r0, r8)     // Catch:{ JSONException -> 0x0038 }
            java.lang.String r0 = "setting"
            r7 = r21
            r2.put(r0, r7)     // Catch:{ JSONException -> 0x0036 }
            goto L_0x0046
        L_0x0036:
            r0 = move-exception
            goto L_0x0043
        L_0x0038:
            r0 = move-exception
            goto L_0x0041
        L_0x003a:
            r0 = move-exception
            goto L_0x003f
        L_0x003c:
            r0 = move-exception
            r6 = r17
        L_0x003f:
            r8 = r20
        L_0x0041:
            r7 = r21
        L_0x0043:
            r0.printStackTrace()
        L_0x0046:
            r3 = 0
            r0 = 15
            if (r5 != r0) goto L_0x004d
            r0 = 1
            goto L_0x004e
        L_0x004d:
            r0 = 0
        L_0x004e:
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r0 = com.xiaomi.smarthome.scene.api.SceneApi.SmartHomeScene.a((org.json.JSONObject) r2, (boolean) r0)     // Catch:{ JSONException -> 0x0053 }
            goto L_0x0059
        L_0x0053:
            r0 = move-exception
            r2 = r0
            r2.printStackTrace()
            r0 = r3
        L_0x0059:
            if (r0 != 0) goto L_0x0063
            if (r10 == 0) goto L_0x0062
            java.lang.String r0 = "scene is null ,jsonexception?"
            r10.onFailure(r1, r0)
        L_0x0062:
            return
        L_0x0063:
            r11 = r14
            java.lang.String r1 = r14.isFromOneDevice(r0)
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x0090
            com.xiaomi.smarthome.scenenew.lumiscene.SceneExtraBuilder r12 = com.xiaomi.smarthome.scenenew.lumiscene.SceneExtraBuilder.a()
            com.xiaomi.smarthome.device.api.SceneInfo r0 = com.xiaomi.router.api.SceneManager.f((com.xiaomi.smarthome.scene.api.SceneApi.SmartHomeScene) r0)
            com.xiaomi.smarthome.framework.plugin.mpk.PluginHostApiImpl$57 r13 = new com.xiaomi.smarthome.framework.plugin.mpk.PluginHostApiImpl$57
            r1 = r13
            r2 = r14
            r3 = r21
            r4 = r15
            r5 = r16
            r6 = r17
            r7 = r19
            r8 = r20
            r9 = r22
            r10 = r23
            r1.<init>(r3, r4, r5, r6, r7, r8, r9, r10)
            r12.a((com.xiaomi.smarthome.device.api.SceneInfo) r0, (com.xiaomi.smarthome.device.api.Callback<com.xiaomi.smarthome.device.api.SceneInfo>) r13)
            goto L_0x00a3
        L_0x0090:
            r1 = r14
            r2 = r15
            r3 = r16
            r4 = r17
            r5 = r19
            r6 = r20
            r7 = r21
            r8 = r22
            r9 = r23
            r1.startSaveScene(r2, r3, r4, r5, r6, r7, r8, r9)
        L_0x00a3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.mpk.PluginHostApiImpl.editSceneV2(java.lang.String, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, org.json.JSONObject, org.json.JSONArray, com.xiaomi.smarthome.device.api.Callback):void");
    }

    private String isFromOneDevice(SceneApi.SmartHomeScene smartHomeScene) {
        DeviceStatus deviceStatus;
        DeviceStatus deviceStatus2;
        DeviceStatus deviceStatus3;
        DeviceStatus deviceStatus4;
        boolean z = false;
        String str = null;
        for (SceneApi.Condition next : smartHomeScene.l) {
            if (!(next.c == null || next.c.d == null)) {
                try {
                    deviceStatus3 = PluginServiceManager.a().b().getDevice(next.c.f21523a);
                } catch (RemoteException e) {
                    e.printStackTrace();
                    deviceStatus3 = null;
                }
                if (deviceStatus3 != null) {
                    String str2 = TextUtils.isEmpty(deviceStatus3.j) ? deviceStatus3.d : deviceStatus3.j;
                    try {
                        deviceStatus4 = PluginServiceManager.a().b().getDevice(str2);
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                        deviceStatus4 = null;
                    }
                    if (deviceStatus4 != null && SmartHomeSceneUtility.a(deviceStatus4.g)) {
                        str = str2;
                        z = true;
                    }
                }
            }
        }
        if (!z) {
            for (SceneApi.Action next2 : smartHomeScene.k) {
                if (!(next2.e == null || next2.g.e == null)) {
                    try {
                        deviceStatus = PluginServiceManager.a().b().getDevice(next2.g.e);
                    } catch (RemoteException e3) {
                        e3.printStackTrace();
                        deviceStatus = null;
                    }
                    if (deviceStatus != null) {
                        String str3 = TextUtils.isEmpty(deviceStatus.j) ? deviceStatus.d : deviceStatus.j;
                        try {
                            deviceStatus2 = PluginServiceManager.a().b().getDevice(str3);
                        } catch (RemoteException e4) {
                            e4.printStackTrace();
                            deviceStatus2 = null;
                        }
                        if (deviceStatus2 != null && SmartHomeSceneUtility.a(deviceStatus2.g)) {
                            str = str3;
                            z = true;
                        }
                    }
                }
            }
        }
        if (z) {
            return str;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void startSaveScene(final String str, final int i, String str2, String str3, String str4, JSONObject jSONObject, JSONArray jSONArray, final Callback<JSONObject> callback) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("us_id", str2);
            if (!TextUtils.isEmpty(str3)) {
                jSONObject2.put("identify", str3);
            }
            if (str4 != null) {
                jSONObject2.put("name", str4);
            }
            jSONObject2.put("st_id", i);
            jSONObject2.put(a.j, jSONObject);
            jSONObject2.put("authed", jSONArray);
        } catch (JSONException e) {
            if (callback != null) {
                callback.onFailure(-1, e.toString());
                return;
            }
        }
        callSmartHomeApi(str, "/scene/editv2", jSONObject2, new Callback<JSONObject>() {
            public void onSuccess(JSONObject jSONObject) {
                final String optString = jSONObject.optString("us_id");
                boolean optBoolean = jSONObject.optBoolean("local");
                String optString2 = jSONObject.optString("local_dev");
                if (!TextUtils.isEmpty(optString2) && optBoolean) {
                    LocalSceneBuilder.a().a(PluginHostApiImpl.this.getDeviceByDid(optString2), jSONObject.optJSONObject("data").toString(), (MessageCallback) new MessageCallback() {
                        public void onSuccess(Intent intent) {
                            JSONObject jSONObject = new JSONObject();
                            try {
                                jSONObject.put("us_id", optString);
                                jSONObject.put("type", 1);
                                jSONObject.put("status", 0);
                                jSONObject.put("st_id", i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            PluginHostApiImpl.this.callSmartHomeApi(str, "/scene/setuserscene", jSONObject, callback, $$Lambda$PluginHostApiImpl$58$1$rU8YTPPy_6i6qtmaxfflECADPAQ.INSTANCE);
                        }

                        static /* synthetic */ JSONObject lambda$onSuccess$0(String str) throws JSONException {
                            return new JSONObject(str);
                        }

                        public void onFailure(int i, String str) {
                            if (callback != null) {
                                callback.onFailure(i, str);
                            }
                        }
                    });
                } else if (callback != null) {
                    callback.onSuccess(jSONObject);
                }
            }

            public void onFailure(int i, String str) {
                if (i == -23) {
                    if (callback != null) {
                        callback.onFailure(-23, "此智能与已有智能会形成死循环，无法创建");
                    }
                } else if (i == -22) {
                    if (callback != null) {
                        callback.onFailure(-23, "智能个数已达到最大限制，无法添加");
                    }
                } else if (i == -1) {
                    if (callback != null) {
                        callback.onFailure(-1, "操作失败，包含已删除设备");
                    }
                } else if (i == -38) {
                    if (callback != null) {
                        callback.onFailure(-1, "暂不支持手动/自动条件的转换");
                    }
                } else if (callback != null) {
                    callback.onFailure(i, "创建失败！");
                }
            }
        }, $$Lambda$PluginHostApiImpl$zzd8frPlVmbQG4XfjtODJtLXag.INSTANCE);
    }

    static /* synthetic */ JSONObject lambda$startSaveScene$4(String str) throws JSONException {
        return new JSONObject(str);
    }

    public void uploadImageFile(String str, String str2, String str3, String str4, JSONObject jSONObject, List<String> list, final ICloudDataCallback<JSONObject> iCloudDataCallback) {
        byte[] bArr;
        Exception e;
        if (!TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str4) && jSONObject != null && list != null) {
            byte[] encode = Base64.encode(CameraCryptoUtils.a().f11570a.getIV(), 2);
            final ArrayList<String> arrayList = new ArrayList<>();
            byte[] bArr2 = null;
            for (String next : list) {
                try {
                    File file = new File(next);
                    if (file.exists()) {
                        if (file.isFile()) {
                            byte[] d = FileUtil.d(next);
                            if (d != null) {
                                byte[] a2 = CameraCryptoUtils.a().a(d);
                                bArr = Base64.encode(CameraCryptoUtils.a().c(a2), 2);
                                try {
                                    String substring = next.substring(0, next.lastIndexOf("/"));
                                    String substring2 = next.substring(next.lastIndexOf("/"), next.lastIndexOf("."));
                                    String substring3 = next.substring(next.lastIndexOf("."));
                                    FileUtil.a(a2, substring, substring2 + "_enc" + substring3);
                                    arrayList.add("" + substring + substring2 + "_enc" + substring3);
                                } catch (Exception e2) {
                                    e = e2;
                                    LogUtil.b(TAG, "uploadImageFile 1:" + e.getLocalizedMessage());
                                    bArr2 = bArr;
                                }
                                bArr2 = bArr;
                            }
                        }
                    }
                    if (iCloudDataCallback != null) {
                        iCloudDataCallback.onCloudDataFailed(-445, "file not exist");
                        return;
                    }
                    return;
                } catch (Exception e3) {
                    bArr = bArr2;
                    e = e3;
                }
            }
            try {
                jSONObject.put("iv", new String(encode, Charset.forName("UTF-8")));
                if (bArr2 != null) {
                    jSONObject.put("imgSign", new String(bArr2, Charset.forName("UTF-8")));
                }
            } catch (Exception e4) {
                LogUtil.b(TAG, "uploadImageFile 2:" + e4.getLocalizedMessage());
            }
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new com.xiaomi.smarthome.core.entity.net.KeyValuePair("data", jSONObject.toString()));
            ArrayList arrayList3 = new ArrayList();
            for (String str5 : arrayList) {
                arrayList3.add(new com.xiaomi.smarthome.core.entity.net.KeyValuePair(str5, str5));
            }
            CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").c(str3).b(str4).b((List<com.xiaomi.smarthome.core.entity.net.KeyValuePair>) arrayList2).c((List<com.xiaomi.smarthome.core.entity.net.KeyValuePair>) arrayList3).a(), new JsonParser<JSONObject>() {
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, new AsyncCallback<JSONObject, com.xiaomi.smarthome.frame.Error>() {
                public void onSuccess(JSONObject jSONObject) {
                    for (String d : arrayList) {
                        try {
                            FileUtils.d(d);
                        } catch (Exception e) {
                            LogUtil.b(PluginHostApiImpl.TAG, "deleteFile:" + e.getLocalizedMessage());
                        }
                    }
                    if (iCloudDataCallback != null) {
                        iCloudDataCallback.onCloudDataSuccess(jSONObject, (Object) null);
                    }
                }

                public void onFailure(com.xiaomi.smarthome.frame.Error error) {
                    for (String d : arrayList) {
                        try {
                            FileUtils.d(d);
                        } catch (Exception e) {
                            LogUtil.b(PluginHostApiImpl.TAG, "deleteFile:" + e.getLocalizedMessage());
                        }
                    }
                    if (iCloudDataCallback == null) {
                        return;
                    }
                    if (error != null) {
                        iCloudDataCallback.onCloudDataFailed(error.a(), error.b());
                    } else {
                        iCloudDataCallback.onCloudDataFailed(-446, "uploadImageFile failed");
                    }
                }
            });
        } else if (iCloudDataCallback != null) {
            iCloudDataCallback.onCloudDataFailed(-444, "param(s) invalid");
        }
    }

    public int getUsePreviewConfig() {
        IPluginRequest b = PluginServiceManager.a().b();
        if (b != null) {
            try {
                return b.getUsePreviewConfig();
            } catch (RemoteException e) {
                LogUtil.b(TAG, "getUsePreviewConfig:" + e.getLocalizedMessage());
            }
        }
        return 0;
    }

    public void refreshDeviceListUi() {
        IPluginRequest b = PluginServiceManager.a().b();
        if (b != null) {
            try {
                b.refreshDeviceListUi();
            } catch (RemoteException e) {
                LogUtil.b(TAG, "refreshDeviceListUi:" + e.getLocalizedMessage());
            }
        }
    }

    public DeviceTagInterface getDeviceTagManager() {
        return new DeviceTagManager();
    }

    public void login(Context context, int i) {
        LoginApi.a().a(CommonApplication.getAppContext(), i, (LoginApi.LoginCallback) null);
    }
}
