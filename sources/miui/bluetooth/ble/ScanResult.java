package miui.bluetooth.ble;

import android.bluetooth.BluetoothDevice;
import android.os.Parcel;
import android.os.Parcelable;
import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;

public final class ScanResult implements Parcelable {
    public static final Parcelable.Creator<ScanResult> CREATOR = new Parcelable.Creator<ScanResult>() {
        public ScanResult createFromParcel(Parcel parcel) {
            return new ScanResult(parcel);
        }

        public ScanResult[] newArray(int i) {
            return new ScanResult[i];
        }
    };
    private BluetoothDevice mDevice;
    private int mRssi;
    private ScanRecord mScanRecord;
    private long mTimestampNanos;
    private int mType;

    public int describeContents() {
        return 0;
    }

    public ScanResult(BluetoothDevice bluetoothDevice, ScanRecord scanRecord, int i, long j, int i2) {
        this.mType = 0;
        this.mDevice = bluetoothDevice;
        this.mScanRecord = scanRecord;
        this.mRssi = i;
        this.mTimestampNanos = j;
        this.mType = i2;
    }

    private ScanResult(Parcel parcel) {
        this.mType = 0;
        readFromParcel(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.mDevice != null) {
            parcel.writeInt(1);
            this.mDevice.writeToParcel(parcel, i);
        } else {
            parcel.writeInt(0);
        }
        if (this.mScanRecord != null) {
            parcel.writeInt(1);
            parcel.writeByteArray(this.mScanRecord.getBytes());
        } else {
            parcel.writeInt(0);
        }
        parcel.writeInt(this.mRssi);
        parcel.writeLong(this.mTimestampNanos);
    }

    private void readFromParcel(Parcel parcel) {
        if (parcel.readInt() == 1) {
            this.mDevice = (BluetoothDevice) BluetoothDevice.CREATOR.createFromParcel(parcel);
        }
        if (parcel.readInt() == 1) {
            this.mScanRecord = ScanRecord.parseFromBytes(parcel.createByteArray());
        }
        this.mRssi = parcel.readInt();
        this.mTimestampNanos = parcel.readLong();
    }

    public BluetoothDevice getDevice() {
        return this.mDevice;
    }

    public ScanRecord getScanRecord() {
        return this.mScanRecord;
    }

    public int getRssi() {
        return this.mRssi;
    }

    public long getTimestampNanos() {
        return this.mTimestampNanos;
    }

    public int getDeviceType() {
        return this.mType;
    }

    public int hashCode() {
        return hash(this.mDevice, Integer.valueOf(this.mRssi), this.mScanRecord, Long.valueOf(this.mTimestampNanos));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ScanResult scanResult = (ScanResult) obj;
        if (!equals(this.mDevice, scanResult.mDevice) || this.mRssi != scanResult.mRssi || !equals(this.mScanRecord, scanResult.mScanRecord) || this.mTimestampNanos != scanResult.mTimestampNanos) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "ScanResult{mDevice=" + this.mDevice + ", mScanRecord=" + toString(this.mScanRecord) + ", mRssi=" + this.mRssi + ", mTimestampNanos=" + this.mTimestampNanos + Operators.BLOCK_END;
    }

    private static int hash(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    public static boolean equals(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    public static String toString(Object obj) {
        return obj == null ? "null" : obj.toString();
    }
}
