package cat.marianao.daw2.m07.uf3.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cat.marianao.daw2.m07.uf3.domain.User;
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
	
	@Test
	public void editUser() {
	    // Crear un usuario nuevo
	    String username = "editedUser";
	    User user = new User();
	    user.setActive(true);
	    user.setCreatedOn(new Timestamp(new Date().getTime()));
	    user.setEmail("edit@test.com");
	    user.setName("Edited");
	    user.setPassword("password");
	    user.setRank(100);
	    user.setUsername(username);

	    // Iniciar transacción y crear usuario
	    entityTransaction.begin();
	    userService.create(user);
	    entityTransaction.commit();

	    // Modificar los datos del usuario
	    user.setEmail("updated@test.com");
	    user.setName("UpdatedName");

	    // Iniciar otra transacción y editar el usuario
	    entityTransaction.begin();
	    userService.edit(user);
	    entityTransaction.commit();

	    // Buscar el usuario editado en la base de datos
	    User editedUser = userService.findUserByUsername(username);

	    // Verificar que los cambios se realizaron correctamente
	    Assert.assertNotNull(editedUser);
	    Assert.assertEquals(username, editedUser.getUsername());
	    Assert.assertEquals("updated@test.com", editedUser.getEmail());
	    Assert.assertEquals("UpdatedName", editedUser.getName());
	}
	
	@Test
	public void removeUser() {
	    // Crear un usuario nuevo
	    String username = "toDelete";
	    User user = new User();
	    user.setActive(true);
	    user.setCreatedOn(new Timestamp(new Date().getTime()));
	    user.setEmail("delete@test.com");
	    user.setName("ToDelete");
	    user.setPassword("password");
	    user.setRank(100);
	    user.setUsername(username);

	    // Iniciar transacción y crear usuario
	    entityTransaction.begin();
	    userService.create(user);
	    entityTransaction.commit();

	    // Eliminar el usuario
	    entityTransaction.begin();
	    User managedUser = entityManager.merge(user); // Asociar la instancia con el contexto de persistencia
	    userService.remove(managedUser);
	    entityTransaction.commit();

	    // Verificar que el usuario se haya eliminado correctamente
	    try {
	        userService.findUserByUsername(username); // Intentar buscar el usuario eliminado
	        Assert.fail("Se esperaba NoResultException, pero no se lanzó"); // Si no se lanza NoResultException, la prueba falla
	    } catch (NoResultException e) {
	        // Se esperaba que se lanzara NoResultException, lo cual indica que el usuario fue eliminado correctamente
	    }
	}
	
	@Test
	public void findActiveUsers() {
	    // Crear usuarios activos e inactivos directamente dentro del método
	    User activeUser1 = new User();
	    activeUser1.setActive(true);
	    activeUser1.setCreatedOn(new Timestamp(new Date().getTime()));
	    activeUser1.setEmail("activeUser1@test.com");
	    activeUser1.setName("activeUser1");
	    activeUser1.setPassword("password");
	    activeUser1.setRank(100);
	    activeUser1.setUsername("activeUser1");

	    User activeUser2 = new User();
	    activeUser2.setActive(true);
	    activeUser2.setCreatedOn(new Timestamp(new Date().getTime()));
	    activeUser2.setEmail("activeUser2@test.com");
	    activeUser2.setName("activeUser2");
	    activeUser2.setPassword("password");
	    activeUser2.setRank(100);
	    activeUser2.setUsername("activeUser2");

	    User inactiveUser1 = new User();
	    inactiveUser1.setActive(false);
	    inactiveUser1.setCreatedOn(new Timestamp(new Date().getTime()));
	    inactiveUser1.setEmail("inactiveUser1@test.com");
	    inactiveUser1.setName("inactiveUser1");
	    inactiveUser1.setPassword("password");
	    inactiveUser1.setRank(100);
	    inactiveUser1.setUsername("inactiveUser1");

	    User inactiveUser2 = new User();
	    inactiveUser2.setActive(false);
	    inactiveUser2.setCreatedOn(new Timestamp(new Date().getTime()));
	    inactiveUser2.setEmail("inactiveUser2@test.com");
	    inactiveUser2.setName("inactiveUser2");
	    inactiveUser2.setPassword("password");
	    inactiveUser2.setRank(100);
	    inactiveUser2.setUsername("inactiveUser2");

	    // Guardar los usuarios en la base de datos
	    entityTransaction.begin();
	    userService.create(activeUser1);
	    userService.create(activeUser2);
	    userService.create(inactiveUser1);
	    userService.create(inactiveUser2);
	    entityTransaction.commit();

	    // Obtener usuarios activos
	    List<User> activeUsers = userService.findActiveUsers();

	    // Verificar que se devuelvan solo los usuarios activos
	    Assert.assertEquals(2, activeUsers.size());
	    Assert.assertTrue(activeUsers.contains(activeUser1));
	    Assert.assertTrue(activeUsers.contains(activeUser2));
	}
	
	@Test
	public void findUserWithHighestRank() {
	    // Crear usuarios con diferentes rangos directamente dentro del método
	    User user1 = new User();
	    user1.setActive(true);
	    user1.setCreatedOn(new Timestamp(new Date().getTime()));
	    user1.setEmail("user1@test.com");
	    user1.setName("user1");
	    user1.setPassword("password");
	    user1.setRank(100);
	    user1.setUsername("user1");

	    User user2 = new User();
	    user2.setActive(true);
	    user2.setCreatedOn(new Timestamp(new Date().getTime()));
	    user2.setEmail("user2@test.com");
	    user2.setName("user2");
	    user2.setPassword("password");
	    user2.setRank(150);
	    user2.setUsername("user2");

	    User user3 = new User();
	    user3.setActive(true);
	    user3.setCreatedOn(new Timestamp(new Date().getTime()));
	    user3.setEmail("user3@test.com");
	    user3.setName("user3");
	    user3.setPassword("password");
	    user3.setRank(80);
	    user3.setUsername("user3");

	    // Guardar los usuarios en la base de datos
	    entityTransaction.begin();
	    userService.create(user1);
	    userService.create(user2);
	    userService.create(user3);
	    entityTransaction.commit();

	    // Obtener usuario con el rango más alto
	    User userWithHighestRank = userService.findUserWithHighestRank();

	    // Verificar que se devuelva el usuario con el rango más alto
	    Assert.assertNotNull(userWithHighestRank);
	    Assert.assertEquals("user2", userWithHighestRank.getUsername());
	    Assert.assertEquals(150, userWithHighestRank.getRank(), 0);
	}
}
