package au.com.puketapu.graphqlquiz;

import java.util.List;

public interface CustomQuestionRepository {

   List<Questions> findByCategory(String category);
   List<Questions> findByCategoryDifficulty(String category, String difficulty);
    
}
