package cat.institutmarianao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.institutmarianao.domain.User;
import cat.institutmarianao.repository.UserDao;
import cat.institutmarianao.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public User get(String username) {
		return userDao.get(username);
	}

}
