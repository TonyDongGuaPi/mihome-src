package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.BaseDownloadTask;
import java.util.ArrayList;
import java.util.List;

public class FileDownloadQueueSet {

    /* renamed from: a  reason: collision with root package name */
    private FileDownloadListener f6397a;
    private boolean b;
    private List<BaseDownloadTask.FinishListener> c;
    private Integer d;
    private Boolean e;
    private Boolean f;
    private Boolean g;
    private Integer h;
    private Integer i;
    private Object j;
    private String k;
    private BaseDownloadTask[] l;

    public FileDownloadQueueSet(FileDownloadListener fileDownloadListener) {
        if (fileDownloadListener != null) {
            this.f6397a = fileDownloadListener;
            return;
        }
        throw new IllegalArgumentException("create FileDownloadQueueSet must with valid target!");
    }

    public FileDownloadQueueSet a(BaseDownloadTask... baseDownloadTaskArr) {
        this.b = false;
        this.l = baseDownloadTaskArr;
        return this;
    }

    public FileDownloadQueueSet a(List<BaseDownloadTask> list) {
        this.b = false;
        this.l = new BaseDownloadTask[list.size()];
        list.toArray(this.l);
        return this;
    }

    public FileDownloadQueueSet b(BaseDownloadTask... baseDownloadTaskArr) {
        this.b = true;
        this.l = baseDownloadTaskArr;
        return this;
    }

    public FileDownloadQueueSet b(List<BaseDownloadTask> list) {
        this.b = true;
        this.l = new BaseDownloadTask[list.size()];
        list.toArray(this.l);
        return this;
    }

    public void a() {
        for (BaseDownloadTask d2 : this.l) {
            d2.d();
        }
        b();
    }

    public void b() {
        for (BaseDownloadTask baseDownloadTask : this.l) {
            baseDownloadTask.a(this.f6397a);
            if (this.d != null) {
                baseDownloadTask.d(this.d.intValue());
            }
            if (this.e != null) {
                baseDownloadTask.b(this.e.booleanValue());
            }
            if (this.f != null) {
                baseDownloadTask.a(this.f.booleanValue());
            }
            if (this.h != null) {
                baseDownloadTask.b(this.h.intValue());
            }
            if (this.i != null) {
                baseDownloadTask.c(this.i.intValue());
            }
            if (this.j != null) {
                baseDownloadTask.a(this.j);
            }
            if (this.c != null) {
                for (BaseDownloadTask.FinishListener b2 : this.c) {
                    baseDownloadTask.b(b2);
                }
            }
            if (this.k != null) {
                baseDownloadTask.a(this.k, true);
            }
            if (this.g != null) {
                baseDownloadTask.c(this.g.booleanValue());
            }
            baseDownloadTask.c().a();
        }
        FileDownloader.a().a(this.f6397a, this.b);
    }

    public FileDownloadQueueSet a(String str) {
        this.k = str;
        return this;
    }

    public FileDownloadQueueSet a(int i2) {
        this.d = Integer.valueOf(i2);
        return this;
    }

    public FileDownloadQueueSet a(boolean z) {
        this.e = Boolean.valueOf(z);
        return this;
    }

    public FileDownloadQueueSet b(boolean z) {
        this.f = Boolean.valueOf(z);
        return this;
    }

    public FileDownloadQueueSet b(int i2) {
        this.h = Integer.valueOf(i2);
        return this;
    }

    public FileDownloadQueueSet c(int i2) {
        this.i = Integer.valueOf(i2);
        return this;
    }

    public FileDownloadQueueSet c() {
        b(-1);
        return this;
    }

    public FileDownloadQueueSet d() {
        return b(0);
    }

    public FileDownloadQueueSet a(Object obj) {
        this.j = obj;
        return this;
    }

    public FileDownloadQueueSet a(BaseDownloadTask.FinishListener finishListener) {
        if (this.c == null) {
            this.c = new ArrayList();
        }
        this.c.add(finishListener);
        return this;
    }

    public FileDownloadQueueSet c(boolean z) {
        this.g = Boolean.valueOf(z);
        return this;
    }
}
