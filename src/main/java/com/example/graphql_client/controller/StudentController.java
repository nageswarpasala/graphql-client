package com.example.graphql_client.controller;

import com.example.graphql_client.model.StudentResponse;
import com.example.graphql_client.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get/{id}")
    public ResponseEntity<StudentResponse> getStudentDetails(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentService.getStudentDetails(id));
    }
}
