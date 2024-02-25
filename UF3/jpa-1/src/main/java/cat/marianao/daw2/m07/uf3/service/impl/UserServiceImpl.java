package cat.marianao.daw2.m07.uf3.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import cat.marianao.daw2.m07.uf3.domain.User;
import cat.marianao.daw2.m07.uf3.service.UserService;

public class UserServiceImpl implements UserService {
	private EntityManager entityManager;

	public UserServiceImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void create(User user) {
		entityManager.persist(user);
	}

	@Override
	public void edit(User user) {
		entityManager.merge(user);
	}

	@Override
	public void remove(User user) {
		entityManager.remove(user);
	}

	@Override
	public User findUserByUsername(String username) {
		return (User) entityManager.createQuery("select object(o) from User o " + "where o.username = :username")
				.setParameter("username", username).getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findActiveUsers() {
	    Query query = entityManager.createQuery("select object(u) from User u where u.active = true");
	    return query.getResultList();
	}
	
	@Override
	public User findUserWithHighestRank() {
        return (User) entityManager.createQuery("SELECT object(u) FROM User u ORDER BY u.rank DESC")
                                  .setMaxResults(1)
                                  .getSingleResult();
	}
	
}
