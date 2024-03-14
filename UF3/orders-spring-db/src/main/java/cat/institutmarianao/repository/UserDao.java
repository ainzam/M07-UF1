package cat.institutmarianao.repository;

import cat.institutmarianao.domain.User;

public interface UserDao {
	User get(String username);

	void save(User user);
}
