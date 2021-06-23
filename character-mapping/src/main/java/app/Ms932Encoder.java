package app;

import java.util.Map;
import java.util.Objects;

public class Ms932Encoder {
    public static final String EM_DASH = Ms932.of("815C");
    public static final String MINUS_SIGN = Ms932.of("817C");
    public static final String WAVE_DASH = Ms932.of("8160");
    public static final String DOUBLE_VERTICAL_LINE = Ms932.of("8161");

    private static final Map<String, String> mappingTable =
            Map.ofEntries(
                    Map.entry(UnicodeCharacterCode.EM_DASH, EM_DASH),
                    Map.entry(UnicodeCharacterCode.MINUS_SIGN, MINUS_SIGN),
                    Map.entry(UnicodeCharacterCode.WAVE_DASH, WAVE_DASH),
                    Map.entry(UnicodeCharacterCode.DOUBLE_VERTICAL_LINE, DOUBLE_VERTICAL_LINE));

    public static byte[] encode(String string) {
        Objects.requireNonNull(string);
        var replaced = mappingTable.getOrDefault(string, string);
        return replaced.getBytes(Ms932.CHARSET);
    }
}
