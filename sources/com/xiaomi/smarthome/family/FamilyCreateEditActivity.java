package com.xiaomi.smarthome.family;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.utils.ClientRemarkInputView;
import com.xiaomi.smarthome.family.FamilyManager;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.imagecache.CircleAvatarProcessor;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.CustomPullDownRefreshLinearLayout;
import com.xiaomi.smarthome.library.common.widget.ExpandGridView;
import com.xiaomi.smarthome.library.common.widget.ExpandListView;
import com.xiaomi.smarthome.library.common.widget.ListItemView;
import com.xiaomi.smarthome.library.common.widget.PieProgressBar;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.miband.data.PluginDeviceDownloadItem;
import com.xiaomi.smarthome.miio.miband.utils.PluginDeviceNavigateHelper;
import com.xiaomi.smarthome.miio.user.UserMamanger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

@Deprecated
public class FamilyCreateEditActivity extends BaseActivity {
    public static final String CREATE_FAMILY_KEY = "is_create_family";
    public static final int EDIT_FAMILY_QUIT_SUCCESS = 201;
    public static final int EDIT_FAMILY_REQUEST_CODE = 101;
    public static final String FAMILY_CURRENT_USER_NAME = "family_current_user_name";

    /* renamed from: a  reason: collision with root package name */
    private static final String f15646a = "FamilyCreateEditActivity";
    /* access modifiers changed from: private */
    public boolean b = true;
    /* access modifiers changed from: private */
    public XQProgressDialog c;
    /* access modifiers changed from: private */
    public CustomPullDownRefreshLinearLayout d;
    private ExpandGridView e;
    /* access modifiers changed from: private */
    public ExpandListView f;
    private View g;
    private View h;
    private TextView i;
    private ImageView j;
    /* access modifiers changed from: private */
    public ImageView k;
    private TextView l;
    PluginDeviceNavigateHelper.DownloadStateListener listener = new PluginDeviceNavigateHelper.DownloadStateListener() {
        public void a(final PluginDeviceDownloadItem pluginDeviceDownloadItem) {
            if (FamilyCreateEditActivity.this.s != null) {
                FamilyCreateEditActivity.this.mHandler.post(new Runnable() {
                    public void run() {
                        int firstVisiblePosition = FamilyCreateEditActivity.this.f.getFirstVisiblePosition();
                        int lastVisiblePosition = FamilyCreateEditActivity.this.f.getLastVisiblePosition();
                        int i = -1;
                        for (Integer num : FamilyCreateEditActivity.this.q) {
                            if (((Device) FamilyCreateEditActivity.this.p.get(num.intValue())).model.equals(pluginDeviceDownloadItem.b)) {
                                if (pluginDeviceDownloadItem.c != PluginDeviceDownloadItem.Status.DOWNLOADING) {
                                    i = num.intValue();
                                    if (num.intValue() >= firstVisiblePosition && num.intValue() <= lastVisiblePosition) {
                                        View childAt = FamilyCreateEditActivity.this.f.getChildAt(num.intValue());
                                        FamilyDeviceAdapter.ViewHolder viewHolder = (FamilyDeviceAdapter.ViewHolder) childAt.getTag();
                                        if (viewHolder != null) {
                                            viewHolder.e.setVisibility(4);
                                            viewHolder.f.setVisibility(4);
                                            childAt.invalidate();
                                        }
                                    }
                                } else if (num.intValue() >= firstVisiblePosition && num.intValue() <= lastVisiblePosition) {
                                    View childAt2 = FamilyCreateEditActivity.this.f.getChildAt(num.intValue());
                                    FamilyDeviceAdapter.ViewHolder viewHolder2 = (FamilyDeviceAdapter.ViewHolder) childAt2.getTag();
                                    if (viewHolder2 != null) {
                                        viewHolder2.e.setVisibility(0);
                                        viewHolder2.f.setVisibility(0);
                                        viewHolder2.e.setPercent(pluginDeviceDownloadItem.f19463a * 100.0f);
                                        viewHolder2.f.setText(String.format("%d%%", new Object[]{Integer.valueOf((int) (pluginDeviceDownloadItem.f19463a * 100.0f))}));
                                        childAt2.invalidate();
                                    }
                                }
                            }
                        }
                        if (i != -1) {
                            FamilyCreateEditActivity.this.q.remove(Integer.valueOf(i));
                        }
                    }
                });
            }
        }
    };
    /* access modifiers changed from: private */
    public TextView m;
    /* access modifiers changed from: private */
    public List<FamilyMemberData> n = new ArrayList();
    /* access modifiers changed from: private */
    public List<FamilyDeviceData> o = new ArrayList();
    /* access modifiers changed from: private */
    public List<Device> p = new ArrayList();
    /* access modifiers changed from: private */
    public List<Integer> q = new ArrayList();
    /* access modifiers changed from: private */
    public FamilyItemData r = null;
    /* access modifiers changed from: private */
    public FamilyDeviceAdapter s;
    /* access modifiers changed from: private */
    public FamilyMemberAdapter t;
    private FamilyManager.Listener u = new FamilyManager.Listener() {
        public void a() {
        }

        public void a(FamilyData familyData) {
            if (familyData != null && FamilyCreateEditActivity.this.r != null && familyData.d.f.equals(FamilyCreateEditActivity.this.r.f)) {
                List unused = FamilyCreateEditActivity.this.o = familyData.e;
                List unused2 = FamilyCreateEditActivity.this.n = familyData.f;
                FamilyCreateEditActivity.this.s.notifyDataSetChanged();
                FamilyCreateEditActivity.this.t.notifyDataSetChanged();
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean v = false;
    /* access modifiers changed from: private */
    public SparseBooleanArray w = new SparseBooleanArray();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        this.b = intent.getBooleanExtra(CREATE_FAMILY_KEY, true);
        if (!this.b) {
            this.r = (FamilyItemData) intent.getParcelableExtra(FamilyItemData.f15689a);
        }
        setContentView(R.layout.family_create_edit_layout);
        c();
        d();
        a();
    }

    private void a() {
        FamilyData a2;
        if (!this.b && (a2 = FamilyManager.a().a(this.r.f)) != null) {
            this.n = a2.f;
            this.o = a2.e;
            e();
            this.s.notifyDataSetChanged();
            this.t.notifyDataSetChanged();
        }
    }

    public void onResume() {
        super.onResume();
        FamilyManager.a().a(this.u);
        PluginDeviceNavigateHelper.a().a(this.listener);
        if (!this.b) {
            this.d.doRefresh();
        } else {
            b();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.d != null) {
            this.d = null;
        }
        if (this.c != null) {
            this.c.dismiss();
            this.c = null;
        }
    }

    public void onPause() {
        super.onPause();
        this.d.postRefresh();
        FamilyManager.a().b(this.u);
        PluginDeviceNavigateHelper.a().b(this.listener);
    }

    public void showDeleteBars() {
        this.g.setVisibility(0);
        this.h.setVisibility(0);
        this.g.measure(0, 0);
        this.h.measure(0, 0);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.g, View.Y, new float[]{(float) (-this.g.getMeasuredHeight()), 0.0f});
        ViewGroup viewGroup = (ViewGroup) this.h.getParent();
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.h, View.Y, new float[]{(float) viewGroup.getHeight(), (float) (viewGroup.getHeight() - this.h.getMeasuredHeight())});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(200);
        animatorSet.play(ofFloat).with(ofFloat2);
        animatorSet.start();
    }

    public void hideDeleteBars() {
        this.g.setVisibility(8);
        this.h.setVisibility(8);
    }

    public void deleteSelected() {
        if (this.w.size() == 0) {
            ToastUtil.a((Context) this, (int) R.string.family_no_device_selected);
            return;
        }
        MLAlertDialog.Builder a2 = new MLAlertDialog.Builder(this).a((int) R.string.family_remove_device_title);
        a2.b((CharSequence) getString(R.string.family_remove_device, new Object[]{this.w.size() + ""})).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).a((int) R.string.confirm_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < FamilyCreateEditActivity.this.w.size(); i2++) {
                    int keyAt = FamilyCreateEditActivity.this.w.keyAt(i2);
                    if (FamilyCreateEditActivity.this.w.get(keyAt) && FamilyCreateEditActivity.this.p.size() > keyAt) {
                        arrayList.add(((Device) FamilyCreateEditActivity.this.p.get(keyAt)).did);
                    }
                }
                RemoteFamilyApi.a().d(FamilyCreateEditActivity.this, FamilyCreateEditActivity.this.r.f, (String) arrayList.get(0), new AsyncCallback<Void, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        if (FamilyCreateEditActivity.this.d != null) {
                            FamilyCreateEditActivity.this.w.clear();
                            FamilyCreateEditActivity.this.m.setVisibility(8);
                            FamilyCreateEditActivity.this.k.setImageResource(R.drawable.all_select_selector);
                            Toast.makeText(FamilyCreateEditActivity.this, R.string.family_remove_device_succeed, 0).show();
                            FamilyCreateEditActivity.this.mHandler.post(new Runnable() {
                                public void run() {
                                    FamilyCreateEditActivity.this.dismissEditMode();
                                    FamilyCreateEditActivity.this.f();
                                }
                            });
                        }
                    }

                    public void onFailure(Error error) {
                        if (FamilyCreateEditActivity.this.d != null) {
                            Toast.makeText(FamilyCreateEditActivity.this, R.string.family_remove_device_failed, 0).show();
                        }
                    }
                });
            }
        }).b().show();
    }

    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (i2 != 4 || keyEvent.getRepeatCount() != 0 || !this.v) {
            return super.onKeyUp(i2, keyEvent);
        }
        dismissEditMode();
        return true;
    }

    private void b() {
        if (this.b) {
            final ClientRemarkInputView clientRemarkInputView = (ClientRemarkInputView) getLayoutInflater().inflate(R.layout.client_remark_input_view, (ViewGroup) null);
            MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
            builder.a((int) R.string.family_create);
            builder.b((View) clientRemarkInputView);
            builder.d(false);
            MLAlertDialog b2 = builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    final String obj = clientRemarkInputView.getEditText().getText().toString();
                    if (TextUtils.isEmpty(obj)) {
                        clientRemarkInputView.getEditText().setError(FamilyCreateEditActivity.this.getString(R.string.back_name_less_limit_not_null));
                    } else if (obj.length() > 10) {
                        clientRemarkInputView.getEditText().setError(FamilyCreateEditActivity.this.getString(R.string.back_name_less_limit_30));
                    } else {
                        ((MLAlertDialog) dialogInterface).setAudoDismiss(true);
                        FamilyCreateEditActivity.this.mHandler.post(new Runnable() {
                            public void run() {
                                FamilyCreateEditActivity.this.b(obj);
                            }
                        });
                    }
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ((MLAlertDialog) dialogInterface).setAudoDismiss(true);
                    FamilyCreateEditActivity.this.finish();
                }
            }).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    ((MLAlertDialog) dialogInterface).setAudoDismiss(true);
                    FamilyCreateEditActivity.this.finish();
                }
            }).b();
            clientRemarkInputView.initialize((ClientRemarkInputView.RenameInterface) null, b2, getString(R.string.family_default_name));
            b2.show();
        }
    }

    private void c() {
        this.i = (TextView) findViewById(R.id.module_a_3_return_title);
        this.d = (CustomPullDownRefreshLinearLayout) findViewById(R.id.family_refresh_view);
        this.e = (ExpandGridView) findViewById(R.id.family_member_grid_view);
        this.f = (ExpandListView) findViewById(R.id.device_list_view);
        this.g = findViewById(R.id.top_delete_bar);
        this.m = (TextView) this.g.findViewById(R.id.selected_cnt);
        this.j = (ImageView) this.g.findViewById(R.id.cancel_btn);
        this.k = (ImageView) this.g.findViewById(R.id.select_all_btn);
        this.h = findViewById(R.id.bottom_delete_bar);
        this.l = (TextView) this.h.findViewById(R.id.delete_msg_btn);
    }

    private void d() {
        a(this.r != null ? this.r.g : null);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FamilyCreateEditActivity.this.finish();
            }
        });
        this.l.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FamilyCreateEditActivity.this.deleteSelected();
            }
        });
        this.j.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FamilyCreateEditActivity.this.dismissEditMode();
            }
        });
        this.k.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (FamilyCreateEditActivity.this.w.size() == FamilyCreateEditActivity.this.p.size()) {
                    FamilyCreateEditActivity.this.unSelectAll();
                } else {
                    FamilyCreateEditActivity.this.selectAll();
                }
            }
        });
        this.d.setRefreshListener(new CustomPullDownRefreshLinearLayout.OnRefreshListener() {
            public void a() {
                FamilyCreateEditActivity.this.f();
            }
        });
        this.d.setScrollView((ScrollView) findViewById(R.id.scroll_view));
        this.s = new FamilyDeviceAdapter();
        View g2 = g();
        g2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (FamilyCreateEditActivity.this.r != null) {
                    Intent intent = new Intent(FamilyCreateEditActivity.this, FamilyAddDeviceActivity.class);
                    intent.putExtra(FamilyItemData.f15689a, FamilyCreateEditActivity.this.r);
                    FamilyCreateEditActivity.this.startActivity(intent);
                }
            }
        });
        this.f.addFooterView(g2);
        this.f.setAdapter(this.s);
        this.t = new FamilyMemberAdapter();
        this.e.setAdapter(this.t);
        if (TitleBarUtil.f11582a) {
            TitleBarUtil.a(TitleBarUtil.a(), this.g);
        }
        this.c = new XQProgressDialog(this);
        this.c.setCancelable(false);
        this.c.setMessage(getResources().getString(R.string.loading_share_info));
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        if (this.i != null) {
            if (str == null || str.isEmpty()) {
                this.i.setText(R.string.family_default_name);
            } else {
                this.i.setText(str);
            }
        }
    }

    /* access modifiers changed from: private */
    public void b(String str) {
        if (this.b) {
            this.c.show();
            RemoteFamilyApi.a().f(this, str, new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    if (FamilyCreateEditActivity.this.d != null) {
                        FamilyCreateEditActivity.this.c.dismiss();
                        boolean unused = FamilyCreateEditActivity.this.b = false;
                        FamilyItemData unused2 = FamilyCreateEditActivity.this.r = FamilyItemData.a(jSONObject);
                        FamilyCreateEditActivity.this.mHandler.postDelayed(new Runnable() {
                            public void run() {
                                FamilyCreateEditActivity.this.f();
                            }
                        }, 800);
                    }
                }

                public void onFailure(Error error) {
                    if (FamilyCreateEditActivity.this.d != null) {
                        FamilyCreateEditActivity.this.c.dismiss();
                        Toast.makeText(FamilyCreateEditActivity.this, R.string.family_create_failed, 0).show();
                        FamilyCreateEditActivity.this.finish();
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        if (this.o != null) {
            this.p.clear();
            this.q.clear();
            for (FamilyDeviceData familyDeviceData : this.o) {
                Device b2 = SmartHomeDeviceManager.a().b(familyDeviceData.c);
                if (b2 != null) {
                    this.p.add(b2);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void f() {
        if (this.b || this.r == null) {
            this.d.postRefresh();
            return;
        }
        SmartHomeDeviceManager.a().p();
        FamilyManager.a().a(this, this.r.f, new AsyncResponseCallback<FamilyData>() {
            public void a(FamilyData familyData) {
                if (FamilyCreateEditActivity.this.d != null) {
                    FamilyCreateEditActivity.this.d.postRefresh();
                    if (familyData == null) {
                        Toast.makeText(FamilyCreateEditActivity.this, R.string.family_not_exist, 0).show();
                        FamilyCreateEditActivity.this.finish();
                        return;
                    }
                    FamilyItemData unused = FamilyCreateEditActivity.this.r = familyData.d;
                    FamilyCreateEditActivity.this.a(FamilyCreateEditActivity.this.r.g);
                    List unused2 = FamilyCreateEditActivity.this.o = familyData.e;
                    FamilyCreateEditActivity.this.e();
                    List unused3 = FamilyCreateEditActivity.this.n = familyData.f;
                    FamilyCreateEditActivity.this.s.notifyDataSetChanged();
                    FamilyCreateEditActivity.this.t.notifyDataSetChanged();
                }
            }

            public void a(int i) {
                if (FamilyCreateEditActivity.this.d != null) {
                    FamilyCreateEditActivity.this.d.postRefresh();
                }
            }

            public void a(int i, Object obj) {
                if (FamilyCreateEditActivity.this.d != null) {
                    FamilyCreateEditActivity.this.d.postRefresh();
                }
            }
        });
    }

    class FamilyMemberAdapter extends BaseAdapter {
        public long getItemId(int i) {
            return 0;
        }

        FamilyMemberAdapter() {
        }

        public int getCount() {
            if (FamilyCreateEditActivity.this.n == null) {
                return 1;
            }
            return 1 + FamilyCreateEditActivity.this.n.size();
        }

        public Object getItem(int i) {
            if (i < 0 || FamilyCreateEditActivity.this.n == null || i >= FamilyCreateEditActivity.this.n.size()) {
                return null;
            }
            return FamilyCreateEditActivity.this.n.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(FamilyCreateEditActivity.this).inflate(R.layout.family_member_item, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.f15677a = (SimpleDraweeView) view.findViewById(R.id.iv_member_icon);
                viewHolder.b = (TextView) view.findViewById(R.id.tv_member_name);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            view.setOnClickListener((View.OnClickListener) null);
            if (i < FamilyCreateEditActivity.this.n.size()) {
                final FamilyMemberData familyMemberData = (FamilyMemberData) FamilyCreateEditActivity.this.n.get(i);
                if (familyMemberData != null) {
                    if (!StringUtil.c(familyMemberData.k)) {
                        viewHolder.b.setText(familyMemberData.k);
                    } else if (!StringUtil.c(familyMemberData.i)) {
                        viewHolder.b.setText(familyMemberData.i);
                    } else if (!StringUtil.c(familyMemberData.h)) {
                        viewHolder.b.setText(familyMemberData.h);
                    }
                    UserMamanger.a().b(familyMemberData.j, viewHolder.f15677a, new CircleAvatarProcessor());
                }
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent(FamilyCreateEditActivity.this, FamilyEditMemberActivity.class);
                        intent.putExtra(FamilyItemData.f15689a, FamilyCreateEditActivity.this.r);
                        intent.putExtra(FamilyMemberData.f15708a, familyMemberData);
                        FamilyCreateEditActivity.this.startActivityForResult(intent, 101);
                    }
                });
            } else {
                viewHolder.b.setText(R.string.family_add_member);
                viewHolder.f15677a.setImageURI(CommonUtils.c((int) R.drawable.family_add_member_button));
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (FamilyCreateEditActivity.this.r != null) {
                            Intent intent = new Intent(FamilyCreateEditActivity.this, FamilyAddMemberActivity.class);
                            String str = null;
                            String s = CoreApi.a().s();
                            Iterator it = FamilyCreateEditActivity.this.n.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                FamilyMemberData familyMemberData = (FamilyMemberData) it.next();
                                if (familyMemberData.g.equals(s)) {
                                    str = familyMemberData.i;
                                    break;
                                }
                            }
                            intent.putExtra(FamilyItemData.f15689a, FamilyCreateEditActivity.this.r);
                            intent.putExtra(FamilyCreateEditActivity.FAMILY_CURRENT_USER_NAME, str);
                            FamilyCreateEditActivity.this.startActivity(intent);
                        }
                    }
                });
            }
            return view;
        }

        class ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            SimpleDraweeView f15677a;
            TextView b;

            ViewHolder() {
            }
        }
    }

    public void dismissEditMode() {
        this.v = false;
        this.w.clear();
        hideDeleteBars();
        this.d.setCanPullDown(true);
        this.s.notifyDataSetChanged();
    }

    public void selectAll() {
        int size = this.p.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.w.put(i2, true);
        }
        this.k.setImageResource(R.drawable.un_select_selector);
        this.s.notifyDataSetChanged();
        this.m.setVisibility(0);
        this.m.setText(getResources().getQuantityString(R.plurals.selected_cnt_tips, this.w.size(), new Object[]{Integer.valueOf(this.w.size())}));
    }

    public void unSelectAll() {
        this.w.clear();
        this.k.setImageResource(R.drawable.all_select_selector);
        this.s.notifyDataSetChanged();
        this.m.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        if (i2 != 101) {
            return;
        }
        if (i3 == -1) {
            this.mHandler.postDelayed(new Runnable() {
                public void run() {
                    FamilyCreateEditActivity.this.f();
                }
            }, 400);
        } else if (i3 == 201) {
            finish();
        }
    }

    private View g() {
        return LayoutInflater.from(this).inflate(R.layout.family_add_device_footer_view, this.f, false);
    }

    class FamilyDeviceAdapter extends BaseAdapter {
        public long getItemId(int i) {
            return 0;
        }

        FamilyDeviceAdapter() {
        }

        public class ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            SimpleDraweeView f15673a;
            CheckBox b;
            TextView c;
            TextView d;
            PieProgressBar e;
            TextView f;

            public ViewHolder() {
            }
        }

        public int getCount() {
            if (FamilyCreateEditActivity.this.p == null) {
                return 0;
            }
            return FamilyCreateEditActivity.this.p.size();
        }

        public Object getItem(int i) {
            if (i < 0 || FamilyCreateEditActivity.this.p == null || i >= FamilyCreateEditActivity.this.p.size()) {
                return null;
            }
            return FamilyCreateEditActivity.this.p.get(i);
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(FamilyCreateEditActivity.this).inflate(R.layout.family_device_item_layout, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.f15673a = (SimpleDraweeView) view.findViewById(R.id.image_button);
                viewHolder.b = (CheckBox) view.findViewById(R.id.ckb_edit_selected);
                viewHolder.c = (TextView) view.findViewById(R.id.name);
                viewHolder.d = (TextView) view.findViewById(R.id.name_status);
                viewHolder.e = (PieProgressBar) view.findViewById(R.id.update_progress);
                viewHolder.f = (TextView) view.findViewById(R.id.update_percent);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.b.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!FamilyCreateEditActivity.this.w.get(i)) {
                        FamilyCreateEditActivity.this.w.put(i, true);
                    } else {
                        FamilyCreateEditActivity.this.w.delete(i);
                    }
                    if (FamilyCreateEditActivity.this.w.size() == FamilyCreateEditActivity.this.p.size()) {
                        FamilyCreateEditActivity.this.k.setImageResource(R.drawable.un_select_selector);
                    } else {
                        FamilyCreateEditActivity.this.k.setImageResource(R.drawable.all_select_selector);
                    }
                    if (FamilyCreateEditActivity.this.w.size() > 0) {
                        FamilyCreateEditActivity.this.m.setVisibility(0);
                        FamilyCreateEditActivity.this.m.setText(FamilyCreateEditActivity.this.getResources().getQuantityString(R.plurals.selected_cnt_tips, FamilyCreateEditActivity.this.w.size(), new Object[]{Integer.valueOf(FamilyCreateEditActivity.this.w.size())}));
                        return;
                    }
                    FamilyCreateEditActivity.this.m.setVisibility(0);
                    FamilyCreateEditActivity.this.m.setText(FamilyCreateEditActivity.this.getString(R.string.selected_0_cnt_tips));
                }
            });
            final Device device = (Device) FamilyCreateEditActivity.this.p.get(i);
            if (device == null) {
                return null;
            }
            DeviceFactory.b(device.model, viewHolder.f15673a);
            viewHolder.c.setText(device.name);
            viewHolder.d.setText(device.desc);
            viewHolder.e.setVisibility(4);
            viewHolder.f.setVisibility(4);
            PluginDeviceDownloadItem a2 = PluginDeviceNavigateHelper.a().a(device.model);
            if (a2 != null) {
                if (a2.c.equals(PluginDeviceDownloadItem.Status.DOWNLOADING)) {
                    if (!FamilyCreateEditActivity.this.q.contains(Integer.valueOf(i))) {
                        FamilyCreateEditActivity.this.q.add(Integer.valueOf(i));
                    }
                    viewHolder.e.setVisibility(0);
                    viewHolder.f.setVisibility(0);
                    viewHolder.e.setPercent(a2.f19463a * 100.0f);
                    viewHolder.f.setText(String.format("%d%%", new Object[]{Integer.valueOf((int) (a2.f19463a * 100.0f))}));
                } else {
                    FamilyCreateEditActivity.this.q.remove(Integer.valueOf(i));
                    viewHolder.e.setVisibility(4);
                    viewHolder.f.setVisibility(4);
                }
            }
            if (FamilyCreateEditActivity.this.v) {
                viewHolder.b.setVisibility(0);
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ((ViewHolder) view.getTag()).b.performClick();
                    }
                });
                if (FamilyCreateEditActivity.this.w.get(i)) {
                    viewHolder.b.setChecked(true);
                } else {
                    viewHolder.b.setChecked(false);
                }
            } else {
                viewHolder.b.setVisibility(8);
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(final View view) {
                        if (device.isConnected && view.isEnabled()) {
                            view.setEnabled(false);
                            FamilyCreateEditActivity.this.mHandler.postDelayed(new Runnable() {
                                public void run() {
                                    view.setEnabled(true);
                                }
                            }, 500);
                            PluginDeviceNavigateHelper.a().a(FamilyCreateEditActivity.this, device.did, (Intent) null);
                            FamilyCreateEditActivity.this.q.add(Integer.valueOf(i));
                        }
                    }
                });
                view.setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View view) {
                        if (!FamilyCreateEditActivity.this.v && !FamilyCreateEditActivity.this.d.isRefreshing()) {
                            if (!FamilyCreateEditActivity.this.w.get(i)) {
                                ((ViewHolder) view.getTag()).b.performClick();
                            }
                            FamilyCreateEditActivity.this.showDeleteBars();
                            boolean unused = FamilyCreateEditActivity.this.v = true;
                            FamilyCreateEditActivity.this.d.setCanPullDown(false);
                            FamilyDeviceAdapter.this.notifyDataSetChanged();
                        }
                        return true;
                    }
                });
            }
            ((ListItemView) view).setPosition(i);
            return view;
        }
    }
}
