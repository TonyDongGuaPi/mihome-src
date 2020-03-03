package com.qti.flp;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.qti.flp.IMaxPowerAllocatedCallback;

public interface ITestService extends IInterface {

    public static class Default implements ITestService {
        public IBinder asBinder() {
            return null;
        }

        public void deleteAidingData(long j) throws RemoteException {
        }

        public void registerMaxPowerChangeCallback(IMaxPowerAllocatedCallback iMaxPowerAllocatedCallback) throws RemoteException {
        }

        public void unregisterMaxPowerChangeCallback(IMaxPowerAllocatedCallback iMaxPowerAllocatedCallback) throws RemoteException {
        }

        public void updateXtraThrottle(boolean z) throws RemoteException {
        }
    }

    void deleteAidingData(long j) throws RemoteException;

    void registerMaxPowerChangeCallback(IMaxPowerAllocatedCallback iMaxPowerAllocatedCallback) throws RemoteException;

    void unregisterMaxPowerChangeCallback(IMaxPowerAllocatedCallback iMaxPowerAllocatedCallback) throws RemoteException;

    void updateXtraThrottle(boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements ITestService {
        private static final String DESCRIPTOR = "com.qti.flp.ITestService";
        static final int TRANSACTION_deleteAidingData = 1;
        static final int TRANSACTION_registerMaxPowerChangeCallback = 3;
        static final int TRANSACTION_unregisterMaxPowerChangeCallback = 4;
        static final int TRANSACTION_updateXtraThrottle = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITestService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ITestService)) {
                return new Proxy(iBinder);
            }
            return (ITestService) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        deleteAidingData(parcel.readLong());
                        parcel2.writeNoException();
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        updateXtraThrottle(parcel.readInt() != 0);
                        parcel2.writeNoException();
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        registerMaxPowerChangeCallback(IMaxPowerAllocatedCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        unregisterMaxPowerChangeCallback(IMaxPowerAllocatedCallback.Stub.asInterface(parcel.readStrongBinder()));
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

        private static class Proxy implements ITestService {
            public static ITestService sDefaultImpl;
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

            public void deleteAidingData(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().deleteAidingData(j);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void updateXtraThrottle(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().updateXtraThrottle(z);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void registerMaxPowerChangeCallback(IMaxPowerAllocatedCallback iMaxPowerAllocatedCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMaxPowerAllocatedCallback != null ? iMaxPowerAllocatedCallback.asBinder() : null);
                    if (this.mRemote.transact(3, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().registerMaxPowerChangeCallback(iMaxPowerAllocatedCallback);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void unregisterMaxPowerChangeCallback(IMaxPowerAllocatedCallback iMaxPowerAllocatedCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iMaxPowerAllocatedCallback != null ? iMaxPowerAllocatedCallback.asBinder() : null);
                    if (this.mRemote.transact(4, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().unregisterMaxPowerChangeCallback(iMaxPowerAllocatedCallback);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITestService iTestService) {
            if (Proxy.sDefaultImpl != null || iTestService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iTestService;
            return true;
        }

        public static ITestService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
