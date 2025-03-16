package com.demo.service;

import com.demo.domain.Order;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Spy
    Order order;

    @Mock
    EmailService emailService;

    @InjectMocks
    OrderService orderService;

    @Before
    public void setup()
    {
        order=spy(new Order(13, "Test Order", 32121.21));
    }


    // email sent successfully and user is notified
    @Test
    public void returnsTrueWhenEmailSent() {
        when(emailService.sendEmail(any(Order.class), any(String.class))).thenReturn(true);
        assertTrue(orderService.placeOrder(order, "customer@mail.com"));
    }

    // email not sent successfully and user is not notified
    @Test
    public void returnsFalseWhenEmailFails() {
        when(emailService.sendEmail(any(Order.class), any(String.class))).thenReturn(false);
        assertFalse(orderService.placeOrder(order, "customer@mail.com"));
    }

    // tax is updated successfully
    @Test
    public void updatesPriceWithTaxOnOrder() {
        orderService.placeOrder(order, "customer@mail.com");
        assertNotEquals(0, order.getPriceWithTax());
    }

    // customer is notified
    @Test
    public void setsCustomerNotifiedWhenEmailSucceeds() {
        when(emailService.sendEmail(any(Order.class), any(String.class))).thenReturn(true);
        orderService.placeOrder(order, "customer@mail.com");
        verify(order, times(1)).setCustomerNotified(true);
    }


    // exception occur when email is not there
    @Test(expected = RuntimeException.class)
    public void throwsExceptionWhenEmailFails() {
        doThrow(new RuntimeException("An Exception Occurred")).when(emailService).sendEmail(any(Order.class));
        orderService.placeOrder(order);
    }

}