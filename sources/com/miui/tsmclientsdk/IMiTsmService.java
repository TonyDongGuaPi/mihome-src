package com.miui.tsmclientsdk;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IMiTsmService extends IInterface {
    void addMiPayShortcut(IMiTsmResponse iMiTsmResponse, String str) throws RemoteException;

    void addMipayCard(IMiTsmResponse iMiTsmResponse, String str, int i, String str2) throws RemoteException;

    void createSSD(IMiTsmResponse iMiTsmResponse, int i) throws RemoteException;

    void deleteAllBankCard(IMiTsmResponse iMiTsmResponse) throws RemoteException;

    void deleteBankCard(IMiTsmResponse iMiTsmResponse, String str) throws RemoteException;

    void getActiveCards(IMiTsmResponse iMiTsmResponse, String str) throws RemoteException;

    void getCPLC(IMiTsmResponse iMiTsmResponse) throws RemoteException;

    void getCardInfo(IMiTsmResponse iMiTsmResponse, List<String> list) throws RemoteException;

    void getCardsQuantity(IMiTsmResponse iMiTsmResponse, String str) throws RemoteException;

    void getCardsState(IMiTsmResponse iMiTsmResponse) throws RemoteException;

    void getDefaultCard(IMiTsmResponse iMiTsmResponse, String str) throws RemoteException;

    void getMiPayStatus(IMiTsmResponse iMiTsmResponse) throws RemoteException;

    void getSeBankCards(IMiTsmResponse iMiTsmResponse) throws RemoteException;

    void getTransCardState(IMiTsmResponse iMiTsmResponse) throws RemoteException;

    void isBankCardAvailable(IMiTsmResponse iMiTsmResponse) throws RemoteException;

    void manageBankCard(IMiTsmResponse iMiTsmResponse, String str, int i) throws RemoteException;

    void manageVirtualSimCard(IMiTsmResponse iMiTsmResponse, String str, int i) throws RemoteException;

    void notifyPayResult(IMiTsmResponse iMiTsmResponse, Bundle bundle) throws RemoteException;

    void requestInappTransaction(IMiTsmResponse iMiTsmResponse, Bundle bundle, int i) throws RemoteException;

    void requestPin(IMiTsmResponse iMiTsmResponse, int i) throws RemoteException;

    void setDefaultCard(IMiTsmResponse iMiTsmResponse, String str, Bundle bundle) throws RemoteException;

    void syncBankCardStatus(IMiTsmResponse iMiTsmResponse) throws RemoteException;

    public static abstract class Stub extends Binder implements IMiTsmService {
        private static final String DESCRIPTOR = "com.miui.tsmclientsdk.IMiTsmService";
        static final int TRANSACTION_addMiPayShortcut = 15;
        static final int TRANSACTION_addMipayCard = 21;
        static final int TRANSACTION_createSSD = 4;
        static final int TRANSACTION_deleteAllBankCard = 10;
        static final int TRANSACTION_deleteBankCard = 9;
        static final int TRANSACTION_getActiveCards = 7;
        static final int TRANSACTION_getCPLC = 1;
        static final int TRANSACTION_getCardInfo = 6;
        static final int TRANSACTION_getCardsQuantity = 5;
        static final int TRANSACTION_getCardsState = 22;
        static final int TRANSACTION_getDefaultCard = 2;
        static final int TRANSACTION_getMiPayStatus = 17;
        static final int TRANSACTION_getSeBankCards = 8;
        static final int TRANSACTION_getTransCardState = 13;
        static final int TRANSACTION_isBankCardAvailable = 12;
        static final int TRANSACTION_manageBankCard = 11;
        static final int TRANSACTION_manageVirtualSimCard = 16;
        static final int TRANSACTION_notifyPayResult = 20;
        static final int TRANSACTION_requestInappTransaction = 18;
        static final int TRANSACTION_requestPin = 19;
        static final int TRANSACTION_setDefaultCard = 3;
        static final int TRANSACTION_syncBankCardStatus = 14;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMiTsmService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMiTsmService)) {
                return new Proxy(iBinder);
            }
            return (IMiTsmService) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v13, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: android.os.Bundle} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r4, android.os.Parcel r5, android.os.Parcel r6, int r7) throws android.os.RemoteException {
            /*
                r3 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                if (r4 == r0) goto L_0x0238
                r0 = 0
                switch(r4) {
                    case 1: goto L_0x0224;
                    case 2: goto L_0x020c;
                    case 3: goto L_0x01e5;
                    case 4: goto L_0x01cd;
                    case 5: goto L_0x01b5;
                    case 6: goto L_0x019d;
                    case 7: goto L_0x0185;
                    case 8: goto L_0x0171;
                    case 9: goto L_0x0159;
                    case 10: goto L_0x0145;
                    case 11: goto L_0x0129;
                    case 12: goto L_0x0115;
                    case 13: goto L_0x0101;
                    case 14: goto L_0x00ed;
                    case 15: goto L_0x00d5;
                    case 16: goto L_0x00b9;
                    case 17: goto L_0x00a5;
                    case 18: goto L_0x007e;
                    case 19: goto L_0x0066;
                    case 20: goto L_0x0043;
                    case 21: goto L_0x0023;
                    case 22: goto L_0x000f;
                    default: goto L_0x000a;
                }
            L_0x000a:
                boolean r4 = super.onTransact(r4, r5, r6, r7)
                return r4
            L_0x000f:
                java.lang.String r4 = "com.miui.tsmclientsdk.IMiTsmService"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.miui.tsmclientsdk.IMiTsmResponse r4 = com.miui.tsmclientsdk.IMiTsmResponse.Stub.asInterface(r4)
                r3.getCardsState(r4)
                r6.writeNoException()
                return r1
            L_0x0023:
                java.lang.String r4 = "com.miui.tsmclientsdk.IMiTsmService"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.miui.tsmclientsdk.IMiTsmResponse r4 = com.miui.tsmclientsdk.IMiTsmResponse.Stub.asInterface(r4)
                java.lang.String r7 = r5.readString()
                int r0 = r5.readInt()
                java.lang.String r5 = r5.readString()
                r3.addMipayCard(r4, r7, r0, r5)
                r6.writeNoException()
                return r1
            L_0x0043:
                java.lang.String r4 = "com.miui.tsmclientsdk.IMiTsmService"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.miui.tsmclientsdk.IMiTsmResponse r4 = com.miui.tsmclientsdk.IMiTsmResponse.Stub.asInterface(r4)
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x005f
                android.os.Parcelable$Creator r7 = android.os.Bundle.CREATOR
                java.lang.Object r5 = r7.createFromParcel(r5)
                r0 = r5
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x005f:
                r3.notifyPayResult(r4, r0)
                r6.writeNoException()
                return r1
            L_0x0066:
                java.lang.String r4 = "com.miui.tsmclientsdk.IMiTsmService"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.miui.tsmclientsdk.IMiTsmResponse r4 = com.miui.tsmclientsdk.IMiTsmResponse.Stub.asInterface(r4)
                int r5 = r5.readInt()
                r3.requestPin(r4, r5)
                r6.writeNoException()
                return r1
            L_0x007e:
                java.lang.String r4 = "com.miui.tsmclientsdk.IMiTsmService"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.miui.tsmclientsdk.IMiTsmResponse r4 = com.miui.tsmclientsdk.IMiTsmResponse.Stub.asInterface(r4)
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x009a
                android.os.Parcelable$Creator r7 = android.os.Bundle.CREATOR
                java.lang.Object r7 = r7.createFromParcel(r5)
                r0 = r7
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x009a:
                int r5 = r5.readInt()
                r3.requestInappTransaction(r4, r0, r5)
                r6.writeNoException()
                return r1
            L_0x00a5:
                java.lang.String r4 = "com.miui.tsmclientsdk.IMiTsmService"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.miui.tsmclientsdk.IMiTsmResponse r4 = com.miui.tsmclientsdk.IMiTsmResponse.Stub.asInterface(r4)
                r3.getMiPayStatus(r4)
                r6.writeNoException()
                return r1
            L_0x00b9:
                java.lang.String r4 = "com.miui.tsmclientsdk.IMiTsmService"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.miui.tsmclientsdk.IMiTsmResponse r4 = com.miui.tsmclientsdk.IMiTsmResponse.Stub.asInterface(r4)
                java.lang.String r7 = r5.readString()
                int r5 = r5.readInt()
                r3.manageVirtualSimCard(r4, r7, r5)
                r6.writeNoException()
                return r1
            L_0x00d5:
                java.lang.String r4 = "com.miui.tsmclientsdk.IMiTsmService"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.miui.tsmclientsdk.IMiTsmResponse r4 = com.miui.tsmclientsdk.IMiTsmResponse.Stub.asInterface(r4)
                java.lang.String r5 = r5.readString()
                r3.addMiPayShortcut(r4, r5)
                r6.writeNoException()
                return r1
            L_0x00ed:
                java.lang.String r4 = "com.miui.tsmclientsdk.IMiTsmService"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.miui.tsmclientsdk.IMiTsmResponse r4 = com.miui.tsmclientsdk.IMiTsmResponse.Stub.asInterface(r4)
                r3.syncBankCardStatus(r4)
                r6.writeNoException()
                return r1
            L_0x0101:
                java.lang.String r4 = "com.miui.tsmclientsdk.IMiTsmService"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.miui.tsmclientsdk.IMiTsmResponse r4 = com.miui.tsmclientsdk.IMiTsmResponse.Stub.asInterface(r4)
                r3.getTransCardState(r4)
                r6.writeNoException()
                return r1
            L_0x0115:
                java.lang.String r4 = "com.miui.tsmclientsdk.IMiTsmService"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.miui.tsmclientsdk.IMiTsmResponse r4 = com.miui.tsmclientsdk.IMiTsmResponse.Stub.asInterface(r4)
                r3.isBankCardAvailable(r4)
                r6.writeNoException()
                return r1
            L_0x0129:
                java.lang.String r4 = "com.miui.tsmclientsdk.IMiTsmService"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.miui.tsmclientsdk.IMiTsmResponse r4 = com.miui.tsmclientsdk.IMiTsmResponse.Stub.asInterface(r4)
                java.lang.String r7 = r5.readString()
                int r5 = r5.readInt()
                r3.manageBankCard(r4, r7, r5)
                r6.writeNoException()
                return r1
            L_0x0145:
                java.lang.String r4 = "com.miui.tsmclientsdk.IMiTsmService"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.miui.tsmclientsdk.IMiTsmResponse r4 = com.miui.tsmclientsdk.IMiTsmResponse.Stub.asInterface(r4)
                r3.deleteAllBankCard(r4)
                r6.writeNoException()
                return r1
            L_0x0159:
                java.lang.String r4 = "com.miui.tsmclientsdk.IMiTsmService"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.miui.tsmclientsdk.IMiTsmResponse r4 = com.miui.tsmclientsdk.IMiTsmResponse.Stub.asInterface(r4)
                java.lang.String r5 = r5.readString()
                r3.deleteBankCard(r4, r5)
                r6.writeNoException()
                return r1
            L_0x0171:
                java.lang.String r4 = "com.miui.tsmclientsdk.IMiTsmService"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.miui.tsmclientsdk.IMiTsmResponse r4 = com.miui.tsmclientsdk.IMiTsmResponse.Stub.asInterface(r4)
                r3.getSeBankCards(r4)
                r6.writeNoException()
                return r1
            L_0x0185:
                java.lang.String r4 = "com.miui.tsmclientsdk.IMiTsmService"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.miui.tsmclientsdk.IMiTsmResponse r4 = com.miui.tsmclientsdk.IMiTsmResponse.Stub.asInterface(r4)
                java.lang.String r5 = r5.readString()
                r3.getActiveCards(r4, r5)
                r6.writeNoException()
                return r1
            L_0x019d:
                java.lang.String r4 = "com.miui.tsmclientsdk.IMiTsmService"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.miui.tsmclientsdk.IMiTsmResponse r4 = com.miui.tsmclientsdk.IMiTsmResponse.Stub.asInterface(r4)
                java.util.ArrayList r5 = r5.createStringArrayList()
                r3.getCardInfo(r4, r5)
                r6.writeNoException()
                return r1
            L_0x01b5:
                java.lang.String r4 = "com.miui.tsmclientsdk.IMiTsmService"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.miui.tsmclientsdk.IMiTsmResponse r4 = com.miui.tsmclientsdk.IMiTsmResponse.Stub.asInterface(r4)
                java.lang.String r5 = r5.readString()
                r3.getCardsQuantity(r4, r5)
                r6.writeNoException()
                return r1
            L_0x01cd:
                java.lang.String r4 = "com.miui.tsmclientsdk.IMiTsmService"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.miui.tsmclientsdk.IMiTsmResponse r4 = com.miui.tsmclientsdk.IMiTsmResponse.Stub.asInterface(r4)
                int r5 = r5.readInt()
                r3.createSSD(r4, r5)
                r6.writeNoException()
                return r1
            L_0x01e5:
                java.lang.String r4 = "com.miui.tsmclientsdk.IMiTsmService"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.miui.tsmclientsdk.IMiTsmResponse r4 = com.miui.tsmclientsdk.IMiTsmResponse.Stub.asInterface(r4)
                java.lang.String r7 = r5.readString()
                int r2 = r5.readInt()
                if (r2 == 0) goto L_0x0205
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r5 = r0.createFromParcel(r5)
                r0 = r5
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0205:
                r3.setDefaultCard(r4, r7, r0)
                r6.writeNoException()
                return r1
            L_0x020c:
                java.lang.String r4 = "com.miui.tsmclientsdk.IMiTsmService"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.miui.tsmclientsdk.IMiTsmResponse r4 = com.miui.tsmclientsdk.IMiTsmResponse.Stub.asInterface(r4)
                java.lang.String r5 = r5.readString()
                r3.getDefaultCard(r4, r5)
                r6.writeNoException()
                return r1
            L_0x0224:
                java.lang.String r4 = "com.miui.tsmclientsdk.IMiTsmService"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.miui.tsmclientsdk.IMiTsmResponse r4 = com.miui.tsmclientsdk.IMiTsmResponse.Stub.asInterface(r4)
                r3.getCPLC(r4)
                r6.writeNoException()
                return r1
            L_0x0238:
                java.lang.String r4 = "com.miui.tsmclientsdk.IMiTsmService"
                r6.writeString(r4)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.miui.tsmclientsdk.IMiTsmService.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IMiTsmService {
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

            public void getCPLC(IMiTsmResponse iMiTsmResponse) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMiTsmResponse != null ? iMiTsmResponse.asBinder() : null);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getDefaultCard(IMiTsmResponse iMiTsmResponse, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMiTsmResponse != null ? iMiTsmResponse.asBinder() : null);
                    obtain.writeString(str);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setDefaultCard(IMiTsmResponse iMiTsmResponse, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMiTsmResponse != null ? iMiTsmResponse.asBinder() : null);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
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

            public void createSSD(IMiTsmResponse iMiTsmResponse, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMiTsmResponse != null ? iMiTsmResponse.asBinder() : null);
                    obtain.writeInt(i);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getCardsQuantity(IMiTsmResponse iMiTsmResponse, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMiTsmResponse != null ? iMiTsmResponse.asBinder() : null);
                    obtain.writeString(str);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getCardInfo(IMiTsmResponse iMiTsmResponse, List<String> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMiTsmResponse != null ? iMiTsmResponse.asBinder() : null);
                    obtain.writeStringList(list);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getActiveCards(IMiTsmResponse iMiTsmResponse, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMiTsmResponse != null ? iMiTsmResponse.asBinder() : null);
                    obtain.writeString(str);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getSeBankCards(IMiTsmResponse iMiTsmResponse) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMiTsmResponse != null ? iMiTsmResponse.asBinder() : null);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void deleteBankCard(IMiTsmResponse iMiTsmResponse, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMiTsmResponse != null ? iMiTsmResponse.asBinder() : null);
                    obtain.writeString(str);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void deleteAllBankCard(IMiTsmResponse iMiTsmResponse) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMiTsmResponse != null ? iMiTsmResponse.asBinder() : null);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void manageBankCard(IMiTsmResponse iMiTsmResponse, String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMiTsmResponse != null ? iMiTsmResponse.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void isBankCardAvailable(IMiTsmResponse iMiTsmResponse) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMiTsmResponse != null ? iMiTsmResponse.asBinder() : null);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getTransCardState(IMiTsmResponse iMiTsmResponse) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMiTsmResponse != null ? iMiTsmResponse.asBinder() : null);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void syncBankCardStatus(IMiTsmResponse iMiTsmResponse) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMiTsmResponse != null ? iMiTsmResponse.asBinder() : null);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void addMiPayShortcut(IMiTsmResponse iMiTsmResponse, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMiTsmResponse != null ? iMiTsmResponse.asBinder() : null);
                    obtain.writeString(str);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void manageVirtualSimCard(IMiTsmResponse iMiTsmResponse, String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMiTsmResponse != null ? iMiTsmResponse.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getMiPayStatus(IMiTsmResponse iMiTsmResponse) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMiTsmResponse != null ? iMiTsmResponse.asBinder() : null);
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void requestInappTransaction(IMiTsmResponse iMiTsmResponse, Bundle bundle, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMiTsmResponse != null ? iMiTsmResponse.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    this.mRemote.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void requestPin(IMiTsmResponse iMiTsmResponse, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMiTsmResponse != null ? iMiTsmResponse.asBinder() : null);
                    obtain.writeInt(i);
                    this.mRemote.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void notifyPayResult(IMiTsmResponse iMiTsmResponse, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMiTsmResponse != null ? iMiTsmResponse.asBinder() : null);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void addMipayCard(IMiTsmResponse iMiTsmResponse, String str, int i, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMiTsmResponse != null ? iMiTsmResponse.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeString(str2);
                    this.mRemote.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getCardsState(IMiTsmResponse iMiTsmResponse) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMiTsmResponse != null ? iMiTsmResponse.asBinder() : null);
                    this.mRemote.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
