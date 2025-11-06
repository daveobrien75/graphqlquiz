package au.com.puketapu.graphqlquiz.respository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;

import au.com.puketapu.graphqlquiz.Questions;
import jakarta.annotation.Resource;
import reactor.core.publisher.Flux;

public class CustomGraphQLRepositoryImpl implements CustomGraphQLRepository {
    @Resource
    @Qualifier("questionsMongoTemplate")
    MongoTemplate mongoTemplate;

    @Autowired
	private QuestionsRepository repository;

    @Override
    public Flux<Questions> getByCategory(String category) {
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
    @Override
    public Flux<Questions> getByCategoryDifficulty(String category, String difficulty) {
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
