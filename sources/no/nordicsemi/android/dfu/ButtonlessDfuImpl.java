package no.nordicsemi.android.dfu;

import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Intent;
import no.nordicsemi.android.dfu.internal.exception.UnknownResponseException;

abstract class ButtonlessDfuImpl extends BaseButtonlessDfuImpl {
    private static final int DFU_STATUS_SUCCESS = 1;
    private static final byte[] OP_CODE_ENTER_BOOTLOADER = {1};
    private static final int OP_CODE_ENTER_BOOTLOADER_KEY = 1;
    private static final int OP_CODE_RESPONSE_CODE_KEY = 32;

    /* access modifiers changed from: protected */
    public abstract BluetoothGattCharacteristic getButtonlessDfuCharacteristic();

    /* access modifiers changed from: protected */
    public abstract int getResponseType();

    /* access modifiers changed from: protected */
    public abstract boolean shouldScanForBootloader();

    ButtonlessDfuImpl(Intent intent, DfuBaseService dfuBaseService) {
        super(intent, dfuBaseService);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:9|10) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r2 = r10.mReceivedData;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0073 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void performDfu(android.content.Intent r11) throws no.nordicsemi.android.dfu.internal.exception.DfuException, no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException, no.nordicsemi.android.dfu.internal.exception.UploadAbortedException {
        /*
            r10 = this;
            no.nordicsemi.android.dfu.DfuProgressInfo r0 = r10.mProgressInfo
            r1 = -2
            r0.setProgress(r1)
            no.nordicsemi.android.dfu.DfuBaseService r0 = r10.mService
            r1 = 1000(0x3e8, float:1.401E-42)
            r0.waitFor(r1)
            android.bluetooth.BluetoothGatt r0 = r10.mGatt
            no.nordicsemi.android.dfu.DfuBaseService r2 = r10.mService
            java.lang.String r3 = "Application with buttonless update found"
            r4 = 15
            r2.sendLogBroadcast(r4, r3)
            no.nordicsemi.android.dfu.DfuBaseService r2 = r10.mService
            java.lang.String r3 = "Jumping to the DFU Bootloader..."
            r4 = 1
            r2.sendLogBroadcast(r4, r3)
            android.bluetooth.BluetoothGattCharacteristic r2 = r10.getButtonlessDfuCharacteristic()
            int r3 = r10.getResponseType()
            int r5 = r10.getResponseType()
            r10.enableCCCD(r2, r5)
            no.nordicsemi.android.dfu.DfuBaseService r5 = r10.mService
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r7 = 2
            if (r3 != r7) goto L_0x003c
            java.lang.String r3 = "Indications"
            goto L_0x003e
        L_0x003c:
            java.lang.String r3 = "Notifications"
        L_0x003e:
            r6.append(r3)
            java.lang.String r3 = " enabled"
            r6.append(r3)
            java.lang.String r3 = r6.toString()
            r6 = 10
            r5.sendLogBroadcast(r6, r3)
            no.nordicsemi.android.dfu.DfuBaseService r3 = r10.mService
            r3.waitFor(r1)
            r1 = 0
            r3 = 20
            no.nordicsemi.android.dfu.DfuProgressInfo r5 = r10.mProgressInfo     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            r7 = -3
            r5.setProgress(r7)     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            java.lang.String r5 = "Sending Enter Bootloader (Op Code = 1)"
            r10.logi(r5)     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            byte[] r5 = OP_CODE_ENTER_BOOTLOADER     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            r10.writeOpCode(r2, r5, r4)     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            no.nordicsemi.android.dfu.DfuBaseService r2 = r10.mService     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            java.lang.String r5 = "Enter bootloader sent (Op Code = 1)"
            r2.sendLogBroadcast(r6, r5)     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            byte[] r2 = r10.readNotificationResponse()     // Catch:{ DeviceDisconnectedException -> 0x0073 }
            goto L_0x0075
        L_0x0073:
            byte[] r2 = r10.mReceivedData     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
        L_0x0075:
            if (r2 == 0) goto L_0x00d3
            int r5 = r10.getStatusCode(r2, r4)     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            r7.<init>()     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            java.lang.String r8 = "Response received (Op Code = "
            r7.append(r8)     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            byte r8 = r2[r4]     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            r7.append(r8)     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            java.lang.String r8 = ", Status = "
            r7.append(r8)     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            r7.append(r5)     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            java.lang.String r8 = ")"
            r7.append(r8)     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            java.lang.String r7 = r7.toString()     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            r10.logi(r7)     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            no.nordicsemi.android.dfu.DfuBaseService r7 = r10.mService     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            r8.<init>()     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            java.lang.String r9 = "Response received (Op Code = "
            r8.append(r9)     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            byte r2 = r2[r4]     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            r8.append(r2)     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            java.lang.String r2 = ", Status = "
            r8.append(r2)     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            r8.append(r5)     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            java.lang.String r2 = ")"
            r8.append(r2)     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            java.lang.String r2 = r8.toString()     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            r7.sendLogBroadcast(r6, r2)     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            if (r5 != r4) goto L_0x00cb
            no.nordicsemi.android.dfu.DfuBaseService r2 = r10.mService     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            r2.waitUntilDisconnected()     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            goto L_0x00d8
        L_0x00cb:
            no.nordicsemi.android.dfu.internal.exception.RemoteDfuException r11 = new no.nordicsemi.android.dfu.internal.exception.RemoteDfuException     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            java.lang.String r2 = "Device returned error after sending Enter Bootloader"
            r11.<init>(r2, r5)     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            throw r11     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
        L_0x00d3:
            java.lang.String r2 = "Device disconnected before receiving notification"
            r10.logi(r2)     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
        L_0x00d8:
            no.nordicsemi.android.dfu.DfuBaseService r2 = r10.mService     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            r5 = 5
            java.lang.String r6 = "Disconnected by the remote device"
            r2.sendLogBroadcast(r5, r6)     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            boolean r2 = r10.shouldScanForBootloader()     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            r10.finalize(r11, r1, r2)     // Catch:{ UnknownResponseException -> 0x0113, RemoteDfuException -> 0x00e8 }
            goto L_0x012b
        L_0x00e8:
            r11 = move-exception
            int r2 = r11.getErrorNumber()
            r2 = r2 | 2048(0x800, float:2.87E-42)
            java.lang.String r11 = r11.getMessage()
            r10.loge(r11)
            no.nordicsemi.android.dfu.DfuBaseService r11 = r10.mService
            java.util.Locale r5 = java.util.Locale.US
            java.lang.String r6 = "Remote DFU error: %s"
            java.lang.Object[] r4 = new java.lang.Object[r4]
            java.lang.String r7 = no.nordicsemi.android.error.SecureDfuError.c(r2)
            r4[r1] = r7
            java.lang.String r1 = java.lang.String.format(r5, r6, r4)
            r11.sendLogBroadcast(r3, r1)
            no.nordicsemi.android.dfu.DfuBaseService r11 = r10.mService
            r1 = r2 | 8192(0x2000, float:1.14794E-41)
            r11.terminateConnection(r0, r1)
            goto L_0x012b
        L_0x0113:
            r11 = move-exception
            java.lang.String r1 = r11.getMessage()
            r10.loge(r1)
            no.nordicsemi.android.dfu.DfuBaseService r1 = r10.mService
            java.lang.String r11 = r11.getMessage()
            r1.sendLogBroadcast(r3, r11)
            no.nordicsemi.android.dfu.DfuBaseService r11 = r10.mService
            r1 = 4104(0x1008, float:5.751E-42)
            r11.terminateConnection(r0, r1)
        L_0x012b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.dfu.ButtonlessDfuImpl.performDfu(android.content.Intent):void");
    }

    private int getStatusCode(byte[] bArr, int i) throws UnknownResponseException {
        if (bArr != null && bArr.length >= 3 && bArr[0] == 32 && bArr[1] == i && (bArr[2] == 1 || bArr[2] == 2 || bArr[2] == 4)) {
            return bArr[2];
        }
        throw new UnknownResponseException("Invalid response received", bArr, 32, i);
    }
}
