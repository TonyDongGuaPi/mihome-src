package com.transitionseverywhere;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public final class Scene {

    /* renamed from: a  reason: collision with root package name */
    Runnable f9467a;
    Runnable b;
    private Context c;
    private int d = -1;
    private ViewGroup e;
    private View f;

    public static Scene a(ViewGroup viewGroup, int i, Context context) {
        SparseArray sparseArray = (SparseArray) viewGroup.getTag(R.id.scene_layoutid_cache);
        if (sparseArray == null) {
            sparseArray = new SparseArray();
            viewGroup.setTag(R.id.scene_layoutid_cache, sparseArray);
        }
        Scene scene = (Scene) sparseArray.get(i);
        if (scene != null) {
            return scene;
        }
        Scene scene2 = new Scene(viewGroup, i, context);
        sparseArray.put(i, scene2);
        return scene2;
    }

    public Scene(ViewGroup viewGroup) {
        this.e = viewGroup;
    }

    private Scene(ViewGroup viewGroup, int i, Context context) {
        this.c = context;
        this.e = viewGroup;
        this.d = i;
    }

    public Scene(ViewGroup viewGroup, View view) {
        this.e = viewGroup;
        this.f = view;
    }

    public Scene(ViewGroup viewGroup, ViewGroup viewGroup2) {
        this.e = viewGroup;
        this.f = viewGroup2;
    }

    public ViewGroup a() {
        return this.e;
    }

    public void b() {
        if (a((View) this.e) == this && this.b != null) {
            this.b.run();
        }
    }

    public void c() {
        if (this.d > 0 || this.f != null) {
            a().removeAllViews();
            if (this.d > 0) {
                LayoutInflater.from(this.c).inflate(this.d, this.e);
            } else {
                this.e.addView(this.f);
            }
        }
        if (this.f9467a != null) {
            this.f9467a.run();
        }
        a(this.e, this);
    }

    static void a(View view, Scene scene) {
        view.setTag(R.id.current_scene, scene);
    }

    public static Scene a(View view) {
        return (Scene) view.getTag(R.id.current_scene);
    }

    public void a(Runnable runnable) {
        this.f9467a = runnable;
    }

    public void b(Runnable runnable) {
        this.b = runnable;
    }

    /* access modifiers changed from: package-private */
    public boolean d() {
        return this.d > 0;
    }
}
