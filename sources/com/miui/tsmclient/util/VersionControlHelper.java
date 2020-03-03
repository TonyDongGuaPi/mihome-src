package com.miui.tsmclient.util;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.miui.tsmclient.entity.VersionControlInfo;
import com.miui.tsmclient.net.AuthApiException;
import com.miui.tsmclient.net.TSMAuthContants;
import com.miui.tsmclient.net.TSMAuthManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class VersionControlHelper {
    public static final String KEY_MATCH = "key_match_";
    public static final int KEY_NEVER_MATCH_CARD_ERROR = -2;
    public static final String KEY_PROTOCOLS = "protocols";
    public static final int KEY_QUERY_SERVICE_FAIL = -1;
    public static final String NEED_USER_PROTOCOL_ID = "user_protocol_id";
    private static volatile VersionControlHelper sMVersionControlHelper;
    private Subscription mConfirmSubscription;
    private Subscription mGetVersionSubscription;
    /* access modifiers changed from: private */
    public Map<String, Boolean> mMatchResultMap = new HashMap();
    /* access modifiers changed from: private */
    public TSMAuthManager mTSMAuthManager = new TSMAuthManager();
    private VersionControlInfo mVersionControlInfo;

    public interface ActionCallBackListener {
        void onFailure(Integer num);

        void onSuccess(VersionControlInfo versionControlInfo);
    }

    public VersionControlInfo getVersionControlInfo() {
        return this.mVersionControlInfo;
    }

    public void setVersionControlInfo(VersionControlInfo versionControlInfo) {
        this.mVersionControlInfo = versionControlInfo;
    }

    private VersionControlHelper() {
    }

    /* access modifiers changed from: private */
    public void put(String str, boolean z) {
        if (this.mMatchResultMap == null) {
            this.mMatchResultMap = new HashMap();
        }
        this.mMatchResultMap.put(str, Boolean.valueOf(z));
    }

    /* access modifiers changed from: private */
    public boolean get(String str) {
        Boolean bool;
        if (this.mMatchResultMap == null || (bool = this.mMatchResultMap.get(str)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public static VersionControlHelper getInstance() {
        if (sMVersionControlHelper == null) {
            synchronized (VersionControlHelper.class) {
                if (sMVersionControlHelper == null) {
                    sMVersionControlHelper = new VersionControlHelper();
                }
            }
        }
        return sMVersionControlHelper;
    }

    public static boolean getHasAlreadyUploadPhoneNumber(Context context, String str) {
        return PrefUtils.getBoolean(context, PrefUtils.PREF_KEY_PHONE_NUMBER_HAS_UPLOAD + str, false);
    }

    public static void putHasAlreadyUploadPhoneNumber(Context context, String str, boolean z) {
        PrefUtils.putBoolean(context, PrefUtils.PREF_KEY_PHONE_NUMBER_HAS_UPLOAD + str, z);
    }

    public static void putVersionControlId(Context context, String str, long j) {
        PrefUtils.putLong(context, PrefUtils.PREF_KEY_VERSION_CONTROL_ID + str, j);
    }

    public static String uploadPhoneNumber(Context context, String str, long j) {
        if (getHasAlreadyUploadPhoneNumber(context, str) || j <= 0) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(NEED_USER_PROTOCOL_ID, j);
            return jSONObject.toString();
        } catch (JSONException e) {
            LogUtils.e("upload phone number failed with an JSONException", e);
            return "";
        }
    }

    public void confirmUploadPhoneNumber(Context context, String str, long j) {
        putHasAlreadyUploadPhoneNumber(context, str, true);
        confirmProtocolVersion(context, j, (ActionCallBackListener) null);
    }

    public void queryAllServiceProtocol(final Context context, final String str, final TSMAuthContants.ActionType actionType, final ActionCallBackListener actionCallBackListener) {
        final String str2 = KEY_MATCH + str;
        this.mGetVersionSubscription = Observable.fromCallable(new Callable<JSONObject>() {
            public JSONObject call() throws Exception {
                return VersionControlHelper.this.mTSMAuthManager.queryAllServiceProtocol(context, str, actionType);
            }
        }).flatMap(new Func1<JSONObject, Observable<VersionControlInfo>>() {
            public Observable<VersionControlInfo> call(JSONObject jSONObject) {
                LogUtils.d("queryAllServiceProtocol api response: " + jSONObject);
                if (jSONObject == null || !jSONObject.has(VersionControlHelper.KEY_PROTOCOLS)) {
                    return Observable.empty();
                }
                try {
                    JSONArray jSONArray = jSONObject.getJSONArray(VersionControlHelper.KEY_PROTOCOLS);
                    return Observable.from((List) new Gson().fromJson(jSONArray.toString(), new TypeToken<ArrayList<VersionControlInfo>>() {
                    }.getType()));
                } catch (Exception e) {
                    LogUtils.e("queryAllServiceProtocol response parse failed!", e);
                    return Observable.empty();
                }
            }
        }).filter(new Func1<VersionControlInfo, Boolean>() {
            public Boolean call(VersionControlInfo versionControlInfo) {
                return Boolean.valueOf(versionControlInfo != null && TextUtils.equals(str, versionControlInfo.mServiceName));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<VersionControlInfo>() {
            public void onCompleted() {
                LogUtils.d("queryAllServiceProtocol onCompleted called!");
                if (!VersionControlHelper.this.get(str2)) {
                    actionCallBackListener.onFailure(-2);
                    VersionControlHelper.this.mMatchResultMap.clear();
                }
            }

            public void onError(Throwable th) {
                LogUtils.e("queryAllServiceProtocol onError called! throwable:" + th);
                VersionControlHelper.this.put(str2, true);
                if (actionCallBackListener == null) {
                    return;
                }
                if (th instanceof AuthApiException) {
                    actionCallBackListener.onFailure(Integer.valueOf(((AuthApiException) th).mErrorCode));
                } else {
                    actionCallBackListener.onFailure(-1);
                }
            }

            public void onNext(VersionControlInfo versionControlInfo) {
                LogUtils.d("queryAllServiceProtocol onNext called!");
                if (actionCallBackListener != null) {
                    VersionControlHelper.this.setVersionControlInfo(versionControlInfo);
                    actionCallBackListener.onSuccess(versionControlInfo);
                    VersionControlHelper.this.put(str2, true);
                }
            }
        });
    }

    public void confirmProtocolVersion(final Context context, final long j, final ActionCallBackListener actionCallBackListener) {
        this.mConfirmSubscription = Observable.fromCallable(new Callable<JSONObject>() {
            public JSONObject call() throws Exception {
                return VersionControlHelper.this.mTSMAuthManager.confirmProtocolVersion(context, j);
            }
        }).map(new Func1<JSONObject, Boolean>() {
            public Boolean call(JSONObject jSONObject) {
                return true;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Boolean>() {
            public void onCompleted() {
                LogUtils.d("confirmProtocolVersion onCompleted called!");
            }

            public void onError(Throwable th) {
                LogUtils.e("confirmProtocolVersion onError called! throwable:" + th);
                if (th instanceof AuthApiException) {
                    AuthApiException authApiException = (AuthApiException) th;
                    if (actionCallBackListener != null) {
                        actionCallBackListener.onFailure(Integer.valueOf(authApiException.mErrorCode));
                    }
                }
            }

            public void onNext(Boolean bool) {
                LogUtils.d("confirmProtocolVersion onNext called!");
                if (bool.booleanValue() && actionCallBackListener != null) {
                    actionCallBackListener.onSuccess((VersionControlInfo) null);
                }
            }
        });
    }

    public void release() {
        if (this.mGetVersionSubscription != null) {
            this.mGetVersionSubscription.unsubscribe();
            this.mGetVersionSubscription = null;
        }
        if (this.mConfirmSubscription != null) {
            this.mConfirmSubscription.unsubscribe();
            this.mConfirmSubscription = null;
        }
        this.mVersionControlInfo = null;
    }
}
