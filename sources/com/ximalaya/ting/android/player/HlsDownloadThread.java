package com.ximalaya.ting.android.player;

public class HlsDownloadThread {

    /* renamed from: a  reason: collision with root package name */
    long f2273a;
    long b;
    private String c;
    private BufferItem d;

    public HlsDownloadThread(String str, BufferItem bufferItem) {
        this.c = str;
        this.d = bufferItem;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:137:0x03b1, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x03b3, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x03b5, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x03b7, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x03b9, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x03bb, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x03bd, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x03bf, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x03c0, code lost:
        r30 = r10;
        r14 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:254:0x052f, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:284:0x057a, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:285:0x057b, code lost:
        r18 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:286:0x057e, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:287:0x057f, code lost:
        r18 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:288:0x0582, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:289:0x0583, code lost:
        r18 = r3;
        r17 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:290:0x0588, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:291:0x0589, code lost:
        r18 = r3;
        r17 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:292:0x058e, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:293:0x058f, code lost:
        r18 = r3;
        r17 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:294:0x0594, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:295:0x0595, code lost:
        r18 = r3;
        r17 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:296:0x059a, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:297:0x059b, code lost:
        r18 = r3;
        r17 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:325:0x0647, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:326:0x0648, code lost:
        r34 = r3;
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:327:0x064d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:328:0x064e, code lost:
        r34 = r3;
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:329:0x0653, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:330:0x0654, code lost:
        r34 = r3;
        r3 = r0;
        r17 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:331:0x065b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:332:0x065c, code lost:
        r34 = r3;
        r3 = r0;
        r17 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:333:0x0663, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:334:0x0664, code lost:
        r34 = r3;
        r3 = r0;
        r17 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:335:0x066b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:336:0x066c, code lost:
        r34 = r3;
        r3 = r0;
        r17 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:337:0x0673, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:338:0x0674, code lost:
        r34 = r3;
        r3 = r0;
        r17 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:518:0x0aba, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:519:0x0abb, code lost:
        r34 = r3;
        r31 = r8;
        r3 = r0;
        r8 = r17;
        r10 = r30;
        r18 = r34;
        r14 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:520:0x0ac8, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:521:0x0ac9, code lost:
        r34 = r3;
        r31 = r8;
        r3 = r0;
        r8 = r17;
        r10 = r30;
        r18 = r34;
        r14 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:522:0x0ad6, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:523:0x0ad7, code lost:
        r34 = r3;
        r31 = r8;
        r3 = r0;
        r10 = r30;
        r18 = r34;
        r14 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:524:0x0ae2, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:525:0x0ae3, code lost:
        r34 = r3;
        r31 = r8;
        r3 = r0;
        r10 = r30;
        r18 = r34;
        r14 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:526:0x0aee, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:527:0x0aef, code lost:
        r34 = r3;
        r31 = r8;
        r3 = r0;
        r10 = r30;
        r18 = r34;
        r14 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:528:0x0afa, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:529:0x0afb, code lost:
        r34 = r3;
        r31 = r8;
        r3 = r0;
        r10 = r30;
        r18 = r34;
        r14 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:530:0x0b06, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:531:0x0b07, code lost:
        r34 = r3;
        r31 = r8;
        r3 = r0;
        r10 = r30;
        r18 = r34;
        r14 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:532:0x0b12, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:533:0x0b13, code lost:
        r31 = r8;
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:633:0x0d45, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:634:0x0d46, code lost:
        r31 = r8;
        r14 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:637:0x0d4c, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:638:0x0d4d, code lost:
        r31 = r8;
        r28 = r26;
        r14 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:642:0x0d57, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:643:0x0d58, code lost:
        r31 = r8;
        r28 = r26;
        r14 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:647:0x0d62, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:648:0x0d63, code lost:
        r31 = r8;
        r28 = r26;
        r14 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:651:0x0d6b, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:652:0x0d6c, code lost:
        r31 = r8;
        r28 = r26;
        r14 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:655:0x0d74, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:656:0x0d75, code lost:
        r31 = r8;
        r28 = r26;
        r14 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:659:0x0d7d, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:660:0x0d7e, code lost:
        r31 = r8;
        r28 = r26;
        r14 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:663:0x0d86, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:664:0x0d87, code lost:
        r31 = r8;
        r28 = r26;
        r14 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x028c, code lost:
        r0 = th;
        r14 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x028d, code lost:
        r3 = r0;
        r31 = r8;
        r14 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:681:0x0dbf, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:682:0x0dc0, code lost:
        r31 = r8;
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:683:0x0dc3, code lost:
        r7 = null;
        r11 = null;
        r14 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:685:0x0dc9, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:686:0x0dca, code lost:
        r28 = r7;
        r31 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0292, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0293, code lost:
        r3 = r0;
        r31 = r8;
        r8 = r17;
        r28 = r26;
        r14 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x029c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x029d, code lost:
        r3 = r0;
        r31 = r8;
        r8 = r17;
        r28 = r26;
        r14 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x02a6, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x02a7, code lost:
        r3 = r0;
        r31 = r8;
        r28 = r26;
        r14 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x02ae, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:753:0x0f05, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:754:0x0f06, code lost:
        r28 = r7;
        r31 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x02af, code lost:
        r3 = r0;
        r31 = r8;
        r28 = r26;
        r14 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x02b6, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x02b7, code lost:
        r3 = r0;
        r31 = r8;
        r28 = r26;
        r14 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x02be, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x02bf, code lost:
        r3 = r0;
        r31 = r8;
        r28 = r26;
        r14 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x02c6, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x02c7, code lost:
        r3 = r0;
        r31 = r8;
        r28 = r26;
        r14 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:832:0x1091, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:833:0x1092, code lost:
        r28 = r7;
        r31 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:869:0x1149, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:870:0x114a, code lost:
        r28 = r7;
        r31 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:905:0x11e7, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:906:0x11e8, code lost:
        r28 = r7;
        r31 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:941:0x1285, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:942:0x1286, code lost:
        r28 = r7;
        r31 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:984:0x1367, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:985:0x1368, code lost:
        r28 = r7;
        r31 = r8;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:1002:0x13d8  */
    /* JADX WARNING: Removed duplicated region for block: B:1005:0x13e6  */
    /* JADX WARNING: Removed duplicated region for block: B:1012:0x140b  */
    /* JADX WARNING: Removed duplicated region for block: B:1014:0x1410 A[SYNTHETIC, Splitter:B:1014:0x1410] */
    /* JADX WARNING: Removed duplicated region for block: B:1018:0x1415 A[SYNTHETIC, Splitter:B:1018:0x1415] */
    /* JADX WARNING: Removed duplicated region for block: B:1037:0x1468  */
    /* JADX WARNING: Removed duplicated region for block: B:1040:0x1476  */
    /* JADX WARNING: Removed duplicated region for block: B:1050:0x14a6  */
    /* JADX WARNING: Removed duplicated region for block: B:1056:0x1522  */
    /* JADX WARNING: Removed duplicated region for block: B:1065:0x15a4  */
    /* JADX WARNING: Removed duplicated region for block: B:1067:0x15a9 A[SYNTHETIC, Splitter:B:1067:0x15a9] */
    /* JADX WARNING: Removed duplicated region for block: B:1071:0x15ae A[SYNTHETIC, Splitter:B:1071:0x15ae] */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x039c A[Catch:{ MalformedURLException -> 0x0481, SocketTimeoutException -> 0x0479, UnknownHostException -> 0x0471, IllegalArgumentException -> 0x0469, FileNotFoundException -> 0x0461, IOException -> 0x0457, Throwable -> 0x044d, all -> 0x0445 }] */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x03bf A[Catch:{ MalformedURLException -> 0x0481, SocketTimeoutException -> 0x0479, UnknownHostException -> 0x0471, IllegalArgumentException -> 0x0469, FileNotFoundException -> 0x0461, IOException -> 0x0457, Throwable -> 0x044d, all -> 0x0445 }, ExcHandler: all (th java.lang.Throwable), Splitter:B:114:0x0363] */
    /* JADX WARNING: Removed duplicated region for block: B:254:0x052f A[ExcHandler: all (th java.lang.Throwable), Splitter:B:252:0x052b] */
    /* JADX WARNING: Removed duplicated region for block: B:532:0x0b12 A[ExcHandler: all (r0v110 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:215:0x049b] */
    /* JADX WARNING: Removed duplicated region for block: B:585:0x0cae  */
    /* JADX WARNING: Removed duplicated region for block: B:633:0x0d45 A[ExcHandler: all (th java.lang.Throwable), PHI: r14 
      PHI: (r14v75 long) = (r14v120 long), (r14v128 long), (r14v217 long), (r14v241 long) binds: [B:21:0x00dd, B:22:?, B:83:0x02d0, B:110:0x0357] A[DONT_GENERATE, DONT_INLINE], Splitter:B:21:0x00dd] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x028c A[ExcHandler: all (th java.lang.Throwable), PHI: r14 
      PHI: (r14v102 long) = (r14v137 long), (r14v226 long), (r14v319 long), (r14v337 long), (r14v345 long), (r14v353 long) binds: [B:61:0x0255, B:86:0x02d6, B:87:?, B:62:?, B:64:0x0288, B:65:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:61:0x0255] */
    /* JADX WARNING: Removed duplicated region for block: B:681:0x0dbf A[ExcHandler: all (r0v26 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:11:0x005e] */
    /* JADX WARNING: Removed duplicated region for block: B:695:0x0de3 A[Catch:{ all -> 0x0e8c }] */
    /* JADX WARNING: Removed duplicated region for block: B:710:0x0e4f  */
    /* JADX WARNING: Removed duplicated region for block: B:714:0x0e5c A[Catch:{ all -> 0x0e86 }] */
    /* JADX WARNING: Removed duplicated region for block: B:719:0x0e6e  */
    /* JADX WARNING: Removed duplicated region for block: B:733:0x0ea1  */
    /* JADX WARNING: Removed duplicated region for block: B:736:0x0eaf  */
    /* JADX WARNING: Removed duplicated region for block: B:743:0x0ed4  */
    /* JADX WARNING: Removed duplicated region for block: B:745:0x0ed9 A[SYNTHETIC, Splitter:B:745:0x0ed9] */
    /* JADX WARNING: Removed duplicated region for block: B:749:0x0ede A[SYNTHETIC, Splitter:B:749:0x0ede] */
    /* JADX WARNING: Removed duplicated region for block: B:759:0x0f13 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:812:0x1031  */
    /* JADX WARNING: Removed duplicated region for block: B:815:0x103f  */
    /* JADX WARNING: Removed duplicated region for block: B:822:0x1064  */
    /* JADX WARNING: Removed duplicated region for block: B:824:0x1069 A[SYNTHETIC, Splitter:B:824:0x1069] */
    /* JADX WARNING: Removed duplicated region for block: B:828:0x106e A[SYNTHETIC, Splitter:B:828:0x106e] */
    /* JADX WARNING: Removed duplicated region for block: B:842:0x10a8 A[Catch:{ all -> 0x0ff4, all -> 0x1457 }] */
    /* JADX WARNING: Removed duplicated region for block: B:849:0x1100  */
    /* JADX WARNING: Removed duplicated region for block: B:852:0x110e  */
    /* JADX WARNING: Removed duplicated region for block: B:859:0x1133  */
    /* JADX WARNING: Removed duplicated region for block: B:861:0x1138 A[SYNTHETIC, Splitter:B:861:0x1138] */
    /* JADX WARNING: Removed duplicated region for block: B:865:0x113d A[SYNTHETIC, Splitter:B:865:0x113d] */
    /* JADX WARNING: Removed duplicated region for block: B:879:0x1160 A[Catch:{ all -> 0x0ff4, all -> 0x1457 }] */
    /* JADX WARNING: Removed duplicated region for block: B:885:0x119e  */
    /* JADX WARNING: Removed duplicated region for block: B:888:0x11ac  */
    /* JADX WARNING: Removed duplicated region for block: B:895:0x11d1  */
    /* JADX WARNING: Removed duplicated region for block: B:897:0x11d6 A[SYNTHETIC, Splitter:B:897:0x11d6] */
    /* JADX WARNING: Removed duplicated region for block: B:901:0x11db A[SYNTHETIC, Splitter:B:901:0x11db] */
    /* JADX WARNING: Removed duplicated region for block: B:915:0x11fe A[Catch:{ all -> 0x0ff4, all -> 0x1457 }] */
    /* JADX WARNING: Removed duplicated region for block: B:921:0x123c  */
    /* JADX WARNING: Removed duplicated region for block: B:924:0x124a  */
    /* JADX WARNING: Removed duplicated region for block: B:931:0x126f  */
    /* JADX WARNING: Removed duplicated region for block: B:933:0x1274 A[SYNTHETIC, Splitter:B:933:0x1274] */
    /* JADX WARNING: Removed duplicated region for block: B:937:0x1279 A[SYNTHETIC, Splitter:B:937:0x1279] */
    /* JADX WARNING: Removed duplicated region for block: B:952:0x12a1 A[Catch:{ all -> 0x0ff4, all -> 0x1457 }] */
    /* JADX WARNING: Removed duplicated region for block: B:955:0x12bb A[Catch:{ all -> 0x0ff4, all -> 0x1457 }] */
    /* JADX WARNING: Removed duplicated region for block: B:956:0x12db A[Catch:{ all -> 0x0ff4, all -> 0x1457 }] */
    /* JADX WARNING: Removed duplicated region for block: B:962:0x1308  */
    /* JADX WARNING: Removed duplicated region for block: B:965:0x1316  */
    /* JADX WARNING: Removed duplicated region for block: B:972:0x133b  */
    /* JADX WARNING: Removed duplicated region for block: B:974:0x1340 A[SYNTHETIC, Splitter:B:974:0x1340] */
    /* JADX WARNING: Removed duplicated region for block: B:978:0x1345 A[SYNTHETIC, Splitter:B:978:0x1345] */
    /* JADX WARNING: Removed duplicated region for block: B:990:0x1373 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int a() {
        /*
            r42 = this;
            r1 = r42
            com.ximalaya.ting.android.opensdk.model.xdcs.CdnConfigModel r2 = com.ximalaya.ting.android.player.cdn.CdnUtil.a()
            r4 = 1
            if (r2 != 0) goto L_0x000b
            r5 = 1
            goto L_0x000c
        L_0x000b:
            r5 = 0
        L_0x000c:
            r6 = 3
            java.util.UUID r7 = java.util.UUID.randomUUID()
            java.lang.String r7 = r7.toString()
            r10 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r17 = 0
            r18 = 0
        L_0x001e:
            int r20 = r6 + -1
            if (r6 <= 0) goto L_0x15ce
            long r8 = java.lang.System.currentTimeMillis()
            java.lang.String r6 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r3 = "DownloadThread hls readData start:"
            r11.append(r3)
            r11.append(r8)
            java.lang.String r3 = r11.toString()
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r6, (java.lang.Object) r3)
            java.lang.String r3 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r11 = "DownloadThread hls readData mCurrentDownloadUrl:"
            r6.append(r11)
            java.lang.String r11 = r1.c
            r6.append(r11)
            java.lang.String r6 = r6.toString()
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r3, (java.lang.Object) r6)
            if (r5 != 0) goto L_0x005c
            com.ximalaya.ting.android.player.cdn.CdnCollectDataForPlay r3 = new com.ximalaya.ting.android.player.cdn.CdnCollectDataForPlay
            r3.<init>()
            r12 = r3
        L_0x005c:
            r24 = 1000(0x3e8, double:4.94E-321)
            java.lang.String r11 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ MalformedURLException -> 0x1367, SocketTimeoutException -> 0x1285, UnknownHostException -> 0x11e7, IllegalArgumentException -> 0x1149, FileNotFoundException -> 0x1091, IOException -> 0x0f05, Throwable -> 0x0dc9, all -> 0x0dbf }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x1367, SocketTimeoutException -> 0x1285, UnknownHostException -> 0x11e7, IllegalArgumentException -> 0x1149, FileNotFoundException -> 0x1091, IOException -> 0x0f05, Throwable -> 0x0dc9, all -> 0x0dbf }
            r3.<init>()     // Catch:{ MalformedURLException -> 0x1367, SocketTimeoutException -> 0x1285, UnknownHostException -> 0x11e7, IllegalArgumentException -> 0x1149, FileNotFoundException -> 0x1091, IOException -> 0x0f05, Throwable -> 0x0dc9, all -> 0x0dbf }
            java.lang.String r6 = "HlsDownloadThread mPlayUrl:"
            r3.append(r6)     // Catch:{ MalformedURLException -> 0x1367, SocketTimeoutException -> 0x1285, UnknownHostException -> 0x11e7, IllegalArgumentException -> 0x1149, FileNotFoundException -> 0x1091, IOException -> 0x0f05, Throwable -> 0x0dc9, all -> 0x0dbf }
            java.lang.String r6 = r1.c     // Catch:{ MalformedURLException -> 0x1367, SocketTimeoutException -> 0x1285, UnknownHostException -> 0x11e7, IllegalArgumentException -> 0x1149, FileNotFoundException -> 0x1091, IOException -> 0x0f05, Throwable -> 0x0dc9, all -> 0x0dbf }
            r3.append(r6)     // Catch:{ MalformedURLException -> 0x1367, SocketTimeoutException -> 0x1285, UnknownHostException -> 0x11e7, IllegalArgumentException -> 0x1149, FileNotFoundException -> 0x1091, IOException -> 0x0f05, Throwable -> 0x0dc9, all -> 0x0dbf }
            java.lang.String r3 = r3.toString()     // Catch:{ MalformedURLException -> 0x1367, SocketTimeoutException -> 0x1285, UnknownHostException -> 0x11e7, IllegalArgumentException -> 0x1149, FileNotFoundException -> 0x1091, IOException -> 0x0f05, Throwable -> 0x0dc9, all -> 0x0dbf }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r11, (java.lang.Object) r3)     // Catch:{ MalformedURLException -> 0x1367, SocketTimeoutException -> 0x1285, UnknownHostException -> 0x11e7, IllegalArgumentException -> 0x1149, FileNotFoundException -> 0x1091, IOException -> 0x0f05, Throwable -> 0x0dc9, all -> 0x0dbf }
            java.lang.String[] r3 = new java.lang.String[r4]     // Catch:{ MalformedURLException -> 0x1367, SocketTimeoutException -> 0x1285, UnknownHostException -> 0x11e7, IllegalArgumentException -> 0x1149, FileNotFoundException -> 0x1091, IOException -> 0x0f05, Throwable -> 0x0dc9, all -> 0x0dbf }
            java.lang.String r6 = r1.c     // Catch:{ MalformedURLException -> 0x1367, SocketTimeoutException -> 0x1285, UnknownHostException -> 0x11e7, IllegalArgumentException -> 0x1149, FileNotFoundException -> 0x1091, IOException -> 0x0f05, Throwable -> 0x0dc9, all -> 0x0dbf }
            r11 = 0
            r3[r11] = r6     // Catch:{ MalformedURLException -> 0x1367, SocketTimeoutException -> 0x1285, UnknownHostException -> 0x11e7, IllegalArgumentException -> 0x1149, FileNotFoundException -> 0x1091, IOException -> 0x0f05, Throwable -> 0x0dc9, all -> 0x0dbf }
            java.lang.String r6 = r1.c     // Catch:{ MalformedURLException -> 0x1367, SocketTimeoutException -> 0x1285, UnknownHostException -> 0x11e7, IllegalArgumentException -> 0x1149, FileNotFoundException -> 0x1091, IOException -> 0x0f05, Throwable -> 0x0dc9, all -> 0x0dbf }
            boolean r6 = android.text.TextUtils.isEmpty(r6)     // Catch:{ MalformedURLException -> 0x1367, SocketTimeoutException -> 0x1285, UnknownHostException -> 0x11e7, IllegalArgumentException -> 0x1149, FileNotFoundException -> 0x1091, IOException -> 0x0f05, Throwable -> 0x0dc9, all -> 0x0dbf }
            if (r6 == 0) goto L_0x00c6
            java.lang.String r6 = ""
            goto L_0x00d0
        L_0x0088:
            r0 = move-exception
            r3 = r0
            r31 = r8
            goto L_0x0dc3
        L_0x008e:
            r0 = move-exception
            r3 = r0
            r28 = r7
            r31 = r8
            goto L_0x0dcf
        L_0x0096:
            r0 = move-exception
            r3 = r0
            r28 = r7
            r31 = r8
            goto L_0x0f0b
        L_0x009e:
            r0 = move-exception
            r3 = r0
            r28 = r7
            r31 = r8
            goto L_0x1097
        L_0x00a6:
            r0 = move-exception
            r3 = r0
            r28 = r7
            r31 = r8
            goto L_0x114f
        L_0x00ae:
            r0 = move-exception
            r3 = r0
            r28 = r7
            r31 = r8
            goto L_0x11ed
        L_0x00b6:
            r0 = move-exception
            r3 = r0
            r28 = r7
            r31 = r8
            goto L_0x128b
        L_0x00be:
            r0 = move-exception
            r3 = r0
            r28 = r7
            r31 = r8
            goto L_0x136d
        L_0x00c6:
            java.lang.String r6 = r1.c     // Catch:{ MalformedURLException -> 0x1367, SocketTimeoutException -> 0x1285, UnknownHostException -> 0x11e7, IllegalArgumentException -> 0x1149, FileNotFoundException -> 0x1091, IOException -> 0x0f05, Throwable -> 0x0dc9, all -> 0x0dbf }
            android.net.Uri r6 = android.net.Uri.parse(r6)     // Catch:{ MalformedURLException -> 0x1367, SocketTimeoutException -> 0x1285, UnknownHostException -> 0x11e7, IllegalArgumentException -> 0x1149, FileNotFoundException -> 0x1091, IOException -> 0x0f05, Throwable -> 0x0dc9, all -> 0x0dbf }
            java.lang.String r6 = r6.getHost()     // Catch:{ MalformedURLException -> 0x1367, SocketTimeoutException -> 0x1285, UnknownHostException -> 0x11e7, IllegalArgumentException -> 0x1149, FileNotFoundException -> 0x1091, IOException -> 0x0f05, Throwable -> 0x0dc9, all -> 0x0dbf }
        L_0x00d0:
            r11 = 4
            java.lang.String r4 = "GET"
            r27 = r6
            r26 = r7
            r6 = 0
            r7 = 0
            java.net.HttpURLConnection r11 = com.ximalaya.ting.android.player.PlayerUtil.a(r3, r6, r11, r7, r4)     // Catch:{ MalformedURLException -> 0x0db8, SocketTimeoutException -> 0x0db1, UnknownHostException -> 0x0daa, IllegalArgumentException -> 0x0da3, FileNotFoundException -> 0x0d9c, IOException -> 0x0d95, Throwable -> 0x0d8f, all -> 0x0dbf }
            r3 = r3[r7]     // Catch:{ MalformedURLException -> 0x0d86, SocketTimeoutException -> 0x0d7d, UnknownHostException -> 0x0d74, IllegalArgumentException -> 0x0d6b, FileNotFoundException -> 0x0d62, IOException -> 0x0d57, Throwable -> 0x0d4c, all -> 0x0d45 }
            r1.c = r3     // Catch:{ MalformedURLException -> 0x0d86, SocketTimeoutException -> 0x0d7d, UnknownHostException -> 0x0d74, IllegalArgumentException -> 0x0d6b, FileNotFoundException -> 0x0d62, IOException -> 0x0d57, Throwable -> 0x0d4c, all -> 0x0d45 }
            if (r11 != 0) goto L_0x0253
            if (r5 != 0) goto L_0x022c
            if (r12 == 0) goto L_0x022c
            java.lang.String r3 = r12.o()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x00f5
            r3 = 0
            r12.m(r3)
        L_0x00f5:
            java.lang.String r3 = r12.n()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x0104
            java.lang.String r3 = ""
            r12.l(r3)
        L_0x0104:
            long r3 = java.lang.System.currentTimeMillis()
            r12.b((long) r3)
            java.lang.String r3 = r12.c()
            if (r3 == 0) goto L_0x011d
            java.lang.String r3 = r12.c()
            java.lang.String r4 = "success"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x0122
        L_0x011d:
            java.lang.String r3 = "failed"
            r12.c(r3)
        L_0x0122:
            if (r13 != 0) goto L_0x022c
            int r3 = r2.f()
            r4 = -1
            if (r3 != r4) goto L_0x012e
            r5 = 1
            goto L_0x022c
        L_0x012e:
            if (r3 != 0) goto L_0x01ac
            int r3 = r2.h()
            long r3 = (long) r3
            r1.f2273a = r3
            int r3 = r2.j()
            long r3 = (long) r3
            r1.b = r3
            long r3 = r1.f2273a
            long r3 = r3 * r24
            int r6 = (r14 > r3 ? 1 : (r14 == r3 ? 0 : -1))
            if (r6 <= 0) goto L_0x0176
            java.lang.String r3 = "cdn_connected_too_slow"
            r12.k(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "connected_time="
            r3.append(r4)
            float r4 = (float) r14
            r6 = 0
            float r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r4, (boolean) r6)
            r3.append(r4)
            java.lang.String r4 = "s, connected_time_threshold="
            r3.append(r4)
            long r6 = r1.f2273a
            r3.append(r6)
            java.lang.String r4 = "s"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r12.j(r3)
            goto L_0x022c
        L_0x0176:
            long r3 = r1.b
            float r3 = (float) r3
            int r3 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r3 <= 0) goto L_0x022c
            java.lang.String r3 = "cdn_download_too_slow"
            r12.k(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "download_speed="
            r3.append(r4)
            r4 = 1
            float r6 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r10, (boolean) r4)
            r3.append(r6)
            java.lang.String r4 = "KB/s, download_speed_threshold="
            r3.append(r4)
            long r6 = r1.b
            r3.append(r6)
            java.lang.String r4 = "KB/s"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r12.j(r3)
            goto L_0x022c
        L_0x01ac:
            r4 = 1
            if (r3 != r4) goto L_0x022c
            int r3 = r2.g()
            long r3 = (long) r3
            r1.f2273a = r3
            int r3 = r2.i()
            long r3 = (long) r3
            r1.b = r3
            r3 = 0
            r1.f2273a = r3
            long r3 = r1.f2273a
            long r3 = r3 * r24
            int r6 = (r14 > r3 ? 1 : (r14 == r3 ? 0 : -1))
            if (r6 <= 0) goto L_0x01f8
            java.lang.String r3 = "cdn_connected_too_slow"
            r12.k(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "connected_time="
            r3.append(r4)
            float r4 = (float) r14
            r6 = 0
            float r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r4, (boolean) r6)
            r3.append(r4)
            java.lang.String r4 = "s, connected_time_threshold="
            r3.append(r4)
            long r6 = r1.f2273a
            r3.append(r6)
            java.lang.String r4 = "s"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r12.j(r3)
            goto L_0x022c
        L_0x01f8:
            long r3 = r1.b
            float r3 = (float) r3
            int r3 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r3 <= 0) goto L_0x022c
            java.lang.String r3 = "cdn_download_too_slow"
            r12.k(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "download_speed="
            r3.append(r4)
            r4 = 1
            float r6 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r10, (boolean) r4)
            r3.append(r6)
            java.lang.String r4 = "KB/s, download_speed_threshold="
            r3.append(r4)
            long r6 = r1.b
            r3.append(r6)
            java.lang.String r4 = "KB/s"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r12.j(r3)
        L_0x022c:
            if (r11 == 0) goto L_0x0231
            r11.disconnect()
        L_0x0231:
            java.lang.String r3 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "DownloadThread hls readData end:"
            r4.append(r6)
            long r6 = java.lang.System.currentTimeMillis()
            long r6 = r6 - r8
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r3, (java.lang.Object) r4)
            r6 = r20
            r7 = r26
        L_0x0250:
            r4 = 1
            goto L_0x001e
        L_0x0253:
            if (r12 == 0) goto L_0x02ce
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ MalformedURLException -> 0x02c6, SocketTimeoutException -> 0x02be, UnknownHostException -> 0x02b6, IllegalArgumentException -> 0x02ae, FileNotFoundException -> 0x02a6, IOException -> 0x029c, Throwable -> 0x0292, all -> 0x028c }
            r6 = 0
            long r14 = r3 - r8
            float r3 = (float) r14     // Catch:{ MalformedURLException -> 0x02c6, SocketTimeoutException -> 0x02be, UnknownHostException -> 0x02b6, IllegalArgumentException -> 0x02ae, FileNotFoundException -> 0x02a6, IOException -> 0x029c, Throwable -> 0x0292, all -> 0x028c }
            r4 = 0
            float r3 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r3, (boolean) r4)     // Catch:{ MalformedURLException -> 0x02c6, SocketTimeoutException -> 0x02be, UnknownHostException -> 0x02b6, IllegalArgumentException -> 0x02ae, FileNotFoundException -> 0x02a6, IOException -> 0x029c, Throwable -> 0x0292, all -> 0x028c }
            r12.a((float) r3)     // Catch:{ MalformedURLException -> 0x02c6, SocketTimeoutException -> 0x02be, UnknownHostException -> 0x02b6, IllegalArgumentException -> 0x02ae, FileNotFoundException -> 0x02a6, IOException -> 0x029c, Throwable -> 0x0292, all -> 0x028c }
            java.lang.String r3 = r1.c     // Catch:{ MalformedURLException -> 0x02c6, SocketTimeoutException -> 0x02be, UnknownHostException -> 0x02b6, IllegalArgumentException -> 0x02ae, FileNotFoundException -> 0x02a6, IOException -> 0x029c, Throwable -> 0x0292, all -> 0x028c }
            r12.g(r3)     // Catch:{ MalformedURLException -> 0x02c6, SocketTimeoutException -> 0x02be, UnknownHostException -> 0x02b6, IllegalArgumentException -> 0x02ae, FileNotFoundException -> 0x02a6, IOException -> 0x029c, Throwable -> 0x0292, all -> 0x028c }
            java.lang.String r3 = r1.c     // Catch:{ MalformedURLException -> 0x02c6, SocketTimeoutException -> 0x02be, UnknownHostException -> 0x02b6, IllegalArgumentException -> 0x02ae, FileNotFoundException -> 0x02a6, IOException -> 0x029c, Throwable -> 0x0292, all -> 0x028c }
            java.lang.String r3 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.String) r3)     // Catch:{ MalformedURLException -> 0x02c6, SocketTimeoutException -> 0x02be, UnknownHostException -> 0x02b6, IllegalArgumentException -> 0x02ae, FileNotFoundException -> 0x02a6, IOException -> 0x029c, Throwable -> 0x0292, all -> 0x028c }
            r12.h(r3)     // Catch:{ MalformedURLException -> 0x02c6, SocketTimeoutException -> 0x02be, UnknownHostException -> 0x02b6, IllegalArgumentException -> 0x02ae, FileNotFoundException -> 0x02a6, IOException -> 0x029c, Throwable -> 0x0292, all -> 0x028c }
            r6 = r27
            r12.n(r6)     // Catch:{ MalformedURLException -> 0x02c6, SocketTimeoutException -> 0x02be, UnknownHostException -> 0x02b6, IllegalArgumentException -> 0x02ae, FileNotFoundException -> 0x02a6, IOException -> 0x029c, Throwable -> 0x0292, all -> 0x028c }
            java.lang.String r3 = "play_hls"
            r12.f(r3)     // Catch:{ MalformedURLException -> 0x02c6, SocketTimeoutException -> 0x02be, UnknownHostException -> 0x02b6, IllegalArgumentException -> 0x02ae, FileNotFoundException -> 0x02a6, IOException -> 0x029c, Throwable -> 0x0292, all -> 0x028c }
            java.lang.String r3 = "via"
            java.lang.String r3 = r11.getHeaderField(r3)     // Catch:{ MalformedURLException -> 0x02c6, SocketTimeoutException -> 0x02be, UnknownHostException -> 0x02b6, IllegalArgumentException -> 0x02ae, FileNotFoundException -> 0x02a6, IOException -> 0x029c, Throwable -> 0x0292, all -> 0x028c }
            r12.m(r3)     // Catch:{ MalformedURLException -> 0x02c6, SocketTimeoutException -> 0x02be, UnknownHostException -> 0x02b6, IllegalArgumentException -> 0x02ae, FileNotFoundException -> 0x02a6, IOException -> 0x029c, Throwable -> 0x0292, all -> 0x028c }
            r3 = r26
            r12.o(r3)     // Catch:{ MalformedURLException -> 0x034a, SocketTimeoutException -> 0x033f, UnknownHostException -> 0x0334, IllegalArgumentException -> 0x0329, FileNotFoundException -> 0x031e, IOException -> 0x0311, Throwable -> 0x0304, all -> 0x028c }
            goto L_0x02d0
        L_0x028c:
            r0 = move-exception
        L_0x028d:
            r3 = r0
            r31 = r8
            goto L_0x0d49
        L_0x0292:
            r0 = move-exception
            r3 = r0
            r31 = r8
            r8 = r17
            r28 = r26
            goto L_0x0d54
        L_0x029c:
            r0 = move-exception
            r3 = r0
            r31 = r8
            r8 = r17
            r28 = r26
            goto L_0x0d5f
        L_0x02a6:
            r0 = move-exception
            r3 = r0
            r31 = r8
            r28 = r26
            goto L_0x0d68
        L_0x02ae:
            r0 = move-exception
            r3 = r0
            r31 = r8
            r28 = r26
            goto L_0x0d71
        L_0x02b6:
            r0 = move-exception
            r3 = r0
            r31 = r8
            r28 = r26
            goto L_0x0d7a
        L_0x02be:
            r0 = move-exception
            r3 = r0
            r31 = r8
            r28 = r26
            goto L_0x0d83
        L_0x02c6:
            r0 = move-exception
            r3 = r0
            r31 = r8
            r28 = r26
            goto L_0x0d8c
        L_0x02ce:
            r3 = r26
        L_0x02d0:
            int r4 = r11.getResponseCode()     // Catch:{ MalformedURLException -> 0x0d3f, SocketTimeoutException -> 0x0d39, UnknownHostException -> 0x0d33, IllegalArgumentException -> 0x0d2d, FileNotFoundException -> 0x0d27, IOException -> 0x0d21, Throwable -> 0x0d1b, all -> 0x0d45 }
            if (r12 == 0) goto L_0x0355
            long r6 = java.lang.System.currentTimeMillis()     // Catch:{ MalformedURLException -> 0x034a, SocketTimeoutException -> 0x033f, UnknownHostException -> 0x0334, IllegalArgumentException -> 0x0329, FileNotFoundException -> 0x031e, IOException -> 0x0311, Throwable -> 0x0304, all -> 0x028c }
            r14 = 0
            long r14 = r6 - r8
            float r6 = (float) r14     // Catch:{ MalformedURLException -> 0x034a, SocketTimeoutException -> 0x033f, UnknownHostException -> 0x0334, IllegalArgumentException -> 0x0329, FileNotFoundException -> 0x031e, IOException -> 0x0311, Throwable -> 0x0304, all -> 0x028c }
            r7 = 0
            float r6 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r6, (boolean) r7)     // Catch:{ MalformedURLException -> 0x034a, SocketTimeoutException -> 0x033f, UnknownHostException -> 0x0334, IllegalArgumentException -> 0x0329, FileNotFoundException -> 0x031e, IOException -> 0x0311, Throwable -> 0x0304, all -> 0x028c }
            r12.a((float) r6)     // Catch:{ MalformedURLException -> 0x034a, SocketTimeoutException -> 0x033f, UnknownHostException -> 0x0334, IllegalArgumentException -> 0x0329, FileNotFoundException -> 0x031e, IOException -> 0x0311, Throwable -> 0x0304, all -> 0x028c }
            java.lang.String r6 = "via"
            java.lang.String r6 = r11.getHeaderField(r6)     // Catch:{ MalformedURLException -> 0x034a, SocketTimeoutException -> 0x033f, UnknownHostException -> 0x0334, IllegalArgumentException -> 0x0329, FileNotFoundException -> 0x031e, IOException -> 0x0311, Throwable -> 0x0304, all -> 0x028c }
            r12.m(r6)     // Catch:{ MalformedURLException -> 0x034a, SocketTimeoutException -> 0x033f, UnknownHostException -> 0x0334, IllegalArgumentException -> 0x0329, FileNotFoundException -> 0x031e, IOException -> 0x0311, Throwable -> 0x0304, all -> 0x028c }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x034a, SocketTimeoutException -> 0x033f, UnknownHostException -> 0x0334, IllegalArgumentException -> 0x0329, FileNotFoundException -> 0x031e, IOException -> 0x0311, Throwable -> 0x0304, all -> 0x028c }
            r6.<init>()     // Catch:{ MalformedURLException -> 0x034a, SocketTimeoutException -> 0x033f, UnknownHostException -> 0x0334, IllegalArgumentException -> 0x0329, FileNotFoundException -> 0x031e, IOException -> 0x0311, Throwable -> 0x0304, all -> 0x028c }
            r6.append(r4)     // Catch:{ MalformedURLException -> 0x034a, SocketTimeoutException -> 0x033f, UnknownHostException -> 0x0334, IllegalArgumentException -> 0x0329, FileNotFoundException -> 0x031e, IOException -> 0x0311, Throwable -> 0x0304, all -> 0x028c }
            java.lang.String r7 = ""
            r6.append(r7)     // Catch:{ MalformedURLException -> 0x034a, SocketTimeoutException -> 0x033f, UnknownHostException -> 0x0334, IllegalArgumentException -> 0x0329, FileNotFoundException -> 0x031e, IOException -> 0x0311, Throwable -> 0x0304, all -> 0x028c }
            java.lang.String r6 = r6.toString()     // Catch:{ MalformedURLException -> 0x034a, SocketTimeoutException -> 0x033f, UnknownHostException -> 0x0334, IllegalArgumentException -> 0x0329, FileNotFoundException -> 0x031e, IOException -> 0x0311, Throwable -> 0x0304, all -> 0x028c }
            r12.l(r6)     // Catch:{ MalformedURLException -> 0x034a, SocketTimeoutException -> 0x033f, UnknownHostException -> 0x0334, IllegalArgumentException -> 0x0329, FileNotFoundException -> 0x031e, IOException -> 0x0311, Throwable -> 0x0304, all -> 0x028c }
            goto L_0x0355
        L_0x0304:
            r0 = move-exception
            r28 = r3
            r31 = r8
            r8 = r17
            r7 = 0
            r33 = 0
        L_0x030e:
            r3 = r0
            goto L_0x0dd5
        L_0x0311:
            r0 = move-exception
            r28 = r3
            r31 = r8
            r8 = r17
            r7 = 0
            r33 = 0
        L_0x031b:
            r3 = r0
            goto L_0x0f11
        L_0x031e:
            r0 = move-exception
            r28 = r3
            r31 = r8
            r7 = 0
            r33 = 0
        L_0x0326:
            r3 = r0
            goto L_0x109b
        L_0x0329:
            r0 = move-exception
            r28 = r3
            r31 = r8
            r7 = 0
            r33 = 0
        L_0x0331:
            r3 = r0
            goto L_0x1153
        L_0x0334:
            r0 = move-exception
            r28 = r3
            r31 = r8
            r7 = 0
            r33 = 0
        L_0x033c:
            r3 = r0
            goto L_0x11f1
        L_0x033f:
            r0 = move-exception
            r28 = r3
            r31 = r8
            r7 = 0
            r33 = 0
        L_0x0347:
            r3 = r0
            goto L_0x128f
        L_0x034a:
            r0 = move-exception
            r28 = r3
            r31 = r8
            r7 = 0
            r33 = 0
        L_0x0352:
            r3 = r0
            goto L_0x1371
        L_0x0355:
            java.lang.String r6 = "Content-Range"
            java.lang.String r6 = r11.getHeaderField(r6)     // Catch:{ MalformedURLException -> 0x0d3f, SocketTimeoutException -> 0x0d39, UnknownHostException -> 0x0d33, IllegalArgumentException -> 0x0d2d, FileNotFoundException -> 0x0d27, IOException -> 0x0d21, Throwable -> 0x0d1b, all -> 0x0d45 }
            boolean r7 = android.text.TextUtils.isEmpty(r6)     // Catch:{ MalformedURLException -> 0x0d3f, SocketTimeoutException -> 0x0d39, UnknownHostException -> 0x0d33, IllegalArgumentException -> 0x0d2d, FileNotFoundException -> 0x0d27, IOException -> 0x0d21, Throwable -> 0x0d1b, all -> 0x0d45 }
            if (r7 != 0) goto L_0x040a
            java.lang.String r7 = "/"
            java.lang.String[] r6 = r6.split(r7)     // Catch:{ MalformedURLException -> 0x0400, SocketTimeoutException -> 0x03f6, UnknownHostException -> 0x03ec, IllegalArgumentException -> 0x03e2, FileNotFoundException -> 0x03d8, IOException -> 0x03ce, Throwable -> 0x03c4, all -> 0x03bf }
            java.lang.String r7 = ""
            java.lang.String r26 = ""
            r28 = r3
            int r3 = r6.length     // Catch:{ MalformedURLException -> 0x03bd, SocketTimeoutException -> 0x03bb, UnknownHostException -> 0x03b9, IllegalArgumentException -> 0x03b7, FileNotFoundException -> 0x03b5, IOException -> 0x03b3, Throwable -> 0x03b1, all -> 0x03bf }
            r29 = r7
            r7 = 2
            if (r3 < r7) goto L_0x03a1
            r3 = 0
            r23 = r6[r3]     // Catch:{ MalformedURLException -> 0x03bd, SocketTimeoutException -> 0x03bb, UnknownHostException -> 0x03b9, IllegalArgumentException -> 0x03b7, FileNotFoundException -> 0x03b5, IOException -> 0x03b3, Throwable -> 0x03b1, all -> 0x03bf }
            boolean r23 = android.text.TextUtils.isEmpty(r23)     // Catch:{ MalformedURLException -> 0x03bd, SocketTimeoutException -> 0x03bb, UnknownHostException -> 0x03b9, IllegalArgumentException -> 0x03b7, FileNotFoundException -> 0x03b5, IOException -> 0x03b3, Throwable -> 0x03b1, all -> 0x03bf }
            if (r23 != 0) goto L_0x038f
            r7 = r6[r3]     // Catch:{ MalformedURLException -> 0x03bd, SocketTimeoutException -> 0x03bb, UnknownHostException -> 0x03b9, IllegalArgumentException -> 0x03b7, FileNotFoundException -> 0x03b5, IOException -> 0x03b3, Throwable -> 0x03b1, all -> 0x03bf }
            java.lang.String r3 = " "
            java.lang.String[] r3 = r7.split(r3)     // Catch:{ MalformedURLException -> 0x03bd, SocketTimeoutException -> 0x03bb, UnknownHostException -> 0x03b9, IllegalArgumentException -> 0x03b7, FileNotFoundException -> 0x03b5, IOException -> 0x03b3, Throwable -> 0x03b1, all -> 0x03bf }
            int r7 = r3.length     // Catch:{ MalformedURLException -> 0x03bd, SocketTimeoutException -> 0x03bb, UnknownHostException -> 0x03b9, IllegalArgumentException -> 0x03b7, FileNotFoundException -> 0x03b5, IOException -> 0x03b3, Throwable -> 0x03b1, all -> 0x03bf }
            r30 = r10
            r10 = 2
            if (r7 < r10) goto L_0x0391
            r7 = 1
            r3 = r3[r7]     // Catch:{ MalformedURLException -> 0x0481, SocketTimeoutException -> 0x0479, UnknownHostException -> 0x0471, IllegalArgumentException -> 0x0469, FileNotFoundException -> 0x0461, IOException -> 0x0457, Throwable -> 0x044d, all -> 0x0445 }
            r7 = r3
            goto L_0x0393
        L_0x038f:
            r30 = r10
        L_0x0391:
            r7 = r29
        L_0x0393:
            r3 = 1
            r10 = r6[r3]     // Catch:{ MalformedURLException -> 0x0481, SocketTimeoutException -> 0x0479, UnknownHostException -> 0x0471, IllegalArgumentException -> 0x0469, FileNotFoundException -> 0x0461, IOException -> 0x0457, Throwable -> 0x044d, all -> 0x0445 }
            boolean r10 = android.text.TextUtils.isEmpty(r10)     // Catch:{ MalformedURLException -> 0x0481, SocketTimeoutException -> 0x0479, UnknownHostException -> 0x0471, IllegalArgumentException -> 0x0469, FileNotFoundException -> 0x0461, IOException -> 0x0457, Throwable -> 0x044d, all -> 0x0445 }
            if (r10 != 0) goto L_0x039e
            r26 = r6[r3]     // Catch:{ MalformedURLException -> 0x0481, SocketTimeoutException -> 0x0479, UnknownHostException -> 0x0471, IllegalArgumentException -> 0x0469, FileNotFoundException -> 0x0461, IOException -> 0x0457, Throwable -> 0x044d, all -> 0x0445 }
        L_0x039e:
            r3 = r26
            goto L_0x03a7
        L_0x03a1:
            r30 = r10
            r3 = r26
            r7 = r29
        L_0x03a7:
            if (r12 == 0) goto L_0x040e
            r12.d(r7)     // Catch:{ MalformedURLException -> 0x0481, SocketTimeoutException -> 0x0479, UnknownHostException -> 0x0471, IllegalArgumentException -> 0x0469, FileNotFoundException -> 0x0461, IOException -> 0x0457, Throwable -> 0x044d, all -> 0x0445 }
            r12.e(r3)     // Catch:{ MalformedURLException -> 0x0481, SocketTimeoutException -> 0x0479, UnknownHostException -> 0x0471, IllegalArgumentException -> 0x0469, FileNotFoundException -> 0x0461, IOException -> 0x0457, Throwable -> 0x044d, all -> 0x0445 }
            goto L_0x040e
        L_0x03b1:
            r0 = move-exception
            goto L_0x03c7
        L_0x03b3:
            r0 = move-exception
            goto L_0x03d1
        L_0x03b5:
            r0 = move-exception
            goto L_0x03db
        L_0x03b7:
            r0 = move-exception
            goto L_0x03e5
        L_0x03b9:
            r0 = move-exception
            goto L_0x03ef
        L_0x03bb:
            r0 = move-exception
            goto L_0x03f9
        L_0x03bd:
            r0 = move-exception
            goto L_0x0403
        L_0x03bf:
            r0 = move-exception
            r30 = r10
            goto L_0x028d
        L_0x03c4:
            r0 = move-exception
            r28 = r3
        L_0x03c7:
            r30 = r10
            r3 = r0
            r31 = r8
            goto L_0x0d52
        L_0x03ce:
            r0 = move-exception
            r28 = r3
        L_0x03d1:
            r30 = r10
            r3 = r0
            r31 = r8
            goto L_0x0d5d
        L_0x03d8:
            r0 = move-exception
            r28 = r3
        L_0x03db:
            r30 = r10
            r3 = r0
            r31 = r8
            goto L_0x0d68
        L_0x03e2:
            r0 = move-exception
            r28 = r3
        L_0x03e5:
            r30 = r10
            r3 = r0
            r31 = r8
            goto L_0x0d71
        L_0x03ec:
            r0 = move-exception
            r28 = r3
        L_0x03ef:
            r30 = r10
            r3 = r0
            r31 = r8
            goto L_0x0d7a
        L_0x03f6:
            r0 = move-exception
            r28 = r3
        L_0x03f9:
            r30 = r10
            r3 = r0
            r31 = r8
            goto L_0x0d83
        L_0x0400:
            r0 = move-exception
            r28 = r3
        L_0x0403:
            r30 = r10
            r3 = r0
            r31 = r8
            goto L_0x0d8c
        L_0x040a:
            r28 = r3
            r30 = r10
        L_0x040e:
            r3 = 200(0xc8, float:2.8E-43)
            if (r4 == r3) goto L_0x0489
            r3 = 206(0xce, float:2.89E-43)
            if (r4 != r3) goto L_0x0418
            goto L_0x0489
        L_0x0418:
            java.lang.String r3 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ MalformedURLException -> 0x0481, SocketTimeoutException -> 0x0479, UnknownHostException -> 0x0471, IllegalArgumentException -> 0x0469, FileNotFoundException -> 0x0461, IOException -> 0x0457, Throwable -> 0x044d, all -> 0x0445 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x0481, SocketTimeoutException -> 0x0479, UnknownHostException -> 0x0471, IllegalArgumentException -> 0x0469, FileNotFoundException -> 0x0461, IOException -> 0x0457, Throwable -> 0x044d, all -> 0x0445 }
            r6.<init>()     // Catch:{ MalformedURLException -> 0x0481, SocketTimeoutException -> 0x0479, UnknownHostException -> 0x0471, IllegalArgumentException -> 0x0469, FileNotFoundException -> 0x0461, IOException -> 0x0457, Throwable -> 0x044d, all -> 0x0445 }
            java.lang.String r7 = "HlsDownloadThread fail responseCode:"
            r6.append(r7)     // Catch:{ MalformedURLException -> 0x0481, SocketTimeoutException -> 0x0479, UnknownHostException -> 0x0471, IllegalArgumentException -> 0x0469, FileNotFoundException -> 0x0461, IOException -> 0x0457, Throwable -> 0x044d, all -> 0x0445 }
            r6.append(r4)     // Catch:{ MalformedURLException -> 0x0481, SocketTimeoutException -> 0x0479, UnknownHostException -> 0x0471, IllegalArgumentException -> 0x0469, FileNotFoundException -> 0x0461, IOException -> 0x0457, Throwable -> 0x044d, all -> 0x0445 }
            java.lang.String r6 = r6.toString()     // Catch:{ MalformedURLException -> 0x0481, SocketTimeoutException -> 0x0479, UnknownHostException -> 0x0471, IllegalArgumentException -> 0x0469, FileNotFoundException -> 0x0461, IOException -> 0x0457, Throwable -> 0x044d, all -> 0x0445 }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r3, (java.lang.Object) r6)     // Catch:{ MalformedURLException -> 0x0481, SocketTimeoutException -> 0x0479, UnknownHostException -> 0x0471, IllegalArgumentException -> 0x0469, FileNotFoundException -> 0x0461, IOException -> 0x0457, Throwable -> 0x044d, all -> 0x0445 }
            java.io.IOException r3 = new java.io.IOException     // Catch:{ MalformedURLException -> 0x0481, SocketTimeoutException -> 0x0479, UnknownHostException -> 0x0471, IllegalArgumentException -> 0x0469, FileNotFoundException -> 0x0461, IOException -> 0x0457, Throwable -> 0x044d, all -> 0x0445 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x0481, SocketTimeoutException -> 0x0479, UnknownHostException -> 0x0471, IllegalArgumentException -> 0x0469, FileNotFoundException -> 0x0461, IOException -> 0x0457, Throwable -> 0x044d, all -> 0x0445 }
            r6.<init>()     // Catch:{ MalformedURLException -> 0x0481, SocketTimeoutException -> 0x0479, UnknownHostException -> 0x0471, IllegalArgumentException -> 0x0469, FileNotFoundException -> 0x0461, IOException -> 0x0457, Throwable -> 0x044d, all -> 0x0445 }
            java.lang.String r7 = "HlsDownloadThread fail responseCode:"
            r6.append(r7)     // Catch:{ MalformedURLException -> 0x0481, SocketTimeoutException -> 0x0479, UnknownHostException -> 0x0471, IllegalArgumentException -> 0x0469, FileNotFoundException -> 0x0461, IOException -> 0x0457, Throwable -> 0x044d, all -> 0x0445 }
            r6.append(r4)     // Catch:{ MalformedURLException -> 0x0481, SocketTimeoutException -> 0x0479, UnknownHostException -> 0x0471, IllegalArgumentException -> 0x0469, FileNotFoundException -> 0x0461, IOException -> 0x0457, Throwable -> 0x044d, all -> 0x0445 }
            java.lang.String r4 = r6.toString()     // Catch:{ MalformedURLException -> 0x0481, SocketTimeoutException -> 0x0479, UnknownHostException -> 0x0471, IllegalArgumentException -> 0x0469, FileNotFoundException -> 0x0461, IOException -> 0x0457, Throwable -> 0x044d, all -> 0x0445 }
            r3.<init>(r4)     // Catch:{ MalformedURLException -> 0x0481, SocketTimeoutException -> 0x0479, UnknownHostException -> 0x0471, IllegalArgumentException -> 0x0469, FileNotFoundException -> 0x0461, IOException -> 0x0457, Throwable -> 0x044d, all -> 0x0445 }
            throw r3     // Catch:{ MalformedURLException -> 0x0481, SocketTimeoutException -> 0x0479, UnknownHostException -> 0x0471, IllegalArgumentException -> 0x0469, FileNotFoundException -> 0x0461, IOException -> 0x0457, Throwable -> 0x044d, all -> 0x0445 }
        L_0x0445:
            r0 = move-exception
            r3 = r0
            r31 = r8
        L_0x0449:
            r10 = r30
            goto L_0x0d49
        L_0x044d:
            r0 = move-exception
            r3 = r0
            r31 = r8
        L_0x0451:
            r8 = r17
            r10 = r30
            goto L_0x0d54
        L_0x0457:
            r0 = move-exception
            r3 = r0
            r31 = r8
        L_0x045b:
            r8 = r17
            r10 = r30
            goto L_0x0d5f
        L_0x0461:
            r0 = move-exception
            r3 = r0
            r31 = r8
        L_0x0465:
            r10 = r30
            goto L_0x0d68
        L_0x0469:
            r0 = move-exception
            r3 = r0
            r31 = r8
        L_0x046d:
            r10 = r30
            goto L_0x0d71
        L_0x0471:
            r0 = move-exception
            r3 = r0
            r31 = r8
        L_0x0475:
            r10 = r30
            goto L_0x0d7a
        L_0x0479:
            r0 = move-exception
            r3 = r0
            r31 = r8
        L_0x047d:
            r10 = r30
            goto L_0x0d83
        L_0x0481:
            r0 = move-exception
            r3 = r0
            r31 = r8
        L_0x0485:
            r10 = r30
            goto L_0x0d8c
        L_0x0489:
            int r3 = r11.getContentLength()     // Catch:{ MalformedURLException -> 0x0d14, SocketTimeoutException -> 0x0d0d, UnknownHostException -> 0x0d06, IllegalArgumentException -> 0x0cff, FileNotFoundException -> 0x0cf8, IOException -> 0x0cf1, Throwable -> 0x0cea, all -> 0x0ce3 }
            if (r12 == 0) goto L_0x0493
            long r6 = (long) r3
            r12.a((long) r6)     // Catch:{ MalformedURLException -> 0x0481, SocketTimeoutException -> 0x0479, UnknownHostException -> 0x0471, IllegalArgumentException -> 0x0469, FileNotFoundException -> 0x0461, IOException -> 0x0457, Throwable -> 0x044d, all -> 0x0445 }
        L_0x0493:
            if (r3 <= 0) goto L_0x0b42
            r4 = 262144(0x40000, float:3.67342E-40)
            if (r3 <= r4) goto L_0x049b
            goto L_0x0b42
        L_0x049b:
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ MalformedURLException -> 0x0b3c, SocketTimeoutException -> 0x0b36, UnknownHostException -> 0x0b30, IllegalArgumentException -> 0x0b2a, FileNotFoundException -> 0x0b24, IOException -> 0x0b1e, Throwable -> 0x0b18, all -> 0x0b12 }
            java.io.InputStream r6 = r11.getInputStream()     // Catch:{ MalformedURLException -> 0x0b06, SocketTimeoutException -> 0x0afa, UnknownHostException -> 0x0aee, IllegalArgumentException -> 0x0ae2, FileNotFoundException -> 0x0ad6, IOException -> 0x0ac8, Throwable -> 0x0aba, all -> 0x0b12 }
            java.lang.String r7 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ MalformedURLException -> 0x0aab, SocketTimeoutException -> 0x0a9c, UnknownHostException -> 0x0a8d, IllegalArgumentException -> 0x0a7e, FileNotFoundException -> 0x0a6f, IOException -> 0x0a5e, Throwable -> 0x0a4d, all -> 0x0a42 }
            java.lang.String r10 = "HlsDownloadThread 0"
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r7, (java.lang.Object) r10)     // Catch:{ MalformedURLException -> 0x0aab, SocketTimeoutException -> 0x0a9c, UnknownHostException -> 0x0a8d, IllegalArgumentException -> 0x0a7e, FileNotFoundException -> 0x0a6f, IOException -> 0x0a5e, Throwable -> 0x0a4d, all -> 0x0a42 }
            com.ximalaya.ting.android.player.BufferItem r7 = r1.d     // Catch:{ MalformedURLException -> 0x0aab, SocketTimeoutException -> 0x0a9c, UnknownHostException -> 0x0a8d, IllegalArgumentException -> 0x0a7e, FileNotFoundException -> 0x0a6f, IOException -> 0x0a5e, Throwable -> 0x0a4d, all -> 0x0a42 }
            if (r7 == 0) goto L_0x0515
            java.io.ByteArrayOutputStream r7 = new java.io.ByteArrayOutputStream     // Catch:{ MalformedURLException -> 0x0509, SocketTimeoutException -> 0x04fd, UnknownHostException -> 0x04f1, IllegalArgumentException -> 0x04e5, FileNotFoundException -> 0x04d9, IOException -> 0x04cb, Throwable -> 0x04bd, all -> 0x04b5 }
            r7.<init>()     // Catch:{ MalformedURLException -> 0x0509, SocketTimeoutException -> 0x04fd, UnknownHostException -> 0x04f1, IllegalArgumentException -> 0x04e5, FileNotFoundException -> 0x04d9, IOException -> 0x04cb, Throwable -> 0x04bd, all -> 0x04b5 }
            goto L_0x0516
        L_0x04b5:
            r0 = move-exception
            r3 = r0
            r33 = r6
            r31 = r8
            goto L_0x0a48
        L_0x04bd:
            r0 = move-exception
            r18 = r3
            r33 = r6
            r31 = r8
            r8 = r17
            r10 = r30
            r7 = 0
            goto L_0x030e
        L_0x04cb:
            r0 = move-exception
            r18 = r3
            r33 = r6
            r31 = r8
            r8 = r17
            r10 = r30
            r7 = 0
            goto L_0x031b
        L_0x04d9:
            r0 = move-exception
            r18 = r3
            r33 = r6
            r31 = r8
            r10 = r30
            r7 = 0
            goto L_0x0326
        L_0x04e5:
            r0 = move-exception
            r18 = r3
            r33 = r6
            r31 = r8
            r10 = r30
            r7 = 0
            goto L_0x0331
        L_0x04f1:
            r0 = move-exception
            r18 = r3
            r33 = r6
            r31 = r8
            r10 = r30
            r7 = 0
            goto L_0x033c
        L_0x04fd:
            r0 = move-exception
            r18 = r3
            r33 = r6
            r31 = r8
            r10 = r30
            r7 = 0
            goto L_0x0347
        L_0x0509:
            r0 = move-exception
            r18 = r3
            r33 = r6
            r31 = r8
            r10 = r30
            r7 = 0
            goto L_0x0352
        L_0x0515:
            r7 = 0
        L_0x0516:
            r10 = 1024(0x400, float:1.435E-42)
            byte[] r10 = new byte[r10]     // Catch:{ MalformedURLException -> 0x0a34, SocketTimeoutException -> 0x0a26, UnknownHostException -> 0x0a18, IllegalArgumentException -> 0x0a0a, FileNotFoundException -> 0x09fc, IOException -> 0x09ec, Throwable -> 0x09dc, all -> 0x09d2 }
            r31 = r8
            r8 = 0
        L_0x051d:
            int r9 = r6.read(r10)     // Catch:{ MalformedURLException -> 0x09cb, SocketTimeoutException -> 0x09c4, UnknownHostException -> 0x09bd, IllegalArgumentException -> 0x09b6, FileNotFoundException -> 0x09b0, IOException -> 0x09aa, Throwable -> 0x09a4, all -> 0x09a0 }
            r33 = r6
            r6 = -1
            if (r9 == r6) goto L_0x056d
            int r6 = r8 + r9
            if (r7 == 0) goto L_0x0567
            r8 = 0
            r7.write(r10, r8, r9)     // Catch:{ MalformedURLException -> 0x0560, SocketTimeoutException -> 0x0559, UnknownHostException -> 0x0552, IllegalArgumentException -> 0x054b, FileNotFoundException -> 0x0544, IOException -> 0x053b, Throwable -> 0x0532, all -> 0x052f }
            goto L_0x0567
        L_0x052f:
            r0 = move-exception
            goto L_0x09d7
        L_0x0532:
            r0 = move-exception
            r18 = r3
            r8 = r17
        L_0x0537:
            r10 = r30
            goto L_0x030e
        L_0x053b:
            r0 = move-exception
            r18 = r3
            r8 = r17
        L_0x0540:
            r10 = r30
            goto L_0x031b
        L_0x0544:
            r0 = move-exception
            r18 = r3
        L_0x0547:
            r10 = r30
            goto L_0x0326
        L_0x054b:
            r0 = move-exception
            r18 = r3
        L_0x054e:
            r10 = r30
            goto L_0x0331
        L_0x0552:
            r0 = move-exception
            r18 = r3
        L_0x0555:
            r10 = r30
            goto L_0x033c
        L_0x0559:
            r0 = move-exception
            r18 = r3
        L_0x055c:
            r10 = r30
            goto L_0x0347
        L_0x0560:
            r0 = move-exception
            r18 = r3
        L_0x0563:
            r10 = r30
            goto L_0x0352
        L_0x0567:
            r8 = r6
            r17 = r8
            r6 = r33
            goto L_0x051d
        L_0x056d:
            if (r12 == 0) goto L_0x067b
            if (r8 <= 0) goto L_0x05a0
            r6 = -1
            if (r9 != r6) goto L_0x05a0
            java.lang.String r6 = "success"
            r12.c(r6)     // Catch:{ MalformedURLException -> 0x059a, SocketTimeoutException -> 0x0594, UnknownHostException -> 0x058e, IllegalArgumentException -> 0x0588, FileNotFoundException -> 0x0582, IOException -> 0x057e, Throwable -> 0x057a, all -> 0x052f }
            goto L_0x05a5
        L_0x057a:
            r0 = move-exception
            r18 = r3
            goto L_0x0537
        L_0x057e:
            r0 = move-exception
            r18 = r3
            goto L_0x0540
        L_0x0582:
            r0 = move-exception
            r18 = r3
            r17 = r8
            goto L_0x0547
        L_0x0588:
            r0 = move-exception
            r18 = r3
            r17 = r8
            goto L_0x054e
        L_0x058e:
            r0 = move-exception
            r18 = r3
            r17 = r8
            goto L_0x0555
        L_0x0594:
            r0 = move-exception
            r18 = r3
            r17 = r8
            goto L_0x055c
        L_0x059a:
            r0 = move-exception
            r18 = r3
            r17 = r8
            goto L_0x0563
        L_0x05a0:
            java.lang.String r6 = "failed"
            r12.c(r6)     // Catch:{ MalformedURLException -> 0x0673, SocketTimeoutException -> 0x066b, UnknownHostException -> 0x0663, IllegalArgumentException -> 0x065b, FileNotFoundException -> 0x0653, IOException -> 0x064d, Throwable -> 0x0647, all -> 0x052f }
        L_0x05a5:
            long r9 = java.lang.System.currentTimeMillis()     // Catch:{ MalformedURLException -> 0x0673, SocketTimeoutException -> 0x066b, UnknownHostException -> 0x0663, IllegalArgumentException -> 0x065b, FileNotFoundException -> 0x0653, IOException -> 0x064d, Throwable -> 0x0647, all -> 0x052f }
            r6 = 0
            long r9 = r9 - r3
            r17 = 0
            int r6 = (r9 > r17 ? 1 : (r9 == r17 ? 0 : -1))
            if (r6 == 0) goto L_0x05c5
            float r6 = (float) r8
            r16 = 0
            float r6 = r6 + r16
            r17 = 1149239296(0x44800000, float:1024.0)
            float r6 = r6 / r17
            r34 = r3
            float r3 = (float) r9
            float r3 = r3 + r16
            r4 = 1148846080(0x447a0000, float:1000.0)
            float r3 = r3 / r4
            float r3 = r6 / r3
            goto L_0x05c9
        L_0x05c5:
            r34 = r3
            r3 = r30
        L_0x05c9:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x063f, SocketTimeoutException -> 0x0637, UnknownHostException -> 0x062f, IllegalArgumentException -> 0x0627, FileNotFoundException -> 0x061f, IOException -> 0x0619, Throwable -> 0x0613, all -> 0x060f }
            r4.<init>()     // Catch:{ MalformedURLException -> 0x063f, SocketTimeoutException -> 0x0637, UnknownHostException -> 0x062f, IllegalArgumentException -> 0x0627, FileNotFoundException -> 0x061f, IOException -> 0x0619, Throwable -> 0x0613, all -> 0x060f }
            r4.append(r8)     // Catch:{ MalformedURLException -> 0x063f, SocketTimeoutException -> 0x0637, UnknownHostException -> 0x062f, IllegalArgumentException -> 0x0627, FileNotFoundException -> 0x061f, IOException -> 0x0619, Throwable -> 0x0613, all -> 0x060f }
            java.lang.String r6 = ""
            r4.append(r6)     // Catch:{ MalformedURLException -> 0x063f, SocketTimeoutException -> 0x0637, UnknownHostException -> 0x062f, IllegalArgumentException -> 0x0627, FileNotFoundException -> 0x061f, IOException -> 0x0619, Throwable -> 0x0613, all -> 0x060f }
            java.lang.String r4 = r4.toString()     // Catch:{ MalformedURLException -> 0x063f, SocketTimeoutException -> 0x0637, UnknownHostException -> 0x062f, IllegalArgumentException -> 0x0627, FileNotFoundException -> 0x061f, IOException -> 0x0619, Throwable -> 0x0613, all -> 0x060f }
            r12.a((java.lang.String) r4)     // Catch:{ MalformedURLException -> 0x063f, SocketTimeoutException -> 0x0637, UnknownHostException -> 0x062f, IllegalArgumentException -> 0x0627, FileNotFoundException -> 0x061f, IOException -> 0x0619, Throwable -> 0x0613, all -> 0x060f }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x063f, SocketTimeoutException -> 0x0637, UnknownHostException -> 0x062f, IllegalArgumentException -> 0x0627, FileNotFoundException -> 0x061f, IOException -> 0x0619, Throwable -> 0x0613, all -> 0x060f }
            r4.<init>()     // Catch:{ MalformedURLException -> 0x063f, SocketTimeoutException -> 0x0637, UnknownHostException -> 0x062f, IllegalArgumentException -> 0x0627, FileNotFoundException -> 0x061f, IOException -> 0x0619, Throwable -> 0x0613, all -> 0x060f }
            r4.append(r9)     // Catch:{ MalformedURLException -> 0x063f, SocketTimeoutException -> 0x0637, UnknownHostException -> 0x062f, IllegalArgumentException -> 0x0627, FileNotFoundException -> 0x061f, IOException -> 0x0619, Throwable -> 0x0613, all -> 0x060f }
            java.lang.String r6 = ""
            r4.append(r6)     // Catch:{ MalformedURLException -> 0x063f, SocketTimeoutException -> 0x0637, UnknownHostException -> 0x062f, IllegalArgumentException -> 0x0627, FileNotFoundException -> 0x061f, IOException -> 0x0619, Throwable -> 0x0613, all -> 0x060f }
            java.lang.String r4 = r4.toString()     // Catch:{ MalformedURLException -> 0x063f, SocketTimeoutException -> 0x0637, UnknownHostException -> 0x062f, IllegalArgumentException -> 0x0627, FileNotFoundException -> 0x061f, IOException -> 0x0619, Throwable -> 0x0613, all -> 0x060f }
            r12.b((java.lang.String) r4)     // Catch:{ MalformedURLException -> 0x063f, SocketTimeoutException -> 0x0637, UnknownHostException -> 0x062f, IllegalArgumentException -> 0x0627, FileNotFoundException -> 0x061f, IOException -> 0x0619, Throwable -> 0x0613, all -> 0x060f }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x063f, SocketTimeoutException -> 0x0637, UnknownHostException -> 0x062f, IllegalArgumentException -> 0x0627, FileNotFoundException -> 0x061f, IOException -> 0x0619, Throwable -> 0x0613, all -> 0x060f }
            r4.<init>()     // Catch:{ MalformedURLException -> 0x063f, SocketTimeoutException -> 0x0637, UnknownHostException -> 0x062f, IllegalArgumentException -> 0x0627, FileNotFoundException -> 0x061f, IOException -> 0x0619, Throwable -> 0x0613, all -> 0x060f }
            r6 = 1
            float r9 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r3, (boolean) r6)     // Catch:{ MalformedURLException -> 0x063f, SocketTimeoutException -> 0x0637, UnknownHostException -> 0x062f, IllegalArgumentException -> 0x0627, FileNotFoundException -> 0x061f, IOException -> 0x0619, Throwable -> 0x0613, all -> 0x060f }
            r4.append(r9)     // Catch:{ MalformedURLException -> 0x063f, SocketTimeoutException -> 0x0637, UnknownHostException -> 0x062f, IllegalArgumentException -> 0x0627, FileNotFoundException -> 0x061f, IOException -> 0x0619, Throwable -> 0x0613, all -> 0x060f }
            java.lang.String r6 = ""
            r4.append(r6)     // Catch:{ MalformedURLException -> 0x063f, SocketTimeoutException -> 0x0637, UnknownHostException -> 0x062f, IllegalArgumentException -> 0x0627, FileNotFoundException -> 0x061f, IOException -> 0x0619, Throwable -> 0x0613, all -> 0x060f }
            java.lang.String r4 = r4.toString()     // Catch:{ MalformedURLException -> 0x063f, SocketTimeoutException -> 0x0637, UnknownHostException -> 0x062f, IllegalArgumentException -> 0x0627, FileNotFoundException -> 0x061f, IOException -> 0x0619, Throwable -> 0x0613, all -> 0x060f }
            r12.i(r4)     // Catch:{ MalformedURLException -> 0x063f, SocketTimeoutException -> 0x0637, UnknownHostException -> 0x062f, IllegalArgumentException -> 0x0627, FileNotFoundException -> 0x061f, IOException -> 0x0619, Throwable -> 0x0613, all -> 0x060f }
            r10 = r3
            r17 = r8
            goto L_0x067f
        L_0x060f:
            r0 = move-exception
            r10 = r3
            goto L_0x0809
        L_0x0613:
            r0 = move-exception
            r10 = r3
            r18 = r34
            goto L_0x030e
        L_0x0619:
            r0 = move-exception
            r10 = r3
            r18 = r34
            goto L_0x031b
        L_0x061f:
            r0 = move-exception
            r10 = r3
            r17 = r8
            r18 = r34
            goto L_0x0326
        L_0x0627:
            r0 = move-exception
            r10 = r3
            r17 = r8
            r18 = r34
            goto L_0x0331
        L_0x062f:
            r0 = move-exception
            r10 = r3
            r17 = r8
            r18 = r34
            goto L_0x033c
        L_0x0637:
            r0 = move-exception
            r10 = r3
            r17 = r8
            r18 = r34
            goto L_0x0347
        L_0x063f:
            r0 = move-exception
            r10 = r3
            r17 = r8
            r18 = r34
            goto L_0x0352
        L_0x0647:
            r0 = move-exception
            r34 = r3
            r3 = r0
            goto L_0x09e6
        L_0x064d:
            r0 = move-exception
            r34 = r3
            r3 = r0
            goto L_0x09f6
        L_0x0653:
            r0 = move-exception
            r34 = r3
            r3 = r0
            r17 = r8
            goto L_0x0a04
        L_0x065b:
            r0 = move-exception
            r34 = r3
            r3 = r0
            r17 = r8
            goto L_0x0a12
        L_0x0663:
            r0 = move-exception
            r34 = r3
            r3 = r0
            r17 = r8
            goto L_0x0a20
        L_0x066b:
            r0 = move-exception
            r34 = r3
            r3 = r0
            r17 = r8
            goto L_0x0a2e
        L_0x0673:
            r0 = move-exception
            r34 = r3
            r3 = r0
            r17 = r8
            goto L_0x0a3c
        L_0x067b:
            r34 = r3
            r10 = r30
        L_0x067f:
            if (r7 == 0) goto L_0x082c
            com.ximalaya.ting.android.player.BufferItem r3 = r1.d     // Catch:{ MalformedURLException -> 0x0828, SocketTimeoutException -> 0x0824, UnknownHostException -> 0x0820, IllegalArgumentException -> 0x081c, FileNotFoundException -> 0x0818, IOException -> 0x0812, Throwable -> 0x080c, all -> 0x0808 }
            if (r3 == 0) goto L_0x082c
            com.ximalaya.ting.android.player.BufferItem r3 = r1.d     // Catch:{ MalformedURLException -> 0x0828, SocketTimeoutException -> 0x0824, UnknownHostException -> 0x0820, IllegalArgumentException -> 0x081c, FileNotFoundException -> 0x0818, IOException -> 0x0812, Throwable -> 0x080c, all -> 0x0808 }
            byte[] r4 = r7.toByteArray()     // Catch:{ MalformedURLException -> 0x0828, SocketTimeoutException -> 0x0824, UnknownHostException -> 0x0820, IllegalArgumentException -> 0x081c, FileNotFoundException -> 0x0818, IOException -> 0x0812, Throwable -> 0x080c, all -> 0x0808 }
            r3.a((byte[]) r4)     // Catch:{ MalformedURLException -> 0x0828, SocketTimeoutException -> 0x0824, UnknownHostException -> 0x0820, IllegalArgumentException -> 0x081c, FileNotFoundException -> 0x0818, IOException -> 0x0812, Throwable -> 0x080c, all -> 0x0808 }
            com.ximalaya.ting.android.player.BufferItem r3 = r1.d     // Catch:{ MalformedURLException -> 0x0828, SocketTimeoutException -> 0x0824, UnknownHostException -> 0x0820, IllegalArgumentException -> 0x081c, FileNotFoundException -> 0x0818, IOException -> 0x0812, Throwable -> 0x080c, all -> 0x0808 }
            int r3 = r3.c()     // Catch:{ MalformedURLException -> 0x0828, SocketTimeoutException -> 0x0824, UnknownHostException -> 0x0820, IllegalArgumentException -> 0x081c, FileNotFoundException -> 0x0818, IOException -> 0x0812, Throwable -> 0x080c, all -> 0x0808 }
            if (r5 != 0) goto L_0x07dc
            if (r12 == 0) goto L_0x07dc
            java.lang.String r4 = r12.o()
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 == 0) goto L_0x06a6
            r4 = 0
            r12.m(r4)
        L_0x06a6:
            java.lang.String r4 = r12.n()
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 == 0) goto L_0x06b5
            java.lang.String r4 = ""
            r12.l(r4)
        L_0x06b5:
            long r4 = java.lang.System.currentTimeMillis()
            r12.b((long) r4)
            java.lang.String r4 = r12.c()
            if (r4 == 0) goto L_0x06ce
            java.lang.String r4 = r12.c()
            java.lang.String r5 = "success"
            boolean r4 = r4.contains(r5)
            if (r4 != 0) goto L_0x06d3
        L_0x06ce:
            java.lang.String r4 = "failed"
            r12.c(r4)
        L_0x06d3:
            if (r13 != 0) goto L_0x07dc
            int r4 = r2.f()
            r5 = -1
            if (r4 != r5) goto L_0x06de
            goto L_0x07dc
        L_0x06de:
            if (r4 != 0) goto L_0x075c
            int r4 = r2.h()
            long r4 = (long) r4
            r1.f2273a = r4
            int r2 = r2.j()
            long r4 = (long) r2
            r1.b = r4
            long r4 = r1.f2273a
            long r4 = r4 * r24
            int r2 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r2 <= 0) goto L_0x0726
            java.lang.String r2 = "cdn_connected_too_slow"
            r12.k(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "connected_time="
            r2.append(r4)
            float r4 = (float) r14
            r5 = 0
            float r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r4, (boolean) r5)
            r2.append(r4)
            java.lang.String r4 = "s, connected_time_threshold="
            r2.append(r4)
            long r4 = r1.f2273a
            r2.append(r4)
            java.lang.String r4 = "s"
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r12.j(r2)
            goto L_0x07dc
        L_0x0726:
            long r4 = r1.b
            float r2 = (float) r4
            int r2 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1))
            if (r2 <= 0) goto L_0x07dc
            java.lang.String r2 = "cdn_download_too_slow"
            r12.k(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "download_speed="
            r2.append(r4)
            r4 = 1
            float r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r10, (boolean) r4)
            r2.append(r4)
            java.lang.String r4 = "KB/s, download_speed_threshold="
            r2.append(r4)
            long r4 = r1.b
            r2.append(r4)
            java.lang.String r4 = "KB/s"
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r12.j(r2)
            goto L_0x07dc
        L_0x075c:
            r5 = 1
            if (r4 != r5) goto L_0x07dc
            int r4 = r2.g()
            long r4 = (long) r4
            r1.f2273a = r4
            int r2 = r2.i()
            long r4 = (long) r2
            r1.b = r4
            r4 = 0
            r1.f2273a = r4
            long r4 = r1.f2273a
            long r4 = r4 * r24
            int r2 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r2 <= 0) goto L_0x07a8
            java.lang.String r2 = "cdn_connected_too_slow"
            r12.k(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "connected_time="
            r2.append(r4)
            float r4 = (float) r14
            r5 = 0
            float r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r4, (boolean) r5)
            r2.append(r4)
            java.lang.String r4 = "s, connected_time_threshold="
            r2.append(r4)
            long r4 = r1.f2273a
            r2.append(r4)
            java.lang.String r4 = "s"
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r12.j(r2)
            goto L_0x07dc
        L_0x07a8:
            long r4 = r1.b
            float r2 = (float) r4
            int r2 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1))
            if (r2 <= 0) goto L_0x07dc
            java.lang.String r2 = "cdn_download_too_slow"
            r12.k(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "download_speed="
            r2.append(r4)
            r4 = 1
            float r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r10, (boolean) r4)
            r2.append(r4)
            java.lang.String r4 = "KB/s, download_speed_threshold="
            r2.append(r4)
            long r4 = r1.b
            r2.append(r4)
            java.lang.String r4 = "KB/s"
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r12.j(r2)
        L_0x07dc:
            if (r11 == 0) goto L_0x07e1
            r11.disconnect()
        L_0x07e1:
            if (r33 == 0) goto L_0x07e6
            r33.close()     // Catch:{ IOException -> 0x07e6 }
        L_0x07e6:
            if (r7 == 0) goto L_0x07eb
            r7.close()     // Catch:{ IOException -> 0x07eb }
        L_0x07eb:
            java.lang.String r2 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "DownloadThread hls readData end:"
            r4.append(r5)
            long r5 = java.lang.System.currentTimeMillis()
            long r5 = r5 - r31
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r2, (java.lang.Object) r4)
            return r3
        L_0x0808:
            r0 = move-exception
        L_0x0809:
            r3 = r0
            goto L_0x145a
        L_0x080c:
            r0 = move-exception
            r3 = r0
            r8 = r17
            goto L_0x09e8
        L_0x0812:
            r0 = move-exception
            r3 = r0
            r8 = r17
            goto L_0x09f8
        L_0x0818:
            r0 = move-exception
            r3 = r0
            goto L_0x0a06
        L_0x081c:
            r0 = move-exception
            r3 = r0
            goto L_0x0a14
        L_0x0820:
            r0 = move-exception
            r3 = r0
            goto L_0x0a22
        L_0x0824:
            r0 = move-exception
            r3 = r0
            goto L_0x0a30
        L_0x0828:
            r0 = move-exception
            r3 = r0
            goto L_0x0a3e
        L_0x082c:
            if (r5 != 0) goto L_0x0974
            if (r12 == 0) goto L_0x0974
            java.lang.String r3 = r12.o()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x083e
            r3 = 0
            r12.m(r3)
        L_0x083e:
            java.lang.String r3 = r12.n()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x084d
            java.lang.String r3 = ""
            r12.l(r3)
        L_0x084d:
            long r3 = java.lang.System.currentTimeMillis()
            r12.b((long) r3)
            java.lang.String r3 = r12.c()
            if (r3 == 0) goto L_0x0866
            java.lang.String r3 = r12.c()
            java.lang.String r4 = "success"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x086b
        L_0x0866:
            java.lang.String r3 = "failed"
            r12.c(r3)
        L_0x086b:
            if (r13 != 0) goto L_0x0974
            int r3 = r2.f()
            r4 = -1
            if (r3 != r4) goto L_0x0876
            goto L_0x0974
        L_0x0876:
            if (r3 != 0) goto L_0x08f4
            int r3 = r2.h()
            long r3 = (long) r3
            r1.f2273a = r3
            int r2 = r2.j()
            long r2 = (long) r2
            r1.b = r2
            long r2 = r1.f2273a
            long r2 = r2 * r24
            int r4 = (r14 > r2 ? 1 : (r14 == r2 ? 0 : -1))
            if (r4 <= 0) goto L_0x08be
            java.lang.String r2 = "cdn_connected_too_slow"
            r12.k(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "connected_time="
            r2.append(r3)
            float r3 = (float) r14
            r4 = 0
            float r3 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r3, (boolean) r4)
            r2.append(r3)
            java.lang.String r3 = "s, connected_time_threshold="
            r2.append(r3)
            long r3 = r1.f2273a
            r2.append(r3)
            java.lang.String r3 = "s"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r12.j(r2)
            goto L_0x0974
        L_0x08be:
            long r2 = r1.b
            float r2 = (float) r2
            int r2 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1))
            if (r2 <= 0) goto L_0x0974
            java.lang.String r2 = "cdn_download_too_slow"
            r12.k(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "download_speed="
            r2.append(r3)
            r3 = 1
            float r3 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r10, (boolean) r3)
            r2.append(r3)
            java.lang.String r3 = "KB/s, download_speed_threshold="
            r2.append(r3)
            long r3 = r1.b
            r2.append(r3)
            java.lang.String r3 = "KB/s"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r12.j(r2)
            goto L_0x0974
        L_0x08f4:
            r4 = 1
            if (r3 != r4) goto L_0x0974
            int r3 = r2.g()
            long r3 = (long) r3
            r1.f2273a = r3
            int r2 = r2.i()
            long r2 = (long) r2
            r1.b = r2
            r2 = 0
            r1.f2273a = r2
            long r2 = r1.f2273a
            long r2 = r2 * r24
            int r4 = (r14 > r2 ? 1 : (r14 == r2 ? 0 : -1))
            if (r4 <= 0) goto L_0x0940
            java.lang.String r2 = "cdn_connected_too_slow"
            r12.k(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "connected_time="
            r2.append(r3)
            float r3 = (float) r14
            r4 = 0
            float r3 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r3, (boolean) r4)
            r2.append(r3)
            java.lang.String r3 = "s, connected_time_threshold="
            r2.append(r3)
            long r3 = r1.f2273a
            r2.append(r3)
            java.lang.String r3 = "s"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r12.j(r2)
            goto L_0x0974
        L_0x0940:
            long r2 = r1.b
            float r2 = (float) r2
            int r2 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1))
            if (r2 <= 0) goto L_0x0974
            java.lang.String r2 = "cdn_download_too_slow"
            r12.k(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "download_speed="
            r2.append(r3)
            r3 = 1
            float r3 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r10, (boolean) r3)
            r2.append(r3)
            java.lang.String r3 = "KB/s, download_speed_threshold="
            r2.append(r3)
            long r3 = r1.b
            r2.append(r3)
            java.lang.String r3 = "KB/s"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r12.j(r2)
        L_0x0974:
            if (r11 == 0) goto L_0x0979
            r11.disconnect()
        L_0x0979:
            if (r33 == 0) goto L_0x097e
            r33.close()     // Catch:{ IOException -> 0x097e }
        L_0x097e:
            if (r7 == 0) goto L_0x0983
            r7.close()     // Catch:{ IOException -> 0x0983 }
        L_0x0983:
            java.lang.String r2 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "DownloadThread hls readData end:"
            r3.append(r4)
            long r4 = java.lang.System.currentTimeMillis()
            long r4 = r4 - r31
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r2, (java.lang.Object) r3)
            return r8
        L_0x09a0:
            r0 = move-exception
            r33 = r6
            goto L_0x09d7
        L_0x09a4:
            r0 = move-exception
            r34 = r3
            r33 = r6
            goto L_0x09e3
        L_0x09aa:
            r0 = move-exception
            r34 = r3
            r33 = r6
            goto L_0x09f3
        L_0x09b0:
            r0 = move-exception
            r34 = r3
            r33 = r6
            goto L_0x0a03
        L_0x09b6:
            r0 = move-exception
            r34 = r3
            r33 = r6
            goto L_0x0a11
        L_0x09bd:
            r0 = move-exception
            r34 = r3
            r33 = r6
            goto L_0x0a1f
        L_0x09c4:
            r0 = move-exception
            r34 = r3
            r33 = r6
            goto L_0x0a2d
        L_0x09cb:
            r0 = move-exception
            r34 = r3
            r33 = r6
            goto L_0x0a3b
        L_0x09d2:
            r0 = move-exception
            r33 = r6
            r31 = r8
        L_0x09d7:
            r3 = r0
            r10 = r30
            goto L_0x145a
        L_0x09dc:
            r0 = move-exception
            r34 = r3
            r33 = r6
            r31 = r8
        L_0x09e3:
            r3 = r0
            r8 = r17
        L_0x09e6:
            r10 = r30
        L_0x09e8:
            r18 = r34
            goto L_0x0dd5
        L_0x09ec:
            r0 = move-exception
            r34 = r3
            r33 = r6
            r31 = r8
        L_0x09f3:
            r3 = r0
            r8 = r17
        L_0x09f6:
            r10 = r30
        L_0x09f8:
            r18 = r34
            goto L_0x0f11
        L_0x09fc:
            r0 = move-exception
            r34 = r3
            r33 = r6
            r31 = r8
        L_0x0a03:
            r3 = r0
        L_0x0a04:
            r10 = r30
        L_0x0a06:
            r18 = r34
            goto L_0x109b
        L_0x0a0a:
            r0 = move-exception
            r34 = r3
            r33 = r6
            r31 = r8
        L_0x0a11:
            r3 = r0
        L_0x0a12:
            r10 = r30
        L_0x0a14:
            r18 = r34
            goto L_0x1153
        L_0x0a18:
            r0 = move-exception
            r34 = r3
            r33 = r6
            r31 = r8
        L_0x0a1f:
            r3 = r0
        L_0x0a20:
            r10 = r30
        L_0x0a22:
            r18 = r34
            goto L_0x11f1
        L_0x0a26:
            r0 = move-exception
            r34 = r3
            r33 = r6
            r31 = r8
        L_0x0a2d:
            r3 = r0
        L_0x0a2e:
            r10 = r30
        L_0x0a30:
            r18 = r34
            goto L_0x128f
        L_0x0a34:
            r0 = move-exception
            r34 = r3
            r33 = r6
            r31 = r8
        L_0x0a3b:
            r3 = r0
        L_0x0a3c:
            r10 = r30
        L_0x0a3e:
            r18 = r34
            goto L_0x1371
        L_0x0a42:
            r0 = move-exception
            r33 = r6
            r31 = r8
            r3 = r0
        L_0x0a48:
            r10 = r30
            r7 = 0
            goto L_0x145a
        L_0x0a4d:
            r0 = move-exception
            r34 = r3
            r33 = r6
            r31 = r8
            r3 = r0
            r8 = r17
            r10 = r30
            r18 = r34
            r7 = 0
            goto L_0x0dd5
        L_0x0a5e:
            r0 = move-exception
            r34 = r3
            r33 = r6
            r31 = r8
            r3 = r0
            r8 = r17
            r10 = r30
            r18 = r34
            r7 = 0
            goto L_0x0f11
        L_0x0a6f:
            r0 = move-exception
            r34 = r3
            r33 = r6
            r31 = r8
            r3 = r0
            r10 = r30
            r18 = r34
            r7 = 0
            goto L_0x109b
        L_0x0a7e:
            r0 = move-exception
            r34 = r3
            r33 = r6
            r31 = r8
            r3 = r0
            r10 = r30
            r18 = r34
            r7 = 0
            goto L_0x1153
        L_0x0a8d:
            r0 = move-exception
            r34 = r3
            r33 = r6
            r31 = r8
            r3 = r0
            r10 = r30
            r18 = r34
            r7 = 0
            goto L_0x11f1
        L_0x0a9c:
            r0 = move-exception
            r34 = r3
            r33 = r6
            r31 = r8
            r3 = r0
            r10 = r30
            r18 = r34
            r7 = 0
            goto L_0x128f
        L_0x0aab:
            r0 = move-exception
            r34 = r3
            r33 = r6
            r31 = r8
            r3 = r0
            r10 = r30
            r18 = r34
            r7 = 0
            goto L_0x1371
        L_0x0aba:
            r0 = move-exception
            r34 = r3
            r31 = r8
            r3 = r0
            r8 = r17
            r10 = r30
            r18 = r34
            goto L_0x0d54
        L_0x0ac8:
            r0 = move-exception
            r34 = r3
            r31 = r8
            r3 = r0
            r8 = r17
            r10 = r30
            r18 = r34
            goto L_0x0d5f
        L_0x0ad6:
            r0 = move-exception
            r34 = r3
            r31 = r8
            r3 = r0
            r10 = r30
            r18 = r34
            goto L_0x0d68
        L_0x0ae2:
            r0 = move-exception
            r34 = r3
            r31 = r8
            r3 = r0
            r10 = r30
            r18 = r34
            goto L_0x0d71
        L_0x0aee:
            r0 = move-exception
            r34 = r3
            r31 = r8
            r3 = r0
            r10 = r30
            r18 = r34
            goto L_0x0d7a
        L_0x0afa:
            r0 = move-exception
            r34 = r3
            r31 = r8
            r3 = r0
            r10 = r30
            r18 = r34
            goto L_0x0d83
        L_0x0b06:
            r0 = move-exception
            r34 = r3
            r31 = r8
            r3 = r0
            r10 = r30
            r18 = r34
            goto L_0x0d8c
        L_0x0b12:
            r0 = move-exception
            r31 = r8
            r3 = r0
            goto L_0x0449
        L_0x0b18:
            r0 = move-exception
            r31 = r8
            r3 = r0
            goto L_0x0451
        L_0x0b1e:
            r0 = move-exception
            r31 = r8
            r3 = r0
            goto L_0x045b
        L_0x0b24:
            r0 = move-exception
            r31 = r8
            r3 = r0
            goto L_0x0465
        L_0x0b2a:
            r0 = move-exception
            r31 = r8
            r3 = r0
            goto L_0x046d
        L_0x0b30:
            r0 = move-exception
            r31 = r8
            r3 = r0
            goto L_0x0475
        L_0x0b36:
            r0 = move-exception
            r31 = r8
            r3 = r0
            goto L_0x047d
        L_0x0b3c:
            r0 = move-exception
            r31 = r8
            r3 = r0
            goto L_0x0485
        L_0x0b42:
            r31 = r8
            java.lang.String r4 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ MalformedURLException -> 0x0ce1, SocketTimeoutException -> 0x0cdf, UnknownHostException -> 0x0cdd, IllegalArgumentException -> 0x0cdb, FileNotFoundException -> 0x0cd9, IOException -> 0x0cd7, Throwable -> 0x0cd5, all -> 0x0cd3 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x0ce1, SocketTimeoutException -> 0x0cdf, UnknownHostException -> 0x0cdd, IllegalArgumentException -> 0x0cdb, FileNotFoundException -> 0x0cd9, IOException -> 0x0cd7, Throwable -> 0x0cd5, all -> 0x0cd3 }
            r6.<init>()     // Catch:{ MalformedURLException -> 0x0ce1, SocketTimeoutException -> 0x0cdf, UnknownHostException -> 0x0cdd, IllegalArgumentException -> 0x0cdb, FileNotFoundException -> 0x0cd9, IOException -> 0x0cd7, Throwable -> 0x0cd5, all -> 0x0cd3 }
            java.lang.String r7 = "HlsDownloadThread fail contentLength:"
            r6.append(r7)     // Catch:{ MalformedURLException -> 0x0ce1, SocketTimeoutException -> 0x0cdf, UnknownHostException -> 0x0cdd, IllegalArgumentException -> 0x0cdb, FileNotFoundException -> 0x0cd9, IOException -> 0x0cd7, Throwable -> 0x0cd5, all -> 0x0cd3 }
            r6.append(r3)     // Catch:{ MalformedURLException -> 0x0ce1, SocketTimeoutException -> 0x0cdf, UnknownHostException -> 0x0cdd, IllegalArgumentException -> 0x0cdb, FileNotFoundException -> 0x0cd9, IOException -> 0x0cd7, Throwable -> 0x0cd5, all -> 0x0cd3 }
            java.lang.String r3 = r6.toString()     // Catch:{ MalformedURLException -> 0x0ce1, SocketTimeoutException -> 0x0cdf, UnknownHostException -> 0x0cdd, IllegalArgumentException -> 0x0cdb, FileNotFoundException -> 0x0cd9, IOException -> 0x0cd7, Throwable -> 0x0cd5, all -> 0x0cd3 }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r4, (java.lang.Object) r3)     // Catch:{ MalformedURLException -> 0x0ce1, SocketTimeoutException -> 0x0cdf, UnknownHostException -> 0x0cdd, IllegalArgumentException -> 0x0cdb, FileNotFoundException -> 0x0cd9, IOException -> 0x0cd7, Throwable -> 0x0cd5, all -> 0x0cd3 }
            if (r5 != 0) goto L_0x0caa
            if (r12 == 0) goto L_0x0caa
            java.lang.String r3 = r12.o()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x0b6c
            r3 = 0
            r12.m(r3)
        L_0x0b6c:
            java.lang.String r3 = r12.n()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x0b7b
            java.lang.String r3 = ""
            r12.l(r3)
        L_0x0b7b:
            long r3 = java.lang.System.currentTimeMillis()
            r12.b((long) r3)
            java.lang.String r3 = r12.c()
            if (r3 == 0) goto L_0x0b94
            java.lang.String r3 = r12.c()
            java.lang.String r4 = "success"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x0b99
        L_0x0b94:
            java.lang.String r3 = "failed"
            r12.c(r3)
        L_0x0b99:
            if (r13 != 0) goto L_0x0caa
            int r3 = r2.f()
            r4 = -1
            if (r3 != r4) goto L_0x0ba7
            r10 = r30
            r5 = 1
            goto L_0x0cac
        L_0x0ba7:
            if (r3 != 0) goto L_0x0c27
            int r3 = r2.h()
            long r3 = (long) r3
            r1.f2273a = r3
            int r3 = r2.j()
            long r3 = (long) r3
            r1.b = r3
            long r3 = r1.f2273a
            long r3 = r3 * r24
            int r6 = (r14 > r3 ? 1 : (r14 == r3 ? 0 : -1))
            if (r6 <= 0) goto L_0x0bef
            java.lang.String r3 = "cdn_connected_too_slow"
            r12.k(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "connected_time="
            r3.append(r4)
            float r4 = (float) r14
            r6 = 0
            float r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r4, (boolean) r6)
            r3.append(r4)
            java.lang.String r4 = "s, connected_time_threshold="
            r3.append(r4)
            long r6 = r1.f2273a
            r3.append(r6)
            java.lang.String r4 = "s"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r12.j(r3)
            goto L_0x0caa
        L_0x0bef:
            long r3 = r1.b
            float r3 = (float) r3
            int r3 = (r3 > r30 ? 1 : (r3 == r30 ? 0 : -1))
            if (r3 <= 0) goto L_0x0caa
            java.lang.String r3 = "cdn_download_too_slow"
            r12.k(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "download_speed="
            r3.append(r4)
            r10 = r30
            r4 = 1
            float r6 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r10, (boolean) r4)
            r3.append(r6)
            java.lang.String r4 = "KB/s, download_speed_threshold="
            r3.append(r4)
            long r6 = r1.b
            r3.append(r6)
            java.lang.String r4 = "KB/s"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r12.j(r3)
            goto L_0x0cac
        L_0x0c27:
            r10 = r30
            r4 = 1
            if (r3 != r4) goto L_0x0cac
            int r3 = r2.g()
            long r3 = (long) r3
            r1.f2273a = r3
            int r3 = r2.i()
            long r3 = (long) r3
            r1.b = r3
            r3 = 0
            r1.f2273a = r3
            long r3 = r1.f2273a
            long r3 = r3 * r24
            int r6 = (r14 > r3 ? 1 : (r14 == r3 ? 0 : -1))
            if (r6 <= 0) goto L_0x0c75
            java.lang.String r3 = "cdn_connected_too_slow"
            r12.k(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "connected_time="
            r3.append(r4)
            float r4 = (float) r14
            r6 = 0
            float r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r4, (boolean) r6)
            r3.append(r4)
            java.lang.String r4 = "s, connected_time_threshold="
            r3.append(r4)
            long r6 = r1.f2273a
            r3.append(r6)
            java.lang.String r4 = "s"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r12.j(r3)
            goto L_0x0cac
        L_0x0c75:
            long r3 = r1.b
            float r3 = (float) r3
            int r3 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r3 <= 0) goto L_0x0cac
            java.lang.String r3 = "cdn_download_too_slow"
            r12.k(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "download_speed="
            r3.append(r4)
            r4 = 1
            float r6 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r10, (boolean) r4)
            r3.append(r6)
            java.lang.String r4 = "KB/s, download_speed_threshold="
            r3.append(r4)
            long r6 = r1.b
            r3.append(r6)
            java.lang.String r4 = "KB/s"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r12.j(r3)
            goto L_0x0cac
        L_0x0caa:
            r10 = r30
        L_0x0cac:
            if (r11 == 0) goto L_0x0cb1
            r11.disconnect()
        L_0x0cb1:
            java.lang.String r3 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "DownloadThread hls readData end:"
            r4.append(r6)
            long r6 = java.lang.System.currentTimeMillis()
            long r6 = r6 - r31
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r3, (java.lang.Object) r4)
            r6 = r20
            r7 = r28
            goto L_0x0250
        L_0x0cd3:
            r0 = move-exception
            goto L_0x0ce6
        L_0x0cd5:
            r0 = move-exception
            goto L_0x0ced
        L_0x0cd7:
            r0 = move-exception
            goto L_0x0cf4
        L_0x0cd9:
            r0 = move-exception
            goto L_0x0cfb
        L_0x0cdb:
            r0 = move-exception
            goto L_0x0d02
        L_0x0cdd:
            r0 = move-exception
            goto L_0x0d09
        L_0x0cdf:
            r0 = move-exception
            goto L_0x0d10
        L_0x0ce1:
            r0 = move-exception
            goto L_0x0d17
        L_0x0ce3:
            r0 = move-exception
            r31 = r8
        L_0x0ce6:
            r10 = r30
            goto L_0x0d48
        L_0x0cea:
            r0 = move-exception
            r31 = r8
        L_0x0ced:
            r10 = r30
            goto L_0x0d51
        L_0x0cf1:
            r0 = move-exception
            r31 = r8
        L_0x0cf4:
            r10 = r30
            goto L_0x0d5c
        L_0x0cf8:
            r0 = move-exception
            r31 = r8
        L_0x0cfb:
            r10 = r30
            goto L_0x0d67
        L_0x0cff:
            r0 = move-exception
            r31 = r8
        L_0x0d02:
            r10 = r30
            goto L_0x0d70
        L_0x0d06:
            r0 = move-exception
            r31 = r8
        L_0x0d09:
            r10 = r30
            goto L_0x0d79
        L_0x0d0d:
            r0 = move-exception
            r31 = r8
        L_0x0d10:
            r10 = r30
            goto L_0x0d82
        L_0x0d14:
            r0 = move-exception
            r31 = r8
        L_0x0d17:
            r10 = r30
            goto L_0x0d8b
        L_0x0d1b:
            r0 = move-exception
            r28 = r3
            r31 = r8
            goto L_0x0d51
        L_0x0d21:
            r0 = move-exception
            r28 = r3
            r31 = r8
            goto L_0x0d5c
        L_0x0d27:
            r0 = move-exception
            r28 = r3
            r31 = r8
            goto L_0x0d67
        L_0x0d2d:
            r0 = move-exception
            r28 = r3
            r31 = r8
            goto L_0x0d70
        L_0x0d33:
            r0 = move-exception
            r28 = r3
            r31 = r8
            goto L_0x0d79
        L_0x0d39:
            r0 = move-exception
            r28 = r3
            r31 = r8
            goto L_0x0d82
        L_0x0d3f:
            r0 = move-exception
            r28 = r3
            r31 = r8
            goto L_0x0d8b
        L_0x0d45:
            r0 = move-exception
            r31 = r8
        L_0x0d48:
            r3 = r0
        L_0x0d49:
            r7 = 0
            goto L_0x0dc5
        L_0x0d4c:
            r0 = move-exception
            r31 = r8
            r28 = r26
        L_0x0d51:
            r3 = r0
        L_0x0d52:
            r8 = r17
        L_0x0d54:
            r7 = 0
            goto L_0x0dd3
        L_0x0d57:
            r0 = move-exception
            r31 = r8
            r28 = r26
        L_0x0d5c:
            r3 = r0
        L_0x0d5d:
            r8 = r17
        L_0x0d5f:
            r7 = 0
            goto L_0x0f0f
        L_0x0d62:
            r0 = move-exception
            r31 = r8
            r28 = r26
        L_0x0d67:
            r3 = r0
        L_0x0d68:
            r7 = 0
            goto L_0x1099
        L_0x0d6b:
            r0 = move-exception
            r31 = r8
            r28 = r26
        L_0x0d70:
            r3 = r0
        L_0x0d71:
            r7 = 0
            goto L_0x1151
        L_0x0d74:
            r0 = move-exception
            r31 = r8
            r28 = r26
        L_0x0d79:
            r3 = r0
        L_0x0d7a:
            r7 = 0
            goto L_0x11ef
        L_0x0d7d:
            r0 = move-exception
            r31 = r8
            r28 = r26
        L_0x0d82:
            r3 = r0
        L_0x0d83:
            r7 = 0
            goto L_0x128d
        L_0x0d86:
            r0 = move-exception
            r31 = r8
            r28 = r26
        L_0x0d8b:
            r3 = r0
        L_0x0d8c:
            r7 = 0
            goto L_0x136f
        L_0x0d8f:
            r0 = move-exception
            r31 = r8
            r28 = r26
            goto L_0x0dce
        L_0x0d95:
            r0 = move-exception
            r31 = r8
            r28 = r26
            goto L_0x0f0a
        L_0x0d9c:
            r0 = move-exception
            r31 = r8
            r28 = r26
            goto L_0x1096
        L_0x0da3:
            r0 = move-exception
            r31 = r8
            r28 = r26
            goto L_0x114e
        L_0x0daa:
            r0 = move-exception
            r31 = r8
            r28 = r26
            goto L_0x11ec
        L_0x0db1:
            r0 = move-exception
            r31 = r8
            r28 = r26
            goto L_0x128a
        L_0x0db8:
            r0 = move-exception
            r31 = r8
            r28 = r26
            goto L_0x136c
        L_0x0dbf:
            r0 = move-exception
            r31 = r8
            r3 = r0
        L_0x0dc3:
            r7 = 0
            r11 = 0
        L_0x0dc5:
            r33 = 0
            goto L_0x145a
        L_0x0dc9:
            r0 = move-exception
            r28 = r7
            r31 = r8
        L_0x0dce:
            r3 = r0
        L_0x0dcf:
            r8 = r17
            r7 = 0
            r11 = 0
        L_0x0dd3:
            r33 = 0
        L_0x0dd5:
            if (r5 != 0) goto L_0x0e81
            if (r12 == 0) goto L_0x0e81
            java.lang.String r4 = r12.k()     // Catch:{ all -> 0x0e8c }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x0e8c }
            if (r4 == 0) goto L_0x0e4f
            long r26 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0e8c }
            r4 = 0
            r36 = r10
            long r9 = r26 - r18
            r21 = 0
            int r4 = (r9 > r21 ? 1 : (r9 == r21 ? 0 : -1))
            if (r4 == 0) goto L_0x0dff
            float r4 = (float) r8
            r6 = 0
            float r4 = r4 + r6
            r13 = 1149239296(0x44800000, float:1024.0)
            float r4 = r4 / r13
            float r13 = (float) r9
            float r13 = r13 + r6
            r6 = 1148846080(0x447a0000, float:1000.0)
            float r13 = r13 / r6
            float r4 = r4 / r13
            goto L_0x0e01
        L_0x0dff:
            r4 = r36
        L_0x0e01:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0e4a }
            r6.<init>()     // Catch:{ all -> 0x0e4a }
            r37 = r14
            r13 = 1
            float r14 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r4, (boolean) r13)     // Catch:{ all -> 0x0e46 }
            r6.append(r14)     // Catch:{ all -> 0x0e46 }
            java.lang.String r13 = ""
            r6.append(r13)     // Catch:{ all -> 0x0e46 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0e46 }
            r12.i(r6)     // Catch:{ all -> 0x0e46 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0e46 }
            r6.<init>()     // Catch:{ all -> 0x0e46 }
            r6.append(r8)     // Catch:{ all -> 0x0e46 }
            java.lang.String r13 = ""
            r6.append(r13)     // Catch:{ all -> 0x0e46 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0e46 }
            r12.a((java.lang.String) r6)     // Catch:{ all -> 0x0e46 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0e46 }
            r6.<init>()     // Catch:{ all -> 0x0e46 }
            r6.append(r9)     // Catch:{ all -> 0x0e46 }
            java.lang.String r9 = ""
            r6.append(r9)     // Catch:{ all -> 0x0e46 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0e46 }
            r12.b((java.lang.String) r6)     // Catch:{ all -> 0x0e46 }
            r10 = r4
            goto L_0x0e53
        L_0x0e46:
            r0 = move-exception
            r3 = r0
            r10 = r4
            goto L_0x0e88
        L_0x0e4a:
            r0 = move-exception
            r37 = r14
            goto L_0x0f8a
        L_0x0e4f:
            r36 = r10
            r37 = r14
        L_0x0e53:
            float r4 = r12.p()     // Catch:{ all -> 0x0e86 }
            r6 = 0
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 > 0) goto L_0x0e6e
            long r13 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0e86 }
            r4 = 0
            long r13 = r13 - r31
            float r4 = (float) r13
            r6 = 0
            float r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r4, (boolean) r6)     // Catch:{ all -> 0x0fad }
            r12.a((float) r4)     // Catch:{ all -> 0x0fad }
            r14 = r13
            goto L_0x0e70
        L_0x0e6e:
            r14 = r37
        L_0x0e70:
            java.lang.String r4 = "failed"
            r12.a((java.lang.String) r4)     // Catch:{ all -> 0x1457 }
            java.lang.String r4 = "cdn_unknown_exception"
            r12.k(r4)     // Catch:{ all -> 0x1457 }
            java.lang.String r3 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.Throwable) r3)     // Catch:{ all -> 0x1457 }
            r12.j(r3)     // Catch:{ all -> 0x1457 }
        L_0x0e81:
            r36 = r10
            r37 = r14
            goto L_0x0e93
        L_0x0e86:
            r0 = move-exception
            r3 = r0
        L_0x0e88:
            r14 = r37
            goto L_0x1459
        L_0x0e8c:
            r0 = move-exception
            r36 = r10
            r37 = r14
            goto L_0x1458
        L_0x0e93:
            if (r5 != 0) goto L_0x0ed2
            if (r12 == 0) goto L_0x0ed2
            java.lang.String r3 = r12.o()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x0ea5
            r3 = 0
            r12.m(r3)
        L_0x0ea5:
            java.lang.String r3 = r12.n()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x0eb4
            java.lang.String r3 = ""
            r12.l(r3)
        L_0x0eb4:
            long r3 = java.lang.System.currentTimeMillis()
            r12.b((long) r3)
            java.lang.String r3 = r12.c()
            if (r3 == 0) goto L_0x0ecd
            java.lang.String r3 = r12.c()
            java.lang.String r4 = "success"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x0ed2
        L_0x0ecd:
            java.lang.String r3 = "failed"
            r12.c(r3)
        L_0x0ed2:
            if (r11 == 0) goto L_0x0ed7
            r11.disconnect()
        L_0x0ed7:
            if (r33 == 0) goto L_0x0edc
            r33.close()     // Catch:{ IOException -> 0x0edc }
        L_0x0edc:
            if (r7 == 0) goto L_0x0ee1
            r7.close()     // Catch:{ IOException -> 0x0ee1 }
        L_0x0ee1:
            java.lang.String r3 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "DownloadThread hls readData end:"
            r4.append(r6)
            long r6 = java.lang.System.currentTimeMillis()
            long r6 = r6 - r31
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r3, (java.lang.Object) r4)
            r17 = r8
            r10 = r36
            r14 = r37
            goto L_0x1364
        L_0x0f05:
            r0 = move-exception
            r28 = r7
            r31 = r8
        L_0x0f0a:
            r3 = r0
        L_0x0f0b:
            r8 = r17
            r7 = 0
            r11 = 0
        L_0x0f0f:
            r33 = 0
        L_0x0f11:
            if (r5 != 0) goto L_0x1001
            if (r12 == 0) goto L_0x1001
            java.lang.String r4 = r12.k()     // Catch:{ all -> 0x0ffa }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x0ffa }
            if (r4 == 0) goto L_0x0f8e
            long r26 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0ffa }
            r4 = 0
            r39 = r10
            long r9 = r26 - r18
            r21 = 0
            int r4 = (r9 > r21 ? 1 : (r9 == r21 ? 0 : -1))
            if (r4 == 0) goto L_0x0f3b
            float r4 = (float) r8
            r6 = 0
            float r4 = r4 + r6
            r13 = 1149239296(0x44800000, float:1024.0)
            float r4 = r4 / r13
            float r13 = (float) r9
            float r13 = r13 + r6
            r6 = 1148846080(0x447a0000, float:1000.0)
            float r13 = r13 / r6
            float r4 = r4 / r13
            goto L_0x0f3d
        L_0x0f3b:
            r4 = r39
        L_0x0f3d:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0f87 }
            r6.<init>()     // Catch:{ all -> 0x0f87 }
            r40 = r14
            r13 = 1
            float r14 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r4, (boolean) r13)     // Catch:{ all -> 0x0f82 }
            r6.append(r14)     // Catch:{ all -> 0x0f82 }
            java.lang.String r13 = ""
            r6.append(r13)     // Catch:{ all -> 0x0f82 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0f82 }
            r12.i(r6)     // Catch:{ all -> 0x0f82 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0f82 }
            r6.<init>()     // Catch:{ all -> 0x0f82 }
            r6.append(r8)     // Catch:{ all -> 0x0f82 }
            java.lang.String r13 = ""
            r6.append(r13)     // Catch:{ all -> 0x0f82 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0f82 }
            r12.a((java.lang.String) r6)     // Catch:{ all -> 0x0f82 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0f82 }
            r6.<init>()     // Catch:{ all -> 0x0f82 }
            r6.append(r9)     // Catch:{ all -> 0x0f82 }
            java.lang.String r9 = ""
            r6.append(r9)     // Catch:{ all -> 0x0f82 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0f82 }
            r12.b((java.lang.String) r6)     // Catch:{ all -> 0x0f82 }
            r10 = r4
            goto L_0x0f92
        L_0x0f82:
            r0 = move-exception
            r3 = r0
            r10 = r4
            goto L_0x0ff6
        L_0x0f87:
            r0 = move-exception
            r40 = r14
        L_0x0f8a:
            r3 = r0
            r10 = r4
            goto L_0x1459
        L_0x0f8e:
            r39 = r10
            r40 = r14
        L_0x0f92:
            float r4 = r12.p()     // Catch:{ all -> 0x0ff4 }
            r6 = 0
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 > 0) goto L_0x0fb2
            long r13 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0ff4 }
            r4 = 0
            long r13 = r13 - r31
            float r4 = (float) r13
            r6 = 0
            float r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r4, (boolean) r6)     // Catch:{ all -> 0x0fad }
            r12.a((float) r4)     // Catch:{ all -> 0x0fad }
            r14 = r13
            goto L_0x0fb4
        L_0x0fad:
            r0 = move-exception
            r3 = r0
            r14 = r13
            goto L_0x1459
        L_0x0fb2:
            r14 = r40
        L_0x0fb4:
            java.lang.String r4 = r3.getMessage()     // Catch:{ all -> 0x1457 }
            if (r4 == 0) goto L_0x0fe2
            java.lang.String r4 = r3.getMessage()     // Catch:{ all -> 0x1457 }
            java.lang.String r6 = "ENOSPC"
            boolean r4 = r4.contains(r6)     // Catch:{ all -> 0x1457 }
            if (r4 != 0) goto L_0x0fd2
            java.lang.String r4 = r3.getMessage()     // Catch:{ all -> 0x1457 }
            java.lang.String r6 = "EACCES"
            boolean r4 = r4.contains(r6)     // Catch:{ all -> 0x1457 }
            if (r4 == 0) goto L_0x0fe2
        L_0x0fd2:
            java.lang.String r4 = "0"
            r12.a((java.lang.String) r4)     // Catch:{ all -> 0x1457 }
            java.lang.String r4 = "0"
            r12.b((java.lang.String) r4)     // Catch:{ all -> 0x1457 }
            java.lang.String r4 = "system_exception"
            r12.k(r4)     // Catch:{ all -> 0x1457 }
            goto L_0x0fe7
        L_0x0fe2:
            java.lang.String r4 = "cdn_io_exception"
            r12.k(r4)     // Catch:{ all -> 0x1457 }
        L_0x0fe7:
            java.lang.String r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.Throwable) r3)     // Catch:{ all -> 0x1457 }
            r12.j(r4)     // Catch:{ all -> 0x1457 }
            java.lang.String r4 = "failed"
            r12.c(r4)     // Catch:{ all -> 0x1457 }
            goto L_0x1009
        L_0x0ff4:
            r0 = move-exception
            r3 = r0
        L_0x0ff6:
            r14 = r40
            goto L_0x1459
        L_0x0ffa:
            r0 = move-exception
            r39 = r10
            r40 = r14
            goto L_0x1458
        L_0x1001:
            r39 = r10
            r40 = r14
            r10 = r39
            r14 = r40
        L_0x1009:
            java.lang.String r4 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x1457 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x1457 }
            r6.<init>()     // Catch:{ all -> 0x1457 }
            java.lang.String r9 = "HlsDownloadThread IOException:"
            r6.append(r9)     // Catch:{ all -> 0x1457 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x1457 }
            r6.append(r3)     // Catch:{ all -> 0x1457 }
            java.lang.String r3 = r6.toString()     // Catch:{ all -> 0x1457 }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r4, (java.lang.Object) r3)     // Catch:{ all -> 0x1457 }
            if (r5 != 0) goto L_0x1062
            if (r12 == 0) goto L_0x1062
            java.lang.String r3 = r12.o()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x1035
            r3 = 0
            r12.m(r3)
        L_0x1035:
            java.lang.String r3 = r12.n()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x1044
            java.lang.String r3 = ""
            r12.l(r3)
        L_0x1044:
            long r3 = java.lang.System.currentTimeMillis()
            r12.b((long) r3)
            java.lang.String r3 = r12.c()
            if (r3 == 0) goto L_0x105d
            java.lang.String r3 = r12.c()
            java.lang.String r4 = "success"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x1062
        L_0x105d:
            java.lang.String r3 = "failed"
            r12.c(r3)
        L_0x1062:
            if (r11 == 0) goto L_0x1067
            r11.disconnect()
        L_0x1067:
            if (r33 == 0) goto L_0x106c
            r33.close()     // Catch:{ IOException -> 0x106c }
        L_0x106c:
            if (r7 == 0) goto L_0x1071
            r7.close()     // Catch:{ IOException -> 0x1071 }
        L_0x1071:
            java.lang.String r3 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "DownloadThread hls readData end:"
            r4.append(r6)
            long r6 = java.lang.System.currentTimeMillis()
            long r6 = r6 - r31
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r3, (java.lang.Object) r4)
            r17 = r8
            goto L_0x1364
        L_0x1091:
            r0 = move-exception
            r28 = r7
            r31 = r8
        L_0x1096:
            r3 = r0
        L_0x1097:
            r7 = 0
            r11 = 0
        L_0x1099:
            r33 = 0
        L_0x109b:
            if (r5 != 0) goto L_0x10d8
            if (r12 == 0) goto L_0x10d8
            float r4 = r12.p()     // Catch:{ all -> 0x1457 }
            r6 = 0
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 > 0) goto L_0x10b8
            long r8 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x1457 }
            r4 = 0
            long r14 = r8 - r31
            float r4 = (float) r14     // Catch:{ all -> 0x1457 }
            r6 = 0
            float r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r4, (boolean) r6)     // Catch:{ all -> 0x1457 }
            r12.a((float) r4)     // Catch:{ all -> 0x1457 }
        L_0x10b8:
            java.lang.String r4 = "system_exception"
            r12.k(r4)     // Catch:{ all -> 0x1457 }
            java.lang.String r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.Throwable) r3)     // Catch:{ all -> 0x1457 }
            r12.j(r4)     // Catch:{ all -> 0x1457 }
            java.lang.String r4 = "0.0"
            r12.i(r4)     // Catch:{ all -> 0x1457 }
            java.lang.String r4 = "0"
            r12.a((java.lang.String) r4)     // Catch:{ all -> 0x1457 }
            java.lang.String r4 = "0"
            r12.b((java.lang.String) r4)     // Catch:{ all -> 0x1457 }
            java.lang.String r4 = "failed"
            r12.c(r4)     // Catch:{ all -> 0x1457 }
        L_0x10d8:
            java.lang.String r4 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x1457 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x1457 }
            r6.<init>()     // Catch:{ all -> 0x1457 }
            java.lang.String r8 = "DownloadThread IOException:"
            r6.append(r8)     // Catch:{ all -> 0x1457 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x1457 }
            r6.append(r3)     // Catch:{ all -> 0x1457 }
            java.lang.String r3 = r6.toString()     // Catch:{ all -> 0x1457 }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r4, (java.lang.Object) r3)     // Catch:{ all -> 0x1457 }
            if (r5 != 0) goto L_0x1131
            if (r12 == 0) goto L_0x1131
            java.lang.String r3 = r12.o()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x1104
            r3 = 0
            r12.m(r3)
        L_0x1104:
            java.lang.String r3 = r12.n()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x1113
            java.lang.String r3 = ""
            r12.l(r3)
        L_0x1113:
            long r3 = java.lang.System.currentTimeMillis()
            r12.b((long) r3)
            java.lang.String r3 = r12.c()
            if (r3 == 0) goto L_0x112c
            java.lang.String r3 = r12.c()
            java.lang.String r4 = "success"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x1131
        L_0x112c:
            java.lang.String r3 = "failed"
            r12.c(r3)
        L_0x1131:
            if (r11 == 0) goto L_0x1136
            r11.disconnect()
        L_0x1136:
            if (r33 == 0) goto L_0x113b
            r33.close()     // Catch:{ IOException -> 0x113b }
        L_0x113b:
            if (r7 == 0) goto L_0x1140
            r7.close()     // Catch:{ IOException -> 0x1140 }
        L_0x1140:
            java.lang.String r3 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            goto L_0x134f
        L_0x1149:
            r0 = move-exception
            r28 = r7
            r31 = r8
        L_0x114e:
            r3 = r0
        L_0x114f:
            r7 = 0
            r11 = 0
        L_0x1151:
            r33 = 0
        L_0x1153:
            if (r5 != 0) goto L_0x1190
            if (r12 == 0) goto L_0x1190
            float r4 = r12.p()     // Catch:{ all -> 0x1457 }
            r6 = 0
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 > 0) goto L_0x1170
            long r8 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x1457 }
            r4 = 0
            long r14 = r8 - r31
            float r4 = (float) r14     // Catch:{ all -> 0x1457 }
            r6 = 0
            float r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r4, (boolean) r6)     // Catch:{ all -> 0x1457 }
            r12.a((float) r4)     // Catch:{ all -> 0x1457 }
        L_0x1170:
            java.lang.String r4 = "dns_fail"
            r12.k(r4)     // Catch:{ all -> 0x1457 }
            java.lang.String r3 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.Throwable) r3)     // Catch:{ all -> 0x1457 }
            r12.j(r3)     // Catch:{ all -> 0x1457 }
            java.lang.String r3 = "0.0"
            r12.i(r3)     // Catch:{ all -> 0x1457 }
            java.lang.String r3 = "0"
            r12.a((java.lang.String) r3)     // Catch:{ all -> 0x1457 }
            java.lang.String r3 = "0"
            r12.b((java.lang.String) r3)     // Catch:{ all -> 0x1457 }
            java.lang.String r3 = "failed"
            r12.c(r3)     // Catch:{ all -> 0x1457 }
        L_0x1190:
            if (r5 != 0) goto L_0x11cf
            if (r12 == 0) goto L_0x11cf
            java.lang.String r3 = r12.o()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x11a2
            r3 = 0
            r12.m(r3)
        L_0x11a2:
            java.lang.String r3 = r12.n()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x11b1
            java.lang.String r3 = ""
            r12.l(r3)
        L_0x11b1:
            long r3 = java.lang.System.currentTimeMillis()
            r12.b((long) r3)
            java.lang.String r3 = r12.c()
            if (r3 == 0) goto L_0x11ca
            java.lang.String r3 = r12.c()
            java.lang.String r4 = "success"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x11cf
        L_0x11ca:
            java.lang.String r3 = "failed"
            r12.c(r3)
        L_0x11cf:
            if (r11 == 0) goto L_0x11d4
            r11.disconnect()
        L_0x11d4:
            if (r33 == 0) goto L_0x11d9
            r33.close()     // Catch:{ IOException -> 0x11d9 }
        L_0x11d9:
            if (r7 == 0) goto L_0x11de
            r7.close()     // Catch:{ IOException -> 0x11de }
        L_0x11de:
            java.lang.String r3 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            goto L_0x134f
        L_0x11e7:
            r0 = move-exception
            r28 = r7
            r31 = r8
        L_0x11ec:
            r3 = r0
        L_0x11ed:
            r7 = 0
            r11 = 0
        L_0x11ef:
            r33 = 0
        L_0x11f1:
            if (r5 != 0) goto L_0x122e
            if (r12 == 0) goto L_0x122e
            float r4 = r12.p()     // Catch:{ all -> 0x1457 }
            r6 = 0
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 > 0) goto L_0x120e
            long r8 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x1457 }
            r4 = 0
            long r14 = r8 - r31
            float r4 = (float) r14     // Catch:{ all -> 0x1457 }
            r6 = 0
            float r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r4, (boolean) r6)     // Catch:{ all -> 0x1457 }
            r12.a((float) r4)     // Catch:{ all -> 0x1457 }
        L_0x120e:
            java.lang.String r4 = "dns_fail"
            r12.k(r4)     // Catch:{ all -> 0x1457 }
            java.lang.String r3 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.Throwable) r3)     // Catch:{ all -> 0x1457 }
            r12.j(r3)     // Catch:{ all -> 0x1457 }
            java.lang.String r3 = "0.0"
            r12.i(r3)     // Catch:{ all -> 0x1457 }
            java.lang.String r3 = "0"
            r12.a((java.lang.String) r3)     // Catch:{ all -> 0x1457 }
            java.lang.String r3 = "0"
            r12.b((java.lang.String) r3)     // Catch:{ all -> 0x1457 }
            java.lang.String r3 = "failed"
            r12.c(r3)     // Catch:{ all -> 0x1457 }
        L_0x122e:
            if (r5 != 0) goto L_0x126d
            if (r12 == 0) goto L_0x126d
            java.lang.String r3 = r12.o()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x1240
            r3 = 0
            r12.m(r3)
        L_0x1240:
            java.lang.String r3 = r12.n()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x124f
            java.lang.String r3 = ""
            r12.l(r3)
        L_0x124f:
            long r3 = java.lang.System.currentTimeMillis()
            r12.b((long) r3)
            java.lang.String r3 = r12.c()
            if (r3 == 0) goto L_0x1268
            java.lang.String r3 = r12.c()
            java.lang.String r4 = "success"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x126d
        L_0x1268:
            java.lang.String r3 = "failed"
            r12.c(r3)
        L_0x126d:
            if (r11 == 0) goto L_0x1272
            r11.disconnect()
        L_0x1272:
            if (r33 == 0) goto L_0x1277
            r33.close()     // Catch:{ IOException -> 0x1277 }
        L_0x1277:
            if (r7 == 0) goto L_0x127c
            r7.close()     // Catch:{ IOException -> 0x127c }
        L_0x127c:
            java.lang.String r3 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            goto L_0x134f
        L_0x1285:
            r0 = move-exception
            r28 = r7
            r31 = r8
        L_0x128a:
            r3 = r0
        L_0x128b:
            r7 = 0
            r11 = 0
        L_0x128d:
            r33 = 0
        L_0x128f:
            if (r5 != 0) goto L_0x12fa
            if (r12 == 0) goto L_0x12fa
            java.lang.String r4 = "0.0"
            r12.i(r4)     // Catch:{ all -> 0x1457 }
            float r4 = r12.p()     // Catch:{ all -> 0x1457 }
            r6 = 0
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 > 0) goto L_0x12b1
            long r8 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x1457 }
            r4 = 0
            long r14 = r8 - r31
            float r4 = (float) r14     // Catch:{ all -> 0x1457 }
            r6 = 0
            float r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r4, (boolean) r6)     // Catch:{ all -> 0x1457 }
            r12.a((float) r4)     // Catch:{ all -> 0x1457 }
        L_0x12b1:
            java.lang.String r4 = r3.getMessage()     // Catch:{ all -> 0x1457 }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x1457 }
            if (r4 == 0) goto L_0x12db
            java.lang.String r4 = "cdn_socket_timeout"
            r12.k(r4)     // Catch:{ all -> 0x1457 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x1457 }
            r4.<init>()     // Catch:{ all -> 0x1457 }
            java.lang.String r6 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x1457 }
            r4.append(r6)     // Catch:{ all -> 0x1457 }
            java.lang.String r3 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.Throwable) r3)     // Catch:{ all -> 0x1457 }
            r4.append(r3)     // Catch:{ all -> 0x1457 }
            java.lang.String r3 = r4.toString()     // Catch:{ all -> 0x1457 }
            r12.j(r3)     // Catch:{ all -> 0x1457 }
            goto L_0x12e7
        L_0x12db:
            java.lang.String r4 = "cdn_connect_timeout"
            r12.k(r4)     // Catch:{ all -> 0x1457 }
            java.lang.String r3 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.Throwable) r3)     // Catch:{ all -> 0x1457 }
            r12.j(r3)     // Catch:{ all -> 0x1457 }
        L_0x12e7:
            java.lang.String r3 = "0"
            r12.a((java.lang.String) r3)     // Catch:{ all -> 0x1457 }
            java.lang.String r3 = "0"
            r12.b((java.lang.String) r3)     // Catch:{ all -> 0x1457 }
            java.lang.String r3 = "failed"
            r12.c(r3)     // Catch:{ all -> 0x1457 }
            r3 = 1
            r12.a((boolean) r3)     // Catch:{ all -> 0x1457 }
        L_0x12fa:
            if (r5 != 0) goto L_0x1339
            if (r12 == 0) goto L_0x1339
            java.lang.String r3 = r12.o()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x130c
            r3 = 0
            r12.m(r3)
        L_0x130c:
            java.lang.String r3 = r12.n()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x131b
            java.lang.String r3 = ""
            r12.l(r3)
        L_0x131b:
            long r3 = java.lang.System.currentTimeMillis()
            r12.b((long) r3)
            java.lang.String r3 = r12.c()
            if (r3 == 0) goto L_0x1334
            java.lang.String r3 = r12.c()
            java.lang.String r4 = "success"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x1339
        L_0x1334:
            java.lang.String r3 = "failed"
            r12.c(r3)
        L_0x1339:
            if (r11 == 0) goto L_0x133e
            r11.disconnect()
        L_0x133e:
            if (r33 == 0) goto L_0x1343
            r33.close()     // Catch:{ IOException -> 0x1343 }
        L_0x1343:
            if (r7 == 0) goto L_0x1348
            r7.close()     // Catch:{ IOException -> 0x1348 }
        L_0x1348:
            java.lang.String r3 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
        L_0x134f:
            java.lang.String r6 = "DownloadThread hls readData end:"
            r4.append(r6)
            long r6 = java.lang.System.currentTimeMillis()
            long r6 = r6 - r31
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r3, (java.lang.Object) r4)
        L_0x1364:
            r6 = 0
            goto L_0x1434
        L_0x1367:
            r0 = move-exception
            r28 = r7
            r31 = r8
        L_0x136c:
            r3 = r0
        L_0x136d:
            r7 = 0
            r11 = 0
        L_0x136f:
            r33 = 0
        L_0x1371:
            if (r5 != 0) goto L_0x13af
            if (r12 == 0) goto L_0x13af
            float r4 = r12.p()     // Catch:{ all -> 0x1457 }
            r6 = 0
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 > 0) goto L_0x138e
            long r8 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x1457 }
            r4 = 0
            long r14 = r8 - r31
            float r4 = (float) r14     // Catch:{ all -> 0x1457 }
            r8 = 0
            float r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r4, (boolean) r8)     // Catch:{ all -> 0x1457 }
            r12.a((float) r4)     // Catch:{ all -> 0x1457 }
        L_0x138e:
            java.lang.String r4 = "cdn_connect_fail "
            r12.k(r4)     // Catch:{ all -> 0x1457 }
            java.lang.String r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.Throwable) r3)     // Catch:{ all -> 0x1457 }
            r12.j(r4)     // Catch:{ all -> 0x1457 }
            java.lang.String r4 = "0.0"
            r12.i(r4)     // Catch:{ all -> 0x1457 }
            java.lang.String r4 = "0"
            r12.a((java.lang.String) r4)     // Catch:{ all -> 0x1457 }
            java.lang.String r4 = "0"
            r12.b((java.lang.String) r4)     // Catch:{ all -> 0x1457 }
            java.lang.String r4 = "failed"
            r12.c(r4)     // Catch:{ all -> 0x1457 }
            goto L_0x13b0
        L_0x13af:
            r6 = 0
        L_0x13b0:
            java.lang.String r4 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x1457 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x1457 }
            r8.<init>()     // Catch:{ all -> 0x1457 }
            java.lang.String r9 = "HlsDownloadThread MalformedURLException:"
            r8.append(r9)     // Catch:{ all -> 0x1457 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x1457 }
            r8.append(r3)     // Catch:{ all -> 0x1457 }
            java.lang.String r3 = r8.toString()     // Catch:{ all -> 0x1457 }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r4, (java.lang.Object) r3)     // Catch:{ all -> 0x1457 }
            if (r5 != 0) goto L_0x1409
            if (r12 == 0) goto L_0x1409
            java.lang.String r3 = r12.o()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x13dc
            r3 = 0
            r12.m(r3)
        L_0x13dc:
            java.lang.String r3 = r12.n()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x13eb
            java.lang.String r3 = ""
            r12.l(r3)
        L_0x13eb:
            long r3 = java.lang.System.currentTimeMillis()
            r12.b((long) r3)
            java.lang.String r3 = r12.c()
            if (r3 == 0) goto L_0x1404
            java.lang.String r3 = r12.c()
            java.lang.String r4 = "success"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x1409
        L_0x1404:
            java.lang.String r3 = "failed"
            r12.c(r3)
        L_0x1409:
            if (r11 == 0) goto L_0x140e
            r11.disconnect()
        L_0x140e:
            if (r33 == 0) goto L_0x1413
            r33.close()     // Catch:{ IOException -> 0x1413 }
        L_0x1413:
            if (r7 == 0) goto L_0x1418
            r7.close()     // Catch:{ IOException -> 0x1418 }
        L_0x1418:
            java.lang.String r3 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r7 = "DownloadThread hls readData end:"
            r4.append(r7)
            long r7 = java.lang.System.currentTimeMillis()
            long r7 = r7 - r31
            r4.append(r7)
            java.lang.String r4 = r4.toString()
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r3, (java.lang.Object) r4)
        L_0x1434:
            if (r12 == 0) goto L_0x144f
            if (r5 != 0) goto L_0x144f
            java.lang.String r3 = r12.m()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x144f
            java.lang.String r3 = r12.l()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x144f
            com.ximalaya.ting.android.player.cdn.CdnUtil.a((com.ximalaya.ting.android.player.cdn.CdnCollectDataForPlay) r12, (com.ximalaya.ting.android.opensdk.model.xdcs.CdnConfigModel) r2)
        L_0x144f:
            r6 = r20
            r7 = r28
            r4 = 1
            r13 = 1
            goto L_0x001e
        L_0x1457:
            r0 = move-exception
        L_0x1458:
            r3 = r0
        L_0x1459:
            r13 = 1
        L_0x145a:
            if (r5 != 0) goto L_0x15a2
            if (r12 == 0) goto L_0x15a2
            java.lang.String r4 = r12.o()
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 == 0) goto L_0x146c
            r4 = 0
            r12.m(r4)
        L_0x146c:
            java.lang.String r4 = r12.n()
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 == 0) goto L_0x147b
            java.lang.String r4 = ""
            r12.l(r4)
        L_0x147b:
            long r4 = java.lang.System.currentTimeMillis()
            r12.b((long) r4)
            java.lang.String r4 = r12.c()
            if (r4 == 0) goto L_0x1494
            java.lang.String r4 = r12.c()
            java.lang.String r5 = "success"
            boolean r4 = r4.contains(r5)
            if (r4 != 0) goto L_0x1499
        L_0x1494:
            java.lang.String r4 = "failed"
            r12.c(r4)
        L_0x1499:
            if (r13 != 0) goto L_0x15a2
            int r4 = r2.f()
            r5 = -1
            if (r4 != r5) goto L_0x14a4
            goto L_0x15a2
        L_0x14a4:
            if (r4 != 0) goto L_0x1522
            int r4 = r2.h()
            long r4 = (long) r4
            r1.f2273a = r4
            int r2 = r2.j()
            long r4 = (long) r2
            r1.b = r4
            long r4 = r1.f2273a
            long r4 = r4 * r24
            int r2 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r2 <= 0) goto L_0x14ec
            java.lang.String r2 = "cdn_connected_too_slow"
            r12.k(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "connected_time="
            r2.append(r4)
            float r4 = (float) r14
            r5 = 0
            float r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r4, (boolean) r5)
            r2.append(r4)
            java.lang.String r4 = "s, connected_time_threshold="
            r2.append(r4)
            long r4 = r1.f2273a
            r2.append(r4)
            java.lang.String r4 = "s"
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r12.j(r2)
            goto L_0x15a2
        L_0x14ec:
            long r4 = r1.b
            float r2 = (float) r4
            int r2 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1))
            if (r2 <= 0) goto L_0x15a2
            java.lang.String r2 = "cdn_download_too_slow"
            r12.k(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "download_speed="
            r2.append(r4)
            r4 = 1
            float r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r10, (boolean) r4)
            r2.append(r4)
            java.lang.String r4 = "KB/s, download_speed_threshold="
            r2.append(r4)
            long r4 = r1.b
            r2.append(r4)
            java.lang.String r4 = "KB/s"
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r12.j(r2)
            goto L_0x15a2
        L_0x1522:
            r5 = 1
            if (r4 != r5) goto L_0x15a2
            int r4 = r2.g()
            long r4 = (long) r4
            r1.f2273a = r4
            int r2 = r2.i()
            long r4 = (long) r2
            r1.b = r4
            r4 = 0
            r1.f2273a = r4
            long r4 = r1.f2273a
            long r4 = r4 * r24
            int r2 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r2 <= 0) goto L_0x156e
            java.lang.String r2 = "cdn_connected_too_slow"
            r12.k(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "connected_time="
            r2.append(r4)
            float r4 = (float) r14
            r5 = 0
            float r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r4, (boolean) r5)
            r2.append(r4)
            java.lang.String r4 = "s, connected_time_threshold="
            r2.append(r4)
            long r4 = r1.f2273a
            r2.append(r4)
            java.lang.String r4 = "s"
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r12.j(r2)
            goto L_0x15a2
        L_0x156e:
            long r4 = r1.b
            float r2 = (float) r4
            int r2 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1))
            if (r2 <= 0) goto L_0x15a2
            java.lang.String r2 = "cdn_download_too_slow"
            r12.k(r2)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "download_speed="
            r2.append(r4)
            r4 = 1
            float r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r10, (boolean) r4)
            r2.append(r4)
            java.lang.String r4 = "KB/s, download_speed_threshold="
            r2.append(r4)
            long r4 = r1.b
            r2.append(r4)
            java.lang.String r4 = "KB/s"
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r12.j(r2)
        L_0x15a2:
            if (r11 == 0) goto L_0x15a7
            r11.disconnect()
        L_0x15a7:
            if (r33 == 0) goto L_0x15ac
            r33.close()     // Catch:{ IOException -> 0x15ac }
        L_0x15ac:
            if (r7 == 0) goto L_0x15b1
            r7.close()     // Catch:{ IOException -> 0x15b1 }
        L_0x15b1:
            java.lang.String r2 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "DownloadThread hls readData end:"
            r4.append(r5)
            long r5 = java.lang.System.currentTimeMillis()
            long r5 = r5 - r31
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r2, (java.lang.Object) r4)
            throw r3
        L_0x15ce:
            r2 = -1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.player.HlsDownloadThread.a():int");
    }
}
