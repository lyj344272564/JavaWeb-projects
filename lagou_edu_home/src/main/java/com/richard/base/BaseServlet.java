package com.richard.base;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String methodName = req.getParameter("methodName");

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
//

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
