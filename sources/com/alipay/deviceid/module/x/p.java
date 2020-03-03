package com.alipay.deviceid.module.x;

import android.content.Context;
import com.alipay.deviceid.module.rpc.report.open.model.ReportRequest;
import com.alipay.deviceid.module.rpc.report.open.model.ReportResult;
import com.alipay.deviceid.module.senative.DeviceIdUtil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public final class p {
    public static ReportRequest a(Context context, r rVar) {
        ReportRequest reportRequest = new ReportRequest();
        if (rVar == null) {
            return null;
        }
        Map hashMap = rVar.d == null ? new HashMap() : rVar.d;
        if (hashMap == null) {
            return null;
        }
        reportRequest.bizType = "1";
        reportRequest.rpcVersion = e.c(rVar.c);
        reportRequest.os = e.c(rVar.f935a);
        reportRequest.appName = (String) hashMap.get("AA1");
        reportRequest.appVersion = (String) hashMap.get("AA2");
        reportRequest.sdkName = (String) hashMap.get("AA3");
        reportRequest.sdkVersion = (String) hashMap.get("AA4");
        HashMap hashMap2 = new HashMap();
        hashMap2.put("apdidToken", e.c(rVar.b));
        reportRequest.bizData = hashMap2;
        try {
            JSONObject jSONObject = new JSONObject();
            for (String str : hashMap.keySet()) {
                if (str != null && str.length() > 0) {
                    jSONObject.put(str, hashMap.get(str));
                }
            }
            String jSONObject2 = jSONObject.toString();
            reportRequest.deviceData = new String(DeviceIdUtil.getInstance(context).packageDevideData(("{\"AA9\":\"%s\",\"AE20\":\"%s\"," + jSONObject2.substring(1)).getBytes("UTF-8")));
        } catch (Exception unused) {
            reportRequest.deviceData = "";
        }
        return reportRequest;
    }

    public static q a(ReportResult reportResult) {
        q qVar = new q();
        if (reportResult == null) {
            return null;
        }
        qVar.f934a = reportResult.success;
        qVar.b = reportResult.resultCode;
        if (reportResult.resultData == null) {
            return null;
        }
        Map<String, String> map = reportResult.resultData;
        qVar.c = e.a(map, "apdidToken", "");
        qVar.d = e.a(map, "appListCmdVer", "");
        qVar.g = e.a(map, "webrtcUrl", "");
        String a2 = e.a(map, "drmSwitch", "");
        qVar.e = "0";
        qVar.f = "0";
        if (e.b(a2) && a2.length() > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(a2.charAt(0));
            qVar.e = sb.toString();
        }
        return qVar;
    }
}
