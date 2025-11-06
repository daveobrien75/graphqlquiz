package au.com.puketapu.graphqlquiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import au.com.puketapu.graphqlquiz.Questions;
import au.com.puketapu.graphqlquiz.respository.QuestionsRepository;
import reactor.core.publisher.Flux;

@Controller
public class QuestionsController {
    @Autowired
	private QuestionsRepository repository;
    
    @QueryMapping
    public List<Questions> getByCategory(@Argument String category) {
        return repository.findByCategory(category);
    }

    @QueryMapping
    public Flux<Questions> findByCategory(@Argument String category) {
        return Flux.fromIterable(repository.findByCategory(category))
                .map(questions -> new Questions(
                    questions._id(),
                    questions.category(),
                    questions.correct_answer(),
                    questions.difficulty(),
                    questions.incorrect_answers(),
                    questions.question(),
                    questions.type()
                ));
    }

    @QueryMapping
    public Flux<Questions> findByCategoryDifficulty(@Argument String category, @Argument String difficulty) {
        return Flux.fromIterable(repository.findByCategoryDifficulty(category, difficulty))
                .map(questions -> new Questions(
                    questions._id(),
                    questions.category(),
                    questions.correct_answer(),
                    questions.difficulty(),
                    questions.incorrect_answers(),
                    questions.question(),
                    questions.type()
                ));
    }
}
