package au.com.puketapu.graphqlquiz.respository;

import java.util.List;

import au.com.puketapu.graphqlquiz.Questions;

public interface CustomQuestionRepository {

   List<Questions> findByCategory(String category);
   List<Questions> findByCategoryDifficulty(String category, String difficulty);
    
}
