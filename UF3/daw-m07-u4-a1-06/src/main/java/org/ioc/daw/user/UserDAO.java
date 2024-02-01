package org.ioc.daw.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.ioc.daw.db.DBConnection;

public class UserDAO {
    private DBConnection dBConnection;
    private Connection connection;

    public UserDAO(DBConnection dBConnection) {
        this.dBConnection = dBConnection;
    }

    public List<User> findAllUsers() throws SQLException {
        String qry = "select user_id, username, name, email, rank, active, created_on from users";
        PreparedStatement preparedStatement = getPreparedStatement(qry);
        List<User> users = executeQuery(preparedStatement);
        return users;
    }

    public User findUserByEmail(String userEmail) throws Exception {
        String qry = "select * from users where email = ?";
        PreparedStatement preparedStatement = getPreparedStatement(qry);
        preparedStatement.setString(1, userEmail);
        return findUniqueResult(preparedStatement);
    }

    public User findUserByUsername(String username) throws Exception {
        String qry = "select * from users where username =?";
        PreparedStatement preparedStatement = getPreparedStatement(qry);
        preparedStatement.setString(1, username);
        return findUniqueResult(preparedStatement);
    }

    public User createUser(String username, String name, String email) throws Exception {
        String qry = "INSERT INTO users (username, name, email) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = getPreparedStatement(qry);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, email);
        return createOrUpdateUser(username, preparedStatement);
    }

    public User updateUserEmail(User user, String newEmail) throws Exception {
        String qry = "UPDATE users SET email = ?  WHERE user_id = ? ";
        PreparedStatement preparedStatement = getPreparedStatement(qry);
        preparedStatement.setString(1, newEmail);
        preparedStatement.setInt(2, user.getUserId());
        return createOrUpdateUser(user.getUsername(), preparedStatement);
    }


    private User createOrUpdateUser(String username, PreparedStatement preparedStatement) throws Exception {
        int result = executeUpdateQuery(preparedStatement);
        if (result == 0) {
            throw new Exception("Error creating user");
        }
        return findUserByUsername(username);
    }

    public void deleteUser(User user) throws Exception {
        String qry = "DELETE FROM users WHERE user_id = ?";
        PreparedStatement preparedStatement = getPreparedStatement(qry);
        preparedStatement.setInt(1, user.getUserId());
        createOrUpdateUser(user.getUsername(), preparedStatement);
    }

    private User findUniqueResult(PreparedStatement preparedStatement) throws Exception {
        List<User> users = executeQuery(preparedStatement);
        if (users.isEmpty()) {
            return null;
        }
        if (users.size() > 1) {
            throw new Exception("Only one result expected");
        }
        return users.get(0);
    }

    private List<User> executeQuery(PreparedStatement preparedStatement) {
        List<User> users = new ArrayList<>();

        try (
                ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                User user = buildUserFromResultSet(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    private PreparedStatement getPreparedStatement(String query) throws SQLException {
        if (getConnection() == null) {
            try {
                setConnection(dBConnection.getConnection());
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
        return getConnection().prepareStatement(query);
    }


    private int executeUpdateQuery(PreparedStatement preparedStatement) {
        int result = 0;
        if (getConnection() == null) {
            try {
                setConnection(dBConnection.getConnection());
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
        try {
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private User buildUserFromResultSet(ResultSet rs) throws SQLException {
        int userId = rs.getInt("user_id");
        String username = rs.getString("username");
        String name = rs.getString("name");
        String email = rs.getString("email");
        int rank = rs.getInt("rank");
        boolean active = rs.getBoolean("active");
        Timestamp timestamp = rs.getTimestamp("created_on");
        User user = new User(userId, username, name, email, rank, timestamp, active);
        return user;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
