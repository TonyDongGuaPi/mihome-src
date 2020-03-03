package a.a.a.e.d;

import a.a.a.b;
import a.a.a.e.a;
import a.a.a.e.c.g;
import a.a.a.e.c.n;
import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioAttributes;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import in.cashify.otex.R;
import in.cashify.otex.widget.CircleRoadProgress;
import java.util.Random;

public class d extends a implements View.OnClickListener, CircleRoadProgress.b {
    public n b;
    public View c;
    public Vibrator d;
    public boolean e;
    public long[] f;
    public TextView g;
    public TextView h;
    public b i;
    public TextView j;
    public TextView k;
    public TextView l;
    public TextView m;
    public Button n;
    public TextView o;

    public static d a(n nVar) {
        d dVar = new d();
        Bundle bundle = new Bundle();
        bundle.putParcelable("arg_vibration_diagnose", nVar);
        dVar.setArguments(bundle);
        return dVar;
    }

    public void a() {
        if (this.i == null) {
            this.i = new b("vib", 4005, false);
        }
        a(this.i);
    }

    public final void a(View.OnClickListener onClickListener) {
        this.j.setOnClickListener(onClickListener);
        this.k.setOnClickListener(onClickListener);
        this.l.setOnClickListener(onClickListener);
        this.m.setOnClickListener(onClickListener);
    }

    public final void a(TextView textView) {
        if (textView != null) {
            textView.setBackgroundResource(R.drawable.prompt_boundary);
            textView.setTextColor(getResources().getColor(R.color.otex_text_color_black));
        }
    }

    public final void a(boolean z) {
        f().a(d(), Boolean.valueOf(!z));
        this.i = new b("vib", Integer.valueOf(z ? 1 : 0), z);
    }

    public g e() {
        return this.b;
    }

    public final void g() {
        int nextInt = (new Random().nextInt(1000) % 3) + 1;
        this.f = new long[(nextInt * 2)];
        for (int i2 = 0; i2 < this.b.b(); i2++) {
            if (i2 < nextInt) {
                long[] jArr = this.f;
                int i3 = i2 * 2;
                jArr[i3] = 1000;
                jArr[i3 + 1] = 1000;
            }
        }
    }

    @SuppressLint({"MissingPermission"})
    public final void h() {
        if (a("android.permission.VIBRATE") && getActivity() != null) {
            this.d = (Vibrator) getActivity().getSystemService("vibrator");
            Vibrator vibrator = this.d;
            if (vibrator == null || !vibrator.hasVibrator()) {
                a(false);
            } else if (Build.VERSION.SDK_INT >= 21) {
                this.d.vibrate(this.f, -1, new AudioAttributes.Builder().setUsage(4).build());
            } else {
                this.d.vibrate(this.f, -1);
            }
        }
    }

    public final void i() {
        requestPermissions(new String[]{"android.permission.VIBRATE"}, 0);
    }

    public final void j() {
        TextView textView = this.o;
        if (textView != null) {
            textView.setText(R.string.otex_retry);
            this.o.setEnabled(false);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00bd, code lost:
        if ((r6.length / 2) == 0) goto L_0x00dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00c6, code lost:
        if ((r6.length / 2) == 1) goto L_0x00dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00cf, code lost:
        if ((r6.length / 2) == 2) goto L_0x00dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00d9, code lost:
        if ((r6.length / 2) == 3) goto L_0x00dd;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onClick(android.view.View r6) {
        /*
            r5 = this;
            int r0 = r6.getId()
            int r1 = in.cashify.otex.R.id.nextButton
            r2 = 1
            r3 = 0
            if (r0 != r1) goto L_0x0039
            android.widget.Button r6 = r5.n
            if (r6 == 0) goto L_0x0011
            r6.setEnabled(r3)
        L_0x0011:
            android.widget.TextView r6 = r5.o
            if (r6 == 0) goto L_0x0018
            r6.setEnabled(r3)
        L_0x0018:
            in.cashify.otex.ExchangeManager r6 = r5.f()
            long r0 = r5.d()
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r2)
            r6.a((long) r0, (java.lang.Boolean) r4)
            a.a.a.b r6 = new a.a.a.b
            r0 = 4001(0xfa1, float:5.607E-42)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.String r1 = "vib"
            r6.<init>(r1, r0, r3, r2)
            r5.i = r6
            goto L_0x00e4
        L_0x0039:
            int r1 = in.cashify.otex.R.id.diagnoseActionButton
            if (r0 != r1) goto L_0x0093
            in.cashify.otex.ExchangeManager r6 = r5.f()
            r6.a()
            android.widget.TextView r6 = r5.g
            r0 = 8
            if (r6 == 0) goto L_0x004d
            r6.setVisibility(r0)
        L_0x004d:
            android.widget.TextView r6 = r5.h
            if (r6 == 0) goto L_0x0054
            r6.setVisibility(r0)
        L_0x0054:
            android.view.View r6 = r5.c
            if (r6 == 0) goto L_0x0083
            int r1 = in.cashify.otex.R.id.layout_vibration
            android.view.View r6 = r6.findViewById(r1)
            int r1 = r6.getVisibility()
            if (r1 != r0) goto L_0x0083
            r6.setVisibility(r3)
            android.view.View r6 = r5.c
            int r0 = in.cashify.otex.R.id.diagnoseHelp
            android.view.View r6 = r6.findViewById(r0)
            android.widget.TextView r6 = (android.widget.TextView) r6
            a.a.a.e.c.g r0 = r5.e()
            java.lang.String r0 = r0.k()
            r6.setText(r0)
            android.widget.TextView r6 = r5.o
            int r0 = in.cashify.otex.R.string.otex_retry
            r6.setText(r0)
        L_0x0083:
            boolean r6 = r5.isAdded()
            if (r6 == 0) goto L_0x00e1
            r5.g()
            r5.a((android.view.View.OnClickListener) r5)
            r5.h()
            goto L_0x00e4
        L_0x0093:
            r1 = 0
            r5.a((android.view.View.OnClickListener) r1)
            r5.j()
            android.widget.TextView r6 = (android.widget.TextView) r6
            int r1 = in.cashify.otex.R.drawable.orange_boundary
            r6.setBackgroundResource(r1)
            android.content.res.Resources r1 = r5.getResources()
            int r4 = in.cashify.otex.R.color.otexColorOrangeLight
            int r1 = r1.getColor(r4)
            r6.setTextColor(r1)
            long[] r6 = r5.f
            if (r6 == 0) goto L_0x00e1
            int r1 = r6.length
            if (r1 != 0) goto L_0x00b6
            goto L_0x00e1
        L_0x00b6:
            int r1 = in.cashify.otex.R.id.button_vibration_count_0
            r4 = 2
            if (r0 != r1) goto L_0x00c0
            int r6 = r6.length
            int r6 = r6 / r4
            if (r6 != 0) goto L_0x00dc
            goto L_0x00dd
        L_0x00c0:
            int r1 = in.cashify.otex.R.id.button_vibration_count_1
            if (r0 != r1) goto L_0x00c9
            int r6 = r6.length
            int r6 = r6 / r4
            if (r6 != r2) goto L_0x00dc
            goto L_0x00dd
        L_0x00c9:
            int r1 = in.cashify.otex.R.id.button_vibration_count_2
            if (r0 != r1) goto L_0x00d2
            int r6 = r6.length
            int r6 = r6 / r4
            if (r6 != r4) goto L_0x00dc
            goto L_0x00dd
        L_0x00d2:
            int r1 = in.cashify.otex.R.id.button_vibration_count_3
            if (r0 != r1) goto L_0x00e4
            int r6 = r6.length
            int r6 = r6 / r4
            r0 = 3
            if (r6 != r0) goto L_0x00dc
            goto L_0x00dd
        L_0x00dc:
            r2 = 0
        L_0x00dd:
            r5.a((boolean) r2)
            goto L_0x00e4
        L_0x00e1:
            r5.a((boolean) r3)
        L_0x00e4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: a.a.a.e.d.d.onClick(android.view.View):void");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.b = (n) getArguments().getParcelable("arg_vibration_diagnose");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_vibration_diagnose, viewGroup, false);
        this.c = inflate;
        return inflate;
    }

    @SuppressLint({"MissingPermission"})
    public void onPause() {
        Vibrator vibrator = this.d;
        if (vibrator != null) {
            vibrator.cancel();
            this.d = null;
        }
        f().a();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        if (!a("android.permission.VIBRATE")) {
            if (this.e) {
                this.i = new b("wf", 4002, false);
                f().a(c(), (CircleRoadProgress.b) this, this.b.q());
                return;
            }
            i();
            this.e = true;
        } else if (getActivity() == null || a((Context) getActivity(), 3) || this.e) {
            TextView textView = this.o;
            if (textView != null) {
                textView.setEnabled(true);
            }
            a(this.j);
            a(this.k);
            a(this.l);
            a(this.m);
            f().a(c(), (CircleRoadProgress.b) this, this.b.q());
        } else {
            b();
            this.e = true;
        }
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.o = (TextView) view.findViewById(R.id.diagnoseActionButton);
        TextView textView = this.o;
        int i2 = 0;
        if (textView != null) {
            textView.setVisibility(0);
            this.o.setOnClickListener(this);
            this.o.setText(R.string.otex_start);
        }
        this.j = (TextView) view.findViewById(R.id.button_vibration_count_0);
        this.k = (TextView) view.findViewById(R.id.button_vibration_count_1);
        this.l = (TextView) view.findViewById(R.id.button_vibration_count_2);
        this.m = (TextView) view.findViewById(R.id.button_vibration_count_3);
        this.g = (TextView) view.findViewById(R.id.diagnoseTitle);
        TextView textView2 = this.g;
        if (textView2 != null) {
            textView2.setText(e().l());
        }
        this.h = (TextView) view.findViewById(R.id.diagnoseMessage);
        TextView textView3 = this.h;
        if (textView3 != null) {
            textView3.setText(e().j());
        }
        this.n = (Button) view.findViewById(R.id.nextButton);
        Button button = this.n;
        if (button != null) {
            if (!e().o()) {
                i2 = 8;
            }
            button.setVisibility(i2);
            this.n.setText(e().m());
            this.n.setOnClickListener(this);
        }
    }
}
