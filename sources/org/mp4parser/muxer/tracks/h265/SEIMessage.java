package org.mp4parser.muxer.tracks.h265;

import java.io.IOException;
import java.io.PrintStream;
import org.mp4parser.boxes.iso14496.part1.objectdescriptors.BitReaderBuffer;

public class SEIMessage {
    public SEIMessage(BitReaderBuffer bitReaderBuffer) throws IOException {
        int i = 0;
        while (((long) bitReaderBuffer.a(8)) == 255) {
            i += 255;
        }
        int a2 = i + bitReaderBuffer.a(8);
        do {
        } while (((long) bitReaderBuffer.a(8)) == 255);
        bitReaderBuffer.a(8);
        PrintStream printStream = System.err;
        printStream.println("payloadType " + a2);
    }
}
