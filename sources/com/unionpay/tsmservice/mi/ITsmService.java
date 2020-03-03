package com.unionpay.tsmservice.mi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.unionpay.tsmservice.mi.request.AcquireSEAppListRequestParams;
import com.unionpay.tsmservice.mi.request.AddCardToVendorPayRequestParams;
import com.unionpay.tsmservice.mi.request.CancelPayRequestParams;
import com.unionpay.tsmservice.mi.request.CardListStatusChangedRequestParams;
import com.unionpay.tsmservice.mi.request.ClearEncryptDataRequestParams;
import com.unionpay.tsmservice.mi.request.EncryptDataRequestParams;
import com.unionpay.tsmservice.mi.request.GetEncryptDataRequestParams;
import com.unionpay.tsmservice.mi.request.GetMessageDetailsRequestParams;
import com.unionpay.tsmservice.mi.request.GetSeIdRequestParams;
import com.unionpay.tsmservice.mi.request.GetTransactionDetailsRequestParams;
import com.unionpay.tsmservice.mi.request.GetVendorPayStatusRequestParams;
import com.unionpay.tsmservice.mi.request.HideSafetyKeyboardRequestParams;
import com.unionpay.tsmservice.mi.request.InitRequestParams;
import com.unionpay.tsmservice.mi.request.OnlinePaymentVerifyRequestParams;
import com.unionpay.tsmservice.mi.request.PayResultNotifyRequestParams;
import com.unionpay.tsmservice.mi.request.PinRequestRequestParams;
import com.unionpay.tsmservice.mi.request.QueryVendorPayStatusRequestParams;
import com.unionpay.tsmservice.mi.request.SafetyKeyboardRequestParams;

public interface ITsmService extends IInterface {

    public abstract class Stub extends Binder implements ITsmService {

        final class a implements ITsmService {

            /* renamed from: a  reason: collision with root package name */
            private IBinder f9833a;

            a(IBinder iBinder) {
                this.f9833a = iBinder;
            }

            public final int acquireSEAppList(AcquireSEAppListRequestParams acquireSEAppListRequestParams, ITsmCallback iTsmCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (acquireSEAppListRequestParams != null) {
                        obtain.writeInt(1);
                        acquireSEAppListRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9833a.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int addCardToVendorPay(AddCardToVendorPayRequestParams addCardToVendorPayRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
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
                    this.f9833a.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IBinder asBinder() {
                return this.f9833a;
            }

            public final int cancelPay(CancelPayRequestParams cancelPayRequestParams) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (cancelPayRequestParams != null) {
                        obtain.writeInt(1);
                        cancelPayRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f9833a.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int cardListStatusChanged(CardListStatusChangedRequestParams cardListStatusChangedRequestParams, ITsmCallback iTsmCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (cardListStatusChangedRequestParams != null) {
                        obtain.writeInt(1);
                        cardListStatusChangedRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9833a.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int clearEncryptData(int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    obtain.writeInt(i);
                    this.f9833a.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int clearKeyboardEncryptData(ClearEncryptDataRequestParams clearEncryptDataRequestParams, int i) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (clearEncryptDataRequestParams != null) {
                        obtain.writeInt(1);
                        clearEncryptDataRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    this.f9833a.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int encryptData(EncryptDataRequestParams encryptDataRequestParams, ITsmCallback iTsmCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (encryptDataRequestParams != null) {
                        obtain.writeInt(1);
                        encryptDataRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9833a.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int exchangeKey(String str, String[] strArr) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    obtain.writeString(str);
                    if (strArr == null) {
                        obtain.writeInt(-1);
                    } else {
                        obtain.writeInt(strArr.length);
                    }
                    this.f9833a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.readStringArray(strArr);
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int getEncryptData(GetEncryptDataRequestParams getEncryptDataRequestParams, ITsmCallback iTsmCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (getEncryptDataRequestParams != null) {
                        obtain.writeInt(1);
                        getEncryptDataRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9833a.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int getMessageDetails(GetMessageDetailsRequestParams getMessageDetailsRequestParams, ITsmCallback iTsmCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (getMessageDetailsRequestParams != null) {
                        obtain.writeInt(1);
                        getMessageDetailsRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9833a.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int getPubKey(int i, String[] strArr) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    obtain.writeInt(i);
                    if (strArr == null) {
                        obtain.writeInt(-1);
                    } else {
                        obtain.writeInt(strArr.length);
                    }
                    this.f9833a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.readStringArray(strArr);
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int getSEId(GetSeIdRequestParams getSeIdRequestParams, ITsmCallback iTsmCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (getSeIdRequestParams != null) {
                        obtain.writeInt(1);
                        getSeIdRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9833a.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int getTransactionDetails(GetTransactionDetailsRequestParams getTransactionDetailsRequestParams, ITsmCallback iTsmCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (getTransactionDetailsRequestParams != null) {
                        obtain.writeInt(1);
                        getTransactionDetailsRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9833a.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int getVendorPayStatus(GetVendorPayStatusRequestParams getVendorPayStatusRequestParams, ITsmCallback iTsmCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (getVendorPayStatusRequestParams != null) {
                        obtain.writeInt(1);
                        getVendorPayStatusRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9833a.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int getVendorPayStatusForBankApp(GetVendorPayStatusRequestParams getVendorPayStatusRequestParams, ITsmCallback iTsmCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (getVendorPayStatusRequestParams != null) {
                        obtain.writeInt(1);
                        getVendorPayStatusRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9833a.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int hideKeyboard() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    this.f9833a.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int hideSafetyKeyboard(HideSafetyKeyboardRequestParams hideSafetyKeyboardRequestParams) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (hideSafetyKeyboardRequestParams != null) {
                        obtain.writeInt(1);
                        hideSafetyKeyboardRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f9833a.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int init(InitRequestParams initRequestParams, ITsmCallback iTsmCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (initRequestParams != null) {
                        obtain.writeInt(1);
                        initRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9833a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int onlinePaymentVerify(OnlinePaymentVerifyRequestParams onlinePaymentVerifyRequestParams, ITsmCallback iTsmCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (onlinePaymentVerifyRequestParams != null) {
                        obtain.writeInt(1);
                        onlinePaymentVerifyRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9833a.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int payResultNotify(PayResultNotifyRequestParams payResultNotifyRequestParams, ITsmCallback iTsmCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (payResultNotifyRequestParams != null) {
                        obtain.writeInt(1);
                        payResultNotifyRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9833a.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int pinRequest(PinRequestRequestParams pinRequestRequestParams, ITsmCallback iTsmCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (pinRequestRequestParams != null) {
                        obtain.writeInt(1);
                        pinRequestRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9833a.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int queryVendorPayStatus(QueryVendorPayStatusRequestParams queryVendorPayStatusRequestParams, ITsmCallback iTsmCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (queryVendorPayStatusRequestParams != null) {
                        obtain.writeInt(1);
                        queryVendorPayStatusRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iTsmCallback != null ? iTsmCallback.asBinder() : null);
                    this.f9833a.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int setSafetyKeyboardBitmap(SafetyKeyboardRequestParams safetyKeyboardRequestParams) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
                    if (safetyKeyboardRequestParams != null) {
                        obtain.writeInt(1);
                        safetyKeyboardRequestParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f9833a.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final int showSafetyKeyboard(SafetyKeyboardRequestParams safetyKeyboardRequestParams, int i, OnSafetyKeyboardCallback onSafetyKeyboardCallback, ITsmActivityCallback iTsmActivityCallback) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.ITsmService");
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
                    this.f9833a.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.unionpay.tsmservice.mi.ITsmService");
        }

        public static ITsmService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.unionpay.tsmservice.mi.ITsmService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof ITsmService)) ? new a(iBinder) : (ITsmService) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: com.unionpay.tsmservice.mi.request.InitRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: java.lang.String[]} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: java.lang.String[]} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: com.unionpay.tsmservice.mi.request.EncryptDataRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: com.unionpay.tsmservice.mi.request.SafetyKeyboardRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v15, resolved type: com.unionpay.tsmservice.mi.request.SafetyKeyboardRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v18, resolved type: com.unionpay.tsmservice.mi.request.GetEncryptDataRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v21, resolved type: com.unionpay.tsmservice.mi.request.ClearEncryptDataRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v24, resolved type: com.unionpay.tsmservice.mi.request.HideSafetyKeyboardRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v27, resolved type: com.unionpay.tsmservice.mi.request.AcquireSEAppListRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v30, resolved type: com.unionpay.tsmservice.mi.request.CardListStatusChangedRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v33, resolved type: com.unionpay.tsmservice.mi.request.QueryVendorPayStatusRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v36, resolved type: com.unionpay.tsmservice.mi.request.GetVendorPayStatusRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v39, resolved type: com.unionpay.tsmservice.mi.request.OnlinePaymentVerifyRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v42, resolved type: com.unionpay.tsmservice.mi.request.PinRequestRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v45, resolved type: com.unionpay.tsmservice.mi.request.CancelPayRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v48, resolved type: com.unionpay.tsmservice.mi.request.PayResultNotifyRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v51, resolved type: com.unionpay.tsmservice.mi.request.GetVendorPayStatusRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v54, resolved type: com.unionpay.tsmservice.mi.request.GetSeIdRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v57, resolved type: com.unionpay.tsmservice.mi.request.AddCardToVendorPayRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v60, resolved type: com.unionpay.tsmservice.mi.request.GetTransactionDetailsRequestParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v63, resolved type: com.unionpay.tsmservice.mi.request.GetMessageDetailsRequestParams} */
        /* JADX WARNING: type inference failed for: r0v1 */
        /* JADX WARNING: type inference failed for: r0v66 */
        /* JADX WARNING: type inference failed for: r0v67 */
        /* JADX WARNING: type inference failed for: r0v68 */
        /* JADX WARNING: type inference failed for: r0v69 */
        /* JADX WARNING: type inference failed for: r0v70 */
        /* JADX WARNING: type inference failed for: r0v71 */
        /* JADX WARNING: type inference failed for: r0v72 */
        /* JADX WARNING: type inference failed for: r0v73 */
        /* JADX WARNING: type inference failed for: r0v74 */
        /* JADX WARNING: type inference failed for: r0v75 */
        /* JADX WARNING: type inference failed for: r0v76 */
        /* JADX WARNING: type inference failed for: r0v77 */
        /* JADX WARNING: type inference failed for: r0v78 */
        /* JADX WARNING: type inference failed for: r0v79 */
        /* JADX WARNING: type inference failed for: r0v80 */
        /* JADX WARNING: type inference failed for: r0v81 */
        /* JADX WARNING: type inference failed for: r0v82 */
        /* JADX WARNING: type inference failed for: r0v83 */
        /* JADX WARNING: type inference failed for: r0v84 */
        /* JADX WARNING: type inference failed for: r0v85 */
        /* JADX WARNING: type inference failed for: r0v86 */
        /* JADX WARNING: type inference failed for: r0v87 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r3, android.os.Parcel r4, android.os.Parcel r5, int r6) {
            /*
                r2 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                if (r3 == r0) goto L_0x0377
                r0 = 0
                switch(r3) {
                    case 1: goto L_0x0350;
                    case 2: goto L_0x0330;
                    case 3: goto L_0x0310;
                    case 4: goto L_0x02e9;
                    case 5: goto L_0x02b6;
                    case 6: goto L_0x0297;
                    case 7: goto L_0x0270;
                    case 8: goto L_0x025c;
                    case 9: goto L_0x024c;
                    case 10: goto L_0x0229;
                    case 11: goto L_0x020a;
                    case 12: goto L_0x01e3;
                    case 13: goto L_0x01bc;
                    case 14: goto L_0x0195;
                    case 15: goto L_0x016e;
                    case 16: goto L_0x0147;
                    case 17: goto L_0x0120;
                    case 18: goto L_0x0101;
                    case 19: goto L_0x00da;
                    case 20: goto L_0x00b3;
                    case 21: goto L_0x008c;
                    case 22: goto L_0x005d;
                    case 23: goto L_0x0036;
                    case 24: goto L_0x000f;
                    default: goto L_0x000a;
                }
            L_0x000a:
                boolean r3 = super.onTransact(r3, r4, r5, r6)
                return r3
            L_0x000f:
                java.lang.String r3 = "com.unionpay.tsmservice.mi.ITsmService"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x0023
                android.os.Parcelable$Creator r3 = com.unionpay.tsmservice.mi.request.GetMessageDetailsRequestParams.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                r0 = r3
                com.unionpay.tsmservice.mi.request.GetMessageDetailsRequestParams r0 = (com.unionpay.tsmservice.mi.request.GetMessageDetailsRequestParams) r0
            L_0x0023:
                android.os.IBinder r3 = r4.readStrongBinder()
                com.unionpay.tsmservice.mi.ITsmCallback r3 = com.unionpay.tsmservice.mi.ITsmCallback.Stub.asInterface(r3)
                int r3 = r2.getMessageDetails(r0, r3)
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x0036:
                java.lang.String r3 = "com.unionpay.tsmservice.mi.ITsmService"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x004a
                android.os.Parcelable$Creator r3 = com.unionpay.tsmservice.mi.request.GetTransactionDetailsRequestParams.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                r0 = r3
                com.unionpay.tsmservice.mi.request.GetTransactionDetailsRequestParams r0 = (com.unionpay.tsmservice.mi.request.GetTransactionDetailsRequestParams) r0
            L_0x004a:
                android.os.IBinder r3 = r4.readStrongBinder()
                com.unionpay.tsmservice.mi.ITsmCallback r3 = com.unionpay.tsmservice.mi.ITsmCallback.Stub.asInterface(r3)
                int r3 = r2.getTransactionDetails(r0, r3)
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x005d:
                java.lang.String r3 = "com.unionpay.tsmservice.mi.ITsmService"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x0071
                android.os.Parcelable$Creator r3 = com.unionpay.tsmservice.mi.request.AddCardToVendorPayRequestParams.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                r0 = r3
                com.unionpay.tsmservice.mi.request.AddCardToVendorPayRequestParams r0 = (com.unionpay.tsmservice.mi.request.AddCardToVendorPayRequestParams) r0
            L_0x0071:
                android.os.IBinder r3 = r4.readStrongBinder()
                com.unionpay.tsmservice.mi.ITsmCallback r3 = com.unionpay.tsmservice.mi.ITsmCallback.Stub.asInterface(r3)
                android.os.IBinder r4 = r4.readStrongBinder()
                com.unionpay.tsmservice.mi.ITsmProgressCallback r4 = com.unionpay.tsmservice.mi.ITsmProgressCallback.Stub.asInterface(r4)
                int r3 = r2.addCardToVendorPay(r0, r3, r4)
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x008c:
                java.lang.String r3 = "com.unionpay.tsmservice.mi.ITsmService"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x00a0
                android.os.Parcelable$Creator r3 = com.unionpay.tsmservice.mi.request.GetSeIdRequestParams.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                r0 = r3
                com.unionpay.tsmservice.mi.request.GetSeIdRequestParams r0 = (com.unionpay.tsmservice.mi.request.GetSeIdRequestParams) r0
            L_0x00a0:
                android.os.IBinder r3 = r4.readStrongBinder()
                com.unionpay.tsmservice.mi.ITsmCallback r3 = com.unionpay.tsmservice.mi.ITsmCallback.Stub.asInterface(r3)
                int r3 = r2.getSEId(r0, r3)
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x00b3:
                java.lang.String r3 = "com.unionpay.tsmservice.mi.ITsmService"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x00c7
                android.os.Parcelable$Creator r3 = com.unionpay.tsmservice.mi.request.GetVendorPayStatusRequestParams.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                r0 = r3
                com.unionpay.tsmservice.mi.request.GetVendorPayStatusRequestParams r0 = (com.unionpay.tsmservice.mi.request.GetVendorPayStatusRequestParams) r0
            L_0x00c7:
                android.os.IBinder r3 = r4.readStrongBinder()
                com.unionpay.tsmservice.mi.ITsmCallback r3 = com.unionpay.tsmservice.mi.ITsmCallback.Stub.asInterface(r3)
                int r3 = r2.getVendorPayStatusForBankApp(r0, r3)
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x00da:
                java.lang.String r3 = "com.unionpay.tsmservice.mi.ITsmService"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x00ee
                android.os.Parcelable$Creator r3 = com.unionpay.tsmservice.mi.request.PayResultNotifyRequestParams.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                r0 = r3
                com.unionpay.tsmservice.mi.request.PayResultNotifyRequestParams r0 = (com.unionpay.tsmservice.mi.request.PayResultNotifyRequestParams) r0
            L_0x00ee:
                android.os.IBinder r3 = r4.readStrongBinder()
                com.unionpay.tsmservice.mi.ITsmCallback r3 = com.unionpay.tsmservice.mi.ITsmCallback.Stub.asInterface(r3)
                int r3 = r2.payResultNotify(r0, r3)
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x0101:
                java.lang.String r3 = "com.unionpay.tsmservice.mi.ITsmService"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x0115
                android.os.Parcelable$Creator r3 = com.unionpay.tsmservice.mi.request.CancelPayRequestParams.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                r0 = r3
                com.unionpay.tsmservice.mi.request.CancelPayRequestParams r0 = (com.unionpay.tsmservice.mi.request.CancelPayRequestParams) r0
            L_0x0115:
                int r3 = r2.cancelPay(r0)
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x0120:
                java.lang.String r3 = "com.unionpay.tsmservice.mi.ITsmService"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x0134
                android.os.Parcelable$Creator r3 = com.unionpay.tsmservice.mi.request.PinRequestRequestParams.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                r0 = r3
                com.unionpay.tsmservice.mi.request.PinRequestRequestParams r0 = (com.unionpay.tsmservice.mi.request.PinRequestRequestParams) r0
            L_0x0134:
                android.os.IBinder r3 = r4.readStrongBinder()
                com.unionpay.tsmservice.mi.ITsmCallback r3 = com.unionpay.tsmservice.mi.ITsmCallback.Stub.asInterface(r3)
                int r3 = r2.pinRequest(r0, r3)
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x0147:
                java.lang.String r3 = "com.unionpay.tsmservice.mi.ITsmService"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x015b
                android.os.Parcelable$Creator r3 = com.unionpay.tsmservice.mi.request.OnlinePaymentVerifyRequestParams.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                r0 = r3
                com.unionpay.tsmservice.mi.request.OnlinePaymentVerifyRequestParams r0 = (com.unionpay.tsmservice.mi.request.OnlinePaymentVerifyRequestParams) r0
            L_0x015b:
                android.os.IBinder r3 = r4.readStrongBinder()
                com.unionpay.tsmservice.mi.ITsmCallback r3 = com.unionpay.tsmservice.mi.ITsmCallback.Stub.asInterface(r3)
                int r3 = r2.onlinePaymentVerify(r0, r3)
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x016e:
                java.lang.String r3 = "com.unionpay.tsmservice.mi.ITsmService"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x0182
                android.os.Parcelable$Creator r3 = com.unionpay.tsmservice.mi.request.GetVendorPayStatusRequestParams.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                r0 = r3
                com.unionpay.tsmservice.mi.request.GetVendorPayStatusRequestParams r0 = (com.unionpay.tsmservice.mi.request.GetVendorPayStatusRequestParams) r0
            L_0x0182:
                android.os.IBinder r3 = r4.readStrongBinder()
                com.unionpay.tsmservice.mi.ITsmCallback r3 = com.unionpay.tsmservice.mi.ITsmCallback.Stub.asInterface(r3)
                int r3 = r2.getVendorPayStatus(r0, r3)
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x0195:
                java.lang.String r3 = "com.unionpay.tsmservice.mi.ITsmService"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x01a9
                android.os.Parcelable$Creator r3 = com.unionpay.tsmservice.mi.request.QueryVendorPayStatusRequestParams.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                r0 = r3
                com.unionpay.tsmservice.mi.request.QueryVendorPayStatusRequestParams r0 = (com.unionpay.tsmservice.mi.request.QueryVendorPayStatusRequestParams) r0
            L_0x01a9:
                android.os.IBinder r3 = r4.readStrongBinder()
                com.unionpay.tsmservice.mi.ITsmCallback r3 = com.unionpay.tsmservice.mi.ITsmCallback.Stub.asInterface(r3)
                int r3 = r2.queryVendorPayStatus(r0, r3)
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x01bc:
                java.lang.String r3 = "com.unionpay.tsmservice.mi.ITsmService"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x01d0
                android.os.Parcelable$Creator r3 = com.unionpay.tsmservice.mi.request.CardListStatusChangedRequestParams.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                r0 = r3
                com.unionpay.tsmservice.mi.request.CardListStatusChangedRequestParams r0 = (com.unionpay.tsmservice.mi.request.CardListStatusChangedRequestParams) r0
            L_0x01d0:
                android.os.IBinder r3 = r4.readStrongBinder()
                com.unionpay.tsmservice.mi.ITsmCallback r3 = com.unionpay.tsmservice.mi.ITsmCallback.Stub.asInterface(r3)
                int r3 = r2.cardListStatusChanged(r0, r3)
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x01e3:
                java.lang.String r3 = "com.unionpay.tsmservice.mi.ITsmService"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x01f7
                android.os.Parcelable$Creator r3 = com.unionpay.tsmservice.mi.request.AcquireSEAppListRequestParams.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                r0 = r3
                com.unionpay.tsmservice.mi.request.AcquireSEAppListRequestParams r0 = (com.unionpay.tsmservice.mi.request.AcquireSEAppListRequestParams) r0
            L_0x01f7:
                android.os.IBinder r3 = r4.readStrongBinder()
                com.unionpay.tsmservice.mi.ITsmCallback r3 = com.unionpay.tsmservice.mi.ITsmCallback.Stub.asInterface(r3)
                int r3 = r2.acquireSEAppList(r0, r3)
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x020a:
                java.lang.String r3 = "com.unionpay.tsmservice.mi.ITsmService"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x021e
                android.os.Parcelable$Creator r3 = com.unionpay.tsmservice.mi.request.HideSafetyKeyboardRequestParams.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                r0 = r3
                com.unionpay.tsmservice.mi.request.HideSafetyKeyboardRequestParams r0 = (com.unionpay.tsmservice.mi.request.HideSafetyKeyboardRequestParams) r0
            L_0x021e:
                int r3 = r2.hideSafetyKeyboard(r0)
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x0229:
                java.lang.String r3 = "com.unionpay.tsmservice.mi.ITsmService"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x023d
                android.os.Parcelable$Creator r3 = com.unionpay.tsmservice.mi.request.ClearEncryptDataRequestParams.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                r0 = r3
                com.unionpay.tsmservice.mi.request.ClearEncryptDataRequestParams r0 = (com.unionpay.tsmservice.mi.request.ClearEncryptDataRequestParams) r0
            L_0x023d:
                int r3 = r4.readInt()
                int r3 = r2.clearKeyboardEncryptData(r0, r3)
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x024c:
                java.lang.String r3 = "com.unionpay.tsmservice.mi.ITsmService"
                r4.enforceInterface(r3)
                int r3 = r2.hideKeyboard()
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x025c:
                java.lang.String r3 = "com.unionpay.tsmservice.mi.ITsmService"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                int r3 = r2.clearEncryptData(r3)
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x0270:
                java.lang.String r3 = "com.unionpay.tsmservice.mi.ITsmService"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x0284
                android.os.Parcelable$Creator r3 = com.unionpay.tsmservice.mi.request.GetEncryptDataRequestParams.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                r0 = r3
                com.unionpay.tsmservice.mi.request.GetEncryptDataRequestParams r0 = (com.unionpay.tsmservice.mi.request.GetEncryptDataRequestParams) r0
            L_0x0284:
                android.os.IBinder r3 = r4.readStrongBinder()
                com.unionpay.tsmservice.mi.ITsmCallback r3 = com.unionpay.tsmservice.mi.ITsmCallback.Stub.asInterface(r3)
                int r3 = r2.getEncryptData(r0, r3)
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x0297:
                java.lang.String r3 = "com.unionpay.tsmservice.mi.ITsmService"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x02ab
                android.os.Parcelable$Creator r3 = com.unionpay.tsmservice.mi.request.SafetyKeyboardRequestParams.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                r0 = r3
                com.unionpay.tsmservice.mi.request.SafetyKeyboardRequestParams r0 = (com.unionpay.tsmservice.mi.request.SafetyKeyboardRequestParams) r0
            L_0x02ab:
                int r3 = r2.setSafetyKeyboardBitmap(r0)
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x02b6:
                java.lang.String r3 = "com.unionpay.tsmservice.mi.ITsmService"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x02ca
                android.os.Parcelable$Creator r3 = com.unionpay.tsmservice.mi.request.SafetyKeyboardRequestParams.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                r0 = r3
                com.unionpay.tsmservice.mi.request.SafetyKeyboardRequestParams r0 = (com.unionpay.tsmservice.mi.request.SafetyKeyboardRequestParams) r0
            L_0x02ca:
                int r3 = r4.readInt()
                android.os.IBinder r6 = r4.readStrongBinder()
                com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback r6 = com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback.Stub.asInterface(r6)
                android.os.IBinder r4 = r4.readStrongBinder()
                com.unionpay.tsmservice.mi.ITsmActivityCallback r4 = com.unionpay.tsmservice.mi.ITsmActivityCallback.Stub.asInterface(r4)
                int r3 = r2.showSafetyKeyboard(r0, r3, r6, r4)
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x02e9:
                java.lang.String r3 = "com.unionpay.tsmservice.mi.ITsmService"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x02fd
                android.os.Parcelable$Creator r3 = com.unionpay.tsmservice.mi.request.EncryptDataRequestParams.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                r0 = r3
                com.unionpay.tsmservice.mi.request.EncryptDataRequestParams r0 = (com.unionpay.tsmservice.mi.request.EncryptDataRequestParams) r0
            L_0x02fd:
                android.os.IBinder r3 = r4.readStrongBinder()
                com.unionpay.tsmservice.mi.ITsmCallback r3 = com.unionpay.tsmservice.mi.ITsmCallback.Stub.asInterface(r3)
                int r3 = r2.encryptData(r0, r3)
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x0310:
                java.lang.String r3 = "com.unionpay.tsmservice.mi.ITsmService"
                r4.enforceInterface(r3)
                java.lang.String r3 = r4.readString()
                int r4 = r4.readInt()
                if (r4 >= 0) goto L_0x0320
                goto L_0x0322
            L_0x0320:
                java.lang.String[] r0 = new java.lang.String[r4]
            L_0x0322:
                int r3 = r2.exchangeKey(r3, r0)
                r5.writeNoException()
                r5.writeInt(r3)
                r5.writeStringArray(r0)
                return r1
            L_0x0330:
                java.lang.String r3 = "com.unionpay.tsmservice.mi.ITsmService"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                int r4 = r4.readInt()
                if (r4 >= 0) goto L_0x0340
                goto L_0x0342
            L_0x0340:
                java.lang.String[] r0 = new java.lang.String[r4]
            L_0x0342:
                int r3 = r2.getPubKey(r3, r0)
                r5.writeNoException()
                r5.writeInt(r3)
                r5.writeStringArray(r0)
                return r1
            L_0x0350:
                java.lang.String r3 = "com.unionpay.tsmservice.mi.ITsmService"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x0364
                android.os.Parcelable$Creator r3 = com.unionpay.tsmservice.mi.request.InitRequestParams.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                r0 = r3
                com.unionpay.tsmservice.mi.request.InitRequestParams r0 = (com.unionpay.tsmservice.mi.request.InitRequestParams) r0
            L_0x0364:
                android.os.IBinder r3 = r4.readStrongBinder()
                com.unionpay.tsmservice.mi.ITsmCallback r3 = com.unionpay.tsmservice.mi.ITsmCallback.Stub.asInterface(r3)
                int r3 = r2.init(r0, r3)
                r5.writeNoException()
                r5.writeInt(r3)
                return r1
            L_0x0377:
                java.lang.String r3 = "com.unionpay.tsmservice.mi.ITsmService"
                r5.writeString(r3)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.unionpay.tsmservice.mi.ITsmService.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    int acquireSEAppList(AcquireSEAppListRequestParams acquireSEAppListRequestParams, ITsmCallback iTsmCallback);

    int addCardToVendorPay(AddCardToVendorPayRequestParams addCardToVendorPayRequestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback);

    int cancelPay(CancelPayRequestParams cancelPayRequestParams);

    int cardListStatusChanged(CardListStatusChangedRequestParams cardListStatusChangedRequestParams, ITsmCallback iTsmCallback);

    int clearEncryptData(int i);

    int clearKeyboardEncryptData(ClearEncryptDataRequestParams clearEncryptDataRequestParams, int i);

    int encryptData(EncryptDataRequestParams encryptDataRequestParams, ITsmCallback iTsmCallback);

    int exchangeKey(String str, String[] strArr);

    int getEncryptData(GetEncryptDataRequestParams getEncryptDataRequestParams, ITsmCallback iTsmCallback);

    int getMessageDetails(GetMessageDetailsRequestParams getMessageDetailsRequestParams, ITsmCallback iTsmCallback);

    int getPubKey(int i, String[] strArr);

    int getSEId(GetSeIdRequestParams getSeIdRequestParams, ITsmCallback iTsmCallback);

    int getTransactionDetails(GetTransactionDetailsRequestParams getTransactionDetailsRequestParams, ITsmCallback iTsmCallback);

    int getVendorPayStatus(GetVendorPayStatusRequestParams getVendorPayStatusRequestParams, ITsmCallback iTsmCallback);

    int getVendorPayStatusForBankApp(GetVendorPayStatusRequestParams getVendorPayStatusRequestParams, ITsmCallback iTsmCallback);

    int hideKeyboard();

    int hideSafetyKeyboard(HideSafetyKeyboardRequestParams hideSafetyKeyboardRequestParams);

    int init(InitRequestParams initRequestParams, ITsmCallback iTsmCallback);

    int onlinePaymentVerify(OnlinePaymentVerifyRequestParams onlinePaymentVerifyRequestParams, ITsmCallback iTsmCallback);

    int payResultNotify(PayResultNotifyRequestParams payResultNotifyRequestParams, ITsmCallback iTsmCallback);

    int pinRequest(PinRequestRequestParams pinRequestRequestParams, ITsmCallback iTsmCallback);

    int queryVendorPayStatus(QueryVendorPayStatusRequestParams queryVendorPayStatusRequestParams, ITsmCallback iTsmCallback);

    int setSafetyKeyboardBitmap(SafetyKeyboardRequestParams safetyKeyboardRequestParams);

    int showSafetyKeyboard(SafetyKeyboardRequestParams safetyKeyboardRequestParams, int i, OnSafetyKeyboardCallback onSafetyKeyboardCallback, ITsmActivityCallback iTsmActivityCallback);
}
