package com.richard.dao;

import com.richard.pojo.Course;
import com.richard.pojo.Course_Lesson;
import com.richard.pojo.Course_Section;

import java.util.List;

public interface CourseContentDao {

    public List<Course_Section> findSectionAndLessonByCourseId(int courseId);

    public List<Course_Lesson> findLessonBySectionId(int sectionId);

    public Course findCourseByCourseId(int courseId);

    public int saveSection(Course_Section section);

    public int updateSection(Course_Section section);

    public int updateSectionStatus(int id,int status);

}
