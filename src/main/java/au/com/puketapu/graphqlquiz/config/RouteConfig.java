package au.com.puketapu.graphqlquiz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import au.com.puketapu.graphqlquiz.handler.GraphQLHandler;

@Configuration
@EnableWebFlux
public class RouteConfig {
    @Bean
    RouterFunction<ServerResponse> routes(GraphQLHandler handler) {
        return route(POST("questions").and(accept(MediaType.APPLICATION_JSON)), handler::execute);
    }
}
