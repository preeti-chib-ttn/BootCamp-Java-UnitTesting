package com.demo.service;
import com.demo.domain.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertTrue;
@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTest {

    private EmailService emailService;
    private Order order;

    @Before
    public void setup(){
        emailService= EmailService.getInstance();
        order= new Order();
    }
    @Test
    public void testSendEmail(){
        assertTrue(emailService
                .sendEmail(order,"Order Placed."));
    }

    @Test(expected = RuntimeException.class)
    public void testSendEmailWithoutMessage(){
        emailService.sendEmail(order);
    }
}