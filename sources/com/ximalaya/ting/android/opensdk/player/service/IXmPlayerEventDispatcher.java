package com.ximalaya.ting.android.opensdk.player.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ximalaya.ting.android.opensdk.model.track.Track;

public interface IXmPlayerEventDispatcher extends IInterface {
    void onBufferProgress(int i) throws RemoteException;

    void onBufferingStart() throws RemoteException;

    void onBufferingStop() throws RemoteException;

    void onError(XmPlayerException xmPlayerException) throws RemoteException;

    void onPlayPause() throws RemoteException;

    void onPlayProgress(int i, int i2) throws RemoteException;

    void onPlayStart() throws RemoteException;

    void onPlayStop() throws RemoteException;

    void onSoundPlayComplete() throws RemoteException;

    void onSoundPrepared() throws RemoteException;

    void onSoundSwitch(Track track, Track track2) throws RemoteException;

    public static abstract class Stub extends Binder implements IXmPlayerEventDispatcher {
        private static final String DESCRIPTOR = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayerEventDispatcher";
        static final int TRANSACTION_onBufferProgress = 9;
        static final int TRANSACTION_onBufferingStart = 7;
        static final int TRANSACTION_onBufferingStop = 8;
        static final int TRANSACTION_onError = 11;
        static final int TRANSACTION_onPlayPause = 2;
        static final int TRANSACTION_onPlayProgress = 10;
        static final int TRANSACTION_onPlayStart = 1;
        static final int TRANSACTION_onPlayStop = 3;
        static final int TRANSACTION_onSoundPlayComplete = 4;
        static final int TRANSACTION_onSoundPrepared = 5;
        static final int TRANSACTION_onSoundSwitch = 6;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IXmPlayerEventDispatcher asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IXmPlayerEventDispatcher)) {
                return new Proxy(iBinder);
            }
            return (IXmPlayerEventDispatcher) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: com.ximalaya.ting.android.opensdk.model.track.Track} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: com.ximalaya.ting.android.opensdk.player.service.XmPlayerException} */
        /* JADX WARNING: type inference failed for: r2v0 */
        /* JADX WARNING: type inference failed for: r2v7 */
        /* JADX WARNING: type inference failed for: r2v8 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r4, android.os.Parcel r5, android.os.Parcel r6, int r7) throws android.os.RemoteException {
            /*
                r3 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                if (r4 == r0) goto L_0x00f2
                r0 = 0
                r2 = 0
                switch(r4) {
                    case 1: goto L_0x00e6;
                    case 2: goto L_0x00da;
                    case 3: goto L_0x00ce;
                    case 4: goto L_0x00c2;
                    case 5: goto L_0x00b6;
                    case 6: goto L_0x0073;
                    case 7: goto L_0x0067;
                    case 8: goto L_0x005b;
                    case 9: goto L_0x004b;
                    case 10: goto L_0x0037;
                    case 11: goto L_0x0010;
                    default: goto L_0x000b;
                }
            L_0x000b:
                boolean r4 = super.onTransact(r4, r5, r6, r7)
                return r4
            L_0x0010:
                java.lang.String r4 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayerEventDispatcher"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0024
                android.os.Parcelable$Creator<com.ximalaya.ting.android.opensdk.player.service.XmPlayerException> r4 = com.ximalaya.ting.android.opensdk.player.service.XmPlayerException.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r2 = r4
                com.ximalaya.ting.android.opensdk.player.service.XmPlayerException r2 = (com.ximalaya.ting.android.opensdk.player.service.XmPlayerException) r2
            L_0x0024:
                r3.onError(r2)
                r6.writeNoException()
                if (r2 == 0) goto L_0x0033
                r6.writeInt(r1)
                r2.writeToParcel(r6, r1)
                goto L_0x0036
            L_0x0033:
                r6.writeInt(r0)
            L_0x0036:
                return r1
            L_0x0037:
                java.lang.String r4 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayerEventDispatcher"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                int r5 = r5.readInt()
                r3.onPlayProgress(r4, r5)
                r6.writeNoException()
                return r1
            L_0x004b:
                java.lang.String r4 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayerEventDispatcher"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                r3.onBufferProgress(r4)
                r6.writeNoException()
                return r1
            L_0x005b:
                java.lang.String r4 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayerEventDispatcher"
                r5.enforceInterface(r4)
                r3.onBufferingStop()
                r6.writeNoException()
                return r1
            L_0x0067:
                java.lang.String r4 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayerEventDispatcher"
                r5.enforceInterface(r4)
                r3.onBufferingStart()
                r6.writeNoException()
                return r1
            L_0x0073:
                java.lang.String r4 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayerEventDispatcher"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0087
                android.os.Parcelable$Creator<com.ximalaya.ting.android.opensdk.model.track.Track> r4 = com.ximalaya.ting.android.opensdk.model.track.Track.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                com.ximalaya.ting.android.opensdk.model.track.Track r4 = (com.ximalaya.ting.android.opensdk.model.track.Track) r4
                goto L_0x0088
            L_0x0087:
                r4 = r2
            L_0x0088:
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x0097
                android.os.Parcelable$Creator<com.ximalaya.ting.android.opensdk.model.track.Track> r7 = com.ximalaya.ting.android.opensdk.model.track.Track.CREATOR
                java.lang.Object r5 = r7.createFromParcel(r5)
                r2 = r5
                com.ximalaya.ting.android.opensdk.model.track.Track r2 = (com.ximalaya.ting.android.opensdk.model.track.Track) r2
            L_0x0097:
                r3.onSoundSwitch(r4, r2)
                r6.writeNoException()
                if (r4 == 0) goto L_0x00a6
                r6.writeInt(r1)
                r4.writeToParcel(r6, r1)
                goto L_0x00a9
            L_0x00a6:
                r6.writeInt(r0)
            L_0x00a9:
                if (r2 == 0) goto L_0x00b2
                r6.writeInt(r1)
                r2.writeToParcel(r6, r1)
                goto L_0x00b5
            L_0x00b2:
                r6.writeInt(r0)
            L_0x00b5:
                return r1
            L_0x00b6:
                java.lang.String r4 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayerEventDispatcher"
                r5.enforceInterface(r4)
                r3.onSoundPrepared()
                r6.writeNoException()
                return r1
            L_0x00c2:
                java.lang.String r4 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayerEventDispatcher"
                r5.enforceInterface(r4)
                r3.onSoundPlayComplete()
                r6.writeNoException()
                return r1
            L_0x00ce:
                java.lang.String r4 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayerEventDispatcher"
                r5.enforceInterface(r4)
                r3.onPlayStop()
                r6.writeNoException()
                return r1
            L_0x00da:
                java.lang.String r4 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayerEventDispatcher"
                r5.enforceInterface(r4)
                r3.onPlayPause()
                r6.writeNoException()
                return r1
            L_0x00e6:
                java.lang.String r4 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayerEventDispatcher"
                r5.enforceInterface(r4)
                r3.onPlayStart()
                r6.writeNoException()
                return r1
            L_0x00f2:
                java.lang.String r4 = "com.ximalaya.ting.android.opensdk.player.service.IXmPlayerEventDispatcher"
                r6.writeString(r4)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.opensdk.player.service.IXmPlayerEventDispatcher.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IXmPlayerEventDispatcher {
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

            public void onPlayStart() throws RemoteException {
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

            public void onPlayPause() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onPlayStop() throws RemoteException {
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

            public void onSoundPlayComplete() throws RemoteException {
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

            public void onSoundPrepared() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onSoundSwitch(Track track, Track track2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (track != null) {
                        obtain.writeInt(1);
                        track.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (track2 != null) {
                        obtain.writeInt(1);
                        track2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        track.a(obtain2);
                    }
                    if (obtain2.readInt() != 0) {
                        track2.a(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onBufferingStart() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onBufferingStop() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onBufferProgress(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onPlayProgress(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onError(XmPlayerException xmPlayerException) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (xmPlayerException != null) {
                        obtain.writeInt(1);
                        xmPlayerException.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        xmPlayerException.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
