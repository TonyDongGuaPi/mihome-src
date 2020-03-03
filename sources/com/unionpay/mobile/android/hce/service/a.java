package com.unionpay.mobile.android.hce.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.unionpay.mobile.android.hce.service.b;

public interface a extends IInterface {

    /* renamed from: com.unionpay.mobile.android.hce.service.a$a  reason: collision with other inner class name */
    public static abstract class C0072a extends Binder implements a {

        /* renamed from: com.unionpay.mobile.android.hce.service.a$a$a  reason: collision with other inner class name */
        private static class C0073a implements a {

            /* renamed from: a  reason: collision with root package name */
            private IBinder f9572a;

            C0073a(IBinder iBinder) {
                this.f9572a = iBinder;
            }

            public final String a(String str, String str2, b bVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.mobile.android.hce.service.IHCEBankService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeStrongBinder(bVar != null ? bVar.asBinder() : null);
                    this.f9572a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void a(String str, String str2, String str3, b bVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.mobile.android.hce.service.IHCEBankService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeStrongBinder(bVar != null ? bVar.asBinder() : null);
                    this.f9572a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IBinder asBinder() {
                return this.f9572a;
            }
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.unionpay.mobile.android.hce.service.IHCEBankService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof a)) ? new C0073a(iBinder) : (a) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface("com.unionpay.mobile.android.hce.service.IHCEBankService");
                        String a2 = a(parcel.readString(), parcel.readString(), b.a.a(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        parcel2.writeString(a2);
                        return true;
                    case 2:
                        parcel.enforceInterface("com.unionpay.mobile.android.hce.service.IHCEBankService");
                        a(parcel.readString(), parcel.readString(), parcel.readString(), b.a.a(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString("com.unionpay.mobile.android.hce.service.IHCEBankService");
                return true;
            }
        }
    }

    String a(String str, String str2, b bVar) throws RemoteException;

    void a(String str, String str2, String str3, b bVar) throws RemoteException;
}
