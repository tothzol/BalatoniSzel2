package hu.unipannon.mik.balatoniszel.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"hu.unipannon.mik.balatoniszel.**"})
public class GuestServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuestServerApplication.class, args);
    }
}
