package cat.institutmarianao.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

// - Configure Spring element and add mappings
@Controller
public class LoginController {
	
	@GetMapping(value = "/")
	public String check(HttpServletRequest request) throws ServletException, IOException {
		if (request.isUserInRole("ROLE_ADMIN")) {
			return "redirect:/admin/orders";
		}
		return "redirect:/users/orders";
	}
	
	@GetMapping(value = "/login")
	public String login() {
		return "login";
	}
	
	@GetMapping(value = "/loginfailed")
	public String loginFailed(Model model) {
		model.addAttribute("error", true);
		return "login";
	}
	
    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }
	
}
