package com.xiaomi.mishopsdk.io.http;

import android.text.TextUtils;
import com.mishopsdk.volley.NetworkResponse;
import com.mishopsdk.volley.VolleyError;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.mishopsdk.R;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.io.entity.ShopApiBaseResult;
import com.xiaomi.mishopsdk.util.ToastUtil;

public class ShopApiError extends VolleyError {
    private ShopApiBaseResult mApiBaseResult;
    private ErrorType mErrorType = ErrorType.UNKNOW;

    public ShopApiError(NetworkResponse networkResponse) {
        super(networkResponse);
    }

    public ShopApiError(Throwable th, NetworkResponse networkResponse) {
        super(th, networkResponse);
    }

    public ShopApiBaseResult getApiBaseResult() {
        return this.mApiBaseResult;
    }

    public void setApiBaseResult(ShopApiBaseResult shopApiBaseResult) {
        this.mApiBaseResult = shopApiBaseResult;
    }

    public enum ErrorType {
        NOCONNECTION(R.string.mishopsdk_network_errortip_noconnection, R.string.mishopsdk_network_errorbtntip_noconnection),
        SERVER(R.string.mishopsdk_network_errortip_server, R.string.mishopsdk_network_errorbtntip_server),
        NETWORK(R.string.mishopsdk_network_errortip_server, R.string.mishopsdk_network_errorbtntip_server),
        TIMEOUT(R.string.mishopsdk_network_errortip_timeout, R.string.mishopsdk_network_errorbtntip_timeout),
        PARSE(R.string.mishopsdk_network_errortip_parse, R.string.mishopsdk_network_errorbtntip_parse),
        CUSTOM(R.string.mishopsdk_network_errortip_custom, R.string.mishopsdk_network_errorbtntip_custom),
        UNKNOW(R.string.mishopsdk_network_errortip_unknow, R.string.mishopsdk_network_errorbtntip_unknow),
        LOGIN(R.string.mishopsdk_network_errortip_login, R.string.mishopsdk_network_errorbtntip_login);
        
        private int mErrorBtnRes;
        private int mErrorTipRes;

        private ErrorType(int i, int i2) {
            this.mErrorTipRes = i;
            this.mErrorBtnRes = i2;
        }

        public int getErrorTipRes() {
            return this.mErrorTipRes;
        }

        public int getErrorBtnRes() {
            return this.mErrorBtnRes;
        }
    }

    public void setErrorType(ErrorType errorType) {
        this.mErrorType = errorType;
    }

    public ErrorType getErrorType() {
        return this.mErrorType;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ShopApiError{Cause=");
        sb.append(getCause() == null ? null : getCause().toString());
        sb.append(", mApiBaseResult=");
        sb.append(this.mApiBaseResult);
        sb.append(", mErrorType=");
        sb.append(this.mErrorType);
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }

    public static void toastError(VolleyError volleyError) {
        if (volleyError instanceof ShopApiError) {
            ShopApiError shopApiError = (ShopApiError) volleyError;
            if (shopApiError.getErrorType() != ErrorType.CUSTOM || TextUtils.isEmpty(shopApiError.getApiBaseResult().mDescription)) {
                ToastUtil.show(ShopApp.instance.getString(shopApiError.getErrorType().getErrorTipRes()));
            } else {
                ToastUtil.show(shopApiError.getApiBaseResult().mDescription);
            }
        } else {
            ToastUtil.show(ShopApp.instance.getString(ErrorType.SERVER.getErrorTipRes()));
        }
    }
}
