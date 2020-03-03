package miui.net;

import android.accounts.Account;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IXiaomiAuthService extends IInterface {
    Bundle getMiCloudAccessToken(Account account, Bundle bundle) throws RemoteException;

    Bundle getMiCloudUserInfo(Account account, Bundle bundle) throws RemoteException;

    Bundle getSnsAccessToken(Account account, Bundle bundle) throws RemoteException;

    void invalidateAccessToken(Account account, Bundle bundle) throws RemoteException;

    public static abstract class Stub extends Binder implements IXiaomiAuthService {
        private static final String DESCRIPTOR = "miui.net.IXiaomiAuthService";
        static final int TRANSACTION_getMiCloudAccessToken = 2;
        static final int TRANSACTION_getMiCloudUserInfo = 1;
        static final int TRANSACTION_getSnsAccessToken = 3;
        static final int TRANSACTION_invalidateAccessToken = 4;

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
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r4, android.os.Parcel r5, android.os.Parcel r6, int r7) throws android.os.RemoteException {
            /*
                r3 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                if (r4 == r0) goto L_0x00e3
                r0 = 0
                r2 = 0
                switch(r4) {
                    case 1: goto L_0x00ab;
                    case 2: goto L_0x0073;
                    case 3: goto L_0x003b;
                    case 4: goto L_0x0010;
                    default: goto L_0x000b;
                }
            L_0x000b:
                boolean r4 = super.onTransact(r4, r5, r6, r7)
                return r4
            L_0x0010:
                java.lang.String r4 = "miui.net.IXiaomiAuthService"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0024
                android.os.Parcelable$Creator r4 = android.accounts.Account.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                android.accounts.Account r4 = (android.accounts.Account) r4
                goto L_0x0025
            L_0x0024:
                r4 = r2
            L_0x0025:
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x0034
                android.os.Parcelable$Creator r7 = android.os.Bundle.CREATOR
                java.lang.Object r5 = r7.createFromParcel(r5)
                r2 = r5
                android.os.Bundle r2 = (android.os.Bundle) r2
            L_0x0034:
                r3.invalidateAccessToken(r4, r2)
                r6.writeNoException()
                return r1
            L_0x003b:
                java.lang.String r4 = "miui.net.IXiaomiAuthService"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x004f
                android.os.Parcelable$Creator r4 = android.accounts.Account.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                android.accounts.Account r4 = (android.accounts.Account) r4
                goto L_0x0050
            L_0x004f:
                r4 = r2
            L_0x0050:
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x005f
                android.os.Parcelable$Creator r7 = android.os.Bundle.CREATOR
                java.lang.Object r5 = r7.createFromParcel(r5)
                r2 = r5
                android.os.Bundle r2 = (android.os.Bundle) r2
            L_0x005f:
                android.os.Bundle r4 = r3.getSnsAccessToken(r4, r2)
                r6.writeNoException()
                if (r4 == 0) goto L_0x006f
                r6.writeInt(r1)
                r4.writeToParcel(r6, r1)
                goto L_0x0072
            L_0x006f:
                r6.writeInt(r0)
            L_0x0072:
                return r1
            L_0x0073:
                java.lang.String r4 = "miui.net.IXiaomiAuthService"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0087
                android.os.Parcelable$Creator r4 = android.accounts.Account.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                android.accounts.Account r4 = (android.accounts.Account) r4
                goto L_0x0088
            L_0x0087:
                r4 = r2
            L_0x0088:
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x0097
                android.os.Parcelable$Creator r7 = android.os.Bundle.CREATOR
                java.lang.Object r5 = r7.createFromParcel(r5)
                r2 = r5
                android.os.Bundle r2 = (android.os.Bundle) r2
            L_0x0097:
                android.os.Bundle r4 = r3.getMiCloudAccessToken(r4, r2)
                r6.writeNoException()
                if (r4 == 0) goto L_0x00a7
                r6.writeInt(r1)
                r4.writeToParcel(r6, r1)
                goto L_0x00aa
            L_0x00a7:
                r6.writeInt(r0)
            L_0x00aa:
                return r1
            L_0x00ab:
                java.lang.String r4 = "miui.net.IXiaomiAuthService"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x00bf
                android.os.Parcelable$Creator r4 = android.accounts.Account.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                android.accounts.Account r4 = (android.accounts.Account) r4
                goto L_0x00c0
            L_0x00bf:
                r4 = r2
            L_0x00c0:
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x00cf
                android.os.Parcelable$Creator r7 = android.os.Bundle.CREATOR
                java.lang.Object r5 = r7.createFromParcel(r5)
                r2 = r5
                android.os.Bundle r2 = (android.os.Bundle) r2
            L_0x00cf:
                android.os.Bundle r4 = r3.getMiCloudUserInfo(r4, r2)
                r6.writeNoException()
                if (r4 == 0) goto L_0x00df
                r6.writeInt(r1)
                r4.writeToParcel(r6, r1)
                goto L_0x00e2
            L_0x00df:
                r6.writeInt(r0)
            L_0x00e2:
                return r1
            L_0x00e3:
                java.lang.String r4 = "miui.net.IXiaomiAuthService"
                r6.writeString(r4)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: miui.net.IXiaomiAuthService.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
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
        }
    }
}
