package com.miui.tsmclient.model.mitsm;

import com.miui.tsmclient.model.ErrorCode;

public class MiTsmErrorCode {
    private static final int APDU_DATA_NOT_READY = 10076;
    private static final int APPLY_TIMES_EXCEED_LIMIT = 3603;
    private static final int APP_DUPLICATE_APPLY = 4009;
    private static final int BANK_CARD_NOT_SUPPORTED = 10014;
    private static final int BANK_SYSTEM_BUSYING = 10003;
    private static final int BIND_CARD_APPLET_UPDATE = 1002001;
    private static final int CARD_NOT_SUPPORT = 4014;
    private static final int CARD_STATUS_NO_PRIVILEGE_3 = 3617;
    private static final int CARD_TYPE_NO_APPLY_PRIVILEGE_1 = 3608;
    private static final int ERROR_CARD_EXCEED_MAXIMUM_AMOUNT = 1140;
    private static final int ERROR_CARD_HAS_BEEN_REFUNDED = 1138;
    private static final int ERROR_CARD_NUMBER_HAS_BEEN_DISABLED = 1137;
    private static final int ERROR_CARD_NUMBER_NOT_EXIST = 1136;
    private static final int ERROR_CARD_OUT_VALIDITY = 1139;
    private static final int ERROR_DEPOSIT_INSUFFICIENT = 1141;
    private static final int ERROR_ISSUE_DUPLICATE = 1134;
    private static final int ERROR_NO_STOCK = 1126;
    private static final int ERROR_RECHARGE_DUPLICATE = 1135;
    private static final int ERROR_RECHARGE_FAILED_AND_REFUND = 1133;
    private static final int ERROR_RECHARGE_FAILED_AND_UNKNOWN = 1130;
    private static final int FAILED_ACTIVATE_CARD = 3618;
    private static final int FAILED_ACTIVATE_CARD_NO_RETRY = 3619;
    private static final int FAILED_TIME_EXCEED_LIMIT = 3605;
    private static final int FPAN_DUPLICATED_APPLY = 3317;
    private static final int NULL_INPUT_INFOAMATION = 3106;
    private static final int OTP_INFO_EXPIRE = 3614;
    private static final int OTP_INFO_VERIFIED = 3613;
    private static final int OTP_INFO_VERIFY_FAILED = 3612;
    private static final int OUT_TASK_NOT_READY = 10080;
    private static final int PERSO_DATA_NOT_READY = 10048;
    public static final int RESERVED_CODE = 1000000;
    private static final int SUCCESS = 0;
    private static final int TOPUP_DATA_NOT_READY = 10050;
    private static final int TOTAL_APPLY_NUM_EXCEED_LIMIT = 10049;
    private static final int TRANSFER_IN_DATA_NOT_READY = 10074;
    private static final int TRANSFER_OUT_DATA_NOT_READY = 10073;
    private static final int USER_ACCOUNT_EXPIRE = 3609;
    private static final int USER_ACCOUNT_IN_BLANK_LIST = 3611;
    private static final int USER_ACCOUNT_NOT_EXIST = 3604;
    private static final int USER_ACCOUNT_NO_APPLY_PRIVILEGE_2 = 3606;
    private static final int USER_ACCOUNT_NO_APPLY_PRIVILEGE_3 = 3616;
    private static final int USER_CARD_VERIFY_FAILED = 3602;
    private static final int USER_IDENTITY_VERIFY_FAILED = 3601;
    private static final int USER_NOT_RESERVE_PHONE_NUM = 3610;

    public static int format(int i) {
        switch (i) {
            case ERROR_RECHARGE_DUPLICATE /*1135*/:
                return 0;
            case 1136:
                return 1136;
            case 1137:
                return 1137;
            case 1138:
                return 1138;
            case 1139:
                return 1139;
            case 1140:
                return 1140;
            case 1141:
                return 1141;
            default:
                switch (i) {
                    case USER_IDENTITY_VERIFY_FAILED /*3601*/:
                        return ErrorCode.USER_IDENTITY_VERIFY_FAILED;
                    case USER_CARD_VERIFY_FAILED /*3602*/:
                        return 3001;
                    case APPLY_TIMES_EXCEED_LIMIT /*3603*/:
                        return ErrorCode.APPLY_TIMES_EXCEED_LIMIT;
                    case USER_ACCOUNT_NOT_EXIST /*3604*/:
                        return ErrorCode.USER_ACCOUNT_NOT_EXIST;
                    case FAILED_TIME_EXCEED_LIMIT /*3605*/:
                        return ErrorCode.FAILED_TIME_EXCEED_LIMIT;
                    case USER_ACCOUNT_NO_APPLY_PRIVILEGE_2 /*3606*/:
                        return ErrorCode.USER_ACCOUNT_NO_APPLY_PRIVILEGE;
                    default:
                        switch (i) {
                            case CARD_TYPE_NO_APPLY_PRIVILEGE_1 /*3608*/:
                                return ErrorCode.CARD_TYPE_ERROR;
                            case 3609:
                                return 3002;
                            case USER_NOT_RESERVE_PHONE_NUM /*3610*/:
                                return 3003;
                            case USER_ACCOUNT_IN_BLANK_LIST /*3611*/:
                                return ErrorCode.USER_ACCOUNT_IN_BLANK_LIST;
                            case OTP_INFO_VERIFY_FAILED /*3612*/:
                                return ErrorCode.OTP_INFO_VERIFY_FAILED;
                            case OTP_INFO_VERIFIED /*3613*/:
                                return 3011;
                            case OTP_INFO_EXPIRE /*3614*/:
                                return ErrorCode.OTP_INFO_EXPIRE;
                            default:
                                switch (i) {
                                    case USER_ACCOUNT_NO_APPLY_PRIVILEGE_3 /*3616*/:
                                        return ErrorCode.USER_NO_APPLY_PRIVILEGE;
                                    case CARD_STATUS_NO_PRIVILEGE_3 /*3617*/:
                                        return ErrorCode.CARD_STATUS_ERROR;
                                    case 3618:
                                        return 3021;
                                    case 3619:
                                        return ErrorCode.CONTACT_CUSTOMER_SERVICE;
                                    default:
                                        switch (i) {
                                            case PERSO_DATA_NOT_READY /*10048*/:
                                                return ErrorCode.DATA_NOT_READY;
                                            case TOTAL_APPLY_NUM_EXCEED_LIMIT /*10049*/:
                                                return ErrorCode.TOTAL_APPLY_NUM_EXCEED_LIMIT;
                                            case TOPUP_DATA_NOT_READY /*10050*/:
                                                break;
                                            default:
                                                switch (i) {
                                                    case TRANSFER_OUT_DATA_NOT_READY /*10073*/:
                                                    case TRANSFER_IN_DATA_NOT_READY /*10074*/:
                                                        break;
                                                    default:
                                                        switch (i) {
                                                            case 0:
                                                                return 0;
                                                            case ERROR_NO_STOCK /*1126*/:
                                                                return 205;
                                                            case ERROR_RECHARGE_FAILED_AND_UNKNOWN /*1130*/:
                                                                return 1004;
                                                            case 1133:
                                                                return 1012;
                                                            case NULL_INPUT_INFOAMATION /*3106*/:
                                                                return ErrorCode.NULL_KEY_INFOMATION;
                                                            case FPAN_DUPLICATED_APPLY /*3317*/:
                                                            case APP_DUPLICATE_APPLY /*4009*/:
                                                                return ErrorCode.FPAN_DUPLICATED_APPLY;
                                                            case CARD_NOT_SUPPORT /*4014*/:
                                                            case BANK_CARD_NOT_SUPPORTED /*10014*/:
                                                                return ErrorCode.BANK_CARD_NOT_SUPPORTED;
                                                            case 10003:
                                                                return ErrorCode.BANK_SYSTEM_BUSYING;
                                                            case APDU_DATA_NOT_READY /*10076*/:
                                                            case OUT_TASK_NOT_READY /*10080*/:
                                                                break;
                                                            case BIND_CARD_APPLET_UPDATE /*1002001*/:
                                                                return ErrorCode.BIND_CARD_APPLET_UPDATE;
                                                            default:
                                                                return i + 1000000;
                                                        }
                                                }
                                        }
                                        return ErrorCode.DATA_NOT_READY;
                                }
                        }
                }
        }
    }

    private MiTsmErrorCode() {
    }
}
