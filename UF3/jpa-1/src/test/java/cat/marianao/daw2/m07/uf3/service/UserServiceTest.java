package cat.marianao.daw2.m07.uf3.service;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cat.marianao.daw2.m07.uf3.domain.User;
import cat.marianao.daw2.m07.uf3.service.UserService;
import cat.marianao.daw2.m07.uf3.service.impl.UserServiceImpl;

public class UserServiceTest {
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;
	private UserService userService;

	@Before
	public void setUp() {
		entityManager = Persistence.createEntityManagerFactory("InMemoryH2PersistenceUnit").createEntityManager();
		userService = new UserServiceImpl(entityManager);
		entityTransaction = entityManager.getTransaction();
	}

	@After
	public void cleanUp() {
		entityManager.close();
	}

	@Test
	public void findAllUsers() {
		String username = "jdoe";
		User user = new User();
		user.setActive(true);
		user.setCreatedOn(new Timestamp(new Date().getTime()));
		user.setEmail("test@test.com");
		user.setName("Jane");
		user.setPassword("password");
		user.setRank(100);
		user.setUsername(username);
		User user1 = new User();
		user1.setActive(true);
		user1.setCreatedOn(new Timestamp(new Date().getTime()));
		user1.setEmail("test1@test.com");
		user1.setName("Joe");
		user1.setPassword("password");
		user1.setRank(100);
		user1.setUsername("joeTest");

		entityTransaction.begin();
		userService.create(user);
		userService.create(user1);
		entityTransaction.commit();

		User userFromDB = userService.findUserByUsername(username);
		Assert.assertNotNull(userFromDB);
		Assert.assertEquals("jdoe", userFromDB.getUsername());
		Assert.assertEquals("test@test.com", userFromDB.getEmail());
		Assert.assertNotNull(userFromDB.getUserId());
	}

}
