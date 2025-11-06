package au.com.puketapu.graphqlquiz.handler;

import graphql.ExecutionInput;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Map;


@Component
public class GraphQLHandler {
    private final GraphQL graphQLBean;

    @Autowired
    public GraphQLHandler(GraphQL graphQL) {
        this.graphQLBean = graphQL;
    }

    public Mono<ServerResponse> execute(ServerRequest request) {
        return request.bodyToMono(Map.class)
                .flatMap(body -> {
                    String query = (String) body.get("query");
                    return this.executeGraphQL(query)
                            .flatMap(response -> ServerResponse.ok()
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .body(response, Object.class));
                })
                .onErrorResume(IOException.class, e ->
                        Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error occurred: " + e.getMessage())))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    private Mono<Object> executeGraphQL(String query) {
        return Mono.fromCallable(() -> this.graphQLBean.execute(ExecutionInput.newExecutionInput()
                        .query(query)
                        .build()))
                .map(executionResult -> Mono.justOrEmpty(executionResult.toSpecification().get("data")));
    }
}