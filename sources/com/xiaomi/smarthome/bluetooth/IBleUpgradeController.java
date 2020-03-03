package com.xiaomi.smarthome.bluetooth;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IBleUpgradeController extends IInterface {
    void attachUpgradeCaller(IBleUpgradeViewer iBleUpgradeViewer) throws RemoteException;

    void detachUpgradeCaller() throws RemoteException;

    String getCurrentVersion() throws RemoteException;

    String getLatestVersion() throws RemoteException;

    String getUpgradeDescription() throws RemoteException;

    void onActivityCreated(Bundle bundle) throws RemoteException;

    boolean onPreEnterActivity(Bundle bundle) throws RemoteException;

    void startUpgrade() throws RemoteException;

    public static abstract class Stub extends Binder implements IBleUpgradeController {
        private static final String DESCRIPTOR = "com.xiaomi.smarthome.bluetooth.IBleUpgradeController";
        static final int TRANSACTION_attachUpgradeCaller = 6;
        static final int TRANSACTION_detachUpgradeCaller = 7;
        static final int TRANSACTION_getCurrentVersion = 1;
        static final int TRANSACTION_getLatestVersion = 2;
        static final int TRANSACTION_getUpgradeDescription = 3;
        static final int TRANSACTION_onActivityCreated = 5;
        static final int TRANSACTION_onPreEnterActivity = 8;
        static final int TRANSACTION_startUpgrade = 4;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IBleUpgradeController asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IBleUpgradeController)) {
                return new Proxy(iBinder);
            }
            return (IBleUpgradeController) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v12, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: android.os.Bundle} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r4, android.os.Parcel r5, android.os.Parcel r6, int r7) throws android.os.RemoteException {
            /*
                r3 = this;
                java.lang.String r0 = "com.xiaomi.smarthome.bluetooth.IBleUpgradeController"
                r1 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r2 = 1
                if (r4 == r1) goto L_0x0097
                r1 = 0
                switch(r4) {
                    case 1: goto L_0x0089;
                    case 2: goto L_0x007b;
                    case 3: goto L_0x006d;
                    case 4: goto L_0x0063;
                    case 5: goto L_0x004a;
                    case 6: goto L_0x0038;
                    case 7: goto L_0x002e;
                    case 8: goto L_0x0011;
                    default: goto L_0x000c;
                }
            L_0x000c:
                boolean r4 = super.onTransact(r4, r5, r6, r7)
                return r4
            L_0x0011:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0023
                android.os.Parcelable$Creator r4 = android.os.Bundle.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                android.os.Bundle r1 = (android.os.Bundle) r1
            L_0x0023:
                boolean r4 = r3.onPreEnterActivity(r1)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x002e:
                r5.enforceInterface(r0)
                r3.detachUpgradeCaller()
                r6.writeNoException()
                return r2
            L_0x0038:
                r5.enforceInterface(r0)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.xiaomi.smarthome.bluetooth.IBleUpgradeViewer r4 = com.xiaomi.smarthome.bluetooth.IBleUpgradeViewer.Stub.asInterface(r4)
                r3.attachUpgradeCaller(r4)
                r6.writeNoException()
                return r2
            L_0x004a:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x005c
                android.os.Parcelable$Creator r4 = android.os.Bundle.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                android.os.Bundle r1 = (android.os.Bundle) r1
            L_0x005c:
                r3.onActivityCreated(r1)
                r6.writeNoException()
                return r2
            L_0x0063:
                r5.enforceInterface(r0)
                r3.startUpgrade()
                r6.writeNoException()
                return r2
            L_0x006d:
                r5.enforceInterface(r0)
                java.lang.String r4 = r3.getUpgradeDescription()
                r6.writeNoException()
                r6.writeString(r4)
                return r2
            L_0x007b:
                r5.enforceInterface(r0)
                java.lang.String r4 = r3.getLatestVersion()
                r6.writeNoException()
                r6.writeString(r4)
                return r2
            L_0x0089:
                r5.enforceInterface(r0)
                java.lang.String r4 = r3.getCurrentVersion()
                r6.writeNoException()
                r6.writeString(r4)
                return r2
            L_0x0097:
                r6.writeString(r0)
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.bluetooth.IBleUpgradeController.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IBleUpgradeController {
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

            public String getCurrentVersion() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getLatestVersion() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getUpgradeDescription() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void startUpgrade() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onActivityCreated(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void attachUpgradeCaller(IBleUpgradeViewer iBleUpgradeViewer) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBleUpgradeViewer != null ? iBleUpgradeViewer.asBinder() : null);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void detachUpgradeCaller() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean onPreEnterActivity(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(8, obtain, obtain2, 0);
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
        }
    }
}
