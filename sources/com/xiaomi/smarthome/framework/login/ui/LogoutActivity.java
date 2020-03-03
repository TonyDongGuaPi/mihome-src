package com.xiaomi.smarthome.framework.login.ui;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.accountsdk.account.XMPassport;
import com.xiaomi.accountsdk.account.data.Gender;
import com.xiaomi.passport.data.XMPassportInfo;
import com.xiaomi.passport.snscorelib.SNSManager;
import com.xiaomi.passport.snscorelib.internal.utils.SNSType;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.account.AccountType;
import com.xiaomi.smarthome.core.entity.statistic.StatHelper;
import com.xiaomi.smarthome.device.utils.ClientRemarkInputView;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.baseui.ToastManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.LoginApiNew;
import com.xiaomi.smarthome.frame.login.MjLoginRouter;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.PluginRuntimeManager;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.api.MiuiVipService;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.framework.login.logic.LoginManager;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.SimpleListDialog;
import com.xiaomi.smarthome.library.common.dialog.WheelDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.imagecache.CircleAvatarProcessor;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import com.xiaomi.smarthome.library.common.util.ImageUtils;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import com.xiaomi.smarthome.library.common.widget.ListItemView;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.library.common.widget.crop.PhotoController;
import com.xiaomi.smarthome.miio.db.record.ShareUserRecord;
import com.xiaomi.smarthome.miio.user.UserMamanger;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import com.xiaomi.youpin.login.ui.web.LoginH5HomeAcvtivity;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import miui.vip.QueryCallback;
import miui.vip.VipAchievement;
import miui.vip.VipUserInfo;

public class LogoutActivity extends BaseActivity {
    public static final int BIND_FB_RESULT = 103;
    public static final int BIND_WX_RESULT = 102;

    /* renamed from: a  reason: collision with root package name */
    private static final int f16581a = 12;
    private static final int b = 23;
    private static final int c = 101;
    private final DialogInterface.OnClickListener A = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialogInterface, int i) {
            if (!LogoutActivity.this.mIsLogouting) {
                LogoutActivity.this.mIsLogouting = true;
                LogoutActivity.this.c(true);
                LogoutActivity.this.x();
                StatHelper.d();
            }
        }
    };
    private boolean B = false;
    /* access modifiers changed from: private */
    public SimpleListDialog C;
    /* access modifiers changed from: private */
    public WheelDialog D;
    private PhotoController E;
    /* access modifiers changed from: private */
    public ShareUserRecord F;
    /* access modifiers changed from: private */
    public String G = "";
    /* access modifiers changed from: private */
    public String H;
    private final PhotoController.IPhotoController I = new PhotoController.IPhotoController() {
        public void a(String str) {
            File file = new File(str);
            if (file.isFile() && file.exists()) {
                LogoutActivity.this.c(str);
            }
        }
    };
    private Context J;
    private XQProgressDialog K;
    private boolean L = false;
    private long M;
    private QueryCallback N;
    /* access modifiers changed from: private */
    public VipUserInfo O;
    /* access modifiers changed from: private */
    public List<VipAchievement> P;
    private BroadcastReceiver Q;
    private TextView d;
    private TextView e;
    private ImageView f;
    /* access modifiers changed from: private */
    public UserInfoContainer g;
    /* access modifiers changed from: private */
    public UserInfoContainer h;
    /* access modifiers changed from: private */
    public UserInfoContainer i;
    /* access modifiers changed from: private */
    public UserInfoContainer j;
    /* access modifiers changed from: private */
    public UserInfoContainer k;
    /* access modifiers changed from: private */
    public UserInfoContainer l;
    AsyncHandle logoutRequestHandle;
    private UserInfoContainer m;
    boolean mIsLogouting = false;
    Random mRandom = new Random();
    private UserInfoContainer n;
    private UserInfoContainer o;
    private UserInfoContainer p;
    private UserInfoContainer q;
    private UserInfoContainer r;
    private UserInfoContainer s;
    private UserInfoContainer t;
    /* access modifiers changed from: private */
    public SwitchButton u;
    /* access modifiers changed from: private */
    public SwitchButton v;
    private MLAlertDialog w;
    private MLAlertDialog x;
    private SimpleListDialog y;
    private XQProgressDialog z;

    private static String a(String str, int i2, int i3) {
        if (TextUtils.isEmpty(str) || str.length() <= i2 + i3) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (int i4 = 0; i4 < str.length(); i4++) {
            if (i4 < i2 || i4 >= str.length() - i3) {
                sb.append(str.charAt(i4));
            } else {
                sb.append("*");
            }
        }
        return sb.toString();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.J = this;
        b();
        k();
        this.E = new PhotoController(this, this.I);
        this.M = this.mRandom.nextLong();
        a();
    }

    private void a() {
        this.Q = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                LogoutActivity.this.c(true);
                LogoutActivity.this.x();
                ToastManager.a().a((int) R.string.mi_validate_relogin);
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(LoginH5HomeAcvtivity.NEED_RELOGIN_EVENT);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.Q, intentFilter);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.L) {
            this.L = false;
            if (this.K != null && this.K.isShowing()) {
                this.K.dismiss();
                a(false);
            }
        }
    }

    private void b() {
        setContentView(R.layout.logout_account_activity);
        this.o = new UserInfoContainer(findViewById(R.id.head_profile_category), 0);
        this.o.a((int) R.string.account_basic_data);
        this.g = new UserInfoContainer(findViewById(R.id.head_container), 1);
        this.g.a((int) R.string.label_head);
        this.g.d(false);
        this.g.c(true);
        this.g.a((CharSequence) getString(R.string.label_head));
        this.k = new UserInfoContainer(findViewById(R.id.uid_container), 2);
        this.k.a((int) R.string.label_account_id);
        this.k.b(false);
        this.k.a();
        this.h = new UserInfoContainer(findViewById(R.id.name_container), 3);
        this.h.a(true);
        this.h.a((int) R.string.label_name);
        this.h.a();
        if (!CoreApi.a().D()) {
            this.p = new UserInfoContainer(findViewById(R.id.account_binding_category), 4);
            this.p.a((int) R.string.account_binding);
            this.q = new UserInfoContainer(findViewById(R.id.wx_account_binding_container), 5);
            this.q.a(true);
            this.q.a((int) R.string.account_wechat);
            this.q.a();
            this.u = (SwitchButton) this.q.d((int) R.id.binding_switch);
            findViewById(R.id.fb_account_binding_container).setVisibility(8);
            if (CoreApi.a().o() != AccountType.MI) {
                this.q.c(8);
                this.p.c(8);
            }
            a(false);
            h();
        } else {
            this.p = new UserInfoContainer(findViewById(R.id.account_binding_category), 4);
            this.p.a((int) R.string.account_binding);
            findViewById(R.id.wx_account_binding_container).setVisibility(8);
            this.r = new UserInfoContainer(findViewById(R.id.fb_account_binding_container), 5);
            this.r.a(true);
            this.r.a((int) R.string.account_fb);
            this.r.a();
            this.v = (SwitchButton) this.r.d((int) R.id.binding_switch);
            b(false);
            i();
        }
        this.s = new UserInfoContainer(findViewById(R.id.account_security_category), 6);
        this.s.a((int) R.string.account_security);
        this.i = new UserInfoContainer(findViewById(R.id.phone_container), 7);
        this.i.a((int) R.string.label_phone);
        this.i.b(false);
        this.i.a();
        this.j = new UserInfoContainer(findViewById(R.id.email_container), 8);
        this.j.a((int) R.string.label_email);
        this.j.b(false);
        this.j.a();
        this.l = new UserInfoContainer(findViewById(R.id.pwd_container), 9);
        this.l.a(true);
        this.l.a((int) R.string.label_account_security);
        this.l.a();
        this.t = new UserInfoContainer(findViewById(R.id.account_vip_category), 10);
        this.m = new UserInfoContainer(findViewById(R.id.vip_level_container), 11);
        this.n = new UserInfoContainer(findViewById(R.id.vip_achievement_container), 12);
        findViewById(R.id.account_vip_category).setVisibility(8);
        findViewById(R.id.vip_level_container).setVisibility(8);
        findViewById(R.id.vip_achievement_container).setVisibility(8);
        this.d = (TextView) findViewById(R.id.module_a_3_return_title);
        this.d.setText(R.string.title_person_info);
        this.e = (TextView) findViewById(R.id.login_system_account_login_button);
        this.e.setBackgroundResource(R.drawable.common_button);
        this.e.setText(R.string.miio_setting_logout);
        this.f = (ImageView) findViewById(R.id.module_a_3_return_btn);
        j();
    }

    private void c() {
        if (this.N == null) {
            this.N = new QueryCallback(new int[0]) {
                public void onConnected(boolean z, final VipUserInfo vipUserInfo, final List<VipAchievement> list) {
                    if (LogoutActivity.this.isValid() && z && vipUserInfo != null && list != null) {
                        LogoutActivity.this.mHandler.post(new Runnable() {
                            public void run() {
                                LogoutActivity logoutActivity;
                                if (LogoutActivity.this.isValid() && (logoutActivity = LogoutActivity.this) != null && !logoutActivity.isFinishing()) {
                                    VipUserInfo unused = LogoutActivity.this.O = vipUserInfo;
                                    List unused2 = LogoutActivity.this.P = list;
                                    LogoutActivity.this.e();
                                }
                            }
                        });
                    }
                }

                public void onUserInfo(int i, final VipUserInfo vipUserInfo, String str) {
                    if (LogoutActivity.this.isValid() && i == 0 && vipUserInfo != null) {
                        LogoutActivity.this.mHandler.post(new Runnable() {
                            public void run() {
                                LogoutActivity logoutActivity;
                                if (LogoutActivity.this.isValid() && (logoutActivity = LogoutActivity.this) != null && !logoutActivity.isFinishing()) {
                                    VipUserInfo unused = LogoutActivity.this.O = vipUserInfo;
                                    LogoutActivity.this.f();
                                }
                            }
                        });
                    }
                }

                public void onAchievements(int i, final List<VipAchievement> list, String str) {
                    if (LogoutActivity.this.isValid() && i == 0 && list != null) {
                        LogoutActivity.this.mHandler.post(new Runnable() {
                            public void run() {
                                LogoutActivity logoutActivity;
                                if (LogoutActivity.this.isValid() && (logoutActivity = LogoutActivity.this) != null && !logoutActivity.isFinishing()) {
                                    List unused = LogoutActivity.this.P = list;
                                    LogoutActivity.this.g();
                                }
                            }
                        });
                    }
                }
            };
        }
    }

    private void d() {
        if (MiuiVipService.a() != null) {
            MiuiVipService.a().a(this.N);
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        f();
        g();
    }

    /* access modifiers changed from: private */
    public void f() {
        this.t.c(8);
        this.m.c(8);
        this.t.c(8);
    }

    /* access modifiers changed from: private */
    public void g() {
        ImageView[] imageViewArr = {(SimpleDraweeView) findViewById(R.id.usr_achievement_icon_1), (SimpleDraweeView) findViewById(R.id.usr_achievement_icon_2), (SimpleDraweeView) findViewById(R.id.usr_achievement_icon_3), (SimpleDraweeView) findViewById(R.id.usr_achievement_icon_4)};
        ImageView[] imageViewArr2 = {(SimpleDraweeView) findViewById(R.id.usr_achievement_icon_1_lock), (SimpleDraweeView) findViewById(R.id.usr_achievement_icon_2_lock), (SimpleDraweeView) findViewById(R.id.usr_achievement_icon_3_lock), (SimpleDraweeView) findViewById(R.id.usr_achievement_icon_4_lock)};
        if (this.P != null) {
            int i2 = 0;
            while (i2 < imageViewArr.length && i2 < this.P.size()) {
                VipAchievement vipAchievement = this.P.get(i2);
                if (!(vipAchievement == null || imageViewArr[i2] == null)) {
                    if (!TextUtils.isEmpty(vipAchievement.url)) {
                        imageViewArr[i2].setImageURI(Uri.parse(vipAchievement.url));
                    }
                    imageViewArr2[i2].setVisibility(vipAchievement.isOwned ? 8 : 0);
                    if (!vipAchievement.isOwned) {
                        imageViewArr2[i2].setImageURI(Uri.parse(MiuiVipService.f));
                        if (imageViewArr2[i2].getVisibility() != 0) {
                            imageViewArr2[i2].setVisibility(0);
                        }
                    } else {
                        imageViewArr2[i2].setVisibility(8);
                    }
                }
                i2++;
            }
            return;
        }
        this.n.c(8);
    }

    private void h() {
        LoginApiNew.a().a(new AsyncCallback<Boolean, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(Boolean bool) {
                LogoutActivity.this.a(bool.booleanValue());
            }
        });
    }

    private void i() {
        LoginApiNew.a().c(new AsyncCallback<Boolean, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(Boolean bool) {
                LogoutActivity.this.b(bool.booleanValue());
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(boolean z2) {
        if (z2) {
            this.u.setChecked(true);
            this.q.b((int) R.string.account_bound);
            return;
        }
        this.u.setChecked(false);
        this.q.b((int) R.string.account_unbound);
    }

    /* access modifiers changed from: private */
    public void b(boolean z2) {
        if (z2) {
            this.v.setChecked(true);
            this.r.b((int) R.string.account_bound);
            return;
        }
        this.v.setChecked(false);
        this.r.b((int) R.string.account_unbound);
    }

    /* access modifiers changed from: private */
    public String a(String str) {
        return a(str, 3, 2);
    }

    /* access modifiers changed from: private */
    public String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        Matcher matcher = Pattern.compile("([\\S]*)@([\\S]*).com").matcher(str);
        if (!matcher.find() || matcher.groupCount() != 2) {
            return str;
        }
        return String.format("%s@%s.com", new Object[]{a(matcher.group(1), 2, 1), a(matcher.group(2), 1, 0)});
    }

    private static class MyAsyncResponseCallback extends AsyncResponseCallback<ShareUserRecord> {

        /* renamed from: a  reason: collision with root package name */
        private final WeakReference<LogoutActivity> f16626a;

        public MyAsyncResponseCallback(LogoutActivity logoutActivity) {
            this.f16626a = new WeakReference<>(logoutActivity);
        }

        public void a(ShareUserRecord shareUserRecord) {
            LogoutActivity logoutActivity = (LogoutActivity) this.f16626a.get();
            if (logoutActivity != null && logoutActivity.isValid()) {
                ShareUserRecord unused = logoutActivity.F = shareUserRecord;
                String unused2 = logoutActivity.G = XMStringUtils.c(shareUserRecord.nickName);
                logoutActivity.h.b(logoutActivity.G);
                logoutActivity.k.b(shareUserRecord.userId);
                UserMamanger.a().b(shareUserRecord.url, logoutActivity.g.b(), new CircleAvatarProcessor());
                if (!TextUtils.isEmpty(shareUserRecord.phone)) {
                    logoutActivity.i.b(logoutActivity.a(shareUserRecord.phone));
                    logoutActivity.i.c(0);
                } else {
                    logoutActivity.i.c(8);
                }
                if (!TextUtils.isEmpty(shareUserRecord.email)) {
                    logoutActivity.j.b(logoutActivity.b(shareUserRecord.email));
                    logoutActivity.j.c(0);
                } else {
                    logoutActivity.j.c(8);
                }
                logoutActivity.l.d(false);
                String unused3 = logoutActivity.H = shareUserRecord.url;
                LocalBroadcastManager.getInstance(logoutActivity).sendBroadcast(new Intent(UserMamanger.f19980a));
            }
        }

        public void a(int i) {
            LogoutActivity logoutActivity = (LogoutActivity) this.f16626a.get();
            if (logoutActivity != null && logoutActivity.isValid()) {
                String unused = logoutActivity.G = "";
                logoutActivity.h.b((int) R.string.mikey_not_set);
                logoutActivity.k.b("");
            }
        }

        public void a(int i, Object obj) {
            LogoutActivity logoutActivity = (LogoutActivity) this.f16626a.get();
            if (logoutActivity != null && logoutActivity.isValid()) {
                String unused = logoutActivity.G = "";
                logoutActivity.h.b((int) R.string.mikey_not_set);
                logoutActivity.k.b("");
            }
        }
    }

    /* access modifiers changed from: private */
    public void j() {
        UserMamanger.a().a((AsyncResponseCallback<ShareUserRecord>) new MyAsyncResponseCallback(this));
    }

    private void k() {
        this.f.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LogoutActivity.this.finish();
            }
        });
        this.d.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LogoutActivity.this.finish();
            }
        });
        this.e.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LogoutActivity.this.s();
            }
        });
        this.h.a((View.OnClickListener) new View.OnClickListener() {
            public void onClick(View view) {
                LogoutActivity.this.t();
            }
        });
        this.g.d((int) R.id.arrow).setVisibility(8);
        this.g.a((View.OnClickListener) new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("url", XMStringUtils.c(LogoutActivity.this.H));
                intent.setClass(LogoutActivity.this, ImagePreviewActivity.class);
                LogoutActivity.this.startActivity(intent);
            }
        });
        this.g.b((View.OnClickListener) new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("url", XMStringUtils.c(LogoutActivity.this.H));
                intent.setClass(LogoutActivity.this, ImagePreviewActivity.class);
                LogoutActivity.this.startActivity(intent);
            }
        });
        this.l.a((View.OnClickListener) new View.OnClickListener() {
            public void onClick(View view) {
                LogoutActivity.this.r();
            }
        });
        if (!CoreApi.a().D()) {
            if (this.t != null) {
                this.t.c(8);
            }
            if (this.m != null) {
                this.m.a((View.OnClickListener) new View.OnClickListener() {
                    public void onClick(View view) {
                        LogoutActivity.this.y();
                    }
                });
            }
            if (this.n != null) {
                this.n.a((View.OnClickListener) new View.OnClickListener() {
                    public void onClick(View view) {
                        LogoutActivity.this.z();
                    }
                });
            }
            this.q.a((View.OnClickListener) new View.OnClickListener() {
                public void onClick(View view) {
                    LogoutActivity.this.u.performClick();
                    if (LogoutActivity.this.u.isChecked()) {
                        LogoutActivity.this.l();
                    } else {
                        LogoutActivity.this.m();
                    }
                }
            });
            this.u.setOnPerformCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (z) {
                        LogoutActivity.this.l();
                    } else {
                        LogoutActivity.this.m();
                    }
                }
            });
            return;
        }
        this.r.a((View.OnClickListener) new View.OnClickListener() {
            public void onClick(View view) {
                LogoutActivity.this.v.performClick();
                if (LogoutActivity.this.v.isChecked()) {
                    LogoutActivity.this.o();
                } else {
                    LogoutActivity.this.p();
                }
            }
        });
        this.v.setOnPerformCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    LogoutActivity.this.o();
                } else {
                    LogoutActivity.this.p();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void l() {
        startActivityForResult(new Intent(this.J, BindWxActivity.class), 102);
    }

    /* access modifiers changed from: private */
    public void m() {
        new MLAlertDialog.Builder(this).a((int) R.string.account_unbind_alert_title).b((int) R.string.account_unbind_alert_msg_wx).a((int) R.string.account_unbind_alert_unbind, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                LogoutActivity.this.n();
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                LogoutActivity.this.a(true);
            }
        }).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                LogoutActivity.this.a(true);
            }
        }).d();
    }

    /* access modifiers changed from: private */
    public void n() {
        AsyncTaskUtils.a(new AsyncTask<Void, Void, Boolean>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Boolean doInBackground(Void... voidArr) {
                SNSManager sNSManager = new SNSManager(LogoutActivity.this);
                try {
                    final XMPassportInfo build = XMPassportInfo.build(LogoutActivity.this.getApplicationContext(), "passportapi");
                    return Boolean.valueOf(sNSManager.unBindSNS(SNSType.WEIXIN, build, new SNSManager.UnBindSNSCallback() {
                        public void refreshAuthToken() {
                            build.refreshAuthToken(LogoutActivity.this.getApplicationContext());
                        }
                    }));
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(Boolean bool) {
                super.onPostExecute(bool);
                if (!bool.booleanValue()) {
                    ToastManager.a().a((int) R.string.account_unbind_failed);
                }
                LogoutActivity.this.a(!bool.booleanValue());
            }
        }, new Void[0]);
    }

    /* access modifiers changed from: private */
    public void o() {
        this.B = true;
        startActivityForResult(new Intent(this.J, BindFbActivity.class), 103);
    }

    /* access modifiers changed from: private */
    public void p() {
        new MLAlertDialog.Builder(this).a((int) R.string.account_unbind_alert_title).b((int) R.string.account_unbind_alert_msg_fb).a((int) R.string.account_unbind_alert_unbind, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                LogoutActivity.this.q();
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                LogoutActivity.this.b(true);
            }
        }).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                LogoutActivity.this.b(true);
            }
        }).d();
    }

    /* access modifiers changed from: private */
    public void q() {
        LoginApiNew.a().d(new AsyncCallback<Void, Error>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
                LogoutActivity.this.b(false);
            }

            public void onFailure(Error error) {
                LogoutActivity.this.b(true);
                Toast.makeText(LogoutActivity.this, R.string.account_unbind_failed, 0).show();
            }
        });
    }

    /* access modifiers changed from: private */
    public void r() {
        MjLoginRouter.c((Context) this, CoreApi.a().s());
    }

    /* access modifiers changed from: private */
    public void c(final String str) {
        AsyncTaskUtils.a(new AsyncTask<Object, Object, String>() {
            /* access modifiers changed from: protected */
            public void onPreExecute() {
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public String doInBackground(Object... objArr) {
                Bitmap decodeFile = BitmapFactory.decodeFile(str);
                String str = "";
                if (decodeFile != null) {
                    str = LogoutActivity.this.a(decodeFile);
                    if (!TextUtils.isEmpty(str)) {
                        ImageUtils.a(decodeFile);
                        com.xiaomi.smarthome.framework.statistic.StatHelper.Q();
                    }
                }
                return str;
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(String str) {
                if (!TextUtils.isEmpty(str)) {
                    Toast.makeText(LogoutActivity.this, R.string.change_head_success, 0).show();
                    LogoutActivity.this.j();
                    return;
                }
                Toast.makeText(LogoutActivity.this, R.string.change_head_fail, 0).show();
            }
        }, new Object[0]);
    }

    /* access modifiers changed from: private */
    public String a(Bitmap bitmap) {
        try {
            String s2 = CoreApi.a().s();
            String u2 = CoreApi.a().u();
            MiServiceTokenInfo a2 = CoreApi.a().a("passportapi");
            return XMPassport.uploadXiaomiUserIcon(s2, u2, "xiaomiio", a2.c, a2.d, bitmap);
        } catch (Exception e2) {
            MyLog.a((Throwable) e2);
            return "";
        }
    }

    /* access modifiers changed from: private */
    public void c(boolean z2) {
        if (z2) {
            if (this.z == null) {
                this.z = new XQProgressDialog(this);
                this.z.setMessage(getString(R.string.login_passport_logouting));
                this.z.setCancelable(true);
                this.z.setDismissCallBack(new MLAlertDialog.DismissCallBack() {
                    public void beforeDismissCallBack() {
                    }

                    public void afterDismissCallBack() {
                        if (LogoutActivity.this.mIsLogouting) {
                            if (LogoutActivity.this.logoutRequestHandle != null) {
                                LogoutActivity.this.logoutRequestHandle.cancel();
                            }
                            LogoutActivity.this.mIsLogouting = false;
                            LogoutActivity.this.logoutRequestHandle = null;
                        }
                    }
                });
            }
            if (!this.z.isShowing()) {
                this.z.show();
            }
        } else if (this.z != null && this.z.isShowing()) {
            this.z.dismiss();
        }
    }

    /* access modifiers changed from: private */
    public void s() {
        if (this.w == null) {
            this.w = new MLAlertDialog.Builder(this).a((int) R.string.setting_logout).b((CharSequence) getResources().getString(R.string.setting_logout_message)).a((int) R.string.ok_button, this.A).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).b();
        }
        this.w.show();
    }

    /* access modifiers changed from: private */
    public void t() {
        if (this.x == null) {
            final ClientRemarkInputView clientRemarkInputView = (ClientRemarkInputView) LayoutInflater.from(this).inflate(R.layout.client_remark_input_view, (ViewGroup) null);
            this.x = new MLAlertDialog.Builder(this).a((int) R.string.input_nick_name).b((View) clientRemarkInputView).d(false).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (clientRemarkInputView.getInputText().length() > 20) {
                        Toast.makeText(LogoutActivity.this, R.string.nick_too_long, 0).show();
                    } else {
                        clientRemarkInputView.onConfirm(dialogInterface);
                    }
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ((MLAlertDialog) dialogInterface).setAudoDismiss(true);
                }
            }).b();
            clientRemarkInputView.initialize(new ClientRemarkInputView.RenameInterface() {
                public final void modifyBackName(String str) {
                    LogoutActivity.this.e(str);
                }
            }, this.x, this.G, this.G, false);
        }
        this.x.show();
        View view = this.x.getView();
        if (view instanceof ClientRemarkInputView) {
            final ClientRemarkInputView clientRemarkInputView2 = (ClientRemarkInputView) view;
            clientRemarkInputView2.setInputText(this.G);
            clientRemarkInputView2.setHint(this.G);
            clientRemarkInputView2.setLimitSize(10);
            final EditText editText = clientRemarkInputView2.getEditText();
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(21)});
            final Button button = this.x.getButton(-1);
            button.setEnabled(false);
            button.setTextColor(getResources().getColor(R.color.std_list_subtitle));
            editText.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable editable) {
                }

                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    clientRemarkInputView2.setAlertText("");
                    button.setEnabled(true);
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    String obj = editText.getText().toString();
                    if (obj.length() <= 0) {
                        button.setEnabled(false);
                        button.setTextColor(LogoutActivity.this.getResources().getColor(R.color.std_list_subtitle));
                    } else if (StringUtil.u(obj)) {
                        clientRemarkInputView2.setAlertText(LogoutActivity.this.getString(R.string.tag_save_data_description));
                        button.setEnabled(false);
                        button.setTextColor(LogoutActivity.this.getResources().getColor(R.color.std_list_subtitle));
                    } else if (StringUtil.a((CharSequence) obj) > 20) {
                        clientRemarkInputView2.setAlertText(LogoutActivity.this.getString(R.string.room_name_too_long));
                        button.setEnabled(false);
                        button.setTextColor(LogoutActivity.this.getResources().getColor(R.color.std_list_subtitle));
                    } else {
                        clientRemarkInputView2.setAlertText("");
                        button.setEnabled(true);
                        button.setTextColor(LogoutActivity.this.getResources().getColor(R.color.std_dialog_button_green));
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void e(String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(this.G)) {
            d(str);
        }
    }

    private void d(final String str) {
        AsyncTaskUtils.a(new AsyncTask<Object, Object, Boolean>() {
            /* access modifiers changed from: protected */
            public void onPreExecute() {
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Boolean doInBackground(Object... objArr) {
                try {
                    String s = CoreApi.a().s();
                    String u = CoreApi.a().u();
                    MiServiceTokenInfo a2 = CoreApi.a().a("passportapi");
                    XMPassport.uploadXiaomiUserName(s, u, "xiaomiio", a2.c, a2.d, str);
                    String unused = LogoutActivity.this.G = str;
                    return true;
                } catch (Exception e) {
                    MyLog.a((Throwable) e);
                    return false;
                }
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(Boolean bool) {
                if (bool.booleanValue()) {
                    Toast.makeText(LogoutActivity.this, R.string.change_nick_success, 0).show();
                    LogoutActivity.this.j();
                    return;
                }
                Toast.makeText(LogoutActivity.this, R.string.change_nick_fail, 0).show();
            }
        }, new Object[0]);
        com.xiaomi.smarthome.framework.statistic.StatHelper.T();
    }

    /* access modifiers changed from: private */
    public void a(final Gender gender) {
        AsyncTaskUtils.a(new AsyncTask<Object, Object, Boolean>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Boolean doInBackground(Object... objArr) {
                try {
                    String s = CoreApi.a().s();
                    String u = CoreApi.a().u();
                    MiServiceTokenInfo a2 = CoreApi.a().a("passportapi");
                    XMPassport.uploadXiaomiUserGender(s, u, "xiaomiio", a2.c, a2.d, gender);
                    return true;
                } catch (Exception e) {
                    MyLog.a((Throwable) e);
                    return false;
                }
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(Boolean bool) {
                if (bool.booleanValue()) {
                    Toast.makeText(LogoutActivity.this, R.string.change_sex_success, 0).show();
                    LogoutActivity.this.j();
                    return;
                }
                Toast.makeText(LogoutActivity.this, R.string.change_sex_fail, 0).show();
            }
        }, new Object[0]);
    }

    /* access modifiers changed from: private */
    public void a(final Calendar calendar) {
        AsyncTaskUtils.a(new AsyncTask<Object, Object, Boolean>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Boolean doInBackground(Object... objArr) {
                try {
                    String s = CoreApi.a().s();
                    String u = CoreApi.a().u();
                    MiServiceTokenInfo a2 = CoreApi.a().a("passportapi");
                    XMPassport.uploadXiaomiUserBirthday(s, u, "xiaomiio", a2.c, a2.d, calendar);
                    return true;
                } catch (Exception e) {
                    MyLog.a((Throwable) e);
                    return false;
                }
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(Boolean bool) {
                if (bool.booleanValue()) {
                    Toast.makeText(LogoutActivity.this, R.string.change_birth_success, 0).show();
                    LogoutActivity.this.j();
                    return;
                }
                Toast.makeText(LogoutActivity.this, R.string.change_birth_fail, 0).show();
            }
        }, new Object[0]);
    }

    private void u() {
        if (this.y == null) {
            CharSequence[] charSequenceArr = {getString(R.string.select_from_camera), getString(R.string.select_local_picture)};
            this.y = new SimpleListDialog(this);
            this.y.a(getString(R.string.change_head_icon));
            this.y.setCancelable(true);
            this.y.setCanceledOnTouchOutside(true);
            this.y.a(charSequenceArr, (SimpleListDialog.IDialogInterface) new SimpleListDialog.IDialogInterface() {
                public void a(int i) {
                    LogoutActivity.this.a(i);
                }
            });
        }
        this.y.show();
    }

    private void v() {
        if (this.D == null) {
            this.D = new WheelDialog(this);
            this.D.a((int) R.string.ok_button, (View.OnClickListener) new View.OnClickListener() {
                public void onClick(View view) {
                    LogoutActivity.this.a(LogoutActivity.this.D.a());
                }
            });
            this.D.b((int) R.string.cancel, (View.OnClickListener) null);
        }
        this.D.a(this.F.birth);
        this.D.show();
    }

    private void w() {
        if (this.C == null) {
            CharSequence[] charSequenceArr = {getString(R.string.sex_male), getString(R.string.sex_female)};
            this.C = new SimpleListDialog(this);
            this.C.a(1);
            this.C.a(charSequenceArr, (SimpleListDialog.IDialogInterface) null);
            this.C.a((int) R.string.ok_button, (View.OnClickListener) new View.OnClickListener() {
                public void onClick(View view) {
                    LogoutActivity.this.a(LogoutActivity.this.C.a() == 1 ? Gender.FEMALE : Gender.MALE);
                }
            });
            this.C.b(R.string.cancel, (View.OnClickListener) null);
        }
        if (XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.sex_female).equalsIgnoreCase(this.F.sex)) {
            this.C.b(1);
        } else {
            this.C.b(0);
        }
        this.C.show();
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        switch (i2) {
            case 0:
                this.E.a();
                return;
            case 1:
                this.E.b();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public void x() {
        final boolean q2 = CoreApi.a().q();
        this.logoutRequestHandle = LoginManager.a().logout(new AsyncCallback<Void, Error>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
                LogoutActivity.this.mIsLogouting = false;
                LogoutActivity.this.c(false);
                com.xiaomi.smarthome.framework.statistic.StatHelper.f(q2);
                LogoutActivity.this.finish();
                PluginApi.getInstance().exitProcess(RunningProcess.PLUGIN0);
                PluginApi.getInstance().exitProcess(RunningProcess.PLUGIN1);
                PluginApi.getInstance().exitProcess(RunningProcess.PLUGIN2);
                PluginApi.getInstance().exitProcess(RunningProcess.PLUGIN3);
                PluginRuntimeManager.getInstance().exitAllFrameProcess();
            }

            public void onFailure(Error error) {
                LogoutActivity.this.mIsLogouting = false;
                LogoutActivity.this.c(false);
                Toast.makeText(LogoutActivity.this, R.string.status_error_cable_not_plugin_body, 0).show();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 102) {
            if (i3 == -1) {
                a(true);
            } else {
                a(false);
            }
            h();
        } else if (i2 == 103) {
            if (i3 == -1) {
                b(true);
            } else {
                b(false);
            }
            i();
        }
        if (!this.B) {
            this.E.a(i2, i3, intent);
        }
    }

    /* access modifiers changed from: private */
    public void y() {
        if (MiuiVipService.a() != null) {
            MiuiVipService.a().a((Context) this);
        }
    }

    /* access modifiers changed from: private */
    public void z() {
        if (MiuiVipService.a() != null) {
            MiuiVipService.a().b((Context) this);
        }
    }

    private class UserInfoContainer {
        private View b;
        private ImageView c;
        private SimpleDraweeView d;
        private TextView e;
        private TextView f;

        private UserInfoContainer(View view, int i) {
            this.b = view;
            if (this.b != null) {
                if (this.b instanceof ListItemView) {
                    ((ListItemView) this.b).setPosition(i);
                }
                this.c = (ImageView) this.b.findViewById(R.id.arrow);
                this.d = (SimpleDraweeView) this.b.findViewById(R.id.icon);
                this.e = (TextView) this.b.findViewById(R.id.title);
                this.f = (TextView) this.b.findViewById(R.id.subtitle);
            }
        }

        public void a(boolean z) {
            if (this.b != null) {
                if (z) {
                    this.b.findViewById(R.id.divider_line).setVisibility(8);
                    this.b.setBackgroundResource(R.drawable.common_white_list_padding_no_left_margin);
                    return;
                }
                this.b.findViewById(R.id.divider_line).setVisibility(0);
                this.b.setBackgroundResource(R.drawable.std_list_item_space_bg);
            }
        }

        public void b(boolean z) {
            if (this.b != null) {
                this.c.setVisibility(z ? 0 : 8);
                if (!z) {
                    ((LinearLayout.LayoutParams) this.f.getLayoutParams()).rightMargin = (int) LogoutActivity.this.getResources().getDimension(R.dimen.std_list_content_margin_right);
                    this.b.requestLayout();
                }
            }
        }

        public void c(boolean z) {
            if (this.b != null) {
                this.d.setVisibility(z ? 0 : 8);
            }
        }

        public void a(CharSequence charSequence) {
            if (charSequence != null && charSequence.length() != 0 && this.b != null) {
                this.d.setContentDescription(charSequence);
            }
        }

        public void a() {
            if (this.b != null) {
                this.b.setFocusable(true);
            }
        }

        public void d(boolean z) {
            if (this.b != null) {
                this.f.setVisibility(z ? 0 : 8);
            }
        }

        public void a(String str) {
            if (this.b != null) {
                this.e.setText(str);
            }
        }

        public void a(int i) {
            if (this.b != null) {
                this.e.setText(i);
            }
        }

        public void b(String str) {
            if (this.b != null) {
                this.f.setText(str);
            }
        }

        public void b(int i) {
            if (this.b != null) {
                this.f.setText(i);
            }
        }

        public void a(View.OnClickListener onClickListener) {
            if (this.b != null) {
                this.b.setOnClickListener(onClickListener);
            }
        }

        public void b(View.OnClickListener onClickListener) {
            if (this.b != null) {
                this.d.setOnClickListener(onClickListener);
            }
        }

        public void a(Bitmap bitmap) {
            if (this.b != null) {
                this.d.setImageBitmap(bitmap);
            }
        }

        public SimpleDraweeView b() {
            return this.d;
        }

        public void c(int i) {
            if (this.b != null) {
                this.b.setVisibility(i);
            }
        }

        public View d(int i) {
            if (this.b == null) {
                return null;
            }
            return this.b.findViewById(i);
        }
    }

    private class BindWechatTimeoutRunnableWrapper {

        /* renamed from: a  reason: collision with root package name */
        public Runnable f16625a;

        private BindWechatTimeoutRunnableWrapper() {
        }
    }

    private void a(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        a((Dialog) this.w);
        a((Dialog) this.x);
        a((Dialog) this.y);
        a((Dialog) this.z);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.Q);
    }
}
