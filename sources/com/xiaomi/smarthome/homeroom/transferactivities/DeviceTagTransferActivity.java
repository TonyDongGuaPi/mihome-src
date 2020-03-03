package com.xiaomi.smarthome.homeroom.transferactivities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.VideoView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.widget.MaskableTextView;
import com.xiaomi.smarthome.library.common.widget.SingleWaveView;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import java.util.ArrayList;
import java.util.List;

public class DeviceTagTransferActivity extends BaseActivity implements View.OnClickListener {
    public static final String START_TRANSFER_ACTIVITY = "start_transfer_activity";

    /* renamed from: a  reason: collision with root package name */
    private static final String f18318a = "DeviceTagTransferActivity";
    private static final int l = 1;
    /* access modifiers changed from: private */
    public View b;
    /* access modifiers changed from: private */
    public boolean c = false;
    /* access modifiers changed from: private */
    public boolean d = false;
    /* access modifiers changed from: private */
    public boolean e = false;
    /* access modifiers changed from: private */
    public VideoView f;
    /* access modifiers changed from: private */
    public MaskableTextView g;
    /* access modifiers changed from: private */
    public List<Device> h = new ArrayList();
    private SingleWaveView i;
    /* access modifiers changed from: private */
    public AlphaAnimation j;
    private SmartHomeDeviceManager.IClientDeviceListener k = new SmartHomeDeviceManager.IClientDeviceListener() {
        public void a(int i, Device device) {
        }

        public void b(int i) {
        }

        public void a(int i) {
            if (i == 3) {
                DeviceTagTransferActivity.this.m.removeMessages(1);
                DeviceTagTransferActivity.this.m.sendEmptyMessageDelayed(1, 500);
            }
        }
    };
    /* access modifiers changed from: private */
    public Handler m = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 1) {
                Log.d("hzd1", "" + SmartHomeDeviceManager.a().d().size());
            }
        }
    };
    DeviceTagInterface.IDeviceTagListener mIDeviceTagListener = new DeviceTagInterface.IDeviceTagListener() {
        public void a() {
            boolean unused = DeviceTagTransferActivity.this.d = true;
        }
    };
    HomeManager.ITransferCheckListener mITransferCheckListener = new HomeManager.ITransferCheckListener() {
        public void a() {
            if (!DeviceTagTransferActivity.this.d) {
                SmartHomeDeviceHelper.a().b().e();
            }
            boolean unused = DeviceTagTransferActivity.this.e = true;
            if (DeviceTagTransferActivity.this.h.isEmpty()) {
                HomeManager.a().a((ArrayList<String>) new ArrayList());
                boolean unused2 = DeviceTagTransferActivity.this.e = false;
            }
        }
    };
    SmartHomeDeviceManager.IsDeviceReadyCallback mIsDeviceReadyListener = new SmartHomeDeviceManager.IsDeviceReadyCallback() {
        public void onDeviceReady(List<Device> list) {
            boolean unused = DeviceTagTransferActivity.this.c = true;
            if (list != null) {
                for (Device next : list) {
                    if (next.isOwner() && !IRDeviceUtil.a(next.did)) {
                        DeviceTagTransferActivity.this.h.add(next);
                    }
                }
            }
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_device_tag_transfer);
        a();
        SmartHomeDeviceManager.a().a(this.mIsDeviceReadyListener);
        SmartHomeDeviceHelper.a().b().a(this.mIDeviceTagListener);
        HomeManager.a().a(this.mITransferCheckListener);
        SmartHomeDeviceHelper.a().b().a((DeviceTagInterface.IRoomConfigListener) null);
        this.i = (SingleWaveView) findViewById(R.id.single_wave_view);
        this.i.start(DisplayUtils.a(15.0f), DisplayUtils.a(30.0f), Color.parseColor("#52cdbf"));
        SmartHomeDeviceManager.a().a(this.k);
    }

    private void a() {
        TitleBarUtil.b((Activity) this);
        this.b = findViewById(R.id.tv_next);
        this.b.setOnClickListener(this);
        this.b.setVisibility(4);
        this.b.postDelayed(new Runnable() {
            public void run() {
                DeviceTagTransferActivity.this.b.setVisibility(0);
                DeviceTagTransferActivity.this.b.setAnimation(DeviceTagTransferActivity.this.j);
                DeviceTagTransferActivity.this.j.startNow();
            }
        }, 3500);
        this.g = (MaskableTextView) findViewById(R.id.tv_msg);
        this.g.setText(R.string.tag_transfer_msg_light);
        this.g.startAnim(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                DeviceTagTransferActivity.this.g.postDelayed(new Runnable() {
                    public void run() {
                        DeviceTagTransferActivity.this.g.reset();
                        DeviceTagTransferActivity.this.g.setText(R.string.tag_transfer_msg_curtains);
                        DeviceTagTransferActivity.this.g.startAnim((Animation.AnimationListener) null);
                    }
                }, 1000);
            }
        });
        this.j = new AlphaAnimation(0.0f, 1.0f);
        this.j.setDuration(3000);
        this.j.setFillAfter(true);
        this.f = (VideoView) findViewById(R.id.video_view);
        this.f.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                return true;
            }
        });
        this.f.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
                        if (i != 3) {
                            return true;
                        }
                        DeviceTagTransferActivity.this.f.setBackgroundColor(0);
                        return true;
                    }
                });
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    public void onClick(View view) {
        if (view == this.b) {
            if (!this.e || !this.d || !this.c) {
                Intent intent = new Intent();
                intent.setClass(this, SmartHomeMainActivity.class);
                intent.putExtra(START_TRANSFER_ACTIVITY, false);
                intent.addFlags(335544320);
                startActivity(intent);
                overridePendingTransition(0, 0);
            } else {
                startActivity(new Intent(this, HomeRoomAddNewUserRoomActivity.class));
            }
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.i.stop();
        this.j.cancel();
        SmartHomeDeviceHelper.a().b().b(this.mIDeviceTagListener);
        HomeManager.a().b(this.mITransferCheckListener);
        SmartHomeDeviceManager.a().c(this.k);
        this.mIsDeviceReadyListener = null;
        this.mIDeviceTagListener = null;
        this.mITransferCheckListener = null;
        this.f.stopPlayback();
    }
}
