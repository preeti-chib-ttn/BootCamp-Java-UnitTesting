package com.demo.service;

import com.demo.domain.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertTrue;
@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTest {
    @InjectMocks
    EmailService emailService;

    @InjectMocks
    Order order;
    @Test
    public void testSendEmail(){
        assertTrue(EmailService.getInstance()
                .sendEmail(order,"Order Placed."));
    }

    @Test(expected = RuntimeException.class)
    public void testSendEmailWithoutMessage(){
        EmailService emailServiceInstance = new EmailService();
        emailServiceInstance.sendEmail(order);
    }
}