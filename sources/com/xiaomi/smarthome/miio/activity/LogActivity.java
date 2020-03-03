package com.xiaomi.smarthome.miio.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.statistic.MiStatType;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.FontManager;
import com.xiaomi.smarthome.miio.page.smartgroup.SmartGroupConstants;
import com.xiaomi.smarthome.scene.api.RemoteSceneApi;
import com.xiaomi.smarthome.scenenew.model.SceneLogInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LogActivity extends BaseActivity {
    public static final String EXTRA_DID = "extra_did";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public ExpandableListView f11780a;
    /* access modifiers changed from: private */
    public ExpandAdapter b;
    /* access modifiers changed from: private */
    public ImageView c;
    /* access modifiers changed from: private */
    public String d = "";
    /* access modifiers changed from: private */
    public Typeface e;
    /* access modifiers changed from: private */
    public View f;
    /* access modifiers changed from: private */
    public HashMap<Integer, Integer> g = new HashMap<>();
    List<SceneLogInfo> mAllLogs = new ArrayList();
    XQProgressDialog mProcessDialog;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.log_layout);
        if (getIntent() != null) {
            this.d = getIntent().getStringExtra("extra_did");
        }
        initViews();
        MobclickAgent.a(getContext(), MiStatType.CLICK.getValue(), "scene_log_tab");
    }

    /* access modifiers changed from: package-private */
    public void initViews() {
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.log_title);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LogActivity.this.finish();
            }
        });
        this.g.put(0, Integer.valueOf(R.string.scene_error_0));
        this.g.put(-2, Integer.valueOf(R.string.scene_error_2));
        this.g.put(-3, Integer.valueOf(R.string.scene_error_3));
        this.g.put(-33066, Integer.valueOf(R.string.scene_error_33066));
        this.c = (ImageView) findViewById(R.id.module_a_3_return_more_more_btn);
        this.c.setImageResource(R.drawable.common_title_bar_clear);
        this.f = findViewById(R.id.common_white_empty_view);
        this.f.setVisibility(8);
        ((TextView) findViewById(R.id.common_white_empty_text)).setText(R.string.log_no_logs);
        this.c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                new MLAlertDialog.Builder(LogActivity.this).b((CharSequence) LogActivity.this.getResources().getString(R.string.log_clear_all_logs)).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        RemoteSceneApi.a().a(LogActivity.this.d, (Context) LogActivity.this, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                            /* renamed from: a */
                            public void onSuccess(Void voidR) {
                                Toast.makeText(LogActivity.this.getApplicationContext(), R.string.log_clear_success, 0);
                                LogActivity.this.mAllLogs.clear();
                                LogActivity.this.b.notifyDataSetChanged();
                                LogActivity.this.f.setVisibility(0);
                                LogActivity.this.c.setEnabled(false);
                            }

                            public void onFailure(Error error) {
                                Toast.makeText(LogActivity.this.getApplicationContext(), R.string.log_clear_error, 0);
                            }
                        });
                    }
                }).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).b().show();
            }
        });
        initData();
        this.f11780a = (ExpandableListView) findViewById(R.id.log_list);
        this.f11780a.setGroupIndicator((Drawable) null);
        this.f11780a.setChildDivider((Drawable) null);
        this.f11780a.setChildIndicator((Drawable) null);
        this.b = new ExpandAdapter();
        this.f11780a.setAdapter(this.b);
        this.e = FontManager.a(SmartGroupConstants.f);
    }

    /* access modifiers changed from: package-private */
    public void initData() {
        this.mProcessDialog = new XQProgressDialog(this);
        this.mProcessDialog.setCancelable(false);
        this.mProcessDialog.setMessage(getResources().getString(R.string.log_downlaoding));
        this.mProcessDialog.show();
        RemoteSceneApi.a().a(this.d, 0, (Context) this, (AsyncCallback<List<SceneLogInfo>, Error>) new AsyncCallback<List<SceneLogInfo>, Error>() {
            /* renamed from: a */
            public void onSuccess(List<SceneLogInfo> list) {
                LogActivity.this.mProcessDialog.dismiss();
                if (list != null) {
                    if ((LogActivity.this.mAllLogs == null || LogActivity.this.mAllLogs.size() == 0) && list != null && list.size() > 0) {
                        try {
                            LogActivity.this.f11780a.addHeaderView(LayoutInflater.from(LogActivity.this).inflate(R.layout.common_list_space_empty, LogActivity.this.f11780a, false));
                        } catch (Exception unused) {
                        }
                    }
                    LogActivity.this.mAllLogs = list;
                    if (LogActivity.this.mAllLogs.size() == 0) {
                        LogActivity.this.f.setVisibility(0);
                        LogActivity.this.c.setEnabled(false);
                    }
                    LogActivity.this.b.notifyDataSetChanged();
                }
            }

            public void onFailure(Error error) {
                LogActivity.this.c.setEnabled(false);
                LogActivity.this.mProcessDialog.dismiss();
            }
        });
    }

    class ViewTag {

        /* renamed from: a  reason: collision with root package name */
        RelativeLayout f11791a;
        RelativeLayout b;
        TextView c;
        TextView d;
        TextView e;
        RelativeLayout f;
        ImageView g;
        ImageView h;
        ImageView i;
        ImageView j;
        TextView k;
        TextView l;
        View m;
        ImageView n;

        ViewTag() {
        }
    }

    class ViewTagDetail {

        /* renamed from: a  reason: collision with root package name */
        TextView f11792a;
        TextView b;
        TextView c;

        ViewTagDetail() {
        }
    }

    class ExpandAdapter extends BaseExpandableListAdapter {

        /* renamed from: a  reason: collision with root package name */
        final int[] f11786a = {R.string.sunday, R.string.monday, R.string.tuesday, R.string.wednesday, R.string.thursday, R.string.friday, R.string.saturday};

        public boolean areAllItemsEnabled() {
            return false;
        }

        public Object getChild(int i, int i2) {
            return null;
        }

        public long getChildId(int i, int i2) {
            return 0;
        }

        public long getCombinedChildId(long j, long j2) {
            return 0;
        }

        public long getCombinedGroupId(long j) {
            return 0;
        }

        public Object getGroup(int i) {
            return null;
        }

        public long getGroupId(int i) {
            return 0;
        }

        public boolean hasStableIds() {
            return false;
        }

        public boolean isChildSelectable(int i, int i2) {
            return false;
        }

        public boolean isEmpty() {
            return false;
        }

        public void onGroupCollapsed(int i) {
        }

        public void onGroupExpanded(int i) {
        }

        ExpandAdapter() {
        }

        public int getGroupCount() {
            return LogActivity.this.mAllLogs.size();
        }

        public int getChildrenCount(int i) {
            if (!LogActivity.this.mAllLogs.get(i).b && LogActivity.this.mAllLogs.get(i).j != 0) {
                return LogActivity.this.mAllLogs.get(i).k.size();
            }
            return 0;
        }

        public View getGroupView(final int i, boolean z, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LogActivity.this.getLayoutInflater().inflate(R.layout.log_item, (ViewGroup) null);
                ViewTag viewTag = new ViewTag();
                viewTag.f11791a = (RelativeLayout) view.findViewById(R.id.log_time_container);
                viewTag.b = (RelativeLayout) view.findViewById(R.id.log_content);
                viewTag.c = (TextView) view.findViewById(R.id.month_text);
                viewTag.d = (TextView) view.findViewById(R.id.day_text);
                viewTag.e = (TextView) view.findViewById(R.id.week_text);
                viewTag.h = (ImageView) view.findViewById(R.id.feed_item_line_top);
                viewTag.g = (ImageView) view.findViewById(R.id.feed_item_line_circle_top);
                viewTag.f = (RelativeLayout) view.findViewById(R.id.top_line_container);
                viewTag.i = (ImageView) view.findViewById(R.id.feed_item_line_circle_bottom);
                viewTag.j = (ImageView) view.findViewById(R.id.feed_item_icon);
                viewTag.k = (TextView) view.findViewById(R.id.log_title_text);
                viewTag.l = (TextView) view.findViewById(R.id.log_detail_text);
                viewTag.m = view.findViewById(R.id.top_line_margin);
                viewTag.n = (ImageView) view.findViewById(R.id.right_arrow);
                view.setTag(viewTag);
            }
            ViewTag viewTag2 = (ViewTag) view.getTag();
            view.setOnClickListener((View.OnClickListener) null);
            if (LogActivity.this.mAllLogs.get(i).b) {
                viewTag2.f11791a.setVisibility(0);
                viewTag2.b.setVisibility(8);
                viewTag2.i.setVisibility(0);
                viewTag2.c.setTypeface(LogActivity.this.e);
                viewTag2.c.setText(LogActivity.this.mAllLogs.get(i).c);
                TextView textView = viewTag2.d;
                textView.setText(LogActivity.this.mAllLogs.get(i).d + LogActivity.this.getString(R.string.month));
                viewTag2.e.setText(this.f11786a[Integer.valueOf(LogActivity.this.mAllLogs.get(i).e).intValue()]);
            } else {
                viewTag2.f11791a.setVisibility(8);
                viewTag2.b.setVisibility(0);
                viewTag2.f.setVisibility(8);
                viewTag2.i.setVisibility(8);
                viewTag2.h.setVisibility(8);
                viewTag2.g.setVisibility(8);
                int i2 = i + 1;
                if (i2 >= LogActivity.this.mAllLogs.size() || LogActivity.this.mAllLogs.get(i2).b) {
                    viewTag2.i.setVisibility(0);
                }
                if (LogActivity.this.mAllLogs.get(i - 1).b) {
                    viewTag2.f.setVisibility(0);
                    viewTag2.h.setVisibility(0);
                    viewTag2.g.setVisibility(0);
                    viewTag2.m.setVisibility(8);
                } else {
                    viewTag2.f.setVisibility(8);
                    viewTag2.h.setVisibility(8);
                    viewTag2.g.setVisibility(8);
                    viewTag2.m.setVisibility(0);
                }
                viewTag2.k.setText(LogActivity.this.mAllLogs.get(i).g);
                String str = "";
                if (LogActivity.this.mAllLogs.get(i).j == 0) {
                    str = LogActivity.this.getString(R.string.log_scene_sucess);
                } else if (LogActivity.this.mAllLogs.get(i).j == -1) {
                    str = LogActivity.this.getString(R.string.log_scene_error);
                } else if (LogActivity.this.mAllLogs.get(i).j == 1) {
                    str = LogActivity.this.getString(R.string.log_scene_success_part);
                }
                TextView textView2 = viewTag2.l;
                textView2.setText(LogActivity.this.mAllLogs.get(i).h + "  " + str);
                viewTag2.j.setImageResource(LogActivity.this.mAllLogs.get(i).i);
                if (i2 < LogActivity.this.mAllLogs.size() && LogActivity.this.mAllLogs.get(i2).b) {
                    if (LogActivity.this.f11780a.isGroupExpanded(i)) {
                        viewTag2.i.setVisibility(8);
                    } else {
                        viewTag2.i.setVisibility(0);
                    }
                }
                if (LogActivity.this.mAllLogs.get(i).j == 0 || LogActivity.this.mAllLogs.get(i).k.size() == 0) {
                    viewTag2.n.setVisibility(8);
                } else {
                    viewTag2.n.setVisibility(0);
                    if (LogActivity.this.f11780a.isGroupExpanded(i)) {
                        viewTag2.n.setImageResource(R.drawable.std_tittlebar_details_shop_arrow_press);
                    } else {
                        viewTag2.n.setImageResource(R.drawable.std_tittlebar_details_shop_arrow_normal);
                    }
                }
                if (LogActivity.this.mAllLogs.get(i).j == 0) {
                    viewTag2.l.setTextColor(LogActivity.this.getResources().getColor(R.color.black_80_transparent));
                } else {
                    viewTag2.l.setTextColor(LogActivity.this.getResources().getColor(R.color.red_80_transparent));
                    view.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            if (LogActivity.this.f11780a.isGroupExpanded(i)) {
                                LogActivity.this.f11780a.collapseGroup(i);
                            } else {
                                LogActivity.this.f11780a.expandGroup(i);
                            }
                        }
                    });
                }
            }
            return view;
        }

        public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(LogActivity.this).inflate(R.layout.scene_log_item_detail, (ViewGroup) null);
                ViewTagDetail viewTagDetail = new ViewTagDetail();
                viewTagDetail.f11792a = (TextView) view.findViewById(R.id.log_title_text);
                viewTagDetail.b = (TextView) view.findViewById(R.id.log_detail_text);
                viewTagDetail.c = (TextView) view.findViewById(R.id.log_detail_right_text);
                view.setTag(viewTagDetail);
            }
            ViewTagDetail viewTagDetail2 = (ViewTagDetail) view.getTag();
            SceneLogInfo.Detail detail = LogActivity.this.mAllLogs.get(i).k.get(i2);
            if (detail.f21992a == 0) {
                viewTagDetail2.f11792a.setText(detail.b);
                viewTagDetail2.b.setText(detail.c);
            } else {
                viewTagDetail2.f11792a.setText(R.string.smarthome_scene_push_action);
                viewTagDetail2.b.setText("");
            }
            viewTagDetail2.c.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                }
            });
            if (LogActivity.this.g.containsKey(Integer.valueOf(detail.e))) {
                viewTagDetail2.c.setText(((Integer) LogActivity.this.g.get(Integer.valueOf(detail.e))).intValue());
            } else {
                viewTagDetail2.c.setText(R.string.scene_error_other);
                viewTagDetail2.c.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ExpandAdapter.this.a();
                    }
                });
            }
            int i3 = i + 1;
            if (i3 >= LogActivity.this.mAllLogs.size() || !LogActivity.this.mAllLogs.get(i3).b) {
                view.findViewById(R.id.feed_item_line_circle_bottom).setVisibility(8);
            } else {
                view.findViewById(R.id.feed_item_line_circle_bottom).setVisibility(0);
            }
            if (detail.e == 0) {
                viewTagDetail2.c.setTextColor(LogActivity.this.getResources().getColor(R.color.black_80_transparent));
            } else {
                viewTagDetail2.c.setTextColor(LogActivity.this.getResources().getColor(R.color.red_80_transparent));
            }
            return view;
        }

        /* access modifiers changed from: private */
        public void a() {
            new MLAlertDialog.Builder(LogActivity.this).b((int) R.string.scene_error_dialog_tips).c((int) R.string.scene_has_knowed, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            }).b().show();
        }
    }
}
