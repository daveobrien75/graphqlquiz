package au.com.puketapu.graphqlquiz.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import au.com.puketapu.graphqlquiz.Questions;
import au.com.puketapu.graphqlquiz.respository.QuestionsRepository;
import graphql.schema.DataFetcher;

@Component
public class GraphQLResolver {
    @Autowired
	private QuestionsRepository repository;

    public DataFetcher<List<Questions>> getQuestionsByCategoryDifficulty() {
        return environment -> {
            String category = environment.getArgument("category");
            String difficulty = environment.getArgument("difficulty");
            return repository.findByCategoryDifficulty(category, difficulty);
        };
    }
    public DataFetcher<List<Questions>> getQuestionsByCategory() {
        return environment -> {
            String category = environment.getArgument("category");
            return repository.findByCategory(category);
        };
    } 
}
