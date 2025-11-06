package au.com.puketapu.graphqlquiz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import au.com.puketapu.graphqlquiz.Questions;
import au.com.puketapu.graphqlquiz.respository.QuestionsRepository;

@Controller
public class QuestionsController {
    @Autowired
	private QuestionsRepository repository;
    
    @QueryMapping
    public List<Questions> getByCategory(@Argument String category) {
        return repository.findByCategory(category);
    }
}
