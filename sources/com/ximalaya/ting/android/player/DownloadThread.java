package com.ximalaya.ting.android.player;

import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;

public class DownloadThread {

    /* renamed from: a  reason: collision with root package name */
    public static long f2270a = 0;
    private static final String d = "dl_mp3";
    private long b;
    private long c;
    private AudioFile e;
    private int f;
    private ByteBuffer g = ByteBuffer.allocate(65536);
    private boolean h = false;

    public DownloadThread(AudioFile audioFile, int i) {
        Logger.a(d, (Object) "======================DownloadThread Constructor(" + i + Operators.BRACKET_END_STR);
        this.e = audioFile;
        this.f = i;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v0, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v1, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v4, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v2, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v3, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v6, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v4, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v5, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v6, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v7, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v7, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v8, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v9, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v8, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v10, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v9, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v11, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v10, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v12, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v11, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v12, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v13, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v14, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v15, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v8, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v15, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v16, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v17, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v17, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v11, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v19, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v20, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v21, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v20, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v21, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v14, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v23, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v24, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v25, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v23, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v24, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v25, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v17, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v27, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v28, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v29, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v26, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v27, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v28, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v31, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v32, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v33, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v29, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v31, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v32, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v33, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v34, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v35, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v36, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v39, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v40, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v41, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v42, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v44, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v45, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v35, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v46, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v36, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v47, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v37, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v49, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v38, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v50, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v51, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v39, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v40, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v53, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v54, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v41, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v55, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v42, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v56, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v57, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v43, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v58, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v44, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v59, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v60, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v45, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v61, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v46, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v64, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v65, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v47, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v66, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v48, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v67, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v68, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v49, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v50, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v70, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v51, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v71, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v52, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v72, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v53, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v73, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v54, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v74, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v55, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v75, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v76, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v77, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v56, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v50, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v51, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v60, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v61, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v52, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v68, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v69, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v75, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v76, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v83, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v84, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v53, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v54, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v55, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v56, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v90, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v57, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v91, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v58, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v59, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v78, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v97, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v98, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v99, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v100, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v104, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v105, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v78, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v81, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v111, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v82, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v112, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v83, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v113, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v84, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v114, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v85, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v115, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v86, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v116, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v87, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v117, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v88, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v118, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v89, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v119, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v90, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v120, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v91, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v121, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v92, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v122, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v93, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v123, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v94, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v124, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v95, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v79, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v125, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v96, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v80, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v126, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v97, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v81, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v127, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v98, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v82, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v128, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v99, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v129, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v100, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v130, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v101, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v131, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v102, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v86, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v103, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v87, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v104, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v105, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v106, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v107, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v108, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v109, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v110, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v111, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v132, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v113, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v115, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v116, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v117, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v120, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v90, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v91, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v92, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v97, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v98, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v99, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v105, resolved type: long} */
    /* JADX WARNING: type inference failed for: r3v6 */
    /* JADX WARNING: type inference failed for: r3v7, types: [int, boolean] */
    /* JADX WARNING: type inference failed for: r3v11 */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x02c4, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x02c5, code lost:
        r31 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x02c9, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x02ca, code lost:
        r31 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x02ce, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x02cf, code lost:
        r31 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x02d3, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x02d4, code lost:
        r31 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x02d8, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x02d9, code lost:
        r31 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x02dd, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x02de, code lost:
        r31 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x02e2, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x02e3, code lost:
        r31 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x0302, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x0305, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x0308, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x030b, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x030e, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x0311, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x0314, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x034e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:0x034f, code lost:
        r3 = r0;
        r16 = r5;
        r22 = r8;
        r14 = r32;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:0x0358, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:0x0359, code lost:
        r3 = r0;
        r16 = r5;
        r22 = r8;
        r14 = r32;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:221:0x0362, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:222:0x0363, code lost:
        r3 = r0;
        r16 = r5;
        r22 = r8;
        r14 = r32;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x036c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:0x036d, code lost:
        r3 = r0;
        r16 = r5;
        r22 = r8;
        r14 = r32;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x0376, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:226:0x0377, code lost:
        r3 = r0;
        r16 = r5;
        r22 = r8;
        r14 = r32;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x0380, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x0381, code lost:
        r3 = r0;
        r16 = r5;
        r22 = r8;
        r14 = r32;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:229:0x038a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x038b, code lost:
        r3 = r0;
        r16 = r5;
        r22 = r8;
        r14 = r32;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:330:0x0616, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:332:0x061e, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:334:0x0626, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:336:0x062e, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:338:0x0636, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:340:0x063e, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:342:0x0646, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:343:0x0647, code lost:
        r3 = r0;
        r16 = r5;
        r22 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:344:0x064e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:345:0x064f, code lost:
        r33 = r11;
        r3 = r0;
        r16 = r5;
        r22 = r8;
        r14 = r32;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:346:0x065a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:347:0x065b, code lost:
        r33 = r11;
        r3 = r0;
        r16 = r5;
        r22 = r8;
        r14 = r32;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:348:0x0666, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:349:0x0667, code lost:
        r33 = r11;
        r3 = r0;
        r16 = r5;
        r22 = r8;
        r14 = r32;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:350:0x0672, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:351:0x0673, code lost:
        r33 = r11;
        r3 = r0;
        r16 = r5;
        r22 = r8;
        r14 = r32;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:352:0x067e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:353:0x067f, code lost:
        r33 = r11;
        r3 = r0;
        r16 = r5;
        r22 = r8;
        r14 = r32;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:354:0x068a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:355:0x068b, code lost:
        r33 = r11;
        r3 = r0;
        r16 = r5;
        r22 = r8;
        r14 = r32;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:356:0x0696, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:357:0x0697, code lost:
        r33 = r11;
        r3 = r0;
        r16 = r5;
        r22 = r8;
        r14 = r32;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:358:0x06a2, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:359:0x06a3, code lost:
        r33 = r11;
        r32 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:360:0x06a8, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:361:0x06a9, code lost:
        r33 = r11;
        r32 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:362:0x06ae, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:363:0x06af, code lost:
        r33 = r11;
        r32 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:364:0x06b4, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:365:0x06b5, code lost:
        r33 = r11;
        r32 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:366:0x06ba, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:367:0x06bb, code lost:
        r33 = r11;
        r32 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:368:0x06c0, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:369:0x06c1, code lost:
        r33 = r11;
        r32 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:370:0x06c6, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:371:0x06c7, code lost:
        r33 = r11;
        r32 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:372:0x06cc, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:373:0x06cd, code lost:
        r33 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:374:0x06cf, code lost:
        r3 = r0;
        r16 = r5;
        r22 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:375:0x06d6, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:376:0x06d7, code lost:
        r33 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:377:0x06d9, code lost:
        r3 = r0;
        r16 = r5;
        r22 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:378:0x06e0, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:379:0x06e1, code lost:
        r33 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:380:0x06e3, code lost:
        r3 = r0;
        r16 = r5;
        r22 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:381:0x06ea, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:382:0x06eb, code lost:
        r33 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:383:0x06ed, code lost:
        r3 = r0;
        r16 = r5;
        r22 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:384:0x06f4, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:385:0x06f5, code lost:
        r33 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:386:0x06f7, code lost:
        r3 = r0;
        r16 = r5;
        r22 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:387:0x06fe, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:388:0x06ff, code lost:
        r33 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:389:0x0701, code lost:
        r3 = r0;
        r16 = r5;
        r22 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:390:0x0708, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:391:0x0709, code lost:
        r33 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:392:0x070b, code lost:
        r3 = r0;
        r16 = r5;
        r22 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:406:0x0794, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:422:0x07c3, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:423:0x07c4, code lost:
        r33 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:424:0x07c8, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:425:0x07c9, code lost:
        r31 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:428:0x07d2, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:429:0x07d3, code lost:
        r31 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:432:0x07dc, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:433:0x07dd, code lost:
        r31 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:436:0x07e6, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:437:0x07e7, code lost:
        r31 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:440:0x07f0, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:441:0x07f1, code lost:
        r31 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:444:0x07fa, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:445:0x07fb, code lost:
        r31 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:448:0x0804, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:449:0x0805, code lost:
        r31 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:519:0x08c1, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:520:0x08c2, code lost:
        r30 = r6;
        r31 = r8;
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:521:0x08c8, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:522:0x08c9, code lost:
        r30 = r6;
        r31 = r8;
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:523:0x08d0, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:524:0x08d1, code lost:
        r30 = r6;
        r31 = r8;
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:526:0x08da, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:527:0x08db, code lost:
        r30 = r6;
        r31 = r8;
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:529:0x08e4, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:530:0x08e5, code lost:
        r30 = r6;
        r31 = r8;
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:532:0x08ee, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:533:0x08ef, code lost:
        r30 = r6;
        r31 = r8;
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:535:0x08f8, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:536:0x08f9, code lost:
        r30 = r6;
        r31 = r8;
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:538:0x0902, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:539:0x0903, code lost:
        r30 = r6;
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:549:0x0924, code lost:
        r4 = java.lang.System.currentTimeMillis() - r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:550:0x092f, code lost:
        if (r4 != 0) goto L_0x0931;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:551:0x0931, code lost:
        r15 = ((((float) r14) + 0.0f) / 1024.0f) / ((((float) r4) + 0.0f) / 1000.0f);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:552:0x093d, code lost:
        r13.i(com.ximalaya.ting.android.player.cdn.CdnUtil.a(r15, true) + "");
        r13.a(r14 + "");
        r13.b(r4 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:556:0x098b, code lost:
        r4 = java.lang.System.currentTimeMillis() - r28;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:558:?, code lost:
        r13.a(com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r4, false));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:559:0x0997, code lost:
        r16 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:567:0x09b8, code lost:
        r13.m((java.lang.String) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:570:0x09c6, code lost:
        r13.l("");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:577:0x09eb, code lost:
        r11.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:593:0x0a25, code lost:
        if (android.text.TextUtils.isEmpty(r13.k()) != false) goto L_0x0a27;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:594:0x0a27, code lost:
        r4 = java.lang.System.currentTimeMillis() - r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:595:0x0a32, code lost:
        if (r4 != 0) goto L_0x0a34;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:596:0x0a34, code lost:
        r15 = ((((float) r14) + 0.0f) / 1024.0f) / ((((float) r4) + 0.0f) / 1000.0f);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:597:0x0a40, code lost:
        r13.i(com.ximalaya.ting.android.player.cdn.CdnUtil.a(r15, true) + "");
        r13.a(r14 + "");
        r13.b(r4 + "");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:600:0x0a8b, code lost:
        if (r13.p() <= 0.0f) goto L_0x0a8d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:602:0x0a91, code lost:
        r4 = java.lang.System.currentTimeMillis() - r28;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:604:?, code lost:
        r13.a(com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r4, false));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:605:0x0a9d, code lost:
        r16 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:608:0x0aa3, code lost:
        if (r3.getMessage() == null) goto L_0x0acd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:613:0x0abd, code lost:
        r13.a("0");
        r13.b("0");
        r13.k(com.ximalaya.ting.android.player.cdn.CdnConstants.k);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:614:0x0acd, code lost:
        r13.k(com.ximalaya.ting.android.player.cdn.CdnConstants.g);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:615:0x0ad2, code lost:
        r13.j(com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.Throwable) r3));
        r13.c("failed");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:622:0x0b09, code lost:
        r13.m((java.lang.String) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:625:0x0b17, code lost:
        r13.l("");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:632:0x0b3c, code lost:
        r11.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:634:0x0b41, code lost:
        if (r30 == false) goto L_0x0b43;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:636:0x0b4b, code lost:
        if (android.text.TextUtils.isEmpty(r13.m()) == false) goto L_0x0b4d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:638:0x0b55, code lost:
        if (android.text.TextUtils.isEmpty(r13.l()) == false) goto L_0x0e79;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:648:0x0b75, code lost:
        r4 = java.lang.System.currentTimeMillis() - r28;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:650:?, code lost:
        r13.a(com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r4, false));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:651:0x0b81, code lost:
        r16 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:660:0x0bcb, code lost:
        r13.m((java.lang.String) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:663:0x0bd9, code lost:
        r13.l("");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:670:0x0bfe, code lost:
        r11.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:672:0x0c03, code lost:
        if (r30 == false) goto L_0x0c05;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:674:0x0c0d, code lost:
        if (android.text.TextUtils.isEmpty(r13.m()) == false) goto L_0x0c0f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:676:0x0c17, code lost:
        if (android.text.TextUtils.isEmpty(r13.l()) == false) goto L_0x0e79;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0189, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:686:0x0c37, code lost:
        r4 = java.lang.System.currentTimeMillis() - r28;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:688:?, code lost:
        r13.a(com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r4, false));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:689:0x0c43, code lost:
        r16 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x018a, code lost:
        r3 = r0;
        r8 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:698:0x0c8d, code lost:
        r13.m((java.lang.String) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:701:0x0c9b, code lost:
        r13.l("");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:708:0x0cc0, code lost:
        r11.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:710:0x0cc5, code lost:
        if (r30 == false) goto L_0x0cc7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:712:0x0ccf, code lost:
        if (android.text.TextUtils.isEmpty(r13.m()) == false) goto L_0x0cd1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:714:0x0cd9, code lost:
        if (android.text.TextUtils.isEmpty(r13.l()) == false) goto L_0x0e79;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:724:0x0cf9, code lost:
        r4 = java.lang.System.currentTimeMillis() - r28;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:726:?, code lost:
        r13.a(com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r4, false));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:727:0x0d05, code lost:
        r16 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:728:0x0d08, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:729:0x0d09, code lost:
        r3 = r0;
        r8 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:738:0x0d55, code lost:
        r13.m((java.lang.String) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:741:0x0d63, code lost:
        r13.l("");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:748:0x0d88, code lost:
        r11.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:750:0x0d8d, code lost:
        if (r30 == false) goto L_0x0d8f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:752:0x0d97, code lost:
        if (android.text.TextUtils.isEmpty(r13.m()) == false) goto L_0x0d99;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:754:0x0da1, code lost:
        if (android.text.TextUtils.isEmpty(r13.l()) == false) goto L_0x0e79;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:765:0x0dc6, code lost:
        r4 = java.lang.System.currentTimeMillis() - r28;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:767:?, code lost:
        r13.a(com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r4, false));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:768:0x0dd2, code lost:
        r16 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:772:0x0dde, code lost:
        r13.k(com.ximalaya.ting.android.player.cdn.CdnConstants.f);
        r13.j(java.lang.String.valueOf(r3) + com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.Throwable) r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:773:0x0dfe, code lost:
        r13.k(com.ximalaya.ting.android.player.cdn.CdnConstants.h);
        r13.j(com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.Throwable) r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:779:0x0e2b, code lost:
        r13.m((java.lang.String) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:782:0x0e39, code lost:
        r13.l("");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:789:0x0e5e, code lost:
        r11.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:791:0x0e63, code lost:
        if (r30 == false) goto L_0x0e65;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:793:0x0e6d, code lost:
        if (android.text.TextUtils.isEmpty(r13.m()) == false) goto L_0x0e6f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:795:0x0e77, code lost:
        if (android.text.TextUtils.isEmpty(r13.l()) == false) goto L_0x0e79;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:796:0x0e79, code lost:
        com.ximalaya.ting.android.player.cdn.CdnUtil.a(r13, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:806:0x0e98, code lost:
        if (r13.p() <= 0.0f) goto L_0x0e9a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:808:0x0e9e, code lost:
        r8 = java.lang.System.currentTimeMillis() - r28;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:810:?, code lost:
        r13.a(com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r8, false));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:811:0x0eaa, code lost:
        r16 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:812:0x0ead, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:813:0x0eae, code lost:
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:817:?, code lost:
        r13.k(com.ximalaya.ting.android.player.cdn.CdnConstants.i);
        r13.j(com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.Throwable) r3));
        r13.i(com.xiaomi.stat.b.m);
        r13.a("0");
        r13.b("0");
        r13.c("failed");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:824:0x0efd, code lost:
        r13.m((java.lang.String) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:827:0x0f0b, code lost:
        r13.l("");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:834:0x0f30, code lost:
        r11.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:851:0x0f73, code lost:
        r13.m((java.lang.String) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:854:0x0f81, code lost:
        r13.l("");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:861:0x0fa6, code lost:
        r4 = r2.f();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:862:0x0fab, code lost:
        if (r4 == -1) goto L_0x0fad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:863:0x0fad, code lost:
        r6 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:864:0x0fb0, code lost:
        if (r4 == 0) goto L_0x0fb2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:865:0x0fb2, code lost:
        r1.b = (long) r2.h();
        r1.c = (long) r2.j();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:866:0x0fc6, code lost:
        if (r8 > (r1.b * 1000)) goto L_0x0fc8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:867:0x0fc8, code lost:
        r13.k(com.ximalaya.ting.android.player.cdn.CdnConstants.d);
        r13.j("connected_time=" + (((float) r8) / 1000.0f) + "s, connected_time_threshold=" + r1.b + "s");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:868:0x0ff3, code lost:
        r6 = r30;
        r5 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:870:0x0ffd, code lost:
        if (((float) r1.c) > r15) goto L_0x0fff;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:871:0x0fff, code lost:
        r13.k(com.ximalaya.ting.android.player.cdn.CdnConstants.e);
        r13.j("download_speed=" + com.ximalaya.ting.android.player.cdn.CdnUtil.a(r15, true) + "KB/s, download_speed_threshold=" + r1.c + "KB/s");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:873:0x102e, code lost:
        if (r4 == 1) goto L_0x1030;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:874:0x1030, code lost:
        r1.b = (long) r2.g();
        r1.c = (long) r2.i();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:875:0x1044, code lost:
        if (r8 > (r1.b * 1000)) goto L_0x1046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:876:0x1046, code lost:
        r13.k(com.ximalaya.ting.android.player.cdn.CdnConstants.d);
        r13.j("connected_time=" + (((float) r8) / 1000.0f) + "s, connected_time_threshold=" + r1.b + "s");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:878:0x1077, code lost:
        if (((float) r1.c) > r15) goto L_0x1079;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:879:0x1079, code lost:
        r13.k(com.ximalaya.ting.android.player.cdn.CdnConstants.e);
        r13.j("download_speed=" + com.ximalaya.ting.android.player.cdn.CdnUtil.a(r15, true) + "KB/s, download_speed_threshold=" + r1.c + "KB/s");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:882:0x10ac, code lost:
        r11.disconnect();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:303:0x05d2  */
    /* JADX WARNING: Removed duplicated region for block: B:406:0x0794 A[ExcHandler: all (th java.lang.Throwable), PHI: r33 
      PHI: (r33v27 java.net.HttpURLConnection) = (r33v28 java.net.HttpURLConnection), (r33v51 java.net.HttpURLConnection), (r33v52 java.net.HttpURLConnection), (r33v52 java.net.HttpURLConnection) binds: [B:401:0x0729, B:232:0x0398, B:212:0x0342, B:213:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:212:0x0342] */
    /* JADX WARNING: Removed duplicated region for block: B:422:0x07c3 A[ExcHandler: all (th java.lang.Throwable), PHI: r11 
      PHI: (r11v37 java.net.HttpURLConnection) = (r11v22 java.net.HttpURLConnection), (r11v22 java.net.HttpURLConnection), (r11v22 java.net.HttpURLConnection), (r11v45 java.net.HttpURLConnection), (r11v45 java.net.HttpURLConnection) binds: [B:146:0x0270, B:199:0x0319, B:201:0x031d, B:204:0x0326, B:207:0x0332] A[DONT_GENERATE, DONT_INLINE], Splitter:B:146:0x0270] */
    /* JADX WARNING: Removed duplicated region for block: B:538:0x0902 A[ExcHandler: all (r0v10 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:18:0x004d] */
    /* JADX WARNING: Removed duplicated region for block: B:549:0x0924 A[Catch:{ all -> 0x0f5e }] */
    /* JADX WARNING: Removed duplicated region for block: B:555:0x0987 A[Catch:{ all -> 0x0f5e }] */
    /* JADX WARNING: Removed duplicated region for block: B:567:0x09b8  */
    /* JADX WARNING: Removed duplicated region for block: B:570:0x09c6  */
    /* JADX WARNING: Removed duplicated region for block: B:577:0x09eb  */
    /* JADX WARNING: Removed duplicated region for block: B:590:0x0a1b A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:622:0x0b09  */
    /* JADX WARNING: Removed duplicated region for block: B:625:0x0b17  */
    /* JADX WARNING: Removed duplicated region for block: B:632:0x0b3c  */
    /* JADX WARNING: Removed duplicated region for block: B:634:0x0b41  */
    /* JADX WARNING: Removed duplicated region for block: B:647:0x0b71 A[Catch:{ all -> 0x0f5e }] */
    /* JADX WARNING: Removed duplicated region for block: B:660:0x0bcb  */
    /* JADX WARNING: Removed duplicated region for block: B:663:0x0bd9  */
    /* JADX WARNING: Removed duplicated region for block: B:670:0x0bfe  */
    /* JADX WARNING: Removed duplicated region for block: B:672:0x0c03  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0189 A[ExcHandler: all (th java.lang.Throwable), PHI: r5 r30 
      PHI: (r5v87 long) = (r5v51 long), (r5v51 long), (r5v50 long), (r5v78 long), (r5v97 long), (r5v105 long) binds: [B:150:0x0282, B:159:0x02a5, B:117:0x01f6, B:120:0x01fe, B:64:0x013c, B:65:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r30v103 boolean) = (r30v77 boolean), (r30v77 boolean), (r30v77 boolean), (r30v77 boolean), (r30v112 boolean), (r30v112 boolean) binds: [B:150:0x0282, B:159:0x02a5, B:117:0x01f6, B:120:0x01fe, B:64:0x013c, B:65:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:64:0x013c] */
    /* JADX WARNING: Removed duplicated region for block: B:685:0x0c33 A[Catch:{ all -> 0x0f5e }] */
    /* JADX WARNING: Removed duplicated region for block: B:698:0x0c8d  */
    /* JADX WARNING: Removed duplicated region for block: B:701:0x0c9b  */
    /* JADX WARNING: Removed duplicated region for block: B:708:0x0cc0  */
    /* JADX WARNING: Removed duplicated region for block: B:710:0x0cc5  */
    /* JADX WARNING: Removed duplicated region for block: B:723:0x0cf5 A[Catch:{ all -> 0x0f5e }] */
    /* JADX WARNING: Removed duplicated region for block: B:738:0x0d55  */
    /* JADX WARNING: Removed duplicated region for block: B:741:0x0d63  */
    /* JADX WARNING: Removed duplicated region for block: B:748:0x0d88  */
    /* JADX WARNING: Removed duplicated region for block: B:750:0x0d8d  */
    /* JADX WARNING: Removed duplicated region for block: B:764:0x0dc2 A[Catch:{ all -> 0x0f5e }] */
    /* JADX WARNING: Removed duplicated region for block: B:772:0x0dde A[Catch:{ all -> 0x0f5e }] */
    /* JADX WARNING: Removed duplicated region for block: B:773:0x0dfe A[Catch:{ all -> 0x0f5e }] */
    /* JADX WARNING: Removed duplicated region for block: B:779:0x0e2b  */
    /* JADX WARNING: Removed duplicated region for block: B:782:0x0e39  */
    /* JADX WARNING: Removed duplicated region for block: B:789:0x0e5e  */
    /* JADX WARNING: Removed duplicated region for block: B:791:0x0e63  */
    /* JADX WARNING: Removed duplicated region for block: B:803:0x0e8f A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:824:0x0efd  */
    /* JADX WARNING: Removed duplicated region for block: B:827:0x0f0b  */
    /* JADX WARNING: Removed duplicated region for block: B:834:0x0f30  */
    /* JADX WARNING: Removed duplicated region for block: B:851:0x0f73  */
    /* JADX WARNING: Removed duplicated region for block: B:854:0x0f81  */
    /* JADX WARNING: Removed duplicated region for block: B:861:0x0fa6  */
    /* JADX WARNING: Removed duplicated region for block: B:882:0x10ac  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int a() {
        /*
            r37 = this;
            r1 = r37
            com.ximalaya.ting.android.opensdk.model.xdcs.CdnConfigModel r2 = com.ximalaya.ting.android.player.cdn.CdnUtil.a()
            boolean r3 = r1.h
            if (r3 != 0) goto L_0x10d1
            com.ximalaya.ting.android.player.AudioFile r3 = r1.e
            if (r3 == 0) goto L_0x10d1
            int r3 = r1.f
            if (r3 < 0) goto L_0x10d1
            com.ximalaya.ting.android.player.AudioFile r3 = r1.e
            java.lang.String r3 = r3.e()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x0020
            goto L_0x10d1
        L_0x0020:
            r3 = 0
            r5 = 1
            if (r2 != 0) goto L_0x0026
            r6 = 1
            goto L_0x0027
        L_0x0026:
            r6 = 0
        L_0x0027:
            r7 = 3
            java.util.UUID r8 = java.util.UUID.randomUUID()
            java.lang.String r8 = r8.toString()
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r22 = 0
        L_0x003d:
            int r24 = r7 + -1
            if (r7 <= 0) goto L_0x10cf
            if (r6 != 0) goto L_0x0049
            com.ximalaya.ting.android.player.cdn.CdnCollectDataForPlay r7 = new com.ximalaya.ting.android.player.cdn.CdnCollectDataForPlay
            r7.<init>()
            r13 = r7
        L_0x0049:
            r25 = 1000(0x3e8, double:4.94E-321)
            r27 = 1148846080(0x447a0000, float:1000.0)
            long r28 = java.lang.System.currentTimeMillis()     // Catch:{ MalformedURLException -> 0x0e82, SocketTimeoutException -> 0x0da5, UnknownHostException -> 0x0cdd, IllegalArgumentException -> 0x0c1b, FileNotFoundException -> 0x0b59, IOException -> 0x0a10, Throwable -> 0x090d, all -> 0x0902 }
            int r11 = r1.f     // Catch:{ MalformedURLException -> 0x08f8, SocketTimeoutException -> 0x08ee, UnknownHostException -> 0x08e4, IllegalArgumentException -> 0x08da, FileNotFoundException -> 0x08d0, IOException -> 0x08c8, Throwable -> 0x08c1, all -> 0x0902 }
            com.ximalaya.ting.android.player.AudioFile r7 = r1.e     // Catch:{ MalformedURLException -> 0x08f8, SocketTimeoutException -> 0x08ee, UnknownHostException -> 0x08e4, IllegalArgumentException -> 0x08da, FileNotFoundException -> 0x08d0, IOException -> 0x08c8, Throwable -> 0x08c1, all -> 0x0902 }
            com.ximalaya.ting.android.player.FileDesc r7 = r7.d()     // Catch:{ MalformedURLException -> 0x08f8, SocketTimeoutException -> 0x08ee, UnknownHostException -> 0x08e4, IllegalArgumentException -> 0x08da, FileNotFoundException -> 0x08d0, IOException -> 0x08c8, Throwable -> 0x08c1, all -> 0x0902 }
            int r7 = r7.a()     // Catch:{ MalformedURLException -> 0x08f8, SocketTimeoutException -> 0x08ee, UnknownHostException -> 0x08e4, IllegalArgumentException -> 0x08da, FileNotFoundException -> 0x08d0, IOException -> 0x08c8, Throwable -> 0x08c1, all -> 0x0902 }
            int r7 = r7 - r5
            r12 = 65536(0x10000, float:9.18355E-41)
            if (r11 != r7) goto L_0x00b0
            int r7 = r1.f     // Catch:{ MalformedURLException -> 0x00a8, SocketTimeoutException -> 0x00a0, UnknownHostException -> 0x0098, IllegalArgumentException -> 0x0090, FileNotFoundException -> 0x0088, IOException -> 0x0080, Throwable -> 0x0078, all -> 0x0072 }
            int r7 = r7 * r12
            com.ximalaya.ting.android.player.AudioFile r11 = r1.e     // Catch:{ MalformedURLException -> 0x00a8, SocketTimeoutException -> 0x00a0, UnknownHostException -> 0x0098, IllegalArgumentException -> 0x0090, FileNotFoundException -> 0x0088, IOException -> 0x0080, Throwable -> 0x0078, all -> 0x0072 }
            com.ximalaya.ting.android.player.FileDesc r11 = r11.d()     // Catch:{ MalformedURLException -> 0x00a8, SocketTimeoutException -> 0x00a0, UnknownHostException -> 0x0098, IllegalArgumentException -> 0x0090, FileNotFoundException -> 0x0088, IOException -> 0x0080, Throwable -> 0x0078, all -> 0x0072 }
            int r11 = r11.b()     // Catch:{ MalformedURLException -> 0x00a8, SocketTimeoutException -> 0x00a0, UnknownHostException -> 0x0098, IllegalArgumentException -> 0x0090, FileNotFoundException -> 0x0088, IOException -> 0x0080, Throwable -> 0x0078, all -> 0x0072 }
            int r11 = r11 - r5
            goto L_0x00ba
        L_0x0072:
            r0 = move-exception
            r3 = r0
            r30 = r6
            goto L_0x0906
        L_0x0078:
            r0 = move-exception
            r3 = r0
            r30 = r6
            r31 = r8
            goto L_0x0915
        L_0x0080:
            r0 = move-exception
            r3 = r0
            r30 = r6
            r31 = r8
            goto L_0x0a18
        L_0x0088:
            r0 = move-exception
            r3 = r0
            r30 = r6
            r31 = r8
            goto L_0x08d6
        L_0x0090:
            r0 = move-exception
            r3 = r0
            r30 = r6
            r31 = r8
            goto L_0x08e0
        L_0x0098:
            r0 = move-exception
            r3 = r0
            r30 = r6
            r31 = r8
            goto L_0x08ea
        L_0x00a0:
            r0 = move-exception
            r3 = r0
            r30 = r6
            r31 = r8
            goto L_0x08f4
        L_0x00a8:
            r0 = move-exception
            r3 = r0
            r30 = r6
            r31 = r8
            goto L_0x08fe
        L_0x00b0:
            int r7 = r1.f     // Catch:{ MalformedURLException -> 0x08f8, SocketTimeoutException -> 0x08ee, UnknownHostException -> 0x08e4, IllegalArgumentException -> 0x08da, FileNotFoundException -> 0x08d0, IOException -> 0x08c8, Throwable -> 0x08c1, all -> 0x0902 }
            int r7 = r7 * r12
            int r11 = r1.f     // Catch:{ MalformedURLException -> 0x08f8, SocketTimeoutException -> 0x08ee, UnknownHostException -> 0x08e4, IllegalArgumentException -> 0x08da, FileNotFoundException -> 0x08d0, IOException -> 0x08c8, Throwable -> 0x08c1, all -> 0x0902 }
            int r11 = r11 + r5
            int r11 = r11 * r12
            int r11 = r11 - r5
        L_0x00ba:
            if (r13 == 0) goto L_0x00e5
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x00a8, SocketTimeoutException -> 0x00a0, UnknownHostException -> 0x0098, IllegalArgumentException -> 0x0090, FileNotFoundException -> 0x0088, IOException -> 0x0080, Throwable -> 0x0078, all -> 0x0072 }
            r9.<init>()     // Catch:{ MalformedURLException -> 0x00a8, SocketTimeoutException -> 0x00a0, UnknownHostException -> 0x0098, IllegalArgumentException -> 0x0090, FileNotFoundException -> 0x0088, IOException -> 0x0080, Throwable -> 0x0078, all -> 0x0072 }
            r9.append(r7)     // Catch:{ MalformedURLException -> 0x00a8, SocketTimeoutException -> 0x00a0, UnknownHostException -> 0x0098, IllegalArgumentException -> 0x0090, FileNotFoundException -> 0x0088, IOException -> 0x0080, Throwable -> 0x0078, all -> 0x0072 }
            java.lang.String r10 = "-"
            r9.append(r10)     // Catch:{ MalformedURLException -> 0x00a8, SocketTimeoutException -> 0x00a0, UnknownHostException -> 0x0098, IllegalArgumentException -> 0x0090, FileNotFoundException -> 0x0088, IOException -> 0x0080, Throwable -> 0x0078, all -> 0x0072 }
            r9.append(r11)     // Catch:{ MalformedURLException -> 0x00a8, SocketTimeoutException -> 0x00a0, UnknownHostException -> 0x0098, IllegalArgumentException -> 0x0090, FileNotFoundException -> 0x0088, IOException -> 0x0080, Throwable -> 0x0078, all -> 0x0072 }
            java.lang.String r9 = r9.toString()     // Catch:{ MalformedURLException -> 0x00a8, SocketTimeoutException -> 0x00a0, UnknownHostException -> 0x0098, IllegalArgumentException -> 0x0090, FileNotFoundException -> 0x0088, IOException -> 0x0080, Throwable -> 0x0078, all -> 0x0072 }
            r13.d(r9)     // Catch:{ MalformedURLException -> 0x00a8, SocketTimeoutException -> 0x00a0, UnknownHostException -> 0x0098, IllegalArgumentException -> 0x0090, FileNotFoundException -> 0x0088, IOException -> 0x0080, Throwable -> 0x0078, all -> 0x0072 }
            com.ximalaya.ting.android.player.AudioFile r9 = r1.e     // Catch:{ MalformedURLException -> 0x00a8, SocketTimeoutException -> 0x00a0, UnknownHostException -> 0x0098, IllegalArgumentException -> 0x0090, FileNotFoundException -> 0x0088, IOException -> 0x0080, Throwable -> 0x0078, all -> 0x0072 }
            com.ximalaya.ting.android.player.FileDesc r9 = r9.d()     // Catch:{ MalformedURLException -> 0x00a8, SocketTimeoutException -> 0x00a0, UnknownHostException -> 0x0098, IllegalArgumentException -> 0x0090, FileNotFoundException -> 0x0088, IOException -> 0x0080, Throwable -> 0x0078, all -> 0x0072 }
            int r9 = r9.b()     // Catch:{ MalformedURLException -> 0x00a8, SocketTimeoutException -> 0x00a0, UnknownHostException -> 0x0098, IllegalArgumentException -> 0x0090, FileNotFoundException -> 0x0088, IOException -> 0x0080, Throwable -> 0x0078, all -> 0x0072 }
            int r9 = r9 - r5
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ MalformedURLException -> 0x00a8, SocketTimeoutException -> 0x00a0, UnknownHostException -> 0x0098, IllegalArgumentException -> 0x0090, FileNotFoundException -> 0x0088, IOException -> 0x0080, Throwable -> 0x0078, all -> 0x0072 }
            r13.e(r9)     // Catch:{ MalformedURLException -> 0x00a8, SocketTimeoutException -> 0x00a0, UnknownHostException -> 0x0098, IllegalArgumentException -> 0x0090, FileNotFoundException -> 0x0088, IOException -> 0x0080, Throwable -> 0x0078, all -> 0x0072 }
        L_0x00e5:
            java.lang.String r9 = "bytes=%d-%d"
            r10 = 2
            java.lang.Object[] r4 = new java.lang.Object[r10]     // Catch:{ MalformedURLException -> 0x08f8, SocketTimeoutException -> 0x08ee, UnknownHostException -> 0x08e4, IllegalArgumentException -> 0x08da, FileNotFoundException -> 0x08d0, IOException -> 0x08c8, Throwable -> 0x08c1, all -> 0x0902 }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ MalformedURLException -> 0x08f8, SocketTimeoutException -> 0x08ee, UnknownHostException -> 0x08e4, IllegalArgumentException -> 0x08da, FileNotFoundException -> 0x08d0, IOException -> 0x08c8, Throwable -> 0x08c1, all -> 0x0902 }
            r4[r3] = r7     // Catch:{ MalformedURLException -> 0x08f8, SocketTimeoutException -> 0x08ee, UnknownHostException -> 0x08e4, IllegalArgumentException -> 0x08da, FileNotFoundException -> 0x08d0, IOException -> 0x08c8, Throwable -> 0x08c1, all -> 0x0902 }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r11)     // Catch:{ MalformedURLException -> 0x08f8, SocketTimeoutException -> 0x08ee, UnknownHostException -> 0x08e4, IllegalArgumentException -> 0x08da, FileNotFoundException -> 0x08d0, IOException -> 0x08c8, Throwable -> 0x08c1, all -> 0x0902 }
            r4[r5] = r7     // Catch:{ MalformedURLException -> 0x08f8, SocketTimeoutException -> 0x08ee, UnknownHostException -> 0x08e4, IllegalArgumentException -> 0x08da, FileNotFoundException -> 0x08d0, IOException -> 0x08c8, Throwable -> 0x08c1, all -> 0x0902 }
            java.lang.String r4 = java.lang.String.format(r9, r4)     // Catch:{ MalformedURLException -> 0x08f8, SocketTimeoutException -> 0x08ee, UnknownHostException -> 0x08e4, IllegalArgumentException -> 0x08da, FileNotFoundException -> 0x08d0, IOException -> 0x08c8, Throwable -> 0x08c1, all -> 0x0902 }
            java.lang.String[] r7 = new java.lang.String[r5]     // Catch:{ MalformedURLException -> 0x08f8, SocketTimeoutException -> 0x08ee, UnknownHostException -> 0x08e4, IllegalArgumentException -> 0x08da, FileNotFoundException -> 0x08d0, IOException -> 0x08c8, Throwable -> 0x08c1, all -> 0x0902 }
            com.ximalaya.ting.android.player.AudioFile r9 = r1.e     // Catch:{ MalformedURLException -> 0x08f8, SocketTimeoutException -> 0x08ee, UnknownHostException -> 0x08e4, IllegalArgumentException -> 0x08da, FileNotFoundException -> 0x08d0, IOException -> 0x08c8, Throwable -> 0x08c1, all -> 0x0902 }
            java.lang.String r9 = r9.e()     // Catch:{ MalformedURLException -> 0x08f8, SocketTimeoutException -> 0x08ee, UnknownHostException -> 0x08e4, IllegalArgumentException -> 0x08da, FileNotFoundException -> 0x08d0, IOException -> 0x08c8, Throwable -> 0x08c1, all -> 0x0902 }
            r7[r3] = r9     // Catch:{ MalformedURLException -> 0x08f8, SocketTimeoutException -> 0x08ee, UnknownHostException -> 0x08e4, IllegalArgumentException -> 0x08da, FileNotFoundException -> 0x08d0, IOException -> 0x08c8, Throwable -> 0x08c1, all -> 0x0902 }
            com.ximalaya.ting.android.player.AudioFile r9 = r1.e     // Catch:{ MalformedURLException -> 0x08f8, SocketTimeoutException -> 0x08ee, UnknownHostException -> 0x08e4, IllegalArgumentException -> 0x08da, FileNotFoundException -> 0x08d0, IOException -> 0x08c8, Throwable -> 0x08c1, all -> 0x0902 }
            java.lang.String r9 = r9.e()     // Catch:{ MalformedURLException -> 0x08f8, SocketTimeoutException -> 0x08ee, UnknownHostException -> 0x08e4, IllegalArgumentException -> 0x08da, FileNotFoundException -> 0x08d0, IOException -> 0x08c8, Throwable -> 0x08c1, all -> 0x0902 }
            boolean r9 = android.text.TextUtils.isEmpty(r9)     // Catch:{ MalformedURLException -> 0x08f8, SocketTimeoutException -> 0x08ee, UnknownHostException -> 0x08e4, IllegalArgumentException -> 0x08da, FileNotFoundException -> 0x08d0, IOException -> 0x08c8, Throwable -> 0x08c1, all -> 0x0902 }
            if (r9 == 0) goto L_0x0113
            java.lang.String r9 = ""
            goto L_0x0121
        L_0x0113:
            com.ximalaya.ting.android.player.AudioFile r9 = r1.e     // Catch:{ MalformedURLException -> 0x08f8, SocketTimeoutException -> 0x08ee, UnknownHostException -> 0x08e4, IllegalArgumentException -> 0x08da, FileNotFoundException -> 0x08d0, IOException -> 0x08c8, Throwable -> 0x08c1, all -> 0x0902 }
            java.lang.String r9 = r9.e()     // Catch:{ MalformedURLException -> 0x08f8, SocketTimeoutException -> 0x08ee, UnknownHostException -> 0x08e4, IllegalArgumentException -> 0x08da, FileNotFoundException -> 0x08d0, IOException -> 0x08c8, Throwable -> 0x08c1, all -> 0x0902 }
            android.net.Uri r9 = android.net.Uri.parse(r9)     // Catch:{ MalformedURLException -> 0x08f8, SocketTimeoutException -> 0x08ee, UnknownHostException -> 0x08e4, IllegalArgumentException -> 0x08da, FileNotFoundException -> 0x08d0, IOException -> 0x08c8, Throwable -> 0x08c1, all -> 0x0902 }
            java.lang.String r9 = r9.getHost()     // Catch:{ MalformedURLException -> 0x08f8, SocketTimeoutException -> 0x08ee, UnknownHostException -> 0x08e4, IllegalArgumentException -> 0x08da, FileNotFoundException -> 0x08d0, IOException -> 0x08c8, Throwable -> 0x08c1, all -> 0x0902 }
        L_0x0121:
            java.lang.String r11 = "GET"
            java.net.HttpURLConnection r11 = com.ximalaya.ting.android.player.PlayerUtil.a(r7, r4, r3, r3, r11)     // Catch:{ MalformedURLException -> 0x08f8, SocketTimeoutException -> 0x08ee, UnknownHostException -> 0x08e4, IllegalArgumentException -> 0x08da, FileNotFoundException -> 0x08d0, IOException -> 0x08c8, Throwable -> 0x08c1, all -> 0x0902 }
            com.ximalaya.ting.android.player.AudioFile r4 = r1.e     // Catch:{ MalformedURLException -> 0x08b5, SocketTimeoutException -> 0x08a9, UnknownHostException -> 0x089d, IllegalArgumentException -> 0x0891, FileNotFoundException -> 0x0885, IOException -> 0x087b, Throwable -> 0x0871, all -> 0x0865 }
            r7 = r7[r3]     // Catch:{ MalformedURLException -> 0x08b5, SocketTimeoutException -> 0x08a9, UnknownHostException -> 0x089d, IllegalArgumentException -> 0x0891, FileNotFoundException -> 0x0885, IOException -> 0x087b, Throwable -> 0x0871, all -> 0x0865 }
            r4.a((java.lang.String) r7)     // Catch:{ MalformedURLException -> 0x08b5, SocketTimeoutException -> 0x08a9, UnknownHostException -> 0x089d, IllegalArgumentException -> 0x0891, FileNotFoundException -> 0x0885, IOException -> 0x087b, Throwable -> 0x0871, all -> 0x0865 }
            if (r11 == 0) goto L_0x0826
            if (r13 == 0) goto L_0x01ee
            long r20 = java.lang.System.currentTimeMillis()     // Catch:{ MalformedURLException -> 0x01e6, SocketTimeoutException -> 0x01de, UnknownHostException -> 0x01d6, IllegalArgumentException -> 0x01ce, FileNotFoundException -> 0x01c6, IOException -> 0x01be, Throwable -> 0x01b6, all -> 0x01b1 }
            r4 = 0
            r30 = r6
            long r5 = r20 - r28
            float r4 = (float) r5
            float r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r4, (boolean) r3)     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            r13.a((float) r4)     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            com.ximalaya.ting.android.player.AudioFile r4 = r1.e     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            java.lang.String r4 = r4.e()     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            r13.g(r4)     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            r13.n(r9)     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            com.ximalaya.ting.android.player.AudioFile r4 = r1.e     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            java.lang.String r4 = r4.e()     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            java.lang.String r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.String) r4)     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            r13.h(r4)     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            java.lang.String r4 = "play"
            r13.f(r4)     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            r4.<init>()     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            int r7 = r11.getResponseCode()     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            r4.append(r7)     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            java.lang.String r7 = ""
            r4.append(r7)     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            java.lang.String r4 = r4.toString()     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            r13.l(r4)     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            java.lang.String r4 = "via"
            java.lang.String r4 = r11.getHeaderField(r4)     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            r13.m(r4)     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            r13.o(r8)     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            r16 = r5
            goto L_0x01f0
        L_0x0189:
            r0 = move-exception
        L_0x018a:
            r3 = r0
            r8 = r5
            goto L_0x086d
        L_0x018e:
            r0 = move-exception
            r3 = r0
            r16 = r5
            goto L_0x01ba
        L_0x0193:
            r0 = move-exception
            r3 = r0
            r16 = r5
            goto L_0x01c2
        L_0x0198:
            r0 = move-exception
            r3 = r0
            r16 = r5
            goto L_0x01ca
        L_0x019d:
            r0 = move-exception
            r3 = r0
            r16 = r5
            goto L_0x01d2
        L_0x01a2:
            r0 = move-exception
            r3 = r0
            r16 = r5
            goto L_0x01da
        L_0x01a7:
            r0 = move-exception
            r3 = r0
            r16 = r5
            goto L_0x01e2
        L_0x01ac:
            r0 = move-exception
            r3 = r0
            r16 = r5
            goto L_0x01ea
        L_0x01b1:
            r0 = move-exception
            r30 = r6
            goto L_0x086a
        L_0x01b6:
            r0 = move-exception
            r30 = r6
        L_0x01b9:
            r3 = r0
        L_0x01ba:
            r31 = r8
            goto L_0x0916
        L_0x01be:
            r0 = move-exception
            r30 = r6
        L_0x01c1:
            r3 = r0
        L_0x01c2:
            r31 = r8
            goto L_0x0a19
        L_0x01c6:
            r0 = move-exception
            r30 = r6
        L_0x01c9:
            r3 = r0
        L_0x01ca:
            r31 = r8
            goto L_0x088d
        L_0x01ce:
            r0 = move-exception
            r30 = r6
        L_0x01d1:
            r3 = r0
        L_0x01d2:
            r31 = r8
            goto L_0x0899
        L_0x01d6:
            r0 = move-exception
            r30 = r6
        L_0x01d9:
            r3 = r0
        L_0x01da:
            r31 = r8
            goto L_0x08a5
        L_0x01de:
            r0 = move-exception
            r30 = r6
        L_0x01e1:
            r3 = r0
        L_0x01e2:
            r31 = r8
            goto L_0x08b1
        L_0x01e6:
            r0 = move-exception
            r30 = r6
        L_0x01e9:
            r3 = r0
        L_0x01ea:
            r31 = r8
            goto L_0x08bd
        L_0x01ee:
            r30 = r6
        L_0x01f0:
            int r4 = r11.getResponseCode()     // Catch:{ MalformedURLException -> 0x0823, SocketTimeoutException -> 0x0820, UnknownHostException -> 0x081d, IllegalArgumentException -> 0x081a, FileNotFoundException -> 0x0817, IOException -> 0x0814, Throwable -> 0x0811, all -> 0x080e }
            if (r13 == 0) goto L_0x0238
            long r5 = java.lang.System.currentTimeMillis()     // Catch:{ MalformedURLException -> 0x0236, SocketTimeoutException -> 0x0234, UnknownHostException -> 0x0232, IllegalArgumentException -> 0x0230, FileNotFoundException -> 0x022e, IOException -> 0x022c, Throwable -> 0x022a, all -> 0x0227 }
            r7 = 0
            long r5 = r5 - r28
            float r7 = (float) r5
            float r7 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r7, (boolean) r3)     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            r13.a((float) r7)     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            java.lang.String r7 = "via"
            java.lang.String r7 = r11.getHeaderField(r7)     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            r13.m(r7)     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            r7.<init>()     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            int r9 = r11.getResponseCode()     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            r7.append(r9)     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            java.lang.String r9 = ""
            r7.append(r9)     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            java.lang.String r7 = r7.toString()     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            r13.l(r7)     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            goto L_0x023a
        L_0x0227:
            r0 = move-exception
            goto L_0x086a
        L_0x022a:
            r0 = move-exception
            goto L_0x01b9
        L_0x022c:
            r0 = move-exception
            goto L_0x01c1
        L_0x022e:
            r0 = move-exception
            goto L_0x01c9
        L_0x0230:
            r0 = move-exception
            goto L_0x01d1
        L_0x0232:
            r0 = move-exception
            goto L_0x01d9
        L_0x0234:
            r0 = move-exception
            goto L_0x01e1
        L_0x0236:
            r0 = move-exception
            goto L_0x01e9
        L_0x0238:
            r5 = r16
        L_0x023a:
            r7 = 206(0xce, float:2.89E-43)
            if (r4 == r7) goto L_0x0270
            r7 = 200(0xc8, float:2.8E-43)
            if (r4 != r7) goto L_0x0243
            goto L_0x0270
        L_0x0243:
            java.lang.String r7 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            r9.<init>()     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            java.lang.String r10 = "DownloadThread fail responseCode:"
            r9.append(r10)     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            r9.append(r4)     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            java.lang.String r9 = r9.toString()     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r7, (java.lang.Object) r9)     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            java.io.IOException r7 = new java.io.IOException     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            r9.<init>()     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            java.lang.String r10 = "DownloadThread fail responseCode:"
            r9.append(r10)     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            r9.append(r4)     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            java.lang.String r4 = r9.toString()     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            r7.<init>(r4)     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
            throw r7     // Catch:{ MalformedURLException -> 0x01ac, SocketTimeoutException -> 0x01a7, UnknownHostException -> 0x01a2, IllegalArgumentException -> 0x019d, FileNotFoundException -> 0x0198, IOException -> 0x0193, Throwable -> 0x018e, all -> 0x0189 }
        L_0x0270:
            int r7 = r11.getContentLength()     // Catch:{ MalformedURLException -> 0x0804, SocketTimeoutException -> 0x07fa, UnknownHostException -> 0x07f0, IllegalArgumentException -> 0x07e6, FileNotFoundException -> 0x07dc, IOException -> 0x07d2, Throwable -> 0x07c8, all -> 0x07c3 }
            java.lang.String r9 = "Content-Range"
            java.lang.String r9 = r11.getHeaderField(r9)     // Catch:{ MalformedURLException -> 0x0804, SocketTimeoutException -> 0x07fa, UnknownHostException -> 0x07f0, IllegalArgumentException -> 0x07e6, FileNotFoundException -> 0x07dc, IOException -> 0x07d2, Throwable -> 0x07c8, all -> 0x07c3 }
            boolean r16 = android.text.TextUtils.isEmpty(r9)     // Catch:{ MalformedURLException -> 0x0804, SocketTimeoutException -> 0x07fa, UnknownHostException -> 0x07f0, IllegalArgumentException -> 0x07e6, FileNotFoundException -> 0x07dc, IOException -> 0x07d2, Throwable -> 0x07c8, all -> 0x07c3 }
            if (r16 != 0) goto L_0x02e7
            java.lang.String r12 = "/"
            java.lang.String[] r9 = r9.split(r12)     // Catch:{ MalformedURLException -> 0x02e2, SocketTimeoutException -> 0x02dd, UnknownHostException -> 0x02d8, IllegalArgumentException -> 0x02d3, FileNotFoundException -> 0x02ce, IOException -> 0x02c9, Throwable -> 0x02c4, all -> 0x0189 }
            java.lang.String r12 = ""
            java.lang.String r16 = ""
            int r3 = r9.length     // Catch:{ MalformedURLException -> 0x02e2, SocketTimeoutException -> 0x02dd, UnknownHostException -> 0x02d8, IllegalArgumentException -> 0x02d3, FileNotFoundException -> 0x02ce, IOException -> 0x02c9, Throwable -> 0x02c4, all -> 0x0189 }
            if (r3 < r10) goto L_0x02b7
            r3 = 0
            r17 = r9[r3]     // Catch:{ MalformedURLException -> 0x02e2, SocketTimeoutException -> 0x02dd, UnknownHostException -> 0x02d8, IllegalArgumentException -> 0x02d3, FileNotFoundException -> 0x02ce, IOException -> 0x02c9, Throwable -> 0x02c4, all -> 0x0189 }
            boolean r17 = android.text.TextUtils.isEmpty(r17)     // Catch:{ MalformedURLException -> 0x02e2, SocketTimeoutException -> 0x02dd, UnknownHostException -> 0x02d8, IllegalArgumentException -> 0x02d3, FileNotFoundException -> 0x02ce, IOException -> 0x02c9, Throwable -> 0x02c4, all -> 0x0189 }
            if (r17 != 0) goto L_0x02a9
            r10 = r9[r3]     // Catch:{ MalformedURLException -> 0x02e2, SocketTimeoutException -> 0x02dd, UnknownHostException -> 0x02d8, IllegalArgumentException -> 0x02d3, FileNotFoundException -> 0x02ce, IOException -> 0x02c9, Throwable -> 0x02c4, all -> 0x0189 }
            java.lang.String r3 = " "
            java.lang.String[] r3 = r10.split(r3)     // Catch:{ MalformedURLException -> 0x02e2, SocketTimeoutException -> 0x02dd, UnknownHostException -> 0x02d8, IllegalArgumentException -> 0x02d3, FileNotFoundException -> 0x02ce, IOException -> 0x02c9, Throwable -> 0x02c4, all -> 0x0189 }
            int r10 = r3.length     // Catch:{ MalformedURLException -> 0x02e2, SocketTimeoutException -> 0x02dd, UnknownHostException -> 0x02d8, IllegalArgumentException -> 0x02d3, FileNotFoundException -> 0x02ce, IOException -> 0x02c9, Throwable -> 0x02c4, all -> 0x0189 }
            r31 = r8
            r8 = 2
            if (r10 < r8) goto L_0x02ab
            r8 = 1
            r3 = r3[r8]     // Catch:{ MalformedURLException -> 0x0314, SocketTimeoutException -> 0x0311, UnknownHostException -> 0x030e, IllegalArgumentException -> 0x030b, FileNotFoundException -> 0x0308, IOException -> 0x0305, Throwable -> 0x0302, all -> 0x0189 }
            r12 = r3
            goto L_0x02ab
        L_0x02a9:
            r31 = r8
        L_0x02ab:
            r3 = 1
            r8 = r9[r3]     // Catch:{ MalformedURLException -> 0x0314, SocketTimeoutException -> 0x0311, UnknownHostException -> 0x030e, IllegalArgumentException -> 0x030b, FileNotFoundException -> 0x0308, IOException -> 0x0305, Throwable -> 0x0302, all -> 0x0189 }
            boolean r8 = android.text.TextUtils.isEmpty(r8)     // Catch:{ MalformedURLException -> 0x0314, SocketTimeoutException -> 0x0311, UnknownHostException -> 0x030e, IllegalArgumentException -> 0x030b, FileNotFoundException -> 0x0308, IOException -> 0x0305, Throwable -> 0x0302, all -> 0x0189 }
            if (r8 != 0) goto L_0x02b9
            r16 = r9[r3]     // Catch:{ MalformedURLException -> 0x0314, SocketTimeoutException -> 0x0311, UnknownHostException -> 0x030e, IllegalArgumentException -> 0x030b, FileNotFoundException -> 0x0308, IOException -> 0x0305, Throwable -> 0x0302, all -> 0x0189 }
            goto L_0x02b9
        L_0x02b7:
            r31 = r8
        L_0x02b9:
            r3 = r16
            if (r13 == 0) goto L_0x02e9
            r13.d(r12)     // Catch:{ MalformedURLException -> 0x0314, SocketTimeoutException -> 0x0311, UnknownHostException -> 0x030e, IllegalArgumentException -> 0x030b, FileNotFoundException -> 0x0308, IOException -> 0x0305, Throwable -> 0x0302, all -> 0x0189 }
            r13.e(r3)     // Catch:{ MalformedURLException -> 0x0314, SocketTimeoutException -> 0x0311, UnknownHostException -> 0x030e, IllegalArgumentException -> 0x030b, FileNotFoundException -> 0x0308, IOException -> 0x0305, Throwable -> 0x0302, all -> 0x0189 }
            goto L_0x02e9
        L_0x02c4:
            r0 = move-exception
            r31 = r8
            goto L_0x07cd
        L_0x02c9:
            r0 = move-exception
            r31 = r8
            goto L_0x07d7
        L_0x02ce:
            r0 = move-exception
            r31 = r8
            goto L_0x07e1
        L_0x02d3:
            r0 = move-exception
            r31 = r8
            goto L_0x07eb
        L_0x02d8:
            r0 = move-exception
            r31 = r8
            goto L_0x07f5
        L_0x02dd:
            r0 = move-exception
            r31 = r8
            goto L_0x07ff
        L_0x02e2:
            r0 = move-exception
            r31 = r8
            goto L_0x0809
        L_0x02e7:
            r31 = r8
        L_0x02e9:
            if (r7 <= 0) goto L_0x0760
            r3 = 65536(0x10000, float:9.18355E-41)
            if (r7 == r3) goto L_0x0317
            int r3 = r1.f     // Catch:{ MalformedURLException -> 0x0314, SocketTimeoutException -> 0x0311, UnknownHostException -> 0x030e, IllegalArgumentException -> 0x030b, FileNotFoundException -> 0x0308, IOException -> 0x0305, Throwable -> 0x0302, all -> 0x0189 }
            com.ximalaya.ting.android.player.AudioFile r8 = r1.e     // Catch:{ MalformedURLException -> 0x0314, SocketTimeoutException -> 0x0311, UnknownHostException -> 0x030e, IllegalArgumentException -> 0x030b, FileNotFoundException -> 0x0308, IOException -> 0x0305, Throwable -> 0x0302, all -> 0x0189 }
            com.ximalaya.ting.android.player.FileDesc r8 = r8.d()     // Catch:{ MalformedURLException -> 0x0314, SocketTimeoutException -> 0x0311, UnknownHostException -> 0x030e, IllegalArgumentException -> 0x030b, FileNotFoundException -> 0x0308, IOException -> 0x0305, Throwable -> 0x0302, all -> 0x0189 }
            int r8 = r8.a()     // Catch:{ MalformedURLException -> 0x0314, SocketTimeoutException -> 0x0311, UnknownHostException -> 0x030e, IllegalArgumentException -> 0x030b, FileNotFoundException -> 0x0308, IOException -> 0x0305, Throwable -> 0x0302, all -> 0x0189 }
            r9 = 1
            int r8 = r8 - r9
            if (r3 < r8) goto L_0x0727
            r3 = 65536(0x10000, float:9.18355E-41)
            goto L_0x0317
        L_0x0302:
            r0 = move-exception
            goto L_0x07cd
        L_0x0305:
            r0 = move-exception
            goto L_0x07d7
        L_0x0308:
            r0 = move-exception
            goto L_0x07e1
        L_0x030b:
            r0 = move-exception
            goto L_0x07eb
        L_0x030e:
            r0 = move-exception
            goto L_0x07f5
        L_0x0311:
            r0 = move-exception
            goto L_0x07ff
        L_0x0314:
            r0 = move-exception
            goto L_0x0809
        L_0x0317:
            if (r7 > r3) goto L_0x0727
            long r8 = java.lang.System.currentTimeMillis()     // Catch:{ MalformedURLException -> 0x0724, SocketTimeoutException -> 0x0721, UnknownHostException -> 0x071e, IllegalArgumentException -> 0x071b, FileNotFoundException -> 0x0718, IOException -> 0x0715, Throwable -> 0x0712, all -> 0x07c3 }
            java.io.InputStream r3 = r11.getInputStream()     // Catch:{ MalformedURLException -> 0x0708, SocketTimeoutException -> 0x06fe, UnknownHostException -> 0x06f4, IllegalArgumentException -> 0x06ea, FileNotFoundException -> 0x06e0, IOException -> 0x06d6, Throwable -> 0x06cc, all -> 0x07c3 }
            long r16 = java.lang.System.currentTimeMillis()     // Catch:{ MalformedURLException -> 0x0708, SocketTimeoutException -> 0x06fe, UnknownHostException -> 0x06f4, IllegalArgumentException -> 0x06ea, FileNotFoundException -> 0x06e0, IOException -> 0x06d6, Throwable -> 0x06cc, all -> 0x07c3 }
            r10 = 0
        L_0x0326:
            java.nio.ByteBuffer r12 = r1.g     // Catch:{ MalformedURLException -> 0x06c6, SocketTimeoutException -> 0x06c0, UnknownHostException -> 0x06ba, IllegalArgumentException -> 0x06b4, FileNotFoundException -> 0x06ae, IOException -> 0x06a8, Throwable -> 0x06a2, all -> 0x07c3 }
            byte[] r12 = r12.array()     // Catch:{ MalformedURLException -> 0x06c6, SocketTimeoutException -> 0x06c0, UnknownHostException -> 0x06ba, IllegalArgumentException -> 0x06b4, FileNotFoundException -> 0x06ae, IOException -> 0x06a8, Throwable -> 0x06a2, all -> 0x07c3 }
            r32 = r14
            r20 = 65536(0x10000, float:9.18355E-41)
            int r14 = r20 - r10
            int r12 = r3.read(r12, r10, r14)     // Catch:{ MalformedURLException -> 0x0696, SocketTimeoutException -> 0x068a, UnknownHostException -> 0x067e, IllegalArgumentException -> 0x0672, FileNotFoundException -> 0x0666, IOException -> 0x065a, Throwable -> 0x064e, all -> 0x07c3 }
            r14 = -1
            if (r12 == r14) goto L_0x0394
            int r14 = r10 + r12
            long r21 = f2270a     // Catch:{ MalformedURLException -> 0x0696, SocketTimeoutException -> 0x068a, UnknownHostException -> 0x067e, IllegalArgumentException -> 0x0672, FileNotFoundException -> 0x0666, IOException -> 0x065a, Throwable -> 0x064e, all -> 0x07c3 }
            r33 = r11
            long r10 = (long) r12
            long r21 = r21 + r10
            f2270a = r21     // Catch:{ MalformedURLException -> 0x038a, SocketTimeoutException -> 0x0380, UnknownHostException -> 0x0376, IllegalArgumentException -> 0x036c, FileNotFoundException -> 0x0362, IOException -> 0x0358, Throwable -> 0x034e, all -> 0x0794 }
            int r10 = r20 - r14
            if (r10 > 0) goto L_0x034a
            goto L_0x0398
        L_0x034a:
            r10 = r14
            r11 = r33
            goto L_0x0326
        L_0x034e:
            r0 = move-exception
            r3 = r0
            r16 = r5
            r22 = r8
            r14 = r32
            goto L_0x0840
        L_0x0358:
            r0 = move-exception
            r3 = r0
            r16 = r5
            r22 = r8
            r14 = r32
            goto L_0x0846
        L_0x0362:
            r0 = move-exception
            r3 = r0
            r16 = r5
            r22 = r8
            r14 = r32
            goto L_0x084c
        L_0x036c:
            r0 = move-exception
            r3 = r0
            r16 = r5
            r22 = r8
            r14 = r32
            goto L_0x0851
        L_0x0376:
            r0 = move-exception
            r3 = r0
            r16 = r5
            r22 = r8
            r14 = r32
            goto L_0x0856
        L_0x0380:
            r0 = move-exception
            r3 = r0
            r16 = r5
            r22 = r8
            r14 = r32
            goto L_0x085b
        L_0x038a:
            r0 = move-exception
            r3 = r0
            r16 = r5
            r22 = r8
            r14 = r32
            goto L_0x0861
        L_0x0394:
            r33 = r11
            r14 = r32
        L_0x0398:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x0646, SocketTimeoutException -> 0x063e, UnknownHostException -> 0x0636, IllegalArgumentException -> 0x062e, FileNotFoundException -> 0x0626, IOException -> 0x061e, Throwable -> 0x0616, all -> 0x0794 }
            r10.<init>()     // Catch:{ MalformedURLException -> 0x0646, SocketTimeoutException -> 0x063e, UnknownHostException -> 0x0636, IllegalArgumentException -> 0x062e, FileNotFoundException -> 0x0626, IOException -> 0x061e, Throwable -> 0x0616, all -> 0x0794 }
            java.lang.String r11 = "UnicomDigestAuthenticator 789 == 读取流成功 ==  耗时="
            r10.append(r11)     // Catch:{ MalformedURLException -> 0x0646, SocketTimeoutException -> 0x063e, UnknownHostException -> 0x0636, IllegalArgumentException -> 0x062e, FileNotFoundException -> 0x0626, IOException -> 0x061e, Throwable -> 0x0616, all -> 0x0794 }
            long r20 = java.lang.System.currentTimeMillis()     // Catch:{ MalformedURLException -> 0x0646, SocketTimeoutException -> 0x063e, UnknownHostException -> 0x0636, IllegalArgumentException -> 0x062e, FileNotFoundException -> 0x0626, IOException -> 0x061e, Throwable -> 0x0616, all -> 0x0794 }
            r11 = 0
            r36 = r4
            r34 = r5
            long r4 = r20 - r16
            r10.append(r4)     // Catch:{ MalformedURLException -> 0x0612, SocketTimeoutException -> 0x060e, UnknownHostException -> 0x060a, IllegalArgumentException -> 0x0606, FileNotFoundException -> 0x0602, IOException -> 0x05fe, Throwable -> 0x05fa, all -> 0x05f5 }
            java.lang.String r4 = r10.toString()     // Catch:{ MalformedURLException -> 0x0612, SocketTimeoutException -> 0x060e, UnknownHostException -> 0x060a, IllegalArgumentException -> 0x0606, FileNotFoundException -> 0x0602, IOException -> 0x05fe, Throwable -> 0x05fa, all -> 0x05f5 }
            com.ximalaya.ting.android.player.Logger.a((java.lang.Object) r4)     // Catch:{ MalformedURLException -> 0x0612, SocketTimeoutException -> 0x060e, UnknownHostException -> 0x060a, IllegalArgumentException -> 0x0606, FileNotFoundException -> 0x0602, IOException -> 0x05fe, Throwable -> 0x05fa, all -> 0x05f5 }
            if (r13 == 0) goto L_0x047b
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ MalformedURLException -> 0x0471, SocketTimeoutException -> 0x0467, UnknownHostException -> 0x045d, IllegalArgumentException -> 0x0453, FileNotFoundException -> 0x0449, IOException -> 0x043f, Throwable -> 0x0435, all -> 0x042b }
            r6 = 0
            long r4 = r4 - r8
            r10 = 0
            int r6 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r6 == 0) goto L_0x03d1
            float r6 = (float) r14     // Catch:{ MalformedURLException -> 0x0471, SocketTimeoutException -> 0x0467, UnknownHostException -> 0x045d, IllegalArgumentException -> 0x0453, FileNotFoundException -> 0x0449, IOException -> 0x043f, Throwable -> 0x0435, all -> 0x042b }
            r10 = 0
            float r6 = r6 + r10
            r11 = 1149239296(0x44800000, float:1024.0)
            float r6 = r6 / r11
            float r11 = (float) r4     // Catch:{ MalformedURLException -> 0x0471, SocketTimeoutException -> 0x0467, UnknownHostException -> 0x045d, IllegalArgumentException -> 0x0453, FileNotFoundException -> 0x0449, IOException -> 0x043f, Throwable -> 0x0435, all -> 0x042b }
            float r11 = r11 + r10
            float r11 = r11 / r27
            float r15 = r6 / r11
        L_0x03d1:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x0471, SocketTimeoutException -> 0x0467, UnknownHostException -> 0x045d, IllegalArgumentException -> 0x0453, FileNotFoundException -> 0x0449, IOException -> 0x043f, Throwable -> 0x0435, all -> 0x042b }
            r6.<init>()     // Catch:{ MalformedURLException -> 0x0471, SocketTimeoutException -> 0x0467, UnknownHostException -> 0x045d, IllegalArgumentException -> 0x0453, FileNotFoundException -> 0x0449, IOException -> 0x043f, Throwable -> 0x0435, all -> 0x042b }
            r6.append(r14)     // Catch:{ MalformedURLException -> 0x0471, SocketTimeoutException -> 0x0467, UnknownHostException -> 0x045d, IllegalArgumentException -> 0x0453, FileNotFoundException -> 0x0449, IOException -> 0x043f, Throwable -> 0x0435, all -> 0x042b }
            java.lang.String r10 = ""
            r6.append(r10)     // Catch:{ MalformedURLException -> 0x0471, SocketTimeoutException -> 0x0467, UnknownHostException -> 0x045d, IllegalArgumentException -> 0x0453, FileNotFoundException -> 0x0449, IOException -> 0x043f, Throwable -> 0x0435, all -> 0x042b }
            java.lang.String r6 = r6.toString()     // Catch:{ MalformedURLException -> 0x0471, SocketTimeoutException -> 0x0467, UnknownHostException -> 0x045d, IllegalArgumentException -> 0x0453, FileNotFoundException -> 0x0449, IOException -> 0x043f, Throwable -> 0x0435, all -> 0x042b }
            r13.a((java.lang.String) r6)     // Catch:{ MalformedURLException -> 0x0471, SocketTimeoutException -> 0x0467, UnknownHostException -> 0x045d, IllegalArgumentException -> 0x0453, FileNotFoundException -> 0x0449, IOException -> 0x043f, Throwable -> 0x0435, all -> 0x042b }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x0471, SocketTimeoutException -> 0x0467, UnknownHostException -> 0x045d, IllegalArgumentException -> 0x0453, FileNotFoundException -> 0x0449, IOException -> 0x043f, Throwable -> 0x0435, all -> 0x042b }
            r6.<init>()     // Catch:{ MalformedURLException -> 0x0471, SocketTimeoutException -> 0x0467, UnknownHostException -> 0x045d, IllegalArgumentException -> 0x0453, FileNotFoundException -> 0x0449, IOException -> 0x043f, Throwable -> 0x0435, all -> 0x042b }
            r6.append(r4)     // Catch:{ MalformedURLException -> 0x0471, SocketTimeoutException -> 0x0467, UnknownHostException -> 0x045d, IllegalArgumentException -> 0x0453, FileNotFoundException -> 0x0449, IOException -> 0x043f, Throwable -> 0x0435, all -> 0x042b }
            java.lang.String r4 = ""
            r6.append(r4)     // Catch:{ MalformedURLException -> 0x0471, SocketTimeoutException -> 0x0467, UnknownHostException -> 0x045d, IllegalArgumentException -> 0x0453, FileNotFoundException -> 0x0449, IOException -> 0x043f, Throwable -> 0x0435, all -> 0x042b }
            java.lang.String r4 = r6.toString()     // Catch:{ MalformedURLException -> 0x0471, SocketTimeoutException -> 0x0467, UnknownHostException -> 0x045d, IllegalArgumentException -> 0x0453, FileNotFoundException -> 0x0449, IOException -> 0x043f, Throwable -> 0x0435, all -> 0x042b }
            r13.b((java.lang.String) r4)     // Catch:{ MalformedURLException -> 0x0471, SocketTimeoutException -> 0x0467, UnknownHostException -> 0x045d, IllegalArgumentException -> 0x0453, FileNotFoundException -> 0x0449, IOException -> 0x043f, Throwable -> 0x0435, all -> 0x042b }
            r4 = -1
            if (r12 == r4) goto L_0x0407
            r4 = 65536(0x10000, float:9.18355E-41)
            if (r14 != r4) goto L_0x0401
            goto L_0x0407
        L_0x0401:
            java.lang.String r4 = "failed"
            r13.c(r4)     // Catch:{ MalformedURLException -> 0x0471, SocketTimeoutException -> 0x0467, UnknownHostException -> 0x045d, IllegalArgumentException -> 0x0453, FileNotFoundException -> 0x0449, IOException -> 0x043f, Throwable -> 0x0435, all -> 0x042b }
            goto L_0x040c
        L_0x0407:
            java.lang.String r4 = "success"
            r13.c(r4)     // Catch:{ MalformedURLException -> 0x0471, SocketTimeoutException -> 0x0467, UnknownHostException -> 0x045d, IllegalArgumentException -> 0x0453, FileNotFoundException -> 0x0449, IOException -> 0x043f, Throwable -> 0x0435, all -> 0x042b }
        L_0x040c:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x0471, SocketTimeoutException -> 0x0467, UnknownHostException -> 0x045d, IllegalArgumentException -> 0x0453, FileNotFoundException -> 0x0449, IOException -> 0x043f, Throwable -> 0x0435, all -> 0x042b }
            r4.<init>()     // Catch:{ MalformedURLException -> 0x0471, SocketTimeoutException -> 0x0467, UnknownHostException -> 0x045d, IllegalArgumentException -> 0x0453, FileNotFoundException -> 0x0449, IOException -> 0x043f, Throwable -> 0x0435, all -> 0x042b }
            r5 = 1
            float r6 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r15, (boolean) r5)     // Catch:{ MalformedURLException -> 0x0471, SocketTimeoutException -> 0x0467, UnknownHostException -> 0x045d, IllegalArgumentException -> 0x0453, FileNotFoundException -> 0x0449, IOException -> 0x043f, Throwable -> 0x0435, all -> 0x042b }
            r4.append(r6)     // Catch:{ MalformedURLException -> 0x0471, SocketTimeoutException -> 0x0467, UnknownHostException -> 0x045d, IllegalArgumentException -> 0x0453, FileNotFoundException -> 0x0449, IOException -> 0x043f, Throwable -> 0x0435, all -> 0x042b }
            java.lang.String r5 = ""
            r4.append(r5)     // Catch:{ MalformedURLException -> 0x0471, SocketTimeoutException -> 0x0467, UnknownHostException -> 0x045d, IllegalArgumentException -> 0x0453, FileNotFoundException -> 0x0449, IOException -> 0x043f, Throwable -> 0x0435, all -> 0x042b }
            java.lang.String r4 = r4.toString()     // Catch:{ MalformedURLException -> 0x0471, SocketTimeoutException -> 0x0467, UnknownHostException -> 0x045d, IllegalArgumentException -> 0x0453, FileNotFoundException -> 0x0449, IOException -> 0x043f, Throwable -> 0x0435, all -> 0x042b }
            r13.i(r4)     // Catch:{ MalformedURLException -> 0x0471, SocketTimeoutException -> 0x0467, UnknownHostException -> 0x045d, IllegalArgumentException -> 0x0453, FileNotFoundException -> 0x0449, IOException -> 0x043f, Throwable -> 0x0435, all -> 0x042b }
            long r4 = (long) r7     // Catch:{ MalformedURLException -> 0x0471, SocketTimeoutException -> 0x0467, UnknownHostException -> 0x045d, IllegalArgumentException -> 0x0453, FileNotFoundException -> 0x0449, IOException -> 0x043f, Throwable -> 0x0435, all -> 0x042b }
            r13.a((long) r4)     // Catch:{ MalformedURLException -> 0x0471, SocketTimeoutException -> 0x0467, UnknownHostException -> 0x045d, IllegalArgumentException -> 0x0453, FileNotFoundException -> 0x0449, IOException -> 0x043f, Throwable -> 0x0435, all -> 0x042b }
            goto L_0x047b
        L_0x042b:
            r0 = move-exception
            r3 = r0
            r5 = r19
            r11 = r33
            r8 = r34
            goto L_0x0f65
        L_0x0435:
            r0 = move-exception
            r3 = r0
            r22 = r8
            r11 = r33
            r16 = r34
            goto L_0x0916
        L_0x043f:
            r0 = move-exception
            r3 = r0
            r22 = r8
            r11 = r33
            r16 = r34
            goto L_0x0a19
        L_0x0449:
            r0 = move-exception
            r3 = r0
            r22 = r8
            r11 = r33
            r16 = r34
            goto L_0x088d
        L_0x0453:
            r0 = move-exception
            r3 = r0
            r22 = r8
            r11 = r33
            r16 = r34
            goto L_0x0899
        L_0x045d:
            r0 = move-exception
            r3 = r0
            r22 = r8
            r11 = r33
            r16 = r34
            goto L_0x08a5
        L_0x0467:
            r0 = move-exception
            r3 = r0
            r22 = r8
            r11 = r33
            r16 = r34
            goto L_0x08b1
        L_0x0471:
            r0 = move-exception
            r3 = r0
            r22 = r8
            r11 = r33
            r16 = r34
            goto L_0x08bd
        L_0x047b:
            r3.close()     // Catch:{ MalformedURLException -> 0x0612, SocketTimeoutException -> 0x060e, UnknownHostException -> 0x060a, IllegalArgumentException -> 0x0606, FileNotFoundException -> 0x0602, IOException -> 0x05fe, Throwable -> 0x05fa, all -> 0x05f5 }
            com.ximalaya.ting.android.player.AudioFile r3 = r1.e     // Catch:{ MalformedURLException -> 0x0612, SocketTimeoutException -> 0x060e, UnknownHostException -> 0x060a, IllegalArgumentException -> 0x0606, FileNotFoundException -> 0x0602, IOException -> 0x05fe, Throwable -> 0x05fa, all -> 0x05f5 }
            java.nio.ByteBuffer r4 = r1.g     // Catch:{ MalformedURLException -> 0x0612, SocketTimeoutException -> 0x060e, UnknownHostException -> 0x060a, IllegalArgumentException -> 0x0606, FileNotFoundException -> 0x0602, IOException -> 0x05fe, Throwable -> 0x05fa, all -> 0x05f5 }
            r3.a((java.nio.ByteBuffer) r4)     // Catch:{ MalformedURLException -> 0x0612, SocketTimeoutException -> 0x060e, UnknownHostException -> 0x060a, IllegalArgumentException -> 0x0606, FileNotFoundException -> 0x0602, IOException -> 0x05fe, Throwable -> 0x05fa, all -> 0x05f5 }
            if (r30 != 0) goto L_0x05d7
            if (r13 == 0) goto L_0x05d7
            java.lang.String r3 = r13.o()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x0497
            r3 = 0
            r13.m(r3)
        L_0x0497:
            java.lang.String r3 = r13.n()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x04a6
            java.lang.String r3 = ""
            r13.l(r3)
        L_0x04a6:
            long r3 = java.lang.System.currentTimeMillis()
            r13.b((long) r3)
            java.lang.String r3 = r13.c()
            if (r3 == 0) goto L_0x04bf
            java.lang.String r3 = r13.c()
            java.lang.String r4 = "success"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x04c4
        L_0x04bf:
            java.lang.String r3 = "failed"
            r13.c(r3)
        L_0x04c4:
            if (r18 != 0) goto L_0x05ce
            int r3 = r2.f()
            r4 = -1
            if (r3 != r4) goto L_0x04d0
            r6 = 1
            goto L_0x05d0
        L_0x04d0:
            if (r3 != 0) goto L_0x0550
            int r3 = r2.h()
            long r3 = (long) r3
            r1.b = r3
            int r3 = r2.j()
            long r3 = (long) r3
            r1.c = r3
            long r3 = r1.b
            long r3 = r3 * r25
            int r5 = (r34 > r3 ? 1 : (r34 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x051b
            java.lang.String r3 = "cdn_connected_too_slow"
            r13.k(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "connected_time="
            r3.append(r4)
            r5 = r34
            float r4 = (float) r5
            float r4 = r4 / r27
            r3.append(r4)
            java.lang.String r4 = "s, connected_time_threshold="
            r3.append(r4)
            long r4 = r1.b
            r3.append(r4)
            java.lang.String r4 = "s"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r13.j(r3)
        L_0x0515:
            r6 = r30
            r19 = 1
            goto L_0x05d0
        L_0x051b:
            long r3 = r1.c
            float r3 = (float) r3
            int r3 = (r3 > r15 ? 1 : (r3 == r15 ? 0 : -1))
            if (r3 <= 0) goto L_0x05ce
            java.lang.String r3 = "cdn_download_too_slow"
            r13.k(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "download_speed="
            r3.append(r4)
            r4 = 1
            float r5 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r15, (boolean) r4)
            r3.append(r5)
            java.lang.String r4 = "KB/s, download_speed_threshold="
            r3.append(r4)
            long r4 = r1.c
            r3.append(r4)
            java.lang.String r4 = "KB/s"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r13.j(r3)
            goto L_0x0515
        L_0x0550:
            r5 = r34
            r4 = 1
            if (r3 != r4) goto L_0x05ce
            int r3 = r2.g()
            long r3 = (long) r3
            r1.b = r3
            int r3 = r2.i()
            long r3 = (long) r3
            r1.c = r3
            long r3 = r1.b
            long r3 = r3 * r25
            int r7 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r7 <= 0) goto L_0x0598
            java.lang.String r3 = "cdn_connected_too_slow"
            r13.k(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "connected_time="
            r3.append(r4)
            float r4 = (float) r5
            float r4 = r4 / r27
            r3.append(r4)
            java.lang.String r4 = "s, connected_time_threshold="
            r3.append(r4)
            long r4 = r1.b
            r3.append(r4)
            java.lang.String r4 = "s"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r13.j(r3)
            goto L_0x0515
        L_0x0598:
            long r3 = r1.c
            float r3 = (float) r3
            int r3 = (r3 > r15 ? 1 : (r3 == r15 ? 0 : -1))
            if (r3 <= 0) goto L_0x05ce
            java.lang.String r3 = "cdn_download_too_slow"
            r13.k(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "download_speed="
            r3.append(r4)
            r4 = 1
            float r5 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r15, (boolean) r4)
            r3.append(r5)
            java.lang.String r4 = "KB/s, download_speed_threshold="
            r3.append(r4)
            long r4 = r1.c
            r3.append(r4)
            java.lang.String r4 = "KB/s"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r13.j(r3)
            goto L_0x0515
        L_0x05ce:
            r6 = r30
        L_0x05d0:
            if (r33 == 0) goto L_0x05d5
            r33.disconnect()
        L_0x05d5:
            r30 = r6
        L_0x05d7:
            if (r13 == 0) goto L_0x05f4
            if (r19 == 0) goto L_0x05f4
            if (r30 != 0) goto L_0x05f4
            java.lang.String r3 = r13.m()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x05f4
            java.lang.String r3 = r13.l()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x05f4
            com.ximalaya.ting.android.player.cdn.CdnUtil.a((com.ximalaya.ting.android.player.cdn.CdnCollectDataForPlay) r13, (com.ximalaya.ting.android.opensdk.model.xdcs.CdnConfigModel) r2)
        L_0x05f4:
            return r36
        L_0x05f5:
            r0 = move-exception
            r5 = r34
            goto L_0x0795
        L_0x05fa:
            r0 = move-exception
            r5 = r34
            goto L_0x0617
        L_0x05fe:
            r0 = move-exception
            r5 = r34
            goto L_0x061f
        L_0x0602:
            r0 = move-exception
            r5 = r34
            goto L_0x0627
        L_0x0606:
            r0 = move-exception
            r5 = r34
            goto L_0x062f
        L_0x060a:
            r0 = move-exception
            r5 = r34
            goto L_0x0637
        L_0x060e:
            r0 = move-exception
            r5 = r34
            goto L_0x063f
        L_0x0612:
            r0 = move-exception
            r5 = r34
            goto L_0x0647
        L_0x0616:
            r0 = move-exception
        L_0x0617:
            r3 = r0
            r16 = r5
            r22 = r8
            goto L_0x0840
        L_0x061e:
            r0 = move-exception
        L_0x061f:
            r3 = r0
            r16 = r5
            r22 = r8
            goto L_0x0846
        L_0x0626:
            r0 = move-exception
        L_0x0627:
            r3 = r0
            r16 = r5
            r22 = r8
            goto L_0x084c
        L_0x062e:
            r0 = move-exception
        L_0x062f:
            r3 = r0
            r16 = r5
            r22 = r8
            goto L_0x0851
        L_0x0636:
            r0 = move-exception
        L_0x0637:
            r3 = r0
            r16 = r5
            r22 = r8
            goto L_0x0856
        L_0x063e:
            r0 = move-exception
        L_0x063f:
            r3 = r0
            r16 = r5
            r22 = r8
            goto L_0x085b
        L_0x0646:
            r0 = move-exception
        L_0x0647:
            r3 = r0
            r16 = r5
            r22 = r8
            goto L_0x0861
        L_0x064e:
            r0 = move-exception
            r33 = r11
            r3 = r0
            r16 = r5
            r22 = r8
            r14 = r32
            goto L_0x0916
        L_0x065a:
            r0 = move-exception
            r33 = r11
            r3 = r0
            r16 = r5
            r22 = r8
            r14 = r32
            goto L_0x0a19
        L_0x0666:
            r0 = move-exception
            r33 = r11
            r3 = r0
            r16 = r5
            r22 = r8
            r14 = r32
            goto L_0x088d
        L_0x0672:
            r0 = move-exception
            r33 = r11
            r3 = r0
            r16 = r5
            r22 = r8
            r14 = r32
            goto L_0x0899
        L_0x067e:
            r0 = move-exception
            r33 = r11
            r3 = r0
            r16 = r5
            r22 = r8
            r14 = r32
            goto L_0x08a5
        L_0x068a:
            r0 = move-exception
            r33 = r11
            r3 = r0
            r16 = r5
            r22 = r8
            r14 = r32
            goto L_0x08b1
        L_0x0696:
            r0 = move-exception
            r33 = r11
            r3 = r0
            r16 = r5
            r22 = r8
            r14 = r32
            goto L_0x08bd
        L_0x06a2:
            r0 = move-exception
            r33 = r11
            r32 = r14
            goto L_0x06cf
        L_0x06a8:
            r0 = move-exception
            r33 = r11
            r32 = r14
            goto L_0x06d9
        L_0x06ae:
            r0 = move-exception
            r33 = r11
            r32 = r14
            goto L_0x06e3
        L_0x06b4:
            r0 = move-exception
            r33 = r11
            r32 = r14
            goto L_0x06ed
        L_0x06ba:
            r0 = move-exception
            r33 = r11
            r32 = r14
            goto L_0x06f7
        L_0x06c0:
            r0 = move-exception
            r33 = r11
            r32 = r14
            goto L_0x0701
        L_0x06c6:
            r0 = move-exception
            r33 = r11
            r32 = r14
            goto L_0x070b
        L_0x06cc:
            r0 = move-exception
            r33 = r11
        L_0x06cf:
            r3 = r0
            r16 = r5
            r22 = r8
            goto L_0x0916
        L_0x06d6:
            r0 = move-exception
            r33 = r11
        L_0x06d9:
            r3 = r0
            r16 = r5
            r22 = r8
            goto L_0x0a19
        L_0x06e0:
            r0 = move-exception
            r33 = r11
        L_0x06e3:
            r3 = r0
            r16 = r5
            r22 = r8
            goto L_0x088d
        L_0x06ea:
            r0 = move-exception
            r33 = r11
        L_0x06ed:
            r3 = r0
            r16 = r5
            r22 = r8
            goto L_0x0899
        L_0x06f4:
            r0 = move-exception
            r33 = r11
        L_0x06f7:
            r3 = r0
            r16 = r5
            r22 = r8
            goto L_0x08a5
        L_0x06fe:
            r0 = move-exception
            r33 = r11
        L_0x0701:
            r3 = r0
            r16 = r5
            r22 = r8
            goto L_0x08b1
        L_0x0708:
            r0 = move-exception
            r33 = r11
        L_0x070b:
            r3 = r0
            r16 = r5
            r22 = r8
            goto L_0x08bd
        L_0x0712:
            r0 = move-exception
            goto L_0x07cb
        L_0x0715:
            r0 = move-exception
            goto L_0x07d5
        L_0x0718:
            r0 = move-exception
            goto L_0x07df
        L_0x071b:
            r0 = move-exception
            goto L_0x07e9
        L_0x071e:
            r0 = move-exception
            goto L_0x07f3
        L_0x0721:
            r0 = move-exception
            goto L_0x07fd
        L_0x0724:
            r0 = move-exception
            goto L_0x0807
        L_0x0727:
            r33 = r11
            java.lang.String r3 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
            r4.<init>()     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
            java.lang.String r8 = "DownloadThread fail contentLength1:"
            r4.append(r8)     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
            r4.append(r7)     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
            java.lang.String r4 = r4.toString()     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r3, (java.lang.Object) r4)     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
            java.io.IOException r3 = new java.io.IOException     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
            r4.<init>()     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
            java.lang.String r8 = "DownloadThread fail contentLength("
            r4.append(r8)     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
            r4.append(r7)     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
            java.lang.String r7 = ")!=DEFAULT_CHUNK_SIZE"
            r4.append(r7)     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
            r7 = 65536(0x10000, float:9.18355E-41)
            r4.append(r7)     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
            java.lang.String r4 = r4.toString()     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
            r3.<init>(r4)     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
            throw r3     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
        L_0x0760:
            r33 = r11
            java.lang.String r3 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
            r4.<init>()     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
            java.lang.String r8 = "DownloadThread fail contentLength0:"
            r4.append(r8)     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
            r4.append(r7)     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
            java.lang.String r4 = r4.toString()     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r3, (java.lang.Object) r4)     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
            java.io.IOException r3 = new java.io.IOException     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
            r4.<init>()     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
            java.lang.String r8 = "DownloadThread fail contentLength("
            r4.append(r8)     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
            r4.append(r7)     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
            java.lang.String r7 = ") <= 0"
            r4.append(r7)     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
            java.lang.String r4 = r4.toString()     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
            r3.<init>(r4)     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
            throw r3     // Catch:{ MalformedURLException -> 0x07bd, SocketTimeoutException -> 0x07b7, UnknownHostException -> 0x07b1, IllegalArgumentException -> 0x07ab, FileNotFoundException -> 0x07a5, IOException -> 0x079f, Throwable -> 0x0799, all -> 0x0794 }
        L_0x0794:
            r0 = move-exception
        L_0x0795:
            r3 = r0
            r8 = r5
            goto L_0x0838
        L_0x0799:
            r0 = move-exception
            r3 = r0
            r16 = r5
            goto L_0x0840
        L_0x079f:
            r0 = move-exception
            r3 = r0
            r16 = r5
            goto L_0x0846
        L_0x07a5:
            r0 = move-exception
            r3 = r0
            r16 = r5
            goto L_0x084c
        L_0x07ab:
            r0 = move-exception
            r3 = r0
            r16 = r5
            goto L_0x0851
        L_0x07b1:
            r0 = move-exception
            r3 = r0
            r16 = r5
            goto L_0x0856
        L_0x07b7:
            r0 = move-exception
            r3 = r0
            r16 = r5
            goto L_0x085b
        L_0x07bd:
            r0 = move-exception
            r3 = r0
            r16 = r5
            goto L_0x0861
        L_0x07c3:
            r0 = move-exception
            r33 = r11
            goto L_0x018a
        L_0x07c8:
            r0 = move-exception
            r31 = r8
        L_0x07cb:
            r33 = r11
        L_0x07cd:
            r3 = r0
            r16 = r5
            goto L_0x0916
        L_0x07d2:
            r0 = move-exception
            r31 = r8
        L_0x07d5:
            r33 = r11
        L_0x07d7:
            r3 = r0
            r16 = r5
            goto L_0x0a19
        L_0x07dc:
            r0 = move-exception
            r31 = r8
        L_0x07df:
            r33 = r11
        L_0x07e1:
            r3 = r0
            r16 = r5
            goto L_0x088d
        L_0x07e6:
            r0 = move-exception
            r31 = r8
        L_0x07e9:
            r33 = r11
        L_0x07eb:
            r3 = r0
            r16 = r5
            goto L_0x0899
        L_0x07f0:
            r0 = move-exception
            r31 = r8
        L_0x07f3:
            r33 = r11
        L_0x07f5:
            r3 = r0
            r16 = r5
            goto L_0x08a5
        L_0x07fa:
            r0 = move-exception
            r31 = r8
        L_0x07fd:
            r33 = r11
        L_0x07ff:
            r3 = r0
            r16 = r5
            goto L_0x08b1
        L_0x0804:
            r0 = move-exception
            r31 = r8
        L_0x0807:
            r33 = r11
        L_0x0809:
            r3 = r0
            r16 = r5
            goto L_0x08bd
        L_0x080e:
            r0 = move-exception
            goto L_0x0868
        L_0x0811:
            r0 = move-exception
            goto L_0x0874
        L_0x0814:
            r0 = move-exception
            goto L_0x087e
        L_0x0817:
            r0 = move-exception
            goto L_0x0888
        L_0x081a:
            r0 = move-exception
            goto L_0x0894
        L_0x081d:
            r0 = move-exception
            goto L_0x08a0
        L_0x0820:
            r0 = move-exception
            goto L_0x08ac
        L_0x0823:
            r0 = move-exception
            goto L_0x08b8
        L_0x0826:
            r30 = r6
            r31 = r8
            r33 = r11
            java.io.IOException r3 = new java.io.IOException     // Catch:{ MalformedURLException -> 0x085f, SocketTimeoutException -> 0x0859, UnknownHostException -> 0x0854, IllegalArgumentException -> 0x084f, FileNotFoundException -> 0x084a, IOException -> 0x0844, Throwable -> 0x083e, all -> 0x0834 }
            java.lang.String r4 = "DownloadThread fail httpUrlConnection connect fail httpUrlConnection==null"
            r3.<init>(r4)     // Catch:{ MalformedURLException -> 0x085f, SocketTimeoutException -> 0x0859, UnknownHostException -> 0x0854, IllegalArgumentException -> 0x084f, FileNotFoundException -> 0x084a, IOException -> 0x0844, Throwable -> 0x083e, all -> 0x0834 }
            throw r3     // Catch:{ MalformedURLException -> 0x085f, SocketTimeoutException -> 0x0859, UnknownHostException -> 0x0854, IllegalArgumentException -> 0x084f, FileNotFoundException -> 0x084a, IOException -> 0x0844, Throwable -> 0x083e, all -> 0x0834 }
        L_0x0834:
            r0 = move-exception
            r3 = r0
            r8 = r16
        L_0x0838:
            r5 = r19
            r11 = r33
            goto L_0x0f65
        L_0x083e:
            r0 = move-exception
            r3 = r0
        L_0x0840:
            r11 = r33
            goto L_0x0916
        L_0x0844:
            r0 = move-exception
            r3 = r0
        L_0x0846:
            r11 = r33
            goto L_0x0a19
        L_0x084a:
            r0 = move-exception
            r3 = r0
        L_0x084c:
            r11 = r33
            goto L_0x088d
        L_0x084f:
            r0 = move-exception
            r3 = r0
        L_0x0851:
            r11 = r33
            goto L_0x0899
        L_0x0854:
            r0 = move-exception
            r3 = r0
        L_0x0856:
            r11 = r33
            goto L_0x08a5
        L_0x0859:
            r0 = move-exception
            r3 = r0
        L_0x085b:
            r11 = r33
            goto L_0x08b1
        L_0x085f:
            r0 = move-exception
            r3 = r0
        L_0x0861:
            r11 = r33
            goto L_0x08bd
        L_0x0865:
            r0 = move-exception
            r30 = r6
        L_0x0868:
            r33 = r11
        L_0x086a:
            r3 = r0
            r8 = r16
        L_0x086d:
            r5 = r19
            goto L_0x0f65
        L_0x0871:
            r0 = move-exception
            r30 = r6
        L_0x0874:
            r31 = r8
            r33 = r11
            r3 = r0
            goto L_0x0916
        L_0x087b:
            r0 = move-exception
            r30 = r6
        L_0x087e:
            r31 = r8
            r33 = r11
            r3 = r0
            goto L_0x0a19
        L_0x0885:
            r0 = move-exception
            r30 = r6
        L_0x0888:
            r31 = r8
            r33 = r11
            r3 = r0
        L_0x088d:
            r6 = 0
            goto L_0x0b64
        L_0x0891:
            r0 = move-exception
            r30 = r6
        L_0x0894:
            r31 = r8
            r33 = r11
            r3 = r0
        L_0x0899:
            r6 = 0
            goto L_0x0c26
        L_0x089d:
            r0 = move-exception
            r30 = r6
        L_0x08a0:
            r31 = r8
            r33 = r11
            r3 = r0
        L_0x08a5:
            r6 = 0
            goto L_0x0ce8
        L_0x08a9:
            r0 = move-exception
            r30 = r6
        L_0x08ac:
            r31 = r8
            r33 = r11
            r3 = r0
        L_0x08b1:
            r6 = 0
            goto L_0x0db0
        L_0x08b5:
            r0 = move-exception
            r30 = r6
        L_0x08b8:
            r31 = r8
            r33 = r11
            r3 = r0
        L_0x08bd:
            r6 = 0
            goto L_0x0e8d
        L_0x08c1:
            r0 = move-exception
            r30 = r6
            r31 = r8
            r3 = r0
            goto L_0x0915
        L_0x08c8:
            r0 = move-exception
            r30 = r6
            r31 = r8
            r3 = r0
            goto L_0x0a18
        L_0x08d0:
            r0 = move-exception
            r30 = r6
            r31 = r8
            r3 = r0
        L_0x08d6:
            r6 = 0
            goto L_0x0b63
        L_0x08da:
            r0 = move-exception
            r30 = r6
            r31 = r8
            r3 = r0
        L_0x08e0:
            r6 = 0
            goto L_0x0c25
        L_0x08e4:
            r0 = move-exception
            r30 = r6
            r31 = r8
            r3 = r0
        L_0x08ea:
            r6 = 0
            goto L_0x0ce7
        L_0x08ee:
            r0 = move-exception
            r30 = r6
            r31 = r8
            r3 = r0
        L_0x08f4:
            r6 = 0
            goto L_0x0daf
        L_0x08f8:
            r0 = move-exception
            r30 = r6
            r31 = r8
            r3 = r0
        L_0x08fe:
            r6 = 0
            goto L_0x0e8c
        L_0x0902:
            r0 = move-exception
            r30 = r6
            r3 = r0
        L_0x0906:
            r8 = r16
            r5 = r19
            r11 = 0
            goto L_0x0f65
        L_0x090d:
            r0 = move-exception
            r30 = r6
            r31 = r8
            r3 = r0
            r28 = r20
        L_0x0915:
            r11 = 0
        L_0x0916:
            if (r30 != 0) goto L_0x09aa
            if (r13 == 0) goto L_0x09aa
            java.lang.String r4 = r13.k()     // Catch:{ all -> 0x0f5e }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x0f5e }
            if (r4 == 0) goto L_0x097e
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0f5e }
            r6 = 0
            long r4 = r4 - r22
            r6 = 0
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x093d
            float r6 = (float) r14     // Catch:{ all -> 0x0f5e }
            r7 = 0
            float r6 = r6 + r7
            r8 = 1149239296(0x44800000, float:1024.0)
            float r6 = r6 / r8
            float r8 = (float) r4     // Catch:{ all -> 0x0f5e }
            float r8 = r8 + r7
            float r8 = r8 / r27
            float r6 = r6 / r8
            r15 = r6
        L_0x093d:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0f5e }
            r6.<init>()     // Catch:{ all -> 0x0f5e }
            r7 = 1
            float r8 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r15, (boolean) r7)     // Catch:{ all -> 0x0f5e }
            r6.append(r8)     // Catch:{ all -> 0x0f5e }
            java.lang.String r7 = ""
            r6.append(r7)     // Catch:{ all -> 0x0f5e }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0f5e }
            r13.i(r6)     // Catch:{ all -> 0x0f5e }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0f5e }
            r6.<init>()     // Catch:{ all -> 0x0f5e }
            r6.append(r14)     // Catch:{ all -> 0x0f5e }
            java.lang.String r7 = ""
            r6.append(r7)     // Catch:{ all -> 0x0f5e }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0f5e }
            r13.a((java.lang.String) r6)     // Catch:{ all -> 0x0f5e }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0f5e }
            r6.<init>()     // Catch:{ all -> 0x0f5e }
            r6.append(r4)     // Catch:{ all -> 0x0f5e }
            java.lang.String r4 = ""
            r6.append(r4)     // Catch:{ all -> 0x0f5e }
            java.lang.String r4 = r6.toString()     // Catch:{ all -> 0x0f5e }
            r13.b((java.lang.String) r4)     // Catch:{ all -> 0x0f5e }
        L_0x097e:
            float r4 = r13.p()     // Catch:{ all -> 0x0f5e }
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 > 0) goto L_0x0999
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0f5e }
            r6 = 0
            long r4 = r4 - r28
            float r6 = (float) r4
            r7 = 0
            float r6 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r6, (boolean) r7)     // Catch:{ all -> 0x0d08 }
            r13.a((float) r6)     // Catch:{ all -> 0x0d08 }
            r16 = r4
        L_0x0999:
            java.lang.String r4 = "failed"
            r13.a((java.lang.String) r4)     // Catch:{ all -> 0x0f5e }
            java.lang.String r4 = "cdn_unknown_exception"
            r13.k(r4)     // Catch:{ all -> 0x0f5e }
            java.lang.String r3 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.Throwable) r3)     // Catch:{ all -> 0x0f5e }
            r13.j(r3)     // Catch:{ all -> 0x0f5e }
        L_0x09aa:
            if (r30 != 0) goto L_0x09ee
            if (r13 == 0) goto L_0x09ee
            java.lang.String r3 = r13.o()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x09bc
            r3 = 0
            r13.m(r3)
        L_0x09bc:
            java.lang.String r3 = r13.n()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x09cb
            java.lang.String r3 = ""
            r13.l(r3)
        L_0x09cb:
            long r3 = java.lang.System.currentTimeMillis()
            r13.b((long) r3)
            java.lang.String r3 = r13.c()
            if (r3 == 0) goto L_0x09e4
            java.lang.String r3 = r13.c()
            java.lang.String r4 = "success"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x09e9
        L_0x09e4:
            java.lang.String r3 = "failed"
            r13.c(r3)
        L_0x09e9:
            if (r11 == 0) goto L_0x09ee
            r11.disconnect()
        L_0x09ee:
            if (r13 == 0) goto L_0x0a09
            if (r30 != 0) goto L_0x0a09
            java.lang.String r3 = r13.m()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0a09
            java.lang.String r3 = r13.l()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0a09
            com.ximalaya.ting.android.player.cdn.CdnUtil.a((com.ximalaya.ting.android.player.cdn.CdnCollectDataForPlay) r13, (com.ximalaya.ting.android.opensdk.model.xdcs.CdnConfigModel) r2)
        L_0x0a09:
            r20 = r28
            r5 = 0
            r6 = 0
            goto L_0x0e7f
        L_0x0a10:
            r0 = move-exception
            r30 = r6
            r31 = r8
            r3 = r0
            r28 = r20
        L_0x0a18:
            r11 = 0
        L_0x0a19:
            if (r30 != 0) goto L_0x0adf
            if (r13 == 0) goto L_0x0adf
            java.lang.String r4 = r13.k()     // Catch:{ all -> 0x0f5e }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x0f5e }
            if (r4 == 0) goto L_0x0a82
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0f5e }
            r6 = 0
            long r4 = r4 - r22
            r6 = 0
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x0a40
            float r8 = (float) r14     // Catch:{ all -> 0x0f5e }
            r9 = 0
            float r8 = r8 + r9
            r10 = 1149239296(0x44800000, float:1024.0)
            float r8 = r8 / r10
            float r10 = (float) r4     // Catch:{ all -> 0x0f5e }
            float r10 = r10 + r9
            float r10 = r10 / r27
            float r8 = r8 / r10
            r15 = r8
        L_0x0a40:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x0f5e }
            r8.<init>()     // Catch:{ all -> 0x0f5e }
            r9 = 1
            float r10 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r15, (boolean) r9)     // Catch:{ all -> 0x0f5e }
            r8.append(r10)     // Catch:{ all -> 0x0f5e }
            java.lang.String r9 = ""
            r8.append(r9)     // Catch:{ all -> 0x0f5e }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x0f5e }
            r13.i(r8)     // Catch:{ all -> 0x0f5e }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x0f5e }
            r8.<init>()     // Catch:{ all -> 0x0f5e }
            r8.append(r14)     // Catch:{ all -> 0x0f5e }
            java.lang.String r9 = ""
            r8.append(r9)     // Catch:{ all -> 0x0f5e }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x0f5e }
            r13.a((java.lang.String) r8)     // Catch:{ all -> 0x0f5e }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x0f5e }
            r8.<init>()     // Catch:{ all -> 0x0f5e }
            r8.append(r4)     // Catch:{ all -> 0x0f5e }
            java.lang.String r4 = ""
            r8.append(r4)     // Catch:{ all -> 0x0f5e }
            java.lang.String r4 = r8.toString()     // Catch:{ all -> 0x0f5e }
            r13.b((java.lang.String) r4)     // Catch:{ all -> 0x0f5e }
            goto L_0x0a84
        L_0x0a82:
            r6 = 0
        L_0x0a84:
            float r4 = r13.p()     // Catch:{ all -> 0x0f5e }
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 > 0) goto L_0x0a9f
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0f5e }
            r8 = 0
            long r4 = r4 - r28
            float r8 = (float) r4
            r9 = 0
            float r8 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r8, (boolean) r9)     // Catch:{ all -> 0x0d08 }
            r13.a((float) r8)     // Catch:{ all -> 0x0d08 }
            r16 = r4
        L_0x0a9f:
            java.lang.String r4 = r3.getMessage()     // Catch:{ all -> 0x0f5e }
            if (r4 == 0) goto L_0x0acd
            java.lang.String r4 = r3.getMessage()     // Catch:{ all -> 0x0f5e }
            java.lang.String r5 = "ENOSPC"
            boolean r4 = r4.contains(r5)     // Catch:{ all -> 0x0f5e }
            if (r4 != 0) goto L_0x0abd
            java.lang.String r4 = r3.getMessage()     // Catch:{ all -> 0x0f5e }
            java.lang.String r5 = "EACCES"
            boolean r4 = r4.contains(r5)     // Catch:{ all -> 0x0f5e }
            if (r4 == 0) goto L_0x0acd
        L_0x0abd:
            java.lang.String r4 = "0"
            r13.a((java.lang.String) r4)     // Catch:{ all -> 0x0f5e }
            java.lang.String r4 = "0"
            r13.b((java.lang.String) r4)     // Catch:{ all -> 0x0f5e }
            java.lang.String r4 = "system_exception"
            r13.k(r4)     // Catch:{ all -> 0x0f5e }
            goto L_0x0ad2
        L_0x0acd:
            java.lang.String r4 = "cdn_io_exception"
            r13.k(r4)     // Catch:{ all -> 0x0f5e }
        L_0x0ad2:
            java.lang.String r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.Throwable) r3)     // Catch:{ all -> 0x0f5e }
            r13.j(r4)     // Catch:{ all -> 0x0f5e }
            java.lang.String r4 = "failed"
            r13.c(r4)     // Catch:{ all -> 0x0f5e }
            goto L_0x0ae1
        L_0x0adf:
            r6 = 0
        L_0x0ae1:
            java.lang.String r4 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x0f5e }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0f5e }
            r5.<init>()     // Catch:{ all -> 0x0f5e }
            java.lang.String r8 = "DownloadThread IOException:"
            r5.append(r8)     // Catch:{ all -> 0x0f5e }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0f5e }
            r5.append(r3)     // Catch:{ all -> 0x0f5e }
            java.lang.String r3 = r5.toString()     // Catch:{ all -> 0x0f5e }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r4, (java.lang.Object) r3)     // Catch:{ all -> 0x0f5e }
            if (r30 != 0) goto L_0x0b3f
            if (r13 == 0) goto L_0x0b3f
            java.lang.String r3 = r13.o()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x0b0d
            r3 = 0
            r13.m(r3)
        L_0x0b0d:
            java.lang.String r3 = r13.n()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x0b1c
            java.lang.String r3 = ""
            r13.l(r3)
        L_0x0b1c:
            long r3 = java.lang.System.currentTimeMillis()
            r13.b((long) r3)
            java.lang.String r3 = r13.c()
            if (r3 == 0) goto L_0x0b35
            java.lang.String r3 = r13.c()
            java.lang.String r4 = "success"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x0b3a
        L_0x0b35:
            java.lang.String r3 = "failed"
            r13.c(r3)
        L_0x0b3a:
            if (r11 == 0) goto L_0x0b3f
            r11.disconnect()
        L_0x0b3f:
            if (r13 == 0) goto L_0x0e7c
            if (r30 != 0) goto L_0x0e7c
            java.lang.String r3 = r13.m()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0e7c
            java.lang.String r3 = r13.l()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0e7c
            goto L_0x0e79
        L_0x0b59:
            r0 = move-exception
            r30 = r6
            r31 = r8
            r6 = 0
            r3 = r0
            r28 = r20
        L_0x0b63:
            r11 = 0
        L_0x0b64:
            if (r30 != 0) goto L_0x0ba3
            if (r13 == 0) goto L_0x0ba3
            float r4 = r13.p()     // Catch:{ all -> 0x0f5e }
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 > 0) goto L_0x0b83
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0f5e }
            r8 = 0
            long r4 = r4 - r28
            float r8 = (float) r4
            r9 = 0
            float r8 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r8, (boolean) r9)     // Catch:{ all -> 0x0d08 }
            r13.a((float) r8)     // Catch:{ all -> 0x0d08 }
            r16 = r4
        L_0x0b83:
            java.lang.String r4 = "system_exception"
            r13.k(r4)     // Catch:{ all -> 0x0f5e }
            java.lang.String r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.Throwable) r3)     // Catch:{ all -> 0x0f5e }
            r13.j(r4)     // Catch:{ all -> 0x0f5e }
            java.lang.String r4 = "0.0"
            r13.i(r4)     // Catch:{ all -> 0x0f5e }
            java.lang.String r4 = "0"
            r13.a((java.lang.String) r4)     // Catch:{ all -> 0x0f5e }
            java.lang.String r4 = "0"
            r13.b((java.lang.String) r4)     // Catch:{ all -> 0x0f5e }
            java.lang.String r4 = "failed"
            r13.c(r4)     // Catch:{ all -> 0x0f5e }
        L_0x0ba3:
            java.lang.String r4 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x0f5e }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0f5e }
            r5.<init>()     // Catch:{ all -> 0x0f5e }
            java.lang.String r8 = "DownloadThread IOException:"
            r5.append(r8)     // Catch:{ all -> 0x0f5e }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0f5e }
            r5.append(r3)     // Catch:{ all -> 0x0f5e }
            java.lang.String r3 = r5.toString()     // Catch:{ all -> 0x0f5e }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r4, (java.lang.Object) r3)     // Catch:{ all -> 0x0f5e }
            if (r30 != 0) goto L_0x0c01
            if (r13 == 0) goto L_0x0c01
            java.lang.String r3 = r13.o()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x0bcf
            r3 = 0
            r13.m(r3)
        L_0x0bcf:
            java.lang.String r3 = r13.n()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x0bde
            java.lang.String r3 = ""
            r13.l(r3)
        L_0x0bde:
            long r3 = java.lang.System.currentTimeMillis()
            r13.b((long) r3)
            java.lang.String r3 = r13.c()
            if (r3 == 0) goto L_0x0bf7
            java.lang.String r3 = r13.c()
            java.lang.String r4 = "success"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x0bfc
        L_0x0bf7:
            java.lang.String r3 = "failed"
            r13.c(r3)
        L_0x0bfc:
            if (r11 == 0) goto L_0x0c01
            r11.disconnect()
        L_0x0c01:
            if (r13 == 0) goto L_0x0e7c
            if (r30 != 0) goto L_0x0e7c
            java.lang.String r3 = r13.m()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0e7c
            java.lang.String r3 = r13.l()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0e7c
            goto L_0x0e79
        L_0x0c1b:
            r0 = move-exception
            r30 = r6
            r31 = r8
            r6 = 0
            r3 = r0
            r28 = r20
        L_0x0c25:
            r11 = 0
        L_0x0c26:
            if (r30 != 0) goto L_0x0c65
            if (r13 == 0) goto L_0x0c65
            float r4 = r13.p()     // Catch:{ all -> 0x0f5e }
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 > 0) goto L_0x0c45
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0f5e }
            r8 = 0
            long r4 = r4 - r28
            float r8 = (float) r4
            r9 = 0
            float r8 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r8, (boolean) r9)     // Catch:{ all -> 0x0d08 }
            r13.a((float) r8)     // Catch:{ all -> 0x0d08 }
            r16 = r4
        L_0x0c45:
            java.lang.String r4 = "dns_fail"
            r13.k(r4)     // Catch:{ all -> 0x0f5e }
            java.lang.String r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.Throwable) r3)     // Catch:{ all -> 0x0f5e }
            r13.j(r4)     // Catch:{ all -> 0x0f5e }
            java.lang.String r4 = "0.0"
            r13.i(r4)     // Catch:{ all -> 0x0f5e }
            java.lang.String r4 = "0"
            r13.a((java.lang.String) r4)     // Catch:{ all -> 0x0f5e }
            java.lang.String r4 = "0"
            r13.b((java.lang.String) r4)     // Catch:{ all -> 0x0f5e }
            java.lang.String r4 = "failed"
            r13.c(r4)     // Catch:{ all -> 0x0f5e }
        L_0x0c65:
            java.lang.String r4 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x0f5e }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0f5e }
            r5.<init>()     // Catch:{ all -> 0x0f5e }
            java.lang.String r8 = "DownloadThread IOException:"
            r5.append(r8)     // Catch:{ all -> 0x0f5e }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0f5e }
            r5.append(r3)     // Catch:{ all -> 0x0f5e }
            java.lang.String r3 = r5.toString()     // Catch:{ all -> 0x0f5e }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r4, (java.lang.Object) r3)     // Catch:{ all -> 0x0f5e }
            if (r30 != 0) goto L_0x0cc3
            if (r13 == 0) goto L_0x0cc3
            java.lang.String r3 = r13.o()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x0c91
            r3 = 0
            r13.m(r3)
        L_0x0c91:
            java.lang.String r3 = r13.n()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x0ca0
            java.lang.String r3 = ""
            r13.l(r3)
        L_0x0ca0:
            long r3 = java.lang.System.currentTimeMillis()
            r13.b((long) r3)
            java.lang.String r3 = r13.c()
            if (r3 == 0) goto L_0x0cb9
            java.lang.String r3 = r13.c()
            java.lang.String r4 = "success"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x0cbe
        L_0x0cb9:
            java.lang.String r3 = "failed"
            r13.c(r3)
        L_0x0cbe:
            if (r11 == 0) goto L_0x0cc3
            r11.disconnect()
        L_0x0cc3:
            if (r13 == 0) goto L_0x0e7c
            if (r30 != 0) goto L_0x0e7c
            java.lang.String r3 = r13.m()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0e7c
            java.lang.String r3 = r13.l()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0e7c
            goto L_0x0e79
        L_0x0cdd:
            r0 = move-exception
            r30 = r6
            r31 = r8
            r6 = 0
            r3 = r0
            r28 = r20
        L_0x0ce7:
            r11 = 0
        L_0x0ce8:
            if (r30 != 0) goto L_0x0d2d
            if (r13 == 0) goto L_0x0d2d
            float r4 = r13.p()     // Catch:{ all -> 0x0f5e }
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 > 0) goto L_0x0d0d
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0f5e }
            r8 = 0
            long r4 = r4 - r28
            float r8 = (float) r4
            r9 = 0
            float r8 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r8, (boolean) r9)     // Catch:{ all -> 0x0d08 }
            r13.a((float) r8)     // Catch:{ all -> 0x0d08 }
            r16 = r4
            goto L_0x0d0d
        L_0x0d08:
            r0 = move-exception
            r3 = r0
            r8 = r4
            goto L_0x0f62
        L_0x0d0d:
            java.lang.String r4 = "dns_fail"
            r13.k(r4)     // Catch:{ all -> 0x0f5e }
            java.lang.String r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.Throwable) r3)     // Catch:{ all -> 0x0f5e }
            r13.j(r4)     // Catch:{ all -> 0x0f5e }
            java.lang.String r4 = "0.0"
            r13.i(r4)     // Catch:{ all -> 0x0f5e }
            java.lang.String r4 = "0"
            r13.a((java.lang.String) r4)     // Catch:{ all -> 0x0f5e }
            java.lang.String r4 = "0"
            r13.b((java.lang.String) r4)     // Catch:{ all -> 0x0f5e }
            java.lang.String r4 = "failed"
            r13.c(r4)     // Catch:{ all -> 0x0f5e }
        L_0x0d2d:
            java.lang.String r4 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x0f5e }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0f5e }
            r5.<init>()     // Catch:{ all -> 0x0f5e }
            java.lang.String r8 = "DownloadThread IOException:"
            r5.append(r8)     // Catch:{ all -> 0x0f5e }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0f5e }
            r5.append(r3)     // Catch:{ all -> 0x0f5e }
            java.lang.String r3 = r5.toString()     // Catch:{ all -> 0x0f5e }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r4, (java.lang.Object) r3)     // Catch:{ all -> 0x0f5e }
            if (r30 != 0) goto L_0x0d8b
            if (r13 == 0) goto L_0x0d8b
            java.lang.String r3 = r13.o()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x0d59
            r3 = 0
            r13.m(r3)
        L_0x0d59:
            java.lang.String r3 = r13.n()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x0d68
            java.lang.String r3 = ""
            r13.l(r3)
        L_0x0d68:
            long r3 = java.lang.System.currentTimeMillis()
            r13.b((long) r3)
            java.lang.String r3 = r13.c()
            if (r3 == 0) goto L_0x0d81
            java.lang.String r3 = r13.c()
            java.lang.String r4 = "success"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x0d86
        L_0x0d81:
            java.lang.String r3 = "failed"
            r13.c(r3)
        L_0x0d86:
            if (r11 == 0) goto L_0x0d8b
            r11.disconnect()
        L_0x0d8b:
            if (r13 == 0) goto L_0x0e7c
            if (r30 != 0) goto L_0x0e7c
            java.lang.String r3 = r13.m()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0e7c
            java.lang.String r3 = r13.l()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0e7c
            goto L_0x0e79
        L_0x0da5:
            r0 = move-exception
            r30 = r6
            r31 = r8
            r6 = 0
            r3 = r0
            r28 = r20
        L_0x0daf:
            r11 = 0
        L_0x0db0:
            if (r30 != 0) goto L_0x0e1d
            if (r13 == 0) goto L_0x0e1d
            java.lang.String r4 = "0.0"
            r13.i(r4)     // Catch:{ all -> 0x0f5e }
            float r4 = r13.p()     // Catch:{ all -> 0x0f5e }
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 > 0) goto L_0x0dd4
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0f5e }
            r8 = 0
            long r4 = r4 - r28
            float r8 = (float) r4
            r9 = 0
            float r8 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r8, (boolean) r9)     // Catch:{ all -> 0x0d08 }
            r13.a((float) r8)     // Catch:{ all -> 0x0d08 }
            r16 = r4
        L_0x0dd4:
            java.lang.String r4 = r3.getMessage()     // Catch:{ all -> 0x0f5e }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x0f5e }
            if (r4 == 0) goto L_0x0dfe
            java.lang.String r4 = "cdn_socket_timeout"
            r13.k(r4)     // Catch:{ all -> 0x0f5e }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0f5e }
            r4.<init>()     // Catch:{ all -> 0x0f5e }
            java.lang.String r5 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x0f5e }
            r4.append(r5)     // Catch:{ all -> 0x0f5e }
            java.lang.String r3 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.Throwable) r3)     // Catch:{ all -> 0x0f5e }
            r4.append(r3)     // Catch:{ all -> 0x0f5e }
            java.lang.String r3 = r4.toString()     // Catch:{ all -> 0x0f5e }
            r13.j(r3)     // Catch:{ all -> 0x0f5e }
            goto L_0x0e0a
        L_0x0dfe:
            java.lang.String r4 = "cdn_connect_timeout"
            r13.k(r4)     // Catch:{ all -> 0x0f5e }
            java.lang.String r3 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.Throwable) r3)     // Catch:{ all -> 0x0f5e }
            r13.j(r3)     // Catch:{ all -> 0x0f5e }
        L_0x0e0a:
            java.lang.String r3 = "0"
            r13.a((java.lang.String) r3)     // Catch:{ all -> 0x0f5e }
            java.lang.String r3 = "0"
            r13.b((java.lang.String) r3)     // Catch:{ all -> 0x0f5e }
            java.lang.String r3 = "failed"
            r13.c(r3)     // Catch:{ all -> 0x0f5e }
            r3 = 1
            r13.a((boolean) r3)     // Catch:{ all -> 0x0f5e }
        L_0x0e1d:
            if (r30 != 0) goto L_0x0e61
            if (r13 == 0) goto L_0x0e61
            java.lang.String r3 = r13.o()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x0e2f
            r3 = 0
            r13.m(r3)
        L_0x0e2f:
            java.lang.String r3 = r13.n()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x0e3e
            java.lang.String r3 = ""
            r13.l(r3)
        L_0x0e3e:
            long r3 = java.lang.System.currentTimeMillis()
            r13.b((long) r3)
            java.lang.String r3 = r13.c()
            if (r3 == 0) goto L_0x0e57
            java.lang.String r3 = r13.c()
            java.lang.String r4 = "success"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x0e5c
        L_0x0e57:
            java.lang.String r3 = "failed"
            r13.c(r3)
        L_0x0e5c:
            if (r11 == 0) goto L_0x0e61
            r11.disconnect()
        L_0x0e61:
            if (r13 == 0) goto L_0x0e7c
            if (r30 != 0) goto L_0x0e7c
            java.lang.String r3 = r13.m()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0e7c
            java.lang.String r3 = r13.l()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0e7c
        L_0x0e79:
            com.ximalaya.ting.android.player.cdn.CdnUtil.a((com.ximalaya.ting.android.player.cdn.CdnCollectDataForPlay) r13, (com.ximalaya.ting.android.opensdk.model.xdcs.CdnConfigModel) r2)
        L_0x0e7c:
            r20 = r28
            r5 = 0
        L_0x0e7f:
            r10 = 0
            goto L_0x0f50
        L_0x0e82:
            r0 = move-exception
            r30 = r6
            r31 = r8
            r6 = 0
            r3 = r0
            r28 = r20
        L_0x0e8c:
            r11 = 0
        L_0x0e8d:
            if (r30 != 0) goto L_0x0ed3
            if (r13 == 0) goto L_0x0ed3
            float r4 = r13.p()     // Catch:{ all -> 0x0f5e }
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 > 0) goto L_0x0eb1
            long r8 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0f5e }
            r4 = 0
            long r8 = r8 - r28
            float r4 = (float) r8
            r10 = 0
            float r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r4, (boolean) r10)     // Catch:{ all -> 0x0ead }
            r13.a((float) r4)     // Catch:{ all -> 0x0ead }
            r16 = r8
            goto L_0x0eb2
        L_0x0ead:
            r0 = move-exception
            r3 = r0
            goto L_0x0f62
        L_0x0eb1:
            r10 = 0
        L_0x0eb2:
            java.lang.String r4 = "cdn_connect_fail "
            r13.k(r4)     // Catch:{ all -> 0x0f5e }
            java.lang.String r4 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((java.lang.Throwable) r3)     // Catch:{ all -> 0x0f5e }
            r13.j(r4)     // Catch:{ all -> 0x0f5e }
            java.lang.String r4 = "0.0"
            r13.i(r4)     // Catch:{ all -> 0x0f5e }
            java.lang.String r4 = "0"
            r13.a((java.lang.String) r4)     // Catch:{ all -> 0x0f5e }
            java.lang.String r4 = "0"
            r13.b((java.lang.String) r4)     // Catch:{ all -> 0x0f5e }
            java.lang.String r4 = "failed"
            r13.c(r4)     // Catch:{ all -> 0x0f5e }
            goto L_0x0ed5
        L_0x0ed3:
            r5 = 0
            r10 = 0
        L_0x0ed5:
            java.lang.String r4 = com.ximalaya.ting.android.player.XMediaplayerJNI.Tag     // Catch:{ all -> 0x0f5e }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x0f5e }
            r8.<init>()     // Catch:{ all -> 0x0f5e }
            java.lang.String r9 = "DownloadThread MalformedURLException:"
            r8.append(r9)     // Catch:{ all -> 0x0f5e }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0f5e }
            r8.append(r3)     // Catch:{ all -> 0x0f5e }
            java.lang.String r3 = r8.toString()     // Catch:{ all -> 0x0f5e }
            com.ximalaya.ting.android.player.Logger.a((java.lang.String) r4, (java.lang.Object) r3)     // Catch:{ all -> 0x0f5e }
            if (r30 != 0) goto L_0x0f33
            if (r13 == 0) goto L_0x0f33
            java.lang.String r3 = r13.o()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x0f01
            r3 = 0
            r13.m(r3)
        L_0x0f01:
            java.lang.String r3 = r13.n()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x0f10
            java.lang.String r3 = ""
            r13.l(r3)
        L_0x0f10:
            long r3 = java.lang.System.currentTimeMillis()
            r13.b((long) r3)
            java.lang.String r3 = r13.c()
            if (r3 == 0) goto L_0x0f29
            java.lang.String r3 = r13.c()
            java.lang.String r4 = "success"
            boolean r3 = r3.contains(r4)
            if (r3 != 0) goto L_0x0f2e
        L_0x0f29:
            java.lang.String r3 = "failed"
            r13.c(r3)
        L_0x0f2e:
            if (r11 == 0) goto L_0x0f33
            r11.disconnect()
        L_0x0f33:
            if (r13 == 0) goto L_0x0f4e
            if (r30 != 0) goto L_0x0f4e
            java.lang.String r3 = r13.m()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0f4e
            java.lang.String r3 = r13.l()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0f4e
            com.ximalaya.ting.android.player.cdn.CdnUtil.a((com.ximalaya.ting.android.player.cdn.CdnCollectDataForPlay) r13, (com.ximalaya.ting.android.opensdk.model.xdcs.CdnConfigModel) r2)
        L_0x0f4e:
            r20 = r28
        L_0x0f50:
            r7 = r24
            r6 = r30
            r8 = r31
            r3 = 0
            r5 = 1
            r18 = 1
            r19 = 1
            goto L_0x003d
        L_0x0f5e:
            r0 = move-exception
            r3 = r0
            r8 = r16
        L_0x0f62:
            r5 = 1
            r18 = 1
        L_0x0f65:
            if (r30 != 0) goto L_0x10b1
            if (r13 == 0) goto L_0x10b1
            java.lang.String r4 = r13.o()
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 == 0) goto L_0x0f77
            r4 = 0
            r13.m(r4)
        L_0x0f77:
            java.lang.String r4 = r13.n()
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 == 0) goto L_0x0f86
            java.lang.String r4 = ""
            r13.l(r4)
        L_0x0f86:
            long r6 = java.lang.System.currentTimeMillis()
            r13.b((long) r6)
            java.lang.String r4 = r13.c()
            if (r4 == 0) goto L_0x0f9f
            java.lang.String r4 = r13.c()
            java.lang.String r6 = "success"
            boolean r4 = r4.contains(r6)
            if (r4 != 0) goto L_0x0fa4
        L_0x0f9f:
            java.lang.String r4 = "failed"
            r13.c(r4)
        L_0x0fa4:
            if (r18 != 0) goto L_0x10a8
            int r4 = r2.f()
            r6 = -1
            if (r4 != r6) goto L_0x0fb0
            r6 = 1
            goto L_0x10aa
        L_0x0fb0:
            if (r4 != 0) goto L_0x102d
            int r4 = r2.h()
            long r6 = (long) r4
            r1.b = r6
            int r4 = r2.j()
            long r6 = (long) r4
            r1.c = r6
            long r6 = r1.b
            long r6 = r6 * r25
            int r4 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r4 <= 0) goto L_0x0ff8
            java.lang.String r4 = "cdn_connected_too_slow"
            r13.k(r4)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "connected_time="
            r4.append(r5)
            float r5 = (float) r8
            float r5 = r5 / r27
            r4.append(r5)
            java.lang.String r5 = "s, connected_time_threshold="
            r4.append(r5)
            long r5 = r1.b
            r4.append(r5)
            java.lang.String r5 = "s"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r13.j(r4)
        L_0x0ff3:
            r6 = r30
            r5 = 1
            goto L_0x10aa
        L_0x0ff8:
            long r6 = r1.c
            float r4 = (float) r6
            int r4 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r4 <= 0) goto L_0x10a8
            java.lang.String r4 = "cdn_download_too_slow"
            r13.k(r4)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "download_speed="
            r4.append(r5)
            r5 = 1
            float r6 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r15, (boolean) r5)
            r4.append(r6)
            java.lang.String r5 = "KB/s, download_speed_threshold="
            r4.append(r5)
            long r5 = r1.c
            r4.append(r5)
            java.lang.String r5 = "KB/s"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r13.j(r4)
            goto L_0x0ff3
        L_0x102d:
            r6 = 1
            if (r4 != r6) goto L_0x10a8
            int r4 = r2.g()
            long r6 = (long) r4
            r1.b = r6
            int r4 = r2.i()
            long r6 = (long) r4
            r1.c = r6
            long r6 = r1.b
            long r6 = r6 * r25
            int r4 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r4 <= 0) goto L_0x1072
            java.lang.String r4 = "cdn_connected_too_slow"
            r13.k(r4)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "connected_time="
            r4.append(r5)
            float r5 = (float) r8
            float r5 = r5 / r27
            r4.append(r5)
            java.lang.String r5 = "s, connected_time_threshold="
            r4.append(r5)
            long r5 = r1.b
            r4.append(r5)
            java.lang.String r5 = "s"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r13.j(r4)
            goto L_0x0ff3
        L_0x1072:
            long r6 = r1.c
            float r4 = (float) r6
            int r4 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r4 <= 0) goto L_0x10a8
            java.lang.String r4 = "cdn_download_too_slow"
            r13.k(r4)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "download_speed="
            r4.append(r5)
            r8 = 1
            float r5 = com.ximalaya.ting.android.player.cdn.CdnUtil.a((float) r15, (boolean) r8)
            r4.append(r5)
            java.lang.String r5 = "KB/s, download_speed_threshold="
            r4.append(r5)
            long r5 = r1.c
            r4.append(r5)
            java.lang.String r5 = "KB/s"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r13.j(r4)
            goto L_0x0ff3
        L_0x10a8:
            r6 = r30
        L_0x10aa:
            if (r11 == 0) goto L_0x10af
            r11.disconnect()
        L_0x10af:
            r30 = r6
        L_0x10b1:
            if (r13 == 0) goto L_0x10ce
            if (r5 == 0) goto L_0x10ce
            if (r30 != 0) goto L_0x10ce
            java.lang.String r4 = r13.m()
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 != 0) goto L_0x10ce
            java.lang.String r4 = r13.l()
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 != 0) goto L_0x10ce
            com.ximalaya.ting.android.player.cdn.CdnUtil.a((com.ximalaya.ting.android.player.cdn.CdnCollectDataForPlay) r13, (com.ximalaya.ting.android.opensdk.model.xdcs.CdnConfigModel) r2)
        L_0x10ce:
            throw r3
        L_0x10cf:
            r2 = -1
            return r2
        L_0x10d1:
            r2 = -1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.player.DownloadThread.a():int");
    }

    public void b() {
        this.h = true;
    }
}
