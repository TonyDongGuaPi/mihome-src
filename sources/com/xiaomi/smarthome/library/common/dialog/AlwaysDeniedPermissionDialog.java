package com.xiaomi.smarthome.library.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.nestscroll.UIUtils;

public class AlwaysDeniedPermissionDialog {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public String f18563a;
    /* access modifiers changed from: private */
    public String b;
    /* access modifiers changed from: private */
    public Context c;
    /* access modifiers changed from: private */
    public View.OnClickListener d;
    /* access modifiers changed from: private */
    public View.OnClickListener e;
    /* access modifiers changed from: private */
    public View f;
    /* access modifiers changed from: private */
    public DeniedPermissionDialog g;

    public void a() {
        if (this.g == null) {
            this.g = new DeniedPermissionDialog(this.c);
            this.g.setCancelable(false);
            this.g.show();
        }
    }

    private class DeniedPermissionDialog extends Dialog {
        public DeniedPermissionDialog(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            View unused = AlwaysDeniedPermissionDialog.this.f = LayoutInflater.from(AlwaysDeniedPermissionDialog.this.c.getApplicationContext()).inflate(R.layout.dialog_user_permission_denied, (ViewGroup) null);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2, 80);
            layoutParams.setMargins(UIUtils.a(8), UIUtils.a(8), UIUtils.a(8), UIUtils.a(8));
            AlwaysDeniedPermissionDialog.this.f.setLayoutParams(layoutParams);
            AlwaysDeniedPermissionDialog.this.b();
            setContentView(AlwaysDeniedPermissionDialog.this.f);
            Window window = getWindow();
            if (window != null) {
                View findViewById = findViewById(16908310);
                if (findViewById != null) {
                    findViewById.setVisibility(8);
                }
                window.setGravity(80);
                window.setBackgroundDrawable(new InsetDrawable(new ColorDrawable(0), UIUtils.a(8), UIUtils.a(8), UIUtils.a(8), UIUtils.a(8)));
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.width = -1;
                attributes.height = -2;
                window.setAttributes(attributes);
            }
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        TextView textView = (TextView) this.f.findViewById(R.id.dialog_title);
        if (!TextUtils.isEmpty(this.f18563a)) {
            textView.setText(this.f18563a);
        }
        if (!TextUtils.isEmpty(this.b)) {
            ((TextView) this.f.findViewById(R.id.dialog_content)).setText(this.b);
        }
        final Button button = (Button) this.f.findViewById(R.id.cancel);
        final Button button2 = (Button) this.f.findViewById(R.id.agree);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlwaysDeniedPermissionDialog.this.g.dismiss();
                if (AlwaysDeniedPermissionDialog.this.e != null) {
                    AlwaysDeniedPermissionDialog.this.e.onClick(button);
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlwaysDeniedPermissionDialog.this.g.dismiss();
                if (AlwaysDeniedPermissionDialog.this.d != null) {
                    AlwaysDeniedPermissionDialog.this.d.onClick(button2);
                }
            }
        });
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private String f18566a;
        private String b;
        private Context c;
        private View.OnClickListener d;
        private View.OnClickListener e;

        public Builder(Context context) {
            this.c = context;
        }

        public Builder a(String str) {
            this.f18566a = str;
            return this;
        }

        public Builder b(String str) {
            this.b = str;
            return this;
        }

        public Builder a(View.OnClickListener onClickListener) {
            this.d = onClickListener;
            return this;
        }

        public Builder b(View.OnClickListener onClickListener) {
            this.e = onClickListener;
            return this;
        }

        public AlwaysDeniedPermissionDialog a() {
            AlwaysDeniedPermissionDialog alwaysDeniedPermissionDialog = new AlwaysDeniedPermissionDialog();
            Context unused = alwaysDeniedPermissionDialog.c = this.c;
            String unused2 = alwaysDeniedPermissionDialog.b = this.b;
            String unused3 = alwaysDeniedPermissionDialog.f18563a = this.f18566a;
            View.OnClickListener unused4 = alwaysDeniedPermissionDialog.d = this.d;
            View.OnClickListener unused5 = alwaysDeniedPermissionDialog.e = this.e;
            return alwaysDeniedPermissionDialog;
        }
    }
}
