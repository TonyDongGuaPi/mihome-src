package a.a.a.e.b;

import a.a.a.e.c.g;
import a.a.a.e.c.j;
import a.a.a.h.a;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import in.cashify.otex.R;
import in.cashify.otex.widget.CircleRoadProgress;
import in.cashify.otex.widget.DiagnoseMicHeaderView;

public class e extends a.a.a.e.a implements CircleRoadProgress.b {
    public j b;
    public boolean c;
    public a.a.a.b d;
    public DiagnoseMicHeaderView e;
    public boolean f;
    public Button g;
    public boolean h;

    public class a implements Runnable {
        public a() {
        }

        public void run() {
            View b = e.this.f().b();
            if (b instanceof DiagnoseMicHeaderView) {
                e.this.a((DiagnoseMicHeaderView) b);
            }
        }
    }

    public class b extends a.c {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ a.a.a.h.a f376a;

        public b(a.a.a.h.a aVar) {
            this.f376a = aVar;
        }

        public boolean a() {
            this.f376a.dismissAllowingStateLoss();
            if (e.this.e != null) {
                e.this.e.c();
            }
            e.this.f().a(e.this.c(), (CircleRoadProgress.b) e.this);
            return true;
        }

        public void b() {
            this.f376a.dismissAllowingStateLoss();
            boolean unused = e.this.f = true;
            e.this.f().a(-1, (CircleRoadProgress.b) e.this);
            e.this.f().a(e.this.d(), (Boolean) true);
            a.a.a.b unused2 = e.this.d = new a.a.a.b("me", 4001, false, true);
        }
    }

    public static e a(j jVar) {
        e eVar = new e();
        Bundle bundle = new Bundle();
        bundle.putParcelable("arg_mic_diagnose", jVar);
        eVar.setArguments(bundle);
        return eVar;
    }

    public void a() {
        DiagnoseMicHeaderView diagnoseMicHeaderView;
        if (!this.f && (diagnoseMicHeaderView = this.e) != null) {
            diagnoseMicHeaderView.a();
            boolean z = this.e.e >= this.b.b();
            if (this.e.e > -1 && !z) {
                g();
                return;
            } else if (!this.h) {
                this.h = true;
                this.d = new a.a.a.b("me", Integer.valueOf(this.e.e), z);
                f().a(-1, (CircleRoadProgress.b) this);
                f().a(d(), Boolean.valueOf(!this.d.c()));
                return;
            }
        }
        a(this.d);
    }

    public final void a(DiagnoseMicHeaderView diagnoseMicHeaderView) {
        this.e = diagnoseMicHeaderView;
        if (a("android.permission.RECORD_AUDIO")) {
            DiagnoseMicHeaderView diagnoseMicHeaderView2 = this.e;
            if (diagnoseMicHeaderView2 != null) {
                diagnoseMicHeaderView2.c();
            }
            f().a(c(), (CircleRoadProgress.b) this);
            Button button = this.g;
            if (button != null) {
                button.setEnabled(true);
            }
        } else if (!this.c) {
            h();
            this.c = true;
        } else if (getActivity() == null || a((Context) getActivity(), 27) || this.c) {
            this.d = new a.a.a.b("me", 4002, false);
            this.f = true;
            f().a(-1, (CircleRoadProgress.b) this);
            f().a(d(), (Boolean) true);
        } else {
            b();
            this.c = true;
        }
    }

    public g e() {
        return this.b;
    }

    public final void g() {
        if (isAdded()) {
            a.a.a.h.a a2 = a.a.a.h.a.a(getString(R.string.otex_mic_alert_title), e().k(), e().n(), e().m(), false);
            a2.a((a.c) new b(a2));
            FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
            beginTransaction.add((Fragment) a2, a2.getClass().getSimpleName());
            beginTransaction.commitAllowingStateLoss();
        }
    }

    public final void h() {
        requestPermissions(new String[]{"android.permission.RECORD_AUDIO"}, 0);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.nextButton) {
            this.f = true;
            Button button = this.g;
            if (button != null) {
                button.setEnabled(false);
            }
            this.d = new a.a.a.b("me", 4001, false, true);
            f().a(d(), (Boolean) true);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.b = (j) getArguments().getParcelable("arg_mic_diagnose");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_mic_diagnose, viewGroup, false);
    }

    public void onPause() {
        DiagnoseMicHeaderView diagnoseMicHeaderView = this.e;
        if (diagnoseMicHeaderView != null) {
            diagnoseMicHeaderView.d();
        }
        f().a();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        View b2 = f().b();
        if (!(b2 instanceof DiagnoseMicHeaderView)) {
            f().c();
            new Handler(Looper.getMainLooper()).postDelayed(new a(), 500);
            return;
        }
        a((DiagnoseMicHeaderView) b2);
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
