package a.a.a.h;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import in.cashify.otex.R;
import java.util.Timer;
import java.util.TimerTask;

public class b extends DialogFragment {

    /* renamed from: a  reason: collision with root package name */
    public long f413a;
    public View b;
    public Timer c;
    @ColorInt
    public int[] d;
    public int e;
    public C0015b f;

    public class a extends TimerTask {

        /* renamed from: a.a.a.h.b$a$a  reason: collision with other inner class name */
        public class C0014a implements Runnable {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ int f415a;

            public C0014a(int i) {
                this.f415a = i;
            }

            public void run() {
                b.this.b.setBackgroundColor(this.f415a);
            }
        }

        public a() {
        }

        public void run() {
            if (b.this.d == null || b.this.b == null) {
                cancel();
            } else if (b.this.e < b.this.d.length) {
                int i = b.this.d[b.d(b.this)];
                FragmentActivity activity = b.this.getActivity();
                if (activity != null) {
                    activity.runOnUiThread(new C0014a(i));
                }
            } else {
                if (b.this.f != null) {
                    b.this.f.g();
                }
                cancel();
                b.this.dismiss();
            }
        }
    }

    /* renamed from: a.a.a.h.b$b  reason: collision with other inner class name */
    public interface C0015b {
        void g();
    }

    public static b a(long j, @ColorInt int[] iArr) {
        b bVar = new b();
        Bundle bundle = new Bundle();
        bundle.putLong("blink_duration", j);
        bundle.putIntArray("blink_color_int_array", iArr);
        bVar.setArguments(bundle);
        bVar.setStyle(2, 16973934);
        return bVar;
    }

    public static /* synthetic */ int d(b bVar) {
        int i = bVar.e;
        bVar.e = i + 1;
        return i;
    }

    public void a(C0015b bVar) {
        this.f = bVar;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, 16973838);
        if (getArguments() != null) {
            this.f413a = getArguments().getLong("blink_duration", 500);
            this.d = getArguments().getIntArray("blink_color_int_array");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_blink_dialog, viewGroup, false);
    }

    public void onPause() {
        super.onPause();
        Timer timer = this.c;
        if (timer != null) {
            timer.cancel();
        }
    }

    public void onResume() {
        super.onResume();
        Timer timer = this.c;
        if (timer != null) {
            timer.cancel();
        }
        this.c = new Timer();
        this.c.scheduleAtFixedRate(new a(), 0, this.f413a);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.b = view.findViewById(R.id.blink_view);
    }
}
