package com.xiaomi.smarthome.mibrain.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableItemViewHolder;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.mibrain.model.NLPResultInfo;
import com.xiaomi.voiceassistant.mijava.MiBrainCloudSDKManager;
import java.util.ArrayList;
import java.util.List;

public class MiBrainMainRecycleAdapter extends RecyclerView.Adapter<Viewholder> {

    /* renamed from: a  reason: collision with root package name */
    Context f10626a;
    LayoutInflater b;
    private List<NLPResultInfo.DeviceOption> c = new ArrayList();

    public MiBrainMainRecycleAdapter(Context context) {
        this.f10626a = context;
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
    public Viewholder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new Viewholder(this.b.inflate(R.layout.mi_brain_recycle_view_item, (ViewGroup) null));
    }

    /* renamed from: a */
    public void onBindViewHolder(Viewholder viewholder, int i) {
        final NLPResultInfo.DeviceOption deviceOption = this.c.get(i);
        if (deviceOption != null) {
            if (!TextUtils.isEmpty(deviceOption.f10655a)) {
                viewholder.f10628a.setText(a((i + 1) + "/ " + deviceOption.f10655a));
            } else {
                viewholder.f10628a.setText("");
            }
            viewholder.b.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (MiBrainCloudSDKManager.g() != null && !TextUtils.isEmpty(deviceOption.f10655a)) {
                        MiBrainCloudSDKManager.g().a(deviceOption.f10655a);
                        LogUtil.a("MiBrainActivity", "option.intention" + deviceOption.f10655a);
                    }
                }
            });
        }
    }

    public int getItemCount() {
        return this.c.size();
    }

    static class Viewholder extends AbstractDraggableItemViewHolder {

        /* renamed from: a  reason: collision with root package name */
        TextView f10628a;
        RelativeLayout b;

        public Viewholder(View view) {
            super(view);
            this.b = (RelativeLayout) view.findViewById(R.id.item_view);
            this.f10628a = (TextView) view.findViewById(R.id.name_tv);
        }
    }

    private SpannableString a(String str) {
        if (TextUtils.isEmpty(str) || str.length() <= 2) {
            return null;
        }
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ForegroundColorSpan(this.f10626a.getResources().getColor(R.color.class_text_17)), 0, 2, 33);
        spannableString.setSpan(new ForegroundColorSpan(this.f10626a.getResources().getColor(R.color.white_60_transparent)), 2, str.length(), 33);
        spannableString.setSpan(new AbsoluteSizeSpan(70), 0, 1, 33);
        spannableString.setSpan(new AbsoluteSizeSpan(45), 1, str.length(), 33);
        return spannableString;
    }
}
