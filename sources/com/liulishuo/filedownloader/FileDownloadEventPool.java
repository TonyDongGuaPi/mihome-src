package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.event.DownloadEventPoolImpl;

public class FileDownloadEventPool extends DownloadEventPoolImpl {

    private static class HolderClass {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static final FileDownloadEventPool f6383a = new FileDownloadEventPool();

        private HolderClass() {
        }
    }

    private FileDownloadEventPool() {
    }

    public static FileDownloadEventPool a() {
        return HolderClass.f6383a;
    }
}
