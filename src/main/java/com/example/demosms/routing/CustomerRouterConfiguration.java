package com.example.demosms.routing;

import com.example.demosms.handler.CustomerHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static com.example.demosms.util.PathUtils.path;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


/**
 * Router to service customer
 * <p>
 * Create by Michał Kuchciak
 */
@Configuration
public class CustomerRouterConfiguration {

    /**
     * Router for manage customer
     *
     * @param handler
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> customerRoutes(CustomerHandler handler) {
        return route(POST(path + "/customer/add"), handler::addCustomer)
                .andRoute(GET(path + "/customer/{customerId}"), handler::getCustomerById)
                .andRoute(DELETE(path + "/customer/delete/{customerId}"), handler::deleteCustomerById);
    }

}
