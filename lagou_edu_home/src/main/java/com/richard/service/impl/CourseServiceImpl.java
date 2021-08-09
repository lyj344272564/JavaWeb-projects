package com.richard.service.impl;

import com.richard.base.StatusCode;
import com.richard.dao.CourseDao;
import com.richard.dao.impl.CourseDaoImpl;
import com.richard.pojo.Course;
import com.richard.service.CourseService;
import com.richard.utils.DateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Course findCourseById(int id) {
        return courseDao.findCourseById(id);
    }

    @Override
    public String updateCourseSalesInfo(Course course) {

        int row = courseDao.updateCourseSalesInfo(course);

        if(row > 0){
            //保存成功
            String result = StatusCode.SUCCESS.toString();
            return result;
        }else{
            //保存失败
            String result = StatusCode.FAIL.toString();
            return result;
        }
    }

    @Override
    public Map<String, Integer> updateCourseStatus(Course course) {
        //调用dao
        int row = courseDao.updateCourseStatus(course);

        Map<String ,Integer> map = new HashMap<>();

        if(row > 0){

            if(course.getStatus() == 0){
                map.put("status",0);
            }else{
                map.put("status",1);
            }
        }

        return map;

    }
}
