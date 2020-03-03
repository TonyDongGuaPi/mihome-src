package com.wx.wheelview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.wx.wheelview.widget.WheelItem;

public class ArrayWheelAdapter extends BaseWheelAdapter<String> {

    /* renamed from: a  reason: collision with root package name */
    private Context f9872a;

    public ArrayWheelAdapter(Context context) {
        this.f9872a = context;
    }

    public View bindView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = new WheelItem(this.f9872a);
        }
        ((WheelItem) view).setText((CharSequence) this.mList.get(i));
        return view;
    }
}
