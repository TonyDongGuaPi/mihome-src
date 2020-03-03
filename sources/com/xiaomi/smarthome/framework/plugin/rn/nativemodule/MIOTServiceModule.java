package com.xiaomi.smarthome.framework.plugin.rn.nativemodule;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableMap;
import com.unionpay.tsmservice.data.Constant;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.device.api.UserInfo;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.frame.plugin.host.PluginHostApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.plugin.rn.constants.RnApiErrorInfo;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnCallbackMapUtil;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;
import com.xiaomi.smarthome.international.ServerApi;
import com.xiaomi.smarthome.miio.db.record.ShareUserRecord;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MIOTServiceModule extends MIOTBaseJavaModule {
    public static final Parser<String> PARSERFORRAWRESULT = new PluginHostApi.ParserForRawResult<String>() {
        /* renamed from: a */
        public String parse(String str) throws JSONException {
            return str;
        }
    };
    private OkHttpClient mOkHttpClient = null;

    static /* synthetic */ void lambda$getServerName$0() throws Exception {
    }

    public String getName() {
        return "MIOTService";
    }

    public MIOTServiceModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Nullable
    public Map<String, Object> getConstants() {
        PluginRecord pluginRecord = getPluginRecord();
        HashMap hashMap = new HashMap();
        hashMap.put("currentAccountID", XmPluginHostApi.instance().getAccountId());
        if (pluginRecord != null) {
            hashMap.put("pluginID", Long.valueOf(pluginRecord.d()));
        }
        hashMap.put("currentServerName", XmPluginHostApi.instance().getGlobalSettingServer(false));
        return hashMap;
    }

    @ReactMethod
    public void callSmartHomeAPI(String str, String str2, final Callback callback) {
        XmPluginHostApi.instance().callSmartHomeApi((String) null, str, str2, new com.xiaomi.smarthome.device.api.Callback<String>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                callback.invoke(true, str);
            }

            public void onFailure(int i, String str) {
                callback.invoke(false, Integer.valueOf(i), str);
            }
        }, PARSERFORRAWRESULT);
    }

    @ReactMethod
    public void addLog(String str, String str2) {
        XmPluginHostApi.instance().logForModel(str, str2);
    }

    @SuppressLint({"CheckResult"})
    @ReactMethod
    public void getServerName(final Callback callback) {
        final WritableMap createMap = Arguments.createMap();
        ServerBean a2 = ServerCompact.a((Context) getReactApplicationContext());
        if (a2 != null) {
            createMap.putString("serverCode", a2.f1530a);
            createMap.putString(Constant.KEY_COUNTRY_CODE, a2.b);
        }
        ServerApi.a().a(a2).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            /* renamed from: a */
            public void accept(String str) throws Exception {
                createMap.putString("countryName", str);
                callback.invoke(createMap);
            }
        }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE, $$Lambda$MIOTServiceModule$OwmJxmVjGqGOFoc15zLY1nlJ9gU.INSTANCE);
    }

    @ReactMethod
    public void editSceneRecord(ReadableMap readableMap, final Callback callback) {
        JSONArray jSONArray;
        JSONObject jSONObject;
        JSONObject jSONObject2;
        JSONObject jSONObject3;
        if (callback != null) {
            if (readableMap == null) {
                callback.invoke(false, "params is null...");
                return;
            }
            String b = MIOTUtils.b(readableMap, "model", "");
            int d = MIOTUtils.d(readableMap, "st_id");
            String b2 = MIOTUtils.b(readableMap, "us_id", "");
            String b3 = MIOTUtils.b(readableMap, "did", "");
            String b4 = MIOTUtils.b(readableMap, "identify", "");
            String b5 = MIOTUtils.b(readableMap, "name", "");
            ReadableMap f = MIOTUtils.f(readableMap, a.j);
            ReadableArray e = MIOTUtils.e(readableMap, "authed");
            try {
                jSONObject2 = new JSONObject(f.toString());
                try {
                    jSONObject3 = jSONObject2.has("NativeMap") ? jSONObject2.getJSONObject("NativeMap") : jSONObject2;
                } catch (JSONException e2) {
                    e = e2;
                    RnPluginLog.b(e.toString());
                    jSONArray = null;
                    jSONObject = jSONObject2;
                    XmPluginHostApi.instance().editSceneV2(b, d, b2, b3, b4, b5, jSONObject, jSONArray, new com.xiaomi.smarthome.device.api.Callback<JSONObject>() {
                        /* renamed from: a */
                        public void onSuccess(JSONObject jSONObject) {
                            Callback callback = callback;
                            Object[] objArr = new Object[2];
                            objArr[0] = true;
                            if (jSONObject == null) {
                                jSONObject = new JSONObject();
                            }
                            objArr[1] = jSONObject.toString();
                            callback.invoke(objArr);
                        }

                        public void onFailure(int i, String str) {
                            Callback callback = callback;
                            callback.invoke(false, "code: " + i + "   errorInfo: " + str);
                        }
                    });
                } catch (Exception e3) {
                    e = e3;
                    RnPluginLog.b(e.toString());
                    jSONArray = null;
                    jSONObject = jSONObject2;
                    XmPluginHostApi.instance().editSceneV2(b, d, b2, b3, b4, b5, jSONObject, jSONArray, new com.xiaomi.smarthome.device.api.Callback<JSONObject>() {
                        /* renamed from: a */
                        public void onSuccess(JSONObject jSONObject) {
                            Callback callback = callback;
                            Object[] objArr = new Object[2];
                            objArr[0] = true;
                            if (jSONObject == null) {
                                jSONObject = new JSONObject();
                            }
                            objArr[1] = jSONObject.toString();
                            callback.invoke(objArr);
                        }

                        public void onFailure(int i, String str) {
                            Callback callback = callback;
                            callback.invoke(false, "code: " + i + "   errorInfo: " + str);
                        }
                    });
                }
                try {
                    jSONObject = jSONObject3;
                    jSONArray = new JSONArray(e.toString());
                } catch (JSONException e4) {
                    e = e4;
                    jSONObject2 = jSONObject3;
                    RnPluginLog.b(e.toString());
                    jSONArray = null;
                    jSONObject = jSONObject2;
                    XmPluginHostApi.instance().editSceneV2(b, d, b2, b3, b4, b5, jSONObject, jSONArray, new com.xiaomi.smarthome.device.api.Callback<JSONObject>() {
                        /* renamed from: a */
                        public void onSuccess(JSONObject jSONObject) {
                            Callback callback = callback;
                            Object[] objArr = new Object[2];
                            objArr[0] = true;
                            if (jSONObject == null) {
                                jSONObject = new JSONObject();
                            }
                            objArr[1] = jSONObject.toString();
                            callback.invoke(objArr);
                        }

                        public void onFailure(int i, String str) {
                            Callback callback = callback;
                            callback.invoke(false, "code: " + i + "   errorInfo: " + str);
                        }
                    });
                } catch (Exception e5) {
                    e = e5;
                    jSONObject2 = jSONObject3;
                    RnPluginLog.b(e.toString());
                    jSONArray = null;
                    jSONObject = jSONObject2;
                    XmPluginHostApi.instance().editSceneV2(b, d, b2, b3, b4, b5, jSONObject, jSONArray, new com.xiaomi.smarthome.device.api.Callback<JSONObject>() {
                        /* renamed from: a */
                        public void onSuccess(JSONObject jSONObject) {
                            Callback callback = callback;
                            Object[] objArr = new Object[2];
                            objArr[0] = true;
                            if (jSONObject == null) {
                                jSONObject = new JSONObject();
                            }
                            objArr[1] = jSONObject.toString();
                            callback.invoke(objArr);
                        }

                        public void onFailure(int i, String str) {
                            Callback callback = callback;
                            callback.invoke(false, "code: " + i + "   errorInfo: " + str);
                        }
                    });
                }
            } catch (JSONException e6) {
                e = e6;
                jSONObject2 = null;
                RnPluginLog.b(e.toString());
                jSONArray = null;
                jSONObject = jSONObject2;
                XmPluginHostApi.instance().editSceneV2(b, d, b2, b3, b4, b5, jSONObject, jSONArray, new com.xiaomi.smarthome.device.api.Callback<JSONObject>() {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        Callback callback = callback;
                        Object[] objArr = new Object[2];
                        objArr[0] = true;
                        if (jSONObject == null) {
                            jSONObject = new JSONObject();
                        }
                        objArr[1] = jSONObject.toString();
                        callback.invoke(objArr);
                    }

                    public void onFailure(int i, String str) {
                        Callback callback = callback;
                        callback.invoke(false, "code: " + i + "   errorInfo: " + str);
                    }
                });
            } catch (Exception e7) {
                e = e7;
                jSONObject2 = null;
                RnPluginLog.b(e.toString());
                jSONArray = null;
                jSONObject = jSONObject2;
                XmPluginHostApi.instance().editSceneV2(b, d, b2, b3, b4, b5, jSONObject, jSONArray, new com.xiaomi.smarthome.device.api.Callback<JSONObject>() {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        Callback callback = callback;
                        Object[] objArr = new Object[2];
                        objArr[0] = true;
                        if (jSONObject == null) {
                            jSONObject = new JSONObject();
                        }
                        objArr[1] = jSONObject.toString();
                        callback.invoke(objArr);
                    }

                    public void onFailure(int i, String str) {
                        Callback callback = callback;
                        callback.invoke(false, "code: " + i + "   errorInfo: " + str);
                    }
                });
            }
            XmPluginHostApi.instance().editSceneV2(b, d, b2, b3, b4, b5, jSONObject, jSONArray, new com.xiaomi.smarthome.device.api.Callback<JSONObject>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    Callback callback = callback;
                    Object[] objArr = new Object[2];
                    objArr[0] = true;
                    if (jSONObject == null) {
                        jSONObject = new JSONObject();
                    }
                    objArr[1] = jSONObject.toString();
                    callback.invoke(objArr);
                }

                public void onFailure(int i, String str) {
                    Callback callback = callback;
                    callback.invoke(false, "code: " + i + "   errorInfo: " + str);
                }
            });
        }
    }

    @ReactMethod
    public void deleteSceneRecords(ReadableArray readableArray, final Callback callback) {
        if (callback != null) {
            if (readableArray == null) {
                callback.invoke(false, "params is null...");
            } else if (getDevice() == null) {
                callback.invoke(false, "current device is null...");
            } else {
                XmPluginHostApi.instance().delScenes(getDevice().model, MIOTUtils.b(readableArray), new com.xiaomi.smarthome.device.api.Callback<JSONObject>() {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        Callback callback = callback;
                        Object[] objArr = new Object[2];
                        objArr[0] = true;
                        if (jSONObject == null) {
                            jSONObject = new JSONObject();
                        }
                        objArr[1] = jSONObject.toString();
                        callback.invoke(objArr);
                    }

                    public void onFailure(int i, String str) {
                        Callback callback = callback;
                        callback.invoke(false, "code: " + i + "   errorInfo: " + str);
                    }
                });
            }
        }
    }

    @ReactMethod
    public void loadAccountInfo(String str, final Callback callback) {
        XmPluginHostApi.instance().getUserInfo(str, new com.xiaomi.smarthome.device.api.Callback<UserInfo>() {
            /* renamed from: a */
            public void onSuccess(UserInfo userInfo) {
                WritableMap createMap = Arguments.createMap();
                createMap.putString("id", userInfo.userId);
                createMap.putString("nickName", userInfo.nickName);
                createMap.putString("avatarURL", userInfo.url);
                createMap.putString(ShareUserRecord.FIELD_BIRTH, userInfo.birth);
                createMap.putString("email", userInfo.email);
                createMap.putString("phone", userInfo.phone);
                createMap.putString(ShareUserRecord.FIELD_SEX, userInfo.sex);
                createMap.putString(ShareUserRecord.FIELD_SHARE_TIME, String.valueOf(userInfo.shareTime));
                callback.invoke(true, createMap);
            }

            public void onFailure(int i, String str) {
                callback.invoke(false, RnCallbackMapUtil.a(i, str));
            }
        });
    }

    @ReactMethod
    public void callSpecificAPI(String str, String str2, ReadableMap readableMap, final Callback callback) {
        Request request;
        if (callback != null) {
            if (TextUtils.isEmpty(str)) {
                callback.invoke(false, RnCallbackMapUtil.a(RnApiErrorInfo.PARAMS_ERROR, "url is empty..."));
            } else if (getDevice() == null) {
                callback.invoke(false, RnCallbackMapUtil.a(RnApiErrorInfo.DEVICE_ERROR, "current device is null..."));
            } else if (!checkSpecificAPIAuthority(getDevice().model)) {
                callback.invoke(false, RnCallbackMapUtil.a(RnApiErrorInfo.AUTHORITY_ERROR, "current device has no authority..."));
            } else {
                if (this.mOkHttpClient == null) {
                    this.mOkHttpClient = new OkHttpClient();
                }
                if ("get".equalsIgnoreCase(str2)) {
                    request = new Request.Builder().url(str).get().build();
                } else if ("post".equalsIgnoreCase(str2)) {
                    FormBody.Builder builder = new FormBody.Builder();
                    if (readableMap != null) {
                        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
                        while (keySetIterator.hasNextKey()) {
                            String nextKey = keySetIterator.nextKey();
                            builder.add(nextKey, MIOTUtils.b(readableMap, nextKey, ""));
                        }
                    }
                    request = new Request.Builder().url(str).post(builder.build()).build();
                } else {
                    RnApiErrorInfo rnApiErrorInfo = RnApiErrorInfo.REQUEST_ERROR;
                    callback.invoke(false, RnCallbackMapUtil.a(rnApiErrorInfo, "can not support this method, method is " + str2));
                    return;
                }
                this.mOkHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
                    public void onFailure(Call call, IOException iOException) {
                        callback.invoke(false, RnCallbackMapUtil.a(RnApiErrorInfo.INVALID_ERROR, iOException.toString()));
                    }

                    public void onResponse(Call call, Response response) throws IOException {
                        if (!response.isSuccessful()) {
                            callback.invoke(false, RnCallbackMapUtil.a(response.code(), response.message()));
                            if (response.body() != null) {
                                response.close();
                                return;
                            }
                            return;
                        }
                        try {
                            String string = response.body().string();
                            callback.invoke(true, string);
                        } catch (IOException e) {
                            callback.invoke(false, RnCallbackMapUtil.a(RnApiErrorInfo.INVALID_ERROR, e.toString()));
                        }
                    }
                });
            }
        }
    }

    private boolean checkSpecificAPIAuthority(String str) {
        if (!TextUtils.isEmpty(str) && str.startsWith("xiaomi.router")) {
            return true;
        }
        return false;
    }

    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        this.mOkHttpClient = null;
    }
}
