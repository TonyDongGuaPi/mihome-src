package a.a.a.e.e;

import a.a.a.e.c.g;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import in.cashify.otex.R;
import in.cashify.otex.widget.CircleRoadProgress;

public class a extends a.a.a.e.a implements CircleRoadProgress.b {
    public a.a.a.e.c.a b;
    public b c;
    public a.a.a.b d;
    public Button e;
    public boolean f;

    public class b extends BroadcastReceiver {
        public b() {
        }

        public void onReceive(Context context, Intent intent) {
            int intExtra;
            if (intent.getAction() != null && intent.getAction().equals("android.intent.action.HEADSET_PLUG") && (intExtra = intent.getIntExtra("state", -1)) != 0 && intExtra == 1) {
                a.a.a.b unused = a.this.d = new a.a.a.b("aj", 1, true);
                a.this.f().a(a.this.d(), (Boolean) false);
            }
        }
    }

    public static a a(a.a.a.e.c.a aVar) {
        a aVar2 = new a();
        Bundle bundle = new Bundle();
        bundle.putParcelable("arg_audio_diagnose", aVar);
        aVar2.setArguments(bundle);
        return aVar2;
    }

    public void a() {
        if (this.d != null || this.f) {
            a(this.d);
            return;
        }
        this.f = true;
        this.d = new a.a.a.b("aj", 4005, false);
        f().a(-1, (CircleRoadProgress.b) this);
        f().a(d(), Boolean.valueOf(true ^ this.d.c()));
    }

    public g e() {
        return this.b;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.c = new b();
        try {
            getActivity().registerReceiver(this.c, new IntentFilter("android.intent.action.HEADSET_PLUG"));
        } catch (Throwable unused) {
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.nextButton) {
            Button button = this.e;
            if (button != null) {
                button.setEnabled(false);
            }
            this.d = new a.a.a.b("aj", 4001, false, true);
            f().a(d(), (Boolean) true);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.b = (a.a.a.e.c.a) getArguments().getParcelable("arg_audio_diagnose");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_audio_diagnose, viewGroup, false);
    }

    public void onDetach() {
        try {
            getActivity().unregisterReceiver(this.c);
        } catch (Throwable unused) {
        }
        super.onDetach();
    }

    public void onPause() {
        f().a();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        Button button = this.e;
        if (button != null) {
            button.setEnabled(true);
        }
        f().a(c(), (CircleRoadProgress.b) this, this.b.q());
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
