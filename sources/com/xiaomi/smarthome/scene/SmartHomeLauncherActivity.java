package com.xiaomi.smarthome.scene;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.xiaomi.miot.support.monitor.core.activity.ActivityInfo;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.app.startup.StartupCheckList;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.LoginApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.util.CheckStatusHandlerTask;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.notishortcut.SmartNotiApi;
import com.xiaomi.smarthome.scene.api.RemoteSceneApi;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.smarthome.stat.StatApp;

public class SmartHomeLauncherActivity extends BaseActivity {
    private static final String c = "SmartHomeLauncherActivity";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public int f21338a = 0;
    /* access modifiers changed from: private */
    public boolean b = false;
    int mSceneId = -1;
    String mStrSceneId;
    String mUserId = "";

    private void a() {
    }

    static /* synthetic */ int access$308(SmartHomeLauncherActivity smartHomeLauncherActivity) {
        int i = smartHomeLauncherActivity.f21338a;
        smartHomeLauncherActivity.f21338a = i + 1;
        return i;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent().getExtras() != null) {
            this.mSceneId = getIntent().getExtras().getInt("extra_scene_id", -1);
            this.mStrSceneId = getIntent().getExtras().getString(SceneApi.d);
            this.mUserId = getIntent().getExtras().getString(SceneApi.b);
        }
        if (!TextUtils.isEmpty(this.mStrSceneId)) {
            StatApp statApp = STAT.f22748a;
            String str = this.mUserId;
            statApp.b(str, this.mStrSceneId + "");
        } else {
            StatApp statApp2 = STAT.f22748a;
            String str2 = this.mUserId;
            statApp2.b(str2, this.mSceneId + "");
        }
        if (this.mSceneId == -1 && TextUtils.isEmpty(this.mStrSceneId)) {
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
                    SmartHomeLauncherActivity.this.finish();
                }

                public void d() {
                    SmartHomeLauncherActivity.this.finish();
                }

                public void e() {
                    SmartHomeLauncherActivity.this.doWork();
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void doWork() {
        if (CoreApi.a().q()) {
            e();
        } else {
            b();
        }
    }

    private void b() {
        CoreApi.a().a((Context) this, (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
            public void onCoreReady() {
                if (CoreApi.a().q()) {
                    SmartHomeLauncherActivity.this.e();
                } else {
                    SmartHomeLauncherActivity.this.c();
                }
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
        this.b = true;
        LoginApi.a().a((Context) this, 1, (LoginApi.LoginCallback) new LoginApi.LoginCallback() {
            public void a() {
                if (CoreApi.a().q()) {
                    boolean unused = SmartHomeLauncherActivity.this.b = false;
                    SmartHomeLauncherActivity.this.e();
                    return;
                }
                SmartHomeLauncherActivity.this.finish();
            }
        });
    }

    private void d() {
        String str;
        if (!TextUtils.isEmpty(this.mStrSceneId)) {
            str = this.mStrSceneId;
        } else {
            str = this.mSceneId + "";
        }
        SmartNotiApi.a((Context) this).a(this, this.mUserId, str, new SmartNotiApi.CallbackV2() {
            public void b() {
                ToastUtil.a((int) R.string.launcher_scene_loading);
            }

            public void a(String str) {
                SHApplication.getGlobalHandler().post(new Runnable() {
                    public void run() {
                        ToastUtil.a((int) R.string.smarthome_scene_start_complete);
                        SmartHomeLauncherActivity.this.finish();
                    }
                });
            }

            public void a(final int i, String str) {
                SHApplication.getGlobalHandler().post(new Runnable() {
                    public void run() {
                        if (i == -400 || i == 401) {
                            ToastUtil.a((int) R.string.launcher_switch_to_userid);
                            SmartHomeLauncherActivity.this.finish();
                        } else if (i == -100) {
                            ToastUtil.a((int) R.string.network_fake_connected);
                            SmartHomeLauncherActivity.this.finish();
                        } else if (i == -999) {
                            ToastUtil.a((int) R.string.smarthome_scene_start_error);
                            SmartHomeLauncherActivity.this.finish();
                        } else if (i == -1) {
                            ToastUtil.a((int) R.string.scene_expired);
                            SmartHomeLauncherActivity.this.finish();
                        } else {
                            ToastUtil.a((int) R.string.smarthome_scene_start_error);
                            SmartHomeLauncherActivity.this.finish();
                        }
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void e() {
        if (!this.mUserId.equalsIgnoreCase(CoreApi.a().s())) {
            ToastUtil.a((int) R.string.launcher_switch_to_userid);
            finish();
            return;
        }
        ToastUtil.a((int) R.string.launcher_scene_loading);
        final CheckStatusHandlerTask checkStatusHandlerTask = new CheckStatusHandlerTask(false);
        checkStatusHandlerTask.a((CheckStatusHandlerTask.MyRunnable) new CheckStatusHandlerTask.MyRunnable() {
            public void a(Handler handler) {
                RemoteSceneApi.a().b((Context) SmartHomeLauncherActivity.this, SmartHomeLauncherActivity.this.mStrSceneId, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        ToastUtil.a((int) R.string.smarthome_scene_start_complete);
                        SmartHomeLauncherActivity.this.finish();
                    }

                    public void onFailure(Error error) {
                        SmartHomeLauncherActivity.access$308(SmartHomeLauncherActivity.this);
                        if (SmartHomeLauncherActivity.this.f21338a <= 2) {
                            checkStatusHandlerTask.c();
                        } else if (error.a() == -1) {
                            ToastUtil.a((int) R.string.scene_expired);
                            SmartHomeLauncherActivity.this.finish();
                        } else {
                            ToastUtil.a((int) R.string.smarthome_scene_start_error);
                            SmartHomeLauncherActivity.this.finish();
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
        LogUtil.a(c, "onStop");
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a(c, ActivityInfo.TYPE_STR_ONDESTROY);
    }
}
