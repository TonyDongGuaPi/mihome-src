package com.mobikwik.sdk.lib.payapi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import com.mobikwik.sdk.lib.Constants;
import com.mobikwik.sdk.lib.SDKErrorCodes;
import com.mobikwik.sdk.lib.User;
import com.mobikwik.sdk.lib.payapi.PaymentAPI;
import com.mobikwik.sdk.lib.utils.Checksum;
import com.mobikwik.sdk.lib.utils.Network;
import com.mobikwik.sdk.lib.utils.ParamSanitizer;
import com.mobikwik.sdk.lib.utils.Utils;
import com.unionpay.tsmservice.data.Constant;
import java.util.Date;
import java.util.HashMap;

public class CCAvenuePaymentApi implements PaymentAPI {
    public static final String CONNECTION_ERROR = "Please check your internet connectivity and try again.";
    public static final String CONNECTION_ERROR_TITLE = "Connection Error!";
    private static final String TAG = "CCAVENUEAPI";
    /* access modifiers changed from: private */
    public Activity activity;
    /* access modifiers changed from: private */
    public String amount;
    final int balanceIndex = 1;
    String bankid;
    private String buyerAddress = "Delhi";
    private String buyerCity = "Delhi";
    private String buyerCountry = "India";
    /* access modifiers changed from: private */
    public String buyerEmail;
    private String buyerName;
    /* access modifiers changed from: private */
    public String buyerPhoneNumber;
    private String buyerPincode = "110075";
    private String buyerState = "Delhi";
    private PaymentAPI.Callback callback;
    String ccavePostUrl;
    String checksum;
    /* access modifiers changed from: private */
    public String checksumUrl;
    ProgressDialog dialog;
    int formValid = 0;
    /* access modifiers changed from: private */
    public boolean isWalletTxn;
    private boolean mIsRunning = false;
    String merchantId;
    String merchantOrderId;
    final int messageIndex = 2;
    String mkOrderId;
    /* access modifiers changed from: private */
    public String mobikwikMBKid;
    String payOption;
    String purposeDescription = "Mobikwik Mobile Recharge";
    /* access modifiers changed from: private */
    public String returnUrl;
    private String returnUrlCopy;
    String sdkMode;
    final int statusIndex = 0;
    final int versionIndex = 3;

    private class GetChecksumNew extends AsyncTask {
        private GetChecksumNew() {
        }

        private void returnFailure(String str) {
            Utils.sendResultBack(CCAvenuePaymentApi.this.activity, (Intent) null, "1", str);
        }

        /* access modifiers changed from: protected */
        public String doInBackground(String... strArr) {
            String str;
            String access$100 = CCAvenuePaymentApi.this.buyerEmail;
            String access$200 = CCAvenuePaymentApi.this.mobikwikMBKid;
            String access$300 = CCAvenuePaymentApi.this.amount;
            CCAvenuePaymentApi.this.sanitizeParams();
            HashMap hashMap = new HashMap();
            hashMap.put("amount", CCAvenuePaymentApi.this.amount);
            if (CCAvenuePaymentApi.this.isWalletTxn) {
                hashMap.put("txId", CCAvenuePaymentApi.this.mkOrderId);
                hashMap.put("codeforrecord", "AndroidSDK");
                hashMap.put("userId", CCAvenuePaymentApi.this.buyerEmail);
                hashMap.put("gatewayId", "ccavenue");
                hashMap.put("cellNumber", CCAvenuePaymentApi.this.buyerPhoneNumber);
                hashMap.put("email", access$100);
                hashMap.put(Constants.MID, access$200);
                hashMap.put("orderid", CCAvenuePaymentApi.this.merchantOrderId);
                hashMap.put("txnAmount", access$300);
                hashMap.put("isdesktoporwallet", "wallet");
                hashMap.put(Constants.LABEL_CCAV_RETURN_URL, CCAvenuePaymentApi.this.returnUrl);
                str = Constants.getServerUrl(CCAvenuePaymentApi.this.sdkMode) + "verifyMobilePaymentData.do";
            } else {
                hashMap.put("orderid", CCAvenuePaymentApi.this.merchantOrderId);
                hashMap.put(Constants.MID, CCAvenuePaymentApi.this.merchantId);
                hashMap.put("pgName", "CCavenue");
                hashMap.put("currency", Constant.KEY_CURRENCYTYPE_INR);
                hashMap.put(Constants.PG_RESPONSE_URL, CCAvenuePaymentApi.this.returnUrl);
                str = CCAvenuePaymentApi.this.checksumUrl;
            }
            return Network.getResponseOfPostRequest(str, hashMap);
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(String str) {
            if (CCAvenuePaymentApi.this.dialog != null && CCAvenuePaymentApi.this.dialog.isShowing()) {
                CCAvenuePaymentApi.this.dialog.dismiss();
            }
            if (Network.validateNetworkResponse(str)) {
                Checksum parseChecksumResponse = Checksum.parseChecksumResponse(str);
                if (parseChecksumResponse == null) {
                    returnFailure("Checksum failure");
                } else if ("SUCCESS".equals(parseChecksumResponse.getStatus())) {
                    CCAvenuePaymentApi.this.checksum = parseChecksumResponse.getChecksum();
                    CCAvenuePaymentApi.this.pay();
                } else {
                    returnFailure(parseChecksumResponse.getChecksum());
                }
            } else {
                returnFailure(SDKErrorCodes.INTERNET_NOT_WORKING.getErrorDescription());
            }
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            CCAvenuePaymentApi cCAvenuePaymentApi = CCAvenuePaymentApi.this;
            cCAvenuePaymentApi.mkOrderId = new Date().getTime() + "";
        }
    }

    public CCAvenuePaymentApi(User user, String str, String str2, String str3, String str4, String str5, boolean z, String str6, String str7, String str8, String str9, String str10) {
        this.bankid = str3;
        this.ccavePostUrl = str7;
        this.merchantId = str10;
        this.buyerEmail = user.getEmail();
        this.buyerPhoneNumber = user.getCell();
        this.amount = str;
        this.returnUrl = str8;
        this.payOption = str4;
        this.sdkMode = str5;
        this.isWalletTxn = z;
        this.bankid = str3;
        this.buyerName = this.buyerEmail.substring(0, this.buyerEmail.indexOf("@"));
        this.merchantOrderId = str2;
        this.mobikwikMBKid = str6;
        this.checksumUrl = str9;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0130  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x016f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void openURL() {
        /*
            r4 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "billing_cust_email="
            r0.append(r1)
            java.lang.String r1 = r4.buyerEmail
            r0.append(r1)
            java.lang.String r1 = "&"
            r0.append(r1)
            java.lang.String r1 = "Amount"
            r0.append(r1)
            java.lang.String r1 = "="
            r0.append(r1)
            java.lang.String r1 = r4.amount
            r0.append(r1)
            java.lang.String r1 = "&"
            r0.append(r1)
            java.lang.String r1 = "billing_cust_tel"
            r0.append(r1)
            java.lang.String r1 = "="
            r0.append(r1)
            java.lang.String r1 = r4.buyerPhoneNumber
            r0.append(r1)
            java.lang.String r1 = "&"
            r0.append(r1)
            java.lang.String r1 = "billing_cust_address"
            r0.append(r1)
            java.lang.String r1 = "="
            r0.append(r1)
            java.lang.String r1 = r4.buyerAddress
            r0.append(r1)
            java.lang.String r1 = "&"
            r0.append(r1)
            java.lang.String r1 = "billing_cust_country"
            r0.append(r1)
            java.lang.String r1 = "="
            r0.append(r1)
            java.lang.String r1 = r4.buyerCountry
            r0.append(r1)
            java.lang.String r1 = "&"
            r0.append(r1)
            java.lang.String r1 = "billing_cust_state"
            r0.append(r1)
            java.lang.String r1 = "="
            r0.append(r1)
            java.lang.String r1 = r4.buyerState
            r0.append(r1)
            java.lang.String r1 = "&"
            r0.append(r1)
            java.lang.String r1 = "billing_cust_city"
            r0.append(r1)
            java.lang.String r1 = "="
            r0.append(r1)
            java.lang.String r1 = r4.buyerCity
            r0.append(r1)
            java.lang.String r1 = "&"
            r0.append(r1)
            java.lang.String r1 = "billing_zip_code"
            r0.append(r1)
            java.lang.String r1 = "="
            r0.append(r1)
            java.lang.String r1 = r4.buyerPincode
            r0.append(r1)
            java.lang.String r1 = "&"
            r0.append(r1)
            java.lang.String r1 = "cardOption"
            r0.append(r1)
            java.lang.String r1 = "="
            r0.append(r1)
            java.lang.String r1 = r4.payOption
            r0.append(r1)
            java.lang.String r1 = "&"
            r0.append(r1)
            java.lang.String r1 = "billing_cust_notes"
            r0.append(r1)
            java.lang.String r1 = "="
            r0.append(r1)
            java.lang.String r1 = r4.purposeDescription
            r0.append(r1)
            java.lang.String r1 = "&"
            r0.append(r1)
            java.lang.String r1 = "Checksum"
            r0.append(r1)
            java.lang.String r1 = "="
            r0.append(r1)
            java.lang.String r1 = r4.checksum
            r0.append(r1)
            java.lang.String r1 = "&"
            r0.append(r1)
            java.lang.String r1 = "billing_cust_name"
            r0.append(r1)
            java.lang.String r1 = "="
            r0.append(r1)
            java.lang.String r1 = r4.buyerName
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = r4.payOption
            java.lang.String r2 = "netBanking"
            int r1 = r1.compareTo(r2)
            if (r1 != 0) goto L_0x0110
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = "&netBankingCards="
        L_0x0103:
            r1.append(r0)
            java.lang.String r0 = r4.bankid
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            goto L_0x012c
        L_0x0110:
            java.lang.String r1 = r4.payOption
            java.lang.String r2 = "NonMoto"
            int r1 = r1.compareTo(r2)
            if (r1 != 0) goto L_0x0125
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = "&NonMotoCardType="
            goto L_0x0103
        L_0x0125:
            java.lang.String r1 = "CCAVENUEAPI"
            java.lang.String r2 = "neither netbanking nor nonmoto, something is wrong!"
            android.util.Log.e(r1, r2)
        L_0x012c:
            boolean r1 = r4.isWalletTxn
            if (r1 == 0) goto L_0x016f
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = "&Order_Id="
            r1.append(r0)
            java.lang.String r0 = r4.mkOrderId
        L_0x013f:
            r1.append(r0)
            java.lang.String r0 = "&"
            r1.append(r0)
            java.lang.String r0 = "Merchant_Id"
            r1.append(r0)
            java.lang.String r0 = "="
            r1.append(r0)
            java.lang.String r0 = r4.merchantId
            r1.append(r0)
            java.lang.String r0 = "&"
            r1.append(r0)
            java.lang.String r0 = "Redirect_Url"
            r1.append(r0)
            java.lang.String r0 = "="
            r1.append(r0)
            java.lang.String r0 = r4.returnUrlCopy
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            goto L_0x017f
        L_0x016f:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = "&Order_Id="
            r1.append(r0)
            java.lang.String r0 = r4.merchantOrderId
            goto L_0x013f
        L_0x017f:
            com.mobikwik.sdk.lib.payapi.PaymentAPI$Callback r1 = r4.callback
            java.lang.String r2 = r4.ccavePostUrl
            r3 = 0
            r1.onSuccess(r2, r0, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobikwik.sdk.lib.payapi.CCAvenuePaymentApi.openURL():void");
    }

    /* access modifiers changed from: private */
    public void pay() {
        this.formValid = 0;
        openURL();
    }

    /* access modifiers changed from: private */
    public void sanitizeParams() {
        this.buyerEmail = ParamSanitizer.sanitizeParam(this.buyerEmail);
        this.amount = ParamSanitizer.sanitizeParam(this.amount);
        this.buyerPhoneNumber = ParamSanitizer.sanitizeParam(this.buyerPhoneNumber);
        this.merchantId = ParamSanitizer.sanitizeParam(this.merchantId);
        this.buyerAddress = ParamSanitizer.sanitizeParam(this.buyerAddress);
        this.buyerCountry = ParamSanitizer.sanitizeParam(this.buyerCountry);
        this.buyerState = ParamSanitizer.sanitizeParam(this.buyerState);
        this.buyerCity = ParamSanitizer.sanitizeParam(this.buyerCity);
        this.mkOrderId = ParamSanitizer.sanitizeParam(this.mkOrderId);
        this.buyerPincode = ParamSanitizer.sanitizeParam(this.buyerPincode);
        this.payOption = ParamSanitizer.sanitizeParam(this.payOption);
        this.purposeDescription = ParamSanitizer.sanitizeParam(this.purposeDescription);
        this.buyerName = ParamSanitizer.sanitizeParam(this.buyerName);
        this.returnUrlCopy = ParamSanitizer.SanitizeURLParam(this.returnUrl);
    }

    public void startAPI(PaymentAPI.Callback callback2, Activity activity2) {
        this.callback = callback2;
        this.activity = activity2;
        if (Network.isConnected(activity2)) {
            new GetChecksumNew().execute(new String[0]);
            this.dialog = ProgressDialog.show(activity2, "", "Processing your payment request...", true);
            this.dialog.show();
            return;
        }
        callback2.onError(SDKErrorCodes.INTERNET_NOT_WORKING.getErrorCode(), SDKErrorCodes.INTERNET_NOT_WORKING.getErrorDescription());
    }

    public void updateUser(User user) {
        if (user.getEmail() != null) {
            this.buyerEmail = user.getEmail();
        }
        if (user.getCell() != null) {
            this.buyerPhoneNumber = user.getCell();
        }
    }
}
