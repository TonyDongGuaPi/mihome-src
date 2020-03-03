package com.xiaomi.passport.data;

import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;

public class LoginPreference {
    public String idcZone;
    public PhoneLoginType phoneLoginType;
    public String userRegion;

    public LoginPreference(String str, String str2, PhoneLoginType phoneLoginType2) {
        this.idcZone = str;
        this.userRegion = str2;
        this.phoneLoginType = phoneLoginType2;
    }

    public enum PhoneLoginType {
        ticket(SmartConfigDataProvider.l),
        password("password");
        
        private final String value;

        private PhoneLoginType(String str) {
            this.value = str;
        }
    }
}
