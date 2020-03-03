package com.xiaomi.infra.galaxy.fds.auth.signature;

import com.alipay.sdk.sys.a;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.LinkedListMultimap;
import com.xiaomi.infra.galaxy.fds.Common;
import com.xiaomi.infra.galaxy.fds.SubResource;
import com.xiaomi.infra.galaxy.fds.exception.GalaxyFDSException;
import com.xiaomi.infra.galaxy.fds.model.HttpMethod;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Signer {

    /* renamed from: a  reason: collision with root package name */
    private static final Log f10152a = LogFactory.getLog(Signer.class);
    private static final Set<String> b = new HashSet();
    private static final String c = XiaomiHeader.DATE.getName();

    static String a(String str) {
        return str == null ? "" : str;
    }

    static {
        for (SubResource name : SubResource.values()) {
            b.add(name.getName());
        }
    }

    public static byte[] a(HttpMethod httpMethod, URI uri, LinkedListMultimap<String, String> linkedListMultimap, String str, SignAlgorithm signAlgorithm) throws NoSuchAlgorithmException, InvalidKeyException {
        Preconditions.checkNotNull(httpMethod);
        Preconditions.checkNotNull(uri);
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(signAlgorithm);
        String a2 = a(httpMethod, uri, linkedListMultimap);
        if (f10152a.isDebugEnabled()) {
            Log log = f10152a;
            log.debug("Sign for request: " + httpMethod + " " + uri + ", stringToSign=" + a2);
        }
        Mac instance = Mac.getInstance(signAlgorithm.name());
        instance.init(new SecretKeySpec(str.getBytes(), signAlgorithm.name()));
        return instance.doFinal(a2.getBytes());
    }

    public static String b(HttpMethod httpMethod, URI uri, LinkedListMultimap linkedListMultimap, String str, SignAlgorithm signAlgorithm) throws NoSuchAlgorithmException, InvalidKeyException {
        return Base64Util.a(a(httpMethod, uri, linkedListMultimap, str, signAlgorithm));
    }

    public static URI a(String str, String str2, String str3, List<String> list, Date date, HttpMethod httpMethod, String str4, String str5, SignAlgorithm signAlgorithm) throws GalaxyFDSException {
        URI uri;
        String str6 = str2;
        String str7 = str3;
        List<String> list2 = list;
        String str8 = str4;
        SignAlgorithm signAlgorithm2 = signAlgorithm;
        try {
            URI uri2 = new URI(str);
            if (list2 != null) {
                if (!list.isEmpty()) {
                    uri = new URI(uri2.getScheme(), (String) null, uri2.getHost(), uri2.getPort(), "/" + str6 + "/" + str7, StringUtils.a((Collection) list2, a.b) + a.b + "GalaxyAccessKeyId" + "=" + str8 + a.b + "Expires" + "=" + date.getTime(), (String) null);
                    return new URI(uri.toString() + a.b + "Signature" + "=" + b(httpMethod, uri, (LinkedListMultimap) null, str5, signAlgorithm2));
                }
            }
            uri = new URI(uri2.getScheme(), (String) null, uri2.getHost(), uri2.getPort(), "/" + str6 + "/" + str7, "GalaxyAccessKeyId=" + str8 + a.b + "Expires" + "=" + date.getTime(), (String) null);
            return new URI(uri.toString() + a.b + "Signature" + "=" + b(httpMethod, uri, (LinkedListMultimap) null, str5, signAlgorithm2));
        } catch (URISyntaxException e) {
            f10152a.error("Invalid URI syntax", e);
            throw new GalaxyFDSException("Invalid URI syntax", e);
        } catch (InvalidKeyException e2) {
            f10152a.error("Invalid secret key spec", e2);
            throw new GalaxyFDSException("Invalid secret key spec", e2);
        } catch (NoSuchAlgorithmException e3) {
            f10152a.error("Unsupported signature algorithm:" + signAlgorithm2, e3);
            throw new GalaxyFDSException("Unsupported signature algorithm:" + signAlgorithm2, e3);
        }
    }

    public static String a(HttpMethod httpMethod, URI uri, LinkedListMultimap linkedListMultimap, String str, String str2, SignAlgorithm signAlgorithm) throws NoSuchAlgorithmException, InvalidKeyException {
        String b2 = b(httpMethod, uri, linkedListMultimap, str2, signAlgorithm);
        return "Galaxy-V2 " + str + ":" + b2;
    }

    static String a(HttpMethod httpMethod, URI uri, LinkedListMultimap<String, String> linkedListMultimap) {
        StringBuilder sb = new StringBuilder();
        sb.append(httpMethod.name());
        sb.append("\n");
        sb.append(a(linkedListMultimap, Common.k).get(0));
        sb.append("\n");
        sb.append(a(linkedListMultimap, "content-type").get(0));
        sb.append("\n");
        long b2 = b(uri);
        if (b2 > 0) {
            sb.append(b2);
            sb.append("\n");
        } else {
            String str = "";
            if ("".equals(a(linkedListMultimap, c).get(0))) {
                str = a(linkedListMultimap, "date").get(0);
            }
            sb.append(a(str));
            sb.append("\n");
        }
        sb.append(a(linkedListMultimap));
        sb.append(a(uri));
        return sb.toString();
    }

    static String a(LinkedListMultimap<String, String> linkedListMultimap) {
        if (linkedListMultimap == null) {
            return "";
        }
        TreeMap treeMap = new TreeMap();
        for (String str : linkedListMultimap.keySet()) {
            String lowerCase = str.toLowerCase();
            if (lowerCase.startsWith("x-xiaomi-")) {
                treeMap.put(lowerCase, Joiner.on(',').join((Iterable<?>) linkedListMultimap.get((Object) str)));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : treeMap.entrySet()) {
            sb.append((String) entry.getKey());
            sb.append(":");
            sb.append((String) entry.getValue());
            sb.append("\n");
        }
        return sb.toString();
    }

    static String a(URI uri) {
        StringBuilder sb = new StringBuilder();
        sb.append(uri.getPath());
        TreeMap treeMap = new TreeMap();
        for (Map.Entry entry : Utils.a(uri).entries()) {
            String str = (String) entry.getKey();
            if (b.contains(str)) {
                treeMap.put(str, (String) entry.getValue());
            }
        }
        if (!treeMap.isEmpty()) {
            sb.append("?");
            boolean z = true;
            for (Map.Entry entry2 : treeMap.entrySet()) {
                if (z) {
                    z = false;
                    sb.append((String) entry2.getKey());
                } else {
                    sb.append(a.b);
                    sb.append((String) entry2.getKey());
                }
                if (!((String) entry2.getValue()).isEmpty()) {
                    sb.append("=");
                    sb.append((String) entry2.getValue());
                }
            }
        }
        return sb.toString();
    }

    static List<String> a(LinkedListMultimap<String, String> linkedListMultimap, String str) {
        LinkedList linkedList = new LinkedList();
        if (linkedListMultimap == null) {
            linkedList.add("");
            return linkedList;
        }
        List<String> list = linkedListMultimap.get((Object) str);
        if (list != null && !list.isEmpty()) {
            return list;
        }
        linkedList.add("");
        return linkedList;
    }

    static long b(URI uri) {
        List list = Utils.a(uri).get((Object) "Expires");
        if (list == null || list.isEmpty()) {
            return 0;
        }
        return Long.parseLong((String) list.get(0));
    }
}
