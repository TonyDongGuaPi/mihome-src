package com.liulishuo.filedownloader.services;

import com.liulishuo.filedownloader.model.ConnectionModel;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import java.util.List;

public interface FileDownloadDatabase {

    public interface Maintainer extends Iterable<FileDownloadModel> {
        void a();

        void a(int i, FileDownloadModel fileDownloadModel);

        void a(FileDownloadModel fileDownloadModel);

        void b(FileDownloadModel fileDownloadModel);
    }

    FileDownloadModel a(int i);

    void a();

    void a(int i, int i2);

    void a(int i, int i2, long j);

    void a(int i, long j);

    void a(int i, long j, String str, String str2);

    void a(int i, String str, long j, long j2, int i2);

    void a(int i, Throwable th);

    void a(int i, Throwable th, long j);

    void a(ConnectionModel connectionModel);

    void a(FileDownloadModel fileDownloadModel);

    Maintainer b();

    List<ConnectionModel> b(int i);

    void b(int i, long j);

    void b(FileDownloadModel fileDownloadModel);

    void c(int i);

    void c(int i, long j);

    boolean d(int i);

    void e(int i);
}
