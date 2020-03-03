package com.taobao.weex.appfram.pickers;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.ListView;
import android.widget.TextView;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.appfram.pickers.DatePickerImpl;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.common.WXThread;
import com.taobao.weex.utils.WXResourceUtils;
import com.taobao.weex.utils.WXViewUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXPickersModule extends WXModule {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final String CANCEL = "cancel";
    private static final String DATA = "data";
    private static final String ERROR = "error";
    private static final String KEY_CANCEL_TITLE = "cancelTitle";
    private static final String KEY_CANCEL_TITLE_COLOR = "cancelTitleColor";
    private static final String KEY_CONFIRM_TITLE = "confirmTitle";
    private static final String KEY_CONFIRM_TITLE_COLOR = "confirmTitleColor";
    private static final String KEY_INDEX = "index";
    private static final String KEY_ITEMS = "items";
    private static final String KEY_MAX = "max";
    private static final String KEY_MIN = "min";
    private static final String KEY_SELECTION_COLOR = "selectionColor";
    private static final String KEY_TEXT_COLOR = "textColor";
    private static final String KEY_TITLE = "title";
    private static final String KEY_TITLE_BACKGROUND_COLOR = "titleBackgroundColor";
    private static final String KEY_TITLE_COLOR = "titleColor";
    private static final String KEY_VALUE = "value";
    private static final String RESULT = "result";
    private static final String SUCCESS = "success";
    private int selected;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(4866417636921619829L, "com/taobao/weex/appfram/pickers/WXPickersModule", 58);
        $jacocoData = a2;
        return a2;
    }

    public WXPickersModule() {
        $jacocoInit()[0] = true;
    }

    static /* synthetic */ int access$000(WXPickersModule wXPickersModule) {
        boolean[] $jacocoInit = $jacocoInit();
        int i = wXPickersModule.selected;
        $jacocoInit[54] = true;
        return i;
    }

    static /* synthetic */ int access$002(WXPickersModule wXPickersModule, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        wXPickersModule.selected = i;
        $jacocoInit[55] = true;
        return i;
    }

    static /* synthetic */ Object access$100(WXPickersModule wXPickersModule, Map map, String str, Object obj) {
        boolean[] $jacocoInit = $jacocoInit();
        Object option = wXPickersModule.getOption(map, str, obj);
        $jacocoInit[56] = true;
        return option;
    }

    static /* synthetic */ int access$200(WXPickersModule wXPickersModule, Map map, String str, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        int color = wXPickersModule.getColor(map, str, i);
        $jacocoInit[57] = true;
        return color;
    }

    @JSMethod
    public void pick(Map<String, Object> map, JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        List<String> safeConvert = safeConvert((List) getOption(map, "items", new ArrayList()));
        try {
            $jacocoInit[1] = true;
            performSinglePick(safeConvert, map, jSCallback);
            $jacocoInit[2] = true;
        } catch (Throwable th) {
            $jacocoInit[3] = true;
            th.printStackTrace();
            $jacocoInit[4] = true;
        }
        $jacocoInit[5] = true;
    }

    @JSMethod
    public void pickDate(Map<String, Object> map, JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        performPickDate(map, jSCallback);
        $jacocoInit[6] = true;
    }

    @JSMethod
    public void pickTime(Map<String, Object> map, JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        performPickTime(map, jSCallback);
        $jacocoInit[7] = true;
    }

    private List<String> safeConvert(List list) {
        boolean[] $jacocoInit = $jacocoInit();
        ArrayList arrayList = new ArrayList(list.size());
        $jacocoInit[8] = true;
        $jacocoInit[9] = true;
        for (Object next : list) {
            $jacocoInit[10] = true;
            arrayList.add(String.valueOf(next));
            $jacocoInit[11] = true;
        }
        $jacocoInit[12] = true;
        return arrayList;
    }

    private <T> T getOption(Map<String, Object> map, String str, T t) {
        boolean[] $jacocoInit = $jacocoInit();
        T t2 = map.get(str);
        if (t2 != null) {
            $jacocoInit[13] = true;
            $jacocoInit[15] = true;
            return t2;
        }
        $jacocoInit[14] = true;
        return t;
    }

    private int getColor(Map<String, Object> map, String str, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        Object option = getOption(map, str, (Object) null);
        if (option == null) {
            $jacocoInit[18] = true;
            return i;
        }
        int color = WXResourceUtils.getColor(option.toString(), i);
        $jacocoInit[19] = true;
        return color;
    }

    private void performPickTime(Map<String, Object> map, final JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKInstance wXSDKInstance = this.mWXSDKInstance;
        $jacocoInit[20] = true;
        Context context = wXSDKInstance.getContext();
        AnonymousClass1 r4 = new DatePickerImpl.OnPickListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXPickersModule this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(1052137768722388397L, "com/taobao/weex/appfram/pickers/WXPickersModule$1", 11);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void onPick(boolean z, @Nullable String str) {
                boolean[] $jacocoInit = $jacocoInit();
                if (z) {
                    $jacocoInit[1] = true;
                    HashMap hashMap = new HashMap(2);
                    $jacocoInit[2] = true;
                    hashMap.put("result", "success");
                    $jacocoInit[3] = true;
                    hashMap.put("data", str);
                    $jacocoInit[4] = true;
                    jSCallback.invoke(hashMap);
                    $jacocoInit[5] = true;
                } else {
                    HashMap hashMap2 = new HashMap(2);
                    $jacocoInit[6] = true;
                    hashMap2.put("result", "cancel");
                    $jacocoInit[7] = true;
                    hashMap2.put("data", (Object) null);
                    $jacocoInit[8] = true;
                    jSCallback.invoke(hashMap2);
                    $jacocoInit[9] = true;
                }
                $jacocoInit[10] = true;
            }
        };
        $jacocoInit[21] = true;
        DatePickerImpl.pickTime(context, (String) getOption(map, "value", ""), r4, map);
        $jacocoInit[22] = true;
    }

    private void performPickDate(Map<String, Object> map, final JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[23] = true;
        $jacocoInit[24] = true;
        WXSDKInstance wXSDKInstance = this.mWXSDKInstance;
        $jacocoInit[25] = true;
        Context context = wXSDKInstance.getContext();
        AnonymousClass2 r6 = new DatePickerImpl.OnPickListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXPickersModule this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(6757107757201703414L, "com/taobao/weex/appfram/pickers/WXPickersModule$2", 11);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void onPick(boolean z, @Nullable String str) {
                boolean[] $jacocoInit = $jacocoInit();
                if (z) {
                    $jacocoInit[1] = true;
                    HashMap hashMap = new HashMap(2);
                    $jacocoInit[2] = true;
                    hashMap.put("result", "success");
                    $jacocoInit[3] = true;
                    hashMap.put("data", str);
                    $jacocoInit[4] = true;
                    jSCallback.invoke(hashMap);
                    $jacocoInit[5] = true;
                } else {
                    HashMap hashMap2 = new HashMap(2);
                    $jacocoInit[6] = true;
                    hashMap2.put("result", "cancel");
                    $jacocoInit[7] = true;
                    hashMap2.put("data", (Object) null);
                    $jacocoInit[8] = true;
                    jSCallback.invoke(hashMap2);
                    $jacocoInit[9] = true;
                }
                $jacocoInit[10] = true;
            }
        };
        $jacocoInit[26] = true;
        DatePickerImpl.pickDate(context, (String) getOption(map, "value", ""), (String) getOption(map, "max", ""), (String) getOption(map, "min", ""), r6, map);
        $jacocoInit[27] = true;
    }

    private void performSinglePick(List<String> list, final Map<String, Object> map, final JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        this.selected = ((Integer) getOption(map, "index", 0)).intValue();
        $jacocoInit[28] = true;
        final int color = getColor(map, KEY_TEXT_COLOR, 0);
        $jacocoInit[29] = true;
        final int color2 = getColor(map, KEY_SELECTION_COLOR, 0);
        WXSDKInstance wXSDKInstance = this.mWXSDKInstance;
        $jacocoInit[30] = true;
        final AnonymousClass3 r4 = new ArrayAdapter<String>(this, wXSDKInstance.getContext(), 17367055, list) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXPickersModule this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-1899636451998587692L, "com/taobao/weex/appfram/pickers/WXPickersModule$3", 15);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            @NonNull
            public View getView(int i, View view, @Nullable ViewGroup viewGroup) {
                boolean z;
                boolean[] $jacocoInit = $jacocoInit();
                View view2 = super.getView(i, view, viewGroup);
                if (view2 == null) {
                    $jacocoInit[1] = true;
                } else if (!(view2 instanceof Checkable)) {
                    $jacocoInit[2] = true;
                } else {
                    $jacocoInit[3] = true;
                    if (i == WXPickersModule.access$000(this.this$0)) {
                        $jacocoInit[4] = true;
                        z = true;
                    } else {
                        $jacocoInit[5] = true;
                        z = false;
                    }
                    $jacocoInit[6] = true;
                    ((Checkable) view2).setChecked(z);
                    if (z) {
                        $jacocoInit[7] = true;
                        view2.setBackgroundColor(color2);
                        $jacocoInit[8] = true;
                    } else {
                        view2.setBackgroundColor(0);
                        $jacocoInit[9] = true;
                    }
                }
                if (!(view2 instanceof TextView)) {
                    $jacocoInit[10] = true;
                } else if (color == 0) {
                    $jacocoInit[11] = true;
                } else {
                    $jacocoInit[12] = true;
                    ((TextView) view2).setTextColor(color);
                    $jacocoInit[13] = true;
                }
                $jacocoInit[14] = true;
                return view2;
            }
        };
        $jacocoInit[31] = true;
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mWXSDKInstance.getContext());
        $jacocoInit[32] = true;
        AlertDialog.Builder adapter = builder.setAdapter(r4, (DialogInterface.OnClickListener) null);
        AnonymousClass5 r3 = new DialogInterface.OnClickListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXPickersModule this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(703838320475497595L, "com/taobao/weex/appfram/pickers/WXPickersModule$5", 5);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                boolean[] $jacocoInit = $jacocoInit();
                HashMap hashMap = new HashMap(2);
                $jacocoInit[1] = true;
                hashMap.put("result", "success");
                $jacocoInit[2] = true;
                hashMap.put("data", Integer.valueOf(WXPickersModule.access$000(this.this$0)));
                $jacocoInit[3] = true;
                jSCallback.invoke(hashMap);
                $jacocoInit[4] = true;
            }
        };
        $jacocoInit[33] = true;
        AlertDialog.Builder positiveButton = adapter.setPositiveButton(17039370, (DialogInterface.OnClickListener) r3);
        AnonymousClass4 r32 = new DialogInterface.OnClickListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXPickersModule this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-6524096010068106180L, "com/taobao/weex/appfram/pickers/WXPickersModule$4", 5);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                boolean[] $jacocoInit = $jacocoInit();
                HashMap hashMap = new HashMap(2);
                $jacocoInit[1] = true;
                hashMap.put("result", "cancel");
                $jacocoInit[2] = true;
                hashMap.put("data", -1);
                $jacocoInit[3] = true;
                jSCallback.invoke(hashMap);
                $jacocoInit[4] = true;
            }
        };
        $jacocoInit[34] = true;
        AlertDialog.Builder negativeButton = positiveButton.setNegativeButton(17039360, (DialogInterface.OnClickListener) r32);
        WXSDKInstance wXSDKInstance2 = this.mWXSDKInstance;
        $jacocoInit[35] = true;
        AlertDialog.Builder customTitle = negativeButton.setCustomTitle(makeTitleView(wXSDKInstance2.getContext(), map));
        $jacocoInit[36] = true;
        final AlertDialog create = customTitle.create();
        if (Build.VERSION.SDK_INT < 21) {
            $jacocoInit[37] = true;
        } else {
            $jacocoInit[38] = true;
            create.create();
            $jacocoInit[39] = true;
        }
        ListView listView = create.getListView();
        $jacocoInit[40] = true;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXPickersModule this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(8405937870882803383L, "com/taobao/weex/appfram/pickers/WXPickersModule$6", 3);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                boolean[] $jacocoInit = $jacocoInit();
                WXPickersModule.access$002(this.this$0, i);
                $jacocoInit[1] = true;
                r4.notifyDataSetChanged();
                $jacocoInit[2] = true;
            }
        });
        $jacocoInit[41] = true;
        create.getWindow().getDecorView().post(WXThread.secure((Runnable) new Runnable(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXPickersModule this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-254742974685949869L, "com/taobao/weex/appfram/pickers/WXPickersModule$7", 23);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void run() {
                boolean[] $jacocoInit = $jacocoInit();
                Button button = create.getButton(-1);
                $jacocoInit[1] = true;
                Button button2 = create.getButton(-2);
                if (button == null) {
                    $jacocoInit[2] = true;
                } else {
                    $jacocoInit[3] = true;
                    String str = (String) WXPickersModule.access$100(this.this$0, map, WXPickersModule.KEY_CONFIRM_TITLE, (Object) null);
                    $jacocoInit[4] = true;
                    int access$200 = WXPickersModule.access$200(this.this$0, map, WXPickersModule.KEY_CONFIRM_TITLE_COLOR, 0);
                    if (str == null) {
                        $jacocoInit[5] = true;
                    } else {
                        $jacocoInit[6] = true;
                        button.setText(str);
                        $jacocoInit[7] = true;
                        button.setAllCaps(false);
                        $jacocoInit[8] = true;
                    }
                    if (access$200 == 0) {
                        $jacocoInit[9] = true;
                    } else {
                        $jacocoInit[10] = true;
                        button.setTextColor(access$200);
                        $jacocoInit[11] = true;
                        button.setAllCaps(false);
                        $jacocoInit[12] = true;
                    }
                }
                if (button2 == null) {
                    $jacocoInit[13] = true;
                } else {
                    $jacocoInit[14] = true;
                    String str2 = (String) WXPickersModule.access$100(this.this$0, map, "cancelTitle", (Object) null);
                    $jacocoInit[15] = true;
                    int access$2002 = WXPickersModule.access$200(this.this$0, map, WXPickersModule.KEY_CANCEL_TITLE_COLOR, 0);
                    if (str2 == null) {
                        $jacocoInit[16] = true;
                    } else {
                        $jacocoInit[17] = true;
                        button2.setText(str2);
                        $jacocoInit[18] = true;
                    }
                    if (access$2002 == 0) {
                        $jacocoInit[19] = true;
                    } else {
                        $jacocoInit[20] = true;
                        button2.setTextColor(access$2002);
                        $jacocoInit[21] = true;
                    }
                }
                $jacocoInit[22] = true;
            }
        }));
        $jacocoInit[42] = true;
        create.show();
        $jacocoInit[43] = true;
    }

    private TextView makeTitleView(Context context, Map<String, Object> map) {
        boolean[] $jacocoInit = $jacocoInit();
        String str = (String) getOption(map, "title", (Object) null);
        if (str == null) {
            $jacocoInit[44] = true;
            return null;
        }
        TextView textView = new TextView(context);
        $jacocoInit[45] = true;
        textView.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        $jacocoInit[46] = true;
        textView.setTextSize(2, 20.0f);
        $jacocoInit[47] = true;
        int dip2px = WXViewUtils.dip2px(12.0f);
        $jacocoInit[48] = true;
        textView.setPadding(dip2px, dip2px, dip2px, dip2px);
        $jacocoInit[49] = true;
        textView.getPaint().setFakeBoldText(true);
        $jacocoInit[50] = true;
        textView.setBackgroundColor(getColor(map, KEY_TITLE_BACKGROUND_COLOR, 0));
        $jacocoInit[51] = true;
        textView.setTextColor(getColor(map, "titleColor", -16777216));
        $jacocoInit[52] = true;
        textView.setText(str);
        $jacocoInit[53] = true;
        return textView;
    }
}
