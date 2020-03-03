package com.miui.tsmclient.common.net.request;

import android.content.Context;
import android.support.annotation.NonNull;
import com.google.gson.reflect.TypeToken;
import com.miui.tsmclient.common.data.CommonResponseInfo;
import com.miui.tsmclient.common.net.ErrorInfo;
import com.miui.tsmclient.common.net.RequestCallback;
import com.miui.tsmclient.common.net.ResponseListener;
import com.miui.tsmclient.common.net.host.AuthHost;
import com.miui.tsmclient.common.net.host.Host;
import com.miui.tsmclient.entity.CardInfo;
import com.miui.tsmclient.net.TSMAuthContants;
import com.miui.tsmclient.util.CardEnvironmentConfig;
import com.miui.tsmclient.util.DeviceUtils;
import com.miui.tsmclient.util.EnvironmentConfig;
import java.util.Locale;

public class SecureRequest<T extends CommonResponseInfo> extends GsonRequest<T> {
    public SecureRequest(int i, String str, TypeToken<T> typeToken) {
        this(i, str, typeToken, (ResponseListener) null);
    }

    public SecureRequest(int i, String str, Class<T> cls) {
        this(i, str, TypeToken.get(cls));
    }

    public SecureRequest(int i, String str, Class<T> cls, ResponseListener<T> responseListener) {
        this(i, str, TypeToken.get(cls), responseListener);
    }

    public SecureRequest(int i, String str, TypeToken<T> typeToken, final ResponseListener<T> responseListener) {
        super(i, str, typeToken, new RequestCallback<T>() {
            public void onResponse(@NonNull T t) {
                if (ResponseListener.this != null) {
                    if (t.isSuccess()) {
                        ResponseListener.this.onSuccess(t);
                    } else {
                        ResponseListener.this.onFailed(t.getErrorCode(), t.getErrorDesc(), t);
                    }
                }
            }

            public void onFailed(ErrorInfo errorInfo) {
                if (ResponseListener.this != null) {
                    ResponseListener.this.onFailed(2, "", null);
                }
            }
        });
        this.mRequestType = CardEnvironmentConfig.getDefaultSecureType();
        addParams(TSMAuthContants.PARAM_MIUI_ROM_TYPE, DeviceUtils.getMIUIRomType((CardInfo) null));
        addParams(TSMAuthContants.PARAM_MIUI_SYSTEM_VERSION, DeviceUtils.getRomVersion());
        addParams(TSMAuthContants.PARAM_ANDROID_VERSION, DeviceUtils.getAndroidVersion());
        addParams("deviceModel", DeviceUtils.getDeviceModel((CardInfo) null));
        addParams(TSMAuthContants.PARAM_LANGUAGE, Locale.getDefault().toString());
        Context context = EnvironmentConfig.getContext();
        if (context != null) {
            addParams(TSMAuthContants.PARAM_TSMCLIENT_VERSION_CODE, String.valueOf(DeviceUtils.getAppVersionCode(context, "com.miui.tsmclient")));
            addParams(TSMAuthContants.PARAM_TSMCLIENT_VERSION_NAME, String.valueOf(DeviceUtils.getAppVersionName(context, "com.miui.tsmclient")));
            addParams(TSMAuthContants.PARAM_APP_VERSION_NAME, String.valueOf(DeviceUtils.getAppVersionCode(context)));
            addParams(TSMAuthContants.PARAM_APP_VERSION_CODE, String.valueOf(DeviceUtils.getAppVersionName(context)));
        }
    }

    public String getUrl() {
        String stagingHost = EnvironmentConfig.isStaging() ? this.mHost.getStagingHost() : this.mHost.getOnlineHost();
        return String.format(stagingHost + this.mPath, new Object[]{this.mHost.getAuthType()});
    }

    public Host onCreateHost() {
        return new AuthHost();
    }

    public boolean isSuccess() {
        return super.isSuccess() && getResult() != null && ((CommonResponseInfo) getResult()).isSuccess();
    }
}
