package com.xiaomi.smarthome.scenenew.actiivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.h6ah4i.android.widget.advrecyclerview.animator.SwipeDismissItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;
import com.h6ah4i.android.widget.advrecyclerview.touchguard.RecyclerViewTouchActionGuardManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableSwipeableItemViewHolder;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.lite.scene.LiteSceneManager;
import com.xiaomi.smarthome.scene.SmartHomeSceneUtility;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scenenew.manager.RecommendSceneManager;
import java.util.ArrayList;
import java.util.List;

public class SmarthomeSortLiteSceneActivity extends BaseActivity {
    @BindView(2131430989)
    Button mCommitBtn;
    RecyclerView.LayoutManager mLayoutManager;
    LiteSceneRecyclerViewAdapter mLiteSceneAdapter;
    RecyclerViewDragDropManager mRecyclerViewDragDropManager;
    RecyclerView mSceneRV;
    List<SceneApi.SmartHomeScene> mSmartHomeScenes;
    RecyclerView.Adapter<LiteSceneRecyclerViewAdapter.SceneChildViewHolder> mWrapAdapter;
    @BindView(2131430990)
    Button moduleA4ReturnBtn;
    @BindView(2131430997)
    TextView moduleA4ReturnTitle;
    RecyclerViewTouchActionGuardManager recyclerViewTouchActionGuardManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_sort_lite_scene);
        ButterKnife.bind((Activity) this);
        a();
        c();
    }

    private void a() {
        this.moduleA4ReturnTitle.setText(R.string.intelligent_sort);
        this.moduleA4ReturnBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SmarthomeSortLiteSceneActivity.this.b();
            }
        });
        this.mCommitBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LiteSceneManager.j().a(SmarthomeSortLiteSceneActivity.this.mLiteSceneAdapter.a());
                LiteSceneManager.j().a((AsyncCallback) new AsyncCallback() {
                    public void onSuccess(Object obj) {
                        ToastUtil.a((int) R.string.save_success);
                        SmarthomeSortLiteSceneActivity.this.setResult(-1);
                        SmarthomeSortLiteSceneActivity.this.finish();
                    }

                    public void onFailure(Error error) {
                        SmarthomeSortLiteSceneActivity.this.setResult(0);
                        ToastUtil.a((int) R.string.save_fail);
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void b() {
        setResult(0);
        finish();
    }

    private void c() {
        this.mSceneRV = (RecyclerView) findViewById(R.id.scene_rv);
        this.recyclerViewTouchActionGuardManager = new RecyclerViewTouchActionGuardManager();
        this.recyclerViewTouchActionGuardManager.b(true);
        this.recyclerViewTouchActionGuardManager.a(true);
        this.mRecyclerViewDragDropManager = new RecyclerViewDragDropManager();
        this.mRecyclerViewDragDropManager.a((NinePatchDrawable) getContext().getResources().getDrawable(R.drawable.std_list_item_drag_shadow));
        this.mRecyclerViewDragDropManager.b(true);
        this.mRecyclerViewDragDropManager.a(true);
        this.mRecyclerViewDragDropManager.c(true);
        this.mRecyclerViewDragDropManager.a((int) (((float) ViewConfiguration.getLongPressTimeout()) * 0.5f));
        this.mLayoutManager = new LinearLayoutManager(this);
        LiteSceneRecyclerViewAdapter liteSceneRecyclerViewAdapter = new LiteSceneRecyclerViewAdapter(this);
        this.mLiteSceneAdapter = liteSceneRecyclerViewAdapter;
        this.mWrapAdapter = this.mRecyclerViewDragDropManager.a((RecyclerView.Adapter) liteSceneRecyclerViewAdapter);
        SwipeDismissItemAnimator swipeDismissItemAnimator = new SwipeDismissItemAnimator();
        swipeDismissItemAnimator.setSupportsChangeAnimations(false);
        this.mSceneRV.setLayoutManager(this.mLayoutManager);
        this.mSceneRV.setAdapter(this.mWrapAdapter);
        this.mSceneRV.setItemAnimator(swipeDismissItemAnimator);
        this.mSceneRV.setHasFixedSize(true);
        this.recyclerViewTouchActionGuardManager.a(this.mSceneRV);
        this.mRecyclerViewDragDropManager.a(this.mSceneRV);
        this.mLiteSceneAdapter.a(LiteSceneManager.j().e());
    }

    public void onBackPressed() {
        b();
    }

    public class LiteSceneRecyclerViewAdapter extends RecyclerView.Adapter<SceneChildViewHolder> implements DraggableItemAdapter<SceneChildViewHolder> {
        private static final String c = "LiteSceneRecyclerViewAdapter";

        /* renamed from: a  reason: collision with root package name */
        List<SceneApi.SmartHomeScene> f21916a = new ArrayList();
        private Context d;
        private LayoutInflater e;
        private int f = 0;

        public boolean b(int i, int i2) {
            return true;
        }

        public LiteSceneRecyclerViewAdapter(Context context) {
            this.d = context;
            this.e = LayoutInflater.from(context);
            setHasStableIds(true);
        }

        public void a(List<SceneApi.SmartHomeScene> list) {
            this.f21916a.clear();
            this.f21916a.addAll(list);
            notifyDataSetChanged();
        }

        /* renamed from: a */
        public SceneChildViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new SceneChildViewHolder(this.e.inflate(R.layout.myscene_fragment_recycleview_sort_item, (ViewGroup) null));
        }

        /* renamed from: a */
        public void onBindViewHolder(SceneChildViewHolder sceneChildViewHolder, int i) {
            boolean z;
            SceneApi.SmartHomeScene smartHomeScene = this.f21916a.get(i);
            sceneChildViewHolder.c.setText(smartHomeScene.g);
            if (TextUtils.equals(smartHomeScene.f, RecommendSceneManager.a().k())) {
                sceneChildViewHolder.j.setVisibility(0);
            } else {
                sceneChildViewHolder.j.setVisibility(8);
            }
            sceneChildViewHolder.e.setVisibility(0);
            sceneChildViewHolder.d.setVisibility(8);
            sceneChildViewHolder.g.setVisibility(8);
            sceneChildViewHolder.j.setVisibility(8);
            sceneChildViewHolder.h.setVisibility(0);
            sceneChildViewHolder.h.setHierarchy(new GenericDraweeHierarchyBuilder(sceneChildViewHolder.h.getResources()).setFadeDuration(200).setPlaceholderImage(sceneChildViewHolder.h.getResources().getDrawable(R.drawable.device_list_phone_no)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
            if (smartHomeScene == null || smartHomeScene.l.size() <= 0) {
                sceneChildViewHolder.h.setImageURI(CommonUtils.c((int) R.drawable.intelligence_icon_default_nor));
            } else {
                SmartHomeSceneUtility.a(sceneChildViewHolder.h, smartHomeScene.l.get(0));
                if (smartHomeScene.l.size() > 1) {
                    sceneChildViewHolder.n.setVisibility(0);
                } else {
                    sceneChildViewHolder.n.setVisibility(8);
                }
            }
            int i2 = 0;
            for (int i3 = 0; i3 < smartHomeScene.k.size(); i3++) {
                SceneApi.Action action = smartHomeScene.k.get(i3);
                if (i2 == 0) {
                    sceneChildViewHolder.k.setVisibility(0);
                    z = SmartHomeSceneUtility.a(action, smartHomeScene, sceneChildViewHolder.k);
                } else if (i2 == 1) {
                    sceneChildViewHolder.l.setVisibility(0);
                    z = SmartHomeSceneUtility.a(action, smartHomeScene, sceneChildViewHolder.l);
                } else if (i2 == 2) {
                    sceneChildViewHolder.m.setVisibility(0);
                    z = SmartHomeSceneUtility.a(action, smartHomeScene, sceneChildViewHolder.m);
                } else {
                    z = false;
                }
                if (z) {
                    i2++;
                }
            }
            if (i2 == 0) {
                sceneChildViewHolder.k.setVisibility(8);
                sceneChildViewHolder.l.setVisibility(8);
                sceneChildViewHolder.m.setVisibility(8);
            } else if (i2 == 1) {
                sceneChildViewHolder.l.setVisibility(8);
                sceneChildViewHolder.m.setVisibility(8);
            } else if (i2 == 2) {
                sceneChildViewHolder.m.setVisibility(8);
            }
            int i4 = 0;
            for (int i5 = 0; i5 < smartHomeScene.k.size(); i5++) {
                SceneApi.Action action2 = smartHomeScene.k.get(i5);
                if (!(action2 == null || action2.g == null)) {
                    i4++;
                }
            }
            if (i4 > 3) {
                sceneChildViewHolder.i.setVisibility(0);
            } else {
                sceneChildViewHolder.i.setVisibility(8);
            }
        }

        public int getItemCount() {
            return this.f21916a.size();
        }

        public long getItemId(int i) {
            return (long) this.f21916a.get(i).hashCode();
        }

        public List<SceneApi.SmartHomeScene> a() {
            return this.f21916a;
        }

        public boolean a(SceneChildViewHolder sceneChildViewHolder, int i, int i2, int i3) {
            LogUtil.a(c, "onCheckChildCanStartDrag");
            ImageView imageView = sceneChildViewHolder.e;
            if (imageView == null) {
                return false;
            }
            Rect rect = new Rect();
            sceneChildViewHolder.itemView.getGlobalVisibleRect(rect);
            Rect rect2 = new Rect();
            imageView.getGlobalVisibleRect(rect2);
            rect2.left -= rect.left;
            rect2.top -= rect.top;
            return rect2.contains(i2, i3);
        }

        /* renamed from: b */
        public ItemDraggableRange a(SceneChildViewHolder sceneChildViewHolder, int i) {
            LogUtil.a(c, "onGetGroupItemDraggableRange" + this.f21916a.size());
            return null;
        }

        public void b_(int i, int i2) {
            LogUtil.a(c, "fromPosition" + i + "------>" + i2);
            this.f21916a.add(i2, this.f21916a.remove(i));
            notifyItemMoved(i, i2);
        }

        public class SceneChildViewHolder extends AbstractDraggableSwipeableItemViewHolder {

            /* renamed from: a  reason: collision with root package name */
            public View f21917a;
            SimpleDraweeView b;
            TextView c;
            TextView d;
            ImageView e;
            LinearLayout f;
            CheckBox g;
            SimpleDraweeView h;
            ImageView i;
            TextView j;
            SimpleDraweeView k;
            SimpleDraweeView l;
            SimpleDraweeView m;
            ImageView n;

            public SceneChildViewHolder(View view) {
                super(view);
                this.f21917a = view;
                this.c = (TextView) view.findViewById(R.id.lite_scene_name_tv);
                this.d = (TextView) view.findViewById(R.id.execute_tv);
                this.e = (ImageView) view.findViewById(R.id.img_handle);
                this.f = (LinearLayout) view.findViewById(R.id.lite_scene_icon_ll);
                this.g = (CheckBox) view.findViewById(R.id.ckb_edit_selected);
                this.h = (SimpleDraweeView) view.findViewById(R.id.icon_condition);
                this.n = (ImageView) view.findViewById(R.id.icon_condition_more);
                this.i = (ImageView) view.findViewById(R.id.scene_new_more_icon);
                this.j = (TextView) view.findViewById(R.id.new_tag);
                this.k = (SimpleDraweeView) view.findViewById(R.id.icon_actiion1);
                this.l = (SimpleDraweeView) view.findViewById(R.id.icon_actiion2);
                this.m = (SimpleDraweeView) view.findViewById(R.id.icon_actiion3);
            }

            public View k() {
                return this.f21917a;
            }
        }
    }
}
