package org.xutils;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface ViewInjector {
    View a(Object obj, LayoutInflater layoutInflater, ViewGroup viewGroup);

    void a(Activity activity);

    void a(View view);

    void a(Object obj, View view);
}
