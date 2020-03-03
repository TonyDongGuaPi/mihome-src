package com.liulishuo.filedownloader.services;

import com.liulishuo.filedownloader.util.FileDownloadHelper;
import com.liulishuo.filedownloader.util.FileDownloadUtils;

public class DefaultIdGenerator implements FileDownloadHelper.IdGenerator {
    public int a(int i, String str, String str2, boolean z) {
        return a(str, str2, z);
    }

    public int a(String str, String str2, boolean z) {
        if (z) {
            return FileDownloadUtils.f(FileDownloadUtils.a("%sp%s@dir", str, str2)).hashCode();
        }
        return FileDownloadUtils.f(FileDownloadUtils.a("%sp%s", str, str2)).hashCode();
    }
}
