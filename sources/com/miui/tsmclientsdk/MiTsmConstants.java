package com.miui.tsmclientsdk;

public class MiTsmConstants {
    public static final String EXTRA_CARD_AID = "extra_card_aid";
    public static final String KEY_ACTIVE_CARD = "key_active_card";
    public static final String KEY_BANK_CARD_NO = "bank_card_no";
    public static final String KEY_CARD = "key_card";
    public static final String KEY_CARD_INFO = "key_card_info";
    public static final String KEY_CARD_LIST = "key_card_list";
    public static final String KEY_CARD_QUANTITY = "key_card_quantity";
    public static final String KEY_CODE = "key_code";
    public static final String KEY_CPLC_DATA = "key_cplc_data";
    public static final String KEY_DATA = "key_data";
    public static final String KEY_DEFAULT_CARD_AID = "key_default_card_aid";
    public static final String KEY_DEFAULT_CARD_BALANCE = "key_default_card_balance";
    public static final String KEY_DEFAULT_CARD_TYPE = "key_default_card_type";
    public static final String KEY_EXTRA_INFO = "key_extra_info";
    public static final String KEY_FAST_CARD_BIND_ID = "bind_id";
    public static final String KEY_FAST_TAIL_NUM = "tail_num";
    public static final String KEY_INAPP_CHANNEL = "inapp_channel";
    public static final String KEY_INAPP_CURRENCY_CODE = "inapp_currency_code";
    public static final String KEY_INAPP_DISCOUNT_AMOUNT = "inapp_discount_amount";
    public static final String KEY_INAPP_ENCRYPT_PAY_AMOUNT = "inapp_encrypt_pay_amount";
    public static final String KEY_INAPP_ICC_DATA = "inapp_icc_data";
    public static final String KEY_INAPP_MAC = "inapp_mac";
    public static final String KEY_INAPP_MAC_KEY_INDEX = "inapp_mac_key_index";
    public static final String KEY_INAPP_MERCHANT_ID = "inapp_merchant_id";
    public static final String KEY_INAPP_MERCHANT_NAME = "inapp_merchant_name";
    public static final String KEY_INAPP_ORDER_AMOUNT = "inapp_order_amount";
    public static final String KEY_INAPP_ORDER_NUMBER = "inapp_order_number";
    public static final String KEY_INAPP_ORDER_SUP_CARD_ATTR = "inapp_order_sup_card_attr";
    public static final String KEY_INAPP_PAN = "inapp_pan";
    public static final String KEY_INAPP_PAY_AMOUNT = "inapp_pay_amount";
    public static final String KEY_INAPP_SIGNED_DATA = "inapp_signed_data";
    public static final String KEY_INAPP_SIGN_KEY_INDEX = "inapp_sign_key_index";
    public static final String KEY_INAPP_VC_REFERENCE_ID = "inapp_vc_reference_id";
    public static final String KEY_INAPP_VERIFY_METHOD = "inapp_verify_method";
    public static final String KEY_INTENT = "key_intent";
    public static final String KEY_MIFARE_CARD_QUANTITY = "key_mifare_card_quantity";
    public static final String KEY_MIFARE_CARD_TYPE = "key_mifare_card_type";
    public static final String KEY_MIPAY_CARD_QUANTITY = "key_mipay_card_quantity";
    public static final String KEY_MIPAY_STATUS = "mipay_status";
    public static final String KEY_NFC_EE_STATUS = "key_nfc_ee_status";
    public static final String KEY_RESULT_CODE = "key_result_code";
    public static final String KEY_RESULT_MSG = "key_result_msg";
    public static final String KEY_SE_BANK_CARD = "se_bank_card";
    public static final int MIPAY_BINDCARD_REQUEST_CODE = 10000;
    public static final int MIPAY_STATUS_NOT_SUPPORT = 0;
    public static final int MIPAY_STATUS_SUPPORT_ALL = 6;
    public static final int MIPAY_STATUS_SUPPORT_INAPP = 2;
    public static final int MIPAY_STATUS_SUPPORT_INSTALL = 4;
    public static final int NO_ERROR = 0;
    public static final int SERVICE_STATUS_NFC_CLOSE = 4;
    public static final int SERVICE_STATUS_NFC_NOT_ROUTING_ESE = 16;
    public static final int SERVICE_STATUS_NO_ACCOUNT = 32;
    public static final int SERVICE_STATUS_NO_NFC = 2;
    public static final int SERVICE_STATUS_NO_TSMCLIENT = 1;
    public static final int SERVICE_STATUS_SUPPORT_ALLAPP = 8;
    public static final int SPTSM_ID_CUP = 0;
    public static final String URI_TSMCLIENT = "https://tsmclient.miui.com";

    public enum CardType {
        BANK,
        TRAFFIC
    }

    public enum Channel {
        UP
    }

    public enum OperationType {
        LOCK,
        DELETE,
        INSTALL
    }

    public class ErrorCode {
        public static final int CANCELLED_BY_USER = 202;
        public static final int ERROR_UNKOWN = -1;
        public static final int FIND_DEVICE_OFF = 305;
        public static final int FINISH = 99;
        public static final int INAPP_NOT_READY = 302;
        public static final int INAPP_NOT_SUPPORTED = 303;
        public static final int INTERRUPTED = 3;
        public static final int INVALID_IMEI = 104;
        public static final int INVALID_PARAM = 5;
        public static final int INVALID_SEID = 105;
        public static final int IO_EXCEPTION = 1;
        public static final int ISSUE_CONFILICT = 6;
        public static final int MIPAY_SERVICE_STATUS_IGNORED = 1010006;
        public static final int NFC_EXCEPTION = 10;
        public static final int NFC_OFF = 307;
        public static final int NO_ACCOUNT = 304;
        public static final int NO_CARD_INSTALLED = 201;
        public static final int NO_NFC = 306;
        public static final int OPERATION_NOT_SUPPORTED = 301;
        public static final int SERVER_RESPONSE_SUCCESS = 300;
        public static final int SE_NOT_INITIALED = 101;
        public static final int SE_NOT_ROUTE_ESE = 308;
        public static final int SE_NO_ENOUGH_STORAGE = 309;
        public static final int SUCCESS = 0;
        public static final int TAG_INVALID_TLV = 103;
        public static final int TAG_NOT_FOUND = 102;
        public static final int TIME_OUT = 2;
        public static final int UNAVAILABLE_SERVICE = 4;

        private ErrorCode() {
        }
    }
}
