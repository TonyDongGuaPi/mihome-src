package com.unionpay.tsmservice.mi;

import android.content.Context;
import com.unionpay.tsmservice.mi.request.AcquireSEAppListRequestParams;
import com.unionpay.tsmservice.mi.request.AddCardToVendorPayRequestParams;
import com.unionpay.tsmservice.mi.request.CardListStatusChangedRequestParams;
import com.unionpay.tsmservice.mi.request.EncryptDataRequestParams;
import com.unionpay.tsmservice.mi.request.GetEncryptDataRequestParams;
import com.unionpay.tsmservice.mi.request.GetMessageDetailsRequestParams;
import com.unionpay.tsmservice.mi.request.GetSeIdRequestParams;
import com.unionpay.tsmservice.mi.request.GetTransactionDetailsRequestParams;
import com.unionpay.tsmservice.mi.request.GetVendorPayStatusRequestParams;
import com.unionpay.tsmservice.mi.request.InitRequestParams;
import com.unionpay.tsmservice.mi.request.OnlinePaymentVerifyRequestParams;
import com.unionpay.tsmservice.mi.request.PayResultNotifyRequestParams;
import com.unionpay.tsmservice.mi.request.PinRequestRequestParams;
import com.unionpay.tsmservice.mi.request.QueryVendorPayStatusRequestParams;
import com.unionpay.tsmservice.mi.request.RequestParams;
import com.unionpay.tsmservice.mi.request.SafetyKeyboardRequestParams;
import com.unionpay.tsmservice.mi.utils.IUPJniInterface;

public class SessionKeyReExchange {

    /* renamed from: a  reason: collision with root package name */
    private UPTsmAddon f9835a;
    private int b;
    private RequestParams c;
    private ITsmCallback d;
    private ITsmProgressCallback e;
    private int f;
    private OnSafetyKeyboardCallback g;
    private Context h;
    private int i;

    public SessionKeyReExchange(UPTsmAddon uPTsmAddon, int i2, ITsmCallback iTsmCallback) {
        this(uPTsmAddon, i2, (RequestParams) null, iTsmCallback);
    }

    public SessionKeyReExchange(UPTsmAddon uPTsmAddon, int i2, RequestParams requestParams, ITsmCallback iTsmCallback) {
        this(uPTsmAddon, i2, requestParams, iTsmCallback, 1000);
    }

    public SessionKeyReExchange(UPTsmAddon uPTsmAddon, int i2, RequestParams requestParams, ITsmCallback iTsmCallback, int i3) {
        this.b = -1;
        this.f = 1000;
        this.f9835a = uPTsmAddon;
        this.b = i2;
        this.c = requestParams;
        this.d = iTsmCallback;
        this.f = i3;
    }

    public SessionKeyReExchange(UPTsmAddon uPTsmAddon, int i2, RequestParams requestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) {
        this(uPTsmAddon, i2, requestParams, iTsmCallback, iTsmProgressCallback, 1000);
    }

    public SessionKeyReExchange(UPTsmAddon uPTsmAddon, int i2, RequestParams requestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback, int i3) {
        this.b = -1;
        this.f = 1000;
        this.f9835a = uPTsmAddon;
        this.b = i2;
        this.c = requestParams;
        this.d = iTsmCallback;
        this.e = iTsmProgressCallback;
        this.f = i3;
    }

    public SessionKeyReExchange(UPTsmAddon uPTsmAddon, int i2, SafetyKeyboardRequestParams safetyKeyboardRequestParams, int i3, OnSafetyKeyboardCallback onSafetyKeyboardCallback, Context context) {
        this.b = -1;
        this.f = 1000;
        this.f9835a = uPTsmAddon;
        this.b = i2;
        this.i = i3;
        this.c = safetyKeyboardRequestParams;
        this.g = onSafetyKeyboardCallback;
        this.h = context;
    }

    public int reExchangeKey() {
        String[] strArr = new String[1];
        int pubKey = this.f9835a.getPubKey(1000, strArr);
        if (pubKey != 0) {
            return pubKey;
        }
        int exchangeKey = this.f9835a.exchangeKey(IUPJniInterface.rER(strArr[0], IUPJniInterface.mSK()), strArr);
        if (exchangeKey != 0) {
            return exchangeKey;
        }
        String dMG = IUPJniInterface.dMG(strArr[0]);
        IUPJniInterface.sSK(dMG);
        Context context = this.f9835a.getContext();
        if (context != null) {
            IUPJniInterface.uSKT(context.getPackageName(), dMG);
        }
        int i2 = this.b;
        if (i2 != 1000) {
            switch (i2) {
                case 0:
                    return this.f9835a.init((InitRequestParams) this.c, this.d);
                case 1:
                    return this.f9835a.encryptData((EncryptDataRequestParams) this.c, this.d);
                case 2:
                    return this.f9835a.getEncryptData((GetEncryptDataRequestParams) this.c, this.d);
                case 3:
                    return this.f9835a.setSafetyKeyboardBitmap((SafetyKeyboardRequestParams) this.c);
                case 4:
                    return this.f9835a.clearEncryptData(this.i);
                case 5:
                    return this.f9835a.hideKeyboard();
                case 6:
                    return this.f9835a.acquireSEAppList((AcquireSEAppListRequestParams) this.c, this.d);
                case 7:
                    return this.f9835a.cardListStatusChanged((CardListStatusChangedRequestParams) this.c, this.d);
                case 8:
                    return this.f9835a.queryVendorPayStatus((QueryVendorPayStatusRequestParams) this.c, this.d);
                case 9:
                    return this.f9835a.getVendorPayStatus((GetVendorPayStatusRequestParams) this.c, this.d);
                case 10:
                    return this.f9835a.onlinePaymentVerify((OnlinePaymentVerifyRequestParams) this.c, this.d);
                case 11:
                    return this.f9835a.pinRequest((PinRequestRequestParams) this.c, this.d);
                case 12:
                    return this.f9835a.payResultNotify((PayResultNotifyRequestParams) this.c, this.d);
                case 13:
                    return this.f9835a.cancelPay();
                case 14:
                    return this.f9835a.getVendorPayStatusForBankApp((GetVendorPayStatusRequestParams) this.c, this.d);
                case 15:
                    return this.f9835a.getSeId((GetSeIdRequestParams) this.c, this.d);
                case 16:
                    return this.f9835a.addCardToVendorPay((AddCardToVendorPayRequestParams) this.c, this.d, this.e);
                case 17:
                    return this.f9835a.getTransactionDetails((GetTransactionDetailsRequestParams) this.c, this.d);
                case 18:
                    return this.f9835a.getMessageDetails((GetMessageDetailsRequestParams) this.c, this.d);
                default:
                    return 0;
            }
        } else {
            return this.f9835a.showSafetyKeyboard((SafetyKeyboardRequestParams) this.c, this.i, this.g, this.h);
        }
    }
}
