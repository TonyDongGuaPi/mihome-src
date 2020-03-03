package no.nordicsemi.android.dfu;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Intent;
import java.util.UUID;
import no.nordicsemi.android.dfu.BaseCustomDfuImpl;
import no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException;
import no.nordicsemi.android.dfu.internal.exception.DfuException;
import no.nordicsemi.android.dfu.internal.exception.UnknownResponseException;
import no.nordicsemi.android.dfu.internal.exception.UploadAbortedException;

class LegacyDfuImpl extends BaseCustomDfuImpl {
    protected static final UUID DEFAULT_DFU_CONTROL_POINT_UUID = new UUID(23300500811742L, 1523193452336828707L);
    protected static final UUID DEFAULT_DFU_PACKET_UUID = new UUID(23304795779038L, 1523193452336828707L);
    protected static final UUID DEFAULT_DFU_SERVICE_UUID = new UUID(23296205844446L, 1523193452336828707L);
    protected static final UUID DEFAULT_DFU_VERSION_UUID = new UUID(23313385713630L, 1523193452336828707L);
    protected static UUID DFU_CONTROL_POINT_UUID = DEFAULT_DFU_CONTROL_POINT_UUID;
    protected static UUID DFU_PACKET_UUID = DEFAULT_DFU_PACKET_UUID;
    protected static UUID DFU_SERVICE_UUID = DEFAULT_DFU_SERVICE_UUID;
    private static final int DFU_STATUS_SUCCESS = 1;
    protected static UUID DFU_VERSION_UUID = DEFAULT_DFU_VERSION_UUID;
    private static final byte[] OP_CODE_ACTIVATE_AND_RESET = {5};
    private static final int OP_CODE_ACTIVATE_AND_RESET_KEY = 5;
    private static final byte[] OP_CODE_INIT_DFU_PARAMS = {2};
    private static final byte[] OP_CODE_INIT_DFU_PARAMS_COMPLETE = {2, 1};
    private static final int OP_CODE_INIT_DFU_PARAMS_KEY = 2;
    private static final byte[] OP_CODE_INIT_DFU_PARAMS_START = {2, 0};
    private static final int OP_CODE_PACKET_RECEIPT_NOTIF_KEY = 17;
    private static final byte[] OP_CODE_PACKET_RECEIPT_NOTIF_REQ = {8, 0, 0};
    private static final int OP_CODE_PACKET_RECEIPT_NOTIF_REQ_KEY = 8;
    private static final byte[] OP_CODE_RECEIVE_FIRMWARE_IMAGE = {3};
    private static final int OP_CODE_RECEIVE_FIRMWARE_IMAGE_KEY = 3;
    private static final byte[] OP_CODE_RESET = {6};
    private static final int OP_CODE_RESET_KEY = 6;
    private static final int OP_CODE_RESPONSE_CODE_KEY = 16;
    private static final byte[] OP_CODE_START_DFU = {1, 0};
    private static final int OP_CODE_START_DFU_KEY = 1;
    private static final byte[] OP_CODE_START_DFU_V1 = {1};
    private static final byte[] OP_CODE_VALIDATE = {4};
    private static final int OP_CODE_VALIDATE_KEY = 4;
    private final LegacyBluetoothCallback mBluetoothCallback = new LegacyBluetoothCallback();
    private BluetoothGattCharacteristic mControlPointCharacteristic;
    /* access modifiers changed from: private */
    public boolean mImageSizeInProgress;
    private BluetoothGattCharacteristic mPacketCharacteristic;

    protected class LegacyBluetoothCallback extends BaseCustomDfuImpl.BaseCustomBluetoothCallback {
        protected LegacyBluetoothCallback() {
            super();
        }

        /* access modifiers changed from: protected */
        public void onPacketCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            if (LegacyDfuImpl.this.mImageSizeInProgress) {
                DfuBaseService dfuBaseService = LegacyDfuImpl.this.mService;
                dfuBaseService.sendLogBroadcast(5, "Data written to " + bluetoothGattCharacteristic.getUuid() + ", value (0x): " + parse(bluetoothGattCharacteristic));
                boolean unused = LegacyDfuImpl.this.mImageSizeInProgress = false;
            }
        }

        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            if (bluetoothGattCharacteristic.getIntValue(17, 0).intValue() == 17) {
                LegacyDfuImpl.this.mProgressInfo.setBytesReceived(bluetoothGattCharacteristic.getIntValue(20, 1).intValue());
                handlePacketReceiptNotification(bluetoothGatt, bluetoothGattCharacteristic);
            } else if (!LegacyDfuImpl.this.mRemoteErrorOccurred) {
                if (bluetoothGattCharacteristic.getIntValue(17, 2).intValue() != 1) {
                    LegacyDfuImpl.this.mRemoteErrorOccurred = true;
                }
                handleNotification(bluetoothGatt, bluetoothGattCharacteristic);
            }
            LegacyDfuImpl.this.notifyLock();
        }
    }

    LegacyDfuImpl(Intent intent, DfuBaseService dfuBaseService) {
        super(intent, dfuBaseService);
    }

    public boolean isClientCompatible(Intent intent, BluetoothGatt bluetoothGatt) {
        BluetoothGattCharacteristic characteristic;
        BluetoothGattService service = bluetoothGatt.getService(DFU_SERVICE_UUID);
        if (service == null || (characteristic = service.getCharacteristic(DFU_CONTROL_POINT_UUID)) == null || characteristic.getDescriptor(CLIENT_CHARACTERISTIC_CONFIG) == null) {
            return false;
        }
        this.mControlPointCharacteristic = characteristic;
        this.mPacketCharacteristic = service.getCharacteristic(DFU_PACKET_UUID);
        if (this.mPacketCharacteristic != null) {
            return true;
        }
        return false;
    }

    public BaseCustomDfuImpl.BaseCustomBluetoothCallback getGattCallback() {
        return this.mBluetoothCallback;
    }

    /* access modifiers changed from: protected */
    public UUID getControlPointCharacteristicUUID() {
        return DFU_CONTROL_POINT_UUID;
    }

    /* access modifiers changed from: protected */
    public UUID getPacketCharacteristicUUID() {
        return DFU_PACKET_UUID;
    }

    /* access modifiers changed from: protected */
    public UUID getDfuServiceUUID() {
        return DFU_SERVICE_UUID;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:108:0x05fe, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x05ff, code lost:
        r2 = r0;
        loge("Disconnected while sending data");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0605, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x0610, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0611, code lost:
        r2 = r0;
        r4 = r2.getErrorNumber() | 256;
        loge(r2.getMessage() + ": " + no.nordicsemi.android.error.LegacyDfuError.a(r4));
        r1.mService.sendLogBroadcast(20, java.lang.String.format(java.util.Locale.US, "Remote DFU error: %s", new java.lang.Object[]{no.nordicsemi.android.error.LegacyDfuError.a(r4)}));
        logi("Sending Reset command (Op Code = 6)");
        writeOpCode(r1.mControlPointCharacteristic, OP_CODE_RESET);
        r1.mService.sendLogBroadcast(10, "Reset request sent");
        r1.mService.terminateConnection(r3, r4 | 8192);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x066a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x066b, code lost:
        r2 = r0;
        loge(r2.getMessage());
        r1.mService.sendLogBroadcast(20, r2.getMessage());
        logi("Sending Reset command (Op Code = 6)");
        writeOpCode(r1.mControlPointCharacteristic, OP_CODE_RESET);
        r1.mService.sendLogBroadcast(10, "Reset request sent");
        r1.mService.terminateConnection(r3, 4104);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x0699, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x069a, code lost:
        r2 = r0;
        logi("Sending Reset command (Op Code = 6)");
        r1.mAborted = false;
        writeOpCode(r1.mControlPointCharacteristic, OP_CODE_RESET);
        r1.mService.sendLogBroadcast(10, "Reset request sent");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x06b1, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x05f6 A[Catch:{ DeviceDisconnectedException -> 0x05fe, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }] */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x066a A[ExcHandler: UnknownResponseException (r0v1 'e' no.nordicsemi.android.dfu.internal.exception.UnknownResponseException A[CUSTOM_DECLARE]), Splitter:B:7:0x0057] */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x0699 A[ExcHandler: UploadAbortedException (r0v0 'e' no.nordicsemi.android.dfu.internal.exception.UploadAbortedException A[CUSTOM_DECLARE]), Splitter:B:7:0x0057] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0431 A[Catch:{ DeviceDisconnectedException -> 0x05fe, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0506 A[Catch:{ DeviceDisconnectedException -> 0x05fe, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void performDfu(android.content.Intent r19) throws no.nordicsemi.android.dfu.internal.exception.DfuException, no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException, no.nordicsemi.android.dfu.internal.exception.UploadAbortedException {
        /*
            r18 = this;
            r1 = r18
            r2 = r19
            java.lang.String r3 = "Legacy DFU bootloader found"
            r1.logw(r3)
            no.nordicsemi.android.dfu.DfuProgressInfo r3 = r1.mProgressInfo
            r4 = -2
            r3.setProgress(r4)
            no.nordicsemi.android.dfu.DfuBaseService r3 = r1.mService
            r4 = 1000(0x3e8, float:1.401E-42)
            r3.waitFor(r4)
            android.bluetooth.BluetoothGatt r3 = r1.mGatt
            java.util.UUID r5 = DFU_SERVICE_UUID
            android.bluetooth.BluetoothGattService r5 = r3.getService(r5)
            java.util.UUID r6 = DFU_VERSION_UUID
            android.bluetooth.BluetoothGattCharacteristic r5 = r5.getCharacteristic(r6)
            int r5 = r1.readVersion(r5)
            r6 = 5
            r7 = 20
            if (r5 < r6) goto L_0x0054
            java.io.InputStream r8 = r1.mInitPacketStream
            if (r8 != 0) goto L_0x0054
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "Init packet not set for the DFU Bootloader version "
            r2.append(r4)
            r2.append(r5)
            java.lang.String r2 = r2.toString()
            r1.logw(r2)
            no.nordicsemi.android.dfu.DfuBaseService r2 = r1.mService
            java.lang.String r4 = "The Init packet is required by this version DFU Bootloader"
            r2.sendLogBroadcast(r7, r4)
            no.nordicsemi.android.dfu.DfuBaseService r2 = r1.mService
            r4 = 4107(0x100b, float:5.755E-42)
            r2.terminateConnection(r3, r4)
            return
        L_0x0054:
            r9 = 1
            r10 = 10
            android.bluetooth.BluetoothGattCharacteristic r11 = r1.mControlPointCharacteristic     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r1.enableCCCD(r11, r9)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            no.nordicsemi.android.dfu.DfuBaseService r11 = r1.mService     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r12 = "Notifications enabled"
            r11.sendLogBroadcast(r10, r12)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            no.nordicsemi.android.dfu.DfuBaseService r11 = r1.mService     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r11.waitFor(r4)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            int r4 = r1.mFileType     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r11 = r4 & 1
            if (r11 <= 0) goto L_0x0071
            int r11 = r1.mImageSizeInBytes     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            goto L_0x0072
        L_0x0071:
            r11 = 0
        L_0x0072:
            r12 = r4 & 2
            if (r12 <= 0) goto L_0x0079
            int r12 = r1.mImageSizeInBytes     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            goto L_0x007a
        L_0x0079:
            r12 = 0
        L_0x007a:
            r13 = r4 & 4
            if (r13 <= 0) goto L_0x0081
            int r14 = r1.mImageSizeInBytes     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            goto L_0x0082
        L_0x0081:
            r14 = 0
        L_0x0082:
            java.io.InputStream r15 = r1.mFirmwareStream     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            boolean r15 = r15 instanceof no.nordicsemi.android.dfu.internal.ArchiveInputStream     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            if (r15 == 0) goto L_0x00cb
            java.io.InputStream r11 = r1.mFirmwareStream     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            no.nordicsemi.android.dfu.internal.ArchiveInputStream r11 = (no.nordicsemi.android.dfu.internal.ArchiveInputStream) r11     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            boolean r12 = r11.isSecureDfuRequired()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            if (r12 == 0) goto L_0x00b9
            java.lang.String r2 = "Secure DFU is required to upload selected firmware"
            r1.loge(r2)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            no.nordicsemi.android.dfu.DfuBaseService r2 = r1.mService     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r4 = "The device does not support given firmware."
            r2.sendLogBroadcast(r7, r4)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r2 = "Sending Reset command (Op Code = 6)"
            r1.logi(r2)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            android.bluetooth.BluetoothGattCharacteristic r2 = r1.mControlPointCharacteristic     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            byte[] r4 = OP_CODE_RESET     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r1.writeOpCode(r2, r4)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            no.nordicsemi.android.dfu.DfuBaseService r2 = r1.mService     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r4 = "Reset request sent"
            r2.sendLogBroadcast(r10, r4)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            no.nordicsemi.android.dfu.DfuBaseService r2 = r1.mService     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r4 = 4099(0x1003, float:5.744E-42)
            r2.terminateConnection(r3, r4)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            return
        L_0x00b9:
            int r12 = r11.softDeviceImageSize()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            int r14 = r11.bootloaderImageSize()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            int r11 = r11.applicationImageSize()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r17 = r12
            r12 = r11
            r11 = r17
            goto L_0x00d0
        L_0x00cb:
            r17 = r14
            r14 = r12
            r12 = r17
        L_0x00d0:
            byte[] r16 = OP_CODE_START_DFU     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            byte r15 = (byte) r4     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r16[r9] = r15     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r15.<init>()     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r8 = "Sending Start DFU command (Op Code = 1, Upload Mode = "
            r15.append(r8)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r15.append(r4)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r8 = ")"
            r15.append(r8)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r8 = r15.toString()     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r1.logi(r8)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            android.bluetooth.BluetoothGattCharacteristic r8 = r1.mControlPointCharacteristic     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            byte[] r15 = OP_CODE_START_DFU     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r1.writeOpCode(r8, r15)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            no.nordicsemi.android.dfu.DfuBaseService r8 = r1.mService     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r15.<init>()     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r7 = "DFU Start sent (Op Code = 1, Upload Mode = "
            r15.append(r7)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r15.append(r4)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r7 = ")"
            r15.append(r7)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r7 = r15.toString()     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r8.sendLogBroadcast(r10, r7)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r7.<init>()     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r8 = "Sending image size array to DFU Packet ("
            r7.append(r8)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r7.append(r11)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r8 = "b, "
            r7.append(r8)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r7.append(r14)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r8 = "b, "
            r7.append(r8)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r7.append(r12)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r8 = "b)"
            r7.append(r8)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r7 = r7.toString()     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r1.logi(r7)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            android.bluetooth.BluetoothGattCharacteristic r7 = r1.mPacketCharacteristic     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r1.writeImageSize(r7, r11, r14, r12)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            no.nordicsemi.android.dfu.DfuBaseService r7 = r1.mService     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r8.<init>()     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r15 = "Firmware image size sent ("
            r8.append(r15)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r8.append(r11)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r15 = "b, "
            r8.append(r15)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r8.append(r14)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r15 = "b, "
            r8.append(r15)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r8.append(r12)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r12 = "b)"
            r8.append(r12)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r8 = r8.toString()     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r7.sendLogBroadcast(r10, r8)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            byte[] r7 = r18.readNotificationResponse()     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            int r8 = r1.getStatusCode(r7, r9)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            no.nordicsemi.android.dfu.DfuBaseService r12 = r1.mService     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r15.<init>()     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r6 = "Response received (Op Code = "
            r15.append(r6)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            byte r6 = r7[r9]     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r15.append(r6)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r6 = " Status = "
            r15.append(r6)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r15.append(r8)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r6 = ")"
            r15.append(r6)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r6 = r15.toString()     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r12.sendLogBroadcast(r10, r6)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r6 = 2
            if (r8 != r6) goto L_0x019d
            r1.resetAndRestart(r3, r2)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            return
        L_0x019d:
            if (r8 != r9) goto L_0x01a1
            goto L_0x02b3
        L_0x01a1:
            no.nordicsemi.android.dfu.internal.exception.RemoteDfuException r6 = new no.nordicsemi.android.dfu.internal.exception.RemoteDfuException     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r7 = "Starting DFU failed"
            r6.<init>(r7, r8)     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            throw r6     // Catch:{ RemoteDfuException -> 0x01a9, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
        L_0x01a9:
            r0 = move-exception
            r6 = r0
            r7 = 15
            int r8 = r6.getErrorNumber()     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r12 = 3
            if (r8 != r12) goto L_0x02bf
            if (r13 <= 0) goto L_0x02be
            r8 = r4 & 3
            if (r8 <= 0) goto L_0x02be
            r8 = 0
            r1.mRemoteErrorOccurred = r8     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r6 = "DFU target does not support (SD/BL)+App update"
            r1.logw(r6)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            no.nordicsemi.android.dfu.DfuBaseService r6 = r1.mService     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r8 = "DFU target does not support (SD/BL)+App update"
            r6.sendLogBroadcast(r7, r8)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r4 = r4 & -5
            r1.mFileType = r4     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            byte[] r6 = OP_CODE_START_DFU     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            byte r8 = (byte) r4     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r6[r9] = r8     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            no.nordicsemi.android.dfu.DfuProgressInfo r6 = r1.mProgressInfo     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r8 = 2
            r6.setTotalPart(r8)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.io.InputStream r6 = r1.mFirmwareStream     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            no.nordicsemi.android.dfu.internal.ArchiveInputStream r6 = (no.nordicsemi.android.dfu.internal.ArchiveInputStream) r6     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r6.setContentType(r4)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            no.nordicsemi.android.dfu.DfuBaseService r6 = r1.mService     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r8 = "Sending only SD/BL"
            r6.sendLogBroadcast(r9, r8)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r6.<init>()     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r8 = "Resending Start DFU command (Op Code = 1, Upload Mode = "
            r6.append(r8)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r6.append(r4)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r8 = ")"
            r6.append(r8)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r6 = r6.toString()     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r1.logi(r6)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            android.bluetooth.BluetoothGattCharacteristic r6 = r1.mControlPointCharacteristic     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            byte[] r8 = OP_CODE_START_DFU     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r1.writeOpCode(r6, r8)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            no.nordicsemi.android.dfu.DfuBaseService r6 = r1.mService     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r8.<init>()     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r12 = "DFU Start sent (Op Code = 1, Upload Mode = "
            r8.append(r12)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r8.append(r4)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r12 = ")"
            r8.append(r12)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r8 = r8.toString()     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r6.sendLogBroadcast(r10, r8)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r6.<init>()     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r8 = "Sending image size array to DFU Packet: ["
            r6.append(r8)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r6.append(r11)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r8 = "b, "
            r6.append(r8)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r6.append(r14)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r8 = "b, "
            r6.append(r8)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r8 = 0
            r6.append(r8)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r8 = "b]"
            r6.append(r8)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r6 = r6.toString()     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r1.logi(r6)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            android.bluetooth.BluetoothGattCharacteristic r6 = r1.mPacketCharacteristic     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r8 = 0
            r1.writeImageSize(r6, r11, r14, r8)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            no.nordicsemi.android.dfu.DfuBaseService r6 = r1.mService     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r8.<init>()     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r12 = "Firmware image size sent ["
            r8.append(r12)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r8.append(r11)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r11 = "b, "
            r8.append(r11)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r8.append(r14)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r11 = "b, "
            r8.append(r11)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r11 = 0
            r8.append(r11)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r11 = "b]"
            r8.append(r11)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r8 = r8.toString()     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r6.sendLogBroadcast(r10, r8)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            byte[] r6 = r18.readNotificationResponse()     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            int r8 = r1.getStatusCode(r6, r9)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            no.nordicsemi.android.dfu.DfuBaseService r11 = r1.mService     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r12.<init>()     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r13 = "Response received (Op Code = "
            r12.append(r13)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            byte r6 = r6[r9]     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r12.append(r6)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r6 = " Status = "
            r12.append(r6)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r12.append(r8)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r6 = ")"
            r12.append(r6)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r6 = r12.toString()     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r11.sendLogBroadcast(r10, r6)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            r6 = 2
            if (r8 != r6) goto L_0x02b1
            r1.resetAndRestart(r3, r2)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            return
        L_0x02b1:
            if (r8 != r9) goto L_0x02b6
        L_0x02b3:
            r4 = 1
            goto L_0x036c
        L_0x02b6:
            no.nordicsemi.android.dfu.internal.exception.RemoteDfuException r6 = new no.nordicsemi.android.dfu.internal.exception.RemoteDfuException     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            java.lang.String r11 = "Starting DFU failed"
            r6.<init>(r11, r8)     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
            throw r6     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
        L_0x02be:
            throw r6     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
        L_0x02bf:
            throw r6     // Catch:{ RemoteDfuException -> 0x02c0, UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a }
        L_0x02c0:
            r0 = move-exception
            r6 = r4
            r4 = r0
            int r8 = r4.getErrorNumber()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r11 = 3
            if (r8 != r11) goto L_0x060f
            r8 = 4
            if (r6 != r8) goto L_0x060e
            r6 = 0
            r1.mRemoteErrorOccurred = r6     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r4 = "DFU target does not support DFU v.2"
            r1.logw(r4)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            no.nordicsemi.android.dfu.DfuBaseService r4 = r1.mService     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r6 = "DFU target does not support DFU v.2"
            r4.sendLogBroadcast(r7, r6)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            no.nordicsemi.android.dfu.DfuBaseService r4 = r1.mService     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r6 = "Switching to DFU v.1"
            r4.sendLogBroadcast(r9, r6)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r4 = "Resending Start DFU command (Op Code = 1)"
            r1.logi(r4)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            android.bluetooth.BluetoothGattCharacteristic r4 = r1.mControlPointCharacteristic     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            byte[] r6 = OP_CODE_START_DFU_V1     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r1.writeOpCode(r4, r6)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            no.nordicsemi.android.dfu.DfuBaseService r4 = r1.mService     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r6 = "DFU Start sent (Op Code = 1)"
            r4.sendLogBroadcast(r10, r6)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r4.<init>()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r6 = "Sending application image size to DFU Packet: "
            r4.append(r6)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            int r6 = r1.mImageSizeInBytes     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r4.append(r6)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r6 = " bytes"
            r4.append(r6)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r4 = r4.toString()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r1.logi(r4)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            android.bluetooth.BluetoothGattCharacteristic r4 = r1.mPacketCharacteristic     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            int r6 = r1.mImageSizeInBytes     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r1.writeImageSize(r4, r6)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            no.nordicsemi.android.dfu.DfuBaseService r4 = r1.mService     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r6.<init>()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r7 = "Firmware image size sent ("
            r6.append(r7)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            int r7 = r1.mImageSizeInBytes     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r6.append(r7)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r7 = " bytes)"
            r6.append(r7)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r6 = r6.toString()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r4.sendLogBroadcast(r10, r6)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            byte[] r4 = r18.readNotificationResponse()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            int r6 = r1.getStatusCode(r4, r9)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            no.nordicsemi.android.dfu.DfuBaseService r7 = r1.mService     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r8.<init>()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r11 = "Response received (Op Code = "
            r8.append(r11)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            byte r4 = r4[r9]     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r8.append(r4)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r4 = ", Status = "
            r8.append(r4)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r8.append(r6)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r4 = ")"
            r8.append(r4)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r4 = r8.toString()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r7.sendLogBroadcast(r10, r4)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r4 = 2
            if (r6 != r4) goto L_0x0369
            r1.resetAndRestart(r3, r2)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            return
        L_0x0369:
            if (r6 != r9) goto L_0x0606
            r4 = 0
        L_0x036c:
            java.io.InputStream r6 = r1.mInitPacketStream     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            if (r6 == 0) goto L_0x041f
            no.nordicsemi.android.dfu.DfuBaseService r6 = r1.mService     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r7 = "Writing Initialize DFU Parameters..."
            r6.sendLogBroadcast(r10, r7)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r6 = 0
            if (r4 == 0) goto L_0x03ba
            java.lang.String r7 = "Sending the Initialize DFU Parameters START (Op Code = 2, Value = 0)"
            r1.logi(r7)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            android.bluetooth.BluetoothGattCharacteristic r7 = r1.mControlPointCharacteristic     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            byte[] r8 = OP_CODE_INIT_DFU_PARAMS_START     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r1.writeOpCode(r7, r8)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r7.<init>()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r8 = "Sending "
            r7.append(r8)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            int r8 = r1.mInitPacketSizeInBytes     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r7.append(r8)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r8 = " bytes of init packet"
            r7.append(r8)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r7 = r7.toString()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r1.logi(r7)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            android.bluetooth.BluetoothGattCharacteristic r7 = r1.mPacketCharacteristic     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r1.writeInitData(r7, r6)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r6 = "Sending the Initialize DFU Parameters COMPLETE (Op Code = 2, Value = 1)"
            r1.logi(r6)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            android.bluetooth.BluetoothGattCharacteristic r6 = r1.mControlPointCharacteristic     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            byte[] r7 = OP_CODE_INIT_DFU_PARAMS_COMPLETE     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r1.writeOpCode(r6, r7)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            no.nordicsemi.android.dfu.DfuBaseService r6 = r1.mService     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r7 = "Initialize DFU Parameters completed"
            r6.sendLogBroadcast(r10, r7)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            goto L_0x03e6
        L_0x03ba:
            java.lang.String r7 = "Sending the Initialize DFU Parameters (Op Code = 2)"
            r1.logi(r7)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            android.bluetooth.BluetoothGattCharacteristic r7 = r1.mControlPointCharacteristic     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            byte[] r8 = OP_CODE_INIT_DFU_PARAMS     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r1.writeOpCode(r7, r8)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r7.<init>()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r8 = "Sending "
            r7.append(r8)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            int r8 = r1.mInitPacketSizeInBytes     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r7.append(r8)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r8 = " bytes of init packet"
            r7.append(r8)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r7 = r7.toString()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r1.logi(r7)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            android.bluetooth.BluetoothGattCharacteristic r7 = r1.mPacketCharacteristic     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r1.writeInitData(r7, r6)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
        L_0x03e6:
            byte[] r6 = r18.readNotificationResponse()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r7 = 2
            int r8 = r1.getStatusCode(r6, r7)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            no.nordicsemi.android.dfu.DfuBaseService r7 = r1.mService     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r11.<init>()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r12 = "Response received (Op Code = "
            r11.append(r12)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            byte r6 = r6[r9]     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r11.append(r6)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r6 = ", Status = "
            r11.append(r6)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r11.append(r8)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r6 = ")"
            r11.append(r6)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r6 = r11.toString()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r7.sendLogBroadcast(r10, r6)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            if (r8 != r9) goto L_0x0417
            goto L_0x041f
        L_0x0417:
            no.nordicsemi.android.dfu.internal.exception.RemoteDfuException r2 = new no.nordicsemi.android.dfu.internal.exception.RemoteDfuException     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r4 = "Device returned error after sending init packet"
            r2.<init>(r4, r8)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            throw r2     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
        L_0x041f:
            if (r4 != 0) goto L_0x042d
            int r4 = r1.mPacketsBeforeNotification     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            if (r4 <= 0) goto L_0x042a
            int r4 = r1.mPacketsBeforeNotification     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            if (r4 > r10) goto L_0x042a
            goto L_0x042d
        L_0x042a:
            r4 = 10
            goto L_0x042f
        L_0x042d:
            int r4 = r1.mPacketsBeforeNotification     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
        L_0x042f:
            if (r4 <= 0) goto L_0x0473
            r1.mPacketsBeforeNotification = r4     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r6.<init>()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r7 = "Sending the number of packets before notifications (Op Code = 8, Value = "
            r6.append(r7)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r6.append(r4)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r7 = ")"
            r6.append(r7)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r6 = r6.toString()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r1.logi(r6)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            byte[] r6 = OP_CODE_PACKET_RECEIPT_NOTIF_REQ     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r1.setNumberOfPackets(r6, r4)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            android.bluetooth.BluetoothGattCharacteristic r6 = r1.mControlPointCharacteristic     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            byte[] r7 = OP_CODE_PACKET_RECEIPT_NOTIF_REQ     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r1.writeOpCode(r6, r7)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            no.nordicsemi.android.dfu.DfuBaseService r6 = r1.mService     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r7.<init>()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r8 = "Packet Receipt Notif Req (Op Code = 8) sent (Value = "
            r7.append(r8)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r7.append(r4)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r4 = ")"
            r7.append(r4)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r4 = r7.toString()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r6.sendLogBroadcast(r10, r4)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
        L_0x0473:
            java.lang.String r4 = "Sending Receive Firmware Image request (Op Code = 3)"
            r1.logi(r4)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            android.bluetooth.BluetoothGattCharacteristic r4 = r1.mControlPointCharacteristic     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            byte[] r6 = OP_CODE_RECEIVE_FIRMWARE_IMAGE     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r1.writeOpCode(r4, r6)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            no.nordicsemi.android.dfu.DfuBaseService r4 = r1.mService     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r6 = "Receive Firmware Image request sent"
            r4.sendLogBroadcast(r10, r6)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            long r6 = android.os.SystemClock.elapsedRealtime()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            no.nordicsemi.android.dfu.DfuProgressInfo r4 = r1.mProgressInfo     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r8 = 0
            r4.setBytesSent(r8)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r4 = "Uploading firmware..."
            r1.logi(r4)     // Catch:{ DeviceDisconnectedException -> 0x05fe }
            no.nordicsemi.android.dfu.DfuBaseService r4 = r1.mService     // Catch:{ DeviceDisconnectedException -> 0x05fe }
            java.lang.String r8 = "Uploading firmware..."
            r4.sendLogBroadcast(r10, r8)     // Catch:{ DeviceDisconnectedException -> 0x05fe }
            android.bluetooth.BluetoothGattCharacteristic r4 = r1.mPacketCharacteristic     // Catch:{ DeviceDisconnectedException -> 0x05fe }
            r1.uploadFirmwareImage(r4)     // Catch:{ DeviceDisconnectedException -> 0x05fe }
            long r11 = android.os.SystemClock.elapsedRealtime()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            byte[] r4 = r18.readNotificationResponse()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r8 = 3
            int r8 = r1.getStatusCode(r4, r8)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r13.<init>()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r14 = "Response received (Op Code = "
            r13.append(r14)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r14 = 0
            byte r15 = r4[r14]     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r13.append(r15)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r14 = ", Req Op Code = "
            r13.append(r14)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            byte r14 = r4[r9]     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r13.append(r14)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r14 = ", Status = "
            r13.append(r14)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r14 = 2
            byte r15 = r4[r14]     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r13.append(r15)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r14 = ")"
            r13.append(r14)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r13 = r13.toString()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r1.logi(r13)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            no.nordicsemi.android.dfu.DfuBaseService r13 = r1.mService     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r14.<init>()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r15 = "Response received (Op Code = "
            r14.append(r15)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            byte r4 = r4[r9]     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r14.append(r4)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r4 = ", Status = "
            r14.append(r4)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r14.append(r8)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r4 = ")"
            r14.append(r4)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r4 = r14.toString()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r13.sendLogBroadcast(r10, r4)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            if (r8 != r9) goto L_0x05f6
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r4.<init>()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r8 = "Transfer of "
            r4.append(r8)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            no.nordicsemi.android.dfu.DfuProgressInfo r8 = r1.mProgressInfo     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            int r8 = r8.getBytesSent()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r4.append(r8)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r8 = " bytes has taken "
            r4.append(r8)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r8 = 0
            long r11 = r11 - r6
            r4.append(r11)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r6 = " ms"
            r4.append(r6)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r4 = r4.toString()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r1.logi(r4)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            no.nordicsemi.android.dfu.DfuBaseService r4 = r1.mService     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r6.<init>()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r7 = "Upload completed in "
            r6.append(r7)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r6.append(r11)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r7 = " ms"
            r6.append(r7)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r6 = r6.toString()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r4.sendLogBroadcast(r10, r6)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r4 = "Sending Validate request (Op Code = 4)"
            r1.logi(r4)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            android.bluetooth.BluetoothGattCharacteristic r4 = r1.mControlPointCharacteristic     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            byte[] r6 = OP_CODE_VALIDATE     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r1.writeOpCode(r4, r6)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            no.nordicsemi.android.dfu.DfuBaseService r4 = r1.mService     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r6 = "Validate request sent"
            r4.sendLogBroadcast(r10, r6)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            byte[] r4 = r18.readNotificationResponse()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r6 = 4
            int r6 = r1.getStatusCode(r4, r6)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r7.<init>()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r8 = "Response received (Op Code = "
            r7.append(r8)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r8 = 0
            byte r11 = r4[r8]     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r7.append(r11)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r8 = ", Req Op Code = "
            r7.append(r8)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            byte r8 = r4[r9]     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r7.append(r8)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r8 = ", Status = "
            r7.append(r8)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r8 = 2
            byte r8 = r4[r8]     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r7.append(r8)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r8 = ")"
            r7.append(r8)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r7 = r7.toString()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r1.logi(r7)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            no.nordicsemi.android.dfu.DfuBaseService r7 = r1.mService     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r8.<init>()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r11 = "Response received (Op Code = "
            r8.append(r11)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            byte r4 = r4[r9]     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r8.append(r4)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r4 = ", Status = "
            r8.append(r4)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r8.append(r6)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r4 = ")"
            r8.append(r4)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r4 = r8.toString()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r7.sendLogBroadcast(r10, r4)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            if (r6 != r9) goto L_0x05ee
            no.nordicsemi.android.dfu.DfuProgressInfo r4 = r1.mProgressInfo     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r6 = -5
            r4.setProgress(r6)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r4 = "Sending Activate and Reset request (Op Code = 5)"
            r1.logi(r4)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            android.bluetooth.BluetoothGattCharacteristic r4 = r1.mControlPointCharacteristic     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            byte[] r6 = OP_CODE_ACTIVATE_AND_RESET     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r1.writeOpCode(r4, r6)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            no.nordicsemi.android.dfu.DfuBaseService r4 = r1.mService     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r6 = "Activate and Reset request sent"
            r4.sendLogBroadcast(r10, r6)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            no.nordicsemi.android.dfu.DfuBaseService r4 = r1.mService     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            r4.waitUntilDisconnected()     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            no.nordicsemi.android.dfu.DfuBaseService r4 = r1.mService     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r6 = "Disconnected by the remote device"
            r7 = 5
            r4.sendLogBroadcast(r7, r6)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            if (r5 != r7) goto L_0x05e8
            r4 = 1
            goto L_0x05e9
        L_0x05e8:
            r4 = 0
        L_0x05e9:
            r1.finalize(r2, r4)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            goto L_0x0698
        L_0x05ee:
            no.nordicsemi.android.dfu.internal.exception.RemoteDfuException r2 = new no.nordicsemi.android.dfu.internal.exception.RemoteDfuException     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r4 = "Device returned validation error"
            r2.<init>(r4, r6)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            throw r2     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
        L_0x05f6:
            no.nordicsemi.android.dfu.internal.exception.RemoteDfuException r2 = new no.nordicsemi.android.dfu.internal.exception.RemoteDfuException     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r4 = "Device returned error after sending file"
            r2.<init>(r4, r8)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            throw r2     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
        L_0x05fe:
            r0 = move-exception
            r2 = r0
            java.lang.String r4 = "Disconnected while sending data"
            r1.loge(r4)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            throw r2     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
        L_0x0606:
            no.nordicsemi.android.dfu.internal.exception.RemoteDfuException r2 = new no.nordicsemi.android.dfu.internal.exception.RemoteDfuException     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            java.lang.String r4 = "Starting DFU failed"
            r2.<init>(r4, r6)     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
            throw r2     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
        L_0x060e:
            throw r4     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
        L_0x060f:
            throw r4     // Catch:{ UploadAbortedException -> 0x0699, UnknownResponseException -> 0x066a, RemoteDfuException -> 0x0610 }
        L_0x0610:
            r0 = move-exception
            r2 = r0
            int r4 = r2.getErrorNumber()
            r4 = r4 | 256(0x100, float:3.59E-43)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r2 = r2.getMessage()
            r5.append(r2)
            java.lang.String r2 = ": "
            r5.append(r2)
            java.lang.String r2 = no.nordicsemi.android.error.LegacyDfuError.a(r4)
            r5.append(r2)
            java.lang.String r2 = r5.toString()
            r1.loge(r2)
            no.nordicsemi.android.dfu.DfuBaseService r2 = r1.mService
            java.util.Locale r5 = java.util.Locale.US
            java.lang.String r6 = "Remote DFU error: %s"
            java.lang.Object[] r7 = new java.lang.Object[r9]
            java.lang.String r8 = no.nordicsemi.android.error.LegacyDfuError.a(r4)
            r9 = 0
            r7[r9] = r8
            java.lang.String r5 = java.lang.String.format(r5, r6, r7)
            r6 = 20
            r2.sendLogBroadcast(r6, r5)
            java.lang.String r2 = "Sending Reset command (Op Code = 6)"
            r1.logi(r2)
            android.bluetooth.BluetoothGattCharacteristic r2 = r1.mControlPointCharacteristic
            byte[] r5 = OP_CODE_RESET
            r1.writeOpCode(r2, r5)
            no.nordicsemi.android.dfu.DfuBaseService r2 = r1.mService
            java.lang.String r5 = "Reset request sent"
            r2.sendLogBroadcast(r10, r5)
            no.nordicsemi.android.dfu.DfuBaseService r2 = r1.mService
            r4 = r4 | 8192(0x2000, float:1.14794E-41)
            r2.terminateConnection(r3, r4)
            goto L_0x0698
        L_0x066a:
            r0 = move-exception
            r2 = r0
            java.lang.String r4 = r2.getMessage()
            r1.loge(r4)
            no.nordicsemi.android.dfu.DfuBaseService r4 = r1.mService
            java.lang.String r2 = r2.getMessage()
            r5 = 20
            r4.sendLogBroadcast(r5, r2)
            java.lang.String r2 = "Sending Reset command (Op Code = 6)"
            r1.logi(r2)
            android.bluetooth.BluetoothGattCharacteristic r2 = r1.mControlPointCharacteristic
            byte[] r4 = OP_CODE_RESET
            r1.writeOpCode(r2, r4)
            no.nordicsemi.android.dfu.DfuBaseService r2 = r1.mService
            java.lang.String r4 = "Reset request sent"
            r2.sendLogBroadcast(r10, r4)
            no.nordicsemi.android.dfu.DfuBaseService r2 = r1.mService
            r4 = 4104(0x1008, float:5.751E-42)
            r2.terminateConnection(r3, r4)
        L_0x0698:
            return
        L_0x0699:
            r0 = move-exception
            r2 = r0
            java.lang.String r3 = "Sending Reset command (Op Code = 6)"
            r1.logi(r3)
            r3 = 0
            r1.mAborted = r3
            android.bluetooth.BluetoothGattCharacteristic r3 = r1.mControlPointCharacteristic
            byte[] r4 = OP_CODE_RESET
            r1.writeOpCode(r3, r4)
            no.nordicsemi.android.dfu.DfuBaseService r3 = r1.mService
            java.lang.String r4 = "Reset request sent"
            r3.sendLogBroadcast(r10, r4)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.dfu.LegacyDfuImpl.performDfu(android.content.Intent):void");
    }

    private void setNumberOfPackets(byte[] bArr, int i) {
        bArr[1] = (byte) (i & 255);
        bArr[2] = (byte) ((i >> 8) & 255);
    }

    private int getStatusCode(byte[] bArr, int i) throws UnknownResponseException {
        if (bArr != null && bArr.length == 3 && bArr[0] == 16 && bArr[1] == i && bArr[2] >= 1 && bArr[2] <= 6) {
            return bArr[2];
        }
        throw new UnknownResponseException("Invalid response received", bArr, 16, i);
    }

    private int readVersion(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic != null) {
            return bluetoothGattCharacteristic.getIntValue(18, 0).intValue();
        }
        return 0;
    }

    private void writeOpCode(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) throws DeviceDisconnectedException, DfuException, UploadAbortedException {
        boolean z = false;
        if (bArr[0] == 6 || bArr[0] == 5) {
            z = true;
        }
        writeOpCode(bluetoothGattCharacteristic, bArr, z);
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x009c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void writeImageSize(android.bluetooth.BluetoothGattCharacteristic r5, int r6) throws no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException, no.nordicsemi.android.dfu.internal.exception.DfuException, no.nordicsemi.android.dfu.internal.exception.UploadAbortedException {
        /*
            r4 = this;
            r0 = 0
            r4.mReceivedData = r0
            r0 = 0
            r4.mError = r0
            r1 = 1
            r4.mImageSizeInProgress = r1
            r5.setWriteType(r1)
            r2 = 4
            byte[] r2 = new byte[r2]
            r5.setValue(r2)
            r2 = 20
            r5.setValue(r6, r2, r0)
            no.nordicsemi.android.dfu.DfuBaseService r6 = r4.mService
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Writing to characteristic "
            r2.append(r3)
            java.util.UUID r3 = r5.getUuid()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r6.sendLogBroadcast(r1, r2)
            no.nordicsemi.android.dfu.DfuBaseService r6 = r4.mService
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "gatt.writeCharacteristic("
            r1.append(r2)
            java.util.UUID r2 = r5.getUuid()
            r1.append(r2)
            java.lang.String r2 = ")"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r6.sendLogBroadcast(r0, r1)
            android.bluetooth.BluetoothGatt r6 = r4.mGatt
            r6.writeCharacteristic(r5)
            java.lang.Object r5 = r4.mLock     // Catch:{ InterruptedException -> 0x0077 }
            monitor-enter(r5)     // Catch:{ InterruptedException -> 0x0077 }
        L_0x0058:
            boolean r6 = r4.mImageSizeInProgress     // Catch:{ all -> 0x0074 }
            if (r6 == 0) goto L_0x0068
            boolean r6 = r4.mConnected     // Catch:{ all -> 0x0074 }
            if (r6 == 0) goto L_0x0068
            int r6 = r4.mError     // Catch:{ all -> 0x0074 }
            if (r6 != 0) goto L_0x0068
            boolean r6 = r4.mAborted     // Catch:{ all -> 0x0074 }
            if (r6 == 0) goto L_0x006c
        L_0x0068:
            boolean r6 = r4.mPaused     // Catch:{ all -> 0x0074 }
            if (r6 == 0) goto L_0x0072
        L_0x006c:
            java.lang.Object r6 = r4.mLock     // Catch:{ all -> 0x0074 }
            r6.wait()     // Catch:{ all -> 0x0074 }
            goto L_0x0058
        L_0x0072:
            monitor-exit(r5)     // Catch:{ all -> 0x0074 }
            goto L_0x007d
        L_0x0074:
            r6 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0074 }
            throw r6     // Catch:{ InterruptedException -> 0x0077 }
        L_0x0077:
            r5 = move-exception
            java.lang.String r6 = "Sleeping interrupted"
            r4.loge(r6, r5)
        L_0x007d:
            boolean r5 = r4.mAborted
            if (r5 != 0) goto L_0x009c
            int r5 = r4.mError
            if (r5 != 0) goto L_0x0092
            boolean r5 = r4.mConnected
            if (r5 == 0) goto L_0x008a
            return
        L_0x008a:
            no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException r5 = new no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException
            java.lang.String r6 = "Unable to write Image Size: device disconnected"
            r5.<init>(r6)
            throw r5
        L_0x0092:
            no.nordicsemi.android.dfu.internal.exception.DfuException r5 = new no.nordicsemi.android.dfu.internal.exception.DfuException
            int r6 = r4.mError
            java.lang.String r0 = "Unable to write Image Size"
            r5.<init>(r0, r6)
            throw r5
        L_0x009c:
            no.nordicsemi.android.dfu.internal.exception.UploadAbortedException r5 = new no.nordicsemi.android.dfu.internal.exception.UploadAbortedException
            r5.<init>()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.dfu.LegacyDfuImpl.writeImageSize(android.bluetooth.BluetoothGattCharacteristic, int):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void writeImageSize(android.bluetooth.BluetoothGattCharacteristic r4, int r5, int r6, int r7) throws no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException, no.nordicsemi.android.dfu.internal.exception.DfuException, no.nordicsemi.android.dfu.internal.exception.UploadAbortedException {
        /*
            r3 = this;
            r0 = 0
            r3.mReceivedData = r0
            r0 = 0
            r3.mError = r0
            r1 = 1
            r3.mImageSizeInProgress = r1
            r4.setWriteType(r1)
            r2 = 12
            byte[] r2 = new byte[r2]
            r4.setValue(r2)
            r2 = 20
            r4.setValue(r5, r2, r0)
            r5 = 4
            r4.setValue(r6, r2, r5)
            r5 = 8
            r4.setValue(r7, r2, r5)
            no.nordicsemi.android.dfu.DfuBaseService r5 = r3.mService
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Writing to characteristic "
            r6.append(r7)
            java.util.UUID r7 = r4.getUuid()
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r5.sendLogBroadcast(r1, r6)
            no.nordicsemi.android.dfu.DfuBaseService r5 = r3.mService
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "gatt.writeCharacteristic("
            r6.append(r7)
            java.util.UUID r7 = r4.getUuid()
            r6.append(r7)
            java.lang.String r7 = ")"
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r5.sendLogBroadcast(r0, r6)
            android.bluetooth.BluetoothGatt r5 = r3.mGatt
            r5.writeCharacteristic(r4)
            java.lang.Object r4 = r3.mLock     // Catch:{ InterruptedException -> 0x0081 }
            monitor-enter(r4)     // Catch:{ InterruptedException -> 0x0081 }
        L_0x0062:
            boolean r5 = r3.mImageSizeInProgress     // Catch:{ all -> 0x007e }
            if (r5 == 0) goto L_0x0072
            boolean r5 = r3.mConnected     // Catch:{ all -> 0x007e }
            if (r5 == 0) goto L_0x0072
            int r5 = r3.mError     // Catch:{ all -> 0x007e }
            if (r5 != 0) goto L_0x0072
            boolean r5 = r3.mAborted     // Catch:{ all -> 0x007e }
            if (r5 == 0) goto L_0x0076
        L_0x0072:
            boolean r5 = r3.mPaused     // Catch:{ all -> 0x007e }
            if (r5 == 0) goto L_0x007c
        L_0x0076:
            java.lang.Object r5 = r3.mLock     // Catch:{ all -> 0x007e }
            r5.wait()     // Catch:{ all -> 0x007e }
            goto L_0x0062
        L_0x007c:
            monitor-exit(r4)     // Catch:{ all -> 0x007e }
            goto L_0x0087
        L_0x007e:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x007e }
            throw r5     // Catch:{ InterruptedException -> 0x0081 }
        L_0x0081:
            r4 = move-exception
            java.lang.String r5 = "Sleeping interrupted"
            r3.loge(r5, r4)
        L_0x0087:
            boolean r4 = r3.mAborted
            if (r4 != 0) goto L_0x00a6
            int r4 = r3.mError
            if (r4 != 0) goto L_0x009c
            boolean r4 = r3.mConnected
            if (r4 == 0) goto L_0x0094
            return
        L_0x0094:
            no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException r4 = new no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException
            java.lang.String r5 = "Unable to write Image Sizes: device disconnected"
            r4.<init>(r5)
            throw r4
        L_0x009c:
            no.nordicsemi.android.dfu.internal.exception.DfuException r4 = new no.nordicsemi.android.dfu.internal.exception.DfuException
            int r5 = r3.mError
            java.lang.String r6 = "Unable to write Image Sizes"
            r4.<init>(r6, r5)
            throw r4
        L_0x00a6:
            no.nordicsemi.android.dfu.internal.exception.UploadAbortedException r4 = new no.nordicsemi.android.dfu.internal.exception.UploadAbortedException
            r4.<init>()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.dfu.LegacyDfuImpl.writeImageSize(android.bluetooth.BluetoothGattCharacteristic, int, int, int):void");
    }

    private void resetAndRestart(BluetoothGatt bluetoothGatt, Intent intent) throws DfuException, DeviceDisconnectedException, UploadAbortedException {
        this.mService.sendLogBroadcast(15, "Last upload interrupted. Restarting device...");
        this.mProgressInfo.setProgress(-5);
        logi("Sending Reset command (Op Code = 6)");
        writeOpCode(this.mControlPointCharacteristic, OP_CODE_RESET);
        this.mService.sendLogBroadcast(10, "Reset request sent");
        this.mService.waitUntilDisconnected();
        this.mService.sendLogBroadcast(5, "Disconnected by the remote device");
        BluetoothGattService service = bluetoothGatt.getService(GENERIC_ATTRIBUTE_SERVICE_UUID);
        this.mService.refreshDeviceCache(bluetoothGatt, !((service == null || service.getCharacteristic(SERVICE_CHANGED_UUID) == null) ? false : true));
        this.mService.close(bluetoothGatt);
        logi("Restarting the service");
        Intent intent2 = new Intent();
        intent2.fillIn(intent, 24);
        restartService(intent2, false);
    }
}
