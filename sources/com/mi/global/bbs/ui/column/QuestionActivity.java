package com.mi.global.bbs.ui.column;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.QuestionAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.model.BaseResult;
import com.mi.global.bbs.model.QuestionsModel;
import com.mi.global.bbs.observer.RefreshManager;
import com.mi.global.bbs.ui.WebActivity;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends BaseActivity {
    private QuestionAdapter adapter;
    /* access modifiers changed from: private */
    public int currentPosition = 0;
    /* access modifiers changed from: private */
    public List<List<QuestionsModel.DataBean.StepsBean>> list;
    @BindView(2131493396)
    TextView mHintText;
    @BindView(2131493580)
    RecyclerView mList;
    @BindView(2131493746)
    TextView mNext;
    @BindView(2131493871)
    TextView mQuestionText;
    @BindView(2131494031)
    TextView mStep;
    @BindView(2131494101)
    View mToolbarAgent;
    /* access modifiers changed from: private */
    public List<String> originalFollows;
    private String[] questions;
    /* access modifiers changed from: private */
    public String replyClubUrl;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        this.contentNeedMargin = false;
        super.onCreate(bundle);
        setCustomContentView(R.layout.bbs_activity_quastions);
        ButterKnife.bind((Activity) this);
        this.adapter = new QuestionAdapter(this);
        this.mList.setLayoutManager(new GridLayoutManager(this, 4));
        this.mList.setAdapter(this.adapter);
        this.questions = getResources().getStringArray(R.array.questions);
        setTitleAndBack(R.string.customise_my_homepage, this.titleBackListener);
        this.mToolBarContainer.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.main_title_bar_bg)));
        this.mToolBarContainer.getBackground().setAlpha(0);
        this.mToolBarDivider.setVisibility(4);
        this.mTitleView.setTextColor(-1);
        this.mUpImageView.setImageResource(R.drawable.bbs_arrow_up_white);
        getStep();
    }

    private void getStep() {
        showProDialog("");
        ApiClient.getQuestionStep().compose(bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<QuestionsModel>() {
            public void accept(QuestionsModel questionsModel) throws Exception {
                QuestionActivity.this.dismissProDialog();
                if (questionsModel != null && questionsModel.data != null && questionsModel.data.steps != null) {
                    List unused = QuestionActivity.this.list = questionsModel.data.steps;
                    List unused2 = QuestionActivity.this.originalFollows = QuestionActivity.this.getFollowIds(QuestionActivity.this.list);
                    String unused3 = QuestionActivity.this.replyClubUrl = questionsModel.data.replyclub;
                    QuestionActivity.this.inflateData(QuestionActivity.this.currentPosition);
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                QuestionActivity.this.dismissProDialog();
            }
        });
    }

    /* access modifiers changed from: private */
    public void inflateData(int i) {
        this.mStep.setText(getString(R.string.step_, new Object[]{Integer.valueOf(i + 1), Integer.valueOf(this.list.size())}));
        this.mQuestionText.setText(this.questions[i > this.questions.length - 1 ? this.questions.length - 1 : i]);
        this.mHintText.setVisibility(8);
        this.adapter.refreshData(this.list.get(i));
        if (i == this.list.size() - 1) {
            this.mHintText.setVisibility(0);
            this.mHintText.getPaint().setFlags(8);
            this.mNext.setText(R.string.done);
        }
        GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_FORU, Constants.ClickEvent.CLICK_PERSONALIZE, "click_personalize_next_" + i);
    }

    @OnClick({2131493396, 2131493746})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.hint_text) {
            if (!TextUtils.isEmpty(this.replyClubUrl)) {
                WebActivity.jump(this, this.replyClubUrl);
            }
        } else if (view.getId() == R.id.next && this.list != null) {
            if (this.currentPosition < this.list.size() - 1) {
                this.currentPosition++;
                inflateData(this.currentPosition);
                return;
            }
            GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_FORU, Constants.ClickEvent.CLICK_PERSONALIZE, "click_personalize_done");
            List<String> followIds = getFollowIds(this.list);
            if (followIds != null) {
                showProDialog("");
                ArrayList arrayList = new ArrayList();
                if (this.originalFollows != null && this.originalFollows.size() > 0) {
                    if (followIds.size() == 0) {
                        arrayList.addAll(this.originalFollows);
                    } else {
                        arrayList.addAll(diff(this.originalFollows, followIds));
                    }
                }
                ApiClient.followSubs(followIds, arrayList).compose(bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
                    public void accept(BaseResult baseResult) throws Exception {
                        QuestionActivity.this.dismissProDialog();
                        if (baseResult != null && baseResult.getErrno() == 0) {
                            QuestionActivity.this.finish();
                            RefreshManager.init().startRefresh(true);
                        }
                    }
                }, new Consumer<Throwable>() {
                    public void accept(Throwable th) throws Exception {
                        QuestionActivity.this.dismissProDialog();
                    }
                });
                return;
            }
            finish();
            RefreshManager.init().startRefresh(true);
        }
    }

    /* access modifiers changed from: private */
    public List<String> getFollowIds(List<List<QuestionsModel.DataBean.StepsBean>> list2) {
        ArrayList arrayList = new ArrayList();
        for (List<QuestionsModel.DataBean.StepsBean> next : list2) {
            if (next != null) {
                for (QuestionsModel.DataBean.StepsBean stepsBean : next) {
                    if (stepsBean.following) {
                        arrayList.add(stepsBean.fid + "");
                    }
                }
            }
        }
        return arrayList;
    }

    public List<String> diff(List<String> list2, List<String> list3) {
        ArrayList arrayList = new ArrayList();
        for (String next : list2) {
            if (!list3.contains(next)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }
}
