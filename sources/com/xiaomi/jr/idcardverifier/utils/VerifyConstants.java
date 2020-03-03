package com.xiaomi.jr.idcardverifier.utils;

public class VerifyConstants {

    /* renamed from: a  reason: collision with root package name */
    public static String f10875a = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDVyfDqwoNh9Vg2iyDIlItO17WuZIGyRdvlqt7dtJENVYwUetK8rkGEbvGfO5AHA8/bPPoRVCVm4d0Rra9rd5zjIWp804uBmJM0lLy38Aw1k04aKdDO79B7Po2l1K2LhYwMs1xGe5lG2kUK1JHnB2hTrzuNxfVZ0IBwZypwzHNkPwIDAQAB";
    public static String b = "https://certify.mipay.com/";
    public static final String c = "identity-api";
    public static String d = (b + "idcard/getPermissionSDK");
    public static String e = (b + "idcard/uploadSDK");
    public static String f = (b + "idcard/commitSDK");
    public static final String g = "AES/ECB/PKCS5Padding";
    public static final String h = "partnerId";
    public static final String i = "feePartnerId";
    public static final String j = "logId";
    public static final String k = "needBindPartnerId";
    public static final String l = "processId";
    public static final String m = "minPhotoLength";
    public static final String n = "verifyResult";
    public static final String o = "skipDefaultSuccessPage";
    public static final String p = "skipDefaultFailurePage";
    public static final String q = "sign";
    public static final String r = "signTimeStamp";

    public interface ErrorCode {

        /* renamed from: a  reason: collision with root package name */
        public static final int f10876a = -1;
        public static final int b = 200;
        public static final int c = 100001;
        public static final int d = 100002;
        public static final int e = 100003;
        public static final int f = 100006;
        public static final int g = 100007;
        public static final int h = 100008;
        public static final int i = 100009;
        public static final int j = 100010;
        public static final int k = 100011;
    }

    public static void a(boolean z) {
        if (z) {
            f10875a = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDWwRHPVDCD81vulmut/+ME1+ZUMf9sf2S1jYmtTsOPP+cmYXFL7DCwWh59WOhVcHBsgcm6IYIJB8yCWP9T6mbeitKRLq8UiCctp+aCLNW6Q1/DdD0mzw0JDtZsAWZ8cfze7K/gV7OmnB9fA4iA4Wix6K4I68pwvafm3AyS12TSwIDAQAB";
            b = "http://staging.certify.mipay.com/";
            d = b + "idcard/getPermissionSDK";
            e = b + "idcard/uploadSDK";
            f = b + "idcard/commitSDK";
            return;
        }
        f10875a = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDVyfDqwoNh9Vg2iyDIlItO17WuZIGyRdvlqt7dtJENVYwUetK8rkGEbvGfO5AHA8/bPPoRVCVm4d0Rra9rd5zjIWp804uBmJM0lLy38Aw1k04aKdDO79B7Po2l1K2LhYwMs1xGe5lG2kUK1JHnB2hTrzuNxfVZ0IBwZypwzHNkPwIDAQAB";
        b = "https://certify.mipay.com/";
        d = b + "idcard/getPermissionSDK";
        e = b + "idcard/uploadSDK";
        f = b + "idcard/commitSDK";
    }
}
