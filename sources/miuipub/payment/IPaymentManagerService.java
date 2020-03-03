package miuipub.payment;

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

    void payOrder(IPaymentManagerResponse iPaymentManagerResponse, Account account, String str, String str2, boolean z, boolean z2, boolean z3, Bundle bundle) throws RemoteException;

    void recharge(IPaymentManagerResponse iPaymentManagerResponse, Account account, String str, String str2) throws RemoteException;

    void showMiliCenter(IPaymentManagerResponse iPaymentManagerResponse, Account account) throws RemoteException;

    void showPayRecord(IPaymentManagerResponse iPaymentManagerResponse, Account account, String str, String str2) throws RemoteException;

    void showRechargeRecord(IPaymentManagerResponse iPaymentManagerResponse, Account account, String str, String str2) throws RemoteException;

    public static abstract class Stub extends Binder implements IPaymentManagerService {
        private static final String DESCRIPTOR = "miuipub.payment.IPaymentManagerService";
        static final int TRANSACTION_getMiliBalance = 7;
        static final int TRANSACTION_payForOrder = 1;
        static final int TRANSACTION_payOrder = 2;
        static final int TRANSACTION_recharge = 6;
        static final int TRANSACTION_showMiliCenter = 3;
        static final int TRANSACTION_showPayRecord = 5;
        static final int TRANSACTION_showRechargeRecord = 4;

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

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: android.accounts.Account} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: android.accounts.Account} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v13, resolved type: android.accounts.Account} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v16, resolved type: android.accounts.Account} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v19, resolved type: android.accounts.Account} */
        /* JADX WARNING: type inference failed for: r3v1 */
        /* JADX WARNING: type inference failed for: r3v22 */
        /* JADX WARNING: type inference failed for: r3v23 */
        /* JADX WARNING: type inference failed for: r3v24 */
        /* JADX WARNING: type inference failed for: r3v25 */
        /* JADX WARNING: type inference failed for: r3v26 */
        /* JADX WARNING: type inference failed for: r3v27 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r14, android.os.Parcel r15, android.os.Parcel r16, int r17) throws android.os.RemoteException {
            /*
                r13 = this;
                r9 = r13
                r0 = r14
                r1 = r15
                java.lang.String r2 = "miuipub.payment.IPaymentManagerService"
                r3 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r10 = 1
                if (r0 == r3) goto L_0x016c
                r3 = 0
                switch(r0) {
                    case 1: goto L_0x0137;
                    case 2: goto L_0x00d9;
                    case 3: goto L_0x00b8;
                    case 4: goto L_0x008f;
                    case 5: goto L_0x0066;
                    case 6: goto L_0x003d;
                    case 7: goto L_0x0014;
                    default: goto L_0x000f;
                }
            L_0x000f:
                boolean r0 = super.onTransact(r14, r15, r16, r17)
                return r0
            L_0x0014:
                r15.enforceInterface(r2)
                android.os.IBinder r0 = r15.readStrongBinder()
                miuipub.payment.IPaymentManagerResponse r0 = miuipub.payment.IPaymentManagerResponse.Stub.asInterface(r0)
                int r2 = r15.readInt()
                if (r2 == 0) goto L_0x002e
                android.os.Parcelable$Creator r2 = android.accounts.Account.CREATOR
                java.lang.Object r2 = r2.createFromParcel(r15)
                r3 = r2
                android.accounts.Account r3 = (android.accounts.Account) r3
            L_0x002e:
                java.lang.String r2 = r15.readString()
                java.lang.String r1 = r15.readString()
                r13.getMiliBalance(r0, r3, r2, r1)
                r16.writeNoException()
                return r10
            L_0x003d:
                r15.enforceInterface(r2)
                android.os.IBinder r0 = r15.readStrongBinder()
                miuipub.payment.IPaymentManagerResponse r0 = miuipub.payment.IPaymentManagerResponse.Stub.asInterface(r0)
                int r2 = r15.readInt()
                if (r2 == 0) goto L_0x0057
                android.os.Parcelable$Creator r2 = android.accounts.Account.CREATOR
                java.lang.Object r2 = r2.createFromParcel(r15)
                r3 = r2
                android.accounts.Account r3 = (android.accounts.Account) r3
            L_0x0057:
                java.lang.String r2 = r15.readString()
                java.lang.String r1 = r15.readString()
                r13.recharge(r0, r3, r2, r1)
                r16.writeNoException()
                return r10
            L_0x0066:
                r15.enforceInterface(r2)
                android.os.IBinder r0 = r15.readStrongBinder()
                miuipub.payment.IPaymentManagerResponse r0 = miuipub.payment.IPaymentManagerResponse.Stub.asInterface(r0)
                int r2 = r15.readInt()
                if (r2 == 0) goto L_0x0080
                android.os.Parcelable$Creator r2 = android.accounts.Account.CREATOR
                java.lang.Object r2 = r2.createFromParcel(r15)
                r3 = r2
                android.accounts.Account r3 = (android.accounts.Account) r3
            L_0x0080:
                java.lang.String r2 = r15.readString()
                java.lang.String r1 = r15.readString()
                r13.showPayRecord(r0, r3, r2, r1)
                r16.writeNoException()
                return r10
            L_0x008f:
                r15.enforceInterface(r2)
                android.os.IBinder r0 = r15.readStrongBinder()
                miuipub.payment.IPaymentManagerResponse r0 = miuipub.payment.IPaymentManagerResponse.Stub.asInterface(r0)
                int r2 = r15.readInt()
                if (r2 == 0) goto L_0x00a9
                android.os.Parcelable$Creator r2 = android.accounts.Account.CREATOR
                java.lang.Object r2 = r2.createFromParcel(r15)
                r3 = r2
                android.accounts.Account r3 = (android.accounts.Account) r3
            L_0x00a9:
                java.lang.String r2 = r15.readString()
                java.lang.String r1 = r15.readString()
                r13.showRechargeRecord(r0, r3, r2, r1)
                r16.writeNoException()
                return r10
            L_0x00b8:
                r15.enforceInterface(r2)
                android.os.IBinder r0 = r15.readStrongBinder()
                miuipub.payment.IPaymentManagerResponse r0 = miuipub.payment.IPaymentManagerResponse.Stub.asInterface(r0)
                int r2 = r15.readInt()
                if (r2 == 0) goto L_0x00d2
                android.os.Parcelable$Creator r2 = android.accounts.Account.CREATOR
                java.lang.Object r1 = r2.createFromParcel(r15)
                r3 = r1
                android.accounts.Account r3 = (android.accounts.Account) r3
            L_0x00d2:
                r13.showMiliCenter(r0, r3)
                r16.writeNoException()
                return r10
            L_0x00d9:
                r15.enforceInterface(r2)
                android.os.IBinder r0 = r15.readStrongBinder()
                miuipub.payment.IPaymentManagerResponse r2 = miuipub.payment.IPaymentManagerResponse.Stub.asInterface(r0)
                int r0 = r15.readInt()
                if (r0 == 0) goto L_0x00f4
                android.os.Parcelable$Creator r0 = android.accounts.Account.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r15)
                android.accounts.Account r0 = (android.accounts.Account) r0
                r4 = r0
                goto L_0x00f5
            L_0x00f4:
                r4 = r3
            L_0x00f5:
                java.lang.String r5 = r15.readString()
                java.lang.String r6 = r15.readString()
                int r0 = r15.readInt()
                r7 = 0
                if (r0 == 0) goto L_0x0106
                r8 = 1
                goto L_0x0107
            L_0x0106:
                r8 = 0
            L_0x0107:
                int r0 = r15.readInt()
                if (r0 == 0) goto L_0x010f
                r11 = 1
                goto L_0x0110
            L_0x010f:
                r11 = 0
            L_0x0110:
                int r0 = r15.readInt()
                if (r0 == 0) goto L_0x0117
                r7 = 1
            L_0x0117:
                int r0 = r15.readInt()
                if (r0 == 0) goto L_0x0127
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r15)
                android.os.Bundle r0 = (android.os.Bundle) r0
                r12 = r0
                goto L_0x0128
            L_0x0127:
                r12 = r3
            L_0x0128:
                r0 = r13
                r1 = r2
                r2 = r4
                r3 = r5
                r4 = r6
                r5 = r8
                r6 = r11
                r8 = r12
                r0.payOrder(r1, r2, r3, r4, r5, r6, r7, r8)
                r16.writeNoException()
                return r10
            L_0x0137:
                r15.enforceInterface(r2)
                android.os.IBinder r0 = r15.readStrongBinder()
                miuipub.payment.IPaymentManagerResponse r0 = miuipub.payment.IPaymentManagerResponse.Stub.asInterface(r0)
                int r2 = r15.readInt()
                if (r2 == 0) goto L_0x0151
                android.os.Parcelable$Creator r2 = android.accounts.Account.CREATOR
                java.lang.Object r2 = r2.createFromParcel(r15)
                android.accounts.Account r2 = (android.accounts.Account) r2
                goto L_0x0152
            L_0x0151:
                r2 = r3
            L_0x0152:
                java.lang.String r4 = r15.readString()
                int r5 = r15.readInt()
                if (r5 == 0) goto L_0x0165
                android.os.Parcelable$Creator r3 = android.os.Bundle.CREATOR
                java.lang.Object r1 = r3.createFromParcel(r15)
                r3 = r1
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x0165:
                r13.payForOrder(r0, r2, r4, r3)
                r16.writeNoException()
                return r10
            L_0x016c:
                r0 = r16
                r0.writeString(r2)
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: miuipub.payment.IPaymentManagerService.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
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

            public void payOrder(IPaymentManagerResponse iPaymentManagerResponse, Account account, String str, String str2, boolean z, boolean z2, boolean z3, Bundle bundle) throws RemoteException {
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
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(z2 ? 1 : 0);
                    obtain.writeInt(z3 ? 1 : 0);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
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
                    this.mRemote.transact(3, obtain, obtain2, 0);
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
                    this.mRemote.transact(4, obtain, obtain2, 0);
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
                    this.mRemote.transact(5, obtain, obtain2, 0);
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
                    this.mRemote.transact(6, obtain, obtain2, 0);
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
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
