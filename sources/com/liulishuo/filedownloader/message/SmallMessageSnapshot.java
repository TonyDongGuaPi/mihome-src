package com.liulishuo.filedownloader.message;

import android.os.Parcel;
import com.liulishuo.filedownloader.message.MessageSnapshot;

public abstract class SmallMessageSnapshot extends MessageSnapshot {
    SmallMessageSnapshot(int i) {
        super(i);
        this.f6434a = false;
    }

    SmallMessageSnapshot(Parcel parcel) {
        super(parcel);
    }

    public long i() {
        return (long) k();
    }

    public long h() {
        return (long) j();
    }

    public static class PendingMessageSnapshot extends SmallMessageSnapshot {
        private final int b;
        private final int c;

        public byte b() {
            return 1;
        }

        PendingMessageSnapshot(PendingMessageSnapshot pendingMessageSnapshot) {
            this(pendingMessageSnapshot.c(), pendingMessageSnapshot.j(), pendingMessageSnapshot.k());
        }

        PendingMessageSnapshot(int i, int i2, int i3) {
            super(i);
            this.b = i2;
            this.c = i3;
        }

        PendingMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.b = parcel.readInt();
            this.c = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            SmallMessageSnapshot.super.writeToParcel(parcel, i);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
        }

        public int j() {
            return this.b;
        }

        public int k() {
            return this.c;
        }
    }

    public static class ConnectedMessageSnapshot extends SmallMessageSnapshot {
        private final boolean b;
        private final int c;
        private final String d;
        private final String e;

        public byte b() {
            return 2;
        }

        public int describeContents() {
            return 0;
        }

        ConnectedMessageSnapshot(int i, boolean z, int i2, String str, String str2) {
            super(i);
            this.b = z;
            this.c = i2;
            this.d = str;
            this.e = str2;
        }

        public void writeToParcel(Parcel parcel, int i) {
            SmallMessageSnapshot.super.writeToParcel(parcel, i);
            parcel.writeByte(this.b ? (byte) 1 : 0);
            parcel.writeInt(this.c);
            parcel.writeString(this.d);
            parcel.writeString(this.e);
        }

        ConnectedMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.b = parcel.readByte() != 0;
            this.c = parcel.readInt();
            this.d = parcel.readString();
            this.e = parcel.readString();
        }

        public String n() {
            return this.e;
        }

        public boolean f() {
            return this.b;
        }

        public int k() {
            return this.c;
        }

        public String g() {
            return this.d;
        }
    }

    public static class ProgressMessageSnapshot extends SmallMessageSnapshot {
        private final int b;

        public byte b() {
            return 3;
        }

        public int describeContents() {
            return 0;
        }

        ProgressMessageSnapshot(int i, int i2) {
            super(i);
            this.b = i2;
        }

        public int j() {
            return this.b;
        }

        public void writeToParcel(Parcel parcel, int i) {
            SmallMessageSnapshot.super.writeToParcel(parcel, i);
            parcel.writeInt(this.b);
        }

        ProgressMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.b = parcel.readInt();
        }
    }

    public static class CompletedFlowDirectlySnapshot extends CompletedSnapshot implements IFlowDirectly {
        CompletedFlowDirectlySnapshot(int i, boolean z, int i2) {
            super(i, z, i2);
        }

        CompletedFlowDirectlySnapshot(Parcel parcel) {
            super(parcel);
        }
    }

    public static class CompletedSnapshot extends SmallMessageSnapshot {
        private final boolean b;
        private final int c;

        public byte b() {
            return -3;
        }

        public int describeContents() {
            return 0;
        }

        CompletedSnapshot(int i, boolean z, int i2) {
            super(i);
            this.b = z;
            this.c = i2;
        }

        public void writeToParcel(Parcel parcel, int i) {
            SmallMessageSnapshot.super.writeToParcel(parcel, i);
            parcel.writeByte(this.b ? (byte) 1 : 0);
            parcel.writeInt(this.c);
        }

        CompletedSnapshot(Parcel parcel) {
            super(parcel);
            this.b = parcel.readByte() != 0;
            this.c = parcel.readInt();
        }

        public int k() {
            return this.c;
        }

        public boolean l() {
            return this.b;
        }
    }

    public static class ErrorMessageSnapshot extends SmallMessageSnapshot {
        private final int b;
        private final Throwable c;

        public byte b() {
            return -1;
        }

        public int describeContents() {
            return 0;
        }

        ErrorMessageSnapshot(int i, int i2, Throwable th) {
            super(i);
            this.b = i2;
            this.c = th;
        }

        public int j() {
            return this.b;
        }

        public Throwable d() {
            return this.c;
        }

        public void writeToParcel(Parcel parcel, int i) {
            SmallMessageSnapshot.super.writeToParcel(parcel, i);
            parcel.writeInt(this.b);
            parcel.writeSerializable(this.c);
        }

        ErrorMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.b = parcel.readInt();
            this.c = (Throwable) parcel.readSerializable();
        }
    }

    public static class RetryMessageSnapshot extends ErrorMessageSnapshot {
        private final int b;

        public byte b() {
            return 5;
        }

        public int describeContents() {
            return 0;
        }

        RetryMessageSnapshot(int i, int i2, Throwable th, int i3) {
            super(i, i2, th);
            this.b = i3;
        }

        public int e() {
            return this.b;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.b);
        }

        RetryMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.b = parcel.readInt();
        }
    }

    public static class WarnFlowDirectlySnapshot extends WarnMessageSnapshot implements IFlowDirectly {
        WarnFlowDirectlySnapshot(int i, int i2, int i3) {
            super(i, i2, i3);
        }

        WarnFlowDirectlySnapshot(Parcel parcel) {
            super(parcel);
        }
    }

    public static class WarnMessageSnapshot extends PendingMessageSnapshot implements MessageSnapshot.IWarnMessageSnapshot {
        public byte b() {
            return -4;
        }

        WarnMessageSnapshot(int i, int i2, int i3) {
            super(i, i2, i3);
        }

        WarnMessageSnapshot(Parcel parcel) {
            super(parcel);
        }

        public MessageSnapshot a() {
            return new PendingMessageSnapshot((PendingMessageSnapshot) this);
        }
    }

    public static class PausedSnapshot extends PendingMessageSnapshot {
        public byte b() {
            return -2;
        }

        PausedSnapshot(int i, int i2, int i3) {
            super(i, i2, i3);
        }
    }
}
