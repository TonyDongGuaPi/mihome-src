package miui.bluetooth.ble;

import android.os.ParcelUuid;

public class MiServiceData {
    public static final byte CAPABILITY_CENTRAL = 2;
    public static final byte CAPABILITY_CONNECTABLE = 1;
    public static final byte CAPABILITY_ENCRYPT = 4;
    public static final byte CAPABILITY_IO = 24;
    private static final int DATA_MIN_LENGTH = 5;
    private static final int FLAG_BINDING = 512;
    private static final int FLAG_CAPABILITY = 32;
    private static final int FLAG_CENTRAL = 4;
    private static final int FLAG_CONNECTED = 2;
    private static final int FLAG_CUSTOM_DATA = 128;
    private static final int FLAG_ENCRYPTED = 8;
    private static final int FLAG_EVENT = 64;
    private static final int FLAG_MAC_ADDRESS = 16;
    private static final int FLAG_NEW_FACTORY = 1;
    private static final int FLAG_SUBTITLE = 256;
    public static final ParcelUuid MI_SERVICE_UUID = ParcelUuid.fromString(String.format(UUID_BASE, new Object[]{"fe95"}));
    private static final String UUID_BASE = "0000%4s-0000-1000-8000-00805f9b34fb";
    private byte[] mData;
    private int mFrameControl;
    private int mVersion;

    public static MiServiceData fromScanRecord(ScanRecord scanRecord) {
        byte[] serviceData;
        if (scanRecord == null || scanRecord.getServiceData() == null || (serviceData = scanRecord.getServiceData(MI_SERVICE_UUID)) == null || serviceData.length < 5) {
            return null;
        }
        return new MiServiceData(serviceData);
    }

    public MiServiceData(byte[] bArr) {
        if (bArr == null || bArr.length < 5) {
            throw new IllegalArgumentException("Mi Service data length must >= 5");
        }
        this.mData = bArr;
        this.mFrameControl = (this.mData[0] & 255) | ((this.mData[1] & 255) << 8);
        this.mVersion = (this.mData[1] & 240) >> 4;
    }

    public byte[] getData() {
        return this.mData;
    }

    public boolean isNewFactory() {
        return (this.mFrameControl & 1) != 0;
    }

    public boolean isConnected() {
        return (this.mFrameControl & 2) != 0;
    }

    public boolean isCentral() {
        return (this.mFrameControl & 4) != 0;
    }

    public boolean isEncrypted() {
        return (this.mFrameControl & 8) != 0;
    }

    public boolean hasMacAddress() {
        return (this.mFrameControl & 16) != 0;
    }

    public byte[] getMacAddress() {
        if (!hasMacAddress() || this.mData.length < 11) {
            return null;
        }
        byte[] bArr = new byte[6];
        System.arraycopy(this.mData, 5, bArr, 0, 6);
        return bArr;
    }

    public boolean hasCapability() {
        return (this.mFrameControl & 32) != 0;
    }

    public byte getCapability() {
        if (!hasCapability()) {
            return 0;
        }
        int i = 5;
        if (hasMacAddress()) {
            i = 11;
        }
        if (this.mData.length >= i + 6) {
            return this.mData[i];
        }
        return 0;
    }

    public boolean hasEvent() {
        return (this.mFrameControl & 64) != 0;
    }

    private int getEventDataIndex() {
        int i = hasMacAddress() ? 11 : 5;
        return hasCapability() ? i + 1 : i;
    }

    public int getEventID() {
        if (!hasEvent()) {
            return 0;
        }
        int eventDataIndex = getEventDataIndex();
        return ((this.mData[eventDataIndex + 1] & 255) << 8) | (this.mData[eventDataIndex] & 255);
    }

    public byte getEvent() {
        if (!hasEvent()) {
            return 0;
        }
        int eventDataIndex = getEventDataIndex();
        if (this.mData.length >= eventDataIndex + 3) {
            return this.mData[eventDataIndex + 2];
        }
        return 0;
    }

    public boolean hasCustomData() {
        return (this.mFrameControl & 128) != 0;
    }

    public byte[] getCustomData() {
        if (!hasCustomData()) {
            return null;
        }
        int i = 5;
        if (hasMacAddress()) {
            i = 11;
        }
        if (hasCapability()) {
            i++;
        }
        if (hasEvent()) {
            i += 3;
        }
        if (this.mData.length <= i) {
            return null;
        }
        byte b = this.mData[i];
        byte[] bArr = new byte[b];
        System.arraycopy(this.mData, i + 1, bArr, 0, b);
        return bArr;
    }

    public boolean hasSubTitle() {
        return (this.mFrameControl & 256) != 0;
    }

    public boolean isBindingFrame() {
        return (this.mFrameControl & 512) != 0;
    }

    public int getProductID() {
        return (this.mData[2] & 255) | ((this.mData[3] & 255) << 8);
    }

    public int getFrameCounter() {
        return this.mData[4] & 255;
    }
}
