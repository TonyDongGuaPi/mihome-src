package com.xiaomi.smarthome.miio.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.miui.tsmclient.entity.CardInfo;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.bbs.BBSInitializer;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.PluginRuntimeManager;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.international.ServerApi;
import com.xiaomi.smarthome.language.LanguageModel;
import com.xiaomi.smarthome.language.LanguageSupport;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.LanguageUtil;
import com.xiaomi.smarthome.shopglobal.ShopGlobalHelper;
import com.xiaomi.smarthome.stat.STAT;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Locale;

public class LanguageSettingActivity extends BaseActivity {
    public static final String SETTING_PREF = "language_setting";

    /* renamed from: a  reason: collision with root package name */
    private RecyclerView.Adapter f11767a;
    /* access modifiers changed from: private */
    public int b = -1;
    /* access modifiers changed from: private */
    public XQProgressDialog c;
    boolean isDefault;
    SharedPreferences language_setting;
    RecyclerView mList;

    private class SimpleAdapter extends RecyclerView.Adapter<MyViewHolder> implements View.OnClickListener {
        private List<LanguageModel> b = LanguageSupport.a();
        private LayoutInflater c;
        private Locale d = CoreApi.a().I();

        public SimpleAdapter(Context context) {
            Locale locale;
            this.c = LayoutInflater.from(context.getApplicationContext());
            if (Build.VERSION.SDK_INT >= 24) {
                locale = Resources.getSystem().getConfiguration().getLocales().get(0);
            } else {
                locale = Locale.getDefault();
            }
            boolean b2 = SharePrefsManager.b(SHApplication.getAppContext(), "language_setting", CardInfo.KEY_IS_DEFAULT, true);
            if (LanguageUtil.K.contains(locale)) {
                if (b2) {
                    this.d = LanguageUtil.G;
                } else if (LanguageUtil.a(Locale.US, this.d)) {
                    this.d = LanguageUtil.G;
                }
            } else if (!LanguageUtil.a(LanguageUtil.G, this.d)) {
            } else {
                if (b2) {
                    this.d = locale;
                    SharePrefsManager.a(SHApplication.getAppContext(), "language_setting", CardInfo.KEY_IS_DEFAULT, true);
                    return;
                }
                this.d = Locale.US;
            }
        }

        /* renamed from: a */
        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View inflate = this.c.inflate(R.layout.item_language_setting, viewGroup, false);
            inflate.setOnClickListener(this);
            return new MyViewHolder(inflate);
        }

        /* renamed from: a */
        public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
            myViewHolder.itemView.setTag(Integer.valueOf(i));
            myViewHolder.itemView.setOnClickListener(this);
            int i2 = this.b.get(i).d;
            myViewHolder.f11771a.setText(i2);
            if (LanguageUtil.a(LanguageUtil.G, this.d)) {
                if (LanguageSettingActivity.this.isDefault && i == 0) {
                    myViewHolder.f11771a.setTextColor(-13452608);
                    return;
                } else if (!LanguageSettingActivity.this.isDefault && LanguageUtil.a(this.b.get(i).f18427a, Locale.US)) {
                    myViewHolder.f11771a.setTextColor(-13452608);
                    return;
                }
            }
            if (LanguageUtil.a(this.b.get(i).f18427a, this.d)) {
                myViewHolder.f11771a.setTextColor(-13452608);
                int unused = LanguageSettingActivity.this.b = i2;
            }
        }

        public int getItemCount() {
            if (this.b == null) {
                return 0;
            }
            return this.b.size();
        }

        public void onClick(View view) {
            int intValue = ((Integer) view.getTag()).intValue();
            if (intValue >= 0 && intValue < this.b.size()) {
                LanguageSettingActivity.this.setLanguage(this.b.get(intValue).c, this.b.get(intValue).d);
            }
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public TextView f11771a;

        public MyViewHolder(View view) {
            super(view);
            this.f11771a = (TextView) view.findViewById(R.id.text);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.language_setting_layout);
        this.language_setting = SharePrefsManager.a((Context) this, "language_setting");
        this.isDefault = this.language_setting.getBoolean(CardInfo.KEY_IS_DEFAULT, true);
        this.f11767a = new SimpleAdapter(this);
        this.mList = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        this.mList.setLayoutManager(linearLayoutManager);
        this.mList.setAdapter(this.f11767a);
        this.mList.setHasFixedSize(true);
        this.mList.setFocusable(false);
        ((NestedScrollView) findViewById(R.id.nested_scroll_parent)).requestFocus();
        if (ShopGlobalHelper.a((Context) SHApplication.getApplication()) || BBSInitializer.a((Context) SHApplication.getApplication())) {
            findViewById(R.id.shop_tip).setVisibility(0);
            findViewById(R.id.shop_tip_close).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    LanguageSettingActivity.this.findViewById(R.id.shop_tip).setVisibility(8);
                }
            });
        } else {
            findViewById(R.id.shop_tip).setVisibility(8);
        }
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LanguageSettingActivity.this.finish();
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.settings_language);
    }

    private static class MyAsyncCallback extends AsyncCallback<Void, Error> {

        /* renamed from: a  reason: collision with root package name */
        private final WeakReference<LanguageSettingActivity> f11770a;
        private final int b;

        private MyAsyncCallback(LanguageSettingActivity languageSettingActivity, int i) {
            this.f11770a = new WeakReference<>(languageSettingActivity);
            this.b = i;
        }

        /* renamed from: a */
        public void onSuccess(Void voidR) {
            LanguageSettingActivity languageSettingActivity = (LanguageSettingActivity) this.f11770a.get();
            if (languageSettingActivity.isValid()) {
                PluginApi.getInstance().exitProcess(RunningProcess.PLUGIN0);
                PluginApi.getInstance().exitProcess(RunningProcess.PLUGIN1);
                PluginApi.getInstance().exitProcess(RunningProcess.PLUGIN2);
                PluginApi.getInstance().exitProcess(RunningProcess.PLUGIN3);
                PluginRuntimeManager.getInstance().exitAllFrameProcess();
                if (languageSettingActivity.c != null) {
                    languageSettingActivity.c.dismiss();
                }
                languageSettingActivity.setResult(-1);
                Intent intent = new Intent(SHApplication.getAppContext(), SmartHomeMainActivity.class);
                intent.setFlags(335544320);
                SHApplication.getAppContext().startActivity(intent);
                if (!(this.b == languageSettingActivity.b || languageSettingActivity.b == -1)) {
                    STAT.d.k(languageSettingActivity.getString(languageSettingActivity.b), languageSettingActivity.getString(this.b));
                }
                languageSettingActivity.finish();
            }
        }

        public void onFailure(Error error) {
            LanguageSettingActivity languageSettingActivity = (LanguageSettingActivity) this.f11770a.get();
            if (languageSettingActivity.isValid()) {
                if (languageSettingActivity.c != null) {
                    languageSettingActivity.c.dismiss();
                }
                ToastUtil.a((int) R.string.mi_brain_setting_fail);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setLanguage(int i, int i2) {
        this.c = new XQProgressDialog(this);
        this.c.setCancelable(false);
        this.c.setMessage(getResources().getString(R.string.loading_share_info));
        this.c.show();
        LanguageUtil.a(i, (AsyncCallback<Void, Error>) new MyAsyncCallback(i2));
        ServerApi.a().a(true);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        try {
            if (this.c != null) {
                this.c.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
