package com.example.graphql_client.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LearningSubject {
    private int id;
    private String subject;
    private Double marksObtained;
}
