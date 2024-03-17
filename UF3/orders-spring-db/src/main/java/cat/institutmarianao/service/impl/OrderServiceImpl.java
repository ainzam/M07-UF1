package cat.institutmarianao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.institutmarianao.domain.Item;
import cat.institutmarianao.domain.Order;
import cat.institutmarianao.domain.OrderItem;
import cat.institutmarianao.domain.User;
import cat.institutmarianao.repository.OrderDao;
import cat.institutmarianao.repository.OrderItemDao;
import cat.institutmarianao.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;

	@Autowired
	private OrderItemDao orderItemDao;
	
	@Override
	public Order get(Long reference) {
		return orderDao.get(reference);
	}

	@Override
	public List<Order> getAll() {
		// TODO get all orders
		return orderDao.getAll();
	}

	@Override
	public List<Order> findByUser(User client) {
		// TODO find orders by client
		return orderDao.findByUser(client);
	}

	@Override
	public void save(Order order) {
		// TODO save order
		orderDao.save(order);
	}

	@Override
	public void setItemQuantity(Order order, Item item, int quantity) {
		// TODO set item quantity to an order
		OrderItem itemq = new OrderItem();
		itemq.setOrder(order);
		itemq.setItem(item);
		itemq.setQuantity(quantity);
		
		orderItemDao.save(itemq);
	}

	@Override
	public void addItemQuantity(Order order, Item item, int incQuantity) {
		// TODO add item quantity to an order
		OrderItem itemq = new OrderItem();
		itemq.setOrder(order);
		itemq.setItem(item);
		
		OrderItem itemExist = orderItemDao.get(itemq);
		itemExist.setQuantity( itemExist.getQuantity() + incQuantity );
		
		orderItemDao.update(itemExist);
		
	}

}
