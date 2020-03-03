package no.nordicsemi.android.dfu;

import android.bluetooth.BluetoothGatt;
import android.content.Intent;
import no.nordicsemi.android.dfu.DfuCallback;
import no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException;
import no.nordicsemi.android.dfu.internal.exception.DfuException;
import no.nordicsemi.android.dfu.internal.exception.UploadAbortedException;

class DfuServiceProvider implements DfuCallback {
    private boolean mAborted;
    private BaseDfuImpl mImpl;
    private boolean mPaused;

    DfuServiceProvider() {
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public DfuService getServiceImpl(Intent intent, DfuBaseService dfuBaseService, BluetoothGatt bluetoothGatt) throws DfuException, DeviceDisconnectedException, UploadAbortedException {
        try {
            this.mImpl = new ButtonlessDfuWithBondSharingImpl(intent, dfuBaseService);
            if (this.mImpl.isClientCompatible(intent, bluetoothGatt)) {
                BaseDfuImpl baseDfuImpl = this.mImpl;
                if (this.mImpl != null) {
                    if (this.mPaused) {
                        this.mImpl.pause();
                    }
                    if (this.mAborted) {
                        this.mImpl.abort();
                    }
                }
                return baseDfuImpl;
            }
            this.mImpl = new ButtonlessDfuWithoutBondSharingImpl(intent, dfuBaseService);
            if (this.mImpl.isClientCompatible(intent, bluetoothGatt)) {
                BaseDfuImpl baseDfuImpl2 = this.mImpl;
                if (this.mImpl != null) {
                    if (this.mPaused) {
                        this.mImpl.pause();
                    }
                    if (this.mAborted) {
                        this.mImpl.abort();
                    }
                }
                return baseDfuImpl2;
            }
            this.mImpl = new SecureDfuImpl(intent, dfuBaseService);
            if (this.mImpl.isClientCompatible(intent, bluetoothGatt)) {
                BaseDfuImpl baseDfuImpl3 = this.mImpl;
                if (this.mImpl != null) {
                    if (this.mPaused) {
                        this.mImpl.pause();
                    }
                    if (this.mAborted) {
                        this.mImpl.abort();
                    }
                }
                return baseDfuImpl3;
            }
            this.mImpl = new LegacyButtonlessDfuImpl(intent, dfuBaseService);
            if (this.mImpl.isClientCompatible(intent, bluetoothGatt)) {
                BaseDfuImpl baseDfuImpl4 = this.mImpl;
                if (this.mImpl != null) {
                    if (this.mPaused) {
                        this.mImpl.pause();
                    }
                    if (this.mAborted) {
                        this.mImpl.abort();
                    }
                }
                return baseDfuImpl4;
            }
            this.mImpl = new LegacyDfuImpl(intent, dfuBaseService);
            if (this.mImpl.isClientCompatible(intent, bluetoothGatt)) {
                BaseDfuImpl baseDfuImpl5 = this.mImpl;
                if (this.mImpl != null) {
                    if (this.mPaused) {
                        this.mImpl.pause();
                    }
                    if (this.mAborted) {
                        this.mImpl.abort();
                    }
                }
                return baseDfuImpl5;
            }
            if (intent.getBooleanExtra(DfuBaseService.EXTRA_UNSAFE_EXPERIMENTAL_BUTTONLESS_DFU, false)) {
                this.mImpl = new ExperimentalButtonlessDfuImpl(intent, dfuBaseService);
                if (this.mImpl.isClientCompatible(intent, bluetoothGatt)) {
                    BaseDfuImpl baseDfuImpl6 = this.mImpl;
                    if (this.mImpl != null) {
                        if (this.mPaused) {
                            this.mImpl.pause();
                        }
                        if (this.mAborted) {
                            this.mImpl.abort();
                        }
                    }
                    return baseDfuImpl6;
                }
            }
            if (this.mImpl != null) {
                if (this.mPaused) {
                    this.mImpl.pause();
                }
                if (this.mAborted) {
                    this.mImpl.abort();
                }
            }
            return null;
        } catch (Throwable th) {
            if (this.mImpl != null) {
                if (this.mPaused) {
                    this.mImpl.pause();
                }
                if (this.mAborted) {
                    this.mImpl.abort();
                }
            }
            throw th;
        }
    }

    public DfuCallback.DfuGattCallback getGattCallback() {
        if (this.mImpl != null) {
            return this.mImpl.getGattCallback();
        }
        return null;
    }

    public void onBondStateChanged(int i) {
        if (this.mImpl != null) {
            this.mImpl.onBondStateChanged(i);
        }
    }

    public void pause() {
        this.mPaused = true;
    }

    public void resume() {
        this.mPaused = false;
    }

    public void abort() {
        this.mAborted = true;
        if (this.mImpl != null) {
            this.mImpl.abort();
        }
    }
}
