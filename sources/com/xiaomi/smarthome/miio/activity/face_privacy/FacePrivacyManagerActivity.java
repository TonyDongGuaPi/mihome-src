package com.xiaomi.smarthome.miio.activity.face_privacy;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.server.debug.NetRequestWarningActivity;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.miio.activity.face_privacy.FacePresent;
import com.xiaomi.smarthome.miio.activity.face_privacy.FacePrivacyApi;
import com.xiaomi.smarthome.miio.activity.face_privacy.FacePrivacyEvent;
import com.xiaomi.smarthome.miio.activity.face_privacy.FacePrivacyState;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00152\u00020\u00012\u00020\u0002:\u0002\u0015\u0016B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\f\u001a\u00020\rH\u0002J\u0012\u0010\u000e\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\u0010\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013H\u0017J\b\u0010\u0014\u001a\u00020\rH\u0002R\u0012\u0010\u0004\u001a\u00060\u0005R\u00020\u0000X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyManagerActivity;", "Lcom/xiaomi/smarthome/framework/page/BaseActivity;", "Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePresent$RenderView;", "()V", "mAdapter", "Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyManagerActivity$InnerAdapter;", "mFacePresent", "Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePresent;", "mLoadingDialog", "Lcom/xiaomi/smarthome/library/common/dialog/XQProgressDialog;", "mRecyclerView", "Landroid/support/v7/widget/RecyclerView;", "dismissLoadingDialog", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "render", "state", "Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyState;", "showLoadingDialog", "Companion", "InnerAdapter", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
public final class FacePrivacyManagerActivity extends BaseActivity implements FacePresent.RenderView {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String e = "FacePrivacyManagerActiv";

    /* renamed from: a  reason: collision with root package name */
    private RecyclerView f11885a;
    /* access modifiers changed from: private */
    public FacePresent b;
    private InnerAdapter c;
    /* access modifiers changed from: private */
    public XQProgressDialog d;
    private HashMap f;

    public void _$_clearFindViewByIdCache() {
        if (this.f != null) {
            this.f.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this.f == null) {
            this.f = new HashMap();
        }
        View view = (View) this.f.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this.f.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @NotNull
    public static final /* synthetic */ FacePresent access$getMFacePresent$p(FacePrivacyManagerActivity facePrivacyManagerActivity) {
        FacePresent facePresent = facePrivacyManagerActivity.b;
        if (facePresent == null) {
            Intrinsics.c("mFacePresent");
        }
        return facePresent;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyManagerActivity$Companion;", "", "()V", "TAG", "", "start", "", "context", "Landroid/content/Context;", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void a(@NotNull Context context) {
            Intrinsics.f(context, "context");
            Intent intent = new Intent(context, FacePrivacyManagerActivity.class);
            if (!(context instanceof Activity)) {
                intent.addFlags(C.ENCODING_PCM_MU_LAW);
            }
            context.startActivity(intent);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_face_privacy_manager);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new FacePrivacyManagerActivity$onCreate$1(this));
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.face_recognition_manager);
        View findViewById = findViewById(R.id.recycler);
        Intrinsics.b(findViewById, "findViewById(R.id.recycler)");
        this.f11885a = (RecyclerView) findViewById;
        RecyclerView recyclerView = this.f11885a;
        if (recyclerView == null) {
            Intrinsics.c("mRecyclerView");
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.c = new InnerAdapter();
        RecyclerView recyclerView2 = this.f11885a;
        if (recyclerView2 == null) {
            Intrinsics.c("mRecyclerView");
        }
        InnerAdapter innerAdapter = this.c;
        if (innerAdapter == null) {
            Intrinsics.c("mAdapter");
        }
        recyclerView2.setAdapter(innerAdapter);
        ViewModel a2 = ViewModelProviders.a((FragmentActivity) this).a(FacePresent.class);
        Intrinsics.b(a2, "ViewModelProviders.of(th…(FacePresent::class.java)");
        this.b = (FacePresent) a2;
        FacePresent facePresent = this.b;
        if (facePresent == null) {
            Intrinsics.c("mFacePresent");
        }
        facePresent.a((FacePresent.RenderView) this);
        FacePresent facePresent2 = this.b;
        if (facePresent2 == null) {
            Intrinsics.c("mFacePresent");
        }
        facePresent2.a((FacePrivacyEvent) FacePrivacyEvent.LoadFaceStatusEvent.f11883a);
    }

    @MainThread
    public void render(@NotNull FacePrivacyState facePrivacyState) {
        Intrinsics.f(facePrivacyState, "state");
        Log.d(e, "render: " + facePrivacyState);
        if (facePrivacyState instanceof FacePrivacyState.ToastState) {
            Context context = this;
            Toast.makeText(context, ((FacePrivacyState.ToastState) facePrivacyState).a(context), 0).show();
        } else if (facePrivacyState instanceof FacePrivacyState.LoadingState) {
            a();
        } else if (facePrivacyState instanceof FacePrivacyState.ErrorState) {
            b();
            Log.e(e, ((FacePrivacyState.ErrorState) facePrivacyState).a().getMessage());
        } else if (facePrivacyState instanceof FacePrivacyState.FaceStatusDataState) {
            b();
            RecyclerView recyclerView = this.f11885a;
            if (recyclerView == null) {
                Intrinsics.c("mRecyclerView");
            }
            recyclerView.setVisibility(0);
            InnerAdapter innerAdapter = this.c;
            if (innerAdapter == null) {
                Intrinsics.c("mAdapter");
            }
            innerAdapter.a(((FacePrivacyState.FaceStatusDataState) facePrivacyState).a());
        }
    }

    private final void a() {
        if (this.d == null) {
            this.d = new XQProgressDialog(this);
            XQProgressDialog xQProgressDialog = this.d;
            if (xQProgressDialog != null) {
                xQProgressDialog.setMessage(getString(R.string.loading));
            }
            XQProgressDialog xQProgressDialog2 = this.d;
            if (xQProgressDialog2 != null) {
                xQProgressDialog2.setCancelable(true);
            }
        }
        XQProgressDialog xQProgressDialog3 = this.d;
        if (xQProgressDialog3 != null) {
            xQProgressDialog3.show();
        }
    }

    private final void b() {
        Window window = getWindow();
        Intrinsics.b(window, "window");
        window.getDecorView().postDelayed(new FacePrivacyManagerActivity$dismissLoadingDialog$1(this), 200);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\b\u0004\u0018\u00002\u0010\u0012\f\u0012\n0\u0002R\u00060\u0000R\u00020\u00030\u0001:\u0001\u0014B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u00020\tH\u0016J \u0010\n\u001a\u00020\u000b2\u000e\u0010\f\u001a\n0\u0002R\u00060\u0000R\u00020\u00032\u0006\u0010\r\u001a\u00020\tH\u0016J \u0010\u000e\u001a\n0\u0002R\u00060\u0000R\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\tH\u0016J\u0014\u0010\u0011\u001a\u00020\u000b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0013R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyManagerActivity$InnerAdapter;", "Landroid/support/v7/widget/RecyclerView$Adapter;", "Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyManagerActivity$InnerAdapter$VH;", "Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyManagerActivity;", "(Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyManagerActivity;)V", "mStatus", "Ljava/util/ArrayList;", "Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyApi$FaceStatus;", "getItemCount", "", "onBindViewHolder", "", "vh", "pos", "onCreateViewHolder", "viewGroup", "Landroid/view/ViewGroup;", "updateData", "data", "", "VH", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
    private final class InnerAdapter extends RecyclerView.Adapter<VH> {
        private final ArrayList<FacePrivacyApi.FaceStatus> b = new ArrayList<>();

        public InnerAdapter() {
        }

        @NotNull
        /* renamed from: a */
        public VH onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
            Intrinsics.f(viewGroup, "viewGroup");
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.face_privacy_item, viewGroup, false);
            Intrinsics.b(inflate, NetRequestWarningActivity.KEY_ITEM);
            return new VH(this, inflate);
        }

        /* renamed from: a */
        public void onBindViewHolder(@NotNull VH vh, int i) {
            Intrinsics.f(vh, "vh");
            FacePrivacyApi.FaceStatus faceStatus = this.b.get(i);
            Intrinsics.b(faceStatus, "mStatus[pos]");
            vh.a(faceStatus);
        }

        public int getItemCount() {
            return this.b.size();
        }

        public final void a(@NotNull List<? extends FacePrivacyApi.FaceStatus> list) {
            Intrinsics.f(list, "data");
            this.b.clear();
            this.b.addAll(list);
            notifyDataSetChanged();
        }

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyManagerActivity$InnerAdapter$VH;", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyManagerActivity$InnerAdapter;Landroid/view/View;)V", "bind", "", "status", "Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyApi$FaceStatus;", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
        private final class VH extends RecyclerView.ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ InnerAdapter f11887a;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public VH(InnerAdapter innerAdapter, @NotNull View view) {
                super(view);
                Intrinsics.f(view, "itemView");
                this.f11887a = innerAdapter;
            }

            public final void a(@NotNull FacePrivacyApi.FaceStatus faceStatus) {
                Intrinsics.f(faceStatus, "status");
                Device device = faceStatus.f11882a;
                Boolean bool = faceStatus.b;
                View findViewById = this.itemView.findViewById(R.id.title);
                Intrinsics.b(findViewById, "itemView.findViewById<TextView>(R.id.title)");
                ((TextView) findViewById).setText(device.name);
                SwitchButton switchButton = (SwitchButton) this.itemView.findViewById(R.id.switcher);
                switchButton.setOnTouchEnable(false);
                this.itemView.setOnClickListener(new FacePrivacyManagerActivity$InnerAdapter$VH$bind$1(this, device, bool, switchButton));
                Intrinsics.b(switchButton, "switchButton");
                Intrinsics.b(bool, "isOpen");
                switchButton.setChecked(bool.booleanValue());
            }
        }
    }
}
