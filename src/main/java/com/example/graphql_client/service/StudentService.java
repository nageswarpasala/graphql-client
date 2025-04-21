package com.example.graphql_client.service;

import com.example.graphql_client.enums.SubjectFilterEnum;
import com.example.graphql_client.model.StudentResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.graphql.GraphQlRequest;
import org.springframework.graphql.client.GraphQlClient;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    private final HttpGraphQlClient graphQlClient;

    StudentService(@Value("${spring.graphql.client}") String url){
        WebClient webClient = WebClient.builder().baseUrl(url).build();
        graphQlClient = HttpGraphQlClient.builder(webClient).build();
    }

    public StudentResponse getStudentDetails(Integer id) {

        Map<String, Object> variables = new HashMap<>();
        variables.put("id", id);
        variables.put("subjectFilter", List.of("All"));

        String document = """
                query getStudent($id : Int!, $subjectFilter: [SubjectFilterEnum!]!) {
                  getStudent(id: $id) {
                    id
                    firstName
                    lastName
                    email
                    learningSubjects(subjectFilter: $subjectFilter) {
                      id
                      subject
                      marksObtained
                    }
                  }
                }
                """;

        Mono<StudentResponse> getStudent = graphQlClient.document(document)
                .variables(variables)
                .retrieve("getStudent")
                .toEntity(StudentResponse.class);

        return getStudent.block();
    }
}
