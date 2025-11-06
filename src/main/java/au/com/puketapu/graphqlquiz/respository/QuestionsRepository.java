package au.com.puketapu.graphqlquiz.respository;

import org.springframework.data.mongodb.repository.MongoRepository;

import au.com.puketapu.graphqlquiz.Questions;

public interface QuestionsRepository extends MongoRepository<Questions, String>, CustomQuestionRepository {
}
