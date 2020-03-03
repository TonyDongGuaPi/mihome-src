package com.xiaomi.smarthome.miio.activity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeConfig;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.stat.STAT;

public class DeviceCardSettingActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private boolean f11732a;
    /* access modifiers changed from: private */
    public VideoView b;
    /* access modifiers changed from: private */
    public Uri c;
    /* access modifiers changed from: private */
    public Uri d;
    private AudioManager e;
    private AudioManager.OnAudioFocusChangeListener f;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_device_card_setting);
        a();
        c();
    }

    private void a() {
        try {
            this.e = (AudioManager) getSystemService("audio");
            this.f = new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int i) {
                }
            };
            this.e.requestAudioFocus(this.f, 3, 2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void b() {
        try {
            this.e.abandonAudioFocus(this.f);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void c() {
        findViewById(R.id.title_bar).setBackgroundColor(getResources().getColor(R.color.white));
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.device_card_shortcut);
        this.f11732a = SharePrefsManager.b(SHApplication.getAppContext(), SmartHomeConfig.i, SmartHomeConfig.r, false);
        final SwitchButton switchButton = (SwitchButton) findViewById(R.id.device_card_shortcut_btn);
        switchButton.setOnTouchEnable(false);
        switchButton.setChecked(this.f11732a);
        findViewById(R.id.device_card_shortcut).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                switchButton.setChecked(!switchButton.isChecked());
                SharePrefsManager.a(SHApplication.getAppContext(), SmartHomeConfig.i, SmartHomeConfig.r, switchButton.isChecked());
                DeviceCardSettingActivity.this.mHandler.removeCallbacksAndMessages((Object) null);
                DeviceCardSettingActivity.this.b.stopPlayback();
                DeviceCardSettingActivity.this.b.setVideoURI(switchButton.isChecked() ? DeviceCardSettingActivity.this.c : DeviceCardSettingActivity.this.d);
                DeviceCardSettingActivity.this.b.start();
                STAT.d.X(switchButton.isChecked() ? "1" : "2");
            }
        });
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DeviceCardSettingActivity.this.onBackPressed();
            }
        });
        this.b = (VideoView) findViewById(R.id.video_view);
        this.b.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                return true;
            }
        });
        this.b.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
                        if (i != 3) {
                            return true;
                        }
                        DeviceCardSettingActivity.this.b.setBackgroundColor(0);
                        return true;
                    }
                });
            }
        });
        this.b.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                DeviceCardSettingActivity.this.mHandler.removeCallbacksAndMessages((Object) null);
                DeviceCardSettingActivity.this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        if (DeviceCardSettingActivity.this.isValid()) {
                            DeviceCardSettingActivity.this.b.start();
                        }
                    }
                }, 2000);
            }
        });
        this.c = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.enter_card);
        this.d = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.enter_plugin);
        this.b.setVideoURI(this.f11732a ? this.c : this.d);
        this.b.start();
        this.b.requestFocus();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (!this.b.isPlaying()) {
            this.b.start();
            this.b.requestFocus();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.b.pause();
        this.b.clearFocus();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.b.stopPlayback();
        this.mHandler.removeCallbacksAndMessages((Object) null);
        b();
    }

    public void onBackPressed() {
        if (this.f11732a != SharePrefsManager.b(SHApplication.getAppContext(), SmartHomeConfig.i, SmartHomeConfig.r, false)) {
            setResult(-1);
        } else {
            setResult(0);
        }
        finish();
    }
}
