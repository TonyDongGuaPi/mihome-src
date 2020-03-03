package com.xiaomi.smarthome.auth.bindaccount;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.auth.bindaccount.model.ThirdAccount;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.DevicePtrFrameLayout;
import com.xiaomi.smarthome.library.common.widget.SmartHomePtrHeader;
import com.xiaomi.smarthome.stat.STAT;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

public class ThirdAccountDetailActivity extends BaseActivity {
    private static final int i = 1;

    /* renamed from: a  reason: collision with root package name */
    private View f13892a;
    private DevicePtrFrameLayout b;
    private SmartHomePtrHeader c;
    private PtrIndicator d;
    private SimpleDraweeView e;
    private TextView f;
    private TextView g;
    /* access modifiers changed from: private */
    public ThirdAccount h;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.h = ThirdAccountBindManager.a().a(intent.getStringExtra("account_id"));
        if (this.h == null) {
            finish();
            return;
        }
        setContentView(R.layout.activity_third_account_detail_layout);
        a();
    }

    private void a() {
        c();
        b();
        findViewById(R.id.bind_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ThirdAccountDetailActivity.this, ThirdAccountAuthWebActivity.class);
                intent.putExtra("account_id", ThirdAccountDetailActivity.this.h.b());
                ThirdAccountDetailActivity.this.startActivityForResult(intent, 1);
                STAT.d.b(ThirdAccountDetailActivity.this.h.a());
            }
        });
        this.e = (SimpleDraweeView) findViewById(R.id.partner_icon);
        this.f = (TextView) findViewById(R.id.partner_name);
        this.f.setText(this.h.a());
        this.g = (TextView) findViewById(R.id.partner_desc);
        this.g.setText(this.h.e());
        ThirdAccountBindManager.a(this.e, this.h.c());
    }

    private void b() {
        this.f13892a = findViewById(R.id.common_white_empty_view);
    }

    private void c() {
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ThirdAccountDetailActivity.this.finish();
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.third_account_platform_detail);
        findViewById(R.id.module_a_3_return_more_more_btn).setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 1 && i3 != -1 && i3 == 0) {
            ToastUtil.a((int) R.string.third_account_auth_canceled);
        }
    }
}
