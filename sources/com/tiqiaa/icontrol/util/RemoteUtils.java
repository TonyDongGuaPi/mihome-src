package com.tiqiaa.icontrol.util;

import android.content.Context;
import android.util.Log;
import com.mi.mistatistic.sdk.data.EventData;
import com.tiqiaa.constant.KeyType;
import com.tiqiaa.remote.entity.Infrared;
import com.tiqiaa.remote.entity.Key;
import com.tiqiaa.remote.entity.Remote;
import com.xiaomi.infrared.InifraredContants;
import com.xiaomi.jr.stats.SensorsDataManager;
import java.lang.reflect.Field;
import java.util.Locale;

public class RemoteUtils {
    private static final String TAG = "RemoteUtils";

    public static String getKeyName(int i) {
        if (i == 1000) {
            return "冷风";
        }
        if (i == 1800) {
            return "第二电源键";
        }
        switch (i) {
            case -100:
                return "自定义键-圆形";
            case -99:
                return "自定义键-椭圆";
            case KeyType.BASE_SQUARE:
                return "自定义键-方形";
            case -97:
                return "自定义键-红色";
            case KeyType.BASE_OVAL_ORANGE:
                return "自定义键-橙色";
            case KeyType.BASE_OVAL_YELLOW:
                return "自定义键-黄色";
            case KeyType.BASE_OVAL_GREEN:
                return "自定义键-绿色";
            case KeyType.BASE_OVAL_BLUE:
                return "自定义键-蓝色";
            case KeyType.BASE_OVAL_CYAN:
                return "自定义键-青色";
            case KeyType.BASE_OVAL_PURPLE:
                return "自定义键-紫色";
            case -90:
                return "记忆键";
            default:
                switch (i) {
                    case 0:
                        return "数字键 0";
                    case 1:
                        return "数字键 1";
                    case 2:
                        return "数字键 2";
                    case 3:
                        return "数字键 3";
                    case 4:
                        return "数字键 4";
                    case 5:
                        return "数字键 5";
                    case 6:
                        return "数字键 6";
                    case 7:
                        return "数字键 7";
                    case 8:
                        return "数字键 8";
                    case 9:
                        return "数字键 9";
                    default:
                        switch (i) {
                            case 800:
                                return InifraredContants.v;
                            case 801:
                                return "信源键";
                            case 802:
                                return "信息显示";
                            case 803:
                                return "回看";
                            case 804:
                                return InifraredContants.H;
                            case KeyType.DIGITAL:
                                return "数位";
                            case KeyType.BACK:
                                return InifraredContants.w;
                            case KeyType.CHANNEL_UP:
                                return InifraredContants.y;
                            case 808:
                                return InifraredContants.x;
                            case KeyType.VOL_UP:
                                return InifraredContants.T;
                            case 810:
                                return InifraredContants.S;
                            case KeyType.TEMP_UP:
                                return "温度加";
                            case KeyType.TEMP_DOWN:
                                return "温度减";
                            case KeyType.D_ZOOM_UP:
                                return "放大";
                            case KeyType.D_ZOOM_DOWN:
                                return "缩小";
                            case KeyType.MEMORYKEY_ONE:
                                return "记忆键1";
                            case 816:
                                return "记忆键2";
                            case 817:
                                return "OK键";
                            case 818:
                                return "上翻";
                            case 819:
                                return "下翻";
                            case KeyType.MENU_LEFT:
                                return "左翻";
                            case KeyType.MENU_RIGHT:
                                return "右翻";
                            case KeyType.MENU:
                                return "菜单键";
                            case KeyType.MENU_EXIT:
                                return InifraredContants.B;
                            case KeyType.FORWARD:
                                return "前进";
                            case KeyType.REWIND:
                                return "后退键";
                            case KeyType.PLAY_PAUSE:
                                return "暂停/播放";
                            case KeyType.STOP:
                                return InifraredContants.Q;
                            case KeyType.PREVIOUS:
                                return "上一个";
                            case KeyType.NEXT:
                                return "下一个";
                            case KeyType.TOP:
                                return "到顶";
                            case KeyType.BOTTOM:
                                return "到底";
                            case 832:
                                return "模式";
                            case KeyType.WIND_AMOUNT:
                                return "风量";
                            case KeyType.WIND_HORIZONTAL:
                                return "水平风向";
                            case KeyType.WIND_VERTICAL:
                                return "垂直风向";
                            case KeyType.HEAD_SHAKING:
                                return "摇头";
                            case KeyType.WIND_CLASS:
                                return "风类";
                            case KeyType.WIND_VELOCITY:
                                return InifraredContants.U;
                            case KeyType.OPEN:
                                return "打开";
                            case KeyType.TITLE:
                                return "标题";
                            case KeyType.TEN_PLUS:
                                return "+10";
                            case KeyType.LANGUAGE:
                                return "语言";
                            case KeyType.SCREEN:
                                return "屏幕";
                            case KeyType.SOUND_CHANNEL:
                                return "声道";
                            case KeyType.STANDARD:
                                return "制式";
                            case KeyType.SUBTITLES:
                                return "字幕";
                            case KeyType.DUAL_SCREEN:
                                return "双画面";
                            case 848:
                                return "画面冻结";
                            case KeyType.RESET:
                                return "重置";
                            case KeyType.VIDEO:
                                return "视频";
                            case KeyType.STEP_SLOW:
                                return "慢放";
                            case KeyType.SHUTTER_ONE:
                                return "单反主键";
                            case KeyType.SHUTTER_TWO:
                                return "单反副键";
                            case KeyType.CONTINUE_UP:
                                return "连续+";
                            case KeyType.CONTINUE_DOWN:
                                return "连续-";
                            case KeyType.CONTINUE_RIGHT:
                                return "连续右";
                            case KeyType.CONTINUE_LEFT:
                                return "连续左";
                            default:
                                switch (i) {
                                    case KeyType.AIR_WIND_DIRECT:
                                        return "风向";
                                    case KeyType.AIR_LIGHT:
                                        return "灯光";
                                    case KeyType.AIR_SUPER:
                                        return "超强";
                                    case KeyType.AIR_SLEEP:
                                        return "睡眠";
                                    case KeyType.AIR_FLASH_AIR:
                                        return "换气";
                                    case KeyType.AIR_AID_HOT:
                                        return "干燥";
                                    case KeyType.AIR_TIME:
                                        return "定时";
                                    case KeyType.AIR_WET:
                                        return "加湿";
                                    case KeyType.AIR_ANION:
                                        return "负离子";
                                    case KeyType.AIR_POWER_SAVING:
                                        return "节能";
                                    case KeyType.AIR_COMFORT:
                                        return "舒适";
                                    case KeyType.AIR_TEMP_DISPLAY:
                                        return "温度显示";
                                    case KeyType.AIR_QUICK_COOL:
                                        return "一键冷";
                                    case KeyType.AIR_QUICK_HOT:
                                        return "一键热";
                                    default:
                                        switch (i) {
                                            case 900:
                                                return "自动";
                                            case 901:
                                                return "信号";
                                            case 902:
                                                return "灯光";
                                            case KeyType.PC:
                                                return "电脑";
                                            default:
                                                switch (i) {
                                                    case 1010:
                                                        return "首页";
                                                    case 1011:
                                                        return "设置";
                                                    case 1012:
                                                        return "弹出菜单";
                                                    case 1013:
                                                        return "顶菜单";
                                                    default:
                                                        switch (i) {
                                                            case 2001:
                                                                return "收藏按钮";
                                                            case 2002:
                                                                return "数字按钮";
                                                            case 2003:
                                                                return "扩展";
                                                            default:
                                                                return "其他";
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }
    }

    public static String getRemoteName(Remote remote) {
        String str;
        if (remote == null) {
            return SensorsDataManager.u;
        }
        Locale locale = Locale.getDefault();
        Log.w(TAG, "getRemoteName............local = " + locale);
        if (remote.getBrand() == null) {
            str = String.valueOf("") + "Unknown Brand";
        } else if (locale.equals(Locale.CHINA) || locale.equals(Locale.CHINESE) || locale.equals(Locale.SIMPLIFIED_CHINESE)) {
            if (remote.getBrand().getBrand_cn() != null && !remote.getBrand().getBrand_cn().equals("")) {
                str = String.valueOf("") + remote.getBrand().getBrand_cn();
            } else if (remote.getBrand().getBrand_tw() != null && !remote.getBrand().getBrand_tw().equals("")) {
                str = String.valueOf("") + remote.getBrand().getBrand_tw();
            } else if (remote.getBrand().getBrand_en() != null && !remote.getBrand().getBrand_en().equals("")) {
                str = String.valueOf("") + remote.getBrand().getBrand_en();
            } else if (remote.getBrand().getBrand_other() == null || remote.getBrand().getBrand_other().equals("")) {
                str = String.valueOf("") + "Unknown Brand";
            } else {
                str = String.valueOf("") + remote.getBrand().getBrand_other();
            }
        } else if (remote.getBrand().getBrand_en() != null && !remote.getBrand().getBrand_en().equals("")) {
            str = String.valueOf("") + remote.getBrand().getBrand_en();
        } else if (remote.getBrand().getBrand_cn() != null && !remote.getBrand().getBrand_cn().equals("")) {
            str = String.valueOf("") + remote.getBrand().getBrand_cn();
        } else if (remote.getBrand().getBrand_tw() != null && !remote.getBrand().getBrand_tw().equals("")) {
            str = String.valueOf("") + remote.getBrand().getBrand_tw();
        } else if (remote.getBrand().getBrand_other() == null || remote.getBrand().getBrand_other().equals("")) {
            str = String.valueOf("") + "Unknown Brand";
        } else {
            str = String.valueOf("") + remote.getBrand().getBrand_other();
        }
        switch (remote.getType()) {
            case 1:
                if (!locale.equals(Locale.CHINA) && !locale.equals(Locale.CHINESE) && !locale.equals(Locale.SIMPLIFIED_CHINESE)) {
                    str = String.valueOf(str) + " TV";
                    break;
                } else {
                    str = String.valueOf(str) + " 电视";
                    break;
                }
            case 2:
                if (!locale.equals(Locale.CHINA) && !locale.equals(Locale.CHINESE) && !locale.equals(Locale.SIMPLIFIED_CHINESE)) {
                    str = String.valueOf(str) + " AC";
                    break;
                } else {
                    str = String.valueOf(str) + " 空调";
                    break;
                }
            case 3:
                if (!locale.equals(Locale.CHINA) && !locale.equals(Locale.CHINESE) && !locale.equals(Locale.SIMPLIFIED_CHINESE)) {
                    str = String.valueOf(str) + " fan";
                    break;
                } else {
                    str = String.valueOf(str) + " 风扇";
                    break;
                }
            case 4:
                if (!locale.equals(Locale.CHINA) && !locale.equals(Locale.CHINESE) && !locale.equals(Locale.SIMPLIFIED_CHINESE)) {
                    str = String.valueOf(str) + " Projector";
                    break;
                } else {
                    str = String.valueOf(str) + " 投影仪";
                    break;
                }
                break;
            case 5:
                if (!locale.equals(Locale.CHINA) && !locale.equals(Locale.CHINESE) && !locale.equals(Locale.SIMPLIFIED_CHINESE)) {
                    str = String.valueOf(str) + " STB";
                    break;
                } else {
                    str = String.valueOf(str) + " 机顶盒";
                    break;
                }
                break;
            case 6:
                if (!locale.equals(Locale.CHINA) && !locale.equals(Locale.CHINESE) && !locale.equals(Locale.SIMPLIFIED_CHINESE)) {
                    str = String.valueOf(str) + " DVD";
                    break;
                } else {
                    str = String.valueOf(str) + " DVD";
                    break;
                }
                break;
            case 7:
                if (!locale.equals(Locale.CHINA) && !locale.equals(Locale.CHINESE) && !locale.equals(Locale.SIMPLIFIED_CHINESE)) {
                    str = String.valueOf(str) + " Camera";
                    break;
                } else {
                    str = String.valueOf(str) + " 相机";
                    break;
                }
                break;
            case 8:
                if (!locale.equals(Locale.CHINA) && !locale.equals(Locale.CHINESE) && !locale.equals(Locale.SIMPLIFIED_CHINESE)) {
                    str = String.valueOf(str) + " Light";
                    break;
                } else {
                    str = String.valueOf(str) + " 遥控灯";
                    break;
                }
                break;
            case 9:
                if (!locale.equals(Locale.CHINA) && !locale.equals(Locale.CHINESE) && !locale.equals(Locale.SIMPLIFIED_CHINESE)) {
                    str = String.valueOf(str) + " Amplifier";
                    break;
                } else {
                    str = String.valueOf(str) + " 功放";
                    break;
                }
                break;
            case 10:
                if (!locale.equals(Locale.CHINA) && !locale.equals(Locale.CHINESE) && !locale.equals(Locale.SIMPLIFIED_CHINESE)) {
                    str = String.valueOf(str) + " IPTV";
                    break;
                } else {
                    str = String.valueOf(str) + " IPTV";
                    break;
                }
                break;
            case 11:
                if (!locale.equals(Locale.CHINA) && !locale.equals(Locale.CHINESE) && !locale.equals(Locale.SIMPLIFIED_CHINESE)) {
                    str = String.valueOf(str) + " Box";
                    break;
                } else {
                    str = String.valueOf(str) + " 盒子";
                    break;
                }
                break;
        }
        if (remote.getModel() == null) {
            return str;
        }
        return String.valueOf(str) + " " + remote.getModel();
    }

    public static boolean isMultiAirRemote(Remote remote) {
        if (remote == null || remote.getKeys() == null || remote.getKeys().size() == 0 || remote.getType() != 2) {
            return false;
        }
        for (Key next : remote.getKeys()) {
            if (next != null && next.getProtocol() > 0) {
                return true;
            }
        }
        return isDiyMultiAirRemote(remote);
    }

    public static boolean isProtocolAirRemote(Remote remote) {
        if (remote == null || remote.getKeys() == null || remote.getKeys().size() == 0) {
            return false;
        }
        for (Key next : remote.getKeys()) {
            if (next != null && next.getProtocol() > 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean isDiyMultiAirRemote(Remote remote) {
        if (remote == null || remote.getKeys() == null || remote.getKeys().size() == 0) {
            return false;
        }
        for (Key next : remote.getKeys()) {
            if (!(next == null || next.getInfrareds() == null || next.getInfrareds().size() <= 0)) {
                for (Infrared next2 : next.getInfrareds()) {
                    if (next2 != null && next2.getFunc() > 0) {
                        return true;
                    }
                }
                continue;
            }
        }
        return false;
    }

    public static int getItemId(Context context, String str, String str2) {
        try {
            Field field = Class.forName(String.valueOf(context.getPackageName()) + ".R$" + str).getField(str2);
            return Integer.parseInt(field.get(field.getName()).toString());
        } catch (Exception e) {
            Log.e("getIdByReflection error", e.getMessage());
            return 0;
        }
    }

    public static int getStringResIDByName(Context context, String str) {
        return context.getResources().getIdentifier(str, EventData.b, context.getPackageName());
    }

    public static String getLocalKeyName(int i) {
        Context context = TiqiaaService.mContext;
        if (i == 1000) {
            return context.getString(getStringResIDByName(context, "KeyType_cool_wind"));
        }
        if (i == 1800) {
            return context.getString(getStringResIDByName(context, "KeyType_power_second"));
        }
        switch (i) {
            case -100:
                return context.getString(getStringResIDByName(context, "KeyType_base_round"));
            case -99:
                return context.getString(getStringResIDByName(context, "KeyType_base_oval"));
            case KeyType.BASE_SQUARE:
                return context.getString(getStringResIDByName(context, "KeyType_base_square"));
            case -97:
                return context.getString(getStringResIDByName(context, "KeyType_base_oval_red"));
            case KeyType.BASE_OVAL_ORANGE:
                return context.getString(getStringResIDByName(context, "KeyType_base_oval_orange"));
            case KeyType.BASE_OVAL_YELLOW:
                return context.getString(getStringResIDByName(context, "KeyType_base_oval_yellow"));
            case KeyType.BASE_OVAL_GREEN:
                return context.getString(getStringResIDByName(context, "KeyType_base_oval_green"));
            case KeyType.BASE_OVAL_BLUE:
                return context.getString(getStringResIDByName(context, "KeyType_base_oval_blue"));
            case KeyType.BASE_OVAL_CYAN:
                return context.getString(getStringResIDByName(context, "KeyType_base_oval_cyan"));
            case KeyType.BASE_OVAL_PURPLE:
                return context.getString(getStringResIDByName(context, "KeyType_base_oval_purple"));
            case -90:
                return context.getString(getStringResIDByName(context, "KeyType_memorykey"));
            default:
                switch (i) {
                    case 0:
                        return context.getString(getStringResIDByName(context, "KeyType_zero"));
                    case 1:
                        return context.getString(getStringResIDByName(context, "KeyType_one"));
                    case 2:
                        return context.getString(getStringResIDByName(context, "KeyType_two"));
                    case 3:
                        return context.getString(getStringResIDByName(context, "KeyType_three"));
                    case 4:
                        return context.getString(getStringResIDByName(context, "KeyType_four"));
                    case 5:
                        return context.getString(getStringResIDByName(context, "KeyType_five"));
                    case 6:
                        return context.getString(getStringResIDByName(context, "KeyType_six"));
                    case 7:
                        return context.getString(getStringResIDByName(context, "KeyType_seven"));
                    case 8:
                        return context.getString(getStringResIDByName(context, "KeyType_eight"));
                    case 9:
                        return context.getString(getStringResIDByName(context, "KeyType_nine"));
                    default:
                        switch (i) {
                            case 800:
                                return context.getString(getStringResIDByName(context, "KeyType_power"));
                            case 801:
                                return context.getString(getStringResIDByName(context, "KeyType_signal"));
                            case 802:
                                return context.getString(getStringResIDByName(context, "KeyType_information"));
                            case 803:
                                return context.getString(getStringResIDByName(context, "KeyType_look_back"));
                            case 804:
                                return context.getString(getStringResIDByName(context, "KeyType_mute"));
                            case KeyType.DIGITAL:
                                return context.getString(getStringResIDByName(context, "KeyType_digital"));
                            case KeyType.BACK:
                                return context.getString(getStringResIDByName(context, "KeyType_back"));
                            case KeyType.CHANNEL_UP:
                                return context.getString(getStringResIDByName(context, "KeyType_channel_up"));
                            case 808:
                                return context.getString(getStringResIDByName(context, "KeyType_channel_down"));
                            case KeyType.VOL_UP:
                                return context.getString(getStringResIDByName(context, "KeyType_vol_up"));
                            case 810:
                                return context.getString(getStringResIDByName(context, "KeyType_vol_down"));
                            case KeyType.TEMP_UP:
                                return context.getString(getStringResIDByName(context, "KeyType_temp_up"));
                            case KeyType.TEMP_DOWN:
                                return context.getString(getStringResIDByName(context, "KeyType_temp_down"));
                            case KeyType.D_ZOOM_UP:
                                return context.getString(getStringResIDByName(context, "KeyType_d_zoom_up"));
                            case KeyType.D_ZOOM_DOWN:
                                return context.getString(getStringResIDByName(context, "KeyType_d_zoom_down"));
                            case KeyType.MEMORYKEY_ONE:
                                return context.getString(getStringResIDByName(context, "KeyType_memorykey_one"));
                            case 816:
                                return context.getString(getStringResIDByName(context, "KeyType_memorykey_two"));
                            case 817:
                                return context.getString(getStringResIDByName(context, "KeyType_menu_ok"));
                            case 818:
                                return context.getString(getStringResIDByName(context, "KeyType_menu_up"));
                            case 819:
                                return context.getString(getStringResIDByName(context, "KeyType_menu_down"));
                            case KeyType.MENU_LEFT:
                                return context.getString(getStringResIDByName(context, "KeyType_menu_left"));
                            case KeyType.MENU_RIGHT:
                                return context.getString(getStringResIDByName(context, "KeyType_menu_right"));
                            case KeyType.MENU:
                                return context.getString(getStringResIDByName(context, "KeyType_menu"));
                            case KeyType.MENU_EXIT:
                                return context.getString(getStringResIDByName(context, "KeyType_menu_exit"));
                            case KeyType.FORWARD:
                                return context.getString(getStringResIDByName(context, "KeyType_forward"));
                            case KeyType.REWIND:
                                return context.getString(getStringResIDByName(context, "KeyType_rewind"));
                            case KeyType.PLAY_PAUSE:
                                return context.getString(getStringResIDByName(context, "KeyType_pause"));
                            case KeyType.STOP:
                                return context.getString(getStringResIDByName(context, "KeyType_stop"));
                            case KeyType.PREVIOUS:
                                return context.getString(getStringResIDByName(context, "KeyType_previous"));
                            case KeyType.NEXT:
                                return context.getString(getStringResIDByName(context, "KeyType_next"));
                            case KeyType.TOP:
                                return context.getString(getStringResIDByName(context, "KeyType_top"));
                            case KeyType.BOTTOM:
                                return context.getString(getStringResIDByName(context, "KeyType_bottom"));
                            case 832:
                                return context.getString(getStringResIDByName(context, "KeyType_mode"));
                            case KeyType.WIND_AMOUNT:
                                return context.getString(getStringResIDByName(context, "KeyType_wind_amount"));
                            case KeyType.WIND_HORIZONTAL:
                                return context.getString(getStringResIDByName(context, "KeyType_wind_horizontal"));
                            case KeyType.WIND_VERTICAL:
                                return context.getString(getStringResIDByName(context, "KeyType_wind_vertical"));
                            case KeyType.HEAD_SHAKING:
                                return context.getString(getStringResIDByName(context, "KeyType_head_swing"));
                            case KeyType.WIND_CLASS:
                                return context.getString(getStringResIDByName(context, "KeyType_wind_class"));
                            case KeyType.WIND_VELOCITY:
                                return context.getString(getStringResIDByName(context, "KeyType_wind_velocity"));
                            case KeyType.OPEN:
                                return context.getString(getStringResIDByName(context, "KeyType_open"));
                            case KeyType.TITLE:
                                return context.getString(getStringResIDByName(context, "KeyType_title"));
                            case KeyType.TEN_PLUS:
                                return context.getString(getStringResIDByName(context, "KeyType_ten_plus"));
                            case KeyType.LANGUAGE:
                                return context.getString(getStringResIDByName(context, "KeyType_language"));
                            case KeyType.SCREEN:
                                return context.getString(getStringResIDByName(context, "KeyType_screen"));
                            case KeyType.SOUND_CHANNEL:
                                return context.getString(getStringResIDByName(context, "KeyType_sound_channel"));
                            case KeyType.STANDARD:
                                return context.getString(getStringResIDByName(context, "KeyType_standard"));
                            case KeyType.SUBTITLES:
                                return context.getString(getStringResIDByName(context, "KeyType_subtitles"));
                            case KeyType.DUAL_SCREEN:
                                return context.getString(getStringResIDByName(context, "KeyType_dual_screen"));
                            case 848:
                                return context.getString(getStringResIDByName(context, "KeyType_freeze"));
                            case KeyType.RESET:
                                return context.getString(getStringResIDByName(context, "KeyType_reset"));
                            case KeyType.VIDEO:
                                return context.getString(getStringResIDByName(context, "KeyType_video"));
                            case KeyType.STEP_SLOW:
                                return context.getString(getStringResIDByName(context, "KeyType_step_slow"));
                            case KeyType.SHUTTER_ONE:
                                return context.getString(getStringResIDByName(context, "KeyType_shutter_one"));
                            case KeyType.SHUTTER_TWO:
                                return context.getString(getStringResIDByName(context, "KeyType_shutter_two"));
                            case KeyType.CONTINUE_UP:
                                return context.getString(getStringResIDByName(context, "KeyType_continue_up"));
                            case KeyType.CONTINUE_DOWN:
                                return context.getString(getStringResIDByName(context, "KeyType_continue_down"));
                            case KeyType.CONTINUE_RIGHT:
                                return context.getString(getStringResIDByName(context, "KeyType_continue_right"));
                            case KeyType.CONTINUE_LEFT:
                                return context.getString(getStringResIDByName(context, "KeyType_continue_left"));
                            default:
                                switch (i) {
                                    case KeyType.AIR_WIND_DIRECT:
                                        return context.getString(getStringResIDByName(context, "KeyType_air_wind_direct"));
                                    case KeyType.AIR_LIGHT:
                                        return context.getString(getStringResIDByName(context, "KeyType_air_light"));
                                    case KeyType.AIR_SUPER:
                                        return context.getString(getStringResIDByName(context, "KeyType_air_super"));
                                    case KeyType.AIR_SLEEP:
                                        return context.getString(getStringResIDByName(context, "KeyType_air_sleep"));
                                    case KeyType.AIR_FLASH_AIR:
                                        return context.getString(getStringResIDByName(context, "KeyType_air_flash_air"));
                                    case KeyType.AIR_AID_HOT:
                                        return context.getString(getStringResIDByName(context, "KeyType_air_aid_hot"));
                                    case KeyType.AIR_TIME:
                                        return context.getString(getStringResIDByName(context, "KeyType_air_time"));
                                    case KeyType.AIR_WET:
                                        return context.getString(getStringResIDByName(context, "KeyType_air_wet"));
                                    case KeyType.AIR_ANION:
                                        return context.getString(getStringResIDByName(context, "KeyType_air_anion"));
                                    case KeyType.AIR_POWER_SAVING:
                                        return context.getString(getStringResIDByName(context, "KeyType_air_power_saving"));
                                    case KeyType.AIR_COMFORT:
                                        return context.getString(getStringResIDByName(context, "KeyType_air_comfort"));
                                    case KeyType.AIR_TEMP_DISPLAY:
                                        return context.getString(getStringResIDByName(context, "KeyType_air_temp_display"));
                                    case KeyType.AIR_QUICK_COOL:
                                        return context.getString(getStringResIDByName(context, "KeyType_air_quick_cool"));
                                    case KeyType.AIR_QUICK_HOT:
                                        return context.getString(getStringResIDByName(context, "KeyType_air_quick_hot"));
                                    default:
                                        switch (i) {
                                            case 900:
                                                return context.getString(getStringResIDByName(context, "KeyType_auto"));
                                            case 901:
                                                return context.getString(getStringResIDByName(context, "KeyType_pjt_signal"));
                                            case 902:
                                                return context.getString(getStringResIDByName(context, "KeyType_brightness"));
                                            case KeyType.PC:
                                                return context.getString(getStringResIDByName(context, "KeyType_pc"));
                                            default:
                                                switch (i) {
                                                    case 1010:
                                                        return context.getString(getStringResIDByName(context, "KeyType_home"));
                                                    case 1011:
                                                        return context.getString(getStringResIDByName(context, "KeyType_setting"));
                                                    case 1012:
                                                        return context.getString(getStringResIDByName(context, "KeyType_popmenu"));
                                                    default:
                                                        switch (i) {
                                                            case 1020:
                                                                return context.getString(getStringResIDByName(context, "KeyType_keep_middle_warn"));
                                                            case 1021:
                                                                return context.getString(getStringResIDByName(context, "KeyType_order_bath"));
                                                            case 1022:
                                                                return context.getString(getStringResIDByName(context, "KeyType_confirm"));
                                                            default:
                                                                switch (i) {
                                                                    case 2001:
                                                                        return context.getString(getStringResIDByName(context, "KeyType_Favorites"));
                                                                    case 2002:
                                                                        return context.getString(getStringResIDByName(context, "KeyType_Numbers"));
                                                                    case 2003:
                                                                        return context.getString(getStringResIDByName(context, "KeyType_Custom"));
                                                                    default:
                                                                        return context.getString(getStringResIDByName(context, "KeyType_other"));
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }
    }
}
