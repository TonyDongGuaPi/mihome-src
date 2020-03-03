package com.xiaomi.smarthome.miio.page.devicetag;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;

public class EditorSelectChildViewHolder extends DeviceTagChildViewHolder {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public CheckBox f19823a;
    private TextView c;
    private TextView d;
    private SimpleDraweeView e;
    private View f;
    private final Drawable g = SHApplication.getAppContext().getResources().getDrawable(R.drawable.tag_normal_checkbox_layout);
    private final Drawable h = SHApplication.getAppContext().getResources().getDrawable(R.drawable.tag_other_checkbox_layout);
    /* access modifiers changed from: private */
    public final MLAlertDialog.Builder i;

    public EditorSelectChildViewHolder(View view, Context context) {
        super(view);
        view.findViewById(R.id.root_item).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditorSelectChildViewHolder.this.f19823a.setChecked(!EditorSelectChildViewHolder.this.f19823a.isChecked());
            }
        });
        this.e = (SimpleDraweeView) view.findViewById(R.id.icon);
        this.f19823a = (CheckBox) view.findViewById(R.id.select_check);
        this.c = (TextView) view.findViewById(R.id.title);
        this.d = (TextView) view.findViewById(R.id.room);
        this.f = view.findViewById(R.id.divider_item);
        this.i = new MLAlertDialog.Builder(context);
        this.i.a((int) R.string.confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                EditorSelectChildViewHolder.this.f19823a.setChecked(true);
            }
        });
        this.i.b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                EditorSelectChildViewHolder.this.f19823a.setChecked(false);
            }
        });
        this.i.a(false);
    }

    public void a(final DeviceTagAdapter deviceTagAdapter, DeviceTagGroup deviceTagGroup, int i2, final int i3) {
        if (deviceTagGroup.w != null && i3 < deviceTagGroup.w.size()) {
            this.f19823a.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
            final DeviceTagChild deviceTagChild = deviceTagGroup.w.get(i3);
            this.c.setText(deviceTagChild.d);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f.getLayoutParams();
            if (i3 == deviceTagGroup.w.size() - 1) {
                layoutParams.leftMargin = 0;
            } else {
                layoutParams.leftMargin = DisplayUtils.a(deviceTagAdapter.f(), 13.0f);
            }
            this.f19823a.setButtonDrawable(this.g);
            if (deviceTagChild.l == 0) {
                this.d.setTextColor(deviceTagAdapter.f().getResources().getColor(R.color.tag_defalut_name_bg));
                this.d.setText(R.string.tag_recommend_defaultroom);
            } else if (deviceTagChild.l == 1) {
                this.d.setTextColor(deviceTagAdapter.f().getResources().getColor(R.color.class_D));
                this.d.setText(R.string.tag_recommend_added);
            } else if (deviceTagChild.l == 2) {
                this.d.setTextColor(deviceTagAdapter.f().getResources().getColor(R.color.class_D));
                TextView textView = this.d;
                textView.setText(Operators.ARRAY_START_STR + deviceTagChild.k + Operators.ARRAY_END_STR);
                this.f19823a.setButtonDrawable(this.h);
            }
            this.f19823a.setChecked(deviceTagChild.h);
            if (deviceTagChild.g != null && deviceTagChild.g.size() > 0) {
                Device b = SmartHomeDeviceManager.a().b(deviceTagChild.g.iterator().next());
                if (b == null) {
                    b = SmartHomeDeviceManager.a().l(deviceTagChild.g.iterator().next());
                }
                if (b != null) {
                    DeviceFactory.b(b.model, this.e);
                }
            }
            this.f19823a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (deviceTagAdapter instanceof DeviceTagEditorAdapter) {
                        if (deviceTagChild.l == 2 && EditorSelectChildViewHolder.this.f19823a.isChecked()) {
                            EditorSelectChildViewHolder.this.i.b((CharSequence) String.format(deviceTagAdapter.f().getString(R.string.tag_transfer_device), new Object[]{deviceTagChild.k, ((DeviceTagEditorAdapter) deviceTagAdapter).i()}));
                            EditorSelectChildViewHolder.this.i.d();
                        }
                        ((DeviceTagEditorAdapter) deviceTagAdapter).c(i3, z);
                        if (deviceTagChild.l == 2) {
                            if (deviceTagChild.h) {
                                SmartHomeDeviceHelper.a().b().d(deviceTagChild.g.iterator().next(), deviceTagChild.k);
                            } else {
                                SmartHomeDeviceHelper.a().b().g(deviceTagChild.g.iterator().next());
                            }
                        }
                    }
                    LocalBroadcastManager.getInstance(deviceTagAdapter.f()).sendBroadcast(new Intent("editor_changed_action"));
                }
            });
        }
    }
}
