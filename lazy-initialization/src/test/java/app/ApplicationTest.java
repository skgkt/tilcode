package app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ApplicationTest {
    @Autowired @Qualifier("bean1")
    private String sut;

    @Test
    void main() {
        assertNotNull(sut);
    }
}
