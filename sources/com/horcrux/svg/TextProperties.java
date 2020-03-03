package com.horcrux.svg;

import com.ximalaya.ting.android.opensdk.auth.constants.XmlyAuthErrorNoConstants;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;

class TextProperties {

    enum Direction {
        ltr,
        rtl
    }

    enum FontStyle {
        normal,
        italic,
        oblique
    }

    enum FontVariantLigatures {
        normal,
        none
    }

    enum TextAnchor {
        start,
        middle,
        end
    }

    enum TextLengthAdjust {
        spacing,
        spacingAndGlyphs
    }

    enum TextPathMethod {
        align,
        stretch
    }

    enum TextPathMidLine {
        sharp,
        smooth
    }

    enum TextPathSide {
        left,
        right
    }

    enum TextPathSpacing {
        auto,
        exact
    }

    TextProperties() {
    }

    enum AlignmentBaseline {
        baseline("baseline"),
        textBottom("text-bottom"),
        alphabetic("alphabetic"),
        ideographic("ideographic"),
        middle("middle"),
        central("central"),
        mathematical("mathematical"),
        textTop("text-top"),
        bottom("bottom"),
        center("center"),
        top("top"),
        textBeforeEdge("text-before-edge"),
        textAfterEdge("text-after-edge"),
        beforeEdge("before-edge"),
        afterEdge("after-edge"),
        hanging("hanging");
        
        private static final Map<String, AlignmentBaseline> alignmentToEnum = null;
        private final String alignment;

        static {
            int i;
            alignmentToEnum = new HashMap();
            for (AlignmentBaseline alignmentBaseline : values()) {
                alignmentToEnum.put(alignmentBaseline.alignment, alignmentBaseline);
            }
        }

        private AlignmentBaseline(String str) {
            this.alignment = str;
        }

        static AlignmentBaseline getEnum(String str) {
            if (alignmentToEnum.containsKey(str)) {
                return alignmentToEnum.get(str);
            }
            throw new IllegalArgumentException("Unknown String Value: " + str);
        }

        @Nonnull
        public String toString() {
            return this.alignment;
        }
    }

    enum FontWeight {
        Normal("normal"),
        Bold("bold"),
        Bolder("bolder"),
        Lighter("lighter"),
        w100("100"),
        w200(XmlyAuthErrorNoConstants.g),
        w300("300"),
        w400("400"),
        w500(XmlyAuthErrorNoConstants.r),
        w600("600"),
        w700("700"),
        w800("800"),
        w900("900");
        
        private static final Map<String, FontWeight> weightToEnum = null;
        private final String weight;

        static {
            int i;
            weightToEnum = new HashMap();
            for (FontWeight fontWeight : values()) {
                weightToEnum.put(fontWeight.weight, fontWeight);
            }
        }

        private FontWeight(String str) {
            this.weight = str;
        }

        static FontWeight getEnum(String str) {
            if (weightToEnum.containsKey(str)) {
                return weightToEnum.get(str);
            }
            throw new IllegalArgumentException("Unknown String Value: " + str);
        }

        @Nonnull
        public String toString() {
            return this.weight;
        }
    }

    enum TextDecoration {
        None("none"),
        Underline(TtmlNode.UNDERLINE),
        Overline("overline"),
        LineThrough("line-through"),
        Blink("blink");
        
        private static final Map<String, TextDecoration> decorationToEnum = null;
        private final String decoration;

        static {
            int i;
            decorationToEnum = new HashMap();
            for (TextDecoration textDecoration : values()) {
                decorationToEnum.put(textDecoration.decoration, textDecoration);
            }
        }

        private TextDecoration(String str) {
            this.decoration = str;
        }

        static TextDecoration getEnum(String str) {
            if (decorationToEnum.containsKey(str)) {
                return decorationToEnum.get(str);
            }
            throw new IllegalArgumentException("Unknown String Value: " + str);
        }

        @Nonnull
        public String toString() {
            return this.decoration;
        }
    }
}
