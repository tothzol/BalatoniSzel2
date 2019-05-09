package hu.unipannon.mik.balatoniszel.server;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan(basePackages = {"hu.unipannon.mik.balatoniszel.**"})
@EnableTransactionManagement
public class PersistenceConfiguration {
}
