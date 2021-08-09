package com.richard.servlet;

import org.apache.commons.io.IOUtils;
import com.richard.utils.UUIDUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet("/upload")
public class FileUploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        // 1. 创建一个磁盘文件工厂对象
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // 2. 创建文件上传核心类
        ServletFileUpload upload = new ServletFileUpload(factory);
        //2.1 设置上传文件名的编码
        upload.setHeaderEncoding("utf-8");
        //2.2 判断表单是否是文件上传表单
        boolean multipartContent = upload.isMultipartContent(req);

        //2.3 是文件上传表单
        if(multipartContent) {
            // 3. 解析request -- 获取表单项集合
            List<FileItem> list = upload.parseRequest(req);
            if (list != null) {
                for (FileItem item : list) {
                    //5. 判断是不是一个普通表单项
                    boolean formField = item.isFormField();
                    if (formField) {
                        //普通表单项, 当 enctype="multipart/form-data"时, request的getParameter()方法 无法获取参数
                        String fieldName = item.getFieldName();
                        String value = item.getString("utf-8");//设置编码
                        System.out.println(fieldName + "=" + value);
                    } else {
                        //文件上传项
                        //文件名
                        String fileName = item.getName();

                        //避免图片名重复 拼接UUID
                        String newFileName = UUIDUtils.getUUID()+"_"+ fileName;

                        //获取输入流
                        InputStream in = item.getInputStream();

                        //创建输出流 输出到D盘
                        //获取项目运行目录
                        String realPath = this.getServletContext().getRealPath("/");
                        String webapps = realPath.substring(0, realPath.indexOf("lagou_edu_home"));

                        FileOutputStream out = new FileOutputStream(webapps + "/upload/" + newFileName);

                        //使用工具类IOUtils,copy文件
                        IOUtils.copy(in,out);

                        // 关闭流
                        out.close();
                        in.close();
                    }

                }
            }

        }

        // 4. 遍历表单项集合

        // 5. 判断是普通的表单项还是文件上传项



        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
