package com.mipay.sdk;

import android.accounts.Account;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IMipayService extends IInterface {

    public static abstract class Stub extends Binder implements IMipayService {

        /* renamed from: a  reason: collision with root package name */
        static final int f8142a = 1;
        private static final String b = "com.mipay.sdk.IMipayService";

        private static class a implements IMipayService {

            /* renamed from: a  reason: collision with root package name */
            private IBinder f8143a;

            a(IBinder iBinder) {
                this.f8143a = iBinder;
            }

            public String a() {
                return Stub.b;
            }

            public IBinder asBinder() {
                return this.f8143a;
            }

            public void pay(IMipayResponse iMipayResponse, Account account, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.b);
                    obtain.writeStrongBinder(iMipayResponse != null ? iMipayResponse.asBinder() : null);
                    if (account != null) {
                        obtain.writeInt(1);
                        account.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f8143a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, b);
        }

        public static IMipayService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(b);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IMipayService)) ? new a(iBinder) : (IMipayService) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: android.os.Bundle} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r5, android.os.Parcel r6, android.os.Parcel r7, int r8) throws android.os.RemoteException {
            /*
                r4 = this;
                r0 = 1
                if (r5 == r0) goto L_0x0013
                r1 = 1598968902(0x5f4e5446, float:1.4867585E19)
                if (r5 == r1) goto L_0x000d
                boolean r5 = super.onTransact(r5, r6, r7, r8)
                return r5
            L_0x000d:
                java.lang.String r5 = "com.mipay.sdk.IMipayService"
                r7.writeString(r5)
                return r0
            L_0x0013:
                java.lang.String r5 = "com.mipay.sdk.IMipayService"
                r6.enforceInterface(r5)
                android.os.IBinder r5 = r6.readStrongBinder()
                com.mipay.sdk.IMipayResponse r5 = com.mipay.sdk.IMipayResponse.Stub.asInterface(r5)
                int r8 = r6.readInt()
                r1 = 0
                if (r8 == 0) goto L_0x0030
                android.os.Parcelable$Creator r8 = android.accounts.Account.CREATOR
                java.lang.Object r8 = r8.createFromParcel(r6)
                android.accounts.Account r8 = (android.accounts.Account) r8
                goto L_0x0031
            L_0x0030:
                r8 = r1
            L_0x0031:
                java.lang.String r2 = r6.readString()
                int r3 = r6.readInt()
                if (r3 == 0) goto L_0x0044
                android.os.Parcelable$Creator r1 = android.os.Bundle.CREATOR
                java.lang.Object r6 = r1.createFromParcel(r6)
                r1 = r6
                android.os.Bundle r1 = (android.os.Bundle) r1
            L_0x0044:
                r4.pay(r5, r8, r2, r1)
                r7.writeNoException()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mipay.sdk.IMipayService.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    void pay(IMipayResponse iMipayResponse, Account account, String str, Bundle bundle) throws RemoteException;
}
