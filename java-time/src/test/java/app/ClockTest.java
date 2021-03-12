package app;

import java.time.*;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class ClockTest {

    /** 最も表現力のある ZonedDateTime 型に変換します。 */
    private static ZonedDateTime map(Clock clock) {
        return Objects.requireNonNull(clock).instant().atZone(clock.getZone());
    }

    @DisplayName("ゾーンを持つクロックが生成されること")
    @Test
    void zonedClock() {
        log.info("デフォルトのゾーンから取得する");
        log.info("Clock.systemDefaultZone={}", map(Clock.systemDefaultZone()));

        log.info("任意のゾーンから取得する");
        var pst = ZoneId.of("PST", ZoneId.SHORT_IDS);
        log.info("Clock.system={}", map(Clock.system(pst)));

        log.info("ゾーンを切り替える");
        var clock = Clock.systemUTC();
        log.info("Clock.systemUTC={}", map(clock));
        log.info("Clock.withZone={}", map(clock.withZone(ZoneId.of("Asia/Tokyo"))));
    }

    @DisplayName("オフセットを反映したクロックが生成されること")
    @Test
    void offsetClock() {
        log.info("UTC から取得する");
        log.info("Clock.systemUTC={}", map(Clock.systemUTC()));

        log.info("任意のオフセットから取得する");
        log.info("Clock.offset={}", map(Clock.offset(Clock.systemUTC(), Duration.ofHours(9))));
    }

    @DisplayName("ステップするクロックが生成されること")
    @Test
    void tickClock() {
        final var zoneId = ZoneId.systemDefault();

        log.info("ミリ秒単位にステップするクロック");
        log.info("Clock.tickMillis={}", map(Clock.tickMillis(zoneId)));
        log.info("秒単位にステップするクロック");
        log.info("Clock.tickSeconds={}", map(Clock.tickSeconds(zoneId)));
        log.info("分単位にステップするクロック");
        log.info("Clock.tickMinutes={}", map(Clock.tickMinutes(zoneId)));
        log.info("指定した Duration にステップするクロック");
        log.info("Clock.tick={}", map(Clock.tick(Clock.systemDefaultZone(), Duration.ofHours(1L))));
    }

    @DisplayName("固定のクロックが生成されること")
    @Test
    void fixedClock() throws InterruptedException {
        var fixedClock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        log.info("固定クロック");
        log.info("Clock.fixed={}", fixedClock);

        var instant1 = fixedClock.instant();
        log.info("最初の日付/時刻={}", map(fixedClock));

        TimeUnit.SECONDS.sleep(1L);

        var instant2 = fixedClock.instant();
        log.info("スリープ後の日付/時刻={}", map(fixedClock));

        Assertions.assertEquals(instant1, instant2);
    }
}
