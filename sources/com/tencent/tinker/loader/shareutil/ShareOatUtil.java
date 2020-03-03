package com.tencent.tinker.loader.shareutil;

public final class ShareOatUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9257a = "Tinker.OatUtil";

    private enum InstructionSet {
        kNone,
        kArm,
        kArm64,
        kThumb2,
        kX86,
        kX86_64,
        kMips,
        kMips64
    }

    private ShareOatUtil() {
        throw new UnsupportedOperationException();
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x0115 A[SYNTHETIC, Splitter:B:55:0x0115] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.io.File r10) throws java.lang.Throwable {
        /*
            r0 = 0
            com.tencent.tinker.loader.shareutil.ShareElfFile r1 = new com.tencent.tinker.loader.shareutil.ShareElfFile     // Catch:{ all -> 0x0111 }
            r1.<init>(r10)     // Catch:{ all -> 0x0111 }
            java.lang.String r10 = ".rodata"
            com.tencent.tinker.loader.shareutil.ShareElfFile$SectionHeader r10 = r1.a((java.lang.String) r10)     // Catch:{ all -> 0x010f }
            if (r10 == 0) goto L_0x0107
            java.nio.channels.FileChannel r0 = r1.a()     // Catch:{ all -> 0x010f }
            long r2 = r10.F     // Catch:{ all -> 0x010f }
            r0.position(r2)     // Catch:{ all -> 0x010f }
            r2 = 8
            byte[] r2 = new byte[r2]     // Catch:{ all -> 0x010f }
            java.nio.ByteBuffer r3 = java.nio.ByteBuffer.wrap(r2)     // Catch:{ all -> 0x010f }
            java.lang.String r4 = "Failed to read oat magic and version."
            com.tencent.tinker.loader.shareutil.ShareElfFile.a(r0, r3, r4)     // Catch:{ all -> 0x010f }
            r3 = 0
            byte r4 = r2[r3]     // Catch:{ all -> 0x010f }
            r5 = 111(0x6f, float:1.56E-43)
            r6 = 4
            r7 = 2
            r8 = 1
            r9 = 3
            if (r4 != r5) goto L_0x00d9
            byte r4 = r2[r8]     // Catch:{ all -> 0x010f }
            r5 = 97
            if (r4 != r5) goto L_0x00d9
            byte r4 = r2[r7]     // Catch:{ all -> 0x010f }
            r5 = 116(0x74, float:1.63E-43)
            if (r4 != r5) goto L_0x00d9
            byte r4 = r2[r9]     // Catch:{ all -> 0x010f }
            r5 = 10
            if (r4 != r5) goto L_0x00d9
            java.lang.String r3 = new java.lang.String     // Catch:{ all -> 0x010f }
            java.lang.String r4 = "ASCII"
            java.nio.charset.Charset r4 = java.nio.charset.Charset.forName(r4)     // Catch:{ all -> 0x010f }
            r3.<init>(r2, r6, r9, r4)     // Catch:{ all -> 0x010f }
            java.lang.Integer.parseInt(r3)     // Catch:{ NumberFormatException -> 0x00c2 }
            r2 = 128(0x80, float:1.794E-43)
            java.nio.ByteBuffer r2 = java.nio.ByteBuffer.allocate(r2)     // Catch:{ all -> 0x010f }
            java.nio.ByteOrder r3 = r1.c()     // Catch:{ all -> 0x010f }
            r2.order(r3)     // Catch:{ all -> 0x010f }
            long r3 = r10.F     // Catch:{ all -> 0x010f }
            r7 = 12
            long r3 = r3 + r7
            r0.position(r3)     // Catch:{ all -> 0x010f }
            r2.limit(r6)     // Catch:{ all -> 0x010f }
            java.lang.String r10 = "Failed to read isa num."
            com.tencent.tinker.loader.shareutil.ShareElfFile.a(r0, r2, r10)     // Catch:{ all -> 0x010f }
            int r10 = r2.getInt()     // Catch:{ all -> 0x010f }
            if (r10 < 0) goto L_0x00ab
            com.tencent.tinker.loader.shareutil.ShareOatUtil$InstructionSet[] r0 = com.tencent.tinker.loader.shareutil.ShareOatUtil.InstructionSet.values()     // Catch:{ all -> 0x010f }
            int r0 = r0.length     // Catch:{ all -> 0x010f }
            if (r10 >= r0) goto L_0x00ab
            int[] r0 = com.tencent.tinker.loader.shareutil.ShareOatUtil.AnonymousClass1.f9258a     // Catch:{ all -> 0x010f }
            com.tencent.tinker.loader.shareutil.ShareOatUtil$InstructionSet[] r2 = com.tencent.tinker.loader.shareutil.ShareOatUtil.InstructionSet.values()     // Catch:{ all -> 0x010f }
            r10 = r2[r10]     // Catch:{ all -> 0x010f }
            int r10 = r10.ordinal()     // Catch:{ all -> 0x010f }
            r10 = r0[r10]     // Catch:{ all -> 0x010f }
            switch(r10) {
                case 1: goto L_0x009f;
                case 2: goto L_0x009f;
                case 3: goto L_0x009c;
                case 4: goto L_0x0099;
                case 5: goto L_0x0096;
                case 6: goto L_0x0093;
                case 7: goto L_0x0090;
                case 8: goto L_0x008d;
                default: goto L_0x008a;
            }     // Catch:{ all -> 0x010f }
        L_0x008a:
            java.io.IOException r10 = new java.io.IOException     // Catch:{ all -> 0x010f }
            goto L_0x00a5
        L_0x008d:
            java.lang.String r10 = "none"
            goto L_0x00a1
        L_0x0090:
            java.lang.String r10 = "mips64"
            goto L_0x00a1
        L_0x0093:
            java.lang.String r10 = "mips"
            goto L_0x00a1
        L_0x0096:
            java.lang.String r10 = "x86_64"
            goto L_0x00a1
        L_0x0099:
            java.lang.String r10 = "x86"
            goto L_0x00a1
        L_0x009c:
            java.lang.String r10 = "arm64"
            goto L_0x00a1
        L_0x009f:
            java.lang.String r10 = "arm"
        L_0x00a1:
            r1.close()     // Catch:{ Exception -> 0x00a4 }
        L_0x00a4:
            return r10
        L_0x00a5:
            java.lang.String r0 = "Should not reach here."
            r10.<init>(r0)     // Catch:{ all -> 0x010f }
            throw r10     // Catch:{ all -> 0x010f }
        L_0x00ab:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x010f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x010f }
            r2.<init>()     // Catch:{ all -> 0x010f }
            java.lang.String r3 = "Bad isa num: "
            r2.append(r3)     // Catch:{ all -> 0x010f }
            r2.append(r10)     // Catch:{ all -> 0x010f }
            java.lang.String r10 = r2.toString()     // Catch:{ all -> 0x010f }
            r0.<init>(r10)     // Catch:{ all -> 0x010f }
            throw r0     // Catch:{ all -> 0x010f }
        L_0x00c2:
            java.io.IOException r10 = new java.io.IOException     // Catch:{ all -> 0x010f }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x010f }
            r0.<init>()     // Catch:{ all -> 0x010f }
            java.lang.String r2 = "Bad oat version: "
            r0.append(r2)     // Catch:{ all -> 0x010f }
            r0.append(r3)     // Catch:{ all -> 0x010f }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x010f }
            r10.<init>(r0)     // Catch:{ all -> 0x010f }
            throw r10     // Catch:{ all -> 0x010f }
        L_0x00d9:
            java.io.IOException r10 = new java.io.IOException     // Catch:{ all -> 0x010f }
            java.lang.String r0 = "Bad oat magic: %x %x %x %x"
            java.lang.Object[] r4 = new java.lang.Object[r6]     // Catch:{ all -> 0x010f }
            byte r5 = r2[r3]     // Catch:{ all -> 0x010f }
            java.lang.Byte r5 = java.lang.Byte.valueOf(r5)     // Catch:{ all -> 0x010f }
            r4[r3] = r5     // Catch:{ all -> 0x010f }
            byte r3 = r2[r8]     // Catch:{ all -> 0x010f }
            java.lang.Byte r3 = java.lang.Byte.valueOf(r3)     // Catch:{ all -> 0x010f }
            r4[r8] = r3     // Catch:{ all -> 0x010f }
            byte r3 = r2[r7]     // Catch:{ all -> 0x010f }
            java.lang.Byte r3 = java.lang.Byte.valueOf(r3)     // Catch:{ all -> 0x010f }
            r4[r7] = r3     // Catch:{ all -> 0x010f }
            byte r2 = r2[r9]     // Catch:{ all -> 0x010f }
            java.lang.Byte r2 = java.lang.Byte.valueOf(r2)     // Catch:{ all -> 0x010f }
            r4[r9] = r2     // Catch:{ all -> 0x010f }
            java.lang.String r0 = java.lang.String.format(r0, r4)     // Catch:{ all -> 0x010f }
            r10.<init>(r0)     // Catch:{ all -> 0x010f }
            throw r10     // Catch:{ all -> 0x010f }
        L_0x0107:
            java.io.IOException r10 = new java.io.IOException     // Catch:{ all -> 0x010f }
            java.lang.String r0 = "Unable to find .rodata section."
            r10.<init>(r0)     // Catch:{ all -> 0x010f }
            throw r10     // Catch:{ all -> 0x010f }
        L_0x010f:
            r10 = move-exception
            goto L_0x0113
        L_0x0111:
            r10 = move-exception
            r1 = r0
        L_0x0113:
            if (r1 == 0) goto L_0x0118
            r1.close()     // Catch:{ Exception -> 0x0118 }
        L_0x0118:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.tinker.loader.shareutil.ShareOatUtil.a(java.io.File):java.lang.String");
    }
}
