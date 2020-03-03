package com.mics.core.ui.page;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mics.R;
import com.mics.core.MiCS;
import com.mics.network.NetworkManager;
import com.mics.util.Divider;
import com.mics.util.KeyboardUtils;
import com.mics.widget.EmojiKeyboardLoader;
import com.mics.widget.RecyclerViewScrollCompat;
import com.mics.widget.SpringView.container.StickyHeaderFooterView;
import com.mics.widget.SpringView.widget.SpringView;
import com.mics.widget.TitleBar;
import com.mics.widget.stickyball.widget.DotIndicatorView;
import com.mics.widget.util.ChatUtils;
import com.mics.widget.util.MiCSToastManager;

public class ChatActivity extends Activity implements SpringView.OnRefreshListener {

    /* renamed from: a  reason: collision with root package name */
    private static final int f7703a = 100;
    private ViewTreeObserver.OnGlobalLayoutListener A = new ViewTreeObserver.OnGlobalLayoutListener() {
        public void onGlobalLayout() {
            ChatActivity.this.c.post(new Runnable() {
                public void run() {
                    ChatActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            ChatActivity.this.g();
                        }
                    });
                }
            });
        }
    };
    private Rect b = new Rect();
    /* access modifiers changed from: private */
    public View c;
    private int d;
    private TitleBar e;
    private SpringView f;
    /* access modifiers changed from: private */
    public RecyclerViewScrollCompat g;
    private int h;
    /* access modifiers changed from: private */
    public int i;
    private EditText j;
    private ImageView k;
    private ImageView l;
    private View m;
    /* access modifiers changed from: private */
    public RelativeLayout n;
    private ImageView o;
    private ImageView p;
    private ImageView q;
    private TextView r;
    /* access modifiers changed from: private */
    public RelativeLayout s;
    /* access modifiers changed from: private */
    public DotIndicatorView t;
    /* access modifiers changed from: private */
    public RecyclerView u;
    private LayoutParamsWrapper v;
    private Interpolator w;
    private ObjectAnimator x;
    private View y;
    /* access modifiers changed from: private */
    public ChatDelegate z;

    public void onLoadMore(View view) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.mics_activity_chat);
        getWindow().setBackgroundDrawable((Drawable) null);
        a();
        b();
        c();
        d();
        this.z = ChatDelegate.a((Activity) this);
        this.z.r();
    }

    private void a() {
        this.e = (TitleBar) findViewById(R.id.tb_chat);
        this.e.setBackgroundResource(R.drawable.mics_bg_title_bar);
        this.e.translucentStatus(this, true);
    }

    private void b() {
        this.f = (SpringView) findViewById(R.id.spring_chat);
        this.f.setType(SpringView.Type.FOLLOW);
        this.f.setOnRefreshListener(this);
        this.f.setHeader(new StickyHeaderFooterView(R.layout.mics_sticky_paging_empty));
        this.f.setFooter(new StickyHeaderFooterView(R.layout.mics_sticky_paging_empty));
        Divider divider = new Divider(this, 0, (int) TypedValue.applyDimension(1, 10.0f, getResources().getDisplayMetrics()), Color.parseColor("#eff0f1"), true);
        this.g = (RecyclerViewScrollCompat) findViewById(R.id.rv_chat);
        this.g.addItemDecoration(divider);
        this.g.setTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 0) {
                    return false;
                }
                ChatActivity.this.i();
                return false;
            }
        });
    }

    private void c() {
        this.j = (EditText) findViewById(R.id.et_chat_tool_input);
        this.m = findViewById(R.id.fl_chat_tool_send);
        ChatUtils.a(this.j, this.m);
        this.m.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (NetworkManager.b()) {
                    ChatActivity.this.e();
                } else {
                    MiCSToastManager.a().a((CharSequence) "网络异常");
                }
            }
        });
        this.i = (int) TypedValue.applyDimension(1, 280.0f, getResources().getDisplayMetrics());
        this.l = (ImageView) findViewById(R.id.iv_chat_tool_more);
        this.l.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean z = true;
                if (ChatActivity.this.h()) {
                    ChatActivity.this.a(true);
                    ChatActivity.this.b(false);
                    ChatActivity.this.a(ChatActivity.this.i);
                    return;
                }
                if (ChatActivity.this.s.getVisibility() == 4) {
                    KeyboardUtils.a((Context) ChatActivity.this);
                }
                ChatActivity chatActivity = ChatActivity.this;
                if (ChatActivity.this.n.getVisibility() == 0) {
                    z = false;
                }
                chatActivity.a(z);
                ChatActivity.this.b(false);
            }
        });
        this.k = (ImageView) findViewById(R.id.iv_chat_tool_emoji);
        this.k.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EmojiKeyboardLoader.a(ChatActivity.this.u, ChatActivity.this.t);
                boolean z = true;
                if (ChatActivity.this.h()) {
                    ChatActivity.this.b(true);
                    ChatActivity.this.a(false);
                    ChatActivity.this.a(ChatActivity.this.i);
                    return;
                }
                if (ChatActivity.this.n.getVisibility() == 4) {
                    KeyboardUtils.a((Context) ChatActivity.this);
                }
                ChatActivity chatActivity = ChatActivity.this;
                if (ChatActivity.this.s.getVisibility() != 4) {
                    z = false;
                }
                chatActivity.b(z);
                ChatActivity.this.a(false);
            }
        });
        this.n = (RelativeLayout) findViewById(R.id.rl_chat_more);
        this.o = (ImageView) findViewById(R.id.iv_chat_more_evaluate);
        this.p = (ImageView) findViewById(R.id.iv_chat_more_gallery);
        this.p.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ChatActivity.this.z != null) {
                    ChatActivity.this.z.c(100);
                }
            }
        });
        this.o.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ChatActivity.this.z != null) {
                    ChatActivity.this.z.b(1);
                }
            }
        });
        this.s = (RelativeLayout) findViewById(R.id.rl_chat_emoji);
        this.t = (DotIndicatorView) findViewById(R.id.indicator_emoji);
        this.u = (RecyclerView) findViewById(R.id.rv_chat_emoji);
        EmojiKeyboardLoader.a(this, this.u, this.j);
    }

    private void d() {
        this.y = findViewById(R.id.v_bashline);
        this.c = getWindow().getDecorView().findViewById(16908290);
        f();
        getWindow().setSoftInputMode(2);
    }

    public void onResume() {
        super.onResume();
        if (this.z != null) {
            this.z.n();
        }
    }

    public void onPause() {
        super.onPause();
        if (this.z != null) {
            this.z.o();
        }
    }

    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 100 && intent != null && MiCS.a().f() != null) {
            String[] a2 = MiCS.a().f().a(intent);
            if (this.z != null) {
                this.z.a(a2);
            }
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        if (this.j.getText().length() > 0) {
            Editable text = this.j.getText();
            if (this.z != null) {
                this.z.a((CharSequence) text);
            }
            this.j.setText("");
        }
    }

    public void onRefresh(View view) {
        if (this.z != null) {
            this.z.t();
        }
    }

    public void finish() {
        if (this.z != null) {
            this.z.o();
            this.z.p();
            this.z = null;
        }
        KeyboardUtils.a((View) this.j);
        super.finish();
    }

    public void onBackPressed() {
        if (this.n.getVisibility() == 0 || this.s.getVisibility() == 0) {
            a(0);
        } else {
            finish();
        }
    }

    private void f() {
        this.c.getViewTreeObserver().addOnGlobalLayoutListener(this.A);
    }

    /* access modifiers changed from: private */
    public void g() {
        this.c.getWindowVisibleDisplayFrame(this.b);
        int i2 = this.b.bottom;
        if (this.d == 0) {
            this.d = this.c.getHeight();
            ChatUtils.a(this.g);
            return;
        }
        this.d = this.c.getHeight();
        int i3 = this.d - i2;
        if (i3 != this.h) {
            if (!(this.n.getVisibility() == 0 || this.s.getVisibility() == 0) || h() || (!h() && ((this.n.getVisibility() == 0 || this.s.getVisibility() == 0) && i3 > 0))) {
                a(i3);
                a(false);
                b(false);
            }
            if (i3 > 0) {
                this.i = i3;
            }
            this.h = i3;
        }
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        if (this.v == null) {
            this.v = new LayoutParamsWrapper(this.y);
            this.w = new DecelerateInterpolator();
        }
        int i3 = this.v.c.bottomMargin;
        if (i2 != i3) {
            this.x = ObjectAnimator.ofInt(this.v, "marginBottom", new int[]{i3, i2});
            this.x.setDuration(200);
            this.x.setInterpolator(this.w);
            this.x.start();
        }
        if (i2 == 0) {
            a(false);
            b(false);
        }
    }

    /* access modifiers changed from: private */
    public void a(boolean z2) {
        int i2 = z2 ? 0 : 4;
        if (this.n.getVisibility() != i2) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(z2 ? 0.6f : 0.4f, z2 ? 1.0f : 0.0f);
            alphaAnimation.setDuration(100);
            this.n.startAnimation(alphaAnimation);
        }
        this.n.setVisibility(i2);
    }

    /* access modifiers changed from: private */
    public void b(boolean z2) {
        int i2 = z2 ? 0 : 4;
        if (this.s.getVisibility() != i2) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(z2 ? 0.6f : 0.4f, z2 ? 1.0f : 0.0f);
            alphaAnimation.setDuration(100);
            this.s.startAnimation(alphaAnimation);
        }
        this.s.setVisibility(i2);
        this.k.setImageResource(z2 ? R.drawable.mics_icon_cs_tool_keyboard : R.drawable.mics_icon_cs_tool_emoji);
    }

    public class LayoutParamsWrapper {
        private View b;
        /* access modifiers changed from: private */
        public ViewGroup.MarginLayoutParams c;

        LayoutParamsWrapper(View view) {
            this.b = view;
            this.c = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        }

        @SuppressLint({"AnimatorKeep"})
        public void setMarginBottom(int i) {
            this.c.bottomMargin = i;
            this.b.setLayoutParams(this.c);
            ChatUtils.a(ChatActivity.this.g);
        }
    }

    /* access modifiers changed from: private */
    public boolean h() {
        return ((ViewGroup.MarginLayoutParams) this.y.getLayoutParams()).bottomMargin == 0;
    }

    /* access modifiers changed from: private */
    public void i() {
        KeyboardUtils.a((View) this.j);
        if (this.n.getVisibility() == 0 || this.s.getVisibility() == 0) {
            a(0);
        }
    }
}
