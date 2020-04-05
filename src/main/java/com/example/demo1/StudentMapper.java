package com.example.demo1;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName Mapper
 * @Description TODO
 * @Author HDXYA
 * @Date 2020/4/5 14:16
 * @Version 1.0
 **/
@Mapper
public interface StudentMapper {
    List<Student> selectStudent(int id);
    List<StudentRespose> selectAllByName(StudentReq studentReq);
    Integer insertOne(Student student);
    Integer insertList(List<Student> studentList);

    Integer updateOne(Student student);

    Integer updateAll(List<Student> student);
}
