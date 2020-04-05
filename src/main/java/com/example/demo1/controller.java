package com.example.demo1;

import com.alibaba.fastjson.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName controller
 * @Description TODO
 * @Author HDXYA
 * @Date 2020/4/5 14:20
 * @Version 1.0
 **/
@RestController
public class controller {
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private RedisUtil redisUtil;
    @GetMapping("/index")
    public Object index() {

        StudentReq studentReq = new StudentReq();
        List<Integer> ids = new ArrayList<>();
        ids.add(2);
        ids.add(6);
        studentReq.setSids(ids);
        studentReq.setSname("李华");
        Student student = new Student(1,"李萍0100", 19, "男");
        Student student2 = new Student(2,"王治m23", 37, "女");
        Student student1 = new Student(null,"张聚", 27, "男");
        List<Student> list=new ArrayList<>();
        list.add(student);
        list.add(student2);
        list.add(student1);
        ;
       redisUtil.set("hdx666",JSONObject.toJSONString(list));
       redisUtil.set("address3","安庆");
       List<Student> students=JSONObject.parseArray(redisUtil.get("hdx666").toString(),Student.class);
        return studentMapper.selectStudent(4);
    }
}
