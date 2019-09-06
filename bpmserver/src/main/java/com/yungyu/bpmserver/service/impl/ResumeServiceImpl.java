package com.yungyu.bpmserver.service.impl;

import com.yungyu.bpmserver.service.IResumeService;
import org.springframework.stereotype.Service;

@Service("resumeService")
public class ResumeServiceImpl implements IResumeService {

    @Override
    public void storeResume() {
        System.out.println("任务已经执行.............");
    }

}
