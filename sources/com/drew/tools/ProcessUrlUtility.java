package com.drew.tools;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import java.io.IOException;
import java.net.URL;

public class ProcessUrlUtility {
    public static void a(String[] strArr) throws IOException, JpegProcessingException {
        if (strArr.length == 0) {
            System.err.println("Expects one or more URLs as arguments.");
            System.exit(1);
        }
        for (String url : strArr) {
            a(new URL(url));
        }
        System.out.println("Completed.");
    }

    private static void a(URL url) throws IOException {
        try {
            Metadata a2 = ImageMetadataReader.a(url.openConnection().getInputStream());
            if (a2.c()) {
                System.err.println(url);
                for (Directory next : a2.a()) {
                    if (next.f()) {
                        for (String str : next.g()) {
                            System.err.printf("\t[%s] %s%n", new Object[]{next.a(), str});
                        }
                    }
                }
            }
            for (Directory next2 : a2.a()) {
                for (Tag next3 : next2.d()) {
                    String e = next3.e();
                    String a3 = next2.a();
                    String c = next3.c();
                    if (c != null && c.length() > 1024) {
                        c = c.substring(0, 1024) + "...";
                    }
                    System.out.printf("[%s] %s = %s%n", new Object[]{a3, e, c});
                }
            }
        } catch (ImageProcessingException e2) {
            System.err.printf("%s: %s [Error Extracting Metadata]%n\t%s%n", new Object[]{e2.getClass().getName(), url, e2.getMessage()});
        } catch (Throwable th) {
            System.err.printf("%s: %s [Error Extracting Metadata]%n", new Object[]{th.getClass().getName(), url});
            th.printStackTrace(System.err);
        }
    }
}
