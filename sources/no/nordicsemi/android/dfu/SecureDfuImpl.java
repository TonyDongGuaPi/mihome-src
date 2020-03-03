package no.nordicsemi.android.dfu;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Intent;
import cn.com.fmsh.communication.core.MessageHead;
import com.taobao.weex.el.parse.Operators;
import java.io.InputStream;
import java.util.Locale;
import java.util.UUID;
import no.nordicsemi.android.dfu.BaseCustomDfuImpl;
import no.nordicsemi.android.dfu.BaseDfuImpl;
import no.nordicsemi.android.dfu.internal.ArchiveInputStream;
import no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException;
import no.nordicsemi.android.dfu.internal.exception.DfuException;
import no.nordicsemi.android.dfu.internal.exception.RemoteDfuException;
import no.nordicsemi.android.dfu.internal.exception.RemoteDfuExtendedErrorException;
import no.nordicsemi.android.dfu.internal.exception.UnknownResponseException;
import no.nordicsemi.android.dfu.internal.exception.UploadAbortedException;
import no.nordicsemi.android.error.SecureDfuError;

class SecureDfuImpl extends BaseCustomDfuImpl {
    protected static final UUID DEFAULT_DFU_CONTROL_POINT_UUID = new UUID(-8157989241631715488L, -6937650605005804976L);
    protected static final UUID DEFAULT_DFU_PACKET_UUID = new UUID(-8157989237336748192L, -6937650605005804976L);
    protected static final UUID DEFAULT_DFU_SERVICE_UUID = new UUID(279658205548544L, -9223371485494954757L);
    protected static UUID DFU_CONTROL_POINT_UUID = DEFAULT_DFU_CONTROL_POINT_UUID;
    protected static UUID DFU_PACKET_UUID = DEFAULT_DFU_PACKET_UUID;
    protected static UUID DFU_SERVICE_UUID = DEFAULT_DFU_SERVICE_UUID;
    private static final int DFU_STATUS_SUCCESS = 1;
    private static final int MAX_ATTEMPTS = 3;
    private static final int OBJECT_COMMAND = 1;
    private static final int OBJECT_DATA = 2;
    private static final byte[] OP_CODE_CALCULATE_CHECKSUM = {3};
    private static final int OP_CODE_CALCULATE_CHECKSUM_KEY = 3;
    private static final byte[] OP_CODE_CREATE_COMMAND = {1, 1, 0, 0, 0, 0};
    private static final byte[] OP_CODE_CREATE_DATA = {1, 2, 0, 0, 0, 0};
    private static final int OP_CODE_CREATE_KEY = 1;
    private static final byte[] OP_CODE_EXECUTE = {4};
    private static final int OP_CODE_EXECUTE_KEY = 4;
    private static final byte[] OP_CODE_PACKET_RECEIPT_NOTIF_REQ = {2, 0, 0};
    private static final int OP_CODE_PACKET_RECEIPT_NOTIF_REQ_KEY = 2;
    private static final int OP_CODE_RESPONSE_CODE_KEY = 96;
    private static final byte[] OP_CODE_SELECT_OBJECT = {6, 0};
    private static final int OP_CODE_SELECT_OBJECT_KEY = 6;
    private final SecureBluetoothCallback mBluetoothCallback = new SecureBluetoothCallback();
    private BluetoothGattCharacteristic mControlPointCharacteristic;
    private BluetoothGattCharacteristic mPacketCharacteristic;

    protected class SecureBluetoothCallback extends BaseCustomDfuImpl.BaseCustomBluetoothCallback {
        protected SecureBluetoothCallback() {
            super();
        }

        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            if (bluetoothGattCharacteristic.getValue() == null || bluetoothGattCharacteristic.getValue().length < 3) {
                SecureDfuImpl secureDfuImpl = SecureDfuImpl.this;
                secureDfuImpl.loge("Empty response: " + parse(bluetoothGattCharacteristic));
                SecureDfuImpl.this.mError = 4104;
                SecureDfuImpl.this.notifyLock();
                return;
            }
            if (bluetoothGattCharacteristic.getIntValue(17, 0).intValue() != 96) {
                SecureDfuImpl secureDfuImpl2 = SecureDfuImpl.this;
                secureDfuImpl2.loge("Invalid response: " + parse(bluetoothGattCharacteristic));
                SecureDfuImpl.this.mError = 4104;
            } else if (bluetoothGattCharacteristic.getIntValue(17, 1).intValue() == 3) {
                int intValue = bluetoothGattCharacteristic.getIntValue(20, 3).intValue();
                if (((int) (((ArchiveInputStream) SecureDfuImpl.this.mFirmwareStream).getCrc32() & MessageHead.SERIAL_MAK)) == bluetoothGattCharacteristic.getIntValue(20, 7).intValue()) {
                    SecureDfuImpl.this.mProgressInfo.setBytesReceived(intValue);
                } else if (SecureDfuImpl.this.mFirmwareUploadInProgress) {
                    SecureDfuImpl.this.mFirmwareUploadInProgress = false;
                    SecureDfuImpl.this.notifyLock();
                    return;
                }
                handlePacketReceiptNotification(bluetoothGatt, bluetoothGattCharacteristic);
            } else if (!SecureDfuImpl.this.mRemoteErrorOccurred) {
                if (bluetoothGattCharacteristic.getIntValue(17, 2).intValue() != 1) {
                    SecureDfuImpl.this.mRemoteErrorOccurred = true;
                }
                handleNotification(bluetoothGatt, bluetoothGattCharacteristic);
            }
            SecureDfuImpl.this.notifyLock();
        }
    }

    SecureDfuImpl(Intent intent, DfuBaseService dfuBaseService) {
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

    public boolean initialize(Intent intent, BluetoothGatt bluetoothGatt, int i, InputStream inputStream, InputStream inputStream2) throws DfuException, DeviceDisconnectedException, UploadAbortedException {
        if (inputStream2 != null) {
            return super.initialize(intent, bluetoothGatt, i, inputStream, inputStream2);
        }
        this.mService.sendLogBroadcast(20, "The Init packet is required by this version DFU Bootloader");
        this.mService.terminateConnection(bluetoothGatt, 4107);
        return false;
    }

    public BaseDfuImpl.BaseBluetoothGattCallback getGattCallback() {
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

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0171, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0172, code lost:
        loge(r10.getMessage());
        r9.mService.sendLogBroadcast(20, r10.getMessage());
        r9.mService.terminateConnection(r0, 4104);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x018a, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x018b, code lost:
        throw r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x006e A[Catch:{ RemoteDfuException -> 0x0077, UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0171 A[ExcHandler: UnknownResponseException (r10v2 'e' no.nordicsemi.android.dfu.internal.exception.UnknownResponseException A[CUSTOM_DECLARE]), Splitter:B:6:0x0045] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x018a A[ExcHandler: UploadAbortedException (r10v1 'e' no.nordicsemi.android.dfu.internal.exception.UploadAbortedException A[CUSTOM_DECLARE]), Splitter:B:6:0x0045] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void performDfu(android.content.Intent r10) throws no.nordicsemi.android.dfu.internal.exception.DfuException, no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException, no.nordicsemi.android.dfu.internal.exception.UploadAbortedException {
        /*
            r9 = this;
            java.lang.String r0 = "Secure DFU bootloader found"
            r9.logw(r0)
            no.nordicsemi.android.dfu.DfuProgressInfo r0 = r9.mProgressInfo
            r1 = -2
            r0.setProgress(r1)
            no.nordicsemi.android.dfu.DfuBaseService r0 = r9.mService
            r1 = 1000(0x3e8, float:1.401E-42)
            r0.waitFor(r1)
            android.bluetooth.BluetoothGatt r0 = r9.mGatt
            java.lang.String r2 = "no.nordicsemi.android.dfu.extra.EXTRA_MTU"
            boolean r2 = r10.hasExtra(r2)
            if (r2 == 0) goto L_0x0041
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 21
            if (r2 < r3) goto L_0x0041
            java.lang.String r2 = "no.nordicsemi.android.dfu.extra.EXTRA_MTU"
            r3 = 517(0x205, float:7.24E-43)
            int r2 = r10.getIntExtra(r2, r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Requesting MTU = "
            r3.append(r4)
            r3.append(r2)
            java.lang.String r3 = r3.toString()
            r9.logi(r3)
            r9.requestMtu(r2)
        L_0x0041:
            r2 = 20
            r3 = 1
            r4 = 0
            android.bluetooth.BluetoothGattCharacteristic r5 = r9.mControlPointCharacteristic     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            r9.enableCCCD(r5, r3)     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            no.nordicsemi.android.dfu.DfuBaseService r5 = r9.mService     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            r6 = 10
            java.lang.String r7 = "Notifications enabled"
            r5.sendLogBroadcast(r6, r7)     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            no.nordicsemi.android.dfu.DfuBaseService r5 = r9.mService     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            r5.waitFor(r1)     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            java.lang.String r1 = "no.nordicsemi.android.dfu.extra.EXTRA_DISABLE_RESUME"
            boolean r1 = r10.hasExtra(r1)     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            if (r1 == 0) goto L_0x006b
            java.lang.String r1 = "no.nordicsemi.android.dfu.extra.EXTRA_DISABLE_RESUME"
            boolean r1 = r10.getBooleanExtra(r1, r4)     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            if (r1 != 0) goto L_0x0069
            goto L_0x006b
        L_0x0069:
            r1 = 0
            goto L_0x006c
        L_0x006b:
            r1 = 1
        L_0x006c:
            if (r1 != 0) goto L_0x0073
            java.lang.String r5 = "Resume feature disabled. Performing fresh DFU"
            r9.logi(r5)     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
        L_0x0073:
            r9.sendInitPacket(r0, r1)     // Catch:{ RemoteDfuException -> 0x0077, UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171 }
            goto L_0x00bb
        L_0x0077:
            r1 = move-exception
            no.nordicsemi.android.dfu.DfuProgressInfo r5 = r9.mProgressInfo     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            boolean r5 = r5.isLastPart()     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            if (r5 != 0) goto L_0x00d6
            r9.mRemoteErrorOccurred = r4     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            java.lang.String r1 = "Sending SD+BL failed. Trying to send App only"
            r9.logw(r1)     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            no.nordicsemi.android.dfu.DfuBaseService r1 = r9.mService     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            r5 = 15
            java.lang.String r6 = "Invalid system components. Trying to send application"
            r1.sendLogBroadcast(r5, r6)     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            r1 = 4
            r9.mFileType = r1     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            java.io.InputStream r1 = r9.mFirmwareStream     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            no.nordicsemi.android.dfu.internal.ArchiveInputStream r1 = (no.nordicsemi.android.dfu.internal.ArchiveInputStream) r1     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            int r5 = r9.mFileType     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            r1.setContentType(r5)     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            byte[] r5 = r1.getApplicationInit()     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            java.io.ByteArrayInputStream r6 = new java.io.ByteArrayInputStream     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            r6.<init>(r5)     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            r9.mInitPacketStream = r6     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            int r5 = r5.length     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            r9.mInitPacketSizeInBytes = r5     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            int r1 = r1.applicationImageSize()     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            r9.mImageSizeInBytes = r1     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            no.nordicsemi.android.dfu.DfuProgressInfo r1 = r9.mProgressInfo     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            int r5 = r9.mImageSizeInBytes     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            r6 = 2
            r1.init(r5, r6, r6)     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            r9.sendInitPacket(r0, r4)     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
        L_0x00bb:
            r9.sendFirmware(r0)     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            no.nordicsemi.android.dfu.DfuProgressInfo r1 = r9.mProgressInfo     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            r5 = -5
            r1.setProgress(r5)     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            no.nordicsemi.android.dfu.DfuBaseService r1 = r9.mService     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            r1.waitUntilDisconnected()     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            no.nordicsemi.android.dfu.DfuBaseService r1 = r9.mService     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            r5 = 5
            java.lang.String r6 = "Disconnected by the remote device"
            r1.sendLogBroadcast(r5, r6)     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            r9.finalize(r10, r4)     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
            goto L_0x0189
        L_0x00d6:
            throw r1     // Catch:{ UploadAbortedException -> 0x018a, UnknownResponseException -> 0x0171, RemoteDfuException -> 0x00d7 }
        L_0x00d7:
            r10 = move-exception
            int r1 = r10.getErrorNumber()
            r1 = r1 | 512(0x200, float:7.175E-43)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = r10.getMessage()
            r5.append(r6)
            java.lang.String r6 = ": "
            r5.append(r6)
            java.lang.String r6 = no.nordicsemi.android.error.SecureDfuError.a(r1)
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r9.loge(r5)
            no.nordicsemi.android.dfu.DfuBaseService r5 = r9.mService
            java.util.Locale r6 = java.util.Locale.US
            java.lang.String r7 = "Remote DFU error: %s"
            java.lang.Object[] r3 = new java.lang.Object[r3]
            java.lang.String r8 = no.nordicsemi.android.error.SecureDfuError.a(r1)
            r3[r4] = r8
            java.lang.String r3 = java.lang.String.format(r6, r7, r3)
            r5.sendLogBroadcast(r2, r3)
            boolean r3 = r10 instanceof no.nordicsemi.android.dfu.internal.exception.RemoteDfuExtendedErrorException
            if (r3 == 0) goto L_0x0169
            no.nordicsemi.android.dfu.internal.exception.RemoteDfuExtendedErrorException r10 = (no.nordicsemi.android.dfu.internal.exception.RemoteDfuExtendedErrorException) r10
            int r1 = r10.getExtendedErrorNumber()
            r1 = r1 | 1024(0x400, float:1.435E-42)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Extended Error details: "
            r3.append(r4)
            java.lang.String r4 = no.nordicsemi.android.error.SecureDfuError.b(r1)
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r9.loge(r3)
            no.nordicsemi.android.dfu.DfuBaseService r3 = r9.mService
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Details: "
            r4.append(r5)
            java.lang.String r5 = no.nordicsemi.android.error.SecureDfuError.b(r1)
            r4.append(r5)
            java.lang.String r5 = " (Code = "
            r4.append(r5)
            int r10 = r10.getExtendedErrorNumber()
            r4.append(r10)
            java.lang.String r10 = ")"
            r4.append(r10)
            java.lang.String r10 = r4.toString()
            r3.sendLogBroadcast(r2, r10)
            no.nordicsemi.android.dfu.DfuBaseService r10 = r9.mService
            r1 = r1 | 8192(0x2000, float:1.14794E-41)
            r10.terminateConnection(r0, r1)
            goto L_0x0189
        L_0x0169:
            no.nordicsemi.android.dfu.DfuBaseService r10 = r9.mService
            r1 = r1 | 8192(0x2000, float:1.14794E-41)
            r10.terminateConnection(r0, r1)
            goto L_0x0189
        L_0x0171:
            r10 = move-exception
            java.lang.String r1 = r10.getMessage()
            r9.loge(r1)
            no.nordicsemi.android.dfu.DfuBaseService r1 = r9.mService
            java.lang.String r10 = r10.getMessage()
            r1.sendLogBroadcast(r2, r10)
            no.nordicsemi.android.dfu.DfuBaseService r10 = r9.mService
            r1 = 4104(0x1008, float:5.751E-42)
            r10.terminateConnection(r0, r1)
        L_0x0189:
            return
        L_0x018a:
            r10 = move-exception
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.dfu.SecureDfuImpl.performDfu(android.content.Intent):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x011e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void sendInitPacket(android.bluetooth.BluetoothGatt r17, boolean r18) throws no.nordicsemi.android.dfu.internal.exception.RemoteDfuException, no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException, no.nordicsemi.android.dfu.internal.exception.DfuException, no.nordicsemi.android.dfu.internal.exception.UploadAbortedException, no.nordicsemi.android.dfu.internal.exception.UnknownResponseException {
        /*
            r16 = this;
            r1 = r16
            r2 = r17
            java.util.zip.CRC32 r3 = new java.util.zip.CRC32
            r3.<init>()
            java.lang.String r0 = "Setting object to Command (Op Code = 6, Type = 1)"
            r1.logi(r0)
            r4 = 1
            no.nordicsemi.android.dfu.SecureDfuImpl$ObjectInfo r5 = r1.selectObject(r4)
            java.util.Locale r0 = java.util.Locale.US
            java.lang.String r6 = "Command object info received (Max size = %d, Offset = %d, CRC = %08X)"
            r7 = 3
            java.lang.Object[] r8 = new java.lang.Object[r7]
            int r9 = r5.maxSize
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            r10 = 0
            r8[r10] = r9
            int r9 = r5.offset
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            r8[r4] = r9
            int r9 = r5.CRC32
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            r11 = 2
            r8[r11] = r9
            java.lang.String r0 = java.lang.String.format(r0, r6, r8)
            r1.logi(r0)
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService
            java.util.Locale r6 = java.util.Locale.US
            java.lang.String r8 = "Command object info received (Max size = %d, Offset = %d, CRC = %08X)"
            java.lang.Object[] r9 = new java.lang.Object[r7]
            int r12 = r5.maxSize
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            r9[r10] = r12
            int r12 = r5.offset
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            r9[r4] = r12
            int r12 = r5.CRC32
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            r9[r11] = r12
            java.lang.String r6 = java.lang.String.format(r6, r8, r9)
            r8 = 10
            r0.sendLogBroadcast(r8, r6)
            int r0 = r1.mInitPacketSizeInBytes
            int r0 = r5.maxSize
            r6 = 4100(0x1004, float:5.745E-42)
            r12 = 4294967295(0xffffffff, double:2.1219957905E-314)
            if (r18 == 0) goto L_0x011a
            int r0 = r5.offset
            if (r0 <= 0) goto L_0x011a
            int r0 = r5.offset
            int r9 = r1.mInitPacketSizeInBytes
            if (r0 > r9) goto L_0x011a
            int r0 = r5.offset     // Catch:{ IOException -> 0x00e4 }
            byte[] r0 = new byte[r0]     // Catch:{ IOException -> 0x00e4 }
            java.io.InputStream r9 = r1.mInitPacketStream     // Catch:{ IOException -> 0x00e4 }
            r9.read(r0)     // Catch:{ IOException -> 0x00e4 }
            r3.update(r0)     // Catch:{ IOException -> 0x00e4 }
            long r14 = r3.getValue()     // Catch:{ IOException -> 0x00e4 }
            long r14 = r14 & r12
            int r0 = (int) r14     // Catch:{ IOException -> 0x00e4 }
            int r9 = r5.CRC32     // Catch:{ IOException -> 0x00e4 }
            if (r9 != r0) goto L_0x00d6
            java.lang.String r0 = "Init packet CRC is the same"
            r1.logi(r0)     // Catch:{ IOException -> 0x00e4 }
            int r0 = r5.offset     // Catch:{ IOException -> 0x00e4 }
            int r9 = r1.mInitPacketSizeInBytes     // Catch:{ IOException -> 0x00e4 }
            if (r0 != r9) goto L_0x00ad
            java.lang.String r0 = "-> Whole Init packet was sent before"
            r1.logi(r0)     // Catch:{ IOException -> 0x00e4 }
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService     // Catch:{ IOException -> 0x00aa }
            java.lang.String r9 = "Received CRC match Init packet"
            r0.sendLogBroadcast(r8, r9)     // Catch:{ IOException -> 0x00aa }
            r0 = 1
            goto L_0x00e1
        L_0x00aa:
            r0 = move-exception
            r9 = 1
            goto L_0x00e6
        L_0x00ad:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00e4 }
            r0.<init>()     // Catch:{ IOException -> 0x00e4 }
            java.lang.String r9 = "-> "
            r0.append(r9)     // Catch:{ IOException -> 0x00e4 }
            int r9 = r5.offset     // Catch:{ IOException -> 0x00e4 }
            r0.append(r9)     // Catch:{ IOException -> 0x00e4 }
            java.lang.String r9 = " bytes of Init packet were sent before"
            r0.append(r9)     // Catch:{ IOException -> 0x00e4 }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x00e4 }
            r1.logi(r0)     // Catch:{ IOException -> 0x00e4 }
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService     // Catch:{ IOException -> 0x00d2 }
            java.lang.String r9 = "Resuming sending Init packet..."
            r0.sendLogBroadcast(r8, r9)     // Catch:{ IOException -> 0x00d2 }
            r0 = 0
            r9 = 1
            goto L_0x00e2
        L_0x00d2:
            r0 = move-exception
            r9 = 0
            r14 = 1
            goto L_0x00e7
        L_0x00d6:
            java.io.InputStream r0 = r1.mInitPacketStream     // Catch:{ IOException -> 0x00e4 }
            r0.reset()     // Catch:{ IOException -> 0x00e4 }
            r3.reset()     // Catch:{ IOException -> 0x00e4 }
            r5.offset = r10     // Catch:{ IOException -> 0x00e4 }
            r0 = 0
        L_0x00e1:
            r9 = 0
        L_0x00e2:
            r14 = r9
            goto L_0x011c
        L_0x00e4:
            r0 = move-exception
            r9 = 0
        L_0x00e6:
            r14 = 0
        L_0x00e7:
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            java.lang.String r11 = "Error while reading "
            r15.append(r11)
            int r11 = r5.offset
            r15.append(r11)
            java.lang.String r11 = " bytes from the init packet stream"
            r15.append(r11)
            java.lang.String r11 = r15.toString()
            r1.loge(r11, r0)
            java.io.InputStream r0 = r1.mInitPacketStream     // Catch:{ IOException -> 0x010e }
            r0.reset()     // Catch:{ IOException -> 0x010e }
            r3.reset()     // Catch:{ IOException -> 0x010e }
            r5.offset = r10     // Catch:{ IOException -> 0x010e }
            r0 = r9
            goto L_0x011c
        L_0x010e:
            r0 = move-exception
            java.lang.String r3 = "Error while resetting the init packet stream"
            r1.loge(r3, r0)
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService
            r0.terminateConnection(r2, r6)
            return
        L_0x011a:
            r0 = 0
            r14 = 0
        L_0x011c:
            if (r0 != 0) goto L_0x0265
            r1.setPacketReceiptNotifications(r10)
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService
            java.lang.String r9 = "Packet Receipt Notif disabled (Op Code = 2, Value = 0)"
            r0.sendLogBroadcast(r8, r9)
            r0 = 1
        L_0x0129:
            if (r0 > r7) goto L_0x0265
            if (r14 != 0) goto L_0x0154
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r11 = "Creating Init packet object (Op Code = 1, Type = 1, Size = "
            r9.append(r11)
            int r11 = r1.mInitPacketSizeInBytes
            r9.append(r11)
            java.lang.String r11 = ")"
            r9.append(r11)
            java.lang.String r9 = r9.toString()
            r1.logi(r9)
            int r9 = r1.mInitPacketSizeInBytes
            r1.writeCreateRequest(r4, r9)
            no.nordicsemi.android.dfu.DfuBaseService r9 = r1.mService
            java.lang.String r11 = "Command object created"
            r9.sendLogBroadcast(r8, r11)
        L_0x0154:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r11 = "Sending "
            r9.append(r11)
            int r11 = r1.mInitPacketSizeInBytes
            int r14 = r5.offset
            int r11 = r11 - r14
            r9.append(r11)
            java.lang.String r11 = " bytes of init packet..."
            r9.append(r11)
            java.lang.String r9 = r9.toString()
            r1.logi(r9)
            android.bluetooth.BluetoothGattCharacteristic r9 = r1.mPacketCharacteristic
            r1.writeInitData(r9, r3)
            long r14 = r3.getValue()
            long r14 = r14 & r12
            int r9 = (int) r14
            no.nordicsemi.android.dfu.DfuBaseService r11 = r1.mService
            java.util.Locale r14 = java.util.Locale.US
            java.lang.String r15 = "Command object sent (CRC = %08X)"
            java.lang.Object[] r12 = new java.lang.Object[r4]
            java.lang.Integer r13 = java.lang.Integer.valueOf(r9)
            r12[r10] = r13
            java.lang.String r12 = java.lang.String.format(r14, r15, r12)
            r11.sendLogBroadcast(r8, r12)
            java.lang.String r11 = "Sending Calculate Checksum command (Op Code = 3)"
            r1.logi(r11)
            no.nordicsemi.android.dfu.SecureDfuImpl$ObjectChecksum r11 = r16.readChecksum()
            no.nordicsemi.android.dfu.DfuBaseService r12 = r1.mService
            java.util.Locale r13 = java.util.Locale.US
            java.lang.String r14 = "Checksum received (Offset = %d, CRC = %08X)"
            r15 = 2
            java.lang.Object[] r6 = new java.lang.Object[r15]
            int r7 = r11.offset
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r6[r10] = r7
            int r7 = r11.CRC32
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r6[r4] = r7
            java.lang.String r6 = java.lang.String.format(r13, r14, r6)
            r12.sendLogBroadcast(r8, r6)
            java.util.Locale r6 = java.util.Locale.US
            java.lang.String r7 = "Checksum received (Offset = %d, CRC = %08X)"
            java.lang.Object[] r12 = new java.lang.Object[r15]
            int r13 = r11.offset
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
            r12[r10] = r13
            int r13 = r11.CRC32
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
            r12[r4] = r13
            java.lang.String r6 = java.lang.String.format(r6, r7, r12)
            r1.logi(r6)
            int r6 = r11.CRC32
            if (r9 != r6) goto L_0x01de
            goto L_0x0265
        L_0x01de:
            r6 = 3
            if (r0 >= r6) goto L_0x024f
            int r0 = r0 + 1
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r9 = "CRC does not match! Retrying...("
            r7.append(r9)
            r7.append(r0)
            java.lang.String r9 = "/"
            r7.append(r9)
            r7.append(r6)
            java.lang.String r6 = ")"
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            r1.logi(r6)
            no.nordicsemi.android.dfu.DfuBaseService r6 = r1.mService
            r7 = 15
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r11 = "CRC does not match! Retrying...("
            r9.append(r11)
            r9.append(r0)
            java.lang.String r11 = "/"
            r9.append(r11)
            r11 = 3
            r9.append(r11)
            java.lang.String r12 = ")"
            r9.append(r12)
            java.lang.String r9 = r9.toString()
            r6.sendLogBroadcast(r7, r9)
            r5.offset = r10     // Catch:{ IOException -> 0x0241 }
            r5.CRC32 = r10     // Catch:{ IOException -> 0x0241 }
            java.io.InputStream r6 = r1.mInitPacketStream     // Catch:{ IOException -> 0x0241 }
            r6.reset()     // Catch:{ IOException -> 0x0241 }
            r3.reset()     // Catch:{ IOException -> 0x0241 }
            r6 = 4100(0x1004, float:5.745E-42)
            r7 = 3
            r12 = 4294967295(0xffffffff, double:2.1219957905E-314)
            r14 = 0
            goto L_0x0129
        L_0x0241:
            r0 = move-exception
            java.lang.String r3 = "Error while resetting the init packet stream"
            r1.loge(r3, r0)
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService
            r3 = 4100(0x1004, float:5.745E-42)
            r0.terminateConnection(r2, r3)
            return
        L_0x024f:
            java.lang.String r0 = "CRC does not match!"
            r1.loge(r0)
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService
            r3 = 20
            java.lang.String r4 = "CRC does not match!"
            r0.sendLogBroadcast(r3, r4)
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService
            r3 = 4109(0x100d, float:5.758E-42)
            r0.terminateConnection(r2, r3)
            return
        L_0x0265:
            java.lang.String r0 = "Executing init packet (Op Code = 4)"
            r1.logi(r0)
            r16.writeExecute()
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService
            java.lang.String r2 = "Command object executed"
            r0.sendLogBroadcast(r8, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.dfu.SecureDfuImpl.sendInitPacket(android.bluetooth.BluetoothGatt, boolean):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x01cd  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x03ed  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void sendFirmware(android.bluetooth.BluetoothGatt r21) throws no.nordicsemi.android.dfu.internal.exception.RemoteDfuException, no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException, no.nordicsemi.android.dfu.internal.exception.DfuException, no.nordicsemi.android.dfu.internal.exception.UploadAbortedException, no.nordicsemi.android.dfu.internal.exception.UnknownResponseException {
        /*
            r20 = this;
            r1 = r20
            r2 = r21
            int r0 = r1.mPacketsBeforeNotification
            r3 = 10
            if (r0 <= 0) goto L_0x0028
            r1.setPacketReceiptNotifications(r0)
            no.nordicsemi.android.dfu.DfuBaseService r4 = r1.mService
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Packet Receipt Notif Req (Op Code = 2) sent (Value = "
            r5.append(r6)
            r5.append(r0)
            java.lang.String r0 = ")"
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            r4.sendLogBroadcast(r3, r0)
        L_0x0028:
            java.lang.String r0 = "Setting object to Data (Op Code = 6, Type = 2)"
            r1.logi(r0)
            r0 = 2
            no.nordicsemi.android.dfu.SecureDfuImpl$ObjectInfo r4 = r1.selectObject(r0)
            java.util.Locale r5 = java.util.Locale.US
            java.lang.String r6 = "Data object info received (Max size = %d, Offset = %d, CRC = %08X)"
            r7 = 3
            java.lang.Object[] r8 = new java.lang.Object[r7]
            int r9 = r4.maxSize
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            r10 = 0
            r8[r10] = r9
            int r9 = r4.offset
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            r11 = 1
            r8[r11] = r9
            int r9 = r4.CRC32
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            r8[r0] = r9
            java.lang.String r5 = java.lang.String.format(r5, r6, r8)
            r1.logi(r5)
            no.nordicsemi.android.dfu.DfuBaseService r5 = r1.mService
            java.util.Locale r6 = java.util.Locale.US
            java.lang.String r8 = "Data object info received (Max size = %d, Offset = %d, CRC = %08X)"
            java.lang.Object[] r9 = new java.lang.Object[r7]
            int r12 = r4.maxSize
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            r9[r10] = r12
            int r12 = r4.offset
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            r9[r11] = r12
            int r12 = r4.CRC32
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            r9[r0] = r12
            java.lang.String r6 = java.lang.String.format(r6, r8, r9)
            r5.sendLogBroadcast(r3, r6)
            no.nordicsemi.android.dfu.DfuProgressInfo r5 = r1.mProgressInfo
            int r6 = r4.maxSize
            r5.setMaxObjectSizeInBytes(r6)
            int r5 = r1.mImageSizeInBytes
            int r6 = r4.maxSize
            int r5 = r5 + r6
            int r5 = r5 - r11
            int r6 = r4.maxSize
            int r5 = r5 / r6
            int r6 = r4.offset
            r8 = 4294967295(0xffffffff, double:2.1219957905E-314)
            r12 = 15
            r13 = 4100(0x1004, float:5.745E-42)
            if (r6 <= 0) goto L_0x01bb
            int r6 = r4.offset     // Catch:{ IOException -> 0x01af }
            int r14 = r4.maxSize     // Catch:{ IOException -> 0x01af }
            int r6 = r6 / r14
            int r14 = r4.maxSize     // Catch:{ IOException -> 0x01af }
            int r14 = r14 * r6
            int r15 = r4.offset     // Catch:{ IOException -> 0x01af }
            int r15 = r15 - r14
            if (r15 != 0) goto L_0x00b1
            int r15 = r4.maxSize     // Catch:{ IOException -> 0x01af }
            int r14 = r14 - r15
            int r15 = r4.maxSize     // Catch:{ IOException -> 0x01af }
        L_0x00b1:
            if (r14 <= 0) goto L_0x00c1
            java.io.InputStream r7 = r1.mFirmwareStream     // Catch:{ IOException -> 0x01af }
            byte[] r11 = new byte[r14]     // Catch:{ IOException -> 0x01af }
            r7.read(r11)     // Catch:{ IOException -> 0x01af }
            java.io.InputStream r7 = r1.mFirmwareStream     // Catch:{ IOException -> 0x01af }
            int r11 = r4.maxSize     // Catch:{ IOException -> 0x01af }
            r7.mark(r11)     // Catch:{ IOException -> 0x01af }
        L_0x00c1:
            java.io.InputStream r7 = r1.mFirmwareStream     // Catch:{ IOException -> 0x01af }
            byte[] r11 = new byte[r15]     // Catch:{ IOException -> 0x01af }
            r7.read(r11)     // Catch:{ IOException -> 0x01af }
            java.io.InputStream r7 = r1.mFirmwareStream     // Catch:{ IOException -> 0x01af }
            no.nordicsemi.android.dfu.internal.ArchiveInputStream r7 = (no.nordicsemi.android.dfu.internal.ArchiveInputStream) r7     // Catch:{ IOException -> 0x01af }
            long r18 = r7.getCrc32()     // Catch:{ IOException -> 0x01af }
            long r10 = r18 & r8
            int r7 = (int) r10     // Catch:{ IOException -> 0x01af }
            int r10 = r4.CRC32     // Catch:{ IOException -> 0x01af }
            if (r7 != r10) goto L_0x0131
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01af }
            r7.<init>()     // Catch:{ IOException -> 0x01af }
            int r10 = r4.offset     // Catch:{ IOException -> 0x01af }
            r7.append(r10)     // Catch:{ IOException -> 0x01af }
            java.lang.String r10 = " bytes of data sent before, CRC match"
            r7.append(r10)     // Catch:{ IOException -> 0x01af }
            java.lang.String r7 = r7.toString()     // Catch:{ IOException -> 0x01af }
            r1.logi(r7)     // Catch:{ IOException -> 0x01af }
            no.nordicsemi.android.dfu.DfuBaseService r7 = r1.mService     // Catch:{ IOException -> 0x01af }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01af }
            r10.<init>()     // Catch:{ IOException -> 0x01af }
            int r11 = r4.offset     // Catch:{ IOException -> 0x01af }
            r10.append(r11)     // Catch:{ IOException -> 0x01af }
            java.lang.String r11 = " bytes of data sent before, CRC match"
            r10.append(r11)     // Catch:{ IOException -> 0x01af }
            java.lang.String r10 = r10.toString()     // Catch:{ IOException -> 0x01af }
            r7.sendLogBroadcast(r3, r10)     // Catch:{ IOException -> 0x01af }
            no.nordicsemi.android.dfu.DfuProgressInfo r7 = r1.mProgressInfo     // Catch:{ IOException -> 0x01af }
            int r10 = r4.offset     // Catch:{ IOException -> 0x01af }
            r7.setBytesSent(r10)     // Catch:{ IOException -> 0x01af }
            no.nordicsemi.android.dfu.DfuProgressInfo r7 = r1.mProgressInfo     // Catch:{ IOException -> 0x01af }
            int r10 = r4.offset     // Catch:{ IOException -> 0x01af }
            r7.setBytesReceived(r10)     // Catch:{ IOException -> 0x01af }
            int r7 = r4.maxSize     // Catch:{ IOException -> 0x01af }
            if (r15 != r7) goto L_0x012e
            int r7 = r4.offset     // Catch:{ IOException -> 0x01af }
            int r10 = r1.mImageSizeInBytes     // Catch:{ IOException -> 0x01af }
            if (r7 >= r10) goto L_0x012e
            java.lang.String r7 = "Executing data object (Op Code = 4)"
            r1.logi(r7)     // Catch:{ IOException -> 0x01af }
            r20.writeExecute()     // Catch:{ IOException -> 0x01af }
            no.nordicsemi.android.dfu.DfuBaseService r7 = r1.mService     // Catch:{ IOException -> 0x01af }
            java.lang.String r10 = "Data object executed"
            r7.sendLogBroadcast(r3, r10)     // Catch:{ IOException -> 0x01af }
            goto L_0x01c2
        L_0x012e:
            r10 = 1
            goto L_0x01c3
        L_0x0131:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01af }
            r7.<init>()     // Catch:{ IOException -> 0x01af }
            int r10 = r4.offset     // Catch:{ IOException -> 0x01af }
            r7.append(r10)     // Catch:{ IOException -> 0x01af }
            java.lang.String r10 = " bytes sent before, CRC does not match"
            r7.append(r10)     // Catch:{ IOException -> 0x01af }
            java.lang.String r7 = r7.toString()     // Catch:{ IOException -> 0x01af }
            r1.logi(r7)     // Catch:{ IOException -> 0x01af }
            no.nordicsemi.android.dfu.DfuBaseService r7 = r1.mService     // Catch:{ IOException -> 0x01af }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01af }
            r10.<init>()     // Catch:{ IOException -> 0x01af }
            int r11 = r4.offset     // Catch:{ IOException -> 0x01af }
            r10.append(r11)     // Catch:{ IOException -> 0x01af }
            java.lang.String r11 = " bytes sent before, CRC does not match"
            r10.append(r11)     // Catch:{ IOException -> 0x01af }
            java.lang.String r10 = r10.toString()     // Catch:{ IOException -> 0x01af }
            r7.sendLogBroadcast(r12, r10)     // Catch:{ IOException -> 0x01af }
            no.nordicsemi.android.dfu.DfuProgressInfo r7 = r1.mProgressInfo     // Catch:{ IOException -> 0x01af }
            r7.setBytesSent(r14)     // Catch:{ IOException -> 0x01af }
            no.nordicsemi.android.dfu.DfuProgressInfo r7 = r1.mProgressInfo     // Catch:{ IOException -> 0x01af }
            r7.setBytesReceived(r14)     // Catch:{ IOException -> 0x01af }
            int r7 = r4.offset     // Catch:{ IOException -> 0x01af }
            int r7 = r7 - r15
            r4.offset = r7     // Catch:{ IOException -> 0x01af }
            r7 = 0
            r4.CRC32 = r7     // Catch:{ IOException -> 0x01af }
            java.io.InputStream r7 = r1.mFirmwareStream     // Catch:{ IOException -> 0x01af }
            r7.reset()     // Catch:{ IOException -> 0x01af }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01af }
            r7.<init>()     // Catch:{ IOException -> 0x01af }
            java.lang.String r10 = "Resuming from byte "
            r7.append(r10)     // Catch:{ IOException -> 0x01af }
            int r10 = r4.offset     // Catch:{ IOException -> 0x01af }
            r7.append(r10)     // Catch:{ IOException -> 0x01af }
            java.lang.String r10 = "..."
            r7.append(r10)     // Catch:{ IOException -> 0x01af }
            java.lang.String r7 = r7.toString()     // Catch:{ IOException -> 0x01af }
            r1.logi(r7)     // Catch:{ IOException -> 0x01af }
            no.nordicsemi.android.dfu.DfuBaseService r7 = r1.mService     // Catch:{ IOException -> 0x01af }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01af }
            r10.<init>()     // Catch:{ IOException -> 0x01af }
            java.lang.String r11 = "Resuming from byte "
            r10.append(r11)     // Catch:{ IOException -> 0x01af }
            int r11 = r4.offset     // Catch:{ IOException -> 0x01af }
            r10.append(r11)     // Catch:{ IOException -> 0x01af }
            java.lang.String r11 = "..."
            r10.append(r11)     // Catch:{ IOException -> 0x01af }
            java.lang.String r10 = r10.toString()     // Catch:{ IOException -> 0x01af }
            r7.sendLogBroadcast(r3, r10)     // Catch:{ IOException -> 0x01af }
            goto L_0x01c2
        L_0x01af:
            r0 = move-exception
            java.lang.String r3 = "Error while reading firmware stream"
            r1.loge(r3, r0)
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService
            r0.terminateConnection(r2, r13)
            return
        L_0x01bb:
            no.nordicsemi.android.dfu.DfuProgressInfo r6 = r1.mProgressInfo
            r7 = 0
            r6.setBytesSent(r7)
            r6 = 0
        L_0x01c2:
            r10 = 0
        L_0x01c3:
            long r14 = android.os.SystemClock.elapsedRealtime()
            int r7 = r4.offset
            int r11 = r1.mImageSizeInBytes
            if (r7 >= r11) goto L_0x03ed
            r11 = 1
        L_0x01ce:
            no.nordicsemi.android.dfu.DfuProgressInfo r7 = r1.mProgressInfo
            int r7 = r7.getAvailableObjectSizeIsBytes()
            if (r7 <= 0) goto L_0x03ff
            if (r10 != 0) goto L_0x0237
            no.nordicsemi.android.dfu.DfuProgressInfo r7 = r1.mProgressInfo
            int r7 = r7.getAvailableObjectSizeIsBytes()
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "Creating Data object (Op Code = 1, Type = 2, Size = "
            r8.append(r9)
            r8.append(r7)
            java.lang.String r9 = ") ("
            r8.append(r9)
            int r9 = r6 + 1
            r8.append(r9)
            java.lang.String r13 = "/"
            r8.append(r13)
            r8.append(r5)
            java.lang.String r13 = ")"
            r8.append(r13)
            java.lang.String r8 = r8.toString()
            r1.logi(r8)
            r1.writeCreateRequest(r0, r7)
            no.nordicsemi.android.dfu.DfuBaseService r7 = r1.mService
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r13 = "Data object ("
            r8.append(r13)
            r8.append(r9)
            java.lang.String r9 = "/"
            r8.append(r9)
            r8.append(r5)
            java.lang.String r9 = ") created"
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r7.sendLogBroadcast(r3, r8)
            no.nordicsemi.android.dfu.DfuBaseService r7 = r1.mService
            java.lang.String r8 = "Uploading firmware..."
            r7.sendLogBroadcast(r3, r8)
            goto L_0x023f
        L_0x0237:
            no.nordicsemi.android.dfu.DfuBaseService r7 = r1.mService
            java.lang.String r8 = "Resuming uploading firmware..."
            r7.sendLogBroadcast(r3, r8)
            r10 = 0
        L_0x023f:
            java.lang.String r7 = "Uploading firmware..."
            r1.logi(r7)     // Catch:{ DeviceDisconnectedException -> 0x03e6 }
            android.bluetooth.BluetoothGattCharacteristic r7 = r1.mPacketCharacteristic     // Catch:{ DeviceDisconnectedException -> 0x03e6 }
            r1.uploadFirmwareImage(r7)     // Catch:{ DeviceDisconnectedException -> 0x03e6 }
            java.lang.String r7 = "Sending Calculate Checksum command (Op Code = 3)"
            r1.logi(r7)
            no.nordicsemi.android.dfu.SecureDfuImpl$ObjectChecksum r7 = r20.readChecksum()
            java.util.Locale r8 = java.util.Locale.US
            java.lang.String r9 = "Checksum received (Offset = %d, CRC = %08X)"
            java.lang.Object[] r13 = new java.lang.Object[r0]
            int r12 = r7.offset
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            r18 = 0
            r13[r18] = r12
            int r12 = r7.CRC32
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            r17 = 1
            r13[r17] = r12
            java.lang.String r8 = java.lang.String.format(r8, r9, r13)
            r1.logi(r8)
            no.nordicsemi.android.dfu.DfuBaseService r8 = r1.mService
            java.util.Locale r9 = java.util.Locale.US
            java.lang.String r12 = "Checksum received (Offset = %d, CRC = %08X)"
            java.lang.Object[] r13 = new java.lang.Object[r0]
            int r0 = r7.offset
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r18 = 0
            r13[r18] = r0
            int r0 = r7.CRC32
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r17 = 1
            r13[r17] = r0
            java.lang.String r0 = java.lang.String.format(r9, r12, r13)
            r8.sendLogBroadcast(r3, r0)
            no.nordicsemi.android.dfu.DfuProgressInfo r0 = r1.mProgressInfo
            int r0 = r0.getBytesSent()
            int r8 = r7.offset
            int r0 = r0 - r8
            if (r0 <= 0) goto L_0x0313
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r0)
            java.lang.String r9 = " bytes were lost!"
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r1.logw(r8)
            no.nordicsemi.android.dfu.DfuBaseService r8 = r1.mService
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r0)
            java.lang.String r12 = " bytes were lost"
            r9.append(r12)
            java.lang.String r9 = r9.toString()
            r12 = 15
            r8.sendLogBroadcast(r12, r9)
            java.io.InputStream r8 = r1.mFirmwareStream     // Catch:{ IOException -> 0x0305 }
            r8.reset()     // Catch:{ IOException -> 0x0305 }
            java.io.InputStream r8 = r1.mFirmwareStream     // Catch:{ IOException -> 0x0305 }
            int r9 = r4.maxSize     // Catch:{ IOException -> 0x0305 }
            int r9 = r9 - r0
            byte[] r9 = new byte[r9]     // Catch:{ IOException -> 0x0305 }
            r8.read(r9)     // Catch:{ IOException -> 0x0305 }
            no.nordicsemi.android.dfu.DfuProgressInfo r8 = r1.mProgressInfo     // Catch:{ IOException -> 0x0305 }
            int r9 = r7.offset     // Catch:{ IOException -> 0x0305 }
            r8.setBytesSent(r9)     // Catch:{ IOException -> 0x0305 }
            r8 = 1
            r1.mPacketsBeforeNotification = r8
            r1.setPacketReceiptNotifications(r8)
            no.nordicsemi.android.dfu.DfuBaseService r9 = r1.mService
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r13 = "Packet Receipt Notif Req (Op Code = 2) sent (Value = "
            r12.append(r13)
            r12.append(r8)
            java.lang.String r8 = ")"
            r12.append(r8)
            java.lang.String r8 = r12.toString()
            r9.sendLogBroadcast(r3, r8)
            goto L_0x0313
        L_0x0305:
            r0 = move-exception
            java.lang.String r3 = "Error while reading firmware stream"
            r1.loge(r3, r0)
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService
            r3 = 4100(0x1004, float:5.745E-42)
            r0.terminateConnection(r2, r3)
            return
        L_0x0313:
            java.io.InputStream r8 = r1.mFirmwareStream
            no.nordicsemi.android.dfu.internal.ArchiveInputStream r8 = (no.nordicsemi.android.dfu.internal.ArchiveInputStream) r8
            long r8 = r8.getCrc32()
            r12 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r8 = r8 & r12
            int r8 = (int) r8
            int r9 = r7.CRC32
            if (r8 != r9) goto L_0x0356
            if (r0 <= 0) goto L_0x0331
            r8 = r12
            r0 = 2
            r10 = 1
        L_0x032b:
            r12 = 15
            r13 = 4100(0x1004, float:5.745E-42)
            goto L_0x01ce
        L_0x0331:
            java.lang.String r0 = "Executing data object (Op Code = 4)"
            r1.logi(r0)
            no.nordicsemi.android.dfu.DfuProgressInfo r0 = r1.mProgressInfo
            boolean r0 = r0.isComplete()
            r1.writeExecute(r0)
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService
            java.lang.String r7 = "Data object executed"
            r0.sendLogBroadcast(r3, r7)
            int r6 = r6 + 1
            java.io.InputStream r0 = r1.mFirmwareStream
            r9 = 0
            r0.mark(r9)
            r3 = 3
            r8 = 15
            r9 = 2
            r11 = 1
            r16 = 0
            goto L_0x03bc
        L_0x0356:
            r9 = 0
            java.util.Locale r0 = java.util.Locale.US
            java.lang.String r12 = "CRC does not match! Expected %08X but found %08X."
            r13 = 2
            java.lang.Object[] r3 = new java.lang.Object[r13]
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r3[r9] = r8
            int r7 = r7.CRC32
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r8 = 1
            r3[r8] = r7
            java.lang.String r0 = java.lang.String.format(r0, r12, r3)
            r3 = 3
            if (r11 >= r3) goto L_0x03d4
            int r11 = r11 + 1
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r0)
            java.util.Locale r0 = java.util.Locale.US
            java.lang.String r8 = " Retrying...(%d/%d)"
            r9 = 2
            java.lang.Object[] r12 = new java.lang.Object[r9]
            java.lang.Integer r13 = java.lang.Integer.valueOf(r11)
            r16 = 0
            r12[r16] = r13
            java.lang.Integer r13 = java.lang.Integer.valueOf(r3)
            r17 = 1
            r12[r17] = r13
            java.lang.String r0 = java.lang.String.format(r0, r8, r12)
            r7.append(r0)
            java.lang.String r0 = r7.toString()
            r1.logi(r0)
            no.nordicsemi.android.dfu.DfuBaseService r7 = r1.mService
            r8 = 15
            r7.sendLogBroadcast(r8, r0)
            java.io.InputStream r0 = r1.mFirmwareStream     // Catch:{ IOException -> 0x03c6 }
            r0.reset()     // Catch:{ IOException -> 0x03c6 }
            no.nordicsemi.android.dfu.DfuProgressInfo r0 = r1.mProgressInfo     // Catch:{ IOException -> 0x03c6 }
            java.io.InputStream r7 = r1.mFirmwareStream     // Catch:{ IOException -> 0x03c6 }
            no.nordicsemi.android.dfu.internal.ArchiveInputStream r7 = (no.nordicsemi.android.dfu.internal.ArchiveInputStream) r7     // Catch:{ IOException -> 0x03c6 }
            int r7 = r7.getBytesRead()     // Catch:{ IOException -> 0x03c6 }
            r0.setBytesSent(r7)     // Catch:{ IOException -> 0x03c6 }
        L_0x03bc:
            r0 = 2
            r3 = 10
            r8 = 4294967295(0xffffffff, double:2.1219957905E-314)
            goto L_0x032b
        L_0x03c6:
            r0 = move-exception
            java.lang.String r3 = "Error while resetting the firmware stream"
            r1.loge(r3, r0)
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService
            r3 = 4100(0x1004, float:5.745E-42)
            r0.terminateConnection(r2, r3)
            return
        L_0x03d4:
            r1.loge(r0)
            no.nordicsemi.android.dfu.DfuBaseService r3 = r1.mService
            r4 = 20
            r3.sendLogBroadcast(r4, r0)
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService
            r3 = 4109(0x100d, float:5.758E-42)
            r0.terminateConnection(r2, r3)
            return
        L_0x03e6:
            r0 = move-exception
            java.lang.String r2 = "Disconnected while sending data"
            r1.loge(r2)
            throw r0
        L_0x03ed:
            java.lang.String r0 = "Executing data object (Op Code = 4)"
            r1.logi(r0)
            r0 = 1
            r1.writeExecute(r0)
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService
            java.lang.String r2 = "Data object executed"
            r3 = 10
            r0.sendLogBroadcast(r3, r2)
        L_0x03ff:
            long r2 = android.os.SystemClock.elapsedRealtime()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r5 = "Transfer of "
            r0.append(r5)
            no.nordicsemi.android.dfu.DfuProgressInfo r5 = r1.mProgressInfo
            int r5 = r5.getBytesSent()
            int r4 = r4.offset
            int r5 = r5 - r4
            r0.append(r5)
            java.lang.String r4 = " bytes has taken "
            r0.append(r4)
            long r2 = r2 - r14
            r0.append(r2)
            java.lang.String r4 = " ms"
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            r1.logi(r0)
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Upload completed in "
            r4.append(r5)
            r4.append(r2)
            java.lang.String r2 = " ms"
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            r3 = 10
            r0.sendLogBroadcast(r3, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.dfu.SecureDfuImpl.sendFirmware(android.bluetooth.BluetoothGatt):void");
    }

    private int getStatusCode(byte[] bArr, int i) throws UnknownResponseException {
        if (bArr != null && bArr.length >= 3 && bArr[0] == 96 && bArr[1] == i && (bArr[2] == 1 || bArr[2] == 2 || bArr[2] == 3 || bArr[2] == 4 || bArr[2] == 5 || bArr[2] == 7 || bArr[2] == 8 || bArr[2] == 10 || bArr[2] == 11)) {
            return bArr[2];
        }
        throw new UnknownResponseException("Invalid response received", bArr, 96, i);
    }

    private void setNumberOfPackets(byte[] bArr, int i) {
        bArr[1] = (byte) (i & 255);
        bArr[2] = (byte) ((i >> 8) & 255);
    }

    private void setObjectSize(byte[] bArr, int i) {
        bArr[2] = (byte) (i & 255);
        bArr[3] = (byte) ((i >> 8) & 255);
        bArr[4] = (byte) ((i >> 16) & 255);
        bArr[5] = (byte) ((i >> 24) & 255);
    }

    private void setPacketReceiptNotifications(int i) throws DfuException, DeviceDisconnectedException, UploadAbortedException, UnknownResponseException, RemoteDfuException {
        if (this.mConnected) {
            logi("Sending the number of packets before notifications (Op Code = 2, Value = " + i + Operators.BRACKET_END_STR);
            setNumberOfPackets(OP_CODE_PACKET_RECEIPT_NOTIF_REQ, i);
            writeOpCode(this.mControlPointCharacteristic, OP_CODE_PACKET_RECEIPT_NOTIF_REQ);
            byte[] readNotificationResponse = readNotificationResponse();
            int statusCode = getStatusCode(readNotificationResponse, 2);
            if (statusCode == 11) {
                throw new RemoteDfuExtendedErrorException("Sending the number of packets failed", readNotificationResponse[3]);
            } else if (statusCode != 1) {
                throw new RemoteDfuException("Sending the number of packets failed", statusCode);
            }
        } else {
            throw new DeviceDisconnectedException("Unable to read Checksum: device disconnected");
        }
    }

    private void writeOpCode(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) throws DeviceDisconnectedException, DfuException, UploadAbortedException {
        writeOpCode(bluetoothGattCharacteristic, bArr, false);
    }

    private void writeCreateRequest(int i, int i2) throws DeviceDisconnectedException, DfuException, UploadAbortedException, RemoteDfuException, UnknownResponseException {
        if (this.mConnected) {
            byte[] bArr = i == 1 ? OP_CODE_CREATE_COMMAND : OP_CODE_CREATE_DATA;
            setObjectSize(bArr, i2);
            writeOpCode(this.mControlPointCharacteristic, bArr);
            byte[] readNotificationResponse = readNotificationResponse();
            int statusCode = getStatusCode(readNotificationResponse, 1);
            if (statusCode == 11) {
                throw new RemoteDfuExtendedErrorException("Creating Command object failed", readNotificationResponse[3]);
            } else if (statusCode != 1) {
                throw new RemoteDfuException("Creating Command object failed", statusCode);
            }
        } else {
            throw new DeviceDisconnectedException("Unable to create object: device disconnected");
        }
    }

    private ObjectInfo selectObject(int i) throws DeviceDisconnectedException, DfuException, UploadAbortedException, RemoteDfuException, UnknownResponseException {
        if (this.mConnected) {
            OP_CODE_SELECT_OBJECT[1] = (byte) i;
            writeOpCode(this.mControlPointCharacteristic, OP_CODE_SELECT_OBJECT);
            byte[] readNotificationResponse = readNotificationResponse();
            int statusCode = getStatusCode(readNotificationResponse, 6);
            if (statusCode == 11) {
                throw new RemoteDfuExtendedErrorException("Selecting object failed", readNotificationResponse[3]);
            } else if (statusCode == 1) {
                ObjectInfo objectInfo = new ObjectInfo();
                objectInfo.maxSize = this.mControlPointCharacteristic.getIntValue(20, 3).intValue();
                objectInfo.offset = this.mControlPointCharacteristic.getIntValue(20, 7).intValue();
                objectInfo.CRC32 = this.mControlPointCharacteristic.getIntValue(20, 11).intValue();
                return objectInfo;
            } else {
                throw new RemoteDfuException("Selecting object failed", statusCode);
            }
        } else {
            throw new DeviceDisconnectedException("Unable to read object info: device disconnected");
        }
    }

    private ObjectChecksum readChecksum() throws DeviceDisconnectedException, DfuException, UploadAbortedException, RemoteDfuException, UnknownResponseException {
        if (this.mConnected) {
            writeOpCode(this.mControlPointCharacteristic, OP_CODE_CALCULATE_CHECKSUM);
            byte[] readNotificationResponse = readNotificationResponse();
            int statusCode = getStatusCode(readNotificationResponse, 3);
            if (statusCode == 11) {
                throw new RemoteDfuExtendedErrorException("Receiving Checksum failed", readNotificationResponse[3]);
            } else if (statusCode == 1) {
                ObjectChecksum objectChecksum = new ObjectChecksum();
                objectChecksum.offset = this.mControlPointCharacteristic.getIntValue(20, 3).intValue();
                objectChecksum.CRC32 = this.mControlPointCharacteristic.getIntValue(20, 7).intValue();
                return objectChecksum;
            } else {
                throw new RemoteDfuException("Receiving Checksum failed", statusCode);
            }
        } else {
            throw new DeviceDisconnectedException("Unable to read Checksum: device disconnected");
        }
    }

    private void writeExecute() throws DfuException, DeviceDisconnectedException, UploadAbortedException, UnknownResponseException, RemoteDfuException {
        if (this.mConnected) {
            writeOpCode(this.mControlPointCharacteristic, OP_CODE_EXECUTE);
            byte[] readNotificationResponse = readNotificationResponse();
            int statusCode = getStatusCode(readNotificationResponse, 4);
            if (statusCode == 11) {
                throw new RemoteDfuExtendedErrorException("Executing object failed", readNotificationResponse[3]);
            } else if (statusCode != 1) {
                throw new RemoteDfuException("Executing object failed", statusCode);
            }
        } else {
            throw new DeviceDisconnectedException("Unable to read Checksum: device disconnected");
        }
    }

    private void writeExecute(boolean z) throws DfuException, DeviceDisconnectedException, UploadAbortedException, UnknownResponseException, RemoteDfuException {
        try {
            writeExecute();
        } catch (RemoteDfuException e) {
            if (!z || e.getErrorNumber() != 5) {
                throw e;
            }
            logw(e.getMessage() + ": " + SecureDfuError.a(517));
            if (this.mFileType == 1) {
                logw("Are you sure your new SoftDevice is API compatible with the updated one? If not, update the bootloader as well");
            }
            this.mService.sendLogBroadcast(15, String.format(Locale.US, "Remote DFU error: %s. SD busy? Retrying...", new Object[]{SecureDfuError.a(517)}));
            logi("SD busy? Retrying...");
            logi("Executing data object (Op Code = 4)");
            writeExecute();
        }
    }

    private class ObjectInfo extends ObjectChecksum {
        protected int maxSize;

        private ObjectInfo() {
            super();
        }
    }

    private class ObjectChecksum {
        protected int CRC32;
        protected int offset;

        private ObjectChecksum() {
        }
    }
}
