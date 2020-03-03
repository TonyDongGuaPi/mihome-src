package com.xiaomi.mistatistic.sdk;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xiaomi.mistatistic.sdk.data.StatEventPojo;
import java.util.List;

public interface a extends IInterface {
    int a() throws RemoteException;

    StatEventPojo a(String str, String str2) throws RemoteException;

    List<StatEventPojo> a(long j) throws RemoteException;

    /* renamed from: com.xiaomi.mistatistic.sdk.a$a  reason: collision with other inner class name */
    public static abstract class C0085a extends Binder implements a {
        public IBinder asBinder() {
            return this;
        }

        public C0085a() {
            attachInterface(this, "com.xiaomi.mistatistic.sdk.IBaseService");
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.xiaomi.mistatistic.sdk.IBaseService");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof a)) {
                return new C0086a(iBinder);
            }
            return (a) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface("com.xiaomi.mistatistic.sdk.IBaseService");
                        StatEventPojo a2 = a(parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        if (a2 != null) {
                            parcel2.writeInt(1);
                            a2.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 2:
                        parcel.enforceInterface("com.xiaomi.mistatistic.sdk.IBaseService");
                        List<StatEventPojo> a3 = a(parcel.readLong());
                        parcel2.writeNoException();
                        parcel2.writeTypedList(a3);
                        return true;
                    case 3:
                        parcel.enforceInterface("com.xiaomi.mistatistic.sdk.IBaseService");
                        int a4 = a();
                        parcel2.writeNoException();
                        parcel2.writeInt(a4);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString("com.xiaomi.mistatistic.sdk.IBaseService");
                return true;
            }
        }

        /* renamed from: com.xiaomi.mistatistic.sdk.a$a$a  reason: collision with other inner class name */
        private static class C0086a implements a {

            /* renamed from: a  reason: collision with root package name */
            private IBinder f11998a;

            C0086a(IBinder iBinder) {
                this.f11998a = iBinder;
            }

            public IBinder asBinder() {
                return this.f11998a;
            }

            public StatEventPojo a(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaomi.mistatistic.sdk.IBaseService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.f11998a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? StatEventPojo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<StatEventPojo> a(long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaomi.mistatistic.sdk.IBaseService");
                    obtain.writeLong(j);
                    this.f11998a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(StatEventPojo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int a() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaomi.mistatistic.sdk.IBaseService");
                    this.f11998a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
