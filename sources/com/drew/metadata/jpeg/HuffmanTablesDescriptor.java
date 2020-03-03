package com.drew.metadata.jpeg;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;

public class HuffmanTablesDescriptor extends TagDescriptor<HuffmanTablesDirectory> {
    public HuffmanTablesDescriptor(@NotNull HuffmanTablesDirectory huffmanTablesDirectory) {
        super(huffmanTablesDirectory);
    }

    @Nullable
    public String a(int i) {
        if (i != 1) {
            return super.a(i);
        }
        return a();
    }

    @Nullable
    public String a() {
        Integer c = ((HuffmanTablesDirectory) this.f5211a).c(1);
        if (c == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(c);
        sb.append(c.intValue() == 1 ? " Huffman table" : " Huffman tables");
        return sb.toString();
    }
}
