package com.xiaomi.smarthome.newui.widget.cards;

import android.support.annotation.NonNull;
import android.view.View;

public interface UpdateInterceptor {
    void onUpdateViewAlpha(@NonNull View view, int i, float f);

    void onUpdateViewBackgroud(@NonNull View view, int i, int i2);

    void onUpdateViewScale(@NonNull View view, int i, float f);

    void onUpdateViewTransitionY(@NonNull View view, int i, float f);

    void onUpdateViewZ(@NonNull View view, int i, float f);
}
