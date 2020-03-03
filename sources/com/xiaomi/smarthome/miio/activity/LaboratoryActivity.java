package com.xiaomi.smarthome.miio.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;
import com.xiaomi.smarthome.mibrain.roomsetting.XiaoAiListActivity;

public class LaboratoryActivity extends BaseActivity {
    @BindView(2131430291)
    View mEmptyView;
    @BindView(2131430008)
    LinearLayout mItemContainer;
    @BindView(2131434039)
    View mXiaoAiRoomSetting;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_laboratory_layout);
        ButterKnife.bind((Activity) this);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LaboratoryActivity.this.finish();
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.laboratory_setting);
        int i = 8;
        this.mXiaoAiRoomSetting.setVisibility(8);
        this.mXiaoAiRoomSetting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(LaboratoryActivity.this, XiaoAiListActivity.class);
                intent.putExtra("show_guide", !PreferenceUtils.a("lab_xiaoai_red_dot_shown", false));
                LaboratoryActivity.this.startActivity(intent);
                PreferenceUtils.c(LaboratoryActivity.this.getApplicationContext(), "lab_xiaoai_red_dot_shown", true);
                LaboratoryActivity.this.mXiaoAiRoomSetting.findViewById(R.id.img_xiaoai_dot).setVisibility(8);
            }
        });
        if (!PreferenceUtils.a("lab_xiaoai_red_dot_shown", false)) {
            this.mXiaoAiRoomSetting.findViewById(R.id.img_xiaoai_dot).setVisibility(0);
        } else {
            this.mXiaoAiRoomSetting.findViewById(R.id.img_xiaoai_dot).setVisibility(8);
        }
        View view = this.mEmptyView;
        if (!a(this.mItemContainer)) {
            i = 0;
        }
        view.setVisibility(i);
    }

    private boolean a(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (viewGroup.getChildAt(i).getVisibility() == 0) {
                return true;
            }
        }
        return false;
    }
}
