package hu.unipannon.mik.balatoniszel.client;


import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

public class BaseController {

    private static final String CLIENT_PUBLIC = "BalatoniSzelPublicClient";
    private static final String CLIENT_GUEST = "BalatoniSzelGuestClient";
    private static final String CLIENT_ADMIN = "BalatoniSzelAdminClient";

    private static final String TOKEN = "BalatoniSzelToken";
    private static final String LOGIN_LEVEL = "BalatoniSzelLoginLevel";

    protected static final ModelAndView UNAUTH_VIEW = new ModelAndView("unauthorized");

    private final ClientFactory clientFactory;
    private final String publicUrl;

    public BaseController(ClientFactory clientFactory, String publicUrl) {
        this.clientFactory = clientFactory;
        this.publicUrl = publicUrl;
    }

    protected String getToken(HttpSession session) {
        return (String) session.getAttribute(TOKEN);
    }

    protected BalatoniSzelAdmin getAdminClient(HttpSession session) {
        return (BalatoniSzelAdmin) session.getAttribute(CLIENT_ADMIN);
    }

    protected BalatoniSzelGuest getGuestClient(HttpSession session) {
        return (BalatoniSzelGuest) session.getAttribute(CLIENT_GUEST);
    }

    protected BalatoniSzelPublic getPublicClient(HttpSession session) {
        BalatoniSzelPublic inSession =  (BalatoniSzelPublic) session.getAttribute(CLIENT_PUBLIC);
        if(inSession == null) {
            return clientFactory.getClient(publicUrl, BalatoniSzelPublic.class);
        } else {
            return inSession;
        }
    }

    protected void handleLogin(LoginInfo loginInfo, HttpSession session) {
        String serverUrl = loginInfo.getServerUrl();
        String token = loginInfo.getToken();
        LoginLevel level = loginInfo.getLevel();
        session.setAttribute(TOKEN, token);
        switch(level) {
            case PUBLIC: {
                BalatoniSzelPublic client = clientFactory.getClient(serverUrl, BalatoniSzelPublic.class);
                session.setAttribute(CLIENT_PUBLIC, client);
            } break;
            case GUEST: {
                BalatoniSzelGuest client = clientFactory.getClient(serverUrl, BalatoniSzelGuest.class);
                session.setAttribute(CLIENT_PUBLIC, client);
                session.setAttribute(CLIENT_GUEST, client);
            } break;
            case ADMIN: {
                BalatoniSzelAdmin client = clientFactory.getClient(serverUrl, BalatoniSzelAdmin.class);
                session.setAttribute(CLIENT_PUBLIC, client);
                session.setAttribute(CLIENT_GUEST, client);
                session.setAttribute(CLIENT_ADMIN, client);
            } break;
        }
        session.setAttribute(LOGIN_LEVEL, level);
    }

    protected LoginLevel getLoginLevel(HttpSession session) {
        LoginLevel inSession = (LoginLevel) session.getAttribute(LOGIN_LEVEL);
        if(inSession == null) {
            return LoginLevel.PUBLIC;
        } else {
            return inSession;
        }
    }

    protected void clearSession(HttpSession session) {
        session.removeAttribute(CLIENT_ADMIN);
        session.removeAttribute(CLIENT_GUEST);
        session.removeAttribute(CLIENT_PUBLIC);
        session.removeAttribute(TOKEN);
        session.removeAttribute(LOGIN_LEVEL);
    }

}
