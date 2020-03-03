package com.xiaomi.smarthome.scene;

import android.app.Activity;
import android.content.DialogInterface;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.lite.scene.SceneLogUtil;
import com.xiaomi.smarthome.scene.CommonSceneOnline;
import com.xiaomi.smarthome.scene.action.BaseInnerAction;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scene.condition.BaseInnerCondition;
import com.xiaomi.smarthome.scene.condition.WeatherInnerCondition;
import com.xiaomi.smarthome.scenenew.view.SelectRoomDialogView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CreateSceneManager {

    /* renamed from: a  reason: collision with root package name */
    public static int f21204a = 30;
    public static final int c = 1;
    public static final int d = 2;
    public static final int e = 3;
    public static final int f = 4;
    public static final int g = 0;
    private static CreateSceneManager h;
    public int b;
    private SceneApi.SmartHomeScene i;
    private BaseInnerCondition j;
    private BaseInnerAction k;
    private SceneApi.Condition l;
    private SceneApi.Action m;
    private List<Integer> n;
    private boolean o = true;
    private List<Integer> p;
    private boolean q = true;
    private Room r;
    private WeatherInnerCondition s;
    private Map<String, Set<Integer>> t = new HashMap();
    private Map<String, Set<Integer>> u = new HashMap();

    public void f() {
    }

    public static CreateSceneManager a() {
        if (h == null) {
            h = new CreateSceneManager();
        }
        return h;
    }

    public void a(SceneApi.SmartHomeScene smartHomeScene) {
        this.i = null;
        this.i = smartHomeScene;
        this.t.clear();
        this.u.clear();
        if (smartHomeScene != null) {
            b(smartHomeScene);
            c(smartHomeScene);
        }
    }

    public boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return this.t.containsKey(str);
    }

    public boolean a(String str, int i2) {
        if (i2 > 0 && a(str) && !this.t.get(str).isEmpty()) {
            return this.t.get(str).contains(Integer.valueOf(i2));
        }
        return false;
    }

    public boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return this.u.containsKey(str);
    }

    public boolean b(String str, int i2) {
        if (i2 > 0 && b(str) && !this.u.get(str).isEmpty()) {
            return this.u.get(str).contains(Integer.valueOf(i2));
        }
        return false;
    }

    public void b(SceneApi.SmartHomeScene smartHomeScene) {
        if (smartHomeScene == null) {
            this.t.clear();
        } else if (smartHomeScene.l.isEmpty()) {
            this.t.clear();
        } else {
            for (int i2 = 0; i2 < smartHomeScene.l.size(); i2++) {
                SceneApi.Condition condition = smartHomeScene.l.get(i2);
                if (condition.c != null && !TextUtils.isEmpty(condition.c.f21523a) && condition.c.k > 0) {
                    if (!this.t.containsKey(condition.c.f21523a)) {
                        this.t.put(condition.c.f21523a, new HashSet());
                    }
                    Set set = this.t.get(condition.c.f21523a);
                    if (set == null) {
                        set = new HashSet();
                    }
                    set.add(Integer.valueOf(condition.c.k));
                }
            }
        }
    }

    public void a(SceneApi.Condition condition) {
        if (condition != null && condition.c != null && !TextUtils.isEmpty(condition.c.f21523a) && condition.c.k > 0) {
            if (!this.t.containsKey(condition.c.f21523a)) {
                this.t.put(condition.c.f21523a, new HashSet());
            }
            Set set = this.u.get(condition.c.f21523a);
            if (set == null) {
                set = new HashSet();
            }
            set.add(Integer.valueOf(condition.c.k));
        }
    }

    public void b(SceneApi.Condition condition) {
        if (condition != null && condition.c != null && !TextUtils.isEmpty(condition.c.f21523a) && condition.c.k > 0 && this.t.containsKey(condition.c.f21523a)) {
            Set set = this.t.get(condition.c.f21523a);
            if (!set.isEmpty()) {
                set.remove(Integer.valueOf(condition.c.k));
            }
            if (set.isEmpty()) {
                this.t.remove(condition.c.f21523a);
            }
        }
    }

    public void c(SceneApi.SmartHomeScene smartHomeScene) {
        if (smartHomeScene == null) {
            this.u.clear();
        } else if (smartHomeScene.k.isEmpty()) {
            this.u.clear();
        } else {
            for (int i2 = 0; i2 < smartHomeScene.k.size(); i2++) {
                SceneApi.Action action = smartHomeScene.k.get(i2);
                if (action.g != null && !TextUtils.isEmpty(action.g.e) && action.f > 0) {
                    if (!this.u.containsKey(action.g.e)) {
                        this.u.put(action.g.e, new HashSet());
                    }
                    Set set = this.u.get(action.g.e);
                    if (set == null) {
                        set = new HashSet();
                    }
                    set.add(Integer.valueOf(action.f));
                }
            }
        }
    }

    public void a(SceneApi.Action action) {
        if (action != null && action.g != null && !TextUtils.isEmpty(action.g.e) && action.f > 0) {
            if (!this.u.containsKey(action.g.e)) {
                this.u.put(action.g.e, new HashSet());
            }
            this.u.get(action.g.e).add(Integer.valueOf(action.f));
        }
    }

    public void b(SceneApi.Action action) {
        if (action != null && action.g != null && !TextUtils.isEmpty(action.g.e) && action.f > 0 && this.u.containsKey(action.g.e)) {
            Set set = this.u.get(action.g.e);
            if (!set.isEmpty()) {
                set.remove(Integer.valueOf(action.f));
            }
            if (set.isEmpty()) {
                this.u.remove(action.g.e);
            }
        }
    }

    public void d(SceneApi.SmartHomeScene smartHomeScene) {
        CommonSceneOnline a2;
        CommonSceneOnline a3;
        SceneLogUtil.a("--------------resetRules------------------");
        this.n = null;
        this.p = null;
        this.i = smartHomeScene;
        this.o = true;
        for (int i2 = 0; i2 < this.i.l.size(); i2++) {
            SceneApi.Condition condition = this.i.l.get(i2);
            if (condition.k != 0) {
                if (a(Integer.valueOf(condition.k))) {
                    a(SceneManager.x().a(condition.k));
                    b(SceneManager.x().b(condition.k));
                } else {
                    this.n.clear();
                    this.o = false;
                }
            } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.CLICK || condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.TIMER || condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.MIKEY || condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.MIBAND) {
                a(SceneManager.x().a(101));
                b(SceneManager.x().b(101));
            } else if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.DEVICE && (a3 = SceneManager.x().a(condition.c.d, condition.c.f21523a)) != null) {
                Iterator<CommonSceneOnline.CommonSceneCondition> it = a3.e.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    CommonSceneOnline.CommonSceneCondition next = it.next();
                    if (next.f.equalsIgnoreCase(condition.c.j)) {
                        if (a(Integer.valueOf(next.d))) {
                            a(SceneManager.x().a(next.d));
                            b(SceneManager.x().b(next.d));
                        } else {
                            this.n.clear();
                            this.o = false;
                        }
                    }
                }
            }
        }
        this.q = true;
        for (int i3 = 0; i3 < this.i.k.size(); i3++) {
            SceneApi.Action action = this.i.k.get(i3);
            if (!(action.g instanceof SceneApi.SHScenePushPayload) && !(action.g instanceof SceneApi.SHSceneDelayPayload)) {
                if (action.d == 0) {
                    SceneApi.SHSceneItemPayload sHSceneItemPayload = action.g;
                    if (sHSceneItemPayload != null && (a2 = SceneManager.x().a(action.e, sHSceneItemPayload.e)) != null) {
                        Iterator<CommonSceneOnline.CommonSceneCondition> it2 = a2.e.iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                break;
                            }
                            CommonSceneOnline.CommonSceneCondition next2 = it2.next();
                            if (next2.f.equalsIgnoreCase(sHSceneItemPayload.c)) {
                                if (b(Integer.valueOf(next2.d))) {
                                    b(SceneManager.x().b(next2.d));
                                    a(SceneManager.x().a(next2.d));
                                } else {
                                    this.p.clear();
                                    this.q = false;
                                }
                            }
                        }
                    }
                } else if (b(Integer.valueOf(action.d))) {
                    b(SceneManager.x().b(action.d));
                    a(SceneManager.x().a(action.d));
                } else {
                    this.p.clear();
                    this.q = false;
                }
            }
        }
    }

    public boolean b() {
        return this.o;
    }

    public void c() {
        this.n = new ArrayList();
    }

    public boolean d() {
        return this.q;
    }

    public void e() {
        this.p = new ArrayList();
    }

    public void a(List<Integer> list) {
        if (this.n != null || list != null) {
            if (this.n == null) {
                this.n = new ArrayList();
                this.n.addAll(list);
            } else if (list != null) {
                int i2 = 0;
                int i3 = 0;
                while (i2 < this.n.size() && i3 < list.size()) {
                    if (this.n.get(i2).intValue() > list.get(i3).intValue()) {
                        i3++;
                    } else if (this.n.get(i2).intValue() < list.get(i3).intValue()) {
                        this.n.remove(i2);
                    } else {
                        i2++;
                        i3++;
                    }
                }
                if (i2 < this.n.size()) {
                    while (i2 < this.n.size()) {
                        this.n.remove(i2);
                    }
                }
            }
        }
    }

    public boolean a(Integer num) {
        if (this.n == null) {
            return true;
        }
        return this.n.contains(num);
    }

    public void b(List<Integer> list) {
        if (this.p != null || list != null) {
            if (this.p == null) {
                this.p = new ArrayList();
                this.p.addAll(list);
            } else if (list != null) {
                int i2 = 0;
                int i3 = 0;
                while (i2 < this.p.size() && i3 < list.size()) {
                    if (this.p.get(i2).intValue() > list.get(i3).intValue()) {
                        i3++;
                    } else if (this.p.get(i2).intValue() < list.get(i3).intValue()) {
                        this.p.remove(i2);
                    } else {
                        i2++;
                        i3++;
                    }
                }
                if (i2 < this.p.size()) {
                    while (i2 < this.p.size()) {
                        this.p.remove(i2);
                    }
                }
            }
        }
    }

    public boolean b(Integer num) {
        if (this.p == null) {
            return true;
        }
        return this.p.contains(num);
    }

    public SceneApi.SmartHomeScene g() {
        return this.i;
    }

    public void a(BaseInnerCondition baseInnerCondition) {
        l();
        this.j = baseInnerCondition;
    }

    public BaseInnerCondition h() {
        return this.j;
    }

    public SceneApi.Condition i() {
        return this.l;
    }

    public void c(SceneApi.Condition condition) {
        l();
        this.l = condition;
        if (condition != null) {
            a(condition);
        }
    }

    public BaseInnerAction j() {
        return this.k;
    }

    public void a(BaseInnerAction baseInnerAction) {
        l();
        this.k = baseInnerAction;
    }

    public SceneApi.Action k() {
        return this.m;
    }

    public void c(SceneApi.Action action) {
        l();
        this.m = action;
        if (this.m != null) {
            a(this.m);
        }
    }

    public void l() {
        this.j = null;
        this.k = null;
        this.m = null;
        this.l = null;
    }

    public View c(String str) {
        View inflate = LayoutInflater.from(SHApplication.getAppContext()).inflate(R.layout.scene_all_activity_item_actionicon_rl, (ViewGroup) null);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) inflate.findViewById(R.id.icon_sdv);
        simpleDraweeView.setHierarchy(new GenericDraweeHierarchyBuilder(simpleDraweeView.getResources()).setFadeDuration(200).setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER).setPlaceholderImage(simpleDraweeView.getResources().getDrawable(R.drawable.device_list_phone_no)).build());
        simpleDraweeView.setImageURI(Uri.parse(str));
        ImageView imageView = (ImageView) inflate.findViewById(R.id.icon_more);
        return inflate;
    }

    public View c(Integer num) {
        View inflate = LayoutInflater.from(SHApplication.getAppContext()).inflate(R.layout.scene_all_activity_item_actionicon_rl, (ViewGroup) null);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) inflate.findViewById(R.id.icon_sdv);
        simpleDraweeView.setHierarchy(new GenericDraweeHierarchyBuilder(simpleDraweeView.getResources()).setFadeDuration(200).setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER).setPlaceholderImage(simpleDraweeView.getResources().getDrawable(R.drawable.device_list_phone_no)).build());
        simpleDraweeView.setImageURI(CommonUtils.c(num.intValue()));
        ImageView imageView = (ImageView) inflate.findViewById(R.id.icon_more);
        return inflate;
    }

    public MLAlertDialog a(Activity activity, SelectRoomDialogView.IOnRoomSelectCallBack iOnRoomSelectCallBack, Room room) {
        ArrayList arrayList = new ArrayList();
        Room room2 = new Room();
        room2.e(activity.getString(R.string.smarthome_scene_all_room));
        room2.d(SelectRoomDialogView.ALL_ROOM_ID);
        arrayList.add(room2);
        List<Room> e2 = HomeManager.a().e();
        if (e2 != null) {
            arrayList.addAll(e2);
        }
        Room room3 = new Room();
        room3.e(activity.getString(R.string.tag_recommend_defaultroom));
        room3.d(SelectRoomDialogView.DEFAULT_ROOM_ID);
        arrayList.add(room3);
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(activity);
        SelectRoomDialogView selectRoomDialogView = (SelectRoomDialogView) LayoutInflater.from(activity).inflate(R.layout.select_room_dialog_view, (ViewGroup) null);
        builder.b((View) selectRoomDialogView);
        selectRoomDialogView.setCallBack(iOnRoomSelectCallBack);
        if (room == null) {
            selectRoomDialogView.setSelectRoom(room2);
        } else {
            selectRoomDialogView.setSelectRoom(room);
        }
        selectRoomDialogView.setData(arrayList);
        builder.a((int) R.string.filter_room);
        builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) null);
        MLAlertDialog b2 = builder.b();
        selectRoomDialogView.setDialog(b2);
        b2.show();
        LinearLayout linearLayout = (LinearLayout) b2.getButton(-2).getParent();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        layoutParams.topMargin = 0;
        linearLayout.setLayoutParams(layoutParams);
        return b2;
    }

    public MLAlertDialog a(Activity activity, SelectRoomDialogView.IOnRoomSelectCallBack iOnRoomSelectCallBack, Room room, List<Room> list) {
        Room room2 = new Room();
        room2.e(activity.getString(R.string.smarthome_scene_all_room));
        room2.d(SelectRoomDialogView.ALL_ROOM_ID);
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(activity);
        SelectRoomDialogView selectRoomDialogView = (SelectRoomDialogView) LayoutInflater.from(activity).inflate(R.layout.select_room_dialog_view, (ViewGroup) null);
        builder.b((View) selectRoomDialogView);
        selectRoomDialogView.setCallBack(iOnRoomSelectCallBack);
        if (room == null) {
            selectRoomDialogView.setSelectRoom(room2);
        } else {
            selectRoomDialogView.setSelectRoom(room);
        }
        selectRoomDialogView.setData(list);
        builder.a((int) R.string.filter_room);
        builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) null);
        MLAlertDialog b2 = builder.b();
        selectRoomDialogView.setDialog(b2);
        b2.show();
        LinearLayout linearLayout = (LinearLayout) b2.getButton(-2).getParent();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        layoutParams.topMargin = 0;
        linearLayout.setLayoutParams(layoutParams);
        return b2;
    }

    public Room m() {
        return this.r;
    }

    public void a(Room room) {
        this.r = room;
    }

    public boolean c(List<SceneApi.Condition> list) {
        return list != null && list.size() == 1 && list.get(0).f21522a == SceneApi.Condition.LAUNCH_TYPE.CLICK;
    }

    public WeatherInnerCondition n() {
        return this.s;
    }

    public void a(WeatherInnerCondition weatherInnerCondition) {
        this.s = weatherInnerCondition;
    }

    public static class SelectedTpl {

        /* renamed from: a  reason: collision with root package name */
        String f21205a;
        int b;

        public SelectedTpl() {
        }

        public SelectedTpl(String str, int i) {
            this.f21205a = str;
            this.b = i;
        }
    }
}
