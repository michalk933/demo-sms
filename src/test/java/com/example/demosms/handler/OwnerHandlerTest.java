package com.example.demosms.handler;

import com.example.demosms.DemoSmsApplication;
import com.example.demosms.domain.Customer;
import com.example.demosms.domain.Owner;
import com.example.demosms.routing.OwnerRouterConfiguration;
import com.example.demosms.service.OwnerService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.example.demosms.util.PathUtils.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = DemoSmsApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OwnerHandlerTest {

    @Autowired
    private OwnerRouterConfiguration ownerRouterConfiguration;

    @Autowired
    private OwnerHandler ownerHandler;

    @MockBean
    private OwnerService ownerService;

    private WebTestClient webClient;

    @Before
    public void init() {
        webClient = WebTestClient
                .bindToRouterFunction(ownerRouterConfiguration.ownerRouters(ownerHandler))
                .build();
    }

    @Test
    public void addOwner(){
        when(ownerService.addOwner(any(Mono.class)))
                .thenReturn(Mono.fromCallable(() -> Owner.of("name",
                        "123123123",
                        1L,
                        "12.12",
                        false)
                ));

        webClient
                .post()
                .uri(path + "/owner/add")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void deleteOwner(){
        when(ownerService.deleteOwner(any(Mono.class)))
                .thenReturn(Mono.fromCallable(() -> Void.class));

        webClient
                .delete()
                .uri(path + "/owner/delete/12")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void getOwnerByid(){
        when(ownerService.getOwnerById(any(Mono.class)))
                .thenReturn(Mono.fromCallable(() -> Owner.of("name",
                        "123123123",
                        1L,
                        "12.12",
                        false)
                ));

        webClient
                .get()
                .uri(path + "/owner/12")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void getCustomerByOwnerId(){
        when(ownerService.getCustomerByOwnerId(any(Mono.class)))
                .thenReturn(Flux.just(Customer.of(
                        "123",
                        "name",
                        "123123123",
                        "123")
                ));

        webClient
                .get()
                .uri(path + "/owner/customers/12")
                .exchange()
                .expectStatus()
                .isOk();
    }

}