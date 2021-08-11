package com.richard.listener;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

@WebListener
public class MyContextListener implements ServletContextListener {

    //监听web容器的启动
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("webServer start");
    }

    //监听容器关闭
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        try {
            //System.out.println("webServer stop");
            //取消JDBC驱动注册
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            Driver driver =null;
            while(drivers.hasMoreElements()){
                driver = DriverManager.getDrivers().nextElement();
                DriverManager.deregisterDriver(driver);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {

            AbandonedConnectionCleanupThread.shutdown();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
