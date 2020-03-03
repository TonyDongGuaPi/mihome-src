package com.liulishuo.filedownloader.download;

import com.liulishuo.filedownloader.download.ConnectTask;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import com.liulishuo.filedownloader.util.FileDownloadUtils;

public class DownloadRunnable implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final int f6424a;
    private final ConnectTask b;
    private final ProcessCallback c;
    private final String d;
    private final boolean e;
    private FetchDataTask f;
    private volatile boolean g;
    private final int h;

    private DownloadRunnable(int i, int i2, ConnectTask connectTask, ProcessCallback processCallback, boolean z, String str) {
        this.h = i;
        this.f6424a = i2;
        this.g = false;
        this.c = processCallback;
        this.d = str;
        this.b = connectTask;
        this.e = z;
    }

    public void a() {
        this.g = true;
        if (this.f != null) {
            this.f.a();
        }
    }

    public void b() {
        a();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00ec, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00ee, code lost:
        r3 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00ef, code lost:
        r6 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00f6, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00f7, code lost:
        r4 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00f9, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00fa, code lost:
        r13 = r6;
        r6 = r3;
        r3 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0105, code lost:
        if (r4 == false) goto L_0x0107;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0107, code lost:
        r14.c.a(r3, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0111, code lost:
        if (r14.f != null) goto L_0x0113;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0113, code lost:
        r14.c.a(r3, r14.f.b - r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x011e, code lost:
        if (r6 != null) goto L_0x0120;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0120, code lost:
        r6.f();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0123, code lost:
        r3 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        com.liulishuo.filedownloader.util.FileDownloadLog.d(r14, "it is valid to retry and connection is valid but create fetch-data-task failed, so give up directly with %s", r3);
        r14.c.b(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0134, code lost:
        if (r6 != null) goto L_0x0136;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0137, code lost:
        r14.c.b(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x013c, code lost:
        if (r6 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0146, code lost:
        r4.f();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00ec A[ExcHandler: all (th java.lang.Throwable), Splitter:B:9:0x0021] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00f6 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:2:0x0011] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0105 A[Catch:{ all -> 0x0142 }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0146  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0137 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r14 = this;
            r0 = 10
            android.os.Process.setThreadPriority(r0)
            com.liulishuo.filedownloader.download.ConnectTask r0 = r14.b
            com.liulishuo.filedownloader.download.ConnectionProfile r0 = r0.e()
            long r0 = r0.b
            r2 = 0
            r3 = 0
            r4 = 0
        L_0x0010:
            r5 = 1
            boolean r6 = r14.g     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00f9, all -> 0x00f6 }
            if (r6 == 0) goto L_0x001b
            if (r3 == 0) goto L_0x001a
            r3.f()
        L_0x001a:
            return
        L_0x001b:
            com.liulishuo.filedownloader.download.ConnectTask r4 = r14.b     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00f1, all -> 0x00f6 }
            com.liulishuo.filedownloader.connection.FileDownloadConnection r4 = r4.a()     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00f1, all -> 0x00f6 }
            int r3 = r4.e()     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
            boolean r6 = com.liulishuo.filedownloader.util.FileDownloadLog.f6465a     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
            r7 = 3
            r8 = 2
            r9 = 4
            if (r6 == 0) goto L_0x0052
            java.lang.String r6 = "the connection[%d] for %d, is connected %s with code[%d]"
            java.lang.Object[] r10 = new java.lang.Object[r9]     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
            int r11 = r14.f6424a     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
            r10[r2] = r11     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
            int r11 = r14.h     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
            r10[r5] = r11     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
            com.liulishuo.filedownloader.download.ConnectTask r11 = r14.b     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
            com.liulishuo.filedownloader.download.ConnectionProfile r11 = r11.e()     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
            r10[r8] = r11     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r3)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
            r10[r7] = r11     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
            com.liulishuo.filedownloader.util.FileDownloadLog.c(r14, r6, r10)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
        L_0x0052:
            r6 = 206(0xce, float:2.89E-43)
            if (r3 == r6) goto L_0x008e
            r6 = 200(0xc8, float:2.8E-43)
            if (r3 != r6) goto L_0x005b
            goto L_0x008e
        L_0x005b:
            java.net.SocketException r6 = new java.net.SocketException     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
            java.lang.String r10 = "Connection failed with request[%s] response[%s] http-state[%d] on task[%d-%d], which is changed after verify connection, so please try again."
            r11 = 5
            java.lang.Object[] r11 = new java.lang.Object[r11]     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
            com.liulishuo.filedownloader.download.ConnectTask r12 = r14.b     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
            java.util.Map r12 = r12.d()     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
            r11[r2] = r12     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
            java.util.Map r12 = r4.c()     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
            r11[r5] = r12     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
            r11[r8] = r3     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
            int r3 = r14.h     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
            r11[r7] = r3     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
            int r3 = r14.f6424a     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
            r11[r9] = r3     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
            java.lang.String r3 = com.liulishuo.filedownloader.util.FileDownloadUtils.a((java.lang.String) r10, (java.lang.Object[]) r11)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
            r6.<init>(r3)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
            throw r6     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00ee, all -> 0x00ec }
        L_0x008e:
            com.liulishuo.filedownloader.download.FetchDataTask$Builder r3 = new com.liulishuo.filedownloader.download.FetchDataTask$Builder     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00e8, all -> 0x00ec }
            r3.<init>()     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00e8, all -> 0x00ec }
            boolean r6 = r14.g     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00e8, all -> 0x00ec }
            if (r6 == 0) goto L_0x009d
            if (r4 == 0) goto L_0x009c
            r4.f()
        L_0x009c:
            return
        L_0x009d:
            int r6 = r14.h     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00e8, all -> 0x00ec }
            com.liulishuo.filedownloader.download.FetchDataTask$Builder r3 = r3.b(r6)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00e8, all -> 0x00ec }
            int r6 = r14.f6424a     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00e8, all -> 0x00ec }
            com.liulishuo.filedownloader.download.FetchDataTask$Builder r3 = r3.a((int) r6)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00e8, all -> 0x00ec }
            com.liulishuo.filedownloader.download.ProcessCallback r6 = r14.c     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00e8, all -> 0x00ec }
            com.liulishuo.filedownloader.download.FetchDataTask$Builder r3 = r3.a((com.liulishuo.filedownloader.download.ProcessCallback) r6)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00e8, all -> 0x00ec }
            com.liulishuo.filedownloader.download.FetchDataTask$Builder r3 = r3.a((com.liulishuo.filedownloader.download.DownloadRunnable) r14)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00e8, all -> 0x00ec }
            boolean r6 = r14.e     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00e8, all -> 0x00ec }
            com.liulishuo.filedownloader.download.FetchDataTask$Builder r3 = r3.a((boolean) r6)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00e8, all -> 0x00ec }
            com.liulishuo.filedownloader.download.FetchDataTask$Builder r3 = r3.a((com.liulishuo.filedownloader.connection.FileDownloadConnection) r4)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00e8, all -> 0x00ec }
            com.liulishuo.filedownloader.download.ConnectTask r6 = r14.b     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00e8, all -> 0x00ec }
            com.liulishuo.filedownloader.download.ConnectionProfile r6 = r6.e()     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00e8, all -> 0x00ec }
            com.liulishuo.filedownloader.download.FetchDataTask$Builder r3 = r3.a((com.liulishuo.filedownloader.download.ConnectionProfile) r6)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00e8, all -> 0x00ec }
            java.lang.String r6 = r14.d     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00e8, all -> 0x00ec }
            com.liulishuo.filedownloader.download.FetchDataTask$Builder r3 = r3.a((java.lang.String) r6)     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00e8, all -> 0x00ec }
            com.liulishuo.filedownloader.download.FetchDataTask r3 = r3.a()     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00e8, all -> 0x00ec }
            r14.f = r3     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00e8, all -> 0x00ec }
            com.liulishuo.filedownloader.download.FetchDataTask r3 = r14.f     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00e8, all -> 0x00ec }
            r3.b()     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00e8, all -> 0x00ec }
            boolean r3 = r14.g     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00e8, all -> 0x00ec }
            if (r3 == 0) goto L_0x00e1
            com.liulishuo.filedownloader.download.FetchDataTask r3 = r14.f     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00e8, all -> 0x00ec }
            r3.a()     // Catch:{ FileDownloadGiveUpRetryException | IOException | IllegalAccessException | IllegalArgumentException -> 0x00e8, all -> 0x00ec }
        L_0x00e1:
            if (r4 == 0) goto L_0x0141
            r4.f()
            goto L_0x0141
        L_0x00e8:
            r3 = move-exception
            r6 = r4
            r4 = 1
            goto L_0x00fd
        L_0x00ec:
            r0 = move-exception
            goto L_0x0144
        L_0x00ee:
            r3 = move-exception
            r6 = r4
            goto L_0x00f4
        L_0x00f1:
            r4 = move-exception
            r6 = r3
            r3 = r4
        L_0x00f4:
            r4 = 0
            goto L_0x00fd
        L_0x00f6:
            r0 = move-exception
            r4 = r3
            goto L_0x0144
        L_0x00f9:
            r6 = move-exception
            r13 = r6
            r6 = r3
            r3 = r13
        L_0x00fd:
            com.liulishuo.filedownloader.download.ProcessCallback r7 = r14.c     // Catch:{ all -> 0x0142 }
            boolean r7 = r7.a((java.lang.Exception) r3)     // Catch:{ all -> 0x0142 }
            if (r7 == 0) goto L_0x0137
            if (r4 != 0) goto L_0x010f
            com.liulishuo.filedownloader.download.ProcessCallback r5 = r14.c     // Catch:{ all -> 0x0142 }
            r7 = 0
            r5.a(r3, r7)     // Catch:{ all -> 0x0142 }
            goto L_0x011e
        L_0x010f:
            com.liulishuo.filedownloader.download.FetchDataTask r7 = r14.f     // Catch:{ all -> 0x0142 }
            if (r7 == 0) goto L_0x0126
            com.liulishuo.filedownloader.download.FetchDataTask r5 = r14.f     // Catch:{ all -> 0x0142 }
            long r7 = r5.b     // Catch:{ all -> 0x0142 }
            r5 = 0
            long r7 = r7 - r0
            com.liulishuo.filedownloader.download.ProcessCallback r5 = r14.c     // Catch:{ all -> 0x0142 }
            r5.a(r3, r7)     // Catch:{ all -> 0x0142 }
        L_0x011e:
            if (r6 == 0) goto L_0x0123
            r6.f()
        L_0x0123:
            r3 = r6
            goto L_0x0010
        L_0x0126:
            java.lang.String r0 = "it is valid to retry and connection is valid but create fetch-data-task failed, so give up directly with %s"
            java.lang.Object[] r1 = new java.lang.Object[r5]     // Catch:{ all -> 0x0142 }
            r1[r2] = r3     // Catch:{ all -> 0x0142 }
            com.liulishuo.filedownloader.util.FileDownloadLog.d(r14, r0, r1)     // Catch:{ all -> 0x0142 }
            com.liulishuo.filedownloader.download.ProcessCallback r0 = r14.c     // Catch:{ all -> 0x0142 }
            r0.b(r3)     // Catch:{ all -> 0x0142 }
            if (r6 == 0) goto L_0x0141
            goto L_0x013e
        L_0x0137:
            com.liulishuo.filedownloader.download.ProcessCallback r0 = r14.c     // Catch:{ all -> 0x0142 }
            r0.b(r3)     // Catch:{ all -> 0x0142 }
            if (r6 == 0) goto L_0x0141
        L_0x013e:
            r6.f()
        L_0x0141:
            return
        L_0x0142:
            r0 = move-exception
            r4 = r6
        L_0x0144:
            if (r4 == 0) goto L_0x0149
            r4.f()
        L_0x0149:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.filedownloader.download.DownloadRunnable.run():void");
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private final ConnectTask.Builder f6425a = new ConnectTask.Builder();
        private ProcessCallback b;
        private String c;
        private Boolean d;
        private Integer e;

        public Builder a(ProcessCallback processCallback) {
            this.b = processCallback;
            return this;
        }

        public Builder a(int i) {
            this.f6425a.a(i);
            return this;
        }

        public Builder a(String str) {
            this.f6425a.a(str);
            return this;
        }

        public Builder b(String str) {
            this.f6425a.b(str);
            return this;
        }

        public Builder a(FileDownloadHeader fileDownloadHeader) {
            this.f6425a.a(fileDownloadHeader);
            return this;
        }

        public Builder a(ConnectionProfile connectionProfile) {
            this.f6425a.a(connectionProfile);
            return this;
        }

        public Builder c(String str) {
            this.c = str;
            return this;
        }

        public Builder a(boolean z) {
            this.d = Boolean.valueOf(z);
            return this;
        }

        public Builder a(Integer num) {
            this.e = num;
            return this;
        }

        public DownloadRunnable a() {
            if (this.b == null || this.c == null || this.d == null || this.e == null) {
                throw new IllegalArgumentException(FileDownloadUtils.a("%s %s %B", this.b, this.c, this.d));
            }
            ConnectTask a2 = this.f6425a.a();
            return new DownloadRunnable(a2.f6417a, this.e.intValue(), a2, this.b, this.d.booleanValue(), this.c);
        }

        /* access modifiers changed from: package-private */
        public DownloadRunnable a(ConnectTask connectTask) {
            return new DownloadRunnable(connectTask.f6417a, 0, connectTask, this.b, false, "");
        }
    }
}
