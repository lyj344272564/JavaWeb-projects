package com.richard.dao;

import com.richard.pojo.Course;

import java.util.List;

public interface CourseDao {

    public List<Course> findCourseList();

    public List<Course> findByCourseNameAndStatus(String courseName, String status);

    public int saveCourseSalesInfo(Course course);
}
