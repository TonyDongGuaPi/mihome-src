package a.a.a.e.e;

import a.a.a.e.a;
import a.a.a.e.c.g;
import a.a.a.e.c.k;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import in.cashify.otex.R;
import in.cashify.otex.widget.CircleRoadProgress;

public class b extends a implements SensorEventListener, CircleRoadProgress.b {
    public k b;
    public SensorManager c;
    public Sensor d;
    public boolean e;
    public a.a.a.b f;
    public Button g;
    public TextView h;
    public boolean i;

    public static b a(k kVar) {
        b bVar = new b();
        Bundle bundle = new Bundle();
        bundle.putParcelable("arg_proximity_diagnose", kVar);
        bVar.setArguments(bundle);
        return bVar;
    }

    public void a() {
        if (this.f != null || this.i) {
            a(this.f);
            return;
        }
        this.i = true;
        this.f = new a.a.a.b("ps", 4005, false);
        f().a(-1, (CircleRoadProgress.b) this);
        f().a(d(), Boolean.valueOf(true ^ this.f.c()));
    }

    public final void a(boolean z) {
        TextView textView = this.h;
        if (textView != null) {
            textView.setEnabled(z);
        }
        Button button = this.g;
        if (button != null) {
            button.setEnabled(z);
        }
    }

    public g e() {
        return this.b;
    }

    public final void g() {
        try {
            if (this.c != null) {
                this.c.unregisterListener(this);
            }
        } catch (Throwable unused) {
        }
    }

    public final void h() {
        try {
            this.c = (SensorManager) getActivity().getSystemService("sensor");
            if (this.c != null) {
                this.d = this.c.getDefaultSensor(8);
            }
        } catch (Exception unused) {
            a(new a.a.a.b("ps", 0, false));
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i2) {
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        h();
    }

    public void onClick(View view) {
        Sensor sensor;
        if (view.getId() == R.id.nextButton) {
            a(false);
            this.f = new a.a.a.b("ps", 0, false, true);
            f().a(d(), (Boolean) true);
        } else if (view.getId() == R.id.diagnoseActionButton) {
            SensorManager sensorManager = this.c;
            if (!(sensorManager == null || (sensor = this.d) == null)) {
                sensorManager.registerListener(this, sensor, 2);
            }
            f().a(c(), (CircleRoadProgress.b) this);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.b = (k) getArguments().getParcelable("arg_proximity_diagnose");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_diagnose_base, viewGroup, false);
    }

    public void onPause() {
        f().a();
        g();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        a(true);
        f().a(0, (CircleRoadProgress.b) this, this.b.q());
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent != null && sensorEvent.values != null && sensorEvent.sensor.getType() == 8) {
            float[] fArr = sensorEvent.values;
            int length = fArr.length;
            int i2 = 0;
            while (i2 < length) {
                float f2 = fArr[i2];
                Sensor sensor = this.d;
                if (sensor == null || f2 != sensor.getMaximumRange()) {
                    i2++;
                } else {
                    this.e = true;
                    return;
                }
            }
            if (this.e) {
                this.f = new a.a.a.b("ps", 1, true);
                f().a(d(), (Boolean) false);
            }
            this.e = false;
        }
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.h = (TextView) view.findViewById(R.id.diagnoseActionButton);
        int i2 = 0;
        this.h.setVisibility(0);
        this.h.setOnClickListener(this);
        this.h.setText(R.string.otex_start);
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
            if (!e().o()) {
                i2 = 8;
            }
            button.setVisibility(i2);
            this.g.setText(e().m());
            this.g.setOnClickListener(this);
        }
    }
}
