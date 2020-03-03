package com.tsmclient.smartcard;

import java.util.HashMap;
import java.util.Map;

public class CardConstants {
    public static final String ATC = "atc";
    public static final String BANK_CARD_ID = "CARD_TYPE_BANKCARD";
    public static final String BANK_CARD_TYPE = "bank_card_type";
    public static final String BMAC = "BMAC";
    public static final String CARD_SCHEME = "card_scheme";
    public static final String CITYU = "CITYU";
    public static final String CQTK = "CQTK";
    public static final String CST = "CHANGSHA";
    public static final int CURRENCY_CODE_AMERICAN = 9;
    public static final int CURRENCY_CODE_CHINA = 1;
    public static final int CURRENCY_CODE_HONGKONG = 2;
    public static final int CURRENCY_CODE_INDIA = 3;
    public static final int CURRENCY_CODE_JAPAN = 4;
    public static final int CURRENCY_CODE_KOREA = 5;
    public static final int CURRENCY_CODE_MACAU = 6;
    public static final int CURRENCY_CODE_MALAYSIA = 8;
    public static final int CURRENCY_CODE_SINGAPORE = 7;
    public static final int CURRENCY_CODE_TAIWAN = 10;
    public static final String E_BALANCE = "e_balance";
    public static final String E_BALANCE_LIMIT = "e_balance_limit";
    public static final String E_PER_LIMIT = "per_limit";
    public static final String GUIYANG = "GUIYANG";
    public static final String HAERBIN = "HAERBIN";
    public static final String HZT = "HZT";
    public static final String KEY_ACCOUNT_NUM = "account_num";
    public static final String KEY_ACCOUNT_REAL_NUM = "account_real_num";
    public static final String KEY_ID = "card_id";
    public static final String KEY_TAG = "nfc_tag";
    public static final String KEY_TYPE = "card_type";
    public static final String KUNMING = "KUNMING";
    public static final String LANZHOU = "LANZHOU";
    public static final String LINGNAN = "LNT";
    public static final String NANCHANG = "NANCHANG";
    public static final String NFC_PREFS_NAME = "nfc_read_card";
    public static final String NFC_TAG = "tag";
    public static final String NINGBO = "NINGBO";
    public static final String OCTOPUS = "OCTOPUS";
    public static final String OVER_DRAWN = "overdrawn";
    public static final String PREFS_KEY_LAST_TRAN = "last_trans_card";
    public static final String QINGDAO = "QINGDAO";
    public static final int RESULT_INVALID = -999;
    public static final int SCHEME_UNIONPAY = 1;
    public static final int SCHEME_VISA = 2;
    public static final String SPTC = "SPTC";
    public static final String SPTC_NEW = "SPTC_NEW";
    public static final String STATUS_CARD_EXCEPTION = "card_exception";
    public static final String STATUS_IN_BLACK_LIST = "in_black_list";
    public static final String STATUS_LOCKED = "card_locked";
    public static final String STATUS_NEGATIVE = "status_negative";
    public static final String STATUS_VALID_END_DATE = "is_valid_end_date";
    public static final String STATUS_VALID_START_DATE = "is_valid_start_date";
    public static final String ST_ONE_DAY_PASS = "ST_ONE_DAY_PASS";
    public static final int ST_ONE_DAY_PASS_DEFAULT_BALANCE = 1800;
    public static final String ST_THREE_DAY_PASS = "ST_THREE_DAY_PASS";
    public static final int ST_THREE_DAY_PASS_DEFAULT_BALANCE = 4500;
    public static final String SUZHOUTONG = "SUZHOUTONG";
    public static final String SZT = "SZT";
    public static final int TRADE_CONSUME = 1;
    public static final int TRADE_DEPOSIT = 3;
    public static final int TRADE_DEPOSIT_ECASH = 5;
    public static final String TRADE_LOG = "trade_log";
    public static final int TRADE_QUERY_BALANCE = 4;
    public static final int TRADE_RECHARGE = 2;
    public static final int TRADE_WITHDRAW = 6;
    public static final int TYPE_BANK_CARD = 1;
    public static final int TYPE_CREDIT = 2;
    public static final int TYPE_DEBIT = 1;
    public static final int TYPE_ECASH = 4;
    public static final int TYPE_QUASI_CREDIT = 3;
    public static final int TYPE_TRANS_CARD = 2;
    public static final String VALID_END = "valid_end";
    public static final String VALID_START = "valid_start";
    public static final String WHT = "WHT";
    public static final String XIAN = "XIAN";
    public static final String ZHENGZHOU = "ZHENGZHOU";
    public static final String ZHOUSHAN = "ZHOUSHAN";
    public static Map<String, Integer> sCurrencyCodeMap = new HashMap();

    static {
        sCurrencyCodeMap.put("0156", 1);
        sCurrencyCodeMap.put("0344", 2);
        sCurrencyCodeMap.put("0356", 3);
        sCurrencyCodeMap.put("0392", 4);
        sCurrencyCodeMap.put("0410", 5);
        sCurrencyCodeMap.put("0446", 6);
        sCurrencyCodeMap.put("0702", 7);
        sCurrencyCodeMap.put("0458", 8);
        sCurrencyCodeMap.put("0840", 9);
        sCurrencyCodeMap.put("0901", 10);
    }

    private CardConstants() {
    }
}
