package cat.institutmarianao.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import cat.institutmarianao.domain.User;
import cat.institutmarianao.utils.Mock;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath:/dao-test-context.xml" })
class UserDaoTest {
	@Autowired
	private UserDao userDao;

	@Test
	@Transactional
	void saveAndGetOk() {
		/* Setup */
		String username = Mock.createRandomString(User.MAX_USERNAME);
		User user = Mock.createUser(username);

		/* User does not exists in database */
		User userFromDb = userDao.get(username);
		assertNull(userFromDb);

		/* Test methods */
		userDao.save(user);
		userFromDb = userDao.get(username);

		/* Verification - user exists and has the same values */
		assertNotNull(userFromDb);
		assertSame(user, userFromDb);
	}
}
