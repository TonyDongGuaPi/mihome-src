package com.mi.global.bbs.ui.checkin;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mi.global.bbs.R;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.model.SignHomeData;
import com.mi.global.bbs.model.SignPostResult;
import com.mi.global.bbs.observer.DataManager;
import com.mi.global.bbs.utils.DefaultTextWatcher;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.view.CheckedTextView;
import com.mi.global.bbs.view.Editor.FontEditText;
import com.mi.global.bbs.view.dialog.SignShareDialog;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SignPostActivity extends BaseActivity implements View.OnClickListener {
    static final int CONTENT_SIZE = 200;
    public static final String EMO = "emoBean";
    public static final String POST_SIGN_SUCCESS = "postSignSuccess";
    SignHomeData.SignBean.EmotionBean bean;
    @BindView(2131493219)
    FontEditText mEmoContent;
    @BindView(2131493220)
    ImageView mEmoIcon;
    @BindView(2131493757)
    TextView mNumIndicate;
    @BindView(2131493973)
    CheckedTextView mShareSwitch;
    View sendBt;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitleAndBack(R.string.check_in, this.titleBackListener);
        setCustomContentView(R.layout.bbs_activity_sign_post);
        ButterKnife.bind((Activity) this);
        this.bean = (SignHomeData.SignBean.EmotionBean) getIntent().getParcelableExtra(EMO);
        this.mShareSwitch.setToggleAble(true);
        this.mShareSwitch.setChecked(true);
        this.mNumIndicate.setText(String.valueOf(200));
        addSaveButton();
        initTextChange();
        if (this.bean != null) {
            ImageLoader.showImage(this.mEmoIcon, this.bean.enableurl);
            this.mEmoContent.setText(String.format(getString(R.string.feel_xx_today), new Object[]{this.bean.sign_word}));
        }
    }

    private void initTextChange() {
        this.mEmoContent.addTextChangedListener(new DefaultTextWatcher() {
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                int length = 200 - SignPostActivity.this.mEmoContent.getText().toString().length();
                SignPostActivity.this.mNumIndicate.setText(String.valueOf(length));
                if (length >= 0) {
                    SignPostActivity.this.sendBt.setEnabled(true);
                    SignPostActivity.this.mNumIndicate.setTextColor(ResourcesCompat.getColor(SignPostActivity.this.getResources(), R.color.text_length_normal, SignPostActivity.this.getTheme()));
                    return;
                }
                SignPostActivity.this.sendBt.setEnabled(false);
                SignPostActivity.this.mNumIndicate.setTextColor(ResourcesCompat.getColor(SignPostActivity.this.getResources(), R.color.text_length_alert, SignPostActivity.this.getTheme()));
            }
        });
    }

    private void addSaveButton() {
        View inflate = getLayoutInflater().inflate(R.layout.bbs_actionbar_submit, this.menuLayout, false);
        inflate.setOnClickListener(this);
        this.sendBt = inflate;
        this.menuLayout.addView(inflate);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.sendBt.getLayoutParams();
        if (Build.VERSION.SDK_INT >= 17) {
            layoutParams.setMarginEnd(getResources().getDimensionPixelOffset(R.dimen.common_padding));
        } else {
            layoutParams.rightMargin = getResources().getDimensionPixelOffset(R.dimen.common_padding);
        }
        this.sendBt.requestLayout();
    }

    public void onClick(View view) {
        showProDialog("");
        ApiClient.getApiService().signPost(this.bean.id, this.mShareSwitch.isChecked() ? 1 : 0, this.mEmoContent.getText().toString()).compose(bindUntilEvent(ActivityEvent.DESTROY)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<SignPostResult>() {
            public void accept(SignPostResult signPostResult) throws Exception {
                SignPostActivity.this.dismissProDialog();
                if (signPostResult != null && signPostResult.data != null) {
                    if (signPostResult.getErrno() == 0) {
                        SignPostActivity.this.mEmoContent.setText("");
                        DataManager.init().notify(SignPostActivity.POST_SIGN_SUCCESS);
                        SignPostActivity.this.showPreShareDialog(signPostResult.data);
                        return;
                    }
                    SignPostActivity.this.toast(signPostResult.getErrmsg());
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                SignPostActivity.this.dismissProDialog();
            }
        });
    }

    /* access modifiers changed from: private */
    public void showPreShareDialog(SignPostResult.SignShareBean signShareBean) {
        SignShareDialog signShareDialog = new SignShareDialog(this);
        signShareDialog.inflateData(signShareBean);
        signShareDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                GoogleTrackerUtil.sendRecordEvent("checkin_card", "click_close", "click_close");
                SignPostActivity.this.finish();
            }
        });
        signShareDialog.show();
    }
}
