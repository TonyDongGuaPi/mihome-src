package com.xiaomi.smarthome.scene.view;

import android.text.TextUtils;
import android.widget.TextView;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.scene.CommonSceneOnline;
import com.xiaomi.smarthome.scene.action.BaseInnerAction;
import com.xiaomi.smarthome.scene.action.InnerActionCommon;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scene.condition.BaseInnerCondition;
import com.xiaomi.smarthome.scene.condition.InnerConditionCommon;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SceneFilterManager {

    /* renamed from: a  reason: collision with root package name */
    public static final int f21703a = 1;
    public static final int b = 2;
    public static final int c = 3;
    private static SceneFilterManager d;
    private ConditionFilter e = new ConditionFilter();
    private SceneListFilter f = new SceneListFilter();
    private Set<String> g = new HashSet();
    private Set<String> h = new HashSet();
    private DeviceTagInterface i = SmartHomeDeviceHelper.a().b();
    private List<BaseInnerCondition> j = new ArrayList();
    private List<BaseInnerAction> k = new ArrayList();
    private List<SceneApi.SmartHomeScene> l;
    private SceneCreateSortInfo m = new SceneCreateSortInfo();

    public Set<String> a() {
        return this.g;
    }

    public void a(Set<String> set) {
        this.g.clear();
        this.g.addAll(set);
    }

    public Set<String> b() {
        return this.h;
    }

    public void b(Set<String> set) {
        this.h.clear();
        this.h.addAll(set);
    }

    public static SceneFilterManager c() {
        if (d == null) {
            d = new SceneFilterManager();
        }
        return d;
    }

    public ConditionFilter d() {
        return this.e;
    }

    public SceneListFilter e() {
        return this.f;
    }

    public List<SceneApi.SmartHomeScene> f() {
        return this.l;
    }

    public void a(List<SceneApi.SmartHomeScene> list) {
        this.l = list;
    }

    public void b(List<BaseInnerCondition> list) {
        this.j = list;
    }

    public void c(List<BaseInnerAction> list) {
        this.k = list;
    }

    public void a(int i2, int i3, String str, int i4) {
        this.e.b = i3;
        this.e.c = str;
        this.e.f21704a = i2;
        this.e.d = i4;
    }

    public void b(int i2, int i3, String str, int i4) {
        this.f.b = i3;
        this.f.c = str;
        this.f.f21707a = i2;
        this.f.d = i4;
    }

    public void g() {
        this.e = new ConditionFilter();
        this.m = new SceneCreateSortInfo();
    }

    public void h() {
        this.f = new SceneListFilter();
    }

    public class ConditionFilter {

        /* renamed from: a  reason: collision with root package name */
        public int f21704a = 2;
        public int b = 0;
        public String c = SHApplication.getAppContext().getResources().getString(R.string.smarthome_scene_all);
        public int d;

        public ConditionFilter() {
        }

        public int a() {
            return this.f21704a;
        }

        public void a(int i) {
            this.f21704a = i;
        }

        public int b() {
            return this.b;
        }

        public void b(int i) {
            this.b = i;
        }

        public String c() {
            return this.c;
        }

        public void a(String str) {
            this.c = str;
        }
    }

    public class SceneListFilter {

        /* renamed from: a  reason: collision with root package name */
        public int f21707a = 2;
        public int b = 0;
        public String c = SHApplication.getAppContext().getResources().getString(R.string.smarthome_scene_all);
        public int d;

        public SceneListFilter() {
        }

        public int a() {
            return this.f21707a;
        }

        public void a(int i) {
            this.f21707a = i;
        }

        public int b() {
            return this.b;
        }

        public void b(int i) {
            this.b = i;
        }

        public String c() {
            return this.c;
        }

        public void a(String str) {
            this.c = str;
        }

        public int d() {
            return this.d;
        }

        public void c(int i) {
            this.d = i;
        }
    }

    public class SceneCreateSortInfo {

        /* renamed from: a  reason: collision with root package name */
        public String f21706a;
        public String b;
        public String c;
        public String d;

        public SceneCreateSortInfo() {
        }
    }

    public boolean c(Set<String> set) {
        Device b2;
        Iterator<String> it = set.iterator();
        while (it.hasNext() && (b2 = SmartHomeDeviceManager.a().b(it.next().toString())) != null && !TextUtils.isEmpty(b2.did) && !TextUtils.isEmpty(b2.model)) {
            if (SceneManager.x().a(b2.model, b2.did) != null && SceneManager.x().a(b2.model, b2.did).e.size() > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean d(Set<String> set) {
        Device b2;
        Iterator<String> it = set.iterator();
        while (it.hasNext() && (b2 = SmartHomeDeviceManager.a().b(it.next().toString())) != null && !TextUtils.isEmpty(b2.did) && !TextUtils.isEmpty(b2.model)) {
            if (SceneManager.x().a(b2.model, b2.did) != null && SceneManager.x().a(b2.model, b2.did).f != null && SceneManager.x().a(b2.model, b2.did).f.size() > 0) {
                return true;
            }
        }
        return false;
    }

    public List<FilterInfo> a(Map<String, Set<String>> map, int i2) {
        if (map == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (String obj : map.keySet()) {
            FilterInfo filterInfo = new FilterInfo();
            String obj2 = obj.toString();
            if (i2 == 1) {
                filterInfo.f21705a = obj2;
                filterInfo.b = a(obj2, map);
                arrayList.add(filterInfo);
            } else if (i2 == 2) {
                filterInfo.f21705a = obj2;
                filterInfo.b = b(obj2, map);
                arrayList.add(filterInfo);
            } else if (i2 == 3) {
                filterInfo.f21705a = obj2;
                filterInfo.b = c(obj2, map);
                arrayList.add(filterInfo);
            }
        }
        FilterInfo filterInfo2 = new FilterInfo();
        filterInfo2.f21705a = SHApplication.getAppContext().getResources().getString(R.string.smarthome_scene_all);
        if (i2 == 1) {
            filterInfo2.b = a(filterInfo2.f21705a, map);
        } else if (i2 == 2) {
            filterInfo2.b = b(filterInfo2.f21705a, map);
        } else if (i2 == 3) {
            filterInfo2.b = c(filterInfo2.f21705a, map);
        }
        arrayList.add(0, filterInfo2);
        return arrayList;
    }

    public int a(String str, Map<String, Set<String>> map) {
        if (str.equalsIgnoreCase(SHApplication.getAppContext().getResources().getString(R.string.smarthome_scene_all))) {
            return this.j.size();
        }
        Set set = map.get(str);
        int i2 = 0;
        for (BaseInnerCondition next : this.j) {
            if (next instanceof InnerConditionCommon) {
                CommonSceneOnline a2 = ((InnerConditionCommon) next).a();
                if (a2 != null) {
                    Iterator it = set.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        if (a2.b.equalsIgnoreCase((String) it.next())) {
                            i2++;
                            break;
                        }
                    }
                }
            } else {
                i2++;
            }
        }
        return i2;
    }

    public int b(String str, Map<String, Set<String>> map) {
        if (str.equalsIgnoreCase(SHApplication.getAppContext().getResources().getString(R.string.smarthome_scene_all))) {
            return this.k.size();
        }
        Set set = map.get(str);
        int i2 = 0;
        for (BaseInnerAction next : this.k) {
            if (next instanceof InnerActionCommon) {
                CommonSceneOnline h2 = ((InnerActionCommon) next).h();
                if (h2 != null) {
                    Iterator it = set.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        if (h2.b.equalsIgnoreCase((String) it.next())) {
                            i2++;
                            break;
                        }
                    }
                }
            } else {
                i2++;
            }
        }
        return i2;
    }

    public int c(String str, Map<String, Set<String>> map) {
        int i2 = 0;
        if (this.l == null || this.l.size() == 0) {
            return 0;
        }
        if (str.equalsIgnoreCase(SHApplication.getAppContext().getResources().getString(R.string.smarthome_scene_all))) {
            return this.l.size();
        }
        if (str.equalsIgnoreCase(SHApplication.getAppContext().getResources().getString(R.string.smarthome_scene_all))) {
            return this.l.size();
        }
        Set set = map.get(str);
        for (SceneApi.SmartHomeScene a2 : this.l) {
            if (a(a2, (Set<String>) set)) {
                i2++;
            }
        }
        return i2;
    }

    public boolean a(SceneApi.SmartHomeScene smartHomeScene, Set<String> set) {
        if (smartHomeScene == null) {
            return false;
        }
        for (SceneApi.Condition a2 : smartHomeScene.l) {
            if (a(a2, set)) {
                return true;
            }
        }
        for (SceneApi.Action a3 : smartHomeScene.k) {
            if (a(a3, set)) {
                return true;
            }
        }
        return false;
    }

    public boolean a(SceneApi.Condition condition, Set<String> set) {
        if (condition.f21522a != SceneApi.Condition.LAUNCH_TYPE.DEVICE || condition.c == null) {
            return false;
        }
        for (String next : set) {
            if (!TextUtils.isEmpty(condition.c.f21523a) && condition.c.f21523a.equalsIgnoreCase(next)) {
                return true;
            }
        }
        return false;
    }

    public boolean a(SceneApi.Action action, Set<String> set) {
        if (action.f21521a == 1 || action.f21521a == 2 || action.g == null || TextUtils.isEmpty(action.g.e)) {
            return false;
        }
        for (String equalsIgnoreCase : set) {
            if (action.g.e.equalsIgnoreCase(equalsIgnoreCase)) {
                return true;
            }
        }
        return false;
    }

    public boolean a(int i2, int i3, String str) {
        List<FilterInfo> a2 = c().a(this.i.a(i2), i3);
        if (a2 == null || a2.size() == 0) {
            return false;
        }
        for (FilterInfo filterInfo : a2) {
            if (filterInfo.f21705a.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    public void a(TextView textView, int i2, boolean z) {
        if (c().e.b == 0 && (i2 == 1 || i2 == 2)) {
            if (i2 == 1) {
                textView.setText(R.string.smarthome_scene_start_condition);
                return;
            } else if (i2 == 2) {
                textView.setText(R.string.smarthome_scene_choose_action_title);
                return;
            }
        }
        if (c().f.b == 0 && i2 == 3 && i2 == 3) {
            textView.setText(R.string.smarthome_scene_all_activity_title);
        } else if (!z || (!(i2 == 1 || i2 == 2) || c().a(c().d().a(), i2, c().d().c()))) {
            if (z && (i2 == 1 || i2 == 2)) {
                int i3 = 0;
                if (i2 == 1) {
                    i3 = c().a(this.e.c, SmartHomeDeviceHelper.a().b().a(this.e.f21704a));
                } else if (i2 == 2) {
                    i3 = c().b(this.e.c, SmartHomeDeviceHelper.a().b().a(this.e.f21704a));
                }
                if (c().d().a() == 2) {
                    textView.setText(a(SmartHomeDeviceHelper.a().b().k(this.e.c), i3));
                } else {
                    textView.setText(a(this.e.c, i3));
                }
            } else if (i2 == 1 || i2 == 2) {
                if (c().d().a() == 2) {
                    textView.setText(a(SmartHomeDeviceHelper.a().b().k(this.e.c), this.e.d));
                } else {
                    textView.setText(a(this.e.c, this.e.d));
                }
            } else if (i2 == 3) {
                int c2 = c(this.f.c, SmartHomeDeviceHelper.a().b().a(this.f.f21707a));
                if (c().e().a() == 2) {
                    textView.setText(a(SmartHomeDeviceHelper.a().b().k(this.f.c), c2));
                } else {
                    textView.setText(a(this.f.c, c2));
                }
            }
        } else if (i2 == 1) {
            textView.setText(R.string.smarthome_scene_start_condition);
        } else if (i2 == 2) {
            textView.setText(R.string.smarthome_scene_choose_action_title);
        }
    }

    public String a(String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (str.length() <= 12) {
            sb.append(str);
        } else {
            sb.append(str.substring(0, 9));
            sb.append("...");
        }
        sb.append(Operators.BRACKET_START_STR);
        sb.append(i2);
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }

    class FilterInfo {

        /* renamed from: a  reason: collision with root package name */
        public String f21705a;
        public int b;

        FilterInfo() {
        }
    }

    public SceneCreateSortInfo i() {
        return this.m;
    }

    public void a(String str, String str2, String str3, String str4) {
        this.m.f21706a = str;
        this.m.b = str2;
        this.m.c = str3;
        this.m.d = str4;
    }
}
