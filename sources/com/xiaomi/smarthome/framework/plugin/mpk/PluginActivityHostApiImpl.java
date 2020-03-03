package com.xiaomi.smarthome.framework.plugin.mpk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.taobao.weex.common.Constants;
import com.xiaomi.infrared.activity.IRMatchingDeviceTypeActivity;
import com.xiaomi.qrcode.ScanBarcodeActivity;
import com.xiaomi.router.miio.miioplugin.IPluginCallback;
import com.xiaomi.router.miio.miioplugin.IPluginCallback2;
import com.xiaomi.router.miio.miioplugin.IPluginCallback3;
import com.xiaomi.router.miio.miioplugin.IPluginRequest;
import com.xiaomi.router.miio.miioplugin.PluginServiceManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.ad.api.AdPosition;
import com.xiaomi.smarthome.ad.api.Advertisement;
import com.xiaomi.smarthome.ad.api.IAdCallback;
import com.xiaomi.smarthome.ad.view.BannerAdView;
import com.xiaomi.smarthome.ad.view.BottomFlowAdView;
import com.xiaomi.smarthome.ad.view.NoticeAdView;
import com.xiaomi.smarthome.ad.view.PluginAdUtil;
import com.xiaomi.smarthome.ad.view.PopAdActivity;
import com.xiaomi.smarthome.camera.lowpower.ScreenDeviceLinkageSettingActivity;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.GatewayAddDeviceListActivity;
import com.xiaomi.smarthome.device.api.BaseDevice;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.FaceManagerCallback;
import com.xiaomi.smarthome.device.api.IRecommendSceneItemCallback;
import com.xiaomi.smarthome.device.api.ISceneInfoCallback;
import com.xiaomi.smarthome.device.api.IXmPluginHostActivity;
import com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo;
import com.xiaomi.smarthome.device.api.RecommendSceneItem;
import com.xiaomi.smarthome.device.api.SceneInfo;
import com.xiaomi.smarthome.device.api.XmPluginBaseActivity;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.family.ShareDeviceActivity;
import com.xiaomi.smarthome.feedback.FeedbackActivity;
import com.xiaomi.smarthome.feedback.FeedbackCommonProblemActivity;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.plugin.host.PluginActivityHostApi;
import com.xiaomi.smarthome.frame.plugin.runtime.activity.PluginHostActivity;
import com.xiaomi.smarthome.framework.login.ui.BindWxActivity;
import com.xiaomi.smarthome.framework.page.CommonShareActivity;
import com.xiaomi.smarthome.framework.page.DeviceMoreActivity;
import com.xiaomi.smarthome.framework.page.DeviceMoreNewActivity;
import com.xiaomi.smarthome.framework.page.DeviceNetworkInfoActivity;
import com.xiaomi.smarthome.framework.page.GeneralOneTimePasswordHistoryActivity;
import com.xiaomi.smarthome.framework.page.PictureShareActivity;
import com.xiaomi.smarthome.framework.page.scene.DeviceSceneActivityNew;
import com.xiaomi.smarthome.framework.page.verify.DevicePinVerifyEnterActivity;
import com.xiaomi.smarthome.framework.plugin.rn.PluginRNActivity;
import com.xiaomi.smarthome.framework.update.ui.BleOTAUpgradeActivity;
import com.xiaomi.smarthome.framework.update.ui.MiioUpgradeActivity;
import com.xiaomi.smarthome.homeroom.HomeRoomRecommendActivity;
import com.xiaomi.smarthome.library.common.dialog.UserLicenseDialog;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.light.group.LightGroupInitActivity;
import com.xiaomi.smarthome.miio.activity.BleGatewayActivity;
import com.xiaomi.smarthome.miio.activity.BleGatewayListActivity;
import com.xiaomi.smarthome.miio.activity.BleUpgradeActivity;
import com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoExoPlayerActivity;
import com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoListActivity;
import com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoPlayerActivity;
import com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoWebActivity;
import com.xiaomi.smarthome.miio.camera.face.FaceManager;
import com.xiaomi.smarthome.miio.camera.face.activity.FaceManagerActivity;
import com.xiaomi.smarthome.miio.camera.face.activity.FaceManagerGuideActivity;
import com.xiaomi.smarthome.miio.camera.face.util.FaceUtils;
import com.xiaomi.smarthome.miio.page.deviceophistory.DeviceOpHistoryActivity;
import com.xiaomi.smarthome.multikey.PowerMultikeyActivity;
import com.xiaomi.smarthome.scene.SmartHomeSceneCreateEditActivity;
import com.xiaomi.smarthome.scene.timer.CommonTimerListActivity;
import com.xiaomi.smarthome.scene.timer.CountDownTimerActivity;
import com.xiaomi.smarthome.scene.timer.TimerCommonManager;
import com.xiaomi.smarthome.scenenew.actiivity.PluginRecommendSceneActivity;
import com.xiaomi.smarthome.scenenew.actiivity.SmarthomeCreateAutoSceneActivity;
import com.xiaomi.smarthome.scenenew.manager.RecommendSceneCreator;
import com.xiaomi.smarthome.scenenew.manager.RecommendSceneManager;
import com.xiaomi.smarthome.scenenew.model.DefaultSceneItemSet;
import com.xiaomi.smarthome.scenenew.pluginrecommend.CreateSceneFromRecommendActivity;
import com.xiaomi.smarthome.scenenew.pluginrecommend.LightActionStartActivity;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class PluginActivityHostApiImpl extends PluginActivityHostApi {
    public void showBottomDialogAd(Activity activity, String str, String str2) {
    }

    public void showUserLicenseDialog(Activity activity, String str, String str2, String str3, View.OnClickListener onClickListener) {
    }

    /* access modifiers changed from: package-private */
    public Looper getLooper() {
        Looper myLooper = Looper.myLooper();
        return myLooper == null ? Looper.getMainLooper() : myLooper;
    }

    public void openMoreMenu(Activity activity, DeviceStat deviceStat, ArrayList<IXmPluginHostActivity.MenuItemBase> arrayList, boolean z, int i) {
        if (deviceStat != null) {
            DeviceMoreActivity.openMoreMenu(activity, deviceStat.did, arrayList, z, i);
        }
    }

    public void openMoreMenu(Activity activity, DeviceStat deviceStat, ArrayList<String> arrayList, ArrayList<Intent> arrayList2, boolean z, int i) {
        if (deviceStat != null) {
            DeviceMoreActivity.openMoreMenu(activity, deviceStat.did, arrayList, arrayList2, z, i);
        }
    }

    public void openMoreMenu(Activity activity, String str, ArrayList<String> arrayList, ArrayList<Intent> arrayList2, boolean z, int i) {
        DeviceMoreActivity.openMoreMenu(activity, str, arrayList, arrayList2, z, i);
    }

    public void goUpdateActivity(Activity activity, DeviceStat deviceStat, Intent intent) {
        Class cls;
        if (deviceStat != null) {
            if (deviceStat.pid == Device.PID_BLUETOOTH || deviceStat.pid == Device.PID_BLE_MESH) {
                cls = BleUpgradeActivity.class;
            } else {
                cls = MiioUpgradeActivity.class;
            }
            Intent intent2 = new Intent(activity, cls);
            if (intent != null) {
                intent2.putExtras(intent);
            }
            intent2.putExtra(MiioUpgradeActivity.MIIO_UPGRADE_DID, deviceStat.did);
            intent2.putExtra(MiioUpgradeActivity.MIIO_UPGRADE_PID, deviceStat.pid);
            intent2.putExtra(MiioUpgradeActivity.MIIO_UPGRADE_NAME, deviceStat.name);
            activity.startActivity(intent2);
        }
    }

    public void goUpdateActivity(Activity activity, DeviceStat deviceStat) {
        goUpdateActivity(activity, deviceStat, (Intent) null);
    }

    @Deprecated
    public void startLoadScene(final XmPluginBaseActivity xmPluginBaseActivity) {
        final Handler handler = new Handler(Looper.getMainLooper());
        AnonymousClass1 r1 = new IPluginCallback.Stub() {
            public void onRequestSuccess(String str) throws RemoteException {
                handler.post(new Runnable() {
                    public void run() {
                        xmPluginBaseActivity.onSceneLoaded(true);
                    }
                });
            }

            public void onRequestFailed(int i, String str) throws RemoteException {
                handler.post(new Runnable() {
                    public void run() {
                        xmPluginBaseActivity.onSceneLoaded(false);
                    }
                });
            }
        };
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().updateScene((String) null, r1);
            } else {
                handler.post(new Runnable() {
                    public void run() {
                        xmPluginBaseActivity.onSceneLoaded(false);
                    }
                });
            }
        } catch (RemoteException unused) {
            handler.post(new Runnable() {
                public void run() {
                    xmPluginBaseActivity.onSceneLoaded(false);
                }
            });
        }
    }

    public void startCreateSceneByDid(Activity activity, String str) {
        Intent intent = new Intent(activity, SmarthomeCreateAutoSceneActivity.class);
        DeviceStat deviceByDid = XmPluginHostApi.instance().getDeviceByDid(str);
        if (deviceByDid != null) {
            DefaultSceneItemSet defaultSceneItemSet = new DefaultSceneItemSet();
            defaultSceneItemSet.b = new String[]{deviceByDid.model};
            defaultSceneItemSet.e = deviceByDid.did;
            new RecommendSceneItem.Key();
            defaultSceneItemSet.f21982a = false;
            new ArrayList().add(defaultSceneItemSet);
        }
        intent.putExtra("from", 1);
        intent.putExtra("scene_stat_from", "scene_create_click_widget");
        intent.putExtra(SmarthomeCreateAutoSceneActivity.EXTRA_DEVICE_ID, str);
        activity.startActivity(intent);
    }

    public void startEditScene(Activity activity, int i) {
        if (i >= 0) {
            Intent intent = new Intent(activity, SmarthomeCreateAutoSceneActivity.class);
            intent.putExtra("extra_scene_id", i + "");
            activity.startActivity(intent);
        }
    }

    public void startEditScene(Activity activity, String str) {
        if (!TextUtils.isEmpty(str)) {
            Intent intent = new Intent(activity, SmarthomeCreateAutoSceneActivity.class);
            intent.putExtra("extra_scene_id", str + "");
            activity.startActivity(intent);
        }
    }

    public void openPluginRecommendScene(Activity activity, String str, int i) {
        Intent intent = new Intent(activity, PluginRecommendSceneActivity.class);
        intent.putExtra("did", str);
        intent.putExtra("sr_id", i);
        intent.putExtra(PluginRecommendSceneActivity.EXTRA_IS_FROM_PLUGIN, true);
        activity.startActivity(intent);
    }

    public void startSetTimerList(Activity activity, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        Intent intent = new Intent(activity, CommonTimerListActivity.class);
        intent.putExtra(TimerCommonManager.j, str);
        intent.putExtra(TimerCommonManager.o, str2);
        intent.putExtra(TimerCommonManager.p, str3);
        intent.putExtra(TimerCommonManager.k, str4);
        intent.putExtra(TimerCommonManager.m, str5);
        intent.putExtra(TimerCommonManager.g, str6);
        intent.putExtra(TimerCommonManager.v, str7);
        activity.startActivity(intent);
    }

    public void openFeedbackActivity(Activity activity, DeviceStat deviceStat) {
        if (deviceStat != null) {
            Intent intent = new Intent(activity, FeedbackActivity.class);
            intent.putExtra("extra_device_did", deviceStat.did);
            activity.startActivity(intent);
        }
    }

    public void addToLauncher(DeviceStat deviceStat) {
        if (deviceStat != null) {
            try {
                if (PluginServiceManager.a().b() != null) {
                    PluginServiceManager.a().b().addToLauncher(deviceStat.did, (Intent) null);
                }
            } catch (RemoteException unused) {
            }
        }
    }

    public void loadWebView(String str, String str2) {
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().loadWebView(str, str2);
            }
        } catch (RemoteException unused) {
        }
    }

    public void loadUrl(String str, String str2) {
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().loadUrl(str, str2);
            }
        } catch (RemoteException unused) {
        }
    }

    public void openShopActivity(String str) {
        try {
            if (PluginServiceManager.a().b() != null) {
                IPluginRequest b = PluginServiceManager.a().b();
                b.loadWebView("https://home.mi.com/shop/detail?gid=" + str, "");
            }
        } catch (RemoteException unused) {
        }
    }

    public void share(Activity activity, String str, String str2, String str3, String str4, String str5, Bitmap bitmap) {
        Intent intent = new Intent(activity, SharesActivity.class);
        intent.putExtra("shareTitle", str);
        intent.putExtra("shareContent", str2);
        intent.putExtra("shareUrl", str3);
        intent.putExtra("shareImageUrl", str4);
        intent.putExtra("shareThumbUrl", str5);
        activity.startActivity(intent);
    }

    public void startSearchNewDevice(String str, String str2, final IXmPluginHostActivity.DeviceFindCallback deviceFindCallback) {
        final Looper looper = getLooper();
        AnonymousClass4 r0 = new IPluginCallback2.Stub() {
            public void onRequestFailed(int i, String str) throws RemoteException {
                new Handler(looper).post(new Runnable() {
                    public void run() {
                        if (deviceFindCallback != null) {
                            deviceFindCallback.onDeviceFind(new ArrayList());
                        }
                    }
                });
            }

            public void onRequestSuccess(final Intent intent) throws RemoteException {
                new Handler(looper).post(new Runnable() {
                    public void run() {
                        if (deviceFindCallback != null) {
                            deviceFindCallback.onDeviceFind(intent.getParcelableArrayListExtra("data"));
                        }
                    }
                });
            }
        };
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().startSearchNewDevice(str2, r0);
            } else if (deviceFindCallback != null) {
                deviceFindCallback.onDeviceFind(new ArrayList());
            }
        } catch (RemoteException unused) {
            if (deviceFindCallback != null) {
                deviceFindCallback.onDeviceFind(new ArrayList());
            }
        }
    }

    public void openSceneActivity(Activity activity, DeviceStat deviceStat, String str) {
        if (deviceStat != null) {
            Intent intent = new Intent();
            intent.setClass(activity, DeviceSceneActivityNew.class);
            intent.putExtra("device_id", deviceStat.did);
            activity.startActivity(intent);
        }
    }

    public void getDeviceRecommendScenes(String str, final IXmPluginHostActivity.AsyncCallback<List<RecommendSceneItem>> asyncCallback) {
        final Looper looper = getLooper();
        AnonymousClass5 r1 = new IRecommendSceneItemCallback.Stub() {
            public void onRequestSuccess(List<RecommendSceneItem> list) throws RemoteException {
                PluginActivityHostApiImpl.this.handlerSuccess(asyncCallback, list, looper);
            }

            public void onRequestFailed(int i, String str) throws RemoteException {
                PluginActivityHostApiImpl.this.handlerFailed(asyncCallback, i, str, looper);
            }
        };
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().loadRecommendScenes(str, r1);
            } else if (asyncCallback != null) {
                asyncCallback.onFailure(-1, "getService null");
            }
        } catch (RemoteException e) {
            if (asyncCallback != null) {
                asyncCallback.onFailure(-1, e.getMessage());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public <T> void handlerSuccess(final IXmPluginHostActivity.AsyncCallback<T> asyncCallback, final T t, Looper looper) {
        if (asyncCallback != null) {
            if (looper == null) {
                looper = Looper.getMainLooper();
            }
            new Handler(looper).post(new Runnable() {
                public void run() {
                    if (asyncCallback != null) {
                        asyncCallback.onSuccess(t);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public <T> void handlerFailed(final IXmPluginHostActivity.AsyncCallback<T> asyncCallback, final int i, final String str, Looper looper) {
        if (asyncCallback != null) {
            if (looper == null) {
                looper = Looper.getMainLooper();
            }
            new Handler(looper).post(new Runnable() {
                public void run() {
                    if (asyncCallback != null) {
                        asyncCallback.onFailure(i, str);
                    }
                }
            });
        }
    }

    public void startEditRecommendScenes(Activity activity, RecommendSceneItem recommendSceneItem, String str, String str2) {
        ArrayList arrayList = new ArrayList();
        SmartHomeSceneCreateEditActivity.DefaultSceneItemSet defaultSceneItemSet = new SmartHomeSceneCreateEditActivity.DefaultSceneItemSet();
        defaultSceneItemSet.b = recommendSceneItem.mRecommendConditionList[0].mDeviceModels;
        defaultSceneItemSet.c = recommendSceneItem.mRecommendConditionList[0].mKeys;
        defaultSceneItemSet.d = recommendSceneItem.mRecommendConditionList[0].mProductId;
        if (DeviceFactory.a(str, defaultSceneItemSet.b)) {
            defaultSceneItemSet.e = str2;
        }
        arrayList.add(defaultSceneItemSet);
        ArrayList arrayList2 = new ArrayList();
        for (RecommendSceneItem.RemommendSceneAction remommendSceneAction : recommendSceneItem.mRecommendActionList) {
            SmartHomeSceneCreateEditActivity.DefaultSceneItemSet defaultSceneItemSet2 = new SmartHomeSceneCreateEditActivity.DefaultSceneItemSet();
            defaultSceneItemSet2.b = remommendSceneAction.mDeviceModels;
            defaultSceneItemSet2.c = remommendSceneAction.mKeys;
            defaultSceneItemSet2.d = remommendSceneAction.mProductId;
            if (DeviceFactory.a(str, defaultSceneItemSet2.b)) {
                defaultSceneItemSet2.e = str2;
            }
            arrayList2.add(defaultSceneItemSet2);
        }
        Intent intent = new Intent(activity, SmartHomeSceneCreateEditActivity.class);
        intent.putParcelableArrayListExtra(SmartHomeSceneCreateEditActivity.EXTRA_DEFAULT_CONDITION_ITMES, arrayList);
        intent.putParcelableArrayListExtra(SmartHomeSceneCreateEditActivity.EXTRA_DEFAULT_ACTION_ITMES, arrayList2);
        intent.putExtra(SmartHomeSceneCreateEditActivity.EXTRA_DEFAULT_SCENE_NAME, recommendSceneItem.mName);
        intent.putExtra("scene_stat_from", "scene_recom_click_widget");
        activity.startActivity(intent);
    }

    public List<SceneInfo> getSceneByDid(String str) {
        if (PluginServiceManager.a().b() == null) {
            return null;
        }
        try {
            return PluginServiceManager.a().b().getSceneByDid(str);
        } catch (RemoteException unused) {
            return null;
        }
    }

    public void setSceneEnabled(SceneInfo sceneInfo, boolean z, IXmPluginHostActivity.AsyncCallback<Void> asyncCallback) {
        final Looper looper = getLooper();
        sceneInfo.mEnable = z;
        final IXmPluginHostActivity.AsyncCallback<Void> asyncCallback2 = asyncCallback;
        final SceneInfo sceneInfo2 = sceneInfo;
        final boolean z2 = z;
        AnonymousClass8 r0 = new ISceneInfoCallback.Stub() {
            public void onSuccess(SceneInfo sceneInfo) throws RemoteException {
                PluginActivityHostApiImpl.this.handlerSuccess(asyncCallback2, null, looper);
            }

            public void onFailure(int i, String str) throws RemoteException {
                sceneInfo2.mEnable = z2;
                PluginActivityHostApiImpl.this.handlerFailed(asyncCallback2, i, str, looper);
            }
        };
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().updateSceneItem(sceneInfo, r0);
            } else if (asyncCallback != null) {
                asyncCallback.onFailure(-1, "getService null");
            }
        } catch (RemoteException e) {
            if (asyncCallback != null) {
                asyncCallback.onFailure(-1, e.getMessage());
            }
        }
    }

    public void modifySceneName(SceneInfo sceneInfo, final IXmPluginHostActivity.AsyncCallback<Void> asyncCallback) {
        final Looper looper = getLooper();
        AnonymousClass9 r1 = new ISceneInfoCallback.Stub() {
            public void onSuccess(SceneInfo sceneInfo) throws RemoteException {
                PluginActivityHostApiImpl.this.handlerSuccess(asyncCallback, null, looper);
            }

            public void onFailure(int i, String str) throws RemoteException {
                PluginActivityHostApiImpl.this.handlerFailed(asyncCallback, i, str, looper);
            }
        };
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().updateSceneItem(sceneInfo, r1);
            } else if (asyncCallback != null) {
                asyncCallback.onFailure(-1, "getService null");
            }
        } catch (RemoteException e) {
            if (asyncCallback != null) {
                asyncCallback.onFailure(-1, e.getMessage());
            }
        }
    }

    public void startSetTimerList(Activity activity, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        Intent intent = new Intent(activity, CommonTimerListActivity.class);
        intent.putExtra(TimerCommonManager.j, str);
        intent.putExtra(TimerCommonManager.o, str2);
        intent.putExtra(TimerCommonManager.p, str3);
        intent.putExtra(TimerCommonManager.k, str4);
        intent.putExtra(TimerCommonManager.m, str5);
        intent.putExtra(TimerCommonManager.g, str6);
        intent.putExtra(TimerCommonManager.i, str7);
        intent.putExtra(TimerCommonManager.v, str8);
        if (activity instanceof PluginRNActivity) {
            intent.putExtra(TimerCommonManager.h, str6);
        }
        activity.startActivity(intent);
    }

    public void startSetTimerListV3(Activity activity, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        Intent intent = new Intent(activity, CommonTimerListActivity.class);
        intent.putExtra(TimerCommonManager.j, str);
        intent.putExtra(TimerCommonManager.o, str2);
        intent.putExtra(TimerCommonManager.p, str3);
        intent.putExtra(TimerCommonManager.k, str4);
        intent.putExtra(TimerCommonManager.m, str5);
        intent.putExtra(TimerCommonManager.g, str6);
        intent.putExtra(TimerCommonManager.i, str7);
        intent.putExtra(TimerCommonManager.v, str8);
        intent.putExtra(TimerCommonManager.h, str6);
        activity.startActivity(intent);
    }

    public void startSetTimerListV3(Activity activity, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, String str9, String str10, String str11) {
        Intent intent = new Intent(activity, CommonTimerListActivity.class);
        intent.putExtra(TimerCommonManager.j, str);
        intent.putExtra(TimerCommonManager.o, str2);
        intent.putExtra(TimerCommonManager.p, str3);
        intent.putExtra(TimerCommonManager.k, str4);
        intent.putExtra(TimerCommonManager.m, str5);
        intent.putExtra(TimerCommonManager.g, str6);
        intent.putExtra(TimerCommonManager.h, str6);
        intent.putExtra(TimerCommonManager.i, str7);
        intent.putExtra(TimerCommonManager.v, str8);
        intent.putExtra("both_timer_must_be_set", z);
        intent.putExtra("on_timer_tips", str9);
        intent.putExtra("off_timer_tips", str10);
        intent.putExtra(CommonTimerListActivity.TIMER_TIPS_TIMER_LIST, str11);
        activity.startActivity(intent);
    }

    public void startSetTimerListV4(Activity activity, String str, boolean z, String str2, String str3, String str4, String str5, String str6, String str7, String str8, JSONObject jSONObject) {
        Intent intent = new Intent(activity, CommonTimerListActivity.class);
        intent.putExtra(TimerCommonManager.j, str);
        intent.putExtra(TimerCommonManager.s, z);
        intent.putExtra(TimerCommonManager.o, str2);
        intent.putExtra(TimerCommonManager.p, str3);
        intent.putExtra(TimerCommonManager.k, str4);
        intent.putExtra(TimerCommonManager.m, str5);
        intent.putExtra(TimerCommonManager.g, str6);
        intent.putExtra(TimerCommonManager.h, str6);
        intent.putExtra(TimerCommonManager.i, str7);
        intent.putExtra(TimerCommonManager.v, str8);
        if (jSONObject.has("on_timer_tips")) {
            intent.putExtra("on_timer_tips", jSONObject.optString("on_timer_tips"));
        }
        if (jSONObject.has("off_timer_tips")) {
            intent.putExtra("off_timer_tips", jSONObject.optString("off_timer_tips"));
        }
        if (jSONObject.has(CommonTimerListActivity.TIMER_TIPS_TIMER_LIST)) {
            intent.putExtra(CommonTimerListActivity.TIMER_TIPS_TIMER_LIST, jSONObject.optString(CommonTimerListActivity.TIMER_TIPS_TIMER_LIST));
        }
        if (jSONObject.optBoolean("both_timer_must_be_set", false)) {
            intent.putExtra(CommonTimerListActivity.ON_TIMER_TYPE, false);
            intent.putExtra(CommonTimerListActivity.OFF_TIMER_TYPE, false);
            intent.putExtra(CommonTimerListActivity.PERIOD_TIMER_TYPE, true);
        } else {
            boolean optBoolean = jSONObject.optBoolean(CommonTimerListActivity.ON_TIMER_TYPE, true);
            boolean optBoolean2 = jSONObject.optBoolean(CommonTimerListActivity.OFF_TIMER_TYPE, true);
            boolean optBoolean3 = jSONObject.optBoolean(CommonTimerListActivity.PERIOD_TIMER_TYPE, true);
            if (optBoolean || optBoolean2 || optBoolean3) {
                intent.putExtra(CommonTimerListActivity.ON_TIMER_TYPE, optBoolean);
                intent.putExtra(CommonTimerListActivity.OFF_TIMER_TYPE, optBoolean2);
                intent.putExtra(CommonTimerListActivity.PERIOD_TIMER_TYPE, optBoolean3);
            }
        }
        activity.startActivity(intent);
    }

    public void startSetTimerCountDown(Activity activity, String str, String str2, String str3, String str4, String str5, boolean z) {
        Intent intent = new Intent(activity, CountDownTimerActivity.class);
        intent.putExtra(TimerCommonManager.j, str);
        intent.putExtra(TimerCommonManager.o, str2);
        intent.putExtra(TimerCommonManager.p, str3);
        intent.putExtra(TimerCommonManager.k, str4);
        intent.putExtra(TimerCommonManager.m, str5);
        intent.putExtra(TimerCommonManager.q, z);
        activity.startActivity(intent);
        DeviceStat deviceByDid = XmPluginHostApi.instance().getDeviceByDid(str);
        if (deviceByDid != null) {
            STAT.d.aE(deviceByDid.model);
        }
    }

    public void startSetTimerCountDownV2(Activity activity, String str, String str2, String str3, String str4, String str5, boolean z, String str6) {
        Intent intent = new Intent(activity, CountDownTimerActivity.class);
        intent.putExtra(TimerCommonManager.j, str);
        intent.putExtra(TimerCommonManager.o, str2);
        intent.putExtra(TimerCommonManager.p, str3);
        intent.putExtra(TimerCommonManager.k, str4);
        intent.putExtra(TimerCommonManager.m, str5);
        intent.putExtra(TimerCommonManager.q, z);
        intent.putExtra(TimerCommonManager.h, str6);
        activity.startActivity(intent);
        DeviceStat deviceByDid = XmPluginHostApi.instance().getDeviceByDid(str);
        if (deviceByDid != null) {
            STAT.d.aE(deviceByDid.model);
        }
    }

    public void startSetTimerCountDownV2(Activity activity, String str, String str2, String str3, String str4, String str5, boolean z, String str6, String str7) {
        Intent intent = new Intent(activity, CountDownTimerActivity.class);
        intent.putExtra(TimerCommonManager.j, str);
        intent.putExtra(TimerCommonManager.o, str2);
        intent.putExtra(TimerCommonManager.p, str3);
        intent.putExtra(TimerCommonManager.k, str4);
        intent.putExtra(TimerCommonManager.m, str5);
        intent.putExtra(TimerCommonManager.q, z);
        intent.putExtra(TimerCommonManager.h, str6);
        intent.putExtra(TimerCommonManager.i, str7);
        activity.startActivity(intent);
        DeviceStat deviceByDid = XmPluginHostApi.instance().getDeviceByDid(str);
        if (deviceByDid != null) {
            STAT.d.aE(deviceByDid.model);
        }
    }

    public void openShareMediaActivity(Activity activity, String str, String str2, String str3, Bitmap bitmap, String str4, Bitmap bitmap2, DeviceStat deviceStat) {
        Intent intent = new Intent(activity, CommonShareActivity.class);
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("ShareTitle", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra("ShareContent", str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            intent.putExtra(CommonShareActivity.SHARE_URL, str3);
        }
        if (bitmap != null) {
            intent.putExtra(CommonShareActivity.SHARE_IMAGE, bitmap);
        }
        if (!TextUtils.isEmpty(str4)) {
            intent.putExtra(CommonShareActivity.SHARE_THUMB_URL, str4);
        }
        if (bitmap2 != null) {
            intent.putExtra(CommonShareActivity.SHARE_THUMB, bitmap2);
        }
        if (deviceStat != null) {
            intent.putExtra(CommonShareActivity.SHARE_DEVICE_MODEL, deviceStat.model);
        }
        activity.startActivity(intent);
    }

    public void openShareMediaActivity(Activity activity, String str, String str2, String str3, DeviceStat deviceStat) {
        Intent intent = new Intent(activity, CommonShareActivity.class);
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("ShareTitle", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra("ShareContent", str2);
        }
        if (deviceStat != null) {
            intent.putExtra(CommonShareActivity.SHARE_DEVICE_MODEL, deviceStat.model);
        }
        intent.putExtra(CommonShareActivity.SHARE_IMAGE_FILE_ZIP_URL, str3);
        activity.startActivity(intent);
    }

    public void openShareMediaActivity(Activity activity, String str, String str2, String str3, Bitmap bitmap, DeviceStat deviceStat) {
        Intent intent = new Intent(activity, CommonShareActivity.class);
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("ShareTitle", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra("ShareContent", str2);
        }
        if (deviceStat != null) {
            intent.putExtra(CommonShareActivity.SHARE_DEVICE_MODEL, deviceStat.model);
        }
        intent.putExtra(CommonShareActivity.SHARE_IMAGE_FILE_ZIP_URL, str3);
        if (bitmap != null) {
            intent.putExtra(CommonShareActivity.SHARE_THUMB, bitmap);
        }
        activity.startActivity(intent);
    }

    public void openSharePictureActivity(Activity activity, String str, String str2, String str3) {
        PictureShareActivity.share((Context) activity, str, str2, str3);
    }

    public void startEditCustomScene(Activity activity) {
        Intent intent = new Intent(activity, SmarthomeCreateAutoSceneActivity.class);
        intent.putExtra("scene_stat_from", "scene_edit_click_tab");
        intent.putExtra("from", 1);
        activity.startActivity(intent);
    }

    public void goUpdateActivity(Activity activity, String str) {
        DeviceStat deviceByDid = XmPluginHostApi.instance().getDeviceByDid(str);
        if (deviceByDid != null) {
            Intent intent = new Intent(activity, MiioUpgradeActivity.class);
            intent.putExtra(MiioUpgradeActivity.MIIO_UPGRADE_DID, deviceByDid.did);
            intent.putExtra(MiioUpgradeActivity.MIIO_UPGRADE_PID, deviceByDid.pid);
            intent.putExtra(MiioUpgradeActivity.MIIO_UPGRADE_NAME, deviceByDid.name);
            activity.startActivity(intent);
        }
    }

    public void goBleMeshDeviceUpdateActivity(Activity activity, String str) {
        BleOTAUpgradeActivity.invokeActivity(activity, 5, (String) null, str, (String) null, (String) null);
    }

    public void goBleOtaDeviceUpdateActivity(Activity activity, int i, String str, String str2, String str3, String str4) {
        Log.d("OTA", String.format("ble ota test ,auth type = %d, versionName =%s,test file Md5 5s,testUrl =%s", new Object[]{Integer.valueOf(i), str3, str4, str}));
        BleOTAUpgradeActivity.invokeActivity(activity, i, str, str2, str3, str4);
    }

    public void startLoadScene(final IXmPluginHostActivity.AsyncCallback asyncCallback) {
        final Looper looper = getLooper();
        AnonymousClass10 r1 = new IPluginCallback.Stub() {
            public void onRequestSuccess(String str) throws RemoteException {
                PluginActivityHostApiImpl.this.handlerSuccess(asyncCallback, null, looper);
            }

            public void onRequestFailed(int i, String str) throws RemoteException {
                PluginActivityHostApiImpl.this.handlerFailed(asyncCallback, i, str, looper);
            }
        };
        try {
            if (PluginServiceManager.a().b() != null) {
                PluginServiceManager.a().b().updateScene((String) null, r1);
            } else if (asyncCallback != null) {
                asyncCallback.onFailure(-1, "getService null");
            }
        } catch (RemoteException e) {
            if (asyncCallback != null) {
                asyncCallback.onFailure(-1, e.getMessage());
            }
        }
    }

    public void openHelpActivity(Activity activity, DeviceStat deviceStat) {
        if (deviceStat != null) {
            Intent intent = new Intent(activity, FeedbackCommonProblemActivity.class);
            intent.putExtra("did", deviceStat.did);
            intent.putExtra("extra_model", deviceStat.model);
            activity.startActivity(intent);
        }
    }

    public void openScanBarcodePage(Activity activity, Bundle bundle, int i) {
        Intent intent = new Intent(activity, ScanBarcodeActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivityForResult(intent, i);
    }

    public void openVerfyPincode(Activity activity, Bundle bundle, int i) {
        Intent intent = new Intent(activity, DevicePinVerifyEnterActivity.class);
        intent.putExtras(bundle);
        intent.putExtra("verfy_pincode_first", true);
        activity.startActivityForResult(intent, i);
    }

    public void openMoreMenu(Activity activity, DeviceStat deviceStat, ArrayList<IXmPluginHostActivity.MenuItemBase> arrayList, boolean z, int i, Intent intent) {
        if (deviceStat != null) {
            DeviceMoreActivity.openMoreMenu(activity, deviceStat.did, arrayList, z, i, intent);
        }
    }

    public void openMoreMenu(Activity activity, DeviceStat deviceStat, ArrayList<IXmPluginHostActivity.MenuItemBase> arrayList, boolean z, int i, Intent intent, String str) {
        if (deviceStat != null) {
            DeviceMoreActivity.openMoreMenu(activity, deviceStat.did, arrayList, z, i, intent, str);
        }
    }

    public void openMoreMenu2(Activity activity, DeviceStat deviceStat, ArrayList<IXmPluginHostActivity.MenuItemBase> arrayList, boolean z, int i, Intent intent) {
        if (deviceStat != null) {
            DeviceMoreNewActivity.openMoreMenu(activity, deviceStat.did, arrayList, z, i, intent);
        }
    }

    public void openAddIRController(PluginHostActivity pluginHostActivity, DeviceStat deviceStat, int i, String[] strArr, Bundle bundle) {
        IRMatchingDeviceTypeActivity.showMatchingDeviceTypeActivity(pluginHostActivity, deviceStat, i, strArr, bundle);
    }

    public void openGatewaySubDeviceList(Activity activity, String str) {
        GatewayAddDeviceListActivity.showActivity(activity, str);
    }

    public void openMoreMenu2(Activity activity, DeviceStat deviceStat, ArrayList<IXmPluginHostActivity.MenuItemBase> arrayList, boolean z, int i, Intent intent, Intent intent2) {
        if (deviceStat != null) {
            DeviceMoreNewActivity.openMoreMenu(activity, deviceStat.did, arrayList, z, i, intent, intent2);
        }
    }

    public void sendPluginAdRequest(Activity activity, String str, String str2) {
        if (PluginServiceManager.a().b() != null) {
            try {
                PluginServiceManager.a().b().sendPluginAdRequest(str, str2);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopPluginAd(String str) {
        if (PluginServiceManager.a().b() != null) {
            try {
                PluginServiceManager.a().b().stopPluginAd(str);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void showPopAd(final Activity activity, String str, String str2) {
        if (PluginServiceManager.a().b() != null) {
            try {
                PluginServiceManager.a().b().queryAd(str, str2, new IAdCallback.Stub() {
                    public void onQueryAdFail() throws RemoteException {
                    }

                    public void onQueryAdSuccess(AdPosition adPosition) throws RemoteException {
                        if (CommonUtils.c(activity) && adPosition != null && adPosition.b().size() != 0) {
                            final Advertisement a2 = PluginAdUtil.a(adPosition);
                            if (PluginServiceManager.a().b() != null) {
                                try {
                                    PluginServiceManager.a().b().loadBitmap(a2.e(), new IPluginCallback3.Stub() {
                                        public void onFailed() throws RemoteException {
                                        }

                                        public void onSuccess(Bitmap bitmap) throws RemoteException {
                                            if (CommonUtils.c(activity)) {
                                                PopAdActivity.start(activity, a2);
                                            }
                                        }
                                    });
                                } catch (RemoteException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void showBottomFlowAd(final Activity activity, String str, String str2) {
        if (PluginServiceManager.a().b() != null) {
            try {
                PluginServiceManager.a().b().queryAd(str, str2, new IAdCallback.Stub() {
                    public void onQueryAdFail() throws RemoteException {
                    }

                    public void onQueryAdSuccess(final AdPosition adPosition) throws RemoteException {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                if (!activity.isFinishing() && adPosition != null && adPosition.b().size() != 0) {
                                    new BottomFlowAdView(activity, adPosition).showAtLocation(activity.getWindow().getDecorView(), 80, 0, 0);
                                }
                            }
                        });
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void showBannerAd(final Activity activity, final ViewGroup viewGroup, String str, String str2) {
        if (PluginServiceManager.a().b() != null && viewGroup != null && viewGroup.getChildCount() <= 0) {
            try {
                PluginServiceManager.a().b().queryAd(str, str2, new IAdCallback.Stub() {
                    public void onQueryAdFail() throws RemoteException {
                    }

                    public void onQueryAdSuccess(final AdPosition adPosition) throws RemoteException {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                if (!activity.isFinishing() && adPosition != null && adPosition.b().size() != 0) {
                                    BannerAdView bannerAdView = new BannerAdView(activity);
                                    bannerAdView.setBannerAd(adPosition);
                                    viewGroup.addView(bannerAdView);
                                    PluginAdUtil.a(adPosition.b().get(0));
                                }
                            }
                        });
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void showNoticeAd(final Activity activity, final ViewGroup viewGroup, String str, String str2) {
        if (PluginServiceManager.a().b() != null && viewGroup != null && viewGroup.getChildCount() <= 0) {
            try {
                PluginServiceManager.a().b().queryAd(str, str2, new IAdCallback.Stub() {
                    public void onQueryAdFail() throws RemoteException {
                    }

                    public void onQueryAdSuccess(final AdPosition adPosition) throws RemoteException {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                if (!activity.isFinishing() && adPosition != null && adPosition.b().size() != 0) {
                                    NoticeAdView noticeAdView = new NoticeAdView(activity);
                                    noticeAdView.setNotice(adPosition);
                                    viewGroup.addView(noticeAdView);
                                }
                            }
                        });
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void clickHotSpotAd(final Activity activity, String str, String str2) {
        if (PluginServiceManager.a().b() != null) {
            try {
                PluginServiceManager.a().b().queryAd(str, str2, new IAdCallback.Stub() {
                    public void onQueryAdFail() throws RemoteException {
                    }

                    public void onQueryAdSuccess(final AdPosition adPosition) throws RemoteException {
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                if (!activity.isFinishing() && adPosition != null && adPosition.b().size() != 0) {
                                    Advertisement a2 = PluginAdUtil.a(adPosition);
                                    PluginAdUtil.a();
                                    FrameManager.b().k().loadWebView(a2.f(), "");
                                }
                            }
                        });
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void openOpHistoryActivity(Activity activity, String str) {
        if (!TextUtils.isEmpty(str)) {
            DeviceOpHistoryActivity.openOpHistoryActivity(activity, str);
        }
    }

    public void reportHotSpotAdShow(String str, String str2) {
        if (PluginServiceManager.a().b() != null) {
            try {
                PluginServiceManager.a().b().reportHotSpotShow();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void openShareDeviceActivity(Activity activity, String str) {
        if (!TextUtils.isEmpty(str)) {
            ShareDeviceActivity.openShareDeviceActivity(activity, str);
        }
    }

    public void showUserLicenseDialog(Activity activity, String str, String str2, Spanned spanned, String str3, Spanned spanned2, View.OnClickListener onClickListener, String str4, Intent intent) {
        new UserLicenseDialog.Builder(activity).a(str).b(str2).a(spanned).c(str3).a(intent).b(spanned2).d(str4).a(onClickListener).a().a();
    }

    public void showUserLicenseDialog(Activity activity, String str, View.OnClickListener onClickListener, String str2) {
        new UserLicenseDialog.Builder(activity).a(str).c(activity.getApplicationContext().getString(R.string.dialog_privacy_title)).b(Html.fromHtml(activity.getApplicationContext().getString(R.string.user_privacy_new))).d(str2).a(onClickListener).a().a();
    }

    public void showUserLicenseHtmlDialog(Activity activity, String str, String str2, String str3, String str4, String str5, View.OnClickListener onClickListener, String str6) {
        new UserLicenseDialog.Builder(activity).a(str).b(str2).g(str3).c(str4).h(str5).d(str6).a(onClickListener).a().a();
    }

    public void showUserLicenseUriDialog(Activity activity, String str, String str2, String str3, String str4, String str5, View.OnClickListener onClickListener, String str6) {
        new UserLicenseDialog.Builder(activity).a(str).b(str2).e(str3).c(str4).f(str5).d(str6).a(onClickListener).a().a();
    }

    public void showUserLicenseUriDialogV2(Activity activity, String str, boolean z, String str2, String str3, View.OnClickListener onClickListener, String str4, Intent intent) {
        if (z) {
            new UserLicenseDialog.Builder(activity).a(str).e(str2).c(activity.getString(R.string.dialog_privacy_title)).f(str3).d(str4).a(intent).a(onClickListener).a().a();
        } else {
            new UserLicenseDialog.Builder(activity).a(str).b(activity.getString(R.string.dialog_license_title)).e(str2).c(activity.getString(R.string.dialog_privacy_title)).f(str3).d(str4).a(intent).a(onClickListener).a().a();
        }
    }

    public void startSetTimerListV2(Activity activity, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, String str9, String str10, String str11) {
        Intent intent = new Intent(activity, CommonTimerListActivity.class);
        intent.putExtra(TimerCommonManager.j, str);
        intent.putExtra(TimerCommonManager.o, str2);
        intent.putExtra(TimerCommonManager.p, str3);
        intent.putExtra(TimerCommonManager.k, str4);
        intent.putExtra(TimerCommonManager.m, str5);
        intent.putExtra(TimerCommonManager.g, str6);
        if (activity instanceof PluginRNActivity) {
            intent.putExtra(TimerCommonManager.h, str6);
        }
        intent.putExtra(TimerCommonManager.i, str7);
        intent.putExtra(TimerCommonManager.v, str8);
        intent.putExtra("both_timer_must_be_set", z);
        intent.putExtra("on_timer_tips", str9);
        intent.putExtra("off_timer_tips", str10);
        intent.putExtra(CommonTimerListActivity.TIMER_TIPS_TIMER_LIST, str11);
        activity.startActivity(intent);
    }

    public void startAddRoom(Activity activity) {
        Intent intent = new Intent(activity, HomeRoomRecommendActivity.class);
        intent.putExtra(HomeRoomRecommendActivity.FROM_PLUG, true);
        activity.startActivity(intent);
    }

    public void openCloudVideoListActivity(Activity activity, String str, String str2) {
        Intent intent = new Intent(activity, CloudVideoListActivity.class);
        intent.putExtra("did", str);
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra("title", str2);
        }
        activity.startActivity(intent);
    }

    public void openCloudVideoListActivityForResult(Activity activity, String str, String str2, int i) {
        Intent intent = new Intent(activity, CloudVideoListActivity.class);
        intent.putExtra("did", str);
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra("title", str2);
        }
        activity.startActivityForResult(intent, i);
    }

    public void openCloudVideoPlayerActivity(Activity activity, String str, String str2, String str3) {
        Intent intent = new Intent(activity, CloudVideoPlayerActivity.class);
        intent.putExtra("did", str);
        intent.putExtra("fileId", str2);
        if (!TextUtils.isEmpty(str3)) {
            intent.putExtra("title", str3);
        }
        activity.startActivity(intent);
    }

    public void openCloudVideoWebActivity(Activity activity, String str, String str2, String str3) {
        Intent intent = new Intent(activity, CloudVideoWebActivity.class);
        intent.putExtra("title", str2);
        intent.putExtra("url", str);
        intent.putExtra("did", str3);
        activity.startActivity(intent);
    }

    public void onDeviceReady(Activity activity, String str, String str2, final IXmPluginHostActivity.AsyncCallback<Void> asyncCallback) {
        try {
            PluginServiceManager.a().b().addDeviceToMain(str2, str, new IPluginCallback.Stub() {
                public void onRequestFailed(int i, String str) throws RemoteException {
                }

                public void onRequestSuccess(String str) throws RemoteException {
                    if (asyncCallback != null) {
                        asyncCallback.onSuccess(null);
                    }
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void openCloudVideoExoPlayerActivity(Activity activity, String str, String str2, String str3) {
        Intent intent = new Intent(activity, CloudVideoExoPlayerActivity.class);
        intent.putExtra("did", str);
        intent.putExtra("fileId", str2);
        if (!TextUtils.isEmpty(str3)) {
            intent.putExtra("title", str3);
        }
        activity.startActivity(intent);
    }

    public void openPowerSwitchNameActivity(Activity activity, String str, String str2) {
        PowerMultikeyActivity.startActivity(activity, str, str2);
    }

    public void openWxBindActivity(Activity activity, int i) {
        activity.startActivityForResult(new Intent(activity, BindWxActivity.class), i);
    }

    public void openBtGatewayActivity(Activity activity, String str) {
        Intent intent = new Intent(activity, BleGatewayActivity.class);
        intent.putExtra(BleGatewayListActivity.KEY_GATEWAY_DID, str);
        activity.startActivity(intent);
    }

    public void openVirtualGroupInitActivity(Activity activity, String str, int i) {
        if (!TextUtils.isEmpty(str)) {
            LightGroupInitActivity.open(activity, str, i);
        }
    }

    public void openOneTimePasswordActivity(Activity activity, String str, int i, int i2) {
        Intent intent = new Intent(activity, GeneralOneTimePasswordHistoryActivity.class);
        intent.putExtra("did", str);
        intent.putExtra(Constants.Name.INTERVAL, i);
        intent.putExtra("digits", i2);
        activity.startActivity(intent);
    }

    public void updatePluginRecommendScenes(Activity activity, String str, IXmPluginHostActivity.AsyncCallback<String> asyncCallback) {
        RecommendSceneManager.a().a(str, asyncCallback);
    }

    public void createSceneFrom(Activity activity, String str, String str2, final IXmPluginHostActivity.AsyncCallback<String> asyncCallback) {
        PluginRecommendSceneInfo.RecommendSceneItem parseFrom = PluginRecommendSceneInfo.RecommendSceneItem.parseFrom(str);
        if (parseFrom != null) {
            RecommendSceneCreator.a(parseFrom, str2, new RecommendSceneCreator.SaveSceneCallback() {
                public void onSaveLocalFail() {
                    if (asyncCallback != null) {
                        asyncCallback.onFailure(-1, "save localscene fail");
                    }
                }

                public void onSaveCloudSuccess(boolean z) {
                    if (asyncCallback != null) {
                        IXmPluginHostActivity.AsyncCallback asyncCallback = asyncCallback;
                        asyncCallback.onSuccess("isSynchronizeCloud #" + z);
                    }
                }

                public void onSaveCloudFail(int i, String str) {
                    if (asyncCallback != null) {
                        asyncCallback.onFailure(i, str);
                    }
                }
            });
        } else if (asyncCallback != null) {
            asyncCallback.onFailure(-9000, "parse recommendSceneItem fail");
        }
    }

    public void openNetworkInfoActivity(Activity activity, String str) {
        Intent intent = new Intent(activity, DeviceNetworkInfoActivity.class);
        intent.putExtra("did", str);
        activity.startActivity(intent);
    }

    public void openFaceManagerActivity(Activity activity, String str) {
        String str2 = XmPluginHostApi.instance().getDeviceByDid(str).userId;
        if (!FaceUtils.getNeedFaceGuide(str)) {
            Intent intent = new Intent(activity, FaceManagerActivity.class);
            intent.putExtra("extra_device_did", str);
            activity.startActivity(intent);
            return;
        }
        if (FaceUtils.getNeedFaceGuide(str + str2)) {
            Intent intent2 = new Intent(activity, FaceManagerGuideActivity.class);
            intent2.putExtra("extra_device_did", str);
            activity.startActivity(intent2);
            return;
        }
        Intent intent3 = new Intent(activity, FaceManagerActivity.class);
        intent3.putExtra("extra_device_did", str);
        activity.startActivity(intent3);
    }

    public void openFaceManagerActivity(int i, Activity activity, String str, String str2) {
        FaceUtils.openFaceManagerActivity(i, activity, str, str2);
    }

    public void openMarkFaceDialog(Activity activity, String str, String str2, final FaceManagerCallback faceManagerCallback) {
        FaceUtils.processMarkFace(activity, str2, FaceManager.getInstance(new BaseDevice(XmPluginHostApi.instance().getDeviceByDid(str))), new FaceManager.IFaceCallback() {
            public void onSuccess(Object obj, Object obj2) {
                faceManagerCallback.onSuccess(obj, obj2);
            }

            public void onFailure(int i, String str) {
                faceManagerCallback.onFailure(i, str);
            }
        });
    }

    public void openReplaceFaceDialog(Activity activity, String str, String str2, String str3, String str4, final FaceManagerCallback faceManagerCallback) {
        FaceUtils.processReplaceFace(activity, str4, str2, str3, FaceManager.getInstance(new BaseDevice(XmPluginHostApi.instance().getDeviceByDid(str))), new FaceManager.IFaceCallback() {
            public void onSuccess(Object obj, Object obj2) {
                faceManagerCallback.onSuccess(obj, obj2);
            }

            public void onFailure(int i, String str) {
                faceManagerCallback.onFailure(i, str);
            }
        });
    }

    public void openFaceEmptyActivity(Activity activity, String str) {
        FaceUtils.openFaceActivity(activity, str, false, 1);
    }

    public void startRecommendSceneDetailActivityBy(Activity activity, String str, int i) {
        if (TextUtils.isEmpty(str) || i < 1000 || activity == null) {
            return;
        }
        if (i / 1000 == 2) {
            Intent intent = new Intent(activity, CreateSceneFromRecommendActivity.class);
            intent.putExtra("sr_id", i);
            intent.putExtra("did", str);
            activity.startActivity(intent);
            return;
        }
        switch (i) {
            case 1000:
                Intent intent2 = new Intent(activity, PluginRecommendSceneActivity.class);
                intent2.putExtra("sr_id", i);
                intent2.putExtra("did", str);
                activity.startActivity(intent2);
                return;
            case 1001:
            case 1002:
            case 1003:
                Intent intent3 = new Intent(activity, LightActionStartActivity.class);
                intent3.putExtra("sr_id", i);
                intent3.putExtra("did", str);
                activity.startActivity(intent3);
                return;
            default:
                return;
        }
    }

    public void openScreenDeviceLinkageSettingActivity(Activity activity, String str, boolean z) {
        Intent intent = new Intent(activity, ScreenDeviceLinkageSettingActivity.class);
        intent.putExtra("extra_device_did", str);
        intent.putExtra("extra_multi_choice", z);
        intent.putExtra("extra_to_device_select_page", false);
        activity.startActivity(intent);
    }

    public void openScreenDeviceLinkageSettingActivity(PluginHostActivity pluginHostActivity, String str, boolean z, int i, String str2) {
        Intent intent = new Intent(pluginHostActivity, ScreenDeviceLinkageSettingActivity.class);
        intent.putExtra("extra_device_did", str);
        intent.putExtra("extra_multi_choice", z);
        intent.putExtra("extra_max_length", i);
        intent.putExtra("extra_screen_device_list", str2);
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra("extra_to_select_page", true);
        }
        pluginHostActivity.startActivity(intent);
    }
}
