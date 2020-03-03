package com.ximalaya.ting.android.sdkdownloader.downloadutil;

import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.sdkdownloader.XmDownloadManager;
import com.ximalaya.ting.android.sdkdownloader.exception.TransferSavePathException;
import java.lang.ref.SoftReference;

public class TransferFileProgressWrapper implements ITransferFileProgress {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public SoftReference<ITransferFileProgress> f2346a;

    public TransferFileProgressWrapper(ITransferFileProgress iTransferFileProgress) {
        this.f2346a = new SoftReference<>(iTransferFileProgress);
    }

    public void a(final int i, final int i2, final Track track) {
        if (this.f2346a != null) {
            XmDownloadManager.b().e().a((Runnable) new Runnable() {
                public void run() {
                    try {
                        ITransferFileProgress iTransferFileProgress = (ITransferFileProgress) TransferFileProgressWrapper.this.f2346a.get();
                        if (iTransferFileProgress != null) {
                            iTransferFileProgress.a(i, i2, track);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void a() {
        if (this.f2346a != null) {
            XmDownloadManager.b().e().a((Runnable) new Runnable() {
                public void run() {
                    try {
                        ITransferFileProgress iTransferFileProgress = (ITransferFileProgress) TransferFileProgressWrapper.this.f2346a.get();
                        if (iTransferFileProgress != null) {
                            iTransferFileProgress.a();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void b() {
        if (this.f2346a != null) {
            XmDownloadManager.b().e().a((Runnable) new Runnable() {
                public void run() {
                    try {
                        ITransferFileProgress iTransferFileProgress = (ITransferFileProgress) TransferFileProgressWrapper.this.f2346a.get();
                        if (iTransferFileProgress != null) {
                            iTransferFileProgress.b();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void a(final TransferSavePathException transferSavePathException) {
        if (this.f2346a != null) {
            XmDownloadManager.b().e().a((Runnable) new Runnable() {
                public void run() {
                    try {
                        ITransferFileProgress iTransferFileProgress = (ITransferFileProgress) TransferFileProgressWrapper.this.f2346a.get();
                        if (iTransferFileProgress != null) {
                            iTransferFileProgress.a(transferSavePathException);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
