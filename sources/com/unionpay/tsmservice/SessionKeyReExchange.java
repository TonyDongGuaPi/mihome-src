package com.unionpay.tsmservice;

import android.content.Context;
import android.os.RemoteException;
import com.unionpay.tsmservice.request.AcquireSEAppListRequestParams;
import com.unionpay.tsmservice.request.ActivateVendorPayRequestParams;
import com.unionpay.tsmservice.request.AddCardToVendorPayRequestParams;
import com.unionpay.tsmservice.request.AppDataUpdateRequestParams;
import com.unionpay.tsmservice.request.AppDeleteRequestParams;
import com.unionpay.tsmservice.request.AppDownloadApplyRequestParams;
import com.unionpay.tsmservice.request.AppDownloadRequestParams;
import com.unionpay.tsmservice.request.AppLockRequestParams;
import com.unionpay.tsmservice.request.AppUnlockRequestParams;
import com.unionpay.tsmservice.request.CardListStatusChangedRequestParams;
import com.unionpay.tsmservice.request.CheckSSamsungPayRequestParams;
import com.unionpay.tsmservice.request.CloseChannelRequestParams;
import com.unionpay.tsmservice.request.ECashTopUpRequestParams;
import com.unionpay.tsmservice.request.EncryptDataRequestParams;
import com.unionpay.tsmservice.request.ExecuteCmdRequestParams;
import com.unionpay.tsmservice.request.GetAccountBalanceRequestParams;
import com.unionpay.tsmservice.request.GetAccountInfoRequestParams;
import com.unionpay.tsmservice.request.GetAppDetailRequestParams;
import com.unionpay.tsmservice.request.GetAppListRequestParams;
import com.unionpay.tsmservice.request.GetAppStatusRequestParams;
import com.unionpay.tsmservice.request.GetAssociatedAppRequestParams;
import com.unionpay.tsmservice.request.GetCardInfoBySpayRequestParams;
import com.unionpay.tsmservice.request.GetCardInfoRequestParams;
import com.unionpay.tsmservice.request.GetDefaultCardRequestParams;
import com.unionpay.tsmservice.request.GetEncryptDataRequestParams;
import com.unionpay.tsmservice.request.GetMessageDetailsRequestParams;
import com.unionpay.tsmservice.request.GetSMSAuthCodeRequestParams;
import com.unionpay.tsmservice.request.GetSeAppListRequestParams;
import com.unionpay.tsmservice.request.GetSeIdRequestParams;
import com.unionpay.tsmservice.request.GetTransElementsRequestParams;
import com.unionpay.tsmservice.request.GetTransRecordRequestParams;
import com.unionpay.tsmservice.request.GetTransactionDetailsRequestParams;
import com.unionpay.tsmservice.request.GetVendorPayStatusRequestParams;
import com.unionpay.tsmservice.request.HideAppApplyRequestParams;
import com.unionpay.tsmservice.request.InitRequestParams;
import com.unionpay.tsmservice.request.OnlinePaymentVerifyRequestParams;
import com.unionpay.tsmservice.request.OpenChannelRequestParams;
import com.unionpay.tsmservice.request.PreDownloadRequestParams;
import com.unionpay.tsmservice.request.QueryVendorPayStatusRequestParams;
import com.unionpay.tsmservice.request.RequestParams;
import com.unionpay.tsmservice.request.SafetyKeyboardRequestParams;
import com.unionpay.tsmservice.request.SendApduRequestParams;
import com.unionpay.tsmservice.request.SendCustomDataRequestParams;
import com.unionpay.tsmservice.request.SetDefaultCardRequestParams;
import com.unionpay.tsmservice.request.SetSamsungDefWalletRequestParams;
import com.unionpay.tsmservice.request.UniteRequestParams;
import com.unionpay.tsmservice.utils.IUPJniInterface;

public class SessionKeyReExchange {

    /* renamed from: a  reason: collision with root package name */
    private UPTsmAddon f9822a;
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
        this(uPTsmAddon, i2, requestParams, iTsmCallback, (ITsmProgressCallback) null);
    }

    public SessionKeyReExchange(UPTsmAddon uPTsmAddon, int i2, RequestParams requestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) {
        this(uPTsmAddon, i2, requestParams, iTsmCallback, iTsmProgressCallback, 1000);
    }

    public SessionKeyReExchange(UPTsmAddon uPTsmAddon, int i2, RequestParams requestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback, int i3) {
        this.b = -1;
        this.f = 1000;
        this.f9822a = uPTsmAddon;
        this.b = i2;
        this.c = requestParams;
        this.d = iTsmCallback;
        this.e = iTsmProgressCallback;
        this.f = i3;
    }

    public SessionKeyReExchange(UPTsmAddon uPTsmAddon, int i2, SafetyKeyboardRequestParams safetyKeyboardRequestParams, int i3, OnSafetyKeyboardCallback onSafetyKeyboardCallback, Context context) {
        this.b = -1;
        this.f = 1000;
        this.f9822a = uPTsmAddon;
        this.b = i2;
        this.i = i3;
        this.c = safetyKeyboardRequestParams;
        this.g = onSafetyKeyboardCallback;
        this.h = context;
    }

    public int reExchangeKey() throws RemoteException {
        String[] strArr = new String[1];
        int pubKey = this.f9822a.getPubKey(1000, strArr);
        if (pubKey != 0) {
            return pubKey;
        }
        int exchangeKey = this.f9822a.exchangeKey(IUPJniInterface.rER(strArr[0], IUPJniInterface.mSK()), strArr);
        if (exchangeKey != 0) {
            return exchangeKey;
        }
        String dMG = IUPJniInterface.dMG(strArr[0], this.f9822a.getCryptType());
        IUPJniInterface.sSK(dMG);
        Context context = this.f9822a.getContext();
        if (context != null) {
            IUPJniInterface.uSKT(context.getPackageName(), dMG);
        }
        int i2 = this.b;
        if (i2 != 1000) {
            switch (i2) {
                case 0:
                    return this.f9822a.init((InitRequestParams) this.c, this.d);
                case 1:
                    return this.f9822a.getAssociatedApp((GetAssociatedAppRequestParams) this.c, this.d);
                case 2:
                    return this.f9822a.getAppList((GetAppListRequestParams) this.c, this.d);
                case 3:
                    return this.f9822a.getSEAppList((GetSeAppListRequestParams) this.c, this.d);
                case 4:
                    return this.f9822a.getAppDetail((GetAppDetailRequestParams) this.c, this.d);
                case 5:
                    return this.f9822a.getAppStatus((GetAppStatusRequestParams) this.c, this.d);
                case 6:
                    return this.f9822a.getCardInfo((GetCardInfoRequestParams) this.c, this.d);
                case 7:
                    return this.f9822a.getAccountInfo((GetAccountInfoRequestParams) this.c, this.d);
                case 8:
                    return this.f9822a.getAccountBalance((GetAccountBalanceRequestParams) this.c, this.d);
                case 9:
                    return this.f9822a.getTransElements((GetTransElementsRequestParams) this.c, this.d);
                case 10:
                    return this.f9822a.getTransRecord((GetTransRecordRequestParams) this.c, this.d);
                case 11:
                    return this.f9822a.getSMSAuthCode((GetSMSAuthCodeRequestParams) this.c, this.d);
                case 12:
                    return this.f9822a.getSeId((GetSeIdRequestParams) this.c, this.d);
                case 13:
                    return this.f9822a.getDefaultCard((GetDefaultCardRequestParams) this.c, this.d);
                case 14:
                    return this.f9822a.setDefaultCard((SetDefaultCardRequestParams) this.c, this.d);
                case 15:
                    return this.f9822a.appDownloadApply((AppDownloadApplyRequestParams) this.c, this.d);
                case 16:
                    return this.f9822a.appDownload((AppDownloadRequestParams) this.c, this.d, this.e);
                case 17:
                    return this.f9822a.appDelete((AppDeleteRequestParams) this.c, this.d, this.e);
                case 18:
                    return this.f9822a.appDataUpdate((AppDataUpdateRequestParams) this.c, this.d, this.e);
                case 19:
                    return this.f9822a.eCashTopUp((ECashTopUpRequestParams) this.c, this.d);
                case 20:
                    return this.f9822a.openChannel((OpenChannelRequestParams) this.c, this.d);
                case 21:
                    return this.f9822a.closeChannel((CloseChannelRequestParams) this.c, this.d);
                case 22:
                    return this.f9822a.sendApdu((SendApduRequestParams) this.c, this.d);
                case 23:
                    return this.f9822a.encryptData((EncryptDataRequestParams) this.c, this.d);
                case 24:
                    return this.f9822a.hideAppApply((HideAppApplyRequestParams) this.c, this.d);
                case 25:
                    return this.f9822a.executeCmd((ExecuteCmdRequestParams) this.c, this.d, this.e);
                case 26:
                    return this.f9822a.appLock((AppLockRequestParams) this.c, this.d);
                case 27:
                    return this.f9822a.appUnlock((AppUnlockRequestParams) this.c, this.d);
                case 28:
                    return this.f9822a.getCardInfoBySamsungPay((GetCardInfoBySpayRequestParams) this.c, this.d);
                case 29:
                    return this.f9822a.checkSSamsungPay((CheckSSamsungPayRequestParams) this.c, this.d);
                case 30:
                    return this.f9822a.setSamsungDefaultWallet((SetSamsungDefWalletRequestParams) this.c, this.d);
                case 31:
                    return this.f9822a.getEncryptData((GetEncryptDataRequestParams) this.c, this.d);
                case 32:
                    return this.f9822a.setSafetyKeyboardBitmap((SafetyKeyboardRequestParams) this.c);
                case 33:
                    return this.f9822a.clearEncryptData(this.i);
                case 34:
                    return this.f9822a.hideKeyboard();
                case 35:
                    return this.f9822a.cardListStatusChanged((CardListStatusChangedRequestParams) this.c, this.d);
                case 36:
                    return this.f9822a.getVendorPayStatus((GetVendorPayStatusRequestParams) this.c, this.d);
                case 37:
                    return this.f9822a.activateVendorPay((ActivateVendorPayRequestParams) this.c, this.d);
                case 38:
                    return this.f9822a.addCardToVendorPay((AddCardToVendorPayRequestParams) this.c, this.d, this.e);
                case 39:
                    return this.f9822a.onlinePaymentVerify((OnlinePaymentVerifyRequestParams) this.c, this.d);
                case 40:
                    return this.f9822a.preDownload((PreDownloadRequestParams) this.c, this.d, this.e);
                case 41:
                    return this.f9822a.queryVendorPayStatus((QueryVendorPayStatusRequestParams) this.c, this.d);
                case 42:
                    return this.f9822a.acquireSEAppList((AcquireSEAppListRequestParams) this.c, this.d);
                case 43:
                    return this.f9822a.getTransactionDetails((GetTransactionDetailsRequestParams) this.c, this.d);
                case 44:
                    return this.f9822a.getMessageDetails((GetMessageDetailsRequestParams) this.c, this.d);
                case 45:
                    return this.f9822a.sendCustomData((SendCustomDataRequestParams) this.c, this.d);
                case 46:
                    return this.f9822a.createSSD((UniteRequestParams) this.c, this.d);
                default:
                    return 0;
            }
        } else {
            return this.f9822a.showSafetyKeyboard((SafetyKeyboardRequestParams) this.c, this.i, this.g, this.h);
        }
    }
}
