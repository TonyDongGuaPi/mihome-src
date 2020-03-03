package a.a.a.e.b;

import a.a.a.b;
import a.a.a.e.a;
import a.a.a.e.c.g;
import a.a.a.e.c.h;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import in.cashify.otex.R;
import in.cashify.otex.widget.CircleRoadProgress;

public class d extends a implements CircleRoadProgress.b {
    public LocationManager b;
    public h c;
    public b d;
    public boolean e;
    public boolean f;
    public Button g;

    public static d a(h hVar) {
        d dVar = new d();
        Bundle bundle = new Bundle();
        bundle.putParcelable("arg_gps_diagnose", hVar);
        dVar.setArguments(bundle);
        return dVar;
    }

    public void a() {
        if (this.d == null) {
            this.d = new b("gps", 4005, false);
        }
        a(this.d);
    }

    public final boolean a(Context context) {
        if (Build.VERSION.SDK_INT < 19) {
            String string = Settings.Secure.getString(context.getContentResolver(), "location_providers_allowed");
            return TextUtils.isEmpty(string) || !string.contains("gps");
        }
        try {
            int i = Settings.Secure.getInt(context.getContentResolver(), "location_mode");
            return (i == 1 || i == 3) ? false : true;
        } catch (Settings.SettingNotFoundException unused) {
            return true;
        }
    }

    public g e() {
        return this.c;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00bb  */
    /* JADX WARNING: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void g() {
        /*
            r6 = this;
            java.lang.String r0 = "android.permission.ACCESS_FINE_LOCATION"
            boolean r0 = r6.a((java.lang.String) r0)
            r1 = 4002(0xfa2, float:5.608E-42)
            r2 = 0
            r3 = 1
            if (r0 != 0) goto L_0x002f
            boolean r0 = r6.f
            if (r0 != 0) goto L_0x0016
            r6.f = r3
            r6.h()
            return
        L_0x0016:
            a.a.a.b r0 = new a.a.a.b
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r3 = "gps"
            r0.<init>(r3, r1, r2)
            r6.d = r0
            in.cashify.otex.ExchangeManager r0 = r6.f()
            long r1 = r6.d()
            r0.a((long) r1, (in.cashify.otex.widget.CircleRoadProgress.b) r6)
            return
        L_0x002f:
            android.content.Context r0 = r6.getContext()     // Catch:{ Exception -> 0x003d }
            java.lang.String r4 = "location"
            java.lang.Object r0 = r0.getSystemService(r4)     // Catch:{ Exception -> 0x003d }
            android.location.LocationManager r0 = (android.location.LocationManager) r0     // Catch:{ Exception -> 0x003d }
            r6.b = r0     // Catch:{ Exception -> 0x003d }
        L_0x003d:
            android.location.LocationManager r0 = r6.b
            if (r0 != 0) goto L_0x005c
            a.a.a.b r0 = new a.a.a.b
            r1 = 4004(0xfa4, float:5.611E-42)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r3 = "gps"
            r0.<init>(r3, r1, r2)
            r6.d = r0
            in.cashify.otex.ExchangeManager r0 = r6.f()
            long r1 = r6.d()
            r0.a((long) r1, (in.cashify.otex.widget.CircleRoadProgress.b) r6)
            return
        L_0x005c:
            in.cashify.otex.ExchangeManager r0 = r6.f()
            long r4 = r6.c()
            r0.a((long) r4, (in.cashify.otex.widget.CircleRoadProgress.b) r6)
            android.location.LocationManager r0 = r6.b
            java.lang.String r4 = "gps"
            boolean r0 = r0.isProviderEnabled(r4)
            if (r0 != 0) goto L_0x00aa
            android.support.v4.app.FragmentActivity r0 = r6.getActivity()
            boolean r0 = r6.a((android.content.Context) r0)
            if (r0 == 0) goto L_0x00aa
            a.a.a.e.c.h r0 = r6.c
            boolean r0 = r0.b()
            if (r0 == 0) goto L_0x009e
            boolean r0 = r6.e
            if (r0 != 0) goto L_0x009e
            boolean r0 = r6.isAdded()
            if (r0 == 0) goto L_0x00b7
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "android.settings.LOCATION_SOURCE_SETTINGS"
            r0.<init>(r1)
            r1 = 67108864(0x4000000, float:1.5046328E-36)
            r0.addFlags(r1)
            r1 = 3
            r6.startActivityForResult(r0, r1)
            goto L_0x00b7
        L_0x009e:
            a.a.a.b r0 = new a.a.a.b
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.String r4 = "gps"
            r0.<init>(r4, r1, r2)
            goto L_0x00b5
        L_0x00aa:
            a.a.a.b r0 = new a.a.a.b
            java.lang.Integer r1 = java.lang.Integer.valueOf(r3)
            java.lang.String r2 = "gps"
            r0.<init>(r2, r1, r3)
        L_0x00b5:
            r6.d = r0
        L_0x00b7:
            a.a.a.b r0 = r6.d
            if (r0 == 0) goto L_0x00d1
            in.cashify.otex.ExchangeManager r0 = r6.f()
            long r1 = r6.d()
            a.a.a.b r4 = r6.d
            boolean r4 = r4.c()
            r3 = r3 ^ r4
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            r0.a((long) r1, (java.lang.Boolean) r3)
        L_0x00d1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: a.a.a.e.b.d.g():void");
    }

    public final void h() {
        requestPermissions(new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 0);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.e = i == 3;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.nextButton) {
            Button button = this.g;
            if (button != null) {
                button.setEnabled(false);
            }
            this.d = new b("gps", 4001, false, true);
            f().a(d(), (Boolean) true);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.c = (h) getArguments().getParcelable("arg_gps_diagnose");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_gps_diagnose, viewGroup, false);
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
            bundle.putBoolean("key_enable_request", this.e);
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
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

    public void onViewStateRestored(Bundle bundle) {
        super.onViewStateRestored(bundle);
        if (bundle != null) {
            this.e = bundle.getBoolean("key_enable_request");
        }
    }
}
