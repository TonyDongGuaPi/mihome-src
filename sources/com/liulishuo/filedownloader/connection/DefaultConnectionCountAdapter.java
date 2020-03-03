package com.liulishuo.filedownloader.connection;

import com.liulishuo.filedownloader.util.FileDownloadHelper;

public class DefaultConnectionCountAdapter implements FileDownloadHelper.ConnectionCountAdapter {

    /* renamed from: a  reason: collision with root package name */
    private static final long f6412a = 1048576;
    private static final long b = 5242880;
    private static final long c = 52428800;
    private static final long d = 104857600;

    public int a(int i, String str, String str2, long j) {
        if (j < 1048576) {
            return 1;
        }
        if (j < b) {
            return 2;
        }
        if (j < 52428800) {
            return 3;
        }
        return j < 104857600 ? 4 : 5;
    }
}
