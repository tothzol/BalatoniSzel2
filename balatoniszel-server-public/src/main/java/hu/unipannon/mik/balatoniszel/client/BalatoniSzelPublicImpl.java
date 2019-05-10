package hu.unipannon.mik.balatoniszel.client;

import hu.unipannon.mik.balatoniszel.core.GuestEntity;
import hu.unipannon.mik.balatoniszel.core.GuestRepository;
import hu.unipannon.mik.balatoniszel.core.SessionEntity;
import hu.unipannon.mik.balatoniszel.core.SessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Clock;
import java.time.LocalDateTime;

public class BalatoniSzelPublicImpl implements BalatoniSzelPublic {

    private static final Logger LOG = LoggerFactory.getLogger(BalatoniSzelPublicImpl.class);

    protected final GuestRepository guestRepository;
    protected final SessionRepository sessionRepository;
    protected final ServerUrls serverUrls;
    protected final Clock clock;

    public BalatoniSzelPublicImpl(GuestRepository guestRepository,
                                  SessionRepository sessionRepository,
                                  ServerUrls serverUrls,
                                  Clock clock) {
        this.guestRepository = guestRepository;
        this.sessionRepository = sessionRepository;
        this.serverUrls = serverUrls;
        this.clock = clock;
    }


    @Override
    public LoginInfo login(String email, String password) {
        GuestEntity guest = guestRepository.getGuest(email, password);
        LoginInfo loginInfo = new LoginInfo();
        if(guest != null) {
            LOG.info("Login OK");
            SessionEntity session = sessionRepository.createSession(guest, guest.getLoginLevel(), LocalDateTime.now(clock));
            loginInfo.setToken(session.getId());
            loginInfo.setServerUrl(serverUrls.getServerUrl(guest.getLoginLevel()));
            loginInfo.setLevel(guest.getLoginLevel());
        } else {
            LOG.error("Login failed");
            loginInfo.setToken(null);
            loginInfo.setLevel(LoginLevel.PUBLIC);
            loginInfo.setServerUrl(serverUrls.getPublicUrl());
        }
        return loginInfo;
    }

    @Override
    public void logout(String token) {
        sessionRepository.logout(token);
    }

    @Override
    public void register(String name, String email, String address, String document, String password, String passwordOneMore) {
        guestRepository.addNewGuest(name, address, document, email, false, password);
    }

    protected boolean checkTokenFor(String token, LoginLevel level) {
        SessionEntity session = sessionRepository.getSession(token);
        if(session == null) {
            LOG.info("no session for token {}", token);
            return LoginLevel.PUBLIC.equals(level) || level == null;
        } else {
            LOG.info("session found, level {}", session.getLevel());
            return session.getLevel().atLeast(level);

        }
    }

    protected GuestEntity getGuestFor(String token) {
        SessionEntity session = sessionRepository.getSession(token);
        if(session == null) {
            return null;
        } else {
            return guestRepository.getGuest(session.getGuestId());
        }
    }
}
