package cn.tongdun.android.shell.settings;

import cn.com.fmsh.tsm.business.constants.Constants;

public class Constants {
    public static final byte[] CLASSES_DEX = {Constants.TagName.ELECTRONIC_USE_TYPE, Constants.TagName.PAY_ORDER_LIST, 95, 102, Constants.TagName.PUBLISH_END_TIME, Constants.TagName.SIM_SEID, Constants.TagName.PAY_ORDER_ID, 97, Constants.TagName.ELECTRONIC_NUMBER};
    public static final byte[] CLASS_NAME = {Constants.TagName.PAY_ORDER, Constants.TagName.ELECTRONIC_STARTTIME, Constants.TagName.SIM_SEID, Constants.TagName.ELECTRONIC_USE_TYPE, Constants.TagName.ELECTRONIC_END_TIME, Constants.TagName.ELECTRONIC_STARTTIME, 103, Constants.TagName.PAY_ORDER_LIST, Constants.TagName.ELECTRONIC_TRANSFER_FLAG, Constants.TagName.ELECTRONIC_STARTTIME, Constants.TagName.SIM_SEID, 97, Constants.TagName.ELECTRONIC_STARTTIME, Constants.TagName.PAY_ORDER_LIST, Constants.TagName.ELECTRONIC_NUMBER, Constants.TagName.ELECTRONIC_END_TIME, Constants.TagName.MAIN_ORDER_ID, Constants.TagName.PAY_ORDER_LIST, Constants.TagName.SIM_SEID, Constants.TagName.PAY_ORDER, Constants.TagName.ELECTRONIC_END_TIME, Constants.TagName.ELECTRONIC_NUMBER, Constants.TagName.ORDER_TYPE, Constants.TagName.SIM_SEID, Constants.TagName.TERMINAL_BASEBAND_VERSION, 77, Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.ELECTRONIC_END_TIME, Constants.TagName.ELECTRONIC_NUMBER, Constants.TagName.ORDER_TYPE};
    public static final boolean DEFAULT_ALWAYS_DEMOTION = false;
    public static final String DEFAULT_APPNAME = null;
    public static final int DEFAULT_BLACKBOX_MAZSIZE = Integer.MAX_VALUE;
    public static final int DEFAULT_BLACKBOX_MINSIZE = 5120;
    public static final String DEFAULT_CUSTOM_URL = null;
    public static final String DEFAULT_CUST_PROCESS = null;
    public static final String DEFAULT_DOMAIN = null;
    public static final String DEFAULT_DOUBLE_URL = null;
    public static final String DEFAULT_ENV_TYPE = null;
    public static final String DEFAULT_GOOGLE_AID = null;
    public static final int DEFAULT_INIT_TIMESPAN = 600000;
    public static final boolean DEFAULT_KILL_DEBUGGER = false;
    public static final boolean DEFAULT_OVERRIDECERTI = false;
    public static final String DEFAULT_PARTNER_CODE = null;
    public static final String DEFAULT_PROXY_URL = null;
    public static final boolean DEFAULT_SKIP_GPS = false;
    public static final int DEFAULT_WAIT_TIME = 3000;
    public static final String OS = linkxxxxx("071e4b08560e5b", 115);
    public static final String VERSION = linkxxxxx("757e2f6235", 33);

    public static String linkxxxxx(String str, int i) {
        try {
            int length = str.length() / 2;
            char[] charArray = str.toCharArray();
            byte[] bArr = new byte[length];
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = i2 * 2;
                bArr[i2] = (byte) ("0123456789abcdef".indexOf(charArray[i3 + 1]) | ("0123456789abcdef".indexOf(charArray[i3]) << 4));
            }
            byte b = (byte) (i ^ 66);
            int length2 = bArr.length;
            bArr[0] = (byte) (bArr[0] ^ Constants.TagName.TERMINAL_BASEBAND_VERSION);
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
