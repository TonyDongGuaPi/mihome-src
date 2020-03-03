package org.mp4parser.muxer.tracks.ttml;

import com.adobe.xmp.XMPConst;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.mp4parser.Box;
import org.mp4parser.boxes.iso14496.part12.SampleDescriptionBox;
import org.mp4parser.boxes.iso14496.part12.SubSampleInformationBox;
import org.mp4parser.boxes.iso14496.part30.XMLSubtitleSampleEntry;
import org.mp4parser.muxer.AbstractTrack;
import org.mp4parser.muxer.Sample;
import org.mp4parser.muxer.TrackMetaData;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TtmlTrackImpl extends AbstractTrack {
    TrackMetaData d = new TrackMetaData();
    SampleDescriptionBox e = new SampleDescriptionBox();
    XMLSubtitleSampleEntry f = new XMLSubtitleSampleEntry();
    List<Sample> g = new ArrayList();
    SubSampleInformationBox h = new SubSampleInformationBox();
    private long[] i;

    public void close() throws IOException {
    }

    public String p() {
        return "subt";
    }

    public TtmlTrackImpl(String str, List<Document> list) throws IOException, ParserConfigurationException, SAXException, XPathExpressionException, URISyntaxException {
        super(str);
        a(list);
        HashSet hashSet = new HashSet();
        this.i = new long[list.size()];
        XPathFactory.newInstance().newXPath().setNamespaceContext(TtmlHelpers.c);
        for (int i2 = 0; i2 < list.size(); i2++) {
            Document document = list.get(i2);
            SubSampleInformationBox.SubSampleEntry subSampleEntry = new SubSampleInformationBox.SubSampleEntry();
            this.h.e().add(subSampleEntry);
            subSampleEntry.a(1);
            this.i[i2] = f(document);
            List<byte[]> b = b(document);
            hashSet.addAll(e(document));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            TtmlHelpers.a(document, byteArrayOutputStream, 4);
            SubSampleInformationBox.SubSampleEntry.SubsampleEntry subsampleEntry = new SubSampleInformationBox.SubSampleEntry.SubsampleEntry();
            subsampleEntry.a((long) byteArrayOutputStream.size());
            subSampleEntry.c().add(subsampleEntry);
            for (byte[] next : b) {
                byteArrayOutputStream.write(next);
                SubSampleInformationBox.SubSampleEntry.SubsampleEntry subsampleEntry2 = new SubSampleInformationBox.SubSampleEntry.SubsampleEntry();
                subsampleEntry2.a((long) next.length);
                subSampleEntry.c().add(subsampleEntry2);
            }
            final byte[] byteArray = byteArrayOutputStream.toByteArray();
            this.g.add(new Sample() {
                public void a(WritableByteChannel writableByteChannel) throws IOException {
                    writableByteChannel.write(ByteBuffer.wrap(byteArray));
                }

                public long a() {
                    return (long) byteArray.length;
                }

                public ByteBuffer b() {
                    return ByteBuffer.wrap(byteArray);
                }
            });
        }
        this.f.a(a(",", TtmlHelpers.a(list.get(0))));
        this.f.b("");
        this.f.c(a(",", (String[]) new ArrayList(hashSet).toArray(new String[hashSet.size()])));
        this.e.a((Box) this.f);
        this.d.a(30000);
        this.d.a(65535);
    }

    public static String a(Document document) {
        return document.getDocumentElement().getAttribute(XMPConst.ak);
    }

    protected static List<byte[]> b(Document document) throws XPathExpressionException, URISyntaxException, IOException {
        NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().compile("//*/@backgroundImage").evaluate(document, XPathConstants.NODESET);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i2 = 1;
        for (int i3 = 0; i3 < nodeList.getLength(); i3++) {
            Node item = nodeList.item(i3);
            String nodeValue = item.getNodeValue();
            String substring = nodeValue.substring(nodeValue.lastIndexOf("."));
            String str = (String) linkedHashMap.get(nodeValue);
            if (str == null) {
                str = "urn:mp4parser:" + i2 + substring;
                linkedHashMap.put(str, nodeValue);
                i2++;
            }
            item.setNodeValue(str);
        }
        ArrayList arrayList = new ArrayList();
        if (!linkedHashMap.isEmpty()) {
            for (Map.Entry value : linkedHashMap.entrySet()) {
                arrayList.add(a(new URI(document.getDocumentURI()).resolve((String) value.getValue()).toURL().openStream()));
            }
        }
        return arrayList;
    }

    private static String a(String str, String[] strArr) {
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        for (String append : strArr) {
            sb.append(append);
            sb.append(str);
        }
        if (sb.length() > 0) {
            i2 = sb.length() - 1;
        }
        sb.setLength(i2);
        return sb.toString();
    }

    private static long g(Document document) {
        XPath newXPath = XPathFactory.newInstance().newXPath();
        newXPath.setNamespaceContext(TtmlHelpers.c);
        try {
            NodeList nodeList = (NodeList) newXPath.compile("//*[name()='p']").evaluate(document, XPathConstants.NODESET);
            long j = 0;
            for (int i2 = 0; i2 < nodeList.getLength(); i2++) {
                j = Math.max(TtmlHelpers.b(nodeList.item(i2)), j);
            }
            return j;
        } catch (XPathExpressionException e2) {
            throw new RuntimeException(e2);
        }
    }

    private static byte[] a(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[8096];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = inputStream.read(bArr);
            if (-1 == read) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    /* access modifiers changed from: protected */
    public long c(Document document) {
        XPath newXPath = XPathFactory.newInstance().newXPath();
        newXPath.setNamespaceContext(TtmlHelpers.c);
        try {
            NodeList nodeList = (NodeList) newXPath.compile("//*[@begin]").evaluate(document, XPathConstants.NODESET);
            long j = Long.MAX_VALUE;
            for (int i2 = 0; i2 < nodeList.getLength(); i2++) {
                j = Math.min(TtmlHelpers.a(nodeList.item(i2)), j);
            }
            return j;
        } catch (XPathExpressionException e2) {
            throw new RuntimeException(e2);
        }
    }

    /* access modifiers changed from: protected */
    public long d(Document document) {
        XPath newXPath = XPathFactory.newInstance().newXPath();
        newXPath.setNamespaceContext(TtmlHelpers.c);
        try {
            NodeList nodeList = (NodeList) newXPath.compile("//*[@end]").evaluate(document, XPathConstants.NODESET);
            long j = 0;
            for (int i2 = 0; i2 < nodeList.getLength(); i2++) {
                j = Math.max(TtmlHelpers.b(nodeList.item(i2)), j);
            }
            return j;
        } catch (XPathExpressionException e2) {
            throw new RuntimeException(e2);
        }
    }

    /* access modifiers changed from: protected */
    public void a(List<Document> list) {
        String str = null;
        for (Document a2 : list) {
            String a3 = a(a2);
            if (str == null) {
                this.d.a(Locale.forLanguageTag(a3).getISO3Language());
                str = a3;
            } else if (!str.equals(a3)) {
                throw new RuntimeException("Within one Track all sample documents need to have the same language");
            }
        }
    }

    /* access modifiers changed from: protected */
    public List<String> e(Document document) throws XPathExpressionException {
        NodeList nodeList = (NodeList) XPathFactory.newInstance().newXPath().compile("//*/@smpte:backgroundImage").evaluate(document, XPathConstants.NODESET);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (int i2 = 0; i2 < nodeList.getLength(); i2++) {
            String nodeValue = nodeList.item(i2).getNodeValue();
            String substring = nodeValue.substring(nodeValue.lastIndexOf("."));
            if (substring.contains("jpg") || substring.contains("jpeg")) {
                linkedHashSet.add("image/jpeg");
            } else if (substring.contains("png")) {
                linkedHashSet.add("image/png");
            }
        }
        return new ArrayList(linkedHashSet);
    }

    /* access modifiers changed from: package-private */
    public long f(Document document) {
        return d(document) - c(document);
    }

    public SampleDescriptionBox n() {
        return this.e;
    }

    public long[] m() {
        long[] jArr = new long[this.i.length];
        for (int i2 = 0; i2 < jArr.length; i2++) {
            jArr[i2] = (this.i[i2] * this.d.b()) / 1000;
        }
        return jArr;
    }

    public TrackMetaData o() {
        return this.d;
    }

    public List<Sample> l() {
        return this.g;
    }

    public SubSampleInformationBox d() {
        return this.h;
    }
}
