package com.xiaomi.a.a.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Map;

public interface a extends IInterface {
    String a(String str, Map map) throws RemoteException;

    String b(String str, Map map) throws RemoteException;

    /* renamed from: com.xiaomi.a.a.a.a$a  reason: collision with other inner class name */
    public static abstract class C0080a extends Binder implements a {

        /* renamed from: a  reason: collision with root package name */
        static final int f9892a = 1;
        static final int b = 2;
        private static final String c = "com.xiaomi.xmsf.push.service.IHttpService";

        public IBinder asBinder() {
            return this;
        }

        public C0080a() {
            attachInterface(this, c);
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(c);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof a)) {
                return new C0081a(iBinder);
            }
            return (a) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface(c);
                        String a2 = a(parcel.readString(), parcel.readHashMap(getClass().getClassLoader()));
                        parcel2.writeNoException();
                        parcel2.writeString(a2);
                        return true;
                    case 2:
                        parcel.enforceInterface(c);
                        String b2 = b(parcel.readString(), parcel.readHashMap(getClass().getClassLoader()));
                        parcel2.writeNoException();
                        parcel2.writeString(b2);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString(c);
                return true;
            }
        }

        /* renamed from: com.xiaomi.a.a.a.a$a$a  reason: collision with other inner class name */
        private static class C0081a implements a {

            /* renamed from: a  reason: collision with root package name */
            private IBinder f9893a;

            public String a() {
                return C0080a.c;
            }

            C0081a(IBinder iBinder) {
                this.f9893a = iBinder;
            }

            public IBinder asBinder() {
                return this.f9893a;
            }

            public String a(String str, Map map) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(C0080a.c);
                    obtain.writeString(str);
                    obtain.writeMap(map);
                    this.f9893a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String b(String str, Map map) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(C0080a.c);
                    obtain.writeString(str);
                    obtain.writeMap(map);
                    this.f9893a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
