package com.example.graphql_client.model;

import lombok.Data;

import java.util.List;

@Data
public class StudentResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private List<LearningSubject> learningSubjects;
}
