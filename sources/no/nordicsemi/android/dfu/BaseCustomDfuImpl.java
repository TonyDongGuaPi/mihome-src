package no.nordicsemi.android.dfu;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Intent;
import java.io.IOException;
import java.util.UUID;
import java.util.zip.CRC32;
import no.nordicsemi.android.dfu.BaseDfuImpl;
import no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException;
import no.nordicsemi.android.dfu.internal.exception.DfuException;
import no.nordicsemi.android.dfu.internal.exception.HexFileValidationException;
import no.nordicsemi.android.dfu.internal.exception.UploadAbortedException;

abstract class BaseCustomDfuImpl extends BaseDfuImpl {
    protected boolean mFirmwareUploadInProgress;
    /* access modifiers changed from: private */
    public boolean mInitPacketInProgress;
    protected int mPacketsBeforeNotification;
    protected int mPacketsSentSinceNotification;
    protected boolean mRemoteErrorOccurred;

    /* access modifiers changed from: protected */
    public abstract UUID getControlPointCharacteristicUUID();

    /* access modifiers changed from: protected */
    public abstract UUID getDfuServiceUUID();

    /* access modifiers changed from: protected */
    public abstract UUID getPacketCharacteristicUUID();

    protected class BaseCustomBluetoothCallback extends BaseDfuImpl.BaseBluetoothGattCallback {
        /* access modifiers changed from: protected */
        public void onPacketCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        }

        protected BaseCustomBluetoothCallback() {
            super();
        }

        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            boolean z = true;
            if (i == 0) {
                if (!bluetoothGattCharacteristic.getUuid().equals(BaseCustomDfuImpl.this.getPacketCharacteristicUUID())) {
                    BaseCustomDfuImpl.this.mService.sendLogBroadcast(5, "Data written to " + bluetoothGattCharacteristic.getUuid() + ", value (0x): " + parse(bluetoothGattCharacteristic));
                    BaseCustomDfuImpl.this.mRequestCompleted = true;
                } else if (BaseCustomDfuImpl.this.mInitPacketInProgress) {
                    BaseCustomDfuImpl.this.mService.sendLogBroadcast(5, "Data written to " + bluetoothGattCharacteristic.getUuid() + ", value (0x): " + parse(bluetoothGattCharacteristic));
                    boolean unused = BaseCustomDfuImpl.this.mInitPacketInProgress = false;
                } else if (BaseCustomDfuImpl.this.mFirmwareUploadInProgress) {
                    BaseCustomDfuImpl.this.mProgressInfo.addBytesSent(bluetoothGattCharacteristic.getValue().length);
                    BaseCustomDfuImpl.this.mPacketsSentSinceNotification++;
                    if (BaseCustomDfuImpl.this.mPacketsBeforeNotification <= 0 || BaseCustomDfuImpl.this.mPacketsSentSinceNotification < BaseCustomDfuImpl.this.mPacketsBeforeNotification) {
                        z = false;
                    }
                    boolean isComplete = BaseCustomDfuImpl.this.mProgressInfo.isComplete();
                    boolean isObjectComplete = BaseCustomDfuImpl.this.mProgressInfo.isObjectComplete();
                    if (!z) {
                        if (isComplete || isObjectComplete) {
                            BaseCustomDfuImpl.this.mFirmwareUploadInProgress = false;
                            BaseCustomDfuImpl.this.notifyLock();
                            return;
                        }
                        try {
                            BaseCustomDfuImpl.this.waitIfPaused();
                            if (!BaseCustomDfuImpl.this.mAborted && BaseCustomDfuImpl.this.mError == 0 && !BaseCustomDfuImpl.this.mRemoteErrorOccurred) {
                                if (!BaseCustomDfuImpl.this.mResetRequestSent) {
                                    int availableObjectSizeIsBytes = BaseCustomDfuImpl.this.mProgressInfo.getAvailableObjectSizeIsBytes();
                                    byte[] bArr = BaseCustomDfuImpl.this.mBuffer;
                                    if (availableObjectSizeIsBytes < bArr.length) {
                                        bArr = new byte[availableObjectSizeIsBytes];
                                    }
                                    BaseCustomDfuImpl.this.writePacket(bluetoothGatt, bluetoothGattCharacteristic, bArr, BaseCustomDfuImpl.this.mFirmwareStream.read(bArr));
                                    return;
                                }
                            }
                            BaseCustomDfuImpl.this.mFirmwareUploadInProgress = false;
                            BaseCustomDfuImpl.this.mService.sendLogBroadcast(15, "Upload terminated");
                            BaseCustomDfuImpl.this.notifyLock();
                            return;
                        } catch (HexFileValidationException unused2) {
                            BaseCustomDfuImpl.this.loge("Invalid HEX file");
                            BaseCustomDfuImpl.this.mError = 4099;
                        } catch (IOException e) {
                            BaseCustomDfuImpl.this.loge("Error while reading the input stream", e);
                            BaseCustomDfuImpl.this.mError = 4100;
                        }
                    } else {
                        return;
                    }
                } else {
                    onPacketCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
                }
            } else if (BaseCustomDfuImpl.this.mResetRequestSent) {
                BaseCustomDfuImpl.this.mRequestCompleted = true;
            } else {
                BaseCustomDfuImpl.this.loge("Characteristic write error: " + i);
                BaseCustomDfuImpl.this.mError = i | 16384;
            }
            BaseCustomDfuImpl.this.notifyLock();
        }

        /* access modifiers changed from: protected */
        public void handlePacketReceiptNotification(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            if (!BaseCustomDfuImpl.this.mFirmwareUploadInProgress) {
                handleNotification(bluetoothGatt, bluetoothGattCharacteristic);
                return;
            }
            BluetoothGattCharacteristic characteristic = bluetoothGatt.getService(BaseCustomDfuImpl.this.getDfuServiceUUID()).getCharacteristic(BaseCustomDfuImpl.this.getPacketCharacteristicUUID());
            try {
                BaseCustomDfuImpl.this.mPacketsSentSinceNotification = 0;
                BaseCustomDfuImpl.this.waitIfPaused();
                if (!BaseCustomDfuImpl.this.mAborted && BaseCustomDfuImpl.this.mError == 0 && !BaseCustomDfuImpl.this.mRemoteErrorOccurred) {
                    if (!BaseCustomDfuImpl.this.mResetRequestSent) {
                        boolean isComplete = BaseCustomDfuImpl.this.mProgressInfo.isComplete();
                        boolean isObjectComplete = BaseCustomDfuImpl.this.mProgressInfo.isObjectComplete();
                        if (!isComplete) {
                            if (!isObjectComplete) {
                                int availableObjectSizeIsBytes = BaseCustomDfuImpl.this.mProgressInfo.getAvailableObjectSizeIsBytes();
                                byte[] bArr = BaseCustomDfuImpl.this.mBuffer;
                                if (availableObjectSizeIsBytes < bArr.length) {
                                    bArr = new byte[availableObjectSizeIsBytes];
                                }
                                BaseCustomDfuImpl.this.writePacket(bluetoothGatt, characteristic, bArr, BaseCustomDfuImpl.this.mFirmwareStream.read(bArr));
                                return;
                            }
                        }
                        BaseCustomDfuImpl.this.mFirmwareUploadInProgress = false;
                        BaseCustomDfuImpl.this.notifyLock();
                        return;
                    }
                }
                BaseCustomDfuImpl.this.mFirmwareUploadInProgress = false;
                BaseCustomDfuImpl.this.mService.sendLogBroadcast(15, "Upload terminated");
            } catch (HexFileValidationException unused) {
                BaseCustomDfuImpl.this.loge("Invalid HEX file");
                BaseCustomDfuImpl.this.mError = 4099;
            } catch (IOException e) {
                BaseCustomDfuImpl.this.loge("Error while reading the input stream", e);
                BaseCustomDfuImpl.this.mError = 4100;
            }
        }

        /* access modifiers changed from: protected */
        public void handleNotification(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            DfuBaseService dfuBaseService = BaseCustomDfuImpl.this.mService;
            dfuBaseService.sendLogBroadcast(5, "Notification received from " + bluetoothGattCharacteristic.getUuid() + ", value (0x): " + parse(bluetoothGattCharacteristic));
            BaseCustomDfuImpl.this.mReceivedData = bluetoothGattCharacteristic.getValue();
            BaseCustomDfuImpl.this.mFirmwareUploadInProgress = false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0052, code lost:
        if (r7 <= 65535) goto L_0x0056;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    BaseCustomDfuImpl(android.content.Intent r7, no.nordicsemi.android.dfu.DfuBaseService r8) {
        /*
            r6 = this;
            r6.<init>(r7, r8)
            java.lang.String r0 = "no.nordicsemi.android.dfu.extra.EXTRA_PRN_ENABLED"
            boolean r0 = r7.hasExtra(r0)
            r1 = 65535(0xffff, float:9.1834E-41)
            r2 = 1
            r3 = 23
            r4 = 0
            r5 = 12
            if (r0 == 0) goto L_0x0032
            java.lang.String r8 = "no.nordicsemi.android.dfu.extra.EXTRA_PRN_ENABLED"
            int r0 = android.os.Build.VERSION.SDK_INT
            if (r0 >= r3) goto L_0x001b
            goto L_0x001c
        L_0x001b:
            r2 = 0
        L_0x001c:
            boolean r8 = r7.getBooleanExtra(r8, r2)
            java.lang.String r0 = "no.nordicsemi.android.dfu.extra.EXTRA_PRN_VALUE"
            int r7 = r7.getIntExtra(r0, r5)
            if (r7 < 0) goto L_0x002a
            if (r7 <= r1) goto L_0x002c
        L_0x002a:
            r7 = 12
        L_0x002c:
            if (r8 != 0) goto L_0x002f
            r7 = 0
        L_0x002f:
            r6.mPacketsBeforeNotification = r7
            goto L_0x005b
        L_0x0032:
            android.content.SharedPreferences r7 = android.preference.PreferenceManager.getDefaultSharedPreferences(r8)
            java.lang.String r8 = "settings_packet_receipt_notification_enabled"
            int r0 = android.os.Build.VERSION.SDK_INT
            if (r0 >= r3) goto L_0x003d
            goto L_0x003e
        L_0x003d:
            r2 = 0
        L_0x003e:
            boolean r8 = r7.getBoolean(r8, r2)
            java.lang.String r0 = "settings_number_of_packets"
            java.lang.String r2 = java.lang.String.valueOf(r5)
            java.lang.String r7 = r7.getString(r0, r2)
            int r7 = java.lang.Integer.parseInt(r7)     // Catch:{ NumberFormatException -> 0x0054 }
            if (r7 < 0) goto L_0x0054
            if (r7 <= r1) goto L_0x0056
        L_0x0054:
            r7 = 12
        L_0x0056:
            if (r8 != 0) goto L_0x0059
            r7 = 0
        L_0x0059:
            r6.mPacketsBeforeNotification = r7
        L_0x005b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.dfu.BaseCustomDfuImpl.<init>(android.content.Intent, no.nordicsemi.android.dfu.DfuBaseService):void");
    }

    /* access modifiers changed from: protected */
    public void writeInitData(BluetoothGattCharacteristic bluetoothGattCharacteristic, CRC32 crc32) throws DfuException, DeviceDisconnectedException, UploadAbortedException {
        try {
            byte[] bArr = this.mBuffer;
            while (true) {
                int read = this.mInitPacketStream.read(bArr, 0, bArr.length);
                if (read != -1) {
                    writeInitPacket(bluetoothGattCharacteristic, bArr, read);
                    if (crc32 != null) {
                        crc32.update(bArr, 0, read);
                    }
                } else {
                    return;
                }
            }
        } catch (IOException e) {
            loge("Error while reading Init packet file", e);
            throw new DfuException("Error while reading Init packet file", 4098);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ac  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void writeInitPacket(android.bluetooth.BluetoothGattCharacteristic r4, byte[] r5, int r6) throws no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException, no.nordicsemi.android.dfu.internal.exception.DfuException, no.nordicsemi.android.dfu.internal.exception.UploadAbortedException {
        /*
            r3 = this;
            boolean r0 = r3.mAborted
            if (r0 != 0) goto L_0x00b6
            int r0 = r5.length
            r1 = 0
            if (r0 == r6) goto L_0x000e
            byte[] r0 = new byte[r6]
            java.lang.System.arraycopy(r5, r1, r0, r1, r6)
            r5 = r0
        L_0x000e:
            r6 = 0
            r3.mReceivedData = r6
            r3.mError = r1
            r6 = 1
            r3.mInitPacketInProgress = r6
            r4.setWriteType(r6)
            r4.setValue(r5)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "Sending init packet (Value = "
            r0.append(r2)
            java.lang.String r5 = r3.parse(r5)
            r0.append(r5)
            java.lang.String r5 = ")"
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            r3.logi(r5)
            no.nordicsemi.android.dfu.DfuBaseService r5 = r3.mService
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "Writing to characteristic "
            r0.append(r2)
            java.util.UUID r2 = r4.getUuid()
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r5.sendLogBroadcast(r6, r0)
            no.nordicsemi.android.dfu.DfuBaseService r5 = r3.mService
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r0 = "gatt.writeCharacteristic("
            r6.append(r0)
            java.util.UUID r0 = r4.getUuid()
            r6.append(r0)
            java.lang.String r0 = ")"
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            r5.sendLogBroadcast(r1, r6)
            android.bluetooth.BluetoothGatt r5 = r3.mGatt
            r5.writeCharacteristic(r4)
            java.lang.Object r4 = r3.mLock     // Catch:{ InterruptedException -> 0x0095 }
            monitor-enter(r4)     // Catch:{ InterruptedException -> 0x0095 }
        L_0x007a:
            boolean r5 = r3.mInitPacketInProgress     // Catch:{ all -> 0x0092 }
            if (r5 == 0) goto L_0x0086
            boolean r5 = r3.mConnected     // Catch:{ all -> 0x0092 }
            if (r5 == 0) goto L_0x0086
            int r5 = r3.mError     // Catch:{ all -> 0x0092 }
            if (r5 == 0) goto L_0x008a
        L_0x0086:
            boolean r5 = r3.mPaused     // Catch:{ all -> 0x0092 }
            if (r5 == 0) goto L_0x0090
        L_0x008a:
            java.lang.Object r5 = r3.mLock     // Catch:{ all -> 0x0092 }
            r5.wait()     // Catch:{ all -> 0x0092 }
            goto L_0x007a
        L_0x0090:
            monitor-exit(r4)     // Catch:{ all -> 0x0092 }
            goto L_0x009b
        L_0x0092:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0092 }
            throw r5     // Catch:{ InterruptedException -> 0x0095 }
        L_0x0095:
            r4 = move-exception
            java.lang.String r5 = "Sleeping interrupted"
            r3.loge(r5, r4)
        L_0x009b:
            int r4 = r3.mError
            if (r4 != 0) goto L_0x00ac
            boolean r4 = r3.mConnected
            if (r4 == 0) goto L_0x00a4
            return
        L_0x00a4:
            no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException r4 = new no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException
            java.lang.String r5 = "Unable to write Init DFU Parameters: device disconnected"
            r4.<init>(r5)
            throw r4
        L_0x00ac:
            no.nordicsemi.android.dfu.internal.exception.DfuException r4 = new no.nordicsemi.android.dfu.internal.exception.DfuException
            int r5 = r3.mError
            java.lang.String r6 = "Unable to write Init DFU Parameters"
            r4.<init>(r6, r5)
            throw r4
        L_0x00b6:
            no.nordicsemi.android.dfu.internal.exception.UploadAbortedException r4 = new no.nordicsemi.android.dfu.internal.exception.UploadAbortedException
            r4.<init>()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.dfu.BaseCustomDfuImpl.writeInitPacket(android.bluetooth.BluetoothGattCharacteristic, byte[], int):void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0074  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void uploadFirmwareImage(android.bluetooth.BluetoothGattCharacteristic r7) throws no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException, no.nordicsemi.android.dfu.internal.exception.DfuException, no.nordicsemi.android.dfu.internal.exception.UploadAbortedException {
        /*
            r6 = this;
            boolean r0 = r6.mAborted
            if (r0 != 0) goto L_0x0092
            r0 = 0
            r6.mReceivedData = r0
            r0 = 0
            r6.mError = r0
            r1 = 1
            r6.mFirmwareUploadInProgress = r1
            r6.mPacketsSentSinceNotification = r0
            byte[] r0 = r6.mBuffer
            java.io.InputStream r2 = r6.mFirmwareStream     // Catch:{ HexFileValidationException -> 0x0088, IOException -> 0x007e }
            int r2 = r2.read(r0)     // Catch:{ HexFileValidationException -> 0x0088, IOException -> 0x007e }
            no.nordicsemi.android.dfu.DfuBaseService r3 = r6.mService     // Catch:{ HexFileValidationException -> 0x0088, IOException -> 0x007e }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ HexFileValidationException -> 0x0088, IOException -> 0x007e }
            r4.<init>()     // Catch:{ HexFileValidationException -> 0x0088, IOException -> 0x007e }
            java.lang.String r5 = "Sending firmware to characteristic "
            r4.append(r5)     // Catch:{ HexFileValidationException -> 0x0088, IOException -> 0x007e }
            java.util.UUID r5 = r7.getUuid()     // Catch:{ HexFileValidationException -> 0x0088, IOException -> 0x007e }
            r4.append(r5)     // Catch:{ HexFileValidationException -> 0x0088, IOException -> 0x007e }
            java.lang.String r5 = "..."
            r4.append(r5)     // Catch:{ HexFileValidationException -> 0x0088, IOException -> 0x007e }
            java.lang.String r4 = r4.toString()     // Catch:{ HexFileValidationException -> 0x0088, IOException -> 0x007e }
            r3.sendLogBroadcast(r1, r4)     // Catch:{ HexFileValidationException -> 0x0088, IOException -> 0x007e }
            android.bluetooth.BluetoothGatt r1 = r6.mGatt     // Catch:{ HexFileValidationException -> 0x0088, IOException -> 0x007e }
            r6.writePacket(r1, r7, r0, r2)     // Catch:{ HexFileValidationException -> 0x0088, IOException -> 0x007e }
            java.lang.Object r7 = r6.mLock     // Catch:{ InterruptedException -> 0x005d }
            monitor-enter(r7)     // Catch:{ InterruptedException -> 0x005d }
        L_0x003e:
            boolean r0 = r6.mFirmwareUploadInProgress     // Catch:{ all -> 0x005a }
            if (r0 == 0) goto L_0x004e
            byte[] r0 = r6.mReceivedData     // Catch:{ all -> 0x005a }
            if (r0 != 0) goto L_0x004e
            boolean r0 = r6.mConnected     // Catch:{ all -> 0x005a }
            if (r0 == 0) goto L_0x004e
            int r0 = r6.mError     // Catch:{ all -> 0x005a }
            if (r0 == 0) goto L_0x0052
        L_0x004e:
            boolean r0 = r6.mPaused     // Catch:{ all -> 0x005a }
            if (r0 == 0) goto L_0x0058
        L_0x0052:
            java.lang.Object r0 = r6.mLock     // Catch:{ all -> 0x005a }
            r0.wait()     // Catch:{ all -> 0x005a }
            goto L_0x003e
        L_0x0058:
            monitor-exit(r7)     // Catch:{ all -> 0x005a }
            goto L_0x0063
        L_0x005a:
            r0 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x005a }
            throw r0     // Catch:{ InterruptedException -> 0x005d }
        L_0x005d:
            r7 = move-exception
            java.lang.String r0 = "Sleeping interrupted"
            r6.loge(r0, r7)
        L_0x0063:
            int r7 = r6.mError
            if (r7 != 0) goto L_0x0074
            boolean r7 = r6.mConnected
            if (r7 == 0) goto L_0x006c
            return
        L_0x006c:
            no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException r7 = new no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException
            java.lang.String r0 = "Uploading Firmware Image failed: device disconnected"
            r7.<init>(r0)
            throw r7
        L_0x0074:
            no.nordicsemi.android.dfu.internal.exception.DfuException r7 = new no.nordicsemi.android.dfu.internal.exception.DfuException
            int r0 = r6.mError
            java.lang.String r1 = "Uploading Firmware Image failed"
            r7.<init>(r1, r0)
            throw r7
        L_0x007e:
            no.nordicsemi.android.dfu.internal.exception.DfuException r7 = new no.nordicsemi.android.dfu.internal.exception.DfuException
            r0 = 4100(0x1004, float:5.745E-42)
            java.lang.String r1 = "Error while reading file"
            r7.<init>(r1, r0)
            throw r7
        L_0x0088:
            no.nordicsemi.android.dfu.internal.exception.DfuException r7 = new no.nordicsemi.android.dfu.internal.exception.DfuException
            r0 = 4099(0x1003, float:5.744E-42)
            java.lang.String r1 = "HEX file not valid"
            r7.<init>(r1, r0)
            throw r7
        L_0x0092:
            no.nordicsemi.android.dfu.internal.exception.UploadAbortedException r7 = new no.nordicsemi.android.dfu.internal.exception.UploadAbortedException
            r7.<init>()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.dfu.BaseCustomDfuImpl.uploadFirmwareImage(android.bluetooth.BluetoothGattCharacteristic):void");
    }

    /* access modifiers changed from: private */
    public void writePacket(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr, int i) {
        if (i > 0) {
            if (bArr.length != i) {
                byte[] bArr2 = new byte[i];
                System.arraycopy(bArr, 0, bArr2, 0, i);
                bArr = bArr2;
            }
            bluetoothGattCharacteristic.setWriteType(1);
            bluetoothGattCharacteristic.setValue(bArr);
            bluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic);
        }
    }

    /* access modifiers changed from: protected */
    public void finalize(Intent intent, boolean z) {
        boolean z2;
        boolean z3 = false;
        boolean booleanExtra = intent.getBooleanExtra(DfuBaseService.EXTRA_KEEP_BOND, false);
        this.mService.refreshDeviceCache(this.mGatt, z || !booleanExtra);
        this.mService.close(this.mGatt);
        if (this.mGatt.getDevice().getBondState() == 12) {
            boolean booleanExtra2 = intent.getBooleanExtra(DfuBaseService.EXTRA_RESTORE_BOND, false);
            if (booleanExtra2 || !booleanExtra) {
                removeBond();
                this.mService.waitFor(2000);
                z2 = true;
            } else {
                z2 = false;
            }
            if (!booleanExtra2 || (this.mFileType & 4) <= 0) {
                z3 = z2;
            } else {
                createBond();
            }
        }
        if (this.mProgressInfo.isLastPart()) {
            if (!z3) {
                this.mService.waitFor(1400);
            }
            this.mProgressInfo.setProgress(-6);
            return;
        }
        logi("Starting service that will upload application");
        Intent intent2 = new Intent();
        intent2.fillIn(intent, 24);
        intent2.putExtra(DfuBaseService.EXTRA_FILE_MIME_TYPE, DfuBaseService.MIME_TYPE_ZIP);
        intent2.putExtra(DfuBaseService.EXTRA_FILE_TYPE, 4);
        intent2.putExtra(DfuBaseService.EXTRA_PART_CURRENT, this.mProgressInfo.getCurrentPart() + 1);
        intent2.putExtra(DfuBaseService.EXTRA_PARTS_TOTAL, this.mProgressInfo.getTotalParts());
        restartService(intent2, true);
    }
}
