package com.richard.service.impl;

import com.richard.base.StatusCode;
import com.richard.dao.CourseDao;
import com.richard.dao.impl.CourseDaoImpl;
import com.richard.pojo.Course;
import com.richard.service.CourseService;
import com.richard.utils.DateUtils;

import java.util.List;

public class CourseServiceImpl implements CourseService {

    CourseDao courseDao = new CourseDaoImpl();

    @Override
    public List<Course> findCourseList() {
        return courseDao.findCourseList();
    }

    @Override
    public String saveCourseSalesInfo(Course course) {

        String formart = DateUtils.getDateFormart();

        course.setCreate_time(formart);
        course.setUpdate_time(formart);
        course.setStatus(1);

        int row = courseDao.saveCourseSalesInfo(course);

        if (row > 0) {
            String result = StatusCode.SUCCESS.toString();
            return result;
        } else {
            String result = StatusCode.FAIL.toString();
            return result;
        }

    }

    @Override
    public List<Course> findByCourseNameAndStatus(String courseName, String status) {
        return courseDao.findByCourseNameAndStatus(courseName,status);
    }
}
