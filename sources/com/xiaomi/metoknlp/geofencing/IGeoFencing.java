package com.xiaomi.metoknlp.geofencing;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IGeoFencing extends IInterface {
    String getDeviceLocation() throws RemoteException;

    List<String> getLocationSsids(String str) throws RemoteException;

    int getVersion() throws RemoteException;

    void registerFenceListener(double d, double d2, float f, long j, String str, String str2, String str3) throws RemoteException;

    void registerFenceListenerByIntent(double d, double d2, float f, long j, PendingIntent pendingIntent, String str, String str2) throws RemoteException;

    void setLocationListener(List<String> list, String str) throws RemoteException;

    void unregisterFenceListener(String str, String str2) throws RemoteException;

    void unregisterFenceListenerByIntent(PendingIntent pendingIntent) throws RemoteException;

    void unsetLocationListener(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IGeoFencing {
        private static final String DESCRIPTOR = "com.xiaomi.metoknlp.geofencing.IGeoFencing";
        static final int TRANSACTION_getDeviceLocation = 3;
        static final int TRANSACTION_getLocationSsids = 4;
        static final int TRANSACTION_getVersion = 5;
        static final int TRANSACTION_registerFenceListener = 6;
        static final int TRANSACTION_registerFenceListenerByIntent = 7;
        static final int TRANSACTION_setLocationListener = 1;
        static final int TRANSACTION_unregisterFenceListener = 8;
        static final int TRANSACTION_unregisterFenceListenerByIntent = 9;
        static final int TRANSACTION_unsetLocationListener = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IGeoFencing asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IGeoFencing)) {
                return new Proxy(iBinder);
            }
            return (IGeoFencing) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v26, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: android.app.PendingIntent} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r17, android.os.Parcel r18, android.os.Parcel r19, int r20) throws android.os.RemoteException {
            /*
                r16 = this;
                r11 = r16
                r0 = r17
                r1 = r18
                r2 = r19
                r3 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r12 = 1
                if (r0 == r3) goto L_0x00fb
                r3 = 0
                switch(r0) {
                    case 1: goto L_0x00ea;
                    case 2: goto L_0x00dd;
                    case 3: goto L_0x00cd;
                    case 4: goto L_0x00b9;
                    case 5: goto L_0x00a9;
                    case 6: goto L_0x007b;
                    case 7: goto L_0x0040;
                    case 8: goto L_0x002f;
                    case 9: goto L_0x0017;
                    default: goto L_0x0012;
                }
            L_0x0012:
                boolean r0 = super.onTransact(r17, r18, r19, r20)
                return r0
            L_0x0017:
                java.lang.String r0 = "com.xiaomi.metoknlp.geofencing.IGeoFencing"
                r1.enforceInterface(r0)
                int r0 = r18.readInt()
                if (r0 == 0) goto L_0x002b
                android.os.Parcelable$Creator r0 = android.app.PendingIntent.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r1)
                r3 = r0
                android.app.PendingIntent r3 = (android.app.PendingIntent) r3
            L_0x002b:
                r11.unregisterFenceListenerByIntent(r3)
                return r12
            L_0x002f:
                java.lang.String r0 = "com.xiaomi.metoknlp.geofencing.IGeoFencing"
                r1.enforceInterface(r0)
                java.lang.String r0 = r18.readString()
                java.lang.String r1 = r18.readString()
                r11.unregisterFenceListener(r0, r1)
                return r12
            L_0x0040:
                java.lang.String r0 = "com.xiaomi.metoknlp.geofencing.IGeoFencing"
                r1.enforceInterface(r0)
                double r4 = r18.readDouble()
                double r6 = r18.readDouble()
                float r8 = r18.readFloat()
                long r9 = r18.readLong()
                int r0 = r18.readInt()
                if (r0 == 0) goto L_0x0065
                android.os.Parcelable$Creator r0 = android.app.PendingIntent.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r1)
                android.app.PendingIntent r0 = (android.app.PendingIntent) r0
                r13 = r0
                goto L_0x0066
            L_0x0065:
                r13 = r3
            L_0x0066:
                java.lang.String r14 = r18.readString()
                java.lang.String r15 = r18.readString()
                r0 = r16
                r1 = r4
                r3 = r6
                r5 = r8
                r6 = r9
                r8 = r13
                r9 = r14
                r10 = r15
                r0.registerFenceListenerByIntent(r1, r3, r5, r6, r8, r9, r10)
                return r12
            L_0x007b:
                java.lang.String r0 = "com.xiaomi.metoknlp.geofencing.IGeoFencing"
                r1.enforceInterface(r0)
                double r2 = r18.readDouble()
                double r4 = r18.readDouble()
                float r6 = r18.readFloat()
                long r7 = r18.readLong()
                java.lang.String r9 = r18.readString()
                java.lang.String r10 = r18.readString()
                java.lang.String r13 = r18.readString()
                r0 = r16
                r1 = r2
                r3 = r4
                r5 = r6
                r6 = r7
                r8 = r9
                r9 = r10
                r10 = r13
                r0.registerFenceListener(r1, r3, r5, r6, r8, r9, r10)
                return r12
            L_0x00a9:
                java.lang.String r0 = "com.xiaomi.metoknlp.geofencing.IGeoFencing"
                r1.enforceInterface(r0)
                int r0 = r16.getVersion()
                r19.writeNoException()
                r2.writeInt(r0)
                return r12
            L_0x00b9:
                java.lang.String r0 = "com.xiaomi.metoknlp.geofencing.IGeoFencing"
                r1.enforceInterface(r0)
                java.lang.String r0 = r18.readString()
                java.util.List r0 = r11.getLocationSsids(r0)
                r19.writeNoException()
                r2.writeStringList(r0)
                return r12
            L_0x00cd:
                java.lang.String r0 = "com.xiaomi.metoknlp.geofencing.IGeoFencing"
                r1.enforceInterface(r0)
                java.lang.String r0 = r16.getDeviceLocation()
                r19.writeNoException()
                r2.writeString(r0)
                return r12
            L_0x00dd:
                java.lang.String r0 = "com.xiaomi.metoknlp.geofencing.IGeoFencing"
                r1.enforceInterface(r0)
                java.lang.String r0 = r18.readString()
                r11.unsetLocationListener(r0)
                return r12
            L_0x00ea:
                java.lang.String r0 = "com.xiaomi.metoknlp.geofencing.IGeoFencing"
                r1.enforceInterface(r0)
                java.util.ArrayList r0 = r18.createStringArrayList()
                java.lang.String r1 = r18.readString()
                r11.setLocationListener(r0, r1)
                return r12
            L_0x00fb:
                java.lang.String r0 = "com.xiaomi.metoknlp.geofencing.IGeoFencing"
                r2.writeString(r0)
                return r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.metoknlp.geofencing.IGeoFencing.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IGeoFencing {
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

            public void setLocationListener(List<String> list, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStringList(list);
                    obtain.writeString(str);
                    this.mRemote.transact(1, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void unsetLocationListener(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public String getDeviceLocation() throws RemoteException {
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

            public List<String> getLocationSsids(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createStringArrayList();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getVersion() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void registerFenceListener(double d, double d2, float f, long j, String str, String str2, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeDouble(d);
                    obtain.writeDouble(d2);
                    obtain.writeFloat(f);
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    this.mRemote.transact(6, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void registerFenceListenerByIntent(double d, double d2, float f, long j, PendingIntent pendingIntent, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeDouble(d);
                    obtain.writeDouble(d2);
                    obtain.writeFloat(f);
                    obtain.writeLong(j);
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(7, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void unregisterFenceListener(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(8, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void unregisterFenceListenerByIntent(PendingIntent pendingIntent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(9, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }
    }
}
