package com.mobikwik.sdk.lib;

import com.unionpay.tsmservice.data.Constant;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.smarthome.framework.api.UserConfig;

public enum SDKErrorCodes {
    SUCCESS("0", "Success"),
    FAILURE("1", "Transaction Failed"),
    USER_BLOCKED(UserConfig.g, "User blocked"),
    MERCHANT_BLOCKED("21", "Merchant Blocked"),
    MERCHANT_DOES_REGISTERED("22", "Merchant Not Registered"),
    MERCHANT_INACTIVE("23", "Merchant Inactive"),
    WALLET_TOPUP_FAILED("30", "Wallet Topup Failed"),
    WALLET_INSUFFICIENT_BALANCE("33", "Insufficent Balance"),
    DUPLICATE_ORDERID("50", "Duplicate order id"),
    UNEXPECTED_ERROR("99", "Unexcepted error"),
    INTERNET_NOT_WORKING("98", "Unable to connect Server"),
    INVALID_EMAIL("53", "Email id Invalid"),
    INVALID_AMOUNT("54", "Amount Invalid"),
    CHECKSUM_MISMATCH("80", "Checksum mismatch"),
    USER_DETAILS_REQUIRED("181", "Either Email or Mobile is required"),
    USER_NOT_REGISTERED_ON_CELL("159", "No Wallet Account is associated with specified cell"),
    USER_NOT_REGISTERED("120", "User does not exist"),
    INVALID_CELL("156", "Cell Invalid"),
    KYC_REACHED(Constants.Plugin.PLUGINID_WEBVIEW, "Your KYC limit has been reached"),
    KYC_NOT_ALLOWED("74", "KYC Transactions is not allowed"),
    INVALID_OTP("155", "Invalid OTP"),
    AUTHENTICATION_FAILURE(Constant.TRANS_TYPE_LOAD, "Authentication Failed"),
    INVALID_TOKEN("198", "Invalid Token"),
    USER_CANCELLED_TRANSACTION("43", "User cancelled Transaction");
    
    private String errorCode;
    private String errorDescription;

    private SDKErrorCodes(String str, String str2) {
        this.errorCode = str;
        this.errorDescription = str2;
    }

    public static SDKErrorCodes getErrorCode(String str) {
        SDKErrorCodes[] values = values();
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(str)) {
                return values[i];
            }
        }
        return null;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorDescription() {
        return this.errorDescription;
    }

    public void updateErrorDescription(String str) {
        this.errorDescription = str;
    }
}
