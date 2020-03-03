package com.taobao.weex.http;

import android.content.Context;
import android.text.TextUtils;
import com.taobao.weex.common.WXConfig;
import com.taobao.weex.el.parse.Operators;
import com.taobao.weex.utils.WXViewUtils;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXHttpUtil {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String KEY_USER_AGENT = "user-agent";
    private static String sDefaultUA = null;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(8818466948618395441L, "com/taobao/weex/http/WXHttpUtil", 33);
        $jacocoData = a2;
        return a2;
    }

    public WXHttpUtil() {
        $jacocoInit()[0] = true;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[32] = true;
    }

    public static String assembleUserAgent(Context context, Map<String, String> map) {
        String str;
        String str2;
        String str3;
        String str4;
        boolean[] $jacocoInit = $jacocoInit();
        if (!TextUtils.isEmpty(sDefaultUA)) {
            $jacocoInit[1] = true;
        } else {
            $jacocoInit[2] = true;
            StringBuilder sb = new StringBuilder();
            $jacocoInit[3] = true;
            sb.append(map.get(WXConfig.sysModel));
            $jacocoInit[4] = true;
            sb.append("(Android/");
            $jacocoInit[5] = true;
            sb.append(map.get(WXConfig.sysVersion));
            $jacocoInit[6] = true;
            sb.append(Operators.BRACKET_END_STR);
            $jacocoInit[7] = true;
            sb.append(" ");
            $jacocoInit[8] = true;
            if (TextUtils.isEmpty(map.get(WXConfig.appGroup))) {
                str = "";
                $jacocoInit[9] = true;
            } else {
                str = map.get(WXConfig.appGroup);
                $jacocoInit[10] = true;
            }
            sb.append(str);
            $jacocoInit[11] = true;
            sb.append(Operators.BRACKET_START_STR);
            $jacocoInit[12] = true;
            if (TextUtils.isEmpty(map.get("appName"))) {
                str2 = "";
                $jacocoInit[13] = true;
            } else {
                str2 = map.get("appName");
                $jacocoInit[14] = true;
            }
            sb.append(str2);
            $jacocoInit[15] = true;
            sb.append("/");
            $jacocoInit[16] = true;
            sb.append(map.get("appVersion"));
            $jacocoInit[17] = true;
            sb.append(Operators.BRACKET_END_STR);
            $jacocoInit[18] = true;
            sb.append(" ");
            $jacocoInit[19] = true;
            sb.append("Weex/");
            $jacocoInit[20] = true;
            sb.append(map.get(WXConfig.weexVersion));
            $jacocoInit[21] = true;
            sb.append(" ");
            $jacocoInit[22] = true;
            if (TextUtils.isEmpty(map.get(WXConfig.externalUserAgent))) {
                str3 = "";
                $jacocoInit[23] = true;
            } else {
                str3 = map.get(WXConfig.externalUserAgent);
                $jacocoInit[24] = true;
            }
            sb.append(str3);
            $jacocoInit[25] = true;
            if (TextUtils.isEmpty(map.get(WXConfig.externalUserAgent))) {
                str4 = "";
                $jacocoInit[26] = true;
            } else {
                str4 = " ";
                $jacocoInit[27] = true;
            }
            sb.append(str4);
            StringBuilder sb2 = new StringBuilder();
            $jacocoInit[28] = true;
            sb2.append(WXViewUtils.getScreenWidth(context));
            sb2.append("x");
            sb2.append(WXViewUtils.getScreenHeight(context));
            sb.append(sb2.toString());
            $jacocoInit[29] = true;
            sDefaultUA = sb.toString();
            $jacocoInit[30] = true;
        }
        String str5 = sDefaultUA;
        $jacocoInit[31] = true;
        return str5;
    }
}
