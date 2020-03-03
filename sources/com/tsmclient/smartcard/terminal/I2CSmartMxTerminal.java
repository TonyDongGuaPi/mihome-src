package com.tsmclient.smartcard.terminal;

import android.content.Context;
import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.exception.NfcEeIOException;
import com.tsmclient.smartcard.terminal.response.ScResponse;
import java.io.IOException;
import java.util.Map;

public class I2CSmartMxTerminal extends SPISmartMxTerminal {
    public I2CSmartMxTerminal(Context context) {
        super(context);
        this.mTerminalType = TerminalType.I2C;
    }

    public String getSignedSpiPK() throws IOException, InterruptedException {
        throw new RuntimeException("getSignedSpiPK is not supported.");
    }

    public void checkNfcEEStatus() throws IOException, InterruptedException {
        try {
            open();
            if (ByteArray.equals(transmit(APDUConstants.MC_OPEN).getStatus(), ScResponse.STATUS_SE_RESTRICT)) {
                throw new NfcEeIOException("nfc ee has been restricted", 5);
            }
        } finally {
            close();
        }
    }

    public Map<String, ByteArray> getCardActivationState(String str) throws InterruptedException {
        throw new RuntimeException("getCardActivationState is not supported.");
    }

    /* access modifiers changed from: protected */
    public synchronized byte[] internalTransmit(byte[] bArr) throws IOException, InterruptedException {
        if (this.mAdapterExtras != null) {
            if (isInterruptible()) {
                Thread.sleep(1);
            }
        } else {
            throw new NfcEeIOException("nfc ee is not open", 2);
        }
        return this.mAdapterExtras.transciveThroughI2C(bArr);
    }
}
