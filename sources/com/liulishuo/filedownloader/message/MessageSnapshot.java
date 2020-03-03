package com.liulishuo.filedownloader.message;

import android.os.Parcel;
import android.os.Parcelable;
import com.liulishuo.filedownloader.message.LargeMessageSnapshot;
import com.liulishuo.filedownloader.message.SmallMessageSnapshot;
import com.liulishuo.filedownloader.util.FileDownloadUtils;

public abstract class MessageSnapshot implements Parcelable, IMessageSnapshot {
    public static final Parcelable.Creator<MessageSnapshot> CREATOR = new Parcelable.Creator<MessageSnapshot>() {
        /* renamed from: a */
        public MessageSnapshot createFromParcel(Parcel parcel) {
            MessageSnapshot messageSnapshot;
            boolean z = true;
            if (parcel.readByte() != 1) {
                z = false;
            }
            byte readByte = parcel.readByte();
            switch (readByte) {
                case -4:
                    if (!z) {
                        messageSnapshot = new SmallMessageSnapshot.WarnMessageSnapshot(parcel);
                        break;
                    } else {
                        messageSnapshot = new LargeMessageSnapshot.WarnMessageSnapshot(parcel);
                        break;
                    }
                case -3:
                    if (!z) {
                        messageSnapshot = new SmallMessageSnapshot.CompletedSnapshot(parcel);
                        break;
                    } else {
                        messageSnapshot = new LargeMessageSnapshot.CompletedSnapshot(parcel);
                        break;
                    }
                case -1:
                    if (!z) {
                        messageSnapshot = new SmallMessageSnapshot.ErrorMessageSnapshot(parcel);
                        break;
                    } else {
                        messageSnapshot = new LargeMessageSnapshot.ErrorMessageSnapshot(parcel);
                        break;
                    }
                case 1:
                    if (!z) {
                        messageSnapshot = new SmallMessageSnapshot.PendingMessageSnapshot(parcel);
                        break;
                    } else {
                        messageSnapshot = new LargeMessageSnapshot.PendingMessageSnapshot(parcel);
                        break;
                    }
                case 2:
                    if (!z) {
                        messageSnapshot = new SmallMessageSnapshot.ConnectedMessageSnapshot(parcel);
                        break;
                    } else {
                        messageSnapshot = new LargeMessageSnapshot.ConnectedMessageSnapshot(parcel);
                        break;
                    }
                case 3:
                    if (!z) {
                        messageSnapshot = new SmallMessageSnapshot.ProgressMessageSnapshot(parcel);
                        break;
                    } else {
                        messageSnapshot = new LargeMessageSnapshot.ProgressMessageSnapshot(parcel);
                        break;
                    }
                case 5:
                    if (!z) {
                        messageSnapshot = new SmallMessageSnapshot.RetryMessageSnapshot(parcel);
                        break;
                    } else {
                        messageSnapshot = new LargeMessageSnapshot.RetryMessageSnapshot(parcel);
                        break;
                    }
                case 6:
                    messageSnapshot = new StartedMessageSnapshot(parcel);
                    break;
                default:
                    messageSnapshot = null;
                    break;
            }
            if (messageSnapshot != null) {
                messageSnapshot.f6434a = z;
                return messageSnapshot;
            }
            throw new IllegalStateException("Can't restore the snapshot because unknown status: " + readByte);
        }

        /* renamed from: a */
        public MessageSnapshot[] newArray(int i) {
            return new MessageSnapshot[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    protected boolean f6434a;
    private final int b;

    public interface IWarnMessageSnapshot {
        MessageSnapshot a();
    }

    public int describeContents() {
        return 0;
    }

    MessageSnapshot(int i) {
        this.b = i;
    }

    public int c() {
        return this.b;
    }

    public Throwable d() {
        throw new NoFieldException("getThrowable", this);
    }

    public int e() {
        throw new NoFieldException("getRetryingTimes", this);
    }

    public boolean f() {
        throw new NoFieldException("isResuming", this);
    }

    public String g() {
        throw new NoFieldException("getEtag", this);
    }

    public long h() {
        throw new NoFieldException("getLargeSofarBytes", this);
    }

    public long i() {
        throw new NoFieldException("getLargeTotalBytes", this);
    }

    public int j() {
        throw new NoFieldException("getSmallSofarBytes", this);
    }

    public int k() {
        throw new NoFieldException("getSmallTotalBytes", this);
    }

    public boolean l() {
        throw new NoFieldException("isReusedDownloadedFile", this);
    }

    public String n() {
        throw new NoFieldException("getFileName", this);
    }

    public boolean m() {
        return this.f6434a;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.f6434a ? (byte) 1 : 0);
        parcel.writeByte(b());
        parcel.writeInt(this.b);
    }

    MessageSnapshot(Parcel parcel) {
        this.b = parcel.readInt();
    }

    public static class NoFieldException extends IllegalStateException {
        NoFieldException(String str, MessageSnapshot messageSnapshot) {
            super(FileDownloadUtils.a("There isn't a field for '%s' in this message %d %d %s", str, Integer.valueOf(messageSnapshot.c()), Byte.valueOf(messageSnapshot.b()), messageSnapshot.getClass().getName()));
        }
    }

    public static class StartedMessageSnapshot extends MessageSnapshot {
        public byte b() {
            return 6;
        }

        StartedMessageSnapshot(int i) {
            super(i);
        }

        StartedMessageSnapshot(Parcel parcel) {
            super(parcel);
        }
    }
}
