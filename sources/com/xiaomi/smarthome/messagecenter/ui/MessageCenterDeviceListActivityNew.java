package com.xiaomi.smarthome.messagecenter.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.PullDownDragListView;
import com.xiaomi.smarthome.library.common.widget.viewflow.LoadingMoreView;
import com.xiaomi.smarthome.messagecenter.AllTypeMsgManager;
import com.xiaomi.smarthome.messagecenter.DevicePushRedpointHelper;
import com.xiaomi.smarthome.messagecenter.IMessage;
import com.xiaomi.smarthome.messagecenter.TypeListMsgCacheManager;
import com.xiaomi.smarthome.messagecenter.TypeListMsgCacheManagerV2;
import com.xiaomi.smarthome.miio.LanguageUtil;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.db.record.MessageRecord;
import com.xiaomi.smarthome.miio.db.record.MessageRecordTypeList;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageCenterDeviceListActivityNew extends BaseActivity {
    public static final String INTENT_KEY_DID = "device_id";
    public static final String INTENT_KEY_MODEL = "device_model";
    public static final String INTENT_KEY_TITLE = "title_name";
    static final String MESSAGE_LAST_UPDATE = "last_update_time";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final String f10541a = "MessageCenterDeviceListActivityNew";
    private static final int t = 7;
    /* access modifiers changed from: private */
    public PullDownDragListView b;
    private View c;
    /* access modifiers changed from: private */
    public String d;
    private String e;
    /* access modifiers changed from: private */
    public List<IMessage> f = new ArrayList();
    private View g;
    private ImageView h;
    /* access modifiers changed from: private */
    public ImageView i;
    private View j;
    private TextView k;
    private TextView l;
    private View m;
    SimpleAdapterNew mAdapter;
    long mLastUpdataTime;
    ImageView mMoreBtn;
    XQProgressDialog mProcessDialog;
    long mTimeStamp = 0;
    /* access modifiers changed from: private */
    public TextView n;
    private LoadingMoreView o;
    /* access modifiers changed from: private */
    public boolean p = false;
    private CharSequence q;
    private View r;
    /* access modifiers changed from: private */
    public int s = -1;
    /* access modifiers changed from: private */
    public boolean u = false;
    /* access modifiers changed from: private */
    public SparseBooleanArray v = new SparseBooleanArray();
    private boolean w = false;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.message_center_list);
        Intent intent = getIntent();
        if (intent != null) {
            this.d = intent.getStringExtra("device_id");
            this.e = intent.getStringExtra("device_model");
        }
        if (TextUtils.isEmpty(this.d) || TextUtils.isEmpty(this.e)) {
            finish();
            return;
        }
        this.q = intent.getStringExtra("title_name");
        initViews();
        this.mLastUpdataTime = PreferenceManager.getDefaultSharedPreferences(this).getLong(MESSAGE_LAST_UPDATE, 0);
        this.mTimeStamp = 0;
        startLoad();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.mTimeStamp = 0;
        startLoad();
    }

    /* access modifiers changed from: package-private */
    public void initViews() {
        this.b = (PullDownDragListView) findViewById(R.id.share_message_list);
        this.o = new LoadingMoreView(this);
        this.o.setVisibility(0);
        this.b.setPullDownEnabled(false);
        this.c = findViewById(R.id.common_white_empty_view);
        this.c.setVisibility(8);
        ((TextView) findViewById(R.id.common_white_empty_text)).setText(R.string.miio_no_message);
        if (!TextUtils.isEmpty(this.q)) {
            ((TextView) findViewById(R.id.module_a_3_return_title)).setText(this.q);
        } else {
            ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.miio_setting_message_center);
        }
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MessageCenterDeviceListActivityNew.this.a();
                MessageCenterDeviceListActivityNew.this.finish();
            }
        });
        this.mMoreBtn = (ImageView) findViewById(R.id.module_a_3_return_more_more_btn);
        this.mMoreBtn.setVisibility(8);
        this.mMoreBtn.setImageResource(R.drawable.common_title_bar_clear);
        this.mMoreBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MessageCenterDeviceListActivityNew.this.f.size() != 0) {
                    new MLAlertDialog.Builder(MessageCenterDeviceListActivityNew.this).b((CharSequence) MessageCenterDeviceListActivityNew.this.getResources().getString(R.string.log_clear_all_logs)).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            long j = 0;
                            for (IMessage next : AllTypeMsgManager.a().f()) {
                                if (next.a() > j) {
                                    j = next.a();
                                }
                            }
                            RemoteFamilyApi.a().c((Context) MessageCenterDeviceListActivityNew.this, j + 1, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                                public void onFailure(Error error) {
                                }

                                /* renamed from: a */
                                public void onSuccess(Void voidR) {
                                    MessageRecord.deleteAll();
                                    MessageCenterDeviceListActivityNew.this.startLoad();
                                }
                            });
                        }
                    }).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).b().show();
                }
            }
        });
        this.mAdapter = new SimpleAdapterNew();
        this.b.setAdapter(this.mAdapter);
        this.m = findViewById(R.id.title_bar);
        this.g = findViewById(R.id.top_delete_bar);
        this.n = (TextView) this.g.findViewById(R.id.selected_cnt);
        this.h = (ImageView) this.g.findViewById(R.id.cancel_btn);
        this.h.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MessageCenterDeviceListActivityNew.this.dismissEditMode();
            }
        });
        this.i = (ImageView) this.g.findViewById(R.id.select_all_btn);
        this.i.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MessageCenterDeviceListActivityNew.this.v.size() == MessageCenterDeviceListActivityNew.this.f.size()) {
                    MessageCenterDeviceListActivityNew.this.unSelectAll();
                    StatHelper.a(false);
                    return;
                }
                MessageCenterDeviceListActivityNew.this.selectAll();
                StatHelper.a(true);
            }
        });
        this.j = findViewById(R.id.bottom_delete_bar);
        this.k = (TextView) this.j.findViewById(R.id.delete_msg_btn);
        this.k.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MessageCenterDeviceListActivityNew.this.deleteSelected();
                StatHelper.E();
            }
        });
        this.l = (TextView) this.j.findViewById(R.id.btm_tip_tv);
        this.l.setVisibility(8);
        if (TitleBarUtil.f11582a) {
            TitleBarUtil.a(TitleBarUtil.a(), this.g);
        }
    }

    public void showDeleteBars() {
        this.g.setVisibility(0);
        this.j.setVisibility(0);
        this.k.setVisibility(0);
        this.l.setVisibility(8);
        this.g.measure(0, 0);
        this.j.measure(0, 0);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.g, View.Y, new float[]{(float) (-this.g.getMeasuredHeight()), 0.0f});
        ViewGroup viewGroup = (ViewGroup) this.j.getParent();
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.j, View.Y, new float[]{(float) viewGroup.getHeight(), (float) (viewGroup.getHeight() - this.j.getMeasuredHeight())});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(200);
        animatorSet.play(ofFloat).with(ofFloat2);
        animatorSet.start();
    }

    public void hideDeleteBars() {
        this.g.setVisibility(8);
        this.j.setVisibility(8);
        this.k.setVisibility(8);
        this.l.setVisibility(8);
    }

    public void onBackPressed() {
        try {
            super.onBackPressed();
            a();
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        if (this.f != null && this.f.size() > 0) {
            DevicePushRedpointHelper.a(this.d, Math.max(this.f.get(0).f.receiveTime, this.f.get(this.f.size() - 1).f.receiveTime), true);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    /* access modifiers changed from: package-private */
    public void startLoad() {
        this.s = 0;
        RemoteFamilyApi.a().a(getContext(), this.d, System.currentTimeMillis() / 1000, 200, (AsyncCallback<List<MessageRecordTypeList>, Error>) new AsyncCallback<List<MessageRecordTypeList>, Error>() {
            /* renamed from: a */
            public void onSuccess(List<MessageRecordTypeList> list) {
                List<IMessage> a2 = TypeListMsgCacheManagerV2.a().a(list);
                if (a2 == null || a2.isEmpty()) {
                    List unused = MessageCenterDeviceListActivityNew.this.f = new ArrayList();
                    MessageCenterDeviceListActivityNew.this.mAdapter.notifyDataSetChanged();
                    return;
                }
                List unused2 = MessageCenterDeviceListActivityNew.this.f = a2;
                MessageCenterDeviceListActivityNew.this.mAdapter.notifyDataSetChanged();
                if (MessageCenterDeviceListActivityNew.this.f != null && MessageCenterDeviceListActivityNew.this.f.size() > 0) {
                    int i = 0;
                    for (int i2 = 0; i2 < MessageCenterDeviceListActivityNew.this.f.size(); i2++) {
                        IMessage iMessage = (IMessage) MessageCenterDeviceListActivityNew.this.f.get(i2);
                        if (iMessage != null) {
                            if (!iMessage.i()) {
                                break;
                            }
                            i++;
                        }
                    }
                    String access$300 = MessageCenterDeviceListActivityNew.f10541a;
                    LogUtil.a(access$300, "newMsgCount=" + i);
                    MessageCenterDeviceListActivityNew.this.b.setSelection(MessageCenterDeviceListActivityNew.this.mAdapter.getCount() + -1);
                    if (i >= 6) {
                        int unused3 = MessageCenterDeviceListActivityNew.this.s = i;
                        MessageCenterDeviceListActivityNew.this.c();
                    }
                }
            }

            public void onFailure(Error error) {
                String str;
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
        });
    }

    /* access modifiers changed from: private */
    public void b() {
        this.r.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void c() {
        this.r = findViewById(R.id.new_msg_tip_layout);
        if (this.s <= 0) {
            this.r.setVisibility(8);
            return;
        }
        this.r.setVisibility(0);
        ((TextView) this.r.findViewById(R.id.new_msg_tv)).setText(getResources().getQuantityString(R.plurals.msg_center_new_msg_tip, this.s, new Object[]{Integer.valueOf(this.s)}));
        this.r.findViewById(R.id.new_msg_tip).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int count = (MessageCenterDeviceListActivityNew.this.b.getCount() - MessageCenterDeviceListActivityNew.this.s) - MessageCenterDeviceListActivityNew.this.b.getFooterViewsCount();
                if (count < 0) {
                    count = 0;
                }
                MessageCenterDeviceListActivityNew.this.b.setSelection(count);
                MessageCenterDeviceListActivityNew.this.b();
            }
        });
        this.b.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (i2 != 0 && MessageCenterDeviceListActivityNew.this.b.getLastVisiblePosition() >= 0) {
                    int unused = MessageCenterDeviceListActivityNew.this.s;
                }
            }
        });
    }

    public void dismissEditMode() {
        this.b.setPullDownEnabled(true);
        this.u = false;
        this.v.clear();
        hideDeleteBars();
        this.mAdapter.notifyDataSetChanged();
    }

    public void selectAll() {
        int size = this.f.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.v.put(i2, true);
        }
        this.i.setImageResource(R.drawable.un_select_selector);
        this.mAdapter.notifyDataSetChanged();
        this.n.setVisibility(0);
        this.n.setText(getResources().getQuantityString(R.plurals.selected_cnt_tips, this.v.size(), new Object[]{Integer.valueOf(this.v.size())}));
    }

    public void unSelectAll() {
        this.v.clear();
        this.i.setImageResource(R.drawable.all_select_selector);
        this.mAdapter.notifyDataSetChanged();
        this.n.setVisibility(8);
    }

    public void deleteSelected() {
        if (this.v.size() == 0) {
            ToastUtil.a((Context) this, (int) R.string.not_select_deleted_msg);
            return;
        }
        new MLAlertDialog.Builder(this).a((int) R.string.delete_msg_title).b((CharSequence) getResources().getQuantityString(R.plurals.delete_msg, this.v.size(), new Object[]{Integer.valueOf(this.v.size())})).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).a((int) R.string.confirm_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ArrayList arrayList = new ArrayList();
                final ArrayList arrayList2 = new ArrayList();
                for (int i2 = 0; i2 < MessageCenterDeviceListActivityNew.this.v.size(); i2++) {
                    int keyAt = MessageCenterDeviceListActivityNew.this.v.keyAt(i2);
                    if (MessageCenterDeviceListActivityNew.this.v.get(keyAt) && MessageCenterDeviceListActivityNew.this.f.size() > keyAt) {
                        IMessage iMessage = (IMessage) MessageCenterDeviceListActivityNew.this.mAdapter.getItem(keyAt);
                        try {
                            arrayList.add(iMessage.c());
                            arrayList2.add(iMessage);
                        } catch (Exception unused) {
                        }
                    }
                }
                RemoteFamilyApi.a().a((Context) MessageCenterDeviceListActivityNew.this, 6, MessageCenterDeviceListActivityNew.this.d, (String[]) arrayList.toArray(new String[0]), (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        if (MessageCenterDeviceListActivityNew.this.isValid()) {
                            MessageCenterDeviceListActivityNew.this.v.clear();
                            MessageCenterDeviceListActivityNew.this.n.setVisibility(8);
                            MessageCenterDeviceListActivityNew.this.i.setImageResource(R.drawable.all_select_selector);
                            MessageCenterDeviceListActivityNew.this.b.setSelectionAfterHeaderView();
                            boolean unused = MessageCenterDeviceListActivityNew.this.p = false;
                            TypeListMsgCacheManager.a().a(MessageCenterDeviceListActivityNew.this.d, (List<IMessage>) arrayList2);
                            MessageCenterDeviceListActivityNew.this.f.removeAll(arrayList2);
                            MessageCenterDeviceListActivityNew.this.mAdapter.notifyDataSetChanged();
                        }
                    }

                    public void onFailure(Error error) {
                        String str;
                        if (MessageCenterDeviceListActivityNew.this.isValid()) {
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
        if (i2 != 4 || keyEvent.getRepeatCount() != 0 || !this.u) {
            return super.onKeyUp(i2, keyEvent);
        }
        dismissEditMode();
        return true;
    }

    class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        TextView f10559a;
        TextView b;
        CheckBox c;

        ViewHolder() {
        }
    }

    class SimpleAdapterNew extends BaseAdapter {

        /* renamed from: a  reason: collision with root package name */
        DateFormat f10554a;

        public long getItemId(int i) {
            return 0;
        }

        public SimpleAdapterNew() {
            this.f10554a = LanguageUtil.b((Activity) MessageCenterDeviceListActivityNew.this);
        }

        public int getCount() {
            return MessageCenterDeviceListActivityNew.this.f.size();
        }

        public Object getItem(int i) {
            int size = (MessageCenterDeviceListActivityNew.this.f.size() - i) - 1;
            if (size < 0 || size >= MessageCenterDeviceListActivityNew.this.f.size()) {
                return null;
            }
            return MessageCenterDeviceListActivityNew.this.f.get(size);
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(MessageCenterDeviceListActivityNew.this).inflate(R.layout.message_item_device_list, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.f10559a = (TextView) view.findViewById(R.id.date_time_tv);
                viewHolder.c = (CheckBox) view.findViewById(R.id.ratio_btn);
                viewHolder.b = (TextView) view.findViewById(R.id.content_tv);
                view.setTag(viewHolder);
                viewHolder.b.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            final IMessage iMessage = (IMessage) getItem(i);
            if (iMessage == null) {
                return view;
            }
            viewHolder.f10559a.setText(this.f10554a.format(new Date(iMessage.f.receiveTime * 1000)));
            if (iMessage.i()) {
                viewHolder.b.setTextColor(MessageCenterDeviceListActivityNew.this.getResources().getColor(R.color.class_V));
            } else {
                viewHolder.b.setTextColor(MessageCenterDeviceListActivityNew.this.getResources().getColor(R.color.black_50_transparent));
            }
            iMessage.a(viewHolder.b);
            if (MessageCenterDeviceListActivityNew.this.u) {
                viewHolder.c.setVisibility(0);
                viewHolder.b.setOnLongClickListener((View.OnLongClickListener) null);
                viewHolder.b.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ((ViewHolder) view.getTag()).c.performClick();
                    }
                });
                if (MessageCenterDeviceListActivityNew.this.v.get(i)) {
                    viewHolder.c.setChecked(true);
                } else {
                    viewHolder.c.setChecked(false);
                }
            } else {
                viewHolder.c.setVisibility(4);
                viewHolder.b.setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View view) {
                        if (!MessageCenterDeviceListActivityNew.this.u) {
                            MessageCenterDeviceListActivityNew.this.b.setPullDownEnabled(false);
                            if (!MessageCenterDeviceListActivityNew.this.v.get(i)) {
                                ((ViewHolder) view.getTag()).c.performClick();
                            }
                            MessageCenterDeviceListActivityNew.this.showDeleteBars();
                            boolean unused = MessageCenterDeviceListActivityNew.this.u = true;
                            SimpleAdapterNew.this.notifyDataSetChanged();
                            StatHelper.D();
                        }
                        return true;
                    }
                });
                viewHolder.b.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        iMessage.a((Activity) MessageCenterDeviceListActivityNew.this);
                    }
                });
            }
            viewHolder.c.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!MessageCenterDeviceListActivityNew.this.v.get(i)) {
                        MessageCenterDeviceListActivityNew.this.v.put(i, true);
                    } else {
                        MessageCenterDeviceListActivityNew.this.v.delete(i);
                    }
                    if (MessageCenterDeviceListActivityNew.this.v.size() == MessageCenterDeviceListActivityNew.this.f.size()) {
                        MessageCenterDeviceListActivityNew.this.i.setImageResource(R.drawable.un_select_selector);
                    } else {
                        MessageCenterDeviceListActivityNew.this.i.setImageResource(R.drawable.all_select_selector);
                    }
                    if (MessageCenterDeviceListActivityNew.this.v.size() > 0) {
                        MessageCenterDeviceListActivityNew.this.n.setVisibility(0);
                        MessageCenterDeviceListActivityNew.this.n.setText(MessageCenterDeviceListActivityNew.this.getResources().getQuantityString(R.plurals.selected_cnt_tips, MessageCenterDeviceListActivityNew.this.v.size(), new Object[]{Integer.valueOf(MessageCenterDeviceListActivityNew.this.v.size())}));
                        return;
                    }
                    MessageCenterDeviceListActivityNew.this.n.setVisibility(0);
                    MessageCenterDeviceListActivityNew.this.n.setText(MessageCenterDeviceListActivityNew.this.getString(R.string.selected_0_cnt_tips));
                }
            });
            return view;
        }
    }

    private void d() {
        if (this.w || e()) {
        }
    }

    private boolean e() {
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            IMessage iMessage = this.f.get(i2);
            if ((iMessage != null || iMessage.a() < 1479106800) && iMessage.h()) {
                return true;
            }
        }
        return false;
    }
}
