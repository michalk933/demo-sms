package com.example.demosms.service.impl;

import com.example.demosms.DemoSmsApplication;
import com.example.demosms.properties.TwilioProperties;
import com.example.demosms.service.CustomerService;
import com.example.demosms.service.TwilioService;
import com.google.common.cache.CacheLoader;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = DemoSmsApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ActiveProfiles("test")
public class SmsServiceImplTest {

    @MockBean
    private CustomerService customerService;

    @MockBean
    private TwilioService twilioService;

    @Autowired
    private TwilioProperties twilioProperties;

    @Test
    public void prepareSmsHappyPath() {
        MessageCreator messageCreator = Message.creator(
                new PhoneNumber("+48123123123"),
                new PhoneNumber(twilioProperties.getTrialNumber()),
                "test message"
        );

        when(customerService.getPhoneNumberByOwnerId(any(Mono.class)))
                .thenReturn(Flux.fromIterable(asList("793014938")));

        when(twilioService.sendSms(messageCreator))
                .thenReturn(Mono.empty());
    }

    @Test
    @ExceptionHandler(value = CacheLoader.InvalidCacheLoadException.class)
    public void prepareSmsCacheLoaderException(){
        MessageCreator messageCreator = Message.creator(
                new PhoneNumber("+48123123123"),
                new PhoneNumber(twilioProperties.getTrialNumber()),
                "test message"
        );

        when(customerService.getPhoneNumberByOwnerId(any(Mono.class)))
                .thenReturn(Flux.fromIterable(asList("793014938")));

        when(twilioService.sendSms(messageCreator))
                .thenThrow(new CacheLoader.InvalidCacheLoadException("Phone number is not a valid number"));

    }


}