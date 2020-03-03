package a.a.a.e.d;

import a.a.a.b;
import a.a.a.e.a;
import a.a.a.e.c.g;
import a.a.a.e.c.m;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import in.cashify.otex.R;
import in.cashify.otex.diagnose.manual.TouchCalibrationActivity;
import in.cashify.otex.widget.CircleRoadProgress;

public class c extends a implements View.OnClickListener, CircleRoadProgress.b {
    public m b;
    public long c;
    public b d;
    public boolean e;
    public TextView f;
    public Button g;

    public static c a(m mVar) {
        c cVar = new c();
        Bundle bundle = new Bundle();
        bundle.putParcelable("arg_touch_diagnose", mVar);
        cVar.setArguments(bundle);
        return cVar;
    }

    public void a() {
        if (this.d == null) {
            this.d = new b("sdr", 4005, false);
        }
        a(this.d);
    }

    public final void a(boolean z) {
        TextView textView = this.f;
        if (textView != null) {
            textView.setEnabled(z);
        }
    }

    public g e() {
        return this.b;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 8 && i2 == -1 && intent != null) {
            boolean z = false;
            int intExtra = intent.getIntExtra("result", 0);
            if (intExtra == 100) {
                z = true;
            }
            this.e = z;
            this.d = new b("sdr", Integer.valueOf(intExtra), this.e);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.diagnoseActionButton) {
            f().a();
            Intent intent = new Intent(getActivity(), TouchCalibrationActivity.class);
            intent.putExtra("arg_context", this.b);
            startActivityForResult(intent, 8);
        } else if (id == R.id.nextButton) {
            a(false);
            Button button = this.g;
            if (button != null) {
                button.setEnabled(false);
            }
            this.d = new b("sdr", 4001, false, true);
            f().a(d(), (Boolean) true);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.b = (m) getArguments().getParcelable("arg_touch_diagnose");
        }
        this.c = c();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_diagnose_base, viewGroup, false);
    }

    public void onPause() {
        f().a();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        a(true);
        f().a(this.c, (CircleRoadProgress.b) this);
        if (this.d != null) {
            f().a(d(), Boolean.valueOf(true ^ this.e));
        }
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.findViewById(R.id.nextButton).setOnClickListener(this);
        this.f = (TextView) view.findViewById(R.id.diagnoseActionButton);
        TextView textView = this.f;
        int i = 0;
        if (textView != null) {
            textView.setOnClickListener(this);
            this.f.setVisibility(0);
            this.f.setText(R.string.otex_start);
        }
        TextView textView2 = (TextView) view.findViewById(R.id.diagnoseTitle);
        if (this.f != null) {
            textView2.setText(e().l());
        }
        TextView textView3 = (TextView) view.findViewById(R.id.diagnoseMessage);
        if (textView3 != null) {
            textView3.setText(e().j());
        }
        this.g = (Button) view.findViewById(R.id.nextButton);
        Button button = this.g;
        if (button != null) {
            if (!e().o()) {
                i = 8;
            }
            button.setVisibility(i);
            this.g.setText(e().m());
            this.g.setOnClickListener(this);
        }
    }
}
