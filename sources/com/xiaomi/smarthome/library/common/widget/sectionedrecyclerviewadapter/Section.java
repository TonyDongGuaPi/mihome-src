package com.xiaomi.smarthome.library.common.widget.sectionedrecyclerviewadapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.xiaomi.smarthome.library.common.widget.sectionedrecyclerviewadapter.SectionParameters;
import com.xiaomi.smarthome.library.common.widget.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public abstract class Section {

    /* renamed from: a  reason: collision with root package name */
    private State f19063a;
    @LayoutRes
    private Integer b;
    boolean c;
    boolean d;
    boolean e;
    @LayoutRes
    Integer f;
    @LayoutRes
    Integer g;
    @LayoutRes
    int h;
    @LayoutRes
    private Integer i;
    @LayoutRes
    private Integer j;

    public enum State {
        LOADING,
        LOADED,
        FAILED,
        EMPTY
    }

    public abstract int a();

    public void a(RecyclerView.ViewHolder viewHolder) {
    }

    public abstract void a(RecyclerView.ViewHolder viewHolder, int i2);

    public abstract RecyclerView.ViewHolder b(View view);

    public void b(RecyclerView.ViewHolder viewHolder) {
    }

    public void c(RecyclerView.ViewHolder viewHolder) {
    }

    public void d(RecyclerView.ViewHolder viewHolder) {
    }

    public void e(RecyclerView.ViewHolder viewHolder) {
    }

    @Deprecated
    public Section(@LayoutRes int i2, @LayoutRes int i3, @LayoutRes int i4) {
        this(new SectionParameters.Builder(i2).c(i3).d(i4).a());
    }

    @Deprecated
    public Section(@LayoutRes int i2, @LayoutRes int i3, @LayoutRes int i4, @LayoutRes int i5) {
        this(new SectionParameters.Builder(i3).a(i2).c(i4).d(i5).a());
    }

    @Deprecated
    public Section(@LayoutRes int i2, @LayoutRes int i3, @LayoutRes int i4, @LayoutRes int i5, @LayoutRes int i6) {
        this(new SectionParameters.Builder(i4).a(i2).b(i3).c(i5).d(i6).a());
    }

    public Section(SectionParameters sectionParameters) {
        this.f19063a = State.LOADED;
        boolean z = true;
        this.c = true;
        this.d = false;
        this.e = false;
        this.f = sectionParameters.f19065a;
        this.g = sectionParameters.b;
        this.h = sectionParameters.c;
        this.b = sectionParameters.d;
        this.i = sectionParameters.e;
        this.j = sectionParameters.f;
        this.d = this.f != null;
        this.e = this.g == null ? false : z;
    }

    public final void a(State state) {
        switch (state) {
            case LOADING:
                if (this.b == null) {
                    throw new IllegalStateException("Missing 'loading state' resource id");
                }
                break;
            case FAILED:
                if (this.i == null) {
                    throw new IllegalStateException("Missing 'failed state' resource id");
                }
                break;
            case EMPTY:
                if (this.j == null) {
                    throw new IllegalStateException("Missing 'empty state' resource id");
                }
                break;
        }
        this.f19063a = state;
    }

    public final State b() {
        return this.f19063a;
    }

    public final boolean c() {
        return this.c;
    }

    public final void a(boolean z) {
        this.c = z;
    }

    public final boolean d() {
        return this.d;
    }

    public final void b(boolean z) {
        this.d = z;
    }

    public final boolean e() {
        return this.e;
    }

    public final void c(boolean z) {
        this.e = z;
    }

    public final Integer f() {
        return this.f;
    }

    public final Integer g() {
        return this.g;
    }

    public final int h() {
        return this.h;
    }

    public final Integer i() {
        return this.b;
    }

    public final Integer j() {
        return this.i;
    }

    public final Integer k() {
        return this.j;
    }

    public final void b(RecyclerView.ViewHolder viewHolder, int i2) {
        switch (this.f19063a) {
            case LOADING:
                c(viewHolder);
                return;
            case FAILED:
                d(viewHolder);
                return;
            case EMPTY:
                e(viewHolder);
                return;
            case LOADED:
                a(viewHolder, i2);
                return;
            default:
                throw new IllegalStateException("Invalid state");
        }
    }

    public final int l() {
        int i2 = 1;
        switch (this.f19063a) {
            case LOADING:
            case FAILED:
            case EMPTY:
                break;
            case LOADED:
                i2 = a();
                break;
            default:
                throw new IllegalStateException("Invalid state");
        }
        return i2 + (this.d ? 1 : 0) + (this.e ? 1 : 0);
    }

    public RecyclerView.ViewHolder a(View view) {
        return new SectionedRecyclerViewAdapter.EmptyViewHolder(view);
    }

    public RecyclerView.ViewHolder c(View view) {
        return new SectionedRecyclerViewAdapter.EmptyViewHolder(view);
    }

    public RecyclerView.ViewHolder d(View view) {
        return new SectionedRecyclerViewAdapter.EmptyViewHolder(view);
    }

    public RecyclerView.ViewHolder e(View view) {
        return new SectionedRecyclerViewAdapter.EmptyViewHolder(view);
    }

    public RecyclerView.ViewHolder f(View view) {
        return new SectionedRecyclerViewAdapter.EmptyViewHolder(view);
    }
}
