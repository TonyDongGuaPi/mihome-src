package com.mi.global.bbs.view.pop;

import android.content.Context;

public class ActionItem {
    public int mDrawable;
    public CharSequence mTitle;
    public String mes;

    public ActionItem(int i) {
        this.mDrawable = i;
    }

    public ActionItem(int i, CharSequence charSequence) {
        this.mDrawable = i;
        this.mTitle = charSequence;
    }

    public ActionItem(Context context, CharSequence charSequence, int i) {
        this.mTitle = charSequence;
        this.mDrawable = i;
        this.mes = null;
    }

    public ActionItem(Context context, CharSequence charSequence, int i, String str) {
        this.mTitle = charSequence;
        this.mDrawable = i;
        this.mes = str;
    }
}
