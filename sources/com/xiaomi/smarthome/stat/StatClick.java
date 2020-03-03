package com.xiaomi.smarthome.stat;

import com.google.android.gms.actions.SearchIntents;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.stat.report.StatReporter;
import com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater;

public abstract class StatClick {

    /* renamed from: a  reason: collision with root package name */
    private StatReporter f22751a = new StatReporter("click");

    public void a() {
        this.f22751a.a("ctoc_add_device", new Object[0]);
    }

    public void a(String str) {
        StatReporter statReporter = this.f22751a;
        Object[] objArr = new Object[2];
        objArr[0] = "name";
        if (str == null) {
            str = "null";
        }
        objArr[1] = str;
        statReporter.a("ctoc_binding_enter", objArr);
    }

    public void b(String str) {
        StatReporter statReporter = this.f22751a;
        Object[] objArr = new Object[2];
        objArr[0] = "name";
        if (str == null) {
            str = "null";
        }
        objArr[1] = str;
        statReporter.a("ctoc_detail_binding", objArr);
    }

    public void c(String str) {
        StatReporter statReporter = this.f22751a;
        Object[] objArr = new Object[2];
        objArr[0] = "name";
        if (str == null) {
            str = "null";
        }
        objArr[1] = str;
        statReporter.a("ctoc_synchronization", objArr);
    }

    public void d(String str) {
        StatReporter statReporter = this.f22751a;
        Object[] objArr = new Object[2];
        objArr[0] = "name";
        if (str == null) {
            str = "null";
        }
        objArr[1] = str;
        statReporter.a("ctoc_relieve", objArr);
    }

    public void a(String str, String str2) {
        if (!CoreApi.a().D()) {
            PluginRecord d = CoreApi.a().d(str2);
            long j = 0;
            if (d != null) {
                j = d.d();
            }
            StatReporter statReporter = this.f22751a;
            Object[] objArr = new Object[6];
            objArr[0] = "did";
            if (str == null) {
                str = "null";
            }
            objArr[1] = str;
            objArr[2] = "model";
            if (str2 == null) {
                str2 = "null";
            }
            objArr[3] = str2;
            objArr[4] = "pluginid";
            objArr[5] = Long.valueOf(j);
            statReporter.a("home_often_pluginicon", objArr);
        }
    }

    public void b(String str, String str2) {
        if (!CoreApi.a().D()) {
            PluginRecord d = CoreApi.a().d(str2);
            long j = 0;
            if (d != null) {
                j = d.d();
            }
            StatReporter statReporter = this.f22751a;
            Object[] objArr = new Object[6];
            objArr[0] = "did";
            if (str == null) {
                str = "null";
            }
            objArr[1] = str;
            objArr[2] = "model";
            if (str2 == null) {
                str2 = "null";
            }
            objArr[3] = str2;
            objArr[4] = "pluginid";
            objArr[5] = Long.valueOf(j);
            statReporter.a("home_often_plugin", objArr);
        }
    }

    public void b() {
        if (!CoreApi.a().D()) {
            this.f22751a.a("home_often_manage", "name", "null");
        }
    }

    public void c(String str, String str2) {
        if (!CoreApi.a().D()) {
            PluginRecord d = CoreApi.a().d(str2);
            long j = 0;
            if (d != null) {
                j = d.d();
            }
            StatReporter statReporter = this.f22751a;
            Object[] objArr = new Object[6];
            objArr[0] = "did";
            if (str == null) {
                str = "null";
            }
            objArr[1] = str;
            objArr[2] = "model";
            if (str2 == null) {
                str2 = "null";
            }
            objArr[3] = str2;
            objArr[4] = "pluginid";
            objArr[5] = Long.valueOf(j);
            statReporter.a("home_buildroom_plugin", objArr);
        }
    }

    public void a(String str, String str2, boolean z) {
        if (!CoreApi.a().D()) {
            PluginRecord d = CoreApi.a().d(str2);
            long j = 0;
            if (d != null) {
                j = d.d();
            }
            StatReporter statReporter = this.f22751a;
            Object[] objArr = new Object[8];
            objArr[0] = "did";
            if (str == null) {
                str = "null";
            }
            objArr[1] = str;
            objArr[2] = "model";
            if (str2 == null) {
                str2 = "null";
            }
            objArr[3] = str2;
            objArr[4] = "pluginid";
            objArr[5] = Long.valueOf(j);
            objArr[6] = "type";
            objArr[7] = z ? "开" : "关";
            statReporter.a("home_buildroom_pluginswitch", objArr);
        }
    }

    public void c() {
        if (!CoreApi.a().D()) {
            this.f22751a.a("home_whole_weather", "name", "null");
        }
    }

    public void d() {
        if (!CoreApi.a().D()) {
            this.f22751a.a("home_whole_scenecreate", "name", "null");
        }
    }

    public void e() {
        if (!CoreApi.a().D()) {
            this.f22751a.a("home_whole_scene", "name", "null");
        }
    }

    public void f() {
        if (!CoreApi.a().D()) {
            this.f22751a.a("home_whole_camera", "name", "null");
        }
    }

    public void g() {
        if (!CoreApi.a().D()) {
            this.f22751a.a("home_whole_speech", "name", "null");
        }
    }

    public void h() {
        if (!CoreApi.a().D()) {
            this.f22751a.a("home_plugin_slip", "name", "null");
        }
    }

    public void d(String str, String str2) {
        if (!CoreApi.a().D()) {
            PluginRecord d = CoreApi.a().d(str2);
            long j = 0;
            if (d != null) {
                j = d.d();
            }
            StatReporter statReporter = this.f22751a;
            Object[] objArr = new Object[6];
            objArr[0] = "did";
            if (str == null) {
                str = "null";
            }
            objArr[1] = str;
            objArr[2] = "model";
            if (str2 == null) {
                str2 = "null";
            }
            objArr[3] = str2;
            objArr[4] = "pluginid";
            objArr[5] = Long.valueOf(j);
            statReporter.a("home_pluginfloat_into", objArr);
        }
    }

    public void e(String str) {
        this.f22751a.a("adddevice_popup_determine", "model", str);
    }

    public void f(String str) {
        this.f22751a.a("adddevice_popup_ignore", "model", str);
    }

    public void i() {
        this.f22751a.a("adddevice_addhome_camera", new Object[0]);
    }

    public void g(String str) {
        this.f22751a.a("adddevice_addhome_nearby", "model", str);
    }

    public void j() {
        this.f22751a.a("adddevice_addhome_near", new Object[0]);
    }

    public void k() {
        this.f22751a.a("adddevice_addhome_scan", new Object[0]);
    }

    public void l() {
        this.f22751a.a("adddevice_addhome_manualadd", new Object[0]);
    }

    public void m() {
        this.f22751a.a("adddevice_addhome_return", new Object[0]);
    }

    public void h(String str) {
        this.f22751a.a("adddevice_camera_cameraclick", "model", str);
    }

    public void n() {
        this.f22751a.a("adddevice_camera_return", new Object[0]);
    }

    public void i(String str) {
        this.f22751a.a("adddevice_addmodel_device", "model", str);
    }

    public void j(String str) {
        this.f22751a.a("adddevice_manualadd_reset", "model", str);
    }

    public void k(String str) {
        this.f22751a.a("adddevice_manualadd_successsreset", "model", str);
    }

    public void l(String str) {
        this.f22751a.a("adddevice_maualadd_buy", "model", str);
    }

    public void o() {
        this.f22751a.a("adddevice_maualadd_return", new Object[0]);
    }

    public void e(String str, String str2) {
        ActionCounter b = STAT.i.b();
        this.f22751a.a(str, "model", str2, "add_device_session", Long.valueOf(b.a()), "add_device_order", Integer.valueOf(b.b()));
    }

    public void a(String str, int i, int i2) {
        ActionCounter b = STAT.i.b();
        this.f22751a.a("adddevice_wlan_next.AP", "model", str, "password", Integer.valueOf(i), "hide", Integer.valueOf(i2), "add_device_session", Long.valueOf(b.a()), "add_device_order", Integer.valueOf(b.b()));
    }

    public void a(String str, int i) {
        ActionCounter b = STAT.i.b();
        this.f22751a.a("adddevice_process_cancel.AP", "model", str, "process", Integer.valueOf(i), "add_device_session", Long.valueOf(b.a()), "add_device_order", Integer.valueOf(b.b()));
    }

    public void f(String str, String str2) {
        ActionCounter b = STAT.i.b();
        this.f22751a.a("adddevice_network_wifi.AP", "model", str, "name", str2, "add_device_session", Long.valueOf(b.a()), "add_device_order", Integer.valueOf(b.b()));
    }

    public void a(String str, String str2, int i) {
        ActionCounter b = STAT.i.b();
        this.f22751a.a("adddevice_process_success.AP", "model", str, "room", str2, "shortcut", Integer.valueOf(i), "add_device_session", Long.valueOf(b.a()), "add_device_order", Integer.valueOf(b.b()));
    }

    public void p() {
        STAT.i.b();
        this.f22751a.a("home_whole_device", new Object[0]);
    }

    public void m(String str) {
        this.f22751a.a("miui_fand_cancel", "model", str);
    }

    public void n(String str) {
        this.f22751a.a("miui_fand_determine", "model", str);
    }

    public void o(String str) {
        this.f22751a.a("miui_fand_close", "model", str);
    }

    public void q() {
        this.f22751a.a("mycanter_personal", new Object[0]);
    }

    public void r() {
        this.f22751a.a("mycanter_message", new Object[0]);
    }

    public void s() {
        this.f22751a.a("mycanter_shareddevices", new Object[0]);
    }

    public void t() {
        this.f22751a.a("mycanter_consumables", new Object[0]);
    }

    public void u() {
        this.f22751a.a("mycanter_voivecontrol", new Object[0]);
    }

    public void v() {
        this.f22751a.a("mycanter_family", new Object[0]);
    }

    public void w() {
        this.f22751a.a("mycanter_weixin", new Object[0]);
    }

    public void x() {
        this.f22751a.a("mycanter_myshop", new Object[0]);
    }

    public void y() {
        this.f22751a.a("mycanter_BLEgate", new Object[0]);
    }

    public void z() {
        this.f22751a.a("mycanter_experimentalfeatures", new Object[0]);
    }

    public void A() {
        this.f22751a.a("mycanter_otherplatforms", new Object[0]);
    }

    public void B() {
        this.f22751a.a("mycanter_privacypolicy", new Object[0]);
    }

    public void p(String str) {
        this.f22751a.a("mycanter_navigationi", "title", str);
    }

    public void C() {
        this.f22751a.a("mycanter_forum", new Object[0]);
    }

    public void D() {
        this.f22751a.a("mycanter_help", new Object[0]);
    }

    public void E() {
        this.f22751a.a("mycanter_app_score", new Object[0]);
    }

    public void F() {
        this.f22751a.a("mycanter_set", new Object[0]);
    }

    public void G() {
        this.f22751a.a("my_canter_consumables", new Object[0]);
    }

    public void g(String str, String str2) {
        this.f22751a.a("consumables_model", "type", str, "title", str2);
    }

    public void a(String str, String str2, String str3) {
        this.f22751a.a("mine_consum_click", "model", str, "title", str2, "url", str3);
    }

    public void q(String str) {
        this.f22751a.a("profile_ad_click", "url", str);
    }

    public void r(String str) {
        this.f22751a.a("profile_service_icon_click", "url", str);
    }

    public void H() {
        this.f22751a.a("add_navigation_near", new Object[0]);
    }

    public void I() {
        this.f22751a.a("add_navigation_add", new Object[0]);
    }

    public void J() {
        this.f22751a.a("add_navigation_search", new Object[0]);
    }

    public void K() {
        this.f22751a.a("add_near_notfind", new Object[0]);
    }

    public void L() {
        this.f22751a.a("add_near_more", new Object[0]);
    }

    public void s(String str) {
        this.f22751a.a("add_near_device", "model", str);
    }

    public void M() {
        this.f22751a.a("add_near_turnbluetooth", new Object[0]);
    }

    public void t(String str) {
        this.f22751a.a("add_manual_navigation", "title", str);
    }

    public void h(String str, String str2) {
        this.f22751a.a("add_manual_device", "model", str2, "title", str);
    }

    public void u(String str) {
        this.f22751a.a("add_search_input", "search", str);
    }

    public void i(String str, String str2) {
        this.f22751a.a("add_search_device", "search", str, "model", str2);
    }

    public void a(int i) {
        this.f22751a.a("set_found_model", "type", Integer.valueOf(i));
    }

    public void v(String str) {
        ActionCounter b = STAT.i.b();
        this.f22751a.a("add_near_wlan", "model", str, "add_device_session", Long.valueOf(b.a()), "add_device_order", Integer.valueOf(b.b()));
    }

    public void N() {
        ActionCounter b = STAT.i.b();
        this.f22751a.a("add_wlan_click", "add_device_session", Long.valueOf(b.a()), "add_device_order", Integer.valueOf(b.b()));
    }

    public void w(String str) {
        ActionCounter b = STAT.i.b();
        this.f22751a.a("add_wlan_editor", "model", str, "add_device_session", Long.valueOf(b.a()), "add_device_order", Integer.valueOf(b.b()));
    }

    public void x(String str) {
        ActionCounter b = STAT.i.b();
        this.f22751a.a("add_wait_yellow", "model", str, "add_device_session", Long.valueOf(b.a()), "add_device_order", Integer.valueOf(b.b()));
    }

    public void y(String str) {
        ActionCounter b = STAT.i.b();
        this.f22751a.a("add_wait_next", "model", str, "add_device_session", Long.valueOf(b.a()), "add_device_order", Integer.valueOf(b.b()));
    }

    public void a(String str, long j) {
        if (!CoreApi.a().p()) {
            ActionCounter b = STAT.i.b();
            this.f22751a.a("add_complete_click", "model", str, "time", Long.valueOf(j), "add_device_session", Long.valueOf(b.a()), "add_device_order", Integer.valueOf(b.b()));
        }
    }

    public void z(String str) {
        ActionCounter b = STAT.i.b();
        this.f22751a.a("add_fail1st_wifi", "model", str, "add_device_session", Long.valueOf(b.a()), "add_device_order", Integer.valueOf(b.b()));
    }

    public void A(String str) {
        ActionCounter b = STAT.i.b();
        this.f22751a.a("add_fail3nd_blue", "model", str, "add_device_session", Long.valueOf(b.a()), "add_device_order", Integer.valueOf(b.b()));
    }

    public void B(String str) {
        ActionCounter b = STAT.i.b();
        this.f22751a.a("add_fail3nd_lbblue", "model", str, "add_device_session", Long.valueOf(b.a()), "add_device_order", Integer.valueOf(b.b()));
    }

    public void C(String str) {
        ActionCounter b = STAT.i.b();
        this.f22751a.a("add_fail2nd_yellow", "model", str, "add_device_session", Long.valueOf(b.a()), "add_device_order", Integer.valueOf(b.b()));
    }

    public void D(String str) {
        ActionCounter b = STAT.i.b();
        this.f22751a.a("add_fail2nd_blue", "model", str, "add_device_session", Long.valueOf(b.a()), "add_device_order", Integer.valueOf(b.b()));
    }

    public void E(String str) {
        ActionCounter b = STAT.i.b();
        this.f22751a.a("add_fail2nd_lbblue", "model", str, "add_device_session", Long.valueOf(b.a()), "add_device_order", Integer.valueOf(b.b()));
    }

    public void F(String str) {
        ActionCounter b = STAT.i.b();
        this.f22751a.a("add_fail1st_manualadd", "model", str, "add_device_session", Long.valueOf(b.a()), "add_device_order", Integer.valueOf(b.b()));
    }

    public void G(String str) {
        ActionCounter b = STAT.i.b();
        this.f22751a.a("add_fail2nd_solution", "model", str, "add_device_session", Long.valueOf(b.a()), "add_device_order", Integer.valueOf(b.b()));
    }

    public void H(String str) {
        ActionCounter b = STAT.i.b();
        this.f22751a.a("add_fail3nd_solution", "model", str, "add_device_session", Long.valueOf(b.a()), "add_device_order", Integer.valueOf(b.b()));
    }

    public void I(String str) {
        ActionCounter b = STAT.i.b();
        this.f22751a.a("add_fail1st_retry", "model", str, "add_device_session", Long.valueOf(b.a()), "add_device_order", Integer.valueOf(b.b()));
    }

    public void J(String str) {
        ActionCounter b = STAT.i.b();
        this.f22751a.a("add_fail2nd_retry", "model", str, "add_device_session", Long.valueOf(b.a()), "add_device_order", Integer.valueOf(b.b()));
    }

    public void K(String str) {
        ActionCounter b = STAT.i.b();
        this.f22751a.a("add_fail3nd_refresh", "model", str, "add_device_session", Long.valueOf(b.a()), "add_device_order", Integer.valueOf(b.b()));
    }

    public void O() {
        this.f22751a.a("app_popup_cancel", new Object[0]);
    }

    public void P() {
        this.f22751a.a("app_popup_determine", new Object[0]);
    }

    public void L(String str) {
        this.f22751a.a("miui_popup_ignore", "model", str);
    }

    public void M(String str) {
        this.f22751a.a("miui_popup_add", "model", str);
    }

    public void N(String str) {
        this.f22751a.a("miui_popup_close", "model", str);
    }

    public void O(String str) {
        this.f22751a.a("app_popup_ignore", "model", str);
    }

    public void P(String str) {
        this.f22751a.a("app_popup_add", "model", str);
    }

    public void Q(String str) {
        this.f22751a.a("app_popup_close", "model", str);
    }

    public void b(String str, String str2, String str3) {
        ActionCounter b = STAT.i.b();
        this.f22751a.a("add_set_use", "name", str, "location", str2, "add", str3, "add_device_session", Long.valueOf(b.a()), "add_device_order", Integer.valueOf(b.b()));
    }

    public void b(String str, int i) {
        this.f22751a.a("miui_bottom_click", "model", str, "num", Integer.valueOf(i));
    }

    public void R(String str) {
        this.f22751a.a("miui_push_click", "model", str);
    }

    public void Q() {
        this.f22751a.a("home_device_state", new Object[0]);
    }

    public void S(String str) {
        this.f22751a.a("share_admin_entry", "model", str);
    }

    public void T(String str) {
        this.f22751a.a("share_MI_entry", "model", str);
    }

    public void U(String str) {
        this.f22751a.a("share_wechat_entry", "model", str);
    }

    public void R() {
        this.f22751a.a("set_room_list", new Object[0]);
    }

    public void S() {
        this.f22751a.a("set_card_entry", new Object[0]);
    }

    public void V(String str) {
        StatReporter statReporter = this.f22751a;
        Object[] objArr = new Object[1];
        if (str == null) {
            str = "";
        }
        objArr[0] = str;
        statReporter.a("miui_card_quick", objArr);
    }

    public void W(String str) {
        this.f22751a.a("set_roomdisplay_click", "type", str);
    }

    public void X(String str) {
        this.f22751a.a("set_cardquick_click", "type", str);
    }

    public void Y(String str) {
        this.f22751a.a("page_roomset_name", "room_newname", str);
    }

    public void T() {
        this.f22751a.a("page_roomset_icon", new Object[0]);
    }

    public void a(boolean z, boolean z2) {
        this.f22751a.a("page_roomset_preserve", "room_name_edit", Boolean.valueOf(z), "device_number_edit", Boolean.valueOf(z2));
    }

    public void U() {
        this.f22751a.a("page_roomadjust_delete", new Object[0]);
    }

    public void V() {
        this.f22751a.a("page_roomadjust_cancel", new Object[0]);
    }

    public void W() {
        this.f22751a.a("page_roomadjust_confirm", new Object[0]);
    }

    public void X() {
        this.f22751a.a("page_room_add", new Object[0]);
    }

    public void Z(String str) {
        this.f22751a.a("page_room_admin", "room", str);
    }

    public void Y() {
        this.f22751a.a("page_room_longclick", new Object[0]);
    }

    public void Z() {
        this.f22751a.a("page_family_entry", new Object[0]);
    }

    public void aa() {
        this.f22751a.a("page_family_add", new Object[0]);
    }

    public void c(String str, int i) {
        this.f22751a.a("home_card_edit", "type", str, "from", Integer.valueOf(i));
    }

    public void ab() {
        this.f22751a.a("home_add_room", new Object[0]);
    }

    public void aa(String str) {
        this.f22751a.a("home_room_switch", "room", str);
    }

    public void ac() {
        this.f22751a.a("home_move_cancel", new Object[0]);
    }

    public void ad() {
        this.f22751a.a("home_move_confirm", new Object[0]);
    }

    public void ab(String str) {
        this.f22751a.a("home_rename_cancel", "model", str);
    }

    public void ac(String str) {
        this.f22751a.a("home_rename_confirm", "model", str);
    }

    public void ad(String str) {
        this.f22751a.a("home_delete_cancel", "model", str);
    }

    public void ae(String str) {
        this.f22751a.a("home_delete_confirm", "model", str);
    }

    public void a(String str, boolean z) {
        StatReporter statReporter = this.f22751a;
        Object[] objArr = new Object[4];
        objArr[0] = "room";
        int i = 1;
        objArr[1] = str;
        objArr[2] = "type";
        if (z) {
            i = 2;
        }
        objArr[3] = Integer.valueOf(i);
        statReporter.a("home_room_navigation", objArr);
    }

    public void ae() {
        this.f22751a.a("home_navigation_share", new Object[0]);
    }

    public void af() {
        this.f22751a.a("home_navigation_near", new Object[0]);
    }

    public void ag() {
        this.f22751a.a("home_room_dropdown", new Object[0]);
    }

    public void ah() {
        this.f22751a.a("home_navigation_roomset", new Object[0]);
    }

    public void ai() {
        this.f22751a.a("home_fimaly_entry", new Object[0]);
    }

    public void a(String str, int i, int i2, int i3, String str2) {
        if (i == 0) {
            this.f22751a.a("home_longpress_plugin", "model", str, "from", Integer.valueOf(i), "serial", Integer.valueOf(i2), "total", Integer.valueOf(i3), "did", str2);
            return;
        }
        this.f22751a.a("home_longpress_plugin", "model", str, "from", Integer.valueOf(i), "did", str2);
    }

    public void af(String str) {
        this.f22751a.a("share_near_click", "model", str);
    }

    public void ag(String str) {
        this.f22751a.a("home_nodevice_ad_click", "url", str);
    }

    public void ah(String str) {
        this.f22751a.a("pintro_ad_click", "url", str);
    }

    public void ai(String str) {
        this.f22751a.a("profile_opd_ad_click", "url", str);
    }

    public void aj() {
        this.f22751a.a("home_add_device", new Object[0]);
    }

    public void a(boolean z) {
        StatReporter statReporter = this.f22751a;
        int i = 2;
        Object[] objArr = new Object[2];
        objArr[0] = "type";
        if (!z) {
            i = 1;
        }
        objArr[1] = Integer.valueOf(i);
        statReporter.a("miui_card_more", objArr);
    }

    public void b(boolean z) {
        StatReporter statReporter = this.f22751a;
        int i = 2;
        Object[] objArr = new Object[2];
        objArr[0] = "type";
        if (!z) {
            i = 1;
        }
        objArr[1] = Integer.valueOf(i);
        statReporter.a("miui_card_click", objArr);
    }

    public void b(int i) {
        this.f22751a.a("home_AI_entry", "type", Integer.valueOf(i));
    }

    public void ak() {
        this.f22751a.a("home_camera_entry", new Object[0]);
    }

    public void c(boolean z) {
        StatReporter statReporter = this.f22751a;
        int i = 2;
        Object[] objArr = new Object[2];
        objArr[0] = "type";
        if (z) {
            i = 1;
        }
        objArr[1] = Integer.valueOf(i);
        statReporter.a("home_navigation_category", objArr);
    }

    public void d(boolean z) {
        StatReporter statReporter = this.f22751a;
        int i = 2;
        Object[] objArr = new Object[2];
        objArr[0] = "type";
        if (z) {
            i = 1;
        }
        objArr[1] = Integer.valueOf(i);
        statReporter.a("home_navigation_priority", objArr);
    }

    public void aj(String str) {
        this.f22751a.a("scan_success", "model", str);
    }

    public void al() {
        this.f22751a.a("set_server_entry", new Object[0]);
    }

    public void am() {
        this.f22751a.a("set_language_entry", new Object[0]);
    }

    public void j(String str, String str2) {
        this.f22751a.a("set_server_click", "old", str, "new", str2);
    }

    public void k(String str, String str2) {
        this.f22751a.a("set_language_click", "old", str, "new", str2);
    }

    public void ak(String str) {
        this.f22751a.a("home_ad_click", "url", str);
    }

    public void a(String str, String str2, boolean z, int i, int i2, int i3, String str3) {
        int i4 = 2;
        if (i == 0) {
            StatReporter statReporter = this.f22751a;
            Object[] objArr = new Object[14];
            objArr[0] = "model";
            objArr[1] = str;
            objArr[2] = "room";
            objArr[3] = str2;
            objArr[4] = "share";
            if (!z) {
                i4 = 1;
            }
            objArr[5] = Integer.valueOf(i4);
            objArr[6] = "from";
            objArr[7] = Integer.valueOf(i);
            objArr[8] = "serial";
            objArr[9] = Integer.valueOf(i2);
            objArr[10] = "total";
            objArr[11] = Integer.valueOf(i3);
            objArr[12] = "did";
            objArr[13] = str3;
            statReporter.a("home_model_click", objArr);
            return;
        }
        StatReporter statReporter2 = this.f22751a;
        Object[] objArr2 = new Object[10];
        objArr2[0] = "model";
        objArr2[1] = str;
        objArr2[2] = "room";
        objArr2[3] = str2;
        objArr2[4] = "share";
        if (!z) {
            i4 = 1;
        }
        objArr2[5] = Integer.valueOf(i4);
        objArr2[6] = "from";
        objArr2[7] = Integer.valueOf(i);
        objArr2[8] = "did";
        objArr2[9] = str3;
        statReporter2.a("home_model_click", objArr2);
    }

    public void b(String str, String str2, boolean z, int i, int i2, int i3, String str3) {
        int i4 = 2;
        if (i == 0) {
            StatReporter statReporter = this.f22751a;
            Object[] objArr = new Object[14];
            objArr[0] = "model";
            objArr[1] = str;
            objArr[2] = "room";
            objArr[3] = str2;
            objArr[4] = "share";
            if (!z) {
                i4 = 1;
            }
            objArr[5] = Integer.valueOf(i4);
            objArr[6] = "from";
            objArr[7] = Integer.valueOf(i);
            objArr[8] = "serial";
            objArr[9] = Integer.valueOf(i2);
            objArr[10] = "total";
            objArr[11] = Integer.valueOf(i3);
            objArr[12] = "did";
            objArr[13] = str3;
            statReporter.a("home_model_float", objArr);
            return;
        }
        StatReporter statReporter2 = this.f22751a;
        Object[] objArr2 = new Object[10];
        objArr2[0] = "model";
        objArr2[1] = str;
        objArr2[2] = "room";
        objArr2[3] = str2;
        objArr2[4] = "share";
        if (!z) {
            i4 = 1;
        }
        objArr2[5] = Integer.valueOf(i4);
        objArr2[6] = "from";
        objArr2[7] = Integer.valueOf(i);
        objArr2[8] = "did";
        objArr2[9] = str3;
        statReporter2.a("home_model_float", objArr2);
    }

    public void b(String str, String str2, boolean z) {
        StatReporter statReporter = this.f22751a;
        Object[] objArr = new Object[6];
        objArr[0] = "model";
        int i = 1;
        objArr[1] = str;
        objArr[2] = "room";
        objArr[3] = str2;
        objArr[4] = "share";
        if (z) {
            i = 2;
        }
        objArr[5] = Integer.valueOf(i);
        statReporter.a("home_plugin_quick", objArr);
    }

    public void c(String str, String str2, boolean z) {
        StatReporter statReporter = this.f22751a;
        Object[] objArr = new Object[6];
        objArr[0] = "model";
        int i = 1;
        objArr[1] = str;
        objArr[2] = "room";
        objArr[3] = str2;
        objArr[4] = "share";
        if (z) {
            i = 2;
        }
        objArr[5] = Integer.valueOf(i);
        statReporter.a("home_device_quick", objArr);
    }

    public void d(String str, String str2, boolean z) {
        StatReporter statReporter = this.f22751a;
        Object[] objArr = new Object[6];
        objArr[0] = "model";
        int i = 1;
        objArr[1] = str;
        objArr[2] = "room";
        objArr[3] = str2;
        objArr[4] = "share";
        if (z) {
            i = 2;
        }
        objArr[5] = Integer.valueOf(i);
        statReporter.a("home_device_more", objArr);
    }

    public void al(String str) {
        this.f22751a.a("automation_ad_click", "url", str);
    }

    public void an() {
        this.f22751a.a("home_score_into", new Object[0]);
    }

    public void ao() {
        this.f22751a.a("home_score_cancel", new Object[0]);
    }

    public void ap() {
        this.f22751a.a("home_score_close", new Object[0]);
    }

    public void aq() {
        this.f22751a.a(XmNotificationCreater.f, new Object[0]);
    }

    public void c(int i) {
        this.f22751a.a("notification_switch", "type", Integer.valueOf(i));
    }

    public void am(String str) {
        this.f22751a.a("infrared_click", "title", str);
    }

    public void ar() {
        this.f22751a.a("infrared_all", new Object[0]);
    }

    public void a(int i, String str, long j) {
        this.f22751a.a("plugin_quick_result", "code", Integer.valueOf(i), "model", str, "time", Long.valueOf(j));
    }

    public void an(String str) {
        this.f22751a.a("adddevice_process_success_mike", "model", str);
    }

    public void d(String str, int i) {
        this.f22751a.a("adddevice_mikepopup_click", "model", str, "type", Integer.valueOf(i));
    }

    public void a(String str, String str2, int i, boolean z) {
        this.f22751a.a("plugin_offline_report", "model", str, "did", str2, "pid", Integer.valueOf(i), "isOnline", Integer.valueOf(z ? 1 : 0));
    }

    public void as() {
        this.f22751a.a("pintro_vip_click", new Object[0]);
    }

    public void ao(String str) {
        this.f22751a.a("plugin_share_back", "model", str);
    }

    public void ap(String str) {
        this.f22751a.a("plugin_share_wx", "model", str);
    }

    public void aq(String str) {
        this.f22751a.a("plugin_share_wx_pyq", "model", str);
    }

    public void ar(String str) {
        this.f22751a.a("plugin_share_weibo", "model", str);
    }

    public void as(String str) {
        this.f22751a.a("plugin_share_facebook", "model", str);
    }

    public void at(String str) {
        this.f22751a.a("plugin_share_line", "model", str);
    }

    public void au(String str) {
        this.f22751a.a("plugin_share_whatsapp", "model", str);
    }

    public void at() {
        this.f22751a.a("plugin_deviceshare_back", new Object[0]);
    }

    public void av(String str) {
        this.f22751a.a("plugin_deviceshare_mi", "model", str);
    }

    public void aw(String str) {
        this.f22751a.a("plugin_deviceshare_wx", "model", str);
    }

    public void ax(String str) {
        this.f22751a.a("plugin_deviceshare_family", "model", str);
    }

    public void ay(String str) {
        this.f22751a.a("plugin_deviceshare_edit", "model", str);
    }

    public void au() {
        this.f22751a.a("plugin_deviceshare_mi_back", new Object[0]);
    }

    public void a(boolean z, String str) {
        this.f22751a.a("plugin_deviceshare_mi_confirm", "result", Integer.valueOf(z ? 1 : 0), "account", str);
    }

    public void av() {
        this.f22751a.a("plugin_deviceshare_family_back", new Object[0]);
    }

    public void aw() {
        this.f22751a.a("plugin_deviceshare_family_familymumber", new Object[0]);
    }

    public void ax() {
        this.f22751a.a("plugin_deviceshare_edit_back", new Object[0]);
    }

    public void ay() {
        this.f22751a.a("plugin_deviceshare_edit_add", new Object[0]);
    }

    public void az() {
        this.f22751a.a("plugin_deviceshare_edit_delete", new Object[0]);
    }

    public void aA() {
        this.f22751a.a("mine_deviceshare_back", new Object[0]);
    }

    public void aB() {
        this.f22751a.a("mine_deviceshare_device", new Object[0]);
    }

    public void aC() {
        this.f22751a.a("mine_deviceshare_accept", new Object[0]);
    }

    public void aD() {
        this.f22751a.a("mine_myminiprograms_back", new Object[0]);
    }

    public void b(String str, boolean z) {
        this.f22751a.a("mine_myminiprograms_share", "model", str, "result", Boolean.valueOf(z));
    }

    public void aE() {
        this.f22751a.a("mine_myminiprograms_edit", new Object[0]);
    }

    public void aF() {
        this.f22751a.a("mine_myminiprograms_help", new Object[0]);
    }

    public void az(String str) {
        this.f22751a.a("addplugin_deviceshare_recent", "model", str);
    }

    public void aA(String str) {
        this.f22751a.a("addplugin_deviceshare_add", "model", str);
    }

    public void aB(String str) {
        this.f22751a.a("recommend_lockspeaker_automation", "model", str);
    }

    public void l(String str, String str2) {
        this.f22751a.a("recommend_lockspeaker_touchspeaker", "type", str, "model", str2);
    }

    public void m(String str, String str2) {
        this.f22751a.a("recommend_lockspeaker_clockspeaker", "type", str, "model", str2);
    }

    public void aC(String str) {
        this.f22751a.a("recommend_lockspeaker_plugin", "model", str);
    }

    public void d(int i) {
        this.f22751a.a("home_navigation_tab", "type", Integer.valueOf(i));
    }

    public void e(int i) {
        this.f22751a.a("home_navigation_dropdown", "type", Integer.valueOf(i));
    }

    public void f(int i) {
        this.f22751a.a("home_room_no_room_device", "no_room_device_number", Integer.valueOf(i));
    }

    public void g(int i) {
        this.f22751a.a("home_navigation_dropdown_category", "room_id", Integer.valueOf(i));
    }

    public void e(String str, int i) {
        this.f22751a.a("home_room_detail", "room_id", str, "room_device_number", Integer.valueOf(i));
    }

    public void aD(String str) {
        this.f22751a.a("home_room_device", "model", str);
    }

    public void aG() {
        this.f22751a.a("home_room_edit", new Object[0]);
    }

    public void aE(String str) {
        this.f22751a.a("general_countdown_use", "model", str);
    }

    public void aH() {
        this.f22751a.a("room_room_detail_back", new Object[0]);
    }

    public void aI() {
        this.f22751a.a("room_edit_sort", new Object[0]);
    }

    public void h(int i) {
        this.f22751a.a("room_device_edit_sort", "from", Integer.valueOf(i));
    }

    public void i(int i) {
        this.f22751a.a("room_device_edit_all", "type", Integer.valueOf(i));
    }

    public void aJ() {
        this.f22751a.a("room_addroom_set", new Object[0]);
    }

    public void j(int i) {
        this.f22751a.a("room_addroom_all", "type", Integer.valueOf(i));
    }

    public void n(String str, String str2) {
        this.f22751a.a("rec_auto_turnoff", "model", str, "sr_id", str2);
    }

    public void o(String str, String str2) {
        this.f22751a.a("rec_auto_delaytime", "model", str, "sr_id", str2);
    }

    public void p(String str, String str2) {
        this.f22751a.a("rec_auto_activetime", "model", str, "sr_id", str2);
    }

    public void c(String str, String str2, String str3) {
        this.f22751a.a("rec_choose_recdevice", "model", str, "sr_id", str2, "model2", str3);
    }

    public void d(String str, String str2, String str3) {
        this.f22751a.a("rec_choose_recmoredevice", "model", str, "sr_id", str2, "model2", str3);
    }

    public void e(String str, String str2, String str3) {
        this.f22751a.a("rec_choose_recble", "model", str, "sr_id", str2, "model2", str3);
    }

    public void q(String str, String str2) {
        this.f22751a.a("rec_light_auto", "model", str, "sr_id", str2);
    }

    public void r(String str, String str2) {
        this.f22751a.a("rec_light_plugin", "model", str, "sr_id", str2);
    }

    public void s(String str, String str2) {
        this.f22751a.a("rec_auto_activate", "model", str, "sr_id", str2);
    }

    public void a(int i, int i2) {
        this.f22751a.a("homeshare_acceptsuccess_switch", "from", Integer.valueOf(i), "type", Integer.valueOf(i2));
    }

    public void aK() {
        this.f22751a.a("homeshare_acceptsuccess_cancel", new Object[0]);
    }

    public void a(long j) {
        this.f22751a.a("home_management_accept", "home_id", Long.valueOf(j));
    }

    public void f(String str, int i) {
        this.f22751a.a("home_management_homeclick", "home_id", str, "type", Integer.valueOf(i));
    }

    public void aL() {
        this.f22751a.a("home_management_editname", new Object[0]);
    }

    public void aM() {
        this.f22751a.a("home_management_editlocation", new Object[0]);
    }

    public void aN() {
        this.f22751a.a("home_management_editroom", new Object[0]);
    }

    public void b(int i, int i2) {
        this.f22751a.a("home_management_clicknumber", "type", Integer.valueOf(i), "from", Integer.valueOf(i2));
    }

    public void aO() {
        this.f22751a.a("home_management_addnumber", new Object[0]);
    }

    public void k(int i) {
        this.f22751a.a("home_management_delete", "from", Integer.valueOf(i));
    }

    public void aF(String str) {
        this.f22751a.a("home_management_numberdetail_delete", "type", str);
    }

    public void aP() {
        this.f22751a.a("my_share_device", new Object[0]);
    }

    public void aQ() {
        this.f22751a.a("my_share_home", new Object[0]);
    }

    public void aR() {
        this.f22751a.a("my_share_wxapp", new Object[0]);
    }

    public void aS() {
        this.f22751a.a("homeshare_acceptalert_accept", new Object[0]);
    }

    public void aT() {
        this.f22751a.a("homeshare_acceptalert_cancel", new Object[0]);
    }

    public void aU() {
        this.f22751a.a("messagecenter_homeshare_accept", new Object[0]);
    }

    public void aV() {
        this.f22751a.a("homeshare_sharepage_back", new Object[0]);
    }

    public void aG(String str) {
        this.f22751a.a("homeshare_sharepage_mi", "home_id", str);
    }

    public void aH(String str) {
        this.f22751a.a("homeshare_sharepage_wx", "home_id", str);
    }

    public void aW() {
        this.f22751a.a("homeshare_mi_back", new Object[0]);
    }

    public void aX() {
        this.f22751a.a("homeshare_mi_confirm", new Object[0]);
    }

    public void aY() {
        this.f22751a.a("homeshare_education_skip", new Object[0]);
    }

    public void l(int i) {
        this.f22751a.a("homeshare_education_startnow", "type", Integer.valueOf(i));
    }

    public void a(int i, int i2, int i3) {
        this.f22751a.a("homeshare_education_share_skip", "contactnumber", Integer.valueOf(i), "homenumber", Integer.valueOf(i2), "type", Integer.valueOf(i3));
    }

    public void b(String str, int i, int i2) {
        this.f22751a.a("homeshare_education_share_sharenow", "home_id", str, "contactnumber", Integer.valueOf(i), "select_contact_number", Integer.valueOf(i2));
    }

    public void aZ() {
        this.f22751a.a("adddevice_nearbynavigation_click", new Object[0]);
    }

    public void ba() {
        this.f22751a.a("adddevice_search_click", new Object[0]);
    }

    public void bb() {
        this.f22751a.a("adddevice_devicelist_scan_click", new Object[0]);
    }

    public void c(int i, int i2) {
        this.f22751a.a("adddevice_catelog_click", "type", Integer.valueOf(i), "through", Integer.valueOf(i2));
    }

    public void a(String str, int i, int i2, int i3) {
        this.f22751a.a("adddevice_devicelist_click", "model", str, "serial", Integer.valueOf(i), "total", Integer.valueOf(i2), "type", Integer.valueOf(i3));
    }

    public void m(int i) {
        this.f22751a.a("adddevice_devicelistnavigation_click", "nearby-device", Integer.valueOf(i));
    }

    public void bc() {
        this.f22751a.a("adddevice_nearby_more_click", new Object[0]);
    }

    public void n(int i) {
        this.f22751a.a("adddevice_nearby_help_click", "nearby-device", Integer.valueOf(i));
    }

    public void aI(String str) {
        this.f22751a.a("adddevice_nearby_devicelist_click", "model", str);
    }

    public void a(int i, long j, String str) {
        this.f22751a.a("adddevice_scan_result", "type", Integer.valueOf(i), "time", Long.valueOf(j), "model", str);
    }

    public void aJ(String str) {
        this.f22751a.a("adddevice_search_noresult", SearchIntents.EXTRA_QUERY, str);
    }

    public void a(String str, String str2, int i, int i2) {
        this.f22751a.a("adddevice_search_result_click", SearchIntents.EXTRA_QUERY, str, "model", str2, "serial", Integer.valueOf(i), "total", Integer.valueOf(i2));
    }

    public void bd() {
        this.f22751a.a("mycanter_authorize", new Object[0]);
    }

    public void be() {
        this.f22751a.a("adddevice_myroom_click", new Object[0]);
    }

    public void bf() {
        this.f22751a.a("adddevice_addroom_click", new Object[0]);
    }

    public void bg() {
        this.f22751a.a("adddevice_suggestroom_click", new Object[0]);
    }

    public void bh() {
        this.f22751a.a("adddevice_selectroom_skip", new Object[0]);
    }

    public void f(String str, String str2, String str3) {
        this.f22751a.a("adddevice_rename_next", "type", str, "device-name", str2, "model", str3);
    }

    public void aK(String str) {
        this.f22751a.a("mi_push_click", "value", str);
    }

    public void aL(String str) {
        this.f22751a.a("adddevice_reset_confirm", "model", str);
    }

    public void aM(String str) {
        this.f22751a.a("adddevice_reset_next", "model", str);
    }

    public void aN(String str) {
        this.f22751a.a("adddevice_scanning_connectbyself", "model", str);
    }

    public void aO(String str) {
        this.f22751a.a("adddevice_scanning_fail_howtoreset", "model", str);
    }

    public void aP(String str) {
        this.f22751a.a("adddevice_scanning_fail_goon", "model", str);
    }

    public void aQ(String str) {
        this.f22751a.a("adddevice_wifi_next", "model", str);
    }

    public void aR(String str) {
        this.f22751a.a("adddevice_wifi_selectothers", "model", str);
    }

    public void aS(String str) {
        this.f22751a.a("adddevice_wifi_changepassword", "model", str);
    }

    public void aT(String str) {
        this.f22751a.a("adddevice_wifi_delete", "model", str);
    }

    public void aU(String str) {
        this.f22751a.a("adddevice_QR_novoice", "model", str);
    }

    public void aV(String str) {
        this.f22751a.a("adddevice_QR_next", "model", str);
    }

    public void aW(String str) {
        this.f22751a.a("adddevice_connect_confirm", "model", str);
    }

    public void aX(String str) {
        this.f22751a.a("adddevice_BLE_scanfail_cannotfind", "model", str);
    }

    public void aY(String str) {
        this.f22751a.a("adddevice_BLE_scanfail_rescan", "model", str);
    }

    public void aZ(String str) {
        this.f22751a.a("adddevice_BLE_appconfirm_select", "model", str);
    }

    public void ba(String str) {
        this.f22751a.a("adddevice_BLE_timeout_cancel", "model", str);
    }

    public void bb(String str) {
        this.f22751a.a("adddevice_BLE_timeout_retry", "model", str);
    }

    public void bc(String str) {
        this.f22751a.a("adddevice_BLE_androidconnectfail_retry", "model", str);
    }

    public void bd(String str) {
        this.f22751a.a("adddevice_BLE_androidconnectsuccess_ok", "model", str);
    }

    public long t(String str, String str2) {
        return this.f22751a.a("page_soundbox_click", "model", str, "category", str2);
    }

    public void bi() {
        this.f22751a.a("oversea_ecom_agree", new Object[0]);
    }

    public void a(String str, int i, long j) {
        this.f22751a.a("addlightgroup_creat_next", "type", str, "number", Integer.valueOf(i), "time", Long.valueOf(j));
    }

    public void be(String str) {
        this.f22751a.a("addlightgroup_try_on", "model", str);
    }

    public void bf(String str) {
        this.f22751a.a("addlightgroup_try_off", "model", str);
    }

    public void bg(String str) {
        this.f22751a.a("addlightgroup_try_reset", "model", str);
    }

    public void bh(String str) {
        this.f22751a.a("addlightgroup_try_click", "model", str);
    }

    public void bi(String str) {
        this.f22751a.a("addlightgroup_reset_click", "model", str);
    }

    public void bj(String str) {
        this.f22751a.a("addlightgroup_cancel_click", "model", str);
    }

    public void u(String str, String str2) {
        this.f22751a.a("lightgroup_edit_save", "model", str, "type", str2);
    }

    public void bk(String str) {
        this.f22751a.a("lightgroup_edit_back", "model", str);
    }

    public void bl(String str) {
        this.f22751a.a("lightgroup_upgrade_click", "model", str);
    }

    public void bm(String str) {
        this.f22751a.a("lightgroup_mesh_upgrade_click", "model", str);
    }

    public void bj() {
        this.f22751a.a("lightgroup_wifi_upgrade_click", new Object[0]);
    }

    public void bk() {
        this.f22751a.a("prompt_define_add_click", new Object[0]);
    }

    public void bl() {
        this.f22751a.a("prompt_cancel_add_click", new Object[0]);
    }

    public void bm() {
        this.f22751a.a("user_private_page", new Object[0]);
    }

    public void bn() {
        this.f22751a.a("del_user_auth", new Object[0]);
    }

    public void bo() {
        this.f22751a.a("del_user_authcon", new Object[0]);
    }

    public void c(String str, int i, int i2) {
        this.f22751a.a("adddevice_home_nearbydevice_click", "model", str, "serial", Integer.valueOf(i), "total", Integer.valueOf(i2));
    }

    public void g(String str, int i) {
        this.f22751a.a("adddevice_devicelist_more", "type", str, "number", Integer.valueOf(i));
    }

    public void bn(String str) {
        this.f22751a.a("adddevice_reset_details", "model", str);
    }

    public void o(int i) {
        this.f22751a.a("home_adddevice", "type", Integer.valueOf(i));
    }

    public void p(int i) {
        this.f22751a.a("home_changenavigation", "family_number", Integer.valueOf(i));
    }

    public void bo(String str) {
        this.f22751a.a("alert_app_p0c", "model", str);
    }

    public void bp(String str) {
        this.f22751a.a("alert_app_p2c", "model", str);
    }
}
