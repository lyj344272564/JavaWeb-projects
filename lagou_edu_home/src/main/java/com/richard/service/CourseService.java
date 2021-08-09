package com.richard.service;


import com.richard.pojo.Course;

import java.util.List;
import java.util.Map;

public interface CourseService {

    public List<Course> findCourseList();

    public List<Course> findByCourseNameAndStatus(String courseName, String status);

    public String saveCourseSalesInfo(Course course);

    public Course findCourseById(int id);

    public String updateCourseSalesInfo(Course course);

    public Map<String,Integer> updateCourseStatus(Course course);
}
