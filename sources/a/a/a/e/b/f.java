package a.a.a.e.b;

import a.a.a.b;
import a.a.a.e.a;
import a.a.a.e.c.g;
import a.a.a.e.c.p;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Build;
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
import java.util.ArrayList;

public class f extends a implements CircleRoadProgress.b {
    public p b;
    public boolean c;
    public boolean d;
    public final ArrayList<b> e = new ArrayList<>();
    public Button f;

    public static f a(p pVar) {
        f fVar = new f();
        Bundle bundle = new Bundle();
        bundle.putParcelable("arg_wifi_diagnose", pVar);
        fVar.setArguments(bundle);
        return fVar;
    }

    public void a() {
        a(this.e);
    }

    public final void a(WifiManager wifiManager) {
        this.e.add(new b("wf", 1, true));
        b(wifiManager);
        f().a(d(), (Boolean) false);
    }

    public final void b(WifiManager wifiManager) {
        b bVar;
        ArrayList<b> arrayList;
        if (wifiManager != null) {
            try {
                int rssi = wifiManager.getConnectionInfo().getRssi();
                this.e.add(new b("wss", Integer.valueOf(rssi), rssi != -127));
            } catch (Exception unused) {
                this.e.add(new b("wss", -127, true));
            }
            try {
                this.e.add(new b("wls", Integer.valueOf(wifiManager.getConnectionInfo().getLinkSpeed()), true));
            } catch (Exception unused2) {
                this.e.add(new b("wls", -1, true));
            }
            try {
                if (Build.VERSION.SDK_INT >= 21) {
                    int frequency = wifiManager.getConnectionInfo().getFrequency();
                    arrayList = this.e;
                    bVar = new b("wss", Integer.valueOf(frequency), true);
                } else {
                    arrayList = this.e;
                    bVar = new b("wss", -1, true);
                }
                arrayList.add(bVar);
            } catch (Exception unused3) {
                this.e.add(new b("wss", -1, true));
            }
        }
    }

    public g e() {
        return this.b;
    }

    public final void g() {
        if (a("android.permission.ACCESS_WIFI_STATE")) {
            WifiManager wifiManager = null;
            try {
                wifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService("wifi");
            } catch (Exception unused) {
            }
            if (wifiManager == null) {
                this.e.add(new b("wf", 4003, false));
                f().a(d(), (CircleRoadProgress.b) this);
                return;
            }
            f().a(c(), (CircleRoadProgress.b) this);
            if (!wifiManager.isWifiEnabled()) {
                if (!a("android.permission.CHANGE_WIFI_STATE")) {
                    p pVar = this.b;
                    if (pVar == null || !pVar.b() || this.c) {
                        this.e.add(new b("wf", 4002, false));
                    } else if (isAdded()) {
                        Intent intent = new Intent("android.settings.WIFI_SETTINGS");
                        intent.addFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                        startActivityForResult(intent, 2);
                        return;
                    } else {
                        return;
                    }
                } else if (wifiManager.setWifiEnabled(true)) {
                    a(wifiManager);
                    wifiManager.setWifiEnabled(false);
                    return;
                } else {
                    this.e.add(new b("wf", 0, false));
                }
                h();
                return;
            }
            a(wifiManager);
        } else if (!this.d) {
            this.d = true;
            i();
        } else {
            this.e.add(new b("wf", 4002, false));
            f().a(d(), (CircleRoadProgress.b) this);
        }
    }

    public final void h() {
        f().a(d(), (Boolean) true);
    }

    public final void i() {
        requestPermissions(new String[]{"android.permission.CHANGE_WIFI_STATE", "android.permission.ACCESS_WIFI_STATE"}, 0);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.c = i == 2;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.nextButton) {
            Button button = this.f;
            if (button != null) {
                button.setEnabled(false);
            }
            f().a(d(), (Boolean) true);
            this.e.add(new b("wf", 4001, false, true));
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.b = (p) getArguments().getParcelable("arg_wifi_diagnose");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_wifi_diagnose, viewGroup, false);
    }

    public void onPause() {
        f().a();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
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
        this.f = (Button) view.findViewById(R.id.nextButton);
        Button button = this.f;
        if (button != null) {
            button.setVisibility(e().o() ? 0 : 8);
            this.f.setText(e().m());
            this.f.setOnClickListener(this);
        }
    }
}
