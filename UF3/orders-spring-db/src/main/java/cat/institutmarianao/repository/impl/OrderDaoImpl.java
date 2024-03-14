package cat.institutmarianao.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cat.institutmarianao.domain.Order;
import cat.institutmarianao.domain.User;
import cat.institutmarianao.repository.OrderDao;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Transactional
@Repository
public class OrderDaoImpl implements OrderDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Order get(Long reference) {
		// TODO get order by reference
		Session session = sessionFactory.getCurrentSession();
		return session.get(Order.class, reference);
	}

	@Override
	public List<Order> getAll() {
		// TODO get all orders
		Session session = sessionFactory.getCurrentSession();

		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Order> query = criteriaBuilder.createQuery(Order.class);

		Root<Order> orderRoot = query.from(Order.class);

		query.select(orderRoot);
		return session.createQuery(query).getResultList();
	}

	@Override
	public List<Order> findByUser(User client) {
		// TODO find order by client
	    Session session = sessionFactory.getCurrentSession();
	    CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
	    CriteriaQuery<Order> query = criteriaBuilder.createQuery(Order.class);
	    Root<Order> orderRoot = query.from(Order.class);

	    query.select(orderRoot)
	         .where(criteriaBuilder.equal(orderRoot.get("client_username"), client));

	    return session.createQuery(query).getResultList();
	}

	@Override
	public void save(Order order) {
		// TODO save order
		Session session = sessionFactory.getCurrentSession();
		session.persist(order);
	}
}