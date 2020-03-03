package com.xiaomi.smarthome.scenenew;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseFragment;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.common.widget.CustomPullDownRefreshListView;
import com.xiaomi.smarthome.lite.scene.SceneLogUtil;
import com.xiaomi.smarthome.operation.OperationCollector;
import com.xiaomi.smarthome.operation.smartrecommend.SmartRecommendOperation;
import com.xiaomi.smarthome.scenenew.adapter.RecommendSceneAdapter;
import com.xiaomi.smarthome.scenenew.manager.RecommendSceneManager;

public class RecommendSceneFragment extends BaseFragment {
    /* access modifiers changed from: private */
    public static final String f = "RecommendSceneFragment";

    /* renamed from: a  reason: collision with root package name */
    View f21765a;
    RecommendSceneAdapter b;
    CustomPullDownRefreshListView c;
    LinearLayout d;
    TextView e;
    private boolean g = true;
    private RecommendSceneManager.IGetRecommendSceneCallBack h = new RecommendSceneManager.IGetRecommendSceneCallBack() {
        public void a(int i) {
            if (RecommendSceneFragment.this.isValid()) {
                String b = RecommendSceneFragment.f;
                LogUtil.a(b, "onRefreshSuccess" + i);
                if (RecommendSceneFragment.this.c != null) {
                    if (i == 1) {
                        RecommendSceneFragment.this.c.postRefresh();
                    }
                    RecommendSceneFragment.this.b.a(RecommendSceneManager.a().b());
                    if (RecommendSceneFragment.this.b.getCount() == 0) {
                        RecommendSceneFragment.this.d.setVisibility(0);
                        RecommendSceneFragment.this.c.setVisibility(8);
                        return;
                    }
                    RecommendSceneFragment.this.d.setVisibility(8);
                    RecommendSceneFragment.this.c.setVisibility(0);
                }
            }
        }

        public void b(int i) {
            if (RecommendSceneFragment.this.isValid()) {
                String b = RecommendSceneFragment.f;
                LogUtil.a(b, "onRefreshFail" + i);
                RecommendSceneFragment.this.c.postRefresh();
                if (RecommendSceneFragment.this.b != null && RecommendSceneFragment.this.b.getCount() == 0) {
                    RecommendSceneFragment.this.d.setVisibility(0);
                }
            }
        }
    };

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        SceneLogUtil.a("RecommendSceneFragment  onCreateView");
        if (this.f21765a == null) {
            this.f21765a = layoutInflater.inflate(R.layout.fragment_recommend_layout, (ViewGroup) null);
            this.c = (CustomPullDownRefreshListView) this.f21765a.findViewById(R.id.pull_listview);
            this.d = (LinearLayout) this.f21765a.findViewById(R.id.common_white_empty_view);
            this.e = (TextView) this.f21765a.findViewById(R.id.common_white_empty_text);
            this.e.setText(R.string.recommend_scene_no_data);
            this.d.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (RecommendSceneFragment.this.c != null) {
                        RecommendSceneFragment.this.c.doRefresh();
                    }
                }
            });
            c();
            d();
        }
        return this.f21765a;
    }

    public void onViewCreated(View view, Bundle bundle) {
        SceneLogUtil.a("RecommendSceneFragment  onCreateView");
        super.onViewCreated(view, bundle);
    }

    private void c() {
        this.b = new RecommendSceneAdapter(getActivity());
        this.c.setAdapter(this.b);
        this.c.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            }

            public void onScrollStateChanged(AbsListView absListView, int i) {
                FragmentActivity validActivity;
                View currentFocus;
                if (1 == i && (validActivity = RecommendSceneFragment.this.getValidActivity()) != null && (currentFocus = validActivity.getCurrentFocus()) != null) {
                    currentFocus.clearFocus();
                }
            }
        });
    }

    public void onResume() {
        SceneLogUtil.a("RecommendSceneFragment  onCreateView");
        super.onResume();
    }

    private void d() {
        this.c.setRefreshListener(new CustomPullDownRefreshListView.OnRefreshListener() {
            public void startRefresh() {
                LogUtil.a(RecommendSceneFragment.f, "startRefresh");
                RecommendSceneManager.a().c();
                if (!HomeManager.A()) {
                    OperationCollector.a((Fragment) RecommendSceneFragment.this).a((ListView) RecommendSceneFragment.this.c);
                }
            }
        });
        this.c.doRefresh();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        SceneLogUtil.a("RecommendSceneFragment  onCreate");
        RecommendSceneManager.a().a(this.h);
    }

    public void onPageSelected() {
        super.onPageSelected();
        SmartRecommendOperation.m();
    }

    public void onDestroy() {
        super.onDestroy();
        RecommendSceneManager.a().b(this.h);
    }

    public void onDetach() {
        super.onDetach();
    }

    public void a() {
        if (isValid()) {
            if (SHApplication.getStateNotifier().a() != 4) {
                if (this.b != null && this.c != null) {
                    this.b.a(RecommendSceneManager.a().b());
                    if (this.b.getCount() == 0) {
                        this.d.setVisibility(0);
                        this.c.setVisibility(8);
                    }
                }
            } else if (this.b != null && this.c != null) {
                this.c.doRefresh();
            }
        }
    }
}
