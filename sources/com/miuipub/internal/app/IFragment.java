package com.miuipub.internal.app;

import android.content.Context;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public interface IFragment extends IImmersionMenu {
    Context P();

    ActionMode a(ActionMode.Callback callback);

    void a(int i, View view, Menu menu);

    void a(ActionMode actionMode);

    boolean a(int i, Menu menu);

    boolean a(Menu menu);

    View b(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle);

    void b(ActionMode actionMode);

    void d(int i);
}
