package com.xiaomi.infrared.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaomi.infrared.InifraredContants;
import com.xiaomi.infrared.adapter.BaseRecyclerAdapter;
import com.xiaomi.infrared.adapter.IRStudyListAdapter;
import com.xiaomi.infrared.bean.IRFunctionType;
import com.xiaomi.infrared.bean.IRKeyValue;
import com.xiaomi.infrared.bean.IRType;
import com.xiaomi.infrared.bean.InfraredControllerInfo;
import com.xiaomi.infrared.bean.MJSetResultBean;
import com.xiaomi.infrared.request.InifraredRequestApi;
import com.xiaomi.infrared.utils.CommUtil;
import com.xiaomi.infrared.utils.IRDataUtil;
import com.xiaomi.infrared.widget.GridItemDecoration;
import com.xiaomi.infrared.widget.ImiGridLayoutManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.audiorecord.ToastUtil;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.IXmPluginHostActivity;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class IRStudyActivity extends BaseActivity implements View.OnClickListener {
    public static final String KEY_ALERT_STUDY_POSITION = "key_alert_study_position";
    public static final String KEY_DEVICE_IMAGE = "key_device_image";
    public static final int KEY_EXIST_KEY_TYPE = 2;
    public static final int KEY_EXIST_TYPE = 1;
    public static final int KEY_NON_EXIST_TYPE = 3;
    public static final String KEY_VALUE_TYPE = "key_value_type";
    public static final int MSG_REFURBISH_LISTVIEW_DATA = 201;
    public static final int REQUEST_CODE_STUDY_KEY = 101;
    public static final String RESULT_BUNDLE_KEY = "request_bundle_key";

    /* renamed from: a  reason: collision with root package name */
    private static final String f10201a = "IRStudyActivityV2";
    /* access modifiers changed from: private */
    public List<IRKeyValue> b = new ArrayList();
    private IRType c;
    private RecyclerView d;
    /* access modifiers changed from: private */
    public IRStudyListAdapter e;
    private ImiGridLayoutManager f;
    private View g;
    /* access modifiers changed from: private */
    public View h;
    /* access modifiers changed from: private */
    public ImageView i;
    /* access modifiers changed from: private */
    public InifraredRequestApi j = new InifraredRequestApi();
    private DeviceStat k;
    /* access modifiers changed from: private */
    public String l;
    private XQProgressDialog m;
    protected Map<String, IRKeyValue> mStudyKeyMap = new LinkedHashMap();

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ir_study_add_other) {
            doStudyOther("");
        } else if (id == R.id.module_a_3_return_btn) {
            finish();
        } else if (id == R.id.module_a_3_right_text_btn_more) {
            e();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        int intExtra = intent.getIntExtra(InifraredContants.IntentParams.v, 0);
        this.k = (DeviceStat) intent.getParcelableExtra("extra_device");
        this.c = IRType.valueOf(intExtra);
        setContentView(R.layout.activity_ir_study_controllerv2);
        View findViewById = findViewById(R.id.module_a_3_return_btn);
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(b());
        TextView textView = (TextView) findViewById(R.id.module_a_3_right_text_btn_more);
        this.j.a((Context) this);
        textView.setVisibility(0);
        textView.setBackground((Drawable) null);
        textView.setText(R.string.ir_finish);
        textView.setTextColor(getResources().getColor(R.color.ir_study_btn_color));
        textView.setTextSize(16.0f);
        this.d = (RecyclerView) findViewById(R.id.ir_study_recycler_view);
        this.g = findViewById(R.id.ir_imi_progress);
        this.i = (ImageView) findViewById(R.id.ivProgress);
        View findViewById2 = findViewById(R.id.ir_study_add_other);
        this.h = findViewById(R.id.ir_study_default_empty);
        View findViewById3 = findViewById(R.id.ir_study_controller_tip_root);
        this.e = new IRStudyListAdapter(this, this.b, this.mStudyKeyMap, this.c);
        this.f = new ImiGridLayoutManager(this, 3);
        this.d.setLayoutManager(this.f);
        this.d.setAdapter(this.e);
        this.d.addItemDecoration(new GridItemDecoration(5));
        this.e.a((BaseRecyclerAdapter.OnItemClickListener) new BaseRecyclerAdapter.OnItemClickListener() {
            public void onItemClick(RecyclerView recyclerView, View view, int i) {
                IRKeyValue iRKeyValue = (IRKeyValue) IRStudyActivity.this.b.get(i);
                int i2 = TextUtils.isEmpty(iRKeyValue.g()) ? 2 : 1;
                Bundle bundle = new Bundle();
                bundle.putInt(IRStudyActivity.KEY_VALUE_TYPE, i2);
                bundle.putInt(IRStudyActivity.KEY_ALERT_STUDY_POSITION, i);
                bundle.putString(IRStudyActivity.KEY_DEVICE_IMAGE, IRStudyActivity.this.l);
                IRStudyTipActivity.showStudyTipActivity((Activity) IRStudyActivity.this, iRKeyValue, bundle, 101);
            }
        });
        if (this.c == IRType.Unknown) {
            this.h.setVisibility(0);
        } else {
            a();
            findViewById3.setVisibility(0);
        }
        findViewById.setOnClickListener(this);
        textView.setOnClickListener(this);
        findViewById2.setOnClickListener(this);
        this.j.d(this.k.model, new AsyncCallback<String, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(String str) {
                try {
                    JSONObject optJSONObject = new JSONObject(str).optJSONObject("neg_screen");
                    String unused = IRStudyActivity.this.l = optJSONObject.optString("neg_480");
                    if (TextUtils.isEmpty(IRStudyActivity.this.l)) {
                        String unused2 = IRStudyActivity.this.l = optJSONObject.optString("short_480");
                    }
                } catch (Exception unused3) {
                    String unused4 = IRStudyActivity.this.l = "";
                }
            }
        });
    }

    private void a() {
        c();
        this.j.b(this.c.value(), (AsyncCallback<ArrayList<IRKeyValue>, Error>) new AsyncCallback<ArrayList<IRKeyValue>, Error>() {
            /* renamed from: a */
            public void onSuccess(ArrayList<IRKeyValue> arrayList) {
                IRStudyActivity.this.b.clear();
                if (arrayList == null || arrayList.size() == 0) {
                    IRStudyActivity.this.h.setVisibility(0);
                } else {
                    IRStudyActivity.this.b.addAll(arrayList);
                    IRStudyActivity.this.h.setVisibility(8);
                }
                IRStudyActivity.this.e.notifyDataSetChanged();
                IRStudyActivity.this.d();
            }

            public void onFailure(Error error) {
                IRStudyActivity.this.d();
                ToastUtil.a((Context) IRStudyActivity.this, (int) R.string.ir_toast_get_data_failed, 0);
            }
        });
    }

    public void moveToPosition(LinearLayoutManager linearLayoutManager, int i2) {
        int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        int findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        if (i2 <= findFirstVisibleItemPosition) {
            this.d.scrollToPosition(i2);
        } else if (i2 <= findLastVisibleItemPosition) {
            this.d.scrollBy(0, this.d.getChildAt(i2 - findFirstVisibleItemPosition).getTop());
        } else {
            this.d.scrollToPosition(i2);
        }
    }

    private String b() {
        int b2 = IRDataUtil.a(this.c.value()).b();
        String string = getResources().getString(R.string.ir_device_type_unknown);
        String string2 = getResources().getString(b2);
        String string3 = getResources().getString(R.string.ir_select_controller_title);
        if (this.c == IRType.Unknown) {
            return string2 + string3;
        }
        return string + string2 + string3;
    }

    /* access modifiers changed from: protected */
    public void onResultKeyProcess(Intent intent) {
        Bundle bundleExtra = intent.getBundleExtra(RESULT_BUNDLE_KEY);
        IRKeyValue iRKeyValue = (IRKeyValue) intent.getParcelableExtra(InifraredContants.IntentParams.r);
        this.mStudyKeyMap.put(iRKeyValue.e(), iRKeyValue);
        int i2 = bundleExtra.getInt(KEY_VALUE_TYPE);
        int i3 = bundleExtra.getInt(KEY_ALERT_STUDY_POSITION, 0);
        if (i2 == 1) {
            ToastUtil.a(this, String.format(getResources().getString(R.string.ir_study_alert_reset_add), new Object[]{CommUtil.a(iRKeyValue)}));
            this.b.get(i3).f(iRKeyValue.g());
        } else if (i2 == 2) {
            this.b.get(i3).f(iRKeyValue.g());
        } else {
            this.b.add(iRKeyValue);
            moveToPosition(this.f, this.b.size() - 1);
            this.h.setVisibility(8);
        }
        this.e.notifyDataSetChanged();
    }

    private void c() {
        this.g.setVisibility(0);
        Drawable drawable = this.i.getDrawable();
        if (drawable instanceof AnimationDrawable) {
            ((AnimationDrawable) drawable).start();
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        this.g.setVisibility(8);
        Drawable drawable = this.i.getDrawable();
        if (drawable instanceof AnimationDrawable) {
            ((AnimationDrawable) drawable).stop();
        }
    }

    /* access modifiers changed from: protected */
    public void doStudyOther(String str) {
        final String string = getString(R.string.ir_controller_edit_key_name_title);
        View inflate = View.inflate(this, R.layout.activity_ir_edit_name, (ViewGroup) null);
        inflate.findViewById(R.id.ir_edit_board).setOnClickListener(this);
        final EditText editText = (EditText) inflate.findViewById(R.id.ir_edit_view);
        if (!TextUtils.isEmpty(str)) {
            editText.setText(str);
            editText.setSelection(0, str.length());
        }
        editText.setHint(getString(R.string.ir_controller_edit_hint) + string);
        new MLAlertDialog.Builder(this).b(inflate).a((CharSequence) string).b((int) R.string.ir_cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).a((int) R.string.ir_confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                String obj = editText.getText().toString();
                if (obj.isEmpty()) {
                    Toast.makeText(IRStudyActivity.this, string + IRStudyActivity.this.getString(R.string.ir_controller_edit_empty_toast), 0).show();
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putInt(IRStudyActivity.KEY_VALUE_TYPE, 3);
                bundle.putString(IRStudyActivity.KEY_DEVICE_IMAGE, IRStudyActivity.this.l);
                IRStudyTipActivity.showStudyTipActivity((Activity) IRStudyActivity.this, obj, bundle, 101);
                dialogInterface.dismiss();
            }
        }).d();
    }

    private void e() {
        final InfraredControllerInfo g2 = g();
        List<IRKeyValue> c2 = g2.c();
        if (c2 == null || c2.size() == 0) {
            ToastUtil.a((Context) this, (int) R.string.edit_save_failed_no_keys, 1);
            return;
        }
        a(getResources().getString(R.string.setting_progress_dialog_tips));
        HashMap hashMap = new HashMap();
        hashMap.put(g2.i(), g2);
        this.j.a(this.k, (Map<String, InfraredControllerInfo>) hashMap, (AsyncCallback<MJSetResultBean, Error>) new AsyncCallback<MJSetResultBean, Error>() {
            /* renamed from: a */
            public void onSuccess(final MJSetResultBean mJSetResultBean) {
                IRStudyActivity.this.j.a(g2.c(), mJSetResultBean.a(), (AsyncCallback<String, Error>) new AsyncCallback<String, Error>() {
                    /* renamed from: a */
                    public void onSuccess(String str) {
                        AnonymousClass1 r5 = new Runnable() {
                            public void run() {
                                Intent intent = new Intent();
                                intent.putExtra(InifraredContants.IntentParams.d, true);
                                intent.putExtra(InifraredContants.IntentParams.e, mJSetResultBean.a());
                                intent.putExtra(InifraredContants.IntentParams.f, mJSetResultBean.f());
                                intent.putExtra(InifraredContants.IntentParams.g, mJSetResultBean.c());
                                intent.putExtra(InifraredContants.IntentParams.h, mJSetResultBean.d());
                                IRStudyActivity.this.setResult(-1, intent);
                                IRStudyActivity.this.finish();
                            }
                        };
                        IRStudyActivity.this.f();
                        if (IRStudyActivity.this.getIntent().getBooleanExtra("create_device", true)) {
                            IRStudyActivity.this.i.postDelayed(r5, 1000);
                            FrameManager.b().k().onDeviceReady(IRStudyActivity.this, mJSetResultBean.d(), mJSetResultBean.a(), new IXmPluginHostActivity.AsyncCallback<Void>() {
                                /* renamed from: a */
                                public void onSuccess(Void voidR) {
                                }

                                public void onFailure(int i, Object obj) {
                                }
                            });
                            return;
                        }
                        IRStudyActivity.this.i.postDelayed(r5, 0);
                    }

                    public void onFailure(Error error) {
                        ToastUtil.a((Context) IRStudyActivity.this, (int) R.string.edit_save_failed, 1);
                        IRStudyActivity.this.f();
                    }
                });
            }

            public void onFailure(Error error) {
                ToastUtil.a((Context) IRStudyActivity.this, (int) R.string.edit_save_failed, 1);
                IRStudyActivity.this.f();
            }
        });
    }

    private void a(String str) {
        if (this.m != null) {
            this.m.cancel();
            this.m = null;
        }
        this.m = new XQProgressDialog(this);
        this.m.setCancelable(false);
        this.m.setMessage(str);
        this.m.show();
    }

    /* access modifiers changed from: private */
    public void f() {
        if (this.m != null) {
            this.m.cancel();
            this.m = null;
        }
    }

    private InfraredControllerInfo g() {
        InfraredControllerInfo infraredControllerInfo = new InfraredControllerInfo();
        infraredControllerInfo.a(IRFunctionType.STUDY);
        infraredControllerInfo.c("" + (System.currentTimeMillis() / 1000));
        infraredControllerInfo.a(this.c);
        infraredControllerInfo.a((List<IRKeyValue>) new ArrayList(this.mStudyKeyMap.values()));
        String string = getResources().getString(IRDataUtil.a(this.c.value()).b());
        infraredControllerInfo.d(string);
        infraredControllerInfo.f(string);
        return infraredControllerInfo;
    }

    public void onDestroy() {
        super.onDestroy();
        this.j.a();
    }

    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (intent != null && intent.getBooleanExtra(InifraredContants.IntentParams.d, false)) {
            setResult(-1, intent);
            finish();
        }
        if (i3 == -1 && i2 == 101) {
            onResultKeyProcess(intent);
        }
    }
}
