package com.xiaomi.smarthome.homeroom;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.exoplayer2.C;
import com.h6ah4i.android.widget.advrecyclerview.animator.SwipeDismissItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.h6ah4i.android.widget.advrecyclerview.touchguard.RecyclerViewTouchActionGuardManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeConfig;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.homeroom.view.NavigationManagerAdapter;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.AnimateLinearLayout;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.List;
import miuipub.view.EditActionMode;

public class NavigationManagerActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private TextView f18172a;
    private ImageView b;
    private View c;
    /* access modifiers changed from: private */
    public NavigationManagerAdapter d;
    private View e;
    private TextView f;
    private TextView g;
    private String h = null;
    private long i;
    private BroadcastReceiver j = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            NavigationManagerActivity.this.d();
        }
    };
    /* access modifiers changed from: private */
    public View k;
    /* access modifiers changed from: private */
    public AnimateLinearLayout l;
    private ImageView m;
    protected RecyclerViewDragDropManager mDragDropManager;
    protected RecyclerViewExpandableItemManager mExpandableItemManager;
    protected LinearLayoutManager mLayoutManager;
    protected RecyclerView.Adapter mRecyclerAdapter;
    protected RecyclerView mRecyclerView;
    protected RecyclerViewTouchActionGuardManager mTouchActionGuardManager;
    protected TextView mTxtEditTitle;
    private ImageView n;
    private Button o;
    private Button p;
    private boolean q = false;
    private boolean r = false;

    public static void startActivity(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            Intent intent = new Intent(context, NavigationManagerActivity.class);
            intent.putExtra("home_id", str);
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            context.startActivity(intent);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_navigation_manage);
        this.h = getIntent().getStringExtra("home_id");
        if (TextUtils.isEmpty(this.h)) {
            this.h = HomeManager.a().l();
        }
        LocalBroadcastManager.getInstance(this).registerReceiver(this.j, new IntentFilter(HomeManager.t));
        a();
        HomeManager.a().b();
    }

    private void a() {
        this.f18172a = (TextView) findViewById(R.id.module_a_3_right_text_btn);
        this.f18172a.setText(R.string.complete);
        this.f18172a.setVisibility(8);
        this.f18172a.setOnClickListener(this);
        this.b = (ImageView) findViewById(R.id.module_a_3_right_btn);
        this.b.setVisibility(0);
        this.b.setOnClickListener(this);
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.navigation_manager);
        this.c = findViewById(R.id.module_a_3_return_btn);
        this.c.setOnClickListener(this);
        this.e = findViewById(R.id.empty);
        this.f = (TextView) findViewById(R.id.no_login_tv);
        ((ImageView) findViewById(R.id.iv_empty_image)).setImageResource(R.drawable.emptypage_icon_empty_nor);
        c();
    }

    private void b() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.navigation_manager_activity_header, this.mRecyclerView, false);
        this.g = (TextView) inflate.findViewById(R.id.selected_category_tv);
        inflate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                NavigationManagerActivity.this.startActivity(new Intent(NavigationManagerActivity.this, CategoryNavigationActivity.class));
            }
        });
        this.d.a(inflate);
    }

    private void c() {
        this.mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                View currentFocus;
                super.onScrollStateChanged(recyclerView, i);
                if (i != 0 && NavigationManagerActivity.this.isValid() && (currentFocus = NavigationManagerActivity.this.getCurrentFocus()) != null) {
                    currentFocus.clearFocus();
                }
            }
        });
        this.mExpandableItemManager = new RecyclerViewExpandableItemManager((Parcelable) null);
        this.mDragDropManager = new RecyclerViewDragDropManager();
        this.d = new NavigationManagerAdapter(this, HomeManager.a().j(this.h));
        this.d.a((NavigationManagerAdapter.EditModeListener) new NavigationManagerAdapter.EditModeListener() {
            public void a() {
                NavigationManagerActivity.this.enterEditMode();
            }

            public void a(int i) {
                NavigationManagerActivity.this.a(i);
            }
        });
        this.mRecyclerAdapter = this.mExpandableItemManager.a((RecyclerView.Adapter) this.d);
        this.mRecyclerAdapter = this.mDragDropManager.a(this.mRecyclerAdapter);
        this.mLayoutManager = new LinearLayoutManager(this);
        SwipeDismissItemAnimator swipeDismissItemAnimator = new SwipeDismissItemAnimator();
        swipeDismissItemAnimator.setSupportsChangeAnimations(false);
        this.mRecyclerView.setLayoutManager(this.mLayoutManager);
        this.mRecyclerView.setAdapter(this.mRecyclerAdapter);
        this.mRecyclerView.setItemAnimator(swipeDismissItemAnimator);
        this.mRecyclerView.setHasFixedSize(false);
        this.mTouchActionGuardManager = new RecyclerViewTouchActionGuardManager();
        this.mTouchActionGuardManager.b(true);
        this.mTouchActionGuardManager.a(true);
        this.mTouchActionGuardManager.a(this.mRecyclerView);
        this.mDragDropManager.a(this.mRecyclerView);
        this.mDragDropManager.a(true);
        this.mDragDropManager.a((NinePatchDrawable) getContext().getResources().getDrawable(R.drawable.std_list_item_drag_shadow));
        this.mDragDropManager.b(true);
        this.mDragDropManager.a((int) (((float) ViewConfiguration.getLongPressTimeout()) * 1.5f));
        this.mDragDropManager.a((RecyclerViewDragDropManager.OnItemDragEventListener) new RecyclerViewDragDropManager.OnItemDragEventListener() {
            public void a(int i) {
            }

            public void a(int i, int i2) {
            }

            public void a(int i, int i2, boolean z) {
            }

            public void b(int i, int i2) {
            }
        });
        this.mExpandableItemManager.a(this.mRecyclerView);
        b();
    }

    /* access modifiers changed from: private */
    public void d() {
        this.d.a(HomeManager.a().j(this.h));
        if (this.d.c() != 0 || MultiHomeDeviceManager.a().d(this.h).size() >= 1) {
            this.e.setVisibility(8);
            this.mRecyclerView.setVisibility(0);
            return;
        }
        this.e.setVisibility(0);
        this.f.setText(Html.fromHtml(getResources().getString(R.string.empty_room_hint)));
        this.f.setOnClickListener(this);
        this.mRecyclerView.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.d != null) {
            d();
        }
        this.g.setText(SharePrefsManager.b((Context) this, SmartHomeConfig.i, SmartHomeConfig.t, true) ? R.string.open : R.string.close);
        this.mExpandableItemManager.d();
        STAT.c.k(0);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.j);
    }

    public void onBackPressed() {
        if (this.q) {
            exitEditMode();
        } else if (this.d.i()) {
            this.d.a(false);
            this.b.setVisibility(0);
            this.f18172a.setVisibility(8);
            this.d.a(HomeManager.a().j(this.h));
            this.d.g();
        } else {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        STAT.c.k(this.mEnterTime);
    }

    public void enterEditMode() {
        if (!this.q && this.d.k() >= 1) {
            try {
                if (this.k == null) {
                    this.k = ((ViewStub) findViewById(R.id.title_bar_choose_device_stub)).inflate();
                    this.m = (ImageView) this.k.findViewById(EditActionMode.f3057a);
                    this.m.setOnClickListener(this);
                    this.n = (ImageView) this.k.findViewById(EditActionMode.b);
                    this.n.setOnClickListener(this);
                    this.mTxtEditTitle = (TextView) this.k.findViewById(R.id.module_a_4_return_more_title);
                    TitleBarUtil.a(TitleBarUtil.a(), findViewById(R.id.title_bar_choose_device));
                }
                if (this.l == null) {
                    this.l = (AnimateLinearLayout) ((ViewStub) findViewById(R.id.edit_action_bar_stub)).inflate();
                    this.o = (Button) this.l.findViewById(R.id.btn_edit_sort);
                    this.o.setOnClickListener(this);
                    this.p = (Button) this.l.findViewById(R.id.btn_edit_delete);
                    this.p.setOnClickListener(this);
                }
                this.mTxtEditTitle.setText(R.string.title_choose_room);
                this.k.setVisibility(0);
                this.l.setTranslationY(0.0f);
                this.l.setVisibility(0);
                this.l.doMoveInAnim(0, AnimateLinearLayout.calcStepDelay(this.l.getChildCount()));
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.k, View.Y, new float[]{-getResources().getDimension(R.dimen.titlebar_height), 0.0f});
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setDuration(200);
                animatorSet.play(ofFloat);
                animatorSet.start();
                this.mRecyclerView.setPadding(0, 0, 0, DisplayUtils.a(100.0f));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.q = true;
            this.d.d();
        }
    }

    public void exitEditMode() {
        if (this.q) {
            ViewGroup viewGroup = (ViewGroup) this.l.getParent();
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.k, View.Y, new float[]{0.0f, (float) (-this.k.getHeight())});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.l, View.Y, new float[]{(float) (viewGroup.getHeight() - this.l.getHeight()), (float) viewGroup.getHeight()});
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(200);
            if (!this.r) {
                animatorSet.play(ofFloat).with(ofFloat2);
            } else {
                animatorSet.play(ofFloat);
                this.d.a(false);
                STAT.c.l(this.i);
            }
            animatorSet.start();
            animatorSet.addListener(new Animator.AnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    NavigationManagerActivity.this.k.clearAnimation();
                    NavigationManagerActivity.this.l.clearAnimation();
                    NavigationManagerActivity.this.k.setVisibility(8);
                    NavigationManagerActivity.this.l.setVisibility(8);
                }
            });
            this.q = false;
            this.r = false;
            this.d.e();
            this.mRecyclerView.setPadding(0, 0, 0, 0);
        }
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        if (i2 > 0) {
            try {
                this.mTxtEditTitle.setText(getResources().getQuantityString(R.plurals.edit_choosed_room, i2, new Object[]{Integer.valueOf(i2)}));
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        } else {
            this.mTxtEditTitle.setText(R.string.title_choose_room);
        }
        this.mTxtEditTitle.setTypeface((Typeface) null, 0);
        if (i2 >= this.d.k()) {
            this.n.setImageResource(R.drawable.un_select_selector);
        } else {
            this.n.setImageResource(R.drawable.all_select_selector);
        }
    }

    private void e() {
        new MLAlertDialog.Builder(this).b((CharSequence) getString(R.string.room_remove_confirm)).a((int) R.string.tag_remove, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ArrayList arrayList = new ArrayList(NavigationManagerActivity.this.d.j());
                if (arrayList.size() == 1) {
                    HomeManager.a().a(HomeManager.a().i((String) arrayList.get(0)), (HomeManager.IHomeOperationCallback) new HomeManager.IHomeOperationCallback() {
                        public void a() {
                        }

                        public void a(int i, Error error) {
                            ToastUtil.a((int) R.string.room_delete_failed);
                        }
                    });
                } else if (arrayList.size() > 1) {
                    HomeManager.a().a((List<String>) arrayList, (HomeManager.IHomeOperationCallback) new HomeManager.IHomeOperationCallback() {
                        public void a() {
                        }

                        public void a(int i, Error error) {
                            ToastUtil.a((int) R.string.room_delete_failed);
                        }
                    });
                }
                NavigationManagerActivity.this.exitEditMode();
                STAT.d.W();
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                STAT.d.V();
            }
        }).a(getResources().getColor(R.color.std_dialog_button_red), -1).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                STAT.d.V();
            }
        }).d();
    }

    public void onClick(View view) {
        if (view == this.m) {
            exitEditMode();
            return;
        }
        boolean z = false;
        if (view == this.n) {
            if (this.r) {
                this.d.a(true);
                exitEditMode();
                return;
            }
            NavigationManagerAdapter navigationManagerAdapter = this.d;
            if (this.d.j().size() < this.d.k()) {
                z = true;
            }
            navigationManagerAdapter.b(z);
            this.d.notifyDataSetChanged();
        } else if (view == this.o) {
            this.r = true;
            this.d.f();
            this.n.setImageResource(R.drawable.title_right_tick_drawable);
            this.mTxtEditTitle.setText(R.string.menu_edit_sort);
            this.m.setImageResource(R.drawable.title_cancel_selector);
            ViewGroup viewGroup = (ViewGroup) this.l.getParent();
            ObjectAnimator.ofFloat(this.l, View.Y, new float[]{(float) (viewGroup.getHeight() - this.l.getHeight()), (float) viewGroup.getHeight()}).start();
            this.i = STAT.c.l(0);
        } else if (view == this.p) {
            e();
            STAT.d.U();
        } else if (view == this.f) {
            STAT.d.X();
            List<Room> e2 = HomeManager.a().e();
            if (e2 == null || e2.size() < 50) {
                HomeRoomRecommendActivity.startActivity(getContext(), this.h);
            } else {
                ToastUtil.a((int) R.string.exceed_room_max_count);
            }
        } else if (view == this.c) {
            finish();
        } else if (view == this.b || view == this.f18172a) {
            f();
        }
    }

    private void f() {
        if (this.b.getVisibility() == 0) {
            this.b.setVisibility(8);
            this.f18172a.setVisibility(0);
            this.d.f();
            return;
        }
        this.b.setVisibility(0);
        this.f18172a.setVisibility(8);
        this.d.g();
        this.d.a(true);
    }
}
