package com.xiaomi.smarthome.frame.login.ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import com.facebook.CallbackManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.statistic.StatHelper;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.baseui.ToastManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.LoginVideoManager;
import com.xiaomi.smarthome.frame.login.logic.LoginHelper;
import com.xiaomi.smarthome.frame.login.util.LoginEventUtil;
import com.xiaomi.smarthome.frame.login.util.LoginUtil;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.international.ServerApi;
import com.xiaomi.smarthome.library.DarkModeCompat;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.youpin.login.api.LoginErrorCode;
import com.xiaomi.youpin.login.api.manager.callback.FacebookLoginCallback;
import com.xiaomi.youpin.login.api.manager.callback.WxLoginCallback;
import com.xiaomi.youpin.login.api.stat.LoginType;
import com.xiaomi.youpin.login.api.wechat.data.WxTouristLoginData;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import java.util.Map;

public abstract class LoginHomeBaseActivity extends LoginBaseActivity {
    protected static final String TAG = "LoginHomeBaseActivity";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public boolean f16200a = false;
    /* access modifiers changed from: private */
    public boolean b = false;
    private CallbackManager c;
    private int d = 0;
    /* access modifiers changed from: private */
    public AudioManager e;
    private Runnable f = new Runnable() {
        public void run() {
            if (!LoginHomeBaseActivity.this.isFinishing()) {
                if ((Build.VERSION.SDK_INT < 17 || !LoginHomeBaseActivity.this.isDestroyed()) && LoginHomeBaseActivity.this.e != null) {
                    LoginHomeBaseActivity.this.e.requestAudioFocus(LoginHomeBaseActivity.this.i, 3, 2);
                }
            }
        }
    };
    private boolean g;
    /* access modifiers changed from: private */
    public boolean h;
    /* access modifiers changed from: private */
    public AudioManager.OnAudioFocusChangeListener i = new AudioManager.OnAudioFocusChangeListener() {
        public void onAudioFocusChange(int i) {
        }
    };
    private Disposable j;
    protected Intent mIntent;
    protected boolean mIsLoginingFB = false;
    protected ImageView vBack;
    protected TextView vFbLogin;
    protected TextView vLocate;
    protected View vPhoneLogin;
    protected View vPwdLogin;
    protected VideoView vVideo;
    protected View vWxLogin;

    /* access modifiers changed from: private */
    public static /* synthetic */ void d() throws Exception {
    }

    /* access modifiers changed from: protected */
    public abstract void initView();

    /* access modifiers changed from: protected */
    public void init() {
        this.g = DarkModeCompat.a((Context) this);
        this.vPhoneLogin = findViewById(R.id.sh_login_pwd_other_way_phone);
        this.vPwdLogin = findViewById(R.id.sh_login_pwd_other_way_pwd);
        this.vBack = (ImageView) findViewById(R.id.sh_login_pwd_back);
        this.vLocate = (TextView) findViewById(R.id.sh_login_pwd_server);
        this.vWxLogin = findViewById(R.id.sh_login_pwd_other_way_wx);
        this.vFbLogin = (TextView) findViewById(R.id.sh_login_pwd_other_way_fb);
        this.vVideo = (VideoView) findViewById(R.id.sh_login_video);
        if (this.g) {
            this.vVideo.setVisibility(4);
            this.vVideo.setBackground((Drawable) null);
            this.vBack.setColorFilter(-1);
        }
        initView();
        if (!this.g) {
            LoginVideoManager.a().a(this);
        }
        FrameManager.b().g().c();
        this.c = CallbackManager.Factory.create();
        this.mIntent = getIntent();
        if (LoginUtil.a((Context) this, this.mIntent)) {
            finish();
            return;
        }
        this.vBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LoginEventUtil.a(LoginHomeBaseActivity.this.mContext, false);
                LoginHomeBaseActivity.this.finish();
            }
        });
        this.vVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
                        if (i != 3) {
                            return true;
                        }
                        LoginHomeBaseActivity.this.vVideo.setBackgroundColor(0);
                        return true;
                    }
                });
            }
        });
        this.vVideo.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                return true;
            }
        });
        this.vWxLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                StatHelper.c();
                LoginHomeBaseActivity.this.a();
                boolean unused = LoginHomeBaseActivity.this.h = true;
            }
        });
        this.vFbLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LoginHomeBaseActivity.this.b();
            }
        });
        this.vFbLogin.post(new Runnable() {
            public void run() {
                TextView textView = LoginHomeBaseActivity.this.vFbLogin;
                textView.setText("    " + LoginHomeBaseActivity.this.vFbLogin.getText());
            }
        });
        c();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.h && !this.mLoginManager.b() && this.vLoadingDialog.isShowing()) {
            this.vLoadingDialog.dismiss();
        }
        this.h = false;
        this.vWxLogin.setEnabled(true);
        if (CoreApi.a().D()) {
            this.vWxLogin.setVisibility(8);
            this.vFbLogin.setVisibility(0);
            View findViewById = findViewById(R.id.login_phone_and_fb_ll);
            if (findViewById != null) {
                findViewById.setVisibility(0);
            }
        } else {
            this.vWxLogin.setVisibility(0);
            this.vFbLogin.setVisibility(8);
            View findViewById2 = findViewById(R.id.login_phone_and_fb_ll);
            if (findViewById2 != null) {
                findViewById2.setVisibility(8);
            }
        }
        if (this.g || !LoginVideoManager.a().b()) {
            this.vVideo.setVisibility(8);
        } else if ((Build.VERSION.SDK_INT < 24 || !isInMultiWindowMode()) && !this.vVideo.isPlaying()) {
            try {
                this.e = (AudioManager) getSystemService("audio");
                this.vVideo.setVideoURI(LoginVideoManager.a().c());
                this.vVideo.seekTo(this.d);
                this.vVideo.start();
                FrameManager.b().e().removeCallbacks(this.f);
                FrameManager.b().e().post(this.f);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
        if (this.vVideo.isPlaying()) {
            this.vVideo.stopPlayback();
        }
        this.vVideo.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.vVideo.stopPlayback();
        if (this.e != null) {
            this.e.abandonAudioFocus(this.i);
        }
        super.onDestroy();
        if (this.j != null) {
            this.j.dispose();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        LoginEventUtil.a(this.mContext, false);
    }

    /* access modifiers changed from: private */
    public void a() {
        this.f16200a = false;
        this.b = false;
        this.vWxLogin.setEnabled(false);
        this.vLoadingDialog.setCancelable(true);
        this.vLoadingDialog.setMessage(getString(R.string.login_passport_login_waiting));
        if (!isFinishing()) {
            this.vLoadingDialog.show();
        }
        final AnonymousClass9 r0 = new WxLoginCallback() {
            public void a(WxTouristLoginData wxTouristLoginData) {
            }

            public void a(boolean z, boolean z2) {
            }

            public void a(int i) {
                if (!LoginHomeBaseActivity.this.b) {
                    boolean unused = LoginHomeBaseActivity.this.f16200a = true;
                    Log.d(LoginHomeBaseActivity.TAG, "onWxAuthFail " + i);
                    LoginHomeBaseActivity.this.vWxLogin.setEnabled(true);
                    if (LoginHomeBaseActivity.this.vLoadingDialog.isShowing()) {
                        LoginHomeBaseActivity.this.vLoadingDialog.dismiss();
                    }
                    if (i == -7001) {
                        ToastManager.a().a((int) R.string.wx_not_installed);
                    } else {
                        ToastManager.a().a(LoginHelper.a(LoginHomeBaseActivity.this.getString(R.string.wx_errcode_failure), i));
                    }
                    LoginBaseActivity.logLoginFailEvent(LoginType.d, i, "onWxAuthFail", (Map<String, String>) new HashMap());
                }
            }

            public void a() {
                if (!LoginHomeBaseActivity.this.b) {
                    boolean unused = LoginHomeBaseActivity.this.f16200a = true;
                    Log.d(LoginHomeBaseActivity.TAG, "onWxAuthSuccess");
                    LoginHomeBaseActivity.this.vWxLogin.setEnabled(true);
                }
            }

            public void b() {
                if (!LoginHomeBaseActivity.this.b) {
                    boolean unused = LoginHomeBaseActivity.this.f16200a = true;
                    Log.d(LoginHomeBaseActivity.TAG, "onWxLoginCancel");
                    LoginHomeBaseActivity.this.vWxLogin.setEnabled(true);
                    if (LoginHomeBaseActivity.this.vLoadingDialog.isShowing()) {
                        LoginHomeBaseActivity.this.vLoadingDialog.dismiss();
                    }
                    ToastManager.a().a((int) R.string.wx_errcode_cancel);
                    LoginBaseActivity.logLoginFailEvent(LoginType.d, "-1", "onWxLoginCancel", (Map<String, String>) new HashMap());
                }
            }

            public void onLoginSuccess(LoginMiAccount loginMiAccount) {
                if (!LoginHomeBaseActivity.this.b) {
                    boolean unused = LoginHomeBaseActivity.this.f16200a = true;
                    if (LoginHomeBaseActivity.this.vLoadingDialog.isShowing()) {
                        LoginHomeBaseActivity.this.vLoadingDialog.dismiss();
                    }
                    LoginHomeBaseActivity.this.processLoginSuccess(6);
                }
            }

            public void onLoginFail(int i, String str, Map<String, String> map) {
                if (!LoginHomeBaseActivity.this.b && i != -7007) {
                    boolean unused = LoginHomeBaseActivity.this.f16200a = true;
                    if (LoginHomeBaseActivity.this.vLoadingDialog.isShowing()) {
                        LoginHomeBaseActivity.this.vLoadingDialog.dismiss();
                    }
                    ToastManager.a().a(LoginHelper.a(LoginHomeBaseActivity.this.getString(R.string.wx_login_fail), i, str));
                    FrameManager.b().g().b();
                    LoginHomeBaseActivity.this.processLoginFail();
                    LoginBaseActivity.logLoginFailEvent(LoginType.d, i, "onLoginFail", map);
                }
            }
        };
        this.vLoadingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                Log.d(LoginHomeBaseActivity.TAG, "remove wx callback");
                if (!LoginHomeBaseActivity.this.f16200a) {
                    Log.d(LoginHomeBaseActivity.TAG, "user cancel wx dialog");
                    r0.b();
                }
                boolean unused = LoginHomeBaseActivity.this.b = true;
            }
        });
        this.mLoginManager.a((Activity) this, (WxLoginCallback) r0);
    }

    /* access modifiers changed from: private */
    public void b() {
        this.mIsLoginingFB = true;
        final XQProgressDialog xQProgressDialog = new XQProgressDialog(this.mContext);
        xQProgressDialog.setCancelable(false);
        this.mLoginManager.a((Activity) this, this.c, (FacebookLoginCallback) new FacebookLoginCallback() {
            public void a() {
                Log.d(LoginHomeBaseActivity.TAG, "onFbAuthCancel");
                if (xQProgressDialog.isShowing()) {
                    xQProgressDialog.dismiss();
                }
                ToastManager.a().a((int) R.string.wx_errcode_cancel);
                LoginBaseActivity.logLoginFailEvent(LoginType.d, (int) LoginErrorCode.aB, "onFbAuthCancel", (Map<String, String>) new HashMap());
            }

            public void a(int i, String str) {
                Log.d(LoginHomeBaseActivity.TAG, "onFbAuthFail " + i);
                if (xQProgressDialog.isShowing()) {
                    xQProgressDialog.dismiss();
                }
                ToastManager.a().a(LoginHelper.a(LoginHomeBaseActivity.this.getString(R.string.fb_login_fail), i, str));
                LoginBaseActivity.logLoginFailEvent(LoginType.d, i, "onFbAuthFail", (Map<String, String>) new HashMap());
            }

            public void b() {
                Log.d(LoginHomeBaseActivity.TAG, "onFbAuthSuccess");
                xQProgressDialog.setMessage(LoginHomeBaseActivity.this.getString(R.string.login_passport_login_waiting));
                if (!LoginHomeBaseActivity.this.isFinishing()) {
                    xQProgressDialog.show();
                }
            }

            public void onLoginSuccess(LoginMiAccount loginMiAccount) {
                if (xQProgressDialog.isShowing()) {
                    xQProgressDialog.dismiss();
                }
                LoginHomeBaseActivity.this.processLoginSuccess(7);
            }

            public void onLoginFail(int i, String str, Map<String, String> map) {
                if (xQProgressDialog.isShowing()) {
                    xQProgressDialog.dismiss();
                }
                FrameManager.b().m();
                ToastManager.a().a(LoginHelper.a(LoginHomeBaseActivity.this.getString(R.string.fb_login_fail), i, str));
                FrameManager.b().g().b();
                LoginHomeBaseActivity.this.processLoginFail();
                LoginBaseActivity.logLoginFailEvent(LoginType.d, i, str, map);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        if (this.mIsLoginingFB) {
            this.c.onActivityResult(i2, i3, intent);
            this.mIsLoginingFB = false;
        }
    }

    private void c() {
        this.j = ServerApi.a().a(ServerCompact.a(SHApplication.getAppContext())).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            /* renamed from: a */
            public void accept(String str) throws Exception {
                LoginHomeBaseActivity.this.vLocate.setText(str);
                LoginHomeBaseActivity.this.vLocate.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        FrameManager.b().g().b(getClass().getSimpleName());
                    }
                });
            }
        }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE, $$Lambda$LoginHomeBaseActivity$IaxyqmqPSaOPlhd7xa_4J0murSI.INSTANCE);
    }
}
