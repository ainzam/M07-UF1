package cat.institutmarianao.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cat.institutmarianao.domain.Medicament;
import cat.institutmarianao.service.MedicamentService;

@Controller
@RequestMapping("/medicaments")
public class MedicamentController {

	@Autowired
	private MedicamentService medicamentService;

	@RequestMapping("/all")
	public ModelAndView allMedicaments(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ModelAndView modelview = new ModelAndView("medicaments");
		modelview.getModelMap().addAttribute("medicaments", medicamentService.getAllMedicaments());
		return modelview;
	}

	@RequestMapping("/{category}")
	public ModelAndView getMedicamentsByCategory(@PathVariable("category") String medicamentCategory,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModelAndView modelview = new ModelAndView("medicaments");
		modelview.getModelMap().addAttribute("medicaments",
				medicamentService.getMedicamentsByCategory(medicamentCategory));
		return modelview;
	}

	@RequestMapping("/filter/{ByCriteria}")
	public ModelAndView getMedicamentsByFilter(
			@MatrixVariable(pathVar = "ByCriteria") Map<String, List<String>> filterParams, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ModelAndView modelview = new ModelAndView("medicaments");
		modelview.getModelMap().addAttribute("medicaments", medicamentService.getMedicamentsByFilter(filterParams));
		return modelview;
	}

	@RequestMapping("/medicament")
	public ModelAndView getMedicamentById(@RequestParam("codi") String medicamentId, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ModelAndView modelview = new ModelAndView("medicament");
		modelview.getModelMap().addAttribute("medicament", medicamentService.getMedicamentById(medicamentId));
		return modelview;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView getAddNewMedicamentForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ModelAndView modelview = new ModelAndView("addMedicament");
		Medicament newMedicament = new Medicament();
		modelview.getModelMap().addAttribute("newMedicament", newMedicament);
		return modelview;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddNewMedicamentForm(@ModelAttribute("newMedicament") Medicament newMedicamentToAdd, BindingResult result) {
	    String[] suppressedFields = result.getSuppressedFields();
	    if (suppressedFields.length > 0) {
	        throw new RuntimeException("Attempting to bind disallowed fields: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
	    }
	    medicamentService.addMedicament(newMedicamentToAdd);
	    return "redirect:/medicaments/all";
	}


	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setDisallowedFields("stockInOrder");
	}

}
