package com.richard.dao;

import com.richard.pojo.Course;

import java.util.List;

public interface CourseDao {

    public List<Course> findCourseList();

    public List<Course> findByCourseNameAndStatus(String courseName, String status);

    public int saveCourseSalesInfo(Course course);

    public Course findCourseById(int id);

    public int updateCourseSalesInfo(Course course);

    public int updateCourseStatus(Course course);

}
