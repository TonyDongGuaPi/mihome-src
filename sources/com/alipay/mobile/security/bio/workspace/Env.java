package com.alipay.mobile.security.bio.workspace;

import android.content.Context;
import com.alipay.mobile.security.bio.utils.DeviceUtil;
import com.taobao.weex.el.parse.Operators;
import java.util.HashMap;

public class Env {
    public static final String BID_LOG_KEY_PUBLIC_KEY = "bid-log-key-public.key";
    public static final String BID_LOG_KEY_PUBLIC_T_KEY = "bid-log-key-public_t.key";
    public static final Env DEV = new Env("dev", "http://mobilegw.aaa.alipay.net/mgw.htm", "http://openapi.stable.alipay.net/gateway.do", "http://mdap-1-64.test.alipay.net", 4, "bid-log-key-public_t.key");
    public static final String KEY_PROTOCOL_FORMAT = "meta_serializer";
    public static final String NAME_DEV = "dev";
    public static final String NAME_ONLINE = "online";
    public static final String NAME_ONLINE_ANT_CLOUD = "ant_cloud_online";
    public static final String NAME_PRE = "pre";
    public static final String NAME_PRE_ANT_CLOUD = "ant_cloud_pre";
    public static final String NAME_TEST = "test";
    public static final Env ONLINE = new Env("online", "https://mobilegw.alipay.com/mgw.htm", "https://openapi.alipay.com/gateway.do", "http://mdap.alipaylog.com", 0, "bid-log-key-public.key");
    public static final Env ONLINE_ANT_CLOUD = new Env(NAME_ONLINE_ANT_CLOUD, "https://cn-hangzhou-mgs-gw.cloud.alipay.com/mgw.htm", "http://139.224.94.200/gateway/identification/simulate/face/initialize", "http://cn-hangzhou-mas-log.cloud.alipay.com/loggw/logUpload.do", 0, "bid-log-key-public.key");
    public static final Env PRE = new Env(NAME_PRE, "https://mobilegwpre.alipay.com/mgw.htm", "https://openapi.prefromoffice.alipay.net/gateway.do", "http://mdap.alipaylog.com", 2, "bid-log-key-public.key");
    public static final Env PRE_ANT_CLOUD = new Env(NAME_PRE_ANT_CLOUD, "https://cn-hangzhou-mgs-gw.cloud.alipay.com/mgw.htm", "http://139.224.138.243/gateway/identification/simulate/face/initialize", "http://cn-hangzhou-mas-log.cloud.alipay.com/loggw/logUpload.do", 2, "bid-log-key-public.key");
    public static final int PROTOCOL_FORMAT_JSON = 1;
    public static final int PROTOCOL_FORMAT_PB = 2;
    public static final Env TEST = new Env("test", "http://mobilegw-1-64.test.alipay.net/mgw.htm", "http://openapi-1-64.test.alipay.net/gateway.do", "http://mdap-1-64.test.alipay.net", 3, "bid-log-key-public_t.key");

    /* renamed from: a  reason: collision with root package name */
    private static final HashMap<String, Env> f1029a = new HashMap<>();
    public int appSecurityEnvConfig;
    public String gwUrl;
    public String loggingGatewayUrl;
    public String name;
    public String publicKeyAssetsName;
    public String zimInitializeUrl;

    static {
        f1029a.put(DEV.name, DEV);
        f1029a.put(TEST.name, TEST);
        f1029a.put(PRE.name, PRE);
        f1029a.put(ONLINE.name, ONLINE);
        f1029a.put(PRE_ANT_CLOUD.name, PRE_ANT_CLOUD);
        f1029a.put(ONLINE_ANT_CLOUD.name, ONLINE_ANT_CLOUD);
    }

    public Env(String str, String str2, String str3, String str4, int i, String str5) {
        this.name = str;
        this.gwUrl = str2;
        this.zimInitializeUrl = str3;
        this.loggingGatewayUrl = str4;
        this.appSecurityEnvConfig = i;
        this.publicKeyAssetsName = str5;
    }

    public static Env getEnvByName(String str) {
        return f1029a.get(str);
    }

    public static void setProtocolFormat(Context context, int i) {
        if (DeviceUtil.isDebug(context)) {
            context.getSharedPreferences("biometric", 0).edit().putInt("meta_serializer", i).apply();
        }
    }

    public static int getProtocolFormat(Context context) {
        if (DeviceUtil.isDebug(context)) {
            return context.getSharedPreferences("biometric", 0).getInt("meta_serializer", 1);
        }
        return 1;
    }

    public String toString() {
        return "Env{name='" + this.name + Operators.SINGLE_QUOTE + ", publicKeyAssetsName='" + this.publicKeyAssetsName + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
