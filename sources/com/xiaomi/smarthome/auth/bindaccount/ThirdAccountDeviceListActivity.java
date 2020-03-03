package com.xiaomi.smarthome.auth.bindaccount;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import com.alipay.sdk.util.i;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.auth.bindaccount.model.ThirdAccount;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.DevicePtrFrameLayout;
import com.xiaomi.smarthome.library.common.widget.SmartHomePtrHeader;
import com.xiaomi.smarthome.stat.STAT;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import java.util.ArrayList;
import java.util.List;

public class ThirdAccountDeviceListActivity extends BaseActivity {
    public static final String INTENT_KEY_SYNC_ON_START = "sync_on_start";

    /* renamed from: a  reason: collision with root package name */
    private View f13895a;
    private TextView b;
    private RecyclerView c;
    /* access modifiers changed from: private */
    public SimpleAdapter d;
    /* access modifiers changed from: private */
    public DevicePtrFrameLayout e;
    private SmartHomePtrHeader f;
    private PtrIndicator g;
    /* access modifiers changed from: private */
    public View h;
    private ImageView i;
    /* access modifiers changed from: private */
    public Animation j;
    private View k;
    private boolean l = false;
    /* access modifiers changed from: private */
    public ThirdAccount m;
    /* access modifiers changed from: private */
    public XQProgressDialog n;
    private TextView o;
    private BroadcastReceiver p = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), ThirdAccountBindManager.d)) {
                if (ThirdAccountDeviceListActivity.this.j != null) {
                    ThirdAccountDeviceListActivity.this.j.cancel();
                }
                if (intent.getIntExtra(ThirdAccountBindManager.f, 0) == 0) {
                    ThirdAccountDeviceListActivity.this.a(true);
                    ToastUtil.a((int) R.string.third_account_sync_device_success);
                    return;
                }
                ToastUtil.a((int) R.string.third_account_sync_device_failed);
            }
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.m = ThirdAccountBindManager.a().a(intent.getStringExtra("account_id"));
        if (this.m == null) {
            finish();
            return;
        }
        setContentView(R.layout.activity_third_account_device_list_layout);
        a();
        this.e.autoRefresh();
        a(false);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.p, new IntentFilter(ThirdAccountBindManager.d));
        this.l = intent.getBooleanExtra(INTENT_KEY_SYNC_ON_START, false);
        if (this.l) {
            h();
        }
    }

    private void a() {
        m();
        l();
        k();
        b();
        c();
        this.h = findViewById(R.id.sync_device);
        this.h.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ThirdAccountDeviceListActivity.this.h();
                STAT.d.c(ThirdAccountDeviceListActivity.this.m.a());
            }
        });
        this.i = (ImageView) findViewById(R.id.sync_icon);
        this.i.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ThirdAccountDeviceListActivity.this.h.performClick();
            }
        });
        this.k = findViewById(R.id.unbind_device);
        this.k.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ThirdAccountDeviceListActivity.this.j();
                STAT.d.d(ThirdAccountDeviceListActivity.this.m.a());
            }
        });
        if (this.m.d() == 1) {
            g();
        } else if (this.m == null || this.m.f() == null || this.m.f().isEmpty()) {
            e();
        } else {
            f();
        }
        if (ThirdAccountBindManager.a().e(this.m == null ? "" : this.m.b())) {
            i();
        }
    }

    private void b() {
        this.e = (DevicePtrFrameLayout) findViewById(R.id.pull_down_refresh);
        this.f = (SmartHomePtrHeader) findViewById(R.id.pull_header_new);
        this.g = new PtrIndicator();
        this.e.disableWhenHorizontalMove(true);
        this.e.setPullToRefresh(false);
        this.e.setPtrIndicator(this.g);
        this.e.addPtrUIHandler(new PtrUIHandler() {
            public void onUIPositionChange(PtrFrameLayout ptrFrameLayout, boolean z, byte b, PtrIndicator ptrIndicator) {
            }

            public void onUIRefreshBegin(PtrFrameLayout ptrFrameLayout) {
            }

            public void onUIRefreshComplete(PtrFrameLayout ptrFrameLayout) {
            }

            public void onUIRefreshPrepare(PtrFrameLayout ptrFrameLayout) {
            }

            public void onUIReset(PtrFrameLayout ptrFrameLayout) {
            }
        });
        this.e.setPtrHandler(new PtrDefaultHandler() {
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                ThirdAccountDeviceListActivity.this.a(false);
            }
        });
    }

    private void c() {
        this.c = (RecyclerView) findViewById(R.id.recycler_view);
        this.c.setLayoutManager(new LinearLayoutManager(this));
        this.d = new SimpleAdapter();
        this.c.setAdapter(this.d);
        View inflate = LayoutInflater.from(this).inflate(R.layout.tag_group_item_common_3, this.c, false);
        ViewGroup.LayoutParams layoutParams = inflate.getLayoutParams();
        layoutParams.height = DisplayUtils.a(20.0f);
        inflate.setLayoutParams(layoutParams);
        this.d.a(inflate);
    }

    /* access modifiers changed from: private */
    public void a(final boolean z) {
        if (this.m != null) {
            ThirdAccountBindManager.a().b((AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                /* renamed from: a */
                public void onSuccess(Void voidR) {
                    if (ThirdAccountDeviceListActivity.this.isValid()) {
                        ThirdAccountDeviceListActivity.this.e.refreshComplete();
                        ThirdAccount unused = ThirdAccountDeviceListActivity.this.m = ThirdAccountBindManager.a().a(ThirdAccountDeviceListActivity.this.m.b());
                        if (ThirdAccountDeviceListActivity.this.m == null) {
                            ThirdAccountDeviceListActivity.this.finish();
                            return;
                        }
                        try {
                            if (ThirdAccountDeviceListActivity.this.m.d() == 1) {
                                ThirdAccountDeviceListActivity.this.g();
                            } else if (ThirdAccountDeviceListActivity.this.m.d() == -1) {
                                ThirdAccountDeviceListActivity.this.d();
                            } else {
                                if (ThirdAccountDeviceListActivity.this.m.f() != null) {
                                    if (!ThirdAccountDeviceListActivity.this.m.f().isEmpty()) {
                                        List f = ThirdAccountDeviceListActivity.this.m.f();
                                        if (f == null) {
                                            f = new ArrayList();
                                        }
                                        if (z) {
                                            StringBuilder sb = new StringBuilder();
                                            for (int i = 0; i < f.size(); i++) {
                                                sb.append(((Device) f.get(i)).did);
                                                sb.append(i.b);
                                            }
                                            STAT.i.a(f.size(), sb.toString());
                                        }
                                        if (f.isEmpty()) {
                                            ThirdAccountDeviceListActivity.this.e();
                                            return;
                                        }
                                        ThirdAccountDeviceListActivity.this.f();
                                        ThirdAccountDeviceListActivity.this.d.a((List<Device>) f);
                                        ThirdAccountDeviceListActivity.this.d.notifyDataSetChanged();
                                        return;
                                    }
                                }
                                ThirdAccountDeviceListActivity.this.e();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                public void onFailure(Error error) {
                    if (ThirdAccountDeviceListActivity.this.isValid()) {
                        ThirdAccountDeviceListActivity.this.e.refreshComplete();
                        ToastUtil.a((int) R.string.load_failed);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        this.h.setVisibility(8);
        this.i.setVisibility(8);
        this.k.setVisibility(8);
        this.f13895a.setVisibility(0);
        this.c.setVisibility(8);
        this.o.setVisibility(8);
        this.b.setText(R.string.third_account_bind_lose_efficacy);
    }

    /* access modifiers changed from: private */
    public void e() {
        this.h.setVisibility(0);
        this.i.setVisibility(0);
        this.k.setVisibility(0);
        this.f13895a.setVisibility(0);
        this.c.setVisibility(8);
        this.o.setVisibility(8);
        this.b.setText(this.m == null ? "" : this.m.a());
    }

    /* access modifiers changed from: private */
    public void f() {
        this.h.setVisibility(0);
        this.i.setVisibility(0);
        this.k.setVisibility(0);
        this.f13895a.setVisibility(8);
        this.o.setVisibility(8);
        this.c.setVisibility(0);
        this.h.setEnabled(true);
        this.i.setEnabled(true);
    }

    /* access modifiers changed from: private */
    public void g() {
        this.h.setVisibility(0);
        this.i.setVisibility(0);
        this.k.setVisibility(0);
        this.f13895a.setVisibility(8);
        this.c.setVisibility(8);
        this.o.setVisibility(0);
        this.h.setEnabled(false);
        this.i.setEnabled(false);
    }

    /* access modifiers changed from: private */
    public void h() {
        if (!ThirdAccountBindManager.a().e(this.m == null ? "" : this.m.b())) {
            i();
            ThirdAccountBindManager.a().d(this.m == null ? "" : this.m.b());
        }
    }

    private void i() {
        if (this.j == null) {
            this.j = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
            this.j.setInterpolator(new LinearInterpolator());
        }
        this.j.setDuration(1000);
        this.j.setRepeatCount(-1);
        this.j.setRepeatMode(-1);
        this.j.setFillAfter(true);
        this.i.startAnimation(this.j);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.j != null) {
            this.j.cancel();
        }
        try {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.p);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void j() {
        new MLAlertDialog.Builder(this).b((CharSequence) getResources().getString(R.string.third_account_unbind_msg)).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).a((int) R.string.third_account_unbind_ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                XQProgressDialog unused = ThirdAccountDeviceListActivity.this.n = new XQProgressDialog(ThirdAccountDeviceListActivity.this);
                ThirdAccountDeviceListActivity.this.n.setCancelable(true);
                ThirdAccountDeviceListActivity.this.n.setMessage(ThirdAccountDeviceListActivity.this.getResources().getString(R.string.loading_share_info));
                ThirdAccountDeviceListActivity.this.n.show();
                ThirdAccountDeviceListActivity.this.n.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    public void onDismiss(DialogInterface dialogInterface) {
                    }
                });
                ThirdAccountBindManager.a().a(ThirdAccountDeviceListActivity.this.m == null ? "" : ThirdAccountDeviceListActivity.this.m.b(), (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        if (ThirdAccountDeviceListActivity.this.isValid()) {
                            if (ThirdAccountDeviceListActivity.this.n != null) {
                                ThirdAccountDeviceListActivity.this.n.dismiss();
                            }
                            ToastUtil.a((int) R.string.third_account_unbind_success);
                            int size = (ThirdAccountDeviceListActivity.this.m == null || ThirdAccountDeviceListActivity.this.m.f() == null) ? 0 : ThirdAccountDeviceListActivity.this.m.f().size();
                            StringBuilder sb = new StringBuilder();
                            for (int i = 0; i < size; i++) {
                                sb.append(ThirdAccountDeviceListActivity.this.m.f().get(i).did);
                                sb.append(i.b);
                            }
                            STAT.i.b(size, sb.toString());
                            ThirdAccountDeviceListActivity.this.finish();
                        }
                    }

                    public void onFailure(Error error) {
                        if (ThirdAccountDeviceListActivity.this.isValid()) {
                            if (ThirdAccountDeviceListActivity.this.n != null) {
                                ThirdAccountDeviceListActivity.this.n.dismiss();
                            }
                            ToastUtil.a((int) R.string.third_account_unbind_failed);
                        }
                    }
                });
            }
        }).b().show();
    }

    private void k() {
        this.o = (TextView) findViewById(R.id.expire_view);
        String string = getString(R.string.third_account_bind_expire);
        String string2 = getString(R.string.third_account_rebind);
        try {
            int indexOf = string.indexOf("%s");
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(String.format(string, new Object[]{string2}));
            spannableStringBuilder.setSpan(new ClickableSpan() {
                public void onClick(View view) {
                    if (ThirdAccountDeviceListActivity.this.m.d() != 0) {
                        ThirdAccountDeviceListActivity.this.finish();
                        Intent intent = new Intent(ThirdAccountDeviceListActivity.this, ThirdAccountDetailActivity.class);
                        intent.putExtra("account_id", ThirdAccountDeviceListActivity.this.m.b());
                        ThirdAccountDeviceListActivity.this.startActivity(intent);
                        STAT.d.a(ThirdAccountDeviceListActivity.this.m.a());
                    }
                }

                public void updateDrawState(TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setColor(ThirdAccountDeviceListActivity.this.getResources().getColor(R.color.choose_connect_device_error_link));
                    textPaint.setUnderlineText(false);
                }
            }, indexOf, string2.length() + indexOf, 33);
            this.o.setText(spannableStringBuilder);
            this.o.setMovementMethod(LinkMovementMethod.getInstance());
            this.o.setHighlightColor(0);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void l() {
        this.f13895a = findViewById(R.id.common_white_empty_view);
        this.b = (TextView) this.f13895a.findViewById(R.id.common_white_empty_text);
    }

    private void m() {
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ThirdAccountDeviceListActivity.this.finish();
            }
        });
        TextView textView = (TextView) findViewById(R.id.module_a_3_return_title);
        String a2 = this.m.a();
        if (TextUtils.isEmpty(a2)) {
            a2 = getString(R.string.third_account_list_title);
        }
        textView.setText(a2);
        findViewById(R.id.module_a_3_return_more_more_btn).setVisibility(8);
    }

    private class SimpleAdapter extends RecyclerView.Adapter<Holder> {

        /* renamed from: a  reason: collision with root package name */
        public static final int f13908a = 0;
        public static final int b = 1;
        private List<Device> d;
        /* access modifiers changed from: private */
        public View e;

        private SimpleAdapter() {
            this.d = new ArrayList();
        }

        public void a(List<Device> list) {
            if (list == null) {
                this.d = new ArrayList();
            } else {
                this.d = list;
            }
        }

        public void a(View view) {
            this.e = view;
            notifyItemInserted(0);
        }

        public int getItemViewType(int i) {
            return (i != 0 || this.e == null) ? 1 : 0;
        }

        /* renamed from: a */
        public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
            if (this.e == null || i != 0) {
                return new Holder(LayoutInflater.from(ThirdAccountDeviceListActivity.this).inflate(R.layout.third_account_item_layout, (ViewGroup) null));
            }
            return new Holder(this.e);
        }

        /* renamed from: a */
        public void onBindViewHolder(Holder holder, int i) {
            if (getItemViewType(i) != 0) {
                Device device = this.d.get(a((RecyclerView.ViewHolder) holder));
                holder.c.setText(device.name);
                ThirdAccountBindManager.a(holder.b, device.propInfo == null ? "" : device.propInfo.optString(ThirdAccountBindManager.f13881a, ""));
            }
        }

        public int a(RecyclerView.ViewHolder viewHolder) {
            int layoutPosition = viewHolder.getLayoutPosition();
            return this.e == null ? layoutPosition : layoutPosition - 1;
        }

        public int getItemCount() {
            return this.e == null ? this.d.size() : this.d.size() + 1;
        }

        public class Holder extends RecyclerView.ViewHolder {
            /* access modifiers changed from: private */
            public SimpleDraweeView b;
            /* access modifiers changed from: private */
            public TextView c;

            public Holder(View view) {
                super(view);
                if (view != SimpleAdapter.this.e) {
                    this.b = (SimpleDraweeView) view.findViewById(R.id.icon);
                    this.c = (TextView) view.findViewById(R.id.title);
                    view.findViewById(R.id.next_btn).setVisibility(8);
                }
            }
        }
    }
}
