package cn.tongdun.android.shell;

import android.content.Context;
import android.webkit.JavascriptInterface;
import cn.com.fmsh.tsm.business.constants.Constants;
import cn.tongdun.android.shell.utils.LogUtil;
import java.util.HashMap;

public class FMJsExport {
    private static final int version = 312;
    Context mContext;

    public FMJsExport(Context context) {
        this.mContext = context;
    }

    @JavascriptInterface
    public void init(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(linkxxxxx("5c1265157e174c3d4b23", 111), str);
        FMAgent.initWithOptions(this.mContext, FMAgent.ENV_PRODUCTION, hashMap);
        LogUtil.info(linkxxxxx("75425d556f657e7e677a3333342b2d2f236a6a6d6d7039233f353f332933", 2));
    }

    @JavascriptInterface
    public String onEvent() {
        String onEvent = FMAgent.onEvent(this.mContext);
        LogUtil.info(linkxxxxx("751b5d0c6f3c7e276723336a34722d7623336c324701540a4e5e1d580b580d4e0d", 91));
        return onEvent;
    }

    public static String linkxxxxx(String str, int i) {
        try {
            int length = str.length() / 2;
            char[] charArray = str.toCharArray();
            byte[] bArr = new byte[length];
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = i2 * 2;
                bArr[i2] = (byte) ("0123456789abcdef".indexOf(charArray[i3 + 1]) | ("0123456789abcdef".indexOf(charArray[i3]) << 4));
            }
            byte b = (byte) (i ^ 107);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ Constants.TagName.CARD_APP_ACTIVATION_STATUS);
            byte b2 = bArr[0];
            int i4 = 1;
            while (i4 < length2) {
                byte b3 = bArr[i4];
                bArr[i4] = (byte) ((b2 ^ bArr[i4]) ^ b);
                i4++;
                b2 = b3;
            }
            return new String(bArr, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
