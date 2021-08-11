package com.richard.base;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String methodName = null;

        // 获取Content-Type类型
        String contentType = req.getHeader("Content-Type");

        if("application/json;charset=utf-8".equals(contentType)) {
            //是JOSN格式 调用getPostJSON
            String postJSON = getPostJSON(req);

            //将JSON格式的字符串转化为map
            Map<String, Object> map = JSON.parseObject(postJSON, Map.class);

            //从map集合中获取 methodName
            methodName =(String) map.get("methodName");

            //将获取到的数据,保存到request域对象中
            req.setAttribute("map",map);

        } else {
            methodName = req.getParameter("methodName");
        }

        if (methodName != null) {
            // 通过反射优化代码 提升代码可维护性

            // 获取字节码文件对象 this=TestServlet
            Class c = this.getClass();

            try {
                // 根据传入方法名，获取对应的方法对象
                Method method = c.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
                // 调用method对象的invoke方法，执行对应功能
                method.invoke(this,req,resp);

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("请求功能不存在！");
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    /***
     * POST请求格式为 application/json;charset=utf-8
     * * 在这个方法中我们使用流的方式,获取到POST请求的数据 * */
    public String getPostJSON(HttpServletRequest request) {

        try {
            //1.从request中获取 字符缓冲输入流对象
            BufferedReader reader = request.getReader();

            //2.创建 StringBuffer,用来保存读取出的数据
            StringBuffer sb = new StringBuffer();

            //3.循环读取
            String line = null;
            while ((line = reader.readLine()) != null) {
                // 每次读取的数据追加
                sb.append(line);
            }

            //4.将读取到的内容转换为字符串,并返回
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
