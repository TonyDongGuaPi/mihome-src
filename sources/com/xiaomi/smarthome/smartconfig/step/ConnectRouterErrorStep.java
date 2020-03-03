package com.xiaomi.smarthome.smartconfig.step;

import android.content.Context;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;

public class ConnectRouterErrorStep extends SmartConfigStep {
    @BindView(2131427881)
    View mBottomContainer;
    @BindView(2131432516)
    ImageView mIcon;
    @BindView(2131432518)
    TextView mInfo;
    @BindView(2131432517)
    TextView mInfoSubTitle;
    @BindView(2131430408)
    TextView mLeftBtn;
    @BindView(2131427825)
    View mMainIconContainer;
    @BindView(2131431897)
    TextView mRightBtn;

    public void a(Message message) {
    }

    public void c() {
    }

    public void d() {
    }

    public void e() {
    }

    public SmartConfigStep.Step f() {
        return SmartConfigStep.Step.STEP_CONNECT_ROUTER_ERROR;
    }

    public void a(Context context) {
        a(context, R.layout.smart_config_connect_router_failed);
        this.mMainIconContainer.setVisibility(0);
        this.mBottomContainer.setVisibility(0);
        this.mIcon.setImageResource(R.drawable.config_failed_unable_connect);
        this.mInfo.setText(R.string.dianlimao_connect_failed_title);
        this.mInfoSubTitle.setText(R.string.dianlimao_connect_failed_subtitle);
        this.mLeftBtn.setText(R.string.cancel);
        this.mLeftBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ConnectRouterErrorStep.this.d_(true);
            }
        });
        this.mRightBtn.setText(R.string.retry);
        this.mRightBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ConnectRouterErrorStep.this.D();
            }
        });
    }
}