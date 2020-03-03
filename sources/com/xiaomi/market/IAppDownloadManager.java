package com.xiaomi.market;

import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IAppDownloadManager extends IInterface {

    public static class Default implements IAppDownloadManager {
        public IBinder asBinder() {
            return null;
        }

        public boolean cancel(String str, String str2) throws RemoteException {
            return false;
        }

        public void download(Bundle bundle) throws RemoteException {
        }

        public void downloadByUri(Uri uri) throws RemoteException {
        }

        public boolean pause(String str, String str2) throws RemoteException {
            return false;
        }

        public boolean resume(String str, String str2) throws RemoteException {
            return false;
        }
    }

    boolean cancel(String str, String str2) throws RemoteException;

    void download(Bundle bundle) throws RemoteException;

    void downloadByUri(Uri uri) throws RemoteException;

    boolean pause(String str, String str2) throws RemoteException;

    boolean resume(String str, String str2) throws RemoteException;

    public static abstract class Stub extends Binder implements IAppDownloadManager {
        private static final String DESCRIPTOR = "com.xiaomi.market.IAppDownloadManager";
        static final int TRANSACTION_cancel = 2;
        static final int TRANSACTION_download = 1;
        static final int TRANSACTION_downloadByUri = 5;
        static final int TRANSACTION_pause = 3;
        static final int TRANSACTION_resume = 4;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAppDownloadManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IAppDownloadManager)) {
                return new Proxy(iBinder);
            }
            return (IAppDownloadManager) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: android.net.Uri} */
        /* JADX WARNING: type inference failed for: r1v1 */
        /* JADX WARNING: type inference failed for: r1v8 */
        /* JADX WARNING: type inference failed for: r1v9 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r4, android.os.Parcel r5, android.os.Parcel r6, int r7) throws android.os.RemoteException {
            /*
                r3 = this;
                java.lang.String r0 = "com.xiaomi.market.IAppDownloadManager"
                r1 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r2 = 1
                if (r4 == r1) goto L_0x0085
                r1 = 0
                switch(r4) {
                    case 1: goto L_0x006c;
                    case 2: goto L_0x0056;
                    case 3: goto L_0x0040;
                    case 4: goto L_0x002a;
                    case 5: goto L_0x0011;
                    default: goto L_0x000c;
                }
            L_0x000c:
                boolean r4 = super.onTransact(r4, r5, r6, r7)
                return r4
            L_0x0011:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0023
                android.os.Parcelable$Creator r4 = android.net.Uri.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                android.net.Uri r1 = (android.net.Uri) r1
            L_0x0023:
                r3.downloadByUri(r1)
                r6.writeNoException()
                return r2
            L_0x002a:
                r5.enforceInterface(r0)
                java.lang.String r4 = r5.readString()
                java.lang.String r5 = r5.readString()
                boolean r4 = r3.resume(r4, r5)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x0040:
                r5.enforceInterface(r0)
                java.lang.String r4 = r5.readString()
                java.lang.String r5 = r5.readString()
                boolean r4 = r3.pause(r4, r5)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x0056:
                r5.enforceInterface(r0)
                java.lang.String r4 = r5.readString()
                java.lang.String r5 = r5.readString()
                boolean r4 = r3.cancel(r4, r5)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x006c:
                r5.enforceInterface(r0)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x007e
                android.os.Parcelable$Creator r4 = android.os.Bundle.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                android.os.Bundle r1 = (android.os.Bundle) r1
            L_0x007e:
                r3.download(r1)
                r6.writeNoException()
                return r2
            L_0x0085:
                r6.writeString(r0)
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.market.IAppDownloadManager.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IAppDownloadManager {
            public static IAppDownloadManager sDefaultImpl;
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

            public void download(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().download(bundle);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean cancel(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    boolean z = false;
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().cancel(str, str2);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean pause(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    boolean z = false;
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().pause(str, str2);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean resume(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    boolean z = false;
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().resume(str, str2);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void downloadByUri(Uri uri) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (uri != null) {
                        obtain.writeInt(1);
                        uri.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(5, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().downloadByUri(uri);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAppDownloadManager iAppDownloadManager) {
            if (Proxy.sDefaultImpl != null || iAppDownloadManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAppDownloadManager;
            return true;
        }

        public static IAppDownloadManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
