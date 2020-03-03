package com.xiaomi.smarthome.framework.plugin.rn.nativemodule;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.View;
import android.view.ViewConfiguration;
import com.alipay.mobile.common.logging.util.LoggingSPCache;
import com.amap.api.location.AMapLocation;
import com.facebook.internal.ServerProtocol;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.measurement.AppMeasurement;
import com.miui.tsmclient.analytics.AnalyticManager;
import com.miui.tsmclient.net.TSMAuthContants;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.Constants;
import com.taobao.weex.common.WXConfig;
import com.unionpay.tsmservice.data.Constant;
import com.xiaomi.plugin.core.XmPluginPackage;
import com.xiaomi.router.miio.miioplugin.IPluginRequest;
import com.xiaomi.router.miio.miioplugin.PluginServiceManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.constants.AppConstants;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.server.internal.plugin.util.ByteUtils;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.MessageCallback;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.host.PluginHostApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.framework.location.AMapLocationManager;
import com.xiaomi.smarthome.framework.location.SHLocationManager;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.navigate.PageUrl;
import com.xiaomi.smarthome.framework.page.DeviceMoreActivity;
import com.xiaomi.smarthome.framework.page.DeviceNetworkInfoActivity;
import com.xiaomi.smarthome.framework.page.LicenseAndPrivacyActivity;
import com.xiaomi.smarthome.framework.page.TimezoneActivity;
import com.xiaomi.smarthome.framework.plugin.rn.PluginRNActivity;
import com.xiaomi.smarthome.framework.plugin.rn.RNRuntime;
import com.xiaomi.smarthome.framework.plugin.rn.fixbug.AndroidBug5497Workaround;
import com.xiaomi.smarthome.framework.plugin.rn.jsc.JSCManagerV2;
import com.xiaomi.smarthome.framework.plugin.rn.utils.MIOTReadableMap;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnCallbackMapUtil;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.util.CorntabUtils;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.light.group.LightGroupManageActivity;
import com.xiaomi.smarthome.light.group.LightGroupSettingActivity;
import com.xiaomi.smarthome.light.group.LightGroupSettingV2Activity;
import com.xiaomi.smarthome.miio.LanguageUtil;
import com.xiaomi.smarthome.multikey.PowerMultikeyActivity;
import com.xiaomi.smarthome.scene.timer.CommonTimerListActivity;
import com.xiaomi.smarthome.scene.timer.PlugTimer;
import com.xiaomi.smarthome.scenenew.actiivity.PluginRecommendSceneActivity;
import com.xiaomi.smarthome.setting.PluginSetting;
import com.xiaomi.youpin.UserMode;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import javax.annotation.Nullable;
import miui.os.SystemProperties;
import miuipub.security.DigestUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class MIOTHostModule extends MIOTBaseJavaModule {
    public static final String RESULT_KEY_FINISH = "result_data";
    public static final int RESULT_VAL_FINISH = 1;

    public String getName() {
        return "MIOTHost";
    }

    public MIOTHostModule(ReactApplicationContext reactApplicationContext, PluginRecord pluginRecord, DeviceStat deviceStat) {
        super(reactApplicationContext);
    }

    public MIOTHostModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void createExecutor(String str, String str2, Callback callback) {
        JSCManagerV2.a().a(getReactApplicationContext(), str, str2, callback);
    }

    @ReactMethod
    public void executeMethod(String str, String str2, String str3, Callback callback) {
        JSCManagerV2.a().a(str, str2, str3, callback);
    }

    @ReactMethod
    public void removeExecutor(String str) {
        JSCManagerV2.a().b(str);
    }

    @Nullable
    public Map<String, Object> getConstants() {
        String str;
        String str2;
        HashMap hashMap = new HashMap();
        WritableMap createMap = Arguments.createMap();
        createMap.putString("mobileModel", Build.MODEL);
        createMap.putString(WXConfig.sysVersion, Build.VERSION.RELEASE);
        createMap.putInt("hostApiLevel", PluginSetting.b);
        createMap.putBoolean("isXiaomiPhone", isXiaomiPhone());
        try {
            if (SystemApi.c()) {
                createMap.putString("miuiVersion", SystemProperties.get("ro.miui.ui.version.name", ""));
            }
        } catch (Throwable th) {
            Log.e("MIOTHostModule", AppMeasurement.Param.FATAL, th);
        }
        hashMap.put("systemInfo", createMap);
        Locale settingLocale = XmPluginHostApi.instance().getSettingLocale();
        if (settingLocale == null && (settingLocale = Locale.getDefault()) == null) {
            settingLocale = getReactApplicationContext().getResources().getConfiguration().locale;
        }
        String country = settingLocale.getCountry();
        hashMap.put("buildType", GlobalSetting.r ? "release" : "debug");
        hashMap.put("appVersion", SystemApi.a().d(getReactApplicationContext()));
        hashMap.put(TSMAuthContants.PARAM_APP_VERSION_CODE, Integer.valueOf(SystemApi.a().e(getReactApplicationContext())));
        if (country == null) {
            str = settingLocale.getLanguage();
        } else {
            str = settingLocale.getLanguage() + JSMethod.NOT_SET + country;
        }
        hashMap.put("language", str);
        hashMap.put("timeZone", TimeZone.getDefault().getID());
        hashMap.put("appConfigEnv", Integer.valueOf(XmPluginHostApi.instance().getUsePreviewConfig()));
        boolean is24HourFormat = DateFormat.is24HourFormat(getReactApplicationContext());
        LogUtil.c("MIOTHostModule", "is24: " + is24HourFormat);
        hashMap.put("is24HourTime", Boolean.valueOf(is24HourFormat));
        Locale b = LanguageUtil.b();
        if (b != null) {
            String country2 = b.getCountry();
            if (TextUtils.isEmpty(country2)) {
                str2 = b.getLanguage();
            } else {
                str2 = b.getLanguage() + JSMethod.NOT_SET + country2;
            }
            hashMap.put("systemLanguage", str2);
        } else {
            Log.e("miot-rn-plugin", "MIOTHostModule  Locale.getDefault is null...");
        }
        return hashMap;
    }

    private boolean hasNavigationBar() {
        if (Build.VERSION.SDK_INT >= 17 && Settings.Global.getInt(getCurrentActivity().getContentResolver(), "force_fsg_nav_bar", 0) != 0) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            Display defaultDisplay = getCurrentActivity().getWindowManager().getDefaultDisplay();
            Point point = new Point();
            Point point2 = new Point();
            defaultDisplay.getSize(point);
            defaultDisplay.getRealSize(point2);
            if (point2.y != point.y) {
                return true;
            }
            return false;
        }
        boolean hasPermanentMenuKey = ViewConfiguration.get(getCurrentActivity()).hasPermanentMenuKey();
        boolean deviceHasKey = KeyCharacterMap.deviceHasKey(4);
        if (hasPermanentMenuKey || deviceHasKey) {
            return false;
        }
        return true;
    }

    private WritableMap getPhoneScreenInfo() {
        int i;
        WritableMap createMap = Arguments.createMap();
        if (getCurrentActivity() == null) {
            return null;
        }
        View findViewById = getCurrentActivity().findViewById(R.id.fl_root);
        int i2 = -1;
        if (findViewById != null) {
            i2 = findViewById.getHeight();
            i = findViewById.getWidth();
        } else {
            i = -1;
        }
        Display defaultDisplay = getCurrentActivity().getWindowManager().getDefaultDisplay();
        Point point = new Point();
        Point point2 = new Point();
        defaultDisplay.getSize(point);
        defaultDisplay.getRealSize(point2);
        RnPluginLog.a("View宽高--> viewHeight:  " + i2 + "   viewWidth: " + i);
        if (i2 < 200 || i < 200) {
            i2 = point2.y;
            i = point2.x;
        }
        int abs = Math.abs(point2.y - i2);
        RnPluginLog.a("屏幕高度--> size.y:  " + point.y + "   realSize.y: " + point2.y + "  navigationBarHeight: " + abs);
        createMap.putInt("viewWidth", DisplayUtils.b((float) i));
        createMap.putInt(Constants.Name.VIEW_HEIGHT, DisplayUtils.b((float) i2));
        return createMap;
    }

    @ReactMethod
    public final void getPhoneScreenInfo(Callback callback) {
        if (callback != null) {
            WritableMap phoneScreenInfo = getPhoneScreenInfo();
            if (phoneScreenInfo != null) {
                callback.invoke(true, phoneScreenInfo);
                return;
            }
            callback.invoke(false, "current activity is not init...");
        }
    }

    @ReactMethod
    public final void getCurrentTimeMillis(Callback callback) {
        callback.invoke(Double.valueOf((double) System.currentTimeMillis()));
    }

    @ReactMethod
    public final void getLocation(final Callback callback) {
        AMapLocationManager.a().a((AMapLocationManager.LocationCallback) new AMapLocationManager.LocationCallback() {
            public void a() {
            }

            public void a(AMapLocation aMapLocation) {
                WritableMap createMap = Arguments.createMap();
                createMap.putString("country", aMapLocation.getCountry());
                createMap.putString("aoiname", aMapLocation.getAoiName());
                createMap.putString("district", aMapLocation.getDistrict());
                createMap.putString("street", aMapLocation.getStreet());
                createMap.putString("city", aMapLocation.getCity());
                createMap.putString("citycode", aMapLocation.getCityCode());
                createMap.putString("province", aMapLocation.getProvince());
                createMap.putString("adcode", aMapLocation.getAdCode());
                createMap.putString("address", aMapLocation.getAddress());
                createMap.putString("latitude", aMapLocation.getLatitude() + "");
                createMap.putString("longitude", aMapLocation.getLongitude() + "");
                createMap.putString("accuracy", aMapLocation.getAccuracy() + "");
                createMap.putString("altitude", aMapLocation.getAltitude() + "");
                createMap.putString("speed", aMapLocation.getSpeed() + "");
                callback.invoke(true, createMap);
            }
        });
    }

    @ReactMethod
    public final void saveInfo(String str, String str2) {
        getSharedPreferencesV2().edit().putString(str, str2).apply();
    }

    @ReactMethod
    public final void keepScreenNotLock(final boolean z) {
        SHApplication.getGlobalHandler().post(new Runnable() {
            public void run() {
                RNRuntime.a().a(z);
            }
        });
    }

    @ReactMethod
    public final void encodeMD5(String str, Callback callback) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            Object[] objArr = new Object[2];
            objArr[0] = true;
            if (str == null) {
                str = "";
            }
            objArr[1] = ByteUtils.c(instance.digest(str.getBytes()));
            callback.invoke(objArr);
        } catch (Throwable th) {
            callback.invoke(false, Log.getStackTraceString(th));
        }
    }

    @ReactMethod
    public final void encodeBase64(String str, Callback callback) {
        try {
            Object[] objArr = new Object[2];
            objArr[0] = true;
            if (str == null) {
                str = "";
            }
            objArr[1] = Base64.encodeToString(str.getBytes(), 0);
            callback.invoke(objArr);
        } catch (Throwable th) {
            callback.invoke(false, Log.getStackTraceString(th));
        }
    }

    @ReactMethod
    public final void decodeBase64(String str, Callback callback) {
        try {
            Object[] objArr = new Object[2];
            objArr[0] = true;
            if (str == null) {
                str = "";
            }
            objArr[1] = new String(Base64.decode(str.getBytes(), 0));
            callback.invoke(objArr);
        } catch (Throwable th) {
            callback.invoke(false, Log.getStackTraceString(th));
        }
    }

    @ReactMethod
    public final void encodeSHA1(String str, Callback callback) {
        try {
            MessageDigest instance = MessageDigest.getInstance(DigestUtils.b);
            Object[] objArr = new Object[2];
            objArr[0] = true;
            if (str == null) {
                str = "";
            }
            objArr[1] = ByteUtils.c(instance.digest(str.getBytes()));
            callback.invoke(objArr);
        } catch (Throwable th) {
            callback.invoke(false, Log.getStackTraceString(th));
        }
    }

    @ReactMethod
    public final void encodeSHA2(String str, Callback callback) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            Object[] objArr = new Object[2];
            objArr[0] = true;
            if (str == null) {
                str = "";
            }
            objArr[1] = ByteUtils.c(instance.digest(str.getBytes()));
            callback.invoke(objArr);
        } catch (Throwable th) {
            callback.invoke(false, Log.getStackTraceString(th));
        }
    }

    @ReactMethod
    public final void loadInfoCallback(String str, Callback callback) {
        String string = getSharedPreferencesV2().getString(str, "");
        if (TextUtils.isEmpty(string)) {
            string = getSharedPreferences().getString(str, "");
            if (!TextUtils.isEmpty(string)) {
                saveInfo(str, string);
            }
        }
        callback.invoke(string);
    }

    @ReactMethod
    public final void openDeleteDevice(String str) {
        DeviceStat device = getDevice();
        if (device != null) {
            RNRuntime.a().a(str, device.did, device.pid);
        }
    }

    @ReactMethod
    public final void closeCurrentPage() {
        SHApplication.getGlobalHandler().post(new Runnable() {
            public void run() {
                RNRuntime.a().o();
            }
        });
    }

    @ReactMethod
    public final void openShareListBar(String str, String str2, ReadableArray readableArray, String str3) {
        RNRuntime.a().a(str, str2, readableArray, str3);
    }

    @ReactMethod
    public final void getDevicesWithModel(String str, Callback callback) {
        if (TextUtils.isEmpty(str)) {
            callback.invoke(false, "request model is empty or null");
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        List<DeviceStat> deviceListV2 = XmPluginHostApi.instance().getDeviceListV2(arrayList);
        WritableArray createArray = Arguments.createArray();
        if (deviceListV2 != null) {
            for (DeviceStat device2Map : deviceListV2) {
                WritableMap createMap = Arguments.createMap();
                MIOTDeviceModule.device2Map(createMap, device2Map);
                createArray.pushMap(createMap);
            }
        }
        callback.invoke(true, createArray);
    }

    @ReactMethod
    public final void openShareDevicePage() {
        final DeviceStat device = getDevice();
        if (device != null) {
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    RNRuntime.a().a(device.did);
                }
            });
        }
    }

    @ReactMethod
    public final void openRoomManagementPage() {
        final DeviceStat device = getDevice();
        if (device != null) {
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    RNRuntime.a().b(device.did);
                }
            });
        }
    }

    @ReactMethod
    public final void openZigbeeConnectDeviceList(String str) {
        final DeviceStat device = getDevice();
        if (device != null) {
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    RNRuntime.a().c(device.did);
                }
            });
        }
    }

    @ReactMethod
    public final void openVoiceCtrlDeviceAuthPage() {
        final DeviceStat device = getDevice();
        if (device != null) {
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    RNRuntime.a().d(device.did);
                }
            });
        }
    }

    @ReactMethod
    public final void openIftttAutoPage() {
        final DeviceStat device = getDevice();
        if (device != null) {
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    RNRuntime.a().a(device);
                }
            });
        }
    }

    @ReactMethod
    public final void openPowerSwitchNameActivity() {
        SHApplication.getGlobalHandler().post(new Runnable() {
            public void run() {
                RNRuntime.a().p();
            }
        });
    }

    @ReactMethod
    public final void openFeedbackInput() {
        final DeviceStat device = getDevice();
        if (device != null) {
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    RNRuntime.a().a(device.model, device.did);
                }
            });
        }
    }

    @ReactMethod
    public final void openSecuritySetting() {
        final DeviceStat device = getDevice();
        if (device != null) {
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    RNRuntime.a().e(device.did);
                }
            });
        }
    }

    @ReactMethod
    public final void openHelpPage() {
        final DeviceStat device = getDevice();
        if (device != null) {
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    RNRuntime.a().b(device.model, device.did);
                }
            });
        }
    }

    @ReactMethod
    public final void openAddIRController(String str, final int i, final ReadableArray readableArray, final ReadableMap readableMap) {
        SHApplication.getGlobalHandler().post(new Runnable() {
            public void run() {
                ArrayList list = Arguments.toList(readableArray);
                RNRuntime.a().a(i, list == null ? null : (String[]) list.toArray(new String[list.size()]), Arguments.toBundle(readableMap));
            }
        });
    }

    @ReactMethod
    public final void openBtGatewayPage() {
        final DeviceStat device = getDevice();
        if (device != null) {
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    RNRuntime.a().f(device.did);
                }
            });
        }
    }

    @Deprecated
    @ReactMethod
    public final void privacyAndProtocolReview(String str, ReadableArray readableArray, String str2, ReadableArray readableArray2) {
        final DeviceStat device = getDevice();
        if (device != null) {
            final String str3 = str;
            final ReadableArray readableArray3 = readableArray;
            final String str4 = str2;
            final ReadableArray readableArray4 = readableArray2;
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    RNRuntime.a().a(device.did, str3, readableArray3, str4, readableArray4);
                }
            });
        }
    }

    @Deprecated
    @ReactMethod
    public final void openPrivacyLicense(String str, ReadableArray readableArray, String str2, ReadableArray readableArray2, Callback callback) {
        final DeviceStat device = getDevice();
        if (device != null) {
            final String str3 = str;
            final ReadableArray readableArray3 = readableArray;
            final String str4 = str2;
            final ReadableArray readableArray4 = readableArray2;
            final Callback callback2 = callback;
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    RNRuntime.a().a(device.did, str3, readableArray3, str4, readableArray4, callback2);
                }
            });
        }
    }

    @ReactMethod
    public void showDeclarationWithConfig(final ReadableMap readableMap, final Callback callback) {
        if (readableMap == null) {
            callback.invoke(false, "params is null...");
            return;
        }
        final DeviceStat device = getDevice();
        if (device == null || getCurrentActivity() == null) {
            callback.invoke(false, "current device is null or activity is null...");
            return;
        }
        SHApplication.getGlobalHandler().post(new Runnable() {
            public void run() {
                String a2 = MIOTUtils.a(MIOTUtils.e(readableMap, "agreementURL"));
                String a3 = MIOTUtils.a(MIOTUtils.e(readableMap, "privacyURL"));
                String a4 = MIOTUtils.a(MIOTUtils.e(readableMap, "experiencePlanURL"));
                boolean b2 = MIOTUtils.b(readableMap, "hideAgreement");
                boolean b3 = MIOTUtils.b(readableMap, "hideUserExperiencePlan");
                if (b2) {
                    a2 = null;
                }
                String str = a2;
                Intent intent = new Intent();
                if (b3) {
                    intent.putExtra("enable_privacy_setting", false);
                } else {
                    intent.putExtra("enable_privacy_setting", true);
                    if (!TextUtils.isEmpty(a4)) {
                        intent.putExtra(DeviceMoreActivity.ARGS_USR_EXP_PLAN_RN_URI, a4);
                    }
                }
                FrameManager.b().k().showUserLicenseUriDialogV2(MIOTHostModule.this.getCurrentActivity(), (String) null, b2, str, a3, new View.OnClickListener() {
                    public void onClick(View view) {
                        callback.invoke(true);
                    }
                }, device.did, intent);
            }
        });
    }

    @ReactMethod
    public void openDeclarationWithConfig(final ReadableMap readableMap, final Callback callback) {
        if (readableMap == null) {
            callback.invoke(false, "params is null...");
            return;
        }
        final DeviceStat device = getDevice();
        if (device == null || getCurrentActivity() == null) {
            callback.invoke(false, "current device is null or activity is null...");
            return;
        }
        SHApplication.getGlobalHandler().post(new Runnable() {
            public void run() {
                String a2 = MIOTUtils.a(MIOTUtils.e(readableMap, "agreementURL"));
                String a3 = MIOTUtils.a(MIOTUtils.e(readableMap, "privacyURL"));
                String a4 = MIOTUtils.a(MIOTUtils.e(readableMap, "experiencePlanURL"));
                boolean b2 = MIOTUtils.b(readableMap, "hideAgreement");
                boolean b3 = MIOTUtils.b(readableMap, "hideUserExperiencePlan");
                if (b2) {
                    a2 = null;
                }
                if (MIOTHostModule.this.getCurrentActivity() != null) {
                    Intent intent = new Intent(MIOTHostModule.this.getCurrentActivity(), LicenseAndPrivacyActivity.class);
                    if (b2) {
                        intent.putExtra(DeviceMoreActivity.ARGS_USE_DEFAULT_LICENSE, true);
                    } else {
                        intent.putExtra(DeviceMoreActivity.ARGS_USE_DEFAULT_LICENSE, false);
                        intent.putExtra(DeviceMoreActivity.ARGS_LICENSE_URI, a2);
                        intent.putExtra(LicenseAndPrivacyActivity.ARGS_LICENSE_TITLE, MIOTHostModule.this.getCurrentActivity().getString(R.string.device_more_activity_license));
                    }
                    intent.putExtra(DeviceMoreActivity.ARGS_PRIVACY_URI, a3);
                    intent.putExtra(LicenseAndPrivacyActivity.ARGS_PRIVACY_TITLE, MIOTHostModule.this.getCurrentActivity().getString(R.string.device_more_activity_privacy));
                    intent.putExtra("did", device.did);
                    if (b3) {
                        intent.putExtra("enable_privacy_setting", false);
                    } else {
                        intent.putExtra("enable_privacy_setting", true);
                        if (!TextUtils.isEmpty(a4)) {
                            intent.putExtra(DeviceMoreActivity.ARGS_USR_EXP_PLAN_RN_URI, a4);
                        }
                    }
                    MIOTHostModule.this.getCurrentActivity().startActivityForResult(intent, 100);
                    callback.invoke(true, "success...");
                }
            }
        });
    }

    @ReactMethod
    public final void openWebPage(final String str) {
        SHApplication.getGlobalHandler().post(new Runnable() {
            public void run() {
                try {
                    if (PluginServiceManager.a().b() != null) {
                        PluginServiceManager.a().b().loadUrl(str, "");
                    }
                } catch (RemoteException e) {
                    Log.e("MIOTHostModule", "openWebPage", e);
                }
            }
        });
    }

    @ReactMethod
    public final void openChangeDeviceName() {
        final DeviceStat device = getDevice();
        if (device != null) {
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    RNRuntime.a().a(device.did, device.mac, device.pid, device.name, (Callback) new Callback() {
                        public void invoke(Object... objArr) {
                            device.name = objArr[0];
                        }
                    });
                }
            });
        }
    }

    @ReactMethod
    public final void openAddToDesktopPage() {
        final DeviceStat device = getDevice();
        if (device != null) {
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    try {
                        IPluginRequest b2 = PluginServiceManager.a().b();
                        if (b2 != null) {
                            b2.addToLauncher(device.did, (Intent) null);
                        }
                    } catch (RemoteException e) {
                        Log.e("MIOTHostModule", "openAddToDesktopPage", e);
                    }
                }
            });
        }
    }

    @ReactMethod
    public final void openBleMeshDeviceUpgradePage() {
        final DeviceStat device = getDevice();
        if (device != null) {
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    RNRuntime.a().g(device.did);
                }
            });
        }
    }

    @ReactMethod
    public final void openBleOtaDeviceUpgradePage(final ReadableMap readableMap) {
        final DeviceStat device = getDevice();
        if (device != null) {
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    MIOTReadableMap mIOTReadableMap = new MIOTReadableMap(readableMap);
                    int a2 = mIOTReadableMap.a(ServerProtocol.DIALOG_PARAM_AUTH_TYPE, -1);
                    String a3 = mIOTReadableMap.a("fake_dfu_name", (String) null);
                    RNRuntime.a().a(a2, mIOTReadableMap.a("fake_dfu_url", (String) null), device.did, a3, mIOTReadableMap.a("md5", (String) null));
                }
            });
        }
    }

    @ReactMethod
    public final void openDeviceUpgradePage() {
        final DeviceStat device = getDevice();
        if (device != null) {
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    RNRuntime.a().h(device.did);
                }
            });
        }
    }

    @ReactMethod
    public final void openAddDeviceGroupPage() {
        final DeviceStat device = getDevice();
        if (device != null) {
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    RNRuntime.a().i(device.model);
                }
            });
        }
    }

    @ReactMethod
    public final void openAddDeviceGroupPageWithGroupModel(@Nullable final String str) {
        if (getDevice() != null) {
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    RNRuntime.a().i(str);
                }
            });
        }
    }

    @ReactMethod
    public final void openEditDeviceGroupPage(ReadableArray readableArray) {
        final DeviceStat device = getDevice();
        if (device != null) {
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    RNRuntime.a().c(device.model, device.did);
                }
            });
        } else {
            RnPluginLog.b("openEditDeviceGroupPage: device is null");
        }
    }

    @Deprecated
    @ReactMethod
    public final void openTimerSettingPage(String str, String str2, String str3, String str4) {
        final DeviceStat device = getDevice();
        if (device != null) {
            final String str5 = str;
            final String str6 = str2;
            final String str7 = str3;
            final String str8 = str4;
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    RNRuntime.a().a(device.did, str5, str6, str7, str8);
                }
            });
        }
    }

    @Deprecated
    @ReactMethod
    public final void openTimerSettingPageWithVariousTypeParams(String str, String str2, String str3, String str4) {
        final DeviceStat device = getDevice();
        if (device != null) {
            final String str5 = str;
            final String str6 = str2;
            final String str7 = str3;
            final String str8 = str4;
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    RNRuntime.a().b(device.did, str5, str6, str7, str8);
                }
            });
        }
    }

    @Deprecated
    @ReactMethod
    public final void openTimerSettingPageWithCustomIdentifier(String str, String str2, String str3, String str4, String str5) {
        final DeviceStat device = getDevice();
        if (device != null) {
            final String str6 = str;
            final String str7 = str2;
            final String str8 = str3;
            final String str9 = str4;
            final String str10 = str5;
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    RNRuntime.a().a(device.did, str6, str7, str8, str9, str10);
                }
            });
        }
    }

    @ReactMethod
    public final void openTimerSettingPageWithOptions(ReadableMap readableMap) {
        final DeviceStat device = getDevice();
        if (device == null || readableMap == null) {
            RnPluginLog.b("device is null or params is null...");
            return;
        }
        MIOTReadableMap mIOTReadableMap = new MIOTReadableMap(readableMap);
        final String a2 = mIOTReadableMap.a("onMethod", "");
        final String a3 = mIOTReadableMap.a("onParam", "");
        final String a4 = mIOTReadableMap.a("offMethod", "");
        final String a5 = mIOTReadableMap.a("offParam", "");
        final String a6 = mIOTReadableMap.a("displayName", "");
        final String a7 = mIOTReadableMap.a("identify", "");
        final String a8 = mIOTReadableMap.a("timerTitle", "");
        final JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("on_timer_tips", mIOTReadableMap.a("onTimerTips", ""));
            jSONObject.put("off_timer_tips", mIOTReadableMap.a("offTimerTips", ""));
            jSONObject.put(CommonTimerListActivity.TIMER_TIPS_TIMER_LIST, mIOTReadableMap.a("listTimerTips", ""));
            jSONObject.put("both_timer_must_be_set", mIOTReadableMap.a("bothTimerMustBeSet", false));
            jSONObject.put(CommonTimerListActivity.ON_TIMER_TYPE, mIOTReadableMap.a("showOnTimerType", true));
            jSONObject.put(CommonTimerListActivity.OFF_TIMER_TYPE, mIOTReadableMap.a("showOffTimerType", true));
            jSONObject.put(CommonTimerListActivity.PERIOD_TIMER_TYPE, mIOTReadableMap.a("showPeriodTimerType", true));
        } catch (JSONException e) {
            RnPluginLog.b("MIOTHost:  " + e.toString());
        }
        SHApplication.getGlobalHandler().post(new Runnable() {
            public void run() {
                FrameManager.b().k().startSetTimerListV4(MIOTHostModule.this.getCurrentActivity(), device.did, false, a2, a3, a4, a5, a7, a6, a8, jSONObject);
            }
        });
    }

    @ReactMethod
    public final void openPowerMultikeyPage(final String str, final String str2) {
        if (getDevice() != null) {
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    PowerMultikeyActivity.startActivity(MIOTHostModule.this.getCurrentActivity(), str, str2);
                }
            });
        }
    }

    @ReactMethod
    public final void launchCountDownWhenDevice(final boolean z, final ReadableMap readableMap) {
        final DeviceStat device = getDevice();
        if (device != null) {
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    MIOTReadableMap mIOTReadableMap = new MIOTReadableMap(readableMap);
                    String a2 = mIOTReadableMap.a("onMethod", "");
                    String a3 = mIOTReadableMap.a("offMethod", "");
                    String a4 = mIOTReadableMap.a("onParam", "");
                    String a5 = mIOTReadableMap.a("offParam", "");
                    String a6 = mIOTReadableMap.a("identify", "");
                    String a7 = mIOTReadableMap.a("displayName", "");
                    if (MIOTHostModule.this.getCurrentActivity() != null) {
                        FrameManager.b().k().startSetTimerCountDownV2(MIOTHostModule.this.getCurrentActivity(), device.did, a2, a4, a3, a5, z, a6, a7);
                    } else {
                        RnPluginLog.b("current activity is null...");
                    }
                }
            });
        }
    }

    @ReactMethod
    public final void openDevice(String str, String str2, ReadableMap readableMap, Callback callback) {
        Intent intent = new Intent();
        Bundle bundle = Arguments.toBundle(readableMap);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        XmPluginHostApi.instance().sendMessage(str, 1, intent, (DeviceStat) null, (MessageCallback) null);
    }

    @ReactMethod
    public final void openOneTimePassword(final String str, final int i, final int i2) {
        SHApplication.getGlobalHandler().post(new Runnable() {
            public void run() {
                FrameManager.b().k().openOneTimePasswordActivity(MIOTHostModule.this.getCurrentActivity(), str, i, i2);
            }
        });
    }

    @ReactMethod
    public final void openDeviceTimeZoneSettingPage(final ReadableMap readableMap) {
        final DeviceStat device = getDevice();
        if (device == null || getCurrentActivity() == null) {
            RnPluginLog.b("current device or current activity is null...");
        } else {
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    boolean b2 = readableMap != null ? MIOTUtils.b(readableMap, TimezoneActivity.KEY_SYNC_DEVICE) : false;
                    Intent intent = new Intent(MIOTHostModule.this.getCurrentActivity(), TimezoneActivity.class);
                    intent.putExtra("extra_device_did", device.did);
                    intent.putExtra(TimezoneActivity.KEY_SYNC_DEVICE, b2);
                    MIOTHostModule.this.getCurrentActivity().startActivityForResult(intent, 3);
                }
            });
        }
    }

    @ReactMethod
    public final void openBtGatewayActivity() {
        SHApplication.getGlobalHandler().post(new Runnable() {
            public void run() {
                RNRuntime.a().q();
            }
        });
    }

    @ReactMethod
    public final void openPhoneBluSettingActivity() {
        SHApplication.getGlobalHandler().post(new Runnable() {
            public void run() {
                RNRuntime.a().r();
            }
        });
    }

    @ReactMethod
    public final void openXiaoAiLearnPage(String str, String str2, String str3, String str4, String str5) {
        final String str6 = str;
        final String str7 = str2;
        final String str8 = str3;
        final String str9 = str4;
        final String str10 = str5;
        SHApplication.getGlobalHandler().post(new Runnable() {
            public void run() {
                RNRuntime.a().c(str6, str7, str8, str9, str10);
            }
        });
    }

    @ReactMethod
    public final void openConnectSucceedPage(final String str, final String str2) {
        SHApplication.getGlobalHandler().post(new Runnable() {
            public void run() {
                RNRuntime.a().d(str, str2);
            }
        });
    }

    @ReactMethod
    public final void loadRealDeviceConfig(String str, Callback callback) {
        String str2;
        String str3;
        PluginRecord d = CoreApi.a().d(str);
        WritableMap createMap = Arguments.createMap();
        if (!(d == null || d.c() == null)) {
            createMap.putString("deviceName", d.c().k());
            createMap.putString("deviceIconURL", d.c().p());
        }
        Locale I = CoreApi.a().I();
        if (I == null) {
            I = Locale.getDefault();
        }
        if (CoreApi.a().D()) {
            ServerBean F = CoreApi.a().F();
            StringBuilder sb = new StringBuilder();
            sb.append("https://");
            if (F != null) {
                str3 = F.f1530a + ".";
            } else {
                str3 = "";
            }
            sb.append(str3);
            sb.append("home.mi.com/views/deviceReset.html?model=");
            sb.append(str);
            sb.append("&locale=");
            sb.append(LocaleUtil.a(I));
            str2 = sb.toString();
        } else {
            str2 = "https://home.mi.com/views/deviceReset.html?model=" + str + "&locale=" + LocaleUtil.a(I);
        }
        createMap.putInt(LoggingSPCache.STORAGE_PRODUCTID, CoreApi.a().e(str));
        createMap.putString("resetPageURL", str2);
        callback.invoke(true, createMap);
    }

    @ReactMethod
    public final void loadCurrentCountryCode(Callback callback) {
        try {
            callback.invoke(new JSONObject(XmPluginHostApi.instance().getCurrentServer()).optString(Constant.KEY_COUNTRY_CODE));
        } catch (JSONException e) {
            callback.invoke(e.toString());
        }
    }

    @ReactMethod
    public final void getConnectedWifi(Callback callback) {
        boolean z;
        if (Build.VERSION.SDK_INT < 28) {
            z = true;
        } else {
            z = SHLocationManager.e();
        }
        if (z) {
            WifiManager wifiManager = (WifiManager) getReactApplicationContext().getApplicationContext().getSystemService("wifi");
            if (wifiManager.isWifiEnabled()) {
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo != null) {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putString("BSSID", connectionInfo.getBSSID());
                    createMap.putString("SSID", connectionInfo.getSSID());
                    callback.invoke(true, createMap);
                    return;
                }
                callback.invoke(false, "wifi info is null!");
                return;
            }
            callback.invoke(false, "wifi is disabled!");
            return;
        }
        if (getCurrentActivity() != null) {
            getCurrentActivity().startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
        }
        callback.invoke(false, "not open location server!");
    }

    @ReactMethod
    public final void getAppName(Callback callback) {
        String str = "";
        try {
            str = (String) getCurrentActivity().getApplicationContext().getPackageManager().getApplicationLabel(getCurrentActivity().getApplicationInfo());
        } catch (Exception e) {
            LogUtil.b(PluginRNActivity.TAG, e.toString());
        }
        callback.invoke(str);
    }

    @ReactMethod
    public final void getUserConfigs(int i, ReadableArray readableArray, final Callback callback) {
        DeviceStat device = getDevice();
        int size = readableArray.size();
        int[] iArr = new int[size];
        int i2 = 0;
        while (i2 < size) {
            try {
                iArr[i2] = readableArray.getInt(i2);
                i2++;
            } catch (Exception e) {
                callback.invoke(false, -1, e.toString());
                return;
            }
        }
        XmPluginHostApi.instance().getUserConfigV2((XmPluginPackage) null, device.model, i, iArr, new com.xiaomi.smarthome.device.api.Callback<Map<String, Object>>() {
            /* renamed from: a */
            public void onSuccess(Map<String, Object> map) {
                WritableMap createMap = Arguments.createMap();
                if (map != null) {
                    for (Map.Entry next : map.entrySet()) {
                        Object value = next.getValue();
                        if (value != null) {
                            createMap.putString((String) next.getKey(), value.toString());
                        } else {
                            createMap.putString((String) next.getKey(), "");
                        }
                    }
                }
                callback.invoke(true, createMap, "success");
            }

            public void onFailure(int i, String str) {
                callback.invoke(false, Integer.valueOf(i), str);
            }
        });
    }

    @ReactMethod
    public void colorsToImageBase64(String str, String str2, String str3, String str4, Callback callback) {
        Observable.just(str).map(new Function(str, str2, str3, str4) {
            private final /* synthetic */ String f$0;
            private final /* synthetic */ String f$1;
            private final /* synthetic */ String f$2;
            private final /* synthetic */ String f$3;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final Object apply(Object obj) {
                return MIOTHostModule.lambda$colorsToImageBase64$0(this.f$0, this.f$1, this.f$2, this.f$3, (String) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public final void accept(Object obj) {
                Callback.this.invoke(true, (String) obj);
            }
        }, new Consumer() {
            public final void accept(Object obj) {
                MIOTHostModule.lambda$colorsToImageBase64$2(Callback.this, (Throwable) obj);
            }
        });
    }

    static /* synthetic */ String lambda$colorsToImageBase64$0(String str, String str2, String str3, String str4, String str5) throws Exception {
        byte[] decode = Base64.decode(str, 2);
        int a2 = MIOTUtils.a(decode, 58);
        int a3 = MIOTUtils.a(decode, 62);
        int parseColor = Color.parseColor(str2);
        int parseColor2 = Color.parseColor(str3);
        int parseColor3 = Color.parseColor(str4);
        int[] iArr = new int[(a2 * a3)];
        int length = iArr.length;
        int i = 86;
        for (int i2 = 0; i2 < length; i2++) {
            switch (decode[i]) {
                case -1:
                    iArr[i2] = parseColor;
                    break;
                case 0:
                    iArr[i2] = parseColor2;
                    break;
                case 1:
                    iArr[i2] = parseColor3;
                    break;
            }
            i++;
        }
        Matrix matrix = new Matrix();
        matrix.setScale(-1.0f, 1.0f);
        Bitmap createBitmap = Bitmap.createBitmap(iArr, a2, a3, Bitmap.Config.ARGB_8888);
        Bitmap createBitmap2 = Bitmap.createBitmap(createBitmap, 0, 0, a2, a3, matrix, true);
        String a4 = MIOTUtils.a(createBitmap2, 100);
        createBitmap.recycle();
        createBitmap2.recycle();
        return a4;
    }

    static /* synthetic */ void lambda$colorsToImageBase64$2(Callback callback, Throwable th) throws Exception {
        LogUtil.b("MIOTHostModule", th.toString());
        callback.invoke(false, th.toString());
    }

    @ReactMethod
    public void pointsToImageBase64(int i, int i2, String str, String str2, Callback callback) {
        Observable.just(str).map(new Function(i, i2, str2) {
            private final /* synthetic */ int f$0;
            private final /* synthetic */ int f$1;
            private final /* synthetic */ String f$2;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final Object apply(Object obj) {
                return MIOTHostModule.lambda$pointsToImageBase64$3(this.f$0, this.f$1, this.f$2, (String) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public final void accept(Object obj) {
                Callback.this.invoke(true, (String) obj);
            }
        }, new Consumer() {
            public final void accept(Object obj) {
                MIOTHostModule.lambda$pointsToImageBase64$5(Callback.this, (Throwable) obj);
            }
        });
    }

    static /* synthetic */ String lambda$pointsToImageBase64$3(int i, int i2, String str, String str2) throws Exception {
        RnPluginLog.a("pointsToImageBase64:  width==" + i + "   height==" + i2);
        JSONObject jSONObject = new JSONObject(str);
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, Integer.valueOf(Color.parseColor(jSONObject.getString(next))));
        }
        String[] split = str2.split(",");
        int[] iArr = new int[(i * i2)];
        int length = iArr.length;
        for (int i3 = 0; i3 < length; i3++) {
            iArr[i3] = ((Integer) hashMap.get(split[i3])).intValue();
        }
        Matrix matrix = new Matrix();
        matrix.setScale(1.0f, -1.0f);
        Bitmap createBitmap = Bitmap.createBitmap(iArr, i, i2, Bitmap.Config.ARGB_8888);
        Bitmap createBitmap2 = Bitmap.createBitmap(createBitmap, 0, 0, i, i2, matrix, true);
        String a2 = MIOTUtils.a(createBitmap2, 100);
        createBitmap.recycle();
        createBitmap2.recycle();
        return a2;
    }

    static /* synthetic */ void lambda$pointsToImageBase64$5(Callback callback, Throwable th) throws Exception {
        th.printStackTrace();
        callback.invoke(false, th.toString());
    }

    private boolean isXiaomiPhone() {
        String lowerCase = Build.BRAND.toLowerCase();
        if (!TextUtils.isEmpty(lowerCase)) {
            return lowerCase.contains(UserMode.f23179a);
        }
        return false;
    }

    @ReactMethod
    public void phoneHasNfc(Callback callback) {
        if (callback != null) {
            if (getCurrentActivity() == null) {
                callback.invoke(false, "current activity is null...");
            }
            NfcAdapter defaultAdapter = ((NfcManager) getCurrentActivity().getSystemService(AnalyticManager.CATEGORY_NFC)).getDefaultAdapter();
            WritableMap createMap = Arguments.createMap();
            if (defaultAdapter == null || !defaultAdapter.isEnabled()) {
                createMap.putBoolean("hasNfc", false);
                callback.invoke(true, createMap);
                return;
            }
            createMap.putBoolean("hasNfc", true);
            callback.invoke(true, createMap);
        }
    }

    @ReactMethod
    public void openMiPayPage(ReadableMap readableMap, Callback callback) {
        String format = String.format("https://tsmclient.miui.com?action=%1$s&type=%2$s&product_id=%3$s&source_channel=%4$s", new Object[]{MIOTUtils.a(readableMap, "action", "issue_mifare"), MIOTUtils.a(readableMap, "type", "1"), MIOTUtils.a(readableMap, "product_id", "66666-00211"), MIOTUtils.a(readableMap, "source_channel", PageUrl.e)});
        RnPluginLog.a("MIOTHost openMiPayPage  url=" + format);
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(format));
        WritableMap createMap = Arguments.createMap();
        if (getCurrentActivity() == null) {
            createMap.putBoolean("result", false);
            createMap.putBoolean("isSupportMiPay", isSupportMiPay());
            createMap.putString("detail", "current activity is null");
            callback.invoke(false, createMap);
        } else if (isSupportMiPay()) {
            getCurrentActivity().startActivity(intent);
            createMap.putBoolean("result", true);
            createMap.putBoolean("isSupportMiPay", true);
            createMap.putString("detail", "");
            callback.invoke(true, createMap);
        } else {
            createMap.putBoolean("result", false);
            createMap.putBoolean("isSupportMiPay", false);
            createMap.putString("detail", "");
            callback.invoke(false, createMap);
        }
    }

    private boolean isSupportMiPay() {
        try {
            if (getCurrentActivity().getPackageManager().getPackageInfo("com.miui.tsmclient", 0).versionCode >= 41) {
                return true;
            }
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            RnPluginLog.b(e.toString());
            return false;
        }
    }

    @ReactMethod
    public void loadOperatorsType(Callback callback) {
        if (getCurrentActivity() == null) {
            callback.invoke(false, "current activity is null ...");
            return;
        }
        TelephonyManager telephonyManager = (TelephonyManager) getCurrentActivity().getSystemService("phone");
        WritableMap createMap = Arguments.createMap();
        createMap.putString("name", telephonyManager.getSimOperatorName());
        createMap.putString("simOperator", telephonyManager.getSimOperator());
        createMap.putString(Constant.KEY_COUNTRY_CODE, telephonyManager.getSimCountryIso());
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putMap("1", createMap);
        callback.invoke(true, createMap2);
    }

    @ReactMethod
    public void openMeshDeviceGroupPage(final String str, final String str2, int i) {
        if (i == 1) {
            openMeshDeviceGroupPageV1(str, str2);
        } else {
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    if (MIOTHostModule.this.getCurrentActivity() == null) {
                        RnPluginLog.a("current activity is null...");
                        return;
                    }
                    DeviceStat deviceByDid = PluginHostApi.instance().getDeviceByDid(str2);
                    if (deviceByDid == null) {
                        RnPluginLog.a("current device is null...");
                        return;
                    }
                    Intent intent = new Intent(MIOTHostModule.this.getCurrentActivity(), LightGroupSettingV2Activity.class);
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(deviceByDid.did);
                    intent.putExtra(LightGroupSettingV2Activity.ARGS_KEY_DID_LIST, arrayList);
                    if ("add".equalsIgnoreCase(str)) {
                        intent.putExtra(AppConstants.O, true);
                    } else if ("edit".equalsIgnoreCase(str)) {
                        intent.putExtra(LightGroupSettingV2Activity.ARGS_KEY_EDIT_MODE, true);
                    }
                    MIOTHostModule.this.getCurrentActivity().startActivity(intent);
                }
            });
        }
    }

    private void openMeshDeviceGroupPageV1(final String str, final String str2) {
        SHApplication.getGlobalHandler().post(new Runnable() {
            public void run() {
                if (MIOTHostModule.this.getCurrentActivity() == null) {
                    RnPluginLog.a("current activity is null...");
                    return;
                }
                DeviceStat deviceByDid = PluginHostApi.instance().getDeviceByDid(str2);
                if (deviceByDid == null) {
                    RnPluginLog.a("current device is null...");
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("did", deviceByDid.did);
                String str = deviceByDid.model;
                if ("add".equalsIgnoreCase(str)) {
                    intent.setClass(MIOTHostModule.this.getCurrentActivity(), LightGroupSettingActivity.class);
                    str = XmPluginHostApi.instance().getLightDeviceGroupModel(deviceByDid.model);
                    intent.putExtra(LightGroupSettingActivity.ARGS_KEY_CREATE_MODE, true);
                    intent.putExtra(AppConstants.O, true);
                } else if ("edit".equalsIgnoreCase(str)) {
                    intent.setClass(MIOTHostModule.this.getCurrentActivity(), LightGroupManageActivity.class);
                }
                if (TextUtils.isEmpty(str)) {
                    RnPluginLog.e("openMeshDeviceGroupPage: groupModel is empty");
                }
                intent.putExtra(LightGroupSettingActivity.ARGS_KEY_GROUP_MODEL, str);
                MIOTHostModule.this.getCurrentActivity().startActivity(intent);
            }
        });
    }

    @ReactMethod
    public void openLightGroupUpgradePage() {
        if (getCurrentActivity() == null) {
            RnPluginLog.a("current activity is null...");
            return;
        }
        DeviceStat device = getDevice();
        if (device == null) {
            RnPluginLog.a("current device is null...");
            return;
        }
        Intent intent = new Intent(getCurrentActivity(), LightGroupManageActivity.class);
        intent.putExtra("did", device.did);
        intent.putExtra(LightGroupManageActivity.ARGS_KEY_UPGRADE_ONLY, true);
        getCurrentActivity().startActivity(intent);
    }

    @ReactMethod
    public void checkStoreSupportted(Callback callback) {
        if (callback != null) {
            boolean isChinaMainLand = XmPluginHostApi.instance().isChinaMainLand(getCurrentActivity().getApplicationContext());
            RnPluginLog.a("MIOTHostMudule  是否为国内服务器：  " + isChinaMainLand);
            callback.invoke(Boolean.valueOf(isChinaMainLand));
        }
    }

    @ReactMethod
    public void pointsScaleToImageBase64(int i, int i2, String str, String str2, int i3, Callback callback) {
        Observable.just(str).map(new Function(i, i2, str2, i3) {
            private final /* synthetic */ int f$0;
            private final /* synthetic */ int f$1;
            private final /* synthetic */ String f$2;
            private final /* synthetic */ int f$3;

            {
                this.f$0 = r1;
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final Object apply(Object obj) {
                return MIOTHostModule.lambda$pointsScaleToImageBase64$6(this.f$0, this.f$1, this.f$2, this.f$3, (String) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            public final void accept(Object obj) {
                Callback.this.invoke(true, (String) obj);
            }
        }, new Consumer() {
            public final void accept(Object obj) {
                MIOTHostModule.lambda$pointsScaleToImageBase64$8(Callback.this, (Throwable) obj);
            }
        });
    }

    static /* synthetic */ String lambda$pointsScaleToImageBase64$6(int i, int i2, String str, int i3, String str2) throws Exception {
        int i4 = i;
        int i5 = i2;
        String str3 = str;
        int i6 = i3;
        RnPluginLog.a("width==" + i4 + "   height==" + i5 + "  colorsMap=" + str3 + "  scale=" + i6);
        JSONObject jSONObject = new JSONObject(str3);
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, Integer.valueOf(Color.parseColor(jSONObject.getString(next))));
        }
        String[] split = str2.split(",");
        int i7 = i4 * i6;
        int[] iArr = new int[(i7 * i5 * i6)];
        int i8 = i4 * i5;
        for (int i9 = 0; i9 < i8; i9++) {
            String str4 = split[i9];
            Integer num = (Integer) hashMap.get(str4);
            if (num == null) {
                RnPluginLog.b("colorKey=" + str4 + " is null");
                num = 0;
            }
            if (i6 == 1) {
                iArr[i9] = num.intValue();
            } else {
                int i10 = i9 / i4;
                for (int i11 = 0; i11 < i6; i11++) {
                    int i12 = (i9 * i6) + ((i10 + i11) * i4 * i6);
                    for (int i13 = 0; i13 < i6; i13++) {
                        iArr[i12 + i13] = num.intValue();
                    }
                }
            }
        }
        Matrix matrix = new Matrix();
        matrix.setScale(1.0f, -1.0f);
        int i14 = i5 * i6;
        Bitmap createBitmap = Bitmap.createBitmap(iArr, i7, i14, Bitmap.Config.ARGB_8888);
        Bitmap createBitmap2 = Bitmap.createBitmap(createBitmap, 0, 0, i7, i14, matrix, true);
        String a2 = MIOTUtils.a(createBitmap2, 100);
        createBitmap.recycle();
        createBitmap2.recycle();
        return a2;
    }

    static /* synthetic */ void lambda$pointsScaleToImageBase64$8(Callback callback, Throwable th) throws Exception {
        th.printStackTrace();
        callback.invoke(false, th.toString());
    }

    @ReactMethod
    public void openPluginRecommendScene(final String str, final int i) {
        if (getCurrentActivity() == null) {
            RnPluginLog.b("current activity is null...");
        } else {
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    Intent intent = new Intent(MIOTHostModule.this.getCurrentActivity(), PluginRecommendSceneActivity.class);
                    intent.putExtra("did", str);
                    intent.putExtra("sr_id", i);
                    MIOTHostModule.this.getCurrentActivity().startActivity(intent);
                }
            });
        }
    }

    @ReactMethod
    public void getSystemTimezoneNameWithCallback(Callback callback) {
        if (callback != null) {
            WritableMap createMap = Arguments.createMap();
            createMap.putString("timeZone", TimeZone.getDefault().getID());
            callback.invoke(true, createMap);
        }
    }

    @ReactMethod
    public void pageShouldAdapterSoftKeyboard(final boolean z, Callback callback) {
        if (callback != null) {
            if (getCurrentActivity() == null) {
                callback.invoke(false, "current activity is null...");
                return;
            }
            RnPluginLog.a("pageShouldFixSoftKeyboard-->pageShouldFix: " + z);
            getCurrentActivity().runOnUiThread(new Runnable() {
                public void run() {
                    if (z) {
                        MIOTHostModule.this.getCurrentActivity().getWindow().setSoftInputMode(16);
                    } else {
                        MIOTHostModule.this.getCurrentActivity().getWindow().setSoftInputMode(32);
                    }
                    AndroidBug5497Workaround.a().a(z);
                }
            });
            callback.invoke(true, true);
        }
    }

    @ReactMethod
    public void refreshDeviceList(final Callback callback) {
        if (callback == null) {
            RnPluginLog.b("callback is null...");
            return;
        }
        final boolean[] zArr = {false};
        XmPluginHostApi.instance().updateDeviceList(new com.xiaomi.smarthome.device.api.Callback<Void>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
                if (!zArr[0]) {
                    zArr[0] = true;
                    try {
                        callback.invoke(true, true);
                    } catch (Exception e) {
                        RnPluginLog.b(e.toString());
                    }
                }
            }

            public void onFailure(int i, String str) {
                if (!zArr[0]) {
                    zArr[0] = true;
                    try {
                        Callback callback = callback;
                        callback.invoke("code: " + i + "   errorInfo: " + str);
                    } catch (Exception e) {
                        RnPluginLog.b(e.toString());
                    }
                }
            }
        });
    }

    @ReactMethod
    public final void openPluginPage(String str, String str2, ReadableMap readableMap, Callback callback) {
        if (callback != null) {
            if (TextUtils.isEmpty(str)) {
                callback.invoke(false, "did is empty!");
            } else if (XmPluginHostApi.instance().getDeviceByDid(str) == null) {
                callback.invoke(false, "cannot get device info from did, did is " + str);
            } else {
                Intent intent = new Intent();
                if (TextUtils.isEmpty(str2)) {
                    str2 = "";
                }
                intent.putExtra("pageName", str2);
                intent.putExtra("pageParams", MIOTUtils.a(readableMap));
                XmPluginHostApi.instance().sendMessage(str, 1, intent, (DeviceStat) null, (MessageCallback) null);
                callback.invoke(true, true);
            }
        }
    }

    /* access modifiers changed from: package-private */
    @ReactMethod
    public final void openDeviceNetworkInfoPage() {
        DeviceStat device = getDevice();
        if (device == null) {
            RnPluginLog.b("openDeviceNetworkInfoPage: cannot get device info");
            return;
        }
        String str = device.did;
        if (TextUtils.isEmpty(str)) {
            RnPluginLog.b("openDeviceNetworkInfoPage: did is empty");
            return;
        }
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            RnPluginLog.b("openDeviceNetworkInfoPage: getCurrentActivity is null");
            return;
        }
        Intent intent = new Intent(currentActivity, DeviceNetworkInfoActivity.class);
        intent.putExtra("did", str);
        currentActivity.startActivity(intent);
    }

    @ReactMethod
    public void convertDateToCron(ReadableMap readableMap, Callback callback) {
        if (callback != null) {
            CorntabUtils.CorntabParam corntabParam = new CorntabUtils.CorntabParam();
            corntabParam.c = readableMap.getInt("hour");
            corntabParam.b = readableMap.getInt("minute");
            int i = readableMap.getInt("repeatType");
            convertJsRepeatToOnTime(i, corntabParam);
            if (i == 4) {
                ReadableArray array = readableMap.getArray(DTransferConstants.J);
                int size = array.size();
                int length = corntabParam.g.length;
                for (int i2 = 0; i2 < size; i2++) {
                    if (i2 < length) {
                        corntabParam.a(i2, array.getBoolean(i2));
                    }
                }
                corntabParam.h = "";
            }
            if (i == 0) {
                checkTimeIfAddDay(corntabParam);
            }
            callback.invoke(true, RnCallbackMapUtil.a((Object) CorntabUtils.a(CorntabUtils.a(CorntabUtils.b(), corntabParam))));
        }
    }

    private void checkTimeIfAddDay(CorntabUtils.CorntabParam corntabParam) {
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        if ((instance.get(11) * 60) + instance.get(12) >= (corntabParam.c * 60) + corntabParam.b) {
            instance2.setTimeInMillis(instance.getTimeInMillis() + 86400000);
            corntabParam.d = instance2.get(5);
            corntabParam.e = instance2.get(2) + 1;
            return;
        }
        corntabParam.d = instance2.get(5);
        corntabParam.e = instance2.get(2) + 1;
    }

    private void convertJsRepeatToOnTime(int i, CorntabUtils.CorntabParam corntabParam) {
        switch (i) {
            case 0:
                corntabParam.a(0);
                corntabParam.h = "";
                return;
            case 1:
                corntabParam.a(127);
                corntabParam.h = "";
                return;
            case 2:
                corntabParam.a(127);
                corntabParam.h = CorntabUtils.p;
                return;
            case 3:
                corntabParam.a(127);
                corntabParam.h = CorntabUtils.q;
                return;
            default:
                return;
        }
    }

    private int convertNativeRepeatToJsRepeat(CorntabUtils.CorntabParam corntabParam) {
        switch (corntabParam.d()) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 4:
                return 4;
            case 5:
                return 2;
            case 6:
                return 3;
            default:
                return -1;
        }
    }

    @ReactMethod
    public void convertCronToDate(ReadableMap readableMap, Callback callback) {
        if (callback == null) {
            return;
        }
        if (readableMap == null) {
            callback.invoke(false, RnCallbackMapUtil.a(-1, "params is null"));
            return;
        }
        String string = readableMap.getString("cron");
        if (TextUtils.isEmpty(string)) {
            callback.invoke(false, RnCallbackMapUtil.a(-1, "cron is empty"));
            return;
        }
        String string2 = readableMap.getString("on_filter");
        CorntabUtils.CorntabParam b = CorntabUtils.b(TimeZone.getDefault(), CorntabUtils.b(), CorntabUtils.a(string));
        if (!TextUtils.isEmpty(string2)) {
            b.h = string2;
        }
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("hour", b.c);
        createMap.putInt("minute", b.b);
        createMap.putInt("repeatType", convertNativeRepeatToJsRepeat(b));
        createMap.putString("repeatStr", b.c((Context) getCurrentActivity()));
        createMap.putString("timerOnDetail", getTimerOnDetail(b));
        WritableArray createArray = Arguments.createArray();
        for (boolean pushBoolean : b.g) {
            createArray.pushBoolean(pushBoolean);
        }
        createMap.putArray(DTransferConstants.J, createArray);
        callback.invoke(true, RnCallbackMapUtil.a(0, (Object) createMap));
    }

    private String getTimerOnDetail(CorntabUtils.CorntabParam corntabParam) {
        StringBuilder sb = new StringBuilder();
        if (corntabParam.d() == 0) {
            sb.append(corntabParam.c((Context) getCurrentActivity()));
            sb.append(" ");
        }
        sb.append(PlugTimer.a(corntabParam.c));
        sb.append(":");
        sb.append(PlugTimer.a(corntabParam.b));
        return sb.toString();
    }
}
