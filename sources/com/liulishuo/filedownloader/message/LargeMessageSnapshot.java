package com.liulishuo.filedownloader.message;

import android.os.Parcel;
import com.liulishuo.filedownloader.message.MessageSnapshot;

public abstract class LargeMessageSnapshot extends MessageSnapshot {
    LargeMessageSnapshot(int i) {
        super(i);
        this.f6434a = true;
    }

    LargeMessageSnapshot(Parcel parcel) {
        super(parcel);
    }

    public int j() {
        if (h() > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) h();
    }

    public int k() {
        if (i() > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) i();
    }

    public static class PendingMessageSnapshot extends LargeMessageSnapshot {
        private final long b;
        private final long c;

        public byte b() {
            return 1;
        }

        public int describeContents() {
            return 0;
        }

        PendingMessageSnapshot(PendingMessageSnapshot pendingMessageSnapshot) {
            this(pendingMessageSnapshot.c(), pendingMessageSnapshot.h(), pendingMessageSnapshot.i());
        }

        PendingMessageSnapshot(int i, long j, long j2) {
            super(i);
            this.b = j;
            this.c = j2;
        }

        public long h() {
            return this.b;
        }

        public long i() {
            return this.c;
        }

        public void writeToParcel(Parcel parcel, int i) {
            LargeMessageSnapshot.super.writeToParcel(parcel, i);
            parcel.writeLong(this.b);
            parcel.writeLong(this.c);
        }

        PendingMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.b = parcel.readLong();
            this.c = parcel.readLong();
        }
    }

    public static class ConnectedMessageSnapshot extends LargeMessageSnapshot {
        private final boolean b;
        private final long c;
        private final String d;
        private final String e;

        public byte b() {
            return 2;
        }

        public int describeContents() {
            return 0;
        }

        ConnectedMessageSnapshot(int i, boolean z, long j, String str, String str2) {
            super(i);
            this.b = z;
            this.c = j;
            this.d = str;
            this.e = str2;
        }

        public void writeToParcel(Parcel parcel, int i) {
            LargeMessageSnapshot.super.writeToParcel(parcel, i);
            parcel.writeByte(this.b ? (byte) 1 : 0);
            parcel.writeLong(this.c);
            parcel.writeString(this.d);
            parcel.writeString(this.e);
        }

        ConnectedMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.b = parcel.readByte() != 0;
            this.c = parcel.readLong();
            this.d = parcel.readString();
            this.e = parcel.readString();
        }

        public String n() {
            return this.e;
        }

        public boolean f() {
            return this.b;
        }

        public long i() {
            return this.c;
        }

        public String g() {
            return this.d;
        }
    }

    public static class ProgressMessageSnapshot extends LargeMessageSnapshot {
        private final long b;

        public byte b() {
            return 3;
        }

        public int describeContents() {
            return 0;
        }

        ProgressMessageSnapshot(int i, long j) {
            super(i);
            this.b = j;
        }

        public long h() {
            return this.b;
        }

        public void writeToParcel(Parcel parcel, int i) {
            LargeMessageSnapshot.super.writeToParcel(parcel, i);
            parcel.writeLong(this.b);
        }

        ProgressMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.b = parcel.readLong();
        }
    }

    public static class CompletedFlowDirectlySnapshot extends CompletedSnapshot implements IFlowDirectly {
        CompletedFlowDirectlySnapshot(int i, boolean z, long j) {
            super(i, z, j);
        }

        CompletedFlowDirectlySnapshot(Parcel parcel) {
            super(parcel);
        }
    }

    public static class CompletedSnapshot extends LargeMessageSnapshot {
        private final boolean b;
        private final long c;

        public byte b() {
            return -3;
        }

        public int describeContents() {
            return 0;
        }

        CompletedSnapshot(int i, boolean z, long j) {
            super(i);
            this.b = z;
            this.c = j;
        }

        public void writeToParcel(Parcel parcel, int i) {
            LargeMessageSnapshot.super.writeToParcel(parcel, i);
            parcel.writeByte(this.b ? (byte) 1 : 0);
            parcel.writeLong(this.c);
        }

        CompletedSnapshot(Parcel parcel) {
            super(parcel);
            this.b = parcel.readByte() != 0;
            this.c = parcel.readLong();
        }

        public long i() {
            return this.c;
        }

        public boolean l() {
            return this.b;
        }
    }

    public static class ErrorMessageSnapshot extends LargeMessageSnapshot {
        private final long b;
        private final Throwable c;

        public byte b() {
            return -1;
        }

        public int describeContents() {
            return 0;
        }

        ErrorMessageSnapshot(int i, long j, Throwable th) {
            super(i);
            this.b = j;
            this.c = th;
        }

        public long h() {
            return this.b;
        }

        public Throwable d() {
            return this.c;
        }

        public void writeToParcel(Parcel parcel, int i) {
            LargeMessageSnapshot.super.writeToParcel(parcel, i);
            parcel.writeLong(this.b);
            parcel.writeSerializable(this.c);
        }

        ErrorMessageSnapshot(Parcel parcel) {
            super(parcel);
            this.b = parcel.readLong();
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

        RetryMessageSnapshot(int i, long j, Throwable th, int i2) {
            super(i, j, th);
            this.b = i2;
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
        WarnFlowDirectlySnapshot(int i, long j, long j2) {
            super(i, j, j2);
        }

        WarnFlowDirectlySnapshot(Parcel parcel) {
            super(parcel);
        }
    }

    public static class WarnMessageSnapshot extends PendingMessageSnapshot implements MessageSnapshot.IWarnMessageSnapshot {
        public byte b() {
            return -4;
        }

        WarnMessageSnapshot(int i, long j, long j2) {
            super(i, j, j2);
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

        PausedSnapshot(int i, long j, long j2) {
            super(i, j, j2);
        }
    }
}
