package cn.com.fmsh.nfcos.client.service.constants;

import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;

public interface Constants {
    public static final int LOGIN_SUCESS = 0;
    public static final int SUCESS_CODE = 0;
    public static final byte[] aid;

    public interface ApduHandlerType {
        public static final int NFC = 1;
        public static final int OMA = 0;
    }

    public interface BusinessOrderType {
        public static final int ORDER_TYPE_ISSUE = 2;
        public static final int ORDER_TYPE_PROMOTION = 3;
        public static final int ORDER_TYPE_RECHARGE = 1;
        public static final int TRANSFER = 4;
        public static final int UNKNOW = 0;
    }

    public interface CardAppStatus {
        public static final int STATUS_ACTIVATE = 5;
        public static final int STATUS_INSTALL = 3;
        public static final int STATUS_LOADED = 2;
        public static final int STATUS_NO_APP = 1;
        public static final int STATUS_PERSONALIZED = 4;
        public static final int STATUS_UNKNOWN = 10;
    }

    public interface CardAppType {
        public static final int CARD_APP_TYPE_LTN = 2;
        public static final int CARD_APP_TYPE_SH = 1;
    }

    public interface CardIoType {
        public static final int CARD_IO_TYPE_IN = 2;
        public static final int CARD_IO_TYPE_OUT = 1;
        public static final int CARD_IO_UNKNOW = 0;
    }

    public interface DataBase {
        public static final String COLUMN_PARA_CONTENT = "CONTENT";
        public static final String DATABASE_NAME = "fmsh_localData.db";

        public interface Column4Card {
            public static final String COLUMN_AMOUNT = "AMOUNT";
            public static final String COLUMN_BALANCE = "BALANCE";
            public static final String COLUMN_DATETIME = "DATETIME";
            public static final String COLUMN_DEVICE_NO = "DEVICE_NO";
            public static final String COLUMN_FACEID = "FACEID";
            public static final String COLUMN_ID = "ID";
            public static final String COLUMN_ORI_TRADETYPE = "ORI_TRADETYPE";
            public static final String COLUMN_TERMINAL_TRADETYPE = "TERMINAL_TRADETYPE";
        }

        public interface Column4Notice {
            public static final String COLUMN_NOTICE_BODY = "BODY";
            public static final String COLUMN_NOTICE_E_DATE = "E_DATA";
            public static final String COLUMN_NOTICE_ID = "ID";
            public static final String COLUMN_NOTICE_S_DATE = "S_DATE";
            public static final String COLUMN_NOTICE_TITLE = "TITLE";
        }

        public interface Column4Recharge {
            public static final String COLUMN_RECHARGE_CARD_NO = "CARD_NO";
            public static final String COLUMN_RECHARGE_ORDER_NO = "ORDER_NO";
            public static final String COLUMN_RECHARGE_SERIAL_NO = "SERIAL_NO";
            public static final String COLUMN_RECHARGE_STATUS = "STATUS";
            public static final String COLUMN_RECHARGE_TAC = "TAC";
            public static final String COLUMN_RECHARGE_TERMINAL_NO = "TERMINAL_NO";
            public static final String COLUMN_RECHARGE_TRADE_TIME = "TRADE_TIME";
        }

        public interface TableName {
            public static final String FM_NFC_PTCARD = "FM_NFC_PTCARD";
            public static final String FM_NOTICE = "FM_NOTICE";
            public static final String FM_PARA = "FM_PARA";
            public static final String FM_RECHARGE_RECORD = "FM_RECHARGE_RECORD";
        }
    }

    public interface ErrorCode {
        public static final int AIDL_PARAMETER_NULL = 9112;
        public static final int BUSINESS_HANDLE_FAIL = 99;
        public static final int ISSUER_FAIL_NO_ORDER = 9800;
        public static final int ISSUER_FAIL_ORDER_NO_PAID = 1102;
        public static final int NFC_TAG_INVAILD = 9110;
        public static final int OPEN_MOBILE_CHANNEL_INVAILD = 9100;
        public static final int OPEN_MOBILE_OPEN_CHANNEL_FAIL = 9104;
        public static final int OPEN_MOBILE_OPEN_READER_FAIL = 9102;
        public static final int OPEN_MOBILE_OPEN_SESSION_FAIL = 9103;
        public static final int OPEN_MOBILE_SERVICE_INVAILD = 9105;
        public static final int OPEN_MOBILE_SESERVICE_NULL = 9101;
        public static final int TERMINAL_SECURITY_CODE_INVAILD = 9111;
    }

    public interface IssueFlag {
        public static final byte all = 1;
        public static final byte end4Activity = -124;
        public static final byte end4download = -127;
        public static final byte end4install = -126;
        public static final byte end4personalization = -125;
        public static final byte personalization = 2;
    }

    public interface IssueProcess {
        public static final int APPLIED = 0;
        public static final int APP_ACTIVATION = 50;
        public static final int APP_INSTALL = 30;
        public static final int APP_LOAD = 20;
        public static final int APP_LOCK = 60;
        public static final int APP_PERSONAL = 40;
        public static final int APP_REMOVE = 70;
        public static final int SSD_KEY_UPDATED = 10;
    }

    public interface OrderStatus {
        public static final int APPLY_FOR_REFUND = 6;
        public static final int DUBIOUS = 12;
        public static final int ERROR = 99;
        public static final int FAILURE = 4;
        public static final int HAS_PAIED = 2;
        public static final int HAS_REFUNDED = 7;
        public static final int INVALID = 13;
        public static final int NOT = 0;
        public static final int PAY_FAILURE = 9;
        public static final int RECHARGING = 11;
        public static final int REFUND_FAILURE = 8;
        public static final int SUCCESS = 3;
        public static final int UNKNOWN = 5;
    }

    public interface OrderType {
        public static final int BUSINESS = 2;
        public static final int MAIN = 1;
        public static final int PAY = 3;
    }

    public interface RechargeMode {
        public static final int MIPAY = 81;
        public static final int UNIONPAY = 3;
        public static final int UNIONPAY_CARD = 49;
    }

    public interface TagName4Attach {
        public static final byte AMOUNT_NAME = 1;
        public static final byte APP_NO_NAME = 3;
        public static final byte CHANNEL_NAME = 2;
        public static final byte CIN_NAME = 4;
        public static final byte MODULE_NAME = 5;
        public static final byte PRODUCT_NAME = 1;
        public static final byte SEID_NAME = 3;
    }

    public interface TradeType {
        public static final int BUS = 1;
        public static final int COMPOSITE_CONSUMPTION = 16;
        public static final int CONSUMPTION = 15;
        public static final int EXPRESSWAY = 9;
        public static final int FERRY = 7;
        public static final int GAS_STATION = 11;
        public static final int MAGLEV = 5;
        public static final int ONLINE_CONSUMPTION = 13;
        public static final int ONLINE_RECHARGE = 12;
        public static final int OTHERS = 14;
        public static final int PARK = 10;
        public static final int PRIVILEGE = 2;
        public static final int RECHARGE = 6;
        public static final int SUBWAY_CONSUMPTION = 3;
        public static final int SUBWAY_UPDATE = 4;
        public static final int TAXI = 8;
    }

    static {
        byte[] bArr = new byte[9];
        bArr[0] = ScriptToolsConst.TagName.CommandSingle;
        bArr[4] = 3;
        bArr[5] = Constants.TagName.ACTIVITY_TOTAL;
        bArr[6] = Constants.TagName.PRODUCT_INFO;
        bArr[7] = 7;
        bArr[8] = 1;
        aid = bArr;
    }

    public interface appAid {
        public static final byte[] LINGNAN_PASS_AID;
        public static final byte[] STPC_AID;
        public static final byte[] STPC_AID_EXT;

        static {
            byte[] bArr = new byte[9];
            bArr[0] = ScriptToolsConst.TagName.CommandSingle;
            bArr[4] = 3;
            bArr[5] = Constants.TagName.ACTIVITY_TOTAL;
            bArr[6] = Constants.TagName.PRODUCT_INFO;
            bArr[7] = 7;
            bArr[8] = 1;
            STPC_AID = bArr;
            byte[] bArr2 = new byte[11];
            bArr2[0] = ScriptToolsConst.TagName.CommandSingle;
            bArr2[4] = 3;
            bArr2[5] = Constants.TagName.ACTIVITY_TOTAL;
            bArr2[6] = Constants.TagName.PRODUCT_INFO;
            bArr2[7] = 7;
            bArr2[8] = 1;
            bArr2[9] = 32;
            STPC_AID_EXT = bArr2;
            byte[] bArr3 = new byte[9];
            bArr3[0] = ScriptToolsConst.TagName.CommandSingle;
            bArr3[4] = 3;
            bArr3[5] = Constants.TagName.ACTIVITY_TOTAL;
            bArr3[6] = Constants.TagName.PRODUCT_INFO;
            bArr3[7] = 7;
            bArr3[8] = 1;
            LINGNAN_PASS_AID = bArr3;
        }
    }
}
