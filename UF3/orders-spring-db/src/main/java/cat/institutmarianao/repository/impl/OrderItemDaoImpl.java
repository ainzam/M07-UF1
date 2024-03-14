package cat.institutmarianao.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cat.institutmarianao.domain.OrderItem;
import cat.institutmarianao.repository.OrderItemDao;
import jakarta.transaction.Transactional;

@Transactional
@Repository
public class OrderItemDaoImpl implements OrderItemDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public OrderItem get(OrderItem orderItem) {
		Session session = sessionFactory.getCurrentSession();

		return session.get(OrderItem.class, orderItem);
	}

	@Override
	public OrderItem update(OrderItem orderItem) {
		Session session = sessionFactory.getCurrentSession();
		return session.merge(orderItem);
	}

	@Override
	public void save(OrderItem orderItem) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(orderItem);
	}
}
