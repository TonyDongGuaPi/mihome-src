package com.xiaomi.smarthome.messagecenter.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.redpoint.ProfileRedPointManager;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.viewflow.LoadingMoreView;
import com.xiaomi.smarthome.messagecenter.AllTypeMsgManager;
import com.xiaomi.smarthome.messagecenter.FamilyMessageManager;
import com.xiaomi.smarthome.messagecenter.IMessage;
import com.xiaomi.smarthome.messagecenter.NewMsgLocalHelper;
import com.xiaomi.smarthome.messagecenter.ShareMessageManager;
import com.xiaomi.smarthome.messagecenter.TypeListMsgCacheManagerV2;
import com.xiaomi.smarthome.messagecenter.shopmessage.ShopMessageManagerV2;
import com.xiaomi.smarthome.messagecenter.shopmessage.ShopTypeMsgManager;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.db.record.MessageRecord;
import com.xiaomi.smarthome.miio.page.SettingMainPageV2;
import com.xiaomi.smarthome.miio.page.msgcentersetting.MessageCenterSettingActivity;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MessageCenterActivity extends BaseActivity {
    static final String MESSAGE_LAST_UPDATE = "last_update_time";

    /* renamed from: a  reason: collision with root package name */
    private View f10506a;
    /* access modifiers changed from: private */
    public List<IMessage> b = new ArrayList();
    private View c;
    private ImageView d;
    /* access modifiers changed from: private */
    public ImageView e;
    private View f;
    private TextView g;
    private View h;
    /* access modifiers changed from: private */
    public TextView i;
    private LoadingMoreView j;
    /* access modifiers changed from: private */
    public boolean k = false;
    private View l;
    private View m;
    MessageCenterAdapter mAdapter;
    boolean mHasMoreData = true;
    long mLastUpdateTime;
    ImageView mMoreBtn;
    XQProgressDialog mProcessDialog;
    long mTimeStamp = 0;
    private View n;
    private RecyclerView o;
    /* access modifiers changed from: private */
    public PtrFrameLayout p;
    private Map<String, Integer> q = new HashMap();
    private boolean r = false;
    /* access modifiers changed from: private */
    public Set<String> s = new HashSet();

    private void a() {
    }

    private void b() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.message_center_v4);
        initViews();
        this.mLastUpdateTime = PreferenceManager.getDefaultSharedPreferences(this).getLong(MESSAGE_LAST_UPDATE, 0);
        this.mHasMoreData = false;
        this.mTimeStamp = 0;
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                MessageCenterActivity.this.p.autoRefresh();
            }
        }, 100);
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.mHasMoreData = false;
        this.mTimeStamp = 0;
        c();
    }

    /* access modifiers changed from: package-private */
    public void initViews() {
        this.j = new LoadingMoreView(this);
        this.j.setVisibility(8);
        this.o = (RecyclerView) findViewById(R.id.message_list);
        this.o.setLayoutManager(new LinearLayoutManager(this));
        this.mAdapter = new MessageCenterAdapter(this);
        this.o.setAdapter(this.mAdapter);
        this.f10506a = findViewById(R.id.common_white_empty_view);
        this.f10506a.setVisibility(8);
        ((TextView) findViewById(R.id.common_white_empty_text)).setText(R.string.miio_no_message);
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.miio_setting_message_center);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PreferenceUtils.b(SettingMainPageV2.b, true);
                PreferenceUtils.b("my_home_red_dot_clicked", true);
                PreferenceUtils.a(SHApplication.getAppContext(), SettingMainPageV2.c, 0);
                MessageCenterActivity.this.finish();
            }
        });
        this.mMoreBtn = (ImageView) findViewById(R.id.module_a_3_right_iv_setting_btn);
        this.mMoreBtn.setVisibility(0);
        this.mMoreBtn.setContentDescription(getString(R.string.message_center_setting_title));
        this.mMoreBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MessageCenterActivity.this.startActivity(new Intent(MessageCenterActivity.this, MessageCenterSettingActivity.class));
                StatHelper.I();
            }
        });
        this.h = findViewById(R.id.title_bar);
        this.c = findViewById(R.id.top_delete_bar);
        this.i = (TextView) this.c.findViewById(R.id.selected_cnt);
        this.d = (ImageView) this.c.findViewById(R.id.cancel_btn);
        this.d.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MessageCenterActivity.this.dismissEditMode();
            }
        });
        this.e = (ImageView) this.c.findViewById(R.id.select_all_btn);
        this.e.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MessageCenterActivity.this.s.size() == MessageCenterActivity.this.b.size()) {
                    MessageCenterActivity.this.unSelectAll();
                    StatHelper.a(false);
                    return;
                }
                MessageCenterActivity.this.selectAll();
                StatHelper.a(true);
            }
        });
        this.f = findViewById(R.id.bottom_delete_bar);
        this.g = (TextView) this.f.findViewById(R.id.delete_msg_btn);
        this.g.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MessageCenterActivity.this.deleteSelected();
                StatHelper.E();
            }
        });
        if (TitleBarUtil.f11582a) {
            TitleBarUtil.a(TitleBarUtil.a(), this.c);
        }
        this.l = LayoutInflater.from(this).inflate(R.layout.message_center_group_item, (ViewGroup) null);
        this.m = LayoutInflater.from(this).inflate(R.layout.message_center_group_item, (ViewGroup) null);
        this.n = LayoutInflater.from(this).inflate(R.layout.message_center_group_item, (ViewGroup) null);
        this.n.findViewById(R.id.content).setBackgroundResource(R.drawable.common_white_list_padding_no_left_margin);
        this.p = (PtrFrameLayout) findViewById(R.id.pull_down_refresh);
        this.p.setPtrHandler(new PtrHandler() {
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
                return PtrDefaultHandler.checkContentCanBePulledDown(ptrFrameLayout, view, view2);
            }

            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                MessageCenterActivity.this.c();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.p != null) {
            this.p.autoRefresh();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    public void showDeleteBars() {
        this.c.setVisibility(0);
        this.f.setVisibility(0);
        this.c.measure(0, 0);
        this.f.measure(0, 0);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.c, View.Y, new float[]{(float) (-this.c.getMeasuredHeight()), 0.0f});
        ViewGroup viewGroup = (ViewGroup) this.f.getParent();
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.f, View.Y, new float[]{(float) viewGroup.getHeight(), (float) (viewGroup.getHeight() - this.f.getMeasuredHeight())});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(200);
        animatorSet.play(ofFloat).with(ofFloat2);
        animatorSet.start();
    }

    public void hideDeleteBars() {
        this.c.setVisibility(8);
        this.f.setVisibility(8);
    }

    public void onBackPressed() {
        try {
            super.onBackPressed();
            PreferenceUtils.b(SettingMainPageV2.b, true);
            PreferenceUtils.b("my_home_red_dot_clicked", true);
            PreferenceUtils.a(SHApplication.getAppContext(), SettingMainPageV2.c, 0);
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        NewMsgLocalHelper.a();
        PreferenceUtils.a((Context) this, ProfileRedPointManager.e + CoreApi.a().s(), (System.currentTimeMillis() + ProfileRedPointManager.a().c()) / 1000);
    }

    private void a(long j2) {
        TypeListMsgCacheManagerV2.a().b(j2, new AsyncCallback<List<IMessage>, Error>() {
            /* renamed from: a */
            public void onSuccess(List<IMessage> list) {
                if (MessageCenterActivity.this.isValid()) {
                    MessageCenterActivity.this.mAdapter.b(list);
                }
            }

            public void onFailure(Error error) {
                String str;
                if (MessageCenterActivity.this.isValid()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(R.string.failed_to_load);
                    if (!GlobalSetting.q || error == null) {
                        str = "";
                    } else {
                        str = ":" + error.a() + ":" + error.b();
                    }
                    sb.append(str);
                    ToastUtil.a((CharSequence) sb.toString());
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void c() {
        TypeListMsgCacheManagerV2.a().a(System.currentTimeMillis(), (AsyncCallback<List<IMessage>, Error>) new AsyncCallback<List<IMessage>, Error>() {
            /* renamed from: a */
            public void onSuccess(List<IMessage> list) {
                if (MessageCenterActivity.this.isValid()) {
                    if (MessageCenterActivity.this.p.isRefreshing()) {
                        MessageCenterActivity.this.p.refreshComplete();
                    }
                    if (MessageCenterActivity.this.mAdapter != null) {
                        MessageCenterActivity.this.mAdapter.a(list);
                    }
                }
            }

            public void onFailure(Error error) {
                String str;
                if (MessageCenterActivity.this.isValid()) {
                    if (MessageCenterActivity.this.p.isRefreshing()) {
                        MessageCenterActivity.this.p.refreshComplete();
                    }
                    if (MessageCenterActivity.this.mAdapter != null) {
                        MessageCenterActivity.this.mAdapter.a(TypeListMsgCacheManagerV2.a().b());
                    }
                    if (!NetworkUtils.c()) {
                        ToastUtil.a((int) R.string.popup_select_loc_no_network);
                        return;
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append(R.string.failed_to_load);
                    if (!GlobalSetting.q || error == null) {
                        str = "";
                    } else {
                        str = ":" + error.a() + ":" + error.b();
                    }
                    sb.append(str);
                    ToastUtil.a((CharSequence) sb.toString());
                }
            }
        });
    }

    public void dismissEditMode() {
        this.r = false;
        this.s.clear();
        hideDeleteBars();
        this.mAdapter.notifyDataSetChanged();
    }

    public void selectAll() {
        List<IMessage> a2 = this.mAdapter.a();
        this.b = a2;
        if (a2 != null && !a2.isEmpty()) {
            for (int i2 = 0; i2 < a2.size(); i2++) {
                IMessage iMessage = a2.get(i2);
                if (iMessage != null) {
                    this.s.add(iMessage.c());
                }
            }
            this.e.setImageResource(R.drawable.un_select_selector);
            this.mAdapter.notifyDataSetChanged();
            this.i.setVisibility(0);
            this.i.setText(getResources().getQuantityString(R.plurals.selected_cnt_tips, this.s.size(), new Object[]{Integer.valueOf(this.s.size())}));
        }
    }

    public void setSelected(String str, boolean z) {
        if (z) {
            this.s.add(str);
        } else {
            this.s.remove(str);
        }
        if (this.s.size() == this.b.size()) {
            unSelectAll();
        } else {
            selectAll();
        }
    }

    public void unSelectAll() {
        this.s.clear();
        this.e.setImageResource(R.drawable.all_select_selector);
        this.mAdapter.notifyDataSetChanged();
        this.i.setVisibility(8);
    }

    public void deleteSelected() {
        if (this.s.size() == 0) {
            ToastUtil.a((Context) this, (int) R.string.not_select_deleted_msg);
            return;
        }
        new MLAlertDialog.Builder(this).a((int) R.string.delete_msg_title).b((CharSequence) getResources().getQuantityString(R.plurals.delete_msg, this.s.size(), new Object[]{Integer.valueOf(this.s.size())})).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).a((int) R.string.confirm_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                final ArrayList arrayList = new ArrayList();
                RemoteFamilyApi.a().a((Context) MessageCenterActivity.this, -1, (String) null, (String[]) MessageCenterActivity.this.s.toArray(new String[0]), (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        if (MessageCenterActivity.this.isValid()) {
                            MessageCenterActivity.this.s.clear();
                            MessageRecord.batchDelete(arrayList);
                            MessageCenterActivity.this.i.setVisibility(8);
                            MessageCenterActivity.this.e.setImageResource(R.drawable.all_select_selector);
                            boolean unused = MessageCenterActivity.this.k = false;
                        }
                    }

                    public void onFailure(Error error) {
                        String str;
                        if (MessageCenterActivity.this.isValid()) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(R.string.delete_failed);
                            if (!GlobalSetting.q || error == null) {
                                str = "";
                            } else {
                                str = ":" + error.a() + ":" + error.b();
                            }
                            sb.append(str);
                            ToastUtil.a((CharSequence) sb.toString());
                        }
                    }
                });
            }
        }).b().show();
    }

    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (i2 != 4 || keyEvent.getRepeatCount() != 0 || !this.r) {
            return super.onKeyUp(i2, keyEvent);
        }
        dismissEditMode();
        return true;
    }

    private void a(IMessage iMessage, View view) {
        iMessage.d((TextView) view.findViewById(R.id.device_item_info));
    }

    private void a(ShopMessageManagerV2.ShopMessageV2 shopMessageV2, View view) {
        shopMessageV2.a((TextView) view.findViewById(R.id.device_item_info));
    }

    private void d() {
        List<IMessage> g2 = AllTypeMsgManager.a().g();
        if (g2 != null) {
            for (IMessage next : g2) {
                if (next instanceof ShareMessageManager.ShareMessage) {
                    a(next, this.l);
                } else if (next instanceof FamilyMessageManager.FamilyMessage) {
                    a(next, this.m);
                }
            }
        }
        ShopMessageManagerV2.ShopMessageV2 d2 = ShopTypeMsgManager.a().d();
        if (d2 != null) {
            a(d2, this.n);
        }
    }

    public void setNumUpIcon(int i2, View view) {
        if (i2 == 0) {
            try {
                view.setVisibility(4);
            } catch (Throwable unused) {
            }
        } else {
            TextView textView = (TextView) view;
            textView.setVisibility(0);
            if (i2 <= 99) {
                textView.setText(i2 + "");
                return;
            }
            textView.setText("99+");
            textView.setBackgroundResource(R.drawable.red_point_num);
        }
    }
}
