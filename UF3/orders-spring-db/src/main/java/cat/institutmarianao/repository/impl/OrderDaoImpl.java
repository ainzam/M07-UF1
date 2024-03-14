package cat.institutmarianao.repository.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cat.institutmarianao.domain.Order;
import cat.institutmarianao.domain.User;
import cat.institutmarianao.repository.OrderDao;
import jakarta.transaction.Transactional;

@Transactional
@Repository
public class OrderDaoImpl implements OrderDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Order get(Long reference) {
		// TODO get order by reference
		return null;
	}

	@Override
	public List<Order> getAll() {
		// TODO get all orders
		return null;
	}

	@Override
	public List<Order> findByUser(User client) {
		// TODO find order by client
		return null;
	}

	@Override
	public void save(Order order) {
		// TODO save order
	}
}
