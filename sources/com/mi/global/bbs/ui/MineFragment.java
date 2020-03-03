package com.mi.global.bbs.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.gson.Gson;
import com.mi.global.bbs.BBSApplication;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.base.BaseFragment;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.UrlAction;
import com.mi.global.bbs.model.UserInfoModel;
import com.mi.global.bbs.observer.DataManager;
import com.mi.global.bbs.ui.checkin.SignActivity;
import com.mi.global.bbs.ui.user.MyFavorActivity;
import com.mi.global.bbs.ui.user.MyRepliesActivity;
import com.mi.global.bbs.ui.user.MyThreadActivity;
import com.mi.global.bbs.ui.user.UserCenterActivity;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.Utils;
import com.mi.global.bbs.view.AppSettingsItem;
import com.mi.global.bbs.view.CircleImageView;
import com.mi.global.bbs.view.dialog.MPopupWindow;
import com.trello.rxlifecycle2.android.FragmentEvent;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.Observable;
import java.util.Observer;

public class MineFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, Observer {
    private static final String FIRST_IN = "first_in_not_sign";
    @BindView(2131493359)
    AppSettingsItem fragmentMeCheckIn;
    @BindView(2131493360)
    AppSettingsItem fragmentMeFavor;
    @BindView(2131493361)
    LinearLayout fragmentMeMedalLayout;
    @BindView(2131493362)
    TextView fragmentMeMedalSubtext;
    @BindView(2131493363)
    TextView fragmentMeMedalText;
    @BindView(2131493364)
    AppSettingsItem fragmentMeMission;
    @BindView(2131493365)
    TextView fragmentMeMoment;
    @BindView(2131493366)
    TextView fragmentMeName;
    @BindView(2131493367)
    AppSettingsItem fragmentMeNotify;
    @BindView(2131493368)
    LinearLayout fragmentMePointsLayout;
    @BindView(2131493369)
    TextView fragmentMePointsSubtext;
    @BindView(2131493370)
    TextView fragmentMePointsText;
    @BindView(2131493371)
    SwipeRefreshLayout fragmentMeRefresh;
    @BindView(2131493372)
    AppSettingsItem fragmentMeReply;
    @BindView(2131493374)
    ScrollView fragmentMeScroll;
    @BindView(2131493375)
    AppSettingsItem fragmentMeThreads;
    @BindView(2131493373)
    FrameLayout mFragmentMeRoot;
    /* access modifiers changed from: private */
    public UserInfoModel.DataBean userInfo;
    @BindView(2131494218)
    CircleImageView userItemIcon;

    public static void jump(Context context) {
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.bbs_fragment_me, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        ButterKnife.bind((Object) this, view);
        DataManager.init().addObserver(this);
        initView();
    }

    private void initView() {
        this.fragmentMeRefresh.setOnRefreshListener(this);
        this.fragmentMeRefresh.setColorSchemeResources(R.color.main_yellow_low, R.color.main_yellow);
        boolean hasLogin = LoginManager.getInstance().hasLogin();
        if (hasLogin) {
            getData();
            loginState();
        } else {
            notLogState();
        }
        if (!Utils.Preference.getBooleanPref(BBSApplication.getInstance(), FIRST_IN, false)) {
            Utils.Preference.setBooleanPref(BBSApplication.getInstance(), FIRST_IN, true);
            if (!hasLogin) {
                this.fragmentMeRefresh.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                        MineFragment.this.fragmentMeRefresh.removeOnLayoutChangeListener(this);
                        new MPopupWindow.Builder(MineFragment.this.getActivity()).setView(R.layout.bbs_welcome_pop_layout).create().showAsDropDown(MineFragment.this.userItemIcon, MineFragment.this.userItemIcon.getWidth() / 2, 6);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public void loginState() {
        String stringPref = Utils.Preference.getStringPref(BBSApplication.getInstance(), Constants.Account.PREF_USER_NAMES, "");
        String stringPref2 = Utils.Preference.getStringPref(BBSApplication.getInstance(), Constants.Account.PREF_USER_ICON, "");
        this.fragmentMePointsText.setText(R.string.points);
        this.fragmentMePointsSubtext.setText("");
        this.fragmentMeMedalSubtext.setText("");
        this.fragmentMeMedalText.setText(R.string.medals);
        this.fragmentMeName.setText(stringPref);
        ImageLoader.showHeadIcon(this.userItemIcon, stringPref2);
        this.fragmentMeMoment.setText(R.string.view_and_edit_your_profile);
    }

    /* access modifiers changed from: private */
    public void notLogState() {
        this.fragmentMePointsText.setText(R.string.points);
        this.fragmentMePointsSubtext.setText(R.string.sign_in_to_check);
        this.fragmentMeMedalSubtext.setText(R.string.sign_in_to_check);
        this.fragmentMeMedalText.setText(R.string.medals);
        this.fragmentMeName.setText(R.string.visitor);
        this.fragmentMeMoment.setText(R.string.sign_in_to_get_more_features);
        this.userItemIcon.setImageResource(R.drawable.bbs_user_head_icon);
    }

    /* access modifiers changed from: private */
    public void getData() {
        ApiClient.getUserInfo(bindUntilEvent(FragmentEvent.DESTROY_VIEW)).subscribe(new Consumer<UserInfoModel>() {
            public void accept(@NonNull UserInfoModel userInfoModel) throws Exception {
                MineFragment.this.dismissProgress();
                MineFragment.this.handleData(userInfoModel);
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                MineFragment.this.dismissProgress();
            }
        });
    }

    public void onRefresh() {
        getData();
    }

    @OnClick({2131493358, 2131493368, 2131493361, 2131493367, 2131493364, 2131493359, 2131493360, 2131493375, 2131493372})
    public void onClick(View view) {
        if (view.getId() == R.id.fragment_me_center) {
            login(new Runnable() {
                public void run() {
                    MineFragment.this.startActivity(new Intent(MineFragment.this.getActivity(), UserCenterActivity.class).putExtra("user", MineFragment.this.userInfo));
                }
            });
        } else if (view.getId() == R.id.fragment_me_points_layout) {
            login(new Runnable() {
                public void run() {
                    WebActivity.jump(MineFragment.this.getActivity(), ApiClient.getAppUrl(UrlAction.MY_POINT));
                }
            });
        } else if (view.getId() == R.id.fragment_me_medal_layout) {
            login(new Runnable() {
                public void run() {
                    WebActivity.jump(MineFragment.this.getActivity(), ApiClient.getAppUrl(UrlAction.MEDAL_CENTER));
                }
            });
        } else if (view.getId() == R.id.fragment_me_missions) {
            login(new Runnable() {
                public void run() {
                    GoogleTrackerUtil.sendRecordEvent("me", "click_mission", "click_mission");
                    MineFragment.this.startActivity(new Intent(MineFragment.this.getActivity(), TaskActivity.class));
                }
            });
        } else if (view.getId() == R.id.fragment_me_check_in) {
            System.out.println("onClick Check-in");
            login(new Runnable() {
                public void run() {
                    MineFragment.this.startActivity(new Intent(MineFragment.this.getActivity(), SignActivity.class));
                }
            });
        } else if (view.getId() == R.id.fragment_me_notify) {
            login(new Runnable() {
                public void run() {
                    WebActivity.jump(MineFragment.this.getActivity(), ApiClient.getAppUrl(UrlAction.MY_MSG));
                }
            });
        } else if (view.getId() == R.id.fragment_me_favor) {
            login(new Runnable() {
                public void run() {
                    MineFragment.this.startActivity(new Intent(MineFragment.this.getActivity(), MyFavorActivity.class));
                }
            });
        } else if (view.getId() == R.id.fragment_me_threads) {
            login(new Runnable() {
                public void run() {
                    MineFragment.this.startActivity(new Intent(MineFragment.this.getActivity(), MyThreadActivity.class));
                }
            });
        } else if (view.getId() == R.id.fragment_me_reply) {
            login(new Runnable() {
                public void run() {
                    MyRepliesActivity.jump(MineFragment.this.getActivity(), LoginManager.getInstance().getUserId());
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void handleData(UserInfoModel userInfoModel) {
        if (userInfoModel != null && userInfoModel.data != null) {
            Utils.Preference.setStringPref(getActivity(), "userInfo", new Gson().toJson((Object) userInfoModel));
            inflateUi(userInfoModel.data);
        }
    }

    private void inflateUi(UserInfoModel.DataBean dataBean) {
        if (getActivity() != null) {
            this.userInfo = dataBean;
            TextView textView = this.fragmentMePointsText;
            FragmentActivity activity = getActivity();
            int i = R.string.points_;
            boolean z = true;
            textView.setText(activity.getString(i, new Object[]{dataBean.points + ""}));
            this.fragmentMePointsSubtext.setText(dataBean.authortitle);
            this.fragmentMeMedalSubtext.setText("");
            TextView textView2 = this.fragmentMeMedalText;
            FragmentActivity activity2 = getActivity();
            int i2 = R.string.medals_;
            textView2.setText(activity2.getString(i2, new Object[]{dataBean.medalCount + ""}));
            this.fragmentMeName.setText(dataBean.username);
            ImageLoader.showHeadIcon(this.userItemIcon, dataBean.icon);
            MineActivity mineActivity = (MineActivity) getActivity();
            if (dataBean.alarm <= 0) {
                z = false;
            }
            mineActivity.showNotificationRedDot(z);
        }
    }

    /* access modifiers changed from: private */
    public void dismissProgress() {
        if (this.fragmentMeRefresh != null) {
            this.fragmentMeRefresh.setRefreshing(false);
        }
    }

    private void login(Runnable runnable) {
        ((BaseActivity) getActivity()).login(runnable);
    }

    public void onDestroyView() {
        super.onDestroyView();
        DataManager.init().deleteObserver(this);
    }

    public void update(Observable observable, Object obj) {
        if (obj != null && (obj instanceof Boolean)) {
            if (((Boolean) obj).booleanValue()) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        MineFragment.this.loginState();
                        MineFragment.this.getData();
                    }
                });
            } else {
                runOnUiThread(new Runnable() {
                    public void run() {
                        MineFragment.this.notLogState();
                    }
                });
            }
        }
    }

    public void runOnUiThread(Runnable runnable) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            runnable.run();
        } else {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }

    public void setRootBottomMargin(int i) {
        if (this.mFragmentMeRoot != null) {
            ((FrameLayout.LayoutParams) this.mFragmentMeRoot.getLayoutParams()).bottomMargin = i;
            this.mFragmentMeRoot.requestLayout();
        }
    }
}
