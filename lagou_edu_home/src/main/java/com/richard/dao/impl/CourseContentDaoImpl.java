package com.richard.dao.impl;

import com.richard.dao.CourseContentDao;
import com.richard.pojo.Course;
import com.richard.pojo.Course_Lesson;
import com.richard.pojo.Course_Section;
import com.richard.utils.DateUtils;
import com.richard.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class CourseContentDaoImpl implements CourseContentDao {


    @Override
    public List<Course_Section> findSectionAndLessonByCourseId(int courseId) {

        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

            String sql = "SELECT \n" +
                    "id,\n" +
                    "course_id,\n" +
                    "section_name,\n" +
                    "description,\n" +
                    "order_num,\n" +
                    "STATUS\n" +
                    "FROM course_section WHERE course_id = ?";
            List<Course_Section> sectionList = qr.query(sql, new BeanListHandler<Course_Section>(Course_Section.class), courseId);

            for (Course_Section section : sectionList) {
                List<Course_Lesson> lessonList = findLessonBySectionId(section.getId());
                section.setLessonList(lessonList);
            }
            return sectionList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Course_Lesson> findLessonBySectionId(int sectionId) {
        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

            String sql = "SELECT \n" +
                    "id,\n" +
                    "course_id,\n" +
                    "section_id,\n" +
                    "theme,\n" +
                    "duration,\n" +
                    "is_free,\n" +
                    "order_num,\n" +
                    "STATUS\n" +
                    "FROM course_lesson WHERE section_id = ?";

            List<Course_Lesson> lessonList = qr.query(sql, new BeanListHandler<Course_Lesson>(Course_Lesson.class), sectionId);

            return lessonList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Course findCourseByCourseId(int courseId) {
        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

            String sql = "SELECT id,course_name FROM course WHERE id = ?";

            Course course = qr.query(sql, new BeanHandler<Course>(Course.class), courseId);

            return course;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int saveSection(Course_Section section) {
        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

            String sql = "INSERT INTO course_section(course_id,section_name,description,order_num,STATUS,create_time,update_time)\n" +
                    "VALUES(?,?,?,?,?,?,?);";
            Object[] param = {section.getCourse_id(),section.getSection_name(),section.getDescription(),
                    section.getOrder_num(),section.getStatus(),section.getCreate_time(),
                    section.getUpdate_time()};

            int row = qr.update(sql, param);

            return row;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateSection(Course_Section section) {
        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

            String sql = "UPDATE course_section SET\n" +
                    "section_name = ?,\n" +
                    "description = ?,\n" +
                    "order_num = ?,\n" +
                    "update_time = ? WHERE id = ?;";

            Object[] param = {section.getSection_name(),section.getDescription(),
                    section.getOrder_num(), section.getUpdate_time(),section.getId()};

            int row = qr.update(sql, param);

            return row;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateSectionStatus(int id, int status) {
        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

            String sql = "UPDATE course_section SET STATUS = ?,update_time = ? WHERE id = ?;";

            Object[] param = {status , DateUtils.getDateFormart(),id};

            int row = qr.update(sql, param);

            return row;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
