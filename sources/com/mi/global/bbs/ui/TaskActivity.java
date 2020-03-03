package com.mi.global.bbs.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.exoplayer2.C;
import com.mi.global.bbs.R;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.model.ClaimRewardResult;
import com.mi.global.bbs.model.TaskModel;
import com.mi.global.bbs.model.TaskNewBieModel;
import com.mi.global.bbs.model.UserInfoModel;
import com.mi.global.bbs.ui.post.PostActivity;
import com.mi.global.bbs.ui.user.EditUserInfoActivity;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.view.TaskItemView;
import com.mi.global.bbs.view.TaskListView;
import com.trello.rxlifecycle2.android.ActivityEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.Iterator;
import java.util.List;

public class TaskActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, TaskListView.OnItemClickListener {
    public static final int MISSION_STATUS_COMPLETE = 1;
    public static final int MISSION_STATUS_COMPLETE_FINISH = 2;
    public static final int MISSION_STATUS_UNCOMPLETE = 0;
    private static final int NEW_BIE_CLAIM_REWARD_TYPE_FINISH = 1;
    private static final String TAG = "TaskActivity";
    private final int TO_POST_REQUEST_CODE = 128;
    @BindView(2131493762)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(2131494060)
    ScrollView mTaskScroll;
    @BindView(2131493640)
    ImageView missionRabbit;
    @BindView(2131493739)
    LinearLayout newBieMissionCompleteLayout;
    @BindView(2131493733)
    TextView newBieMissionTitle;
    @BindView(2131493740)
    TaskListView newbieListLayout;
    @BindView(2131493176)
    TaskListView taskListLayout;
    @BindView(2131493641)
    TextView tvMissionTitle;
    /* access modifiers changed from: private */
    public UserInfoModel.DataBean userInfo = null;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        this.contentNeedMargin = false;
        super.onCreate(bundle);
        setCustomContentView(R.layout.bbs_activity_task);
        ButterKnife.bind((Activity) this);
        setTitleAndBack(R.string.mission, this.titleBackListener);
        this.mToolBarContainer.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.main_yellow)));
        this.mToolBarContainer.getBackground().setAlpha(0);
        this.mToolBarDivider.setVisibility(4);
        this.taskListLayout.addItemStringAndIconArray(getResources().getStringArray(R.array.task_titles));
        this.taskListLayout.setOnItemClickListener(this);
        this.newbieListLayout.addItemStringAndIconArray(getResources().getStringArray(R.array.newbie_titles));
        this.newbieListLayout.setOnItemClickListener(this);
        this.mRefreshLayout.setOnRefreshListener(this);
        this.mRefreshLayout.setColorSchemeResources(R.color.main_yellow_low, R.color.main_yellow);
        this.mRefreshLayout.setRefreshing(true);
        this.mContentLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                TaskActivity.this.mContentLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int height = TaskActivity.this.mToolBarContainer.getHeight();
                TaskActivity.this.mTaskScroll.setPadding(0, height, 0, 0);
                TaskActivity.this.mRefreshLayout.setProgressViewEndTarget(true, height * 2);
            }
        });
        onRefresh();
        GoogleTrackerUtil.sendRecordPage(TAG);
    }

    private void getData() {
        ApiClient.getTask(bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<TaskModel>() {
            public void accept(@NonNull TaskModel taskModel) throws Exception {
                if (taskModel != null && taskModel.getTasks() != null) {
                    TaskActivity.this.updateMissonMesView(taskModel.getTasks());
                    TaskActivity.this.taskListLayout.setTaskData(TaskModel.makeTaskItemViewModelData(taskModel.getTasks()));
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                TaskActivity.this.mRefreshLayout.setRefreshing(false);
            }
        });
        ApiClient.getNewBieTask(bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<TaskNewBieModel>() {
            public void accept(@NonNull TaskNewBieModel taskNewBieModel) throws Exception {
                TaskActivity.this.updateNewBieMissIonView(taskNewBieModel);
                if (taskNewBieModel != null) {
                    TaskActivity.this.newbieListLayout.setTaskData(TaskNewBieModel.makeTaskData(taskNewBieModel));
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                TaskActivity.this.mRefreshLayout.setRefreshing(false);
            }
        });
        ApiClient.getUserInfo(bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<UserInfoModel>() {
            public void accept(@NonNull UserInfoModel userInfoModel) throws Exception {
                TaskActivity.this.mRefreshLayout.setRefreshing(false);
                if (userInfoModel != null && userInfoModel.data != null) {
                    UserInfoModel.DataBean unused = TaskActivity.this.userInfo = userInfoModel.data;
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                TaskActivity.this.mRefreshLayout.setRefreshing(false);
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateMissonMesView(List<TaskModel.TasksBean> list) {
        if (list != null) {
            int i = 0;
            for (TaskModel.TasksBean taskstatus : list) {
                if (taskstatus.getTaskstatus() > 0) {
                    i++;
                }
            }
            if (i == 3) {
                this.missionRabbit.setImageDrawable(getResources().getDrawable(R.drawable.bbs_mission_sucess));
                this.tvMissionTitle.setText(R.string.have_complete_mission);
                return;
            }
            this.missionRabbit.setImageDrawable(getResources().getDrawable(R.drawable.bbs_mission_fail));
            this.tvMissionTitle.setText(R.string.not_complete_mission);
        }
    }

    /* access modifiers changed from: private */
    public void updateNewBieMissIonView(TaskNewBieModel taskNewBieModel) {
        if (taskNewBieModel == null || taskNewBieModel.getData() == null || taskNewBieModel.getData().getTasks() == null) {
            this.newBieMissionCompleteLayout.setVisibility(8);
            this.newBieMissionTitle.setVisibility(8);
            this.newbieListLayout.setVisibility(8);
        } else if (newBieMissionIsComplete(taskNewBieModel)) {
            this.newBieMissionCompleteLayout.setVisibility(0);
            this.newBieMissionTitle.setVisibility(0);
            this.newbieListLayout.setVisibility(8);
        } else {
            this.newBieMissionCompleteLayout.setVisibility(8);
            this.newBieMissionTitle.setVisibility(0);
            this.newbieListLayout.setVisibility(0);
        }
    }

    private boolean newBieMissionIsComplete(TaskNewBieModel taskNewBieModel) {
        boolean z;
        Iterator<TaskNewBieModel.TaskDataBean.TaskNewBieBean> it = taskNewBieModel.getData().getTasks().iterator();
        loop0:
        while (true) {
            z = true;
            while (true) {
                if (!it.hasNext()) {
                    break loop0;
                }
                TaskNewBieModel.TaskDataBean.TaskNewBieBean next = it.next();
                if (!z || next.getTaskstatus() <= 0) {
                    z = false;
                }
            }
        }
        if (!z || taskNewBieModel.getData().getFinish() != 2) {
            return false;
        }
        return true;
    }

    public void onRefresh() {
        getData();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        getData();
    }

    public void onItemClick(TaskListView taskListView, int i, TaskItemView.TaskItemState taskItemState) {
        if (taskItemState == TaskItemView.TaskItemState.UNFINISHED) {
            if (taskListView.getTaskItemView(i) != null && taskListView.getTaskItemView(i).getTag() != null) {
                int intValue = ((Integer) taskListView.getTaskItemView(i).getTag()).intValue();
                if (TaskNewBieModel.isNewBieMission(intValue)) {
                    newBieMissionItemClickJump(intValue);
                } else {
                    todayMissionItemClickJump(i);
                }
            }
        } else if (taskItemState == TaskItemView.TaskItemState.AWARD && taskListView.getTaskItemView(i) != null && taskListView.getTaskItemView(i).getTag() != null) {
            int intValue2 = ((Integer) taskListView.getTaskItemView(i).getTag()).intValue();
            if (TaskNewBieModel.isNewBieMission(intValue2)) {
                newBieClaimAward(intValue2);
            } else {
                claimAward(intValue2);
            }
        }
    }

    private void newBieMissionItemClickJump(int i) {
        if (i == 1 || i == 2) {
            startActivity(new Intent(this, EditUserInfoActivity.class).putExtra("user", this.userInfo));
        } else if (i == 3) {
            DiscoverPeopleActivity.show(this);
        } else if (i == 4) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setAction(MainActivity.ACTION_JUMP_TO_MAIN_ACTIVITY);
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            startActivity(intent);
        }
    }

    private void todayMissionItemClickJump(int i) {
        if (i == 0) {
            jumpToPostActivity();
        } else {
            jumpToTarget();
        }
    }

    private void jumpToTarget() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setAction(MainActivity.ACTION_JUMP_TO_MAIN_ACTIVITY_HOME);
        intent.addFlags(268468224);
        startActivity(intent);
    }

    private void jumpToPostActivity() {
        startActivityForResult(new Intent(this, PostActivity.class), 128);
    }

    private void newBieClaimAward(int i) {
        showProDialog(getString(R.string.claim_awarding));
        if (i == 4) {
            newBieFinishRequest();
        } else {
            newBieClaimAwardRequest(i);
        }
    }

    private void newBieClaimAwardRequest(int i) {
        ApiClient.newBieClaimReward(i, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<ClaimRewardResult>() {
            public void accept(@NonNull ClaimRewardResult claimRewardResult) throws Exception {
                TaskActivity.this.dismissProDialog();
                TaskActivity.this.toast(TaskActivity.this.getQuantityString(R.plurals.add_points, claimRewardResult.getPoint()));
                TaskActivity.this.onRefresh();
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                TaskActivity.this.dismissProDialog();
            }
        });
    }

    private void newBieFinishRequest() {
        ApiClient.newBieFinish(1, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<ClaimRewardResult>() {
            public void accept(@NonNull ClaimRewardResult claimRewardResult) throws Exception {
                TaskActivity.this.dismissProDialog();
                TaskActivity.this.toast(TaskActivity.this.getResources().getString(R.string.newbie_mission_medal));
                TaskActivity.this.onRefresh();
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                TaskActivity.this.dismissProDialog();
            }
        });
    }

    private void claimAward(int i) {
        showProDialog(getString(R.string.claim_awarding));
        ApiClient.claimReward(i, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<ClaimRewardResult>() {
            public void accept(@NonNull ClaimRewardResult claimRewardResult) throws Exception {
                TaskActivity.this.dismissProDialog();
                TaskActivity.this.toast(TaskActivity.this.getQuantityString(R.plurals.add_points, claimRewardResult.getPoint()));
                TaskActivity.this.onRefresh();
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                TaskActivity.this.dismissProDialog();
            }
        });
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 128 && i2 == -1) {
            this.mRefreshLayout.setRefreshing(true);
            onRefresh();
        }
    }
}
