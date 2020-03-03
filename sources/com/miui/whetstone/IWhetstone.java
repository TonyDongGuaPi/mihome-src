package com.miui.whetstone;

import android.graphics.Bitmap;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.InputEvent;
import com.miui.whetstone.strategy.WhetstonePackageInfo;

public interface IWhetstone extends IInterface {
    IBinder getApplicationThread(int i) throws RemoteException;

    int getWhetstoneLevel() throws RemoteException;

    WhetstonePackageInfo getWhetstonePackage(IBinder iBinder) throws RemoteException;

    boolean injectInputEvent(InputEvent inputEvent, int i) throws RemoteException;

    boolean isNeeded(int i) throws RemoteException;

    boolean putSetting(String str, String str2, String str3) throws RemoteException;

    Bitmap screenShot(int i, int i2) throws RemoteException;

    void setWhetstoneLevel(int i) throws RemoteException;

    void wifiSmartConfigStartAsync(String str, String str2, String str3, String str4, String str5, IBinder iBinder) throws RemoteException;

    void wifiSmartConfigStop() throws RemoteException;

    boolean wifiSmartConfigSupport() throws RemoteException;

    public static abstract class Stub extends Binder implements IWhetstone {
        private static final String DESCRIPTOR = "com.miui.whetstone.IWhetstone";
        static final int TRANSACTION_getApplicationThread = 5;
        static final int TRANSACTION_getWhetstoneLevel = 3;
        static final int TRANSACTION_getWhetstonePackage = 4;
        static final int TRANSACTION_injectInputEvent = 10;
        static final int TRANSACTION_isNeeded = 1;
        static final int TRANSACTION_putSetting = 11;
        static final int TRANSACTION_screenShot = 9;
        static final int TRANSACTION_setWhetstoneLevel = 2;
        static final int TRANSACTION_wifiSmartConfigStart = 6;
        static final int TRANSACTION_wifiSmartConfigStop = 7;
        static final int TRANSACTION_wifiSmartConfigSupport = 8;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWhetstone asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IWhetstone)) {
                return new Proxy(iBinder);
            }
            return (IWhetstone) queryLocalInterface;
        }

        /* JADX WARNING: type inference failed for: r0v1 */
        /* JADX WARNING: type inference failed for: r0v2, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r0v4, types: [android.view.InputEvent] */
        /* JADX WARNING: type inference failed for: r0v7 */
        /* JADX WARNING: type inference failed for: r0v8 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r10, android.os.Parcel r11, android.os.Parcel r12, int r13) throws android.os.RemoteException {
            /*
                r9 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                if (r10 == r0) goto L_0x0125
                r0 = 0
                r2 = 0
                switch(r10) {
                    case 1: goto L_0x0111;
                    case 2: goto L_0x0101;
                    case 3: goto L_0x00f1;
                    case 4: goto L_0x00ce;
                    case 5: goto L_0x00b1;
                    case 6: goto L_0x008c;
                    case 7: goto L_0x0080;
                    case 8: goto L_0x0070;
                    case 9: goto L_0x004f;
                    case 10: goto L_0x002c;
                    case 11: goto L_0x0010;
                    default: goto L_0x000b;
                }
            L_0x000b:
                boolean r10 = super.onTransact(r10, r11, r12, r13)
                return r10
            L_0x0010:
                java.lang.String r10 = "com.miui.whetstone.IWhetstone"
                r11.enforceInterface(r10)
                java.lang.String r10 = r11.readString()
                java.lang.String r13 = r11.readString()
                java.lang.String r11 = r11.readString()
                boolean r10 = r9.putSetting(r10, r13, r11)
                r12.writeNoException()
                r12.writeInt(r10)
                return r1
            L_0x002c:
                java.lang.String r10 = "com.miui.whetstone.IWhetstone"
                r11.enforceInterface(r10)
                int r10 = r11.readInt()
                if (r10 == 0) goto L_0x0040
                android.os.Parcelable$Creator r10 = android.view.InputEvent.CREATOR
                java.lang.Object r10 = r10.createFromParcel(r11)
                r0 = r10
                android.view.InputEvent r0 = (android.view.InputEvent) r0
            L_0x0040:
                int r10 = r11.readInt()
                boolean r10 = r9.injectInputEvent(r0, r10)
                r12.writeNoException()
                r12.writeInt(r10)
                return r1
            L_0x004f:
                java.lang.String r10 = "com.miui.whetstone.IWhetstone"
                r11.enforceInterface(r10)
                int r10 = r11.readInt()
                int r11 = r11.readInt()
                android.graphics.Bitmap r10 = r9.screenShot(r10, r11)
                r12.writeNoException()
                if (r10 == 0) goto L_0x006c
                r12.writeInt(r1)
                r10.writeToParcel(r12, r2)
                goto L_0x006f
            L_0x006c:
                r12.writeInt(r2)
            L_0x006f:
                return r1
            L_0x0070:
                java.lang.String r10 = "com.miui.whetstone.IWhetstone"
                r11.enforceInterface(r10)
                boolean r10 = r9.wifiSmartConfigSupport()
                r12.writeNoException()
                r12.writeInt(r10)
                return r1
            L_0x0080:
                java.lang.String r10 = "com.miui.whetstone.IWhetstone"
                r11.enforceInterface(r10)
                r9.wifiSmartConfigStop()
                r12.writeNoException()
                return r1
            L_0x008c:
                java.lang.String r10 = "com.miui.whetstone.IWhetstone"
                r11.enforceInterface(r10)
                java.lang.String r3 = r11.readString()
                java.lang.String r4 = r11.readString()
                java.lang.String r5 = r11.readString()
                java.lang.String r6 = r11.readString()
                java.lang.String r7 = r11.readString()
                android.os.IBinder r8 = r11.readStrongBinder()
                r2 = r9
                r2.wifiSmartConfigStartAsync(r3, r4, r5, r6, r7, r8)
                r12.writeNoException()
                return r1
            L_0x00b1:
                java.lang.String r10 = "com.miui.whetstone.IWhetstone"
                r11.enforceInterface(r10)
                int r10 = r11.readInt()
                android.os.IBinder r10 = r9.getApplicationThread(r10)
                r12.writeNoException()
                if (r10 == 0) goto L_0x00ca
                r12.writeInt(r1)
                r12.writeStrongBinder(r10)
                goto L_0x00cd
            L_0x00ca:
                r12.writeInt(r2)
            L_0x00cd:
                return r1
            L_0x00ce:
                java.lang.String r10 = "com.miui.whetstone.IWhetstone"
                r11.enforceInterface(r10)
                int r10 = r11.readInt()
                if (r10 <= 0) goto L_0x00dd
                android.os.IBinder r0 = r11.readStrongBinder()
            L_0x00dd:
                com.miui.whetstone.strategy.WhetstonePackageInfo r10 = r9.getWhetstonePackage(r0)
                r12.writeNoException()
                if (r10 == 0) goto L_0x00ed
                r12.writeInt(r1)
                r10.writeToParcel(r12, r2)
                goto L_0x00f0
            L_0x00ed:
                r12.writeInt(r2)
            L_0x00f0:
                return r1
            L_0x00f1:
                java.lang.String r10 = "com.miui.whetstone.IWhetstone"
                r11.enforceInterface(r10)
                int r10 = r9.getWhetstoneLevel()
                r12.writeNoException()
                r12.writeInt(r10)
                return r1
            L_0x0101:
                java.lang.String r10 = "com.miui.whetstone.IWhetstone"
                r11.enforceInterface(r10)
                int r10 = r11.readInt()
                r9.setWhetstoneLevel(r10)
                r12.writeNoException()
                return r1
            L_0x0111:
                java.lang.String r10 = "com.miui.whetstone.IWhetstone"
                r11.enforceInterface(r10)
                int r10 = r11.readInt()
                boolean r10 = r9.isNeeded(r10)
                r12.writeNoException()
                r12.writeInt(r10)
                return r1
            L_0x0125:
                java.lang.String r10 = "com.miui.whetstone.IWhetstone"
                r12.writeString(r10)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.miui.whetstone.IWhetstone.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IWhetstone {
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

            public boolean isNeeded(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    boolean z = false;
                    this.mRemote.transact(1, obtain, obtain2, 0);
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

            public void setWhetstoneLevel(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getWhetstoneLevel() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public WhetstonePackageInfo getWhetstonePackage(IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (iBinder != null) {
                        obtain.writeInt(1);
                        obtain.writeStrongBinder(iBinder);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (WhetstonePackageInfo) WhetstonePackageInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder getApplicationThread(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? obtain2.readStrongBinder() : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void wifiSmartConfigStartAsync(String str, String str2, String str3, String str4, String str5, IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    obtain.writeString(str5);
                    obtain.writeStrongBinder(iBinder);
                    this.mRemote.transact(6, obtain, obtain2, 1);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void wifiSmartConfigStop() throws RemoteException {
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

            public boolean wifiSmartConfigSupport() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
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

            public Bitmap screenShot(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() == 1 ? (Bitmap) Bitmap.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean injectInputEvent(InputEvent inputEvent, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
                    if (inputEvent != null) {
                        obtain.writeInt(1);
                        inputEvent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean putSetting(String str, String str2, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
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
        }
    }
}
