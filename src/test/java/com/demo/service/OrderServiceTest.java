package com.demo.service;
import com.demo.domain.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    private OrderService orderService;
    private Order order;

    @Before
    public void setup() {
        orderService = OrderService.getInstance();
        order = new Order();
        order.setPrice(100.0);
    }

    @Test
    public void testPlaceOrder() {
        orderService.placeOrder(order,"test@mail.com");
        assertEquals(120.0, order.getPriceWithTax(), 0.01);
        assertTrue(order.isCustomerNotified());
    }


    @Test(expected = RuntimeException.class)
    public void testPlacedOrderWithoutCustomerID() {
        orderService.placeOrder(order);
    }

}


