package com.sina.weibo.sdk.share;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.WeiboAppManager;
import com.sina.weibo.sdk.api.StoryObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.WbAppInfo;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.MD5;
import com.sina.weibo.sdk.utils.Utility;
import com.sina.weibo.sdk.utils.WbSdkVersion;
import com.sina.weibo.sdk.web.view.WbSdkProgressBar;

public class WbShareTransActivity extends BaseActivity {
    private CopyResourceTask copyResourceTask;
    boolean flag = false;
    private Handler handler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            super.handleMessage(message);
            WbShareTransActivity.this.sendCallback(1);
        }
    };
    /* access modifiers changed from: private */
    public FrameLayout rootLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("Share", "startShareTransActivity");
        initView();
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getIntExtra(WBConstants.G, -1) != 0) {
                finish();
            } else {
                checkSource(intent);
            }
        }
    }

    private void checkSource(Intent intent) {
        try {
            Bundle extras = intent.getExtras();
            if (extras == null) {
                finish();
                return;
            }
            WeiboMultiMessage weiboMultiMessage = new WeiboMultiMessage();
            weiboMultiMessage.toObject(extras);
            transPicAndVideoResource(weiboMultiMessage);
        } catch (Exception unused) {
            finish();
        }
    }

    private void initView() {
        View view;
        int intExtra = getIntent().getIntExtra(WBConstants.K, -1);
        int intExtra2 = getIntent().getIntExtra(WBConstants.L, -1);
        this.rootLayout = new FrameLayout(this);
        if (intExtra2 != -1) {
            try {
                view = ((LayoutInflater) getSystemService("layout_inflater")).inflate(intExtra2, (ViewGroup) null);
            } catch (Exception unused) {
                view = new WbSdkProgressBar(this);
            }
        } else {
            WbSdkProgressBar wbSdkProgressBar = new WbSdkProgressBar(this);
            if (intExtra != -1) {
                wbSdkProgressBar.setProgressColor(intExtra);
            }
            view = wbSdkProgressBar;
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.rootLayout.addView(view, layoutParams);
        this.rootLayout.setBackgroundColor(855638016);
    }

    /* access modifiers changed from: private */
    public void gotoWeiboComposer(WeiboMultiMessage weiboMultiMessage) {
        LogUtil.a("Share", "gotoWeiboComposer");
        Intent intent = getIntent();
        this.flag = true;
        try {
            intent.putExtra(WBConstants.G, -1);
            Intent intent2 = new Intent(WBConstants.q);
            Bundle extras = intent.getExtras();
            weiboMultiMessage.toBundle(extras);
            intent2.putExtras(extras);
            String packageName = getPackageName();
            intent2.putExtra(WBConstants.Base.f8821a, WbSdkVersion.f8850a);
            intent2.putExtra(WBConstants.Base.b, packageName);
            intent2.putExtra(WBConstants.Base.c, WbSdk.getAuthInfo().getAppKey());
            intent2.putExtra(WBConstants.SDK.f8825a, WBConstants.z);
            intent2.putExtra(WBConstants.x, MD5.a(Utility.a(this, packageName)));
            String stringExtra = intent.getStringExtra(WBConstants.I);
            if (!TextUtils.isEmpty(stringExtra) && "com.sina.weibo.sdk.web.WeiboSdkWebActivity".equals(stringExtra)) {
                intent2.setClassName(this, "com.sina.weibo.sdk.web.WeiboSdkWebActivity");
                startActivityForResult(intent2, WBConstants.s);
            } else if (WbSdk.isWbInstall(this)) {
                WbAppInfo a2 = WeiboAppManager.a(this).a();
                if (a2 != null) {
                    intent2.setPackage(a2.getPackageName());
                    startActivityForResult(intent2, WBConstants.s);
                    return;
                }
                startActivityForResult(intent2, WBConstants.s);
            } else {
                sendCallback(2);
            }
        } catch (Exception unused) {
            sendCallback(2);
        }
    }

    private void transPicAndVideoResource(WeiboMultiMessage weiboMultiMessage) {
        setContentView(this.rootLayout);
        if (weiboMultiMessage.multiImageObject == null && weiboMultiMessage.videoSourceObject == null) {
            gotoWeiboComposer(weiboMultiMessage);
            return;
        }
        if (this.copyResourceTask != null) {
            this.copyResourceTask.cancel(true);
        }
        this.copyResourceTask = new CopyResourceTask(this, new TransResourceCallback() {
            public void onTransFinish(StoryObject storyObject) {
            }

            public void onTransFinish(TransResourceResult transResourceResult) {
                WbShareTransActivity.this.rootLayout.setVisibility(4);
                if (transResourceResult == null || !transResourceResult.transDone) {
                    WbShareTransActivity.this.sendCallback(2);
                } else {
                    WbShareTransActivity.this.gotoWeiboComposer(transResourceResult.message);
                }
            }
        });
        this.copyResourceTask.execute(new WeiboMultiMessage[]{weiboMultiMessage});
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.handler != null) {
            this.handler.sendEmptyMessageDelayed(0, 100);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtil.b("Share", "startTransActivity.onNewIntent()");
        if (this.handler != null) {
            this.handler.removeMessages(0);
            this.handler = null;
        }
        setResult(-1, intent);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.remove(WBConstants.G);
    }

    /* access modifiers changed from: private */
    public void sendCallback(int i) {
        if (this.rootLayout != null) {
            this.rootLayout.setVisibility(8);
        }
        try {
            Intent intent = new Intent();
            new Bundle().putInt(WBConstants.Response.f8824a, i);
            setResult(-1, intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.handler != null) {
            this.handler.removeMessages(0);
            this.handler = null;
        }
        finish();
    }
}
