package cat.institutmarianao.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cat.institutmarianao.domain.Order;
import cat.institutmarianao.service.ItemService;
import cat.institutmarianao.service.OrderService;

//TODO - Configure Spring element and add mappings
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private OrderService orderService;
	
	public ModelAndView orders() {

		// TODO - get all user orders
		
		Set<Order> orders = orderService.getAll();
		
		// TODO - Prepare the orders.jsp view and send user orders and Order.STATES as
		// STATES
		// TODO - parameter
		return null;
	}

	public String setDeliveryDate(/* TODO - Get reference parameter */
	/* TODO - Get deliveryDate parameter */) {

		// TODO - Get the order related to the reference passed as parameter
		// TODO - Set the order delivery date with the deliveryDate value
		// TODO - Save the order
		return "redirect:/admin/orders";
	}

	public String setState(/* TODO - Get reference parameter */
	/* TODO - Get state parameter */) {

		// TODO - Get the order related to the reference passed as parameter
		// TODO - Set the order state with the state value
		// TODO - Save the order
		return "redirect:/admin/orders";
	}
}
