package com.xiaomi.smarthome.feedback;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.DeviceFactory;
import java.util.List;

class FeedBackAdapter extends BaseAdapter {

    /* renamed from: a  reason: collision with root package name */
    private List<String> f15924a;
    private Context b;

    public long getItemId(int i) {
        return 0;
    }

    public FeedBackAdapter(Context context) {
        this.b = context;
    }

    public void a(List<String> list) {
        this.f15924a = list;
        notifyDataSetChanged();
    }

    public int getCount() {
        if (this.f15924a == null) {
            return 0;
        }
        return this.f15924a.size();
    }

    public Object getItem(int i) {
        if (i < 0 || this.f15924a == null || i >= this.f15924a.size()) {
            return null;
        }
        return this.f15924a.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(this.b).inflate(R.layout.feed_back_item, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.f15925a = (SimpleDraweeView) view.findViewById(R.id.iv_device_icon);
            viewHolder.b = (TextView) view.findViewById(R.id.tv_device_name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        String str = this.f15924a.get(i);
        if (str != null && !str.isEmpty()) {
            if (str.equalsIgnoreCase(FeedbackApi.COMMON_EXP)) {
                viewHolder.f15925a.setImageResource(R.drawable.std_help_icon_appexperience);
                viewHolder.b.setText(R.string.feedback_app_experience);
            } else if (str.equalsIgnoreCase("shop")) {
                viewHolder.f15925a.setImageResource(R.drawable.std_help_icon_mall);
                viewHolder.b.setText(R.string.feedback_eshop);
            } else if (str.equalsIgnoreCase("other")) {
                viewHolder.f15925a.setImageResource(R.drawable.std_help_icon_other);
                viewHolder.b.setText(R.string.feedback_other);
            } else if (str.equalsIgnoreCase(FeedbackApi.BLE_GATEWAY)) {
                viewHolder.f15925a.setImageResource(R.drawable.std_help_icon_bluetooth);
                viewHolder.b.setText(R.string.device_more_activity_bluetooth_gateway);
            } else if (str.equalsIgnoreCase(FeedbackApi.AUTO_SCENE)) {
                viewHolder.b.setText(R.string.feedback_auto_scene);
                viewHolder.f15925a.setImageResource(R.drawable.std_help_icon_autoscene);
            } else {
                DeviceFactory.b(str, viewHolder.f15925a);
                viewHolder.b.setText(DeviceFactory.h(str));
            }
        }
        return view;
    }

    class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        SimpleDraweeView f15925a;
        TextView b;

        ViewHolder() {
        }
    }
}
