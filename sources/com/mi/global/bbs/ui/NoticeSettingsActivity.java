package com.mi.global.bbs.ui;

import android.app.Activity;
import android.os.Bundle;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mi.global.bbs.R;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.model.GetPushFlagModel;
import com.mi.global.bbs.view.SettingsItemContainerView;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class NoticeSettingsActivity extends BaseActivity implements SettingsItemContainerView.OnItemCheckedChangedListener {
    private static final int FLAG_FAVOR = 2;
    private static final int FLAG_FOLLOW = 4;
    private static final int FLAG_MESSAGE = 8;
    private static final int FLAG_QA = 16;
    private static final int FLAG_REPLY = 1;
    /* access modifiers changed from: private */
    public int mPrevFlag = 0;
    /* access modifiers changed from: private */
    public int mPushMsgFlag = 0;
    @BindView(2131493614)
    SettingsItemContainerView mSettingsContainer;

    private void setPushFlag() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitleAndBack(getString(R.string.notifications), this.titleBackListener);
        setCustomContentView(R.layout.bbs_activity_settings);
        ButterKnife.bind((Activity) this);
        this.mSettingsContainer.setOnItemCheckedChangedListener(this);
        this.mSettingsContainer.createItems(getResources().getStringArray(R.array.settings_list_str));
        getPushFlag();
    }

    private void getPushFlag() {
        ApiClient.getPushMsgFlag(bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<GetPushFlagModel>() {
            public void accept(@NonNull GetPushFlagModel getPushFlagModel) throws Exception {
                if (getPushFlagModel != null) {
                    int pushmsgflag = getPushFlagModel.getPushmsgflag();
                    if (pushmsgflag != -1) {
                        int unused = NoticeSettingsActivity.this.mPushMsgFlag = pushmsgflag;
                        int unused2 = NoticeSettingsActivity.this.mPrevFlag = pushmsgflag;
                        NoticeSettingsActivity.this.setFlagState(NoticeSettingsActivity.this.mPushMsgFlag);
                        return;
                    }
                    NoticeSettingsActivity.this.toast(getPushFlagModel.getErrmsg());
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
            }
        });
    }

    public void onBackPressed() {
        setPushFlag();
        finish();
    }

    /* access modifiers changed from: private */
    public void setFlagState(int i) {
        if ((i & 8) == 8) {
            this.mSettingsContainer.setItemState(0, true);
        }
        if ((i & 2) == 2) {
            this.mSettingsContainer.setItemState(1, true);
        }
        if ((i & 4) == 4) {
            this.mSettingsContainer.setItemState(2, true);
        }
        if ((i & 1) == 1) {
            this.mSettingsContainer.setItemState(3, true);
        }
        if ((i & 16) == 16) {
            this.mSettingsContainer.setItemState(4, true);
        }
    }

    public void onItemCheckedChangedListener(int i, boolean z) {
        switch (i) {
            case 0:
                this.mPushMsgFlag = z ? addPushFlag(8) : subPushFlag(8);
                return;
            case 1:
                this.mPushMsgFlag = z ? addPushFlag(2) : subPushFlag(2);
                return;
            case 2:
                this.mPushMsgFlag = z ? addPushFlag(4) : subPushFlag(4);
                return;
            case 3:
                this.mPushMsgFlag = z ? addPushFlag(1) : subPushFlag(1);
                return;
            case 4:
                this.mPushMsgFlag = z ? addPushFlag(16) : subPushFlag(16);
                return;
            default:
                return;
        }
    }

    private int addPushFlag(int i) {
        this.mPushMsgFlag = i | this.mPushMsgFlag;
        return this.mPushMsgFlag;
    }

    private int subPushFlag(int i) {
        this.mPushMsgFlag = (i ^ -1) & this.mPushMsgFlag;
        return this.mPushMsgFlag;
    }
}
