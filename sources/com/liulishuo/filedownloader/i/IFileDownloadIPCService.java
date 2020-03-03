package com.liulishuo.filedownloader.i;

import android.app.Notification;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.liulishuo.filedownloader.model.FileDownloadHeader;

public interface IFileDownloadIPCService extends IInterface {
    boolean checkDownloading(String str, String str2) throws RemoteException;

    void clearAllTaskData() throws RemoteException;

    boolean clearTaskData(int i) throws RemoteException;

    long getSofar(int i) throws RemoteException;

    byte getStatus(int i) throws RemoteException;

    long getTotal(int i) throws RemoteException;

    boolean isIdle() throws RemoteException;

    boolean pause(int i) throws RemoteException;

    void pauseAllTasks() throws RemoteException;

    void registerCallback(IFileDownloadIPCCallback iFileDownloadIPCCallback) throws RemoteException;

    boolean setMaxNetworkThreadCount(int i) throws RemoteException;

    void start(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, FileDownloadHeader fileDownloadHeader, boolean z3) throws RemoteException;

    void startForeground(int i, Notification notification) throws RemoteException;

    void stopForeground(boolean z) throws RemoteException;

    void unregisterCallback(IFileDownloadIPCCallback iFileDownloadIPCCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IFileDownloadIPCService {
        private static final String DESCRIPTOR = "com.liulishuo.filedownloader.i.IFileDownloadIPCService";
        static final int TRANSACTION_checkDownloading = 3;
        static final int TRANSACTION_clearAllTaskData = 15;
        static final int TRANSACTION_clearTaskData = 14;
        static final int TRANSACTION_getSofar = 8;
        static final int TRANSACTION_getStatus = 10;
        static final int TRANSACTION_getTotal = 9;
        static final int TRANSACTION_isIdle = 11;
        static final int TRANSACTION_pause = 5;
        static final int TRANSACTION_pauseAllTasks = 6;
        static final int TRANSACTION_registerCallback = 1;
        static final int TRANSACTION_setMaxNetworkThreadCount = 7;
        static final int TRANSACTION_start = 4;
        static final int TRANSACTION_startForeground = 12;
        static final int TRANSACTION_stopForeground = 13;
        static final int TRANSACTION_unregisterCallback = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IFileDownloadIPCService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IFileDownloadIPCService)) {
                return new Proxy(iBinder);
            }
            return (IFileDownloadIPCService) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: android.app.Notification} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r17, android.os.Parcel r18, android.os.Parcel r19, int r20) throws android.os.RemoteException {
            /*
                r16 = this;
                r10 = r16
                r0 = r17
                r1 = r18
                r11 = r19
                r2 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r12 = 1
                if (r0 == r2) goto L_0x0175
                r2 = 0
                r3 = 0
                switch(r0) {
                    case 1: goto L_0x0164;
                    case 2: goto L_0x0153;
                    case 3: goto L_0x013b;
                    case 4: goto L_0x00e4;
                    case 5: goto L_0x00d0;
                    case 6: goto L_0x00c4;
                    case 7: goto L_0x00b0;
                    case 8: goto L_0x009c;
                    case 9: goto L_0x0088;
                    case 10: goto L_0x0074;
                    case 11: goto L_0x0064;
                    case 12: goto L_0x0048;
                    case 13: goto L_0x0038;
                    case 14: goto L_0x0024;
                    case 15: goto L_0x0018;
                    default: goto L_0x0013;
                }
            L_0x0013:
                boolean r0 = super.onTransact(r17, r18, r19, r20)
                return r0
            L_0x0018:
                java.lang.String r0 = "com.liulishuo.filedownloader.i.IFileDownloadIPCService"
                r1.enforceInterface(r0)
                r16.clearAllTaskData()
                r19.writeNoException()
                return r12
            L_0x0024:
                java.lang.String r0 = "com.liulishuo.filedownloader.i.IFileDownloadIPCService"
                r1.enforceInterface(r0)
                int r0 = r18.readInt()
                boolean r0 = r10.clearTaskData(r0)
                r19.writeNoException()
                r11.writeInt(r0)
                return r12
            L_0x0038:
                java.lang.String r0 = "com.liulishuo.filedownloader.i.IFileDownloadIPCService"
                r1.enforceInterface(r0)
                int r0 = r18.readInt()
                if (r0 == 0) goto L_0x0044
                r3 = 1
            L_0x0044:
                r10.stopForeground(r3)
                return r12
            L_0x0048:
                java.lang.String r0 = "com.liulishuo.filedownloader.i.IFileDownloadIPCService"
                r1.enforceInterface(r0)
                int r0 = r18.readInt()
                int r3 = r18.readInt()
                if (r3 == 0) goto L_0x0060
                android.os.Parcelable$Creator r2 = android.app.Notification.CREATOR
                java.lang.Object r1 = r2.createFromParcel(r1)
                r2 = r1
                android.app.Notification r2 = (android.app.Notification) r2
            L_0x0060:
                r10.startForeground(r0, r2)
                return r12
            L_0x0064:
                java.lang.String r0 = "com.liulishuo.filedownloader.i.IFileDownloadIPCService"
                r1.enforceInterface(r0)
                boolean r0 = r16.isIdle()
                r19.writeNoException()
                r11.writeInt(r0)
                return r12
            L_0x0074:
                java.lang.String r0 = "com.liulishuo.filedownloader.i.IFileDownloadIPCService"
                r1.enforceInterface(r0)
                int r0 = r18.readInt()
                byte r0 = r10.getStatus(r0)
                r19.writeNoException()
                r11.writeByte(r0)
                return r12
            L_0x0088:
                java.lang.String r0 = "com.liulishuo.filedownloader.i.IFileDownloadIPCService"
                r1.enforceInterface(r0)
                int r0 = r18.readInt()
                long r0 = r10.getTotal(r0)
                r19.writeNoException()
                r11.writeLong(r0)
                return r12
            L_0x009c:
                java.lang.String r0 = "com.liulishuo.filedownloader.i.IFileDownloadIPCService"
                r1.enforceInterface(r0)
                int r0 = r18.readInt()
                long r0 = r10.getSofar(r0)
                r19.writeNoException()
                r11.writeLong(r0)
                return r12
            L_0x00b0:
                java.lang.String r0 = "com.liulishuo.filedownloader.i.IFileDownloadIPCService"
                r1.enforceInterface(r0)
                int r0 = r18.readInt()
                boolean r0 = r10.setMaxNetworkThreadCount(r0)
                r19.writeNoException()
                r11.writeInt(r0)
                return r12
            L_0x00c4:
                java.lang.String r0 = "com.liulishuo.filedownloader.i.IFileDownloadIPCService"
                r1.enforceInterface(r0)
                r16.pauseAllTasks()
                r19.writeNoException()
                return r12
            L_0x00d0:
                java.lang.String r0 = "com.liulishuo.filedownloader.i.IFileDownloadIPCService"
                r1.enforceInterface(r0)
                int r0 = r18.readInt()
                boolean r0 = r10.pause(r0)
                r19.writeNoException()
                r11.writeInt(r0)
                return r12
            L_0x00e4:
                java.lang.String r0 = "com.liulishuo.filedownloader.i.IFileDownloadIPCService"
                r1.enforceInterface(r0)
                java.lang.String r4 = r18.readString()
                java.lang.String r5 = r18.readString()
                int r0 = r18.readInt()
                if (r0 == 0) goto L_0x00f9
                r6 = 1
                goto L_0x00fa
            L_0x00f9:
                r6 = 0
            L_0x00fa:
                int r7 = r18.readInt()
                int r8 = r18.readInt()
                int r9 = r18.readInt()
                int r0 = r18.readInt()
                if (r0 == 0) goto L_0x010e
                r13 = 1
                goto L_0x010f
            L_0x010e:
                r13 = 0
            L_0x010f:
                int r0 = r18.readInt()
                if (r0 == 0) goto L_0x011f
                android.os.Parcelable$Creator<com.liulishuo.filedownloader.model.FileDownloadHeader> r0 = com.liulishuo.filedownloader.model.FileDownloadHeader.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r1)
                com.liulishuo.filedownloader.model.FileDownloadHeader r0 = (com.liulishuo.filedownloader.model.FileDownloadHeader) r0
                r14 = r0
                goto L_0x0120
            L_0x011f:
                r14 = r2
            L_0x0120:
                int r0 = r18.readInt()
                if (r0 == 0) goto L_0x0128
                r15 = 1
                goto L_0x0129
            L_0x0128:
                r15 = 0
            L_0x0129:
                r0 = r16
                r1 = r4
                r2 = r5
                r3 = r6
                r4 = r7
                r5 = r8
                r6 = r9
                r7 = r13
                r8 = r14
                r9 = r15
                r0.start(r1, r2, r3, r4, r5, r6, r7, r8, r9)
                r19.writeNoException()
                return r12
            L_0x013b:
                java.lang.String r0 = "com.liulishuo.filedownloader.i.IFileDownloadIPCService"
                r1.enforceInterface(r0)
                java.lang.String r0 = r18.readString()
                java.lang.String r1 = r18.readString()
                boolean r0 = r10.checkDownloading(r0, r1)
                r19.writeNoException()
                r11.writeInt(r0)
                return r12
            L_0x0153:
                java.lang.String r0 = "com.liulishuo.filedownloader.i.IFileDownloadIPCService"
                r1.enforceInterface(r0)
                android.os.IBinder r0 = r18.readStrongBinder()
                com.liulishuo.filedownloader.i.IFileDownloadIPCCallback r0 = com.liulishuo.filedownloader.i.IFileDownloadIPCCallback.Stub.asInterface(r0)
                r10.unregisterCallback(r0)
                return r12
            L_0x0164:
                java.lang.String r0 = "com.liulishuo.filedownloader.i.IFileDownloadIPCService"
                r1.enforceInterface(r0)
                android.os.IBinder r0 = r18.readStrongBinder()
                com.liulishuo.filedownloader.i.IFileDownloadIPCCallback r0 = com.liulishuo.filedownloader.i.IFileDownloadIPCCallback.Stub.asInterface(r0)
                r10.registerCallback(r0)
                return r12
            L_0x0175:
                java.lang.String r0 = "com.liulishuo.filedownloader.i.IFileDownloadIPCService"
                r11.writeString(r0)
                return r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.i.IFileDownloadIPCService.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IFileDownloadIPCService {
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

            public void registerCallback(IFileDownloadIPCCallback iFileDownloadIPCCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iFileDownloadIPCCallback != null ? iFileDownloadIPCCallback.asBinder() : null);
                    this.mRemote.transact(1, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void unregisterCallback(IFileDownloadIPCCallback iFileDownloadIPCCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iFileDownloadIPCCallback != null ? iFileDownloadIPCCallback.asBinder() : null);
                    this.mRemote.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public boolean checkDownloading(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    boolean z = false;
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void start(String str, String str2, boolean z, int i, int i2, int i3, boolean z2, FileDownloadHeader fileDownloadHeader, boolean z3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeInt(z2 ? 1 : 0);
                    if (fileDownloadHeader != null) {
                        obtain.writeInt(1);
                        fileDownloadHeader.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(z3 ? 1 : 0);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean pause(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    boolean z = false;
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void pauseAllTasks() throws RemoteException {
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

            public boolean setMaxNetworkThreadCount(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    boolean z = false;
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public long getSofar(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readLong();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public long getTotal(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readLong();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public byte getStatus(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readByte();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isIdle() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void startForeground(int i, Notification notification) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (notification != null) {
                        obtain.writeInt(1);
                        notification.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(12, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void stopForeground(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(13, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public boolean clearTaskData(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    boolean z = false;
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void clearAllTaskData() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
