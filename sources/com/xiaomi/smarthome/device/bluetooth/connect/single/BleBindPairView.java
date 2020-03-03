package com.xiaomi.smarthome.device.bluetooth.connect.single;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.miio.TitleBarUtil;

public class BleBindPairView extends RelativeLayout {

    /* renamed from: a  reason: collision with root package name */
    private View f15185a;
    private ImageView b;
    private TextView c;
    /* access modifiers changed from: private */
    public TextView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private TextView h;
    private TextView i;
    private TextView j;
    private TextView k;
    /* access modifiers changed from: private */
    public OnPairCallback l;
    /* access modifiers changed from: private */
    public Handler m;
    /* access modifiers changed from: private */
    public int n = 30;
    /* access modifiers changed from: private */
    public Runnable o = new Runnable() {
        public void run() {
            if (BleBindPairView.this.d.getVisibility() == 0) {
                BleBindPairView.access$110(BleBindPairView.this);
                if (BleBindPairView.this.n > 0) {
                    TextView access$000 = BleBindPairView.this.d;
                    access$000.setText(BleBindPairView.this.n + "s");
                    BleBindPairView.this.m.postDelayed(BleBindPairView.this.o, 1000);
                    return;
                }
                BleBindPairView.this.b();
            }
        }
    };

    public interface OnPairCallback {
        void a();

        void b();
    }

    static /* synthetic */ int access$110(BleBindPairView bleBindPairView) {
        int i2 = bleBindPairView.n;
        bleBindPairView.n = i2 - 1;
        return i2;
    }

    public BleBindPairView(Context context) {
        super(context);
        a();
    }

    public BleBindPairView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public BleBindPairView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a();
    }

    private void a() {
        this.m = new Handler();
        LayoutInflater.from(getContext()).inflate(R.layout.ble_bind_pair_layout, this);
        this.f15185a = findViewById(R.id.paircode_title);
        this.b = (ImageView) findViewById(R.id.close_btn);
        this.c = (TextView) findViewById(R.id.paircode_tip);
        this.d = (TextView) findViewById(R.id.paircode_countdown);
        this.e = (TextView) findViewById(R.id.retry_btn);
        this.f = (TextView) findViewById(R.id.pair_code_text_1);
        this.g = (TextView) findViewById(R.id.pair_code_text_2);
        this.h = (TextView) findViewById(R.id.pair_code_text_3);
        this.i = (TextView) findViewById(R.id.pair_code_text_4);
        this.j = (TextView) findViewById(R.id.pair_code_text_5);
        this.k = (TextView) findViewById(R.id.pair_code_text_6);
        this.e.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (BleBindPairView.this.l != null) {
                    BleBindPairView.this.l.b();
                }
            }
        });
        this.b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BleBindPairView.this.c();
            }
        });
        this.c.setText(R.string.ble_auth_pair_code_tips);
        TextView textView = this.d;
        textView.setText(this.n + "s");
        this.m.postDelayed(this.o, 1000);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        offsetTitleBar();
    }

    public void offsetTitleBar() {
        int a2;
        if (this.f15185a != null && (a2 = TitleBarUtil.a()) != -1) {
            if (this.f15185a.getLayoutParams().height > 0) {
                this.f15185a.getLayoutParams().height += a2;
            }
            this.f15185a.setPadding(0, a2, 0, 0);
            this.f15185a.setLayoutParams(this.f15185a.getLayoutParams());
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.l = null;
        this.m.removeCallbacks(this.o);
    }

    public void setPairCallback(OnPairCallback onPairCallback) {
        this.l = onPairCallback;
    }

    public void setPaircode(String str) {
        if (str != null && str.length() >= 6) {
            TextView textView = this.f;
            textView.setText(str.charAt(0) + "");
            TextView textView2 = this.g;
            textView2.setText(str.charAt(1) + "");
            TextView textView3 = this.h;
            textView3.setText(str.charAt(2) + "");
            TextView textView4 = this.i;
            textView4.setText(str.charAt(3) + "");
            TextView textView5 = this.j;
            textView5.setText(str.charAt(4) + "");
            TextView textView6 = this.k;
            textView6.setText(str.charAt(5) + "");
        }
    }

    public void onBack() {
        c();
    }

    public void onFailed() {
        this.c.setText(R.string.ble_auth_pair_code_failed);
        this.c.setTextColor(getResources().getColor(R.color.important_info));
        this.e.setVisibility(0);
        this.d.setVisibility(8);
        this.m.removeCallbacks(this.o);
    }

    /* access modifiers changed from: private */
    public void b() {
        this.c.setText(R.string.ble_auth_pair_code_expired);
        this.c.setTextColor(getResources().getColor(R.color.black));
        this.e.setVisibility(0);
        this.d.setVisibility(8);
        this.f.setTextColor(getResources().getColor(R.color.grey_e5));
        this.g.setTextColor(getResources().getColor(R.color.grey_e5));
        this.h.setTextColor(getResources().getColor(R.color.grey_e5));
        this.i.setTextColor(getResources().getColor(R.color.grey_e5));
        this.j.setTextColor(getResources().getColor(R.color.grey_e5));
        this.k.setTextColor(getResources().getColor(R.color.grey_e5));
        this.m.removeCallbacks(this.o);
    }

    /* access modifiers changed from: private */
    public void c() {
        new MLAlertDialog.Builder(getContext()).a((int) R.string.ble_new_cancel_dialog_title).b((int) R.string.ble_new_cancel_dialog_message).a((int) R.string.ble_new_cancel_dialog_ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (BleBindPairView.this.l != null) {
                    BleBindPairView.this.l.a();
                }
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).b().show();
    }
}
