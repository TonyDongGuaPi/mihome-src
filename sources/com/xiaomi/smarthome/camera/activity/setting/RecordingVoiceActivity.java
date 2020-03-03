package com.xiaomi.smarthome.camera.activity.setting;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.PermissionChecker;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mijia.camera.CameraPlayerEx;
import com.mijia.camera.ICameraPlayerListener;
import com.mijia.camera.Utils.Util;
import com.mijia.debug.SDKLog;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.IStreamCmdMessageListener;
import com.xiaomi.smarthome.camera.activity.CameraPlayerBaseActivity;
import com.xiaomi.smarthome.camera.activity.CommandTreatment;
import com.xiaomi.smarthome.camera.activity.SPUtil;
import com.xiaomi.smarthome.camera.activity.setting.bean.Desc;
import com.xiaomi.smarthome.camera.activity.setting.bean.GetLeavMsgData;
import com.xiaomi.smarthome.camera.activity.setting.bean.LeaveMsg;
import com.xiaomi.smarthome.camera.activity.setting.record.GlobalParam;
import com.xiaomi.smarthome.camera.activity.setting.record.LeaveMsgManager;
import com.xiaomi.smarthome.camera.activity.setting.record.LeaveMsgUtil;
import com.xiaomi.smarthome.camera.activity.setting.record.PlayLeaveMsg;
import com.xiaomi.smarthome.camera.activity.setting.record.RecorderLeaveMsg;
import com.xiaomi.smarthome.camera.activity.voice.VoiceManager;
import com.xiaomi.smarthome.camera.view.calendar.DateUtils;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class RecordingVoiceActivity extends CameraPlayerBaseActivity implements View.OnClickListener, ICameraPlayerListener, IStreamCmdMessageListener {
    public static final String AUTO_PLAY_RECORD = "AUTO_PLAY_RECORD";
    public static final int HANDLER_MSG_RECORD_SUCCESS = 1000;
    public static final String MANUAL_PLAY_RECORD = "MANUAL_PLAY_RECORD";
    public static final String SETTING_KEY = "SETTING_KEY_MAIN";
    public static final String TAG = "RecordingVoiceActivity";
    /* access modifiers changed from: private */
    public ArrayList<LeaveMsg> allMsgs = new ArrayList<>();
    /* access modifiers changed from: private */
    public AnimationDrawable animationDrawable;
    /* access modifiers changed from: private */
    public LeaveMsg bindMsg;
    /* access modifiers changed from: private */
    public int currentPosition;
    /* access modifiers changed from: private */
    public boolean edited = false;
    private ImageView iv_loading;
    private ImageView iv_record_voice;
    /* access modifiers changed from: private */
    public ImageView iv_speak_animation;
    /* access modifiers changed from: private */
    public String key;
    /* access modifiers changed from: private */
    public LeaveMsgAdapter leaveMsgAdapter;
    private RecyclerView leave_msg_recycler_view;
    /* access modifiers changed from: private */
    public LinearLayout ll_record_empty;
    /* access modifiers changed from: private */
    public LinearLayout ll_record_failed;
    /* access modifiers changed from: private */
    public LinearLayout ll_record_loading;
    /* access modifiers changed from: private */
    public LinearLayout ll_record_short;
    /* access modifiers changed from: private */
    public LinearLayout ll_record_success;
    /* access modifiers changed from: private */
    public LinearLayout ll_recording;
    private Animation loadAnimation;
    /* access modifiers changed from: private */
    public VoiceManager mVoiceManager;
    /* access modifiers changed from: private */
    public ArrayList<LeaveMsg> msgs = new ArrayList<>();
    public Handler myHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (message.what == 1000) {
                String str = (String) message.obj;
                long j = (long) message.arg1;
                String str2 = RecordingVoiceActivity.TAG;
                SDKLog.e(str2, "totalTime=" + j);
                if (j < 1) {
                    SDKLog.e(RecordingVoiceActivity.TAG, "录制时间太短小于1s");
                    return;
                }
                RecordingVoiceActivity.this.clearRecord();
                String a2 = Util.a(System.currentTimeMillis(), RecordingVoiceActivity.this.getResources());
                final long j2 = j;
                final String str3 = a2;
                final String str4 = str;
                LeaveMsgManager.getInstance(RecordingVoiceActivity.this.activity()).putData(RecordingVoiceActivity.this.mCameraDevice, j2, str, a2, new Callback<Object>() {
                    public void onFailure(int i, String str) {
                    }

                    public void onSuccess(Object obj) {
                        LeaveMsg leaveMsg = new LeaveMsg();
                        leaveMsg.itemId = ((Long) obj).longValue();
                        leaveMsg.descObj = new Desc();
                        leaveMsg.descObj.duration = j2 * 1000;
                        leaveMsg.descObj.name = str3;
                        RecordingVoiceActivity.this.msgs.add(leaveMsg);
                        RecordingVoiceActivity.this.allMsgs.add(leaveMsg);
                        boolean unused = RecordingVoiceActivity.this.edited = true;
                        RecordingVoiceActivity.this.refreshUINew();
                        LeaveMsgManager.getInstance(RecordingVoiceActivity.this.activity()).addDownLoadItem(leaveMsg.itemId, str4, RecordingVoiceActivity.this.mCameraDevice.getDid());
                        RecordingVoiceActivity.this.mVoiceManager.sendRecordVoiceSuccess(leaveMsg.itemId, RecordingVoiceActivity.this);
                    }
                });
            }
        }
    };
    long onVoiceBtnClickDownTime = 0;
    private String[] permitArray = {"android.permission.RECORD_AUDIO", "android.permission.WRITE_EXTERNAL_STORAGE"};
    /* access modifiers changed from: private */
    public TextView record_time_tv;
    /* access modifiers changed from: private */
    public RecorderLeaveMsg recorderLeaveMsg;
    /* access modifiers changed from: private */
    public TextView recording_voice;
    /* access modifiers changed from: private */
    public RelativeLayout rl_voice_tip;
    /* access modifiers changed from: private */
    public long startTime;
    Runnable timerRunnable = new Runnable() {
        public void run() {
            int currentTimeMillis = (int) ((System.currentTimeMillis() - RecordingVoiceActivity.this.startTime) / 1000);
            String str = RecordingVoiceActivity.TAG;
            SDKLog.e(str, "duration===" + currentTimeMillis);
            if (currentTimeMillis >= 6) {
                RecordingVoiceActivity.this.onClickRecord();
                return;
            }
            String timeFromNum = DateUtils.getTimeFromNum(currentTimeMillis);
            String str2 = RecordingVoiceActivity.TAG;
            SDKLog.e(str2, "timerRunnable timeFromNum=" + timeFromNum);
            String access$2300 = RecordingVoiceActivity.this.removeRepeat(timeFromNum);
            if (access$2300.contains("6")) {
                SDKLog.e(RecordingVoiceActivity.TAG, "录音的时间大于6s,强制转为5s");
                RecordingVoiceActivity.this.record_time_tv.setText("00:05");
            } else {
                RecordingVoiceActivity.this.record_time_tv.setText(access$2300);
            }
            RecordingVoiceActivity.this.myHandler.postDelayed(RecordingVoiceActivity.this.timerRunnable, 133);
        }
    };

    /* access modifiers changed from: protected */
    public void detectSDCard() {
    }

    /* access modifiers changed from: protected */
    public void doResume() {
    }

    public boolean isHistory() {
        return false;
    }

    public void onDisconnectedWithCode(int i) {
    }

    /* access modifiers changed from: protected */
    public void refreshUI() {
    }

    /* access modifiers changed from: protected */
    public void resumeCamera() {
    }

    /* access modifiers changed from: protected */
    public void startPlay() {
    }

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        TitleBarUtil.a((Activity) this);
        setContentView(R.layout.setting_record_voice_activity);
        this.key = getIntent().getExtras().getString(SETTING_KEY);
        this.recorderLeaveMsg = new RecorderLeaveMsg(activity(), this.myHandler, this.mCameraDevice);
        initView();
        intData();
        this.mVoiceManager = new VoiceManager(this.mCameraDevice, this);
    }

    public void onResume() {
        super.onResume();
        SDKLog.e(TAG, "onResume....");
        this.mCameraPlayerEx = new CameraPlayerEx(this.mCameraDevice, this);
    }

    public void onPause() {
        super.onPause();
        this.mCameraPlayerEx.w();
        this.mCameraPlayerEx = null;
        onStopRecord();
    }

    private void initView() {
        findViewById(R.id.title_bar_return).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                RecordingVoiceActivity.this.onBackPressed();
            }
        });
        ((TextView) findViewById(R.id.title_bar_title)).setText(R.string.recording_voice);
        findViewById(R.id.tv_record_voice).setVisibility(8);
        this.recording_voice = (TextView) findViewById(R.id.recording_voice);
        this.rl_voice_tip = (RelativeLayout) findViewById(R.id.rl_voice_tip);
        this.ll_record_short = (LinearLayout) findViewById(R.id.ll_record_short);
        this.ll_record_success = (LinearLayout) findViewById(R.id.ll_record_success);
        this.ll_recording = (LinearLayout) findViewById(R.id.ll_recording);
        this.record_time_tv = (TextView) findViewById(R.id.record_time_tv);
        this.leave_msg_recycler_view = (RecyclerView) findViewById(R.id.leave_msg_recycler_view);
        this.leave_msg_recycler_view.setLayoutManager(new LinearLayoutManager(this));
        this.leaveMsgAdapter = new LeaveMsgAdapter();
        this.leave_msg_recycler_view.setAdapter(this.leaveMsgAdapter);
        this.ll_record_empty = (LinearLayout) findViewById(R.id.ll_record_empty);
        this.iv_record_voice = (ImageView) findViewById(R.id.iv_record_voice);
        this.iv_record_voice.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    SDKLog.e(RecordingVoiceActivity.TAG, "MotionEvent.ACTION_DOWN");
                    RecordingVoiceActivity.this.onVoiceBtnClickDownTime = System.currentTimeMillis();
                    RecordingVoiceActivity.this.rl_voice_tip.setVisibility(0);
                    RecordingVoiceActivity.this.ll_record_short.setVisibility(8);
                    RecordingVoiceActivity.this.ll_record_success.setVisibility(8);
                    RecordingVoiceActivity.this.ll_recording.setVisibility(0);
                    RecordingVoiceActivity.this.ll_record_loading.setVisibility(8);
                    RecordingVoiceActivity.this.ll_record_failed.setVisibility(8);
                    AnimationDrawable unused = RecordingVoiceActivity.this.animationDrawable = (AnimationDrawable) RecordingVoiceActivity.this.iv_speak_animation.getDrawable();
                    RecordingVoiceActivity.this.animationDrawable.start();
                } else if (motionEvent.getAction() == 1) {
                    SDKLog.e(RecordingVoiceActivity.TAG, "MotionEvent.ACTION_UP");
                    if (System.currentTimeMillis() - RecordingVoiceActivity.this.onVoiceBtnClickDownTime < 1000) {
                        SDKLog.e(RecordingVoiceActivity.TAG, "MotionEvent.ACTION_UP 小于1s");
                        RecordingVoiceActivity.this.onStopRecord();
                        RecordingVoiceActivity.this.rl_voice_tip.setVisibility(0);
                        RecordingVoiceActivity.this.ll_record_short.setVisibility(0);
                        RecordingVoiceActivity.this.ll_record_success.setVisibility(8);
                        RecordingVoiceActivity.this.ll_recording.setVisibility(8);
                        RecordingVoiceActivity.this.ll_record_loading.setVisibility(8);
                        RecordingVoiceActivity.this.ll_record_failed.setVisibility(8);
                        AnimationDrawable unused2 = RecordingVoiceActivity.this.animationDrawable = (AnimationDrawable) RecordingVoiceActivity.this.iv_speak_animation.getDrawable();
                        RecordingVoiceActivity.this.animationDrawable.stop();
                        RecordingVoiceActivity.this.mHandler.postDelayed(new Runnable() {
                            public void run() {
                                SDKLog.e(RecordingVoiceActivity.TAG, "1s之后消失");
                                RecordingVoiceActivity.this.rl_voice_tip.setVisibility(8);
                            }
                        }, 1000);
                    } else {
                        SDKLog.e(RecordingVoiceActivity.TAG, "MotionEvent.ACTION_UP 大于1s");
                        RecordingVoiceActivity.this.onStopRecord();
                        RecordingVoiceActivity.this.rl_voice_tip.setVisibility(0);
                        RecordingVoiceActivity.this.ll_record_short.setVisibility(8);
                        RecordingVoiceActivity.this.ll_recording.setVisibility(8);
                        RecordingVoiceActivity.this.ll_record_success.setVisibility(8);
                        RecordingVoiceActivity.this.ll_record_loading.setVisibility(0);
                        RecordingVoiceActivity.this.ll_record_failed.setVisibility(8);
                        AnimationDrawable unused3 = RecordingVoiceActivity.this.animationDrawable = (AnimationDrawable) RecordingVoiceActivity.this.iv_speak_animation.getDrawable();
                        RecordingVoiceActivity.this.animationDrawable.stop();
                    }
                } else if (motionEvent.getAction() == 3) {
                    SDKLog.e(RecordingVoiceActivity.TAG, "MotionEvent.ACTION_CANCEL");
                    if (System.currentTimeMillis() - RecordingVoiceActivity.this.onVoiceBtnClickDownTime < 1000) {
                        SDKLog.e(RecordingVoiceActivity.TAG, "MotionEvent.ACTION_UP 小于1s");
                        RecordingVoiceActivity.this.onStopRecord();
                        RecordingVoiceActivity.this.rl_voice_tip.setVisibility(0);
                        RecordingVoiceActivity.this.ll_record_short.setVisibility(0);
                        RecordingVoiceActivity.this.ll_record_success.setVisibility(8);
                        RecordingVoiceActivity.this.ll_recording.setVisibility(8);
                        RecordingVoiceActivity.this.ll_record_loading.setVisibility(8);
                        RecordingVoiceActivity.this.ll_record_failed.setVisibility(8);
                        AnimationDrawable unused4 = RecordingVoiceActivity.this.animationDrawable = (AnimationDrawable) RecordingVoiceActivity.this.iv_speak_animation.getDrawable();
                        RecordingVoiceActivity.this.animationDrawable.stop();
                        RecordingVoiceActivity.this.mHandler.postDelayed(new Runnable() {
                            public void run() {
                                SDKLog.e(RecordingVoiceActivity.TAG, "1s之后消失");
                                RecordingVoiceActivity.this.rl_voice_tip.setVisibility(8);
                            }
                        }, 1000);
                    } else {
                        SDKLog.e(RecordingVoiceActivity.TAG, "MotionEvent.ACTION_UP 大于1s");
                        RecordingVoiceActivity.this.onStopRecord();
                        RecordingVoiceActivity.this.rl_voice_tip.setVisibility(8);
                        RecordingVoiceActivity.this.ll_record_short.setVisibility(8);
                        RecordingVoiceActivity.this.ll_recording.setVisibility(8);
                        RecordingVoiceActivity.this.ll_record_success.setVisibility(8);
                        RecordingVoiceActivity.this.ll_record_loading.setVisibility(8);
                        RecordingVoiceActivity.this.ll_record_failed.setVisibility(8);
                        AnimationDrawable unused5 = RecordingVoiceActivity.this.animationDrawable = (AnimationDrawable) RecordingVoiceActivity.this.iv_speak_animation.getDrawable();
                        RecordingVoiceActivity.this.animationDrawable.stop();
                        RecordingVoiceActivity.this.mHandler.postDelayed(new Runnable() {
                            public void run() {
                                SDKLog.e(RecordingVoiceActivity.TAG, "1s之后消失");
                                RecordingVoiceActivity.this.rl_voice_tip.setVisibility(8);
                            }
                        }, 1000);
                    }
                } else if (motionEvent.getAction() == 2) {
                    SDKLog.e(RecordingVoiceActivity.TAG, "MotionEvent.ACTION_MOVE");
                }
                return false;
            }
        });
        this.iv_record_voice.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                SDKLog.e(RecordingVoiceActivity.TAG, "setOnLongClickListener");
                RecordingVoiceActivity.this.recording();
                return false;
            }
        });
        this.iv_speak_animation = (ImageView) findViewById(R.id.iv_speak_animation);
        this.iv_loading = (ImageView) findViewById(R.id.iv_loading);
        this.loadAnimation = AnimationUtils.loadAnimation(activity(), R.anim.anim_rotate_loading);
        if (this.loadAnimation != null) {
            this.iv_loading.startAnimation(this.loadAnimation);
        }
        this.ll_record_loading = (LinearLayout) findViewById(R.id.ll_record_loading);
        this.ll_record_failed = (LinearLayout) findViewById(R.id.ll_record_failed);
    }

    private void intData() {
        String str = (String) SPUtil.getInstance(activity(), this.mCameraDevice.getDid()).get(SPUtil.KEY_LEAVE_MSGS, "");
        if (!TextUtils.isEmpty(str)) {
            String str2 = TAG;
            SDKLog.e(str2, "msgsJson=" + str);
            this.allMsgs = ((GetLeavMsgData) GlobalParam.gGson.fromJson(str, GetLeavMsgData.class)).resultList;
        }
        LeaveMsgManager.getInstance(activity()).getAllMsgs().clear();
        LeaveMsgManager.getInstance(activity()).getData(this.mCameraDevice, 0, 10, new Callback<Object>() {
            public void onSuccess(Object obj) {
                SDKLog.e(RecordingVoiceActivity.TAG, "获取录音数据成功");
                RecordingVoiceActivity.this.ll_record_empty.setVisibility(8);
                RecordingVoiceActivity.this.msgs.clear();
                RecordingVoiceActivity.this.msgs.addAll(LeaveMsgManager.getInstance(RecordingVoiceActivity.this.activity()).getAllMsgs());
                String str = RecordingVoiceActivity.TAG;
                SDKLog.e(str, "msgs=" + RecordingVoiceActivity.this.msgs.size());
                if (RecordingVoiceActivity.this.msgs == null || RecordingVoiceActivity.this.msgs.size() <= 0) {
                    RecordingVoiceActivity.this.ll_record_empty.setVisibility(0);
                    return;
                }
                RecordingVoiceActivity.this.leaveMsgAdapter.notifyDataSetChanged();
                RecordingVoiceActivity.this.recording_voice.setText(RecordingVoiceActivity.this.getString(R.string.re_recording));
                RecordingVoiceActivity.this.ll_record_empty.setVisibility(8);
                if (RecordingVoiceActivity.AUTO_PLAY_RECORD.equals(RecordingVoiceActivity.this.key)) {
                    LeaveMsg unused = RecordingVoiceActivity.this.bindMsg = (LeaveMsg) RecordingVoiceActivity.this.msgs.get(0);
                    int unused2 = RecordingVoiceActivity.this.currentPosition = 0;
                    RecordingVoiceActivity.this.mVoiceManager.voiceAudition(RecordingVoiceActivity.this);
                }
                Iterator it = RecordingVoiceActivity.this.msgs.iterator();
                while (it.hasNext()) {
                    LeaveMsg leaveMsg = (LeaveMsg) it.next();
                    if (!RecordingVoiceActivity.this.containsByItemId(leaveMsg.itemId)) {
                        boolean unused3 = RecordingVoiceActivity.this.edited = true;
                        RecordingVoiceActivity.this.allMsgs.add(leaveMsg);
                    }
                }
            }

            public void onFailure(int i, String str) {
                String str2 = RecordingVoiceActivity.TAG;
                SDKLog.e(str2, "getData onFailure=" + i + str);
                RecordingVoiceActivity.this.ll_record_empty.setVisibility(0);
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean containsByItemId(long j) {
        for (int size = this.allMsgs.size() - 1; size >= 0; size--) {
            LeaveMsg leaveMsg = this.allMsgs.get(size);
            SDKLog.e(TAG, j + "=" + leaveMsg.itemId);
            if (leaveMsg.itemId == j) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void removeByItemId(long j) {
        this.edited = true;
        for (int size = this.allMsgs.size() - 1; size >= 0; size--) {
            LeaveMsg leaveMsg = this.allMsgs.get(size);
            if (leaveMsg.itemId == j) {
                this.allMsgs.remove(leaveMsg);
                return;
            }
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.ll_record_voice) {
            if (XmPluginHostApi.instance().getApiLevel() >= 75) {
                XmPluginHostApi.instance().checkAndRequestPermisson(activity(), true, new Callback<List<String>>() {
                    public void onSuccess(List<String> list) {
                        SDKLog.c(RecordingVoiceActivity.TAG, "checkAndRequestPermisson onSuccess");
                        RecordingVoiceActivity.this.onClickRecord();
                    }

                    public void onFailure(int i, String str) {
                        ToastUtil.a((Context) RecordingVoiceActivity.this.activity(), (int) R.string.file_permission_denied);
                        RecordingVoiceActivity.this.rl_voice_tip.setVisibility(8);
                    }
                }, this.permitArray);
            } else if (PermissionChecker.checkSelfPermission(activity(), "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
                ToastUtil.a((Context) activity(), (int) R.string.file_permission_denied);
                this.rl_voice_tip.setVisibility(8);
            } else if (PermissionChecker.checkSelfPermission(activity(), "android.permission.RECORD_AUDIO") != 0) {
                ToastUtil.a((Context) activity(), (int) R.string.audio_permission_denied);
                this.rl_voice_tip.setVisibility(8);
            } else {
                onClickRecord();
            }
        }
    }

    /* access modifiers changed from: private */
    public void onClickRecord() {
        if (!this.recorderLeaveMsg.isRecording()) {
            this.rl_voice_tip.setVisibility(0);
            this.ll_recording.setVisibility(0);
            this.ll_record_short.setVisibility(8);
            this.ll_record_failed.setVisibility(8);
            this.ll_record_success.setVisibility(8);
            this.ll_record_loading.setVisibility(8);
            this.recorderLeaveMsg.startRecord();
            this.startTime = System.currentTimeMillis();
            this.record_time_tv.setVisibility(0);
            this.record_time_tv.setText("00:00");
            this.myHandler.postDelayed(this.timerRunnable, 868);
            this.animationDrawable = (AnimationDrawable) this.iv_speak_animation.getDrawable();
            this.animationDrawable.start();
            return;
        }
        onStopRecord();
    }

    /* access modifiers changed from: private */
    public void onStopRecord() {
        if (this.recorderLeaveMsg.isRecording()) {
            this.myHandler.removeCallbacks(this.timerRunnable);
            this.recorderLeaveMsg.stop();
            this.record_time_tv.setVisibility(8);
            this.rl_voice_tip.setVisibility(0);
            this.ll_record_success.setVisibility(8);
            this.ll_record_short.setVisibility(8);
            this.ll_recording.setVisibility(8);
            this.ll_record_failed.setVisibility(8);
            this.ll_record_loading.setVisibility(0);
            this.animationDrawable = (AnimationDrawable) this.iv_speak_animation.getDrawable();
            this.animationDrawable.stop();
        }
    }

    /* access modifiers changed from: private */
    public void refreshUINew() {
        this.leaveMsgAdapter.notifyDataSetChanged();
    }

    public void onServerCmd(int i, byte[] bArr) {
        String str = TAG;
        SDKLog.e(str, "录制声音页 CommandTreatment onServerCmd type=" + i);
        new CommandTreatment(this.mHandler, this.mCameraDevice).processData(bArr);
    }

    public void onConnected() {
        super.onConnected();
        SDKLog.e(TAG, "摄像头连接成功，授权+校准时间");
        this.mCameraPlayerEx.A();
        this.mCameraPlayerEx.z();
    }

    public void onSendCmdError() {
        SDKLog.e(TAG, "onSendCmdError");
        ToastUtil.a((Context) activity(), (CharSequence) getString(R.string.operation_failed));
    }

    class LeaveMsgAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        /* access modifiers changed from: private */
        public boolean globalIsPlaying = false;

        LeaveMsgAdapter() {
        }

        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new LeaveMsgViewHolder(LayoutInflater.from(RecordingVoiceActivity.this.activity()).inflate(R.layout.camera_item_leave_msg, viewGroup, false));
        }

        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            long j;
            int unused = RecordingVoiceActivity.this.currentPosition = i;
            LeaveMsgViewHolder leaveMsgViewHolder = (LeaveMsgViewHolder) viewHolder;
            LeaveMsg unused2 = RecordingVoiceActivity.this.bindMsg = (LeaveMsg) RecordingVoiceActivity.this.msgs.get(i);
            Desc desc = RecordingVoiceActivity.this.bindMsg.descObj;
            leaveMsgViewHolder.tv_msg_content.setText(desc == null ? RecordingVoiceActivity.this.bindMsg.desc : desc.name);
            if (desc == null) {
                j = 0;
            } else {
                j = desc.duration / 1000;
            }
            if (j < 10) {
                leaveMsgViewHolder.duration_tv.setText(String.format(Locale.getDefault(), "00:0%d", new Object[]{Long.valueOf(j)}));
            } else {
                leaveMsgViewHolder.duration_tv.setText(String.format(Locale.getDefault(), "00:%d", new Object[]{Long.valueOf(j)}));
            }
            if (RecordingVoiceActivity.this.bindMsg.isPlaying) {
                leaveMsgViewHolder.duration_tv.setTextColor(RecordingVoiceActivity.this.getResources().getColor(R.color.leave_msg_playing));
                leaveMsgViewHolder.tv_msg_content.setTextColor(RecordingVoiceActivity.this.getResources().getColor(R.color.leave_msg_playing));
                leaveMsgViewHolder.icon_msg_play.setImageResource(R.drawable.icon_leave_msg_stop);
            } else {
                leaveMsgViewHolder.duration_tv.setTextColor(RecordingVoiceActivity.this.getResources().getColor(R.color.leave_msg_no_playing));
                leaveMsgViewHolder.tv_msg_content.setTextColor(RecordingVoiceActivity.this.getResources().getColor(R.color.black));
                leaveMsgViewHolder.icon_msg_play.setImageResource(R.drawable.icon_leave_msg_play);
            }
            leaveMsgViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!LeaveMsgAdapter.this.globalIsPlaying && !RecordingVoiceActivity.this.recorderLeaveMsg.isRecording()) {
                        SDKLog.e(RecordingVoiceActivity.TAG, "发送了 个性语音试听功能");
                        RecordingVoiceActivity.this.mVoiceManager.voiceAudition(RecordingVoiceActivity.this);
                    }
                }
            });
        }

        /* access modifiers changed from: package-private */
        public void palyRecord(final LeaveMsg leaveMsg, final int i) {
            String str = RecordingVoiceActivity.TAG;
            SDKLog.e(str, "播放的录音路径=" + LeaveMsgUtil.getAudioFilePath(RecordingVoiceActivity.this.mCameraDevice.getDid(), leaveMsg.itemId));
            new PlayLeaveMsg(RecordingVoiceActivity.this.activity(), LeaveMsgUtil.getAudioFilePath(RecordingVoiceActivity.this.mCameraDevice.getDid(), leaveMsg.itemId)).startPlay(new PlayLeaveMsg.PlayListener() {
                public void onPlaying() {
                    boolean unused = LeaveMsgAdapter.this.globalIsPlaying = true;
                    leaveMsg.isPlaying = true;
                    RecordingVoiceActivity.this.myHandler.post(new Runnable() {
                        public void run() {
                            LeaveMsgAdapter.this.notifyItemChanged(i);
                        }
                    });
                }

                public void onStop() {
                    boolean unused = LeaveMsgAdapter.this.globalIsPlaying = false;
                    leaveMsg.isPlaying = false;
                    RecordingVoiceActivity.this.myHandler.post(new Runnable() {
                        public void run() {
                            LeaveMsgAdapter.this.notifyItemChanged(i);
                        }
                    });
                }
            });
        }

        public int getItemCount() {
            return RecordingVoiceActivity.this.msgs.size();
        }

        class LeaveMsgViewHolder extends RecyclerView.ViewHolder {
            TextView duration_tv;
            ImageView icon_msg_play;
            TextView tv_msg_content;

            public LeaveMsgViewHolder(View view) {
                super(view);
                this.tv_msg_content = (TextView) view.findViewById(R.id.tv_msg_content);
                this.duration_tv = (TextView) view.findViewById(R.id.duration_tv);
                this.icon_msg_play = (ImageView) view.findViewById(R.id.icon_msg_play);
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        SDKLog.e(VoiceBroadCastActivity.TAG, "onDestroy.....");
        this.myHandler.removeCallbacksAndMessages((Object) null);
        if (this.edited) {
            SPUtil.getInstance(activity(), this.mCameraDevice.getDid()).reSaveLeaveMsgs(this.allMsgs);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        SDKLog.e(VoiceBroadCastActivity.TAG, "onBackPressed.....");
        this.myHandler.removeCallbacksAndMessages((Object) null);
        SPUtil.getInstance(activity(), this.mCameraDevice.getDid()).reSaveLeaveMsgs(this.msgs);
    }

    /* access modifiers changed from: private */
    public void clearRecord() {
        String str = TAG;
        SDKLog.e(str, "clearRecord msgs.size()=" + this.msgs.size());
        if (this.msgs != null && this.msgs.size() > 0) {
            Iterator<LeaveMsg> it = this.msgs.iterator();
            while (it.hasNext()) {
                final LeaveMsg next = it.next();
                LeaveMsgManager.getInstance(activity()).removeItem(this.mCameraDevice, next.itemId, new Callback<Object>() {
                    public void onSuccess(Object obj) {
                        RecordingVoiceActivity.this.msgs.remove(next);
                        RecordingVoiceActivity.this.removeByItemId(next.itemId);
                        String audioFilePath = LeaveMsgUtil.getAudioFilePath(RecordingVoiceActivity.this.mCameraDevice.getDid(), next.itemId);
                        SDKLog.e(VoiceBroadCastActivity.TAG, "clearRecord fileName =" + audioFilePath);
                        File file = new File(audioFilePath);
                        if (file.exists()) {
                            SDKLog.e(VoiceBroadCastActivity.TAG, "删除了 录音文件");
                            file.delete();
                            SPUtil.getInstance(RecordingVoiceActivity.this.activity(), RecordingVoiceActivity.this.mCameraDevice.getDid()).remove(SPUtil.KEY_LEAVE_MSGS);
                            RecordingVoiceActivity.this.msgs.clear();
                        }
                    }

                    public void onFailure(int i, String str) {
                        String str2 = RecordingVoiceActivity.TAG;
                        SDKLog.e(str2, "删除 onFailure i=" + i + ",s=" + str);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public void recording() {
        if (XmPluginHostApi.instance().getApiLevel() >= 75) {
            XmPluginHostApi.instance().checkAndRequestPermisson(activity(), true, new Callback<List<String>>() {
                public void onSuccess(List<String> list) {
                    SDKLog.c(RecordingVoiceActivity.TAG, "checkAndRequestPermisson onSuccess");
                    RecordingVoiceActivity.this.onClickRecord();
                }

                public void onFailure(int i, String str) {
                    ToastUtil.a((Context) RecordingVoiceActivity.this.activity(), (int) R.string.file_permission_denied);
                }
            }, this.permitArray);
        } else if (PermissionChecker.checkSelfPermission(activity(), "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            ToastUtil.a((Context) activity(), (int) R.string.file_permission_denied);
        } else if (PermissionChecker.checkSelfPermission(activity(), "android.permission.RECORD_AUDIO") != 0) {
            ToastUtil.a((Context) activity(), (int) R.string.audio_permission_denied);
        } else {
            onClickRecord();
        }
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        int i = message.what;
        if (i == 21008) {
            int i2 = message.arg1;
            String str = TAG;
            SDKLog.e(str, "result=" + i2);
            if (i2 == 1) {
                this.rl_voice_tip.setVisibility(0);
                this.ll_record_success.setVisibility(0);
                this.ll_record_short.setVisibility(8);
                this.ll_recording.setVisibility(8);
                this.ll_record_failed.setVisibility(8);
                this.ll_record_loading.setVisibility(8);
                this.myHandler.postDelayed(new Runnable() {
                    public void run() {
                        RecordingVoiceActivity.this.rl_voice_tip.setVisibility(8);
                    }
                }, 1000);
                this.recording_voice.setText(getString(R.string.re_recording));
                this.ll_record_empty.setVisibility(8);
            } else if (i2 == 2) {
                this.rl_voice_tip.setVisibility(0);
                this.ll_record_failed.setVisibility(0);
                this.ll_record_success.setVisibility(8);
                this.ll_record_short.setVisibility(8);
                this.ll_recording.setVisibility(8);
                this.ll_record_loading.setVisibility(8);
                clearRecord();
                this.msgs.clear();
                this.allMsgs.clear();
                refreshUINew();
                this.myHandler.postDelayed(new Runnable() {
                    public void run() {
                        RecordingVoiceActivity.this.rl_voice_tip.setVisibility(8);
                    }
                }, 1000);
            }
        } else if (i == 210014) {
            SDKLog.e(TAG, "获取个性语音试听的状态");
            byte[] bArr = (byte[]) message.obj;
            if (bArr != null) {
                byte b = bArr[0];
                String str2 = TAG;
                SDKLog.e(str2, "获取个性语音试听的状态voiceState=" + b);
                if (b == 1) {
                    this.leaveMsgAdapter.palyRecord(this.bindMsg, this.currentPosition);
                } else if (b == 2) {
                    ToastUtil.a((Context) activity(), (CharSequence) "未找到播放文件");
                } else {
                    ToastUtil.a((Context) activity(), (CharSequence) "正在播放");
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public String removeRepeat(String str) {
        return str.length() > 5 ? str.substring(3) : str;
    }
}
