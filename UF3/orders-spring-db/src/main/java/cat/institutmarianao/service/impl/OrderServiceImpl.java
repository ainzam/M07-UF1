package cat.institutmarianao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.institutmarianao.domain.Item;
import cat.institutmarianao.domain.Order;
import cat.institutmarianao.domain.User;
import cat.institutmarianao.repository.OrderDao;
import cat.institutmarianao.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;

	@Override
	public Order get(Long reference) {
		return orderDao.get(reference);
	}

	@Override
	public List<Order> getAll() {
		// TODO get all orders
		return null;
	}

	@Override
	public List<Order> findByUser(User client) {
		// TODO find orders by client
		return null;
	}

	@Override
	public void save(Order order) {
		// TODO save order
	}

	@Override
	public void setItemQuantity(Order order, Item item, int quantity) {
		// TODO set item quantity to an order
	}

	@Override
	public void addItemQuantity(Order order, Item item, int incQuantity) {
		// TODO add item quantity to an order
	}

}
