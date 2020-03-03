package com.xiaomi.smarthome.camera.activity.setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mijia.app.Event;
import com.mijia.camera.CameraPlayerEx;
import com.mijia.camera.ICameraPlayerListener;
import com.mijia.debug.SDKLog;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.IStreamCmdMessageListener;
import com.xiaomi.smarthome.camera.activity.CameraPlayerBaseActivity;
import com.xiaomi.smarthome.camera.activity.CommandTreatment;
import com.xiaomi.smarthome.camera.activity.SPUtil;
import com.xiaomi.smarthome.camera.activity.setting.bean.Desc;
import com.xiaomi.smarthome.camera.activity.setting.bean.LeaveMsg;
import com.xiaomi.smarthome.camera.activity.setting.record.LeaveMsgManager;
import com.xiaomi.smarthome.camera.activity.setting.record.LeaveMsgUtil;
import com.xiaomi.smarthome.camera.activity.voice.VoiceManager;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import org.json.JSONObject;

public class VoiceBroadCastActivity extends CameraPlayerBaseActivity implements View.OnClickListener, ICameraPlayerListener, IStreamCmdMessageListener {
    public static final String TAG = "VoiceBroadCastActivity";
    /* access modifiers changed from: private */
    public ArrayList<LeaveMsg> allMsgs = new ArrayList<>();
    /* access modifiers changed from: private */
    public long deviceVoiceId;
    TextView duration_tv;
    private boolean isOpenBroadCast = false;
    /* access modifiers changed from: private */
    public LinearLayout ll_record_empty;
    private VoiceManager mVoiceManager;
    /* access modifiers changed from: private */
    public ArrayList<LeaveMsg> msgs = new ArrayList<>();
    /* access modifiers changed from: private */
    public RelativeLayout rl_play;
    private TextView tv_automatic_broadcast;
    TextView tv_msg_content;
    private TextView tv_record_voice;

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
        setContentView(R.layout.camera_setting_voice_broadcast_activity);
        initView();
        this.mVoiceManager = new VoiceManager(this.mCameraDevice, this);
    }

    private void initView() {
        findViewById(R.id.title_bar_return).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                VoiceBroadCastActivity.this.finish();
            }
        });
        ((TextView) findViewById(R.id.title_bar_title)).setText(R.string.setting_voice_broadcast);
        this.tv_record_voice = (TextView) findViewById(R.id.tv_record_voice);
        this.tv_record_voice.setOnClickListener(this);
        this.ll_record_empty = (LinearLayout) findViewById(R.id.ll_record_empty);
        this.tv_msg_content = (TextView) findViewById(R.id.tv_msg_content);
        this.duration_tv = (TextView) findViewById(R.id.duration_tv);
        this.rl_play = (RelativeLayout) findViewById(R.id.rl_play);
        this.rl_play.setOnClickListener(this);
        this.tv_automatic_broadcast = (TextView) findViewById(R.id.tv_automatic_broadcast);
        this.tv_automatic_broadcast.setOnClickListener(this);
        this.ll_record_empty.setVisibility(0);
        this.rl_play.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void initData() {
        LeaveMsgManager.getInstance(activity()).getData(this.mCameraDevice, 0, 10, new Callback<Object>() {
            public void onSuccess(Object obj) {
                long j;
                VoiceBroadCastActivity.this.allMsgs.clear();
                VoiceBroadCastActivity.this.msgs.clear();
                VoiceBroadCastActivity.this.msgs.addAll(LeaveMsgManager.getInstance(VoiceBroadCastActivity.this.activity()).getAllMsgs());
                String str = VoiceBroadCastActivity.TAG;
                SDKLog.e(str, "获取录音数据成功msgs.size()=" + VoiceBroadCastActivity.this.msgs.size());
                if (VoiceBroadCastActivity.this.msgs.size() > 0) {
                    VoiceBroadCastActivity.this.rl_play.setVisibility(0);
                    VoiceBroadCastActivity.this.ll_record_empty.setVisibility(8);
                } else {
                    VoiceBroadCastActivity.this.rl_play.setVisibility(8);
                    VoiceBroadCastActivity.this.ll_record_empty.setVisibility(0);
                }
                Iterator it = VoiceBroadCastActivity.this.msgs.iterator();
                while (it.hasNext()) {
                    LeaveMsg leaveMsg = (LeaveMsg) it.next();
                    if (!VoiceBroadCastActivity.this.containsByItemId(leaveMsg.itemId)) {
                        SDKLog.e(VoiceBroadCastActivity.TAG, "云上的个性语音Id和固件上的语音ID不一致，删除");
                        VoiceBroadCastActivity.this.clearRecord(leaveMsg);
                    } else {
                        VoiceBroadCastActivity.this.allMsgs.add(leaveMsg);
                    }
                }
                String str2 = VoiceBroadCastActivity.TAG;
                SDKLog.e(str2, "获取录音数据成功allMsgs.size()=" + VoiceBroadCastActivity.this.allMsgs.size());
                if (VoiceBroadCastActivity.this.allMsgs == null || VoiceBroadCastActivity.this.allMsgs.size() <= 0) {
                    VoiceBroadCastActivity.this.ll_record_empty.setVisibility(0);
                    VoiceBroadCastActivity.this.rl_play.setVisibility(8);
                    return;
                }
                LeaveMsg leaveMsg2 = (LeaveMsg) VoiceBroadCastActivity.this.allMsgs.get(0);
                Desc desc = leaveMsg2.descObj;
                VoiceBroadCastActivity.this.tv_msg_content.setText(desc == null ? leaveMsg2.desc : desc.name);
                if (desc == null) {
                    j = 0;
                } else {
                    j = desc.duration / 1000;
                }
                if (j < 10) {
                    VoiceBroadCastActivity.this.duration_tv.setText(String.format(Locale.getDefault(), "00:0%d", new Object[]{Long.valueOf(j)}));
                } else {
                    VoiceBroadCastActivity.this.duration_tv.setText(String.format(Locale.getDefault(), "00:%d", new Object[]{Long.valueOf(j)}));
                }
                VoiceBroadCastActivity.this.ll_record_empty.setVisibility(8);
                VoiceBroadCastActivity.this.rl_play.setVisibility(0);
            }

            public void onFailure(int i, String str) {
                VoiceBroadCastActivity.this.ll_record_empty.setVisibility(0);
                VoiceBroadCastActivity.this.rl_play.setVisibility(8);
            }
        });
        this.mVoiceManager.getVoiceState(this);
    }

    /* access modifiers changed from: private */
    public boolean containsByItemId(long j) {
        String str = TAG;
        SDKLog.e(str, j + "=" + this.deviceVoiceId);
        return this.deviceVoiceId == j;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.rl_play) {
            Intent intent = new Intent(this, RecordingVoiceActivity.class);
            intent.putExtra(RecordingVoiceActivity.SETTING_KEY, RecordingVoiceActivity.AUTO_PLAY_RECORD);
            startActivity(intent);
        } else if (id != R.id.tv_automatic_broadcast) {
            if (id == R.id.tv_record_voice) {
                Intent intent2 = new Intent(this, RecordingVoiceActivity.class);
                intent2.putExtra(RecordingVoiceActivity.SETTING_KEY, RecordingVoiceActivity.MANUAL_PLAY_RECORD);
                startActivity(intent2);
            }
        } else if (this.allMsgs == null || this.allMsgs.size() <= 0) {
            ToastUtil.a((Context) activity(), (CharSequence) getString(R.string.no_broacast_content));
        } else {
            int i = 1;
            this.isOpenBroadCast = !this.isOpenBroadCast;
            if (this.isOpenBroadCast) {
                ToastUtil.a((Context) activity(), (CharSequence) getString(R.string.automatic_announcement_turned_on));
                this.tv_automatic_broadcast.setBackgroundResource(R.drawable.voice_broadcast_bg);
                this.tv_automatic_broadcast.setText(getString(R.string.close_automatic_announcement));
                if (Build.VERSION.SDK_INT >= 23) {
                    this.tv_automatic_broadcast.setTextColor(getColor(R.color.white));
                }
                SDKLog.e(TAG, "开启语音播报");
                this.mVoiceManager.setVoiceState(1, 1, this);
            } else {
                ToastUtil.a((Context) activity(), (CharSequence) getString(R.string.automatic_announcement_turned_off));
                this.tv_automatic_broadcast.setBackgroundResource(R.drawable.voice_broadcast_bg);
                this.tv_automatic_broadcast.setText(getString(R.string.open_automatic_announcement));
                if (Build.VERSION.SDK_INT >= 23) {
                    this.tv_automatic_broadcast.setTextColor(getColor(R.color.white));
                }
                SDKLog.e(TAG, "关闭语音播报");
                this.mVoiceManager.setVoiceState(2, 1, this);
            }
            String str = Event.bx;
            if (!this.isOpenBroadCast) {
                i = 2;
            }
            Event.a(str, "type", (Object) String.valueOf(i));
        }
    }

    public void onResume() {
        super.onResume();
        SDKLog.e(TAG, "onResume....");
        getCustomVoiceInfo();
        this.mCameraPlayerEx = new CameraPlayerEx(this.mCameraDevice, this);
    }

    public void onPause() {
        super.onPause();
        this.mCameraPlayerEx.w();
        this.mCameraPlayerEx = null;
    }

    public void onServerCmd(int i, byte[] bArr) {
        String str = TAG;
        SDKLog.e(str, "延时摄影详情页 CommandTreatment onServerCmd type=" + i);
        new CommandTreatment(this.mHandler, this.mCameraDevice).processData(bArr);
    }

    public void onConnected() {
        super.onConnected();
        SDKLog.e(TAG, "摄像头连接成功，授权+校准时间");
        this.mCameraPlayerEx.A();
        this.mCameraPlayerEx.z();
    }

    public void handleMessage(Message message) {
        byte[] bArr;
        super.handleMessage(message);
        int i = message.what;
        if (i == 21009) {
            byte[] bArr2 = (byte[]) message.obj;
            if (bArr2 != null && bArr2.length >= 3) {
                byte b = bArr2[0];
                byte b2 = bArr2[1];
                byte b3 = bArr2[2];
                if (b2 == 1) {
                    this.isOpenBroadCast = true;
                    refreshVoiceUI(true);
                } else {
                    this.isOpenBroadCast = false;
                    refreshVoiceUI(false);
                }
                String str = TAG;
                SDKLog.e(str, "获取个性语音播报的状态 fileExist=" + b + ",isOpen=" + b2 + ",playScence=" + b3);
            }
        } else if (i == 210010 && (bArr = (byte[]) message.obj) != null && bArr.length > 0) {
            byte b4 = bArr[0];
            String str2 = TAG;
            SDKLog.e(str2, "设置个性语音播报的状态 result=" + b4);
        }
    }

    private void refreshVoiceUI(boolean z) {
        if (z) {
            this.tv_automatic_broadcast.setBackgroundResource(R.drawable.voice_broadcast_bg);
            this.tv_automatic_broadcast.setText(getString(R.string.close_automatic_announcement));
            if (Build.VERSION.SDK_INT >= 23) {
                this.tv_automatic_broadcast.setTextColor(getColor(R.color.white));
                return;
            }
            return;
        }
        this.tv_automatic_broadcast.setBackgroundResource(R.drawable.voice_broadcast_bg);
        this.tv_automatic_broadcast.setText(getString(R.string.open_automatic_announcement));
        if (Build.VERSION.SDK_INT >= 23) {
            this.tv_automatic_broadcast.setTextColor(getColor(R.color.white));
        }
    }

    private void getCustomVoiceInfo() {
        if (this.mCameraDevice != null && this.mCameraDevice.f() != null) {
            this.mCameraDevice.f().b((Callback<JSONObject>) new Callback<JSONObject>() {
                public void onFailure(int i, String str) {
                }

                public void onSuccess(JSONObject jSONObject) {
                    try {
                        String str = VoiceBroadCastActivity.TAG;
                        SDKLog.e(str, "jsonObject==" + jSONObject.toString());
                        String optString = jSONObject.optString("fileId");
                        String str2 = VoiceBroadCastActivity.TAG;
                        SDKLog.e(str2, "fileId==" + optString);
                        if (!TextUtils.isEmpty(optString)) {
                            long unused = VoiceBroadCastActivity.this.deviceVoiceId = Long.parseLong(optString);
                        }
                        String str3 = VoiceBroadCastActivity.TAG;
                        SDKLog.e(str3, "long fileId==" + optString);
                        VoiceBroadCastActivity.this.initData();
                    } catch (Exception unused2) {
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void clearRecord(final LeaveMsg leaveMsg) {
        LeaveMsgManager.getInstance(activity()).removeItem(this.mCameraDevice, leaveMsg.itemId, new Callback<Object>() {
            public void onSuccess(Object obj) {
                VoiceBroadCastActivity.this.msgs.remove(leaveMsg);
                VoiceBroadCastActivity.this.removeByItemId(leaveMsg.itemId);
                String audioFilePath = LeaveMsgUtil.getAudioFilePath(VoiceBroadCastActivity.this.mCameraDevice.getDid(), leaveMsg.itemId);
                SDKLog.e(VoiceBroadCastActivity.TAG, "clearRecord fileName =" + audioFilePath);
                File file = new File(audioFilePath);
                if (file.exists()) {
                    SDKLog.e(VoiceBroadCastActivity.TAG, "删除了 录音文件");
                    file.delete();
                    SPUtil.getInstance(VoiceBroadCastActivity.this.activity(), VoiceBroadCastActivity.this.mCameraDevice.getDid()).remove(SPUtil.KEY_LEAVE_MSGS);
                    VoiceBroadCastActivity.this.msgs.clear();
                }
            }

            public void onFailure(int i, String str) {
                String str2 = VoiceBroadCastActivity.TAG;
                SDKLog.e(str2, "删除 onFailure i=" + i + ",s=" + str);
            }
        });
    }

    /* access modifiers changed from: private */
    public void removeByItemId(long j) {
        for (int size = this.allMsgs.size() - 1; size >= 0; size--) {
            LeaveMsg leaveMsg = this.allMsgs.get(size);
            if (leaveMsg.itemId == j) {
                this.allMsgs.remove(leaveMsg);
                return;
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onSendCmdError() {
        SDKLog.e(TAG, "onSendCmdError");
        ToastUtil.a((Context) activity(), (CharSequence) getString(R.string.operation_failed));
    }
}
