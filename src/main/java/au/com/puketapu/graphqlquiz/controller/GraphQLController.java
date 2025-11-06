package au.com.puketapu.graphqlquiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import au.com.puketapu.graphqlquiz.Questions;
import au.com.puketapu.graphqlquiz.respository.GraphQLRepository;
import reactor.core.publisher.Flux;

@Controller
public class GraphQLController {
    @Autowired
	private GraphQLRepository repository;
    
    @QueryMapping
    public Flux<Questions> getByCategory(@Argument String category) {
        return repository.getByCategory(category);
    }

    @QueryMapping
    public Flux<Questions> getByCategoryDifficulty(@Argument String category, @Argument String difficulty) {
        return repository.getByCategoryDifficulty(category, difficulty);
    }
}
