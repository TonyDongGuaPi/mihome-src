package com.xiaomi.smarthome.framework.permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.mobikwik.sdk.lib.Constants;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.server.debug.NetRequestWarningActivity;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.shop.utils.DisplayUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00142\u00020\u0001:\u0004\u0013\u0014\u0015\u0016B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\nX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX.¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/xiaomi/smarthome/framework/permission/PermissionRequestActivity;", "Lcom/xiaomi/smarthome/framework/page/BaseActivity;", "()V", "backBtn", "Landroid/view/View;", "checkedPermissions", "Ljava/util/HashSet;", "Lcom/xiaomi/smarthome/framework/permission/PermissionBean;", "Lkotlin/collections/HashSet;", "permissions", "", "recyclerView", "Landroid/support/v7/widget/RecyclerView;", "selectAllCheckBox", "Landroid/widget/CheckBox;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "Adapter", "Companion", "ItemDivider", "MyViewHolder", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
public final class PermissionRequestActivity extends BaseActivity {
    public static final long ANIMATION_DURATION = 200;
    @NotNull
    public static final String ARG_KEYS_PERMISSION = "arg_keys_permission";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int REQUEST_CODE = 6050;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public List<PermissionBean> f17110a;
    /* access modifiers changed from: private */
    public RecyclerView b;
    /* access modifiers changed from: private */
    public CheckBox c;
    private View d;
    /* access modifiers changed from: private */
    public HashSet<PermissionBean> e = new HashSet<>();
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
    public static final /* synthetic */ List access$getPermissions$p(PermissionRequestActivity permissionRequestActivity) {
        List<PermissionBean> list = permissionRequestActivity.f17110a;
        if (list == null) {
            Intrinsics.c("permissions");
        }
        return list;
    }

    @NotNull
    public static final /* synthetic */ RecyclerView access$getRecyclerView$p(PermissionRequestActivity permissionRequestActivity) {
        RecyclerView recyclerView = permissionRequestActivity.b;
        if (recyclerView == null) {
            Intrinsics.c("recyclerView");
        }
        return recyclerView;
    }

    @NotNull
    public static final /* synthetic */ CheckBox access$getSelectAllCheckBox$p(PermissionRequestActivity permissionRequestActivity) {
        CheckBox checkBox = permissionRequestActivity.c;
        if (checkBox == null) {
            Intrinsics.c("selectAllCheckBox");
        }
        return checkBox;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ&\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0016\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010J\u000e\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ \u0010\u0012\u001a\u00020\n2\u0016\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/xiaomi/smarthome/framework/permission/PermissionRequestActivity$Companion;", "", "()V", "ANIMATION_DURATION", "", "ARG_KEYS_PERMISSION", "", "REQUEST_CODE", "", "check", "", "activity", "Landroid/app/Activity;", "permissions", "Ljava/util/ArrayList;", "Lcom/xiaomi/smarthome/framework/permission/PermissionBean;", "Lkotlin/collections/ArrayList;", "checkPermissionPass", "shouldShow", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
    public static final class Companion {
        private final boolean a(ArrayList<PermissionBean> arrayList) {
            return true;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean a(@NotNull Activity activity) {
            Activity activity2 = activity;
            Intrinsics.f(activity2, "activity");
            String[] strArr = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
            if (Build.VERSION.SDK_INT >= 29) {
                strArr = new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_BACKGROUND_LOCATION"};
            }
            String string = activity2.getString(R.string.permission_name_location);
            Intrinsics.b(string, "activity.getString(R.str…permission_name_location)");
            String string2 = activity2.getString(R.string.permission_location_rational_desc_new);
            Intrinsics.b(string2, "activity.getString(R.str…cation_rational_desc_new)");
            String string3 = activity2.getString(R.string.permission_name_storage);
            Intrinsics.b(string3, "activity.getString(R.str….permission_name_storage)");
            String string4 = activity2.getString(R.string.permission_storage_rational_desc_new);
            Intrinsics.b(string4, "activity.getString(R.str…torage_rational_desc_new)");
            String string5 = activity2.getString(R.string.permission_name_phone_state);
            Intrinsics.b(string5, "activity.getString(R.str…mission_name_phone_state)");
            String string6 = activity2.getString(R.string.permission_phone_state_rational_desc_new);
            Intrinsics.b(string6, "activity.getString(R.str…_state_rational_desc_new)");
            return a(activity2, CollectionsKt.d((T[]) new PermissionBean[]{new PermissionBean(strArr, string, string2, false, 8, (DefaultConstructorMarker) null), new PermissionBean(new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, string3, string4, false, 8, (DefaultConstructorMarker) null), new PermissionBean(new String[]{"android.permission.READ_PHONE_STATE"}, string5, string6, false, 8, (DefaultConstructorMarker) null)}));
        }

        public final boolean a(@NotNull Activity activity, @NotNull ArrayList<PermissionBean> arrayList) {
            Intrinsics.f(activity, "activity");
            Intrinsics.f(arrayList, "permissions");
            if (!a(arrayList)) {
                return false;
            }
            Iterable iterable = arrayList;
            Collection arrayList2 = new ArrayList();
            Iterator it = iterable.iterator();
            while (true) {
                boolean z = true;
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                PermissionBean permissionBean = (PermissionBean) next;
                if (permissionBean.a() || permissionBean.b()) {
                    z = false;
                }
                if (z) {
                    arrayList2.add(next);
                }
            }
            if (((List) arrayList2).isEmpty()) {
                return false;
            }
            Collection arrayList3 = new ArrayList();
            for (Object next2 : iterable) {
                PermissionBean permissionBean2 = (PermissionBean) next2;
                if ((!permissionBean2.b()) || permissionBean2.a()) {
                    arrayList3.add(next2);
                }
            }
            Intent intent = new Intent(activity, PermissionRequestActivity.class);
            intent.putParcelableArrayListExtra(PermissionRequestActivity.ARG_KEYS_PERMISSION, (ArrayList) arrayList3);
            activity.startActivityForResult(intent, PermissionRequestActivity.REQUEST_CODE);
            return true;
        }

        public final boolean b(@NotNull Activity activity) {
            Activity activity2 = activity;
            Intrinsics.f(activity2, "activity");
            String string = activity2.getString(R.string.permission_name_location);
            Intrinsics.b(string, "activity.getString(R.str…permission_name_location)");
            String string2 = activity2.getString(R.string.permission_location_rational_desc_new);
            Intrinsics.b(string2, "activity.getString(R.str…cation_rational_desc_new)");
            String string3 = activity2.getString(R.string.permission_name_storage);
            Intrinsics.b(string3, "activity.getString(R.str….permission_name_storage)");
            String string4 = activity2.getString(R.string.permission_storage_rational_desc_new);
            Intrinsics.b(string4, "activity.getString(R.str…torage_rational_desc_new)");
            String string5 = activity2.getString(R.string.permission_name_phone_state);
            Intrinsics.b(string5, "activity.getString(R.str…mission_name_phone_state)");
            String string6 = activity2.getString(R.string.permission_phone_state_rational_desc_new);
            Intrinsics.b(string6, "activity.getString(R.str…_state_rational_desc_new)");
            PermissionBean[] permissionBeanArr = {new PermissionBean(new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}, string, string2, false, 8, (DefaultConstructorMarker) null), new PermissionBean(new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, string3, string4, false, 8, (DefaultConstructorMarker) null), new PermissionBean(new String[]{"android.permission.READ_PHONE_STATE"}, string5, string6, false, 8, (DefaultConstructorMarker) null)};
            Collection arrayList = new ArrayList();
            for (Object next : CollectionsKt.d((T[]) permissionBeanArr)) {
                PermissionBean permissionBean = (PermissionBean) next;
                if (!permissionBean.a() && !permissionBean.b()) {
                    arrayList.add(next);
                }
            }
            return ((List) arrayList).isEmpty();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        String str;
        super.onCreate(bundle);
        setContentView(R.layout.activity_permission_request);
        if (bundle != null) {
            finish();
            return;
        }
        ArrayList parcelableArrayListExtra = getIntent().getParcelableArrayListExtra(ARG_KEYS_PERMISSION);
        Intrinsics.b(parcelableArrayListExtra, "intent.getParcelableArra…xtra(ARG_KEYS_PERMISSION)");
        this.f17110a = parcelableArrayListExtra;
        List<PermissionBean> list = this.f17110a;
        if (list == null) {
            Intrinsics.c("permissions");
        }
        if (list.isEmpty()) {
            finish();
            return;
        }
        View findViewById = findViewById(R.id.recycler);
        Intrinsics.b(findViewById, "findViewById(R.id.recycler)");
        this.b = (RecyclerView) findViewById;
        RecyclerView recyclerView = this.b;
        if (recyclerView == null) {
            Intrinsics.c("recyclerView");
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView2 = this.b;
        if (recyclerView2 == null) {
            Intrinsics.c("recyclerView");
        }
        recyclerView2.setAdapter(new Adapter());
        RecyclerView recyclerView3 = this.b;
        if (recyclerView3 == null) {
            Intrinsics.c("recyclerView");
        }
        recyclerView3.addItemDecoration(new ItemDivider(DisplayUtils.a((Activity) this, 24.0f), 1));
        ((TextView) findViewById(R.id.next_btn)).setOnClickListener(new PermissionRequestActivity$onCreate$1(this));
        List<PermissionBean> list2 = this.f17110a;
        if (list2 == null) {
            Intrinsics.c("permissions");
        }
        Collection arrayList = new ArrayList();
        for (Object next : list2) {
            if (!((PermissionBean) next).f()) {
                arrayList.add(next);
            }
        }
        this.e.addAll((List) arrayList);
        View findViewById2 = findViewById(R.id.select_all_check_box);
        Intrinsics.b(findViewById2, "findViewById<CheckBox>(R.id.select_all_check_box)");
        this.c = (CheckBox) findViewById2;
        CheckBox checkBox = this.c;
        if (checkBox == null) {
            Intrinsics.c("selectAllCheckBox");
        }
        int size = this.e.size();
        List<PermissionBean> list3 = this.f17110a;
        if (list3 == null) {
            Intrinsics.c("permissions");
        }
        checkBox.setChecked(size == list3.size());
        View findViewById3 = findViewById(R.id.select_all);
        findViewById3.setOnClickListener(new PermissionRequestActivity$onCreate$2(this));
        try {
            StringCompanionObject stringCompanionObject = StringCompanionObject.f2835a;
            String string = getString(R.string.we_wanna_require_those_permissions);
            Intrinsics.b(string, "getString(R.string.we_wa…equire_those_permissions)");
            Object[] objArr = {getString(R.string.app_name2)};
            str = String.format(string, Arrays.copyOf(objArr, objArr.length));
            Intrinsics.b(str, "java.lang.String.format(format, *args)");
        } catch (Exception unused) {
            str = getString(R.string.we_wanna_require_those_permissions);
        }
        View findViewById4 = findViewById(R.id.request_permission_desc);
        Intrinsics.b(findViewById4, "findViewById<TextView>(R….request_permission_desc)");
        ((TextView) findViewById4).setText(str);
        findViewById3.setBackgroundResource(R.drawable.selector_list_item_new);
        View findViewById5 = findViewById(R.id.module_a_3_return_btn);
        Intrinsics.b(findViewById5, "findViewById<TextView>(R.id.module_a_3_return_btn)");
        this.d = findViewById5;
        View view = this.d;
        if (view == null) {
            Intrinsics.c("backBtn");
        }
        view.setOnClickListener(new PermissionRequestActivity$onCreate$3(this));
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0004\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u001c\u0010\u0007\u001a\u00020\b2\n\u0010\t\u001a\u00060\u0002R\u00020\u00032\u0006\u0010\n\u001a\u00020\u0006H\u0016J\u001c\u0010\u000b\u001a\u00060\u0002R\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0006H\u0016¨\u0006\u000f"}, d2 = {"Lcom/xiaomi/smarthome/framework/permission/PermissionRequestActivity$Adapter;", "Landroid/support/v7/widget/RecyclerView$Adapter;", "Lcom/xiaomi/smarthome/framework/permission/PermissionRequestActivity$MyViewHolder;", "Lcom/xiaomi/smarthome/framework/permission/PermissionRequestActivity;", "(Lcom/xiaomi/smarthome/framework/permission/PermissionRequestActivity;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "p1", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
    private final class Adapter extends RecyclerView.Adapter<MyViewHolder> {
        public Adapter() {
        }

        @NotNull
        /* renamed from: a */
        public MyViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
            Intrinsics.f(viewGroup, "parent");
            View inflate = PermissionRequestActivity.this.getLayoutInflater().inflate(R.layout.permission_item, viewGroup, false);
            inflate.setBackgroundResource(R.drawable.selector_list_item_new);
            PermissionRequestActivity permissionRequestActivity = PermissionRequestActivity.this;
            Intrinsics.b(inflate, NetRequestWarningActivity.KEY_ITEM);
            return new MyViewHolder(permissionRequestActivity, inflate);
        }

        public int getItemCount() {
            return PermissionRequestActivity.access$getPermissions$p(PermissionRequestActivity.this).size();
        }

        /* renamed from: a */
        public void onBindViewHolder(@NotNull MyViewHolder myViewHolder, int i) {
            Intrinsics.f(myViewHolder, Constants.HOLDER);
            myViewHolder.a((PermissionBean) PermissionRequestActivity.access$getPermissions$p(PermissionRequestActivity.this).get(i));
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/xiaomi/smarthome/framework/permission/PermissionRequestActivity$MyViewHolder;", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Lcom/xiaomi/smarthome/framework/permission/PermissionRequestActivity;Landroid/view/View;)V", "bind", "", "permissionBean", "Lcom/xiaomi/smarthome/framework/permission/PermissionBean;", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
    private final class MyViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ PermissionRequestActivity f17113a;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public MyViewHolder(PermissionRequestActivity permissionRequestActivity, @NotNull View view) {
            super(view);
            Intrinsics.f(view, "view");
            this.f17113a = permissionRequestActivity;
        }

        public final void a(@NotNull PermissionBean permissionBean) {
            Context context;
            int i;
            Intrinsics.f(permissionBean, "permissionBean");
            if (permissionBean.f()) {
                context = this.f17113a.getContext();
                i = R.string.optional;
            } else {
                context = this.f17113a.getContext();
                i = R.string.require;
            }
            String string = context.getString(i);
            View findViewById = this.itemView.findViewById(R.id.permission_name);
            Intrinsics.b(findViewById, "itemView.findViewById<Te…ew>(R.id.permission_name)");
            ((TextView) findViewById).setText(permissionBean.d() + " (" + string + Operators.BRACKET_END);
            View findViewById2 = this.itemView.findViewById(R.id.permission_desc);
            Intrinsics.b(findViewById2, "itemView.findViewById<Te…ew>(R.id.permission_desc)");
            ((TextView) findViewById2).setText(permissionBean.e());
            CheckBox checkBox = (CheckBox) this.itemView.findViewById(R.id.check_box);
            Intrinsics.b(checkBox, "checkBox");
            checkBox.setChecked(this.f17113a.e.contains(permissionBean));
            if (permissionBean.f()) {
                checkBox.setOnCheckedChangeListener(new PermissionRequestActivity$MyViewHolder$bind$1(this, permissionBean));
                this.itemView.setOnClickListener(new PermissionRequestActivity$MyViewHolder$bind$2(checkBox));
            }
            checkBox.setAlpha(permissionBean.f() ? 1.0f : 0.3f);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J(\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J \u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u000e\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/xiaomi/smarthome/framework/permission/PermissionRequestActivity$ItemDivider;", "Landroid/support/v7/widget/RecyclerView$ItemDecoration;", "mLeft", "", "mDividerHeight", "(II)V", "mPaint", "Landroid/graphics/Paint;", "mWhitePaint", "getItemOffsets", "", "outRect", "Landroid/graphics/Rect;", "view", "Landroid/view/View;", "parent", "Landroid/support/v7/widget/RecyclerView;", "state", "Landroid/support/v7/widget/RecyclerView$State;", "onDrawOver", "c", "Landroid/graphics/Canvas;", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
    private static final class ItemDivider extends RecyclerView.ItemDecoration {

        /* renamed from: a  reason: collision with root package name */
        private final Paint f17112a = new Paint(1);
        private final Paint b = new Paint(1);
        private final int c;
        private final int d;

        public ItemDivider(int i, int i2) {
            this.c = i;
            this.d = i2;
            this.b.setColor(-1);
            this.f17112a.setColor(0);
            this.f17112a.setAlpha((int) 38.25f);
        }

        public void getItemOffsets(@NotNull Rect rect, @NotNull View view, @NotNull RecyclerView recyclerView, @NotNull RecyclerView.State state) {
            Intrinsics.f(rect, "outRect");
            Intrinsics.f(view, "view");
            Intrinsics.f(recyclerView, "parent");
            Intrinsics.f(state, "state");
            int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
            rect.bottom = this.d;
            if (childAdapterPosition == 0) {
                rect.top = this.d;
            }
        }

        public void onDrawOver(@NotNull Canvas canvas, @NotNull RecyclerView recyclerView, @NotNull RecyclerView.State state) {
            Intrinsics.f(canvas, "c");
            Intrinsics.f(recyclerView, "parent");
            Intrinsics.f(state, "state");
            super.onDrawOver(canvas, recyclerView, state);
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            if (linearLayoutManager != null) {
                int childCount = linearLayoutManager.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = linearLayoutManager.getChildAt(i);
                    if (childAt != null) {
                        if (i == 0) {
                            Intrinsics.b(childAt, "it");
                            canvas.drawRect((float) this.c, (float) childAt.getTop(), (float) childAt.getWidth(), (float) (childAt.getTop() + this.d), this.f17112a);
                        }
                        if (i == linearLayoutManager.getItemCount() - 1) {
                            Intrinsics.b(childAt, "it");
                            canvas.drawRect(0.0f, (float) childAt.getBottom(), (float) childAt.getWidth(), (float) (childAt.getBottom() + this.d), this.f17112a);
                        } else {
                            Intrinsics.b(childAt, "it");
                            canvas.drawRect((float) this.c, (float) childAt.getBottom(), (float) childAt.getWidth(), (float) (childAt.getBottom() + this.d), this.f17112a);
                        }
                    }
                }
            }
        }
    }
}
