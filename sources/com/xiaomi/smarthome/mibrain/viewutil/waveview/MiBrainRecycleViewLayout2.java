package com.xiaomi.smarthome.mibrain.viewutil.waveview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableItemViewHolder;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.mibrain.model.NLPResultInfo;
import com.xiaomi.voiceassistant.mijava.MiBrainCloudSDKManager;
import java.util.ArrayList;
import java.util.List;

public class MiBrainRecycleViewLayout2 extends RelativeLayout {
    MiBrainMainRecycleAdapter mAdapter;
    Context mContext;
    RecyclerView mRecycleView;

    public MiBrainRecycleViewLayout2(Context context) {
        this(context, (AttributeSet) null);
    }

    public MiBrainRecycleViewLayout2(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public MiBrainRecycleViewLayout2(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.mi_brain_recycleview_layout, this);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mRecycleView = (RecyclerView) findViewById(R.id.mi_brain_recycle_view);
        findViewById(R.id.header).setVisibility(8);
        a();
    }

    private void a() {
        this.mAdapter = new MiBrainMainRecycleAdapter(this.mContext);
        this.mRecycleView.setLayoutManager(new LinearLayoutManager(this.mContext, 1, false));
        this.mRecycleView.setAdapter(this.mAdapter);
    }

    public void show(List<NLPResultInfo.DeviceOption> list, String str, String str2) {
        setVisibility(0);
        this.mRecycleView.setVisibility(0);
        this.mAdapter.a(list);
    }

    public void hide() {
        setVisibility(8);
    }

    private static class MiBrainMainRecycleAdapter extends RecyclerView.Adapter<ViewHolder> {

        /* renamed from: a  reason: collision with root package name */
        Context f10754a;
        LayoutInflater b;
        private List<NLPResultInfo.DeviceOption> c = new ArrayList();

        public MiBrainMainRecycleAdapter(Context context) {
            this.f10754a = context;
            this.b = LayoutInflater.from(context);
        }

        public void a(List<NLPResultInfo.DeviceOption> list) {
            if (list != null) {
                this.c.clear();
                this.c.addAll(list);
                notifyDataSetChanged();
            }
        }

        /* renamed from: a */
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(this.b.inflate(R.layout.mi_brain_recycle_view_item_2, viewGroup, false));
        }

        /* renamed from: a */
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            final NLPResultInfo.DeviceOption deviceOption = this.c.get(i);
            if (deviceOption != null) {
                if (!TextUtils.isEmpty(deviceOption.f10655a)) {
                    viewHolder.f10756a.setText(deviceOption.f10655a);
                } else {
                    viewHolder.f10756a.setText("");
                }
                viewHolder.b.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (MiBrainCloudSDKManager.g() != null && !TextUtils.isEmpty(deviceOption.f10655a)) {
                            MiBrainCloudSDKManager.g().a(deviceOption.f10655a);
                        }
                    }
                });
            }
        }

        public int getItemCount() {
            return this.c.size();
        }

        static class ViewHolder extends AbstractDraggableItemViewHolder {

            /* renamed from: a  reason: collision with root package name */
            TextView f10756a;
            RelativeLayout b;

            public ViewHolder(View view) {
                super(view);
                this.b = (RelativeLayout) view.findViewById(R.id.item_view);
                this.f10756a = (TextView) view.findViewById(R.id.name_tv);
                this.f10756a.setTextColor(this.b.getContext().getResources().getColor(R.color.black));
            }
        }
    }
}
