package com.richard.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.richard.base.BaseServlet;
import com.richard.pojo.Course;
import com.richard.pojo.Course_Section;
import com.richard.service.CourseContentService;
import com.richard.service.impl.CourseContentServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@WebServlet("/courseContent")
public class CourseContentServlet extends BaseServlet {

    private CourseContentService courseContentService = new CourseContentServiceImpl();

    public void findSectionAndLessonByCourseId(HttpServletRequest request , HttpServletResponse response){

        try {

            String course_id = request.getParameter("course_id");

            List<Course_Section> sectionList = courseContentService.findSectionAndLessonByCourseId(Integer.parseInt(course_id));

            String result = JSON.toJSONString(sectionList);

            response.getWriter().println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void findCourseById(HttpServletRequest request , HttpServletResponse response) {

        try {

            String courseId = request.getParameter("course_id");

            Course course = courseContentService.findCourseById(Integer.parseInt(courseId));

            SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class,"id","course_name");

            String result = JSON.toJSONString(course,filter);

            response.getWriter().println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void saveOrUpdateSection(HttpServletRequest request , HttpServletResponse response) {

        try {

            //1.获取参数 从域对象中获取
            Map<String,Object> map = (Map)request.getAttribute("map");

            Course_Section section = new Course_Section();

            //3.使用BeanUtils工具类,将map中的数据封装到 section
            BeanUtils.populate(section,map);

            if (section.getId() == 0) {
                String result = courseContentService.saveSection(section);
                response.getWriter().print(result);
            } else {
                String result = courseContentService.updateSection(section);
                response.getWriter().println(result);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateSectionStatus(HttpServletRequest request , HttpServletResponse response) {

        try {

            int id = Integer.parseInt(request.getParameter("id"));
            int status = Integer.parseInt(request.getParameter("status"));

            String result = courseContentService.updateSectionStatus(id, status);

            response.getWriter().println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
