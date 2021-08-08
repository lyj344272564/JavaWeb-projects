package com.richard.service;


import com.richard.pojo.Course;

import java.util.List;

public interface CourseService {

    public List<Course> findCourseList();

    public List<Course> findByCourseNameAndStatus(String courseName, String status);

    public String saveCourseSalesInfo(Course course);
}
