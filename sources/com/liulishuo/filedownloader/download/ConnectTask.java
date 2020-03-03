package com.liulishuo.filedownloader.download;

import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.liulishuo.filedownloader.connection.FileDownloadConnection;
import com.liulishuo.filedownloader.connection.RedirectHandler;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectTask {

    /* renamed from: a  reason: collision with root package name */
    final int f6417a;
    final String b;
    final FileDownloadHeader c;
    private ConnectionProfile d;
    private String e;
    private Map<String, List<String>> f;
    private List<String> g;

    private ConnectTask(ConnectionProfile connectionProfile, int i, String str, String str2, FileDownloadHeader fileDownloadHeader) {
        this.f6417a = i;
        this.b = str;
        this.e = str2;
        this.c = fileDownloadHeader;
        this.d = connectionProfile;
    }

    /* access modifiers changed from: package-private */
    public FileDownloadConnection a() throws IOException, IllegalAccessException {
        FileDownloadConnection a2 = CustomComponentHolder.a().a(this.b);
        a(a2);
        b(a2);
        this.f = a2.b();
        if (FileDownloadLog.f6465a) {
            FileDownloadLog.c(this, "%s request header %s", Integer.valueOf(this.f6417a), this.f);
        }
        a2.d();
        this.g = new ArrayList();
        return RedirectHandler.a(this.f, a2, this.g);
    }

    /* access modifiers changed from: package-private */
    public void a(FileDownloadConnection fileDownloadConnection) {
        HashMap<String, List<String>> a2;
        if (this.c != null && (a2 = this.c.a()) != null) {
            if (FileDownloadLog.f6465a) {
                FileDownloadLog.e(this, "%d add outside header: %s", Integer.valueOf(this.f6417a), a2);
            }
            for (Map.Entry next : a2.entrySet()) {
                String str = (String) next.getKey();
                List<String> list = (List) next.getValue();
                if (list != null) {
                    for (String a3 : list) {
                        fileDownloadConnection.a(str, a3);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void b(FileDownloadConnection fileDownloadConnection) {
        String str;
        if (!fileDownloadConnection.a(this.e, this.d.f6419a)) {
            if (!TextUtils.isEmpty(this.e)) {
                fileDownloadConnection.a(HttpHeaders.IF_MATCH, this.e);
            }
            if (this.d.c == 0) {
                str = FileDownloadUtils.a("bytes=%d-", Long.valueOf(this.d.b));
            } else {
                str = FileDownloadUtils.a("bytes=%d-%d", Long.valueOf(this.d.b), Long.valueOf(this.d.c));
            }
            fileDownloadConnection.a("Range", str);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return this.d.b > 0;
    }

    /* access modifiers changed from: package-private */
    public String c() {
        if (this.g == null || this.g.isEmpty()) {
            return null;
        }
        return this.g.get(this.g.size() - 1);
    }

    public Map<String, List<String>> d() {
        return this.f;
    }

    public ConnectionProfile e() {
        return this.d;
    }

    public void a(ConnectionProfile connectionProfile, String str) throws Reconnect {
        if (connectionProfile == null) {
            throw new IllegalArgumentException();
        }
        this.d = connectionProfile;
        this.e = str;
        throw new Reconnect();
    }

    class Reconnect extends Throwable {
        Reconnect() {
        }
    }

    static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private Integer f6418a;
        private String b;
        private String c;
        private FileDownloadHeader d;
        private ConnectionProfile e;

        Builder() {
        }

        public Builder a(int i) {
            this.f6418a = Integer.valueOf(i);
            return this;
        }

        public Builder a(String str) {
            this.b = str;
            return this;
        }

        public Builder b(String str) {
            this.c = str;
            return this;
        }

        public Builder a(FileDownloadHeader fileDownloadHeader) {
            this.d = fileDownloadHeader;
            return this;
        }

        public Builder a(ConnectionProfile connectionProfile) {
            this.e = connectionProfile;
            return this;
        }

        /* access modifiers changed from: package-private */
        public ConnectTask a() {
            if (this.f6418a != null && this.e != null && this.b != null) {
                return new ConnectTask(this.e, this.f6418a.intValue(), this.b, this.c, this.d);
            }
            throw new IllegalArgumentException();
        }
    }
}
