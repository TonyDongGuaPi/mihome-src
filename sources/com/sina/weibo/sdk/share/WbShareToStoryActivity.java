package com.sina.weibo.sdk.share;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.api.StoryMessage;
import com.sina.weibo.sdk.api.StoryObject;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.web.view.WbSdkProgressBar;

public class WbShareToStoryActivity extends BaseActivity {
    private String callbackActivity;
    private View progressBar;
    private int progressColor = -1;
    private int progressId = -1;
    private FrameLayout rootLayout;
    private SaveFileTask saveFileTask;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent != null) {
            if (bundle != null) {
                try {
                    this.callbackActivity = bundle.getString(WBConstants.H);
                } catch (Exception unused) {
                }
            } else {
                this.callbackActivity = intent.getStringExtra(WBConstants.H);
            }
            if (intent.getIntExtra(WBConstants.G, -1) != 0) {
                finish();
            } else if (TextUtils.isEmpty(this.callbackActivity)) {
                finish();
            } else {
                StoryMessage storyMessage = null;
                try {
                    storyMessage = (StoryMessage) getIntent().getParcelableExtra(WBConstants.Msg.k);
                } catch (Exception unused2) {
                }
                if (storyMessage == null) {
                    setCallbackActivity(2);
                } else if (checkInfo(storyMessage)) {
                    initView();
                    gotoSave(storyMessage);
                } else {
                    setCallbackActivity(2);
                }
            }
        }
    }

    private void initView() {
        try {
            this.progressColor = getIntent().getIntExtra(WBConstants.K, -1);
            this.progressId = getIntent().getIntExtra(WBConstants.L, -1);
        } catch (Exception unused) {
        }
        this.rootLayout = new FrameLayout(this);
        if (this.progressId != -1) {
            try {
                this.progressBar = ((LayoutInflater) getSystemService("layout_inflater")).inflate(this.progressId, (ViewGroup) null);
            } catch (Exception unused2) {
                this.progressBar = new WbSdkProgressBar(this);
            }
        } else {
            this.progressBar = new WbSdkProgressBar(this);
            if (this.progressColor != -1) {
                ((WbSdkProgressBar) this.progressBar).setProgressColor(this.progressColor);
            }
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.rootLayout.addView(this.progressBar, layoutParams);
        this.rootLayout.setBackgroundColor(855638016);
        setContentView(this.rootLayout);
    }

    private boolean checkInfo(StoryMessage storyMessage) {
        return storyMessage.checkSource() && WbSdk.supportMultiImage(this);
    }

    private void gotoSave(StoryMessage storyMessage) {
        if (this.saveFileTask != null) {
            this.saveFileTask.cancel(true);
        }
        this.saveFileTask = new SaveFileTask(this, new TransResourceCallback() {
            public void onTransFinish(TransResourceResult transResourceResult) {
            }

            public void onTransFinish(StoryObject storyObject) {
                if (storyObject != null) {
                    try {
                        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("sinaweibo://story/publish?forceedit=1&finish=true"));
                        intent.setPackage(intent.getStringExtra(WBConstants.D));
                        intent.putExtra("storyData", storyObject);
                        WbShareToStoryActivity.this.startActivity(intent);
                    } catch (Exception unused) {
                        WbShareToStoryActivity.this.setCallbackActivity(2);
                    }
                } else {
                    WbShareToStoryActivity.this.setCallbackActivity(2);
                }
            }
        });
        this.saveFileTask.execute(new StoryMessage[]{storyMessage});
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        int i;
        super.onNewIntent(intent);
        try {
            i = intent.getIntExtra("backType", 0);
        } catch (Exception unused) {
            i = 0;
        }
        if (i == 0) {
            setCallbackActivity(1);
        } else {
            setCallbackActivity(0);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(WBConstants.H, this.callbackActivity);
    }

    /* access modifiers changed from: private */
    public void setCallbackActivity(int i) {
        if (this.rootLayout != null) {
            this.rootLayout.setVisibility(8);
        }
        try {
            Intent intent = new Intent();
            intent.putExtra(WBConstants.Response.f8824a, i);
            setResult(-1, intent);
        } catch (Exception e) {
            LogUtil.e("weibo sdk", e.toString());
        }
        finish();
    }
}
