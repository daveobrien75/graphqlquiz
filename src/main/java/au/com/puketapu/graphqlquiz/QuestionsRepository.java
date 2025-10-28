package au.com.puketapu.graphqlquiz;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionsRepository extends MongoRepository<Questions, String>, CustomQuestionRepository {

   List<Questions> findByCategory(String category);
    
}
