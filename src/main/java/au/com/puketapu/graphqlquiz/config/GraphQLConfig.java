package au.com.puketapu.graphqlquiz.config;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import au.com.puketapu.graphqlquiz.resolver.GraphQLResolver;
import au.com.puketapu.graphqlquiz.respository.GraphQLRepository;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Configuration
public class GraphQLConfig {

    private final GraphQLResolver itemResolver;

    //@Autowired
    public GraphQLConfig(GraphQLResolver itemResolver) {
        this.itemResolver = itemResolver;
    }

    @Bean
    public GraphQL graphQL(RuntimeWiring runtimeWiring) throws IOException {
        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeDefinitionRegistry = new TypeDefinitionRegistry();

        // Load the GraphQL schema from the schema.graphqls file
        String schemaFile = new ClassPathResource("graphql/schema.graphqls").getFile().getAbsolutePath();
        typeDefinitionRegistry.merge(schemaParser.parse(new File(schemaFile)));

        SchemaGenerator schemaGenerator = new SchemaGenerator();
        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(
                typeDefinitionRegistry, runtimeWiring
        );

        return GraphQL.newGraphQL(graphQLSchema).build();
    }

    @Bean
    RuntimeWiring runtimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
            .type("Query", builder -> builder
                .dataFetcher("findQuestionsByCategoryDifficulty", this.itemResolver.getQuestionsByCategoryDifficulty())
                .dataFetcher("findQuestionsByCategory", this.itemResolver.getQuestionsByCategory())
            )
            .build();
    }
    
}
