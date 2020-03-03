package com.xiaomi.smarthome.scene;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.XQProgressHorizontalDialog;
import com.xiaomi.smarthome.library.common.util.PermssionUtil;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.scene.action.BaseInnerAction;
import com.xiaomi.smarthome.scene.action.InnerActionCommon;
import com.xiaomi.smarthome.scene.condition.BaseInnerCondition;
import com.xiaomi.smarthome.scene.condition.ELLocationInnerCondition;
import com.xiaomi.smarthome.scene.condition.IZatGeoFencingInnerCondition;
import com.xiaomi.smarthome.scene.condition.InnerConditionCommon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SmartHomeSceneDetailActivity extends BaseActivity {
    public static final String EXTRA_GROUP_ID = "extra_group_id";
    public static final String EXTRA_INDEX = "extra_index";
    public static final String EXTRA_SELECTED_DID = "extra_selected_did";
    public static final String EXTRA_SELECTED_INDEX = "extra_selected_title";
    public static final String EXTRA_SHOW_GROUP_INFO = "show_group_info";
    public static final String EXTRA_TITLE = "extra_title";
    public static final int GET_CONDITION_DETAIL = 101;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public BaseInnerCondition f21437a;
    /* access modifiers changed from: private */
    public BaseInnerAction b;
    private ItemAdapter c;
    /* access modifiers changed from: private */
    public int d = -1;
    /* access modifiers changed from: private */
    public int e = -1;
    /* access modifiers changed from: private */
    public boolean f = true;
    private int g;
    /* access modifiers changed from: private */
    public List<Integer> h = new ArrayList();
    /* access modifiers changed from: private */
    public HashMap<Integer, String> i = new HashMap<>();
    /* access modifiers changed from: private */
    public List<String> j = new ArrayList();
    /* access modifiers changed from: private */
    public String k;
    @BindView(2131430969)
    ImageView mBackBtn;
    @BindView(2131428548)
    ListView mContentList;
    @BindView(2131430975)
    TextView mTitle;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.smarthome_scene_detail);
        ButterKnife.bind((Activity) this);
        this.mBackBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SmartHomeSceneDetailActivity.this.finish();
            }
        });
        this.f21437a = CreateSceneManager.a().h();
        this.b = CreateSceneManager.a().j();
        String stringExtra = getIntent().getStringExtra("extra_title");
        this.e = getIntent().getIntExtra("extra_selected_title", -1);
        if (this.e == -1) {
            this.k = getIntent().getStringExtra(EXTRA_SELECTED_DID);
        }
        this.g = getIntent().getIntExtra("extra_group_id", -1);
        this.f = getIntent().getBooleanExtra("show_group_info", true);
        this.mTitle.setText(stringExtra);
        this.c = new ItemAdapter();
        int i2 = 0;
        this.mContentList.addHeaderView(LayoutInflater.from(this).inflate(R.layout.common_list_space_empty, this.mContentList, false));
        this.mContentList.setAdapter(this.c);
        if (this.f21437a != null) {
            if (!(this.f21437a instanceof InnerConditionCommon) || ((InnerConditionCommon) this.f21437a).h() == null) {
                this.f = false;
            }
            if (this.f) {
                ArrayList arrayList = new ArrayList();
                for (int valueOf : ((InnerConditionCommon) this.f21437a).h()) {
                    arrayList.add(Integer.valueOf(valueOf));
                }
                this.f21437a.b();
                ArrayList arrayList2 = new ArrayList();
                if (this.e != -1) {
                    int[] f2 = this.f21437a.f();
                    int length = f2.length;
                    int i3 = 0;
                    while (true) {
                        if (i3 >= length) {
                            break;
                        }
                        Integer valueOf2 = Integer.valueOf(f2[i3]);
                        if (valueOf2.intValue() == this.e) {
                            this.d = ((InnerConditionCommon) this.f21437a).e(valueOf2.intValue());
                            break;
                        }
                        i3++;
                    }
                }
                generateDisplayIds(arrayList2, arrayList);
            } else {
                ArrayList arrayList3 = new ArrayList();
                ArrayList arrayList4 = new ArrayList();
                if (this.g == -1) {
                    int i4 = 0;
                    while (true) {
                        if (i4 >= ((this.f21437a == null || this.f21437a.f() == null) ? 0 : this.f21437a.f().length)) {
                            break;
                        }
                        arrayList3.add(Integer.valueOf(this.f21437a.f()[i4]));
                        i4++;
                    }
                } else {
                    for (int i5 = 0; i5 < ((InnerConditionCommon) this.f21437a).f().length; i5++) {
                        if (this.g == ((InnerConditionCommon) this.f21437a).e(i5)) {
                            arrayList3.add(Integer.valueOf(this.f21437a.f()[i5]));
                        }
                    }
                }
                for (int i6 = 0; i6 < arrayList3.size(); i6++) {
                    if (CreateSceneManager.a().g() != null && CreateSceneManager.a().g().q == 0 && !CreateSceneManager.a().a(Integer.valueOf(this.f21437a.c(((Integer) arrayList3.get(i6)).intValue())))) {
                        arrayList4.add(arrayList3.get(i6));
                    }
                }
                generateDisplayIds(arrayList4, arrayList3);
            }
        }
        if (this.b != null) {
            if (!(this.b instanceof InnerActionCommon) || ((InnerActionCommon) this.b).f() == null) {
                this.f = false;
            }
            if (this.f) {
                ArrayList arrayList5 = new ArrayList();
                for (int valueOf3 : ((InnerActionCommon) this.b).f()) {
                    arrayList5.add(Integer.valueOf(valueOf3));
                }
                this.b.d();
                ArrayList arrayList6 = new ArrayList();
                if (this.e != -1) {
                    int[] c2 = this.b.c();
                    int length2 = c2.length;
                    while (true) {
                        if (i2 >= length2) {
                            break;
                        }
                        Integer valueOf4 = Integer.valueOf(c2[i2]);
                        if (valueOf4.intValue() == this.e) {
                            this.d = ((InnerActionCommon) this.b).f(valueOf4.intValue());
                            break;
                        }
                        i2++;
                    }
                }
                generateDisplayIds(arrayList6, arrayList5);
            } else {
                ArrayList arrayList7 = new ArrayList();
                ArrayList arrayList8 = new ArrayList();
                arrayList8.addAll(this.b.d());
                if (this.g != -1) {
                    for (int i7 = 0; i7 < ((InnerActionCommon) this.b).c().length; i7++) {
                        if (this.g == ((InnerActionCommon) this.b).f(i7)) {
                            arrayList7.add(Integer.valueOf(this.b.c()[i7]));
                        }
                    }
                } else {
                    for (int valueOf5 : this.b.c()) {
                        arrayList7.add(Integer.valueOf(valueOf5));
                    }
                }
                while (i2 < arrayList7.size()) {
                    if (!CreateSceneManager.a().b(Integer.valueOf(this.b.a(((Integer) arrayList7.get(i2)).intValue())))) {
                        arrayList8.add(arrayList7.get(i2));
                    }
                    i2++;
                }
                generateDisplayIds(arrayList8, arrayList7);
            }
        }
        if ((this.f21437a instanceof ELLocationInnerCondition) || (this.f21437a instanceof IZatGeoFencingInnerCondition)) {
            PermssionUtil.a((Activity) this);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        int intExtra;
        if (this.f && i3 == -1 && (intExtra = intent.getIntExtra("extra_index", -1)) != -1) {
            Intent intent2 = new Intent();
            intent2.putExtra("extra_index", intExtra);
            setResult(-1, intent2);
            finish();
            CreateSceneManager.a().l();
        }
    }

    class ItemAdapter extends BaseAdapter {
        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        ItemAdapter() {
        }

        public int getCount() {
            return SmartHomeSceneDetailActivity.this.h.size();
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(SmartHomeSceneDetailActivity.this).inflate(R.layout.smarthome_action_item, viewGroup, false);
            }
            View findViewById = view.findViewById(R.id.is_selected);
            if ((!SmartHomeSceneDetailActivity.this.f || ((Integer) SmartHomeSceneDetailActivity.this.h.get(i)).intValue() != SmartHomeSceneDetailActivity.this.d) && (SmartHomeSceneDetailActivity.this.f || ((Integer) SmartHomeSceneDetailActivity.this.h.get(i)).intValue() != SmartHomeSceneDetailActivity.this.e)) {
                view.findViewById(R.id.description_text).setSelected(false);
                if (SmartHomeSceneDetailActivity.this.f21437a instanceof InnerConditionCommon) {
                    InnerConditionCommon innerConditionCommon = (InnerConditionCommon) SmartHomeSceneDetailActivity.this.f21437a;
                    if (innerConditionCommon.a() == null || innerConditionCommon.a().e.isEmpty() || i >= innerConditionCommon.a().e.size()) {
                        findViewById.setVisibility(8);
                    } else if (CreateSceneManager.a().a(SmartHomeSceneDetailActivity.this.k, innerConditionCommon.a().e.get(i).b)) {
                        findViewById.setVisibility(0);
                    } else {
                        findViewById.setVisibility(8);
                    }
                } else if (SmartHomeSceneDetailActivity.this.b instanceof InnerActionCommon) {
                    InnerActionCommon innerActionCommon = (InnerActionCommon) SmartHomeSceneDetailActivity.this.b;
                    if (innerActionCommon.h() == null || innerActionCommon.h().f.isEmpty() || i >= innerActionCommon.h().f.size()) {
                        findViewById.setVisibility(8);
                    } else if (CreateSceneManager.a().b(SmartHomeSceneDetailActivity.this.k, innerActionCommon.h().f.get(i).b)) {
                        findViewById.setVisibility(0);
                    } else {
                        findViewById.setVisibility(8);
                    }
                } else {
                    findViewById.setVisibility(8);
                }
            } else {
                view.findViewById(R.id.description_text).setSelected(true);
                findViewById.setVisibility(8);
            }
            ((TextView) view.findViewById(R.id.description_text)).setText((CharSequence) SmartHomeSceneDetailActivity.this.j.get(i));
            if (SmartHomeSceneDetailActivity.this.i.containsKey(SmartHomeSceneDetailActivity.this.h.get(i))) {
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ToastUtil.a((int) R.string.scene_unclickable_toast_2);
                    }
                });
                view.findViewById(R.id.lock_item).setVisibility(0);
            } else {
                view.findViewById(R.id.lock_item).setVisibility(8);
                if (SmartHomeSceneDetailActivity.this.f) {
                    view.findViewById(R.id.expand_hint).setVisibility(0);
                } else {
                    view.findViewById(R.id.expand_hint).setVisibility(8);
                }
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Device access$900 = SmartHomeSceneDetailActivity.this.a();
                        if (access$900 != null) {
                            PluginRecord d = CoreApi.a().d(access$900.model);
                            if (d == null) {
                                SmartHomeSceneDetailActivity.this.a(i);
                            } else if (d.h() == null) {
                                SmartHomeSceneDetailActivity.this.a(i, SmartHomeSceneDetailActivity.this, d);
                            } else if (!d.h().q() || d.l()) {
                                SmartHomeSceneDetailActivity.this.a(i);
                            } else {
                                SmartHomeSceneDetailActivity.this.a(i, SmartHomeSceneDetailActivity.this, d);
                            }
                        } else {
                            Log.e("Scene", "scene, this situation can not exec, if exec device may be null...");
                            SmartHomeSceneDetailActivity.this.a(i);
                        }
                    }
                });
            }
            return view;
        }
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        if (this.f) {
            Intent intent = new Intent();
            intent.setClass(this, SmartHomeSceneDetailActivity.class);
            if (this.e != -1) {
                intent.putExtra("extra_selected_title", this.e);
            }
            intent.putExtra("extra_title", this.j.get(i2));
            intent.putExtra("extra_group_id", this.h.get(i2));
            intent.putExtra("show_group_info", false);
            startActivityForResult(intent, 101);
            return;
        }
        Intent intent2 = new Intent();
        intent2.putExtra("extra_index", this.h.get(i2));
        setResult(-1, intent2);
        finish();
        CreateSceneManager.a().l();
    }

    /* access modifiers changed from: private */
    public void a(int i2, Context context, PluginRecord pluginRecord) {
        final XQProgressHorizontalDialog b2 = XQProgressHorizontalDialog.b(this, context.getString(R.string.plugin_downloading) + pluginRecord.p() + context.getString(R.string.plugin));
        final PluginDownloadTask pluginDownloadTask = new PluginDownloadTask();
        final boolean z = !pluginRecord.l() && !pluginRecord.k();
        final Context context2 = context;
        final int i3 = i2;
        PluginApi.getInstance().installPlugin(SHApplication.getAppContext(), pluginRecord, new PluginApi.SendMessageCallback() {
            public void onDownloadStart(final PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                pluginDownloadTask.a(pluginDownloadTask);
                if (b2 != null && (context2 instanceof Activity) && !((Activity) context2).isFinishing()) {
                    if (Build.VERSION.SDK_INT < 17 || !((Activity) context2).isDestroyed()) {
                        b2.a(100, 0);
                        b2.c();
                        b2.setCancelable(true);
                        b2.show();
                        b2.setOnCancelListener(new DialogInterface.OnCancelListener() {
                            public void onCancel(DialogInterface dialogInterface) {
                                CoreApi.a().a(pluginRecord.o(), pluginDownloadTask, (CoreApi.CancelPluginDownloadCallback) null);
                            }
                        });
                    }
                }
            }

            public void onDownloadProgress(PluginRecord pluginRecord, float f2) {
                if (z) {
                    int i = (int) (f2 * 100.0f);
                    if (i >= 99) {
                        i = 99;
                    }
                    if (b2 != null) {
                        b2.a(100, i);
                    }
                } else if (b2 != null) {
                    b2.a(100, (int) (f2 * 100.0f));
                }
            }

            public void onDownloadSuccess(PluginRecord pluginRecord) {
                if (!z && b2 != null) {
                    b2.dismiss();
                }
            }

            public void onDownloadFailure(PluginError pluginError) {
                if (!z && b2 != null) {
                    b2.dismiss();
                }
                Toast.makeText(SHApplication.getAppContext(), R.string.device_enter_failed, 0).show();
            }

            public void onDownloadCancel() {
                if (!z && b2 != null) {
                    b2.dismiss();
                }
            }

            public void onInstallSuccess(PluginRecord pluginRecord) {
                if (b2 != null && b2.isShowing()) {
                    b2.dismiss();
                }
                SmartHomeSceneDetailActivity.this.a(i3);
            }

            public void onInstallFailure(PluginError pluginError) {
                LogUtil.b("SmartHomeSceneDetailActivity", pluginError.b());
                if (b2 != null && b2.isShowing()) {
                    b2.dismiss();
                }
                Toast.makeText(SHApplication.getAppContext(), R.string.device_enter_failed, 0).show();
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0020  */
    /* JADX WARNING: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.smarthome.device.Device a() {
        /*
            r2 = this;
            com.xiaomi.smarthome.scene.action.BaseInnerAction r0 = r2.b
            if (r0 == 0) goto L_0x0019
            com.xiaomi.smarthome.scene.action.BaseInnerAction r0 = r2.b
            boolean r0 = r0 instanceof com.xiaomi.smarthome.scene.action.InnerActionCommon
            if (r0 == 0) goto L_0x0019
            com.xiaomi.smarthome.scene.action.BaseInnerAction r0 = r2.b
            com.xiaomi.smarthome.scene.action.InnerActionCommon r0 = (com.xiaomi.smarthome.scene.action.InnerActionCommon) r0
            com.xiaomi.smarthome.device.Device r1 = r0.g()
            if (r1 == 0) goto L_0x0019
            com.xiaomi.smarthome.device.Device r0 = r0.g()
            goto L_0x001a
        L_0x0019:
            r0 = 0
        L_0x001a:
            if (r0 != 0) goto L_0x0026
            com.xiaomi.smarthome.scene.condition.BaseInnerCondition r1 = r2.f21437a
            if (r1 == 0) goto L_0x0026
            com.xiaomi.smarthome.scene.condition.BaseInnerCondition r0 = r2.f21437a
            com.xiaomi.smarthome.device.Device r0 = r0.d()
        L_0x0026:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scene.SmartHomeSceneDetailActivity.a():com.xiaomi.smarthome.device.Device");
    }

    public void generateDisplayIds(List<Integer> list, List<Integer> list2) {
        this.h.clear();
        if (list2 != null) {
            this.h.addAll(list2);
        }
        if (list != null) {
            for (Integer put : list) {
                this.i.put(put, "Disable");
            }
        }
        if (this.f21437a != null) {
            for (int i2 = 0; i2 < this.h.size(); i2++) {
                if (this.f) {
                    this.j.add(((InnerConditionCommon) this.f21437a).d(this.h.get(i2).intValue()));
                } else {
                    this.j.add(this.f21437a.b(this.h.get(i2).intValue()));
                }
            }
        }
        if (this.b != null) {
            for (int i3 = 0; i3 < this.h.size(); i3++) {
                if (this.f) {
                    this.j.add(((InnerActionCommon) this.b).e(this.h.get(i3).intValue()));
                } else {
                    this.j.add(this.b.c(this.h.get(i3).intValue()));
                }
            }
        }
    }
}
