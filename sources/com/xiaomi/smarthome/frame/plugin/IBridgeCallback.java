package com.xiaomi.smarthome.frame.plugin;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xiaomi.smarthome.frame.plugin.runtime.bridge.BridgeError;

public interface IBridgeCallback extends IInterface {
    void onFailure(BridgeError bridgeError) throws RemoteException;

    void onHandle(boolean z) throws RemoteException;

    void onMessageFailure(BridgeError bridgeError) throws RemoteException;

    void onMessageSuccess(Bundle bundle) throws RemoteException;

    void onSendSuccess(Bundle bundle) throws RemoteException;

    public static abstract class Stub extends Binder implements IBridgeCallback {
        private static final String DESCRIPTOR = "com.xiaomi.smarthome.frame.plugin.IBridgeCallback";
        static final int TRANSACTION_onFailure = 5;
        static final int TRANSACTION_onHandle = 2;
        static final int TRANSACTION_onMessageFailure = 4;
        static final int TRANSACTION_onMessageSuccess = 3;
        static final int TRANSACTION_onSendSuccess = 1;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IBridgeCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IBridgeCallback)) {
                return new Proxy(iBinder);
            }
            return (IBridgeCallback) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: com.xiaomi.smarthome.frame.plugin.runtime.bridge.BridgeError} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v11, resolved type: com.xiaomi.smarthome.frame.plugin.runtime.bridge.BridgeError} */
        /* JADX WARNING: type inference failed for: r1v1 */
        /* JADX WARNING: type inference failed for: r1v14 */
        /* JADX WARNING: type inference failed for: r1v15 */
        /* JADX WARNING: type inference failed for: r1v16 */
        /* JADX WARNING: type inference failed for: r1v17 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r4, android.os.Parcel r5, android.os.Parcel r6, int r7) throws android.os.RemoteException {
            /*
                r3 = this;
                java.lang.String r0 = "com.xiaomi.smarthome.frame.plugin.IBridgeCallback"
                r1 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r2 = 1
                if (r4 == r1) goto L_0x0088
                r1 = 0
                switch(r4) {
                    case 1: goto L_0x006f;
                    case 2: goto L_0x005c;
                    case 3: goto L_0x0043;
                    case 4: goto L_0x002a;
                    case 5: goto L_0x0011;
                    default: goto L_0x000c;
                }
            L_0x000c:
                boolean r4 = super.onTransact(r4, r5, r6, r7)
                return r4
            L_0x0011:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0023
                android.os.Parcelable$Creator<com.xiaomi.smarthome.frame.plugin.runtime.bridge.BridgeError> r4 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.BridgeError.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.xiaomi.smarthome.frame.plugin.runtime.bridge.BridgeError r1 = (com.xiaomi.smarthome.frame.plugin.runtime.bridge.BridgeError) r1
            L_0x0023:
                r3.onFailure(r1)
                r6.writeNoException()
                return r2
            L_0x002a:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x003c
                android.os.Parcelable$Creator<com.xiaomi.smarthome.frame.plugin.runtime.bridge.BridgeError> r4 = com.xiaomi.smarthome.frame.plugin.runtime.bridge.BridgeError.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.xiaomi.smarthome.frame.plugin.runtime.bridge.BridgeError r1 = (com.xiaomi.smarthome.frame.plugin.runtime.bridge.BridgeError) r1
            L_0x003c:
                r3.onMessageFailure(r1)
                r6.writeNoException()
                return r2
            L_0x0043:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0055
                android.os.Parcelable$Creator r4 = android.os.Bundle.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                android.os.Bundle r1 = (android.os.Bundle) r1
            L_0x0055:
                r3.onMessageSuccess(r1)
                r6.writeNoException()
                return r2
            L_0x005c:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0067
                r4 = 1
                goto L_0x0068
            L_0x0067:
                r4 = 0
            L_0x0068:
                r3.onHandle(r4)
                r6.writeNoException()
                return r2
            L_0x006f:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0081
                android.os.Parcelable$Creator r4 = android.os.Bundle.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                android.os.Bundle r1 = (android.os.Bundle) r1
            L_0x0081:
                r3.onSendSuccess(r1)
                r6.writeNoException()
                return r2
            L_0x0088:
                r6.writeString(r0)
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.plugin.IBridgeCallback.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IBridgeCallback {
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

            public void onSendSuccess(Bundle bundle) throws RemoteException {
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
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onHandle(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onMessageSuccess(Bundle bundle) throws RemoteException {
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
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onMessageFailure(BridgeError bridgeError) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bridgeError != null) {
                        obtain.writeInt(1);
                        bridgeError.writeToParcel(obtain, 0);
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

            public void onFailure(BridgeError bridgeError) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bridgeError != null) {
                        obtain.writeInt(1);
                        bridgeError.writeToParcel(obtain, 0);
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
        }
    }
}
