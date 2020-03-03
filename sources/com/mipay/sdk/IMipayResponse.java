package com.mipay.sdk;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IMipayResponse extends IInterface {

    public static abstract class Stub extends Binder implements IMipayResponse {

        /* renamed from: a  reason: collision with root package name */
        static final int f8140a = 1;
        static final int b = 2;
        private static final String c = "com.mipay.sdk.IMipayResponse";

        private static class a implements IMipayResponse {

            /* renamed from: a  reason: collision with root package name */
            private IBinder f8141a;

            a(IBinder iBinder) {
                this.f8141a = iBinder;
            }

            public String a() {
                return Stub.c;
            }

            public IBinder asBinder() {
                return this.f8141a;
            }

            public void onError(int i, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.c);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f8141a.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void onResult(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.c);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f8141a.transact(1, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, c);
        }

        public static IMipayResponse asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(c);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IMipayResponse)) ? new a(iBinder) : (IMipayResponse) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: android.os.Bundle} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r3, android.os.Parcel r4, android.os.Parcel r5, int r6) throws android.os.RemoteException {
            /*
                r2 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                if (r3 == r0) goto L_0x0047
                r0 = 0
                switch(r3) {
                    case 1: goto L_0x002f;
                    case 2: goto L_0x000f;
                    default: goto L_0x000a;
                }
            L_0x000a:
                boolean r3 = super.onTransact(r3, r4, r5, r6)
                return r3
            L_0x000f:
                java.lang.String r3 = "com.mipay.sdk.IMipayResponse"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                java.lang.String r5 = r4.readString()
                int r6 = r4.readInt()
                if (r6 == 0) goto L_0x002b
                android.os.Parcelable$Creator r6 = android.os.Bundle.CREATOR
                java.lang.Object r4 = r6.createFromParcel(r4)
                r0 = r4
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x002b:
                r2.onError(r3, r5, r0)
                return r1
            L_0x002f:
                java.lang.String r3 = "com.mipay.sdk.IMipayResponse"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x0043
                android.os.Parcelable$Creator r3 = android.os.Bundle.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                r0 = r3
                android.os.Bundle r0 = (android.os.Bundle) r0
            L_0x0043:
                r2.onResult(r0)
                return r1
            L_0x0047:
                java.lang.String r3 = "com.mipay.sdk.IMipayResponse"
                r5.writeString(r3)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mipay.sdk.IMipayResponse.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    void onError(int i, String str, Bundle bundle) throws RemoteException;

    void onResult(Bundle bundle) throws RemoteException;
}
