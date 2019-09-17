package com.yungyu.oauthserver.service;

import com.yungyu.oauthserver.entity.Student;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
@CacheConfig(cacheNames = "student")
public interface IStudentService {

    @CachePut(key = "#p0.no")
    Student update(Student student);

    @CacheEvict(key = "#p0", allEntries = true)
    void deleteStudentByNo(String no);

    @Cacheable(key = "#p0")
    Student queryStudentByNo(String no);

}
