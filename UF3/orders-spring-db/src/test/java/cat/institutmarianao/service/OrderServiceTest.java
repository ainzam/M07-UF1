package cat.institutmarianao.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import cat.institutmarianao.repository.OrderDao;
import cat.institutmarianao.repository.OrderItemDao;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath:/services-test-context.xml" })
class OrderServiceTest {
	@Autowired
	private OrderDao orderDao;

	@Autowired
	private OrderItemDao orderItemDao;

	@Autowired
	private OrderService orderService;

	@Test
	void getOrderOk() {
		// TODO test OrderService.get
	}

	@Test
	void getAllOk() {
		// TODO test OrderService.getAll
	}

	@Test
	void findByUserOk() {
		// TODO test OrderService.findByUser
	}

	@Test
	void saveOk() {
		// TODO test OrderService.save
	}

	@Test
	void setItemQuantityOk() {
		// TODO test OrderService.setItemQuantity
	}

	@Test
	void addItemQuantityOk() {
		// TODO test OrderService.addItemQuantity
	}
}