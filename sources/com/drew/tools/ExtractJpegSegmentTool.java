package com.drew.tools;

import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.imaging.jpeg.JpegSegmentData;
import com.drew.imaging.jpeg.JpegSegmentReader;
import com.drew.imaging.jpeg.JpegSegmentType;
import com.drew.lang.Iterables;
import com.drew.lang.annotations.NotNull;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.List;

public class ExtractJpegSegmentTool {
    public static void a(String[] strArr) throws IOException, JpegProcessingException {
        if (strArr.length < 1) {
            a();
            System.exit(1);
        }
        String str = strArr[0];
        if (!new File(str).exists()) {
            System.err.println("File does not exist");
            a();
            System.exit(1);
        }
        HashSet hashSet = new HashSet();
        for (int i = 1; i < strArr.length; i++) {
            JpegSegmentType valueOf = JpegSegmentType.valueOf(strArr[i].toUpperCase());
            if (!valueOf.canContainMetadata) {
                System.err.printf("WARNING: Segment type %s cannot contain metadata so it may not be necessary to extract it%n", new Object[]{valueOf});
            }
            hashSet.add(valueOf);
        }
        if (hashSet.size() == 0) {
            hashSet.addAll(JpegSegmentType.canContainMetadataTypes);
        }
        PrintStream printStream = System.out;
        printStream.println("Reading: " + str);
        a(str, JpegSegmentReader.a(new File(str), (Iterable<JpegSegmentType>) hashSet));
    }

    public static void a(@NotNull String str, @NotNull JpegSegmentData jpegSegmentData) throws IOException {
        for (JpegSegmentType next : jpegSegmentData.a()) {
            List<E> a2 = Iterables.a(jpegSegmentData.b(next));
            if (a2.size() != 0) {
                if (a2.size() > 1) {
                    for (int i = 0; i < a2.size(); i++) {
                        String format = String.format("%s.%s.%d", new Object[]{str, next.toString().toLowerCase(), Integer.valueOf(i)});
                        PrintStream printStream = System.out;
                        printStream.println("Writing: " + format);
                        FileUtil.a(new File(format), (byte[]) a2.get(i));
                    }
                } else {
                    String format2 = String.format("%s.%s", new Object[]{str, next.toString().toLowerCase()});
                    PrintStream printStream2 = System.out;
                    printStream2.println("Writing: " + format2);
                    FileUtil.a(new File(format2), (byte[]) a2.get(0));
                }
            }
        }
    }

    private static void a() {
        System.out.println("USAGE:\n");
        System.out.println("\tjava com.drew.tools.ExtractJpegSegmentTool <filename> [<segment> ...]\n");
        System.out.print("Where <segment> is zero or more of:");
        for (JpegSegmentType jpegSegmentType : (JpegSegmentType[]) JpegSegmentType.class.getEnumConstants()) {
            if (jpegSegmentType.canContainMetadata) {
                System.out.print(" " + jpegSegmentType.toString());
            }
        }
        System.out.println();
    }
}
