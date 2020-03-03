package android.support.v8.renderscript;

import android.content.res.Resources;

public class ScriptC extends Script {
    private static final String TAG = "ScriptC";

    protected ScriptC(int i, RenderScript renderScript) {
        super(i, renderScript);
    }

    protected ScriptC(RenderScript renderScript, Resources resources, int i) {
        super(0, renderScript);
        if (RenderScript.isNative) {
            this.mT = new ScriptCThunker((RenderScriptThunker) renderScript, resources, i);
            return;
        }
        int internalCreate = internalCreate(renderScript, resources, i);
        if (internalCreate != 0) {
            setID(internalCreate);
            return;
        }
        throw new RSRuntimeException("Loading of ScriptC script failed.");
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0045 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized int internalCreate(android.support.v8.renderscript.RenderScript r8, android.content.res.Resources r9, int r10) {
        /*
            java.lang.Class<android.support.v8.renderscript.ScriptC> r0 = android.support.v8.renderscript.ScriptC.class
            monitor-enter(r0)
            java.io.InputStream r1 = r9.openRawResource(r10)     // Catch:{ all -> 0x004b }
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]     // Catch:{ all -> 0x0040 }
            r3 = 0
            r4 = 0
        L_0x000d:
            int r5 = r2.length     // Catch:{ all -> 0x0040 }
            int r5 = r5 - r4
            if (r5 != 0) goto L_0x001f
            int r5 = r2.length     // Catch:{ all -> 0x0040 }
            int r5 = r5 * 2
            byte[] r5 = new byte[r5]     // Catch:{ all -> 0x0040 }
            int r6 = r2.length     // Catch:{ all -> 0x0040 }
            java.lang.System.arraycopy(r2, r3, r5, r3, r6)     // Catch:{ all -> 0x0040 }
            int r2 = r5.length     // Catch:{ all -> 0x0040 }
            int r2 = r2 - r4
            r7 = r5
            r5 = r2
            r2 = r7
        L_0x001f:
            int r5 = r1.read(r2, r4, r5)     // Catch:{ all -> 0x0040 }
            if (r5 > 0) goto L_0x003e
            r1.close()     // Catch:{ IOException -> 0x0045 }
            java.lang.String r9 = r9.getResourceEntryName(r10)     // Catch:{ all -> 0x004b }
            android.content.Context r10 = r8.getApplicationContext()     // Catch:{ all -> 0x004b }
            java.io.File r10 = r10.getCacheDir()     // Catch:{ all -> 0x004b }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x004b }
            int r8 = r8.nScriptCCreate(r9, r10, r2, r4)     // Catch:{ all -> 0x004b }
            monitor-exit(r0)
            return r8
        L_0x003e:
            int r4 = r4 + r5
            goto L_0x000d
        L_0x0040:
            r8 = move-exception
            r1.close()     // Catch:{ IOException -> 0x0045 }
            throw r8     // Catch:{ IOException -> 0x0045 }
        L_0x0045:
            android.content.res.Resources$NotFoundException r8 = new android.content.res.Resources$NotFoundException     // Catch:{ all -> 0x004b }
            r8.<init>()     // Catch:{ all -> 0x004b }
            throw r8     // Catch:{ all -> 0x004b }
        L_0x004b:
            r8 = move-exception
            monitor-exit(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v8.renderscript.ScriptC.internalCreate(android.support.v8.renderscript.RenderScript, android.content.res.Resources, int):int");
    }
}
