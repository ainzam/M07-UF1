package cat.institutmarianao.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cat.institutmarianao.domain.User;
import cat.institutmarianao.repository.UserDao;
import jakarta.transaction.Transactional;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User get(String username) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(User.class, username);
	}

	@Override
	public void save(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(user);
	}
}
