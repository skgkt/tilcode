package app;

import static org.junit.jupiter.api.Assertions.*;

import java.time.*;
import java.time.zone.ZoneOffsetTransition;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@Slf4j
class JavaTimeTest {

    @Nested
    class ZoneIdTest {

        @DisplayName("JVMにおいて使用可能なZoneIdの一覧が出力されること")
        @Test
        void printAvailableZoneIds() {
            var availableZoneIds = ZoneId.getAvailableZoneIds();
            availableZoneIds.forEach(log::info);
            assertTrue(availableZoneIds.size() >= 1);
        }

        @DisplayName("Asia/TokyoとJSTは同じZoneIdであること")
        @Test
        void definitionPattern() {
            var longId = ZoneId.of("Asia/Tokyo");
            var shortId = ZoneId.of("JST", ZoneId.SHORT_IDS);
            assertEquals(longId, shortId);
        }
    }

    @Nested
    class ZoneOffsetTest {

        @DisplayName("ZoneOffsetはZoneIdのサブクラスである")
        @Test
        @SuppressWarnings("ConstantConditions")
        void zoneOffsetIsZoneIdSubClass() {
            var zoneOffset = ZoneOffset.of("+09:00");
            assertTrue(zoneOffset instanceof ZoneId);
            assertTrue(zoneOffset instanceof ZoneOffset);
        }

        @DisplayName("生成の方法は異なるが、同じZoneOffsetであること")
        @Test
        void definitionPattern() {
            var zoneOffset1 = ZoneOffset.of("+09:00");
            var zoneOffset2 = ZoneOffset.ofHoursMinutes(9, 0);
            assertEquals(zoneOffset1, zoneOffset2);
        }
    }

    @Nested
    class ZoneRuleTest {

        @DisplayName("日本における過去の夏時間情報が出力されること")
        @Test
        void printZoneOffsetTransition() {
            ZoneId.of("Asia/Tokyo").getRules().getTransitions().stream()
                    .map(ZoneOffsetTransition::toString)
                    .forEach(log::info);
        }
    }

    @Nested
    class InstantTest {

        @Test
        void instant() {
            // 時系列における単一時点を表すモデル。
            // Date and Time API はこの Instant を介して変換することで正しく変換できる。
            assertNotNull(Instant.now());
        }
    }

    @DisplayName("型もタイムゾーンも異なる場合でも同じ日時に変換するテスト")
    @Nested
    class DifferentTypeAndTimeZoneTest {

        @DisplayName("同じLocalDateTimeとなること")
        @Test
        void localDateTimeAndOffsetDateTime() {
            var instant = Instant.now();
            // 日本時間換算で LocalDateTime を宣言する。
            var localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Tokyo"));
            // UTC で OffsetDateTime を宣言する。
            var offsetDateTime = OffsetDateTime.ofInstant(instant, ZoneOffset.UTC);

            // OffsetDateTime は内部に LocalDateTime と ZoneOffset を保持しているが、
            // toLocalDateTime() メソッドは、内部に保持している LocalDateTime を返すだけであるため、
            // ZoneOffset 分だけ誤差が生じてしまう。
            assertNotEquals(localDateTime, offsetDateTime.toLocalDateTime());

            // 一方、Instant を介して LocalDateTime に変換する場合は ofInstant() メソッド呼び出し時に
            // 日本時間としてオフセットが計算されるため誤差なく変換できる。
            assertEquals(
                    localDateTime,
                    LocalDateTime.ofInstant(offsetDateTime.toInstant(), ZoneId.systemDefault()));
        }
    }
}
