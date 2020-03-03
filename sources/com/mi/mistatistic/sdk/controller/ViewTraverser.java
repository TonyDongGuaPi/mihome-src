package com.mi.mistatistic.sdk.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.GridView;
import android.widget.ListView;
import com.mi.mistatistic.sdk.data.ClickableView;
import java.util.ArrayList;
import java.util.List;

@SuppressLint({"NewApi"})
public class ViewTraverser {

    /* renamed from: a  reason: collision with root package name */
    private List<ClickableView> f7352a = new ArrayList();

    public void a(View view) {
        if (Build.VERSION.SDK_INT >= 15) {
            if (view.hasOnClickListeners()) {
                this.f7352a.add(new ClickableView(view));
            } else if (b(view)) {
                this.f7352a.add(new ClickableView(view));
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    a(viewGroup.getChildAt(i));
                }
            }
        }
    }

    private boolean b(View view) {
        ViewParent parent = view.getParent();
        return (parent instanceof ListView) || (parent instanceof GridView);
    }

    public void a(Activity activity) {
        a(activity.getWindow().getDecorView().getRootView());
    }

    public List<ClickableView> a() {
        return this.f7352a;
    }
}
