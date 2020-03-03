package com.mibigkoo.convenientbanner.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public interface Holder<T> {
    View a(Context context, ViewGroup viewGroup);

    void a(Context context, int i, T t);
}
