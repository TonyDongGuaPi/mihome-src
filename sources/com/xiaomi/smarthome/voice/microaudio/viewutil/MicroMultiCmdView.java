package com.xiaomi.smarthome.voice.microaudio.viewutil;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.voice.microaudio.model.MicroPushMsgInfo;
import java.util.ArrayList;
import java.util.List;

public class MicroMultiCmdView extends RelativeLayout {
    MicroMultiCmdAdapter mAdapter;
    Context mContext;
    TextView mErrorTV;
    ListView mListView;

    public MicroMultiCmdView(Context context) {
        this(context, (AttributeSet) null);
    }

    public MicroMultiCmdView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MicroMultiCmdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mListView = (ListView) findViewById(R.id.micro_multi_cmd_lv);
        this.mErrorTV = (TextView) findViewById(R.id.multi_cmd_error_tv);
        this.mAdapter = new MicroMultiCmdAdapter();
        this.mListView.setAdapter(this.mAdapter);
    }

    public void show(List<MicroPushMsgInfo.MultiCmdInfo> list) {
        if (getVisibility() != 0 && list != null) {
            setVisibility(0);
            this.mAdapter.a(list);
        }
    }

    public void hide() {
        if (getVisibility() != 8) {
            setVisibility(8);
            this.mErrorTV.setVisibility(8);
            this.mAdapter.a(new ArrayList());
        }
    }

    private class MicroMultiCmdAdapter extends BaseAdapter {
        private List<MicroPushMsgInfo.MultiCmdInfo> b;

        public long getItemId(int i) {
            return (long) i;
        }

        private MicroMultiCmdAdapter() {
            this.b = new ArrayList();
        }

        public void a(List<MicroPushMsgInfo.MultiCmdInfo> list) {
            this.b.clear();
            this.b.addAll(list);
            notifyDataSetChanged();
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
                view = LayoutInflater.from(MicroMultiCmdView.this.mContext).inflate(R.layout.micro_multi_cmd_view_list_item, (ViewGroup) null);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            MicroPushMsgInfo.MultiCmdInfo multiCmdInfo = this.b.get(i);
            viewHolder.b.setText(MicroMultiCmdView.this.a(multiCmdInfo.f22809a));
            viewHolder.f22821a.setText(multiCmdInfo.b);
            viewHolder.d.setImageResource(multiCmdInfo.c == 0 ? R.drawable.micro_multi_cmd_success_icon : R.drawable.micro_multi_cmd_fail_icon);
            return view;
        }
    }

    /* access modifiers changed from: private */
    public String a(List<String> list) {
        if (list == null || list.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                sb.append(list.get(i));
            } else {
                sb.append("\n");
                sb.append("\n");
                sb.append(list.get(i));
            }
        }
        return sb.toString();
    }

    private class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        TextView f22821a;
        TextView b;
        TextView c;
        ImageView d;

        public ViewHolder(View view) {
            this.f22821a = (TextView) view.findViewById(R.id.device_result);
            this.b = (TextView) view.findViewById(R.id.device_name);
            this.c = (TextView) view.findViewById(R.id.device_desc);
            this.d = (ImageView) view.findViewById(R.id.device_result_iv);
        }
    }
}
