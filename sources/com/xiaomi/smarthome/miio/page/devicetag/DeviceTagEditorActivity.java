package com.xiaomi.smarthome.miio.page.devicetag;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class DeviceTagEditorActivity extends BaseActivity implements TextWatcher, View.OnClickListener {
    public static final String ACTION_EDITOR_CHANGED = "editor_changed_action";
    public static final int ACTIVITY_REQUEST_CODE_BATCH = 1001;
    public static final String CONTAINED_DIDS_PARAM = "contained_dids_param";
    public static final String TAG_NAME = "tag_name";
    public static final String TAG_PARAM = "tag_param";

    /* renamed from: a  reason: collision with root package name */
    private View f19804a;
    private TextView b;
    /* access modifiers changed from: private */
    public EditText c;
    private Pattern d;
    private DeviceTagEditorFragment e;
    private String f;
    private BroadcastReceiver g = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals("editor_changed_action", intent.getAction())) {
                DeviceTagEditorActivity.this.b();
            } else if (TextUtils.equals(DeviceTagEditorFragment.f19812a, intent.getAction())) {
                DeviceTagEditorActivity.this.softInputToggle(false);
            }
        }
    };

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_device_tag_editor);
        this.f = getIntent().getStringExtra(TAG_PARAM);
        String stringExtra = getIntent().getStringExtra("tag_name");
        this.e = (DeviceTagEditorFragment) getSupportFragmentManager().findFragmentById(R.id.device_tag_left_fragment);
        this.e.a(this.f);
        this.f19804a = findViewById(R.id.module_a_4_return_more_btn);
        this.b = (TextView) findViewById(R.id.module_a_4_return_finish_btn);
        TextView textView = (TextView) findViewById(R.id.module_a_4_return_more_title);
        this.c = (EditText) findViewById(R.id.input_tag);
        this.d = Pattern.compile("^[\\u4e00-\\u9fa5_a-zA-Z0-9 ]+$");
        if (TextUtils.isEmpty(this.f)) {
            textView.setText(R.string.tag_add_title);
            if (TextUtils.isEmpty(stringExtra)) {
                this.c.postDelayed(new Runnable() {
                    public void run() {
                        DeviceTagEditorActivity.this.softInputToggle(true);
                    }
                }, 150);
            } else {
                this.c.setText(stringExtra);
                this.c.setSelection(this.c.length());
                this.b.setEnabled(true);
            }
        } else {
            textView.setText(R.string.tag_editor_title);
            this.c.setText(this.f);
            this.c.setSelection(this.c.length());
        }
        this.b.setText(R.string.save);
        this.f19804a.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.c.addTextChangedListener(this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("editor_changed_action");
        intentFilter.addAction(DeviceTagEditorFragment.f19812a);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.g, intentFilter);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.g);
    }

    public void onClick(View view) {
        if (view == this.f19804a) {
            softInputToggle(false);
            onBackPressed();
        } else if (view == this.b) {
            a();
        }
    }

    public void onBackPressed() {
        if (this.b.isEnabled()) {
            new MLAlertDialog.Builder(this).a((int) R.string.tag_dump_data_prompt).b((int) R.string.tag_dump_data_prompt_description).a((int) R.string.save, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    DeviceTagEditorActivity.this.a();
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    DeviceTagEditorActivity.this.finish();
                }
            }).d();
        } else {
            super.onBackPressed();
        }
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.b.setEnabled(!TextUtils.isEmpty(charSequence));
    }

    /* access modifiers changed from: private */
    public void a() {
        softInputToggle(false);
        final String trim = this.c.getText().toString().trim();
        if (!this.d.matcher(trim).matches() || StringUtil.t(trim) || TextUtils.isEmpty(trim)) {
            new MLAlertDialog.Builder(this).a((CharSequence) String.format(getString(R.string.tag_save_data_title), new Object[]{trim})).b((int) R.string.tag_save_data_description).c((int) R.string.tag_roger, (DialogInterface.OnClickListener) null).d();
            return;
        }
        Set<String> c2 = this.e.c();
        if (TextUtils.isEmpty(this.f)) {
            if (!SmartHomeDeviceHelper.a().b().a(4, trim)) {
                SmartHomeDeviceHelper.a().b().a(trim, c2);
                a(c2);
            } else {
                this.b.setEnabled(false);
                new MLAlertDialog.Builder(this).b((int) R.string.tag_name_duplicate).a((int) R.string.confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DeviceTagEditorActivity.this.c.setSelection(0, trim.length());
                    }
                }).d();
                return;
            }
        } else if (this.f.equals(trim) || !SmartHomeDeviceHelper.a().b().a(4, trim)) {
            SmartHomeDeviceHelper.a().b().a(this.f, trim, c2);
            a(c2);
            StatHelper.h(trim);
            if (getIntent().getBooleanExtra("result", false)) {
                Intent intent = new Intent();
                intent.putExtra("old_name", this.f);
                intent.putExtra("new_name", trim);
                setResult(-1, intent);
            }
        } else {
            this.b.setEnabled(false);
            new MLAlertDialog.Builder(this).b((int) R.string.tag_name_duplicate).a((int) R.string.confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    DeviceTagEditorActivity.this.c.setSelection(0, trim.length());
                }
            }).d();
            return;
        }
        SmartHomeDeviceHelper.a().b().j();
        SmartHomeDeviceHelper.a().b().k();
        finish();
    }

    private void a(Set<String> set) {
        if (set != null && !set.isEmpty()) {
            HashSet hashSet = new HashSet();
            for (String l : set) {
                Device l2 = SmartHomeDeviceManager.a().l(l);
                if (l2 != null && !l2.isShowMainList()) {
                    hashSet.add(l2.did);
                }
            }
            if (hashSet.size() > 0) {
                SmartHomeDeviceManager.a().a(true, (Set<String>) hashSet, (Context) this, (AsyncResponseCallback<Void>) null);
            }
        }
    }

    public void softInputToggle(boolean z) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (!inputMethodManager.isActive()) {
            return;
        }
        if (z) {
            inputMethodManager.showSoftInput(this.c, 0);
        } else {
            inputMethodManager.hideSoftInputFromWindow(this.c.getWindowToken(), 0);
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        this.b.setEnabled(!TextUtils.isEmpty(this.c.getText().toString()));
    }

    private void c() {
        Intent intent = new Intent(this, DeviceTagBatchActivity.class);
        Set<String> c2 = this.e.c();
        if (c2 != null && !c2.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(c2);
            intent.putExtra("contained_dids_param", arrayList);
        }
        startActivityForResult(intent, 1001);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        ArrayList<String> stringArrayListExtra;
        if (i2 == -1 && i == 1001 && intent != null && (stringArrayListExtra = intent.getStringArrayListExtra("contained_dids_param")) != null && !stringArrayListExtra.isEmpty()) {
            this.e.a(stringArrayListExtra);
            b();
        }
        super.onActivityResult(i, i2, intent);
    }

    public String getCurrentTag() {
        return this.c.getText().toString();
    }
}
