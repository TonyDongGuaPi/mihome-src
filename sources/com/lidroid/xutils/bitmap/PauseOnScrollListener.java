package com.lidroid.xutils.bitmap;

import android.widget.AbsListView;
import com.lidroid.xutils.task.TaskHandler;

public class PauseOnScrollListener implements AbsListView.OnScrollListener {

    /* renamed from: a  reason: collision with root package name */
    private TaskHandler f6294a;
    private final boolean b;
    private final boolean c;
    private final AbsListView.OnScrollListener d;

    public PauseOnScrollListener(TaskHandler taskHandler, boolean z, boolean z2) {
        this(taskHandler, z, z2, (AbsListView.OnScrollListener) null);
    }

    public PauseOnScrollListener(TaskHandler taskHandler, boolean z, boolean z2, AbsListView.OnScrollListener onScrollListener) {
        this.f6294a = taskHandler;
        this.b = z;
        this.c = z2;
        this.d = onScrollListener;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        switch (i) {
            case 0:
                this.f6294a.j();
                break;
            case 1:
                if (this.b) {
                    this.f6294a.i();
                    break;
                }
                break;
            case 2:
                if (this.c) {
                    this.f6294a.i();
                    break;
                }
                break;
        }
        if (this.d != null) {
            this.d.onScrollStateChanged(absListView, i);
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.d != null) {
            this.d.onScroll(absListView, i, i2, i3);
        }
    }
}
