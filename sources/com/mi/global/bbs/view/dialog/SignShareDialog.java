package com.mi.global.bbs.view.dialog;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.mi.global.bbs.R;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.model.SignPostResult;
import com.mi.global.bbs.model.UploadResultModel;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.ImageUtil;
import com.mi.global.bbs.view.CircleImageView;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.io.File;

public class SignShareDialog extends Dialog {
    /* access modifiers changed from: private */
    public BaseActivity ctx;
    @BindView(2131493084)
    ImageView mCloseBtn;
    @BindView(2131493971)
    LinearLayout mSharePic;
    @BindView(2131493982)
    TextView mSignContent;
    @BindView(2131493983)
    TextView mSignDay1;
    @BindView(2131493984)
    TextView mSignDay2;
    @BindView(2131493985)
    TextView mSignDay3;
    @BindView(2131493986)
    ImageView mSignIcon;
    @BindView(2131494212)
    TextView mUserGroupName;
    @BindView(2131494213)
    CircleImageView mUserIcon;
    @BindView(2131494225)
    TextView mUserName;
    private String shareImagePath;

    public SignShareDialog(BaseActivity baseActivity) {
        super(baseActivity, R.style.EmoDialog);
        this.ctx = baseActivity;
        setContentView(R.layout.bbs_sign_share_dialog);
        ButterKnife.bind((Dialog) this);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        Window window = getWindow();
        layoutParams.copyFrom(window.getAttributes());
        layoutParams.width = -1;
        layoutParams.height = -1;
        window.setAttributes(layoutParams);
    }

    public void inflateData(SignPostResult.SignShareBean signShareBean) {
        ImageLoader.showImage(this.mSignIcon, signShareBean.emotionurl);
        this.mSignContent.setText(signShareBean.message);
        ImageLoader.showHeadIcon(this.mUserIcon, signShareBean.usericon);
        this.mUserName.setText(signShareBean.username);
        this.mUserGroupName.setText(signShareBean.group);
        int i = signShareBean.consecutivedays;
        if (i >= 10) {
            this.mSignDay1.setText(String.valueOf(i / 100));
            int i2 = i % 100;
            this.mSignDay2.setText(String.valueOf(i2 / 10));
            this.mSignDay3.setText(String.valueOf(i2 % 10));
            return;
        }
        this.mSignDay3.setText(String.valueOf(i));
    }

    @OnClick({2131493084, 2131493209})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.close_btn) {
            cancel();
        } else if (view.getId() == R.id.download) {
            GoogleTrackerUtil.sendRecordEvent("checkin_card", "click_download", "click_download");
            obtainBitmapAndSave();
        }
    }

    private String obtainBitmapAndSave() {
        Bitmap bitmap = getBitmap();
        String str = null;
        if (bitmap != null) {
            File saveBitmap = ImageUtil.saveBitmap(getContext().getString(R.string.mi_community) + System.currentTimeMillis(), bitmap);
            if (saveBitmap != null) {
                str = saveBitmap.getAbsolutePath();
            }
            if (!(this.ctx == null || saveBitmap == null)) {
                this.ctx.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(saveBitmap)));
                Toast.makeText(this.ctx, R.string.saved_successfully, 0).show();
            }
        }
        return str;
    }

    private Bitmap getBitmap() {
        this.mSharePic.destroyDrawingCache();
        this.mSharePic.setDrawingCacheEnabled(true);
        Bitmap createBitmap = Bitmap.createBitmap(this.mSharePic.getDrawingCache());
        this.mSharePic.setDrawingCacheEnabled(false);
        return createBitmap;
    }

    private void uploadImage(String str) {
        this.ctx.showProDialog("");
        ApiClient.uploadImage(str, this.ctx.bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<UploadResultModel>() {
            public void accept(@NonNull UploadResultModel uploadResultModel) throws Exception {
                SignShareDialog.this.handleUploadResult(uploadResultModel);
                SignShareDialog.this.ctx.dismissProDialog();
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                SignShareDialog.this.ctx.dismissProDialog();
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleUploadResult(UploadResultModel uploadResultModel) {
        if (uploadResultModel != null) {
            UploadResultModel.DataBean data = uploadResultModel.getData();
            if (data != null) {
                ShareDialog shareDialog = new ShareDialog(this.ctx);
                shareDialog.setClickRunnable(new Runnable() {
                    public void run() {
                        SignShareDialog.this.ctx.finish();
                    }
                });
                shareDialog.setShareTitle("").setShareUrl(data.getUrl()).setCallbackManager(this.ctx.callbackManager).show();
                cancel();
                return;
            }
            Toast.makeText(this.ctx, uploadResultModel.getErrmsg(), 0).show();
        }
    }
}
