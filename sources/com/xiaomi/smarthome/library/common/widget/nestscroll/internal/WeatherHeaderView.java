package com.xiaomi.smarthome.library.common.widget.nestscroll.internal;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.support.v4.content.LocalBroadcastManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.LoginApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.widget.ParticleSystemHelper;
import com.xiaomi.smarthome.library.common.widget.nestscroll.IPullHeader;
import com.xiaomi.smarthome.library.common.widget.nestscroll.PullHeaderLayout;
import com.xiaomi.smarthome.library.common.widget.nestscroll.RefreshHeader;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.areainfo.AreaInfoManager;
import com.xiaomi.smarthome.miio.page.smartgroup.SmartGroupConstants;
import com.xiaomi.smarthome.miio.page.smartgroup.SmartGroupWeatherActivity;
import com.xiaomi.smarthome.miio.page.smartgroup.SmartgroupWpActivity;
import com.ximalaya.ting.android.opensdk.constants.PreferenceConstantsInOpenSdk;

public class WeatherHeaderView extends FrameLayout implements IPullHeader {
    private static final int P = 1;
    private static final int Q = 2;
    private static final int R = 0;

    /* renamed from: a  reason: collision with root package name */
    private static final String f19035a = "WeatherHeaderView";
    public static boolean mIsSupportWeather = true;
    private ImageView A;
    private BroadcastReceiver B;
    /* access modifiers changed from: private */
    public boolean C = false;
    private boolean D = true;
    private boolean E = false;
    /* access modifiers changed from: private */
    public boolean F = true;
    /* access modifiers changed from: private */
    public boolean G = false;
    /* access modifiers changed from: private */
    public float H = -2.14748365E9f;
    /* access modifiers changed from: private */
    public float I = 0.0f;
    private float J = 0.0f;
    private boolean K = false;
    private boolean L = false;
    private boolean M = false;
    private float N = -2.14748365E9f;
    private float O = 0.0f;
    private int S = 0;
    /* access modifiers changed from: private */
    public ParticleSystemHelper T;
    private Runnable U = new Runnable() {
        public void run() {
            WeatherHeaderView.this.c();
        }
    };
    private ViewGroup V = null;
    /* access modifiers changed from: private */
    public int[] W = null;
    private Runnable aa = new Runnable() {
        public void run() {
            if (WeatherHeaderView.this.f != null) {
                WeatherHeaderView.this.f.setImageResource(R.drawable.std_titlebar_main_add_white);
            }
            TitleBarUtil.b((Activity) WeatherHeaderView.this.getContext());
            WeatherHeaderView.this.d.setImageResource(R.drawable.client_all_drawer_lite_btn);
            if (WeatherHeaderView.this.F) {
                WeatherHeaderView.this.c.setBackgroundResource(R.drawable.transparent);
                WeatherHeaderView.this.g.setTextColor(WeatherHeaderView.this.getResources().getColor(R.color.white));
                if (Math.abs(WeatherHeaderView.this.mProgress) <= 0.01f || WeatherHeaderView.this.G) {
                    WeatherHeaderView.this.g.setAlpha(1.0f);
                } else {
                    WeatherHeaderView.this.g.setAlpha(0.0f);
                }
            } else {
                boolean unused = WeatherHeaderView.this.F = true;
                ValueAnimator ofFloat = ObjectAnimator.ofFloat(new float[]{255.0f, 0.0f});
                ofFloat.setDuration(300);
                ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        WeatherHeaderView.this.c.setBackgroundColor((((int) ((Float) valueAnimator.getAnimatedValue()).floatValue()) << 24) | 15724528);
                    }
                });
                ofFloat.addListener(new SimpleAnimatorListener() {
                    public void onAnimationEnd(Animator animator) {
                        WeatherHeaderView.this.c.setBackgroundResource(R.drawable.transparent);
                        WeatherHeaderView.this.g.setTextColor(WeatherHeaderView.this.getResources().getColor(R.color.white));
                        if (Math.abs(WeatherHeaderView.this.mProgress) <= 0.01f || WeatherHeaderView.this.G) {
                            WeatherHeaderView.this.g.setAlpha(1.0f);
                        } else {
                            WeatherHeaderView.this.g.setAlpha(0.0f);
                        }
                        WeatherHeaderView.this.c.setClickable(false);
                    }
                });
                ofFloat.start();
            }
        }
    };
    private Runnable ab = new Runnable() {
        public void run() {
            WeatherHeaderView.this.d.setImageResource(R.drawable.client_all_drawer_btn);
            if (WeatherHeaderView.this.f != null) {
                WeatherHeaderView.this.f.setImageResource(R.drawable.std_tittlebar_main_device_add);
            }
            TitleBarUtil.a((Activity) WeatherHeaderView.this.getContext());
            if (!WeatherHeaderView.this.F) {
                WeatherHeaderView.this.c.setBackgroundResource(R.drawable.common_title_bar_bg);
                WeatherHeaderView.this.g.setTextColor(WeatherHeaderView.this.getResources().getColor(R.color.black_80_transparent));
                WeatherHeaderView.this.g.setAlpha(1.0f);
                return;
            }
            boolean unused = WeatherHeaderView.this.F = false;
            ValueAnimator ofFloat = ObjectAnimator.ofFloat(new float[]{0.0f, 255.0f});
            ofFloat.setDuration(300);
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    WeatherHeaderView.this.c.setBackgroundColor((((int) ((Float) valueAnimator.getAnimatedValue()).floatValue()) << 24) | 15724528);
                }
            });
            ofFloat.addListener(new SimpleAnimatorListener() {
                public void onAnimationEnd(Animator animator) {
                    WeatherHeaderView.this.c.setBackgroundResource(R.drawable.common_title_bar_bg);
                    WeatherHeaderView.this.g.setTextColor(WeatherHeaderView.this.getResources().getColor(R.color.black_80_transparent));
                    WeatherHeaderView.this.g.setAlpha(1.0f);
                    WeatherHeaderView.this.c.setClickable(true);
                }
            });
            ofFloat.start();
        }
    };
    private boolean ac = false;
    private View b;
    /* access modifiers changed from: private */
    public View c;
    /* access modifiers changed from: private */
    public ImageView d;
    /* access modifiers changed from: private */
    public ImageView e;
    /* access modifiers changed from: private */
    public ImageView f;
    boolean firstShowProgressBar = false;
    /* access modifiers changed from: private */
    public TextView g;
    private View h;
    private View i;
    private View j;
    private View k;
    /* access modifiers changed from: private */
    public TextView l;
    /* access modifiers changed from: private */
    public TextView m;
    View mHeaderContentView;
    View mNoLoginOfflineView;
    protected float mProgress = 0.0f;
    protected int mPullState = 0;
    private TextView n;
    private TextView o;
    /* access modifiers changed from: private */
    public TextView p;
    /* access modifiers changed from: private */
    public TextView q;
    /* access modifiers changed from: private */
    public TextView r;
    private View s;
    private TextView t;
    private TextView u;
    private TextView v;
    private TextView w;
    /* access modifiers changed from: private */
    public ProgressBar x;
    /* access modifiers changed from: private */
    public View y;
    private ImageView z;

    private float getChangeTitleBarProgress() {
        return 0.99f;
    }

    public void addXiaofangView(View view) {
    }

    public void removeXiaofangView(View view) {
    }

    public void setExpandable(boolean z2) {
        this.D = z2;
    }

    public WeatherHeaderView(Context context) {
        super(context);
        a();
    }

    public WeatherHeaderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public WeatherHeaderView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a();
    }

    private void a() {
        LayoutInflater.from(getContext()).inflate(R.layout.weahter_header_view_nestscroll, this);
        this.b = findViewById(R.id.weather_root_view);
        this.l = (TextView) this.b.findViewById(R.id.title_1);
        this.l.setIncludeFontPadding(false);
        this.m = (TextView) this.b.findViewById(R.id.title_1_suffix);
        this.i = this.b.findViewById(R.id.variable_layout);
        this.c = this.b.findViewById(R.id.title_bar);
        this.h = this.b.findViewById(R.id.title_bar_spacer);
        this.x = (ProgressBar) findViewById(R.id.pb_loading);
        this.z = (ImageView) findViewById(R.id.weather_nestscroll_bg_iv);
        this.A = (ImageView) findViewById(R.id.foregroud_iv);
        this.n = (TextView) findViewById(R.id.weather_title);
        this.p = (TextView) findViewById(R.id.weather_title_right);
        this.q = (TextView) findViewById(R.id.aqi_title_right);
        this.o = (TextView) findViewById(R.id.loc_title);
        this.r = (TextView) findViewById(R.id.loc_title_right);
        this.t = (TextView) findViewById(R.id.aqi_title);
        this.u = (TextView) findViewById(R.id.aqi_title_below);
        this.v = (TextView) findViewById(R.id.water_title);
        this.w = (TextView) findViewById(R.id.temp_title);
        this.j = findViewById(R.id.bottom_content);
        this.k = findViewById(R.id.top_content);
        this.mHeaderContentView = findViewById(R.id.header_view_content);
        this.mNoLoginOfflineView = findViewById(R.id.header_view_no_login);
        this.V = (ViewGroup) findViewById(R.id.btm_tips);
        this.s = findViewById(R.id.loc_weather_btm_container);
        a(this.b);
        b();
        findViewById(R.id.aqi_layout).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WeatherHeaderView.this.a(SmartgroupWpActivity.class, 0);
            }
        });
        findViewById(R.id.water_layout).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WeatherHeaderView.this.a(SmartgroupWpActivity.class, 1);
            }
        });
        findViewById(R.id.temp_layout).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WeatherHeaderView.this.a(SmartGroupWeatherActivity.class, -1);
            }
        });
        findViewById(R.id.title_1).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WeatherHeaderView.this.a(SmartGroupWeatherActivity.class, -1);
            }
        });
        AnonymousClass5 r1 = new View.OnClickListener() {
            public void onClick(View view) {
                AreaInfoManager.a().a(WeatherHeaderView.this.getContext());
            }
        };
        findViewById(R.id.loc_title).setOnClickListener(r1);
        findViewById(R.id.weather_title).setOnClickListener(r1);
        findViewById(R.id.loc_title_right).setOnClickListener(r1);
        findViewById(R.id.weather_title_right).setOnClickListener(r1);
        this.T = ParticleSystemHelper.a(findViewById(R.id.ref_view));
    }

    /* access modifiers changed from: private */
    public void a(Class<?> cls, int i2) {
        Context context = getContext();
        if (context != null && AreaInfoManager.a().j()) {
            Intent intent = new Intent(context, cls);
            intent.putExtra(SmartGroupConstants.f19890a, AreaInfoManager.a().f());
            if (i2 != -1) {
                intent.putExtra("type", i2);
            }
            context.startActivity(intent);
        }
    }

    private void a(View view) {
        this.d = (ImageView) view.findViewById(R.id.drawer_btn);
        if (CoreApi.a().D() || !CoreApi.a().q()) {
            this.d.setVisibility(8);
        } else {
            this.d.setVisibility(0);
        }
        this.g = (TextView) view.findViewById(R.id.module_a_2_more_title);
        this.x = (ProgressBar) view.findViewById(R.id.title_prog);
        resizeTitleBar();
        post(new Runnable() {
            public void run() {
                ViewStub viewStub;
                if (!WeatherHeaderView.this.C && (viewStub = (ViewStub) WeatherHeaderView.this.findViewById(R.id.title_more_stub)) != null) {
                    View inflate = viewStub.inflate();
                    ImageView unused = WeatherHeaderView.this.f = (ImageView) inflate.findViewById(R.id.module_a_2_return_more_more_btn_text);
                    WeatherHeaderView.this.f.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            View findViewById = WeatherHeaderView.this.getRootView().findViewById(R.id.new_message_tag);
                            if (findViewById != null) {
                                ((SmartHomeMainActivity) WeatherHeaderView.this.getContext()).showAddView(findViewById.getVisibility() == 0, false);
                            }
                        }
                    });
                    View unused2 = WeatherHeaderView.this.y = inflate.findViewById(R.id.new_message_tag);
                }
            }
        });
        this.d.findViewById(R.id.drawer_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!(WeatherHeaderView.this.e == null || WeatherHeaderView.this.e.getVisibility() == 4)) {
                    WeatherHeaderView.this.e.setVisibility(4);
                }
                LocalBroadcastManager.getInstance(WeatherHeaderView.this.getContext()).sendBroadcast(new Intent(SmartHomeMainActivity.ACTION_OPEN_DRAWER));
            }
        });
    }

    private void b() {
        if (this.B == null) {
            this.B = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (AreaInfoManager.f11897a.equals(intent.getAction())) {
                        WeatherHeaderView.this.updateViews();
                    }
                }
            };
        }
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(this.B, new IntentFilter(AreaInfoManager.f11897a));
        AreaInfoManager.a().a(SHApplication.getAppContext(), false);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.C = false;
        ViewGroup viewGroup = (ViewGroup) getParent();
        if (viewGroup instanceof PullHeaderLayout) {
            ((PullHeaderLayout) viewGroup).addRefreshHeader(new RefreshHeader() {
                public void reset() {
                    WeatherHeaderView.this.x.setVisibility(8);
                }

                public void pull() {
                    WeatherHeaderView.this.x.setVisibility(8);
                }

                public void refreshing() {
                    if (WeatherHeaderView.this.firstShowProgressBar) {
                        WeatherHeaderView.this.firstShowProgressBar = false;
                    } else if (WeatherHeaderView.this.x.getVisibility() != 0) {
                        WeatherHeaderView.this.x.setVisibility(0);
                    }
                }

                public void onPositionChange(float f, float f2, float f3, boolean z, PullHeaderLayout.State state) {
                    if (WeatherHeaderView.this.firstShowProgressBar) {
                        WeatherHeaderView.this.firstShowProgressBar = false;
                    } else if (WeatherHeaderView.this.x.getVisibility() != 0) {
                        WeatherHeaderView.this.x.setVisibility(0);
                    }
                }

                public void complete() {
                    WeatherHeaderView.this.x.setVisibility(8);
                }
            });
        }
    }

    public void setNewDeviceViewVisibility(int i2) {
        if (this.y != null) {
            this.y.setVisibility(i2);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        this.C = true;
        super.onDetachedFromWindow();
        this.W = null;
        this.L = false;
        this.M = false;
        try {
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this.B);
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public void resizeTitleBar() {
        TitleBarUtil.a((Activity) getContext(), (View) this);
        if (!TitleBarUtil.f11582a) {
            try {
                setTitleBar(this);
            } catch (Exception unused) {
            }
        }
        TitleBarUtil.b((Activity) getContext());
    }

    public static void setTitleBar(View view) {
        View findViewById = view.findViewById(R.id.title_bar);
        if (findViewById != null) {
            int dimensionPixelSize = view.getResources().getDimensionPixelSize(R.dimen.title_bar_top_padding);
            try {
                Resources resources = SHApplication.getApplication().getResources();
                dimensionPixelSize = resources.getDimensionPixelOffset(resources.getIdentifier(PreferenceConstantsInOpenSdk.B, "dimen", "android"));
            } catch (Exception unused) {
            }
            View findViewById2 = findViewById.findViewById(R.id.placeholder);
            findViewById2.getLayoutParams().height = dimensionPixelSize - DisplayUtils.d(view.getContext(), 6.0f);
            findViewById2.setVisibility(0);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void offsetTopAndBottom(int i2) {
        if (this.M) {
            this.i.offsetTopAndBottom(i2);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int top = this.i.getTop();
        int top2 = this.k.getTop();
        int left = this.l.getLeft();
        super.onLayout(z2, i2, i3, i4, i5);
        c();
        if (this.M) {
            if (top != this.i.getTop()) {
                this.i.offsetTopAndBottom(top - this.i.getTop());
            }
            if (top2 != this.k.getTop()) {
                this.k.offsetTopAndBottom(top2 - this.k.getTop());
            }
            if (this.L && left != this.l.getLeft()) {
                int left2 = left - this.l.getLeft();
                this.l.offsetLeftAndRight(left2);
                this.m.offsetLeftAndRight(left2);
                this.p.offsetLeftAndRight(left2);
                this.q.offsetLeftAndRight(left2);
                this.r.offsetLeftAndRight(left2);
            }
        }
        removeCallbacks(this.U);
        if (this.E) {
            postDelayed(this.U, 1000);
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        if (!this.M && this.l.getHeight() > 0) {
            this.M = true;
            this.J = (float) this.i.getTop();
            this.N = (float) this.k.getTop();
            this.O = (((float) this.mHeaderContentView.getHeight()) - (getResources().getDisplayMetrics().density * 16.0f)) - ((float) (this.k.getHeight() / 2));
            this.z.setPivotX((float) (this.z.getWidth() / 2));
            this.z.setPivotY(((float) this.z.getHeight()) * 0.76462394f);
        }
        if (!this.L && this.r.getWidth() > 0 && this.E) {
            this.L = true;
            this.H = (float) this.l.getLeft();
            float max = ((float) Math.max(this.r.getRight(), this.q.getRight())) - this.H;
            String str = f19035a;
            Log.d(str, "width=" + max + ",mLocTitleRight.getWidth=" + this.r.getWidth() + ",mTitle1TargetX-mTitle1X" + (this.H - this.I));
            this.I = (((float) getResources().getDisplayMetrics().widthPixels) - max) / 2.0f;
        }
    }

    public void onPullProgress(PullHeaderLayout pullHeaderLayout, int i2, float f2, int i3, boolean z2) {
        findViewById(R.id.aqi_layout).setOnClickListener((View.OnClickListener) null);
        findViewById(R.id.water_layout).setOnClickListener((View.OnClickListener) null);
        findViewById(R.id.temp_layout).setOnClickListener((View.OnClickListener) null);
        this.mPullState = i2;
        this.mProgress = f2;
        this.G = z2;
        if (!this.ac) {
            if (i3 >= 0 || Math.abs(this.mProgress) >= getChangeTitleBarProgress()) {
                if (i3 > 0 && Math.abs(this.mProgress) > getChangeTitleBarProgress() && this.F) {
                    removeCallbacks(this.ab);
                    post(this.ab);
                }
            } else if (!this.F) {
                removeCallbacks(this.aa);
                post(this.aa);
            }
        }
        if (this.D) {
            c();
            if (this.M) {
                int d2 = DisplayUtils.d(getContext(), 30.0f);
                if (((double) Math.abs(this.mProgress)) <= 0.5d) {
                    findViewById(R.id.aqi_layout).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            WeatherHeaderView.this.a(SmartgroupWpActivity.class, 0);
                        }
                    });
                    findViewById(R.id.water_layout).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            WeatherHeaderView.this.a(SmartgroupWpActivity.class, 1);
                        }
                    });
                    findViewById(R.id.temp_layout).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            WeatherHeaderView.this.a(SmartGroupWeatherActivity.class, -1);
                        }
                    });
                }
                if (((double) Math.abs(this.mProgress)) <= 0.001d && this.r.getWidth() > 0) {
                    this.I = (((float) getResources().getDisplayMetrics().widthPixels) - (((float) Math.max(this.r.getRight(), this.q.getRight())) - this.H)) / 2.0f;
                }
                String str = f19035a;
                LogUtil.a(str, "onPullProgress progress=" + f2 + ",overDrag=" + z2 + ",mTopContentY=" + this.N + ",mTopContentTargetY=" + this.O);
                if (z2) {
                    d();
                    return;
                }
                float f3 = (float) d2;
                this.mNoLoginOfflineView.setPadding(0, 0, 0, (int) (((float) (((int) (1.0f - (Math.abs(this.mProgress) * f3))) + d2)) + ((Math.abs(this.mProgress) * f3) / 3.0f)));
                this.z.setScaleX(1.0f - (Math.abs(f2) * 0.4f));
                this.z.setScaleY(1.0f - (Math.abs(f2) * 0.4f));
                float abs = ((this.O - this.N) * Math.abs(f2)) + this.N;
                float top = abs - ((float) this.k.getTop());
                LogUtil.a("onPullProgress", "onPullProgress progress=" + f2 + ",mTopContentY=" + this.N + ",mTopContentTargetY=" + this.O);
                StringBuilder sb = new StringBuilder();
                sb.append("mTopLayout.getTop=");
                sb.append(this.k.getTop());
                sb.append(",yToBeOn=");
                sb.append(abs);
                LogUtil.a("onPullProgress", sb.toString());
                this.k.offsetTopAndBottom((int) top);
                LogUtil.a("onPullProgress", "mTopLayout.getTop=" + this.k.getTop());
                if (this.L) {
                    int abs2 = (int) ((((this.I - this.H) * Math.abs(f2)) + this.H) - ((float) this.l.getLeft()));
                    this.l.offsetLeftAndRight(abs2);
                    this.m.offsetLeftAndRight(abs2);
                    this.p.offsetLeftAndRight(abs2);
                    this.q.offsetLeftAndRight(abs2);
                    this.r.offsetLeftAndRight(abs2);
                }
                float abs3 = (float) (Math.abs(f2) <= 0.4f ? (double) (1.0f - (Math.abs(f2) / 0.4f)) : 0.0d);
                this.j.setAlpha(abs3);
                if (this.F) {
                    this.g.setAlpha(abs3);
                }
                float f4 = 1.0f - abs3;
                this.p.setAlpha(f4);
                this.q.setAlpha(f4);
                this.s.setAlpha(abs3);
                this.r.setAlpha(f4);
                if (this.W != null && this.W.length >= 7) {
                    this.A.setAlpha((((((float) this.W[6]) - ((float) this.W[5])) / 100.0f) * Math.abs(f2)) + (((float) this.W[5]) / 100.0f));
                }
            }
        }
    }

    private void d() {
        int left;
        if (this.M) {
            float f2 = this.J;
            this.i.getTop();
            int top = (int) (this.N - ((float) this.k.getTop()));
            if (top != 0) {
                this.k.offsetTopAndBottom(top);
            }
        }
        if (this.L && (left = (int) (this.H - ((float) this.l.getLeft()))) != 0) {
            this.l.offsetLeftAndRight(left);
            this.m.offsetLeftAndRight(left);
            this.p.offsetLeftAndRight(left);
            this.q.offsetLeftAndRight(left);
            this.r.offsetLeftAndRight(left);
        }
        this.j.setAlpha(1.0f);
        if (this.F) {
            this.g.setAlpha(1.0f);
        }
        this.p.setAlpha(0.0f);
        this.q.setAlpha(0.0f);
        this.s.setAlpha(1.0f);
        this.r.setAlpha(0.0f);
    }

    public void onPullEnd(PullHeaderLayout pullHeaderLayout, int i2, int i3) {
        this.mPullState = 0;
    }

    public void onBounceEnd() {
        this.i.offsetTopAndBottom((int) (this.J - ((float) this.i.getTop())));
    }

    public void showOfflineView() {
        this.S = 1;
        this.S = 2;
        this.mNoLoginOfflineView.setVisibility(0);
        this.mHeaderContentView.setVisibility(4);
        ((TextView) this.mNoLoginOfflineView.findViewById(R.id.no_login_tv)).setText(R.string.network_disable);
        ((ImageView) this.mNoLoginOfflineView.findViewById(R.id.no_login_icon)).setImageResource(R.drawable.network_disconnected_icon);
        this.mNoLoginOfflineView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WeatherHeaderView.this.getContext().startActivity(new Intent("android.settings.SETTINGS"));
            }
        });
    }

    public void showLoginView() {
        this.S = 2;
        this.mNoLoginOfflineView.setVisibility(0);
        this.mHeaderContentView.setVisibility(4);
        ((TextView) this.mNoLoginOfflineView.findViewById(R.id.no_login_tv)).setText(R.string.login_at_once);
        ((ImageView) this.mNoLoginOfflineView.findViewById(R.id.no_login_icon)).setImageResource(R.drawable.user_default);
        this.mNoLoginOfflineView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LoginApi.a().a(WeatherHeaderView.this.getContext(), 1, (LoginApi.LoginCallback) null);
            }
        });
    }

    public void showContentView() {
        this.S = 0;
        this.mNoLoginOfflineView.setVisibility(8);
        this.mHeaderContentView.setVisibility(0);
    }

    public void scrollToBottom() {
        if (getParent() instanceof PullHeaderLayout) {
            ((PullHeaderLayout) getParent()).scrollToBottom();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002e, code lost:
        r2 = com.xiaomi.smarthome.library.common.widget.nestscroll.internal.WeatherHeaderUtil.a(r0.x, r0.s);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateViews() {
        /*
            r10 = this;
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            boolean r0 = r0.D()
            r1 = 0
            if (r0 != 0) goto L_0x001c
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            boolean r0 = r0.q()
            if (r0 != 0) goto L_0x0016
            goto L_0x001c
        L_0x0016:
            android.widget.ImageView r0 = r10.d
            r0.setVisibility(r1)
            goto L_0x0023
        L_0x001c:
            android.widget.ImageView r0 = r10.d
            r2 = 8
            r0.setVisibility(r2)
        L_0x0023:
            com.xiaomi.smarthome.miio.areainfo.AreaInfoManager r0 = com.xiaomi.smarthome.miio.areainfo.AreaInfoManager.a()
            com.xiaomi.smarthome.framework.api.model.AreaPropInfo r0 = r0.H()
            if (r0 != 0) goto L_0x002e
            return
        L_0x002e:
            java.lang.String r2 = r0.x
            java.lang.String r3 = r0.s
            int[] r2 = com.xiaomi.smarthome.library.common.widget.nestscroll.internal.WeatherHeaderUtil.a((java.lang.String) r2, (java.lang.String) r3)
            if (r2 != 0) goto L_0x0039
            return
        L_0x0039:
            com.xiaomi.smarthome.library.common.widget.nestscroll.internal.WeatherHeaderView$16 r3 = new com.xiaomi.smarthome.library.common.widget.nestscroll.internal.WeatherHeaderView$16
            r3.<init>(r2)
            r4 = 1000(0x3e8, double:4.94E-321)
            r10.postDelayed(r3, r4)
            android.widget.TextView r3 = r10.l
            java.lang.String r6 = r0.E
            r3.setText(r6)
            android.widget.TextView r3 = r10.n
            java.lang.String r6 = r0.x
            r3.setText(r6)
            android.widget.TextView r3 = r10.p
            java.lang.String r6 = r0.x
            r3.setText(r6)
            android.widget.TextView r3 = r10.q
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            android.content.res.Resources r7 = r10.getResources()
            r8 = 4
            r9 = r2[r8]
            java.lang.String r7 = r7.getString(r9)
            r6.append(r7)
            java.lang.String r7 = " "
            r6.append(r7)
            java.lang.String r7 = r0.s
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r3.setText(r6)
            android.widget.TextView r3 = r10.u
            r6 = r2[r8]
            r3.setText(r6)
            android.widget.TextView r3 = r10.o
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            com.xiaomi.smarthome.miio.areainfo.AreaInfoManager r7 = com.xiaomi.smarthome.miio.areainfo.AreaInfoManager.a()
            java.lang.String r7 = r7.k()
            r6.append(r7)
            java.lang.String r7 = " "
            r6.append(r7)
            com.xiaomi.smarthome.miio.areainfo.AreaInfoManager r7 = com.xiaomi.smarthome.miio.areainfo.AreaInfoManager.a()
            java.lang.String r7 = r7.l()
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r3.setText(r6)
            android.widget.TextView r3 = r10.r
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            com.xiaomi.smarthome.miio.areainfo.AreaInfoManager r7 = com.xiaomi.smarthome.miio.areainfo.AreaInfoManager.a()
            java.lang.String r7 = r7.k()
            r6.append(r7)
            java.lang.String r7 = " "
            r6.append(r7)
            com.xiaomi.smarthome.miio.areainfo.AreaInfoManager r7 = com.xiaomi.smarthome.miio.areainfo.AreaInfoManager.a()
            java.lang.String r7 = r7.l()
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r3.setText(r6)
            android.widget.TextView r3 = r10.t
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = r0.q
            r6.append(r7)
            java.lang.String r7 = "/"
            r6.append(r7)
            java.lang.String r7 = r0.r
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r3.setText(r6)
            android.widget.TextView r3 = r10.v
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = r0.u
            r6.append(r7)
            java.lang.String r7 = "/"
            r6.append(r7)
            java.lang.String r7 = r0.v
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r3.setText(r6)
            android.widget.TextView r3 = r10.w
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = r0.C
            r6.append(r7)
            java.lang.String r7 = "/"
            r6.append(r7)
            java.lang.String r0 = r0.D
            r6.append(r0)
            java.lang.String r0 = r6.toString()
            r3.setText(r0)
            com.xiaomi.smarthome.library.common.widget.nestscroll.internal.WeatherHeaderView$17 r0 = new com.xiaomi.smarthome.library.common.widget.nestscroll.internal.WeatherHeaderView$17
            r0.<init>()
            r10.postDelayed(r0, r4)
            r0 = 1
            r10.E = r0
            boolean r3 = r10.L
            if (r3 != 0) goto L_0x0145
            com.xiaomi.smarthome.library.common.widget.nestscroll.internal.WeatherHeaderView$18 r3 = new com.xiaomi.smarthome.library.common.widget.nestscroll.internal.WeatherHeaderView$18
            r3.<init>()
            r10.post(r3)
        L_0x0145:
            android.view.View r3 = r10.b
            r1 = r2[r1]
            r3.setBackgroundColor(r1)
            android.widget.ImageView r1 = r10.A
            r0 = r2[r0]
            r1.setImageResource(r0)
            android.widget.ImageView r0 = r10.A
            r1 = 5
            r1 = r2[r1]
            float r1 = (float) r1
            r3 = 1120403456(0x42c80000, float:100.0)
            float r1 = r1 / r3
            r0.setAlpha(r1)
            android.widget.ImageView r0 = r10.z
            r1 = 2
            r1 = r2[r1]
            r0.setImageResource(r1)
            android.widget.ImageView r0 = r10.z
            r1 = 3
            r1 = r2[r1]
            float r1 = (float) r1
            float r1 = r1 / r3
            r0.setAlpha(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.widget.nestscroll.internal.WeatherHeaderView.updateViews():void");
    }

    public void refreshTitleBar(boolean z2) {
        if (!this.ac) {
            if (Math.abs(this.mProgress) < getChangeTitleBarProgress()) {
                if (z2 || !this.F) {
                    removeCallbacks(this.aa);
                    post(this.aa);
                }
            } else if (z2 || this.F) {
                removeCallbacks(this.ab);
                post(this.ab);
            }
        }
    }

    public void changeTitleBarBlack() {
        TitleBarUtil.a((Activity) getContext());
        this.ac = true;
    }

    public void resetTitleBar() {
        this.ac = false;
        refreshTitleBar(true);
    }

    public void setTitleText(String str) {
        this.g.setText(str);
    }

    public static void setIsSupportWeather(boolean z2) {
        mIsSupportWeather = true;
    }

    public void setDrawerDotVisibility(int i2) {
        if (this.e == null) {
            this.e = (ImageView) findViewById(R.id.drawer_dot);
        }
        if (this.e != null) {
            this.e.setVisibility(i2);
        }
    }
}
