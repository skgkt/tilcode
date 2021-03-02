package app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public String bean1() {
        log.info("bean1!!");
        return "bean1";
    }

    @Bean
    public String bean2() {
        throw new UnsupportedOperationException();
    }
}
