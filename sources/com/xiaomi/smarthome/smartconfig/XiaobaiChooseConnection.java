package com.xiaomi.smarthome.smartconfig;

import android.app.Activity;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.framework.page.BaseActivity;

public class XiaobaiChooseConnection extends BaseActivity {
    @BindView(2131432516)
    SimpleDraweeView mDeviceIcon;
    @BindView(2131432518)
    TextView mDeviceInfo;
    @BindView(2131432517)
    TextView mDeviceInfoSubTitle;
    @BindView(2131431179)
    Button mNextBtn1;
    @BindView(2131431180)
    Button mNextBtn2;
    @BindView(2131430969)
    View mReturnBtn;
    ScanResult mScanResult;
    @BindView(2131430975)
    TextView mTitle;
    @BindView(2131432919)
    View mTitleBar;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.xiaobai_choose_connection);
        ButterKnife.bind((Activity) this);
        initView();
    }

    /* access modifiers changed from: package-private */
    public void initView() {
        this.mScanResult = (ScanResult) getIntent().getParcelableExtra("scanResult");
        this.mReturnBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                XiaobaiChooseConnection.this.finish();
            }
        });
        this.mDeviceInfo.setText(DeviceFactory.h("chuangmi.camera.xiaobai"));
        DeviceFactory.b("chuangmi.camera.xiaobai", this.mDeviceIcon);
        this.mDeviceInfoSubTitle.setText(R.string.kuailian_sub_main_title_1);
        this.mNextBtn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(XiaobaiChooseConnection.this, SmartConfigMainActivity.class);
                intent.putExtra("strategy_id", 3);
                intent.putExtra("scanResult", XiaobaiChooseConnection.this.mScanResult);
                intent.putExtra("model", DeviceFactory.a(XiaobaiChooseConnection.this.mScanResult));
                XiaobaiChooseConnection.this.startActivity(intent);
                XiaobaiChooseConnection.this.finish();
            }
        });
        this.mNextBtn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(XiaobaiChooseConnection.this, SmartConfigMainActivity.class);
                intent.putExtra("strategy_id", 2);
                intent.putExtra("scanResult", XiaobaiChooseConnection.this.mScanResult);
                intent.putExtra("model", DeviceFactory.a(XiaobaiChooseConnection.this.mScanResult));
                XiaobaiChooseConnection.this.startActivity(intent);
                XiaobaiChooseConnection.this.finish();
            }
        });
    }
}
