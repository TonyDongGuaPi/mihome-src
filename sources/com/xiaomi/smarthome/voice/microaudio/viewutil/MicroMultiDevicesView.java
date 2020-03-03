package com.xiaomi.smarthome.voice.microaudio.viewutil;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.voice.microaudio.model.MicroPushMsgInfo;
import java.util.ArrayList;
import java.util.List;

public class MicroMultiDevicesView extends LinearLayout {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Context f22822a;
    MultiDevicesAdapter mAdapter;
    RecyclerView mRecycleView;
    TextView mTitleTV;

    public MicroMultiDevicesView(Context context) {
        this(context, (AttributeSet) null);
    }

    public MicroMultiDevicesView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MicroMultiDevicesView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f22822a = context;
        setOrientation(1);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mRecycleView = (RecyclerView) findViewById(R.id.micro_multi_devices_rv);
        this.mTitleTV = (TextView) findViewById(R.id.title);
        this.mRecycleView.setLayoutManager(new LinearLayoutManager(this.f22822a, 1, false));
        this.mAdapter = new MultiDevicesAdapter();
        this.mRecycleView.setAdapter(this.mAdapter);
    }

    public void show(List list) {
        setVisibility(0);
        this.mAdapter.a(list);
    }

    public void show(MicroPushMsgInfo microPushMsgInfo) {
        if (microPushMsgInfo == null || microPushMsgInfo.j.size() <= 0) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        this.mAdapter.a(microPushMsgInfo.j);
        if (!TextUtils.isEmpty(microPushMsgInfo.l)) {
            this.mTitleTV.setText(microPushMsgInfo.l);
        }
    }

    public void hide() {
        setVisibility(8);
    }

    private class MultiDevicesAdapter extends RecyclerView.Adapter<MultiViewHolder> {

        /* renamed from: a  reason: collision with root package name */
        List<String> f22823a;

        private MultiDevicesAdapter() {
            this.f22823a = new ArrayList();
        }

        public void a(List list) {
            this.f22823a.clear();
            this.f22823a.addAll(list);
            notifyDataSetChanged();
        }

        /* renamed from: a */
        public MultiViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new MultiViewHolder(LayoutInflater.from(MicroMultiDevicesView.this.f22822a).inflate(R.layout.micro_multi_devices_view_item, (ViewGroup) null));
        }

        /* renamed from: a */
        public void onBindViewHolder(MultiViewHolder multiViewHolder, int i) {
            String str = this.f22823a.get(i);
            TextView textView = multiViewHolder.f22824a;
            StringBuilder sb = new StringBuilder();
            sb.append(i + 1);
            sb.append(" ");
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            sb.append(str);
            textView.setText(sb.toString());
        }

        public int getItemCount() {
            return this.f22823a.size();
        }
    }

    class MultiViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        TextView f22824a;

        public MultiViewHolder(View view) {
            super(view);
            this.f22824a = (TextView) view.findViewById(R.id.micro_device_name);
        }
    }
}
