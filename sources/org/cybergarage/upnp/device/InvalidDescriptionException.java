package org.cybergarage.upnp.device;

import com.taobao.weex.el.parse.Operators;
import java.io.File;

public class InvalidDescriptionException extends Exception {
    public InvalidDescriptionException() {
    }

    public InvalidDescriptionException(String str) {
        super(str);
    }

    public InvalidDescriptionException(String str, File file) {
        super(str + " (" + file.toString() + Operators.BRACKET_END_STR);
    }

    public InvalidDescriptionException(Exception exc) {
        super(exc.getMessage());
    }
}
