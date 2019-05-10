package hu.unipannon.mik.balatoniszel.core;

import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;

@Component
public class SessionReaper implements Runnable {

    private final SessionRepository sessionRepository;
    private final Clock clock;

    public SessionReaper(SessionRepository sessionRepository, Clock clock) {
        this.sessionRepository = sessionRepository;
        this.clock = clock;
    }

    @Override
    public void run() {
        sessionRepository.deleteExpiredSessions(LocalDateTime.now(clock));
    }
}
