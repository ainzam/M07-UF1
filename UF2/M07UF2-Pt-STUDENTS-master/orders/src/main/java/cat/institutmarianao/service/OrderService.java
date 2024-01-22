package cat.institutmarianao.service;

import java.util.Set;

import cat.institutmarianao.domain.Order;
import cat.institutmarianao.domain.User;

public interface OrderService {

    Set<Order> getAll();

    Order getByReference(Integer reference);

    Set<Order> findByUser(User user);

    void save(Order order);
}