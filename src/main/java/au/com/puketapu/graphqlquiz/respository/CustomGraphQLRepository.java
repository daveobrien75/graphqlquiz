package au.com.puketapu.graphqlquiz.respository;

import au.com.puketapu.graphqlquiz.Questions;
import reactor.core.publisher.Flux;

public interface CustomGraphQLRepository {

   Flux<Questions> getByCategory(String category);
   Flux<Questions> getByCategoryDifficulty(String category, String difficulty);
    
}
