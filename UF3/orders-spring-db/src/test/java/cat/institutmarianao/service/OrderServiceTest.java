package cat.institutmarianao.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import cat.institutmarianao.domain.Order;
import cat.institutmarianao.domain.User;
import cat.institutmarianao.repository.OrderDao;
import cat.institutmarianao.repository.OrderItemDao;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath:/services-test-context.xml" })
class OrderServiceTest {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderItemDao orderItemDao;

    @Autowired
    private OrderService orderService;

    @Test
    void getOrderOk() {
        /* Setup */
        Long reference = new Random().nextLong();
        Order order = mock(Order.class);
        
        when(orderDao.get(reference)).thenReturn(order);
        
        /* Test */
        Order orderFromDb = orderService.get(reference);
        
        /* Verificación */
        assertEquals(order, orderFromDb);
        verify(orderDao, times(1)).get(reference);
    }

    @Test
    void getAllOk() {
        /* Mock de OrderDao */
        List<Order> orders = new ArrayList<>();
        orders.add(mock(Order.class));
        orders.add(mock(Order.class));
        when(orderDao.getAll()).thenReturn(orders);
        
        /* Test */
        List<Order> ordersFromDb = orderService.getAll();

        /* Verificación */
        assertEquals(orders, ordersFromDb);
        verify(orderDao, times(1)).getAll();
    }

    @Test
    void findByUserOk() {
        /* Mock de OrderDao */
        User user = mock(User.class);
        List<Order> orders = new ArrayList<>();
        orders.add(mock(Order.class));
        when(orderDao.findByUser(user)).thenReturn(orders);
        
        /* Test */
        List<Order> ordersFromDb = orderDao.findByUser(user);

        /* Verificación */
        assertEquals(orders, ordersFromDb);
        verify(orderDao, times(1)).findByUser(user);
    }

    @Test
    void saveOk() {
        /* Mock de OrderDao */
        Order order = mock(Order.class);
        
        /* Verificación */
        orderService.save(order);
        verify(orderDao, times(1)).save(order);
    }

    @Test
    void setItemQuantityOk() {
        // Mock
    }


    @Test
    void addItemQuantityOk() {
    	//
    }

}
