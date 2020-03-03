package com.xiaomi.smarthome.scenenew.actiivity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Location;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableItemViewHolder;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeConfig;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.SceneInfo;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.plugin.mpk.MpkPluginApi;
import com.xiaomi.smarthome.framework.update.ui.MiioUpgradeActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.ExpandableItemIndicator;
import com.xiaomi.smarthome.lite.scene.HomeSceneScrollView;
import com.xiaomi.smarthome.lite.scene.LiteSceneManager;
import com.xiaomi.smarthome.miio.areainfo.AreaInfoManager;
import com.xiaomi.smarthome.miio.areainfo.LocationData;
import com.xiaomi.smarthome.miio.areainfo.ShowProvinceHelper;
import com.xiaomi.smarthome.scene.SmartHomeSceneTimerActivity;
import com.xiaomi.smarthome.scene.SmartHomeSceneUtility;
import com.xiaomi.smarthome.scene.api.RemoteSceneApi;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scenenew.actiivity.SmarthomeRecommendDetailActivity;
import com.xiaomi.smarthome.scenenew.lumiscene.SceneExtraBuilder;
import com.xiaomi.smarthome.scenenew.manager.RecommendSceneManager;
import com.xiaomi.smarthome.scenenew.model.RecommendSceneDetailInfo;
import com.xiaomi.smarthome.scenenew.model.RecommendSceneInfo;
import com.xiaomi.smarthome.scenenew.view.ExpandableListViewWithScrollView;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;

public class SmarthomeRecommendDetailActivity extends BaseActivity {
    public static final int ADAPTER_FROM_ACTION = 1;
    public static final int ADAPTER_FROM_CONDITION = 0;
    public static final int GROUP_TYPE_DETAIL = 1;
    public static final int GROUP_TYPE_EXPANDABLE = 0;
    public static final String TAG = "SmarthomeRecommendDetailActivity";
    private static final int d = 100;
    private static final int e = 101;
    private static final int f = 1;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public XQProgressDialog f21870a;
    private Unbinder b;
    private HomeSceneScrollView.OnScrollListener c = new HomeSceneScrollView.OnScrollListener() {
        public void onScrollY(int i) {
            float measuredHeight = ((float) i) / ((float) SmarthomeRecommendDetailActivity.this.mRecommendDetailRL.getMeasuredHeight());
            if (measuredHeight > 0.8f) {
                SmarthomeRecommendDetailActivity.this.mTitleTV.setTextColor(SmarthomeRecommendDetailActivity.this.getResources().getColor(R.color.title_bar_text_color));
            } else {
                SmarthomeRecommendDetailActivity.this.mTitleTV.setTextColor(SmarthomeRecommendDetailActivity.this.getResources().getColor(R.color.white));
            }
            SmarthomeRecommendDetailActivity.this.mTitleBarItem.setAlpha(measuredHeight);
            float f = 1.0f - measuredHeight;
            if (f <= 0.0f) {
                f = 0.0f;
            }
            LogUtil.a(SmarthomeRecommendDetailActivity.TAG, "alpha" + measuredHeight + "   detailAlpha" + f);
        }
    };
    int errorTime = 0;
    IntentFilter filter;
    ExpandableItemAdapter mActionAdapter;
    @BindView(2131427752)
    ExpandableListViewWithScrollView mActionLV;
    ExpandableItemAdapter mConditionAdapter;
    Context mContext;
    @BindView(2131427754)
    ExpandableListViewWithScrollView mContionLV;
    RecommendSceneDetailInfo mDetailInfo = new RecommendSceneDetailInfo();
    File mFile;
    Handler mHandler = new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 100:
                    SmarthomeRecommendDetailActivity.this.startSaveScene((String) null);
                    return;
                case 101:
                    SmarthomeRecommendDetailActivity.this.a(false);
                    return;
                default:
                    return;
            }
        }
    };
    LayoutInflater mInflater;
    @BindView(2131430799)
    View mMaskView;
    @BindView(2131431766)
    SimpleDraweeView mPlaceHolder;
    @BindView(2131431767)
    RelativeLayout mRecommendDetailRL;
    RecommendSceneInfo.RecommendSceneItem mRecommendSceneItem;
    @BindView(2131430969)
    ImageView mReturnIV;
    SceneApi.SmartHomeScene mScene;
    @BindView(2131429648)
    HomeSceneScrollView mScrollView;
    @BindView(2131431777)
    TextView mStartBtn;
    @BindView(2131432919)
    FrameLayout mTitleBar;
    @BindView(2131432937)
    TextView mTitleBarItem;
    @BindView(2131430975)
    TextView mTitleTV;
    @BindView(2131431768)
    VideoView mVideoView;
    boolean needRegister;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_recommend_detail_layout);
        this.b = ButterKnife.bind((Activity) this);
        this.mContext = this;
        this.mInflater = LayoutInflater.from(this);
        this.mRecommendSceneItem = RecommendSceneManager.a().g();
        if (this.mRecommendSceneItem == null) {
            finish();
        }
        f();
        a();
    }

    private void a() {
        this.mTitleTV.setText(R.string.scene_recommend_title);
        this.mTitleBarItem.setText(this.mDetailInfo.f);
        this.mTitleBar.getBackground().setAlpha(0);
        if (this.mStartBtn != null) {
            if (this.mDetailInfo.a()) {
                this.mStartBtn.setEnabled(true);
            } else {
                this.mStartBtn.setEnabled(false);
            }
        }
        c();
        d();
        b();
    }

    private void b() {
        if (this.mStartBtn != null) {
            this.mStartBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    SmarthomeRecommendDetailActivity.this.mScene = SmarthomeRecommendDetailActivity.this.g();
                    SmarthomeRecommendDetailActivity.this.h();
                }
            });
        }
        this.mScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            public void onScrollChanged() {
                float scrollY = ((float) SmarthomeRecommendDetailActivity.this.mScrollView.getScrollY()) / ((float) SmarthomeRecommendDetailActivity.this.mRecommendDetailRL.getMeasuredHeight());
                if (scrollY > 0.5f) {
                    SmarthomeRecommendDetailActivity.this.mTitleTV.setTextColor(SmarthomeRecommendDetailActivity.this.getResources().getColor(R.color.title_bar_text_color));
                } else {
                    SmarthomeRecommendDetailActivity.this.mTitleTV.setTextColor(SmarthomeRecommendDetailActivity.this.getResources().getColor(R.color.white));
                }
                int intValue = new Float(255.0f * scrollY).intValue();
                if (intValue >= 255) {
                    intValue = 255;
                }
                SmarthomeRecommendDetailActivity.this.mTitleBar.getBackground().setAlpha(intValue);
                SmarthomeRecommendDetailActivity.this.mTitleBarItem.setAlpha(scrollY);
                float f = 1.2f * scrollY;
                SmarthomeRecommendDetailActivity.this.mMaskView.setAlpha(f);
                LogUtil.a(SmarthomeRecommendDetailActivity.TAG, "alpha" + scrollY + "  titleBarAlpha" + intValue + "  maskAlpha" + f);
            }
        });
        this.mReturnIV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SmarthomeRecommendDetailActivity.this.finish();
            }
        });
    }

    private void c() {
        this.mConditionAdapter = new ExpandableItemAdapter(0);
        this.mActionAdapter = new ExpandableItemAdapter(1);
        this.mContionLV.setAdapter(this.mConditionAdapter);
        this.mActionLV.setAdapter(this.mActionAdapter);
        this.mContionLV.setGroupIndicator((Drawable) null);
        this.mActionLV.setGroupIndicator((Drawable) null);
        this.mConditionAdapter.a(this.mDetailInfo.f21985a);
        this.mActionAdapter.b(this.mDetailInfo.b);
    }

    private void d() {
        if (!TextUtils.isEmpty(this.mDetailInfo.c)) {
            this.mVideoView.setVisibility(8);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mVideoView.getLayoutParams();
            layoutParams.width = DisplayUtils.b((Context) this);
            LogUtil.a(TAG, "params.width" + layoutParams.width);
            this.mVideoView.setLayoutParams(layoutParams);
            String str = this.mDetailInfo.c;
            if (!RecommendSceneManager.a().a(str)) {
                GenericDraweeHierarchy build = new GenericDraweeHierarchyBuilder(this.mContext.getResources()).setFadeDuration(200).setPlaceholderImage(this.mContext.getResources().getDrawable(R.drawable.recommend_scene_list_default_icon)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY).setPlaceholderImageScaleType(ScalingUtils.ScaleType.CENTER_CROP).build();
                this.mPlaceHolder.setVisibility(0);
                this.mPlaceHolder.setHierarchy(build);
                this.mPlaceHolder.setImageURI(Uri.parse(this.mDetailInfo.d));
            }
            RecommendSceneManager.a().a(str, (RecommendSceneManager.IGetVideoCallBack) new RecommendSceneManager.IGetVideoCallBack() {
                public void a() {
                }

                public void a(File file) {
                    SmarthomeRecommendDetailActivity.this.mFile = file;
                    SmarthomeRecommendDetailActivity.this.e();
                }
            });
            this.mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mediaPlayer) {
                    SmarthomeRecommendDetailActivity.this.e();
                }
            });
            this.mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                        public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
                            if (i != 3) {
                                return true;
                            }
                            SmarthomeRecommendDetailActivity.this.mVideoView.setBackgroundColor(0);
                            return true;
                        }
                    });
                }
            });
            this.mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                    try {
                        if (SmarthomeRecommendDetailActivity.this.errorTime < 3) {
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    SmarthomeRecommendDetailActivity.this.e();
                                }
                            }, 2000);
                            SmarthomeRecommendDetailActivity.this.errorTime++;
                        } else if (SmarthomeRecommendDetailActivity.this.mFile != null && SmarthomeRecommendDetailActivity.this.mFile.exists()) {
                            SmarthomeRecommendDetailActivity.this.mFile.delete();
                        }
                        return true;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return true;
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.mVideoView != null && this.mVideoView.getVisibility() == 0) {
            this.mVideoView.stopPlayback();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.mVideoView != null) {
            e();
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        if (isValid() && this.mPlaceHolder != null && this.mVideoView != null && this.mFile != null && this.mFile.exists()) {
            if (this.mPlaceHolder.getVisibility() == 0) {
                SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                    public void run() {
                        SmarthomeRecommendDetailActivity.this.mPlaceHolder.setVisibility(8);
                    }
                }, 200);
            }
            if (this.mVideoView.getVisibility() == 8) {
                this.mVideoView.setVisibility(0);
            }
            this.mVideoView.setVideoURI(Uri.parse(this.mFile.getAbsolutePath()));
            this.mVideoView.start();
            LogUtil.a(TAG, "height" + this.mVideoView.getMeasuredHeight() + "*" + this.mVideoView.getMeasuredWidth());
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mVideoView.stopPlayback();
        RecommendSceneManager.a().a((RecommendSceneInfo.RecommendSceneItem) null);
        if (this.b != null) {
            this.b.unbind();
        }
    }

    private void f() {
        if (this.mRecommendSceneItem == null) {
            finish();
        } else {
            this.mDetailInfo.a(this.mRecommendSceneItem);
        }
    }

    class ExpandableItemAdapter extends BaseExpandableListAdapter {

        /* renamed from: a  reason: collision with root package name */
        List<RecommendSceneDetailInfo.ConditionItem> f21895a = new ArrayList();
        List<RecommendSceneDetailInfo.ActionItem> b = new ArrayList();
        private int d = 0;

        public int getGroupTypeCount() {
            return 2;
        }

        public boolean hasStableIds() {
            return false;
        }

        public boolean isChildSelectable(int i, int i2) {
            return false;
        }

        public void a(List<RecommendSceneDetailInfo.ConditionItem> list) {
            this.f21895a.clear();
            this.f21895a.addAll(list);
            notifyDataSetChanged();
        }

        public void b(List<RecommendSceneDetailInfo.ActionItem> list) {
            this.b.clear();
            this.b.addAll(list);
            notifyDataSetChanged();
        }

        public ExpandableItemAdapter(int i) {
            this.d = i;
        }

        public int getGroupCount() {
            if (this.d == 0) {
                return this.f21895a.size();
            }
            return this.b.size();
        }

        public int getChildrenCount(int i) {
            if (this.d == 0) {
                if (this.f21895a.get(i).b.size() <= 1) {
                    return 0;
                }
                return this.f21895a.get(i).b.size();
            } else if (this.b.get(i).b.size() <= 1) {
                return 0;
            } else {
                return this.b.get(i).b.size();
            }
        }

        public Object getGroup(int i) {
            if (this.d == 1) {
                return this.b.get(i);
            }
            if (this.d == 0) {
                return this.f21895a.get(i);
            }
            return null;
        }

        public Object getChild(int i, int i2) {
            if (this.d == 1) {
                return this.b.get(i).b.get(i2);
            }
            if (this.d == 0) {
                return this.f21895a.get(i).b.get(i2);
            }
            return null;
        }

        public long getGroupId(int i) {
            if (this.d == 0) {
                return (long) this.f21895a.get(i).hashCode();
            }
            if (this.d == 1) {
                return (long) this.b.get(i).hashCode();
            }
            return 0;
        }

        public long getChildId(int i, int i2) {
            if (this.d == 0) {
                return (long) this.f21895a.get(i).b.get(i2).hashCode();
            }
            if (this.d == 1) {
                return (long) this.b.get(i).b.get(i2).hashCode();
            }
            return 0;
        }

        public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
            View view2;
            ChildHolder childHolder;
            GroupHolder groupHolder;
            int i2 = i;
            boolean z2 = z;
            int groupType = getGroupType(i);
            if (groupType == 0) {
                View inflate = SmarthomeRecommendDetailActivity.this.mInflater.inflate(R.layout.recommend_scene_group_item, (ViewGroup) null);
                view2 = inflate;
                groupHolder = new GroupHolder(inflate);
                childHolder = null;
            } else if (groupType == 1) {
                View inflate2 = SmarthomeRecommendDetailActivity.this.mInflater.inflate(R.layout.recommend_scene_child_item, (ViewGroup) null);
                childHolder = new ChildHolder(inflate2);
                view2 = inflate2;
                groupHolder = null;
            } else {
                view2 = view;
                groupHolder = null;
                childHolder = null;
            }
            if (this.d == 0) {
                if (groupType == 1) {
                    final RecommendSceneDetailInfo.ConditionItem conditionItem = this.f21895a.get(i2);
                    childHolder.e.setHierarchy(new GenericDraweeHierarchyBuilder(SmarthomeRecommendDetailActivity.this.getResources()).setPlaceholderImage((int) R.drawable.device_list_phone_no).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
                    SmartHomeSceneUtility.a(childHolder.e, conditionItem.f21987a);
                    if (conditionItem.f21987a != null && conditionItem.f21987a.f21522a == SceneApi.Condition.LAUNCH_TYPE.DEVICE) {
                        childHolder.c.setText(SmarthomeRecommendDetailActivity.this.a(conditionItem.f21987a));
                        if (conditionItem.b.size() == 0 || !SmartHomeDeviceManager.a().h(conditionItem.b.get(0).did)) {
                            if (!TextUtils.isEmpty(conditionItem.h)) {
                                childHolder.c.setText(conditionItem.h);
                            }
                            childHolder.d.setText(R.string.lack_device_click_to_view);
                            childHolder.d.setVisibility(0);
                            childHolder.f.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    RecommendSceneManager.a().a((Context) SmarthomeRecommendDetailActivity.this, conditionItem.c);
                                }
                            });
                            childHolder.d.setTextColor(SmarthomeRecommendDetailActivity.this.getResources().getColor(R.color.choose_connect_device_error_link));
                        } else {
                            final Device device = conditionItem.b.get(0);
                            if (!RecommendSceneManager.a().a(conditionItem.i, RecommendSceneManager.a().a(device))) {
                                childHolder.d.setText(R.string.model_version_not_support);
                                childHolder.d.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {
                                        SmarthomeRecommendDetailActivity.this.a(device);
                                    }
                                });
                            } else {
                                Room p = HomeManager.a().p(device.did);
                                String string = p == null ? SmarthomeRecommendDetailActivity.this.getResources().getString(R.string.room_default) : p.e();
                                childHolder.d.setVisibility(0);
                                childHolder.d.setText(string);
                                childHolder.d.setOnClickListener((View.OnClickListener) null);
                                childHolder.d.setTextColor(SmarthomeRecommendDetailActivity.this.getResources().getColor(R.color.std_list_subtitle));
                            }
                        }
                        childHolder.b.setText(SmarthomeRecommendDetailActivity.this.b(conditionItem.f21987a));
                    } else if (conditionItem.f21987a == null || !conditionItem.f21987a.b()) {
                        childHolder.c.setText(SmarthomeRecommendDetailActivity.this.a(conditionItem.f21987a));
                        childHolder.d.setVisibility(8);
                        childHolder.b.setText(SmarthomeRecommendDetailActivity.this.b(conditionItem.f21987a));
                    } else {
                        if (!TextUtils.isEmpty(conditionItem.f21987a.j.f)) {
                            childHolder.c.setText(conditionItem.f21987a.j.f);
                        } else {
                            childHolder.c.setText(R.string.scene_rec_detail_select_city);
                        }
                        childHolder.d.setVisibility(8);
                        childHolder.b.setText(SmarthomeRecommendDetailActivity.this.b(conditionItem.f21987a));
                        childHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                ShowProvinceHelper.a((Activity) SmarthomeRecommendDetailActivity.this, (ShowProvinceHelper.IUpdateLocationCallback) new ShowProvinceHelper.IUpdateLocationCallback() {
                                    public void a(Context context, String str, String str2, String str3, String str4) {
                                        if (conditionItem != null && conditionItem.f21987a != null && conditionItem.f21987a.b()) {
                                            conditionItem.f21987a.j.e = str4;
                                            conditionItem.f21987a.j.f = str3;
                                            SceneApi.ConditionWeather conditionWeather = conditionItem.f21987a.j;
                                            conditionWeather.g = conditionItem.f21987a.j.f + " " + conditionItem.f21987a.j.b;
                                            SmarthomeRecommendDetailActivity.this.mConditionAdapter.notifyDataSetChanged();
                                        }
                                    }

                                    public void a(Context context, Address address, Location location, boolean z, boolean z2) {
                                        if (conditionItem != null && conditionItem.f21987a != null && conditionItem.f21987a.b()) {
                                            LocationData a2 = ShowProvinceHelper.a(context, address);
                                            conditionItem.f21987a.j.e = a2.f;
                                            conditionItem.f21987a.j.f = a2.e;
                                            SceneApi.ConditionWeather conditionWeather = conditionItem.f21987a.j;
                                            conditionWeather.g = conditionItem.f21987a.j.f + " " + conditionItem.f21987a.j.b;
                                            SmarthomeRecommendDetailActivity.this.mConditionAdapter.notifyDataSetChanged();
                                        }
                                    }
                                });
                            }
                        });
                    }
                } else if (groupType == 0) {
                    RecommendSceneDetailInfo.ConditionItem conditionItem2 = this.f21895a.get(i2);
                    groupHolder.K_();
                    groupHolder.c.setText(SmarthomeRecommendDetailActivity.this.b(conditionItem2.f21987a));
                    a(groupHolder, conditionItem2);
                    if (groupHolder.d != null) {
                        groupHolder.d.setExpandedState(z2, false);
                    }
                }
            } else if (this.d == 1) {
                if (groupType == 1) {
                    final RecommendSceneDetailInfo.ActionItem actionItem = this.b.get(i2);
                    childHolder.e.setHierarchy(new GenericDraweeHierarchyBuilder(SmarthomeRecommendDetailActivity.this.getResources()).setPlaceholderImage((int) R.drawable.device_list_phone_no).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
                    SmartHomeSceneUtility.a(childHolder.e, childHolder.b, actionItem.f21986a);
                    childHolder.b.setText(SmarthomeRecommendDetailActivity.this.a(actionItem.f21986a));
                    if (actionItem == null || actionItem.f21986a == null || actionItem.f21986a.g == null || !(actionItem.f21986a.g instanceof SceneApi.SHSceneItemPayloadCommon)) {
                        childHolder.d.setVisibility(8);
                    } else if (actionItem.b == null || actionItem.b.size() <= 0 || !SmartHomeDeviceManager.a().h(actionItem.b.get(0).did)) {
                        childHolder.c.setText(actionItem.g);
                        childHolder.d.setText(R.string.lack_device_click_to_view);
                        childHolder.d.setVisibility(0);
                        childHolder.f.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                RecommendSceneManager.a().a((Context) SmarthomeRecommendDetailActivity.this, actionItem.c);
                            }
                        });
                        childHolder.d.setTextColor(SmarthomeRecommendDetailActivity.this.getResources().getColor(R.color.choose_connect_device_error_link));
                    } else {
                        final Device device2 = actionItem.b.get(0);
                        if (!RecommendSceneManager.a().a(actionItem.h, RecommendSceneManager.a().a(device2))) {
                            childHolder.d.setText(R.string.model_version_not_support);
                            childHolder.d.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    SmarthomeRecommendDetailActivity.this.a(device2);
                                }
                            });
                        } else {
                            childHolder.c.setText(SmarthomeRecommendDetailActivity.this.a(actionItem.f21986a, actionItem.b.get(0).did));
                            Room p2 = HomeManager.a().p(actionItem.b.get(0).did);
                            String string2 = p2 == null ? SmarthomeRecommendDetailActivity.this.getResources().getString(R.string.room_default) : p2.e();
                            childHolder.d.setVisibility(0);
                            childHolder.d.setText(string2);
                            childHolder.d.setOnClickListener((View.OnClickListener) null);
                            childHolder.d.setTextColor(SmarthomeRecommendDetailActivity.this.getResources().getColor(R.color.std_list_subtitle));
                        }
                    }
                } else if (groupType == 0) {
                    RecommendSceneDetailInfo.ActionItem actionItem2 = this.b.get(i2);
                    groupHolder.c.setText(SmarthomeRecommendDetailActivity.this.a(actionItem2.f21986a));
                    a(groupHolder, actionItem2);
                    if (groupHolder.d != null) {
                        groupHolder.d.setExpandedState(z2, false);
                    }
                }
            }
            return view2;
        }

        public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
            List<Device> list;
            View inflate = SmarthomeRecommendDetailActivity.this.mInflater.inflate(R.layout.recommend_scene_child_item, (ViewGroup) null);
            final ChildHolder childHolder = new ChildHolder(inflate);
            if (this.d == 0) {
                list = this.f21895a.get(i).b;
            } else {
                list = this.b.get(i).b;
            }
            childHolder.f21894a.setVisibility(0);
            childHolder.b.setVisibility(8);
            childHolder.c.setText(list.get(i2).getName());
            DeviceFactory.b(list.get(i2).model, childHolder.e);
            if (this.d == 0) {
                final RecommendSceneDetailInfo.ConditionItem conditionItem = this.f21895a.get(i);
                final Device device = conditionItem.b.get(i2);
                final HashMap<String, Boolean> hashMap = conditionItem.d;
                childHolder.f21894a.setChecked(hashMap.get(device.did) == null ? false : hashMap.get(device.did).booleanValue());
                childHolder.f21894a.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        LogUtil.a(SmarthomeRecommendDetailActivity.TAG, "onClick" + childHolder.f21894a.isChecked());
                    }
                });
                childHolder.g.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (!RecommendSceneManager.a().a(conditionItem.i, RecommendSceneManager.a().a(device))) {
                            ToastUtil.a((int) R.string.model_version_not_support);
                        } else {
                            childHolder.f21894a.setChecked(!childHolder.f21894a.isChecked());
                        }
                    }
                });
                childHolder.f21894a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                        ExpandableItemAdapter.this.a(hashMap, device.did, z);
                    }
                });
                if (conditionItem.f21987a.f21522a != SceneApi.Condition.LAUNCH_TYPE.DEVICE) {
                    childHolder.c.setText("");
                    childHolder.d.setVisibility(8);
                } else if (!RecommendSceneManager.a().a(conditionItem.i, RecommendSceneManager.a().a(device))) {
                    childHolder.d.setText(R.string.model_version_not_support);
                    childHolder.f21894a.setEnabled(false);
                    childHolder.d.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            SmarthomeRecommendDetailActivity.this.a(device);
                        }
                    });
                } else {
                    Room p = HomeManager.a().p(device.did);
                    String string = p == null ? SmarthomeRecommendDetailActivity.this.mContext.getString(R.string.room_default) : p.e();
                    childHolder.d.setVisibility(0);
                    childHolder.d.setText(string);
                }
            } else if (this.d == 1) {
                final RecommendSceneDetailInfo.ActionItem actionItem = this.b.get(i);
                final Device device2 = actionItem.b.get(i2);
                final HashMap<String, Boolean> hashMap2 = actionItem.d;
                childHolder.f21894a.setChecked(hashMap2.get(device2.did) == null ? false : hashMap2.get(device2.did).booleanValue());
                childHolder.f21894a.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        LogUtil.a(SmarthomeRecommendDetailActivity.TAG, "onClick" + childHolder.f21894a.isChecked());
                    }
                });
                childHolder.f21894a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                        LogUtil.a(SmarthomeRecommendDetailActivity.TAG, "onCheckedChanged" + z);
                        hashMap2.put(device2.did, Boolean.valueOf(z));
                        SmarthomeRecommendDetailActivity.this.mActionAdapter.notifyDataSetChanged();
                    }
                });
                childHolder.g.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (!RecommendSceneManager.a().a(actionItem.h, RecommendSceneManager.a().a(device2))) {
                            ToastUtil.a((int) R.string.model_version_not_support);
                        } else {
                            childHolder.f21894a.setChecked(!childHolder.f21894a.isChecked());
                        }
                    }
                });
                if (!(actionItem.f21986a.g instanceof SceneApi.SHSceneItemPayloadCommon)) {
                    childHolder.c.setText("");
                    childHolder.d.setVisibility(8);
                } else if (!RecommendSceneManager.a().a(actionItem.h, RecommendSceneManager.a().a(device2))) {
                    childHolder.d.setText(R.string.model_version_not_support);
                    childHolder.f21894a.setEnabled(false);
                    childHolder.d.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            SmarthomeRecommendDetailActivity.this.a(device2);
                        }
                    });
                } else {
                    Room p2 = HomeManager.a().p(device2.did);
                    String string2 = p2 == null ? SmarthomeRecommendDetailActivity.this.getString(R.string.room_default) : p2.e();
                    childHolder.d.setVisibility(0);
                    childHolder.d.setText(string2);
                }
            }
            return inflate;
        }

        public int getGroupType(int i) {
            return this.d == 0 ? this.f21895a.get(i).b.size() <= 1 ? 1 : 0 : (this.d != 1 || this.b.get(i).b.size() <= 1) ? 1 : 0;
        }

        public void onGroupExpanded(int i) {
            LogUtil.a(SmarthomeRecommendDetailActivity.TAG, "onGroupExpanded" + i);
            super.onGroupExpanded(i);
        }

        public void onGroupCollapsed(int i) {
            LogUtil.a(SmarthomeRecommendDetailActivity.TAG, "onGroupCollapsed" + i);
            super.onGroupCollapsed(i);
        }

        /* access modifiers changed from: private */
        public void a(HashMap<String, Boolean> hashMap, String str, boolean z) {
            LogUtil.a(SmarthomeRecommendDetailActivity.TAG, "onConditionCheckBoxSingleChange");
            if (hashMap != null) {
                for (String str2 : hashMap.keySet()) {
                    hashMap.put(str2.toString(), false);
                }
                hashMap.put(str, true);
                notifyDataSetChanged();
            }
        }

        private void a(GroupHolder groupHolder, RecommendSceneDetailInfo.ActionItem actionItem) {
            int i;
            if (actionItem == null || actionItem.d == null) {
                i = 0;
            } else {
                i = 0;
                for (Boolean booleanValue : actionItem.d.values()) {
                    if (booleanValue.booleanValue()) {
                        i++;
                    }
                }
            }
            LogUtil.a(SmarthomeRecommendDetailActivity.TAG, "selectCount" + i);
            if (actionItem == null || actionItem.d == null || i != 1) {
                if (!TextUtils.isEmpty(actionItem.g)) {
                    groupHolder.f21910a.setText(actionItem.g);
                } else {
                    groupHolder.f21910a.setText(SmarthomeRecommendDetailActivity.this.a(actionItem.f21986a, (String) null));
                }
                groupHolder.b.setText(SmarthomeRecommendDetailActivity.this.getResources().getString(R.string.one, new Object[]{Integer.valueOf(i)}));
                return;
            }
            for (int i2 = 0; i2 < actionItem.b.size(); i2++) {
                Device device = actionItem.b.get(i2);
                if (actionItem.d.get(device.did) != null && actionItem.d.get(device.did).booleanValue()) {
                    if (!TextUtils.isEmpty(device.name)) {
                        groupHolder.f21910a.setText(device.name);
                    } else {
                        groupHolder.f21910a.setText(actionItem.g);
                    }
                    Room p = HomeManager.a().p(device.did);
                    groupHolder.b.setText(p == null ? SmarthomeRecommendDetailActivity.this.getResources().getString(R.string.room_default) : p.e());
                }
            }
        }

        private void a(GroupHolder groupHolder, RecommendSceneDetailInfo.ConditionItem conditionItem) {
            int i;
            if (conditionItem == null || conditionItem.d == null) {
                i = 0;
            } else {
                i = 0;
                for (Boolean booleanValue : conditionItem.d.values()) {
                    if (booleanValue.booleanValue()) {
                        i++;
                    }
                }
            }
            LogUtil.a(SmarthomeRecommendDetailActivity.TAG, "selectCount" + i);
            if (conditionItem == null || conditionItem.d == null || i != 1) {
                if (!TextUtils.isEmpty(conditionItem.h)) {
                    groupHolder.f21910a.setText(conditionItem.h);
                } else {
                    groupHolder.f21910a.setText(SmarthomeRecommendDetailActivity.this.a(conditionItem.f21987a));
                }
                groupHolder.b.setText(SmarthomeRecommendDetailActivity.this.getResources().getString(R.string.one, new Object[]{Integer.valueOf(i)}));
                return;
            }
            for (int i2 = 0; i2 < conditionItem.b.size(); i2++) {
                Device device = conditionItem.b.get(i2);
                if (conditionItem.d.get(device.did) != null && conditionItem.d.get(device.did).booleanValue()) {
                    if (!TextUtils.isEmpty(device.name)) {
                        groupHolder.f21910a.setText(device.name);
                    } else {
                        groupHolder.f21910a.setText(conditionItem.h);
                    }
                    Room p = HomeManager.a().p(device.did);
                    groupHolder.b.setText(p == null ? SmarthomeRecommendDetailActivity.this.getResources().getString(R.string.room_default) : p.e());
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(final Device device) {
        new MLAlertDialog.Builder(this).a((int) R.string.model_version_no_support_dialog_title).a((int) R.string.confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(SmarthomeRecommendDetailActivity.this.mContext, MiioUpgradeActivity.class);
                intent.putExtra(MiioUpgradeActivity.MIIO_UPGRADE_DID, device.did);
                intent.putExtra(MiioUpgradeActivity.MIIO_UPGRADE_PID, device.pid);
                intent.putExtra(MiioUpgradeActivity.MIIO_UPGRADE_NAME, device.name);
                SmarthomeRecommendDetailActivity.this.startActivityForResult(intent, 1);
            }
        }).b().show();
    }

    class GroupHolder extends AbstractDraggableItemViewHolder implements ExpandableItemViewHolder {

        /* renamed from: a  reason: collision with root package name */
        TextView f21910a;
        TextView b;
        TextView c;
        ExpandableItemIndicator d;
        private int f;

        public GroupHolder(View view) {
            super(view);
            this.f21910a = (TextView) view.findViewById(R.id.device_name);
            this.b = (TextView) view.findViewById(R.id.device_count_tv);
            this.c = (TextView) view.findViewById(R.id.name);
            this.d = (ExpandableItemIndicator) view.findViewById(R.id.expandable_item_indicator);
        }

        public void c_(int i) {
            this.f = i;
        }

        public int K_() {
            return this.f;
        }
    }

    class ChildHolder extends AbstractDraggableItemViewHolder implements ExpandableItemViewHolder {

        /* renamed from: a  reason: collision with root package name */
        CheckBox f21894a;
        TextView b;
        TextView c;
        TextView d;
        SimpleDraweeView e;
        LinearLayout f;
        View g;
        private int i;

        public ChildHolder(View view) {
            super(view);
            this.g = view;
            this.f21894a = (CheckBox) view.findViewById(R.id.recommend_scene_checkbox);
            this.b = (TextView) view.findViewById(R.id.name);
            this.c = (TextView) view.findViewById(R.id.device_name);
            this.d = (TextView) view.findViewById(R.id.device_room_name);
            this.e = (SimpleDraweeView) view.findViewById(R.id.image);
            this.f = (LinearLayout) view.findViewById(R.id.device_name_ll);
        }

        public void c_(int i2) {
            this.i = i2;
        }

        public int K_() {
            return this.i;
        }
    }

    /* access modifiers changed from: private */
    public String a(SceneApi.Condition condition) {
        return SmartHomeSceneUtility.b(SHApplication.getAppContext(), condition);
    }

    /* access modifiers changed from: private */
    public String b(SceneApi.Condition condition) {
        if (condition == null) {
            return "";
        }
        if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.DEVICE) {
            return condition.c.b;
        }
        if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.CLICK) {
            return getResources().getString(R.string.click_condition_name);
        }
        if (condition.b()) {
            return condition.j.b;
        }
        if (condition.f21522a != SceneApi.Condition.LAUNCH_TYPE.TIMER) {
            return "";
        }
        return SmartHomeSceneTimerActivity.getTimerHint(this, condition.b != null ? condition.b.f21527a : null);
    }

    /* access modifiers changed from: private */
    public String a(SceneApi.Action action, String str) {
        if (!(action.g instanceof SceneApi.SHSceneItemPayloadCommon)) {
            return action.b;
        }
        if (!TextUtils.isEmpty(str)) {
            Device b2 = SmartHomeDeviceManager.a().b(str);
            if (b2 == null) {
                b2 = SmartHomeDeviceManager.a().l(str);
            }
            if (b2 != null) {
                return b2.name;
            }
            return "";
        } else if ("".isEmpty()) {
            return DeviceFactory.k(action.e).name;
        } else {
            return "";
        }
    }

    /* access modifiers changed from: private */
    public String a(SceneApi.Action action) {
        return action == null ? "" : action.b;
    }

    /* access modifiers changed from: private */
    public SceneApi.SmartHomeScene g() {
        SceneApi.Action a2;
        SceneApi.Condition b2;
        SceneApi.SmartHomeScene smartHomeScene = new SceneApi.SmartHomeScene();
        smartHomeScene.u = new SceneApi.EffectiveTimeSpan();
        smartHomeScene.g = this.mDetailInfo.f;
        smartHomeScene.n = this.mDetailInfo.g == 1;
        smartHomeScene.o = this.mDetailInfo.h == 1;
        smartHomeScene.i = Integer.valueOf(this.mDetailInfo.e).intValue();
        if (this.mDetailInfo.i == 30) {
            smartHomeScene.t = true;
        } else {
            smartHomeScene.t = false;
        }
        if (this.mDetailInfo.f21985a != null) {
            for (int i = 0; i < this.mDetailInfo.f21985a.size(); i++) {
                RecommendSceneDetailInfo.ConditionItem conditionItem = this.mDetailInfo.f21985a.get(i);
                if (conditionItem.f.equalsIgnoreCase("device")) {
                    if (conditionItem.b.size() != 0) {
                        for (int i2 = 0; i2 < conditionItem.b.size(); i2++) {
                            Device device = conditionItem.b.get(i2);
                            if (!(conditionItem.d.get(device.did) == null || !conditionItem.d.get(device.did).booleanValue() || (b2 = RecommendSceneDetailInfo.b(device.model, conditionItem.e.optString(device.model))) == null || b2.c == null || smartHomeScene.l == null)) {
                                b2.c.f21523a = device.did;
                                smartHomeScene.l.add(b2);
                            }
                        }
                    }
                } else if (!(conditionItem.f21987a == null || smartHomeScene.l == null)) {
                    smartHomeScene.l.add(conditionItem.f21987a);
                }
            }
        }
        if (this.mDetailInfo.b != null) {
            for (int i3 = 0; i3 < this.mDetailInfo.b.size(); i3++) {
                RecommendSceneDetailInfo.ActionItem actionItem = this.mDetailInfo.b.get(i3);
                if (actionItem.f == SceneApi.Action.ACTION_TYPE.TYPE_DEVICE.value) {
                    if (actionItem.b.size() != 0) {
                        for (int i4 = 0; i4 < actionItem.b.size(); i4++) {
                            Device device2 = actionItem.b.get(i4);
                            if (!(actionItem.d.get(device2.did) == null || !actionItem.d.get(device2.did).booleanValue() || (a2 = RecommendSceneDetailInfo.a(device2.model, actionItem.e.optString(device2.model))) == null || a2.g == null)) {
                                a2.g.e = device2.did;
                                SceneApi.SHSceneItemPayload sHSceneItemPayload = a2.g;
                                sHSceneItemPayload.c = device2.model + "." + a2.g.c;
                                a2.b = device2.name;
                                smartHomeScene.k.add(a2);
                            }
                        }
                    }
                } else if (actionItem.f21986a != null) {
                    smartHomeScene.k.add(actionItem.f21986a);
                }
            }
        }
        return smartHomeScene;
    }

    class MyReceiver extends BroadcastReceiver {

        /* renamed from: a  reason: collision with root package name */
        TextView f21911a;
        RecommendSceneDetailInfo.ConditionItem b;

        MyReceiver() {
        }

        public void a(RecommendSceneDetailInfo.ConditionItem conditionItem) {
            this.b = conditionItem;
        }

        public void onReceive(Context context, Intent intent) {
            if (!AreaInfoManager.f11897a.equals(intent.getAction())) {
                AreaInfoManager.c.equalsIgnoreCase(intent.getAction());
            } else if (this.b != null && this.b.f21987a != null && this.b.f21987a.b()) {
                this.b.f21987a.j.e = AreaInfoManager.a().f();
                this.b.f21987a.j.f = AreaInfoManager.a().n();
                SceneApi.ConditionWeather conditionWeather = this.b.f21987a.j;
                conditionWeather.g = AreaInfoManager.a().n() + this.b.f21987a.j.b;
                SmarthomeRecommendDetailActivity.this.mConditionAdapter.notifyDataSetChanged();
            }
        }
    }

    /* access modifiers changed from: private */
    public void h() {
        Device n;
        String str;
        Device n2;
        String str2;
        if (!NetworkUtils.c()) {
            ToastUtil.a((int) R.string.network_fake_connected);
        } else if (this.mScene.l.size() == 0 || this.mScene.k.size() == 0) {
            ToastUtil.a((CharSequence) getString(R.string.condition_action_count_err));
        } else {
            this.f21870a = XQProgressDialog.a(this.mContext, (CharSequence) null, getString(R.string.smarthome_scene_saving_scene));
            String str3 = null;
            boolean z = false;
            for (SceneApi.Condition next : this.mScene.l) {
                if (!(next.c == null || next.c.d == null || (n2 = SmartHomeDeviceManager.a().n(next.c.f21523a)) == null)) {
                    if (n2.isSubDevice()) {
                        str2 = n2.parentId;
                    } else {
                        str2 = n2.did;
                    }
                    if (SmartHomeSceneUtility.a(SmartHomeDeviceManager.a().n(str2))) {
                        str3 = str2;
                        z = true;
                    }
                }
            }
            if (!z) {
                for (SceneApi.Action next2 : this.mScene.k) {
                    if (!(next2.e == null || next2.g.e == null || (n = SmartHomeDeviceManager.a().n(next2.g.e)) == null)) {
                        if (n.isSubDevice()) {
                            str = n.parentId;
                        } else {
                            str = n.did;
                        }
                        if (SmartHomeSceneUtility.a(SmartHomeDeviceManager.a().n(str))) {
                            str3 = str;
                            z = true;
                        }
                    }
                }
            }
            if (!z || str3 == null) {
                startSaveScene((String) null);
                return;
            }
            Device n3 = SmartHomeDeviceManager.a().n(str3);
            if (n3 == null || n3.isOnline) {
                Intent intent = new Intent();
                SceneApi.a(this.mScene);
                SceneInfo f2 = SceneManager.f(this.mScene);
                intent.putExtra("scene_info", f2);
                this.mHandler.sendEmptyMessageDelayed(100, 1000);
                Log.i("lumiscene", "is rn plugin, scene in app exec..");
                a(f2, n3);
                return;
            }
            this.f21870a.dismiss();
            Toast.makeText(this.mContext, R.string.smarthome_scene_getway_offline, 0).show();
        }
    }

    private void a(SceneInfo sceneInfo, final Device device) {
        SceneExtraBuilder.a().a(sceneInfo, (Callback<SceneInfo>) new Callback<SceneInfo>() {
            /* renamed from: a */
            public void onSuccess(SceneInfo sceneInfo) {
                if (SmarthomeRecommendDetailActivity.this.mHandler.hasMessages(100)) {
                    SmarthomeRecommendDetailActivity.this.mHandler.removeMessages(100);
                    if (sceneInfo != null) {
                        for (int i = 0; i < sceneInfo.mLaunchList.size(); i++) {
                            if (SmarthomeRecommendDetailActivity.this.mScene.l.get(i).c != null && (SmarthomeRecommendDetailActivity.this.mScene.l.get(i).c instanceof SceneApi.ConditionDeviceCommon)) {
                                ((SceneApi.ConditionDeviceCommon) SmarthomeRecommendDetailActivity.this.mScene.l.get(i).c).m = sceneInfo.mLaunchList.get(i).mExtra;
                            }
                        }
                        for (int i2 = 0; i2 < sceneInfo.mActions.size(); i2++) {
                            if (SmarthomeRecommendDetailActivity.this.mScene.k.get(i2).g != null && (SmarthomeRecommendDetailActivity.this.mScene.k.get(i2).g instanceof SceneApi.SHSceneItemPayloadCommon)) {
                                ((SceneApi.SHSceneItemPayloadCommon) SmarthomeRecommendDetailActivity.this.mScene.k.get(i2).g).f21531a = sceneInfo.mActions.get(i2).mExtra;
                            }
                        }
                    }
                    SmarthomeRecommendDetailActivity.this.startSaveScene(device.did);
                }
            }

            public void onFailure(int i, String str) {
                LogUtil.b("lumiscene", str);
                if (SmarthomeRecommendDetailActivity.this.mHandler.hasMessages(100)) {
                    SmarthomeRecommendDetailActivity.this.mHandler.removeMessages(100);
                }
                SmarthomeRecommendDetailActivity.this.mHandler.post(new Runnable() {
                    public final void run() {
                        SmarthomeRecommendDetailActivity.AnonymousClass12.this.a();
                    }
                });
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void a() {
                if (SmarthomeRecommendDetailActivity.this.isValid()) {
                    SmarthomeRecommendDetailActivity.this.f21870a.dismiss();
                }
                Toast.makeText(SmarthomeRecommendDetailActivity.this.mContext, R.string.smarthome_scene_set_fail, 0).show();
            }
        });
    }

    public void startSaveScene(final String str) {
        if (SmartHomeConfig.c) {
            RemoteSceneApi.a().a((Context) this, this.mScene, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    SmarthomeRecommendDetailActivity.this.a(str, jSONObject);
                }

                public void onFailure(Error error) {
                    SmarthomeRecommendDetailActivity.this.f21870a.dismiss();
                    if (error.a() == -23) {
                        Toast.makeText(SmarthomeRecommendDetailActivity.this.mContext, R.string.dead_loop_error, 0).show();
                    } else if (error.a() == -1) {
                        Toast.makeText(SmarthomeRecommendDetailActivity.this.getContext(), R.string.smarthome_scene_has_deleted_device, 0).show();
                    } else {
                        Toast.makeText(SmarthomeRecommendDetailActivity.this.mContext, R.string.smarthome_scene_set_fail, 0).show();
                    }
                }
            });
        } else {
            this.f21870a.dismiss();
        }
    }

    /* access modifiers changed from: private */
    public void a(final boolean z) {
        this.mHandler.post(new Runnable() {
            public void run() {
                if (z) {
                    if (SmarthomeRecommendDetailActivity.this.mScene.t) {
                        SmarthomeRecommendDetailActivity.this.f21870a.dismiss();
                        LiteSceneManager.j().b(SmarthomeRecommendDetailActivity.this.mScene, new AsyncCallback() {
                            public void onSuccess(Object obj) {
                                SmarthomeRecommendDetailActivity.this.f21870a.dismiss();
                                SHApplication.getGlobalWorkerHandler().post(new Runnable() {
                                    public void run() {
                                        LiteSceneManager.j().b();
                                    }
                                });
                                Toast.makeText(SmarthomeRecommendDetailActivity.this.mContext, R.string.recommend_scene_add_success, 0).show();
                            }

                            public void onFailure(Error error) {
                                SmarthomeRecommendDetailActivity.this.f21870a.dismiss();
                            }
                        });
                    } else {
                        SceneManager.x().d(SmarthomeRecommendDetailActivity.this.mScene);
                        CoreApi.a().U();
                        SceneManager.x().c((String) null);
                        SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                            public void run() {
                                SmarthomeRecommendDetailActivity.this.f21870a.dismiss();
                                Toast.makeText(SmarthomeRecommendDetailActivity.this.mContext, R.string.recommend_scene_add_success, 0).show();
                            }
                        }, 500);
                    }
                    if (SmarthomeRecommendDetailActivity.this.mStartBtn != null) {
                        SmarthomeRecommendDetailActivity.this.mStartBtn.setText(R.string.go_look_at);
                        SmarthomeRecommendDetailActivity.this.mStartBtn.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                SmarthomeRecommendDetailActivity.this.finish();
                                SmarthomeRecommendDetailActivity.this.overridePendingTransition(0, 0);
                                RecommendSceneManager.a().b(SmarthomeRecommendDetailActivity.this.mScene.f);
                                RecommendSceneManager.a().a(SmarthomeRecommendDetailActivity.this.mScene.t);
                            }
                        });
                        return;
                    }
                    return;
                }
                SmarthomeRecommendDetailActivity.this.f21870a.dismiss();
                Toast.makeText(SmarthomeRecommendDetailActivity.this.mContext, R.string.local_sync_failed, 0).show();
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(String str, JSONObject jSONObject) {
        String optString = jSONObject.optString("us_id");
        boolean optBoolean = jSONObject.optBoolean("local");
        String optString2 = jSONObject.optString("local_dev");
        this.mScene.f = optString;
        if (TextUtils.isEmpty(optString2) || !optBoolean) {
            a(true);
            return;
        }
        Device n = SmartHomeDeviceManager.a().n(optString2);
        if (n == null) {
            a(false);
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("extra", jSONObject.optJSONObject("data").toString());
        MpkPluginApi.callPlugin(n.did, 14, intent, n.newDeviceStat(), new PluginApi.SendMessageCallback() {
            public void onMessageSuccess(Intent intent) {
                RemoteSceneApi.a().b(SmarthomeRecommendDetailActivity.this.mContext, SmarthomeRecommendDetailActivity.this.mScene, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        SmarthomeRecommendDetailActivity.this.a(true);
                    }

                    public void onFailure(Error error) {
                        SmarthomeRecommendDetailActivity.this.a(false);
                    }
                });
            }

            public void onMessageFailure(int i, String str) {
                SmarthomeRecommendDetailActivity.this.a(false);
            }

            public void onMessageHandle(boolean z) {
                if (!z) {
                    SmarthomeRecommendDetailActivity.this.a(false);
                }
            }
        });
        this.mHandler.sendEmptyMessageDelayed(101, 10000);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1) {
            f();
            a();
        }
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(new ContextWrapper(context) {
            public Object getSystemService(String str) {
                if ("audio".equals(str)) {
                    return getApplicationContext().getSystemService(str);
                }
                return super.getSystemService(str);
            }
        });
    }
}
