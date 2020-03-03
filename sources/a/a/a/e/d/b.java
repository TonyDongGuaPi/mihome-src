package a.a.a.e.d;

import android.app.Activity;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

public class b {

    /* renamed from: a  reason: collision with root package name */
    public int f388a = 0;
    public Timer b;
    public int c = 0;
    public boolean d = false;
    public d e;
    public TextView f;
    public final Activity g;

    public class a extends TimerTask {
        public a() {
        }

        public void run() {
            b.a(b.this);
            if (b.this.c >= b.this.f388a) {
                if (b.this.b != null) {
                    b.this.b.cancel();
                }
                if (b.this.e != null) {
                    b.this.e.a();
                }
            }
            b.this.a(String.valueOf(b.this.f388a - b.this.c));
        }
    }

    /* renamed from: a.a.a.e.d.b$b  reason: collision with other inner class name */
    public class C0005b implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f390a;

        public C0005b(String str) {
            this.f390a = str;
        }

        public void run() {
            b.this.f.setText(this.f390a);
        }
    }

    public class c implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ int f391a;

        public c(int i) {
            this.f391a = i;
        }

        public void run() {
            if (b.this.f != null) {
                b.this.f.setText("");
                b.this.f.setVisibility(this.f391a);
            }
        }
    }

    public interface d {
        void a();
    }

    public b(Activity activity) {
        this.g = activity;
    }

    public static /* synthetic */ int a(b bVar) {
        int i = bVar.c;
        bVar.c = i + 1;
        return i;
    }

    public void a() {
        if (!this.d) {
            a(1000, 1000);
            b(0);
        }
    }

    public void a(int i) {
        this.f388a = i;
    }

    public final void a(long j, long j2) {
        this.b = new Timer();
        this.b.scheduleAtFixedRate(new a(), j, j2);
    }

    public void a(d dVar) {
        this.e = dVar;
    }

    public void a(TextView textView) {
        this.f = textView;
    }

    public final void a(String str) {
        if (this.f != null) {
            this.g.runOnUiThread(new C0005b(str));
        }
    }

    public void b() {
        this.d = true;
        e();
    }

    public final void b(int i) {
        Activity activity = this.g;
        if (activity != null) {
            activity.runOnUiThread(new c(i));
        }
    }

    public void c() {
        if (this.d) {
            a(1000, 1000);
            this.d = false;
        }
    }

    public void d() {
        this.d = false;
        this.c = 0;
        e();
        b(8);
    }

    public final void e() {
        Timer timer = this.b;
        if (timer != null) {
            timer.cancel();
            this.b = null;
        }
    }
}
