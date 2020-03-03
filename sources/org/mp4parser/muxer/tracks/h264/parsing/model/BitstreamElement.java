package org.mp4parser.muxer.tracks.h264.parsing.model;

import java.io.IOException;
import java.io.OutputStream;

public abstract class BitstreamElement {
    public abstract void a(OutputStream outputStream) throws IOException;
}
