package com.example.demosms.routing;

import com.example.demosms.handler.SmsHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static com.example.demosms.util.PathUtils.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


/**
 * Router to service sms
 *
 * Create by Michał Kuchciak
 */
@Configuration
public class SmsRouterConfiguration {

    /**
     * Router for send sms
     *
     * @param handler
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> smsRoutes(SmsHandler handler) {
        return route(POST(path + "/sms/send/{ownerId}"), handler::sendSms)
                .andRoute(GET(path + "/sms/number/phones/{ownerId}"), handler::getNumberPhoneForOwner);
    }

}
