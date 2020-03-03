package no.nordicsemi.android.dfu.internal;

import android.support.annotation.NonNull;
import android.util.Log;
import com.google.gson.Gson;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import no.nordicsemi.android.dfu.internal.manifest.Manifest;
import no.nordicsemi.android.dfu.internal.manifest.ManifestFile;

public class ArchiveInputStream extends ZipInputStream {
    private static final String APPLICATION_BIN = "application.bin";
    private static final String APPLICATION_HEX = "application.hex";
    private static final String APPLICATION_INIT = "application.dat";
    private static final String BOOTLOADER_BIN = "bootloader.bin";
    private static final String BOOTLOADER_HEX = "bootloader.hex";
    private static final String MANIFEST = "manifest.json";
    private static final String SOFTDEVICE_BIN = "softdevice.bin";
    private static final String SOFTDEVICE_HEX = "softdevice.hex";
    private static final String SYSTEM_INIT = "system.dat";
    private static final String TAG = "DfuArchiveInputStream";
    private byte[] applicationBytes;
    private byte[] applicationInitBytes;
    private int applicationSize;
    private byte[] bootloaderBytes;
    private int bootloaderSize;
    private int bytesRead = 0;
    private int bytesReadFromCurrentSource = 0;
    private int bytesReadFromMarkedSource;
    private CRC32 crc32 = new CRC32();
    private byte[] currentSource;
    private Map<String, byte[]> entries = new HashMap();
    private Manifest manifest;
    private byte[] markedSource;
    private byte[] softDeviceAndBootloaderBytes;
    private byte[] softDeviceBytes;
    private int softDeviceSize;
    private byte[] systemInitBytes;
    private int type;

    public boolean markSupported() {
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:74:0x021e A[Catch:{ all -> 0x0296 }] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x022e A[Catch:{ all -> 0x0296 }] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0259 A[Catch:{ all -> 0x0296 }] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0269 A[Catch:{ all -> 0x0296 }] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x028e A[SYNTHETIC, Splitter:B:91:0x028e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ArchiveInputStream(java.io.InputStream r4, int r5, int r6) throws java.io.IOException {
        /*
            r3 = this;
            r3.<init>(r4)
            java.util.zip.CRC32 r4 = new java.util.zip.CRC32
            r4.<init>()
            r3.crc32 = r4
            java.util.HashMap r4 = new java.util.HashMap
            r4.<init>()
            r3.entries = r4
            r4 = 0
            r3.bytesRead = r4
            r3.bytesReadFromCurrentSource = r4
            r3.parseZip(r5)     // Catch:{ all -> 0x0296 }
            no.nordicsemi.android.dfu.internal.manifest.Manifest r5 = r3.manifest     // Catch:{ all -> 0x0296 }
            r0 = 1
            if (r5 == 0) goto L_0x01ca
            no.nordicsemi.android.dfu.internal.manifest.Manifest r5 = r3.manifest     // Catch:{ all -> 0x0296 }
            no.nordicsemi.android.dfu.internal.manifest.FileInfo r5 = r5.getApplicationInfo()     // Catch:{ all -> 0x0296 }
            if (r5 == 0) goto L_0x007d
            if (r6 == 0) goto L_0x002c
            r5 = r6 & 4
            if (r5 <= 0) goto L_0x007d
        L_0x002c:
            no.nordicsemi.android.dfu.internal.manifest.Manifest r5 = r3.manifest     // Catch:{ all -> 0x0296 }
            no.nordicsemi.android.dfu.internal.manifest.FileInfo r5 = r5.getApplicationInfo()     // Catch:{ all -> 0x0296 }
            java.util.Map<java.lang.String, byte[]> r1 = r3.entries     // Catch:{ all -> 0x0296 }
            java.lang.String r2 = r5.getBinFileName()     // Catch:{ all -> 0x0296 }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x0296 }
            byte[] r1 = (byte[]) r1     // Catch:{ all -> 0x0296 }
            r3.applicationBytes = r1     // Catch:{ all -> 0x0296 }
            java.util.Map<java.lang.String, byte[]> r1 = r3.entries     // Catch:{ all -> 0x0296 }
            java.lang.String r2 = r5.getDatFileName()     // Catch:{ all -> 0x0296 }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x0296 }
            byte[] r1 = (byte[]) r1     // Catch:{ all -> 0x0296 }
            r3.applicationInitBytes = r1     // Catch:{ all -> 0x0296 }
            byte[] r1 = r3.applicationBytes     // Catch:{ all -> 0x0296 }
            if (r1 == 0) goto L_0x005d
            byte[] r5 = r3.applicationBytes     // Catch:{ all -> 0x0296 }
            int r5 = r5.length     // Catch:{ all -> 0x0296 }
            r3.applicationSize = r5     // Catch:{ all -> 0x0296 }
            byte[] r5 = r3.applicationBytes     // Catch:{ all -> 0x0296 }
            r3.currentSource = r5     // Catch:{ all -> 0x0296 }
            r5 = 1
            goto L_0x007e
        L_0x005d:
            java.io.IOException r4 = new java.io.IOException     // Catch:{ all -> 0x0296 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0296 }
            r6.<init>()     // Catch:{ all -> 0x0296 }
            java.lang.String r0 = "Application file "
            r6.append(r0)     // Catch:{ all -> 0x0296 }
            java.lang.String r5 = r5.getBinFileName()     // Catch:{ all -> 0x0296 }
            r6.append(r5)     // Catch:{ all -> 0x0296 }
            java.lang.String r5 = " not found."
            r6.append(r5)     // Catch:{ all -> 0x0296 }
            java.lang.String r5 = r6.toString()     // Catch:{ all -> 0x0296 }
            r4.<init>(r5)     // Catch:{ all -> 0x0296 }
            throw r4     // Catch:{ all -> 0x0296 }
        L_0x007d:
            r5 = 0
        L_0x007e:
            no.nordicsemi.android.dfu.internal.manifest.Manifest r1 = r3.manifest     // Catch:{ all -> 0x0296 }
            no.nordicsemi.android.dfu.internal.manifest.FileInfo r1 = r1.getBootloaderInfo()     // Catch:{ all -> 0x0296 }
            if (r1 == 0) goto L_0x00e9
            if (r6 == 0) goto L_0x008c
            r1 = r6 & 2
            if (r1 <= 0) goto L_0x00e9
        L_0x008c:
            byte[] r5 = r3.systemInitBytes     // Catch:{ all -> 0x0296 }
            if (r5 != 0) goto L_0x00e1
            no.nordicsemi.android.dfu.internal.manifest.Manifest r5 = r3.manifest     // Catch:{ all -> 0x0296 }
            no.nordicsemi.android.dfu.internal.manifest.FileInfo r5 = r5.getBootloaderInfo()     // Catch:{ all -> 0x0296 }
            java.util.Map<java.lang.String, byte[]> r1 = r3.entries     // Catch:{ all -> 0x0296 }
            java.lang.String r2 = r5.getBinFileName()     // Catch:{ all -> 0x0296 }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x0296 }
            byte[] r1 = (byte[]) r1     // Catch:{ all -> 0x0296 }
            r3.bootloaderBytes = r1     // Catch:{ all -> 0x0296 }
            java.util.Map<java.lang.String, byte[]> r1 = r3.entries     // Catch:{ all -> 0x0296 }
            java.lang.String r2 = r5.getDatFileName()     // Catch:{ all -> 0x0296 }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x0296 }
            byte[] r1 = (byte[]) r1     // Catch:{ all -> 0x0296 }
            r3.systemInitBytes = r1     // Catch:{ all -> 0x0296 }
            byte[] r1 = r3.bootloaderBytes     // Catch:{ all -> 0x0296 }
            if (r1 == 0) goto L_0x00c1
            byte[] r5 = r3.bootloaderBytes     // Catch:{ all -> 0x0296 }
            int r5 = r5.length     // Catch:{ all -> 0x0296 }
            r3.bootloaderSize = r5     // Catch:{ all -> 0x0296 }
            byte[] r5 = r3.bootloaderBytes     // Catch:{ all -> 0x0296 }
            r3.currentSource = r5     // Catch:{ all -> 0x0296 }
            r5 = 1
            goto L_0x00e9
        L_0x00c1:
            java.io.IOException r4 = new java.io.IOException     // Catch:{ all -> 0x0296 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0296 }
            r6.<init>()     // Catch:{ all -> 0x0296 }
            java.lang.String r0 = "Bootloader file "
            r6.append(r0)     // Catch:{ all -> 0x0296 }
            java.lang.String r5 = r5.getBinFileName()     // Catch:{ all -> 0x0296 }
            r6.append(r5)     // Catch:{ all -> 0x0296 }
            java.lang.String r5 = " not found."
            r6.append(r5)     // Catch:{ all -> 0x0296 }
            java.lang.String r5 = r6.toString()     // Catch:{ all -> 0x0296 }
            r4.<init>(r5)     // Catch:{ all -> 0x0296 }
            throw r4     // Catch:{ all -> 0x0296 }
        L_0x00e1:
            java.io.IOException r4 = new java.io.IOException     // Catch:{ all -> 0x0296 }
            java.lang.String r5 = "Manifest: softdevice and bootloader specified. Use softdevice_bootloader instead."
            r4.<init>(r5)     // Catch:{ all -> 0x0296 }
            throw r4     // Catch:{ all -> 0x0296 }
        L_0x00e9:
            no.nordicsemi.android.dfu.internal.manifest.Manifest r1 = r3.manifest     // Catch:{ all -> 0x0296 }
            no.nordicsemi.android.dfu.internal.manifest.FileInfo r1 = r1.getSoftdeviceInfo()     // Catch:{ all -> 0x0296 }
            if (r1 == 0) goto L_0x0148
            if (r6 == 0) goto L_0x00f7
            r1 = r6 & 1
            if (r1 <= 0) goto L_0x0148
        L_0x00f7:
            no.nordicsemi.android.dfu.internal.manifest.Manifest r5 = r3.manifest     // Catch:{ all -> 0x0296 }
            no.nordicsemi.android.dfu.internal.manifest.FileInfo r5 = r5.getSoftdeviceInfo()     // Catch:{ all -> 0x0296 }
            java.util.Map<java.lang.String, byte[]> r1 = r3.entries     // Catch:{ all -> 0x0296 }
            java.lang.String r2 = r5.getBinFileName()     // Catch:{ all -> 0x0296 }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x0296 }
            byte[] r1 = (byte[]) r1     // Catch:{ all -> 0x0296 }
            r3.softDeviceBytes = r1     // Catch:{ all -> 0x0296 }
            java.util.Map<java.lang.String, byte[]> r1 = r3.entries     // Catch:{ all -> 0x0296 }
            java.lang.String r2 = r5.getDatFileName()     // Catch:{ all -> 0x0296 }
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x0296 }
            byte[] r1 = (byte[]) r1     // Catch:{ all -> 0x0296 }
            r3.systemInitBytes = r1     // Catch:{ all -> 0x0296 }
            byte[] r1 = r3.softDeviceBytes     // Catch:{ all -> 0x0296 }
            if (r1 == 0) goto L_0x0128
            byte[] r5 = r3.softDeviceBytes     // Catch:{ all -> 0x0296 }
            int r5 = r5.length     // Catch:{ all -> 0x0296 }
            r3.softDeviceSize = r5     // Catch:{ all -> 0x0296 }
            byte[] r5 = r3.softDeviceBytes     // Catch:{ all -> 0x0296 }
            r3.currentSource = r5     // Catch:{ all -> 0x0296 }
            r5 = 1
            goto L_0x0148
        L_0x0128:
            java.io.IOException r4 = new java.io.IOException     // Catch:{ all -> 0x0296 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0296 }
            r6.<init>()     // Catch:{ all -> 0x0296 }
            java.lang.String r0 = "SoftDevice file "
            r6.append(r0)     // Catch:{ all -> 0x0296 }
            java.lang.String r5 = r5.getBinFileName()     // Catch:{ all -> 0x0296 }
            r6.append(r5)     // Catch:{ all -> 0x0296 }
            java.lang.String r5 = " not found."
            r6.append(r5)     // Catch:{ all -> 0x0296 }
            java.lang.String r5 = r6.toString()     // Catch:{ all -> 0x0296 }
            r4.<init>(r5)     // Catch:{ all -> 0x0296 }
            throw r4     // Catch:{ all -> 0x0296 }
        L_0x0148:
            no.nordicsemi.android.dfu.internal.manifest.Manifest r1 = r3.manifest     // Catch:{ all -> 0x0296 }
            no.nordicsemi.android.dfu.internal.manifest.SoftDeviceBootloaderFileInfo r1 = r1.getSoftdeviceBootloaderInfo()     // Catch:{ all -> 0x0296 }
            if (r1 == 0) goto L_0x01be
            if (r6 == 0) goto L_0x015a
            r1 = r6 & 1
            if (r1 <= 0) goto L_0x01be
            r6 = r6 & 2
            if (r6 <= 0) goto L_0x01be
        L_0x015a:
            byte[] r5 = r3.systemInitBytes     // Catch:{ all -> 0x0296 }
            if (r5 != 0) goto L_0x01b6
            no.nordicsemi.android.dfu.internal.manifest.Manifest r5 = r3.manifest     // Catch:{ all -> 0x0296 }
            no.nordicsemi.android.dfu.internal.manifest.SoftDeviceBootloaderFileInfo r5 = r5.getSoftdeviceBootloaderInfo()     // Catch:{ all -> 0x0296 }
            java.util.Map<java.lang.String, byte[]> r6 = r3.entries     // Catch:{ all -> 0x0296 }
            java.lang.String r1 = r5.getBinFileName()     // Catch:{ all -> 0x0296 }
            java.lang.Object r6 = r6.get(r1)     // Catch:{ all -> 0x0296 }
            byte[] r6 = (byte[]) r6     // Catch:{ all -> 0x0296 }
            r3.softDeviceAndBootloaderBytes = r6     // Catch:{ all -> 0x0296 }
            java.util.Map<java.lang.String, byte[]> r6 = r3.entries     // Catch:{ all -> 0x0296 }
            java.lang.String r1 = r5.getDatFileName()     // Catch:{ all -> 0x0296 }
            java.lang.Object r6 = r6.get(r1)     // Catch:{ all -> 0x0296 }
            byte[] r6 = (byte[]) r6     // Catch:{ all -> 0x0296 }
            r3.systemInitBytes = r6     // Catch:{ all -> 0x0296 }
            byte[] r6 = r3.softDeviceAndBootloaderBytes     // Catch:{ all -> 0x0296 }
            if (r6 == 0) goto L_0x0196
            int r6 = r5.getSoftdeviceSize()     // Catch:{ all -> 0x0296 }
            r3.softDeviceSize = r6     // Catch:{ all -> 0x0296 }
            int r5 = r5.getBootloaderSize()     // Catch:{ all -> 0x0296 }
            r3.bootloaderSize = r5     // Catch:{ all -> 0x0296 }
            byte[] r5 = r3.softDeviceAndBootloaderBytes     // Catch:{ all -> 0x0296 }
            r3.currentSource = r5     // Catch:{ all -> 0x0296 }
            r5 = 1
            goto L_0x01be
        L_0x0196:
            java.io.IOException r4 = new java.io.IOException     // Catch:{ all -> 0x0296 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0296 }
            r6.<init>()     // Catch:{ all -> 0x0296 }
            java.lang.String r0 = "File "
            r6.append(r0)     // Catch:{ all -> 0x0296 }
            java.lang.String r5 = r5.getBinFileName()     // Catch:{ all -> 0x0296 }
            r6.append(r5)     // Catch:{ all -> 0x0296 }
            java.lang.String r5 = " not found."
            r6.append(r5)     // Catch:{ all -> 0x0296 }
            java.lang.String r5 = r6.toString()     // Catch:{ all -> 0x0296 }
            r4.<init>(r5)     // Catch:{ all -> 0x0296 }
            throw r4     // Catch:{ all -> 0x0296 }
        L_0x01b6:
            java.io.IOException r4 = new java.io.IOException     // Catch:{ all -> 0x0296 }
            java.lang.String r5 = "Manifest: The softdevice_bootloader may not be used together with softdevice or bootloader."
            r4.<init>(r5)     // Catch:{ all -> 0x0296 }
            throw r4     // Catch:{ all -> 0x0296 }
        L_0x01be:
            if (r5 == 0) goto L_0x01c2
            goto L_0x0281
        L_0x01c2:
            java.io.IOException r4 = new java.io.IOException     // Catch:{ all -> 0x0296 }
            java.lang.String r5 = "Manifest file must specify at least one file."
            r4.<init>(r5)     // Catch:{ all -> 0x0296 }
            throw r4     // Catch:{ all -> 0x0296 }
        L_0x01ca:
            if (r6 == 0) goto L_0x01d0
            r5 = r6 & 4
            if (r5 <= 0) goto L_0x0207
        L_0x01d0:
            java.util.Map<java.lang.String, byte[]> r5 = r3.entries     // Catch:{ all -> 0x0296 }
            java.lang.String r1 = "application.hex"
            java.lang.Object r5 = r5.get(r1)     // Catch:{ all -> 0x0296 }
            byte[] r5 = (byte[]) r5     // Catch:{ all -> 0x0296 }
            r3.applicationBytes = r5     // Catch:{ all -> 0x0296 }
            byte[] r5 = r3.applicationBytes     // Catch:{ all -> 0x0296 }
            if (r5 != 0) goto L_0x01ec
            java.util.Map<java.lang.String, byte[]> r5 = r3.entries     // Catch:{ all -> 0x0296 }
            java.lang.String r1 = "application.bin"
            java.lang.Object r5 = r5.get(r1)     // Catch:{ all -> 0x0296 }
            byte[] r5 = (byte[]) r5     // Catch:{ all -> 0x0296 }
            r3.applicationBytes = r5     // Catch:{ all -> 0x0296 }
        L_0x01ec:
            byte[] r5 = r3.applicationBytes     // Catch:{ all -> 0x0296 }
            if (r5 == 0) goto L_0x0207
            byte[] r5 = r3.applicationBytes     // Catch:{ all -> 0x0296 }
            int r5 = r5.length     // Catch:{ all -> 0x0296 }
            r3.applicationSize = r5     // Catch:{ all -> 0x0296 }
            java.util.Map<java.lang.String, byte[]> r5 = r3.entries     // Catch:{ all -> 0x0296 }
            java.lang.String r1 = "application.dat"
            java.lang.Object r5 = r5.get(r1)     // Catch:{ all -> 0x0296 }
            byte[] r5 = (byte[]) r5     // Catch:{ all -> 0x0296 }
            r3.applicationInitBytes = r5     // Catch:{ all -> 0x0296 }
            byte[] r5 = r3.applicationBytes     // Catch:{ all -> 0x0296 }
            r3.currentSource = r5     // Catch:{ all -> 0x0296 }
            r5 = 1
            goto L_0x0208
        L_0x0207:
            r5 = 0
        L_0x0208:
            if (r6 == 0) goto L_0x020e
            r1 = r6 & 2
            if (r1 <= 0) goto L_0x0244
        L_0x020e:
            java.util.Map<java.lang.String, byte[]> r1 = r3.entries     // Catch:{ all -> 0x0296 }
            java.lang.String r2 = "bootloader.hex"
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x0296 }
            byte[] r1 = (byte[]) r1     // Catch:{ all -> 0x0296 }
            r3.bootloaderBytes = r1     // Catch:{ all -> 0x0296 }
            byte[] r1 = r3.bootloaderBytes     // Catch:{ all -> 0x0296 }
            if (r1 != 0) goto L_0x022a
            java.util.Map<java.lang.String, byte[]> r1 = r3.entries     // Catch:{ all -> 0x0296 }
            java.lang.String r2 = "bootloader.bin"
            java.lang.Object r1 = r1.get(r2)     // Catch:{ all -> 0x0296 }
            byte[] r1 = (byte[]) r1     // Catch:{ all -> 0x0296 }
            r3.bootloaderBytes = r1     // Catch:{ all -> 0x0296 }
        L_0x022a:
            byte[] r1 = r3.bootloaderBytes     // Catch:{ all -> 0x0296 }
            if (r1 == 0) goto L_0x0244
            byte[] r5 = r3.bootloaderBytes     // Catch:{ all -> 0x0296 }
            int r5 = r5.length     // Catch:{ all -> 0x0296 }
            r3.bootloaderSize = r5     // Catch:{ all -> 0x0296 }
            java.util.Map<java.lang.String, byte[]> r5 = r3.entries     // Catch:{ all -> 0x0296 }
            java.lang.String r1 = "system.dat"
            java.lang.Object r5 = r5.get(r1)     // Catch:{ all -> 0x0296 }
            byte[] r5 = (byte[]) r5     // Catch:{ all -> 0x0296 }
            r3.systemInitBytes = r5     // Catch:{ all -> 0x0296 }
            byte[] r5 = r3.bootloaderBytes     // Catch:{ all -> 0x0296 }
            r3.currentSource = r5     // Catch:{ all -> 0x0296 }
            r5 = 1
        L_0x0244:
            if (r6 == 0) goto L_0x0249
            r6 = r6 & r0
            if (r6 <= 0) goto L_0x027f
        L_0x0249:
            java.util.Map<java.lang.String, byte[]> r6 = r3.entries     // Catch:{ all -> 0x0296 }
            java.lang.String r1 = "softdevice.hex"
            java.lang.Object r6 = r6.get(r1)     // Catch:{ all -> 0x0296 }
            byte[] r6 = (byte[]) r6     // Catch:{ all -> 0x0296 }
            r3.softDeviceBytes = r6     // Catch:{ all -> 0x0296 }
            byte[] r6 = r3.softDeviceBytes     // Catch:{ all -> 0x0296 }
            if (r6 != 0) goto L_0x0265
            java.util.Map<java.lang.String, byte[]> r6 = r3.entries     // Catch:{ all -> 0x0296 }
            java.lang.String r1 = "softdevice.bin"
            java.lang.Object r6 = r6.get(r1)     // Catch:{ all -> 0x0296 }
            byte[] r6 = (byte[]) r6     // Catch:{ all -> 0x0296 }
            r3.softDeviceBytes = r6     // Catch:{ all -> 0x0296 }
        L_0x0265:
            byte[] r6 = r3.softDeviceBytes     // Catch:{ all -> 0x0296 }
            if (r6 == 0) goto L_0x027f
            byte[] r5 = r3.softDeviceBytes     // Catch:{ all -> 0x0296 }
            int r5 = r5.length     // Catch:{ all -> 0x0296 }
            r3.softDeviceSize = r5     // Catch:{ all -> 0x0296 }
            java.util.Map<java.lang.String, byte[]> r5 = r3.entries     // Catch:{ all -> 0x0296 }
            java.lang.String r6 = "system.dat"
            java.lang.Object r5 = r5.get(r6)     // Catch:{ all -> 0x0296 }
            byte[] r5 = (byte[]) r5     // Catch:{ all -> 0x0296 }
            r3.systemInitBytes = r5     // Catch:{ all -> 0x0296 }
            byte[] r5 = r3.softDeviceBytes     // Catch:{ all -> 0x0296 }
            r3.currentSource = r5     // Catch:{ all -> 0x0296 }
            r5 = 1
        L_0x027f:
            if (r5 == 0) goto L_0x028e
        L_0x0281:
            r3.mark(r4)     // Catch:{ all -> 0x0296 }
            int r4 = r3.getContentType()
            r3.type = r4
            super.close()
            return
        L_0x028e:
            java.io.IOException r4 = new java.io.IOException     // Catch:{ all -> 0x0296 }
            java.lang.String r5 = "The ZIP file must contain an Application, a Soft Device and/or a Bootloader."
            r4.<init>(r5)     // Catch:{ all -> 0x0296 }
            throw r4     // Catch:{ all -> 0x0296 }
        L_0x0296:
            r4 = move-exception
            int r5 = r3.getContentType()
            r3.type = r5
            super.close()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: no.nordicsemi.android.dfu.internal.ArchiveInputStream.<init>(java.io.InputStream, int, int):void");
    }

    private void parseZip(int i) throws IOException {
        byte[] bArr = new byte[1024];
        String str = null;
        while (true) {
            ZipEntry nextEntry = getNextEntry();
            if (nextEntry == null) {
                break;
            }
            String name = nextEntry.getName();
            if (nextEntry.isDirectory()) {
                Log.w(TAG, "A directory found in the ZIP: " + name + "!");
            } else {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int read = super.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                if (name.toLowerCase(Locale.US).endsWith("hex")) {
                    HexInputStream hexInputStream = new HexInputStream(byteArray, i);
                    byteArray = new byte[hexInputStream.available()];
                    hexInputStream.read(byteArray);
                    hexInputStream.close();
                }
                if (MANIFEST.equals(name)) {
                    str = new String(byteArray, "UTF-8");
                } else {
                    this.entries.put(name, byteArray);
                }
            }
        }
        if (this.entries.isEmpty()) {
            throw new FileNotFoundException("No files found in the ZIP. Check if the URI provided is valid and the ZIP contains required files on root level, not in a directory.");
        } else if (str != null) {
            this.manifest = ((ManifestFile) new Gson().fromJson(str, ManifestFile.class)).getManifest();
            if (this.manifest == null) {
                Log.w(TAG, "Manifest failed to be parsed. Did you add \n-keep class no.nordicsemi.android.dfu.** { *; }\nto your proguard rules?");
            }
        } else {
            Log.w(TAG, "Manifest not found in the ZIP. It is recommended to use a distribution file created with: https://github.com/NordicSemiconductor/pc-nrfutil/ (for Legacy DFU use version 0.5.x)");
        }
    }

    public void close() throws IOException {
        this.softDeviceBytes = null;
        this.bootloaderBytes = null;
        this.softDeviceBytes = null;
        this.softDeviceAndBootloaderBytes = null;
        this.applicationSize = 0;
        this.bootloaderSize = 0;
        this.softDeviceSize = 0;
        this.currentSource = null;
        this.bytesReadFromCurrentSource = 0;
        this.bytesRead = 0;
        super.close();
    }

    public int read(@NonNull byte[] bArr) throws IOException {
        int length = this.currentSource.length - this.bytesReadFromCurrentSource;
        if (bArr.length <= length) {
            length = bArr.length;
        }
        System.arraycopy(this.currentSource, this.bytesReadFromCurrentSource, bArr, 0, length);
        this.bytesReadFromCurrentSource += length;
        if (bArr.length > length) {
            if (startNextFile() == null) {
                this.bytesRead += length;
                this.crc32.update(bArr, 0, length);
                return length;
            }
            int length2 = this.currentSource.length;
            if (bArr.length - length <= length2) {
                length2 = bArr.length - length;
            }
            System.arraycopy(this.currentSource, 0, bArr, length, length2);
            this.bytesReadFromCurrentSource += length2;
            length += length2;
        }
        this.bytesRead += length;
        this.crc32.update(bArr, 0, length);
        return length;
    }

    public void mark(int i) {
        this.markedSource = this.currentSource;
        this.bytesReadFromMarkedSource = this.bytesReadFromCurrentSource;
    }

    public void reset() throws IOException {
        this.currentSource = this.markedSource;
        int i = this.bytesReadFromMarkedSource;
        this.bytesReadFromCurrentSource = i;
        this.bytesRead = i;
        this.crc32.reset();
        if (this.currentSource == this.bootloaderBytes && this.softDeviceBytes != null) {
            this.crc32.update(this.softDeviceBytes);
            this.bytesRead += this.softDeviceSize;
        }
        this.crc32.update(this.currentSource, 0, this.bytesReadFromCurrentSource);
    }

    public int getBytesRead() {
        return this.bytesRead;
    }

    public long getCrc32() {
        return this.crc32.getValue();
    }

    public int getContentType() {
        this.type = 0;
        if (this.softDeviceAndBootloaderBytes != null) {
            this.type |= 3;
        }
        if (this.softDeviceSize > 0) {
            this.type |= 1;
        }
        if (this.bootloaderSize > 0) {
            this.type |= 2;
        }
        if (this.applicationSize > 0) {
            this.type |= 4;
        }
        return this.type;
    }

    public int setContentType(int i) {
        this.type = i;
        int i2 = i & 4;
        if (i2 > 0 && this.applicationBytes == null) {
            this.type &= -5;
        }
        int i3 = i & 3;
        if (i3 == 3) {
            if (this.softDeviceBytes == null && this.softDeviceAndBootloaderBytes == null) {
                this.type &= -2;
            }
            if (this.bootloaderBytes == null && this.softDeviceAndBootloaderBytes == null) {
                this.type &= -2;
            }
        } else if (this.softDeviceAndBootloaderBytes != null) {
            this.type &= -4;
        }
        if (i3 > 0 && this.softDeviceAndBootloaderBytes != null) {
            this.currentSource = this.softDeviceAndBootloaderBytes;
        } else if ((i & 1) > 0) {
            this.currentSource = this.softDeviceBytes;
        } else if ((i & 2) > 0) {
            this.currentSource = this.bootloaderBytes;
        } else if (i2 > 0) {
            this.currentSource = this.applicationBytes;
        }
        this.bytesReadFromCurrentSource = 0;
        try {
            mark(0);
            reset();
        } catch (IOException unused) {
        }
        return this.type;
    }

    private byte[] startNextFile() {
        byte[] bArr;
        if (this.currentSource == this.softDeviceBytes && this.bootloaderBytes != null && (this.type & 2) > 0) {
            bArr = this.bootloaderBytes;
            this.currentSource = bArr;
        } else if (this.currentSource == this.applicationBytes || this.applicationBytes == null || (this.type & 4) <= 0) {
            bArr = null;
            this.currentSource = null;
        } else {
            bArr = this.applicationBytes;
            this.currentSource = bArr;
        }
        this.bytesReadFromCurrentSource = 0;
        return bArr;
    }

    public int available() {
        if (this.softDeviceAndBootloaderBytes == null || this.softDeviceSize != 0 || this.bootloaderSize != 0 || (this.type & 3) <= 0) {
            return ((softDeviceImageSize() + bootloaderImageSize()) + applicationImageSize()) - this.bytesRead;
        }
        return (this.softDeviceAndBootloaderBytes.length + applicationImageSize()) - this.bytesRead;
    }

    public int softDeviceImageSize() {
        if ((this.type & 1) > 0) {
            return this.softDeviceSize;
        }
        return 0;
    }

    public int bootloaderImageSize() {
        if ((this.type & 2) > 0) {
            return this.bootloaderSize;
        }
        return 0;
    }

    public int applicationImageSize() {
        if ((this.type & 4) > 0) {
            return this.applicationSize;
        }
        return 0;
    }

    public byte[] getSystemInit() {
        return this.systemInitBytes;
    }

    public byte[] getApplicationInit() {
        return this.applicationInitBytes;
    }

    public boolean isSecureDfuRequired() {
        return this.manifest != null && this.manifest.isSecureDfuRequired();
    }
}
