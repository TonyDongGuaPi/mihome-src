package org.cybergarage.http;

public class Parameter {
    private String name = new String();
    private String value = new String();

    public Parameter() {
    }

    public Parameter(String str, String str2) {
        setName(str);
        setValue(str2);
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }
}
