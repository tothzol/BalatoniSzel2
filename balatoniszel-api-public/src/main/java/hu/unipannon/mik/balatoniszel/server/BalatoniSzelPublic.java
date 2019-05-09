package hu.unipannon.mik.balatoniszel.server;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface BalatoniSzelPublic {
    @WebMethod
    LoginInfo login(String email, String password);
}
