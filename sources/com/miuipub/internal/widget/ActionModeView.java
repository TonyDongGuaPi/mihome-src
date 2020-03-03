package com.miuipub.internal.widget;

import android.view.ActionMode;
import miuipub.view.ActionModeAnimationListener;

public interface ActionModeView {

    /* renamed from: a  reason: collision with root package name */
    public static final int f8338a = 250;

    void addAnimationListener(ActionModeAnimationListener actionModeAnimationListener);

    void animateToVisibility(boolean z);

    void closeMode();

    void initForMode(ActionMode actionMode);

    void killMode();

    void notifyAnimationEnd(boolean z);

    void notifyAnimationStart(boolean z);

    void notifyAnimationUpdate(boolean z, float f);

    void removeAnimationListener(ActionModeAnimationListener actionModeAnimationListener);
}
