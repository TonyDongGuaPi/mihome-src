package com.xiaomi.smarthome.frame.login.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.FrameManager;
import java.util.ArrayList;
import java.util.List;

public class LoginHistoryAdapter extends BaseAdapter {

    /* renamed from: a  reason: collision with root package name */
    private Context f16306a;
    private List<String> b = new ArrayList();

    public long getItemId(int i) {
        return (long) i;
    }

    public LoginHistoryAdapter(Context context) {
        this.f16306a = context;
    }

    public void a(List<String> list) {
        this.b = list;
    }

    public int getCount() {
        return this.b.size();
    }

    public Object getItem(int i) {
        return this.b.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(this.f16306a).inflate(R.layout.user_recent_item, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.b = (TextView) view.findViewById(R.id.username_content);
            viewHolder.f16307a = (ImageView) view.findViewById(R.id.username_icon);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        String str = this.b.get(i);
        viewHolder.b.setText(str);
        FrameManager.b().g().a(str, (View) viewHolder.f16307a);
        return view;
    }

    private class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        ImageView f16307a;
        TextView b;

        private ViewHolder() {
        }
    }
}
