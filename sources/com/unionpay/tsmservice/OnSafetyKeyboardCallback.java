package com.unionpay.tsmservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface OnSafetyKeyboardCallback extends IInterface {

    public static abstract class Stub extends Binder implements OnSafetyKeyboardCallback {

        private static class a implements OnSafetyKeyboardCallback {

            /* renamed from: a  reason: collision with root package name */
            private IBinder f9821a;

            a(IBinder iBinder) {
                this.f9821a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f9821a;
            }

            public final void onEditorChanged(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.OnSafetyKeyboardCallback");
                    obtain.writeInt(i);
                    this.f9821a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void onHide() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.OnSafetyKeyboardCallback");
                    this.f9821a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void onShow() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.OnSafetyKeyboardCallback");
                    this.f9821a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.unionpay.tsmservice.OnSafetyKeyboardCallback");
        }

        public static OnSafetyKeyboardCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.unionpay.tsmservice.OnSafetyKeyboardCallback");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof OnSafetyKeyboardCallback)) ? new a(iBinder) : (OnSafetyKeyboardCallback) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface("com.unionpay.tsmservice.OnSafetyKeyboardCallback");
                        onShow();
                        break;
                    case 2:
                        parcel.enforceInterface("com.unionpay.tsmservice.OnSafetyKeyboardCallback");
                        onHide();
                        break;
                    case 3:
                        parcel.enforceInterface("com.unionpay.tsmservice.OnSafetyKeyboardCallback");
                        onEditorChanged(parcel.readInt());
                        break;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeNoException();
                return true;
            }
            parcel2.writeString("com.unionpay.tsmservice.OnSafetyKeyboardCallback");
            return true;
        }
    }

    void onEditorChanged(int i) throws RemoteException;

    void onHide() throws RemoteException;

    void onShow() throws RemoteException;
}
