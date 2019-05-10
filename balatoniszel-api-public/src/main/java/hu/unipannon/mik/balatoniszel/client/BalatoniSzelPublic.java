package hu.unipannon.mik.balatoniszel.client;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface BalatoniSzelPublic {
    @WebMethod
    LoginInfo login(String email, String password);

    void logout(String token);

    void register(String name, String email, String address, String document, String password, String passwordOneMore);
}
