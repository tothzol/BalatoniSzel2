package hu.unipannon.mik.balatoniszel.server;

import hu.unipannon.mik.balatoniszel.core.GuestEntity;
import hu.unipannon.mik.balatoniszel.core.GuestRepository;

public class BalatoniSzelPublicImpl implements BalatoniSzelPublic {

    protected final GuestRepository guestRepository;
    protected final ServerUrls serverUrls;

    public BalatoniSzelPublicImpl(GuestRepository guestRepository, ServerUrls serverUrls) {
        this.guestRepository = guestRepository;
        this.serverUrls = serverUrls;
    }


    @Override
    public LoginInfo login(String email, String password) {
        GuestEntity guest = guestRepository.getGuest(email, password);
        if(guest != null) {

        } else {
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setToken(null);
            loginInfo.setLevel(LoginLevel.PUBLIC);
            loginInfo.setServerUrl(serverUrls.getPublicUrl());
        }
    }
}
