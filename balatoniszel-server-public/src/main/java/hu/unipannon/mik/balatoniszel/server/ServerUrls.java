package hu.unipannon.mik.balatoniszel.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServerUrls {
    private final String publicUrl;
    private final String guestUrl;
    private final String adminUrl;


    public ServerUrls(@Value("${urls.public}") String publicUrl,
                      @Value("${urls.guest}")String guestUrl,
                      @Value("${urls.admin}")String adminUrl) {
        this.publicUrl = publicUrl;
        this.guestUrl = guestUrl;
        this.adminUrl = adminUrl;
    }


    public String getPublicUrl() {
        return publicUrl;
    }

    public String getGuestUrl() {
        return guestUrl;
    }

    public String getAdminUrl() {
        return adminUrl;
    }
}
