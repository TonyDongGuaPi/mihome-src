package org.jsoup.parser;

import com.taobao.weex.el.parse.Operators;
import com.xiaomi.jr.hybrid.WebEvent;
import com.xiaomi.smarthome.fastvideo.IOUtils;
import kotlin.text.Typography;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.lang.CharUtils;
import org.jsoup.nodes.DocumentType;
import org.jsoup.parser.Token;

enum TokeniserState {
    Data {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char c = characterReader.c();
            if (c == 0) {
                tokeniser.c((TokeniserState) this);
                tokeniser.a(characterReader.d());
            } else if (c == '&') {
                tokeniser.b(CharacterReferenceInData);
            } else if (c == '<') {
                tokeniser.b(TagOpen);
            } else if (c != 65535) {
                tokeniser.a(characterReader.i());
            } else {
                tokeniser.a((Token) new Token.EOF());
            }
        }
    },
    CharacterReferenceInData {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            TokeniserState.readCharRef(tokeniser, Data);
        }
    },
    Rcdata {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char c = characterReader.c();
            if (c == 0) {
                tokeniser.c((TokeniserState) this);
                characterReader.f();
                tokeniser.a((char) TokeniserState.replacementChar);
            } else if (c == '&') {
                tokeniser.b(CharacterReferenceInRcdata);
            } else if (c == '<') {
                tokeniser.b(RcdataLessthanSign);
            } else if (c != 65535) {
                tokeniser.a(characterReader.a(Typography.c, Typography.d, 0));
            } else {
                tokeniser.a((Token) new Token.EOF());
            }
        }
    },
    CharacterReferenceInRcdata {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            TokeniserState.readCharRef(tokeniser, Rcdata);
        }
    },
    Rawtext {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            TokeniserState.readData(tokeniser, characterReader, this, RawtextLessthanSign);
        }
    },
    ScriptData {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            TokeniserState.readData(tokeniser, characterReader, this, ScriptDataLessthanSign);
        }
    },
    PLAINTEXT {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char c = characterReader.c();
            if (c == 0) {
                tokeniser.c((TokeniserState) this);
                characterReader.f();
                tokeniser.a((char) TokeniserState.replacementChar);
            } else if (c != 65535) {
                tokeniser.a(characterReader.b(0));
            } else {
                tokeniser.a((Token) new Token.EOF());
            }
        }
    },
    TagOpen {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char c = characterReader.c();
            if (c == '!') {
                tokeniser.b(MarkupDeclarationOpen);
            } else if (c == '/') {
                tokeniser.b(EndTagOpen);
            } else if (c == '?') {
                tokeniser.b(BogusComment);
            } else if (characterReader.p()) {
                tokeniser.a(true);
                tokeniser.a(TagName);
            } else {
                tokeniser.c((TokeniserState) this);
                tokeniser.a((char) Typography.d);
                tokeniser.a(Data);
            }
        }
    },
    EndTagOpen {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.b()) {
                tokeniser.d(this);
                tokeniser.a("</");
                tokeniser.a(Data);
            } else if (characterReader.p()) {
                tokeniser.a(false);
                tokeniser.a(TagName);
            } else if (characterReader.c((char) Typography.e)) {
                tokeniser.c((TokeniserState) this);
                tokeniser.b(Data);
            } else {
                tokeniser.c((TokeniserState) this);
                tokeniser.b(BogusComment);
            }
        }
    },
    TagName {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            tokeniser.c.b(characterReader.j());
            char d = characterReader.d();
            switch (d) {
                case 0:
                    tokeniser.c.b(TokeniserState.replacementStr);
                    return;
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    tokeniser.a(BeforeAttributeName);
                    return;
                case '/':
                    tokeniser.a(SelfClosingStartTag);
                    return;
                case '>':
                    tokeniser.c();
                    tokeniser.a(Data);
                    return;
                case 65535:
                    tokeniser.d(this);
                    tokeniser.a(Data);
                    return;
                default:
                    tokeniser.c.a(d);
                    return;
            }
        }
    },
    RcdataLessthanSign {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.c((char) IOUtils.f15883a)) {
                tokeniser.h();
                tokeniser.b(RCDATAEndTagOpen);
                return;
            }
            if (characterReader.p() && tokeniser.j() != null) {
                if (!characterReader.f("</" + tokeniser.j())) {
                    tokeniser.c = tokeniser.a(false).a(tokeniser.j());
                    tokeniser.c();
                    characterReader.e();
                    tokeniser.a(Data);
                    return;
                }
            }
            tokeniser.a("<");
            tokeniser.a(Rcdata);
        }
    },
    RCDATAEndTagOpen {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.p()) {
                tokeniser.a(false);
                tokeniser.c.a(characterReader.c());
                tokeniser.b.append(characterReader.c());
                tokeniser.b(RCDATAEndTagName);
                return;
            }
            tokeniser.a("</");
            tokeniser.a(Rcdata);
        }
    },
    RCDATAEndTagName {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.p()) {
                String l = characterReader.l();
                tokeniser.c.b(l);
                tokeniser.b.append(l);
                return;
            }
            switch (characterReader.d()) {
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    if (tokeniser.i()) {
                        tokeniser.a(BeforeAttributeName);
                        return;
                    } else {
                        anythingElse(tokeniser, characterReader);
                        return;
                    }
                case '/':
                    if (tokeniser.i()) {
                        tokeniser.a(SelfClosingStartTag);
                        return;
                    } else {
                        anythingElse(tokeniser, characterReader);
                        return;
                    }
                case '>':
                    if (tokeniser.i()) {
                        tokeniser.c();
                        tokeniser.a(Data);
                        return;
                    }
                    anythingElse(tokeniser, characterReader);
                    return;
                default:
                    anythingElse(tokeniser, characterReader);
                    return;
            }
        }

        private void anythingElse(Tokeniser tokeniser, CharacterReader characterReader) {
            tokeniser.a("</" + tokeniser.b.toString());
            characterReader.e();
            tokeniser.a(Rcdata);
        }
    },
    RawtextLessthanSign {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.c((char) IOUtils.f15883a)) {
                tokeniser.h();
                tokeniser.b(RawtextEndTagOpen);
                return;
            }
            tokeniser.a((char) Typography.d);
            tokeniser.a(Rawtext);
        }
    },
    RawtextEndTagOpen {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            TokeniserState.readEndTag(tokeniser, characterReader, RawtextEndTagName, Rawtext);
        }
    },
    RawtextEndTagName {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            TokeniserState.handleDataEndTag(tokeniser, characterReader, Rawtext);
        }
    },
    ScriptDataLessthanSign {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char d = characterReader.d();
            if (d == '!') {
                tokeniser.a("<!");
                tokeniser.a(ScriptDataEscapeStart);
            } else if (d != '/') {
                tokeniser.a("<");
                characterReader.e();
                tokeniser.a(ScriptData);
            } else {
                tokeniser.h();
                tokeniser.a(ScriptDataEndTagOpen);
            }
        }
    },
    ScriptDataEndTagOpen {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            TokeniserState.readEndTag(tokeniser, characterReader, ScriptDataEndTagName, ScriptData);
        }
    },
    ScriptDataEndTagName {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            TokeniserState.handleDataEndTag(tokeniser, characterReader, ScriptData);
        }
    },
    ScriptDataEscapeStart {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.c('-')) {
                tokeniser.a('-');
                tokeniser.b(ScriptDataEscapeStartDash);
                return;
            }
            tokeniser.a(ScriptData);
        }
    },
    ScriptDataEscapeStartDash {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.c('-')) {
                tokeniser.a('-');
                tokeniser.b(ScriptDataEscapedDashDash);
                return;
            }
            tokeniser.a(ScriptData);
        }
    },
    ScriptDataEscaped {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.b()) {
                tokeniser.d(this);
                tokeniser.a(Data);
                return;
            }
            char c = characterReader.c();
            if (c == 0) {
                tokeniser.c((TokeniserState) this);
                characterReader.f();
                tokeniser.a((char) TokeniserState.replacementChar);
            } else if (c == '-') {
                tokeniser.a('-');
                tokeniser.b(ScriptDataEscapedDash);
            } else if (c != '<') {
                tokeniser.a(characterReader.a('-', Typography.d, 0));
            } else {
                tokeniser.b(ScriptDataEscapedLessthanSign);
            }
        }
    },
    ScriptDataEscapedDash {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.b()) {
                tokeniser.d(this);
                tokeniser.a(Data);
                return;
            }
            char d = characterReader.d();
            if (d == 0) {
                tokeniser.c((TokeniserState) this);
                tokeniser.a((char) TokeniserState.replacementChar);
                tokeniser.a(ScriptDataEscaped);
            } else if (d == '-') {
                tokeniser.a(d);
                tokeniser.a(ScriptDataEscapedDashDash);
            } else if (d != '<') {
                tokeniser.a(d);
                tokeniser.a(ScriptDataEscaped);
            } else {
                tokeniser.a(ScriptDataEscapedLessthanSign);
            }
        }
    },
    ScriptDataEscapedDashDash {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.b()) {
                tokeniser.d(this);
                tokeniser.a(Data);
                return;
            }
            char d = characterReader.d();
            if (d == 0) {
                tokeniser.c((TokeniserState) this);
                tokeniser.a((char) TokeniserState.replacementChar);
                tokeniser.a(ScriptDataEscaped);
            } else if (d == '-') {
                tokeniser.a(d);
            } else if (d == '<') {
                tokeniser.a(ScriptDataEscapedLessthanSign);
            } else if (d != '>') {
                tokeniser.a(d);
                tokeniser.a(ScriptDataEscaped);
            } else {
                tokeniser.a(d);
                tokeniser.a(ScriptData);
            }
        }
    },
    ScriptDataEscapedLessthanSign {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.p()) {
                tokeniser.h();
                tokeniser.b.append(characterReader.c());
                tokeniser.a("<" + characterReader.c());
                tokeniser.b(ScriptDataDoubleEscapeStart);
            } else if (characterReader.c((char) IOUtils.f15883a)) {
                tokeniser.h();
                tokeniser.b(ScriptDataEscapedEndTagOpen);
            } else {
                tokeniser.a((char) Typography.d);
                tokeniser.a(ScriptDataEscaped);
            }
        }
    },
    ScriptDataEscapedEndTagOpen {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.p()) {
                tokeniser.a(false);
                tokeniser.c.a(characterReader.c());
                tokeniser.b.append(characterReader.c());
                tokeniser.b(ScriptDataEscapedEndTagName);
                return;
            }
            tokeniser.a("</");
            tokeniser.a(ScriptDataEscaped);
        }
    },
    ScriptDataEscapedEndTagName {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            TokeniserState.handleDataEndTag(tokeniser, characterReader, ScriptDataEscaped);
        }
    },
    ScriptDataDoubleEscapeStart {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            TokeniserState.handleDataDoubleEscapeTag(tokeniser, characterReader, ScriptDataDoubleEscaped, ScriptDataEscaped);
        }
    },
    ScriptDataDoubleEscaped {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char c = characterReader.c();
            if (c == 0) {
                tokeniser.c((TokeniserState) this);
                characterReader.f();
                tokeniser.a((char) TokeniserState.replacementChar);
            } else if (c == '-') {
                tokeniser.a(c);
                tokeniser.b(ScriptDataDoubleEscapedDash);
            } else if (c == '<') {
                tokeniser.a(c);
                tokeniser.b(ScriptDataDoubleEscapedLessthanSign);
            } else if (c != 65535) {
                tokeniser.a(characterReader.a('-', Typography.d, 0));
            } else {
                tokeniser.d(this);
                tokeniser.a(Data);
            }
        }
    },
    ScriptDataDoubleEscapedDash {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char d = characterReader.d();
            if (d == 0) {
                tokeniser.c((TokeniserState) this);
                tokeniser.a((char) TokeniserState.replacementChar);
                tokeniser.a(ScriptDataDoubleEscaped);
            } else if (d == '-') {
                tokeniser.a(d);
                tokeniser.a(ScriptDataDoubleEscapedDashDash);
            } else if (d == '<') {
                tokeniser.a(d);
                tokeniser.a(ScriptDataDoubleEscapedLessthanSign);
            } else if (d != 65535) {
                tokeniser.a(d);
                tokeniser.a(ScriptDataDoubleEscaped);
            } else {
                tokeniser.d(this);
                tokeniser.a(Data);
            }
        }
    },
    ScriptDataDoubleEscapedDashDash {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char d = characterReader.d();
            if (d == 0) {
                tokeniser.c((TokeniserState) this);
                tokeniser.a((char) TokeniserState.replacementChar);
                tokeniser.a(ScriptDataDoubleEscaped);
            } else if (d == '-') {
                tokeniser.a(d);
            } else if (d == '<') {
                tokeniser.a(d);
                tokeniser.a(ScriptDataDoubleEscapedLessthanSign);
            } else if (d == '>') {
                tokeniser.a(d);
                tokeniser.a(ScriptData);
            } else if (d != 65535) {
                tokeniser.a(d);
                tokeniser.a(ScriptDataDoubleEscaped);
            } else {
                tokeniser.d(this);
                tokeniser.a(Data);
            }
        }
    },
    ScriptDataDoubleEscapedLessthanSign {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.c((char) IOUtils.f15883a)) {
                tokeniser.a((char) IOUtils.f15883a);
                tokeniser.h();
                tokeniser.b(ScriptDataDoubleEscapeEnd);
                return;
            }
            tokeniser.a(ScriptDataDoubleEscaped);
        }
    },
    ScriptDataDoubleEscapeEnd {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            TokeniserState.handleDataDoubleEscapeTag(tokeniser, characterReader, ScriptDataEscaped, ScriptDataDoubleEscaped);
        }
    },
    BeforeAttributeName {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char d = characterReader.d();
            switch (d) {
                case 0:
                    tokeniser.c((TokeniserState) this);
                    tokeniser.c.o();
                    characterReader.e();
                    tokeniser.a(AttributeName);
                    return;
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    return;
                case '\"':
                case '\'':
                case '<':
                case '=':
                    tokeniser.c((TokeniserState) this);
                    tokeniser.c.o();
                    tokeniser.c.b(d);
                    tokeniser.a(AttributeName);
                    return;
                case '/':
                    tokeniser.a(SelfClosingStartTag);
                    return;
                case '>':
                    tokeniser.c();
                    tokeniser.a(Data);
                    return;
                case 65535:
                    tokeniser.d(this);
                    tokeniser.a(Data);
                    return;
                default:
                    tokeniser.c.o();
                    characterReader.e();
                    tokeniser.a(AttributeName);
                    return;
            }
        }
    },
    AttributeName {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            tokeniser.c.c(characterReader.b(attributeNameCharsSorted));
            char d = characterReader.d();
            switch (d) {
                case 0:
                    tokeniser.c((TokeniserState) this);
                    tokeniser.c.b((char) TokeniserState.replacementChar);
                    return;
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    tokeniser.a(AfterAttributeName);
                    return;
                case '\"':
                case '\'':
                case '<':
                    tokeniser.c((TokeniserState) this);
                    tokeniser.c.b(d);
                    return;
                case '/':
                    tokeniser.a(SelfClosingStartTag);
                    return;
                case '=':
                    tokeniser.a(BeforeAttributeValue);
                    return;
                case '>':
                    tokeniser.c();
                    tokeniser.a(Data);
                    return;
                case 65535:
                    tokeniser.d(this);
                    tokeniser.a(Data);
                    return;
                default:
                    tokeniser.c.b(d);
                    return;
            }
        }
    },
    AfterAttributeName {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char d = characterReader.d();
            switch (d) {
                case 0:
                    tokeniser.c((TokeniserState) this);
                    tokeniser.c.b((char) TokeniserState.replacementChar);
                    tokeniser.a(AttributeName);
                    return;
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    return;
                case '\"':
                case '\'':
                case '<':
                    tokeniser.c((TokeniserState) this);
                    tokeniser.c.o();
                    tokeniser.c.b(d);
                    tokeniser.a(AttributeName);
                    return;
                case '/':
                    tokeniser.a(SelfClosingStartTag);
                    return;
                case '=':
                    tokeniser.a(BeforeAttributeValue);
                    return;
                case '>':
                    tokeniser.c();
                    tokeniser.a(Data);
                    return;
                case 65535:
                    tokeniser.d(this);
                    tokeniser.a(Data);
                    return;
                default:
                    tokeniser.c.o();
                    characterReader.e();
                    tokeniser.a(AttributeName);
                    return;
            }
        }
    },
    BeforeAttributeValue {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char d = characterReader.d();
            switch (d) {
                case 0:
                    tokeniser.c((TokeniserState) this);
                    tokeniser.c.c((char) TokeniserState.replacementChar);
                    tokeniser.a(AttributeValue_unquoted);
                    return;
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    return;
                case '\"':
                    tokeniser.a(AttributeValue_doubleQuoted);
                    return;
                case '&':
                    characterReader.e();
                    tokeniser.a(AttributeValue_unquoted);
                    return;
                case '\'':
                    tokeniser.a(AttributeValue_singleQuoted);
                    return;
                case '<':
                case '=':
                case '`':
                    tokeniser.c((TokeniserState) this);
                    tokeniser.c.c(d);
                    tokeniser.a(AttributeValue_unquoted);
                    return;
                case '>':
                    tokeniser.c((TokeniserState) this);
                    tokeniser.c();
                    tokeniser.a(Data);
                    return;
                case 65535:
                    tokeniser.d(this);
                    tokeniser.c();
                    tokeniser.a(Data);
                    return;
                default:
                    characterReader.e();
                    tokeniser.a(AttributeValue_unquoted);
                    return;
            }
        }
    },
    AttributeValue_doubleQuoted {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            String a2 = characterReader.a(attributeDoubleValueCharsSorted);
            if (a2.length() > 0) {
                tokeniser.c.d(a2);
            } else {
                tokeniser.c.u();
            }
            char d = characterReader.d();
            if (d == 0) {
                tokeniser.c((TokeniserState) this);
                tokeniser.c.c((char) TokeniserState.replacementChar);
            } else if (d == '\"') {
                tokeniser.a(AfterAttributeValue_quoted);
            } else if (d == '&') {
                int[] a3 = tokeniser.a('\"', true);
                if (a3 != null) {
                    tokeniser.c.a(a3);
                } else {
                    tokeniser.c.c((char) Typography.c);
                }
            } else if (d != 65535) {
                tokeniser.c.c(d);
            } else {
                tokeniser.d(this);
                tokeniser.a(Data);
            }
        }
    },
    AttributeValue_singleQuoted {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            String a2 = characterReader.a(attributeSingleValueCharsSorted);
            if (a2.length() > 0) {
                tokeniser.c.d(a2);
            } else {
                tokeniser.c.u();
            }
            char d = characterReader.d();
            if (d == 0) {
                tokeniser.c((TokeniserState) this);
                tokeniser.c.c((char) TokeniserState.replacementChar);
            } else if (d != 65535) {
                switch (d) {
                    case '&':
                        int[] a3 = tokeniser.a(Character.valueOf(Operators.SINGLE_QUOTE), true);
                        if (a3 != null) {
                            tokeniser.c.a(a3);
                            return;
                        } else {
                            tokeniser.c.c((char) Typography.c);
                            return;
                        }
                    case '\'':
                        tokeniser.a(AfterAttributeValue_quoted);
                        return;
                    default:
                        tokeniser.c.c(d);
                        return;
                }
            } else {
                tokeniser.d(this);
                tokeniser.a(Data);
            }
        }
    },
    AttributeValue_unquoted {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            String b = characterReader.b(attributeValueUnquoted);
            if (b.length() > 0) {
                tokeniser.c.d(b);
            }
            char d = characterReader.d();
            switch (d) {
                case 0:
                    tokeniser.c((TokeniserState) this);
                    tokeniser.c.c((char) TokeniserState.replacementChar);
                    return;
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    tokeniser.a(BeforeAttributeName);
                    return;
                case '\"':
                case '\'':
                case '<':
                case '=':
                case '`':
                    tokeniser.c((TokeniserState) this);
                    tokeniser.c.c(d);
                    return;
                case '&':
                    int[] a2 = tokeniser.a(Character.valueOf(Typography.e), true);
                    if (a2 != null) {
                        tokeniser.c.a(a2);
                        return;
                    } else {
                        tokeniser.c.c((char) Typography.c);
                        return;
                    }
                case '>':
                    tokeniser.c();
                    tokeniser.a(Data);
                    return;
                case 65535:
                    tokeniser.d(this);
                    tokeniser.a(Data);
                    return;
                default:
                    tokeniser.c.c(d);
                    return;
            }
        }
    },
    AfterAttributeValue_quoted {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.d()) {
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    tokeniser.a(BeforeAttributeName);
                    return;
                case '/':
                    tokeniser.a(SelfClosingStartTag);
                    return;
                case '>':
                    tokeniser.c();
                    tokeniser.a(Data);
                    return;
                case 65535:
                    tokeniser.d(this);
                    tokeniser.a(Data);
                    return;
                default:
                    tokeniser.c((TokeniserState) this);
                    characterReader.e();
                    tokeniser.a(BeforeAttributeName);
                    return;
            }
        }
    },
    SelfClosingStartTag {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char d = characterReader.d();
            if (d == '>') {
                tokeniser.c.d = true;
                tokeniser.c();
                tokeniser.a(Data);
            } else if (d != 65535) {
                tokeniser.c((TokeniserState) this);
                characterReader.e();
                tokeniser.a(BeforeAttributeName);
            } else {
                tokeniser.d(this);
                tokeniser.a(Data);
            }
        }
    },
    BogusComment {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            characterReader.e();
            Token.Comment comment = new Token.Comment();
            comment.c = true;
            comment.b.append(characterReader.b((char) Typography.e));
            tokeniser.a((Token) comment);
            tokeniser.b(Data);
        }
    },
    MarkupDeclarationOpen {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.d(HelpFormatter.f)) {
                tokeniser.d();
                tokeniser.a(CommentStart);
            } else if (characterReader.e("DOCTYPE")) {
                tokeniser.a(Doctype);
            } else if (characterReader.d("[CDATA[")) {
                tokeniser.a(CdataSection);
            } else {
                tokeniser.c((TokeniserState) this);
                tokeniser.b(BogusComment);
            }
        }
    },
    CommentStart {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char d = characterReader.d();
            if (d == 0) {
                tokeniser.c((TokeniserState) this);
                tokeniser.h.b.append(TokeniserState.replacementChar);
                tokeniser.a(Comment);
            } else if (d == '-') {
                tokeniser.a(CommentStartDash);
            } else if (d == '>') {
                tokeniser.c((TokeniserState) this);
                tokeniser.e();
                tokeniser.a(Data);
            } else if (d != 65535) {
                tokeniser.h.b.append(d);
                tokeniser.a(Comment);
            } else {
                tokeniser.d(this);
                tokeniser.e();
                tokeniser.a(Data);
            }
        }
    },
    CommentStartDash {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char d = characterReader.d();
            if (d == 0) {
                tokeniser.c((TokeniserState) this);
                tokeniser.h.b.append(TokeniserState.replacementChar);
                tokeniser.a(Comment);
            } else if (d == '-') {
                tokeniser.a(CommentStartDash);
            } else if (d == '>') {
                tokeniser.c((TokeniserState) this);
                tokeniser.e();
                tokeniser.a(Data);
            } else if (d != 65535) {
                tokeniser.h.b.append(d);
                tokeniser.a(Comment);
            } else {
                tokeniser.d(this);
                tokeniser.e();
                tokeniser.a(Data);
            }
        }
    },
    Comment {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char c = characterReader.c();
            if (c == 0) {
                tokeniser.c((TokeniserState) this);
                characterReader.f();
                tokeniser.h.b.append(TokeniserState.replacementChar);
            } else if (c == '-') {
                tokeniser.b(CommentEndDash);
            } else if (c != 65535) {
                tokeniser.h.b.append(characterReader.a('-', 0));
            } else {
                tokeniser.d(this);
                tokeniser.e();
                tokeniser.a(Data);
            }
        }
    },
    CommentEndDash {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char d = characterReader.d();
            if (d == 0) {
                tokeniser.c((TokeniserState) this);
                StringBuilder sb = tokeniser.h.b;
                sb.append('-');
                sb.append(TokeniserState.replacementChar);
                tokeniser.a(Comment);
            } else if (d == '-') {
                tokeniser.a(CommentEnd);
            } else if (d != 65535) {
                StringBuilder sb2 = tokeniser.h.b;
                sb2.append('-');
                sb2.append(d);
                tokeniser.a(Comment);
            } else {
                tokeniser.d(this);
                tokeniser.e();
                tokeniser.a(Data);
            }
        }
    },
    CommentEnd {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char d = characterReader.d();
            if (d == 0) {
                tokeniser.c((TokeniserState) this);
                StringBuilder sb = tokeniser.h.b;
                sb.append(HelpFormatter.f);
                sb.append(TokeniserState.replacementChar);
                tokeniser.a(Comment);
            } else if (d == '!') {
                tokeniser.c((TokeniserState) this);
                tokeniser.a(CommentEndBang);
            } else if (d == '-') {
                tokeniser.c((TokeniserState) this);
                tokeniser.h.b.append('-');
            } else if (d == '>') {
                tokeniser.e();
                tokeniser.a(Data);
            } else if (d != 65535) {
                tokeniser.c((TokeniserState) this);
                StringBuilder sb2 = tokeniser.h.b;
                sb2.append(HelpFormatter.f);
                sb2.append(d);
                tokeniser.a(Comment);
            } else {
                tokeniser.d(this);
                tokeniser.e();
                tokeniser.a(Data);
            }
        }
    },
    CommentEndBang {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char d = characterReader.d();
            if (d == 0) {
                tokeniser.c((TokeniserState) this);
                StringBuilder sb = tokeniser.h.b;
                sb.append("--!");
                sb.append(TokeniserState.replacementChar);
                tokeniser.a(Comment);
            } else if (d == '-') {
                tokeniser.h.b.append("--!");
                tokeniser.a(CommentEndDash);
            } else if (d == '>') {
                tokeniser.e();
                tokeniser.a(Data);
            } else if (d != 65535) {
                StringBuilder sb2 = tokeniser.h.b;
                sb2.append("--!");
                sb2.append(d);
                tokeniser.a(Comment);
            } else {
                tokeniser.d(this);
                tokeniser.e();
                tokeniser.a(Data);
            }
        }
    },
    Doctype {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.d()) {
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    tokeniser.a(BeforeDoctypeName);
                    return;
                case '>':
                    break;
                case 65535:
                    tokeniser.d(this);
                    break;
                default:
                    tokeniser.c((TokeniserState) this);
                    tokeniser.a(BeforeDoctypeName);
                    return;
            }
            tokeniser.c((TokeniserState) this);
            tokeniser.f();
            tokeniser.g.f = true;
            tokeniser.g();
            tokeniser.a(Data);
        }
    },
    BeforeDoctypeName {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.p()) {
                tokeniser.f();
                tokeniser.a(DoctypeName);
                return;
            }
            char d = characterReader.d();
            switch (d) {
                case 0:
                    tokeniser.c((TokeniserState) this);
                    tokeniser.f();
                    tokeniser.g.b.append(TokeniserState.replacementChar);
                    tokeniser.a(DoctypeName);
                    return;
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    return;
                case 65535:
                    tokeniser.d(this);
                    tokeniser.f();
                    tokeniser.g.f = true;
                    tokeniser.g();
                    tokeniser.a(Data);
                    return;
                default:
                    tokeniser.f();
                    tokeniser.g.b.append(d);
                    tokeniser.a(DoctypeName);
                    return;
            }
        }
    },
    DoctypeName {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.p()) {
                tokeniser.g.b.append(characterReader.l());
                return;
            }
            char d = characterReader.d();
            switch (d) {
                case 0:
                    tokeniser.c((TokeniserState) this);
                    tokeniser.g.b.append(TokeniserState.replacementChar);
                    return;
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    tokeniser.a(AfterDoctypeName);
                    return;
                case '>':
                    tokeniser.g();
                    tokeniser.a(Data);
                    return;
                case 65535:
                    tokeniser.d(this);
                    tokeniser.g.f = true;
                    tokeniser.g();
                    tokeniser.a(Data);
                    return;
                default:
                    tokeniser.g.b.append(d);
                    return;
            }
        }
    },
    AfterDoctypeName {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            if (characterReader.b()) {
                tokeniser.d(this);
                tokeniser.g.f = true;
                tokeniser.g();
                tokeniser.a(Data);
            } else if (characterReader.c(9, 10, CharUtils.b, 12, ' ')) {
                characterReader.f();
            } else if (characterReader.c((char) Typography.e)) {
                tokeniser.g();
                tokeniser.b(Data);
            } else if (characterReader.e(DocumentType.f3664a)) {
                tokeniser.g.c = DocumentType.f3664a;
                tokeniser.a(AfterDoctypePublicKeyword);
            } else if (characterReader.e(DocumentType.b)) {
                tokeniser.g.c = DocumentType.b;
                tokeniser.a(AfterDoctypeSystemKeyword);
            } else {
                tokeniser.c((TokeniserState) this);
                tokeniser.g.f = true;
                tokeniser.b(BogusDoctype);
            }
        }
    },
    AfterDoctypePublicKeyword {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.d()) {
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    tokeniser.a(BeforeDoctypePublicIdentifier);
                    return;
                case '\"':
                    tokeniser.c((TokeniserState) this);
                    tokeniser.a(DoctypePublicIdentifier_doubleQuoted);
                    return;
                case '\'':
                    tokeniser.c((TokeniserState) this);
                    tokeniser.a(DoctypePublicIdentifier_singleQuoted);
                    return;
                case '>':
                    tokeniser.c((TokeniserState) this);
                    tokeniser.g.f = true;
                    tokeniser.g();
                    tokeniser.a(Data);
                    return;
                case 65535:
                    tokeniser.d(this);
                    tokeniser.g.f = true;
                    tokeniser.g();
                    tokeniser.a(Data);
                    return;
                default:
                    tokeniser.c((TokeniserState) this);
                    tokeniser.g.f = true;
                    tokeniser.a(BogusDoctype);
                    return;
            }
        }
    },
    BeforeDoctypePublicIdentifier {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.d()) {
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    return;
                case '\"':
                    tokeniser.a(DoctypePublicIdentifier_doubleQuoted);
                    return;
                case '\'':
                    tokeniser.a(DoctypePublicIdentifier_singleQuoted);
                    return;
                case '>':
                    tokeniser.c((TokeniserState) this);
                    tokeniser.g.f = true;
                    tokeniser.g();
                    tokeniser.a(Data);
                    return;
                case 65535:
                    tokeniser.d(this);
                    tokeniser.g.f = true;
                    tokeniser.g();
                    tokeniser.a(Data);
                    return;
                default:
                    tokeniser.c((TokeniserState) this);
                    tokeniser.g.f = true;
                    tokeniser.a(BogusDoctype);
                    return;
            }
        }
    },
    DoctypePublicIdentifier_doubleQuoted {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char d = characterReader.d();
            if (d == 0) {
                tokeniser.c((TokeniserState) this);
                tokeniser.g.d.append(TokeniserState.replacementChar);
            } else if (d == '\"') {
                tokeniser.a(AfterDoctypePublicIdentifier);
            } else if (d == '>') {
                tokeniser.c((TokeniserState) this);
                tokeniser.g.f = true;
                tokeniser.g();
                tokeniser.a(Data);
            } else if (d != 65535) {
                tokeniser.g.d.append(d);
            } else {
                tokeniser.d(this);
                tokeniser.g.f = true;
                tokeniser.g();
                tokeniser.a(Data);
            }
        }
    },
    DoctypePublicIdentifier_singleQuoted {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char d = characterReader.d();
            if (d == 0) {
                tokeniser.c((TokeniserState) this);
                tokeniser.g.d.append(TokeniserState.replacementChar);
            } else if (d == '\'') {
                tokeniser.a(AfterDoctypePublicIdentifier);
            } else if (d == '>') {
                tokeniser.c((TokeniserState) this);
                tokeniser.g.f = true;
                tokeniser.g();
                tokeniser.a(Data);
            } else if (d != 65535) {
                tokeniser.g.d.append(d);
            } else {
                tokeniser.d(this);
                tokeniser.g.f = true;
                tokeniser.g();
                tokeniser.a(Data);
            }
        }
    },
    AfterDoctypePublicIdentifier {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.d()) {
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    tokeniser.a(BetweenDoctypePublicAndSystemIdentifiers);
                    return;
                case '\"':
                    tokeniser.c((TokeniserState) this);
                    tokeniser.a(DoctypeSystemIdentifier_doubleQuoted);
                    return;
                case '\'':
                    tokeniser.c((TokeniserState) this);
                    tokeniser.a(DoctypeSystemIdentifier_singleQuoted);
                    return;
                case '>':
                    tokeniser.g();
                    tokeniser.a(Data);
                    return;
                case 65535:
                    tokeniser.d(this);
                    tokeniser.g.f = true;
                    tokeniser.g();
                    tokeniser.a(Data);
                    return;
                default:
                    tokeniser.c((TokeniserState) this);
                    tokeniser.g.f = true;
                    tokeniser.a(BogusDoctype);
                    return;
            }
        }
    },
    BetweenDoctypePublicAndSystemIdentifiers {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.d()) {
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    return;
                case '\"':
                    tokeniser.c((TokeniserState) this);
                    tokeniser.a(DoctypeSystemIdentifier_doubleQuoted);
                    return;
                case '\'':
                    tokeniser.c((TokeniserState) this);
                    tokeniser.a(DoctypeSystemIdentifier_singleQuoted);
                    return;
                case '>':
                    tokeniser.g();
                    tokeniser.a(Data);
                    return;
                case 65535:
                    tokeniser.d(this);
                    tokeniser.g.f = true;
                    tokeniser.g();
                    tokeniser.a(Data);
                    return;
                default:
                    tokeniser.c((TokeniserState) this);
                    tokeniser.g.f = true;
                    tokeniser.a(BogusDoctype);
                    return;
            }
        }
    },
    AfterDoctypeSystemKeyword {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.d()) {
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    tokeniser.a(BeforeDoctypeSystemIdentifier);
                    return;
                case '\"':
                    tokeniser.c((TokeniserState) this);
                    tokeniser.a(DoctypeSystemIdentifier_doubleQuoted);
                    return;
                case '\'':
                    tokeniser.c((TokeniserState) this);
                    tokeniser.a(DoctypeSystemIdentifier_singleQuoted);
                    return;
                case '>':
                    tokeniser.c((TokeniserState) this);
                    tokeniser.g.f = true;
                    tokeniser.g();
                    tokeniser.a(Data);
                    return;
                case 65535:
                    tokeniser.d(this);
                    tokeniser.g.f = true;
                    tokeniser.g();
                    tokeniser.a(Data);
                    return;
                default:
                    tokeniser.c((TokeniserState) this);
                    tokeniser.g.f = true;
                    tokeniser.g();
                    return;
            }
        }
    },
    BeforeDoctypeSystemIdentifier {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.d()) {
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    return;
                case '\"':
                    tokeniser.a(DoctypeSystemIdentifier_doubleQuoted);
                    return;
                case '\'':
                    tokeniser.a(DoctypeSystemIdentifier_singleQuoted);
                    return;
                case '>':
                    tokeniser.c((TokeniserState) this);
                    tokeniser.g.f = true;
                    tokeniser.g();
                    tokeniser.a(Data);
                    return;
                case 65535:
                    tokeniser.d(this);
                    tokeniser.g.f = true;
                    tokeniser.g();
                    tokeniser.a(Data);
                    return;
                default:
                    tokeniser.c((TokeniserState) this);
                    tokeniser.g.f = true;
                    tokeniser.a(BogusDoctype);
                    return;
            }
        }
    },
    DoctypeSystemIdentifier_doubleQuoted {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char d = characterReader.d();
            if (d == 0) {
                tokeniser.c((TokeniserState) this);
                tokeniser.g.e.append(TokeniserState.replacementChar);
            } else if (d == '\"') {
                tokeniser.a(AfterDoctypeSystemIdentifier);
            } else if (d == '>') {
                tokeniser.c((TokeniserState) this);
                tokeniser.g.f = true;
                tokeniser.g();
                tokeniser.a(Data);
            } else if (d != 65535) {
                tokeniser.g.e.append(d);
            } else {
                tokeniser.d(this);
                tokeniser.g.f = true;
                tokeniser.g();
                tokeniser.a(Data);
            }
        }
    },
    DoctypeSystemIdentifier_singleQuoted {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char d = characterReader.d();
            if (d == 0) {
                tokeniser.c((TokeniserState) this);
                tokeniser.g.e.append(TokeniserState.replacementChar);
            } else if (d == '\'') {
                tokeniser.a(AfterDoctypeSystemIdentifier);
            } else if (d == '>') {
                tokeniser.c((TokeniserState) this);
                tokeniser.g.f = true;
                tokeniser.g();
                tokeniser.a(Data);
            } else if (d != 65535) {
                tokeniser.g.e.append(d);
            } else {
                tokeniser.d(this);
                tokeniser.g.f = true;
                tokeniser.g();
                tokeniser.a(Data);
            }
        }
    },
    AfterDoctypeSystemIdentifier {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            switch (characterReader.d()) {
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    return;
                case '>':
                    tokeniser.g();
                    tokeniser.a(Data);
                    return;
                case 65535:
                    tokeniser.d(this);
                    tokeniser.g.f = true;
                    tokeniser.g();
                    tokeniser.a(Data);
                    return;
                default:
                    tokeniser.c((TokeniserState) this);
                    tokeniser.a(BogusDoctype);
                    return;
            }
        }
    },
    BogusDoctype {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            char d = characterReader.d();
            if (d == '>') {
                tokeniser.g();
                tokeniser.a(Data);
            } else if (d == 65535) {
                tokeniser.g();
                tokeniser.a(Data);
            }
        }
    },
    CdataSection {
        /* access modifiers changed from: package-private */
        public void read(Tokeniser tokeniser, CharacterReader characterReader) {
            tokeniser.a(characterReader.a("]]>"));
            if (characterReader.d("]]>") || characterReader.b()) {
                tokeniser.a(Data);
            }
        }
    };
    
    static final char[] attributeDoubleValueCharsSorted = null;
    static final char[] attributeNameCharsSorted = null;
    static final char[] attributeSingleValueCharsSorted = null;
    static final char[] attributeValueUnquoted = null;
    private static final char eof = '￿';
    static final char nullChar = '\u0000';
    private static final char replacementChar = '�';
    /* access modifiers changed from: private */
    public static final String replacementStr = null;

    /* access modifiers changed from: package-private */
    public abstract void read(Tokeniser tokeniser, CharacterReader characterReader);

    static {
        attributeSingleValueCharsSorted = new char[]{0, Typography.c, Operators.SINGLE_QUOTE};
        attributeDoubleValueCharsSorted = new char[]{0, '\"', Typography.c};
        attributeNameCharsSorted = new char[]{0, 9, 10, 12, CharUtils.b, ' ', '\"', Operators.SINGLE_QUOTE, IOUtils.f15883a, Typography.d, '=', Typography.e};
        attributeValueUnquoted = new char[]{0, 9, 10, 12, CharUtils.b, ' ', '\"', Typography.c, Operators.SINGLE_QUOTE, Typography.d, '=', Typography.e, '`'};
        replacementStr = String.valueOf(replacementChar);
    }

    /* access modifiers changed from: private */
    public static void handleDataEndTag(Tokeniser tokeniser, CharacterReader characterReader, TokeniserState tokeniserState) {
        if (characterReader.p()) {
            String l = characterReader.l();
            tokeniser.c.b(l);
            tokeniser.b.append(l);
            return;
        }
        boolean z = true;
        if (tokeniser.i() && !characterReader.b()) {
            char d = characterReader.d();
            switch (d) {
                case 9:
                case 10:
                case 12:
                case 13:
                case ' ':
                    tokeniser.a(BeforeAttributeName);
                    break;
                case '/':
                    tokeniser.a(SelfClosingStartTag);
                    break;
                case '>':
                    tokeniser.c();
                    tokeniser.a(Data);
                    break;
                default:
                    tokeniser.b.append(d);
                    break;
            }
            z = false;
        }
        if (z) {
            tokeniser.a("</" + tokeniser.b.toString());
            tokeniser.a(tokeniserState);
        }
    }

    /* access modifiers changed from: private */
    public static void readData(Tokeniser tokeniser, CharacterReader characterReader, TokeniserState tokeniserState, TokeniserState tokeniserState2) {
        char c = characterReader.c();
        if (c == 0) {
            tokeniser.c(tokeniserState);
            characterReader.f();
            tokeniser.a((char) replacementChar);
        } else if (c == '<') {
            tokeniser.b(tokeniserState2);
        } else if (c != 65535) {
            tokeniser.a(characterReader.a(Typography.d, 0));
        } else {
            tokeniser.a((Token) new Token.EOF());
        }
    }

    /* access modifiers changed from: private */
    public static void readCharRef(Tokeniser tokeniser, TokeniserState tokeniserState) {
        int[] a2 = tokeniser.a((Character) null, false);
        if (a2 == null) {
            tokeniser.a((char) Typography.c);
        } else {
            tokeniser.a(a2);
        }
        tokeniser.a(tokeniserState);
    }

    /* access modifiers changed from: private */
    public static void readEndTag(Tokeniser tokeniser, CharacterReader characterReader, TokeniserState tokeniserState, TokeniserState tokeniserState2) {
        if (characterReader.p()) {
            tokeniser.a(false);
            tokeniser.a(tokeniserState);
            return;
        }
        tokeniser.a("</");
        tokeniser.a(tokeniserState2);
    }

    /* access modifiers changed from: private */
    public static void handleDataDoubleEscapeTag(Tokeniser tokeniser, CharacterReader characterReader, TokeniserState tokeniserState, TokeniserState tokeniserState2) {
        if (characterReader.p()) {
            String l = characterReader.l();
            tokeniser.b.append(l);
            tokeniser.a(l);
            return;
        }
        char d = characterReader.d();
        switch (d) {
            case 9:
            case 10:
            case 12:
            case 13:
            case ' ':
            case '/':
            case '>':
                if (tokeniser.b.toString().equals(WebEvent.A)) {
                    tokeniser.a(tokeniserState);
                } else {
                    tokeniser.a(tokeniserState2);
                }
                tokeniser.a(d);
                return;
            default:
                characterReader.e();
                tokeniser.a(tokeniserState2);
                return;
        }
    }
}
