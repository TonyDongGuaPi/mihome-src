package com.tsmclient.smartcard.terminal;

import android.content.Context;
import android.nfc.NfcAdapter;
import android.text.TextUtils;
import com.android.nfc_extras.EeIOException;
import com.android.nfc_extras.NfcAdapterExtras;
import com.android.nfc_extras.NfcExecutionEnvironment;
import com.tsmclient.smartcard.exception.NfcEeIOException;
import java.io.IOException;

public class SPISmartMxTerminal extends NfcEETerminal {
    protected NfcAdapterExtras mAdapterExtras;
    protected NfcExecutionEnvironment mNfcEe;

    public SPISmartMxTerminal(Context context) {
        super(context);
        this.mTerminalType = TerminalType.SPI;
    }

    public String getSignedSpiPK() throws IOException, InterruptedException {
        try {
            open();
            return this.mAdapterExtras.getSpiSignedPK();
        } finally {
            close();
        }
    }

    public void checkNfcEEStatus() throws IOException, InterruptedException {
        throw new RuntimeException("checkNfcEEStatus is not supported");
    }

    /* access modifiers changed from: protected */
    public synchronized void internalClose() {
        if (this.mNfcEe != null) {
            try {
                this.mNfcEe.close();
            } catch (IOException unused) {
            }
            this.mNfcEe = null;
            this.mAdapterExtras = null;
        }
    }

    /* access modifiers changed from: protected */
    public synchronized void internalConnect() throws IOException {
        if (this.mNfcEe != null) {
            throw new NfcEeIOException("nfc ee is open, close it first", 3);
        } else if (this.mContext != null) {
            NfcAdapter defaultAdapter = NfcAdapter.getDefaultAdapter(this.mContext.getApplicationContext());
            if (defaultAdapter == null) {
                throw new NfcEeIOException("nfc is unavailable on this device", 1);
            } else if (defaultAdapter.isEnabled()) {
                this.mAdapterExtras = NfcAdapterExtras.get(defaultAdapter);
                this.mNfcEe = this.mAdapterExtras.getEmbeddedExecutionEnvironment();
                try {
                    this.mNfcEe.open();
                } catch (EeIOException e) {
                    if (!TextUtils.equals(e.getMessage(), "SERESTRICTED")) {
                        throw new NfcEeIOException("nfc was dead or nfc ee occurred an unknown error", 4);
                    }
                }
            } else {
                throw new NfcEeIOException("nfc is disabled", 1);
            }
        } else {
            throw new IOException("context is null!");
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:11|12|13) */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        throw new com.tsmclient.smartcard.exception.NfcEeIOException("nfc was dead or nfc ee occurred an unknown error", 4);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0018 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized byte[] internalTransmit(byte[] r3) throws java.io.IOException, java.lang.InterruptedException {
        /*
            r2 = this;
            monitor-enter(r2)
            com.android.nfc_extras.NfcExecutionEnvironment r0 = r2.mNfcEe     // Catch:{ all -> 0x002a }
            if (r0 == 0) goto L_0x0021
            boolean r0 = r2.isInterruptible()     // Catch:{ all -> 0x002a }
            if (r0 == 0) goto L_0x0010
            r0 = 1
            java.lang.Thread.sleep(r0)     // Catch:{ all -> 0x002a }
        L_0x0010:
            com.android.nfc_extras.NfcExecutionEnvironment r0 = r2.mNfcEe     // Catch:{ EeIOException -> 0x0018 }
            byte[] r3 = r0.transceive(r3)     // Catch:{ EeIOException -> 0x0018 }
            monitor-exit(r2)
            return r3
        L_0x0018:
            com.tsmclient.smartcard.exception.NfcEeIOException r3 = new com.tsmclient.smartcard.exception.NfcEeIOException     // Catch:{ all -> 0x002a }
            java.lang.String r0 = "nfc was dead or nfc ee occurred an unknown error"
            r1 = 4
            r3.<init>(r0, r1)     // Catch:{ all -> 0x002a }
            throw r3     // Catch:{ all -> 0x002a }
        L_0x0021:
            com.tsmclient.smartcard.exception.NfcEeIOException r3 = new com.tsmclient.smartcard.exception.NfcEeIOException     // Catch:{ all -> 0x002a }
            java.lang.String r0 = "nfc ee is not open"
            r1 = 2
            r3.<init>(r0, r1)     // Catch:{ all -> 0x002a }
            throw r3     // Catch:{ all -> 0x002a }
        L_0x002a:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tsmclient.smartcard.terminal.SPISmartMxTerminal.internalTransmit(byte[]):byte[]");
    }
}
