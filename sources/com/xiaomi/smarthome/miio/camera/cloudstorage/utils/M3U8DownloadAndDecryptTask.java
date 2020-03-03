package com.xiaomi.smarthome.miio.camera.cloudstorage.utils;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.iheartradio.m3u8.Encoding;
import com.iheartradio.m3u8.Format;
import com.iheartradio.m3u8.ParsingMode;
import com.iheartradio.m3u8.PlaylistParser;
import com.iheartradio.m3u8.data.TrackData;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.file.FileUtils;
import com.xiaomi.smarthome.framework.log.LogUtil;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.aspectj.runtime.internal.AroundClosure;

public class M3U8DownloadAndDecryptTask extends AsyncTask<String, Integer, Integer> {
    private static final int CODE_CANCEL = -104;
    private static final int CODE_EXCEPTION = -103;
    private static final int CODE_SUCCESS = 101;
    private static final int NOT_FULLY_SUCCESS = -102;
    private static final String TAG = "M3U8DownloadAndDecryptTask";
    private IFileDownloadCallback callback;
    private boolean isFileDownloadSuccess = false;
    private String m3u8FileFolder = null;
    private String postfix = "";
    private int trackDataSize = 0;
    private List<TrackDataLite> tsFileList = new ArrayList();

    public class AjcClosure1 extends AroundClosure {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            return M3U8DownloadAndDecryptTask.openConnection_aroundBody0((M3U8DownloadAndDecryptTask) objArr2[0], (URL) objArr2[1]);
        }
    }

    public interface IFileDownloadCallback {
        void onFailure(int i);

        void onProgress(int i);

        void onSuccess(List<String> list, String str);
    }

    private class TrackDataLite {
        public float duration;
        public boolean isContinue;
        public String title;
        public String uri;

        private TrackDataLite() {
        }
    }

    public M3U8DownloadAndDecryptTask(IFileDownloadCallback iFileDownloadCallback) {
        this.callback = iFileDownloadCallback;
    }

    /* access modifiers changed from: protected */
    public Integer doInBackground(String... strArr) {
        int i;
        int i2;
        int i3;
        String str = strArr[0];
        String str2 = strArr[1];
        String str3 = strArr[2];
        String str4 = SHApplication.getAppContext().getExternalFilesDir((String) null) + "/" + str2 + "/" + str3 + "/" + str3 + ".m3u8";
        String substring = str4.substring(0, str4.lastIndexOf("/"));
        this.m3u8FileFolder = substring;
        File file = new File(substring);
        if (!file.exists()) {
            CloudVideoFileUtils.RecursionDeleteFile(file);
            file.mkdirs();
        }
        publishProgress(new Integer[]{0});
        try {
            if (downloadFile(str, str4)) {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new String(FileUtils.b(str4)).replace("#EXT-X-VERSION:7", "#EXT-X-VERSION:3").getBytes());
                List<TrackData> b = new PlaylistParser((InputStream) byteArrayInputStream, Format.EXT_M3U, Encoding.UTF_8, ParsingMode.b).c().d().b();
                this.trackDataSize = b.size();
                String str5 = substring + "/" + str2 + ".key";
                if (b != null && b.size() > 0) {
                    if (b.get(0).a().contains("/ts")) {
                        this.postfix = ".ts";
                    } else if (b.get(0).a().contains("/mp4")) {
                        this.postfix = ".mp4";
                    } else {
                        this.postfix = ".ts";
                    }
                }
                Iterator<TrackData> it = b.iterator();
                while (true) {
                    i3 = -104;
                    if (!it.hasNext()) {
                        i3 = 0;
                        break;
                    }
                    TrackData next = it.next();
                    if (isCancelled()) {
                        break;
                    } else if (!TextUtils.isEmpty(next.a())) {
                        String str6 = substring + "/" + System.currentTimeMillis() + this.postfix;
                        String replace = str6.replace(this.postfix, "_plain" + this.postfix);
                        downloadFile(next.a(), str6);
                        if (isCancelled()) {
                            break;
                        }
                        String c = next.i().c();
                        TrackDataLite trackDataLite = new TrackDataLite();
                        if (TextUtils.isEmpty((CharSequence) null) || TextUtils.isEmpty(c) || !c.equals((Object) null)) {
                            downloadFile(c, str5);
                            if (decryptTS(str6, str5, replace, next.i().e())) {
                                trackDataLite.uri = replace;
                                trackDataLite.duration = next.c().f6058a;
                                trackDataLite.isContinue = false;
                                this.tsFileList.add(trackDataLite);
                            }
                        } else if (decryptTS(str6, str5, replace, next.i().e())) {
                            trackDataLite.uri = replace;
                            trackDataLite.duration = next.c().f6058a;
                            trackDataLite.isContinue = false;
                            this.tsFileList.add(trackDataLite);
                        }
                        if (b != null && b.size() > 0) {
                            publishProgress(new Integer[]{Integer.valueOf((int) ((((float) this.tsFileList.size()) / ((float) b.size())) * 100.0f))});
                        }
                    }
                }
                if (this.tsFileList.size() >= b.size()) {
                    publishProgress(new Integer[]{100});
                    generateNewM3U8(str4.replace(".m3u8", "_plain.m3u8"));
                }
                byteArrayInputStream.close();
                i2 = i3;
            } else {
                i2 = 0;
            }
            if (!this.isFileDownloadSuccess) {
                i = i2;
                return Integer.valueOf(i);
            }
        } catch (Exception e) {
            LogUtil.b(TAG, "common exception:" + e.getLocalizedMessage());
            e.printStackTrace();
            if (!this.isFileDownloadSuccess) {
                i = -103;
            }
        } finally {
            boolean z = this.isFileDownloadSuccess;
        }
        i = 101;
        return Integer.valueOf(i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x00b5 A[SYNTHETIC, Splitter:B:24:0x00b5] */
    /* JADX WARNING: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void generateNewM3U8(java.lang.String r11) {
        /*
            r10 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r11)
            if (r0 != 0) goto L_0x00b9
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x0091, all -> 0x008c }
            r1.<init>(r11)     // Catch:{ Exception -> 0x0091, all -> 0x008c }
            java.io.FileOutputStream r11 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0091, all -> 0x008c }
            r11.<init>(r1)     // Catch:{ Exception -> 0x0091, all -> 0x008c }
            com.iheartradio.m3u8.PlaylistWriter r1 = new com.iheartradio.m3u8.PlaylistWriter     // Catch:{ Exception -> 0x008a }
            com.iheartradio.m3u8.Format r2 = com.iheartradio.m3u8.Format.EXT_M3U     // Catch:{ Exception -> 0x008a }
            com.iheartradio.m3u8.Encoding r3 = com.iheartradio.m3u8.Encoding.UTF_8     // Catch:{ Exception -> 0x008a }
            r1.<init>(r11, r2, r3)     // Catch:{ Exception -> 0x008a }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ Exception -> 0x008a }
            r2.<init>()     // Catch:{ Exception -> 0x008a }
            java.util.List<com.xiaomi.smarthome.miio.camera.cloudstorage.utils.M3U8DownloadAndDecryptTask$TrackDataLite> r3 = r10.tsFileList     // Catch:{ Exception -> 0x008a }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ Exception -> 0x008a }
        L_0x0025:
            boolean r4 = r3.hasNext()     // Catch:{ Exception -> 0x008a }
            r5 = 1
            if (r4 == 0) goto L_0x0057
            java.lang.Object r4 = r3.next()     // Catch:{ Exception -> 0x008a }
            com.xiaomi.smarthome.miio.camera.cloudstorage.utils.M3U8DownloadAndDecryptTask$TrackDataLite r4 = (com.xiaomi.smarthome.miio.camera.cloudstorage.utils.M3U8DownloadAndDecryptTask.TrackDataLite) r4     // Catch:{ Exception -> 0x008a }
            com.iheartradio.m3u8.data.TrackData$Builder r6 = new com.iheartradio.m3u8.data.TrackData$Builder     // Catch:{ Exception -> 0x008a }
            r6.<init>()     // Catch:{ Exception -> 0x008a }
            com.iheartradio.m3u8.data.TrackInfo r7 = new com.iheartradio.m3u8.data.TrackInfo     // Catch:{ Exception -> 0x008a }
            float r8 = r4.duration     // Catch:{ Exception -> 0x008a }
            r7.<init>(r8, r0)     // Catch:{ Exception -> 0x008a }
            com.iheartradio.m3u8.data.TrackData$Builder r6 = r6.a((com.iheartradio.m3u8.data.TrackInfo) r7)     // Catch:{ Exception -> 0x008a }
            boolean r7 = r4.isContinue     // Catch:{ Exception -> 0x008a }
            r5 = r5 ^ r7
            com.iheartradio.m3u8.data.TrackData$Builder r5 = r6.a((boolean) r5)     // Catch:{ Exception -> 0x008a }
            java.lang.String r4 = r4.uri     // Catch:{ Exception -> 0x008a }
            com.iheartradio.m3u8.data.TrackData$Builder r4 = r5.a((java.lang.String) r4)     // Catch:{ Exception -> 0x008a }
            com.iheartradio.m3u8.data.TrackData r4 = r4.a()     // Catch:{ Exception -> 0x008a }
            r2.add(r4)     // Catch:{ Exception -> 0x008a }
            goto L_0x0025
        L_0x0057:
            com.iheartradio.m3u8.data.MediaPlaylist$Builder r0 = new com.iheartradio.m3u8.data.MediaPlaylist$Builder     // Catch:{ Exception -> 0x008a }
            r0.<init>()     // Catch:{ Exception -> 0x008a }
            com.iheartradio.m3u8.data.MediaPlaylist$Builder r0 = r0.b((int) r5)     // Catch:{ Exception -> 0x008a }
            r3 = 3
            com.iheartradio.m3u8.data.MediaPlaylist$Builder r0 = r0.a((int) r3)     // Catch:{ Exception -> 0x008a }
            com.iheartradio.m3u8.data.MediaPlaylist$Builder r0 = r0.a((java.util.List<com.iheartradio.m3u8.data.TrackData>) r2)     // Catch:{ Exception -> 0x008a }
            com.iheartradio.m3u8.data.MediaPlaylist r0 = r0.a()     // Catch:{ Exception -> 0x008a }
            com.iheartradio.m3u8.data.Playlist$Builder r2 = new com.iheartradio.m3u8.data.Playlist$Builder     // Catch:{ Exception -> 0x008a }
            r2.<init>()     // Catch:{ Exception -> 0x008a }
            r3 = 7
            com.iheartradio.m3u8.data.Playlist$Builder r2 = r2.a((int) r3)     // Catch:{ Exception -> 0x008a }
            com.iheartradio.m3u8.data.Playlist$Builder r0 = r2.a((com.iheartradio.m3u8.data.MediaPlaylist) r0)     // Catch:{ Exception -> 0x008a }
            com.iheartradio.m3u8.data.Playlist$Builder r0 = r0.a((boolean) r5)     // Catch:{ Exception -> 0x008a }
            com.iheartradio.m3u8.data.Playlist r0 = r0.a()     // Catch:{ Exception -> 0x008a }
            r1.a(r0)     // Catch:{ Exception -> 0x008a }
        L_0x0086:
            r11.close()     // Catch:{ IOException -> 0x00b9 }
            goto L_0x00b9
        L_0x008a:
            r0 = move-exception
            goto L_0x0095
        L_0x008c:
            r11 = move-exception
            r9 = r0
            r0 = r11
            r11 = r9
            goto L_0x00b3
        L_0x0091:
            r11 = move-exception
            r9 = r0
            r0 = r11
            r11 = r9
        L_0x0095:
            java.lang.String r1 = "M3U8DownloadAndDecryptTask"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b2 }
            r2.<init>()     // Catch:{ all -> 0x00b2 }
            java.lang.String r3 = "generateNewM3U8 error:"
            r2.append(r3)     // Catch:{ all -> 0x00b2 }
            java.lang.String r0 = r0.getLocalizedMessage()     // Catch:{ all -> 0x00b2 }
            r2.append(r0)     // Catch:{ all -> 0x00b2 }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x00b2 }
            com.xiaomi.smarthome.framework.log.LogUtil.b(r1, r0)     // Catch:{ all -> 0x00b2 }
            if (r11 == 0) goto L_0x00b9
            goto L_0x0086
        L_0x00b2:
            r0 = move-exception
        L_0x00b3:
            if (r11 == 0) goto L_0x00b8
            r11.close()     // Catch:{ IOException -> 0x00b8 }
        L_0x00b8:
            throw r0
        L_0x00b9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.camera.cloudstorage.utils.M3U8DownloadAndDecryptTask.generateNewM3U8(java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x008a A[SYNTHETIC, Splitter:B:39:0x008a] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x008f A[Catch:{ IOException -> 0x0092 }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0096 A[SYNTHETIC, Splitter:B:46:0x0096] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x009b A[Catch:{ IOException -> 0x009e }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean decryptTS(java.lang.String r6, java.lang.String r7, java.lang.String r8, java.util.List<java.lang.Byte> r9) {
        /*
            r5 = this;
            r0 = 0
            r1 = 0
            byte[] r2 = com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoFileUtils.file2Bytes(r6)     // Catch:{ IOException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x006c, all -> 0x0069 }
            byte[] r7 = com.xiaomi.smarthome.frame.file.FileUtils.b(r7)     // Catch:{ IOException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x006c, all -> 0x0069 }
            javax.crypto.spec.SecretKeySpec r3 = new javax.crypto.spec.SecretKeySpec     // Catch:{ IOException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x006c, all -> 0x0069 }
            java.lang.String r4 = "AES"
            r3.<init>(r7, r4)     // Catch:{ IOException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x006c, all -> 0x0069 }
            java.lang.String r7 = com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoUtils.byteList2String(r9)     // Catch:{ IOException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x006c, all -> 0x0069 }
            byte[] r7 = com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoUtils.parseHexBinary(r7)     // Catch:{ IOException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x006c, all -> 0x0069 }
            javax.crypto.spec.IvParameterSpec r9 = new javax.crypto.spec.IvParameterSpec     // Catch:{ IOException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x006c, all -> 0x0069 }
            r9.<init>(r7)     // Catch:{ IOException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x006c, all -> 0x0069 }
            java.lang.String r7 = "AES/CBC/PKCS7Padding"
            javax.crypto.Cipher r7 = javax.crypto.Cipher.getInstance(r7)     // Catch:{ IOException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x006c, all -> 0x0069 }
            r4 = 2
            r7.init(r4, r3, r9)     // Catch:{ IOException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x006c, all -> 0x0069 }
            byte[] r7 = r7.doFinal(r2)     // Catch:{ IOException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x006c, all -> 0x0069 }
            if (r7 == 0) goto L_0x004f
            int r9 = r7.length     // Catch:{ IOException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x006c, all -> 0x0069 }
            if (r9 <= 0) goto L_0x004f
            java.io.FileOutputStream r9 = new java.io.FileOutputStream     // Catch:{ IOException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x006c, all -> 0x0069 }
            r9.<init>(r8)     // Catch:{ IOException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x006c, all -> 0x0069 }
            java.io.BufferedOutputStream r8 = new java.io.BufferedOutputStream     // Catch:{ IOException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x004b, all -> 0x0047 }
            r8.<init>(r9)     // Catch:{ IOException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x004b, all -> 0x0047 }
            r8.write(r7)     // Catch:{ IOException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x0045, all -> 0x0043 }
            r8.flush()     // Catch:{ IOException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x0045, all -> 0x0043 }
            r0 = r9
            goto L_0x0050
        L_0x0043:
            r6 = move-exception
            goto L_0x0049
        L_0x0045:
            r6 = move-exception
            goto L_0x004d
        L_0x0047:
            r6 = move-exception
            r8 = r0
        L_0x0049:
            r0 = r9
            goto L_0x0094
        L_0x004b:
            r6 = move-exception
            r8 = r0
        L_0x004d:
            r0 = r9
            goto L_0x006e
        L_0x004f:
            r8 = r0
        L_0x0050:
            java.lang.String r7 = "M3U8DownloadAndDecryptTask"
            java.lang.String r9 = "decryptTS success"
            com.xiaomi.smarthome.framework.log.LogUtil.a((java.lang.String) r7, (java.lang.String) r9)     // Catch:{ IOException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x0067 }
            r7 = 1
            com.xiaomi.smarthome.frame.file.FileUtils.j(r6)     // Catch:{ IOException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException -> 0x0067 }
            if (r0 == 0) goto L_0x0060
            r0.close()     // Catch:{ IOException -> 0x0092 }
        L_0x0060:
            if (r8 == 0) goto L_0x0065
            r8.close()     // Catch:{ IOException -> 0x0092 }
        L_0x0065:
            r1 = 1
            goto L_0x0092
        L_0x0067:
            r6 = move-exception
            goto L_0x006e
        L_0x0069:
            r6 = move-exception
            r8 = r0
            goto L_0x0094
        L_0x006c:
            r6 = move-exception
            r8 = r0
        L_0x006e:
            java.lang.String r7 = "M3U8DownloadAndDecryptTask"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x0093 }
            r9.<init>()     // Catch:{ all -> 0x0093 }
            java.lang.String r2 = "decrypt error:"
            r9.append(r2)     // Catch:{ all -> 0x0093 }
            java.lang.String r6 = r6.getLocalizedMessage()     // Catch:{ all -> 0x0093 }
            r9.append(r6)     // Catch:{ all -> 0x0093 }
            java.lang.String r6 = r9.toString()     // Catch:{ all -> 0x0093 }
            com.xiaomi.smarthome.framework.log.LogUtil.b(r7, r6)     // Catch:{ all -> 0x0093 }
            if (r0 == 0) goto L_0x008d
            r0.close()     // Catch:{ IOException -> 0x0092 }
        L_0x008d:
            if (r8 == 0) goto L_0x0092
            r8.close()     // Catch:{ IOException -> 0x0092 }
        L_0x0092:
            return r1
        L_0x0093:
            r6 = move-exception
        L_0x0094:
            if (r0 == 0) goto L_0x0099
            r0.close()     // Catch:{ IOException -> 0x009e }
        L_0x0099:
            if (r8 == 0) goto L_0x009e
            r8.close()     // Catch:{ IOException -> 0x009e }
        L_0x009e:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.camera.cloudstorage.utils.M3U8DownloadAndDecryptTask.decryptTS(java.lang.String, java.lang.String, java.lang.String, java.util.List):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x009c, code lost:
        if (r3 != null) goto L_0x009e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x009e, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00c8, code lost:
        if (r3 != null) goto L_0x009e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00c5 A[SYNTHETIC, Splitter:B:42:0x00c5] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00ce A[SYNTHETIC, Splitter:B:47:0x00ce] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00d3 A[Catch:{ IOException -> 0x00d6 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean downloadFile(java.lang.String r7, java.lang.String r8) {
        /*
            r6 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            r1 = 0
            if (r0 != 0) goto L_0x00d7
            boolean r0 = android.text.TextUtils.isEmpty(r8)
            if (r0 != 0) goto L_0x00d7
            java.io.File r0 = new java.io.File
            r0.<init>(r8)
            r8 = 0
            java.net.URL r2 = new java.net.URL     // Catch:{ Exception -> 0x00a7, all -> 0x00a4 }
            r2.<init>(r7)     // Catch:{ Exception -> 0x00a7, all -> 0x00a4 }
            com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor r7 = com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor.b()     // Catch:{ Exception -> 0x00a7, all -> 0x00a4 }
            java.net.URLConnection r7 = r7.b(r2)     // Catch:{ Exception -> 0x00a7, all -> 0x00a4 }
            javax.net.ssl.HttpsURLConnection r7 = (javax.net.ssl.HttpsURLConnection) r7     // Catch:{ Exception -> 0x00a7, all -> 0x00a4 }
            java.lang.String r2 = "GET"
            r7.setRequestMethod(r2)     // Catch:{ Exception -> 0x00a7, all -> 0x00a4 }
            java.lang.String r2 = "Cookie"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00a7, all -> 0x00a4 }
            r3.<init>()     // Catch:{ Exception -> 0x00a7, all -> 0x00a4 }
            java.lang.String r4 = "yetAnotherServiceToken="
            r3.append(r4)     // Catch:{ Exception -> 0x00a7, all -> 0x00a4 }
            com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils r4 = com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils.getInstance()     // Catch:{ Exception -> 0x00a7, all -> 0x00a4 }
            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r4 = r4.getTokenInfo()     // Catch:{ Exception -> 0x00a7, all -> 0x00a4 }
            java.lang.String r4 = r4.c     // Catch:{ Exception -> 0x00a7, all -> 0x00a4 }
            r3.append(r4)     // Catch:{ Exception -> 0x00a7, all -> 0x00a4 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x00a7, all -> 0x00a4 }
            r7.setRequestProperty(r2, r3)     // Catch:{ Exception -> 0x00a7, all -> 0x00a4 }
            r2 = 10000(0x2710, float:1.4013E-41)
            r7.setConnectTimeout(r2)     // Catch:{ Exception -> 0x00a7, all -> 0x00a4 }
            r2 = 15000(0x3a98, float:2.102E-41)
            r7.setReadTimeout(r2)     // Catch:{ Exception -> 0x00a7, all -> 0x00a4 }
            r7.connect()     // Catch:{ Exception -> 0x00a7, all -> 0x00a4 }
            int r2 = r7.getResponseCode()     // Catch:{ Exception -> 0x00a7, all -> 0x00a4 }
            java.io.InputStream r3 = r7.getInputStream()     // Catch:{ Exception -> 0x00a7, all -> 0x00a4 }
            int r7 = r7.getContentLength()     // Catch:{ Exception -> 0x00a2 }
            if (r7 <= 0) goto L_0x0097
            r4 = 200(0xc8, float:2.8E-43)
            if (r2 != r4) goto L_0x0097
            boolean r2 = r0.exists()     // Catch:{ Exception -> 0x00a2 }
            if (r2 == 0) goto L_0x006f
            r0.deleteOnExit()     // Catch:{ Exception -> 0x00a2 }
        L_0x006f:
            r0.createNewFile()     // Catch:{ Exception -> 0x00a2 }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x00a2 }
            r2.<init>(r0)     // Catch:{ Exception -> 0x00a2 }
            byte[] r8 = new byte[r7]     // Catch:{ Exception -> 0x0094, all -> 0x0091 }
            r0 = 0
        L_0x007a:
            if (r0 >= r7) goto L_0x0087
            int r4 = r7 - r0
            int r4 = r3.read(r8, r0, r4)     // Catch:{ Exception -> 0x0094, all -> 0x0091 }
            r5 = -1
            if (r4 == r5) goto L_0x0087
            int r0 = r0 + r4
            goto L_0x007a
        L_0x0087:
            int r7 = r8.length     // Catch:{ Exception -> 0x0094, all -> 0x0091 }
            r2.write(r8, r1, r7)     // Catch:{ Exception -> 0x0094, all -> 0x0091 }
            r2.flush()     // Catch:{ Exception -> 0x0094, all -> 0x0091 }
            r1 = 1
            r8 = r2
            goto L_0x0097
        L_0x0091:
            r7 = move-exception
            r8 = r2
            goto L_0x00cc
        L_0x0094:
            r7 = move-exception
            r8 = r2
            goto L_0x00a9
        L_0x0097:
            if (r8 == 0) goto L_0x009c
            r8.close()     // Catch:{ IOException -> 0x00f5 }
        L_0x009c:
            if (r3 == 0) goto L_0x00f5
        L_0x009e:
            r3.close()     // Catch:{ IOException -> 0x00f5 }
            goto L_0x00f5
        L_0x00a2:
            r7 = move-exception
            goto L_0x00a9
        L_0x00a4:
            r7 = move-exception
            r3 = r8
            goto L_0x00cc
        L_0x00a7:
            r7 = move-exception
            r3 = r8
        L_0x00a9:
            java.lang.String r0 = "M3U8DownloadAndDecryptTask"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00cb }
            r2.<init>()     // Catch:{ all -> 0x00cb }
            java.lang.String r4 = "downloadFile error:"
            r2.append(r4)     // Catch:{ all -> 0x00cb }
            java.lang.String r7 = r7.getLocalizedMessage()     // Catch:{ all -> 0x00cb }
            r2.append(r7)     // Catch:{ all -> 0x00cb }
            java.lang.String r7 = r2.toString()     // Catch:{ all -> 0x00cb }
            com.xiaomi.smarthome.framework.log.LogUtil.b(r0, r7)     // Catch:{ all -> 0x00cb }
            if (r8 == 0) goto L_0x00c8
            r8.close()     // Catch:{ IOException -> 0x00f5 }
        L_0x00c8:
            if (r3 == 0) goto L_0x00f5
            goto L_0x009e
        L_0x00cb:
            r7 = move-exception
        L_0x00cc:
            if (r8 == 0) goto L_0x00d1
            r8.close()     // Catch:{ IOException -> 0x00d6 }
        L_0x00d1:
            if (r3 == 0) goto L_0x00d6
            r3.close()     // Catch:{ IOException -> 0x00d6 }
        L_0x00d6:
            throw r7
        L_0x00d7:
            java.lang.String r0 = "M3U8DownloadAndDecryptTask"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "urlString:"
            r2.append(r3)
            r2.append(r7)
            java.lang.String r7 = " filePath:"
            r2.append(r7)
            r2.append(r8)
            java.lang.String r7 = r2.toString()
            com.xiaomi.smarthome.framework.log.LogUtil.a((java.lang.String) r0, (java.lang.String) r7)
        L_0x00f5:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.camera.cloudstorage.utils.M3U8DownloadAndDecryptTask.downloadFile(java.lang.String, java.lang.String):boolean");
    }

    static final URLConnection openConnection_aroundBody0(M3U8DownloadAndDecryptTask m3U8DownloadAndDecryptTask, URL url) {
        return url.openConnection();
    }

    /* access modifiers changed from: protected */
    public void onProgressUpdate(Integer... numArr) {
        super.onProgressUpdate(numArr);
        if (this.callback != null) {
            this.callback.onProgress(numArr[0].intValue());
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Integer num) {
        Integer num2;
        super.onPostExecute(num);
        if (this.callback != null) {
            if (this.trackDataSize > 0 && this.tsFileList.size() == this.trackDataSize) {
                num2 = 101;
            } else if (num.intValue() == -104) {
                num2 = -104;
            } else {
                num2 = -102;
            }
            if (num2.intValue() == 101) {
                ArrayList arrayList = new ArrayList();
                for (TrackDataLite trackDataLite : this.tsFileList) {
                    arrayList.add(trackDataLite.uri);
                }
                IFileDownloadCallback iFileDownloadCallback = this.callback;
                iFileDownloadCallback.onSuccess(arrayList, "" + this.m3u8FileFolder);
                return;
            }
            this.callback.onFailure(num2.intValue());
        }
    }
}
