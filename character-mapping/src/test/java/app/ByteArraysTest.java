package app;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

class ByteArraysTest {

    @Test
    void toHexString() {
        assertEquals("41", ByteArrays.toHexString("A".getBytes(StandardCharsets.UTF_8)));
    }
}
