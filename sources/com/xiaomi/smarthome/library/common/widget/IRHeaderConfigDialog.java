package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.renderer.DevicePluginOpener;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;

public class IRHeaderConfigDialog {

    /* renamed from: a  reason: collision with root package name */
    LinearLayout f18858a = null;
    LinearLayout b = null;
    ImageView c = null;
    TextView d = null;
    ImageView e = null;
    TextView f = null;
    TextView g = null;
    MLAlertDialog h = null;
    ConfigChangeListener i = null;

    public interface ConfigChangeListener {
        void a();
    }

    public MLAlertDialog a(Context context, ConfigChangeListener configChangeListener) {
        this.i = configChangeListener;
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(context);
        builder.a((int) R.string.dialog_title_phone_ir_settings);
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_ir_config, (ViewGroup) null, false);
        this.f18858a = (LinearLayout) inflate.findViewById(R.id.sc_duokan);
        this.b = (LinearLayout) inflate.findViewById(R.id.sc_tianjia);
        this.c = (ImageView) this.f18858a.findViewWithTag("checkbox");
        this.d = (TextView) this.f18858a.findViewWithTag("text");
        this.e = (ImageView) this.b.findViewWithTag("checkbox");
        this.f = (TextView) this.b.findViewWithTag("text");
        if (IRDeviceUtil.e()) {
            this.e.setVisibility(0);
            this.f.setSelected(true);
            this.c.setVisibility(4);
            this.d.setSelected(false);
        } else {
            this.e.setVisibility(4);
            this.f.setSelected(false);
            this.c.setVisibility(0);
            this.d.setSelected(true);
        }
        this.b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                IRHeaderConfigDialog.this.e.setVisibility(0);
                IRHeaderConfigDialog.this.f.setSelected(true);
                IRHeaderConfigDialog.this.c.setVisibility(4);
                IRHeaderConfigDialog.this.d.setSelected(false);
            }
        });
        this.f18858a.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                IRHeaderConfigDialog.this.e.setVisibility(4);
                IRHeaderConfigDialog.this.f.setSelected(false);
                IRHeaderConfigDialog.this.c.setVisibility(0);
                IRHeaderConfigDialog.this.d.setSelected(true);
            }
        });
        this.g = (TextView) inflate.findViewById(R.id.txt_manage_remotes);
        this.g.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (IRHeaderConfigDialog.this.h != null) {
                    IRHeaderConfigDialog.this.h.dismiss();
                    IRHeaderConfigDialog.this.h.cancel();
                }
                DevicePluginOpener.a(view.getContext());
            }
        });
        builder.b(inflate);
        builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                dialogInterface.cancel();
            }
        });
        builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                IRDeviceUtil.a(IRHeaderConfigDialog.this.e.getVisibility() == 0);
                if (IRHeaderConfigDialog.this.i != null) {
                    IRHeaderConfigDialog.this.i.a();
                }
                dialogInterface.dismiss();
                dialogInterface.cancel();
            }
        });
        this.h = builder.b();
        return this.h;
    }

    public void a() {
        if (this.h != null) {
            this.h.show();
        }
    }
}
