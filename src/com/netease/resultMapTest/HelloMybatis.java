package com.netease.resultMapTest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class HelloMybatis {
    public static void main(String[] args) {
        // 1. 声明配置文件的目录渎职
        String resource = "com/netease/conf.xml";

        // 2. 加载应用配置文件
        InputStream is = HelloMybatis.class.getClassLoader().getResourceAsStream(resource);

        // 3.创建SqlSessionFactory
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);

        // 4. 获取session
        SqlSession session = sessionFactory.openSession();

        try {
            //5. 获取操作类
            UserOp userOp = session.getMapper(UserOp.class);

            // 6.完成查询操作
            User user = userOp.getUser(1);
            System.out.println(user.getId() + " " + user.getUserName() + " " + user.getSex());
            System.out.println(user.getCourses().get(0).getCourseName());
            System.out.println(user.getCourses().get(0).getTeacher().getTeacherName());
        } finally {
            // 7. 关闭session
            session.close();
        }


    }
}
