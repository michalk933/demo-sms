package com.example.demosms.handler;

import com.example.demosms.DemoSmsApplication;
import com.example.demosms.routing.SmsRouterConfiguration;
import com.example.demosms.domain.SmsRequest;
import com.example.demosms.service.CustomerService;
import com.example.demosms.service.SmsService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import static com.example.demosms.util.PathUtils.path;
import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = DemoSmsApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SmsHandlerTest {

    @Autowired
    private SmsRouterConfiguration routerConfiguration;

    @Autowired
    private SmsHandler smsHandler;

    @MockBean
    private SmsService smsService;

    @MockBean
    private CustomerService customerService;

    private WebTestClient webClient;

    @Before
    public void init() {
        webClient = WebTestClient
                .bindToRouterFunction(routerConfiguration.smsRoutes(smsHandler))
                .build();
    }

    @Test
    public void sendSmsTest() {
        SmsRequest smsRequest = new SmsRequest("ALA MA KOTA");

        when(smsService.prepareSms(any(Mono.class), any(Mono.class)))
                .thenReturn(Mono.empty());

        webClient
                .post()
                .uri(path + "/sms/send/12")
                .body(Mono.just(smsRequest), SmsRequest.class)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void getListPhoneNumberTest() {
        when(customerService.getPhoneNumberByOwnerId(any(Mono.class)))
                .thenReturn(Flux.fromIterable(asList("793014938")));

        webClient
                .get()
                .uri(path + "/sms/number/phones/12")
                .exchange()
                .expectStatus()
                .isOk();
    }

}