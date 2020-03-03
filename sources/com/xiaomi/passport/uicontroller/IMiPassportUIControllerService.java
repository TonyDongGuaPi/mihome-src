package com.xiaomi.passport.uicontroller;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.data.MiLoginResult;
import com.xiaomi.accountsdk.account.data.NotificationAuthResult;
import com.xiaomi.accountsdk.account.data.NotificationLoginEndParams;
import com.xiaomi.accountsdk.account.data.PasswordLoginParams;
import com.xiaomi.accountsdk.account.data.Step2LoginParams;

public interface IMiPassportUIControllerService extends IInterface {
    void addOrUpdateAccountManager(AccountInfo accountInfo) throws RemoteException;

    MiLoginResult loginByPassword(PasswordLoginParams passwordLoginParams) throws RemoteException;

    MiLoginResult loginByStep2(Step2LoginParams step2LoginParams) throws RemoteException;

    NotificationAuthResult onNotificationAuthEnd(String str) throws RemoteException;

    MiLoginResult onNotificationLoginEnd(NotificationLoginEndParams notificationLoginEndParams) throws RemoteException;

    public static abstract class Stub extends Binder implements IMiPassportUIControllerService {
        private static final String DESCRIPTOR = "com.xiaomi.passport.uicontroller.IMiPassportUIControllerService";
        static final int TRANSACTION_addOrUpdateAccountManager = 4;
        static final int TRANSACTION_loginByPassword = 1;
        static final int TRANSACTION_loginByStep2 = 3;
        static final int TRANSACTION_onNotificationAuthEnd = 5;
        static final int TRANSACTION_onNotificationLoginEnd = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMiPassportUIControllerService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMiPassportUIControllerService)) {
                return new Proxy(iBinder);
            }
            return (IMiPassportUIControllerService) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: com.xiaomi.accountsdk.account.data.PasswordLoginParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: com.xiaomi.accountsdk.account.data.NotificationLoginEndParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: com.xiaomi.accountsdk.account.data.Step2LoginParams} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: com.xiaomi.accountsdk.account.data.AccountInfo} */
        /* JADX WARNING: type inference failed for: r2v0 */
        /* JADX WARNING: type inference failed for: r2v13 */
        /* JADX WARNING: type inference failed for: r2v14 */
        /* JADX WARNING: type inference failed for: r2v15 */
        /* JADX WARNING: type inference failed for: r2v16 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r4, android.os.Parcel r5, android.os.Parcel r6, int r7) throws android.os.RemoteException {
            /*
                r3 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                if (r4 == r0) goto L_0x00c0
                r0 = 0
                r2 = 0
                switch(r4) {
                    case 1: goto L_0x0098;
                    case 2: goto L_0x0070;
                    case 3: goto L_0x0048;
                    case 4: goto L_0x002d;
                    case 5: goto L_0x0010;
                    default: goto L_0x000b;
                }
            L_0x000b:
                boolean r4 = super.onTransact(r4, r5, r6, r7)
                return r4
            L_0x0010:
                java.lang.String r4 = "com.xiaomi.passport.uicontroller.IMiPassportUIControllerService"
                r5.enforceInterface(r4)
                java.lang.String r4 = r5.readString()
                com.xiaomi.accountsdk.account.data.NotificationAuthResult r4 = r3.onNotificationAuthEnd(r4)
                r6.writeNoException()
                if (r4 == 0) goto L_0x0029
                r6.writeInt(r1)
                r4.writeToParcel(r6, r1)
                goto L_0x002c
            L_0x0029:
                r6.writeInt(r0)
            L_0x002c:
                return r1
            L_0x002d:
                java.lang.String r4 = "com.xiaomi.passport.uicontroller.IMiPassportUIControllerService"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0041
                android.os.Parcelable$Creator<com.xiaomi.accountsdk.account.data.AccountInfo> r4 = com.xiaomi.accountsdk.account.data.AccountInfo.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r2 = r4
                com.xiaomi.accountsdk.account.data.AccountInfo r2 = (com.xiaomi.accountsdk.account.data.AccountInfo) r2
            L_0x0041:
                r3.addOrUpdateAccountManager(r2)
                r6.writeNoException()
                return r1
            L_0x0048:
                java.lang.String r4 = "com.xiaomi.passport.uicontroller.IMiPassportUIControllerService"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x005c
                android.os.Parcelable$Creator<com.xiaomi.accountsdk.account.data.Step2LoginParams> r4 = com.xiaomi.accountsdk.account.data.Step2LoginParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r2 = r4
                com.xiaomi.accountsdk.account.data.Step2LoginParams r2 = (com.xiaomi.accountsdk.account.data.Step2LoginParams) r2
            L_0x005c:
                com.xiaomi.accountsdk.account.data.MiLoginResult r4 = r3.loginByStep2(r2)
                r6.writeNoException()
                if (r4 == 0) goto L_0x006c
                r6.writeInt(r1)
                r4.writeToParcel(r6, r1)
                goto L_0x006f
            L_0x006c:
                r6.writeInt(r0)
            L_0x006f:
                return r1
            L_0x0070:
                java.lang.String r4 = "com.xiaomi.passport.uicontroller.IMiPassportUIControllerService"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0084
                android.os.Parcelable$Creator<com.xiaomi.accountsdk.account.data.NotificationLoginEndParams> r4 = com.xiaomi.accountsdk.account.data.NotificationLoginEndParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r2 = r4
                com.xiaomi.accountsdk.account.data.NotificationLoginEndParams r2 = (com.xiaomi.accountsdk.account.data.NotificationLoginEndParams) r2
            L_0x0084:
                com.xiaomi.accountsdk.account.data.MiLoginResult r4 = r3.onNotificationLoginEnd(r2)
                r6.writeNoException()
                if (r4 == 0) goto L_0x0094
                r6.writeInt(r1)
                r4.writeToParcel(r6, r1)
                goto L_0x0097
            L_0x0094:
                r6.writeInt(r0)
            L_0x0097:
                return r1
            L_0x0098:
                java.lang.String r4 = "com.xiaomi.passport.uicontroller.IMiPassportUIControllerService"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x00ac
                android.os.Parcelable$Creator<com.xiaomi.accountsdk.account.data.PasswordLoginParams> r4 = com.xiaomi.accountsdk.account.data.PasswordLoginParams.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r2 = r4
                com.xiaomi.accountsdk.account.data.PasswordLoginParams r2 = (com.xiaomi.accountsdk.account.data.PasswordLoginParams) r2
            L_0x00ac:
                com.xiaomi.accountsdk.account.data.MiLoginResult r4 = r3.loginByPassword(r2)
                r6.writeNoException()
                if (r4 == 0) goto L_0x00bc
                r6.writeInt(r1)
                r4.writeToParcel(r6, r1)
                goto L_0x00bf
            L_0x00bc:
                r6.writeInt(r0)
            L_0x00bf:
                return r1
            L_0x00c0:
                java.lang.String r4 = "com.xiaomi.passport.uicontroller.IMiPassportUIControllerService"
                r6.writeString(r4)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.passport.uicontroller.IMiPassportUIControllerService.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IMiPassportUIControllerService {
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

            public MiLoginResult loginByPassword(PasswordLoginParams passwordLoginParams) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (passwordLoginParams != null) {
                        obtain.writeInt(1);
                        passwordLoginParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? MiLoginResult.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public MiLoginResult onNotificationLoginEnd(NotificationLoginEndParams notificationLoginEndParams) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (notificationLoginEndParams != null) {
                        obtain.writeInt(1);
                        notificationLoginEndParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? MiLoginResult.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public MiLoginResult loginByStep2(Step2LoginParams step2LoginParams) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (step2LoginParams != null) {
                        obtain.writeInt(1);
                        step2LoginParams.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? MiLoginResult.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void addOrUpdateAccountManager(AccountInfo accountInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (accountInfo != null) {
                        obtain.writeInt(1);
                        accountInfo.writeToParcel(obtain, 0);
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

            public NotificationAuthResult onNotificationAuthEnd(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? NotificationAuthResult.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
