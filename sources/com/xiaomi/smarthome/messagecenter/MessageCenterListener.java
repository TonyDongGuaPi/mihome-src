package com.xiaomi.smarthome.messagecenter;

public interface MessageCenterListener {
    void onCheckNewFeedbackFinished(int i);

    void onCheckNewMessageFinished(int i);

    void onCheckSignNotifyFinished(boolean z, boolean z2);

    void onCheckUpdateFinished(boolean z, boolean z2);
}
