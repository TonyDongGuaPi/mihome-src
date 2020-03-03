package com.drew.imaging.png;

import cn.com.fmsh.tsm.business.constants.Constants;
import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PngChunkReader {

    /* renamed from: a  reason: collision with root package name */
    private static final byte[] f5185a = {Constants.TagName.COMPANY_CODE, Constants.TagName.ORDER_BRIEF_INFO_LIST, 78, Constants.TagName.ACTIVITY_INFO, 13, 10, 26, 10};

    public Iterable<PngChunk> a(@NotNull SequentialReader sequentialReader, @Nullable Set<PngChunkType> set) throws PngProcessingException, IOException {
        sequentialReader.a(true);
        if (Arrays.equals(f5185a, sequentialReader.a(f5185a.length))) {
            ArrayList arrayList = new ArrayList();
            HashSet hashSet = new HashSet();
            boolean z = false;
            boolean z2 = false;
            while (!z) {
                int j = sequentialReader.j();
                if (j >= 0) {
                    PngChunkType pngChunkType = new PngChunkType(sequentialReader.a(4));
                    boolean z3 = set == null || set.contains(pngChunkType);
                    byte[] a2 = sequentialReader.a(j);
                    sequentialReader.a(4);
                    if (!z3 || !hashSet.contains(pngChunkType) || pngChunkType.e()) {
                        if (pngChunkType.equals(PngChunkType.f5186a)) {
                            z2 = true;
                        } else if (!z2) {
                            throw new PngProcessingException(String.format("First chunk should be '%s', but '%s' was observed", new Object[]{PngChunkType.f5186a, pngChunkType}));
                        }
                        if (pngChunkType.equals(PngChunkType.d)) {
                            z = true;
                        }
                        if (z3) {
                            arrayList.add(new PngChunk(pngChunkType, a2));
                        }
                        hashSet.add(pngChunkType);
                    } else {
                        throw new PngProcessingException(String.format("Observed multiple instances of PNG chunk '%s', for which multiples are not allowed", new Object[]{pngChunkType}));
                    }
                } else {
                    throw new PngProcessingException("PNG chunk length exceeds maximum");
                }
            }
            return arrayList;
        }
        throw new PngProcessingException("PNG signature mismatch");
    }
}
