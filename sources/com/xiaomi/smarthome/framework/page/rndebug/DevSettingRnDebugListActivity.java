package com.xiaomi.smarthome.framework.page.rndebug;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.baseui.ToastManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.page.DevelopSettingRNActivity;
import com.xiaomi.smarthome.framework.page.DevelopSharePreManager;
import com.xiaomi.smarthome.framework.page.rndebug.RnDebugListAdapter;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.setting.RnPluginDebugDeviceMock;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class DevSettingRnDebugListActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f16932a = "debug_http_host";
    private static final int g = 10000;
    private static final int h = 10001;
    /* access modifiers changed from: private */
    public MLAlertDialog b = null;
    /* access modifiers changed from: private */
    public List<JSONObject> c = new ArrayList();
    /* access modifiers changed from: private */
    public RnDebugListAdapter d;
    /* access modifiers changed from: private */
    public JSONObject e;
    /* access modifiers changed from: private */
    public boolean f = false;
    /* access modifiers changed from: private */
    public int i = -1;
    @BindView(2131430982)
    ImageView ivHeadRight;
    @BindView(2131430203)
    ImageView ivPluginDebug;
    @BindView(2131430378)
    View layoutOtherSetting;
    @BindView(2131430379)
    View layoutRnSettingIp;
    @BindView(2131432596)
    SlideRecyclerView srvRnDebugPluginModelList;
    @BindView(2131430975)
    TextView tvHeadTitle;
    @BindView(2131433366)
    TextView tvIpDetail;
    @BindView(2131433428)
    TextView tvPluginDebug;
    @BindView(2131430969)
    View viewHeadLeft;
    @BindView(2131430340)
    View viewWindow;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, DevSettingRnDebugListActivity.class));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_dev_setting_rn_debug_list);
        ButterKnife.bind((Activity) this);
        a();
        d();
    }

    private void a() {
        this.viewHeadLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DevSettingRnDebugListActivity.this.e();
            }
        });
        this.tvHeadTitle.setText(getString(R.string.string_rn_debug_dev_setting));
        this.ivHeadRight.setVisibility(0);
        this.ivHeadRight.setImageResource(R.drawable.alarm_direction_add_normal);
        this.f = DevelopSharePreManager.a().g();
        this.tvIpDetail.setText(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(f16932a, ""));
        this.ivHeadRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!DevSettingRnDebugListActivity.this.f) {
                    ToastManager.a().a("请开启开发模式");
                } else {
                    DevelopSettingRNActivity.startActivityForResult(DevSettingRnDebugListActivity.this, 10000);
                }
            }
        });
        this.ivPluginDebug.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean unused = DevSettingRnDebugListActivity.this.f = !DevSettingRnDebugListActivity.this.f;
                DevelopSharePreManager.a().d(DevSettingRnDebugListActivity.this.f);
                DevSettingRnDebugListActivity.this.b();
            }
        });
        this.layoutRnSettingIp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!DevSettingRnDebugListActivity.this.f) {
                    ToastManager.a().a("请开启开发模式");
                } else {
                    DevSettingRnDebugListActivity.this.g();
                }
            }
        });
        this.layoutOtherSetting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!DevSettingRnDebugListActivity.this.f) {
                    ToastManager.a().a("请开启开发模式");
                } else {
                    RnDebugOtherSettingActivity.startActivity(DevSettingRnDebugListActivity.this);
                }
            }
        });
        c();
    }

    /* access modifiers changed from: private */
    public void b() {
        RnPluginLog.a("DevSettingRnDebugListActivity  IsDebugEnable: " + this.f);
        if (this.f) {
            this.viewWindow.setBackgroundColor(getResources().getColor(R.color.white));
            this.ivPluginDebug.setBackgroundResource(R.drawable.btn_power_on);
            this.tvPluginDebug.setText(getString(R.string.string_rn_debug_dev_pattern_on));
        } else {
            this.viewWindow.setBackgroundColor(getResources().getColor(R.color.white_60_transparent));
            this.ivPluginDebug.setBackgroundResource(R.drawable.btn_power_off);
            this.tvPluginDebug.setText(getString(R.string.string_rn_debug_dev_pattern_off));
        }
        this.d.a(this.f);
        this.d.notifyDataSetChanged();
    }

    private void c() {
        this.srvRnDebugPluginModelList.setLayoutManager(new LinearLayoutManager(this, 1, false));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, 1);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.std_lite_grid_divider));
        this.srvRnDebugPluginModelList.addItemDecoration(dividerItemDecoration);
        this.d = new RnDebugListAdapter(this, this.c);
        this.srvRnDebugPluginModelList.setAdapter(this.d);
        this.d.a((RnDebugListAdapter.OnRnDebugListClickListener) new RnDebugListAdapter.OnRnDebugListClickListener() {
            public void a(View view, int i) {
                int unused = DevSettingRnDebugListActivity.this.i = i;
                DevelopSettingRNActivity.startActivityForResult(DevSettingRnDebugListActivity.this, 10001, ((JSONObject) DevSettingRnDebugListActivity.this.c.get(i)).toString());
            }

            public void b(View view, int i) {
                DevSettingRnDebugListActivity.this.c.remove(i);
                DevSettingRnDebugListActivity.this.srvRnDebugPluginModelList.closeMenu();
            }

            public void a(View view, int i, boolean z) {
                try {
                    ((JSONObject) DevSettingRnDebugListActivity.this.c.get(i)).put(RnDebugConstant.c, z);
                } catch (JSONException e) {
                    RnPluginLog.a(e.toString());
                }
            }

            public void a(View view) {
                if (DevSettingRnDebugListActivity.this.f() == 1) {
                    Observable.create(new Observable.OnSubscribe<String>() {
                        /* renamed from: a */
                        public void call(Subscriber<? super String> subscriber) {
                            RnDebugFileUtil.a(DevSettingRnDebugListActivity.this, DevSettingRnDebugListActivity.this.c);
                            if (DevSettingRnDebugListActivity.this.e != null) {
                                DevelopSharePreManager.a().a(DevSettingRnDebugListActivity.this.e.optString(RnDebugConstant.f16953a));
                            }
                            subscriber.onNext("");
                        }
                    }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
                        /* renamed from: a */
                        public void call(String str) {
                            Intent unused = DevSettingRnDebugListActivity.this.a(RnPluginDebugDeviceMock.f22091a, DevSettingRnDebugListActivity.this.getContext(), new Bundle(), true);
                        }
                    }, (Action1<Throwable>) new Action1<Throwable>() {
                        /* renamed from: a */
                        public void call(Throwable th) {
                            RnPluginLog.a(th.toString());
                        }
                    });
                } else {
                    ToastUtil.a((CharSequence) "请选择列表中的一项进行调试");
                }
            }
        });
    }

    private void d() {
        Observable.create(new Observable.OnSubscribe<String>() {
            /* renamed from: a */
            public void call(Subscriber<? super String> subscriber) {
                List unused = DevSettingRnDebugListActivity.this.c = RnDebugFileUtil.a((Context) DevSettingRnDebugListActivity.this);
                subscriber.onNext("");
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            /* renamed from: a */
            public void call(String str) {
                DevSettingRnDebugListActivity.this.d.a((List<JSONObject>) DevSettingRnDebugListActivity.this.c);
                DevSettingRnDebugListActivity.this.b();
            }
        }, (Action1<Throwable>) new Action1<Throwable>() {
            /* renamed from: a */
            public void call(Throwable th) {
                RnPluginLog.a(th.toString());
            }
        });
    }

    /* access modifiers changed from: private */
    public void e() {
        Observable.create(new Observable.OnSubscribe<String>() {
            /* renamed from: a */
            public void call(Subscriber<? super String> subscriber) {
                RnDebugFileUtil.a(DevSettingRnDebugListActivity.this, DevSettingRnDebugListActivity.this.c);
                subscriber.onNext("");
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            /* renamed from: a */
            public void call(String str) {
                DevSettingRnDebugListActivity.this.finish();
            }
        }, (Action1<Throwable>) new Action1<Throwable>() {
            /* renamed from: a */
            public void call(Throwable th) {
                RnPluginLog.a(th.toString());
            }
        });
    }

    /* access modifiers changed from: private */
    public int f() {
        int size = this.c.size();
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            try {
                if (this.c.get(i3).getBoolean(RnDebugConstant.c)) {
                    this.e = this.c.get(i3);
                    i2++;
                }
            } catch (JSONException e2) {
                RnPluginLog.a(e2.toString());
            }
        }
        return i2;
    }

    /* access modifiers changed from: private */
    public void g() {
        String trim = this.tvIpDetail.getText().toString().trim();
        if (!TextUtils.isEmpty(trim)) {
            trim = trim.split(":")[0];
        }
        if (this.b == null) {
            MLAlertDialog.Builder a2 = new MLAlertDialog.Builder(this).a((CharSequence) "IP地址").b((CharSequence) "输入电脑的IP地址，您的手机可以不通过USB连接电脑，就能调试RN扩展程序！").a("请输入您电脑的ip地址", true).a(false).b((CharSequence) "取消", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    DevSettingRnDebugListActivity.this.b.dismiss();
                }
            }).a((CharSequence) "确定", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    String trim = DevSettingRnDebugListActivity.this.b.getInputView().getText().toString().trim();
                    DevSettingRnDebugListActivity.this.b.dismiss();
                    if (!TextUtils.isEmpty(trim)) {
                        trim = trim + ":8081";
                    }
                    DevSettingRnDebugListActivity.this.tvIpDetail.setText(trim);
                    SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(DevSettingRnDebugListActivity.this.getApplicationContext()).edit();
                    edit.putString(DevSettingRnDebugListActivity.f16932a, trim);
                    edit.apply();
                }
            });
            if (!TextUtils.isEmpty(trim)) {
                a2.a().setText(trim);
            }
            this.b = a2.d();
            return;
        }
        if (!TextUtils.isEmpty(trim)) {
            this.b.getInputView().setText(trim);
        }
        this.b.show();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, @Nullable Intent intent) {
        Bundle bundleExtra;
        super.onActivityResult(i2, i3, intent);
        if (intent != null && (bundleExtra = intent.getBundleExtra("data")) != null) {
            String string = bundleExtra.getString("jsonData");
            if (!TextUtils.isEmpty(string)) {
                JSONObject jSONObject = null;
                try {
                    jSONObject = new JSONObject(string);
                } catch (JSONException e2) {
                    RnPluginLog.a(e2.toString());
                }
                if (jSONObject != null) {
                    if (10001 == i2) {
                        a(jSONObject, this.c.get(this.i));
                    } else if (10000 == i2) {
                        this.c.add(0, jSONObject);
                    }
                    this.d.a(this.c);
                    this.d.notifyDataSetChanged();
                }
            }
        }
    }

    private void a(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject != null) {
            if (jSONObject2 == null) {
                jSONObject2 = new JSONObject();
            }
            try {
                jSONObject2.put(RnDebugConstant.f16953a, jSONObject.optString(RnDebugConstant.f16953a));
                jSONObject2.put(RnDebugConstant.b, jSONObject.optString(RnDebugConstant.b));
                jSONObject2.put(RnDebugConstant.c, jSONObject.optBoolean(RnDebugConstant.c));
                jSONObject2.put(RnDebugConstant.d, jSONObject.optString(RnDebugConstant.d));
                jSONObject2.put(RnDebugConstant.e, jSONObject.optBoolean(RnDebugConstant.e));
            } catch (JSONException e2) {
                RnPluginLog.b(e2.toString());
            }
        }
    }

    /* access modifiers changed from: private */
    public Intent a(String str, Context context, Bundle bundle, boolean z) {
        if (CoreApi.a().c(str)) {
            PluginRecord d2 = CoreApi.a().d(str);
            if (z) {
                Intent intent = new Intent();
                if (bundle != null) {
                    intent.putExtras(bundle);
                }
                Context context2 = context;
                PluginApi.getInstance().sendMessage(context2, d2, 1, intent, RnPluginDebugDeviceMock.b(), (RunningProcess) null, false, new PluginApi.SendMessageCallback() {
                    public void onDownInfoSuccess(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                    }

                    public void onDownloadCancel() {
                    }

                    public void onDownloadFailure(PluginError pluginError) {
                    }

                    public void onDownloadProgress(PluginRecord pluginRecord, float f) {
                    }

                    public void onDownloadStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                    }

                    public void onDownloadSuccess(PluginRecord pluginRecord) {
                    }

                    public void onInstallSuccess(PluginRecord pluginRecord) {
                    }

                    public void onSendCancel() {
                    }

                    public void onSendFailure(Error error) {
                    }

                    public void onSendSuccess(Bundle bundle) {
                    }
                }, new PluginApi.SendMessageHandle());
            } else {
                Intent intent2 = new Intent();
                if (bundle != null) {
                    intent2.putExtras(bundle);
                }
                AnonymousClass16 r14 = new AnonymousClass1SendMessageCallbackWrapper() {
                    public void onDownloadCancel() {
                    }

                    public void onDownloadFailure(PluginError pluginError) {
                    }

                    public void onDownloadProgress(PluginRecord pluginRecord, float f) {
                    }

                    public void onDownloadStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                    }

                    public void onDownloadSuccess(PluginRecord pluginRecord) {
                    }

                    public void onSendCancel() {
                    }

                    public void onSendFailure(Error error) {
                    }

                    public void onSendSuccess(Bundle bundle) {
                    }
                };
                r14.b = PluginApi.getInstance().sendMessage(context, d2, 1, intent2, RnPluginDebugDeviceMock.b(), (RunningProcess) null, false, r14);
            }
        }
        Log.e("Device_Renderer", str + ", 0 - " + System.currentTimeMillis());
        return null;
    }

    public void onBackPressed() {
        super.onBackPressed();
        e();
    }
}
