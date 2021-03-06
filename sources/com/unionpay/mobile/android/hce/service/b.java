package com.unionpay.mobile.android.hce.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface b extends IInterface {

    public static abstract class a extends Binder implements b {

        /* renamed from: com.unionpay.mobile.android.hce.service.b$a$a  reason: collision with other inner class name */
        private static class C0074a implements b {

            /* renamed from: a  reason: collision with root package name */
            private IBinder f9573a;

            C0074a(IBinder iBinder) {
                this.f9573a = iBinder;
            }

            public final void a(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.mobile.android.hce.service.IHCECallback");
                    obtain.writeString(str);
                    this.f9573a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void a(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.mobile.android.hce.service.IHCECallback");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.f9573a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IBinder asBinder() {
                return this.f9573a;
            }
        }

        public a() {
            attachInterface(this, "com.unionpay.mobile.android.hce.service.IHCECallback");
        }

        public static b a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.unionpay.mobile.android.hce.service.IHCECallback");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof b)) ? new C0074a(iBinder) : (b) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface("com.unionpay.mobile.android.hce.service.IHCECallback");
                        a(parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case 2:
                        parcel.enforceInterface("com.unionpay.mobile.android.hce.service.IHCECallback");
                        a(parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString("com.unionpay.mobile.android.hce.service.IHCECallback");
                return true;
            }
        }
    }

    void a(String str) throws RemoteException;

    void a(String str, String str2) throws RemoteException;
}
