package cat.institutmarianao.service;

import java.util.List;

import cat.institutmarianao.domain.Item;
import cat.institutmarianao.domain.Order;
import cat.institutmarianao.domain.User;

public interface OrderService {
	List<Order> getAll();

	Order get(Long reference);

	List<Order> findByUser(User client);

	void save(Order order);

	void setItemQuantity(Order order, Item item, int incQuantity);

	void addItemQuantity(Order order, Item item, int incQuantity);

}
