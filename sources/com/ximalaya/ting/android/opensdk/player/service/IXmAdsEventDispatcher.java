package com.ximalaya.ting.android.opensdk.player.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ximalaya.ting.android.opensdk.model.advertis.Advertis;
import com.ximalaya.ting.android.opensdk.model.advertis.AdvertisList;

public interface IXmAdsEventDispatcher extends IInterface {
    void onAdsStartBuffering() throws RemoteException;

    void onAdsStopBuffering() throws RemoteException;

    void onCompletePlayAds() throws RemoteException;

    void onError(int i, int i2) throws RemoteException;

    void onGetAdsInfo(AdvertisList advertisList) throws RemoteException;

    void onStartGetAdsInfo() throws RemoteException;

    void onStartPlayAds(Advertis advertis, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IXmAdsEventDispatcher {
        private static final String DESCRIPTOR = "com.ximalaya.ting.android.opensdk.player.service.IXmAdsEventDispatcher";
        static final int TRANSACTION_onAdsStartBuffering = 3;
        static final int TRANSACTION_onAdsStopBuffering = 4;
        static final int TRANSACTION_onCompletePlayAds = 6;
        static final int TRANSACTION_onError = 7;
        static final int TRANSACTION_onGetAdsInfo = 2;
        static final int TRANSACTION_onStartGetAdsInfo = 1;
        static final int TRANSACTION_onStartPlayAds = 5;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IXmAdsEventDispatcher asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IXmAdsEventDispatcher)) {
                return new Proxy(iBinder);
            }
            return (IXmAdsEventDispatcher) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: com.ximalaya.ting.android.opensdk.model.advertis.AdvertisList} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: com.ximalaya.ting.android.opensdk.model.advertis.Advertis} */
        /* JADX WARNING: type inference failed for: r0v1 */
        /* JADX WARNING: type inference failed for: r0v8 */
        /* JADX WARNING: type inference failed for: r0v9 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r3, android.os.Parcel r4, android.os.Parcel r5, int r6) throws android.os.RemoteException {
            /*
                r2 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                if (r3 == r0) goto L_0x008d
                r0 = 0
                switch(r3) {
                    case 1: goto L_0x0081;
                    case 2: goto L_0x0066;
                    case 3: goto L_0x005a;
                    case 4: goto L_0x004e;
                    case 5: goto L_0x002f;
                    case 6: goto L_0x0023;
                    case 7: goto L_0x000f;
                    default: goto L_0x000a;
                }
            L_0x000a:
                boolean r3 = super.onTransact(r3, r4, r5, r6)
                return r3
            L_0x000f:
                java.lang.String r3 = "com.ximalaya.ting.android.opensdk.player.service.IXmAdsEventDispatcher"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                int r4 = r4.readInt()
                r2.onError(r3, r4)
                r5.writeNoException()
                return r1
            L_0x0023:
                java.lang.String r3 = "com.ximalaya.ting.android.opensdk.player.service.IXmAdsEventDispatcher"
                r4.enforceInterface(r3)
                r2.onCompletePlayAds()
                r5.writeNoException()
                return r1
            L_0x002f:
                java.lang.String r3 = "com.ximalaya.ting.android.opensdk.player.service.IXmAdsEventDispatcher"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x0043
                android.os.Parcelable$Creator<com.ximalaya.ting.android.opensdk.model.advertis.Advertis> r3 = com.ximalaya.ting.android.opensdk.model.advertis.Advertis.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                r0 = r3
                com.ximalaya.ting.android.opensdk.model.advertis.Advertis r0 = (com.ximalaya.ting.android.opensdk.model.advertis.Advertis) r0
            L_0x0043:
                int r3 = r4.readInt()
                r2.onStartPlayAds(r0, r3)
                r5.writeNoException()
                return r1
            L_0x004e:
                java.lang.String r3 = "com.ximalaya.ting.android.opensdk.player.service.IXmAdsEventDispatcher"
                r4.enforceInterface(r3)
                r2.onAdsStopBuffering()
                r5.writeNoException()
                return r1
            L_0x005a:
                java.lang.String r3 = "com.ximalaya.ting.android.opensdk.player.service.IXmAdsEventDispatcher"
                r4.enforceInterface(r3)
                r2.onAdsStartBuffering()
                r5.writeNoException()
                return r1
            L_0x0066:
                java.lang.String r3 = "com.ximalaya.ting.android.opensdk.player.service.IXmAdsEventDispatcher"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x007a
                android.os.Parcelable$Creator<com.ximalaya.ting.android.opensdk.model.advertis.AdvertisList> r3 = com.ximalaya.ting.android.opensdk.model.advertis.AdvertisList.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                r0 = r3
                com.ximalaya.ting.android.opensdk.model.advertis.AdvertisList r0 = (com.ximalaya.ting.android.opensdk.model.advertis.AdvertisList) r0
            L_0x007a:
                r2.onGetAdsInfo(r0)
                r5.writeNoException()
                return r1
            L_0x0081:
                java.lang.String r3 = "com.ximalaya.ting.android.opensdk.player.service.IXmAdsEventDispatcher"
                r4.enforceInterface(r3)
                r2.onStartGetAdsInfo()
                r5.writeNoException()
                return r1
            L_0x008d:
                java.lang.String r3 = "com.ximalaya.ting.android.opensdk.player.service.IXmAdsEventDispatcher"
                r5.writeString(r3)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.player.service.IXmAdsEventDispatcher.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IXmAdsEventDispatcher {
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

            public void onStartGetAdsInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onGetAdsInfo(AdvertisList advertisList) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (advertisList != null) {
                        obtain.writeInt(1);
                        advertisList.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onAdsStartBuffering() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onAdsStopBuffering() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onStartPlayAds(Advertis advertis, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (advertis != null) {
                        obtain.writeInt(1);
                        advertis.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onCompletePlayAds() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onError(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
