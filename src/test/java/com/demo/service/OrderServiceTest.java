package com.demo.service;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
    @InjectMocks
    OrderService orderService;

    @InjectMocks
    EmailService emailService;



}


