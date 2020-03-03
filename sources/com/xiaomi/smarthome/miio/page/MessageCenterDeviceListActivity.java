package com.xiaomi.smarthome.miio.page;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Pair;
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
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.PullDownDragListView;
import com.xiaomi.smarthome.library.common.widget.viewflow.LoadingMoreView;
import com.xiaomi.smarthome.messagecenter.AllTypeMsgManager;
import com.xiaomi.smarthome.messagecenter.DevicePushRedpointHelper;
import com.xiaomi.smarthome.messagecenter.IMessage;
import com.xiaomi.smarthome.messagecenter.TypeListMsgCacheManager;
import com.xiaomi.smarthome.miio.LanguageUtil;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.db.record.MessageRecord;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MessageCenterDeviceListActivity extends BaseActivity {
    public static final String INTENT_KEY_DID = "device_id";
    public static final String INTENT_KEY_MODEL = "device_model";
    public static final String INTENT_KEY_TITLE = "title_name";
    static final String MESSAGE_LAST_UPDATE = "last_update_time";
    private static final int s = 7;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public PullDownDragListView f19560a;
    private View b;
    private String c;
    private String d;
    /* access modifiers changed from: private */
    public List<IMessage> e = new ArrayList();
    private View f;
    private ImageView g;
    /* access modifiers changed from: private */
    public ImageView h;
    private View i;
    private TextView j;
    private TextView k;
    private View l;
    /* access modifiers changed from: private */
    public TextView m;
    SimpleAdapterNew mAdapter;
    long mLastUpdataTime;
    ImageView mMoreBtn;
    XQProgressDialog mProcessDialog;
    long mTimeStamp = 0;
    private LoadingMoreView n;
    private boolean o = false;
    private CharSequence p;
    private View q;
    /* access modifiers changed from: private */
    public int r = -1;
    /* access modifiers changed from: private */
    public boolean t = false;
    /* access modifiers changed from: private */
    public SparseBooleanArray u = new SparseBooleanArray();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.message_center_list);
        Intent intent = getIntent();
        if (intent != null) {
            this.c = intent.getStringExtra("device_id");
            this.d = intent.getStringExtra("device_model");
        }
        if (TextUtils.isEmpty(this.c) || TextUtils.isEmpty(this.d)) {
            finish();
            return;
        }
        this.p = intent.getStringExtra("title_name");
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
        this.f19560a = (PullDownDragListView) findViewById(R.id.share_message_list);
        this.n = new LoadingMoreView(this);
        this.n.setVisibility(0);
        this.f19560a.setPullDownEnabled(false);
        this.b = findViewById(R.id.common_white_empty_view);
        this.b.setVisibility(8);
        ((TextView) findViewById(R.id.common_white_empty_text)).setText(R.string.miio_no_message);
        if (!TextUtils.isEmpty(this.p)) {
            ((TextView) findViewById(R.id.module_a_3_return_title)).setText(this.p);
        } else {
            ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.miio_setting_message_center);
        }
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MessageCenterDeviceListActivity.this.a();
                MessageCenterDeviceListActivity.this.finish();
            }
        });
        this.mMoreBtn = (ImageView) findViewById(R.id.module_a_3_return_more_more_btn);
        this.mMoreBtn.setVisibility(8);
        this.mMoreBtn.setImageResource(R.drawable.common_title_bar_clear);
        this.mMoreBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MessageCenterDeviceListActivity.this.e.size() != 0) {
                    new MLAlertDialog.Builder(MessageCenterDeviceListActivity.this).b((CharSequence) MessageCenterDeviceListActivity.this.getResources().getString(R.string.log_clear_all_logs)).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            long j = 0;
                            for (IMessage next : AllTypeMsgManager.a().f()) {
                                if (next.a() > j) {
                                    j = next.a();
                                }
                            }
                            RemoteFamilyApi.a().c((Context) MessageCenterDeviceListActivity.this, j + 1, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                                public void onFailure(Error error) {
                                }

                                /* renamed from: a */
                                public void onSuccess(Void voidR) {
                                    MessageRecord.deleteAll();
                                    MessageCenterDeviceListActivity.this.startLoad();
                                }
                            });
                        }
                    }).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).b().show();
                }
            }
        });
        this.mAdapter = new SimpleAdapterNew();
        this.f19560a.setAdapter(this.mAdapter);
        this.l = findViewById(R.id.title_bar);
        this.f = findViewById(R.id.top_delete_bar);
        this.m = (TextView) this.f.findViewById(R.id.selected_cnt);
        this.g = (ImageView) this.f.findViewById(R.id.cancel_btn);
        this.g.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MessageCenterDeviceListActivity.this.dismissEditMode();
            }
        });
        this.h = (ImageView) this.f.findViewById(R.id.select_all_btn);
        this.h.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MessageCenterDeviceListActivity.this.u.size() == MessageCenterDeviceListActivity.this.e.size()) {
                    MessageCenterDeviceListActivity.this.unSelectAll();
                    StatHelper.a(false);
                    return;
                }
                MessageCenterDeviceListActivity.this.selectAll();
                StatHelper.a(true);
            }
        });
        this.i = findViewById(R.id.bottom_delete_bar);
        this.j = (TextView) this.i.findViewById(R.id.delete_msg_btn);
        this.j.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MessageCenterDeviceListActivity.this.deleteSelected();
                StatHelper.E();
            }
        });
        this.k = (TextView) this.i.findViewById(R.id.btm_tip_tv);
        this.k.setVisibility(8);
        if (TitleBarUtil.f11582a) {
            TitleBarUtil.a(TitleBarUtil.a(), this.f);
        }
    }

    public void showDeleteBars() {
        this.f.setVisibility(0);
        this.i.setVisibility(0);
        this.j.setVisibility(0);
        this.k.setVisibility(8);
        this.f.measure(0, 0);
        this.i.measure(0, 0);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f, View.Y, new float[]{(float) (-this.f.getMeasuredHeight()), 0.0f});
        ViewGroup viewGroup = (ViewGroup) this.i.getParent();
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.i, View.Y, new float[]{(float) viewGroup.getHeight(), (float) (viewGroup.getHeight() - this.i.getMeasuredHeight())});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(200);
        animatorSet.play(ofFloat).with(ofFloat2);
        animatorSet.start();
    }

    public void hideDeleteBars() {
        this.f.setVisibility(8);
        this.i.setVisibility(8);
        this.j.setVisibility(8);
        this.k.setVisibility(8);
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
        if (this.e != null && this.e.size() > 0) {
            DevicePushRedpointHelper.a(this.c, Math.max(this.e.get(0).f.receiveTime, this.e.get(this.e.size() - 1).f.receiveTime), true);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    /* access modifiers changed from: package-private */
    public void startLoad() {
        this.r = 0;
        List<Pair<TypeListMsgCacheManager.DevicePushData, List<IMessage>>> c2 = TypeListMsgCacheManager.a().c();
        if (c2 != null) {
            Iterator it = new ArrayList(c2).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Pair pair = (Pair) it.next();
                if (pair.first != null && TextUtils.equals(((TypeListMsgCacheManager.DevicePushData) pair.first).f10475a, this.c)) {
                    this.e = new ArrayList((Collection) pair.second);
                    this.f19560a.setSelection(this.e.size() - 1);
                    this.mAdapter.notifyDataSetChanged();
                    break;
                }
            }
            if (this.e != null && this.e.size() > 0) {
                int i2 = 0;
                for (int i3 = 0; i3 < this.e.size(); i3++) {
                    IMessage iMessage = this.e.get(i3);
                    if (iMessage != null) {
                        if (!iMessage.h()) {
                            break;
                        }
                        i2++;
                    }
                }
                if (i2 >= 6) {
                    this.r = i2;
                    c();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        this.q.setVisibility(8);
    }

    private void c() {
        this.q = findViewById(R.id.new_msg_tip_layout);
        if (this.r <= 0) {
            this.q.setVisibility(8);
            return;
        }
        this.q.setVisibility(0);
        ((TextView) this.q.findViewById(R.id.new_msg_tv)).setText(getResources().getQuantityString(R.plurals.msg_center_new_msg_tip, this.r, new Object[]{Integer.valueOf(this.r)}));
        this.q.findViewById(R.id.new_msg_tip).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int count = (MessageCenterDeviceListActivity.this.f19560a.getCount() - MessageCenterDeviceListActivity.this.r) - MessageCenterDeviceListActivity.this.f19560a.getFooterViewsCount();
                if (count < 0) {
                    count = 0;
                }
                MessageCenterDeviceListActivity.this.f19560a.setSelection(count);
                MessageCenterDeviceListActivity.this.b();
            }
        });
        this.f19560a.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (i2 != 0 && MessageCenterDeviceListActivity.this.f19560a.getLastVisiblePosition() >= 0 && i3 - i >= MessageCenterDeviceListActivity.this.r) {
                    MessageCenterDeviceListActivity.this.b();
                }
            }
        });
    }

    public void dismissEditMode() {
        this.f19560a.setPullDownEnabled(true);
        this.t = false;
        this.u.clear();
        hideDeleteBars();
        this.mAdapter.notifyDataSetChanged();
    }

    public void selectAll() {
        int size = this.e.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.u.put(i2, true);
        }
        this.h.setImageResource(R.drawable.un_select_selector);
        this.mAdapter.notifyDataSetChanged();
        this.m.setVisibility(0);
        this.m.setText(getResources().getQuantityString(R.plurals.selected_cnt_tips, this.u.size(), new Object[]{Integer.valueOf(this.u.size())}));
    }

    public void unSelectAll() {
        this.u.clear();
        this.h.setImageResource(R.drawable.all_select_selector);
        this.mAdapter.notifyDataSetChanged();
        this.m.setVisibility(8);
    }

    public void deleteSelected() {
        if (this.u.size() == 0) {
            ToastUtil.a((Context) this, (int) R.string.not_select_deleted_msg);
            return;
        }
        new MLAlertDialog.Builder(this).a((int) R.string.delete_msg_title).b((CharSequence) getResources().getQuantityString(R.plurals.delete_msg, this.u.size(), new Object[]{Integer.valueOf(this.u.size())})).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).a((int) R.string.confirm_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                for (int i2 = 0; i2 < MessageCenterDeviceListActivity.this.u.size(); i2++) {
                    int keyAt = MessageCenterDeviceListActivity.this.u.keyAt(i2);
                    if (MessageCenterDeviceListActivity.this.u.get(keyAt) && MessageCenterDeviceListActivity.this.e.size() > keyAt) {
                        IMessage iMessage = (IMessage) MessageCenterDeviceListActivity.this.mAdapter.getItem(keyAt);
                        try {
                            arrayList.add(iMessage.c());
                            arrayList2.add(iMessage);
                        } catch (Exception unused) {
                        }
                    }
                }
            }
        }).b().show();
    }

    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (i2 != 4 || keyEvent.getRepeatCount() != 0 || !this.t) {
            return super.onKeyUp(i2, keyEvent);
        }
        dismissEditMode();
        return true;
    }

    class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        TextView f19576a;
        TextView b;
        CheckBox c;

        ViewHolder() {
        }
    }

    class SimpleAdapterNew extends BaseAdapter {

        /* renamed from: a  reason: collision with root package name */
        DateFormat f19571a;

        public long getItemId(int i) {
            return 0;
        }

        public SimpleAdapterNew() {
            this.f19571a = LanguageUtil.b((Activity) MessageCenterDeviceListActivity.this);
        }

        public int getCount() {
            return MessageCenterDeviceListActivity.this.e.size();
        }

        public Object getItem(int i) {
            int size = (MessageCenterDeviceListActivity.this.e.size() - i) - 1;
            if (size < 0 || size >= MessageCenterDeviceListActivity.this.e.size()) {
                return null;
            }
            return MessageCenterDeviceListActivity.this.e.get(size);
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(MessageCenterDeviceListActivity.this).inflate(R.layout.message_item_device_list, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.f19576a = (TextView) view.findViewById(R.id.date_time_tv);
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
            viewHolder.f19576a.setText(this.f19571a.format(new Date(iMessage.f.receiveTime * 1000)));
            if (iMessage.h()) {
                viewHolder.b.setTextColor(MessageCenterDeviceListActivity.this.getResources().getColor(R.color.class_V));
            } else {
                viewHolder.b.setTextColor(MessageCenterDeviceListActivity.this.getResources().getColor(R.color.black_50_transparent));
            }
            iMessage.a(viewHolder.b);
            if (MessageCenterDeviceListActivity.this.t) {
                viewHolder.c.setVisibility(0);
                viewHolder.b.setOnLongClickListener((View.OnLongClickListener) null);
                viewHolder.b.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ((ViewHolder) view.getTag()).c.performClick();
                    }
                });
                if (MessageCenterDeviceListActivity.this.u.get(i)) {
                    viewHolder.c.setChecked(true);
                } else {
                    viewHolder.c.setChecked(false);
                }
            } else {
                viewHolder.c.setVisibility(4);
                viewHolder.b.setOnLongClickListener(new View.OnLongClickListener() {
                    public boolean onLongClick(View view) {
                        if (!MessageCenterDeviceListActivity.this.t) {
                            MessageCenterDeviceListActivity.this.f19560a.setPullDownEnabled(false);
                            if (!MessageCenterDeviceListActivity.this.u.get(i)) {
                                ((ViewHolder) view.getTag()).c.performClick();
                            }
                            MessageCenterDeviceListActivity.this.showDeleteBars();
                            boolean unused = MessageCenterDeviceListActivity.this.t = true;
                            SimpleAdapterNew.this.notifyDataSetChanged();
                            StatHelper.D();
                        }
                        return true;
                    }
                });
                viewHolder.b.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        iMessage.a((Activity) MessageCenterDeviceListActivity.this);
                    }
                });
            }
            viewHolder.c.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!MessageCenterDeviceListActivity.this.u.get(i)) {
                        MessageCenterDeviceListActivity.this.u.put(i, true);
                    } else {
                        MessageCenterDeviceListActivity.this.u.delete(i);
                    }
                    if (MessageCenterDeviceListActivity.this.u.size() == MessageCenterDeviceListActivity.this.e.size()) {
                        MessageCenterDeviceListActivity.this.h.setImageResource(R.drawable.un_select_selector);
                    } else {
                        MessageCenterDeviceListActivity.this.h.setImageResource(R.drawable.all_select_selector);
                    }
                    if (MessageCenterDeviceListActivity.this.u.size() > 0) {
                        MessageCenterDeviceListActivity.this.m.setVisibility(0);
                        MessageCenterDeviceListActivity.this.m.setText(MessageCenterDeviceListActivity.this.getResources().getQuantityString(R.plurals.selected_cnt_tips, MessageCenterDeviceListActivity.this.u.size(), new Object[]{Integer.valueOf(MessageCenterDeviceListActivity.this.u.size())}));
                        return;
                    }
                    MessageCenterDeviceListActivity.this.m.setVisibility(0);
                    MessageCenterDeviceListActivity.this.m.setText(MessageCenterDeviceListActivity.this.getString(R.string.selected_0_cnt_tips));
                }
            });
            return view;
        }
    }
}
