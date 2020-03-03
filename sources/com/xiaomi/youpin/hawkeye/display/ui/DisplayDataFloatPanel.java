package com.xiaomi.youpin.hawkeye.display.ui;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.youpin.hawkeye.R;
import com.xiaomi.youpin.hawkeye.entity.BaseInfo;
import com.xiaomi.youpin.hawkeye.fps.FPSRecordInfo;
import com.xiaomi.youpin.hawkeye.utils.AsyncThreadTask;
import java.util.ArrayList;
import java.util.List;

public class DisplayDataFloatPanel extends RelativeLayout {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public RecyclerView f23356a;
    private TextView b;
    private ImageView c;
    /* access modifiers changed from: private */
    public DisplayAdapter d;
    /* access modifiers changed from: private */
    public List<BaseInfo> e = new ArrayList();

    public DisplayDataFloatPanel(Context context) {
        super(context);
        a();
    }

    public DisplayDataFloatPanel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public DisplayDataFloatPanel(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        inflate(getContext(), R.layout.layout_float_display_view, this);
        this.f23356a = (RecyclerView) findViewById(R.id.rv_list);
        this.b = (TextView) findViewById(R.id.tv_memory);
        this.c = (ImageView) findViewById(R.id.tv_close);
        this.f23356a.setLayoutManager(new LinearLayoutManager(getContext()));
        this.d = new DisplayAdapter(this.e, getContext());
        this.f23356a.setAdapter(this.d);
        this.c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FloatViewManager.a().f();
            }
        });
    }

    public void addDisplayData(BaseInfo baseInfo) {
        if (!(baseInfo instanceof FPSRecordInfo)) {
            this.e.add(baseInfo);
            if (this.f23356a != null && this.d != null) {
                AsyncThreadTask.d(new Runnable() {
                    public void run() {
                        DisplayDataFloatPanel.this.d.notifyDataSetChanged();
                        DisplayDataFloatPanel.this.f23356a.scrollToPosition(DisplayDataFloatPanel.this.e.isEmpty() ? 0 : DisplayDataFloatPanel.this.e.size() - 1);
                    }
                }, 0);
            }
        } else if (this.b != null) {
            TextView textView = this.b;
            textView.setText(((FPSRecordInfo) baseInfo).mFrameRate + " fps");
        }
    }
}
