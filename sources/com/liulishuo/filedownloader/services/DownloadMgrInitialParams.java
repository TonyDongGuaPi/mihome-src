package com.liulishuo.filedownloader.services;

import com.liulishuo.filedownloader.connection.DefaultConnectionCountAdapter;
import com.liulishuo.filedownloader.connection.FileDownloadUrlConnection;
import com.liulishuo.filedownloader.stream.FileDownloadRandomAccessFile;
import com.liulishuo.filedownloader.util.FileDownloadHelper;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadProperties;
import com.liulishuo.filedownloader.util.FileDownloadUtils;

public class DownloadMgrInitialParams {

    /* renamed from: a  reason: collision with root package name */
    private final InitCustomMaker f6451a;

    public DownloadMgrInitialParams() {
        this.f6451a = null;
    }

    public DownloadMgrInitialParams(InitCustomMaker initCustomMaker) {
        this.f6451a = initCustomMaker;
    }

    public int a() {
        if (this.f6451a == null) {
            return h();
        }
        Integer num = this.f6451a.b;
        if (num == null) {
            return h();
        }
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "initial FileDownloader manager with the customize maxNetworkThreadCount: %d", num);
        }
        return FileDownloadProperties.a(num.intValue());
    }

    public FileDownloadDatabase b() {
        if (this.f6451a == null || this.f6451a.f6452a == null) {
            return i();
        }
        FileDownloadDatabase a2 = this.f6451a.f6452a.a();
        if (a2 == null) {
            return i();
        }
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "initial FileDownloader manager with the customize database: %s", a2);
        }
        return a2;
    }

    public FileDownloadHelper.OutputStreamCreator c() {
        if (this.f6451a == null) {
            return j();
        }
        FileDownloadHelper.OutputStreamCreator outputStreamCreator = this.f6451a.c;
        if (outputStreamCreator == null) {
            return j();
        }
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "initial FileDownloader manager with the customize output stream: %s", outputStreamCreator);
        }
        return outputStreamCreator;
    }

    public FileDownloadHelper.ConnectionCreator d() {
        if (this.f6451a == null) {
            return k();
        }
        FileDownloadHelper.ConnectionCreator connectionCreator = this.f6451a.d;
        if (connectionCreator == null) {
            return k();
        }
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "initial FileDownloader manager with the customize connection creator: %s", connectionCreator);
        }
        return connectionCreator;
    }

    public FileDownloadHelper.ConnectionCountAdapter e() {
        if (this.f6451a == null) {
            return l();
        }
        FileDownloadHelper.ConnectionCountAdapter connectionCountAdapter = this.f6451a.e;
        if (connectionCountAdapter == null) {
            return l();
        }
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "initial FileDownloader manager with the customize connection count adapter: %s", connectionCountAdapter);
        }
        return connectionCountAdapter;
    }

    public FileDownloadHelper.IdGenerator f() {
        if (this.f6451a == null) {
            return g();
        }
        FileDownloadHelper.IdGenerator idGenerator = this.f6451a.f;
        if (idGenerator == null) {
            return g();
        }
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "initial FileDownloader manager with the customize id generator: %s", idGenerator);
        }
        return idGenerator;
    }

    private FileDownloadHelper.IdGenerator g() {
        return new DefaultIdGenerator();
    }

    private int h() {
        return FileDownloadProperties.a().e;
    }

    private FileDownloadDatabase i() {
        return new DefaultDatabaseImpl();
    }

    private FileDownloadHelper.OutputStreamCreator j() {
        return new FileDownloadRandomAccessFile.Creator();
    }

    private FileDownloadHelper.ConnectionCreator k() {
        return new FileDownloadUrlConnection.Creator();
    }

    private FileDownloadHelper.ConnectionCountAdapter l() {
        return new DefaultConnectionCountAdapter();
    }

    public static class InitCustomMaker {

        /* renamed from: a  reason: collision with root package name */
        FileDownloadHelper.DatabaseCustomMaker f6452a;
        Integer b;
        FileDownloadHelper.OutputStreamCreator c;
        FileDownloadHelper.ConnectionCreator d;
        FileDownloadHelper.ConnectionCountAdapter e;
        FileDownloadHelper.IdGenerator f;

        public void a() {
        }

        public InitCustomMaker a(FileDownloadHelper.IdGenerator idGenerator) {
            this.f = idGenerator;
            return this;
        }

        public InitCustomMaker a(FileDownloadHelper.ConnectionCountAdapter connectionCountAdapter) {
            this.e = connectionCountAdapter;
            return this;
        }

        public InitCustomMaker a(FileDownloadHelper.DatabaseCustomMaker databaseCustomMaker) {
            this.f6452a = databaseCustomMaker;
            return this;
        }

        public InitCustomMaker a(int i) {
            if (i > 0) {
                this.b = Integer.valueOf(i);
            }
            return this;
        }

        public InitCustomMaker a(FileDownloadHelper.OutputStreamCreator outputStreamCreator) {
            this.c = outputStreamCreator;
            if (this.c == null || this.c.a() || FileDownloadProperties.a().f) {
                return this;
            }
            throw new IllegalArgumentException("Since the provided FileDownloadOutputStream does not support the seek function, if FileDownloader pre-allocates file size at the beginning of the download, it will can not be resumed from the breakpoint. If you need to ensure that the resumption is available, please add and set the value of 'file.non-pre-allocation' field to 'true' in the 'filedownloader.properties' file which is in your application assets folder manually for resolving this problem.");
        }

        public InitCustomMaker a(FileDownloadHelper.ConnectionCreator connectionCreator) {
            this.d = connectionCreator;
            return this;
        }

        public String toString() {
            return FileDownloadUtils.a("component: database[%s], maxNetworkCount[%s], outputStream[%s], connection[%s], connectionCountAdapter[%s]", this.f6452a, this.b, this.c, this.d, this.e);
        }
    }
}
