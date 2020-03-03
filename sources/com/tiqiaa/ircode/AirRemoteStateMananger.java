package com.tiqiaa.ircode;

import android.content.Context;
import android.content.SharedPreferences;
import com.imi.fastjson.JSON;
import com.tiqiaa.constant.KeyType;
import com.tiqiaa.icontrol.util.LogUtil;
import com.tiqiaa.remote.entity.AirAidHot;
import com.tiqiaa.remote.entity.AirAnion;
import com.tiqiaa.remote.entity.AirComfort;
import com.tiqiaa.remote.entity.AirFlashAir;
import com.tiqiaa.remote.entity.AirLight;
import com.tiqiaa.remote.entity.AirMode;
import com.tiqiaa.remote.entity.AirMute;
import com.tiqiaa.remote.entity.AirPower;
import com.tiqiaa.remote.entity.AirPowerSaving;
import com.tiqiaa.remote.entity.AirRemoteState;
import com.tiqiaa.remote.entity.AirSleep;
import com.tiqiaa.remote.entity.AirSuper;
import com.tiqiaa.remote.entity.AirTemp;
import com.tiqiaa.remote.entity.AirTempDisplay;
import com.tiqiaa.remote.entity.AirTime;
import com.tiqiaa.remote.entity.AirWet;
import com.tiqiaa.remote.entity.AirWindAmount;
import com.tiqiaa.remote.entity.AirWindDirection;
import com.tiqiaa.remote.entity.AirWindHoz;
import com.tiqiaa.remote.entity.AirWindVer;
import com.tiqiaa.remote.entity.Infrared;
import com.tiqiaa.remote.entity.Key;
import java.util.HashMap;
import java.util.Map;

public class AirRemoteStateMananger {
    private static final String NAME_AIR_REMOTE_STATES_CACHE = "air_remote_sates_cache";
    private static final String TAG = "com.tiqiaa.ircode.AirRemoteStateMananger";
    private static AirRemoteStateMananger mStateMananger;
    Context mContext;
    private Map<String, AirRemoteState> mRemoteStateCache = new HashMap();
    private SharedPreferences mSharedPreferences = this.mContext.getSharedPreferences(NAME_AIR_REMOTE_STATES_CACHE, 0);

    private AirRemoteStateMananger(Context context) {
        this.mContext = context;
    }

    public static synchronized AirRemoteStateMananger getInstance(Context context) {
        AirRemoteStateMananger airRemoteStateMananger;
        synchronized (AirRemoteStateMananger.class) {
            if (mStateMananger == null) {
                mStateMananger = new AirRemoteStateMananger(context);
            }
            airRemoteStateMananger = mStateMananger;
        }
        return airRemoteStateMananger;
    }

    public AirRemoteState getAirRemoteState(String str) {
        if (str == null || str.trim().equals("")) {
            LogUtil.e(TAG, "fetchAirInfrareds getAirRemoteStatus------return null!");
            return null;
        }
        AirRemoteState airRemoteState = this.mRemoteStateCache.get(str);
        if (airRemoteState != null) {
            String str2 = TAG;
            LogUtil.e(str2, "fetchAirInfrareds " + str + ":getAirRemoteStatus from mRemoteStateCache,state:" + JSON.toJSONString(airRemoteState));
            return airRemoteState;
        }
        String string = this.mSharedPreferences.getString(str, (String) null);
        if (string != null) {
            try {
                AirRemoteState airRemoteState2 = (AirRemoteState) JSON.parseObject(string, AirRemoteState.class);
                this.mRemoteStateCache.put(str, airRemoteState2);
                String str3 = TAG;
                LogUtil.e(str3, "fetchAirInfrareds " + str + ":getAirRemoteStatus from mSharedPreferences,state:" + JSON.toJSONString(airRemoteState2));
                return airRemoteState2;
            } catch (Exception e) {
                LogUtil.printException(e);
            }
        }
        AirRemoteState airRemoteState3 = new AirRemoteState(str);
        this.mRemoteStateCache.put(str, airRemoteState3);
        saveAirRemoteState(airRemoteState3);
        String str4 = TAG;
        LogUtil.e(str4, "fetchAirInfrareds " + str + ":getAirRemoteStatus from new ,state:" + JSON.toJSONString(airRemoteState3));
        return airRemoteState3;
    }

    public void saveAirRemoteState(AirRemoteState airRemoteState) {
        if (airRemoteState != null) {
            this.mSharedPreferences.edit().putString(airRemoteState.getRemote_id(), JSON.toJSONString(airRemoteState)).commit();
        }
    }

    public void fitAirState(AirRemoteState airRemoteState, Key key) {
        if (key == null || airRemoteState == null) {
            LogUtil.e(TAG, "fetchAirInfrareds fitAirState key==null||state==null");
        } else if (airRemoteState.getPower() != AirPower.POWER_OFF || key.getType() == 800) {
            airRemoteState.setProtocol(key.getProtocol());
            if (airRemoteState.getCurrent_key() != 800) {
                airRemoteState.setLast_key(airRemoteState.getCurrent_key());
            }
            airRemoteState.setCurrent_key(key.getType());
            LogUtil.e(TAG, "fetchAirInfrareds fitAirState change state keyType:" + key.getType());
            int type = key.getType();
            if (type == 800) {
                if (airRemoteState.getPower() == AirPower.POWER_OFF) {
                    airRemoteState.setPower(AirPower.POWER_ON);
                } else {
                    airRemoteState.setPower(AirPower.POWER_OFF);
                }
                airRemoteState.setTime(AirTime.TIME_OFF);
            } else if (type != 804) {
                switch (type) {
                    case KeyType.TEMP_UP:
                        AirTemp airMaxTemp = getAirMaxTemp(key, airRemoteState);
                        int value = airRemoteState.getTemp().value() + 1;
                        if (value <= airMaxTemp.value()) {
                            airRemoteState.setTemp(AirTemp.getAirTemp(value));
                            break;
                        } else {
                            airRemoteState.setTemp(airMaxTemp);
                            break;
                        }
                    case KeyType.TEMP_DOWN:
                        AirTemp airMinTemp = getAirMinTemp(key, airRemoteState);
                        int value2 = airRemoteState.getTemp().value() - 1;
                        if (value2 >= airMinTemp.value()) {
                            airRemoteState.setTemp(AirTemp.getAirTemp(value2));
                            break;
                        } else {
                            airRemoteState.setTemp(airMinTemp);
                            break;
                        }
                    default:
                        switch (type) {
                            case 832:
                                if (airRemoteState.getMode() != AirMode.AUTO) {
                                    if (airRemoteState.getMode() != AirMode.COOL) {
                                        if (airRemoteState.getMode() != AirMode.DRY) {
                                            if (airRemoteState.getMode() != AirMode.HOT) {
                                                airRemoteState.setMode(AirMode.AUTO);
                                                break;
                                            } else {
                                                airRemoteState.setMode(AirMode.WIND);
                                                break;
                                            }
                                        } else {
                                            airRemoteState.setMode(AirMode.HOT);
                                            break;
                                        }
                                    } else {
                                        airRemoteState.setMode(AirMode.DRY);
                                        break;
                                    }
                                } else {
                                    airRemoteState.setMode(AirMode.COOL);
                                    break;
                                }
                            case KeyType.WIND_AMOUNT:
                                if (airRemoteState.getWind_amount() != AirWindAmount.AUTO) {
                                    if (airRemoteState.getWind_amount() != AirWindAmount.LEVEL_1) {
                                        if (airRemoteState.getWind_amount() != AirWindAmount.LEVEL_2) {
                                            airRemoteState.setWind_amount(AirWindAmount.AUTO);
                                            break;
                                        } else {
                                            airRemoteState.setWind_amount(AirWindAmount.LEVEL_3);
                                            break;
                                        }
                                    } else {
                                        airRemoteState.setWind_amount(AirWindAmount.LEVEL_2);
                                        break;
                                    }
                                } else {
                                    airRemoteState.setWind_amount(AirWindAmount.LEVEL_1);
                                    break;
                                }
                            case KeyType.WIND_HORIZONTAL:
                                if (airRemoteState.getWind_hoz() != AirWindHoz.HOZ_OFF) {
                                    airRemoteState.setWind_hoz(AirWindHoz.HOZ_OFF);
                                    break;
                                } else {
                                    airRemoteState.setWind_hoz(AirWindHoz.HOZ_ON);
                                    break;
                                }
                            case KeyType.WIND_VERTICAL:
                                if (airRemoteState.getWind_ver() != AirWindVer.VER_OFF) {
                                    airRemoteState.setWind_ver(AirWindVer.VER_OFF);
                                    break;
                                } else {
                                    airRemoteState.setWind_ver(AirWindVer.VER_ON);
                                    break;
                                }
                            default:
                                switch (type) {
                                    case KeyType.AIR_WIND_DIRECT:
                                        if (airRemoteState.getWind_direction() != AirWindDirection.AUTO) {
                                            if (airRemoteState.getWind_direction() != AirWindDirection.UP) {
                                                if (airRemoteState.getWind_direction() != AirWindDirection.MIDDLE) {
                                                    airRemoteState.setWind_direction(AirWindDirection.AUTO);
                                                    break;
                                                } else {
                                                    airRemoteState.setWind_direction(AirWindDirection.DOWN);
                                                    break;
                                                }
                                            } else {
                                                airRemoteState.setWind_direction(AirWindDirection.MIDDLE);
                                                break;
                                            }
                                        } else {
                                            airRemoteState.setWind_direction(AirWindDirection.UP);
                                            break;
                                        }
                                    case KeyType.AIR_LIGHT:
                                        if (airRemoteState.getLight() != AirLight.LIGHT_OFF) {
                                            airRemoteState.setLight(AirLight.LIGHT_OFF);
                                            break;
                                        } else {
                                            airRemoteState.setLight(AirLight.LIGHT_ON);
                                            break;
                                        }
                                    case KeyType.AIR_SUPER:
                                        if (airRemoteState.getSuper_mode() != AirSuper.SUPER_OFF) {
                                            airRemoteState.setSuper_mode(AirSuper.SUPER_OFF);
                                            break;
                                        } else {
                                            airRemoteState.setSuper_mode(AirSuper.SUPER_ON);
                                            break;
                                        }
                                    case KeyType.AIR_SLEEP:
                                        if (airRemoteState.getSleep() != AirSleep.SLEEP_OFF) {
                                            airRemoteState.setSleep(AirSleep.SLEEP_OFF);
                                            break;
                                        } else {
                                            airRemoteState.setSleep(AirSleep.SLEEP_ON);
                                            break;
                                        }
                                    case KeyType.AIR_FLASH_AIR:
                                        if (airRemoteState.getFlash_air() != AirFlashAir.FLASH_OFF) {
                                            airRemoteState.setFlash_air(AirFlashAir.FLASH_OFF);
                                            break;
                                        } else {
                                            airRemoteState.setFlash_air(AirFlashAir.FLASH_ON);
                                            break;
                                        }
                                    case KeyType.AIR_AID_HOT:
                                        if (airRemoteState.getHot() != AirAidHot.AIDHOT_OFF) {
                                            airRemoteState.setHot(AirAidHot.AIDHOT_OFF);
                                            break;
                                        } else {
                                            airRemoteState.setHot(AirAidHot.AIDHOT_ON);
                                            break;
                                        }
                                    case KeyType.AIR_TIME:
                                        airRemoteState.setTime(AirTime.TIME_ON);
                                        break;
                                    case KeyType.AIR_WET:
                                        if (airRemoteState.getWet() != AirWet.WET_OFF) {
                                            airRemoteState.setWet(AirWet.WET_OFF);
                                            break;
                                        } else {
                                            airRemoteState.setWet(AirWet.WET_ON);
                                            break;
                                        }
                                    case KeyType.AIR_ANION:
                                        if (airRemoteState.getAnion() != AirAnion.ANION_OFF) {
                                            airRemoteState.setAnion(AirAnion.ANION_OFF);
                                            break;
                                        } else {
                                            airRemoteState.setAnion(AirAnion.ANION_ON);
                                            break;
                                        }
                                    case KeyType.AIR_POWER_SAVING:
                                        if (airRemoteState.getPower_saving() != AirPowerSaving.POWER_SAVING_OFF) {
                                            airRemoteState.setPower_saving(AirPowerSaving.POWER_SAVING_OFF);
                                            break;
                                        } else {
                                            airRemoteState.setPower_saving(AirPowerSaving.POWER_SAVING_ON);
                                            break;
                                        }
                                    case KeyType.AIR_COMFORT:
                                        if (airRemoteState.getComfort() != AirComfort.COMFORT_OFF) {
                                            airRemoteState.setComfort(AirComfort.COMFORT_OFF);
                                            break;
                                        } else {
                                            airRemoteState.setComfort(AirComfort.COMFORT_ON);
                                            break;
                                        }
                                    case KeyType.AIR_TEMP_DISPLAY:
                                        if (airRemoteState.getTemp_display() != AirTempDisplay.DISPLAY_INDOOR_TEMP) {
                                            if (airRemoteState.getTemp_display() != AirTempDisplay.DISPLAY_OUTDOOR_TEMP) {
                                                if (airRemoteState.getTemp_display() != AirTempDisplay.DISPLAY_TARGET_TEMP) {
                                                    if (airRemoteState.getTemp_display() != AirTempDisplay.DISPLAY_NONE) {
                                                        airRemoteState.setTemp_display(AirTempDisplay.DISPLAY_NONE);
                                                        break;
                                                    } else {
                                                        airRemoteState.setTemp_display(AirTempDisplay.DISPLAY_INDOOR_TEMP);
                                                        break;
                                                    }
                                                } else {
                                                    airRemoteState.setTemp_display(AirTempDisplay.DISPLAY_NONE);
                                                    break;
                                                }
                                            } else {
                                                airRemoteState.setTemp_display(AirTempDisplay.DISPLAY_TARGET_TEMP);
                                                break;
                                            }
                                        } else {
                                            airRemoteState.setTemp_display(AirTempDisplay.DISPLAY_OUTDOOR_TEMP);
                                            break;
                                        }
                                    case KeyType.AIR_QUICK_COOL:
                                        quick_cool(airRemoteState);
                                        break;
                                    case KeyType.AIR_QUICK_HOT:
                                        quick_hot(airRemoteState);
                                        break;
                                }
                        }
                }
            } else if (airRemoteState.getMute() == AirMute.MUTE_OFF) {
                airRemoteState.setMute(AirMute.MUTE_ON);
            } else {
                airRemoteState.setMute(AirMute.MUTE_OFF);
            }
            if (key.getType() != 876) {
                airRemoteState.setTime(AirTime.TIME_OFF);
                airRemoteState.setTime_limit(0);
            }
            if (airRemoteState.getTime_limit() > 0) {
                airRemoteState.setTime_limit(0);
            }
            airRemoteState.setCaculate_number(airRemoteState.getCaculate_number() + 1);
        } else {
            LogUtil.e(TAG, "fetchAirInfrareds fitAirState other key pressed while POWER_OFF!");
        }
    }

    private void quick_cool(AirRemoteState airRemoteState) {
        airRemoteState.setMode(AirMode.COOL);
        airRemoteState.setTemp(AirTemp.T26);
        airRemoteState.setWind_amount(AirWindAmount.AUTO);
        airRemoteState.setWind_direction(AirWindDirection.MIDDLE);
        airRemoteState.setWind_hoz(AirWindHoz.HOZ_OFF);
        airRemoteState.setWind_ver(AirWindVer.VER_OFF);
        airRemoteState.setSuper_mode(AirSuper.SUPER_OFF);
        airRemoteState.setSleep(AirSleep.SLEEP_OFF);
        airRemoteState.setHot(AirAidHot.AIDHOT_OFF);
        airRemoteState.setTime(AirTime.TIME_OFF);
        airRemoteState.setTemp_display(AirTempDisplay.DISPLAY_INDOOR_TEMP);
        airRemoteState.setPower_saving(AirPowerSaving.POWER_SAVING_OFF);
        airRemoteState.setAnion(AirAnion.ANION_OFF);
        airRemoteState.setComfort(AirComfort.COMFORT_OFF);
        airRemoteState.setFlash_air(AirFlashAir.FLASH_OFF);
        airRemoteState.setLight(AirLight.LIGHT_ON);
        airRemoteState.setWet(AirWet.WET_OFF);
        airRemoteState.setMute(AirMute.MUTE_OFF);
        airRemoteState.setCaculate_number(airRemoteState.getCaculate_number() + 1);
    }

    private void quick_hot(AirRemoteState airRemoteState) {
        airRemoteState.setMode(AirMode.HOT);
        airRemoteState.setTemp(AirTemp.T22);
        airRemoteState.setWind_amount(AirWindAmount.AUTO);
        airRemoteState.setWind_direction(AirWindDirection.MIDDLE);
        airRemoteState.setWind_hoz(AirWindHoz.HOZ_OFF);
        airRemoteState.setWind_ver(AirWindVer.VER_OFF);
        airRemoteState.setSuper_mode(AirSuper.SUPER_OFF);
        airRemoteState.setSleep(AirSleep.SLEEP_OFF);
        airRemoteState.setHot(AirAidHot.AIDHOT_OFF);
        airRemoteState.setTime(AirTime.TIME_OFF);
        airRemoteState.setTemp_display(AirTempDisplay.DISPLAY_INDOOR_TEMP);
        airRemoteState.setPower_saving(AirPowerSaving.POWER_SAVING_OFF);
        airRemoteState.setAnion(AirAnion.ANION_OFF);
        airRemoteState.setComfort(AirComfort.COMFORT_OFF);
        airRemoteState.setFlash_air(AirFlashAir.FLASH_OFF);
        airRemoteState.setLight(AirLight.LIGHT_ON);
        airRemoteState.setWet(AirWet.WET_OFF);
        airRemoteState.setMute(AirMute.MUTE_OFF);
        airRemoteState.setCaculate_number(airRemoteState.getCaculate_number() + 1);
        airRemoteState.setCaculate_number(airRemoteState.getCaculate_number() + 1);
    }

    public AirTemp getAirMaxTemp(Key key, AirRemoteState airRemoteState) {
        int intValue;
        if (key == null || key.getInfrareds() == null || key.getInfrareds().size() == 0 || key.getProtocol() > 0) {
            return AirTemp.T30;
        }
        if (key.getType() != 812 && key.getType() != 811) {
            return AirTemp.T30;
        }
        int i = 0;
        for (Infrared next : key.getInfrareds()) {
            if (next != null) {
                try {
                    if (next.getFunc() == airRemoteState.getMode().value() && next.getMark() > 0 && (intValue = Integer.valueOf(next.getMark()).intValue()) > i) {
                        i = intValue;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return AirTemp.getAirTemp(i);
    }

    public AirTemp getAirMinTemp(Key key, AirRemoteState airRemoteState) {
        int mark;
        if (key == null || key.getInfrareds() == null || key.getInfrareds().size() == 0 || airRemoteState == null || key.getProtocol() > 0) {
            LogUtil.e(TAG, "getAirMinTemp..........获取温度+-按键的最低温度.........key==null||key.getInfrareds()==null||key.getInfrareds().size()==0||state==null||key.getProtocol()>0");
            return AirTemp.T16;
        } else if (key.getType() == 812 || key.getType() == 811) {
            int i = 31;
            for (Infrared next : key.getInfrareds()) {
                if (next != null) {
                    try {
                        if (next.getFunc() == airRemoteState.getMode().value() && next.getMark() > 0 && (mark = next.getMark()) < i) {
                            i = mark;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            String str = TAG;
            LogUtil.w(str, "getAirMinTemp..........获取温度+-按键的最低温度........min_tmp = " + i);
            return AirTemp.getAirTemp(i);
        } else {
            LogUtil.e(TAG, "getAirMinTemp..........获取温度+-按键的最低温度........不是TEMP_DOWN或TEMP_UP");
            return AirTemp.T16;
        }
    }

    public void flush() {
        if (this.mRemoteStateCache != null && this.mRemoteStateCache.size() != 0) {
            for (AirRemoteState saveAirRemoteState : this.mRemoteStateCache.values()) {
                saveAirRemoteState(saveAirRemoteState);
            }
            this.mRemoteStateCache.clear();
            this.mRemoteStateCache = null;
            this.mSharedPreferences = null;
            mStateMananger = null;
        }
    }

    public void clearAirRemoteState(String str) {
        if (this.mRemoteStateCache != null) {
            this.mRemoteStateCache.remove(str);
        }
        this.mSharedPreferences.edit().remove(str).commit();
    }
}
