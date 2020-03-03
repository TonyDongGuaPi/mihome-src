package com.liulishuo.filedownloader.message;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.download.DownloadStatusCallback;
import com.liulishuo.filedownloader.message.BlockCompleteMessage;
import com.liulishuo.filedownloader.message.LargeMessageSnapshot;
import com.liulishuo.filedownloader.message.MessageSnapshot;
import com.liulishuo.filedownloader.message.SmallMessageSnapshot;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.io.File;

public class MessageSnapshotTaker {
    public static MessageSnapshot a(byte b, FileDownloadModel fileDownloadModel) {
        return a(b, fileDownloadModel, (DownloadStatusCallback.ProcessParams) null);
    }

    public static MessageSnapshot a(int i, File file, boolean z) {
        long length = file.length();
        if (length > 2147483647L) {
            if (z) {
                return new LargeMessageSnapshot.CompletedFlowDirectlySnapshot(i, true, length);
            }
            return new LargeMessageSnapshot.CompletedSnapshot(i, true, length);
        } else if (z) {
            return new SmallMessageSnapshot.CompletedFlowDirectlySnapshot(i, true, (int) length);
        } else {
            return new SmallMessageSnapshot.CompletedSnapshot(i, true, (int) length);
        }
    }

    public static MessageSnapshot a(int i, long j, long j2, boolean z) {
        if (j2 > 2147483647L) {
            if (z) {
                return new LargeMessageSnapshot.WarnFlowDirectlySnapshot(i, j, j2);
            }
            return new LargeMessageSnapshot.WarnMessageSnapshot(i, j, j2);
        } else if (z) {
            return new SmallMessageSnapshot.WarnFlowDirectlySnapshot(i, (int) j, (int) j2);
        } else {
            return new SmallMessageSnapshot.WarnMessageSnapshot(i, (int) j, (int) j2);
        }
    }

    public static MessageSnapshot a(int i, long j, Throwable th) {
        if (j > 2147483647L) {
            return new LargeMessageSnapshot.ErrorMessageSnapshot(i, j, th);
        }
        return new SmallMessageSnapshot.ErrorMessageSnapshot(i, (int) j, th);
    }

    public static MessageSnapshot a(BaseDownloadTask baseDownloadTask) {
        if (baseDownloadTask.N()) {
            return new LargeMessageSnapshot.PausedSnapshot(baseDownloadTask.k(), baseDownloadTask.w(), baseDownloadTask.z());
        }
        return new SmallMessageSnapshot.PausedSnapshot(baseDownloadTask.k(), baseDownloadTask.v(), baseDownloadTask.y());
    }

    public static MessageSnapshot a(MessageSnapshot messageSnapshot) {
        if (messageSnapshot.b() == -3) {
            return new BlockCompleteMessage.BlockCompleteMessageImpl(messageSnapshot);
        }
        throw new IllegalStateException(FileDownloadUtils.a("take block completed snapshot, must has already be completed. %d %d", Integer.valueOf(messageSnapshot.c()), Byte.valueOf(messageSnapshot.b())));
    }

    public static MessageSnapshot a(byte b, FileDownloadModel fileDownloadModel, DownloadStatusCallback.ProcessParams processParams) {
        IllegalStateException illegalStateException;
        MessageSnapshot errorMessageSnapshot;
        int a2 = fileDownloadModel.a();
        if (b != -4) {
            switch (b) {
                case -3:
                    if (fileDownloadModel.q()) {
                        return new LargeMessageSnapshot.CompletedSnapshot(a2, false, fileDownloadModel.h());
                    }
                    return new SmallMessageSnapshot.CompletedSnapshot(a2, false, (int) fileDownloadModel.h());
                case -1:
                    if (fileDownloadModel.q()) {
                        return new LargeMessageSnapshot.ErrorMessageSnapshot(a2, fileDownloadModel.g(), processParams.b());
                    }
                    return new SmallMessageSnapshot.ErrorMessageSnapshot(a2, (int) fileDownloadModel.g(), processParams.b());
                case 1:
                    if (fileDownloadModel.q()) {
                        return new LargeMessageSnapshot.PendingMessageSnapshot(a2, fileDownloadModel.g(), fileDownloadModel.h());
                    }
                    return new SmallMessageSnapshot.PendingMessageSnapshot(a2, (int) fileDownloadModel.g(), (int) fileDownloadModel.h());
                case 2:
                    String m = fileDownloadModel.l() ? fileDownloadModel.m() : null;
                    if (fileDownloadModel.q()) {
                        return new LargeMessageSnapshot.ConnectedMessageSnapshot(a2, processParams.a(), fileDownloadModel.h(), fileDownloadModel.j(), m);
                    }
                    return new SmallMessageSnapshot.ConnectedMessageSnapshot(a2, processParams.a(), (int) fileDownloadModel.h(), fileDownloadModel.j(), m);
                case 3:
                    if (fileDownloadModel.q()) {
                        return new LargeMessageSnapshot.ProgressMessageSnapshot(a2, fileDownloadModel.g());
                    }
                    return new SmallMessageSnapshot.ProgressMessageSnapshot(a2, (int) fileDownloadModel.g());
                case 5:
                    if (fileDownloadModel.q()) {
                        return new LargeMessageSnapshot.RetryMessageSnapshot(a2, fileDownloadModel.g(), processParams.b(), processParams.c());
                    }
                    return new SmallMessageSnapshot.RetryMessageSnapshot(a2, (int) fileDownloadModel.g(), processParams.b(), processParams.c());
                case 6:
                    return new MessageSnapshot.StartedMessageSnapshot(a2);
                default:
                    String a3 = FileDownloadUtils.a("it can't takes a snapshot for the task(%s) when its status is %d,", fileDownloadModel, Byte.valueOf(b));
                    FileDownloadLog.d(MessageSnapshotTaker.class, "it can't takes a snapshot for the task(%s) when its status is %d,", fileDownloadModel, Byte.valueOf(b));
                    if (processParams.b() != null) {
                        illegalStateException = new IllegalStateException(a3, processParams.b());
                    } else {
                        illegalStateException = new IllegalStateException(a3);
                    }
                    if (fileDownloadModel.q()) {
                        errorMessageSnapshot = new LargeMessageSnapshot.ErrorMessageSnapshot(a2, fileDownloadModel.g(), illegalStateException);
                    } else {
                        errorMessageSnapshot = new SmallMessageSnapshot.ErrorMessageSnapshot(a2, (int) fileDownloadModel.g(), illegalStateException);
                    }
                    return errorMessageSnapshot;
            }
        } else {
            throw new IllegalStateException(FileDownloadUtils.a("please use #catchWarn instead %d", Integer.valueOf(a2)));
        }
    }
}
