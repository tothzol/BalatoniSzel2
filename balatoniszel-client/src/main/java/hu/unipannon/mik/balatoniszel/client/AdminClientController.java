package hu.unipannon.mik.balatoniszel.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class AdminClientController extends BaseController {

    @Autowired
    public AdminClientController(ClientFactory clientFactory, @Value("${public.url}") String publicUrl) {
        super(clientFactory, publicUrl);
    }

    @GetMapping(path = "/admin")
    public ModelAndView adminIndex(HttpSession session) {
        BalatoniSzelAdmin client = getAdminClient(session);
        if (client != null) {
            ModelAndView adminIndex =  new ModelAndView("admin_index");
            adminIndex.addObject("reservations", client.reservations(getToken(session)));
            return adminIndex;
        } else {
            return UNAUTH_VIEW;
        }
    }

    @GetMapping(path = "/admin/reservations")
    public ModelAndView reservations(HttpSession session) {
        BalatoniSzelAdmin client = getAdminClient(session);
        if(client != null) {
            ModelAndView reservationsView = new ModelAndView("admin_reservations");
            reservationsView.addObject("reservations", client.reservations(getToken(session)));
            return reservationsView;
        } else {
            return UNAUTH_VIEW;
        }
    }

    @PostMapping(path = "/admin/reservations/setDeposit")
    public ModelAndView setDeposit(@RequestParam(name = "reservationId") String reservationId,
                                   @RequestParam(name = "deposit") int deposit,
                                   HttpSession session) {
        BalatoniSzelAdmin client = getAdminClient(session);
        if (client != null) {
            client.setDeposit(getToken(session), reservationId, deposit);
            return new ModelAndView("redirect:/admin");
        } else {
            return UNAUTH_VIEW;
        }
    }

    @GetMapping(path = "/admin/guests")
    public ModelAndView guests(HttpSession session) {
        BalatoniSzelAdmin client = getAdminClient(session);
        if (client != null) {
            ModelAndView adminGuests =  new ModelAndView("admin_guests");
            adminGuests.addObject("guests", client.guests(getToken(session)));
            return adminGuests;

        } else {
            return UNAUTH_VIEW;
        }

    }

    @PostMapping(path = "/admin/guests/setRegular")
    public ModelAndView setRegular(@RequestParam(name = "guestId") String guestId,
                                   @RequestParam(name = "regular") boolean regular,
                                   HttpSession session) {
        BalatoniSzelAdmin client = getAdminClient(session);
        if (client != null) {
            client.setRegular(getToken(session), guestId, regular);
            return new ModelAndView("redirect:/admin/guests");
        } else {
            return UNAUTH_VIEW;
        }

    }

    @GetMapping(path = "/admin/specialDays")
    public ModelAndView specialDays(HttpSession session) {
        BalatoniSzelAdmin client = getAdminClient(session);
        if (client != null) {
            ModelAndView      specialDaysView       = new ModelAndView("admin_specialDays");
            List<SpecialDays> specialDaysList = client.getSpecialDays(getToken(session));
            specialDaysView.addObject("specialDays", specialDaysList);
            return specialDaysView;
        } else {
            return UNAUTH_VIEW;
        }
    }

    @PostMapping(path="/admin/specialDays/add")
    public ModelAndView addSpecialDay(@RequestParam(name = "startDate") String startDate,
                                      @RequestParam(name = "endDate") String endDate,
                                      HttpSession session) {
        BalatoniSzelAdmin client = getAdminClient(session);
        if (client != null) {
            client.setSpecialDays(getToken(session), startDate, endDate);
            return new ModelAndView("redirect:/admin/specialDays");
        }else {
            return UNAUTH_VIEW;
        }
    }

    @PostMapping(path="/admin/specialDays/delete")
    public ModelAndView deleteSpecialDay(@RequestParam(name = "specialDayId") String id,
                                         HttpSession session) {
        BalatoniSzelAdmin client = getAdminClient(session);
        if (client != null) {
            client.deleteSpecialDays(getToken(session), id);
            return new ModelAndView("redirect:/admin/specialDays");
        } else {
            return UNAUTH_VIEW;
        }
    }
}
