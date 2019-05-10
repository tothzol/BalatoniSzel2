package hu.unipannon.mik.balatoniszel.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RestController
public class PublicClientController extends BaseController {

    @Autowired
    public PublicClientController(ClientFactory clientFactory, @Value("${public.url}") String publicUrl) {
        super(clientFactory, publicUrl);
    }

    @GetMapping(path = "/")
    public ModelAndView index(HttpSession session) {
        LoginLevel loginLevel = getLoginLevel(session);
        String viewName;
        switch(loginLevel) {
            case ADMIN:
                viewName = "redirect:/admin"; break;
            case GUEST:
                viewName = "redirect:/guest"; break;
            case PUBLIC:
            default: viewName = "public_index";

        }
        ModelAndView      indexView = new ModelAndView(viewName);
        return indexView;
    }

    @GetMapping(path = "/logout")
    public ModelAndView logout(HttpSession session) {
        getPublicClient(session).logout(getToken(session));
        clearSession(session);
        ModelAndView      indexView = new ModelAndView("redirect:/");
        return indexView;
    }

    @PostMapping(path="/login")
    public ModelAndView login(@RequestParam(name = "email") String email,
                                 @RequestParam(name = "password") String password,
                                 HttpSession session) {
        BalatoniSzelPublic client = getPublicClient(session);
        LoginInfo loginInfo = client.login(email, password);
        handleLogin(loginInfo, session);
        return new ModelAndView("redirect:/");
    }

    @GetMapping(path = "/register")
    public ModelAndView register() {
        ModelAndView      registerView = new ModelAndView("public_register");
        return registerView;
    }

    @PostMapping(path="/register")
    public ModelAndView register(@RequestParam(name = "guestName") String name,
                                 @RequestParam(name = "guestEmail") String email,
                                 @RequestParam(name = "guestAddress") String address,
                                 @RequestParam(name = "guestDocument") String document,
                                 @RequestParam(name = "guestPassword") String password,
                                 @RequestParam(name = "guestPasswordOneMore") String passwordOneMore,
                                 HttpSession session) {
        BalatoniSzelPublic client = getPublicClient(session);
        client.register(name,email,address,document, password,passwordOneMore);
        LoginInfo loginInfo = client.login(email, password);
        handleLogin(loginInfo, session);
        return new ModelAndView("redirect:/");
    }

}
