package in.cashify.otex.diagnose.manual;

import a.a.a.e.c.m;
import a.a.a.e.d.b;
import a.a.a.h.a;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import in.cashify.otex.R;
import in.cashify.otex.widget.CalibrationView;

public class TouchCalibrationActivity extends AppCompatActivity implements b.d, CalibrationView.c, CalibrationView.f {

    /* renamed from: a  reason: collision with root package name */
    public boolean f2603a;
    public m b;
    public final b c = new b(this);
    public CalibrationView d;
    public TextView e;

    public class a extends a.c {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ a.a.a.h.a f2604a;

        public a(a.a.a.h.a aVar) {
            this.f2604a = aVar;
        }

        public boolean a() {
            TouchCalibrationActivity.this.e();
            TouchCalibrationActivity.this.c.a();
            boolean unused = TouchCalibrationActivity.this.f2603a = false;
            return true;
        }

        public void b() {
            this.f2604a.dismissAllowingStateLoss();
            Intent intent = new Intent();
            intent.putExtra("result", TouchCalibrationActivity.this.d());
            TouchCalibrationActivity.this.setResult(-1, intent);
            TouchCalibrationActivity.this.finish();
            boolean unused = TouchCalibrationActivity.this.f2603a = false;
        }
    }

    public void a() {
        if (!this.f2603a) {
            this.f2603a = true;
            this.c.d();
            if (this.b != null) {
                a.a.a.h.a a2 = a.a.a.h.a.a(getResources().getString(R.string.otex_touch_alert_title), this.b.k(), this.b.n(), this.b.m(), false);
                a2.a((a.c) new a(a2));
                FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
                beginTransaction.add((Fragment) a2, a2.getClass().getSimpleName());
                beginTransaction.commitAllowingStateLoss();
            }
        }
    }

    public void a(boolean z) {
        if (z) {
            this.c.a();
        } else {
            this.c.d();
        }
    }

    public void b() {
        TextView textView = this.e;
        if (textView != null) {
            textView.setVisibility(8);
        }
    }

    public void c() {
        Intent intent = new Intent();
        intent.putExtra("result", 100);
        setResult(-1, intent);
        finish();
    }

    public int d() {
        CalibrationView calibrationView = this.d;
        if (calibrationView != null) {
            return calibrationView.getCompletionPercent();
        }
        return 0;
    }

    public void e() {
        CalibrationView calibrationView = this.d;
        if (calibrationView != null) {
            calibrationView.e();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_touch_calibration);
        this.b = (m) getIntent().getParcelableExtra("arg_context");
        this.c.a(this.b.b());
        this.c.a((TextView) findViewById(R.id.touchTimer));
        this.c.a((b.d) this);
        this.d = (CalibrationView) findViewById(R.id.touchCalibrationView);
        CalibrationView calibrationView = this.d;
        if (calibrationView != null) {
            calibrationView.setOnCalibrationDoneListener(this);
            this.e = (TextView) findViewById(R.id.test_touch_sub_title);
        }
    }

    public void onPause() {
        super.onPause();
        if (!this.f2603a) {
            this.c.b();
        }
    }

    public void onResume() {
        super.onResume();
        if (!this.f2603a) {
            this.c.c();
        }
    }
}
