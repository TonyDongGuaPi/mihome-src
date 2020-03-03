package com.qti.wifidbreceiver;

import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IWiFiDBReceiverResponseListener extends IInterface {

    public static class Default implements IWiFiDBReceiverResponseListener {
        public IBinder asBinder() {
            return null;
        }

        public void onAPListAvailable(List<APInfo> list) throws RemoteException {
        }

        public void onAPListAvailableExt(List<APInfoExt> list, int i, Location location) throws RemoteException {
        }

        public void onLookupRequest(List<APInfoExt> list, Location location) throws RemoteException {
        }

        public void onServiceRequest() throws RemoteException {
        }

        public void onStatusUpdate(boolean z, String str) throws RemoteException {
        }
    }

    void onAPListAvailable(List<APInfo> list) throws RemoteException;

    void onAPListAvailableExt(List<APInfoExt> list, int i, Location location) throws RemoteException;

    void onLookupRequest(List<APInfoExt> list, Location location) throws RemoteException;

    void onServiceRequest() throws RemoteException;

    void onStatusUpdate(boolean z, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IWiFiDBReceiverResponseListener {
        private static final String DESCRIPTOR = "com.qti.wifidbreceiver.IWiFiDBReceiverResponseListener";
        static final int TRANSACTION_onAPListAvailable = 1;
        static final int TRANSACTION_onAPListAvailableExt = 4;
        static final int TRANSACTION_onLookupRequest = 5;
        static final int TRANSACTION_onServiceRequest = 3;
        static final int TRANSACTION_onStatusUpdate = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWiFiDBReceiverResponseListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IWiFiDBReceiverResponseListener)) {
                return new Proxy(iBinder);
            }
            return (IWiFiDBReceiverResponseListener) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: android.location.Location} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: android.location.Location} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r4, android.os.Parcel r5, android.os.Parcel r6, int r7) throws android.os.RemoteException {
            /*
                r3 = this;
                java.lang.String r0 = "com.qti.wifidbreceiver.IWiFiDBReceiverResponseListener"
                r1 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r2 = 1
                if (r4 == r1) goto L_0x0075
                r1 = 0
                switch(r4) {
                    case 1: goto L_0x0068;
                    case 2: goto L_0x0054;
                    case 3: goto L_0x004d;
                    case 4: goto L_0x002d;
                    case 5: goto L_0x0011;
                    default: goto L_0x000c;
                }
            L_0x000c:
                boolean r4 = super.onTransact(r4, r5, r6, r7)
                return r4
            L_0x0011:
                r5.enforceInterface(r0)
                android.os.Parcelable$Creator<com.qti.wifidbreceiver.APInfoExt> r4 = com.qti.wifidbreceiver.APInfoExt.CREATOR
                java.util.ArrayList r4 = r5.createTypedArrayList(r4)
                int r6 = r5.readInt()
                if (r6 == 0) goto L_0x0029
                android.os.Parcelable$Creator r6 = android.location.Location.CREATOR
                java.lang.Object r5 = r6.createFromParcel(r5)
                r1 = r5
                android.location.Location r1 = (android.location.Location) r1
            L_0x0029:
                r3.onLookupRequest(r4, r1)
                return r2
            L_0x002d:
                r5.enforceInterface(r0)
                android.os.Parcelable$Creator<com.qti.wifidbreceiver.APInfoExt> r4 = com.qti.wifidbreceiver.APInfoExt.CREATOR
                java.util.ArrayList r4 = r5.createTypedArrayList(r4)
                int r6 = r5.readInt()
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x0049
                android.os.Parcelable$Creator r7 = android.location.Location.CREATOR
                java.lang.Object r5 = r7.createFromParcel(r5)
                r1 = r5
                android.location.Location r1 = (android.location.Location) r1
            L_0x0049:
                r3.onAPListAvailableExt(r4, r6, r1)
                return r2
            L_0x004d:
                r5.enforceInterface(r0)
                r3.onServiceRequest()
                return r2
            L_0x0054:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x005f
                r4 = 1
                goto L_0x0060
            L_0x005f:
                r4 = 0
            L_0x0060:
                java.lang.String r5 = r5.readString()
                r3.onStatusUpdate(r4, r5)
                return r2
            L_0x0068:
                r5.enforceInterface(r0)
                android.os.Parcelable$Creator<com.qti.wifidbreceiver.APInfo> r4 = com.qti.wifidbreceiver.APInfo.CREATOR
                java.util.ArrayList r4 = r5.createTypedArrayList(r4)
                r3.onAPListAvailable(r4)
                return r2
            L_0x0075:
                r6.writeString(r0)
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qti.wifidbreceiver.IWiFiDBReceiverResponseListener.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IWiFiDBReceiverResponseListener {
            public static IWiFiDBReceiverResponseListener sDefaultImpl;
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

            public void onAPListAvailable(List<APInfo> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    if (this.mRemote.transact(1, obtain, (Parcel) null, 1) || Stub.getDefaultImpl() == null) {
                        obtain.recycle();
                    } else {
                        Stub.getDefaultImpl().onAPListAvailable(list);
                    }
                } finally {
                    obtain.recycle();
                }
            }

            public void onStatusUpdate(boolean z, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeString(str);
                    if (this.mRemote.transact(2, obtain, (Parcel) null, 1) || Stub.getDefaultImpl() == null) {
                        obtain.recycle();
                    } else {
                        Stub.getDefaultImpl().onStatusUpdate(z, str);
                    }
                } finally {
                    obtain.recycle();
                }
            }

            public void onServiceRequest() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, obtain, (Parcel) null, 1) || Stub.getDefaultImpl() == null) {
                        obtain.recycle();
                    } else {
                        Stub.getDefaultImpl().onServiceRequest();
                    }
                } finally {
                    obtain.recycle();
                }
            }

            public void onAPListAvailableExt(List<APInfoExt> list, int i, Location location) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    obtain.writeInt(i);
                    if (location != null) {
                        obtain.writeInt(1);
                        location.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(4, obtain, (Parcel) null, 1) || Stub.getDefaultImpl() == null) {
                        obtain.recycle();
                    } else {
                        Stub.getDefaultImpl().onAPListAvailableExt(list, i, location);
                    }
                } finally {
                    obtain.recycle();
                }
            }

            public void onLookupRequest(List<APInfoExt> list, Location location) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    if (location != null) {
                        obtain.writeInt(1);
                        location.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(5, obtain, (Parcel) null, 1) || Stub.getDefaultImpl() == null) {
                        obtain.recycle();
                    } else {
                        Stub.getDefaultImpl().onLookupRequest(list, location);
                    }
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWiFiDBReceiverResponseListener iWiFiDBReceiverResponseListener) {
            if (Proxy.sDefaultImpl != null || iWiFiDBReceiverResponseListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iWiFiDBReceiverResponseListener;
            return true;
        }

        public static IWiFiDBReceiverResponseListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
