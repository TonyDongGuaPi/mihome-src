package com.xiaomi.jr.idcardverifier;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.miui.supportlite.app.ActionBar;
import com.miui.supportlite.app.Activity;
import com.xiaomi.jr.idcardverifier.http.VerifyResponse;
import com.xiaomi.jr.idcardverifier.utils.VerifyConstants;

public class VerifyResultActivity extends Activity {
    public static final int RESULT_CODE_CANCEL = 102;
    public static final int RESULT_CODE_COMPLETE = 101;
    public static final int RESULT_CODE_RETRY = 100;

    /* renamed from: a  reason: collision with root package name */
    private ImageView f10865a;
    private TextView b;
    private TextView c;
    private Button d;
    private Button e;
    private Button f;
    private ViewStub g;
    private VerifyResponse h;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_verify_result);
        a();
    }

    private void a() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.id_card_verify);
            ImageView a2 = actionBar.a();
            if (a2 != null) {
                a2.setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        VerifyResultActivity.this.d(view);
                    }
                });
            }
        }
        this.g = (ViewStub) findViewById(R.id.identity_has_bound_hint_stub);
        this.f10865a = (ImageView) findViewById(R.id.verify_result_content_icon);
        this.b = (TextView) findViewById(R.id.verify_result_content_title);
        this.c = (TextView) findViewById(R.id.verify_result_content_desc);
        this.d = (Button) findViewById(R.id.complete_button);
        this.d.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                VerifyResultActivity.this.c(view);
            }
        });
        this.e = (Button) findViewById(R.id.rescan_button);
        this.e.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                VerifyResultActivity.this.b(view);
            }
        });
        this.f = (Button) findViewById(R.id.cancel_button);
        this.f.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                VerifyResultActivity.this.a(view);
            }
        });
        Intent intent = getIntent();
        if (intent != null) {
            this.h = (VerifyResponse) intent.getParcelableExtra(VerifyConstants.n);
        }
        if (this.h != null) {
            boolean f2 = this.h.f();
            int a3 = this.h.a();
            this.f10865a.setImageResource(f2 ? R.drawable.icon_verify_success : R.drawable.icon_verify_failed);
            this.b.setText(f2 ? R.string.verify_identity_result_success : R.string.verify_identity_result_failed);
            this.c.setText(a(a3));
            int i = 8;
            this.d.setVisibility(f2 ? 0 : 8);
            this.e.setVisibility(f2 ? 8 : 0);
            Button button = this.f;
            if (!f2) {
                i = 0;
            }
            button.setVisibility(i);
            if (a3 == 100010) {
                this.g.inflate();
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void d(View view) {
        setResult(102);
        finish();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        setResult(101);
        finish();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        setResult(100);
        finish();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        setResult(102);
        finish();
    }

    private String a(int i) {
        if (i != -1) {
            if (i == 200) {
                return getString(R.string.verify_identity_result_success_desc);
            }
            switch (i) {
                case VerifyConstants.ErrorCode.c /*100001*/:
                case VerifyConstants.ErrorCode.d /*100002*/:
                case VerifyConstants.ErrorCode.e /*100003*/:
                    break;
                default:
                    switch (i) {
                        case VerifyConstants.ErrorCode.f /*100006*/:
                            return getString(R.string.verify_identity_result_failed_desc_default);
                        case VerifyConstants.ErrorCode.g /*100007*/:
                        case VerifyConstants.ErrorCode.h /*100008*/:
                            return getString(R.string.verify_identity_result_failed_desc_reflected_light);
                        case VerifyConstants.ErrorCode.i /*100009*/:
                            return getString(R.string.verify_identity_result_failed_desc_xiaomi_id_has_bound);
                        case VerifyConstants.ErrorCode.j /*100010*/:
                            return getString(R.string.verify_identity_result_failed_desc_identity_has_bound);
                        case VerifyConstants.ErrorCode.k /*100011*/:
                            return getString(R.string.verify_identity_result_failed_desc_card_expire);
                        default:
                            return getString(R.string.verify_identity_result_failed_desc_default);
                    }
            }
        }
        return getString(R.string.verify_identity_result_failed_desc_network_exception);
    }
}
