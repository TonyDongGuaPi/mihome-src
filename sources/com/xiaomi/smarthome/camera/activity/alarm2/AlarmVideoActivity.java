package com.xiaomi.smarthome.camera.activity.alarm2;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.mijia.app.Event;
import com.mijia.debug.SDKLog;
import com.mijia.model.alarmcloud.AlarmCloudCallback;
import com.mijia.model.alarmcloud.AlarmCloudManager;
import com.mijia.model.alarmcloud.AlarmVideo;
import com.miui.tsmclient.util.StringUtils;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.activity.alarm.AlarmSettingV2Activity;
import com.xiaomi.smarthome.camera.activity.alarm2.adapter.AlarmVideoAdapter;
import com.xiaomi.smarthome.camera.activity.alarm2.adapter.RecyclerEventTypeAdapter;
import com.xiaomi.smarthome.camera.activity.alarm2.bean.EventType;
import com.xiaomi.smarthome.camera.activity.alarm2.util.AnimUtil;
import com.xiaomi.smarthome.camera.v4.activity.alarm.AlarmDayV2Activity;
import com.xiaomi.smarthome.camera.view.calendar.CEn7DayRecyclerAdapterNew;
import com.xiaomi.smarthome.camera.view.calendar.CalendarDate;
import com.xiaomi.smarthome.camera.view.calendar.DateUtils;
import com.xiaomi.smarthome.camera.view.calendar.YdCatCalendarView;
import com.xiaomi.smarthome.camera.view.recycle.RecyclerViewRefreshLayout;
import com.xiaomi.smarthome.camera.view.recycle.RecyclerViewRefreshLayoutEx;
import com.xiaomi.smarthome.device.api.BaseDevice;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoUserStatus;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.ICloudVideoCallback;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.shop.utils.DisplayUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AlarmVideoActivity extends CameraBaseActivity implements View.OnClickListener, BaseDevice.StateChangedListener {
    private static final String IS_FIRST_OPEN = "isFirstOpen";
    private static final String PREF_NAME = "prefName";
    private static final int REQUEST_CODE_HISTORY = 1002;
    public static final int REQUEST_CODE_SHOW_ALL_VIDEO = 17;
    private static final int REQUEST_CODE_VIDEO_PLAYER = 1001;
    public static final String TAG = "AlarmVideoActivity";
    private static final String[] mDevicePropKeys = {"electricity"};
    private final int UPDATE_CHECK = Constants.TradeCode.QUERY_BUSINESS_ORDER_STATUS;
    /* access modifiers changed from: private */
    public View calendar_container;
    YdCatCalendarView custom_calendar;
    RecyclerEventTypeAdapter eventTypeAdapter;
    /* access modifiers changed from: private */
    public String[] eventTypes = {"Default", AlarmCloudManager.OBJECT_MOTION, AlarmCloudManager.PEOPLE_MOTION, AlarmCloudManager.BABY_CRY, AlarmCloudManager.FACE, AlarmCloudManager.AI};
    /* access modifiers changed from: private */
    public String[] eventTypesDesc = {SHApplication.getAppContext().getResources().getString(R.string.event_type_all), SHApplication.getAppContext().getResources().getString(R.string.event_type_obj_motion), SHApplication.getAppContext().getResources().getString(R.string.event_type_people_motion), SHApplication.getAppContext().getResources().getString(R.string.event_type_baby_cry), SHApplication.getAppContext().getResources().getString(R.string.event_type_face), SHApplication.getAppContext().getResources().getString(R.string.event_type_ai)};
    RecyclerView event_type_recycler;
    /* access modifiers changed from: private */
    public boolean isNetRefreshing = false;
    ImageView iv_more_record;
    ImageView iv_next_day;
    private boolean licenseDialogShow = false;
    LinearLayout ll_calendar_bg;
    LinearLayout ll_empty_content;
    LinearLayout ll_title_bar;
    /* access modifiers changed from: private */
    public AlarmVideoAdapter mAlarmVideoAdapter = null;
    /* access modifiers changed from: private */
    public EventType mCurEventType = new EventType();
    /* access modifiers changed from: private */
    public long mEndPoint = 0;
    /* access modifiers changed from: private */
    public long mEndTime = 0;
    /* access modifiers changed from: private */
    public boolean mHasAISceneBuilt;
    LayoutInflater mLayoutInflater;
    private SceneManager.IScenceListener mListener = new SceneManager.IScenceListener() {
        public void onRefreshScenceSuccess(int i) {
            List<SceneApi.SmartHomeScene> list;
            if (!AlarmVideoActivity.this.isFinishing() && AlarmVideoActivity.this.isValid()) {
                if (!SceneManager.x().n()) {
                    list = SceneManager.x().j();
                } else {
                    list = SceneManager.x().d(AlarmVideoActivity.this.mCameraDevice.getDid());
                }
                if (list == null || list.size() <= 0) {
                    boolean unused = AlarmVideoActivity.this.mHasAISceneBuilt = false;
                    return;
                }
                boolean unused2 = AlarmVideoActivity.this.mHasAISceneBuilt = true;
                if (AlarmVideoActivity.this.eventTypeAdapter != null && AlarmVideoActivity.this.eventTypeAdapter.getItemCount() > 0 && !AlarmVideoActivity.this.eventTypeAdapter.containsAiType()) {
                    EventType eventType = new EventType();
                    eventType.name = AlarmVideoActivity.this.eventTypes[AlarmVideoActivity.this.eventTypes.length - 1];
                    eventType.desc = AlarmVideoActivity.this.eventTypesDesc[AlarmVideoActivity.this.eventTypesDesc.length - 1];
                    AlarmVideoActivity.this.eventTypeAdapter.addEvent(eventType);
                }
            }
        }

        public void onRefreshScenceFailed(int i) {
            boolean unused = AlarmVideoActivity.this.mHasAISceneBuilt = false;
        }
    };
    private ImageView mLoadingView;
    private boolean mNeedLicense = false;
    /* access modifiers changed from: private */
    public List<AlarmVideo> mRecordList = new ArrayList();
    private SimpleDateFormat mSdf = new SimpleDateFormat(StringUtils.EXPECT_TIME_FORMAT, Locale.CHINA);
    /* access modifiers changed from: private */
    public CalendarDate mSelectedDate;
    /* access modifiers changed from: private */
    public long mStartPoint = 0;
    /* access modifiers changed from: private */
    public long mStartTime = 0;
    /* access modifiers changed from: private */
    public CloudVideoUserStatus mUserStatus;
    /* access modifiers changed from: private */
    public int mVisibleItemCount;
    RecyclerViewRefreshLayoutEx ptrLayout;
    RecyclerView rv_event_list;
    TextView sub_title_bar_title;
    private int targetHeight = -1;
    ImageView title_bar_more;
    ImageView title_bar_redpoint;
    TextView title_bar_title;
    TextView tv_all_record;
    TextView tv_cur_day;
    TextView tv_request_result;

    interface AgreeListener {
        void onOk();

        void onPass();
    }

    private void checkIsAgreeLicense(AgreeListener agreeListener) {
    }

    public void onStateChanged(BaseDevice baseDevice) {
    }

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        if (getApplicationContext() != null) {
            this.mLayoutInflater = LayoutInflater.from(getApplicationContext());
        }
        setContentView(R.layout.activity_alarm_video2);
        findView();
        initEventType();
        initView();
        initData();
        this.mHandler.post(new Runnable() {
            public void run() {
                AlarmVideoActivity.this.calculateHeight();
            }
        });
        refreshContent();
        Event.c();
    }

    private void initEventType() {
        String r = this.mCameraDevice.e().r();
        for (int i = 0; i < this.eventTypes.length; i++) {
            if (r.equals(this.eventTypes[i])) {
                this.mCurEventType.name = this.eventTypes[i];
                this.mCurEventType.desc = this.eventTypesDesc[i];
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public void calculateHeight() {
        this.mVisibleItemCount = (int) Math.ceil((double) (((float) this.rv_event_list.getMeasuredHeight()) / (((float) DisplayUtils.a((Context) this, 88.0f)) * 1.0f)));
    }

    private void findView() {
        this.calendar_container = findViewById(R.id.calendar_area);
        findViewById(R.id.see_all_video).setOnClickListener(this);
        this.title_bar_more = (ImageView) findViewById(R.id.title_bar_more);
        this.title_bar_more.setImageResource(R.drawable.house_keeping_setting);
        this.title_bar_more.setVisibility(this.mCameraDevice.isReadOnlyShared() ? 8 : 0);
        this.title_bar_more.setOnClickListener(this);
        this.custom_calendar = (YdCatCalendarView) findViewById(R.id.custom_calendar);
        this.ll_calendar_bg = (LinearLayout) findViewById(R.id.ll_calendar_bg);
        this.ll_calendar_bg.setOnClickListener(this);
        this.title_bar_title = (TextView) findViewById(R.id.title_bar_title);
        this.sub_title_bar_title = (TextView) findViewById(R.id.sub_title_bar_title);
        this.tv_cur_day = (TextView) findViewById(R.id.tv_cur_day);
        this.tv_cur_day.setOnClickListener(this);
        this.iv_next_day = (ImageView) findViewById(R.id.iv_next_day);
        this.iv_more_record = (ImageView) findViewById(R.id.iv_more_record);
        this.ptrLayout = (RecyclerViewRefreshLayoutEx) findViewById(R.id.ptr);
        this.tv_all_record = (TextView) findViewById(R.id.tv_all_record);
        this.rv_event_list = (RecyclerView) findViewById(R.id.rv_event_list);
        this.event_type_recycler = (RecyclerView) findViewById(R.id.event_type_recycler);
        this.ll_empty_content = (LinearLayout) findViewById(R.id.ll_empty_content);
        this.tv_request_result = (TextView) findViewById(R.id.tv_request_result);
        this.title_bar_redpoint = (ImageView) findViewById(R.id.title_bar_redpoint);
        findViewById(R.id.title_bar_return).setOnClickListener(this);
        findViewById(R.id.ll_all_record).setOnClickListener(this);
        this.iv_next_day.setOnClickListener(this);
        this.mLoadingView = (ImageView) findViewById(R.id.ivLoading);
        this.mLoadingView.setImageDrawable(getResources().getDrawable(R.drawable.camera_loading));
    }

    private void initView() {
        this.tv_cur_day.setText(DateUtils.getMonthDay(System.currentTimeMillis() + 1));
        this.mAlarmVideoAdapter = new AlarmVideoAdapter(this);
        this.mAlarmVideoAdapter.setOnItemClickedListener(new AlarmVideoAdapter.ItemClickedListener() {
            public void onItemClicked(final AlarmVideo alarmVideo, int i) {
                AlarmVideoActivity.this.mCameraDevice.a().markEvent(alarmVideo.fileId, alarmVideo.offset, new Callback<Boolean>() {
                    public void onSuccess(Boolean bool) {
                        if (!AlarmVideoActivity.this.isFinishing() && bool.booleanValue()) {
                            alarmVideo.isRead = true;
                            if (AlarmVideoActivity.this.mRecordList != null && AlarmVideoActivity.this.mRecordList.indexOf(alarmVideo) != -1) {
                                AlarmVideoActivity.this.mAlarmVideoAdapter.notifyItemChanged(AlarmVideoActivity.this.mRecordList.indexOf(alarmVideo));
                            }
                        }
                    }

                    public void onFailure(int i, String str) {
                        if (!AlarmVideoActivity.this.isFinishing()) {
                        }
                    }
                });
                if (AlarmVideoActivity.this.mCameraDevice.e().f()) {
                    Intent intent = new Intent(AlarmVideoActivity.this.getContext(), AlarmVideoPlayerActivity.class);
                    intent.putExtra("fileId", alarmVideo.fileId);
                    intent.putExtra("isAlarm", alarmVideo.isAlarm);
                    intent.putExtra("createTime", alarmVideo.createTime);
                    intent.putExtra("offset", alarmVideo.offset);
                    intent.putExtra("startDuration", alarmVideo.startDuration);
                    intent.putExtra("pincod", true);
                    AlarmVideoActivity.this.startActivityForResult(intent, 1001);
                    return;
                }
                Intent intent2 = new Intent(AlarmVideoActivity.this.getContext(), AlarmVideoNormalPlayerActivity.class);
                intent2.putExtra("fileId", alarmVideo.fileId);
                intent2.putExtra("isAlarm", alarmVideo.isAlarm);
                intent2.putExtra("createTime", alarmVideo.createTime);
                intent2.putExtra("offset", alarmVideo.offset);
                intent2.putExtra("dateTime", AlarmVideoActivity.this.mSelectedDate);
                intent2.putExtra("index", i);
                intent2.putExtra("pincod", true);
                AlarmVideoActivity.this.startActivityForResult(intent2, 17);
            }
        });
        ((TextView) findViewById(R.id.title_bar_title)).setText(getString(R.string.housekeeping));
        int width = getWindowManager().getDefaultDisplay().getWidth();
        if (this.targetHeight == -1) {
            this.targetHeight = (width * 720) / 1280;
        }
        initRecycler();
        initPtr();
        this.custom_calendar.setCalendarListener(new YdCatCalendarView.YdCatCalendarListener() {
            public void clickOnLeftPage() {
            }

            public void clickOnRightPage() {
            }

            public void clickOnDate(final CalendarDate calendarDate, View view) {
                AlarmVideoActivity.this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        AlarmVideoActivity.this.showOrHideCalendar(false, (AnimUtil.AnimEndListener) null);
                        CalendarDate unused = AlarmVideoActivity.this.mSelectedDate = calendarDate;
                        long[] dayStartAndEndTimeMillis = calendarDate.getDayStartAndEndTimeMillis();
                        SDKLog.b(AlarmVideoActivity.TAG, calendarDate.getYear() + "-" + calendarDate.getMonth() + "-" + calendarDate.getDay());
                        long unused2 = AlarmVideoActivity.this.mEndTime = dayStartAndEndTimeMillis[1];
                        long unused3 = AlarmVideoActivity.this.mStartPoint = dayStartAndEndTimeMillis[0];
                        long unused4 = AlarmVideoActivity.this.mStartTime = AlarmVideoActivity.this.mStartPoint;
                        long unused5 = AlarmVideoActivity.this.mEndPoint = AlarmVideoActivity.this.mEndTime;
                        AlarmVideoActivity.this.tv_cur_day.setText(DateUtils.getMonthDay(AlarmVideoActivity.this.mStartTime + 1));
                        AlarmVideoActivity.this.refreshContent();
                    }
                }, 300);
            }
        });
    }

    private void showLoadingView() {
        if (this.mLoadingView != null) {
            this.mLoadingView.setVisibility(0);
            Drawable drawable = this.mLoadingView.getDrawable();
            if (drawable instanceof AnimationDrawable) {
                ((AnimationDrawable) drawable).start();
            }
        }
    }

    /* access modifiers changed from: private */
    public void hideLoadingView() {
        if (this.mLoadingView != null) {
            Drawable drawable = this.mLoadingView.getDrawable();
            if (drawable instanceof AnimationDrawable) {
                ((AnimationDrawable) drawable).stop();
            }
            this.mLoadingView.setVisibility(8);
        }
    }

    public void initRecycler() {
        this.rv_event_list.setLayoutManager(new LinearLayoutManager(activity(), 1, false));
        this.rv_event_list.setAdapter(this.mAlarmVideoAdapter);
        this.rv_event_list.setHasFixedSize(true);
    }

    public void initPtr() {
        this.ptrLayout.setFooterRefreshView(LayoutInflater.from(this).inflate(R.layout.camera_list_load_more, (ViewGroup) null));
        this.ptrLayout.setOnPullRefreshListener(new RecyclerViewRefreshLayout.OnPullRefreshListener() {
            public void onPullDistance(int i) {
            }

            public void onPullEnable(boolean z) {
            }

            public void onRefresh() {
                if (!AlarmVideoActivity.this.isFinishing()) {
                    AlarmVideoActivity.this.refreshContent();
                }
            }
        });
        this.ptrLayout.setOnPushLoadMoreListener(new RecyclerViewRefreshLayout.OnPushLoadMoreListener() {
            public void onPushDistance(int i) {
            }

            public void onPushEnable(boolean z) {
            }

            public void onLoadMore() {
                if (!AlarmVideoActivity.this.isFinishing()) {
                    AlarmVideoActivity.this.getEventList(AlarmVideoActivity.this.mStartTime, AlarmVideoActivity.this.mEndTime, 2);
                }
            }
        });
    }

    private void initData() {
        long todayStartTime = getTodayStartTime();
        long currentTimeMillis = System.currentTimeMillis();
        this.mStartTime = todayStartTime;
        this.mStartPoint = todayStartTime;
        this.mEndTime = currentTimeMillis;
        this.mEndPoint = currentTimeMillis;
        SceneManager.x().a(this.mDid, this.mListener);
    }

    private long getTodayStartTime() {
        Calendar instance = Calendar.getInstance();
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        return instance.getTimeInMillis();
    }

    public void onResume() {
        super.onResume();
    }

    public void onStop() {
        super.onStop();
    }

    public void refreshContent() {
        showLoadingView();
        long[] dayStartAndEndTimeMillis = DateUtils.getCalendarDate(System.currentTimeMillis()).getDayStartAndEndTimeMillis();
        if (this.mEndPoint > dayStartAndEndTimeMillis[0] && this.mEndPoint < dayStartAndEndTimeMillis[1]) {
            this.mEndPoint = System.currentTimeMillis();
        }
        getEventList(this.mStartPoint, this.mEndPoint, 1);
    }

    public void onAllRecordClicked() {
        boolean z = false;
        if (this.calendar_container.getVisibility() == 0) {
            showOrHideCalendar(false, new AnimUtil.AnimEndListener() {
                public void animEnd() {
                    AlarmVideoActivity.this.showOrHideEventType(AlarmVideoActivity.this.event_type_recycler.getVisibility() != 0, (AnimUtil.AnimEndListener) null);
                }
            });
            return;
        }
        if (this.event_type_recycler.getVisibility() != 0) {
            z = true;
        }
        showOrHideEventType(z, (AnimUtil.AnimEndListener) null);
    }

    /* access modifiers changed from: private */
    public void onEventTypeBgClick() {
        showOrHideEventType(false, (AnimUtil.AnimEndListener) null);
    }

    public void showOrHideEventType(boolean z, AnimUtil.AnimEndListener animEndListener) {
        if (z) {
            AnimUtil.animLayoutTop(activity(), true, (View) null, this.ll_calendar_bg, this.event_type_recycler, animEndListener);
            this.iv_more_record.setImageResource(R.drawable.icon_select_up);
            this.tv_all_record.setTextColor(getResources().getColorStateList(R.color.leave_msg_playing));
            return;
        }
        AnimUtil.animLayoutTop(activity(), false, (View) null, this.ll_calendar_bg, this.event_type_recycler, animEndListener);
        this.iv_more_record.setImageResource(R.drawable.icon_select_down);
        this.tv_all_record.setTextColor(getResources().getColorStateList(R.color.black));
    }

    public void onDestroy() {
        super.onDestroy();
        this.mHandler.removeCallbacksAndMessages((Object) null);
        SceneManager.x().b(this.mListener);
        Event.d();
    }

    /* access modifiers changed from: private */
    public void getEventList(long j, long j2, final int i) {
        SDKLog.b(TAG, "startTime=" + j + " endTime=" + j2);
        if (this.mUserStatus == null) {
            getUserStatus();
        } else if (!this.isNetRefreshing) {
            this.isNetRefreshing = true;
            if (i == 1) {
                this.ptrLayout.setLoadMore(true);
                this.ptrLayout.setRefreshing(false);
                this.ptrLayout.setMode(3);
                this.mAlarmVideoAdapter.setToTheEnd(false);
                this.ll_empty_content.setVisibility(8);
            }
            this.mCameraDevice.a().getEventList(this.mCurEventType.name, j, j2, new AlarmCloudCallback<ArrayList<AlarmVideo>>() {
                public void onSuccess(ArrayList<AlarmVideo> arrayList) {
                }

                public void onSuccess(ArrayList<AlarmVideo> arrayList, long j, boolean z) {
                    if (!AlarmVideoActivity.this.isFinishing()) {
                        AlarmVideoActivity.this.hideLoadingView();
                        boolean unused = AlarmVideoActivity.this.isNetRefreshing = false;
                        if (AlarmVideoActivity.this.ptrLayout.isRefreshing()) {
                            AlarmVideoActivity.this.ptrLayout.setRefreshing(false);
                        }
                        if (i == 1) {
                            AlarmVideoActivity.this.mRecordList.clear();
                        }
                        AlarmVideoActivity.this.mRecordList.addAll(arrayList);
                        if (AlarmVideoActivity.this.mRecordList == null || AlarmVideoActivity.this.mRecordList.size() <= 0) {
                            AlarmVideoActivity.this.mAlarmVideoAdapter.update(AlarmVideoActivity.this.mRecordList);
                            AlarmVideoActivity.this.ll_empty_content.setVisibility(0);
                            AlarmVideoActivity.this.tv_request_result.setText(AlarmVideoActivity.this.getEmptyStringByType(AlarmVideoActivity.this.mCurEventType.name));
                            AlarmVideoActivity.this.ptrLayout.setEnabled(true);
                            return;
                        }
                        AlarmVideoActivity.this.ll_empty_content.setVisibility(8);
                        AlarmVideoActivity.this.ptrLayout.setVisibility(0);
                        AlarmVideoActivity.this.ptrLayout.setEnabled(true);
                        if (!z) {
                            AlarmVideoActivity.this.calculateHeight();
                            if (AlarmVideoActivity.this.mRecordList.size() < AlarmVideoActivity.this.mVisibleItemCount) {
                                AlarmVideoActivity.this.mAlarmVideoAdapter.setToTheEnd(false);
                            } else {
                                AlarmVideoActivity.this.mAlarmVideoAdapter.setToTheEnd(true);
                            }
                            AlarmVideoActivity.this.ptrLayout.setMode(1);
                            AlarmVideoActivity.this.ptrLayout.setLoadMore(false);
                        } else {
                            long unused2 = AlarmVideoActivity.this.mEndTime = j;
                            AlarmVideoActivity.this.mAlarmVideoAdapter.setToTheEnd(false);
                            AlarmVideoActivity.this.ptrLayout.setMode(3);
                            AlarmVideoActivity.this.ptrLayout.setLoadMore(false);
                        }
                        AlarmVideoActivity.this.mAlarmVideoAdapter.update(AlarmVideoActivity.this.mRecordList);
                    }
                }

                public void onFailure(int i, String str) {
                    if (!AlarmVideoActivity.this.isFinishing()) {
                        AlarmVideoActivity.this.hideLoadingView();
                        boolean unused = AlarmVideoActivity.this.isNetRefreshing = false;
                        if (AlarmVideoActivity.this.ptrLayout.isRefreshing()) {
                            AlarmVideoActivity.this.ptrLayout.setRefreshing(false);
                        }
                        AlarmVideoActivity.this.ptrLayout.setLoadMore(false);
                        if (i == 1) {
                            AlarmVideoActivity.this.ll_empty_content.setVisibility(0);
                            AlarmVideoActivity.this.mRecordList.clear();
                            AlarmVideoActivity.this.mAlarmVideoAdapter.update(AlarmVideoActivity.this.mRecordList);
                            AlarmVideoActivity.this.tv_request_result.setText(AlarmVideoActivity.this.getEmptyStringByType(AlarmVideoActivity.this.mCurEventType.name));
                            AlarmVideoActivity.this.ptrLayout.setMode(1);
                        }
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getEmptyStringByType(java.lang.String r2) {
        /*
            r1 = this;
            int r0 = r2.hashCode()
            switch(r0) {
                case -1293551627: goto L_0x003a;
                case -1085510111: goto L_0x0030;
                case 2088: goto L_0x0026;
                case 2181757: goto L_0x001c;
                case 722651973: goto L_0x0012;
                case 1316906260: goto L_0x0008;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x0044
        L_0x0008:
            java.lang.String r0 = "BabyCry"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0044
            r2 = 4
            goto L_0x0045
        L_0x0012:
            java.lang.String r0 = "PeopleMotion"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0044
            r2 = 2
            goto L_0x0045
        L_0x001c:
            java.lang.String r0 = "Face"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0044
            r2 = 3
            goto L_0x0045
        L_0x0026:
            java.lang.String r0 = "AI"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0044
            r2 = 5
            goto L_0x0045
        L_0x0030:
            java.lang.String r0 = "Default"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0044
            r2 = 0
            goto L_0x0045
        L_0x003a:
            java.lang.String r0 = "ObjectMotion"
            boolean r2 = r2.equals(r0)
            if (r2 == 0) goto L_0x0044
            r2 = 1
            goto L_0x0045
        L_0x0044:
            r2 = -1
        L_0x0045:
            r0 = 2131494852(0x7f0c07c4, float:1.8613224E38)
            switch(r2) {
                case 0: goto L_0x0078;
                case 1: goto L_0x0070;
                case 2: goto L_0x0068;
                case 3: goto L_0x0060;
                case 4: goto L_0x0058;
                case 5: goto L_0x0050;
                default: goto L_0x004b;
            }
        L_0x004b:
            java.lang.String r2 = r1.getString(r0)
            return r2
        L_0x0050:
            r2 = 2131494850(0x7f0c07c2, float:1.861322E38)
            java.lang.String r2 = r1.getString(r2)
            return r2
        L_0x0058:
            r2 = 2131494851(0x7f0c07c3, float:1.8613222E38)
            java.lang.String r2 = r1.getString(r2)
            return r2
        L_0x0060:
            r2 = 2131494854(0x7f0c07c6, float:1.8613228E38)
            java.lang.String r2 = r1.getString(r2)
            return r2
        L_0x0068:
            r2 = 2131494856(0x7f0c07c8, float:1.8613232E38)
            java.lang.String r2 = r1.getString(r2)
            return r2
        L_0x0070:
            r2 = 2131494857(0x7f0c07c9, float:1.8613234E38)
            java.lang.String r2 = r1.getString(r2)
            return r2
        L_0x0078:
            java.lang.String r2 = r1.getString(r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.camera.activity.alarm2.AlarmVideoActivity.getEmptyStringByType(java.lang.String):java.lang.String");
    }

    public void getUserStatus() {
        CloudVideoNetUtils.getInstance().getCloudStatus(this, this.mDid, new ICloudVideoCallback<CloudVideoUserStatus>() {
            public void onCloudVideoSuccess(CloudVideoUserStatus cloudVideoUserStatus, Object obj) {
                if (!AlarmVideoActivity.this.isFinishing()) {
                    SDKLog.b("user status", cloudVideoUserStatus.toString());
                    if (cloudVideoUserStatus == null) {
                        CloudVideoUserStatus unused = AlarmVideoActivity.this.mUserStatus = new CloudVideoUserStatus();
                        AlarmVideoActivity.this.mUserStatus.setDefault(true);
                        return;
                    }
                    CloudVideoUserStatus unused2 = AlarmVideoActivity.this.mUserStatus = cloudVideoUserStatus;
                    AlarmVideoActivity.this.updateSelectParamByUserStatus();
                    AlarmVideoActivity.this.getEventList(AlarmVideoActivity.this.mStartPoint, AlarmVideoActivity.this.mEndPoint, 1);
                }
            }

            public void onCloudVideoFailed(int i, String str) {
                if (!AlarmVideoActivity.this.isFinishing()) {
                    CloudVideoUserStatus unused = AlarmVideoActivity.this.mUserStatus = new CloudVideoUserStatus();
                    AlarmVideoActivity.this.mUserStatus.setDefault(true);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateSelectParamByUserStatus() {
        SDKLog.b("lzc", this.mUserStatus.toString());
        long currentTimeMillis = System.currentTimeMillis();
        if (this.mUserStatus.isDefault() || !this.mUserStatus.vip || this.mUserStatus.endTime < currentTimeMillis || this.mUserStatus.startTime > currentTimeMillis) {
            updateEventType(false);
            CEn7DayRecyclerAdapterNew.CLOUD_DAYS = 6;
            long currentTimeMillis2 = System.currentTimeMillis();
            this.custom_calendar.initRecycler(activity());
            this.custom_calendar.addHaveVideoDay(DateUtils.getCalendarDate(currentTimeMillis2 - (CEn7DayRecyclerAdapterNew.CLOUD_DAYS * 86400000)));
            this.custom_calendar.refreshFlag();
            return;
        }
        updateEventType(true);
        int i = this.mUserStatus.rollingSaveInterval;
        if (i <= 8) {
            CEn7DayRecyclerAdapterNew.CLOUD_DAYS = 7;
        } else if (i <= 16) {
            CEn7DayRecyclerAdapterNew.CLOUD_DAYS = 15;
        } else {
            CEn7DayRecyclerAdapterNew.CLOUD_DAYS = 30;
        }
        long currentTimeMillis3 = System.currentTimeMillis();
        this.custom_calendar.initRecycler(activity());
        this.custom_calendar.addHaveVideoDay(DateUtils.getCalendarDate(currentTimeMillis3 - (CEn7DayRecyclerAdapterNew.CLOUD_DAYS * 86400000)));
        this.custom_calendar.refreshFlag();
    }

    private void updateEventType(boolean z) {
        this.mAlarmVideoAdapter.setVipStatus(z);
        int length = this.eventTypes.length;
        final ArrayList arrayList = new ArrayList();
        boolean z2 = false;
        for (int i = 0; i < length; i++) {
            if ((z || !(i == 3 || i == 4)) && (this.mHasAISceneBuilt || i != 5)) {
                EventType eventType = new EventType();
                eventType.name = this.eventTypes[i];
                eventType.desc = this.eventTypesDesc[i];
                arrayList.add(eventType);
                if (this.mCurEventType.name.equals(eventType.name)) {
                    z2 = true;
                }
            }
        }
        if (!z2) {
            this.mCurEventType.name = this.eventTypes[0];
            this.mCurEventType.desc = this.eventTypesDesc[0];
            this.mCameraDevice.e().b(this.mCurEventType.name);
        }
        this.tv_all_record.setText(this.mCurEventType.desc);
        this.eventTypeAdapter = new RecyclerEventTypeAdapter(activity(), arrayList, new RecyclerEventTypeAdapter.OnItemClick() {
            public void onItemClick(int i) {
                EventType unused = AlarmVideoActivity.this.mCurEventType = (EventType) arrayList.get(i);
                AlarmVideoActivity.this.tv_all_record.setText(AlarmVideoActivity.this.mCurEventType.desc);
                AlarmVideoActivity.this.mCameraDevice.e().b(AlarmVideoActivity.this.mCurEventType.name);
                AlarmVideoActivity.this.onEventTypeBgClick();
                AlarmVideoActivity.this.refreshContent();
                int i2 = 1;
                if (!"Default".equalsIgnoreCase(AlarmVideoActivity.this.mCurEventType.name)) {
                    if (AlarmCloudManager.OBJECT_MOTION.equalsIgnoreCase(AlarmVideoActivity.this.mCurEventType.name)) {
                        i2 = 2;
                    } else if (AlarmCloudManager.PEOPLE_MOTION.equalsIgnoreCase(AlarmVideoActivity.this.mCurEventType.name)) {
                        i2 = 3;
                    } else if (AlarmCloudManager.BABY_CRY.equalsIgnoreCase(AlarmVideoActivity.this.mCurEventType.name)) {
                        i2 = 4;
                    } else if (AlarmCloudManager.FACE.equalsIgnoreCase(AlarmVideoActivity.this.mCurEventType.name)) {
                        i2 = 5;
                    } else if (AlarmCloudManager.AI.equalsIgnoreCase(AlarmVideoActivity.this.mCurEventType.name)) {
                        i2 = 6;
                    }
                }
                Event.a(Event.bG, "type", (Object) Integer.valueOf(i2));
            }
        });
        this.event_type_recycler.setLayoutManager(new GridLayoutManager(activity(), 2));
        this.event_type_recycler.setAdapter(this.eventTypeAdapter);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_next_day:
            case R.id.tv_cur_day:
                onSelectDayClick();
                return;
            case R.id.ll_all_record:
                onAllRecordClicked();
                return;
            case R.id.ll_calendar_bg:
                if (this.calendar_container.getVisibility() == 0) {
                    onCalendarBgClicked();
                    return;
                } else if (this.event_type_recycler.getVisibility() == 0) {
                    onEventTypeBgClick();
                    return;
                } else {
                    return;
                }
            case R.id.see_all_video:
                Event.a(Event.aO);
                showOrHideCalendar(false, (AnimUtil.AnimEndListener) null);
                if (this.mUserStatus != null && this.mUserStatus.vip) {
                    FrameManager.b().k().openCloudVideoListActivityForResult(this, this.mCameraDevice.getDid(), this.mCameraDevice.getName(), 17);
                    return;
                } else if (this.isV4) {
                    startActivityForResult(new Intent(this, AlarmDayV2Activity.class), 17);
                    return;
                } else {
                    startActivityForResult(new Intent(this, com.xiaomi.smarthome.camera.activity.alarm.AlarmDayV2Activity.class), 17);
                    return;
                }
            case R.id.title_bar_more:
                startActivity(new Intent(this, AlarmSettingV2Activity.class));
                Event.a(Event.aR);
                return;
            case R.id.title_bar_return:
                finish();
                return;
            default:
                return;
        }
    }

    private void onSelectDayClick() {
        Event.a(Event.bF);
        boolean z = false;
        if (this.event_type_recycler.getVisibility() == 0) {
            showOrHideEventType(false, new AnimUtil.AnimEndListener() {
                public void animEnd() {
                    AlarmVideoActivity.this.showOrHideCalendar(AlarmVideoActivity.this.calendar_container.getVisibility() != 0, (AnimUtil.AnimEndListener) null);
                }
            });
            return;
        }
        if (this.calendar_container.getVisibility() != 0) {
            z = true;
        }
        showOrHideCalendar(z, (AnimUtil.AnimEndListener) null);
    }

    public void onCalendarBgClicked() {
        showOrHideCalendar(false, (AnimUtil.AnimEndListener) null);
    }

    public void showOrHideCalendar(boolean z, AnimUtil.AnimEndListener animEndListener) {
        if (z) {
            AnimUtil.animLayoutTop(activity(), true, (View) null, this.ll_calendar_bg, this.calendar_container, animEndListener);
            this.iv_next_day.setImageResource(R.drawable.icon_select_up);
            this.tv_cur_day.setTextColor(getResources().getColorStateList(R.color.leave_msg_playing));
            return;
        }
        AnimUtil.animLayoutTop(activity(), false, (View) null, this.ll_calendar_bg, this.calendar_container, animEndListener);
        this.iv_next_day.setImageResource(R.drawable.icon_select_down);
        this.tv_cur_day.setTextColor(getResources().getColorStateList(R.color.black));
    }

    @NonNull
    @android.support.annotation.NonNull
    public LayoutInflater getLayoutInflater() {
        if (this.mLayoutInflater != null) {
            return this.mLayoutInflater;
        }
        return super.getLayoutInflater();
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 17) {
            if (i == 1001 && i2 == -1) {
                refreshContent();
            }
        } else if (i2 == -1) {
            refreshContent();
        }
    }
}
