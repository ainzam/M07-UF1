package cat.institutmarianao.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath:/dao-test-context.xml" })
class OrderDaoTest {
	@Autowired
	private UserDao userDao;
	@Autowired
	private OrderDao orderDao;

	@Test
	@Transactional
	void saveAndGetOk() {
		// TODO test OrderDao.save and OrderDao.get
	}

	@Test
	@Transactional
	void findAllOk() {
		// TODO test OrderDao.findAllOk
	}

	@Test
	@Transactional
	void findByUsernameOk() {
		// TODO test OrderDao.findByUsernameOk
	}
}
