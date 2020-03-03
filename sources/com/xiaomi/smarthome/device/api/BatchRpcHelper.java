package com.xiaomi.smarthome.device.api;

import android.text.TextUtils;
import com.xiaomi.smarthome.core.server.internal.device.BatchRpcParam;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class BatchRpcHelper {
    public static final int BATCH_LIMIT = 20;
    private List<BatchRpcParam> batchData = new ArrayList();

    public BatchRpcHelper addBatchRequest(String str, String str2, JSONArray jSONArray) {
        if (TextUtils.isEmpty(str)) {
            return this;
        }
        BatchRpcParam batchRpcParam = new BatchRpcParam();
        batchRpcParam.f14497a = str;
        batchRpcParam.b = str2;
        batchRpcParam.c = jSONArray;
        this.batchData.add(batchRpcParam);
        return this;
    }

    public void commit(AsyncCallback<JSONObject, Error> asyncCallback) {
        if (this.batchData.size() <= 20) {
            CoreApi.a().a(this.batchData, asyncCallback);
        } else if (asyncCallback != null) {
            asyncCallback.onFailure(new Error(0, "too many rpc requests, cannot be more than 20"));
        }
    }
}
