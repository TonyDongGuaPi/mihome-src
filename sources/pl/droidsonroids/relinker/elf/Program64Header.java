package pl.droidsonroids.relinker.elf;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import pl.droidsonroids.relinker.elf.Elf;

public class Program64Header extends Elf.ProgramHeader {
    public Program64Header(ElfParser elfParser, Elf.Header header, long j) throws IOException {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(header.d ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j2 = header.f + (j * ((long) header.h));
        this.c = elfParser.c(allocate, j2);
        this.d = elfParser.b(allocate, 8 + j2);
        this.e = elfParser.b(allocate, 16 + j2);
        this.f = elfParser.b(allocate, j2 + 40);
    }
}
