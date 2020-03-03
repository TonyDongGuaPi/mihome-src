package com.qti.geofence;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IGeofenceService extends IInterface {

    public static class Default implements IGeofenceService {
        public int addGeofence(double d, double d2, double d3, int i, int i2, int i3, int i4, int i5) throws RemoteException {
            return 0;
        }

        public int addGeofenceObj(GeofenceData geofenceData) throws RemoteException {
            return 0;
        }

        public IBinder asBinder() {
            return null;
        }

        public void pauseGeofence(int i) throws RemoteException {
        }

        public void recoverGeofences(List<GeofenceData> list) throws RemoteException {
        }

        public void registerCallback(IGeofenceCallback iGeofenceCallback) throws RemoteException {
        }

        public void registerPendingIntent(PendingIntent pendingIntent) throws RemoteException {
        }

        public void removeGeofence(int i) throws RemoteException {
        }

        public void resumeGeofence(int i) throws RemoteException {
        }

        public void unregisterCallback(IGeofenceCallback iGeofenceCallback) throws RemoteException {
        }

        public void unregisterPendingIntent(PendingIntent pendingIntent) throws RemoteException {
        }

        public void updateGeofence(int i, int i2, int i3) throws RemoteException {
        }

        public void updateGeofenceData(int i, double d, double d2, double d3, int i2, int i3, int i4, int i5, int i6, int i7) throws RemoteException {
        }
    }

    int addGeofence(double d, double d2, double d3, int i, int i2, int i3, int i4, int i5) throws RemoteException;

    int addGeofenceObj(GeofenceData geofenceData) throws RemoteException;

    void pauseGeofence(int i) throws RemoteException;

    void recoverGeofences(List<GeofenceData> list) throws RemoteException;

    void registerCallback(IGeofenceCallback iGeofenceCallback) throws RemoteException;

    void registerPendingIntent(PendingIntent pendingIntent) throws RemoteException;

    void removeGeofence(int i) throws RemoteException;

    void resumeGeofence(int i) throws RemoteException;

    void unregisterCallback(IGeofenceCallback iGeofenceCallback) throws RemoteException;

    void unregisterPendingIntent(PendingIntent pendingIntent) throws RemoteException;

    void updateGeofence(int i, int i2, int i3) throws RemoteException;

    void updateGeofenceData(int i, double d, double d2, double d3, int i2, int i3, int i4, int i5, int i6, int i7) throws RemoteException;

    public static abstract class Stub extends Binder implements IGeofenceService {
        private static final String DESCRIPTOR = "com.qti.geofence.IGeofenceService";
        static final int TRANSACTION_addGeofence = 3;
        static final int TRANSACTION_addGeofenceObj = 8;
        static final int TRANSACTION_pauseGeofence = 6;
        static final int TRANSACTION_recoverGeofences = 11;
        static final int TRANSACTION_registerCallback = 1;
        static final int TRANSACTION_registerPendingIntent = 9;
        static final int TRANSACTION_removeGeofence = 4;
        static final int TRANSACTION_resumeGeofence = 7;
        static final int TRANSACTION_unregisterCallback = 2;
        static final int TRANSACTION_unregisterPendingIntent = 10;
        static final int TRANSACTION_updateGeofence = 5;
        static final int TRANSACTION_updateGeofenceData = 12;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IGeofenceService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IGeofenceService)) {
                return new Proxy(iBinder);
            }
            return (IGeofenceService) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: com.qti.geofence.GeofenceData} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: android.app.PendingIntent} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: android.app.PendingIntent} */
        /* JADX WARNING: type inference failed for: r3v1 */
        /* JADX WARNING: type inference failed for: r3v13 */
        /* JADX WARNING: type inference failed for: r3v14 */
        /* JADX WARNING: type inference failed for: r3v15 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r19, android.os.Parcel r20, android.os.Parcel r21, int r22) throws android.os.RemoteException {
            /*
                r18 = this;
                r14 = r18
                r0 = r19
                r1 = r20
                r15 = r21
                java.lang.String r2 = "com.qti.geofence.IGeofenceService"
                r3 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r16 = 1
                if (r0 == r3) goto L_0x0157
                r3 = 0
                switch(r0) {
                    case 1: goto L_0x0145;
                    case 2: goto L_0x0133;
                    case 3: goto L_0x00fb;
                    case 4: goto L_0x00ed;
                    case 5: goto L_0x00d7;
                    case 6: goto L_0x00c9;
                    case 7: goto L_0x00bb;
                    case 8: goto L_0x009e;
                    case 9: goto L_0x0085;
                    case 10: goto L_0x006c;
                    case 11: goto L_0x0059;
                    case 12: goto L_0x001a;
                    default: goto L_0x0015;
                }
            L_0x0015:
                boolean r0 = super.onTransact(r19, r20, r21, r22)
                return r0
            L_0x001a:
                r1.enforceInterface(r2)
                int r2 = r20.readInt()
                double r3 = r20.readDouble()
                double r5 = r20.readDouble()
                double r7 = r20.readDouble()
                int r9 = r20.readInt()
                int r10 = r20.readInt()
                int r11 = r20.readInt()
                int r12 = r20.readInt()
                int r13 = r20.readInt()
                int r17 = r20.readInt()
                r0 = r18
                r1 = r2
                r2 = r3
                r4 = r5
                r6 = r7
                r8 = r9
                r9 = r10
                r10 = r11
                r11 = r12
                r12 = r13
                r13 = r17
                r0.updateGeofenceData(r1, r2, r4, r6, r8, r9, r10, r11, r12, r13)
                r21.writeNoException()
                return r16
            L_0x0059:
                r1.enforceInterface(r2)
                android.os.Parcelable$Creator<com.qti.geofence.GeofenceData> r0 = com.qti.geofence.GeofenceData.CREATOR
                java.util.ArrayList r0 = r1.createTypedArrayList(r0)
                r14.recoverGeofences(r0)
                r21.writeNoException()
                r15.writeTypedList(r0)
                return r16
            L_0x006c:
                r1.enforceInterface(r2)
                int r0 = r20.readInt()
                if (r0 == 0) goto L_0x007e
                android.os.Parcelable$Creator r0 = android.app.PendingIntent.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r1)
                r3 = r0
                android.app.PendingIntent r3 = (android.app.PendingIntent) r3
            L_0x007e:
                r14.unregisterPendingIntent(r3)
                r21.writeNoException()
                return r16
            L_0x0085:
                r1.enforceInterface(r2)
                int r0 = r20.readInt()
                if (r0 == 0) goto L_0x0097
                android.os.Parcelable$Creator r0 = android.app.PendingIntent.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r1)
                r3 = r0
                android.app.PendingIntent r3 = (android.app.PendingIntent) r3
            L_0x0097:
                r14.registerPendingIntent(r3)
                r21.writeNoException()
                return r16
            L_0x009e:
                r1.enforceInterface(r2)
                int r0 = r20.readInt()
                if (r0 == 0) goto L_0x00b0
                android.os.Parcelable$Creator<com.qti.geofence.GeofenceData> r0 = com.qti.geofence.GeofenceData.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r1)
                r3 = r0
                com.qti.geofence.GeofenceData r3 = (com.qti.geofence.GeofenceData) r3
            L_0x00b0:
                int r0 = r14.addGeofenceObj(r3)
                r21.writeNoException()
                r15.writeInt(r0)
                return r16
            L_0x00bb:
                r1.enforceInterface(r2)
                int r0 = r20.readInt()
                r14.resumeGeofence(r0)
                r21.writeNoException()
                return r16
            L_0x00c9:
                r1.enforceInterface(r2)
                int r0 = r20.readInt()
                r14.pauseGeofence(r0)
                r21.writeNoException()
                return r16
            L_0x00d7:
                r1.enforceInterface(r2)
                int r0 = r20.readInt()
                int r2 = r20.readInt()
                int r1 = r20.readInt()
                r14.updateGeofence(r0, r2, r1)
                r21.writeNoException()
                return r16
            L_0x00ed:
                r1.enforceInterface(r2)
                int r0 = r20.readInt()
                r14.removeGeofence(r0)
                r21.writeNoException()
                return r16
            L_0x00fb:
                r1.enforceInterface(r2)
                double r2 = r20.readDouble()
                double r4 = r20.readDouble()
                double r6 = r20.readDouble()
                int r8 = r20.readInt()
                int r9 = r20.readInt()
                int r10 = r20.readInt()
                int r11 = r20.readInt()
                int r12 = r20.readInt()
                r0 = r18
                r1 = r2
                r3 = r4
                r5 = r6
                r7 = r8
                r8 = r9
                r9 = r10
                r10 = r11
                r11 = r12
                int r0 = r0.addGeofence(r1, r3, r5, r7, r8, r9, r10, r11)
                r21.writeNoException()
                r15.writeInt(r0)
                return r16
            L_0x0133:
                r1.enforceInterface(r2)
                android.os.IBinder r0 = r20.readStrongBinder()
                com.qti.geofence.IGeofenceCallback r0 = com.qti.geofence.IGeofenceCallback.Stub.asInterface(r0)
                r14.unregisterCallback(r0)
                r21.writeNoException()
                return r16
            L_0x0145:
                r1.enforceInterface(r2)
                android.os.IBinder r0 = r20.readStrongBinder()
                com.qti.geofence.IGeofenceCallback r0 = com.qti.geofence.IGeofenceCallback.Stub.asInterface(r0)
                r14.registerCallback(r0)
                r21.writeNoException()
                return r16
            L_0x0157:
                r15.writeString(r2)
                return r16
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qti.geofence.IGeofenceService.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IGeofenceService {
            public static IGeofenceService sDefaultImpl;
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

            public void registerCallback(IGeofenceCallback iGeofenceCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iGeofenceCallback != null ? iGeofenceCallback.asBinder() : null);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().registerCallback(iGeofenceCallback);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void unregisterCallback(IGeofenceCallback iGeofenceCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iGeofenceCallback != null ? iGeofenceCallback.asBinder() : null);
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().unregisterCallback(iGeofenceCallback);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int addGeofence(double d, double d2, double d3, int i, int i2, int i3, int i4, int i5) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeDouble(d);
                    obtain.writeDouble(d2);
                    obtain.writeDouble(d3);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeInt(i4);
                    obtain.writeInt(i5);
                    try {
                        if (this.mRemote.transact(3, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                            obtain2.readException();
                            int readInt = obtain2.readInt();
                            obtain2.recycle();
                            obtain.recycle();
                            return readInt;
                        }
                        int addGeofence = Stub.getDefaultImpl().addGeofence(d, d2, d3, i, i2, i3, i4, i5);
                        obtain2.recycle();
                        obtain.recycle();
                        return addGeofence;
                    } catch (Throwable th) {
                        th = th;
                        obtain2.recycle();
                        obtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            public void removeGeofence(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(4, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().removeGeofence(i);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void updateGeofence(int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    if (this.mRemote.transact(5, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().updateGeofence(i, i2, i3);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void pauseGeofence(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(6, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().pauseGeofence(i);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void resumeGeofence(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(7, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().resumeGeofence(i);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int addGeofenceObj(GeofenceData geofenceData) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (geofenceData != null) {
                        obtain.writeInt(1);
                        geofenceData.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addGeofenceObj(geofenceData);
                    }
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void registerPendingIntent(PendingIntent pendingIntent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(9, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().registerPendingIntent(pendingIntent);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void unregisterPendingIntent(PendingIntent pendingIntent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(10, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().unregisterPendingIntent(pendingIntent);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void recoverGeofences(List<GeofenceData> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    if (this.mRemote.transact(11, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.readTypedList(list, GeofenceData.CREATOR);
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().recoverGeofences(list);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void updateGeofenceData(int i, double d, double d2, double d3, int i2, int i3, int i4, int i5, int i6, int i7) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeDouble(d);
                    obtain.writeDouble(d2);
                    obtain.writeDouble(d3);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeInt(i4);
                    obtain.writeInt(i5);
                    obtain.writeInt(i6);
                    obtain.writeInt(i7);
                    if (this.mRemote.transact(12, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().updateGeofenceData(i, d, d2, d3, i2, i3, i4, i5, i6, i7);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IGeofenceService iGeofenceService) {
            if (Proxy.sDefaultImpl != null || iGeofenceService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iGeofenceService;
            return true;
        }

        public static IGeofenceService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
