package com.xiaomi.smarthome.miio.camera.cloudstorage.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;
import com.coloros.mcssdk.mode.CommandMessage;
import com.mijia.model.alarm.AlarmNetUtils;
import com.xiaomi.mistatistic.sdk.BaseService;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.device.api.ICloudDataCallback;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoWebActivity;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoUserStatus;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.DailyList;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.ICloudVideoCallback;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.StatsRecord;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.StatsRecord2;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.FileDownloadAndDecryptTask;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CloudVideoNetUtils {
    public static final String ALL_CAMERA_STATUS = "/miot/camera/app/v1/vip/cloud/support";
    public static final String CLOUD_COMMON_VIP_STATUS = "/common/app/vip/status";
    public static final String CLOUD_DEDUCT_ORDERS = "/miot/camera/app/v1/vip/deductOrders";
    public static final String CLOUD_SETTING_CAPACITY = "/miot/camera/app/v1/capacity";
    public static final String CLOUD_SETTING_STATUS = "/miot/camera/app/v1/vip/status";
    public static final String CLOUD_SETTING_USAGE_GET = "/miot/camera/app/v1/get/cloudSwitch";
    public static final String CLOUD_SETTING_USAGE_PUT = "/miot/camera/app/v1/put/cloudSwitch";
    public static final String CLOUD_VIDEO_TIPS = "/miot/camera/app/v1/vip/tips";
    public static final int FAIL_GENERAL = -90001;
    public static final int FAIL_IN_CODE = -90003;
    public static final int FAIL_IN_SUCCESS = -90002;
    public static final int FAIL_IN_SUCCESS_NO_DATA = -90004;
    public static final String IMAGE_SNAPSHOT_FILE = "/miot/camera/app/v1/img";
    public static final String IS_NEW_USER = "/miot/camera/app/v1/vip/newUser";
    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    public static final String RELATIVE_VIDEO_DATES = "/miot/camera/app/v1/all";
    public static final String RELATIVE_VIDEO_DATES_2 = "/miot/camera/app/v1/cloud/file/exist";
    public static final String REQUEST_MIPAY_SIGN_V2 = "/miot/camera/app/v2/vip/android/mipaySign";
    public static final String REQUEST_VIP_STATUS = "/miot/camera/app/v1/vip/status";
    private static final String TAG = "CloudVideoNetUtils";
    public static final String URL_HOST = "api.io.mi.com";
    public static final String URL_HOST_PREFIX_BUSINESS = "business.smartcamera.";
    public static final String URL_HOST_PREFIX_CAMERA = "camera.";
    public static final String URL_HOST_PREFIX_PROCESSOR = "processor.smartcamera.";
    public static final String URL_SCHEME = "https://";
    public static final String VIDEO_FILE = "/miot/camera/app/v1/mp4";
    public static final String VIDEO_PLAYLIST = "/miot/camera/app/v1/cloud/playlist";
    public static final String VIDEO_PLAYLIST_LIMIT = "/miot/camera/app/v1/cloud/playlist/limit";
    public static final String VIDEO_PLAY_FILE = "/miot/camera/app/v1/cloud/m3u8";
    private static CloudVideoNetUtils sInstance;
    private OkHttpClient client = new OkHttpClient();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private CloudVideoNetUtils() {
    }

    public static CloudVideoNetUtils getInstance() {
        if (sInstance == null) {
            synchronized (CloudVideoNetUtils.class) {
                if (sInstance == null) {
                    sInstance = new CloudVideoNetUtils();
                }
            }
        }
        return sInstance;
    }

    public long toGMT8TimeZone(long j) {
        Calendar instance = Calendar.getInstance(TimeZone.getDefault());
        instance.setTimeInMillis(j);
        instance.add(14, -(instance.get(15) + instance.get(16)));
        Calendar instance2 = Calendar.getInstance(TimeZone.getTimeZone("GMT+08"));
        instance2.setTimeInMillis(instance.getTimeInMillis());
        instance2.add(14, instance2.get(15) + instance2.get(16));
        return instance2.getTimeInMillis();
    }

    public long toLocalTimeZone(long j) {
        Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("GMT+08"));
        instance.setTimeInMillis(j);
        instance.add(14, -(instance.get(15) + instance.get(16)));
        Calendar instance2 = Calendar.getInstance(TimeZone.getDefault());
        instance2.setTimeInMillis(instance.getTimeInMillis());
        instance2.add(14, instance2.get(15) + instance2.get(16));
        return instance2.getTimeInMillis();
    }

    public boolean isNetworkConnected(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context == null || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) == null) {
            return false;
        }
        return activeNetworkInfo.isAvailable();
    }

    public boolean isWifiConnected(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context == null || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) == null) {
            return false;
        }
        if (activeNetworkInfo.getType() == 1 || activeNetworkInfo.getType() == 9) {
            return true;
        }
        return false;
    }

    public void openCloudVideoBuyPage(Context context, String str) {
        Intent intent = new Intent(context, CloudVideoWebActivity.class);
        intent.putExtra("title", context.getString(R.string.buy_cloud_service));
        intent.putExtra("url", CloudVideoWebActivity.REQUEST_CLOUD_BUY_mipay_sr62m5p7ds_v2);
        intent.putExtra("did", str);
        context.startActivity(intent);
    }

    public void openCloudVideoManagePage(Context context, String str) {
        Intent intent = new Intent(context, CloudVideoWebActivity.class);
        intent.putExtra("title", context.getString(R.string.cs_my_service));
        intent.putExtra("url", CloudVideoWebActivity.REQUEST_CLOUD_MANAGEMENT);
        intent.putExtra("did", str);
        context.startActivity(intent);
    }

    public void getVideoDates(Context context, String str, final ICloudVideoCallback<List<StatsRecord>> iCloudVideoCallback) {
        if (TextUtils.isEmpty(str) || context == null) {
            LogUtil.b(TAG, "params is null");
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", str));
        Context context2 = context;
        CoreApi.a().a(context2, new NetRequest.Builder().c("business.smartcamera.").a("GET").b(RELATIVE_VIDEO_DATES).b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, new AsyncCallback<JSONObject, Error>() {
            public void onSuccess(JSONObject jSONObject) {
                if (iCloudVideoCallback == null) {
                    return;
                }
                if (jSONObject == null) {
                    iCloudVideoCallback.onCloudVideoFailed(-90002, "result is null");
                } else if (jSONObject.optInt("code", -90003) == 0) {
                    JSONObject optJSONObject = jSONObject.optJSONObject("data");
                    if (optJSONObject != null) {
                        JSONArray optJSONArray = optJSONObject.optJSONArray("statsRecords");
                        if (optJSONArray == null || optJSONArray.length() <= 0) {
                            iCloudVideoCallback.onCloudVideoFailed(-90002, "statsRecords is empty");
                            return;
                        }
                        ArrayList arrayList = new ArrayList();
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            StatsRecord statsRecord = new StatsRecord();
                            statsRecord.date = optJSONArray.optJSONObject(i).optString("date", (String) null);
                            statsRecord.count = optJSONArray.optJSONObject(i).optInt("count", 0);
                            arrayList.add(statsRecord);
                        }
                        if (iCloudVideoCallback == null || arrayList.size() <= 0) {
                            iCloudVideoCallback.onCloudVideoFailed(-90002, "list is empty");
                        } else {
                            iCloudVideoCallback.onCloudVideoSuccess(arrayList, (Object) null);
                        }
                    } else {
                        iCloudVideoCallback.onCloudVideoFailed(-90002, "data is null");
                    }
                } else {
                    iCloudVideoCallback.onCloudVideoFailed(-90002, "code is not 0");
                }
            }

            public void onFailure(Error error) {
                if (iCloudVideoCallback == null) {
                    return;
                }
                if (error != null) {
                    ICloudVideoCallback iCloudVideoCallback = iCloudVideoCallback;
                    int a2 = error.a();
                    iCloudVideoCallback.onCloudVideoFailed(a2, "" + error.b());
                    return;
                }
                iCloudVideoCallback.onCloudVideoFailed(-90001, "FAIL_GENERAL");
            }
        });
    }

    public void getVideoDatesSerial(Context context, String str, final ICloudVideoCallback<List<StatsRecord2>> iCloudVideoCallback) {
        if (TextUtils.isEmpty(str) || context == null) {
            LogUtil.b(TAG, "params is null");
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", str));
        Context context2 = context;
        CoreApi.a().a(context2, new NetRequest.Builder().c("business.smartcamera.").a("GET").b(RELATIVE_VIDEO_DATES_2).b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, new AsyncCallback<JSONObject, Error>() {
            public void onSuccess(JSONObject jSONObject) {
                if (iCloudVideoCallback == null) {
                    return;
                }
                if (jSONObject == null) {
                    iCloudVideoCallback.onCloudVideoFailed(-90002, "result is null");
                } else if (jSONObject.optInt("code", -90003) == 0) {
                    JSONObject optJSONObject = jSONObject.optJSONObject("data");
                    if (optJSONObject != null) {
                        JSONArray optJSONArray = optJSONObject.optJSONArray("filesExist");
                        if (optJSONArray == null || optJSONArray.length() <= 0) {
                            iCloudVideoCallback.onCloudVideoFailed(-90002, "FilesExist is empty");
                            return;
                        }
                        ArrayList arrayList = new ArrayList();
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            StatsRecord2 statsRecord2 = new StatsRecord2();
                            statsRecord2.timeStamp = optJSONArray.optJSONObject(i).optLong(BaseService.TIME_STAMP, 0);
                            statsRecord2.isExist = optJSONArray.optJSONObject(i).optBoolean("exist", false);
                            arrayList.add(statsRecord2);
                        }
                        if (iCloudVideoCallback == null || arrayList.size() <= 0) {
                            iCloudVideoCallback.onCloudVideoFailed(-90002, "list is empty");
                        } else {
                            iCloudVideoCallback.onCloudVideoSuccess(arrayList, (Object) null);
                        }
                    } else {
                        iCloudVideoCallback.onCloudVideoFailed(-90002, "data is null");
                    }
                } else {
                    iCloudVideoCallback.onCloudVideoFailed(-90002, "code is not 0");
                }
            }

            public void onFailure(Error error) {
                if (iCloudVideoCallback == null) {
                    return;
                }
                if (error != null) {
                    ICloudVideoCallback iCloudVideoCallback = iCloudVideoCallback;
                    int a2 = error.a();
                    iCloudVideoCallback.onCloudVideoFailed(a2, "" + error.b());
                    return;
                }
                iCloudVideoCallback.onCloudVideoFailed(-90001, "FAIL_GENERAL");
            }
        });
    }

    public void getVideoDailyList(Context context, String str, final ICloudVideoCallback<List<DailyList>> iCloudVideoCallback) {
        if (context != null && !TextUtils.isEmpty(str)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", str));
            Context context2 = context;
            CoreApi.a().a(context2, new NetRequest.Builder().c("business.smartcamera.").a("GET").b(VIDEO_PLAYLIST).b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, new AsyncCallback<JSONObject, Error>() {
                public void onSuccess(JSONObject jSONObject) {
                    if (iCloudVideoCallback != null) {
                        if (jSONObject != null) {
                            int optInt = jSONObject.optInt("code", -90003);
                            if (optInt == 0) {
                                JSONObject optJSONObject = jSONObject.optJSONObject("data");
                                if (optJSONObject != null) {
                                    LogUtil.a(CloudVideoNetUtils.TAG, "data:" + optJSONObject.toString());
                                    JSONArray optJSONArray = optJSONObject.optJSONArray("playUnits");
                                    String optString = optJSONObject.optString("date");
                                    ArrayList arrayList = new ArrayList();
                                    if (optJSONArray == null || optJSONArray.length() <= 0) {
                                        iCloudVideoCallback.onCloudVideoFailed(-90002, "playUnits error");
                                        return;
                                    }
                                    for (int i = 0; i < optJSONArray.length(); i++) {
                                        DailyList dailyList = new DailyList();
                                        JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                                        if (optJSONObject2 != null) {
                                            dailyList.duration = optJSONObject2.optLong("duration");
                                            dailyList.expireTime = optJSONObject2.optLong(MibiConstants.eP);
                                            dailyList.createTime = optJSONObject2.optLong("createTime");
                                            dailyList.imgStoreId = optJSONObject2.optString("imgStoreId");
                                            dailyList.fileId = optJSONObject2.optString("fileId");
                                            String optString2 = optJSONObject2.optString(CommandMessage.TYPE_TAGS);
                                            if (TextUtils.isEmpty(optString2) || !optString2.contains("people")) {
                                                dailyList.isHuman = false;
                                            } else {
                                                dailyList.isHuman = true;
                                            }
                                            arrayList.add(dailyList);
                                        }
                                    }
                                    if (arrayList.size() > 0) {
                                        Collections.sort(arrayList, new Comparator<DailyList>() {
                                            public int compare(DailyList dailyList, DailyList dailyList2) {
                                                return dailyList.createTime > dailyList2.createTime ? 1 : -1;
                                            }
                                        });
                                        iCloudVideoCallback.onCloudVideoSuccess(arrayList, optString);
                                        return;
                                    }
                                    iCloudVideoCallback.onCloudVideoFailed(-90002, "list data error");
                                    return;
                                }
                                iCloudVideoCallback.onCloudVideoFailed(-90002, "data is null");
                                return;
                            }
                            iCloudVideoCallback.onCloudVideoFailed(optInt, "result is fail");
                            return;
                        }
                        iCloudVideoCallback.onCloudVideoFailed(-90002, "result is null");
                    }
                }

                public void onFailure(Error error) {
                    if (iCloudVideoCallback == null) {
                        return;
                    }
                    if (error != null) {
                        ICloudVideoCallback iCloudVideoCallback = iCloudVideoCallback;
                        int a2 = error.a();
                        iCloudVideoCallback.onCloudVideoFailed(a2, "" + error.b());
                        return;
                    }
                    iCloudVideoCallback.onCloudVideoFailed(-90001, "FAIL_GENERAL");
                }
            });
        }
    }

    public void getVideoDailyListLimit(Context context, String str, final ICloudVideoCallback<List<DailyList>> iCloudVideoCallback) {
        if (context != null && !TextUtils.isEmpty(str)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", str));
            Context context2 = context;
            CoreApi.a().a(context2, new NetRequest.Builder().c("business.smartcamera.").a("GET").b(VIDEO_PLAYLIST_LIMIT).b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, new AsyncCallback<JSONObject, Error>() {
                public void onSuccess(JSONObject jSONObject) {
                    if (iCloudVideoCallback != null) {
                        if (jSONObject != null) {
                            int optInt = jSONObject.optInt("code", -90003);
                            if (optInt == 0) {
                                JSONObject optJSONObject = jSONObject.optJSONObject("data");
                                if (optJSONObject != null) {
                                    JSONArray optJSONArray = optJSONObject.optJSONArray("playUnits");
                                    optJSONObject.optString("date");
                                    Long valueOf = Long.valueOf(optJSONObject.optLong(BaseService.TIME_STAMP, 0));
                                    ArrayList arrayList = new ArrayList();
                                    if (optJSONArray == null || optJSONArray.length() <= 0) {
                                        iCloudVideoCallback.onCloudVideoFailed(CloudVideoNetUtils.FAIL_IN_SUCCESS_NO_DATA, "playUnits error");
                                        return;
                                    }
                                    LogUtil.a(CloudVideoNetUtils.TAG, "playUnits:" + optJSONArray.toString());
                                    for (int i = 0; i < optJSONArray.length(); i++) {
                                        JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                                        if (optJSONObject2 != null) {
                                            DailyList dailyList = new DailyList();
                                            dailyList.duration = optJSONObject2.optLong("duration");
                                            dailyList.expireTime = optJSONObject2.optLong(MibiConstants.eP);
                                            dailyList.createTime = optJSONObject2.optLong("createTime");
                                            dailyList.imgStoreId = optJSONObject2.optString("imgStoreId");
                                            dailyList.fileId = optJSONObject2.optString("fileId");
                                            String optString = optJSONObject2.optString(CommandMessage.TYPE_TAGS);
                                            if (TextUtils.isEmpty(optString) || !optString.contains("people")) {
                                                dailyList.isHuman = false;
                                            } else {
                                                dailyList.isHuman = true;
                                            }
                                            arrayList.add(dailyList);
                                        }
                                    }
                                    if (arrayList.size() > 0) {
                                        iCloudVideoCallback.onCloudVideoSuccess(arrayList, valueOf);
                                    } else {
                                        iCloudVideoCallback.onCloudVideoFailed(-90002, "list data error");
                                    }
                                } else {
                                    iCloudVideoCallback.onCloudVideoFailed(-90002, "data is null");
                                }
                            } else {
                                iCloudVideoCallback.onCloudVideoFailed(optInt, "result is fail");
                            }
                        } else {
                            iCloudVideoCallback.onCloudVideoFailed(-90002, "result is null");
                        }
                    }
                }

                public void onFailure(Error error) {
                    if (iCloudVideoCallback == null) {
                        return;
                    }
                    if (error != null) {
                        LogUtil.b(CloudVideoNetUtils.TAG, "error:" + error.a() + "" + error.b());
                        ICloudVideoCallback iCloudVideoCallback = iCloudVideoCallback;
                        int a2 = error.a();
                        iCloudVideoCallback.onCloudVideoFailed(a2, "" + error.b());
                        return;
                    }
                    iCloudVideoCallback.onCloudVideoFailed(-90001, "FAIL_GENERAL");
                }
            });
        }
    }

    public void loadAllCameraCloudStorageInfo(String str, final ICloudVideoCallback<JSONObject> iCloudVideoCallback) {
        if (!TextUtils.isEmpty(str)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", str));
            CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().c("business.smartcamera.").a("GET").b(ALL_CAMERA_STATUS).b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, new AsyncCallback<JSONObject, Error>() {
                public void onSuccess(JSONObject jSONObject) {
                    if (iCloudVideoCallback != null) {
                        iCloudVideoCallback.onCloudVideoSuccess(jSONObject, (Object) null);
                    }
                }

                public void onFailure(Error error) {
                    if (iCloudVideoCallback == null) {
                        return;
                    }
                    if (error != null) {
                        ICloudVideoCallback iCloudVideoCallback = iCloudVideoCallback;
                        int a2 = error.a();
                        iCloudVideoCallback.onCloudVideoFailed(a2, "" + error.b());
                        return;
                    }
                    iCloudVideoCallback.onCloudVideoFailed(-90001, "FAIL_GENERAL");
                }
            });
        } else if (iCloudVideoCallback != null) {
            iCloudVideoCallback.onCloudVideoFailed(-9003, "params is null");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x00b4 A[Catch:{ JSONException -> 0x00f4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00eb A[Catch:{ JSONException -> 0x00f4 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getPlayFileUrl(java.lang.String r4, java.lang.String r5, java.lang.String r6) {
        /*
            r3 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            r1 = 0
            if (r0 != 0) goto L_0x010b
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            if (r0 == 0) goto L_0x000f
            goto L_0x010b
        L_0x000f:
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r2 = "did"
            r0.put(r2, r4)     // Catch:{ JSONException -> 0x00f4 }
            java.lang.String r2 = "fileId"
            r0.put(r2, r5)     // Catch:{ JSONException -> 0x00f4 }
            java.lang.String r5 = "videoCodec"
            r0.put(r5, r6)     // Catch:{ JSONException -> 0x00f4 }
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r5 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()     // Catch:{ JSONException -> 0x00f4 }
            com.xiaomi.smarthome.device.Device r4 = r5.b((java.lang.String) r4)     // Catch:{ JSONException -> 0x00f4 }
            if (r4 == 0) goto L_0x0034
            java.lang.String r5 = "deviceModel"
            java.lang.String r4 = r4.model     // Catch:{ JSONException -> 0x00f4 }
            r0.put(r5, r4)     // Catch:{ JSONException -> 0x00f4 }
        L_0x0034:
            com.xiaomi.smarthome.frame.core.CoreApi r4 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ JSONException -> 0x00f4 }
            java.util.Locale r4 = r4.I()     // Catch:{ JSONException -> 0x00f4 }
            if (r4 == 0) goto L_0x0048
            java.lang.String r5 = "region"
            java.lang.String r4 = r4.getCountry()     // Catch:{ JSONException -> 0x00f4 }
            r0.put(r5, r4)     // Catch:{ JSONException -> 0x00f4 }
            goto L_0x0055
        L_0x0048:
            java.lang.String r4 = "region"
            java.util.Locale r5 = java.util.Locale.getDefault()     // Catch:{ JSONException -> 0x00f4 }
            java.lang.String r5 = r5.getCountry()     // Catch:{ JSONException -> 0x00f4 }
            r0.put(r4, r5)     // Catch:{ JSONException -> 0x00f4 }
        L_0x0055:
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ JSONException -> 0x00f4 }
            r4.<init>()     // Catch:{ JSONException -> 0x00f4 }
            com.xiaomi.smarthome.core.entity.net.KeyValuePair r5 = new com.xiaomi.smarthome.core.entity.net.KeyValuePair     // Catch:{ JSONException -> 0x00f4 }
            java.lang.String r6 = "data"
            java.lang.String r0 = r0.toString()     // Catch:{ JSONException -> 0x00f4 }
            r5.<init>(r6, r0)     // Catch:{ JSONException -> 0x00f4 }
            r4.add(r5)     // Catch:{ JSONException -> 0x00f4 }
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r5 = new com.xiaomi.smarthome.core.entity.net.NetRequest$Builder     // Catch:{ JSONException -> 0x00f4 }
            r5.<init>()     // Catch:{ JSONException -> 0x00f4 }
            java.lang.String r6 = "business.smartcamera."
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r5 = r5.c((java.lang.String) r6)     // Catch:{ JSONException -> 0x00f4 }
            java.lang.String r6 = "GET"
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r5 = r5.a((java.lang.String) r6)     // Catch:{ JSONException -> 0x00f4 }
            java.lang.String r6 = "/miot/camera/app/v1/cloud/m3u8"
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r5 = r5.b((java.lang.String) r6)     // Catch:{ JSONException -> 0x00f4 }
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r4 = r5.b((java.util.List<com.xiaomi.smarthome.core.entity.net.KeyValuePair>) r4)     // Catch:{ JSONException -> 0x00f4 }
            com.xiaomi.smarthome.core.entity.net.NetRequest r4 = r4.a()     // Catch:{ JSONException -> 0x00f4 }
            java.lang.String r5 = ""
            com.xiaomi.smarthome.frame.core.CoreApi r6 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ JSONException -> 0x00f4 }
            com.xiaomi.smarthome.frame.server_compact.ServerBean r6 = r6.F()     // Catch:{ JSONException -> 0x00f4 }
            if (r6 == 0) goto L_0x00ac
            boolean r0 = com.xiaomi.smarthome.frame.server_compact.ServerCompact.c((com.xiaomi.smarthome.frame.server_compact.ServerBean) r6)     // Catch:{ JSONException -> 0x00f4 }
            if (r0 == 0) goto L_0x009a
            goto L_0x00ac
        L_0x009a:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00f4 }
            r0.<init>()     // Catch:{ JSONException -> 0x00f4 }
            r0.append(r5)     // Catch:{ JSONException -> 0x00f4 }
            java.lang.String r5 = r6.f1530a     // Catch:{ JSONException -> 0x00f4 }
            r0.append(r5)     // Catch:{ JSONException -> 0x00f4 }
            java.lang.String r5 = r0.toString()     // Catch:{ JSONException -> 0x00f4 }
            goto L_0x00ae
        L_0x00ac:
            java.lang.String r5 = ""
        L_0x00ae:
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ JSONException -> 0x00f4 }
            if (r6 != 0) goto L_0x00c5
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00f4 }
            r6.<init>()     // Catch:{ JSONException -> 0x00f4 }
            r6.append(r5)     // Catch:{ JSONException -> 0x00f4 }
            java.lang.String r5 = "."
            r6.append(r5)     // Catch:{ JSONException -> 0x00f4 }
            java.lang.String r5 = r6.toString()     // Catch:{ JSONException -> 0x00f4 }
        L_0x00c5:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00f4 }
            r6.<init>()     // Catch:{ JSONException -> 0x00f4 }
            java.lang.String r0 = "https://"
            r6.append(r0)     // Catch:{ JSONException -> 0x00f4 }
            r6.append(r5)     // Catch:{ JSONException -> 0x00f4 }
            java.lang.String r5 = "business.smartcamera."
            r6.append(r5)     // Catch:{ JSONException -> 0x00f4 }
            java.lang.String r5 = "api.io.mi.com"
            r6.append(r5)     // Catch:{ JSONException -> 0x00f4 }
            java.lang.String r5 = "/miot/camera/app/v1/cloud/m3u8"
            r6.append(r5)     // Catch:{ JSONException -> 0x00f4 }
            java.lang.String r5 = r6.toString()     // Catch:{ JSONException -> 0x00f4 }
            android.util.Pair r4 = r3.paramEncrypt(r4)     // Catch:{ JSONException -> 0x00f4 }
            if (r4 == 0) goto L_0x00f4
            java.lang.Object r4 = r4.first     // Catch:{ JSONException -> 0x00f4 }
            java.util.List r4 = (java.util.List) r4     // Catch:{ JSONException -> 0x00f4 }
            java.lang.String r4 = com.xiaomi.smarthome.core.server.internal.util.KeyValuePairUtil.a((java.lang.String) r5, (java.util.List<com.xiaomi.smarthome.core.entity.net.KeyValuePair>) r4)     // Catch:{ JSONException -> 0x00f4 }
            r1 = r4
        L_0x00f4:
            java.lang.String r4 = "CloudVideoNetUtils"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "getPlayFileUrl:"
            r5.append(r6)
            r5.append(r1)
            java.lang.String r5 = r5.toString()
            com.xiaomi.smarthome.framework.log.LogUtil.a((java.lang.String) r4, (java.lang.String) r5)
            return r1
        L_0x010b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils.getPlayFileUrl(java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0087 A[Catch:{ JSONException -> 0x00c7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00be A[Catch:{ JSONException -> 0x00c7 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getVideoFileUrl(java.lang.String r4, java.lang.String r5, java.lang.String r6, boolean r7) {
        /*
            r3 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r2 = "did"
            r0.put(r2, r4)     // Catch:{ JSONException -> 0x00c7 }
            java.lang.String r4 = "fileId"
            r0.put(r4, r6)     // Catch:{ JSONException -> 0x00c7 }
            java.lang.String r4 = "model"
            r0.put(r4, r5)     // Catch:{ JSONException -> 0x00c7 }
            java.lang.String r4 = "isAlarm"
            r0.put(r4, r7)     // Catch:{ JSONException -> 0x00c7 }
            java.lang.String r4 = "videoCodec"
            java.lang.String r5 = "H265"
            r0.put(r4, r5)     // Catch:{ JSONException -> 0x00c7 }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ JSONException -> 0x00c7 }
            r4.<init>()     // Catch:{ JSONException -> 0x00c7 }
            com.xiaomi.smarthome.core.entity.net.KeyValuePair r5 = new com.xiaomi.smarthome.core.entity.net.KeyValuePair     // Catch:{ JSONException -> 0x00c7 }
            java.lang.String r6 = "data"
            java.lang.String r7 = r0.toString()     // Catch:{ JSONException -> 0x00c7 }
            r5.<init>(r6, r7)     // Catch:{ JSONException -> 0x00c7 }
            r4.add(r5)     // Catch:{ JSONException -> 0x00c7 }
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r5 = new com.xiaomi.smarthome.core.entity.net.NetRequest$Builder     // Catch:{ JSONException -> 0x00c7 }
            r5.<init>()     // Catch:{ JSONException -> 0x00c7 }
            java.lang.String r6 = "business.smartcamera."
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r5 = r5.c((java.lang.String) r6)     // Catch:{ JSONException -> 0x00c7 }
            java.lang.String r6 = "GET"
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r5 = r5.a((java.lang.String) r6)     // Catch:{ JSONException -> 0x00c7 }
            java.lang.String r6 = "/common/app/m3u8"
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r5 = r5.b((java.lang.String) r6)     // Catch:{ JSONException -> 0x00c7 }
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r4 = r5.b((java.util.List<com.xiaomi.smarthome.core.entity.net.KeyValuePair>) r4)     // Catch:{ JSONException -> 0x00c7 }
            com.xiaomi.smarthome.core.entity.net.NetRequest r4 = r4.a()     // Catch:{ JSONException -> 0x00c7 }
            java.lang.String r5 = ""
            com.xiaomi.smarthome.frame.core.CoreApi r6 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ JSONException -> 0x00c7 }
            com.xiaomi.smarthome.frame.server_compact.ServerBean r6 = r6.F()     // Catch:{ JSONException -> 0x00c7 }
            if (r6 == 0) goto L_0x007f
            boolean r7 = com.xiaomi.smarthome.frame.server_compact.ServerCompact.c((com.xiaomi.smarthome.frame.server_compact.ServerBean) r6)     // Catch:{ JSONException -> 0x00c7 }
            if (r7 == 0) goto L_0x006d
            goto L_0x007f
        L_0x006d:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00c7 }
            r7.<init>()     // Catch:{ JSONException -> 0x00c7 }
            r7.append(r5)     // Catch:{ JSONException -> 0x00c7 }
            java.lang.String r5 = r6.f1530a     // Catch:{ JSONException -> 0x00c7 }
            r7.append(r5)     // Catch:{ JSONException -> 0x00c7 }
            java.lang.String r5 = r7.toString()     // Catch:{ JSONException -> 0x00c7 }
            goto L_0x0081
        L_0x007f:
            java.lang.String r5 = ""
        L_0x0081:
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ JSONException -> 0x00c7 }
            if (r6 != 0) goto L_0x0098
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00c7 }
            r6.<init>()     // Catch:{ JSONException -> 0x00c7 }
            r6.append(r5)     // Catch:{ JSONException -> 0x00c7 }
            java.lang.String r5 = "."
            r6.append(r5)     // Catch:{ JSONException -> 0x00c7 }
            java.lang.String r5 = r6.toString()     // Catch:{ JSONException -> 0x00c7 }
        L_0x0098:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00c7 }
            r6.<init>()     // Catch:{ JSONException -> 0x00c7 }
            java.lang.String r7 = "https://"
            r6.append(r7)     // Catch:{ JSONException -> 0x00c7 }
            r6.append(r5)     // Catch:{ JSONException -> 0x00c7 }
            java.lang.String r5 = "business.smartcamera."
            r6.append(r5)     // Catch:{ JSONException -> 0x00c7 }
            java.lang.String r5 = "api.io.mi.com"
            r6.append(r5)     // Catch:{ JSONException -> 0x00c7 }
            java.lang.String r5 = "/common/app/m3u8"
            r6.append(r5)     // Catch:{ JSONException -> 0x00c7 }
            java.lang.String r5 = r6.toString()     // Catch:{ JSONException -> 0x00c7 }
            android.util.Pair r4 = r3.paramEncrypt(r4)     // Catch:{ JSONException -> 0x00c7 }
            if (r4 == 0) goto L_0x00c7
            java.lang.Object r4 = r4.first     // Catch:{ JSONException -> 0x00c7 }
            java.util.List r4 = (java.util.List) r4     // Catch:{ JSONException -> 0x00c7 }
            java.lang.String r4 = com.xiaomi.smarthome.core.server.internal.util.KeyValuePairUtil.a((java.lang.String) r5, (java.util.List<com.xiaomi.smarthome.core.entity.net.KeyValuePair>) r4)     // Catch:{ JSONException -> 0x00c7 }
            r1 = r4
        L_0x00c7:
            java.lang.String r4 = "CloudVideoNetUtils"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "getVideoFileUrl:"
            r5.append(r6)
            r5.append(r1)
            java.lang.String r5 = r5.toString()
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.a(r4, r5)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils.getVideoFileUrl(java.lang.String, java.lang.String, java.lang.String, boolean):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0088 A[Catch:{ JSONException -> 0x00c8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x00bf A[Catch:{ JSONException -> 0x00c8 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getSnapshotUrl(java.lang.String r4, java.lang.String r5, java.lang.String r6) {
        /*
            r3 = this;
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            r1 = 0
            java.lang.String r2 = "did"
            r0.put(r2, r4)     // Catch:{ JSONException -> 0x00c8 }
            java.lang.String r4 = "fileId"
            r0.put(r4, r5)     // Catch:{ JSONException -> 0x00c8 }
            java.lang.String r4 = "stoId"
            r0.put(r4, r6)     // Catch:{ JSONException -> 0x00c8 }
            com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoCryptoUtils r4 = com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoCryptoUtils.getInstance()     // Catch:{ JSONException -> 0x00c8 }
            javax.crypto.spec.IvParameterSpec r4 = r4.iv     // Catch:{ JSONException -> 0x00c8 }
            byte[] r4 = r4.getIV()     // Catch:{ JSONException -> 0x00c8 }
            r5 = 2
            java.lang.String r4 = android.util.Base64.encodeToString(r4, r5)     // Catch:{ JSONException -> 0x00c8 }
            java.lang.String r5 = "segmentIv"
            r0.put(r5, r4)     // Catch:{ JSONException -> 0x00c8 }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ JSONException -> 0x00c8 }
            r4.<init>()     // Catch:{ JSONException -> 0x00c8 }
            com.xiaomi.smarthome.core.entity.net.KeyValuePair r5 = new com.xiaomi.smarthome.core.entity.net.KeyValuePair     // Catch:{ JSONException -> 0x00c8 }
            java.lang.String r6 = "data"
            java.lang.String r0 = r0.toString()     // Catch:{ JSONException -> 0x00c8 }
            r5.<init>(r6, r0)     // Catch:{ JSONException -> 0x00c8 }
            r4.add(r5)     // Catch:{ JSONException -> 0x00c8 }
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r5 = new com.xiaomi.smarthome.core.entity.net.NetRequest$Builder     // Catch:{ JSONException -> 0x00c8 }
            r5.<init>()     // Catch:{ JSONException -> 0x00c8 }
            java.lang.String r6 = "processor.smartcamera."
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r5 = r5.c((java.lang.String) r6)     // Catch:{ JSONException -> 0x00c8 }
            java.lang.String r6 = "GET"
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r5 = r5.a((java.lang.String) r6)     // Catch:{ JSONException -> 0x00c8 }
            java.lang.String r6 = "/miot/camera/app/v1/img"
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r5 = r5.b((java.lang.String) r6)     // Catch:{ JSONException -> 0x00c8 }
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r4 = r5.b((java.util.List<com.xiaomi.smarthome.core.entity.net.KeyValuePair>) r4)     // Catch:{ JSONException -> 0x00c8 }
            com.xiaomi.smarthome.core.entity.net.NetRequest r4 = r4.a()     // Catch:{ JSONException -> 0x00c8 }
            java.lang.String r5 = ""
            com.xiaomi.smarthome.frame.core.CoreApi r6 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ JSONException -> 0x00c8 }
            com.xiaomi.smarthome.frame.server_compact.ServerBean r6 = r6.F()     // Catch:{ JSONException -> 0x00c8 }
            if (r6 == 0) goto L_0x0080
            boolean r0 = com.xiaomi.smarthome.frame.server_compact.ServerCompact.c((com.xiaomi.smarthome.frame.server_compact.ServerBean) r6)     // Catch:{ JSONException -> 0x00c8 }
            if (r0 == 0) goto L_0x006e
            goto L_0x0080
        L_0x006e:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00c8 }
            r0.<init>()     // Catch:{ JSONException -> 0x00c8 }
            r0.append(r5)     // Catch:{ JSONException -> 0x00c8 }
            java.lang.String r5 = r6.f1530a     // Catch:{ JSONException -> 0x00c8 }
            r0.append(r5)     // Catch:{ JSONException -> 0x00c8 }
            java.lang.String r5 = r0.toString()     // Catch:{ JSONException -> 0x00c8 }
            goto L_0x0082
        L_0x0080:
            java.lang.String r5 = ""
        L_0x0082:
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ JSONException -> 0x00c8 }
            if (r6 != 0) goto L_0x0099
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00c8 }
            r6.<init>()     // Catch:{ JSONException -> 0x00c8 }
            r6.append(r5)     // Catch:{ JSONException -> 0x00c8 }
            java.lang.String r5 = "."
            r6.append(r5)     // Catch:{ JSONException -> 0x00c8 }
            java.lang.String r5 = r6.toString()     // Catch:{ JSONException -> 0x00c8 }
        L_0x0099:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00c8 }
            r6.<init>()     // Catch:{ JSONException -> 0x00c8 }
            java.lang.String r0 = "https://"
            r6.append(r0)     // Catch:{ JSONException -> 0x00c8 }
            r6.append(r5)     // Catch:{ JSONException -> 0x00c8 }
            java.lang.String r5 = "processor.smartcamera."
            r6.append(r5)     // Catch:{ JSONException -> 0x00c8 }
            java.lang.String r5 = "api.io.mi.com"
            r6.append(r5)     // Catch:{ JSONException -> 0x00c8 }
            java.lang.String r5 = "/miot/camera/app/v1/img"
            r6.append(r5)     // Catch:{ JSONException -> 0x00c8 }
            java.lang.String r5 = r6.toString()     // Catch:{ JSONException -> 0x00c8 }
            android.util.Pair r4 = r3.paramEncrypt(r4)     // Catch:{ JSONException -> 0x00c8 }
            if (r4 == 0) goto L_0x00c8
            java.lang.Object r4 = r4.first     // Catch:{ JSONException -> 0x00c8 }
            java.util.List r4 = (java.util.List) r4     // Catch:{ JSONException -> 0x00c8 }
            java.lang.String r4 = com.xiaomi.smarthome.core.server.internal.util.KeyValuePairUtil.a((java.lang.String) r5, (java.util.List<com.xiaomi.smarthome.core.entity.net.KeyValuePair>) r4)     // Catch:{ JSONException -> 0x00c8 }
            r1 = r4
        L_0x00c8:
            java.lang.String r4 = "CloudVideoNetUtils"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "getSnapshotUrl:"
            r5.append(r6)
            r5.append(r1)
            java.lang.String r5 = r5.toString()
            com.xiaomi.smarthome.framework.log.LogUtil.a((java.lang.String) r4, (java.lang.String) r5)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils.getSnapshotUrl(java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0088 A[Catch:{ Exception -> 0x00c8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x00bf A[Catch:{ Exception -> 0x00c8 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getCloudFileUrl(java.lang.String r4, java.lang.String r5, java.lang.String r6) {
        /*
            r3 = this;
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            r1 = 0
            java.lang.String r2 = "did"
            r0.put(r2, r4)     // Catch:{ Exception -> 0x00c8 }
            java.lang.String r4 = "fileId"
            r0.put(r4, r5)     // Catch:{ Exception -> 0x00c8 }
            java.lang.String r4 = "stoId"
            r0.put(r4, r6)     // Catch:{ Exception -> 0x00c8 }
            com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoCryptoUtils r4 = com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoCryptoUtils.getInstance()     // Catch:{ Exception -> 0x00c8 }
            javax.crypto.spec.IvParameterSpec r4 = r4.iv     // Catch:{ Exception -> 0x00c8 }
            byte[] r4 = r4.getIV()     // Catch:{ Exception -> 0x00c8 }
            r5 = 2
            java.lang.String r4 = android.util.Base64.encodeToString(r4, r5)     // Catch:{ Exception -> 0x00c8 }
            java.lang.String r5 = "segmentIv"
            r0.put(r5, r4)     // Catch:{ Exception -> 0x00c8 }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ Exception -> 0x00c8 }
            r4.<init>()     // Catch:{ Exception -> 0x00c8 }
            com.xiaomi.smarthome.core.entity.net.KeyValuePair r5 = new com.xiaomi.smarthome.core.entity.net.KeyValuePair     // Catch:{ Exception -> 0x00c8 }
            java.lang.String r6 = "data"
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x00c8 }
            r5.<init>(r6, r0)     // Catch:{ Exception -> 0x00c8 }
            r4.add(r5)     // Catch:{ Exception -> 0x00c8 }
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r5 = new com.xiaomi.smarthome.core.entity.net.NetRequest$Builder     // Catch:{ Exception -> 0x00c8 }
            r5.<init>()     // Catch:{ Exception -> 0x00c8 }
            java.lang.String r6 = "processor.smartcamera."
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r5 = r5.c((java.lang.String) r6)     // Catch:{ Exception -> 0x00c8 }
            java.lang.String r6 = "GET"
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r5 = r5.a((java.lang.String) r6)     // Catch:{ Exception -> 0x00c8 }
            java.lang.String r6 = "/miot/camera/app/v1/mp4"
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r5 = r5.b((java.lang.String) r6)     // Catch:{ Exception -> 0x00c8 }
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r4 = r5.b((java.util.List<com.xiaomi.smarthome.core.entity.net.KeyValuePair>) r4)     // Catch:{ Exception -> 0x00c8 }
            com.xiaomi.smarthome.core.entity.net.NetRequest r4 = r4.a()     // Catch:{ Exception -> 0x00c8 }
            java.lang.String r5 = ""
            com.xiaomi.smarthome.frame.core.CoreApi r6 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ Exception -> 0x00c8 }
            com.xiaomi.smarthome.frame.server_compact.ServerBean r6 = r6.F()     // Catch:{ Exception -> 0x00c8 }
            if (r6 == 0) goto L_0x0080
            boolean r0 = com.xiaomi.smarthome.frame.server_compact.ServerCompact.c((com.xiaomi.smarthome.frame.server_compact.ServerBean) r6)     // Catch:{ Exception -> 0x00c8 }
            if (r0 == 0) goto L_0x006e
            goto L_0x0080
        L_0x006e:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00c8 }
            r0.<init>()     // Catch:{ Exception -> 0x00c8 }
            r0.append(r5)     // Catch:{ Exception -> 0x00c8 }
            java.lang.String r5 = r6.f1530a     // Catch:{ Exception -> 0x00c8 }
            r0.append(r5)     // Catch:{ Exception -> 0x00c8 }
            java.lang.String r5 = r0.toString()     // Catch:{ Exception -> 0x00c8 }
            goto L_0x0082
        L_0x0080:
            java.lang.String r5 = ""
        L_0x0082:
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Exception -> 0x00c8 }
            if (r6 != 0) goto L_0x0099
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00c8 }
            r6.<init>()     // Catch:{ Exception -> 0x00c8 }
            r6.append(r5)     // Catch:{ Exception -> 0x00c8 }
            java.lang.String r5 = "."
            r6.append(r5)     // Catch:{ Exception -> 0x00c8 }
            java.lang.String r5 = r6.toString()     // Catch:{ Exception -> 0x00c8 }
        L_0x0099:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00c8 }
            r6.<init>()     // Catch:{ Exception -> 0x00c8 }
            java.lang.String r0 = "https://"
            r6.append(r0)     // Catch:{ Exception -> 0x00c8 }
            r6.append(r5)     // Catch:{ Exception -> 0x00c8 }
            java.lang.String r5 = "processor.smartcamera."
            r6.append(r5)     // Catch:{ Exception -> 0x00c8 }
            java.lang.String r5 = "api.io.mi.com"
            r6.append(r5)     // Catch:{ Exception -> 0x00c8 }
            java.lang.String r5 = "/miot/camera/app/v1/mp4"
            r6.append(r5)     // Catch:{ Exception -> 0x00c8 }
            java.lang.String r5 = r6.toString()     // Catch:{ Exception -> 0x00c8 }
            android.util.Pair r4 = r3.paramEncrypt(r4)     // Catch:{ Exception -> 0x00c8 }
            if (r4 == 0) goto L_0x00c8
            java.lang.Object r4 = r4.first     // Catch:{ Exception -> 0x00c8 }
            java.util.List r4 = (java.util.List) r4     // Catch:{ Exception -> 0x00c8 }
            java.lang.String r4 = com.xiaomi.smarthome.core.server.internal.util.KeyValuePairUtil.a((java.lang.String) r5, (java.util.List<com.xiaomi.smarthome.core.entity.net.KeyValuePair>) r4)     // Catch:{ Exception -> 0x00c8 }
            r1 = r4
        L_0x00c8:
            java.lang.String r4 = "CloudVideoNetUtils"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "getCloudFileUrl:"
            r5.append(r6)
            r5.append(r1)
            java.lang.String r5 = r5.toString()
            com.xiaomi.smarthome.framework.log.LogUtil.a((java.lang.String) r4, (java.lang.String) r5)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils.getCloudFileUrl(java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    public void getPlainImageFile(String str, final ICloudVideoCallback<byte[]> iCloudVideoCallback) {
        MiServiceTokenInfo tokenInfo = getTokenInfo();
        Request.Builder url = new Request.Builder().get().url(str);
        this.client.newCall(url.header("Cookie", "yetAnotherServiceToken=" + tokenInfo.c).build()).enqueue(new Callback() {
            public void onFailure(Call call, IOException iOException) {
                if (iCloudVideoCallback != null) {
                    iCloudVideoCallback.onCloudVideoFailed(-90001, "");
                }
            }

            public void onResponse(Call call, Response response) throws IOException {
                if (iCloudVideoCallback == null) {
                    return;
                }
                if (response.isSuccessful()) {
                    iCloudVideoCallback.onCloudVideoSuccess(response.body().bytes(), (Object) null);
                    return;
                }
                iCloudVideoCallback.onCloudVideoFailed(response.code(), response.message());
            }
        });
    }

    public void getCloudFile(Context context, String str, String str2, String str3, String str4, final ICloudDataCallback<String> iCloudDataCallback) {
        if ((context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) && iCloudDataCallback != null) {
            iCloudDataCallback.onCloudDataFailed(-90001, "invalid param(s)");
            return;
        }
        String cloudFileUrl = getCloudFileUrl(str, str2, str3);
        final String str5 = context.getApplicationContext().getCacheDir() + "/" + str2 + ".mp4";
        LogUtil.c(TAG, "mp4FilePath = " + str5);
        if (new File(str5).exists()) {
            LogUtil.c(TAG, "file.exists = true");
            if (iCloudDataCallback != null) {
                iCloudDataCallback.onCloudDataSuccess(str5, (Object) null);
                return;
            }
        }
        LogUtil.c(TAG, "start download...");
        new FileDownloadAndDecryptTask(new FileDownloadAndDecryptTask.IFileDownloadCallback() {
            public void onSuccess() {
                if (iCloudDataCallback != null) {
                    iCloudDataCallback.onCloudDataSuccess(str5, (Object) null);
                }
            }

            public void onFailure(int i) {
                if (iCloudDataCallback != null) {
                    iCloudDataCallback.onCloudDataFailed(i, (String) null);
                }
            }

            public void onProgress(int i) {
                if (iCloudDataCallback != null) {
                    iCloudDataCallback.onCloudDataProgress(i);
                }
            }
        }).execute(new String[]{cloudFileUrl, str5});
    }

    public void deleteFiles(Context context, String str, final ICloudVideoCallback<String> iCloudVideoCallback) {
        if (context != null && !TextUtils.isEmpty(str)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", str));
            Context context2 = context;
            CoreApi.a().a(context2, new NetRequest.Builder().c("business.smartcamera.").a("POST").b(AlarmNetUtils.J).b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, new AsyncCallback<JSONObject, Error>() {
                public void onSuccess(JSONObject jSONObject) {
                    if (iCloudVideoCallback == null) {
                        return;
                    }
                    if (jSONObject != null) {
                        int optInt = jSONObject.optInt("code");
                        if (optInt == 0) {
                            iCloudVideoCallback.onCloudVideoSuccess("success", (Object) null);
                        } else {
                            iCloudVideoCallback.onCloudVideoFailed(optInt, "code error");
                        }
                    } else {
                        iCloudVideoCallback.onCloudVideoFailed(-90002, "result is null");
                    }
                }

                public void onFailure(Error error) {
                    if (iCloudVideoCallback == null) {
                        return;
                    }
                    if (error != null) {
                        ICloudVideoCallback iCloudVideoCallback = iCloudVideoCallback;
                        int a2 = error.a();
                        iCloudVideoCallback.onCloudVideoFailed(a2, "" + error.b());
                        return;
                    }
                    iCloudVideoCallback.onCloudVideoFailed(-90001, "FAIL_GENERAL");
                }
            });
        }
    }

    public void getSettingUsage(Context context, String str, final ICloudVideoCallback<Boolean> iCloudVideoCallback) {
        if (context != null && !TextUtils.isEmpty(str)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", str));
            Context context2 = context;
            CoreApi.a().a(context2, new NetRequest.Builder().c("business.smartcamera.").a("GET").b(CLOUD_SETTING_USAGE_GET).b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, new AsyncCallback<JSONObject, Error>() {
                public void onSuccess(JSONObject jSONObject) {
                    if (iCloudVideoCallback == null) {
                        return;
                    }
                    if (jSONObject == null) {
                        iCloudVideoCallback.onCloudVideoFailed(-90002, "result is null");
                    } else if (jSONObject.optInt("code", -90003) == 0) {
                        JSONObject optJSONObject = jSONObject.optJSONObject("data");
                        if (optJSONObject != null) {
                            iCloudVideoCallback.onCloudVideoSuccess(Boolean.valueOf(optJSONObject.optBoolean("status")), (Object) null);
                            return;
                        }
                        iCloudVideoCallback.onCloudVideoFailed(-90002, "status null");
                    } else {
                        iCloudVideoCallback.onCloudVideoFailed(-90002, "code is not 0");
                    }
                }

                public void onFailure(Error error) {
                    if (iCloudVideoCallback == null) {
                        return;
                    }
                    if (error != null) {
                        ICloudVideoCallback iCloudVideoCallback = iCloudVideoCallback;
                        int a2 = error.a();
                        iCloudVideoCallback.onCloudVideoFailed(a2, "" + error.b());
                        return;
                    }
                    iCloudVideoCallback.onCloudVideoFailed(-90001, "FAIL_GENERAL");
                }
            });
        }
    }

    public void setSettingUsage(Context context, String str, final ICloudVideoCallback<Boolean> iCloudVideoCallback) {
        if (context != null && !TextUtils.isEmpty(str)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", str));
            Context context2 = context;
            CoreApi.a().a(context2, new NetRequest.Builder().c("business.smartcamera.").a("POST").b(CLOUD_SETTING_USAGE_PUT).b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, new AsyncCallback<JSONObject, Error>() {
                public void onSuccess(JSONObject jSONObject) {
                    if (iCloudVideoCallback == null) {
                        return;
                    }
                    if (jSONObject == null) {
                        iCloudVideoCallback.onCloudVideoFailed(-90002, "result is null");
                    } else if (jSONObject.optInt("code", -90003) == 0) {
                        iCloudVideoCallback.onCloudVideoSuccess(null, (Object) null);
                    } else {
                        iCloudVideoCallback.onCloudVideoFailed(-90002, "code is not 0");
                    }
                }

                public void onFailure(Error error) {
                    if (iCloudVideoCallback == null) {
                        return;
                    }
                    if (error != null) {
                        ICloudVideoCallback iCloudVideoCallback = iCloudVideoCallback;
                        int a2 = error.a();
                        iCloudVideoCallback.onCloudVideoFailed(a2, "" + error.b());
                        return;
                    }
                    iCloudVideoCallback.onCloudVideoFailed(-90001, "FAIL_GENERAL");
                }
            });
        }
    }

    public void getSettingCapacity(Context context, String str, final ICloudVideoCallback<Long> iCloudVideoCallback) {
        if (context != null && !TextUtils.isEmpty(str)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", str));
            Context context2 = context;
            CoreApi.a().a(context2, new NetRequest.Builder().c("business.smartcamera.").a("GET").b(CLOUD_SETTING_CAPACITY).b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, new AsyncCallback<JSONObject, Error>() {
                public void onSuccess(JSONObject jSONObject) {
                    if (iCloudVideoCallback == null) {
                        return;
                    }
                    if (jSONObject == null) {
                        iCloudVideoCallback.onCloudVideoFailed(-90002, "result is null");
                    } else if (jSONObject.optInt("code", -90003) == 0) {
                        JSONObject optJSONObject = jSONObject.optJSONObject("data");
                        if (optJSONObject != null) {
                            iCloudVideoCallback.onCloudVideoSuccess(Long.valueOf(optJSONObject.optLong("capacity")), (Object) null);
                            return;
                        }
                        iCloudVideoCallback.onCloudVideoFailed(-90002, "data is null");
                    } else {
                        iCloudVideoCallback.onCloudVideoFailed(-90002, "code is not 0");
                    }
                }

                public void onFailure(Error error) {
                    if (iCloudVideoCallback == null) {
                        return;
                    }
                    if (error != null) {
                        ICloudVideoCallback iCloudVideoCallback = iCloudVideoCallback;
                        int a2 = error.a();
                        iCloudVideoCallback.onCloudVideoFailed(a2, "" + error.b());
                        return;
                    }
                    iCloudVideoCallback.onCloudVideoFailed(-90001, "FAIL_GENERAL");
                }
            });
        }
    }

    public void getSettingStatus(Context context, String str, final ICloudVideoCallback<CloudVideoUserStatus> iCloudVideoCallback) {
        if (context != null && !TextUtils.isEmpty(str)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", str));
            Context context2 = context;
            CoreApi.a().a(context2, new NetRequest.Builder().c("business.smartcamera.").a("GET").b("/miot/camera/app/v1/vip/status").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, new AsyncCallback<JSONObject, Error>() {
                public void onSuccess(JSONObject jSONObject) {
                    if (iCloudVideoCallback == null) {
                        return;
                    }
                    if (jSONObject == null) {
                        iCloudVideoCallback.onCloudVideoFailed(-90002, "result is null");
                    } else if (jSONObject.optInt("code", -90003) == 0) {
                        JSONObject optJSONObject = jSONObject.optJSONObject("data");
                        if (optJSONObject != null) {
                            boolean optBoolean = optJSONObject.optBoolean("vip");
                            CloudVideoUserStatus cloudVideoUserStatus = new CloudVideoUserStatus();
                            if (optBoolean) {
                                cloudVideoUserStatus.vip = true;
                                cloudVideoUserStatus.startTime = optJSONObject.optLong("startTime");
                                cloudVideoUserStatus.endTime = optJSONObject.optLong("endTime");
                                cloudVideoUserStatus.packageType = optJSONObject.optString("pacakgeType");
                                cloudVideoUserStatus.isRenew = optJSONObject.optBoolean("isRenew");
                            } else {
                                cloudVideoUserStatus.vip = false;
                                cloudVideoUserStatus.status = optJSONObject.optInt("status");
                                cloudVideoUserStatus.startTime = optJSONObject.optLong("startTime");
                                cloudVideoUserStatus.endTime = optJSONObject.optLong("endTime");
                                cloudVideoUserStatus.packageType = optJSONObject.optString("pacakgeType");
                            }
                            iCloudVideoCallback.onCloudVideoSuccess(cloudVideoUserStatus, (Object) null);
                            return;
                        }
                        iCloudVideoCallback.onCloudVideoFailed(-90002, "data is null");
                    } else {
                        iCloudVideoCallback.onCloudVideoFailed(-90002, "code is not 0");
                    }
                }

                public void onFailure(Error error) {
                    if (iCloudVideoCallback == null) {
                        return;
                    }
                    if (error != null) {
                        ICloudVideoCallback iCloudVideoCallback = iCloudVideoCallback;
                        int a2 = error.a();
                        iCloudVideoCallback.onCloudVideoFailed(a2, "" + error.b());
                        return;
                    }
                    iCloudVideoCallback.onCloudVideoFailed(-90001, "FAIL_GENERAL");
                }
            });
        }
    }

    public void getDeductOrders(Context context, String str, final ICloudVideoCallback<Boolean> iCloudVideoCallback) {
        if (context != null && !TextUtils.isEmpty(str)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", str));
            Context context2 = context;
            CoreApi.a().a(context2, new NetRequest.Builder().c("business.smartcamera.").a("GET").b(CLOUD_DEDUCT_ORDERS).b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, new AsyncCallback<JSONObject, Error>() {
                public void onSuccess(JSONObject jSONObject) {
                    if (iCloudVideoCallback == null) {
                        return;
                    }
                    if (jSONObject == null) {
                        iCloudVideoCallback.onCloudVideoFailed(-90002, "result is null");
                    } else if (jSONObject.optInt("code", -90003) == 0) {
                        JSONObject optJSONObject = jSONObject.optJSONObject("data");
                        if (optJSONObject != null) {
                            iCloudVideoCallback.onCloudVideoSuccess(Boolean.valueOf(optJSONObject.optBoolean("hasDeductOrders")), (Object) null);
                            return;
                        }
                        iCloudVideoCallback.onCloudVideoFailed(-90002, "data is null");
                    } else {
                        iCloudVideoCallback.onCloudVideoFailed(-90002, "code is not 0");
                    }
                }

                public void onFailure(Error error) {
                    if (iCloudVideoCallback == null) {
                        return;
                    }
                    if (error != null) {
                        ICloudVideoCallback iCloudVideoCallback = iCloudVideoCallback;
                        int a2 = error.a();
                        iCloudVideoCallback.onCloudVideoFailed(a2, "" + error.b());
                        return;
                    }
                    iCloudVideoCallback.onCloudVideoFailed(-90001, "FAIL_GENERAL");
                }
            });
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00db A[Catch:{ JSONException -> 0x0136 }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x012d A[Catch:{ JSONException -> 0x0136 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String generateRequestUrl(java.lang.String r9, org.json.JSONObject r10, org.json.JSONObject r11) {
        /*
            r8 = this;
            r9 = 0
            if (r10 == 0) goto L_0x014d
            if (r11 != 0) goto L_0x0007
            goto L_0x014d
        L_0x0007:
            java.lang.String r0 = "prefix"
            java.lang.String r0 = r10.optString(r0)
            java.lang.String r1 = "method"
            java.lang.String r1 = r10.optString(r1)
            java.lang.String r2 = "path"
            java.lang.String r10 = r10.optString(r2)
            r2 = 0
            r3 = 0
            r4 = 0
        L_0x001c:
            int r5 = r11.length()     // Catch:{ JSONException -> 0x0136 }
            if (r2 >= r5) goto L_0x0049
            org.json.JSONArray r5 = r11.names()     // Catch:{ JSONException -> 0x0136 }
            java.lang.Object r5 = r5.get(r2)     // Catch:{ JSONException -> 0x0136 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ JSONException -> 0x0136 }
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ JSONException -> 0x0136 }
            r7 = 1
            if (r6 != 0) goto L_0x0046
            java.lang.String r6 = "region"
            boolean r6 = r5.equals(r6)     // Catch:{ JSONException -> 0x0136 }
            if (r6 == 0) goto L_0x003d
            r3 = 1
            goto L_0x0046
        L_0x003d:
            java.lang.String r6 = "segmentIv"
            boolean r5 = r5.equals(r6)     // Catch:{ JSONException -> 0x0136 }
            if (r5 == 0) goto L_0x0046
            r4 = 1
        L_0x0046:
            int r2 = r2 + 1
            goto L_0x001c
        L_0x0049:
            if (r3 != 0) goto L_0x006c
            com.xiaomi.smarthome.frame.core.CoreApi r2 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ JSONException -> 0x0136 }
            java.util.Locale r2 = r2.I()     // Catch:{ JSONException -> 0x0136 }
            if (r2 == 0) goto L_0x005f
            java.lang.String r3 = "region"
            java.lang.String r2 = r2.getCountry()     // Catch:{ JSONException -> 0x0136 }
            r11.put(r3, r2)     // Catch:{ JSONException -> 0x0136 }
            goto L_0x006c
        L_0x005f:
            java.lang.String r2 = "region"
            java.util.Locale r3 = java.util.Locale.getDefault()     // Catch:{ JSONException -> 0x0136 }
            java.lang.String r3 = r3.getCountry()     // Catch:{ JSONException -> 0x0136 }
            r11.put(r2, r3)     // Catch:{ JSONException -> 0x0136 }
        L_0x006c:
            if (r4 != 0) goto L_0x0082
            com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoCryptoUtils r2 = com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoCryptoUtils.getInstance()     // Catch:{ JSONException -> 0x0136 }
            javax.crypto.spec.IvParameterSpec r2 = r2.iv     // Catch:{ JSONException -> 0x0136 }
            byte[] r2 = r2.getIV()     // Catch:{ JSONException -> 0x0136 }
            r3 = 2
            java.lang.String r2 = android.util.Base64.encodeToString(r2, r3)     // Catch:{ JSONException -> 0x0136 }
            java.lang.String r3 = "segmentIv"
            r11.put(r3, r2)     // Catch:{ JSONException -> 0x0136 }
        L_0x0082:
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ JSONException -> 0x0136 }
            r2.<init>()     // Catch:{ JSONException -> 0x0136 }
            com.xiaomi.smarthome.core.entity.net.KeyValuePair r3 = new com.xiaomi.smarthome.core.entity.net.KeyValuePair     // Catch:{ JSONException -> 0x0136 }
            java.lang.String r4 = "data"
            java.lang.String r11 = r11.toString()     // Catch:{ JSONException -> 0x0136 }
            r3.<init>(r4, r11)     // Catch:{ JSONException -> 0x0136 }
            r2.add(r3)     // Catch:{ JSONException -> 0x0136 }
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r11 = new com.xiaomi.smarthome.core.entity.net.NetRequest$Builder     // Catch:{ JSONException -> 0x0136 }
            r11.<init>()     // Catch:{ JSONException -> 0x0136 }
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r11 = r11.c((java.lang.String) r0)     // Catch:{ JSONException -> 0x0136 }
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r11 = r11.a((java.lang.String) r1)     // Catch:{ JSONException -> 0x0136 }
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r11 = r11.b((java.lang.String) r10)     // Catch:{ JSONException -> 0x0136 }
            com.xiaomi.smarthome.core.entity.net.NetRequest$Builder r11 = r11.b((java.util.List<com.xiaomi.smarthome.core.entity.net.KeyValuePair>) r2)     // Catch:{ JSONException -> 0x0136 }
            com.xiaomi.smarthome.core.entity.net.NetRequest r11 = r11.a()     // Catch:{ JSONException -> 0x0136 }
            java.lang.String r1 = ""
            com.xiaomi.smarthome.frame.core.CoreApi r2 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ JSONException -> 0x0136 }
            com.xiaomi.smarthome.frame.server_compact.ServerBean r2 = r2.F()     // Catch:{ JSONException -> 0x0136 }
            if (r2 == 0) goto L_0x00d3
            boolean r3 = com.xiaomi.smarthome.frame.server_compact.ServerCompact.c((com.xiaomi.smarthome.frame.server_compact.ServerBean) r2)     // Catch:{ JSONException -> 0x0136 }
            if (r3 == 0) goto L_0x00c1
            goto L_0x00d3
        L_0x00c1:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0136 }
            r3.<init>()     // Catch:{ JSONException -> 0x0136 }
            r3.append(r1)     // Catch:{ JSONException -> 0x0136 }
            java.lang.String r1 = r2.f1530a     // Catch:{ JSONException -> 0x0136 }
            r3.append(r1)     // Catch:{ JSONException -> 0x0136 }
            java.lang.String r1 = r3.toString()     // Catch:{ JSONException -> 0x0136 }
            goto L_0x00d5
        L_0x00d3:
            java.lang.String r1 = ""
        L_0x00d5:
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ JSONException -> 0x0136 }
            if (r2 != 0) goto L_0x00ec
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0136 }
            r2.<init>()     // Catch:{ JSONException -> 0x0136 }
            r2.append(r1)     // Catch:{ JSONException -> 0x0136 }
            java.lang.String r1 = "."
            r2.append(r1)     // Catch:{ JSONException -> 0x0136 }
            java.lang.String r1 = r2.toString()     // Catch:{ JSONException -> 0x0136 }
        L_0x00ec:
            boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch:{ JSONException -> 0x0136 }
            if (r2 != 0) goto L_0x010b
            java.lang.String r2 = "."
            boolean r2 = r0.endsWith(r2)     // Catch:{ JSONException -> 0x0136 }
            if (r2 != 0) goto L_0x010b
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0136 }
            r2.<init>()     // Catch:{ JSONException -> 0x0136 }
            r2.append(r0)     // Catch:{ JSONException -> 0x0136 }
            java.lang.String r0 = "."
            r2.append(r0)     // Catch:{ JSONException -> 0x0136 }
            java.lang.String r0 = r2.toString()     // Catch:{ JSONException -> 0x0136 }
        L_0x010b:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x0136 }
            r2.<init>()     // Catch:{ JSONException -> 0x0136 }
            java.lang.String r3 = "https://"
            r2.append(r3)     // Catch:{ JSONException -> 0x0136 }
            r2.append(r1)     // Catch:{ JSONException -> 0x0136 }
            r2.append(r0)     // Catch:{ JSONException -> 0x0136 }
            java.lang.String r0 = "api.io.mi.com"
            r2.append(r0)     // Catch:{ JSONException -> 0x0136 }
            r2.append(r10)     // Catch:{ JSONException -> 0x0136 }
            java.lang.String r10 = r2.toString()     // Catch:{ JSONException -> 0x0136 }
            android.util.Pair r11 = r8.paramEncrypt(r11)     // Catch:{ JSONException -> 0x0136 }
            if (r11 == 0) goto L_0x0136
            java.lang.Object r11 = r11.first     // Catch:{ JSONException -> 0x0136 }
            java.util.List r11 = (java.util.List) r11     // Catch:{ JSONException -> 0x0136 }
            java.lang.String r10 = com.xiaomi.smarthome.core.server.internal.util.KeyValuePairUtil.a((java.lang.String) r10, (java.util.List<com.xiaomi.smarthome.core.entity.net.KeyValuePair>) r11)     // Catch:{ JSONException -> 0x0136 }
            r9 = r10
        L_0x0136:
            java.lang.String r10 = "CloudVideoNetUtils"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r0 = "generateRequestUrl:"
            r11.append(r0)
            r11.append(r9)
            java.lang.String r11 = r11.toString()
            com.xiaomi.smarthome.framework.log.LogUtil.a((java.lang.String) r10, (java.lang.String) r11)
            return r9
        L_0x014d:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils.generateRequestUrl(java.lang.String, org.json.JSONObject, org.json.JSONObject):java.lang.String");
    }

    public void checkCloudStorageStatus(String str, final ICloudVideoCallback<JSONObject> iCloudVideoCallback) {
        if (!TextUtils.isEmpty(str)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", str));
            CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().c("business.smartcamera.").a("GET").b("/miot/camera/app/v1/vip/status").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, new AsyncCallback<JSONObject, Error>() {
                public void onSuccess(JSONObject jSONObject) {
                    if (iCloudVideoCallback == null) {
                        return;
                    }
                    if (jSONObject != null) {
                        iCloudVideoCallback.onCloudVideoSuccess(jSONObject, (Object) null);
                    } else {
                        iCloudVideoCallback.onCloudVideoFailed(-90002, "result is null");
                    }
                }

                public void onFailure(Error error) {
                    if (iCloudVideoCallback == null) {
                        return;
                    }
                    if (error != null) {
                        ICloudVideoCallback iCloudVideoCallback = iCloudVideoCallback;
                        int a2 = error.a();
                        iCloudVideoCallback.onCloudVideoFailed(a2, "" + error.b());
                        return;
                    }
                    iCloudVideoCallback.onCloudVideoFailed(-90001, "FAIL_GENERAL");
                }
            });
        } else if (iCloudVideoCallback != null) {
            iCloudVideoCallback.onCloudVideoFailed(-90001, "context or params null");
        }
    }

    public void getMipaySignV2(String str, final ICloudVideoCallback<JSONObject> iCloudVideoCallback) {
        if (!TextUtils.isEmpty(str)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", str));
            CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().c("business.smartcamera.").a("GET").b(REQUEST_MIPAY_SIGN_V2).b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, new AsyncCallback<JSONObject, Error>() {
                public void onSuccess(JSONObject jSONObject) {
                    if (iCloudVideoCallback == null) {
                        return;
                    }
                    if (jSONObject != null) {
                        iCloudVideoCallback.onCloudVideoSuccess(jSONObject, (Object) null);
                    } else {
                        iCloudVideoCallback.onCloudVideoFailed(-90002, "result is null");
                    }
                }

                public void onFailure(Error error) {
                    if (iCloudVideoCallback == null) {
                        return;
                    }
                    if (error != null) {
                        ICloudVideoCallback iCloudVideoCallback = iCloudVideoCallback;
                        int a2 = error.a();
                        iCloudVideoCallback.onCloudVideoFailed(a2, "" + error.b());
                        return;
                    }
                    iCloudVideoCallback.onCloudVideoFailed(-90001, "FAIL_GENERAL");
                }
            });
        } else if (iCloudVideoCallback != null) {
            iCloudVideoCallback.onCloudVideoFailed(-90001, "context or params null");
        }
    }

    public void getCloudPromoteTips(final String str, final ICloudVideoCallback<String> iCloudVideoCallback) {
        isNewUser(new ICloudVideoCallback<JSONObject>() {
            public void onCloudVideoSuccess(JSONObject jSONObject, Object obj) {
                JSONObject optJSONObject = jSONObject.optJSONObject("data");
                if (optJSONObject == null) {
                    iCloudVideoCallback.onCloudVideoFailed(CloudVideoNetUtils.FAIL_IN_SUCCESS_NO_DATA, "Get new user null");
                }
                boolean optBoolean = optJSONObject.optBoolean("isNewUser");
                if (optBoolean) {
                    CloudVideoNetUtils.this.getCloudPromoteTipsInternal(str, optBoolean, new ICloudVideoCallback<JSONObject>() {
                        public void onCloudVideoSuccess(JSONObject jSONObject, Object obj) {
                            if (!(iCloudVideoCallback == null || jSONObject == null)) {
                                Log.d(CloudVideoNetUtils.TAG, "getCloudPromoteTips " + jSONObject.toString());
                                JSONObject optJSONObject = jSONObject.optJSONObject("data");
                                if (optJSONObject != null) {
                                    String optString = optJSONObject.optString("tipsInfo");
                                    if (optJSONObject.optBoolean("isDisplay") && !TextUtils.isEmpty(optString)) {
                                        iCloudVideoCallback.onCloudVideoSuccess(optString, (Object) null);
                                        return;
                                    }
                                }
                            }
                            iCloudVideoCallback.onCloudVideoFailed(-90002, "No need to display");
                        }

                        public void onCloudVideoFailed(int i, String str) {
                            if (iCloudVideoCallback != null) {
                                iCloudVideoCallback.onCloudVideoFailed(-90001, "FAIL_GENERAL");
                            }
                        }
                    });
                }
            }

            public void onCloudVideoFailed(int i, String str) {
                if (iCloudVideoCallback != null) {
                    iCloudVideoCallback.onCloudVideoFailed(-90001, "FAIL_GENERAL");
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void getCloudPromoteTipsInternal(String str, boolean z, final ICloudVideoCallback<JSONObject> iCloudVideoCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("did", str);
            Locale I = CoreApi.a().I();
            if (I != null) {
                jSONObject.put("region", I.getCountry());
            } else {
                jSONObject.put("region", Locale.getDefault().getCountry());
            }
            jSONObject.put("isNewUser", z);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().c("business.smartcamera.").a("GET").b(CLOUD_VIDEO_TIPS).b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, new AsyncCallback<JSONObject, Error>() {
            public void onSuccess(JSONObject jSONObject) {
                if (iCloudVideoCallback != null) {
                    iCloudVideoCallback.onCloudVideoSuccess(jSONObject, (Object) null);
                }
            }

            public void onFailure(Error error) {
                if (iCloudVideoCallback == null) {
                    return;
                }
                if (error != null) {
                    ICloudVideoCallback iCloudVideoCallback = iCloudVideoCallback;
                    int a2 = error.a();
                    iCloudVideoCallback.onCloudVideoFailed(a2, "" + error.b());
                    return;
                }
                iCloudVideoCallback.onCloudVideoFailed(-90001, "FAIL_GENERAL");
            }
        });
    }

    public void isNewUser(final ICloudVideoCallback<JSONObject> iCloudVideoCallback) {
        JSONObject jSONObject = new JSONObject();
        Locale I = CoreApi.a().I();
        if (I != null) {
            try {
                jSONObject.put("region", I.getCountry());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            jSONObject.put("region", Locale.getDefault().getCountry());
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().c("business.smartcamera.").a("GET").b(IS_NEW_USER).b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, new AsyncCallback<JSONObject, Error>() {
            public void onSuccess(JSONObject jSONObject) {
                if (iCloudVideoCallback != null) {
                    iCloudVideoCallback.onCloudVideoSuccess(jSONObject, (Object) null);
                }
            }

            public void onFailure(Error error) {
                if (iCloudVideoCallback == null) {
                    return;
                }
                if (error != null) {
                    ICloudVideoCallback iCloudVideoCallback = iCloudVideoCallback;
                    int a2 = error.a();
                    iCloudVideoCallback.onCloudVideoFailed(a2, "" + error.b());
                    return;
                }
                iCloudVideoCallback.onCloudVideoFailed(-90001, "FAIL_GENERAL");
            }
        });
    }

    public void getCloudStatus(Context context, String str, final ICloudVideoCallback<CloudVideoUserStatus> iCloudVideoCallback) {
        if (context != null && !TextUtils.isEmpty(str)) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("did", str);
                Locale I = CoreApi.a().I();
                if (I != null) {
                    jSONObject.put("region", I.getCountry());
                } else {
                    jSONObject.put("region", Locale.getDefault().getCountry());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KeyValuePair("data", jSONObject.toString()));
            Context context2 = context;
            CoreApi.a().a(context2, new NetRequest.Builder().c("business.smartcamera.").a("GET").b(CLOUD_COMMON_VIP_STATUS).b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
                public JSONObject parse(JSONObject jSONObject) throws JSONException {
                    return jSONObject;
                }
            }, Crypto.RC4, new AsyncCallback<JSONObject, Error>() {
                public void onSuccess(JSONObject jSONObject) {
                    if (iCloudVideoCallback == null) {
                        return;
                    }
                    if (jSONObject == null) {
                        iCloudVideoCallback.onCloudVideoFailed(-90002, "result is null");
                    } else if (jSONObject.optInt("code", -90003) == 0) {
                        JSONObject optJSONObject = jSONObject.optJSONObject("data");
                        if (optJSONObject != null) {
                            boolean optBoolean = optJSONObject.optBoolean("vip");
                            CloudVideoUserStatus cloudVideoUserStatus = new CloudVideoUserStatus();
                            if (optBoolean) {
                                cloudVideoUserStatus.vip = true;
                                cloudVideoUserStatus.status = optJSONObject.optInt("status");
                                cloudVideoUserStatus.startTime = optJSONObject.optLong("startTime");
                                cloudVideoUserStatus.endTime = optJSONObject.optLong("endTime");
                                cloudVideoUserStatus.packageType = optJSONObject.optString("pacakgeType");
                                cloudVideoUserStatus.rollingSaveInterval = CloudVideoUtils.getDayIntervalByTimestamp(optJSONObject.optLong("rollingSaveInterval"));
                            } else {
                                cloudVideoUserStatus.vip = false;
                                cloudVideoUserStatus.status = optJSONObject.optInt("status");
                                cloudVideoUserStatus.startTime = optJSONObject.optLong("startTime");
                                cloudVideoUserStatus.endTime = optJSONObject.optLong("endTime");
                                cloudVideoUserStatus.packageType = optJSONObject.optString("pacakgeType");
                            }
                            iCloudVideoCallback.onCloudVideoSuccess(cloudVideoUserStatus, (Object) null);
                            return;
                        }
                        iCloudVideoCallback.onCloudVideoFailed(-90002, "data is null");
                    } else {
                        iCloudVideoCallback.onCloudVideoFailed(-90002, "code is not 0");
                    }
                }

                public void onFailure(Error error) {
                    if (iCloudVideoCallback == null) {
                        return;
                    }
                    if (error != null) {
                        ICloudVideoCallback iCloudVideoCallback = iCloudVideoCallback;
                        int a2 = error.a();
                        iCloudVideoCallback.onCloudVideoFailed(a2, "" + error.b());
                        return;
                    }
                    iCloudVideoCallback.onCloudVideoFailed(-90001, "FAIL_GENERAL");
                }
            });
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003f A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0040  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.util.Pair<java.util.List<com.xiaomi.smarthome.core.entity.net.KeyValuePair>, java.lang.String> paramEncrypt(com.xiaomi.smarthome.core.entity.net.NetRequest r11) {
        /*
            r10 = this;
            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r0 = r10.getTokenInfo()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            long r2 = r0.e
            java.lang.String r2 = com.xiaomi.smarthome.library.crypto.CloudCoder.a((long) r2)
            java.lang.String r3 = r0.d     // Catch:{ NoSuchAlgorithmException -> 0x0035, InvalidKeyException -> 0x002d, Exception -> 0x0025 }
            byte[] r3 = com.xiaomi.smarthome.library.crypto.rc4coder.Coder.a((java.lang.String) r3)     // Catch:{ NoSuchAlgorithmException -> 0x0035, InvalidKeyException -> 0x002d, Exception -> 0x0025 }
            byte[] r4 = com.xiaomi.smarthome.library.crypto.rc4coder.Coder.a((java.lang.String) r2)     // Catch:{ NoSuchAlgorithmException -> 0x0035, InvalidKeyException -> 0x002d, Exception -> 0x0025 }
            byte[] r3 = r10.byteArraysConcat(r3, r4)     // Catch:{ NoSuchAlgorithmException -> 0x0035, InvalidKeyException -> 0x002d, Exception -> 0x0025 }
            byte[] r3 = com.xiaomi.smarthome.library.crypto.rc4coder.Coder.d(r3)     // Catch:{ NoSuchAlgorithmException -> 0x0035, InvalidKeyException -> 0x002d, Exception -> 0x0025 }
            java.lang.String r3 = com.xiaomi.smarthome.library.crypto.rc4coder.Coder.a((byte[]) r3)     // Catch:{ NoSuchAlgorithmException -> 0x0035, InvalidKeyException -> 0x002d, Exception -> 0x0025 }
            goto L_0x003d
        L_0x0025:
            java.lang.String r3 = "CloudVideoNetUtils"
            java.lang.String r4 = "generate sessionSecurity fail"
            android.util.Log.d(r3, r4)
            goto L_0x003c
        L_0x002d:
            java.lang.String r3 = "CloudVideoNetUtils"
            java.lang.String r4 = "generate sessionSecurity fail:InvalidKeyException"
            android.util.Log.d(r3, r4)
            goto L_0x003c
        L_0x0035:
            java.lang.String r3 = "CloudVideoNetUtils"
            java.lang.String r4 = "generate sessionSecurity fail:NoSuchAlgorithmException"
            android.util.Log.d(r3, r4)
        L_0x003c:
            r3 = r1
        L_0x003d:
            if (r3 != 0) goto L_0x0040
            return r1
        L_0x0040:
            java.util.TreeMap r1 = new java.util.TreeMap
            r1.<init>()
            java.util.TreeMap r4 = new java.util.TreeMap
            r4.<init>()
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            com.xiaomi.smarthome.library.crypto.rc4coder.RC4DropCoder r6 = new com.xiaomi.smarthome.library.crypto.rc4coder.RC4DropCoder
            r6.<init>((java.lang.String) r3)
            java.util.List r7 = r11.e()
            if (r7 == 0) goto L_0x008a
            java.util.Iterator r7 = r7.iterator()
        L_0x005e:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x008a
            java.lang.Object r8 = r7.next()
            com.xiaomi.smarthome.core.entity.net.KeyValuePair r8 = (com.xiaomi.smarthome.core.entity.net.KeyValuePair) r8
            java.lang.String r9 = r8.a()
            boolean r9 = android.text.TextUtils.isEmpty(r9)
            if (r9 != 0) goto L_0x005e
            java.lang.String r9 = r8.b()
            boolean r9 = android.text.TextUtils.isEmpty(r9)
            if (r9 != 0) goto L_0x005e
            java.lang.String r9 = r8.a()
            java.lang.String r8 = r8.b()
            r4.put(r9, r8)
            goto L_0x005e
        L_0x008a:
            java.lang.String r7 = r11.a()
            java.lang.String r8 = r11.b()
            java.lang.String r7 = com.xiaomi.smarthome.library.crypto.CloudCoder.a(r7, r8, r4, r3)
            java.lang.String r8 = "rc4_hash__"
            r4.put(r8, r7)
            java.util.Set r4 = r4.entrySet()
            java.util.Iterator r4 = r4.iterator()
        L_0x00a3:
            boolean r7 = r4.hasNext()
            if (r7 == 0) goto L_0x00cf
            java.lang.Object r7 = r4.next()
            java.util.Map$Entry r7 = (java.util.Map.Entry) r7
            java.lang.Object r8 = r7.getValue()
            java.lang.String r8 = (java.lang.String) r8
            java.lang.String r8 = r6.b((java.lang.String) r8)
            java.lang.Object r9 = r7.getKey()
            r1.put(r9, r8)
            com.xiaomi.smarthome.core.entity.net.KeyValuePair r9 = new com.xiaomi.smarthome.core.entity.net.KeyValuePair
            java.lang.Object r7 = r7.getKey()
            java.lang.String r7 = (java.lang.String) r7
            r9.<init>(r7, r8)
            r5.add(r9)
            goto L_0x00a3
        L_0x00cf:
            java.lang.String r4 = r11.a()
            java.lang.String r11 = r11.b()
            java.lang.String r11 = com.xiaomi.smarthome.library.crypto.CloudCoder.a(r4, r11, r1, r3)
            com.xiaomi.smarthome.core.entity.net.KeyValuePair r1 = new com.xiaomi.smarthome.core.entity.net.KeyValuePair
            java.lang.String r3 = "signature"
            r1.<init>(r3, r11)
            r5.add(r1)
            com.xiaomi.smarthome.core.entity.net.KeyValuePair r11 = new com.xiaomi.smarthome.core.entity.net.KeyValuePair
            java.lang.String r1 = "_nonce"
            r11.<init>(r1, r2)
            r5.add(r11)
            com.xiaomi.smarthome.core.entity.net.KeyValuePair r11 = new com.xiaomi.smarthome.core.entity.net.KeyValuePair
            java.lang.String r1 = "ssecurity"
            java.lang.String r0 = r0.d
            r11.<init>(r1, r0)
            r5.add(r11)
            android.util.Pair r11 = android.util.Pair.create(r5, r2)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils.paramEncrypt(com.xiaomi.smarthome.core.entity.net.NetRequest):android.util.Pair");
    }

    public MiServiceTokenInfo getTokenInfo() {
        return CoreApi.a().a("xiaomiio");
    }

    public MiServiceTokenInfo getYPTokenInfo() {
        return CoreApi.a().a("miotstore");
    }

    private byte[] byteArraysConcat(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }
}
