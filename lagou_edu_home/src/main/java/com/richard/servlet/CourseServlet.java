package com.richard.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.richard.base.BaseServlet;
import com.richard.pojo.Course;
import com.richard.service.CourseService;
import com.richard.service.impl.CourseServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/course")
public class CourseServlet extends BaseServlet {

    CourseService courseService = new CourseServiceImpl();

    // 查询信息列表
    public void findCourseList(HttpServletRequest req, HttpServletResponse resp){

        try {
            List<Course> courseList = courseService.findCourseList();

            // 响应结果
            SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class,
                    "id","course_name","price","sort_num","status");

            String result = JSON.toJSONString(courseList,filter);

            resp.getWriter().println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 根据条件查询
    public void findByCourseNameAndStatus(HttpServletRequest req, HttpServletResponse resp) {

        try {
            //1.接收参数
            String courseName = req.getParameter("course_name");
            String status = req.getParameter("status");

            List<Course> courseList = courseService.findByCourseNameAndStatus(courseName, status);

            SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class,
                    "id","course_name","price","sort_num","status");

            String result = JSON.toJSONString(courseList, filter);

            resp.getWriter().println(result);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
