package hu.unipannon.mik.balatoniszel.client;

import hu.unipannon.mik.balatoniszel.core.SessionReaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Configuration
public class SessionReaperConfiguration {

    private final SessionReaper sessionReaper;

    private ScheduledExecutorService reaperExecutor;

    @Autowired
    public SessionReaperConfiguration(SessionReaper sessionReaper) {
        this.sessionReaper = sessionReaper;
    }

    @PostConstruct
    public void scheduleExpirationChecker() {
        this.reaperExecutor = Executors.newScheduledThreadPool(1);
        // TODO fix after demo
        this.reaperExecutor.scheduleAtFixedRate(sessionReaper, 0, 1, TimeUnit.MINUTES);
    }

    @PreDestroy
    public void shutdownExpirationExecutor() {
        reaperExecutor.shutdown();
    }
}
