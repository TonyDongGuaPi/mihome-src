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

public class YeelightPresetDialog extends Dialog {

    /* renamed from: a  reason: collision with root package name */
    int f19995a;
    int b;
    String c;
    String d;
    Button e;
    TextView f;
    TextView g;
    TextView h;
    TextView i;
    ImageView j;

    public YeelightPresetDialog(Context context) {
        super(context, 16973840);
        DisplayUtils.a(getWindow());
    }

    public YeelightPresetDialog(Context context, int i2) {
        super(context, 16973840);
        DisplayUtils.a(getWindow());
    }

    public void a(int i2, int i3, String str, String str2) {
        this.f19995a = i2;
        this.b = i3;
        this.c = str;
        this.d = str2;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.yeelight_dialog_preset);
        this.e = (Button) findViewById(R.id.btn_ok);
        this.f = (TextView) findViewById(R.id.txt_color_value);
        this.g = (TextView) findViewById(R.id.txt_color_disc);
        this.h = (TextView) findViewById(R.id.txt_color_name);
        this.i = (TextView) findViewById(R.id.txt_bright_value);
        this.j = (ImageView) findViewById(R.id.img_color_value);
        TextView textView = this.f;
        textView.setText(getContext().getString(R.string.yeelight_color_value) + "R：" + Color.red(this.f19995a) + "/ G:" + Color.green(this.f19995a) + "/ B:" + Color.blue(this.f19995a));
        this.h.setText(this.c);
        this.g.setText(this.d);
        TextView textView2 = this.i;
        StringBuilder sb = new StringBuilder();
        sb.append(getContext().getString(R.string.yeelight_bright_value));
        sb.append(this.b);
        sb.append(Operators.MOD);
        textView2.setText(sb.toString());
        ((GradientDrawable) this.j.getDrawable()).setColor(this.f19995a);
        this.e.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                YeelightPresetDialog.this.dismiss();
            }
        });
    }
}
