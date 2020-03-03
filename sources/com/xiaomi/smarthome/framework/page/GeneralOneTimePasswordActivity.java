package com.xiaomi.smarthome.framework.page;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.taobao.weex.common.Constants;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.bluetooth.Response;
import com.xiaomi.smarthome.bluetooth.XmBluetoothManager;
import com.xiaomi.smarthome.bluetooth.XmBluetoothRecord;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.model.OneTimePasswordInfo;
import com.xiaomi.smarthome.framework.api.profile.OneTimePwdManager;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.OneTimePwdChooseTimeDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.ImageUtils;
import com.xiaomi.smarthome.library.common.widget.TimePicker;
import com.xiaomi.smarthome.miio.LanguageUtil;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.youpin.share.ShareObject;
import com.xiaomi.youpin.share.model.ShareChannel;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeSet;
import org.json.JSONArray;

public class GeneralOneTimePasswordActivity extends BaseActivity {
    static final String TAG = "OneTimePwdActivity";

    /* renamed from: a  reason: collision with root package name */
    private static final int f16835a = 7;
    private static final int b = 334;
    private static final int c = 42;
    private static final int d = 30;
    private static final int e = 8;
    private static final int f = 334;
    private static final int g = 36;
    private static final int h = 24;
    private static final String j = "show_copy_share_dialog";
    private static Map<String, Map<Long, PasswordCache>> p = new HashMap();
    private static Handler r;
    /* access modifiers changed from: private */
    public SharedPreferences i;
    /* access modifiers changed from: private */
    public String k;
    /* access modifiers changed from: private */
    public Dialog l;
    /* access modifiers changed from: private */
    public int m;
    @BindView(2131430969)
    ImageView mBackBt;
    @BindView(2131428538)
    LinearLayout mContainerView;
    @BindView(2131428586)
    TextView mCopyPasswordBtn;
    @BindView(2131428589)
    TextView mCopySuccessHint;
    @BindView(2131429237)
    TextView mFinishBtn;
    @BindView(2131428372)
    TextView mGenerateBtn;
    @BindView(2131429451)
    TextView mGenerateTipsView;
    @BindView(2131429452)
    TextView mGenerateTitleView;
    @BindView(2131431438)
    TextView mPasswordText1;
    @BindView(2131431439)
    TextView mPasswordText2;
    @BindView(2131431440)
    TextView mPasswordText3;
    @BindView(2131431441)
    TextView mPasswordText4;
    @BindView(2131431442)
    TextView mPasswordText5;
    @BindView(2131431443)
    TextView mPasswordText6;
    @BindView(2131431444)
    TextView mPasswordText7;
    @BindView(2131431445)
    TextView mPasswordText8;
    @BindView(2131432418)
    TextView mShareBtn;
    /* access modifiers changed from: private */
    public int n;
    /* access modifiers changed from: private */
    public Device o;
    private long q;
    private boolean s;
    private Bitmap t;
    private Bitmap u;
    /* access modifiers changed from: private */
    public OneTimePasswordInfo v;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!a()) {
            finish();
            return;
        }
        setContentView(R.layout.activity_general_one_time_password);
        ButterKnife.bind((Activity) this);
        this.i = getSharedPreferences(j, 0);
        r = new Handler(Looper.getMainLooper());
        this.s = false;
        b();
    }

    private boolean a() {
        String stringExtra = getIntent().getStringExtra("did");
        this.n = getIntent().getIntExtra(Constants.Name.INTERVAL, 0);
        this.m = getIntent().getIntExtra("digits", 0);
        this.o = SmartHomeDeviceManager.a().b(stringExtra);
        if (this.o == null) {
            MyLog.f("GeneralOneTimePasswordActivity failed, device don't exist, " + stringExtra);
            return false;
        } else if (this.n < 1 || this.n > 60) {
            MyLog.f("GeneralOneTimePasswordActivity failed, mInterval is invalid, " + this.n);
            return false;
        } else if (this.m >= 6 && this.m <= 8) {
            return true;
        } else {
            MyLog.f("GeneralOneTimePasswordActivity failed, mPasswordNum is invalid, " + this.m);
            return false;
        }
    }

    private void b() {
        this.mBackBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GeneralOneTimePasswordActivity.this.h();
            }
        });
        this.mFinishBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GeneralOneTimePasswordActivity.this.h();
            }
        });
        this.mGenerateTitleView.setText(R.string.one_time_password_ready_generate_title);
        this.mGenerateTipsView.setText(R.string.one_time_password_ready_generate_tips);
        this.mCopyPasswordBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ((ClipboardManager) GeneralOneTimePasswordActivity.this.getSystemService(ShareChannel.d)).setPrimaryClip(ClipData.newPlainText("text", GeneralOneTimePasswordActivity.this.k));
                GeneralOneTimePasswordActivity.this.d();
            }
        });
        this.mShareBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (GeneralOneTimePasswordActivity.this.l != null && GeneralOneTimePasswordActivity.this.l.isShowing()) {
                    GeneralOneTimePasswordActivity.this.l.dismiss();
                }
                View inflate = GeneralOneTimePasswordActivity.this.getLayoutInflater().inflate(R.layout.dialog_one_time_password_share, (ViewGroup) null);
                Dialog unused = GeneralOneTimePasswordActivity.this.l = new MLAlertDialog.Builder(GeneralOneTimePasswordActivity.this).b(inflate).e();
                GeneralOneTimePasswordActivity.this.l.getWindow().setLayout(DisplayUtils.a((Activity) GeneralOneTimePasswordActivity.this, 342.0f), -2);
                final View findViewById = inflate.findViewById(R.id.share_content);
                TextView textView = (TextView) inflate.findViewById(R.id.product_name);
                TextView textView2 = (TextView) inflate.findViewById(R.id.password);
                TextView textView3 = (TextView) inflate.findViewById(R.id.deadline_time);
                PluginRecord d = CoreApi.a().d(GeneralOneTimePasswordActivity.this.o.model);
                if (d != null) {
                    textView.setText(d.c().k());
                }
                StringBuilder sb = new StringBuilder();
                sb.append(GeneralOneTimePasswordActivity.this.k.charAt(0));
                sb.append(" ");
                sb.append(GeneralOneTimePasswordActivity.this.k.charAt(1));
                sb.append(" ");
                sb.append(GeneralOneTimePasswordActivity.this.k.charAt(2));
                sb.append(" ");
                sb.append(GeneralOneTimePasswordActivity.this.k.charAt(3));
                sb.append(" ");
                sb.append(GeneralOneTimePasswordActivity.this.k.charAt(4));
                sb.append(" ");
                sb.append(GeneralOneTimePasswordActivity.this.k.charAt(5));
                if (GeneralOneTimePasswordActivity.this.m == 7) {
                    sb.append(" ");
                    sb.append(GeneralOneTimePasswordActivity.this.k.charAt(6));
                } else if (GeneralOneTimePasswordActivity.this.m == 8) {
                    sb.append(" ");
                    sb.append(GeneralOneTimePasswordActivity.this.k.charAt(6));
                    sb.append(" ");
                    sb.append(GeneralOneTimePasswordActivity.this.k.charAt(7));
                }
                textView2.setText(sb.toString());
                textView3.setText(GeneralOneTimePasswordActivity.this.getResources().getString(R.string.one_time_password_valid_period) + ": " + GeneralOneTimePasswordActivity.this.v.g() + com.xiaomi.mipush.sdk.Constants.J + GeneralOneTimePasswordActivity.this.v.h() + ",");
                File externalCacheDir = GeneralOneTimePasswordActivity.this.getExternalCacheDir();
                externalCacheDir.mkdirs();
                File file = new File(externalCacheDir, "weixin_share.jpg");
                if (file.exists() && file.isFile()) {
                    if (file.delete()) {
                        Log.d(GeneralOneTimePasswordActivity.TAG, "delete last pwd pic Success.");
                    } else {
                        Log.e(GeneralOneTimePasswordActivity.TAG, "delete last pwd pic failed.");
                    }
                }
                inflate.findViewById(R.id.close_btn).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (GeneralOneTimePasswordActivity.this.l != null && GeneralOneTimePasswordActivity.this.l.isShowing()) {
                            GeneralOneTimePasswordActivity.this.l.dismiss();
                        }
                    }
                });
                inflate.findViewById(R.id.confirm_share).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        GeneralOneTimePasswordActivity.this.a(findViewById);
                    }
                });
            }
        });
        this.mGenerateBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                new MLAlertDialog.Builder(GeneralOneTimePasswordActivity.this).a((int) R.string.one_time_password_choose_valid_time).a((CharSequence[]) new String[]{GeneralOneTimePasswordActivity.this.getString(R.string.one_time_password_valid_now), GeneralOneTimePasswordActivity.this.getString(R.string.one_time_password_valid_timer)}, 0, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {
                            GeneralOneTimePasswordActivity.this.c();
                        } else {
                            final OneTimePwdChooseTimeDialog oneTimePwdChooseTimeDialog = new OneTimePwdChooseTimeDialog(GeneralOneTimePasswordActivity.this, GeneralOneTimePasswordActivity.this.n);
                            oneTimePwdChooseTimeDialog.setTitle(R.string.one_time_password_set_valid_time);
                            oneTimePwdChooseTimeDialog.setCancelable(true);
                            oneTimePwdChooseTimeDialog.a(R.string.ok_button, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    TimePicker a2 = oneTimePwdChooseTimeDialog.a();
                                    if (a2 == null) {
                                        Log.e(GeneralOneTimePasswordActivity.TAG, "TimePicker is null.");
                                        return;
                                    }
                                    final int intValue = a2.getCurrentHour().intValue();
                                    final int intValue2 = a2.getCurrentMinute().intValue() * GeneralOneTimePasswordActivity.this.n;
                                    XmPluginHostApi.instance().getUTCFromServer("", new Callback<Long>() {
                                        /* renamed from: a */
                                        public void onSuccess(Long l) {
                                            GeneralOneTimePasswordActivity.this.a(GeneralOneTimePasswordActivity.this.a(l.longValue(), intValue, intValue2), l.longValue());
                                        }

                                        public void onFailure(int i, String str) {
                                            Toast.makeText(GeneralOneTimePasswordActivity.this, R.string.one_time_password_generate_failed, 0).show();
                                        }
                                    });
                                }
                            });
                            oneTimePwdChooseTimeDialog.b(R.string.cancel, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            });
                            oneTimePwdChooseTimeDialog.show();
                        }
                        dialogInterface.dismiss();
                    }
                }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).d();
            }
        });
        if (this.m == 7) {
            ViewGroup.LayoutParams layoutParams = this.mContainerView.getLayoutParams();
            layoutParams.width = DisplayUtils.a((Activity) this, 334.0f);
            this.mContainerView.setLayoutParams(layoutParams);
            a(this.mPasswordText1, 42, 30);
            a(this.mPasswordText2, 42, 30);
            a(this.mPasswordText3, 42, 30);
            a(this.mPasswordText4, 42, 30);
            a(this.mPasswordText5, 42, 30);
            a(this.mPasswordText6, 42, 30);
            this.mPasswordText7.setVisibility(0);
            a(this.mPasswordText7, 42, 30);
        } else if (this.m == 8) {
            ViewGroup.LayoutParams layoutParams2 = this.mContainerView.getLayoutParams();
            layoutParams2.width = DisplayUtils.a((Activity) this, 334.0f);
            this.mContainerView.setLayoutParams(layoutParams2);
            a(this.mPasswordText1, 36, 24);
            a(this.mPasswordText2, 36, 24);
            a(this.mPasswordText3, 36, 24);
            a(this.mPasswordText4, 36, 24);
            a(this.mPasswordText5, 36, 24);
            a(this.mPasswordText6, 36, 24);
            this.mPasswordText7.setVisibility(0);
            a(this.mPasswordText7, 36, 24);
            this.mPasswordText8.setVisibility(0);
            a(this.mPasswordText8, 36, 24);
        }
    }

    /* access modifiers changed from: private */
    public long a(long j2, int i2, int i3) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(1000 * j2);
        int i4 = instance.get(11);
        int i5 = instance.get(12);
        int i6 = ((i4 * 60) + i5) * 60;
        int i7 = ((i2 * 60) + i3) * 60;
        Log.i(TAG, "Current hour = " + i4 + ", minute = " + i5 + ", delay hour = " + i2 + ", minute = " + i3);
        return i7 >= i6 ? (j2 + ((long) i7)) - ((long) i6) : ((j2 + ((long) i7)) - ((long) i6)) + 86400;
    }

    /* access modifiers changed from: private */
    public void c() {
        XmBluetoothManager.getInstance().getOneTimePassword(this.o.mac, this.n, this.m, new Response.BleResponseV2<int[]>() {
            /* renamed from: a */
            public void onResponse(int i, String str, final int[] iArr) {
                if (i != 0 || iArr == null || iArr.length <= 0) {
                    Toast.makeText(GeneralOneTimePasswordActivity.this, R.string.one_time_password_generate_failed, 0).show();
                } else {
                    XmPluginHostApi.instance().getUTCFromServer("", new Callback<Long>() {
                        /* renamed from: a */
                        public void onSuccess(Long l) {
                            GeneralOneTimePasswordActivity.this.a(l.longValue(), l.longValue(), iArr, false);
                        }

                        public void onFailure(int i, String str) {
                            Toast.makeText(GeneralOneTimePasswordActivity.this, R.string.one_time_password_generate_failed, 0).show();
                        }
                    });
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(long j2, long j3) {
        final long j4 = j2;
        final long j5 = j3;
        XmBluetoothManager.getInstance().getOneTimePasswordWithDelayTime(this.o.mac, this.n, this.m, j2, new Response.BleResponseV2<int[]>() {
            /* renamed from: a */
            public void onResponse(int i, String str, int[] iArr) {
                if (i != 0 || iArr == null || iArr.length <= 0) {
                    Toast.makeText(GeneralOneTimePasswordActivity.this, R.string.one_time_password_generate_failed, 0).show();
                } else {
                    GeneralOneTimePasswordActivity.this.a(j4, j5, iArr, true);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(long j2, long j3, int[] iArr, boolean z) {
        int i2;
        int[] iArr2 = iArr;
        this.q = ((j2 / ((long) (this.n * 60))) + 2) * ((long) this.n) * 60;
        Map map = p.get(this.o.did);
        PasswordCache passwordCache = map != null ? (PasswordCache) map.get(Long.valueOf(this.q)) : null;
        int a2 = a(this.q);
        if (a2 >= iArr2.length) {
            Log.e("TAG", "existNumOfOldPwdList >= " + iArr2.length);
            b(j2);
            return;
        }
        if (passwordCache == null || !a(iArr2, passwordCache.b)) {
            HashMap hashMap = new HashMap();
            PasswordCache passwordCache2 = new PasswordCache(iArr2);
            if (a2 > 0) {
                passwordCache2.f16854a = a2;
            }
            hashMap.put(Long.valueOf(this.q), passwordCache2);
            p.put(this.o.did, hashMap);
            passwordCache = passwordCache2;
        } else if (passwordCache.f16854a < iArr2.length - 1) {
            if (a2 > 0 && passwordCache.f16854a < (i2 = a2 - 1)) {
                passwordCache.f16854a = i2;
            }
            passwordCache.f16854a++;
        } else {
            b(j2);
            return;
        }
        this.k = a(passwordCache.a());
        this.v = new OneTimePasswordInfo(j3, j2, this.q, OneTimePwdManager.a(this.k, String.valueOf(this.q), this.o.mac, this.o.userId));
        XmPluginHostApi.instance().getUserDeviceData(this.o.model, this.o.did, XmBluetoothRecord.TYPE_PROP, "device_lock", 0, j3, new Callback<JSONArray>() {
            /* renamed from: a */
            public void onSuccess(JSONArray jSONArray) {
                Log.i(GeneralOneTimePasswordActivity.TAG, " get result =" + jSONArray);
                OneTimePwdManager.a(GeneralOneTimePasswordActivity.this.o, jSONArray, GeneralOneTimePasswordActivity.this.v);
            }

            public void onFailure(int i, String str) {
                Log.i(GeneralOneTimePasswordActivity.TAG, "getUserDeviceData failed, error = " + i + ", msg = " + str);
            }
        });
        this.mGenerateTitleView.setText(R.string.one_time_password_has_generate_title);
        this.mGenerateTipsView.setText(Html.fromHtml(String.format(getResources().getString(R.string.one_time_password_has_generate_tips), new Object[]{"<font color='#32BAC0'>" + f() + "</font>"})));
        this.mPasswordText1.setText(String.valueOf(this.k.charAt(0)));
        this.mPasswordText2.setText(String.valueOf(this.k.charAt(1)));
        this.mPasswordText3.setText(String.valueOf(this.k.charAt(2)));
        this.mPasswordText4.setText(String.valueOf(this.k.charAt(3)));
        this.mPasswordText5.setText(String.valueOf(this.k.charAt(4)));
        this.mPasswordText6.setText(String.valueOf(this.k.charAt(5)));
        if (this.m == 7) {
            this.mPasswordText7.setText(String.valueOf(this.k.charAt(6)));
        } else if (this.m == 8) {
            this.mPasswordText7.setText(String.valueOf(this.k.charAt(6)));
            this.mPasswordText8.setText(String.valueOf(this.k.charAt(7)));
        }
        this.mGenerateBtn.setVisibility(8);
        this.mCopyPasswordBtn.setVisibility(0);
        this.mShareBtn.setVisibility(0);
        this.mFinishBtn.setVisibility(0);
        this.s = true;
    }

    private int a(long j2) {
        ArrayList arrayList = new ArrayList(OneTimePwdManager.f16465a.values());
        TreeSet treeSet = new TreeSet();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (((OneTimePasswordInfo) arrayList.get(i2)).c() == j2) {
                treeSet.add(((OneTimePasswordInfo) arrayList.get(i2)).d());
            }
        }
        if (treeSet.size() > 0) {
            return treeSet.size();
        }
        return -1;
    }

    private void b(long j2) {
        if (isValid()) {
            new MLAlertDialog.Builder(this).b((CharSequence) String.format(getResources().getString(R.string.one_time_password_max_time), new Object[]{c(j2)})).a((int) R.string.ok_button, (DialogInterface.OnClickListener) null).b().show();
        }
    }

    private void a(TextView textView, int i2, int i3) {
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        float f2 = (float) i2;
        layoutParams.width = DisplayUtils.a((Activity) this, f2);
        layoutParams.height = DisplayUtils.a((Activity) this, f2);
        textView.setLayoutParams(layoutParams);
        textView.setTextSize((float) i3);
    }

    /* access modifiers changed from: private */
    public void d() {
        if (this.mCopySuccessHint != null) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration(400);
            alphaAnimation.setFillAfter(true);
            this.mCopySuccessHint.setVisibility(0);
            this.mCopySuccessHint.startAnimation(alphaAnimation);
            if (r != null) {
                r.postDelayed(new Runnable() {
                    public void run() {
                        GeneralOneTimePasswordActivity.this.e();
                    }
                }, 2000);
            }
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(400);
        alphaAnimation.setFillAfter(true);
        if (this.mCopySuccessHint != null) {
            this.mCopySuccessHint.startAnimation(alphaAnimation);
        }
    }

    private String c(long j2) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(((j2 / ((long) (this.n * 60))) + 1) * ((long) this.n) * 60 * 1000);
        return String.format("%1$02d:%2$02d", new Object[]{Integer.valueOf(instance.get(11)), Integer.valueOf(instance.get(12))});
    }

    private String f() {
        Calendar instance = Calendar.getInstance();
        int i2 = instance.get(11);
        int i3 = instance.get(12);
        instance.setTimeInMillis(this.q * 1000);
        int i4 = instance.get(11);
        int i5 = instance.get(12);
        String format = String.format("%1$02d:%2$02d", new Object[]{Integer.valueOf(i4), Integer.valueOf(i5)});
        if ((i2 * 60) + i3 <= (i4 * 60) + i5) {
            return format;
        }
        Locale locale = Locale.getDefault();
        if (locale.equals(Locale.CHINA) || locale.equals(Locale.TAIWAN)) {
            return getResources().getString(R.string.plug_timer_tomorrow) + format;
        }
        return getResources().getString(R.string.plug_timer_tomorrow).toLowerCase(locale) + " " + format;
    }

    private String g() {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(this.q * 1000);
        return new SimpleDateFormat(LanguageUtil.a((Activity) this), Locale.CHINA).format(instance.getTime());
    }

    private String a(int i2) {
        String valueOf = String.valueOf(i2);
        if (valueOf.length() >= this.m) {
            return valueOf;
        }
        int length = this.m - valueOf.length();
        byte[] bArr = new byte[this.m];
        for (int i3 = 0; i3 < length; i3++) {
            bArr[i3] = 48;
        }
        System.arraycopy(valueOf.getBytes(), 0, bArr, length, valueOf.getBytes().length);
        return new String(bArr);
    }

    /* access modifiers changed from: private */
    public void a(View view) {
        if (!a("com.tencent.mm")) {
            Toast.makeText(this, R.string.device_shop_share_no_weixin, 1).show();
            return;
        }
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(createBitmap));
        this.t = changeColor(createBitmap);
        this.u = ImageUtils.b(this.t, 150);
        IWXAPI iwxapi = SHApplication.getIWXAPI();
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        if (this.u != null) {
            wXMediaMessage.setThumbImage(this.u);
        }
        if (this.t != null) {
            wXMediaMessage.mediaObject = new WXImageObject(this.t);
        }
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = wXMediaMessage;
        req.scene = 0;
        boolean sendReq = iwxapi.sendReq(req);
        Miio.h(TAG, "sendReq return " + sendReq);
        createBitmap.recycle();
    }

    private boolean a(String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        return packageInfo != null;
    }

    private Intent b(String str) {
        ActivityInfo activityInfo;
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND").setType(ShareObject.d);
        List<ResolveInfo> queryIntentActivities = getPackageManager().queryIntentActivities(intent, 0);
        if (!queryIntentActivities.isEmpty()) {
            Iterator<ResolveInfo> it = queryIntentActivities.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ResolveInfo next = it.next();
                if (str.equals(next.activityInfo.name)) {
                    activityInfo = next.activityInfo;
                    break;
                }
            }
        }
        activityInfo = null;
        if (activityInfo == null) {
            return null;
        }
        Intent intent2 = new Intent("android.intent.action.SEND");
        intent2.setType(ShareObject.d);
        intent2.setClassName(activityInfo.packageName, activityInfo.name);
        intent2.addFlags(268468225);
        intent2.putExtra("android.intent.extra.TEXT", getString(R.string.share_score_score_info));
        return intent2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0020 A[SYNTHETIC, Splitter:B:14:0x0020] */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.io.File r3, android.graphics.Bitmap r4) {
        /*
            r2 = this;
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0028, OutOfMemoryError -> 0x0024, all -> 0x001c }
            r1.<init>(r3)     // Catch:{ Exception -> 0x0028, OutOfMemoryError -> 0x0024, all -> 0x001c }
            if (r4 == 0) goto L_0x0018
            android.graphics.Bitmap$CompressFormat r3 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ Exception -> 0x0029, OutOfMemoryError -> 0x0025, all -> 0x0016 }
            r0 = 90
            boolean r3 = r4.compress(r3, r0, r1)     // Catch:{ Exception -> 0x0029, OutOfMemoryError -> 0x0025, all -> 0x0016 }
            if (r3 == 0) goto L_0x0018
            r1.flush()     // Catch:{ Exception -> 0x0029, OutOfMemoryError -> 0x0025, all -> 0x0016 }
            goto L_0x0018
        L_0x0016:
            r3 = move-exception
            goto L_0x001e
        L_0x0018:
            r1.close()     // Catch:{ IOException -> 0x002c }
            goto L_0x002c
        L_0x001c:
            r3 = move-exception
            r1 = r0
        L_0x001e:
            if (r1 == 0) goto L_0x0023
            r1.close()     // Catch:{ IOException -> 0x0023 }
        L_0x0023:
            throw r3
        L_0x0024:
            r1 = r0
        L_0x0025:
            if (r1 == 0) goto L_0x002c
            goto L_0x0018
        L_0x0028:
            r1 = r0
        L_0x0029:
            if (r1 == 0) goto L_0x002c
            goto L_0x0018
        L_0x002c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.page.GeneralOneTimePasswordActivity.a(java.io.File, android.graphics.Bitmap):void");
    }

    public static Bitmap changeColor(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[(width * height)];
        int i2 = 0;
        int i3 = 0;
        while (i2 < height) {
            int i4 = i3;
            int i5 = 0;
            while (i5 < width) {
                iArr[i4] = b(bitmap.getPixel(i5, i2));
                i5++;
                i4++;
            }
            i2++;
            i3 = i4;
        }
        return Bitmap.createBitmap(iArr, width, height, Bitmap.Config.ARGB_8888);
    }

    private static int b(int i2) {
        int alpha = Color.alpha(i2);
        return Color.rgb(a(Color.red(i2), alpha), a(Color.green(i2), alpha), a(Color.blue(i2), alpha));
    }

    private static int a(int i2, int i3) {
        int i4 = (((i2 * i3) / 255) + 255) - i3;
        if (i4 > 255) {
            return 255;
        }
        return i4;
    }

    private boolean a(int[] iArr, int[] iArr2) {
        if (iArr == null || iArr2 == null || iArr.length != iArr2.length) {
            return false;
        }
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[i2] != iArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    private class PasswordCache {

        /* renamed from: a  reason: collision with root package name */
        int f16854a = 0;
        int[] b;

        public PasswordCache(int[] iArr) {
            this.b = iArr;
        }

        public int a() {
            if (this.b == null || this.f16854a >= this.b.length) {
                return 0;
            }
            return this.b[this.f16854a];
        }
    }

    public void onBackPressed() {
        h();
    }

    /* access modifiers changed from: private */
    public void h() {
        if (!this.s) {
            finish();
        } else if (this.i == null || this.i.getBoolean(j, true)) {
            View inflate = LayoutInflater.from(this).inflate(R.layout.one_time_copy_share_dialog, (ViewGroup) null);
            Button button = (Button) inflate.findViewById(R.id.ok);
            final CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.remember);
            TextView textView = (TextView) inflate.findViewById(R.id.remember_textview);
            if (textView.getText() != null && textView.getText().toString().contains("show again")) {
                textView.setText("Do not remind me again");
            }
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (checkBox.isChecked() && GeneralOneTimePasswordActivity.this.i != null) {
                        GeneralOneTimePasswordActivity.this.i.edit().putBoolean(GeneralOneTimePasswordActivity.j, false).apply();
                    }
                    GeneralOneTimePasswordActivity.this.finish();
                }
            });
            MLAlertDialog b2 = new MLAlertDialog.Builder(this).b(inflate).b();
            b2.setCancelable(true);
            b2.show();
        } else {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.t != null) {
            this.t.recycle();
            this.t = null;
        }
        if (this.u != null) {
            this.u.recycle();
            this.u = null;
        }
    }
}
