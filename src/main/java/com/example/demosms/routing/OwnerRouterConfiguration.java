package com.example.demosms.routing;


import com.example.demosms.handler.OwnerHandler;
import com.example.demosms.util.PathUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Router to service owner
 * <p>
 * Create by Michał Kuchciak
 */
@Configuration
public class OwnerRouterConfiguration {

    /**
     * Router for manage owner
     *
     * @param handler
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> ownerRouters(OwnerHandler handler) {
        return RouterFunctions.route(RequestPredicates.POST(PathUtils.path + "/owner/add"), handler::addOwner)
                .andRoute(RequestPredicates.DELETE(PathUtils.path + "/owner/delete/{ownerId}"), handler::deleteOwner)
                .andRoute(RequestPredicates.GET(PathUtils.path + "/owner/{ownerId}"), handler::getOwnerById)
                .andRoute(RequestPredicates.GET(PathUtils.path + "/owner/customers/{ownerId}"), handler::getCustomerByOwnerId);
    }

}
