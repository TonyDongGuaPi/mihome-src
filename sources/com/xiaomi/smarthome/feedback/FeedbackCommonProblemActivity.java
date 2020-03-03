package com.xiaomi.smarthome.feedback;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.webview.SmartHomeWebView;
import com.xiaomi.smarthome.setting.ServerRouteUtil;
import java.util.Locale;

public class FeedbackCommonProblemActivity extends BaseActivity {
    public static final String ARGS_KEY_DID = "did";
    public static final String EXTRA_MODEL = "extra_model";

    /* renamed from: a  reason: collision with root package name */
    private static final String f15946a = "/views/faqQuestion.html?model=";
    /* access modifiers changed from: private */
    public String b;
    /* access modifiers changed from: private */
    public String c;
    /* access modifiers changed from: private */
    public SmartHomeWebView d;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.feedback_common_problem_layout);
        this.b = getIntent().getStringExtra("extra_model");
        this.c = getIntent().getStringExtra("did");
        if (this.b == null || this.b.isEmpty()) {
            finish();
        }
        a();
    }

    private void a() {
        b();
        this.d = (SmartHomeWebView) findViewById(R.id.wv_common_problem);
        Locale I = CoreApi.a().I();
        if (I == null) {
            I = Locale.getDefault();
        }
        SmartHomeWebView smartHomeWebView = this.d;
        smartHomeWebView.loadUrl(buildUrl(f15946a + this.b + "&locale=" + LocaleUtil.a(I)));
        TextView textView = (TextView) findViewById(R.id.btn_feedback_problem);
        textView.setText(R.string.feedback_problem);
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(FeedbackCommonProblemActivity.this.getContext(), FeedbackActivity.class);
                intent.putExtra("extra_device_model", FeedbackCommonProblemActivity.this.b);
                if (!TextUtils.isEmpty(FeedbackCommonProblemActivity.this.c)) {
                    intent.putExtra("extra_device_did", FeedbackCommonProblemActivity.this.c);
                }
                FeedbackCommonProblemActivity.this.startActivity(intent);
            }
        });
    }

    public String buildUrl(String str) {
        return ServerRouteUtil.b(SHApplication.getAppContext()) + str;
    }

    public void onBackPressed() {
        if (this.d == null || !this.d.canGoBack()) {
            super.onBackPressed();
        } else {
            this.d.goBack();
        }
    }

    private void b() {
        String feedbackDeviceName;
        TextView textView = (TextView) findViewById(R.id.module_a_3_return_title);
        if (!(textView == null || (feedbackDeviceName = FeedbackManager.INSTANCE.getFeedbackDeviceName(getContext(), this.b)) == null)) {
            textView.setText(feedbackDeviceName);
        }
        View findViewById = findViewById(R.id.module_a_3_return_btn);
        if (findViewById != null) {
            findViewById.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (FeedbackCommonProblemActivity.this.d == null || !FeedbackCommonProblemActivity.this.d.canGoBack()) {
                        FeedbackCommonProblemActivity.this.finish();
                    } else {
                        FeedbackCommonProblemActivity.this.d.goBack();
                    }
                }
            });
        }
    }
}
