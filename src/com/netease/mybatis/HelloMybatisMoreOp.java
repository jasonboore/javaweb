package com.netease.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class HelloMybatisMoreOp {
    public static void main(String[] args) {
        moreOp();
    }
    public static void moreOp() {
        // 1. 声明配置文件的目录渎职
        String resource = "com/netease/conf.xml";

        // 2. 加载应用配置文件
        InputStream is = HelloMybatis.class.getClassLoader().getResourceAsStream(resource);

        // 3.创建SqlSessionFactory
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);

        // 4. 获取session
        // 不设置true的话，会把所有的SQL语句打包当成事务，直到commit才执行，设置成true就一个SQL一执行
        SqlSession session = sessionFactory.openSession(true);

        try {
            //5. 获取操作类
            UserOp userOp = session.getMapper(UserOp.class);
            User user = new User("LiMing", "女");

            //插入用户
            userOp.addUser(user);
            System.out.println(user.getId());

            // 查询用户
            user = userOp.getUser(user.getId());
            System.out.println(user.getId() + " " + user.getUserName() + " " + user.getSex());

            // 修改用户
            user.setUserName("李明");
            userOp.updateUser(user);

            // 删除用户
            userOp.deleteUser(user.getId());
        } finally {
            // 7. 关闭session
            session.close();
        }
    }
}
