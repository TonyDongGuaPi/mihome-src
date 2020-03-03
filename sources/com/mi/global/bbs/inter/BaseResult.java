package com.mi.global.bbs.inter;

import android.text.TextUtils;
import com.mi.global.bbs.BBSApplication;
import com.mi.global.bbs.R;
import com.mi.multimonitor.Request;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public abstract class BaseResult {
    private static Map<ResultStatus, Integer> RESULT_STATUS_DES = new HashMap();
    public String closeLink;
    public boolean isClosed;
    private String mErrorDesc;
    private boolean mIsError;
    private ResultStatus mResultStatus = ResultStatus.OK;

    public enum ResultStatus {
        NETWROK_ERROR,
        SERVICE_ERROR,
        DATA_ERROR,
        AUTH_ERROR,
        OK,
        CLOSED
    }

    /* access modifiers changed from: protected */
    public abstract int getCount();

    public abstract BaseResult shallowClone();

    static {
        RESULT_STATUS_DES.put(ResultStatus.NETWROK_ERROR, Integer.valueOf(R.string.bbs_network_unavaliable));
        RESULT_STATUS_DES.put(ResultStatus.SERVICE_ERROR, Integer.valueOf(R.string.bbs_service_unavailiable));
        RESULT_STATUS_DES.put(ResultStatus.DATA_ERROR, Integer.valueOf(R.string.bbs_data_error));
        RESULT_STATUS_DES.put(ResultStatus.AUTH_ERROR, Integer.valueOf(R.string.bbs_auth_error));
    }

    public static int getStatusDes(ResultStatus resultStatus) {
        return RESULT_STATUS_DES.get(resultStatus).intValue();
    }

    public ResultStatus getResultStatus() {
        return this.mResultStatus;
    }

    public void setResultStatus(ResultStatus resultStatus) {
        this.mResultStatus = resultStatus;
    }

    public void parseError(JSONObject jSONObject) {
        this.mIsError = true;
        this.isClosed = false;
        this.mErrorDesc = BBSApplication.getInstance().getResources().getString(R.string.bbs_data_error);
        if (jSONObject != null) {
            if (jSONObject.optInt(Request.RESULT_CODE_KEY) == 0) {
                this.mIsError = false;
                return;
            }
            String optString = jSONObject.optString("errmsg");
            if (!TextUtils.isEmpty(optString)) {
                this.mErrorDesc = optString;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("data");
            if (optJSONObject != null) {
                this.closeLink = optJSONObject.optString("close_link");
                this.isClosed = !TextUtils.isEmpty(this.closeLink);
            }
        }
    }

    public boolean isError() {
        return this.mIsError;
    }

    public String getErrorDesc() {
        return this.mErrorDesc;
    }
}
