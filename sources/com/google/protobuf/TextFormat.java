package com.google.protobuf;

import cn.com.fmsh.communication.core.MessageHead;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.alipay.sdk.util.i;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.Message;
import com.google.protobuf.MessageReflection;
import com.google.protobuf.UnknownFieldSet;
import com.mi.global.shop.model.Tags;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.fastvideo.IOUtils;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

public final class TextFormat {
    private static final Printer DEFAULT_PRINTER = new Printer();
    private static final Parser PARSER = Parser.newBuilder().build();
    private static final Printer SINGLE_LINE_PRINTER = new Printer().setSingleLineMode(true);
    private static final Printer UNICODE_PRINTER = new Printer().setEscapeNonAscii(false);
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(TextFormat.class.getName());

    private interface ByteSequence {
        byte byteAt(int i);

        int size();
    }

    private static int digitValue(byte b) {
        return (48 > b || b > 57) ? (97 > b || b > 122) ? b + Constants.TagName.STATION_ID + 10 : (b - 97) + 10 : b - 48;
    }

    private static boolean isHex(byte b) {
        return (48 <= b && b <= 57) || (97 <= b && b <= 102) || (65 <= b && b <= 70);
    }

    private static boolean isOctal(byte b) {
        return 48 <= b && b <= 55;
    }

    private TextFormat() {
    }

    public static void print(MessageOrBuilder messageOrBuilder, Appendable appendable) throws IOException {
        DEFAULT_PRINTER.print(messageOrBuilder, new TextGenerator(appendable));
    }

    public static void print(UnknownFieldSet unknownFieldSet, Appendable appendable) throws IOException {
        DEFAULT_PRINTER.printUnknownFields(unknownFieldSet, new TextGenerator(appendable));
    }

    public static void printUnicode(MessageOrBuilder messageOrBuilder, Appendable appendable) throws IOException {
        UNICODE_PRINTER.print(messageOrBuilder, new TextGenerator(appendable));
    }

    public static void printUnicode(UnknownFieldSet unknownFieldSet, Appendable appendable) throws IOException {
        UNICODE_PRINTER.printUnknownFields(unknownFieldSet, new TextGenerator(appendable));
    }

    public static String shortDebugString(MessageOrBuilder messageOrBuilder) {
        try {
            StringBuilder sb = new StringBuilder();
            SINGLE_LINE_PRINTER.print(messageOrBuilder, new TextGenerator(sb));
            return sb.toString().trim();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String shortDebugString(UnknownFieldSet unknownFieldSet) {
        try {
            StringBuilder sb = new StringBuilder();
            SINGLE_LINE_PRINTER.printUnknownFields(unknownFieldSet, new TextGenerator(sb));
            return sb.toString().trim();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String printToString(MessageOrBuilder messageOrBuilder) {
        try {
            StringBuilder sb = new StringBuilder();
            print(messageOrBuilder, (Appendable) sb);
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String printToString(UnknownFieldSet unknownFieldSet) {
        try {
            StringBuilder sb = new StringBuilder();
            print(unknownFieldSet, (Appendable) sb);
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String printToUnicodeString(MessageOrBuilder messageOrBuilder) {
        try {
            StringBuilder sb = new StringBuilder();
            UNICODE_PRINTER.print(messageOrBuilder, new TextGenerator(sb));
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String printToUnicodeString(UnknownFieldSet unknownFieldSet) {
        try {
            StringBuilder sb = new StringBuilder();
            UNICODE_PRINTER.printUnknownFields(unknownFieldSet, new TextGenerator(sb));
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void printField(Descriptors.FieldDescriptor fieldDescriptor, Object obj, Appendable appendable) throws IOException {
        DEFAULT_PRINTER.printField(fieldDescriptor, obj, new TextGenerator(appendable));
    }

    public static String printFieldToString(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
        try {
            StringBuilder sb = new StringBuilder();
            printField(fieldDescriptor, obj, sb);
            return sb.toString();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void printFieldValue(Descriptors.FieldDescriptor fieldDescriptor, Object obj, Appendable appendable) throws IOException {
        DEFAULT_PRINTER.printFieldValue(fieldDescriptor, obj, new TextGenerator(appendable));
    }

    public static void printUnknownFieldValue(int i, Object obj, Appendable appendable) throws IOException {
        printUnknownFieldValue(i, obj, new TextGenerator(appendable));
    }

    /* access modifiers changed from: private */
    public static void printUnknownFieldValue(int i, Object obj, TextGenerator textGenerator) throws IOException {
        int tagWireType = WireFormat.getTagWireType(i);
        if (tagWireType != 5) {
            switch (tagWireType) {
                case 0:
                    textGenerator.print(unsignedToString(((Long) obj).longValue()));
                    return;
                case 1:
                    textGenerator.print(String.format((Locale) null, "0x%016x", new Object[]{(Long) obj}));
                    return;
                case 2:
                    textGenerator.print("\"");
                    textGenerator.print(escapeBytes((ByteString) obj));
                    textGenerator.print("\"");
                    return;
                case 3:
                    DEFAULT_PRINTER.printUnknownFields((UnknownFieldSet) obj, textGenerator);
                    return;
                default:
                    StringBuilder sb = new StringBuilder(20);
                    sb.append("Bad tag: ");
                    sb.append(i);
                    throw new IllegalArgumentException(sb.toString());
            }
        } else {
            textGenerator.print(String.format((Locale) null, "0x%08x", new Object[]{(Integer) obj}));
        }
    }

    private static final class Printer {
        boolean escapeNonAscii;
        boolean singleLineMode;

        private Printer() {
            this.singleLineMode = false;
            this.escapeNonAscii = true;
        }

        /* access modifiers changed from: private */
        public Printer setSingleLineMode(boolean z) {
            this.singleLineMode = z;
            return this;
        }

        /* access modifiers changed from: private */
        public Printer setEscapeNonAscii(boolean z) {
            this.escapeNonAscii = z;
            return this;
        }

        /* access modifiers changed from: private */
        public void print(MessageOrBuilder messageOrBuilder, TextGenerator textGenerator) throws IOException {
            for (Map.Entry next : messageOrBuilder.getAllFields().entrySet()) {
                printField((Descriptors.FieldDescriptor) next.getKey(), next.getValue(), textGenerator);
            }
            printUnknownFields(messageOrBuilder.getUnknownFields(), textGenerator);
        }

        /* access modifiers changed from: private */
        public void printField(Descriptors.FieldDescriptor fieldDescriptor, Object obj, TextGenerator textGenerator) throws IOException {
            if (fieldDescriptor.isRepeated()) {
                for (Object printSingleField : (List) obj) {
                    printSingleField(fieldDescriptor, printSingleField, textGenerator);
                }
                return;
            }
            printSingleField(fieldDescriptor, obj, textGenerator);
        }

        private void printSingleField(Descriptors.FieldDescriptor fieldDescriptor, Object obj, TextGenerator textGenerator) throws IOException {
            if (fieldDescriptor.isExtension()) {
                textGenerator.print(Operators.ARRAY_START_STR);
                if (!fieldDescriptor.getContainingType().getOptions().getMessageSetWireFormat() || fieldDescriptor.getType() != Descriptors.FieldDescriptor.Type.MESSAGE || !fieldDescriptor.isOptional() || fieldDescriptor.getExtensionScope() != fieldDescriptor.getMessageType()) {
                    textGenerator.print(fieldDescriptor.getFullName());
                } else {
                    textGenerator.print(fieldDescriptor.getMessageType().getFullName());
                }
                textGenerator.print(Operators.ARRAY_END_STR);
            } else if (fieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.GROUP) {
                textGenerator.print(fieldDescriptor.getMessageType().getName());
            } else {
                textGenerator.print(fieldDescriptor.getName());
            }
            if (fieldDescriptor.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                textGenerator.print(": ");
            } else if (this.singleLineMode) {
                textGenerator.print(" { ");
            } else {
                textGenerator.print(" {\n");
                textGenerator.indent();
            }
            printFieldValue(fieldDescriptor, obj, textGenerator);
            if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                if (this.singleLineMode) {
                    textGenerator.print("} ");
                    return;
                }
                textGenerator.outdent();
                textGenerator.print("}\n");
            } else if (this.singleLineMode) {
                textGenerator.print(" ");
            } else {
                textGenerator.print("\n");
            }
        }

        /* access modifiers changed from: private */
        public void printFieldValue(Descriptors.FieldDescriptor fieldDescriptor, Object obj, TextGenerator textGenerator) throws IOException {
            switch (fieldDescriptor.getType()) {
                case INT32:
                case SINT32:
                case SFIXED32:
                    textGenerator.print(((Integer) obj).toString());
                    return;
                case INT64:
                case SINT64:
                case SFIXED64:
                    textGenerator.print(((Long) obj).toString());
                    return;
                case BOOL:
                    textGenerator.print(((Boolean) obj).toString());
                    return;
                case FLOAT:
                    textGenerator.print(((Float) obj).toString());
                    return;
                case DOUBLE:
                    textGenerator.print(((Double) obj).toString());
                    return;
                case UINT32:
                case FIXED32:
                    textGenerator.print(TextFormat.unsignedToString(((Integer) obj).intValue()));
                    return;
                case UINT64:
                case FIXED64:
                    textGenerator.print(TextFormat.unsignedToString(((Long) obj).longValue()));
                    return;
                case STRING:
                    textGenerator.print("\"");
                    textGenerator.print(this.escapeNonAscii ? TextFormat.escapeText((String) obj) : TextFormat.escapeDoubleQuotesAndBackslashes((String) obj));
                    textGenerator.print("\"");
                    return;
                case BYTES:
                    textGenerator.print("\"");
                    if (obj instanceof ByteString) {
                        textGenerator.print(TextFormat.escapeBytes((ByteString) obj));
                    } else {
                        textGenerator.print(TextFormat.escapeBytes((byte[]) obj));
                    }
                    textGenerator.print("\"");
                    return;
                case ENUM:
                    textGenerator.print(((Descriptors.EnumValueDescriptor) obj).getName());
                    return;
                case MESSAGE:
                case GROUP:
                    print((Message) obj, textGenerator);
                    return;
                default:
                    return;
            }
        }

        /* access modifiers changed from: private */
        public void printUnknownFields(UnknownFieldSet unknownFieldSet, TextGenerator textGenerator) throws IOException {
            for (Map.Entry next : unknownFieldSet.asMap().entrySet()) {
                int intValue = ((Integer) next.getKey()).intValue();
                UnknownFieldSet.Field field = (UnknownFieldSet.Field) next.getValue();
                printUnknownField(intValue, 0, field.getVarintList(), textGenerator);
                printUnknownField(intValue, 5, field.getFixed32List(), textGenerator);
                printUnknownField(intValue, 1, field.getFixed64List(), textGenerator);
                printUnknownField(intValue, 2, field.getLengthDelimitedList(), textGenerator);
                for (UnknownFieldSet next2 : field.getGroupList()) {
                    textGenerator.print(((Integer) next.getKey()).toString());
                    if (this.singleLineMode) {
                        textGenerator.print(" { ");
                    } else {
                        textGenerator.print(" {\n");
                        textGenerator.indent();
                    }
                    printUnknownFields(next2, textGenerator);
                    if (this.singleLineMode) {
                        textGenerator.print("} ");
                    } else {
                        textGenerator.outdent();
                        textGenerator.print("}\n");
                    }
                }
            }
        }

        private void printUnknownField(int i, int i2, List<?> list, TextGenerator textGenerator) throws IOException {
            for (Object next : list) {
                textGenerator.print(String.valueOf(i));
                textGenerator.print(": ");
                TextFormat.printUnknownFieldValue(i2, (Object) next, textGenerator);
                textGenerator.print(this.singleLineMode ? " " : "\n");
            }
        }
    }

    public static String unsignedToString(int i) {
        if (i >= 0) {
            return Integer.toString(i);
        }
        return Long.toString(((long) i) & MessageHead.SERIAL_MAK);
    }

    public static String unsignedToString(long j) {
        if (j >= 0) {
            return Long.toString(j);
        }
        return BigInteger.valueOf(j & Long.MAX_VALUE).setBit(63).toString();
    }

    private static final class TextGenerator {
        private boolean atStartOfLine;
        private final StringBuilder indent;
        private final Appendable output;

        private TextGenerator(Appendable appendable) {
            this.indent = new StringBuilder();
            this.atStartOfLine = true;
            this.output = appendable;
        }

        public void indent() {
            this.indent.append("  ");
        }

        public void outdent() {
            int length = this.indent.length();
            if (length != 0) {
                this.indent.delete(length - 2, length);
                return;
            }
            throw new IllegalArgumentException(" Outdent() without matching Indent().");
        }

        public void print(CharSequence charSequence) throws IOException {
            int length = charSequence.length();
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                if (charSequence.charAt(i2) == 10) {
                    int i3 = i2 + 1;
                    write(charSequence.subSequence(i, i3));
                    this.atStartOfLine = true;
                    i = i3;
                }
            }
            write(charSequence.subSequence(i, length));
        }

        private void write(CharSequence charSequence) throws IOException {
            if (charSequence.length() != 0) {
                if (this.atStartOfLine) {
                    this.atStartOfLine = false;
                    this.output.append(this.indent);
                }
                this.output.append(charSequence);
            }
        }
    }

    private static final class Tokenizer {
        private static final Pattern DOUBLE_INFINITY = Pattern.compile("-?inf(inity)?", 2);
        private static final Pattern FLOAT_INFINITY = Pattern.compile("-?inf(inity)?f?", 2);
        private static final Pattern FLOAT_NAN = Pattern.compile("nanf?", 2);
        private static final Pattern TOKEN = Pattern.compile("[a-zA-Z_][0-9a-zA-Z_+-]*+|[.]?[0-9+-][0-9a-zA-Z_.+-]*+|\"([^\"\n\\\\]|\\\\.)*+(\"|\\\\?$)|'([^'\n\\\\]|\\\\.)*+('|\\\\?$)", 8);
        private static final Pattern WHITESPACE = Pattern.compile("(\\s|(#.*$))++", 8);
        private int column;
        /* access modifiers changed from: private */
        public String currentToken;
        private int line;
        private final Matcher matcher;
        private int pos;
        private int previousColumn;
        private int previousLine;
        private final CharSequence text;

        private Tokenizer(CharSequence charSequence) {
            this.pos = 0;
            this.line = 0;
            this.column = 0;
            this.previousLine = 0;
            this.previousColumn = 0;
            this.text = charSequence;
            this.matcher = WHITESPACE.matcher(charSequence);
            skipWhitespace();
            nextToken();
        }

        public boolean atEnd() {
            return this.currentToken.length() == 0;
        }

        public void nextToken() {
            this.previousLine = this.line;
            this.previousColumn = this.column;
            while (this.pos < this.matcher.regionStart()) {
                if (this.text.charAt(this.pos) == 10) {
                    this.line++;
                    this.column = 0;
                } else {
                    this.column++;
                }
                this.pos++;
            }
            if (this.matcher.regionStart() == this.matcher.regionEnd()) {
                this.currentToken = "";
                return;
            }
            this.matcher.usePattern(TOKEN);
            if (this.matcher.lookingAt()) {
                this.currentToken = this.matcher.group();
                this.matcher.region(this.matcher.end(), this.matcher.regionEnd());
            } else {
                this.currentToken = String.valueOf(this.text.charAt(this.pos));
                this.matcher.region(this.pos + 1, this.matcher.regionEnd());
            }
            skipWhitespace();
        }

        private void skipWhitespace() {
            this.matcher.usePattern(WHITESPACE);
            if (this.matcher.lookingAt()) {
                this.matcher.region(this.matcher.end(), this.matcher.regionEnd());
            }
        }

        public boolean tryConsume(String str) {
            if (!this.currentToken.equals(str)) {
                return false;
            }
            nextToken();
            return true;
        }

        public void consume(String str) throws ParseException {
            if (!tryConsume(str)) {
                String valueOf = String.valueOf(String.valueOf(str));
                StringBuilder sb = new StringBuilder(valueOf.length() + 12);
                sb.append("Expected \"");
                sb.append(valueOf);
                sb.append("\".");
                throw parseException(sb.toString());
            }
        }

        public boolean lookingAtInteger() {
            if (this.currentToken.length() == 0) {
                return false;
            }
            char charAt = this.currentToken.charAt(0);
            if (('0' <= charAt && charAt <= '9') || charAt == '-' || charAt == '+') {
                return true;
            }
            return false;
        }

        public boolean lookingAt(String str) {
            return this.currentToken.equals(str);
        }

        public String consumeIdentifier() throws ParseException {
            for (int i = 0; i < this.currentToken.length(); i++) {
                char charAt = this.currentToken.charAt(i);
                if (('a' > charAt || charAt > 'z') && (('A' > charAt || charAt > 'Z') && !(('0' <= charAt && charAt <= '9') || charAt == '_' || charAt == '.'))) {
                    String valueOf = String.valueOf(String.valueOf(this.currentToken));
                    StringBuilder sb = new StringBuilder(valueOf.length() + 29);
                    sb.append("Expected identifier. Found '");
                    sb.append(valueOf);
                    sb.append("'");
                    throw parseException(sb.toString());
                }
            }
            String str = this.currentToken;
            nextToken();
            return str;
        }

        public boolean tryConsumeIdentifier() {
            try {
                consumeIdentifier();
                return true;
            } catch (ParseException unused) {
                return false;
            }
        }

        public int consumeInt32() throws ParseException {
            try {
                int parseInt32 = TextFormat.parseInt32(this.currentToken);
                nextToken();
                return parseInt32;
            } catch (NumberFormatException e) {
                throw integerParseException(e);
            }
        }

        public int consumeUInt32() throws ParseException {
            try {
                int parseUInt32 = TextFormat.parseUInt32(this.currentToken);
                nextToken();
                return parseUInt32;
            } catch (NumberFormatException e) {
                throw integerParseException(e);
            }
        }

        public long consumeInt64() throws ParseException {
            try {
                long parseInt64 = TextFormat.parseInt64(this.currentToken);
                nextToken();
                return parseInt64;
            } catch (NumberFormatException e) {
                throw integerParseException(e);
            }
        }

        public boolean tryConsumeInt64() {
            try {
                consumeInt64();
                return true;
            } catch (ParseException unused) {
                return false;
            }
        }

        public long consumeUInt64() throws ParseException {
            try {
                long parseUInt64 = TextFormat.parseUInt64(this.currentToken);
                nextToken();
                return parseUInt64;
            } catch (NumberFormatException e) {
                throw integerParseException(e);
            }
        }

        public boolean tryConsumeUInt64() {
            try {
                consumeUInt64();
                return true;
            } catch (ParseException unused) {
                return false;
            }
        }

        public double consumeDouble() throws ParseException {
            if (DOUBLE_INFINITY.matcher(this.currentToken).matches()) {
                boolean startsWith = this.currentToken.startsWith("-");
                nextToken();
                return startsWith ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
            } else if (this.currentToken.equalsIgnoreCase("nan")) {
                nextToken();
                return Double.NaN;
            } else {
                try {
                    double parseDouble = Double.parseDouble(this.currentToken);
                    nextToken();
                    return parseDouble;
                } catch (NumberFormatException e) {
                    throw floatParseException(e);
                }
            }
        }

        public boolean tryConsumeDouble() {
            try {
                consumeDouble();
                return true;
            } catch (ParseException unused) {
                return false;
            }
        }

        public float consumeFloat() throws ParseException {
            if (FLOAT_INFINITY.matcher(this.currentToken).matches()) {
                boolean startsWith = this.currentToken.startsWith("-");
                nextToken();
                return startsWith ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY;
            } else if (FLOAT_NAN.matcher(this.currentToken).matches()) {
                nextToken();
                return Float.NaN;
            } else {
                try {
                    float parseFloat = Float.parseFloat(this.currentToken);
                    nextToken();
                    return parseFloat;
                } catch (NumberFormatException e) {
                    throw floatParseException(e);
                }
            }
        }

        public boolean tryConsumeFloat() {
            try {
                consumeFloat();
                return true;
            } catch (ParseException unused) {
                return false;
            }
        }

        public boolean consumeBoolean() throws ParseException {
            if (this.currentToken.equals("true") || this.currentToken.equals("t") || this.currentToken.equals("1")) {
                nextToken();
                return true;
            } else if (this.currentToken.equals("false") || this.currentToken.equals("f") || this.currentToken.equals("0")) {
                nextToken();
                return false;
            } else {
                throw parseException("Expected \"true\" or \"false\".");
            }
        }

        public String consumeString() throws ParseException {
            return consumeByteString().toStringUtf8();
        }

        public boolean tryConsumeString() {
            try {
                consumeString();
                return true;
            } catch (ParseException unused) {
                return false;
            }
        }

        public ByteString consumeByteString() throws ParseException {
            ArrayList arrayList = new ArrayList();
            consumeByteString(arrayList);
            while (true) {
                if (!this.currentToken.startsWith("'") && !this.currentToken.startsWith("\"")) {
                    return ByteString.copyFrom((Iterable<ByteString>) arrayList);
                }
                consumeByteString(arrayList);
            }
        }

        private void consumeByteString(List<ByteString> list) throws ParseException {
            char c = 0;
            if (this.currentToken.length() > 0) {
                c = this.currentToken.charAt(0);
            }
            if (c != '\"' && c != '\'') {
                throw parseException("Expected string.");
            } else if (this.currentToken.length() < 2 || this.currentToken.charAt(this.currentToken.length() - 1) != c) {
                throw parseException("String missing ending quote.");
            } else {
                try {
                    ByteString unescapeBytes = TextFormat.unescapeBytes(this.currentToken.substring(1, this.currentToken.length() - 1));
                    nextToken();
                    list.add(unescapeBytes);
                } catch (InvalidEscapeSequenceException e) {
                    throw parseException(e.getMessage());
                }
            }
        }

        public ParseException parseException(String str) {
            return new ParseException(this.line + 1, this.column + 1, str);
        }

        public ParseException parseExceptionPreviousToken(String str) {
            return new ParseException(this.previousLine + 1, this.previousColumn + 1, str);
        }

        private ParseException integerParseException(NumberFormatException numberFormatException) {
            String valueOf = String.valueOf(numberFormatException.getMessage());
            return parseException(valueOf.length() != 0 ? "Couldn't parse integer: ".concat(valueOf) : new String("Couldn't parse integer: "));
        }

        private ParseException floatParseException(NumberFormatException numberFormatException) {
            String valueOf = String.valueOf(numberFormatException.getMessage());
            return parseException(valueOf.length() != 0 ? "Couldn't parse number: ".concat(valueOf) : new String("Couldn't parse number: "));
        }
    }

    public static class ParseException extends IOException {
        private static final long serialVersionUID = 3196188060225107702L;
        private final int column;
        private final int line;

        public ParseException(String str) {
            this(-1, -1, str);
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public ParseException(int r5, int r6, java.lang.String r7) {
            /*
                r4 = this;
                java.lang.String r0 = java.lang.Integer.toString(r5)
                java.lang.String r0 = java.lang.String.valueOf(r0)
                java.lang.String r0 = java.lang.String.valueOf(r0)
                java.lang.String r7 = java.lang.String.valueOf(r7)
                java.lang.String r7 = java.lang.String.valueOf(r7)
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                int r2 = r0.length()
                int r2 = r2 + 14
                int r3 = r7.length()
                int r2 = r2 + r3
                r1.<init>(r2)
                r1.append(r0)
                java.lang.String r0 = ":"
                r1.append(r0)
                r1.append(r6)
                java.lang.String r0 = ": "
                r1.append(r0)
                r1.append(r7)
                java.lang.String r7 = r1.toString()
                r4.<init>(r7)
                r4.line = r5
                r4.column = r6
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.TextFormat.ParseException.<init>(int, int, java.lang.String):void");
        }

        public int getLine() {
            return this.line;
        }

        public int getColumn() {
            return this.column;
        }
    }

    public static Parser getParser() {
        return PARSER;
    }

    public static void merge(Readable readable, Message.Builder builder) throws IOException {
        PARSER.merge(readable, builder);
    }

    public static void merge(CharSequence charSequence, Message.Builder builder) throws ParseException {
        PARSER.merge(charSequence, builder);
    }

    public static void merge(Readable readable, ExtensionRegistry extensionRegistry, Message.Builder builder) throws IOException {
        PARSER.merge(readable, extensionRegistry, builder);
    }

    public static void merge(CharSequence charSequence, ExtensionRegistry extensionRegistry, Message.Builder builder) throws ParseException {
        PARSER.merge(charSequence, extensionRegistry, builder);
    }

    public static class Parser {
        private static final int BUFFER_SIZE = 4096;
        private final boolean allowUnknownFields;
        private final SingularOverwritePolicy singularOverwritePolicy;

        public enum SingularOverwritePolicy {
            ALLOW_SINGULAR_OVERWRITES,
            FORBID_SINGULAR_OVERWRITES
        }

        private Parser(boolean z, SingularOverwritePolicy singularOverwritePolicy2) {
            this.allowUnknownFields = z;
            this.singularOverwritePolicy = singularOverwritePolicy2;
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public static class Builder {
            private boolean allowUnknownFields = false;
            private SingularOverwritePolicy singularOverwritePolicy = SingularOverwritePolicy.ALLOW_SINGULAR_OVERWRITES;

            public Builder setSingularOverwritePolicy(SingularOverwritePolicy singularOverwritePolicy2) {
                this.singularOverwritePolicy = singularOverwritePolicy2;
                return this;
            }

            public Parser build() {
                return new Parser(this.allowUnknownFields, this.singularOverwritePolicy);
            }
        }

        public void merge(Readable readable, Message.Builder builder) throws IOException {
            merge(readable, ExtensionRegistry.getEmptyRegistry(), builder);
        }

        public void merge(CharSequence charSequence, Message.Builder builder) throws ParseException {
            merge(charSequence, ExtensionRegistry.getEmptyRegistry(), builder);
        }

        public void merge(Readable readable, ExtensionRegistry extensionRegistry, Message.Builder builder) throws IOException {
            merge((CharSequence) toStringBuilder(readable), extensionRegistry, builder);
        }

        private static StringBuilder toStringBuilder(Readable readable) throws IOException {
            StringBuilder sb = new StringBuilder();
            CharBuffer allocate = CharBuffer.allocate(4096);
            while (true) {
                int read = readable.read(allocate);
                if (read == -1) {
                    return sb;
                }
                allocate.flip();
                sb.append(allocate, 0, read);
            }
        }

        public void merge(CharSequence charSequence, ExtensionRegistry extensionRegistry, Message.Builder builder) throws ParseException {
            Tokenizer tokenizer = new Tokenizer(charSequence);
            MessageReflection.BuilderAdapter builderAdapter = new MessageReflection.BuilderAdapter(builder);
            while (!tokenizer.atEnd()) {
                mergeField(tokenizer, extensionRegistry, builderAdapter);
            }
        }

        private void mergeField(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget mergeTarget) throws ParseException {
            ExtensionRegistry.ExtensionInfo extensionInfo;
            Descriptors.FieldDescriptor fieldDescriptor;
            Descriptors.Descriptor descriptorForType = mergeTarget.getDescriptorForType();
            Descriptors.FieldDescriptor fieldDescriptor2 = null;
            if (tokenizer.tryConsume(Operators.ARRAY_START_STR)) {
                StringBuilder sb = new StringBuilder(tokenizer.consumeIdentifier());
                while (tokenizer.tryConsume(".")) {
                    sb.append('.');
                    sb.append(tokenizer.consumeIdentifier());
                }
                ExtensionRegistry.ExtensionInfo findExtensionByName = mergeTarget.findExtensionByName(extensionRegistry, sb.toString());
                if (findExtensionByName == null) {
                    if (this.allowUnknownFields) {
                        Logger access$1100 = TextFormat.logger;
                        String valueOf = String.valueOf(String.valueOf(sb));
                        StringBuilder sb2 = new StringBuilder(valueOf.length() + 48);
                        sb2.append("Extension \"");
                        sb2.append(valueOf);
                        sb2.append("\" not found in the ExtensionRegistry.");
                        access$1100.warning(sb2.toString());
                    } else {
                        String valueOf2 = String.valueOf(String.valueOf(sb));
                        StringBuilder sb3 = new StringBuilder(valueOf2.length() + 48);
                        sb3.append("Extension \"");
                        sb3.append(valueOf2);
                        sb3.append("\" not found in the ExtensionRegistry.");
                        throw tokenizer.parseExceptionPreviousToken(sb3.toString());
                    }
                } else if (findExtensionByName.descriptor.getContainingType() == descriptorForType) {
                    fieldDescriptor2 = findExtensionByName.descriptor;
                } else {
                    String valueOf3 = String.valueOf(String.valueOf(sb));
                    String valueOf4 = String.valueOf(String.valueOf(descriptorForType.getFullName()));
                    StringBuilder sb4 = new StringBuilder(valueOf3.length() + 45 + valueOf4.length());
                    sb4.append("Extension \"");
                    sb4.append(valueOf3);
                    sb4.append("\" does not extend message type \"");
                    sb4.append(valueOf4);
                    sb4.append("\".");
                    throw tokenizer.parseExceptionPreviousToken(sb4.toString());
                }
                tokenizer.consume(Operators.ARRAY_END_STR);
                fieldDescriptor = fieldDescriptor2;
                extensionInfo = findExtensionByName;
            } else {
                String consumeIdentifier = tokenizer.consumeIdentifier();
                Descriptors.FieldDescriptor findFieldByName = descriptorForType.findFieldByName(consumeIdentifier);
                if (!(findFieldByName != null || (findFieldByName = descriptorForType.findFieldByName(consumeIdentifier.toLowerCase(Locale.US))) == null || findFieldByName.getType() == Descriptors.FieldDescriptor.Type.GROUP)) {
                    findFieldByName = null;
                }
                if (findFieldByName != null && findFieldByName.getType() == Descriptors.FieldDescriptor.Type.GROUP && !findFieldByName.getMessageType().getName().equals(consumeIdentifier)) {
                    findFieldByName = null;
                }
                if (findFieldByName == null) {
                    if (this.allowUnknownFields) {
                        Logger access$11002 = TextFormat.logger;
                        String valueOf5 = String.valueOf(String.valueOf(descriptorForType.getFullName()));
                        String valueOf6 = String.valueOf(String.valueOf(consumeIdentifier));
                        StringBuilder sb5 = new StringBuilder(valueOf5.length() + 38 + valueOf6.length());
                        sb5.append("Message type \"");
                        sb5.append(valueOf5);
                        sb5.append("\" has no field named \"");
                        sb5.append(valueOf6);
                        sb5.append("\".");
                        access$11002.warning(sb5.toString());
                    } else {
                        String valueOf7 = String.valueOf(String.valueOf(descriptorForType.getFullName()));
                        String valueOf8 = String.valueOf(String.valueOf(consumeIdentifier));
                        StringBuilder sb6 = new StringBuilder(valueOf7.length() + 38 + valueOf8.length());
                        sb6.append("Message type \"");
                        sb6.append(valueOf7);
                        sb6.append("\" has no field named \"");
                        sb6.append(valueOf8);
                        sb6.append("\".");
                        throw tokenizer.parseExceptionPreviousToken(sb6.toString());
                    }
                }
                extensionInfo = null;
                fieldDescriptor = findFieldByName;
            }
            if (fieldDescriptor != null) {
                if (fieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                    tokenizer.tryConsume(":");
                } else {
                    tokenizer.consume(":");
                }
                if (!fieldDescriptor.isRepeated() || !tokenizer.tryConsume(Operators.ARRAY_START_STR)) {
                    consumeFieldValue(tokenizer, extensionRegistry, mergeTarget, fieldDescriptor, extensionInfo);
                    return;
                }
                while (true) {
                    consumeFieldValue(tokenizer, extensionRegistry, mergeTarget, fieldDescriptor, extensionInfo);
                    if (!tokenizer.tryConsume(Operators.ARRAY_END_STR)) {
                        tokenizer.consume(",");
                    } else {
                        return;
                    }
                }
            } else if (!tokenizer.tryConsume(":") || tokenizer.lookingAt(Operators.BLOCK_START_STR) || tokenizer.lookingAt("<")) {
                skipFieldMessage(tokenizer);
            } else {
                skipFieldValue(tokenizer);
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: com.google.protobuf.Message} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: com.google.protobuf.Message} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: com.google.protobuf.Message} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v14, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v16, resolved type: com.google.protobuf.Message} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v17, resolved type: com.google.protobuf.Message} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v18, resolved type: com.google.protobuf.Message} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v19, resolved type: com.google.protobuf.Message} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v20, resolved type: com.google.protobuf.Message} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v21, resolved type: com.google.protobuf.Message} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v22, resolved type: com.google.protobuf.Message} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v23, resolved type: com.google.protobuf.Message} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v24, resolved type: com.google.protobuf.Message} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v25, resolved type: com.google.protobuf.Message} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v26, resolved type: com.google.protobuf.Message} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v27, resolved type: com.google.protobuf.Message} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v28, resolved type: com.google.protobuf.Message} */
        /* JADX WARNING: type inference failed for: r2v2, types: [java.lang.Integer] */
        /* JADX WARNING: type inference failed for: r2v3, types: [java.lang.Long] */
        /* JADX WARNING: type inference failed for: r2v4, types: [java.lang.Boolean] */
        /* JADX WARNING: type inference failed for: r2v5, types: [java.lang.Float] */
        /* JADX WARNING: type inference failed for: r2v6, types: [java.lang.Double] */
        /* JADX WARNING: type inference failed for: r2v7, types: [java.lang.Integer] */
        /* JADX WARNING: type inference failed for: r2v8, types: [java.lang.Long] */
        /* JADX WARNING: type inference failed for: r2v9, types: [java.lang.String] */
        /* JADX WARNING: type inference failed for: r2v10, types: [com.google.protobuf.ByteString] */
        /* JADX WARNING: type inference failed for: r2v11, types: [com.google.protobuf.Descriptors$EnumValueDescriptor] */
        /* JADX WARNING: type inference failed for: r2v12, types: [com.google.protobuf.Descriptors$EnumValueDescriptor] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Unknown variable types count: 2 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void consumeFieldValue(com.google.protobuf.TextFormat.Tokenizer r4, com.google.protobuf.ExtensionRegistry r5, com.google.protobuf.MessageReflection.MergeTarget r6, com.google.protobuf.Descriptors.FieldDescriptor r7, com.google.protobuf.ExtensionRegistry.ExtensionInfo r8) throws com.google.protobuf.TextFormat.ParseException {
            /*
                r3 = this;
                com.google.protobuf.Descriptors$FieldDescriptor$JavaType r0 = r7.getJavaType()
                com.google.protobuf.Descriptors$FieldDescriptor$JavaType r1 = com.google.protobuf.Descriptors.FieldDescriptor.JavaType.MESSAGE
                r2 = 0
                if (r0 != r1) goto L_0x0065
                java.lang.String r0 = "<"
                boolean r0 = r4.tryConsume(r0)
                if (r0 == 0) goto L_0x0014
                java.lang.String r0 = ">"
                goto L_0x001d
            L_0x0014:
                java.lang.String r0 = "{"
                r4.consume(r0)
                java.lang.String r0 = "}"
            L_0x001d:
                if (r8 != 0) goto L_0x0020
                goto L_0x0022
            L_0x0020:
                com.google.protobuf.Message r2 = r8.defaultInstance
            L_0x0022:
                com.google.protobuf.MessageReflection$MergeTarget r8 = r6.newMergeTargetForField(r7, r2)
            L_0x0026:
                boolean r1 = r4.tryConsume(r0)
                if (r1 != 0) goto L_0x005f
                boolean r1 = r4.atEnd()
                if (r1 != 0) goto L_0x0036
                r3.mergeField(r4, r5, r8)
                goto L_0x0026
            L_0x0036:
                java.lang.String r5 = java.lang.String.valueOf(r0)
                java.lang.String r5 = java.lang.String.valueOf(r5)
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                int r7 = r5.length()
                int r7 = r7 + 12
                r6.<init>(r7)
                java.lang.String r7 = "Expected \""
                r6.append(r7)
                r6.append(r5)
                java.lang.String r5 = "\"."
                r6.append(r5)
                java.lang.String r5 = r6.toString()
                com.google.protobuf.TextFormat$ParseException r4 = r4.parseException(r5)
                throw r4
            L_0x005f:
                java.lang.Object r2 = r8.finish()
                goto L_0x015f
            L_0x0065:
                int[] r5 = com.google.protobuf.TextFormat.AnonymousClass3.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type
                com.google.protobuf.Descriptors$FieldDescriptor$Type r8 = r7.getType()
                int r8 = r8.ordinal()
                r5 = r5[r8]
                switch(r5) {
                    case 1: goto L_0x0157;
                    case 2: goto L_0x0157;
                    case 3: goto L_0x0157;
                    case 4: goto L_0x014e;
                    case 5: goto L_0x014e;
                    case 6: goto L_0x014e;
                    case 7: goto L_0x0145;
                    case 8: goto L_0x013c;
                    case 9: goto L_0x0133;
                    case 10: goto L_0x012a;
                    case 11: goto L_0x012a;
                    case 12: goto L_0x0121;
                    case 13: goto L_0x0121;
                    case 14: goto L_0x011c;
                    case 15: goto L_0x0117;
                    case 16: goto L_0x007e;
                    case 17: goto L_0x0076;
                    case 18: goto L_0x0076;
                    default: goto L_0x0074;
                }
            L_0x0074:
                goto L_0x015f
            L_0x0076:
                java.lang.RuntimeException r4 = new java.lang.RuntimeException
                java.lang.String r5 = "Can't get here."
                r4.<init>(r5)
                throw r4
            L_0x007e:
                com.google.protobuf.Descriptors$EnumDescriptor r5 = r7.getEnumType()
                boolean r8 = r4.lookingAtInteger()
                if (r8 == 0) goto L_0x00c9
                int r8 = r4.consumeInt32()
                com.google.protobuf.Descriptors$EnumValueDescriptor r2 = r5.findValueByNumber((int) r8)
                if (r2 == 0) goto L_0x0094
                goto L_0x015f
            L_0x0094:
                java.lang.String r5 = r5.getFullName()
                java.lang.String r5 = java.lang.String.valueOf(r5)
                java.lang.String r5 = java.lang.String.valueOf(r5)
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                int r7 = r5.length()
                int r7 = r7 + 50
                r6.<init>(r7)
                java.lang.String r7 = "Enum type \""
                r6.append(r7)
                r6.append(r5)
                java.lang.String r5 = "\" has no value with number "
                r6.append(r5)
                r6.append(r8)
                java.lang.String r5 = "."
                r6.append(r5)
                java.lang.String r5 = r6.toString()
                com.google.protobuf.TextFormat$ParseException r4 = r4.parseExceptionPreviousToken(r5)
                throw r4
            L_0x00c9:
                java.lang.String r8 = r4.consumeIdentifier()
                com.google.protobuf.Descriptors$EnumValueDescriptor r2 = r5.findValueByName(r8)
                if (r2 == 0) goto L_0x00d5
                goto L_0x015f
            L_0x00d5:
                java.lang.String r5 = r5.getFullName()
                java.lang.String r5 = java.lang.String.valueOf(r5)
                java.lang.String r5 = java.lang.String.valueOf(r5)
                java.lang.String r6 = java.lang.String.valueOf(r8)
                java.lang.String r6 = java.lang.String.valueOf(r6)
                java.lang.StringBuilder r7 = new java.lang.StringBuilder
                int r8 = r5.length()
                int r8 = r8 + 35
                int r0 = r6.length()
                int r8 = r8 + r0
                r7.<init>(r8)
                java.lang.String r8 = "Enum type \""
                r7.append(r8)
                r7.append(r5)
                java.lang.String r5 = "\" has no value named \""
                r7.append(r5)
                r7.append(r6)
                java.lang.String r5 = "\"."
                r7.append(r5)
                java.lang.String r5 = r7.toString()
                com.google.protobuf.TextFormat$ParseException r4 = r4.parseExceptionPreviousToken(r5)
                throw r4
            L_0x0117:
                com.google.protobuf.ByteString r2 = r4.consumeByteString()
                goto L_0x015f
            L_0x011c:
                java.lang.String r2 = r4.consumeString()
                goto L_0x015f
            L_0x0121:
                long r0 = r4.consumeUInt64()
                java.lang.Long r2 = java.lang.Long.valueOf(r0)
                goto L_0x015f
            L_0x012a:
                int r5 = r4.consumeUInt32()
                java.lang.Integer r2 = java.lang.Integer.valueOf(r5)
                goto L_0x015f
            L_0x0133:
                double r0 = r4.consumeDouble()
                java.lang.Double r2 = java.lang.Double.valueOf(r0)
                goto L_0x015f
            L_0x013c:
                float r5 = r4.consumeFloat()
                java.lang.Float r2 = java.lang.Float.valueOf(r5)
                goto L_0x015f
            L_0x0145:
                boolean r5 = r4.consumeBoolean()
                java.lang.Boolean r2 = java.lang.Boolean.valueOf(r5)
                goto L_0x015f
            L_0x014e:
                long r0 = r4.consumeInt64()
                java.lang.Long r2 = java.lang.Long.valueOf(r0)
                goto L_0x015f
            L_0x0157:
                int r5 = r4.consumeInt32()
                java.lang.Integer r2 = java.lang.Integer.valueOf(r5)
            L_0x015f:
                boolean r5 = r7.isRepeated()
                if (r5 == 0) goto L_0x016a
                r6.addRepeatedField(r7, r2)
                goto L_0x0225
            L_0x016a:
                com.google.protobuf.TextFormat$Parser$SingularOverwritePolicy r5 = r3.singularOverwritePolicy
                com.google.protobuf.TextFormat$Parser$SingularOverwritePolicy r8 = com.google.protobuf.TextFormat.Parser.SingularOverwritePolicy.FORBID_SINGULAR_OVERWRITES
                if (r5 != r8) goto L_0x01a4
                boolean r5 = r6.hasField(r7)
                if (r5 != 0) goto L_0x0177
                goto L_0x01a4
            L_0x0177:
                java.lang.String r5 = r7.getFullName()
                java.lang.String r5 = java.lang.String.valueOf(r5)
                java.lang.String r5 = java.lang.String.valueOf(r5)
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                int r7 = r5.length()
                int r7 = r7 + 44
                r6.<init>(r7)
                java.lang.String r7 = "Non-repeated field \""
                r6.append(r7)
                r6.append(r5)
                java.lang.String r5 = "\" cannot be overwritten."
                r6.append(r5)
                java.lang.String r5 = r6.toString()
                com.google.protobuf.TextFormat$ParseException r4 = r4.parseExceptionPreviousToken(r5)
                throw r4
            L_0x01a4:
                com.google.protobuf.TextFormat$Parser$SingularOverwritePolicy r5 = r3.singularOverwritePolicy
                com.google.protobuf.TextFormat$Parser$SingularOverwritePolicy r8 = com.google.protobuf.TextFormat.Parser.SingularOverwritePolicy.FORBID_SINGULAR_OVERWRITES
                if (r5 != r8) goto L_0x0222
                com.google.protobuf.Descriptors$OneofDescriptor r5 = r7.getContainingOneof()
                if (r5 == 0) goto L_0x0222
                com.google.protobuf.Descriptors$OneofDescriptor r5 = r7.getContainingOneof()
                boolean r5 = r6.hasOneof(r5)
                if (r5 != 0) goto L_0x01bb
                goto L_0x0222
            L_0x01bb:
                com.google.protobuf.Descriptors$OneofDescriptor r5 = r7.getContainingOneof()
                java.lang.String r7 = r7.getFullName()
                java.lang.String r7 = java.lang.String.valueOf(r7)
                java.lang.String r7 = java.lang.String.valueOf(r7)
                com.google.protobuf.Descriptors$FieldDescriptor r6 = r6.getOneofFieldDescriptor(r5)
                java.lang.String r6 = r6.getFullName()
                java.lang.String r6 = java.lang.String.valueOf(r6)
                java.lang.String r6 = java.lang.String.valueOf(r6)
                java.lang.String r5 = r5.getName()
                java.lang.String r5 = java.lang.String.valueOf(r5)
                java.lang.String r5 = java.lang.String.valueOf(r5)
                java.lang.StringBuilder r8 = new java.lang.StringBuilder
                int r0 = r7.length()
                int r0 = r0 + 70
                int r1 = r6.length()
                int r0 = r0 + r1
                int r1 = r5.length()
                int r0 = r0 + r1
                r8.<init>(r0)
                java.lang.String r0 = "Field \""
                r8.append(r0)
                r8.append(r7)
                java.lang.String r7 = "\" is specified along with field \""
                r8.append(r7)
                r8.append(r6)
                java.lang.String r6 = "\", another member of oneof \""
                r8.append(r6)
                r8.append(r5)
                java.lang.String r5 = "\"."
                r8.append(r5)
                java.lang.String r5 = r8.toString()
                com.google.protobuf.TextFormat$ParseException r4 = r4.parseExceptionPreviousToken(r5)
                throw r4
            L_0x0222:
                r6.setField(r7, r2)
            L_0x0225:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.TextFormat.Parser.consumeFieldValue(com.google.protobuf.TextFormat$Tokenizer, com.google.protobuf.ExtensionRegistry, com.google.protobuf.MessageReflection$MergeTarget, com.google.protobuf.Descriptors$FieldDescriptor, com.google.protobuf.ExtensionRegistry$ExtensionInfo):void");
        }

        private void skipField(Tokenizer tokenizer) throws ParseException {
            if (tokenizer.tryConsume(Operators.ARRAY_START_STR)) {
                do {
                    tokenizer.consumeIdentifier();
                } while (tokenizer.tryConsume("."));
                tokenizer.consume(Operators.ARRAY_END_STR);
            } else {
                tokenizer.consumeIdentifier();
            }
            if (!tokenizer.tryConsume(":") || tokenizer.lookingAt("<") || tokenizer.lookingAt(Operators.BLOCK_START_STR)) {
                skipFieldMessage(tokenizer);
            } else {
                skipFieldValue(tokenizer);
            }
            if (!tokenizer.tryConsume(i.b)) {
                tokenizer.tryConsume(",");
            }
        }

        private void skipFieldMessage(Tokenizer tokenizer) throws ParseException {
            String str;
            if (tokenizer.tryConsume("<")) {
                str = ">";
            } else {
                tokenizer.consume(Operators.BLOCK_START_STR);
                str = "}";
            }
            while (!tokenizer.lookingAt(">") && !tokenizer.lookingAt("}")) {
                skipField(tokenizer);
            }
            tokenizer.consume(str);
        }

        private void skipFieldValue(Tokenizer tokenizer) throws ParseException {
            if (tokenizer.tryConsumeString()) {
                do {
                } while (tokenizer.tryConsumeString());
            } else if (!tokenizer.tryConsumeIdentifier() && !tokenizer.tryConsumeInt64() && !tokenizer.tryConsumeUInt64() && !tokenizer.tryConsumeDouble() && !tokenizer.tryConsumeFloat()) {
                String valueOf = String.valueOf(tokenizer.currentToken);
                throw tokenizer.parseException(valueOf.length() != 0 ? "Invalid field value: ".concat(valueOf) : new String("Invalid field value: "));
            }
        }
    }

    private static String escapeBytes(ByteSequence byteSequence) {
        StringBuilder sb = new StringBuilder(byteSequence.size());
        for (int i = 0; i < byteSequence.size(); i++) {
            byte byteAt = byteSequence.byteAt(i);
            if (byteAt == 34) {
                sb.append("\\\"");
            } else if (byteAt == 39) {
                sb.append("\\'");
            } else if (byteAt != 92) {
                switch (byteAt) {
                    case 7:
                        sb.append("\\a");
                        break;
                    case 8:
                        sb.append("\\b");
                        break;
                    case 9:
                        sb.append("\\t");
                        break;
                    case 10:
                        sb.append("\\n");
                        break;
                    case 11:
                        sb.append("\\v");
                        break;
                    case 12:
                        sb.append("\\f");
                        break;
                    case 13:
                        sb.append("\\r");
                        break;
                    default:
                        if (byteAt < 32) {
                            sb.append(IOUtils.b);
                            sb.append((char) (((byteAt >>> 6) & 3) + 48));
                            sb.append((char) (((byteAt >>> 3) & 7) + 48));
                            sb.append((char) ((byteAt & 7) + 48));
                            break;
                        } else {
                            sb.append((char) byteAt);
                            break;
                        }
                }
            } else {
                sb.append("\\\\");
            }
        }
        return sb.toString();
    }

    static String escapeBytes(final ByteString byteString) {
        return escapeBytes((ByteSequence) new ByteSequence() {
            public int size() {
                return byteString.size();
            }

            public byte byteAt(int i) {
                return byteString.byteAt(i);
            }
        });
    }

    static String escapeBytes(final byte[] bArr) {
        return escapeBytes((ByteSequence) new ByteSequence() {
            public int size() {
                return bArr.length;
            }

            public byte byteAt(int i) {
                return bArr[i];
            }
        });
    }

    static ByteString unescapeBytes(CharSequence charSequence) throws InvalidEscapeSequenceException {
        int i;
        int i2;
        int i3;
        ByteString copyFromUtf8 = ByteString.copyFromUtf8(charSequence.toString());
        byte[] bArr = new byte[copyFromUtf8.size()];
        int i4 = 0;
        int i5 = 0;
        while (i < copyFromUtf8.size()) {
            byte byteAt = copyFromUtf8.byteAt(i);
            if (byteAt == 92) {
                i++;
                if (i < copyFromUtf8.size()) {
                    byte byteAt2 = copyFromUtf8.byteAt(i);
                    if (isOctal(byteAt2)) {
                        int digitValue = digitValue(byteAt2);
                        int i6 = i + 1;
                        if (i6 < copyFromUtf8.size() && isOctal(copyFromUtf8.byteAt(i6))) {
                            digitValue = (digitValue * 8) + digitValue(copyFromUtf8.byteAt(i6));
                            i = i6;
                        }
                        int i7 = i + 1;
                        if (i7 < copyFromUtf8.size() && isOctal(copyFromUtf8.byteAt(i7))) {
                            digitValue = (digitValue * 8) + digitValue(copyFromUtf8.byteAt(i7));
                            i = i7;
                        }
                        i2 = i5 + 1;
                        bArr[i5] = (byte) digitValue;
                    } else {
                        if (byteAt2 == 34) {
                            i3 = i5 + 1;
                            bArr[i5] = 34;
                        } else if (byteAt2 == 39) {
                            i3 = i5 + 1;
                            bArr[i5] = 39;
                        } else if (byteAt2 == 92) {
                            i3 = i5 + 1;
                            bArr[i5] = Constants.TagName.ORDER_TRADE_STATUSES;
                        } else if (byteAt2 == 102) {
                            i3 = i5 + 1;
                            bArr[i5] = 12;
                        } else if (byteAt2 == 110) {
                            i3 = i5 + 1;
                            bArr[i5] = 10;
                        } else if (byteAt2 == 114) {
                            i3 = i5 + 1;
                            bArr[i5] = 13;
                        } else if (byteAt2 == 116) {
                            i3 = i5 + 1;
                            bArr[i5] = 9;
                        } else if (byteAt2 == 118) {
                            i3 = i5 + 1;
                            bArr[i5] = 11;
                        } else if (byteAt2 != 120) {
                            switch (byteAt2) {
                                case 97:
                                    i3 = i5 + 1;
                                    bArr[i5] = 7;
                                    break;
                                case 98:
                                    i3 = i5 + 1;
                                    bArr[i5] = 8;
                                    break;
                                default:
                                    StringBuilder sb = new StringBuilder(29);
                                    sb.append("Invalid escape sequence: '\\");
                                    sb.append((char) byteAt2);
                                    sb.append("'");
                                    throw new InvalidEscapeSequenceException(sb.toString());
                            }
                        } else {
                            i++;
                            if (i >= copyFromUtf8.size() || !isHex(copyFromUtf8.byteAt(i))) {
                                throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\x' with no digits");
                            }
                            int digitValue2 = digitValue(copyFromUtf8.byteAt(i));
                            int i8 = i + 1;
                            if (i8 < copyFromUtf8.size() && isHex(copyFromUtf8.byteAt(i8))) {
                                digitValue2 = (digitValue2 * 16) + digitValue(copyFromUtf8.byteAt(i8));
                                i = i8;
                            }
                            bArr[i5] = (byte) digitValue2;
                            i3 = i5 + 1;
                        }
                        i5 = i3;
                        i4 = i + 1;
                    }
                } else {
                    throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\' at end of string.");
                }
            } else {
                i2 = i5 + 1;
                bArr[i5] = byteAt;
            }
            i5 = i2;
            i4 = i + 1;
        }
        return ByteString.copyFrom(bArr, 0, i5);
    }

    static class InvalidEscapeSequenceException extends IOException {
        private static final long serialVersionUID = -8164033650142593304L;

        InvalidEscapeSequenceException(String str) {
            super(str);
        }
    }

    static String escapeText(String str) {
        return escapeBytes(ByteString.copyFromUtf8(str));
    }

    public static String escapeDoubleQuotesAndBackslashes(String str) {
        return str.replace(Tags.MiHome.TEL_SEPARATOR4, "\\\\").replace("\"", "\\\"");
    }

    static String unescapeText(String str) throws InvalidEscapeSequenceException {
        return unescapeBytes(str).toStringUtf8();
    }

    static int parseInt32(String str) throws NumberFormatException {
        return (int) parseInteger(str, true, false);
    }

    static int parseUInt32(String str) throws NumberFormatException {
        return (int) parseInteger(str, false, false);
    }

    static long parseInt64(String str) throws NumberFormatException {
        return parseInteger(str, true, true);
    }

    static long parseUInt64(String str) throws NumberFormatException {
        return parseInteger(str, false, true);
    }

    private static long parseInteger(String str, boolean z, boolean z2) throws NumberFormatException {
        int i = 0;
        boolean z3 = true;
        if (!str.startsWith("-", 0)) {
            z3 = false;
        } else if (!z) {
            String valueOf = String.valueOf(str);
            throw new NumberFormatException(valueOf.length() != 0 ? "Number must be positive: ".concat(valueOf) : new String("Number must be positive: "));
        } else {
            i = 1;
        }
        int i2 = 10;
        if (str.startsWith("0x", i)) {
            i += 2;
            i2 = 16;
        } else if (str.startsWith("0", i)) {
            i2 = 8;
        }
        String substring = str.substring(i);
        if (substring.length() < 16) {
            long parseLong = Long.parseLong(substring, i2);
            if (z3) {
                parseLong = -parseLong;
            }
            if (z2) {
                return parseLong;
            }
            if (z) {
                if (parseLong <= 2147483647L && parseLong >= -2147483648L) {
                    return parseLong;
                }
                String valueOf2 = String.valueOf(str);
                throw new NumberFormatException(valueOf2.length() != 0 ? "Number out of range for 32-bit signed integer: ".concat(valueOf2) : new String("Number out of range for 32-bit signed integer: "));
            } else if (parseLong < IjkMediaMeta.AV_CH_WIDE_RIGHT && parseLong >= 0) {
                return parseLong;
            } else {
                String valueOf3 = String.valueOf(str);
                throw new NumberFormatException(valueOf3.length() != 0 ? "Number out of range for 32-bit unsigned integer: ".concat(valueOf3) : new String("Number out of range for 32-bit unsigned integer: "));
            }
        } else {
            BigInteger bigInteger = new BigInteger(substring, i2);
            if (z3) {
                bigInteger = bigInteger.negate();
            }
            if (!z2) {
                if (z) {
                    if (bigInteger.bitLength() > 31) {
                        String valueOf4 = String.valueOf(str);
                        throw new NumberFormatException(valueOf4.length() != 0 ? "Number out of range for 32-bit signed integer: ".concat(valueOf4) : new String("Number out of range for 32-bit signed integer: "));
                    }
                } else if (bigInteger.bitLength() > 32) {
                    String valueOf5 = String.valueOf(str);
                    throw new NumberFormatException(valueOf5.length() != 0 ? "Number out of range for 32-bit unsigned integer: ".concat(valueOf5) : new String("Number out of range for 32-bit unsigned integer: "));
                }
            } else if (z) {
                if (bigInteger.bitLength() > 63) {
                    String valueOf6 = String.valueOf(str);
                    throw new NumberFormatException(valueOf6.length() != 0 ? "Number out of range for 64-bit signed integer: ".concat(valueOf6) : new String("Number out of range for 64-bit signed integer: "));
                }
            } else if (bigInteger.bitLength() > 64) {
                String valueOf7 = String.valueOf(str);
                throw new NumberFormatException(valueOf7.length() != 0 ? "Number out of range for 64-bit unsigned integer: ".concat(valueOf7) : new String("Number out of range for 64-bit unsigned integer: "));
            }
            return bigInteger.longValue();
        }
    }
}
