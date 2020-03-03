package com.huawei.hms.core.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.huawei.hms.core.aidl.d;

public interface e extends IInterface {
    void a(b bVar) throws RemoteException;

    void a(b bVar, d dVar) throws RemoteException;

    public static abstract class a extends Binder implements e {
        public static e a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.hms.core.aidl.IAIDLInvoke");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof e)) {
                return new C0054a(iBinder);
            }
            return (e) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                b bVar = null;
                switch (i) {
                    case 1:
                        parcel.enforceInterface("com.huawei.hms.core.aidl.IAIDLInvoke");
                        if (parcel.readInt() != 0) {
                            bVar = b.CREATOR.createFromParcel(parcel);
                        }
                        a(bVar);
                        parcel2.writeNoException();
                        return true;
                    case 2:
                        parcel.enforceInterface("com.huawei.hms.core.aidl.IAIDLInvoke");
                        if (parcel.readInt() != 0) {
                            bVar = b.CREATOR.createFromParcel(parcel);
                        }
                        a(bVar, d.a.a(parcel.readStrongBinder()));
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString("com.huawei.hms.core.aidl.IAIDLInvoke");
                return true;
            }
        }

        /* renamed from: com.huawei.hms.core.aidl.e$a$a  reason: collision with other inner class name */
        private static class C0054a implements e {

            /* renamed from: a  reason: collision with root package name */
            private IBinder f5872a;

            C0054a(IBinder iBinder) {
                this.f5872a = iBinder;
            }

            public IBinder asBinder() {
                return this.f5872a;
            }

            public void a(b bVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.core.aidl.IAIDLInvoke");
                    if (bVar != null) {
                        obtain.writeInt(1);
                        bVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f5872a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void a(b bVar, d dVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.hms.core.aidl.IAIDLInvoke");
                    if (bVar != null) {
                        obtain.writeInt(1);
                        bVar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    this.f5872a.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }
    }
}
