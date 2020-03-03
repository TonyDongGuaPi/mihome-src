package com.qti.gnssconfig;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.qti.gnssconfig.IGnssConfigCallback;

public interface IGnssConfigService extends IInterface {

    public static class Default implements IGnssConfigService {
        public IBinder asBinder() {
            return null;
        }

        public void getGnssSvTypeConfig() throws RemoteException {
        }

        public void registerCallback(IGnssConfigCallback iGnssConfigCallback) throws RemoteException {
        }

        public void resetGnssSvTypeConfig() throws RemoteException {
        }

        public void setGnssSvTypeConfig(int[] iArr) throws RemoteException {
        }
    }

    void getGnssSvTypeConfig() throws RemoteException;

    void registerCallback(IGnssConfigCallback iGnssConfigCallback) throws RemoteException;

    void resetGnssSvTypeConfig() throws RemoteException;

    void setGnssSvTypeConfig(int[] iArr) throws RemoteException;

    public static abstract class Stub extends Binder implements IGnssConfigService {
        private static final String DESCRIPTOR = "com.qti.gnssconfig.IGnssConfigService";
        static final int TRANSACTION_getGnssSvTypeConfig = 2;
        static final int TRANSACTION_registerCallback = 1;
        static final int TRANSACTION_resetGnssSvTypeConfig = 4;
        static final int TRANSACTION_setGnssSvTypeConfig = 3;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IGnssConfigService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IGnssConfigService)) {
                return new Proxy(iBinder);
            }
            return (IGnssConfigService) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        registerCallback(IGnssConfigCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        getGnssSvTypeConfig();
                        parcel2.writeNoException();
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        setGnssSvTypeConfig(parcel.createIntArray());
                        parcel2.writeNoException();
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        resetGnssSvTypeConfig();
                        parcel2.writeNoException();
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        private static class Proxy implements IGnssConfigService {
            public static IGnssConfigService sDefaultImpl;
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

            public void registerCallback(IGnssConfigCallback iGnssConfigCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iGnssConfigCallback != null ? iGnssConfigCallback.asBinder() : null);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().registerCallback(iGnssConfigCallback);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getGnssSvTypeConfig() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().getGnssSvTypeConfig();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setGnssSvTypeConfig(int[] iArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeIntArray(iArr);
                    if (this.mRemote.transact(3, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().setGnssSvTypeConfig(iArr);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void resetGnssSvTypeConfig() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(4, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().resetGnssSvTypeConfig();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IGnssConfigService iGnssConfigService) {
            if (Proxy.sDefaultImpl != null || iGnssConfigService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iGnssConfigService;
            return true;
        }

        public static IGnssConfigService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
