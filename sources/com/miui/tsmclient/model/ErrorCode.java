package com.miui.tsmclient.model;

import android.content.Context;
import android.text.TextUtils;
import com.miui.tsmclient.util.ResUtils;
import com.tsmclient.smartcard.Coder;

public class ErrorCode {
    public static final int APPLY_TIMES_EXCEED_LIMIT = 3008;
    public static final int BANK_CARD_NOT_SUPPORTED = 3013;
    public static final int BANK_SYSTEM_BUSYING = 3025;
    public static final int BIND_CARD_APPLET_UPDATE = 3024;
    public static final int CARD_NOT_SUPPORT_CURRENT_OPERATION = 505;
    public static final int CARD_STATUS_ERROR = 3019;
    public static final int CARD_TYPE_ERROR = 3020;
    public static final int CARD_VERIFY_FAILED = 3001;
    public static final int CONTACT_CUSTOMER_SERVICE = 3022;
    public static final int DATA_NOT_READY = 3007;
    public static final int DEFAULT_VALUE = -1;
    public static final int ERROR_ACCOUNT_LOCKED = 1009;
    public static final int ERROR_AMOUNT_LARGER_THAN_TOTAL_AMOUNT = 1008;
    public static final int ERROR_APPLET_NEED_UPGRADE = 7007;
    public static final int ERROR_AUTH_FAILED = 7;
    public static final int ERROR_BALANCE_SMALLER_THAN_MIN_BALANCE = 1013;
    public static final int ERROR_CARD_CONFLICT = 2000;
    public static final int ERROR_CARD_EXCEED_MAXIMUM_AMOUNT = 1140;
    public static final int ERROR_CARD_HAS_BEEN_REFUNDED = 1138;
    public static final int ERROR_CARD_INVALID = 2002;
    public static final int ERROR_CARD_NOT_EXIST = 2003;
    public static final int ERROR_CARD_NO_STOCK = 205;
    public static final int ERROR_CARD_NUMBER_HAS_BEEN_DISABLED = 1137;
    public static final int ERROR_CARD_NUMBER_NOT_EXIST = 1136;
    public static final int ERROR_CARD_OUT_VALIDITY = 1139;
    public static final int ERROR_CLIENT_FORCE_INTERRUPT = 6;
    public static final int ERROR_CLIENT_INVALID_PARAM = 1;
    public static final int ERROR_CONFLICT_APP = 9;
    public static final int ERROR_DEPOSIT_INSUFFICIENT = 1141;
    public static final int ERROR_DUPLICATE_PAY = 1000;
    public static final int ERROR_ESE_ROUTE_DISABLED = 33;
    public static final int ERROR_GET_ACCOUNT = 14;
    public static final int ERROR_GET_CPLC = 13;
    public static final int ERROR_GET_DEVICE_INFO = 15;
    public static final int ERROR_HANDLE_UNSOLVED_ORDER_FAILED = 1003;
    public static final int ERROR_HAS_NO_PROMOTION_RESOURCE = 1002;
    public static final int ERROR_INTERRUPTED = 11;
    public static final int ERROR_IO_EXCEPTION = 12;
    public static final int ERROR_LOGIN_FAILED = 5;
    public static final int ERROR_MIUI_VERSION_RESTRICTED = 211;
    public static final int ERROR_NETWORK = 2;
    public static final int ERROR_NFC = 10;
    public static final int ERROR_NFC_DISABLED = 31;
    public static final int ERROR_NOT_GET_SERVICE = 3;
    public static final int ERROR_NO_PERMISSION = 20;
    public static final int ERROR_OPERATION_NOT_SUPPORT = 17;
    public static final int ERROR_ORDER_HANDLE_UNFINISH = 1010;
    public static final int ERROR_ORDER_STATE_NEED_CONFIRM = 1004;
    public static final int ERROR_PAY_AMOUNT_TOO_LARGE = 1007;
    public static final int ERROR_PAY_AMOUNT_TOO_SMALL = 1006;
    public static final int ERROR_PLUGIN_NOT_FOUND = 1005;
    public static final int ERROR_RECHARGE_FAILED = 1001;
    public static final int ERROR_RECHARGE_FAILED_AND_REFUND = 1012;
    public static final int ERROR_REMOTE_FAILED = 4;
    public static final int ERROR_SERVER_RESPONSE = 16;
    public static final int ERROR_SE_RESTRICTED = 32;
    public static final int ERROR_TRANSMIT_APDU = 8;
    public static final int ERROR_UNKNOWN = -2;
    public static final int FAILED_TIME_EXCEED_LIMIT = 3009;
    public static final int FPAN_DUPLICATED_APPLY = 3017;
    public static final int NULL_KEY_INFOMATION = 3015;
    public static final int OTP_INFO_EXPIRE = 3005;
    public static final int OTP_INFO_REVERIFY = 3011;
    public static final int OTP_INFO_VERIFY_FAILED = 3004;
    public static final int REJECT_UNRESTRICT_ESE = 3026;
    public static final int RETRY_OR_CONTACT_CUSTOMER_SERVICE = 3021;
    public static final int SE_APP_NOT_FOUND = 10021;
    public static final int SUCCESS = 0;
    public static final int TOTAL_APPLY_NUM_EXCEED_LIMIT = 3018;
    public static final int USER_ACCOUNT_EXPIRE = 3002;
    public static final int USER_ACCOUNT_IN_BLANK_LIST = 3016;
    public static final int USER_ACCOUNT_NOT_EXIST = 3012;
    public static final int USER_ACCOUNT_NO_APPLY_PRIVILEGE = 3010;
    public static final int USER_IDENTITY_VERIFY_FAILED = 3014;
    public static final int USER_NOT_RESERVE_PHONE_NUM = 3003;
    public static final int USER_NO_APPLY_PRIVILEGE = 3023;

    public static boolean isSuccess(int i) {
        return i == 0;
    }

    public static String getErrorText(Context context, int i, String str) {
        return TextUtils.isEmpty(str) ? findText(context, i) : str;
    }

    public static String findText(Context context, int i) {
        if (context == null) {
            return null;
        }
        if (i == 0) {
            return ResUtils.getString(context, "error_common");
        }
        if (i == 2) {
            return ResUtils.getString(context, "error_network");
        }
        if (i == 3025) {
            return ResUtils.getString(context, "bank_system_busying");
        }
        switch (i) {
            case 5:
                return ResUtils.getString(context, "error_login_account");
            case 6:
                return ResUtils.getString(context, "error_user_cancel");
            case 7:
                return ResUtils.getString(context, "error_auth_failed");
            default:
                switch (i) {
                    case 10:
                        return ResUtils.getString(context, "error_nfc");
                    case 11:
                        return ResUtils.getString(context, "error_interrupted");
                    default:
                        switch (i) {
                            case 13:
                                return ResUtils.getString(context, "error_get_cplc");
                            case 14:
                                return ResUtils.getString(context, "error_get_account");
                            case 15:
                                return ResUtils.getString(context, "error_get_imei");
                            case 16:
                                return ResUtils.getString(context, "error_server_response");
                            default:
                                switch (i) {
                                    case 1001:
                                        return ResUtils.getString(context, "error_recharge_failed");
                                    case 1002:
                                        return ResUtils.getString(context, "card_recharge_promo_code_exhausted");
                                    default:
                                        switch (i) {
                                            case 3001:
                                                break;
                                            case 3002:
                                                return ResUtils.getString(context, "bank_card_user_account_expire");
                                            case 3003:
                                                return ResUtils.getString(context, "bank_card_user_not_reserve_phone");
                                            case OTP_INFO_VERIFY_FAILED /*3004*/:
                                                return ResUtils.getString(context, "sms_code_wrong");
                                            case OTP_INFO_EXPIRE /*3005*/:
                                                return ResUtils.getString(context, "sms_expire");
                                            default:
                                                switch (i) {
                                                    case APPLY_TIMES_EXCEED_LIMIT /*3008*/:
                                                        return ResUtils.getString(context, "apply_times_exceed_limit");
                                                    case FAILED_TIME_EXCEED_LIMIT /*3009*/:
                                                        return ResUtils.getString(context, "failed_times_exceed_limit");
                                                    case USER_ACCOUNT_NO_APPLY_PRIVILEGE /*3010*/:
                                                        return ResUtils.getString(context, "user_account_no_apply_privilege");
                                                    case 3011:
                                                        return ResUtils.getString(context, "otp_info_reverify");
                                                    case USER_ACCOUNT_NOT_EXIST /*3012*/:
                                                        return ResUtils.getString(context, "user_account_not_exist");
                                                    case BANK_CARD_NOT_SUPPORTED /*3013*/:
                                                        return ResUtils.getString(context, "bank_card_not_supported");
                                                    case USER_IDENTITY_VERIFY_FAILED /*3014*/:
                                                        return ResUtils.getString(context, "user_identity_verify_failed");
                                                    case NULL_KEY_INFOMATION /*3015*/:
                                                        break;
                                                    case USER_ACCOUNT_IN_BLANK_LIST /*3016*/:
                                                        return ResUtils.getString(context, "user_account_in_blank_list");
                                                    case FPAN_DUPLICATED_APPLY /*3017*/:
                                                        return ResUtils.getString(context, "fpan_duplicated_apply");
                                                    case TOTAL_APPLY_NUM_EXCEED_LIMIT /*3018*/:
                                                        return ResUtils.getString(context, "total_apply_num_exceed_limit");
                                                    case CARD_STATUS_ERROR /*3019*/:
                                                        return ResUtils.getString(context, "card_status_no_apply_privilege");
                                                    case CARD_TYPE_ERROR /*3020*/:
                                                        return ResUtils.getString(context, "card_type_no_apply_privilege");
                                                    case 3021:
                                                        return ResUtils.getString(context, "activate_failed");
                                                    case CONTACT_CUSTOMER_SERVICE /*3022*/:
                                                        return ResUtils.getString(context, "activate_failed_no_retry");
                                                    case USER_NO_APPLY_PRIVILEGE /*3023*/:
                                                        return ResUtils.getString(context, "user_no_apply_privilege");
                                                    default:
                                                        int i2 = i - 1000000;
                                                        if (i2 > 0) {
                                                            i = i2;
                                                        }
                                                        if (i == 10041 || Coder.sizeOfInt(i) == 4) {
                                                            return ResUtils.getString(context, "bank_error");
                                                        }
                                                        return ResUtils.getString(context, "error_common");
                                                }
                                        }
                                        return ResUtils.getString(context, "bank_card_verify_failed");
                                }
                        }
                }
        }
    }
}
