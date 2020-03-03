package com.xiaomi.smarthome.miio.page;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.family.FamilyActivity;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.CustomPullDownRefreshListView;
import com.xiaomi.smarthome.library.common.widget.PullDownDragListView;
import com.xiaomi.smarthome.library.common.widget.viewflow.LoadingMoreView;
import com.xiaomi.smarthome.messagecenter.AllTypeMsgManager;
import com.xiaomi.smarthome.messagecenter.HomeShareMessageManager;
import com.xiaomi.smarthome.messagecenter.IMessage;
import com.xiaomi.smarthome.messagecenter.IMessageManager;
import com.xiaomi.smarthome.messagecenter.ShopMessageManager;
import com.xiaomi.smarthome.messagecenter.TypeListMsgCacheManagerV2;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.db.record.MessageRecord;
import com.xiaomi.smarthome.miio.db.record.MessageRecordTypeList;
import com.xiaomi.smarthome.miio.page.MessageCenterListActivity;
import com.xiaomi.smarthome.miio.page.deviceshare.ShareDeviceInfoActivity;
import com.xiaomi.smarthome.newui.MultiHomeManagerActivity;
import com.xiaomi.smarthome.shop.analytics.MIOTStat;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class MessageCenterListActivity extends BaseActivity {
    public static final String INTENT_KEY_MESSAGE_TYPE = "message_type";
    public static final String INTENT_KEY_TITLE = "message_title";
    static final String MESSAGE_LAST_UPDATE = "last_update_time";
    private static final String r = "MessageCenterListActivi";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public PullDownDragListView f19577a;
    /* access modifiers changed from: private */
    public View b;
    /* access modifiers changed from: private */
    public int c = -1;
    /* access modifiers changed from: private */
    public List<IMessage> d = new ArrayList();
    private View e;
    private ImageView f;
    /* access modifiers changed from: private */
    public ImageView g;
    private View h;
    private TextView i;
    private TextView j;
    private View k;
    /* access modifiers changed from: private */
    public TextView l;
    /* access modifiers changed from: private */
    public LoadingMoreView m;
    SimpleAdapterNew mAdapter;
    boolean mHasMoreData = true;
    long mLastUpdataTime;
    ImageView mMoreBtn;
    XQProgressDialog mProcessDialog;
    long mTimeStamp = 0;
    /* access modifiers changed from: private */
    public boolean n = false;
    private String o;
    /* access modifiers changed from: private */
    public Handler p = new Handler();
    /* access modifiers changed from: private */
    public AllTypeMsgManager.DataloadListener q = new AllTypeMsgManager.DataloadListener() {
        public void a(int i) {
            MessageCenterListActivity.this.v.clear();
            if (MessageCenterListActivity.this.d.size() == 0) {
                MessageCenterListActivity.this.b.setVisibility(0);
                MessageCenterListActivity.this.f19577a.setVisibility(8);
                MessageCenterListActivity.this.hideDeleteBars();
                boolean unused = MessageCenterListActivity.this.u = false;
            } else {
                MessageCenterListActivity.this.b.setVisibility(8);
                MessageCenterListActivity.this.f19577a.setVisibility(0);
                MessageCenterListActivity.this.f19577a.setOnScrollListener(new EndlessScrollListener() {
                    {
                        MessageCenterListActivity messageCenterListActivity = MessageCenterListActivity.this;
                    }

                    public boolean a(int i, int i2) {
                        return MessageCenterListActivity.this.a();
                    }
                });
            }
            MessageCenterListActivity.this.m.setVisibility(8);
            MessageCenterListActivity.this.f19577a.postRefresh();
            MessageCenterListActivity.this.mAdapter.notifyDataSetChanged();
        }

        public void a(List<IMessage> list) {
            if (list == null || list.size() == 0) {
                MessageCenterListActivity.this.m.setVisibility(8);
                boolean unused = MessageCenterListActivity.this.n = true;
                return;
            }
            MessageCenterListActivity.this.mAdapter.notifyDataSetChanged();
        }

        public void b(int i) {
            MessageCenterListActivity.this.v.clear();
            MessageCenterListActivity.this.mAdapter.notifyDataSetChanged();
            MessageCenterListActivity.this.f19577a.postRefresh();
            if (MessageCenterListActivity.this.d.size() == 0) {
                MessageCenterListActivity.this.b.setVisibility(0);
                MessageCenterListActivity.this.f19577a.setVisibility(8);
            } else {
                MessageCenterListActivity.this.b.setVisibility(8);
                MessageCenterListActivity.this.f19577a.setVisibility(0);
            }
            if (i == 10) {
                MessageCenterListActivity.this.f19577a.postRefresh();
            }
        }

        public void c(int i) {
            MessageCenterListActivity.this.m.displayError((int) R.string.loading_more_error, (View.OnClickListener) new View.OnClickListener() {
                public void onClick(View view) {
                    boolean unused = MessageCenterListActivity.this.a();
                }
            });
        }
    };
    /* access modifiers changed from: private */
    public boolean s = false;
    private long t = 0;
    /* access modifiers changed from: private */
    public boolean u = false;
    /* access modifiers changed from: private */
    public SparseBooleanArray v = new SparseBooleanArray();

    public static class ShareData {

        /* renamed from: a  reason: collision with root package name */
        public List<MessageRecord> f19601a;
        public long b;
        public int c;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.message_center);
        Intent intent = getIntent();
        if (intent != null) {
            this.c = intent.getIntExtra("message_type", -1);
        }
        if (this.c == -1) {
            finish();
            return;
        }
        this.o = intent.getStringExtra(INTENT_KEY_TITLE);
        initViews();
        this.mLastUpdataTime = PreferenceManager.getDefaultSharedPreferences(this).getLong(MESSAGE_LAST_UPDATE, 0);
        this.mHasMoreData = false;
        this.mTimeStamp = 0;
        this.f19577a.doRefresh();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.mHasMoreData = false;
        this.mTimeStamp = 0;
        startLoad();
        this.f19577a.doRefresh();
    }

    /* access modifiers changed from: package-private */
    public void initViews() {
        this.f19577a = (PullDownDragListView) findViewById(R.id.share_message_list);
        this.m = new LoadingMoreView(this);
        this.f19577a.addFooterView(this.m);
        this.m.setVisibility(0);
        this.f19577a.setRefreshListener(new CustomPullDownRefreshListView.OnRefreshListener() {
            public void startRefresh() {
                MessageCenterListActivity.this.mTimeStamp = 0;
                MessageCenterListActivity.this.startLoad();
            }
        });
        this.f19577a.addHeaderView(LayoutInflater.from(this).inflate(R.layout.common_list_space_empty, this.f19577a, false));
        this.b = findViewById(R.id.common_white_empty_view);
        this.b.setVisibility(8);
        ((TextView) findViewById(R.id.common_white_empty_text)).setText(R.string.miio_no_message);
        if (!TextUtils.isEmpty(this.o)) {
            ((TextView) findViewById(R.id.module_a_3_return_title)).setText(this.o);
        } else {
            ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.miio_setting_message_center);
        }
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PreferenceUtils.b(SettingMainPageV2.b, true);
                PreferenceUtils.b("my_home_red_dot_clicked", true);
                PreferenceUtils.a(SHApplication.getAppContext(), SettingMainPageV2.c, 0);
                MessageCenterListActivity.this.finish();
            }
        });
        this.mMoreBtn = (ImageView) findViewById(R.id.module_a_3_return_more_more_btn);
        this.mMoreBtn.setVisibility(8);
        this.mMoreBtn.setImageResource(R.drawable.common_title_bar_clear);
        this.mMoreBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MessageCenterListActivity.this.d.size() != 0) {
                    new MLAlertDialog.Builder(MessageCenterListActivity.this).b((CharSequence) MessageCenterListActivity.this.getResources().getString(R.string.log_clear_all_logs)).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            long j = 0;
                            for (IMessage next : AllTypeMsgManager.a().f()) {
                                if (next.a() > j) {
                                    j = next.a();
                                }
                            }
                            RemoteFamilyApi.a().c((Context) MessageCenterListActivity.this, j + 1, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                                public void onFailure(Error error) {
                                }

                                /* renamed from: a */
                                public void onSuccess(Void voidR) {
                                    MessageRecord.deleteAll();
                                    MessageCenterListActivity.this.startLoad();
                                }
                            });
                        }
                    }).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).b().show();
                }
            }
        });
        this.mAdapter = new SimpleAdapterNew();
        this.f19577a.setAdapter(this.mAdapter);
        this.k = findViewById(R.id.title_bar);
        this.e = findViewById(R.id.top_delete_bar);
        this.l = (TextView) this.e.findViewById(R.id.selected_cnt);
        this.f = (ImageView) this.e.findViewById(R.id.cancel_btn);
        this.f.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MessageCenterListActivity.this.dismissEditMode();
            }
        });
        this.g = (ImageView) this.e.findViewById(R.id.select_all_btn);
        this.g.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean z = true;
                if (MessageCenterListActivity.this.v.size() == MessageCenterListActivity.this.d.size()) {
                    MessageCenterListActivity.this.unSelectAll();
                    StatHelper.a(false);
                } else {
                    MessageCenterListActivity.this.selectAll();
                    StatHelper.a(true);
                }
                MessageCenterListActivity messageCenterListActivity = MessageCenterListActivity.this;
                if (MessageCenterListActivity.this.v.size() != MessageCenterListActivity.this.d.size()) {
                    z = false;
                }
                boolean unused = messageCenterListActivity.s = z;
                Log.e(MessageCenterListActivity.r, "is select all : " + MessageCenterListActivity.this.s);
            }
        });
        this.h = findViewById(R.id.bottom_delete_bar);
        this.h.setVisibility(0);
        this.i = (TextView) this.h.findViewById(R.id.delete_msg_btn);
        this.i.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MessageCenterListActivity.this.deleteSelected();
                StatHelper.E();
            }
        });
        this.i.setVisibility(4);
        this.j = (TextView) this.h.findViewById(R.id.btm_tip_tv);
        this.j.setVisibility(0);
        if (this.c == 1) {
            this.j.setText(R.string.msg_center_list_btm_tips_device_share);
        } else if (this.c == 5) {
            this.j.setText(R.string.msg_center_list_btm_tips_family);
        } else if (this.c == 8) {
            this.j.setText(R.string.msg_center_list_btm_tips_home_share);
        }
        this.j = (TextView) this.h.findViewById(R.id.btm_tip_tv);
        this.j.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MessageCenterListActivity.this.c == 1) {
                    MIOTStat.Log(MIOTStat.TOUCH, "msg_center_list_share");
                    if (CoreApi.a().q()) {
                        Intent intent = new Intent();
                        intent.setClass(MessageCenterListActivity.this, ShareDeviceInfoActivity.class);
                        intent.putExtra("user_id", CoreApi.a().s());
                        MessageCenterListActivity.this.startActivity(intent);
                        return;
                    }
                    SHApplication.showLoginDialog(MessageCenterListActivity.this, false);
                } else if (MessageCenterListActivity.this.c == 5) {
                    MIOTStat.Log(MIOTStat.TOUCH, "msg_center_list_family");
                    if (CoreApi.a().q()) {
                        Intent intent2 = new Intent();
                        intent2.setClass(MessageCenterListActivity.this, FamilyActivity.class);
                        MessageCenterListActivity.this.startActivity(intent2);
                        return;
                    }
                    SHApplication.showLoginDialog(MessageCenterListActivity.this, false);
                } else if (MessageCenterListActivity.this.c != 8) {
                } else {
                    if (CoreApi.a().q()) {
                        Intent intent3 = new Intent(MessageCenterListActivity.this, MultiHomeManagerActivity.class);
                        intent3.putExtra("from", 3);
                        MessageCenterListActivity.this.startActivity(intent3);
                        return;
                    }
                    SHApplication.showLoginDialog(MessageCenterListActivity.this, false);
                }
            }
        });
        if (TitleBarUtil.f11582a) {
            TitleBarUtil.a(TitleBarUtil.a(), this.e);
        }
    }

    public void showDeleteBars() {
        this.e.setVisibility(0);
        this.h.setVisibility(0);
        this.i.setVisibility(0);
        this.j.setVisibility(8);
        this.e.measure(0, 0);
        this.h.measure(0, 0);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.e, View.TRANSLATION_Y, new float[]{(float) (-this.e.getMeasuredHeight()), 0.0f});
        ViewGroup viewGroup = (ViewGroup) this.h.getParent();
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.h, View.Y, new float[]{(float) viewGroup.getHeight(), (float) (viewGroup.getHeight() - this.h.getMeasuredHeight())});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(200);
        animatorSet.play(ofFloat).with(ofFloat2);
        animatorSet.start();
    }

    public void hideDeleteBars() {
        this.e.setVisibility(8);
        this.h.setVisibility(0);
        this.i.setVisibility(4);
        this.j.setVisibility(0);
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
    }

    /* access modifiers changed from: private */
    public boolean a() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.t < 800) {
            return false;
        }
        this.t = currentTimeMillis;
        IMessage iMessage = (IMessage) this.mAdapter.getItem(this.mAdapter.getCount() - 1);
        if (iMessage == null || this.n || this.u) {
            return false;
        }
        this.m.setVisibility(0);
        this.m.displayLoding();
        RemoteFamilyApi.a().a((Context) this, this.c, iMessage.a() - 1, 100, (AsyncCallback<List<MessageRecordTypeList>, Error>) new AsyncCallback<List<MessageRecordTypeList>, Error>() {
            /* renamed from: a */
            public void onSuccess(List<MessageRecordTypeList> list) {
                if (MessageCenterListActivity.this.isValid()) {
                    MessageCenterListActivity.this.p.post(new Runnable() {
                        public void run() {
                            if (MessageCenterListActivity.this.isValid()) {
                                MessageCenterListActivity.this.m.setVisibility(8);
                                MessageCenterListActivity.this.q.a(MessageCenterListActivity.this.d.size());
                            }
                        }
                    });
                    List<IMessage> a2 = TypeListMsgCacheManagerV2.a().a(list);
                    if (a2 == null || a2.isEmpty()) {
                        boolean unused = MessageCenterListActivity.this.n = true;
                    } else {
                        MessageCenterListActivity.this.d.addAll(a2);
                    }
                }
            }

            public void onFailure(Error error) {
                String str;
                if (MessageCenterListActivity.this.isValid()) {
                    MessageCenterListActivity.this.q.c(0);
                    StringBuilder sb = new StringBuilder();
                    sb.append(R.string.failed_to_load);
                    if (!GlobalSetting.q || error == null) {
                        str = "";
                    } else {
                        str = ":" + error.a() + ":" + error.b();
                    }
                    sb.append(str);
                    ToastUtil.a((CharSequence) sb.toString());
                    MessageCenterListActivity.this.p.post(new Runnable() {
                        public void run() {
                            MessageCenterListActivity.this.m.displayError((int) R.string.loading_more_error, (View.OnClickListener) new View.OnClickListener() {
                                public void onClick(View view) {
                                    boolean unused = MessageCenterListActivity.this.a();
                                }
                            });
                        }
                    });
                }
            }
        });
        return true;
    }

    /* access modifiers changed from: package-private */
    public void startLoad() {
        if (this.mHasMoreData || this.mTimeStamp <= 0) {
            HashSet hashSet = new HashSet();
            hashSet.add(this.c + "");
            RemoteFamilyApi.a().a((Context) this, this.c, 0, 100, (AsyncCallback<List<MessageRecordTypeList>, Error>) new AsyncCallback<List<MessageRecordTypeList>, Error>() {
                /* renamed from: a */
                public void onSuccess(List<MessageRecordTypeList> list) {
                    if (MessageCenterListActivity.this.isValid()) {
                        List<IMessage> a2 = TypeListMsgCacheManagerV2.a().a(list);
                        if (a2 == null || a2.isEmpty()) {
                            List unused = MessageCenterListActivity.this.d = new ArrayList();
                        } else {
                            List unused2 = MessageCenterListActivity.this.d = a2;
                        }
                        MessageCenterListActivity.this.p.post(new Runnable() {
                            public void run() {
                                if (MessageCenterListActivity.this.isValid()) {
                                    MessageCenterListActivity.this.q.a(MessageCenterListActivity.this.d.size());
                                }
                            }
                        });
                    }
                }

                public void onFailure(Error error) {
                    String str;
                    if (MessageCenterListActivity.this.isValid()) {
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
            return;
        }
        this.f19577a.postRefresh();
    }

    private List<IMessage> a(List<MessageRecord> list) {
        IMessage a2;
        ArrayList arrayList = new ArrayList();
        Map<String, IMessageManager> d2 = AllTypeMsgManager.a().d();
        IMessageManager iMessageManager = d2.get(this.c + "");
        for (MessageRecord next : list) {
            if (!(iMessageManager == null || (a2 = iMessageManager.a(next)) == null)) {
                arrayList.add(a2);
            }
        }
        return arrayList;
    }

    public void dismissEditMode() {
        this.f19577a.setPullDownEnabled(true);
        this.u = false;
        this.v.clear();
        hideDeleteBars();
        this.s = false;
        this.v.clear();
        this.mAdapter.notifyDataSetChanged();
    }

    public void selectAll() {
        int size = this.d.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.v.put(i2, true);
        }
        this.g.setImageResource(R.drawable.un_select_selector);
        this.mAdapter.notifyDataSetChanged();
        this.l.setVisibility(0);
        this.l.setText(getResources().getQuantityString(R.plurals.selected_cnt_tips, this.v.size(), new Object[]{Integer.valueOf(this.v.size())}));
    }

    public void unSelectAll() {
        this.v.clear();
        this.g.setImageResource(R.drawable.all_select_selector);
        this.mAdapter.notifyDataSetChanged();
        this.l.setVisibility(8);
    }

    public void deleteSelected() {
        if (this.v.size() == 0) {
            ToastUtil.a((Context) this, (int) R.string.not_select_deleted_msg);
            return;
        }
        new MLAlertDialog.Builder(this).a((int) R.string.delete_msg_title).b((CharSequence) getResources().getQuantityString(R.plurals.delete_msg, this.v.size(), new Object[]{Integer.valueOf(this.v.size())})).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).a((int) R.string.confirm_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                final ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < MessageCenterListActivity.this.v.size(); i2++) {
                    int keyAt = MessageCenterListActivity.this.v.keyAt(i2);
                    if (MessageCenterListActivity.this.v.get(keyAt) && MessageCenterListActivity.this.d.size() > keyAt) {
                        try {
                            arrayList.add(((IMessage) MessageCenterListActivity.this.d.get(keyAt)).c());
                        } catch (Exception unused) {
                        }
                    }
                }
                RemoteFamilyApi.a().a((Context) MessageCenterListActivity.this, MessageCenterListActivity.this.c, (String) null, (String[]) arrayList.toArray(new String[0]), (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                    public void onFailure(Error error) {
                    }

                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        MessageCenterListActivity.this.v.clear();
                        MessageRecord.batchDelete(arrayList);
                        MessageCenterListActivity.this.startLoad();
                        MessageCenterListActivity.this.l.setVisibility(8);
                        MessageCenterListActivity.this.g.setImageResource(R.drawable.all_select_selector);
                        MessageCenterListActivity.this.f19577a.setSelectionAfterHeaderView();
                        boolean unused = MessageCenterListActivity.this.n = false;
                    }
                });
            }
        }).b().show();
    }

    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (i2 != 4 || keyEvent.getRepeatCount() != 0 || !this.u) {
            return super.onKeyUp(i2, keyEvent);
        }
        dismissEditMode();
        return true;
    }

    class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        View f19607a;
        SimpleDraweeView b;
        TextView c;
        CheckBox d;
        TextView e;
        TextView f;
        View g;

        ViewHolder() {
        }
    }

    class SimpleAdapterNew extends BaseAdapter {
        public long getItemId(int i) {
            return 0;
        }

        SimpleAdapterNew() {
        }

        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            if (MessageCenterListActivity.this.u) {
                MessageCenterListActivity.this.p.post(new Runnable() {
                    public final void run() {
                        MessageCenterListActivity.SimpleAdapterNew.this.a();
                    }
                });
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a() {
            if (MessageCenterListActivity.this.u && MessageCenterListActivity.this.s) {
                MessageCenterListActivity.this.l.setVisibility(0);
                MessageCenterListActivity.this.l.setText(MessageCenterListActivity.this.getResources().getQuantityString(R.plurals.selected_cnt_tips, MessageCenterListActivity.this.d.size(), new Object[]{Integer.valueOf(MessageCenterListActivity.this.d.size())}));
            } else if (MessageCenterListActivity.this.u) {
                MessageCenterListActivity.this.l.setVisibility(0);
                MessageCenterListActivity.this.l.setText(MessageCenterListActivity.this.getResources().getQuantityString(R.plurals.selected_cnt_tips, MessageCenterListActivity.this.v.size(), new Object[]{Integer.valueOf(MessageCenterListActivity.this.v.size())}));
            }
        }

        public int getCount() {
            return MessageCenterListActivity.this.d.size();
        }

        public Object getItem(int i) {
            if (i < 0 || i >= MessageCenterListActivity.this.d.size()) {
                return null;
            }
            return MessageCenterListActivity.this.d.get(i);
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(MessageCenterListActivity.this).inflate(R.layout.message_item_new, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.b = (SimpleDraweeView) view.findViewById(R.id.device_icon);
                viewHolder.b.setHierarchy(new GenericDraweeHierarchyBuilder(viewHolder.b.getResources()).setFadeDuration(200).setActualImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).setPlaceholderImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).build());
                viewHolder.c = (TextView) view.findViewById(R.id.right_tv);
                viewHolder.d = (CheckBox) view.findViewById(R.id.ratio_btn);
                viewHolder.e = (TextView) view.findViewById(R.id.device_item);
                viewHolder.f = (TextView) view.findViewById(R.id.device_item_info);
                viewHolder.f19607a = view.findViewById(R.id.right_fl);
                viewHolder.g = view.findViewById(R.id.new_message_tag);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            final IMessage iMessage = (IMessage) MessageCenterListActivity.this.d.get(i);
            iMessage.a(viewHolder.e);
            iMessage.b(viewHolder.f);
            iMessage.a(viewHolder.b);
            if (iMessage.h()) {
                viewHolder.e.setTextColor(MessageCenterListActivity.this.getResources().getColor(R.color.black_80_transparent));
                viewHolder.f.setTextColor(MessageCenterListActivity.this.getResources().getColor(R.color.black_50_transparent));
            } else {
                viewHolder.e.setTextColor(MessageCenterListActivity.this.getResources().getColor(R.color.class_D));
                viewHolder.f.setTextColor(MessageCenterListActivity.this.getResources().getColor(R.color.class_D));
            }
            view.findViewById(R.id.arrow).setVisibility(8);
            viewHolder.d.setClickable(false);
            viewHolder.d.setFocusable(false);
            viewHolder.c.setOnClickListener((View.OnClickListener) null);
            if (MessageCenterListActivity.this.u) {
                viewHolder.f19607a.setVisibility(0);
                viewHolder.d.setVisibility(0);
                viewHolder.c.setVisibility(8);
                if (MessageCenterListActivity.this.s) {
                    MessageCenterListActivity.this.v.put(i, true);
                }
                if (MessageCenterListActivity.this.v.get(i)) {
                    viewHolder.d.setChecked(true);
                } else {
                    viewHolder.d.setChecked(false);
                }
            } else {
                viewHolder.f19607a.setVisibility(0);
                viewHolder.d.setVisibility(8);
                viewHolder.c.setVisibility(0);
                if (!iMessage.b()) {
                    viewHolder.c.setTextColor(MessageCenterListActivity.this.getResources().getColor(R.color.class_D));
                    float f = MessageCenterListActivity.this.getResources().getDisplayMetrics().density;
                    int i2 = (int) (15.0f * f);
                    int i3 = (int) (f * 7.0f);
                    viewHolder.c.setPadding(i2, i3, i2, i3);
                    viewHolder.c.setBackgroundResource(0);
                    iMessage.c(viewHolder.c);
                    if (viewHolder.c.getVisibility() == 8) {
                        viewHolder.f19607a.setVisibility(8);
                    }
                } else {
                    viewHolder.c.setTextColor(MessageCenterListActivity.this.getResources().getColorStateList(R.color.selector_common_text));
                    viewHolder.c.setBackgroundResource(R.drawable.selector_common_btn);
                    float f2 = MessageCenterListActivity.this.getResources().getDisplayMetrics().density;
                    int i4 = (int) (15.0f * f2);
                    int i5 = (int) (f2 * 7.0f);
                    viewHolder.c.setPadding(i4, i5, i4, i5);
                    viewHolder.c.setText(R.string.smarthome_share_accept);
                    viewHolder.c.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            MessageCenterListActivity.this.mProcessDialog = new XQProgressDialog(MessageCenterListActivity.this);
                            MessageCenterListActivity.this.mProcessDialog.setCancelable(false);
                            MessageCenterListActivity.this.mProcessDialog.setMessage(MessageCenterListActivity.this.getResources().getString(R.string.loading_share_info));
                            MessageCenterListActivity.this.mProcessDialog.show();
                            MessageCenterListActivity.this.mProcessDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                public void onDismiss(DialogInterface dialogInterface) {
                                    try {
                                        MessageCenterListActivity.this.startLoad();
                                        if (iMessage instanceof HomeShareMessageManager.HomeShareMessage) {
                                            MessageCenterListActivity.this.a(String.valueOf(((HomeShareMessageManager.HomeShareMessage) iMessage).d()));
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                            iMessage.a(MessageCenterListActivity.this.mProcessDialog);
                        }
                    });
                }
            }
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (MessageCenterListActivity.this.u) {
                        ((CheckBox) view.findViewById(R.id.ratio_btn)).toggle();
                        if (!MessageCenterListActivity.this.v.get(i)) {
                            MessageCenterListActivity.this.v.put(i, true);
                        } else {
                            MessageCenterListActivity.this.v.delete(i);
                        }
                        if (MessageCenterListActivity.this.v.size() == MessageCenterListActivity.this.d.size()) {
                            MessageCenterListActivity.this.g.setImageResource(R.drawable.un_select_selector);
                        } else {
                            MessageCenterListActivity.this.g.setImageResource(R.drawable.all_select_selector);
                        }
                        if (MessageCenterListActivity.this.v.size() > 0) {
                            MessageCenterListActivity.this.l.setVisibility(0);
                            MessageCenterListActivity.this.l.setText(MessageCenterListActivity.this.getResources().getQuantityString(R.plurals.selected_cnt_tips, MessageCenterListActivity.this.v.size(), new Object[]{Integer.valueOf(MessageCenterListActivity.this.v.size())}));
                        } else {
                            MessageCenterListActivity.this.l.setVisibility(0);
                            MessageCenterListActivity.this.l.setText(MessageCenterListActivity.this.getString(R.string.selected_0_cnt_tips));
                        }
                        boolean unused = MessageCenterListActivity.this.s = false;
                        return;
                    }
                    ((IMessage) MessageCenterListActivity.this.d.get(i)).a((Activity) MessageCenterListActivity.this);
                    if (MessageCenterListActivity.this.d.get(i) instanceof ShopMessageManager.ShopMessage) {
                        MIOTStat.Log(MIOTStat.TOUCH, "message");
                    }
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View view) {
                    if (MessageCenterListActivity.this.u) {
                        return false;
                    }
                    MessageCenterListActivity.this.f19577a.setPullDownEnabled(false);
                    MessageCenterListActivity.this.showDeleteBars();
                    boolean unused = MessageCenterListActivity.this.u = true;
                    MessageCenterListActivity.this.v.put(i, true);
                    SimpleAdapterNew.this.notifyDataSetChanged();
                    StatHelper.D();
                    return true;
                }
            });
            return view;
        }
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        final Home j2 = HomeManager.a().j(str);
        if (j2 != null) {
            new MLAlertDialog.Builder(this).b((CharSequence) String.format(getString(R.string.home_share_response_success1), new Object[]{j2.k()})).a((int) R.string.view_now, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    MessageCenterListActivity.this.mProcessDialog.show();
                    MessageCenterListActivity.this.mProcessDialog.setOnDismissListener((DialogInterface.OnDismissListener) null);
                    HomeManager.a().a(j2.j(), (AsyncCallback) new AsyncCallback() {
                        public void onSuccess(Object obj) {
                            if (MessageCenterListActivity.this.isValid()) {
                                if (MessageCenterListActivity.this.mProcessDialog != null) {
                                    MessageCenterListActivity.this.mProcessDialog.dismiss();
                                }
                                Intent intent = new Intent(MessageCenterListActivity.this, SmartHomeMainActivity.class);
                                intent.setFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                                MessageCenterListActivity.this.startActivity(intent);
                                STAT.d.a(2, 1);
                            }
                        }

                        public void onFailure(Error error) {
                            if (MessageCenterListActivity.this.mProcessDialog != null) {
                                MessageCenterListActivity.this.mProcessDialog.dismiss();
                            }
                            STAT.d.aK();
                        }
                    });
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).d();
        }
    }

    public abstract class EndlessScrollListener implements AbsListView.OnScrollListener {

        /* renamed from: a  reason: collision with root package name */
        private int f19600a = 5;
        private int c = 0;
        private int d = 0;
        private boolean e = true;
        private int f = 0;

        public abstract boolean a(int i, int i2);

        public EndlessScrollListener() {
        }

        public EndlessScrollListener(int i) {
            this.f19600a = i;
        }

        public EndlessScrollListener(int i, int i2) {
            this.f19600a = i;
            this.f = i2;
            this.c = i2;
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (i3 < this.d) {
                this.c = this.f;
                this.d = i3;
                if (i3 == 0) {
                    this.e = true;
                }
            }
            if (this.e && i3 > this.d) {
                this.e = false;
                this.d = i3;
                this.c++;
            }
            if (!MessageCenterListActivity.this.n && !this.e && absListView.getLastVisiblePosition() == ((ListAdapter) absListView.getAdapter()).getCount() - 1 && absListView.getChildAt(absListView.getChildCount() - 1).getBottom() <= absListView.getHeight()) {
                this.e = a(this.c + 1, i3);
            }
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            View currentFocus;
            if (1 == i && (currentFocus = MessageCenterListActivity.this.getCurrentFocus()) != null) {
                currentFocus.clearFocus();
            }
        }
    }
}
