package hu.unipannon.mik.balatoniszel.client;

import hu.unipannon.mik.balatoniszel.core.ExpirationChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Configuration
public class ExpirationConfiguration {

    private final ExpirationChecker expirationChecker;

    private ScheduledExecutorService expirationExecutor;

    @Autowired
    public ExpirationConfiguration(ExpirationChecker expirationChecker) {
        this.expirationChecker = expirationChecker;
    }

    @PostConstruct
    public void scheduleExpirationChecker() {
        this.expirationExecutor = Executors.newScheduledThreadPool(1);
        // TODO fix after demo
        this.expirationExecutor.scheduleAtFixedRate(expirationChecker, 0, 1, TimeUnit.MINUTES);
    }

    @PreDestroy
    public void shutdownExpirationExecutor() {
        expirationExecutor.shutdown();
    }
}
