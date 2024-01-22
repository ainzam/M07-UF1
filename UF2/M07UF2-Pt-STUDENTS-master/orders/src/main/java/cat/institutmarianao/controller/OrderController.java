package cat.institutmarianao.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import cat.institutmarianao.domain.Item;
import cat.institutmarianao.domain.Order;
import cat.institutmarianao.domain.User;
import cat.institutmarianao.service.ItemService;
import cat.institutmarianao.service.OrderService;

//TODO - Configure Spring element and add mappings
@Controller
@RequestMapping("/users/orders")
public class OrderController {
	
	@Autowired
    private ItemService itemService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserDetailsService userDetailsService;

	@ModelAttribute("order")
	public Order setupOrder() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		User client = (User) userDetailsService.loadUserByUsername(username);

		Order order = new Order();
		order.setClient(client);
		return order;
	}
	@GetMapping
	public ModelAndView orders() {
		// TODO - get authenticated user here
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User client = (User) userDetailsService.loadUserByUsername(username);

		// TODO - get user orders
        Iterable<Order> orders = orderService.findByUser(client);
		 
		// TODO - Prepare the orders.jsp view and send user orders and Order.STATES as
		// parameter
        ModelAndView modelAndView = new ModelAndView("orders");
        modelAndView.addObject("orders", orders);
        modelAndView.addObject("states", Order.STATES);
		return modelAndView;
	}

	@GetMapping("/newOrder")
	public ModelAndView newOrder() {
		// TODO - Prepare the newOrder.jsp view and send all the available items
		// TODO - The new user order is in session
		ModelAndView modelAndView = new ModelAndView("newOrder");
        modelAndView.addObject("items", itemService.getAll());
        return modelAndView;
	}

	@GetMapping("/newOrder/clearItems")
	public String newOrderClearItems(@SessionAttribute("order") Order order) {

		order.clearItems();

		return "redirect:/users/orders/newOrder";
	}

	@GetMapping("/newOrder/increaseItem")
	public String newOrderIncreaseItem(@SessionAttribute("order") Order order,
			@RequestParam("reference") String reference) {

		// TODO - Get the item related to the reference passed as parameter
		
		Item item = itemService.getItem(reference);
		
		// TODO - Increase item quantity
		
		order.increaseQuantity(item);
		
		return "redirect:/users/orders/newOrder";
	}

	public String newOrderDecreaseItem(@SessionAttribute("order") Order order,
			@RequestParam("reference") String reference) {

		// TODO - Get the item related to the reference passed as parameter
		
		Item item = itemService.getItem(reference);
		
		// TODO - Decrease item quantity

		order.decreaseQuantity(item);
		return "redirect:/users/orders/newOrder";
	}

	@PostMapping("/newOrder/finishOrder")
	public String finishOrder() {
		// Nothing to do. We have order attibute in session, so the view can take it
		// from there
		return "finishOrder";
	}

	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
	}

	@GetMapping("/newOrder/finishOrder")
	public String finishOrder(@ModelAttribute("order") Order order, BindingResult bindingResult,
			SessionStatus sessionStatus) {

		if (bindingResult.hasErrors()) {
			return "finishOrder";
		}
		// TODO - Set a new reference for the order using Order.incReferenceSequence()
		order.setReference(Order.incReferenceSequence());
		// TODO - Set order start date to current date
		order.setStartDate(new Date());
		// TODO - Save order
		orderService.save(order);
		sessionStatus.setComplete(); // Clean session attributes - leave a new order ready in session

		return "redirect:/users/orders/";
	}
}
