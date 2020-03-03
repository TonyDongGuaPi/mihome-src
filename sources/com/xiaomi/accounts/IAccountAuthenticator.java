package com.xiaomi.accounts;

import android.accounts.Account;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IAccountAuthenticator extends IInterface {
    void addAccount(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, String str, String str2, String[] strArr, Bundle bundle) throws RemoteException;

    void confirmCredentials(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, Account account, Bundle bundle) throws RemoteException;

    void editProperties(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, String str) throws RemoteException;

    void getAccountRemovalAllowed(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, Account account) throws RemoteException;

    void getAuthToken(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, Account account, String str, Bundle bundle) throws RemoteException;

    void getAuthTokenLabel(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, String str) throws RemoteException;

    void hasFeatures(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, Account account, String[] strArr) throws RemoteException;

    void updateCredentials(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, Account account, String str, Bundle bundle) throws RemoteException;

    public static abstract class Stub extends Binder implements IAccountAuthenticator {
        private static final String DESCRIPTOR = "com.xiaomi.accounts.IAccountAuthenticator";
        static final int TRANSACTION_addAccount = 1;
        static final int TRANSACTION_confirmCredentials = 2;
        static final int TRANSACTION_editProperties = 6;
        static final int TRANSACTION_getAccountRemovalAllowed = 8;
        static final int TRANSACTION_getAuthToken = 3;
        static final int TRANSACTION_getAuthTokenLabel = 4;
        static final int TRANSACTION_hasFeatures = 7;
        static final int TRANSACTION_updateCredentials = 5;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAccountAuthenticator asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IAccountAuthenticator)) {
                return new Proxy(iBinder);
            }
            return (IAccountAuthenticator) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v16, resolved type: android.accounts.Account} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v19, resolved type: android.accounts.Account} */
        /* JADX WARNING: type inference failed for: r0v1 */
        /* JADX WARNING: type inference failed for: r0v2 */
        /* JADX WARNING: type inference failed for: r0v22 */
        /* JADX WARNING: type inference failed for: r0v23 */
        /* JADX WARNING: type inference failed for: r0v24 */
        /* JADX WARNING: type inference failed for: r0v25 */
        /* JADX WARNING: type inference failed for: r0v26 */
        /* JADX WARNING: type inference failed for: r0v27 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r9, android.os.Parcel r10, android.os.Parcel r11, int r12) throws android.os.RemoteException {
            /*
                r8 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                if (r9 == r0) goto L_0x0143
                r0 = 0
                switch(r9) {
                    case 1: goto L_0x0115;
                    case 2: goto L_0x00e5;
                    case 3: goto L_0x00b1;
                    case 4: goto L_0x009c;
                    case 5: goto L_0x0068;
                    case 6: goto L_0x0053;
                    case 7: goto L_0x002f;
                    case 8: goto L_0x000f;
                    default: goto L_0x000a;
                }
            L_0x000a:
                boolean r9 = super.onTransact(r9, r10, r11, r12)
                return r9
            L_0x000f:
                java.lang.String r9 = "com.xiaomi.accounts.IAccountAuthenticator"
                r10.enforceInterface(r9)
                android.os.IBinder r9 = r10.readStrongBinder()
                com.xiaomi.accounts.IAccountAuthenticatorResponse r9 = com.xiaomi.accounts.IAccountAuthenticatorResponse.Stub.asInterface(r9)
                int r11 = r10.readInt()
                if (r11 == 0) goto L_0x002b
                android.os.Parcelable$Creator r11 = android.accounts.Account.CREATOR
                java.lang.Object r10 = r11.createFromParcel(r10)
                r0 = r10
                android.accounts.Account r0 = (android.accounts.Account) r0
            L_0x002b:
                r8.getAccountRemovalAllowed(r9, r0)
                return r1
            L_0x002f:
                java.lang.String r9 = "com.xiaomi.accounts.IAccountAuthenticator"
                r10.enforceInterface(r9)
                android.os.IBinder r9 = r10.readStrongBinder()
                com.xiaomi.accounts.IAccountAuthenticatorResponse r9 = com.xiaomi.accounts.IAccountAuthenticatorResponse.Stub.asInterface(r9)
                int r11 = r10.readInt()
                if (r11 == 0) goto L_0x004b
                android.os.Parcelable$Creator r11 = android.accounts.Account.CREATOR
                java.lang.Object r11 = r11.createFromParcel(r10)
                r0 = r11
                android.accounts.Account r0 = (android.accounts.Account) r0
            L_0x004b:
                java.lang.String[] r10 = r10.createStringArray()
                r8.hasFeatures(r9, r0, r10)
                return r1
            L_0x0053:
                java.lang.String r9 = "com.xiaomi.accounts.IAccountAuthenticator"
                r10.enforceInterface(r9)
                android.os.IBinder r9 = r10.readStrongBinder()
                com.xiaomi.accounts.IAccountAuthenticatorResponse r9 = com.xiaomi.accounts.IAccountAuthenticatorResponse.Stub.asInterface(r9)
                java.lang.String r10 = r10.readString()
                r8.editProperties(r9, r10)
                return r1
            L_0x0068:
                java.lang.String r9 = "com.xiaomi.accounts.IAccountAuthenticator"
                r10.enforceInterface(r9)
                android.os.IBinder r9 = r10.readStrongBinder()
                com.xiaomi.accounts.IAccountAuthenticatorResponse r9 = com.xiaomi.accounts.IAccountAuthenticatorResponse.Stub.asInterface(r9)
                int r11 = r10.readInt()
                if (r11 == 0) goto L_0x0084
                android.os.Parcelable$Creator r11 = android.accounts.Account.CREATOR
                java.lang.Object r11 = r11.createFromParcel(r10)
                android.accounts.Account r11 = (android.accounts.Account) r11
                goto L_0x0085
            L_0x0084:
                r11 = r0
            L_0x0085:
                java.lang.String r12 = r10.readString()
                int r2 = r10.readInt()
                if (r2 == 0) goto L_0x0098
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r10 = r0.createFromParcel(r10)
                r0 = r10
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0098:
                r8.updateCredentials(r9, r11, r12, r0)
                return r1
            L_0x009c:
                java.lang.String r9 = "com.xiaomi.accounts.IAccountAuthenticator"
                r10.enforceInterface(r9)
                android.os.IBinder r9 = r10.readStrongBinder()
                com.xiaomi.accounts.IAccountAuthenticatorResponse r9 = com.xiaomi.accounts.IAccountAuthenticatorResponse.Stub.asInterface(r9)
                java.lang.String r10 = r10.readString()
                r8.getAuthTokenLabel(r9, r10)
                return r1
            L_0x00b1:
                java.lang.String r9 = "com.xiaomi.accounts.IAccountAuthenticator"
                r10.enforceInterface(r9)
                android.os.IBinder r9 = r10.readStrongBinder()
                com.xiaomi.accounts.IAccountAuthenticatorResponse r9 = com.xiaomi.accounts.IAccountAuthenticatorResponse.Stub.asInterface(r9)
                int r11 = r10.readInt()
                if (r11 == 0) goto L_0x00cd
                android.os.Parcelable$Creator r11 = android.accounts.Account.CREATOR
                java.lang.Object r11 = r11.createFromParcel(r10)
                android.accounts.Account r11 = (android.accounts.Account) r11
                goto L_0x00ce
            L_0x00cd:
                r11 = r0
            L_0x00ce:
                java.lang.String r12 = r10.readString()
                int r2 = r10.readInt()
                if (r2 == 0) goto L_0x00e1
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r10 = r0.createFromParcel(r10)
                r0 = r10
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x00e1:
                r8.getAuthToken(r9, r11, r12, r0)
                return r1
            L_0x00e5:
                java.lang.String r9 = "com.xiaomi.accounts.IAccountAuthenticator"
                r10.enforceInterface(r9)
                android.os.IBinder r9 = r10.readStrongBinder()
                com.xiaomi.accounts.IAccountAuthenticatorResponse r9 = com.xiaomi.accounts.IAccountAuthenticatorResponse.Stub.asInterface(r9)
                int r11 = r10.readInt()
                if (r11 == 0) goto L_0x0101
                android.os.Parcelable$Creator r11 = android.accounts.Account.CREATOR
                java.lang.Object r11 = r11.createFromParcel(r10)
                android.accounts.Account r11 = (android.accounts.Account) r11
                goto L_0x0102
            L_0x0101:
                r11 = r0
            L_0x0102:
                int r12 = r10.readInt()
                if (r12 == 0) goto L_0x0111
                android.os.Parcelable$Creator r12 = android.os.Bundle.CREATOR
                java.lang.Object r10 = r12.createFromParcel(r10)
                r0 = r10
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0111:
                r8.confirmCredentials(r9, r11, r0)
                return r1
            L_0x0115:
                java.lang.String r9 = "com.xiaomi.accounts.IAccountAuthenticator"
                r10.enforceInterface(r9)
                android.os.IBinder r9 = r10.readStrongBinder()
                com.xiaomi.accounts.IAccountAuthenticatorResponse r3 = com.xiaomi.accounts.IAccountAuthenticatorResponse.Stub.asInterface(r9)
                java.lang.String r4 = r10.readString()
                java.lang.String r5 = r10.readString()
                java.lang.String[] r6 = r10.createStringArray()
                int r9 = r10.readInt()
                if (r9 == 0) goto L_0x013d
                android.os.Parcelable$Creator r9 = android.os.Bundle.CREATOR
                java.lang.Object r9 = r9.createFromParcel(r10)
                r0 = r9
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x013d:
                r7 = r0
                r2 = r8
                r2.addAccount(r3, r4, r5, r6, r7)
                return r1
            L_0x0143:
                java.lang.String r9 = "com.xiaomi.accounts.IAccountAuthenticator"
                r11.writeString(r9)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.accounts.IAccountAuthenticator.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IAccountAuthenticator {
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

            public void addAccount(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, String str, String str2, String[] strArr, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iAccountAuthenticatorResponse != null ? iAccountAuthenticatorResponse.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStringArray(strArr);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(1, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void confirmCredentials(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, Account account, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iAccountAuthenticatorResponse != null ? iAccountAuthenticatorResponse.asBinder() : null);
                    if (account != null) {
                        obtain.writeInt(1);
                        account.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void getAuthToken(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, Account account, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iAccountAuthenticatorResponse != null ? iAccountAuthenticatorResponse.asBinder() : null);
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
                    this.mRemote.transact(3, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void getAuthTokenLabel(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iAccountAuthenticatorResponse != null ? iAccountAuthenticatorResponse.asBinder() : null);
                    obtain.writeString(str);
                    this.mRemote.transact(4, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void updateCredentials(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, Account account, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iAccountAuthenticatorResponse != null ? iAccountAuthenticatorResponse.asBinder() : null);
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
                    this.mRemote.transact(5, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void editProperties(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iAccountAuthenticatorResponse != null ? iAccountAuthenticatorResponse.asBinder() : null);
                    obtain.writeString(str);
                    this.mRemote.transact(6, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void hasFeatures(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, Account account, String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iAccountAuthenticatorResponse != null ? iAccountAuthenticatorResponse.asBinder() : null);
                    if (account != null) {
                        obtain.writeInt(1);
                        account.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStringArray(strArr);
                    this.mRemote.transact(7, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void getAccountRemovalAllowed(IAccountAuthenticatorResponse iAccountAuthenticatorResponse, Account account) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iAccountAuthenticatorResponse != null ? iAccountAuthenticatorResponse.asBinder() : null);
                    if (account != null) {
                        obtain.writeInt(1);
                        account.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(8, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }
    }
}
