package com.liulishuo.filedownloader.util;

import android.annotation.SuppressLint;
import android.content.Context;
import com.liulishuo.filedownloader.IThreadPoolMonitor;
import com.liulishuo.filedownloader.connection.FileDownloadConnection;
import com.liulishuo.filedownloader.exception.PathConflictException;
import com.liulishuo.filedownloader.message.MessageSnapshotFlow;
import com.liulishuo.filedownloader.message.MessageSnapshotTaker;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.liulishuo.filedownloader.services.FileDownloadDatabase;
import com.liulishuo.filedownloader.stream.FileDownloadOutputStream;
import java.io.File;
import java.io.IOException;

public class FileDownloadHelper {
    @SuppressLint({"StaticFieldLeak"})

    /* renamed from: a  reason: collision with root package name */
    private static Context f6464a;

    public interface ConnectionCountAdapter {
        int a(int i, String str, String str2, long j);
    }

    public interface ConnectionCreator {
        FileDownloadConnection a(String str) throws IOException;
    }

    public interface DatabaseCustomMaker {
        FileDownloadDatabase a();
    }

    public interface IdGenerator {
        int a(int i, String str, String str2, boolean z);

        int a(String str, String str2, boolean z);
    }

    public interface OutputStreamCreator {
        FileDownloadOutputStream a(File file) throws IOException;

        boolean a();
    }

    public static void a(Context context) {
        f6464a = context;
    }

    public static Context a() {
        return f6464a;
    }

    public static boolean a(int i, String str, boolean z, boolean z2) {
        if (!z && str != null) {
            File file = new File(str);
            if (file.exists()) {
                MessageSnapshotFlow.a().a(MessageSnapshotTaker.a(i, file, z2));
                return true;
            }
        }
        return false;
    }

    public static boolean a(int i, FileDownloadModel fileDownloadModel, IThreadPoolMonitor iThreadPoolMonitor, boolean z) {
        if (!iThreadPoolMonitor.a(fileDownloadModel)) {
            return false;
        }
        MessageSnapshotFlow.a().a(MessageSnapshotTaker.a(i, fileDownloadModel.g(), fileDownloadModel.h(), z));
        return true;
    }

    public static boolean a(int i, long j, String str, String str2, IThreadPoolMonitor iThreadPoolMonitor) {
        int a2;
        if (str2 == null || str == null || (a2 = iThreadPoolMonitor.a(str, i)) == 0) {
            return false;
        }
        MessageSnapshotFlow.a().a(MessageSnapshotTaker.a(i, j, (Throwable) new PathConflictException(a2, str, str2)));
        return true;
    }
}
