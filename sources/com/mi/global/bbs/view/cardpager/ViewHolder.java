package com.mi.global.bbs.view.cardpager;

import android.content.Context;
import android.view.View;

public interface ViewHolder<T> {
    View getView(Context context, T t);
}
