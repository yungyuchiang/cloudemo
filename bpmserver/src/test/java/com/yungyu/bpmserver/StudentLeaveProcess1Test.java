package com.yungyu.bpmserver;


import com.yungyu.bpmserver.entity.Student;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BpmApp.class)
public class StudentLeaveProcess1Test {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Test
    public void start(){
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("studentLeaveProcess1");
        System.out.println("流程实例ID:"+pi.getId());
        System.out.println("ProcessInstanceId" + pi.getProcessInstanceId());
        System.out.println("流程定义ID:"+pi.getProcessDefinitionId());
    }

    @Test
    public void getState(){
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId("62501")
                .singleResult();
        System.out.println(processInstance.getName());
    }

    @Test
    public void setVariablesValues(){
        String taskId = "62505";
        taskService.setVariable(taskId, "days", 2);
        taskService.setVariable(taskId, "date", new Date());
        taskService.setVariable(taskId, "reason", "发烧");
        Student s = new Student();
        s.setId(1);
        s.setName("张三");
        taskService.setVariable(taskId, "student", s);
    }

    @Test
    public void completeTask(){
      taskService.complete("55002");
    }

    @Test
    public void completeTask2(){
        Map<String, Object> variables=new HashMap<String,Object>();
        variables.put("msg", "重要情况");
        taskService.complete("52503", variables); //完成任务的时候，设置流程变量
    }

}
