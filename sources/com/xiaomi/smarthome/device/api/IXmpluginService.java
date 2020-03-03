package com.xiaomi.smarthome.device.api;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xiaomi.router.miio.miioplugin.IMessageCallback;
import com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo;

public interface IXmpluginService extends IInterface {
    void sendMessage(PluginPackageInfo pluginPackageInfo, String str, int i, Intent intent, IMessageCallback iMessageCallback, boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements IXmpluginService {
        private static final String DESCRIPTOR = "com.xiaomi.smarthome.device.api.IXmpluginService";
        static final int TRANSACTION_sendMessage = 1;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IXmpluginService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IXmpluginService)) {
                return new Proxy(iBinder);
            }
            return (IXmpluginService) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v7, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: android.content.Intent} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r10, android.os.Parcel r11, android.os.Parcel r12, int r13) throws android.os.RemoteException {
            /*
                r9 = this;
                java.lang.String r0 = "com.xiaomi.smarthome.device.api.IXmpluginService"
                r1 = 1
                if (r10 == r1) goto L_0x0013
                r2 = 1598968902(0x5f4e5446, float:1.4867585E19)
                if (r10 == r2) goto L_0x000f
                boolean r10 = super.onTransact(r10, r11, r12, r13)
                return r10
            L_0x000f:
                r12.writeString(r0)
                return r1
            L_0x0013:
                r11.enforceInterface(r0)
                int r10 = r11.readInt()
                r13 = 0
                if (r10 == 0) goto L_0x0027
                android.os.Parcelable$Creator<com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo> r10 = com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo.CREATOR
                java.lang.Object r10 = r10.createFromParcel(r11)
                com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo r10 = (com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo) r10
                r3 = r10
                goto L_0x0028
            L_0x0027:
                r3 = r13
            L_0x0028:
                java.lang.String r4 = r11.readString()
                int r5 = r11.readInt()
                int r10 = r11.readInt()
                if (r10 == 0) goto L_0x003f
                android.os.Parcelable$Creator r10 = android.content.Intent.CREATOR
                java.lang.Object r10 = r10.createFromParcel(r11)
                r13 = r10
                android.content.Intent r13 = (android.content.Intent) r13
            L_0x003f:
                r6 = r13
                android.os.IBinder r10 = r11.readStrongBinder()
                com.xiaomi.router.miio.miioplugin.IMessageCallback r7 = com.xiaomi.router.miio.miioplugin.IMessageCallback.Stub.asInterface(r10)
                int r10 = r11.readInt()
                if (r10 == 0) goto L_0x0050
                r8 = 1
                goto L_0x0052
            L_0x0050:
                r10 = 0
                r8 = 0
            L_0x0052:
                r2 = r9
                r2.sendMessage(r3, r4, r5, r6, r7, r8)
                r12.writeNoException()
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.api.IXmpluginService.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements IXmpluginService {
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

            public void sendMessage(PluginPackageInfo pluginPackageInfo, String str, int i, Intent intent, IMessageCallback iMessageCallback, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (pluginPackageInfo != null) {
                        obtain.writeInt(1);
                        pluginPackageInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iMessageCallback != null ? iMessageCallback.asBinder() : null);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
