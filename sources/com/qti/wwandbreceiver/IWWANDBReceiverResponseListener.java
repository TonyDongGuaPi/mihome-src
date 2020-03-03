package com.qti.wwandbreceiver;

import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IWWANDBReceiverResponseListener extends IInterface {

    public static class Default implements IWWANDBReceiverResponseListener {
        public IBinder asBinder() {
            return null;
        }

        public void onBSListAvailable(List<BSInfo> list) throws RemoteException {
        }

        public void onBSListAvailableExt(List<BSInfo> list, int i, Location location) throws RemoteException {
        }

        public void onServiceRequest() throws RemoteException {
        }

        public void onStatusUpdate(boolean z, String str) throws RemoteException {
        }
    }

    void onBSListAvailable(List<BSInfo> list) throws RemoteException;

    void onBSListAvailableExt(List<BSInfo> list, int i, Location location) throws RemoteException;

    void onServiceRequest() throws RemoteException;

    void onStatusUpdate(boolean z, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IWWANDBReceiverResponseListener {
        private static final String DESCRIPTOR = "com.qti.wwandbreceiver.IWWANDBReceiverResponseListener";
        static final int TRANSACTION_onBSListAvailable = 1;
        static final int TRANSACTION_onBSListAvailableExt = 4;
        static final int TRANSACTION_onServiceRequest = 3;
        static final int TRANSACTION_onStatusUpdate = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWWANDBReceiverResponseListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IWWANDBReceiverResponseListener)) {
                return new Proxy(iBinder);
            }
            return (IWWANDBReceiverResponseListener) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        onBSListAvailable(parcel.createTypedArrayList(BSInfo.CREATOR));
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        onStatusUpdate(parcel.readInt() != 0, parcel.readString());
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        onServiceRequest();
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        onBSListAvailableExt(parcel.createTypedArrayList(BSInfo.CREATOR), parcel.readInt(), parcel.readInt() != 0 ? (Location) Location.CREATOR.createFromParcel(parcel) : null);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        private static class Proxy implements IWWANDBReceiverResponseListener {
            public static IWWANDBReceiverResponseListener sDefaultImpl;
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

            public void onBSListAvailable(List<BSInfo> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    if (this.mRemote.transact(1, obtain, (Parcel) null, 1) || Stub.getDefaultImpl() == null) {
                        obtain.recycle();
                    } else {
                        Stub.getDefaultImpl().onBSListAvailable(list);
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

            public void onBSListAvailableExt(List<BSInfo> list, int i, Location location) throws RemoteException {
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
                        Stub.getDefaultImpl().onBSListAvailableExt(list, i, location);
                    }
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWWANDBReceiverResponseListener iWWANDBReceiverResponseListener) {
            if (Proxy.sDefaultImpl != null || iWWANDBReceiverResponseListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iWWANDBReceiverResponseListener;
            return true;
        }

        public static IWWANDBReceiverResponseListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
