package app;

public class ByteArrays {

    public static String toHexString(byte[] bytes) {
        var builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02X", Byte.toUnsignedInt(b)));
        }
        return builder.toString();
    }
}
