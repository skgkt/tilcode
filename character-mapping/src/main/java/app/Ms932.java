package app;

import com.google.common.base.Preconditions;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.Set;

public class Ms932 {
    public static final Charset CHARSET = Charset.forName("ms932");

    public static String of(String ms932CharacterCode) {
        Objects.requireNonNull(ms932CharacterCode);
        Preconditions.checkArgument(Set.of(2, 4).contains(ms932CharacterCode.length()));

        if (ms932CharacterCode.length() == 2) {
            return new String(new byte[] {(byte) Integer.parseInt(ms932CharacterCode, 16)});
        }

        var upper = ms932CharacterCode.substring(0, 2);
        var lower = ms932CharacterCode.substring(2, 4);

        var bytes =
                new byte[] {(byte) Integer.parseInt(upper, 16), (byte) Integer.parseInt(lower, 16)};
        return new String(bytes, CHARSET);
    }
}
