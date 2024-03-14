package cat.institutmarianao.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import cat.institutmarianao.domain.User;
import cat.institutmarianao.repository.UserDao;
import cat.institutmarianao.utils.Mock;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath:/services-test-context.xml" })
class UserServiceTest {
	@Autowired
	private UserDao userDao;

	@Autowired
	private UserService userService;

	@Test
	void getUserOk() {
		/* Setup */
		String username = Mock.createRandomString(User.MAX_USERNAME);
		User user = Mock.createUser(username);

		when(userDao.get(username)).thenReturn(user);

		/* Test */
		User userFromDb = userService.get(username);

		/* Verification */
		assertEquals(username, userFromDb.getUsername());
		assertSame(user, userFromDb);
		verify(userDao, times(1)).get(username);
	}
}