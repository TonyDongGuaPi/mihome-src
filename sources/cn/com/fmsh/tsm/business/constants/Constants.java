package cn.com.fmsh.tsm.business.constants;

import cn.com.fmsh.script.constants.ScriptToolsConst;

public interface Constants {
    public static final String CODE_PROPERTIES_FILE = "/code.properties";
    public static final String DEFAULT_APP_NO = "00000000000000000000";
    public static final String DEFAULT_STATION_VERSION = "0";
    public static final String PROTOCOL_CONFIG_FILE = "/message.xml";
    public static final String SYSTEM_CONFIG_FILE = "/business.xml";
    public static final int TERMIANL_NUMBER_LENGTH = 32;

    public interface AppLock4Consume {
        public static final int lock = 1;
        public static final int unlock = 0;
    }

    public interface AppLock4Load {
        public static final int lock = 1;
        public static final int unlock = 0;
    }

    public interface AppManagerType {
        public static final byte CLEAN = 15;
        public static final byte INSTALL = 3;
        public static final byte ISSUER = 1;
        public static final byte PERSONLIZATION = 2;
        public static final byte QUERY_STATUS = 4;
    }

    public interface BillType {
        public static final int ALL = 0;
        public static final int CONFIRM_DOUBT = 2;
        public static final int DOUBT = 1;
    }

    public interface CardForm {
        public static final int IN_CARD = 2;
        public static final int OUT_CARD = 1;
    }

    public interface CardType {
        public static final byte CARD_SH = 1;
    }

    public interface Config {
        public static final String CONFIG_NAME_PREFIC = "business";
    }

    public interface NetworkStatus {
        public static final int CONNECT_FAILURE = 2;
        public static final int NONE = 1;
        public static final int SUCCESS = 99;
    }

    public interface OrderSource {
        public static final int CHANNEL_MI = 32;
        public static final int CHANNEL_MOBILE = 21;
    }

    public interface PayChannel {
        public static final byte ONEKEY = 1;
        public static final byte SECURITY = 2;
        public static final byte UNIONPAY = 3;
    }

    public interface RechargeStatus {
        public static final int DOUBT = 2;
        public static final int FAILURE = 0;
        public static final int SUCCESS = 1;
    }

    public interface Result4BusinessHandle {
        public static final int FAILURE = -1;
        public static final int SUCESS = 0;
    }

    public interface TagName {
        public static final byte ACTIVITIES = Byte.MIN_VALUE;
        public static final byte ACTIVITY = -127;
        public static final byte ACTIVITY_CODE = -125;
        public static final byte ACTIVITY_CODE_LIST = 51;
        public static final byte ACTIVITY_DEFINITION = -120;
        public static final byte ACTIVITY_END = -123;
        public static final byte ACTIVITY_INFO = 71;
        public static final byte ACTIVITY_NAME = -126;
        public static final byte ACTIVITY_REMAINDER = -121;
        public static final byte ACTIVITY_START = -124;
        public static final byte ACTIVITY_STATUS = -115;
        public static final byte ACTIVITY_TOTAL = -122;
        public static final byte APK_DOWNLOAD_URL = 28;
        public static final byte APK_SIZE = 48;
        public static final byte APK_UPDATE_FLAG = 45;
        public static final byte APP_AID = -77;
        public static final byte APP_MANAGE_OPEATE_TYPE = -69;
        public static final byte APP_TYPE = -78;
        public static final byte BUSINESS_HANDLE_RESULT = -75;
        public static final byte BUSINESS_ORDER = 26;
        public static final byte BUSINESS_ORDER_ID = 17;
        public static final byte BUSINESS_ORDER_LIST = 27;
        public static final byte BUSINESS_ORDER_LOAD_TYPE = -55;
        public static final byte BUSINESS_ORDER_OP_TYPE = 58;
        public static final byte BUSINESS_ORDER_TYPE = 72;
        public static final byte CARD_APP_ACTIVATION_STATUS = 63;
        public static final byte CARD_APP_BLANCE = 40;
        public static final byte CARD_APP_RAMDOM = 59;
        public static final byte CARD_APP_VERSION = 61;
        public static final byte CARD_BUSINESS_OP_RECOMMENED = 88;
        public static final byte CARD_BUSINESS_ORDER_STATUS = 62;
        public static final byte CARD_FORM = 47;
        public static final byte CARD_NO = 15;
        public static final byte CARD_TYPE = 14;
        public static final byte CITY_CODE = -101;
        public static final byte COMPANY_CODE = -119;
        public static final byte CPLC = -74;
        public static final byte CP_NO = 79;
        public static final byte DEVICE_MODEL = 104;
        public static final byte ELECTRONIC = 107;
        public static final byte ELECTRONIC_APP_TYPE = 120;
        public static final byte ELECTRONIC_END_TIME = 111;
        public static final byte ELECTRONIC_FROZEN_FLAG = 118;
        public static final byte ELECTRONIC_ID = 112;
        public static final byte ELECTRONIC_LIST = 108;
        public static final byte ELECTRONIC_NUMBER = 114;
        public static final byte ELECTRONIC_OUT_SERIAL = 126;
        public static final byte ELECTRONIC_OUT_STATE = 122;
        public static final byte ELECTRONIC_PRICE_FAVOURABLE = 125;
        public static final byte ELECTRONIC_PUBLISH_START_TIME = Byte.MAX_VALUE;
        public static final byte ELECTRONIC_STARTTIME = 110;
        public static final byte ELECTRONIC_STATE = 121;
        public static final byte ELECTRONIC_TRANSFER_FLAG = 117;
        public static final byte ELECTRONIC_TYPE = 115;
        public static final byte ELECTRONIC_TYPE_ID = 113;
        public static final int ELECTRONIC_USE_COUNT = 119;
        public static final byte ELECTRONIC_USE_TIME = 123;
        public static final byte ELECTRONIC_USE_TYPE = 116;
        public static final byte EUID = -72;
        public static final byte IDENTIFYING_CODE = 12;
        public static final byte IDENTIFYING_SERIAL = 64;
        public static final byte IDENTIFYING_TYPE = 11;
        public static final byte IMEI = -70;
        public static final byte INVOICE_TOKEN = 66;
        public static final byte INVOICE_TOKEN_OBJECT = -99;
        public static final byte INVOICE_TOKEN_OBJECT_LIST = -98;
        public static final byte MAIN_ORDER = 96;
        public static final byte MAIN_ORDER_ID = 105;
        public static final byte MAIN_ORDER_LIST = 97;
        public static final byte MOC = 95;
        public static final byte NOTICE_BODY = 52;
        public static final byte NOTICE_END_TIME = 55;
        public static final byte NOTICE_ID = 49;
        public static final byte NOTICE_START_TIME = 54;
        public static final byte NOTICE_TITLE = 50;
        public static final byte OPERATE_TIMING = 98;
        public static final byte OPERATION_ID = -90;
        public static final byte OPERATION_STEP = -89;
        public static final byte ORDER_AMOUNT = 16;
        public static final byte ORDER_BRIEF_INFO = 73;
        public static final byte ORDER_BRIEF_INFO_LIST = 80;
        public static final byte ORDER_CHANNEL = 30;
        public static final byte ORDER_DATE = 19;
        public static final byte ORDER_INVOICE_STATUS = 24;
        public static final byte ORDER_QUERY_PARAM = 25;
        public static final byte ORDER_RANGE_TYPE = 37;
        public static final byte ORDER_TAC = 32;
        public static final byte ORDER_TERMINAL = 23;
        public static final byte ORDER_TIME = 20;
        public static final byte ORDER_TRADE_NO = 22;
        public static final byte ORDER_TRADE_STATUS = 21;
        public static final byte ORDER_TRADE_STATUSES = 92;
        public static final byte ORDER_TYPE = 101;
        public static final byte PASSWORD_MODIFY = 9;
        public static final byte PASSWORD_PROMPT = 10;
        public static final byte PATCH_DATA = -76;
        public static final byte PAY_CHANNEL = 13;
        public static final byte PAY_CHANNEL_MIN = -117;
        public static final byte PAY_CHANNEL_NAME = -118;
        public static final byte PAY_ORDER = 99;
        public static final byte PAY_ORDER_ID = 106;
        public static final byte PAY_ORDER_LIST = 100;
        public static final byte PLATFORM_NOTICES = -109;
        public static final byte PREDEPOSIT_BLANCE = 91;
        public static final byte PREDEPOSIT_INFO = -106;
        public static final byte PREDEPOSIT_LIST = -105;
        public static final byte PREDEPOSIT_STATUS = -116;
        public static final byte PREDEPOSIT_TOTAL = 90;
        public static final byte PREDEPOSIT_TYPE = -107;
        public static final byte PRICE = 124;
        public static final byte PRODUCT_CODE = -103;
        public static final byte PRODUCT_ID = 103;
        public static final byte PRODUCT_INFO = -104;
        public static final byte PRODUCT_LIST = -100;
        public static final byte PRODUCT_NAME = -102;
        public static final byte PROMOTION_MESSAGE = -58;
        public static final byte PROMOTION_MESSAGE_DATA = -56;
        public static final byte PROMOTION_MESSAGE_LIST = -57;
        public static final byte PUBLISH_END_TIME = 109;
        public static final byte QUERY_DATA_SORT_TYPE = 86;
        public static final byte QUERY_RECORD_COUNT = 38;
        public static final byte RENT_HANDLE_DATD = -61;
        public static final byte RENT_HANDLE_TYPE = -62;
        public static final byte SEID = -79;
        public static final byte SIM_SEID = 46;
        public static final byte SIR = -71;
        public static final byte STATION_CONFIG_VERSION = -68;
        public static final byte STATION_ENAME = -64;
        public static final byte STATION_ID = -65;
        public static final byte STATION_INFO = -67;
        public static final byte STATION_INFO_LIST = -66;
        public static final byte STATION_NAME = -63;
        public static final byte SYSTEM_NEW_VERSION = 44;
        public static final byte SYSTEM_VERSION = -112;
        public static final byte TERMINAL_BACK_CHILDREN_ID = 85;
        public static final byte TERMINAL_BACK_CONTENT = 65;
        public static final byte TERMINAL_BACK_INFO = 83;
        public static final byte TERMINAL_BACK_INFO_LIST = 84;
        public static final byte TERMINAL_BACK_INFO_TYPE = 67;
        public static final byte TERMINAL_BACK_MAIN_ID = 81;
        public static final byte TERMINAL_BACK_QUESTION_FLAG = 82;
        public static final byte TERMINAL_BASEBAND_VERSION = 70;
        public static final byte TERMINAL_INFO_TYPE = 93;
        public static final byte TERMINAL_MODEL_NUMBER = 69;
        public static final byte TERMINAL_OP_TYPE = 76;
        public static final byte TERMINAL_OS_VERSION = 68;
        public static final byte TEXT_NOTICE = -110;
        public static final byte THIRD_PAY_NUMBER = 18;
        public static final byte TRADE_STATUS = 31;
        public static final byte UNSOLVED_NOTICES = -108;
        public static final byte URL_LIST = -113;
        public static final byte URL_TYPE = -114;
        public static final byte USER_ACCOUNT = 2;
        public static final byte USER_CERT_NO = 8;
        public static final byte USER_CERT_TYPE = 7;
        public static final byte USER_EMAIL = 4;
        public static final byte USER_LOCK_TIME = 43;
        public static final byte USER_LOGIN_FAIL_COUNT = 36;
        public static final byte USER_MOBILE = 5;
        public static final byte USER_NAME = 6;
        public static final byte USER_PASS = 3;
        public static final byte USER_PLATFORM_ID = -59;
        public static final byte USER_PLATFORM_TYPE = -60;
        public static final byte USER_TYPE = 1;

        public interface TerminalInfoTag {
            public static final byte BUSINESS_VERSION = 5;
            public static final byte SKD_VERSION = 4;
            public static final byte TERMINAL_MODEL = 1;
            public static final byte TERMINAL_OS_VERSION = 2;
            public static final byte TERMINAL_VERSION = 3;
        }
    }

    public interface TagValue {
        public static final byte BUSINESS_HANDLE_RESULT_FAIL = -1;
        public static final byte BUSINESS_HANDLE_RESULT_SUCESS = 0;
    }

    public interface TagValueLength {
        public static final int NOTICE_ID = 8;
    }

    public interface TicketOperateType {
        public static final int APPLY_BUSINESS = 91;
        public static final int QUERY_LAST_OPERATE = 94;
        public static final int RETURN_TICKET = 92;
        public static final int WRITE_TICKET = 93;
    }

    public interface TradeCode {
        public static final int ALIPAY_ONE_KEY = 2111;
        public static final int ALIPAY_ONE_KEY_CANCEL = 2031;
        public static final int ALIPAY_ONE_KEY_QUERY = 2021;
        public static final int ALIPAY_ONE_KEY_SIGN = 2011;
        public static final int APPLET_DOWNLOAD = 8851;
        public static final int APPLET_DOWNLOAD_VER2 = 8852;
        public static final int APPLY_FOR_ELECTRONIC_TAKEUP = 4611;
        public static final int APPLY_ORDER = 1111;
        public static final int APPLY_ORDER_EX = 1141;
        public static final int APPLY_ORDER_EX_VER2 = 1142;
        public static final int APPLY_ORDER_VER2 = 1112;
        public static final int APP_ISSUER = 8811;
        public static final int APP_ISSUER_PREPARE = 8821;
        public static final int APP_ISSUER_PREPARE_RESULT = 8831;
        public static final int APP_ISSUER_VER2 = 8812;
        public static final int APP_MANAGER = 8841;
        public static final int APP_MANAGER_VER2 = 8842;
        public static final int BUSINESS_ORDER_SETTING = 3041;
        public static final int BUSINESS_ORDER_SETTING_VER2 = 3042;
        public static final int CHECK_SERVER = 1221;
        public static final int DEAL_WITH_DOUBT = 3021;
        public static final int DELETE_TERMINAL_BACK = 4021;
        public static final int GET_CODE = 1061;
        public static final int GET_INVOICE = 3061;
        public static final int GET_INVOICE_VER2 = 3062;
        public static final int GET_INVOICE_VER3 = 3063;
        public static final int LOGIN = 1021;
        public static final int LOGIN_VER2 = 1022;
        public static final int LOGIN_VER3 = 1023;
        public static final int MAIN_ORDER_EXEC = 3091;
        public static final int PERSONLIZATION = 3071;
        public static final int PROMOTION_APPLY_ORDER = 1201;
        public static final int PWD_MODIFY = 1031;
        public static final int QUERY_ACTIVITIES = 1151;
        public static final int QUERY_BUSINESS_ORDER_STATUS = 3051;
        public static final int QUERY_ELECTRONIC_ACTIVITY = 1181;
        public static final int QUERY_ELECTRONIC_TAKEUP = 4631;
        public static final int QUERY_ELECTRONIC_TAKEUP_LIST = 4641;
        public static final int QUERY_ELECTRONIC_TAKEUP_LIST_VER2 = 4642;
        public static final int QUERY_IDENTIFYING = 1061;
        public static final int QUERY_NOTICE = 1311;
        public static final int QUERY_ORDER = 1121;
        public static final int QUERY_ORDERS = 1131;
        public static final int QUERY_ORDERS_VER2 = 1132;
        public static final int QUERY_ORDERS_VER3 = 1133;
        public static final int QUERY_ORDERS_VER4 = 1134;
        public static final int QUERY_ORDER_VER2 = 1122;
        public static final int QUERY_PREDEPOSIT = 1161;
        public static final int QUERY_PREDEPOSIT_VER2 = 1162;
        public static final int QUERY_PRODUCT_INFO = 5021;
        public static final int QUERY_PRODUCT_LIST = 5011;
        public static final int QUERY_PROMOTION_MESSAGE_LIST = 1191;
        public static final int QUERY_TERMINAL_BACK = 4011;
        public static final int QUERY_USER_INFO = 1051;
        public static final int QUERY_USER_INFO_VER2 = 1052;
        public static final int QUERY_VERSION = 1211;
        public static final int REFUND = 2121;
        public static final int REFUND_VER2 = 2122;
        public static final int REMOTE_RECHARGE = 3011;
        public static final int RENT_BUSINESS_HANDLE = 3101;
        public static final int REPORT_TO_PLATFORM = 9001;
        public static final int RETRIVE_PWD = 1041;
        public static final int RETRIVE_PWD_VER3 = 1043;
        public static final int SET_ORDER_STATUS = 1171;
        public static final int STATION_INFO_UPDATE = 3081;
        public static final int SUCESS_4_PLATFORM = 9000;
        public static final int TERMINAL_BACK = 4001;
        public static final int TERMINAL_INFO_REPORT = 4031;
        public static final int TICKET_MANAGER = 8861;
        public static final int USER_REGISTER = 1001;
        public static final int USER_REGISTER_VER2 = 1002;
        public static final int USER_REGISTER_VER3 = 1003;
        public static final int USER_UPDATE = 1011;
        public static final int USER_UPDATE_VER2 = 1012;
        public static final int USE_ELECTRONIC_TAKEUP = 4621;
        public static final int VERIFY_CODE = 1071;
    }

    public interface UserType {
        public static final int COMMON = 1;
        public static final int REAL_NAME = 2;
        public static final int REAL_NAME_AUTH = 3;
    }

    public interface XMLNode {
        public static final String AID = "Aid";
        public static final String AID_VALUE = "value";
        public static final String BUSINESS_AND_SERVER = "BusinessAndServer";
        public static final String BUSINESS_NAME = "businessName";
        public static final String CIPHER = "Cipher";
        public static final String COMPANY_CODE = "CompanyCode";
        public static final String COMPANY_CODE_VALUE = "value";
        public static final String KEY = "Key";
        public static final String KEY_CIPHER = "cipher";
        public static final String KEY_ENCRY_TYPE = "encryType";
        public static final String KEY_EXPONENT = "exponent";
        public static final String KEY_INDEX = "index";
        public static final String KEY_MODULUS = "modulus";
        public static final String LOG_LEVEL = "LogLevel";
        public static final String LOG_LEVEL_VALUE = "value";
        public static final String ORDER_SOURCE = "OrderSource";
        public static final String ORDER_SOURCE_VALUE = "value";
        public static final String SDK_VERSION = "sdkVersion";
        public static final String SERVER = "Server";
        public static final String SERVER_DOMAIN = "domain";
        public static final String SERVER_NAME = "serverName";
        public static final String SERVER_PORT = "port";
        public static final String SERVER_TOMEOUT = "socketTimeout";
        public static final String TERMINAL = "Termianl";
        public static final String TERMINAL_BUSINESS_VERSION = "businessVersion";
        public static final String TERMINAL_OS_VERSION = "osVersion";
        public static final String TERMINAL_TYPE = "type";
        public static final String TERMINAL_VERSION = "terminalVersion";
    }

    public interface Command {
        public static final byte[] LOAD_HEAD = {Byte.MIN_VALUE, TagName.TERMINAL_BACK_QUESTION_FLAG};
        public static final byte[] LOAD_INITIALIZE_HEAD = {Byte.MIN_VALUE, TagName.ORDER_BRIEF_INFO_LIST};
        public static final byte[] SELECT_0015;
        public static final byte[] SELECT_3F01;
        public static final byte[] UPDATE_VALID_DATE = {4, -42, TagName.PREDEPOSIT_TYPE, 24, 8};

        static {
            byte[] bArr = new byte[7];
            bArr[1] = ScriptToolsConst.TagName.CommandMultiple;
            bArr[4] = 2;
            bArr[5] = TagName.CARD_APP_ACTIVATION_STATUS;
            bArr[6] = 1;
            SELECT_3F01 = bArr;
            byte[] bArr2 = new byte[5];
            bArr2[1] = -80;
            bArr2[2] = TagName.PREDEPOSIT_TYPE;
            SELECT_0015 = bArr2;
        }
    }

    public interface RespCodeonse4Platform {
        public static final byte[] CARD_REQUEST = {TagName.SYSTEM_VERSION, 1};
        public static final byte[] SUCESS;

        static {
            byte[] bArr = new byte[2];
            bArr[0] = TagName.SYSTEM_VERSION;
            SUCESS = bArr;
        }
    }

    public interface LoginCode {
        public static final byte[] CANCEL_CONTRACT = {16, 16};
        public static final byte[] CONTRACTING = {16, 3};
        public static final byte[] CONTRACT_FAIL = {16, 4};
        public static final byte[] CONTRACT_OK = {16, 5};
        public static final byte[] FROZEN = {16, 18};
        public static final byte[] INFO_INCOMPLETE = {16, 20};
        public static final byte[] INVALID_PWD = {16, 1};
        public static final byte[] LOCKED = {16, 17};
        public static final byte[] LOGOUT = {16, 6};
        public static final byte[] PWD_OVERRUN = {16, 19};
        public static final byte[] REGISTER = {16, 7};
        public static final byte[] UNCONTRACT = {16, 8};
        public static final byte[] UNCONTRACT_USER = {16, 2};
        public static final byte[] UNREGISTER;

        static {
            byte[] bArr = new byte[2];
            bArr[0] = 16;
            UNREGISTER = bArr;
        }
    }
}
