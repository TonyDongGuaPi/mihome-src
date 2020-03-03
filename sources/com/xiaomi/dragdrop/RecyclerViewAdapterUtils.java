package com.xiaomi.dragdrop;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewParent;

public class RecyclerViewAdapterUtils {
    private RecyclerViewAdapterUtils() {
    }

    @Nullable
    public static RecyclerView a(@Nullable View view) {
        if (view == null) {
            return null;
        }
        ViewParent parent = view.getParent();
        if (parent instanceof RecyclerView) {
            return (RecyclerView) parent;
        }
        if (parent instanceof View) {
            return a((View) parent);
        }
        return null;
    }

    @Nullable
    public static View b(@Nullable View view) {
        RecyclerView a2 = a(view);
        if (a2 == null) {
            return null;
        }
        return a2.findContainingItemView(view);
    }

    @Nullable
    public static RecyclerView.ViewHolder c(@Nullable View view) {
        RecyclerView a2 = a(view);
        if (a2 == null) {
            return null;
        }
        return a2.findContainingViewHolder(view);
    }
}
