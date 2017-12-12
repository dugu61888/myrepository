package com.apcompany.api.dao.teachcourse;

import com.apcompany.api.model.schema.teachcourse.CourseDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ICourseDAO {

    @Select("select * from course")
    List<CourseDO> getCourseList();

    @Insert("insert ignore into course(name,created,modified) values(#{name},now(),now() )")
    boolean addCourse(
            @Param("name")String name);

    @Select("select * from course where id=#{id} ")
    CourseDO getById(@Param("id")int id);

}
