package com.xiaomi.smarthome.frame.plugin;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IBridgeServiceApi extends IInterface {
    void exitProcess() throws RemoteException;

    boolean isProcessForeground() throws RemoteException;

    void sendMessage(String str, String str2, int i, Intent intent, boolean z, IBridgeCallback iBridgeCallback) throws RemoteException;

    void startService(String str, long j, long j2, Intent intent, String str2) throws RemoteException;

    public static abstract class Stub extends Binder implements IBridgeServiceApi {
        private static final String DESCRIPTOR = "com.xiaomi.smarthome.frame.plugin.IBridgeServiceApi";
        static final int TRANSACTION_exitProcess = 3;
        static final int TRANSACTION_isProcessForeground = 4;
        static final int TRANSACTION_sendMessage = 1;
        static final int TRANSACTION_startService = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IBridgeServiceApi asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IBridgeServiceApi)) {
                return new Proxy(iBinder);
            }
            return (IBridgeServiceApi) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: android.content.Intent} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: android.content.Intent} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r22, android.os.Parcel r23, android.os.Parcel r24, int r25) throws android.os.RemoteException {
            /*
                r21 = this;
                r0 = r22
                r1 = r23
                r2 = r24
                java.lang.String r3 = "com.xiaomi.smarthome.frame.plugin.IBridgeServiceApi"
                r4 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r5 = 1
                if (r0 == r4) goto L_0x0098
                r4 = 0
                switch(r0) {
                    case 1: goto L_0x005b;
                    case 2: goto L_0x002f;
                    case 3: goto L_0x0025;
                    case 4: goto L_0x0017;
                    default: goto L_0x0012;
                }
            L_0x0012:
                boolean r0 = super.onTransact(r22, r23, r24, r25)
                return r0
            L_0x0017:
                r1.enforceInterface(r3)
                boolean r0 = r21.isProcessForeground()
                r24.writeNoException()
                r2.writeInt(r0)
                return r5
            L_0x0025:
                r1.enforceInterface(r3)
                r21.exitProcess()
                r24.writeNoException()
                return r5
            L_0x002f:
                r1.enforceInterface(r3)
                java.lang.String r7 = r23.readString()
                long r8 = r23.readLong()
                long r10 = r23.readLong()
                int r0 = r23.readInt()
                if (r0 == 0) goto L_0x004d
                android.os.Parcelable$Creator r0 = android.content.Intent.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r1)
                r4 = r0
                android.content.Intent r4 = (android.content.Intent) r4
            L_0x004d:
                r12 = r4
                java.lang.String r13 = r23.readString()
                r6 = r21
                r6.startService(r7, r8, r10, r12, r13)
                r24.writeNoException()
                return r5
            L_0x005b:
                r1.enforceInterface(r3)
                java.lang.String r15 = r23.readString()
                java.lang.String r16 = r23.readString()
                int r17 = r23.readInt()
                int r0 = r23.readInt()
                if (r0 == 0) goto L_0x0079
                android.os.Parcelable$Creator r0 = android.content.Intent.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r1)
                r4 = r0
                android.content.Intent r4 = (android.content.Intent) r4
            L_0x0079:
                r18 = r4
                int r0 = r23.readInt()
                if (r0 == 0) goto L_0x0084
                r19 = 1
                goto L_0x0087
            L_0x0084:
                r0 = 0
                r19 = 0
            L_0x0087:
                android.os.IBinder r0 = r23.readStrongBinder()
                com.xiaomi.smarthome.frame.plugin.IBridgeCallback r20 = com.xiaomi.smarthome.frame.plugin.IBridgeCallback.Stub.asInterface(r0)
                r14 = r21
                r14.sendMessage(r15, r16, r17, r18, r19, r20)
                r24.writeNoException()
                return r5
            L_0x0098:
                r2.writeString(r3)
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.plugin.IBridgeServiceApi.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IBridgeServiceApi {
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

            public void sendMessage(String str, String str2, int i, Intent intent, boolean z, IBridgeCallback iBridgeCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeStrongBinder(iBridgeCallback != null ? iBridgeCallback.asBinder() : null);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void startService(String str, long j, long j2, Intent intent, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str2);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void exitProcess() throws RemoteException {
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

            public boolean isProcessForeground() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(4, obtain, obtain2, 0);
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
