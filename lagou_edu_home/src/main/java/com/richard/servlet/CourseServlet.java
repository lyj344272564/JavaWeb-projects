package com.richard.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.richard.base.BaseServlet;
import com.richard.pojo.Course;
import com.richard.service.CourseService;
import com.richard.service.impl.CourseServiceImpl;
import com.richard.utils.DateUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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

    public void findCourseById(HttpServletRequest request , HttpServletResponse response) {

        try {

            //1.接收参数
            String id = request.getParameter("id");

            //2.业务处理
            Course course = courseService.findCourseById(Integer.parseInt(id));

            //3.返回结果 响应JSON格式数据
            //使用 SimplePropertyPreFilter,指定要转换为JSON的字段
            SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class,"id","course_name","brief","teacher_name",
                    "teacher_info","preview_first_field","preview_second_field","discounts","price","price_tag","share_image_title","share_title","share_description","course_description");

            String result  = JSON.toJSONString(course, filter);
            response.getWriter().println(result);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void updateCourseStatus(HttpServletRequest request,HttpServletResponse response) {

        try {

            //1.获取参数
            String id = request.getParameter("id");

            // 2. 执行
            Course course = courseService.findCourseById(Integer.parseInt(id));

            int status = course.getStatus();

            if (status == 0) {
                course.setStatus(1);
            } else {
                course.setStatus(0);
            }

            //5.设置更新时间
            course.setUpdate_time(DateUtils.getDateFormart());

            Map<String, Integer> map = courseService.updateCourseStatus(course);

            String result = JSON.toJSONString(map);

            response.getWriter().print(result);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
