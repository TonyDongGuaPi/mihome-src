package in.cashify.otex.diagnose.semi;

import a.a.a.b;
import a.a.a.e.a;
import a.a.a.e.c.e;
import a.a.a.e.c.g;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import in.cashify.otex.R;
import in.cashify.otex.widget.CircleRoadProgress;

public class ChargerDiagnoseFragment extends a implements CircleRoadProgress.b {
    public ChargingMonitor b;
    public e c;
    public TextView d;
    public b e;
    public Button f;
    public boolean g;

    public class ChargingMonitor extends BroadcastReceiver {
        public ChargingMonitor() {
        }

        public void a(Context context) {
            context.registerReceiver(this, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        }

        public void b(Context context) {
            try {
                context.unregisterReceiver(this);
            } catch (Throwable unused) {
            }
        }

        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.BATTERY_CHANGED".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("plugged", -1);
                boolean z = intExtra == 2;
                boolean z2 = intExtra == 1;
                if (z || z2) {
                    b(context);
                    if (ChargerDiagnoseFragment.this.d != null) {
                        ChargerDiagnoseFragment.this.d.setText(ChargerDiagnoseFragment.this.e().k());
                    }
                    b unused = ChargerDiagnoseFragment.this.e = new b("bp", 1, true, false);
                    ChargerDiagnoseFragment.this.f().a(ChargerDiagnoseFragment.this.d(), (Boolean) false);
                }
            }
        }
    }

    public static ChargerDiagnoseFragment a(e eVar) {
        ChargerDiagnoseFragment chargerDiagnoseFragment = new ChargerDiagnoseFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("arg_charger_diagnose", eVar);
        chargerDiagnoseFragment.setArguments(bundle);
        return chargerDiagnoseFragment;
    }

    public void a() {
        if (this.e != null || this.g) {
            a(this.e);
            return;
        }
        this.g = true;
        this.e = new b("btl", 4005, false);
        f().a(-1, (CircleRoadProgress.b) this);
        f().a(d(), Boolean.valueOf(true ^ this.e.c()));
    }

    public g e() {
        return this.c;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.b = new ChargingMonitor();
        FragmentActivity activity = getActivity();
        if (isAdded() && activity != null) {
            this.b.a(activity.getApplicationContext());
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.nextButton) {
            Button button = this.f;
            if (button != null) {
                button.setEnabled(false);
            }
            this.e = new b("bp", 4001, false, true);
            f().a(d(), (Boolean) true);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.c = (e) getArguments().getParcelable("arg_charger_diagnose");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_diagnose_base, viewGroup, false);
    }

    public void onDetach() {
        if (this.b != null) {
            FragmentActivity activity = getActivity();
            if (isAdded() && activity != null) {
                this.b.b(getActivity().getApplicationContext());
            }
        }
        super.onDetach();
    }

    public void onPause() {
        if (this.b != null) {
            FragmentActivity activity = getActivity();
            if (isAdded() && activity != null) {
                this.b.b(activity.getApplicationContext());
            }
        }
        f().a();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        Button button = this.f;
        if (button != null) {
            button.setEnabled(true);
        }
        if (this.b != null) {
            FragmentActivity activity = getActivity();
            if (isAdded() && activity != null) {
                this.b.a(activity.getApplicationContext());
            }
        }
        f().a(c(), (CircleRoadProgress.b) this, this.c.q());
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        TextView textView = (TextView) view.findViewById(R.id.diagnoseTitle);
        if (textView != null) {
            textView.setText(e().l());
        }
        this.d = (TextView) view.findViewById(R.id.diagnoseMessage);
        TextView textView2 = this.d;
        if (textView2 != null) {
            textView2.setText(e().j());
        }
        this.f = (Button) view.findViewById(R.id.nextButton);
        Button button = this.f;
        if (button != null) {
            button.setVisibility(e().o() ? 0 : 8);
            this.f.setText(e().m());
            this.f.setOnClickListener(this);
        }
    }
}
