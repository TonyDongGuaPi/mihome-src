package com.nostra13.universalimageloader.core.listener;

import android.widget.AbsListView;
import com.nostra13.universalimageloader.core.ImageLoader;

public class PauseOnScrollListener implements AbsListView.OnScrollListener {

    /* renamed from: a  reason: collision with root package name */
    private ImageLoader f8492a;
    private final boolean b;
    private final boolean c;
    private final AbsListView.OnScrollListener d;

    public PauseOnScrollListener(ImageLoader imageLoader, boolean z, boolean z2) {
        this(imageLoader, z, z2, (AbsListView.OnScrollListener) null);
    }

    public PauseOnScrollListener(ImageLoader imageLoader, boolean z, boolean z2, AbsListView.OnScrollListener onScrollListener) {
        this.f8492a = imageLoader;
        this.b = z;
        this.c = z2;
        this.d = onScrollListener;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        switch (i) {
            case 0:
                this.f8492a.j();
                break;
            case 1:
                if (this.b) {
                    this.f8492a.i();
                    break;
                }
                break;
            case 2:
                if (this.c) {
                    this.f8492a.i();
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
