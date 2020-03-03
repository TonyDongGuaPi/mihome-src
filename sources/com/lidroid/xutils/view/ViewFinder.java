package com.lidroid.xutils.view;

import android.app.Activity;
import android.content.Context;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceGroup;
import android.view.View;

public class ViewFinder {

    /* renamed from: a  reason: collision with root package name */
    private View f6375a;
    private Activity b;
    private PreferenceGroup c;
    private PreferenceActivity d;

    public ViewFinder(View view) {
        this.f6375a = view;
    }

    public ViewFinder(Activity activity) {
        this.b = activity;
    }

    public ViewFinder(PreferenceGroup preferenceGroup) {
        this.c = preferenceGroup;
    }

    public ViewFinder(PreferenceActivity preferenceActivity) {
        this.d = preferenceActivity;
        this.b = preferenceActivity;
    }

    public View a(int i) {
        return this.b == null ? this.f6375a.findViewById(i) : this.b.findViewById(i);
    }

    public View a(ViewInjectInfo viewInjectInfo) {
        return a(((Integer) viewInjectInfo.f6376a).intValue(), viewInjectInfo.b);
    }

    public View a(int i, int i2) {
        View a2 = i2 > 0 ? a(i2) : null;
        if (a2 != null) {
            return a2.findViewById(i);
        }
        return a(i);
    }

    public Preference a(CharSequence charSequence) {
        return this.c == null ? this.d.findPreference(charSequence) : this.c.findPreference(charSequence);
    }

    public Context a() {
        if (this.f6375a != null) {
            return this.f6375a.getContext();
        }
        if (this.b != null) {
            return this.b;
        }
        if (this.d != null) {
            return this.d;
        }
        return null;
    }
}
