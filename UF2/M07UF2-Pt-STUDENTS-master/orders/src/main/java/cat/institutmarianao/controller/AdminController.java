package cat.institutmarianao.controller;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cat.institutmarianao.domain.Order;
import cat.institutmarianao.service.OrderService;

//TODO - Configure Spring element and add mappings
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping("/orders")
	public ModelAndView orders() {

		// get all user orders
		
		Set<Order> orders = orderService.getAll();
		
		// Prepare the orders.jsp view and send user orders and Order.STATES as
		// STATES
		ModelAndView modelAndView = new ModelAndView("orders");
		modelAndView.addObject("orders", orders);
		modelAndView.addObject("states", Order.STATES);
		
		// parameter
		return modelAndView;
	}

	@GetMapping("/setDeliveryDate")
	public String setDeliveryDate(@RequestParam("reference") Integer reference,
			@RequestParam("deliveryDate") Date deliveryDate) {

		//  - Get the order related to the reference passed as parameter
		Order order = orderService.getByReference(reference);
		//  - Set the order delivery date with the deliveryDate value
		order.setDeliveryDate(deliveryDate);
		//  - Save the order
		orderService.save(order);
		
		return "redirect:/admin/orders";
	}

	@GetMapping("/setState")
	public String setState(@RequestParam("reference") Integer reference,
			@RequestParam("state") Integer state) {

		//  - Get the order related to the reference passed as parameter
		Order order = orderService.getByReference(reference);
		//  - Set the order state with the state value
		order.setState(state);
		//  - Save the order
		orderService.save(order);
		
		
		return "redirect:/admin/orders";
	}
}
