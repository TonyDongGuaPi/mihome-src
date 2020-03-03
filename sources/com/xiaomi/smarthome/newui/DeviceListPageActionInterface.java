package com.xiaomi.smarthome.newui;

import android.app.Activity;
import android.graphics.RectF;
import android.support.v7.widget.RecyclerView;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.newui.adapter.DviceEditInterface;

public interface DeviceListPageActionInterface {
    void enterEditMode(DviceEditInterface dviceEditInterface);

    void exitEditMode();

    Activity getActivity();

    RecyclerView getRecyclerView();

    boolean isEditMode();

    void onClickCommonUseDevice(Device device, RectF rectF, String str);

    void onStateChanged();

    void updateActionItems(DviceEditInterface dviceEditInterface, int i, String str, boolean z);
}
