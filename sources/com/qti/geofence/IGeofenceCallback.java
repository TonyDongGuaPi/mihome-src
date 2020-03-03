package com.qti.geofence;

import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IGeofenceCallback extends IInterface {

    public static class Default implements IGeofenceCallback {
        public IBinder asBinder() {
            return null;
        }

        public void onEngineReportStatus(int i, Location location) throws RemoteException {
        }

        public void onRequestResultReturned(int i, int i2, int i3) throws RemoteException {
        }

        public void onTransitionEvent(int i, int i2, Location location) throws RemoteException {
        }
    }

    void onEngineReportStatus(int i, Location location) throws RemoteException;

    void onRequestResultReturned(int i, int i2, int i3) throws RemoteException;

    void onTransitionEvent(int i, int i2, Location location) throws RemoteException;

    public static abstract class Stub extends Binder implements IGeofenceCallback {
        private static final String DESCRIPTOR = "com.qti.geofence.IGeofenceCallback";
        static final int TRANSACTION_onEngineReportStatus = 3;
        static final int TRANSACTION_onRequestResultReturned = 2;
        static final int TRANSACTION_onTransitionEvent = 1;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IGeofenceCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IGeofenceCallback)) {
                return new Proxy(iBinder);
            }
            return (IGeofenceCallback) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: android.location.Location} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: android.location.Location} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r4, android.os.Parcel r5, android.os.Parcel r6, int r7) throws android.os.RemoteException {
            /*
                r3 = this;
                java.lang.String r0 = "com.qti.geofence.IGeofenceCallback"
                r1 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r2 = 1
                if (r4 == r1) goto L_0x005c
                r1 = 0
                switch(r4) {
                    case 1: goto L_0x003e;
                    case 2: goto L_0x002b;
                    case 3: goto L_0x0011;
                    default: goto L_0x000c;
                }
            L_0x000c:
                boolean r4 = super.onTransact(r4, r5, r6, r7)
                return r4
            L_0x0011:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                int r6 = r5.readInt()
                if (r6 == 0) goto L_0x0027
                android.os.Parcelable$Creator r6 = android.location.Location.CREATOR
                java.lang.Object r5 = r6.createFromParcel(r5)
                r1 = r5
                android.location.Location r1 = (android.location.Location) r1
            L_0x0027:
                r3.onEngineReportStatus(r4, r1)
                return r2
            L_0x002b:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                int r6 = r5.readInt()
                int r5 = r5.readInt()
                r3.onRequestResultReturned(r4, r6, r5)
                return r2
            L_0x003e:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                int r6 = r5.readInt()
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x0058
                android.os.Parcelable$Creator r7 = android.location.Location.CREATOR
                java.lang.Object r5 = r7.createFromParcel(r5)
                r1 = r5
                android.location.Location r1 = (android.location.Location) r1
            L_0x0058:
                r3.onTransitionEvent(r4, r6, r1)
                return r2
            L_0x005c:
                r6.writeString(r0)
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qti.geofence.IGeofenceCallback.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IGeofenceCallback {
            public static IGeofenceCallback sDefaultImpl;
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

            public void onTransitionEvent(int i, int i2, Location location) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (location != null) {
                        obtain.writeInt(1);
                        location.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, obtain, (Parcel) null, 1) || Stub.getDefaultImpl() == null) {
                        obtain.recycle();
                    } else {
                        Stub.getDefaultImpl().onTransitionEvent(i, i2, location);
                    }
                } finally {
                    obtain.recycle();
                }
            }

            public void onRequestResultReturned(int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    if (this.mRemote.transact(2, obtain, (Parcel) null, 1) || Stub.getDefaultImpl() == null) {
                        obtain.recycle();
                    } else {
                        Stub.getDefaultImpl().onRequestResultReturned(i, i2, i3);
                    }
                } finally {
                    obtain.recycle();
                }
            }

            public void onEngineReportStatus(int i, Location location) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (location != null) {
                        obtain.writeInt(1);
                        location.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, obtain, (Parcel) null, 1) || Stub.getDefaultImpl() == null) {
                        obtain.recycle();
                    } else {
                        Stub.getDefaultImpl().onEngineReportStatus(i, location);
                    }
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IGeofenceCallback iGeofenceCallback) {
            if (Proxy.sDefaultImpl != null || iGeofenceCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iGeofenceCallback;
            return true;
        }

        public static IGeofenceCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
