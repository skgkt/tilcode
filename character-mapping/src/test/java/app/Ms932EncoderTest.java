package app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class Ms932EncoderTest {

    @Nested
    @DisplayName("ハイフンマイナス")
    class HyphenMinus {

        @Test
        @DisplayName("HYPHEN-MINUS")
        void hyphenMinus() {
            // F10キー
            assertEquals("2D", ByteArrays.toHexString(Ms932Encoder.encode("\u002D")));
        }

        @Test
        @DisplayName("FULLWIDTH HYPHEN-MINUS")
        void fullWidthHyphenMinus() {
            // F9キー
            assertEquals("817C", ByteArrays.toHexString(Ms932Encoder.encode("\uFF0D")));
        }

        @Test
        @DisplayName("KATAKANA-HIRAGANA PROLONGED SOUND MARK")
        void katakanaHiraganaProlongedSoundMark() {
            // F7キー
            assertEquals("815B", ByteArrays.toHexString(Ms932Encoder.encode("\u30FC")));
        }

        @Test
        @DisplayName("HALFWIDTH KATAKANA-HIRAGANA PROLONGED SOUND MARK")
        void halfWidthKatakanaHiraganaProlongedSoundMark() {
            // F8キー
            assertEquals("B0", ByteArrays.toHexString(Ms932Encoder.encode("\uFF70")));
        }

        @Test
        @DisplayName("HYPHEN")
        void hyphen() {
            assertEquals("815D", ByteArrays.toHexString(Ms932Encoder.encode("\u2010")));
        }

        @Test
        @DisplayName("NON-BREAKING HYPHEN")
        void nonBreakingHyphen() {
            // MS932に未定義
            assertEquals("", ByteArrays.toHexString(Ms932Encoder.encode("\u2011")));
        }

        @Test
        @DisplayName("FIGURE DASH")
        void figureDash() {
            // MS932に未定義
            assertEquals("", ByteArrays.toHexString(Ms932Encoder.encode("\u2012")));
        }

        @Test
        @DisplayName("HORIZONTAL BAR")
        void horizontalBar() {
            assertEquals("815C", ByteArrays.toHexString(Ms932Encoder.encode("\u2015")));
        }

        @Test
        @DisplayName("MINUS SIGN")
        void name() {
            assertEquals("817C", ByteArrays.toHexString(Ms932Encoder.encode("\u2212")));
        }

        @Test
        @DisplayName("EN DASH")
        void enDash() {
            // MS932に未定義
            assertEquals("", ByteArrays.toHexString(Ms932Encoder.encode("\u2013")));
        }

        @Test
        @DisplayName("EM DASH")
        void emDash() {
            assertEquals("815C", ByteArrays.toHexString(Ms932Encoder.encode("\u2014")));
        }
    }

    @Nested
    @DisplayName("波ダッシュ")
    class WaveDash {

        @Test
        @DisplayName("WAVE DASH")
        void waveDash() {
            assertEquals("8160", ByteArrays.toHexString(Ms932Encoder.encode("\u301C")));
        }

        @Test
        @DisplayName("FULLWIDTH TILDE")
        void fullWidthTilde() {
            assertEquals("8160", ByteArrays.toHexString(Ms932Encoder.encode("\uFF5E")));
        }
    }

    @Test
    @DisplayName("二重縦線")
    void doubleVerticalLine() {
        assertEquals("8161", ByteArrays.toHexString(Ms932Encoder.encode("\u2016")));
    }

    @Test
    @DisplayName("PARALLEL TO")
    void parallelTo() {
        assertEquals("8161", ByteArrays.toHexString(Ms932Encoder.encode("\u2225")));
    }

    @Test
    @DisplayName("BROKEN BAR")
    void brokenBar() {
        // MS932に未定義
        assertEquals("", ByteArrays.toHexString(Ms932Encoder.encode("\u00A6")));
    }

    @Test
    @DisplayName("FULLWIDTH BROKEN BAR")
    void fullWidthBrokenBar() {
        assertEquals("FA55", ByteArrays.toHexString(Ms932Encoder.encode("\uFFE4")));
    }

    @Nested
    @DisplayName("セント記号")
    class CentSign {

        @Test
        @DisplayName("CENT SIGN")
        void centSign() {
            assertEquals("8191", ByteArrays.toHexString(Ms932Encoder.encode("\u00A2")));
        }

        @Test
        @DisplayName("FULLWIDTH CENT SIGN")
        void fullWidthCentSign() {
            assertEquals("8191", ByteArrays.toHexString(Ms932Encoder.encode("\uFFE0")));
        }
    }

    @Nested
    @DisplayName("ポンド記号")
    class PoundSign {

        @Test
        @DisplayName("POUND SIGN")
        void poundSign() {
            assertEquals("8192", ByteArrays.toHexString(Ms932Encoder.encode("\u00A3")));
        }

        @Test
        @DisplayName("FULLWIDTH POUND SIGN")
        void fullWidthPoundSign() {
            assertEquals("8192", ByteArrays.toHexString(Ms932Encoder.encode("\uFFE1")));
        }
    }

    @Nested
    @DisplayName("否定記号")
    class NotSign {

        @Test
        @DisplayName("POUND SIGN")
        void poundSign() {
            assertEquals("81CA", ByteArrays.toHexString(Ms932Encoder.encode("\u00AC")));
        }

        @Test
        @DisplayName("FULLWIDTH NOT SIGN")
        void fullWidthPoundSign() {
            assertEquals("81CA", ByteArrays.toHexString(Ms932Encoder.encode("\uFFE2")));
        }
    }
}
