package com.xiaomi.smarthome.international;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.bbs.BBSInitializer;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.ui.LoginPhoneActivity;
import com.xiaomi.smarthome.frame.login.ui.LoginPwdActivity;
import com.xiaomi.smarthome.frame.login.ui.LoginPwdPhoneActivity;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.frame.server_compact.ServersConfig;
import com.xiaomi.smarthome.framework.corereceiver.CoreHostApiImpl;
import com.xiaomi.smarthome.framework.login.logic.LoginManager;
import com.xiaomi.smarthome.framework.login.util.LoginUtil;
import com.xiaomi.smarthome.framework.navigate.SmartHomeLauncher;
import com.xiaomi.smarthome.framework.openapi.ApiConst;
import com.xiaomi.smarthome.framework.page.ActivityStack;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.push.PushManager;
import com.xiaomi.smarthome.international.SelectServerActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.activity.LicenseChooseActivity;
import com.xiaomi.smarthome.notishortcut.SmartNotiApi;
import com.xiaomi.smarthome.shop.utils.DisplayUtils;
import com.xiaomi.smarthome.shop.utils.LogUtil;
import com.xiaomi.smarthome.shopglobal.ShopGlobalHelper;
import com.xiaomi.smarthome.stat.STAT;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class SelectServerActivity extends BaseActivity {
    @Deprecated
    public static final String DISPLAY_MODE = "display_mode";
    public static final int DISPLAY_MODE_DIALOG = 1;
    public static final int DISPLAY_MODE_PAGE = 2;
    public static final String FROM_WHERE = "from_where";

    /* renamed from: a  reason: collision with root package name */
    private static final String f18396a = "SelectServerActivity";
    /* access modifiers changed from: private */
    public Context b;
    /* access modifiers changed from: private */
    public String c;
    /* access modifiers changed from: private */
    public SimpleAdapter d;
    private ListView e;
    /* access modifiers changed from: private */
    public XQProgressDialog f;
    private MLAlertDialog g;
    private Disposable h;
    /* access modifiers changed from: private */
    public String i = "";
    /* access modifiers changed from: private */
    public EditText j;
    /* access modifiers changed from: private */
    public CheckBox k;
    /* access modifiers changed from: private */
    public View l;
    private View m;
    int mFirstVisiblePosition = -1;
    /* access modifiers changed from: private */
    public List<ServerBean> n;
    private View o;
    private View p;
    private MLAlertDialog q;
    /* access modifiers changed from: private */
    public ServerBean r = null;
    /* access modifiers changed from: private */
    public ServerBean s = null;
    /* access modifiers changed from: private */
    public ServerBean t;
    /* access modifiers changed from: private */
    public ServerBean u;
    /* access modifiers changed from: private */
    public boolean v = false;

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(DialogInterface dialogInterface, int i2) {
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void b(DialogInterface dialogInterface, int i2) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = getApplicationContext();
        setContentView(R.layout.international_activity);
        if (getIntent() != null) {
            this.c = getIntent().getStringExtra(FROM_WHERE);
        }
        a();
        b();
        c();
        this.t = ServerCompact.a((Context) this);
    }

    private void a() {
        View findViewById = findViewById(R.id.nav_title);
        this.p = findViewById(R.id.rule_desc);
        this.l = findViewById(R.id.save_btn);
        this.m = findViewById(R.id.finish_btn);
        this.j = (EditText) findViewById(R.id.search_box);
        this.o = findViewById(R.id.normal_desc);
        this.e = (ListView) findViewById(R.id.listview);
        this.k = (CheckBox) findViewById(R.id.rule_cb);
        ((ViewGroup.MarginLayoutParams) findViewById.getLayoutParams()).setMargins(0, TitleBarUtil.a((Context) this), 0, 0);
    }

    private void b() {
        this.l.setEnabled(false);
        this.l.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SelectServerActivity.this.c(view);
            }
        });
        this.m.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SelectServerActivity.this.b(view);
            }
        });
        this.j.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public final void onFocusChange(View view, boolean z) {
                SelectServerActivity.this.a(view, z);
            }
        });
        this.j.addTextChangedListener(new SearchTextWatcher());
        this.j.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return SelectServerActivity.this.a(textView, i, keyEvent);
            }
        });
        ListView listView = this.e;
        SimpleAdapter simpleAdapter = new SimpleAdapter(this);
        this.d = simpleAdapter;
        listView.setAdapter(simpleAdapter);
        this.k.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SelectServerActivity.this.a(compoundButton, z);
            }
        });
        this.p.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SelectServerActivity.this.a(view);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        d();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        onBackPressed();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view, boolean z) {
        if (z) {
            e();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean a(TextView textView, int i2, KeyEvent keyEvent) {
        if (keyEvent == null || 66 != keyEvent.getKeyCode()) {
            return false;
        }
        if (keyEvent.getAction() == 1 && this.k.isChecked() && this.l.isEnabled()) {
            d();
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(CompoundButton compoundButton, boolean z) {
        if (!z) {
            a((Runnable) null);
        }
        this.l.setEnabled(z && ((this.v && this.s != null) || (!this.v && this.r != null)));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        startActivity(new Intent(this, LicenseChooseActivity.class));
    }

    private void a(Runnable runnable) {
        this.q = new MLAlertDialog.Builder(this).b((int) R.string.need_agree_with_the_rules_to_forward).a((int) R.string.agree_and_continue, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(runnable) {
            private final /* synthetic */ Runnable f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                SelectServerActivity.this.a(this.f$1, dialogInterface, i);
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).b();
        this.q.show();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(Runnable runnable, DialogInterface dialogInterface, int i2) {
        this.k.setChecked(true);
        if (runnable != null) {
            runnable.run();
        }
    }

    private void c() {
        this.h = ServerApi.a().b().distinctUntilChanged().map(new Function<List<ServerBean>, List<ServerBean>>() {
            /* renamed from: a */
            public List<ServerBean> apply(List<ServerBean> list) throws Exception {
                if (list.isEmpty()) {
                    return list;
                }
                if (SelectServerActivity.this.t == null) {
                    ServerBean unused = SelectServerActivity.this.t = ServerCompact.a(SHApplication.getAppContext());
                }
                if (SelectServerActivity.this.u == null) {
                    ServerBean unused2 = SelectServerActivity.this.u = SelectServerUtils.b();
                }
                ArrayList arrayList = new ArrayList(list);
                if (SelectServerActivity.this.t != null) {
                    Iterator it = arrayList.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        ServerBean serverBean = (ServerBean) it.next();
                        if (TextUtils.equals(serverBean.b, SelectServerActivity.this.t.b)) {
                            serverBean.e = true;
                            String unused3 = SelectServerActivity.this.i = serverBean.c;
                            break;
                        }
                    }
                }
                if (SelectServerActivity.this.u != null) {
                    Iterator it2 = arrayList.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        ServerBean serverBean2 = (ServerBean) it2.next();
                        if (TextUtils.equals(serverBean2.b, SelectServerActivity.this.u.b)) {
                            serverBean2.d = true;
                            break;
                        }
                    }
                }
                Locale c = ServerCompact.c(SHApplication.getAppContext());
                if (!ServersConfig.b(SHApplication.getAppContext(), c)) {
                    c = Locale.ENGLISH;
                } else if (LocaleUtil.b(c).equalsIgnoreCase("zh_tw") || LocaleUtil.b(c).equalsIgnoreCase("zh_hk")) {
                    c = Locale.SIMPLIFIED_CHINESE;
                }
                Collections.sort(arrayList, new Comparator(Collator.getInstance(c)) {
                    private final /* synthetic */ Collator f$0;

                    {
                        this.f$0 = r1;
                    }

                    public final int compare(Object obj, Object obj2) {
                        return this.f$0.compare(((ServerBean) obj).c, ((ServerBean) obj2).c);
                    }
                });
                ServerCompact.k();
                return arrayList;
            }
        }).filter($$Lambda$SelectServerActivity$Fe_slCB3A1O7Tiq2N530L5Ukb3E.INSTANCE).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<ServerBean>>() {
            /* renamed from: a */
            public void accept(List<ServerBean> list) throws Exception {
                int i;
                List unused = SelectServerActivity.this.n = list;
                if (SelectServerActivity.this.u == null || SelectServerActivity.this.t != null) {
                    i = SelectServerActivity.this.t != null ? SelectServerActivity.this.n.indexOf(SelectServerActivity.this.t) : -1;
                } else {
                    i = SelectServerActivity.this.n.indexOf(SelectServerActivity.this.u);
                    SelectServerActivity.this.l.setEnabled(true);
                    ServerBean unused2 = SelectServerActivity.this.r = SelectServerActivity.this.u;
                }
                SelectServerActivity.this.mFirstVisiblePosition = i;
                SelectServerActivity.this.showServerList(list, i);
                LogUtil.a("wangwei", "accept: " + list.size());
            }
        }, new Consumer() {
            public final void accept(Object obj) {
                SelectServerActivity.this.showError((Throwable) obj);
            }
        });
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean a(List list) throws Exception {
        return !list.isEmpty();
    }

    private void d() {
        if (this.v) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.j.getWindowToken(), 2);
        }
        ServerBean serverBean = this.v ? this.s : this.r;
        if (serverBean == null) {
            this.l.setEnabled(false);
            return;
        }
        ServerBean F = CoreApi.a().F();
        if (F == null) {
            a(serverBean, (AsyncCallback<Void, Error>) null);
        } else if (!TextUtils.equals(F.b, serverBean.b)) {
            a(serverBean, F);
        }
    }

    /* access modifiers changed from: private */
    public void a(final ServerBean serverBean, final ServerBean serverBean2) {
        String str;
        String str2;
        String str3;
        String string;
        if (CoreApi.a().q()) {
            AnonymousClass3 r0 = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    XQProgressDialog unused = SelectServerActivity.this.f = new XQProgressDialog(SelectServerActivity.this);
                    SelectServerActivity.this.f.setCancelable(true);
                    SelectServerActivity.this.f.setMessage(SelectServerActivity.this.getString(R.string.please_wait));
                    SelectServerActivity.this.f.show();
                    LoginManager.a().a((AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                        /* renamed from: a */
                        public void onSuccess(Void voidR) {
                            if (SelectServerActivity.this.f != null) {
                                SelectServerActivity.this.f.dismiss();
                            }
                            SelectServerActivity.this.a(serverBean, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                                public void onFailure(Error error) {
                                }

                                /* renamed from: a */
                                public void onSuccess(Void voidR) {
                                    Uri uri;
                                    SelectServerActivity.this.b(serverBean2, serverBean);
                                    Intent intent = new Intent(SHApplication.getAppContext(), SmartHomeLauncher.class);
                                    if (LoginUtil.a()) {
                                        uri = Uri.parse("https://home.mi.com/main/login_mi_system?account_name=" + LoginUtil.b());
                                    } else if (ServerCompact.b(serverBean)) {
                                        uri = Uri.parse("https://home.mi.com/main/login_phone_pwd");
                                    } else {
                                        uri = Uri.parse("https://home.mi.com/main/login");
                                    }
                                    intent.putExtra(ApiConst.r, 13);
                                    intent.setData(uri);
                                    SelectServerActivity.this.startActivity(intent);
                                    Process.killProcess(Process.myPid());
                                }
                            });
                        }

                        public void onFailure(Error error) {
                            if (SelectServerActivity.this.f != null) {
                                SelectServerActivity.this.f.dismiss();
                            }
                            Toast.makeText(SelectServerActivity.this.b, R.string.server_change_server_failure, 0).show();
                        }
                    });
                }
            };
            $$Lambda$SelectServerActivity$ql7aJgcK6GJHn2Q7cu7GnEbOtJg r9 = $$Lambda$SelectServerActivity$ql7aJgcK6GJHn2Q7cu7GnEbOtJg.INSTANCE;
            String str4 = "";
            boolean equals = TextUtils.equals(serverBean2.f1530a, serverBean.f1530a);
            if (equals) {
                str2 = getString(R.string.confirm_switch_region);
            } else {
                if (!(SHApplication.getStateNotifier().a() == 4)) {
                    str4 = getString(R.string.server_change_server_title_no_login);
                    str3 = getString(R.string.server_change_server_message_no_login);
                } else {
                    if (SmartHomeDeviceManager.a().w() > 0) {
                        string = getString(R.string.server_change_server_title_login_has_devices);
                        str3 = getString(R.string.server_change_server_message_login_has_devices);
                    } else {
                        string = getString(R.string.server_change_server_title_login_no_devices);
                        str3 = getString(R.string.server_change_server_message_login_no_devices);
                    }
                    str4 = string;
                }
                if (ShopGlobalHelper.a(serverBean) || BBSInitializer.a(serverBean)) {
                    str2 = str3 + String.format(getString(R.string.server_change_server_message_shop_tip), new Object[0]);
                } else {
                    str2 = str3;
                }
            }
            if (this.g != null && this.g.isShowing()) {
                this.g.dismiss();
            }
            MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
            if (equals) {
                builder.a((int) R.string.confirm, (DialogInterface.OnClickListener) r0);
            } else {
                builder.a((CharSequence) str4);
                builder.a((int) R.string.confirm_changing, (DialogInterface.OnClickListener) r0);
            }
            this.g = builder.b((CharSequence) str2).b((int) R.string.cancel, (DialogInterface.OnClickListener) r9).b();
            this.g.show();
            return;
        }
        AnonymousClass4 r02 = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                SelectServerActivity.this.a(serverBean, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                    public void onFailure(Error error) {
                    }

                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        SelectServerActivity.this.b(serverBean2, serverBean);
                        Intent intent = new Intent(SelectServerActivity.this.b, SmartHomeMainActivity.class);
                        intent.setFlags(268468224);
                        if (!TextUtils.isEmpty(SelectServerActivity.this.c) && ((LoginPhoneActivity.class.getSimpleName().equals(SelectServerActivity.this.c) || LoginPwdActivity.class.getSimpleName().equals(SelectServerActivity.this.c) || LoginPwdPhoneActivity.class.getSimpleName().equals(SelectServerActivity.this.c)) && (!SystemApi.c() || !LoginUtil.a()))) {
                            intent.putExtra(SmartHomeMainActivity.INTENT_KEY_REQUEST_CODE, 7);
                        }
                        SelectServerActivity.this.startActivity(intent);
                        Process.killProcess(Process.myPid());
                    }
                });
            }
        };
        $$Lambda$SelectServerActivity$MVkL_X920OtiSRXROFxKtdpuevE r92 = $$Lambda$SelectServerActivity$MVkL_X920OtiSRXROFxKtdpuevE.INSTANCE;
        String str5 = "";
        boolean equals2 = TextUtils.equals(serverBean2.f1530a, serverBean.f1530a);
        if (equals2) {
            str = getString(R.string.confirm_switch_region);
        } else {
            str5 = getString(R.string.server_change_server_title_no_login);
            String string2 = getString(R.string.server_change_server_message_no_login);
            if (ShopGlobalHelper.a(serverBean) || BBSInitializer.a(serverBean)) {
                str = string2 + String.format(getString(R.string.server_change_server_message_shop_tip), new Object[0]);
            } else {
                str = string2;
            }
        }
        if (this.g != null && this.g.isShowing()) {
            this.g.dismiss();
        }
        MLAlertDialog.Builder builder2 = new MLAlertDialog.Builder(this);
        if (equals2) {
            builder2.a((int) R.string.confirm, (DialogInterface.OnClickListener) r02);
        } else {
            builder2.a((CharSequence) str5);
            builder2.a((int) R.string.confirm_changing, (DialogInterface.OnClickListener) r02);
        }
        this.g = builder2.b((CharSequence) str).b((int) R.string.cancel, (DialogInterface.OnClickListener) r92).b();
        this.g.show();
    }

    /* access modifiers changed from: private */
    public void b(ServerBean serverBean, ServerBean serverBean2) {
        ActivityStack.instance.doClearOnServerChanged();
        ShopGlobalHelper.b(serverBean);
        BBSInitializer.b(serverBean);
    }

    private void e() {
        if (!this.v) {
            this.v = true;
            this.mFirstVisiblePosition = this.e.getFirstVisiblePosition();
            LogUtil.a(f18396a, "enterSearchMode: " + this.mFirstVisiblePosition);
            if (Build.VERSION.SDK_INT >= 19) {
                AutoTransition autoTransition = new AutoTransition();
                autoTransition.setDuration(70);
                TransitionManager.beginDelayedTransition((ViewGroup) getWindow().getDecorView(), autoTransition);
            }
            this.s = null;
            this.o.setVisibility(8);
            this.l.setEnabled(false);
            showServerList(Collections.emptyList(), -1);
        }
    }

    private void f() {
        if (this.v) {
            LogUtil.a(f18396a, "exitSearchMode: " + this.mFirstVisiblePosition);
            this.v = false;
            if (Build.VERSION.SDK_INT >= 19) {
                AutoTransition autoTransition = new AutoTransition();
                autoTransition.setDuration(40);
                TransitionManager.beginDelayedTransition((ViewGroup) getWindow().getDecorView(), autoTransition);
            }
            this.j.setText("");
            this.o.setVisibility(0);
            showServerList(this.n, this.mFirstVisiblePosition);
            if (this.r == null || !this.k.isChecked()) {
                this.l.setEnabled(false);
            } else {
                this.l.setEnabled(true);
            }
        }
    }

    class SearchTextWatcher implements TextWatcher {
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        SearchTextWatcher() {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (SelectServerActivity.this.n == null || charSequence == null || TextUtils.isEmpty(charSequence.toString().trim())) {
                SelectServerActivity.this.showServerList(Collections.EMPTY_LIST, -1);
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (ServerBean serverBean : SelectServerActivity.this.n) {
                if (serverBean.c.toLowerCase().contains(charSequence.toString().trim().toLowerCase())) {
                    arrayList.add(serverBean);
                }
            }
            SelectServerActivity.this.showServerList(arrayList, -1);
        }

        public void afterTextChanged(Editable editable) {
            if (SelectServerActivity.this.s != null) {
                boolean z = SelectServerActivity.this.k.isChecked() && SelectServerActivity.this.d.b.contains(SelectServerActivity.this.s);
                SelectServerActivity.this.l.setEnabled(z);
                if (!z) {
                    ServerBean unused = SelectServerActivity.this.s = null;
                }
            }
        }
    }

    public void showServerList(List<ServerBean> list, int i2) {
        if (list != null) {
            this.e.setAdapter(this.d);
            this.d.a(list);
            this.e.setSelection(i2);
        }
    }

    public void showError(Throwable th) {
        th.printStackTrace();
    }

    public void onBackPressed() {
        if (this.v) {
            this.j.clearFocus();
            ((ViewGroup) this.j.getParent()).requestFocus();
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.j.getWindowToken(), 2);
            if (this.s != null) {
                a(true, this.s);
            } else {
                f();
            }
        } else if (this.r == null || this.t == null) {
            super.onBackPressed();
        } else {
            a(false, this.r);
        }
    }

    private void a(boolean z, final ServerBean serverBean) {
        if (this.g != null) {
            this.g.dismiss();
        }
        this.g = new MLAlertDialog.Builder(this).b((int) R.string.save_previous_settings).a((int) R.string.save, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (SelectServerActivity.this.t == null) {
                    SelectServerActivity.this.a(serverBean, (AsyncCallback<Void, Error>) null);
                } else {
                    SelectServerActivity.this.a(serverBean, SelectServerActivity.this.t);
                }
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(z) {
            private final /* synthetic */ boolean f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                SelectServerActivity.this.a(this.f$1, dialogInterface, i);
            }
        }).b();
        this.g.show();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(boolean z, DialogInterface dialogInterface, int i2) {
        if (z) {
            if (this.t == null) {
                this.r = this.u;
            } else {
                this.r = null;
            }
            f();
            return;
        }
        super.onBackPressed();
    }

    /* access modifiers changed from: private */
    public void a(final ServerBean serverBean, final AsyncCallback<Void, Error> asyncCallback) {
        if (serverBean == null) {
            g();
            return;
        }
        SmartNotiApi.a(SHApplication.getAppContext()).a(serverBean);
        if (CoreApi.a().l()) {
            STAT.d.j(this.i, serverBean.c);
            CoreApi.a().a(serverBean, asyncCallback);
        } else {
            IntentFilter intentFilter = new IntentFilter(CoreHostApiImpl.e);
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this);
                    STAT.d.j(SelectServerActivity.this.i, serverBean.c);
                    CoreApi.a().a(serverBean, (AsyncCallback<Void, Error>) asyncCallback);
                }
            }, intentFilter);
        }
        PushManager.a().d();
        finish();
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(this.b);
        Intent intent = new Intent(ServerHelper.b);
        intent.putExtra("param_key", 1);
        instance.sendBroadcast(intent);
    }

    private void g() {
        finish();
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(this.b);
        Intent intent = new Intent(ServerHelper.b);
        intent.putExtra("param_key", 2);
        instance.sendBroadcast(intent);
    }

    static class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        TextView f18408a;
        ImageView b;

        ViewHolder() {
        }
    }

    class SimpleAdapter extends BaseAdapter {
        /* access modifiers changed from: private */
        public final List<ServerBean> b = new ArrayList();
        private final LayoutInflater c;
        private Context d;

        public long getItemId(int i) {
            return (long) i;
        }

        public SimpleAdapter(Context context) {
            this.d = context;
            this.c = LayoutInflater.from(this.d);
        }

        /* access modifiers changed from: private */
        public void a(List<ServerBean> list) {
            this.b.clear();
            this.b.addAll(list);
            notifyDataSetChanged();
        }

        public int getCount() {
            return this.b.size();
        }

        public Object getItem(int i) {
            return this.b.get(i);
        }

        private String a(int i) {
            return this.d.getResources().getString(i);
        }

        private int b(int i) {
            return this.d.getResources().getColor(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = this.c.inflate(R.layout.international_item, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.f18408a = (TextView) view.findViewById(R.id.text);
                viewHolder.b = (ImageView) view.findViewById(R.id.selected_mark);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
                viewHolder.f18408a.setText("");
                viewHolder.f18408a.setTextColor(b(R.color.class_Y));
            }
            ServerBean serverBean = this.b.get(i);
            String str = serverBean.c;
            if (serverBean.e) {
                str = str + " " + a((int) R.string.inter_current);
            }
            if (SelectServerActivity.this.t == null && serverBean.d) {
                str = str + " " + a((int) R.string.inter_recommend);
            }
            if (SelectServerActivity.this.v && SelectServerActivity.this.s != null && TextUtils.equals(SelectServerActivity.this.s.b, serverBean.b)) {
                viewHolder.f18408a.setTextColor(-13452608);
                viewHolder.b.setVisibility(0);
            } else if (!SelectServerActivity.this.v && SelectServerActivity.this.r != null && TextUtils.equals(SelectServerActivity.this.r.b, serverBean.b)) {
                viewHolder.f18408a.setTextColor(-13452608);
                viewHolder.b.setVisibility(0);
            } else if (!serverBean.e || ((SelectServerActivity.this.v && SelectServerActivity.this.s != null) || (!SelectServerActivity.this.v && SelectServerActivity.this.r != null))) {
                viewHolder.f18408a.setTextColor(b(R.color.class_V));
                viewHolder.b.setVisibility(4);
            } else {
                viewHolder.f18408a.setTextColor(-13452608);
                viewHolder.b.setVisibility(0);
            }
            viewHolder.f18408a.setText(str);
            view.setOnClickListener(new View.OnClickListener(serverBean, i) {
                private final /* synthetic */ ServerBean f$1;
                private final /* synthetic */ int f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onClick(View view) {
                    SelectServerActivity.SimpleAdapter.this.a(this.f$1, this.f$2, view);
                }
            });
            ViewGroup.LayoutParams layoutParams = view.findViewById(R.id.divider).getLayoutParams();
            if (layoutParams != null) {
                if (i == getCount() - 1) {
                    ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = 0;
                } else {
                    ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = DisplayUtils.a((Activity) SelectServerActivity.this, 24.0f);
                }
            }
            return view;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(ServerBean serverBean, int i, View view) {
            boolean z = (SelectServerActivity.this.v && SelectServerActivity.this.s == null) || (!SelectServerActivity.this.v && SelectServerActivity.this.r == null);
            if (!z || SelectServerActivity.this.t == null || !TextUtils.equals(SelectServerActivity.this.t.b, serverBean.b)) {
                if (z || SelectServerActivity.this.t == null || !TextUtils.equals(SelectServerActivity.this.t.b, serverBean.b)) {
                    SelectServerActivity.this.l.setEnabled(SelectServerActivity.this.k.isChecked());
                    if (SelectServerActivity.this.v) {
                        ServerBean unused = SelectServerActivity.this.s = this.b.get(i);
                    } else {
                        ServerBean unused2 = SelectServerActivity.this.r = this.b.get(i);
                    }
                    notifyDataSetChanged();
                } else {
                    SelectServerActivity.this.l.setEnabled(false);
                    if (SelectServerActivity.this.v) {
                        ServerBean unused3 = SelectServerActivity.this.s = null;
                    } else {
                        ServerBean unused4 = SelectServerActivity.this.r = null;
                    }
                    notifyDataSetChanged();
                }
            }
            if (SelectServerActivity.this.v) {
                ((InputMethodManager) SelectServerActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(SelectServerActivity.this.j.getWindowToken(), 2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.f != null) {
            this.f.dismiss();
        }
        if (this.g != null && this.g.isShowing()) {
            this.g.dismiss();
        }
        if (this.q != null && this.q.isShowing()) {
            this.q.dismiss();
        }
        LocalBroadcastManager.getInstance(this.b).sendBroadcast(new Intent(ServerHelper.c));
        if (this.h != null) {
            this.h.dispose();
        }
    }
}
