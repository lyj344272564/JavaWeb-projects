package com.richard.service.impl;

import com.richard.base.StatusCode;
import com.richard.dao.CourseContentDao;
import com.richard.dao.impl.CourseContentDaoImpl;
import com.richard.pojo.Course;
import com.richard.pojo.Course_Section;
import com.richard.service.CourseContentService;
import com.richard.utils.DateUtils;

import java.util.List;

public class CourseContentServiceImpl implements CourseContentService {

    private CourseContentDao courseContentDao = new CourseContentDaoImpl();

    @Override
    public List<Course_Section> findSectionAndLessonByCourseId(int courseId) {
        return courseContentDao.findSectionAndLessonByCourseId(courseId);
    }

    @Override
    public Course findCourseById(int courseId) {
        return courseContentDao.findCourseByCourseId(courseId);
    }

    @Override
    public String saveSection(Course_Section section) {

        section.setStatus(2);

        String date = DateUtils.getDateFormart();
        section.setCreate_time(date);
        section.setUpdate_time(date);

        int row = courseContentDao.saveSection(section);

        if (row > 0) {
            String result = StatusCode.SUCCESS.toString();
            return result;
        } else {
            String result = StatusCode.FAIL.toString();
            return result;
        }

    }

    @Override
    public String updateSection(Course_Section section) {

        String date = DateUtils.getDateFormart();
        section.setUpdate_time(date);

        int row = courseContentDao.updateSection(section);
        if (row > 0) {
            String result = StatusCode.SUCCESS.toString();
            return result;
        } else {
            String result = StatusCode.FAIL.toString();
            return result;
        }
    }

    @Override
    public String updateSectionStatus(int id, int status) {
        int i = courseContentDao.updateSectionStatus(id, status);
        if (i>0) {
            String result = StatusCode.SUCCESS.toString();
            return result;
        } else {
            String result = StatusCode.FAIL.toString();
            return result;
        }
    }

}
