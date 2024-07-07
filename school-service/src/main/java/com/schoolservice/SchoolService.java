package com.schoolservice;

import com.schoolservice.School;
import com.schoolservice.SchoolRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public School addSchool(School school) {
        return schoolRepository.save(school);
    }

    public List<School> fetchSchools() {
        return schoolRepository.findAll();
    }

    public School fetchSchoolById(int id) {
        return schoolRepository.findById(id).orElse(null);
    }
}
