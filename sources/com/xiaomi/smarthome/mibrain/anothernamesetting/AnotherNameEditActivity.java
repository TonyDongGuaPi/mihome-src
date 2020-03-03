package com.xiaomi.smarthome.mibrain.anothernamesetting;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.multikey.PowerMultikeyApi;
import com.xiaomi.smarthome.multikey.PowerMultikeyBean;
import com.xiaomi.smarthome.newui.MyScaleAnimation;
import com.xiaomi.smarthome.newui.NameEditDialogHelper;
import com.xiaomi.smarthome.shop.utils.DisplayUtils;
import java.util.ArrayList;
import java.util.List;

public class AnotherNameEditActivity extends BaseActivity {
    public static final String KEY_ALIAS_DID = "key_alias_did";
    public static final String KEY_MULTI_BTN = "key_multi_btn";
    public static final String KEY_MULTI_BTN_BEAN_LIST = "key_multi_btn_name_bean_list";
    public static final String KEY_MULTI_BTN_POSITION = "key_multi_btn_name_position";
    public static final String VALUE_KEY = "common";
    public static final int sMaxAliasCount = 3;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Device f10636a;
    private Boolean b;
    private XQProgressDialog c;
    private AliasAdapter d;
    /* access modifiers changed from: private */
    public AnotherNameEditViewModel e;
    /* access modifiers changed from: private */
    public String f = "common";
    private PowerMultikeyBean g;
    private String h;
    /* access modifiers changed from: private */
    public ArrayList<PowerMultikeyBean> i;
    /* access modifiers changed from: private */
    public List<String> j = new ArrayList();
    /* access modifiers changed from: private */
    public List<String> k = new ArrayList();
    private Unbinder l;
    NameEditDialogHelper.NameEditListener listener = new NameEditDialogHelper.NameEditListener() {
        public String b(String str) {
            return null;
        }

        public void a(String str) {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(AnotherNameEditActivity.this.f)) {
                AnotherNameEditActivity.this.i();
                AnotherNameEditActivity.this.e.a(AnotherNameEditActivity.this.f10636a.did, str, AnotherNameEditActivity.this.f, (List<PowerMultikeyBean>) AnotherNameEditActivity.this.i);
            }
        }
    };
    private View m;
    @BindView(2131428722)
    TextView mDelete;
    @BindView(2131428728)
    ViewGroup mDeleteGroup;
    @BindView(2131428788)
    SimpleDraweeView mDeviceIcon;
    @BindView(2131428814)
    TextView mDeviceName;
    @BindView(2131429517)
    LinearLayout mGuide;
    @BindView(2131429518)
    ImageView mGuideCancel;
    @BindView(2131430508)
    ListView mListView;
    @BindView(2131433396)
    TextView mMultiBtnName;
    @BindView(2131430744)
    View mMultiGroup;
    @BindView(2131430969)
    ImageView mReturn;
    @BindView(2131430975)
    TextView mTitle;
    @BindView(2131430982)
    ImageView mTitleRightIcon;
    @BindView(2131433395)
    TextView mTvMultiBtnKey;
    /* access modifiers changed from: private */
    public boolean n = false;

    class AliasAdapter extends BaseAdapter {
        private Context b;

        public long getItemId(int i) {
            return (long) i;
        }

        public class ViewHolder_ViewBinding implements Unbinder {

            /* renamed from: a  reason: collision with root package name */
            private ViewHolder f10642a;

            @UiThread
            public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
                this.f10642a = viewHolder;
                viewHolder.mNameTV = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_alias, "field 'mNameTV'", TextView.class);
                viewHolder.mCheckBox = (CheckBox) Utils.findRequiredViewAsType(view, R.id.ckb_edit_selected, "field 'mCheckBox'", CheckBox.class);
            }

            @CallSuper
            public void unbind() {
                ViewHolder viewHolder = this.f10642a;
                if (viewHolder != null) {
                    this.f10642a = null;
                    viewHolder.mNameTV = null;
                    viewHolder.mCheckBox = null;
                    return;
                }
                throw new IllegalStateException("Bindings already cleared.");
            }
        }

        public AliasAdapter(Context context) {
            this.b = context;
        }

        public int getCount() {
            return AnotherNameEditActivity.this.j.size();
        }

        public Object getItem(int i) {
            return AnotherNameEditActivity.this.j.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            int i2 = 0;
            if (view == null) {
                view = LayoutInflater.from(this.b).inflate(R.layout.voice_another_name_list_item, AnotherNameEditActivity.this.mListView, false);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            CheckBox checkBox = viewHolder.mCheckBox;
            if (!AnotherNameEditActivity.this.n) {
                i2 = 8;
            }
            checkBox.setVisibility(i2);
            viewHolder.mNameTV.setText((CharSequence) AnotherNameEditActivity.this.j.get(i));
            CheckBox checkBox2 = viewHolder.mCheckBox;
            List access$700 = AnotherNameEditActivity.this.k;
            checkBox2.setChecked(access$700.contains(i + JSMethod.NOT_SET + ((String) AnotherNameEditActivity.this.j.get(i))));
            return view;
        }

        class ViewHolder {
            @BindView(2131428355)
            CheckBox mCheckBox;
            @BindView(2131433188)
            TextView mNameTV;

            public ViewHolder(View view) {
                ButterKnife.bind((Object) this, view);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_another_name_edit);
        this.l = ButterKnife.bind((Activity) this);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        String stringExtra = intent.getStringExtra(KEY_ALIAS_DID);
        if (TextUtils.isEmpty(stringExtra)) {
            finish();
            return;
        }
        this.f10636a = SmartHomeDeviceManager.a().b(stringExtra);
        if (this.f10636a == null) {
            finish();
            return;
        }
        this.b = Boolean.valueOf(intent.getBooleanExtra(KEY_MULTI_BTN, false));
        if (this.b.booleanValue()) {
            int intExtra = intent.getIntExtra(KEY_MULTI_BTN_POSITION, -1);
            this.i = intent.getParcelableArrayListExtra(KEY_MULTI_BTN_BEAN_LIST);
            if (this.i == null || intExtra == -1) {
                finish();
                return;
            }
            this.h = PowerMultikeyApi.a((List<PowerMultikeyBean>) this.i, intExtra);
            if (intExtra < this.i.size()) {
                this.g = this.i.get(intExtra);
            }
            if (this.g == null || TextUtils.isEmpty(this.h)) {
                finish();
                return;
            }
            this.f = this.g.b();
        }
        if (TextUtils.isEmpty(this.f)) {
            finish();
            return;
        }
        a();
        b();
    }

    public void onBackPressed() {
        if (this.mGuide.getVisibility() == 0) {
            this.mGuide.setVisibility(8);
        } else if (this.n) {
            l();
        } else {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        j();
        if (this.l != null) {
            this.l.unbind();
        }
    }

    private void a() {
        this.mGuideCancel.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                AnotherNameEditActivity.this.f(view);
            }
        });
        this.mDeviceName.setText(this.f10636a.getName());
        DeviceFactory.b(this.f10636a.model, this.mDeviceIcon);
        if (this.b.booleanValue()) {
            g();
        }
        this.mDeleteGroup.setVisibility(8);
        this.mDelete.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                AnotherNameEditActivity.this.e(view);
            }
        });
        f();
        d();
        e();
        this.d = new AliasAdapter(this);
        this.mListView.setAdapter(this.d);
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                AnotherNameEditActivity.this.a(adapterView, view, i, j);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void f(View view) {
        this.mGuide.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void e(View view) {
        if (this.k.size() > 0 && !TextUtils.isEmpty(this.f)) {
            a(this.mDelete, new Runnable() {
                public final void run() {
                    AnotherNameEditActivity.this.m();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(AdapterView adapterView, View view, int i2, long j2) {
        String str = (String) adapterView.getAdapter().getItem(i2);
        if (!TextUtils.isEmpty(str) && this.n) {
            AliasAdapter.ViewHolder viewHolder = (AliasAdapter.ViewHolder) view.getTag();
            viewHolder.mCheckBox.toggle();
            if (viewHolder.mCheckBox.isChecked()) {
                List<String> list = this.k;
                list.add((i2 - this.mListView.getHeaderViewsCount()) + JSMethod.NOT_SET + str);
                return;
            }
            List<String> list2 = this.k;
            list2.remove((i2 - this.mListView.getHeaderViewsCount()) + JSMethod.NOT_SET + str);
        }
    }

    private void b() {
        this.e = (AnotherNameEditViewModel) ViewModelProviders.a((FragmentActivity) this).a(AnotherNameEditViewModel.class);
        this.e.a().observe(this, new Observer() {
            public final void onChanged(Object obj) {
                AnotherNameEditActivity.this.a((List) obj);
            }
        });
        c();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(List list) {
        this.j.clear();
        this.j.addAll(list);
        if (this.j.size() > 0) {
            this.m.setEnabled(true);
        } else {
            this.m.setEnabled(false);
        }
        this.d.notifyDataSetChanged();
        j();
    }

    private void c() {
        i();
        this.e.a(this.f10636a.did, this.f);
    }

    private void d() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.voice_another_name_list_header, this.mListView, false);
        this.m = inflate.findViewById(R.id.edit);
        this.m.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                AnotherNameEditActivity.this.d(view);
            }
        });
        this.mListView.addHeaderView(inflate);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void d(View view) {
        if (this.n) {
            l();
        } else {
            k();
        }
    }

    private void e() {
        View inflate = LayoutInflater.from(getApplicationContext()).inflate(R.layout.voice_another_name_list_footer, this.mListView, false);
        inflate.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                AnotherNameEditActivity.this.c(view);
            }
        });
        this.mListView.addFooterView(inflate);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        if (this.j.size() >= 3) {
            new MLAlertDialog.Builder(this).b((CharSequence) getResources().getQuantityString(R.plurals.voice_another_name_max, 3, new Object[]{3})).b((int) R.string.i_know, (DialogInterface.OnClickListener) null).d(true).a(getResources().getColor(R.color.black_70_transparent), -2).d();
            return;
        }
        NameEditDialogHelper.a(this, "", getString(R.string.voice_another_name_add), getString(R.string.voice_another_name_add_hint), this.listener);
    }

    private void f() {
        this.mTitle.setText(R.string.voice_another_name_setting);
        this.mReturn.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                AnotherNameEditActivity.this.b(view);
            }
        });
        this.mTitleRightIcon.setImageResource(R.drawable.mihome_help_icon);
        this.mTitleRightIcon.setVisibility(0);
        this.mTitleRightIcon.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                AnotherNameEditActivity.this.a(view);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        finish();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        h();
    }

    private void g() {
        this.mMultiGroup.setVisibility(0);
        this.mTvMultiBtnKey.setText(this.h);
        if (TextUtils.isEmpty(this.g.a())) {
            this.mMultiBtnName.setText(R.string.unset);
        } else {
            this.mMultiBtnName.setText(this.g.a());
        }
    }

    private void h() {
        if (isValid()) {
            this.mGuide.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public void i() {
        j();
        this.c = XQProgressDialog.a(this, (CharSequence) null, getString(R.string.loading));
    }

    private void j() {
        if (this.c != null && this.c.isShowing()) {
            this.c.dismiss();
        }
    }

    private void k() {
        this.n = true;
        this.mDeleteGroup.setVisibility(0);
        ObjectAnimator.ofFloat(this.mDeleteGroup, "translationY", new float[]{(float) DisplayUtils.a((Activity) this, 67.0f), 0.0f}).start();
        this.d.notifyDataSetChanged();
    }

    private void l() {
        this.n = false;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mDeleteGroup, "translationY", new float[]{0.0f, (float) DisplayUtils.a((Activity) this, 67.0f)});
        ofFloat.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                AnotherNameEditActivity.this.mDeleteGroup.setVisibility(8);
            }
        });
        ofFloat.start();
        this.d.notifyDataSetChanged();
    }

    private void a(View view, final Runnable runnable) {
        MyScaleAnimation myScaleAnimation = new MyScaleAnimation(1.1f, 1, 0.5f, 1, 0.5f);
        myScaleAnimation.setDuration(100);
        myScaleAnimation.setInterpolator(new LinearInterpolator());
        myScaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                runnable.run();
            }
        });
        view.startAnimation(myScaleAnimation);
    }

    /* access modifiers changed from: private */
    public void m() {
        i();
        this.e.a(this.f10636a.did, this.k, this.f, (List<PowerMultikeyBean>) this.i);
        l();
    }
}
