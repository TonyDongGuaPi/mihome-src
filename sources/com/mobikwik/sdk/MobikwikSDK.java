package com.mobikwik.sdk;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import com.mobikwik.sdk.lib.Constants;
import com.mobikwik.sdk.lib.DirectPay;
import com.mobikwik.sdk.lib.MKTransactionResponse;
import com.mobikwik.sdk.lib.SDKErrorCodes;
import com.mobikwik.sdk.lib.Transaction;
import com.mobikwik.sdk.lib.model.SavedCardResponse;
import com.mobikwik.sdk.lib.payapi.PaymentAPI;
import com.mobikwik.sdk.lib.utils.Utils;
import com.mobikwik.sdk.lib.wallet.WalletAPIs;
import com.mobikwik.sdk.lib.wallet.WalletResponseCallback;
import com.mobikwik.sdk.ui.data.b;
import java.util.ArrayList;

public class MobikwikSDK extends Activity implements DirectPay.CallBack {
    public static final String EXTRA_TRANSACTION = "MK_EXT_TXN";
    public static final String EXTRA_TRANSACTION_CONFIG = "MK_EXT_TXN_CONFIG";
    public static final String EXTRA_TRANSACTION_RESPONSE = "MK_EXT_TXN_RESP";
    public static final int RESULT_CODE_TRANSACTION_RESPONSE = 1;

    /* renamed from: a  reason: collision with root package name */
    private DirectPay f8354a;
    /* access modifiers changed from: private */
    public ProgressDialog b;

    /* access modifiers changed from: private */
    public void a(ArrayList arrayList, SavedCardResponse.CardDetails[] cardDetailsArr) {
        b b2 = b.b((Context) this);
        b2.a(arrayList);
        b2.b(cardDetailsArr);
        startActivityForResult(new Intent(this, PaymentOptions.class), 0);
    }

    /* access modifiers changed from: package-private */
    public void a(Intent intent, String str, String str2) {
        Intent intent2 = new Intent();
        if (intent != null) {
            intent2 = new Intent(intent);
        }
        if (Utils.isNull(intent2.getStringExtra(Constants.STATUS_CODE))) {
            if (Utils.isNull(str)) {
                intent2.putExtra(Constants.STATUS_CODE, "1");
            } else {
                intent2.putExtra(Constants.STATUS_CODE, str);
            }
        }
        if (Utils.isNull(intent2.getStringExtra(Constants.STATUS_MSG))) {
            if (Utils.isNull(str)) {
                intent2.putExtra(Constants.STATUS_MSG, "Something went bad");
            } else {
                intent2.putExtra(Constants.STATUS_MSG, str2);
            }
        }
        b b2 = b.b((Context) this);
        Transaction d = b2.d();
        MKTransactionResponse mKTransactionResponse = new MKTransactionResponse();
        if (d != null) {
            mKTransactionResponse.amount = d.getAmount();
            mKTransactionResponse.orderId = d.getOrderId();
        }
        mKTransactionResponse.statusCode = intent2.getStringExtra(Constants.STATUS_CODE);
        mKTransactionResponse.statusMessage = intent2.getStringExtra(Constants.STATUS_MSG);
        if (b2.j() && SDKErrorCodes.USER_CANCELLED_TRANSACTION.getErrorCode().equals(mKTransactionResponse.statusCode)) {
            WalletAPIs.getInstance("1".equals(b2.f().getMode()), this).cancelTxn(d.getUser().getEmail(), d.getUser().getCell(), d.getOrderId(), d.getAmount(), b2.f().getMbkId(), b2.i(), b2.f().getPgResponseUrl(), b2.f().getMerchantName(), (WalletResponseCallback) null);
        }
        Intent intent3 = new Intent();
        intent3.putExtra(EXTRA_TRANSACTION_RESPONSE, mKTransactionResponse);
        setResult(1, intent3);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 0 && i2 == 1) {
            a(intent, (String) null, (String) null);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0081, code lost:
        if ((r2.getAmount().length() - r1) > 3) goto L_0x0083;
     */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x008f  */
    @android.annotation.SuppressLint({"InlinedApi"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r13) {
        /*
            r12 = this;
            super.onCreate(r13)
            if (r13 != 0) goto L_0x00d1
            android.content.Intent r13 = r12.getIntent()
            java.lang.String r0 = "MK_EXT_TXN"
            java.io.Serializable r13 = r13.getSerializableExtra(r0)
            r2 = r13
            com.mobikwik.sdk.lib.Transaction r2 = (com.mobikwik.sdk.lib.Transaction) r2
            r13 = 0
            if (r2 != 0) goto L_0x001d
            java.lang.String r0 = "1"
            java.lang.String r1 = "Invalid Request"
        L_0x0019:
            r12.a((android.content.Intent) r13, (java.lang.String) r0, (java.lang.String) r1)
            return
        L_0x001d:
            boolean r0 = com.mobikwik.sdk.lib.utils.Network.isConnected(r12)
            if (r0 != 0) goto L_0x0030
            com.mobikwik.sdk.lib.SDKErrorCodes r0 = com.mobikwik.sdk.lib.SDKErrorCodes.INTERNET_NOT_WORKING
            java.lang.String r0 = r0.getErrorCode()
            com.mobikwik.sdk.lib.SDKErrorCodes r1 = com.mobikwik.sdk.lib.SDKErrorCodes.INTERNET_NOT_WORKING
        L_0x002b:
            java.lang.String r1 = r1.getErrorDescription()
            goto L_0x0019
        L_0x0030:
            android.content.Intent r0 = r12.getIntent()
            java.lang.String r1 = "MK_EXT_TXN_CONFIG"
            java.io.Serializable r0 = r0.getSerializableExtra(r1)
            r5 = r0
            com.mobikwik.sdk.lib.TransactionConfiguration r5 = (com.mobikwik.sdk.lib.TransactionConfiguration) r5
            com.mobikwik.sdk.ui.data.b r4 = com.mobikwik.sdk.ui.data.b.a((android.content.Context) r12)
            if (r5 == 0) goto L_0x00c7
            boolean r0 = r5.isConfigValid()
            if (r0 != 0) goto L_0x004b
            goto L_0x00c7
        L_0x004b:
            r4.a((com.mobikwik.sdk.lib.TransactionConfiguration) r5)
            r4.a((com.mobikwik.sdk.lib.Transaction) r2)
            com.mobikwik.sdk.lib.payinstrument.PaymentInstrument r0 = r2.getPayInstrument()
            if (r0 != 0) goto L_0x00ba
            r6 = 0
            r7 = 1
            java.lang.String r0 = r2.getAmount()     // Catch:{ Exception -> 0x0083 }
            double r0 = java.lang.Double.parseDouble(r0)     // Catch:{ Exception -> 0x0083 }
            r8 = 0
            int r3 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r3 > 0) goto L_0x0069
            r0 = 1
            goto L_0x006a
        L_0x0069:
            r0 = 0
        L_0x006a:
            java.lang.String r1 = r2.getAmount()     // Catch:{ Exception -> 0x0083 }
            java.lang.String r3 = "."
            int r1 = r1.indexOf(r3)     // Catch:{ Exception -> 0x0083 }
            r3 = -1
            if (r1 == r3) goto L_0x0084
            java.lang.String r3 = r2.getAmount()     // Catch:{ Exception -> 0x0083 }
            int r3 = r3.length()     // Catch:{ Exception -> 0x0083 }
            int r3 = r3 - r1
            r1 = 3
            if (r3 <= r1) goto L_0x0084
        L_0x0083:
            r0 = 1
        L_0x0084:
            if (r0 != r7) goto L_0x008f
            com.mobikwik.sdk.lib.SDKErrorCodes r0 = com.mobikwik.sdk.lib.SDKErrorCodes.INVALID_AMOUNT
            java.lang.String r0 = r0.getErrorCode()
            com.mobikwik.sdk.lib.SDKErrorCodes r1 = com.mobikwik.sdk.lib.SDKErrorCodes.INVALID_AMOUNT
            goto L_0x002b
        L_0x008f:
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            com.mobikwik.sdk.lib.tasks.LoadPaymentsMapping r8 = new com.mobikwik.sdk.lib.tasks.LoadPaymentsMapping
            java.lang.String r9 = r5.getMbkId()
            java.lang.String r0 = r5.getMode()
            int r10 = java.lang.Integer.parseInt(r0)
            com.mobikwik.sdk.a r11 = new com.mobikwik.sdk.a
            r0 = r11
            r1 = r12
            r0.<init>(r1, r2, r3, r4, r5)
            r8.<init>(r12, r9, r10, r11)
            java.lang.String r0 = "Processing..."
            android.app.ProgressDialog r13 = android.app.ProgressDialog.show(r12, r13, r0, r7)
            r12.b = r13
            java.lang.Void[] r13 = new java.lang.Void[r6]
            r8.execute(r13)
            goto L_0x00d1
        L_0x00ba:
            com.mobikwik.sdk.lib.DirectPay r13 = new com.mobikwik.sdk.lib.DirectPay
            r13.<init>(r12, r2, r5, r12)
            r12.f8354a = r13
            com.mobikwik.sdk.lib.DirectPay r13 = r12.f8354a
            r13.start()
            goto L_0x00d1
        L_0x00c7:
            com.mobikwik.sdk.lib.SDKErrorCodes r0 = com.mobikwik.sdk.lib.SDKErrorCodes.FAILURE
            java.lang.String r0 = r0.getErrorCode()
            java.lang.String r1 = "Invalid Configuration"
            goto L_0x0019
        L_0x00d1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobikwik.sdk.MobikwikSDK.onCreate(android.os.Bundle):void");
    }

    public void onFailure(String str, String str2) {
        a((Intent) null, str, str2);
    }

    public void onSuccess(PaymentAPI paymentAPI) {
        b b2 = b.b((Context) this);
        Transaction d = b2.d();
        Intent intent = new Intent();
        intent.setClass(this, PGWebView.class);
        intent.putExtra("KEY_API", paymentAPI);
        intent.putExtra("KEY_IS_WALLET", false);
        intent.putExtra("KEY_PG_AMOUNT", d.getAmount());
        intent.putExtra("KEY_RESPONSE_URL", b2.f().getPgResponseUrl());
        startActivityForResult(intent, 0);
    }
}
