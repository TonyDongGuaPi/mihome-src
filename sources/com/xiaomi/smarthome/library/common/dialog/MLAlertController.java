package com.xiaomi.smarthome.library.common.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import com.xiaomi.smarthome.library.R;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import java.lang.ref.WeakReference;

public class MLAlertController {
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 4;
    private CharSequence A;
    private int B;
    /* access modifiers changed from: private */
    public Message C;
    private ScrollView D;
    private int E;
    private Drawable F;
    private ImageView G;
    private TextView H;
    private TextView I;
    private View J;
    private boolean K;
    /* access modifiers changed from: private */
    public ListAdapter L;
    /* access modifiers changed from: private */
    public int M;
    private int N;
    /* access modifiers changed from: private */
    public int O;
    /* access modifiers changed from: private */
    public int P;
    /* access modifiers changed from: private */
    public int Q;
    /* access modifiers changed from: private */
    public int R;
    /* access modifiers changed from: private */
    public int S;
    /* access modifiers changed from: private */
    public int T;
    /* access modifiers changed from: private */
    public Handler U;
    /* access modifiers changed from: private */
    public boolean V;
    /* access modifiers changed from: private */
    public boolean W;
    private boolean X;

    /* renamed from: a  reason: collision with root package name */
    View.OnClickListener f18569a;
    private final Context e;
    /* access modifiers changed from: private */
    public final DialogInterface f;
    private final Window g;
    private CharSequence h;
    private CharSequence i;
    private SpannableStringBuilder j;
    /* access modifiers changed from: private */
    public ListView k;
    /* access modifiers changed from: private */
    public View l;
    private int m;
    private int n;
    private int o;
    private int p;
    private boolean q;
    /* access modifiers changed from: private */
    public Button r;
    private CharSequence s;
    private int t;
    /* access modifiers changed from: private */
    public Message u;
    /* access modifiers changed from: private */
    public Button v;
    private CharSequence w;
    private int x;
    /* access modifiers changed from: private */
    public Message y;
    /* access modifiers changed from: private */
    public Button z;

    private static boolean c(int i2) {
        return i2 == 1 || i2 == 2 || i2 == 4;
    }

    private static final class ButtonHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private static final int f18578a = 1;
        private WeakReference<DialogInterface> b;

        public ButtonHandler(DialogInterface dialogInterface) {
            this.b = new WeakReference<>(dialogInterface);
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 1) {
                switch (i) {
                    case -3:
                    case -2:
                    case -1:
                        ((DialogInterface.OnClickListener) message.obj).onClick((DialogInterface) this.b.get(), message.what);
                        return;
                    default:
                        return;
                }
            } else {
                ((DialogInterface) message.obj).dismiss();
            }
        }
    }

    public void a() {
        this.U.obtainMessage(1, this.f).sendToTarget();
    }

    public MLAlertController(Context context, DialogInterface dialogInterface, Window window) {
        this(context, dialogInterface, window, 80);
    }

    public MLAlertController(Context context, DialogInterface dialogInterface, Window window, int i2) {
        this.q = false;
        this.t = -1;
        this.x = -1;
        this.B = -1;
        this.E = -1;
        this.M = -1;
        this.V = false;
        this.W = true;
        this.X = false;
        this.f18569a = new View.OnClickListener() {
            public void onClick(View view) {
                Message message;
                if (view == MLAlertController.this.r && MLAlertController.this.u != null) {
                    message = Message.obtain(MLAlertController.this.u);
                } else if (view != MLAlertController.this.v || MLAlertController.this.y == null) {
                    message = (view != MLAlertController.this.z || MLAlertController.this.C == null) ? null : Message.obtain(MLAlertController.this.C);
                } else {
                    message = Message.obtain(MLAlertController.this.y);
                }
                if (message != null) {
                    message.sendToTarget();
                }
                if (MLAlertController.this.W) {
                    MLAlertController.this.U.obtainMessage(1, MLAlertController.this.f).sendToTarget();
                }
            }
        };
        this.e = context;
        this.f = dialogInterface;
        this.g = window;
        this.U = new ButtonHandler(dialogInterface);
        this.N = R.layout.ml_alert_dialog;
        this.O = R.layout.ml_select_dialog;
        this.P = R.layout.ml_select_dialog_center;
        this.Q = R.layout.ml_select_dialog_multichoice;
        this.R = R.layout.ml_select_dialog_singlechoice;
        this.S = R.layout.ml_select_dialog_item;
        this.T = i2;
    }

    static boolean a(View view) {
        if (view.onCheckIsTextEditor()) {
            return true;
        }
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        while (childCount > 0) {
            childCount--;
            if (a(viewGroup.getChildAt(childCount))) {
                return true;
            }
        }
        return false;
    }

    public void b() {
        this.g.requestFeature(1);
        this.g.setGravity(this.T);
        if (this.l == null || !a(this.l)) {
            this.g.setFlags(131072, 131072);
        }
        this.g.setContentView(this.N);
        e();
    }

    public void a(CharSequence charSequence) {
        this.h = charSequence;
        if (this.H != null) {
            this.H.setText(charSequence);
        }
    }

    public void b(View view) {
        this.J = view;
    }

    public void a(boolean z2) {
        this.W = z2;
    }

    public void b(CharSequence charSequence) {
        this.i = charSequence;
        if (this.I != null) {
            this.I.setText(charSequence);
        }
    }

    public void a(SpannableStringBuilder spannableStringBuilder) {
        this.j = spannableStringBuilder;
        if (this.I != null) {
            this.I.setText(this.j);
            this.I.setHighlightColor(0);
            this.I.setText(spannableStringBuilder);
            this.I.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    public void c(View view) {
        this.l = view;
        this.q = false;
    }

    public void b(boolean z2) {
        this.X = z2;
    }

    public void a(View view, int i2, int i3, int i4, int i5) {
        this.l = view;
        this.q = true;
        this.m = i2;
        this.n = i3;
        this.o = i4;
        this.p = i5;
    }

    public void a(int i2, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Message message) {
        if (message == null && onClickListener != null) {
            message = this.U.obtainMessage(i2, onClickListener);
        }
        switch (i2) {
            case -3:
                this.A = charSequence;
                this.C = message;
                return;
            case -2:
                this.w = charSequence;
                this.y = message;
                return;
            case -1:
                this.s = charSequence;
                this.u = message;
                return;
            default:
                throw new IllegalArgumentException("Button does not exist");
        }
    }

    public void a(int i2) {
        this.E = i2;
        if (this.G == null) {
            return;
        }
        if (i2 > 0) {
            this.G.setImageResource(this.E);
        } else if (i2 == 0) {
            this.G.setVisibility(8);
        }
    }

    public void a(Drawable drawable) {
        this.F = drawable;
        if (this.G != null && this.F != null) {
            this.G.setImageDrawable(drawable);
        }
    }

    public void c(boolean z2) {
        this.K = z2;
    }

    public ListView c() {
        return this.k;
    }

    public View d() {
        return this.l;
    }

    public Button b(int i2) {
        switch (i2) {
            case -3:
                return this.z;
            case -2:
                return this.v;
            case -1:
                return this.r;
            default:
                return null;
        }
    }

    public void a(int i2, int i3) {
        switch (i2) {
            case -3:
                this.B = i3;
                return;
            case -2:
                this.x = i3;
                return;
            case -1:
                this.t = i3;
                return;
            default:
                throw new IllegalArgumentException("Button does not exist");
        }
    }

    public boolean a(int i2, KeyEvent keyEvent) {
        if (i2 == 82 && this.k != null && this.k.getVisibility() == 0) {
            this.f.dismiss();
        }
        return this.D != null && this.D.executeKeyEvent(keyEvent);
    }

    public boolean b(int i2, KeyEvent keyEvent) {
        return this.D != null && this.D.executeKeyEvent(keyEvent);
    }

    private void e() {
        LinearLayout linearLayout = (LinearLayout) this.g.findViewById(R.id.contentPanel);
        b(linearLayout);
        boolean f2 = f();
        LinearLayout linearLayout2 = (LinearLayout) this.g.findViewById(R.id.topPanel);
        boolean a2 = a(linearLayout2);
        View findViewById = this.g.findViewById(R.id.buttonPanel);
        if (!f2) {
            findViewById.setVisibility(8);
        }
        FrameLayout frameLayout = (FrameLayout) this.g.findViewById(R.id.customPanel);
        if (this.l != null) {
            FrameLayout frameLayout2 = (FrameLayout) this.g.findViewById(R.id.custom);
            frameLayout2.addView(this.l);
            if (this.q) {
                frameLayout2.setPadding(this.m, this.n, this.o, this.p);
                if (this.X) {
                    this.V = true;
                }
            }
            if (this.k != null) {
                ((LinearLayout.LayoutParams) frameLayout.getLayoutParams()).weight = 0.0f;
            }
        } else {
            frameLayout.setVisibility(8);
        }
        if (this.V) {
            this.g.findViewById(R.id.parentPanel).setBackgroundColor(this.e.getResources().getColor(17170445));
        }
        if (this.k != null) {
            this.g.findViewById(R.id.title_divider_line).setVisibility(0);
        } else {
            this.g.findViewById(R.id.title_divider_line).setVisibility(8);
            this.g.findViewById(R.id.title_divider_line_bottom).setVisibility(8);
        }
        if (linearLayout2.getVisibility() == 8 && linearLayout.getVisibility() == 8 && frameLayout.getVisibility() == 8 && f2) {
            findViewById.setPadding(findViewById.getPaddingLeft(), findViewById.getPaddingBottom(), findViewById.getPaddingRight(), findViewById.getPaddingBottom());
        }
        a(linearLayout2, linearLayout, frameLayout, f2, a2, findViewById);
        if (TextUtils.isEmpty(this.h) && TextUtils.isEmpty(this.i) && this.j == null) {
            this.g.findViewById(R.id.empty_view).setVisibility(8);
        }
        if (!TextUtils.isEmpty(this.h) && TextUtils.isEmpty(this.i) && this.j == null && frameLayout.getVisibility() == 8 && f2) {
            ((LinearLayout.LayoutParams) findViewById.getLayoutParams()).topMargin = 0;
        }
    }

    private boolean a(LinearLayout linearLayout) {
        if (this.J != null) {
            linearLayout.addView(this.J, 0, new LinearLayout.LayoutParams(-1, -2));
            this.g.findViewById(R.id.title_template).setVisibility(8);
        } else {
            boolean z2 = !TextUtils.isEmpty(this.h);
            this.G = (ImageView) this.g.findViewById(R.id.icon);
            if (z2) {
                this.H = (TextView) this.g.findViewById(R.id.alertTitle);
                this.H.setText(this.h);
                if (this.E > 0) {
                    this.G.setImageResource(this.E);
                } else if (this.F != null) {
                    this.G.setImageDrawable(this.F);
                } else if (this.E == 0) {
                    this.H.setPadding(this.G.getPaddingLeft(), this.G.getPaddingTop(), this.G.getPaddingRight(), this.G.getPaddingBottom());
                    this.G.setVisibility(8);
                }
            } else {
                this.g.findViewById(R.id.title_template).setVisibility(8);
                this.G.setVisibility(8);
                linearLayout.setVisibility(8);
                return false;
            }
        }
        return true;
    }

    private void b(LinearLayout linearLayout) {
        this.D = (ScrollView) this.g.findViewById(R.id.scrollView);
        this.D.setFocusable(false);
        this.I = (TextView) this.g.findViewById(R.id.message);
        if (this.I != null) {
            if (this.i != null) {
                this.I.setText(this.i);
            } else if (this.j != null) {
                this.I.setText(this.j);
                this.I.setHighlightColor(0);
                this.I.setMovementMethod(LinkMovementMethod.getInstance());
            } else {
                this.I.setVisibility(8);
                this.D.removeView(this.I);
                if (this.k != null) {
                    linearLayout.removeView(this.g.findViewById(R.id.scrollView));
                    linearLayout.addView(this.k, new LinearLayout.LayoutParams(-1, -1));
                    linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, 0, 1.0f));
                    return;
                }
                linearLayout.setVisibility(8);
            }
        }
    }

    private boolean f() {
        int i2;
        this.r = (Button) this.g.findViewById(R.id.button1);
        this.r.setOnClickListener(this.f18569a);
        if (TextUtils.isEmpty(this.s)) {
            this.r.setVisibility(8);
            i2 = 0;
        } else {
            this.r.setText(this.s);
            this.r.setVisibility(0);
            if (this.t != -1) {
                this.r.setTextColor(this.t);
            }
            i2 = 1;
        }
        this.v = (Button) this.g.findViewById(R.id.button2);
        this.v.setOnClickListener(this.f18569a);
        if (TextUtils.isEmpty(this.w)) {
            this.v.setVisibility(8);
        } else {
            this.v.setText(this.w);
            this.v.setVisibility(0);
            i2 |= 2;
            if (this.x != -1) {
                this.v.setTextColor(this.x);
            }
        }
        this.z = (Button) this.g.findViewById(R.id.button3);
        this.z.setOnClickListener(this.f18569a);
        if (TextUtils.isEmpty(this.A)) {
            this.z.setVisibility(8);
        } else {
            this.z.setText(this.A);
            this.z.setVisibility(0);
            i2 |= 4;
            if (this.B != -1) {
                this.z.setTextColor(this.B);
            }
        }
        if (c(i2)) {
            if (i2 == 1) {
                a((TextView) this.r);
            } else if (i2 == 2) {
                a((TextView) this.v);
            } else if (i2 == 4) {
                a((TextView) this.z);
            }
        }
        if (i2 != 0) {
            return true;
        }
        return false;
    }

    private void a(TextView textView) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
        layoutParams.gravity = 1;
        layoutParams.weight = 0.5f;
        textView.setLayoutParams(layoutParams);
        textView.setBackgroundResource(R.drawable.dialog_btn_selector);
    }

    private void a(LinearLayout linearLayout, LinearLayout linearLayout2, View view, boolean z2, boolean z3, View view2) {
        int i2;
        if (this.V) {
            int color = this.e.getResources().getColor(17170445);
            int color2 = this.e.getResources().getColor(17170445);
            int color3 = this.e.getResources().getColor(17170445);
            int color4 = this.e.getResources().getColor(17170445);
            int color5 = this.e.getResources().getColor(17170445);
            int color6 = this.e.getResources().getColor(17170445);
            int color7 = this.e.getResources().getColor(17170445);
            int color8 = this.e.getResources().getColor(17170445);
            int color9 = this.e.getResources().getColor(17170445);
            View[] viewArr = new View[4];
            boolean[] zArr = new boolean[4];
            if (z3) {
                viewArr[0] = linearLayout;
                zArr[0] = false;
                i2 = 1;
            } else {
                i2 = 0;
            }
            viewArr[i2] = linearLayout2.getVisibility() == 8 ? null : linearLayout2;
            zArr[i2] = this.k != null;
            int i3 = i2 + 1;
            if (view != null) {
                viewArr[i3] = view;
                zArr[i3] = this.K;
                i3++;
            }
            if (z2) {
                viewArr[i3] = view2;
                zArr[i3] = true;
            }
            int i4 = color;
            View view3 = null;
            boolean z4 = false;
            boolean z5 = false;
            for (int i5 = 0; i5 < viewArr.length; i5++) {
                View view4 = viewArr[i5];
                if (view4 != null) {
                    if (view3 != null) {
                        if (!z5) {
                            view3.setBackgroundResource(z4 ? color6 : color2);
                        } else {
                            view3.setBackgroundResource(z4 ? color7 : color3);
                        }
                        z5 = true;
                    }
                    z4 = zArr[i5];
                    view3 = view4;
                }
            }
            if (view3 != null) {
                if (z5) {
                    if (!z4) {
                        color8 = color4;
                    } else if (z2) {
                        color8 = color9;
                    }
                    view3.setBackgroundResource(color8);
                } else {
                    if (!z4) {
                        color5 = i4;
                    }
                    view3.setBackgroundResource(color5);
                }
            }
        }
        if (this.k != null && this.L != null) {
            this.k.setAdapter(this.L);
            if (this.M > -1) {
                this.k.setItemChecked(this.M, true);
                this.k.setSelection(this.M);
            }
        }
    }

    public static class RecycleListView extends ListView {
        boolean mRecycleOnMeasure = true;

        public RecycleListView(Context context) {
            super(context);
        }

        public RecycleListView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public RecycleListView(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
        }

        /* access modifiers changed from: protected */
        public boolean recycleOnMeasure() {
            return this.mRecycleOnMeasure;
        }
    }

    public static class AlertParams {
        public boolean[] A;
        public boolean B;
        public boolean C;
        public int D = -1;
        public DialogInterface.OnMultiChoiceClickListener E;
        public Cursor F;
        public String G;
        public String H;
        public boolean I;
        public AdapterView.OnItemSelectedListener J;
        public OnPrepareListViewListener K;
        public boolean L = true;
        public boolean M = true;
        public MLAlertDialog.DismissCallBack N;
        public CharSequence O;
        public boolean P = false;
        public int Q = -1;
        public int R = -1;
        public int S = -1;

        /* renamed from: a  reason: collision with root package name */
        public final Context f18571a;
        public final LayoutInflater b;
        public int c = 0;
        public Drawable d;
        public CharSequence e;
        public View f;
        public CharSequence g;
        public SpannableStringBuilder h;
        public CharSequence i;
        public DialogInterface.OnClickListener j;
        public CharSequence k;
        public DialogInterface.OnClickListener l;
        public CharSequence m;
        public DialogInterface.OnClickListener n;
        public boolean o;
        public DialogInterface.OnCancelListener p;
        public DialogInterface.OnKeyListener q;
        public CharSequence[] r;
        public ListAdapter s;
        public DialogInterface.OnClickListener t;
        public View u;
        public int v;
        public int w;
        public int x;
        public int y;
        public boolean z = false;

        public interface OnPrepareListViewListener {
            void a(ListView listView);
        }

        public AlertParams(Context context) {
            this.f18571a = context;
            this.o = true;
            this.b = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        public void a(MLAlertController mLAlertController) {
            if (this.f != null) {
                mLAlertController.b(this.f);
            } else {
                if (this.e != null) {
                    mLAlertController.a(this.e);
                }
                if (this.d != null) {
                    mLAlertController.a(this.d);
                }
                if (this.c >= 0) {
                    mLAlertController.a(this.c);
                }
            }
            if (this.g != null) {
                mLAlertController.b(this.g);
            }
            if (this.h != null) {
                mLAlertController.a(this.h);
            }
            if (this.i != null) {
                mLAlertController.a(-1, this.i, this.j, (Message) null);
            }
            if (this.k != null) {
                mLAlertController.a(-2, this.k, this.l, (Message) null);
            }
            if (this.m != null) {
                mLAlertController.a(-3, this.m, this.n, (Message) null);
            }
            if (this.I) {
                mLAlertController.c(true);
            }
            boolean unused = mLAlertController.V = false;
            if (!(this.r == null && this.F == null && this.s == null)) {
                if (mLAlertController.T == 17) {
                    b(mLAlertController);
                } else {
                    c(mLAlertController);
                }
            }
            if (this.u != null) {
                if (this.z) {
                    mLAlertController.a(this.u, this.v, this.w, this.x, this.y);
                } else {
                    mLAlertController.c(this.u);
                }
            }
            mLAlertController.a(this.M);
            mLAlertController.b(this.P);
            if (this.Q != -1) {
                mLAlertController.a(-1, this.Q);
            }
            if (this.R != -1) {
                mLAlertController.a(-2, this.Q);
            }
            if (this.S != -1) {
                mLAlertController.a(-3, this.Q);
            }
        }

        private void b(final MLAlertController mLAlertController) {
            ListAdapter listAdapter;
            LinearLayout linearLayout = (LinearLayout) this.b.inflate(mLAlertController.P, (ViewGroup) null);
            final RecycleListView recycleListView = (RecycleListView) linearLayout.findViewById(R.id.select_dialog_listview);
            int i2 = R.layout.ml_center_item;
            if (this.F == null) {
                listAdapter = this.s != null ? this.s : new ArrayAdapter(this.f18571a, i2, R.id.text1, this.r);
            } else {
                listAdapter = new SimpleCursorAdapter(this.f18571a, i2, this.F, new String[]{this.G}, new int[]{R.id.text1});
            }
            if (this.O != null) {
                ((TextView) linearLayout.findViewById(R.id.title)).setText(this.O);
            }
            if (this.K != null) {
                this.K.a(recycleListView);
            }
            ListAdapter unused = mLAlertController.L = listAdapter;
            recycleListView.setAdapter(listAdapter);
            int unused2 = mLAlertController.M = this.D;
            if (this.t != null) {
                recycleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
                        AlertParams.this.t.onClick(mLAlertController.f, i);
                        if (!AlertParams.this.C) {
                            mLAlertController.f.dismiss();
                        }
                    }
                });
            } else if (this.E != null) {
                recycleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
                        if (AlertParams.this.A != null) {
                            AlertParams.this.A[i] = recycleListView.isItemChecked(i);
                        }
                        AlertParams.this.E.onClick(mLAlertController.f, i, recycleListView.isItemChecked(i));
                    }
                });
            }
            if (this.J != null) {
                recycleListView.setOnItemSelectedListener(this.J);
            }
            if (this.J != null) {
                recycleListView.setOnItemSelectedListener(this.J);
            }
            if (this.C) {
                recycleListView.setChoiceMode(1);
            } else if (this.B) {
                recycleListView.setChoiceMode(2);
            }
            recycleListView.mRecycleOnMeasure = this.L;
            View unused3 = mLAlertController.l = linearLayout;
            boolean unused4 = mLAlertController.V = true;
            mLAlertController.b(this.P);
        }

        /* JADX WARNING: type inference failed for: r9v0, types: [android.widget.ListAdapter] */
        /* JADX WARNING: type inference failed for: r9v1 */
        /* JADX WARNING: type inference failed for: r1v22, types: [android.widget.ArrayAdapter] */
        /* JADX WARNING: type inference failed for: r1v23, types: [android.widget.ListAdapter] */
        /* JADX WARNING: type inference failed for: r1v30, types: [com.xiaomi.smarthome.library.common.dialog.MLAlertController$AlertParams$4] */
        /* JADX WARNING: type inference failed for: r1v31, types: [com.xiaomi.smarthome.library.common.dialog.MLAlertController$AlertParams$3] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void c(final com.xiaomi.smarthome.library.common.dialog.MLAlertController r11) {
            /*
                r10 = this;
                android.view.LayoutInflater r0 = r10.b
                int r1 = r11.O
                r2 = 0
                android.view.View r0 = r0.inflate(r1, r2)
                com.xiaomi.smarthome.library.common.dialog.MLAlertController$RecycleListView r0 = (com.xiaomi.smarthome.library.common.dialog.MLAlertController.RecycleListView) r0
                boolean r1 = r10.B
                r8 = 1
                if (r1 == 0) goto L_0x0038
                android.database.Cursor r1 = r10.F
                if (r1 != 0) goto L_0x0029
                com.xiaomi.smarthome.library.common.dialog.MLAlertController$AlertParams$3 r9 = new com.xiaomi.smarthome.library.common.dialog.MLAlertController$AlertParams$3
                android.content.Context r3 = r10.f18571a
                int r4 = r11.Q
                int r5 = com.xiaomi.smarthome.library.R.id.text1
                java.lang.CharSequence[] r6 = r10.r
                r1 = r9
                r2 = r10
                r7 = r0
                r1.<init>(r3, r4, r5, r6, r7)
                goto L_0x0077
            L_0x0029:
                com.xiaomi.smarthome.library.common.dialog.MLAlertController$AlertParams$4 r9 = new com.xiaomi.smarthome.library.common.dialog.MLAlertController$AlertParams$4
                android.content.Context r3 = r10.f18571a
                android.database.Cursor r4 = r10.F
                r5 = 0
                r1 = r9
                r2 = r10
                r6 = r0
                r7 = r11
                r1.<init>(r3, r4, r5, r6, r7)
                goto L_0x0077
            L_0x0038:
                boolean r1 = r10.C
                if (r1 == 0) goto L_0x0042
                int r1 = r11.R
            L_0x0040:
                r4 = r1
                goto L_0x0047
            L_0x0042:
                int r1 = r11.S
                goto L_0x0040
            L_0x0047:
                android.database.Cursor r1 = r10.F
                if (r1 != 0) goto L_0x005e
                android.widget.ListAdapter r1 = r10.s
                if (r1 == 0) goto L_0x0052
                android.widget.ListAdapter r1 = r10.s
                goto L_0x0076
            L_0x0052:
                android.widget.ArrayAdapter r1 = new android.widget.ArrayAdapter
                android.content.Context r2 = r10.f18571a
                int r3 = com.xiaomi.smarthome.library.R.id.text1
                java.lang.CharSequence[] r5 = r10.r
                r1.<init>(r2, r4, r3, r5)
                goto L_0x0076
            L_0x005e:
                android.widget.SimpleCursorAdapter r1 = new android.widget.SimpleCursorAdapter
                android.content.Context r3 = r10.f18571a
                android.database.Cursor r5 = r10.F
                java.lang.String[] r6 = new java.lang.String[r8]
                java.lang.String r2 = r10.G
                r7 = 0
                r6[r7] = r2
                int[] r9 = new int[r8]
                int r2 = com.xiaomi.smarthome.library.R.id.text1
                r9[r7] = r2
                r2 = r1
                r7 = r9
                r2.<init>(r3, r4, r5, r6, r7)
            L_0x0076:
                r9 = r1
            L_0x0077:
                com.xiaomi.smarthome.library.common.dialog.MLAlertController$AlertParams$OnPrepareListViewListener r1 = r10.K
                if (r1 == 0) goto L_0x0080
                com.xiaomi.smarthome.library.common.dialog.MLAlertController$AlertParams$OnPrepareListViewListener r1 = r10.K
                r1.a(r0)
            L_0x0080:
                android.widget.ListAdapter unused = r11.L = r9
                int r1 = r10.D
                int unused = r11.M = r1
                android.content.DialogInterface$OnClickListener r1 = r10.t
                if (r1 == 0) goto L_0x0095
                com.xiaomi.smarthome.library.common.dialog.MLAlertController$AlertParams$5 r1 = new com.xiaomi.smarthome.library.common.dialog.MLAlertController$AlertParams$5
                r1.<init>(r11)
                r0.setOnItemClickListener(r1)
                goto L_0x00a1
            L_0x0095:
                android.content.DialogInterface$OnMultiChoiceClickListener r1 = r10.E
                if (r1 == 0) goto L_0x00a1
                com.xiaomi.smarthome.library.common.dialog.MLAlertController$AlertParams$6 r1 = new com.xiaomi.smarthome.library.common.dialog.MLAlertController$AlertParams$6
                r1.<init>(r0, r11)
                r0.setOnItemClickListener(r1)
            L_0x00a1:
                android.widget.AdapterView$OnItemSelectedListener r1 = r10.J
                if (r1 == 0) goto L_0x00aa
                android.widget.AdapterView$OnItemSelectedListener r1 = r10.J
                r0.setOnItemSelectedListener(r1)
            L_0x00aa:
                boolean r1 = r10.C
                if (r1 == 0) goto L_0x00b2
                r0.setChoiceMode(r8)
                goto L_0x00ba
            L_0x00b2:
                boolean r1 = r10.B
                if (r1 == 0) goto L_0x00ba
                r1 = 2
                r0.setChoiceMode(r1)
            L_0x00ba:
                boolean r1 = r10.L
                r0.mRecycleOnMeasure = r1
                android.widget.ListView unused = r11.k = r0
                boolean r0 = r10.P
                r11.b((boolean) r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.dialog.MLAlertController.AlertParams.c(com.xiaomi.smarthome.library.common.dialog.MLAlertController):void");
        }
    }
}
