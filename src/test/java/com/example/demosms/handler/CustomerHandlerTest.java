package com.example.demosms.handler;

import com.example.demosms.DemoSmsApplication;
import com.example.demosms.domain.Customer;
import com.example.demosms.routing.CustomerRouterConfiguration;
import com.example.demosms.service.CustomerService;
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
import reactor.core.publisher.Mono;

import static com.example.demosms.util.PathUtils.path;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = DemoSmsApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerHandlerTest {

    @Autowired
    private CustomerRouterConfiguration customerRouterConfiguration;

    @Autowired
    private CustomerHandler customerHandler;

    @MockBean
    private CustomerService customerService;

    private WebTestClient webClient;

    @Before
    public void init() {
        webClient = WebTestClient
                .bindToRouterFunction(customerRouterConfiguration.customerRoutes(customerHandler))
                .build();
    }

    @Test
    public void addCustomer() {
        when(customerService.addCustomer(any(Mono.class)))
                .thenReturn(Mono.just(new Customer()));

        webClient
                .post()
                .uri(path + "/customer/add")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void getCustomerById() {
        when(customerService.getCustomerById(any(Mono.class)))
                .thenReturn(Mono.fromCallable(() -> Customer.of(
                        "123",
                        "name",
                        "123123123",
                        "123")
                ));

        webClient
                .get()
                .uri(path + "/customer/12")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void deleteCustomerById() {
        when(customerService.deleteCustomer(any(Mono.class)))
                .thenReturn(Mono.just(Void.class));

        webClient
                .delete()
                .uri(path + "/customer/delete/12")
                .exchange()
                .expectStatus()
                .isOk();
    }
}