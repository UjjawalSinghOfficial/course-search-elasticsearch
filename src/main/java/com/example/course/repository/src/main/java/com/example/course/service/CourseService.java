package com.example.course.service;

import com.example.course.model.CourseDocument;
import com.example.course.repository.CourseRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @PostConstruct
    public void loadCourses() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = new ClassPathResource("sample-courses.json").getInputStream();
        List<CourseDocument> courses = mapper.readValue(is, new TypeReference<List<CourseDocument>>() {});
        courseRepository.saveAll(courses);
    }
}
