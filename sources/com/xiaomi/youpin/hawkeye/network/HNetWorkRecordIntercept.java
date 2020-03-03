package com.xiaomi.youpin.hawkeye.network;

import android.text.TextUtils;
import com.xiaomi.youpin.hawkeye.HawkEye;
import com.xiaomi.youpin.hawkeye.task.ApmTaskConstants;
import com.xiaomi.youpin.hawkeye.task.TaskManager;
import com.xiaomi.youpin.hawkeye.utils.HLog;
import java.io.IOException;
import java.nio.charset.Charset;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

public class HNetWorkRecordIntercept implements Interceptor {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1579a = "HNetWorkRecordIntercept";

    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        NetWorkRecordTask netWorkRecordTask = (NetWorkRecordTask) TaskManager.a().a(ApmTaskConstants.d);
        if (netWorkRecordTask != null && netWorkRecordTask.e()) {
            String httpUrl = request.url().toString();
            if (!TextUtils.isEmpty(httpUrl) && HawkEye.c().h().a(request)) {
                NetWorkRecordInfo a2 = a(request, httpUrl);
                a2.mTaskName = netWorkRecordTask.a();
                Response response = null;
                try {
                    response = chain.proceed(request);
                } catch (IOException e) {
                    netWorkRecordTask.b(a(e.getMessage(), a2));
                    HLog.e(f1579a, " response is error !" + e.getMessage());
                }
                if (response != null) {
                    netWorkRecordTask.b(a(response, a2));
                    return response;
                }
            }
        }
        return chain.proceed(request);
    }

    private NetWorkRecordInfo a(Request request, String str) {
        NetWorkRecordInfo netWorkRecordInfo = new NetWorkRecordInfo();
        netWorkRecordInfo.url = str;
        netWorkRecordInfo.startTime = System.currentTimeMillis();
        netWorkRecordInfo.method = request.method();
        netWorkRecordInfo.requestLength = a(request);
        HLog.b(f1579a, "request length is :  " + netWorkRecordInfo.requestLength);
        return netWorkRecordInfo;
    }

    private long a(Request request) {
        RequestBody body = request.body();
        if (body == null) {
            return (long) request.url().toString().getBytes().length;
        }
        String header = request.header("Content-Encoding");
        RequestBodyHelper requestBodyHelper = new RequestBodyHelper();
        try {
            BufferedSink buffer = Okio.buffer(Okio.sink(requestBodyHelper.a(header)));
            body.writeTo(buffer);
            if (buffer != null) {
                buffer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (requestBodyHelper.a() == null) {
            return 0;
        }
        return (long) requestBodyHelper.a().length;
    }

    private NetWorkRecordInfo a(String str, NetWorkRecordInfo netWorkRecordInfo) {
        netWorkRecordInfo.endTime = System.currentTimeMillis();
        netWorkRecordInfo.errorMessage = str;
        netWorkRecordInfo.totalLength = netWorkRecordInfo.requestLength;
        return netWorkRecordInfo;
    }

    private NetWorkRecordInfo a(Response response, NetWorkRecordInfo netWorkRecordInfo) {
        if (response != null) {
            netWorkRecordInfo.endTime = System.currentTimeMillis();
            netWorkRecordInfo.totalCost = netWorkRecordInfo.endTime - netWorkRecordInfo.startTime;
            netWorkRecordInfo.responseCode = response.code();
            netWorkRecordInfo.responseLength = a(response);
            netWorkRecordInfo.totalLength = netWorkRecordInfo.requestLength + netWorkRecordInfo.responseLength;
        }
        HLog.b(f1579a, "response length is :  " + netWorkRecordInfo.responseLength);
        return netWorkRecordInfo;
    }

    private long a(Response response) {
        ResponseBody body = response.body();
        if (body != null) {
            try {
                BufferedSource source = body.source();
                source.request(Long.MAX_VALUE);
                Buffer buffer = source.buffer();
                return (long) buffer.clone().readString(Charset.forName("UTF-8")).length();
            } catch (Exception unused) {
            }
        }
        return 0;
    }
}
