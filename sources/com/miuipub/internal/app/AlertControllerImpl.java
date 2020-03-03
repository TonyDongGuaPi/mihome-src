package com.miuipub.internal.app;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.miuipub.internal.variable.AlertControllerWrapper;
import com.miuipub.internal.view.menu.MenuBuilder;
import com.miuipub.internal.view.menu.MenuPresenter;
import com.miuipub.internal.widget.ActionBarContainer;
import com.miuipub.internal.widget.ActionBarContextView;
import com.miuipub.internal.widget.ActionBarView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import miuipub.app.ActionBar;
import miuipub.os.Build;
import miuipub.v6.R;
import miuipub.widget.ProgressBar;

public class AlertControllerImpl implements MenuBuilder.Callback {
    private CharSequence A;
    /* access modifiers changed from: private */
    public Message B;
    /* access modifiers changed from: private */
    public Handler C;
    private ScrollView D;
    /* access modifiers changed from: private */
    public DialogInterface E;
    private ListAdapter F;
    private Context G;
    private ActionBar H;
    private ActionBarView I;
    private View.OnClickListener J = new View.OnClickListener() {
        public void onClick(View view) {
            Message message;
            if (view == AlertControllerImpl.this.t && AlertControllerImpl.this.v != null) {
                message = Message.obtain(AlertControllerImpl.this.v);
            } else if (view != AlertControllerImpl.this.w || AlertControllerImpl.this.y == null) {
                message = (view != AlertControllerImpl.this.z || AlertControllerImpl.this.B == null) ? null : Message.obtain(AlertControllerImpl.this.B);
            } else {
                message = Message.obtain(AlertControllerImpl.this.y);
            }
            if (message != null) {
                message.sendToTarget();
            }
            AlertControllerImpl.this.C.obtainMessage(1, AlertControllerImpl.this.E).sendToTarget();
        }
    };
    private int K = -1;
    private boolean[] L;
    private MenuBuilder M;
    private final Runnable N = new Runnable() {
        public void run() {
            MenuBuilder b = AlertControllerImpl.this.b();
            if (!AlertControllerImpl.this.b(b) || !AlertControllerImpl.this.d(b)) {
                AlertControllerImpl.this.a((MenuBuilder) null);
            } else {
                AlertControllerImpl.this.a(b);
            }
        }
    };
    private MenuPresenter.Callback O = new MenuPresenter.Callback() {
        public void b(MenuBuilder menuBuilder, boolean z) {
        }

        public boolean b(MenuBuilder menuBuilder) {
            return false;
        }
    };
    private Window.Callback P = new Window.Callback() {
        public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
            return false;
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return false;
        }

        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            return false;
        }

        public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            return false;
        }

        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            return false;
        }

        public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
            return false;
        }

        public void onActionModeFinished(ActionMode actionMode) {
        }

        public void onActionModeStarted(ActionMode actionMode) {
        }

        public void onAttachedToWindow() {
        }

        public void onContentChanged() {
        }

        public boolean onCreatePanelMenu(int i, Menu menu) {
            return false;
        }

        public View onCreatePanelView(int i) {
            return null;
        }

        public void onDetachedFromWindow() {
        }

        public boolean onMenuItemSelected(int i, MenuItem menuItem) {
            return false;
        }

        public boolean onMenuOpened(int i, Menu menu) {
            return false;
        }

        public void onPanelClosed(int i, Menu menu) {
        }

        public boolean onPreparePanel(int i, View view, Menu menu) {
            return false;
        }

        public boolean onSearchRequested() {
            return false;
        }

        public boolean onSearchRequested(SearchEvent searchEvent) {
            return false;
        }

        public void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        }

        public void onWindowFocusChanged(boolean z) {
        }

        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            return null;
        }

        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
            return null;
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final int f8230a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private final Window f;
    private ViewGroup g;
    private CharSequence h;
    private CharSequence i;
    private ListView j;
    private TextView k;
    private TextView l;
    private View m;
    private View n;
    private boolean o;
    private CharSequence p;
    private ArrayList<AlertControllerWrapper.AlertParams.ActionItem> q;
    private DialogInterface.OnClickListener r;
    private Button s;
    /* access modifiers changed from: private */
    public Button t;
    private CharSequence u;
    /* access modifiers changed from: private */
    public Message v;
    /* access modifiers changed from: private */
    public Button w;
    private CharSequence x;
    /* access modifiers changed from: private */
    public Message y;
    /* access modifiers changed from: private */
    public Button z;

    /* access modifiers changed from: private */
    public boolean d(MenuBuilder menuBuilder) {
        return true;
    }

    public void c(MenuBuilder menuBuilder) {
    }

    private static final class ButtonHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private static final int f8237a = 1;
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

    public AlertControllerImpl(Context context, DialogInterface dialogInterface, Window window) {
        this.G = context;
        this.E = dialogInterface;
        this.f = window;
        this.C = new ButtonHandler(dialogInterface);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes((AttributeSet) null, R.styleable.V6_AlertDialog, 16842845, 0);
        this.e = obtainStyledAttributes.getResourceId(R.styleable.V6_AlertDialog_v6_layout, R.layout.v6_alert_dialog);
        this.f8230a = obtainStyledAttributes.getResourceId(R.styleable.V6_AlertDialog_v6_listLayout, R.layout.v6_select_dialog);
        this.b = obtainStyledAttributes.getResourceId(R.styleable.V6_AlertDialog_v6_multiChoiceItemLayout, R.layout.v6_select_dialog_multichoice);
        this.c = obtainStyledAttributes.getResourceId(R.styleable.V6_AlertDialog_v6_singleChoiceItemLayout, R.layout.v6_select_dialog_singlechoice);
        this.d = obtainStyledAttributes.getResourceId(R.styleable.V6_AlertDialog_v6_listItemLayout, R.layout.v6_select_dialog_item);
        obtainStyledAttributes.recycle();
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

    public void a() {
        this.f.requestFeature(1);
        if (this.n == null || !a(this.n)) {
            this.f.setFlags(131072, 131072);
        }
        m();
        this.g = (ViewGroup) this.f.findViewById(R.id.parentPanel);
        n();
    }

    private void m() {
        if (this.q != null) {
            View inflate = View.inflate(this.G, R.layout.v6_screen_action_bar, (ViewGroup) null);
            this.I = (ActionBarView) inflate.findViewById(R.id.action_bar);
            this.I.setWindowCallback(this.P);
            ActionBarContainer actionBarContainer = (ActionBarContainer) inflate.findViewById(R.id.split_action_bar);
            if (actionBarContainer != null) {
                this.I.setSplitView(actionBarContainer);
                this.I.setSplitActionBar(true);
                this.I.setSplitWhenNarrow(true);
                ActionBarContextView actionBarContextView = (ActionBarContextView) inflate.findViewById(R.id.action_context_bar);
                actionBarContextView.setSplitView(actionBarContainer);
                actionBarContextView.setSplitActionBar(true);
                actionBarContextView.setSplitWhenNarrow(true);
            }
            View.inflate(this.G, this.e, (ViewGroup) inflate.findViewById(16908290));
            this.f.setContentView(inflate);
            this.f.getDecorView().post(this.N);
            this.H = new ActionBarImpl((Dialog) this.E);
            this.H.setDisplayOptions(0);
            this.I.setCollapsable(true);
            return;
        }
        this.f.setContentView(this.e);
        if (!Build.W) {
            this.f.setGravity(80);
            this.f.setLayout(-1, -2);
        }
    }

    /* access modifiers changed from: private */
    public void a(MenuBuilder menuBuilder) {
        if (menuBuilder != this.M) {
            this.M = menuBuilder;
            if (this.I != null) {
                this.I.setMenu(menuBuilder, this.O);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public MenuBuilder b() {
        MenuBuilder menuBuilder = new MenuBuilder(this.G);
        menuBuilder.a((MenuBuilder.Callback) this);
        return menuBuilder;
    }

    /* access modifiers changed from: private */
    public boolean b(MenuBuilder menuBuilder) {
        Iterator<AlertControllerWrapper.AlertParams.ActionItem> it = this.q.iterator();
        while (it.hasNext()) {
            AlertControllerWrapper.AlertParams.ActionItem next = it.next();
            menuBuilder.add(0, next.id, 0, next.label).setIcon(next.icon).setShowAsAction(2);
        }
        return true;
    }

    public boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
        if (this.r == null) {
            return true;
        }
        this.r.onClick(this.E, menuItem.getItemId());
        return true;
    }

    public void a(CharSequence charSequence) {
        this.h = charSequence;
        if (this.k != null) {
            this.k.setText(charSequence);
        }
    }

    public void b(View view) {
        this.m = view;
    }

    public void b(CharSequence charSequence) {
        this.i = charSequence;
        if (this.l != null) {
            this.l.setText(charSequence);
        }
    }

    public void c(View view) {
        this.n = view;
    }

    public void a(boolean z2, CharSequence charSequence) {
        this.o = z2;
        this.p = charSequence;
    }

    public void a(int i2, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Message message) {
        if (message == null && onClickListener != null) {
            message = this.C.obtainMessage(i2, onClickListener);
        }
        switch (i2) {
            case -3:
                this.A = charSequence;
                this.B = message;
                return;
            case -2:
                this.x = charSequence;
                this.y = message;
                return;
            case -1:
                this.u = charSequence;
                this.v = message;
                return;
            default:
                throw new IllegalStateException("Button does not exist");
        }
    }

    public void a(ArrayList<AlertControllerWrapper.AlertParams.ActionItem> arrayList, DialogInterface.OnClickListener onClickListener) {
        this.q = arrayList;
        this.r = onClickListener;
    }

    public ListView c() {
        return this.j;
    }

    public void a(ListView listView) {
        this.j = listView;
    }

    public int d() {
        return this.f8230a;
    }

    public int e() {
        return this.d;
    }

    public int f() {
        return this.c;
    }

    public int g() {
        return this.b;
    }

    public TextView h() {
        return this.l;
    }

    public void a(boolean[] zArr) {
        this.L = zArr;
    }

    public boolean[] i() {
        return this.L;
    }

    public boolean j() {
        boolean isChecked = ((CheckBox) this.g.findViewById(16908289)).isChecked();
        this.o = isChecked;
        return isChecked;
    }

    public DialogInterface k() {
        return this.E;
    }

    public Button a(int i2) {
        switch (i2) {
            case -3:
                return this.z;
            case -2:
                return this.w;
            case -1:
                return this.t;
            default:
                return null;
        }
    }

    public ActionBar l() {
        return this.H;
    }

    public void a(ListAdapter listAdapter) {
        this.F = listAdapter;
    }

    public void b(int i2) {
        this.K = i2;
    }

    public boolean a(int i2, KeyEvent keyEvent) {
        return this.D != null && this.D.executeKeyEvent(keyEvent);
    }

    public boolean b(int i2, KeyEvent keyEvent) {
        return this.D != null && this.D.executeKeyEvent(keyEvent);
    }

    private void n() {
        ViewGroup viewGroup = (ViewGroup) this.g.findViewById(R.id.topPanel);
        if (viewGroup != null) {
            a(viewGroup);
        }
        ViewGroup viewGroup2 = (ViewGroup) this.g.findViewById(R.id.contentPanel);
        if (viewGroup2 != null) {
            b(viewGroup2);
        }
        FrameLayout frameLayout = (FrameLayout) this.g.findViewById(R.id.customPanel);
        if (frameLayout != null) {
            a(frameLayout);
        }
        ViewGroup viewGroup3 = (ViewGroup) this.g.findViewById(R.id.buttonPanel);
        if (viewGroup3 != null) {
            c(viewGroup3);
        }
    }

    private void a(ViewGroup viewGroup) {
        if (this.m != null) {
            viewGroup.addView(this.m, 0, new LinearLayout.LayoutParams(-1, -2));
            int dimensionPixelSize = this.G.getResources().getDimensionPixelSize(R.dimen.v6_dialog_title_vertical_padding);
            if (this.m.getPaddingTop() != 0) {
                dimensionPixelSize = this.m.getPaddingTop();
            }
            int dimensionPixelSize2 = this.G.getResources().getDimensionPixelSize(R.dimen.v6_dialog_title_horizontal_padding);
            int paddingLeft = this.m.getPaddingLeft() != 0 ? this.m.getPaddingLeft() : dimensionPixelSize2;
            if (this.m.getPaddingRight() != 0) {
                dimensionPixelSize2 = this.m.getPaddingRight();
            }
            this.m.setPadding(paddingLeft, dimensionPixelSize, dimensionPixelSize2, 0);
            viewGroup.removeView(this.g.findViewById(R.id.alertTitle));
        } else if (!TextUtils.isEmpty(this.h)) {
            this.k = (TextView) viewGroup.findViewById(R.id.alertTitle);
            this.k.setText(this.h);
        } else {
            viewGroup.setVisibility(8);
        }
    }

    private void b(ViewGroup viewGroup) {
        this.D = (ScrollView) this.g.findViewById(R.id.scrollView);
        this.D.setFocusable(false);
        this.l = (TextView) this.g.findViewById(R.id.message);
        if (this.l != null) {
            if (this.i != null) {
                this.l.setText(this.i);
                View findViewById = this.g.findViewById(R.id.topPanel);
                if (findViewById != null && findViewById.getVisibility() == 8 && this.n == null) {
                    viewGroup.setPadding(viewGroup.getPaddingLeft(), this.G.getResources().getDimensionPixelSize(R.dimen.v6_dialog_title_vertical_padding), viewGroup.getRight(), viewGroup.getPaddingBottom());
                    return;
                }
                return;
            }
            this.l.setVisibility(8);
            this.D.removeView(this.l);
            if (this.j != null) {
                o();
                viewGroup.removeView(this.g.findViewById(R.id.scrollView));
                viewGroup.addView(this.j, new LinearLayout.LayoutParams(-1, -1));
                viewGroup.setLayoutParams(new LinearLayout.LayoutParams(-1, 0, 1.0f));
                if (this.m == null && this.k == null) {
                    viewGroup.setPadding(0, 0, 0, 0);
                }
                if (Build.W && this.m == null && this.k != null) {
                    this.k.setPadding(0, 0, 0, 0);
                    try {
                        this.g.findViewById(R.id.topPanel).setBackgroundDrawable(this.G.getResources().getDrawable(R.drawable.v6_dialog_title_bg_light));
                    } catch (Resources.NotFoundException unused) {
                    }
                    viewGroup.setPadding(viewGroup.getPaddingLeft(), 0, viewGroup.getPaddingRight(), viewGroup.getPaddingBottom());
                    return;
                }
                return;
            }
            viewGroup.setVisibility(8);
        }
    }

    private void o() {
        int choiceMode = this.j.getChoiceMode();
        if (this.F != null) {
            if (choiceMode == 2) {
                Button button = (Button) this.g.findViewById(R.id.cancel);
                if (button != null) {
                    button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            AlertControllerImpl.this.E.dismiss();
                        }
                    });
                }
                Button button2 = (Button) this.g.findViewById(R.id.select);
                if (button2 != null) {
                    this.s = button2;
                    button2.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                        }
                    });
                }
                if (this.s == null) {
                    this.j.setAdapter(this.F);
                }
            } else {
                this.j.setAdapter(this.F);
            }
        }
        if (this.K > -1) {
            this.j.setItemChecked(this.K, true);
            this.j.setSelection(this.K);
        }
    }

    private void a(FrameLayout frameLayout) {
        if (this.n != null) {
            ((FrameLayout) this.g.findViewById(16908331)).addView(this.n, new ViewGroup.LayoutParams(-1, -1));
            if (this.j != null) {
                ((LinearLayout.LayoutParams) frameLayout.getLayoutParams()).weight = 0.0f;
            }
            if (this.n instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) this.n;
                int dimensionPixelSize = this.G.getResources().getDimensionPixelSize(R.dimen.v6_dialog_custom_vertical_padding);
                if (viewGroup.getPaddingTop() != 0) {
                    dimensionPixelSize = viewGroup.getPaddingTop();
                }
                int dimensionPixelSize2 = this.G.getResources().getDimensionPixelSize(R.dimen.v6_dialog_custom_horizontal_padding);
                int paddingLeft = viewGroup.getPaddingLeft() != 0 ? viewGroup.getPaddingLeft() : dimensionPixelSize2;
                if (viewGroup.getPaddingRight() != 0) {
                    dimensionPixelSize2 = viewGroup.getPaddingRight();
                }
                View findViewById = viewGroup.findViewById(16908301);
                if (findViewById == null || (findViewById instanceof ProgressBar)) {
                    viewGroup.setPadding(paddingLeft, dimensionPixelSize, dimensionPixelSize2, viewGroup.getPaddingBottom());
                    frameLayout.setPadding(0, 0, 0, 0);
                    return;
                }
                frameLayout.setPadding(0, 0, 0, 0);
                return;
            }
            return;
        }
        frameLayout.setVisibility(8);
    }

    private void b(FrameLayout frameLayout) {
        if (this.p != null) {
            frameLayout.setVisibility(0);
            CheckBox checkBox = (CheckBox) frameLayout.findViewById(16908289);
            checkBox.setChecked(this.o);
            checkBox.setText(this.p);
            return;
        }
        frameLayout.setVisibility(8);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00c1  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00f8  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0046  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c(android.view.ViewGroup r7) {
        /*
            r6 = this;
            r0 = 16908313(0x1020019, float:2.38773E-38)
            android.view.View r0 = r7.findViewById(r0)
            android.widget.Button r0 = (android.widget.Button) r0
            r6.t = r0
            android.widget.Button r0 = r6.t
            r1 = 8
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L_0x0036
            android.widget.Button r0 = r6.t
            android.view.View$OnClickListener r4 = r6.J
            r0.setOnClickListener(r4)
            java.lang.CharSequence r0 = r6.u
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x0028
            android.widget.Button r0 = r6.t
            r0.setVisibility(r1)
            goto L_0x0036
        L_0x0028:
            android.widget.Button r0 = r6.t
            java.lang.CharSequence r4 = r6.u
            r0.setText(r4)
            android.widget.Button r0 = r6.t
            r0.setVisibility(r3)
            r0 = 1
            goto L_0x0037
        L_0x0036:
            r0 = 0
        L_0x0037:
            r4 = 16908314(0x102001a, float:2.3877302E-38)
            android.view.View r4 = r7.findViewById(r4)
            android.widget.Button r4 = (android.widget.Button) r4
            r6.w = r4
            android.widget.Button r4 = r6.w
            if (r4 == 0) goto L_0x0068
            android.widget.Button r4 = r6.w
            android.view.View$OnClickListener r5 = r6.J
            r4.setOnClickListener(r5)
            java.lang.CharSequence r4 = r6.x
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 == 0) goto L_0x005b
            android.widget.Button r4 = r6.w
            r4.setVisibility(r1)
            goto L_0x0068
        L_0x005b:
            android.widget.Button r0 = r6.w
            java.lang.CharSequence r4 = r6.x
            r0.setText(r4)
            android.widget.Button r0 = r6.w
            r0.setVisibility(r3)
            r0 = 1
        L_0x0068:
            r4 = 16908315(0x102001b, float:2.3877305E-38)
            android.view.View r4 = r7.findViewById(r4)
            android.widget.Button r4 = (android.widget.Button) r4
            r6.z = r4
            android.widget.Button r4 = r6.z
            if (r4 == 0) goto L_0x0099
            android.widget.Button r4 = r6.z
            android.view.View$OnClickListener r5 = r6.J
            r4.setOnClickListener(r5)
            java.lang.CharSequence r4 = r6.A
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 == 0) goto L_0x008c
            android.widget.Button r4 = r6.z
            r4.setVisibility(r1)
            goto L_0x0099
        L_0x008c:
            android.widget.Button r0 = r6.z
            java.lang.CharSequence r4 = r6.A
            r0.setText(r4)
            android.widget.Button r0 = r6.z
            r0.setVisibility(r3)
            r0 = 1
        L_0x0099:
            if (r0 == 0) goto L_0x00c1
            boolean r0 = miuipub.os.Build.W
            if (r0 == 0) goto L_0x00c4
            java.lang.CharSequence r0 = r6.i
            if (r0 != 0) goto L_0x00c4
            android.widget.ListView r0 = r6.j
            if (r0 == 0) goto L_0x00c4
            int r0 = r7.getChildCount()
            if (r0 != r2) goto L_0x00c4
            android.view.View r0 = r7.getChildAt(r3)
            int r1 = r7.getPaddingLeft()
            int r4 = r7.getPaddingRight()
            int r5 = r7.getPaddingBottom()
            r0.setPadding(r1, r3, r4, r5)
            goto L_0x00c4
        L_0x00c1:
            r7.setVisibility(r1)
        L_0x00c4:
            android.view.View r7 = r7.getChildAt(r3)
            android.view.ViewGroup r7 = (android.view.ViewGroup) r7
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
        L_0x00d0:
            int r4 = r7.getChildCount()
            if (r1 >= r4) goto L_0x00e6
            android.view.View r4 = r7.getChildAt(r1)
            int r5 = r4.getVisibility()
            if (r5 != 0) goto L_0x00e3
            r0.add(r4)
        L_0x00e3:
            int r1 = r1 + 1
            goto L_0x00d0
        L_0x00e6:
            int r7 = r0.size()
            if (r7 != r2) goto L_0x00f8
            java.lang.Object r7 = r0.get(r3)
            android.view.View r7 = (android.view.View) r7
            int r0 = miuipub.v6.R.drawable.v6_btn_bg_dialog_light_single
            r7.setBackgroundResource(r0)
            goto L_0x0128
        L_0x00f8:
            if (r7 <= r2) goto L_0x0128
        L_0x00fa:
            if (r3 >= r7) goto L_0x0128
            if (r3 != 0) goto L_0x010a
            java.lang.Object r1 = r0.get(r3)
            android.view.View r1 = (android.view.View) r1
            int r2 = miuipub.v6.R.drawable.v6_btn_bg_dialog_light_first
            r1.setBackgroundResource(r2)
            goto L_0x0125
        L_0x010a:
            int r1 = r7 + -1
            if (r3 != r1) goto L_0x011a
            java.lang.Object r1 = r0.get(r3)
            android.view.View r1 = (android.view.View) r1
            int r2 = miuipub.v6.R.drawable.v6_btn_bg_dialog_light_last
            r1.setBackgroundResource(r2)
            goto L_0x0125
        L_0x011a:
            java.lang.Object r1 = r0.get(r3)
            android.view.View r1 = (android.view.View) r1
            int r2 = miuipub.v6.R.drawable.v6_btn_bg_dialog_light_middle
            r1.setBackgroundResource(r2)
        L_0x0125:
            int r3 = r3 + 1
            goto L_0x00fa
        L_0x0128:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miuipub.internal.app.AlertControllerImpl.c(android.view.ViewGroup):void");
    }
}
