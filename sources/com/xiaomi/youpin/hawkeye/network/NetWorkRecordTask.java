package com.xiaomi.youpin.hawkeye.network;

import android.text.TextUtils;
import com.xiaomi.youpin.hawkeye.task.ApmTaskConstants;
import com.xiaomi.youpin.hawkeye.task.BaseTask;
import com.xiaomi.youpin.hawkeye.upload.UploadConstants;

public class NetWorkRecordTask extends BaseTask {
    public String a() {
        return ApmTaskConstants.d;
    }

    public boolean a(String str) {
        return TextUtils.isEmpty(str) || !str.startsWith(UploadConstants.f23381a);
    }
}
