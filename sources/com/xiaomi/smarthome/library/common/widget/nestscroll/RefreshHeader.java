package com.xiaomi.smarthome.library.common.widget.nestscroll;

import com.xiaomi.smarthome.library.common.widget.nestscroll.PullHeaderLayout;

public interface RefreshHeader {
    void complete();

    void onPositionChange(float f, float f2, float f3, boolean z, PullHeaderLayout.State state);

    void pull();

    void refreshing();

    void reset();
}
