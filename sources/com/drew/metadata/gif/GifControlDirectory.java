package com.drew.metadata.gif;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import java.util.HashMap;

public class GifControlDirectory extends Directory {
    public static final int e = 1;
    public static final int f = 2;
    public static final int g = 3;
    public static final int h = 4;
    public static final int i = 5;
    @NotNull
    protected static final HashMap<Integer, String> j = new HashMap<>();

    @NotNull
    public String a() {
        return "GIF Control";
    }

    static {
        j.put(1, "Delay");
        j.put(2, "Disposal Method");
        j.put(3, "User Input Flag");
        j.put(4, "Transparent Color Flag");
        j.put(5, "Transparent Color Index");
    }

    public GifControlDirectory() {
        a((TagDescriptor) new GifControlDescriptor(this));
    }

    @NotNull
    public DisposalMethod j() {
        return (DisposalMethod) u(2);
    }

    public boolean k() {
        Boolean o = o(4);
        return o != null && o.booleanValue();
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HashMap<Integer, String> b() {
        return j;
    }

    public enum DisposalMethod {
        NOT_SPECIFIED,
        DO_NOT_DISPOSE,
        RESTORE_TO_BACKGROUND_COLOR,
        RESTORE_TO_PREVIOUS,
        TO_BE_DEFINED,
        INVALID;

        public static DisposalMethod typeOf(int i) {
            switch (i) {
                case 0:
                    return NOT_SPECIFIED;
                case 1:
                    return DO_NOT_DISPOSE;
                case 2:
                    return RESTORE_TO_BACKGROUND_COLOR;
                case 3:
                    return RESTORE_TO_PREVIOUS;
                case 4:
                case 5:
                case 6:
                case 7:
                    return TO_BE_DEFINED;
                default:
                    return INVALID;
            }
        }

        public String toString() {
            switch (this) {
                case DO_NOT_DISPOSE:
                    return "Don't Dispose";
                case INVALID:
                    return "Invalid value";
                case NOT_SPECIFIED:
                    return "Not Specified";
                case RESTORE_TO_BACKGROUND_COLOR:
                    return "Restore to Background Color";
                case RESTORE_TO_PREVIOUS:
                    return "Restore to Previous";
                case TO_BE_DEFINED:
                    return "To Be Defined";
                default:
                    return super.toString();
            }
        }
    }
}
