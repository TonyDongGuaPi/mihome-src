package com.wx.wheelview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.wx.wheelview.common.WheelData;
import com.wx.wheelview.widget.WheelItem;

public class SimpleWheelAdapter extends BaseWheelAdapter<WheelData> {

    /* renamed from: a  reason: collision with root package name */
    private Context f9873a;

    public SimpleWheelAdapter(Context context) {
        this.f9873a = context;
    }

    public View bindView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = new WheelItem(this.f9873a);
        }
        WheelItem wheelItem = (WheelItem) view;
        wheelItem.setImage(((WheelData) this.mList.get(i)).getId());
        wheelItem.setText(((WheelData) this.mList.get(i)).getName());
        return view;
    }
}
