package com.richard.dao;

import com.richard.dao.impl.CourseDaoImpl;
import com.richard.pojo.Course;
import com.richard.utils.DateUtils;

import java.util.List;

public class Test {

    CourseDao courseDao = new CourseDaoImpl();

    @org.junit.Test
    public void testFindCourseList() {
        List<Course> courseList = courseDao.findCourseList();
        System.out.println(courseList);
    }
    @org.junit.Test
    public void testFindByCourseNameAndStatus() {
        List<Course> courseList = courseDao.findByCourseNameAndStatus("微服务", "1");
        System.out.println(courseList);
    }

    @org.junit.Test
    public void testSaveCourseSalesInfo() {

        Course course = new Course();

        course.setCourse_name("爱你一万年");
        course.setCourse_description("爱你一万年");
        course.setTeacher_name("richard");
        course.setTeacher_info("richard牛逼");
        course.setPreview_first_field("共10讲");
        course.setPreview_second_field("每周日更新");
        course.setDiscounts(88.88);
        course.setPrice(188.0);
        course.setShare_image_title("6666");
        course.setShare_title("777");
        course.setShare_description("牛逼");
        course.setCourse_description("爱你一万年");

        course.setCourse_img_url("http://dsadsd");
        course.setStatus(1);
        String formart = DateUtils.getDateFormart();
        course.setCreate_time(formart);
        course.setUpdate_time(formart);

        int i = courseDao.saveCourseSalesInfo(course);

        System.out.println(i);

    }

}
