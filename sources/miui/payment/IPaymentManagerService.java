package miui.payment;

import android.accounts.Account;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IPaymentManagerService extends IInterface {
    void getMiliBalance(IPaymentManagerResponse iPaymentManagerResponse, Account account, String str, String str2) throws RemoteException;

    void payForOrder(IPaymentManagerResponse iPaymentManagerResponse, Account account, String str, Bundle bundle) throws RemoteException;

    void recharge(IPaymentManagerResponse iPaymentManagerResponse, Account account, String str, String str2) throws RemoteException;

    void showMiliCenter(IPaymentManagerResponse iPaymentManagerResponse, Account account) throws RemoteException;

    void showPayRecord(IPaymentManagerResponse iPaymentManagerResponse, Account account, String str, String str2) throws RemoteException;

    void showRechargeRecord(IPaymentManagerResponse iPaymentManagerResponse, Account account, String str, String str2) throws RemoteException;

    public static abstract class Stub extends Binder implements IPaymentManagerService {
        private static final String DESCRIPTOR = "miui.payment.IPaymentManagerService";
        static final int TRANSACTION_getMiliBalance = 6;
        static final int TRANSACTION_payForOrder = 1;
        static final int TRANSACTION_recharge = 5;
        static final int TRANSACTION_showMiliCenter = 2;
        static final int TRANSACTION_showPayRecord = 4;
        static final int TRANSACTION_showRechargeRecord = 3;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPaymentManagerService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IPaymentManagerService)) {
                return new Proxy(iBinder);
            }
            return (IPaymentManagerService) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: android.accounts.Account} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: android.accounts.Account} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: android.accounts.Account} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v15, resolved type: android.accounts.Account} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v18, resolved type: android.accounts.Account} */
        /* JADX WARNING: type inference failed for: r0v1 */
        /* JADX WARNING: type inference failed for: r0v21 */
        /* JADX WARNING: type inference failed for: r0v22 */
        /* JADX WARNING: type inference failed for: r0v23 */
        /* JADX WARNING: type inference failed for: r0v24 */
        /* JADX WARNING: type inference failed for: r0v25 */
        /* JADX WARNING: type inference failed for: r0v26 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r5, android.os.Parcel r6, android.os.Parcel r7, int r8) throws android.os.RemoteException {
            /*
                r4 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                if (r5 == r0) goto L_0x0115
                r0 = 0
                switch(r5) {
                    case 1: goto L_0x00de;
                    case 2: goto L_0x00bb;
                    case 3: goto L_0x0090;
                    case 4: goto L_0x0065;
                    case 5: goto L_0x003a;
                    case 6: goto L_0x000f;
                    default: goto L_0x000a;
                }
            L_0x000a:
                boolean r5 = super.onTransact(r5, r6, r7, r8)
                return r5
            L_0x000f:
                java.lang.String r5 = "miui.payment.IPaymentManagerService"
                r6.enforceInterface(r5)
                android.os.IBinder r5 = r6.readStrongBinder()
                miui.payment.IPaymentManagerResponse r5 = miui.payment.IPaymentManagerResponse.Stub.asInterface(r5)
                int r8 = r6.readInt()
                if (r8 == 0) goto L_0x002b
                android.os.Parcelable$Creator r8 = android.accounts.Account.CREATOR
                java.lang.Object r8 = r8.createFromParcel(r6)
                r0 = r8
                android.accounts.Account r0 = (android.accounts.Account) r0
            L_0x002b:
                java.lang.String r8 = r6.readString()
                java.lang.String r6 = r6.readString()
                r4.getMiliBalance(r5, r0, r8, r6)
                r7.writeNoException()
                return r1
            L_0x003a:
                java.lang.String r5 = "miui.payment.IPaymentManagerService"
                r6.enforceInterface(r5)
                android.os.IBinder r5 = r6.readStrongBinder()
                miui.payment.IPaymentManagerResponse r5 = miui.payment.IPaymentManagerResponse.Stub.asInterface(r5)
                int r8 = r6.readInt()
                if (r8 == 0) goto L_0x0056
                android.os.Parcelable$Creator r8 = android.accounts.Account.CREATOR
                java.lang.Object r8 = r8.createFromParcel(r6)
                r0 = r8
                android.accounts.Account r0 = (android.accounts.Account) r0
            L_0x0056:
                java.lang.String r8 = r6.readString()
                java.lang.String r6 = r6.readString()
                r4.recharge(r5, r0, r8, r6)
                r7.writeNoException()
                return r1
            L_0x0065:
                java.lang.String r5 = "miui.payment.IPaymentManagerService"
                r6.enforceInterface(r5)
                android.os.IBinder r5 = r6.readStrongBinder()
                miui.payment.IPaymentManagerResponse r5 = miui.payment.IPaymentManagerResponse.Stub.asInterface(r5)
                int r8 = r6.readInt()
                if (r8 == 0) goto L_0x0081
                android.os.Parcelable$Creator r8 = android.accounts.Account.CREATOR
                java.lang.Object r8 = r8.createFromParcel(r6)
                r0 = r8
                android.accounts.Account r0 = (android.accounts.Account) r0
            L_0x0081:
                java.lang.String r8 = r6.readString()
                java.lang.String r6 = r6.readString()
                r4.showPayRecord(r5, r0, r8, r6)
                r7.writeNoException()
                return r1
            L_0x0090:
                java.lang.String r5 = "miui.payment.IPaymentManagerService"
                r6.enforceInterface(r5)
                android.os.IBinder r5 = r6.readStrongBinder()
                miui.payment.IPaymentManagerResponse r5 = miui.payment.IPaymentManagerResponse.Stub.asInterface(r5)
                int r8 = r6.readInt()
                if (r8 == 0) goto L_0x00ac
                android.os.Parcelable$Creator r8 = android.accounts.Account.CREATOR
                java.lang.Object r8 = r8.createFromParcel(r6)
                r0 = r8
                android.accounts.Account r0 = (android.accounts.Account) r0
            L_0x00ac:
                java.lang.String r8 = r6.readString()
                java.lang.String r6 = r6.readString()
                r4.showRechargeRecord(r5, r0, r8, r6)
                r7.writeNoException()
                return r1
            L_0x00bb:
                java.lang.String r5 = "miui.payment.IPaymentManagerService"
                r6.enforceInterface(r5)
                android.os.IBinder r5 = r6.readStrongBinder()
                miui.payment.IPaymentManagerResponse r5 = miui.payment.IPaymentManagerResponse.Stub.asInterface(r5)
                int r8 = r6.readInt()
                if (r8 == 0) goto L_0x00d7
                android.os.Parcelable$Creator r8 = android.accounts.Account.CREATOR
                java.lang.Object r6 = r8.createFromParcel(r6)
                r0 = r6
                android.accounts.Account r0 = (android.accounts.Account) r0
            L_0x00d7:
                r4.showMiliCenter(r5, r0)
                r7.writeNoException()
                return r1
            L_0x00de:
                java.lang.String r5 = "miui.payment.IPaymentManagerService"
                r6.enforceInterface(r5)
                android.os.IBinder r5 = r6.readStrongBinder()
                miui.payment.IPaymentManagerResponse r5 = miui.payment.IPaymentManagerResponse.Stub.asInterface(r5)
                int r8 = r6.readInt()
                if (r8 == 0) goto L_0x00fa
                android.os.Parcelable$Creator r8 = android.accounts.Account.CREATOR
                java.lang.Object r8 = r8.createFromParcel(r6)
                android.accounts.Account r8 = (android.accounts.Account) r8
                goto L_0x00fb
            L_0x00fa:
                r8 = r0
            L_0x00fb:
                java.lang.String r2 = r6.readString()
                int r3 = r6.readInt()
                if (r3 == 0) goto L_0x010e
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r6 = r0.createFromParcel(r6)
                r0 = r6
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x010e:
                r4.payForOrder(r5, r8, r2, r0)
                r7.writeNoException()
                return r1
            L_0x0115:
                java.lang.String r5 = "miui.payment.IPaymentManagerService"
                r7.writeString(r5)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: miui.payment.IPaymentManagerService.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IPaymentManagerService {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public void payForOrder(IPaymentManagerResponse iPaymentManagerResponse, Account account, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iPaymentManagerResponse != null ? iPaymentManagerResponse.asBinder() : null);
                    if (account != null) {
                        obtain.writeInt(1);
                        account.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void showMiliCenter(IPaymentManagerResponse iPaymentManagerResponse, Account account) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iPaymentManagerResponse != null ? iPaymentManagerResponse.asBinder() : null);
                    if (account != null) {
                        obtain.writeInt(1);
                        account.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void showRechargeRecord(IPaymentManagerResponse iPaymentManagerResponse, Account account, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iPaymentManagerResponse != null ? iPaymentManagerResponse.asBinder() : null);
                    if (account != null) {
                        obtain.writeInt(1);
                        account.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void showPayRecord(IPaymentManagerResponse iPaymentManagerResponse, Account account, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iPaymentManagerResponse != null ? iPaymentManagerResponse.asBinder() : null);
                    if (account != null) {
                        obtain.writeInt(1);
                        account.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void recharge(IPaymentManagerResponse iPaymentManagerResponse, Account account, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iPaymentManagerResponse != null ? iPaymentManagerResponse.asBinder() : null);
                    if (account != null) {
                        obtain.writeInt(1);
                        account.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getMiliBalance(IPaymentManagerResponse iPaymentManagerResponse, Account account, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iPaymentManagerResponse != null ? iPaymentManagerResponse.asBinder() : null);
                    if (account != null) {
                        obtain.writeInt(1);
                        account.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
