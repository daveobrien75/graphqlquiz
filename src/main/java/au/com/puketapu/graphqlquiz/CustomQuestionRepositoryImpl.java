package au.com.puketapu.graphqlquiz;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import jakarta.annotation.Resource;

public class CustomQuestionRepositoryImpl implements CustomQuestionRepository {
    @Resource
    @Qualifier("questionsMongoTemplate")
    MongoTemplate mongoTemplate;

    @Override
    public List<Questions> findByCategoryDifficulty(String category, String difficulty) {
        Query query = new Query();
        query.addCriteria(Criteria.where("category").is(category)
            .andOperator(Criteria.where("difficulty").is(difficulty)));
        
        return mongoTemplate.find(query, Questions.class);
    }
    @Override
    public List<Questions> findByCategory(String category) {
        Query query = new Query();
        query.addCriteria(Criteria.where("category").is(category));
        
        return mongoTemplate.find(query, Questions.class);
    }
}
