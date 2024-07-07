package com.studentservice;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<?> fetchStudentById(@PathVariable String id) {
        return studentService.fetchStudentById(id);
    }

    @GetMapping
    public ResponseEntity<?> fetchStudents() {
        return studentService.fetchStudents();
    }

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

}
