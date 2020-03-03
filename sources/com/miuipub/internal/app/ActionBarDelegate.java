package com.miuipub.internal.app;

import android.content.res.Configuration;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import miuipub.app.ActionBar;

interface ActionBarDelegate {
    ActionMode a(ActionMode.Callback callback);

    ActionBar a();

    void a(Configuration configuration);

    void a(ActionMode actionMode);

    boolean a(int i);

    boolean a(int i, Menu menu);

    boolean a(int i, MenuItem menuItem);

    boolean a(int i, View view, Menu menu);

    ActionMode b(ActionMode.Callback callback);

    View b(int i);

    void b();

    void b(ActionMode actionMode);

    void c();

    void d();
}
