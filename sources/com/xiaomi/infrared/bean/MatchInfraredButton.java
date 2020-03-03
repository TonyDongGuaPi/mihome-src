package com.xiaomi.infrared.bean;

import com.alibaba.fastjson.parser.JSONLexer;
import com.xiaomi.infrared.InifraredContants;
import com.xiaomi.smarthome.R;
import org.apache.commons.lang.CharUtils;

public class MatchInfraredButton {

    /* renamed from: a  reason: collision with root package name */
    public int f10236a;
    public String b;
    private final IRType c;
    private final String d;
    private final String e;
    private final String f;
    private final String g;

    public String c() {
        return null;
    }

    public MatchInfraredButton(String str, String str2, String str3, String str4, IRType iRType, String str5) {
        this.c = iRType;
        this.e = str4;
        this.d = str3;
        this.f = str5;
        this.g = str2 == null ? "" : str2;
        this.b = str;
        String str6 = this.g;
        char c2 = 65535;
        switch (str6.hashCode()) {
            case -902669859:
                if (str6.equals(InifraredContants.V)) {
                    c2 = 25;
                    break;
                }
                break;
            case 19979:
                if (str6.equals(InifraredContants.z)) {
                    c2 = 3;
                    break;
                }
                break;
            case 21491:
                if (str6.equals(InifraredContants.F)) {
                    c2 = 9;
                    break;
                }
                break;
            case 24038:
                if (str6.equals(InifraredContants.E)) {
                    c2 = 8;
                    break;
                }
                break;
            case 659866:
                if (str6.equals(InifraredContants.D)) {
                    c2 = 7;
                    break;
                }
                break;
            case 665222:
                if (str6.equals(InifraredContants.Q)) {
                    c2 = 20;
                    break;
                }
                break;
            case 670745:
                if (str6.equals(InifraredContants.A)) {
                    c2 = 4;
                    break;
                }
                break;
            case 778119:
                if (str6.equals(InifraredContants.C)) {
                    c2 = 6;
                    break;
                }
                break;
            case 798069:
                if (str6.equals(InifraredContants.N)) {
                    c2 = 17;
                    break;
                }
                break;
            case 813114:
                if (str6.equals(InifraredContants.P)) {
                    c2 = 19;
                    break;
                }
                break;
            case 824881:
                if (str6.equals(InifraredContants.L)) {
                    c2 = 15;
                    break;
                }
                break;
            case 834074:
                if (str6.equals(InifraredContants.K)) {
                    c2 = 14;
                    break;
                }
                break;
            case 834888:
                if (str6.equals(InifraredContants.O)) {
                    c2 = 18;
                    break;
                }
                break;
            case 958459:
                if (str6.equals(InifraredContants.v)) {
                    c2 = JSONLexer.EOI;
                    break;
                }
                break;
            case 991478:
                if (str6.equals(InifraredContants.J)) {
                    c2 = CharUtils.b;
                    break;
                }
                break;
            case 1067769:
                if (str6.equals(InifraredContants.G)) {
                    c2 = 10;
                    break;
                }
                break;
            case 1163658:
                if (str6.equals(InifraredContants.w)) {
                    c2 = 0;
                    break;
                }
                break;
            case 1163770:
                if (str6.equals(InifraredContants.B)) {
                    c2 = 5;
                    break;
                }
                break;
            case 1239994:
                if (str6.equals(InifraredContants.H)) {
                    c2 = 11;
                    break;
                }
                break;
            case 1249553:
                if (str6.equals(InifraredContants.U)) {
                    c2 = 24;
                    break;
                }
                break;
            case 19844220:
                if (str6.equals(InifraredContants.M)) {
                    c2 = 16;
                    break;
                }
                break;
            case 19845181:
                if (str6.equals(InifraredContants.I)) {
                    c2 = 12;
                    break;
                }
                break;
            case 20346138:
                if (str6.equals(InifraredContants.R)) {
                    c2 = 21;
                    break;
                }
                break;
            case 38539119:
                if (str6.equals(InifraredContants.T)) {
                    c2 = 23;
                    break;
                }
                break;
            case 38539121:
                if (str6.equals(InifraredContants.S)) {
                    c2 = 22;
                    break;
                }
                break;
            case 38679177:
                if (str6.equals(InifraredContants.y)) {
                    c2 = 2;
                    break;
                }
                break;
            case 38679179:
                if (str6.equals(InifraredContants.x)) {
                    c2 = 1;
                    break;
                }
                break;
            case 921101345:
                if (str6.equals(InifraredContants.X)) {
                    c2 = 27;
                    break;
                }
                break;
            case 921104814:
                if (str6.equals(InifraredContants.W)) {
                    c2 = 28;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                this.f10236a = R.drawable.btn_match_back;
                return;
            case 1:
                this.f10236a = R.drawable.btn_match_ch_dec;
                return;
            case 2:
                this.f10236a = R.drawable.btn_match_ch_inc;
                return;
            case 3:
                this.f10236a = R.drawable.btn_match_down;
                return;
            case 4:
                this.f10236a = R.drawable.btn_match_eject;
                return;
            case 5:
                this.f10236a = R.drawable.btn_match_next;
                return;
            case 6:
                this.f10236a = R.drawable.btn_match_guide;
                return;
            case 7:
                this.f10236a = R.drawable.btn_match_home;
                return;
            case 8:
                this.f10236a = R.drawable.btn_match_left;
                return;
            case 9:
                this.f10236a = R.drawable.btn_match_right;
                return;
            case 10:
                this.f10236a = R.drawable.btn_match_menu;
                return;
            case 11:
                this.f10236a = R.drawable.btn_match_mute;
                return;
            case 12:
                this.f10236a = R.drawable.btn_match_next;
                return;
            case 13:
                this.f10236a = R.drawable.btn_match_ok;
                return;
            case 14:
                this.f10236a = R.drawable.btn_match_pause;
                return;
            case 15:
                this.f10236a = R.drawable.btn_match_play;
                return;
            case 16:
                this.f10236a = R.drawable.btn_match_previous;
                return;
            case 17:
                this.f10236a = R.drawable.btn_match_rew;
                return;
            case 18:
                this.f10236a = R.drawable.btn_match_shake_wind;
                return;
            case 19:
                this.f10236a = R.drawable.btn_match_shutter;
                return;
            case 20:
                this.f10236a = R.drawable.btn_match_stop;
                return;
            case 21:
                this.f10236a = R.drawable.btn_match_tvav;
                return;
            case 22:
                this.f10236a = R.drawable.btn_match_vol_dec;
                return;
            case 23:
                this.f10236a = R.drawable.btn_match_vol_inc;
                return;
            case 24:
                this.f10236a = R.drawable.btn_match_wind_speed;
                return;
            case 25:
            case 26:
                this.f10236a = R.drawable.btn_match_power;
                return;
            case 27:
            case 28:
                this.f10236a = R.drawable.btn_match_power;
                return;
            default:
                this.f10236a = R.drawable.btn_match_default;
                return;
        }
    }

    public int a() {
        return this.f10236a;
    }

    public String b() {
        return this.b;
    }

    public IRType d() {
        return this.c;
    }

    public String e() {
        return this.d;
    }

    public String f() {
        return this.e;
    }

    public String g() {
        return this.f;
    }
}
