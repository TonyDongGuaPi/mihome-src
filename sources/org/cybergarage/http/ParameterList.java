package org.cybergarage.http;

import java.util.Vector;

public class ParameterList extends Vector {
    public Parameter at(int i) {
        return (Parameter) get(i);
    }

    public Parameter getParameter(int i) {
        return (Parameter) get(i);
    }

    public Parameter getParameter(String str) {
        if (str == null) {
            return null;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            Parameter at = at(i);
            if (str.compareTo(at.getName()) == 0) {
                return at;
            }
        }
        return null;
    }

    public String getValue(String str) {
        Parameter parameter = getParameter(str);
        if (parameter == null) {
            return "";
        }
        return parameter.getValue();
    }
}
