package a.a.a.e.d;

import a.a.a.e.c.f;
import a.a.a.e.c.g;
import a.a.a.e.c.i;
import a.a.a.h.a;
import a.a.a.h.b;
import android.content.Intent;
import android.os.Bundle;
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
import java.util.List;

public class a extends a.a.a.e.a implements b.C0015b, View.OnClickListener, CircleRoadProgress.b {
    public f b;
    public b c;
    public a.a.a.b d;
    public TextView e;
    public Button f;
    public a.a.a.h.a g;

    /* renamed from: a.a.a.e.d.a$a  reason: collision with other inner class name */
    public class C0004a extends a.c {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ i.b f387a;
        public final /* synthetic */ i.b b;

        public C0004a(i.b bVar, i.b bVar2) {
            this.f387a = bVar;
            this.b = bVar2;
        }

        public boolean a() {
            a.this.f().a(a.this.d(), (Boolean) true);
            a aVar = a.this;
            a.a.a.b unused = aVar.d = new a.a.a.b(aVar.b.s(), this.f387a.a(), false, true);
            return true;
        }

        public void b() {
            a.this.f().a(a.this.d(), (Boolean) false);
            a aVar = a.this;
            a.a.a.b unused = aVar.d = new a.a.a.b(aVar.b.s(), this.b.a(), true, true);
        }
    }

    public static a a(f fVar) {
        a aVar = new a();
        Bundle bundle = new Bundle();
        bundle.putParcelable("arg_dead_pixel_diagnose", fVar);
        aVar.setArguments(bundle);
        return aVar;
    }

    public void a() {
        if (this.d == null) {
            this.d = new a.a.a.b(this.b.s(), 4005, false);
        }
        a(this.d);
    }

    public g e() {
        return this.b;
    }

    public void g() {
        List<i.b> d2 = this.b.d();
        if (d2.size() < 2) {
            f().a(d(), (Boolean) true);
            this.d = new a.a.a.b(this.b.s(), 4001, false, true);
            return;
        }
        i.b bVar = d2.get(0);
        i.b bVar2 = d2.get(1);
        this.g = a.a.a.h.a.a("", getString(R.string.blink_dialog_msg), bVar.b(), bVar2.b(), false);
        this.g.a((a.c) new C0004a(bVar, bVar2));
        FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
        a.a.a.h.a aVar = this.g;
        beginTransaction.add((Fragment) aVar, aVar.getClass().getSimpleName());
        beginTransaction.commitAllowingStateLoss();
    }

    public final void h() {
        String name = b.class.getName();
        this.c = b.a(this.b.b(), this.b.c());
        this.c.setCancelable(false);
        this.c.a((b.C0015b) this);
        FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
        beginTransaction.add((Fragment) this.c, name);
        beginTransaction.commitAllowingStateLoss();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.diagnoseActionButton) {
            f().a();
            h();
        } else if (id == R.id.nextButton) {
            TextView textView = this.e;
            if (textView != null) {
                textView.setEnabled(false);
            }
            Button button = this.f;
            if (button != null) {
                button.setEnabled(false);
            }
            f().a(d(), (Boolean) true);
            this.d = new a.a.a.b(this.b.s(), 4001, false, true);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.b = (f) getArguments().getParcelable("arg_dead_pixel_diagnose");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_diagnose_base, viewGroup, false);
    }

    public void onPause() {
        f().a();
        super.onPause();
        b bVar = this.c;
        if (bVar != null) {
            bVar.dismissAllowingStateLoss();
        }
        a.a.a.h.a aVar = this.g;
        if (aVar != null) {
            aVar.dismissAllowingStateLoss();
        }
    }

    public void onResume() {
        super.onResume();
        f().a(c(), (CircleRoadProgress.b) this, this.b.q());
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.findViewById(R.id.nextButton).setOnClickListener(this);
        this.e = (TextView) view.findViewById(R.id.diagnoseActionButton);
        TextView textView = this.e;
        int i = 0;
        if (textView != null) {
            textView.setOnClickListener(this);
            this.e.setVisibility(0);
            this.e.setText(R.string.otex_start);
        }
        TextView textView2 = (TextView) view.findViewById(R.id.diagnoseTitle);
        if (textView2 != null) {
            textView2.setText(e().l());
        }
        TextView textView3 = (TextView) view.findViewById(R.id.diagnoseMessage);
        if (textView3 != null) {
            textView3.setText(e().j());
        }
        this.f = (Button) view.findViewById(R.id.nextButton);
        Button button = this.f;
        if (button != null) {
            if (!e().o()) {
                i = 8;
            }
            button.setVisibility(i);
            this.f.setText(e().m());
            this.f.setOnClickListener(this);
        }
    }
}
