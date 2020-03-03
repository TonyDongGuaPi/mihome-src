package com.xiaomi.youpin.hawkeye.network;

import android.text.TextUtils;
import com.xiaomi.youpin.hawkeye.upload.UploadConstants;
import okhttp3.Request;

public class DefaultNetWorkFilterImpl implements INetWorkRecordFilter {
    public boolean a(Request request) {
        String httpUrl = request.url().toString();
        return TextUtils.isEmpty(httpUrl) || !httpUrl.startsWith(UploadConstants.f23381a);
    }
}
