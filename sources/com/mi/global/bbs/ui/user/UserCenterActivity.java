package com.mi.global.bbs.ui.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.facebook.CallbackManager;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.adapter.MyViewPagerAdapter;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.UrlAction;
import com.mi.global.bbs.model.BaseResult;
import com.mi.global.bbs.model.UploadResultModel;
import com.mi.global.bbs.model.UserCenterShareModel;
import com.mi.global.bbs.model.UserInfoModel;
import com.mi.global.bbs.observer.DataManager;
import com.mi.global.bbs.ui.WebActivity;
import com.mi.global.bbs.ui.activity.ActivitiesActivity;
import com.mi.global.bbs.ui.column.ColumnDetailActivity;
import com.mi.global.bbs.utils.CheckUtil;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.DialogFactory;
import com.mi.global.bbs.utils.FileUtils;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.ImageLoader;
import com.mi.global.bbs.utils.PermissionClaimer;
import com.mi.global.bbs.view.AbsorbNavigationLayout;
import com.mi.global.bbs.view.HeadLogoView;
import com.mi.global.bbs.view.PagerSlidingTabStrip;
import com.mi.global.bbs.view.dialog.ShareDialog;
import com.mi.multi_image_selector.MultiImageSelector;
import com.mi.util.ResourceUtil;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.xiaomi.smarthome.library.common.widget.crop.CropImageActivity;
import com.xiaomi.youpin.share.ShareObject;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.AgentOptions;

public class UserCenterActivity extends BaseActivity implements Observer {
    private static final int REQUEST_CROP = 27;
    private static final int REQUEST_IMAGE = 17;
    private static final String UID_KEY = "uid";
    private static final String USER_PROFILE_BG_NAME = "user_bg";
    /* access modifiers changed from: private */
    public UserCenterShareModel.FacebookBean facebook = null;
    private Uri imageUri;
    @BindView(2131494189)
    TextView mUserCenterColumnBt;
    /* access modifiers changed from: private */
    public String myId;
    /* access modifiers changed from: private */
    public int retainHeight = 0;
    /* access modifiers changed from: private */
    public String uid;
    /* access modifiers changed from: private */
    public UserActivitiesFragment userActivitiesFragment;
    @BindView(2131494186)
    TextView userCenterBottomFollowBt;
    @BindView(2131494187)
    LinearLayout userCenterBottomLayout;
    /* access modifiers changed from: private */
    public ImageView userCenterEdit;
    @BindView(2131494191)
    FrameLayout userCenterFollowTab;
    @BindView(2131494193)
    TextView userCenterFollowerTx;
    @BindView(2131494195)
    TextView userCenterFollowingTx;
    @BindView(2131494196)
    TextView userCenterGroupText;
    @BindView(2131494197)
    LinearLayout userCenterMedalContainer;
    @BindView(2131494198)
    PagerSlidingTabStrip userCenterNagTab;
    @BindView(2131494199)
    TextView userCenterNameText;
    @BindView(2131494200)
    AbsorbNavigationLayout userCenterNavigationContainer;
    @BindView(2131494201)
    ViewPager userCenterPager;
    @BindView(2131494203)
    TextView userCenterRepliesTx;
    /* access modifiers changed from: private */
    public ImageView userCenterShare;
    @BindView(2131494206)
    TextView userCenterThreadsTx;
    @BindView(2131494207)
    ImageView userCenterTopBg;
    @BindView(2131494208)
    FrameLayout userCenterTopBgFrame;
    @BindView(2131494209)
    LinearLayout userCenterTopLayout;
    /* access modifiers changed from: private */
    public UserInfoModel.DataBean userInfo;
    @BindView(2131494218)
    HeadLogoView userItemIcon;
    /* access modifiers changed from: private */
    public UserProfileFragment userProfileFragment;

    public static void jump(Context context, String str) {
        context.startActivity(new Intent(context, UserCenterActivity.class).putExtra("uid", str));
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        this.contentNeedMargin = false;
        this.statusBarDark = false;
        super.onCreate(bundle);
        setTitleAndBack("", this.titleBackListener);
        this.mUpImageView.setImageResource(R.drawable.bbs_arrow_up_white);
        String imageCacheFileName = FileUtils.getImageCacheFileName(USER_PROFILE_BG_NAME);
        if (!TextUtils.isEmpty(imageCacheFileName)) {
            this.imageUri = Uri.fromFile(new File(imageCacheFileName));
        }
        setCustomContentView(R.layout.bbs_activity_user_center);
        ButterKnife.bind((Activity) this);
        this.callbackManager = CallbackManager.Factory.create();
        DataManager.init().addObserver(this);
        this.myId = LoginManager.getInstance().getUserId();
        initView();
        adjustTopMargin();
    }

    private void adjustTopMargin() {
        int statusBarHeight = getStatusBarHeight();
        if (statusBarHeight > 0) {
            ((ViewGroup.MarginLayoutParams) this.userCenterTopLayout.getLayoutParams()).topMargin = statusBarHeight;
            this.userCenterTopLayout.requestLayout();
        }
    }

    private void initView() {
        initToolbarItem();
        initTab();
        this.userCenterNavigationContainer.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                UserCenterActivity.this.userCenterNavigationContainer.removeOnLayoutChangeListener(this);
                int unused = UserCenterActivity.this.retainHeight = UserCenterActivity.this.mToolBarContainer.getMeasuredHeight();
                UserCenterActivity.this.userCenterNavigationContainer.setRetainHeight(UserCenterActivity.this.retainHeight);
                if (UserCenterActivity.this.userActivitiesFragment != null) {
                    UserCenterActivity.this.userActivitiesFragment.setPadding(UserCenterActivity.this.retainHeight);
                }
                if (UserCenterActivity.this.userProfileFragment != null) {
                    UserCenterActivity.this.userProfileFragment.setPadding(UserCenterActivity.this.retainHeight);
                }
            }
        });
        this.mTitleView.setVisibility(4);
        this.mToolBarContainer.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.main_title_bar_bg)));
        this.mToolBarContainer.getBackground().setAlpha(0);
        this.mToolBarDivider.setVisibility(4);
        this.userCenterNavigationContainer.setOnScrollProgressListener(new AbsorbNavigationLayout.OnScrollProgressListener() {
            public void onScrollProgress(float f) {
                if (((int) (((float) UserCenterActivity.this.userCenterNavigationContainer.getMoveHeight()) * f)) >= (UserCenterActivity.this.userCenterNameText.getTop() - UserCenterActivity.this.mTitleView.getTop()) - UserCenterActivity.this.mToolbarAgent.getHeight()) {
                    UserCenterActivity.this.statusBarDarkMode(true);
                    UserCenterActivity.this.userCenterNameText.setVisibility(4);
                    UserCenterActivity.this.mTitleView.setVisibility(0);
                    UserCenterActivity.this.mToolBarDivider.setVisibility(0);
                    UserCenterActivity.this.mUpImageView.setImageResource(R.drawable.bbs_arrow_up_black);
                    if (UserCenterActivity.this.userCenterEdit.getVisibility() == 0) {
                        UserCenterActivity.this.userCenterEdit.setImageResource(R.drawable.bbs_user_center_edit_icon);
                    }
                    if (UserCenterActivity.this.userCenterShare.getVisibility() == 0) {
                        UserCenterActivity.this.userCenterShare.setImageResource(R.drawable.bbs_user_center_share);
                    }
                } else {
                    UserCenterActivity.this.statusBarDarkMode(false);
                    UserCenterActivity.this.userCenterNameText.setVisibility(0);
                    UserCenterActivity.this.mTitleView.setVisibility(4);
                    UserCenterActivity.this.mUpImageView.setImageResource(R.drawable.bbs_arrow_up_white);
                    if (UserCenterActivity.this.userCenterEdit.getVisibility() == 0) {
                        UserCenterActivity.this.userCenterEdit.setImageResource(R.drawable.bbs_user_center_edit_icon_white);
                    }
                    if (UserCenterActivity.this.userCenterShare.getVisibility() == 0) {
                        UserCenterActivity.this.userCenterShare.setImageResource(R.drawable.bbs_user_center_share_white);
                    }
                    UserCenterActivity.this.mToolBarDivider.setVisibility(4);
                }
                UserCenterActivity.this.mToolBarContainer.getBackground().setAlpha((int) (f * 255.0f));
            }
        });
        this.mToolBarContainer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
    }

    private void initToolbarItem() {
        LayoutInflater.from(this).inflate(R.layout.bbs_user_center_toolbar_action_layout, this.menuLayout, true);
        this.userCenterEdit = (ImageView) this.menuLayout.findViewById(R.id.user_center_edit);
        this.userCenterShare = (ImageView) this.menuLayout.findViewById(R.id.user_center_share);
        this.userCenterEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UserCenterActivity.this.startActivity(new Intent(UserCenterActivity.this, EditUserInfoActivity.class).putExtra("user", UserCenterActivity.this.userInfo));
            }
        });
        this.userCenterShare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (UserCenterActivity.this.facebook == null) {
                    UserCenterActivity.this.showProDialog(UserCenterActivity.this.getString(R.string.bbs_loading));
                    ApiClient.getUserShare(UserCenterActivity.this.getWatchId(), UserCenterActivity.this.bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<UserCenterShareModel>() {
                        public void accept(@NonNull UserCenterShareModel userCenterShareModel) throws Exception {
                            if (!(userCenterShareModel == null || userCenterShareModel.getErrno() != 0 || userCenterShareModel.data == null)) {
                                UserCenterShareModel.FacebookBean unused = UserCenterActivity.this.facebook = userCenterShareModel.data;
                                UserCenterActivity.this.share(userCenterShareModel.data);
                            }
                            UserCenterActivity.this.dismissProDialog();
                        }
                    }, new Consumer<Throwable>() {
                        public void accept(@NonNull Throwable th) throws Exception {
                            UserCenterActivity.this.dismissProDialog();
                        }
                    });
                    return;
                }
                UserCenterActivity.this.share(UserCenterActivity.this.facebook);
            }
        });
    }

    private void initTab() {
        ArrayList arrayList = new ArrayList();
        this.userActivitiesFragment = new UserActivitiesFragment();
        this.userProfileFragment = new UserProfileFragment();
        this.userInfo = (UserInfoModel.DataBean) getIntent().getParcelableExtra("user");
        this.uid = getIntent().getStringExtra("uid");
        this.userCenterEdit.setVisibility(!TextUtils.isEmpty(this.uid) ? 8 : 0);
        if (this.userInfo != null) {
            this.userProfileFragment.setUserInfo(this.userInfo);
            this.userActivitiesFragment.setUserName(this.userInfo.username);
            bindUserData(this.userInfo);
        } else if (!TextUtils.isEmpty(this.uid)) {
            this.userCenterBottomLayout.setVisibility(0);
            getData(this.uid);
            if (!TextUtils.isEmpty(this.myId) && this.uid.equals(this.myId)) {
                this.userCenterBottomLayout.setVisibility(8);
                this.userCenterEdit.setVisibility(0);
            }
        } else if (!TextUtils.isEmpty(this.myId)) {
            getData(this.myId);
        }
        this.userActivitiesFragment.setUid(this.uid);
        arrayList.add(this.userActivitiesFragment);
        arrayList.add(this.userProfileFragment);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.userCenterPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.user_tab), arrayList));
        this.userCenterNagTab.setViewPager(this.userCenterPager);
        this.userCenterNagTab.setTextColor(Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR));
        this.userCenterNagTab.setSelectedTextColor(Color.parseColor(ActivitiesActivity.ONLINE_ACTIVITY_TAB_TEXT_COLOR_SELECT));
        this.userCenterNagTab.setTextSize((int) TypedValue.applyDimension(2, 15.0f, displayMetrics));
        this.userCenterNagTab.setDividerColor(0);
        this.userCenterNagTab.setAllCaps(false);
        this.userCenterNagTab.setShouldExpand(true);
    }

    private void getData(String str) {
        ApiClient.getUserInfo(str, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<UserInfoModel>() {
            public void accept(@NonNull UserInfoModel userInfoModel) throws Exception {
                if (userInfoModel != null && userInfoModel.data != null) {
                    UserInfoModel.DataBean unused = UserCenterActivity.this.userInfo = userInfoModel.data;
                    UserCenterActivity.this.bindUserData(userInfoModel.data);
                    UserCenterActivity.this.userProfileFragment.inflaterUi(userInfoModel.data);
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                UserCenterActivity.this.bindUserData((UserInfoModel.DataBean) null);
            }
        });
    }

    /* access modifiers changed from: private */
    public void bindUserData(final UserInfoModel.DataBean dataBean) {
        if (dataBean != null) {
            setTitle((CharSequence) dataBean.username);
            this.userCenterNameText.setText(dataBean.username);
            this.userCenterFollowerTx.setText(dataBean.followerCount);
            TextView textView = this.userCenterThreadsTx;
            textView.setText(dataBean.threads + "");
            this.userCenterGroupText.setText(dataBean.authortitle);
            int i = 0;
            this.userCenterGroupText.setVisibility(0);
            this.userCenterFollowingTx.setText(dataBean.followingCount);
            this.userCenterRepliesTx.setText(dataBean.replies);
            this.userCenterShare.setVisibility(dataBean.shareFlag == 0 ? 8 : 0);
            if (dataBean.shareFlag == 0) {
                ((LinearLayout.LayoutParams) this.userCenterEdit.getLayoutParams()).rightMargin = getResources().getDimensionPixelSize(R.dimen.padding_normal);
                this.userCenterEdit.requestLayout();
            }
            if (!TextUtils.isEmpty(this.uid) && this.userCenterBottomLayout.getVisibility() == 0 && dataBean.followFlag != 0) {
                followState();
            }
            this.userActivitiesFragment.setUserName(dataBean.username);
            ImageLoader.showHeadLogo(this.userItemIcon, dataBean.icon, 1, dataBean.groupid);
            showBg(dataBean.bkgimg);
            createMedalUi(dataBean);
            TextView textView2 = this.mUserCenterColumnBt;
            if (dataBean.isColunmWriter != 1) {
                i = 8;
            }
            textView2.setVisibility(i);
            this.mUserCenterColumnBt.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    UserCenterActivity userCenterActivity = UserCenterActivity.this;
                    ColumnDetailActivity.jumpWithId(userCenterActivity, dataBean.columnId + "");
                }
            });
        }
    }

    private void showBg(String str) {
        ImageLoader.showImage(this.userCenterTopBg, Constants.USER_TOP_BG_URL);
        if (!TextUtils.isEmpty(str)) {
            ImageLoader.showImage(this.userCenterTopBg, str);
        }
    }

    private void createMedalUi(UserInfoModel.DataBean dataBean) {
        if (dataBean.medals != null && dataBean.medals.size() > 0) {
            this.userCenterMedalContainer.removeAllViews();
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.medal_icon_size);
            int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.padding_normal);
            int dimensionPixelSize3 = getResources().getDimensionPixelSize(R.dimen.dimen_20);
            int i = 0;
            while (i < dataBean.medals.size() && i < 7) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dimensionPixelSize, dimensionPixelSize, 1.0f);
                layoutParams.topMargin = dimensionPixelSize2;
                layoutParams.bottomMargin = dimensionPixelSize2;
                if (i == 6) {
                    layoutParams.setMarginEnd(0);
                } else {
                    layoutParams.setMarginEnd(dimensionPixelSize3);
                }
                if (i == 0) {
                    layoutParams.setMarginStart(dimensionPixelSize2);
                } else {
                    layoutParams.setMarginStart(0);
                }
                UserInfoModel.DataBean.MedalsBean medalsBean = dataBean.medals.get(i);
                ImageView imageView = new ImageView(this);
                ImageLoader.showImage(imageView, medalsBean.image);
                final String str = medalsBean.medalid;
                imageView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        GoogleTrackerUtil.sendRecordEvent("profile", "click_medal", String.format("click_medal_", new Object[]{(TextUtils.isEmpty(UserCenterActivity.this.myId) || !UserCenterActivity.this.myId.equals(UserCenterActivity.this.uid)) ? "1" : "0"}));
                        UserCenterActivity userCenterActivity = UserCenterActivity.this;
                        WebActivity.jump(userCenterActivity, ApiClient.getAppUrl(UrlAction.MEDAL_DETAIL + str));
                    }
                });
                this.userCenterMedalContainer.addView(imageView, layoutParams);
                i++;
            }
            TextView textView = new TextView(this);
            Drawable drawable = getResources().getDrawable(R.drawable.bbs_item_next_icon);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            textView.setCompoundDrawables((Drawable) null, (Drawable) null, drawable, (Drawable) null);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -1);
            layoutParams2.gravity = 16;
            textView.setGravity(16);
            textView.setPadding(dimensionPixelSize3, 0, dimensionPixelSize2, 0);
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    GoogleTrackerUtil.sendRecordEvent("profile", "click_medal_more", String.format(Locale.ENGLISH, "click_medal_more_", new Object[]{(TextUtils.isEmpty(UserCenterActivity.this.myId) || !UserCenterActivity.this.myId.equals(UserCenterActivity.this.uid)) ? "1" : "0"}));
                    WebActivity.jump(UserCenterActivity.this, ApiClient.getAppUrl("home.php?mod=medalcenter&uid=" + CheckUtil.getCheckAes(UserCenterActivity.this.getWatchId())));
                }
            });
            this.userCenterMedalContainer.addView(textView, layoutParams2);
        }
    }

    @OnClick({2131494207, 2131494218, 2131494199, 2131494196, 2131494194, 2131494192, 2131494205, 2131494202, 2131494191, 2131494188, 2131494189})
    public void onClick(View view) {
        if (view.getId() == R.id.user_center_top_bg) {
            if (TextUtils.isEmpty(this.uid)) {
                pickPicture();
            } else if (!TextUtils.isEmpty(this.uid) && !TextUtils.isEmpty(this.myId) && this.uid.equals(this.myId)) {
                pickPicture();
            }
        } else if (view.getId() == R.id.user_center_following) {
            FollowersActivity.show(this, getWatchId(), 2);
        } else if (view.getId() == R.id.user_center_follower) {
            FollowersActivity.show(this, getWatchId(), 1);
        } else if (view.getId() == R.id.user_center_threads) {
            MyThreadActivity.show(this, getWatchId());
        } else if (view.getId() == R.id.user_center_replies) {
            MyRepliesActivity.jump(this, getWatchId());
        } else if (view.getId() == R.id.user_center_follow_tab) {
            follow();
        } else if (view.getId() == R.id.user_center_chat_tab) {
            pullChat();
        }
    }

    private void pickPicture() {
        PermissionClaimer.requestPermissionsWithReasonDialog(this, true, PermissionClaimer.getRequestPermissionReasons(this, R.string.str_permission_access_file), new PermissionClaimer.DefaultPermissionReqListener() {
            public void onGranted() {
                super.onGranted();
                MultiImageSelector.a().b().a((Activity) UserCenterActivity.this, 17, true);
            }
        }, "android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    /* access modifiers changed from: private */
    public String getWatchId() {
        return TextUtils.isEmpty(this.uid) ? this.myId : this.uid;
    }

    private void follow() {
        if (TextUtils.isEmpty(this.myId)) {
            gotoAccount();
        } else if (this.userInfo == null) {
        } else {
            if (this.userInfo.followFlag == 0) {
                showProDialog(getString(R.string.following_ing));
                ApiClient.follow(this.uid, true, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
                    public void accept(@NonNull BaseResult baseResult) throws Exception {
                        UserCenterActivity.this.followResult(baseResult, true);
                    }
                }, new Consumer<Throwable>() {
                    public void accept(@NonNull Throwable th) throws Exception {
                        UserCenterActivity.this.dismissProDialog();
                    }
                });
                return;
            }
            DialogFactory.showAlert((Context) this, getString(R.string.unfollow_hint), getString(R.string.bbs_yes), getString(R.string.bbs_cancel), (DialogFactory.DefaultOnAlertClick) new DialogFactory.DefaultOnAlertClick() {
                public void onOkClick(View view) {
                    UserCenterActivity.this.showProDialog(UserCenterActivity.this.getString(R.string.unfollowing_ing));
                    ApiClient.follow(UserCenterActivity.this.uid, false, UserCenterActivity.this.bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
                        public void accept(@NonNull BaseResult baseResult) throws Exception {
                            UserCenterActivity.this.followResult(baseResult, false);
                        }
                    }, new Consumer<Throwable>() {
                        public void accept(@NonNull Throwable th) throws Exception {
                            UserCenterActivity.this.dismissProDialog();
                        }
                    });
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void followResult(@NonNull BaseResult baseResult, boolean z) {
        StringBuilder sb;
        int intValue;
        if (baseResult.getErrno() == 0) {
            if (z) {
                this.userInfo.followFlag = 1;
                followState();
            } else {
                this.userInfo.followFlag = 0;
                unfollowState();
            }
            if (!TextUtils.isEmpty(this.userCenterFollowerTx.getText().toString())) {
                TextView textView = this.userCenterFollowerTx;
                if (z) {
                    sb = new StringBuilder();
                    intValue = Integer.valueOf(this.userCenterFollowerTx.getText().toString()).intValue() + 1;
                } else {
                    sb = new StringBuilder();
                    intValue = Integer.valueOf(this.userCenterFollowerTx.getText().toString()).intValue() - 1;
                }
                sb.append(intValue);
                sb.append("");
                textView.setText(sb.toString());
            }
        } else {
            toast(baseResult.getErrmsg());
        }
        dismissProDialog();
    }

    private void pullChat() {
        String appUrl = ApiClient.getAppUrl(String.format(UrlAction.MSG_URL, new Object[]{CheckUtil.getCheckAes(this.uid)}));
        if (!TextUtils.isEmpty(this.myId)) {
            WebActivity.jump(this, appUrl);
        } else {
            gotoAccount();
        }
    }

    private void followState() {
        this.userCenterBottomFollowBt.setText(R.string.following);
        setLeftDrawable(this.userCenterBottomFollowBt, R.drawable.bbs_user_center_following_icon);
    }

    private void setLeftDrawable(TextView textView, int i) {
        Drawable drawable = getResources().getDrawable(i);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        textView.setCompoundDrawables(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
    }

    private void unfollowState() {
        this.userCenterBottomFollowBt.setText(R.string.follow);
        setLeftDrawable(this.userCenterBottomFollowBt, R.drawable.bbs_user_center_follow_icon);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        ArrayList<String> stringArrayListExtra;
        super.onActivityResult(i, i2, intent);
        if (i == 17 && i2 == -1 && (stringArrayListExtra = intent.getStringArrayListExtra("select_result")) != null && stringArrayListExtra.size() > 0 && this.imageUri != null) {
            startCropIntent(stringArrayListExtra.get(0));
        }
        if (i == 27 && i2 == -1 && this.imageUri != null) {
            uploadImage(this.imageUri.getPath());
        }
    }

    private void startCropIntent(String str) {
        Uri uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(this, ResourceUtil.a("file_provider_authorities"), new File(str));
            intent.addFlags(1);
        } else {
            uri = Uri.fromFile(new File(str));
        }
        intent.setDataAndType(uri, ShareObject.d);
        intent.putExtra("crop", "true");
        intent.putExtra(CropImageActivity.ASPECT_X, 16);
        intent.putExtra(CropImageActivity.ASPECT_Y, 10);
        intent.putExtra(CropImageActivity.OUTPUT_X, 1080);
        intent.putExtra(CropImageActivity.OUTPUT_Y, 675);
        intent.putExtra("scale", true);
        intent.putExtra(AgentOptions.k, this.imageUri);
        intent.putExtra(CropImageActivity.RETURN_DATA, false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        intent.putExtra(CropImageActivity.SCALE_UP_IF_NEEDED, true);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 27);
        } else {
            uploadImage(str);
        }
    }

    private void uploadImage(String str) {
        showProDialog("");
        ApiClient.uploadImage(str, bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<UploadResultModel>() {
            public void accept(@NonNull UploadResultModel uploadResultModel) throws Exception {
                UserCenterActivity.this.handleUploadResult(uploadResultModel);
                UserCenterActivity.this.dismissProDialog();
                FileUtils.clearCacheImage();
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                UserCenterActivity.this.dismissProDialog();
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleUploadResult(UploadResultModel uploadResultModel) {
        UploadResultModel.DataBean data;
        if (uploadResultModel != null && (data = uploadResultModel.getData()) != null && !TextUtils.isEmpty(data.getUrl())) {
            showBg(data.getUrl());
            ApiClient.editUserProfileBg(data.getUrl(), bindUntilEvent(ActivityEvent.DESTROY)).subscribe(new Consumer<BaseResult>() {
                public void accept(@NonNull BaseResult baseResult) throws Exception {
                    if (baseResult.getErrno() == 0) {
                        DataManager.init().userInfoChange(true);
                    }
                }
            }, new Consumer<Throwable>() {
                public void accept(@NonNull Throwable th) throws Exception {
                }
            });
        }
    }

    public void update(Observable observable, Object obj) {
        if (obj != null && (obj instanceof Boolean) && ((Boolean) obj).booleanValue()) {
            if (TextUtils.isEmpty(this.myId) && LoginManager.getInstance().hasLogin()) {
                this.myId = LoginManager.getInstance().getUserId();
            }
            if ((TextUtils.isEmpty(this.uid) || TextUtils.isEmpty(this.myId) || this.myId.equals(this.uid)) && !TextUtils.isEmpty(this.myId)) {
                getData(this.myId);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        DataManager.init().deleteObserver(this);
        super.onDestroy();
    }

    /* access modifiers changed from: private */
    public void share(UserCenterShareModel.FacebookBean facebookBean) {
        new ShareDialog(this).setShareTitle(facebookBean.shareSubject).setShareUrl(facebookBean.shareUrl).setShareText(facebookBean.shareMsg).setShareImgUrl(facebookBean.sharePic).setCallbackManager(this.callbackManager).show();
    }
}
