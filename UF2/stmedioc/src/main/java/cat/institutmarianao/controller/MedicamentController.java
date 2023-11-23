package cat.institutmarianao.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cat.institutmarianao.domain.Medicament;

@Controller
public class MedicamentController {

    @RequestMapping(value = "/medicaments", method = RequestMethod.GET)
    public ModelAndView handleRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        ModelAndView modelview = new ModelAndView("medicaments");
        Medicament ibuprofe = new Medicament("M010", "Ibuprofé", 2);
        ibuprofe.setDescription("Ibuprofé de 600mg");
        ibuprofe.setCategory("Anti−inflamatori");
        ibuprofe.setProducer("Cinfa");
        ibuprofe.setStockQuantity(214);
        modelview.getModelMap().addAttribute("medicament", ibuprofe);
        return modelview;
    }
}
