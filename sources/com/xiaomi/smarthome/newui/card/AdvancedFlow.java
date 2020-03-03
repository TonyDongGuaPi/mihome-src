package com.xiaomi.smarthome.newui.card;

import com.libra.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;

public class AdvancedFlow {

    /* renamed from: a  reason: collision with root package name */
    private static final String f20454a = "AdvancedFlow";
    private List<FlowItem> b = new ArrayList();
    private int c;
    private FlowCompleteAction d;

    public AdvancedFlow(int i, FlowCompleteAction flowCompleteAction) {
        this.c = i;
        this.d = flowCompleteAction;
    }

    public void a(FlowItem flowItem) {
        this.b.add(flowItem);
    }

    public FlowCompleteAction a() {
        return this.d;
    }

    public void a(FlowCompleteAction flowCompleteAction) {
        this.d = flowCompleteAction;
    }

    public int b() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }

    public List<FlowItem> c() {
        return this.b;
    }

    public void a(List<FlowItem> list) {
        this.b = list;
    }

    public static AdvancedFlow a(String str) {
        return d();
    }

    public static AdvancedFlow d() {
        AdvancedFlow advancedFlow = new AdvancedFlow(0, FlowCompleteAction.STAY);
        FlowItem flowItem = new FlowItem(3000, FlowItemMode.FLOW_MODE_COLOR, -65536, 100);
        FlowItem flowItem2 = new FlowItem(3000, FlowItemMode.FLOW_MODE_COLOR, Color.g, 100);
        FlowItem flowItem3 = new FlowItem(3000, FlowItemMode.FLOW_MODE_COLOR, Color.h, 100);
        FlowItem flowItem4 = new FlowItem(3000, FlowItemMode.FLOW_MODE_COLOR, -256, 100);
        advancedFlow.a(flowItem);
        advancedFlow.a(flowItem2);
        advancedFlow.a(flowItem3);
        advancedFlow.a(flowItem4);
        return advancedFlow;
    }

    public static AdvancedFlow a(JSONArray jSONArray) {
        try {
            int i = jSONArray.getInt(1);
            FlowCompleteAction flowCompleteAction = FlowCompleteAction.values()[jSONArray.getInt(2)];
            String string = jSONArray.getString(3);
            AdvancedFlow advancedFlow = new AdvancedFlow(i, flowCompleteAction);
            Matcher matcher = Pattern.compile("\\d+,\\d+,\\d+,\\d+,*").matcher(string);
            while (matcher.find()) {
                advancedFlow.a(FlowItem.a(matcher.group()));
            }
            return advancedFlow;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static AdvancedFlow b(String str) {
        try {
            String[] split = str.split(",", 3);
            int intValue = Integer.valueOf(split[0]).intValue();
            FlowCompleteAction flowCompleteAction = FlowCompleteAction.values()[Integer.valueOf(split[1]).intValue()];
            String str2 = split[2];
            AdvancedFlow advancedFlow = new AdvancedFlow(intValue, flowCompleteAction);
            Matcher matcher = Pattern.compile("\\d+,\\d+,\\d+,\\d+,*").matcher(str2);
            while (matcher.find()) {
                advancedFlow.a(FlowItem.a(matcher.group()));
            }
            return advancedFlow;
        } catch (Exception e) {
            e.printStackTrace();
            return d();
        }
    }

    public JSONArray e() {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(b());
        jSONArray.put(a().ordinal());
        jSONArray.put(f());
        return jSONArray;
    }

    public String f() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < c().size(); i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(c().get(i).e());
        }
        return sb.toString();
    }

    public int[] g() {
        ArrayList arrayList = new ArrayList();
        for (FlowItem next : this.b) {
            if (next.b() == FlowItemMode.FLOW_MODE_COLOR) {
                arrayList.add(Integer.valueOf(next.c()));
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        int[] iArr = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            iArr[i] = ((Integer) arrayList.get(i)).intValue();
        }
        return iArr;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AdvancedFlow)) {
            return false;
        }
        AdvancedFlow advancedFlow = (AdvancedFlow) obj;
        if (advancedFlow.c != this.c || advancedFlow.d != this.d || advancedFlow.c().size() != c().size()) {
            return false;
        }
        for (int i = 0; i < this.b.size(); i++) {
            if (!this.b.get(i).equals(advancedFlow.c().get(i))) {
                return false;
            }
        }
        return true;
    }
}
