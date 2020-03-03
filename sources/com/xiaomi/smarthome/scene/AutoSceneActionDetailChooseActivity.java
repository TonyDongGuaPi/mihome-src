package com.xiaomi.smarthome.scene;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.scene.api.SceneApi;

public class AutoSceneActionDetailChooseActivity extends BaseActivity {
    public static final String EXTRA_ENABEL = "extra_enable";
    public static final String EXTRA_ID = "extra_id";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public int f21192a = -1;
    private String b = null;
    @BindView(2131428391)
    TextView mCloseTV;
    @BindView(2131431293)
    TextView mOpenTV;
    @BindView(2131430969)
    ImageView mReturnBtn;
    @BindView(2131430975)
    TextView mTitle;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.auto_scene_choose_action_detail_activity_layout);
        ButterKnife.bind((Activity) this);
        if (getIntent() != null) {
            this.f21192a = getIntent().getIntExtra(EXTRA_ENABEL, -1);
            this.b = getIntent().getStringExtra("extra_id");
        }
        SceneApi.SmartHomeScene e = SceneManager.x().e(this.b);
        if (e != null && !TextUtils.isEmpty(e.g)) {
            this.mTitle.setText(e.g);
        }
        a();
        b();
    }

    /* access modifiers changed from: private */
    public void a() {
        if (this.f21192a == -1) {
            this.mOpenTV.setSelected(false);
            this.mCloseTV.setSelected(false);
        } else if (this.f21192a == 1) {
            this.mOpenTV.setSelected(true);
            this.mCloseTV.setSelected(false);
        } else if (this.f21192a == 0) {
            this.mOpenTV.setSelected(false);
            this.mCloseTV.setSelected(true);
        }
    }

    private void b() {
        this.mCloseTV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int unused = AutoSceneActionDetailChooseActivity.this.f21192a = 0;
                AutoSceneActionDetailChooseActivity.this.a();
                Intent intent = new Intent();
                intent.putExtra(AutoSceneActionDetailChooseActivity.EXTRA_ENABEL, AutoSceneActionDetailChooseActivity.this.f21192a);
                AutoSceneActionDetailChooseActivity.this.setResult(-1, intent);
                AutoSceneActionDetailChooseActivity.this.finish();
                CreateSceneManager.a().l();
            }
        });
        this.mOpenTV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                int unused = AutoSceneActionDetailChooseActivity.this.f21192a = 1;
                AutoSceneActionDetailChooseActivity.this.a();
                intent.putExtra(AutoSceneActionDetailChooseActivity.EXTRA_ENABEL, AutoSceneActionDetailChooseActivity.this.f21192a);
                AutoSceneActionDetailChooseActivity.this.setResult(-1, intent);
                AutoSceneActionDetailChooseActivity.this.finish();
                CreateSceneManager.a().l();
            }
        });
        this.mReturnBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (AutoSceneActionDetailChooseActivity.this.f21192a == -1) {
                    AutoSceneActionDetailChooseActivity.this.setResult(0);
                    AutoSceneActionDetailChooseActivity.this.finish();
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra(AutoSceneActionDetailChooseActivity.EXTRA_ENABEL, AutoSceneActionDetailChooseActivity.this.f21192a);
                AutoSceneActionDetailChooseActivity.this.setResult(-1, intent);
                AutoSceneActionDetailChooseActivity.this.finish();
                CreateSceneManager.a().l();
            }
        });
    }

    public void onBackPressed() {
        if (this.f21192a == -1) {
            setResult(0);
            finish();
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_ENABEL, this.f21192a);
        setResult(-1, intent);
        finish();
        CreateSceneManager.a().l();
    }
}
