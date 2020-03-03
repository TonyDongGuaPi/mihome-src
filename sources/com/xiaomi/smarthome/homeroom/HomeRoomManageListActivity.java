package com.xiaomi.smarthome.homeroom;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
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
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.HomeRoomManageListActivity;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.homeroom.view.RoomListAdapter;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.AnimateLinearLayout;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.List;
import miuipub.view.EditActionMode;

public class HomeRoomManageListActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private View f18102a;
    private TextView b;
    private View c;
    /* access modifiers changed from: private */
    public RoomListAdapter d;
    private View e;
    private TextView f;
    private BroadcastReceiver g = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String stringExtra = intent.getStringExtra(HomeManager.v);
            if (TextUtils.equals(stringExtra, HomeManager.y) || TextUtils.equals(stringExtra, HomeManager.z)) {
                HomeRoomManageListActivity.this.a();
                return;
            }
            HomeRoomManageListActivity.this.mHandler.removeCallbacksAndMessages((Object) null);
            HomeRoomManageListActivity.this.mHandler.postDelayed(new Runnable() {
                public final void run() {
                    HomeRoomManageListActivity.AnonymousClass1.this.a();
                }
            }, 1000);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a() {
            HomeRoomManageListActivity.this.a();
        }
    };
    /* access modifiers changed from: private */
    public String h = null;
    private long i;
    /* access modifiers changed from: private */
    public View j;
    /* access modifiers changed from: private */
    public AnimateLinearLayout k;
    private ImageView l;
    private ImageView m;
    protected RecyclerViewDragDropManager mDragDropManager;
    protected RecyclerViewExpandableItemManager mExpandableItemManager;
    protected LinearLayoutManager mLayoutManager;
    protected RecyclerView.Adapter mRecyclerAdapter;
    protected RecyclerView mRecyclerView;
    protected RecyclerViewTouchActionGuardManager mTouchActionGuardManager;
    protected TextView mTxtEditTitle;
    private Button n;
    private boolean o = false;
    private boolean p = false;

    public static void startActivity(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            Intent intent = new Intent(context, HomeRoomManageListActivity.class);
            intent.putExtra("home_id", str);
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            context.startActivity(intent);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_manage_home_room_list);
        this.h = getIntent().getStringExtra("home_id");
        if (TextUtils.isEmpty(this.h)) {
            this.h = HomeManager.a().l();
        }
        LocalBroadcastManager.getInstance(this).registerReceiver(this.g, new IntentFilter(HomeManager.t));
        b();
        a();
        HomeManager.a().b();
    }

    /* access modifiers changed from: private */
    public void a() {
        this.d.b(HomeManager.a().j(this.h));
        if (this.d.e() == 0) {
            this.e.setVisibility(0);
            this.f.setText(Html.fromHtml(getResources().getString(R.string.empty_room_hint)));
            this.f.setOnClickListener(this);
            this.mRecyclerView.setVisibility(8);
            this.b.setEnabled(false);
            return;
        }
        this.e.setVisibility(8);
        this.mRecyclerView.setVisibility(0);
        this.b.setEnabled(true);
    }

    private void b() {
        this.b = (TextView) findViewById(R.id.module_a_3_right_text_btn);
        this.b.setText(R.string.menu_edit_sort);
        this.b.setOnClickListener(this);
        this.c = findViewById(R.id.add_room);
        this.c.setOnClickListener(this);
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.home_manager);
        this.f18102a = findViewById(R.id.module_a_3_return_btn);
        this.f18102a.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomeRoomManageListActivity.this.finish();
            }
        });
        this.e = findViewById(R.id.empty);
        this.f = (TextView) findViewById(R.id.no_login_tv);
        ((ImageView) findViewById(R.id.iv_empty_image)).setImageResource(R.drawable.emptypage_icon_empty_nor);
        d();
        c();
    }

    private void c() {
        if (HomeManager.a().o() && HomeManager.a().f().size() > 1) {
            this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    new MLAlertDialog.Builder(HomeRoomManageListActivity.this).b((int) R.string.setting_after, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).a((int) R.string.setting_now, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            HomeRoomRecommendActivity.startActivity(HomeRoomManageListActivity.this.getContext(), HomeRoomManageListActivity.this.h);
                        }
                    }).b((int) R.string.message_multihome_guide).d();
                }
            }, 350);
            SharedPreferences.Editor edit = SHApplication.getAppContext().getSharedPreferences(HomeManager.f1548a, 0).edit();
            edit.remove(HomeManager.H + HomeManager.a().l()).commit();
        }
    }

    private void d() {
        this.mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                View currentFocus;
                super.onScrollStateChanged(recyclerView, i);
                if (i != 0 && HomeRoomManageListActivity.this.isValid() && (currentFocus = HomeRoomManageListActivity.this.getCurrentFocus()) != null) {
                    currentFocus.clearFocus();
                }
            }
        });
        this.mExpandableItemManager = new RecyclerViewExpandableItemManager((Parcelable) null);
        this.mDragDropManager = new RecyclerViewDragDropManager();
        this.d = new RoomListAdapter(this);
        this.d.a((RoomListAdapter.EditModeListener) new RoomListAdapter.EditModeListener() {
            public void a() {
                HomeRoomManageListActivity.this.enterEditMode();
            }

            public void a(int i) {
                HomeRoomManageListActivity.this.a(i);
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
        this.mRecyclerView.setBackgroundColor(getResources().getColor(R.color.list_bg));
        this.mExpandableItemManager.a(this.mRecyclerView);
        this.mExpandableItemManager.d();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.d != null) {
            a();
        }
        STAT.c.k(0);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.g);
    }

    public void onBackPressed() {
        if (this.o) {
            exitEditMode();
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
        if (!this.o && this.d.l() >= 1) {
            try {
                this.c.setVisibility(8);
                if (this.j == null) {
                    this.j = ((ViewStub) findViewById(R.id.title_bar_choose_device_stub)).inflate();
                    this.l = (ImageView) this.j.findViewById(EditActionMode.b);
                    this.l.setOnClickListener(this);
                    this.m = (ImageView) this.j.findViewById(EditActionMode.f3057a);
                    this.m.setOnClickListener(this);
                    this.mTxtEditTitle = (TextView) this.j.findViewById(R.id.module_a_4_return_more_title);
                    TitleBarUtil.a(TitleBarUtil.a(), findViewById(R.id.title_bar_choose_device));
                }
                if (this.k == null) {
                    this.k = (AnimateLinearLayout) ((ViewStub) findViewById(R.id.edit_action_bar_stub)).inflate();
                    this.k.findViewById(R.id.btn_edit_sort).setVisibility(8);
                    this.n = (Button) this.k.findViewById(R.id.btn_edit_delete);
                    this.n.setOnClickListener(this);
                }
                this.j.setVisibility(0);
                this.k.setTranslationY(0.0f);
                this.k.setVisibility(0);
                this.k.doMoveInAnim(0, AnimateLinearLayout.calcStepDelay(this.k.getChildCount()));
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.j, View.Y, new float[]{-getResources().getDimension(R.dimen.titlebar_height), 0.0f});
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setDuration(200);
                animatorSet.play(ofFloat);
                animatorSet.start();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.o = true;
            this.d.f();
        }
    }

    public void exitEditMode() {
        if (this.o) {
            this.c.setVisibility(0);
            ViewGroup viewGroup = (ViewGroup) this.k.getParent();
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.j, View.Y, new float[]{0.0f, (float) (-this.j.getHeight())});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.k, View.Y, new float[]{(float) (viewGroup.getHeight() - this.k.getHeight()), (float) viewGroup.getHeight()});
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(200);
            if (!this.p) {
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
                    HomeRoomManageListActivity.this.j.clearAnimation();
                    HomeRoomManageListActivity.this.k.clearAnimation();
                    HomeRoomManageListActivity.this.j.setVisibility(8);
                    HomeRoomManageListActivity.this.k.setVisibility(8);
                }
            });
            this.o = false;
            this.p = false;
            this.d.g();
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
        if (i2 >= this.d.l()) {
            this.m.setImageResource(R.drawable.un_select_selector);
        } else {
            this.m.setImageResource(R.drawable.all_select_selector);
        }
    }

    private void e() {
        new MLAlertDialog.Builder(this).b((CharSequence) getString(R.string.room_remove_confirm)).a((int) R.string.tag_remove, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ArrayList arrayList = new ArrayList(HomeRoomManageListActivity.this.d.k());
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
                HomeRoomManageListActivity.this.exitEditMode();
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
        if (view == this.l) {
            exitEditMode();
            return;
        }
        boolean z = false;
        if (view == this.m) {
            if (this.p) {
                this.d.a(true);
                exitEditMode();
                return;
            }
            RoomListAdapter roomListAdapter = this.d;
            if (this.d.k().size() < this.d.l()) {
                z = true;
            }
            roomListAdapter.b(z);
            this.d.notifyDataSetChanged();
        } else if (view == this.b) {
            this.p = true;
            enterEditMode();
            this.d.h();
            if (this.k != null) {
                this.k.setVisibility(8);
            }
            this.m.setImageResource(R.drawable.title_right_tick_drawable);
            this.mTxtEditTitle.setText(R.string.menu_edit_sort);
            this.l.setImageResource(R.drawable.title_cancel_selector);
            ViewGroup viewGroup = (ViewGroup) this.k.getParent();
            ObjectAnimator.ofFloat(this.k, View.Y, new float[]{(float) (viewGroup.getHeight() - this.k.getHeight()), (float) viewGroup.getHeight()}).start();
            this.i = STAT.c.l(0);
            STAT.d.aI();
        } else if (view == this.n) {
            e();
            STAT.d.U();
        } else if (view == this.c || view == this.f) {
            STAT.d.X();
            List<Room> e2 = HomeManager.a().e();
            if (e2 == null || e2.size() < 50) {
                HomeRoomRecommendActivity.startActivity(getContext(), this.h);
            } else {
                ToastUtil.a((int) R.string.exceed_room_max_count);
            }
        }
    }
}
