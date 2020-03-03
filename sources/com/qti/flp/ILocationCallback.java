package com.qti.flp;

import android.location.Location;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ILocationCallback extends IInterface {

    public static class Default implements ILocationCallback {
        public IBinder asBinder() {
            return null;
        }

        public void onLocationAvailable(Location[] locationArr) throws RemoteException {
        }
    }

    void onLocationAvailable(Location[] locationArr) throws RemoteException;

    public static abstract class Stub extends Binder implements ILocationCallback {
        private static final String DESCRIPTOR = "com.qti.flp.ILocationCallback";
        static final int TRANSACTION_onLocationAvailable = 1;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILocationCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ILocationCallback)) {
                return new Proxy(iBinder);
            }
            return (ILocationCallback) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onLocationAvailable((Location[]) parcel.createTypedArray(Location.CREATOR));
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        private static class Proxy implements ILocationCallback {
            public static ILocationCallback sDefaultImpl;
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

            public void onLocationAvailable(Location[] locationArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedArray(locationArr, 0);
                    if (this.mRemote.transact(1, obtain, (Parcel) null, 1) || Stub.getDefaultImpl() == null) {
                        obtain.recycle();
                    } else {
                        Stub.getDefaultImpl().onLocationAvailable(locationArr);
                    }
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILocationCallback iLocationCallback) {
            if (Proxy.sDefaultImpl != null || iLocationCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iLocationCallback;
            return true;
        }

        public static ILocationCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
