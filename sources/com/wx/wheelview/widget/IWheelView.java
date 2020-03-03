package com.wx.wheelview.widget;

import com.wx.wheelview.adapter.BaseWheelAdapter;
import java.util.HashMap;
import java.util.List;

public interface IWheelView<T> {

    /* renamed from: a  reason: collision with root package name */
    public static final boolean f9877a = false;
    public static final int b = 3;
    public static final boolean c = false;

    void join(WheelView wheelView);

    void joinDatas(HashMap<String, List<T>> hashMap);

    void setLoop(boolean z);

    void setWheelAdapter(BaseWheelAdapter<T> baseWheelAdapter);

    void setWheelClickable(boolean z);

    void setWheelData(List<T> list);

    void setWheelSize(int i);
}
