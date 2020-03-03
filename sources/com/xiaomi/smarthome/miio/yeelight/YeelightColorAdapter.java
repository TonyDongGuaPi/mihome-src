package com.xiaomi.smarthome.miio.yeelight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.ColorRadioButton;

public class YeelightColorAdapter extends BaseAdapter {

    /* renamed from: a  reason: collision with root package name */
    final Context f19992a;

    public long getItemId(int i) {
        return (long) i;
    }

    public YeelightColorAdapter(Context context) {
        this.f19992a = context;
    }

    public int getCount() {
        return YeelightPrefManager.d();
    }

    public Object getItem(int i) {
        return Integer.valueOf(YeelightPrefManager.b(i));
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.f19992a).inflate(R.layout.yeelight_user_color, viewGroup, false);
        }
        ColorRadioButton colorRadioButton = (ColorRadioButton) view;
        colorRadioButton.setLinkTextColor(YeelightPrefManager.b(i));
        colorRadioButton.setText(YeelightPrefManager.d(i));
        return view;
    }
}
