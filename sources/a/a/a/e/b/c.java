package a.a.a.e.b;

import a.a.a.e.c.d;
import a.a.a.e.c.g;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import in.cashify.otex.ExchangeManager;
import in.cashify.otex.R;
import in.cashify.otex.widget.CircleRoadProgress;
import in.cashify.otex.widget.DiagnoseCameraHeaderView;
import org.cybergarage.upnp.Device;

public class c extends a.a.a.e.a implements CircleRoadProgress.b {
    public d b;
    public boolean c;
    public a.a.a.b d;
    public DiagnoseCameraHeaderView e;
    public Button f;
    public boolean g;

    public class a implements Runnable {
        public a() {
        }

        public void run() {
            View b = c.this.f().b();
            if (b instanceof DiagnoseCameraHeaderView) {
                c.this.a((DiagnoseCameraHeaderView) b);
            }
        }
    }

    public class b implements C0002c {

        public class a implements Runnable {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ a.a.a.b f374a;

            public a(a.a.a.b bVar) {
                this.f374a = bVar;
            }

            public void run() {
                a.a.a.b unused = c.this.d = this.f374a;
                c.this.f().a(c.this.d(), (Boolean) false);
            }
        }

        public b() {
        }

        public void a(a.a.a.b bVar) {
            FragmentActivity activity = c.this.getActivity();
            if (c.this.isAdded() && activity != null) {
                activity.runOnUiThread(new a(bVar));
            }
        }
    }

    /* renamed from: a.a.a.e.b.c$c  reason: collision with other inner class name */
    public interface C0002c {
        void a(a.a.a.b bVar);
    }

    public static c a(d dVar) {
        c cVar = new c();
        Bundle bundle = new Bundle();
        bundle.putParcelable("arg_camera_diagnose", dVar);
        cVar.setArguments(bundle);
        return cVar;
    }

    public void a() {
        if (this.d != null || this.g) {
            a(this.d);
            return;
        }
        this.g = true;
        this.d = new a.a.a.b(this.e.getRequestKey(), 4005, false);
        f().a(-1, (CircleRoadProgress.b) this);
        f().a(d(), Boolean.valueOf(true ^ this.d.c()));
        DiagnoseCameraHeaderView diagnoseCameraHeaderView = this.e;
        if (diagnoseCameraHeaderView != null) {
            diagnoseCameraHeaderView.h();
        }
    }

    public final void a(DiagnoseCameraHeaderView diagnoseCameraHeaderView) {
        a.a.a.b bVar;
        this.e = diagnoseCameraHeaderView;
        this.e.setmCameraContext(this.b);
        this.e.setPictureTakenCallBack(new b());
        this.e.g();
        if (!a("android.permission.CAMERA")) {
            if (this.c) {
                f().a(-1, (CircleRoadProgress.b) this);
                f().a(d(), (Boolean) true);
                this.d = new a.a.a.b(this.e.getRequestKey(), 4002, false);
                return;
            }
            g();
            this.c = true;
        } else if (getActivity() == null || a((Context) getActivity(), 26) || this.c) {
            if (!a((Context) getActivity())) {
                f().a(-1, (CircleRoadProgress.b) this);
                f().a(d(), (Boolean) true);
                bVar = new a.a.a.b(this.e.getRequestKey(), 4003, false);
            } else if (this.e.b == null) {
                f().a(-1, (CircleRoadProgress.b) this);
                f().a(d(), (Boolean) true);
                bVar = new a.a.a.b(this.e.getRequestKey(), Integer.valueOf(Device.HTTP_DEFAULT_PORT), false);
            } else {
                f().a(c(), (CircleRoadProgress.b) this);
                if (this.e.b.b()) {
                    this.e.f();
                    return;
                }
                DiagnoseCameraHeaderView diagnoseCameraHeaderView2 = this.e;
                diagnoseCameraHeaderView2.b.setPreviewStartListener(diagnoseCameraHeaderView2);
                return;
            }
            this.d = bVar;
        } else {
            b();
            this.c = true;
        }
    }

    public final boolean a(Context context) {
        PackageManager packageManager;
        String str;
        if (h() == 1) {
            packageManager = context.getPackageManager();
            str = "android.hardware.camera.front";
        } else {
            packageManager = context.getPackageManager();
            str = "android.hardware.camera";
        }
        return packageManager.hasSystemFeature(str);
    }

    public g e() {
        return this.b;
    }

    public final void g() {
        requestPermissions(new String[]{"android.permission.CAMERA"}, 0);
    }

    public final int h() {
        return this.b.f().equals(ExchangeManager.h.FRONT_CAMERA.a()) ? 1 : 0;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.nextButton) {
            Button button = this.f;
            if (button != null) {
                button.setEnabled(false);
            }
            this.d = new a.a.a.b(this.e.getRequestKey(), 4001, false, true);
            f().a(d(), (Boolean) true);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.b = (d) getArguments().getParcelable("arg_camera_diagnose");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_camera_diagnose, viewGroup, false);
    }

    public void onPause() {
        DiagnoseCameraHeaderView diagnoseCameraHeaderView = this.e;
        if (diagnoseCameraHeaderView != null) {
            diagnoseCameraHeaderView.c();
        }
        f().a();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        View b2 = f().b();
        if (!(b2 instanceof DiagnoseCameraHeaderView)) {
            f().c();
            new Handler(Looper.getMainLooper()).postDelayed(new a(), 500);
            return;
        }
        a((DiagnoseCameraHeaderView) b2);
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
