package com.xiaomi.smarthome.camera.activity.timelapse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wx.wheelview.adapter.BaseWheelAdapter;
import com.xiaomi.smarthome.R;

public class TimeWheelAdapter extends BaseWheelAdapter<String> {
    private Context mContext;

    public TimeWheelAdapter(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    public View bindView(int i, View view, ViewGroup viewGroup) {
        View view2;
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view2 = LayoutInflater.from(this.mContext).inflate(R.layout.camera_time_to_record_list_item, (ViewGroup) null);
            viewHolder.textView = (TextView) view2.findViewById(R.id.tv_hour);
            view2.setTag(viewHolder);
        } else {
            view2 = view;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textView.setText((CharSequence) this.mList.get(i));
        return view2;
    }

    static class ViewHolder {
        TextView textView;

        ViewHolder() {
        }
    }
}
