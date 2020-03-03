package com.mi.global.bbs.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.SelectImageAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.model.BaseResult;
import com.mi.global.bbs.model.FeedbackUploadModel;
import com.mi.global.bbs.model.FeedbackUploadResult;
import com.mi.global.bbs.service.UploadImageEngine;
import com.mi.global.bbs.utils.DeviceUtils;
import com.mi.global.bbs.utils.ImageUtil;
import com.mi.global.bbs.utils.ImeUtils;
import com.mi.global.bbs.utils.JsonParser;
import com.mi.global.bbs.view.Editor.FontEditText;
import com.mi.global.bbs.view.UploadImageView;
import com.mi.global.bbs.view.WrapContentLinearLayoutManager;
import com.taobao.weex.annotation.JSMethod;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.Iterator;

public class FeedbackActivity extends BaseActivity implements View.OnClickListener, UploadImageEngine.UploadListener, UploadImageView.RetryUploadListener {
    public static final int REQUEST_IMAGE = 101;
    private SelectImageAdapter adapter;
    @BindView(2131493254)
    FontEditText feedbackActivityContent;
    @BindView(2131493255)
    FontEditText feedbackActivityEmailEt;
    @BindView(2131493256)
    TextView feedbackActivityNumberPicTx;
    @BindView(2131493257)
    RecyclerView feedbackActivitySelectPicView;
    private ArrayList<FeedbackUploadModel> list = new ArrayList<>();
    private StringBuilder picStringBuilder = new StringBuilder();
    private int size;
    private UploadImageEngine uploadEngine;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitleAndBack(R.string.bbs_feedback, this.titleBackListener);
        setCustomContentView(R.layout.bbs_activity_feedback);
        ButterKnife.bind((Activity) this);
        this.size = getResources().getDimensionPixelSize(R.dimen.feedback_upload_imageView_size);
        WrapContentLinearLayoutManager wrapContentLinearLayoutManager = new WrapContentLinearLayoutManager(this);
        wrapContentLinearLayoutManager.setOrientation(0);
        this.feedbackActivitySelectPicView.setLayoutManager(wrapContentLinearLayoutManager);
        this.adapter = new SelectImageAdapter(this.list);
        this.feedbackActivitySelectPicView.setAdapter(this.adapter);
        this.adapter.setRetryUploadListener(this);
        TextView textView = this.feedbackActivityNumberPicTx;
        int i = R.string._0_9;
        textView.setText(getString(i, new Object[]{this.list.size() + ""}));
        this.uploadEngine = new UploadImageEngine();
        this.uploadEngine.setUploadListener(this);
        addSaveButton();
    }

    private void addSaveButton() {
        View inflate = getLayoutInflater().inflate(R.layout.bbs_actionbar_submit, this.menuLayout, false);
        inflate.findViewById(R.id.actionbar_submit).setOnClickListener(this);
        this.menuLayout.addView(inflate);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.actionbar_submit) {
            String obj = this.feedbackActivityContent.getText().toString();
            String obj2 = this.feedbackActivityEmailEt.getText().toString();
            if (TextUtils.isEmpty(obj) || TextUtils.isEmpty(obj2)) {
                toast(Integer.valueOf(R.string.feedback_un_complete));
                return;
            }
            ImeUtils.hideIme(view);
            if (!this.uploadEngine.isUploading()) {
                feedback(obj, obj2);
            } else {
                toast(Integer.valueOf(R.string.wait_for_img_uploaded));
            }
        }
    }

    private void feedback(String str, String str2) {
        String deviceModel = DeviceUtils.getDeviceModel();
        String version = DeviceUtils.getVersion(this);
        String sb = this.picStringBuilder.toString();
        if (!TextUtils.isEmpty(sb)) {
            sb = sb.substring(0, sb.length() - 1);
        }
        showProDialog(getString(R.string.bbs_loading));
        ApiClient.postFeedbackContent(str2, str, version, deviceModel, sb, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
            public void accept(@NonNull BaseResult baseResult) throws Exception {
                FeedbackActivity.this.dismissProDialog();
                if (baseResult.getErrno() == 0) {
                    FeedbackActivity.this.toast(Integer.valueOf(R.string.feedback_success));
                    FeedbackActivity.this.finish();
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                FeedbackActivity.this.dismissProDialog();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        ArrayList<String> stringArrayListExtra;
        super.onActivityResult(i, i2, intent);
        if (i == 101 && i2 == -1 && (stringArrayListExtra = intent.getStringArrayListExtra("select_result")) != null && stringArrayListExtra.size() > 0 && this.list.size() != 9) {
            ArrayList arrayList = new ArrayList();
            for (String next : stringArrayListExtra) {
                FeedbackUploadModel feedbackUploadModel = new FeedbackUploadModel();
                feedbackUploadModel.fileName = next;
                feedbackUploadModel.state = 0;
                feedbackUploadModel.bitmap = ImageUtil.decodeFile(next, this.size, this.size);
                arrayList.add(feedbackUploadModel);
            }
            this.list.addAll(arrayList);
            addToUploadQueue(arrayList);
            TextView textView = this.feedbackActivityNumberPicTx;
            int i3 = R.string._0_9;
            textView.setText(getString(i3, new Object[]{this.list.size() + ""}));
            this.adapter.notifyDataSetChanged();
        }
    }

    private void addToUploadQueue(ArrayList<FeedbackUploadModel> arrayList) {
        if (arrayList.size() > 0) {
            Iterator<FeedbackUploadModel> it = arrayList.iterator();
            while (it.hasNext()) {
                this.uploadEngine.addToQueue(it.next().fileName);
            }
        }
    }

    public void onUpload(String str, int i) {
        this.list.get(i).state = 1;
        this.adapter.notifyDataSetChanged();
    }

    public void onFinish(String str, int i, boolean z, String str2) {
        this.list.get(i).state = z ? 3 : 2;
        this.adapter.notifyDataSetChanged();
        if (z) {
            FeedbackUploadResult feedbackUploadResult = (FeedbackUploadResult) JsonParser.parse(str2, FeedbackUploadResult.class);
            if (feedbackUploadResult != null && feedbackUploadResult.getData() != null) {
                this.picStringBuilder.append(feedbackUploadResult.getData().getId());
                this.picStringBuilder.append(JSMethod.NOT_SET);
            }
        } else if (!TextUtils.isEmpty(str2)) {
            toast(str2);
        }
    }

    public void onRetryUpload(int i) {
        this.uploadEngine.setNext(i);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.uploadEngine.setUploadListener((UploadImageEngine.UploadListener) null);
    }
}
