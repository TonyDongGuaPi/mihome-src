package a.a.a.e.b;

import a.a.a.b;
import a.a.a.e.c.g;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.xiaomi.smarthome.framework.api.model.AreaPropInfo;
import in.cashify.otex.R;
import in.cashify.otex.widget.CircleRoadProgress;
import java.util.ArrayList;

public class a extends a.a.a.e.a implements CircleRoadProgress.b {
    public final ArrayList<b> b = new ArrayList<>();
    public a.a.a.e.c.b c;
    public b d;
    public Button e;

    public static a a(a.a.a.e.c.b bVar) {
        a aVar = new a();
        Bundle bundle = new Bundle();
        bundle.putParcelable("arg_battery_diagnose_context", bVar);
        aVar.setArguments(bundle);
        return aVar;
    }

    public void a() {
        b bVar = this.d;
        if (bVar != null) {
            a(bVar);
        } else {
            a(this.b);
        }
    }

    public g e() {
        return this.c;
    }

    public void g() {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
        if (getActivity() != null) {
            Intent registerReceiver = getActivity().registerReceiver((BroadcastReceiver) null, intentFilter);
            if (registerReceiver == null) {
                this.b.add(new b("btl", 0, false));
                f().a(d(), (Boolean) true);
            } else if (registerReceiver.getBooleanExtra("present", false)) {
                int intExtra = registerReceiver.getIntExtra("health", -1);
                int intExtra2 = registerReceiver.getIntExtra("level", -1);
                int intExtra3 = registerReceiver.getIntExtra("plugged", -1);
                int intExtra4 = registerReceiver.getIntExtra("scale", -1);
                int intExtra5 = registerReceiver.getIntExtra(AreaPropInfo.n, -1);
                int intExtra6 = registerReceiver.getIntExtra("voltage", -1);
                String stringExtra = registerReceiver.getStringExtra("technology");
                this.b.add(new b("bh", Integer.valueOf(intExtra), intExtra != -1));
                this.b.add(new b("bl", Integer.valueOf(intExtra2), intExtra2 != -1));
                this.b.add(new b("bp", Integer.valueOf(intExtra3), intExtra3 != -1));
                this.b.add(new b("bs", Integer.valueOf(intExtra4), intExtra4 != -1));
                this.b.add(new b("bt", Integer.valueOf(intExtra5), intExtra5 != -1));
                this.b.add(new b("bv", Integer.valueOf(intExtra6), intExtra6 != -1));
                this.b.add(new b("btl", stringExtra, true));
                f().a(d(), (Boolean) false);
            }
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.nextButton) {
            Button button = this.e;
            if (button != null) {
                button.setEnabled(false);
            }
            f().a(d(), (Boolean) true);
            this.d = new b("btl", 4001, false, true);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.c = (a.a.a.e.c.b) getArguments().getParcelable("arg_battery_diagnose_context");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_battery_diagnose, viewGroup, false);
    }

    public void onPause() {
        f().a();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        f().a(c(), (CircleRoadProgress.b) this);
        g();
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
        this.e = (Button) view.findViewById(R.id.nextButton);
        Button button = this.e;
        if (button != null) {
            button.setVisibility(e().o() ? 0 : 8);
            this.e.setText(e().m());
            this.e.setOnClickListener(this);
        }
    }
}
