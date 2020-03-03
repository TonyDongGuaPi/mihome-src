package com.xiaomi.smarthome.miio.page;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.utils.ClientRemarkInputView;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.library.common.widget.FlowGroup;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeviceTagEditActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private static final int[] f19552a = {R.string.tag_recommend_bedroom, R.string.tag_recommend_livingroom, R.string.tag_recommend_kitchen, R.string.tag_recommend_washroom, R.string.tag_recommend_balcony, R.string.tag_recommend_home, R.string.tag_recommend_workplace};
    private String b;
    private View c;
    private TextView d;
    private List<Set<String>> e;
    private DeviceTagInterface f;
    /* access modifiers changed from: private */
    public FlowGroup g;
    private FlowGroup h;
    private View i;
    private View j;

    private static class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public String f19556a;
        public boolean b;
        public View c;

        private ViewHolder() {
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_edit_device_tag);
        this.b = getIntent().getStringExtra("did");
        if (StringUtil.c(this.b)) {
            finish();
            return;
        }
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.tag_string);
        this.d = (TextView) findViewById(R.id.module_a_3_right_text_btn);
        this.d.setText(R.string.tag_done);
        this.d.setVisibility(4);
        this.d.setOnClickListener(this);
        this.c = findViewById(R.id.module_a_3_return_btn);
        this.c.setOnClickListener(this);
        this.i = findViewById(R.id.tag_add);
        this.i.setOnClickListener(this);
        this.j = findViewById(R.id.tag_delete);
        this.j.setOnClickListener(this);
        this.g = (FlowGroup) findViewById(R.id.tag_owned_container);
        this.h = (FlowGroup) findViewById(R.id.tag_recommend_container);
        this.f = SmartHomeDeviceHelper.a().b();
        if (this.e == null) {
            this.e = new ArrayList();
            for (int i2 = 0; i2 < 5; i2++) {
                this.e.add(new HashSet());
            }
        }
        a();
    }

    private void a() {
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            showTags(this, getLayoutInflater(), this.g, this.e.get(i2), i2);
        }
        b();
    }

    public boolean showTags(Activity activity, LayoutInflater layoutInflater, FlowGroup flowGroup, Set<String> set, int i2) {
        boolean z = false;
        if (flowGroup == null || set == null) {
            return false;
        }
        for (String a2 : set) {
            z |= a(activity, layoutInflater, flowGroup, a2, i2, false);
        }
        return z;
    }

    private boolean a(Activity activity, LayoutInflater layoutInflater, FlowGroup flowGroup, String str, int i2, final boolean z) {
        if (flowGroup == null || StringUtil.c(str)) {
            return false;
        }
        ViewHolder viewHolder = new ViewHolder();
        if (i2 == 2) {
            viewHolder.f19556a = str;
            str = this.f.e(str);
        } else if (i2 == 4) {
            viewHolder.b = true;
        }
        final View inflate = layoutInflater.inflate(R.layout.item_device_tag, (ViewGroup) null);
        viewHolder.c = inflate.findViewById(R.id.tag_delete_icon);
        TextView textView = (TextView) inflate.findViewById(R.id.device_tag_text);
        textView.setText(str);
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean z = z;
            }
        });
        viewHolder.c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DeviceTagEditActivity.this.g.removeView(inflate);
            }
        });
        inflate.setTag(viewHolder);
        flowGroup.addView(inflate);
        return true;
    }

    private void b() {
        LayoutInflater layoutInflater = getLayoutInflater();
        for (int string : f19552a) {
            a(this, layoutInflater, this.h, getString(string), -1, true);
        }
    }

    public void onClick(View view) {
        if (view == this.j) {
            c();
        } else if (view == this.i) {
            e();
        } else if (view == this.d) {
            d();
        } else if (view == this.c) {
            finish();
        }
    }

    private void c() {
        this.d.setVisibility(0);
        if (this.g.getChildCount() > 0) {
            for (int i2 = 0; i2 < this.g.getChildCount(); i2++) {
                ViewHolder viewHolder = (ViewHolder) this.g.getChildAt(i2).getTag();
                if (viewHolder != null && viewHolder.b) {
                    viewHolder.c.setVisibility(0);
                }
            }
        }
    }

    private void d() {
        this.d.setVisibility(4);
        if (this.g.getChildCount() > 0) {
            for (int i2 = 0; i2 < this.g.getChildCount(); i2++) {
                ViewHolder viewHolder = (ViewHolder) this.g.getChildAt(i2).getTag();
                if (viewHolder != null && viewHolder.b) {
                    viewHolder.c.setVisibility(4);
                }
            }
        }
    }

    private void e() {
        final ClientRemarkInputView clientRemarkInputView = (ClientRemarkInputView) getLayoutInflater().inflate(R.layout.client_remark_input_view, (ViewGroup) null);
        clientRemarkInputView.initialize((ClientRemarkInputView.RenameInterface) null, new MLAlertDialog.Builder(this).a((int) R.string.tag_edit_title).b((View) clientRemarkInputView).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                clientRemarkInputView.getEditText().getText().toString();
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).d(), "");
    }
}
