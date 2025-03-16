package com.demo.service;

import com.demo.domain.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTest {

    @Mock
    private Order order;

    @InjectMocks
    private EmailService emailService;

    @Test
    public void getInstanceShouldNotReturnNull() {
        assertNotNull(EmailService.getInstance());
    }

    @Test
    public void getInstanceShouldReturnSameInstance() {
        assertSame(EmailService.getInstance(), EmailService.getInstance());
    }

    @Test
    public void getInstanceShouldNotMatchNewInstance() {
        assertNotSame(EmailService.getInstance(), emailService);
    }

    @Test(expected = RuntimeException.class)
    public void sendEmailShouldThrowException() {
        emailService.sendEmail(order);
    }

    @Test
    public void sendEmailShouldReturnTrue() {
        assertTrue(emailService.sendEmail(order, "customer@mail.com"));
    }

    @Test
    public void shouldSetCustomerNotifiedWhenEmailSent() {
        emailService.sendEmail(order, "customer@mail.com");
        verify(order, times(1)).setCustomerNotified(true);
    }

    @Test
    public void shouldNotSetCustomerNotifiedWhenEmailFails() {
        emailService.sendEmail(order, "customer@mail.com");
        verify(order, never()).setCustomerNotified(false);
    }
}
