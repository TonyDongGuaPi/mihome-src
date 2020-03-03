package com.xiaomi.smarthome.library.common.widget.sectionedrecyclerviewadapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.xiaomi.smarthome.library.common.widget.sectionedrecyclerviewadapter.SectionParameters;

public abstract class StatelessSection extends Section {
    @Deprecated
    public StatelessSection(@LayoutRes int i) {
        this(new SectionParameters.Builder(i).a());
    }

    @Deprecated
    public StatelessSection(@LayoutRes int i, @LayoutRes int i2) {
        this(new SectionParameters.Builder(i2).a(i).a());
    }

    @Deprecated
    public StatelessSection(@LayoutRes int i, @LayoutRes int i2, @LayoutRes int i3) {
        this(new SectionParameters.Builder(i3).a(i).b(i2).a());
    }

    public StatelessSection(SectionParameters sectionParameters) {
        super(sectionParameters);
        if (sectionParameters.d != null) {
            throw new IllegalArgumentException("Stateless section shouldn't have a loading state resource");
        } else if (sectionParameters.e != null) {
            throw new IllegalArgumentException("Stateless section shouldn't have a failed state resource");
        } else if (sectionParameters.f != null) {
            throw new IllegalArgumentException("Stateless section shouldn't have an empty state resource");
        }
    }

    public final void c(RecyclerView.ViewHolder viewHolder) {
        super.c(viewHolder);
    }

    public final RecyclerView.ViewHolder d(View view) {
        return super.d(view);
    }

    public final void d(RecyclerView.ViewHolder viewHolder) {
        super.d(viewHolder);
    }

    public final RecyclerView.ViewHolder e(View view) {
        return super.e(view);
    }

    public final void e(RecyclerView.ViewHolder viewHolder) {
        super.e(viewHolder);
    }

    public final RecyclerView.ViewHolder f(View view) {
        return super.f(view);
    }
}
