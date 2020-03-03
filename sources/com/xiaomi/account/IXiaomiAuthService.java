package com.xiaomi.account;

import android.accounts.Account;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IXiaomiAuthService extends IInterface {
    void getAccessTokenInResponse(IXiaomiAuthResponse iXiaomiAuthResponse, Bundle bundle, int i, int i2) throws RemoteException;

    Bundle getMiCloudAccessToken(Account account, Bundle bundle) throws RemoteException;

    Bundle getMiCloudUserInfo(Account account, Bundle bundle) throws RemoteException;

    Bundle getSnsAccessToken(Account account, Bundle bundle) throws RemoteException;

    int getVersionNum() throws RemoteException;

    void invalidateAccessToken(Account account, Bundle bundle) throws RemoteException;

    boolean isSupport(String str) throws RemoteException;

    boolean supportResponseWay() throws RemoteException;

    public static abstract class Stub extends Binder implements IXiaomiAuthService {
        private static final String DESCRIPTOR = "com.xiaomi.account.IXiaomiAuthService";
        static final int TRANSACTION_getAccessTokenInResponse = 6;
        static final int TRANSACTION_getMiCloudAccessToken = 2;
        static final int TRANSACTION_getMiCloudUserInfo = 1;
        static final int TRANSACTION_getSnsAccessToken = 3;
        static final int TRANSACTION_getVersionNum = 7;
        static final int TRANSACTION_invalidateAccessToken = 4;
        static final int TRANSACTION_isSupport = 8;
        static final int TRANSACTION_supportResponseWay = 5;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IXiaomiAuthService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IXiaomiAuthService)) {
                return new Proxy(iBinder);
            }
            return (IXiaomiAuthService) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v12, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v14, resolved type: android.os.Bundle} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r4, android.os.Parcel r5, android.os.Parcel r6, int r7) throws android.os.RemoteException {
            /*
                r3 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                if (r4 == r0) goto L_0x0142
                r0 = 0
                r2 = 0
                switch(r4) {
                    case 1: goto L_0x010a;
                    case 2: goto L_0x00d2;
                    case 3: goto L_0x009a;
                    case 4: goto L_0x006f;
                    case 5: goto L_0x005f;
                    case 6: goto L_0x0034;
                    case 7: goto L_0x0024;
                    case 8: goto L_0x0010;
                    default: goto L_0x000b;
                }
            L_0x000b:
                boolean r4 = super.onTransact(r4, r5, r6, r7)
                return r4
            L_0x0010:
                java.lang.String r4 = "com.xiaomi.account.IXiaomiAuthService"
                r5.enforceInterface(r4)
                java.lang.String r4 = r5.readString()
                boolean r4 = r3.isSupport(r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x0024:
                java.lang.String r4 = "com.xiaomi.account.IXiaomiAuthService"
                r5.enforceInterface(r4)
                int r4 = r3.getVersionNum()
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x0034:
                java.lang.String r4 = "com.xiaomi.account.IXiaomiAuthService"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.xiaomi.account.IXiaomiAuthResponse r4 = com.xiaomi.account.IXiaomiAuthResponse.Stub.asInterface(r4)
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x0050
                android.os.Parcelable$Creator r7 = android.os.Bundle.CREATOR
                java.lang.Object r7 = r7.createFromParcel(r5)
                r2 = r7
                android.os.Bundle r2 = (android.os.Bundle) r2
            L_0x0050:
                int r7 = r5.readInt()
                int r5 = r5.readInt()
                r3.getAccessTokenInResponse(r4, r2, r7, r5)
                r6.writeNoException()
                return r1
            L_0x005f:
                java.lang.String r4 = "com.xiaomi.account.IXiaomiAuthService"
                r5.enforceInterface(r4)
                boolean r4 = r3.supportResponseWay()
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x006f:
                java.lang.String r4 = "com.xiaomi.account.IXiaomiAuthService"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0083
                android.os.Parcelable$Creator r4 = android.accounts.Account.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                android.accounts.Account r4 = (android.accounts.Account) r4
                goto L_0x0084
            L_0x0083:
                r4 = r2
            L_0x0084:
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x0093
                android.os.Parcelable$Creator r7 = android.os.Bundle.CREATOR
                java.lang.Object r5 = r7.createFromParcel(r5)
                r2 = r5
                android.os.Bundle r2 = (android.os.Bundle) r2
            L_0x0093:
                r3.invalidateAccessToken(r4, r2)
                r6.writeNoException()
                return r1
            L_0x009a:
                java.lang.String r4 = "com.xiaomi.account.IXiaomiAuthService"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x00ae
                android.os.Parcelable$Creator r4 = android.accounts.Account.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                android.accounts.Account r4 = (android.accounts.Account) r4
                goto L_0x00af
            L_0x00ae:
                r4 = r2
            L_0x00af:
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x00be
                android.os.Parcelable$Creator r7 = android.os.Bundle.CREATOR
                java.lang.Object r5 = r7.createFromParcel(r5)
                r2 = r5
                android.os.Bundle r2 = (android.os.Bundle) r2
            L_0x00be:
                android.os.Bundle r4 = r3.getSnsAccessToken(r4, r2)
                r6.writeNoException()
                if (r4 == 0) goto L_0x00ce
                r6.writeInt(r1)
                r4.writeToParcel(r6, r1)
                goto L_0x00d1
            L_0x00ce:
                r6.writeInt(r0)
            L_0x00d1:
                return r1
            L_0x00d2:
                java.lang.String r4 = "com.xiaomi.account.IXiaomiAuthService"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x00e6
                android.os.Parcelable$Creator r4 = android.accounts.Account.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                android.accounts.Account r4 = (android.accounts.Account) r4
                goto L_0x00e7
            L_0x00e6:
                r4 = r2
            L_0x00e7:
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x00f6
                android.os.Parcelable$Creator r7 = android.os.Bundle.CREATOR
                java.lang.Object r5 = r7.createFromParcel(r5)
                r2 = r5
                android.os.Bundle r2 = (android.os.Bundle) r2
            L_0x00f6:
                android.os.Bundle r4 = r3.getMiCloudAccessToken(r4, r2)
                r6.writeNoException()
                if (r4 == 0) goto L_0x0106
                r6.writeInt(r1)
                r4.writeToParcel(r6, r1)
                goto L_0x0109
            L_0x0106:
                r6.writeInt(r0)
            L_0x0109:
                return r1
            L_0x010a:
                java.lang.String r4 = "com.xiaomi.account.IXiaomiAuthService"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x011e
                android.os.Parcelable$Creator r4 = android.accounts.Account.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                android.accounts.Account r4 = (android.accounts.Account) r4
                goto L_0x011f
            L_0x011e:
                r4 = r2
            L_0x011f:
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x012e
                android.os.Parcelable$Creator r7 = android.os.Bundle.CREATOR
                java.lang.Object r5 = r7.createFromParcel(r5)
                r2 = r5
                android.os.Bundle r2 = (android.os.Bundle) r2
            L_0x012e:
                android.os.Bundle r4 = r3.getMiCloudUserInfo(r4, r2)
                r6.writeNoException()
                if (r4 == 0) goto L_0x013e
                r6.writeInt(r1)
                r4.writeToParcel(r6, r1)
                goto L_0x0141
            L_0x013e:
                r6.writeInt(r0)
            L_0x0141:
                return r1
            L_0x0142:
                java.lang.String r4 = "com.xiaomi.account.IXiaomiAuthService"
                r6.writeString(r4)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.account.IXiaomiAuthService.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IXiaomiAuthService {
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

            public Bundle getMiCloudUserInfo(Account account, Bundle bundle) throws RemoteException {
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
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getMiCloudAccessToken(Account account, Bundle bundle) throws RemoteException {
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
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getSnsAccessToken(Account account, Bundle bundle) throws RemoteException {
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
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void invalidateAccessToken(Account account, Bundle bundle) throws RemoteException {
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
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean supportResponseWay() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(5, obtain, obtain2, 0);
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

            public void getAccessTokenInResponse(IXiaomiAuthResponse iXiaomiAuthResponse, Bundle bundle, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iXiaomiAuthResponse != null ? iXiaomiAuthResponse.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getVersionNum() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isSupport(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = false;
                    this.mRemote.transact(8, obtain, obtain2, 0);
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
