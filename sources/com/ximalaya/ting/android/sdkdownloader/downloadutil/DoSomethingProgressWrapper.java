package com.ximalaya.ting.android.sdkdownloader.downloadutil;

import com.ximalaya.ting.android.sdkdownloader.XmDownloadManager;
import com.ximalaya.ting.android.sdkdownloader.exception.BaseRuntimeException;

public class DoSomethingProgressWrapper<T extends BaseRuntimeException> implements IDoSomethingProgress<T> {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public IDoSomethingProgress f2340a;

    public DoSomethingProgressWrapper(IDoSomethingProgress iDoSomethingProgress) {
        this.f2340a = iDoSomethingProgress;
    }

    public void a() {
        if (this.f2340a != null) {
            XmDownloadManager.b().e().a((Runnable) new Runnable() {
                public void run() {
                    try {
                        IDoSomethingProgress a2 = DoSomethingProgressWrapper.this.f2340a;
                        if (a2 != null) {
                            a2.a();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void b() {
        if (this.f2340a != null) {
            XmDownloadManager.b().e().a((Runnable) new Runnable() {
                public void run() {
                    try {
                        IDoSomethingProgress a2 = DoSomethingProgressWrapper.this.f2340a;
                        if (a2 != null) {
                            a2.b();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void a(final T t) {
        if (this.f2340a != null) {
            XmDownloadManager.b().e().a((Runnable) new Runnable() {
                public void run() {
                    try {
                        IDoSomethingProgress a2 = DoSomethingProgressWrapper.this.f2340a;
                        if (a2 != null) {
                            a2.a(t);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
