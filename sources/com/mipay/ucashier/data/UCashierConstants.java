package com.mipay.ucashier.data;

import com.mipay.common.data.d;
import com.mipay.common.data.e;

public class UCashierConstants extends d {
    public static final String FLAG_TRADE = "trade";
    public static final String KEY_DEVICE_ID = "deviceId";
    public static final String KEY_LAST_PAY_TYPE = "lastPayType";
    public static final String KEY_ORDER = "order";
    public static final String KEY_PAY_ICON_URL = "payIconUrl";
    public static final String KEY_PAY_INFO = "payInfo";
    public static final String KEY_PAY_NAME = "payName";
    public static final String KEY_PAY_TIP = "payTip";
    public static final String KEY_PAY_TITLE = "payTitle";
    public static final String KEY_PAY_TYPE = "payType";
    public static final String KEY_PAY_TYPES = "payTypes";
    public static final String KEY_PRODUCT_NAME = "productName";
    public static final String KEY_RESULT = "result";
    public static final String KEY_TOTAL_FEE = "totalFee";
    public static final String KEY_TRADE_ID = "tradeId";
    public static final String KEY_USER_ID = "userId";
    public static String URL_BASE_API = (d.STAGING ? URL_UCASHIER_BASE_STAGING : URL_UCASHIER_BASE_ONLINE);
    public static String URL_CREATE_TRADE = "api/trade/create";
    public static String URL_PAY_TRADE = "api/trade/doPay";
    public static final String URL_UCASHIER_BASE_ONLINE = "https://api.ucashier.mipay.com/";
    public static final String URL_UCASHIER_BASE_STAGING = "http://staging.api.ucashier.mipay.com";

    public static String getUrl(String str) {
        return e.a(URL_BASE_API, str);
    }
}
