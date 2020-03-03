package a.a.a.e.b;

import a.a.a.e.a;
import a.a.a.e.c.c;
import a.a.a.e.c.g;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.xiaomi.mishopsdk.util.Constants;
import in.cashify.otex.R;
import in.cashify.otex.widget.CircleRoadProgress;

public class b extends a implements CircleRoadProgress.b {
    public boolean b;
    public boolean c;
    public c d;
    public a.a.a.b e;
    public Button f;

    public static b a(c cVar) {
        b bVar = new b();
        Bundle bundle = new Bundle();
        bundle.putParcelable("arg_bluetooth_diagnose", cVar);
        bVar.setArguments(bundle);
        return bVar;
    }

    public void a() {
        if (this.e == null) {
            this.e = new a.a.a.b("btd", 4005, false);
        }
        a(this.e);
    }

    public g e() {
        return this.d;
    }

    public final void g() {
        if (!a("android.permission.BLUETOOTH")) {
            if (this.b) {
                this.e = new a.a.a.b("btd", 4002, false);
                f().a(d(), (CircleRoadProgress.b) this);
                return;
            }
            h();
            this.b = true;
        } else if (getActivity() == null || a((Context) getActivity(), 39) || this.b) {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter == null) {
                this.e = new a.a.a.b("btd", 4003, false);
                f().a(d(), (CircleRoadProgress.b) this);
                return;
            }
            f().a(c(), (CircleRoadProgress.b) this);
            if (defaultAdapter.isEnabled()) {
                this.e = new a.a.a.b("btd", 1, true);
            } else if (a("android.permission.BLUETOOTH_ADMIN")) {
                boolean enable = defaultAdapter.enable();
                this.e = new a.a.a.b("btd", Integer.valueOf(enable ? 1 : 0), enable);
                defaultAdapter.disable();
            } else {
                c cVar = this.d;
                if (cVar == null || !cVar.b() || this.c) {
                    this.e = new a.a.a.b("btd", 0, false);
                    f().a(d(), (Boolean) true);
                } else if (isAdded()) {
                    Intent intent = new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE");
                    intent.addFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                    startActivityForResult(intent, 1);
                }
            }
            if (this.e != null) {
                f().a(d(), Boolean.valueOf(!this.e.c()));
            }
        } else {
            b();
            this.b = true;
        }
    }

    public final void h() {
        requestPermissions(new String[]{"android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH"}, 0);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        this.c = z;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.nextButton) {
            Button button = this.f;
            if (button != null) {
                button.setEnabled(false);
            }
            this.e = new a.a.a.b("btd", 4001, false, true);
            f().a(d(), (Boolean) true);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.d = (c) getArguments().getParcelable("arg_bluetooth_diagnose");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_bluetooth_diagnose, viewGroup, false);
    }

    public void onPause() {
        f().a();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        g();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle != null) {
            bundle.putBoolean("enable_request", this.c);
        }
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
        this.f = (Button) view.findViewById(R.id.nextButton);
        Button button = this.f;
        if (button != null) {
            button.setVisibility(e().o() ? 0 : 8);
            this.f.setText(e().m());
            this.f.setOnClickListener(this);
        }
    }

    public void onViewStateRestored(@Nullable Bundle bundle) {
        super.onViewStateRestored(bundle);
        if (bundle != null) {
            this.c = bundle.getBoolean("enable_request");
        }
    }
}
