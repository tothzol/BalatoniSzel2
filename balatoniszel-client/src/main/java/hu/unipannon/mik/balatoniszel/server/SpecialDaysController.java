package hu.unipannon.mik.balatoniszel.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class SpecialDaysController {

    private final BalatoniSzel client;

    @Autowired
    public SpecialDaysController(BalatoniSzel client) {
        this.client = client;
    }

    @GetMapping(path = "/specialDays")
    public ModelAndView specialDays() {
        ModelAndView      specialDaysView       = new ModelAndView("specialDays");
        List<SpecialDays> specialDaysList = client.getSpecialDays();
        specialDaysView.addObject("specialDays", specialDaysList);
        return specialDaysView;
    }

    @PostMapping(path="/addSpecialDay")
    public ModelAndView addSpecialDay(@RequestParam(name = "startDate") String startDate,
                                      @RequestParam(name = "endDate") String endDate) {
        client.setSpecialDays(startDate, endDate);
        return new ModelAndView("redirect:/specialDays");
    }

    @PostMapping(path="/deleteSpecialDay")
    public ModelAndView deleteSpecialDay(@RequestParam(name = "specialDayId") String id) {
        client.deleteSpecialDays(id);
        return new ModelAndView("redirect:/specialDays");
    }
}
