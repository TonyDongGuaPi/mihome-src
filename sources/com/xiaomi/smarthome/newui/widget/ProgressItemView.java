package com.xiaomi.smarthome.newui.widget;

public interface ProgressItemView {
    float getPercent();

    void setCancel();

    void setFail();

    void setPercent(float f);

    void setStart();

    void setSuccess();

    void setVisible(boolean z);
}
