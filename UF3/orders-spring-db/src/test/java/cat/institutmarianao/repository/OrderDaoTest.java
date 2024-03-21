package cat.institutmarianao.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import cat.institutmarianao.domain.Order;
import cat.institutmarianao.domain.User;
import cat.institutmarianao.utils.Mock;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath:/dao-test-context.xml" })
class OrderDaoTest {
	@Autowired
	private UserDao userDao;
	@Autowired
	private OrderDao orderDao;

	@Test
	@Transactional
	void saveAndGetOk() {
        // Setup
        User user = Mock.createUser("testuser");
        Order order = Mock.createOrder(user);
        userDao.save(user);
        
        // Order does not exist in database
        assertNull(order.getReference());
        assertEquals(0, orderDao.getAll().size());

        // Test method - save order
        orderDao.save(order);

        // Verification - order exists and has the same reference
        assertNotNull(order.getReference());
        Order orderFromDb = orderDao.get(order.getReference());
        assertNotNull(orderFromDb);
        assertSame(order, orderFromDb);
    }

	@Test
	@Transactional
	void findAllOk() {
        // Setup
        User user = Mock.createUser("testuser");
        Order[] orders = { Mock.createOrder(user), Mock.createOrder(user), Mock.createOrder(user), Mock.createOrder(user) };

        
        // Orders do not exist in database
        for (Order order : orders) {
            assertNull(order.getReference());
        }

        // Test method - empty
        assertEquals(0, orderDao.getAll().size());

        // Setup - save orders
        for (Order order : orders) {
            orderDao.save(order);
        }

        // Test method - retrieve all
        Order[] ordersFromDb = orderDao.getAll().toArray(new Order[orders.length]);

        // Orders are all in the database
        assertEquals(orders.length, orderDao.getAll().size());

        // Verification - check all orders
        assertSame(orders.length, ordersFromDb.length);
        for (int i = 0; i < orders.length; i++) {
            assertSame(orders[i], ordersFromDb[i]);
        }
	}

	@Test
	@Transactional
	void findByUsernameOk() {
	    // Setup
	    String username = "testuser";
	    User user = Mock.createUser(username);
	    Order[] orders = { Mock.createOrder(user), Mock.createOrder(user), Mock.createOrder(user) };

	    // Orders do not exist in database
	    for (Order order : orders) {
	        assertNull(order.getReference());
	    }

	    // Save orders associated with the user
	    for (Order order : orders) {
	        orderDao.save(order);
	    }

	    // Test method - retrieve orders by username
	    List<Order> ordersByUsername = orderDao.findByUser(user);

	    // Verification
	    assertEquals(orders.length, ordersByUsername.size());

	    // Ensure all retrieved orders belong to the correct user
	    for (Order order : ordersByUsername) {
	        assertEquals(username, order.getClient().getUsername());
	    }
	}
}
