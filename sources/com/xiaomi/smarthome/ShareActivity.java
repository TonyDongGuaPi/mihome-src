package com.xiaomi.smarthome;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.widget.SmartBarView;
import com.xiaomi.smarthome.library.common.widget.SmartCircleView;
import com.xiaomi.youpin.share.ShareObject;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class ShareActivity extends BaseActivity {
    @BindView(2131427513)
    SmartBarView mActiveScoreBarView;
    @BindView(2131427514)
    TextView mActiveScoreTitleView;
    @BindView(2131429071)
    TextView mExceedScoreView;
    XQProgressDialog mProcessDialog;
    @BindView(2131432099)
    SmartBarView mSafeScoreBarView;
    @BindView(2131432100)
    TextView mSafeScoreTitleView;
    @BindView(2131432290)
    SmartBarView mSenceScoreBarView;
    @BindView(2131432291)
    TextView mSenceScoreTitleView;
    @BindView(2131432411)
    View mShareRootView;
    @BindView(2131432525)
    SmartCircleView mSmartCircleView;
    SmartInfo mSmartInfo;
    @BindView(2131432523)
    TextView mSmartScoreView;
    @BindView(2131432524)
    TextView mSmartSocreCenterView;

    public static class SmartInfo {

        /* renamed from: a  reason: collision with root package name */
        public int f13419a;
        public int b;
        public int c;
        public int d;
        public int e;

        public static SmartInfo a(JSONObject jSONObject) {
            SmartInfo smartInfo = new SmartInfo();
            smartInfo.f13419a = jSONObject.optInt("smart_index");
            smartInfo.b = jSONObject.optInt("exceed");
            smartInfo.c = jSONObject.optInt("safe_index");
            smartInfo.d = jSONObject.optInt("scene_index");
            smartInfo.e = jSONObject.optInt("active_index");
            return smartInfo;
        }
    }

    /* access modifiers changed from: package-private */
    public void showProgressDialog() {
        dismissProgressDialog();
        this.mProcessDialog = new XQProgressDialog(this);
        this.mProcessDialog.setCancelable(false);
        this.mProcessDialog.setMessage(getResources().getString(R.string.loading_share_info));
        this.mProcessDialog.show();
    }

    /* access modifiers changed from: package-private */
    public void dismissProgressDialog() {
        if (this.mProcessDialog != null) {
            this.mProcessDialog.dismiss();
            this.mProcessDialog = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void loadData() {
        showProgressDialog();
        RemoteFamilyApi.a().c((Context) this, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                ShareActivity.this.mSmartInfo = SmartInfo.a(jSONObject);
                ShareActivity.this.dismissProgressDialog();
                ShareActivity.this.refreshUI();
            }

            public void onFailure(Error error) {
                Toast.makeText(ShareActivity.this.getApplicationContext(), R.string.load_data_error, 0).show();
                ShareActivity.this.dismissProgressDialog();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.share_layout);
        ButterKnife.bind((Activity) this);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShareActivity.this.finish();
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.smarthome_score);
        findViewById(R.id.miliao_share).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShareActivity.this.reportEvent("miliao");
                ShareActivity.this.share(new String[]{"com.xiaomi.channel.control.SystemShareActivity"});
            }
        });
        findViewById(R.id.miliao_share).setVisibility(8);
        findViewById(R.id.wx_share).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShareActivity.this.reportEvent("wx_friend");
                ShareActivity.this.share(new String[]{"com.tencent.mm.ui.tools.ShareImgUI"});
            }
        });
        findViewById(R.id.friends_share).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShareActivity.this.reportEvent("wx_friends");
                ShareActivity.this.share(new String[]{"com.tencent.mm.ui.tools.ShareToTimeLineUI"});
            }
        });
        findViewById(R.id.weibo_share).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShareActivity.this.reportEvent("weibo");
                ShareActivity.this.share(new String[]{"com.sina.weibo.EditActivity", "com.sina.weibo.ComposerDispatchActivity"});
            }
        });
        findViewById(R.id.qzone_share).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShareActivity.this.reportEvent("qzone");
                ShareActivity.this.share(new String[]{"com.qzone.ui.operation.QZonePublishMoodActivity"});
            }
        });
        findViewById(R.id.qq_share).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShareActivity.this.reportEvent("qq_friend");
                ShareActivity.this.share(new String[]{"com.tencent.mobileqq.activity.JumpActivity"});
            }
        });
        loadData();
        refreshUI();
    }

    /* access modifiers changed from: package-private */
    public void reportEvent(String str) {
        CoreApi.a().a(StatType.EVENT, "share_smart_index", str, (String) null, false);
    }

    /* access modifiers changed from: package-private */
    public void refreshUI() {
        if (this.mSmartInfo == null) {
            this.mShareRootView.setVisibility(4);
            return;
        }
        this.mShareRootView.setVisibility(0);
        this.mSmartCircleView.setScore(this.mSmartInfo.f13419a, this.mSmartSocreCenterView);
        this.mSmartCircleView.start();
        this.mSmartScoreView.setText(getString(R.string.share_score_all_socres, new Object[]{Integer.valueOf(this.mSmartInfo.f13419a)}));
        this.mExceedScoreView.setText(getString(R.string.share_score_win, new Object[]{Integer.valueOf(this.mSmartInfo.b)}));
        this.mSafeScoreTitleView.setText(getString(R.string.share_score_safe_scores, new Object[]{Integer.valueOf(this.mSmartInfo.c)}));
        this.mSafeScoreBarView.setColorAndScore(-31687, this.mSmartInfo.c);
        this.mSafeScoreBarView.start();
        this.mActiveScoreTitleView.setText(getString(R.string.share_score_action_scores, new Object[]{Integer.valueOf(this.mSmartInfo.e)}));
        this.mActiveScoreBarView.setColorAndScore(-23496, this.mSmartInfo.e);
        this.mActiveScoreBarView.start();
        this.mSenceScoreTitleView.setText(getString(R.string.share_score_scence_scores, new Object[]{Integer.valueOf(this.mSmartInfo.d)}));
        this.mSenceScoreBarView.setColorAndScore(-6496461, this.mSmartInfo.d);
        this.mSenceScoreBarView.start();
    }

    public void share(String[] strArr) {
        if (this.mSmartInfo != null) {
            Intent intent = null;
            for (String shareActivityIntent : strArr) {
                intent = getShareActivityIntent(shareActivityIntent);
                if (intent != null) {
                    break;
                }
            }
            if (intent == null) {
                Toast.makeText(this, R.string.share_score_share_no_install, 0).show();
                return;
            }
            this.mSmartCircleView.resetStartAnimation();
            Bitmap createBitmap = Bitmap.createBitmap(this.mShareRootView.getWidth(), this.mShareRootView.getHeight(), Bitmap.Config.ARGB_8888);
            this.mShareRootView.draw(new Canvas(createBitmap));
            File externalCacheDir = getExternalCacheDir();
            externalCacheDir.mkdirs();
            File file = new File(externalCacheDir, "share.jpg");
            a(file, createBitmap);
            intent.putExtra("android.intent.extra.STREAM", Uri.parse("file://" + file.getAbsolutePath()));
            startActivity(intent);
        }
    }

    /* access modifiers changed from: package-private */
    public Intent getShareActivityIntent(String str) {
        ActivityInfo activityInfo;
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND").setType(ShareObject.d);
        List<ResolveInfo> queryIntentActivities = getPackageManager().queryIntentActivities(intent, 0);
        if (!queryIntentActivities.isEmpty()) {
            Iterator<ResolveInfo> it = queryIntentActivities.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ResolveInfo next = it.next();
                if (str.equals(next.activityInfo.name)) {
                    activityInfo = next.activityInfo;
                    break;
                }
            }
        }
        activityInfo = null;
        if (activityInfo == null) {
            return null;
        }
        Intent intent2 = new Intent("android.intent.action.SEND");
        intent2.setType(ShareObject.d);
        intent2.setClassName(activityInfo.packageName, activityInfo.name);
        intent2.addFlags(268468225);
        intent2.putExtra("android.intent.extra.TEXT", getString(R.string.share_score_score_info));
        return intent2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0020 A[SYNTHETIC, Splitter:B:14:0x0020] */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.io.File r3, android.graphics.Bitmap r4) {
        /*
            r2 = this;
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0028, OutOfMemoryError -> 0x0024, all -> 0x001c }
            r1.<init>(r3)     // Catch:{ Exception -> 0x0028, OutOfMemoryError -> 0x0024, all -> 0x001c }
            if (r4 == 0) goto L_0x0018
            android.graphics.Bitmap$CompressFormat r3 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ Exception -> 0x0029, OutOfMemoryError -> 0x0025, all -> 0x0016 }
            r0 = 90
            boolean r3 = r4.compress(r3, r0, r1)     // Catch:{ Exception -> 0x0029, OutOfMemoryError -> 0x0025, all -> 0x0016 }
            if (r3 == 0) goto L_0x0018
            r1.flush()     // Catch:{ Exception -> 0x0029, OutOfMemoryError -> 0x0025, all -> 0x0016 }
            goto L_0x0018
        L_0x0016:
            r3 = move-exception
            goto L_0x001e
        L_0x0018:
            r1.close()     // Catch:{ IOException -> 0x002c }
            goto L_0x002c
        L_0x001c:
            r3 = move-exception
            r1 = r0
        L_0x001e:
            if (r1 == 0) goto L_0x0023
            r1.close()     // Catch:{ IOException -> 0x0023 }
        L_0x0023:
            throw r3
        L_0x0024:
            r1 = r0
        L_0x0025:
            if (r1 == 0) goto L_0x002c
            goto L_0x0018
        L_0x0028:
            r1 = r0
        L_0x0029:
            if (r1 == 0) goto L_0x002c
            goto L_0x0018
        L_0x002c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.ShareActivity.a(java.io.File, android.graphics.Bitmap):void");
    }
}
