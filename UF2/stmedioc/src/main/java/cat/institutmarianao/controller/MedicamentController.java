package cat.institutmarianao.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cat.institutmarianao.domain.repository.MedicamentRepository;

@Controller
public class MedicamentController {

    @Autowired
    private MedicamentRepository medicamentRepository;

    @RequestMapping(value = "/medicaments", method = RequestMethod.GET)
    public ModelAndView handleRequest(HttpServletRequest request,
                                      HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView modelview = new ModelAndView("medicaments");
        modelview.getModelMap().addAttribute("medicaments", medicamentRepository.getAllMedicaments());
        return modelview;
    }
}
