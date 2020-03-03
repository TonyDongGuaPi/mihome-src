package com.xiaomi.smarthome.miio.yeelight;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;

public class YeelightUserDefineDialog extends Dialog {

    /* renamed from: a  reason: collision with root package name */
    int f19997a;
    int b;
    String c;
    View.OnClickListener d;
    Button e;
    Button f;
    TextView g;
    TextView h;
    TextView i;
    ImageView j;

    public YeelightUserDefineDialog(Context context) {
        super(context, 16973840);
        DisplayUtils.a(getWindow());
    }

    public YeelightUserDefineDialog(Context context, int i2) {
        super(context, 16973840);
        DisplayUtils.a(getWindow());
    }

    public void a(int i2, int i3, String str, View.OnClickListener onClickListener) {
        this.f19997a = i2;
        this.b = i3;
        this.c = str;
        this.d = onClickListener;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.yeelight_dialog_userdefine);
        this.e = (Button) findViewById(R.id.btn_del);
        this.f = (Button) findViewById(R.id.btn_cancel);
        this.g = (TextView) findViewById(R.id.txt_color_value);
        this.h = (TextView) findViewById(R.id.txt_color_name);
        this.i = (TextView) findViewById(R.id.txt_bright_value);
        this.j = (ImageView) findViewById(R.id.img_color_value);
        TextView textView = this.g;
        textView.setText(getContext().getString(R.string.yeelight_color_value) + "R：" + Color.red(this.f19997a) + "/ G:" + Color.green(this.f19997a) + "/ B:" + Color.blue(this.f19997a));
        this.h.setText(this.c);
        TextView textView2 = this.i;
        StringBuilder sb = new StringBuilder();
        sb.append(getContext().getString(R.string.yeelight_bright_value));
        sb.append(this.b);
        sb.append(Operators.MOD);
        textView2.setText(sb.toString());
        ((GradientDrawable) this.j.getDrawable()).setColor(this.f19997a);
        this.e.setOnClickListener(this.d);
        this.f.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                YeelightUserDefineDialog.this.dismiss();
            }
        });
    }
}
