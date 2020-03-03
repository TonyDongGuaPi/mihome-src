package com.xiaomi.smarthome.voice.microaudio.viewutil;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.sip.api.SipApi;
import com.xiaomi.smarthome.sip.model.MicroHistoryInfo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MicroHistoryView extends RelativeLayout {
    MicroHistoryAdapter mAdapter;
    Context mContext;
    TextView mErrorTV;
    ListView mListView;

    public MicroHistoryView(Context context) {
        this(context, (AttributeSet) null);
    }

    public MicroHistoryView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MicroHistoryView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mListView = (ListView) findViewById(R.id.micro_history_lv);
        this.mErrorTV = (TextView) findViewById(R.id.history_error_tv);
        this.mAdapter = new MicroHistoryAdapter();
        this.mListView.setAdapter(this.mAdapter);
    }

    public void show() {
        if (getVisibility() != 0) {
            SipApi.a().a(SHApplication.getAppContext(), "app", 3, 0, -1, 50, new AsyncCallback<List<MicroHistoryInfo>, Error>() {
                /* renamed from: a */
                public void onSuccess(List<MicroHistoryInfo> list) {
                    MicroHistoryView.this.mErrorTV.setVisibility(8);
                    MicroHistoryView.this.mAdapter.a(list);
                }

                public void onFailure(Error error) {
                    MicroHistoryView.this.mErrorTV.setVisibility(0);
                }
            });
            setVisibility(0);
        }
    }

    public void hide() {
        if (getVisibility() != 8) {
            setVisibility(8);
            this.mErrorTV.setVisibility(8);
            this.mAdapter.a(new ArrayList());
        }
    }

    private class MicroHistoryAdapter extends BaseAdapter {
        private List<MicroHistoryInfo> b;

        public long getItemId(int i) {
            return (long) i;
        }

        private MicroHistoryAdapter() {
            this.b = new ArrayList();
        }

        public void a(List<MicroHistoryInfo> list) {
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
                view = LayoutInflater.from(MicroHistoryView.this.mContext).inflate(R.layout.micro_history_view_list_item, (ViewGroup) null);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            MicroHistoryInfo microHistoryInfo = this.b.get(i);
            viewHolder.f22819a.setText(SimpleDateFormat.getInstance().format(new Date(Long.valueOf(microHistoryInfo.f22226a).longValue())));
            viewHolder.b.setText(microHistoryInfo.c);
            viewHolder.c.setText(microHistoryInfo.e == 0 ? R.string.success : R.string.failed);
            return view;
        }
    }

    private class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        TextView f22819a;
        TextView b;
        TextView c;

        public ViewHolder(View view) {
            this.f22819a = (TextView) view.findViewById(R.id.micro_history_time);
            this.b = (TextView) view.findViewById(R.id.micro_history_info_tv);
            this.c = (TextView) view.findViewById(R.id.micro_history_result_tv);
        }
    }
}
