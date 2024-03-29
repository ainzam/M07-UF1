package cat.marianao.daw2.m07.uf3.service;

import java.util.List;

import cat.marianao.daw2.m07.uf3.domain.User;

public interface UserService {
    public void create(User user);
    public void edit(User user);
    public void remove(User user);
    public User findUserByUsername(String username);
    public List<User> findActiveUsers();
    public User findUserWithHighestRank();
}
