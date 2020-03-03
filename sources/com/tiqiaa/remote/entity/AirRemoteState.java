package com.tiqiaa.remote.entity;

import com.imi.fastjson.annotation.JSONField;
import com.imi.fastjson.annotation.JSONType;
import com.tiqiaa.common.IJsonable;

@JSONType(ignores = {"caculate_number", "time_limit", "time"}, orders = {"remote_id", "protocol", "current_key", "last_key", "power", "mode", "temp", "wind_amount", "wind_direction", "wind_hoz", "wind_ver", "super_mode", "sleep", "hot", "time", "temp_display", "power_saving", "anion", "comfort", "flash_air", "light", "wet", "mute", "time_limit", "caculate_number"})
public class AirRemoteState implements IJsonable {
    public static final String AIR_STATUS = "air_status";
    @JSONField(name = "anion")
    AirAnion anion;
    @JSONField(name = "caculate_number")
    int caculate_number;
    @JSONField(name = "comfort")
    AirComfort comfort;
    @JSONField(name = "current_key")
    int current_key;
    @JSONField(name = "flash_air")
    AirFlashAir flash_air;
    @JSONField(name = "hot")
    AirAidHot hot;
    @JSONField(name = "last_key")
    int last_key;
    @JSONField(name = "light")
    AirLight light;
    @JSONField(name = "mode")
    AirMode mode;
    @JSONField(name = "mute")
    AirMute mute;
    @JSONField(name = "power")
    AirPower power;
    @JSONField(name = "power_saving")
    AirPowerSaving power_saving;
    @JSONField(name = "protocol")
    int protocol;
    @JSONField(name = "remote_id")
    String remote_id;
    @JSONField(name = "sleep")
    AirSleep sleep;
    @JSONField(name = "super_mode")
    AirSuper super_mode;
    @JSONField(name = "temp")
    AirTemp temp;
    @JSONField(name = "temp_display")
    AirTempDisplay temp_display;
    @JSONField(name = "time")
    AirTime time;
    @JSONField(name = "time_limit")
    int time_limit;
    @JSONField(name = "wet")
    AirWet wet;
    @JSONField(name = "wind_amount")
    AirWindAmount wind_amount;
    @JSONField(name = "wind_direction")
    AirWindDirection wind_direction;
    @JSONField(name = "wind_hoz")
    AirWindHoz wind_hoz;
    @JSONField(name = "wind_ver")
    AirWindVer wind_ver;

    AirRemoteState() {
    }

    public AirRemoteState(String str) {
        if (str != null) {
            this.remote_id = str;
            this.power = AirPower.POWER_OFF;
            this.mode = AirMode.AUTO;
            this.temp = AirTemp.T26;
            this.wind_amount = AirWindAmount.AUTO;
            this.wind_direction = AirWindDirection.AUTO;
            this.wind_hoz = AirWindHoz.HOZ_OFF;
            this.wind_ver = AirWindVer.VER_OFF;
            this.super_mode = AirSuper.SUPER_OFF;
            this.sleep = AirSleep.SLEEP_OFF;
            this.hot = AirAidHot.AIDHOT_OFF;
            this.time = AirTime.TIME_OFF;
            this.temp_display = AirTempDisplay.DISPLAY_NONE;
            this.power_saving = AirPowerSaving.POWER_SAVING_OFF;
            this.anion = AirAnion.ANION_OFF;
            this.comfort = AirComfort.COMFORT_OFF;
            this.flash_air = AirFlashAir.FLASH_OFF;
            this.light = AirLight.LIGHT_ON;
            this.wet = AirWet.WET_OFF;
            this.mute = AirMute.MUTE_OFF;
            this.caculate_number = 0;
            return;
        }
        throw new NullPointerException("the string param 'remote_id' can not be null");
    }

    public int getProtocol() {
        return this.protocol;
    }

    public void setProtocol(int i) {
        this.protocol = i;
    }

    public AirPower getPower() {
        return this.power;
    }

    public void setPower(AirPower airPower) {
        this.power = airPower;
    }

    public AirMute getMute() {
        return this.mute;
    }

    public void setMute(AirMute airMute) {
        this.mute = airMute;
    }

    public int getCaculate_number() {
        return this.caculate_number;
    }

    public void setCaculate_number(int i) {
        this.caculate_number = i;
    }

    public AirMode getMode() {
        return this.mode;
    }

    public void setMode(AirMode airMode) {
        this.mode = airMode;
    }

    public AirTemp getTemp() {
        return this.temp;
    }

    public void setTemp(AirTemp airTemp) {
        this.temp = airTemp;
    }

    public String getRemote_id() {
        return this.remote_id;
    }

    public void setRemote_id(String str) {
        this.remote_id = str;
    }

    public AirTempDisplay getTemp_display() {
        return this.temp_display;
    }

    public void setTemp_display(AirTempDisplay airTempDisplay) {
        this.temp_display = airTempDisplay;
    }

    public AirWindAmount getWind_amount() {
        return this.wind_amount;
    }

    public void setWind_amount(AirWindAmount airWindAmount) {
        this.wind_amount = airWindAmount;
    }

    public AirWindDirection getWind_direction() {
        return this.wind_direction;
    }

    public void setWind_direction(AirWindDirection airWindDirection) {
        this.wind_direction = airWindDirection;
    }

    public AirWindHoz getWind_hoz() {
        return this.wind_hoz;
    }

    public void setWind_hoz(AirWindHoz airWindHoz) {
        this.wind_hoz = airWindHoz;
    }

    public AirWindVer getWind_ver() {
        return this.wind_ver;
    }

    public void setWind_ver(AirWindVer airWindVer) {
        this.wind_ver = airWindVer;
    }

    public AirTime getTime() {
        if (this.time == null) {
            this.time = AirTime.TIME_OFF;
        }
        return this.time;
    }

    public void setTime(AirTime airTime) {
        this.time = airTime;
    }

    public AirSuper getSuper_mode() {
        return this.super_mode;
    }

    public void setSuper_mode(AirSuper airSuper) {
        this.super_mode = airSuper;
    }

    public AirSleep getSleep() {
        return this.sleep;
    }

    public void setSleep(AirSleep airSleep) {
        this.sleep = airSleep;
    }

    public AirAidHot getHot() {
        return this.hot;
    }

    public void setHot(AirAidHot airAidHot) {
        this.hot = airAidHot;
    }

    public AirPowerSaving getPower_saving() {
        return this.power_saving;
    }

    public void setPower_saving(AirPowerSaving airPowerSaving) {
        this.power_saving = airPowerSaving;
    }

    public AirAnion getAnion() {
        return this.anion;
    }

    public void setAnion(AirAnion airAnion) {
        this.anion = airAnion;
    }

    public AirComfort getComfort() {
        return this.comfort;
    }

    public void setComfort(AirComfort airComfort) {
        this.comfort = airComfort;
    }

    public AirFlashAir getFlash_air() {
        return this.flash_air;
    }

    public void setFlash_air(AirFlashAir airFlashAir) {
        this.flash_air = airFlashAir;
    }

    public AirLight getLight() {
        return this.light;
    }

    public void setLight(AirLight airLight) {
        this.light = airLight;
    }

    public AirWet getWet() {
        return this.wet;
    }

    public void setWet(AirWet airWet) {
        this.wet = airWet;
    }

    public int getCurrent_key() {
        return this.current_key;
    }

    public void setCurrent_key(int i) {
        this.current_key = i;
    }

    public int getLast_key() {
        return this.last_key;
    }

    public void setLast_key(int i) {
        this.last_key = i;
    }

    public int getTime_limit() {
        return this.time_limit;
    }

    public void setTime_limit(int i) {
        this.time_limit = i;
    }
}
