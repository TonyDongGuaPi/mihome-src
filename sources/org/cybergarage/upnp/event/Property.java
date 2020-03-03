package org.cybergarage.upnp.event;

public class Property {
    private String name = "";
    private String value = "";

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        if (str == null) {
            str = "";
        }
        this.name = str;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        if (str == null) {
            str = "";
        }
        this.value = str;
    }
}
