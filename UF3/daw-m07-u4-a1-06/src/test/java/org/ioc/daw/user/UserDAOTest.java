package org.ioc.daw.user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.ioc.daw.db.DBConnection;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserDAOTest {


    private DBConnection dBConnection;
    private String connectionProperties = "db_test.properties";
    UserDAO userDAO;

    @Before
    public void setUp() {
        dBConnection = new DBConnection(connectionProperties);
        userDAO = new UserDAO(dBConnection);
    }

    @After
    public void tearDown() throws IOException, SQLException {
        userDAO.getConnection().close();
    }

    @Test
    public void findAllUsers() throws SQLException {
        List<User> users = userDAO.findAllUsers();
        Assert.assertEquals("Hauriem de tenir 2 usuaris a la base de dades", 2, users.size());
    }

    @Test
    public void findUserByEmail() throws Exception {
        String existingEmail = "john@email.com";
        String unknownEmail = "does.not@exist.com";

        User user = userDAO.findUserByEmail(existingEmail);
        Assert.assertNotNull(user);
        user = userDAO.findUserByEmail(unknownEmail);
        Assert.assertNull(user);
    }

    @Test
    public void findUserByUsername() throws Exception {
        String existingUsername = "user1";
        String unknownUsername = "unknown";

        User user = userDAO.findUserByUsername(existingUsername);
        Assert.assertNotNull(user);
        user = userDAO.findUserByUsername(unknownUsername);
        Assert.assertNull(user);
    }

    @Test
    public void createUser() throws Exception {
        String username = "testUser";
        String name = "Pete Test";
        String email = "pete@email.com";
        String password = "securePassword";  // Agrega la contraseña aquí
        User createdUser = userDAO.createUser(username, name, email, password);
        Assert.assertNotNull(createdUser);
        Assert.assertEquals(username, createdUser.getUsername());
        Assert.assertNotEquals(0, createdUser.getUserId());
    }

    @Test
    public void createUserEscapesSQLCharacters() throws Exception {
        String username = "sl','sls";
        String name = "Pete Test";
        String email = "pete@email.com";
        String password = "securePassword";  // Agrega la contraseña aquí
        User createdUser = userDAO.createUser(username, name, email, password);
        Assert.assertNotNull(createdUser);
    }

    @Test
    public void updateUserEmail() throws Exception {
        String username = "testUser";
        String name = "Pete Test";
        String email = "pete@email.com";
        String password = "securePassword";  // Agrega la contraseña aquí
        User createdUser = userDAO.createUser(username, name, email, password);
        Assert.assertNotNull(createdUser);
        Assert.assertEquals(email, createdUser.getEmail());
        User updatedUser = userDAO.updateUserEmail(createdUser, "new@email.com");
        Assert.assertEquals(createdUser.getUserId(), updatedUser.getUserId());
        Assert.assertEquals("new@email.com", updatedUser.getEmail());
    }

    @Test
    public void deleteUser() throws Exception {
        String username = "testUser";
        String name = "Pete Test";
        String email = "pete@email.com";
        String password = "securePassword";  // Agrega la contraseña aquí
        User createdUser = userDAO.createUser(username, name, email, password);
        Assert.assertNotNull(createdUser);
        userDAO.deleteUser(createdUser);
        User deletedUser = userDAO.findUserByUsername(username);
        Assert.assertNull(deletedUser);
    }
    
  //Nou test per a la funcionalitat de desactivar l'usuari
    @Test
    public void deactivateUser() throws Exception {
        String username = "testUser";
        String name = "Pete Test";
        String email = "pete@email.com";
        String password = "securePassword";  // Agrega la contraseña aquí
        User createdUser = userDAO.createUser(username, name, email, password);
        Assert.assertNotNull(createdUser);

        userDAO.deactivateUser(createdUser);  // Añade la contraseña aquí

        User deactivatedUser = userDAO.findUserByUsername(username);
        Assert.assertNotNull(deactivatedUser);
        Assert.assertFalse(deactivatedUser.isActive());
    }

    //Nou test per a la funcionalitat de recuperar tots els usuaris actius
    @Test
    public void findActiveUsers() throws SQLException {
        List<User> allUsers = userDAO.findAllUsers();
        Assert.assertFalse(allUsers.isEmpty());

        List<User> activeUsers = userDAO.findActiveUsers();
        Assert.assertFalse(activeUsers.isEmpty());

        // Assegura't que la llista d'usuaris actius és més petita o igual que la llista completa
        Assert.assertTrue(activeUsers.size() <= allUsers.size());

        // Comprova que tots els usuaris a la llista d'usuaris actius estiguin actius
        for (User user : activeUsers) {
            Assert.assertTrue(user.isActive());
        }
    }

    //Nou test per a la funcionalitat d'actualitzar el 'ranking' d'un usuari
    @Test
    public void updateUserRanking() throws Exception {
        String username = "testUser";
        String name = "Pete Test";
        String email = "pete@email.com";
        String password = "securePassword";  // Agrega la contraseña aquí
        int initialRanking = 5;
        User createdUser = userDAO.createUser(username, name, email, password);
        Assert.assertNotNull(createdUser);

        userDAO.updateUserRanking(createdUser, initialRanking);  // Añade la contraseña aquí

        User updatedUser = userDAO.findUserByUsername(username);
        Assert.assertNotNull(updatedUser);
        Assert.assertEquals(initialRanking, updatedUser.getRank());
    }

    // Test para el método updateUserPassword
    @Test
    public void updateUserPassword() throws Exception {
        // Datos del usuario de prueba
        String username = "testUser";
        String name = "Pete Test";
        String email = "pete@email.com";
        String password = "securePassword";
        
        // Crear un usuario con contraseña
        User createdUser = userDAO.createUser(username, name, email, password);
        Assert.assertNotNull(createdUser);

        // Verificar que la contraseña inicial es correcta
        Assert.assertEquals(password, createdUser.getPassword());

        // Nueva contraseña
        String newPassword = "newSecurePassword";

        // Actualizar la contraseña del usuario
        userDAO.updateUserPassword(createdUser, newPassword);

        // Obtener el usuario actualizado de la base de datos
        User updatedUser = userDAO.findUserByUsername(username);
        
        // Verificar que la contraseña se ha actualizado correctamente
        Assert.assertNotNull(updatedUser);
        Assert.assertEquals(newPassword, updatedUser.getPassword());
    }


}
