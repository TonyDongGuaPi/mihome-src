package com.xiaomi.infrared.adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {

    /* renamed from: a  reason: collision with root package name */
    private SparseArray<View> f10222a = new SparseArray<>();
    private View b;

    private ViewHolder(View view) {
        super(view);
        this.b = view;
    }

    public static ViewHolder a(View view) {
        return new ViewHolder(view);
    }

    public <T extends View> T a(int i) {
        T t = (View) this.f10222a.get(i);
        if (t != null) {
            return t;
        }
        T findViewById = this.b.findViewById(i);
        this.f10222a.put(i, findViewById);
        return findViewById;
    }

    public View a() {
        return this.b;
    }

    public ViewHolder a(int i, String str) {
        ((TextView) a(i)).setText(str);
        return this;
    }

    public ViewHolder a(int i, int i2) {
        ((ImageView) a(i)).setImageResource(i2);
        return this;
    }

    public ViewHolder a(int i, Bitmap bitmap) {
        ((ImageView) a(i)).setImageBitmap(bitmap);
        return this;
    }
}
