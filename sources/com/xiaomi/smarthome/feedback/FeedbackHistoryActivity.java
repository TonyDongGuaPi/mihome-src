package com.xiaomi.smarthome.feedback;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.feedback.FeedbackList;
import com.xiaomi.smarthome.feedback.view.BatchBar;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.widget.CustomPullDownRefreshListView;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.shop.utils.PageScrollListener;
import java.util.List;
import java.util.Set;

public class FeedbackHistoryActivity extends BaseActivity implements BatchBar.Delegate {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public List<FeedbackList.FeedbackItem> f15955a;
    /* access modifiers changed from: private */
    public FeedbackHistoryAdapter b;
    /* access modifiers changed from: private */
    public CustomPullDownRefreshListView c;
    /* access modifiers changed from: private */
    public ProgressBar d;
    /* access modifiers changed from: private */
    public BatchBar e;
    /* access modifiers changed from: private */
    public View f;
    /* access modifiers changed from: private */
    public View g;

    public View getContentViewOfBatchActionBar() {
        return null;
    }

    public View getContentViewOfBatchSelectBar() {
        return null;
    }

    public void onStartBatchMode() {
    }

    public void onUpdateBatchBarViewState(int i) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.feedback_history_layout);
        d();
        a();
        b();
    }

    private void a(ViewGroup viewGroup) {
        if (viewGroup != null && TitleBarUtil.f11582a) {
            TitleBarUtil.a(TitleBarUtil.a(), (View) viewGroup);
        }
    }

    private void a() {
        this.b = new FeedbackHistoryAdapter();
        this.c = (CustomPullDownRefreshListView) findViewById(R.id.pull_down_lv_feedback_history);
        this.d = (ProgressBar) findViewById(R.id.pb_loading);
        this.e = new BatchBar(getContext());
        this.f = findViewById(R.id.tv_empty_view);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.batch_select_bar);
        a(viewGroup);
        this.e.a(viewGroup, (ViewGroup) findViewById(R.id.batch_action_bar));
        this.e.a((BatchBar.Delegate) this);
        this.c.setAdapter(this.b);
        this.c.setOnScrollListener(new PageScrollListener(new Runnable() {
            public void run() {
                if (!FeedbackManager.INSTANCE.isLoadingHistory() && !FeedbackManager.INSTANCE.isAllLoaded()) {
                    FeedbackHistoryActivity.this.d.setVisibility(0);
                    FeedbackHistoryActivity.this.c();
                }
            }
        }));
        this.c.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /* JADX WARNING: type inference failed for: r2v0, types: [android.widget.AdapterView<?>, android.widget.AdapterView] */
            /* JADX WARNING: Unknown variable types count: 1 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onItemClick(android.widget.AdapterView<?> r2, android.view.View r3, int r4, long r5) {
                /*
                    r1 = this;
                    java.lang.Object r3 = r3.getTag()
                    com.xiaomi.smarthome.feedback.FeedbackHistoryActivity$ViewHolder r3 = (com.xiaomi.smarthome.feedback.FeedbackHistoryActivity.ViewHolder) r3
                    if (r3 != 0) goto L_0x0009
                    return
                L_0x0009:
                    com.xiaomi.smarthome.feedback.FeedbackHistoryActivity r5 = com.xiaomi.smarthome.feedback.FeedbackHistoryActivity.this
                    java.util.List r5 = r5.f15955a
                    if (r5 == 0) goto L_0x0074
                    com.xiaomi.smarthome.feedback.FeedbackHistoryActivity r5 = com.xiaomi.smarthome.feedback.FeedbackHistoryActivity.this
                    java.util.List r5 = r5.f15955a
                    int r5 = r5.size()
                    if (r5 > 0) goto L_0x001e
                    goto L_0x0074
                L_0x001e:
                    com.xiaomi.smarthome.feedback.FeedbackHistoryActivity r5 = com.xiaomi.smarthome.feedback.FeedbackHistoryActivity.this
                    com.xiaomi.smarthome.feedback.view.BatchBar r5 = r5.e
                    boolean r5 = r5.a()
                    if (r5 == 0) goto L_0x0043
                    com.xiaomi.smarthome.feedback.FeedbackHistoryActivity r2 = com.xiaomi.smarthome.feedback.FeedbackHistoryActivity.this
                    com.xiaomi.smarthome.feedback.view.BatchBar r2 = r2.e
                    com.xiaomi.smarthome.feedback.FeedbackHistoryActivity r3 = com.xiaomi.smarthome.feedback.FeedbackHistoryActivity.this
                    com.xiaomi.smarthome.library.common.widget.CustomPullDownRefreshListView r3 = r3.c
                    int r3 = r3.getHeaderViewsCount()
                    int r4 = r4 - r3
                    java.lang.Integer r3 = java.lang.Integer.valueOf(r4)
                    r2.b((java.lang.Integer) r3)
                    goto L_0x0073
                L_0x0043:
                    android.content.Intent r5 = new android.content.Intent
                    r5.<init>()
                    com.xiaomi.smarthome.feedback.FeedbackHistoryActivity r6 = com.xiaomi.smarthome.feedback.FeedbackHistoryActivity.this
                    android.content.Context r6 = r6.getContext()
                    java.lang.Class<com.xiaomi.smarthome.feedback.FeedbackDetailActivity> r0 = com.xiaomi.smarthome.feedback.FeedbackDetailActivity.class
                    r5.setClass(r6, r0)
                    android.widget.Adapter r2 = r2.getAdapter()
                    java.lang.Object r2 = r2.getItem(r4)
                    com.xiaomi.smarthome.feedback.FeedbackList$FeedbackItem r2 = (com.xiaomi.smarthome.feedback.FeedbackList.FeedbackItem) r2
                    java.lang.String r4 = "extra_id"
                    java.lang.String r6 = r2.f15970a
                    r5.putExtra(r4, r6)
                    android.view.View r3 = r3.c
                    r4 = 8
                    r3.setVisibility(r4)
                    r3 = 0
                    r2.c = r3
                    com.xiaomi.smarthome.feedback.FeedbackHistoryActivity r2 = com.xiaomi.smarthome.feedback.FeedbackHistoryActivity.this
                    r2.startActivity(r5)
                L_0x0073:
                    return
                L_0x0074:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.feedback.FeedbackHistoryActivity.AnonymousClass2.onItemClick(android.widget.AdapterView, android.view.View, int, long):void");
            }
        });
        this.c.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (FeedbackHistoryActivity.this.c.isRefreshing() || FeedbackHistoryActivity.this.f15955a == null || FeedbackHistoryActivity.this.f15955a.size() <= 0) {
                    return false;
                }
                if (!FeedbackHistoryActivity.this.e.a()) {
                    FeedbackHistoryActivity.this.e.a((BaseAdapter) FeedbackHistoryActivity.this.b);
                }
                if (!FeedbackHistoryActivity.this.e.a()) {
                    return true;
                }
                FeedbackHistoryActivity.this.e.b(Integer.valueOf(i - FeedbackHistoryActivity.this.c.getHeaderViewsCount()));
                return true;
            }
        });
        this.g = LayoutInflater.from(this).inflate(R.layout.common_list_space_empty, this.c, false);
    }

    /* access modifiers changed from: private */
    public void b() {
        FeedbackManager.INSTANCE.loadFeedbackHistory(getContext(), new AsyncCallback<List<FeedbackList.FeedbackItem>, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(final List<FeedbackList.FeedbackItem> list) {
                FeedbackHistoryActivity.this.mHandler.post(new Runnable() {
                    public void run() {
                        List unused = FeedbackHistoryActivity.this.f15955a = list;
                        FeedbackHistoryActivity.this.b.notifyDataSetChanged();
                        if (list.size() == 0) {
                            FeedbackHistoryActivity.this.f.setVisibility(0);
                            return;
                        }
                        FeedbackHistoryActivity.this.f.setVisibility(8);
                        FeedbackHistoryActivity.this.c.addHeaderView(FeedbackHistoryActivity.this.g);
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void c() {
        FeedbackManager.INSTANCE.loadMoreFeedbackHistory(getContext(), new AsyncCallback<List<FeedbackList.FeedbackItem>, Error>() {
            /* renamed from: a */
            public void onSuccess(final List<FeedbackList.FeedbackItem> list) {
                FeedbackHistoryActivity.this.mHandler.post(new Runnable() {
                    public void run() {
                        List unused = FeedbackHistoryActivity.this.f15955a = list;
                        FeedbackHistoryActivity.this.d.setVisibility(8);
                        FeedbackHistoryActivity.this.b.notifyDataSetChanged();
                    }
                });
            }

            public void onFailure(Error error) {
                FeedbackHistoryActivity.this.mHandler.post(new Runnable() {
                    public void run() {
                        FeedbackHistoryActivity.this.d.setVisibility(8);
                    }
                });
            }
        });
        this.d.setVisibility(0);
    }

    private void d() {
        TextView textView = (TextView) findViewById(R.id.module_a_3_return_title);
        if (textView != null) {
            textView.setText(R.string.feedback_history);
        }
        View findViewById = findViewById(R.id.module_a_3_return_btn);
        if (findViewById != null) {
            findViewById.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FeedbackHistoryActivity.this.finish();
                }
            });
        }
    }

    public void onExitBatchMode(int i, BaseAdapter baseAdapter) {
        if (i == 1) {
            Set<Integer> c2 = this.e.c();
            if (!c2.isEmpty()) {
                String[] strArr = new String[c2.size()];
                int i2 = 0;
                for (Integer intValue : c2) {
                    strArr[i2] = this.f15955a.get(intValue.intValue()).f15970a;
                    i2++;
                }
                FeedbackApi.INSTANCE.deleteFeedback(getContext(), strArr, new AsyncCallback<Boolean, Error>() {
                    public void onFailure(Error error) {
                    }

                    /* renamed from: a */
                    public void onSuccess(Boolean bool) {
                        FeedbackHistoryActivity.this.mHandler.post(new Runnable() {
                            public void run() {
                                FeedbackHistoryActivity.this.b();
                            }
                        });
                    }
                });
            }
        }
    }

    public void onBackPressed() {
        if (this.e == null || !this.e.a()) {
            super.onBackPressed();
        } else {
            this.e.a(0);
        }
    }

    class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        TextView f15968a;
        TextView b;
        View c;
        View d;
        CheckBox e;

        ViewHolder() {
        }
    }

    class FeedbackHistoryAdapter extends BaseAdapter {
        public long getItemId(int i) {
            return 0;
        }

        FeedbackHistoryAdapter() {
        }

        public int getCount() {
            if (FeedbackHistoryActivity.this.f15955a == null) {
                return 0;
            }
            return FeedbackHistoryActivity.this.f15955a.size();
        }

        public Object getItem(int i) {
            return FeedbackHistoryActivity.this.f15955a.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            FeedbackList.FeedbackItem feedbackItem;
            String str;
            if (view == null) {
                view = LayoutInflater.from(FeedbackHistoryActivity.this.getContext()).inflate(R.layout.feedback_history_item, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.c = view.findViewById(R.id.red_point);
                viewHolder.d = view.findViewById(R.id.right_hint);
                viewHolder.f15968a = (TextView) view.findViewById(R.id.feedback_title);
                viewHolder.b = (TextView) view.findViewById(R.id.feedback_desc);
                viewHolder.e = (CheckBox) view.findViewById(R.id.ckb_edit_selected);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            if (!(FeedbackHistoryActivity.this.f15955a == null || (feedbackItem = (FeedbackList.FeedbackItem) FeedbackHistoryActivity.this.f15955a.get(i)) == null)) {
                if (i == getCount() - 1) {
                    view.setBackgroundResource(R.drawable.common_white_list_padding_no_left_margin);
                } else {
                    view.setBackgroundResource(R.drawable.common_white_list_padding);
                }
                if (feedbackItem.f != null && !feedbackItem.f.isEmpty()) {
                    viewHolder.f15968a.setText(feedbackItem.f);
                }
                if (FeedbackHistoryActivity.this.e.a()) {
                    viewHolder.c.setVisibility(8);
                    viewHolder.d.setVisibility(8);
                    viewHolder.e.setVisibility(0);
                    if (FeedbackHistoryActivity.this.e.a(Integer.valueOf(i))) {
                        viewHolder.e.setChecked(true);
                    } else {
                        viewHolder.e.setChecked(false);
                    }
                } else {
                    viewHolder.e.setVisibility(8);
                    if (feedbackItem.c) {
                        viewHolder.c.setVisibility(0);
                        viewHolder.d.setVisibility(8);
                    } else {
                        viewHolder.c.setVisibility(8);
                        viewHolder.d.setVisibility(0);
                    }
                }
                String feedbackDeviceName = FeedbackManager.INSTANCE.getFeedbackDeviceName(FeedbackHistoryActivity.this.getContext(), feedbackItem.d);
                if (feedbackDeviceName != null) {
                    str = String.format("%s | %s", new Object[]{feedbackDeviceName, feedbackItem.b});
                } else {
                    str = feedbackItem.b;
                }
                if (str != null) {
                    viewHolder.b.setText(str);
                }
            }
            return view;
        }
    }
}
