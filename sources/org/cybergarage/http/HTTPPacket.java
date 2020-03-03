package org.cybergarage.http;

import com.taobao.weex.el.parse.Operators;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.Vector;
import org.cybergarage.net.HostInterface;
import org.cybergarage.util.Debug;
import org.cybergarage.util.StringUtil;

public class HTTPPacket {
    private byte[] content = new byte[0];
    private InputStream contentInput = null;
    private String firstLine = "";
    private Vector httpHeaderList = new Vector();
    private String version;

    public HTTPPacket() {
        setVersion("1.1");
        setContentInputStream((InputStream) null);
    }

    public HTTPPacket(HTTPPacket hTTPPacket) {
        setVersion("1.1");
        set(hTTPPacket);
        setContentInputStream((InputStream) null);
    }

    public HTTPPacket(InputStream inputStream) {
        setVersion("1.1");
        set(inputStream);
        setContentInputStream((InputStream) null);
    }

    public void init() {
        setFirstLine("");
        clearHeaders();
        setContent(new byte[0], false);
        setContentInputStream((InputStream) null);
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String getVersion() {
        return this.version;
    }

    private String readLine(BufferedInputStream bufferedInputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1];
        try {
            int read = bufferedInputStream.read(bArr);
            while (true) {
                if (read <= 0) {
                    break;
                } else if (bArr[0] == 10) {
                    break;
                } else {
                    if (bArr[0] != 13) {
                        byteArrayOutputStream.write(bArr[0]);
                    }
                    read = bufferedInputStream.read(bArr);
                }
            }
        } catch (InterruptedIOException unused) {
        } catch (IOException e) {
            Debug.warning((Exception) e);
        }
        return byteArrayOutputStream.toString();
    }

    /* access modifiers changed from: protected */
    /*  JADX ERROR: IF instruction can be used only in fallback mode
        jadx.core.utils.exceptions.CodegenException: IF instruction can be used only in fallback mode
        	at jadx.core.codegen.InsnGen.fallbackOnlyInsn(InsnGen.java:579)
        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:485)
        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
        	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:205)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
        	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:250)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
        	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:311)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:68)
        	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
        	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
        	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
        	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
        	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
        	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
        	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
        	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
        	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
        */
    public boolean set(java.io.InputStream r20, boolean r21) {
        /*
            r19 = this;
            r1 = r19
            r2 = 0
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0134 }
            r0 = r20
            r3.<init>(r0)     // Catch:{ Exception -> 0x0134 }
            java.lang.String r0 = r1.readLine(r3)     // Catch:{ Exception -> 0x0134 }
            if (r0 == 0) goto L_0x0133
            int r4 = r0.length()     // Catch:{ Exception -> 0x0134 }
            if (r4 > 0) goto L_0x0018
            goto L_0x0133
        L_0x0018:
            r1.setFirstLine(r0)     // Catch:{ Exception -> 0x0134 }
            org.cybergarage.http.HTTPStatus r4 = new org.cybergarage.http.HTTPStatus     // Catch:{ Exception -> 0x0134 }
            r4.<init>(r0)     // Catch:{ Exception -> 0x0134 }
            int r0 = r4.getStatusCode()     // Catch:{ Exception -> 0x0134 }
            r4 = 100
            r5 = 1
            if (r0 != r4) goto L_0x0059
            java.lang.String r0 = r1.readLine(r3)     // Catch:{ Exception -> 0x0134 }
        L_0x002d:
            if (r0 == 0) goto L_0x0048
            int r4 = r0.length()     // Catch:{ Exception -> 0x0134 }
            if (r4 <= 0) goto L_0x0048
            org.cybergarage.http.HTTPHeader r4 = new org.cybergarage.http.HTTPHeader     // Catch:{ Exception -> 0x0134 }
            r4.<init>(r0)     // Catch:{ Exception -> 0x0134 }
            boolean r0 = r4.hasName()     // Catch:{ Exception -> 0x0134 }
            if (r0 != r5) goto L_0x0043
            r1.setHeader(r4)     // Catch:{ Exception -> 0x0134 }
        L_0x0043:
            java.lang.String r0 = r1.readLine(r3)     // Catch:{ Exception -> 0x0134 }
            goto L_0x002d
        L_0x0048:
            java.lang.String r0 = r1.readLine(r3)     // Catch:{ Exception -> 0x0134 }
            if (r0 == 0) goto L_0x0058
            int r4 = r0.length()     // Catch:{ Exception -> 0x0134 }
            if (r4 <= 0) goto L_0x0058
            r1.setFirstLine(r0)     // Catch:{ Exception -> 0x0134 }
            goto L_0x0059
        L_0x0058:
            return r5
        L_0x0059:
            java.lang.String r0 = r1.readLine(r3)     // Catch:{ Exception -> 0x0134 }
        L_0x005d:
            if (r0 == 0) goto L_0x0078
            int r4 = r0.length()     // Catch:{ Exception -> 0x0134 }
            if (r4 <= 0) goto L_0x0078
            org.cybergarage.http.HTTPHeader r4 = new org.cybergarage.http.HTTPHeader     // Catch:{ Exception -> 0x0134 }
            r4.<init>(r0)     // Catch:{ Exception -> 0x0134 }
            boolean r0 = r4.hasName()     // Catch:{ Exception -> 0x0134 }
            if (r0 != r5) goto L_0x0073
            r1.setHeader(r4)     // Catch:{ Exception -> 0x0134 }
        L_0x0073:
            java.lang.String r0 = r1.readLine(r3)     // Catch:{ Exception -> 0x0134 }
            goto L_0x005d
        L_0x0078:
            r0 = r21
            if (r0 != r5) goto L_0x0082
            java.lang.String r0 = ""
            r1.setContent((java.lang.String) r0, (boolean) r2)     // Catch:{ Exception -> 0x0134 }
            return r5
        L_0x0082:
            boolean r4 = r19.isChunked()     // Catch:{ Exception -> 0x0134 }
            r6 = 16
            r7 = 0
            if (r4 != r5) goto L_0x009d
            java.lang.String r0 = r1.readLine(r3)     // Catch:{ Exception -> 0x009b }
            if (r0 == 0) goto L_0x009b
            java.lang.String r0 = r0.trim()     // Catch:{ Exception -> 0x009b }
            long r9 = java.lang.Long.parseLong(r0, r6)     // Catch:{ Exception -> 0x009b }
            goto L_0x00a1
        L_0x009b:
            r9 = r7
            goto L_0x00a1
        L_0x009d:
            long r9 = r19.getContentLength()     // Catch:{ Exception -> 0x0134 }
        L_0x00a1:
            java.io.ByteArrayOutputStream r11 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x0134 }
            r11.<init>()     // Catch:{ Exception -> 0x0134 }
        L_0x00a6:
            int r0 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r0 >= 0) goto L_0x012b
            int r0 = org.cybergarage.http.HTTP.getChunkSize()     // Catch:{ Exception -> 0x0134 }
            long r12 = (long) r0     // Catch:{ Exception -> 0x0134 }
            int r0 = (r9 > r12 ? 1 : (r9 == r12 ? 0 : -1))
            if (r0 <= 0) goto L_0x00b5
            r14 = r12
            goto L_0x00b6
        L_0x00b5:
            r14 = r9
        L_0x00b6:
            int r0 = (int) r14     // Catch:{ Exception -> 0x0134 }
            byte[] r0 = new byte[r0]     // Catch:{ Exception -> 0x0134 }
            r14 = r7
        L_0x00ba:
            int r16 = (r14 > r9 ? 1 : (r14 == r9 ? 0 : -1))
            if (r16 >= 0) goto L_0x00de
            long r16 = r9 - r14
            int r18 = (r12 > r16 ? 1 : (r12 == r16 ? 0 : -1))
            if (r18 >= 0) goto L_0x00c6
            r6 = r12
            goto L_0x00c8
        L_0x00c6:
            r6 = r16
        L_0x00c8:
            int r6 = (int) r6
            int r6 = r3.read(r0, r2, r6)     // Catch:{ Exception -> 0x00da }
            if (r6 >= 0) goto L_0x00d0
            goto L_0x00de
        L_0x00d0:
            r11.write(r0, r2, r6)     // Catch:{ Exception -> 0x00da }
            long r6 = (long) r6
            long r14 = r14 + r6
            r6 = 16
            r7 = 0
            goto L_0x00ba
        L_0x00da:
            r0 = move-exception
            org.cybergarage.util.Debug.warning((java.lang.Exception) r0)     // Catch:{ Exception -> 0x0134 }
        L_0x00de:
            if (r4 != r5) goto L_0x0121
            r6 = 0
        L_0x00e2:
            java.lang.String r0 = "\r\n"
            int r0 = r0.length()     // Catch:{ Exception -> 0x0134 }
            long r8 = (long) r0     // Catch:{ Exception -> 0x0134 }
            long r8 = r8 - r6
            long r8 = r3.skip(r8)     // Catch:{ Exception -> 0x0134 }
            r12 = 0
            int r0 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r0 >= 0) goto L_0x00f5
            goto L_0x0102
        L_0x00f5:
            r0 = 0
            long r6 = r6 + r8
            java.lang.String r0 = "\r\n"
            int r0 = r0.length()     // Catch:{ Exception -> 0x0134 }
            long r8 = (long) r0
            int r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r0 < 0) goto L_0x00e2
        L_0x0102:
            java.lang.String r0 = r1.readLine(r3)     // Catch:{ Exception -> 0x011c }
            java.lang.String r6 = new java.lang.String     // Catch:{ Exception -> 0x011c }
            byte[] r7 = r0.getBytes()     // Catch:{ Exception -> 0x011c }
            int r0 = r0.length()     // Catch:{ Exception -> 0x011c }
            int r0 = r0 + -2
            r6.<init>(r7, r2, r0)     // Catch:{ Exception -> 0x011c }
            r8 = 16
            long r6 = java.lang.Long.parseLong(r6, r8)     // Catch:{ Exception -> 0x011e }
            goto L_0x011f
        L_0x011c:
            r8 = 16
        L_0x011e:
            r6 = r12
        L_0x011f:
            r9 = r6
            goto L_0x0126
        L_0x0121:
            r8 = 16
            r12 = 0
            r9 = r12
        L_0x0126:
            r7 = r12
            r6 = 16
            goto L_0x00a6
        L_0x012b:
            byte[] r0 = r11.toByteArray()     // Catch:{ Exception -> 0x0134 }
            r1.setContent((byte[]) r0, (boolean) r2)     // Catch:{ Exception -> 0x0134 }
            return r5
        L_0x0133:
            return r2
        L_0x0134:
            r0 = move-exception
            org.cybergarage.util.Debug.warning((java.lang.Exception) r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.cybergarage.http.HTTPPacket.set(java.io.InputStream, boolean):boolean");
    }

    /* access modifiers changed from: protected */
    public boolean set(InputStream inputStream) {
        return set(inputStream, false);
    }

    /* access modifiers changed from: protected */
    public boolean set(HTTPSocket hTTPSocket) {
        return set(hTTPSocket.getInputStream());
    }

    /* access modifiers changed from: protected */
    public void set(HTTPPacket hTTPPacket) {
        setFirstLine(hTTPPacket.getFirstLine());
        clearHeaders();
        int nHeaders = hTTPPacket.getNHeaders();
        for (int i = 0; i < nHeaders; i++) {
            addHeader(hTTPPacket.getHeader(i));
        }
        setContent(hTTPPacket.getContent());
    }

    public boolean read(HTTPSocket hTTPSocket) {
        init();
        return set(hTTPSocket);
    }

    private void setFirstLine(String str) {
        this.firstLine = str;
    }

    /* access modifiers changed from: protected */
    public String getFirstLine() {
        return this.firstLine;
    }

    /* access modifiers changed from: protected */
    public String getFirstLineToken(int i) {
        StringTokenizer stringTokenizer = new StringTokenizer(this.firstLine, " ");
        String str = "";
        for (int i2 = 0; i2 <= i; i2++) {
            if (!stringTokenizer.hasMoreTokens()) {
                return "";
            }
            str = stringTokenizer.nextToken();
        }
        return str;
    }

    public boolean hasFirstLine() {
        return this.firstLine.length() > 0;
    }

    public int getNHeaders() {
        return this.httpHeaderList.size();
    }

    public void addHeader(HTTPHeader hTTPHeader) {
        this.httpHeaderList.add(hTTPHeader);
    }

    public void addHeader(String str, String str2) {
        this.httpHeaderList.add(new HTTPHeader(str, str2));
    }

    public HTTPHeader getHeader(int i) {
        return (HTTPHeader) this.httpHeaderList.get(i);
    }

    public HTTPHeader getHeader(String str) {
        int nHeaders = getNHeaders();
        for (int i = 0; i < nHeaders; i++) {
            HTTPHeader header = getHeader(i);
            if (header.getName().equalsIgnoreCase(str)) {
                return header;
            }
        }
        return null;
    }

    public void clearHeaders() {
        this.httpHeaderList.clear();
        this.httpHeaderList = new Vector();
    }

    public boolean hasHeader(String str) {
        return getHeader(str) != null;
    }

    public void setHeader(String str, String str2) {
        HTTPHeader header = getHeader(str);
        if (header != null) {
            header.setValue(str2);
        } else {
            addHeader(str, str2);
        }
    }

    public void setHeader(String str, int i) {
        setHeader(str, Integer.toString(i));
    }

    public void setHeader(String str, long j) {
        setHeader(str, Long.toString(j));
    }

    public void setHeader(HTTPHeader hTTPHeader) {
        setHeader(hTTPHeader.getName(), hTTPHeader.getValue());
    }

    public String getHeaderValue(String str) {
        HTTPHeader header = getHeader(str);
        if (header == null) {
            return "";
        }
        return header.getValue();
    }

    public void setStringHeader(String str, String str2, String str3, String str4) {
        if (!str2.startsWith(str3)) {
            str2 = str3 + str2;
        }
        if (!str2.endsWith(str4)) {
            str2 = str2 + str4;
        }
        setHeader(str, str2);
    }

    public void setStringHeader(String str, String str2) {
        setStringHeader(str, str2, "\"", "\"");
    }

    public String getStringHeaderValue(String str, String str2, String str3) {
        String headerValue = getHeaderValue(str);
        if (headerValue.startsWith(str2)) {
            headerValue = headerValue.substring(1, headerValue.length());
        }
        return headerValue.endsWith(str3) ? headerValue.substring(0, headerValue.length() - 1) : headerValue;
    }

    public String getStringHeaderValue(String str) {
        return getStringHeaderValue(str, "\"", "\"");
    }

    public void setIntegerHeader(String str, int i) {
        setHeader(str, Integer.toString(i));
    }

    public void setLongHeader(String str, long j) {
        setHeader(str, Long.toString(j));
    }

    public int getIntegerHeaderValue(String str) {
        HTTPHeader header = getHeader(str);
        if (header == null) {
            return 0;
        }
        return StringUtil.toInteger(header.getValue());
    }

    public long getLongHeaderValue(String str) {
        HTTPHeader header = getHeader(str);
        if (header == null) {
            return 0;
        }
        return StringUtil.toLong(header.getValue());
    }

    public String getHeaderString() {
        StringBuffer stringBuffer = new StringBuffer();
        int nHeaders = getNHeaders();
        for (int i = 0; i < nHeaders; i++) {
            HTTPHeader header = getHeader(i);
            stringBuffer.append(header.getName() + ": " + header.getValue() + "\r\n");
        }
        return stringBuffer.toString();
    }

    public void setContent(byte[] bArr, boolean z) {
        this.content = bArr;
        if (z) {
            setContentLength((long) bArr.length);
        }
    }

    public void setContent(byte[] bArr) {
        setContent(bArr, true);
    }

    public void setContent(String str, boolean z) {
        setContent(str.getBytes(), z);
    }

    public void setContent(String str) {
        setContent(str, true);
    }

    public byte[] getContent() {
        return this.content;
    }

    public String getContentString() {
        String charSet = getCharSet();
        if (charSet == null || charSet.length() <= 0) {
            return new String(this.content);
        }
        try {
            return new String(this.content, charSet);
        } catch (Exception e) {
            Debug.warning(e);
            return new String(this.content);
        }
    }

    public boolean hasContent() {
        return this.content.length > 0;
    }

    public void setContentInputStream(InputStream inputStream) {
        this.contentInput = inputStream;
    }

    public InputStream getContentInputStream() {
        return this.contentInput;
    }

    public boolean hasContentInputStream() {
        return this.contentInput != null;
    }

    public void setContentType(String str) {
        setHeader("Content-Type", str);
    }

    public String getContentType() {
        return getHeaderValue("Content-Type");
    }

    public String getCharSet() {
        String lowerCase;
        int indexOf;
        String contentType = getContentType();
        if (contentType == null || (indexOf = lowerCase.indexOf(HTTP.CHARSET)) < 0) {
            return "";
        }
        int length = indexOf + HTTP.CHARSET.length() + 1;
        String str = new String((lowerCase = contentType.toLowerCase()).getBytes(), length, lowerCase.length() - length);
        if (str.length() < 0) {
            return "";
        }
        if (str.charAt(0) == '\"') {
            str = str.substring(1, str.length() - 1);
        }
        if (str.length() < 0) {
            return "";
        }
        return str.charAt(str.length() - 1) == '\"' ? str.substring(0, str.length() - 1) : str;
    }

    public void setContentLength(long j) {
        setLongHeader("Content-Length", j);
    }

    public long getContentLength() {
        return getLongHeaderValue("Content-Length");
    }

    public boolean hasConnection() {
        return hasHeader("Connection");
    }

    public void setConnection(String str) {
        setHeader("Connection", str);
    }

    public String getConnection() {
        return getHeaderValue("Connection");
    }

    public boolean isCloseConnection() {
        String connection;
        if (hasConnection() && (connection = getConnection()) != null) {
            return connection.equalsIgnoreCase("close");
        }
        return false;
    }

    public boolean isKeepAliveConnection() {
        String connection;
        if (hasConnection() && (connection = getConnection()) != null) {
            return connection.equalsIgnoreCase("Keep-Alive");
        }
        return false;
    }

    public boolean hasContentRange() {
        return hasHeader("Content-Range") || hasHeader("Range");
    }

    public void setContentRange(long j, long j2, long j3) {
        StringBuilder sb = new StringBuilder();
        sb.append((("" + "bytes ") + Long.toString(j) + "-") + Long.toString(j2) + "/");
        sb.append(0 < j3 ? Long.toString(j3) : "*");
        setHeader("Content-Range", sb.toString());
    }

    public long[] getContentRange() {
        long[] jArr = {0, 0, 0};
        if (!hasContentRange()) {
            return jArr;
        }
        String headerValue = getHeaderValue("Content-Range");
        if (headerValue.length() <= 0) {
            headerValue = getHeaderValue("Range");
        }
        if (headerValue.length() <= 0) {
            return jArr;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(headerValue, " =");
        if (!stringTokenizer.hasMoreTokens()) {
            return jArr;
        }
        stringTokenizer.nextToken(" ");
        if (!stringTokenizer.hasMoreTokens()) {
            return jArr;
        }
        try {
            jArr[0] = Long.parseLong(stringTokenizer.nextToken(" -"));
        } catch (NumberFormatException unused) {
        }
        if (!stringTokenizer.hasMoreTokens()) {
            return jArr;
        }
        try {
            jArr[1] = Long.parseLong(stringTokenizer.nextToken("-/"));
        } catch (NumberFormatException unused2) {
        }
        if (!stringTokenizer.hasMoreTokens()) {
            return jArr;
        }
        try {
            jArr[2] = Long.parseLong(stringTokenizer.nextToken("/"));
        } catch (NumberFormatException unused3) {
        }
        return jArr;
    }

    public long getContentRangeFirstPosition() {
        return getContentRange()[0];
    }

    public long getContentRangeLastPosition() {
        return getContentRange()[1];
    }

    public long getContentRangeInstanceLength() {
        return getContentRange()[2];
    }

    public void setCacheControl(String str) {
        setHeader("Cache-Control", str);
    }

    public void setCacheControl(String str, int i) {
        setHeader("Cache-Control", str + "=" + Integer.toString(i));
    }

    public void setCacheControl(int i) {
        setCacheControl("max-age", i);
    }

    public String getCacheControl() {
        return getHeaderValue("Cache-Control");
    }

    public void setServer(String str) {
        setHeader("Server", str);
    }

    public String getServer() {
        return getHeaderValue("Server");
    }

    public void setHost(String str, int i) {
        if (HostInterface.isIPv6Address(str)) {
            str = Operators.ARRAY_START_STR + str + Operators.ARRAY_END_STR;
        }
        setHeader(HTTP.HOST, str + ":" + Integer.toString(i));
    }

    public void setHost(String str) {
        if (HostInterface.isIPv6Address(str)) {
            str = Operators.ARRAY_START_STR + str + Operators.ARRAY_END_STR;
        }
        setHeader(HTTP.HOST, str);
    }

    public String getHost() {
        return getHeaderValue(HTTP.HOST);
    }

    public void setDate(Calendar calendar) {
        setHeader("Date", new Date(calendar).getDateString());
    }

    public String getDate() {
        return getHeaderValue("Date");
    }

    public boolean hasTransferEncoding() {
        return hasHeader("Transfer-Encoding");
    }

    public void setTransferEncoding(String str) {
        setHeader("Transfer-Encoding", str);
    }

    public String getTransferEncoding() {
        return getHeaderValue("Transfer-Encoding");
    }

    public boolean isChunked() {
        String transferEncoding;
        if (hasTransferEncoding() && (transferEncoding = getTransferEncoding()) != null) {
            return transferEncoding.equalsIgnoreCase(HTTP.CHUNKED);
        }
        return false;
    }
}
