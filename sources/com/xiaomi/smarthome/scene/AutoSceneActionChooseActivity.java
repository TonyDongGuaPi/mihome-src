package com.xiaomi.smarthome.scene;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.scene.api.SceneApi;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import java.util.ArrayList;
import java.util.List;

public class AutoSceneActionChooseActivity extends BaseActivity {
    public static final String SELECT_SCENE_ID = "select_scene_id";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public List<SceneApi.SmartHomeScene> f21183a = new ArrayList();
    private String b = null;
    private int c = -1;
    /* access modifiers changed from: private */
    public SceneAdapter d;
    private LayoutInflater e;
    /* access modifiers changed from: private */
    public int f = -1;
    /* access modifiers changed from: private */
    public SceneManager.IScenceListener g = new SceneManager.IScenceListener() {
        public void onRefreshScenceSuccess(int i) {
            if (i == 5) {
                SceneManager.x().b((SceneManager.IScenceListener) this);
                AutoSceneActionChooseActivity.this.mPullRefreshLL.refreshComplete();
                List unused = AutoSceneActionChooseActivity.this.f21183a = SceneManager.x().v();
                AutoSceneActionChooseActivity.this.a(AutoSceneActionChooseActivity.this.f21183a == null || AutoSceneActionChooseActivity.this.f21183a.size() == 0);
                AutoSceneActionChooseActivity.this.d.notifyDataSetChanged();
            }
        }

        public void onRefreshScenceFailed(int i) {
            SceneManager.x().b((SceneManager.IScenceListener) this);
            AutoSceneActionChooseActivity.this.mPullRefreshLL.refreshComplete();
            List unused = AutoSceneActionChooseActivity.this.f21183a = SceneManager.x().v();
            AutoSceneActionChooseActivity.this.d.notifyDataSetChanged();
        }
    };
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

    public class ViewHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private ViewHolder f21190a;

        @UiThread
        public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
            this.f21190a = viewHolder;
            viewHolder.actionLL = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.action_ll, "field 'actionLL'", LinearLayout.class);
            viewHolder.mIconCondition = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.icon_condition, "field 'mIconCondition'", SimpleDraweeView.class);
            viewHolder.mIconConditionMore = (ImageView) Utils.findRequiredViewAsType(view, R.id.icon_condition_more, "field 'mIconConditionMore'", ImageView.class);
            viewHolder.tvSceneName = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_scene_name, "field 'tvSceneName'", TextView.class);
            viewHolder.mRightView = (ImageView) Utils.findRequiredViewAsType(view, R.id.right_view, "field 'mRightView'", ImageView.class);
            viewHolder.mSceneNewMoreIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.scene_new_more_icon, "field 'mSceneNewMoreIcon'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            ViewHolder viewHolder = this.f21190a;
            if (viewHolder != null) {
                this.f21190a = null;
                viewHolder.actionLL = null;
                viewHolder.mIconCondition = null;
                viewHolder.mIconConditionMore = null;
                viewHolder.tvSceneName = null;
                viewHolder.mRightView = null;
                viewHolder.mSceneNewMoreIcon = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.auto_scene_choose_action_activity_layout);
        ButterKnife.bind((Activity) this);
        this.e = LayoutInflater.from(this);
        initView();
        this.mPullRefreshLL.autoRefresh();
        b();
    }

    /* access modifiers changed from: package-private */
    public void initView() {
        this.c = getIntent().getIntExtra(AutoSceneActionDetailChooseActivity.EXTRA_ENABEL, -1);
        this.b = getIntent().getStringExtra(SELECT_SCENE_ID);
        this.mTitle.setText(R.string.control_scene_action);
        this.mReturnBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AutoSceneActionChooseActivity.this.setResult(0);
                AutoSceneActionChooseActivity.this.finish();
            }
        });
        this.d = new SceneAdapter();
        this.mLayoutManager = new LinearLayoutManager(this);
        this.mRecyclerView.setLayoutManager(this.mLayoutManager);
        this.mRecyclerView.setAdapter(this.d);
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
                        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) AutoSceneActionChooseActivity.this.mLayoutManager;
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
                    LinearLayoutManager linearLayoutManager2 = (LinearLayoutManager) AutoSceneActionChooseActivity.this.mLayoutManager;
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
                SceneManager.x().a((String) null, AutoSceneActionChooseActivity.this.g);
                SceneManager.x().a();
            }
        });
        if (!SceneManager.x().n()) {
            this.mPullRefreshLL.autoRefresh();
            return;
        }
        this.f21183a.addAll(SceneManager.x().h());
        a(this.f21183a == null || this.f21183a.size() == 0);
        this.d.notifyDataSetChanged();
    }

    private void a() {
        SceneApi.SmartHomeScene g2 = CreateSceneManager.a().g();
        if (g2 != null) {
            for (int i = 0; i < this.f21183a.size(); i++) {
                if (TextUtils.equals(this.f21183a.get(i).f, g2.f)) {
                    this.f21183a.remove(i);
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        if (z) {
            this.mPullRefreshLL.setVisibility(8);
            this.mRecyclerView.setVisibility(8);
            this.mNoSceneView.setVisibility(0);
            return;
        }
        this.mPullRefreshLL.setVisibility(0);
        this.mRecyclerView.setVisibility(0);
        this.mNoSceneView.setVisibility(8);
    }

    private void b() {
        if (!TextUtils.isEmpty(this.b)) {
            this.f = a(this.b);
            if (this.f != -1) {
                Intent intent = new Intent(this, AutoSceneActionDetailChooseActivity.class);
                intent.putExtra(AutoSceneActionDetailChooseActivity.EXTRA_ENABEL, this.c);
                intent.putExtra("extra_id", this.b);
                startActivityForResult(intent, 101);
            }
        }
    }

    /* access modifiers changed from: private */
    public int a(String str) {
        List<SceneApi.SmartHomeScene> h = SceneManager.x().h();
        for (int i = 0; i < h.size(); i++) {
            if (TextUtils.equals(h.get(i).f, str)) {
                return i;
            }
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    class SceneAdapter extends RecyclerView.Adapter {
        public long getItemId(int i) {
            return 0;
        }

        SceneAdapter() {
        }

        @NonNull
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(AutoSceneActionChooseActivity.this).inflate(R.layout.auto_scene_choose_action_item, (ViewGroup) null));
        }

        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            ViewHolder viewHolder2 = (ViewHolder) viewHolder;
            final SceneApi.SmartHomeScene smartHomeScene = (SceneApi.SmartHomeScene) AutoSceneActionChooseActivity.this.f21183a.get(i);
            viewHolder2.tvSceneName.setText(smartHomeScene.g);
            viewHolder2.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(AutoSceneActionChooseActivity.this, AutoSceneActionDetailChooseActivity.class);
                    intent.putExtra("extra_id", smartHomeScene.f);
                    AutoSceneActionChooseActivity.this.startActivityForResult(intent, 101);
                    int unused = AutoSceneActionChooseActivity.this.f = AutoSceneActionChooseActivity.this.a(smartHomeScene.f);
                }
            });
            viewHolder2.tvSceneName.setTextColor(AutoSceneActionChooseActivity.this.getResources().getColor(R.color.black_80_transparent));
            if (smartHomeScene.l.size() > 0) {
                viewHolder2.mIconCondition.setVisibility(0);
                viewHolder2.mIconCondition.setHierarchy(new GenericDraweeHierarchyBuilder(viewHolder2.mIconCondition.getResources()).setFadeDuration(200).setPlaceholderImage(viewHolder2.mIconCondition.getResources().getDrawable(R.drawable.device_list_phone_no)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
                SmartHomeSceneUtility.a(viewHolder2.mIconCondition, smartHomeScene.l.get(0));
                if (smartHomeScene.l.size() > 1) {
                    viewHolder2.mIconConditionMore.setVisibility(0);
                } else {
                    viewHolder2.mIconConditionMore.setVisibility(8);
                }
                viewHolder2.mIconConditionMore.setImageResource(SmartHomeSceneUtility.f(smartHomeScene.q));
            } else {
                viewHolder2.mIconCondition.setVisibility(8);
                viewHolder2.mIconConditionMore.setVisibility(8);
            }
            if (SmartHomeSceneUtility.a(smartHomeScene, viewHolder2.actionLL) > 3) {
                viewHolder2.mSceneNewMoreIcon.setVisibility(0);
            } else {
                viewHolder2.mSceneNewMoreIcon.setVisibility(8);
            }
        }

        public int getItemCount() {
            if (AutoSceneActionChooseActivity.this.f21183a == null) {
                return 0;
            }
            return AutoSceneActionChooseActivity.this.f21183a.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(2131427494)
        LinearLayout actionLL;
        @BindView(2131429703)
        SimpleDraweeView mIconCondition;
        @BindView(2131429704)
        ImageView mIconConditionMore;
        @BindView(2131431921)
        ImageView mRightView;
        @BindView(2131432152)
        ImageView mSceneNewMoreIcon;
        @BindView(2131433475)
        TextView tvSceneName;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 101) {
            int intExtra = intent.getIntExtra(AutoSceneActionDetailChooseActivity.EXTRA_ENABEL, -1);
            Intent intent2 = new Intent();
            intent2.putExtra("extra_index", this.f);
            intent2.putExtra(AutoSceneActionDetailChooseActivity.EXTRA_ENABEL, intExtra);
            setResult(-1, intent2);
            finish();
        }
    }
}
