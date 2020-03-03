package com.xiaomi.smarthome.core.client;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xiaomi.smarthome.core.entity.message.CoreMessageType;
import com.xiaomi.smarthome.core.server.IServerCallback;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;

public interface IClientApi extends IInterface {
    void onAccountReady(boolean z, String str) throws RemoteException;

    void onActivityResume(int i, int i2, String str) throws RemoteException;

    void onBleCharacterChanged(Bundle bundle) throws RemoteException;

    void onCoreMessage(CoreMessageType coreMessageType, Bundle bundle) throws RemoteException;

    void onCoreReady() throws RemoteException;

    void onGlobalDynamicSettingReady() throws RemoteException;

    void onLocaleChanged(Bundle bundle, Bundle bundle2) throws RemoteException;

    void onPluginChanged(boolean z, boolean z2, String str) throws RemoteException;

    void onPluginReady() throws RemoteException;

    void onServerChanged(ServerBean serverBean, ServerBean serverBean2) throws RemoteException;

    void onStatisticReady() throws RemoteException;

    void onUnAuthorized() throws RemoteException;

    void refreshServiceToken(String str, String str2, boolean z, String str3, IServerCallback iServerCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IClientApi {
        private static final String DESCRIPTOR = "com.xiaomi.smarthome.core.client.IClientApi";
        static final int TRANSACTION_onAccountReady = 1;
        static final int TRANSACTION_onActivityResume = 12;
        static final int TRANSACTION_onBleCharacterChanged = 13;
        static final int TRANSACTION_onCoreMessage = 8;
        static final int TRANSACTION_onCoreReady = 5;
        static final int TRANSACTION_onGlobalDynamicSettingReady = 2;
        static final int TRANSACTION_onLocaleChanged = 10;
        static final int TRANSACTION_onPluginChanged = 11;
        static final int TRANSACTION_onPluginReady = 4;
        static final int TRANSACTION_onServerChanged = 9;
        static final int TRANSACTION_onStatisticReady = 3;
        static final int TRANSACTION_onUnAuthorized = 7;
        static final int TRANSACTION_refreshServiceToken = 6;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IClientApi asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IClientApi)) {
                return new Proxy(iBinder);
            }
            return (IClientApi) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: com.xiaomi.smarthome.frame.server_compact.ServerBean} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: android.os.Bundle} */
        /* JADX WARNING: type inference failed for: r3v0 */
        /* JADX WARNING: type inference failed for: r3v13 */
        /* JADX WARNING: type inference failed for: r3v14 */
        /* JADX WARNING: type inference failed for: r3v15 */
        /* JADX WARNING: type inference failed for: r3v16 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r11, android.os.Parcel r12, android.os.Parcel r13, int r14) throws android.os.RemoteException {
            /*
                r10 = this;
                java.lang.String r0 = "com.xiaomi.smarthome.core.client.IClientApi"
                r1 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r2 = 1
                if (r11 == r1) goto L_0x0149
                r1 = 0
                r3 = 0
                switch(r11) {
                    case 1: goto L_0x0134;
                    case 2: goto L_0x012a;
                    case 3: goto L_0x0120;
                    case 4: goto L_0x0116;
                    case 5: goto L_0x010c;
                    case 6: goto L_0x00e4;
                    case 7: goto L_0x00da;
                    case 8: goto L_0x00b1;
                    case 9: goto L_0x0088;
                    case 10: goto L_0x005f;
                    case 11: goto L_0x0041;
                    case 12: goto L_0x002b;
                    case 13: goto L_0x0012;
                    default: goto L_0x000d;
                }
            L_0x000d:
                boolean r11 = super.onTransact(r11, r12, r13, r14)
                return r11
            L_0x0012:
                r12.enforceInterface(r0)
                int r11 = r12.readInt()
                if (r11 == 0) goto L_0x0024
                android.os.Parcelable$Creator r11 = android.os.Bundle.CREATOR
                java.lang.Object r11 = r11.createFromParcel(r12)
                r3 = r11
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x0024:
                r10.onBleCharacterChanged(r3)
                r13.writeNoException()
                return r2
            L_0x002b:
                r12.enforceInterface(r0)
                int r11 = r12.readInt()
                int r14 = r12.readInt()
                java.lang.String r12 = r12.readString()
                r10.onActivityResume(r11, r14, r12)
                r13.writeNoException()
                return r2
            L_0x0041:
                r12.enforceInterface(r0)
                int r11 = r12.readInt()
                if (r11 == 0) goto L_0x004c
                r11 = 1
                goto L_0x004d
            L_0x004c:
                r11 = 0
            L_0x004d:
                int r14 = r12.readInt()
                if (r14 == 0) goto L_0x0054
                r1 = 1
            L_0x0054:
                java.lang.String r12 = r12.readString()
                r10.onPluginChanged(r11, r1, r12)
                r13.writeNoException()
                return r2
            L_0x005f:
                r12.enforceInterface(r0)
                int r11 = r12.readInt()
                if (r11 == 0) goto L_0x0071
                android.os.Parcelable$Creator r11 = android.os.Bundle.CREATOR
                java.lang.Object r11 = r11.createFromParcel(r12)
                android.os.Bundle r11 = (android.os.Bundle) r11
                goto L_0x0072
            L_0x0071:
                r11 = r3
            L_0x0072:
                int r14 = r12.readInt()
                if (r14 == 0) goto L_0x0081
                android.os.Parcelable$Creator r14 = android.os.Bundle.CREATOR
                java.lang.Object r12 = r14.createFromParcel(r12)
                r3 = r12
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x0081:
                r10.onLocaleChanged(r11, r3)
                r13.writeNoException()
                return r2
            L_0x0088:
                r12.enforceInterface(r0)
                int r11 = r12.readInt()
                if (r11 == 0) goto L_0x009a
                android.os.Parcelable$Creator<com.xiaomi.smarthome.frame.server_compact.ServerBean> r11 = com.xiaomi.smarthome.frame.server_compact.ServerBean.CREATOR
                java.lang.Object r11 = r11.createFromParcel(r12)
                com.xiaomi.smarthome.frame.server_compact.ServerBean r11 = (com.xiaomi.smarthome.frame.server_compact.ServerBean) r11
                goto L_0x009b
            L_0x009a:
                r11 = r3
            L_0x009b:
                int r14 = r12.readInt()
                if (r14 == 0) goto L_0x00aa
                android.os.Parcelable$Creator<com.xiaomi.smarthome.frame.server_compact.ServerBean> r14 = com.xiaomi.smarthome.frame.server_compact.ServerBean.CREATOR
                java.lang.Object r12 = r14.createFromParcel(r12)
                r3 = r12
                com.xiaomi.smarthome.frame.server_compact.ServerBean r3 = (com.xiaomi.smarthome.frame.server_compact.ServerBean) r3
            L_0x00aa:
                r10.onServerChanged(r11, r3)
                r13.writeNoException()
                return r2
            L_0x00b1:
                r12.enforceInterface(r0)
                int r11 = r12.readInt()
                if (r11 == 0) goto L_0x00c3
                android.os.Parcelable$Creator<com.xiaomi.smarthome.core.entity.message.CoreMessageType> r11 = com.xiaomi.smarthome.core.entity.message.CoreMessageType.CREATOR
                java.lang.Object r11 = r11.createFromParcel(r12)
                com.xiaomi.smarthome.core.entity.message.CoreMessageType r11 = (com.xiaomi.smarthome.core.entity.message.CoreMessageType) r11
                goto L_0x00c4
            L_0x00c3:
                r11 = r3
            L_0x00c4:
                int r14 = r12.readInt()
                if (r14 == 0) goto L_0x00d3
                android.os.Parcelable$Creator r14 = android.os.Bundle.CREATOR
                java.lang.Object r12 = r14.createFromParcel(r12)
                r3 = r12
                android.os.Bundle r3 = (android.os.Bundle) r3
            L_0x00d3:
                r10.onCoreMessage(r11, r3)
                r13.writeNoException()
                return r2
            L_0x00da:
                r12.enforceInterface(r0)
                r10.onUnAuthorized()
                r13.writeNoException()
                return r2
            L_0x00e4:
                r12.enforceInterface(r0)
                java.lang.String r5 = r12.readString()
                java.lang.String r6 = r12.readString()
                int r11 = r12.readInt()
                if (r11 == 0) goto L_0x00f7
                r7 = 1
                goto L_0x00f8
            L_0x00f7:
                r7 = 0
            L_0x00f8:
                java.lang.String r8 = r12.readString()
                android.os.IBinder r11 = r12.readStrongBinder()
                com.xiaomi.smarthome.core.server.IServerCallback r9 = com.xiaomi.smarthome.core.server.IServerCallback.Stub.asInterface(r11)
                r4 = r10
                r4.refreshServiceToken(r5, r6, r7, r8, r9)
                r13.writeNoException()
                return r2
            L_0x010c:
                r12.enforceInterface(r0)
                r10.onCoreReady()
                r13.writeNoException()
                return r2
            L_0x0116:
                r12.enforceInterface(r0)
                r10.onPluginReady()
                r13.writeNoException()
                return r2
            L_0x0120:
                r12.enforceInterface(r0)
                r10.onStatisticReady()
                r13.writeNoException()
                return r2
            L_0x012a:
                r12.enforceInterface(r0)
                r10.onGlobalDynamicSettingReady()
                r13.writeNoException()
                return r2
            L_0x0134:
                r12.enforceInterface(r0)
                int r11 = r12.readInt()
                if (r11 == 0) goto L_0x013e
                r1 = 1
            L_0x013e:
                java.lang.String r11 = r12.readString()
                r10.onAccountReady(r1, r11)
                r13.writeNoException()
                return r2
            L_0x0149:
                r13.writeString(r0)
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.client.IClientApi.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IClientApi {
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

            public void onAccountReady(boolean z, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeString(str);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onGlobalDynamicSettingReady() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onStatisticReady() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onPluginReady() throws RemoteException {
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

            public void onCoreReady() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void refreshServiceToken(String str, String str2, boolean z, String str3, IServerCallback iServerCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeString(str3);
                    obtain.writeStrongBinder(iServerCallback != null ? iServerCallback.asBinder() : null);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onUnAuthorized() throws RemoteException {
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

            public void onCoreMessage(CoreMessageType coreMessageType, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (coreMessageType != null) {
                        obtain.writeInt(1);
                        coreMessageType.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onServerChanged(ServerBean serverBean, ServerBean serverBean2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (serverBean != null) {
                        obtain.writeInt(1);
                        serverBean.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (serverBean2 != null) {
                        obtain.writeInt(1);
                        serverBean2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onLocaleChanged(Bundle bundle, Bundle bundle2) throws RemoteException {
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
                    if (bundle2 != null) {
                        obtain.writeInt(1);
                        bundle2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onPluginChanged(boolean z, boolean z2, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(z2 ? 1 : 0);
                    obtain.writeString(str);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onActivityResume(int i, int i2, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onBleCharacterChanged(Bundle bundle) throws RemoteException {
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
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
