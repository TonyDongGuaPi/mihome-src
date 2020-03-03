package com.unionpay.tsmservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
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
import com.unionpay.tsmservice.request.CheckSupportCardApplyRequestParams;
import com.unionpay.tsmservice.request.ClearEncryptDataRequestParams;
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
import com.unionpay.tsmservice.request.GetCurrentWalletClientRequestParams;
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
import com.unionpay.tsmservice.request.HideSafetyKeyboardRequestParams;
import com.unionpay.tsmservice.request.InitRequestParams;
import com.unionpay.tsmservice.request.OnlinePaymentVerifyRequestParams;
import com.unionpay.tsmservice.request.OpenChannelRequestParams;
import com.unionpay.tsmservice.request.PreDownloadRequestParams;
import com.unionpay.tsmservice.request.QueryVendorPayStatusRequestParams;
import com.unionpay.tsmservice.request.SafetyKeyboardRequestParams;
import com.unionpay.tsmservice.request.SendApduRequestParams;
import com.unionpay.tsmservice.request.SendCustomDataRequestParams;
import com.unionpay.tsmservice.request.SetDefaultCardRequestParams;
import com.unionpay.tsmservice.request.SetSamsungDefWalletRequestParams;
import com.unionpay.tsmservice.request.StartCardApplyRequestParams;
import com.unionpay.tsmservice.request.UniteRequestParams;

public interface ITsmService extends IInterface {

    public static abstract class Stub extends Binder implements ITsmService {

        private static class a implements ITsmService {

            /* renamed from: a  reason: collision with root package name */
            private IBinder f9820a;

            a(IBinder iBinder) {
                this.f9820a = iBinder;
            }

            public final int acquireSEAppList(AcquireSEAppListRequestParams acquireSEAppListRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (acquireSEAppListRequestParams != null) {
                        obtain.writeInt(1);
                        acquireSEAppListRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(51, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int activateVendorPay(ActivateVendorPayRequestParams activateVendorPayRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (activateVendorPayRequestParams != null) {
                        obtain.writeInt(1);
                        activateVendorPayRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(44, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int addCardToVendorPay(AddCardToVendorPayRequestParams addCardToVendorPayRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (addCardToVendorPayRequestParams != null) {
                        obtain.writeInt(1);
                        addCardToVendorPayRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    IBinder iBinder = null;
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    if (iTsmProgressCallback != null) {
                        iBinder = iTsmProgressCallback.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.f9820a.transact(45, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int appDataUpdate(AppDataUpdateRequestParams appDataUpdateRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (appDataUpdateRequestParams != null) {
                        obtain.writeInt(1);
                        appDataUpdateRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    IBinder iBinder = null;
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    if (iTsmProgressCallback != null) {
                        iBinder = iTsmProgressCallback.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.f9820a.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int appDelete(AppDeleteRequestParams appDeleteRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (appDeleteRequestParams != null) {
                        obtain.writeInt(1);
                        appDeleteRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    IBinder iBinder = null;
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    if (iTsmProgressCallback != null) {
                        iBinder = iTsmProgressCallback.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.f9820a.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int appDownload(AppDownloadRequestParams appDownloadRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (appDownloadRequestParams != null) {
                        obtain.writeInt(1);
                        appDownloadRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    IBinder iBinder = null;
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    if (iTsmProgressCallback != null) {
                        iBinder = iTsmProgressCallback.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.f9820a.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int appDownloadApply(AppDownloadApplyRequestParams appDownloadApplyRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (appDownloadApplyRequestParams != null) {
                        obtain.writeInt(1);
                        appDownloadApplyRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int appLock(AppLockRequestParams appLockRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (appLockRequestParams != null) {
                        obtain.writeInt(1);
                        appLockRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int appUnlock(AppUnlockRequestParams appUnlockRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (appUnlockRequestParams != null) {
                        obtain.writeInt(1);
                        appUnlockRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IBinder asBinder() {
                return this.f9820a;
            }

            public final int cardListStatusChanged(CardListStatusChangedRequestParams cardListStatusChangedRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (cardListStatusChangedRequestParams != null) {
                        obtain.writeInt(1);
                        cardListStatusChangedRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(42, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int checkSSamsungPay(CheckSSamsungPayRequestParams checkSSamsungPayRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (checkSSamsungPayRequestParams != null) {
                        obtain.writeInt(1);
                        checkSSamsungPayRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int checkSupportCardApply(CheckSupportCardApplyRequestParams checkSupportCardApplyRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (checkSupportCardApplyRequestParams != null) {
                        obtain.writeInt(1);
                        checkSupportCardApplyRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(39, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int clearEncryptData(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    obtain.writeInt(i);
                    this.f9820a.transact(37, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int clearKeyboardEncryptData(ClearEncryptDataRequestParams clearEncryptDataRequestParams, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (clearEncryptDataRequestParams != null) {
                        obtain.writeInt(1);
                        clearEncryptDataRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    this.f9820a.transact(47, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int closeChannel(CloseChannelRequestParams closeChannelRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (closeChannelRequestParams != null) {
                        obtain.writeInt(1);
                        closeChannelRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int createSSD(UniteRequestParams uniteRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (uniteRequestParams != null) {
                        obtain.writeInt(1);
                        uniteRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(55, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int eCashTopUp(ECashTopUpRequestParams eCashTopUpRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (eCashTopUpRequestParams != null) {
                        obtain.writeInt(1);
                        eCashTopUpRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int encryptData(EncryptDataRequestParams encryptDataRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (encryptDataRequestParams != null) {
                        obtain.writeInt(1);
                        encryptDataRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int exchangeKey(String str, String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    obtain.writeString(str);
                    if (strArr == null) {
                        obtain.writeInt(-1);
                    } else {
                        obtain.writeInt(strArr.length);
                    }
                    this.f9820a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.readStringArray(strArr);
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int executeCmd(ExecuteCmdRequestParams executeCmdRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (executeCmdRequestParams != null) {
                        obtain.writeInt(1);
                        executeCmdRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    IBinder iBinder = null;
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    if (iTsmProgressCallback != null) {
                        iBinder = iTsmProgressCallback.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.f9820a.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int getAccountBalance(GetAccountBalanceRequestParams getAccountBalanceRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (getAccountBalanceRequestParams != null) {
                        obtain.writeInt(1);
                        getAccountBalanceRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int getAccountInfo(GetAccountInfoRequestParams getAccountInfoRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (getAccountInfoRequestParams != null) {
                        obtain.writeInt(1);
                        getAccountInfoRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int getAppDetail(GetAppDetailRequestParams getAppDetailRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (getAppDetailRequestParams != null) {
                        obtain.writeInt(1);
                        getAppDetailRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int getAppList(GetAppListRequestParams getAppListRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (getAppListRequestParams != null) {
                        obtain.writeInt(1);
                        getAppListRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int getAppStatus(GetAppStatusRequestParams getAppStatusRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (getAppStatusRequestParams != null) {
                        obtain.writeInt(1);
                        getAppStatusRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int getAssociatedApp(GetAssociatedAppRequestParams getAssociatedAppRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (getAssociatedAppRequestParams != null) {
                        obtain.writeInt(1);
                        getAssociatedAppRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int getCardInfo(GetCardInfoRequestParams getCardInfoRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (getCardInfoRequestParams != null) {
                        obtain.writeInt(1);
                        getCardInfoRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int getCardInfoBySamsungPay(GetCardInfoBySpayRequestParams getCardInfoBySpayRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (getCardInfoBySpayRequestParams != null) {
                        obtain.writeInt(1);
                        getCardInfoBySpayRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int getCurrentWalletClient(GetCurrentWalletClientRequestParams getCurrentWalletClientRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (getCurrentWalletClientRequestParams != null) {
                        obtain.writeInt(1);
                        getCurrentWalletClientRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(41, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int getDefaultCard(GetDefaultCardRequestParams getDefaultCardRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (getDefaultCardRequestParams != null) {
                        obtain.writeInt(1);
                        getDefaultCardRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int getEncryptData(GetEncryptDataRequestParams getEncryptDataRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (getEncryptDataRequestParams != null) {
                        obtain.writeInt(1);
                        getEncryptDataRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(36, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int getMessageDetails(GetMessageDetailsRequestParams getMessageDetailsRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (getMessageDetailsRequestParams != null) {
                        obtain.writeInt(1);
                        getMessageDetailsRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(53, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int getPubKey(int i, String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    obtain.writeInt(i);
                    if (strArr == null) {
                        obtain.writeInt(-1);
                    } else {
                        obtain.writeInt(strArr.length);
                    }
                    this.f9820a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.readStringArray(strArr);
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int getSEAppList(GetSeAppListRequestParams getSeAppListRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (getSeAppListRequestParams != null) {
                        obtain.writeInt(1);
                        getSeAppListRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int getSEId(GetSeIdRequestParams getSeIdRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (getSeIdRequestParams != null) {
                        obtain.writeInt(1);
                        getSeIdRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int getSMSAuthCode(GetSMSAuthCodeRequestParams getSMSAuthCodeRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (getSMSAuthCodeRequestParams != null) {
                        obtain.writeInt(1);
                        getSMSAuthCodeRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int getTransElements(GetTransElementsRequestParams getTransElementsRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (getTransElementsRequestParams != null) {
                        obtain.writeInt(1);
                        getTransElementsRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int getTransRecord(GetTransRecordRequestParams getTransRecordRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (getTransRecordRequestParams != null) {
                        obtain.writeInt(1);
                        getTransRecordRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int getTransactionDetails(GetTransactionDetailsRequestParams getTransactionDetailsRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (getTransactionDetailsRequestParams != null) {
                        obtain.writeInt(1);
                        getTransactionDetailsRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(52, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int getVendorPayStatus(GetVendorPayStatusRequestParams getVendorPayStatusRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (getVendorPayStatusRequestParams != null) {
                        obtain.writeInt(1);
                        getVendorPayStatusRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(43, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int hideAppApply(HideAppApplyRequestParams hideAppApplyRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (hideAppApplyRequestParams != null) {
                        obtain.writeInt(1);
                        hideAppApplyRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int hideKeyboard() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    this.f9820a.transact(38, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int hideSafetyKeyboard(HideSafetyKeyboardRequestParams hideSafetyKeyboardRequestParams) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (hideSafetyKeyboardRequestParams != null) {
                        obtain.writeInt(1);
                        hideSafetyKeyboardRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f9820a.transact(48, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int init(InitRequestParams initRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (initRequestParams != null) {
                        obtain.writeInt(1);
                        initRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int onlinePaymentVerify(OnlinePaymentVerifyRequestParams onlinePaymentVerifyRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (onlinePaymentVerifyRequestParams != null) {
                        obtain.writeInt(1);
                        onlinePaymentVerifyRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(46, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int openChannel(OpenChannelRequestParams openChannelRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (openChannelRequestParams != null) {
                        obtain.writeInt(1);
                        openChannelRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int preDownload(PreDownloadRequestParams preDownloadRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (preDownloadRequestParams != null) {
                        obtain.writeInt(1);
                        preDownloadRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    IBinder iBinder = null;
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    if (iTsmProgressCallback != null) {
                        iBinder = iTsmProgressCallback.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.f9820a.transact(49, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int queryVendorPayStatus(QueryVendorPayStatusRequestParams queryVendorPayStatusRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (queryVendorPayStatusRequestParams != null) {
                        obtain.writeInt(1);
                        queryVendorPayStatusRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(50, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int sendApdu(SendApduRequestParams sendApduRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (sendApduRequestParams != null) {
                        obtain.writeInt(1);
                        sendApduRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int sendCustomData(SendCustomDataRequestParams sendCustomDataRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (sendCustomDataRequestParams != null) {
                        obtain.writeInt(1);
                        sendCustomDataRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(54, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int setDefaultCard(SetDefaultCardRequestParams setDefaultCardRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (setDefaultCardRequestParams != null) {
                        obtain.writeInt(1);
                        setDefaultCardRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int setSafetyKeyboardBitmap(SafetyKeyboardRequestParams safetyKeyboardRequestParams) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (safetyKeyboardRequestParams != null) {
                        obtain.writeInt(1);
                        safetyKeyboardRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f9820a.transact(35, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int setSamsungDefaultWallet(SetSamsungDefWalletRequestParams setSamsungDefWalletRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (setSamsungDefWalletRequestParams != null) {
                        obtain.writeInt(1);
                        setSamsungDefWalletRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int showSafetyKeyboard(SafetyKeyboardRequestParams safetyKeyboardRequestParams, int i, OnSafetyKeyboardCallback onSafetyKeyboardCallback, ITsmActivityCallback iTsmActivityCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (safetyKeyboardRequestParams != null) {
                        obtain.writeInt(1);
                        safetyKeyboardRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    IBinder iBinder = null;
                    obtain.writeStrongBinder(onSafetyKeyboardCallback != null ? onSafetyKeyboardCallback.asBinder() : null);
                    if (iTsmActivityCallback != null) {
                        iBinder = iTsmActivityCallback.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.f9820a.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int startCardApply(StartCardApplyRequestParams startCardApplyRequestParams, ITsmCallback iTsmCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.ITsmService");
                    if (startCardApplyRequestParams != null) {
                        obtain.writeInt(1);
                        startCardApplyRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9820a.transact(40, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.unionpay.tsmservice.ITsmService");
        }

        public static ITsmService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.unionpay.tsmservice.ITsmService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ITsmService)) ? new a(iBinder) : (ITsmService) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: com.unionpay.tsmservice.request.InitRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: java.lang.String[]} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: java.lang.String[]} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: com.unionpay.tsmservice.request.EncryptDataRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v12, resolved type: com.unionpay.tsmservice.request.GetSeIdRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v15, resolved type: com.unionpay.tsmservice.request.GetAssociatedAppRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v18, resolved type: com.unionpay.tsmservice.request.GetSeAppListRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v21, resolved type: com.unionpay.tsmservice.request.GetAppListRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v24, resolved type: com.unionpay.tsmservice.request.GetAppStatusRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v27, resolved type: com.unionpay.tsmservice.request.GetAppDetailRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v30, resolved type: com.unionpay.tsmservice.request.GetTransElementsRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v33, resolved type: com.unionpay.tsmservice.request.AppDownloadApplyRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v36, resolved type: com.unionpay.tsmservice.request.AppDownloadRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v39, resolved type: com.unionpay.tsmservice.request.AppDeleteRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v42, resolved type: com.unionpay.tsmservice.request.AppDataUpdateRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v45, resolved type: com.unionpay.tsmservice.request.AppLockRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v48, resolved type: com.unionpay.tsmservice.request.AppUnlockRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v51, resolved type: com.unionpay.tsmservice.request.GetSMSAuthCodeRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v54, resolved type: com.unionpay.tsmservice.request.ECashTopUpRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v57, resolved type: com.unionpay.tsmservice.request.GetTransRecordRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v60, resolved type: com.unionpay.tsmservice.request.GetAccountInfoRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v63, resolved type: com.unionpay.tsmservice.request.GetAccountBalanceRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v66, resolved type: com.unionpay.tsmservice.request.GetCardInfoRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v69, resolved type: com.unionpay.tsmservice.request.SetDefaultCardRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v72, resolved type: com.unionpay.tsmservice.request.GetDefaultCardRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v75, resolved type: com.unionpay.tsmservice.request.OpenChannelRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v78, resolved type: com.unionpay.tsmservice.request.SendApduRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v81, resolved type: com.unionpay.tsmservice.request.CloseChannelRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v84, resolved type: com.unionpay.tsmservice.request.HideAppApplyRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v87, resolved type: com.unionpay.tsmservice.request.ExecuteCmdRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v90, resolved type: com.unionpay.tsmservice.request.GetCardInfoBySpayRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v93, resolved type: com.unionpay.tsmservice.request.CheckSSamsungPayRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v96, resolved type: com.unionpay.tsmservice.request.SetSamsungDefWalletRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v99, resolved type: com.unionpay.tsmservice.request.SafetyKeyboardRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v102, resolved type: com.unionpay.tsmservice.request.SafetyKeyboardRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v105, resolved type: com.unionpay.tsmservice.request.GetEncryptDataRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v108, resolved type: com.unionpay.tsmservice.request.CheckSupportCardApplyRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v111, resolved type: com.unionpay.tsmservice.request.StartCardApplyRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v114, resolved type: com.unionpay.tsmservice.request.GetCurrentWalletClientRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v117, resolved type: com.unionpay.tsmservice.request.CardListStatusChangedRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v120, resolved type: com.unionpay.tsmservice.request.GetVendorPayStatusRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v123, resolved type: com.unionpay.tsmservice.request.ActivateVendorPayRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v126, resolved type: com.unionpay.tsmservice.request.AddCardToVendorPayRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v129, resolved type: com.unionpay.tsmservice.request.OnlinePaymentVerifyRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v132, resolved type: com.unionpay.tsmservice.request.ClearEncryptDataRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v135, resolved type: com.unionpay.tsmservice.request.HideSafetyKeyboardRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v138, resolved type: com.unionpay.tsmservice.request.PreDownloadRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v141, resolved type: com.unionpay.tsmservice.request.QueryVendorPayStatusRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v144, resolved type: com.unionpay.tsmservice.request.AcquireSEAppListRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v147, resolved type: com.unionpay.tsmservice.request.GetTransactionDetailsRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v150, resolved type: com.unionpay.tsmservice.request.GetMessageDetailsRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v153, resolved type: com.unionpay.tsmservice.request.SendCustomDataRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v156, resolved type: com.unionpay.tsmservice.request.UniteRequestParams} */
        /* JADX WARNING: type inference failed for: r1v1 */
        /* JADX WARNING: type inference failed for: r1v159 */
        /* JADX WARNING: type inference failed for: r1v160 */
        /* JADX WARNING: type inference failed for: r1v161 */
        /* JADX WARNING: type inference failed for: r1v162 */
        /* JADX WARNING: type inference failed for: r1v163 */
        /* JADX WARNING: type inference failed for: r1v164 */
        /* JADX WARNING: type inference failed for: r1v165 */
        /* JADX WARNING: type inference failed for: r1v166 */
        /* JADX WARNING: type inference failed for: r1v167 */
        /* JADX WARNING: type inference failed for: r1v168 */
        /* JADX WARNING: type inference failed for: r1v169 */
        /* JADX WARNING: type inference failed for: r1v170 */
        /* JADX WARNING: type inference failed for: r1v171 */
        /* JADX WARNING: type inference failed for: r1v172 */
        /* JADX WARNING: type inference failed for: r1v173 */
        /* JADX WARNING: type inference failed for: r1v174 */
        /* JADX WARNING: type inference failed for: r1v175 */
        /* JADX WARNING: type inference failed for: r1v176 */
        /* JADX WARNING: type inference failed for: r1v177 */
        /* JADX WARNING: type inference failed for: r1v178 */
        /* JADX WARNING: type inference failed for: r1v179 */
        /* JADX WARNING: type inference failed for: r1v180 */
        /* JADX WARNING: type inference failed for: r1v181 */
        /* JADX WARNING: type inference failed for: r1v182 */
        /* JADX WARNING: type inference failed for: r1v183 */
        /* JADX WARNING: type inference failed for: r1v184 */
        /* JADX WARNING: type inference failed for: r1v185 */
        /* JADX WARNING: type inference failed for: r1v186 */
        /* JADX WARNING: type inference failed for: r1v187 */
        /* JADX WARNING: type inference failed for: r1v188 */
        /* JADX WARNING: type inference failed for: r1v189 */
        /* JADX WARNING: type inference failed for: r1v190 */
        /* JADX WARNING: type inference failed for: r1v191 */
        /* JADX WARNING: type inference failed for: r1v192 */
        /* JADX WARNING: type inference failed for: r1v193 */
        /* JADX WARNING: type inference failed for: r1v194 */
        /* JADX WARNING: type inference failed for: r1v195 */
        /* JADX WARNING: type inference failed for: r1v196 */
        /* JADX WARNING: type inference failed for: r1v197 */
        /* JADX WARNING: type inference failed for: r1v198 */
        /* JADX WARNING: type inference failed for: r1v199 */
        /* JADX WARNING: type inference failed for: r1v200 */
        /* JADX WARNING: type inference failed for: r1v201 */
        /* JADX WARNING: type inference failed for: r1v202 */
        /* JADX WARNING: type inference failed for: r1v203 */
        /* JADX WARNING: type inference failed for: r1v204 */
        /* JADX WARNING: type inference failed for: r1v205 */
        /* JADX WARNING: type inference failed for: r1v206 */
        /* JADX WARNING: type inference failed for: r1v207 */
        /* JADX WARNING: type inference failed for: r1v208 */
        /* JADX WARNING: type inference failed for: r1v209 */
        /* JADX WARNING: type inference failed for: r1v210 */
        /* JADX WARNING: type inference failed for: r1v211 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r4, android.os.Parcel r5, android.os.Parcel r6, int r7) throws android.os.RemoteException {
            /*
                r3 = this;
                java.lang.String r0 = "com.unionpay.tsmservice.ITsmService"
                r1 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r2 = 1
                if (r4 == r1) goto L_0x07f4
                r1 = 0
                switch(r4) {
                    case 1: goto L_0x07cf;
                    case 2: goto L_0x07b1;
                    case 3: goto L_0x0793;
                    case 4: goto L_0x076e;
                    case 5: goto L_0x0749;
                    case 6: goto L_0x0724;
                    case 7: goto L_0x06ff;
                    case 8: goto L_0x06da;
                    case 9: goto L_0x06b5;
                    case 10: goto L_0x0690;
                    case 11: goto L_0x066b;
                    case 12: goto L_0x0646;
                    case 13: goto L_0x0619;
                    case 14: goto L_0x05ec;
                    case 15: goto L_0x05bf;
                    case 16: goto L_0x059a;
                    case 17: goto L_0x0575;
                    case 18: goto L_0x0550;
                    case 19: goto L_0x052b;
                    case 20: goto L_0x0506;
                    case 21: goto L_0x04e1;
                    case 22: goto L_0x04bc;
                    case 23: goto L_0x0497;
                    case 24: goto L_0x0472;
                    case 25: goto L_0x044d;
                    case 26: goto L_0x0428;
                    case 27: goto L_0x0403;
                    case 28: goto L_0x03de;
                    case 29: goto L_0x03b9;
                    case 30: goto L_0x038c;
                    case 31: goto L_0x0367;
                    case 32: goto L_0x0342;
                    case 33: goto L_0x031d;
                    case 34: goto L_0x02ec;
                    case 35: goto L_0x02cf;
                    case 36: goto L_0x02aa;
                    case 37: goto L_0x0298;
                    case 38: goto L_0x028a;
                    case 39: goto L_0x0265;
                    case 40: goto L_0x0240;
                    case 41: goto L_0x021b;
                    case 42: goto L_0x01f6;
                    case 43: goto L_0x01d1;
                    case 44: goto L_0x01ac;
                    case 45: goto L_0x017f;
                    case 46: goto L_0x015a;
                    case 47: goto L_0x0139;
                    case 48: goto L_0x011c;
                    case 49: goto L_0x00ef;
                    case 50: goto L_0x00ca;
                    case 51: goto L_0x00a5;
                    case 52: goto L_0x0080;
                    case 53: goto L_0x005b;
                    case 54: goto L_0x0036;
                    case 55: goto L_0x0011;
                    default: goto L_0x000c;
                }
            L_0x000c:
                boolean r4 = super.onTransact(r4, r5, r6, r7)
                return r4
            L_0x0011:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0023
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.UniteRequestParams> r4 = com.unionpay.tsmservice.request.UniteRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.UniteRequestParams r1 = (com.unionpay.tsmservice.request.UniteRequestParams) r1
            L_0x0023:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.createSSD(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x0036:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0048
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.SendCustomDataRequestParams> r4 = com.unionpay.tsmservice.request.SendCustomDataRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.SendCustomDataRequestParams r1 = (com.unionpay.tsmservice.request.SendCustomDataRequestParams) r1
            L_0x0048:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.sendCustomData(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x005b:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x006d
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.GetMessageDetailsRequestParams> r4 = com.unionpay.tsmservice.request.GetMessageDetailsRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.GetMessageDetailsRequestParams r1 = (com.unionpay.tsmservice.request.GetMessageDetailsRequestParams) r1
            L_0x006d:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.getMessageDetails(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x0080:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0092
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.GetTransactionDetailsRequestParams> r4 = com.unionpay.tsmservice.request.GetTransactionDetailsRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.GetTransactionDetailsRequestParams r1 = (com.unionpay.tsmservice.request.GetTransactionDetailsRequestParams) r1
            L_0x0092:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.getTransactionDetails(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x00a5:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x00b7
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.AcquireSEAppListRequestParams> r4 = com.unionpay.tsmservice.request.AcquireSEAppListRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.AcquireSEAppListRequestParams r1 = (com.unionpay.tsmservice.request.AcquireSEAppListRequestParams) r1
            L_0x00b7:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.acquireSEAppList(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x00ca:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x00dc
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.QueryVendorPayStatusRequestParams> r4 = com.unionpay.tsmservice.request.QueryVendorPayStatusRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.QueryVendorPayStatusRequestParams r1 = (com.unionpay.tsmservice.request.QueryVendorPayStatusRequestParams) r1
            L_0x00dc:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.queryVendorPayStatus(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x00ef:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0101
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.PreDownloadRequestParams> r4 = com.unionpay.tsmservice.request.PreDownloadRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.PreDownloadRequestParams r1 = (com.unionpay.tsmservice.request.PreDownloadRequestParams) r1
            L_0x0101:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                android.os.IBinder r5 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmProgressCallback r5 = com.unionpay.tsmservice.ITsmProgressCallback.Stub.asInterface(r5)
                int r4 = r3.preDownload(r1, r4, r5)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x011c:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x012e
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.HideSafetyKeyboardRequestParams> r4 = com.unionpay.tsmservice.request.HideSafetyKeyboardRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.HideSafetyKeyboardRequestParams r1 = (com.unionpay.tsmservice.request.HideSafetyKeyboardRequestParams) r1
            L_0x012e:
                int r4 = r3.hideSafetyKeyboard(r1)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x0139:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x014b
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.ClearEncryptDataRequestParams> r4 = com.unionpay.tsmservice.request.ClearEncryptDataRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.ClearEncryptDataRequestParams r1 = (com.unionpay.tsmservice.request.ClearEncryptDataRequestParams) r1
            L_0x014b:
                int r4 = r5.readInt()
                int r4 = r3.clearKeyboardEncryptData(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x015a:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x016c
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.OnlinePaymentVerifyRequestParams> r4 = com.unionpay.tsmservice.request.OnlinePaymentVerifyRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.OnlinePaymentVerifyRequestParams r1 = (com.unionpay.tsmservice.request.OnlinePaymentVerifyRequestParams) r1
            L_0x016c:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.onlinePaymentVerify(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x017f:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0191
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.AddCardToVendorPayRequestParams> r4 = com.unionpay.tsmservice.request.AddCardToVendorPayRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.AddCardToVendorPayRequestParams r1 = (com.unionpay.tsmservice.request.AddCardToVendorPayRequestParams) r1
            L_0x0191:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                android.os.IBinder r5 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmProgressCallback r5 = com.unionpay.tsmservice.ITsmProgressCallback.Stub.asInterface(r5)
                int r4 = r3.addCardToVendorPay(r1, r4, r5)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x01ac:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x01be
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.ActivateVendorPayRequestParams> r4 = com.unionpay.tsmservice.request.ActivateVendorPayRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.ActivateVendorPayRequestParams r1 = (com.unionpay.tsmservice.request.ActivateVendorPayRequestParams) r1
            L_0x01be:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.activateVendorPay(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x01d1:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x01e3
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.GetVendorPayStatusRequestParams> r4 = com.unionpay.tsmservice.request.GetVendorPayStatusRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.GetVendorPayStatusRequestParams r1 = (com.unionpay.tsmservice.request.GetVendorPayStatusRequestParams) r1
            L_0x01e3:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.getVendorPayStatus(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x01f6:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0208
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.CardListStatusChangedRequestParams> r4 = com.unionpay.tsmservice.request.CardListStatusChangedRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.CardListStatusChangedRequestParams r1 = (com.unionpay.tsmservice.request.CardListStatusChangedRequestParams) r1
            L_0x0208:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.cardListStatusChanged(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x021b:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x022d
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.GetCurrentWalletClientRequestParams> r4 = com.unionpay.tsmservice.request.GetCurrentWalletClientRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.GetCurrentWalletClientRequestParams r1 = (com.unionpay.tsmservice.request.GetCurrentWalletClientRequestParams) r1
            L_0x022d:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.getCurrentWalletClient(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x0240:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0252
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.StartCardApplyRequestParams> r4 = com.unionpay.tsmservice.request.StartCardApplyRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.StartCardApplyRequestParams r1 = (com.unionpay.tsmservice.request.StartCardApplyRequestParams) r1
            L_0x0252:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.startCardApply(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x0265:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0277
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.CheckSupportCardApplyRequestParams> r4 = com.unionpay.tsmservice.request.CheckSupportCardApplyRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.CheckSupportCardApplyRequestParams r1 = (com.unionpay.tsmservice.request.CheckSupportCardApplyRequestParams) r1
            L_0x0277:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.checkSupportCardApply(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x028a:
                r5.enforceInterface(r0)
                int r4 = r3.hideKeyboard()
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x0298:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                int r4 = r3.clearEncryptData(r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x02aa:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x02bc
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.GetEncryptDataRequestParams> r4 = com.unionpay.tsmservice.request.GetEncryptDataRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.GetEncryptDataRequestParams r1 = (com.unionpay.tsmservice.request.GetEncryptDataRequestParams) r1
            L_0x02bc:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.getEncryptData(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x02cf:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x02e1
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.SafetyKeyboardRequestParams> r4 = com.unionpay.tsmservice.request.SafetyKeyboardRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.SafetyKeyboardRequestParams r1 = (com.unionpay.tsmservice.request.SafetyKeyboardRequestParams) r1
            L_0x02e1:
                int r4 = r3.setSafetyKeyboardBitmap(r1)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x02ec:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x02fe
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.SafetyKeyboardRequestParams> r4 = com.unionpay.tsmservice.request.SafetyKeyboardRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.SafetyKeyboardRequestParams r1 = (com.unionpay.tsmservice.request.SafetyKeyboardRequestParams) r1
            L_0x02fe:
                int r4 = r5.readInt()
                android.os.IBinder r7 = r5.readStrongBinder()
                com.unionpay.tsmservice.OnSafetyKeyboardCallback r7 = com.unionpay.tsmservice.OnSafetyKeyboardCallback.Stub.asInterface(r7)
                android.os.IBinder r5 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmActivityCallback r5 = com.unionpay.tsmservice.ITsmActivityCallback.Stub.asInterface(r5)
                int r4 = r3.showSafetyKeyboard(r1, r4, r7, r5)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x031d:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x032f
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.SetSamsungDefWalletRequestParams> r4 = com.unionpay.tsmservice.request.SetSamsungDefWalletRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.SetSamsungDefWalletRequestParams r1 = (com.unionpay.tsmservice.request.SetSamsungDefWalletRequestParams) r1
            L_0x032f:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.setSamsungDefaultWallet(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x0342:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0354
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.CheckSSamsungPayRequestParams> r4 = com.unionpay.tsmservice.request.CheckSSamsungPayRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.CheckSSamsungPayRequestParams r1 = (com.unionpay.tsmservice.request.CheckSSamsungPayRequestParams) r1
            L_0x0354:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.checkSSamsungPay(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x0367:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0379
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.GetCardInfoBySpayRequestParams> r4 = com.unionpay.tsmservice.request.GetCardInfoBySpayRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.GetCardInfoBySpayRequestParams r1 = (com.unionpay.tsmservice.request.GetCardInfoBySpayRequestParams) r1
            L_0x0379:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.getCardInfoBySamsungPay(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x038c:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x039e
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.ExecuteCmdRequestParams> r4 = com.unionpay.tsmservice.request.ExecuteCmdRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.ExecuteCmdRequestParams r1 = (com.unionpay.tsmservice.request.ExecuteCmdRequestParams) r1
            L_0x039e:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                android.os.IBinder r5 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmProgressCallback r5 = com.unionpay.tsmservice.ITsmProgressCallback.Stub.asInterface(r5)
                int r4 = r3.executeCmd(r1, r4, r5)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x03b9:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x03cb
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.HideAppApplyRequestParams> r4 = com.unionpay.tsmservice.request.HideAppApplyRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.HideAppApplyRequestParams r1 = (com.unionpay.tsmservice.request.HideAppApplyRequestParams) r1
            L_0x03cb:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.hideAppApply(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x03de:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x03f0
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.CloseChannelRequestParams> r4 = com.unionpay.tsmservice.request.CloseChannelRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.CloseChannelRequestParams r1 = (com.unionpay.tsmservice.request.CloseChannelRequestParams) r1
            L_0x03f0:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.closeChannel(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x0403:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0415
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.SendApduRequestParams> r4 = com.unionpay.tsmservice.request.SendApduRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.SendApduRequestParams r1 = (com.unionpay.tsmservice.request.SendApduRequestParams) r1
            L_0x0415:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.sendApdu(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x0428:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x043a
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.OpenChannelRequestParams> r4 = com.unionpay.tsmservice.request.OpenChannelRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.OpenChannelRequestParams r1 = (com.unionpay.tsmservice.request.OpenChannelRequestParams) r1
            L_0x043a:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.openChannel(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x044d:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x045f
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.GetDefaultCardRequestParams> r4 = com.unionpay.tsmservice.request.GetDefaultCardRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.GetDefaultCardRequestParams r1 = (com.unionpay.tsmservice.request.GetDefaultCardRequestParams) r1
            L_0x045f:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.getDefaultCard(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x0472:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0484
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.SetDefaultCardRequestParams> r4 = com.unionpay.tsmservice.request.SetDefaultCardRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.SetDefaultCardRequestParams r1 = (com.unionpay.tsmservice.request.SetDefaultCardRequestParams) r1
            L_0x0484:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.setDefaultCard(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x0497:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x04a9
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.GetCardInfoRequestParams> r4 = com.unionpay.tsmservice.request.GetCardInfoRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.GetCardInfoRequestParams r1 = (com.unionpay.tsmservice.request.GetCardInfoRequestParams) r1
            L_0x04a9:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.getCardInfo(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x04bc:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x04ce
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.GetAccountBalanceRequestParams> r4 = com.unionpay.tsmservice.request.GetAccountBalanceRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.GetAccountBalanceRequestParams r1 = (com.unionpay.tsmservice.request.GetAccountBalanceRequestParams) r1
            L_0x04ce:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.getAccountBalance(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x04e1:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x04f3
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.GetAccountInfoRequestParams> r4 = com.unionpay.tsmservice.request.GetAccountInfoRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.GetAccountInfoRequestParams r1 = (com.unionpay.tsmservice.request.GetAccountInfoRequestParams) r1
            L_0x04f3:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.getAccountInfo(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x0506:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0518
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.GetTransRecordRequestParams> r4 = com.unionpay.tsmservice.request.GetTransRecordRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.GetTransRecordRequestParams r1 = (com.unionpay.tsmservice.request.GetTransRecordRequestParams) r1
            L_0x0518:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.getTransRecord(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x052b:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x053d
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.ECashTopUpRequestParams> r4 = com.unionpay.tsmservice.request.ECashTopUpRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.ECashTopUpRequestParams r1 = (com.unionpay.tsmservice.request.ECashTopUpRequestParams) r1
            L_0x053d:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.eCashTopUp(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x0550:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0562
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.GetSMSAuthCodeRequestParams> r4 = com.unionpay.tsmservice.request.GetSMSAuthCodeRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.GetSMSAuthCodeRequestParams r1 = (com.unionpay.tsmservice.request.GetSMSAuthCodeRequestParams) r1
            L_0x0562:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.getSMSAuthCode(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x0575:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0587
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.AppUnlockRequestParams> r4 = com.unionpay.tsmservice.request.AppUnlockRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.AppUnlockRequestParams r1 = (com.unionpay.tsmservice.request.AppUnlockRequestParams) r1
            L_0x0587:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.appUnlock(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x059a:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x05ac
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.AppLockRequestParams> r4 = com.unionpay.tsmservice.request.AppLockRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.AppLockRequestParams r1 = (com.unionpay.tsmservice.request.AppLockRequestParams) r1
            L_0x05ac:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.appLock(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x05bf:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x05d1
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.AppDataUpdateRequestParams> r4 = com.unionpay.tsmservice.request.AppDataUpdateRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.AppDataUpdateRequestParams r1 = (com.unionpay.tsmservice.request.AppDataUpdateRequestParams) r1
            L_0x05d1:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                android.os.IBinder r5 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmProgressCallback r5 = com.unionpay.tsmservice.ITsmProgressCallback.Stub.asInterface(r5)
                int r4 = r3.appDataUpdate(r1, r4, r5)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x05ec:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x05fe
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.AppDeleteRequestParams> r4 = com.unionpay.tsmservice.request.AppDeleteRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.AppDeleteRequestParams r1 = (com.unionpay.tsmservice.request.AppDeleteRequestParams) r1
            L_0x05fe:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                android.os.IBinder r5 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmProgressCallback r5 = com.unionpay.tsmservice.ITsmProgressCallback.Stub.asInterface(r5)
                int r4 = r3.appDelete(r1, r4, r5)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x0619:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x062b
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.AppDownloadRequestParams> r4 = com.unionpay.tsmservice.request.AppDownloadRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.AppDownloadRequestParams r1 = (com.unionpay.tsmservice.request.AppDownloadRequestParams) r1
            L_0x062b:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                android.os.IBinder r5 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmProgressCallback r5 = com.unionpay.tsmservice.ITsmProgressCallback.Stub.asInterface(r5)
                int r4 = r3.appDownload(r1, r4, r5)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x0646:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0658
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.AppDownloadApplyRequestParams> r4 = com.unionpay.tsmservice.request.AppDownloadApplyRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.AppDownloadApplyRequestParams r1 = (com.unionpay.tsmservice.request.AppDownloadApplyRequestParams) r1
            L_0x0658:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.appDownloadApply(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x066b:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x067d
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.GetTransElementsRequestParams> r4 = com.unionpay.tsmservice.request.GetTransElementsRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.GetTransElementsRequestParams r1 = (com.unionpay.tsmservice.request.GetTransElementsRequestParams) r1
            L_0x067d:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.getTransElements(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x0690:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x06a2
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.GetAppDetailRequestParams> r4 = com.unionpay.tsmservice.request.GetAppDetailRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.GetAppDetailRequestParams r1 = (com.unionpay.tsmservice.request.GetAppDetailRequestParams) r1
            L_0x06a2:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.getAppDetail(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x06b5:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x06c7
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.GetAppStatusRequestParams> r4 = com.unionpay.tsmservice.request.GetAppStatusRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.GetAppStatusRequestParams r1 = (com.unionpay.tsmservice.request.GetAppStatusRequestParams) r1
            L_0x06c7:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.getAppStatus(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x06da:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x06ec
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.GetAppListRequestParams> r4 = com.unionpay.tsmservice.request.GetAppListRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.GetAppListRequestParams r1 = (com.unionpay.tsmservice.request.GetAppListRequestParams) r1
            L_0x06ec:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.getAppList(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x06ff:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0711
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.GetSeAppListRequestParams> r4 = com.unionpay.tsmservice.request.GetSeAppListRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.GetSeAppListRequestParams r1 = (com.unionpay.tsmservice.request.GetSeAppListRequestParams) r1
            L_0x0711:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.getSEAppList(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x0724:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0736
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.GetAssociatedAppRequestParams> r4 = com.unionpay.tsmservice.request.GetAssociatedAppRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.GetAssociatedAppRequestParams r1 = (com.unionpay.tsmservice.request.GetAssociatedAppRequestParams) r1
            L_0x0736:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.getAssociatedApp(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x0749:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x075b
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.GetSeIdRequestParams> r4 = com.unionpay.tsmservice.request.GetSeIdRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.GetSeIdRequestParams r1 = (com.unionpay.tsmservice.request.GetSeIdRequestParams) r1
            L_0x075b:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.getSEId(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x076e:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0780
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.EncryptDataRequestParams> r4 = com.unionpay.tsmservice.request.EncryptDataRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.EncryptDataRequestParams r1 = (com.unionpay.tsmservice.request.EncryptDataRequestParams) r1
            L_0x0780:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.encryptData(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x0793:
                r5.enforceInterface(r0)
                java.lang.String r4 = r5.readString()
                int r5 = r5.readInt()
                if (r5 >= 0) goto L_0x07a1
                goto L_0x07a3
            L_0x07a1:
                java.lang.String[] r1 = new java.lang.String[r5]
            L_0x07a3:
                int r4 = r3.exchangeKey(r4, r1)
                r6.writeNoException()
                r6.writeInt(r4)
                r6.writeStringArray(r1)
                return r2
            L_0x07b1:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                int r5 = r5.readInt()
                if (r5 >= 0) goto L_0x07bf
                goto L_0x07c1
            L_0x07bf:
                java.lang.String[] r1 = new java.lang.String[r5]
            L_0x07c1:
                int r4 = r3.getPubKey(r4, r1)
                r6.writeNoException()
                r6.writeInt(r4)
                r6.writeStringArray(r1)
                return r2
            L_0x07cf:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x07e1
                android.os.Parcelable$Creator<com.unionpay.tsmservice.request.InitRequestParams> r4 = com.unionpay.tsmservice.request.InitRequestParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.unionpay.tsmservice.request.InitRequestParams r1 = (com.unionpay.tsmservice.request.InitRequestParams) r1
            L_0x07e1:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.unionpay.tsmservice.ITsmCallback r4 = com.unionpay.tsmservice.ITsmCallback.Stub.asInterface(r4)
                int r4 = r3.init(r1, r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x07f4:
                r6.writeString(r0)
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.ITsmService.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    int acquireSEAppList(AcquireSEAppListRequestParams acquireSEAppListRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int activateVendorPay(ActivateVendorPayRequestParams activateVendorPayRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int addCardToVendorPay(AddCardToVendorPayRequestParams addCardToVendorPayRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) throws RemoteException;

    int appDataUpdate(AppDataUpdateRequestParams appDataUpdateRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) throws RemoteException;

    int appDelete(AppDeleteRequestParams appDeleteRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) throws RemoteException;

    int appDownload(AppDownloadRequestParams appDownloadRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) throws RemoteException;

    int appDownloadApply(AppDownloadApplyRequestParams appDownloadApplyRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int appLock(AppLockRequestParams appLockRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int appUnlock(AppUnlockRequestParams appUnlockRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int cardListStatusChanged(CardListStatusChangedRequestParams cardListStatusChangedRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int checkSSamsungPay(CheckSSamsungPayRequestParams checkSSamsungPayRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int checkSupportCardApply(CheckSupportCardApplyRequestParams checkSupportCardApplyRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int clearEncryptData(int i) throws RemoteException;

    int clearKeyboardEncryptData(ClearEncryptDataRequestParams clearEncryptDataRequestParams, int i) throws RemoteException;

    int closeChannel(CloseChannelRequestParams closeChannelRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int createSSD(UniteRequestParams uniteRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int eCashTopUp(ECashTopUpRequestParams eCashTopUpRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int encryptData(EncryptDataRequestParams encryptDataRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int exchangeKey(String str, String[] strArr) throws RemoteException;

    int executeCmd(ExecuteCmdRequestParams executeCmdRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) throws RemoteException;

    int getAccountBalance(GetAccountBalanceRequestParams getAccountBalanceRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int getAccountInfo(GetAccountInfoRequestParams getAccountInfoRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int getAppDetail(GetAppDetailRequestParams getAppDetailRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int getAppList(GetAppListRequestParams getAppListRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int getAppStatus(GetAppStatusRequestParams getAppStatusRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int getAssociatedApp(GetAssociatedAppRequestParams getAssociatedAppRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int getCardInfo(GetCardInfoRequestParams getCardInfoRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int getCardInfoBySamsungPay(GetCardInfoBySpayRequestParams getCardInfoBySpayRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int getCurrentWalletClient(GetCurrentWalletClientRequestParams getCurrentWalletClientRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int getDefaultCard(GetDefaultCardRequestParams getDefaultCardRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int getEncryptData(GetEncryptDataRequestParams getEncryptDataRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int getMessageDetails(GetMessageDetailsRequestParams getMessageDetailsRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int getPubKey(int i, String[] strArr) throws RemoteException;

    int getSEAppList(GetSeAppListRequestParams getSeAppListRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int getSEId(GetSeIdRequestParams getSeIdRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int getSMSAuthCode(GetSMSAuthCodeRequestParams getSMSAuthCodeRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int getTransElements(GetTransElementsRequestParams getTransElementsRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int getTransRecord(GetTransRecordRequestParams getTransRecordRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int getTransactionDetails(GetTransactionDetailsRequestParams getTransactionDetailsRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int getVendorPayStatus(GetVendorPayStatusRequestParams getVendorPayStatusRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int hideAppApply(HideAppApplyRequestParams hideAppApplyRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int hideKeyboard() throws RemoteException;

    int hideSafetyKeyboard(HideSafetyKeyboardRequestParams hideSafetyKeyboardRequestParams) throws RemoteException;

    int init(InitRequestParams initRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int onlinePaymentVerify(OnlinePaymentVerifyRequestParams onlinePaymentVerifyRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int openChannel(OpenChannelRequestParams openChannelRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int preDownload(PreDownloadRequestParams preDownloadRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) throws RemoteException;

    int queryVendorPayStatus(QueryVendorPayStatusRequestParams queryVendorPayStatusRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int sendApdu(SendApduRequestParams sendApduRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int sendCustomData(SendCustomDataRequestParams sendCustomDataRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int setDefaultCard(SetDefaultCardRequestParams setDefaultCardRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int setSafetyKeyboardBitmap(SafetyKeyboardRequestParams safetyKeyboardRequestParams) throws RemoteException;

    int setSamsungDefaultWallet(SetSamsungDefWalletRequestParams setSamsungDefWalletRequestParams, ITsmCallback iTsmCallback) throws RemoteException;

    int showSafetyKeyboard(SafetyKeyboardRequestParams safetyKeyboardRequestParams, int i, OnSafetyKeyboardCallback onSafetyKeyboardCallback, ITsmActivityCallback iTsmActivityCallback) throws RemoteException;

    int startCardApply(StartCardApplyRequestParams startCardApplyRequestParams, ITsmCallback iTsmCallback) throws RemoteException;
}
