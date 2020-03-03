package com.xiaomi.smarthome.feedback;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.feedback.FeedbackList;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;

public class FeedbackDetailActivity extends BaseActivity {
    public static final String EXTRA_ID = "extra_id";

    /* renamed from: a  reason: collision with root package name */
    private String f15950a;
    /* access modifiers changed from: private */
    public TextView b;
    /* access modifiers changed from: private */
    public TextView c;
    /* access modifiers changed from: private */
    public View d;
    /* access modifiers changed from: private */
    public View e;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.feedback_detail_layout);
        this.f15950a = getIntent().getStringExtra("extra_id");
        b();
        a();
    }

    private void a(FeedbackList.FeedbackItem feedbackItem) {
        String str;
        TextView textView = (TextView) findViewById(R.id.feedback_title);
        TextView textView2 = (TextView) findViewById(R.id.feedback_desc);
        String feedbackDeviceName = FeedbackManager.INSTANCE.getFeedbackDeviceName(getContext(), feedbackItem.d);
        if (feedbackDeviceName != null) {
            str = String.format("%s | %s", new Object[]{feedbackDeviceName, feedbackItem.b});
        } else {
            str = feedbackItem.b;
        }
        if (str != null) {
            textView2.setText(str);
        }
        if (feedbackItem.f != null) {
            textView.setText(feedbackItem.f);
        }
    }

    private void a() {
        if (this.f15950a == null || this.f15950a.isEmpty()) {
            finish();
            return;
        }
        FeedbackList.FeedbackItem feedbackItem = FeedbackManager.INSTANCE.getFeedbackItem(this.f15950a);
        if (feedbackItem == null) {
            finish();
            return;
        }
        a(feedbackItem);
        this.b = (TextView) findViewById(R.id.feedback_reply_time);
        this.c = (TextView) findViewById(R.id.feedback_reply_content);
        this.e = findViewById(R.id.feedback_reply_container);
        this.d = findViewById(R.id.empty_reply_container);
        FeedbackApi.INSTANCE.getFeedbackDetail(getContext(), this.f15950a, new AsyncCallback<FeedbackDetail, Error>() {
            /* renamed from: a */
            public void onSuccess(final FeedbackDetail feedbackDetail) {
                FeedbackDetailActivity.this.mHandler.post(new Runnable() {
                    public void run() {
                        FeedbackDetailActivity.this.b.setText(feedbackDetail.g);
                        FeedbackDetailActivity.this.c.setText(feedbackDetail.f);
                        if (feedbackDetail.e == 1) {
                            FeedbackDetailActivity.this.d.setVisibility(8);
                            FeedbackDetailActivity.this.e.setVisibility(0);
                            return;
                        }
                        FeedbackDetailActivity.this.d.setVisibility(0);
                        FeedbackDetailActivity.this.e.setVisibility(8);
                    }
                });
            }

            public void onFailure(Error error) {
                FeedbackDetailActivity.this.mHandler.post(new Runnable() {
                    public void run() {
                        FeedbackDetailActivity.this.d.setVisibility(0);
                        FeedbackDetailActivity.this.e.setVisibility(8);
                    }
                });
            }
        });
    }

    private void b() {
        TextView textView = (TextView) findViewById(R.id.module_a_3_return_title);
        if (textView != null) {
            textView.setText(R.string.feedback_detail);
        }
        View findViewById = findViewById(R.id.module_a_3_return_btn);
        if (findViewById != null) {
            findViewById.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FeedbackDetailActivity.this.finish();
                }
            });
        }
    }
}
