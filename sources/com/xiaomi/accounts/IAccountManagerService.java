package com.xiaomi.accounts;

import android.accounts.Account;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IAccountManagerService extends IInterface {
    boolean addAccount(Account account, String str, Bundle bundle) throws RemoteException;

    void addAcount(IAccountManagerResponse iAccountManagerResponse, String str, String str2, String[] strArr, boolean z, Bundle bundle) throws RemoteException;

    void clearPassword(Account account) throws RemoteException;

    void confirmCredentials(IAccountManagerResponse iAccountManagerResponse, Account account, Bundle bundle, boolean z) throws RemoteException;

    void editProperties(IAccountManagerResponse iAccountManagerResponse, String str, boolean z) throws RemoteException;

    Account[] getAccounts(String str) throws RemoteException;

    void getAccountsByFeatures(IAccountManagerResponse iAccountManagerResponse, String str, String[] strArr) throws RemoteException;

    void getAuthToken(IAccountManagerResponse iAccountManagerResponse, Account account, String str, boolean z, boolean z2, Bundle bundle) throws RemoteException;

    void getAuthTokenLabel(IAccountManagerResponse iAccountManagerResponse, String str, String str2) throws RemoteException;

    String getPassword(Account account) throws RemoteException;

    String getUserData(Account account, String str) throws RemoteException;

    void hasFeatures(IAccountManagerResponse iAccountManagerResponse, Account account, String[] strArr) throws RemoteException;

    void invalidateAuthToken(String str, String str2) throws RemoteException;

    String peekAuthToken(Account account, String str) throws RemoteException;

    void removeAccount(IAccountManagerResponse iAccountManagerResponse, Account account) throws RemoteException;

    void setAuthToken(Account account, String str, String str2) throws RemoteException;

    void setPassword(Account account, String str) throws RemoteException;

    void setUserData(Account account, String str, String str2) throws RemoteException;

    void updateAppPermission(Account account, String str, int i, boolean z) throws RemoteException;

    void updateCredentials(IAccountManagerResponse iAccountManagerResponse, Account account, String str, boolean z, Bundle bundle) throws RemoteException;

    public static abstract class Stub extends Binder implements IAccountManagerService {
        private static final String DESCRIPTOR = "com.xiaomi.accounts.IAccountManagerService";
        static final int TRANSACTION_addAccount = 6;
        static final int TRANSACTION_addAcount = 16;
        static final int TRANSACTION_clearPassword = 12;
        static final int TRANSACTION_confirmCredentials = 19;
        static final int TRANSACTION_editProperties = 18;
        static final int TRANSACTION_getAccounts = 3;
        static final int TRANSACTION_getAccountsByFeatures = 5;
        static final int TRANSACTION_getAuthToken = 15;
        static final int TRANSACTION_getAuthTokenLabel = 20;
        static final int TRANSACTION_getPassword = 1;
        static final int TRANSACTION_getUserData = 2;
        static final int TRANSACTION_hasFeatures = 4;
        static final int TRANSACTION_invalidateAuthToken = 8;
        static final int TRANSACTION_peekAuthToken = 9;
        static final int TRANSACTION_removeAccount = 7;
        static final int TRANSACTION_setAuthToken = 10;
        static final int TRANSACTION_setPassword = 11;
        static final int TRANSACTION_setUserData = 13;
        static final int TRANSACTION_updateAppPermission = 14;
        static final int TRANSACTION_updateCredentials = 17;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAccountManagerService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IAccountManagerService)) {
                return new Proxy(iBinder);
            }
            return (IAccountManagerService) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: android.accounts.Account} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: android.accounts.Account} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: android.accounts.Account} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: android.accounts.Account} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v16, resolved type: android.accounts.Account} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v19, resolved type: android.accounts.Account} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v22, resolved type: android.accounts.Account} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v25, resolved type: android.accounts.Account} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v28, resolved type: android.accounts.Account} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v31, resolved type: android.accounts.Account} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v43, resolved type: android.os.Bundle} */
        /* JADX WARNING: type inference failed for: r2v0 */
        /* JADX WARNING: type inference failed for: r2v34 */
        /* JADX WARNING: type inference failed for: r2v37 */
        /* JADX WARNING: type inference failed for: r2v40 */
        /* JADX WARNING: type inference failed for: r2v47 */
        /* JADX WARNING: type inference failed for: r2v48 */
        /* JADX WARNING: type inference failed for: r2v49 */
        /* JADX WARNING: type inference failed for: r2v50 */
        /* JADX WARNING: type inference failed for: r2v51 */
        /* JADX WARNING: type inference failed for: r2v52 */
        /* JADX WARNING: type inference failed for: r2v53 */
        /* JADX WARNING: type inference failed for: r2v54 */
        /* JADX WARNING: type inference failed for: r2v55 */
        /* JADX WARNING: type inference failed for: r2v56 */
        /* JADX WARNING: type inference failed for: r2v57 */
        /* JADX WARNING: type inference failed for: r2v58 */
        /* JADX WARNING: type inference failed for: r2v59 */
        /* JADX WARNING: type inference failed for: r2v60 */
        /* JADX WARNING: type inference failed for: r2v61 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r11, android.os.Parcel r12, android.os.Parcel r13, int r14) throws android.os.RemoteException {
            /*
                r10 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                if (r11 == r0) goto L_0x031d
                r0 = 0
                r2 = 0
                switch(r11) {
                    case 1: goto L_0x02fe;
                    case 2: goto L_0x02db;
                    case 3: goto L_0x02c7;
                    case 4: goto L_0x02a0;
                    case 5: goto L_0x0284;
                    case 6: goto L_0x0251;
                    case 7: goto L_0x022e;
                    case 8: goto L_0x021a;
                    case 9: goto L_0x01f7;
                    case 10: goto L_0x01d4;
                    case 11: goto L_0x01b5;
                    case 12: goto L_0x019a;
                    case 13: goto L_0x0177;
                    case 14: goto L_0x014d;
                    case 15: goto L_0x0101;
                    case 16: goto L_0x00c7;
                    case 17: goto L_0x0084;
                    case 18: goto L_0x0065;
                    case 19: goto L_0x002c;
                    case 20: goto L_0x0010;
                    default: goto L_0x000b;
                }
            L_0x000b:
                boolean r11 = super.onTransact(r11, r12, r13, r14)
                return r11
            L_0x0010:
                java.lang.String r11 = "com.xiaomi.accounts.IAccountManagerService"
                r12.enforceInterface(r11)
                android.os.IBinder r11 = r12.readStrongBinder()
                com.xiaomi.accounts.IAccountManagerResponse r11 = com.xiaomi.accounts.IAccountManagerResponse.Stub.asInterface(r11)
                java.lang.String r14 = r12.readString()
                java.lang.String r12 = r12.readString()
                r10.getAuthTokenLabel(r11, r14, r12)
                r13.writeNoException()
                return r1
            L_0x002c:
                java.lang.String r11 = "com.xiaomi.accounts.IAccountManagerService"
                r12.enforceInterface(r11)
                android.os.IBinder r11 = r12.readStrongBinder()
                com.xiaomi.accounts.IAccountManagerResponse r11 = com.xiaomi.accounts.IAccountManagerResponse.Stub.asInterface(r11)
                int r14 = r12.readInt()
                if (r14 == 0) goto L_0x0048
                android.os.Parcelable$Creator r14 = android.accounts.Account.CREATOR
                java.lang.Object r14 = r14.createFromParcel(r12)
                android.accounts.Account r14 = (android.accounts.Account) r14
                goto L_0x0049
            L_0x0048:
                r14 = r2
            L_0x0049:
                int r3 = r12.readInt()
                if (r3 == 0) goto L_0x0057
                android.os.Parcelable$Creator r2 = android.os.Bundle.CREATOR
                java.lang.Object r2 = r2.createFromParcel(r12)
                android.os.Bundle r2 = (android.os.Bundle) r2
            L_0x0057:
                int r12 = r12.readInt()
                if (r12 == 0) goto L_0x005e
                r0 = 1
            L_0x005e:
                r10.confirmCredentials(r11, r14, r2, r0)
                r13.writeNoException()
                return r1
            L_0x0065:
                java.lang.String r11 = "com.xiaomi.accounts.IAccountManagerService"
                r12.enforceInterface(r11)
                android.os.IBinder r11 = r12.readStrongBinder()
                com.xiaomi.accounts.IAccountManagerResponse r11 = com.xiaomi.accounts.IAccountManagerResponse.Stub.asInterface(r11)
                java.lang.String r14 = r12.readString()
                int r12 = r12.readInt()
                if (r12 == 0) goto L_0x007d
                r0 = 1
            L_0x007d:
                r10.editProperties(r11, r14, r0)
                r13.writeNoException()
                return r1
            L_0x0084:
                java.lang.String r11 = "com.xiaomi.accounts.IAccountManagerService"
                r12.enforceInterface(r11)
                android.os.IBinder r11 = r12.readStrongBinder()
                com.xiaomi.accounts.IAccountManagerResponse r4 = com.xiaomi.accounts.IAccountManagerResponse.Stub.asInterface(r11)
                int r11 = r12.readInt()
                if (r11 == 0) goto L_0x00a1
                android.os.Parcelable$Creator r11 = android.accounts.Account.CREATOR
                java.lang.Object r11 = r11.createFromParcel(r12)
                android.accounts.Account r11 = (android.accounts.Account) r11
                r5 = r11
                goto L_0x00a2
            L_0x00a1:
                r5 = r2
            L_0x00a2:
                java.lang.String r6 = r12.readString()
                int r11 = r12.readInt()
                if (r11 == 0) goto L_0x00ae
                r7 = 1
                goto L_0x00af
            L_0x00ae:
                r7 = 0
            L_0x00af:
                int r11 = r12.readInt()
                if (r11 == 0) goto L_0x00be
                android.os.Parcelable$Creator r11 = android.os.Bundle.CREATOR
                java.lang.Object r11 = r11.createFromParcel(r12)
                r2 = r11
                android.os.Bundle r2 = (android.os.Bundle) r2
            L_0x00be:
                r8 = r2
                r3 = r10
                r3.updateCredentials(r4, r5, r6, r7, r8)
                r13.writeNoException()
                return r1
            L_0x00c7:
                java.lang.String r11 = "com.xiaomi.accounts.IAccountManagerService"
                r12.enforceInterface(r11)
                android.os.IBinder r11 = r12.readStrongBinder()
                com.xiaomi.accounts.IAccountManagerResponse r4 = com.xiaomi.accounts.IAccountManagerResponse.Stub.asInterface(r11)
                java.lang.String r5 = r12.readString()
                java.lang.String r6 = r12.readString()
                java.lang.String[] r7 = r12.createStringArray()
                int r11 = r12.readInt()
                if (r11 == 0) goto L_0x00e8
                r8 = 1
                goto L_0x00e9
            L_0x00e8:
                r8 = 0
            L_0x00e9:
                int r11 = r12.readInt()
                if (r11 == 0) goto L_0x00f8
                android.os.Parcelable$Creator r11 = android.os.Bundle.CREATOR
                java.lang.Object r11 = r11.createFromParcel(r12)
                r2 = r11
                android.os.Bundle r2 = (android.os.Bundle) r2
            L_0x00f8:
                r9 = r2
                r3 = r10
                r3.addAcount(r4, r5, r6, r7, r8, r9)
                r13.writeNoException()
                return r1
            L_0x0101:
                java.lang.String r11 = "com.xiaomi.accounts.IAccountManagerService"
                r12.enforceInterface(r11)
                android.os.IBinder r11 = r12.readStrongBinder()
                com.xiaomi.accounts.IAccountManagerResponse r4 = com.xiaomi.accounts.IAccountManagerResponse.Stub.asInterface(r11)
                int r11 = r12.readInt()
                if (r11 == 0) goto L_0x011e
                android.os.Parcelable$Creator r11 = android.accounts.Account.CREATOR
                java.lang.Object r11 = r11.createFromParcel(r12)
                android.accounts.Account r11 = (android.accounts.Account) r11
                r5 = r11
                goto L_0x011f
            L_0x011e:
                r5 = r2
            L_0x011f:
                java.lang.String r6 = r12.readString()
                int r11 = r12.readInt()
                if (r11 == 0) goto L_0x012b
                r7 = 1
                goto L_0x012c
            L_0x012b:
                r7 = 0
            L_0x012c:
                int r11 = r12.readInt()
                if (r11 == 0) goto L_0x0134
                r8 = 1
                goto L_0x0135
            L_0x0134:
                r8 = 0
            L_0x0135:
                int r11 = r12.readInt()
                if (r11 == 0) goto L_0x0144
                android.os.Parcelable$Creator r11 = android.os.Bundle.CREATOR
                java.lang.Object r11 = r11.createFromParcel(r12)
                r2 = r11
                android.os.Bundle r2 = (android.os.Bundle) r2
            L_0x0144:
                r9 = r2
                r3 = r10
                r3.getAuthToken(r4, r5, r6, r7, r8, r9)
                r13.writeNoException()
                return r1
            L_0x014d:
                java.lang.String r11 = "com.xiaomi.accounts.IAccountManagerService"
                r12.enforceInterface(r11)
                int r11 = r12.readInt()
                if (r11 == 0) goto L_0x0161
                android.os.Parcelable$Creator r11 = android.accounts.Account.CREATOR
                java.lang.Object r11 = r11.createFromParcel(r12)
                r2 = r11
                android.accounts.Account r2 = (android.accounts.Account) r2
            L_0x0161:
                java.lang.String r11 = r12.readString()
                int r14 = r12.readInt()
                int r12 = r12.readInt()
                if (r12 == 0) goto L_0x0170
                r0 = 1
            L_0x0170:
                r10.updateAppPermission(r2, r11, r14, r0)
                r13.writeNoException()
                return r1
            L_0x0177:
                java.lang.String r11 = "com.xiaomi.accounts.IAccountManagerService"
                r12.enforceInterface(r11)
                int r11 = r12.readInt()
                if (r11 == 0) goto L_0x018b
                android.os.Parcelable$Creator r11 = android.accounts.Account.CREATOR
                java.lang.Object r11 = r11.createFromParcel(r12)
                r2 = r11
                android.accounts.Account r2 = (android.accounts.Account) r2
            L_0x018b:
                java.lang.String r11 = r12.readString()
                java.lang.String r12 = r12.readString()
                r10.setUserData(r2, r11, r12)
                r13.writeNoException()
                return r1
            L_0x019a:
                java.lang.String r11 = "com.xiaomi.accounts.IAccountManagerService"
                r12.enforceInterface(r11)
                int r11 = r12.readInt()
                if (r11 == 0) goto L_0x01ae
                android.os.Parcelable$Creator r11 = android.accounts.Account.CREATOR
                java.lang.Object r11 = r11.createFromParcel(r12)
                r2 = r11
                android.accounts.Account r2 = (android.accounts.Account) r2
            L_0x01ae:
                r10.clearPassword(r2)
                r13.writeNoException()
                return r1
            L_0x01b5:
                java.lang.String r11 = "com.xiaomi.accounts.IAccountManagerService"
                r12.enforceInterface(r11)
                int r11 = r12.readInt()
                if (r11 == 0) goto L_0x01c9
                android.os.Parcelable$Creator r11 = android.accounts.Account.CREATOR
                java.lang.Object r11 = r11.createFromParcel(r12)
                r2 = r11
                android.accounts.Account r2 = (android.accounts.Account) r2
            L_0x01c9:
                java.lang.String r11 = r12.readString()
                r10.setPassword(r2, r11)
                r13.writeNoException()
                return r1
            L_0x01d4:
                java.lang.String r11 = "com.xiaomi.accounts.IAccountManagerService"
                r12.enforceInterface(r11)
                int r11 = r12.readInt()
                if (r11 == 0) goto L_0x01e8
                android.os.Parcelable$Creator r11 = android.accounts.Account.CREATOR
                java.lang.Object r11 = r11.createFromParcel(r12)
                r2 = r11
                android.accounts.Account r2 = (android.accounts.Account) r2
            L_0x01e8:
                java.lang.String r11 = r12.readString()
                java.lang.String r12 = r12.readString()
                r10.setAuthToken(r2, r11, r12)
                r13.writeNoException()
                return r1
            L_0x01f7:
                java.lang.String r11 = "com.xiaomi.accounts.IAccountManagerService"
                r12.enforceInterface(r11)
                int r11 = r12.readInt()
                if (r11 == 0) goto L_0x020b
                android.os.Parcelable$Creator r11 = android.accounts.Account.CREATOR
                java.lang.Object r11 = r11.createFromParcel(r12)
                r2 = r11
                android.accounts.Account r2 = (android.accounts.Account) r2
            L_0x020b:
                java.lang.String r11 = r12.readString()
                java.lang.String r11 = r10.peekAuthToken(r2, r11)
                r13.writeNoException()
                r13.writeString(r11)
                return r1
            L_0x021a:
                java.lang.String r11 = "com.xiaomi.accounts.IAccountManagerService"
                r12.enforceInterface(r11)
                java.lang.String r11 = r12.readString()
                java.lang.String r12 = r12.readString()
                r10.invalidateAuthToken(r11, r12)
                r13.writeNoException()
                return r1
            L_0x022e:
                java.lang.String r11 = "com.xiaomi.accounts.IAccountManagerService"
                r12.enforceInterface(r11)
                android.os.IBinder r11 = r12.readStrongBinder()
                com.xiaomi.accounts.IAccountManagerResponse r11 = com.xiaomi.accounts.IAccountManagerResponse.Stub.asInterface(r11)
                int r14 = r12.readInt()
                if (r14 == 0) goto L_0x024a
                android.os.Parcelable$Creator r14 = android.accounts.Account.CREATOR
                java.lang.Object r12 = r14.createFromParcel(r12)
                r2 = r12
                android.accounts.Account r2 = (android.accounts.Account) r2
            L_0x024a:
                r10.removeAccount(r11, r2)
                r13.writeNoException()
                return r1
            L_0x0251:
                java.lang.String r11 = "com.xiaomi.accounts.IAccountManagerService"
                r12.enforceInterface(r11)
                int r11 = r12.readInt()
                if (r11 == 0) goto L_0x0265
                android.os.Parcelable$Creator r11 = android.accounts.Account.CREATOR
                java.lang.Object r11 = r11.createFromParcel(r12)
                android.accounts.Account r11 = (android.accounts.Account) r11
                goto L_0x0266
            L_0x0265:
                r11 = r2
            L_0x0266:
                java.lang.String r14 = r12.readString()
                int r0 = r12.readInt()
                if (r0 == 0) goto L_0x0279
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r12 = r0.createFromParcel(r12)
                r2 = r12
                android.os.Bundle r2 = (android.os.Bundle) r2
            L_0x0279:
                boolean r11 = r10.addAccount(r11, r14, r2)
                r13.writeNoException()
                r13.writeInt(r11)
                return r1
            L_0x0284:
                java.lang.String r11 = "com.xiaomi.accounts.IAccountManagerService"
                r12.enforceInterface(r11)
                android.os.IBinder r11 = r12.readStrongBinder()
                com.xiaomi.accounts.IAccountManagerResponse r11 = com.xiaomi.accounts.IAccountManagerResponse.Stub.asInterface(r11)
                java.lang.String r14 = r12.readString()
                java.lang.String[] r12 = r12.createStringArray()
                r10.getAccountsByFeatures(r11, r14, r12)
                r13.writeNoException()
                return r1
            L_0x02a0:
                java.lang.String r11 = "com.xiaomi.accounts.IAccountManagerService"
                r12.enforceInterface(r11)
                android.os.IBinder r11 = r12.readStrongBinder()
                com.xiaomi.accounts.IAccountManagerResponse r11 = com.xiaomi.accounts.IAccountManagerResponse.Stub.asInterface(r11)
                int r14 = r12.readInt()
                if (r14 == 0) goto L_0x02bc
                android.os.Parcelable$Creator r14 = android.accounts.Account.CREATOR
                java.lang.Object r14 = r14.createFromParcel(r12)
                r2 = r14
                android.accounts.Account r2 = (android.accounts.Account) r2
            L_0x02bc:
                java.lang.String[] r12 = r12.createStringArray()
                r10.hasFeatures(r11, r2, r12)
                r13.writeNoException()
                return r1
            L_0x02c7:
                java.lang.String r11 = "com.xiaomi.accounts.IAccountManagerService"
                r12.enforceInterface(r11)
                java.lang.String r11 = r12.readString()
                android.accounts.Account[] r11 = r10.getAccounts(r11)
                r13.writeNoException()
                r13.writeTypedArray(r11, r1)
                return r1
            L_0x02db:
                java.lang.String r11 = "com.xiaomi.accounts.IAccountManagerService"
                r12.enforceInterface(r11)
                int r11 = r12.readInt()
                if (r11 == 0) goto L_0x02ef
                android.os.Parcelable$Creator r11 = android.accounts.Account.CREATOR
                java.lang.Object r11 = r11.createFromParcel(r12)
                r2 = r11
                android.accounts.Account r2 = (android.accounts.Account) r2
            L_0x02ef:
                java.lang.String r11 = r12.readString()
                java.lang.String r11 = r10.getUserData(r2, r11)
                r13.writeNoException()
                r13.writeString(r11)
                return r1
            L_0x02fe:
                java.lang.String r11 = "com.xiaomi.accounts.IAccountManagerService"
                r12.enforceInterface(r11)
                int r11 = r12.readInt()
                if (r11 == 0) goto L_0x0312
                android.os.Parcelable$Creator r11 = android.accounts.Account.CREATOR
                java.lang.Object r11 = r11.createFromParcel(r12)
                r2 = r11
                android.accounts.Account r2 = (android.accounts.Account) r2
            L_0x0312:
                java.lang.String r11 = r10.getPassword(r2)
                r13.writeNoException()
                r13.writeString(r11)
                return r1
            L_0x031d:
                java.lang.String r11 = "com.xiaomi.accounts.IAccountManagerService"
                r13.writeString(r11)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.accounts.IAccountManagerService.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IAccountManagerService {
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

            public String getPassword(Account account) throws RemoteException {
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

            public String getUserData(Account account, String str) throws RemoteException {
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
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Account[] getAccounts(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return (Account[]) obtain2.createTypedArray(Account.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void hasFeatures(IAccountManagerResponse iAccountManagerResponse, Account account, String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iAccountManagerResponse != null ? iAccountManagerResponse.asBinder() : null);
                    if (account != null) {
                        obtain.writeInt(1);
                        account.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStringArray(strArr);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getAccountsByFeatures(IAccountManagerResponse iAccountManagerResponse, String str, String[] strArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iAccountManagerResponse != null ? iAccountManagerResponse.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeStringArray(strArr);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean addAccount(Account account, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
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
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void removeAccount(IAccountManagerResponse iAccountManagerResponse, Account account) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iAccountManagerResponse != null ? iAccountManagerResponse.asBinder() : null);
                    if (account != null) {
                        obtain.writeInt(1);
                        account.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void invalidateAuthToken(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String peekAuthToken(Account account, String str) throws RemoteException {
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
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setAuthToken(Account account, String str, String str2) throws RemoteException {
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
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setPassword(Account account, String str) throws RemoteException {
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
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void clearPassword(Account account) throws RemoteException {
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
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setUserData(Account account, String str, String str2) throws RemoteException {
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
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void updateAppPermission(Account account, String str, int i, boolean z) throws RemoteException {
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
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getAuthToken(IAccountManagerResponse iAccountManagerResponse, Account account, String str, boolean z, boolean z2, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iAccountManagerResponse != null ? iAccountManagerResponse.asBinder() : null);
                    if (account != null) {
                        obtain.writeInt(1);
                        account.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(z2 ? 1 : 0);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void addAcount(IAccountManagerResponse iAccountManagerResponse, String str, String str2, String[] strArr, boolean z, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iAccountManagerResponse != null ? iAccountManagerResponse.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStringArray(strArr);
                    obtain.writeInt(z ? 1 : 0);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void updateCredentials(IAccountManagerResponse iAccountManagerResponse, Account account, String str, boolean z, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iAccountManagerResponse != null ? iAccountManagerResponse.asBinder() : null);
                    if (account != null) {
                        obtain.writeInt(1);
                        account.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void editProperties(IAccountManagerResponse iAccountManagerResponse, String str, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iAccountManagerResponse != null ? iAccountManagerResponse.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void confirmCredentials(IAccountManagerResponse iAccountManagerResponse, Account account, Bundle bundle, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iAccountManagerResponse != null ? iAccountManagerResponse.asBinder() : null);
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
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getAuthTokenLabel(IAccountManagerResponse iAccountManagerResponse, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iAccountManagerResponse != null ? iAccountManagerResponse.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
