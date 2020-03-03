package com.qti.wifidbreceiver;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.qti.wifidbreceiver.IWiFiDBReceiverResponseListener;
import java.util.List;

public interface IWiFiDBReceiver extends IInterface {

    public static class Default implements IWiFiDBReceiver {
        public IBinder asBinder() {
            return null;
        }

        public int pushLookupResult(List<APLocationData> list, List<APSpecialInfo> list2) throws RemoteException {
            return 0;
        }

        public int pushWiFiDB(List<APLocationData> list, List<APSpecialInfo> list2, int i) throws RemoteException {
            return 0;
        }

        public boolean registerResponseListener(IWiFiDBReceiverResponseListener iWiFiDBReceiverResponseListener) throws RemoteException {
            return false;
        }

        public boolean registerResponseListenerExt(IWiFiDBReceiverResponseListener iWiFiDBReceiverResponseListener, PendingIntent pendingIntent) throws RemoteException {
            return false;
        }

        public void removeResponseListener(IWiFiDBReceiverResponseListener iWiFiDBReceiverResponseListener) throws RemoteException {
        }

        public int requestAPList(int i) throws RemoteException {
            return 0;
        }

        public int requestScanList() throws RemoteException {
            return 0;
        }
    }

    int pushLookupResult(List<APLocationData> list, List<APSpecialInfo> list2) throws RemoteException;

    int pushWiFiDB(List<APLocationData> list, List<APSpecialInfo> list2, int i) throws RemoteException;

    boolean registerResponseListener(IWiFiDBReceiverResponseListener iWiFiDBReceiverResponseListener) throws RemoteException;

    boolean registerResponseListenerExt(IWiFiDBReceiverResponseListener iWiFiDBReceiverResponseListener, PendingIntent pendingIntent) throws RemoteException;

    void removeResponseListener(IWiFiDBReceiverResponseListener iWiFiDBReceiverResponseListener) throws RemoteException;

    int requestAPList(int i) throws RemoteException;

    int requestScanList() throws RemoteException;

    public static abstract class Stub extends Binder implements IWiFiDBReceiver {
        private static final String DESCRIPTOR = "com.qti.wifidbreceiver.IWiFiDBReceiver";
        static final int TRANSACTION_pushLookupResult = 7;
        static final int TRANSACTION_pushWiFiDB = 3;
        static final int TRANSACTION_registerResponseListener = 1;
        static final int TRANSACTION_registerResponseListenerExt = 6;
        static final int TRANSACTION_removeResponseListener = 4;
        static final int TRANSACTION_requestAPList = 2;
        static final int TRANSACTION_requestScanList = 5;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWiFiDBReceiver asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IWiFiDBReceiver)) {
                return new Proxy(iBinder);
            }
            return (IWiFiDBReceiver) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean registerResponseListener = registerResponseListener(IWiFiDBReceiverResponseListener.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        parcel2.writeInt(registerResponseListener ? 1 : 0);
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        int requestAPList = requestAPList(parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(requestAPList);
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        int pushWiFiDB = pushWiFiDB(parcel.createTypedArrayList(APLocationData.CREATOR), parcel.createTypedArrayList(APSpecialInfo.CREATOR), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(pushWiFiDB);
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        removeResponseListener(IWiFiDBReceiverResponseListener.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        int requestScanList = requestScanList();
                        parcel2.writeNoException();
                        parcel2.writeInt(requestScanList);
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean registerResponseListenerExt = registerResponseListenerExt(IWiFiDBReceiverResponseListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? (PendingIntent) PendingIntent.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        parcel2.writeInt(registerResponseListenerExt ? 1 : 0);
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        int pushLookupResult = pushLookupResult(parcel.createTypedArrayList(APLocationData.CREATOR), parcel.createTypedArrayList(APSpecialInfo.CREATOR));
                        parcel2.writeNoException();
                        parcel2.writeInt(pushLookupResult);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        private static class Proxy implements IWiFiDBReceiver {
            public static IWiFiDBReceiver sDefaultImpl;
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

            public boolean registerResponseListener(IWiFiDBReceiverResponseListener iWiFiDBReceiverResponseListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iWiFiDBReceiverResponseListener != null ? iWiFiDBReceiverResponseListener.asBinder() : null);
                    boolean z = false;
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerResponseListener(iWiFiDBReceiverResponseListener);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int requestAPList(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestAPList(i);
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

            public int pushWiFiDB(List<APLocationData> list, List<APSpecialInfo> list2, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    obtain.writeTypedList(list2);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().pushWiFiDB(list, list2, i);
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

            public void removeResponseListener(IWiFiDBReceiverResponseListener iWiFiDBReceiverResponseListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iWiFiDBReceiverResponseListener != null ? iWiFiDBReceiverResponseListener.asBinder() : null);
                    if (this.mRemote.transact(4, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().removeResponseListener(iWiFiDBReceiverResponseListener);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int requestScanList() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestScanList();
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

            public boolean registerResponseListenerExt(IWiFiDBReceiverResponseListener iWiFiDBReceiverResponseListener, PendingIntent pendingIntent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iWiFiDBReceiverResponseListener != null ? iWiFiDBReceiverResponseListener.asBinder() : null);
                    boolean z = true;
                    if (pendingIntent != null) {
                        obtain.writeInt(1);
                        pendingIntent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerResponseListenerExt(iWiFiDBReceiverResponseListener, pendingIntent);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int pushLookupResult(List<APLocationData> list, List<APSpecialInfo> list2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedList(list);
                    obtain.writeTypedList(list2);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().pushLookupResult(list, list2);
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
        }

        public static boolean setDefaultImpl(IWiFiDBReceiver iWiFiDBReceiver) {
            if (Proxy.sDefaultImpl != null || iWiFiDBReceiver == null) {
                return false;
            }
            Proxy.sDefaultImpl = iWiFiDBReceiver;
            return true;
        }

        public static IWiFiDBReceiver getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
