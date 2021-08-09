package com.richard.testBeanutils;

import com.richard.pojo.Course;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class TestBeanUtils {

    @Test
    public void test01() throws InvocationTargetException, IllegalAccessException {

        Course course = new Course();

        Map<String, Object> map = new HashMap<>();

        map.put("id",1);
        map.put("course_name","大数据");
        map.put("brief","课程半酣所有大数据流行技术");
        map.put("teacher_name","李宇杰");
        map.put("teacher_info","非著名不专业");

        BeanUtils.populate(course,map);

        System.out.println(course);

    }

}
