package au.com.puketapu.graphqlquiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class QuestionsHandler {
    @Autowired
	private QuestionsRepository repository;
	public Mono<ServerResponse> quizquestions(ServerRequest request) {
        
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters
            .fromValue(repository.findAll()));
	}
	public Mono<ServerResponse> categoryquestions(ServerRequest request) {

		String category = request.queryParam("category").orElse("General Knowledge");
		String difficulty = request.queryParam("difficulty").orElse("medium");
        
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters
            .fromValue(repository.findByCategoryDifficulty(category, difficulty)));
	}
}
