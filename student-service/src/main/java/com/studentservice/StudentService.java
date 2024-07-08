package com.studentservice;

import com.studentservice.dto.School;
import com.studentservice.dto.StudentResponse;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final RestTemplate restTemplate;

    public ResponseEntity<?> createStudent(Student student) {
        try {
            return new ResponseEntity<Student>(studentRepository.save(student), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> fetchStudentById(String id) {
        return studentRepository.findById(id)
                .map(student -> {
                    val school = restTemplate.getForObject("http://localhost:8080/school/" + student.getSchoolId(), School.class);
                    val studentResponse = new StudentResponse(
                            student.getId(),
                            student.getName(),
                            student.getAge(),
                            student.getGender(),
                            school
                    );
                    return new ResponseEntity<>(studentResponse, HttpStatus.OK);
                })
                .or(() -> (Optional<? extends ResponseEntity<StudentResponse>>) Optional.of(new ResponseEntity<>("No Student Found",HttpStatus.NOT_FOUND)))
                .get();
    }

    public ResponseEntity<?> fetchStudents() {
        val students = studentRepository.findAll();
        if (!students.isEmpty()) {
            return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Students", HttpStatus.NOT_FOUND);
        }
    }

}
