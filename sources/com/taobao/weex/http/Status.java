package com.taobao.weex.http;

import com.mi.global.shop.model.Tags;
import com.xiaomi.smarthome.constants.AppConstants;
import com.ximalaya.ting.android.opensdk.auth.constants.XmlyAuthErrorNoConstants;
import java.util.HashMap;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class Status {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String ERR_CONNECT_FAILED = "ERR_CONNECT_FAILED";
    public static final String ERR_INVALID_REQUEST = "ERR_INVALID_REQUEST";
    public static final String UNKNOWN_STATUS = "unknown status";
    private static Map<String, String> statusMap = new HashMap();

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-4311725747541454986L, "com/taobao/weex/http/Status", 55);
        $jacocoData = a2;
        return a2;
    }

    public Status() {
        $jacocoInit()[0] = true;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[3] = true;
        statusMap.put("100", "Continue");
        $jacocoInit[4] = true;
        statusMap.put("101", "Switching Protocol");
        $jacocoInit[5] = true;
        statusMap.put(XmlyAuthErrorNoConstants.g, "OK");
        $jacocoInit[6] = true;
        statusMap.put(XmlyAuthErrorNoConstants.h, "Created");
        $jacocoInit[7] = true;
        statusMap.put(XmlyAuthErrorNoConstants.i, "Accepted");
        $jacocoInit[8] = true;
        statusMap.put(XmlyAuthErrorNoConstants.j, "Non-Authoritative Information");
        $jacocoInit[9] = true;
        statusMap.put(XmlyAuthErrorNoConstants.k, "No Content");
        $jacocoInit[10] = true;
        statusMap.put(XmlyAuthErrorNoConstants.l, "Reset Content");
        $jacocoInit[11] = true;
        statusMap.put(XmlyAuthErrorNoConstants.m, "Partial Content");
        $jacocoInit[12] = true;
        statusMap.put("300", "Multiple Choice");
        $jacocoInit[13] = true;
        statusMap.put("301", "Moved Permanently");
        $jacocoInit[14] = true;
        statusMap.put("302", "Found");
        $jacocoInit[15] = true;
        statusMap.put("303", "See Other");
        $jacocoInit[16] = true;
        statusMap.put("304", "Not Modified");
        $jacocoInit[17] = true;
        statusMap.put("305", "Use Proxy");
        $jacocoInit[18] = true;
        statusMap.put("306", Tags.Coupon.STAT_UNUSED);
        $jacocoInit[19] = true;
        statusMap.put("307", "Temporary Redirect");
        $jacocoInit[20] = true;
        statusMap.put("308", "Permanent Redirect");
        $jacocoInit[21] = true;
        statusMap.put("400", "Bad Request");
        $jacocoInit[22] = true;
        statusMap.put("401", "Unauthorized");
        $jacocoInit[23] = true;
        statusMap.put("402", "Payment Required");
        $jacocoInit[24] = true;
        statusMap.put(AppConstants.v, "Forbidden");
        $jacocoInit[25] = true;
        statusMap.put("404", "Not Found");
        $jacocoInit[26] = true;
        statusMap.put("405", "Method Not Allowed");
        $jacocoInit[27] = true;
        statusMap.put("406", "Not Acceptable");
        $jacocoInit[28] = true;
        statusMap.put("407", "Proxy Authentication Required");
        $jacocoInit[29] = true;
        statusMap.put("408", "Request Timeout");
        $jacocoInit[30] = true;
        statusMap.put("409", "Conflict");
        $jacocoInit[31] = true;
        statusMap.put("410", "Gone");
        $jacocoInit[32] = true;
        statusMap.put("411", "Length Required");
        $jacocoInit[33] = true;
        statusMap.put("412", "Precondition Failed");
        $jacocoInit[34] = true;
        statusMap.put("413", "Payload Too Large");
        $jacocoInit[35] = true;
        statusMap.put("414", "URI Too Long");
        $jacocoInit[36] = true;
        statusMap.put("415", "Unsupported Media Type");
        $jacocoInit[37] = true;
        statusMap.put("416", "Requested Range Not Satisfiable");
        $jacocoInit[38] = true;
        statusMap.put("417", "Expectation Failed");
        $jacocoInit[39] = true;
        statusMap.put("418", "I'm a teapot");
        $jacocoInit[40] = true;
        statusMap.put("421", "Misdirected Request");
        $jacocoInit[41] = true;
        statusMap.put("426", "Upgrade Required");
        $jacocoInit[42] = true;
        statusMap.put("428", "Precondition Required");
        $jacocoInit[43] = true;
        statusMap.put("429", "Too Many Requests");
        $jacocoInit[44] = true;
        statusMap.put("431", "Request Header Fields Too Large");
        $jacocoInit[45] = true;
        statusMap.put(XmlyAuthErrorNoConstants.r, "Internal Server Error");
        $jacocoInit[46] = true;
        statusMap.put(XmlyAuthErrorNoConstants.s, "Not Implemented");
        $jacocoInit[47] = true;
        statusMap.put(XmlyAuthErrorNoConstants.t, "Bad Gateway");
        $jacocoInit[48] = true;
        statusMap.put("503", "Service Unavailable");
        $jacocoInit[49] = true;
        statusMap.put("504", "Gateway Timeout");
        $jacocoInit[50] = true;
        statusMap.put("505", "HTTP Version Not Supported");
        $jacocoInit[51] = true;
        statusMap.put("506", "Variant Also Negotiates");
        $jacocoInit[52] = true;
        statusMap.put("507", "Variant Also Negotiates");
        $jacocoInit[53] = true;
        statusMap.put("511", "Network Authentication Required");
        $jacocoInit[54] = true;
    }

    public static String getStatusText(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!statusMap.containsKey(str)) {
            $jacocoInit[1] = true;
            return UNKNOWN_STATUS;
        }
        String str2 = statusMap.get(str);
        $jacocoInit[2] = true;
        return str2;
    }
}
