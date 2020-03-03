package com.mijia.model.local;

import com.mijia.model.local.LocalFileManager;
import java.util.Comparator;

public class LocalFileCompare implements Comparator<LocalFileManager.LocalFile> {
    /* renamed from: a */
    public int compare(LocalFileManager.LocalFile localFile, LocalFileManager.LocalFile localFile2) {
        try {
            long j = localFile.f8057a;
            long j2 = localFile2.f8057a;
            if (j == 0) {
                j = localFile.c.lastModified();
            }
            if (j2 == 0) {
                j2 = localFile2.c.lastModified();
            }
            if (j > j2) {
                return 1;
            }
            if (j < j2) {
                return -1;
            }
            return 0;
        } catch (Exception unused) {
            return 0;
        }
    }
}
