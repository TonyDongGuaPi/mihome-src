package com.xiaomi.verificationsdk;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.passport.uicontroller.SimpleFutureTask;
import com.xiaomi.verificationsdk.internal.Config;
import com.xiaomi.verificationsdk.internal.Constants;
import com.xiaomi.verificationsdk.internal.EnvEncryptUtils;
import com.xiaomi.verificationsdk.internal.ErrorInfo;
import com.xiaomi.verificationsdk.internal.NetWorkUtils;
import com.xiaomi.verificationsdk.internal.RegisterInfo;
import com.xiaomi.verificationsdk.internal.ScoreManager;
import com.xiaomi.verificationsdk.internal.SensorHelper;
import com.xiaomi.verificationsdk.internal.SharedPreferencesUtil;
import com.xiaomi.verificationsdk.internal.VerificationException;
import com.xiaomi.verificationsdk.internal.VerifyError;
import com.xiaomi.verificationsdk.internal.VerifyRequest;
import com.xiaomi.verificationsdk.internal.VerifyResult;
import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

public class VerificationManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23089a = "VerificationManager";
    private static final ExecutorService b = Executors.newCachedThreadPool();
    private static final String q = "VerificationConfig";
    private SimpleFutureTask<Config> c;
    /* access modifiers changed from: private */
    public SensorHelper d;
    /* access modifiers changed from: private */
    public VerifyResultCallback e;
    /* access modifiers changed from: private */
    public AsyncSessionRegister f;
    /* access modifiers changed from: private */
    public WebView g;
    /* access modifiers changed from: private */
    public LinearLayout h;
    /* access modifiers changed from: private */
    public AlertDialog i;
    /* access modifiers changed from: private */
    public Handler j;
    /* access modifiers changed from: private */
    public String k;
    /* access modifiers changed from: private */
    public boolean l;
    /* access modifiers changed from: private */
    public boolean m;
    /* access modifiers changed from: private */
    public boolean n;
    /* access modifiers changed from: private */
    public boolean o;
    /* access modifiers changed from: private */
    public boolean p;
    /* access modifiers changed from: private */
    public int r;
    /* access modifiers changed from: private */
    public int s;
    /* access modifiers changed from: private */
    public SharedPreferencesUtil t;
    /* access modifiers changed from: private */
    public WeakReference<Activity> u;
    /* access modifiers changed from: private */
    public boolean v = true;
    /* access modifiers changed from: private */
    public View w;
    /* access modifiers changed from: private */
    public final AtomicBoolean x = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public DialogInterface.OnKeyListener y = new DialogInterface.OnKeyListener() {
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            if (i != 4) {
                return false;
            }
            VerificationManager.this.g();
            if (VerificationManager.this.e == null) {
                return true;
            }
            VerificationManager.this.j.post(new Runnable() {
                public void run() {
                    VerificationManager.this.e.a();
                    VerificationManager.b(VerificationManager.this.x);
                }
            });
            return true;
        }
    };
    /* access modifiers changed from: private */
    public DialogInterface.OnDismissListener z = new DialogInterface.OnDismissListener() {
        public void onDismiss(DialogInterface dialogInterface) {
            if (VerificationManager.this.v && VerificationManager.this.e != null) {
                VerificationManager.this.j.post(new Runnable() {
                    public void run() {
                        VerificationManager.this.e.a();
                        VerificationManager.b(VerificationManager.this.x);
                    }
                });
            }
        }
    };

    public interface AsyncSessionRegister {
        void a(ValueCallback<RegisterInfo> valueCallback);
    }

    public interface SessionRegister {
        RegisterInfo a();
    }

    public interface Verify2Callback {
        void a(VerifyError verifyError);

        void a(VerifyResult verifyResult);

        void a(String str);
    }

    public interface VerifyResultCallback {
        void a();

        void a(VerifyError verifyError);

        void a(VerifyResult verifyResult);
    }

    public VerificationManager(Activity activity) {
        if (activity != null) {
            this.j = new Handler(Looper.getMainLooper());
            this.u = new WeakReference<>(activity);
            this.d = new SensorHelper(activity.getApplicationContext());
            this.t = new SharedPreferencesUtil((Context) this.u.get(), q);
            return;
        }
        throw new IllegalArgumentException("activity  should not be null");
    }

    public void a() {
        d();
    }

    /* access modifiers changed from: private */
    public void c() {
        this.d.b();
        d();
    }

    private void d() {
        if (Math.abs(System.currentTimeMillis() - this.t.a(Constants.P, 0)) < 86400000) {
            AccountLog.i(f23089a, "not download twice within 24 hours");
            this.r = this.t.a(Constants.R, 5000);
            this.s = this.t.a(Constants.Q, 50);
            this.d.a(this.s, this.r);
            return;
        }
        b(Constants.N);
    }

    public void b() {
        if (a(this.x)) {
            this.v = true;
            if (this.e == null) {
                throw new IllegalArgumentException("startVerify: mVerifyResultCallback should not be null");
            } else if (this.f == null) {
                throw new IllegalArgumentException("startVerify: mAsyncSessionRegister should not be null");
            } else if (e()) {
                this.m = false;
                h();
            } else {
                i();
            }
        }
    }

    static boolean a(AtomicBoolean atomicBoolean) {
        return atomicBoolean.compareAndSet(false, true);
    }

    static void b(AtomicBoolean atomicBoolean) {
        atomicBoolean.getAndSet(false);
    }

    private boolean e() {
        return TextUtils.isEmpty(this.k) || this.l || this.m;
    }

    /* access modifiers changed from: private */
    public void f() {
        if (this.i != null) {
            this.i.dismiss();
            this.i = null;
        }
    }

    /* access modifiers changed from: private */
    public void g() {
        if (this.i != null) {
            this.i.hide();
        }
    }

    private void a(final String str, final RegisterInfo registerInfo) {
        if (this.u.get() == null || ((Activity) this.u.get()).isFinishing()) {
            Log.e(f23089a, "Activity is destroy");
        } else if (registerInfo != null) {
            this.j.post(new Runnable() {
                public void run() {
                    View unused = VerificationManager.this.w = ((Activity) VerificationManager.this.u.get()).getLayoutInflater().inflate(R.layout.verify_dialog, (ViewGroup) null);
                    WebView unused2 = VerificationManager.this.g = (WebView) VerificationManager.this.w.findViewById(R.id.verify_webView);
                    LinearLayout unused3 = VerificationManager.this.h = (LinearLayout) VerificationManager.this.w.findViewById(R.id.verify_ProgressBar);
                    if (VerificationManager.this.h != null && VerificationManager.this.h.getVisibility() == 0) {
                        VerificationManager.this.h.setVisibility(8);
                    }
                    if (VerificationManager.this.i != null) {
                        VerificationManager.this.i.dismiss();
                        AlertDialog unused4 = VerificationManager.this.i = null;
                    }
                    if (!TextUtils.isEmpty(str)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder((Context) VerificationManager.this.u.get(), 16974394);
                        WebSettings settings = VerificationManager.this.g.getSettings();
                        settings.setJavaScriptEnabled(true);
                        settings.setUseWideViewPort(true);
                        String a2 = VerificationManager.this.a((Context) VerificationManager.this.u.get());
                        settings.setUserAgentString(a2 + " androidVerifySDK/" + BuildConfig.f + " androidVerifySDK/VersionCode/" + 3);
                        VerificationManager.this.g.setWebViewClient(new WebViewClient() {
                            public void onPageFinished(WebView webView, String str) {
                                super.onPageFinished(webView, str);
                                VerificationManager.this.h.setVisibility(8);
                                VerificationManager.this.g.setVisibility(0);
                            }

                            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                                Bundle a2;
                                if (!str.contains(Constants.W) || (a2 = NetWorkUtils.a(str)) == null) {
                                    return false;
                                }
                                int parseInt = Integer.parseInt(a2.getString("code"));
                                String string = a2.getString("flag");
                                if (parseInt == 0) {
                                    VerificationManager.this.c();
                                    boolean unused = VerificationManager.this.v = false;
                                    VerificationManager.this.f();
                                    String unused2 = VerificationManager.this.k = "";
                                    boolean unused3 = VerificationManager.this.l = false;
                                    final VerifyResult a3 = new VerifyResult.Builder().a(registerInfo.b()).b(registerInfo.a()).c(string).d(ScoreManager.a()).a();
                                    VerificationManager.this.j.post(new Runnable() {
                                        public void run() {
                                            VerificationManager.this.e.a(a3);
                                            VerificationManager.b(VerificationManager.this.x);
                                        }
                                    });
                                    return true;
                                }
                                if (parseInt == 1) {
                                    boolean unused4 = VerificationManager.this.v = false;
                                    boolean unused5 = VerificationManager.this.m = true;
                                    VerificationManager.this.f();
                                    VerificationManager.this.j.post(new Runnable() {
                                        public void run() {
                                            VerificationManager.this.e.a();
                                            VerificationManager.b(VerificationManager.this.x);
                                        }
                                    });
                                } else if (parseInt == 2) {
                                    boolean unused6 = VerificationManager.this.v = false;
                                    VerificationManager.this.f();
                                    boolean unused7 = VerificationManager.this.l = true;
                                    final VerifyError a4 = VerificationManager.a(ErrorInfo.ErrorCode.ERROR_EVENTID_EXPIRED.getCode(), "eventid expired");
                                    VerificationManager.this.j.post(new Runnable() {
                                        public void run() {
                                            VerificationManager.this.e.a(a4);
                                            VerificationManager.b(VerificationManager.this.x);
                                        }
                                    });
                                } else if (parseInt == 3) {
                                    boolean unused8 = VerificationManager.this.v = false;
                                    VerificationManager.this.f();
                                    String unused9 = VerificationManager.this.k = "";
                                    boolean unused10 = VerificationManager.this.l = false;
                                    final VerifyResult a5 = new VerifyResult.Builder().a(registerInfo.b()).b(registerInfo.a()).c(EnvEncryptUtils.a()).a();
                                    VerificationManager.this.j.post(new Runnable() {
                                        public void run() {
                                            VerificationManager.this.e.a(a5);
                                            VerificationManager.b(VerificationManager.this.x);
                                        }
                                    });
                                }
                                return false;
                            }

                            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                                VerificationManager.this.h.setVisibility(0);
                                VerificationManager.this.g.setVisibility(8);
                                super.onPageStarted(webView, str, bitmap);
                            }
                        });
                        VerificationManager.this.g.loadUrl(str);
                        ViewGroup viewGroup = (ViewGroup) VerificationManager.this.w.getParent();
                        if (viewGroup != null) {
                            viewGroup.removeAllViews();
                        }
                        AlertDialog unused5 = VerificationManager.this.i = builder.create();
                        VerificationManager.this.i.setView(VerificationManager.this.w, 0, 0, 0, 5);
                        VerificationManager.this.i.setOnKeyListener(VerificationManager.this.y);
                        VerificationManager.this.i.setOnDismissListener(VerificationManager.this.z);
                        VerificationManager.this.i.show();
                        VerificationManager.this.i.getWindow().setGravity(80);
                        VerificationManager.this.i.getWindow().clearFlags(131072);
                        return;
                    }
                    throw new IllegalArgumentException("showDialog:url should not be null");
                }
            });
        } else {
            throw new IllegalArgumentException("showDialog: registerInfo is null");
        }
    }

    /* access modifiers changed from: private */
    public void a(final int i2, final int i3) {
        if (this.u.get() == null || ((Activity) this.u.get()).isFinishing()) {
            Log.e(f23089a, "Activity is destroy");
            return;
        }
        this.j.post(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder((Context) VerificationManager.this.u.get(), 16974394);
                TextView textView = new TextView((Context) VerificationManager.this.u.get());
                textView.setText(((Activity) VerificationManager.this.u.get()).getResources().getString(i3) + Operators.BRACKET_START_STR + i2 + Operators.BRACKET_END_STR);
                textView.setPadding(0, 40, 0, 0);
                textView.setGravity(17);
                builder.setView(textView);
                AlertDialog unused = VerificationManager.this.i = builder.create();
                VerificationManager.this.i.show();
                VerificationManager.this.i.getWindow().setGravity(80);
                VerificationManager.this.i.getWindow().clearFlags(131072);
            }
        });
        this.j.postDelayed(new Runnable() {
            public void run() {
                boolean unused = VerificationManager.this.v = false;
                VerificationManager.this.f();
            }
        }, 2000);
    }

    private void h() {
        b.execute(new Runnable() {
            public void run() {
                VerificationManager.this.f.a(new ValueCallback<RegisterInfo>() {
                    /* renamed from: a */
                    public void onReceiveValue(final RegisterInfo registerInfo) {
                        if (registerInfo == null) {
                            try {
                                if (!VerificationManager.this.p) {
                                    final VerifyError a2 = VerificationManager.a(ErrorInfo.ErrorCode.ERROR_REGISTRATION_SESSION_EXCEPTION.getCode(), "registration session is exception to mRegisterInfo is null");
                                    VerificationManager.this.j.post(new Runnable() {
                                        public void run() {
                                            VerificationManager.this.e.a(a2);
                                            VerificationManager.b(VerificationManager.this.x);
                                        }
                                    });
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                VerificationManager.this.a(ErrorInfo.ErrorCode.ERROR_JSON_EXCEPTION.getCode(), ErrorInfo.getMsgIdGivenErrorCode(ErrorInfo.ErrorCode.ERROR_JSON_EXCEPTION));
                                int code = ErrorInfo.ErrorCode.ERROR_JSON_EXCEPTION.getCode();
                                final VerifyError a3 = VerificationManager.a(code, "registere:" + e.toString());
                                VerificationManager.this.j.post(new Runnable() {
                                    public void run() {
                                        VerificationManager.this.e.a(a3);
                                        VerificationManager.b(VerificationManager.this.x);
                                    }
                                });
                            }
                        } else {
                            if (TextUtils.isEmpty(VerificationManager.this.d.a())) {
                                VerificationManager.this.d.c();
                            }
                            JSONObject jSONObject = new JSONObject(VerificationManager.this.d.a());
                            JSONObject jSONObject2 = jSONObject.getJSONObject(Constants.d);
                            jSONObject2.put(Constants.B, VerificationManager.this.n);
                            jSONObject.put(Constants.d, jSONObject2);
                            jSONObject.put(Constants.f, VerificationManager.this.o);
                            jSONObject.put("eventId", registerInfo.a());
                            VerificationManager.this.d.a(jSONObject.toString());
                            VerificationManager.this.d.a(VerificationManager.this.d.a(), registerInfo, Boolean.valueOf(VerificationManager.this.l), new Verify2Callback() {
                                public void a(final VerifyResult verifyResult) {
                                    VerificationManager.this.c();
                                    VerificationManager.this.j.post(new Runnable() {
                                        public void run() {
                                            VerificationManager.this.e.a(verifyResult);
                                            VerificationManager.b(VerificationManager.this.x);
                                        }
                                    });
                                }

                                public void a(final VerifyError verifyError) {
                                    VerificationManager.this.a(verifyError.a(), verifyError.c());
                                    VerificationManager.this.j.post(new Runnable() {
                                        public void run() {
                                            VerificationManager.this.e.a(verifyError);
                                            VerificationManager.b(VerificationManager.this.x);
                                        }
                                    });
                                }

                                public void a(final String str) {
                                    String unused = VerificationManager.this.k = str;
                                    boolean unused2 = VerificationManager.this.l = false;
                                    VerificationManager.this.j.post(new Runnable() {
                                        public void run() {
                                            VerificationManager.this.b(str, registerInfo);
                                        }
                                    });
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void b(String str, RegisterInfo registerInfo) {
        if (NetWorkUtils.a((Context) this.u.get())) {
            a(str, registerInfo);
            return;
        }
        a(ErrorInfo.ErrorCode.ERROR_CONNECT_UNREACHABLE_EXCEPTION.getCode(), ErrorInfo.getMsgIdGivenErrorCode(ErrorInfo.ErrorCode.ERROR_CONNECT_UNREACHABLE_EXCEPTION));
        final VerifyError a2 = a(ErrorInfo.ErrorCode.ERROR_CONNECT_UNREACHABLE_EXCEPTION.getCode(), "network disconnected");
        this.j.post(new Runnable() {
            public void run() {
                VerificationManager.this.e.a(a2);
                VerificationManager.b(VerificationManager.this.x);
            }
        });
    }

    private void i() {
        if (!NetWorkUtils.a((Context) this.u.get())) {
            a(ErrorInfo.ErrorCode.ERROR_CONNECT_UNREACHABLE_EXCEPTION.getCode(), ErrorInfo.getMsgIdGivenErrorCode(ErrorInfo.ErrorCode.ERROR_CONNECT_UNREACHABLE_EXCEPTION));
            final VerifyError a2 = a(ErrorInfo.ErrorCode.ERROR_CONNECT_UNREACHABLE_EXCEPTION.getCode(), "network disconnected");
            this.j.post(new Runnable() {
                public void run() {
                    VerificationManager.this.e.a(a2);
                    VerificationManager.b(VerificationManager.this.x);
                }
            });
        } else if (this.i != null) {
            this.j.post(new Runnable() {
                public void run() {
                    VerificationManager.this.i.show();
                }
            });
        }
    }

    private SimpleFutureTask<Config> b(final String str) {
        if (!TextUtils.isEmpty(str)) {
            this.c = new SimpleFutureTask<>(new Callable<Config>() {
                /* renamed from: a */
                public Config call() throws Exception {
                    return VerifyRequest.b(str);
                }
            }, new SimpleFutureTask.Callback<Config>() {
                public void call(SimpleFutureTask<Config> simpleFutureTask) {
                    try {
                        Config config = (Config) simpleFutureTask.get();
                        if (config == null) {
                            int unused = VerificationManager.this.s = 50;
                            int unused2 = VerificationManager.this.r = 5000;
                        } else {
                            int unused3 = VerificationManager.this.s = config.b();
                            int unused4 = VerificationManager.this.r = config.a();
                            VerificationManager.this.t.b(Constants.P, System.currentTimeMillis());
                            VerificationManager.this.t.b(Constants.Q, VerificationManager.this.s);
                            VerificationManager.this.t.b(Constants.R, VerificationManager.this.r);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e2) {
                        e2.printStackTrace();
                    }
                    VerificationManager.this.d.a(VerificationManager.this.s, VerificationManager.this.r);
                }
            });
            b.submit(this.c);
            return this.c;
        }
        throw new IllegalArgumentException("getConfig: url is null");
    }

    public static VerifyError a(int i2, String str) {
        return new VerifyError.Build().a(i2).a(str).a();
    }

    public VerificationManager a(final String str) {
        return a((SessionRegister) new SessionRegister() {
            public RegisterInfo a() {
                try {
                    return VerifyRequest.a(str);
                } catch (VerificationException e) {
                    boolean unused = VerificationManager.this.p = true;
                    VerificationManager.this.a(e.getCode(), e.getDialogTipMsg());
                    final VerifyError a2 = VerificationManager.a(e.getCode(), e.getMessage());
                    VerificationManager.this.j.post(new Runnable() {
                        public void run() {
                            VerificationManager.this.e.a(a2);
                            VerificationManager.b(VerificationManager.this.x);
                        }
                    });
                    return null;
                }
            }
        });
    }

    public VerificationManager a(boolean z2) {
        this.n = z2;
        return this;
    }

    public VerificationManager b(boolean z2) {
        this.o = z2;
        return this;
    }

    public void c(boolean z2) {
        if (z2) {
            this.k = "";
        }
    }

    public VerificationManager a(final SessionRegister sessionRegister) {
        if (sessionRegister != null) {
            this.f = new AsyncSessionRegister() {
                public void a(ValueCallback<RegisterInfo> valueCallback) {
                    boolean unused = VerificationManager.this.p = false;
                    valueCallback.onReceiveValue(sessionRegister.a());
                }
            };
            return this;
        }
        throw new IllegalArgumentException("setSessionRegister: sessionRegister should not be null");
    }

    public VerificationManager a(AsyncSessionRegister asyncSessionRegister) {
        this.f = asyncSessionRegister;
        this.p = false;
        if (this.f != null) {
            return this;
        }
        throw new IllegalArgumentException("setAsyncSessionRegister: asyncSessionRegister should not be null");
    }

    public VerificationManager a(VerifyResultCallback verifyResultCallback) {
        this.e = verifyResultCallback;
        return this;
    }

    /* access modifiers changed from: private */
    public String a(Context context) {
        if (Build.VERSION.SDK_INT < 19) {
            return this.g.getSettings().getUserAgentString();
        }
        return WebSettings.getDefaultUserAgent(context);
    }
}
