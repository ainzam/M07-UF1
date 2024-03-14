package cat.institutmarianao.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import cat.institutmarianao.domain.Item;
import cat.institutmarianao.domain.Order;
import cat.institutmarianao.domain.OrderItem;
import cat.institutmarianao.domain.User;
import cat.institutmarianao.utils.Mock;
import jakarta.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath:/dao-test-context.xml" })
class OrderItemDaoTest {
	@Autowired
	private UserDao userDao;

	@Autowired
	private ItemDao itemDao;

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private OrderItemDao orderItemDao;

	/**
	 * Tests {@link OrderItemDao#get(OrderItemId)} method when the requested
	 * {@link OrderItemId} exists
	 */
	@Test
	@Transactional
	void getShouldReturnOrderItem() {
		/* Setup */
		/* Create user */
		String username = Mock.createRandomString(User.MAX_USERNAME);
		User user = Mock.createUser(username);
		userDao.save(user); /* Save the created user */

		/* Create an item */
		Item item = Mock.createItem();
		assertNull(item.getReference());
		itemDao.save(item); /* Save the created item */

		assertNotNull(item.getReference()); /* Item has reference */

		/* Prepare an order */
		Order order = Mock.createOrder(user);
		orderDao.save(order);
		assertNotNull(order.getReference()); /* Order has reference */

		/* The order-item does not exists in previously in the database */
		OrderItem orderItemId = new OrderItem();
		orderItemId.setOrder(order);
		orderItemId.setItem(item);
		/* Test the get method of the Dao */
		OrderItem orderItemFromDb = orderItemDao.get(orderItemId);
		assertNull(orderItemFromDb);

		/* Save the order-item */
		OrderItem orderItem = Mock.createOrderItem(order, item);
		orderItemDao.save(orderItem);

		/* Test the get method of the Dao */
		orderItemFromDb = orderItemDao.get(orderItemId);

		/* Verification - item exists in the database */
		assertNotNull(orderItemFromDb);
		assertEquals(orderItem, orderItemFromDb);
	}

	/**
	 * Tests {@link OrderItemDao#get(OrderItemId)} method when the requested
	 * {@link OrderItemId} exists
	 */
	@Test
	@Transactional
	void getShouldReturnNull() {
		/* Setup */
		/*
		 * Create user and save to the database - user must exist previously in the
		 * database
		 */
		String username = Mock.createRandomString(User.MAX_USERNAME);
		User user = Mock.createUser(username);
		userDao.save(user); /* Save the created user */

		/* Create an item */
		Item item = Mock.createItem();
		itemDao.save(item); /* Save the created item */

		assertNotNull(item.getReference()); /* Item has reference */

		/* Prepare an order */
		Order order = Mock.createOrder(user);
		orderDao.save(order);
		assertNotNull(order.getReference()); /* Order has reference */

		/* Prepare the id to get */
		OrderItem orderItemId = new OrderItem();
		orderItemId.setOrder(order);
		orderItemId.setItem(item);

		/* Test the get method of the Dao */
		OrderItem orderItemFromDb = orderItemDao.get(orderItemId);

		/* The order-item was not in the database */
		assertNull(orderItemFromDb);
	}

	@Test
	@Transactional
	void updateShouldUpdateQuantity() {
		/* Setup */
		/* Create user */
		String username = Mock.createRandomString(User.MAX_USERNAME);
		User user = Mock.createUser(username);
		userDao.save(user); /* Save the created user */

		/* Create an item */
		Item item = Mock.createItem();
		assertNull(item.getReference());
		itemDao.save(item); /* Save the created item */

		assertNotNull(item.getReference()); /* Item has reference */

		/* Prepare an order */
		Order order = Mock.createOrder(user);
		orderDao.save(order);
		assertNotNull(order.getReference()); /* Order has reference */

		/* Save the order-item */
		OrderItem orderItem = Mock.createOrderItem(order, item);
		orderItemDao.save(orderItem);

		/* Refresh the saved order-item */
		OrderItem orderItemFromDb = orderItemDao.get(orderItem);
		assertNotNull(orderItemFromDb);

		/* Prepare a new quantity */
		int newQuantity = orderItemFromDb.getQuantity() - 1;
		/* And check that this new quantity is not the stored quantity */
		assertNotEquals(newQuantity, orderItemFromDb.getQuantity());

		/* We prepare the update with another OrderItem object */
		OrderItem newOrderItem = new OrderItem();
		newOrderItem.setOrder(order);
		newOrderItem.setItem(item);
		newOrderItem.setQuantity(newQuantity);

		/* Test method */
		orderItemFromDb = orderItemDao.update(newOrderItem);

		/* Verification - order-item pair exists in the database */
		assertNotNull(orderItemFromDb);
		/* And has the new quantity */
		assertEquals(newQuantity, orderItemFromDb.getQuantity());
	}
}
