package com.xiaomi.smarthome.device;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.xiaomi.infrared.InifraredContants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.config.SHConfig;
import com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.device.ChooseDeviceNestedParent;
import com.xiaomi.smarthome.device.DeviceScanManager;
import com.xiaomi.smarthome.device.api.DevicelibApi;
import com.xiaomi.smarthome.device.choosedevice.ChooseCollapsedGridView;
import com.xiaomi.smarthome.device.choosedevice.ChooseDeviceActivity;
import com.xiaomi.smarthome.device.choosedevice.VerticalSlidingTab;
import com.xiaomi.smarthome.device.choosedevice.XLinearLayout;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.framework.page.BaseFragment;
import com.xiaomi.smarthome.framework.webview.WebShellActivity;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.smarthome.stat.StatClick;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class ChooseDeviceManually extends BaseFragment implements DeviceScanManager.OnScanListener {

    /* renamed from: a  reason: collision with root package name */
    public static final String f14787a = "sub_category_name";
    private static final String c = "ChooseDeviceManually";
    private static final String d = "category_pref_category_data_key";
    private static final String e = "category_pref_category_locale_key";
    private static final String f = "category_pref_category_time_key";
    private XLinearLayout A;
    private View B;
    private DeviceScanManager C;
    private TextView D;
    private InnerAdapter E;
    /* access modifiers changed from: private */
    public List<SubCategoryData> F = new ArrayList();
    /* access modifiers changed from: private */
    public List<String> G = new ArrayList();
    /* access modifiers changed from: private */
    public int H;
    private Runnable I;
    /* access modifiers changed from: private */
    public Runnable J;
    /* access modifiers changed from: private */
    public ChooseDeviceNestedParent.OnScanViewChangeListener K;
    boolean b;
    /* access modifiers changed from: private */
    public VerticalSlidingTab g;
    private View h;
    private RecyclerView i;
    private ChooseDeviceNestedParent j;
    /* access modifiers changed from: private */
    public ChooseManuallyListener k;
    /* access modifiers changed from: private */
    public List<PluginRecord> l = new ArrayList();
    private Map<Integer, Category> m = new HashMap();
    private Map<Integer, Category> n = new HashMap();
    /* access modifiers changed from: private */
    public Map<String, Integer> o = new HashMap();
    private List<Pair<String, List<Pair<String, List<PluginRecord>>>>> p = new ArrayList();
    private View q;
    private View r;
    private boolean s;
    private List<ModelGroupInfo> t = new ArrayList();
    private final Object u = new Object();
    private String v;
    /* access modifiers changed from: private */
    public List<String> w;
    /* access modifiers changed from: private */
    public AddDeviceFragmentListener x;
    private View y;
    /* access modifiers changed from: private */
    public ChooseConnectDeviceAdapter z;

    public interface AddDeviceFragmentListener {
        void addFragment();
    }

    public interface ChooseManuallyListener {
        void chooseConnectDevice(PluginRecord pluginRecord);
    }

    public ChooseDeviceManually() {
        this.o.put(InifraredContants.e, 0);
        this.o.put(InifraredContants.d, 1);
        this.o.put(InifraredContants.h, 2);
        this.o.put(InifraredContants.g, 3);
        this.o.put(InifraredContants.i, 4);
        this.o.put(InifraredContants.k, 5);
        this.o.put(InifraredContants.o, 6);
        this.o.put(InifraredContants.l, 7);
        this.o.put(InifraredContants.j, 8);
        this.o.put(InifraredContants.n, 9);
        this.o.put(InifraredContants.m, 10);
        this.o.put(InifraredContants.p, 11);
        this.o.put(InifraredContants.q, 12);
        this.I = new Runnable() {
            public final void run() {
                ChooseDeviceManually.this.j();
            }
        };
        this.J = new Runnable() {
            public final void run() {
                ChooseDeviceManually.this.h();
            }
        };
        this.b = false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void j() {
        synchronized (this.u) {
            if (this.m.size() == 0) {
                d();
            }
        }
    }

    public static class SubCategoryData {

        /* renamed from: a  reason: collision with root package name */
        public String f14800a;
        public String b;
        public List<PluginRecord> c;

        public SubCategoryData(String str, String str2, List<PluginRecord> list) {
            this.f14800a = str;
            this.b = str2;
            this.c = list;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void h() {
        synchronized (this.u) {
            if (this.p.size() <= 0) {
                this.p = c();
                this.F = new ArrayList();
                this.G = new ArrayList();
                for (Pair next : this.p) {
                    String str = (String) next.first;
                    this.G.add(str);
                    for (Pair pair : (List) next.second) {
                        this.F.add(new SubCategoryData(str, (String) pair.first, (List) pair.second));
                    }
                }
                this.i.post(new Runnable() {
                    public final void run() {
                        ChooseDeviceManually.this.i();
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void i() {
        try {
            a(this.i, this.g);
            this.r.setVisibility(8);
            this.g.setTabNames(this.G);
            this.E.a(this.F);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.q == null) {
            int i2 = 0;
            this.q = layoutInflater.inflate(R.layout.choose_device_manually, viewGroup, false);
            this.i = (RecyclerView) this.q.findViewById(R.id.right_recycler);
            this.i.setLayoutManager(new LinearLayoutManager(getContext()));
            this.E = new InnerAdapter();
            this.i.setAdapter(this.E);
            this.i.addItemDecoration(new RecyclerView.ItemDecoration() {
                public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
                    rect.bottom = DisplayUtils.d(ChooseDeviceManually.this.getContext(), 26.0f);
                }
            });
            this.g = (VerticalSlidingTab) this.q.findViewById(R.id.left_list_view);
            this.r = this.q.findViewById(R.id.loading_mask);
            View view = this.r;
            if (!this.s) {
                i2 = 4;
            }
            view.setVisibility(i2);
            this.h = this.q.findViewById(R.id.scan_view);
            this.y = this.q.findViewById(R.id.scan_no_device_desc);
            this.y.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    String str;
                    STAT.d.K();
                    STAT.d.n(ChooseDeviceManually.this.z.getCount());
                    Intent intent = new Intent(ChooseDeviceManually.this.getActivity(), WebShellActivity.class);
                    Bundle bundle = new Bundle();
                    ServerBean F = CoreApi.a().F();
                    if (CoreApi.a().D()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("https://");
                        if (F != null) {
                            str = F.f1530a + ".";
                        } else {
                            str = "";
                        }
                        sb.append(str);
                        sb.append("home.mi.com/views/faqDetail.html?question=");
                        sb.append(ChooseDeviceManually.this.getString(R.string.param_question_cannot_find_near_device));
                        bundle.putString("url", sb.toString());
                    } else {
                        bundle.putString("url", "https://home.mi.com/views/faqDetail.html?question=" + ChooseDeviceManually.this.getString(R.string.param_question_cannot_find_near_device));
                    }
                    intent.putExtras(bundle);
                    ChooseDeviceManually.this.startActivity(intent);
                }
            });
            this.A = (XLinearLayout) this.q.findViewById(R.id.llDevice);
            this.B = this.q.findViewById(R.id.more_device);
            this.B.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (ChooseDeviceManually.this.x != null) {
                        ChooseDeviceManually.this.x.addFragment();
                    }
                }
            });
            this.D = (TextView) this.q.findViewById(R.id.more_scan_device_cnt);
            this.j = (ChooseDeviceNestedParent) this.q.findViewById(R.id.nested_scroll_parent);
            this.j.setScanView(this.h);
            this.z = new ChooseConnectDeviceAdapter(this.mContext, this, getActivity().getIntent(), R.layout.choose_device_scan_list_item, false);
            this.A.setAdapter((BaseAdapter) this.z, true);
            SHApplication.getGlobalWorkerHandler().post(new Runnable() {
                public final void run() {
                    ChooseDeviceManually.this.f();
                }
            });
            ((ChooseDeviceNestedParent) this.q.findViewById(R.id.nested_scroll_parent)).setOnScanViewChangeListener(new ChooseDeviceNestedParent.OnScanViewChangeListener() {
                public void a() {
                    if (ChooseDeviceManually.this.K != null) {
                        ChooseDeviceManually.this.K.a();
                    }
                }

                public void a(float f) {
                    if (ChooseDeviceManually.this.K != null) {
                        ChooseDeviceManually.this.K.a(f);
                    }
                }

                public void b() {
                    if (ChooseDeviceManually.this.K != null) {
                        ChooseDeviceManually.this.K.b();
                    }
                }
            });
        }
        if (this.l.size() > 0) {
            a(this.l);
        }
        if (this.C != null) {
            this.C.getScanCache(this);
        }
        return this.q;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void f() {
        CoreApi.a().a((Context) getActivity(), (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
            public final void onCoreReady() {
                ChooseDeviceManually.this.g();
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void g() {
        if (isValid()) {
            ((ChooseDeviceActivity) getActivity()).getWorkerHandler().post(this.I);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(final RecyclerView recyclerView, final VerticalSlidingTab verticalSlidingTab) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                ChooseDeviceManually.this.b = false;
            }

            public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
                int findFirstVisibleItemPosition;
                if (!ChooseDeviceManually.this.b) {
                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                    if ((layoutManager instanceof LinearLayoutManager) && (findFirstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition()) >= 0 && findFirstVisibleItemPosition < ChooseDeviceManually.this.F.size()) {
                        String str = ((SubCategoryData) ChooseDeviceManually.this.F.get(findFirstVisibleItemPosition)).f14800a;
                        if (!TextUtils.isEmpty(str)) {
                            int indexOf = ChooseDeviceManually.this.G.indexOf(str);
                            verticalSlidingTab.setCurrentPosition(indexOf);
                            boolean z = ChooseDeviceManually.this.w != null && ChooseDeviceManually.this.w.size() > 0;
                            StatClick statClick = STAT.d;
                            if (!z) {
                                indexOf++;
                            }
                            statClick.c(indexOf, 2);
                        }
                    }
                }
            }
        });
        verticalSlidingTab.setOnTabPositionChangeListener(new VerticalSlidingTab.OnTabPositionChangeListener() {
            public void a(int i, int i2) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (linearLayoutManager != null && i2 >= 0 && i2 < ChooseDeviceManually.this.G.size()) {
                    String str = (String) ChooseDeviceManually.this.G.get(i2);
                    boolean z = false;
                    for (int i3 = 0; i3 < ChooseDeviceManually.this.F.size(); i3++) {
                        if (TextUtils.equals(str, ((SubCategoryData) ChooseDeviceManually.this.F.get(i3)).f14800a)) {
                            ChooseDeviceManually.this.b = true;
                            linearLayoutManager.scrollToPositionWithOffset(i3, 0);
                            if (ChooseDeviceManually.this.w != null && ChooseDeviceManually.this.w.size() > 0) {
                                z = true;
                            }
                            StatClick statClick = STAT.d;
                            if (!z) {
                                i2++;
                            }
                            statClick.c(i2, 1);
                            return;
                        }
                    }
                }
            }
        });
    }

    private class InnerAdapter extends RecyclerView.Adapter<VH> {
        private final List<SubCategoryData> b;

        private InnerAdapter() {
            this.b = new ArrayList();
        }

        public void a(List<SubCategoryData> list) {
            this.b.clear();
            this.b.addAll(list);
            notifyDataSetChanged();
        }

        /* renamed from: a */
        public VH onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new VH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.choose_device_grid_collpased_layout, viewGroup, false));
        }

        @SuppressLint({"SetTextI18n"})
        /* renamed from: a */
        public void onBindViewHolder(VH vh, int i) {
            vh.a(this.b.get(i));
        }

        public int getItemCount() {
            return this.b.size();
        }

        class VH extends RecyclerView.ViewHolder {
            VH(View view) {
                super(view);
            }

            public void a(SubCategoryData subCategoryData) {
                ChooseCollapsedGridView chooseCollapsedGridView = (ChooseCollapsedGridView) this.itemView;
                chooseCollapsedGridView.setSubCategoryData(subCategoryData.f14800a, subCategoryData.b, subCategoryData.c);
                chooseCollapsedGridView.setChooseDeviceListener(
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0012: INVOKE  
                      (r0v1 'chooseCollapsedGridView' com.xiaomi.smarthome.device.choosedevice.ChooseCollapsedGridView)
                      (wrap: com.xiaomi.smarthome.device.-$$Lambda$ChooseDeviceManually$InnerAdapter$VH$pOLvtQMalg9GJ4oPNK0Z4e1tuB8 : 0x000f: CONSTRUCTOR  (r4v2 com.xiaomi.smarthome.device.-$$Lambda$ChooseDeviceManually$InnerAdapter$VH$pOLvtQMalg9GJ4oPNK0Z4e1tuB8) = 
                      (r3v0 'this' com.xiaomi.smarthome.device.ChooseDeviceManually$InnerAdapter$VH A[THIS])
                     call: com.xiaomi.smarthome.device.-$$Lambda$ChooseDeviceManually$InnerAdapter$VH$pOLvtQMalg9GJ4oPNK0Z4e1tuB8.<init>(com.xiaomi.smarthome.device.ChooseDeviceManually$InnerAdapter$VH):void type: CONSTRUCTOR)
                     com.xiaomi.smarthome.device.choosedevice.ChooseCollapsedGridView.setChooseDeviceListener(com.xiaomi.smarthome.device.ChooseDeviceManually$ChooseManuallyListener):void type: VIRTUAL in method: com.xiaomi.smarthome.device.ChooseDeviceManually.InnerAdapter.VH.a(com.xiaomi.smarthome.device.ChooseDeviceManually$SubCategoryData):void, dex: classes8.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                    	at jadx.core.codegen.ClassGen.addInnerClass(ClassGen.java:249)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:238)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                    	at jadx.core.codegen.ClassGen.addInnerClass(ClassGen.java:249)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:238)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                    	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                    	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                    Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000f: CONSTRUCTOR  (r4v2 com.xiaomi.smarthome.device.-$$Lambda$ChooseDeviceManually$InnerAdapter$VH$pOLvtQMalg9GJ4oPNK0Z4e1tuB8) = 
                      (r3v0 'this' com.xiaomi.smarthome.device.ChooseDeviceManually$InnerAdapter$VH A[THIS])
                     call: com.xiaomi.smarthome.device.-$$Lambda$ChooseDeviceManually$InnerAdapter$VH$pOLvtQMalg9GJ4oPNK0Z4e1tuB8.<init>(com.xiaomi.smarthome.device.ChooseDeviceManually$InnerAdapter$VH):void type: CONSTRUCTOR in method: com.xiaomi.smarthome.device.ChooseDeviceManually.InnerAdapter.VH.a(com.xiaomi.smarthome.device.ChooseDeviceManually$SubCategoryData):void, dex: classes8.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                    	... 59 more
                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.xiaomi.smarthome.device.-$$Lambda$ChooseDeviceManually$InnerAdapter$VH$pOLvtQMalg9GJ4oPNK0Z4e1tuB8, state: NOT_LOADED
                    	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:260)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:606)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                    	... 65 more
                    */
                /*
                    this = this;
                    android.view.View r0 = r3.itemView
                    com.xiaomi.smarthome.device.choosedevice.ChooseCollapsedGridView r0 = (com.xiaomi.smarthome.device.choosedevice.ChooseCollapsedGridView) r0
                    java.lang.String r1 = r4.f14800a
                    java.lang.String r2 = r4.b
                    java.util.List<com.xiaomi.smarthome.core.entity.plugin.PluginRecord> r4 = r4.c
                    r0.setSubCategoryData(r1, r2, r4)
                    com.xiaomi.smarthome.device.-$$Lambda$ChooseDeviceManually$InnerAdapter$VH$pOLvtQMalg9GJ4oPNK0Z4e1tuB8 r4 = new com.xiaomi.smarthome.device.-$$Lambda$ChooseDeviceManually$InnerAdapter$VH$pOLvtQMalg9GJ4oPNK0Z4e1tuB8
                    r4.<init>(r3)
                    r0.setChooseDeviceListener(r4)
                    com.xiaomi.smarthome.device.ChooseDeviceManually$InnerAdapter$VH$1 r4 = new com.xiaomi.smarthome.device.ChooseDeviceManually$InnerAdapter$VH$1
                    r4.<init>()
                    r0.setOnManualCategoryDeviceClicked(r4)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.ChooseDeviceManually.InnerAdapter.VH.a(com.xiaomi.smarthome.device.ChooseDeviceManually$SubCategoryData):void");
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void a(PluginRecord pluginRecord) {
                if (ChooseDeviceManually.this.k != null) {
                    ChooseDeviceManually.this.k.chooseConnectDevice(pluginRecord);
                }
            }
        }
    }

    public void onScan(List<?> list) {
        this.H = list.size();
        if (this.H == 0) {
            this.D.setText("");
            this.D.setVisibility(8);
            this.y.setVisibility(0);
            this.A.setVisibility(4);
            this.B.setVisibility(4);
            return;
        }
        this.y.setVisibility(4);
        this.A.setVisibility(0);
        if (this.H > 3) {
            this.D.setVisibility(0);
            TextView textView = this.D;
            textView.setText((this.H - 3) + "");
            this.B.setVisibility(0);
        } else {
            this.D.setVisibility(8);
            this.B.setVisibility(4);
        }
        this.z.a((Collection<?>) list);
        this.A.setAdapter((BaseAdapter) this.z, true);
    }

    public void onResume() {
        super.onResume();
        STAT.c.b();
    }

    public void a(ChooseDeviceNestedParent.OnScanViewChangeListener onScanViewChangeListener) {
        this.K = onScanViewChangeListener;
    }

    public boolean a() {
        return this.j != null && this.j.getTop() < 0;
    }

    private List<Pair<String, List<Pair<String, List<PluginRecord>>>>> c() {
        TreeMap treeMap = new TreeMap(new Comparator() {
            public final int compare(Object obj, Object obj2) {
                return ChooseDeviceManually.this.b((Integer) obj, (Integer) obj2);
            }
        });
        for (PluginRecord next : this.l) {
            if (next.c() != null) {
                int K2 = next.c().K();
                if (IRDeviceUtil.a(next)) {
                    K2 = Integer.MAX_VALUE;
                }
                Category category = this.n.get(Integer.valueOf(K2));
                if (category != null) {
                    if (treeMap.get(Integer.valueOf(category.b)) == null) {
                        TreeMap treeMap2 = new TreeMap(new Comparator() {
                            public final int compare(Object obj, Object obj2) {
                                return ChooseDeviceManually.this.a((Integer) obj, (Integer) obj2);
                            }
                        });
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(next);
                        treeMap2.put(Integer.valueOf(K2), arrayList);
                        treeMap.put(Integer.valueOf(category.b), treeMap2);
                    } else {
                        TreeMap treeMap3 = (TreeMap) treeMap.get(Integer.valueOf(category.b));
                        if (treeMap3.get(Integer.valueOf(K2)) == null) {
                            ArrayList arrayList2 = new ArrayList();
                            arrayList2.add(next);
                            treeMap3.put(Integer.valueOf(K2), arrayList2);
                        } else {
                            ((List) treeMap3.get(Integer.valueOf(K2))).add(next);
                        }
                    }
                }
            }
        }
        for (TreeMap values : treeMap.values()) {
            for (List sort : values.values()) {
                Collections.sort(sort, new Comparator<PluginRecord>() {
                    public boolean equals(Object obj) {
                        return false;
                    }

                    /* renamed from: a */
                    public int compare(PluginRecord pluginRecord, PluginRecord pluginRecord2) {
                        if (IRDeviceUtil.a(pluginRecord) && IRDeviceUtil.a(pluginRecord2)) {
                            Integer num = (Integer) ChooseDeviceManually.this.o.get(pluginRecord.o());
                            Integer num2 = (Integer) ChooseDeviceManually.this.o.get(pluginRecord2.o());
                            if (num != null && num2 != null) {
                                return num.intValue() - num2.intValue();
                            }
                            Log.e("ChooseDevice", "---后台新增了APP不知道的红外品类---");
                            return 1;
                        } else if (pluginRecord == null || pluginRecord.c() == null) {
                            return 1;
                        } else {
                            if (pluginRecord2 == null || pluginRecord2.c() == null) {
                                return -1;
                            }
                            if (pluginRecord.c().M() == 0 && pluginRecord2.c().M() == 0) {
                                return 0;
                            }
                            if (pluginRecord.c().M() == 0) {
                                return 1;
                            }
                            if (pluginRecord2.c().M() == 0) {
                                return -1;
                            }
                            return pluginRecord.c().M() - pluginRecord2.c().M();
                        }
                    }
                });
            }
        }
        ArrayList arrayList3 = new ArrayList();
        if (this.w != null && this.w.size() > 0) {
            HashMap hashMap = new HashMap();
            for (PluginRecord next2 : this.l) {
                hashMap.put(next2.o(), next2);
            }
            ArrayList arrayList4 = new ArrayList();
            for (String str : this.w) {
                PluginRecord pluginRecord = (PluginRecord) hashMap.get(str);
                if (pluginRecord != null) {
                    arrayList4.add(pluginRecord);
                }
            }
            if (arrayList4.size() > 0) {
                ArrayList arrayList5 = new ArrayList();
                arrayList5.add(Pair.create(this.v, arrayList4));
                arrayList3.add(Pair.create(this.v, arrayList5));
            }
        }
        for (Map.Entry entry : treeMap.entrySet()) {
            if (this.m.get(entry.getKey()) != null) {
                ArrayList arrayList6 = new ArrayList();
                for (Map.Entry entry2 : ((TreeMap) entry.getValue()).entrySet()) {
                    if (this.n.get(entry2.getKey()) != null) {
                        String str2 = this.n.get(entry2.getKey()).d;
                        ArrayList arrayList7 = new ArrayList();
                        arrayList6.add(Pair.create(str2, arrayList7));
                        if (((Integer) entry2.getKey()).intValue() == 17) {
                            List list = (List) entry2.getValue();
                            c((List<PluginRecord>) list);
                            arrayList7.addAll(list);
                            ArrayList arrayList8 = new ArrayList();
                            Iterator it = arrayList7.iterator();
                            while (it.hasNext()) {
                                PluginRecord pluginRecord2 = (PluginRecord) it.next();
                                if (pluginRecord2.c().t() == Device.PID_VIRTUAL_GROUP) {
                                    arrayList8.add(pluginRecord2);
                                }
                            }
                            if (arrayList8.size() > 0) {
                                arrayList7.removeAll(arrayList8);
                                arrayList7.addAll(1, arrayList8);
                            }
                        } else {
                            arrayList7.addAll((Collection) entry2.getValue());
                        }
                    }
                }
                if (arrayList6.size() > 0) {
                    arrayList3.add(Pair.create(this.m.get(entry.getKey()).d, arrayList6));
                }
            }
        }
        return arrayList3;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ int b(Integer num, Integer num2) {
        if (this.m.get(num) == null || this.m.get(num2) == null) {
            return this.m.get(num) == null ? -1 : 1;
        }
        return this.m.get(num).c - this.m.get(num2).c;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ int a(Integer num, Integer num2) {
        if (this.n.get(num) == null || this.n.get(num2) == null) {
            return this.n.get(num) == null ? -1 : 1;
        }
        if (this.n.get(num).c == 0 || this.n.get(num2).c == 0) {
            return this.n.get(num).f14796a - this.n.get(num2).f14796a;
        }
        return this.n.get(num).c - this.n.get(num2).c;
    }

    private void c(List<PluginRecord> list) {
        boolean z2;
        if (this.t.size() > 0) {
            for (ModelGroupInfo next : this.t) {
                Iterator<PluginRecord> it = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (TextUtils.equals(it.next().o(), next.d)) {
                            z2 = true;
                            break;
                        }
                    } else {
                        z2 = false;
                        break;
                    }
                }
                if (!z2) {
                    PluginRecord pluginRecord = new PluginRecord();
                    PluginDeviceInfo pluginDeviceInfo = new PluginDeviceInfo();
                    pluginDeviceInfo.b(next.f14912a);
                    pluginDeviceInfo.a(next.d);
                    pluginDeviceInfo.e(5);
                    pluginRecord.a(pluginDeviceInfo);
                    list.add(0, pluginRecord);
                }
            }
        }
    }

    private PluginRecord a(String str) {
        PluginDeviceInfo pluginDeviceInfo = new PluginDeviceInfo();
        pluginDeviceInfo.a(f14787a);
        pluginDeviceInfo.b(str);
        PluginRecord pluginRecord = new PluginRecord();
        pluginRecord.a(pluginDeviceInfo);
        return pluginRecord;
    }

    private void d() {
        Locale I2 = CoreApi.a().I();
        if (I2 == null) {
            I2 = Locale.getDefault();
        }
        String c2 = SHConfig.a().c(d);
        String c3 = SHConfig.a().c(e);
        long b2 = SHConfig.a().b(f);
        if (TextUtils.isEmpty(c2) || System.currentTimeMillis() - b2 >= 86400000 || !LocaleUtil.a(I2).equalsIgnoreCase(c3)) {
            e();
        } else {
            b(c2);
        }
    }

    /* access modifiers changed from: private */
    public void a(JSONObject jSONObject) {
        try {
            this.v = jSONObject.getString("tab_name");
            boolean z2 = jSONObject.getBoolean("show");
            if (!TextUtils.isEmpty(this.v) && z2) {
                JSONArray jSONArray = jSONObject.getJSONArray("newest_product");
                this.w = new ArrayList();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    this.w.add(jSONArray.getString(i2));
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void e() {
        DevicelibApi.getDeviceCategory(getActivity(), new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                ChooseDeviceManually.this.a(jSONObject.optJSONObject("top_category"));
                String optString = jSONObject.optString("catgory_info");
                if (!TextUtils.isEmpty(optString)) {
                    try {
                        if (ChooseDeviceManually.this.a(new JSONArray(optString))) {
                            SHConfig.a().a(ChooseDeviceManually.d, jSONObject.toString());
                            Locale I = CoreApi.a().I();
                            if (I == null) {
                                I = Locale.getDefault();
                            }
                            SHConfig.a().a(ChooseDeviceManually.e, LocaleUtil.a(I));
                            SHConfig.a().a(ChooseDeviceManually.f, System.currentTimeMillis());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (ChooseDeviceManually.this.l.size() > 0 && ChooseDeviceManually.this.isValid()) {
                        ((ChooseDeviceActivity) ChooseDeviceManually.this.getActivity()).getWorkerHandler().post(ChooseDeviceManually.this.J);
                    }
                }
            }

            public void onFailure(Error error) {
                ChooseDeviceManually.this.b(SHConfig.a().c(ChooseDeviceManually.d));
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(11:(2:3|4)|9|10|11|12|13|14|15|16|17|(1:27)(2:21|28)) */
    /* JADX WARNING: Can't wrap try/catch for region: R(12:3|4|9|10|11|12|13|14|15|16|17|(1:27)(2:21|28)) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x002a */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x0030 */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0041 A[Catch:{ Exception -> 0x0017 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(java.lang.String r4) {
        /*
            r3 = this;
            if (r4 == 0) goto L_0x0054
            int r0 = r4.length()
            if (r0 <= 0) goto L_0x0054
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0019 }
            r0.<init>(r4)     // Catch:{ JSONException -> 0x0019 }
            java.lang.String r1 = "top_category"
            org.json.JSONObject r0 = r0.optJSONObject(r1)     // Catch:{ JSONException -> 0x0019 }
            r3.a((org.json.JSONObject) r0)     // Catch:{ JSONException -> 0x0019 }
            goto L_0x001d
        L_0x0017:
            r4 = move-exception
            goto L_0x0051
        L_0x0019:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ Exception -> 0x0017 }
        L_0x001d:
            r0 = 0
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Exception -> 0x002a }
            r1.<init>(r4)     // Catch:{ Exception -> 0x002a }
            java.lang.String r2 = "catgory_info"
            org.json.JSONArray r1 = r1.getJSONArray(r2)     // Catch:{ Exception -> 0x002a }
            r0 = r1
        L_0x002a:
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch:{ Exception -> 0x0030 }
            r1.<init>(r4)     // Catch:{ Exception -> 0x0030 }
            r0 = r1
        L_0x0030:
            r3.a((org.json.JSONArray) r0)     // Catch:{ Exception -> 0x0017 }
            java.util.List<com.xiaomi.smarthome.core.entity.plugin.PluginRecord> r4 = r3.l     // Catch:{ Exception -> 0x0017 }
            int r4 = r4.size()     // Catch:{ Exception -> 0x0017 }
            if (r4 <= 0) goto L_0x0054
            boolean r4 = r3.isValid()     // Catch:{ Exception -> 0x0017 }
            if (r4 == 0) goto L_0x0054
            android.support.v4.app.FragmentActivity r4 = r3.getActivity()     // Catch:{ Exception -> 0x0017 }
            com.xiaomi.smarthome.device.choosedevice.ChooseDeviceActivity r4 = (com.xiaomi.smarthome.device.choosedevice.ChooseDeviceActivity) r4     // Catch:{ Exception -> 0x0017 }
            android.os.Handler r4 = r4.getWorkerHandler()     // Catch:{ Exception -> 0x0017 }
            java.lang.Runnable r0 = r3.J     // Catch:{ Exception -> 0x0017 }
            r4.post(r0)     // Catch:{ Exception -> 0x0017 }
            goto L_0x0054
        L_0x0051:
            r4.printStackTrace()
        L_0x0054:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.ChooseDeviceManually.b(java.lang.String):void");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c5  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0109 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:49:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(org.json.JSONArray r15) {
        /*
            r14 = this;
            r0 = 0
            if (r15 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            r3 = -1
            r3 = 0
            r4 = -1
            r5 = -1
        L_0x0012:
            r6 = 0
            r7 = 1
            int r8 = r15.length()     // Catch:{ Exception -> 0x009d }
            if (r3 >= r8) goto L_0x009b
            org.json.JSONObject r8 = r15.getJSONObject(r3)     // Catch:{ Exception -> 0x009d }
            com.xiaomi.smarthome.device.ChooseDeviceManually$Category r9 = new com.xiaomi.smarthome.device.ChooseDeviceManually$Category     // Catch:{ Exception -> 0x009d }
            r9.<init>()     // Catch:{ Exception -> 0x009d }
            java.lang.String r10 = "id"
            int r10 = r8.optInt(r10)     // Catch:{ Exception -> 0x009d }
            r9.f14796a = r10     // Catch:{ Exception -> 0x009d }
            java.lang.String r10 = "rank"
            int r10 = r8.optInt(r10)     // Catch:{ Exception -> 0x009d }
            r9.c = r10     // Catch:{ Exception -> 0x009d }
            java.lang.String r10 = "name"
            java.lang.String r10 = r8.optString(r10)     // Catch:{ Exception -> 0x009d }
            r9.d = r10     // Catch:{ Exception -> 0x009d }
            int r10 = r9.f14796a     // Catch:{ Exception -> 0x009d }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ Exception -> 0x009d }
            r1.put(r10, r9)     // Catch:{ Exception -> 0x009d }
            int r10 = r9.c     // Catch:{ Exception -> 0x009d }
            if (r5 >= r10) goto L_0x0052
            int r10 = r9.c     // Catch:{ Exception -> 0x009d }
            int r5 = r9.f14796a     // Catch:{ Exception -> 0x004f }
            r4 = r5
            r5 = r10
            goto L_0x0052
        L_0x004f:
            r15 = move-exception
            r5 = r10
            goto L_0x009e
        L_0x0052:
            java.lang.String r10 = "subcategory"
            org.json.JSONArray r8 = r8.optJSONArray(r10)     // Catch:{ Exception -> 0x009d }
            if (r8 == 0) goto L_0x0097
            java.lang.Object r10 = org.json.JSONObject.NULL     // Catch:{ Exception -> 0x009d }
            if (r8 != r10) goto L_0x005f
            goto L_0x0097
        L_0x005f:
            r10 = 0
        L_0x0060:
            int r11 = r8.length()     // Catch:{ Exception -> 0x009d }
            if (r10 >= r11) goto L_0x0097
            com.xiaomi.smarthome.device.ChooseDeviceManually$Category r11 = new com.xiaomi.smarthome.device.ChooseDeviceManually$Category     // Catch:{ Exception -> 0x009d }
            r11.<init>()     // Catch:{ Exception -> 0x009d }
            org.json.JSONObject r12 = r8.optJSONObject(r10)     // Catch:{ Exception -> 0x009d }
            java.lang.String r13 = "id"
            int r13 = r12.optInt(r13)     // Catch:{ Exception -> 0x009d }
            r11.f14796a = r13     // Catch:{ Exception -> 0x009d }
            java.lang.String r13 = "rank"
            int r13 = r12.optInt(r13)     // Catch:{ Exception -> 0x009d }
            r11.c = r13     // Catch:{ Exception -> 0x009d }
            java.lang.String r13 = "name"
            java.lang.String r12 = r12.optString(r13)     // Catch:{ Exception -> 0x009d }
            r11.d = r12     // Catch:{ Exception -> 0x009d }
            int r12 = r9.f14796a     // Catch:{ Exception -> 0x009d }
            r11.b = r12     // Catch:{ Exception -> 0x009d }
            int r12 = r11.f14796a     // Catch:{ Exception -> 0x009d }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch:{ Exception -> 0x009d }
            r2.put(r12, r11)     // Catch:{ Exception -> 0x009d }
            int r10 = r10 + 1
            goto L_0x0060
        L_0x0097:
            int r3 = r3 + 1
            goto L_0x0012
        L_0x009b:
            r15 = 1
            goto L_0x00a2
        L_0x009d:
            r15 = move-exception
        L_0x009e:
            r15.printStackTrace()
            r15 = 0
        L_0x00a2:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r4)
            java.lang.Object r3 = r1.get(r3)
            com.xiaomi.smarthome.device.ChooseDeviceManually$Category r3 = (com.xiaomi.smarthome.device.ChooseDeviceManually.Category) r3
            if (r3 == 0) goto L_0x00b3
            int r4 = r3.c
            int r4 = r4 + r7
            r3.c = r4
        L_0x00b3:
            com.xiaomi.smarthome.device.ChooseDeviceManually$Category r3 = new com.xiaomi.smarthome.device.ChooseDeviceManually$Category
            r3.<init>()
            r3.c = r5
            r4 = 2147483647(0x7fffffff, float:NaN)
            r3.f14796a = r4
            android.support.v4.app.FragmentActivity r5 = r14.getActivity()
            if (r5 == 0) goto L_0x00d2
            android.content.res.Resources r5 = r5.getResources()
            r8 = 2131494492(0x7f0c065c, float:1.8612494E38)
            java.lang.String r5 = r5.getString(r8)
            r3.d = r5
        L_0x00d2:
            com.xiaomi.smarthome.device.ChooseDeviceManually$Category r5 = new com.xiaomi.smarthome.device.ChooseDeviceManually$Category
            r5.<init>()
            r5.c = r0
            r5.f14796a = r4
            java.lang.String r4 = r3.d
            r5.d = r4
            int r4 = r3.f14796a
            r5.b = r4
            int r4 = r5.f14796a
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r2.put(r4, r5)
            int r4 = r3.f14796a
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r1.put(r4, r3)
            r14.m = r1
            r14.n = r2
            java.util.Map<java.lang.Integer, com.xiaomi.smarthome.device.ChooseDeviceManually$Category> r1 = r14.m
            int r1 = r1.size()
            if (r1 <= 0) goto L_0x010c
            java.util.Map<java.lang.Integer, com.xiaomi.smarthome.device.ChooseDeviceManually$Category> r1 = r14.n
            int r1 = r1.size()
            if (r1 <= 0) goto L_0x010c
            if (r15 == 0) goto L_0x010c
            r0 = 1
        L_0x010c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.ChooseDeviceManually.a(org.json.JSONArray):boolean");
    }

    public void a(ChooseManuallyListener chooseManuallyListener) {
        this.k = chooseManuallyListener;
    }

    public void a(AddDeviceFragmentListener addDeviceFragmentListener) {
        this.x = addDeviceFragmentListener;
    }

    public void onDetach() {
        super.onDetach();
        this.k = null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004e, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0050, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.util.List<com.xiaomi.smarthome.core.entity.plugin.PluginRecord> r3) {
        /*
            r2 = this;
            java.lang.Object r0 = r2.u
            monitor-enter(r0)
            int r1 = r3.size()     // Catch:{ all -> 0x0051 }
            if (r1 != 0) goto L_0x000b
            monitor-exit(r0)     // Catch:{ all -> 0x0051 }
            return
        L_0x000b:
            r2.l = r3     // Catch:{ all -> 0x0051 }
            android.support.v4.app.FragmentActivity r3 = r2.getActivity()     // Catch:{ all -> 0x0051 }
            if (r3 == 0) goto L_0x004f
            android.support.v4.app.FragmentActivity r3 = r2.getActivity()     // Catch:{ all -> 0x0051 }
            com.xiaomi.smarthome.device.choosedevice.ChooseDeviceActivity r3 = (com.xiaomi.smarthome.device.choosedevice.ChooseDeviceActivity) r3     // Catch:{ all -> 0x0051 }
            android.os.Handler r3 = r3.getWorkerHandler()     // Catch:{ all -> 0x0051 }
            if (r3 != 0) goto L_0x0020
            goto L_0x004f
        L_0x0020:
            android.view.View r3 = r2.q     // Catch:{ all -> 0x0051 }
            if (r3 != 0) goto L_0x0026
            monitor-exit(r0)     // Catch:{ all -> 0x0051 }
            return
        L_0x0026:
            java.util.Map<java.lang.Integer, com.xiaomi.smarthome.device.ChooseDeviceManually$Category> r3 = r2.m     // Catch:{ all -> 0x0051 }
            int r3 = r3.size()     // Catch:{ all -> 0x0051 }
            if (r3 <= 0) goto L_0x003e
            android.support.v4.app.FragmentActivity r3 = r2.getActivity()     // Catch:{ all -> 0x0051 }
            com.xiaomi.smarthome.device.choosedevice.ChooseDeviceActivity r3 = (com.xiaomi.smarthome.device.choosedevice.ChooseDeviceActivity) r3     // Catch:{ all -> 0x0051 }
            android.os.Handler r3 = r3.getWorkerHandler()     // Catch:{ all -> 0x0051 }
            java.lang.Runnable r1 = r2.J     // Catch:{ all -> 0x0051 }
            r3.post(r1)     // Catch:{ all -> 0x0051 }
            goto L_0x004d
        L_0x003e:
            android.support.v4.app.FragmentActivity r3 = r2.getActivity()     // Catch:{ all -> 0x0051 }
            com.xiaomi.smarthome.device.choosedevice.ChooseDeviceActivity r3 = (com.xiaomi.smarthome.device.choosedevice.ChooseDeviceActivity) r3     // Catch:{ all -> 0x0051 }
            android.os.Handler r3 = r3.getWorkerHandler()     // Catch:{ all -> 0x0051 }
            java.lang.Runnable r1 = r2.I     // Catch:{ all -> 0x0051 }
            r3.post(r1)     // Catch:{ all -> 0x0051 }
        L_0x004d:
            monitor-exit(r0)     // Catch:{ all -> 0x0051 }
            return
        L_0x004f:
            monitor-exit(r0)     // Catch:{ all -> 0x0051 }
            return
        L_0x0051:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0051 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.ChooseDeviceManually.a(java.util.List):void");
    }

    public void b() {
        if (this.r != null) {
            this.r.setVisibility(0);
        } else {
            this.s = true;
        }
    }

    public void b(List<ModelGroupInfo> list) {
        this.t = list;
    }

    public void a(DeviceScanManager deviceScanManager) {
        this.C = deviceScanManager;
        if (this.z != null) {
            this.z.a(deviceScanManager);
        }
    }

    private class Category {

        /* renamed from: a  reason: collision with root package name */
        int f14796a;
        int b;
        int c;
        String d;

        private Category() {
        }
    }
}
