package com.yungyu.oauthserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yungyu.oauthserver.entity.Student;
import com.yungyu.oauthserver.mapper.StudentMapper;
import com.yungyu.oauthserver.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("studentService")
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student update(Student student) {
        this.studentMapper.updateById(student);
        return this.queryStudentByNo(student.getNo());
    }

    @Override
    public void deleteStudentByNo(String no) {
        UpdateWrapper<Student> wrapper = new UpdateWrapper();
        wrapper.set("no", no);
        this.studentMapper.delete(wrapper);
    }

    @Override
    public Student queryStudentByNo(String no) {
        QueryWrapper<Student> wrapper = new QueryWrapper();
        wrapper.eq("no", no);

        return this.studentMapper.selectOne(wrapper);
    }

}
