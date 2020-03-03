package com.mi.global.bbs.ui.checkin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.EmoAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.model.SignHomeData;
import com.mi.global.bbs.observer.DataManager;
import com.mi.global.bbs.ui.TaskActivity;
import com.mi.global.bbs.utils.DialogFactory;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.TextHelper;
import com.mi.global.bbs.utils.Utils;
import com.mi.global.bbs.view.CircleImageView;
import com.mi.global.bbs.view.ImageCheckView;
import com.mi.global.bbs.view.dialog.EmoDialog;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.Observable;
import java.util.Observer;

public class SignActivity extends BaseActivity implements Observer {
    View emoView = null;
    @BindView(2131493076)
    ImageCheckView mCheckbox;
    @BindView(2131493220)
    ImageView mEmoIcon;
    @BindView(2131493975)
    BottomSheetLayout mSheet;
    SignHomeData.SignBean mSignBean;
    @BindView(2131493983)
    TextView mSignDay1;
    @BindView(2131493984)
    TextView mSignDay2;
    @BindView(2131493985)
    TextView mSignDay3;
    @BindView(2131493992)
    TextView mSignStatTv;
    @BindView(2131493993)
    TextView mSignTxt;
    @BindView(2131493994)
    LinearLayout mSignView1;
    @BindView(2131493995)
    LinearLayout mSignView2;
    @BindView(2131494213)
    CircleImageView mUserIcon;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setTitleAndBack(getString(R.string.check_in), this.titleBackListener);
        setCustomContentView(R.layout.bbs_activity_sign);
        ButterKnife.bind((Activity) this);
        this.mCheckbox.setOnCheckedChangedListener(new ImageCheckView.OnCheckedChangedListener() {
            public void onCheckedChanged(ImageCheckView imageCheckView, boolean z) {
                SignActivity.this.setSignAlert(z);
                if (z) {
                    GoogleTrackerUtil.sendRecordEvent("checkin", "click_reminder", "click_reminder");
                    DialogFactory.showSignAlert(SignActivity.this);
                }
            }
        });
        DataManager.init().addObserver(this);
        fetchData();
    }

    /* access modifiers changed from: private */
    public void setSignAlert(boolean z) {
        ApiClient.getApiService().setSignAlert(z ? 1 : 0).compose(bindUntilEvent(ActivityEvent.DESTROY)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<JsonObject>() {
            public void accept(JsonObject jsonObject) throws Exception {
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
            }
        });
    }

    private void fetchData() {
        showProDialog("");
        ApiClient.getSignHomeData().compose(bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<SignHomeData>() {
            public void accept(SignHomeData signHomeData) throws Exception {
                SignActivity.this.dismissProDialog();
                if (signHomeData != null && signHomeData.data != null) {
                    Utils.Preference.setStringPref(SignActivity.this, "emo", new Gson().toJson((Object) signHomeData.data.emotionlist));
                    SignActivity.this.mSignBean = signHomeData.data;
                    SignActivity.this.inflateUI(signHomeData.data);
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                SignActivity.this.dismissProDialog();
            }
        });
    }

    /* access modifiers changed from: private */
    public void inflateUI(SignHomeData.SignBean signBean) {
        boolean z = false;
        if (signBean.havesigned == 0) {
            this.mSignView1.setVisibility(0);
            this.mSignView2.setVisibility(8);
            TextView textView = this.mSignStatTv;
            int i = R.plurals.fans_checked_in_today;
            textView.setText(TextHelper.getQuantityString(this, i, signBean.totalsigned + ""));
        } else {
            this.mSignView1.setVisibility(8);
            this.mSignView2.setVisibility(0);
            this.mSignTxt.setText(signBean.message);
            ImageLoader.showHeadIcon(this.mEmoIcon, getSignEmoUrl());
            if (this.mSignBean.dynamic_id.equals("0")) {
                this.mSignTxt.setVisibility(4);
            }
            this.mSignTxt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!SignActivity.this.mSignBean.dynamic_id.equals("0")) {
                        SignDetailActivity.jump(SignActivity.this, SignActivity.this.mSignBean.dynamic_id);
                    }
                }
            });
        }
        ImageCheckView imageCheckView = this.mCheckbox;
        if (signBean.signalram == 1) {
            z = true;
        }
        imageCheckView.setChecked(z);
        ImageLoader.showHeadIcon(this.mUserIcon, signBean.usericon);
        if (signBean.consecutivedays >= 10) {
            this.mSignDay1.setText(String.valueOf(signBean.consecutivedays / 100));
            this.mSignDay2.setText(String.valueOf((signBean.consecutivedays % 100) / 10));
            this.mSignDay3.setText(String.valueOf((signBean.consecutivedays % 100) % 10));
            return;
        }
        TextView textView2 = this.mSignDay3;
        textView2.setText(signBean.consecutivedays + "");
    }

    @OnClick({2131493074, 2131493075, 2131493981, 2131494053})
    public void onViewClicked(View view) {
        String str = this.mSignBean == null ? "" : this.mSignBean.usericon;
        int i = this.mSignBean == null ? 0 : this.mSignBean.consecutivedays;
        if (view.getId() == R.id.check_in_calendar_bt) {
            GoogleTrackerUtil.sendRecordEvent("checkin", "click_calendar", "click_calendar");
            SignCalendarActivity.jump(this, 0, str, i);
        } else if (view.getId() == R.id.check_in_leaderboard_bt) {
            GoogleTrackerUtil.sendRecordEvent("checkin", "click_leaderboard", "click_leaderboard");
            SignCalendarActivity.jump(this, 1, str, i);
        } else if (view.getId() == R.id.sign_bt) {
            showEmoDialog();
        } else if (view.getId() == R.id.task_bt) {
            GoogleTrackerUtil.sendRecordEvent("checkin", "click_mission", "click_mission");
            startActivity(new Intent(this, TaskActivity.class));
        }
    }

    private void showEmoDialog() {
        if (this.emoView != null) {
            this.mSheet.showWithSheetView(this.emoView);
        } else if (this.mSignBean != null && this.mSignBean.emotionlist != null) {
            EmoDialog emoDialog = new EmoDialog(this, this.mSheet);
            emoDialog.setOnEmotionItemClickListener(new EmoAdapter.OnEmotionItemClickListener() {
                public void onItemClick(int i) {
                    SignActivity.this.mSheet.dismissSheet();
                }
            });
            emoDialog.setEmoList(this.mSignBean.emotionlist);
            this.emoView = emoDialog.getContentView();
            this.mSheet.showWithSheetView(this.emoView);
        }
    }

    public String getSignEmoUrl() {
        if (this.mSignBean == null || this.mSignBean.emotionlist == null) {
            return "";
        }
        for (SignHomeData.SignBean.EmotionBean next : this.mSignBean.emotionlist) {
            if (next.id == this.mSignBean.emotion) {
                return next.enableurl;
            }
        }
        return "";
    }

    public void update(Observable observable, Object obj) {
        if (obj != null && (obj instanceof String) && obj.equals(SignPostActivity.POST_SIGN_SUCCESS)) {
            fetchData();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        DataManager.init().deleteObserver(this);
    }
}
