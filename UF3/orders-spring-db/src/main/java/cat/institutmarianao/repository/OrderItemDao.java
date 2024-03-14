package cat.institutmarianao.repository;

import cat.institutmarianao.domain.OrderItem;

public interface OrderItemDao {

	OrderItem get(OrderItem orderItem);

	OrderItem update(OrderItem orderItem);

	void save(OrderItem orderItem);

}
