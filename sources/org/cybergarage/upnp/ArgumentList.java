package org.cybergarage.upnp;

import java.util.Vector;

public class ArgumentList extends Vector {
    public static final String ELEM_NAME = "argumentList";

    public Argument getArgument(int i) {
        return (Argument) get(i);
    }

    public Argument getArgument(String str) {
        int size = size();
        for (int i = 0; i < size; i++) {
            Argument argument = getArgument(i);
            String name = argument.getName();
            if (name != null && name.equals(str)) {
                return argument;
            }
        }
        return null;
    }

    public void set(ArgumentList argumentList) {
        int size = argumentList.size();
        for (int i = 0; i < size; i++) {
            Argument argument = argumentList.getArgument(i);
            Argument argument2 = getArgument(argument.getName());
            if (argument2 != null) {
                argument2.setValue(argument.getValue());
            }
        }
    }

    public void setReqArgs(ArgumentList argumentList) {
        int size = size();
        for (int i = 0; i < size; i++) {
            Argument argument = getArgument(i);
            if (argument.isInDirection()) {
                String name = argument.getName();
                Argument argument2 = argumentList.getArgument(name);
                if (argument2 != null) {
                    argument.setValue(argument2.getValue());
                } else {
                    throw new IllegalArgumentException("Argument \"" + name + "\" missing.");
                }
            }
        }
    }

    public void setResArgs(ArgumentList argumentList) {
        int size = size();
        for (int i = 0; i < size; i++) {
            Argument argument = getArgument(i);
            if (argument.isOutDirection()) {
                String name = argument.getName();
                Argument argument2 = argumentList.getArgument(name);
                if (argument2 != null) {
                    argument.setValue(argument2.getValue());
                } else {
                    throw new IllegalArgumentException("Argument \"" + name + "\" missing.");
                }
            }
        }
    }
}
