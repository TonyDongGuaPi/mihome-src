package com.tsmclient.smartcard.terminal;

import android.content.Context;
import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.exception.NfcEeIOException;
import com.tsmclient.smartcard.terminal.external.IChannel;
import com.tsmclient.smartcard.terminal.response.ScResponse;
import java.io.IOException;

public class PeripheralTerminal extends NfcEETerminal {
    private static final String CHANNEL_SIMPLE_NAME = ".mitsmsdk.ChannelImpl";
    private IChannel mChannelImpl;

    public PeripheralTerminal(Context context, String str) {
        super(context);
        this.mTerminalCategory = str;
        this.mTerminalType = TerminalType.PERIPHERAL;
    }

    /* access modifiers changed from: protected */
    public void internalConnect() throws IOException, InterruptedException {
        if (this.mChannelImpl != null) {
            throw new NfcEeIOException("nfc ee is open, close it first", 3);
        } else if (this.mContext != null) {
            this.mChannelImpl = createChannel();
            if (this.mChannelImpl != null) {
                try {
                    this.mChannelImpl.open();
                } catch (IOException unused) {
                    throw new NfcEeIOException("nfc was dead or nfc ee occurred an unknown error", 4);
                }
            } else {
                throw new NfcEeIOException("nfc is unavailable on this device", 1);
            }
        } else {
            throw new IOException("context is null!");
        }
    }

    /* access modifiers changed from: protected */
    public void internalClose() {
        if (this.mChannelImpl != null) {
            try {
                this.mChannelImpl.close();
            } catch (IOException unused) {
            } catch (InterruptedException unused2) {
                Thread.currentThread().interrupt();
            }
            this.mChannelImpl = null;
        }
    }

    /* access modifiers changed from: protected */
    public synchronized byte[] internalTransmit(byte[] bArr) throws IOException, InterruptedException {
        if (isInterruptible()) {
            Thread.sleep(1);
        }
        return this.mChannelImpl.transceive(bArr);
    }

    public String getSignedSpiPK() throws IOException, InterruptedException {
        throw new RuntimeException("getSignedSpiPK is not supported.");
    }

    public void checkNfcEEStatus() throws IOException, InterruptedException {
        try {
            open();
            if (ByteArray.equals(transmit(APDUConstants.MC_OPEN).getStatus(), ScResponse.STATUS_SE_RESTRICT)) {
                throw new NfcEeIOException("Se has been restricted", 5);
            }
        } finally {
            close();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.tsmclient.smartcard.terminal.external.IChannel createChannel() throws java.io.IOException {
        /*
            r6 = this;
            java.lang.String r0 = r6.mTerminalCategory
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            r1 = 0
            if (r0 == 0) goto L_0x0011
            java.lang.String r0 = "NfcEETerminal"
            java.lang.String r2 = "terminal category is null"
            android.util.Log.d(r0, r2)
            return r1
        L_0x0011:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = r6.mTerminalCategory
            r0.append(r2)
            java.lang.String r2 = ".mitsmsdk.ChannelImpl"
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ Exception -> 0x0045 }
            if (r0 == 0) goto L_0x004d
            r2 = 1
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch:{ Exception -> 0x0045 }
            java.lang.Class<android.content.Context> r4 = android.content.Context.class
            r5 = 0
            r3[r5] = r4     // Catch:{ Exception -> 0x0045 }
            java.lang.reflect.Constructor r0 = r0.getConstructor(r3)     // Catch:{ Exception -> 0x0045 }
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Exception -> 0x0045 }
            android.content.Context r3 = r6.mContext     // Catch:{ Exception -> 0x0045 }
            android.content.Context r3 = r3.getApplicationContext()     // Catch:{ Exception -> 0x0045 }
            r2[r5] = r3     // Catch:{ Exception -> 0x0045 }
            java.lang.Object r0 = r0.newInstance(r2)     // Catch:{ Exception -> 0x0045 }
            goto L_0x004e
        L_0x0045:
            r0 = move-exception
            java.lang.String r2 = "NfcEETerminal"
            java.lang.String r3 = "createChannel failed"
            android.util.Log.e(r2, r3, r0)
        L_0x004d:
            r0 = r1
        L_0x004e:
            boolean r2 = r0 instanceof com.tsmclient.smartcard.terminal.external.IChannel
            if (r2 == 0) goto L_0x0055
            r1 = r0
            com.tsmclient.smartcard.terminal.external.IChannel r1 = (com.tsmclient.smartcard.terminal.external.IChannel) r1
        L_0x0055:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tsmclient.smartcard.terminal.PeripheralTerminal.createChannel():com.tsmclient.smartcard.terminal.external.IChannel");
    }
}
