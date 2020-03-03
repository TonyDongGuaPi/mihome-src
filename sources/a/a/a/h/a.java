package a.a.a.h;

import a.a.a.d;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.xiaomi.mipush.sdk.PushMessageHelper;
import com.xiaomi.smarthome.device.bluetooth.ui.BleMatchListBatchActivity;
import in.cashify.otex.R;
import java.util.Timer;
import java.util.TimerTask;

public class a extends DialogFragment implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    public c f410a;
    public TextView b;
    public ProgressBar c;
    public Timer d;
    public boolean e;

    /* renamed from: a.a.a.h.a$a  reason: collision with other inner class name */
    public class C0013a extends TimerTask {
        public C0013a() {
        }

        public void run() {
            a.this.b();
        }
    }

    public class b implements Runnable {
        public b() {
        }

        public void run() {
            a.this.b.setVisibility(0);
            a.this.c.setVisibility(4);
        }
    }

    public static abstract class c {
        public abstract boolean a();

        public abstract void b();

        public void c() {
        }
    }

    public static a a(String str, String str2, String str3, String str4, boolean z) {
        a aVar = new a();
        aVar.setStyle(1, 0);
        Bundle bundle = new Bundle();
        bundle.putString(BleMatchListBatchActivity.KEY_TITLE, str);
        bundle.putString(PushMessageHelper.j, str2);
        bundle.putString("key_button_positive", str3);
        bundle.putString("key_button_negative", str4);
        bundle.putBoolean("key_cancelable", z);
        aVar.setArguments(bundle);
        return aVar;
    }

    public final void a() {
        if (this.b.getVisibility() != 8 || this.c.getVisibility() != 0) {
            Timer timer = this.d;
            if (timer != null) {
                timer.cancel();
                this.d = null;
            }
            this.d = new Timer();
            this.b.setVisibility(4);
            this.c.setVisibility(0);
            this.d.schedule(new C0013a(), 1000);
        }
    }

    public void a(c cVar) {
        if (cVar != null) {
            this.f410a = cVar;
            return;
        }
        throw new NullPointerException("Listener must not be null");
    }

    public final void b() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new b());
        }
    }

    public void onClick(View view) {
        if (this.f410a != null) {
            int id = view.getId();
            if (id == R.id.customDialogButtonOk) {
                this.e = true;
                if (this.f410a.a()) {
                    dismiss();
                } else {
                    a();
                }
            } else if (id == R.id.customDialogButtonCancel) {
                this.e = true;
                dismiss();
                this.f410a.b();
            }
        }
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            String string = arguments.getString(BleMatchListBatchActivity.KEY_TITLE);
            String string2 = arguments.getString(PushMessageHelper.j);
            String string3 = arguments.getString("key_button_positive");
            String string4 = arguments.getString("key_button_negative");
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            View inflate = getActivity().getLayoutInflater().inflate(R.layout.layout_custom_dialog, (ViewGroup) null);
            builder.setView(inflate);
            TextView textView = (TextView) inflate.findViewById(R.id.customDialogTitle);
            if (string == null || string.isEmpty()) {
                textView.setVisibility(8);
            } else {
                textView.setText(arguments.getString(BleMatchListBatchActivity.KEY_TITLE));
            }
            this.b = (TextView) inflate.findViewById(R.id.customDialogMessage);
            this.b.setText(string2);
            this.c = (ProgressBar) inflate.findViewById(R.id.progress);
            Button button = (Button) inflate.findViewById(R.id.customDialogButtonOk);
            button.setAllCaps(true);
            if (TextUtils.isEmpty(string3)) {
                button.setVisibility(8);
            } else {
                button.setText(string3);
                button.setOnClickListener(this);
            }
            Button button2 = (Button) inflate.findViewById(R.id.customDialogButtonCancel);
            button2.setAllCaps(true);
            if (TextUtils.isEmpty(string4)) {
                button2.setVisibility(8);
            } else {
                button2.setText(string4);
                button2.setOnClickListener(this);
            }
            AlertDialog create = builder.create();
            setCancelable(getArguments().getBoolean("key_cancelable"));
            Window window = create.getWindow();
            if (window != null) {
                window.requestFeature(8);
                window.setBackgroundDrawable(new ColorDrawable(d.a((Context) getActivity(), 17170445)));
            }
            return create;
        }
        throw new IllegalStateException("Use newInstance method to create Dialog");
    }

    public void onDismiss(DialogInterface dialogInterface) {
        c cVar;
        super.onDismiss(dialogInterface);
        if (!this.e && (cVar = this.f410a) != null) {
            cVar.c();
        }
    }
}
