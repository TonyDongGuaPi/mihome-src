package com.xiaomi.smarthome.scene;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.lite.scene.LiteSceneManager;
import com.xiaomi.smarthome.scene.api.SceneApi;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import java.util.ArrayList;
import java.util.List;

public class LiteAutomationChooseScene extends BaseActivity {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public List<SceneApi.SmartHomeScene> f21282a;
    /* access modifiers changed from: private */
    public String b;
    LiteSceneManager.IScenceListener liteSceneListener = new LiteSceneManager.IScenceListener() {
        public void a(int i) {
            if (LiteAutomationChooseScene.this.isValid()) {
                LiteAutomationChooseScene.this.mPullRefreshLL.refreshComplete();
                LiteSceneManager.j().c((LiteSceneManager.IScenceListener) this);
                if (i == 4) {
                    List unused = LiteAutomationChooseScene.this.f21282a = LiteSceneManager.j().f();
                    LiteAutomationChooseScene.this.mAdapter.notifyDataSetChanged();
                } else if (i == 5) {
                    List unused2 = LiteAutomationChooseScene.this.f21282a = LiteSceneManager.j().e();
                    LiteAutomationChooseScene.this.mAdapter.notifyDataSetChanged();
                }
                LiteAutomationChooseScene.this.b();
                LiteAutomationChooseScene.this.a(LiteAutomationChooseScene.this.f21282a == null || LiteAutomationChooseScene.this.f21282a.size() == 0);
            }
        }

        public void b(int i) {
            if (LiteAutomationChooseScene.this.isValid()) {
                LiteAutomationChooseScene.this.mPullRefreshLL.refreshComplete();
                LiteSceneManager.j().c((LiteSceneManager.IScenceListener) this);
                LiteAutomationChooseScene.this.mAdapter.notifyDataSetChanged();
            }
        }
    };
    SimpleAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    @BindView(2131431206)
    View mNoSceneView;
    @BindView(2131431674)
    PtrFrameLayout mPullRefreshLL;
    @BindView(2131432149)
    RecyclerView mRecyclerView;
    @BindView(2131430969)
    ImageView mReturnBtn;
    @BindView(2131430975)
    TextView mTitle;

    public class ItemViewHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private ItemViewHolder f21286a;

        @UiThread
        public ItemViewHolder_ViewBinding(ItemViewHolder itemViewHolder, View view) {
            this.f21286a = itemViewHolder;
            itemViewHolder.image = (ImageView) Utils.findRequiredViewAsType(view, R.id.image, "field 'image'", ImageView.class);
            itemViewHolder.name = (TextView) Utils.findRequiredViewAsType(view, R.id.name, "field 'name'", TextView.class);
            itemViewHolder.status = (TextView) Utils.findRequiredViewAsType(view, R.id.name_status, "field 'status'", TextView.class);
            itemViewHolder.rightBtn = Utils.findRequiredView(view, R.id.right_btn_container, "field 'rightBtn'");
            itemViewHolder.mDelItem = Utils.findRequiredView(view, R.id.del_action_item, "field 'mDelItem'");
            itemViewHolder.mTrueItem = Utils.findRequiredView(view, R.id.sort_action_item, "field 'mTrueItem'");
        }

        @CallSuper
        public void unbind() {
            ItemViewHolder itemViewHolder = this.f21286a;
            if (itemViewHolder != null) {
                this.f21286a = null;
                itemViewHolder.image = null;
                itemViewHolder.name = null;
                itemViewHolder.status = null;
                itemViewHolder.rightBtn = null;
                itemViewHolder.mDelItem = null;
                itemViewHolder.mTrueItem = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(2131429757)
        public ImageView image;
        @BindView(2131428713)
        public View mDelItem;
        @BindView(2131432561)
        public View mTrueItem;
        @BindView(2131431126)
        public TextView name;
        @BindView(2131431898)
        public View rightBtn;
        @BindView(2131431130)
        public TextView status;

        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
            this.rightBtn.setVisibility(8);
            this.mDelItem.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.automation_shoose_scene);
        ButterKnife.bind((Activity) this);
        initView();
        a();
        b();
        a(this.f21282a == null || this.f21282a.size() == 0);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        LiteSceneManager.j().c(this.liteSceneListener);
    }

    /* access modifiers changed from: package-private */
    public void initView() {
        this.mLayoutManager = new LinearLayoutManager(this);
        this.mRecyclerView.setLayoutManager(this.mLayoutManager);
        this.mTitle.setText(R.string.exectute_one_scene);
        this.mReturnBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LiteAutomationChooseScene.this.setResult(0);
                LiteAutomationChooseScene.this.finish();
            }
        });
        this.mAdapter = new SimpleAdapter();
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mPullRefreshLL.setPtrHandler(new PtrHandler() {
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
                if (Build.VERSION.SDK_INT < 14) {
                    if (view instanceof AbsListView) {
                        AbsListView absListView = (AbsListView) view;
                        if (absListView.getChildCount() <= 0 || (absListView.getFirstVisiblePosition() <= 0 && absListView.getChildAt(0).getTop() >= absListView.getPaddingTop())) {
                            return false;
                        }
                        return true;
                    } else if (view instanceof RecyclerView) {
                        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) LiteAutomationChooseScene.this.mLayoutManager;
                        if (linearLayoutManager != null && linearLayoutManager.findFirstCompletelyVisibleItemPosition() - 1 < 0) {
                            return true;
                        }
                        return false;
                    } else if (view.getScrollY() > 0) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (!(view instanceof RecyclerView)) {
                    return view.canScrollVertically(-1);
                } else {
                    LinearLayoutManager linearLayoutManager2 = (LinearLayoutManager) LiteAutomationChooseScene.this.mLayoutManager;
                    if (linearLayoutManager2 == null) {
                        return false;
                    }
                    LogUtil.a("ptrf", linearLayoutManager2.findFirstCompletelyVisibleItemPosition() + "____" + linearLayoutManager2.findFirstVisibleItemPosition());
                    if (linearLayoutManager2.findFirstCompletelyVisibleItemPosition() - 1 >= 0 || linearLayoutManager2.findFirstVisibleItemPosition() - 1 >= 0) {
                        return false;
                    }
                    return true;
                }
            }

            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                LiteSceneManager.j().a(LiteAutomationChooseScene.this.liteSceneListener);
            }
        });
    }

    private void a() {
        this.b = getIntent().getStringExtra("select_us_id");
        this.f21282a = new ArrayList();
        if (!LiteSceneManager.j().h()) {
            this.mPullRefreshLL.autoRefresh();
        } else if (LiteSceneManager.j().e().size() > 0) {
            this.f21282a.addAll(LiteSceneManager.j().e());
        } else {
            this.mRecyclerView.setVisibility(8);
            this.mNoSceneView.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        SceneApi.SmartHomeScene g = CreateSceneManager.a().g();
        if (g != null) {
            for (int i = 0; i < this.f21282a.size(); i++) {
                if (TextUtils.equals(this.f21282a.get(i).f, g.f)) {
                    this.f21282a.remove(i);
                    return;
                }
            }
        }
    }

    private class SimpleAdapter extends RecyclerView.Adapter {
        private SimpleAdapter() {
        }

        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ItemViewHolder(LayoutInflater.from(LiteAutomationChooseScene.this).inflate(R.layout.lite_auto_choose_scene_item, (ViewGroup) null));
        }

        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
            SceneApi.SmartHomeScene smartHomeScene = (SceneApi.SmartHomeScene) LiteAutomationChooseScene.this.f21282a.get(i);
            itemViewHolder.image.setImageResource(SmartHomeSceneUtility.a(smartHomeScene.i));
            if (TextUtils.equals(LiteAutomationChooseScene.this.b, smartHomeScene.f)) {
                itemViewHolder.name.setSelected(true);
            } else {
                itemViewHolder.name.setSelected(false);
            }
            itemViewHolder.name.setText(smartHomeScene.g);
            itemViewHolder.status.setVisibility(8);
            itemViewHolder.mTrueItem.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("extra_index", i);
                    LiteAutomationChooseScene.this.setResult(-1, intent);
                    LiteAutomationChooseScene.this.finish();
                    CreateSceneManager.a().l();
                }
            });
        }

        public int getItemCount() {
            return LiteAutomationChooseScene.this.f21282a.size();
        }
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        if (z) {
            this.mRecyclerView.setVisibility(8);
            this.mPullRefreshLL.setVisibility(8);
            this.mNoSceneView.setVisibility(0);
            return;
        }
        this.mRecyclerView.setVisibility(0);
        this.mPullRefreshLL.setVisibility(0);
        this.mNoSceneView.setVisibility(8);
    }
}
