package com.xiaomi.smarthome.voice;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import com.google.android.exoplayer2.C;
import com.xiaomi.miot.support.monitor.core.activity.ActivityInfo;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.app.startup.StartupCheckList;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.config.AndroidCommonConfigManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.LoginApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.util.CheckStatusHandlerTask;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.mibrain.activity.MiBrainActivityNew;
import com.xiaomi.smarthome.scene.api.RemoteSceneApi;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.voiceassistant.mijava.NLProcessor;

public class SmartHomeVoiceLauncherActivity extends BaseActivity {
    private static final String d = "SmartHomeVoiceLauncherActivity";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public int f22782a = 0;
    /* access modifiers changed from: private */
    public boolean b = false;
    private int c = 1212;
    MLAlertDialog dialog;
    private boolean e = false;
    ImageView imageView;
    String mSceneId = null;
    String mUserId = "";

    private void a() {
    }

    static /* synthetic */ int access$308(SmartHomeVoiceLauncherActivity smartHomeVoiceLauncherActivity) {
        int i = smartHomeVoiceLauncherActivity.f22782a;
        smartHomeVoiceLauncherActivity.f22782a = i + 1;
        return i;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_smarthome_voice_launch);
        this.imageView = (ImageView) findViewById(R.id.loading);
        ((AnimationDrawable) this.imageView.getDrawable()).start();
        if (getIntent().getExtras() != null) {
            this.mUserId = getIntent().getExtras().getString(SceneApi.b);
        }
        STAT.f22748a.c(this.mUserId);
        HomeManager.a();
        if (HomeManager.A()) {
            ToastUtil.a((int) R.string.voice_not_support_internation);
            finish();
        } else if (!NetworkUtils.c()) {
            ToastUtil.a((int) R.string.status_error_cable_not_plugin_body);
            finish();
        } else {
            StartupCheckList.a((StartupCheckList.CheckListCallback) new StartupCheckList.CheckListCallback() {
                public void a() {
                }

                public void c() {
                }

                public void b() {
                    SmartHomeVoiceLauncherActivity.this.finish();
                }

                public void d() {
                    SmartHomeVoiceLauncherActivity.this.finish();
                }

                public void e() {
                    SmartHomeVoiceLauncherActivity.this.doWork();
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void doWork() {
        CoreApi.a().a((Context) this, (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
            public void onCoreReady() {
                if (CoreApi.a().q()) {
                    SmartHomeVoiceLauncherActivity.this.c();
                    return;
                }
                SmartHomeVoiceLauncherActivity.this.dialog = new MLAlertDialog.Builder(SmartHomeVoiceLauncherActivity.this).b((int) R.string.go_to_login_mes).a((int) R.string.btn_login_text, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SmartHomeVoiceLauncherActivity.this.b();
                        SmartHomeVoiceLauncherActivity.this.dialog.dismiss();
                    }
                }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SmartHomeVoiceLauncherActivity.this.dialog.dismiss();
                        SmartHomeVoiceLauncherActivity.this.finish();
                    }
                }).b();
                SmartHomeVoiceLauncherActivity.this.dialog.show();
            }
        });
    }

    /* access modifiers changed from: private */
    public void b() {
        CoreApi.a().a((Context) this, (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
            public void onCoreReady() {
                if (CoreApi.a().q()) {
                    SmartHomeVoiceLauncherActivity.this.c();
                    return;
                }
                boolean unused = SmartHomeVoiceLauncherActivity.this.b = true;
                LoginApi.a().a((Context) SmartHomeVoiceLauncherActivity.this, 1, (LoginApi.LoginCallback) new LoginApi.LoginCallback() {
                    public void a() {
                        if (CoreApi.a().q()) {
                            boolean unused = SmartHomeVoiceLauncherActivity.this.b = false;
                            SmartHomeVoiceLauncherActivity.this.c();
                            return;
                        }
                        SmartHomeVoiceLauncherActivity.this.finish();
                    }
                });
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.b) {
            finish();
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        if (AndroidCommonConfigManager.a().b() == null || AndroidCommonConfigManager.a().b().size() == 0 || AndroidCommonConfigManager.a().d() == null || AndroidCommonConfigManager.a().d().size() == 0) {
            AndroidCommonConfigManager.a().i();
        }
        VoiceManager.a().b(new AsyncCallback() {
            public void onSuccess(Object obj) {
                LogUtil.a(SmartHomeVoiceLauncherActivity.d, "getVoiceSettingFromServer onSuccess" + obj.toString());
                if (CoreApi.a().q()) {
                    LogUtil.a(SmartHomeVoiceLauncherActivity.d, "lauchScene" + VoiceManager.a().c());
                    Context appContext = SHApplication.getAppContext();
                    NLProcessor.b = "";
                    Intent intent = new Intent(appContext, MiBrainActivityNew.class);
                    intent.putExtra("from", "shortcut");
                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                    appContext.startActivity(intent);
                    SmartHomeVoiceLauncherActivity.this.overridePendingTransition(0, 0);
                    SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                        public void run() {
                            SmartHomeVoiceLauncherActivity.this.finish();
                        }
                    }, 100);
                    return;
                }
                SmartHomeVoiceLauncherActivity.this.b();
            }

            public void onFailure(Error error) {
                LogUtil.a(SmartHomeVoiceLauncherActivity.d, "getVoiceSettingFromServer onFailure" + error.toString());
                SmartHomeVoiceLauncherActivity.this.finish();
            }
        });
    }

    private void d() {
        if (!this.mUserId.equalsIgnoreCase(CoreApi.a().s())) {
            ToastUtil.a((int) R.string.launcher_switch_to_userid);
            finish();
            return;
        }
        ToastUtil.a((int) R.string.launcher_scene_loading);
        final CheckStatusHandlerTask checkStatusHandlerTask = new CheckStatusHandlerTask(false);
        checkStatusHandlerTask.a((CheckStatusHandlerTask.MyRunnable) new CheckStatusHandlerTask.MyRunnable() {
            public void a(Handler handler) {
                RemoteSceneApi.a().b((Context) SmartHomeVoiceLauncherActivity.this, SmartHomeVoiceLauncherActivity.this.mSceneId, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        ToastUtil.a((int) R.string.smarthome_scene_start_complete);
                        SmartHomeVoiceLauncherActivity.this.finish();
                    }

                    public void onFailure(Error error) {
                        SmartHomeVoiceLauncherActivity.access$308(SmartHomeVoiceLauncherActivity.this);
                        if (SmartHomeVoiceLauncherActivity.this.f22782a <= 2) {
                            checkStatusHandlerTask.c();
                        } else if (error.a() == -1) {
                            ToastUtil.a((int) R.string.scene_expired);
                            SmartHomeVoiceLauncherActivity.this.finish();
                        } else {
                            ToastUtil.a((int) R.string.smarthome_scene_start_error);
                            SmartHomeVoiceLauncherActivity.this.finish();
                        }
                    }
                });
            }
        }, 300);
        checkStatusHandlerTask.a();
    }

    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
        LogUtil.a(d, "onStop");
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a(d, ActivityInfo.TYPE_STR_ONDESTROY);
    }
}
