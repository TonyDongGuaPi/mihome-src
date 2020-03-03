package com.xiaomi.smarthome.library.common.widget.nestscroll;

public interface IPullHeader {
    void onBounceEnd();

    void onPullEnd(PullHeaderLayout pullHeaderLayout, int i, int i2);

    void onPullProgress(PullHeaderLayout pullHeaderLayout, int i, float f, int i2, boolean z);
}
