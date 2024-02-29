package cat.institutmarianao.user.impl;

import java.util.List;

//import jakarta.persistence.EntityManager;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import cat.institutmarianao.domain.User;
import cat.institutmarianao.service.UserService;
import cat.institutmarianao.service.impl.UserServiceImpl;
import cat.institutmarianao.user.mock.Mock;
import jakarta.inject.Inject;

@RunWith(Arquillian.class)
public class UserServiceTest {
	
	@Inject
	private UserService userService;

	@Deployment(testable = true)
	public static JavaArchive createTestableDeployment() {
		final JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "example.jar")
				.addClasses(UserService.class, UserServiceImpl.class)
				.addAsManifestResource("META-INF/persistence-test.xml", "persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
		return jar;
	}

	@Test
	public void findAllUsers() {

		User user0 = Mock.getUser0();
		User user1 = Mock.getUser1();

		userService.create(user0);
		userService.create(user1);

		List<User> users = userService.getAllUsers();
		Assert.assertEquals(2, users.size());

		userService.remove(user0);
		userService.remove(user1);
	}
	
	@Test
	public void createUser() {
	    User user0 = Mock.getUser0();
	    userService.create(user0);

	    User createdUser = userService.findUserByUsername(user0.getUsername());
	    Assert.assertNotNull(createdUser);
	    Assert.assertEquals(user0.getUsername(), createdUser.getUsername());
	    
		userService.remove(user0);
	    
	}
	
	@Test
	public void editUser() {
	    User user0 = Mock.getUser0();
	    userService.create(user0);

	    user0.setName("New Name");
	    userService.edit(user0);

	    User editedUser = userService.findUserByUsername(user0.getUsername());
	    Assert.assertEquals("New Name", editedUser.getName());
	    
	    userService.remove(user0);
	}

	@Test
	public void removeUser() {
	    User user0 = Mock.getUser0();
	    userService.create(user0);
	    
	    userService.remove(user0);
	    Assert.assertNotNull(userService.findUserByUsername(user0.getEmail())); 

	}
	
	@Test
	public void findActiveUsers() {
		
		User user0 = Mock.getUser0();
		User user1 = Mock.getUser1();
		
	    userService.create(user0);
	    userService.create(user1);

	    List<User> activeUsers = userService.findActiveUsers();

	    Assert.assertEquals(2, activeUsers.size());
	    
		userService.remove(user0);
		userService.remove(user1);
	}
	
	@Test
	public void findUserByUsername() {
	    User user0 = Mock.getUser0();
	    userService.create(user0);

	    User foundUser = userService.findUserByUsername(user0.getUsername());
	    Assert.assertEquals(user0.getUsername(), foundUser.getUsername());
	    
	    userService.remove(user0);
	}
	
	@Test
	public void findUserByEmail() {
	    User user0 = Mock.getUser0();
	    userService.create(user0);

	    User foundUser = userService.findUserByEmail(user0.getEmail());

	    Assert.assertEquals(user0.getEmail(), foundUser.getEmail());
	    Assert.assertEquals(user0.getUsername(), foundUser.getUsername());
	    userService.remove(user0);
	}

	

	
}
