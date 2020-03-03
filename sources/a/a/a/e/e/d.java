package a.a.a.e.e;

import a.a.a.b;
import a.a.a.e.c.g;
import a.a.a.e.c.o;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.view.WindowCallbackWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import in.cashify.otex.ExchangeManager;
import in.cashify.otex.R;
import in.cashify.otex.widget.CircleRoadProgress;

public class d extends a.a.a.e.a implements CircleRoadProgress.b {
    public o b;
    public boolean c;
    public boolean d;
    public b e;
    public Window.Callback f;
    public Button g;
    public boolean h;

    public class a extends WindowCallbackWrapper {
        public a(Window.Callback callback) {
            super(callback);
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return d.this.a(keyEvent.getKeyCode()) || super.dispatchKeyEvent(keyEvent);
        }
    }

    public static d a(o oVar) {
        d dVar = new d();
        Bundle bundle = new Bundle();
        bundle.putParcelable("arg_volume_diagnose", oVar);
        dVar.setArguments(bundle);
        return dVar;
    }

    public void a() {
        if (this.e != null || this.h) {
            a(this.e);
            return;
        }
        this.h = true;
        this.e = new b("vb", 4005, false);
        f().a(-1, (CircleRoadProgress.b) this);
        f().a(d(), Boolean.valueOf(true ^ this.e.c()));
    }

    public final boolean a(int i) {
        boolean z;
        ExchangeManager exchangeManager;
        int i2;
        if (i == 25) {
            this.d = true;
            if (!this.c) {
                exchangeManager = f();
                i2 = R.drawable.ic_volume_down;
                exchangeManager.a(i2);
                z = true;
                if (this.c && this.d) {
                    h();
                    f().a(d(), (Boolean) false);
                    this.e = new b("vb", 1, true);
                }
                return z;
            }
        } else if (i == 24) {
            this.c = true;
            if (!this.d) {
                exchangeManager = f();
                i2 = R.drawable.ic_volume_up;
                exchangeManager.a(i2);
                z = true;
                h();
                f().a(d(), (Boolean) false);
                this.e = new b("vb", 1, true);
                return z;
            }
        } else {
            z = false;
            h();
            f().a(d(), (Boolean) false);
            this.e = new b("vb", 1, true);
            return z;
        }
        exchangeManager = f();
        i2 = R.drawable.ic_volume_up_down;
        exchangeManager.a(i2);
        z = true;
        h();
        f().a(d(), (Boolean) false);
        this.e = new b("vb", 1, true);
        return z;
    }

    public g e() {
        return this.b;
    }

    public final void g() {
        try {
            this.f = getActivity().getWindow().getCallback();
            getActivity().getWindow().setCallback(new a(this.f));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void h() {
        try {
            Window window = getActivity().getWindow();
            if (this.f != window.getCallback()) {
                window.setCallback(this.f);
            }
        } catch (Throwable unused) {
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.nextButton) {
            Button button = this.g;
            if (button != null) {
                button.setEnabled(false);
            }
            this.e = new b("vb", 0, false, true);
            f().a(d(), (Boolean) true);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.b = (o) getArguments().getParcelable("arg_volume_diagnose");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_volume_diagnose, viewGroup, false);
    }

    public void onPause() {
        h();
        f().a();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        g();
        f().a(c(), (CircleRoadProgress.b) this);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        TextView textView = (TextView) view.findViewById(R.id.diagnoseTitle);
        if (textView != null) {
            textView.setText(e().l());
        }
        TextView textView2 = (TextView) view.findViewById(R.id.diagnoseMessage);
        if (textView2 != null) {
            textView2.setText(e().j());
        }
        this.g = (Button) view.findViewById(R.id.nextButton);
        Button button = this.g;
        if (button != null) {
            button.setVisibility(e().o() ? 0 : 8);
            this.g.setText(e().m());
            this.g.setOnClickListener(this);
        }
    }
}
