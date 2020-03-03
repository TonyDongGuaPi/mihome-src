package com.mobikwik.sdk.lib.payapi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import com.alipay.sdk.sys.a;
import com.google.gson.Gson;
import com.mobikwik.sdk.lib.Constants;
import com.mobikwik.sdk.lib.SDKErrorCodes;
import com.mobikwik.sdk.lib.User;
import com.mobikwik.sdk.lib.model.MBKInitPaymentReq;
import com.mobikwik.sdk.lib.model.MbkInitPaymentResponse;
import com.mobikwik.sdk.lib.model.PaymentsMappingAPIResponse;
import com.mobikwik.sdk.lib.payapi.PaymentAPI;
import com.mobikwik.sdk.lib.payinstrument.Bank;
import com.mobikwik.sdk.lib.payinstrument.Card;
import com.mobikwik.sdk.lib.utils.Network;
import com.mobikwik.sdk.lib.utils.RSAEncUtils;
import com.mobikwik.sdk.lib.utils.Utils;
import com.unionpay.tsmservice.data.Constant;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class MBKPaymentAPI implements PaymentAPI, Serializable {
    public static final String CONNECTION_ERROR = "Please check your internet connectivity and try again.";
    public static final String CONNECTION_ERROR_TITLE = "Connection Error!";
    private static final String TAG = "MBKPaymentAPI";
    Activity activity;
    /* access modifiers changed from: private */
    public String addAmount;
    String bankid;
    /* access modifiers changed from: private */
    public String buyerEmail;
    private String buyerName;
    /* access modifiers changed from: private */
    public String buyerPhoneNumber;
    /* access modifiers changed from: private */
    public PaymentAPI.Callback callback;
    Card card;
    String currency;
    ProgressDialog dialog;
    /* access modifiers changed from: private */
    public boolean isToken;
    /* access modifiers changed from: private */
    public PaymentsMappingAPIResponse.PaymentsMapping.MbkPublicKey mbkKey;
    /* access modifiers changed from: private */
    public String merchantName;
    String merchantOrderId;
    String mkOrderId;
    /* access modifiers changed from: private */
    public String mobikwikMBKid;
    String sdkMode;
    /* access modifiers changed from: private */
    public String tokenOrOtp;
    /* access modifiers changed from: private */
    public String txnAmount;
    /* access modifiers changed from: private */
    public String txnHash;

    private class GetChecksumNew extends AsyncTask {
        private SDKErrorCodes code;
        private String response;

        private GetChecksumNew() {
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(String... strArr) {
            String str;
            RSAEncUtils rSAEncUtils = new RSAEncUtils(MBKPaymentAPI.this.mbkKey);
            String str2 = Constants.getServerUrl(MBKPaymentAPI.this.sdkMode) + "/p/pg/v1/sdk/initiate";
            MBKInitPaymentReq mBKInitPaymentReq = new MBKInitPaymentReq();
            if (MBKPaymentAPI.this.card != null) {
                mBKInitPaymentReq.cardBanking = MBKPaymentAPI.this.card.encrypt(rSAEncUtils);
                str = "CC";
            } else if (MBKPaymentAPI.this.bankid != null) {
                mBKInitPaymentReq.netBanking = new Bank(MBKPaymentAPI.this.bankid, "");
                str = "NB";
            } else {
                this.code = SDKErrorCodes.UNEXPECTED_ERROR;
                return null;
            }
            mBKInitPaymentReq.payMode = str;
            mBKInitPaymentReq.amount = MBKPaymentAPI.this.addAmount;
            mBKInitPaymentReq.deviceId = "jaglfiugl345ie4676urg89l0ile5rrgkjbsrgu";
            mBKInitPaymentReq.deviceName = Build.MANUFACTURER + " " + Build.MODEL;
            mBKInitPaymentReq.plateform = "ANDROID";
            mBKInitPaymentReq.memberId = MBKPaymentAPI.this.buyerEmail;
            mBKInitPaymentReq.mobileNumber = MBKPaymentAPI.this.buyerPhoneNumber;
            mBKInitPaymentReq.mid = MBKPaymentAPI.this.mobikwikMBKid;
            mBKInitPaymentReq.merchantName = MBKPaymentAPI.this.merchantName;
            if (MBKPaymentAPI.this.isToken) {
                mBKInitPaymentReq.token = MBKPaymentAPI.this.tokenOrOtp;
            } else {
                mBKInitPaymentReq.otp = MBKPaymentAPI.this.tokenOrOtp;
            }
            mBKInitPaymentReq.txnId = MBKPaymentAPI.this.merchantOrderId;
            mBKInitPaymentReq.txnAmount = MBKPaymentAPI.this.txnAmount;
            mBKInitPaymentReq.txnHash = MBKPaymentAPI.this.txnHash;
            this.response = Network.getResponseOfPostRequest(str2, new Gson().toJson((Object) mBKInitPaymentReq), "application/json", (String) null);
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void voidR) {
            PaymentAPI.Callback access$1100;
            String errorCode;
            SDKErrorCodes sDKErrorCodes;
            if (MBKPaymentAPI.this.dialog != null && MBKPaymentAPI.this.dialog.isShowing()) {
                MBKPaymentAPI.this.dialog.dismiss();
            }
            if (this.code != null) {
                access$1100 = MBKPaymentAPI.this.callback;
                errorCode = this.code.getErrorCode();
                sDKErrorCodes = this.code;
            } else if (!Network.validateNetworkResponse(MBKPaymentAPI.this.activity, this.response, true)) {
                access$1100 = MBKPaymentAPI.this.callback;
                errorCode = SDKErrorCodes.INTERNET_NOT_WORKING.getErrorCode();
                sDKErrorCodes = SDKErrorCodes.INTERNET_NOT_WORKING;
            } else {
                MbkInitPaymentResponse mbkInitPaymentResponse = (MbkInitPaymentResponse) new Gson().fromJson(this.response, MbkInitPaymentResponse.class);
                if (mbkInitPaymentResponse.getSuccess().booleanValue()) {
                    MBKPaymentAPI.this.processSuccessResponse(mbkInitPaymentResponse);
                    return;
                } else {
                    MBKPaymentAPI.this.callback.onError(mbkInitPaymentResponse.getMessage().getStatusCode(), mbkInitPaymentResponse.getMessage().getText());
                    return;
                }
            }
            access$1100.onError(errorCode, sDKErrorCodes.getErrorDescription());
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            MBKPaymentAPI.this.dialog = ProgressDialog.show(MBKPaymentAPI.this.activity, "", "Processing your payment request...", true);
            MBKPaymentAPI mBKPaymentAPI = MBKPaymentAPI.this;
            mBKPaymentAPI.mkOrderId = new Date().getTime() + "";
        }
    }

    private MBKPaymentAPI(User user, String str, String str2, String str3, String str4, String str5, boolean z, String str6, String str7, String str8) {
        this.currency = Constant.KEY_CURRENCYTYPE_INR;
        this.buyerEmail = user.getEmail();
        this.buyerPhoneNumber = user.getCell();
        this.addAmount = str;
        this.sdkMode = str3;
        this.merchantOrderId = str2;
        this.mobikwikMBKid = str4;
        this.txnAmount = str5;
        this.isToken = z;
        this.tokenOrOtp = str6;
        this.merchantName = str7;
        this.txnHash = str8;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MBKPaymentAPI(Card card2, PaymentsMappingAPIResponse.PaymentsMapping.MbkPublicKey mbkPublicKey, User user, String str, String str2, String str3, String str4, String str5, boolean z, String str6, String str7, String str8) {
        this(user, str, str2, str3, str4, str5, z, str6, str7, str8);
        this.card = card2;
        this.mbkKey = mbkPublicKey;
    }

    public MBKPaymentAPI(String str, User user, String str2, String str3, String str4, String str5, String str6, boolean z, String str7, String str8, String str9) {
        this(user, str2, str3, str4, str5, str6, z, str7, str8, str9);
        this.bankid = str;
    }

    /* access modifiers changed from: private */
    public void processSuccessResponse(MbkInitPaymentResponse mbkInitPaymentResponse) {
        if (mbkInitPaymentResponse.getData().getRedirect().booleanValue()) {
            String str = "";
            for (Map.Entry entry : mbkInitPaymentResponse.getData().getParameters().entrySet()) {
                str = str + ((String) entry.getKey()) + "=" + Utils.utf8Encode((String) entry.getValue()) + a.b;
            }
            this.callback.onSuccess(mbkInitPaymentResponse.getData().getPostingUrl(), str, mbkInitPaymentResponse.getData().getHeaders());
            return;
        }
        this.callback.onPaymentCompleted((String) mbkInitPaymentResponse.getData().getParameters().get("Balance"));
    }

    public void setCurrency(String str) {
        if (!Utils.isNull(str)) {
            this.currency = str;
        }
    }

    public void startAPI(PaymentAPI.Callback callback2, Activity activity2) {
        this.activity = activity2;
        this.callback = callback2;
        if (Network.isConnected(activity2)) {
            new GetChecksumNew().execute(new String[0]);
        } else {
            callback2.onError(SDKErrorCodes.INTERNET_NOT_WORKING.getErrorCode(), SDKErrorCodes.INTERNET_NOT_WORKING.getErrorDescription());
        }
    }
}
