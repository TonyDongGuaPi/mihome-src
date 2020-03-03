package com.qti.debugreport;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IDebugReportCallback extends IInterface {

    public static class Default implements IDebugReportCallback {
        public IBinder asBinder() {
            return null;
        }

        public void onDebugDataAvailable(Bundle bundle) throws RemoteException {
        }
    }

    void onDebugDataAvailable(Bundle bundle) throws RemoteException;

    public static abstract class Stub extends Binder implements IDebugReportCallback {
        private static final String DESCRIPTOR = "com.qti.debugreport.IDebugReportCallback";
        static final int TRANSACTION_onDebugDataAvailable = 1;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDebugReportCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IDebugReportCallback)) {
                return new Proxy(iBinder);
            }
            return (IDebugReportCallback) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onDebugDataAvailable(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        private static class Proxy implements IDebugReportCallback {
            public static IDebugReportCallback sDefaultImpl;
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

            public void onDebugDataAvailable(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, obtain, (Parcel) null, 1) || Stub.getDefaultImpl() == null) {
                        obtain.recycle();
                    } else {
                        Stub.getDefaultImpl().onDebugDataAvailable(bundle);
                    }
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDebugReportCallback iDebugReportCallback) {
            if (Proxy.sDefaultImpl != null || iDebugReportCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDebugReportCallback;
            return true;
        }

        public static IDebugReportCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
