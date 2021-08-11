package com.richard.dao;

import com.richard.dao.impl.CourseContentDaoImpl;
import com.richard.pojo.Course_Lesson;
import com.richard.pojo.Course_Section;
import org.junit.Test;

import java.util.List;

public class TEstContent {

    private CourseContentDao contentDao = new CourseContentDaoImpl();

    @Test
    public void test() {

        List<Course_Section> sectionList = contentDao.findSectionAndLessonByCourseId(59);

        for (Course_Section section : sectionList) {

            System.out.println(section.getSection_name());

            List<Course_Lesson> lessonList = section.getLessonList();
            for (Course_Lesson lesson : lessonList) {
                System.out.println(lesson.getTheme());
            }

        }

    }


}
