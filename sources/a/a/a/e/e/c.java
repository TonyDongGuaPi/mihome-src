package a.a.a.e.e;

import a.a.a.e.c.g;
import a.a.a.e.c.l;
import a.a.a.h.a;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.coloros.mcssdk.PushManager;
import in.cashify.otex.R;
import in.cashify.otex.widget.CircleRoadProgress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.cybergarage.upnp.Device;

public class c extends a.a.a.e.a implements CircleRoadProgress.b {
    public final Handler b = new Handler(Looper.myLooper());
    public final List<Integer> c = new ArrayList();
    public l d;
    public TextToSpeech e;
    public int f = -1;
    public boolean g = true;
    public TextView h;
    public View i;
    public TextView j;
    public TextView k;
    public TextView l;
    public a.a.a.b m;
    public Button n;
    public AudioManager o;

    public class a implements Runnable {
        public a() {
        }

        public void run() {
            FragmentActivity activity = c.this.getActivity();
            if (c.this.isAdded() && activity != null) {
                TextToSpeech unused = c.this.e = new TextToSpeech(activity.getApplicationContext(), new b());
                c.this.e.setLanguage(Locale.getDefault());
            }
        }
    }

    public class b implements TextToSpeech.OnInitListener {

        public class a implements Runnable {
            public a() {
            }

            public void run() {
                a.a.a.b unused = c.this.m = new a.a.a.b("se", Integer.valueOf(Device.HTTP_DEFAULT_PORT), false);
                c.this.f().a(c.this.d(), (Boolean) true);
            }
        }

        /* renamed from: a.a.a.e.e.c$b$b  reason: collision with other inner class name */
        public class C0007b extends UtteranceProgressListener {

            /* renamed from: a.a.a.e.e.c$b$b$a */
            public class a implements Runnable {
                public a() {
                }

                public void run() {
                    if (c.this.h != null) {
                        c.this.h.setEnabled(true);
                    }
                }
            }

            public C0007b() {
            }

            public void onDone(String str) {
                c.this.b.post(new a());
            }

            public void onError(String str) {
            }

            public void onStart(String str) {
            }
        }

        /* renamed from: a.a.a.e.e.c$b$c  reason: collision with other inner class name */
        public class C0008c implements TextToSpeech.OnUtteranceCompletedListener {

            /* renamed from: a.a.a.e.e.c$b$c$a */
            public class a implements Runnable {
                public a() {
                }

                public void run() {
                    if (c.this.h != null) {
                        c.this.h.setEnabled(true);
                    }
                }
            }

            public C0008c() {
            }

            public void onUtteranceCompleted(String str) {
                c.this.b.post(new a());
            }
        }

        public class d implements Runnable {
            public d() {
            }

            public void run() {
                a.a.a.b unused = c.this.m = new a.a.a.b("se", Integer.valueOf(Device.HTTP_DEFAULT_PORT), false);
                c.this.f().a(c.this.d(), (Boolean) true);
            }
        }

        public b() {
        }

        public void onInit(int i) {
            if (c.this.isAdded()) {
                if (c.this.e == null) {
                    c.this.b.post(new a());
                    return;
                }
                int unused = c.this.f = i;
                if (i == 0) {
                    c.this.h.setEnabled(true);
                    if (Build.VERSION.SDK_INT >= 15) {
                        c.this.e.setOnUtteranceProgressListener(new C0007b());
                    } else {
                        c.this.e.setOnUtteranceCompletedListener(new C0008c());
                    }
                } else {
                    c.this.b.post(new d());
                }
            }
        }
    }

    /* renamed from: a.a.a.e.e.c$c  reason: collision with other inner class name */
    public class C0009c extends a.c {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ a.a.a.h.a f401a;

        public C0009c(a.a.a.h.a aVar) {
            this.f401a = aVar;
        }

        public boolean a() {
            this.f401a.dismissAllowingStateLoss();
            if (c.this.getContext() == null) {
                return false;
            }
            NotificationManager notificationManager = (NotificationManager) c.this.getContext().getSystemService(PushManager.MESSAGE_TYPE_NOTI);
            if (Build.VERSION.SDK_INT < 23 || notificationManager == null || notificationManager.isNotificationPolicyAccessGranted()) {
                return true;
            }
            c.this.startActivityForResult(new Intent("android.settings.NOTIFICATION_POLICY_ACCESS_SETTINGS"), 103);
            return true;
        }

        public void b() {
            if (c.this.n != null) {
                c.this.n.performClick();
                return;
            }
            c.this.f().a(c.this.d(), (Boolean) true);
            a.a.a.b unused = c.this.m = new a.a.a.b("se", 4001, false, true);
        }
    }

    public class d extends a.c {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ a.a.a.h.a f402a;

        public d(a.a.a.h.a aVar) {
            this.f402a = aVar;
        }

        public boolean a() {
            this.f402a.dismissAllowingStateLoss();
            return true;
        }

        public void b() {
            if (c.this.n != null) {
                c.this.n.performClick();
                return;
            }
            c.this.f().a(c.this.d(), (Boolean) true);
            a.a.a.b unused = c.this.m = new a.a.a.b("se", 4001, false, true);
        }
    }

    public static c a(l lVar) {
        c cVar = new c();
        Bundle bundle = new Bundle();
        bundle.putParcelable("arg_speaker_diagnose", lVar);
        cVar.setArguments(bundle);
        return cVar;
    }

    public void a() {
        a.a.a.b bVar = this.m;
        if (bVar == null) {
            a(new a.a.a.b("se", 4005, false));
        } else {
            a(bVar);
        }
    }

    public final void a(int i2) {
        ArrayList arrayList = new ArrayList();
        for (int i3 = 1; i3 < 9; i3++) {
            arrayList.add(Integer.valueOf(i3));
        }
        Collections.shuffle(arrayList);
        this.c.clear();
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            this.c.add(arrayList.get(i5));
        }
        while (true) {
            int i6 = i2 - 1;
            if (i4 < i6) {
                a(String.valueOf(arrayList.get(i4)), 1, (String) null);
                a((long) this.d.d(), 1, (String) null);
                i4++;
            } else {
                a(String.valueOf(arrayList.get(i6)), 1, "last_utterance_id");
                return;
            }
        }
    }

    public final void a(long j2, int i2, String str) {
        TextToSpeech textToSpeech = this.e;
        if (textToSpeech != null) {
            if (Build.VERSION.SDK_INT >= 21) {
                textToSpeech.playSilentUtterance(j2, i2, str);
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("utteranceId", str);
            this.e.playSilence(j2, i2, hashMap);
        }
    }

    public final void a(View view, int i2) {
        TextView textView = (TextView) view.findViewById(i2);
        textView.setBackgroundResource(R.drawable.prompt_boundary);
        textView.setTextColor(getResources().getColor(R.color.otex_text_color_black));
    }

    public final void a(TextView textView) {
        if (textView != null && !textView.isEnabled()) {
            textView.setEnabled(true);
            textView.setBackgroundResource(R.drawable.prompt_boundary);
            textView.setTextColor(a.a.a.d.a((Context) getActivity(), R.color.otexColorAccent));
        }
    }

    public final void a(String str, int i2, String str2) {
        TextToSpeech textToSpeech = this.e;
        if (textToSpeech != null) {
            if (Build.VERSION.SDK_INT >= 21) {
                textToSpeech.speak(str, i2, (Bundle) null, str2);
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("utteranceId", str2);
            this.e.speak(str, i2, hashMap);
        }
    }

    public boolean a(Context context) {
        try {
            for (String equalsIgnoreCase : context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions) {
                if (equalsIgnoreCase.equalsIgnoreCase("android.permission.ACCESS_NOTIFICATION_POLICY")) {
                    return true;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return false;
    }

    public final void b(View view, @IdRes int i2) {
        View findViewById = view.findViewById(i2);
        if (findViewById != null) {
            findViewById.setOnClickListener(this);
        }
    }

    public final void b(TextView textView) {
        if (textView != null && !this.c.isEmpty()) {
            textView.setBackgroundResource(R.drawable.orange_boundary);
            textView.setTextColor(getResources().getColor(R.color.otexColorOrangeLight));
            textView.setEnabled(false);
            if (textView.getText().toString().equalsIgnoreCase(String.valueOf(this.c.get(0)))) {
                this.c.remove(0);
            } else {
                TextToSpeech textToSpeech = this.e;
                if (textToSpeech != null) {
                    textToSpeech.stop();
                }
                this.m = new a.a.a.b("se", 0, false);
                f().a(d(), (Boolean) true);
                this.g = true;
            }
            if (this.c.isEmpty()) {
                this.m = new a.a.a.b("se", Integer.valueOf(this.d.t()), true);
                f().a(d(), (Boolean) false);
            }
        }
    }

    public g e() {
        return this.d;
    }

    public void g() {
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                k();
            } catch (Exception unused) {
                if (a(getContext())) {
                    m();
                } else {
                    n();
                }
            }
        } else {
            k();
        }
    }

    public final void h() {
        new Thread(new a()).start();
    }

    public final void i() {
        if (getView() != null) {
            a(getView(), R.id.button_speaker_count_1);
            a(getView(), R.id.button_speaker_count_2);
            a(getView(), R.id.button_speaker_count_3);
            a(getView(), R.id.button_speaker_count_4);
            a(getView(), R.id.button_speaker_count_5);
            a(getView(), R.id.button_speaker_count_6);
            a(getView(), R.id.button_speaker_count_7);
            a(getView(), R.id.button_speaker_count_8);
        }
    }

    public final void j() {
        if (this.f == 0) {
            f().a();
            View view = this.i;
            if (view != null && view.getVisibility() == 8) {
                this.i.setVisibility(0);
            }
            TextView textView = this.j;
            if (textView != null) {
                textView.setVisibility(8);
            }
            TextView textView2 = this.k;
            if (textView2 != null) {
                textView2.setVisibility(8);
            }
            TextView textView3 = this.l;
            if (textView3 != null) {
                textView3.setVisibility(0);
            }
            this.g = false;
            a(this.d.s());
            TextView textView4 = this.h;
            if (textView4 != null) {
                textView4.setText(R.string.otex_re_play);
                this.h.setEnabled(false);
            }
        }
    }

    public final void k() {
        AudioManager audioManager = this.o;
        if (audioManager != null) {
            this.o.setStreamVolume(3, audioManager.getStreamMaxVolume(3), 2);
            j();
        }
    }

    public final void l() {
        View view = getView();
        if (view != null) {
            a((TextView) view.findViewById(R.id.button_speaker_count_1));
            a((TextView) view.findViewById(R.id.button_speaker_count_2));
            a((TextView) view.findViewById(R.id.button_speaker_count_3));
            a((TextView) view.findViewById(R.id.button_speaker_count_4));
            a((TextView) view.findViewById(R.id.button_speaker_count_5));
            a((TextView) view.findViewById(R.id.button_speaker_count_6));
            a((TextView) view.findViewById(R.id.button_speaker_count_7));
            a((TextView) view.findViewById(R.id.button_speaker_count_8));
        }
    }

    public final void m() {
        if (isAdded()) {
            a.a.a.h.a a2 = a.a.a.h.a.a(getString(R.string.speaker_alert_title), this.d.c(), this.d.n(), this.d.m(), false);
            a2.a((a.c) new C0009c(a2));
            FragmentManager fragmentManager = getFragmentManager();
            if (fragmentManager != null) {
                FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.add((Fragment) a2, a2.getClass().getSimpleName());
                beginTransaction.commitAllowingStateLoss();
            }
        }
    }

    public final void n() {
        if (isAdded()) {
            a.a.a.h.a a2 = a.a.a.h.a.a(getString(R.string.speaker_alert_title), this.d.b(), this.d.n(), this.d.m(), false);
            a2.a((a.c) new d(a2));
            FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
            beginTransaction.add((Fragment) a2, a2.getClass().getSimpleName());
            beginTransaction.commitAllowingStateLoss();
        }
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        h();
    }

    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 103) {
            g();
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.nextButton) {
            TextToSpeech textToSpeech = this.e;
            if (textToSpeech != null) {
                textToSpeech.stop();
            }
            TextView textView = this.h;
            if (textView != null) {
                textView.setEnabled(false);
            }
            Button button = this.n;
            if (button != null) {
                button.setEnabled(false);
            }
            this.m = new a.a.a.b("se", 4001, false, true);
            f().a(d(), (Boolean) true);
        } else if (id == R.id.btn_play_sound) {
            l();
            i();
            g();
        } else if (!this.g) {
            if (id == R.id.button_speaker_count_1 || id == R.id.button_speaker_count_2 || id == R.id.button_speaker_count_3 || id == R.id.button_speaker_count_4 || id == R.id.button_speaker_count_5 || id == R.id.button_speaker_count_6 || id == R.id.button_speaker_count_7 || id == R.id.button_speaker_count_8) {
                b((TextView) view);
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.d = (l) getArguments().getParcelable("arg_speaker_diagnose");
            if (getContext() != null) {
                this.o = (AudioManager) getContext().getSystemService("audio");
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_speaker_diagnose, viewGroup, false);
    }

    public void onDestroy() {
        super.onDestroy();
        TextToSpeech textToSpeech = this.e;
        if (textToSpeech != null) {
            textToSpeech.shutdown();
            this.e = null;
        }
    }

    public void onPause() {
        TextToSpeech textToSpeech = this.e;
        if (textToSpeech != null) {
            textToSpeech.stop();
            TextView textView = this.h;
            if (textView != null) {
                textView.setEnabled(true);
            }
        }
        f().a();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        TextView textView = this.h;
        if (textView != null) {
            textView.setEnabled(true);
        }
        Button button = this.n;
        if (button != null) {
            button.setEnabled(true);
        }
        l();
        i();
        f().a(c(), (CircleRoadProgress.b) this, this.d.q());
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.i = view.findViewById(R.id.layout_vibration);
        this.h = (TextView) view.findViewById(R.id.btn_play_sound);
        TextView textView = this.h;
        int i2 = 0;
        if (textView != null) {
            textView.setVisibility(0);
            this.h.setOnClickListener(this);
            this.h.setEnabled(false);
            this.h.setText(R.string.otex_play);
        }
        b(view, R.id.button_speaker_count_1);
        b(view, R.id.button_speaker_count_2);
        b(view, R.id.button_speaker_count_3);
        b(view, R.id.button_speaker_count_4);
        b(view, R.id.button_speaker_count_5);
        b(view, R.id.button_speaker_count_6);
        b(view, R.id.button_speaker_count_7);
        b(view, R.id.button_speaker_count_8);
        this.j = (TextView) view.findViewById(R.id.diagnoseTitle);
        TextView textView2 = this.j;
        if (textView2 != null) {
            textView2.setText(e().l());
        }
        this.k = (TextView) view.findViewById(R.id.diagnoseMessage);
        TextView textView3 = this.k;
        if (textView3 != null) {
            textView3.setText(e().j());
        }
        this.l = (TextView) view.findViewById(R.id.diagnoseHelp);
        TextView textView4 = this.l;
        if (textView4 != null) {
            textView4.setText(e().k());
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
