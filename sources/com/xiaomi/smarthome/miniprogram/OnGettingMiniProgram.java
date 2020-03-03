package com.xiaomi.smarthome.miniprogram;

import com.xiaomi.smarthome.miniprogram.model.MyMiniProgramDevice;
import java.util.List;

public interface OnGettingMiniProgram {
    void onError(boolean z);

    void onSuccess(List<MyMiniProgramDevice> list, int i, boolean z);
}
