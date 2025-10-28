package au.com.puketapu.graphqlquiz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class QuestionsController {
    @Autowired
	private QuestionsRepository repository;
    
    @QueryMapping
    public List<Questions> questionsByCategory(@Argument String category) {
        return repository.findByCategory(category);
    }
}
