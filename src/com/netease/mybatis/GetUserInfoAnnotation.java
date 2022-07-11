package com.netease.mybatis;

import org.apache.ibatis.annotations.Select;

public interface GetUserInfoAnnotation {
    @Select("select id, username, sex from user where id = #{id}")
    public User getUser(int id);
}
