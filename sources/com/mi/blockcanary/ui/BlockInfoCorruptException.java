package com.mi.blockcanary.ui;

import java.util.Locale;

public class BlockInfoCorruptException extends Exception {
    public BlockInfoCorruptException(BlockInfoEx blockInfoEx) {
        this(String.format(Locale.US, "BlockInfo (%s) is corrupt.", new Object[]{blockInfoEx.D.getName()}));
    }

    public BlockInfoCorruptException(String str) {
        super(str);
    }
}
