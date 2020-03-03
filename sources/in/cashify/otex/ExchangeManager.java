package in.cashify.otex;

import a.a.a.a;
import a.a.a.e.a;
import a.a.a.f.c.a;
import a.a.a.f.d.a;
import a.a.a.h.c.a;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.verificationsdk.internal.Constants;
import in.cashify.otex.ExchangeError;
import in.cashify.otex.widget.CircleRoadProgress;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.mp4parser.boxes.iso14496.part12.FreeSpaceBox;

public class ExchangeManager extends Fragment implements a.a.a.c, a.C0001a, ViewPager.OnPageChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public ExchangeSetup f2592a;
    public OnExchangeCallback b;
    public a.a.a.a c;
    public final ArrayList<a.a.a.e.c.g> d = new ArrayList<>();
    public final HashMap<String, a.a.a.b> e = new HashMap<>();
    public FrameLayout f;
    public a.a.a.h.c.a g;
    public int h;
    public ViewPager i;
    public boolean j = false;
    public ProgressBar k;
    public TextView l;

    public interface OnExchangeCallback {
        void a(int i, String str, String str2);

        void a(SetupError setupError);

        void a(String str);

        void a(String str, String str2);

        void b(String str, String str2);
    }

    public class a implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ ExchangeSetup f2593a;

        public a(ExchangeSetup exchangeSetup) {
            this.f2593a = exchangeSetup;
        }

        public void run() {
            SetupError setupError;
            OnExchangeCallback onExchangeCallback;
            try {
                if (ExchangeManager.this.c == null) {
                    a.a.a.a unused = ExchangeManager.this.c = new a.b().a(ExchangeManager.this.getActivity());
                }
                a.a.a.f.d.a a2 = new a.b(ExchangeManager.this.getActivity()).a(this.f2593a, ExchangeManager.this.c);
                if (ExchangeManager.this.b != null) {
                    if (String.valueOf(this.f2593a.b()).length() != 6) {
                        onExchangeCallback = ExchangeManager.this.b;
                        setupError = new SetupError(ExchangeError.Kind.INVALID_PIN_CODE);
                    } else if (a2 == null) {
                        onExchangeCallback = ExchangeManager.this.b;
                        setupError = new SetupError(ExchangeError.Kind.UNKNOWN_ERROR);
                    } else {
                        ExchangeManager.this.b.a(a2.a(), a2.b());
                        return;
                    }
                    onExchangeCallback.a(setupError);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public class b implements Runnable {
        public b() {
        }

        public void run() {
            try {
                ExchangeManager.this.i.setOffscreenPageLimit(ExchangeManager.this.d.size());
                ExchangeManager.this.a(ExchangeManager.this.g, (List<a.a.a.e.c.g>) ExchangeManager.this.d);
                ExchangeManager.this.b(ExchangeManager.this.h = 0);
            } catch (Throwable unused) {
                if (ExchangeManager.this.b != null) {
                    ExchangeManager.this.b.a(new SetupError(ExchangeError.Kind.UNKNOWN_ERROR));
                }
            }
        }
    }

    public class c implements Comparator<a.a.a.e.c.g> {
        /* renamed from: a */
        public int compare(a.a.a.e.c.g gVar, a.a.a.e.c.g gVar2) {
            return gVar.g() - gVar2.g();
        }
    }

    public class d implements Runnable {
        public d() {
        }

        public void run() {
            try {
                if (ExchangeManager.this.b != null) {
                    a.a.a.f.c.a a2 = new a.b().a(ExchangeManager.this.getActivity(), ExchangeManager.this.f2592a, ExchangeManager.this.c, ExchangeManager.this.e);
                    if (a2 != null) {
                        ExchangeManager.this.b.b(a2.a(), a2.b());
                    } else {
                        ExchangeManager.this.b.a(new SetupError(ExchangeError.Kind.UNKNOWN_ERROR));
                    }
                }
            } catch (Throwable unused) {
                if (ExchangeManager.this.b != null) {
                    ExchangeManager.this.b.a(new SetupError(ExchangeError.Kind.UNKNOWN_ERROR));
                }
            }
        }
    }

    public class e implements DialogInterface.OnClickListener {
        public e() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            ExchangeManager.this.g();
        }
    }

    public class f implements DialogInterface.OnClickListener {
        public f() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            ExchangeManager.this.d();
        }
    }

    public class g implements Runnable {
        public g() {
        }

        public void run() {
            if (ExchangeManager.this.g != null) {
                ExchangeManager.this.g.notifyDataSetChanged();
            }
        }
    }

    public enum h {
        WELCOME_PAGE("welcome_page"),
        QUOTE_PAGE("quote_page"),
        MOBILE_AGE("mobile_age"),
        WIFI("wifi"),
        BLUETOOTH(BleDevice.f14751a),
        MICROPHONE("mic"),
        FRONT_CAMERA("front_camera"),
        BACK_CAMERA("back_camera"),
        BATTERY(Constants.m),
        SPEAKER("speaker"),
        VOLUME("volume_button"),
        CHARGING(Constants.y),
        AUDIO_JACK("audio_jack"),
        MANUAL_WEB_PAGE("manual_web_page"),
        PROXIMITY_SENSOR("proximity_sensor"),
        TELEPHONY_TEST("telephony_test"),
        VIBRATION("vibration"),
        MANUAL_SINGLE_CHOICE("manual_single_choice"),
        MANUAL_MULTI_CHOICE("manual_multi_choice"),
        MANUAL_DROPDOWN("manual_dropdown"),
        GPS("gps"),
        PROMPT_PAGE("prompt_page"),
        ZERO_QUOTE_PAGE("zero_quote_page"),
        TOUCH_CALIBRATION("touch_calibration"),
        HOME_BUTTON("home_button"),
        RECENT_TASK_BUTTON("recent_task_button"),
        BACK_BUTTON("back_button"),
        DEAD_PIXEL("manual_dead_pixel"),
        SENSOR_ACCELEROMETER("sensor_accelerometer"),
        SENSOR_COMPASS("sensor_compass"),
        SENSOR_GYROSCOPE("sensor_gyroscope"),
        BUTTON_BACK("button_back"),
        BUTTON_HOME("home_button"),
        BUTTON_RECENT_APPS("button_recent_apps"),
        SIM_CARD("sim_card"),
        SPEAKER_RECEIVER("speaker_receiver"),
        POWER_BUTTON("power_button"),
        REMARK(DeviceTagInterface.f),
        FINGERPRINT("fingerprint"),
        NONE("none");
        

        /* renamed from: a  reason: collision with root package name */
        public final String f2599a;

        /* access modifiers changed from: public */
        h(String str) {
            this.f2599a = str;
        }

        public static h a(String str) {
            for (h hVar : values()) {
                if (hVar.a().equalsIgnoreCase(str)) {
                    return hVar;
                }
            }
            return NONE;
        }

        public String a() {
            return this.f2599a;
        }
    }

    public static ExchangeManager a(ExchangeSetup exchangeSetup) {
        ExchangeManager exchangeManager = new ExchangeManager();
        Bundle bundle = new Bundle();
        bundle.putParcelable("arg_exchange_info", exchangeSetup);
        exchangeManager.setArguments(bundle);
        return exchangeManager;
    }

    public static String a(int i2, long j2) {
        return "cashify:diagnose:" + i2 + ":" + j2;
    }

    public static void a(List<a.a.a.e.c.g> list) {
        Collections.sort(list, new c());
    }

    public void a() {
        a.C0016a a2 = this.g.a(this.h);
        if (a2 != null) {
            a2.d(4);
            c();
        }
    }

    public void a(int i2) {
        a.C0016a a2 = this.g.a(this.h);
        if (a2 != null) {
            a2.a(i2);
            c();
        }
    }

    public final void a(int i2, int i3) {
        this.l.setText(MessageFormat.format("{0}/{1}", new Object[]{Integer.valueOf(i2), Integer.valueOf(i3)}));
    }

    public void a(long j2, CircleRoadProgress.b bVar) {
        a.C0016a a2 = this.g.a(this.h);
        if (a2 != null) {
            a2.b(3);
            a2.d(1);
            a2.a(j2);
            a2.a(bVar);
            c();
        }
    }

    public void a(long j2, CircleRoadProgress.b bVar, int i2) {
        a.C0016a a2 = this.g.a(this.h);
        if (a2 != null) {
            a2.a(i2);
            a2.b(3);
            a2.d(1);
            a2.a(j2);
            a2.a(bVar);
            c();
        }
    }

    public void a(long j2, Boolean bool) {
        Context context;
        int i2;
        a.C0016a a2 = this.g.a(this.h);
        if (a2 != null) {
            a2.a(j2);
            if (bool.booleanValue()) {
                a2.a(R.drawable.ic_cross);
                if (getContext() != null) {
                    context = getContext();
                    i2 = R.color.arc_fail_color;
                }
                a2.d(2);
                c();
            }
            a2.a(R.drawable.ic_tick);
            if (getContext() != null) {
                context = getContext();
                i2 = R.color.arc_pass_color;
            }
            a2.d(2);
            c();
            a2.c(ContextCompat.getColor(context, i2));
            a2.d(2);
            c();
        }
    }

    public final void a(a.a.a.e.c.g gVar, boolean z, boolean z2) {
        a.C0016a a2;
        a.a.a.h.c.a aVar = this.g;
        if (aVar != null && (a2 = aVar.a(this.h)) != null) {
            int i2 = z ? 1 : 2;
            a2.b(i2);
            gVar.b(i2);
            if (this.b != null) {
                this.b.a(this.h, gVar.f(), z2 ? FreeSpaceBox.f3849a : z ? "pass" : "fail");
            }
        }
    }

    public final void a(a.a.a.h.c.a aVar, List<a.a.a.e.c.g> list) {
        if (list == null || list.isEmpty()) {
            Log.d("", "Diagnose list may not be null");
            return;
        }
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            a.a.a.e.c.g gVar = list.get(i2);
            int p = gVar.p();
            if (i2 == this.h) {
                p = 3;
            }
            a.C0016a aVar2 = new a.C0016a(p, gVar.q(), gVar.r(), gVar.l());
            aVar2.a(gVar.f());
            arrayList.add(aVar2);
        }
        aVar.a((List<a.C0016a>) arrayList);
        this.k.setMax(this.d.size());
        a(0, this.d.size());
    }

    public final void a(Bundle bundle) {
        ArrayList parcelableArrayList = bundle.getParcelableArrayList("bundle_result_map");
        ArrayList parcelableArrayList2 = bundle.getParcelableArrayList("bundle_device_registration");
        this.c = (a.a.a.a) bundle.getParcelable("bundle_device_info");
        if (parcelableArrayList2 != null && !parcelableArrayList2.isEmpty()) {
            this.d.clear();
            this.d.addAll(parcelableArrayList2);
            this.h = bundle.getInt("bundle_current_test_index");
            if (this.h >= parcelableArrayList2.size()) {
                this.h = 0;
            }
        }
        if (parcelableArrayList != null && !parcelableArrayList.isEmpty()) {
            this.e.clear();
            Iterator it = parcelableArrayList.iterator();
            while (it.hasNext()) {
                a.a.a.b bVar = (a.a.a.b) it.next();
                this.e.put(bVar.a(), bVar);
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:16|17|(2:19|30)(1:28)) */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r0 = new org.json.JSONObject(r3);
        r1 = new in.cashify.otex.SetupError(r0.getString("mssg"), r0.getInt("error"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0060, code lost:
        if (r2.b != null) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0062, code lost:
        r2.b.a(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0068, code lost:
        r3 = r2.b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x006a, code lost:
        if (r3 != null) goto L_0x006c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006c, code lost:
        r3.a(new in.cashify.otex.SetupError(in.cashify.otex.ExchangeError.Kind.INVALID_RESPONSE));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0048 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r3) {
        /*
            r2 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 == 0) goto L_0x0013
            in.cashify.otex.ExchangeManager$OnExchangeCallback r3 = r2.b
            in.cashify.otex.SetupError r0 = new in.cashify.otex.SetupError
            in.cashify.otex.ExchangeError$Kind r1 = in.cashify.otex.ExchangeError.Kind.INVALID_RESPONSE
            r0.<init>(r1)
            r3.a((in.cashify.otex.SetupError) r0)
            return
        L_0x0013:
            a.a.a.f.d.b r0 = new a.a.a.f.d.b     // Catch:{ InvalidObjectException | JSONException -> 0x0048 }
            r0.<init>((java.lang.String) r3)     // Catch:{ InvalidObjectException | JSONException -> 0x0048 }
            java.util.ArrayList<a.a.a.e.c.g> r1 = r2.d     // Catch:{ InvalidObjectException | JSONException -> 0x0048 }
            r1.clear()     // Catch:{ InvalidObjectException | JSONException -> 0x0048 }
            java.util.List r0 = r0.a()     // Catch:{ InvalidObjectException | JSONException -> 0x0048 }
            if (r0 == 0) goto L_0x002e
            boolean r1 = r0.isEmpty()     // Catch:{ InvalidObjectException | JSONException -> 0x0048 }
            if (r1 != 0) goto L_0x002e
            java.util.ArrayList<a.a.a.e.c.g> r1 = r2.d     // Catch:{ InvalidObjectException | JSONException -> 0x0048 }
            r1.addAll(r0)     // Catch:{ InvalidObjectException | JSONException -> 0x0048 }
        L_0x002e:
            java.util.ArrayList<a.a.a.e.c.g> r0 = r2.d     // Catch:{ InvalidObjectException | JSONException -> 0x0048 }
            a((java.util.List<a.a.a.e.c.g>) r0)     // Catch:{ InvalidObjectException | JSONException -> 0x0048 }
            android.support.v4.app.FragmentActivity r3 = r2.getActivity()
            if (r3 == 0) goto L_0x0047
            boolean r0 = r2.isAdded()
            if (r0 == 0) goto L_0x0047
            in.cashify.otex.ExchangeManager$b r0 = new in.cashify.otex.ExchangeManager$b
            r0.<init>()
            r3.runOnUiThread(r0)
        L_0x0047:
            return
        L_0x0048:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0068 }
            r0.<init>(r3)     // Catch:{ JSONException -> 0x0068 }
            java.lang.String r3 = "mssg"
            java.lang.String r3 = r0.getString(r3)     // Catch:{ JSONException -> 0x0068 }
            java.lang.String r1 = "error"
            int r0 = r0.getInt(r1)     // Catch:{ JSONException -> 0x0068 }
            in.cashify.otex.SetupError r1 = new in.cashify.otex.SetupError     // Catch:{ JSONException -> 0x0068 }
            r1.<init>(r3, r0)     // Catch:{ JSONException -> 0x0068 }
            in.cashify.otex.ExchangeManager$OnExchangeCallback r3 = r2.b     // Catch:{ JSONException -> 0x0068 }
            if (r3 == 0) goto L_0x0076
            in.cashify.otex.ExchangeManager$OnExchangeCallback r3 = r2.b     // Catch:{ JSONException -> 0x0068 }
            r3.a((in.cashify.otex.SetupError) r1)     // Catch:{ JSONException -> 0x0068 }
            goto L_0x0076
        L_0x0068:
            in.cashify.otex.ExchangeManager$OnExchangeCallback r3 = r2.b
            if (r3 == 0) goto L_0x0076
            in.cashify.otex.SetupError r0 = new in.cashify.otex.SetupError
            in.cashify.otex.ExchangeError$Kind r1 = in.cashify.otex.ExchangeError.Kind.INVALID_RESPONSE
            r0.<init>(r1)
            r3.a((in.cashify.otex.SetupError) r0)
        L_0x0076:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: in.cashify.otex.ExchangeManager.a(java.lang.String):void");
    }

    public void a(String str, ArrayList<a.a.a.b> arrayList) {
        boolean z;
        String f2;
        if (this.h < this.d.size()) {
            boolean z2 = false;
            if (arrayList != null) {
                Iterator<a.a.a.b> it = arrayList.iterator();
                z = false;
                while (it.hasNext()) {
                    a.a.a.b next = it.next();
                    if (next != null) {
                        this.e.put(next.a(), next);
                        z2 = next.c();
                        z = next.d();
                    }
                }
            } else {
                z = false;
            }
            a.a.a.e.c.g gVar = this.d.get(this.h);
            if (gVar != null && (f2 = gVar.f()) != null && f2.equals(str)) {
                a(gVar, z2, z);
                e();
            }
        }
    }

    public View b() {
        return this.g.a();
    }

    public final void b(int i2) {
        if (i2 >= 0 && i2 < this.d.size() && isAdded()) {
            this.k.incrementProgressBy(1);
            a(i2 + 1, this.d.size());
            a.a.a.e.c.g gVar = this.d.get(i2);
            OnExchangeCallback onExchangeCallback = this.b;
            if (onExchangeCallback != null) {
                onExchangeCallback.a(gVar.l());
            }
            FragmentManager childFragmentManager = getChildFragmentManager();
            String a2 = a(this.f.getId(), (long) i2);
            Fragment findFragmentByTag = childFragmentManager.findFragmentByTag(a2);
            if (findFragmentByTag == null) {
                findFragmentByTag = gVar.a();
            }
            FragmentTransaction beginTransaction = childFragmentManager.beginTransaction();
            beginTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
            beginTransaction.replace(this.f.getId(), findFragmentByTag, a2);
            beginTransaction.addToBackStack((String) null);
            beginTransaction.commitAllowingStateLoss();
        }
    }

    public final void b(ExchangeSetup exchangeSetup) {
        new Thread(new a(exchangeSetup)).start();
    }

    public void c() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            a.a.a.h.c.a aVar = this.g;
            if (aVar != null) {
                aVar.notifyDataSetChanged();
                return;
            }
            return;
        }
        new Handler().post(new g());
    }

    public final void d() {
        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(new String[]{"android.permission.READ_PHONE_STATE"}, 28);
        }
    }

    public final synchronized void e() {
        int i2 = this.h + 1;
        this.h = i2;
        if (i2 < this.d.size()) {
            a.a.a.h.c.a aVar = this.g;
            if (aVar != null) {
                a.C0016a a2 = aVar.a(i2);
                if (a2 != null) {
                    a2.b(3);
                }
                this.i.setCurrentItem(i2, true);
                c();
            }
            b(i2);
        } else {
            f();
        }
    }

    public final void f() {
        new Thread(new d()).start();
    }

    public final void g() {
        OnExchangeCallback onExchangeCallback = this.b;
        if (onExchangeCallback != null) {
            onExchangeCallback.a(new SetupError(ExchangeError.Kind.PERMISSION_NOT_GRANTED));
        }
    }

    public final void h() {
        new AlertDialog.Builder(getActivity()).setMessage(R.string.otex_permission_message).setPositiveButton(getString(17039370), new f()).setNegativeButton(getString(17039360), new e()).show();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (getParentFragment() instanceof OnExchangeCallback) {
            this.b = (OnExchangeCallback) getParentFragment();
            return;
        }
        throw new RuntimeException(context.toString() + " must implement ExchangeManager.OnExchangeCallback");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.f2592a = (ExchangeSetup) getArguments().getParcelable("arg_exchange_info");
        }
        if (bundle != null) {
            a(bundle);
        } else if (getActivity() == null || ContextCompat.checkSelfPermission(getActivity(), "android.permission.READ_PHONE_STATE") != 0) {
            d();
        } else {
            b(this.f2592a);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.cloneInContext(new ContextThemeWrapper(getActivity(), R.style.DiagnoseTheme_Light)).inflate(R.layout.fragment_exchange_manager, viewGroup, false);
    }

    public void onDetach() {
        super.onDetach();
        this.b = null;
    }

    public void onPageScrollStateChanged(int i2) {
        this.j = i2 != 0;
    }

    public void onPageScrolled(int i2, float f2, int i3) {
        if (this.j) {
            this.i.invalidate();
        }
    }

    public void onPageSelected(int i2) {
    }

    public void onRequestPermissionsResult(int i2, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (i2 == 28) {
            for (int i3 = 0; i3 < strArr.length; i3++) {
                String str = strArr[i3];
                if ("android.permission.READ_PHONE_STATE".equals(str)) {
                    if (iArr[i3] == -1) {
                        if (!(Build.VERSION.SDK_INT >= 23 ? shouldShowRequestPermissionRationale(str) : false)) {
                            g();
                        } else {
                            h();
                        }
                    } else {
                        b(this.f2592a);
                    }
                }
            }
        }
        super.onRequestPermissionsResult(i2, strArr, iArr);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.e.values());
        bundle.putParcelableArrayList("bundle_result_map", arrayList);
        bundle.putParcelableArrayList("bundle_device_registration", this.d);
        bundle.putInt("bundle_current_test_index", this.h);
        bundle.putParcelable("bundle_device_info", this.c);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        this.f = (FrameLayout) view.findViewById(R.id.diagnose_container);
        View findViewById = view.findViewById(R.id.pager_container);
        if (Build.VERSION.SDK_INT < 17) {
            findViewById.setLayerType(1, (Paint) null);
        }
        this.k = (ProgressBar) view.findViewById(R.id.progress_bar_test_status);
        if (getContext() != null) {
            this.k.getProgressDrawable().setColorFilter(ContextCompat.getColor(getContext(), R.color.otexColorPrimary), PorterDuff.Mode.SRC_IN);
        }
        this.l = (TextView) view.findViewById(R.id.tv_progress_counter);
        this.i = (ViewPager) view.findViewById(R.id.pager_header);
        this.g = new a.a.a.h.c.a();
        this.i.setAdapter(this.g);
        this.i.setPageTransformer(true, new a.a.a.h.d.a());
        this.i.addOnPageChangeListener(this);
        if (this.d.size() > 0) {
            this.i.setOffscreenPageLimit(this.d.size());
            a(this.g, (List<a.a.a.e.c.g>) this.d);
            b(this.h);
        }
    }
}
