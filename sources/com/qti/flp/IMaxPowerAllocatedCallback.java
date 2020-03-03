package com.qti.flp;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IMaxPowerAllocatedCallback extends IInterface {

    public static class Default implements IMaxPowerAllocatedCallback {
        public IBinder asBinder() {
            return null;
        }

        public void onMaxPowerAllocatedChanged(double d) throws RemoteException {
        }
    }

    void onMaxPowerAllocatedChanged(double d) throws RemoteException;

    public static abstract class Stub extends Binder implements IMaxPowerAllocatedCallback {
        private static final String DESCRIPTOR = "com.qti.flp.IMaxPowerAllocatedCallback";
        static final int TRANSACTION_onMaxPowerAllocatedChanged = 1;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMaxPowerAllocatedCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMaxPowerAllocatedCallback)) {
                return new Proxy(iBinder);
            }
            return (IMaxPowerAllocatedCallback) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onMaxPowerAllocatedChanged(parcel.readDouble());
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        private static class Proxy implements IMaxPowerAllocatedCallback {
            public static IMaxPowerAllocatedCallback sDefaultImpl;
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

            public void onMaxPowerAllocatedChanged(double d) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeDouble(d);
                    if (this.mRemote.transact(1, obtain, (Parcel) null, 1) || Stub.getDefaultImpl() == null) {
                        obtain.recycle();
                    } else {
                        Stub.getDefaultImpl().onMaxPowerAllocatedChanged(d);
                    }
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMaxPowerAllocatedCallback iMaxPowerAllocatedCallback) {
            if (Proxy.sDefaultImpl != null || iMaxPowerAllocatedCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iMaxPowerAllocatedCallback;
            return true;
        }

        public static IMaxPowerAllocatedCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
