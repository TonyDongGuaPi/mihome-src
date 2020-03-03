package com.xiaomi.accountsdk.account;

import android.accounts.Account;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public interface IXiaomiAccountService extends IInterface {
    String getAccessToken(Account account, String str, String str2, boolean z) throws RemoteException;

    ParcelFileDescriptor getAvatarFd(Account account) throws RemoteException;

    ParcelFileDescriptor getAvatarFdByFileName(String str) throws RemoteException;

    String getEmail(Account account) throws RemoteException;

    String getEncryptedUserId(Account account) throws RemoteException;

    String getNickName(Account account) throws RemoteException;

    String getPhone(Account account) throws RemoteException;

    String getSnsAccessToken(String str) throws RemoteException;

    String getUserName(Account account) throws RemoteException;

    XiaomiAccount getXiaomiAccount(boolean z, Account account) throws RemoteException;

    boolean invalidateSnsAccessToken(String str, String str2) throws RemoteException;

    public static abstract class Stub extends Binder implements IXiaomiAccountService {
        private static final String DESCRIPTOR = "com.xiaomi.accountsdk.account.IXiaomiAccountService";
        static final int TRANSACTION_getAccessToken = 9;
        static final int TRANSACTION_getAvatarFd = 6;
        static final int TRANSACTION_getAvatarFdByFileName = 8;
        static final int TRANSACTION_getEmail = 3;
        static final int TRANSACTION_getEncryptedUserId = 4;
        static final int TRANSACTION_getNickName = 2;
        static final int TRANSACTION_getPhone = 5;
        static final int TRANSACTION_getSnsAccessToken = 10;
        static final int TRANSACTION_getUserName = 1;
        static final int TRANSACTION_getXiaomiAccount = 7;
        static final int TRANSACTION_invalidateSnsAccessToken = 11;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IXiaomiAccountService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IXiaomiAccountService)) {
                return new Proxy(iBinder);
            }
            return (IXiaomiAccountService) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: android.accounts.Account} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v11, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: android.accounts.Account} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v16, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: android.accounts.Account} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v21, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: android.accounts.Account} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v26, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v14, resolved type: android.accounts.Account} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v31, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v17, resolved type: android.accounts.Account} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v20, resolved type: android.accounts.Account} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v46, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v23, resolved type: android.accounts.Account} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r4, android.os.Parcel r5, android.os.Parcel r6, int r7) throws android.os.RemoteException {
            /*
                r3 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                if (r4 == r0) goto L_0x017b
                r0 = 0
                r2 = 0
                switch(r4) {
                    case 1: goto L_0x015c;
                    case 2: goto L_0x013d;
                    case 3: goto L_0x011e;
                    case 4: goto L_0x00ff;
                    case 5: goto L_0x00e0;
                    case 6: goto L_0x00b8;
                    case 7: goto L_0x0087;
                    case 8: goto L_0x006a;
                    case 9: goto L_0x003c;
                    case 10: goto L_0x0028;
                    case 11: goto L_0x0010;
                    default: goto L_0x000b;
                }
            L_0x000b:
                boolean r4 = super.onTransact(r4, r5, r6, r7)
                return r4
            L_0x0010:
                java.lang.String r4 = "com.xiaomi.accountsdk.account.IXiaomiAccountService"
                r5.enforceInterface(r4)
                java.lang.String r4 = r5.readString()
                java.lang.String r5 = r5.readString()
                boolean r4 = r3.invalidateSnsAccessToken(r4, r5)
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x0028:
                java.lang.String r4 = "com.xiaomi.accountsdk.account.IXiaomiAccountService"
                r5.enforceInterface(r4)
                java.lang.String r4 = r5.readString()
                java.lang.String r4 = r3.getSnsAccessToken(r4)
                r6.writeNoException()
                r6.writeString(r4)
                return r1
            L_0x003c:
                java.lang.String r4 = "com.xiaomi.accountsdk.account.IXiaomiAccountService"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0050
                android.os.Parcelable$Creator r4 = android.accounts.Account.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r2 = r4
                android.accounts.Account r2 = (android.accounts.Account) r2
            L_0x0050:
                java.lang.String r4 = r5.readString()
                java.lang.String r7 = r5.readString()
                int r5 = r5.readInt()
                if (r5 == 0) goto L_0x005f
                r0 = 1
            L_0x005f:
                java.lang.String r4 = r3.getAccessToken(r2, r4, r7, r0)
                r6.writeNoException()
                r6.writeString(r4)
                return r1
            L_0x006a:
                java.lang.String r4 = "com.xiaomi.accountsdk.account.IXiaomiAccountService"
                r5.enforceInterface(r4)
                java.lang.String r4 = r5.readString()
                android.os.ParcelFileDescriptor r4 = r3.getAvatarFdByFileName(r4)
                r6.writeNoException()
                if (r4 == 0) goto L_0x0083
                r6.writeInt(r1)
                r4.writeToParcel(r6, r1)
                goto L_0x0086
            L_0x0083:
                r6.writeInt(r0)
            L_0x0086:
                return r1
            L_0x0087:
                java.lang.String r4 = "com.xiaomi.accountsdk.account.IXiaomiAccountService"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0094
                r4 = 1
                goto L_0x0095
            L_0x0094:
                r4 = 0
            L_0x0095:
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x00a4
                android.os.Parcelable$Creator r7 = android.accounts.Account.CREATOR
                java.lang.Object r5 = r7.createFromParcel(r5)
                r2 = r5
                android.accounts.Account r2 = (android.accounts.Account) r2
            L_0x00a4:
                com.xiaomi.accountsdk.account.XiaomiAccount r4 = r3.getXiaomiAccount(r4, r2)
                r6.writeNoException()
                if (r4 == 0) goto L_0x00b4
                r6.writeInt(r1)
                r4.writeToParcel(r6, r1)
                goto L_0x00b7
            L_0x00b4:
                r6.writeInt(r0)
            L_0x00b7:
                return r1
            L_0x00b8:
                java.lang.String r4 = "com.xiaomi.accountsdk.account.IXiaomiAccountService"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x00cc
                android.os.Parcelable$Creator r4 = android.accounts.Account.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r2 = r4
                android.accounts.Account r2 = (android.accounts.Account) r2
            L_0x00cc:
                android.os.ParcelFileDescriptor r4 = r3.getAvatarFd(r2)
                r6.writeNoException()
                if (r4 == 0) goto L_0x00dc
                r6.writeInt(r1)
                r4.writeToParcel(r6, r1)
                goto L_0x00df
            L_0x00dc:
                r6.writeInt(r0)
            L_0x00df:
                return r1
            L_0x00e0:
                java.lang.String r4 = "com.xiaomi.accountsdk.account.IXiaomiAccountService"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x00f4
                android.os.Parcelable$Creator r4 = android.accounts.Account.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r2 = r4
                android.accounts.Account r2 = (android.accounts.Account) r2
            L_0x00f4:
                java.lang.String r4 = r3.getPhone(r2)
                r6.writeNoException()
                r6.writeString(r4)
                return r1
            L_0x00ff:
                java.lang.String r4 = "com.xiaomi.accountsdk.account.IXiaomiAccountService"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0113
                android.os.Parcelable$Creator r4 = android.accounts.Account.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r2 = r4
                android.accounts.Account r2 = (android.accounts.Account) r2
            L_0x0113:
                java.lang.String r4 = r3.getEncryptedUserId(r2)
                r6.writeNoException()
                r6.writeString(r4)
                return r1
            L_0x011e:
                java.lang.String r4 = "com.xiaomi.accountsdk.account.IXiaomiAccountService"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0132
                android.os.Parcelable$Creator r4 = android.accounts.Account.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r2 = r4
                android.accounts.Account r2 = (android.accounts.Account) r2
            L_0x0132:
                java.lang.String r4 = r3.getEmail(r2)
                r6.writeNoException()
                r6.writeString(r4)
                return r1
            L_0x013d:
                java.lang.String r4 = "com.xiaomi.accountsdk.account.IXiaomiAccountService"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0151
                android.os.Parcelable$Creator r4 = android.accounts.Account.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r2 = r4
                android.accounts.Account r2 = (android.accounts.Account) r2
            L_0x0151:
                java.lang.String r4 = r3.getNickName(r2)
                r6.writeNoException()
                r6.writeString(r4)
                return r1
            L_0x015c:
                java.lang.String r4 = "com.xiaomi.accountsdk.account.IXiaomiAccountService"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0170
                android.os.Parcelable$Creator r4 = android.accounts.Account.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r2 = r4
                android.accounts.Account r2 = (android.accounts.Account) r2
            L_0x0170:
                java.lang.String r4 = r3.getUserName(r2)
                r6.writeNoException()
                r6.writeString(r4)
                return r1
            L_0x017b:
                java.lang.String r4 = "com.xiaomi.accountsdk.account.IXiaomiAccountService"
                r6.writeString(r4)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.accountsdk.account.IXiaomiAccountService.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IXiaomiAccountService {
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

            public String getUserName(Account account) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        obtain.writeInt(1);
                        account.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getNickName(Account account) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        obtain.writeInt(1);
                        account.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getEmail(Account account) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        obtain.writeInt(1);
                        account.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getEncryptedUserId(Account account) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        obtain.writeInt(1);
                        account.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getPhone(Account account) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        obtain.writeInt(1);
                        account.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public ParcelFileDescriptor getAvatarFd(Account account) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        obtain.writeInt(1);
                        account.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public XiaomiAccount getXiaomiAccount(boolean z, Account account) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (account != null) {
                        obtain.writeInt(1);
                        account.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? XiaomiAccount.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public ParcelFileDescriptor getAvatarFdByFileName(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getAccessToken(Account account, String str, String str2, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (account != null) {
                        obtain.writeInt(1);
                        account.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getSnsAccessToken(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean invalidateSnsAccessToken(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    boolean z = false;
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
