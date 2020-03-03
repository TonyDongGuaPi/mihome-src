package com.xiaomi.smarthome.mibrain.viewutil.waveview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.mibrain.adapter.MiBrainMainRecycleAdapter;
import com.xiaomi.smarthome.mibrain.model.NLPResultInfo;
import java.util.List;

public class MiBrainRecycleViewLayout extends RelativeLayout {
    MiBrainMainRecycleAdapter mAdapter;
    Context mContext;
    RecyclerView mRecycleView;
    SimpleDraweeView mRecycleViewSDV;
    TextView mRecycleViewTitleTV;

    public MiBrainRecycleViewLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public MiBrainRecycleViewLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public MiBrainRecycleViewLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.mi_brain_recycleview_layout, this);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mRecycleView = (RecyclerView) findViewById(R.id.mi_brain_recycle_view);
        this.mRecycleViewSDV = (SimpleDraweeView) findViewById(R.id.mi_brain_recycle_view_sdv);
        this.mRecycleViewTitleTV = (TextView) findViewById(R.id.mi_brain_recycle_view_title_tv);
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
        if (!TextUtils.isEmpty(str)) {
            this.mRecycleViewTitleTV.setText(str);
        } else {
            this.mRecycleViewTitleTV.setText("");
        }
        this.mRecycleViewSDV.setHierarchy(new GenericDraweeHierarchyBuilder(this.mContext.getResources()).setFadeDuration(200).setPlaceholderImage(this.mContext.getResources().getDrawable(R.drawable.device_list_phone_no)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
        TextUtils.isEmpty(str2);
    }

    public void hide() {
        setVisibility(8);
        this.mRecycleViewTitleTV.setText("");
    }
}
