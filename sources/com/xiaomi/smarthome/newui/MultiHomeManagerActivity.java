package com.xiaomi.smarthome.newui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.h6ah4i.android.widget.advrecyclerview.animator.SwipeDismissItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.h6ah4i.android.widget.advrecyclerview.touchguard.RecyclerViewTouchActionGuardManager;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.HomeMemberManager;
import com.xiaomi.smarthome.homeroom.HomeRoomCreatHomeActivity;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.HomeInviteInfo;
import com.xiaomi.smarthome.homeroom.view.HomeListAdapter;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.AnimateLinearLayout;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.stat.STAT;
import java.lang.ref.WeakReference;
import java.util.List;
import miuipub.view.EditActionMode;

public class MultiHomeManagerActivity extends BaseActivity implements View.OnClickListener {
    public static int MAX_HOME_SIZE = 11;
    private static WeakReference<MultiHomeManagerActivity> n;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public View f20331a;
    private ImageView b;
    /* access modifiers changed from: private */
    public AnimateLinearLayout c;
    private Button d;
    private ImageView e;
    /* access modifiers changed from: private */
    public HomeListAdapter f;
    private boolean g = false;
    private boolean h = false;
    private View i;
    private View j;
    /* access modifiers changed from: private */
    public XQProgressDialog k;
    private String l;
    private BroadcastReceiver m = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            MultiHomeManagerActivity.this.contrlProgreassDialog(false, true, HomeMemberManager.a().a(HomeInviteInfo.f18314a).size() > 0 ? 3000 : 500);
        }
    };
    protected RecyclerViewDragDropManager mDragDropManager;
    protected RecyclerViewExpandableItemManager mExpandableItemManager;
    protected LinearLayoutManager mLayoutManager;
    protected RecyclerView.Adapter mRecyclerAdapter;
    protected RecyclerView mRecyclerView;
    protected RecyclerViewTouchActionGuardManager mTouchActionGuardManager;
    protected TextView mTxtEditTitle;
    boolean onPaused = false;

    /* access modifiers changed from: private */
    public void a(int i2) {
    }

    public String getLatestAcceptHomeId() {
        return this.l;
    }

    public void setLatestAcceptHomeId(String str) {
        this.l = str;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_multi_home_manager);
        b();
        a();
        LocalBroadcastManager.getInstance(this).registerReceiver(this.m, new IntentFilter(HomeManager.t));
    }

    private void a() {
        HomeMemberManager.a().a((AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(Void voidR) {
                MultiHomeManagerActivity.this.f.g();
                MultiHomeManagerActivity.this.mExpandableItemManager.d();
                STAT.c.a(0, MultiHomeManagerActivity.this.f.c(), MultiHomeManagerActivity.this.f.d(), MultiHomeManagerActivity.this.f.e(), MultiHomeManagerActivity.this.getIntent().getIntExtra("from", 0));
            }
        });
    }

    private void b() {
        findViewById(R.id.module_a_3_right_btn).setVisibility(8);
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.home_manage_tite);
        this.i = findViewById(R.id.module_a_3_return_btn);
        this.i.setOnClickListener(this);
        this.j = findViewById(R.id.addhome);
        this.j.setOnClickListener(this);
        this.mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        c();
    }

    private void c() {
        this.mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                View currentFocus;
                super.onScrollStateChanged(recyclerView, i);
                if (i != 0 && MultiHomeManagerActivity.this.isValid() && (currentFocus = MultiHomeManagerActivity.this.getCurrentFocus()) != null) {
                    currentFocus.clearFocus();
                }
            }
        });
        this.mRecyclerView.addItemDecoration(new FooterDecoration());
        this.mExpandableItemManager = new RecyclerViewExpandableItemManager((Parcelable) null);
        this.mDragDropManager = new RecyclerViewDragDropManager();
        this.f = new HomeListAdapter(this);
        this.f.a((HomeListAdapter.EditModeListener) new HomeListAdapter.EditModeListener() {
            public void a() {
                MultiHomeManagerActivity.this.enterEditMode();
            }

            public void a(int i) {
                MultiHomeManagerActivity.this.a(i);
            }
        });
        this.mRecyclerAdapter = this.mExpandableItemManager.a((RecyclerView.Adapter) this.f);
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
        this.mRecyclerView.setBackgroundColor(getResources().getColor(R.color.list_bg));
        this.mExpandableItemManager.a(this.mRecyclerView);
        this.mExpandableItemManager.d();
        this.mRecyclerView.setItemAnimator((RecyclerView.ItemAnimator) null);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.onPaused) {
            a();
            this.onPaused = false;
            return;
        }
        this.f.g();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.onPaused = true;
        STAT.c.a(this.mEnterTime, 0, 0, 0, 0);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.m);
    }

    public void showProgressDialog(String str) {
        if (TextUtils.isEmpty(str)) {
            str = getResources().getString(R.string.loading_share_info);
        }
        this.k = new XQProgressDialog(this);
        this.k.setCancelable(true);
        this.k.setMessage(str);
        this.k.show();
    }

    public void contrlProgreassDialog(boolean z, boolean z2, int i2) {
        if (isValid() && !z) {
            if (this.k != null) {
                getWindow().getDecorView().postDelayed(new Runnable() {
                    public void run() {
                        if (MultiHomeManagerActivity.this.k != null) {
                            MultiHomeManagerActivity.this.k.dismiss();
                        }
                        MultiHomeManagerActivity.this.f.g();
                        MultiHomeManagerActivity.this.d();
                    }
                }, (long) i2);
            }
            if (!z2) {
                ToastUtil.a((int) R.string.home_set_failed);
            }
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        final Home j2 = HomeManager.a().j(getLatestAcceptHomeId());
        setLatestAcceptHomeId("");
        if (j2 != null) {
            new MLAlertDialog.Builder(this).b((CharSequence) String.format(getString(R.string.home_share_response_success1), new Object[]{j2.k()})).a((int) R.string.view_now, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    MultiHomeManagerActivity.this.showProgressDialog("");
                    HomeManager.a().a(j2.j(), (AsyncCallback) new AsyncCallback() {
                        public void onSuccess(Object obj) {
                            if (MultiHomeManagerActivity.this.isValid()) {
                                MultiHomeManagerActivity.this.contrlProgreassDialog(false, true, 0);
                                Intent intent = new Intent(MultiHomeManagerActivity.this, SmartHomeMainActivity.class);
                                intent.setFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                                MultiHomeManagerActivity.this.startActivity(intent);
                                STAT.d.a(3, 1);
                            }
                        }

                        public void onFailure(Error error) {
                            MultiHomeManagerActivity.this.contrlProgreassDialog(false, false, 0);
                            STAT.d.aK();
                        }
                    });
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).d();
        }
    }

    public void onBackPressed() {
        if (this.g) {
            exitEditMode();
        } else {
            finish();
        }
    }

    public void onClick(View view) {
        if (view == this.b) {
            exitEditMode();
            return;
        }
        if (view == this.d) {
            this.h = true;
            this.f.j();
            this.mTxtEditTitle.setText(R.string.menu_edit_sort);
            this.b.setImageResource(R.drawable.title_cancel_selector);
            ViewGroup viewGroup = (ViewGroup) this.c.getParent();
            ObjectAnimator.ofFloat(this.c, View.Y, new float[]{(float) (viewGroup.getHeight() - this.c.getHeight()), (float) viewGroup.getHeight()}).start();
        } else if (view == this.e) {
            if (this.h) {
                this.f.a(true);
            }
            exitEditMode();
        } else if (view == this.i) {
            finish();
        } else if (view == this.j) {
            STAT.d.aa();
            List<Home> f2 = HomeManager.a().f();
            if (f2 != null) {
                int i2 = 0;
                for (int i3 = 0; i3 < f2.size(); i3++) {
                    if (f2.get(i3).p()) {
                        i2++;
                    }
                }
                if (i2 >= MAX_HOME_SIZE) {
                    ToastUtil.a((CharSequence) getString(R.string.multi_home_max_home));
                    return;
                }
                startActivity(new Intent(this, HomeRoomCreatHomeActivity.class));
                n = new WeakReference<>(this);
            }
        }
    }

    public static void finishActivity() {
        if (n != null && n.get() != null) {
            ((MultiHomeManagerActivity) n.get()).finish();
        }
    }

    public void enterEditMode() {
        if (!this.g) {
            try {
                if (this.f20331a == null) {
                    this.f20331a = ((ViewStub) findViewById(R.id.title_bar_choose_device_stub)).inflate();
                    this.b = (ImageView) this.f20331a.findViewById(EditActionMode.f3057a);
                    this.b.setOnClickListener(this);
                    this.e = (ImageView) this.f20331a.findViewById(EditActionMode.b);
                    this.e.setOnClickListener(this);
                    this.e.setImageResource(R.drawable.title_right_tick_drawable);
                    this.mTxtEditTitle = (TextView) this.f20331a.findViewById(R.id.module_a_4_return_more_title);
                    TitleBarUtil.a(TitleBarUtil.a(), findViewById(R.id.title_bar_choose_device));
                }
                if (this.c == null) {
                    this.c = (AnimateLinearLayout) ((ViewStub) findViewById(R.id.edit_action_bar_stub)).inflate();
                    this.d = (Button) this.c.findViewById(R.id.btn_edit_sort);
                    this.d.setOnClickListener(this);
                    this.c.findViewById(R.id.btn_edit_delete).setVisibility(8);
                }
                this.mTxtEditTitle.setText(R.string.home_manage_tite);
                this.f20331a.setVisibility(0);
                this.c.setTranslationY(0.0f);
                this.c.setVisibility(0);
                this.c.doMoveInAnim(0, AnimateLinearLayout.calcStepDelay(this.c.getChildCount()));
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f20331a, View.Y, new float[]{-getResources().getDimension(R.dimen.titlebar_height), 0.0f});
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setDuration(200);
                animatorSet.play(ofFloat);
                animatorSet.start();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.g = true;
            this.f.h();
        }
    }

    public void exitEditMode() {
        if (this.g) {
            ViewGroup viewGroup = (ViewGroup) this.c.getParent();
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f20331a, View.Y, new float[]{0.0f, (float) (-this.f20331a.getHeight())});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.c, View.Y, new float[]{(float) (viewGroup.getHeight() - this.c.getHeight()), (float) viewGroup.getHeight()});
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(200);
            if (!this.h) {
                animatorSet.play(ofFloat).with(ofFloat2);
            } else {
                animatorSet.play(ofFloat);
                this.f.a(false);
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
                    MultiHomeManagerActivity.this.f20331a.clearAnimation();
                    MultiHomeManagerActivity.this.c.clearAnimation();
                    MultiHomeManagerActivity.this.f20331a.setVisibility(8);
                    MultiHomeManagerActivity.this.c.setVisibility(8);
                }
            });
            this.g = false;
            this.h = false;
            this.f.i();
        }
    }

    class FooterDecoration extends RecyclerView.ItemDecoration {
        FooterDecoration() {
        }

        public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
            if (recyclerView.getChildAdapterPosition(view) == MultiHomeManagerActivity.this.mRecyclerAdapter.getItemCount() - 1) {
                rect.set(0, 0, 0, DisplayUtils.a(100.0f));
            }
        }
    }
}
