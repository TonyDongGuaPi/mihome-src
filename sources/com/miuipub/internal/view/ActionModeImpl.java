package com.miuipub.internal.view;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.miuipub.internal.view.menu.MenuBuilder;
import com.miuipub.internal.widget.ActionModeView;
import java.lang.ref.WeakReference;
import miuipub.view.ActionModeAnimationListener;

public class ActionModeImpl extends ActionMode implements MenuBuilder.Callback, ActionModeAnimationListener {
    boolean c = false;
    private ActionMode.Callback d;
    private MenuBuilder e;
    private ActionModeCallback f;
    protected Context n_;
    protected WeakReference<ActionModeView> o_;

    public interface ActionModeCallback {
        void a(ActionMode actionMode);
    }

    public void a(boolean z) {
    }

    public void a(boolean z, float f2) {
    }

    public ActionModeImpl(Context context, ActionMode.Callback callback) {
        this.n_ = context;
        this.d = callback;
        this.e = new MenuBuilder(context).a(1);
        this.e.a((MenuBuilder.Callback) this);
    }

    public void a(ActionModeCallback actionModeCallback) {
        this.f = actionModeCallback;
    }

    public void a(ActionModeView actionModeView) {
        actionModeView.addAnimationListener(this);
        this.o_ = new WeakReference<>(actionModeView);
    }

    public boolean a() {
        this.e.h();
        try {
            return this.d.onCreateActionMode(this, this.e);
        } finally {
            this.e.i();
        }
    }

    public void setTitle(CharSequence charSequence) {
        throw new UnsupportedOperationException("setTitle not supported");
    }

    public void setSubtitle(CharSequence charSequence) {
        throw new UnsupportedOperationException("setSubTitle not supported");
    }

    public void invalidate() {
        this.e.h();
        try {
            this.d.onPrepareActionMode(this, this.e);
        } finally {
            this.e.i();
        }
    }

    public void finish() {
        if (!this.c) {
            this.c = true;
            ((ActionModeView) this.o_.get()).closeMode();
            if (this.f != null) {
                this.f.a(this);
            }
        }
    }

    public Menu getMenu() {
        return this.e;
    }

    public CharSequence getTitle() {
        throw new UnsupportedOperationException("getTitle not supported");
    }

    public void setTitle(int i) {
        throw new UnsupportedOperationException("setTitle not supported");
    }

    public CharSequence getSubtitle() {
        throw new UnsupportedOperationException("getSubtitle not supported");
    }

    public void setSubtitle(int i) {
        throw new UnsupportedOperationException("setSubTitle not supported");
    }

    public View getCustomView() {
        throw new UnsupportedOperationException("getCustomView not supported");
    }

    public void setCustomView(View view) {
        throw new UnsupportedOperationException("setCustomView not supported");
    }

    public MenuInflater getMenuInflater() {
        return new MenuInflater(this.n_);
    }

    public boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
        return this.d != null && this.d.onActionItemClicked(this, menuItem);
    }

    public void c(MenuBuilder menuBuilder) {
        if (this.d != null) {
            invalidate();
        }
    }

    public void b(boolean z) {
        if (!z) {
            this.d.onDestroyActionMode(this);
            this.d = null;
        }
    }
}
