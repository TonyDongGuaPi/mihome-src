package org.cybergarage.xml;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.cybergarage.http.HTTP;
import org.cybergarage.http.HTTPRequest;
import org.cybergarage.http.HTTPResponse;

public abstract class Parser {
    public abstract Node parse(InputStream inputStream) throws ParserException;

    public Node parse(URL url) throws ParserException {
        String host = url.getHost();
        int port = url.getPort();
        if (port == -1) {
            port = 80;
        }
        String path = url.getPath();
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Content-Length", "0");
            if (host != null) {
                httpURLConnection.setRequestProperty(HTTP.HOST, host);
            }
            InputStream inputStream = httpURLConnection.getInputStream();
            Node parse = parse(inputStream);
            inputStream.close();
            httpURLConnection.disconnect();
            return parse;
        } catch (Exception unused) {
            HTTPRequest hTTPRequest = new HTTPRequest();
            hTTPRequest.setMethod("GET");
            hTTPRequest.setURI(path);
            HTTPResponse post = hTTPRequest.post(host, port);
            if (post.isSuccessful()) {
                return parse((InputStream) new ByteArrayInputStream(new String(post.getContent()).getBytes()));
            }
            throw new ParserException("HTTP comunication failed: no answer from peer.Unable to retrive resoure -> " + url.toString());
        }
    }

    public Node parse(File file) throws ParserException {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            Node parse = parse((InputStream) fileInputStream);
            fileInputStream.close();
            return parse;
        } catch (Exception e) {
            throw new ParserException(e);
        }
    }

    public Node parse(String str) throws ParserException {
        try {
            return parse((InputStream) new ByteArrayInputStream(str.getBytes()));
        } catch (Exception e) {
            throw new ParserException(e);
        }
    }
}
