package com.miuipub.internal.view;

import android.content.Context;
import android.view.ActionMode;
import android.view.View;
import com.miuipub.internal.widget.ActionBarContextView;
import com.miuipub.internal.widget.ActionModeView;
import miuipub.view.ActionModeAnimationListener;
import miuipub.view.EditActionMode;

public class EditActionModeImpl extends ActionModeImpl implements EditActionMode {
    public void setCustomView(View view) {
    }

    public void setSubtitle(int i) {
    }

    public void setSubtitle(CharSequence charSequence) {
    }

    public EditActionModeImpl(Context context, ActionMode.Callback callback) {
        super(context, callback);
    }

    public void setTitle(CharSequence charSequence) {
        ((ActionBarContextView) this.o_.get()).setTitle(charSequence);
    }

    public void setTitle(int i) {
        setTitle((CharSequence) this.n_.getResources().getString(i));
    }

    public CharSequence getTitle() {
        return ((ActionBarContextView) this.o_.get()).getTitle();
    }

    public void a(int i, CharSequence charSequence) {
        ((ActionBarContextView) this.o_.get()).setButton(i, charSequence);
    }

    public void a(int i, int i2) {
        a(i, (CharSequence) this.n_.getResources().getString(i2));
    }

    public void a(ActionModeAnimationListener actionModeAnimationListener) {
        ((ActionModeView) this.o_.get()).addAnimationListener(actionModeAnimationListener);
    }

    public void b(ActionModeAnimationListener actionModeAnimationListener) {
        ((ActionModeView) this.o_.get()).removeAnimationListener(actionModeAnimationListener);
    }
}
